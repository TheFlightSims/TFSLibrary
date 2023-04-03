/*     */ package org.apache.commons.math3.analysis.solvers;
/*     */ 
/*     */ import org.apache.commons.math3.exception.NoBracketingException;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ 
/*     */ public class MullerSolver2 extends AbstractUnivariateSolver {
/*     */   private static final double DEFAULT_ABSOLUTE_ACCURACY = 1.0E-6D;
/*     */   
/*     */   public MullerSolver2() {
/*  57 */     this(1.0E-6D);
/*     */   }
/*     */   
/*     */   public MullerSolver2(double absoluteAccuracy) {
/*  65 */     super(absoluteAccuracy);
/*     */   }
/*     */   
/*     */   public MullerSolver2(double relativeAccuracy, double absoluteAccuracy) {
/*  75 */     super(relativeAccuracy, absoluteAccuracy);
/*     */   }
/*     */   
/*     */   protected double doSolve() {
/*  83 */     double min = getMin();
/*  84 */     double max = getMax();
/*  86 */     verifyInterval(min, max);
/*  88 */     double relativeAccuracy = getRelativeAccuracy();
/*  89 */     double absoluteAccuracy = getAbsoluteAccuracy();
/*  90 */     double functionValueAccuracy = getFunctionValueAccuracy();
/*  96 */     double x0 = min;
/*  97 */     double y0 = computeObjectiveValue(x0);
/*  98 */     if (FastMath.abs(y0) < functionValueAccuracy)
/*  99 */       return x0; 
/* 101 */     double x1 = max;
/* 102 */     double y1 = computeObjectiveValue(x1);
/* 103 */     if (FastMath.abs(y1) < functionValueAccuracy)
/* 104 */       return x1; 
/* 107 */     if (y0 * y1 > 0.0D)
/* 108 */       throw new NoBracketingException(x0, x1, y0, y1); 
/* 111 */     double x2 = 0.5D * (x0 + x1);
/* 112 */     double y2 = computeObjectiveValue(x2);
/* 114 */     double oldx = Double.POSITIVE_INFINITY;
/*     */     while (true) {
/* 117 */       double x, denominator, q = (x2 - x1) / (x1 - x0);
/* 118 */       double a = q * (y2 - (1.0D + q) * y1 + q * y0);
/* 119 */       double b = (2.0D * q + 1.0D) * y2 - (1.0D + q) * (1.0D + q) * y1 + q * q * y0;
/* 120 */       double c = (1.0D + q) * y2;
/* 121 */       double delta = b * b - 4.0D * a * c;
/* 124 */       if (delta >= 0.0D) {
/* 126 */         double dplus = b + FastMath.sqrt(delta);
/* 127 */         double dminus = b - FastMath.sqrt(delta);
/* 128 */         denominator = (FastMath.abs(dplus) > FastMath.abs(dminus)) ? dplus : dminus;
/*     */       } else {
/* 131 */         denominator = FastMath.sqrt(b * b - delta);
/*     */       } 
/* 133 */       if (denominator != 0.0D) {
/* 134 */         x = x2 - 2.0D * c * (x2 - x1) / denominator;
/* 137 */         while (x == x1 || x == x2)
/* 138 */           x += absoluteAccuracy; 
/*     */       } else {
/* 142 */         x = min + FastMath.random() * (max - min);
/* 143 */         oldx = Double.POSITIVE_INFINITY;
/*     */       } 
/* 145 */       double y = computeObjectiveValue(x);
/* 148 */       double tolerance = FastMath.max(relativeAccuracy * FastMath.abs(x), absoluteAccuracy);
/* 149 */       if (FastMath.abs(x - oldx) <= tolerance || FastMath.abs(y) <= functionValueAccuracy)
/* 151 */         return x; 
/* 155 */       x0 = x1;
/* 156 */       y0 = y1;
/* 157 */       x1 = x2;
/* 158 */       y1 = y2;
/* 159 */       x2 = x;
/* 160 */       y2 = y;
/* 161 */       oldx = x;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\analysis\solvers\MullerSolver2.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */