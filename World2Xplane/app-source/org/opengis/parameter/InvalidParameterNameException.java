/*    */ package org.opengis.parameter;
/*    */ 
/*    */ import org.opengis.annotation.Specification;
/*    */ import org.opengis.annotation.UML;
/*    */ 
/*    */ @UML(identifier = "GC_InvalidParameterName", specification = Specification.ISO_19111)
/*    */ public class InvalidParameterNameException extends IllegalArgumentException {
/*    */   private static final long serialVersionUID = -8473266898408204803L;
/*    */   
/*    */   private final String parameterName;
/*    */   
/*    */   public InvalidParameterNameException(String message, String parameterName) {
/* 47 */     super(message);
/* 48 */     this.parameterName = parameterName;
/*    */   }
/*    */   
/*    */   public String getParameterName() {
/* 57 */     return this.parameterName;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\parameter\InvalidParameterNameException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */