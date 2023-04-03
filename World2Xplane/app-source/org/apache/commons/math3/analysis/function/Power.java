/*    */ package org.apache.commons.math3.analysis.function;
/*    */ 
/*    */ import org.apache.commons.math3.analysis.DifferentiableUnivariateFunction;
/*    */ import org.apache.commons.math3.analysis.UnivariateFunction;
/*    */ import org.apache.commons.math3.util.FastMath;
/*    */ 
/*    */ public class Power implements DifferentiableUnivariateFunction {
/*    */   private final double p;
/*    */   
/*    */   public Power(double p) {
/* 38 */     this.p = p;
/*    */   }
/*    */   
/*    */   public double value(double x) {
/* 43 */     return FastMath.pow(x, this.p);
/*    */   }
/*    */   
/*    */   public UnivariateFunction derivative() {
/* 48 */     return new UnivariateFunction() {
/*    */         public double value(double x) {
/* 51 */           return Power.this.p * FastMath.pow(x, Power.this.p - 1.0D);
/*    */         }
/*    */       };
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\analysis\function\Power.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */