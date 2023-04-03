/*     */ package org.apache.commons.math3.distribution;
/*     */ 
/*     */ import org.apache.commons.math3.exception.NotStrictlyPositiveException;
/*     */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ 
/*     */ public class ZipfDistribution extends AbstractIntegerDistribution {
/*     */   private static final long serialVersionUID = -140627372283420404L;
/*     */   
/*     */   private final int numberOfElements;
/*     */   
/*     */   private final double exponent;
/*     */   
/*  41 */   private double numericalMean = Double.NaN;
/*     */   
/*     */   private boolean numericalMeanIsCalculated = false;
/*     */   
/*  47 */   private double numericalVariance = Double.NaN;
/*     */   
/*     */   private boolean numericalVarianceIsCalculated = false;
/*     */   
/*     */   public ZipfDistribution(int numberOfElements, double exponent) throws NotStrictlyPositiveException {
/*  63 */     if (numberOfElements <= 0)
/*  64 */       throw new NotStrictlyPositiveException(LocalizedFormats.DIMENSION, Integer.valueOf(numberOfElements)); 
/*  67 */     if (exponent <= 0.0D)
/*  68 */       throw new NotStrictlyPositiveException(LocalizedFormats.EXPONENT, Double.valueOf(exponent)); 
/*  72 */     this.numberOfElements = numberOfElements;
/*  73 */     this.exponent = exponent;
/*     */   }
/*     */   
/*     */   public int getNumberOfElements() {
/*  82 */     return this.numberOfElements;
/*     */   }
/*     */   
/*     */   public double getExponent() {
/*  91 */     return this.exponent;
/*     */   }
/*     */   
/*     */   public double probability(int x) {
/*  96 */     if (x <= 0 || x > this.numberOfElements)
/*  97 */       return 0.0D; 
/* 100 */     return 1.0D / FastMath.pow(x, this.exponent) / generalizedHarmonic(this.numberOfElements, this.exponent);
/*     */   }
/*     */   
/*     */   public double cumulativeProbability(int x) {
/* 105 */     if (x <= 0)
/* 106 */       return 0.0D; 
/* 107 */     if (x >= this.numberOfElements)
/* 108 */       return 1.0D; 
/* 111 */     return generalizedHarmonic(x, this.exponent) / generalizedHarmonic(this.numberOfElements, this.exponent);
/*     */   }
/*     */   
/*     */   public double getNumericalMean() {
/* 125 */     if (!this.numericalMeanIsCalculated) {
/* 126 */       this.numericalMean = calculateNumericalMean();
/* 127 */       this.numericalMeanIsCalculated = true;
/*     */     } 
/* 129 */     return this.numericalMean;
/*     */   }
/*     */   
/*     */   protected double calculateNumericalMean() {
/* 138 */     int N = getNumberOfElements();
/* 139 */     double s = getExponent();
/* 141 */     double Hs1 = generalizedHarmonic(N, s - 1.0D);
/* 142 */     double Hs = generalizedHarmonic(N, s);
/* 144 */     return Hs1 / Hs;
/*     */   }
/*     */   
/*     */   public double getNumericalVariance() {
/* 159 */     if (!this.numericalVarianceIsCalculated) {
/* 160 */       this.numericalVariance = calculateNumericalVariance();
/* 161 */       this.numericalVarianceIsCalculated = true;
/*     */     } 
/* 163 */     return this.numericalVariance;
/*     */   }
/*     */   
/*     */   protected double calculateNumericalVariance() {
/* 172 */     int N = getNumberOfElements();
/* 173 */     double s = getExponent();
/* 175 */     double Hs2 = generalizedHarmonic(N, s - 2.0D);
/* 176 */     double Hs1 = generalizedHarmonic(N, s - 1.0D);
/* 177 */     double Hs = generalizedHarmonic(N, s);
/* 179 */     return Hs2 / Hs - Hs1 * Hs1 / Hs * Hs;
/*     */   }
/*     */   
/*     */   private double generalizedHarmonic(int n, double m) {
/* 192 */     double value = 0.0D;
/* 193 */     for (int k = n; k > 0; k--)
/* 194 */       value += 1.0D / FastMath.pow(k, m); 
/* 196 */     return value;
/*     */   }
/*     */   
/*     */   public int getSupportLowerBound() {
/* 207 */     return 1;
/*     */   }
/*     */   
/*     */   public int getSupportUpperBound() {
/* 218 */     return getNumberOfElements();
/*     */   }
/*     */   
/*     */   public boolean isSupportConnected() {
/* 229 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\distribution\ZipfDistribution.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */