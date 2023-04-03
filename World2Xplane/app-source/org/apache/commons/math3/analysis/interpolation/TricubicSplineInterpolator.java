/*     */ package org.apache.commons.math3.analysis.interpolation;
/*     */ 
/*     */ import org.apache.commons.math3.analysis.TrivariateFunction;
/*     */ import org.apache.commons.math3.exception.DimensionMismatchException;
/*     */ import org.apache.commons.math3.exception.NoDataException;
/*     */ import org.apache.commons.math3.util.MathArrays;
/*     */ 
/*     */ public class TricubicSplineInterpolator implements TrivariateGridInterpolator {
/*     */   public TricubicSplineInterpolatingFunction interpolate(double[] xval, double[] yval, double[] zval, double[][][] fval) {
/*  38 */     if (xval.length == 0 || yval.length == 0 || zval.length == 0 || fval.length == 0)
/*  39 */       throw new NoDataException(); 
/*  41 */     if (xval.length != fval.length)
/*  42 */       throw new DimensionMismatchException(xval.length, fval.length); 
/*  45 */     MathArrays.checkOrder(xval);
/*  46 */     MathArrays.checkOrder(yval);
/*  47 */     MathArrays.checkOrder(zval);
/*  49 */     int xLen = xval.length;
/*  50 */     int yLen = yval.length;
/*  51 */     int zLen = zval.length;
/*  56 */     double[][][] fvalXY = new double[zLen][xLen][yLen];
/*  57 */     double[][][] fvalZX = new double[yLen][zLen][xLen];
/*  58 */     for (int i = 0; i < xLen; i++) {
/*  59 */       if ((fval[i]).length != yLen)
/*  60 */         throw new DimensionMismatchException((fval[i]).length, yLen); 
/*  63 */       for (int i4 = 0; i4 < yLen; i4++) {
/*  64 */         if ((fval[i][i4]).length != zLen)
/*  65 */           throw new DimensionMismatchException((fval[i][i4]).length, zLen); 
/*  68 */         for (int i5 = 0; i5 < zLen; i5++) {
/*  69 */           double v = fval[i][i4][i5];
/*  70 */           fvalXY[i5][i][i4] = v;
/*  71 */           fvalZX[i4][i5][i] = v;
/*     */         } 
/*     */       } 
/*     */     } 
/*  76 */     BicubicSplineInterpolator bsi = new BicubicSplineInterpolator();
/*  79 */     BicubicSplineInterpolatingFunction[] xSplineYZ = new BicubicSplineInterpolatingFunction[xLen];
/*  81 */     for (int m = 0; m < xLen; m++)
/*  82 */       xSplineYZ[m] = bsi.interpolate(yval, zval, fval[m]); 
/*  86 */     BicubicSplineInterpolatingFunction[] ySplineZX = new BicubicSplineInterpolatingFunction[yLen];
/*  88 */     for (int j = 0; j < yLen; j++)
/*  89 */       ySplineZX[j] = bsi.interpolate(zval, xval, fvalZX[j]); 
/*  93 */     BicubicSplineInterpolatingFunction[] zSplineXY = new BicubicSplineInterpolatingFunction[zLen];
/*  95 */     for (int k = 0; k < zLen; k++)
/*  96 */       zSplineXY[k] = bsi.interpolate(xval, yval, fvalXY[k]); 
/* 100 */     double[][][] dFdX = new double[xLen][yLen][zLen];
/* 101 */     double[][][] dFdY = new double[xLen][yLen][zLen];
/* 102 */     double[][][] d2FdXdY = new double[xLen][yLen][zLen];
/* 103 */     for (int n = 0; n < zLen; n++) {
/* 104 */       BicubicSplineInterpolatingFunction f = zSplineXY[n];
/* 105 */       for (int i4 = 0; i4 < xLen; i4++) {
/* 106 */         double x = xval[i4];
/* 107 */         for (int i5 = 0; i5 < yLen; i5++) {
/* 108 */           double y = yval[i5];
/* 109 */           dFdX[i4][i5][n] = f.partialDerivativeX(x, y);
/* 110 */           dFdY[i4][i5][n] = f.partialDerivativeY(x, y);
/* 111 */           d2FdXdY[i4][i5][n] = f.partialDerivativeXY(x, y);
/*     */         } 
/*     */       } 
/*     */     } 
/* 117 */     double[][][] dFdZ = new double[xLen][yLen][zLen];
/* 118 */     double[][][] d2FdYdZ = new double[xLen][yLen][zLen];
/* 119 */     for (int i1 = 0; i1 < xLen; i1++) {
/* 120 */       BicubicSplineInterpolatingFunction f = xSplineYZ[i1];
/* 121 */       for (int i4 = 0; i4 < yLen; i4++) {
/* 122 */         double y = yval[i4];
/* 123 */         for (int i5 = 0; i5 < zLen; i5++) {
/* 124 */           double z = zval[i5];
/* 125 */           dFdZ[i1][i4][i5] = f.partialDerivativeY(y, z);
/* 126 */           d2FdYdZ[i1][i4][i5] = f.partialDerivativeXY(y, z);
/*     */         } 
/*     */       } 
/*     */     } 
/* 132 */     double[][][] d2FdZdX = new double[xLen][yLen][zLen];
/* 133 */     for (int i2 = 0; i2 < yLen; i2++) {
/* 134 */       BicubicSplineInterpolatingFunction f = ySplineZX[i2];
/* 135 */       for (int i4 = 0; i4 < zLen; i4++) {
/* 136 */         double z = zval[i4];
/* 137 */         for (int i5 = 0; i5 < xLen; i5++) {
/* 138 */           double x = xval[i5];
/* 139 */           d2FdZdX[i5][i2][i4] = f.partialDerivativeXY(z, x);
/*     */         } 
/*     */       } 
/*     */     } 
/* 145 */     double[][][] d3FdXdYdZ = new double[xLen][yLen][zLen];
/* 146 */     for (int i3 = 0; i3 < xLen; i3++) {
/* 147 */       int nI = nextIndex(i3, xLen);
/* 148 */       int pI = previousIndex(i3);
/* 149 */       for (int i4 = 0; i4 < yLen; i4++) {
/* 150 */         int nJ = nextIndex(i4, yLen);
/* 151 */         int pJ = previousIndex(i4);
/* 152 */         for (int i5 = 0; i5 < zLen; i5++) {
/* 153 */           int nK = nextIndex(i5, zLen);
/* 154 */           int pK = previousIndex(i5);
/* 157 */           d3FdXdYdZ[i3][i4][i5] = (fval[nI][nJ][nK] - fval[nI][pJ][nK] - fval[pI][nJ][nK] + fval[pI][pJ][nK] - fval[nI][nJ][pK] + fval[nI][pJ][pK] + fval[pI][nJ][pK] - fval[pI][pJ][pK]) / (xval[nI] - xval[pI]) * (yval[nJ] - yval[pJ]) * (zval[nK] - zval[pK]);
/*     */         } 
/*     */       } 
/*     */     } 
/* 167 */     return new TricubicSplineInterpolatingFunction(xval, yval, zval, fval, dFdX, dFdY, dFdZ, d2FdXdY, d2FdZdX, d2FdYdZ, d3FdXdYdZ);
/*     */   }
/*     */   
/*     */   private int nextIndex(int i, int max) {
/* 182 */     int index = i + 1;
/* 183 */     return (index < max) ? index : (index - 1);
/*     */   }
/*     */   
/*     */   private int previousIndex(int i) {
/* 193 */     int index = i - 1;
/* 194 */     return (index >= 0) ? index : 0;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\analysis\interpolation\TricubicSplineInterpolator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */