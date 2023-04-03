/*     */ package org.apache.commons.math3.stat.regression;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.apache.commons.math3.distribution.TDistribution;
/*     */ import org.apache.commons.math3.exception.MathIllegalArgumentException;
/*     */ import org.apache.commons.math3.exception.NoDataException;
/*     */ import org.apache.commons.math3.exception.OutOfRangeException;
/*     */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ 
/*     */ public class SimpleRegression implements Serializable, UpdatingMultipleLinearRegression {
/*     */   private static final long serialVersionUID = -3004689053607543335L;
/*     */   
/*  70 */   private double sumX = 0.0D;
/*     */   
/*  73 */   private double sumXX = 0.0D;
/*     */   
/*  76 */   private double sumY = 0.0D;
/*     */   
/*  79 */   private double sumYY = 0.0D;
/*     */   
/*  82 */   private double sumXY = 0.0D;
/*     */   
/*  85 */   private long n = 0L;
/*     */   
/*  88 */   private double xbar = 0.0D;
/*     */   
/*  91 */   private double ybar = 0.0D;
/*     */   
/*     */   private final boolean hasIntercept;
/*     */   
/*     */   public SimpleRegression() {
/* 101 */     this(true);
/*     */   }
/*     */   
/*     */   public SimpleRegression(boolean includeIntercept) {
/* 116 */     this.hasIntercept = includeIntercept;
/*     */   }
/*     */   
/*     */   public void addData(double x, double y) {
/* 133 */     if (this.n == 0L) {
/* 134 */       this.xbar = x;
/* 135 */       this.ybar = y;
/* 137 */     } else if (this.hasIntercept) {
/* 138 */       double fact1 = 1.0D + this.n;
/* 139 */       double fact2 = this.n / (1.0D + this.n);
/* 140 */       double dx = x - this.xbar;
/* 141 */       double dy = y - this.ybar;
/* 142 */       this.sumXX += dx * dx * fact2;
/* 143 */       this.sumYY += dy * dy * fact2;
/* 144 */       this.sumXY += dx * dy * fact2;
/* 145 */       this.xbar += dx / fact1;
/* 146 */       this.ybar += dy / fact1;
/*     */     } 
/* 149 */     if (!this.hasIntercept) {
/* 150 */       this.sumXX += x * x;
/* 151 */       this.sumYY += y * y;
/* 152 */       this.sumXY += x * y;
/*     */     } 
/* 154 */     this.sumX += x;
/* 155 */     this.sumY += y;
/* 156 */     this.n++;
/*     */   }
/*     */   
/*     */   public void removeData(double x, double y) {
/* 175 */     if (this.n > 0L) {
/* 176 */       if (this.hasIntercept) {
/* 177 */         double fact1 = this.n - 1.0D;
/* 178 */         double fact2 = this.n / (this.n - 1.0D);
/* 179 */         double dx = x - this.xbar;
/* 180 */         double dy = y - this.ybar;
/* 181 */         this.sumXX -= dx * dx * fact2;
/* 182 */         this.sumYY -= dy * dy * fact2;
/* 183 */         this.sumXY -= dx * dy * fact2;
/* 184 */         this.xbar -= dx / fact1;
/* 185 */         this.ybar -= dy / fact1;
/*     */       } else {
/* 187 */         double fact1 = this.n - 1.0D;
/* 188 */         this.sumXX -= x * x;
/* 189 */         this.sumYY -= y * y;
/* 190 */         this.sumXY -= x * y;
/* 191 */         this.xbar -= x / fact1;
/* 192 */         this.ybar -= y / fact1;
/*     */       } 
/* 194 */       this.sumX -= x;
/* 195 */       this.sumY -= y;
/* 196 */       this.n--;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void addData(double[][] data) {
/* 220 */     for (int i = 0; i < data.length; i++) {
/* 221 */       if ((data[i]).length < 2)
/* 222 */         throw new ModelSpecificationException(LocalizedFormats.INVALID_REGRESSION_OBSERVATION, new Object[] { Integer.valueOf((data[i]).length), Integer.valueOf(2) }); 
/* 225 */       addData(data[i][0], data[i][1]);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void addObservation(double[] x, double y) throws ModelSpecificationException {
/* 239 */     if (x == null || x.length == 0)
/* 240 */       throw new ModelSpecificationException(LocalizedFormats.INVALID_REGRESSION_OBSERVATION, new Object[] { Integer.valueOf((x != null) ? x.length : 0), Integer.valueOf(1) }); 
/* 242 */     addData(x[0], y);
/*     */   }
/*     */   
/*     */   public void addObservations(double[][] x, double[] y) {
/* 257 */     if (x == null || y == null || x.length != y.length)
/* 258 */       throw new ModelSpecificationException(LocalizedFormats.DIMENSIONS_MISMATCH_SIMPLE, new Object[] { Integer.valueOf((x == null) ? 0 : x.length), Integer.valueOf((y == null) ? 0 : y.length) }); 
/* 263 */     boolean obsOk = true;
/*     */     int i;
/* 264 */     for (i = 0; i < x.length; i++) {
/* 265 */       if (x[i] == null || (x[i]).length == 0)
/* 266 */         obsOk = false; 
/*     */     } 
/* 269 */     if (!obsOk)
/* 270 */       throw new ModelSpecificationException(LocalizedFormats.NOT_ENOUGH_DATA_FOR_NUMBER_OF_PREDICTORS, new Object[] { Integer.valueOf(0), Integer.valueOf(1) }); 
/* 274 */     for (i = 0; i < x.length; i++)
/* 275 */       addData(x[i][0], y[i]); 
/*     */   }
/*     */   
/*     */   public void removeData(double[][] data) {
/* 294 */     for (int i = 0; i < data.length && this.n > 0L; i++)
/* 295 */       removeData(data[i][0], data[i][1]); 
/*     */   }
/*     */   
/*     */   public void clear() {
/* 303 */     this.sumX = 0.0D;
/* 304 */     this.sumXX = 0.0D;
/* 305 */     this.sumY = 0.0D;
/* 306 */     this.sumYY = 0.0D;
/* 307 */     this.sumXY = 0.0D;
/* 308 */     this.n = 0L;
/*     */   }
/*     */   
/*     */   public long getN() {
/* 317 */     return this.n;
/*     */   }
/*     */   
/*     */   public double predict(double x) {
/* 338 */     double b1 = getSlope();
/* 339 */     if (this.hasIntercept)
/* 340 */       return getIntercept(b1) + b1 * x; 
/* 342 */     return b1 * x;
/*     */   }
/*     */   
/*     */   public double getIntercept() {
/* 365 */     return this.hasIntercept ? getIntercept(getSlope()) : 0.0D;
/*     */   }
/*     */   
/*     */   public boolean hasIntercept() {
/* 375 */     return this.hasIntercept;
/*     */   }
/*     */   
/*     */   public double getSlope() {
/* 395 */     if (this.n < 2L)
/* 396 */       return Double.NaN; 
/* 398 */     if (FastMath.abs(this.sumXX) < 4.9E-323D)
/* 399 */       return Double.NaN; 
/* 401 */     return this.sumXY / this.sumXX;
/*     */   }
/*     */   
/*     */   public double getSumSquaredErrors() {
/* 434 */     return FastMath.max(0.0D, this.sumYY - this.sumXY * this.sumXY / this.sumXX);
/*     */   }
/*     */   
/*     */   public double getTotalSumSquares() {
/* 448 */     if (this.n < 2L)
/* 449 */       return Double.NaN; 
/* 451 */     return this.sumYY;
/*     */   }
/*     */   
/*     */   public double getXSumSquares() {
/* 462 */     if (this.n < 2L)
/* 463 */       return Double.NaN; 
/* 465 */     return this.sumXX;
/*     */   }
/*     */   
/*     */   public double getSumOfCrossProducts() {
/* 474 */     return this.sumXY;
/*     */   }
/*     */   
/*     */   public double getRegressionSumSquares() {
/* 494 */     return getRegressionSumSquares(getSlope());
/*     */   }
/*     */   
/*     */   public double getMeanSquareError() {
/* 508 */     if (this.n < 3L)
/* 509 */       return Double.NaN; 
/* 511 */     return this.hasIntercept ? (getSumSquaredErrors() / (this.n - 2L)) : (getSumSquaredErrors() / (this.n - 1L));
/*     */   }
/*     */   
/*     */   public double getR() {
/* 529 */     double b1 = getSlope();
/* 530 */     double result = FastMath.sqrt(getRSquare());
/* 531 */     if (b1 < 0.0D)
/* 532 */       result = -result; 
/* 534 */     return result;
/*     */   }
/*     */   
/*     */   public double getRSquare() {
/* 552 */     double ssto = getTotalSumSquares();
/* 553 */     return (ssto - getSumSquaredErrors()) / ssto;
/*     */   }
/*     */   
/*     */   public double getInterceptStdErr() {
/* 569 */     if (!this.hasIntercept)
/* 570 */       return Double.NaN; 
/* 572 */     return FastMath.sqrt(getMeanSquareError() * (1.0D / this.n + this.xbar * this.xbar / this.sumXX));
/*     */   }
/*     */   
/*     */   public double getSlopeStdErr() {
/* 588 */     return FastMath.sqrt(getMeanSquareError() / this.sumXX);
/*     */   }
/*     */   
/*     */   public double getSlopeConfidenceInterval() {
/* 614 */     return getSlopeConfidenceInterval(0.05D);
/*     */   }
/*     */   
/*     */   public double getSlopeConfidenceInterval(double alpha) {
/* 649 */     if (alpha >= 1.0D || alpha <= 0.0D)
/* 650 */       throw new OutOfRangeException(LocalizedFormats.SIGNIFICANCE_LEVEL, Double.valueOf(alpha), Integer.valueOf(0), Integer.valueOf(1)); 
/* 653 */     TDistribution distribution = new TDistribution((this.n - 2L));
/* 654 */     return getSlopeStdErr() * distribution.inverseCumulativeProbability(1.0D - alpha / 2.0D);
/*     */   }
/*     */   
/*     */   public double getSignificance() {
/* 681 */     TDistribution distribution = new TDistribution((this.n - 2L));
/* 682 */     return 2.0D * (1.0D - distribution.cumulativeProbability(FastMath.abs(getSlope()) / getSlopeStdErr()));
/*     */   }
/*     */   
/*     */   private double getIntercept(double slope) {
/* 697 */     if (this.hasIntercept)
/* 698 */       return (this.sumY - slope * this.sumX) / this.n; 
/* 700 */     return 0.0D;
/*     */   }
/*     */   
/*     */   private double getRegressionSumSquares(double slope) {
/* 710 */     return slope * slope * this.sumXX;
/*     */   }
/*     */   
/*     */   public RegressionResults regress() throws ModelSpecificationException {
/* 719 */     if (this.hasIntercept) {
/* 720 */       if (this.n < 3L)
/* 721 */         throw new NoDataException(LocalizedFormats.NOT_ENOUGH_DATA_REGRESSION); 
/* 723 */       if (FastMath.abs(this.sumXX) > 2.2250738585072014E-308D) {
/* 724 */         double[] arrayOfDouble3 = { getIntercept(), getSlope() };
/* 725 */         double mse = getMeanSquareError();
/* 726 */         double _syy = this.sumYY + this.sumY * this.sumY / this.n;
/* 727 */         double[] arrayOfDouble4 = { mse * (this.xbar * this.xbar / this.sumXX + 1.0D / this.n), -this.xbar * mse / this.sumXX, mse / this.sumXX };
/* 731 */         return new RegressionResults(arrayOfDouble3, new double[][] { arrayOfDouble4 }, true, this.n, 2, this.sumY, _syy, getSumSquaredErrors(), true, false);
/*     */       } 
/* 735 */       double[] arrayOfDouble1 = { this.sumY / this.n, Double.NaN };
/* 737 */       double[] arrayOfDouble2 = { this.ybar / (this.n - 1.0D), Double.NaN, Double.NaN };
/* 741 */       return new RegressionResults(arrayOfDouble1, new double[][] { arrayOfDouble2 }, true, this.n, 1, this.sumY, this.sumYY, getSumSquaredErrors(), true, false);
/*     */     } 
/* 746 */     if (this.n < 2L)
/* 747 */       throw new NoDataException(LocalizedFormats.NOT_ENOUGH_DATA_REGRESSION); 
/* 749 */     if (!Double.isNaN(this.sumXX)) {
/* 750 */       double[] arrayOfDouble1 = { getMeanSquareError() / this.sumXX };
/* 751 */       double[] arrayOfDouble2 = { this.sumXY / this.sumXX };
/* 752 */       return new RegressionResults(arrayOfDouble2, new double[][] { arrayOfDouble1 }, true, this.n, 1, this.sumY, this.sumYY, getSumSquaredErrors(), false, false);
/*     */     } 
/* 756 */     double[] vcv = { Double.NaN };
/* 757 */     double[] params = { Double.NaN };
/* 758 */     return new RegressionResults(params, new double[][] { vcv }, true, this.n, 1, Double.NaN, Double.NaN, Double.NaN, false, false);
/*     */   }
/*     */   
/*     */   public RegressionResults regress(int[] variablesToInclude) throws ModelSpecificationException {
/* 775 */     if (variablesToInclude == null || variablesToInclude.length == 0)
/* 776 */       throw new MathIllegalArgumentException(LocalizedFormats.ARRAY_ZERO_LENGTH_OR_NULL_NOT_ALLOWED, new Object[0]); 
/* 778 */     if (variablesToInclude.length > 2 || (variablesToInclude.length > 1 && !this.hasIntercept))
/* 779 */       throw new ModelSpecificationException(LocalizedFormats.ARRAY_SIZE_EXCEEDS_MAX_VARIABLES, new Object[] { Integer.valueOf((variablesToInclude.length > 1 && !this.hasIntercept) ? 1 : 2) }); 
/* 784 */     if (this.hasIntercept) {
/* 785 */       if (variablesToInclude.length == 2) {
/* 786 */         if (variablesToInclude[0] == 1)
/* 787 */           throw new ModelSpecificationException(LocalizedFormats.NOT_INCREASING_SEQUENCE, new Object[0]); 
/* 788 */         if (variablesToInclude[0] != 0)
/* 789 */           throw new OutOfRangeException(Integer.valueOf(variablesToInclude[0]), Integer.valueOf(0), Integer.valueOf(1)); 
/* 791 */         if (variablesToInclude[1] != 1)
/* 792 */           throw new OutOfRangeException(Integer.valueOf(variablesToInclude[0]), Integer.valueOf(0), Integer.valueOf(1)); 
/* 794 */         return regress();
/*     */       } 
/* 796 */       if (variablesToInclude[0] != 1 && variablesToInclude[0] != 0)
/* 797 */         throw new OutOfRangeException(Integer.valueOf(variablesToInclude[0]), Integer.valueOf(0), Integer.valueOf(1)); 
/* 799 */       double _mean = this.sumY * this.sumY / this.n;
/* 800 */       double _syy = this.sumYY + _mean;
/* 801 */       if (variablesToInclude[0] == 0) {
/* 803 */         double[] vcv = { this.sumYY / ((this.n - 1L) * this.n) };
/* 804 */         double[] params = { this.ybar };
/* 805 */         return new RegressionResults(params, new double[][] { vcv }, true, this.n, 1, this.sumY, _syy + _mean, this.sumYY, true, false);
/*     */       } 
/* 809 */       if (variablesToInclude[0] == 1) {
/* 811 */         double _sxx = this.sumXX + this.sumX * this.sumX / this.n;
/* 812 */         double _sxy = this.sumXY + this.sumX * this.sumY / this.n;
/* 813 */         double _sse = FastMath.max(0.0D, _syy - _sxy * _sxy / _sxx);
/* 814 */         double _mse = _sse / (this.n - 1L);
/* 815 */         if (!Double.isNaN(_sxx)) {
/* 816 */           double[] arrayOfDouble1 = { _mse / _sxx };
/* 817 */           double[] arrayOfDouble2 = { _sxy / _sxx };
/* 818 */           return new RegressionResults(arrayOfDouble2, new double[][] { arrayOfDouble1 }, true, this.n, 1, this.sumY, _syy, _sse, false, false);
/*     */         } 
/* 822 */         double[] vcv = { Double.NaN };
/* 823 */         double[] params = { Double.NaN };
/* 824 */         return new RegressionResults(params, new double[][] { vcv }, true, this.n, 1, Double.NaN, Double.NaN, Double.NaN, false, false);
/*     */       } 
/*     */     } else {
/* 831 */       if (variablesToInclude[0] != 0)
/* 832 */         throw new OutOfRangeException(Integer.valueOf(variablesToInclude[0]), Integer.valueOf(0), Integer.valueOf(0)); 
/* 834 */       return regress();
/*     */     } 
/* 837 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\stat\regression\SimpleRegression.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */