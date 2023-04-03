/*    */ package org.apache.commons.math3.analysis.function;
/*    */ 
/*    */ import org.apache.commons.math3.analysis.DifferentiableUnivariateFunction;
/*    */ import org.apache.commons.math3.analysis.UnivariateFunction;
/*    */ import org.apache.commons.math3.util.FastMath;
/*    */ 
/*    */ public class Tanh implements DifferentiableUnivariateFunction {
/*    */   public double value(double x) {
/* 33 */     return FastMath.tanh(x);
/*    */   }
/*    */   
/*    */   public UnivariateFunction derivative() {
/* 38 */     return new UnivariateFunction() {
/*    */         public double value(double x) {
/* 41 */           double tanhX = FastMath.tanh(x);
/* 42 */           return 1.0D - tanhX * tanhX;
/*    */         }
/*    */       };
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\analysis\function\Tanh.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */