/*     */ package org.apache.commons.math3.stat.correlation;
/*     */ 
/*     */ import org.apache.commons.math3.exception.DimensionMismatchException;
/*     */ import org.apache.commons.math3.exception.MathUnsupportedOperationException;
/*     */ import org.apache.commons.math3.exception.NumberIsTooSmallException;
/*     */ import org.apache.commons.math3.linear.MatrixUtils;
/*     */ import org.apache.commons.math3.linear.RealMatrix;
/*     */ 
/*     */ public class StorelessCovariance extends Covariance {
/*     */   private StorelessBivariateCovariance[] covMatrix;
/*     */   
/*     */   private int dimension;
/*     */   
/*     */   public StorelessCovariance(int dim) {
/*  57 */     this(dim, true);
/*     */   }
/*     */   
/*     */   public StorelessCovariance(int dim, boolean biasCorrected) {
/*  70 */     this.dimension = dim;
/*  71 */     this.covMatrix = new StorelessBivariateCovariance[this.dimension * (this.dimension + 1) / 2];
/*  72 */     initializeMatrix(biasCorrected);
/*     */   }
/*     */   
/*     */   private void initializeMatrix(boolean biasCorrected) {
/*  82 */     for (int i = 0; i < this.dimension; i++) {
/*  83 */       for (int j = 0; j < this.dimension; j++)
/*  84 */         setElement(i, j, new StorelessBivariateCovariance(biasCorrected)); 
/*     */     } 
/*     */   }
/*     */   
/*     */   private int indexOf(int i, int j) {
/*  99 */     return (j < i) ? (i * (i + 1) / 2 + j) : (j * (j + 1) / 2 + i);
/*     */   }
/*     */   
/*     */   private StorelessBivariateCovariance getElement(int i, int j) {
/* 109 */     return this.covMatrix[indexOf(i, j)];
/*     */   }
/*     */   
/*     */   private void setElement(int i, int j, StorelessBivariateCovariance cov) {
/* 120 */     this.covMatrix[indexOf(i, j)] = cov;
/*     */   }
/*     */   
/*     */   public double getCovariance(int xIndex, int yIndex) throws NumberIsTooSmallException {
/* 136 */     return getElement(xIndex, yIndex).getResult();
/*     */   }
/*     */   
/*     */   public void increment(double[] data) throws DimensionMismatchException {
/* 150 */     int length = data.length;
/* 151 */     if (length != this.dimension)
/* 152 */       throw new DimensionMismatchException(length, this.dimension); 
/* 157 */     for (int i = 0; i < length; i++) {
/* 158 */       for (int j = i; j < length; j++)
/* 159 */         getElement(i, j).increment(data[i], data[j]); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public RealMatrix getCovarianceMatrix() throws NumberIsTooSmallException {
/* 172 */     return MatrixUtils.createRealMatrix(getData());
/*     */   }
/*     */   
/*     */   public double[][] getData() throws NumberIsTooSmallException {
/* 183 */     double[][] data = new double[this.dimension][this.dimension];
/* 184 */     for (int i = 0; i < this.dimension; i++) {
/* 185 */       for (int j = 0; j < this.dimension; j++)
/* 186 */         data[i][j] = getElement(i, j).getResult(); 
/*     */     } 
/* 189 */     return data;
/*     */   }
/*     */   
/*     */   public int getN() throws MathUnsupportedOperationException {
/* 204 */     throw new MathUnsupportedOperationException();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\stat\correlation\StorelessCovariance.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */