/*     */ package com.ctc.wstx.io;
/*     */ 
/*     */ import com.ctc.wstx.api.ReaderConfig;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ 
/*     */ public final class MergedStream extends InputStream {
/*     */   final ReaderConfig mConfig;
/*     */   
/*     */   final InputStream mIn;
/*     */   
/*     */   byte[] mData;
/*     */   
/*     */   int mPtr;
/*     */   
/*     */   final int mEnd;
/*     */   
/*     */   public MergedStream(ReaderConfig cfg, InputStream in, byte[] buf, int start, int end) {
/*  31 */     this.mConfig = cfg;
/*  32 */     this.mIn = in;
/*  33 */     this.mData = buf;
/*  34 */     this.mPtr = start;
/*  35 */     this.mEnd = end;
/*     */   }
/*     */   
/*     */   public int available() throws IOException {
/*  41 */     if (this.mData != null)
/*  42 */       return this.mEnd - this.mPtr; 
/*  44 */     return this.mIn.available();
/*     */   }
/*     */   
/*     */   public void close() throws IOException {
/*  50 */     freeMergedBuffer();
/*  51 */     this.mIn.close();
/*     */   }
/*     */   
/*     */   public void mark(int readlimit) {
/*  56 */     if (this.mData == null)
/*  57 */       this.mIn.mark(readlimit); 
/*     */   }
/*     */   
/*     */   public boolean markSupported() {
/*  65 */     return (this.mData == null && this.mIn.markSupported());
/*     */   }
/*     */   
/*     */   public int read() throws IOException {
/*  71 */     if (this.mData != null) {
/*  72 */       int c = this.mData[this.mPtr++] & 0xFF;
/*  73 */       if (this.mPtr >= this.mEnd)
/*  74 */         freeMergedBuffer(); 
/*  76 */       return c;
/*     */     } 
/*  78 */     return this.mIn.read();
/*     */   }
/*     */   
/*     */   public int read(byte[] b) throws IOException {
/*  84 */     return read(b, 0, b.length);
/*     */   }
/*     */   
/*     */   public int read(byte[] b, int off, int len) throws IOException {
/*  90 */     if (this.mData != null) {
/*  91 */       int avail = this.mEnd - this.mPtr;
/*  92 */       if (len > avail)
/*  93 */         len = avail; 
/*  95 */       System.arraycopy(this.mData, this.mPtr, b, off, len);
/*  96 */       this.mPtr += len;
/*  97 */       if (this.mPtr >= this.mEnd)
/*  98 */         freeMergedBuffer(); 
/* 100 */       return len;
/*     */     } 
/* 102 */     return this.mIn.read(b, off, len);
/*     */   }
/*     */   
/*     */   public void reset() throws IOException {
/* 108 */     if (this.mData == null)
/* 109 */       this.mIn.reset(); 
/*     */   }
/*     */   
/*     */   public long skip(long n) throws IOException {
/* 116 */     long count = 0L;
/* 118 */     if (this.mData != null) {
/* 119 */       int amount = this.mEnd - this.mPtr;
/* 121 */       if (amount > n) {
/* 122 */         this.mPtr += (int)n;
/* 123 */         return n;
/*     */       } 
/* 125 */       freeMergedBuffer();
/* 126 */       count += amount;
/* 127 */       n -= amount;
/*     */     } 
/* 130 */     if (n > 0L)
/* 131 */       count += this.mIn.skip(n); 
/* 133 */     return count;
/*     */   }
/*     */   
/*     */   private void freeMergedBuffer() {
/* 138 */     if (this.mData != null) {
/* 139 */       byte[] data = this.mData;
/* 140 */       this.mData = null;
/* 141 */       if (this.mConfig != null)
/* 142 */         this.mConfig.freeFullBBuffer(data); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\io\MergedStream.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */