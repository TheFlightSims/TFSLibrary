/*     */ package org.apache.commons.math3.analysis.solvers;
/*     */ 
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ 
/*     */ public class MullerSolver extends AbstractUnivariateSolver {
/*     */   private static final double DEFAULT_ABSOLUTE_ACCURACY = 1.0E-6D;
/*     */   
/*     */   public MullerSolver() {
/*  56 */     this(1.0E-6D);
/*     */   }
/*     */   
/*     */   public MullerSolver(double absoluteAccuracy) {
/*  64 */     super(absoluteAccuracy);
/*     */   }
/*     */   
/*     */   public MullerSolver(double relativeAccuracy, double absoluteAccuracy) {
/*  74 */     super(relativeAccuracy, absoluteAccuracy);
/*     */   }
/*     */   
/*     */   protected double doSolve() {
/*  82 */     double min = getMin();
/*  83 */     double max = getMax();
/*  84 */     double initial = getStartValue();
/*  86 */     double functionValueAccuracy = getFunctionValueAccuracy();
/*  88 */     verifySequence(min, initial, max);
/*  91 */     double fMin = computeObjectiveValue(min);
/*  92 */     if (FastMath.abs(fMin) < functionValueAccuracy)
/*  93 */       return min; 
/*  95 */     double fMax = computeObjectiveValue(max);
/*  96 */     if (FastMath.abs(fMax) < functionValueAccuracy)
/*  97 */       return max; 
/*  99 */     double fInitial = computeObjectiveValue(initial);
/* 100 */     if (FastMath.abs(fInitial) < functionValueAccuracy)
/* 101 */       return initial; 
/* 104 */     verifyBracketing(min, max);
/* 106 */     if (isBracketing(min, initial))
/* 107 */       return solve(min, initial, fMin, fInitial); 
/* 109 */     return solve(initial, max, fInitial, fMax);
/*     */   }
/*     */   
/*     */   private double solve(double min, double max, double fMin, double fMax) {
/* 124 */     double relativeAccuracy = getRelativeAccuracy();
/* 125 */     double absoluteAccuracy = getAbsoluteAccuracy();
/* 126 */     double functionValueAccuracy = getFunctionValueAccuracy();
/* 133 */     double x0 = min;
/* 134 */     double y0 = fMin;
/* 135 */     double x2 = max;
/* 136 */     double y2 = fMax;
/* 137 */     double x1 = 0.5D * (x0 + x2);
/* 138 */     double y1 = computeObjectiveValue(x1);
/* 140 */     double oldx = Double.POSITIVE_INFINITY;
/*     */     while (true) {
/* 146 */       double d01 = (y1 - y0) / (x1 - x0);
/* 147 */       double d12 = (y2 - y1) / (x2 - x1);
/* 148 */       double d012 = (d12 - d01) / (x2 - x0);
/* 149 */       double c1 = d01 + (x1 - x0) * d012;
/* 150 */       double delta = c1 * c1 - 4.0D * y1 * d012;
/* 151 */       double xplus = x1 + -2.0D * y1 / (c1 + FastMath.sqrt(delta));
/* 152 */       double xminus = x1 + -2.0D * y1 / (c1 - FastMath.sqrt(delta));
/* 155 */       double x = isSequence(x0, xplus, x2) ? xplus : xminus;
/* 156 */       double y = computeObjectiveValue(x);
/* 159 */       double tolerance = FastMath.max(relativeAccuracy * FastMath.abs(x), absoluteAccuracy);
/* 160 */       if (FastMath.abs(x - oldx) <= tolerance || FastMath.abs(y) <= functionValueAccuracy)
/* 162 */         return x; 
/* 169 */       boolean bisect = ((x < x1 && x1 - x0 > 0.95D * (x2 - x0)) || (x > x1 && x2 - x1 > 0.95D * (x2 - x0)) || x == x1);
/* 173 */       if (!bisect) {
/* 174 */         x0 = (x < x1) ? x0 : x1;
/* 175 */         y0 = (x < x1) ? y0 : y1;
/* 176 */         x2 = (x > x1) ? x2 : x1;
/* 177 */         y2 = (x > x1) ? y2 : y1;
/* 178 */         x1 = x;
/* 178 */         y1 = y;
/* 179 */         oldx = x;
/*     */         continue;
/*     */       } 
/* 181 */       double xm = 0.5D * (x0 + x2);
/* 182 */       double ym = computeObjectiveValue(xm);
/* 183 */       if (FastMath.signum(y0) + FastMath.signum(ym) == 0.0D) {
/* 184 */         x2 = xm;
/* 184 */         y2 = ym;
/*     */       } else {
/* 186 */         x0 = xm;
/* 186 */         y0 = ym;
/*     */       } 
/* 188 */       x1 = 0.5D * (x0 + x2);
/* 189 */       y1 = computeObjectiveValue(x1);
/* 190 */       oldx = Double.POSITIVE_INFINITY;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\analysis\solvers\MullerSolver.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */