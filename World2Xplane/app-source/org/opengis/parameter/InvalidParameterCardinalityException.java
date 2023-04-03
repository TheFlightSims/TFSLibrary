/*    */ package org.opengis.parameter;
/*    */ 
/*    */ public class InvalidParameterCardinalityException extends IllegalStateException {
/*    */   private static final long serialVersionUID = 4030549323541812311L;
/*    */   
/*    */   private final String parameterName;
/*    */   
/*    */   public InvalidParameterCardinalityException(String message, String parameterName) {
/* 57 */     super(message);
/* 58 */     this.parameterName = parameterName;
/*    */   }
/*    */   
/*    */   public String getParameterName() {
/* 67 */     return this.parameterName;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\parameter\InvalidParameterCardinalityException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */