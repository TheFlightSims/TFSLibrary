/*     */ package org.apache.commons.math3.stat.descriptive.summary;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.apache.commons.math3.exception.NullArgumentException;
/*     */ import org.apache.commons.math3.stat.descriptive.AbstractStorelessUnivariateStatistic;
/*     */ import org.apache.commons.math3.stat.descriptive.StorelessUnivariateStatistic;
/*     */ import org.apache.commons.math3.stat.descriptive.UnivariateStatistic;
/*     */ import org.apache.commons.math3.stat.descriptive.WeightedEvaluation;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ import org.apache.commons.math3.util.MathUtils;
/*     */ 
/*     */ public class Product extends AbstractStorelessUnivariateStatistic implements Serializable, WeightedEvaluation {
/*     */   private static final long serialVersionUID = 2824226005990582538L;
/*     */   
/*     */   private long n;
/*     */   
/*     */   private double value;
/*     */   
/*     */   public Product() {
/*  58 */     this.n = 0L;
/*  59 */     this.value = 1.0D;
/*     */   }
/*     */   
/*     */   public Product(Product original) {
/*  69 */     copy(original, this);
/*     */   }
/*     */   
/*     */   public void increment(double d) {
/*  77 */     this.value *= d;
/*  78 */     this.n++;
/*     */   }
/*     */   
/*     */   public double getResult() {
/*  86 */     return this.value;
/*     */   }
/*     */   
/*     */   public long getN() {
/*  93 */     return this.n;
/*     */   }
/*     */   
/*     */   public void clear() {
/* 101 */     this.value = 1.0D;
/* 102 */     this.n = 0L;
/*     */   }
/*     */   
/*     */   public double evaluate(double[] values, int begin, int length) {
/* 121 */     double product = Double.NaN;
/* 122 */     if (test(values, begin, length, true)) {
/* 123 */       product = 1.0D;
/* 124 */       for (int i = begin; i < begin + length; i++)
/* 125 */         product *= values[i]; 
/*     */     } 
/* 128 */     return product;
/*     */   }
/*     */   
/*     */   public double evaluate(double[] values, double[] weights, int begin, int length) {
/* 161 */     double product = Double.NaN;
/* 162 */     if (test(values, weights, begin, length, true)) {
/* 163 */       product = 1.0D;
/* 164 */       for (int i = begin; i < begin + length; i++)
/* 165 */         product *= FastMath.pow(values[i], weights[i]); 
/*     */     } 
/* 168 */     return product;
/*     */   }
/*     */   
/*     */   public double evaluate(double[] values, double[] weights) {
/* 195 */     return evaluate(values, weights, 0, values.length);
/*     */   }
/*     */   
/*     */   public Product copy() {
/* 204 */     Product result = new Product();
/* 205 */     copy(this, result);
/* 206 */     return result;
/*     */   }
/*     */   
/*     */   public static void copy(Product source, Product dest) throws NullArgumentException {
/* 219 */     MathUtils.checkNotNull(source);
/* 220 */     MathUtils.checkNotNull(dest);
/* 221 */     dest.setData(source.getDataRef());
/* 222 */     dest.n = source.n;
/* 223 */     dest.value = source.value;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\stat\descriptive\summary\Product.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */