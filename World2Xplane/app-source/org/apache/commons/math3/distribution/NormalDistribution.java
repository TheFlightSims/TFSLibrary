/*     */ package org.apache.commons.math3.distribution;
/*     */ 
/*     */ import org.apache.commons.math3.exception.NotStrictlyPositiveException;
/*     */ import org.apache.commons.math3.exception.NumberIsTooLargeException;
/*     */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*     */ import org.apache.commons.math3.special.Erf;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ 
/*     */ public class NormalDistribution extends AbstractRealDistribution {
/*     */   public static final double DEFAULT_INVERSE_ABSOLUTE_ACCURACY = 1.0E-9D;
/*     */   
/*     */   private static final long serialVersionUID = 8589540077390120676L;
/*     */   
/*  42 */   private static final double SQRT2PI = FastMath.sqrt(6.283185307179586D);
/*     */   
/*  44 */   private static final double SQRT2 = FastMath.sqrt(2.0D);
/*     */   
/*     */   private final double mean;
/*     */   
/*     */   private final double standardDeviation;
/*     */   
/*     */   private final double solverAbsoluteAccuracy;
/*     */   
/*     */   public NormalDistribution(double mean, double sd) throws NotStrictlyPositiveException {
/*  61 */     this(mean, sd, 1.0E-9D);
/*     */   }
/*     */   
/*     */   public NormalDistribution(double mean, double sd, double inverseCumAccuracy) throws NotStrictlyPositiveException {
/*  76 */     if (sd <= 0.0D)
/*  77 */       throw new NotStrictlyPositiveException(LocalizedFormats.STANDARD_DEVIATION, Double.valueOf(sd)); 
/*  80 */     this.mean = mean;
/*  81 */     this.standardDeviation = sd;
/*  82 */     this.solverAbsoluteAccuracy = inverseCumAccuracy;
/*     */   }
/*     */   
/*     */   public NormalDistribution() {
/*  90 */     this(0.0D, 1.0D);
/*     */   }
/*     */   
/*     */   public double getMean() {
/*  99 */     return this.mean;
/*     */   }
/*     */   
/*     */   public double getStandardDeviation() {
/* 108 */     return this.standardDeviation;
/*     */   }
/*     */   
/*     */   public double probability(double x) {
/* 119 */     return 0.0D;
/*     */   }
/*     */   
/*     */   public double density(double x) {
/* 124 */     double x0 = x - this.mean;
/* 125 */     double x1 = x0 / this.standardDeviation;
/* 126 */     return FastMath.exp(-0.5D * x1 * x1) / this.standardDeviation * SQRT2PI;
/*     */   }
/*     */   
/*     */   public double cumulativeProbability(double x) {
/* 137 */     double dev = x - this.mean;
/* 138 */     if (FastMath.abs(dev) > 40.0D * this.standardDeviation)
/* 139 */       return (dev < 0.0D) ? 0.0D : 1.0D; 
/* 141 */     return 0.5D * (1.0D + Erf.erf(dev / this.standardDeviation * SQRT2));
/*     */   }
/*     */   
/*     */   public double cumulativeProbability(double x0, double x1) throws NumberIsTooLargeException {
/* 148 */     if (x0 > x1)
/* 149 */       throw new NumberIsTooLargeException(LocalizedFormats.LOWER_ENDPOINT_ABOVE_UPPER_ENDPOINT, Double.valueOf(x0), Double.valueOf(x1), true); 
/* 152 */     double denom = this.standardDeviation * SQRT2;
/* 153 */     double v0 = (x0 - this.mean) / denom;
/* 154 */     double v1 = (x1 - this.mean) / denom;
/* 155 */     return 0.5D * Erf.erf(v0, v1);
/*     */   }
/*     */   
/*     */   protected double getSolverAbsoluteAccuracy() {
/* 161 */     return this.solverAbsoluteAccuracy;
/*     */   }
/*     */   
/*     */   public double getNumericalMean() {
/* 170 */     return getMean();
/*     */   }
/*     */   
/*     */   public double getNumericalVariance() {
/* 179 */     double s = getStandardDeviation();
/* 180 */     return s * s;
/*     */   }
/*     */   
/*     */   public double getSupportLowerBound() {
/* 193 */     return Double.NEGATIVE_INFINITY;
/*     */   }
/*     */   
/*     */   public double getSupportUpperBound() {
/* 206 */     return Double.POSITIVE_INFINITY;
/*     */   }
/*     */   
/*     */   public boolean isSupportLowerBoundInclusive() {
/* 211 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isSupportUpperBoundInclusive() {
/* 216 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isSupportConnected() {
/* 227 */     return true;
/*     */   }
/*     */   
/*     */   public double sample() {
/* 233 */     return this.randomData.nextGaussian(this.mean, this.standardDeviation);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\distribution\NormalDistribution.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */