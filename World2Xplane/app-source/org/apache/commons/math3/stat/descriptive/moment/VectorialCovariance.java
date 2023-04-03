/*     */ package org.apache.commons.math3.stat.descriptive.moment;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Arrays;
/*     */ import org.apache.commons.math3.exception.DimensionMismatchException;
/*     */ import org.apache.commons.math3.linear.MatrixUtils;
/*     */ import org.apache.commons.math3.linear.RealMatrix;
/*     */ 
/*     */ public class VectorialCovariance implements Serializable {
/*     */   private static final long serialVersionUID = 4118372414238930270L;
/*     */   
/*     */   private final double[] sums;
/*     */   
/*     */   private final double[] productsSums;
/*     */   
/*     */   private final boolean isBiasCorrected;
/*     */   
/*     */   private long n;
/*     */   
/*     */   public VectorialCovariance(int dimension, boolean isBiasCorrected) {
/*  54 */     this.sums = new double[dimension];
/*  55 */     this.productsSums = new double[dimension * (dimension + 1) / 2];
/*  56 */     this.n = 0L;
/*  57 */     this.isBiasCorrected = isBiasCorrected;
/*     */   }
/*     */   
/*     */   public void increment(double[] v) throws DimensionMismatchException {
/*  66 */     if (v.length != this.sums.length)
/*  67 */       throw new DimensionMismatchException(v.length, this.sums.length); 
/*  69 */     int k = 0;
/*  70 */     for (int i = 0; i < v.length; i++) {
/*  71 */       this.sums[i] = this.sums[i] + v[i];
/*  72 */       for (int j = 0; j <= i; j++)
/*  73 */         this.productsSums[k++] = this.productsSums[k++] + v[i] * v[j]; 
/*     */     } 
/*  76 */     this.n++;
/*     */   }
/*     */   
/*     */   public RealMatrix getResult() {
/*  85 */     int dimension = this.sums.length;
/*  86 */     RealMatrix result = MatrixUtils.createRealMatrix(dimension, dimension);
/*  88 */     if (this.n > 1L) {
/*  89 */       double c = 1.0D / (this.n * (this.isBiasCorrected ? (this.n - 1L) : this.n));
/*  90 */       int k = 0;
/*  91 */       for (int i = 0; i < dimension; i++) {
/*  92 */         for (int j = 0; j <= i; j++) {
/*  93 */           double e = c * (this.n * this.productsSums[k++] - this.sums[i] * this.sums[j]);
/*  94 */           result.setEntry(i, j, e);
/*  95 */           result.setEntry(j, i, e);
/*     */         } 
/*     */       } 
/*     */     } 
/* 100 */     return result;
/*     */   }
/*     */   
/*     */   public long getN() {
/* 109 */     return this.n;
/*     */   }
/*     */   
/*     */   public void clear() {
/* 116 */     this.n = 0L;
/* 117 */     Arrays.fill(this.sums, 0.0D);
/* 118 */     Arrays.fill(this.productsSums, 0.0D);
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 124 */     int prime = 31;
/* 125 */     int result = 1;
/* 126 */     result = 31 * result + (this.isBiasCorrected ? 1231 : 1237);
/* 127 */     result = 31 * result + (int)(this.n ^ this.n >>> 32L);
/* 128 */     result = 31 * result + Arrays.hashCode(this.productsSums);
/* 129 */     result = 31 * result + Arrays.hashCode(this.sums);
/* 130 */     return result;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 136 */     if (this == obj)
/* 137 */       return true; 
/* 139 */     if (!(obj instanceof VectorialCovariance))
/* 140 */       return false; 
/* 142 */     VectorialCovariance other = (VectorialCovariance)obj;
/* 143 */     if (this.isBiasCorrected != other.isBiasCorrected)
/* 144 */       return false; 
/* 146 */     if (this.n != other.n)
/* 147 */       return false; 
/* 149 */     if (!Arrays.equals(this.productsSums, other.productsSums))
/* 150 */       return false; 
/* 152 */     if (!Arrays.equals(this.sums, other.sums))
/* 153 */       return false; 
/* 155 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\stat\descriptive\moment\VectorialCovariance.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */