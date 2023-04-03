/*     */ package org.apache.commons.math3.distribution;
/*     */ 
/*     */ import org.apache.commons.math3.exception.NumberIsTooLargeException;
/*     */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*     */ 
/*     */ public class UniformIntegerDistribution extends AbstractIntegerDistribution {
/*     */   private static final long serialVersionUID = 20120109L;
/*     */   
/*     */   private final int lower;
/*     */   
/*     */   private final int upper;
/*     */   
/*     */   public UniformIntegerDistribution(int lower, int upper) throws NumberIsTooLargeException {
/*  51 */     if (lower >= upper)
/*  52 */       throw new NumberIsTooLargeException(LocalizedFormats.LOWER_BOUND_NOT_BELOW_UPPER_BOUND, Integer.valueOf(lower), Integer.valueOf(upper), false); 
/*  56 */     this.lower = lower;
/*  57 */     this.upper = upper;
/*     */   }
/*     */   
/*     */   public double probability(int x) {
/*  62 */     if (x < this.lower || x > this.upper)
/*  63 */       return 0.0D; 
/*  65 */     return 1.0D / (this.upper - this.lower + 1);
/*     */   }
/*     */   
/*     */   public double cumulativeProbability(int x) {
/*  70 */     if (x < this.lower)
/*  71 */       return 0.0D; 
/*  73 */     if (x > this.upper)
/*  74 */       return 1.0D; 
/*  76 */     return ((x - this.lower) + 1.0D) / ((this.upper - this.lower) + 1.0D);
/*     */   }
/*     */   
/*     */   public double getNumericalMean() {
/*  86 */     return 0.5D * (this.lower + this.upper);
/*     */   }
/*     */   
/*     */   public double getNumericalVariance() {
/*  96 */     double n = (this.upper - this.lower + 1);
/*  97 */     return (n * n - 1.0D) / 12.0D;
/*     */   }
/*     */   
/*     */   public int getSupportLowerBound() {
/* 109 */     return this.lower;
/*     */   }
/*     */   
/*     */   public int getSupportUpperBound() {
/* 121 */     return this.upper;
/*     */   }
/*     */   
/*     */   public boolean isSupportConnected() {
/* 132 */     return true;
/*     */   }
/*     */   
/*     */   public int sample() {
/* 138 */     return this.randomData.nextInt(this.lower, this.upper);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\distribution\UniformIntegerDistribution.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */