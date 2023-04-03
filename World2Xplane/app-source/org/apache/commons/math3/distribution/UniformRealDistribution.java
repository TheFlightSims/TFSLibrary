/*     */ package org.apache.commons.math3.distribution;
/*     */ 
/*     */ import org.apache.commons.math3.exception.NumberIsTooLargeException;
/*     */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*     */ 
/*     */ public class UniformRealDistribution extends AbstractRealDistribution {
/*     */   public static final double DEFAULT_INVERSE_ABSOLUTE_ACCURACY = 1.0E-9D;
/*     */   
/*     */   private static final long serialVersionUID = 20120109L;
/*     */   
/*     */   private final double lower;
/*     */   
/*     */   private final double upper;
/*     */   
/*     */   private final double solverAbsoluteAccuracy;
/*     */   
/*     */   public UniformRealDistribution(double lower, double upper) throws NumberIsTooLargeException {
/*  58 */     this(lower, upper, 1.0E-9D);
/*     */   }
/*     */   
/*     */   public UniformRealDistribution(double lower, double upper, double inverseCumAccuracy) throws NumberIsTooLargeException {
/*  72 */     if (lower >= upper)
/*  73 */       throw new NumberIsTooLargeException(LocalizedFormats.LOWER_BOUND_NOT_BELOW_UPPER_BOUND, Double.valueOf(lower), Double.valueOf(upper), false); 
/*  78 */     this.lower = lower;
/*  79 */     this.upper = upper;
/*  80 */     this.solverAbsoluteAccuracy = inverseCumAccuracy;
/*     */   }
/*     */   
/*     */   public UniformRealDistribution() {
/*  88 */     this(0.0D, 1.0D);
/*     */   }
/*     */   
/*     */   public double probability(double x) {
/*  99 */     return 0.0D;
/*     */   }
/*     */   
/*     */   public double density(double x) {
/* 104 */     if (x < this.lower || x > this.upper)
/* 105 */       return 0.0D; 
/* 107 */     return 1.0D / (this.upper - this.lower);
/*     */   }
/*     */   
/*     */   public double cumulativeProbability(double x) {
/* 112 */     if (x <= this.lower)
/* 113 */       return 0.0D; 
/* 115 */     if (x >= this.upper)
/* 116 */       return 1.0D; 
/* 118 */     return (x - this.lower) / (this.upper - this.lower);
/*     */   }
/*     */   
/*     */   protected double getSolverAbsoluteAccuracy() {
/* 124 */     return this.solverAbsoluteAccuracy;
/*     */   }
/*     */   
/*     */   public double getNumericalMean() {
/* 134 */     return 0.5D * (this.lower + this.upper);
/*     */   }
/*     */   
/*     */   public double getNumericalVariance() {
/* 144 */     double ul = this.upper - this.lower;
/* 145 */     return ul * ul / 12.0D;
/*     */   }
/*     */   
/*     */   public double getSupportLowerBound() {
/* 157 */     return this.lower;
/*     */   }
/*     */   
/*     */   public double getSupportUpperBound() {
/* 169 */     return this.upper;
/*     */   }
/*     */   
/*     */   public boolean isSupportLowerBoundInclusive() {
/* 174 */     return true;
/*     */   }
/*     */   
/*     */   public boolean isSupportUpperBoundInclusive() {
/* 179 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isSupportConnected() {
/* 190 */     return true;
/*     */   }
/*     */   
/*     */   public double sample() {
/* 196 */     return this.randomData.nextUniform(this.lower, this.upper, true);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\distribution\UniformRealDistribution.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */