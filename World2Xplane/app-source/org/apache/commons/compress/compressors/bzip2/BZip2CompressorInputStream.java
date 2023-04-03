/*      */ package org.apache.commons.compress.compressors.bzip2;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import org.apache.commons.compress.compressors.CompressorInputStream;
/*      */ 
/*      */ public class BZip2CompressorInputStream extends CompressorInputStream implements BZip2Constants {
/*      */   private int last;
/*      */   
/*      */   private int origPtr;
/*      */   
/*      */   private int blockSize100k;
/*      */   
/*      */   private boolean blockRandomised;
/*      */   
/*      */   private int bsBuff;
/*      */   
/*      */   private int bsLive;
/*      */   
/*   60 */   private final CRC crc = new CRC();
/*      */   
/*      */   private int nInUse;
/*      */   
/*      */   private InputStream in;
/*      */   
/*      */   private final boolean decompressConcatenated;
/*      */   
/*   67 */   private int currentChar = -1;
/*      */   
/*      */   private static final int EOF = 0;
/*      */   
/*      */   private static final int START_BLOCK_STATE = 1;
/*      */   
/*      */   private static final int RAND_PART_A_STATE = 2;
/*      */   
/*      */   private static final int RAND_PART_B_STATE = 3;
/*      */   
/*      */   private static final int RAND_PART_C_STATE = 4;
/*      */   
/*      */   private static final int NO_RAND_PART_A_STATE = 5;
/*      */   
/*      */   private static final int NO_RAND_PART_B_STATE = 6;
/*      */   
/*      */   private static final int NO_RAND_PART_C_STATE = 7;
/*      */   
/*   78 */   private int currentState = 1;
/*      */   
/*      */   private int storedBlockCRC;
/*      */   
/*      */   private int storedCombinedCRC;
/*      */   
/*      */   private int computedBlockCRC;
/*      */   
/*      */   private int computedCombinedCRC;
/*      */   
/*      */   private int su_count;
/*      */   
/*      */   private int su_ch2;
/*      */   
/*      */   private int su_chPrev;
/*      */   
/*      */   private int su_i2;
/*      */   
/*      */   private int su_j2;
/*      */   
/*      */   private int su_rNToGo;
/*      */   
/*      */   private int su_rTPos;
/*      */   
/*      */   private int su_tPos;
/*      */   
/*      */   private char su_z;
/*      */   
/*      */   private Data data;
/*      */   
/*      */   public BZip2CompressorInputStream(InputStream in) throws IOException {
/*  111 */     this(in, false);
/*      */   }
/*      */   
/*      */   public BZip2CompressorInputStream(InputStream in, boolean decompressConcatenated) throws IOException {
/*  135 */     this.in = in;
/*  136 */     this.decompressConcatenated = decompressConcatenated;
/*  138 */     init(true);
/*  139 */     initBlock();
/*  140 */     setupBlock();
/*      */   }
/*      */   
/*      */   public int read() throws IOException {
/*  146 */     if (this.in != null) {
/*  147 */       int r = read0();
/*  148 */       count((r < 0) ? -1 : 1);
/*  149 */       return r;
/*      */     } 
/*  151 */     throw new IOException("stream closed");
/*      */   }
/*      */   
/*      */   public int read(byte[] dest, int offs, int len) throws IOException {
/*  163 */     if (offs < 0)
/*  164 */       throw new IndexOutOfBoundsException("offs(" + offs + ") < 0."); 
/*  166 */     if (len < 0)
/*  167 */       throw new IndexOutOfBoundsException("len(" + len + ") < 0."); 
/*  169 */     if (offs + len > dest.length)
/*  170 */       throw new IndexOutOfBoundsException("offs(" + offs + ") + len(" + len + ") > dest.length(" + dest.length + ")."); 
/*  173 */     if (this.in == null)
/*  174 */       throw new IOException("stream closed"); 
/*  177 */     int hi = offs + len;
/*  178 */     int destOffs = offs;
/*      */     int b;
/*  179 */     while (destOffs < hi && (b = read0()) >= 0)
/*  180 */       dest[destOffs++] = (byte)b; 
/*  183 */     int c = (destOffs == offs) ? -1 : (destOffs - offs);
/*  184 */     count(c);
/*  185 */     return c;
/*      */   }
/*      */   
/*      */   private void makeMaps() {
/*  189 */     boolean[] inUse = this.data.inUse;
/*  190 */     byte[] seqToUnseq = this.data.seqToUnseq;
/*  192 */     int nInUseShadow = 0;
/*  194 */     for (int i = 0; i < 256; i++) {
/*  195 */       if (inUse[i])
/*  196 */         seqToUnseq[nInUseShadow++] = (byte)i; 
/*      */     } 
/*  200 */     this.nInUse = nInUseShadow;
/*      */   }
/*      */   
/*      */   private int read0() throws IOException {
/*  204 */     int retChar = this.currentChar;
/*  206 */     switch (this.currentState) {
/*      */       case 0:
/*  208 */         return -1;
/*      */       case 1:
/*  211 */         throw new IllegalStateException();
/*      */       case 2:
/*  214 */         throw new IllegalStateException();
/*      */       case 3:
/*  217 */         setupRandPartB();
/*  239 */         return retChar;
/*      */       case 4:
/*      */         setupRandPartC();
/*  239 */         return retChar;
/*      */       case 5:
/*      */         throw new IllegalStateException();
/*      */       case 6:
/*      */         setupNoRandPartB();
/*  239 */         return retChar;
/*      */       case 7:
/*      */         setupNoRandPartC();
/*  239 */         return retChar;
/*      */     } 
/*      */     throw new IllegalStateException();
/*      */   }
/*      */   
/*      */   private boolean init(boolean isFirstStream) throws IOException {
/*  243 */     if (null == this.in)
/*  244 */       throw new IOException("No InputStream"); 
/*  247 */     int magic0 = this.in.read();
/*  248 */     int magic1 = this.in.read();
/*  249 */     int magic2 = this.in.read();
/*  250 */     if (magic0 == -1 && !isFirstStream)
/*  251 */       return false; 
/*  254 */     if (magic0 != 66 || magic1 != 90 || magic2 != 104)
/*  255 */       throw new IOException(isFirstStream ? "Stream is not in the BZip2 format" : "Garbage after a valid BZip2 stream"); 
/*  260 */     int blockSize = this.in.read();
/*  261 */     if (blockSize < 49 || blockSize > 57)
/*  262 */       throw new IOException("BZip2 block size is invalid"); 
/*  265 */     this.blockSize100k = blockSize - 48;
/*  267 */     this.bsLive = 0;
/*  268 */     this.computedCombinedCRC = 0;
/*  270 */     return true;
/*      */   }
/*      */   
/*      */   private void initBlock() throws IOException {
/*      */     char magic0, magic1, magic2, magic3, magic4, magic5;
/*      */     while (true) {
/*  283 */       magic0 = bsGetUByte();
/*  284 */       magic1 = bsGetUByte();
/*  285 */       magic2 = bsGetUByte();
/*  286 */       magic3 = bsGetUByte();
/*  287 */       magic4 = bsGetUByte();
/*  288 */       magic5 = bsGetUByte();
/*  291 */       if (magic0 != '\027' || magic1 != 'r' || magic2 != 'E' || magic3 != '8' || magic4 != 'P' || magic5 != '')
/*      */         break; 
/*  299 */       if (complete())
/*      */         return; 
/*      */     } 
/*  304 */     if (magic0 != '1' || magic1 != 'A' || magic2 != 'Y' || magic3 != '&' || magic4 != 'S' || magic5 != 'Y') {
/*  311 */       this.currentState = 0;
/*  312 */       throw new IOException("bad block header");
/*      */     } 
/*  314 */     this.storedBlockCRC = bsGetInt();
/*  315 */     this.blockRandomised = (bsR(1) == 1);
/*  321 */     if (this.data == null)
/*  322 */       this.data = new Data(this.blockSize100k); 
/*  326 */     getAndMoveToFrontDecode();
/*  328 */     this.crc.initialiseCRC();
/*  329 */     this.currentState = 1;
/*      */   }
/*      */   
/*      */   private void endBlock() throws IOException {
/*  334 */     this.computedBlockCRC = this.crc.getFinalCRC();
/*  337 */     if (this.storedBlockCRC != this.computedBlockCRC) {
/*  340 */       this.computedCombinedCRC = this.storedCombinedCRC << 1 | this.storedCombinedCRC >>> 31;
/*  342 */       this.computedCombinedCRC ^= this.storedBlockCRC;
/*  344 */       throw new IOException("BZip2 CRC error");
/*      */     } 
/*  347 */     this.computedCombinedCRC = this.computedCombinedCRC << 1 | this.computedCombinedCRC >>> 31;
/*  349 */     this.computedCombinedCRC ^= this.computedBlockCRC;
/*      */   }
/*      */   
/*      */   private boolean complete() throws IOException {
/*  353 */     this.storedCombinedCRC = bsGetInt();
/*  354 */     this.currentState = 0;
/*  355 */     this.data = null;
/*  357 */     if (this.storedCombinedCRC != this.computedCombinedCRC)
/*  358 */       throw new IOException("BZip2 CRC error"); 
/*  363 */     return (!this.decompressConcatenated || !init(false));
/*      */   }
/*      */   
/*      */   public void close() throws IOException {
/*  368 */     InputStream inShadow = this.in;
/*  369 */     if (inShadow != null)
/*      */       try {
/*  371 */         if (inShadow != System.in)
/*  372 */           inShadow.close(); 
/*      */       } finally {
/*  375 */         this.data = null;
/*  376 */         this.in = null;
/*      */       }  
/*      */   }
/*      */   
/*      */   private int bsR(int n) throws IOException {
/*  382 */     int bsLiveShadow = this.bsLive;
/*  383 */     int bsBuffShadow = this.bsBuff;
/*  385 */     if (bsLiveShadow < n) {
/*  386 */       InputStream inShadow = this.in;
/*      */       do {
/*  388 */         int thech = inShadow.read();
/*  390 */         if (thech < 0)
/*  391 */           throw new IOException("unexpected end of stream"); 
/*  394 */         bsBuffShadow = bsBuffShadow << 8 | thech;
/*  395 */         bsLiveShadow += 8;
/*  396 */       } while (bsLiveShadow < n);
/*  398 */       this.bsBuff = bsBuffShadow;
/*      */     } 
/*  401 */     this.bsLive = bsLiveShadow - n;
/*  402 */     return bsBuffShadow >> bsLiveShadow - n & (1 << n) - 1;
/*      */   }
/*      */   
/*      */   private boolean bsGetBit() throws IOException {
/*  406 */     int bsLiveShadow = this.bsLive;
/*  407 */     int bsBuffShadow = this.bsBuff;
/*  409 */     if (bsLiveShadow < 1) {
/*  410 */       int thech = this.in.read();
/*  412 */       if (thech < 0)
/*  413 */         throw new IOException("unexpected end of stream"); 
/*  416 */       bsBuffShadow = bsBuffShadow << 8 | thech;
/*  417 */       bsLiveShadow += 8;
/*  418 */       this.bsBuff = bsBuffShadow;
/*      */     } 
/*  421 */     this.bsLive = bsLiveShadow - 1;
/*  422 */     return ((bsBuffShadow >> bsLiveShadow - 1 & 0x1) != 0);
/*      */   }
/*      */   
/*      */   private char bsGetUByte() throws IOException {
/*  426 */     return (char)bsR(8);
/*      */   }
/*      */   
/*      */   private int bsGetInt() throws IOException {
/*  430 */     return ((bsR(8) << 8 | bsR(8)) << 8 | bsR(8)) << 8 | bsR(8);
/*      */   }
/*      */   
/*      */   private static void hbCreateDecodeTables(int[] limit, int[] base, int[] perm, char[] length, int minLen, int maxLen, int alphaSize) {
/*      */     int i;
/*      */     int pp;
/*  439 */     for (i = minLen, pp = 0; i <= maxLen; i++) {
/*  440 */       for (int k = 0; k < alphaSize; k++) {
/*  441 */         if (length[k] == i)
/*  442 */           perm[pp++] = k; 
/*      */       } 
/*      */     } 
/*  447 */     for (i = 23; --i > 0; ) {
/*  448 */       base[i] = 0;
/*  449 */       limit[i] = 0;
/*      */     } 
/*  452 */     for (i = 0; i < alphaSize; i++)
/*  453 */       base[length[i] + 1] = base[length[i] + 1] + 1; 
/*      */     int b;
/*  456 */     for (i = 1, b = base[0]; i < 23; i++) {
/*  457 */       b += base[i];
/*  458 */       base[i] = b;
/*      */     } 
/*      */     int vec;
/*      */     int j;
/*  461 */     for (i = minLen, vec = 0, j = base[i]; i <= maxLen; i++) {
/*  462 */       int nb = base[i + 1];
/*  463 */       vec += nb - j;
/*  464 */       j = nb;
/*  465 */       limit[i] = vec - 1;
/*  466 */       vec <<= 1;
/*      */     } 
/*  469 */     for (i = minLen + 1; i <= maxLen; i++)
/*  470 */       base[i] = (limit[i - 1] + 1 << 1) - base[i]; 
/*      */   }
/*      */   
/*      */   private void recvDecodingTables() throws IOException {
/*  475 */     Data dataShadow = this.data;
/*  476 */     boolean[] inUse = dataShadow.inUse;
/*  477 */     byte[] pos = dataShadow.recvDecodingTables_pos;
/*  478 */     byte[] selector = dataShadow.selector;
/*  479 */     byte[] selectorMtf = dataShadow.selectorMtf;
/*  481 */     int inUse16 = 0;
/*      */     int i;
/*  484 */     for (i = 0; i < 16; i++) {
/*  485 */       if (bsGetBit())
/*  486 */         inUse16 |= 1 << i; 
/*      */     } 
/*  490 */     for (i = 256; --i >= 0;)
/*  491 */       inUse[i] = false; 
/*  494 */     for (i = 0; i < 16; i++) {
/*  495 */       if ((inUse16 & 1 << i) != 0) {
/*  496 */         int i16 = i << 4;
/*  497 */         for (int m = 0; m < 16; m++) {
/*  498 */           if (bsGetBit())
/*  499 */             inUse[i16 + m] = true; 
/*      */         } 
/*      */       } 
/*      */     } 
/*  505 */     makeMaps();
/*  506 */     int alphaSize = this.nInUse + 2;
/*  509 */     int nGroups = bsR(3);
/*  510 */     int nSelectors = bsR(15);
/*  512 */     for (int k = 0; k < nSelectors; k++) {
/*  513 */       int m = 0;
/*  514 */       while (bsGetBit())
/*  515 */         m++; 
/*  517 */       selectorMtf[k] = (byte)m;
/*      */     } 
/*  521 */     for (int v = nGroups; --v >= 0;)
/*  522 */       pos[v] = (byte)v; 
/*  525 */     for (int j = 0; j < nSelectors; j++) {
/*  526 */       int m = selectorMtf[j] & 0xFF;
/*  527 */       byte tmp = pos[m];
/*  528 */       while (m > 0) {
/*  530 */         pos[m] = pos[m - 1];
/*  531 */         m--;
/*      */       } 
/*  533 */       pos[0] = tmp;
/*  534 */       selector[j] = tmp;
/*      */     } 
/*  537 */     char[][] len = dataShadow.temp_charArray2d;
/*  540 */     for (int t = 0; t < nGroups; t++) {
/*  541 */       int curr = bsR(5);
/*  542 */       char[] len_t = len[t];
/*  543 */       for (int m = 0; m < alphaSize; m++) {
/*  544 */         while (bsGetBit())
/*  545 */           curr += bsGetBit() ? -1 : 1; 
/*  547 */         len_t[m] = (char)curr;
/*      */       } 
/*      */     } 
/*  552 */     createHuffmanDecodingTables(alphaSize, nGroups);
/*      */   }
/*      */   
/*      */   private void createHuffmanDecodingTables(int alphaSize, int nGroups) {
/*  560 */     Data dataShadow = this.data;
/*  561 */     char[][] len = dataShadow.temp_charArray2d;
/*  562 */     int[] minLens = dataShadow.minLens;
/*  563 */     int[][] limit = dataShadow.limit;
/*  564 */     int[][] base = dataShadow.base;
/*  565 */     int[][] perm = dataShadow.perm;
/*  567 */     for (int t = 0; t < nGroups; t++) {
/*  568 */       int minLen = 32;
/*  569 */       int maxLen = 0;
/*  570 */       char[] len_t = len[t];
/*  571 */       for (int i = alphaSize; --i >= 0; ) {
/*  572 */         char lent = len_t[i];
/*  573 */         if (lent > maxLen)
/*  574 */           maxLen = lent; 
/*  576 */         if (lent < minLen)
/*  577 */           minLen = lent; 
/*      */       } 
/*  580 */       hbCreateDecodeTables(limit[t], base[t], perm[t], len[t], minLen, maxLen, alphaSize);
/*  582 */       minLens[t] = minLen;
/*      */     } 
/*      */   }
/*      */   
/*      */   private void getAndMoveToFrontDecode() throws IOException {
/*  587 */     this.origPtr = bsR(24);
/*  588 */     recvDecodingTables();
/*  590 */     InputStream inShadow = this.in;
/*  591 */     Data dataShadow = this.data;
/*  592 */     byte[] ll8 = dataShadow.ll8;
/*  593 */     int[] unzftab = dataShadow.unzftab;
/*  594 */     byte[] selector = dataShadow.selector;
/*  595 */     byte[] seqToUnseq = dataShadow.seqToUnseq;
/*  596 */     char[] yy = dataShadow.getAndMoveToFrontDecode_yy;
/*  597 */     int[] minLens = dataShadow.minLens;
/*  598 */     int[][] limit = dataShadow.limit;
/*  599 */     int[][] base = dataShadow.base;
/*  600 */     int[][] perm = dataShadow.perm;
/*  601 */     int limitLast = this.blockSize100k * 100000;
/*  608 */     for (int i = 256; --i >= 0; ) {
/*  609 */       yy[i] = (char)i;
/*  610 */       unzftab[i] = 0;
/*      */     } 
/*  613 */     int groupNo = 0;
/*  614 */     int groupPos = 49;
/*  615 */     int eob = this.nInUse + 1;
/*  616 */     int nextSym = getAndMoveToFrontDecode0(0);
/*  617 */     int bsBuffShadow = this.bsBuff;
/*  618 */     int bsLiveShadow = this.bsLive;
/*  619 */     int lastShadow = -1;
/*  620 */     int zt = selector[groupNo] & 0xFF;
/*  621 */     int[] base_zt = base[zt];
/*  622 */     int[] limit_zt = limit[zt];
/*  623 */     int[] perm_zt = perm[zt];
/*  624 */     int minLens_zt = minLens[zt];
/*  626 */     while (nextSym != eob) {
/*  627 */       if (nextSym == 0 || nextSym == 1) {
/*  628 */         int s = -1;
/*      */         int n;
/*  630 */         for (n = 1;; n <<= 1) {
/*  631 */           if (nextSym == 0) {
/*  632 */             s += n;
/*  633 */           } else if (nextSym == 1) {
/*  634 */             s += n << 1;
/*      */           } else {
/*      */             break;
/*      */           } 
/*  639 */           if (groupPos == 0) {
/*  640 */             groupPos = 49;
/*  641 */             zt = selector[++groupNo] & 0xFF;
/*  642 */             base_zt = base[zt];
/*  643 */             limit_zt = limit[zt];
/*  644 */             perm_zt = perm[zt];
/*  645 */             minLens_zt = minLens[zt];
/*      */           } else {
/*  647 */             groupPos--;
/*      */           } 
/*  650 */           int j = minLens_zt;
/*  654 */           while (bsLiveShadow < j) {
/*  655 */             int thech = inShadow.read();
/*  656 */             if (thech >= 0) {
/*  657 */               bsBuffShadow = bsBuffShadow << 8 | thech;
/*  658 */               bsLiveShadow += 8;
/*      */               continue;
/*      */             } 
/*  661 */             throw new IOException("unexpected end of stream");
/*      */           } 
/*  664 */           int k = bsBuffShadow >> bsLiveShadow - j & (1 << j) - 1;
/*  666 */           bsLiveShadow -= j;
/*  668 */           while (k > limit_zt[j]) {
/*  669 */             j++;
/*  670 */             while (bsLiveShadow < 1) {
/*  671 */               int thech = inShadow.read();
/*  672 */               if (thech >= 0) {
/*  673 */                 bsBuffShadow = bsBuffShadow << 8 | thech;
/*  674 */                 bsLiveShadow += 8;
/*      */                 continue;
/*      */               } 
/*  677 */               throw new IOException("unexpected end of stream");
/*      */             } 
/*  681 */             bsLiveShadow--;
/*  682 */             k = k << 1 | bsBuffShadow >> bsLiveShadow & 0x1;
/*      */           } 
/*  685 */           nextSym = perm_zt[k - base_zt[j]];
/*      */         } 
/*  688 */         byte ch = seqToUnseq[yy[0]];
/*  689 */         unzftab[ch & 0xFF] = unzftab[ch & 0xFF] + s + 1;
/*  691 */         while (s-- >= 0)
/*  692 */           ll8[++lastShadow] = ch; 
/*  695 */         if (lastShadow >= limitLast)
/*  696 */           throw new IOException("block overrun"); 
/*      */         continue;
/*      */       } 
/*  699 */       if (++lastShadow >= limitLast)
/*  700 */         throw new IOException("block overrun"); 
/*  703 */       char tmp = yy[nextSym - 1];
/*  704 */       unzftab[seqToUnseq[tmp] & 0xFF] = unzftab[seqToUnseq[tmp] & 0xFF] + 1;
/*  705 */       ll8[lastShadow] = seqToUnseq[tmp];
/*  712 */       if (nextSym <= 16) {
/*  713 */         for (int j = nextSym - 1; j > 0;)
/*  714 */           yy[j] = yy[--j]; 
/*      */       } else {
/*  717 */         System.arraycopy(yy, 0, yy, 1, nextSym - 1);
/*      */       } 
/*  720 */       yy[0] = tmp;
/*  722 */       if (groupPos == 0) {
/*  723 */         groupPos = 49;
/*  724 */         zt = selector[++groupNo] & 0xFF;
/*  725 */         base_zt = base[zt];
/*  726 */         limit_zt = limit[zt];
/*  727 */         perm_zt = perm[zt];
/*  728 */         minLens_zt = minLens[zt];
/*      */       } else {
/*  730 */         groupPos--;
/*      */       } 
/*  733 */       int zn = minLens_zt;
/*  737 */       while (bsLiveShadow < zn) {
/*  738 */         int thech = inShadow.read();
/*  739 */         if (thech >= 0) {
/*  740 */           bsBuffShadow = bsBuffShadow << 8 | thech;
/*  741 */           bsLiveShadow += 8;
/*      */           continue;
/*      */         } 
/*  744 */         throw new IOException("unexpected end of stream");
/*      */       } 
/*  747 */       int zvec = bsBuffShadow >> bsLiveShadow - zn & (1 << zn) - 1;
/*  749 */       bsLiveShadow -= zn;
/*  751 */       while (zvec > limit_zt[zn]) {
/*  752 */         zn++;
/*  753 */         while (bsLiveShadow < 1) {
/*  754 */           int thech = inShadow.read();
/*  755 */           if (thech >= 0) {
/*  756 */             bsBuffShadow = bsBuffShadow << 8 | thech;
/*  757 */             bsLiveShadow += 8;
/*      */             continue;
/*      */           } 
/*  760 */           throw new IOException("unexpected end of stream");
/*      */         } 
/*  763 */         bsLiveShadow--;
/*  764 */         zvec = zvec << 1 | bsBuffShadow >> bsLiveShadow & 0x1;
/*      */       } 
/*  766 */       nextSym = perm_zt[zvec - base_zt[zn]];
/*      */     } 
/*  770 */     this.last = lastShadow;
/*  771 */     this.bsLive = bsLiveShadow;
/*  772 */     this.bsBuff = bsBuffShadow;
/*      */   }
/*      */   
/*      */   private int getAndMoveToFrontDecode0(int groupNo) throws IOException {
/*  776 */     InputStream inShadow = this.in;
/*  777 */     Data dataShadow = this.data;
/*  778 */     int zt = dataShadow.selector[groupNo] & 0xFF;
/*  779 */     int[] limit_zt = dataShadow.limit[zt];
/*  780 */     int zn = dataShadow.minLens[zt];
/*  781 */     int zvec = bsR(zn);
/*  782 */     int bsLiveShadow = this.bsLive;
/*  783 */     int bsBuffShadow = this.bsBuff;
/*  785 */     while (zvec > limit_zt[zn]) {
/*  786 */       zn++;
/*  787 */       while (bsLiveShadow < 1) {
/*  788 */         int thech = inShadow.read();
/*  790 */         if (thech >= 0) {
/*  791 */           bsBuffShadow = bsBuffShadow << 8 | thech;
/*  792 */           bsLiveShadow += 8;
/*      */           continue;
/*      */         } 
/*  795 */         throw new IOException("unexpected end of stream");
/*      */       } 
/*  798 */       bsLiveShadow--;
/*  799 */       zvec = zvec << 1 | bsBuffShadow >> bsLiveShadow & 0x1;
/*      */     } 
/*  802 */     this.bsLive = bsLiveShadow;
/*  803 */     this.bsBuff = bsBuffShadow;
/*  805 */     return dataShadow.perm[zt][zvec - dataShadow.base[zt][zn]];
/*      */   }
/*      */   
/*      */   private void setupBlock() throws IOException {
/*  809 */     if (this.data == null)
/*      */       return; 
/*  813 */     int[] cftab = this.data.cftab;
/*  814 */     int[] tt = this.data.initTT(this.last + 1);
/*  815 */     byte[] ll8 = this.data.ll8;
/*  816 */     cftab[0] = 0;
/*  817 */     System.arraycopy(this.data.unzftab, 0, cftab, 1, 256);
/*      */     int i, c;
/*  819 */     for (i = 1, c = cftab[0]; i <= 256; i++) {
/*  820 */       c += cftab[i];
/*  821 */       cftab[i] = c;
/*      */     } 
/*      */     int lastShadow;
/*  824 */     for (i = 0, lastShadow = this.last; i <= lastShadow; i++) {
/*  825 */       cftab[ll8[i] & 0xFF] = cftab[ll8[i] & 0xFF] + 1;
/*  825 */       tt[cftab[ll8[i] & 0xFF]] = i;
/*      */     } 
/*  828 */     if (this.origPtr < 0 || this.origPtr >= tt.length)
/*  829 */       throw new IOException("stream corrupted"); 
/*  832 */     this.su_tPos = tt[this.origPtr];
/*  833 */     this.su_count = 0;
/*  834 */     this.su_i2 = 0;
/*  835 */     this.su_ch2 = 256;
/*  837 */     if (this.blockRandomised) {
/*  838 */       this.su_rNToGo = 0;
/*  839 */       this.su_rTPos = 0;
/*  840 */       setupRandPartA();
/*      */     } else {
/*  842 */       setupNoRandPartA();
/*      */     } 
/*      */   }
/*      */   
/*      */   private void setupRandPartA() throws IOException {
/*  847 */     if (this.su_i2 <= this.last) {
/*  848 */       this.su_chPrev = this.su_ch2;
/*  849 */       int su_ch2Shadow = this.data.ll8[this.su_tPos] & 0xFF;
/*  850 */       this.su_tPos = this.data.tt[this.su_tPos];
/*  851 */       if (this.su_rNToGo == 0) {
/*  852 */         this.su_rNToGo = Rand.rNums(this.su_rTPos) - 1;
/*  853 */         if (++this.su_rTPos == 512)
/*  854 */           this.su_rTPos = 0; 
/*      */       } else {
/*  857 */         this.su_rNToGo--;
/*      */       } 
/*  859 */       this.su_ch2 = su_ch2Shadow ^= (this.su_rNToGo == 1) ? 1 : 0;
/*  860 */       this.su_i2++;
/*  861 */       this.currentChar = su_ch2Shadow;
/*  862 */       this.currentState = 3;
/*  863 */       this.crc.updateCRC(su_ch2Shadow);
/*      */     } else {
/*  865 */       endBlock();
/*  866 */       initBlock();
/*  867 */       setupBlock();
/*      */     } 
/*      */   }
/*      */   
/*      */   private void setupNoRandPartA() throws IOException {
/*  872 */     if (this.su_i2 <= this.last) {
/*  873 */       this.su_chPrev = this.su_ch2;
/*  874 */       int su_ch2Shadow = this.data.ll8[this.su_tPos] & 0xFF;
/*  875 */       this.su_ch2 = su_ch2Shadow;
/*  876 */       this.su_tPos = this.data.tt[this.su_tPos];
/*  877 */       this.su_i2++;
/*  878 */       this.currentChar = su_ch2Shadow;
/*  879 */       this.currentState = 6;
/*  880 */       this.crc.updateCRC(su_ch2Shadow);
/*      */     } else {
/*  882 */       this.currentState = 5;
/*  883 */       endBlock();
/*  884 */       initBlock();
/*  885 */       setupBlock();
/*      */     } 
/*      */   }
/*      */   
/*      */   private void setupRandPartB() throws IOException {
/*  890 */     if (this.su_ch2 != this.su_chPrev) {
/*  891 */       this.currentState = 2;
/*  892 */       this.su_count = 1;
/*  893 */       setupRandPartA();
/*  894 */     } else if (++this.su_count >= 4) {
/*  895 */       this.su_z = (char)(this.data.ll8[this.su_tPos] & 0xFF);
/*  896 */       this.su_tPos = this.data.tt[this.su_tPos];
/*  897 */       if (this.su_rNToGo == 0) {
/*  898 */         this.su_rNToGo = Rand.rNums(this.su_rTPos) - 1;
/*  899 */         if (++this.su_rTPos == 512)
/*  900 */           this.su_rTPos = 0; 
/*      */       } else {
/*  903 */         this.su_rNToGo--;
/*      */       } 
/*  905 */       this.su_j2 = 0;
/*  906 */       this.currentState = 4;
/*  907 */       if (this.su_rNToGo == 1)
/*  908 */         this.su_z = (char)(this.su_z ^ 0x1); 
/*  910 */       setupRandPartC();
/*      */     } else {
/*  912 */       this.currentState = 2;
/*  913 */       setupRandPartA();
/*      */     } 
/*      */   }
/*      */   
/*      */   private void setupRandPartC() throws IOException {
/*  918 */     if (this.su_j2 < this.su_z) {
/*  919 */       this.currentChar = this.su_ch2;
/*  920 */       this.crc.updateCRC(this.su_ch2);
/*  921 */       this.su_j2++;
/*      */     } else {
/*  923 */       this.currentState = 2;
/*  924 */       this.su_i2++;
/*  925 */       this.su_count = 0;
/*  926 */       setupRandPartA();
/*      */     } 
/*      */   }
/*      */   
/*      */   private void setupNoRandPartB() throws IOException {
/*  931 */     if (this.su_ch2 != this.su_chPrev) {
/*  932 */       this.su_count = 1;
/*  933 */       setupNoRandPartA();
/*  934 */     } else if (++this.su_count >= 4) {
/*  935 */       this.su_z = (char)(this.data.ll8[this.su_tPos] & 0xFF);
/*  936 */       this.su_tPos = this.data.tt[this.su_tPos];
/*  937 */       this.su_j2 = 0;
/*  938 */       setupNoRandPartC();
/*      */     } else {
/*  940 */       setupNoRandPartA();
/*      */     } 
/*      */   }
/*      */   
/*      */   private void setupNoRandPartC() throws IOException {
/*  945 */     if (this.su_j2 < this.su_z) {
/*  946 */       int su_ch2Shadow = this.su_ch2;
/*  947 */       this.currentChar = su_ch2Shadow;
/*  948 */       this.crc.updateCRC(su_ch2Shadow);
/*  949 */       this.su_j2++;
/*  950 */       this.currentState = 7;
/*      */     } else {
/*  952 */       this.su_i2++;
/*  953 */       this.su_count = 0;
/*  954 */       setupNoRandPartA();
/*      */     } 
/*      */   }
/*      */   
/*      */   private static final class Data {
/*  961 */     final boolean[] inUse = new boolean[256];
/*      */     
/*  963 */     final byte[] seqToUnseq = new byte[256];
/*      */     
/*  964 */     final byte[] selector = new byte[18002];
/*      */     
/*  965 */     final byte[] selectorMtf = new byte[18002];
/*      */     
/*  971 */     final int[] unzftab = new int[256];
/*      */     
/*  973 */     final int[][] limit = new int[6][258];
/*      */     
/*  974 */     final int[][] base = new int[6][258];
/*      */     
/*  975 */     final int[][] perm = new int[6][258];
/*      */     
/*  976 */     final int[] minLens = new int[6];
/*      */     
/*  978 */     final int[] cftab = new int[257];
/*      */     
/*  979 */     final char[] getAndMoveToFrontDecode_yy = new char[256];
/*      */     
/*  980 */     final char[][] temp_charArray2d = new char[6][258];
/*      */     
/*  982 */     final byte[] recvDecodingTables_pos = new byte[6];
/*      */     
/*      */     int[] tt;
/*      */     
/*      */     byte[] ll8;
/*      */     
/*      */     Data(int blockSize100k) {
/*  996 */       this.ll8 = new byte[blockSize100k * 100000];
/*      */     }
/*      */     
/*      */     int[] initTT(int length) {
/* 1007 */       int[] ttShadow = this.tt;
/* 1013 */       if (ttShadow == null || ttShadow.length < length)
/* 1014 */         this.tt = ttShadow = new int[length]; 
/* 1017 */       return ttShadow;
/*      */     }
/*      */   }
/*      */   
/*      */   public static boolean matches(byte[] signature, int length) {
/* 1035 */     if (length < 3)
/* 1036 */       return false; 
/* 1039 */     if (signature[0] != 66)
/* 1040 */       return false; 
/* 1043 */     if (signature[1] != 90)
/* 1044 */       return false; 
/* 1047 */     if (signature[2] != 104)
/* 1048 */       return false; 
/* 1051 */     return true;
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\compress\compressors\bzip2\BZip2CompressorInputStream.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */