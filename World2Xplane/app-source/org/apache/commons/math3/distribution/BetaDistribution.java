/*     */ package org.apache.commons.math3.distribution;
/*     */ 
/*     */ import org.apache.commons.math3.exception.NumberIsTooSmallException;
/*     */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*     */ import org.apache.commons.math3.special.Beta;
/*     */ import org.apache.commons.math3.special.Gamma;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ 
/*     */ public class BetaDistribution extends AbstractRealDistribution {
/*     */   public static final double DEFAULT_INVERSE_ABSOLUTE_ACCURACY = 1.0E-9D;
/*     */   
/*     */   private static final long serialVersionUID = -1221965979403477668L;
/*     */   
/*     */   private final double alpha;
/*     */   
/*     */   private final double beta;
/*     */   
/*     */   private double z;
/*     */   
/*     */   private final double solverAbsoluteAccuracy;
/*     */   
/*     */   public BetaDistribution(double alpha, double beta, double inverseCumAccuracy) {
/*  62 */     this.alpha = alpha;
/*  63 */     this.beta = beta;
/*  64 */     this.z = Double.NaN;
/*  65 */     this.solverAbsoluteAccuracy = inverseCumAccuracy;
/*     */   }
/*     */   
/*     */   public BetaDistribution(double alpha, double beta) {
/*  75 */     this(alpha, beta, 1.0E-9D);
/*     */   }
/*     */   
/*     */   public double getAlpha() {
/*  84 */     return this.alpha;
/*     */   }
/*     */   
/*     */   public double getBeta() {
/*  93 */     return this.beta;
/*     */   }
/*     */   
/*     */   private void recomputeZ() {
/*  98 */     if (Double.isNaN(this.z))
/*  99 */       this.z = Gamma.logGamma(this.alpha) + Gamma.logGamma(this.beta) - Gamma.logGamma(this.alpha + this.beta); 
/*     */   }
/*     */   
/*     */   public double probability(double x) {
/* 111 */     return 0.0D;
/*     */   }
/*     */   
/*     */   public double density(double x) {
/* 116 */     recomputeZ();
/* 117 */     if (x < 0.0D || x > 1.0D)
/* 118 */       return 0.0D; 
/* 119 */     if (x == 0.0D) {
/* 120 */       if (this.alpha < 1.0D)
/* 121 */         throw new NumberIsTooSmallException(LocalizedFormats.CANNOT_COMPUTE_BETA_DENSITY_AT_0_FOR_SOME_ALPHA, Double.valueOf(this.alpha), Integer.valueOf(1), false); 
/* 123 */       return 0.0D;
/*     */     } 
/* 124 */     if (x == 1.0D) {
/* 125 */       if (this.beta < 1.0D)
/* 126 */         throw new NumberIsTooSmallException(LocalizedFormats.CANNOT_COMPUTE_BETA_DENSITY_AT_1_FOR_SOME_BETA, Double.valueOf(this.beta), Integer.valueOf(1), false); 
/* 128 */       return 0.0D;
/*     */     } 
/* 130 */     double logX = FastMath.log(x);
/* 131 */     double log1mX = FastMath.log1p(-x);
/* 132 */     return FastMath.exp((this.alpha - 1.0D) * logX + (this.beta - 1.0D) * log1mX - this.z);
/*     */   }
/*     */   
/*     */   public double cumulativeProbability(double x) {
/* 138 */     if (x <= 0.0D)
/* 139 */       return 0.0D; 
/* 140 */     if (x >= 1.0D)
/* 141 */       return 1.0D; 
/* 143 */     return Beta.regularizedBeta(x, this.alpha, this.beta);
/*     */   }
/*     */   
/*     */   protected double getSolverAbsoluteAccuracy() {
/* 156 */     return this.solverAbsoluteAccuracy;
/*     */   }
/*     */   
/*     */   public double getNumericalMean() {
/* 166 */     double a = getAlpha();
/* 167 */     return a / (a + getBeta());
/*     */   }
/*     */   
/*     */   public double getNumericalVariance() {
/* 178 */     double a = getAlpha();
/* 179 */     double b = getBeta();
/* 180 */     double alphabetasum = a + b;
/* 181 */     return a * b / alphabetasum * alphabetasum * (alphabetasum + 1.0D);
/*     */   }
/*     */   
/*     */   public double getSupportLowerBound() {
/* 192 */     return 0.0D;
/*     */   }
/*     */   
/*     */   public double getSupportUpperBound() {
/* 203 */     return 1.0D;
/*     */   }
/*     */   
/*     */   public boolean isSupportLowerBoundInclusive() {
/* 208 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isSupportUpperBoundInclusive() {
/* 213 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isSupportConnected() {
/* 224 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\distribution\BetaDistribution.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */