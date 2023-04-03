/*     */ package org.apache.commons.math3.stat.descriptive.rank;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.apache.commons.math3.exception.NullArgumentException;
/*     */ import org.apache.commons.math3.stat.descriptive.AbstractStorelessUnivariateStatistic;
/*     */ import org.apache.commons.math3.stat.descriptive.StorelessUnivariateStatistic;
/*     */ import org.apache.commons.math3.stat.descriptive.UnivariateStatistic;
/*     */ import org.apache.commons.math3.util.MathUtils;
/*     */ 
/*     */ public class Max extends AbstractStorelessUnivariateStatistic implements Serializable {
/*     */   private static final long serialVersionUID = -5593383832225844641L;
/*     */   
/*     */   private long n;
/*     */   
/*     */   private double value;
/*     */   
/*     */   public Max() {
/*  57 */     this.n = 0L;
/*  58 */     this.value = Double.NaN;
/*     */   }
/*     */   
/*     */   public Max(Max original) {
/*  68 */     copy(original, this);
/*     */   }
/*     */   
/*     */   public void increment(double d) {
/*  76 */     if (d > this.value || Double.isNaN(this.value))
/*  77 */       this.value = d; 
/*  79 */     this.n++;
/*     */   }
/*     */   
/*     */   public void clear() {
/*  87 */     this.value = Double.NaN;
/*  88 */     this.n = 0L;
/*     */   }
/*     */   
/*     */   public double getResult() {
/*  96 */     return this.value;
/*     */   }
/*     */   
/*     */   public long getN() {
/* 103 */     return this.n;
/*     */   }
/*     */   
/*     */   public double evaluate(double[] values, int begin, int length) {
/* 130 */     double max = Double.NaN;
/* 131 */     if (test(values, begin, length)) {
/* 132 */       max = values[begin];
/* 133 */       for (int i = begin; i < begin + length; i++) {
/* 134 */         if (!Double.isNaN(values[i]))
/* 135 */           max = (max > values[i]) ? max : values[i]; 
/*     */       } 
/*     */     } 
/* 139 */     return max;
/*     */   }
/*     */   
/*     */   public Max copy() {
/* 147 */     Max result = new Max();
/* 148 */     copy(this, result);
/* 149 */     return result;
/*     */   }
/*     */   
/*     */   public static void copy(Max source, Max dest) throws NullArgumentException {
/* 162 */     MathUtils.checkNotNull(source);
/* 163 */     MathUtils.checkNotNull(dest);
/* 164 */     dest.setData(source.getDataRef());
/* 165 */     dest.n = source.n;
/* 166 */     dest.value = source.value;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\stat\descriptive\rank\Max.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */