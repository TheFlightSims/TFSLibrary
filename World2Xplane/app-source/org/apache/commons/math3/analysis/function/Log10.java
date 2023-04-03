/*    */ package org.apache.commons.math3.analysis.function;
/*    */ 
/*    */ import org.apache.commons.math3.analysis.DifferentiableUnivariateFunction;
/*    */ import org.apache.commons.math3.analysis.UnivariateFunction;
/*    */ import org.apache.commons.math3.util.FastMath;
/*    */ 
/*    */ public class Log10 implements DifferentiableUnivariateFunction {
/* 32 */   private static final double LN_10 = FastMath.log(10.0D);
/*    */   
/*    */   public double value(double x) {
/* 36 */     return FastMath.log10(x);
/*    */   }
/*    */   
/*    */   public UnivariateFunction derivative() {
/* 41 */     return new UnivariateFunction() {
/*    */         public double value(double x) {
/* 44 */           return 1.0D / x * Log10.LN_10;
/*    */         }
/*    */       };
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\analysis\function\Log10.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */