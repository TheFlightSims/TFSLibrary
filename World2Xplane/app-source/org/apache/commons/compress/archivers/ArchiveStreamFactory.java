/*     */ package org.apache.commons.compress.archivers;
/*     */ 
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import org.apache.commons.compress.archivers.ar.ArArchiveInputStream;
/*     */ import org.apache.commons.compress.archivers.ar.ArArchiveOutputStream;
/*     */ import org.apache.commons.compress.archivers.cpio.CpioArchiveInputStream;
/*     */ import org.apache.commons.compress.archivers.cpio.CpioArchiveOutputStream;
/*     */ import org.apache.commons.compress.archivers.dump.DumpArchiveInputStream;
/*     */ import org.apache.commons.compress.archivers.jar.JarArchiveInputStream;
/*     */ import org.apache.commons.compress.archivers.jar.JarArchiveOutputStream;
/*     */ import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
/*     */ import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;
/*     */ import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
/*     */ import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
/*     */ 
/*     */ public class ArchiveStreamFactory {
/*     */   public static final String AR = "ar";
/*     */   
/*     */   public static final String CPIO = "cpio";
/*     */   
/*     */   public static final String DUMP = "dump";
/*     */   
/*     */   public static final String JAR = "jar";
/*     */   
/*     */   public static final String TAR = "tar";
/*     */   
/*     */   public static final String ZIP = "zip";
/*     */   
/*     */   public ArchiveInputStream createArchiveInputStream(String archiverName, InputStream in) throws ArchiveException {
/* 120 */     if (archiverName == null)
/* 121 */       throw new IllegalArgumentException("Archivername must not be null."); 
/* 124 */     if (in == null)
/* 125 */       throw new IllegalArgumentException("InputStream must not be null."); 
/* 128 */     if ("ar".equalsIgnoreCase(archiverName))
/* 129 */       return (ArchiveInputStream)new ArArchiveInputStream(in); 
/* 131 */     if ("zip".equalsIgnoreCase(archiverName))
/* 132 */       return (ArchiveInputStream)new ZipArchiveInputStream(in); 
/* 134 */     if ("tar".equalsIgnoreCase(archiverName))
/* 135 */       return (ArchiveInputStream)new TarArchiveInputStream(in); 
/* 137 */     if ("jar".equalsIgnoreCase(archiverName))
/* 138 */       return (ArchiveInputStream)new JarArchiveInputStream(in); 
/* 140 */     if ("cpio".equalsIgnoreCase(archiverName))
/* 141 */       return (ArchiveInputStream)new CpioArchiveInputStream(in); 
/* 143 */     if ("dump".equalsIgnoreCase(archiverName))
/* 144 */       return (ArchiveInputStream)new DumpArchiveInputStream(in); 
/* 147 */     throw new ArchiveException("Archiver: " + archiverName + " not found.");
/*     */   }
/*     */   
/*     */   public ArchiveOutputStream createArchiveOutputStream(String archiverName, OutputStream out) throws ArchiveException {
/* 162 */     if (archiverName == null)
/* 163 */       throw new IllegalArgumentException("Archivername must not be null."); 
/* 165 */     if (out == null)
/* 166 */       throw new IllegalArgumentException("OutputStream must not be null."); 
/* 169 */     if ("ar".equalsIgnoreCase(archiverName))
/* 170 */       return (ArchiveOutputStream)new ArArchiveOutputStream(out); 
/* 172 */     if ("zip".equalsIgnoreCase(archiverName))
/* 173 */       return (ArchiveOutputStream)new ZipArchiveOutputStream(out); 
/* 175 */     if ("tar".equalsIgnoreCase(archiverName))
/* 176 */       return (ArchiveOutputStream)new TarArchiveOutputStream(out); 
/* 178 */     if ("jar".equalsIgnoreCase(archiverName))
/* 179 */       return (ArchiveOutputStream)new JarArchiveOutputStream(out); 
/* 181 */     if ("cpio".equalsIgnoreCase(archiverName))
/* 182 */       return (ArchiveOutputStream)new CpioArchiveOutputStream(out); 
/* 184 */     throw new ArchiveException("Archiver: " + archiverName + " not found.");
/*     */   }
/*     */   
/*     */   public ArchiveInputStream createArchiveInputStream(InputStream in) throws ArchiveException {
/* 199 */     if (in == null)
/* 200 */       throw new IllegalArgumentException("Stream must not be null."); 
/* 203 */     if (!in.markSupported())
/* 204 */       throw new IllegalArgumentException("Mark is not supported."); 
/* 207 */     byte[] signature = new byte[12];
/* 208 */     in.mark(signature.length);
/*     */     try {
/* 210 */       int signatureLength = in.read(signature);
/* 211 */       in.reset();
/* 212 */       if (ZipArchiveInputStream.matches(signature, signatureLength))
/* 213 */         return (ArchiveInputStream)new ZipArchiveInputStream(in); 
/* 214 */       if (JarArchiveInputStream.matches(signature, signatureLength))
/* 215 */         return (ArchiveInputStream)new JarArchiveInputStream(in); 
/* 216 */       if (ArArchiveInputStream.matches(signature, signatureLength))
/* 217 */         return (ArchiveInputStream)new ArArchiveInputStream(in); 
/* 218 */       if (CpioArchiveInputStream.matches(signature, signatureLength))
/* 219 */         return (ArchiveInputStream)new CpioArchiveInputStream(in); 
/* 223 */       byte[] dumpsig = new byte[32];
/* 224 */       in.mark(dumpsig.length);
/* 225 */       signatureLength = in.read(dumpsig);
/* 226 */       in.reset();
/* 227 */       if (DumpArchiveInputStream.matches(dumpsig, signatureLength))
/* 228 */         return (ArchiveInputStream)new DumpArchiveInputStream(in); 
/* 232 */       byte[] tarheader = new byte[512];
/* 233 */       in.mark(tarheader.length);
/* 234 */       signatureLength = in.read(tarheader);
/* 235 */       in.reset();
/* 236 */       if (TarArchiveInputStream.matches(tarheader, signatureLength))
/* 237 */         return (ArchiveInputStream)new TarArchiveInputStream(in); 
/* 240 */       if (signatureLength >= 512)
/*     */         try {
/* 242 */           TarArchiveInputStream tais = new TarArchiveInputStream(new ByteArrayInputStream(tarheader));
/* 243 */           tais.getNextEntry();
/* 244 */           return (ArchiveInputStream)new TarArchiveInputStream(in);
/* 245 */         } catch (Exception e) {} 
/* 252 */     } catch (IOException e) {
/* 253 */       throw new ArchiveException("Could not use reset and mark operations.", e);
/*     */     } 
/* 256 */     throw new ArchiveException("No Archiver found for the stream signature");
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\compress\archivers\ArchiveStreamFactory.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */