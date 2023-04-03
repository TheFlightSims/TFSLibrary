/*    */ package org.opengis.referencing.operation;
/*    */ 
/*    */ public class NoninvertibleTransformException extends TransformException {
/*    */   private static final long serialVersionUID = 9184806660368158575L;
/*    */   
/*    */   public NoninvertibleTransformException() {}
/*    */   
/*    */   public NoninvertibleTransformException(String message) {
/* 44 */     super(message);
/*    */   }
/*    */   
/*    */   public NoninvertibleTransformException(String message, Throwable cause) {
/* 58 */     super(message, cause);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\referencing\operation\NoninvertibleTransformException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */