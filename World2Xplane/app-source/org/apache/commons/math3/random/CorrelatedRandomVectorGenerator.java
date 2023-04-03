/*     */ package org.apache.commons.math3.random;
/*     */ 
/*     */ import org.apache.commons.math3.exception.DimensionMismatchException;
/*     */ import org.apache.commons.math3.linear.RealMatrix;
/*     */ import org.apache.commons.math3.linear.RectangularCholeskyDecomposition;
/*     */ 
/*     */ public class CorrelatedRandomVectorGenerator implements RandomVectorGenerator {
/*     */   private final double[] mean;
/*     */   
/*     */   private final NormalizedRandomGenerator generator;
/*     */   
/*     */   private final double[] normalized;
/*     */   
/*     */   private final RealMatrix root;
/*     */   
/*     */   public CorrelatedRandomVectorGenerator(double[] mean, RealMatrix covariance, double small, NormalizedRandomGenerator generator) {
/*  90 */     int order = covariance.getRowDimension();
/*  91 */     if (mean.length != order)
/*  92 */       throw new DimensionMismatchException(mean.length, order); 
/*  94 */     this.mean = (double[])mean.clone();
/*  96 */     RectangularCholeskyDecomposition decomposition = new RectangularCholeskyDecomposition(covariance, small);
/*  98 */     this.root = decomposition.getRootMatrix();
/* 100 */     this.generator = generator;
/* 101 */     this.normalized = new double[decomposition.getRank()];
/*     */   }
/*     */   
/*     */   public CorrelatedRandomVectorGenerator(RealMatrix covariance, double small, NormalizedRandomGenerator generator) {
/* 119 */     int order = covariance.getRowDimension();
/* 120 */     this.mean = new double[order];
/* 121 */     for (int i = 0; i < order; i++)
/* 122 */       this.mean[i] = 0.0D; 
/* 125 */     RectangularCholeskyDecomposition decomposition = new RectangularCholeskyDecomposition(covariance, small);
/* 127 */     this.root = decomposition.getRootMatrix();
/* 129 */     this.generator = generator;
/* 130 */     this.normalized = new double[decomposition.getRank()];
/*     */   }
/*     */   
/*     */   public NormalizedRandomGenerator getGenerator() {
/* 138 */     return this.generator;
/*     */   }
/*     */   
/*     */   public int getRank() {
/* 148 */     return this.normalized.length;
/*     */   }
/*     */   
/*     */   public RealMatrix getRootMatrix() {
/* 158 */     return this.root;
/*     */   }
/*     */   
/*     */   public double[] nextVector() {
/* 168 */     for (int i = 0; i < this.normalized.length; i++)
/* 169 */       this.normalized[i] = this.generator.nextNormalizedDouble(); 
/* 173 */     double[] correlated = new double[this.mean.length];
/* 174 */     for (int j = 0; j < correlated.length; j++) {
/* 175 */       correlated[j] = this.mean[j];
/* 176 */       for (int k = 0; k < this.root.getColumnDimension(); k++)
/* 177 */         correlated[j] = correlated[j] + this.root.getEntry(j, k) * this.normalized[k]; 
/*     */     } 
/* 181 */     return correlated;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\random\CorrelatedRandomVectorGenerator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */