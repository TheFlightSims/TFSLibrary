/*     */ package org.apache.commons.compress.compressors;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;
/*     */ import org.apache.commons.compress.compressors.bzip2.BZip2CompressorOutputStream;
/*     */ import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream;
/*     */ import org.apache.commons.compress.compressors.gzip.GzipCompressorOutputStream;
/*     */ import org.apache.commons.compress.compressors.pack200.Pack200CompressorInputStream;
/*     */ import org.apache.commons.compress.compressors.pack200.Pack200CompressorOutputStream;
/*     */ import org.apache.commons.compress.compressors.xz.XZCompressorInputStream;
/*     */ import org.apache.commons.compress.compressors.xz.XZCompressorOutputStream;
/*     */ 
/*     */ public class CompressorStreamFactory {
/*     */   public static final String BZIP2 = "bzip2";
/*     */   
/*     */   public static final String GZIP = "gz";
/*     */   
/*     */   public static final String PACK200 = "pack200";
/*     */   
/*     */   public static final String XZ = "xz";
/*     */   
/*     */   public CompressorInputStream createCompressorInputStream(InputStream in) throws CompressorException {
/*  98 */     if (in == null)
/*  99 */       throw new IllegalArgumentException("Stream must not be null."); 
/* 102 */     if (!in.markSupported())
/* 103 */       throw new IllegalArgumentException("Mark is not supported."); 
/* 106 */     byte[] signature = new byte[12];
/* 107 */     in.mark(signature.length);
/*     */     try {
/* 109 */       int signatureLength = in.read(signature);
/* 110 */       in.reset();
/* 112 */       if (BZip2CompressorInputStream.matches(signature, signatureLength))
/* 113 */         return (CompressorInputStream)new BZip2CompressorInputStream(in); 
/* 116 */       if (GzipCompressorInputStream.matches(signature, signatureLength))
/* 117 */         return (CompressorInputStream)new GzipCompressorInputStream(in); 
/* 120 */       if (XZCompressorInputStream.matches(signature, signatureLength))
/* 121 */         return (CompressorInputStream)new XZCompressorInputStream(in); 
/* 124 */       if (Pack200CompressorInputStream.matches(signature, signatureLength))
/* 125 */         return (CompressorInputStream)new Pack200CompressorInputStream(in); 
/* 128 */     } catch (IOException e) {
/* 129 */       throw new CompressorException("Failed to detect Compressor from InputStream.", e);
/*     */     } 
/* 132 */     throw new CompressorException("No Compressor found for the stream signature.");
/*     */   }
/*     */   
/*     */   public CompressorInputStream createCompressorInputStream(String name, InputStream in) throws CompressorException {
/* 146 */     if (name == null || in == null)
/* 147 */       throw new IllegalArgumentException("Compressor name and stream must not be null."); 
/*     */     try {
/* 153 */       if ("gz".equalsIgnoreCase(name))
/* 154 */         return (CompressorInputStream)new GzipCompressorInputStream(in); 
/* 157 */       if ("bzip2".equalsIgnoreCase(name))
/* 158 */         return (CompressorInputStream)new BZip2CompressorInputStream(in); 
/* 161 */       if ("xz".equalsIgnoreCase(name))
/* 162 */         return (CompressorInputStream)new XZCompressorInputStream(in); 
/* 165 */       if ("pack200".equalsIgnoreCase(name))
/* 166 */         return (CompressorInputStream)new Pack200CompressorInputStream(in); 
/* 169 */     } catch (IOException e) {
/* 170 */       throw new CompressorException("Could not create CompressorInputStream.", e);
/*     */     } 
/* 173 */     throw new CompressorException("Compressor: " + name + " not found.");
/*     */   }
/*     */   
/*     */   public CompressorOutputStream createCompressorOutputStream(String name, OutputStream out) throws CompressorException {
/* 188 */     if (name == null || out == null)
/* 189 */       throw new IllegalArgumentException("Compressor name and stream must not be null."); 
/*     */     try {
/* 195 */       if ("gz".equalsIgnoreCase(name))
/* 196 */         return (CompressorOutputStream)new GzipCompressorOutputStream(out); 
/* 199 */       if ("bzip2".equalsIgnoreCase(name))
/* 200 */         return (CompressorOutputStream)new BZip2CompressorOutputStream(out); 
/* 203 */       if ("xz".equalsIgnoreCase(name))
/* 204 */         return (CompressorOutputStream)new XZCompressorOutputStream(out); 
/* 207 */       if ("pack200".equalsIgnoreCase(name))
/* 208 */         return (CompressorOutputStream)new Pack200CompressorOutputStream(out); 
/* 211 */     } catch (IOException e) {
/* 212 */       throw new CompressorException("Could not create CompressorOutputStream", e);
/*     */     } 
/* 215 */     throw new CompressorException("Compressor: " + name + " not found.");
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\compress\compressors\CompressorStreamFactory.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */