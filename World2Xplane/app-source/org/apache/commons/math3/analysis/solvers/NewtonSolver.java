/*    */ package org.apache.commons.math3.analysis.solvers;
/*    */ 
/*    */ import org.apache.commons.math3.analysis.DifferentiableUnivariateFunction;
/*    */ import org.apache.commons.math3.analysis.UnivariateFunction;
/*    */ import org.apache.commons.math3.util.FastMath;
/*    */ 
/*    */ public class NewtonSolver extends AbstractDifferentiableUnivariateSolver {
/*    */   private static final double DEFAULT_ABSOLUTE_ACCURACY = 1.0E-6D;
/*    */   
/*    */   public NewtonSolver() {
/* 39 */     this(1.0E-6D);
/*    */   }
/*    */   
/*    */   public NewtonSolver(double absoluteAccuracy) {
/* 47 */     super(absoluteAccuracy);
/*    */   }
/*    */   
/*    */   public double solve(int maxEval, DifferentiableUnivariateFunction f, double min, double max) {
/* 66 */     return solve(maxEval, f, UnivariateSolverUtils.midpoint(min, max));
/*    */   }
/*    */   
/*    */   protected double doSolve() {
/* 74 */     double startValue = getStartValue();
/* 75 */     double absoluteAccuracy = getAbsoluteAccuracy();
/* 77 */     double x0 = startValue;
/*    */     while (true) {
/* 80 */       double x1 = x0 - computeObjectiveValue(x0) / computeDerivativeObjectiveValue(x0);
/* 81 */       if (FastMath.abs(x1 - x0) <= absoluteAccuracy)
/* 82 */         return x1; 
/* 85 */       x0 = x1;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\analysis\solvers\NewtonSolver.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */