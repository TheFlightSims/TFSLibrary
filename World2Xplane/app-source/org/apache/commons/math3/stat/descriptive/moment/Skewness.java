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
/*     */ public class Skewness extends AbstractStorelessUnivariateStatistic implements Serializable {
/*     */   private static final long serialVersionUID = 7101857578996691352L;
/*     */   
/*  49 */   protected ThirdMoment moment = null;
/*     */   
/*     */   protected boolean incMoment;
/*     */   
/*     */   public Skewness() {
/*  63 */     this.incMoment = true;
/*  64 */     this.moment = new ThirdMoment();
/*     */   }
/*     */   
/*     */   public Skewness(ThirdMoment m3) {
/*  72 */     this.incMoment = false;
/*  73 */     this.moment = m3;
/*     */   }
/*     */   
/*     */   public Skewness(Skewness original) {
/*  83 */     copy(original, this);
/*     */   }
/*     */   
/*     */   public void increment(double d) {
/*  91 */     if (this.incMoment)
/*  92 */       this.moment.increment(d); 
/*     */   }
/*     */   
/*     */   public double getResult() {
/* 106 */     if (this.moment.n < 3L)
/* 107 */       return Double.NaN; 
/* 109 */     double variance = this.moment.m2 / (this.moment.n - 1L);
/* 110 */     if (variance < 1.0E-19D)
/* 111 */       return 0.0D; 
/* 113 */     double n0 = this.moment.getN();
/* 114 */     return n0 * this.moment.m3 / (n0 - 1.0D) * (n0 - 2.0D) * FastMath.sqrt(variance) * variance;
/*     */   }
/*     */   
/*     */   public long getN() {
/* 123 */     return this.moment.getN();
/*     */   }
/*     */   
/*     */   public void clear() {
/* 131 */     if (this.incMoment)
/* 132 */       this.moment.clear(); 
/*     */   }
/*     */   
/*     */   public double evaluate(double[] values, int begin, int length) {
/* 157 */     double skew = Double.NaN;
/* 159 */     if (test(values, begin, length) && length > 2) {
/* 160 */       Mean mean = new Mean();
/* 162 */       double m = mean.evaluate(values, begin, length);
/* 167 */       double accum = 0.0D;
/* 168 */       double accum2 = 0.0D;
/* 169 */       for (int i = begin; i < begin + length; i++) {
/* 170 */         double d = values[i] - m;
/* 171 */         accum += d * d;
/* 172 */         accum2 += d;
/*     */       } 
/* 174 */       double variance = (accum - accum2 * accum2 / length) / (length - 1);
/* 176 */       double accum3 = 0.0D;
/* 177 */       for (int j = begin; j < begin + length; j++) {
/* 178 */         double d = values[j] - m;
/* 179 */         accum3 += d * d * d;
/*     */       } 
/* 181 */       accum3 /= variance * FastMath.sqrt(variance);
/* 184 */       double n0 = length;
/* 187 */       skew = n0 / (n0 - 1.0D) * (n0 - 2.0D) * accum3;
/*     */     } 
/* 189 */     return skew;
/*     */   }
/*     */   
/*     */   public Skewness copy() {
/* 197 */     Skewness result = new Skewness();
/* 198 */     copy(this, result);
/* 199 */     return result;
/*     */   }
/*     */   
/*     */   public static void copy(Skewness source, Skewness dest) throws NullArgumentException {
/* 212 */     MathUtils.checkNotNull(source);
/* 213 */     MathUtils.checkNotNull(dest);
/* 214 */     dest.setData(source.getDataRef());
/* 215 */     dest.moment = new ThirdMoment(source.moment.copy());
/* 216 */     dest.incMoment = source.incMoment;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\stat\descriptive\moment\Skewness.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */