/*    */ package javax.persistence;
/*    */ 
/*    */ public class RollbackException extends PersistenceException {
/*    */   public RollbackException() {}
/*    */   
/*    */   public RollbackException(String message) {
/* 61 */     super(message);
/*    */   }
/*    */   
/*    */   public RollbackException(String message, Throwable cause) {
/* 71 */     super(message, cause);
/*    */   }
/*    */   
/*    */   public RollbackException(Throwable cause) {
/* 80 */     super(cause);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\persistence\RollbackException.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */