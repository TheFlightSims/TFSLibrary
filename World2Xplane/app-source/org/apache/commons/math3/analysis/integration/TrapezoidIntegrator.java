/*     */ package org.apache.commons.math3.analysis.integration;
/*     */ 
/*     */ import org.apache.commons.math3.exception.MaxCountExceededException;
/*     */ import org.apache.commons.math3.exception.NotStrictlyPositiveException;
/*     */ import org.apache.commons.math3.exception.NumberIsTooLargeException;
/*     */ import org.apache.commons.math3.exception.NumberIsTooSmallException;
/*     */ import org.apache.commons.math3.exception.TooManyEvaluationsException;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ 
/*     */ public class TrapezoidIntegrator extends BaseAbstractUnivariateIntegrator {
/*     */   public static final int TRAPEZOID_MAX_ITERATIONS_COUNT = 64;
/*     */   
/*     */   private double s;
/*     */   
/*     */   public TrapezoidIntegrator(double relativeAccuracy, double absoluteAccuracy, int minimalIterationCount, int maximalIterationCount) throws NotStrictlyPositiveException, NumberIsTooSmallException, NumberIsTooLargeException {
/*  64 */     super(relativeAccuracy, absoluteAccuracy, minimalIterationCount, maximalIterationCount);
/*  65 */     if (maximalIterationCount > 64)
/*  66 */       throw new NumberIsTooLargeException(Integer.valueOf(maximalIterationCount), Integer.valueOf(64), false); 
/*     */   }
/*     */   
/*     */   public TrapezoidIntegrator(int minimalIterationCount, int maximalIterationCount) throws NotStrictlyPositiveException, NumberIsTooSmallException, NumberIsTooLargeException {
/*  86 */     super(minimalIterationCount, maximalIterationCount);
/*  87 */     if (maximalIterationCount > 64)
/*  88 */       throw new NumberIsTooLargeException(Integer.valueOf(maximalIterationCount), Integer.valueOf(64), false); 
/*     */   }
/*     */   
/*     */   public TrapezoidIntegrator() {
/*  98 */     super(3, 64);
/*     */   }
/*     */   
/*     */   double stage(BaseAbstractUnivariateIntegrator baseIntegrator, int n) throws TooManyEvaluationsException {
/* 119 */     if (n == 0) {
/* 120 */       double d1 = baseIntegrator.getMax();
/* 121 */       double d2 = baseIntegrator.getMin();
/* 122 */       this.s = 0.5D * (d1 - d2) * (baseIntegrator.computeObjectiveValue(d2) + baseIntegrator.computeObjectiveValue(d1));
/* 125 */       return this.s;
/*     */     } 
/* 127 */     long np = 1L << n - 1;
/* 128 */     double sum = 0.0D;
/* 129 */     double max = baseIntegrator.getMax();
/* 130 */     double min = baseIntegrator.getMin();
/* 132 */     double spacing = (max - min) / np;
/* 133 */     double x = min + 0.5D * spacing;
/*     */     long i;
/* 134 */     for (i = 0L; i < np; i++) {
/* 135 */       sum += baseIntegrator.computeObjectiveValue(x);
/* 136 */       x += spacing;
/*     */     } 
/* 139 */     this.s = 0.5D * (this.s + sum * spacing);
/* 140 */     return this.s;
/*     */   }
/*     */   
/*     */   protected double doIntegrate() throws TooManyEvaluationsException, MaxCountExceededException {
/* 149 */     double oldt = stage(this, 0);
/* 150 */     this.iterations.incrementCount();
/*     */     while (true) {
/* 152 */       int i = this.iterations.getCount();
/* 153 */       double t = stage(this, i);
/* 154 */       if (i >= getMinimalIterationCount()) {
/* 155 */         double delta = FastMath.abs(t - oldt);
/* 156 */         double rLimit = getRelativeAccuracy() * (FastMath.abs(oldt) + FastMath.abs(t)) * 0.5D;
/* 158 */         if (delta <= rLimit || delta <= getAbsoluteAccuracy())
/* 159 */           return t; 
/*     */       } 
/* 162 */       oldt = t;
/* 163 */       this.iterations.incrementCount();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\analysis\integration\TrapezoidIntegrator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */