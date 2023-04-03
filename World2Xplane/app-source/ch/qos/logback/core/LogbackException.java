/*    */ package ch.qos.logback.core;
/*    */ 
/*    */ public class LogbackException extends RuntimeException {
/*    */   private static final long serialVersionUID = -799956346239073266L;
/*    */   
/*    */   public LogbackException(String msg) {
/* 21 */     super(msg);
/*    */   }
/*    */   
/*    */   public LogbackException(String msg, Throwable nested) {
/* 26 */     super(msg, nested);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\core\LogbackException.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */