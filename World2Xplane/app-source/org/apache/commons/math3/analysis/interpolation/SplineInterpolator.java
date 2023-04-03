/*     */ package org.apache.commons.math3.analysis.interpolation;
/*     */ 
/*     */ import org.apache.commons.math3.analysis.UnivariateFunction;
/*     */ import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;
/*     */ import org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction;
/*     */ import org.apache.commons.math3.exception.DimensionMismatchException;
/*     */ import org.apache.commons.math3.exception.NumberIsTooSmallException;
/*     */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*     */ import org.apache.commons.math3.util.MathArrays;
/*     */ 
/*     */ public class SplineInterpolator implements UnivariateInterpolator {
/*     */   public PolynomialSplineFunction interpolate(double[] x, double[] y) {
/*  68 */     if (x.length != y.length)
/*  69 */       throw new DimensionMismatchException(x.length, y.length); 
/*  72 */     if (x.length < 3)
/*  73 */       throw new NumberIsTooSmallException(LocalizedFormats.NUMBER_OF_POINTS, Integer.valueOf(x.length), Integer.valueOf(3), true); 
/*  78 */     int n = x.length - 1;
/*  80 */     MathArrays.checkOrder(x);
/*  83 */     double[] h = new double[n];
/*  84 */     for (int i = 0; i < n; i++)
/*  85 */       h[i] = x[i + 1] - x[i]; 
/*  88 */     double[] mu = new double[n];
/*  89 */     double[] z = new double[n + 1];
/*  90 */     mu[0] = 0.0D;
/*  91 */     z[0] = 0.0D;
/*  92 */     double g = 0.0D;
/*  93 */     for (int k = 1; k < n; k++) {
/*  94 */       g = 2.0D * (x[k + 1] - x[k - 1]) - h[k - 1] * mu[k - 1];
/*  95 */       mu[k] = h[k] / g;
/*  96 */       z[k] = (3.0D * (y[k + 1] * h[k - 1] - y[k] * (x[k + 1] - x[k - 1]) + y[k - 1] * h[k]) / h[k - 1] * h[k] - h[k - 1] * z[k - 1]) / g;
/*     */     } 
/* 101 */     double[] b = new double[n];
/* 102 */     double[] c = new double[n + 1];
/* 103 */     double[] d = new double[n];
/* 105 */     z[n] = 0.0D;
/* 106 */     c[n] = 0.0D;
/* 108 */     for (int j = n - 1; j >= 0; j--) {
/* 109 */       c[j] = z[j] - mu[j] * c[j + 1];
/* 110 */       b[j] = (y[j + 1] - y[j]) / h[j] - h[j] * (c[j + 1] + 2.0D * c[j]) / 3.0D;
/* 111 */       d[j] = (c[j + 1] - c[j]) / 3.0D * h[j];
/*     */     } 
/* 114 */     PolynomialFunction[] polynomials = new PolynomialFunction[n];
/* 115 */     double[] coefficients = new double[4];
/* 116 */     for (int m = 0; m < n; m++) {
/* 117 */       coefficients[0] = y[m];
/* 118 */       coefficients[1] = b[m];
/* 119 */       coefficients[2] = c[m];
/* 120 */       coefficients[3] = d[m];
/* 121 */       polynomials[m] = new PolynomialFunction(coefficients);
/*     */     } 
/* 124 */     return new PolynomialSplineFunction(x, polynomials);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\analysis\interpolation\SplineInterpolator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */