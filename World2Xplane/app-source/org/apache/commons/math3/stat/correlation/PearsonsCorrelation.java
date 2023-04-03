/*     */ package org.apache.commons.math3.stat.correlation;
/*     */ 
/*     */ import org.apache.commons.math3.distribution.TDistribution;
/*     */ import org.apache.commons.math3.exception.DimensionMismatchException;
/*     */ import org.apache.commons.math3.exception.MathIllegalArgumentException;
/*     */ import org.apache.commons.math3.exception.NullArgumentException;
/*     */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*     */ import org.apache.commons.math3.linear.BlockRealMatrix;
/*     */ import org.apache.commons.math3.linear.RealMatrix;
/*     */ import org.apache.commons.math3.stat.regression.SimpleRegression;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ 
/*     */ public class PearsonsCorrelation {
/*     */   private final RealMatrix correlationMatrix;
/*     */   
/*     */   private final int nObs;
/*     */   
/*     */   public PearsonsCorrelation() {
/*  57 */     this.correlationMatrix = null;
/*  58 */     this.nObs = 0;
/*     */   }
/*     */   
/*     */   public PearsonsCorrelation(double[][] data) {
/*  70 */     this((RealMatrix)new BlockRealMatrix(data));
/*     */   }
/*     */   
/*     */   public PearsonsCorrelation(RealMatrix matrix) {
/*  80 */     checkSufficientData(matrix);
/*  81 */     this.nObs = matrix.getRowDimension();
/*  82 */     this.correlationMatrix = computeCorrelationMatrix(matrix);
/*     */   }
/*     */   
/*     */   public PearsonsCorrelation(Covariance covariance) {
/*  94 */     RealMatrix covarianceMatrix = covariance.getCovarianceMatrix();
/*  95 */     if (covarianceMatrix == null)
/*  96 */       throw new NullArgumentException(LocalizedFormats.COVARIANCE_MATRIX, new Object[0]); 
/*  98 */     this.nObs = covariance.getN();
/*  99 */     this.correlationMatrix = covarianceToCorrelation(covarianceMatrix);
/*     */   }
/*     */   
/*     */   public PearsonsCorrelation(RealMatrix covarianceMatrix, int numberOfObservations) {
/* 111 */     this.nObs = numberOfObservations;
/* 112 */     this.correlationMatrix = covarianceToCorrelation(covarianceMatrix);
/*     */   }
/*     */   
/*     */   public RealMatrix getCorrelationMatrix() {
/* 122 */     return this.correlationMatrix;
/*     */   }
/*     */   
/*     */   public RealMatrix getCorrelationStandardErrors() {
/* 138 */     int nVars = this.correlationMatrix.getColumnDimension();
/* 139 */     double[][] out = new double[nVars][nVars];
/* 140 */     for (int i = 0; i < nVars; i++) {
/* 141 */       for (int j = 0; j < nVars; j++) {
/* 142 */         double r = this.correlationMatrix.getEntry(i, j);
/* 143 */         out[i][j] = FastMath.sqrt((1.0D - r * r) / (this.nObs - 2));
/*     */       } 
/*     */     } 
/* 146 */     return (RealMatrix)new BlockRealMatrix(out);
/*     */   }
/*     */   
/*     */   public RealMatrix getCorrelationPValues() {
/* 164 */     TDistribution tDistribution = new TDistribution((this.nObs - 2));
/* 165 */     int nVars = this.correlationMatrix.getColumnDimension();
/* 166 */     double[][] out = new double[nVars][nVars];
/* 167 */     for (int i = 0; i < nVars; i++) {
/* 168 */       for (int j = 0; j < nVars; j++) {
/* 169 */         if (i == j) {
/* 170 */           out[i][j] = 0.0D;
/*     */         } else {
/* 172 */           double r = this.correlationMatrix.getEntry(i, j);
/* 173 */           double t = FastMath.abs(r * FastMath.sqrt((this.nObs - 2) / (1.0D - r * r)));
/* 174 */           out[i][j] = 2.0D * tDistribution.cumulativeProbability(-t);
/*     */         } 
/*     */       } 
/*     */     } 
/* 178 */     return (RealMatrix)new BlockRealMatrix(out);
/*     */   }
/*     */   
/*     */   public RealMatrix computeCorrelationMatrix(RealMatrix matrix) {
/* 190 */     int nVars = matrix.getColumnDimension();
/* 191 */     BlockRealMatrix blockRealMatrix = new BlockRealMatrix(nVars, nVars);
/* 192 */     for (int i = 0; i < nVars; i++) {
/* 193 */       for (int j = 0; j < i; j++) {
/* 194 */         double corr = correlation(matrix.getColumn(i), matrix.getColumn(j));
/* 195 */         blockRealMatrix.setEntry(i, j, corr);
/* 196 */         blockRealMatrix.setEntry(j, i, corr);
/*     */       } 
/* 198 */       blockRealMatrix.setEntry(i, i, 1.0D);
/*     */     } 
/* 200 */     return (RealMatrix)blockRealMatrix;
/*     */   }
/*     */   
/*     */   public RealMatrix computeCorrelationMatrix(double[][] data) {
/* 212 */     return computeCorrelationMatrix((RealMatrix)new BlockRealMatrix(data));
/*     */   }
/*     */   
/*     */   public double correlation(double[] xArray, double[] yArray) {
/* 228 */     SimpleRegression regression = new SimpleRegression();
/* 229 */     if (xArray.length != yArray.length)
/* 230 */       throw new DimensionMismatchException(xArray.length, yArray.length); 
/* 231 */     if (xArray.length < 2)
/* 232 */       throw new MathIllegalArgumentException(LocalizedFormats.INSUFFICIENT_DIMENSION, new Object[] { Integer.valueOf(xArray.length), Integer.valueOf(2) }); 
/* 235 */     for (int i = 0; i < xArray.length; i++)
/* 236 */       regression.addData(xArray[i], yArray[i]); 
/* 238 */     return regression.getR();
/*     */   }
/*     */   
/*     */   public RealMatrix covarianceToCorrelation(RealMatrix covarianceMatrix) {
/* 254 */     int nVars = covarianceMatrix.getColumnDimension();
/* 255 */     BlockRealMatrix blockRealMatrix = new BlockRealMatrix(nVars, nVars);
/* 256 */     for (int i = 0; i < nVars; i++) {
/* 257 */       double sigma = FastMath.sqrt(covarianceMatrix.getEntry(i, i));
/* 258 */       blockRealMatrix.setEntry(i, i, 1.0D);
/* 259 */       for (int j = 0; j < i; j++) {
/* 260 */         double entry = covarianceMatrix.getEntry(i, j) / sigma * FastMath.sqrt(covarianceMatrix.getEntry(j, j));
/* 262 */         blockRealMatrix.setEntry(i, j, entry);
/* 263 */         blockRealMatrix.setEntry(j, i, entry);
/*     */       } 
/*     */     } 
/* 266 */     return (RealMatrix)blockRealMatrix;
/*     */   }
/*     */   
/*     */   private void checkSufficientData(RealMatrix matrix) {
/* 277 */     int nRows = matrix.getRowDimension();
/* 278 */     int nCols = matrix.getColumnDimension();
/* 279 */     if (nRows < 2 || nCols < 2)
/* 280 */       throw new MathIllegalArgumentException(LocalizedFormats.INSUFFICIENT_ROWS_AND_COLUMNS, new Object[] { Integer.valueOf(nRows), Integer.valueOf(nCols) }); 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\stat\correlation\PearsonsCorrelation.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */