/*     */ package org.apache.commons.math3.stat.descriptive.summary;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.apache.commons.math3.exception.NullArgumentException;
/*     */ import org.apache.commons.math3.stat.descriptive.AbstractStorelessUnivariateStatistic;
/*     */ import org.apache.commons.math3.stat.descriptive.StorelessUnivariateStatistic;
/*     */ import org.apache.commons.math3.stat.descriptive.UnivariateStatistic;
/*     */ import org.apache.commons.math3.util.MathUtils;
/*     */ 
/*     */ public class Sum extends AbstractStorelessUnivariateStatistic implements Serializable {
/*     */   private static final long serialVersionUID = -8231831954703408316L;
/*     */   
/*     */   private long n;
/*     */   
/*     */   private double value;
/*     */   
/*     */   public Sum() {
/*  57 */     this.n = 0L;
/*  58 */     this.value = 0.0D;
/*     */   }
/*     */   
/*     */   public Sum(Sum original) {
/*  68 */     copy(original, this);
/*     */   }
/*     */   
/*     */   public void increment(double d) {
/*  76 */     this.value += d;
/*  77 */     this.n++;
/*     */   }
/*     */   
/*     */   public double getResult() {
/*  85 */     return this.value;
/*     */   }
/*     */   
/*     */   public long getN() {
/*  92 */     return this.n;
/*     */   }
/*     */   
/*     */   public void clear() {
/* 100 */     this.value = 0.0D;
/* 101 */     this.n = 0L;
/*     */   }
/*     */   
/*     */   public double evaluate(double[] values, int begin, int length) {
/* 120 */     double sum = Double.NaN;
/* 121 */     if (test(values, begin, length, true)) {
/* 122 */       sum = 0.0D;
/* 123 */       for (int i = begin; i < begin + length; i++)
/* 124 */         sum += values[i]; 
/*     */     } 
/* 127 */     return sum;
/*     */   }
/*     */   
/*     */   public double evaluate(double[] values, double[] weights, int begin, int length) {
/* 159 */     double sum = Double.NaN;
/* 160 */     if (test(values, weights, begin, length, true)) {
/* 161 */       sum = 0.0D;
/* 162 */       for (int i = begin; i < begin + length; i++)
/* 163 */         sum += values[i] * weights[i]; 
/*     */     } 
/* 166 */     return sum;
/*     */   }
/*     */   
/*     */   public double evaluate(double[] values, double[] weights) {
/* 192 */     return evaluate(values, weights, 0, values.length);
/*     */   }
/*     */   
/*     */   public Sum copy() {
/* 200 */     Sum result = new Sum();
/* 201 */     copy(this, result);
/* 202 */     return result;
/*     */   }
/*     */   
/*     */   public static void copy(Sum source, Sum dest) throws NullArgumentException {
/* 215 */     MathUtils.checkNotNull(source);
/* 216 */     MathUtils.checkNotNull(dest);
/* 217 */     dest.setData(source.getDataRef());
/* 218 */     dest.n = source.n;
/* 219 */     dest.value = source.value;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\stat\descriptive\summary\Sum.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */