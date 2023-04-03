/*    */ package ch.qos.logback.core.status;
/*    */ 
/*    */ public class InfoStatus extends StatusBase {
/*    */   public InfoStatus(String msg, Object origin) {
/* 20 */     super(0, msg, origin);
/*    */   }
/*    */   
/*    */   public InfoStatus(String msg, Object origin, Throwable t) {
/* 24 */     super(0, msg, origin, t);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\core\status\InfoStatus.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */