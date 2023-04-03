/*     */ package com.ctc.wstx.io;
/*     */ 
/*     */ import com.ctc.wstx.api.ReaderConfig;
/*     */ import java.io.CharConversionException;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ 
/*     */ public final class UTF8Reader extends BaseReader {
/*     */   boolean mXml11 = false;
/*     */   
/*  34 */   char mSurrogate = Character.MIN_VALUE;
/*     */   
/*  39 */   int mCharCount = 0;
/*     */   
/*  44 */   int mByteCount = 0;
/*     */   
/*     */   public UTF8Reader(ReaderConfig cfg, InputStream in, byte[] buf, int ptr, int len, boolean recycleBuffer) {
/*  55 */     super(cfg, in, buf, ptr, len, recycleBuffer);
/*     */   }
/*     */   
/*     */   public void setXmlCompliancy(int xmlVersion) {
/*  60 */     this.mXml11 = (xmlVersion == 272);
/*     */   }
/*     */   
/*     */   public int read(char[] cbuf, int start, int len) throws IOException {
/*  73 */     if (start < 0 || start + len > cbuf.length)
/*  74 */       reportBounds(cbuf, start, len); 
/*  77 */     if (this.mByteBuffer == null)
/*  78 */       return -1; 
/*  80 */     if (len < 1)
/*  81 */       return 0; 
/*  84 */     len += start;
/*  85 */     int outPtr = start;
/*  88 */     if (this.mSurrogate != '\000') {
/*  89 */       cbuf[outPtr++] = this.mSurrogate;
/*  90 */       this.mSurrogate = Character.MIN_VALUE;
/*     */     } else {
/*  96 */       int left = this.mByteBufferEnd - this.mBytePtr;
/* 108 */       if (left < 4)
/* 110 */         if ((left < 1 || this.mByteBuffer[this.mBytePtr] < 0) && 
/* 111 */           !loadMore(left))
/* 112 */           return -1;  
/*     */     } 
/* 122 */     byte[] buf = this.mByteBuffer;
/* 123 */     int inPtr = this.mBytePtr;
/* 124 */     int inBufLen = this.mByteBufferEnd;
/* 127 */     label104: while (outPtr < len) {
/* 129 */       int needed, c = buf[inPtr++];
/* 134 */       if (c >= 0) {
/* 135 */         if (c == 127 && this.mXml11) {
/* 136 */           int bytePos = this.mByteCount + inPtr - 1;
/* 137 */           int charPos = this.mCharCount + outPtr - start;
/* 138 */           reportInvalidXml11(c, bytePos, charPos);
/*     */         } 
/* 140 */         cbuf[outPtr++] = (char)c;
/* 146 */         int outMax = len - outPtr;
/* 147 */         int inMax = inBufLen - inPtr;
/* 148 */         int inEnd = inPtr + ((inMax < outMax) ? inMax : outMax);
/* 152 */         while (inPtr < inEnd) {
/* 155 */           c = buf[inPtr++] & 0xFF;
/* 156 */           if (c >= 127) {
/* 161 */             if (c == 127) {
/* 162 */               if (this.mXml11) {
/* 163 */                 int bytePos = this.mByteCount + inPtr - 1;
/* 164 */                 int charPos = this.mCharCount + outPtr - start;
/* 165 */                 reportInvalidXml11(c, bytePos, charPos);
/*     */               } 
/* 167 */               cbuf[outPtr++] = (char)c;
/* 168 */               if (inPtr >= inEnd)
/*     */                 break; 
/*     */               continue;
/*     */             } 
/*     */             continue label104;
/*     */           } 
/*     */           cbuf[outPtr++] = (char)c;
/*     */         } 
/*     */         break;
/*     */       } 
/* 178 */       if ((c & 0xE0) == 192) {
/* 179 */         c &= 0x1F;
/* 180 */         needed = 1;
/* 181 */       } else if ((c & 0xF0) == 224) {
/* 182 */         c &= 0xF;
/* 183 */         needed = 2;
/* 184 */       } else if ((c & 0xF8) == 240) {
/* 186 */         c &= 0xF;
/* 187 */         needed = 3;
/*     */       } else {
/* 189 */         reportInvalidInitial(c & 0xFF, outPtr - start);
/* 191 */         needed = 1;
/*     */       } 
/* 198 */       if (inBufLen - inPtr < needed) {
/* 199 */         inPtr--;
/*     */         break;
/*     */       } 
/* 203 */       int d = buf[inPtr++];
/* 204 */       if ((d & 0xC0) != 128)
/* 205 */         reportInvalidOther(d & 0xFF, outPtr - start); 
/* 207 */       c = c << 6 | d & 0x3F;
/* 209 */       if (needed > 1) {
/* 210 */         d = buf[inPtr++];
/* 211 */         if ((d & 0xC0) != 128)
/* 212 */           reportInvalidOther(d & 0xFF, outPtr - start); 
/* 214 */         c = c << 6 | d & 0x3F;
/* 215 */         if (needed > 2) {
/* 216 */           d = buf[inPtr++];
/* 217 */           if ((d & 0xC0) != 128)
/* 218 */             reportInvalidOther(d & 0xFF, outPtr - start); 
/* 220 */           c = c << 6 | d & 0x3F;
/* 221 */           if (c > 1114111)
/* 222 */             reportInvalid(c, outPtr - start, "(above " + Integer.toHexString(1114111) + ") "); 
/* 229 */           c -= 65536;
/* 230 */           cbuf[outPtr++] = (char)(55296 + (c >> 10));
/* 232 */           c = 0xDC00 | c & 0x3FF;
/* 235 */           if (outPtr >= len) {
/* 236 */             this.mSurrogate = (char)c;
/*     */             break;
/*     */           } 
/* 245 */         } else if (c >= 55296) {
/* 247 */           if (c < 57344) {
/* 248 */             reportInvalid(c, outPtr - start, "(a surrogate character) ");
/* 249 */           } else if (c >= 65534) {
/* 250 */             reportInvalid(c, outPtr - start, "");
/*     */           } 
/* 252 */         } else if (this.mXml11 && c == 8232) {
/* 259 */           if (outPtr > start && cbuf[outPtr - 1] == '\r')
/* 260 */             cbuf[outPtr - 1] = '\n'; 
/* 262 */           c = 10;
/*     */         } 
/* 266 */       } else if (this.mXml11 && 
/* 267 */         c <= 159) {
/* 268 */         if (c == 133) {
/* 269 */           c = 10;
/* 270 */         } else if (c >= 127) {
/* 271 */           int bytePos = this.mByteCount + inPtr - 1;
/* 272 */           int charPos = this.mCharCount + outPtr - start;
/* 273 */           reportInvalidXml11(c, bytePos, charPos);
/*     */         } 
/*     */       } 
/* 278 */       cbuf[outPtr++] = (char)c;
/* 279 */       if (inPtr >= inBufLen)
/*     */         break; 
/*     */     } 
/* 284 */     this.mBytePtr = inPtr;
/* 285 */     len = outPtr - start;
/* 286 */     this.mCharCount += len;
/* 287 */     return len;
/*     */   }
/*     */   
/*     */   private void reportInvalidInitial(int mask, int offset) throws IOException {
/* 300 */     int bytePos = this.mByteCount + this.mBytePtr - 1;
/* 301 */     int charPos = this.mCharCount + offset + 1;
/* 303 */     throw new CharConversionException("Invalid UTF-8 start byte 0x" + Integer.toHexString(mask) + " (at char #" + charPos + ", byte #" + bytePos + ")");
/*     */   }
/*     */   
/*     */   private void reportInvalidOther(int mask, int offset) throws IOException {
/* 311 */     int bytePos = this.mByteCount + this.mBytePtr - 1;
/* 312 */     int charPos = this.mCharCount + offset;
/* 314 */     throw new CharConversionException("Invalid UTF-8 middle byte 0x" + Integer.toHexString(mask) + " (at char #" + charPos + ", byte #" + bytePos + ")");
/*     */   }
/*     */   
/*     */   private void reportUnexpectedEOF(int gotBytes, int needed) throws IOException {
/* 322 */     int bytePos = this.mByteCount + gotBytes;
/* 323 */     int charPos = this.mCharCount;
/* 325 */     throw new CharConversionException("Unexpected EOF in the middle of a multi-byte char: got " + gotBytes + ", needed " + needed + ", at char #" + charPos + ", byte #" + bytePos + ")");
/*     */   }
/*     */   
/*     */   private void reportInvalid(int value, int offset, String msg) throws IOException {
/* 333 */     int bytePos = this.mByteCount + this.mBytePtr - 1;
/* 334 */     int charPos = this.mCharCount + offset;
/* 336 */     throw new CharConversionException("Invalid UTF-8 character 0x" + Integer.toHexString(value) + msg + " at char #" + charPos + ", byte #" + bytePos + ")");
/*     */   }
/*     */   
/*     */   private boolean loadMore(int available) throws IOException {
/*     */     int needed;
/* 350 */     this.mByteCount += this.mByteBufferEnd - available;
/* 353 */     if (available > 0) {
/* 357 */       if (this.mBytePtr > 0 && canModifyBuffer()) {
/* 358 */         for (int i = 0; i < available; i++)
/* 359 */           this.mByteBuffer[i] = this.mByteBuffer[this.mBytePtr + i]; 
/* 361 */         this.mBytePtr = 0;
/* 362 */         this.mByteBufferEnd = available;
/*     */       } 
/*     */     } else {
/* 368 */       int count = readBytes();
/* 369 */       if (count < 1) {
/* 370 */         if (count < 0) {
/* 371 */           freeBuffers();
/* 372 */           return false;
/*     */         } 
/* 375 */         reportStrangeStream();
/*     */       } 
/*     */     } 
/* 382 */     int c = this.mByteBuffer[this.mBytePtr];
/* 383 */     if (c >= 0)
/* 384 */       return true; 
/* 389 */     if ((c & 0xE0) == 192) {
/* 390 */       needed = 2;
/* 391 */     } else if ((c & 0xF0) == 224) {
/* 392 */       needed = 3;
/* 393 */     } else if ((c & 0xF8) == 240) {
/* 395 */       needed = 4;
/*     */     } else {
/* 397 */       reportInvalidInitial(c & 0xFF, 0);
/* 399 */       needed = 1;
/*     */     } 
/* 406 */     while (this.mBytePtr + needed > this.mByteBufferEnd) {
/* 407 */       int count = readBytesAt(this.mByteBufferEnd);
/* 408 */       if (count < 1) {
/* 409 */         if (count < 0) {
/* 410 */           freeBuffers();
/* 411 */           reportUnexpectedEOF(this.mByteBufferEnd, needed);
/*     */         } 
/* 414 */         reportStrangeStream();
/*     */       } 
/*     */     } 
/* 417 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\io\UTF8Reader.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */