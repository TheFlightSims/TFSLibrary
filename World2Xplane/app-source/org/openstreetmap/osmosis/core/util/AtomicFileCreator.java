/*    */ package org.openstreetmap.osmosis.core.util;
/*    */ 
/*    */ import java.io.File;
/*    */ import org.openstreetmap.osmosis.core.OsmosisRuntimeException;
/*    */ 
/*    */ public class AtomicFileCreator {
/*    */   private File file;
/*    */   
/*    */   private File tmpFile;
/*    */   
/*    */   public AtomicFileCreator(File file) {
/* 25 */     this.file = file;
/* 26 */     this.tmpFile = new File(file.getPath() + ".tmp");
/*    */   }
/*    */   
/*    */   public boolean exists() {
/* 39 */     return (this.file.exists() || this.tmpFile.exists());
/*    */   }
/*    */   
/*    */   public void renameTmpFileToCurrent() {
/* 48 */     if (!this.tmpFile.exists())
/* 49 */       throw new OsmosisRuntimeException("Can't rename non-existent file " + this.tmpFile + "."); 
/* 53 */     if (this.file.exists() && 
/* 54 */       !this.file.delete())
/* 55 */       throw new OsmosisRuntimeException("Unable to delete file " + this.file + "."); 
/* 60 */     if (!this.tmpFile.renameTo(this.file))
/* 61 */       throw new OsmosisRuntimeException("Unable to rename file " + this.tmpFile + " to " + this.file + "."); 
/*    */   }
/*    */   
/*    */   public File getTmpFile() {
/* 74 */     return this.tmpFile;
/*    */   }
/*    */   
/*    */   public File getFile() {
/* 85 */     return this.file;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\cor\\util\AtomicFileCreator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */