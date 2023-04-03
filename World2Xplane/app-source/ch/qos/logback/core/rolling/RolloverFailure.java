/*    */ package ch.qos.logback.core.rolling;
/*    */ 
/*    */ import ch.qos.logback.core.LogbackException;
/*    */ 
/*    */ public class RolloverFailure extends LogbackException {
/*    */   private static final long serialVersionUID = -4407533730831239458L;
/*    */   
/*    */   public RolloverFailure(String msg) {
/* 29 */     super(msg);
/*    */   }
/*    */   
/*    */   public RolloverFailure(String message, Throwable cause) {
/* 33 */     super(message, cause);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\core\rolling\RolloverFailure.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */