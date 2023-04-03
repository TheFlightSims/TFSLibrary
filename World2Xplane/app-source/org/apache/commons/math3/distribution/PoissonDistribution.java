/*     */ package org.apache.commons.math3.distribution;
/*     */ 
/*     */ import org.apache.commons.math3.exception.NotStrictlyPositiveException;
/*     */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*     */ import org.apache.commons.math3.special.Gamma;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ 
/*     */ public class PoissonDistribution extends AbstractIntegerDistribution {
/*     */   public static final int DEFAULT_MAX_ITERATIONS = 10000000;
/*     */   
/*     */   public static final double DEFAULT_EPSILON = 1.0E-12D;
/*     */   
/*     */   private static final long serialVersionUID = -3349935121172596109L;
/*     */   
/*     */   private final NormalDistribution normal;
/*     */   
/*     */   private final double mean;
/*     */   
/*     */   private final int maxIterations;
/*     */   
/*     */   private final double epsilon;
/*     */   
/*     */   public PoissonDistribution(double p) throws NotStrictlyPositiveException {
/*  73 */     this(p, 1.0E-12D, 10000000);
/*     */   }
/*     */   
/*     */   public PoissonDistribution(double p, double epsilon, int maxIterations) throws NotStrictlyPositiveException {
/*  89 */     if (p <= 0.0D)
/*  90 */       throw new NotStrictlyPositiveException(LocalizedFormats.MEAN, Double.valueOf(p)); 
/*  92 */     this.mean = p;
/*  93 */     this.normal = new NormalDistribution(p, FastMath.sqrt(p));
/*  94 */     this.epsilon = epsilon;
/*  95 */     this.maxIterations = maxIterations;
/*     */   }
/*     */   
/*     */   public PoissonDistribution(double p, double epsilon) throws NotStrictlyPositiveException {
/* 109 */     this(p, epsilon, 10000000);
/*     */   }
/*     */   
/*     */   public PoissonDistribution(double p, int maxIterations) {
/* 122 */     this(p, 1.0E-12D, maxIterations);
/*     */   }
/*     */   
/*     */   public double getMean() {
/* 131 */     return this.mean;
/*     */   }
/*     */   
/*     */   public double probability(int x) {
/*     */     double ret;
/* 137 */     if (x < 0 || x == Integer.MAX_VALUE) {
/* 138 */       ret = 0.0D;
/* 139 */     } else if (x == 0) {
/* 140 */       ret = FastMath.exp(-this.mean);
/*     */     } else {
/* 142 */       ret = FastMath.exp(-SaddlePointExpansion.getStirlingError(x) - SaddlePointExpansion.getDeviancePart(x, this.mean)) / FastMath.sqrt(6.283185307179586D * x);
/*     */     } 
/* 146 */     return ret;
/*     */   }
/*     */   
/*     */   public double cumulativeProbability(int x) {
/* 151 */     if (x < 0)
/* 152 */       return 0.0D; 
/* 154 */     if (x == Integer.MAX_VALUE)
/* 155 */       return 1.0D; 
/* 157 */     return Gamma.regularizedGammaQ(x + 1.0D, this.mean, this.epsilon, this.maxIterations);
/*     */   }
/*     */   
/*     */   public double normalApproximateProbability(int x) {
/* 174 */     return this.normal.cumulativeProbability(x + 0.5D);
/*     */   }
/*     */   
/*     */   public double getNumericalMean() {
/* 183 */     return getMean();
/*     */   }
/*     */   
/*     */   public double getNumericalVariance() {
/* 192 */     return getMean();
/*     */   }
/*     */   
/*     */   public int getSupportLowerBound() {
/* 203 */     return 0;
/*     */   }
/*     */   
/*     */   public int getSupportUpperBound() {
/* 217 */     return Integer.MAX_VALUE;
/*     */   }
/*     */   
/*     */   public boolean isSupportConnected() {
/* 228 */     return true;
/*     */   }
/*     */   
/*     */   public int sample() {
/* 255 */     return (int)FastMath.min(this.randomData.nextPoisson(this.mean), 2147483647L);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\distribution\PoissonDistribution.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */