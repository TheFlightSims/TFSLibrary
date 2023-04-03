/*     */ package org.apache.commons.math3.distribution;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.apache.commons.math3.exception.MathInternalError;
/*     */ import org.apache.commons.math3.exception.NotStrictlyPositiveException;
/*     */ import org.apache.commons.math3.exception.NumberIsTooLargeException;
/*     */ import org.apache.commons.math3.exception.OutOfRangeException;
/*     */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*     */ import org.apache.commons.math3.random.RandomDataImpl;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ 
/*     */ public abstract class AbstractIntegerDistribution implements IntegerDistribution, Serializable {
/*     */   private static final long serialVersionUID = -1146319659338487221L;
/*     */   
/*  44 */   protected final RandomDataImpl randomData = new RandomDataImpl();
/*     */   
/*     */   public double cumulativeProbability(int x0, int x1) throws NumberIsTooLargeException {
/*  56 */     if (x1 < x0)
/*  57 */       throw new NumberIsTooLargeException(LocalizedFormats.LOWER_ENDPOINT_ABOVE_UPPER_ENDPOINT, Integer.valueOf(x0), Integer.valueOf(x1), true); 
/*  60 */     return cumulativeProbability(x1) - cumulativeProbability(x0);
/*     */   }
/*     */   
/*     */   public int inverseCumulativeProbability(double p) throws OutOfRangeException {
/*  75 */     if (p < 0.0D || p > 1.0D)
/*  76 */       throw new OutOfRangeException(Double.valueOf(p), Integer.valueOf(0), Integer.valueOf(1)); 
/*  79 */     int lower = getSupportLowerBound();
/*  80 */     if (p == 0.0D)
/*  81 */       return lower; 
/*  83 */     if (lower == Integer.MIN_VALUE) {
/*  84 */       if (checkedCumulativeProbability(lower) >= p)
/*  85 */         return lower; 
/*     */     } else {
/*  88 */       lower--;
/*     */     } 
/*  92 */     int upper = getSupportUpperBound();
/*  93 */     if (p == 1.0D)
/*  94 */       return upper; 
/*  99 */     double mu = getNumericalMean();
/* 100 */     double sigma = FastMath.sqrt(getNumericalVariance());
/* 101 */     boolean chebyshevApplies = (!Double.isInfinite(mu) && !Double.isNaN(mu) && !Double.isInfinite(sigma) && !Double.isNaN(sigma) && sigma != 0.0D);
/* 103 */     if (chebyshevApplies) {
/* 104 */       double k = FastMath.sqrt((1.0D - p) / p);
/* 105 */       double tmp = mu - k * sigma;
/* 106 */       if (tmp > lower)
/* 107 */         lower = (int)Math.ceil(tmp) - 1; 
/* 109 */       k = 1.0D / k;
/* 110 */       tmp = mu + k * sigma;
/* 111 */       if (tmp < upper)
/* 112 */         upper = (int)Math.ceil(tmp) - 1; 
/*     */     } 
/* 116 */     return solveInverseCumulativeProbability(p, lower, upper);
/*     */   }
/*     */   
/*     */   protected int solveInverseCumulativeProbability(double p, int lower, int upper) {
/* 132 */     while (lower + 1 < upper) {
/* 133 */       int xm = (lower + upper) / 2;
/* 134 */       if (xm < lower || xm > upper)
/* 140 */         xm = lower + (upper - lower) / 2; 
/* 143 */       double pm = checkedCumulativeProbability(xm);
/* 144 */       if (pm >= p) {
/* 145 */         upper = xm;
/*     */         continue;
/*     */       } 
/* 147 */       lower = xm;
/*     */     } 
/* 150 */     return upper;
/*     */   }
/*     */   
/*     */   public void reseedRandomGenerator(long seed) {
/* 155 */     this.randomData.reSeed(seed);
/*     */   }
/*     */   
/*     */   public int sample() {
/* 166 */     return this.randomData.nextInversionDeviate(this);
/*     */   }
/*     */   
/*     */   public int[] sample(int sampleSize) {
/* 176 */     if (sampleSize <= 0)
/* 177 */       throw new NotStrictlyPositiveException(LocalizedFormats.NUMBER_OF_SAMPLES, Integer.valueOf(sampleSize)); 
/* 180 */     int[] out = new int[sampleSize];
/* 181 */     for (int i = 0; i < sampleSize; i++)
/* 182 */       out[i] = sample(); 
/* 184 */     return out;
/*     */   }
/*     */   
/*     */   private double checkedCumulativeProbability(int argument) throws MathInternalError {
/* 200 */     double result = Double.NaN;
/* 201 */     result = cumulativeProbability(argument);
/* 202 */     if (Double.isNaN(result))
/* 203 */       throw new MathInternalError(LocalizedFormats.DISCRETE_CUMULATIVE_PROBABILITY_RETURNED_NAN, new Object[] { Integer.valueOf(argument) }); 
/* 206 */     return result;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\distribution\AbstractIntegerDistribution.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */