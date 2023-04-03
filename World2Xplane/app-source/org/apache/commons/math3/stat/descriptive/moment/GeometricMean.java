/*     */ package org.apache.commons.math3.stat.descriptive.moment;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.apache.commons.math3.exception.MathIllegalStateException;
/*     */ import org.apache.commons.math3.exception.NullArgumentException;
/*     */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*     */ import org.apache.commons.math3.stat.descriptive.AbstractStorelessUnivariateStatistic;
/*     */ import org.apache.commons.math3.stat.descriptive.StorelessUnivariateStatistic;
/*     */ import org.apache.commons.math3.stat.descriptive.UnivariateStatistic;
/*     */ import org.apache.commons.math3.stat.descriptive.summary.SumOfLogs;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ import org.apache.commons.math3.util.MathUtils;
/*     */ 
/*     */ public class GeometricMean extends AbstractStorelessUnivariateStatistic implements Serializable {
/*     */   private static final long serialVersionUID = -8178734905303459453L;
/*     */   
/*     */   private StorelessUnivariateStatistic sumOfLogs;
/*     */   
/*     */   public GeometricMean() {
/*  66 */     this.sumOfLogs = (StorelessUnivariateStatistic)new SumOfLogs();
/*     */   }
/*     */   
/*     */   public GeometricMean(GeometricMean original) {
/*  77 */     copy(original, this);
/*     */   }
/*     */   
/*     */   public GeometricMean(SumOfLogs sumOfLogs) {
/*  85 */     this.sumOfLogs = (StorelessUnivariateStatistic)sumOfLogs;
/*     */   }
/*     */   
/*     */   public GeometricMean copy() {
/*  93 */     GeometricMean result = new GeometricMean();
/*  94 */     copy(this, result);
/*  95 */     return result;
/*     */   }
/*     */   
/*     */   public void increment(double d) {
/* 103 */     this.sumOfLogs.increment(d);
/*     */   }
/*     */   
/*     */   public double getResult() {
/* 111 */     if (this.sumOfLogs.getN() > 0L)
/* 112 */       return FastMath.exp(this.sumOfLogs.getResult() / this.sumOfLogs.getN()); 
/* 114 */     return Double.NaN;
/*     */   }
/*     */   
/*     */   public void clear() {
/* 123 */     this.sumOfLogs.clear();
/*     */   }
/*     */   
/*     */   public double evaluate(double[] values, int begin, int length) {
/* 145 */     return FastMath.exp(this.sumOfLogs.evaluate(values, begin, length) / length);
/*     */   }
/*     */   
/*     */   public long getN() {
/* 153 */     return this.sumOfLogs.getN();
/*     */   }
/*     */   
/*     */   public void setSumLogImpl(StorelessUnivariateStatistic sumLogImpl) {
/* 169 */     checkEmpty();
/* 170 */     this.sumOfLogs = sumLogImpl;
/*     */   }
/*     */   
/*     */   public StorelessUnivariateStatistic getSumLogImpl() {
/* 179 */     return this.sumOfLogs;
/*     */   }
/*     */   
/*     */   public static void copy(GeometricMean source, GeometricMean dest) throws NullArgumentException {
/* 192 */     MathUtils.checkNotNull(source);
/* 193 */     MathUtils.checkNotNull(dest);
/* 194 */     dest.setData(source.getDataRef());
/* 195 */     dest.sumOfLogs = source.sumOfLogs.copy();
/*     */   }
/*     */   
/*     */   private void checkEmpty() {
/* 203 */     if (getN() > 0L)
/* 204 */       throw new MathIllegalStateException(LocalizedFormats.VALUES_ADDED_BEFORE_CONFIGURING_STATISTIC, new Object[] { Long.valueOf(getN()) }); 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\stat\descriptive\moment\GeometricMean.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */