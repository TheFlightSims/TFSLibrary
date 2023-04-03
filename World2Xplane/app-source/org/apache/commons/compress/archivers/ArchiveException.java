/*    */ package org.apache.commons.compress.archivers;
/*    */ 
/*    */ public class ArchiveException extends Exception {
/*    */   private static final long serialVersionUID = 2772690708123267100L;
/*    */   
/*    */   public ArchiveException(String message) {
/* 37 */     super(message);
/*    */   }
/*    */   
/*    */   public ArchiveException(String message, Exception cause) {
/* 49 */     super(message);
/* 50 */     initCause(cause);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\compress\archivers\ArchiveException.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */