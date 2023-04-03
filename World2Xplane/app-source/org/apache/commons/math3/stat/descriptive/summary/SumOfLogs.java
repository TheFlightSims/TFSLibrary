/*     */ package org.apache.commons.math3.stat.descriptive.summary;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.apache.commons.math3.exception.NullArgumentException;
/*     */ import org.apache.commons.math3.stat.descriptive.AbstractStorelessUnivariateStatistic;
/*     */ import org.apache.commons.math3.stat.descriptive.StorelessUnivariateStatistic;
/*     */ import org.apache.commons.math3.stat.descriptive.UnivariateStatistic;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ import org.apache.commons.math3.util.MathUtils;
/*     */ 
/*     */ public class SumOfLogs extends AbstractStorelessUnivariateStatistic implements Serializable {
/*     */   private static final long serialVersionUID = -370076995648386763L;
/*     */   
/*     */   private int n;
/*     */   
/*     */   private double value;
/*     */   
/*     */   public SumOfLogs() {
/*  65 */     this.value = 0.0D;
/*  66 */     this.n = 0;
/*     */   }
/*     */   
/*     */   public SumOfLogs(SumOfLogs original) {
/*  76 */     copy(original, this);
/*     */   }
/*     */   
/*     */   public void increment(double d) {
/*  84 */     this.value += FastMath.log(d);
/*  85 */     this.n++;
/*     */   }
/*     */   
/*     */   public double getResult() {
/*  93 */     return this.value;
/*     */   }
/*     */   
/*     */   public long getN() {
/* 100 */     return this.n;
/*     */   }
/*     */   
/*     */   public void clear() {
/* 108 */     this.value = 0.0D;
/* 109 */     this.n = 0;
/*     */   }
/*     */   
/*     */   public double evaluate(double[] values, int begin, int length) {
/* 131 */     double sumLog = Double.NaN;
/* 132 */     if (test(values, begin, length, true)) {
/* 133 */       sumLog = 0.0D;
/* 134 */       for (int i = begin; i < begin + length; i++)
/* 135 */         sumLog += FastMath.log(values[i]); 
/*     */     } 
/* 138 */     return sumLog;
/*     */   }
/*     */   
/*     */   public SumOfLogs copy() {
/* 146 */     SumOfLogs result = new SumOfLogs();
/* 147 */     copy(this, result);
/* 148 */     return result;
/*     */   }
/*     */   
/*     */   public static void copy(SumOfLogs source, SumOfLogs dest) throws NullArgumentException {
/* 161 */     MathUtils.checkNotNull(source);
/* 162 */     MathUtils.checkNotNull(dest);
/* 163 */     dest.setData(source.getDataRef());
/* 164 */     dest.n = source.n;
/* 165 */     dest.value = source.value;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\stat\descriptive\summary\SumOfLogs.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */