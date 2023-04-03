/*     */ package org.apache.commons.math3.stat.descriptive;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Arrays;
/*     */ import org.apache.commons.math3.exception.DimensionMismatchException;
/*     */ import org.apache.commons.math3.exception.MathIllegalStateException;
/*     */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*     */ import org.apache.commons.math3.linear.RealMatrix;
/*     */ import org.apache.commons.math3.stat.descriptive.moment.GeometricMean;
/*     */ import org.apache.commons.math3.stat.descriptive.moment.Mean;
/*     */ import org.apache.commons.math3.stat.descriptive.moment.VectorialCovariance;
/*     */ import org.apache.commons.math3.stat.descriptive.rank.Max;
/*     */ import org.apache.commons.math3.stat.descriptive.rank.Min;
/*     */ import org.apache.commons.math3.stat.descriptive.summary.Sum;
/*     */ import org.apache.commons.math3.stat.descriptive.summary.SumOfLogs;
/*     */ import org.apache.commons.math3.stat.descriptive.summary.SumOfSquares;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ import org.apache.commons.math3.util.MathArrays;
/*     */ import org.apache.commons.math3.util.MathUtils;
/*     */ import org.apache.commons.math3.util.Precision;
/*     */ 
/*     */ public class MultivariateSummaryStatistics implements StatisticalMultivariateSummary, Serializable {
/*     */   private static final long serialVersionUID = 2271900808994826718L;
/*     */   
/*     */   private int k;
/*     */   
/*  82 */   private long n = 0L;
/*     */   
/*     */   private StorelessUnivariateStatistic[] sumImpl;
/*     */   
/*     */   private StorelessUnivariateStatistic[] sumSqImpl;
/*     */   
/*     */   private StorelessUnivariateStatistic[] minImpl;
/*     */   
/*     */   private StorelessUnivariateStatistic[] maxImpl;
/*     */   
/*     */   private StorelessUnivariateStatistic[] sumLogImpl;
/*     */   
/*     */   private StorelessUnivariateStatistic[] geoMeanImpl;
/*     */   
/*     */   private StorelessUnivariateStatistic[] meanImpl;
/*     */   
/*     */   private VectorialCovariance covarianceImpl;
/*     */   
/*     */   public MultivariateSummaryStatistics(int k, boolean isCovarianceBiasCorrected) {
/* 116 */     this.k = k;
/* 118 */     this.sumImpl = new StorelessUnivariateStatistic[k];
/* 119 */     this.sumSqImpl = new StorelessUnivariateStatistic[k];
/* 120 */     this.minImpl = new StorelessUnivariateStatistic[k];
/* 121 */     this.maxImpl = new StorelessUnivariateStatistic[k];
/* 122 */     this.sumLogImpl = new StorelessUnivariateStatistic[k];
/* 123 */     this.geoMeanImpl = new StorelessUnivariateStatistic[k];
/* 124 */     this.meanImpl = new StorelessUnivariateStatistic[k];
/* 126 */     for (int i = 0; i < k; i++) {
/* 127 */       this.sumImpl[i] = (StorelessUnivariateStatistic)new Sum();
/* 128 */       this.sumSqImpl[i] = (StorelessUnivariateStatistic)new SumOfSquares();
/* 129 */       this.minImpl[i] = (StorelessUnivariateStatistic)new Min();
/* 130 */       this.maxImpl[i] = (StorelessUnivariateStatistic)new Max();
/* 131 */       this.sumLogImpl[i] = (StorelessUnivariateStatistic)new SumOfLogs();
/* 132 */       this.geoMeanImpl[i] = (StorelessUnivariateStatistic)new GeometricMean();
/* 133 */       this.meanImpl[i] = (StorelessUnivariateStatistic)new Mean();
/*     */     } 
/* 136 */     this.covarianceImpl = new VectorialCovariance(k, isCovarianceBiasCorrected);
/*     */   }
/*     */   
/*     */   public void addValue(double[] value) {
/* 149 */     checkDimension(value.length);
/* 150 */     for (int i = 0; i < this.k; i++) {
/* 151 */       double v = value[i];
/* 152 */       this.sumImpl[i].increment(v);
/* 153 */       this.sumSqImpl[i].increment(v);
/* 154 */       this.minImpl[i].increment(v);
/* 155 */       this.maxImpl[i].increment(v);
/* 156 */       this.sumLogImpl[i].increment(v);
/* 157 */       this.geoMeanImpl[i].increment(v);
/* 158 */       this.meanImpl[i].increment(v);
/*     */     } 
/* 160 */     this.covarianceImpl.increment(value);
/* 161 */     this.n++;
/*     */   }
/*     */   
/*     */   public int getDimension() {
/* 169 */     return this.k;
/*     */   }
/*     */   
/*     */   public long getN() {
/* 177 */     return this.n;
/*     */   }
/*     */   
/*     */   private double[] getResults(StorelessUnivariateStatistic[] stats) {
/* 186 */     double[] results = new double[stats.length];
/* 187 */     for (int i = 0; i < results.length; i++)
/* 188 */       results[i] = stats[i].getResult(); 
/* 190 */     return results;
/*     */   }
/*     */   
/*     */   public double[] getSum() {
/* 201 */     return getResults(this.sumImpl);
/*     */   }
/*     */   
/*     */   public double[] getSumSq() {
/* 212 */     return getResults(this.sumSqImpl);
/*     */   }
/*     */   
/*     */   public double[] getSumLog() {
/* 223 */     return getResults(this.sumLogImpl);
/*     */   }
/*     */   
/*     */   public double[] getMean() {
/* 234 */     return getResults(this.meanImpl);
/*     */   }
/*     */   
/*     */   public double[] getStandardDeviation() {
/* 245 */     double[] stdDev = new double[this.k];
/* 246 */     if (getN() < 1L) {
/* 247 */       Arrays.fill(stdDev, Double.NaN);
/* 248 */     } else if (getN() < 2L) {
/* 249 */       Arrays.fill(stdDev, 0.0D);
/*     */     } else {
/* 251 */       RealMatrix matrix = this.covarianceImpl.getResult();
/* 252 */       for (int i = 0; i < this.k; i++)
/* 253 */         stdDev[i] = FastMath.sqrt(matrix.getEntry(i, i)); 
/*     */     } 
/* 256 */     return stdDev;
/*     */   }
/*     */   
/*     */   public RealMatrix getCovariance() {
/* 265 */     return this.covarianceImpl.getResult();
/*     */   }
/*     */   
/*     */   public double[] getMax() {
/* 276 */     return getResults(this.maxImpl);
/*     */   }
/*     */   
/*     */   public double[] getMin() {
/* 287 */     return getResults(this.minImpl);
/*     */   }
/*     */   
/*     */   public double[] getGeometricMean() {
/* 298 */     return getResults(this.geoMeanImpl);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 309 */     String separator = ", ";
/* 310 */     String suffix = System.getProperty("line.separator");
/* 311 */     StringBuilder outBuffer = new StringBuilder();
/* 312 */     outBuffer.append("MultivariateSummaryStatistics:" + suffix);
/* 313 */     outBuffer.append("n: " + getN() + suffix);
/* 314 */     append(outBuffer, getMin(), "min: ", ", ", suffix);
/* 315 */     append(outBuffer, getMax(), "max: ", ", ", suffix);
/* 316 */     append(outBuffer, getMean(), "mean: ", ", ", suffix);
/* 317 */     append(outBuffer, getGeometricMean(), "geometric mean: ", ", ", suffix);
/* 318 */     append(outBuffer, getSumSq(), "sum of squares: ", ", ", suffix);
/* 319 */     append(outBuffer, getSumLog(), "sum of logarithms: ", ", ", suffix);
/* 320 */     append(outBuffer, getStandardDeviation(), "standard deviation: ", ", ", suffix);
/* 321 */     outBuffer.append("covariance: " + getCovariance().toString() + suffix);
/* 322 */     return outBuffer.toString();
/*     */   }
/*     */   
/*     */   private void append(StringBuilder buffer, double[] data, String prefix, String separator, String suffix) {
/* 335 */     buffer.append(prefix);
/* 336 */     for (int i = 0; i < data.length; i++) {
/* 337 */       if (i > 0)
/* 338 */         buffer.append(separator); 
/* 340 */       buffer.append(data[i]);
/*     */     } 
/* 342 */     buffer.append(suffix);
/*     */   }
/*     */   
/*     */   public void clear() {
/* 349 */     this.n = 0L;
/* 350 */     for (int i = 0; i < this.k; i++) {
/* 351 */       this.minImpl[i].clear();
/* 352 */       this.maxImpl[i].clear();
/* 353 */       this.sumImpl[i].clear();
/* 354 */       this.sumLogImpl[i].clear();
/* 355 */       this.sumSqImpl[i].clear();
/* 356 */       this.geoMeanImpl[i].clear();
/* 357 */       this.meanImpl[i].clear();
/*     */     } 
/* 359 */     this.covarianceImpl.clear();
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/* 370 */     if (object == this)
/* 371 */       return true; 
/* 373 */     if (!(object instanceof MultivariateSummaryStatistics))
/* 374 */       return false; 
/* 376 */     MultivariateSummaryStatistics stat = (MultivariateSummaryStatistics)object;
/* 377 */     return (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()) && MathArrays.equalsIncludingNaN(stat.getMean(), getMean()) && MathArrays.equalsIncludingNaN(stat.getMin(), getMin()) && Precision.equalsIncludingNaN((float)stat.getN(), (float)getN()) && MathArrays.equalsIncludingNaN(stat.getSum(), getSum()) && MathArrays.equalsIncludingNaN(stat.getSumSq(), getSumSq()) && MathArrays.equalsIncludingNaN(stat.getSumLog(), getSumLog()) && stat.getCovariance().equals(getCovariance()));
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 395 */     int result = 31 + MathUtils.hash(getGeometricMean());
/* 396 */     result = result * 31 + MathUtils.hash(getGeometricMean());
/* 397 */     result = result * 31 + MathUtils.hash(getMax());
/* 398 */     result = result * 31 + MathUtils.hash(getMean());
/* 399 */     result = result * 31 + MathUtils.hash(getMin());
/* 400 */     result = result * 31 + MathUtils.hash(getN());
/* 401 */     result = result * 31 + MathUtils.hash(getSum());
/* 402 */     result = result * 31 + MathUtils.hash(getSumSq());
/* 403 */     result = result * 31 + MathUtils.hash(getSumLog());
/* 404 */     result = result * 31 + getCovariance().hashCode();
/* 405 */     return result;
/*     */   }
/*     */   
/*     */   private void setImpl(StorelessUnivariateStatistic[] newImpl, StorelessUnivariateStatistic[] oldImpl) {
/* 420 */     checkEmpty();
/* 421 */     checkDimension(newImpl.length);
/* 422 */     System.arraycopy(newImpl, 0, oldImpl, 0, newImpl.length);
/*     */   }
/*     */   
/*     */   public StorelessUnivariateStatistic[] getSumImpl() {
/* 431 */     return (StorelessUnivariateStatistic[])this.sumImpl.clone();
/*     */   }
/*     */   
/*     */   public void setSumImpl(StorelessUnivariateStatistic[] sumImpl) {
/* 448 */     setImpl(sumImpl, this.sumImpl);
/*     */   }
/*     */   
/*     */   public StorelessUnivariateStatistic[] getSumsqImpl() {
/* 457 */     return (StorelessUnivariateStatistic[])this.sumSqImpl.clone();
/*     */   }
/*     */   
/*     */   public void setSumsqImpl(StorelessUnivariateStatistic[] sumsqImpl) {
/* 474 */     setImpl(sumsqImpl, this.sumSqImpl);
/*     */   }
/*     */   
/*     */   public StorelessUnivariateStatistic[] getMinImpl() {
/* 483 */     return (StorelessUnivariateStatistic[])this.minImpl.clone();
/*     */   }
/*     */   
/*     */   public void setMinImpl(StorelessUnivariateStatistic[] minImpl) {
/* 500 */     setImpl(minImpl, this.minImpl);
/*     */   }
/*     */   
/*     */   public StorelessUnivariateStatistic[] getMaxImpl() {
/* 509 */     return (StorelessUnivariateStatistic[])this.maxImpl.clone();
/*     */   }
/*     */   
/*     */   public void setMaxImpl(StorelessUnivariateStatistic[] maxImpl) {
/* 526 */     setImpl(maxImpl, this.maxImpl);
/*     */   }
/*     */   
/*     */   public StorelessUnivariateStatistic[] getSumLogImpl() {
/* 535 */     return (StorelessUnivariateStatistic[])this.sumLogImpl.clone();
/*     */   }
/*     */   
/*     */   public void setSumLogImpl(StorelessUnivariateStatistic[] sumLogImpl) {
/* 552 */     setImpl(sumLogImpl, this.sumLogImpl);
/*     */   }
/*     */   
/*     */   public StorelessUnivariateStatistic[] getGeoMeanImpl() {
/* 561 */     return (StorelessUnivariateStatistic[])this.geoMeanImpl.clone();
/*     */   }
/*     */   
/*     */   public void setGeoMeanImpl(StorelessUnivariateStatistic[] geoMeanImpl) {
/* 578 */     setImpl(geoMeanImpl, this.geoMeanImpl);
/*     */   }
/*     */   
/*     */   public StorelessUnivariateStatistic[] getMeanImpl() {
/* 587 */     return (StorelessUnivariateStatistic[])this.meanImpl.clone();
/*     */   }
/*     */   
/*     */   public void setMeanImpl(StorelessUnivariateStatistic[] meanImpl) {
/* 604 */     setImpl(meanImpl, this.meanImpl);
/*     */   }
/*     */   
/*     */   private void checkEmpty() {
/* 612 */     if (this.n > 0L)
/* 613 */       throw new MathIllegalStateException(LocalizedFormats.VALUES_ADDED_BEFORE_CONFIGURING_STATISTIC, new Object[] { Long.valueOf(this.n) }); 
/*     */   }
/*     */   
/*     */   private void checkDimension(int dimension) {
/* 624 */     if (dimension != this.k)
/* 625 */       throw new DimensionMismatchException(dimension, this.k); 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\stat\descriptive\MultivariateSummaryStatistics.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */