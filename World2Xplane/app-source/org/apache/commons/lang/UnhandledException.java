/*    */ package org.apache.commons.lang;
/*    */ 
/*    */ import org.apache.commons.lang.exception.NestableRuntimeException;
/*    */ 
/*    */ public class UnhandledException extends NestableRuntimeException {
/*    */   private static final long serialVersionUID = 1832101364842773720L;
/*    */   
/*    */   public UnhandledException(Throwable cause) {
/* 60 */     super(cause);
/*    */   }
/*    */   
/*    */   public UnhandledException(String message, Throwable cause) {
/* 70 */     super(message, cause);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\lang\UnhandledException.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */