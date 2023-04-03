/*      */ package com.ctc.wstx.io;
/*      */ 
/*      */ import com.ctc.wstx.api.ReaderConfig;
/*      */ import com.ctc.wstx.exc.WstxEOFException;
/*      */ import com.ctc.wstx.exc.WstxException;
/*      */ import com.ctc.wstx.exc.WstxIOException;
/*      */ import java.io.CharConversionException;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.io.InputStreamReader;
/*      */ import java.io.Reader;
/*      */ import java.io.UnsupportedEncodingException;
/*      */ import javax.xml.stream.Location;
/*      */ import javax.xml.stream.XMLStreamException;
/*      */ 
/*      */ public final class StreamBootstrapper extends InputBootstrapper {
/*      */   static final int MIN_BUF_SIZE = 128;
/*      */   
/*      */   final InputStream mIn;
/*      */   
/*      */   private byte[] mByteBuffer;
/*      */   
/*      */   private final boolean mRecycleBuffer;
/*      */   
/*      */   private int mInputPtr;
/*      */   
/*      */   private int mInputEnd;
/*      */   
/*      */   boolean mBigEndian = true;
/*      */   
/*      */   boolean mHadBOM = false;
/*      */   
/*      */   boolean mByteSizeFound = false;
/*      */   
/*      */   int mBytesPerChar;
/*      */   
/*      */   boolean mEBCDIC = false;
/*      */   
/*   90 */   String mInputEncoding = null;
/*      */   
/*   96 */   int[] mSingleByteTranslation = null;
/*      */   
/*      */   private StreamBootstrapper(String pubId, String sysId, InputStream in) {
/*  106 */     super(pubId, sysId);
/*  107 */     this.mIn = in;
/*  108 */     this.mInputPtr = this.mInputEnd = 0;
/*  109 */     this.mRecycleBuffer = true;
/*      */   }
/*      */   
/*      */   private StreamBootstrapper(String pubId, String sysId, byte[] data, int start, int end) {
/*  118 */     super(pubId, sysId);
/*  119 */     this.mIn = null;
/*  120 */     this.mRecycleBuffer = false;
/*  121 */     this.mByteBuffer = data;
/*  122 */     this.mInputPtr = start;
/*  123 */     this.mInputEnd = end;
/*      */   }
/*      */   
/*      */   public static StreamBootstrapper getInstance(String pubId, String sysId, InputStream in) {
/*  138 */     return new StreamBootstrapper(pubId, sysId, in);
/*      */   }
/*      */   
/*      */   public static StreamBootstrapper getInstance(String pubId, String sysId, byte[] data, int start, int end) {
/*  149 */     return new StreamBootstrapper(pubId, sysId, data, start, end);
/*      */   }
/*      */   
/*      */   public Reader bootstrapInput(ReaderConfig cfg, boolean mainDoc, int xmlVersion) throws IOException, XMLStreamException {
/*      */     BaseReader r;
/*  155 */     String normEnc = null;
/*  158 */     int bufSize = cfg.getInputBufferLength();
/*  159 */     if (bufSize < 128)
/*  160 */       bufSize = 128; 
/*  162 */     if (this.mByteBuffer == null)
/*  163 */       this.mByteBuffer = cfg.allocFullBBuffer(bufSize); 
/*  166 */     resolveStreamEncoding();
/*  168 */     if (hasXmlDecl()) {
/*  170 */       readXmlDecl(mainDoc, xmlVersion);
/*  171 */       if (this.mFoundEncoding != null)
/*  172 */         normEnc = verifyXmlEncoding(this.mFoundEncoding); 
/*      */     } else {
/*  178 */       this.mXml11Handling = (272 == xmlVersion);
/*      */     } 
/*  183 */     if (normEnc == null)
/*  188 */       if (this.mEBCDIC) {
/*  189 */         if (this.mFoundEncoding == null || this.mFoundEncoding.length() == 0)
/*  190 */           reportXmlProblem("Missing encoding declaration: underlying encoding looks like an EBCDIC variant, but no xml encoding declaration found"); 
/*  193 */         normEnc = this.mFoundEncoding;
/*  194 */       } else if (this.mBytesPerChar == 2) {
/*  195 */         normEnc = this.mBigEndian ? "UTF-16BE" : "UTF-16LE";
/*  196 */       } else if (this.mBytesPerChar == 4) {
/*  202 */         normEnc = this.mBigEndian ? "UTF-32BE" : "UTF-32LE";
/*      */       } else {
/*  205 */         normEnc = "UTF-8";
/*      */       }  
/*  209 */     this.mInputEncoding = normEnc;
/*  217 */     if (normEnc == "UTF-8") {
/*  218 */       r = new UTF8Reader(cfg, this.mIn, this.mByteBuffer, this.mInputPtr, this.mInputEnd, this.mRecycleBuffer);
/*  219 */     } else if (normEnc == "ISO-8859-1") {
/*  220 */       r = new ISOLatinReader(cfg, this.mIn, this.mByteBuffer, this.mInputPtr, this.mInputEnd, this.mRecycleBuffer);
/*  221 */     } else if (normEnc == "US-ASCII") {
/*  222 */       r = new AsciiReader(cfg, this.mIn, this.mByteBuffer, this.mInputPtr, this.mInputEnd, this.mRecycleBuffer);
/*  223 */     } else if (normEnc.startsWith("UTF-32")) {
/*  225 */       if (normEnc == "UTF-32")
/*  226 */         this.mInputEncoding = this.mBigEndian ? "UTF-32BE" : "UTF-32LE"; 
/*  228 */       r = new UTF32Reader(cfg, this.mIn, this.mByteBuffer, this.mInputPtr, this.mInputEnd, this.mRecycleBuffer, this.mBigEndian);
/*      */     } else {
/*  233 */       InputStream in = this.mIn;
/*  234 */       if (this.mInputPtr < this.mInputEnd)
/*  235 */         in = new MergedStream(cfg, in, this.mByteBuffer, this.mInputPtr, this.mInputEnd); 
/*  241 */       if (normEnc == "UTF-16")
/*  242 */         this.mInputEncoding = normEnc = this.mBigEndian ? "UTF-16BE" : "UTF-16LE"; 
/*      */       try {
/*  245 */         return new InputStreamReader(in, normEnc);
/*  246 */       } catch (UnsupportedEncodingException usex) {
/*  247 */         throw new WstxIOException("Unsupported encoding: " + usex.getMessage());
/*      */       } 
/*      */     } 
/*  251 */     if (this.mXml11Handling)
/*  252 */       r.setXmlCompliancy(272); 
/*  255 */     return r;
/*      */   }
/*      */   
/*      */   public String getInputEncoding() {
/*  263 */     return this.mInputEncoding;
/*      */   }
/*      */   
/*      */   public int getInputTotal() {
/*  267 */     int total = this.mInputProcessed + this.mInputPtr;
/*  268 */     if (this.mBytesPerChar > 1)
/*  269 */       total /= this.mBytesPerChar; 
/*  271 */     return total;
/*      */   }
/*      */   
/*      */   public int getInputColumn() {
/*  275 */     int col = this.mInputPtr - this.mInputRowStart;
/*  276 */     if (this.mBytesPerChar > 1)
/*  277 */       col /= this.mBytesPerChar; 
/*  279 */     return col;
/*      */   }
/*      */   
/*      */   protected void resolveStreamEncoding() throws IOException, WstxException {
/*  296 */     this.mBytesPerChar = 0;
/*  297 */     this.mBigEndian = true;
/*  302 */     if (ensureLoaded(4)) {
/*  305 */       int msw, quartet = this.mByteBuffer[0] << 24 | (this.mByteBuffer[1] & 0xFF) << 16 | (this.mByteBuffer[2] & 0xFF) << 8 | this.mByteBuffer[3] & 0xFF;
/*  313 */       switch (quartet) {
/*      */         case 65279:
/*  315 */           this.mBigEndian = true;
/*  316 */           this.mInputPtr = this.mBytesPerChar = 4;
/*      */           break;
/*      */         case -131072:
/*  319 */           this.mInputPtr = this.mBytesPerChar = 4;
/*  320 */           this.mBigEndian = false;
/*      */           break;
/*      */         case 65534:
/*  323 */           reportWeirdUCS4("2143");
/*      */           break;
/*      */         case -16842752:
/*  326 */           reportWeirdUCS4("3412");
/*      */           break;
/*      */         default:
/*  331 */           msw = quartet >>> 16;
/*  332 */           if (msw == 65279) {
/*  333 */             this.mInputPtr = this.mBytesPerChar = 2;
/*  334 */             this.mBigEndian = true;
/*      */             break;
/*      */           } 
/*  337 */           if (msw == 65534) {
/*  338 */             this.mInputPtr = this.mBytesPerChar = 2;
/*  339 */             this.mBigEndian = false;
/*      */             break;
/*      */           } 
/*  344 */           if (quartet >>> 8 == 15711167) {
/*  345 */             this.mInputPtr = 3;
/*  346 */             this.mBytesPerChar = 1;
/*  347 */             this.mBigEndian = true;
/*      */             break;
/*      */           } 
/*  356 */           switch (quartet) {
/*      */             case 60:
/*  358 */               this.mBigEndian = true;
/*  359 */               this.mBytesPerChar = 4;
/*      */               break;
/*      */             case 1006632960:
/*  362 */               this.mBytesPerChar = 4;
/*  363 */               this.mBigEndian = false;
/*      */               break;
/*      */             case 15360:
/*  366 */               reportWeirdUCS4("2143");
/*      */               break;
/*      */             case 3932160:
/*  369 */               reportWeirdUCS4("3412");
/*      */               break;
/*      */             case 3932223:
/*  372 */               this.mBytesPerChar = 2;
/*  373 */               this.mBigEndian = true;
/*      */               break;
/*      */             case 1006649088:
/*  376 */               this.mBytesPerChar = 2;
/*  377 */               this.mBigEndian = false;
/*      */               break;
/*      */             case 1010792557:
/*  380 */               this.mBytesPerChar = 1;
/*  381 */               this.mBigEndian = true;
/*      */               break;
/*      */             case 1282385812:
/*  385 */               this.mBytesPerChar = -1;
/*  386 */               this.mEBCDIC = true;
/*  393 */               this.mSingleByteTranslation = EBCDICCodec.getCp037Mapping();
/*      */               break;
/*      */           } 
/*      */           break;
/*      */       } 
/*  402 */       this.mHadBOM = (this.mInputPtr > 0);
/*  405 */       this.mInputProcessed = -this.mInputPtr;
/*  406 */       this.mInputRowStart = this.mInputPtr;
/*      */     } 
/*  412 */     this.mByteSizeFound = (this.mBytesPerChar != 0);
/*  413 */     if (!this.mByteSizeFound) {
/*  414 */       this.mBytesPerChar = 1;
/*  415 */       this.mBigEndian = true;
/*      */     } 
/*      */   }
/*      */   
/*      */   protected String verifyXmlEncoding(String enc) throws WstxException {
/*  425 */     enc = CharsetNames.normalize(enc);
/*  428 */     if (enc == "UTF-8") {
/*  429 */       verifyEncoding(enc, 1);
/*  430 */     } else if (enc == "ISO-8859-1") {
/*  431 */       verifyEncoding(enc, 1);
/*  432 */     } else if (enc == "US-ASCII") {
/*  433 */       verifyEncoding(enc, 1);
/*  434 */     } else if (enc == "UTF-16") {
/*  444 */       verifyEncoding(enc, 2);
/*  445 */     } else if (enc == "UTF-16LE") {
/*  446 */       verifyEncoding(enc, 2, false);
/*  447 */     } else if (enc == "UTF-16BE") {
/*  448 */       verifyEncoding(enc, 2, true);
/*  450 */     } else if (enc == "UTF-32") {
/*  455 */       verifyEncoding(enc, 4);
/*  456 */     } else if (enc == "UTF-32LE") {
/*  457 */       verifyEncoding(enc, 4, false);
/*  458 */     } else if (enc == "UTF-32BE") {
/*  459 */       verifyEncoding(enc, 4, true);
/*      */     } 
/*  461 */     return enc;
/*      */   }
/*      */   
/*      */   protected boolean ensureLoaded(int minimum) throws IOException {
/*  476 */     int gotten = this.mInputEnd - this.mInputPtr;
/*  477 */     while (gotten < minimum) {
/*  478 */       int count = (this.mIn == null) ? -1 : this.mIn.read(this.mByteBuffer, this.mInputEnd, this.mByteBuffer.length - this.mInputEnd);
/*  479 */       if (count < 1)
/*  480 */         return false; 
/*  482 */       this.mInputEnd += count;
/*  483 */       gotten += count;
/*      */     } 
/*  485 */     return true;
/*      */   }
/*      */   
/*      */   protected void loadMore() throws IOException, WstxException {
/*  498 */     this.mInputProcessed += this.mInputEnd;
/*  499 */     this.mInputRowStart -= this.mInputEnd;
/*  501 */     this.mInputPtr = 0;
/*  502 */     this.mInputEnd = (this.mIn == null) ? -1 : this.mIn.read(this.mByteBuffer, 0, this.mByteBuffer.length);
/*  503 */     if (this.mInputEnd < 1)
/*  504 */       throw new WstxEOFException(" in xml declaration", getLocation()); 
/*      */   }
/*      */   
/*      */   protected void pushback() {
/*  516 */     if (this.mBytesPerChar < 0) {
/*  517 */       this.mInputPtr += this.mBytesPerChar;
/*      */     } else {
/*  519 */       this.mInputPtr -= this.mBytesPerChar;
/*      */     } 
/*      */   }
/*      */   
/*      */   protected int getNext() throws IOException, WstxException {
/*  526 */     if (this.mBytesPerChar != 1) {
/*  527 */       if (this.mBytesPerChar == -1)
/*  528 */         return nextTranslated(); 
/*  530 */       return nextMultiByte();
/*      */     } 
/*  532 */     byte b = (this.mInputPtr < this.mInputEnd) ? this.mByteBuffer[this.mInputPtr++] : nextByte();
/*  534 */     return b & 0xFF;
/*      */   }
/*      */   
/*      */   protected int getNextAfterWs(boolean reqWs) throws IOException, WstxException {
/*      */     int count;
/*  543 */     if (this.mBytesPerChar == 1) {
/*  544 */       count = skipSbWs();
/*  546 */     } else if (this.mBytesPerChar == -1) {
/*  547 */       count = skipTranslatedWs();
/*      */     } else {
/*  549 */       count = skipMbWs();
/*      */     } 
/*  553 */     if (reqWs && count == 0)
/*  554 */       reportUnexpectedChar(getNext(), "; expected a white space"); 
/*  558 */     if (this.mBytesPerChar != 1) {
/*  559 */       if (this.mBytesPerChar == -1)
/*  560 */         return nextTranslated(); 
/*  562 */       return nextMultiByte();
/*      */     } 
/*  564 */     byte b = (this.mInputPtr < this.mInputEnd) ? this.mByteBuffer[this.mInputPtr++] : nextByte();
/*  566 */     return b & 0xFF;
/*      */   }
/*      */   
/*      */   protected int checkKeyword(String exp) throws IOException, WstxException {
/*  576 */     if (this.mBytesPerChar != 1) {
/*  577 */       if (this.mBytesPerChar == -1)
/*  578 */         return checkTranslatedKeyword(exp); 
/*  580 */       return checkMbKeyword(exp);
/*      */     } 
/*  582 */     return checkSbKeyword(exp);
/*      */   }
/*      */   
/*      */   protected int readQuotedValue(char[] kw, int quoteChar) throws IOException, WstxException {
/*  588 */     int i = 0;
/*  589 */     int len = kw.length;
/*  590 */     boolean simple = (this.mBytesPerChar == 1);
/*  591 */     boolean mb = (!simple && this.mBytesPerChar > 1);
/*  593 */     while (i < len) {
/*      */       int c;
/*  596 */       if (simple) {
/*  597 */         byte b = (this.mInputPtr < this.mInputEnd) ? this.mByteBuffer[this.mInputPtr++] : nextByte();
/*  599 */         if (b == 0)
/*  600 */           reportNull(); 
/*  602 */         if (b == 13 || b == 10) {
/*  603 */           skipSbLF(b);
/*  604 */           b = 10;
/*      */         } 
/*  606 */         c = b & 0xFF;
/*  608 */       } else if (mb) {
/*  609 */         c = nextMultiByte();
/*  610 */         if (c == 13 || c == 10) {
/*  611 */           skipMbLF(c);
/*  612 */           c = 10;
/*      */         } 
/*      */       } else {
/*  615 */         c = nextTranslated();
/*  616 */         if (c == 13 || c == 10) {
/*  617 */           skipTranslatedLF(c);
/*  618 */           c = 10;
/*      */         } 
/*      */       } 
/*  623 */       if (c == quoteChar)
/*  624 */         return (i < len) ? i : -1; 
/*  627 */       if (i < len)
/*  628 */         kw[i++] = (char)c; 
/*      */     } 
/*  635 */     return -1;
/*      */   }
/*      */   
/*      */   protected boolean hasXmlDecl() throws IOException, WstxException {
/*  644 */     if (this.mBytesPerChar == 1) {
/*  648 */       if (ensureLoaded(6) && 
/*  649 */         this.mByteBuffer[this.mInputPtr] == 60 && this.mByteBuffer[this.mInputPtr + 1] == 63 && this.mByteBuffer[this.mInputPtr + 2] == 120 && this.mByteBuffer[this.mInputPtr + 3] == 109 && this.mByteBuffer[this.mInputPtr + 4] == 108 && (this.mByteBuffer[this.mInputPtr + 5] & 0xFF) <= 32) {
/*  657 */         this.mInputPtr += 6;
/*  658 */         return true;
/*      */       } 
/*  661 */     } else if (this.mBytesPerChar == -1) {
/*  662 */       if (ensureLoaded(6)) {
/*  663 */         int start = this.mInputPtr;
/*  664 */         if (nextTranslated() == 60 && nextTranslated() == 63 && nextTranslated() == 120 && nextTranslated() == 109 && nextTranslated() == 108 && nextTranslated() <= 32)
/*  670 */           return true; 
/*  672 */         this.mInputPtr = start;
/*      */       } 
/*  678 */     } else if (ensureLoaded(6 * this.mBytesPerChar)) {
/*  679 */       int start = this.mInputPtr;
/*  680 */       if (nextMultiByte() == 60 && nextMultiByte() == 63 && nextMultiByte() == 120 && nextMultiByte() == 109 && nextMultiByte() == 108 && nextMultiByte() <= 32)
/*  686 */         return true; 
/*  688 */       this.mInputPtr = start;
/*      */     } 
/*  692 */     return false;
/*      */   }
/*      */   
/*      */   protected Location getLocation() {
/*  702 */     int total = this.mInputProcessed + this.mInputPtr;
/*  703 */     int col = this.mInputPtr - this.mInputRowStart;
/*  705 */     if (this.mBytesPerChar > 1) {
/*  706 */       total /= this.mBytesPerChar;
/*  707 */       col /= this.mBytesPerChar;
/*      */     } 
/*  710 */     return (Location)new WstxInputLocation(null, this.mPublicId, this.mSystemId, total - 1, this.mInputRow, col);
/*      */   }
/*      */   
/*      */   protected byte nextByte() throws IOException, WstxException {
/*  724 */     if (this.mInputPtr >= this.mInputEnd)
/*  725 */       loadMore(); 
/*  727 */     return this.mByteBuffer[this.mInputPtr++];
/*      */   }
/*      */   
/*      */   protected int skipSbWs() throws IOException, WstxException {
/*  733 */     int count = 0;
/*      */     while (true) {
/*  736 */       byte b = (this.mInputPtr < this.mInputEnd) ? this.mByteBuffer[this.mInputPtr++] : nextByte();
/*  739 */       if ((b & 0xFF) > 32) {
/*  740 */         this.mInputPtr--;
/*      */         break;
/*      */       } 
/*  743 */       if (b == 13 || b == 10) {
/*  744 */         skipSbLF(b);
/*  745 */       } else if (b == 0) {
/*  746 */         reportNull();
/*      */       } 
/*  748 */       count++;
/*      */     } 
/*  750 */     return count;
/*      */   }
/*      */   
/*      */   protected void skipSbLF(byte lfByte) throws IOException, WstxException {
/*  756 */     if (lfByte == 13) {
/*  757 */       byte b = (this.mInputPtr < this.mInputEnd) ? this.mByteBuffer[this.mInputPtr++] : nextByte();
/*  759 */       if (b != 10)
/*  760 */         this.mInputPtr--; 
/*      */     } 
/*  763 */     this.mInputRow++;
/*  764 */     this.mInputRowStart = this.mInputPtr;
/*      */   }
/*      */   
/*      */   protected int checkSbKeyword(String expected) throws IOException, WstxException {
/*  774 */     int len = expected.length();
/*  776 */     for (int ptr = 1; ptr < len; ptr++) {
/*  777 */       byte b = (this.mInputPtr < this.mInputEnd) ? this.mByteBuffer[this.mInputPtr++] : nextByte();
/*  780 */       if (b == 0)
/*  781 */         reportNull(); 
/*  783 */       if ((b & 0xFF) != expected.charAt(ptr))
/*  784 */         return b & 0xFF; 
/*      */     } 
/*  788 */     return 0;
/*      */   }
/*      */   
/*      */   protected int nextMultiByte() throws IOException, WstxException {
/*      */     int c;
/*  800 */     byte b = (this.mInputPtr < this.mInputEnd) ? this.mByteBuffer[this.mInputPtr++] : nextByte();
/*  802 */     byte b2 = (this.mInputPtr < this.mInputEnd) ? this.mByteBuffer[this.mInputPtr++] : nextByte();
/*  806 */     if (this.mBytesPerChar == 2) {
/*  807 */       if (this.mBigEndian) {
/*  808 */         c = (b & 0xFF) << 8 | b2 & 0xFF;
/*      */       } else {
/*  810 */         c = b & 0xFF | (b2 & 0xFF) << 8;
/*      */       } 
/*      */     } else {
/*  814 */       byte b3 = (this.mInputPtr < this.mInputEnd) ? this.mByteBuffer[this.mInputPtr++] : nextByte();
/*  816 */       byte b4 = (this.mInputPtr < this.mInputEnd) ? this.mByteBuffer[this.mInputPtr++] : nextByte();
/*  819 */       if (this.mBigEndian) {
/*  820 */         c = b << 24 | (b2 & 0xFF) << 16 | (b3 & 0xFF) << 8 | b4 & 0xFF;
/*      */       } else {
/*  823 */         c = b4 << 24 | (b3 & 0xFF) << 16 | (b2 & 0xFF) << 8 | b & 0xFF;
/*      */       } 
/*      */     } 
/*  829 */     if (c == 0)
/*  830 */       reportNull(); 
/*  832 */     return c;
/*      */   }
/*      */   
/*      */   protected int nextTranslated() throws IOException, WstxException {
/*  838 */     byte b = (this.mInputPtr < this.mInputEnd) ? this.mByteBuffer[this.mInputPtr++] : nextByte();
/*  840 */     int ch = this.mSingleByteTranslation[b & 0xFF];
/*  841 */     if (ch < 0)
/*  842 */       ch = -ch; 
/*  844 */     return ch;
/*      */   }
/*      */   
/*      */   protected int skipMbWs() throws IOException, WstxException {
/*  850 */     int count = 0;
/*      */     while (true) {
/*  853 */       int c = nextMultiByte();
/*  855 */       if (c > 32) {
/*  856 */         this.mInputPtr -= this.mBytesPerChar;
/*      */         break;
/*      */       } 
/*  859 */       if (c == 13 || c == 10) {
/*  860 */         skipMbLF(c);
/*  861 */       } else if (c == 0) {
/*  862 */         reportNull();
/*      */       } 
/*  864 */       count++;
/*      */     } 
/*  866 */     return count;
/*      */   }
/*      */   
/*      */   protected int skipTranslatedWs() throws IOException, WstxException {
/*  872 */     int count = 0;
/*      */     while (true) {
/*  875 */       int c = nextTranslated();
/*  878 */       if (c > 32 && c != 133) {
/*  879 */         this.mInputPtr--;
/*      */         break;
/*      */       } 
/*  882 */       if (c == 13 || c == 10) {
/*  883 */         skipTranslatedLF(c);
/*  884 */       } else if (c == 0) {
/*  885 */         reportNull();
/*      */       } 
/*  887 */       count++;
/*      */     } 
/*  889 */     return count;
/*      */   }
/*      */   
/*      */   protected void skipMbLF(int lf) throws IOException, WstxException {
/*  895 */     if (lf == 13) {
/*  896 */       int c = nextMultiByte();
/*  897 */       if (c != 10)
/*  898 */         this.mInputPtr -= this.mBytesPerChar; 
/*      */     } 
/*  901 */     this.mInputRow++;
/*  902 */     this.mInputRowStart = this.mInputPtr;
/*      */   }
/*      */   
/*      */   protected void skipTranslatedLF(int lf) throws IOException, WstxException {
/*  908 */     if (lf == 13) {
/*  909 */       int c = nextTranslated();
/*  910 */       if (c != 10)
/*  911 */         this.mInputPtr--; 
/*      */     } 
/*  914 */     this.mInputRow++;
/*  915 */     this.mInputRowStart = this.mInputPtr;
/*      */   }
/*      */   
/*      */   protected int checkMbKeyword(String expected) throws IOException, WstxException {
/*  925 */     int len = expected.length();
/*  927 */     for (int ptr = 1; ptr < len; ptr++) {
/*  928 */       int c = nextMultiByte();
/*  929 */       if (c == 0)
/*  930 */         reportNull(); 
/*  932 */       if (c != expected.charAt(ptr))
/*  933 */         return c; 
/*      */     } 
/*  937 */     return 0;
/*      */   }
/*      */   
/*      */   protected int checkTranslatedKeyword(String expected) throws IOException, WstxException {
/*  943 */     int len = expected.length();
/*  945 */     for (int ptr = 1; ptr < len; ptr++) {
/*  946 */       int c = nextTranslated();
/*  947 */       if (c == 0)
/*  948 */         reportNull(); 
/*  950 */       if (c != expected.charAt(ptr))
/*  951 */         return c; 
/*      */     } 
/*  955 */     return 0;
/*      */   }
/*      */   
/*      */   private void verifyEncoding(String id, int bpc) throws WstxException {
/*  967 */     if (this.mByteSizeFound)
/*  971 */       if (bpc != this.mBytesPerChar) {
/*  973 */         if (this.mEBCDIC)
/*  974 */           reportXmlProblem("Declared encoding '" + id + "' incompatible with auto-detected physical encoding (EBCDIC variant), can not decode input since actual code page not known"); 
/*  976 */         reportXmlProblem("Declared encoding '" + id + "' uses " + bpc + " bytes per character; but physical encoding appeared to use " + this.mBytesPerChar + "; cannot decode");
/*      */       }  
/*      */   }
/*      */   
/*      */   private void verifyEncoding(String id, int bpc, boolean bigEndian) throws WstxException {
/*  985 */     if (this.mByteSizeFound) {
/*  986 */       verifyEncoding(id, bpc);
/*  988 */       if (bigEndian != this.mBigEndian) {
/*  989 */         String bigStr = bigEndian ? "big" : "little";
/*  990 */         reportXmlProblem("Declared encoding '" + id + "' has different endianness (" + bigStr + " endian) than what physical ordering appeared to be; cannot decode");
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void reportWeirdUCS4(String type) throws IOException {
/* 1000 */     throw new CharConversionException("Unsupported UCS-4 endianness (" + type + ") detected");
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\io\StreamBootstrapper.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */