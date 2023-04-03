/*     */ package org.opengis.parameter;
/*     */ 
/*     */ import org.opengis.annotation.Specification;
/*     */ import org.opengis.annotation.UML;
/*     */ 
/*     */ @UML(identifier = "GC_InvalidParameterValue", specification = Specification.ISO_19111)
/*     */ public class InvalidParameterValueException extends IllegalArgumentException {
/*     */   private static final long serialVersionUID = 3814037056147642789L;
/*     */   
/*     */   private final String parameterName;
/*     */   
/*     */   private final Object value;
/*     */   
/*     */   public InvalidParameterValueException(String message, String parameterName, Object value) {
/*  56 */     super(message);
/*  57 */     this.parameterName = parameterName;
/*  58 */     this.value = value;
/*     */   }
/*     */   
/*     */   public InvalidParameterValueException(String message, String parameterName, double value) {
/*  70 */     this(message, parameterName, Double.valueOf(value));
/*     */   }
/*     */   
/*     */   public InvalidParameterValueException(String message, String parameterName, int value) {
/*  82 */     this(message, parameterName, Integer.valueOf(value));
/*     */   }
/*     */   
/*     */   public String getParameterName() {
/*  91 */     return this.parameterName;
/*     */   }
/*     */   
/*     */   public Object getValue() {
/* 100 */     return this.value;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\parameter\InvalidParameterValueException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */