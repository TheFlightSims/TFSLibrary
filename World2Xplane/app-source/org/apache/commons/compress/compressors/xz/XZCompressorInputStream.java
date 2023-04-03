/*     */ package org.apache.commons.compress.compressors.xz;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import org.apache.commons.compress.compressors.CompressorInputStream;
/*     */ import org.tukaani.xz.SingleXZInputStream;
/*     */ import org.tukaani.xz.XZ;
/*     */ import org.tukaani.xz.XZInputStream;
/*     */ 
/*     */ public class XZCompressorInputStream extends CompressorInputStream {
/*     */   private final InputStream in;
/*     */   
/*     */   public static boolean matches(byte[] signature, int length) {
/*  44 */     if (length < XZ.HEADER_MAGIC.length)
/*  45 */       return false; 
/*  48 */     for (int i = 0; i < XZ.HEADER_MAGIC.length; i++) {
/*  49 */       if (signature[i] != XZ.HEADER_MAGIC[i])
/*  50 */         return false; 
/*     */     } 
/*  54 */     return true;
/*     */   }
/*     */   
/*     */   public XZCompressorInputStream(InputStream inputStream) throws IOException {
/*  72 */     this(inputStream, false);
/*     */   }
/*     */   
/*     */   public XZCompressorInputStream(InputStream inputStream, boolean decompressConcatenated) throws IOException {
/*  95 */     if (decompressConcatenated) {
/*  96 */       this.in = (InputStream)new XZInputStream(inputStream);
/*     */     } else {
/*  98 */       this.in = (InputStream)new SingleXZInputStream(inputStream);
/*     */     } 
/*     */   }
/*     */   
/*     */   public int read() throws IOException {
/* 105 */     int ret = this.in.read();
/* 106 */     count((ret == -1) ? -1 : 1);
/* 107 */     return ret;
/*     */   }
/*     */   
/*     */   public int read(byte[] buf, int off, int len) throws IOException {
/* 113 */     int ret = this.in.read(buf, off, len);
/* 114 */     count(ret);
/* 115 */     return ret;
/*     */   }
/*     */   
/*     */   public long skip(long n) throws IOException {
/* 121 */     return this.in.skip(n);
/*     */   }
/*     */   
/*     */   public int available() throws IOException {
/* 127 */     return this.in.available();
/*     */   }
/*     */   
/*     */   public void close() throws IOException {
/* 133 */     this.in.close();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\compress\compressors\xz\XZCompressorInputStream.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */