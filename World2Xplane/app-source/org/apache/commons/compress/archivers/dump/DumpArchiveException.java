/*    */ package org.apache.commons.compress.archivers.dump;
/*    */ 
/*    */ import java.io.IOException;
/*    */ 
/*    */ public class DumpArchiveException extends IOException {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   public DumpArchiveException() {}
/*    */   
/*    */   public DumpArchiveException(String msg) {
/* 34 */     super(msg);
/*    */   }
/*    */   
/*    */   public DumpArchiveException(Throwable cause) {
/* 39 */     initCause(cause);
/*    */   }
/*    */   
/*    */   public DumpArchiveException(String msg, Throwable cause) {
/* 43 */     super(msg);
/* 44 */     initCause(cause);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\compress\archivers\dump\DumpArchiveException.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */