/*     */ package org.apache.commons.math3.stat.descriptive;
/*     */ 
/*     */ import org.apache.commons.math3.exception.NullArgumentException;
/*     */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*     */ import org.apache.commons.math3.util.MathUtils;
/*     */ import org.apache.commons.math3.util.Precision;
/*     */ 
/*     */ public abstract class AbstractStorelessUnivariateStatistic extends AbstractUnivariateStatistic implements StorelessUnivariateStatistic {
/*     */   public double evaluate(double[] values) {
/*  59 */     if (values == null)
/*  60 */       throw new NullArgumentException(LocalizedFormats.INPUT_ARRAY, new Object[0]); 
/*  62 */     return evaluate(values, 0, values.length);
/*     */   }
/*     */   
/*     */   public double evaluate(double[] values, int begin, int length) {
/*  88 */     if (test(values, begin, length)) {
/*  89 */       clear();
/*  90 */       incrementAll(values, begin, length);
/*     */     } 
/*  92 */     return getResult();
/*     */   }
/*     */   
/*     */   public void incrementAll(double[] values) {
/* 127 */     if (values == null)
/* 128 */       throw new NullArgumentException(LocalizedFormats.INPUT_ARRAY, new Object[0]); 
/* 130 */     incrementAll(values, 0, values.length);
/*     */   }
/*     */   
/*     */   public void incrementAll(double[] values, int begin, int length) {
/* 146 */     if (test(values, begin, length)) {
/* 147 */       int k = begin + length;
/* 148 */       for (int i = begin; i < k; i++)
/* 149 */         increment(values[i]); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/* 163 */     if (object == this)
/* 164 */       return true; 
/* 166 */     if (!(object instanceof AbstractStorelessUnivariateStatistic))
/* 167 */       return false; 
/* 169 */     AbstractStorelessUnivariateStatistic stat = (AbstractStorelessUnivariateStatistic)object;
/* 170 */     return (Precision.equalsIncludingNaN(stat.getResult(), getResult()) && Precision.equalsIncludingNaN((float)stat.getN(), (float)getN()));
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 181 */     return 31 * (31 + MathUtils.hash(getResult())) + MathUtils.hash(getN());
/*     */   }
/*     */   
/*     */   public abstract StorelessUnivariateStatistic copy();
/*     */   
/*     */   public abstract void clear();
/*     */   
/*     */   public abstract double getResult();
/*     */   
/*     */   public abstract void increment(double paramDouble);
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\stat\descriptive\AbstractStorelessUnivariateStatistic.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */