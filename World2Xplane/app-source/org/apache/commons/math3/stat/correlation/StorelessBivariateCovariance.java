/*     */ package org.apache.commons.math3.stat.correlation;
/*     */ 
/*     */ import org.apache.commons.math3.exception.NumberIsTooSmallException;
/*     */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*     */ 
/*     */ class StorelessBivariateCovariance {
/*     */   private double meanX;
/*     */   
/*     */   private double meanY;
/*     */   
/*     */   private double n;
/*     */   
/*     */   private double covarianceNumerator;
/*     */   
/*     */   private boolean biasCorrected;
/*     */   
/*     */   public StorelessBivariateCovariance() {
/*  61 */     this(true);
/*     */   }
/*     */   
/*     */   public StorelessBivariateCovariance(boolean biasCorrection) {
/*  72 */     this.meanX = this.meanY = 0.0D;
/*  73 */     this.n = 0.0D;
/*  74 */     this.covarianceNumerator = 0.0D;
/*  75 */     this.biasCorrected = biasCorrection;
/*     */   }
/*     */   
/*     */   public void increment(double x, double y) {
/*  85 */     this.n++;
/*  86 */     double deltaX = x - this.meanX;
/*  87 */     double deltaY = y - this.meanY;
/*  88 */     this.meanX += deltaX / this.n;
/*  89 */     this.meanY += deltaY / this.n;
/*  90 */     this.covarianceNumerator += (this.n - 1.0D) / this.n * deltaX * deltaY;
/*     */   }
/*     */   
/*     */   public double getN() {
/*  99 */     return this.n;
/*     */   }
/*     */   
/*     */   public double getResult() throws NumberIsTooSmallException {
/* 110 */     if (this.n < 2.0D)
/* 111 */       throw new NumberIsTooSmallException(LocalizedFormats.INSUFFICIENT_DIMENSION, Double.valueOf(this.n), Integer.valueOf(2), true); 
/* 114 */     if (this.biasCorrected)
/* 115 */       return this.covarianceNumerator / (this.n - 1.0D); 
/* 117 */     return this.covarianceNumerator / this.n;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\stat\correlation\StorelessBivariateCovariance.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */