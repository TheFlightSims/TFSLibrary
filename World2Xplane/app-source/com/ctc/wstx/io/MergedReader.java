/*     */ package com.ctc.wstx.io;
/*     */ 
/*     */ import com.ctc.wstx.api.ReaderConfig;
/*     */ import java.io.IOException;
/*     */ import java.io.Reader;
/*     */ 
/*     */ public final class MergedReader extends Reader {
/*     */   final ReaderConfig mConfig;
/*     */   
/*     */   final Reader mIn;
/*     */   
/*     */   char[] mData;
/*     */   
/*     */   int mPtr;
/*     */   
/*     */   final int mEnd;
/*     */   
/*     */   public MergedReader(ReaderConfig cfg, Reader in, char[] buf, int start, int end) {
/*  37 */     this.mConfig = cfg;
/*  38 */     this.mIn = in;
/*  39 */     this.mData = buf;
/*  40 */     this.mPtr = start;
/*  41 */     this.mEnd = end;
/*  43 */     if (buf != null && start >= end)
/*  44 */       throw new IllegalArgumentException("Trying to construct MergedReader with empty contents (start " + start + ", end " + end + ")"); 
/*     */   }
/*     */   
/*     */   public void close() throws IOException {
/*  51 */     freeMergedBuffer();
/*  52 */     this.mIn.close();
/*     */   }
/*     */   
/*     */   public void mark(int readlimit) throws IOException {
/*  58 */     if (this.mData == null)
/*  59 */       this.mIn.mark(readlimit); 
/*     */   }
/*     */   
/*     */   public boolean markSupported() {
/*  67 */     return (this.mData == null && this.mIn.markSupported());
/*     */   }
/*     */   
/*     */   public int read() throws IOException {
/*  73 */     if (this.mData != null) {
/*  74 */       int c = this.mData[this.mPtr++] & 0xFF;
/*  75 */       if (this.mPtr >= this.mEnd)
/*  76 */         freeMergedBuffer(); 
/*  78 */       return c;
/*     */     } 
/*  80 */     return this.mIn.read();
/*     */   }
/*     */   
/*     */   public int read(char[] cbuf) throws IOException {
/*  86 */     return read(cbuf, 0, cbuf.length);
/*     */   }
/*     */   
/*     */   public int read(char[] cbuf, int off, int len) throws IOException {
/*  92 */     if (this.mData != null) {
/*  93 */       int avail = this.mEnd - this.mPtr;
/*  94 */       if (len > avail)
/*  95 */         len = avail; 
/*  97 */       System.arraycopy(this.mData, this.mPtr, cbuf, off, len);
/*  98 */       this.mPtr += len;
/*  99 */       if (this.mPtr >= this.mEnd)
/* 100 */         freeMergedBuffer(); 
/* 102 */       return len;
/*     */     } 
/* 105 */     return this.mIn.read(cbuf, off, len);
/*     */   }
/*     */   
/*     */   public boolean ready() throws IOException {
/* 111 */     return (this.mData != null || this.mIn.ready());
/*     */   }
/*     */   
/*     */   public void reset() throws IOException {
/* 117 */     if (this.mData == null)
/* 118 */       this.mIn.reset(); 
/*     */   }
/*     */   
/*     */   public long skip(long n) throws IOException {
/* 125 */     long count = 0L;
/* 127 */     if (this.mData != null) {
/* 128 */       int amount = this.mEnd - this.mPtr;
/* 130 */       if (amount > n) {
/* 131 */         this.mPtr += (int)n;
/* 132 */         return amount;
/*     */       } 
/* 134 */       freeMergedBuffer();
/* 135 */       count += amount;
/* 136 */       n -= amount;
/*     */     } 
/* 139 */     if (n > 0L)
/* 140 */       count += this.mIn.skip(n); 
/* 142 */     return count;
/*     */   }
/*     */   
/*     */   private void freeMergedBuffer() {
/* 147 */     if (this.mData != null) {
/* 148 */       char[] data = this.mData;
/* 149 */       this.mData = null;
/* 150 */       if (this.mConfig != null)
/* 151 */         this.mConfig.freeSmallCBuffer(data); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\io\MergedReader.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */