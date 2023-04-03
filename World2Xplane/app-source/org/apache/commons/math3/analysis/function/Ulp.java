/*    */ package org.apache.commons.math3.analysis.function;
/*    */ 
/*    */ import org.apache.commons.math3.analysis.UnivariateFunction;
/*    */ import org.apache.commons.math3.util.FastMath;
/*    */ 
/*    */ public class Ulp implements UnivariateFunction {
/*    */   public double value(double x) {
/* 32 */     return FastMath.ulp(x);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\analysis\function\Ulp.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */