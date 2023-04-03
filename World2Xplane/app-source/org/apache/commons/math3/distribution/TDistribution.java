/*     */ package org.apache.commons.math3.distribution;
/*     */ 
/*     */ import org.apache.commons.math3.exception.NotStrictlyPositiveException;
/*     */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*     */ import org.apache.commons.math3.special.Beta;
/*     */ import org.apache.commons.math3.special.Gamma;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ 
/*     */ public class TDistribution extends AbstractRealDistribution {
/*     */   public static final double DEFAULT_INVERSE_ABSOLUTE_ACCURACY = 1.0E-9D;
/*     */   
/*     */   private static final long serialVersionUID = -5852615386664158222L;
/*     */   
/*     */   private final double degreesOfFreedom;
/*     */   
/*     */   private final double solverAbsoluteAccuracy;
/*     */   
/*     */   public TDistribution(double degreesOfFreedom, double inverseCumAccuracy) throws NotStrictlyPositiveException {
/*  58 */     if (degreesOfFreedom <= 0.0D)
/*  59 */       throw new NotStrictlyPositiveException(LocalizedFormats.DEGREES_OF_FREEDOM, Double.valueOf(degreesOfFreedom)); 
/*  62 */     this.degreesOfFreedom = degreesOfFreedom;
/*  63 */     this.solverAbsoluteAccuracy = inverseCumAccuracy;
/*     */   }
/*     */   
/*     */   public TDistribution(double degreesOfFreedom) throws NotStrictlyPositiveException {
/*  74 */     this(degreesOfFreedom, 1.0E-9D);
/*     */   }
/*     */   
/*     */   public double getDegreesOfFreedom() {
/*  83 */     return this.degreesOfFreedom;
/*     */   }
/*     */   
/*     */   public double probability(double x) {
/*  94 */     return 0.0D;
/*     */   }
/*     */   
/*     */   public double density(double x) {
/*  99 */     double n = this.degreesOfFreedom;
/* 100 */     double nPlus1Over2 = (n + 1.0D) / 2.0D;
/* 101 */     return FastMath.exp(Gamma.logGamma(nPlus1Over2) - 0.5D * (FastMath.log(Math.PI) + FastMath.log(n)) - Gamma.logGamma(n / 2.0D) - nPlus1Over2 * FastMath.log(1.0D + x * x / n));
/*     */   }
/*     */   
/*     */   public double cumulativeProbability(double x) {
/*     */     double ret;
/* 111 */     if (x == 0.0D) {
/* 112 */       ret = 0.5D;
/*     */     } else {
/* 114 */       double t = Beta.regularizedBeta(this.degreesOfFreedom / (this.degreesOfFreedom + x * x), 0.5D * this.degreesOfFreedom, 0.5D);
/* 119 */       if (x < 0.0D) {
/* 120 */         ret = 0.5D * t;
/*     */       } else {
/* 122 */         ret = 1.0D - 0.5D * t;
/*     */       } 
/*     */     } 
/* 126 */     return ret;
/*     */   }
/*     */   
/*     */   protected double getSolverAbsoluteAccuracy() {
/* 132 */     return this.solverAbsoluteAccuracy;
/*     */   }
/*     */   
/*     */   public double getNumericalMean() {
/* 145 */     double df = getDegreesOfFreedom();
/* 147 */     if (df > 1.0D)
/* 148 */       return 0.0D; 
/* 151 */     return Double.NaN;
/*     */   }
/*     */   
/*     */   public double getNumericalVariance() {
/* 166 */     double df = getDegreesOfFreedom();
/* 168 */     if (df > 2.0D)
/* 169 */       return df / (df - 2.0D); 
/* 172 */     if (df > 1.0D && df <= 2.0D)
/* 173 */       return Double.POSITIVE_INFINITY; 
/* 176 */     return Double.NaN;
/*     */   }
/*     */   
/*     */   public double getSupportLowerBound() {
/* 189 */     return Double.NEGATIVE_INFINITY;
/*     */   }
/*     */   
/*     */   public double getSupportUpperBound() {
/* 202 */     return Double.POSITIVE_INFINITY;
/*     */   }
/*     */   
/*     */   public boolean isSupportLowerBoundInclusive() {
/* 207 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isSupportUpperBoundInclusive() {
/* 212 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isSupportConnected() {
/* 223 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\distribution\TDistribution.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */