/*    */ package org.apache.commons.math3.analysis.function;
/*    */ 
/*    */ import org.apache.commons.math3.analysis.DifferentiableUnivariateFunction;
/*    */ import org.apache.commons.math3.analysis.UnivariateFunction;
/*    */ import org.apache.commons.math3.util.FastMath;
/*    */ 
/*    */ public class Sinh implements DifferentiableUnivariateFunction {
/*    */   public double value(double x) {
/* 32 */     return FastMath.sinh(x);
/*    */   }
/*    */   
/*    */   public DifferentiableUnivariateFunction derivative() {
/* 37 */     return new Cosh();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\analysis\function\Sinh.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */