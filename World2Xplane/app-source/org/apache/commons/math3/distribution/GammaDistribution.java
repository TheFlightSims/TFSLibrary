/*     */ package org.apache.commons.math3.distribution;
/*     */ 
/*     */ import org.apache.commons.math3.exception.NotStrictlyPositiveException;
/*     */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*     */ import org.apache.commons.math3.special.Gamma;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ 
/*     */ public class GammaDistribution extends AbstractRealDistribution {
/*     */   public static final double DEFAULT_INVERSE_ABSOLUTE_ACCURACY = 1.0E-9D;
/*     */   
/*     */   private static final long serialVersionUID = -3239549463135430361L;
/*     */   
/*     */   private final double alpha;
/*     */   
/*     */   private final double beta;
/*     */   
/*     */   private final double solverAbsoluteAccuracy;
/*     */   
/*     */   public GammaDistribution(double alpha, double beta) {
/*  53 */     this(alpha, beta, 1.0E-9D);
/*     */   }
/*     */   
/*     */   public GammaDistribution(double alpha, double beta, double inverseCumAccuracy) throws NotStrictlyPositiveException {
/*  71 */     if (alpha <= 0.0D)
/*  72 */       throw new NotStrictlyPositiveException(LocalizedFormats.ALPHA, Double.valueOf(alpha)); 
/*  74 */     if (beta <= 0.0D)
/*  75 */       throw new NotStrictlyPositiveException(LocalizedFormats.BETA, Double.valueOf(beta)); 
/*  78 */     this.alpha = alpha;
/*  79 */     this.beta = beta;
/*  80 */     this.solverAbsoluteAccuracy = inverseCumAccuracy;
/*     */   }
/*     */   
/*     */   public double getAlpha() {
/*  89 */     return this.alpha;
/*     */   }
/*     */   
/*     */   public double getBeta() {
/*  98 */     return this.beta;
/*     */   }
/*     */   
/*     */   public double probability(double x) {
/* 109 */     return 0.0D;
/*     */   }
/*     */   
/*     */   public double density(double x) {
/* 114 */     if (x < 0.0D)
/* 115 */       return 0.0D; 
/* 117 */     return FastMath.pow(x / this.beta, this.alpha - 1.0D) / this.beta * FastMath.exp(-x / this.beta) / FastMath.exp(Gamma.logGamma(this.alpha));
/*     */   }
/*     */   
/*     */   public double cumulativeProbability(double x) {
/*     */     double ret;
/* 138 */     if (x <= 0.0D) {
/* 139 */       ret = 0.0D;
/*     */     } else {
/* 141 */       ret = Gamma.regularizedGammaP(this.alpha, x / this.beta);
/*     */     } 
/* 144 */     return ret;
/*     */   }
/*     */   
/*     */   protected double getSolverAbsoluteAccuracy() {
/* 150 */     return this.solverAbsoluteAccuracy;
/*     */   }
/*     */   
/*     */   public double getNumericalMean() {
/* 160 */     return getAlpha() * getBeta();
/*     */   }
/*     */   
/*     */   public double getNumericalVariance() {
/* 172 */     double b = getBeta();
/* 173 */     return getAlpha() * b * b;
/*     */   }
/*     */   
/*     */   public double getSupportLowerBound() {
/* 184 */     return 0.0D;
/*     */   }
/*     */   
/*     */   public double getSupportUpperBound() {
/* 196 */     return Double.POSITIVE_INFINITY;
/*     */   }
/*     */   
/*     */   public boolean isSupportLowerBoundInclusive() {
/* 201 */     return true;
/*     */   }
/*     */   
/*     */   public boolean isSupportUpperBoundInclusive() {
/* 206 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isSupportConnected() {
/* 217 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\distribution\GammaDistribution.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */