/*     */ package org.apache.commons.math3.distribution;
/*     */ 
/*     */ import org.apache.commons.math3.exception.NotStrictlyPositiveException;
/*     */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*     */ import org.apache.commons.math3.special.Beta;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ 
/*     */ public class FDistribution extends AbstractRealDistribution {
/*     */   public static final double DEFAULT_INVERSE_ABSOLUTE_ACCURACY = 1.0E-9D;
/*     */   
/*     */   private static final long serialVersionUID = -8516354193418641566L;
/*     */   
/*     */   private final double numeratorDegreesOfFreedom;
/*     */   
/*     */   private final double denominatorDegreesOfFreedom;
/*     */   
/*     */   private final double solverAbsoluteAccuracy;
/*     */   
/*  52 */   private double numericalVariance = Double.NaN;
/*     */   
/*     */   private boolean numericalVarianceIsCalculated = false;
/*     */   
/*     */   public FDistribution(double numeratorDegreesOfFreedom, double denominatorDegreesOfFreedom) throws NotStrictlyPositiveException {
/*  68 */     this(numeratorDegreesOfFreedom, denominatorDegreesOfFreedom, 1.0E-9D);
/*     */   }
/*     */   
/*     */   public FDistribution(double numeratorDegreesOfFreedom, double denominatorDegreesOfFreedom, double inverseCumAccuracy) throws NotStrictlyPositiveException {
/*  89 */     if (numeratorDegreesOfFreedom <= 0.0D)
/*  90 */       throw new NotStrictlyPositiveException(LocalizedFormats.DEGREES_OF_FREEDOM, Double.valueOf(numeratorDegreesOfFreedom)); 
/*  93 */     if (denominatorDegreesOfFreedom <= 0.0D)
/*  94 */       throw new NotStrictlyPositiveException(LocalizedFormats.DEGREES_OF_FREEDOM, Double.valueOf(denominatorDegreesOfFreedom)); 
/*  97 */     this.numeratorDegreesOfFreedom = numeratorDegreesOfFreedom;
/*  98 */     this.denominatorDegreesOfFreedom = denominatorDegreesOfFreedom;
/*  99 */     this.solverAbsoluteAccuracy = inverseCumAccuracy;
/*     */   }
/*     */   
/*     */   public double probability(double x) {
/* 110 */     return 0.0D;
/*     */   }
/*     */   
/*     */   public double density(double x) {
/* 119 */     double nhalf = this.numeratorDegreesOfFreedom / 2.0D;
/* 120 */     double mhalf = this.denominatorDegreesOfFreedom / 2.0D;
/* 121 */     double logx = FastMath.log(x);
/* 122 */     double logn = FastMath.log(this.numeratorDegreesOfFreedom);
/* 123 */     double logm = FastMath.log(this.denominatorDegreesOfFreedom);
/* 124 */     double lognxm = FastMath.log(this.numeratorDegreesOfFreedom * x + this.denominatorDegreesOfFreedom);
/* 126 */     return FastMath.exp(nhalf * logn + nhalf * logx - logx + mhalf * logm - nhalf * lognxm - mhalf * lognxm - Beta.logBeta(nhalf, mhalf));
/*     */   }
/*     */   
/*     */   public double cumulativeProbability(double x) {
/*     */     double ret;
/* 144 */     if (x <= 0.0D) {
/* 145 */       ret = 0.0D;
/*     */     } else {
/* 147 */       double n = this.numeratorDegreesOfFreedom;
/* 148 */       double m = this.denominatorDegreesOfFreedom;
/* 150 */       ret = Beta.regularizedBeta(n * x / (m + n * x), 0.5D * n, 0.5D * m);
/*     */     } 
/* 154 */     return ret;
/*     */   }
/*     */   
/*     */   public double getNumeratorDegreesOfFreedom() {
/* 163 */     return this.numeratorDegreesOfFreedom;
/*     */   }
/*     */   
/*     */   public double getDenominatorDegreesOfFreedom() {
/* 172 */     return this.denominatorDegreesOfFreedom;
/*     */   }
/*     */   
/*     */   protected double getSolverAbsoluteAccuracy() {
/* 178 */     return this.solverAbsoluteAccuracy;
/*     */   }
/*     */   
/*     */   public double getNumericalMean() {
/* 191 */     double denominatorDF = getDenominatorDegreesOfFreedom();
/* 193 */     if (denominatorDF > 2.0D)
/* 194 */       return denominatorDF / (denominatorDF - 2.0D); 
/* 197 */     return Double.NaN;
/*     */   }
/*     */   
/*     */   public double getNumericalVariance() {
/* 214 */     if (!this.numericalVarianceIsCalculated) {
/* 215 */       this.numericalVariance = calculateNumericalVariance();
/* 216 */       this.numericalVarianceIsCalculated = true;
/*     */     } 
/* 218 */     return this.numericalVariance;
/*     */   }
/*     */   
/*     */   protected double calculateNumericalVariance() {
/* 227 */     double denominatorDF = getDenominatorDegreesOfFreedom();
/* 229 */     if (denominatorDF > 4.0D) {
/* 230 */       double numeratorDF = getNumeratorDegreesOfFreedom();
/* 231 */       double denomDFMinusTwo = denominatorDF - 2.0D;
/* 233 */       return 2.0D * denominatorDF * denominatorDF * (numeratorDF + denominatorDF - 2.0D) / numeratorDF * denomDFMinusTwo * denomDFMinusTwo * (denominatorDF - 4.0D);
/*     */     } 
/* 237 */     return Double.NaN;
/*     */   }
/*     */   
/*     */   public double getSupportLowerBound() {
/* 248 */     return 0.0D;
/*     */   }
/*     */   
/*     */   public double getSupportUpperBound() {
/* 260 */     return Double.POSITIVE_INFINITY;
/*     */   }
/*     */   
/*     */   public boolean isSupportLowerBoundInclusive() {
/* 265 */     return true;
/*     */   }
/*     */   
/*     */   public boolean isSupportUpperBoundInclusive() {
/* 270 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isSupportConnected() {
/* 281 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\distribution\FDistribution.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */