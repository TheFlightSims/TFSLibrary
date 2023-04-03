/*     */ package org.apache.commons.math3.analysis.solvers;
/*     */ 
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ 
/*     */ public class SecantSolver extends AbstractUnivariateSolver {
/*     */   protected static final double DEFAULT_ABSOLUTE_ACCURACY = 1.0E-6D;
/*     */   
/*     */   public SecantSolver() {
/*  48 */     super(1.0E-6D);
/*     */   }
/*     */   
/*     */   public SecantSolver(double absoluteAccuracy) {
/*  57 */     super(absoluteAccuracy);
/*     */   }
/*     */   
/*     */   public SecantSolver(double relativeAccuracy, double absoluteAccuracy) {
/*  68 */     super(relativeAccuracy, absoluteAccuracy);
/*     */   }
/*     */   
/*     */   protected final double doSolve() {
/*  75 */     double x0 = getMin();
/*  76 */     double x1 = getMax();
/*  77 */     double f0 = computeObjectiveValue(x0);
/*  78 */     double f1 = computeObjectiveValue(x1);
/*  83 */     if (f0 == 0.0D)
/*  84 */       return x0; 
/*  86 */     if (f1 == 0.0D)
/*  87 */       return x1; 
/*  91 */     verifyBracketing(x0, x1);
/*  94 */     double ftol = getFunctionValueAccuracy();
/*  95 */     double atol = getAbsoluteAccuracy();
/*  96 */     double rtol = getRelativeAccuracy();
/*     */     while (true) {
/* 101 */       double x = x1 - f1 * (x1 - x0) / (f1 - f0);
/* 102 */       double fx = computeObjectiveValue(x);
/* 107 */       if (fx == 0.0D)
/* 108 */         return x; 
/* 112 */       x0 = x1;
/* 113 */       f0 = f1;
/* 114 */       x1 = x;
/* 115 */       f1 = fx;
/* 120 */       if (FastMath.abs(f1) <= ftol)
/* 121 */         return x1; 
/* 126 */       if (FastMath.abs(x1 - x0) < FastMath.max(rtol * FastMath.abs(x1), atol))
/* 127 */         return x1; 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\analysis\solvers\SecantSolver.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */