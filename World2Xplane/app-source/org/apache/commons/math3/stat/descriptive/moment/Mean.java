/*     */ package org.apache.commons.math3.stat.descriptive.moment;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.apache.commons.math3.exception.NullArgumentException;
/*     */ import org.apache.commons.math3.stat.descriptive.AbstractStorelessUnivariateStatistic;
/*     */ import org.apache.commons.math3.stat.descriptive.StorelessUnivariateStatistic;
/*     */ import org.apache.commons.math3.stat.descriptive.UnivariateStatistic;
/*     */ import org.apache.commons.math3.stat.descriptive.WeightedEvaluation;
/*     */ import org.apache.commons.math3.stat.descriptive.summary.Sum;
/*     */ import org.apache.commons.math3.util.MathUtils;
/*     */ 
/*     */ public class Mean extends AbstractStorelessUnivariateStatistic implements Serializable, WeightedEvaluation {
/*     */   private static final long serialVersionUID = -1296043746617791564L;
/*     */   
/*     */   protected FirstMoment moment;
/*     */   
/*     */   protected boolean incMoment;
/*     */   
/*     */   public Mean() {
/*  80 */     this.incMoment = true;
/*  81 */     this.moment = new FirstMoment();
/*     */   }
/*     */   
/*     */   public Mean(FirstMoment m1) {
/*  90 */     this.moment = m1;
/*  91 */     this.incMoment = false;
/*     */   }
/*     */   
/*     */   public Mean(Mean original) {
/* 101 */     copy(original, this);
/*     */   }
/*     */   
/*     */   public void increment(double d) {
/* 109 */     if (this.incMoment)
/* 110 */       this.moment.increment(d); 
/*     */   }
/*     */   
/*     */   public void clear() {
/* 119 */     if (this.incMoment)
/* 120 */       this.moment.clear(); 
/*     */   }
/*     */   
/*     */   public double getResult() {
/* 129 */     return this.moment.m1;
/*     */   }
/*     */   
/*     */   public long getN() {
/* 136 */     return this.moment.getN();
/*     */   }
/*     */   
/*     */   public double evaluate(double[] values, int begin, int length) {
/* 157 */     if (test(values, begin, length)) {
/* 158 */       Sum sum = new Sum();
/* 159 */       double sampleSize = length;
/* 162 */       double xbar = sum.evaluate(values, begin, length) / sampleSize;
/* 165 */       double correction = 0.0D;
/* 166 */       for (int i = begin; i < begin + length; i++)
/* 167 */         correction += values[i] - xbar; 
/* 169 */       return xbar + correction / sampleSize;
/*     */     } 
/* 171 */     return Double.NaN;
/*     */   }
/*     */   
/*     */   public double evaluate(double[] values, double[] weights, int begin, int length) {
/* 205 */     if (test(values, weights, begin, length)) {
/* 206 */       Sum sum = new Sum();
/* 209 */       double sumw = sum.evaluate(weights, begin, length);
/* 210 */       double xbarw = sum.evaluate(values, weights, begin, length) / sumw;
/* 213 */       double correction = 0.0D;
/* 214 */       for (int i = begin; i < begin + length; i++)
/* 215 */         correction += weights[i] * (values[i] - xbarw); 
/* 217 */       return xbarw + correction / sumw;
/*     */     } 
/* 219 */     return Double.NaN;
/*     */   }
/*     */   
/*     */   public double evaluate(double[] values, double[] weights) {
/* 247 */     return evaluate(values, weights, 0, values.length);
/*     */   }
/*     */   
/*     */   public Mean copy() {
/* 255 */     Mean result = new Mean();
/* 256 */     copy(this, result);
/* 257 */     return result;
/*     */   }
/*     */   
/*     */   public static void copy(Mean source, Mean dest) throws NullArgumentException {
/* 271 */     MathUtils.checkNotNull(source);
/* 272 */     MathUtils.checkNotNull(dest);
/* 273 */     dest.setData(source.getDataRef());
/* 274 */     dest.incMoment = source.incMoment;
/* 275 */     dest.moment = source.moment.copy();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\stat\descriptive\moment\Mean.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */