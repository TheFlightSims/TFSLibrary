/*     */ package org.apache.commons.math3.stat.descriptive.moment;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.apache.commons.math3.exception.MathIllegalStateException;
/*     */ import org.apache.commons.math3.exception.NullArgumentException;
/*     */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*     */ import org.apache.commons.math3.stat.descriptive.AbstractStorelessUnivariateStatistic;
/*     */ import org.apache.commons.math3.stat.descriptive.StorelessUnivariateStatistic;
/*     */ import org.apache.commons.math3.stat.descriptive.UnivariateStatistic;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ import org.apache.commons.math3.util.MathUtils;
/*     */ 
/*     */ public class Kurtosis extends AbstractStorelessUnivariateStatistic implements Serializable {
/*     */   private static final long serialVersionUID = 2784465764798260919L;
/*     */   
/*     */   protected FourthMoment moment;
/*     */   
/*     */   protected boolean incMoment;
/*     */   
/*     */   public Kurtosis() {
/*  69 */     this.incMoment = true;
/*  70 */     this.moment = new FourthMoment();
/*     */   }
/*     */   
/*     */   public Kurtosis(FourthMoment m4) {
/*  79 */     this.incMoment = false;
/*  80 */     this.moment = m4;
/*     */   }
/*     */   
/*     */   public Kurtosis(Kurtosis original) {
/*  90 */     copy(original, this);
/*     */   }
/*     */   
/*     */   public void increment(double d) {
/*  98 */     if (this.incMoment) {
/*  99 */       this.moment.increment(d);
/*     */     } else {
/* 101 */       throw new MathIllegalStateException(LocalizedFormats.CANNOT_INCREMENT_STATISTIC_CONSTRUCTED_FROM_EXTERNAL_MOMENTS, new Object[0]);
/*     */     } 
/*     */   }
/*     */   
/*     */   public double getResult() {
/* 110 */     double kurtosis = Double.NaN;
/* 111 */     if (this.moment.getN() > 3L) {
/* 112 */       double variance = this.moment.m2 / (this.moment.n - 1L);
/* 113 */       if (this.moment.n <= 3L || variance < 1.0E-19D) {
/* 114 */         kurtosis = 0.0D;
/*     */       } else {
/* 116 */         double n = this.moment.n;
/* 117 */         kurtosis = (n * (n + 1.0D) * this.moment.getResult() - 3.0D * this.moment.m2 * this.moment.m2 * (n - 1.0D)) / (n - 1.0D) * (n - 2.0D) * (n - 3.0D) * variance * variance;
/*     */       } 
/*     */     } 
/* 123 */     return kurtosis;
/*     */   }
/*     */   
/*     */   public void clear() {
/* 131 */     if (this.incMoment) {
/* 132 */       this.moment.clear();
/*     */     } else {
/* 134 */       throw new MathIllegalStateException(LocalizedFormats.CANNOT_CLEAR_STATISTIC_CONSTRUCTED_FROM_EXTERNAL_MOMENTS, new Object[0]);
/*     */     } 
/*     */   }
/*     */   
/*     */   public long getN() {
/* 142 */     return this.moment.getN();
/*     */   }
/*     */   
/*     */   public double evaluate(double[] values, int begin, int length) {
/* 166 */     double kurt = Double.NaN;
/* 168 */     if (test(values, begin, length) && length > 3) {
/* 171 */       Variance variance = new Variance();
/* 172 */       variance.incrementAll(values, begin, length);
/* 173 */       double mean = variance.moment.m1;
/* 174 */       double stdDev = FastMath.sqrt(variance.getResult());
/* 178 */       double accum3 = 0.0D;
/* 179 */       for (int i = begin; i < begin + length; i++)
/* 180 */         accum3 += FastMath.pow(values[i] - mean, 4.0D); 
/* 182 */       accum3 /= FastMath.pow(stdDev, 4.0D);
/* 185 */       double n0 = length;
/* 187 */       double coefficientOne = n0 * (n0 + 1.0D) / (n0 - 1.0D) * (n0 - 2.0D) * (n0 - 3.0D);
/* 189 */       double termTwo = 3.0D * FastMath.pow(n0 - 1.0D, 2.0D) / (n0 - 2.0D) * (n0 - 3.0D);
/* 193 */       kurt = coefficientOne * accum3 - termTwo;
/*     */     } 
/* 195 */     return kurt;
/*     */   }
/*     */   
/*     */   public Kurtosis copy() {
/* 203 */     Kurtosis result = new Kurtosis();
/* 204 */     copy(this, result);
/* 205 */     return result;
/*     */   }
/*     */   
/*     */   public static void copy(Kurtosis source, Kurtosis dest) throws NullArgumentException {
/* 218 */     MathUtils.checkNotNull(source);
/* 219 */     MathUtils.checkNotNull(dest);
/* 220 */     dest.setData(source.getDataRef());
/* 221 */     dest.moment = source.moment.copy();
/* 222 */     dest.incMoment = source.incMoment;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\stat\descriptive\moment\Kurtosis.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */