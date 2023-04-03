/*     */ package org.apache.commons.math3.distribution;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.apache.commons.math3.Field;
/*     */ import org.apache.commons.math3.FieldElement;
/*     */ import org.apache.commons.math3.exception.MathArithmeticException;
/*     */ import org.apache.commons.math3.exception.NotStrictlyPositiveException;
/*     */ import org.apache.commons.math3.exception.NumberIsTooLargeException;
/*     */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*     */ import org.apache.commons.math3.fraction.BigFraction;
/*     */ import org.apache.commons.math3.fraction.BigFractionField;
/*     */ import org.apache.commons.math3.fraction.FractionConversionException;
/*     */ import org.apache.commons.math3.linear.Array2DRowFieldMatrix;
/*     */ import org.apache.commons.math3.linear.Array2DRowRealMatrix;
/*     */ import org.apache.commons.math3.linear.FieldMatrix;
/*     */ import org.apache.commons.math3.linear.RealMatrix;
/*     */ 
/*     */ public class KolmogorovSmirnovDistribution implements Serializable {
/*     */   private static final long serialVersionUID = -4670676796862967187L;
/*     */   
/*     */   private int n;
/*     */   
/*     */   public KolmogorovSmirnovDistribution(int n) throws NotStrictlyPositiveException {
/*  86 */     if (n <= 0)
/*  87 */       throw new NotStrictlyPositiveException(LocalizedFormats.NOT_POSITIVE_NUMBER_OF_SAMPLES, Integer.valueOf(n)); 
/*  90 */     this.n = n;
/*     */   }
/*     */   
/*     */   public double cdf(double d) throws MathArithmeticException {
/* 109 */     return cdf(d, false);
/*     */   }
/*     */   
/*     */   public double cdfExact(double d) throws MathArithmeticException {
/* 129 */     return cdf(d, true);
/*     */   }
/*     */   
/*     */   public double cdf(double d, boolean exact) throws MathArithmeticException {
/* 151 */     double ninv = 1.0D / this.n;
/* 152 */     double ninvhalf = 0.5D * ninv;
/* 154 */     if (d <= ninvhalf)
/* 156 */       return 0.0D; 
/* 158 */     if (ninvhalf < d && d <= ninv) {
/* 160 */       double res = 1.0D;
/* 161 */       double f = 2.0D * d - ninv;
/* 164 */       for (int i = 1; i <= this.n; i++)
/* 165 */         res *= i * f; 
/* 168 */       return res;
/*     */     } 
/* 170 */     if (1.0D - ninv <= d && d < 1.0D)
/* 172 */       return 1.0D - 2.0D * Math.pow(1.0D - d, this.n); 
/* 174 */     if (1.0D <= d)
/* 176 */       return 1.0D; 
/* 179 */     return exact ? exactK(d) : roundedK(d);
/*     */   }
/*     */   
/*     */   private double exactK(double d) throws MathArithmeticException {
/* 196 */     int k = (int)Math.ceil(this.n * d);
/* 198 */     FieldMatrix<BigFraction> H = createH(d);
/* 199 */     FieldMatrix<BigFraction> Hpower = H.power(this.n);
/* 201 */     BigFraction pFrac = (BigFraction)Hpower.getEntry(k - 1, k - 1);
/* 203 */     for (int i = 1; i <= this.n; i++)
/* 204 */       pFrac = pFrac.multiply(i).divide(this.n); 
/* 212 */     return pFrac.bigDecimalValue(20, 4).doubleValue();
/*     */   }
/*     */   
/*     */   private double roundedK(double d) throws MathArithmeticException {
/* 228 */     int k = (int)Math.ceil(this.n * d);
/* 229 */     FieldMatrix<BigFraction> HBigFraction = createH(d);
/* 230 */     int m = HBigFraction.getRowDimension();
/* 236 */     Array2DRowRealMatrix array2DRowRealMatrix = new Array2DRowRealMatrix(m, m);
/* 238 */     for (int i = 0; i < m; i++) {
/* 239 */       for (int n = 0; n < m; n++)
/* 240 */         array2DRowRealMatrix.setEntry(i, n, ((BigFraction)HBigFraction.getEntry(i, n)).doubleValue()); 
/*     */     } 
/* 244 */     RealMatrix Hpower = array2DRowRealMatrix.power(this.n);
/* 246 */     double pFrac = Hpower.getEntry(k - 1, k - 1);
/* 248 */     for (int j = 1; j <= this.n; j++)
/* 249 */       pFrac *= j / this.n; 
/* 252 */     return pFrac;
/*     */   }
/*     */   
/*     */   private FieldMatrix<BigFraction> createH(double d) throws NumberIsTooLargeException, FractionConversionException {
/* 269 */     int k = (int)Math.ceil(this.n * d);
/* 271 */     int m = 2 * k - 1;
/* 272 */     double hDouble = k - this.n * d;
/* 274 */     if (hDouble >= 1.0D)
/* 275 */       throw new NumberIsTooLargeException(Double.valueOf(hDouble), Double.valueOf(1.0D), false); 
/* 278 */     BigFraction h = null;
/*     */     try {
/* 281 */       h = new BigFraction(hDouble, 1.0E-20D, 10000);
/* 282 */     } catch (FractionConversionException e1) {
/*     */       try {
/* 284 */         h = new BigFraction(hDouble, 1.0E-10D, 10000);
/* 285 */       } catch (FractionConversionException e2) {
/* 286 */         h = new BigFraction(hDouble, 1.0E-5D, 10000);
/*     */       } 
/*     */     } 
/* 290 */     BigFraction[][] Hdata = new BigFraction[m][m];
/* 295 */     for (int i = 0; i < m; i++) {
/* 296 */       for (int n = 0; n < m; n++) {
/* 297 */         if (i - n + 1 < 0) {
/* 298 */           Hdata[i][n] = BigFraction.ZERO;
/*     */         } else {
/* 300 */           Hdata[i][n] = BigFraction.ONE;
/*     */         } 
/*     */       } 
/*     */     } 
/* 309 */     BigFraction[] hPowers = new BigFraction[m];
/* 310 */     hPowers[0] = h;
/*     */     int j;
/* 311 */     for (j = 1; j < m; j++)
/* 312 */       hPowers[j] = h.multiply(hPowers[j - 1]); 
/* 318 */     for (j = 0; j < m; j++) {
/* 319 */       Hdata[j][0] = Hdata[j][0].subtract(hPowers[j]);
/* 320 */       Hdata[m - 1][j] = Hdata[m - 1][j].subtract(hPowers[m - j - 1]);
/*     */     } 
/* 328 */     if (h.compareTo(BigFraction.ONE_HALF) == 1)
/* 329 */       Hdata[m - 1][0] = Hdata[m - 1][0].add(h.multiply(2).subtract(1).pow(m)); 
/* 343 */     for (j = 0; j < m; j++) {
/* 344 */       for (int n = 0; n < j + 1; n++) {
/* 345 */         if (j - n + 1 > 0)
/* 346 */           for (int g = 2; g <= j - n + 1; g++)
/* 347 */             Hdata[j][n] = Hdata[j][n].divide(g);  
/*     */       } 
/*     */     } 
/* 353 */     return (FieldMatrix<BigFraction>)new Array2DRowFieldMatrix((Field)BigFractionField.getInstance(), (FieldElement[][])Hdata);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\distribution\KolmogorovSmirnovDistribution.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */