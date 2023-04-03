/*     */ package com.ctc.wstx.io;
/*     */ 
/*     */ import com.ctc.wstx.api.WriterConfig;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.io.Writer;
/*     */ 
/*     */ public final class UTF8Writer extends Writer implements CompletelyCloseable {
/*     */   private static final int DEFAULT_BUF_LEN = 4000;
/*     */   
/*     */   static final int SURR1_FIRST = 55296;
/*     */   
/*     */   static final int SURR1_LAST = 56319;
/*     */   
/*     */   static final int SURR2_FIRST = 56320;
/*     */   
/*     */   static final int SURR2_LAST = 57343;
/*     */   
/*     */   final WriterConfig mConfig;
/*     */   
/*     */   final boolean mAutoCloseOutput;
/*     */   
/*     */   final OutputStream mOut;
/*     */   
/*     */   byte[] mOutBuffer;
/*     */   
/*     */   final int mOutBufferLast;
/*     */   
/*     */   int mOutPtr;
/*     */   
/*  43 */   int mSurrogate = 0;
/*     */   
/*     */   public UTF8Writer(WriterConfig cfg, OutputStream out, boolean autoclose) {
/*  47 */     this.mConfig = cfg;
/*  48 */     this.mAutoCloseOutput = autoclose;
/*  49 */     this.mOut = out;
/*  50 */     this.mOutBuffer = (this.mConfig == null) ? new byte[4000] : cfg.allocFullBBuffer(4000);
/*  55 */     this.mOutBufferLast = this.mOutBuffer.length - 4;
/*  56 */     this.mOutPtr = 0;
/*     */   }
/*     */   
/*     */   public void closeCompletely() throws IOException {
/*  67 */     _close(true);
/*     */   }
/*     */   
/*     */   public void close() throws IOException {
/*  93 */     _close(this.mAutoCloseOutput);
/*     */   }
/*     */   
/*     */   public void flush() throws IOException {
/*  99 */     if (this.mOutPtr > 0 && this.mOutBuffer != null) {
/* 100 */       this.mOut.write(this.mOutBuffer, 0, this.mOutPtr);
/* 101 */       this.mOutPtr = 0;
/*     */     } 
/* 103 */     this.mOut.flush();
/*     */   }
/*     */   
/*     */   public void write(char[] cbuf) throws IOException {
/* 109 */     write(cbuf, 0, cbuf.length);
/*     */   }
/*     */   
/*     */   public void write(char[] cbuf, int off, int len) throws IOException {
/* 115 */     if (len < 2) {
/* 116 */       if (len == 1)
/* 117 */         write(cbuf[off]); 
/*     */       return;
/*     */     } 
/* 123 */     if (this.mSurrogate > 0) {
/* 124 */       char second = cbuf[off++];
/* 125 */       len--;
/* 126 */       write(_convertSurrogate(second));
/*     */     } 
/* 130 */     int outPtr = this.mOutPtr;
/* 131 */     byte[] outBuf = this.mOutBuffer;
/* 132 */     int outBufLast = this.mOutBufferLast;
/* 135 */     len += off;
/* 138 */     label45: while (off < len) {
/* 142 */       if (outPtr >= outBufLast) {
/* 143 */         this.mOut.write(outBuf, 0, outPtr);
/* 144 */         outPtr = 0;
/*     */       } 
/* 147 */       int c = cbuf[off++];
/* 149 */       if (c < 128) {
/* 150 */         outBuf[outPtr++] = (byte)c;
/* 152 */         int maxInCount = len - off;
/* 153 */         int maxOutCount = outBufLast - outPtr;
/* 155 */         if (maxInCount > maxOutCount)
/* 156 */           maxInCount = maxOutCount; 
/* 158 */         maxInCount += off;
/* 161 */         while (off < maxInCount) {
/* 164 */           c = cbuf[off++];
/* 165 */           if (c >= 128)
/*     */             continue label45; 
/* 168 */           outBuf[outPtr++] = (byte)c;
/*     */         } 
/*     */         continue;
/*     */       } 
/* 173 */       if (c < 2048) {
/* 174 */         outBuf[outPtr++] = (byte)(0xC0 | c >> 6);
/* 175 */         outBuf[outPtr++] = (byte)(0x80 | c & 0x3F);
/*     */         continue;
/*     */       } 
/* 178 */       if (c < 55296 || c > 57343) {
/* 179 */         outBuf[outPtr++] = (byte)(0xE0 | c >> 12);
/* 180 */         outBuf[outPtr++] = (byte)(0x80 | c >> 6 & 0x3F);
/* 181 */         outBuf[outPtr++] = (byte)(0x80 | c & 0x3F);
/*     */         continue;
/*     */       } 
/* 185 */       if (c > 56319) {
/* 186 */         this.mOutPtr = outPtr;
/* 187 */         throwIllegal(c);
/*     */       } 
/* 189 */       this.mSurrogate = c;
/* 191 */       if (off >= len)
/*     */         break; 
/* 194 */       c = _convertSurrogate(cbuf[off++]);
/* 195 */       if (c > 1114111) {
/* 196 */         this.mOutPtr = outPtr;
/* 197 */         throwIllegal(c);
/*     */       } 
/* 199 */       outBuf[outPtr++] = (byte)(0xF0 | c >> 18);
/* 200 */       outBuf[outPtr++] = (byte)(0x80 | c >> 12 & 0x3F);
/* 201 */       outBuf[outPtr++] = (byte)(0x80 | c >> 6 & 0x3F);
/* 202 */       outBuf[outPtr++] = (byte)(0x80 | c & 0x3F);
/*     */     } 
/* 205 */     this.mOutPtr = outPtr;
/*     */   }
/*     */   
/*     */   public void write(int c) throws IOException {
/* 212 */     if (this.mSurrogate > 0) {
/* 213 */       c = _convertSurrogate(c);
/* 215 */     } else if (c >= 55296 && c <= 57343) {
/* 217 */       if (c > 56319)
/* 218 */         throwIllegal(c); 
/* 221 */       this.mSurrogate = c;
/*     */       return;
/*     */     } 
/* 225 */     if (this.mOutPtr >= this.mOutBufferLast) {
/* 226 */       this.mOut.write(this.mOutBuffer, 0, this.mOutPtr);
/* 227 */       this.mOutPtr = 0;
/*     */     } 
/* 230 */     if (c < 128) {
/* 231 */       this.mOutBuffer[this.mOutPtr++] = (byte)c;
/*     */     } else {
/* 233 */       int ptr = this.mOutPtr;
/* 234 */       if (c < 2048) {
/* 235 */         this.mOutBuffer[ptr++] = (byte)(0xC0 | c >> 6);
/* 236 */         this.mOutBuffer[ptr++] = (byte)(0x80 | c & 0x3F);
/* 237 */       } else if (c <= 65535) {
/* 238 */         this.mOutBuffer[ptr++] = (byte)(0xE0 | c >> 12);
/* 239 */         this.mOutBuffer[ptr++] = (byte)(0x80 | c >> 6 & 0x3F);
/* 240 */         this.mOutBuffer[ptr++] = (byte)(0x80 | c & 0x3F);
/*     */       } else {
/* 242 */         if (c > 1114111)
/* 243 */           throwIllegal(c); 
/* 245 */         this.mOutBuffer[ptr++] = (byte)(0xF0 | c >> 18);
/* 246 */         this.mOutBuffer[ptr++] = (byte)(0x80 | c >> 12 & 0x3F);
/* 247 */         this.mOutBuffer[ptr++] = (byte)(0x80 | c >> 6 & 0x3F);
/* 248 */         this.mOutBuffer[ptr++] = (byte)(0x80 | c & 0x3F);
/*     */       } 
/* 250 */       this.mOutPtr = ptr;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void write(String str) throws IOException {
/* 257 */     write(str, 0, str.length());
/*     */   }
/*     */   
/*     */   public void write(String str, int off, int len) throws IOException {
/* 263 */     if (len < 2) {
/* 264 */       if (len == 1)
/* 265 */         write(str.charAt(off)); 
/*     */       return;
/*     */     } 
/* 271 */     if (this.mSurrogate > 0) {
/* 272 */       char second = str.charAt(off++);
/* 273 */       len--;
/* 274 */       write(_convertSurrogate(second));
/*     */     } 
/* 278 */     int outPtr = this.mOutPtr;
/* 279 */     byte[] outBuf = this.mOutBuffer;
/* 280 */     int outBufLast = this.mOutBufferLast;
/* 283 */     len += off;
/* 286 */     label45: while (off < len) {
/* 290 */       if (outPtr >= outBufLast) {
/* 291 */         this.mOut.write(outBuf, 0, outPtr);
/* 292 */         outPtr = 0;
/*     */       } 
/* 295 */       int c = str.charAt(off++);
/* 297 */       if (c < 128) {
/* 298 */         outBuf[outPtr++] = (byte)c;
/* 300 */         int maxInCount = len - off;
/* 301 */         int maxOutCount = outBufLast - outPtr;
/* 303 */         if (maxInCount > maxOutCount)
/* 304 */           maxInCount = maxOutCount; 
/* 306 */         maxInCount += off;
/* 309 */         while (off < maxInCount) {
/* 312 */           c = str.charAt(off++);
/* 313 */           if (c >= 128)
/*     */             continue label45; 
/* 316 */           outBuf[outPtr++] = (byte)c;
/*     */         } 
/*     */         continue;
/*     */       } 
/* 321 */       if (c < 2048) {
/* 322 */         outBuf[outPtr++] = (byte)(0xC0 | c >> 6);
/* 323 */         outBuf[outPtr++] = (byte)(0x80 | c & 0x3F);
/*     */         continue;
/*     */       } 
/* 326 */       if (c < 55296 || c > 57343) {
/* 327 */         outBuf[outPtr++] = (byte)(0xE0 | c >> 12);
/* 328 */         outBuf[outPtr++] = (byte)(0x80 | c >> 6 & 0x3F);
/* 329 */         outBuf[outPtr++] = (byte)(0x80 | c & 0x3F);
/*     */         continue;
/*     */       } 
/* 333 */       if (c > 56319) {
/* 334 */         this.mOutPtr = outPtr;
/* 335 */         throwIllegal(c);
/*     */       } 
/* 337 */       this.mSurrogate = c;
/* 339 */       if (off >= len)
/*     */         break; 
/* 342 */       c = _convertSurrogate(str.charAt(off++));
/* 343 */       if (c > 1114111) {
/* 344 */         this.mOutPtr = outPtr;
/* 345 */         throwIllegal(c);
/*     */       } 
/* 347 */       outBuf[outPtr++] = (byte)(0xF0 | c >> 18);
/* 348 */       outBuf[outPtr++] = (byte)(0x80 | c >> 12 & 0x3F);
/* 349 */       outBuf[outPtr++] = (byte)(0x80 | c >> 6 & 0x3F);
/* 350 */       outBuf[outPtr++] = (byte)(0x80 | c & 0x3F);
/*     */     } 
/* 353 */     this.mOutPtr = outPtr;
/*     */   }
/*     */   
/*     */   private final void _close(boolean forceClosing) throws IOException {
/* 365 */     byte[] buf = this.mOutBuffer;
/* 366 */     if (buf != null) {
/* 367 */       this.mOutBuffer = null;
/* 368 */       if (this.mOutPtr > 0) {
/* 369 */         this.mOut.write(buf, 0, this.mOutPtr);
/* 370 */         this.mOutPtr = 0;
/*     */       } 
/* 372 */       if (this.mConfig != null)
/* 373 */         this.mConfig.freeFullBBuffer(buf); 
/*     */     } 
/* 377 */     if (forceClosing)
/* 378 */       this.mOut.close(); 
/* 384 */     int code = this.mSurrogate;
/* 385 */     if (code > 0) {
/* 386 */       this.mSurrogate = 0;
/* 387 */       throwIllegal(code);
/*     */     } 
/*     */   }
/*     */   
/*     */   private final int _convertSurrogate(int secondPart) throws IOException {
/* 397 */     int firstPart = this.mSurrogate;
/* 398 */     this.mSurrogate = 0;
/* 401 */     if (secondPart < 56320 || secondPart > 57343)
/* 402 */       throw new IOException("Broken surrogate pair: first char 0x" + Integer.toHexString(firstPart) + ", second 0x" + Integer.toHexString(secondPart) + "; illegal combination"); 
/* 404 */     return 65536 + (firstPart - 55296 << 10) + secondPart - 56320;
/*     */   }
/*     */   
/*     */   private void throwIllegal(int code) throws IOException {
/* 410 */     if (code > 1114111)
/* 411 */       throw new IOException("Illegal character point (0x" + Integer.toHexString(code) + ") to output; max is 0x10FFFF as per RFC 3629"); 
/* 413 */     if (code >= 55296) {
/* 414 */       if (code <= 56319)
/* 415 */         throw new IOException("Unmatched first part of surrogate pair (0x" + Integer.toHexString(code) + ")"); 
/* 417 */       throw new IOException("Unmatched second part of surrogate pair (0x" + Integer.toHexString(code) + ")");
/*     */     } 
/* 421 */     throw new IOException("Illegal character point (0x" + Integer.toHexString(code) + ") to output");
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\io\UTF8Writer.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */