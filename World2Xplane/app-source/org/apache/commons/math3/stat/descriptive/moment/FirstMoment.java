/*     */ package org.apache.commons.math3.stat.descriptive.moment;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.apache.commons.math3.exception.NullArgumentException;
/*     */ import org.apache.commons.math3.stat.descriptive.AbstractStorelessUnivariateStatistic;
/*     */ import org.apache.commons.math3.stat.descriptive.StorelessUnivariateStatistic;
/*     */ import org.apache.commons.math3.stat.descriptive.UnivariateStatistic;
/*     */ import org.apache.commons.math3.util.MathUtils;
/*     */ 
/*     */ class FirstMoment extends AbstractStorelessUnivariateStatistic implements Serializable {
/*     */   private static final long serialVersionUID = 6112755307178490473L;
/*     */   
/*     */   protected long n;
/*     */   
/*     */   protected double m1;
/*     */   
/*     */   protected double dev;
/*     */   
/*     */   protected double nDev;
/*     */   
/*     */   public FirstMoment() {
/*  80 */     this.n = 0L;
/*  81 */     this.m1 = Double.NaN;
/*  82 */     this.dev = Double.NaN;
/*  83 */     this.nDev = Double.NaN;
/*     */   }
/*     */   
/*     */   public FirstMoment(FirstMoment original) {
/*  94 */     copy(original, this);
/*     */   }
/*     */   
/*     */   public void increment(double d) {
/* 102 */     if (this.n == 0L)
/* 103 */       this.m1 = 0.0D; 
/* 105 */     this.n++;
/* 106 */     double n0 = this.n;
/* 107 */     this.dev = d - this.m1;
/* 108 */     this.nDev = this.dev / n0;
/* 109 */     this.m1 += this.nDev;
/*     */   }
/*     */   
/*     */   public void clear() {
/* 117 */     this.m1 = Double.NaN;
/* 118 */     this.n = 0L;
/* 119 */     this.dev = Double.NaN;
/* 120 */     this.nDev = Double.NaN;
/*     */   }
/*     */   
/*     */   public double getResult() {
/* 128 */     return this.m1;
/*     */   }
/*     */   
/*     */   public long getN() {
/* 135 */     return this.n;
/*     */   }
/*     */   
/*     */   public FirstMoment copy() {
/* 143 */     FirstMoment result = new FirstMoment();
/* 144 */     copy(this, result);
/* 145 */     return result;
/*     */   }
/*     */   
/*     */   public static void copy(FirstMoment source, FirstMoment dest) throws NullArgumentException {
/* 158 */     MathUtils.checkNotNull(source);
/* 159 */     MathUtils.checkNotNull(dest);
/* 160 */     dest.setData(source.getDataRef());
/* 161 */     dest.n = source.n;
/* 162 */     dest.m1 = source.m1;
/* 163 */     dest.dev = source.dev;
/* 164 */     dest.nDev = source.nDev;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\stat\descriptive\moment\FirstMoment.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */