/*     */ package org.apache.commons.math3.analysis.integration;
/*     */ 
/*     */ import org.apache.commons.math3.exception.MathIllegalArgumentException;
/*     */ import org.apache.commons.math3.exception.MaxCountExceededException;
/*     */ import org.apache.commons.math3.exception.NotStrictlyPositiveException;
/*     */ import org.apache.commons.math3.exception.NumberIsTooSmallException;
/*     */ import org.apache.commons.math3.exception.TooManyEvaluationsException;
/*     */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ 
/*     */ public class LegendreGaussIntegrator extends BaseAbstractUnivariateIntegrator {
/*  57 */   private static final double[] ABSCISSAS_2 = new double[] { -1.0D / FastMath.sqrt(3.0D), 1.0D / FastMath.sqrt(3.0D) };
/*     */   
/*  63 */   private static final double[] WEIGHTS_2 = new double[] { 1.0D, 1.0D };
/*     */   
/*  69 */   private static final double[] ABSCISSAS_3 = new double[] { -FastMath.sqrt(0.6D), 0.0D, FastMath.sqrt(0.6D) };
/*     */   
/*  76 */   private static final double[] WEIGHTS_3 = new double[] { 0.5555555555555556D, 0.8888888888888888D, 0.5555555555555556D };
/*     */   
/*  83 */   private static final double[] ABSCISSAS_4 = new double[] { -FastMath.sqrt((15.0D + 2.0D * FastMath.sqrt(30.0D)) / 35.0D), -FastMath.sqrt((15.0D - 2.0D * FastMath.sqrt(30.0D)) / 35.0D), FastMath.sqrt((15.0D - 2.0D * FastMath.sqrt(30.0D)) / 35.0D), FastMath.sqrt((15.0D + 2.0D * FastMath.sqrt(30.0D)) / 35.0D) };
/*     */   
/*  91 */   private static final double[] WEIGHTS_4 = new double[] { (90.0D - 5.0D * FastMath.sqrt(30.0D)) / 180.0D, (90.0D + 5.0D * FastMath.sqrt(30.0D)) / 180.0D, (90.0D + 5.0D * FastMath.sqrt(30.0D)) / 180.0D, (90.0D - 5.0D * FastMath.sqrt(30.0D)) / 180.0D };
/*     */   
/*  99 */   private static final double[] ABSCISSAS_5 = new double[] { -FastMath.sqrt((35.0D + 2.0D * FastMath.sqrt(70.0D)) / 63.0D), -FastMath.sqrt((35.0D - 2.0D * FastMath.sqrt(70.0D)) / 63.0D), 0.0D, FastMath.sqrt((35.0D - 2.0D * FastMath.sqrt(70.0D)) / 63.0D), FastMath.sqrt((35.0D + 2.0D * FastMath.sqrt(70.0D)) / 63.0D) };
/*     */   
/* 108 */   private static final double[] WEIGHTS_5 = new double[] { (322.0D - 13.0D * FastMath.sqrt(70.0D)) / 900.0D, (322.0D + 13.0D * FastMath.sqrt(70.0D)) / 900.0D, 0.5688888888888889D, (322.0D + 13.0D * FastMath.sqrt(70.0D)) / 900.0D, (322.0D - 13.0D * FastMath.sqrt(70.0D)) / 900.0D };
/*     */   
/*     */   private final double[] abscissas;
/*     */   
/*     */   private final double[] weights;
/*     */   
/*     */   public LegendreGaussIntegrator(int n, double relativeAccuracy, double absoluteAccuracy, int minimalIterationCount, int maximalIterationCount) throws NotStrictlyPositiveException, NumberIsTooSmallException {
/* 140 */     super(relativeAccuracy, absoluteAccuracy, minimalIterationCount, maximalIterationCount);
/* 141 */     switch (n) {
/*     */       case 2:
/* 143 */         this.abscissas = ABSCISSAS_2;
/* 144 */         this.weights = WEIGHTS_2;
/*     */         return;
/*     */       case 3:
/* 147 */         this.abscissas = ABSCISSAS_3;
/* 148 */         this.weights = WEIGHTS_3;
/*     */         return;
/*     */       case 4:
/* 151 */         this.abscissas = ABSCISSAS_4;
/* 152 */         this.weights = WEIGHTS_4;
/*     */         return;
/*     */       case 5:
/* 155 */         this.abscissas = ABSCISSAS_5;
/* 156 */         this.weights = WEIGHTS_5;
/*     */         return;
/*     */     } 
/* 159 */     throw new MathIllegalArgumentException(LocalizedFormats.N_POINTS_GAUSS_LEGENDRE_INTEGRATOR_NOT_SUPPORTED, new Object[] { Integer.valueOf(n), Integer.valueOf(2), Integer.valueOf(5) });
/*     */   }
/*     */   
/*     */   public LegendreGaussIntegrator(int n, double relativeAccuracy, double absoluteAccuracy) {
/* 175 */     this(n, relativeAccuracy, absoluteAccuracy, 3, 2147483647);
/*     */   }
/*     */   
/*     */   public LegendreGaussIntegrator(int n, int minimalIterationCount, int maximalIterationCount) {
/* 192 */     this(n, 1.0E-6D, 1.0E-15D, minimalIterationCount, maximalIterationCount);
/*     */   }
/*     */   
/*     */   protected double doIntegrate() throws TooManyEvaluationsException, MaxCountExceededException {
/* 202 */     double oldt = stage(1);
/* 204 */     int n = 2;
/*     */     while (true) {
/* 208 */       double t = stage(n);
/* 211 */       double delta = FastMath.abs(t - oldt);
/* 212 */       double limit = FastMath.max(getAbsoluteAccuracy(), getRelativeAccuracy() * (FastMath.abs(oldt) + FastMath.abs(t)) * 0.5D);
/* 217 */       if (this.iterations.getCount() + 1 >= getMinimalIterationCount() && delta <= limit)
/* 218 */         return t; 
/* 222 */       double ratio = FastMath.min(4.0D, FastMath.pow(delta / limit, 0.5D / this.abscissas.length));
/* 223 */       n = FastMath.max((int)(ratio * n), n + 1);
/* 224 */       oldt = t;
/* 225 */       this.iterations.incrementCount();
/*     */     } 
/*     */   }
/*     */   
/*     */   private double stage(int n) throws TooManyEvaluationsException {
/* 242 */     double step = (getMax() - getMin()) / n;
/* 243 */     double halfStep = step / 2.0D;
/* 246 */     double midPoint = getMin() + halfStep;
/* 247 */     double sum = 0.0D;
/* 248 */     for (int i = 0; i < n; i++) {
/* 249 */       for (int j = 0; j < this.abscissas.length; j++)
/* 250 */         sum += this.weights[j] * computeObjectiveValue(midPoint + halfStep * this.abscissas[j]); 
/* 252 */       midPoint += step;
/*     */     } 
/* 255 */     return halfStep * sum;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\analysis\integration\LegendreGaussIntegrator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */