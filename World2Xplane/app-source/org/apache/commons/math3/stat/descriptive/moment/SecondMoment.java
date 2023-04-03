/*     */ package org.apache.commons.math3.stat.descriptive.moment;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.apache.commons.math3.exception.NullArgumentException;
/*     */ import org.apache.commons.math3.stat.descriptive.StorelessUnivariateStatistic;
/*     */ import org.apache.commons.math3.stat.descriptive.UnivariateStatistic;
/*     */ import org.apache.commons.math3.util.MathUtils;
/*     */ 
/*     */ public class SecondMoment extends FirstMoment implements Serializable {
/*     */   private static final long serialVersionUID = 3942403127395076445L;
/*     */   
/*     */   protected double m2;
/*     */   
/*     */   public SecondMoment() {
/*  61 */     this.m2 = Double.NaN;
/*     */   }
/*     */   
/*     */   public SecondMoment(SecondMoment original) {
/*  71 */     super(original);
/*  72 */     this.m2 = original.m2;
/*     */   }
/*     */   
/*     */   public void increment(double d) {
/*  80 */     if (this.n < 1L)
/*  81 */       this.m1 = this.m2 = 0.0D; 
/*  83 */     super.increment(d);
/*  84 */     this.m2 += (this.n - 1.0D) * this.dev * this.nDev;
/*     */   }
/*     */   
/*     */   public void clear() {
/*  92 */     super.clear();
/*  93 */     this.m2 = Double.NaN;
/*     */   }
/*     */   
/*     */   public double getResult() {
/* 101 */     return this.m2;
/*     */   }
/*     */   
/*     */   public SecondMoment copy() {
/* 109 */     SecondMoment result = new SecondMoment();
/* 110 */     copy(this, result);
/* 111 */     return result;
/*     */   }
/*     */   
/*     */   public static void copy(SecondMoment source, SecondMoment dest) throws NullArgumentException {
/* 124 */     MathUtils.checkNotNull(source);
/* 125 */     MathUtils.checkNotNull(dest);
/* 126 */     FirstMoment.copy(source, dest);
/* 127 */     dest.m2 = source.m2;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\stat\descriptive\moment\SecondMoment.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */