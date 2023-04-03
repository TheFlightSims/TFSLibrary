/*     */ package org.apache.commons.math3.distribution;
/*     */ 
/*     */ import org.apache.commons.math3.exception.NotStrictlyPositiveException;
/*     */ import org.apache.commons.math3.exception.OutOfRangeException;
/*     */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ 
/*     */ public class ExponentialDistribution extends AbstractRealDistribution {
/*     */   public static final double DEFAULT_INVERSE_ABSOLUTE_ACCURACY = 1.0E-9D;
/*     */   
/*     */   private static final long serialVersionUID = 2401296428283614780L;
/*     */   
/*     */   private final double mean;
/*     */   
/*     */   private final double solverAbsoluteAccuracy;
/*     */   
/*     */   public ExponentialDistribution(double mean) {
/*  49 */     this(mean, 1.0E-9D);
/*     */   }
/*     */   
/*     */   public ExponentialDistribution(double mean, double inverseCumAccuracy) throws NotStrictlyPositiveException {
/*  64 */     if (mean <= 0.0D)
/*  65 */       throw new NotStrictlyPositiveException(LocalizedFormats.MEAN, Double.valueOf(mean)); 
/*  67 */     this.mean = mean;
/*  68 */     this.solverAbsoluteAccuracy = inverseCumAccuracy;
/*     */   }
/*     */   
/*     */   public double getMean() {
/*  77 */     return this.mean;
/*     */   }
/*     */   
/*     */   public double probability(double x) {
/*  88 */     return 0.0D;
/*     */   }
/*     */   
/*     */   public double density(double x) {
/*  93 */     if (x < 0.0D)
/*  94 */       return 0.0D; 
/*  96 */     return FastMath.exp(-x / this.mean) / this.mean;
/*     */   }
/*     */   
/*     */   public double cumulativeProbability(double x) {
/*     */     double ret;
/* 111 */     if (x <= 0.0D) {
/* 112 */       ret = 0.0D;
/*     */     } else {
/* 114 */       ret = 1.0D - FastMath.exp(-x / this.mean);
/*     */     } 
/* 116 */     return ret;
/*     */   }
/*     */   
/*     */   public double inverseCumulativeProbability(double p) throws OutOfRangeException {
/*     */     double ret;
/* 129 */     if (p < 0.0D || p > 1.0D)
/* 130 */       throw new OutOfRangeException(Double.valueOf(p), Double.valueOf(0.0D), Double.valueOf(1.0D)); 
/* 131 */     if (p == 1.0D) {
/* 132 */       ret = Double.POSITIVE_INFINITY;
/*     */     } else {
/* 134 */       ret = -this.mean * FastMath.log(1.0D - p);
/*     */     } 
/* 137 */     return ret;
/*     */   }
/*     */   
/*     */   public double sample() {
/* 153 */     return this.randomData.nextExponential(this.mean);
/*     */   }
/*     */   
/*     */   protected double getSolverAbsoluteAccuracy() {
/* 159 */     return this.solverAbsoluteAccuracy;
/*     */   }
/*     */   
/*     */   public double getNumericalMean() {
/* 168 */     return getMean();
/*     */   }
/*     */   
/*     */   public double getNumericalVariance() {
/* 177 */     double m = getMean();
/* 178 */     return m * m;
/*     */   }
/*     */   
/*     */   public double getSupportLowerBound() {
/* 189 */     return 0.0D;
/*     */   }
/*     */   
/*     */   public double getSupportUpperBound() {
/* 201 */     return Double.POSITIVE_INFINITY;
/*     */   }
/*     */   
/*     */   public boolean isSupportLowerBoundInclusive() {
/* 206 */     return true;
/*     */   }
/*     */   
/*     */   public boolean isSupportUpperBoundInclusive() {
/* 211 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isSupportConnected() {
/* 222 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\distribution\ExponentialDistribution.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */