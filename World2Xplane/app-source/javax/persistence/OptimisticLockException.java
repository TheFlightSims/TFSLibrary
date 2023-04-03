/*     */ package javax.persistence;
/*     */ 
/*     */ public class OptimisticLockException extends PersistenceException {
/*     */   Object entity;
/*     */   
/*     */   public OptimisticLockException() {}
/*     */   
/*     */   public OptimisticLockException(String message) {
/*  65 */     super(message);
/*     */   }
/*     */   
/*     */   public OptimisticLockException(String message, Throwable cause) {
/*  75 */     super(message, cause);
/*     */   }
/*     */   
/*     */   public OptimisticLockException(Throwable cause) {
/*  84 */     super(cause);
/*     */   }
/*     */   
/*     */   public OptimisticLockException(Object entity) {
/*  93 */     this.entity = entity;
/*     */   }
/*     */   
/*     */   public OptimisticLockException(String message, Throwable cause, Object entity) {
/* 104 */     super(message, cause);
/* 105 */     this.entity = entity;
/*     */   }
/*     */   
/*     */   public Object getEntity() {
/* 113 */     return this.entity;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\persistence\OptimisticLockException.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */