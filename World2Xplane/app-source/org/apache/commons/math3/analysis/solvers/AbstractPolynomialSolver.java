/*    */ package org.apache.commons.math3.analysis.solvers;
/*    */ 
/*    */ import org.apache.commons.math3.analysis.UnivariateFunction;
/*    */ import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;
/*    */ 
/*    */ public abstract class AbstractPolynomialSolver extends BaseAbstractUnivariateSolver<PolynomialFunction> implements PolynomialSolver {
/*    */   private PolynomialFunction polynomialFunction;
/*    */   
/*    */   protected AbstractPolynomialSolver(double absoluteAccuracy) {
/* 40 */     super(absoluteAccuracy);
/*    */   }
/*    */   
/*    */   protected AbstractPolynomialSolver(double relativeAccuracy, double absoluteAccuracy) {
/* 50 */     super(relativeAccuracy, absoluteAccuracy);
/*    */   }
/*    */   
/*    */   protected AbstractPolynomialSolver(double relativeAccuracy, double absoluteAccuracy, double functionValueAccuracy) {
/* 62 */     super(relativeAccuracy, absoluteAccuracy, functionValueAccuracy);
/*    */   }
/*    */   
/*    */   protected void setup(int maxEval, PolynomialFunction f, double min, double max, double startValue) {
/* 71 */     super.setup(maxEval, f, min, max, startValue);
/* 72 */     this.polynomialFunction = f;
/*    */   }
/*    */   
/*    */   protected double[] getCoefficients() {
/* 79 */     return this.polynomialFunction.getCoefficients();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\analysis\solvers\AbstractPolynomialSolver.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */