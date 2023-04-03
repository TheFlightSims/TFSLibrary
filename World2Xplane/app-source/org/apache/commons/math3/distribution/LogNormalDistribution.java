/*     */ package org.apache.commons.math3.distribution;
/*     */ 
/*     */ import org.apache.commons.math3.exception.NotStrictlyPositiveException;
/*     */ import org.apache.commons.math3.exception.NumberIsTooLargeException;
/*     */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*     */ import org.apache.commons.math3.special.Erf;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ 
/*     */ public class LogNormalDistribution extends AbstractRealDistribution {
/*     */   public static final double DEFAULT_INVERSE_ABSOLUTE_ACCURACY = 1.0E-9D;
/*     */   
/*     */   private static final long serialVersionUID = 20120112L;
/*     */   
/*  62 */   private static final double SQRT2PI = FastMath.sqrt(6.283185307179586D);
/*     */   
/*  65 */   private static final double SQRT2 = FastMath.sqrt(2.0D);
/*     */   
/*     */   private final double scale;
/*     */   
/*     */   private final double shape;
/*     */   
/*     */   private final double solverAbsoluteAccuracy;
/*     */   
/*     */   public LogNormalDistribution(double scale, double shape) throws NotStrictlyPositiveException {
/*  85 */     this(scale, shape, 1.0E-9D);
/*     */   }
/*     */   
/*     */   public LogNormalDistribution(double scale, double shape, double inverseCumAccuracy) throws NotStrictlyPositiveException {
/*  99 */     if (shape <= 0.0D)
/* 100 */       throw new NotStrictlyPositiveException(LocalizedFormats.SHAPE, Double.valueOf(shape)); 
/* 103 */     this.scale = scale;
/* 104 */     this.shape = shape;
/* 105 */     this.solverAbsoluteAccuracy = inverseCumAccuracy;
/*     */   }
/*     */   
/*     */   public LogNormalDistribution() {
/* 116 */     this(0.0D, 1.0D);
/*     */   }
/*     */   
/*     */   public double getScale() {
/* 125 */     return this.scale;
/*     */   }
/*     */   
/*     */   public double getShape() {
/* 134 */     return this.shape;
/*     */   }
/*     */   
/*     */   public double probability(double x) {
/* 145 */     return 0.0D;
/*     */   }
/*     */   
/*     */   public double density(double x) {
/* 160 */     if (x <= 0.0D)
/* 161 */       return 0.0D; 
/* 163 */     double x0 = FastMath.log(x) - this.scale;
/* 164 */     double x1 = x0 / this.shape;
/* 165 */     return FastMath.exp(-0.5D * x1 * x1) / this.shape * SQRT2PI * x;
/*     */   }
/*     */   
/*     */   public double cumulativeProbability(double x) {
/* 184 */     if (x <= 0.0D)
/* 185 */       return 0.0D; 
/* 187 */     double dev = FastMath.log(x) - this.scale;
/* 188 */     if (FastMath.abs(dev) > 40.0D * this.shape)
/* 189 */       return (dev < 0.0D) ? 0.0D : 1.0D; 
/* 191 */     return 0.5D + 0.5D * Erf.erf(dev / this.shape * SQRT2);
/*     */   }
/*     */   
/*     */   public double cumulativeProbability(double x0, double x1) throws NumberIsTooLargeException {
/* 198 */     if (x0 > x1)
/* 199 */       throw new NumberIsTooLargeException(LocalizedFormats.LOWER_ENDPOINT_ABOVE_UPPER_ENDPOINT, Double.valueOf(x0), Double.valueOf(x1), true); 
/* 202 */     if (x0 <= 0.0D || x1 <= 0.0D)
/* 203 */       return super.cumulativeProbability(x0, x1); 
/* 205 */     double denom = this.shape * SQRT2;
/* 206 */     double v0 = (FastMath.log(x0) - this.scale) / denom;
/* 207 */     double v1 = (FastMath.log(x1) - this.scale) / denom;
/* 208 */     return 0.5D * Erf.erf(v0, v1);
/*     */   }
/*     */   
/*     */   protected double getSolverAbsoluteAccuracy() {
/* 214 */     return this.solverAbsoluteAccuracy;
/*     */   }
/*     */   
/*     */   public double getNumericalMean() {
/* 224 */     double s = this.shape;
/* 225 */     return FastMath.exp(this.scale + s * s / 2.0D);
/*     */   }
/*     */   
/*     */   public double getNumericalVariance() {
/* 235 */     double s = this.shape;
/* 236 */     double ss = s * s;
/* 237 */     return (FastMath.exp(ss) - 1.0D) * FastMath.exp(2.0D * this.scale + ss);
/*     */   }
/*     */   
/*     */   public double getSupportLowerBound() {
/* 248 */     return 0.0D;
/*     */   }
/*     */   
/*     */   public double getSupportUpperBound() {
/* 261 */     return Double.POSITIVE_INFINITY;
/*     */   }
/*     */   
/*     */   public boolean isSupportLowerBoundInclusive() {
/* 266 */     return true;
/*     */   }
/*     */   
/*     */   public boolean isSupportUpperBoundInclusive() {
/* 271 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isSupportConnected() {
/* 282 */     return true;
/*     */   }
/*     */   
/*     */   public double sample() {
/* 288 */     double n = this.randomData.nextGaussian(0.0D, 1.0D);
/* 289 */     return FastMath.exp(this.scale + this.shape * n);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\distribution\LogNormalDistribution.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */