/*     */ package org.apache.commons.math3.stat.descriptive;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.util.Arrays;
/*     */ import org.apache.commons.math3.exception.MathIllegalArgumentException;
/*     */ import org.apache.commons.math3.exception.MathIllegalStateException;
/*     */ import org.apache.commons.math3.exception.NullArgumentException;
/*     */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*     */ import org.apache.commons.math3.stat.descriptive.moment.GeometricMean;
/*     */ import org.apache.commons.math3.stat.descriptive.moment.Kurtosis;
/*     */ import org.apache.commons.math3.stat.descriptive.moment.Mean;
/*     */ import org.apache.commons.math3.stat.descriptive.moment.Skewness;
/*     */ import org.apache.commons.math3.stat.descriptive.moment.Variance;
/*     */ import org.apache.commons.math3.stat.descriptive.rank.Max;
/*     */ import org.apache.commons.math3.stat.descriptive.rank.Min;
/*     */ import org.apache.commons.math3.stat.descriptive.rank.Percentile;
/*     */ import org.apache.commons.math3.stat.descriptive.summary.Sum;
/*     */ import org.apache.commons.math3.stat.descriptive.summary.SumOfSquares;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ import org.apache.commons.math3.util.MathUtils;
/*     */ import org.apache.commons.math3.util.ResizableDoubleArray;
/*     */ 
/*     */ public class DescriptiveStatistics implements StatisticalSummary, Serializable {
/*     */   public static final int INFINITE_WINDOW = -1;
/*     */   
/*     */   private static final long serialVersionUID = 4133067267405273064L;
/*     */   
/*     */   private static final String SET_QUANTILE_METHOD_NAME = "setQuantile";
/*     */   
/*  77 */   protected int windowSize = -1;
/*     */   
/*  82 */   private ResizableDoubleArray eDA = new ResizableDoubleArray();
/*     */   
/*  85 */   private UnivariateStatistic meanImpl = (UnivariateStatistic)new Mean();
/*     */   
/*  88 */   private UnivariateStatistic geometricMeanImpl = (UnivariateStatistic)new GeometricMean();
/*     */   
/*  91 */   private UnivariateStatistic kurtosisImpl = (UnivariateStatistic)new Kurtosis();
/*     */   
/*  94 */   private UnivariateStatistic maxImpl = (UnivariateStatistic)new Max();
/*     */   
/*  97 */   private UnivariateStatistic minImpl = (UnivariateStatistic)new Min();
/*     */   
/* 100 */   private UnivariateStatistic percentileImpl = (UnivariateStatistic)new Percentile();
/*     */   
/* 103 */   private UnivariateStatistic skewnessImpl = (UnivariateStatistic)new Skewness();
/*     */   
/* 106 */   private UnivariateStatistic varianceImpl = (UnivariateStatistic)new Variance();
/*     */   
/* 109 */   private UnivariateStatistic sumsqImpl = (UnivariateStatistic)new SumOfSquares();
/*     */   
/* 112 */   private UnivariateStatistic sumImpl = (UnivariateStatistic)new Sum();
/*     */   
/*     */   public DescriptiveStatistics() {}
/*     */   
/*     */   public DescriptiveStatistics(int window) {
/* 126 */     setWindowSize(window);
/*     */   }
/*     */   
/*     */   public DescriptiveStatistics(double[] initialDoubleArray) {
/* 138 */     if (initialDoubleArray != null)
/* 139 */       this.eDA = new ResizableDoubleArray(initialDoubleArray); 
/*     */   }
/*     */   
/*     */   public DescriptiveStatistics(DescriptiveStatistics original) {
/* 150 */     copy(original, this);
/*     */   }
/*     */   
/*     */   public void addValue(double v) {
/* 162 */     if (this.windowSize != -1) {
/* 163 */       if (getN() == this.windowSize) {
/* 164 */         this.eDA.addElementRolling(v);
/* 165 */       } else if (getN() < this.windowSize) {
/* 166 */         this.eDA.addElement(v);
/*     */       } 
/*     */     } else {
/* 169 */       this.eDA.addElement(v);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void removeMostRecentValue() {
/* 177 */     this.eDA.discardMostRecentElements(1);
/*     */   }
/*     */   
/*     */   public double replaceMostRecentValue(double v) {
/* 188 */     return this.eDA.substituteMostRecentElement(v);
/*     */   }
/*     */   
/*     */   public double getMean() {
/* 197 */     return apply(this.meanImpl);
/*     */   }
/*     */   
/*     */   public double getGeometricMean() {
/* 207 */     return apply(this.geometricMeanImpl);
/*     */   }
/*     */   
/*     */   public double getVariance() {
/* 221 */     return apply(this.varianceImpl);
/*     */   }
/*     */   
/*     */   public double getPopulationVariance() {
/* 232 */     return apply((UnivariateStatistic)new Variance(false));
/*     */   }
/*     */   
/*     */   public double getStandardDeviation() {
/* 241 */     double stdDev = Double.NaN;
/* 242 */     if (getN() > 0L)
/* 243 */       if (getN() > 1L) {
/* 244 */         stdDev = FastMath.sqrt(getVariance());
/*     */       } else {
/* 246 */         stdDev = 0.0D;
/*     */       }  
/* 249 */     return stdDev;
/*     */   }
/*     */   
/*     */   public double getSkewness() {
/* 259 */     return apply(this.skewnessImpl);
/*     */   }
/*     */   
/*     */   public double getKurtosis() {
/* 269 */     return apply(this.kurtosisImpl);
/*     */   }
/*     */   
/*     */   public double getMax() {
/* 277 */     return apply(this.maxImpl);
/*     */   }
/*     */   
/*     */   public double getMin() {
/* 285 */     return apply(this.minImpl);
/*     */   }
/*     */   
/*     */   public long getN() {
/* 293 */     return this.eDA.getNumElements();
/*     */   }
/*     */   
/*     */   public double getSum() {
/* 301 */     return apply(this.sumImpl);
/*     */   }
/*     */   
/*     */   public double getSumsq() {
/* 310 */     return apply(this.sumsqImpl);
/*     */   }
/*     */   
/*     */   public void clear() {
/* 317 */     this.eDA.clear();
/*     */   }
/*     */   
/*     */   public int getWindowSize() {
/* 328 */     return this.windowSize;
/*     */   }
/*     */   
/*     */   public void setWindowSize(int windowSize) {
/* 341 */     if (windowSize < 1 && 
/* 342 */       windowSize != -1)
/* 343 */       throw new MathIllegalArgumentException(LocalizedFormats.NOT_POSITIVE_WINDOW_SIZE, new Object[] { Integer.valueOf(windowSize) }); 
/* 348 */     this.windowSize = windowSize;
/* 353 */     if (windowSize != -1 && windowSize < this.eDA.getNumElements())
/* 354 */       this.eDA.discardFrontElements(this.eDA.getNumElements() - windowSize); 
/*     */   }
/*     */   
/*     */   public double[] getValues() {
/* 368 */     return this.eDA.getElements();
/*     */   }
/*     */   
/*     */   public double[] getSortedValues() {
/* 380 */     double[] sort = getValues();
/* 381 */     Arrays.sort(sort);
/* 382 */     return sort;
/*     */   }
/*     */   
/*     */   public double getElement(int index) {
/* 391 */     return this.eDA.getElement(index);
/*     */   }
/*     */   
/*     */   public double getPercentile(double p) {
/* 413 */     if (this.percentileImpl instanceof Percentile) {
/* 414 */       ((Percentile)this.percentileImpl).setQuantile(p);
/*     */     } else {
/*     */       try {
/* 417 */         this.percentileImpl.getClass().getMethod("setQuantile", new Class[] { double.class }).invoke(this.percentileImpl, new Object[] { Double.valueOf(p) });
/* 420 */       } catch (NoSuchMethodException e1) {
/* 421 */         throw new MathIllegalStateException(LocalizedFormats.PERCENTILE_IMPLEMENTATION_UNSUPPORTED_METHOD, new Object[] { this.percentileImpl.getClass().getName(), "setQuantile" });
/* 424 */       } catch (IllegalAccessException e2) {
/* 425 */         throw new MathIllegalStateException(LocalizedFormats.PERCENTILE_IMPLEMENTATION_CANNOT_ACCESS_METHOD, new Object[] { "setQuantile", this.percentileImpl.getClass().getName() });
/* 428 */       } catch (InvocationTargetException e3) {
/* 429 */         throw new IllegalStateException(e3.getCause());
/*     */       } 
/*     */     } 
/* 432 */     return apply(this.percentileImpl);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 444 */     StringBuilder outBuffer = new StringBuilder();
/* 445 */     String endl = "\n";
/* 446 */     outBuffer.append("DescriptiveStatistics:").append(endl);
/* 447 */     outBuffer.append("n: ").append(getN()).append(endl);
/* 448 */     outBuffer.append("min: ").append(getMin()).append(endl);
/* 449 */     outBuffer.append("max: ").append(getMax()).append(endl);
/* 450 */     outBuffer.append("mean: ").append(getMean()).append(endl);
/* 451 */     outBuffer.append("std dev: ").append(getStandardDeviation()).append(endl);
/* 453 */     outBuffer.append("median: ").append(getPercentile(50.0D)).append(endl);
/* 454 */     outBuffer.append("skewness: ").append(getSkewness()).append(endl);
/* 455 */     outBuffer.append("kurtosis: ").append(getKurtosis()).append(endl);
/* 456 */     return outBuffer.toString();
/*     */   }
/*     */   
/*     */   public double apply(UnivariateStatistic stat) {
/* 465 */     return stat.evaluate(this.eDA.getInternalValues(), this.eDA.start(), this.eDA.getNumElements());
/*     */   }
/*     */   
/*     */   public synchronized UnivariateStatistic getMeanImpl() {
/* 477 */     return this.meanImpl;
/*     */   }
/*     */   
/*     */   public synchronized void setMeanImpl(UnivariateStatistic meanImpl) {
/* 488 */     this.meanImpl = meanImpl;
/*     */   }
/*     */   
/*     */   public synchronized UnivariateStatistic getGeometricMeanImpl() {
/* 498 */     return this.geometricMeanImpl;
/*     */   }
/*     */   
/*     */   public synchronized void setGeometricMeanImpl(UnivariateStatistic geometricMeanImpl) {
/* 510 */     this.geometricMeanImpl = geometricMeanImpl;
/*     */   }
/*     */   
/*     */   public synchronized UnivariateStatistic getKurtosisImpl() {
/* 520 */     return this.kurtosisImpl;
/*     */   }
/*     */   
/*     */   public synchronized void setKurtosisImpl(UnivariateStatistic kurtosisImpl) {
/* 531 */     this.kurtosisImpl = kurtosisImpl;
/*     */   }
/*     */   
/*     */   public synchronized UnivariateStatistic getMaxImpl() {
/* 541 */     return this.maxImpl;
/*     */   }
/*     */   
/*     */   public synchronized void setMaxImpl(UnivariateStatistic maxImpl) {
/* 552 */     this.maxImpl = maxImpl;
/*     */   }
/*     */   
/*     */   public synchronized UnivariateStatistic getMinImpl() {
/* 562 */     return this.minImpl;
/*     */   }
/*     */   
/*     */   public synchronized void setMinImpl(UnivariateStatistic minImpl) {
/* 573 */     this.minImpl = minImpl;
/*     */   }
/*     */   
/*     */   public synchronized UnivariateStatistic getPercentileImpl() {
/* 583 */     return this.percentileImpl;
/*     */   }
/*     */   
/*     */   public synchronized void setPercentileImpl(UnivariateStatistic percentileImpl) {
/*     */     try {
/* 600 */       percentileImpl.getClass().getMethod("setQuantile", new Class[] { double.class }).invoke(percentileImpl, new Object[] { Double.valueOf(50.0D) });
/* 603 */     } catch (NoSuchMethodException e1) {
/* 604 */       throw new MathIllegalArgumentException(LocalizedFormats.PERCENTILE_IMPLEMENTATION_UNSUPPORTED_METHOD, new Object[] { percentileImpl.getClass().getName(), "setQuantile" });
/* 607 */     } catch (IllegalAccessException e2) {
/* 608 */       throw new MathIllegalArgumentException(LocalizedFormats.PERCENTILE_IMPLEMENTATION_CANNOT_ACCESS_METHOD, new Object[] { "setQuantile", percentileImpl.getClass().getName() });
/* 611 */     } catch (InvocationTargetException e3) {
/* 612 */       throw new IllegalArgumentException(e3.getCause());
/*     */     } 
/* 614 */     this.percentileImpl = percentileImpl;
/*     */   }
/*     */   
/*     */   public synchronized UnivariateStatistic getSkewnessImpl() {
/* 624 */     return this.skewnessImpl;
/*     */   }
/*     */   
/*     */   public synchronized void setSkewnessImpl(UnivariateStatistic skewnessImpl) {
/* 636 */     this.skewnessImpl = skewnessImpl;
/*     */   }
/*     */   
/*     */   public synchronized UnivariateStatistic getVarianceImpl() {
/* 646 */     return this.varianceImpl;
/*     */   }
/*     */   
/*     */   public synchronized void setVarianceImpl(UnivariateStatistic varianceImpl) {
/* 658 */     this.varianceImpl = varianceImpl;
/*     */   }
/*     */   
/*     */   public synchronized UnivariateStatistic getSumsqImpl() {
/* 668 */     return this.sumsqImpl;
/*     */   }
/*     */   
/*     */   public synchronized void setSumsqImpl(UnivariateStatistic sumsqImpl) {
/* 679 */     this.sumsqImpl = sumsqImpl;
/*     */   }
/*     */   
/*     */   public synchronized UnivariateStatistic getSumImpl() {
/* 689 */     return this.sumImpl;
/*     */   }
/*     */   
/*     */   public synchronized void setSumImpl(UnivariateStatistic sumImpl) {
/* 700 */     this.sumImpl = sumImpl;
/*     */   }
/*     */   
/*     */   public DescriptiveStatistics copy() {
/* 709 */     DescriptiveStatistics result = new DescriptiveStatistics();
/* 710 */     copy(this, result);
/* 711 */     return result;
/*     */   }
/*     */   
/*     */   public static void copy(DescriptiveStatistics source, DescriptiveStatistics dest) throws NullArgumentException {
/* 724 */     MathUtils.checkNotNull(source);
/* 725 */     MathUtils.checkNotNull(dest);
/* 727 */     dest.eDA = source.eDA.copy();
/* 728 */     dest.windowSize = source.windowSize;
/* 731 */     dest.maxImpl = source.maxImpl.copy();
/* 732 */     dest.meanImpl = source.meanImpl.copy();
/* 733 */     dest.minImpl = source.minImpl.copy();
/* 734 */     dest.sumImpl = source.sumImpl.copy();
/* 735 */     dest.varianceImpl = source.varianceImpl.copy();
/* 736 */     dest.sumsqImpl = source.sumsqImpl.copy();
/* 737 */     dest.geometricMeanImpl = source.geometricMeanImpl.copy();
/* 738 */     dest.kurtosisImpl = source.kurtosisImpl;
/* 739 */     dest.skewnessImpl = source.skewnessImpl;
/* 740 */     dest.percentileImpl = source.percentileImpl;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\stat\descriptive\DescriptiveStatistics.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */