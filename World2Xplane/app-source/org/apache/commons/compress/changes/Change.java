/*    */ package org.apache.commons.compress.changes;
/*    */ 
/*    */ import java.io.InputStream;
/*    */ import org.apache.commons.compress.archivers.ArchiveEntry;
/*    */ 
/*    */ class Change {
/*    */   private final String targetFile;
/*    */   
/*    */   private final ArchiveEntry entry;
/*    */   
/*    */   private final InputStream input;
/*    */   
/*    */   private final boolean replaceMode;
/*    */   
/*    */   private final int type;
/*    */   
/*    */   static final int TYPE_DELETE = 1;
/*    */   
/*    */   static final int TYPE_ADD = 2;
/*    */   
/*    */   static final int TYPE_MOVE = 3;
/*    */   
/*    */   static final int TYPE_DELETE_DIR = 4;
/*    */   
/*    */   Change(String pFilename, int type) {
/* 50 */     if (pFilename == null)
/* 51 */       throw new NullPointerException(); 
/* 53 */     this.targetFile = pFilename;
/* 54 */     this.type = type;
/* 55 */     this.input = null;
/* 56 */     this.entry = null;
/* 57 */     this.replaceMode = true;
/*    */   }
/*    */   
/*    */   Change(ArchiveEntry pEntry, InputStream pInput, boolean replace) {
/* 67 */     if (pEntry == null || pInput == null)
/* 68 */       throw new NullPointerException(); 
/* 70 */     this.entry = pEntry;
/* 71 */     this.input = pInput;
/* 72 */     this.type = 2;
/* 73 */     this.targetFile = null;
/* 74 */     this.replaceMode = replace;
/*    */   }
/*    */   
/*    */   ArchiveEntry getEntry() {
/* 78 */     return this.entry;
/*    */   }
/*    */   
/*    */   InputStream getInput() {
/* 82 */     return this.input;
/*    */   }
/*    */   
/*    */   String targetFile() {
/* 86 */     return this.targetFile;
/*    */   }
/*    */   
/*    */   int type() {
/* 90 */     return this.type;
/*    */   }
/*    */   
/*    */   boolean isReplaceMode() {
/* 94 */     return this.replaceMode;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\compress\changes\Change.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */