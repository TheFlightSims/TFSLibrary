/*     */ package org.apache.commons.math3.analysis.interpolation;
/*     */ 
/*     */ import org.apache.commons.math3.analysis.UnivariateFunction;
/*     */ import org.apache.commons.math3.exception.NumberIsTooSmallException;
/*     */ import org.apache.commons.math3.util.MathArrays;
/*     */ import org.apache.commons.math3.util.MathUtils;
/*     */ 
/*     */ public class UnivariatePeriodicInterpolator implements UnivariateInterpolator {
/*     */   public static final int DEFAULT_EXTEND = 5;
/*     */   
/*     */   private final UnivariateInterpolator interpolator;
/*     */   
/*     */   private final double period;
/*     */   
/*     */   private final int extend;
/*     */   
/*     */   public UnivariatePeriodicInterpolator(UnivariateInterpolator interpolator, double period, int extend) {
/*  59 */     this.interpolator = interpolator;
/*  60 */     this.period = period;
/*  61 */     this.extend = extend;
/*     */   }
/*     */   
/*     */   public UnivariatePeriodicInterpolator(UnivariateInterpolator interpolator, double period) {
/*  74 */     this(interpolator, period, 5);
/*     */   }
/*     */   
/*     */   public UnivariateFunction interpolate(double[] xval, double[] yval) {
/*  85 */     if (xval.length < this.extend)
/*  86 */       throw new NumberIsTooSmallException(Integer.valueOf(xval.length), Integer.valueOf(this.extend), true); 
/*  89 */     MathArrays.checkOrder(xval);
/*  90 */     final double offset = xval[0];
/*  92 */     int len = xval.length + this.extend * 2;
/*  93 */     double[] x = new double[len];
/*  94 */     double[] y = new double[len];
/*     */     int i;
/*  95 */     for (i = 0; i < xval.length; i++) {
/*  96 */       int index = i + this.extend;
/*  97 */       x[index] = MathUtils.reduce(xval[i], this.period, offset);
/*  98 */       y[index] = yval[i];
/*     */     } 
/* 102 */     for (i = 0; i < this.extend; i++) {
/* 103 */       int index = xval.length - this.extend + i;
/* 104 */       x[i] = MathUtils.reduce(xval[index], this.period, offset) - this.period;
/* 105 */       y[i] = yval[index];
/* 107 */       index = len - this.extend + i;
/* 108 */       x[index] = MathUtils.reduce(xval[i], this.period, offset) + this.period;
/* 109 */       y[index] = yval[i];
/*     */     } 
/* 112 */     MathArrays.sortInPlace(x, new double[][] { y });
/* 114 */     final UnivariateFunction f = this.interpolator.interpolate(x, y);
/* 115 */     return new UnivariateFunction() {
/*     */         public double value(double x) {
/* 117 */           return f.value(MathUtils.reduce(x, UnivariatePeriodicInterpolator.this.period, offset));
/*     */         }
/*     */       };
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\analysis\interpolation\UnivariatePeriodicInterpolator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */