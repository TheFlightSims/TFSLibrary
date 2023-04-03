/*     */ package org.apache.commons.math3.stat.regression;
/*     */ 
/*     */ import org.apache.commons.math3.exception.DimensionMismatchException;
/*     */ import org.apache.commons.math3.exception.MathIllegalArgumentException;
/*     */ import org.apache.commons.math3.exception.NoDataException;
/*     */ import org.apache.commons.math3.exception.NullArgumentException;
/*     */ import org.apache.commons.math3.exception.NumberIsTooSmallException;
/*     */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*     */ import org.apache.commons.math3.linear.Array2DRowRealMatrix;
/*     */ import org.apache.commons.math3.linear.ArrayRealVector;
/*     */ import org.apache.commons.math3.linear.NonSquareMatrixException;
/*     */ import org.apache.commons.math3.linear.RealMatrix;
/*     */ import org.apache.commons.math3.linear.RealVector;
/*     */ import org.apache.commons.math3.stat.descriptive.moment.Variance;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ 
/*     */ public abstract class AbstractMultipleLinearRegression implements MultipleLinearRegression {
/*     */   private RealMatrix xMatrix;
/*     */   
/*     */   private RealVector yVector;
/*     */   
/*     */   private boolean noIntercept = false;
/*     */   
/*     */   protected RealMatrix getX() {
/*  54 */     return this.xMatrix;
/*     */   }
/*     */   
/*     */   protected RealVector getY() {
/*  61 */     return this.yVector;
/*     */   }
/*     */   
/*     */   public boolean isNoIntercept() {
/*  69 */     return this.noIntercept;
/*     */   }
/*     */   
/*     */   public void setNoIntercept(boolean noIntercept) {
/*  77 */     this.noIntercept = noIntercept;
/*     */   }
/*     */   
/*     */   public void newSampleData(double[] data, int nobs, int nvars) {
/* 116 */     if (data == null)
/* 117 */       throw new NullArgumentException(); 
/* 119 */     if (data.length != nobs * (nvars + 1))
/* 120 */       throw new DimensionMismatchException(data.length, nobs * (nvars + 1)); 
/* 122 */     if (nobs <= nvars)
/* 123 */       throw new NumberIsTooSmallException(Integer.valueOf(nobs), Integer.valueOf(nvars), false); 
/* 125 */     double[] y = new double[nobs];
/* 126 */     int cols = this.noIntercept ? nvars : (nvars + 1);
/* 127 */     double[][] x = new double[nobs][cols];
/* 128 */     int pointer = 0;
/* 129 */     for (int i = 0; i < nobs; i++) {
/* 130 */       y[i] = data[pointer++];
/* 131 */       if (!this.noIntercept)
/* 132 */         x[i][0] = 1.0D; 
/* 134 */       for (int j = this.noIntercept ? 0 : 1; j < cols; j++)
/* 135 */         x[i][j] = data[pointer++]; 
/*     */     } 
/* 138 */     this.xMatrix = (RealMatrix)new Array2DRowRealMatrix(x);
/* 139 */     this.yVector = (RealVector)new ArrayRealVector(y);
/*     */   }
/*     */   
/*     */   protected void newYSampleData(double[] y) {
/* 150 */     if (y == null)
/* 151 */       throw new NullArgumentException(); 
/* 153 */     if (y.length == 0)
/* 154 */       throw new NoDataException(); 
/* 156 */     this.yVector = (RealVector)new ArrayRealVector(y);
/*     */   }
/*     */   
/*     */   protected void newXSampleData(double[][] x) {
/* 185 */     if (x == null)
/* 186 */       throw new NullArgumentException(); 
/* 188 */     if (x.length == 0)
/* 189 */       throw new NoDataException(); 
/* 191 */     if (this.noIntercept) {
/* 192 */       this.xMatrix = (RealMatrix)new Array2DRowRealMatrix(x, true);
/*     */     } else {
/* 194 */       int nVars = (x[0]).length;
/* 195 */       double[][] xAug = new double[x.length][nVars + 1];
/* 196 */       for (int i = 0; i < x.length; i++) {
/* 197 */         if ((x[i]).length != nVars)
/* 198 */           throw new DimensionMismatchException((x[i]).length, nVars); 
/* 200 */         xAug[i][0] = 1.0D;
/* 201 */         System.arraycopy(x[i], 0, xAug[i], 1, nVars);
/*     */       } 
/* 203 */       this.xMatrix = (RealMatrix)new Array2DRowRealMatrix(xAug, false);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void validateSampleData(double[][] x, double[] y) {
/* 226 */     if (x == null || y == null)
/* 227 */       throw new NullArgumentException(); 
/* 229 */     if (x.length != y.length)
/* 230 */       throw new DimensionMismatchException(y.length, x.length); 
/* 232 */     if (x.length == 0)
/* 233 */       throw new NoDataException(); 
/* 235 */     if ((x[0]).length + 1 > x.length)
/* 236 */       throw new MathIllegalArgumentException(LocalizedFormats.NOT_ENOUGH_DATA_FOR_NUMBER_OF_PREDICTORS, new Object[] { Integer.valueOf(x.length), Integer.valueOf((x[0]).length) }); 
/*     */   }
/*     */   
/*     */   protected void validateCovarianceData(double[][] x, double[][] covariance) {
/* 253 */     if (x.length != covariance.length)
/* 254 */       throw new DimensionMismatchException(x.length, covariance.length); 
/* 256 */     if (covariance.length > 0 && covariance.length != (covariance[0]).length)
/* 257 */       throw new NonSquareMatrixException(covariance.length, (covariance[0]).length); 
/*     */   }
/*     */   
/*     */   public double[] estimateRegressionParameters() {
/* 265 */     RealVector b = calculateBeta();
/* 266 */     return b.toArray();
/*     */   }
/*     */   
/*     */   public double[] estimateResiduals() {
/* 273 */     RealVector b = calculateBeta();
/* 274 */     RealVector e = this.yVector.subtract(this.xMatrix.operate(b));
/* 275 */     return e.toArray();
/*     */   }
/*     */   
/*     */   public double[][] estimateRegressionParametersVariance() {
/* 282 */     return calculateBetaVariance().getData();
/*     */   }
/*     */   
/*     */   public double[] estimateRegressionParametersStandardErrors() {
/* 289 */     double[][] betaVariance = estimateRegressionParametersVariance();
/* 290 */     double sigma = calculateErrorVariance();
/* 291 */     int length = (betaVariance[0]).length;
/* 292 */     double[] result = new double[length];
/* 293 */     for (int i = 0; i < length; i++)
/* 294 */       result[i] = FastMath.sqrt(sigma * betaVariance[i][i]); 
/* 296 */     return result;
/*     */   }
/*     */   
/*     */   public double estimateRegressandVariance() {
/* 303 */     return calculateYVariance();
/*     */   }
/*     */   
/*     */   public double estimateErrorVariance() {
/* 313 */     return calculateErrorVariance();
/*     */   }
/*     */   
/*     */   public double estimateRegressionStandardError() {
/* 324 */     return Math.sqrt(estimateErrorVariance());
/*     */   }
/*     */   
/*     */   protected abstract RealVector calculateBeta();
/*     */   
/*     */   protected abstract RealMatrix calculateBetaVariance();
/*     */   
/*     */   protected double calculateYVariance() {
/* 349 */     return (new Variance()).evaluate(this.yVector.toArray());
/*     */   }
/*     */   
/*     */   protected double calculateErrorVariance() {
/* 364 */     RealVector residuals = calculateResiduals();
/* 365 */     return residuals.dotProduct(residuals) / (this.xMatrix.getRowDimension() - this.xMatrix.getColumnDimension());
/*     */   }
/*     */   
/*     */   protected RealVector calculateResiduals() {
/* 380 */     RealVector b = calculateBeta();
/* 381 */     return this.yVector.subtract(this.xMatrix.operate(b));
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\stat\regression\AbstractMultipleLinearRegression.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */