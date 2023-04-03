/*     */ package org.apache.commons.math3.stat.descriptive.moment;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.apache.commons.math3.exception.NullArgumentException;
/*     */ import org.apache.commons.math3.stat.descriptive.AbstractStorelessUnivariateStatistic;
/*     */ import org.apache.commons.math3.stat.descriptive.StorelessUnivariateStatistic;
/*     */ import org.apache.commons.math3.stat.descriptive.UnivariateStatistic;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ import org.apache.commons.math3.util.MathUtils;
/*     */ 
/*     */ public class StandardDeviation extends AbstractStorelessUnivariateStatistic implements Serializable {
/*     */   private static final long serialVersionUID = 5728716329662425188L;
/*     */   
/*  50 */   private Variance variance = null;
/*     */   
/*     */   public StandardDeviation() {
/*  57 */     this.variance = new Variance();
/*     */   }
/*     */   
/*     */   public StandardDeviation(SecondMoment m2) {
/*  66 */     this.variance = new Variance(m2);
/*     */   }
/*     */   
/*     */   public StandardDeviation(StandardDeviation original) {
/*  76 */     copy(original, this);
/*     */   }
/*     */   
/*     */   public StandardDeviation(boolean isBiasCorrected) {
/*  90 */     this.variance = new Variance(isBiasCorrected);
/*     */   }
/*     */   
/*     */   public StandardDeviation(boolean isBiasCorrected, SecondMoment m2) {
/* 105 */     this.variance = new Variance(isBiasCorrected, m2);
/*     */   }
/*     */   
/*     */   public void increment(double d) {
/* 113 */     this.variance.increment(d);
/*     */   }
/*     */   
/*     */   public long getN() {
/* 120 */     return this.variance.getN();
/*     */   }
/*     */   
/*     */   public double getResult() {
/* 128 */     return FastMath.sqrt(this.variance.getResult());
/*     */   }
/*     */   
/*     */   public void clear() {
/* 136 */     this.variance.clear();
/*     */   }
/*     */   
/*     */   public double evaluate(double[] values) {
/* 155 */     return FastMath.sqrt(this.variance.evaluate(values));
/*     */   }
/*     */   
/*     */   public double evaluate(double[] values, int begin, int length) {
/* 178 */     return FastMath.sqrt(this.variance.evaluate(values, begin, length));
/*     */   }
/*     */   
/*     */   public double evaluate(double[] values, double mean, int begin, int length) {
/* 207 */     return FastMath.sqrt(this.variance.evaluate(values, mean, begin, length));
/*     */   }
/*     */   
/*     */   public double evaluate(double[] values, double mean) {
/* 232 */     return FastMath.sqrt(this.variance.evaluate(values, mean));
/*     */   }
/*     */   
/*     */   public boolean isBiasCorrected() {
/* 239 */     return this.variance.isBiasCorrected();
/*     */   }
/*     */   
/*     */   public void setBiasCorrected(boolean isBiasCorrected) {
/* 246 */     this.variance.setBiasCorrected(isBiasCorrected);
/*     */   }
/*     */   
/*     */   public StandardDeviation copy() {
/* 254 */     StandardDeviation result = new StandardDeviation();
/* 255 */     copy(this, result);
/* 256 */     return result;
/*     */   }
/*     */   
/*     */   public static void copy(StandardDeviation source, StandardDeviation dest) throws NullArgumentException {
/* 270 */     MathUtils.checkNotNull(source);
/* 271 */     MathUtils.checkNotNull(dest);
/* 272 */     dest.setData(source.getDataRef());
/* 273 */     dest.variance = source.variance.copy();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\stat\descriptive\moment\StandardDeviation.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */