/*    */ package org.apache.commons.math3.analysis.function;
/*    */ 
/*    */ import org.apache.commons.math3.analysis.DifferentiableUnivariateFunction;
/*    */ import org.apache.commons.math3.analysis.UnivariateFunction;
/*    */ import org.apache.commons.math3.util.FastMath;
/*    */ 
/*    */ public class Sqrt implements DifferentiableUnivariateFunction {
/*    */   public double value(double x) {
/* 33 */     return FastMath.sqrt(x);
/*    */   }
/*    */   
/*    */   public UnivariateFunction derivative() {
/* 38 */     return new UnivariateFunction() {
/*    */         public double value(double x) {
/* 41 */           return 1.0D / 2.0D * FastMath.sqrt(x);
/*    */         }
/*    */       };
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\analysis\function\Sqrt.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */