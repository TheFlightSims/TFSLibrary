/*      */ package com.ctc.wstx.sw;
/*      */ 
/*      */ import com.ctc.wstx.api.WriterConfig;
/*      */ import com.ctc.wstx.io.CharsetNames;
/*      */ import com.ctc.wstx.io.CompletelyCloseable;
/*      */ import java.io.IOException;
/*      */ import java.io.OutputStream;
/*      */ import java.io.Writer;
/*      */ import javax.xml.stream.XMLStreamConstants;
/*      */ import javax.xml.stream.XMLStreamException;
/*      */ import org.codehaus.stax2.ri.typed.AsciiValueEncoder;
/*      */ import org.codehaus.stax2.validation.XMLValidator;
/*      */ 
/*      */ public final class BufferingXmlWriter extends XmlWriter implements XMLStreamConstants {
/*      */   static final int DEFAULT_BUFFER_SIZE = 1000;
/*      */   
/*      */   static final int DEFAULT_SMALL_SIZE = 256;
/*      */   
/*      */   protected static final int HIGHEST_ENCODABLE_ATTR_CHAR = 60;
/*      */   
/*      */   protected static final int HIGHEST_ENCODABLE_TEXT_CHAR = 62;
/*      */   
/*      */   protected final Writer mOut;
/*      */   
/*      */   protected char[] mOutputBuffer;
/*      */   
/*      */   protected final int mSmallWriteSize;
/*      */   
/*      */   protected int mOutputPtr;
/*      */   
/*      */   protected int mOutputBufLen;
/*      */   
/*      */   protected final OutputStream mUnderlyingStream;
/*      */   
/*      */   private final int mEncHighChar;
/*      */   
/*      */   final char mEncQuoteChar;
/*      */   
/*      */   final String mEncQuoteEntity;
/*      */   
/*      */   public BufferingXmlWriter(Writer out, WriterConfig cfg, String enc, boolean autoclose, OutputStream outs, int bitsize) throws IOException {
/*  147 */     super(cfg, enc, autoclose);
/*  148 */     this.mOut = out;
/*  149 */     this.mOutputBuffer = cfg.allocFullCBuffer(1000);
/*  150 */     this.mOutputBufLen = this.mOutputBuffer.length;
/*  151 */     this.mSmallWriteSize = 256;
/*  152 */     this.mOutputPtr = 0;
/*  154 */     this.mUnderlyingStream = outs;
/*  157 */     this.mEncQuoteChar = '"';
/*  158 */     this.mEncQuoteEntity = "&quot;";
/*  163 */     if (bitsize < 1)
/*  164 */       bitsize = guessEncodingBitSize(enc); 
/*  166 */     this.mEncHighChar = (bitsize < 16) ? (1 << bitsize) : 65534;
/*      */   }
/*      */   
/*      */   protected int getOutputPtr() {
/*  170 */     return this.mOutputPtr;
/*      */   }
/*      */   
/*      */   protected final OutputStream getOutputStream() {
/*  181 */     return this.mUnderlyingStream;
/*      */   }
/*      */   
/*      */   protected final Writer getWriter() {
/*  186 */     return this.mOut;
/*      */   }
/*      */   
/*      */   public void close(boolean forceRealClose) throws IOException {
/*  198 */     flush();
/*  199 */     this.mTextWriter = null;
/*  200 */     this.mAttrValueWriter = null;
/*  203 */     char[] buf = this.mOutputBuffer;
/*  204 */     if (buf != null) {
/*  205 */       this.mOutputBuffer = null;
/*  206 */       this.mConfig.freeFullCBuffer(buf);
/*      */     } 
/*  209 */     if (forceRealClose || this.mAutoCloseOutput)
/*  213 */       if (this.mOut instanceof CompletelyCloseable) {
/*  214 */         ((CompletelyCloseable)this.mOut).closeCompletely();
/*      */       } else {
/*  216 */         this.mOut.close();
/*      */       }  
/*      */   }
/*      */   
/*      */   public final void flush() throws IOException {
/*  224 */     flushBuffer();
/*  225 */     this.mOut.flush();
/*      */   }
/*      */   
/*      */   public void writeRaw(char[] cbuf, int offset, int len) throws IOException {
/*  231 */     if (this.mOut == null)
/*      */       return; 
/*  236 */     if (len < this.mSmallWriteSize) {
/*  238 */       if (this.mOutputPtr + len > this.mOutputBufLen)
/*  239 */         flushBuffer(); 
/*  241 */       System.arraycopy(cbuf, offset, this.mOutputBuffer, this.mOutputPtr, len);
/*  242 */       this.mOutputPtr += len;
/*      */       return;
/*      */     } 
/*  247 */     int ptr = this.mOutputPtr;
/*  248 */     if (ptr > 0) {
/*  250 */       if (ptr < this.mSmallWriteSize) {
/*  257 */         int needed = this.mSmallWriteSize - ptr;
/*  260 */         System.arraycopy(cbuf, offset, this.mOutputBuffer, ptr, needed);
/*  261 */         this.mOutputPtr = ptr + needed;
/*  262 */         len -= needed;
/*  263 */         offset += needed;
/*      */       } 
/*  265 */       flushBuffer();
/*      */     } 
/*  269 */     this.mOut.write(cbuf, offset, len);
/*      */   }
/*      */   
/*      */   public final void writeRawAscii(char[] cbuf, int offset, int len) throws IOException {
/*  281 */     writeRaw(cbuf, offset, len);
/*      */   }
/*      */   
/*      */   public void writeRaw(String str) throws IOException {
/*  287 */     if (this.mOut == null)
/*      */       return; 
/*  290 */     int len = str.length();
/*  293 */     if (len < this.mSmallWriteSize) {
/*  295 */       if (this.mOutputPtr + len >= this.mOutputBufLen)
/*  296 */         flushBuffer(); 
/*  298 */       str.getChars(0, len, this.mOutputBuffer, this.mOutputPtr);
/*  299 */       this.mOutputPtr += len;
/*      */       return;
/*      */     } 
/*  303 */     writeRaw(str, 0, len);
/*      */   }
/*      */   
/*      */   public void writeRaw(String str, int offset, int len) throws IOException {
/*  309 */     if (this.mOut == null)
/*      */       return; 
/*  314 */     if (len < this.mSmallWriteSize) {
/*  316 */       if (this.mOutputPtr + len >= this.mOutputBufLen)
/*  317 */         flushBuffer(); 
/*  319 */       str.getChars(offset, offset + len, this.mOutputBuffer, this.mOutputPtr);
/*  320 */       this.mOutputPtr += len;
/*      */       return;
/*      */     } 
/*  325 */     int ptr = this.mOutputPtr;
/*  326 */     if (ptr > 0) {
/*  328 */       if (ptr < this.mSmallWriteSize) {
/*  335 */         int needed = this.mSmallWriteSize - ptr;
/*  338 */         str.getChars(offset, offset + needed, this.mOutputBuffer, ptr);
/*  339 */         this.mOutputPtr = ptr + needed;
/*  340 */         len -= needed;
/*  341 */         offset += needed;
/*      */       } 
/*  343 */       flushBuffer();
/*      */     } 
/*  347 */     this.mOut.write(str, offset, len);
/*      */   }
/*      */   
/*      */   public final void writeCDataStart() throws IOException {
/*  359 */     fastWriteRaw("<![CDATA[");
/*      */   }
/*      */   
/*      */   public final void writeCDataEnd() throws IOException {
/*  365 */     fastWriteRaw("]]>");
/*      */   }
/*      */   
/*      */   public final void writeCommentStart() throws IOException {
/*  371 */     fastWriteRaw("<!--");
/*      */   }
/*      */   
/*      */   public final void writeCommentEnd() throws IOException {
/*  377 */     fastWriteRaw("-->");
/*      */   }
/*      */   
/*      */   public final void writePIStart(String target, boolean addSpace) throws IOException {
/*  383 */     fastWriteRaw('<', '?');
/*  384 */     fastWriteRaw(target);
/*  385 */     if (addSpace)
/*  386 */       fastWriteRaw(' '); 
/*      */   }
/*      */   
/*      */   public final void writePIEnd() throws IOException {
/*  393 */     fastWriteRaw('?', '>');
/*      */   }
/*      */   
/*      */   public int writeCData(String data) throws IOException {
/*  405 */     if (this.mCheckContent) {
/*  406 */       int ix = verifyCDataContent(data);
/*  407 */       if (ix >= 0) {
/*  408 */         if (!this.mFixContent)
/*  409 */           return ix; 
/*  412 */         writeSegmentedCData(data, ix);
/*  413 */         return -1;
/*      */       } 
/*      */     } 
/*  416 */     fastWriteRaw("<![CDATA[");
/*  417 */     writeRaw(data, 0, data.length());
/*  418 */     fastWriteRaw("]]>");
/*  419 */     return -1;
/*      */   }
/*      */   
/*      */   public int writeCData(char[] cbuf, int offset, int len) throws IOException {
/*  425 */     if (this.mCheckContent) {
/*  426 */       int ix = verifyCDataContent(cbuf, offset, len);
/*  427 */       if (ix >= 0) {
/*  428 */         if (!this.mFixContent)
/*  429 */           return ix; 
/*  432 */         writeSegmentedCData(cbuf, offset, len, ix);
/*  433 */         return -1;
/*      */       } 
/*      */     } 
/*  436 */     fastWriteRaw("<![CDATA[");
/*  437 */     writeRaw(cbuf, offset, len);
/*  438 */     fastWriteRaw("]]>");
/*  439 */     return -1;
/*      */   }
/*      */   
/*      */   public void writeCharacters(String text) throws IOException {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: getfield mOut : Ljava/io/Writer;
/*      */     //   4: ifnonnull -> 8
/*      */     //   7: return
/*      */     //   8: aload_0
/*      */     //   9: getfield mTextWriter : Ljava/io/Writer;
/*      */     //   12: ifnull -> 24
/*      */     //   15: aload_0
/*      */     //   16: getfield mTextWriter : Ljava/io/Writer;
/*      */     //   19: aload_1
/*      */     //   20: invokevirtual write : (Ljava/lang/String;)V
/*      */     //   23: return
/*      */     //   24: iconst_0
/*      */     //   25: istore_2
/*      */     //   26: aload_1
/*      */     //   27: invokevirtual length : ()I
/*      */     //   30: istore_3
/*      */     //   31: aload_0
/*      */     //   32: getfield mEncHighChar : I
/*      */     //   35: istore #4
/*      */     //   37: aconst_null
/*      */     //   38: astore #5
/*      */     //   40: iload_2
/*      */     //   41: iload_3
/*      */     //   42: if_icmplt -> 48
/*      */     //   45: goto -> 266
/*      */     //   48: aload_1
/*      */     //   49: iload_2
/*      */     //   50: iinc #2, 1
/*      */     //   53: invokevirtual charAt : (I)C
/*      */     //   56: istore #6
/*      */     //   58: iload #6
/*      */     //   60: bipush #62
/*      */     //   62: if_icmpgt -> 192
/*      */     //   65: iload #6
/*      */     //   67: bipush #32
/*      */     //   69: if_icmpgt -> 133
/*      */     //   72: iload #6
/*      */     //   74: bipush #32
/*      */     //   76: if_icmpeq -> 202
/*      */     //   79: iload #6
/*      */     //   81: bipush #10
/*      */     //   83: if_icmpeq -> 202
/*      */     //   86: iload #6
/*      */     //   88: bipush #9
/*      */     //   90: if_icmpeq -> 202
/*      */     //   93: iload #6
/*      */     //   95: bipush #13
/*      */     //   97: if_icmpne -> 110
/*      */     //   100: aload_0
/*      */     //   101: getfield mEscapeCR : Z
/*      */     //   104: ifeq -> 202
/*      */     //   107: goto -> 238
/*      */     //   110: aload_0
/*      */     //   111: getfield mXml11 : Z
/*      */     //   114: ifeq -> 122
/*      */     //   117: iload #6
/*      */     //   119: ifne -> 238
/*      */     //   122: aload_0
/*      */     //   123: iload #6
/*      */     //   125: invokevirtual handleInvalidChar : (I)C
/*      */     //   128: istore #6
/*      */     //   130: goto -> 202
/*      */     //   133: iload #6
/*      */     //   135: bipush #60
/*      */     //   137: if_icmpne -> 147
/*      */     //   140: ldc '&lt;'
/*      */     //   142: astore #5
/*      */     //   144: goto -> 238
/*      */     //   147: iload #6
/*      */     //   149: bipush #38
/*      */     //   151: if_icmpne -> 161
/*      */     //   154: ldc '&amp;'
/*      */     //   156: astore #5
/*      */     //   158: goto -> 238
/*      */     //   161: iload #6
/*      */     //   163: bipush #62
/*      */     //   165: if_icmpne -> 202
/*      */     //   168: iload_2
/*      */     //   169: iconst_2
/*      */     //   170: if_icmplt -> 185
/*      */     //   173: aload_1
/*      */     //   174: iload_2
/*      */     //   175: iconst_2
/*      */     //   176: isub
/*      */     //   177: invokevirtual charAt : (I)C
/*      */     //   180: bipush #93
/*      */     //   182: if_icmpne -> 202
/*      */     //   185: ldc '&gt;'
/*      */     //   187: astore #5
/*      */     //   189: goto -> 238
/*      */     //   192: iload #6
/*      */     //   194: iload #4
/*      */     //   196: if_icmplt -> 202
/*      */     //   199: goto -> 238
/*      */     //   202: aload_0
/*      */     //   203: getfield mOutputPtr : I
/*      */     //   206: aload_0
/*      */     //   207: getfield mOutputBufLen : I
/*      */     //   210: if_icmplt -> 217
/*      */     //   213: aload_0
/*      */     //   214: invokespecial flushBuffer : ()V
/*      */     //   217: aload_0
/*      */     //   218: getfield mOutputBuffer : [C
/*      */     //   221: aload_0
/*      */     //   222: dup
/*      */     //   223: getfield mOutputPtr : I
/*      */     //   226: dup_x1
/*      */     //   227: iconst_1
/*      */     //   228: iadd
/*      */     //   229: putfield mOutputPtr : I
/*      */     //   232: iload #6
/*      */     //   234: castore
/*      */     //   235: goto -> 40
/*      */     //   238: aload #5
/*      */     //   240: ifnull -> 252
/*      */     //   243: aload_0
/*      */     //   244: aload #5
/*      */     //   246: invokevirtual writeRaw : (Ljava/lang/String;)V
/*      */     //   249: goto -> 263
/*      */     //   252: aload_0
/*      */     //   253: aload_1
/*      */     //   254: iload_2
/*      */     //   255: iconst_1
/*      */     //   256: isub
/*      */     //   257: invokevirtual charAt : (I)C
/*      */     //   260: invokevirtual writeAsEntity : (I)V
/*      */     //   263: goto -> 37
/*      */     //   266: return
/*      */     // Line number table:
/*      */     //   Java source line number -> byte code offset
/*      */     //   #445	-> 0
/*      */     //   #446	-> 7
/*      */     //   #448	-> 8
/*      */     //   #449	-> 15
/*      */     //   #450	-> 23
/*      */     //   #452	-> 24
/*      */     //   #453	-> 26
/*      */     //   #454	-> 31
/*      */     //   #458	-> 37
/*      */     //   #462	-> 40
/*      */     //   #463	-> 45
/*      */     //   #465	-> 48
/*      */     //   #466	-> 58
/*      */     //   #467	-> 65
/*      */     //   #468	-> 72
/*      */     //   #469	-> 93
/*      */     //   #470	-> 100
/*      */     //   #471	-> 107
/*      */     //   #474	-> 110
/*      */     //   #475	-> 122
/*      */     //   #481	-> 133
/*      */     //   #482	-> 140
/*      */     //   #483	-> 144
/*      */     //   #484	-> 147
/*      */     //   #485	-> 154
/*      */     //   #486	-> 158
/*      */     //   #487	-> 161
/*      */     //   #490	-> 168
/*      */     //   #491	-> 185
/*      */     //   #492	-> 189
/*      */     //   #495	-> 192
/*      */     //   #496	-> 199
/*      */     //   #498	-> 202
/*      */     //   #499	-> 213
/*      */     //   #501	-> 217
/*      */     //   #502	-> 235
/*      */     //   #503	-> 238
/*      */     //   #504	-> 243
/*      */     //   #506	-> 252
/*      */     //   #508	-> 263
/*      */     //   #509	-> 266
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	descriptor
/*      */     //   58	177	6	c	C
/*      */     //   40	223	5	ent	Ljava/lang/String;
/*      */     //   0	267	0	this	Lcom/ctc/wstx/sw/BufferingXmlWriter;
/*      */     //   0	267	1	text	Ljava/lang/String;
/*      */     //   26	241	2	inPtr	I
/*      */     //   31	236	3	len	I
/*      */     //   37	230	4	highChar	I
/*      */   }
/*      */   
/*      */   public void writeCharacters(char[] cbuf, int offset, int len) throws IOException {
/*  514 */     if (this.mOut == null)
/*      */       return; 
/*  518 */     if (this.mTextWriter != null) {
/*  519 */       this.mTextWriter.write(cbuf, offset, len);
/*      */     } else {
/*  521 */       len += offset;
/*      */       do {
/*  523 */         int c = 0;
/*  524 */         int highChar = this.mEncHighChar;
/*  525 */         int start = offset;
/*  526 */         String ent = null;
/*  528 */         for (; offset < len; offset++) {
/*  529 */           c = cbuf[offset];
/*  530 */           if (c <= 62) {
/*  531 */             if (c == 60) {
/*  532 */               ent = "&lt;";
/*      */               break;
/*      */             } 
/*  534 */             if (c == 38) {
/*  535 */               ent = "&amp;";
/*      */               break;
/*      */             } 
/*  537 */             if (c == 62) {
/*  541 */               if (offset == start || cbuf[offset - 1] == ']') {
/*  542 */                 ent = "&gt;";
/*      */                 break;
/*      */               } 
/*  545 */             } else if (c < 32 && 
/*  546 */               c != 10 && c != 9) {
/*  548 */               if (c == 13) {
/*  549 */                 if (this.mEscapeCR)
/*      */                   break; 
/*      */               } else {
/*  553 */                 if (!this.mXml11 || c == 0) {
/*  554 */                   c = handleInvalidChar(c);
/*  556 */                   ent = String.valueOf((char)c);
/*      */                 } 
/*      */                 break;
/*      */               } 
/*      */             } 
/*  561 */           } else if (c >= highChar) {
/*      */             break;
/*      */           } 
/*      */         } 
/*  566 */         int outLen = offset - start;
/*  567 */         if (outLen > 0)
/*  568 */           writeRaw(cbuf, start, outLen); 
/*  570 */         if (ent != null) {
/*  571 */           writeRaw(ent);
/*  572 */           ent = null;
/*  573 */         } else if (offset < len) {
/*  574 */           writeAsEntity(c);
/*      */         } 
/*  576 */       } while (++offset < len);
/*      */     } 
/*      */   }
/*      */   
/*      */   public int writeComment(String data) throws IOException {
/*  590 */     if (this.mCheckContent) {
/*  591 */       int ix = verifyCommentContent(data);
/*  592 */       if (ix >= 0) {
/*  593 */         if (!this.mFixContent)
/*  594 */           return ix; 
/*  597 */         writeSegmentedComment(data, ix);
/*  598 */         return -1;
/*      */       } 
/*      */     } 
/*  601 */     fastWriteRaw("<!--");
/*  602 */     writeRaw(data);
/*  603 */     fastWriteRaw("-->");
/*  604 */     return -1;
/*      */   }
/*      */   
/*      */   public void writeDTD(String data) throws IOException {
/*  610 */     writeRaw(data);
/*      */   }
/*      */   
/*      */   public void writeDTD(String rootName, String systemId, String publicId, String internalSubset) throws IOException, XMLStreamException {
/*  617 */     fastWriteRaw("<!DOCTYPE ");
/*  618 */     if (this.mCheckNames)
/*  624 */       verifyNameValidity(rootName, false); 
/*  626 */     fastWriteRaw(rootName);
/*  627 */     if (systemId != null) {
/*  628 */       if (publicId != null) {
/*  629 */         fastWriteRaw(" PUBLIC \"");
/*  630 */         fastWriteRaw(publicId);
/*  631 */         fastWriteRaw("\" \"");
/*      */       } else {
/*  633 */         fastWriteRaw(" SYSTEM \"");
/*      */       } 
/*  635 */       fastWriteRaw(systemId);
/*  636 */       fastWriteRaw('"');
/*      */     } 
/*  639 */     if (internalSubset != null && internalSubset.length() > 0) {
/*  640 */       fastWriteRaw(' ', '[');
/*  641 */       fastWriteRaw(internalSubset);
/*  642 */       fastWriteRaw(']');
/*      */     } 
/*  644 */     fastWriteRaw('>');
/*      */   }
/*      */   
/*      */   public void writeEntityReference(String name) throws IOException, XMLStreamException {
/*  650 */     if (this.mCheckNames)
/*  651 */       verifyNameValidity(name, this.mNsAware); 
/*  653 */     fastWriteRaw('&');
/*  654 */     fastWriteRaw(name);
/*  655 */     fastWriteRaw(';');
/*      */   }
/*      */   
/*      */   public void writeXmlDeclaration(String version, String encoding, String standalone) throws IOException {
/*  661 */     fastWriteRaw("<?xml version='");
/*  662 */     fastWriteRaw(version);
/*  663 */     fastWriteRaw('\'');
/*  665 */     if (encoding != null && encoding.length() > 0) {
/*  666 */       fastWriteRaw(" encoding='");
/*  667 */       fastWriteRaw(encoding);
/*  668 */       fastWriteRaw('\'');
/*      */     } 
/*  670 */     if (standalone != null) {
/*  671 */       fastWriteRaw(" standalone='");
/*  672 */       fastWriteRaw(standalone);
/*  673 */       fastWriteRaw('\'');
/*      */     } 
/*  675 */     fastWriteRaw('?', '>');
/*      */   }
/*      */   
/*      */   public int writePI(String target, String data) throws IOException, XMLStreamException {
/*  681 */     if (this.mCheckNames)
/*  683 */       verifyNameValidity(target, this.mNsAware); 
/*  685 */     fastWriteRaw('<', '?');
/*  686 */     fastWriteRaw(target);
/*  687 */     if (data != null && data.length() > 0) {
/*  688 */       if (this.mCheckContent) {
/*  689 */         int ix = data.indexOf('?');
/*  690 */         if (ix >= 0) {
/*  691 */           ix = data.indexOf("?>", ix);
/*  692 */           if (ix >= 0)
/*  693 */             return ix; 
/*      */         } 
/*      */       } 
/*  697 */       fastWriteRaw(' ');
/*  699 */       writeRaw(data);
/*      */     } 
/*  701 */     fastWriteRaw('?', '>');
/*  702 */     return -1;
/*      */   }
/*      */   
/*      */   public void writeStartTagStart(String localName) throws IOException, XMLStreamException {
/*  714 */     if (this.mCheckNames)
/*  715 */       verifyNameValidity(localName, this.mNsAware); 
/*  718 */     int ptr = this.mOutputPtr;
/*  719 */     int extra = this.mOutputBufLen - ptr - 1 + localName.length();
/*  720 */     if (extra < 0) {
/*  721 */       fastWriteRaw('<');
/*  722 */       fastWriteRaw(localName);
/*      */     } else {
/*  724 */       char[] buf = this.mOutputBuffer;
/*  725 */       buf[ptr++] = '<';
/*  726 */       int len = localName.length();
/*  727 */       localName.getChars(0, len, buf, ptr);
/*  728 */       this.mOutputPtr = ptr + len;
/*      */     } 
/*      */   }
/*      */   
/*      */   public void writeStartTagStart(String prefix, String localName) throws IOException, XMLStreamException {
/*  735 */     if (prefix == null || prefix.length() == 0) {
/*  736 */       writeStartTagStart(localName);
/*      */       return;
/*      */     } 
/*  740 */     if (this.mCheckNames) {
/*  741 */       verifyNameValidity(prefix, this.mNsAware);
/*  742 */       verifyNameValidity(localName, this.mNsAware);
/*      */     } 
/*  745 */     int ptr = this.mOutputPtr;
/*  746 */     int len = prefix.length();
/*  747 */     int extra = this.mOutputBufLen - ptr - 2 + localName.length() + len;
/*  748 */     if (extra < 0) {
/*  749 */       fastWriteRaw('<');
/*  750 */       fastWriteRaw(prefix);
/*  751 */       fastWriteRaw(':');
/*  752 */       fastWriteRaw(localName);
/*      */     } else {
/*  754 */       char[] buf = this.mOutputBuffer;
/*  755 */       buf[ptr++] = '<';
/*  756 */       prefix.getChars(0, len, buf, ptr);
/*  757 */       ptr += len;
/*  758 */       buf[ptr++] = ':';
/*  759 */       len = localName.length();
/*  760 */       localName.getChars(0, len, buf, ptr);
/*  761 */       this.mOutputPtr = ptr + len;
/*      */     } 
/*      */   }
/*      */   
/*      */   public void writeStartTagEnd() throws IOException {
/*  768 */     fastWriteRaw('>');
/*      */   }
/*      */   
/*      */   public void writeStartTagEmptyEnd() throws IOException {
/*  774 */     int ptr = this.mOutputPtr;
/*  775 */     if (ptr + 3 >= this.mOutputBufLen) {
/*  776 */       if (this.mOut == null)
/*      */         return; 
/*  779 */       flushBuffer();
/*  780 */       ptr = this.mOutputPtr;
/*      */     } 
/*  782 */     char[] buf = this.mOutputBuffer;
/*  783 */     if (this.mAddSpaceAfterEmptyElem)
/*  784 */       buf[ptr++] = ' '; 
/*  786 */     buf[ptr++] = '/';
/*  787 */     buf[ptr++] = '>';
/*  788 */     this.mOutputPtr = ptr;
/*      */   }
/*      */   
/*      */   public void writeEndTag(String localName) throws IOException {
/*  794 */     int ptr = this.mOutputPtr;
/*  795 */     int extra = this.mOutputBufLen - ptr - 3 + localName.length();
/*  796 */     if (extra < 0) {
/*  797 */       fastWriteRaw('<', '/');
/*  798 */       fastWriteRaw(localName);
/*  799 */       fastWriteRaw('>');
/*      */     } else {
/*  801 */       char[] buf = this.mOutputBuffer;
/*  802 */       buf[ptr++] = '<';
/*  803 */       buf[ptr++] = '/';
/*  804 */       int len = localName.length();
/*  805 */       localName.getChars(0, len, buf, ptr);
/*  806 */       ptr += len;
/*  807 */       buf[ptr++] = '>';
/*  808 */       this.mOutputPtr = ptr;
/*      */     } 
/*      */   }
/*      */   
/*      */   public void writeEndTag(String prefix, String localName) throws IOException {
/*  815 */     if (prefix == null || prefix.length() == 0) {
/*  816 */       writeEndTag(localName);
/*      */       return;
/*      */     } 
/*  819 */     int ptr = this.mOutputPtr;
/*  820 */     int len = prefix.length();
/*  821 */     int extra = this.mOutputBufLen - ptr - 4 + localName.length() + len;
/*  822 */     if (extra < 0) {
/*  823 */       fastWriteRaw('<', '/');
/*  828 */       fastWriteRaw(prefix);
/*  829 */       fastWriteRaw(':');
/*  830 */       fastWriteRaw(localName);
/*  831 */       fastWriteRaw('>');
/*      */     } else {
/*  833 */       char[] buf = this.mOutputBuffer;
/*  834 */       buf[ptr++] = '<';
/*  835 */       buf[ptr++] = '/';
/*  836 */       prefix.getChars(0, len, buf, ptr);
/*  837 */       ptr += len;
/*  838 */       buf[ptr++] = ':';
/*  839 */       len = localName.length();
/*  840 */       localName.getChars(0, len, buf, ptr);
/*  841 */       ptr += len;
/*  842 */       buf[ptr++] = '>';
/*  843 */       this.mOutputPtr = ptr;
/*      */     } 
/*      */   }
/*      */   
/*      */   public void writeAttribute(String localName, String value) throws IOException, XMLStreamException {
/*  856 */     if (this.mOut == null)
/*      */       return; 
/*  859 */     if (this.mCheckNames)
/*  860 */       verifyNameValidity(localName, this.mNsAware); 
/*  862 */     int len = localName.length();
/*  863 */     if (this.mOutputBufLen - this.mOutputPtr - 3 + len < 0) {
/*  864 */       fastWriteRaw(' ');
/*  865 */       fastWriteRaw(localName);
/*  866 */       fastWriteRaw('=', '"');
/*      */     } else {
/*  868 */       int ptr = this.mOutputPtr;
/*  869 */       char[] buf = this.mOutputBuffer;
/*  870 */       buf[ptr++] = ' ';
/*  871 */       localName.getChars(0, len, buf, ptr);
/*  872 */       ptr += len;
/*  873 */       buf[ptr++] = '=';
/*  874 */       buf[ptr++] = '"';
/*  875 */       this.mOutputPtr = ptr;
/*      */     } 
/*  878 */     len = (value == null) ? 0 : value.length();
/*  879 */     if (len > 0)
/*  880 */       if (this.mAttrValueWriter != null) {
/*  881 */         this.mAttrValueWriter.write(value, 0, len);
/*      */       } else {
/*  883 */         writeAttrValue(value, len);
/*      */       }  
/*  886 */     fastWriteRaw('"');
/*      */   }
/*      */   
/*      */   public void writeAttribute(String localName, char[] value, int offset, int vlen) throws IOException, XMLStreamException {
/*  892 */     if (this.mOut == null)
/*      */       return; 
/*  895 */     if (this.mCheckNames)
/*  896 */       verifyNameValidity(localName, this.mNsAware); 
/*  898 */     int len = localName.length();
/*  899 */     if (this.mOutputBufLen - this.mOutputPtr - 3 + len < 0) {
/*  900 */       fastWriteRaw(' ');
/*  901 */       fastWriteRaw(localName);
/*  902 */       fastWriteRaw('=', '"');
/*      */     } else {
/*  904 */       int ptr = this.mOutputPtr;
/*  905 */       char[] buf = this.mOutputBuffer;
/*  906 */       buf[ptr++] = ' ';
/*  907 */       localName.getChars(0, len, buf, ptr);
/*  908 */       ptr += len;
/*  909 */       buf[ptr++] = '=';
/*  910 */       buf[ptr++] = '"';
/*  911 */       this.mOutputPtr = ptr;
/*      */     } 
/*  914 */     if (vlen > 0)
/*  915 */       if (this.mAttrValueWriter != null) {
/*  916 */         this.mAttrValueWriter.write(value, offset, vlen);
/*      */       } else {
/*  918 */         writeAttrValue(value, offset, vlen);
/*      */       }  
/*  921 */     fastWriteRaw('"');
/*      */   }
/*      */   
/*      */   public void writeAttribute(String prefix, String localName, String value) throws IOException, XMLStreamException {
/*  927 */     if (this.mOut == null)
/*      */       return; 
/*  930 */     if (this.mCheckNames) {
/*  931 */       verifyNameValidity(prefix, this.mNsAware);
/*  932 */       verifyNameValidity(localName, this.mNsAware);
/*      */     } 
/*  934 */     int len = prefix.length();
/*  935 */     if (this.mOutputBufLen - this.mOutputPtr - 4 + localName.length() + len < 0) {
/*  936 */       fastWriteRaw(' ');
/*  937 */       if (len > 0) {
/*  938 */         fastWriteRaw(prefix);
/*  939 */         fastWriteRaw(':');
/*      */       } 
/*  941 */       fastWriteRaw(localName);
/*  942 */       fastWriteRaw('=', '"');
/*      */     } else {
/*  944 */       int ptr = this.mOutputPtr;
/*  945 */       char[] buf = this.mOutputBuffer;
/*  946 */       buf[ptr++] = ' ';
/*  947 */       prefix.getChars(0, len, buf, ptr);
/*  948 */       ptr += len;
/*  949 */       buf[ptr++] = ':';
/*  950 */       len = localName.length();
/*  951 */       localName.getChars(0, len, buf, ptr);
/*  952 */       ptr += len;
/*  953 */       buf[ptr++] = '=';
/*  954 */       buf[ptr++] = '"';
/*  955 */       this.mOutputPtr = ptr;
/*      */     } 
/*  958 */     len = (value == null) ? 0 : value.length();
/*  959 */     if (len > 0)
/*  960 */       if (this.mAttrValueWriter != null) {
/*  961 */         this.mAttrValueWriter.write(value, 0, len);
/*      */       } else {
/*  963 */         writeAttrValue(value, len);
/*      */       }  
/*  966 */     fastWriteRaw('"');
/*      */   }
/*      */   
/*      */   public void writeAttribute(String prefix, String localName, char[] value, int offset, int vlen) throws IOException, XMLStreamException {
/*  972 */     if (this.mOut == null)
/*      */       return; 
/*  975 */     if (this.mCheckNames) {
/*  976 */       verifyNameValidity(prefix, this.mNsAware);
/*  977 */       verifyNameValidity(localName, this.mNsAware);
/*      */     } 
/*  979 */     int len = prefix.length();
/*  980 */     if (this.mOutputBufLen - this.mOutputPtr - 4 + localName.length() + len < 0) {
/*  981 */       fastWriteRaw(' ');
/*  982 */       if (len > 0) {
/*  983 */         fastWriteRaw(prefix);
/*  984 */         fastWriteRaw(':');
/*      */       } 
/*  986 */       fastWriteRaw(localName);
/*  987 */       fastWriteRaw('=', '"');
/*      */     } else {
/*  989 */       int ptr = this.mOutputPtr;
/*  990 */       char[] buf = this.mOutputBuffer;
/*  991 */       buf[ptr++] = ' ';
/*  992 */       prefix.getChars(0, len, buf, ptr);
/*  993 */       ptr += len;
/*  994 */       buf[ptr++] = ':';
/*  995 */       len = localName.length();
/*  996 */       localName.getChars(0, len, buf, ptr);
/*  997 */       ptr += len;
/*  998 */       buf[ptr++] = '=';
/*  999 */       buf[ptr++] = '"';
/* 1000 */       this.mOutputPtr = ptr;
/*      */     } 
/* 1002 */     if (vlen > 0)
/* 1003 */       if (this.mAttrValueWriter != null) {
/* 1004 */         this.mAttrValueWriter.write(value, offset, vlen);
/*      */       } else {
/* 1006 */         writeAttrValue(value, offset, vlen);
/*      */       }  
/* 1009 */     fastWriteRaw('"');
/*      */   }
/*      */   
/*      */   private final void writeAttrValue(String value, int len) throws IOException {
/*      */     // Byte code:
/*      */     //   0: iconst_0
/*      */     //   1: istore_3
/*      */     //   2: aload_0
/*      */     //   3: getfield mEncQuoteChar : C
/*      */     //   6: istore #4
/*      */     //   8: aload_0
/*      */     //   9: getfield mEncHighChar : I
/*      */     //   12: istore #5
/*      */     //   14: aconst_null
/*      */     //   15: astore #6
/*      */     //   17: iload_3
/*      */     //   18: iload_2
/*      */     //   19: if_icmplt -> 25
/*      */     //   22: goto -> 221
/*      */     //   25: aload_1
/*      */     //   26: iload_3
/*      */     //   27: iinc #3, 1
/*      */     //   30: invokevirtual charAt : (I)C
/*      */     //   33: istore #7
/*      */     //   35: iload #7
/*      */     //   37: bipush #60
/*      */     //   39: if_icmpgt -> 147
/*      */     //   42: iload #7
/*      */     //   44: bipush #32
/*      */     //   46: if_icmpge -> 103
/*      */     //   49: iload #7
/*      */     //   51: bipush #13
/*      */     //   53: if_icmpne -> 66
/*      */     //   56: aload_0
/*      */     //   57: getfield mEscapeCR : Z
/*      */     //   60: ifeq -> 157
/*      */     //   63: goto -> 193
/*      */     //   66: iload #7
/*      */     //   68: bipush #10
/*      */     //   70: if_icmpeq -> 193
/*      */     //   73: iload #7
/*      */     //   75: bipush #9
/*      */     //   77: if_icmpeq -> 193
/*      */     //   80: aload_0
/*      */     //   81: getfield mXml11 : Z
/*      */     //   84: ifeq -> 92
/*      */     //   87: iload #7
/*      */     //   89: ifne -> 193
/*      */     //   92: aload_0
/*      */     //   93: iload #7
/*      */     //   95: invokevirtual handleInvalidChar : (I)C
/*      */     //   98: istore #7
/*      */     //   100: goto -> 157
/*      */     //   103: iload #7
/*      */     //   105: iload #4
/*      */     //   107: if_icmpne -> 119
/*      */     //   110: aload_0
/*      */     //   111: getfield mEncQuoteEntity : Ljava/lang/String;
/*      */     //   114: astore #6
/*      */     //   116: goto -> 193
/*      */     //   119: iload #7
/*      */     //   121: bipush #60
/*      */     //   123: if_icmpne -> 133
/*      */     //   126: ldc '&lt;'
/*      */     //   128: astore #6
/*      */     //   130: goto -> 193
/*      */     //   133: iload #7
/*      */     //   135: bipush #38
/*      */     //   137: if_icmpne -> 157
/*      */     //   140: ldc '&amp;'
/*      */     //   142: astore #6
/*      */     //   144: goto -> 193
/*      */     //   147: iload #7
/*      */     //   149: iload #5
/*      */     //   151: if_icmplt -> 157
/*      */     //   154: goto -> 193
/*      */     //   157: aload_0
/*      */     //   158: getfield mOutputPtr : I
/*      */     //   161: aload_0
/*      */     //   162: getfield mOutputBufLen : I
/*      */     //   165: if_icmplt -> 172
/*      */     //   168: aload_0
/*      */     //   169: invokespecial flushBuffer : ()V
/*      */     //   172: aload_0
/*      */     //   173: getfield mOutputBuffer : [C
/*      */     //   176: aload_0
/*      */     //   177: dup
/*      */     //   178: getfield mOutputPtr : I
/*      */     //   181: dup_x1
/*      */     //   182: iconst_1
/*      */     //   183: iadd
/*      */     //   184: putfield mOutputPtr : I
/*      */     //   187: iload #7
/*      */     //   189: castore
/*      */     //   190: goto -> 17
/*      */     //   193: aload #6
/*      */     //   195: ifnull -> 207
/*      */     //   198: aload_0
/*      */     //   199: aload #6
/*      */     //   201: invokevirtual writeRaw : (Ljava/lang/String;)V
/*      */     //   204: goto -> 218
/*      */     //   207: aload_0
/*      */     //   208: aload_1
/*      */     //   209: iload_3
/*      */     //   210: iconst_1
/*      */     //   211: isub
/*      */     //   212: invokevirtual charAt : (I)C
/*      */     //   215: invokevirtual writeAsEntity : (I)V
/*      */     //   218: goto -> 14
/*      */     //   221: return
/*      */     // Line number table:
/*      */     //   Java source line number -> byte code offset
/*      */     //   #1015	-> 0
/*      */     //   #1016	-> 2
/*      */     //   #1017	-> 8
/*      */     //   #1021	-> 14
/*      */     //   #1025	-> 17
/*      */     //   #1026	-> 22
/*      */     //   #1028	-> 25
/*      */     //   #1029	-> 35
/*      */     //   #1030	-> 42
/*      */     //   #1031	-> 49
/*      */     //   #1032	-> 56
/*      */     //   #1033	-> 63
/*      */     //   #1035	-> 66
/*      */     //   #1037	-> 92
/*      */     //   #1041	-> 103
/*      */     //   #1042	-> 110
/*      */     //   #1043	-> 116
/*      */     //   #1044	-> 119
/*      */     //   #1045	-> 126
/*      */     //   #1046	-> 130
/*      */     //   #1047	-> 133
/*      */     //   #1048	-> 140
/*      */     //   #1049	-> 144
/*      */     //   #1051	-> 147
/*      */     //   #1052	-> 154
/*      */     //   #1054	-> 157
/*      */     //   #1055	-> 168
/*      */     //   #1057	-> 172
/*      */     //   #1058	-> 190
/*      */     //   #1059	-> 193
/*      */     //   #1060	-> 198
/*      */     //   #1062	-> 207
/*      */     //   #1064	-> 218
/*      */     //   #1065	-> 221
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	descriptor
/*      */     //   35	155	7	c	C
/*      */     //   17	201	6	ent	Ljava/lang/String;
/*      */     //   0	222	0	this	Lcom/ctc/wstx/sw/BufferingXmlWriter;
/*      */     //   0	222	1	value	Ljava/lang/String;
/*      */     //   0	222	2	len	I
/*      */     //   2	220	3	inPtr	I
/*      */     //   8	214	4	qchar	C
/*      */     //   14	208	5	highChar	I
/*      */   }
/*      */   
/*      */   private final void writeAttrValue(char[] value, int offset, int len) throws IOException {
/*      */     // Byte code:
/*      */     //   0: iload_3
/*      */     //   1: iload_2
/*      */     //   2: iadd
/*      */     //   3: istore_3
/*      */     //   4: aload_0
/*      */     //   5: getfield mEncQuoteChar : C
/*      */     //   8: istore #4
/*      */     //   10: aload_0
/*      */     //   11: getfield mEncHighChar : I
/*      */     //   14: istore #5
/*      */     //   16: aconst_null
/*      */     //   17: astore #6
/*      */     //   19: iload_2
/*      */     //   20: iload_3
/*      */     //   21: if_icmplt -> 27
/*      */     //   24: goto -> 219
/*      */     //   27: aload_1
/*      */     //   28: iload_2
/*      */     //   29: iinc #2, 1
/*      */     //   32: caload
/*      */     //   33: istore #7
/*      */     //   35: iload #7
/*      */     //   37: bipush #60
/*      */     //   39: if_icmpgt -> 147
/*      */     //   42: iload #7
/*      */     //   44: bipush #32
/*      */     //   46: if_icmpge -> 103
/*      */     //   49: iload #7
/*      */     //   51: bipush #13
/*      */     //   53: if_icmpne -> 66
/*      */     //   56: aload_0
/*      */     //   57: getfield mEscapeCR : Z
/*      */     //   60: ifeq -> 157
/*      */     //   63: goto -> 193
/*      */     //   66: iload #7
/*      */     //   68: bipush #10
/*      */     //   70: if_icmpeq -> 193
/*      */     //   73: iload #7
/*      */     //   75: bipush #9
/*      */     //   77: if_icmpeq -> 193
/*      */     //   80: aload_0
/*      */     //   81: getfield mXml11 : Z
/*      */     //   84: ifeq -> 92
/*      */     //   87: iload #7
/*      */     //   89: ifne -> 193
/*      */     //   92: aload_0
/*      */     //   93: iload #7
/*      */     //   95: invokevirtual handleInvalidChar : (I)C
/*      */     //   98: istore #7
/*      */     //   100: goto -> 157
/*      */     //   103: iload #7
/*      */     //   105: iload #4
/*      */     //   107: if_icmpne -> 119
/*      */     //   110: aload_0
/*      */     //   111: getfield mEncQuoteEntity : Ljava/lang/String;
/*      */     //   114: astore #6
/*      */     //   116: goto -> 193
/*      */     //   119: iload #7
/*      */     //   121: bipush #60
/*      */     //   123: if_icmpne -> 133
/*      */     //   126: ldc '&lt;'
/*      */     //   128: astore #6
/*      */     //   130: goto -> 193
/*      */     //   133: iload #7
/*      */     //   135: bipush #38
/*      */     //   137: if_icmpne -> 157
/*      */     //   140: ldc '&amp;'
/*      */     //   142: astore #6
/*      */     //   144: goto -> 193
/*      */     //   147: iload #7
/*      */     //   149: iload #5
/*      */     //   151: if_icmplt -> 157
/*      */     //   154: goto -> 193
/*      */     //   157: aload_0
/*      */     //   158: getfield mOutputPtr : I
/*      */     //   161: aload_0
/*      */     //   162: getfield mOutputBufLen : I
/*      */     //   165: if_icmplt -> 172
/*      */     //   168: aload_0
/*      */     //   169: invokespecial flushBuffer : ()V
/*      */     //   172: aload_0
/*      */     //   173: getfield mOutputBuffer : [C
/*      */     //   176: aload_0
/*      */     //   177: dup
/*      */     //   178: getfield mOutputPtr : I
/*      */     //   181: dup_x1
/*      */     //   182: iconst_1
/*      */     //   183: iadd
/*      */     //   184: putfield mOutputPtr : I
/*      */     //   187: iload #7
/*      */     //   189: castore
/*      */     //   190: goto -> 19
/*      */     //   193: aload #6
/*      */     //   195: ifnull -> 207
/*      */     //   198: aload_0
/*      */     //   199: aload #6
/*      */     //   201: invokevirtual writeRaw : (Ljava/lang/String;)V
/*      */     //   204: goto -> 216
/*      */     //   207: aload_0
/*      */     //   208: aload_1
/*      */     //   209: iload_2
/*      */     //   210: iconst_1
/*      */     //   211: isub
/*      */     //   212: caload
/*      */     //   213: invokevirtual writeAsEntity : (I)V
/*      */     //   216: goto -> 16
/*      */     //   219: return
/*      */     // Line number table:
/*      */     //   Java source line number -> byte code offset
/*      */     //   #1070	-> 0
/*      */     //   #1071	-> 4
/*      */     //   #1072	-> 10
/*      */     //   #1076	-> 16
/*      */     //   #1080	-> 19
/*      */     //   #1081	-> 24
/*      */     //   #1083	-> 27
/*      */     //   #1084	-> 35
/*      */     //   #1085	-> 42
/*      */     //   #1086	-> 49
/*      */     //   #1087	-> 56
/*      */     //   #1088	-> 63
/*      */     //   #1090	-> 66
/*      */     //   #1092	-> 92
/*      */     //   #1096	-> 103
/*      */     //   #1097	-> 110
/*      */     //   #1098	-> 116
/*      */     //   #1099	-> 119
/*      */     //   #1100	-> 126
/*      */     //   #1101	-> 130
/*      */     //   #1102	-> 133
/*      */     //   #1103	-> 140
/*      */     //   #1104	-> 144
/*      */     //   #1106	-> 147
/*      */     //   #1107	-> 154
/*      */     //   #1109	-> 157
/*      */     //   #1110	-> 168
/*      */     //   #1112	-> 172
/*      */     //   #1113	-> 190
/*      */     //   #1114	-> 193
/*      */     //   #1115	-> 198
/*      */     //   #1117	-> 207
/*      */     //   #1119	-> 216
/*      */     //   #1120	-> 219
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	descriptor
/*      */     //   35	155	7	c	C
/*      */     //   19	197	6	ent	Ljava/lang/String;
/*      */     //   0	220	0	this	Lcom/ctc/wstx/sw/BufferingXmlWriter;
/*      */     //   0	220	1	value	[C
/*      */     //   0	220	2	offset	I
/*      */     //   0	220	3	len	I
/*      */     //   10	210	4	qchar	C
/*      */     //   16	204	5	highChar	I
/*      */   }
/*      */   
/*      */   public final void writeTypedElement(AsciiValueEncoder enc) throws IOException {
/* 1131 */     if (this.mOut == null)
/*      */       return; 
/* 1135 */     int free = this.mOutputBufLen - this.mOutputPtr;
/* 1136 */     if (enc.bufferNeedsFlush(free))
/* 1137 */       flush(); 
/*      */     while (true) {
/* 1140 */       this.mOutputPtr = enc.encodeMore(this.mOutputBuffer, this.mOutputPtr, this.mOutputBufLen);
/* 1142 */       if (enc.isCompleted())
/*      */         break; 
/* 1145 */       flush();
/*      */     } 
/*      */   }
/*      */   
/*      */   public final void writeTypedElement(AsciiValueEncoder enc, XMLValidator validator, char[] copyBuffer) throws IOException, XMLStreamException {
/* 1153 */     if (this.mOut == null)
/*      */       return; 
/* 1156 */     int free = this.mOutputBufLen - this.mOutputPtr;
/* 1157 */     if (enc.bufferNeedsFlush(free))
/* 1158 */       flush(); 
/* 1160 */     int start = this.mOutputPtr;
/*      */     while (true) {
/* 1162 */       this.mOutputPtr = enc.encodeMore(this.mOutputBuffer, this.mOutputPtr, this.mOutputBufLen);
/* 1164 */       validator.validateText(this.mOutputBuffer, start, this.mOutputPtr, false);
/* 1165 */       if (enc.isCompleted())
/*      */         break; 
/* 1168 */       flush();
/* 1169 */       start = this.mOutputPtr;
/*      */     } 
/*      */   }
/*      */   
/*      */   public void writeTypedAttribute(String localName, AsciiValueEncoder enc) throws IOException, XMLStreamException {
/* 1176 */     if (this.mOut == null)
/*      */       return; 
/* 1179 */     if (this.mCheckNames)
/* 1180 */       verifyNameValidity(localName, this.mNsAware); 
/* 1182 */     int len = localName.length();
/* 1183 */     if (this.mOutputPtr + 3 + len > this.mOutputBufLen) {
/* 1184 */       fastWriteRaw(' ');
/* 1185 */       fastWriteRaw(localName);
/* 1186 */       fastWriteRaw('=', '"');
/*      */     } else {
/* 1188 */       int ptr = this.mOutputPtr;
/* 1189 */       char[] buf = this.mOutputBuffer;
/* 1190 */       buf[ptr++] = ' ';
/* 1191 */       localName.getChars(0, len, buf, ptr);
/* 1192 */       ptr += len;
/* 1193 */       buf[ptr++] = '=';
/* 1194 */       buf[ptr++] = '"';
/* 1195 */       this.mOutputPtr = ptr;
/*      */     } 
/* 1198 */     int free = this.mOutputBufLen - this.mOutputPtr;
/* 1199 */     if (enc.bufferNeedsFlush(free))
/* 1200 */       flush(); 
/*      */     while (true) {
/* 1203 */       this.mOutputPtr = enc.encodeMore(this.mOutputBuffer, this.mOutputPtr, this.mOutputBufLen);
/* 1204 */       if (enc.isCompleted())
/*      */         break; 
/* 1207 */       flush();
/*      */     } 
/* 1209 */     fastWriteRaw('"');
/*      */   }
/*      */   
/*      */   public void writeTypedAttribute(String prefix, String localName, AsciiValueEncoder enc) throws IOException, XMLStreamException {
/* 1216 */     if (this.mOut == null)
/*      */       return; 
/* 1219 */     if (this.mCheckNames) {
/* 1220 */       verifyNameValidity(prefix, this.mNsAware);
/* 1221 */       verifyNameValidity(localName, this.mNsAware);
/*      */     } 
/* 1223 */     int plen = prefix.length();
/* 1224 */     int llen = localName.length();
/* 1226 */     if (this.mOutputPtr + 4 + plen + llen > this.mOutputBufLen) {
/* 1227 */       writePrefixedName(prefix, localName);
/* 1228 */       fastWriteRaw('=', '"');
/*      */     } else {
/* 1230 */       int ptr = this.mOutputPtr;
/* 1231 */       char[] buf = this.mOutputBuffer;
/* 1232 */       buf[ptr++] = ' ';
/* 1233 */       if (plen > 0) {
/* 1234 */         prefix.getChars(0, plen, buf, ptr);
/* 1235 */         ptr += plen;
/* 1236 */         buf[ptr++] = ':';
/*      */       } 
/* 1239 */       localName.getChars(0, llen, buf, ptr);
/* 1240 */       ptr += llen;
/* 1241 */       buf[ptr++] = '=';
/* 1242 */       buf[ptr++] = '"';
/* 1243 */       this.mOutputPtr = ptr;
/*      */     } 
/* 1246 */     int free = this.mOutputBufLen - this.mOutputPtr;
/* 1247 */     if (enc.bufferNeedsFlush(free))
/* 1248 */       flush(); 
/*      */     while (true) {
/* 1251 */       this.mOutputPtr = enc.encodeMore(this.mOutputBuffer, this.mOutputPtr, this.mOutputBufLen);
/* 1252 */       if (enc.isCompleted())
/*      */         break; 
/* 1255 */       flush();
/*      */     } 
/* 1258 */     fastWriteRaw('"');
/*      */   }
/*      */   
/*      */   public void writeTypedAttribute(String prefix, String localName, String nsURI, AsciiValueEncoder enc, XMLValidator validator, char[] copyBuffer) throws IOException, XMLStreamException {
/* 1266 */     if (this.mOut == null)
/*      */       return; 
/* 1269 */     if (prefix == null)
/* 1270 */       prefix = ""; 
/* 1272 */     if (nsURI == null)
/* 1273 */       nsURI = ""; 
/* 1275 */     int plen = prefix.length();
/* 1276 */     if (this.mCheckNames) {
/* 1277 */       if (plen > 0)
/* 1278 */         verifyNameValidity(prefix, this.mNsAware); 
/* 1280 */       verifyNameValidity(localName, this.mNsAware);
/*      */     } 
/* 1282 */     if (this.mOutputBufLen - this.mOutputPtr - 4 + localName.length() + plen < 0) {
/* 1283 */       writePrefixedName(prefix, localName);
/* 1284 */       fastWriteRaw('=', '"');
/*      */     } else {
/* 1286 */       int ptr = this.mOutputPtr;
/* 1287 */       char[] buf = this.mOutputBuffer;
/* 1288 */       buf[ptr++] = ' ';
/* 1289 */       if (plen > 0) {
/* 1290 */         prefix.getChars(0, plen, buf, ptr);
/* 1291 */         ptr += plen;
/* 1292 */         buf[ptr++] = ':';
/*      */       } 
/* 1295 */       int llen = localName.length();
/* 1296 */       localName.getChars(0, llen, buf, ptr);
/* 1297 */       ptr += llen;
/* 1298 */       buf[ptr++] = '=';
/* 1299 */       buf[ptr++] = '"';
/* 1300 */       this.mOutputPtr = ptr;
/*      */     } 
/* 1307 */     int free = this.mOutputBufLen - this.mOutputPtr;
/* 1308 */     if (enc.bufferNeedsFlush(free))
/* 1309 */       flush(); 
/* 1311 */     int start = this.mOutputPtr;
/* 1314 */     this.mOutputPtr = enc.encodeMore(this.mOutputBuffer, this.mOutputPtr, this.mOutputBufLen);
/* 1315 */     if (enc.isCompleted()) {
/* 1316 */       validator.validateAttribute(localName, nsURI, prefix, this.mOutputBuffer, start, this.mOutputPtr);
/*      */       return;
/*      */     } 
/* 1321 */     StringBuffer sb = new StringBuffer(this.mOutputBuffer.length << 1);
/* 1322 */     sb.append(this.mOutputBuffer, start, this.mOutputPtr - start);
/*      */     do {
/* 1324 */       flush();
/* 1325 */       start = this.mOutputPtr;
/* 1326 */       this.mOutputPtr = enc.encodeMore(this.mOutputBuffer, this.mOutputPtr, this.mOutputBufLen);
/* 1327 */       sb.append(this.mOutputBuffer, start, this.mOutputPtr - start);
/* 1329 */     } while (!enc.isCompleted());
/* 1333 */     fastWriteRaw('"');
/* 1336 */     String valueStr = sb.toString();
/* 1337 */     validator.validateAttribute(localName, nsURI, prefix, valueStr);
/*      */   }
/*      */   
/*      */   protected final void writePrefixedName(String prefix, String localName) throws IOException {
/* 1343 */     fastWriteRaw(' ');
/* 1344 */     if (prefix.length() > 0) {
/* 1345 */       fastWriteRaw(prefix);
/* 1346 */       fastWriteRaw(':');
/*      */     } 
/* 1348 */     fastWriteRaw(localName);
/*      */   }
/*      */   
/*      */   private final void flushBuffer() throws IOException {
/* 1360 */     if (this.mOutputPtr > 0 && this.mOutputBuffer != null) {
/* 1361 */       int ptr = this.mOutputPtr;
/* 1363 */       this.mLocPastChars += ptr;
/* 1364 */       this.mLocRowStartOffset -= ptr;
/* 1365 */       this.mOutputPtr = 0;
/* 1366 */       this.mOut.write(this.mOutputBuffer, 0, ptr);
/*      */     } 
/*      */   }
/*      */   
/*      */   private final void fastWriteRaw(char c) throws IOException {
/* 1373 */     if (this.mOutputPtr >= this.mOutputBufLen) {
/* 1374 */       if (this.mOut == null)
/*      */         return; 
/* 1377 */       flushBuffer();
/*      */     } 
/* 1379 */     this.mOutputBuffer[this.mOutputPtr++] = c;
/*      */   }
/*      */   
/*      */   private final void fastWriteRaw(char c1, char c2) throws IOException {
/* 1385 */     if (this.mOutputPtr + 1 >= this.mOutputBufLen) {
/* 1386 */       if (this.mOut == null)
/*      */         return; 
/* 1389 */       flushBuffer();
/*      */     } 
/* 1391 */     this.mOutputBuffer[this.mOutputPtr++] = c1;
/* 1392 */     this.mOutputBuffer[this.mOutputPtr++] = c2;
/*      */   }
/*      */   
/*      */   private final void fastWriteRaw(String str) throws IOException {
/* 1398 */     int len = str.length();
/* 1399 */     int ptr = this.mOutputPtr;
/* 1400 */     if (ptr + len >= this.mOutputBufLen) {
/* 1401 */       if (this.mOut == null)
/*      */         return; 
/* 1408 */       if (len > this.mOutputBufLen) {
/* 1409 */         writeRaw(str);
/*      */         return;
/*      */       } 
/* 1412 */       flushBuffer();
/* 1413 */       ptr = this.mOutputPtr;
/*      */     } 
/* 1415 */     str.getChars(0, len, this.mOutputBuffer, ptr);
/* 1416 */     this.mOutputPtr = ptr + len;
/*      */   }
/*      */   
/*      */   protected int verifyCDataContent(String content) {
/* 1431 */     if (content != null && content.length() >= 3) {
/* 1432 */       int ix = content.indexOf(']');
/* 1433 */       if (ix >= 0)
/* 1434 */         return content.indexOf("]]>", ix); 
/*      */     } 
/* 1437 */     return -1;
/*      */   }
/*      */   
/*      */   protected int verifyCDataContent(char[] c, int start, int end) {
/* 1442 */     if (c != null) {
/* 1443 */       start += 2;
/* 1447 */       while (start < end) {
/* 1448 */         char ch = c[start];
/* 1449 */         if (ch == ']') {
/* 1450 */           start++;
/*      */           continue;
/*      */         } 
/* 1453 */         if (ch == '>' && 
/* 1454 */           c[start - 1] == ']' && c[start - 2] == ']')
/* 1456 */           return start - 2; 
/* 1459 */         start += 2;
/*      */       } 
/*      */     } 
/* 1462 */     return -1;
/*      */   }
/*      */   
/*      */   protected int verifyCommentContent(String content) {
/* 1467 */     int ix = content.indexOf('-');
/* 1468 */     if (ix >= 0)
/* 1472 */       if (ix < content.length() - 1)
/* 1473 */         ix = content.indexOf("--", ix);  
/* 1476 */     return ix;
/*      */   }
/*      */   
/*      */   protected void writeSegmentedCData(String content, int index) throws IOException {
/* 1486 */     int start = 0;
/* 1487 */     while (index >= 0) {
/* 1488 */       fastWriteRaw("<![CDATA[");
/* 1489 */       writeRaw(content, start, index + 2 - start);
/* 1490 */       fastWriteRaw("]]>");
/* 1491 */       start = index + 2;
/* 1492 */       index = content.indexOf("]]>", start);
/*      */     } 
/* 1495 */     fastWriteRaw("<![CDATA[");
/* 1496 */     writeRaw(content, start, content.length() - start);
/* 1497 */     fastWriteRaw("]]>");
/*      */   }
/*      */   
/*      */   protected void writeSegmentedCData(char[] c, int start, int len, int index) throws IOException {
/* 1503 */     int end = start + len;
/* 1504 */     while (index >= 0) {
/* 1505 */       fastWriteRaw("<![CDATA[");
/* 1506 */       writeRaw(c, start, index + 2 - start);
/* 1507 */       fastWriteRaw("]]>");
/* 1508 */       start = index + 2;
/* 1509 */       index = verifyCDataContent(c, start, end);
/*      */     } 
/* 1512 */     fastWriteRaw("<![CDATA[");
/* 1513 */     writeRaw(c, start, end - start);
/* 1514 */     fastWriteRaw("]]>");
/*      */   }
/*      */   
/*      */   protected void writeSegmentedComment(String content, int index) throws IOException {
/* 1520 */     int len = content.length();
/* 1522 */     if (index == len - 1) {
/* 1523 */       fastWriteRaw("<!--");
/* 1524 */       writeRaw(content);
/* 1526 */       fastWriteRaw(" -->");
/*      */       return;
/*      */     } 
/* 1539 */     fastWriteRaw("<!--");
/* 1540 */     int start = 0;
/* 1541 */     while (index >= 0) {
/* 1543 */       writeRaw(content, start, index + 1 - start);
/* 1545 */       fastWriteRaw(' ');
/* 1547 */       start = index + 1;
/* 1548 */       index = content.indexOf("--", start);
/*      */     } 
/* 1551 */     writeRaw(content, start, len - start);
/* 1553 */     if (content.charAt(len - 1) == '-')
/* 1554 */       fastWriteRaw(' '); 
/* 1556 */     fastWriteRaw("-->");
/*      */   }
/*      */   
/*      */   public static int guessEncodingBitSize(String enc) {
/* 1568 */     if (enc == null || enc.length() == 0)
/* 1569 */       return 16; 
/* 1573 */     enc = CharsetNames.normalize(enc);
/* 1576 */     if (enc == "UTF-8")
/* 1577 */       return 16; 
/* 1578 */     if (enc == "ISO-8859-1")
/* 1579 */       return 8; 
/* 1580 */     if (enc == "US-ASCII")
/* 1581 */       return 7; 
/* 1582 */     if (enc == "UTF-16" || enc == "UTF-16BE" || enc == "UTF-16LE" || enc == "UTF-32BE" || enc == "UTF-32LE")
/* 1587 */       return 16; 
/* 1595 */     return 8;
/*      */   }
/*      */   
/*      */   protected final void writeAsEntity(int c) throws IOException {
/* 1601 */     char[] buf = this.mOutputBuffer;
/* 1602 */     int ptr = this.mOutputPtr;
/* 1603 */     if (ptr + 10 >= buf.length) {
/* 1604 */       flushBuffer();
/* 1605 */       ptr = this.mOutputPtr;
/*      */     } 
/* 1607 */     buf[ptr++] = '&';
/* 1610 */     if (c < 256) {
/* 1614 */       if (c == 38) {
/* 1615 */         buf[ptr++] = 'a';
/* 1616 */         buf[ptr++] = 'm';
/* 1617 */         buf[ptr++] = 'p';
/* 1618 */       } else if (c == 60) {
/* 1619 */         buf[ptr++] = 'l';
/* 1620 */         buf[ptr++] = 't';
/* 1621 */       } else if (c == 62) {
/* 1622 */         buf[ptr++] = 'g';
/* 1623 */         buf[ptr++] = 't';
/* 1624 */       } else if (c == 39) {
/* 1625 */         buf[ptr++] = 'a';
/* 1626 */         buf[ptr++] = 'p';
/* 1627 */         buf[ptr++] = 'o';
/* 1628 */         buf[ptr++] = 's';
/* 1629 */       } else if (c == 34) {
/* 1630 */         buf[ptr++] = 'q';
/* 1631 */         buf[ptr++] = 'u';
/* 1632 */         buf[ptr++] = 'o';
/* 1633 */         buf[ptr++] = 't';
/*      */       } else {
/* 1635 */         buf[ptr++] = '#';
/* 1636 */         buf[ptr++] = 'x';
/* 1638 */         if (c >= 16) {
/* 1639 */           int digit = c >> 4;
/* 1640 */           buf[ptr++] = (char)((digit < 10) ? (48 + digit) : (87 + digit));
/* 1641 */           c &= 0xF;
/*      */         } 
/* 1643 */         buf[ptr++] = (char)((c < 10) ? (48 + c) : (87 + c));
/*      */       } 
/*      */     } else {
/* 1646 */       buf[ptr++] = '#';
/* 1647 */       buf[ptr++] = 'x';
/* 1650 */       int shift = 20;
/* 1651 */       int origPtr = ptr;
/*      */       while (true) {
/* 1654 */         int digit = c >> shift & 0xF;
/* 1655 */         if (digit > 0 || ptr != origPtr)
/* 1656 */           buf[ptr++] = (char)((digit < 10) ? (48 + digit) : (87 + digit)); 
/* 1658 */         shift -= 4;
/* 1659 */         if (shift <= 0) {
/* 1660 */           c &= 0xF;
/* 1661 */           buf[ptr++] = (char)((c < 10) ? (48 + c) : (87 + c));
/*      */         } else {
/*      */           continue;
/*      */         } 
/* 1663 */         buf[ptr++] = ';';
/* 1664 */         this.mOutputPtr = ptr;
/*      */         return;
/*      */       } 
/*      */     } 
/*      */     buf[ptr++] = ';';
/* 1664 */     this.mOutputPtr = ptr;
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\sw\BufferingXmlWriter.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */