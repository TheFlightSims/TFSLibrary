/*     */ package org.apache.commons.math3.distribution;
/*     */ 
/*     */ public class ChiSquaredDistribution extends AbstractRealDistribution {
/*     */   public static final double DEFAULT_INVERSE_ABSOLUTE_ACCURACY = 1.0E-9D;
/*     */   
/*     */   private static final long serialVersionUID = -8352658048349159782L;
/*     */   
/*     */   private final GammaDistribution gamma;
/*     */   
/*     */   private final double solverAbsoluteAccuracy;
/*     */   
/*     */   public ChiSquaredDistribution(double degreesOfFreedom) {
/*  45 */     this(degreesOfFreedom, 1.0E-9D);
/*     */   }
/*     */   
/*     */   public ChiSquaredDistribution(double degreesOfFreedom, double inverseCumAccuracy) {
/*  60 */     this.gamma = new GammaDistribution(degreesOfFreedom / 2.0D, 2.0D);
/*  61 */     this.solverAbsoluteAccuracy = inverseCumAccuracy;
/*     */   }
/*     */   
/*     */   public double getDegreesOfFreedom() {
/*  70 */     return this.gamma.getAlpha() * 2.0D;
/*     */   }
/*     */   
/*     */   public double probability(double x) {
/*  81 */     return 0.0D;
/*     */   }
/*     */   
/*     */   public double density(double x) {
/*  86 */     return this.gamma.density(x);
/*     */   }
/*     */   
/*     */   public double cumulativeProbability(double x) {
/*  91 */     return this.gamma.cumulativeProbability(x);
/*     */   }
/*     */   
/*     */   protected double getSolverAbsoluteAccuracy() {
/*  97 */     return this.solverAbsoluteAccuracy;
/*     */   }
/*     */   
/*     */   public double getNumericalMean() {
/* 106 */     return getDegreesOfFreedom();
/*     */   }
/*     */   
/*     */   public double getNumericalVariance() {
/* 117 */     return 2.0D * getDegreesOfFreedom();
/*     */   }
/*     */   
/*     */   public double getSupportLowerBound() {
/* 129 */     return 0.0D;
/*     */   }
/*     */   
/*     */   public double getSupportUpperBound() {
/* 141 */     return Double.POSITIVE_INFINITY;
/*     */   }
/*     */   
/*     */   public boolean isSupportLowerBoundInclusive() {
/* 146 */     return true;
/*     */   }
/*     */   
/*     */   public boolean isSupportUpperBoundInclusive() {
/* 151 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isSupportConnected() {
/* 162 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\distribution\ChiSquaredDistribution.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */