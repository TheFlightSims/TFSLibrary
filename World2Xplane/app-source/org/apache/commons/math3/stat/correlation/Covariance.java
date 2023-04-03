/*     */ package org.apache.commons.math3.stat.correlation;
/*     */ 
/*     */ import org.apache.commons.math3.exception.MathIllegalArgumentException;
/*     */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*     */ import org.apache.commons.math3.linear.BlockRealMatrix;
/*     */ import org.apache.commons.math3.linear.RealMatrix;
/*     */ import org.apache.commons.math3.stat.descriptive.moment.Mean;
/*     */ import org.apache.commons.math3.stat.descriptive.moment.Variance;
/*     */ 
/*     */ public class Covariance {
/*     */   private final RealMatrix covarianceMatrix;
/*     */   
/*     */   private final int n;
/*     */   
/*     */   public Covariance() {
/*  62 */     this.covarianceMatrix = null;
/*  63 */     this.n = 0;
/*     */   }
/*     */   
/*     */   public Covariance(double[][] data, boolean biasCorrected) {
/*  82 */     this((RealMatrix)new BlockRealMatrix(data), biasCorrected);
/*     */   }
/*     */   
/*     */   public Covariance(double[][] data) {
/*  97 */     this(data, true);
/*     */   }
/*     */   
/*     */   public Covariance(RealMatrix matrix, boolean biasCorrected) {
/* 115 */     checkSufficientData(matrix);
/* 116 */     this.n = matrix.getRowDimension();
/* 117 */     this.covarianceMatrix = computeCovarianceMatrix(matrix, biasCorrected);
/*     */   }
/*     */   
/*     */   public Covariance(RealMatrix matrix) {
/* 131 */     this(matrix, true);
/*     */   }
/*     */   
/*     */   public RealMatrix getCovarianceMatrix() {
/* 140 */     return this.covarianceMatrix;
/*     */   }
/*     */   
/*     */   public int getN() {
/* 149 */     return this.n;
/*     */   }
/*     */   
/*     */   protected RealMatrix computeCovarianceMatrix(RealMatrix matrix, boolean biasCorrected) {
/* 160 */     int dimension = matrix.getColumnDimension();
/* 161 */     Variance variance = new Variance(biasCorrected);
/* 162 */     BlockRealMatrix blockRealMatrix = new BlockRealMatrix(dimension, dimension);
/* 163 */     for (int i = 0; i < dimension; i++) {
/* 164 */       for (int j = 0; j < i; j++) {
/* 165 */         double cov = covariance(matrix.getColumn(i), matrix.getColumn(j), biasCorrected);
/* 166 */         blockRealMatrix.setEntry(i, j, cov);
/* 167 */         blockRealMatrix.setEntry(j, i, cov);
/*     */       } 
/* 169 */       blockRealMatrix.setEntry(i, i, variance.evaluate(matrix.getColumn(i)));
/*     */     } 
/* 171 */     return (RealMatrix)blockRealMatrix;
/*     */   }
/*     */   
/*     */   protected RealMatrix computeCovarianceMatrix(RealMatrix matrix) {
/* 182 */     return computeCovarianceMatrix(matrix, true);
/*     */   }
/*     */   
/*     */   protected RealMatrix computeCovarianceMatrix(double[][] data, boolean biasCorrected) {
/* 193 */     return computeCovarianceMatrix((RealMatrix)new BlockRealMatrix(data), biasCorrected);
/*     */   }
/*     */   
/*     */   protected RealMatrix computeCovarianceMatrix(double[][] data) {
/* 204 */     return computeCovarianceMatrix(data, true);
/*     */   }
/*     */   
/*     */   public double covariance(double[] xArray, double[] yArray, boolean biasCorrected) throws IllegalArgumentException {
/* 221 */     Mean mean = new Mean();
/* 222 */     double result = 0.0D;
/* 223 */     int length = xArray.length;
/* 224 */     if (length != yArray.length)
/* 225 */       throw new MathIllegalArgumentException(LocalizedFormats.DIMENSIONS_MISMATCH_SIMPLE, new Object[] { Integer.valueOf(length), Integer.valueOf(yArray.length) }); 
/* 227 */     if (length < 2)
/* 228 */       throw new MathIllegalArgumentException(LocalizedFormats.INSUFFICIENT_OBSERVED_POINTS_IN_SAMPLE, new Object[] { Integer.valueOf(length), Integer.valueOf(2) }); 
/* 231 */     double xMean = mean.evaluate(xArray);
/* 232 */     double yMean = mean.evaluate(yArray);
/* 233 */     for (int i = 0; i < length; i++) {
/* 234 */       double xDev = xArray[i] - xMean;
/* 235 */       double yDev = yArray[i] - yMean;
/* 236 */       result += (xDev * yDev - result) / (i + 1);
/*     */     } 
/* 239 */     return biasCorrected ? (result * length / (length - 1)) : result;
/*     */   }
/*     */   
/*     */   public double covariance(double[] xArray, double[] yArray) throws IllegalArgumentException {
/* 256 */     return covariance(xArray, yArray, true);
/*     */   }
/*     */   
/*     */   private void checkSufficientData(RealMatrix matrix) {
/* 265 */     int nRows = matrix.getRowDimension();
/* 266 */     int nCols = matrix.getColumnDimension();
/* 267 */     if (nRows < 2 || nCols < 2)
/* 268 */       throw new MathIllegalArgumentException(LocalizedFormats.INSUFFICIENT_ROWS_AND_COLUMNS, new Object[] { Integer.valueOf(nRows), Integer.valueOf(nCols) }); 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\stat\correlation\Covariance.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */