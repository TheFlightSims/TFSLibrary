/*     */ package org.apache.commons.math3.stat.descriptive.summary;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.apache.commons.math3.exception.NullArgumentException;
/*     */ import org.apache.commons.math3.stat.descriptive.AbstractStorelessUnivariateStatistic;
/*     */ import org.apache.commons.math3.stat.descriptive.StorelessUnivariateStatistic;
/*     */ import org.apache.commons.math3.stat.descriptive.UnivariateStatistic;
/*     */ import org.apache.commons.math3.util.MathUtils;
/*     */ 
/*     */ public class SumOfSquares extends AbstractStorelessUnivariateStatistic implements Serializable {
/*     */   private static final long serialVersionUID = 1460986908574398008L;
/*     */   
/*     */   private long n;
/*     */   
/*     */   private double value;
/*     */   
/*     */   public SumOfSquares() {
/*  56 */     this.n = 0L;
/*  57 */     this.value = 0.0D;
/*     */   }
/*     */   
/*     */   public SumOfSquares(SumOfSquares original) {
/*  67 */     copy(original, this);
/*     */   }
/*     */   
/*     */   public void increment(double d) {
/*  75 */     this.value += d * d;
/*  76 */     this.n++;
/*     */   }
/*     */   
/*     */   public double getResult() {
/*  84 */     return this.value;
/*     */   }
/*     */   
/*     */   public long getN() {
/*  91 */     return this.n;
/*     */   }
/*     */   
/*     */   public void clear() {
/*  99 */     this.value = 0.0D;
/* 100 */     this.n = 0L;
/*     */   }
/*     */   
/*     */   public double evaluate(double[] values, int begin, int length) {
/* 119 */     double sumSq = Double.NaN;
/* 120 */     if (test(values, begin, length, true)) {
/* 121 */       sumSq = 0.0D;
/* 122 */       for (int i = begin; i < begin + length; i++)
/* 123 */         sumSq += values[i] * values[i]; 
/*     */     } 
/* 126 */     return sumSq;
/*     */   }
/*     */   
/*     */   public SumOfSquares copy() {
/* 134 */     SumOfSquares result = new SumOfSquares();
/* 135 */     copy(this, result);
/* 136 */     return result;
/*     */   }
/*     */   
/*     */   public static void copy(SumOfSquares source, SumOfSquares dest) throws NullArgumentException {
/* 149 */     MathUtils.checkNotNull(source);
/* 150 */     MathUtils.checkNotNull(dest);
/* 151 */     dest.setData(source.getDataRef());
/* 152 */     dest.n = source.n;
/* 153 */     dest.value = source.value;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\stat\descriptive\summary\SumOfSquares.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */