/*     */ package org.apache.commons.math3.analysis.interpolation;
/*     */ 
/*     */ import org.apache.commons.math3.analysis.BivariateFunction;
/*     */ import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;
/*     */ import org.apache.commons.math3.exception.DimensionMismatchException;
/*     */ import org.apache.commons.math3.exception.NoDataException;
/*     */ import org.apache.commons.math3.optimization.DifferentiableMultivariateVectorOptimizer;
/*     */ import org.apache.commons.math3.optimization.fitting.PolynomialFitter;
/*     */ import org.apache.commons.math3.optimization.general.GaussNewtonOptimizer;
/*     */ import org.apache.commons.math3.util.MathArrays;
/*     */ 
/*     */ public class SmoothingPolynomialBicubicSplineInterpolator extends BicubicSplineInterpolator {
/*     */   private final PolynomialFitter xFitter;
/*     */   
/*     */   private final PolynomialFitter yFitter;
/*     */   
/*     */   public SmoothingPolynomialBicubicSplineInterpolator() {
/*  45 */     this(3);
/*     */   }
/*     */   
/*     */   public SmoothingPolynomialBicubicSplineInterpolator(int degree) {
/*  52 */     this(degree, degree);
/*     */   }
/*     */   
/*     */   public SmoothingPolynomialBicubicSplineInterpolator(int xDegree, int yDegree) {
/*  63 */     this.xFitter = new PolynomialFitter(xDegree, (DifferentiableMultivariateVectorOptimizer)new GaussNewtonOptimizer(false));
/*  64 */     this.yFitter = new PolynomialFitter(yDegree, (DifferentiableMultivariateVectorOptimizer)new GaussNewtonOptimizer(false));
/*     */   }
/*     */   
/*     */   public BicubicSplineInterpolatingFunction interpolate(double[] xval, double[] yval, double[][] fval) {
/*  74 */     if (xval.length == 0 || yval.length == 0 || fval.length == 0)
/*  75 */       throw new NoDataException(); 
/*  77 */     if (xval.length != fval.length)
/*  78 */       throw new DimensionMismatchException(xval.length, fval.length); 
/*  81 */     int xLen = xval.length;
/*  82 */     int yLen = yval.length;
/*  84 */     for (int i = 0; i < xLen; i++) {
/*  85 */       if ((fval[i]).length != yLen)
/*  86 */         throw new DimensionMismatchException((fval[i]).length, yLen); 
/*     */     } 
/*  90 */     MathArrays.checkOrder(xval);
/*  91 */     MathArrays.checkOrder(yval);
/*  95 */     PolynomialFunction[] yPolyX = new PolynomialFunction[yLen];
/*  96 */     for (int j = 0; j < yLen; j++) {
/*  97 */       this.xFitter.clearObservations();
/*  98 */       for (int i1 = 0; i1 < xLen; i1++)
/*  99 */         this.xFitter.addObservedPoint(1.0D, xval[i1], fval[i1][j]); 
/* 102 */       yPolyX[j] = new PolynomialFunction(this.xFitter.fit());
/*     */     } 
/* 107 */     double[][] fval_1 = new double[xLen][yLen];
/* 108 */     for (int k = 0; k < yLen; k++) {
/* 109 */       PolynomialFunction f = yPolyX[k];
/* 110 */       for (int i1 = 0; i1 < xLen; i1++)
/* 111 */         fval_1[i1][k] = f.value(xval[i1]); 
/*     */     } 
/* 117 */     PolynomialFunction[] xPolyY = new PolynomialFunction[xLen];
/* 118 */     for (int m = 0; m < xLen; m++) {
/* 119 */       this.yFitter.clearObservations();
/* 120 */       for (int i1 = 0; i1 < yLen; i1++)
/* 121 */         this.yFitter.addObservedPoint(1.0D, yval[i1], fval_1[m][i1]); 
/* 124 */       xPolyY[m] = new PolynomialFunction(this.yFitter.fit());
/*     */     } 
/* 129 */     double[][] fval_2 = new double[xLen][yLen];
/* 130 */     for (int n = 0; n < xLen; n++) {
/* 131 */       PolynomialFunction f = xPolyY[n];
/* 132 */       for (int i1 = 0; i1 < yLen; i1++)
/* 133 */         fval_2[n][i1] = f.value(yval[i1]); 
/*     */     } 
/* 137 */     return super.interpolate(xval, yval, fval_2);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\analysis\interpolation\SmoothingPolynomialBicubicSplineInterpolator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */