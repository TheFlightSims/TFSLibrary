/*     */ package org.apache.commons.math3.distribution;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.apache.commons.math3.analysis.UnivariateFunction;
/*     */ import org.apache.commons.math3.analysis.solvers.UnivariateSolverUtils;
/*     */ import org.apache.commons.math3.exception.NotStrictlyPositiveException;
/*     */ import org.apache.commons.math3.exception.NumberIsTooLargeException;
/*     */ import org.apache.commons.math3.exception.OutOfRangeException;
/*     */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*     */ import org.apache.commons.math3.random.RandomDataImpl;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ 
/*     */ public abstract class AbstractRealDistribution implements RealDistribution, Serializable {
/*     */   public static final double SOLVER_DEFAULT_ABSOLUTE_ACCURACY = 1.0E-6D;
/*     */   
/*     */   private static final long serialVersionUID = -38038050983108802L;
/*     */   
/*  47 */   protected final RandomDataImpl randomData = new RandomDataImpl();
/*     */   
/*  50 */   private double solverAbsoluteAccuracy = 1.0E-6D;
/*     */   
/*     */   public double cumulativeProbability(double x0, double x1) throws NumberIsTooLargeException {
/*  62 */     if (x0 > x1)
/*  63 */       throw new NumberIsTooLargeException(LocalizedFormats.LOWER_ENDPOINT_ABOVE_UPPER_ENDPOINT, Double.valueOf(x0), Double.valueOf(x1), true); 
/*  66 */     return cumulativeProbability(x1) - cumulativeProbability(x0);
/*     */   }
/*     */   
/*     */   public double inverseCumulativeProbability(final double p) throws OutOfRangeException {
/* 107 */     if (p < 0.0D || p > 1.0D)
/* 108 */       throw new OutOfRangeException(Double.valueOf(p), Integer.valueOf(0), Integer.valueOf(1)); 
/* 111 */     double lowerBound = getSupportLowerBound();
/* 112 */     if (p == 0.0D)
/* 113 */       return lowerBound; 
/* 116 */     double upperBound = getSupportUpperBound();
/* 117 */     if (p == 1.0D)
/* 118 */       return upperBound; 
/* 121 */     double mu = getNumericalMean();
/* 122 */     double sig = FastMath.sqrt(getNumericalVariance());
/* 124 */     boolean chebyshevApplies = (!Double.isInfinite(mu) && !Double.isNaN(mu) && !Double.isInfinite(sig) && !Double.isNaN(sig));
/* 127 */     if (lowerBound == Double.NEGATIVE_INFINITY)
/* 128 */       if (chebyshevApplies) {
/* 129 */         lowerBound = mu - sig * FastMath.sqrt((1.0D - p) / p);
/*     */       } else {
/* 131 */         lowerBound = -1.0D;
/* 132 */         while (cumulativeProbability(lowerBound) >= p)
/* 133 */           lowerBound *= 2.0D; 
/*     */       }  
/* 138 */     if (upperBound == Double.POSITIVE_INFINITY)
/* 139 */       if (chebyshevApplies) {
/* 140 */         upperBound = mu + sig * FastMath.sqrt(p / (1.0D - p));
/*     */       } else {
/* 142 */         upperBound = 1.0D;
/* 143 */         while (cumulativeProbability(upperBound) < p)
/* 144 */           upperBound *= 2.0D; 
/*     */       }  
/* 149 */     UnivariateFunction toSolve = new UnivariateFunction() {
/*     */         public double value(double x) {
/* 152 */           return AbstractRealDistribution.this.cumulativeProbability(x) - p;
/*     */         }
/*     */       };
/* 156 */     double x = UnivariateSolverUtils.solve(toSolve, lowerBound, upperBound, getSolverAbsoluteAccuracy());
/* 161 */     if (!isSupportConnected()) {
/* 163 */       double dx = getSolverAbsoluteAccuracy();
/* 164 */       if (x - dx >= getSupportLowerBound()) {
/* 165 */         double px = cumulativeProbability(x);
/* 166 */         if (cumulativeProbability(x - dx) == px) {
/* 167 */           upperBound = x;
/* 168 */           while (upperBound - lowerBound > dx) {
/* 169 */             double midPoint = 0.5D * (lowerBound + upperBound);
/* 170 */             if (cumulativeProbability(midPoint) < px) {
/* 171 */               lowerBound = midPoint;
/*     */               continue;
/*     */             } 
/* 173 */             upperBound = midPoint;
/*     */           } 
/* 176 */           return upperBound;
/*     */         } 
/*     */       } 
/*     */     } 
/* 180 */     return x;
/*     */   }
/*     */   
/*     */   protected double getSolverAbsoluteAccuracy() {
/* 191 */     return this.solverAbsoluteAccuracy;
/*     */   }
/*     */   
/*     */   public void reseedRandomGenerator(long seed) {
/* 196 */     this.randomData.reSeed(seed);
/*     */   }
/*     */   
/*     */   public double sample() {
/* 208 */     return this.randomData.nextInversionDeviate(this);
/*     */   }
/*     */   
/*     */   public double[] sample(int sampleSize) {
/* 218 */     if (sampleSize <= 0)
/* 219 */       throw new NotStrictlyPositiveException(LocalizedFormats.NUMBER_OF_SAMPLES, Integer.valueOf(sampleSize)); 
/* 222 */     double[] out = new double[sampleSize];
/* 223 */     for (int i = 0; i < sampleSize; i++)
/* 224 */       out[i] = sample(); 
/* 226 */     return out;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\distribution\AbstractRealDistribution.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */