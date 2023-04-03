/*     */ package org.apache.commons.math3.analysis.integration;
/*     */ 
/*     */ import org.apache.commons.math3.exception.MaxCountExceededException;
/*     */ import org.apache.commons.math3.exception.NotStrictlyPositiveException;
/*     */ import org.apache.commons.math3.exception.NumberIsTooLargeException;
/*     */ import org.apache.commons.math3.exception.NumberIsTooSmallException;
/*     */ import org.apache.commons.math3.exception.TooManyEvaluationsException;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ 
/*     */ public class SimpsonIntegrator extends BaseAbstractUnivariateIntegrator {
/*     */   public static final int SIMPSON_MAX_ITERATIONS_COUNT = 64;
/*     */   
/*     */   public SimpsonIntegrator(double relativeAccuracy, double absoluteAccuracy, int minimalIterationCount, int maximalIterationCount) throws NotStrictlyPositiveException, NumberIsTooSmallException, NumberIsTooLargeException {
/*  62 */     super(relativeAccuracy, absoluteAccuracy, minimalIterationCount, maximalIterationCount);
/*  63 */     if (maximalIterationCount > 64)
/*  64 */       throw new NumberIsTooLargeException(Integer.valueOf(maximalIterationCount), Integer.valueOf(64), false); 
/*     */   }
/*     */   
/*     */   public SimpsonIntegrator(int minimalIterationCount, int maximalIterationCount) throws NotStrictlyPositiveException, NumberIsTooSmallException, NumberIsTooLargeException {
/*  84 */     super(minimalIterationCount, maximalIterationCount);
/*  85 */     if (maximalIterationCount > 64)
/*  86 */       throw new NumberIsTooLargeException(Integer.valueOf(maximalIterationCount), Integer.valueOf(64), false); 
/*     */   }
/*     */   
/*     */   public SimpsonIntegrator() {
/*  96 */     super(3, 64);
/*     */   }
/*     */   
/*     */   protected double doIntegrate() throws TooManyEvaluationsException, MaxCountExceededException {
/* 104 */     TrapezoidIntegrator qtrap = new TrapezoidIntegrator();
/* 105 */     if (getMinimalIterationCount() == 1)
/* 106 */       return (4.0D * qtrap.stage(this, 1) - qtrap.stage(this, 0)) / 3.0D; 
/* 110 */     double olds = 0.0D;
/* 111 */     double oldt = qtrap.stage(this, 0);
/*     */     while (true) {
/* 113 */       double t = qtrap.stage(this, this.iterations.getCount());
/* 114 */       this.iterations.incrementCount();
/* 115 */       double s = (4.0D * t - oldt) / 3.0D;
/* 116 */       if (this.iterations.getCount() >= getMinimalIterationCount()) {
/* 117 */         double delta = FastMath.abs(s - olds);
/* 118 */         double rLimit = getRelativeAccuracy() * (FastMath.abs(olds) + FastMath.abs(s)) * 0.5D;
/* 120 */         if (delta <= rLimit || delta <= getAbsoluteAccuracy())
/* 121 */           return s; 
/*     */       } 
/* 124 */       olds = s;
/* 125 */       oldt = t;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\analysis\integration\SimpsonIntegrator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */