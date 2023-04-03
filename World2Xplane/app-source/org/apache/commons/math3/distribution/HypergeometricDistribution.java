/*     */ package org.apache.commons.math3.distribution;
/*     */ 
/*     */ import org.apache.commons.math3.exception.NotPositiveException;
/*     */ import org.apache.commons.math3.exception.NotStrictlyPositiveException;
/*     */ import org.apache.commons.math3.exception.NumberIsTooLargeException;
/*     */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*     */ import org.apache.commons.math3.util.ArithmeticUtils;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ 
/*     */ public class HypergeometricDistribution extends AbstractIntegerDistribution {
/*     */   private static final long serialVersionUID = -436928820673516179L;
/*     */   
/*     */   private final int numberOfSuccesses;
/*     */   
/*     */   private final int populationSize;
/*     */   
/*     */   private final int sampleSize;
/*     */   
/*  48 */   private double numericalVariance = Double.NaN;
/*     */   
/*     */   private boolean numericalVarianceIsCalculated = false;
/*     */   
/*     */   public HypergeometricDistribution(int populationSize, int numberOfSuccesses, int sampleSize) throws NotPositiveException, NotStrictlyPositiveException, NumberIsTooLargeException {
/*  67 */     if (populationSize <= 0)
/*  68 */       throw new NotStrictlyPositiveException(LocalizedFormats.POPULATION_SIZE, Integer.valueOf(populationSize)); 
/*  71 */     if (numberOfSuccesses < 0)
/*  72 */       throw new NotPositiveException(LocalizedFormats.NUMBER_OF_SUCCESSES, Integer.valueOf(numberOfSuccesses)); 
/*  75 */     if (sampleSize < 0)
/*  76 */       throw new NotPositiveException(LocalizedFormats.NUMBER_OF_SAMPLES, Integer.valueOf(sampleSize)); 
/*  80 */     if (numberOfSuccesses > populationSize)
/*  81 */       throw new NumberIsTooLargeException(LocalizedFormats.NUMBER_OF_SUCCESS_LARGER_THAN_POPULATION_SIZE, Integer.valueOf(numberOfSuccesses), Integer.valueOf(populationSize), true); 
/*  84 */     if (sampleSize > populationSize)
/*  85 */       throw new NumberIsTooLargeException(LocalizedFormats.SAMPLE_SIZE_LARGER_THAN_POPULATION_SIZE, Integer.valueOf(sampleSize), Integer.valueOf(populationSize), true); 
/*  89 */     this.numberOfSuccesses = numberOfSuccesses;
/*  90 */     this.populationSize = populationSize;
/*  91 */     this.sampleSize = sampleSize;
/*     */   }
/*     */   
/*     */   public double cumulativeProbability(int x) {
/*     */     double ret;
/*  98 */     int[] domain = getDomain(this.populationSize, this.numberOfSuccesses, this.sampleSize);
/*  99 */     if (x < domain[0]) {
/* 100 */       ret = 0.0D;
/* 101 */     } else if (x >= domain[1]) {
/* 102 */       ret = 1.0D;
/*     */     } else {
/* 104 */       ret = innerCumulativeProbability(domain[0], x, 1, this.populationSize, this.numberOfSuccesses, this.sampleSize);
/*     */     } 
/* 108 */     return ret;
/*     */   }
/*     */   
/*     */   private int[] getDomain(int n, int m, int k) {
/* 121 */     return new int[] { getLowerDomain(n, m, k), getUpperDomain(m, k) };
/*     */   }
/*     */   
/*     */   private int getLowerDomain(int n, int m, int k) {
/* 134 */     return FastMath.max(0, m - n - k);
/*     */   }
/*     */   
/*     */   public int getNumberOfSuccesses() {
/* 143 */     return this.numberOfSuccesses;
/*     */   }
/*     */   
/*     */   public int getPopulationSize() {
/* 152 */     return this.populationSize;
/*     */   }
/*     */   
/*     */   public int getSampleSize() {
/* 161 */     return this.sampleSize;
/*     */   }
/*     */   
/*     */   private int getUpperDomain(int m, int k) {
/* 173 */     return FastMath.min(k, m);
/*     */   }
/*     */   
/*     */   public double probability(int x) {
/*     */     double ret;
/* 180 */     int[] domain = getDomain(this.populationSize, this.numberOfSuccesses, this.sampleSize);
/* 181 */     if (x < domain[0] || x > domain[1]) {
/* 182 */       ret = 0.0D;
/*     */     } else {
/* 184 */       double p = this.sampleSize / this.populationSize;
/* 185 */       double q = (this.populationSize - this.sampleSize) / this.populationSize;
/* 186 */       double p1 = SaddlePointExpansion.logBinomialProbability(x, this.numberOfSuccesses, p, q);
/* 188 */       double p2 = SaddlePointExpansion.logBinomialProbability(this.sampleSize - x, this.populationSize - this.numberOfSuccesses, p, q);
/* 191 */       double p3 = SaddlePointExpansion.logBinomialProbability(this.sampleSize, this.populationSize, p, q);
/* 193 */       ret = FastMath.exp(p1 + p2 - p3);
/*     */     } 
/* 196 */     return ret;
/*     */   }
/*     */   
/*     */   private double probability(int n, int m, int k, int x) {
/* 210 */     return FastMath.exp(ArithmeticUtils.binomialCoefficientLog(m, x) + ArithmeticUtils.binomialCoefficientLog(n - m, k - x) - ArithmeticUtils.binomialCoefficientLog(n, k));
/*     */   }
/*     */   
/*     */   public double upperCumulativeProbability(int x) {
/*     */     double ret;
/* 225 */     int[] domain = getDomain(this.populationSize, this.numberOfSuccesses, this.sampleSize);
/* 226 */     if (x < domain[0]) {
/* 227 */       ret = 1.0D;
/* 228 */     } else if (x > domain[1]) {
/* 229 */       ret = 0.0D;
/*     */     } else {
/* 231 */       ret = innerCumulativeProbability(domain[1], x, -1, this.populationSize, this.numberOfSuccesses, this.sampleSize);
/*     */     } 
/* 235 */     return ret;
/*     */   }
/*     */   
/*     */   private double innerCumulativeProbability(int x0, int x1, int dx, int n, int m, int k) {
/* 256 */     double ret = probability(n, m, k, x0);
/* 257 */     while (x0 != x1) {
/* 258 */       x0 += dx;
/* 259 */       ret += probability(n, m, k, x0);
/*     */     } 
/* 261 */     return ret;
/*     */   }
/*     */   
/*     */   public double getNumericalMean() {
/* 271 */     return (getSampleSize() * getNumberOfSuccesses()) / getPopulationSize();
/*     */   }
/*     */   
/*     */   public double getNumericalVariance() {
/* 282 */     if (!this.numericalVarianceIsCalculated) {
/* 283 */       this.numericalVariance = calculateNumericalVariance();
/* 284 */       this.numericalVarianceIsCalculated = true;
/*     */     } 
/* 286 */     return this.numericalVariance;
/*     */   }
/*     */   
/*     */   protected double calculateNumericalVariance() {
/* 295 */     double N = getPopulationSize();
/* 296 */     double m = getNumberOfSuccesses();
/* 297 */     double n = getSampleSize();
/* 298 */     return n * m * (N - n) * (N - m) / N * N * (N - 1.0D);
/*     */   }
/*     */   
/*     */   public int getSupportLowerBound() {
/* 311 */     return FastMath.max(0, getSampleSize() + getNumberOfSuccesses() - getPopulationSize());
/*     */   }
/*     */   
/*     */   public int getSupportUpperBound() {
/* 324 */     return FastMath.min(getNumberOfSuccesses(), getSampleSize());
/*     */   }
/*     */   
/*     */   public boolean isSupportConnected() {
/* 335 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\distribution\HypergeometricDistribution.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */