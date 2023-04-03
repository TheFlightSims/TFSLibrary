/*     */ package org.apache.commons.math3.distribution;
/*     */ 
/*     */ import org.apache.commons.math3.exception.NotStrictlyPositiveException;
/*     */ import org.apache.commons.math3.exception.OutOfRangeException;
/*     */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*     */ import org.apache.commons.math3.special.Beta;
/*     */ import org.apache.commons.math3.util.ArithmeticUtils;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ 
/*     */ public class PascalDistribution extends AbstractIntegerDistribution {
/*     */   private static final long serialVersionUID = 6751309484392813623L;
/*     */   
/*     */   private final int numberOfSuccesses;
/*     */   
/*     */   private final double probabilityOfSuccess;
/*     */   
/*     */   public PascalDistribution(int r, double p) throws NotStrictlyPositiveException, OutOfRangeException {
/*  82 */     if (r <= 0)
/*  83 */       throw new NotStrictlyPositiveException(LocalizedFormats.NUMBER_OF_SUCCESSES, Integer.valueOf(r)); 
/*  86 */     if (p < 0.0D || p > 1.0D)
/*  87 */       throw new OutOfRangeException(Double.valueOf(p), Integer.valueOf(0), Integer.valueOf(1)); 
/*  90 */     this.numberOfSuccesses = r;
/*  91 */     this.probabilityOfSuccess = p;
/*     */   }
/*     */   
/*     */   public int getNumberOfSuccesses() {
/* 100 */     return this.numberOfSuccesses;
/*     */   }
/*     */   
/*     */   public double getProbabilityOfSuccess() {
/* 109 */     return this.probabilityOfSuccess;
/*     */   }
/*     */   
/*     */   public double probability(int x) {
/*     */     double ret;
/* 115 */     if (x < 0) {
/* 116 */       ret = 0.0D;
/*     */     } else {
/* 118 */       ret = ArithmeticUtils.binomialCoefficientDouble(x + this.numberOfSuccesses - 1, this.numberOfSuccesses - 1) * FastMath.pow(this.probabilityOfSuccess, this.numberOfSuccesses) * FastMath.pow(1.0D - this.probabilityOfSuccess, x);
/*     */     } 
/* 123 */     return ret;
/*     */   }
/*     */   
/*     */   public double cumulativeProbability(int x) {
/*     */     double ret;
/* 129 */     if (x < 0) {
/* 130 */       ret = 0.0D;
/*     */     } else {
/* 132 */       ret = Beta.regularizedBeta(this.probabilityOfSuccess, this.numberOfSuccesses, (x + 1));
/*     */     } 
/* 135 */     return ret;
/*     */   }
/*     */   
/*     */   public double getNumericalMean() {
/* 145 */     double p = getProbabilityOfSuccess();
/* 146 */     double r = getNumberOfSuccesses();
/* 147 */     return r * (1.0D - p) / p;
/*     */   }
/*     */   
/*     */   public double getNumericalVariance() {
/* 157 */     double p = getProbabilityOfSuccess();
/* 158 */     double r = getNumberOfSuccesses();
/* 159 */     return r * (1.0D - p) / p * p;
/*     */   }
/*     */   
/*     */   public int getSupportLowerBound() {
/* 170 */     return 0;
/*     */   }
/*     */   
/*     */   public int getSupportUpperBound() {
/* 183 */     return Integer.MAX_VALUE;
/*     */   }
/*     */   
/*     */   public boolean isSupportConnected() {
/* 194 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\distribution\PascalDistribution.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */