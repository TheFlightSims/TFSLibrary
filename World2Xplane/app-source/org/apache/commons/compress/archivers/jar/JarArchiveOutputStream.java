/*    */ package org.apache.commons.compress.archivers.jar;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.OutputStream;
/*    */ import org.apache.commons.compress.archivers.ArchiveEntry;
/*    */ import org.apache.commons.compress.archivers.zip.JarMarker;
/*    */ import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
/*    */ import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
/*    */ import org.apache.commons.compress.archivers.zip.ZipExtraField;
/*    */ 
/*    */ public class JarArchiveOutputStream extends ZipArchiveOutputStream {
/*    */   private boolean jarMarkerAdded = false;
/*    */   
/*    */   public JarArchiveOutputStream(OutputStream out) {
/* 41 */     super(out);
/*    */   }
/*    */   
/*    */   public void putArchiveEntry(ArchiveEntry ze) throws IOException {
/* 47 */     if (!this.jarMarkerAdded) {
/* 48 */       ((ZipArchiveEntry)ze).addAsFirstExtraField((ZipExtraField)JarMarker.getInstance());
/* 49 */       this.jarMarkerAdded = true;
/*    */     } 
/* 51 */     super.putArchiveEntry(ze);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\compress\archivers\jar\JarArchiveOutputStream.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */