/*    */ package org.apache.commons.math3.analysis.solvers;
/*    */ 
/*    */ import org.apache.commons.math3.util.FastMath;
/*    */ 
/*    */ public class BisectionSolver extends AbstractUnivariateSolver {
/*    */   private static final double DEFAULT_ABSOLUTE_ACCURACY = 1.0E-6D;
/*    */   
/*    */   public BisectionSolver() {
/* 37 */     this(1.0E-6D);
/*    */   }
/*    */   
/*    */   public BisectionSolver(double absoluteAccuracy) {
/* 45 */     super(absoluteAccuracy);
/*    */   }
/*    */   
/*    */   public BisectionSolver(double relativeAccuracy, double absoluteAccuracy) {
/* 55 */     super(relativeAccuracy, absoluteAccuracy);
/*    */   }
/*    */   
/*    */   protected double doSolve() {
/* 63 */     double min = getMin();
/* 64 */     double max = getMax();
/* 65 */     verifyInterval(min, max);
/* 66 */     double absoluteAccuracy = getAbsoluteAccuracy();
/*    */     while (true) {
/* 72 */       double m = UnivariateSolverUtils.midpoint(min, max);
/* 73 */       double fmin = computeObjectiveValue(min);
/* 74 */       double fm = computeObjectiveValue(m);
/* 76 */       if (fm * fmin > 0.0D) {
/* 78 */         min = m;
/*    */       } else {
/* 81 */         max = m;
/*    */       } 
/* 84 */       if (FastMath.abs(max - min) <= absoluteAccuracy) {
/* 85 */         m = UnivariateSolverUtils.midpoint(min, max);
/* 86 */         return m;
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\analysis\solvers\BisectionSolver.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */