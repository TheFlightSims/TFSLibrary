/*    */ package org.apache.commons.math3.analysis.function;
/*    */ 
/*    */ import org.apache.commons.math3.analysis.DifferentiableUnivariateFunction;
/*    */ import org.apache.commons.math3.analysis.UnivariateFunction;
/*    */ 
/*    */ public class Identity implements DifferentiableUnivariateFunction {
/*    */   public double value(double x) {
/* 31 */     return x;
/*    */   }
/*    */   
/*    */   public DifferentiableUnivariateFunction derivative() {
/* 36 */     return new Constant(1.0D);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\analysis\function\Identity.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */