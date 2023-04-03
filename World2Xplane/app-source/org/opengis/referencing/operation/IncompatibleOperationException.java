/*    */ package org.opengis.referencing.operation;
/*    */ 
/*    */ public class IncompatibleOperationException extends Exception {
/*    */   private static final long serialVersionUID = 3197174832430350656L;
/*    */   
/*    */   private final String operationName;
/*    */   
/*    */   public IncompatibleOperationException(String message, String operationName) {
/* 42 */     super(message);
/* 43 */     this.operationName = operationName;
/*    */   }
/*    */   
/*    */   public String getOperationName() {
/* 52 */     return this.operationName;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\referencing\operation\IncompatibleOperationException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */