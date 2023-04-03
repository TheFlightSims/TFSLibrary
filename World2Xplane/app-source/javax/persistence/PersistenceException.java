/*    */ package javax.persistence;
/*    */ 
/*    */ public class PersistenceException extends RuntimeException {
/*    */   public PersistenceException() {}
/*    */   
/*    */   public PersistenceException(String message) {
/* 63 */     super(message);
/*    */   }
/*    */   
/*    */   public PersistenceException(String message, Throwable cause) {
/* 73 */     super(message, cause);
/*    */   }
/*    */   
/*    */   public PersistenceException(Throwable cause) {
/* 82 */     super(cause);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\persistence\PersistenceException.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */