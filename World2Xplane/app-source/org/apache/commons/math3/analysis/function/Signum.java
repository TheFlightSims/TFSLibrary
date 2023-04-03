/*    */ package org.apache.commons.math3.analysis.function;
/*    */ 
/*    */ import org.apache.commons.math3.analysis.UnivariateFunction;
/*    */ import org.apache.commons.math3.util.FastMath;
/*    */ 
/*    */ public class Signum implements UnivariateFunction {
/*    */   public double value(double x) {
/* 32 */     return FastMath.signum(x);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\analysis\function\Signum.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */