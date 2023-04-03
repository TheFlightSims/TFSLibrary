/*     */ package org.apache.commons.math3.stat.descriptive;
/*     */ 
/*     */ import org.apache.commons.math3.linear.RealMatrix;
/*     */ 
/*     */ public class SynchronizedMultivariateSummaryStatistics extends MultivariateSummaryStatistics {
/*     */   private static final long serialVersionUID = 7099834153347155363L;
/*     */   
/*     */   public SynchronizedMultivariateSummaryStatistics(int k, boolean isCovarianceBiasCorrected) {
/*  47 */     super(k, isCovarianceBiasCorrected);
/*     */   }
/*     */   
/*     */   public synchronized void addValue(double[] value) {
/*  55 */     super.addValue(value);
/*     */   }
/*     */   
/*     */   public synchronized int getDimension() {
/*  63 */     return super.getDimension();
/*     */   }
/*     */   
/*     */   public synchronized long getN() {
/*  71 */     return super.getN();
/*     */   }
/*     */   
/*     */   public synchronized double[] getSum() {
/*  79 */     return super.getSum();
/*     */   }
/*     */   
/*     */   public synchronized double[] getSumSq() {
/*  87 */     return super.getSumSq();
/*     */   }
/*     */   
/*     */   public synchronized double[] getSumLog() {
/*  95 */     return super.getSumLog();
/*     */   }
/*     */   
/*     */   public synchronized double[] getMean() {
/* 103 */     return super.getMean();
/*     */   }
/*     */   
/*     */   public synchronized double[] getStandardDeviation() {
/* 111 */     return super.getStandardDeviation();
/*     */   }
/*     */   
/*     */   public synchronized RealMatrix getCovariance() {
/* 119 */     return super.getCovariance();
/*     */   }
/*     */   
/*     */   public synchronized double[] getMax() {
/* 127 */     return super.getMax();
/*     */   }
/*     */   
/*     */   public synchronized double[] getMin() {
/* 135 */     return super.getMin();
/*     */   }
/*     */   
/*     */   public synchronized double[] getGeometricMean() {
/* 143 */     return super.getGeometricMean();
/*     */   }
/*     */   
/*     */   public synchronized String toString() {
/* 151 */     return super.toString();
/*     */   }
/*     */   
/*     */   public synchronized void clear() {
/* 159 */     super.clear();
/*     */   }
/*     */   
/*     */   public synchronized boolean equals(Object object) {
/* 167 */     return super.equals(object);
/*     */   }
/*     */   
/*     */   public synchronized int hashCode() {
/* 175 */     return super.hashCode();
/*     */   }
/*     */   
/*     */   public synchronized StorelessUnivariateStatistic[] getSumImpl() {
/* 183 */     return super.getSumImpl();
/*     */   }
/*     */   
/*     */   public synchronized void setSumImpl(StorelessUnivariateStatistic[] sumImpl) {
/* 191 */     super.setSumImpl(sumImpl);
/*     */   }
/*     */   
/*     */   public synchronized StorelessUnivariateStatistic[] getSumsqImpl() {
/* 199 */     return super.getSumsqImpl();
/*     */   }
/*     */   
/*     */   public synchronized void setSumsqImpl(StorelessUnivariateStatistic[] sumsqImpl) {
/* 207 */     super.setSumsqImpl(sumsqImpl);
/*     */   }
/*     */   
/*     */   public synchronized StorelessUnivariateStatistic[] getMinImpl() {
/* 215 */     return super.getMinImpl();
/*     */   }
/*     */   
/*     */   public synchronized void setMinImpl(StorelessUnivariateStatistic[] minImpl) {
/* 223 */     super.setMinImpl(minImpl);
/*     */   }
/*     */   
/*     */   public synchronized StorelessUnivariateStatistic[] getMaxImpl() {
/* 231 */     return super.getMaxImpl();
/*     */   }
/*     */   
/*     */   public synchronized void setMaxImpl(StorelessUnivariateStatistic[] maxImpl) {
/* 239 */     super.setMaxImpl(maxImpl);
/*     */   }
/*     */   
/*     */   public synchronized StorelessUnivariateStatistic[] getSumLogImpl() {
/* 247 */     return super.getSumLogImpl();
/*     */   }
/*     */   
/*     */   public synchronized void setSumLogImpl(StorelessUnivariateStatistic[] sumLogImpl) {
/* 255 */     super.setSumLogImpl(sumLogImpl);
/*     */   }
/*     */   
/*     */   public synchronized StorelessUnivariateStatistic[] getGeoMeanImpl() {
/* 263 */     return super.getGeoMeanImpl();
/*     */   }
/*     */   
/*     */   public synchronized void setGeoMeanImpl(StorelessUnivariateStatistic[] geoMeanImpl) {
/* 271 */     super.setGeoMeanImpl(geoMeanImpl);
/*     */   }
/*     */   
/*     */   public synchronized StorelessUnivariateStatistic[] getMeanImpl() {
/* 279 */     return super.getMeanImpl();
/*     */   }
/*     */   
/*     */   public synchronized void setMeanImpl(StorelessUnivariateStatistic[] meanImpl) {
/* 287 */     super.setMeanImpl(meanImpl);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\stat\descriptive\SynchronizedMultivariateSummaryStatistics.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */