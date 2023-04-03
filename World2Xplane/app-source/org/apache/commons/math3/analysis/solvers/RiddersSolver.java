/*     */ package org.apache.commons.math3.analysis.solvers;
/*     */ 
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ 
/*     */ public class RiddersSolver extends AbstractUnivariateSolver {
/*     */   private static final double DEFAULT_ABSOLUTE_ACCURACY = 1.0E-6D;
/*     */   
/*     */   public RiddersSolver() {
/*  41 */     this(1.0E-6D);
/*     */   }
/*     */   
/*     */   public RiddersSolver(double absoluteAccuracy) {
/*  49 */     super(absoluteAccuracy);
/*     */   }
/*     */   
/*     */   public RiddersSolver(double relativeAccuracy, double absoluteAccuracy) {
/*  59 */     super(relativeAccuracy, absoluteAccuracy);
/*     */   }
/*     */   
/*     */   protected double doSolve() {
/*  67 */     double min = getMin();
/*  68 */     double max = getMax();
/*  72 */     double x1 = min;
/*  73 */     double y1 = computeObjectiveValue(x1);
/*  74 */     double x2 = max;
/*  75 */     double y2 = computeObjectiveValue(x2);
/*  78 */     if (y1 == 0.0D)
/*  79 */       return min; 
/*  81 */     if (y2 == 0.0D)
/*  82 */       return max; 
/*  84 */     verifyBracketing(min, max);
/*  86 */     double absoluteAccuracy = getAbsoluteAccuracy();
/*  87 */     double functionValueAccuracy = getFunctionValueAccuracy();
/*  88 */     double relativeAccuracy = getRelativeAccuracy();
/*  90 */     double oldx = Double.POSITIVE_INFINITY;
/*     */     while (true) {
/*  93 */       double x3 = 0.5D * (x1 + x2);
/*  94 */       double y3 = computeObjectiveValue(x3);
/*  95 */       if (FastMath.abs(y3) <= functionValueAccuracy)
/*  96 */         return x3; 
/*  98 */       double delta = 1.0D - y1 * y2 / y3 * y3;
/*  99 */       double correction = FastMath.signum(y2) * FastMath.signum(y3) * (x3 - x1) / FastMath.sqrt(delta);
/* 101 */       double x = x3 - correction;
/* 102 */       double y = computeObjectiveValue(x);
/* 105 */       double tolerance = FastMath.max(relativeAccuracy * FastMath.abs(x), absoluteAccuracy);
/* 106 */       if (FastMath.abs(x - oldx) <= tolerance)
/* 107 */         return x; 
/* 109 */       if (FastMath.abs(y) <= functionValueAccuracy)
/* 110 */         return x; 
/* 115 */       if (correction > 0.0D) {
/* 116 */         if (FastMath.signum(y1) + FastMath.signum(y) == 0.0D) {
/* 117 */           x2 = x;
/* 118 */           y2 = y;
/*     */         } else {
/* 120 */           x1 = x;
/* 121 */           x2 = x3;
/* 122 */           y1 = y;
/* 123 */           y2 = y3;
/*     */         } 
/* 126 */       } else if (FastMath.signum(y2) + FastMath.signum(y) == 0.0D) {
/* 127 */         x1 = x;
/* 128 */         y1 = y;
/*     */       } else {
/* 130 */         x1 = x3;
/* 131 */         x2 = x;
/* 132 */         y1 = y3;
/* 133 */         y2 = y;
/*     */       } 
/* 136 */       oldx = x;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\analysis\solvers\RiddersSolver.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */