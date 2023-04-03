/*     */ package org.postgresql.largeobject;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.sql.SQLException;
/*     */ 
/*     */ public class BlobOutputStream extends OutputStream {
/*     */   private LargeObject lo;
/*     */   
/*     */   private byte[] buf;
/*     */   
/*     */   private int bsize;
/*     */   
/*     */   private int bpos;
/*     */   
/*     */   public BlobOutputStream(LargeObject lo) {
/*  47 */     this(lo, 1024);
/*     */   }
/*     */   
/*     */   public BlobOutputStream(LargeObject lo, int bsize) {
/*  57 */     this.lo = lo;
/*  58 */     this.bsize = bsize;
/*  59 */     this.buf = new byte[bsize];
/*  60 */     this.bpos = 0;
/*     */   }
/*     */   
/*     */   public void write(int b) throws IOException {
/*  65 */     checkClosed();
/*     */     try {
/*  68 */       if (this.bpos >= this.bsize) {
/*  70 */         this.lo.write(this.buf);
/*  71 */         this.bpos = 0;
/*     */       } 
/*  73 */       this.buf[this.bpos++] = (byte)b;
/*  75 */     } catch (SQLException se) {
/*  77 */       throw new IOException(se.toString());
/*     */     } 
/*     */   }
/*     */   
/*     */   public void write(byte[] buf, int off, int len) throws IOException {
/*  83 */     checkClosed();
/*     */     try {
/*  87 */       if (this.bpos > 0)
/*  88 */         flush(); 
/*  90 */       if (off == 0 && len == buf.length) {
/*  91 */         this.lo.write(buf);
/*     */       } else {
/*  93 */         this.lo.write(buf, off, len);
/*     */       } 
/*  95 */     } catch (SQLException se) {
/*  97 */       throw new IOException(se.toString());
/*     */     } 
/*     */   }
/*     */   
/*     */   public void flush() throws IOException {
/* 114 */     checkClosed();
/*     */     try {
/* 117 */       if (this.bpos > 0)
/* 118 */         this.lo.write(this.buf, 0, this.bpos); 
/* 119 */       this.bpos = 0;
/* 121 */     } catch (SQLException se) {
/* 123 */       throw new IOException(se.toString());
/*     */     } 
/*     */   }
/*     */   
/*     */   public void close() throws IOException {
/* 139 */     if (this.lo != null)
/*     */       try {
/* 142 */         flush();
/* 143 */         this.lo.close();
/* 144 */         this.lo = null;
/* 146 */       } catch (SQLException se) {
/* 148 */         throw new IOException(se.toString());
/*     */       }  
/*     */   }
/*     */   
/*     */   private void checkClosed() throws IOException {
/* 155 */     if (this.lo == null)
/* 156 */       throw new IOException("BlobOutputStream is closed"); 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\largeobject\BlobOutputStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */