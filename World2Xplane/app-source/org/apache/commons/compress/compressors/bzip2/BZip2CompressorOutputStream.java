/*      */ package org.apache.commons.compress.compressors.bzip2;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import java.io.OutputStream;
/*      */ import org.apache.commons.compress.compressors.CompressorOutputStream;
/*      */ 
/*      */ public class BZip2CompressorOutputStream extends CompressorOutputStream implements BZip2Constants {
/*      */   public static final int MIN_BLOCKSIZE = 1;
/*      */   
/*      */   public static final int MAX_BLOCKSIZE = 9;
/*      */   
/*      */   private static final int GREATER_ICOST = 15;
/*      */   
/*      */   private static final int LESSER_ICOST = 0;
/*      */   
/*      */   private int last;
/*      */   
/*      */   private final int blockSize100k;
/*      */   
/*      */   private int bsBuff;
/*      */   
/*      */   private int bsLive;
/*      */   
/*      */   private static void hbMakeCodeLengths(byte[] len, int[] freq, Data dat, int alphaSize, int maxLen) {
/*  150 */     int[] heap = dat.heap;
/*  151 */     int[] weight = dat.weight;
/*  152 */     int[] parent = dat.parent;
/*  154 */     for (int i = alphaSize; --i >= 0;)
/*  155 */       weight[i + 1] = ((freq[i] == 0) ? 1 : freq[i]) << 8; 
/*  158 */     for (boolean tooLong = true; tooLong; ) {
/*  159 */       tooLong = false;
/*  161 */       int nNodes = alphaSize;
/*  162 */       int nHeap = 0;
/*  163 */       heap[0] = 0;
/*  164 */       weight[0] = 0;
/*  165 */       parent[0] = -2;
/*      */       int j;
/*  167 */       for (j = 1; j <= alphaSize; j++) {
/*  168 */         parent[j] = -1;
/*  169 */         nHeap++;
/*  170 */         heap[nHeap] = j;
/*  172 */         int zz = nHeap;
/*  173 */         int tmp = heap[zz];
/*  174 */         while (weight[tmp] < weight[heap[zz >> 1]]) {
/*  175 */           heap[zz] = heap[zz >> 1];
/*  176 */           zz >>= 1;
/*      */         } 
/*  178 */         heap[zz] = tmp;
/*      */       } 
/*  181 */       while (nHeap > 1) {
/*  182 */         int n1 = heap[1];
/*  183 */         heap[1] = heap[nHeap];
/*  184 */         nHeap--;
/*  186 */         int yy = 0;
/*  187 */         int zz = 1;
/*  188 */         int tmp = heap[1];
/*      */         while (true) {
/*  191 */           yy = zz << 1;
/*  193 */           if (yy > nHeap)
/*      */             break; 
/*  197 */           if (yy < nHeap && weight[heap[yy + 1]] < weight[heap[yy]])
/*  199 */             yy++; 
/*  202 */           if (weight[tmp] < weight[heap[yy]])
/*      */             break; 
/*  206 */           heap[zz] = heap[yy];
/*  207 */           zz = yy;
/*      */         } 
/*  210 */         heap[zz] = tmp;
/*  212 */         int n2 = heap[1];
/*  213 */         heap[1] = heap[nHeap];
/*  214 */         nHeap--;
/*  216 */         yy = 0;
/*  217 */         zz = 1;
/*  218 */         tmp = heap[1];
/*      */         while (true) {
/*  221 */           yy = zz << 1;
/*  223 */           if (yy > nHeap)
/*      */             break; 
/*  227 */           if (yy < nHeap && weight[heap[yy + 1]] < weight[heap[yy]])
/*  229 */             yy++; 
/*  232 */           if (weight[tmp] < weight[heap[yy]])
/*      */             break; 
/*  236 */           heap[zz] = heap[yy];
/*  237 */           zz = yy;
/*      */         } 
/*  240 */         heap[zz] = tmp;
/*  241 */         nNodes++;
/*  242 */         parent[n2] = nNodes;
/*  242 */         parent[n1] = nNodes;
/*  244 */         int weight_n1 = weight[n1];
/*  245 */         int weight_n2 = weight[n2];
/*  246 */         weight[nNodes] = (weight_n1 & 0xFFFFFF00) + (weight_n2 & 0xFFFFFF00) | 1 + (((weight_n1 & 0xFF) > (weight_n2 & 0xFF)) ? (weight_n1 & 0xFF) : (weight_n2 & 0xFF));
/*  253 */         parent[nNodes] = -1;
/*  254 */         nHeap++;
/*  255 */         heap[nHeap] = nNodes;
/*  257 */         tmp = 0;
/*  258 */         zz = nHeap;
/*  259 */         tmp = heap[zz];
/*  260 */         int weight_tmp = weight[tmp];
/*  261 */         while (weight_tmp < weight[heap[zz >> 1]]) {
/*  262 */           heap[zz] = heap[zz >> 1];
/*  263 */           zz >>= 1;
/*      */         } 
/*  265 */         heap[zz] = tmp;
/*      */       } 
/*  269 */       for (j = 1; j <= alphaSize; j++) {
/*  270 */         int m = 0;
/*  271 */         int k = j;
/*      */         int parent_k;
/*  273 */         while ((parent_k = parent[k]) >= 0) {
/*  274 */           k = parent_k;
/*  275 */           m++;
/*      */         } 
/*  278 */         len[j - 1] = (byte)m;
/*  279 */         if (m > maxLen)
/*  280 */           tooLong = true; 
/*      */       } 
/*  284 */       if (tooLong)
/*  285 */         for (j = 1; j < alphaSize; j++) {
/*  286 */           int k = weight[j] >> 8;
/*  287 */           k = 1 + (k >> 1);
/*  288 */           weight[j] = k << 8;
/*      */         }  
/*      */     } 
/*      */   }
/*      */   
/*  307 */   private final CRC crc = new CRC();
/*      */   
/*      */   private int nInUse;
/*      */   
/*      */   private int nMTF;
/*      */   
/*  313 */   private int currentChar = -1;
/*      */   
/*  314 */   private int runLength = 0;
/*      */   
/*      */   private int blockCRC;
/*      */   
/*      */   private int combinedCRC;
/*      */   
/*      */   private final int allowableBlockSize;
/*      */   
/*      */   private Data data;
/*      */   
/*      */   private BlockSort blockSorter;
/*      */   
/*      */   private OutputStream out;
/*      */   
/*      */   public static int chooseBlockSize(long inputLength) {
/*  341 */     return (inputLength > 0L) ? (int)Math.min(inputLength / 132000L + 1L, 9L) : 9;
/*      */   }
/*      */   
/*      */   public BZip2CompressorOutputStream(OutputStream out) throws IOException {
/*  358 */     this(out, 9);
/*      */   }
/*      */   
/*      */   public BZip2CompressorOutputStream(OutputStream out, int blockSize) throws IOException {
/*  384 */     if (blockSize < 1)
/*  385 */       throw new IllegalArgumentException("blockSize(" + blockSize + ") < 1"); 
/*  388 */     if (blockSize > 9)
/*  389 */       throw new IllegalArgumentException("blockSize(" + blockSize + ") > 9"); 
/*  393 */     this.blockSize100k = blockSize;
/*  394 */     this.out = out;
/*  397 */     this.allowableBlockSize = this.blockSize100k * 100000 - 20;
/*  398 */     init();
/*      */   }
/*      */   
/*      */   public void write(int b) throws IOException {
/*  404 */     if (this.out != null) {
/*  405 */       write0(b);
/*      */     } else {
/*  407 */       throw new IOException("closed");
/*      */     } 
/*      */   }
/*      */   
/*      */   private void writeRun() throws IOException {
/*  425 */     int lastShadow = this.last;
/*  427 */     if (lastShadow < this.allowableBlockSize) {
/*  428 */       int currentCharShadow = this.currentChar;
/*  429 */       Data dataShadow = this.data;
/*  430 */       dataShadow.inUse[currentCharShadow] = true;
/*  431 */       byte ch = (byte)currentCharShadow;
/*  433 */       int runLengthShadow = this.runLength;
/*  434 */       this.crc.updateCRC(currentCharShadow, runLengthShadow);
/*  436 */       switch (runLengthShadow) {
/*      */         case 1:
/*  438 */           dataShadow.block[lastShadow + 2] = ch;
/*  439 */           this.last = lastShadow + 1;
/*      */           return;
/*      */         case 2:
/*  443 */           dataShadow.block[lastShadow + 2] = ch;
/*  444 */           dataShadow.block[lastShadow + 3] = ch;
/*  445 */           this.last = lastShadow + 2;
/*      */           return;
/*      */         case 3:
/*  449 */           block = dataShadow.block;
/*  450 */           block[lastShadow + 2] = ch;
/*  451 */           block[lastShadow + 3] = ch;
/*  452 */           block[lastShadow + 4] = ch;
/*  453 */           this.last = lastShadow + 3;
/*      */           return;
/*      */       } 
/*  458 */       runLengthShadow -= 4;
/*  459 */       dataShadow.inUse[runLengthShadow] = true;
/*  460 */       byte[] block = dataShadow.block;
/*  461 */       block[lastShadow + 2] = ch;
/*  462 */       block[lastShadow + 3] = ch;
/*  463 */       block[lastShadow + 4] = ch;
/*  464 */       block[lastShadow + 5] = ch;
/*  465 */       block[lastShadow + 6] = (byte)runLengthShadow;
/*  466 */       this.last = lastShadow + 5;
/*      */     } else {
/*  472 */       endBlock();
/*  473 */       initBlock();
/*  474 */       writeRun();
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void finalize() throws Throwable {
/*  483 */     finish();
/*  484 */     super.finalize();
/*      */   }
/*      */   
/*      */   public void finish() throws IOException {
/*  489 */     if (this.out != null)
/*      */       try {
/*  491 */         if (this.runLength > 0)
/*  492 */           writeRun(); 
/*  494 */         this.currentChar = -1;
/*  495 */         endBlock();
/*  496 */         endCompression();
/*      */       } finally {
/*  498 */         this.out = null;
/*  499 */         this.data = null;
/*  500 */         this.blockSorter = null;
/*      */       }  
/*      */   }
/*      */   
/*      */   public void close() throws IOException {
/*  507 */     if (this.out != null) {
/*  508 */       OutputStream outShadow = this.out;
/*  509 */       finish();
/*  510 */       outShadow.close();
/*      */     } 
/*      */   }
/*      */   
/*      */   public void flush() throws IOException {
/*  516 */     OutputStream outShadow = this.out;
/*  517 */     if (outShadow != null)
/*  518 */       outShadow.flush(); 
/*      */   }
/*      */   
/*      */   private void init() throws IOException {
/*  529 */     bsPutUByte(66);
/*  530 */     bsPutUByte(90);
/*  532 */     this.data = new Data(this.blockSize100k);
/*  533 */     this.blockSorter = new BlockSort(this.data);
/*  536 */     bsPutUByte(104);
/*  537 */     bsPutUByte(48 + this.blockSize100k);
/*  539 */     this.combinedCRC = 0;
/*  540 */     initBlock();
/*      */   }
/*      */   
/*      */   private void initBlock() {
/*  545 */     this.crc.initialiseCRC();
/*  546 */     this.last = -1;
/*  549 */     boolean[] inUse = this.data.inUse;
/*  550 */     for (int i = 256; --i >= 0;)
/*  551 */       inUse[i] = false; 
/*      */   }
/*      */   
/*      */   private void endBlock() throws IOException {
/*  557 */     this.blockCRC = this.crc.getFinalCRC();
/*  558 */     this.combinedCRC = this.combinedCRC << 1 | this.combinedCRC >>> 31;
/*  559 */     this.combinedCRC ^= this.blockCRC;
/*  562 */     if (this.last == -1)
/*      */       return; 
/*  567 */     blockSort();
/*  580 */     bsPutUByte(49);
/*  581 */     bsPutUByte(65);
/*  582 */     bsPutUByte(89);
/*  583 */     bsPutUByte(38);
/*  584 */     bsPutUByte(83);
/*  585 */     bsPutUByte(89);
/*  588 */     bsPutInt(this.blockCRC);
/*  591 */     bsW(1, 0);
/*  594 */     moveToFrontCodeAndSend();
/*      */   }
/*      */   
/*      */   private void endCompression() throws IOException {
/*  604 */     bsPutUByte(23);
/*  605 */     bsPutUByte(114);
/*  606 */     bsPutUByte(69);
/*  607 */     bsPutUByte(56);
/*  608 */     bsPutUByte(80);
/*  609 */     bsPutUByte(144);
/*  611 */     bsPutInt(this.combinedCRC);
/*  612 */     bsFinishedWithStream();
/*      */   }
/*      */   
/*      */   public final int getBlockSize() {
/*  619 */     return this.blockSize100k;
/*      */   }
/*      */   
/*      */   public void write(byte[] buf, int offs, int len) throws IOException {
/*  625 */     if (offs < 0)
/*  626 */       throw new IndexOutOfBoundsException("offs(" + offs + ") < 0."); 
/*  628 */     if (len < 0)
/*  629 */       throw new IndexOutOfBoundsException("len(" + len + ") < 0."); 
/*  631 */     if (offs + len > buf.length)
/*  632 */       throw new IndexOutOfBoundsException("offs(" + offs + ") + len(" + len + ") > buf.length(" + buf.length + ")."); 
/*  636 */     if (this.out == null)
/*  637 */       throw new IOException("stream closed"); 
/*  640 */     for (int hi = offs + len; offs < hi;)
/*  641 */       write0(buf[offs++]); 
/*      */   }
/*      */   
/*      */   private void write0(int b) throws IOException {
/*  650 */     if (this.currentChar != -1) {
/*  651 */       b &= 0xFF;
/*  652 */       if (this.currentChar == b) {
/*  653 */         if (++this.runLength > 254) {
/*  654 */           writeRun();
/*  655 */           this.currentChar = -1;
/*  656 */           this.runLength = 0;
/*      */         } 
/*      */       } else {
/*  660 */         writeRun();
/*  661 */         this.runLength = 1;
/*  662 */         this.currentChar = b;
/*      */       } 
/*      */     } else {
/*  665 */       this.currentChar = b & 0xFF;
/*  666 */       this.runLength++;
/*      */     } 
/*      */   }
/*      */   
/*      */   private static void hbAssignCodes(int[] code, byte[] length, int minLen, int maxLen, int alphaSize) {
/*  673 */     int vec = 0;
/*  674 */     for (int n = minLen; n <= maxLen; n++) {
/*  675 */       for (int i = 0; i < alphaSize; i++) {
/*  676 */         if ((length[i] & 0xFF) == n) {
/*  677 */           code[i] = vec;
/*  678 */           vec++;
/*      */         } 
/*      */       } 
/*  681 */       vec <<= 1;
/*      */     } 
/*      */   }
/*      */   
/*      */   private void bsFinishedWithStream() throws IOException {
/*  686 */     while (this.bsLive > 0) {
/*  687 */       int ch = this.bsBuff >> 24;
/*  688 */       this.out.write(ch);
/*  689 */       this.bsBuff <<= 8;
/*  690 */       this.bsLive -= 8;
/*      */     } 
/*      */   }
/*      */   
/*      */   private void bsW(int n, int v) throws IOException {
/*  695 */     OutputStream outShadow = this.out;
/*  696 */     int bsLiveShadow = this.bsLive;
/*  697 */     int bsBuffShadow = this.bsBuff;
/*  699 */     while (bsLiveShadow >= 8) {
/*  700 */       outShadow.write(bsBuffShadow >> 24);
/*  701 */       bsBuffShadow <<= 8;
/*  702 */       bsLiveShadow -= 8;
/*      */     } 
/*  705 */     this.bsBuff = bsBuffShadow | v << 32 - bsLiveShadow - n;
/*  706 */     this.bsLive = bsLiveShadow + n;
/*      */   }
/*      */   
/*      */   private void bsPutUByte(int c) throws IOException {
/*  710 */     bsW(8, c);
/*      */   }
/*      */   
/*      */   private void bsPutInt(int u) throws IOException {
/*  714 */     bsW(8, u >> 24 & 0xFF);
/*  715 */     bsW(8, u >> 16 & 0xFF);
/*  716 */     bsW(8, u >> 8 & 0xFF);
/*  717 */     bsW(8, u & 0xFF);
/*      */   }
/*      */   
/*      */   private void sendMTFValues() throws IOException {
/*  721 */     byte[][] len = this.data.sendMTFValues_len;
/*  722 */     int alphaSize = this.nInUse + 2;
/*  724 */     for (int t = 6; --t >= 0; ) {
/*  725 */       byte[] len_t = len[t];
/*  726 */       for (int v = alphaSize; --v >= 0;)
/*  727 */         len_t[v] = 15; 
/*      */     } 
/*  733 */     int nGroups = (this.nMTF < 200) ? 2 : ((this.nMTF < 600) ? 3 : ((this.nMTF < 1200) ? 4 : ((this.nMTF < 2400) ? 5 : 6)));
/*  737 */     sendMTFValues0(nGroups, alphaSize);
/*  742 */     int nSelectors = sendMTFValues1(nGroups, alphaSize);
/*  745 */     sendMTFValues2(nGroups, nSelectors);
/*  748 */     sendMTFValues3(nGroups, alphaSize);
/*  751 */     sendMTFValues4();
/*  754 */     sendMTFValues5(nGroups, nSelectors);
/*  757 */     sendMTFValues6(nGroups, alphaSize);
/*  760 */     sendMTFValues7();
/*      */   }
/*      */   
/*      */   private void sendMTFValues0(int nGroups, int alphaSize) {
/*  764 */     byte[][] len = this.data.sendMTFValues_len;
/*  765 */     int[] mtfFreq = this.data.mtfFreq;
/*  767 */     int remF = this.nMTF;
/*  768 */     int gs = 0;
/*  770 */     for (int nPart = nGroups; nPart > 0; nPart--) {
/*  771 */       int tFreq = remF / nPart;
/*  772 */       int ge = gs - 1;
/*  773 */       int aFreq = 0;
/*  775 */       for (int a = alphaSize - 1; aFreq < tFreq && ge < a;)
/*  776 */         aFreq += mtfFreq[++ge]; 
/*  779 */       if (ge > gs && nPart != nGroups && nPart != 1 && (nGroups - nPart & 0x1) != 0)
/*  781 */         aFreq -= mtfFreq[ge--]; 
/*  784 */       byte[] len_np = len[nPart - 1];
/*  785 */       for (int v = alphaSize; --v >= 0; ) {
/*  786 */         if (v >= gs && v <= ge) {
/*  787 */           len_np[v] = 0;
/*      */           continue;
/*      */         } 
/*  789 */         len_np[v] = 15;
/*      */       } 
/*  793 */       gs = ge + 1;
/*  794 */       remF -= aFreq;
/*      */     } 
/*      */   }
/*      */   
/*      */   private int sendMTFValues1(int nGroups, int alphaSize) {
/*  799 */     Data dataShadow = this.data;
/*  800 */     int[][] rfreq = dataShadow.sendMTFValues_rfreq;
/*  801 */     int[] fave = dataShadow.sendMTFValues_fave;
/*  802 */     short[] cost = dataShadow.sendMTFValues_cost;
/*  803 */     char[] sfmap = dataShadow.sfmap;
/*  804 */     byte[] selector = dataShadow.selector;
/*  805 */     byte[][] len = dataShadow.sendMTFValues_len;
/*  806 */     byte[] len_0 = len[0];
/*  807 */     byte[] len_1 = len[1];
/*  808 */     byte[] len_2 = len[2];
/*  809 */     byte[] len_3 = len[3];
/*  810 */     byte[] len_4 = len[4];
/*  811 */     byte[] len_5 = len[5];
/*  812 */     int nMTFShadow = this.nMTF;
/*  814 */     int nSelectors = 0;
/*  816 */     for (int iter = 0; iter < 4; iter++) {
/*  817 */       for (int i = nGroups; --i >= 0; ) {
/*  818 */         fave[i] = 0;
/*  819 */         int[] rfreqt = rfreq[i];
/*  820 */         for (int j = alphaSize; --j >= 0;)
/*  821 */           rfreqt[j] = 0; 
/*      */       } 
/*  825 */       nSelectors = 0;
/*      */       int gs;
/*  827 */       for (gs = 0; gs < this.nMTF; ) {
/*  835 */         int ge = Math.min(gs + 50 - 1, nMTFShadow - 1);
/*  837 */         if (nGroups == 6) {
/*  840 */           short cost0 = 0;
/*  841 */           short cost1 = 0;
/*  842 */           short cost2 = 0;
/*  843 */           short cost3 = 0;
/*  844 */           short cost4 = 0;
/*  845 */           short cost5 = 0;
/*  847 */           for (int m = gs; m <= ge; m++) {
/*  848 */             int icv = sfmap[m];
/*  849 */             cost0 = (short)(cost0 + (len_0[icv] & 0xFF));
/*  850 */             cost1 = (short)(cost1 + (len_1[icv] & 0xFF));
/*  851 */             cost2 = (short)(cost2 + (len_2[icv] & 0xFF));
/*  852 */             cost3 = (short)(cost3 + (len_3[icv] & 0xFF));
/*  853 */             cost4 = (short)(cost4 + (len_4[icv] & 0xFF));
/*  854 */             cost5 = (short)(cost5 + (len_5[icv] & 0xFF));
/*      */           } 
/*  857 */           cost[0] = cost0;
/*  858 */           cost[1] = cost1;
/*  859 */           cost[2] = cost2;
/*  860 */           cost[3] = cost3;
/*  861 */           cost[4] = cost4;
/*  862 */           cost[5] = cost5;
/*      */         } else {
/*  865 */           for (int n = nGroups; --n >= 0;)
/*  866 */             cost[n] = 0; 
/*  869 */           for (int m = gs; m <= ge; m++) {
/*  870 */             int icv = sfmap[m];
/*  871 */             for (int i1 = nGroups; --i1 >= 0;)
/*  872 */               cost[i1] = (short)(cost[i1] + (len[i1][icv] & 0xFF)); 
/*      */           } 
/*      */         } 
/*  881 */         int bt = -1;
/*  882 */         for (int j = nGroups, bc = 999999999; --j >= 0; ) {
/*  883 */           int cost_t = cost[j];
/*  884 */           if (cost_t < bc) {
/*  885 */             bc = cost_t;
/*  886 */             bt = j;
/*      */           } 
/*      */         } 
/*  890 */         fave[bt] = fave[bt] + 1;
/*  891 */         selector[nSelectors] = (byte)bt;
/*  892 */         nSelectors++;
/*  897 */         int[] rfreq_bt = rfreq[bt];
/*  898 */         for (int k = gs; k <= ge; k++)
/*  899 */           rfreq_bt[sfmap[k]] = rfreq_bt[sfmap[k]] + 1; 
/*  902 */         gs = ge + 1;
/*      */       } 
/*  908 */       for (int t = 0; t < nGroups; t++)
/*  909 */         hbMakeCodeLengths(len[t], rfreq[t], this.data, alphaSize, 20); 
/*      */     } 
/*  913 */     return nSelectors;
/*      */   }
/*      */   
/*      */   private void sendMTFValues2(int nGroups, int nSelectors) {
/*  919 */     Data dataShadow = this.data;
/*  920 */     byte[] pos = dataShadow.sendMTFValues2_pos;
/*      */     int i;
/*  922 */     for (i = nGroups; --i >= 0;)
/*  923 */       pos[i] = (byte)i; 
/*  926 */     for (i = 0; i < nSelectors; i++) {
/*  927 */       byte ll_i = dataShadow.selector[i];
/*  928 */       byte tmp = pos[0];
/*  929 */       int j = 0;
/*  931 */       while (ll_i != tmp) {
/*  932 */         j++;
/*  933 */         byte tmp2 = tmp;
/*  934 */         tmp = pos[j];
/*  935 */         pos[j] = tmp2;
/*      */       } 
/*  938 */       pos[0] = tmp;
/*  939 */       dataShadow.selectorMtf[i] = (byte)j;
/*      */     } 
/*      */   }
/*      */   
/*      */   private void sendMTFValues3(int nGroups, int alphaSize) {
/*  944 */     int[][] code = this.data.sendMTFValues_code;
/*  945 */     byte[][] len = this.data.sendMTFValues_len;
/*  947 */     for (int t = 0; t < nGroups; t++) {
/*  948 */       int minLen = 32;
/*  949 */       int maxLen = 0;
/*  950 */       byte[] len_t = len[t];
/*  951 */       for (int i = alphaSize; --i >= 0; ) {
/*  952 */         int l = len_t[i] & 0xFF;
/*  953 */         if (l > maxLen)
/*  954 */           maxLen = l; 
/*  956 */         if (l < minLen)
/*  957 */           minLen = l; 
/*      */       } 
/*  964 */       hbAssignCodes(code[t], len[t], minLen, maxLen, alphaSize);
/*      */     } 
/*      */   }
/*      */   
/*      */   private void sendMTFValues4() throws IOException {
/*  969 */     boolean[] inUse = this.data.inUse;
/*  970 */     boolean[] inUse16 = this.data.sentMTFValues4_inUse16;
/*      */     int i;
/*  972 */     for (i = 16; --i >= 0; ) {
/*  973 */       inUse16[i] = false;
/*  974 */       int i16 = i * 16;
/*  975 */       for (int k = 16; --k >= 0;) {
/*  976 */         if (inUse[i16 + k])
/*  977 */           inUse16[i] = true; 
/*      */       } 
/*      */     } 
/*  982 */     for (i = 0; i < 16; i++)
/*  983 */       bsW(1, inUse16[i] ? 1 : 0); 
/*  986 */     OutputStream outShadow = this.out;
/*  987 */     int bsLiveShadow = this.bsLive;
/*  988 */     int bsBuffShadow = this.bsBuff;
/*  990 */     for (int j = 0; j < 16; j++) {
/*  991 */       if (inUse16[j]) {
/*  992 */         int i16 = j * 16;
/*  993 */         for (int k = 0; k < 16; k++) {
/*  995 */           while (bsLiveShadow >= 8) {
/*  996 */             outShadow.write(bsBuffShadow >> 24);
/*  997 */             bsBuffShadow <<= 8;
/*  998 */             bsLiveShadow -= 8;
/*      */           } 
/* 1000 */           if (inUse[i16 + k])
/* 1001 */             bsBuffShadow |= 1 << 32 - bsLiveShadow - 1; 
/* 1003 */           bsLiveShadow++;
/*      */         } 
/*      */       } 
/*      */     } 
/* 1008 */     this.bsBuff = bsBuffShadow;
/* 1009 */     this.bsLive = bsLiveShadow;
/*      */   }
/*      */   
/*      */   private void sendMTFValues5(int nGroups, int nSelectors) throws IOException {
/* 1014 */     bsW(3, nGroups);
/* 1015 */     bsW(15, nSelectors);
/* 1017 */     OutputStream outShadow = this.out;
/* 1018 */     byte[] selectorMtf = this.data.selectorMtf;
/* 1020 */     int bsLiveShadow = this.bsLive;
/* 1021 */     int bsBuffShadow = this.bsBuff;
/* 1023 */     for (int i = 0; i < nSelectors; i++) {
/* 1024 */       for (int j = 0, hj = selectorMtf[i] & 0xFF; j < hj; j++) {
/* 1026 */         while (bsLiveShadow >= 8) {
/* 1027 */           outShadow.write(bsBuffShadow >> 24);
/* 1028 */           bsBuffShadow <<= 8;
/* 1029 */           bsLiveShadow -= 8;
/*      */         } 
/* 1031 */         bsBuffShadow |= 1 << 32 - bsLiveShadow - 1;
/* 1032 */         bsLiveShadow++;
/*      */       } 
/* 1036 */       while (bsLiveShadow >= 8) {
/* 1037 */         outShadow.write(bsBuffShadow >> 24);
/* 1038 */         bsBuffShadow <<= 8;
/* 1039 */         bsLiveShadow -= 8;
/*      */       } 
/* 1042 */       bsLiveShadow++;
/*      */     } 
/* 1045 */     this.bsBuff = bsBuffShadow;
/* 1046 */     this.bsLive = bsLiveShadow;
/*      */   }
/*      */   
/*      */   private void sendMTFValues6(int nGroups, int alphaSize) throws IOException {
/* 1051 */     byte[][] len = this.data.sendMTFValues_len;
/* 1052 */     OutputStream outShadow = this.out;
/* 1054 */     int bsLiveShadow = this.bsLive;
/* 1055 */     int bsBuffShadow = this.bsBuff;
/* 1057 */     for (int t = 0; t < nGroups; t++) {
/* 1058 */       byte[] len_t = len[t];
/* 1059 */       int curr = len_t[0] & 0xFF;
/* 1062 */       while (bsLiveShadow >= 8) {
/* 1063 */         outShadow.write(bsBuffShadow >> 24);
/* 1064 */         bsBuffShadow <<= 8;
/* 1065 */         bsLiveShadow -= 8;
/*      */       } 
/* 1067 */       bsBuffShadow |= curr << 32 - bsLiveShadow - 5;
/* 1068 */       bsLiveShadow += 5;
/* 1070 */       for (int i = 0; i < alphaSize; i++) {
/* 1071 */         int lti = len_t[i] & 0xFF;
/* 1072 */         while (curr < lti) {
/* 1074 */           while (bsLiveShadow >= 8) {
/* 1075 */             outShadow.write(bsBuffShadow >> 24);
/* 1076 */             bsBuffShadow <<= 8;
/* 1077 */             bsLiveShadow -= 8;
/*      */           } 
/* 1079 */           bsBuffShadow |= 2 << 32 - bsLiveShadow - 2;
/* 1080 */           bsLiveShadow += 2;
/* 1082 */           curr++;
/*      */         } 
/* 1085 */         while (curr > lti) {
/* 1087 */           while (bsLiveShadow >= 8) {
/* 1088 */             outShadow.write(bsBuffShadow >> 24);
/* 1089 */             bsBuffShadow <<= 8;
/* 1090 */             bsLiveShadow -= 8;
/*      */           } 
/* 1092 */           bsBuffShadow |= 3 << 32 - bsLiveShadow - 2;
/* 1093 */           bsLiveShadow += 2;
/* 1095 */           curr--;
/*      */         } 
/* 1099 */         while (bsLiveShadow >= 8) {
/* 1100 */           outShadow.write(bsBuffShadow >> 24);
/* 1101 */           bsBuffShadow <<= 8;
/* 1102 */           bsLiveShadow -= 8;
/*      */         } 
/* 1105 */         bsLiveShadow++;
/*      */       } 
/*      */     } 
/* 1109 */     this.bsBuff = bsBuffShadow;
/* 1110 */     this.bsLive = bsLiveShadow;
/*      */   }
/*      */   
/*      */   private void sendMTFValues7() throws IOException {
/* 1114 */     Data dataShadow = this.data;
/* 1115 */     byte[][] len = dataShadow.sendMTFValues_len;
/* 1116 */     int[][] code = dataShadow.sendMTFValues_code;
/* 1117 */     OutputStream outShadow = this.out;
/* 1118 */     byte[] selector = dataShadow.selector;
/* 1119 */     char[] sfmap = dataShadow.sfmap;
/* 1120 */     int nMTFShadow = this.nMTF;
/* 1122 */     int selCtr = 0;
/* 1124 */     int bsLiveShadow = this.bsLive;
/* 1125 */     int bsBuffShadow = this.bsBuff;
/* 1127 */     for (int gs = 0; gs < nMTFShadow; ) {
/* 1128 */       int ge = Math.min(gs + 50 - 1, nMTFShadow - 1);
/* 1129 */       int selector_selCtr = selector[selCtr] & 0xFF;
/* 1130 */       int[] code_selCtr = code[selector_selCtr];
/* 1131 */       byte[] len_selCtr = len[selector_selCtr];
/* 1133 */       while (gs <= ge) {
/* 1134 */         int sfmap_i = sfmap[gs];
/* 1140 */         while (bsLiveShadow >= 8) {
/* 1141 */           outShadow.write(bsBuffShadow >> 24);
/* 1142 */           bsBuffShadow <<= 8;
/* 1143 */           bsLiveShadow -= 8;
/*      */         } 
/* 1145 */         int n = len_selCtr[sfmap_i] & 0xFF;
/* 1146 */         bsBuffShadow |= code_selCtr[sfmap_i] << 32 - bsLiveShadow - n;
/* 1147 */         bsLiveShadow += n;
/* 1149 */         gs++;
/*      */       } 
/* 1152 */       gs = ge + 1;
/* 1153 */       selCtr++;
/*      */     } 
/* 1156 */     this.bsBuff = bsBuffShadow;
/* 1157 */     this.bsLive = bsLiveShadow;
/*      */   }
/*      */   
/*      */   private void moveToFrontCodeAndSend() throws IOException {
/* 1161 */     bsW(24, this.data.origPtr);
/* 1162 */     generateMTFValues();
/* 1163 */     sendMTFValues();
/*      */   }
/*      */   
/*      */   private void blockSort() {
/* 1167 */     this.blockSorter.blockSort(this.data, this.last);
/*      */   }
/*      */   
/*      */   private void generateMTFValues() {
/* 1178 */     int lastShadow = this.last;
/* 1179 */     Data dataShadow = this.data;
/* 1180 */     boolean[] inUse = dataShadow.inUse;
/* 1181 */     byte[] block = dataShadow.block;
/* 1182 */     int[] fmap = dataShadow.fmap;
/* 1183 */     char[] sfmap = dataShadow.sfmap;
/* 1184 */     int[] mtfFreq = dataShadow.mtfFreq;
/* 1185 */     byte[] unseqToSeq = dataShadow.unseqToSeq;
/* 1186 */     byte[] yy = dataShadow.generateMTFValues_yy;
/* 1189 */     int nInUseShadow = 0;
/* 1190 */     for (int i = 0; i < 256; i++) {
/* 1191 */       if (inUse[i]) {
/* 1192 */         unseqToSeq[i] = (byte)nInUseShadow;
/* 1193 */         nInUseShadow++;
/*      */       } 
/*      */     } 
/* 1196 */     this.nInUse = nInUseShadow;
/* 1198 */     int eob = nInUseShadow + 1;
/*      */     int j;
/* 1200 */     for (j = eob; j >= 0; j--)
/* 1201 */       mtfFreq[j] = 0; 
/* 1204 */     for (j = nInUseShadow; --j >= 0;)
/* 1205 */       yy[j] = (byte)j; 
/* 1208 */     int wr = 0;
/* 1209 */     int zPend = 0;
/* 1211 */     for (int k = 0; k <= lastShadow; k++) {
/* 1212 */       byte ll_i = unseqToSeq[block[fmap[k]] & 0xFF];
/* 1213 */       byte tmp = yy[0];
/* 1214 */       int m = 0;
/* 1216 */       while (ll_i != tmp) {
/* 1217 */         m++;
/* 1218 */         byte tmp2 = tmp;
/* 1219 */         tmp = yy[m];
/* 1220 */         yy[m] = tmp2;
/*      */       } 
/* 1222 */       yy[0] = tmp;
/* 1224 */       if (m == 0) {
/* 1225 */         zPend++;
/*      */       } else {
/* 1227 */         if (zPend > 0) {
/* 1228 */           zPend--;
/*      */           while (true) {
/* 1230 */             if ((zPend & 0x1) == 0) {
/* 1231 */               sfmap[wr] = Character.MIN_VALUE;
/* 1232 */               wr++;
/* 1233 */               mtfFreq[0] = mtfFreq[0] + 1;
/*      */             } else {
/* 1235 */               sfmap[wr] = '\001';
/* 1236 */               wr++;
/* 1237 */               mtfFreq[1] = mtfFreq[1] + 1;
/*      */             } 
/* 1240 */             if (zPend >= 2) {
/* 1241 */               zPend = zPend - 2 >> 1;
/*      */               continue;
/*      */             } 
/*      */             break;
/*      */           } 
/* 1246 */           zPend = 0;
/*      */         } 
/* 1248 */         sfmap[wr] = (char)(m + 1);
/* 1249 */         wr++;
/* 1250 */         mtfFreq[m + 1] = mtfFreq[m + 1] + 1;
/*      */       } 
/*      */     } 
/* 1254 */     if (zPend > 0) {
/* 1255 */       zPend--;
/*      */       while (true) {
/* 1257 */         if ((zPend & 0x1) == 0) {
/* 1258 */           sfmap[wr] = Character.MIN_VALUE;
/* 1259 */           wr++;
/* 1260 */           mtfFreq[0] = mtfFreq[0] + 1;
/*      */         } else {
/* 1262 */           sfmap[wr] = '\001';
/* 1263 */           wr++;
/* 1264 */           mtfFreq[1] = mtfFreq[1] + 1;
/*      */         } 
/* 1267 */         if (zPend >= 2) {
/* 1268 */           zPend = zPend - 2 >> 1;
/*      */           continue;
/*      */         } 
/*      */         break;
/*      */       } 
/*      */     } 
/* 1275 */     sfmap[wr] = (char)eob;
/* 1276 */     mtfFreq[eob] = mtfFreq[eob] + 1;
/* 1277 */     this.nMTF = wr + 1;
/*      */   }
/*      */   
/*      */   static final class Data {
/* 1284 */     final boolean[] inUse = new boolean[256];
/*      */     
/* 1285 */     final byte[] unseqToSeq = new byte[256];
/*      */     
/* 1286 */     final int[] mtfFreq = new int[258];
/*      */     
/* 1287 */     final byte[] selector = new byte[18002];
/*      */     
/* 1288 */     final byte[] selectorMtf = new byte[18002];
/*      */     
/* 1290 */     final byte[] generateMTFValues_yy = new byte[256];
/*      */     
/* 1291 */     final byte[][] sendMTFValues_len = new byte[6][258];
/*      */     
/* 1293 */     final int[][] sendMTFValues_rfreq = new int[6][258];
/*      */     
/* 1295 */     final int[] sendMTFValues_fave = new int[6];
/*      */     
/* 1296 */     final short[] sendMTFValues_cost = new short[6];
/*      */     
/* 1297 */     final int[][] sendMTFValues_code = new int[6][258];
/*      */     
/* 1299 */     final byte[] sendMTFValues2_pos = new byte[6];
/*      */     
/* 1300 */     final boolean[] sentMTFValues4_inUse16 = new boolean[16];
/*      */     
/* 1302 */     final int[] heap = new int[260];
/*      */     
/* 1303 */     final int[] weight = new int[516];
/*      */     
/* 1304 */     final int[] parent = new int[516];
/*      */     
/*      */     final byte[] block;
/*      */     
/*      */     final int[] fmap;
/*      */     
/*      */     final char[] sfmap;
/*      */     
/*      */     int origPtr;
/*      */     
/*      */     Data(int blockSize100k) {
/* 1332 */       int n = blockSize100k * 100000;
/* 1333 */       this.block = new byte[n + 1 + 20];
/* 1334 */       this.fmap = new int[n];
/* 1335 */       this.sfmap = new char[2 * n];
/*      */     }
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\compress\compressors\bzip2\BZip2CompressorOutputStream.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */