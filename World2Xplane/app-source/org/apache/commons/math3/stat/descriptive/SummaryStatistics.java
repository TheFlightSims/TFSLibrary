/*     */ package org.apache.commons.math3.stat.descriptive;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.apache.commons.math3.exception.MathIllegalStateException;
/*     */ import org.apache.commons.math3.exception.NullArgumentException;
/*     */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*     */ import org.apache.commons.math3.stat.descriptive.moment.FirstMoment;
/*     */ import org.apache.commons.math3.stat.descriptive.moment.GeometricMean;
/*     */ import org.apache.commons.math3.stat.descriptive.moment.Mean;
/*     */ import org.apache.commons.math3.stat.descriptive.moment.SecondMoment;
/*     */ import org.apache.commons.math3.stat.descriptive.moment.Variance;
/*     */ import org.apache.commons.math3.stat.descriptive.rank.Max;
/*     */ import org.apache.commons.math3.stat.descriptive.rank.Min;
/*     */ import org.apache.commons.math3.stat.descriptive.summary.Sum;
/*     */ import org.apache.commons.math3.stat.descriptive.summary.SumOfLogs;
/*     */ import org.apache.commons.math3.stat.descriptive.summary.SumOfSquares;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ import org.apache.commons.math3.util.MathUtils;
/*     */ import org.apache.commons.math3.util.Precision;
/*     */ 
/*     */ public class SummaryStatistics implements StatisticalSummary, Serializable {
/*     */   private static final long serialVersionUID = -2021321786743555871L;
/*     */   
/*  67 */   private long n = 0L;
/*     */   
/*  70 */   private SecondMoment secondMoment = new SecondMoment();
/*     */   
/*  73 */   private Sum sum = new Sum();
/*     */   
/*  76 */   private SumOfSquares sumsq = new SumOfSquares();
/*     */   
/*  79 */   private Min min = new Min();
/*     */   
/*  82 */   private Max max = new Max();
/*     */   
/*  85 */   private SumOfLogs sumLog = new SumOfLogs();
/*     */   
/*  88 */   private GeometricMean geoMean = new GeometricMean(this.sumLog);
/*     */   
/*  91 */   private Mean mean = new Mean((FirstMoment)this.secondMoment);
/*     */   
/*  94 */   private Variance variance = new Variance(this.secondMoment);
/*     */   
/*  97 */   private StorelessUnivariateStatistic sumImpl = (StorelessUnivariateStatistic)this.sum;
/*     */   
/* 100 */   private StorelessUnivariateStatistic sumsqImpl = (StorelessUnivariateStatistic)this.sumsq;
/*     */   
/* 103 */   private StorelessUnivariateStatistic minImpl = (StorelessUnivariateStatistic)this.min;
/*     */   
/* 106 */   private StorelessUnivariateStatistic maxImpl = (StorelessUnivariateStatistic)this.max;
/*     */   
/* 109 */   private StorelessUnivariateStatistic sumLogImpl = (StorelessUnivariateStatistic)this.sumLog;
/*     */   
/* 112 */   private StorelessUnivariateStatistic geoMeanImpl = (StorelessUnivariateStatistic)this.geoMean;
/*     */   
/* 115 */   private StorelessUnivariateStatistic meanImpl = (StorelessUnivariateStatistic)this.mean;
/*     */   
/* 118 */   private StorelessUnivariateStatistic varianceImpl = (StorelessUnivariateStatistic)this.variance;
/*     */   
/*     */   public SummaryStatistics() {}
/*     */   
/*     */   public SummaryStatistics(SummaryStatistics original) {
/* 132 */     copy(original, this);
/*     */   }
/*     */   
/*     */   public StatisticalSummary getSummary() {
/* 141 */     return new StatisticalSummaryValues(getMean(), getVariance(), getN(), getMax(), getMin(), getSum());
/*     */   }
/*     */   
/*     */   public void addValue(double value) {
/* 150 */     this.sumImpl.increment(value);
/* 151 */     this.sumsqImpl.increment(value);
/* 152 */     this.minImpl.increment(value);
/* 153 */     this.maxImpl.increment(value);
/* 154 */     this.sumLogImpl.increment(value);
/* 155 */     this.secondMoment.increment(value);
/* 158 */     if (this.meanImpl != this.mean)
/* 159 */       this.meanImpl.increment(value); 
/* 161 */     if (this.varianceImpl != this.variance)
/* 162 */       this.varianceImpl.increment(value); 
/* 164 */     if (this.geoMeanImpl != this.geoMean)
/* 165 */       this.geoMeanImpl.increment(value); 
/* 167 */     this.n++;
/*     */   }
/*     */   
/*     */   public long getN() {
/* 175 */     return this.n;
/*     */   }
/*     */   
/*     */   public double getSum() {
/* 183 */     return this.sumImpl.getResult();
/*     */   }
/*     */   
/*     */   public double getSumsq() {
/* 194 */     return this.sumsqImpl.getResult();
/*     */   }
/*     */   
/*     */   public double getMean() {
/* 205 */     return this.meanImpl.getResult();
/*     */   }
/*     */   
/*     */   public double getStandardDeviation() {
/* 216 */     double stdDev = Double.NaN;
/* 217 */     if (getN() > 0L)
/* 218 */       if (getN() > 1L) {
/* 219 */         stdDev = FastMath.sqrt(getVariance());
/*     */       } else {
/* 221 */         stdDev = 0.0D;
/*     */       }  
/* 224 */     return stdDev;
/*     */   }
/*     */   
/*     */   public double getVariance() {
/* 239 */     return this.varianceImpl.getResult();
/*     */   }
/*     */   
/*     */   public double getPopulationVariance() {
/* 251 */     Variance populationVariance = new Variance(this.secondMoment);
/* 252 */     populationVariance.setBiasCorrected(false);
/* 253 */     return populationVariance.getResult();
/*     */   }
/*     */   
/*     */   public double getMax() {
/* 264 */     return this.maxImpl.getResult();
/*     */   }
/*     */   
/*     */   public double getMin() {
/* 275 */     return this.minImpl.getResult();
/*     */   }
/*     */   
/*     */   public double getGeometricMean() {
/* 286 */     return this.geoMeanImpl.getResult();
/*     */   }
/*     */   
/*     */   public double getSumOfLogs() {
/* 298 */     return this.sumLogImpl.getResult();
/*     */   }
/*     */   
/*     */   public double getSecondMoment() {
/* 313 */     return this.secondMoment.getResult();
/*     */   }
/*     */   
/*     */   public String toString() {
/* 324 */     StringBuilder outBuffer = new StringBuilder();
/* 325 */     String endl = "\n";
/* 326 */     outBuffer.append("SummaryStatistics:").append(endl);
/* 327 */     outBuffer.append("n: ").append(getN()).append(endl);
/* 328 */     outBuffer.append("min: ").append(getMin()).append(endl);
/* 329 */     outBuffer.append("max: ").append(getMax()).append(endl);
/* 330 */     outBuffer.append("mean: ").append(getMean()).append(endl);
/* 331 */     outBuffer.append("geometric mean: ").append(getGeometricMean()).append(endl);
/* 333 */     outBuffer.append("variance: ").append(getVariance()).append(endl);
/* 334 */     outBuffer.append("sum of squares: ").append(getSumsq()).append(endl);
/* 335 */     outBuffer.append("standard deviation: ").append(getStandardDeviation()).append(endl);
/* 337 */     outBuffer.append("sum of logs: ").append(getSumOfLogs()).append(endl);
/* 338 */     return outBuffer.toString();
/*     */   }
/*     */   
/*     */   public void clear() {
/* 345 */     this.n = 0L;
/* 346 */     this.minImpl.clear();
/* 347 */     this.maxImpl.clear();
/* 348 */     this.sumImpl.clear();
/* 349 */     this.sumLogImpl.clear();
/* 350 */     this.sumsqImpl.clear();
/* 351 */     this.geoMeanImpl.clear();
/* 352 */     this.secondMoment.clear();
/* 353 */     if (this.meanImpl != this.mean)
/* 354 */       this.meanImpl.clear(); 
/* 356 */     if (this.varianceImpl != this.variance)
/* 357 */       this.varianceImpl.clear(); 
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/* 370 */     if (object == this)
/* 371 */       return true; 
/* 373 */     if (!(object instanceof SummaryStatistics))
/* 374 */       return false; 
/* 376 */     SummaryStatistics stat = (SummaryStatistics)object;
/* 377 */     return (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && Precision.equalsIncludingNaN(stat.getMax(), getMax()) && Precision.equalsIncludingNaN(stat.getMean(), getMean()) && Precision.equalsIncludingNaN(stat.getMin(), getMin()) && Precision.equalsIncludingNaN((float)stat.getN(), (float)getN()) && Precision.equalsIncludingNaN(stat.getSum(), getSum()) && Precision.equalsIncludingNaN(stat.getSumsq(), getSumsq()) && Precision.equalsIncludingNaN(stat.getVariance(), getVariance()));
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 393 */     int result = 31 + MathUtils.hash(getGeometricMean());
/* 394 */     result = result * 31 + MathUtils.hash(getGeometricMean());
/* 395 */     result = result * 31 + MathUtils.hash(getMax());
/* 396 */     result = result * 31 + MathUtils.hash(getMean());
/* 397 */     result = result * 31 + MathUtils.hash(getMin());
/* 398 */     result = result * 31 + MathUtils.hash(getN());
/* 399 */     result = result * 31 + MathUtils.hash(getSum());
/* 400 */     result = result * 31 + MathUtils.hash(getSumsq());
/* 401 */     result = result * 31 + MathUtils.hash(getVariance());
/* 402 */     return result;
/*     */   }
/*     */   
/*     */   public StorelessUnivariateStatistic getSumImpl() {
/* 412 */     return this.sumImpl;
/*     */   }
/*     */   
/*     */   public void setSumImpl(StorelessUnivariateStatistic sumImpl) {
/* 431 */     checkEmpty();
/* 432 */     this.sumImpl = sumImpl;
/*     */   }
/*     */   
/*     */   public StorelessUnivariateStatistic getSumsqImpl() {
/* 441 */     return this.sumsqImpl;
/*     */   }
/*     */   
/*     */   public void setSumsqImpl(StorelessUnivariateStatistic sumsqImpl) {
/* 460 */     checkEmpty();
/* 461 */     this.sumsqImpl = sumsqImpl;
/*     */   }
/*     */   
/*     */   public StorelessUnivariateStatistic getMinImpl() {
/* 470 */     return this.minImpl;
/*     */   }
/*     */   
/*     */   public void setMinImpl(StorelessUnivariateStatistic minImpl) {
/* 489 */     checkEmpty();
/* 490 */     this.minImpl = minImpl;
/*     */   }
/*     */   
/*     */   public StorelessUnivariateStatistic getMaxImpl() {
/* 499 */     return this.maxImpl;
/*     */   }
/*     */   
/*     */   public void setMaxImpl(StorelessUnivariateStatistic maxImpl) {
/* 518 */     checkEmpty();
/* 519 */     this.maxImpl = maxImpl;
/*     */   }
/*     */   
/*     */   public StorelessUnivariateStatistic getSumLogImpl() {
/* 528 */     return this.sumLogImpl;
/*     */   }
/*     */   
/*     */   public void setSumLogImpl(StorelessUnivariateStatistic sumLogImpl) {
/* 547 */     checkEmpty();
/* 548 */     this.sumLogImpl = sumLogImpl;
/* 549 */     this.geoMean.setSumLogImpl(sumLogImpl);
/*     */   }
/*     */   
/*     */   public StorelessUnivariateStatistic getGeoMeanImpl() {
/* 558 */     return this.geoMeanImpl;
/*     */   }
/*     */   
/*     */   public void setGeoMeanImpl(StorelessUnivariateStatistic geoMeanImpl) {
/* 577 */     checkEmpty();
/* 578 */     this.geoMeanImpl = geoMeanImpl;
/*     */   }
/*     */   
/*     */   public StorelessUnivariateStatistic getMeanImpl() {
/* 587 */     return this.meanImpl;
/*     */   }
/*     */   
/*     */   public void setMeanImpl(StorelessUnivariateStatistic meanImpl) {
/* 606 */     checkEmpty();
/* 607 */     this.meanImpl = meanImpl;
/*     */   }
/*     */   
/*     */   public StorelessUnivariateStatistic getVarianceImpl() {
/* 616 */     return this.varianceImpl;
/*     */   }
/*     */   
/*     */   public void setVarianceImpl(StorelessUnivariateStatistic varianceImpl) {
/* 635 */     checkEmpty();
/* 636 */     this.varianceImpl = varianceImpl;
/*     */   }
/*     */   
/*     */   private void checkEmpty() {
/* 643 */     if (this.n > 0L)
/* 644 */       throw new MathIllegalStateException(LocalizedFormats.VALUES_ADDED_BEFORE_CONFIGURING_STATISTIC, new Object[] { Long.valueOf(this.n) }); 
/*     */   }
/*     */   
/*     */   public SummaryStatistics copy() {
/* 655 */     SummaryStatistics result = new SummaryStatistics();
/* 656 */     copy(this, result);
/* 657 */     return result;
/*     */   }
/*     */   
/*     */   public static void copy(SummaryStatistics source, SummaryStatistics dest) throws NullArgumentException {
/* 670 */     MathUtils.checkNotNull(source);
/* 671 */     MathUtils.checkNotNull(dest);
/* 672 */     dest.maxImpl = source.maxImpl.copy();
/* 673 */     dest.minImpl = source.minImpl.copy();
/* 674 */     dest.sumImpl = source.sumImpl.copy();
/* 675 */     dest.sumLogImpl = source.sumLogImpl.copy();
/* 676 */     dest.sumsqImpl = source.sumsqImpl.copy();
/* 677 */     dest.secondMoment = source.secondMoment.copy();
/* 678 */     dest.n = source.n;
/* 681 */     if (source.getVarianceImpl() instanceof Variance) {
/* 682 */       dest.varianceImpl = (StorelessUnivariateStatistic)new Variance(dest.secondMoment);
/*     */     } else {
/* 684 */       dest.varianceImpl = source.varianceImpl.copy();
/*     */     } 
/* 686 */     if (source.meanImpl instanceof Mean) {
/* 687 */       dest.meanImpl = (StorelessUnivariateStatistic)new Mean((FirstMoment)dest.secondMoment);
/*     */     } else {
/* 689 */       dest.meanImpl = source.meanImpl.copy();
/*     */     } 
/* 691 */     if (source.getGeoMeanImpl() instanceof GeometricMean) {
/* 692 */       dest.geoMeanImpl = (StorelessUnivariateStatistic)new GeometricMean((SumOfLogs)dest.sumLogImpl);
/*     */     } else {
/* 694 */       dest.geoMeanImpl = source.geoMeanImpl.copy();
/*     */     } 
/* 699 */     if (source.geoMean == source.geoMeanImpl) {
/* 700 */       dest.geoMean = (GeometricMean)dest.geoMeanImpl;
/*     */     } else {
/* 702 */       GeometricMean.copy(source.geoMean, dest.geoMean);
/*     */     } 
/* 704 */     if (source.max == source.maxImpl) {
/* 705 */       dest.max = (Max)dest.maxImpl;
/*     */     } else {
/* 707 */       Max.copy(source.max, dest.max);
/*     */     } 
/* 709 */     if (source.mean == source.meanImpl) {
/* 710 */       dest.mean = (Mean)dest.meanImpl;
/*     */     } else {
/* 712 */       Mean.copy(source.mean, dest.mean);
/*     */     } 
/* 714 */     if (source.min == source.minImpl) {
/* 715 */       dest.min = (Min)dest.minImpl;
/*     */     } else {
/* 717 */       Min.copy(source.min, dest.min);
/*     */     } 
/* 719 */     if (source.sum == source.sumImpl) {
/* 720 */       dest.sum = (Sum)dest.sumImpl;
/*     */     } else {
/* 722 */       Sum.copy(source.sum, dest.sum);
/*     */     } 
/* 724 */     if (source.variance == source.varianceImpl) {
/* 725 */       dest.variance = (Variance)dest.varianceImpl;
/*     */     } else {
/* 727 */       Variance.copy(source.variance, dest.variance);
/*     */     } 
/* 729 */     if (source.sumLog == source.sumLogImpl) {
/* 730 */       dest.sumLog = (SumOfLogs)dest.sumLogImpl;
/*     */     } else {
/* 732 */       SumOfLogs.copy(source.sumLog, dest.sumLog);
/*     */     } 
/* 734 */     if (source.sumsq == source.sumsqImpl) {
/* 735 */       dest.sumsq = (SumOfSquares)dest.sumsqImpl;
/*     */     } else {
/* 737 */       SumOfSquares.copy(source.sumsq, dest.sumsq);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\stat\descriptive\SummaryStatistics.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */