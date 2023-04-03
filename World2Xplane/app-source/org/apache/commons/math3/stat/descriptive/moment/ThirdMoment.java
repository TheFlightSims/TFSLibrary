/*     */ package org.apache.commons.math3.stat.descriptive.moment;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.apache.commons.math3.exception.NullArgumentException;
/*     */ import org.apache.commons.math3.stat.descriptive.StorelessUnivariateStatistic;
/*     */ import org.apache.commons.math3.stat.descriptive.UnivariateStatistic;
/*     */ import org.apache.commons.math3.util.MathUtils;
/*     */ 
/*     */ class ThirdMoment extends SecondMoment implements Serializable {
/*     */   private static final long serialVersionUID = -7818711964045118679L;
/*     */   
/*     */   protected double m3;
/*     */   
/*     */   protected double nDevSq;
/*     */   
/*     */   public ThirdMoment() {
/*  70 */     this.m3 = Double.NaN;
/*  71 */     this.nDevSq = Double.NaN;
/*     */   }
/*     */   
/*     */   public ThirdMoment(ThirdMoment original) {
/*  81 */     copy(original, this);
/*     */   }
/*     */   
/*     */   public void increment(double d) {
/*  89 */     if (this.n < 1L)
/*  90 */       this.m3 = this.m2 = this.m1 = 0.0D; 
/*  93 */     double prevM2 = this.m2;
/*  94 */     super.increment(d);
/*  95 */     this.nDevSq = this.nDev * this.nDev;
/*  96 */     double n0 = this.n;
/*  97 */     this.m3 = this.m3 - 3.0D * this.nDev * prevM2 + (n0 - 1.0D) * (n0 - 2.0D) * this.nDevSq * this.dev;
/*     */   }
/*     */   
/*     */   public double getResult() {
/* 105 */     return this.m3;
/*     */   }
/*     */   
/*     */   public void clear() {
/* 113 */     super.clear();
/* 114 */     this.m3 = Double.NaN;
/* 115 */     this.nDevSq = Double.NaN;
/*     */   }
/*     */   
/*     */   public ThirdMoment copy() {
/* 123 */     ThirdMoment result = new ThirdMoment();
/* 124 */     copy(this, result);
/* 125 */     return result;
/*     */   }
/*     */   
/*     */   public static void copy(ThirdMoment source, ThirdMoment dest) throws NullArgumentException {
/* 138 */     MathUtils.checkNotNull(source);
/* 139 */     MathUtils.checkNotNull(dest);
/* 140 */     SecondMoment.copy(source, dest);
/* 141 */     dest.m3 = source.m3;
/* 142 */     dest.nDevSq = source.nDevSq;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\stat\descriptive\moment\ThirdMoment.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */