/*    */ package ch.qos.logback.core.status;
/*    */ 
/*    */ public class ErrorStatus extends StatusBase {
/*    */   public ErrorStatus(String msg, Object origin) {
/* 21 */     super(2, msg, origin);
/*    */   }
/*    */   
/*    */   public ErrorStatus(String msg, Object origin, Throwable t) {
/* 25 */     super(2, msg, origin, t);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\core\status\ErrorStatus.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */