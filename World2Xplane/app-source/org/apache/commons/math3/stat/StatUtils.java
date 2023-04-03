/*     */ package org.apache.commons.math3.stat;
/*     */ 
/*     */ import org.apache.commons.math3.exception.DimensionMismatchException;
/*     */ import org.apache.commons.math3.exception.NoDataException;
/*     */ import org.apache.commons.math3.exception.NumberIsTooSmallException;
/*     */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*     */ import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
/*     */ import org.apache.commons.math3.stat.descriptive.UnivariateStatistic;
/*     */ import org.apache.commons.math3.stat.descriptive.moment.GeometricMean;
/*     */ import org.apache.commons.math3.stat.descriptive.moment.Mean;
/*     */ import org.apache.commons.math3.stat.descriptive.moment.Variance;
/*     */ import org.apache.commons.math3.stat.descriptive.rank.Max;
/*     */ import org.apache.commons.math3.stat.descriptive.rank.Min;
/*     */ import org.apache.commons.math3.stat.descriptive.rank.Percentile;
/*     */ import org.apache.commons.math3.stat.descriptive.summary.Product;
/*     */ import org.apache.commons.math3.stat.descriptive.summary.Sum;
/*     */ import org.apache.commons.math3.stat.descriptive.summary.SumOfLogs;
/*     */ import org.apache.commons.math3.stat.descriptive.summary.SumOfSquares;
/*     */ 
/*     */ public final class StatUtils {
/*  45 */   private static final UnivariateStatistic SUM = (UnivariateStatistic)new Sum();
/*     */   
/*  48 */   private static final UnivariateStatistic SUM_OF_SQUARES = (UnivariateStatistic)new SumOfSquares();
/*     */   
/*  51 */   private static final UnivariateStatistic PRODUCT = (UnivariateStatistic)new Product();
/*     */   
/*  54 */   private static final UnivariateStatistic SUM_OF_LOGS = (UnivariateStatistic)new SumOfLogs();
/*     */   
/*  57 */   private static final UnivariateStatistic MIN = (UnivariateStatistic)new Min();
/*     */   
/*  60 */   private static final UnivariateStatistic MAX = (UnivariateStatistic)new Max();
/*     */   
/*  63 */   private static final UnivariateStatistic MEAN = (UnivariateStatistic)new Mean();
/*     */   
/*  66 */   private static final Variance VARIANCE = new Variance();
/*     */   
/*  69 */   private static final Percentile PERCENTILE = new Percentile();
/*     */   
/*  72 */   private static final GeometricMean GEOMETRIC_MEAN = new GeometricMean();
/*     */   
/*     */   public static double sum(double[] values) {
/*  93 */     return SUM.evaluate(values);
/*     */   }
/*     */   
/*     */   public static double sum(double[] values, int begin, int length) {
/* 112 */     return SUM.evaluate(values, begin, length);
/*     */   }
/*     */   
/*     */   public static double sumSq(double[] values) {
/* 127 */     return SUM_OF_SQUARES.evaluate(values);
/*     */   }
/*     */   
/*     */   public static double sumSq(double[] values, int begin, int length) {
/* 146 */     return SUM_OF_SQUARES.evaluate(values, begin, length);
/*     */   }
/*     */   
/*     */   public static double product(double[] values) {
/* 160 */     return PRODUCT.evaluate(values);
/*     */   }
/*     */   
/*     */   public static double product(double[] values, int begin, int length) {
/* 179 */     return PRODUCT.evaluate(values, begin, length);
/*     */   }
/*     */   
/*     */   public static double sumLog(double[] values) {
/* 197 */     return SUM_OF_LOGS.evaluate(values);
/*     */   }
/*     */   
/*     */   public static double sumLog(double[] values, int begin, int length) {
/* 220 */     return SUM_OF_LOGS.evaluate(values, begin, length);
/*     */   }
/*     */   
/*     */   public static double mean(double[] values) {
/* 237 */     return MEAN.evaluate(values);
/*     */   }
/*     */   
/*     */   public static double mean(double[] values, int begin, int length) {
/* 259 */     return MEAN.evaluate(values, begin, length);
/*     */   }
/*     */   
/*     */   public static double geometricMean(double[] values) {
/* 276 */     return GEOMETRIC_MEAN.evaluate(values);
/*     */   }
/*     */   
/*     */   public static double geometricMean(double[] values, int begin, int length) {
/* 298 */     return GEOMETRIC_MEAN.evaluate(values, begin, length);
/*     */   }
/*     */   
/*     */   public static double variance(double[] values) {
/* 322 */     return VARIANCE.evaluate(values);
/*     */   }
/*     */   
/*     */   public static double variance(double[] values, int begin, int length) {
/* 351 */     return VARIANCE.evaluate(values, begin, length);
/*     */   }
/*     */   
/*     */   public static double variance(double[] values, double mean, int begin, int length) {
/* 386 */     return VARIANCE.evaluate(values, mean, begin, length);
/*     */   }
/*     */   
/*     */   public static double variance(double[] values, double mean) {
/* 416 */     return VARIANCE.evaluate(values, mean);
/*     */   }
/*     */   
/*     */   public static double populationVariance(double[] values) {
/* 436 */     return (new Variance(false)).evaluate(values);
/*     */   }
/*     */   
/*     */   public static double populationVariance(double[] values, int begin, int length) {
/* 462 */     return (new Variance(false)).evaluate(values, begin, length);
/*     */   }
/*     */   
/*     */   public static double populationVariance(double[] values, double mean, int begin, int length) {
/* 494 */     return (new Variance(false)).evaluate(values, mean, begin, length);
/*     */   }
/*     */   
/*     */   public static double populationVariance(double[] values, double mean) {
/* 521 */     return (new Variance(false)).evaluate(values, mean);
/*     */   }
/*     */   
/*     */   public static double max(double[] values) {
/* 542 */     return MAX.evaluate(values);
/*     */   }
/*     */   
/*     */   public static double max(double[] values, int begin, int length) {
/* 569 */     return MAX.evaluate(values, begin, length);
/*     */   }
/*     */   
/*     */   public static double min(double[] values) {
/* 590 */     return MIN.evaluate(values);
/*     */   }
/*     */   
/*     */   public static double min(double[] values, int begin, int length) {
/* 617 */     return MIN.evaluate(values, begin, length);
/*     */   }
/*     */   
/*     */   public static double percentile(double[] values, double p) {
/* 644 */     return PERCENTILE.evaluate(values, p);
/*     */   }
/*     */   
/*     */   public static double percentile(double[] values, int begin, int length, double p) {
/* 676 */     return PERCENTILE.evaluate(values, begin, length, p);
/*     */   }
/*     */   
/*     */   public static double sumDifference(double[] sample1, double[] sample2) {
/* 691 */     int n = sample1.length;
/* 692 */     if (n != sample2.length)
/* 693 */       throw new DimensionMismatchException(n, sample2.length); 
/* 695 */     if (n <= 0)
/* 696 */       throw new NoDataException(LocalizedFormats.INSUFFICIENT_DIMENSION); 
/* 698 */     double result = 0.0D;
/* 699 */     for (int i = 0; i < n; i++)
/* 700 */       result += sample1[i] - sample2[i]; 
/* 702 */     return result;
/*     */   }
/*     */   
/*     */   public static double meanDifference(double[] sample1, double[] sample2) {
/* 717 */     return sumDifference(sample1, sample2) / sample1.length;
/*     */   }
/*     */   
/*     */   public static double varianceDifference(double[] sample1, double[] sample2, double meanDifference) {
/* 736 */     double sum1 = 0.0D;
/* 737 */     double sum2 = 0.0D;
/* 738 */     double diff = 0.0D;
/* 739 */     int n = sample1.length;
/* 740 */     if (n != sample2.length)
/* 741 */       throw new DimensionMismatchException(n, sample2.length); 
/* 743 */     if (n < 2)
/* 744 */       throw new NumberIsTooSmallException(Integer.valueOf(n), Integer.valueOf(2), true); 
/* 746 */     for (int i = 0; i < n; i++) {
/* 747 */       diff = sample1[i] - sample2[i];
/* 748 */       sum1 += (diff - meanDifference) * (diff - meanDifference);
/* 749 */       sum2 += diff - meanDifference;
/*     */     } 
/* 751 */     return (sum1 - sum2 * sum2 / n) / (n - 1);
/*     */   }
/*     */   
/*     */   public static double[] normalize(double[] sample) {
/* 762 */     DescriptiveStatistics stats = new DescriptiveStatistics();
/* 765 */     for (int i = 0; i < sample.length; i++)
/* 766 */       stats.addValue(sample[i]); 
/* 770 */     double mean = stats.getMean();
/* 771 */     double standardDeviation = stats.getStandardDeviation();
/* 774 */     double[] standardizedSample = new double[sample.length];
/* 776 */     for (int j = 0; j < sample.length; j++)
/* 778 */       standardizedSample[j] = (sample[j] - mean) / standardDeviation; 
/* 780 */     return standardizedSample;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\stat\StatUtils.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */