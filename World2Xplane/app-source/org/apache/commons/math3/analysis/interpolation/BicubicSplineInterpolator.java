/*     */ package org.apache.commons.math3.analysis.interpolation;
/*     */ 
/*     */ import org.apache.commons.math3.analysis.BivariateFunction;
/*     */ import org.apache.commons.math3.analysis.UnivariateFunction;
/*     */ import org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction;
/*     */ import org.apache.commons.math3.exception.DimensionMismatchException;
/*     */ import org.apache.commons.math3.exception.NoDataException;
/*     */ import org.apache.commons.math3.util.MathArrays;
/*     */ 
/*     */ public class BicubicSplineInterpolator implements BivariateGridInterpolator {
/*     */   public BicubicSplineInterpolatingFunction interpolate(double[] xval, double[] yval, double[][] fval) {
/*  39 */     if (xval.length == 0 || yval.length == 0 || fval.length == 0)
/*  40 */       throw new NoDataException(); 
/*  42 */     if (xval.length != fval.length)
/*  43 */       throw new DimensionMismatchException(xval.length, fval.length); 
/*  46 */     MathArrays.checkOrder(xval);
/*  47 */     MathArrays.checkOrder(yval);
/*  49 */     int xLen = xval.length;
/*  50 */     int yLen = yval.length;
/*  56 */     double[][] fX = new double[yLen][xLen];
/*  57 */     for (int i = 0; i < xLen; i++) {
/*  58 */       if ((fval[i]).length != yLen)
/*  59 */         throw new DimensionMismatchException((fval[i]).length, yLen); 
/*  62 */       for (int i2 = 0; i2 < yLen; i2++)
/*  63 */         fX[i2][i] = fval[i][i2]; 
/*     */     } 
/*  67 */     SplineInterpolator spInterpolator = new SplineInterpolator();
/*  71 */     PolynomialSplineFunction[] ySplineX = new PolynomialSplineFunction[yLen];
/*  72 */     for (int j = 0; j < yLen; j++)
/*  73 */       ySplineX[j] = spInterpolator.interpolate(xval, fX[j]); 
/*  78 */     PolynomialSplineFunction[] xSplineY = new PolynomialSplineFunction[xLen];
/*  79 */     for (int k = 0; k < xLen; k++)
/*  80 */       xSplineY[k] = spInterpolator.interpolate(yval, fval[k]); 
/*  84 */     double[][] dFdX = new double[xLen][yLen];
/*  85 */     for (int m = 0; m < yLen; m++) {
/*  86 */       UnivariateFunction f = ySplineX[m].derivative();
/*  87 */       for (int i2 = 0; i2 < xLen; i2++)
/*  88 */         dFdX[i2][m] = f.value(xval[i2]); 
/*     */     } 
/*  93 */     double[][] dFdY = new double[xLen][yLen];
/*  94 */     for (int n = 0; n < xLen; n++) {
/*  95 */       UnivariateFunction f = xSplineY[n].derivative();
/*  96 */       for (int i2 = 0; i2 < yLen; i2++)
/*  97 */         dFdY[n][i2] = f.value(yval[i2]); 
/*     */     } 
/* 102 */     double[][] d2FdXdY = new double[xLen][yLen];
/* 103 */     for (int i1 = 0; i1 < xLen; i1++) {
/* 104 */       int nI = nextIndex(i1, xLen);
/* 105 */       int pI = previousIndex(i1);
/* 106 */       for (int i2 = 0; i2 < yLen; i2++) {
/* 107 */         int nJ = nextIndex(i2, yLen);
/* 108 */         int pJ = previousIndex(i2);
/* 109 */         d2FdXdY[i1][i2] = (fval[nI][nJ] - fval[nI][pJ] - fval[pI][nJ] + fval[pI][pJ]) / (xval[nI] - xval[pI]) * (yval[nJ] - yval[pJ]);
/*     */       } 
/*     */     } 
/* 116 */     return new BicubicSplineInterpolatingFunction(xval, yval, fval, dFdX, dFdY, d2FdXdY);
/*     */   }
/*     */   
/*     */   private int nextIndex(int i, int max) {
/* 129 */     int index = i + 1;
/* 130 */     return (index < max) ? index : (index - 1);
/*     */   }
/*     */   
/*     */   private int previousIndex(int i) {
/* 140 */     int index = i - 1;
/* 141 */     return (index >= 0) ? index : 0;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\analysis\interpolation\BicubicSplineInterpolator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */