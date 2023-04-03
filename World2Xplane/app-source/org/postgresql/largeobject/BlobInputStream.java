/*     */ package org.postgresql.largeobject;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.sql.SQLException;
/*     */ 
/*     */ public class BlobInputStream extends InputStream {
/*     */   private LargeObject lo;
/*     */   
/*     */   private byte[] buffer;
/*     */   
/*     */   private int bpos;
/*     */   
/*     */   private int bsize;
/*     */   
/*  44 */   private int mpos = 0;
/*     */   
/*     */   public BlobInputStream(LargeObject lo) {
/*  51 */     this(lo, 1024);
/*     */   }
/*     */   
/*     */   public BlobInputStream(LargeObject lo, int bsize) {
/*  60 */     this.lo = lo;
/*  61 */     this.buffer = null;
/*  62 */     this.bpos = 0;
/*  63 */     this.bsize = bsize;
/*     */   }
/*     */   
/*     */   public int read() throws IOException {
/*  71 */     checkClosed();
/*     */     try {
/*  74 */       if (this.buffer == null || this.bpos >= this.buffer.length) {
/*  76 */         this.buffer = this.lo.read(this.bsize);
/*  77 */         this.bpos = 0;
/*     */       } 
/*  81 */       if (this.bpos >= this.buffer.length)
/*  83 */         return -1; 
/*  86 */       int ret = this.buffer[this.bpos] & Byte.MAX_VALUE;
/*  87 */       if ((this.buffer[this.bpos] & 0x80) == 128)
/*  89 */         ret |= 0x80; 
/*  92 */       this.bpos++;
/*  94 */       return ret;
/*  96 */     } catch (SQLException se) {
/*  98 */       throw new IOException(se.toString());
/*     */     } 
/*     */   }
/*     */   
/*     */   public void close() throws IOException {
/* 114 */     if (this.lo != null)
/*     */       try {
/* 117 */         this.lo.close();
/* 118 */         this.lo = null;
/* 120 */       } catch (SQLException se) {
/* 122 */         throw new IOException(se.toString());
/*     */       }  
/*     */   }
/*     */   
/*     */   public synchronized void mark(int readlimit) {
/*     */     try {
/* 154 */       this.mpos = this.lo.tell();
/* 156 */     } catch (SQLException se) {}
/*     */   }
/*     */   
/*     */   public synchronized void reset() throws IOException {
/* 173 */     checkClosed();
/*     */     try {
/* 176 */       this.lo.seek(this.mpos);
/* 178 */     } catch (SQLException se) {
/* 180 */       throw new IOException(se.toString());
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean markSupported() {
/* 196 */     return true;
/*     */   }
/*     */   
/*     */   private void checkClosed() throws IOException {
/* 201 */     if (this.lo == null)
/* 202 */       throw new IOException("BlobOutputStream is closed"); 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\largeobject\BlobInputStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */