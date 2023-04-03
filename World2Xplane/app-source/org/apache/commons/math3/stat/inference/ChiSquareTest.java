/*     */ package org.apache.commons.math3.stat.inference;
/*     */ 
/*     */ import org.apache.commons.math3.distribution.ChiSquaredDistribution;
/*     */ import org.apache.commons.math3.exception.DimensionMismatchException;
/*     */ import org.apache.commons.math3.exception.MaxCountExceededException;
/*     */ import org.apache.commons.math3.exception.NotPositiveException;
/*     */ import org.apache.commons.math3.exception.NotStrictlyPositiveException;
/*     */ import org.apache.commons.math3.exception.NullArgumentException;
/*     */ import org.apache.commons.math3.exception.OutOfRangeException;
/*     */ import org.apache.commons.math3.exception.ZeroException;
/*     */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ import org.apache.commons.math3.util.MathUtils;
/*     */ 
/*     */ public class ChiSquareTest {
/*     */   public double chiSquare(double[] expected, long[] observed) throws NotPositiveException, NotStrictlyPositiveException, DimensionMismatchException {
/*  83 */     if (expected.length < 2)
/*  84 */       throw new DimensionMismatchException(expected.length, 2); 
/*  86 */     if (expected.length != observed.length)
/*  87 */       throw new DimensionMismatchException(expected.length, observed.length); 
/*  89 */     checkPositive(expected);
/*  90 */     checkNonNegative(observed);
/*  92 */     double sumExpected = 0.0D;
/*  93 */     double sumObserved = 0.0D;
/*  94 */     for (int i = 0; i < observed.length; i++) {
/*  95 */       sumExpected += expected[i];
/*  96 */       sumObserved += observed[i];
/*     */     } 
/*  98 */     double ratio = 1.0D;
/*  99 */     boolean rescale = false;
/* 100 */     if (FastMath.abs(sumExpected - sumObserved) > 1.0E-5D) {
/* 101 */       ratio = sumObserved / sumExpected;
/* 102 */       rescale = true;
/*     */     } 
/* 104 */     double sumSq = 0.0D;
/* 105 */     for (int j = 0; j < observed.length; j++) {
/* 106 */       if (rescale) {
/* 107 */         double dev = observed[j] - ratio * expected[j];
/* 108 */         sumSq += dev * dev / ratio * expected[j];
/*     */       } else {
/* 110 */         double dev = observed[j] - expected[j];
/* 111 */         sumSq += dev * dev / expected[j];
/*     */       } 
/*     */     } 
/* 114 */     return sumSq;
/*     */   }
/*     */   
/*     */   public double chiSquareTest(double[] expected, long[] observed) throws NotPositiveException, NotStrictlyPositiveException, DimensionMismatchException, MaxCountExceededException {
/* 158 */     ChiSquaredDistribution distribution = new ChiSquaredDistribution(expected.length - 1.0D);
/* 160 */     return 1.0D - distribution.cumulativeProbability(chiSquare(expected, observed));
/*     */   }
/*     */   
/*     */   public boolean chiSquareTest(double[] expected, long[] observed, double alpha) throws NotPositiveException, NotStrictlyPositiveException, DimensionMismatchException, OutOfRangeException, MaxCountExceededException {
/* 209 */     if (alpha <= 0.0D || alpha > 0.5D)
/* 210 */       throw new OutOfRangeException(LocalizedFormats.OUT_OF_BOUND_SIGNIFICANCE_LEVEL, Double.valueOf(alpha), Integer.valueOf(0), Double.valueOf(0.5D)); 
/* 213 */     return (chiSquareTest(expected, observed) < alpha);
/*     */   }
/*     */   
/*     */   public double chiSquare(long[][] counts) throws NullArgumentException, NotPositiveException, DimensionMismatchException {
/* 249 */     checkArray(counts);
/* 250 */     int nRows = counts.length;
/* 251 */     int nCols = (counts[0]).length;
/* 254 */     double[] rowSum = new double[nRows];
/* 255 */     double[] colSum = new double[nCols];
/* 256 */     double total = 0.0D;
/* 257 */     for (int row = 0; row < nRows; row++) {
/* 258 */       for (int col = 0; col < nCols; col++) {
/* 259 */         rowSum[row] = rowSum[row] + counts[row][col];
/* 260 */         colSum[col] = colSum[col] + counts[row][col];
/* 261 */         total += counts[row][col];
/*     */       } 
/*     */     } 
/* 266 */     double sumSq = 0.0D;
/* 267 */     double expected = 0.0D;
/* 268 */     for (int i = 0; i < nRows; i++) {
/* 269 */       for (int col = 0; col < nCols; col++) {
/* 270 */         expected = rowSum[i] * colSum[col] / total;
/* 271 */         sumSq += (counts[i][col] - expected) * (counts[i][col] - expected) / expected;
/*     */       } 
/*     */     } 
/* 275 */     return sumSq;
/*     */   }
/*     */   
/*     */   public double chiSquareTest(long[][] counts) throws NullArgumentException, DimensionMismatchException, NotPositiveException, MaxCountExceededException {
/* 314 */     checkArray(counts);
/* 315 */     double df = (counts.length - 1.0D) * ((counts[0]).length - 1.0D);
/* 317 */     ChiSquaredDistribution distribution = new ChiSquaredDistribution(df);
/* 318 */     return 1.0D - distribution.cumulativeProbability(chiSquare(counts));
/*     */   }
/*     */   
/*     */   public boolean chiSquareTest(long[][] counts, double alpha) throws NullArgumentException, DimensionMismatchException, NotPositiveException, OutOfRangeException, MaxCountExceededException {
/* 364 */     if (alpha <= 0.0D || alpha > 0.5D)
/* 365 */       throw new OutOfRangeException(LocalizedFormats.OUT_OF_BOUND_SIGNIFICANCE_LEVEL, Double.valueOf(alpha), Integer.valueOf(0), Double.valueOf(0.5D)); 
/* 368 */     return (chiSquareTest(counts) < alpha);
/*     */   }
/*     */   
/*     */   public double chiSquareDataSetsComparison(long[] observed1, long[] observed2) throws DimensionMismatchException, NotPositiveException, ZeroException {
/* 415 */     if (observed1.length < 2)
/* 416 */       throw new DimensionMismatchException(observed1.length, 2); 
/* 418 */     if (observed1.length != observed2.length)
/* 419 */       throw new DimensionMismatchException(observed1.length, observed2.length); 
/* 423 */     checkNonNegative(observed1);
/* 424 */     checkNonNegative(observed2);
/* 427 */     long countSum1 = 0L;
/* 428 */     long countSum2 = 0L;
/* 429 */     boolean unequalCounts = false;
/* 430 */     double weight = 0.0D;
/* 431 */     for (int i = 0; i < observed1.length; i++) {
/* 432 */       countSum1 += observed1[i];
/* 433 */       countSum2 += observed2[i];
/*     */     } 
/* 436 */     if (countSum1 == 0L || countSum2 == 0L)
/* 437 */       throw new ZeroException(); 
/* 440 */     unequalCounts = (countSum1 != countSum2);
/* 441 */     if (unequalCounts)
/* 442 */       weight = FastMath.sqrt(countSum1 / countSum2); 
/* 445 */     double sumSq = 0.0D;
/* 446 */     double dev = 0.0D;
/* 447 */     double obs1 = 0.0D;
/* 448 */     double obs2 = 0.0D;
/* 449 */     for (int j = 0; j < observed1.length; j++) {
/* 450 */       if (observed1[j] == 0L && observed2[j] == 0L)
/* 451 */         throw new ZeroException(LocalizedFormats.OBSERVED_COUNTS_BOTTH_ZERO_FOR_ENTRY, new Object[] { Integer.valueOf(j) }); 
/* 453 */       obs1 = observed1[j];
/* 454 */       obs2 = observed2[j];
/* 455 */       if (unequalCounts) {
/* 456 */         dev = obs1 / weight - obs2 * weight;
/*     */       } else {
/* 458 */         dev = obs1 - obs2;
/*     */       } 
/* 460 */       sumSq += dev * dev / (obs1 + obs2);
/*     */     } 
/* 463 */     return sumSq;
/*     */   }
/*     */   
/*     */   public double chiSquareTestDataSetsComparison(long[] observed1, long[] observed2) throws DimensionMismatchException, NotPositiveException, ZeroException, MaxCountExceededException {
/* 513 */     ChiSquaredDistribution distribution = new ChiSquaredDistribution(observed1.length - 1.0D);
/* 514 */     return 1.0D - distribution.cumulativeProbability(chiSquareDataSetsComparison(observed1, observed2));
/*     */   }
/*     */   
/*     */   public boolean chiSquareTestDataSetsComparison(long[] observed1, long[] observed2, double alpha) throws DimensionMismatchException, NotPositiveException, ZeroException, OutOfRangeException, MaxCountExceededException {
/* 567 */     if (alpha <= 0.0D || alpha > 0.5D)
/* 569 */       throw new OutOfRangeException(LocalizedFormats.OUT_OF_BOUND_SIGNIFICANCE_LEVEL, Double.valueOf(alpha), Integer.valueOf(0), Double.valueOf(0.5D)); 
/* 572 */     return (chiSquareTestDataSetsComparison(observed1, observed2) < alpha);
/*     */   }
/*     */   
/*     */   private void checkArray(long[][] in) throws NullArgumentException, DimensionMismatchException, NotPositiveException {
/* 589 */     if (in.length < 2)
/* 590 */       throw new DimensionMismatchException(in.length, 2); 
/* 593 */     if ((in[0]).length < 2)
/* 594 */       throw new DimensionMismatchException((in[0]).length, 2); 
/* 597 */     checkRectangular(in);
/* 598 */     checkNonNegative(in);
/*     */   }
/*     */   
/*     */   private void checkRectangular(long[][] in) throws NullArgumentException, DimensionMismatchException {
/* 614 */     MathUtils.checkNotNull(in);
/* 615 */     for (int i = 1; i < in.length; i++) {
/* 616 */       if ((in[i]).length != (in[0]).length)
/* 617 */         throw new DimensionMismatchException(LocalizedFormats.DIFFERENT_ROWS_LENGTHS, (in[i]).length, (in[0]).length); 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void checkPositive(double[] in) throws NotStrictlyPositiveException {
/* 634 */     for (int i = 0; i < in.length; i++) {
/* 635 */       if (in[i] <= 0.0D)
/* 636 */         throw new NotStrictlyPositiveException(Double.valueOf(in[i])); 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void checkNonNegative(long[] in) throws NotPositiveException {
/* 651 */     for (int i = 0; i < in.length; i++) {
/* 652 */       if (in[i] < 0L)
/* 653 */         throw new NotPositiveException(Long.valueOf(in[i])); 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void checkNonNegative(long[][] in) throws NotPositiveException {
/* 668 */     for (int i = 0; i < in.length; i++) {
/* 669 */       for (int j = 0; j < (in[i]).length; j++) {
/* 670 */         if (in[i][j] < 0L)
/* 671 */           throw new NotPositiveException(Long.valueOf(in[i][j])); 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\stat\inference\ChiSquareTest.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */