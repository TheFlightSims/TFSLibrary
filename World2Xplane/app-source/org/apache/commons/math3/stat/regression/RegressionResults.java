/*     */ package org.apache.commons.math3.stat.regression;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Arrays;
/*     */ import org.apache.commons.math3.exception.OutOfRangeException;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ import org.apache.commons.math3.util.MathArrays;
/*     */ 
/*     */ public class RegressionResults implements Serializable {
/*     */   private static final int SSE_IDX = 0;
/*     */   
/*     */   private static final int SST_IDX = 1;
/*     */   
/*     */   private static final int RSQ_IDX = 2;
/*     */   
/*     */   private static final int MSE_IDX = 3;
/*     */   
/*     */   private static final int ADJRSQ_IDX = 4;
/*     */   
/*     */   private static final long serialVersionUID = 1L;
/*     */   
/*     */   private final double[] parameters;
/*     */   
/*     */   private final double[][] varCovData;
/*     */   
/*     */   private final boolean isSymmetricVCD;
/*     */   
/*     */   private final int rank;
/*     */   
/*     */   private final long nobs;
/*     */   
/*     */   private final boolean containsConstant;
/*     */   
/*     */   private final double[] globalFitInfo;
/*     */   
/*     */   private RegressionResults() {
/*  67 */     this.parameters = null;
/*  68 */     this.varCovData = (double[][])null;
/*  69 */     this.rank = -1;
/*  70 */     this.nobs = -1L;
/*  71 */     this.containsConstant = false;
/*  72 */     this.isSymmetricVCD = false;
/*  73 */     this.globalFitInfo = null;
/*     */   }
/*     */   
/*     */   public RegressionResults(double[] parameters, double[][] varcov, boolean isSymmetricCompressed, long nobs, int rank, double sumy, double sumysq, double sse, boolean containsConstant, boolean copyData) {
/* 100 */     if (copyData) {
/* 101 */       this.parameters = MathArrays.copyOf(parameters);
/* 102 */       this.varCovData = new double[varcov.length][];
/* 103 */       for (int i = 0; i < varcov.length; i++)
/* 104 */         this.varCovData[i] = MathArrays.copyOf(varcov[i]); 
/*     */     } else {
/* 107 */       this.parameters = parameters;
/* 108 */       this.varCovData = varcov;
/*     */     } 
/* 110 */     this.isSymmetricVCD = isSymmetricCompressed;
/* 111 */     this.nobs = nobs;
/* 112 */     this.rank = rank;
/* 113 */     this.containsConstant = containsConstant;
/* 114 */     this.globalFitInfo = new double[5];
/* 115 */     Arrays.fill(this.globalFitInfo, Double.NaN);
/* 117 */     if (rank > 0)
/* 118 */       this.globalFitInfo[1] = containsConstant ? (sumysq - sumy * sumy / nobs) : sumysq; 
/* 122 */     this.globalFitInfo[0] = sse;
/* 123 */     this.globalFitInfo[3] = this.globalFitInfo[0] / (nobs - rank);
/* 125 */     this.globalFitInfo[2] = 1.0D - this.globalFitInfo[0] / this.globalFitInfo[1];
/* 129 */     if (!containsConstant) {
/* 130 */       this.globalFitInfo[4] = 1.0D - (1.0D - this.globalFitInfo[2]) * nobs / (nobs - rank);
/*     */     } else {
/* 134 */       this.globalFitInfo[4] = 1.0D - sse * (nobs - 1.0D) / this.globalFitInfo[1] * (nobs - rank);
/*     */     } 
/*     */   }
/*     */   
/*     */   public double getParameterEstimate(int index) {
/* 151 */     if (this.parameters == null)
/* 152 */       return Double.NaN; 
/* 154 */     if (index < 0 || index >= this.parameters.length)
/* 155 */       throw new OutOfRangeException(Integer.valueOf(index), Integer.valueOf(0), Integer.valueOf(this.parameters.length - 1)); 
/* 157 */     return this.parameters[index];
/*     */   }
/*     */   
/*     */   public double[] getParameterEstimates() {
/* 171 */     if (this.parameters == null)
/* 172 */       return null; 
/* 174 */     return MathArrays.copyOf(this.parameters);
/*     */   }
/*     */   
/*     */   public double getStdErrorOfEstimate(int index) {
/* 188 */     if (this.parameters == null)
/* 189 */       return Double.NaN; 
/* 191 */     if (index < 0 || index >= this.parameters.length)
/* 192 */       throw new OutOfRangeException(Integer.valueOf(index), Integer.valueOf(0), Integer.valueOf(this.parameters.length - 1)); 
/* 194 */     double var = getVcvElement(index, index);
/* 195 */     if (!Double.isNaN(var) && var > Double.MIN_VALUE)
/* 196 */       return FastMath.sqrt(var); 
/* 198 */     return Double.NaN;
/*     */   }
/*     */   
/*     */   public double[] getStdErrorOfEstimates() {
/* 213 */     if (this.parameters == null)
/* 214 */       return null; 
/* 216 */     double[] se = new double[this.parameters.length];
/* 217 */     for (int i = 0; i < this.parameters.length; i++) {
/* 218 */       double var = getVcvElement(i, i);
/* 219 */       if (!Double.isNaN(var) && var > Double.MIN_VALUE) {
/* 220 */         se[i] = FastMath.sqrt(var);
/*     */       } else {
/* 223 */         se[i] = Double.NaN;
/*     */       } 
/*     */     } 
/* 225 */     return se;
/*     */   }
/*     */   
/*     */   public double getCovarianceOfParameters(int i, int j) {
/* 241 */     if (this.parameters == null)
/* 242 */       return Double.NaN; 
/* 244 */     if (i < 0 || i >= this.parameters.length)
/* 245 */       throw new OutOfRangeException(Integer.valueOf(i), Integer.valueOf(0), Integer.valueOf(this.parameters.length - 1)); 
/* 247 */     if (j < 0 || j >= this.parameters.length)
/* 248 */       throw new OutOfRangeException(Integer.valueOf(j), Integer.valueOf(0), Integer.valueOf(this.parameters.length - 1)); 
/* 250 */     return getVcvElement(i, j);
/*     */   }
/*     */   
/*     */   public int getNumberOfParameters() {
/* 262 */     if (this.parameters == null)
/* 263 */       return -1; 
/* 265 */     return this.parameters.length;
/*     */   }
/*     */   
/*     */   public long getN() {
/* 274 */     return this.nobs;
/*     */   }
/*     */   
/*     */   public double getTotalSumSquares() {
/* 288 */     return this.globalFitInfo[1];
/*     */   }
/*     */   
/*     */   public double getRegressionSumSquares() {
/* 308 */     return this.globalFitInfo[1] - this.globalFitInfo[0];
/*     */   }
/*     */   
/*     */   public double getErrorSumSquares() {
/* 330 */     return this.globalFitInfo[0];
/*     */   }
/*     */   
/*     */   public double getMeanSquareError() {
/* 344 */     return this.globalFitInfo[3];
/*     */   }
/*     */   
/*     */   public double getRSquared() {
/* 362 */     return this.globalFitInfo[2];
/*     */   }
/*     */   
/*     */   public double getAdjustedRSquared() {
/* 380 */     return this.globalFitInfo[4];
/*     */   }
/*     */   
/*     */   public boolean hasIntercept() {
/* 390 */     return this.containsConstant;
/*     */   }
/*     */   
/*     */   private double getVcvElement(int i, int j) {
/* 401 */     if (this.isSymmetricVCD) {
/* 402 */       if (this.varCovData.length > 1) {
/* 404 */         if (i == j)
/* 405 */           return this.varCovData[i][i]; 
/* 406 */         if (i >= (this.varCovData[j]).length)
/* 407 */           return this.varCovData[i][j]; 
/* 409 */         return this.varCovData[j][i];
/*     */       } 
/* 412 */       if (i > j)
/* 413 */         return this.varCovData[0][(i + 1) * i / 2 + j]; 
/* 415 */       return this.varCovData[0][(j + 1) * j / 2 + i];
/*     */     } 
/* 419 */     return this.varCovData[i][j];
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\stat\regression\RegressionResults.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */