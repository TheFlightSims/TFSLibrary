/*     */ package org.apache.commons.math3.stat.descriptive;
/*     */ 
/*     */ import org.apache.commons.math3.exception.NullArgumentException;
/*     */ import org.apache.commons.math3.util.MathUtils;
/*     */ 
/*     */ public class SynchronizedDescriptiveStatistics extends DescriptiveStatistics {
/*     */   private static final long serialVersionUID = 1L;
/*     */   
/*     */   public SynchronizedDescriptiveStatistics() {
/*  44 */     this(-1);
/*     */   }
/*     */   
/*     */   public SynchronizedDescriptiveStatistics(int window) {
/*  52 */     super(window);
/*     */   }
/*     */   
/*     */   public SynchronizedDescriptiveStatistics(SynchronizedDescriptiveStatistics original) {
/*  61 */     copy(original, this);
/*     */   }
/*     */   
/*     */   public synchronized void addValue(double v) {
/*  69 */     super.addValue(v);
/*     */   }
/*     */   
/*     */   public synchronized double apply(UnivariateStatistic stat) {
/*  77 */     return super.apply(stat);
/*     */   }
/*     */   
/*     */   public synchronized void clear() {
/*  85 */     super.clear();
/*     */   }
/*     */   
/*     */   public synchronized double getElement(int index) {
/*  93 */     return super.getElement(index);
/*     */   }
/*     */   
/*     */   public synchronized long getN() {
/* 101 */     return super.getN();
/*     */   }
/*     */   
/*     */   public synchronized double getStandardDeviation() {
/* 109 */     return super.getStandardDeviation();
/*     */   }
/*     */   
/*     */   public synchronized double[] getValues() {
/* 117 */     return super.getValues();
/*     */   }
/*     */   
/*     */   public synchronized int getWindowSize() {
/* 125 */     return super.getWindowSize();
/*     */   }
/*     */   
/*     */   public synchronized void setWindowSize(int windowSize) {
/* 133 */     super.setWindowSize(windowSize);
/*     */   }
/*     */   
/*     */   public synchronized String toString() {
/* 141 */     return super.toString();
/*     */   }
/*     */   
/*     */   public synchronized SynchronizedDescriptiveStatistics copy() {
/* 152 */     SynchronizedDescriptiveStatistics result = new SynchronizedDescriptiveStatistics();
/* 154 */     copy(this, result);
/* 155 */     return result;
/*     */   }
/*     */   
/*     */   public static void copy(SynchronizedDescriptiveStatistics source, SynchronizedDescriptiveStatistics dest) throws NullArgumentException {
/* 170 */     MathUtils.checkNotNull(source);
/* 171 */     MathUtils.checkNotNull(dest);
/* 172 */     synchronized (source) {
/* 173 */       synchronized (dest) {
/* 174 */         DescriptiveStatistics.copy(source, dest);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\stat\descriptive\SynchronizedDescriptiveStatistics.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */