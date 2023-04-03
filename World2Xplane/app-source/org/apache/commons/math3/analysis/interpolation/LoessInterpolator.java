/*     */ package org.apache.commons.math3.analysis.interpolation;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Arrays;
/*     */ import org.apache.commons.math3.analysis.UnivariateFunction;
/*     */ import org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction;
/*     */ import org.apache.commons.math3.exception.DimensionMismatchException;
/*     */ import org.apache.commons.math3.exception.NoDataException;
/*     */ import org.apache.commons.math3.exception.NotPositiveException;
/*     */ import org.apache.commons.math3.exception.NumberIsTooSmallException;
/*     */ import org.apache.commons.math3.exception.OutOfRangeException;
/*     */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ import org.apache.commons.math3.util.MathArrays;
/*     */ import org.apache.commons.math3.util.MathUtils;
/*     */ 
/*     */ public class LoessInterpolator implements UnivariateInterpolator, Serializable {
/*     */   public static final double DEFAULT_BANDWIDTH = 0.3D;
/*     */   
/*     */   public static final int DEFAULT_ROBUSTNESS_ITERS = 2;
/*     */   
/*     */   public static final double DEFAULT_ACCURACY = 1.0E-12D;
/*     */   
/*     */   private static final long serialVersionUID = 5204927143605193821L;
/*     */   
/*     */   private final double bandwidth;
/*     */   
/*     */   private final int robustnessIters;
/*     */   
/*     */   private final double accuracy;
/*     */   
/*     */   public LoessInterpolator() {
/*  94 */     this.bandwidth = 0.3D;
/*  95 */     this.robustnessIters = 2;
/*  96 */     this.accuracy = 1.0E-12D;
/*     */   }
/*     */   
/*     */   public LoessInterpolator(double bandwidth, int robustnessIters) {
/* 122 */     this(bandwidth, robustnessIters, 1.0E-12D);
/*     */   }
/*     */   
/*     */   public LoessInterpolator(double bandwidth, int robustnessIters, double accuracy) {
/* 147 */     if (bandwidth < 0.0D || bandwidth > 1.0D)
/* 149 */       throw new OutOfRangeException(LocalizedFormats.BANDWIDTH, Double.valueOf(bandwidth), Integer.valueOf(0), Integer.valueOf(1)); 
/* 151 */     this.bandwidth = bandwidth;
/* 152 */     if (robustnessIters < 0)
/* 153 */       throw new NotPositiveException(LocalizedFormats.ROBUSTNESS_ITERATIONS, Integer.valueOf(robustnessIters)); 
/* 155 */     this.robustnessIters = robustnessIters;
/* 156 */     this.accuracy = accuracy;
/*     */   }
/*     */   
/*     */   public final PolynomialSplineFunction interpolate(double[] xval, double[] yval) {
/* 181 */     return (new SplineInterpolator()).interpolate(xval, smooth(xval, yval));
/*     */   }
/*     */   
/*     */   public final double[] smooth(double[] xval, double[] yval, double[] weights) {
/* 206 */     if (xval.length != yval.length)
/* 207 */       throw new DimensionMismatchException(xval.length, yval.length); 
/* 210 */     int n = xval.length;
/* 212 */     if (n == 0)
/* 213 */       throw new NoDataException(); 
/* 216 */     checkAllFiniteReal(xval);
/* 217 */     checkAllFiniteReal(yval);
/* 218 */     checkAllFiniteReal(weights);
/* 220 */     MathArrays.checkOrder(xval);
/* 222 */     if (n == 1)
/* 223 */       return new double[] { yval[0] }; 
/* 226 */     if (n == 2)
/* 227 */       return new double[] { yval[0], yval[1] }; 
/* 230 */     int bandwidthInPoints = (int)(this.bandwidth * n);
/* 232 */     if (bandwidthInPoints < 2)
/* 233 */       throw new NumberIsTooSmallException(LocalizedFormats.BANDWIDTH, Integer.valueOf(bandwidthInPoints), Integer.valueOf(2), true); 
/* 237 */     double[] res = new double[n];
/* 239 */     double[] residuals = new double[n];
/* 240 */     double[] sortedResiduals = new double[n];
/* 242 */     double[] robustnessWeights = new double[n];
/* 247 */     Arrays.fill(robustnessWeights, 1.0D);
/* 249 */     for (int iter = 0; iter <= this.robustnessIters; iter++) {
/* 250 */       int[] bandwidthInterval = { 0, bandwidthInPoints - 1 };
/* 252 */       for (int i = 0; i < n; i++) {
/*     */         int edge;
/* 253 */         double beta, x = xval[i];
/* 257 */         if (i > 0)
/* 258 */           updateBandwidthInterval(xval, weights, i, bandwidthInterval); 
/* 261 */         int ileft = bandwidthInterval[0];
/* 262 */         int iright = bandwidthInterval[1];
/* 267 */         if (xval[i] - xval[ileft] > xval[iright] - xval[i]) {
/* 268 */           edge = ileft;
/*     */         } else {
/* 270 */           edge = iright;
/*     */         } 
/* 280 */         double sumWeights = 0.0D;
/* 281 */         double sumX = 0.0D;
/* 282 */         double sumXSquared = 0.0D;
/* 283 */         double sumY = 0.0D;
/* 284 */         double sumXY = 0.0D;
/* 285 */         double denom = FastMath.abs(1.0D / (xval[edge] - x));
/* 286 */         for (int k = ileft; k <= iright; k++) {
/* 287 */           double xk = xval[k];
/* 288 */           double yk = yval[k];
/* 289 */           double dist = (k < i) ? (x - xk) : (xk - x);
/* 290 */           double w = tricube(dist * denom) * robustnessWeights[k] * weights[k];
/* 291 */           double xkw = xk * w;
/* 292 */           sumWeights += w;
/* 293 */           sumX += xkw;
/* 294 */           sumXSquared += xk * xkw;
/* 295 */           sumY += yk * w;
/* 296 */           sumXY += yk * xkw;
/*     */         } 
/* 299 */         double meanX = sumX / sumWeights;
/* 300 */         double meanY = sumY / sumWeights;
/* 301 */         double meanXY = sumXY / sumWeights;
/* 302 */         double meanXSquared = sumXSquared / sumWeights;
/* 305 */         if (FastMath.sqrt(FastMath.abs(meanXSquared - meanX * meanX)) < this.accuracy) {
/* 306 */           beta = 0.0D;
/*     */         } else {
/* 308 */           beta = (meanXY - meanX * meanY) / (meanXSquared - meanX * meanX);
/*     */         } 
/* 311 */         double alpha = meanY - beta * meanX;
/* 313 */         res[i] = beta * x + alpha;
/* 314 */         residuals[i] = FastMath.abs(yval[i] - res[i]);
/*     */       } 
/* 319 */       if (iter == this.robustnessIters)
/*     */         break; 
/* 328 */       System.arraycopy(residuals, 0, sortedResiduals, 0, n);
/* 329 */       Arrays.sort(sortedResiduals);
/* 330 */       double medianResidual = sortedResiduals[n / 2];
/* 332 */       if (FastMath.abs(medianResidual) < this.accuracy)
/*     */         break; 
/* 336 */       for (int j = 0; j < n; j++) {
/* 337 */         double arg = residuals[j] / 6.0D * medianResidual;
/* 338 */         if (arg >= 1.0D) {
/* 339 */           robustnessWeights[j] = 0.0D;
/*     */         } else {
/* 341 */           double w = 1.0D - arg * arg;
/* 342 */           robustnessWeights[j] = w * w;
/*     */         } 
/*     */       } 
/*     */     } 
/* 347 */     return res;
/*     */   }
/*     */   
/*     */   public final double[] smooth(double[] xval, double[] yval) {
/* 368 */     if (xval.length != yval.length)
/* 369 */       throw new DimensionMismatchException(xval.length, yval.length); 
/* 372 */     double[] unitWeights = new double[xval.length];
/* 373 */     Arrays.fill(unitWeights, 1.0D);
/* 375 */     return smooth(xval, yval, unitWeights);
/*     */   }
/*     */   
/*     */   private static void updateBandwidthInterval(double[] xval, double[] weights, int i, int[] bandwidthInterval) {
/* 396 */     int left = bandwidthInterval[0];
/* 397 */     int right = bandwidthInterval[1];
/* 401 */     int nextRight = nextNonzero(weights, right);
/* 402 */     if (nextRight < xval.length && xval[nextRight] - xval[i] < xval[i] - xval[left]) {
/* 403 */       int nextLeft = nextNonzero(weights, bandwidthInterval[0]);
/* 404 */       bandwidthInterval[0] = nextLeft;
/* 405 */       bandwidthInterval[1] = nextRight;
/*     */     } 
/*     */   }
/*     */   
/*     */   private static int nextNonzero(double[] weights, int i) {
/* 418 */     int j = i + 1;
/* 419 */     while (j < weights.length && weights[j] == 0.0D)
/* 420 */       j++; 
/* 422 */     return j;
/*     */   }
/*     */   
/*     */   private static double tricube(double x) {
/* 434 */     double absX = FastMath.abs(x);
/* 435 */     if (absX >= 1.0D)
/* 436 */       return 0.0D; 
/* 438 */     double tmp = 1.0D - absX * absX * absX;
/* 439 */     return tmp * tmp * tmp;
/*     */   }
/*     */   
/*     */   private static void checkAllFiniteReal(double[] values) {
/* 450 */     for (int i = 0; i < values.length; i++)
/* 451 */       MathUtils.checkFinite(values[i]); 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\analysis\interpolation\LoessInterpolator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */