/*    */ package org.apache.commons.math3.analysis.solvers;
/*    */ 
/*    */ import org.apache.commons.math3.analysis.DifferentiableUnivariateFunction;
/*    */ import org.apache.commons.math3.analysis.UnivariateFunction;
/*    */ 
/*    */ public abstract class AbstractDifferentiableUnivariateSolver extends BaseAbstractUnivariateSolver<DifferentiableUnivariateFunction> implements DifferentiableUnivariateSolver {
/*    */   private UnivariateFunction functionDerivative;
/*    */   
/*    */   protected AbstractDifferentiableUnivariateSolver(double absoluteAccuracy) {
/* 42 */     super(absoluteAccuracy);
/*    */   }
/*    */   
/*    */   protected AbstractDifferentiableUnivariateSolver(double relativeAccuracy, double absoluteAccuracy, double functionValueAccuracy) {
/* 55 */     super(relativeAccuracy, absoluteAccuracy, functionValueAccuracy);
/*    */   }
/*    */   
/*    */   protected double computeDerivativeObjectiveValue(double point) {
/* 67 */     incrementEvaluationCount();
/* 68 */     return this.functionDerivative.value(point);
/*    */   }
/*    */   
/*    */   protected void setup(int maxEval, DifferentiableUnivariateFunction f, double min, double max, double startValue) {
/* 77 */     super.setup(maxEval, f, min, max, startValue);
/* 78 */     this.functionDerivative = f.derivative();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\analysis\solvers\AbstractDifferentiableUnivariateSolver.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */