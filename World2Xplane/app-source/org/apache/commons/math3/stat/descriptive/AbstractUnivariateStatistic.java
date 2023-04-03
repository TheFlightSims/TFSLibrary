/*     */ package org.apache.commons.math3.stat.descriptive;
/*     */ 
/*     */ import org.apache.commons.math3.exception.DimensionMismatchException;
/*     */ import org.apache.commons.math3.exception.MathIllegalArgumentException;
/*     */ import org.apache.commons.math3.exception.NotPositiveException;
/*     */ import org.apache.commons.math3.exception.NullArgumentException;
/*     */ import org.apache.commons.math3.exception.NumberIsTooLargeException;
/*     */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*     */ 
/*     */ public abstract class AbstractUnivariateStatistic implements UnivariateStatistic {
/*     */   private double[] storedData;
/*     */   
/*     */   public void setData(double[] values) {
/*  54 */     this.storedData = (values == null) ? null : (double[])values.clone();
/*     */   }
/*     */   
/*     */   public double[] getData() {
/*  62 */     return (this.storedData == null) ? null : (double[])this.storedData.clone();
/*     */   }
/*     */   
/*     */   protected double[] getDataRef() {
/*  70 */     return this.storedData;
/*     */   }
/*     */   
/*     */   public void setData(double[] values, int begin, int length) {
/*  81 */     this.storedData = new double[length];
/*  82 */     System.arraycopy(values, begin, this.storedData, 0, length);
/*     */   }
/*     */   
/*     */   public double evaluate() {
/*  93 */     return evaluate(this.storedData);
/*     */   }
/*     */   
/*     */   public double evaluate(double[] values) {
/* 100 */     test(values, 0, 0);
/* 101 */     return evaluate(values, 0, values.length);
/*     */   }
/*     */   
/*     */   public abstract double evaluate(double[] paramArrayOfdouble, int paramInt1, int paramInt2);
/*     */   
/*     */   public abstract UnivariateStatistic copy();
/*     */   
/*     */   protected boolean test(double[] values, int begin, int length) {
/* 137 */     return test(values, begin, length, false);
/*     */   }
/*     */   
/*     */   protected boolean test(double[] values, int begin, int length, boolean allowEmpty) {
/* 163 */     if (values == null)
/* 164 */       throw new NullArgumentException(LocalizedFormats.INPUT_ARRAY, new Object[0]); 
/* 167 */     if (begin < 0)
/* 168 */       throw new NotPositiveException(LocalizedFormats.START_POSITION, Integer.valueOf(begin)); 
/* 171 */     if (length < 0)
/* 172 */       throw new NotPositiveException(LocalizedFormats.LENGTH, Integer.valueOf(length)); 
/* 175 */     if (begin + length > values.length)
/* 176 */       throw new NumberIsTooLargeException(LocalizedFormats.SUBARRAY_ENDS_AFTER_ARRAY_END, Integer.valueOf(begin + length), Integer.valueOf(values.length), true); 
/* 180 */     if (length == 0 && !allowEmpty)
/* 181 */       return false; 
/* 184 */     return true;
/*     */   }
/*     */   
/*     */   protected boolean test(double[] values, double[] weights, int begin, int length) {
/* 222 */     return test(values, weights, begin, length, false);
/*     */   }
/*     */   
/*     */   protected boolean test(double[] values, double[] weights, int begin, int length, boolean allowEmpty) {
/* 258 */     if (weights == null)
/* 259 */       throw new NullArgumentException(LocalizedFormats.INPUT_ARRAY, new Object[0]); 
/* 262 */     if (weights.length != values.length)
/* 263 */       throw new DimensionMismatchException(weights.length, values.length); 
/* 266 */     boolean containsPositiveWeight = false;
/* 267 */     for (int i = begin; i < begin + length; i++) {
/* 268 */       if (Double.isNaN(weights[i]))
/* 269 */         throw new MathIllegalArgumentException(LocalizedFormats.NAN_ELEMENT_AT_INDEX, new Object[] { Integer.valueOf(i) }); 
/* 271 */       if (Double.isInfinite(weights[i]))
/* 272 */         throw new MathIllegalArgumentException(LocalizedFormats.INFINITE_ARRAY_ELEMENT, new Object[] { Double.valueOf(weights[i]), Integer.valueOf(i) }); 
/* 274 */       if (weights[i] < 0.0D)
/* 275 */         throw new MathIllegalArgumentException(LocalizedFormats.NEGATIVE_ELEMENT_AT_INDEX, new Object[] { Integer.valueOf(i), Double.valueOf(weights[i]) }); 
/* 277 */       if (!containsPositiveWeight && weights[i] > 0.0D)
/* 278 */         containsPositiveWeight = true; 
/*     */     } 
/* 282 */     if (!containsPositiveWeight)
/* 283 */       throw new MathIllegalArgumentException(LocalizedFormats.WEIGHT_AT_LEAST_ONE_NON_ZERO, new Object[0]); 
/* 286 */     return test(values, begin, length, allowEmpty);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\stat\descriptive\AbstractUnivariateStatistic.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */