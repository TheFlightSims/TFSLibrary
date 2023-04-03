/*    */ package org.apache.commons.math3.analysis.function;
/*    */ 
/*    */ import org.apache.commons.math3.analysis.DifferentiableUnivariateFunction;
/*    */ import org.apache.commons.math3.analysis.UnivariateFunction;
/*    */ 
/*    */ public class Inverse implements DifferentiableUnivariateFunction {
/*    */   public double value(double x) {
/* 32 */     return 1.0D / x;
/*    */   }
/*    */   
/*    */   public UnivariateFunction derivative() {
/* 37 */     return new UnivariateFunction() {
/*    */         public double value(double x) {
/* 40 */           return -1.0D / x * x;
/*    */         }
/*    */       };
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\analysis\function\Inverse.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */