/*     */ package org.apache.commons.math3.distribution;
/*     */ 
/*     */ import org.apache.commons.math3.exception.NotPositiveException;
/*     */ import org.apache.commons.math3.exception.OutOfRangeException;
/*     */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*     */ import org.apache.commons.math3.special.Beta;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ 
/*     */ public class BinomialDistribution extends AbstractIntegerDistribution {
/*     */   private static final long serialVersionUID = 6751309484392813623L;
/*     */   
/*     */   private final int numberOfTrials;
/*     */   
/*     */   private final double probabilityOfSuccess;
/*     */   
/*     */   public BinomialDistribution(int trials, double p) {
/*  50 */     if (trials < 0)
/*  51 */       throw new NotPositiveException(LocalizedFormats.NUMBER_OF_TRIALS, Integer.valueOf(trials)); 
/*  54 */     if (p < 0.0D || p > 1.0D)
/*  55 */       throw new OutOfRangeException(Double.valueOf(p), Integer.valueOf(0), Integer.valueOf(1)); 
/*  58 */     this.probabilityOfSuccess = p;
/*  59 */     this.numberOfTrials = trials;
/*     */   }
/*     */   
/*     */   public int getNumberOfTrials() {
/*  68 */     return this.numberOfTrials;
/*     */   }
/*     */   
/*     */   public double getProbabilityOfSuccess() {
/*  77 */     return this.probabilityOfSuccess;
/*     */   }
/*     */   
/*     */   public double probability(int x) {
/*     */     double ret;
/*  83 */     if (x < 0 || x > this.numberOfTrials) {
/*  84 */       ret = 0.0D;
/*     */     } else {
/*  86 */       ret = FastMath.exp(SaddlePointExpansion.logBinomialProbability(x, this.numberOfTrials, this.probabilityOfSuccess, 1.0D - this.probabilityOfSuccess));
/*     */     } 
/*  90 */     return ret;
/*     */   }
/*     */   
/*     */   public double cumulativeProbability(int x) {
/*     */     double ret;
/*  96 */     if (x < 0) {
/*  97 */       ret = 0.0D;
/*  98 */     } else if (x >= this.numberOfTrials) {
/*  99 */       ret = 1.0D;
/*     */     } else {
/* 101 */       ret = 1.0D - Beta.regularizedBeta(this.probabilityOfSuccess, x + 1.0D, (this.numberOfTrials - x));
/*     */     } 
/* 104 */     return ret;
/*     */   }
/*     */   
/*     */   public double getNumericalMean() {
/* 114 */     return this.numberOfTrials * this.probabilityOfSuccess;
/*     */   }
/*     */   
/*     */   public double getNumericalVariance() {
/* 124 */     double p = this.probabilityOfSuccess;
/* 125 */     return this.numberOfTrials * p * (1.0D - p);
/*     */   }
/*     */   
/*     */   public int getSupportLowerBound() {
/* 137 */     return (this.probabilityOfSuccess < 1.0D) ? 0 : this.numberOfTrials;
/*     */   }
/*     */   
/*     */   public int getSupportUpperBound() {
/* 149 */     return (this.probabilityOfSuccess > 0.0D) ? this.numberOfTrials : 0;
/*     */   }
/*     */   
/*     */   public boolean isSupportConnected() {
/* 160 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\distribution\BinomialDistribution.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */