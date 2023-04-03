/*     */ package org.apache.commons.math3.distribution;
/*     */ 
/*     */ import org.apache.commons.math3.exception.NotStrictlyPositiveException;
/*     */ import org.apache.commons.math3.exception.OutOfRangeException;
/*     */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*     */ import org.apache.commons.math3.special.Gamma;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ 
/*     */ public class WeibullDistribution extends AbstractRealDistribution {
/*     */   public static final double DEFAULT_INVERSE_ABSOLUTE_ACCURACY = 1.0E-9D;
/*     */   
/*     */   private static final long serialVersionUID = 8589540077390120676L;
/*     */   
/*     */   private final double shape;
/*     */   
/*     */   private final double scale;
/*     */   
/*     */   private final double solverAbsoluteAccuracy;
/*     */   
/*  57 */   private double numericalMean = Double.NaN;
/*     */   
/*     */   private boolean numericalMeanIsCalculated = false;
/*     */   
/*  63 */   private double numericalVariance = Double.NaN;
/*     */   
/*     */   private boolean numericalVarianceIsCalculated = false;
/*     */   
/*     */   public WeibullDistribution(double alpha, double beta) throws NotStrictlyPositiveException {
/*  79 */     this(alpha, beta, 1.0E-9D);
/*     */   }
/*     */   
/*     */   public WeibullDistribution(double alpha, double beta, double inverseCumAccuracy) throws NotStrictlyPositiveException {
/*  98 */     if (alpha <= 0.0D)
/*  99 */       throw new NotStrictlyPositiveException(LocalizedFormats.SHAPE, Double.valueOf(alpha)); 
/* 102 */     if (beta <= 0.0D)
/* 103 */       throw new NotStrictlyPositiveException(LocalizedFormats.SCALE, Double.valueOf(beta)); 
/* 106 */     this.scale = beta;
/* 107 */     this.shape = alpha;
/* 108 */     this.solverAbsoluteAccuracy = inverseCumAccuracy;
/*     */   }
/*     */   
/*     */   public double getShape() {
/* 117 */     return this.shape;
/*     */   }
/*     */   
/*     */   public double getScale() {
/* 126 */     return this.scale;
/*     */   }
/*     */   
/*     */   public double probability(double x) {
/* 137 */     return 0.0D;
/*     */   }
/*     */   
/*     */   public double density(double x) {
/* 142 */     if (x < 0.0D)
/* 143 */       return 0.0D; 
/* 146 */     double xscale = x / this.scale;
/* 147 */     double xscalepow = FastMath.pow(xscale, this.shape - 1.0D);
/* 154 */     double xscalepowshape = xscalepow * xscale;
/* 156 */     return this.shape / this.scale * xscalepow * FastMath.exp(-xscalepowshape);
/*     */   }
/*     */   
/*     */   public double cumulativeProbability(double x) {
/*     */     double ret;
/* 162 */     if (x <= 0.0D) {
/* 163 */       ret = 0.0D;
/*     */     } else {
/* 165 */       ret = 1.0D - FastMath.exp(-FastMath.pow(x / this.scale, this.shape));
/*     */     } 
/* 167 */     return ret;
/*     */   }
/*     */   
/*     */   public double inverseCumulativeProbability(double p) {
/*     */     double ret;
/* 179 */     if (p < 0.0D || p > 1.0D)
/* 180 */       throw new OutOfRangeException(Double.valueOf(p), Double.valueOf(0.0D), Double.valueOf(1.0D)); 
/* 181 */     if (p == 0.0D) {
/* 182 */       ret = 0.0D;
/* 183 */     } else if (p == 1.0D) {
/* 184 */       ret = Double.POSITIVE_INFINITY;
/*     */     } else {
/* 186 */       ret = this.scale * FastMath.pow(-FastMath.log(1.0D - p), 1.0D / this.shape);
/*     */     } 
/* 188 */     return ret;
/*     */   }
/*     */   
/*     */   protected double getSolverAbsoluteAccuracy() {
/* 200 */     return this.solverAbsoluteAccuracy;
/*     */   }
/*     */   
/*     */   public double getNumericalMean() {
/* 210 */     if (!this.numericalMeanIsCalculated) {
/* 211 */       this.numericalMean = calculateNumericalMean();
/* 212 */       this.numericalMeanIsCalculated = true;
/*     */     } 
/* 214 */     return this.numericalMean;
/*     */   }
/*     */   
/*     */   protected double calculateNumericalMean() {
/* 223 */     double sh = getShape();
/* 224 */     double sc = getScale();
/* 226 */     return sc * FastMath.exp(Gamma.logGamma(1.0D + 1.0D / sh));
/*     */   }
/*     */   
/*     */   public double getNumericalVariance() {
/* 236 */     if (!this.numericalVarianceIsCalculated) {
/* 237 */       this.numericalVariance = calculateNumericalVariance();
/* 238 */       this.numericalVarianceIsCalculated = true;
/*     */     } 
/* 240 */     return this.numericalVariance;
/*     */   }
/*     */   
/*     */   protected double calculateNumericalVariance() {
/* 249 */     double sh = getShape();
/* 250 */     double sc = getScale();
/* 251 */     double mn = getNumericalMean();
/* 253 */     return sc * sc * FastMath.exp(Gamma.logGamma(1.0D + 2.0D / sh)) - mn * mn;
/*     */   }
/*     */   
/*     */   public double getSupportLowerBound() {
/* 265 */     return 0.0D;
/*     */   }
/*     */   
/*     */   public double getSupportUpperBound() {
/* 278 */     return Double.POSITIVE_INFINITY;
/*     */   }
/*     */   
/*     */   public boolean isSupportLowerBoundInclusive() {
/* 283 */     return true;
/*     */   }
/*     */   
/*     */   public boolean isSupportUpperBoundInclusive() {
/* 288 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isSupportConnected() {
/* 299 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\distribution\WeibullDistribution.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */