/*    */ package org.opengis.parameter;
/*    */ 
/*    */ public class ParameterNotFoundException extends IllegalArgumentException {
/*    */   private static final long serialVersionUID = -8074834945993975175L;
/*    */   
/*    */   private final String parameterName;
/*    */   
/*    */   public ParameterNotFoundException(String message, String parameterName) {
/* 45 */     super(message);
/* 46 */     this.parameterName = parameterName;
/*    */   }
/*    */   
/*    */   public String getParameterName() {
/* 55 */     return this.parameterName;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\parameter\ParameterNotFoundException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */