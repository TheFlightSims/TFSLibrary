/*    */ package org.apache.commons.compress.archivers.jar;
/*    */ 
/*    */ import java.security.cert.Certificate;
/*    */ import java.util.jar.Attributes;
/*    */ import java.util.jar.JarEntry;
/*    */ import java.util.zip.ZipEntry;
/*    */ import java.util.zip.ZipException;
/*    */ import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
/*    */ 
/*    */ public class JarArchiveEntry extends ZipArchiveEntry {
/* 35 */   private Attributes manifestAttributes = null;
/*    */   
/* 36 */   private Certificate[] certificates = null;
/*    */   
/*    */   public JarArchiveEntry(ZipEntry entry) throws ZipException {
/* 39 */     super(entry);
/*    */   }
/*    */   
/*    */   public JarArchiveEntry(String name) {
/* 43 */     super(name);
/*    */   }
/*    */   
/*    */   public JarArchiveEntry(ZipArchiveEntry entry) throws ZipException {
/* 47 */     super(entry);
/*    */   }
/*    */   
/*    */   public JarArchiveEntry(JarEntry entry) throws ZipException {
/* 51 */     super(entry);
/*    */   }
/*    */   
/*    */   public Attributes getManifestAttributes() {
/* 56 */     return this.manifestAttributes;
/*    */   }
/*    */   
/*    */   public Certificate[] getCertificates() {
/* 60 */     if (this.certificates != null) {
/* 61 */       Certificate[] certs = new Certificate[this.certificates.length];
/* 62 */       System.arraycopy(this.certificates, 0, certs, 0, certs.length);
/* 63 */       return certs;
/*    */     } 
/* 65 */     return null;
/*    */   }
/*    */   
/*    */   public boolean equals(Object o) {
/* 70 */     return super.equals(o);
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 75 */     return super.hashCode();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\compress\archivers\jar\JarArchiveEntry.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */