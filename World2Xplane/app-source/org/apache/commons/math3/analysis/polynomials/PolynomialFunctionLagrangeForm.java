/*     */ package org.apache.commons.math3.analysis.polynomials;
/*     */ 
/*     */ import org.apache.commons.math3.analysis.UnivariateFunction;
/*     */ import org.apache.commons.math3.exception.DimensionMismatchException;
/*     */ import org.apache.commons.math3.exception.NumberIsTooSmallException;
/*     */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ import org.apache.commons.math3.util.MathArrays;
/*     */ 
/*     */ public class PolynomialFunctionLagrangeForm implements UnivariateFunction {
/*     */   private double[] coefficients;
/*     */   
/*     */   private final double[] x;
/*     */   
/*     */   private final double[] y;
/*     */   
/*     */   private boolean coefficientsComputed;
/*     */   
/*     */   public PolynomialFunctionLagrangeForm(double[] x, double[] y) {
/*  72 */     this.x = new double[x.length];
/*  73 */     this.y = new double[y.length];
/*  74 */     System.arraycopy(x, 0, this.x, 0, x.length);
/*  75 */     System.arraycopy(y, 0, this.y, 0, y.length);
/*  76 */     this.coefficientsComputed = false;
/*  78 */     if (!verifyInterpolationArray(x, y, false)) {
/*  79 */       MathArrays.sortInPlace(this.x, new double[][] { this.y });
/*  81 */       verifyInterpolationArray(this.x, this.y, true);
/*     */     } 
/*     */   }
/*     */   
/*     */   public double value(double z) {
/*  98 */     return evaluateInternal(this.x, this.y, z);
/*     */   }
/*     */   
/*     */   public int degree() {
/* 107 */     return this.x.length - 1;
/*     */   }
/*     */   
/*     */   public double[] getInterpolatingPoints() {
/* 118 */     double[] out = new double[this.x.length];
/* 119 */     System.arraycopy(this.x, 0, out, 0, this.x.length);
/* 120 */     return out;
/*     */   }
/*     */   
/*     */   public double[] getInterpolatingValues() {
/* 131 */     double[] out = new double[this.y.length];
/* 132 */     System.arraycopy(this.y, 0, out, 0, this.y.length);
/* 133 */     return out;
/*     */   }
/*     */   
/*     */   public double[] getCoefficients() {
/* 147 */     if (!this.coefficientsComputed)
/* 148 */       computeCoefficients(); 
/* 150 */     double[] out = new double[this.coefficients.length];
/* 151 */     System.arraycopy(this.coefficients, 0, out, 0, this.coefficients.length);
/* 152 */     return out;
/*     */   }
/*     */   
/*     */   public static double evaluate(double[] x, double[] y, double z) {
/* 172 */     if (verifyInterpolationArray(x, y, false))
/* 173 */       return evaluateInternal(x, y, z); 
/* 177 */     double[] xNew = new double[x.length];
/* 178 */     double[] yNew = new double[y.length];
/* 179 */     System.arraycopy(x, 0, xNew, 0, x.length);
/* 180 */     System.arraycopy(y, 0, yNew, 0, y.length);
/* 182 */     MathArrays.sortInPlace(xNew, new double[][] { yNew });
/* 184 */     verifyInterpolationArray(xNew, yNew, true);
/* 185 */     return evaluateInternal(xNew, yNew, z);
/*     */   }
/*     */   
/*     */   private static double evaluateInternal(double[] x, double[] y, double z) {
/* 205 */     int nearest = 0;
/* 206 */     int n = x.length;
/* 207 */     double[] c = new double[n];
/* 208 */     double[] d = new double[n];
/* 209 */     double min_dist = Double.POSITIVE_INFINITY;
/* 210 */     for (int i = 0; i < n; i++) {
/* 212 */       c[i] = y[i];
/* 213 */       d[i] = y[i];
/* 215 */       double dist = FastMath.abs(z - x[i]);
/* 216 */       if (dist < min_dist) {
/* 217 */         nearest = i;
/* 218 */         min_dist = dist;
/*     */       } 
/*     */     } 
/* 223 */     double value = y[nearest];
/* 225 */     for (int j = 1; j < n; j++) {
/* 226 */       for (int k = 0; k < n - j; k++) {
/* 227 */         double tc = x[k] - z;
/* 228 */         double td = x[j + k] - z;
/* 229 */         double divider = x[k] - x[j + k];
/* 231 */         double w = (c[k + 1] - d[k]) / divider;
/* 232 */         c[k] = tc * w;
/* 233 */         d[k] = td * w;
/*     */       } 
/* 236 */       if (nearest < 0.5D * (n - j + 1)) {
/* 237 */         value += c[nearest];
/*     */       } else {
/* 239 */         nearest--;
/* 240 */         value += d[nearest];
/*     */       } 
/*     */     } 
/* 244 */     return value;
/*     */   }
/*     */   
/*     */   protected void computeCoefficients() {
/* 254 */     int n = degree() + 1;
/* 255 */     this.coefficients = new double[n];
/* 256 */     for (int i = 0; i < n; i++)
/* 257 */       this.coefficients[i] = 0.0D; 
/* 261 */     double[] c = new double[n + 1];
/* 262 */     c[0] = 1.0D;
/* 263 */     for (int j = 0; j < n; j++) {
/* 264 */       for (int m = j; m > 0; m--)
/* 265 */         c[m] = c[m - 1] - c[m] * this.x[j]; 
/* 267 */       c[0] = c[0] * -this.x[j];
/* 268 */       c[j + 1] = 1.0D;
/*     */     } 
/* 271 */     double[] tc = new double[n];
/* 272 */     for (int k = 0; k < n; k++) {
/* 274 */       double d = 1.0D;
/* 275 */       for (int m = 0; m < n; m++) {
/* 276 */         if (k != m)
/* 277 */           d *= this.x[k] - this.x[m]; 
/*     */       } 
/* 280 */       double t = this.y[k] / d;
/* 284 */       tc[n - 1] = c[n];
/* 285 */       this.coefficients[n - 1] = this.coefficients[n - 1] + t * tc[n - 1];
/* 286 */       for (int i1 = n - 2; i1 >= 0; i1--) {
/* 287 */         tc[i1] = c[i1 + 1] + tc[i1 + 1] * this.x[k];
/* 288 */         this.coefficients[i1] = this.coefficients[i1] + t * tc[i1];
/*     */       } 
/*     */     } 
/* 292 */     this.coefficientsComputed = true;
/*     */   }
/*     */   
/*     */   public static boolean verifyInterpolationArray(double[] x, double[] y, boolean abort) {
/* 314 */     if (x.length != y.length)
/* 315 */       throw new DimensionMismatchException(x.length, y.length); 
/* 317 */     if (x.length < 2)
/* 318 */       throw new NumberIsTooSmallException(LocalizedFormats.WRONG_NUMBER_OF_POINTS, Integer.valueOf(2), Integer.valueOf(x.length), true); 
/* 321 */     return MathArrays.checkOrder(x, MathArrays.OrderDirection.INCREASING, true, abort);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\analysis\polynomials\PolynomialFunctionLagrangeForm.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */