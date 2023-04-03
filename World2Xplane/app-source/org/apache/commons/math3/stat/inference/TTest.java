/*      */ package org.apache.commons.math3.stat.inference;
/*      */ 
/*      */ import org.apache.commons.math3.distribution.TDistribution;
/*      */ import org.apache.commons.math3.exception.DimensionMismatchException;
/*      */ import org.apache.commons.math3.exception.MaxCountExceededException;
/*      */ import org.apache.commons.math3.exception.NoDataException;
/*      */ import org.apache.commons.math3.exception.NullArgumentException;
/*      */ import org.apache.commons.math3.exception.NumberIsTooSmallException;
/*      */ import org.apache.commons.math3.exception.OutOfRangeException;
/*      */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*      */ import org.apache.commons.math3.stat.StatUtils;
/*      */ import org.apache.commons.math3.stat.descriptive.StatisticalSummary;
/*      */ import org.apache.commons.math3.util.FastMath;
/*      */ 
/*      */ public class TTest {
/*      */   public double pairedT(double[] sample1, double[] sample2) throws NullArgumentException, NoDataException, DimensionMismatchException, NumberIsTooSmallException {
/*   83 */     checkSampleData(sample1);
/*   84 */     checkSampleData(sample2);
/*   85 */     double meanDifference = StatUtils.meanDifference(sample1, sample2);
/*   86 */     return t(meanDifference, 0.0D, StatUtils.varianceDifference(sample1, sample2, meanDifference), sample1.length);
/*      */   }
/*      */   
/*      */   public double pairedTTest(double[] sample1, double[] sample2) throws NullArgumentException, NoDataException, DimensionMismatchException, NumberIsTooSmallException, MaxCountExceededException {
/*  132 */     double meanDifference = StatUtils.meanDifference(sample1, sample2);
/*  133 */     return tTest(meanDifference, 0.0D, StatUtils.varianceDifference(sample1, sample2, meanDifference), sample1.length);
/*      */   }
/*      */   
/*      */   public boolean pairedTTest(double[] sample1, double[] sample2, double alpha) throws NullArgumentException, NoDataException, DimensionMismatchException, NumberIsTooSmallException, OutOfRangeException, MaxCountExceededException {
/*  180 */     checkSignificanceLevel(alpha);
/*  181 */     return (pairedTTest(sample1, sample2) < alpha);
/*      */   }
/*      */   
/*      */   public double t(double mu, double[] observed) throws NullArgumentException, NumberIsTooSmallException {
/*  204 */     checkSampleData(observed);
/*  205 */     return t(StatUtils.mean(observed), mu, StatUtils.variance(observed), observed.length);
/*      */   }
/*      */   
/*      */   public double t(double mu, StatisticalSummary sampleStats) throws NullArgumentException, NumberIsTooSmallException {
/*  230 */     checkSampleData(sampleStats);
/*  231 */     return t(sampleStats.getMean(), mu, sampleStats.getVariance(), sampleStats.getN());
/*      */   }
/*      */   
/*      */   public double homoscedasticT(double[] sample1, double[] sample2) throws NullArgumentException, NumberIsTooSmallException {
/*  273 */     checkSampleData(sample1);
/*  274 */     checkSampleData(sample2);
/*  275 */     return homoscedasticT(StatUtils.mean(sample1), StatUtils.mean(sample2), StatUtils.variance(sample1), StatUtils.variance(sample2), sample1.length, sample2.length);
/*      */   }
/*      */   
/*      */   public double t(double[] sample1, double[] sample2) throws NullArgumentException, NumberIsTooSmallException {
/*  313 */     checkSampleData(sample1);
/*  314 */     checkSampleData(sample2);
/*  315 */     return t(StatUtils.mean(sample1), StatUtils.mean(sample2), StatUtils.variance(sample1), StatUtils.variance(sample2), sample1.length, sample2.length);
/*      */   }
/*      */   
/*      */   public double t(StatisticalSummary sampleStats1, StatisticalSummary sampleStats2) throws NullArgumentException, NumberIsTooSmallException {
/*  357 */     checkSampleData(sampleStats1);
/*  358 */     checkSampleData(sampleStats2);
/*  359 */     return t(sampleStats1.getMean(), sampleStats2.getMean(), sampleStats1.getVariance(), sampleStats2.getVariance(), sampleStats1.getN(), sampleStats2.getN());
/*      */   }
/*      */   
/*      */   public double homoscedasticT(StatisticalSummary sampleStats1, StatisticalSummary sampleStats2) throws NullArgumentException, NumberIsTooSmallException {
/*  405 */     checkSampleData(sampleStats1);
/*  406 */     checkSampleData(sampleStats2);
/*  407 */     return homoscedasticT(sampleStats1.getMean(), sampleStats2.getMean(), sampleStats1.getVariance(), sampleStats2.getVariance(), sampleStats1.getN(), sampleStats2.getN());
/*      */   }
/*      */   
/*      */   public double tTest(double mu, double[] sample) throws NullArgumentException, NumberIsTooSmallException, MaxCountExceededException {
/*  444 */     checkSampleData(sample);
/*  445 */     return tTest(StatUtils.mean(sample), mu, StatUtils.variance(sample), sample.length);
/*      */   }
/*      */   
/*      */   public boolean tTest(double mu, double[] sample, double alpha) throws NullArgumentException, NumberIsTooSmallException, OutOfRangeException, MaxCountExceededException {
/*  491 */     checkSignificanceLevel(alpha);
/*  492 */     return (tTest(mu, sample) < alpha);
/*      */   }
/*      */   
/*      */   public double tTest(double mu, StatisticalSummary sampleStats) throws NullArgumentException, NumberIsTooSmallException, MaxCountExceededException {
/*  529 */     checkSampleData(sampleStats);
/*  530 */     return tTest(sampleStats.getMean(), mu, sampleStats.getVariance(), sampleStats.getN());
/*      */   }
/*      */   
/*      */   public boolean tTest(double mu, StatisticalSummary sampleStats, double alpha) throws NullArgumentException, NumberIsTooSmallException, OutOfRangeException, MaxCountExceededException {
/*  578 */     checkSignificanceLevel(alpha);
/*  579 */     return (tTest(mu, sampleStats) < alpha);
/*      */   }
/*      */   
/*      */   public double tTest(double[] sample1, double[] sample2) throws NullArgumentException, NumberIsTooSmallException, MaxCountExceededException {
/*  624 */     checkSampleData(sample1);
/*  625 */     checkSampleData(sample2);
/*  626 */     return tTest(StatUtils.mean(sample1), StatUtils.mean(sample2), StatUtils.variance(sample1), StatUtils.variance(sample2), sample1.length, sample2.length);
/*      */   }
/*      */   
/*      */   public double homoscedasticTTest(double[] sample1, double[] sample2) throws NullArgumentException, NumberIsTooSmallException, MaxCountExceededException {
/*  670 */     checkSampleData(sample1);
/*  671 */     checkSampleData(sample2);
/*  672 */     return homoscedasticTTest(StatUtils.mean(sample1), StatUtils.mean(sample2), StatUtils.variance(sample1), StatUtils.variance(sample2), sample1.length, sample2.length);
/*      */   }
/*      */   
/*      */   public boolean tTest(double[] sample1, double[] sample2, double alpha) throws NullArgumentException, NumberIsTooSmallException, OutOfRangeException, MaxCountExceededException {
/*  737 */     checkSignificanceLevel(alpha);
/*  738 */     return (tTest(sample1, sample2) < alpha);
/*      */   }
/*      */   
/*      */   public boolean homoscedasticTTest(double[] sample1, double[] sample2, double alpha) throws NullArgumentException, NumberIsTooSmallException, OutOfRangeException, MaxCountExceededException {
/*  800 */     checkSignificanceLevel(alpha);
/*  801 */     return (homoscedasticTTest(sample1, sample2) < alpha);
/*      */   }
/*      */   
/*      */   public double tTest(StatisticalSummary sampleStats1, StatisticalSummary sampleStats2) throws NullArgumentException, NumberIsTooSmallException, MaxCountExceededException {
/*  845 */     checkSampleData(sampleStats1);
/*  846 */     checkSampleData(sampleStats2);
/*  847 */     return tTest(sampleStats1.getMean(), sampleStats2.getMean(), sampleStats1.getVariance(), sampleStats2.getVariance(), sampleStats1.getN(), sampleStats2.getN());
/*      */   }
/*      */   
/*      */   public double homoscedasticTTest(StatisticalSummary sampleStats1, StatisticalSummary sampleStats2) throws NullArgumentException, NumberIsTooSmallException, MaxCountExceededException {
/*  892 */     checkSampleData(sampleStats1);
/*  893 */     checkSampleData(sampleStats2);
/*  894 */     return homoscedasticTTest(sampleStats1.getMean(), sampleStats2.getMean(), sampleStats1.getVariance(), sampleStats2.getVariance(), sampleStats1.getN(), sampleStats2.getN());
/*      */   }
/*      */   
/*      */   public boolean tTest(StatisticalSummary sampleStats1, StatisticalSummary sampleStats2, double alpha) throws NullArgumentException, NumberIsTooSmallException, OutOfRangeException, MaxCountExceededException {
/*  963 */     checkSignificanceLevel(alpha);
/*  964 */     return (tTest(sampleStats1, sampleStats2) < alpha);
/*      */   }
/*      */   
/*      */   protected double df(double v1, double v2, double n1, double n2) {
/*  980 */     return (v1 / n1 + v2 / n2) * (v1 / n1 + v2 / n2) / (v1 * v1 / n1 * n1 * (n1 - 1.0D) + v2 * v2 / n2 * n2 * (n2 - 1.0D));
/*      */   }
/*      */   
/*      */   protected double t(double m, double mu, double v, double n) {
/*  996 */     return (m - mu) / FastMath.sqrt(v / n);
/*      */   }
/*      */   
/*      */   protected double t(double m1, double m2, double v1, double v2, double n1, double n2) {
/* 1015 */     return (m1 - m2) / FastMath.sqrt(v1 / n1 + v2 / n2);
/*      */   }
/*      */   
/*      */   protected double homoscedasticT(double m1, double m2, double v1, double v2, double n1, double n2) {
/* 1033 */     double pooledVariance = ((n1 - 1.0D) * v1 + (n2 - 1.0D) * v2) / (n1 + n2 - 2.0D);
/* 1034 */     return (m1 - m2) / FastMath.sqrt(pooledVariance * (1.0D / n1 + 1.0D / n2));
/*      */   }
/*      */   
/*      */   protected double tTest(double m, double mu, double v, double n) throws MaxCountExceededException {
/* 1051 */     double t = FastMath.abs(t(m, mu, v, n));
/* 1052 */     TDistribution distribution = new TDistribution(n - 1.0D);
/* 1053 */     return 2.0D * distribution.cumulativeProbability(-t);
/*      */   }
/*      */   
/*      */   protected double tTest(double m1, double m2, double v1, double v2, double n1, double n2) throws MaxCountExceededException {
/* 1077 */     double t = FastMath.abs(t(m1, m2, v1, v2, n1, n2));
/* 1078 */     double degreesOfFreedom = df(v1, v2, n1, n2);
/* 1079 */     TDistribution distribution = new TDistribution(degreesOfFreedom);
/* 1080 */     return 2.0D * distribution.cumulativeProbability(-t);
/*      */   }
/*      */   
/*      */   protected double homoscedasticTTest(double m1, double m2, double v1, double v2, double n1, double n2) throws MaxCountExceededException {
/* 1104 */     double t = FastMath.abs(homoscedasticT(m1, m2, v1, v2, n1, n2));
/* 1105 */     double degreesOfFreedom = n1 + n2 - 2.0D;
/* 1106 */     TDistribution distribution = new TDistribution(degreesOfFreedom);
/* 1107 */     return 2.0D * distribution.cumulativeProbability(-t);
/*      */   }
/*      */   
/*      */   private void checkSignificanceLevel(double alpha) throws OutOfRangeException {
/* 1120 */     if (alpha <= 0.0D || alpha > 0.5D)
/* 1121 */       throw new OutOfRangeException(LocalizedFormats.SIGNIFICANCE_LEVEL, Double.valueOf(alpha), Double.valueOf(0.0D), Double.valueOf(0.5D)); 
/*      */   }
/*      */   
/*      */   private void checkSampleData(double[] data) throws NullArgumentException, NumberIsTooSmallException {
/* 1137 */     if (data == null)
/* 1138 */       throw new NullArgumentException(); 
/* 1140 */     if (data.length < 2)
/* 1141 */       throw new NumberIsTooSmallException(LocalizedFormats.INSUFFICIENT_DATA_FOR_T_STATISTIC, Integer.valueOf(data.length), Integer.valueOf(2), true); 
/*      */   }
/*      */   
/*      */   private void checkSampleData(StatisticalSummary stat) throws NullArgumentException, NumberIsTooSmallException {
/* 1158 */     if (stat == null)
/* 1159 */       throw new NullArgumentException(); 
/* 1161 */     if (stat.getN() < 2L)
/* 1162 */       throw new NumberIsTooSmallException(LocalizedFormats.INSUFFICIENT_DATA_FOR_T_STATISTIC, Long.valueOf(stat.getN()), Integer.valueOf(2), true); 
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\stat\inference\TTest.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */