/*     */ package org.apache.commons.math3.stat.inference;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import org.apache.commons.math3.exception.ConvergenceException;
/*     */ import org.apache.commons.math3.exception.DimensionMismatchException;
/*     */ import org.apache.commons.math3.exception.MaxCountExceededException;
/*     */ import org.apache.commons.math3.exception.NoDataException;
/*     */ import org.apache.commons.math3.exception.NotPositiveException;
/*     */ import org.apache.commons.math3.exception.NotStrictlyPositiveException;
/*     */ import org.apache.commons.math3.exception.NullArgumentException;
/*     */ import org.apache.commons.math3.exception.NumberIsTooSmallException;
/*     */ import org.apache.commons.math3.exception.OutOfRangeException;
/*     */ import org.apache.commons.math3.exception.ZeroException;
/*     */ import org.apache.commons.math3.stat.descriptive.StatisticalSummary;
/*     */ 
/*     */ public class TestUtils {
/*  42 */   private static final TTest T_TEST = new TTest();
/*     */   
/*  45 */   private static final ChiSquareTest CHI_SQUARE_TEST = new ChiSquareTest();
/*     */   
/*  48 */   private static final OneWayAnova ONE_WAY_ANANOVA = new OneWayAnova();
/*     */   
/*     */   public static double homoscedasticT(double[] sample1, double[] sample2) throws NullArgumentException, NumberIsTooSmallException {
/*  64 */     return T_TEST.homoscedasticT(sample1, sample2);
/*     */   }
/*     */   
/*     */   public static double homoscedasticT(StatisticalSummary sampleStats1, StatisticalSummary sampleStats2) throws NullArgumentException, NumberIsTooSmallException {
/*  73 */     return T_TEST.homoscedasticT(sampleStats1, sampleStats2);
/*     */   }
/*     */   
/*     */   public static boolean homoscedasticTTest(double[] sample1, double[] sample2, double alpha) throws NullArgumentException, NumberIsTooSmallException, OutOfRangeException, MaxCountExceededException {
/*  83 */     return T_TEST.homoscedasticTTest(sample1, sample2, alpha);
/*     */   }
/*     */   
/*     */   public static double homoscedasticTTest(double[] sample1, double[] sample2) throws NullArgumentException, NumberIsTooSmallException, MaxCountExceededException {
/*  91 */     return T_TEST.homoscedasticTTest(sample1, sample2);
/*     */   }
/*     */   
/*     */   public static double homoscedasticTTest(StatisticalSummary sampleStats1, StatisticalSummary sampleStats2) throws NullArgumentException, NumberIsTooSmallException, MaxCountExceededException {
/* 100 */     return T_TEST.homoscedasticTTest(sampleStats1, sampleStats2);
/*     */   }
/*     */   
/*     */   public static double pairedT(double[] sample1, double[] sample2) throws NullArgumentException, NoDataException, DimensionMismatchException, NumberIsTooSmallException {
/* 109 */     return T_TEST.pairedT(sample1, sample2);
/*     */   }
/*     */   
/*     */   public static boolean pairedTTest(double[] sample1, double[] sample2, double alpha) throws NullArgumentException, NoDataException, DimensionMismatchException, NumberIsTooSmallException, OutOfRangeException, MaxCountExceededException {
/* 119 */     return T_TEST.pairedTTest(sample1, sample2, alpha);
/*     */   }
/*     */   
/*     */   public static double pairedTTest(double[] sample1, double[] sample2) throws NullArgumentException, NoDataException, DimensionMismatchException, NumberIsTooSmallException, MaxCountExceededException {
/* 128 */     return T_TEST.pairedTTest(sample1, sample2);
/*     */   }
/*     */   
/*     */   public static double t(double mu, double[] observed) throws NullArgumentException, NumberIsTooSmallException {
/* 136 */     return T_TEST.t(mu, observed);
/*     */   }
/*     */   
/*     */   public static double t(double mu, StatisticalSummary sampleStats) throws NullArgumentException, NumberIsTooSmallException {
/* 144 */     return T_TEST.t(mu, sampleStats);
/*     */   }
/*     */   
/*     */   public static double t(double[] sample1, double[] sample2) throws NullArgumentException, NumberIsTooSmallException {
/* 152 */     return T_TEST.t(sample1, sample2);
/*     */   }
/*     */   
/*     */   public static double t(StatisticalSummary sampleStats1, StatisticalSummary sampleStats2) throws NullArgumentException, NumberIsTooSmallException {
/* 161 */     return T_TEST.t(sampleStats1, sampleStats2);
/*     */   }
/*     */   
/*     */   public static boolean tTest(double mu, double[] sample, double alpha) throws NullArgumentException, NumberIsTooSmallException, OutOfRangeException, MaxCountExceededException {
/* 170 */     return T_TEST.tTest(mu, sample, alpha);
/*     */   }
/*     */   
/*     */   public static double tTest(double mu, double[] sample) throws NullArgumentException, NumberIsTooSmallException, MaxCountExceededException {
/* 179 */     return T_TEST.tTest(mu, sample);
/*     */   }
/*     */   
/*     */   public static boolean tTest(double mu, StatisticalSummary sampleStats, double alpha) throws NullArgumentException, NumberIsTooSmallException, OutOfRangeException, MaxCountExceededException {
/* 189 */     return T_TEST.tTest(mu, sampleStats, alpha);
/*     */   }
/*     */   
/*     */   public static double tTest(double mu, StatisticalSummary sampleStats) throws NullArgumentException, NumberIsTooSmallException, MaxCountExceededException {
/* 198 */     return T_TEST.tTest(mu, sampleStats);
/*     */   }
/*     */   
/*     */   public static boolean tTest(double[] sample1, double[] sample2, double alpha) throws NullArgumentException, NumberIsTooSmallException, OutOfRangeException, MaxCountExceededException {
/* 208 */     return T_TEST.tTest(sample1, sample2, alpha);
/*     */   }
/*     */   
/*     */   public static double tTest(double[] sample1, double[] sample2) throws NullArgumentException, NumberIsTooSmallException, MaxCountExceededException {
/* 217 */     return T_TEST.tTest(sample1, sample2);
/*     */   }
/*     */   
/*     */   public static boolean tTest(StatisticalSummary sampleStats1, StatisticalSummary sampleStats2, double alpha) throws NullArgumentException, NumberIsTooSmallException, OutOfRangeException, MaxCountExceededException {
/* 228 */     return T_TEST.tTest(sampleStats1, sampleStats2, alpha);
/*     */   }
/*     */   
/*     */   public static double tTest(StatisticalSummary sampleStats1, StatisticalSummary sampleStats2) throws NullArgumentException, NumberIsTooSmallException, MaxCountExceededException {
/* 238 */     return T_TEST.tTest(sampleStats1, sampleStats2);
/*     */   }
/*     */   
/*     */   public static double chiSquare(double[] expected, long[] observed) throws NotPositiveException, NotStrictlyPositiveException, DimensionMismatchException {
/* 247 */     return CHI_SQUARE_TEST.chiSquare(expected, observed);
/*     */   }
/*     */   
/*     */   public static double chiSquare(long[][] counts) throws NullArgumentException, NotPositiveException, DimensionMismatchException {
/* 256 */     return CHI_SQUARE_TEST.chiSquare(counts);
/*     */   }
/*     */   
/*     */   public static boolean chiSquareTest(double[] expected, long[] observed, double alpha) throws NotPositiveException, NotStrictlyPositiveException, DimensionMismatchException, OutOfRangeException, MaxCountExceededException {
/* 266 */     return CHI_SQUARE_TEST.chiSquareTest(expected, observed, alpha);
/*     */   }
/*     */   
/*     */   public static double chiSquareTest(double[] expected, long[] observed) throws NotPositiveException, NotStrictlyPositiveException, DimensionMismatchException, MaxCountExceededException {
/* 275 */     return CHI_SQUARE_TEST.chiSquareTest(expected, observed);
/*     */   }
/*     */   
/*     */   public static boolean chiSquareTest(long[][] counts, double alpha) throws NullArgumentException, DimensionMismatchException, NotPositiveException, OutOfRangeException, MaxCountExceededException {
/* 284 */     return CHI_SQUARE_TEST.chiSquareTest(counts, alpha);
/*     */   }
/*     */   
/*     */   public static double chiSquareTest(long[][] counts) throws NullArgumentException, DimensionMismatchException, NotPositiveException, MaxCountExceededException {
/* 293 */     return CHI_SQUARE_TEST.chiSquareTest(counts);
/*     */   }
/*     */   
/*     */   public static double chiSquareDataSetsComparison(long[] observed1, long[] observed2) throws DimensionMismatchException, NotPositiveException, ZeroException {
/* 304 */     return CHI_SQUARE_TEST.chiSquareDataSetsComparison(observed1, observed2);
/*     */   }
/*     */   
/*     */   public static double chiSquareTestDataSetsComparison(long[] observed1, long[] observed2) throws DimensionMismatchException, NotPositiveException, ZeroException, MaxCountExceededException {
/* 316 */     return CHI_SQUARE_TEST.chiSquareTestDataSetsComparison(observed1, observed2);
/*     */   }
/*     */   
/*     */   public static boolean chiSquareTestDataSetsComparison(long[] observed1, long[] observed2, double alpha) throws DimensionMismatchException, NotPositiveException, ZeroException, OutOfRangeException, MaxCountExceededException {
/* 329 */     return CHI_SQUARE_TEST.chiSquareTestDataSetsComparison(observed1, observed2, alpha);
/*     */   }
/*     */   
/*     */   public static double oneWayAnovaFValue(Collection<double[]> categoryData) throws NullArgumentException, DimensionMismatchException {
/* 339 */     return ONE_WAY_ANANOVA.anovaFValue(categoryData);
/*     */   }
/*     */   
/*     */   public static double oneWayAnovaPValue(Collection<double[]> categoryData) throws NullArgumentException, DimensionMismatchException, ConvergenceException, MaxCountExceededException {
/* 350 */     return ONE_WAY_ANANOVA.anovaPValue(categoryData);
/*     */   }
/*     */   
/*     */   public static boolean oneWayAnovaTest(Collection<double[]> categoryData, double alpha) throws NullArgumentException, DimensionMismatchException, OutOfRangeException, ConvergenceException, MaxCountExceededException {
/* 362 */     return ONE_WAY_ANANOVA.anovaTest(categoryData, alpha);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\stat\inference\TestUtils.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */