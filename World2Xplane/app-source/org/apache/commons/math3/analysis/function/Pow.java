/*    */ package org.apache.commons.math3.analysis.function;
/*    */ 
/*    */ import org.apache.commons.math3.analysis.BivariateFunction;
/*    */ import org.apache.commons.math3.util.FastMath;
/*    */ 
/*    */ public class Pow implements BivariateFunction {
/*    */   public double value(double x, double y) {
/* 32 */     return FastMath.pow(x, y);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\analysis\function\Pow.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */