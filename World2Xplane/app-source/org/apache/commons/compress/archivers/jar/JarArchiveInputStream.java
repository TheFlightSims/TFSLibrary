/*    */ package org.apache.commons.compress.archivers.jar;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import org.apache.commons.compress.archivers.ArchiveEntry;
/*    */ import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
/*    */ import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
/*    */ 
/*    */ public class JarArchiveInputStream extends ZipArchiveInputStream {
/*    */   public JarArchiveInputStream(InputStream inputStream) {
/* 36 */     super(inputStream);
/*    */   }
/*    */   
/*    */   public JarArchiveEntry getNextJarEntry() throws IOException {
/* 40 */     ZipArchiveEntry entry = getNextZipEntry();
/* 41 */     return (entry == null) ? null : new JarArchiveEntry(entry);
/*    */   }
/*    */   
/*    */   public ArchiveEntry getNextEntry() throws IOException {
/* 46 */     return (ArchiveEntry)getNextJarEntry();
/*    */   }
/*    */   
/*    */   public static boolean matches(byte[] signature, int length) {
/* 60 */     return ZipArchiveInputStream.matches(signature, length);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\compress\archivers\jar\JarArchiveInputStream.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */