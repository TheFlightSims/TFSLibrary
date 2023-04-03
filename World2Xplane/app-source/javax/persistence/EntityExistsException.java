/*    */ package javax.persistence;
/*    */ 
/*    */ public class EntityExistsException extends PersistenceException {
/*    */   public EntityExistsException() {}
/*    */   
/*    */   public EntityExistsException(String message) {
/* 64 */     super(message);
/*    */   }
/*    */   
/*    */   public EntityExistsException(String message, Throwable cause) {
/* 74 */     super(message, cause);
/*    */   }
/*    */   
/*    */   public EntityExistsException(Throwable cause) {
/* 83 */     super(cause);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\persistence\EntityExistsException.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */