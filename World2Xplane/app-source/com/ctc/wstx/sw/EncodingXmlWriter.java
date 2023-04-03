/*     */ package com.ctc.wstx.sw;
/*     */ 
/*     */ import com.ctc.wstx.api.WriterConfig;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.io.Writer;
/*     */ import javax.xml.stream.XMLStreamException;
/*     */ import org.codehaus.stax2.ri.typed.AsciiValueEncoder;
/*     */ import org.codehaus.stax2.validation.XMLValidator;
/*     */ 
/*     */ public abstract class EncodingXmlWriter extends XmlWriter {
/*     */   static final int DEFAULT_BUFFER_SIZE = 4000;
/*     */   
/*     */   static final byte BYTE_SPACE = 32;
/*     */   
/*     */   static final byte BYTE_COLON = 58;
/*     */   
/*     */   static final byte BYTE_SEMICOLON = 59;
/*     */   
/*     */   static final byte BYTE_LBRACKET = 91;
/*     */   
/*     */   static final byte BYTE_RBRACKET = 93;
/*     */   
/*     */   static final byte BYTE_QMARK = 63;
/*     */   
/*     */   static final byte BYTE_EQ = 61;
/*     */   
/*     */   static final byte BYTE_SLASH = 47;
/*     */   
/*     */   static final byte BYTE_HASH = 35;
/*     */   
/*     */   static final byte BYTE_HYPHEN = 45;
/*     */   
/*     */   static final byte BYTE_LT = 60;
/*     */   
/*     */   static final byte BYTE_GT = 62;
/*     */   
/*     */   static final byte BYTE_AMP = 38;
/*     */   
/*     */   static final byte BYTE_QUOT = 34;
/*     */   
/*     */   static final byte BYTE_APOS = 39;
/*     */   
/*     */   static final byte BYTE_A = 97;
/*     */   
/*     */   static final byte BYTE_G = 103;
/*     */   
/*     */   static final byte BYTE_L = 108;
/*     */   
/*     */   static final byte BYTE_M = 109;
/*     */   
/*     */   static final byte BYTE_O = 111;
/*     */   
/*     */   static final byte BYTE_P = 112;
/*     */   
/*     */   static final byte BYTE_Q = 113;
/*     */   
/*     */   static final byte BYTE_S = 115;
/*     */   
/*     */   static final byte BYTE_T = 116;
/*     */   
/*     */   static final byte BYTE_U = 117;
/*     */   
/*     */   static final byte BYTE_X = 120;
/*     */   
/*     */   private final OutputStream mOut;
/*     */   
/*     */   protected byte[] mOutputBuffer;
/*     */   
/*     */   protected int mOutputPtr;
/*     */   
/* 104 */   protected int mSurrogate = 0;
/*     */   
/*     */   public EncodingXmlWriter(OutputStream out, WriterConfig cfg, String encoding, boolean autoclose) throws IOException {
/* 116 */     super(cfg, encoding, autoclose);
/* 117 */     this.mOut = out;
/* 118 */     this.mOutputBuffer = cfg.allocFullBBuffer(4000);
/* 119 */     this.mOutputPtr = 0;
/*     */   }
/*     */   
/*     */   protected int getOutputPtr() {
/* 127 */     return this.mOutputPtr;
/*     */   }
/*     */   
/*     */   protected final OutputStream getOutputStream() {
/* 138 */     return this.mOut;
/*     */   }
/*     */   
/*     */   protected final Writer getWriter() {
/* 144 */     return null;
/*     */   }
/*     */   
/*     */   public void close(boolean forceRealClose) throws IOException {
/* 150 */     flush();
/* 153 */     byte[] buf = this.mOutputBuffer;
/* 154 */     if (buf != null) {
/* 155 */       this.mOutputBuffer = null;
/* 156 */       this.mConfig.freeFullBBuffer(buf);
/*     */     } 
/* 159 */     if (forceRealClose || this.mAutoCloseOutput)
/* 164 */       this.mOut.close(); 
/*     */   }
/*     */   
/*     */   public final void flush() throws IOException {
/* 171 */     flushBuffer();
/* 172 */     this.mOut.flush();
/*     */   }
/*     */   
/*     */   public abstract void writeRaw(char[] paramArrayOfchar, int paramInt1, int paramInt2) throws IOException;
/*     */   
/*     */   public abstract void writeRaw(String paramString, int paramInt1, int paramInt2) throws IOException;
/*     */   
/*     */   public final void writeCDataStart() throws IOException {
/* 191 */     writeAscii("<![CDATA[");
/*     */   }
/*     */   
/*     */   public final void writeCDataEnd() throws IOException {
/* 197 */     writeAscii("]]>");
/*     */   }
/*     */   
/*     */   public final void writeCommentStart() throws IOException {
/* 203 */     writeAscii("<!--");
/*     */   }
/*     */   
/*     */   public final void writeCommentEnd() throws IOException {
/* 209 */     writeAscii("-->");
/*     */   }
/*     */   
/*     */   public final void writePIStart(String target, boolean addSpace) throws IOException {
/* 215 */     writeAscii((byte)60, (byte)63);
/* 216 */     writeRaw(target);
/* 217 */     if (addSpace)
/* 218 */       writeAscii((byte)32); 
/*     */   }
/*     */   
/*     */   public final void writePIEnd() throws IOException {
/* 225 */     writeAscii((byte)63, (byte)62);
/*     */   }
/*     */   
/*     */   public int writeCData(String data) throws IOException {
/* 237 */     writeAscii("<![CDATA[");
/* 238 */     int ix = writeCDataContent(data);
/* 239 */     if (ix >= 0)
/* 240 */       return ix; 
/* 242 */     writeAscii("]]>");
/* 243 */     return -1;
/*     */   }
/*     */   
/*     */   public int writeCData(char[] cbuf, int offset, int len) throws IOException {
/* 249 */     writeAscii("<![CDATA[");
/* 250 */     int ix = writeCDataContent(cbuf, offset, len);
/* 251 */     if (ix >= 0)
/* 252 */       return ix; 
/* 254 */     writeAscii("]]>");
/* 255 */     return -1;
/*     */   }
/*     */   
/*     */   public final void writeCharacters(String data) throws IOException {
/* 262 */     if (this.mTextWriter != null) {
/* 263 */       this.mTextWriter.write(data);
/*     */     } else {
/* 265 */       writeTextContent(data);
/*     */     } 
/*     */   }
/*     */   
/*     */   public final void writeCharacters(char[] cbuf, int offset, int len) throws IOException {
/* 273 */     if (this.mTextWriter != null) {
/* 274 */       this.mTextWriter.write(cbuf, offset, len);
/*     */     } else {
/* 276 */       writeTextContent(cbuf, offset, len);
/*     */     } 
/*     */   }
/*     */   
/*     */   public int writeComment(String data) throws IOException {
/* 290 */     writeAscii("<!--");
/* 291 */     int ix = writeCommentContent(data);
/* 292 */     if (ix >= 0)
/* 293 */       return ix; 
/* 295 */     writeAscii("-->");
/* 296 */     return -1;
/*     */   }
/*     */   
/*     */   public void writeDTD(String data) throws IOException {
/* 302 */     if (this.mSurrogate != 0)
/* 303 */       throwUnpairedSurrogate(); 
/* 305 */     writeRaw(data, 0, data.length());
/*     */   }
/*     */   
/*     */   public void writeDTD(String rootName, String systemId, String publicId, String internalSubset) throws IOException, XMLStreamException {
/* 312 */     writeAscii("<!DOCTYPE ");
/* 313 */     writeAscii(rootName);
/* 314 */     if (systemId != null) {
/* 315 */       if (publicId != null) {
/* 316 */         writeAscii(" PUBLIC \"");
/* 317 */         writeRaw(publicId, 0, publicId.length());
/* 318 */         writeAscii("\" \"");
/*     */       } else {
/* 320 */         writeAscii(" SYSTEM \"");
/*     */       } 
/* 322 */       writeRaw(systemId, 0, systemId.length());
/* 323 */       writeAscii((byte)34);
/*     */     } 
/* 327 */     if (internalSubset != null && internalSubset.length() > 0) {
/* 328 */       writeAscii((byte)32, (byte)91);
/* 329 */       writeRaw(internalSubset, 0, internalSubset.length());
/* 330 */       writeAscii((byte)93);
/*     */     } 
/* 332 */     writeAscii((byte)62);
/*     */   }
/*     */   
/*     */   public void writeEntityReference(String name) throws IOException, XMLStreamException {
/* 338 */     if (this.mSurrogate != 0)
/* 339 */       throwUnpairedSurrogate(); 
/* 341 */     writeAscii((byte)38);
/* 342 */     writeName(name);
/* 343 */     writeAscii((byte)59);
/*     */   }
/*     */   
/*     */   public void writeXmlDeclaration(String version, String encoding, String standalone) throws IOException {
/* 349 */     writeAscii("<?xml version='");
/* 350 */     writeAscii(version);
/* 351 */     writeAscii((byte)39);
/* 353 */     if (encoding != null && encoding.length() > 0) {
/* 354 */       writeAscii(" encoding='");
/* 356 */       writeRaw(encoding, 0, encoding.length());
/* 357 */       writeAscii((byte)39);
/*     */     } 
/* 359 */     if (standalone != null) {
/* 360 */       writeAscii(" standalone='");
/* 361 */       writeAscii(standalone);
/* 362 */       writeAscii((byte)39);
/*     */     } 
/* 364 */     writeAscii((byte)63, (byte)62);
/*     */   }
/*     */   
/*     */   public int writePI(String target, String data) throws IOException, XMLStreamException {
/* 370 */     writeAscii((byte)60, (byte)63);
/* 371 */     writeName(target);
/* 372 */     if (data != null && data.length() > 0) {
/* 373 */       writeAscii((byte)32);
/* 374 */       int ix = writePIData(data);
/* 375 */       if (ix >= 0)
/* 376 */         return ix; 
/*     */     } 
/* 379 */     writeAscii((byte)63, (byte)62);
/* 380 */     return -1;
/*     */   }
/*     */   
/*     */   public void writeStartTagStart(String localName) throws IOException, XMLStreamException {
/* 392 */     writeAscii((byte)60);
/* 393 */     writeName(localName);
/*     */   }
/*     */   
/*     */   public void writeStartTagStart(String prefix, String localName) throws IOException, XMLStreamException {
/* 399 */     if (prefix == null || prefix.length() == 0) {
/* 400 */       writeStartTagStart(localName);
/*     */       return;
/*     */     } 
/* 403 */     writeAscii((byte)60);
/* 404 */     writeName(prefix);
/* 405 */     writeAscii((byte)58);
/* 406 */     writeName(localName);
/*     */   }
/*     */   
/*     */   public void writeStartTagEnd() throws IOException {
/* 412 */     writeAscii((byte)62);
/*     */   }
/*     */   
/*     */   public void writeStartTagEmptyEnd() throws IOException {
/* 418 */     if (this.mAddSpaceAfterEmptyElem) {
/* 419 */       writeAscii(" />");
/*     */     } else {
/* 421 */       writeAscii((byte)47, (byte)62);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void writeEndTag(String localName) throws IOException {
/* 428 */     writeAscii((byte)60, (byte)47);
/* 433 */     writeNameUnchecked(localName);
/* 434 */     writeAscii((byte)62);
/*     */   }
/*     */   
/*     */   public void writeEndTag(String prefix, String localName) throws IOException {
/* 440 */     writeAscii((byte)60, (byte)47);
/* 445 */     if (prefix != null && prefix.length() > 0) {
/* 446 */       writeNameUnchecked(prefix);
/* 447 */       writeAscii((byte)58);
/*     */     } 
/* 449 */     writeNameUnchecked(localName);
/* 450 */     writeAscii((byte)62);
/*     */   }
/*     */   
/*     */   public void writeAttribute(String localName, String value) throws IOException, XMLStreamException {
/* 462 */     writeAscii((byte)32);
/* 463 */     writeName(localName);
/* 464 */     writeAscii((byte)61, (byte)34);
/* 466 */     int len = value.length();
/* 467 */     if (len > 0)
/* 468 */       if (this.mAttrValueWriter != null) {
/* 469 */         this.mAttrValueWriter.write(value, 0, len);
/*     */       } else {
/* 471 */         writeAttrValue(value);
/*     */       }  
/* 474 */     writeAscii((byte)34);
/*     */   }
/*     */   
/*     */   public void writeAttribute(String localName, char[] value, int offset, int len) throws IOException, XMLStreamException {
/* 480 */     writeAscii((byte)32);
/* 481 */     writeName(localName);
/* 482 */     writeAscii((byte)61, (byte)34);
/* 484 */     if (len > 0)
/* 485 */       if (this.mAttrValueWriter != null) {
/* 486 */         this.mAttrValueWriter.write(value, offset, len);
/*     */       } else {
/* 488 */         writeAttrValue(value, offset, len);
/*     */       }  
/* 491 */     writeAscii((byte)34);
/*     */   }
/*     */   
/*     */   public void writeAttribute(String prefix, String localName, String value) throws IOException, XMLStreamException {
/* 497 */     writeAscii((byte)32);
/* 498 */     writeName(prefix);
/* 499 */     writeAscii((byte)58);
/* 500 */     writeName(localName);
/* 501 */     writeAscii((byte)61, (byte)34);
/* 503 */     int len = value.length();
/* 504 */     if (len > 0)
/* 505 */       if (this.mAttrValueWriter != null) {
/* 506 */         this.mAttrValueWriter.write(value, 0, len);
/*     */       } else {
/* 508 */         writeAttrValue(value);
/*     */       }  
/* 511 */     writeAscii((byte)34);
/*     */   }
/*     */   
/*     */   public void writeAttribute(String prefix, String localName, char[] value, int offset, int len) throws IOException, XMLStreamException {
/* 517 */     writeAscii((byte)32);
/* 518 */     writeName(prefix);
/* 519 */     writeAscii((byte)58);
/* 520 */     writeName(localName);
/* 521 */     writeAscii((byte)61, (byte)34);
/* 523 */     if (len > 0)
/* 524 */       if (this.mAttrValueWriter != null) {
/* 525 */         this.mAttrValueWriter.write(value, offset, len);
/*     */       } else {
/* 527 */         writeAttrValue(value, offset, len);
/*     */       }  
/* 530 */     writeAscii((byte)34);
/*     */   }
/*     */   
/*     */   public final void writeTypedElement(AsciiValueEncoder enc) throws IOException {
/* 545 */     if (this.mSurrogate != 0)
/* 546 */       throwUnpairedSurrogate(); 
/* 548 */     if (enc.bufferNeedsFlush(this.mOutputBuffer.length - this.mOutputPtr))
/* 549 */       flush(); 
/*     */     while (true) {
/* 552 */       this.mOutputPtr = enc.encodeMore(this.mOutputBuffer, this.mOutputPtr, this.mOutputBuffer.length);
/* 554 */       if (enc.isCompleted())
/*     */         break; 
/* 557 */       flush();
/*     */     } 
/*     */   }
/*     */   
/*     */   public final void writeTypedElement(AsciiValueEncoder enc, XMLValidator validator, char[] copyBuffer) throws IOException, XMLStreamException {
/* 568 */     if (this.mSurrogate != 0)
/* 569 */       throwUnpairedSurrogate(); 
/* 576 */     int copyBufferLen = copyBuffer.length;
/*     */     do {
/* 580 */       int ptr = enc.encodeMore(copyBuffer, 0, copyBufferLen);
/* 583 */       validator.validateText(copyBuffer, 0, ptr, false);
/* 584 */       writeRawAscii(copyBuffer, 0, ptr);
/* 585 */     } while (!enc.isCompleted());
/*     */   }
/*     */   
/*     */   public void writeTypedAttribute(String localName, AsciiValueEncoder enc) throws IOException, XMLStreamException {
/* 591 */     writeAscii((byte)32);
/* 592 */     writeName(localName);
/* 593 */     writeAscii((byte)61, (byte)34);
/* 595 */     if (enc.bufferNeedsFlush(this.mOutputBuffer.length - this.mOutputPtr))
/* 596 */       flush(); 
/*     */     while (true) {
/* 599 */       this.mOutputPtr = enc.encodeMore(this.mOutputBuffer, this.mOutputPtr, this.mOutputBuffer.length);
/* 600 */       if (enc.isCompleted())
/*     */         break; 
/* 603 */       flush();
/*     */     } 
/* 605 */     writeAscii((byte)34);
/*     */   }
/*     */   
/*     */   public void writeTypedAttribute(String prefix, String localName, AsciiValueEncoder enc) throws IOException, XMLStreamException {
/* 612 */     writeAscii((byte)32);
/* 613 */     writeName(prefix);
/* 614 */     writeAscii((byte)58);
/* 615 */     writeName(localName);
/* 616 */     writeAscii((byte)61, (byte)34);
/* 618 */     if (enc.bufferNeedsFlush(this.mOutputBuffer.length - this.mOutputPtr))
/* 619 */       flush(); 
/*     */     while (true) {
/* 622 */       this.mOutputPtr = enc.encodeMore(this.mOutputBuffer, this.mOutputPtr, this.mOutputBuffer.length);
/* 623 */       if (enc.isCompleted())
/*     */         break; 
/* 626 */       flush();
/*     */     } 
/* 628 */     writeAscii((byte)34);
/*     */   }
/*     */   
/*     */   public void writeTypedAttribute(String prefix, String localName, String nsURI, AsciiValueEncoder enc, XMLValidator validator, char[] copyBuffer) throws IOException, XMLStreamException {
/* 636 */     boolean hasPrefix = (prefix != null && prefix.length() > 0);
/* 637 */     if (nsURI == null)
/* 638 */       nsURI = ""; 
/* 642 */     writeAscii((byte)32);
/* 643 */     if (hasPrefix) {
/* 644 */       writeName(prefix);
/* 645 */       writeAscii((byte)58);
/*     */     } 
/* 647 */     writeName(localName);
/* 648 */     writeAscii((byte)61, (byte)34);
/* 657 */     int copyBufferLen = copyBuffer.length;
/* 660 */     int last = enc.encodeMore(copyBuffer, 0, copyBufferLen);
/* 661 */     writeRawAscii(copyBuffer, 0, last);
/* 662 */     if (enc.isCompleted()) {
/* 663 */       validator.validateAttribute(localName, nsURI, prefix, copyBuffer, 0, last);
/*     */       return;
/*     */     } 
/* 668 */     StringBuffer sb = new StringBuffer(copyBufferLen << 1);
/* 669 */     sb.append(copyBuffer, 0, last);
/*     */     do {
/* 671 */       last = enc.encodeMore(copyBuffer, 0, copyBufferLen);
/* 672 */       writeRawAscii(copyBuffer, 0, last);
/* 673 */       sb.append(copyBuffer, 0, last);
/* 674 */     } while (!enc.isCompleted());
/* 676 */     writeAscii((byte)34);
/* 679 */     String valueStr = sb.toString();
/* 680 */     validator.validateAttribute(localName, nsURI, prefix, valueStr);
/*     */   }
/*     */   
/*     */   protected final void flushBuffer() throws IOException {
/* 694 */     if (this.mOutputPtr > 0 && this.mOutputBuffer != null) {
/* 695 */       int ptr = this.mOutputPtr;
/* 696 */       this.mOutputPtr = 0;
/* 697 */       this.mOut.write(this.mOutputBuffer, 0, ptr);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected final void writeAscii(byte b) throws IOException {
/* 704 */     if (this.mSurrogate != 0)
/* 705 */       throwUnpairedSurrogate(); 
/* 707 */     if (this.mOutputPtr >= this.mOutputBuffer.length)
/* 708 */       flushBuffer(); 
/* 710 */     this.mOutputBuffer[this.mOutputPtr++] = b;
/*     */   }
/*     */   
/*     */   protected final void writeAscii(byte b1, byte b2) throws IOException {
/* 716 */     if (this.mSurrogate != 0)
/* 717 */       throwUnpairedSurrogate(); 
/* 719 */     if (this.mOutputPtr + 1 >= this.mOutputBuffer.length)
/* 720 */       flushBuffer(); 
/* 722 */     this.mOutputBuffer[this.mOutputPtr++] = b1;
/* 723 */     this.mOutputBuffer[this.mOutputPtr++] = b2;
/*     */   }
/*     */   
/*     */   protected final void writeAscii(String str) throws IOException {
/* 729 */     if (this.mSurrogate != 0)
/* 730 */       throwUnpairedSurrogate(); 
/* 733 */     int len = str.length();
/* 734 */     int ptr = this.mOutputPtr;
/* 735 */     byte[] buf = this.mOutputBuffer;
/* 736 */     if (ptr + len >= buf.length) {
/* 741 */       if (len > buf.length) {
/* 742 */         writeRaw(str, 0, len);
/*     */         return;
/*     */       } 
/* 745 */       flushBuffer();
/* 746 */       ptr = this.mOutputPtr;
/*     */     } 
/* 748 */     this.mOutputPtr += len;
/* 749 */     for (int i = 0; i < len; i++)
/* 750 */       buf[ptr++] = (byte)str.charAt(i); 
/*     */   }
/*     */   
/*     */   public final void writeRawAscii(char[] buf, int offset, int len) throws IOException {
/* 757 */     if (this.mSurrogate != 0)
/* 758 */       throwUnpairedSurrogate(); 
/* 760 */     int ptr = this.mOutputPtr;
/* 761 */     byte[] dst = this.mOutputBuffer;
/* 762 */     if (ptr + len >= dst.length) {
/* 763 */       if (len > dst.length) {
/* 764 */         writeRaw(buf, offset, len);
/*     */         return;
/*     */       } 
/* 767 */       flushBuffer();
/* 768 */       ptr = this.mOutputPtr;
/*     */     } 
/* 770 */     this.mOutputPtr += len;
/* 771 */     for (int i = 0; i < len; i++)
/* 772 */       dst[ptr + i] = (byte)buf[offset + i]; 
/*     */   }
/*     */   
/*     */   protected final int writeAsEntity(int c) throws IOException {
/* 785 */     byte[] buf = this.mOutputBuffer;
/* 786 */     int ptr = this.mOutputPtr;
/* 787 */     if (ptr + 10 >= buf.length) {
/* 788 */       flushBuffer();
/* 789 */       ptr = this.mOutputPtr;
/*     */     } 
/* 791 */     buf[ptr++] = 38;
/* 794 */     if (c < 256) {
/* 798 */       if (c == 38) {
/* 799 */         buf[ptr++] = 97;
/* 800 */         buf[ptr++] = 109;
/* 801 */         buf[ptr++] = 112;
/* 802 */       } else if (c == 60) {
/* 803 */         buf[ptr++] = 108;
/* 804 */         buf[ptr++] = 116;
/* 805 */       } else if (c == 62) {
/* 806 */         buf[ptr++] = 103;
/* 807 */         buf[ptr++] = 116;
/* 808 */       } else if (c == 39) {
/* 809 */         buf[ptr++] = 97;
/* 810 */         buf[ptr++] = 112;
/* 811 */         buf[ptr++] = 111;
/* 812 */         buf[ptr++] = 115;
/* 813 */       } else if (c == 34) {
/* 814 */         buf[ptr++] = 113;
/* 815 */         buf[ptr++] = 117;
/* 816 */         buf[ptr++] = 111;
/* 817 */         buf[ptr++] = 116;
/*     */       } else {
/* 819 */         buf[ptr++] = 35;
/* 820 */         buf[ptr++] = 120;
/* 822 */         if (c >= 16) {
/* 823 */           int digit = c >> 4;
/* 824 */           buf[ptr++] = (byte)((digit < 10) ? (48 + digit) : (87 + digit));
/* 825 */           c &= 0xF;
/*     */         } 
/* 827 */         buf[ptr++] = (byte)((c < 10) ? (48 + c) : (87 + c));
/*     */       } 
/*     */     } else {
/* 830 */       buf[ptr++] = 35;
/* 831 */       buf[ptr++] = 120;
/* 834 */       int shift = 20;
/* 835 */       int origPtr = ptr;
/*     */       while (true) {
/* 838 */         int digit = c >> shift & 0xF;
/* 839 */         if (digit > 0 || ptr != origPtr)
/* 840 */           buf[ptr++] = (byte)((digit < 10) ? (48 + digit) : (87 + digit)); 
/* 842 */         shift -= 4;
/* 843 */         if (shift <= 0) {
/* 844 */           c &= 0xF;
/* 845 */           buf[ptr++] = (byte)((c < 10) ? (48 + c) : (87 + c));
/* 847 */           buf[ptr++] = 59;
/* 848 */           this.mOutputPtr = ptr;
/* 849 */           return ptr;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     buf[ptr++] = 59;
/*     */     this.mOutputPtr = ptr;
/* 849 */     return ptr;
/*     */   }
/*     */   
/*     */   protected final void writeName(String name) throws IOException, XMLStreamException {
/* 855 */     if (this.mCheckNames)
/* 856 */       verifyNameValidity(name, this.mNsAware); 
/* 859 */     writeRaw(name, 0, name.length());
/*     */   }
/*     */   
/*     */   protected final void writeNameUnchecked(String name) throws IOException {
/* 865 */     writeRaw(name, 0, name.length());
/*     */   }
/*     */   
/*     */   protected final int calcSurrogate(int secondSurr) throws IOException {
/* 872 */     int firstSurr = this.mSurrogate;
/* 873 */     this.mSurrogate = 0;
/* 874 */     if (firstSurr < 55296 || firstSurr > 56319)
/* 875 */       throwUnpairedSurrogate(firstSurr); 
/* 879 */     if (secondSurr < 56320 || secondSurr > 57343)
/* 880 */       throwUnpairedSurrogate(secondSurr); 
/* 882 */     int ch = 65536 + (firstSurr - 55296 << 10) + secondSurr - 56320;
/* 883 */     if (ch > 1114111)
/* 884 */       throw new IOException("Illegal surrogate character pair, resulting code 0x" + Integer.toHexString(ch) + " above legal XML character range"); 
/* 886 */     return ch;
/*     */   }
/*     */   
/*     */   protected final void throwUnpairedSurrogate() throws IOException {
/* 892 */     int surr = this.mSurrogate;
/* 893 */     this.mSurrogate = 0;
/* 894 */     throwUnpairedSurrogate(surr);
/*     */   }
/*     */   
/*     */   protected final void throwUnpairedSurrogate(int code) throws IOException {
/* 901 */     flush();
/* 902 */     throw new IOException("Unpaired surrogate character (0x" + Integer.toHexString(code) + ")");
/*     */   }
/*     */   
/*     */   protected abstract void writeAttrValue(String paramString) throws IOException;
/*     */   
/*     */   protected abstract void writeAttrValue(char[] paramArrayOfchar, int paramInt1, int paramInt2) throws IOException;
/*     */   
/*     */   protected abstract int writeCDataContent(String paramString) throws IOException;
/*     */   
/*     */   protected abstract int writeCDataContent(char[] paramArrayOfchar, int paramInt1, int paramInt2) throws IOException;
/*     */   
/*     */   protected abstract int writeCommentContent(String paramString) throws IOException;
/*     */   
/*     */   protected abstract int writePIData(String paramString) throws IOException, XMLStreamException;
/*     */   
/*     */   protected abstract void writeTextContent(String paramString) throws IOException;
/*     */   
/*     */   protected abstract void writeTextContent(char[] paramArrayOfchar, int paramInt1, int paramInt2) throws IOException;
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\sw\EncodingXmlWriter.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */