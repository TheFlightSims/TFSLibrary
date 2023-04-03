/*    */ package org.opengis.parameter;
/*    */ 
/*    */ public class InvalidParameterTypeException extends IllegalStateException {
/*    */   private static final long serialVersionUID = 2740762597003093176L;
/*    */   
/*    */   private final String parameterName;
/*    */   
/*    */   public InvalidParameterTypeException(String message, String parameterName) {
/* 51 */     super(message);
/* 52 */     this.parameterName = parameterName;
/*    */   }
/*    */   
/*    */   public String getParameterName() {
/* 61 */     return this.parameterName;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\parameter\InvalidParameterTypeException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */