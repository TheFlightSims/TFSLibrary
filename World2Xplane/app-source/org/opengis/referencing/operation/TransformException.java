/*    */ package org.opengis.referencing.operation;
/*    */ 
/*    */ public class TransformException extends Exception {
/*    */   private static final long serialVersionUID = -8923944544398567533L;
/*    */   
/*    */   private MathTransform lastCompletedTransform;
/*    */   
/*    */   public TransformException() {}
/*    */   
/*    */   public TransformException(String message) {
/* 62 */     super(message);
/*    */   }
/*    */   
/*    */   public TransformException(String message, Throwable cause) {
/* 74 */     super(message, cause);
/*    */   }
/*    */   
/*    */   public MathTransform getLastCompletedTransform() {
/* 87 */     return this.lastCompletedTransform;
/*    */   }
/*    */   
/*    */   public void setLastCompletedTransform(MathTransform transform) {
/* 99 */     this.lastCompletedTransform = transform;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\referencing\operation\TransformException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */