/*    */ package org.opengis.referencing.operation;
/*    */ 
/*    */ import org.opengis.referencing.FactoryException;
/*    */ 
/*    */ public class OperationNotFoundException extends FactoryException {
/*    */   private static final long serialVersionUID = -382625493416204214L;
/*    */   
/*    */   public OperationNotFoundException() {}
/*    */   
/*    */   public OperationNotFoundException(String message) {
/* 46 */     super(message);
/*    */   }
/*    */   
/*    */   public OperationNotFoundException(String message, Throwable cause) {
/* 56 */     super(message, cause);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\referencing\operation\OperationNotFoundException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */