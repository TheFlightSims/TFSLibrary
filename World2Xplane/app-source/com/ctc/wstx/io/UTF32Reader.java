/*     */ package com.ctc.wstx.io;
/*     */ 
/*     */ import com.ctc.wstx.api.ReaderConfig;
/*     */ import java.io.CharConversionException;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ 
/*     */ public final class UTF32Reader extends BaseReader {
/*     */   final boolean mBigEndian;
/*     */   
/*     */   boolean mXml11;
/*     */   
/*  39 */   char mSurrogate = Character.MIN_VALUE;
/*     */   
/*  44 */   int mCharCount = 0;
/*     */   
/*  49 */   int mByteCount = 0;
/*     */   
/*     */   public UTF32Reader(ReaderConfig cfg, InputStream in, byte[] buf, int ptr, int len, boolean recycleBuffer, boolean isBigEndian) {
/*  61 */     super(cfg, in, buf, ptr, len, recycleBuffer);
/*  62 */     this.mBigEndian = isBigEndian;
/*     */   }
/*     */   
/*     */   public void setXmlCompliancy(int xmlVersion) {
/*  67 */     this.mXml11 = (xmlVersion == 272);
/*     */   }
/*     */   
/*     */   public int read(char[] cbuf, int start, int len) throws IOException {
/*  80 */     if (start < 0 || start + len > cbuf.length)
/*  81 */       reportBounds(cbuf, start, len); 
/*  84 */     if (this.mByteBuffer == null)
/*  85 */       return -1; 
/*  87 */     if (len < 1)
/*  88 */       return 0; 
/*  91 */     len += start;
/*  92 */     int outPtr = start;
/*  95 */     if (this.mSurrogate != '\000') {
/*  96 */       cbuf[outPtr++] = this.mSurrogate;
/*  97 */       this.mSurrogate = Character.MIN_VALUE;
/*     */     } else {
/* 103 */       int left = this.mByteBufferEnd - this.mBytePtr;
/* 104 */       if (left < 4 && 
/* 105 */         !loadMore(left))
/* 106 */         return -1; 
/*     */     } 
/* 111 */     byte[] buf = this.mByteBuffer;
/* 114 */     while (outPtr < len) {
/* 115 */       int ch, ptr = this.mBytePtr;
/* 118 */       if (this.mBigEndian) {
/* 119 */         ch = buf[ptr] << 24 | (buf[ptr + 1] & 0xFF) << 16 | (buf[ptr + 2] & 0xFF) << 8 | buf[ptr + 3] & 0xFF;
/*     */       } else {
/* 122 */         ch = buf[ptr] & 0xFF | (buf[ptr + 1] & 0xFF) << 8 | (buf[ptr + 2] & 0xFF) << 16 | buf[ptr + 3] << 24;
/*     */       } 
/* 125 */       this.mBytePtr += 4;
/* 129 */       if (ch >= 127)
/* 130 */         if (ch <= 159) {
/* 131 */           if (this.mXml11) {
/* 132 */             if (ch != 133)
/* 133 */               reportInvalid(ch, outPtr - start, "(can only be included via entity in xml 1.1)"); 
/* 135 */             ch = 10;
/*     */           } 
/* 137 */         } else if (ch >= 55296) {
/* 139 */           if (ch > 1114111)
/* 140 */             reportInvalid(ch, outPtr - start, "(above " + Integer.toHexString(1114111) + ") "); 
/* 143 */           if (ch > 65535) {
/* 144 */             ch -= 65536;
/* 145 */             cbuf[outPtr++] = (char)(55296 + (ch >> 10));
/* 147 */             ch = 0xDC00 | ch & 0x3FF;
/* 149 */             if (outPtr >= len) {
/* 150 */               this.mSurrogate = (char)ch;
/*     */               break;
/*     */             } 
/* 154 */           } else if (ch < 57344) {
/* 155 */             reportInvalid(ch, outPtr - start, "(a surrogate char) ");
/* 156 */           } else if (ch >= 65534) {
/* 157 */             reportInvalid(ch, outPtr - start, "");
/*     */           } 
/* 160 */         } else if (ch == 8232 && this.mXml11) {
/* 161 */           ch = 10;
/*     */         }  
/* 164 */       cbuf[outPtr++] = (char)ch;
/* 165 */       if (this.mBytePtr >= this.mByteBufferEnd)
/*     */         break; 
/*     */     } 
/* 170 */     len = outPtr - start;
/* 171 */     this.mCharCount += len;
/* 172 */     return len;
/*     */   }
/*     */   
/*     */   private void reportUnexpectedEOF(int gotBytes, int needed) throws IOException {
/* 184 */     int bytePos = this.mByteCount + gotBytes;
/* 185 */     int charPos = this.mCharCount;
/* 187 */     throw new CharConversionException("Unexpected EOF in the middle of a 4-byte UTF-32 char: got " + gotBytes + ", needed " + needed + ", at char #" + charPos + ", byte #" + bytePos + ")");
/*     */   }
/*     */   
/*     */   private void reportInvalid(int value, int offset, String msg) throws IOException {
/* 195 */     int bytePos = this.mByteCount + this.mBytePtr - 1;
/* 196 */     int charPos = this.mCharCount + offset;
/* 198 */     throw new CharConversionException("Invalid UTF-32 character 0x" + Integer.toHexString(value) + msg + " at char #" + charPos + ", byte #" + bytePos + ")");
/*     */   }
/*     */   
/*     */   private boolean loadMore(int available) throws IOException {
/* 212 */     this.mByteCount += this.mByteBufferEnd - available;
/* 215 */     if (available > 0) {
/* 219 */       if (this.mBytePtr > 0 && canModifyBuffer()) {
/* 220 */         for (int i = 0; i < available; i++)
/* 221 */           this.mByteBuffer[i] = this.mByteBuffer[this.mBytePtr + i]; 
/* 223 */         this.mBytePtr = 0;
/* 224 */         this.mByteBufferEnd = available;
/*     */       } 
/*     */     } else {
/* 230 */       int count = readBytes();
/* 231 */       if (count < 1) {
/* 232 */         if (count < 0) {
/* 233 */           freeBuffers();
/* 234 */           return false;
/*     */         } 
/* 237 */         reportStrangeStream();
/*     */       } 
/*     */     } 
/* 244 */     while (this.mByteBufferEnd < 4) {
/* 245 */       int count = readBytesAt(this.mByteBufferEnd);
/* 246 */       if (count < 1) {
/* 247 */         if (count < 0) {
/* 248 */           freeBuffers();
/* 249 */           reportUnexpectedEOF(this.mByteBufferEnd, 4);
/*     */         } 
/* 252 */         reportStrangeStream();
/*     */       } 
/*     */     } 
/* 255 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\io\UTF32Reader.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */