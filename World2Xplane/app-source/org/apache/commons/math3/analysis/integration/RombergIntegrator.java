/*     */ package org.apache.commons.math3.analysis.integration;
/*     */ 
/*     */ import org.apache.commons.math3.exception.MaxCountExceededException;
/*     */ import org.apache.commons.math3.exception.NotStrictlyPositiveException;
/*     */ import org.apache.commons.math3.exception.NumberIsTooLargeException;
/*     */ import org.apache.commons.math3.exception.NumberIsTooSmallException;
/*     */ import org.apache.commons.math3.exception.TooManyEvaluationsException;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ 
/*     */ public class RombergIntegrator extends BaseAbstractUnivariateIntegrator {
/*     */   public static final int ROMBERG_MAX_ITERATIONS_COUNT = 32;
/*     */   
/*     */   public RombergIntegrator(double relativeAccuracy, double absoluteAccuracy, int minimalIterationCount, int maximalIterationCount) throws NotStrictlyPositiveException, NumberIsTooSmallException, NumberIsTooLargeException {
/*  63 */     super(relativeAccuracy, absoluteAccuracy, minimalIterationCount, maximalIterationCount);
/*  64 */     if (maximalIterationCount > 32)
/*  65 */       throw new NumberIsTooLargeException(Integer.valueOf(maximalIterationCount), Integer.valueOf(32), false); 
/*     */   }
/*     */   
/*     */   public RombergIntegrator(int minimalIterationCount, int maximalIterationCount) throws NotStrictlyPositiveException, NumberIsTooSmallException, NumberIsTooLargeException {
/*  85 */     super(minimalIterationCount, maximalIterationCount);
/*  86 */     if (maximalIterationCount > 32)
/*  87 */       throw new NumberIsTooLargeException(Integer.valueOf(maximalIterationCount), Integer.valueOf(32), false); 
/*     */   }
/*     */   
/*     */   public RombergIntegrator() {
/*  97 */     super(3, 32);
/*     */   }
/*     */   
/*     */   protected double doIntegrate() throws TooManyEvaluationsException, MaxCountExceededException {
/* 105 */     int m = this.iterations.getMaximalCount() + 1;
/* 106 */     double[] previousRow = new double[m];
/* 107 */     double[] currentRow = new double[m];
/* 109 */     TrapezoidIntegrator qtrap = new TrapezoidIntegrator();
/* 110 */     currentRow[0] = qtrap.stage(this, 0);
/* 111 */     this.iterations.incrementCount();
/* 112 */     double olds = currentRow[0];
/*     */     while (true) {
/* 115 */       int i = this.iterations.getCount();
/* 118 */       double[] tmpRow = previousRow;
/* 119 */       previousRow = currentRow;
/* 120 */       currentRow = tmpRow;
/* 122 */       currentRow[0] = qtrap.stage(this, i);
/* 123 */       this.iterations.incrementCount();
/* 124 */       for (int j = 1; j <= i; j++) {
/* 126 */         double r = ((1L << 2 * j) - 1L);
/* 127 */         double tIJm1 = currentRow[j - 1];
/* 128 */         currentRow[j] = tIJm1 + (tIJm1 - previousRow[j - 1]) / r;
/*     */       } 
/* 130 */       double s = currentRow[i];
/* 131 */       if (i >= getMinimalIterationCount()) {
/* 132 */         double delta = FastMath.abs(s - olds);
/* 133 */         double rLimit = getRelativeAccuracy() * (FastMath.abs(olds) + FastMath.abs(s)) * 0.5D;
/* 134 */         if (delta <= rLimit || delta <= getAbsoluteAccuracy())
/* 135 */           return s; 
/*     */       } 
/* 138 */       olds = s;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\analysis\integration\RombergIntegrator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */