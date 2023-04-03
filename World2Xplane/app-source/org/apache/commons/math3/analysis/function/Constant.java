/*    */ package org.apache.commons.math3.analysis.function;
/*    */ 
/*    */ import org.apache.commons.math3.analysis.DifferentiableUnivariateFunction;
/*    */ import org.apache.commons.math3.analysis.UnivariateFunction;
/*    */ 
/*    */ public class Constant implements DifferentiableUnivariateFunction {
/*    */   private final double c;
/*    */   
/*    */   public Constant(double c) {
/* 36 */     this.c = c;
/*    */   }
/*    */   
/*    */   public double value(double x) {
/* 41 */     return this.c;
/*    */   }
/*    */   
/*    */   public DifferentiableUnivariateFunction derivative() {
/* 46 */     return new Constant(0.0D);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\analysis\function\Constant.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */