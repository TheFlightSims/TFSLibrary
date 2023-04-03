/*     */ package org.apache.commons.math3.stat.regression;
/*     */ 
/*     */ import org.apache.commons.math3.linear.Array2DRowRealMatrix;
/*     */ import org.apache.commons.math3.linear.LUDecomposition;
/*     */ import org.apache.commons.math3.linear.RealMatrix;
/*     */ import org.apache.commons.math3.linear.RealVector;
/*     */ 
/*     */ public class GLSMultipleLinearRegression extends AbstractMultipleLinearRegression {
/*     */   private RealMatrix Omega;
/*     */   
/*     */   private RealMatrix OmegaInverse;
/*     */   
/*     */   public void newSampleData(double[] y, double[][] x, double[][] covariance) {
/*  57 */     validateSampleData(x, y);
/*  58 */     newYSampleData(y);
/*  59 */     newXSampleData(x);
/*  60 */     validateCovarianceData(x, covariance);
/*  61 */     newCovarianceData(covariance);
/*     */   }
/*     */   
/*     */   protected void newCovarianceData(double[][] omega) {
/*  70 */     this.Omega = (RealMatrix)new Array2DRowRealMatrix(omega);
/*  71 */     this.OmegaInverse = null;
/*     */   }
/*     */   
/*     */   protected RealMatrix getOmegaInverse() {
/*  80 */     if (this.OmegaInverse == null)
/*  81 */       this.OmegaInverse = (new LUDecomposition(this.Omega)).getSolver().getInverse(); 
/*  83 */     return this.OmegaInverse;
/*     */   }
/*     */   
/*     */   protected RealVector calculateBeta() {
/*  95 */     RealMatrix OI = getOmegaInverse();
/*  96 */     RealMatrix XT = getX().transpose();
/*  97 */     RealMatrix XTOIX = XT.multiply(OI).multiply(getX());
/*  98 */     RealMatrix inverse = (new LUDecomposition(XTOIX)).getSolver().getInverse();
/*  99 */     return inverse.multiply(XT).multiply(OI).operate(getY());
/*     */   }
/*     */   
/*     */   protected RealMatrix calculateBetaVariance() {
/* 111 */     RealMatrix OI = getOmegaInverse();
/* 112 */     RealMatrix XTOIX = getX().transpose().multiply(OI).multiply(getX());
/* 113 */     return (new LUDecomposition(XTOIX)).getSolver().getInverse();
/*     */   }
/*     */   
/*     */   protected double calculateErrorVariance() {
/* 130 */     RealVector residuals = calculateResiduals();
/* 131 */     double t = residuals.dotProduct(getOmegaInverse().operate(residuals));
/* 132 */     return t / (getX().getRowDimension() - getX().getColumnDimension());
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\stat\regression\GLSMultipleLinearRegression.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */