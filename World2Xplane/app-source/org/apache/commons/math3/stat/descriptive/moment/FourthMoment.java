/*     */ package org.apache.commons.math3.stat.descriptive.moment;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.apache.commons.math3.exception.NullArgumentException;
/*     */ import org.apache.commons.math3.stat.descriptive.StorelessUnivariateStatistic;
/*     */ import org.apache.commons.math3.stat.descriptive.UnivariateStatistic;
/*     */ import org.apache.commons.math3.util.MathUtils;
/*     */ 
/*     */ class FourthMoment extends ThirdMoment implements Serializable {
/*     */   private static final long serialVersionUID = 4763990447117157611L;
/*     */   
/*     */   private double m4;
/*     */   
/*     */   public FourthMoment() {
/*  69 */     this.m4 = Double.NaN;
/*     */   }
/*     */   
/*     */   public FourthMoment(FourthMoment original) {
/*  80 */     copy(original, this);
/*     */   }
/*     */   
/*     */   public void increment(double d) {
/*  88 */     if (this.n < 1L) {
/*  89 */       this.m4 = 0.0D;
/*  90 */       this.m3 = 0.0D;
/*  91 */       this.m2 = 0.0D;
/*  92 */       this.m1 = 0.0D;
/*     */     } 
/*  95 */     double prevM3 = this.m3;
/*  96 */     double prevM2 = this.m2;
/*  98 */     super.increment(d);
/* 100 */     double n0 = this.n;
/* 102 */     this.m4 = this.m4 - 4.0D * this.nDev * prevM3 + 6.0D * this.nDevSq * prevM2 + (n0 * n0 - 3.0D * (n0 - 1.0D)) * this.nDevSq * this.nDevSq * (n0 - 1.0D) * n0;
/*     */   }
/*     */   
/*     */   public double getResult() {
/* 111 */     return this.m4;
/*     */   }
/*     */   
/*     */   public void clear() {
/* 119 */     super.clear();
/* 120 */     this.m4 = Double.NaN;
/*     */   }
/*     */   
/*     */   public FourthMoment copy() {
/* 128 */     FourthMoment result = new FourthMoment();
/* 129 */     copy(this, result);
/* 130 */     return result;
/*     */   }
/*     */   
/*     */   public static void copy(FourthMoment source, FourthMoment dest) throws NullArgumentException {
/* 143 */     MathUtils.checkNotNull(source);
/* 144 */     MathUtils.checkNotNull(dest);
/* 145 */     ThirdMoment.copy(source, dest);
/* 146 */     dest.m4 = source.m4;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\stat\descriptive\moment\FourthMoment.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */