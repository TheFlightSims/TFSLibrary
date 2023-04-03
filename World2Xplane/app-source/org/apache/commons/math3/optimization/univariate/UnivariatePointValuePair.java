/*    */ package org.apache.commons.math3.optimization.univariate;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class UnivariatePointValuePair implements Serializable {
/*    */   private static final long serialVersionUID = 1003888396256744753L;
/*    */   
/*    */   private final double point;
/*    */   
/*    */   private final double value;
/*    */   
/*    */   public UnivariatePointValuePair(double point, double value) {
/* 46 */     this.point = point;
/* 47 */     this.value = value;
/*    */   }
/*    */   
/*    */   public double getPoint() {
/* 56 */     return this.point;
/*    */   }
/*    */   
/*    */   public double getValue() {
/* 65 */     return this.value;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\optimizatio\\univariate\UnivariatePointValuePair.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */