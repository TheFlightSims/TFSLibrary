/*    */ package org.opengis.referencing;
/*    */ 
/*    */ public class FactoryException extends Exception {
/*    */   private static final long serialVersionUID = -3414250034883898315L;
/*    */   
/*    */   public FactoryException() {}
/*    */   
/*    */   public FactoryException(String message) {
/* 53 */     super(message);
/*    */   }
/*    */   
/*    */   public FactoryException(Exception cause) {
/* 65 */     super(cause.getLocalizedMessage(), cause);
/*    */   }
/*    */   
/*    */   public FactoryException(String message, Throwable cause) {
/* 79 */     super(message, cause);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\referencing\FactoryException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */