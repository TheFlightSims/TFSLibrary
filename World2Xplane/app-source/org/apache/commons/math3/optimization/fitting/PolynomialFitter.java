/*    */ package org.apache.commons.math3.optimization.fitting;
/*    */ 
/*    */ import org.apache.commons.math3.analysis.ParametricUnivariateFunction;
/*    */ import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;
/*    */ import org.apache.commons.math3.optimization.DifferentiableMultivariateVectorOptimizer;
/*    */ 
/*    */ public class PolynomialFitter extends CurveFitter {
/*    */   private final int degree;
/*    */   
/*    */   public PolynomialFitter(int degree, DifferentiableMultivariateVectorOptimizer optimizer) {
/* 44 */     super(optimizer);
/* 45 */     this.degree = degree;
/*    */   }
/*    */   
/*    */   public double[] fit() {
/* 56 */     return fit((ParametricUnivariateFunction)new PolynomialFunction.Parametric(), new double[this.degree + 1]);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\optimization\fitting\PolynomialFitter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */