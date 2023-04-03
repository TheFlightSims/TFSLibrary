/*     */ package org.apache.commons.math3.stat.descriptive;
/*     */ 
/*     */ import org.apache.commons.math3.exception.NullArgumentException;
/*     */ import org.apache.commons.math3.util.MathUtils;
/*     */ 
/*     */ public class SynchronizedSummaryStatistics extends SummaryStatistics {
/*     */   private static final long serialVersionUID = 1909861009042253704L;
/*     */   
/*     */   public SynchronizedSummaryStatistics() {}
/*     */   
/*     */   public SynchronizedSummaryStatistics(SynchronizedSummaryStatistics original) {
/*  53 */     copy(original, this);
/*     */   }
/*     */   
/*     */   public synchronized StatisticalSummary getSummary() {
/*  61 */     return super.getSummary();
/*     */   }
/*     */   
/*     */   public synchronized void addValue(double value) {
/*  69 */     super.addValue(value);
/*     */   }
/*     */   
/*     */   public synchronized long getN() {
/*  77 */     return super.getN();
/*     */   }
/*     */   
/*     */   public synchronized double getSum() {
/*  85 */     return super.getSum();
/*     */   }
/*     */   
/*     */   public synchronized double getSumsq() {
/*  93 */     return super.getSumsq();
/*     */   }
/*     */   
/*     */   public synchronized double getMean() {
/* 101 */     return super.getMean();
/*     */   }
/*     */   
/*     */   public synchronized double getStandardDeviation() {
/* 109 */     return super.getStandardDeviation();
/*     */   }
/*     */   
/*     */   public synchronized double getVariance() {
/* 117 */     return super.getVariance();
/*     */   }
/*     */   
/*     */   public synchronized double getPopulationVariance() {
/* 125 */     return super.getPopulationVariance();
/*     */   }
/*     */   
/*     */   public synchronized double getMax() {
/* 133 */     return super.getMax();
/*     */   }
/*     */   
/*     */   public synchronized double getMin() {
/* 141 */     return super.getMin();
/*     */   }
/*     */   
/*     */   public synchronized double getGeometricMean() {
/* 149 */     return super.getGeometricMean();
/*     */   }
/*     */   
/*     */   public synchronized String toString() {
/* 157 */     return super.toString();
/*     */   }
/*     */   
/*     */   public synchronized void clear() {
/* 165 */     super.clear();
/*     */   }
/*     */   
/*     */   public synchronized boolean equals(Object object) {
/* 173 */     return super.equals(object);
/*     */   }
/*     */   
/*     */   public synchronized int hashCode() {
/* 181 */     return super.hashCode();
/*     */   }
/*     */   
/*     */   public synchronized StorelessUnivariateStatistic getSumImpl() {
/* 189 */     return super.getSumImpl();
/*     */   }
/*     */   
/*     */   public synchronized void setSumImpl(StorelessUnivariateStatistic sumImpl) {
/* 197 */     super.setSumImpl(sumImpl);
/*     */   }
/*     */   
/*     */   public synchronized StorelessUnivariateStatistic getSumsqImpl() {
/* 205 */     return super.getSumsqImpl();
/*     */   }
/*     */   
/*     */   public synchronized void setSumsqImpl(StorelessUnivariateStatistic sumsqImpl) {
/* 213 */     super.setSumsqImpl(sumsqImpl);
/*     */   }
/*     */   
/*     */   public synchronized StorelessUnivariateStatistic getMinImpl() {
/* 221 */     return super.getMinImpl();
/*     */   }
/*     */   
/*     */   public synchronized void setMinImpl(StorelessUnivariateStatistic minImpl) {
/* 229 */     super.setMinImpl(minImpl);
/*     */   }
/*     */   
/*     */   public synchronized StorelessUnivariateStatistic getMaxImpl() {
/* 237 */     return super.getMaxImpl();
/*     */   }
/*     */   
/*     */   public synchronized void setMaxImpl(StorelessUnivariateStatistic maxImpl) {
/* 245 */     super.setMaxImpl(maxImpl);
/*     */   }
/*     */   
/*     */   public synchronized StorelessUnivariateStatistic getSumLogImpl() {
/* 253 */     return super.getSumLogImpl();
/*     */   }
/*     */   
/*     */   public synchronized void setSumLogImpl(StorelessUnivariateStatistic sumLogImpl) {
/* 261 */     super.setSumLogImpl(sumLogImpl);
/*     */   }
/*     */   
/*     */   public synchronized StorelessUnivariateStatistic getGeoMeanImpl() {
/* 269 */     return super.getGeoMeanImpl();
/*     */   }
/*     */   
/*     */   public synchronized void setGeoMeanImpl(StorelessUnivariateStatistic geoMeanImpl) {
/* 277 */     super.setGeoMeanImpl(geoMeanImpl);
/*     */   }
/*     */   
/*     */   public synchronized StorelessUnivariateStatistic getMeanImpl() {
/* 285 */     return super.getMeanImpl();
/*     */   }
/*     */   
/*     */   public synchronized void setMeanImpl(StorelessUnivariateStatistic meanImpl) {
/* 293 */     super.setMeanImpl(meanImpl);
/*     */   }
/*     */   
/*     */   public synchronized StorelessUnivariateStatistic getVarianceImpl() {
/* 301 */     return super.getVarianceImpl();
/*     */   }
/*     */   
/*     */   public synchronized void setVarianceImpl(StorelessUnivariateStatistic varianceImpl) {
/* 309 */     super.setVarianceImpl(varianceImpl);
/*     */   }
/*     */   
/*     */   public synchronized SynchronizedSummaryStatistics copy() {
/* 320 */     SynchronizedSummaryStatistics result = new SynchronizedSummaryStatistics();
/* 322 */     copy(this, result);
/* 323 */     return result;
/*     */   }
/*     */   
/*     */   public static void copy(SynchronizedSummaryStatistics source, SynchronizedSummaryStatistics dest) throws NullArgumentException {
/* 338 */     MathUtils.checkNotNull(source);
/* 339 */     MathUtils.checkNotNull(dest);
/* 340 */     synchronized (source) {
/* 341 */       synchronized (dest) {
/* 342 */         SummaryStatistics.copy(source, dest);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\stat\descriptive\SynchronizedSummaryStatistics.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */