/*     */ package org.apache.commons.math3.stat.descriptive.moment;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.apache.commons.math3.exception.NullArgumentException;
/*     */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*     */ import org.apache.commons.math3.stat.descriptive.AbstractStorelessUnivariateStatistic;
/*     */ import org.apache.commons.math3.stat.descriptive.StorelessUnivariateStatistic;
/*     */ import org.apache.commons.math3.stat.descriptive.UnivariateStatistic;
/*     */ import org.apache.commons.math3.stat.descriptive.WeightedEvaluation;
/*     */ import org.apache.commons.math3.util.MathUtils;
/*     */ 
/*     */ public class Variance extends AbstractStorelessUnivariateStatistic implements Serializable, WeightedEvaluation {
/*     */   private static final long serialVersionUID = -9111962718267217978L;
/*     */   
/*  75 */   protected SecondMoment moment = null;
/*     */   
/*     */   protected boolean incMoment = true;
/*     */   
/*     */   private boolean isBiasCorrected = true;
/*     */   
/*     */   public Variance() {
/*  98 */     this.moment = new SecondMoment();
/*     */   }
/*     */   
/*     */   public Variance(SecondMoment m2) {
/* 112 */     this.incMoment = false;
/* 113 */     this.moment = m2;
/*     */   }
/*     */   
/*     */   public Variance(boolean isBiasCorrected) {
/* 125 */     this.moment = new SecondMoment();
/* 126 */     this.isBiasCorrected = isBiasCorrected;
/*     */   }
/*     */   
/*     */   public Variance(boolean isBiasCorrected, SecondMoment m2) {
/* 139 */     this.incMoment = false;
/* 140 */     this.moment = m2;
/* 141 */     this.isBiasCorrected = isBiasCorrected;
/*     */   }
/*     */   
/*     */   public Variance(Variance original) {
/* 151 */     copy(original, this);
/*     */   }
/*     */   
/*     */   public void increment(double d) {
/* 169 */     if (this.incMoment)
/* 170 */       this.moment.increment(d); 
/*     */   }
/*     */   
/*     */   public double getResult() {
/* 179 */     if (this.moment.n == 0L)
/* 180 */       return Double.NaN; 
/* 181 */     if (this.moment.n == 1L)
/* 182 */       return 0.0D; 
/* 184 */     if (this.isBiasCorrected)
/* 185 */       return this.moment.m2 / (this.moment.n - 1.0D); 
/* 187 */     return this.moment.m2 / this.moment.n;
/*     */   }
/*     */   
/*     */   public long getN() {
/* 196 */     return this.moment.getN();
/*     */   }
/*     */   
/*     */   public void clear() {
/* 204 */     if (this.incMoment)
/* 205 */       this.moment.clear(); 
/*     */   }
/*     */   
/*     */   public double evaluate(double[] values) {
/* 227 */     if (values == null)
/* 228 */       throw new NullArgumentException(LocalizedFormats.INPUT_ARRAY, new Object[0]); 
/* 230 */     return evaluate(values, 0, values.length);
/*     */   }
/*     */   
/*     */   public double evaluate(double[] values, int begin, int length) {
/* 256 */     double var = Double.NaN;
/* 258 */     if (test(values, begin, length)) {
/* 259 */       clear();
/* 260 */       if (length == 1) {
/* 261 */         var = 0.0D;
/* 262 */       } else if (length > 1) {
/* 263 */         Mean mean = new Mean();
/* 264 */         double m = mean.evaluate(values, begin, length);
/* 265 */         var = evaluate(values, m, begin, length);
/*     */       } 
/*     */     } 
/* 268 */     return var;
/*     */   }
/*     */   
/*     */   public double evaluate(double[] values, double[] weights, int begin, int length) {
/* 316 */     double var = Double.NaN;
/* 318 */     if (test(values, weights, begin, length)) {
/* 319 */       clear();
/* 320 */       if (length == 1) {
/* 321 */         var = 0.0D;
/* 322 */       } else if (length > 1) {
/* 323 */         Mean mean = new Mean();
/* 324 */         double m = mean.evaluate(values, weights, begin, length);
/* 325 */         var = evaluate(values, weights, m, begin, length);
/*     */       } 
/*     */     } 
/* 328 */     return var;
/*     */   }
/*     */   
/*     */   public double evaluate(double[] values, double[] weights) {
/* 370 */     return evaluate(values, weights, 0, values.length);
/*     */   }
/*     */   
/*     */   public double evaluate(double[] values, double mean, int begin, int length) {
/* 402 */     double var = Double.NaN;
/* 404 */     if (test(values, begin, length))
/* 405 */       if (length == 1) {
/* 406 */         var = 0.0D;
/* 407 */       } else if (length > 1) {
/* 408 */         double accum = 0.0D;
/* 409 */         double dev = 0.0D;
/* 410 */         double accum2 = 0.0D;
/* 411 */         for (int i = begin; i < begin + length; i++) {
/* 412 */           dev = values[i] - mean;
/* 413 */           accum += dev * dev;
/* 414 */           accum2 += dev;
/*     */         } 
/* 416 */         double len = length;
/* 417 */         if (this.isBiasCorrected) {
/* 418 */           var = (accum - accum2 * accum2 / len) / (len - 1.0D);
/*     */         } else {
/* 420 */           var = (accum - accum2 * accum2 / len) / len;
/*     */         } 
/*     */       }  
/* 424 */     return var;
/*     */   }
/*     */   
/*     */   public double evaluate(double[] values, double mean) {
/* 453 */     return evaluate(values, mean, 0, values.length);
/*     */   }
/*     */   
/*     */   public double evaluate(double[] values, double[] weights, double mean, int begin, int length) {
/* 504 */     double var = Double.NaN;
/* 506 */     if (test(values, weights, begin, length))
/* 507 */       if (length == 1) {
/* 508 */         var = 0.0D;
/* 509 */       } else if (length > 1) {
/* 510 */         double accum = 0.0D;
/* 511 */         double dev = 0.0D;
/* 512 */         double accum2 = 0.0D;
/* 513 */         for (int i = begin; i < begin + length; i++) {
/* 514 */           dev = values[i] - mean;
/* 515 */           accum += weights[i] * dev * dev;
/* 516 */           accum2 += weights[i] * dev;
/*     */         } 
/* 519 */         double sumWts = 0.0D;
/* 520 */         for (int j = begin; j < begin + length; j++)
/* 521 */           sumWts += weights[j]; 
/* 524 */         if (this.isBiasCorrected) {
/* 525 */           var = (accum - accum2 * accum2 / sumWts) / (sumWts - 1.0D);
/*     */         } else {
/* 527 */           var = (accum - accum2 * accum2 / sumWts) / sumWts;
/*     */         } 
/*     */       }  
/* 531 */     return var;
/*     */   }
/*     */   
/*     */   public double evaluate(double[] values, double[] weights, double mean) {
/* 576 */     return evaluate(values, weights, mean, 0, values.length);
/*     */   }
/*     */   
/*     */   public boolean isBiasCorrected() {
/* 583 */     return this.isBiasCorrected;
/*     */   }
/*     */   
/*     */   public void setBiasCorrected(boolean biasCorrected) {
/* 590 */     this.isBiasCorrected = biasCorrected;
/*     */   }
/*     */   
/*     */   public Variance copy() {
/* 598 */     Variance result = new Variance();
/* 599 */     copy(this, result);
/* 600 */     return result;
/*     */   }
/*     */   
/*     */   public static void copy(Variance source, Variance dest) throws NullArgumentException {
/* 613 */     MathUtils.checkNotNull(source);
/* 614 */     MathUtils.checkNotNull(dest);
/* 615 */     dest.setData(source.getDataRef());
/* 616 */     dest.moment = source.moment.copy();
/* 617 */     dest.isBiasCorrected = source.isBiasCorrected;
/* 618 */     dest.incMoment = source.incMoment;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\stat\descriptive\moment\Variance.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */