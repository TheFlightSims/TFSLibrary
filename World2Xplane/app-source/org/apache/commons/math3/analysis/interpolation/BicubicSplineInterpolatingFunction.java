/*     */ package org.apache.commons.math3.analysis.interpolation;
/*     */ 
/*     */ import org.apache.commons.math3.analysis.BivariateFunction;
/*     */ import org.apache.commons.math3.exception.DimensionMismatchException;
/*     */ import org.apache.commons.math3.exception.NoDataException;
/*     */ import org.apache.commons.math3.exception.OutOfRangeException;
/*     */ import org.apache.commons.math3.util.MathArrays;
/*     */ 
/*     */ public class BicubicSplineInterpolatingFunction implements BivariateFunction {
/*  39 */   private static final double[][] AINV = new double[][] { 
/*     */       { 
/*  39 */         1.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 
/*  39 */         0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D }, { 
/*  39 */         0.0D, 0.0D, 0.0D, 0.0D, 1.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 
/*  39 */         0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D }, { 
/*  39 */         -3.0D, 3.0D, 0.0D, 0.0D, -2.0D, -1.0D, 0.0D, 0.0D, 0.0D, 0.0D, 
/*  39 */         0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D }, { 
/*  39 */         2.0D, -2.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.0D, 0.0D, 0.0D, 0.0D, 
/*  39 */         0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D }, { 
/*  39 */         0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 1.0D, 0.0D, 
/*  39 */         0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D }, { 
/*  39 */         0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 
/*  39 */         0.0D, 0.0D, 1.0D, 0.0D, 0.0D, 0.0D }, { 
/*  39 */         0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, -3.0D, 3.0D, 
/*  39 */         0.0D, 0.0D, -2.0D, -1.0D, 0.0D, 0.0D }, { 
/*  39 */         0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 2.0D, -2.0D, 
/*  39 */         0.0D, 0.0D, 1.0D, 1.0D, 0.0D, 0.0D }, { 
/*  39 */         -3.0D, 0.0D, 3.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, -2.0D, 0.0D, 
/*  39 */         -1.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D }, { 
/*  39 */         0.0D, 0.0D, 0.0D, 0.0D, -3.0D, 0.0D, 3.0D, 0.0D, 0.0D, 0.0D, 
/*  39 */         0.0D, 0.0D, -2.0D, 0.0D, -1.0D, 0.0D }, 
/*     */       { 
/*  39 */         9.0D, -9.0D, -9.0D, 9.0D, 6.0D, 3.0D, -6.0D, -3.0D, 6.0D, -6.0D, 
/*  39 */         3.0D, -3.0D, 4.0D, 2.0D, 2.0D, 1.0D }, { 
/*  39 */         -6.0D, 6.0D, 6.0D, -6.0D, -3.0D, -3.0D, 3.0D, 3.0D, -4.0D, 4.0D, 
/*  39 */         -2.0D, 2.0D, -2.0D, -2.0D, -1.0D, -1.0D }, { 
/*  39 */         2.0D, 0.0D, -2.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 1.0D, 0.0D, 
/*  39 */         1.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D }, { 
/*  39 */         0.0D, 0.0D, 0.0D, 0.0D, 2.0D, 0.0D, -2.0D, 0.0D, 0.0D, 0.0D, 
/*  39 */         0.0D, 0.0D, 1.0D, 0.0D, 1.0D, 0.0D }, { 
/*  39 */         -6.0D, 6.0D, 6.0D, -6.0D, -4.0D, -2.0D, 4.0D, 2.0D, -3.0D, 3.0D, 
/*  39 */         -3.0D, 3.0D, -2.0D, -1.0D, -2.0D, -1.0D }, { 
/*  39 */         4.0D, -4.0D, -4.0D, 4.0D, 2.0D, 2.0D, -2.0D, -2.0D, 2.0D, -2.0D, 
/*  39 */         2.0D, -2.0D, 1.0D, 1.0D, 1.0D, 1.0D } };
/*     */   
/*     */   private final double[] xval;
/*     */   
/*     */   private final double[] yval;
/*     */   
/*     */   private final BicubicSplineFunction[][] splines;
/*     */   
/*  73 */   private BivariateFunction[][][] partialDerivatives = (BivariateFunction[][][])null;
/*     */   
/*     */   public BicubicSplineInterpolatingFunction(double[] x, double[] y, double[][] f, double[][] dFdX, double[][] dFdY, double[][] d2FdXdY) throws DimensionMismatchException {
/*  98 */     int xLen = x.length;
/*  99 */     int yLen = y.length;
/* 101 */     if (xLen == 0 || yLen == 0 || f.length == 0 || (f[0]).length == 0)
/* 102 */       throw new NoDataException(); 
/* 104 */     if (xLen != f.length)
/* 105 */       throw new DimensionMismatchException(xLen, f.length); 
/* 107 */     if (xLen != dFdX.length)
/* 108 */       throw new DimensionMismatchException(xLen, dFdX.length); 
/* 110 */     if (xLen != dFdY.length)
/* 111 */       throw new DimensionMismatchException(xLen, dFdY.length); 
/* 113 */     if (xLen != d2FdXdY.length)
/* 114 */       throw new DimensionMismatchException(xLen, d2FdXdY.length); 
/* 117 */     MathArrays.checkOrder(x);
/* 118 */     MathArrays.checkOrder(y);
/* 120 */     this.xval = (double[])x.clone();
/* 121 */     this.yval = (double[])y.clone();
/* 123 */     int lastI = xLen - 1;
/* 124 */     int lastJ = yLen - 1;
/* 125 */     this.splines = new BicubicSplineFunction[lastI][lastJ];
/* 127 */     for (int i = 0; i < lastI; i++) {
/* 128 */       if ((f[i]).length != yLen)
/* 129 */         throw new DimensionMismatchException((f[i]).length, yLen); 
/* 131 */       if ((dFdX[i]).length != yLen)
/* 132 */         throw new DimensionMismatchException((dFdX[i]).length, yLen); 
/* 134 */       if ((dFdY[i]).length != yLen)
/* 135 */         throw new DimensionMismatchException((dFdY[i]).length, yLen); 
/* 137 */       if ((d2FdXdY[i]).length != yLen)
/* 138 */         throw new DimensionMismatchException((d2FdXdY[i]).length, yLen); 
/* 140 */       int ip1 = i + 1;
/* 141 */       for (int j = 0; j < lastJ; j++) {
/* 142 */         int jp1 = j + 1;
/* 143 */         double[] beta = { 
/* 143 */             f[i][j], f[ip1][j], f[i][jp1], f[ip1][jp1], dFdX[i][j], dFdX[ip1][j], dFdX[i][jp1], dFdX[ip1][jp1], dFdY[i][j], dFdY[ip1][j], 
/* 143 */             dFdY[i][jp1], dFdY[ip1][jp1], d2FdXdY[i][j], d2FdXdY[ip1][j], d2FdXdY[i][jp1], d2FdXdY[ip1][jp1] };
/* 150 */         this.splines[i][j] = new BicubicSplineFunction(computeSplineCoefficients(beta));
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public double value(double x, double y) {
/* 159 */     int i = searchIndex(x, this.xval);
/* 160 */     if (i == -1)
/* 161 */       throw new OutOfRangeException(Double.valueOf(x), Double.valueOf(this.xval[0]), Double.valueOf(this.xval[this.xval.length - 1])); 
/* 163 */     int j = searchIndex(y, this.yval);
/* 164 */     if (j == -1)
/* 165 */       throw new OutOfRangeException(Double.valueOf(y), Double.valueOf(this.yval[0]), Double.valueOf(this.yval[this.yval.length - 1])); 
/* 168 */     double xN = (x - this.xval[i]) / (this.xval[i + 1] - this.xval[i]);
/* 169 */     double yN = (y - this.yval[j]) / (this.yval[j + 1] - this.yval[j]);
/* 171 */     return this.splines[i][j].value(xN, yN);
/*     */   }
/*     */   
/*     */   public double partialDerivativeX(double x, double y) {
/* 181 */     return partialDerivative(0, x, y);
/*     */   }
/*     */   
/*     */   public double partialDerivativeY(double x, double y) {
/* 190 */     return partialDerivative(1, x, y);
/*     */   }
/*     */   
/*     */   public double partialDerivativeXX(double x, double y) {
/* 199 */     return partialDerivative(2, x, y);
/*     */   }
/*     */   
/*     */   public double partialDerivativeYY(double x, double y) {
/* 208 */     return partialDerivative(3, x, y);
/*     */   }
/*     */   
/*     */   public double partialDerivativeXY(double x, double y) {
/* 216 */     return partialDerivative(4, x, y);
/*     */   }
/*     */   
/*     */   private double partialDerivative(int which, double x, double y) {
/* 226 */     if (this.partialDerivatives == null)
/* 227 */       computePartialDerivatives(); 
/* 230 */     int i = searchIndex(x, this.xval);
/* 231 */     if (i == -1)
/* 232 */       throw new OutOfRangeException(Double.valueOf(x), Double.valueOf(this.xval[0]), Double.valueOf(this.xval[this.xval.length - 1])); 
/* 234 */     int j = searchIndex(y, this.yval);
/* 235 */     if (j == -1)
/* 236 */       throw new OutOfRangeException(Double.valueOf(y), Double.valueOf(this.yval[0]), Double.valueOf(this.yval[this.yval.length - 1])); 
/* 239 */     double xN = (x - this.xval[i]) / (this.xval[i + 1] - this.xval[i]);
/* 240 */     double yN = (y - this.yval[j]) / (this.yval[j + 1] - this.yval[j]);
/* 242 */     return this.partialDerivatives[which][i][j].value(xN, yN);
/*     */   }
/*     */   
/*     */   private void computePartialDerivatives() {
/* 249 */     int lastI = this.xval.length - 1;
/* 250 */     int lastJ = this.yval.length - 1;
/* 251 */     this.partialDerivatives = new BivariateFunction[5][lastI][lastJ];
/* 253 */     for (int i = 0; i < lastI; i++) {
/* 254 */       for (int j = 0; j < lastJ; j++) {
/* 255 */         BicubicSplineFunction f = this.splines[i][j];
/* 256 */         this.partialDerivatives[0][i][j] = f.partialDerivativeX();
/* 257 */         this.partialDerivatives[1][i][j] = f.partialDerivativeY();
/* 258 */         this.partialDerivatives[2][i][j] = f.partialDerivativeXX();
/* 259 */         this.partialDerivatives[3][i][j] = f.partialDerivativeYY();
/* 260 */         this.partialDerivatives[4][i][j] = f.partialDerivativeXY();
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private int searchIndex(double c, double[] val) {
/* 273 */     if (c < val[0])
/* 274 */       return -1; 
/* 277 */     int max = val.length;
/* 278 */     for (int i = 1; i < max; i++) {
/* 279 */       if (c <= val[i])
/* 280 */         return i - 1; 
/*     */     } 
/* 284 */     return -1;
/*     */   }
/*     */   
/*     */   private double[] computeSplineCoefficients(double[] beta) {
/* 317 */     double[] a = new double[16];
/* 319 */     for (int i = 0; i < 16; i++) {
/* 320 */       double result = 0.0D;
/* 321 */       double[] row = AINV[i];
/* 322 */       for (int j = 0; j < 16; j++)
/* 323 */         result += row[j] * beta[j]; 
/* 325 */       a[i] = result;
/*     */     } 
/* 328 */     return a;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\analysis\interpolation\BicubicSplineInterpolatingFunction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */