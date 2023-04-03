/*    */ package org.apache.commons.math3.analysis.function;
/*    */ 
/*    */ import org.apache.commons.math3.analysis.UnivariateFunction;
/*    */ import org.apache.commons.math3.util.FastMath;
/*    */ 
/*    */ public class Floor implements UnivariateFunction {
/*    */   public double value(double x) {
/* 32 */     return FastMath.floor(x);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\analysis\function\Floor.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */