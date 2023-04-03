/*     */ package org.apache.commons.math3.stat.regression;
/*     */ 
/*     */ import org.apache.commons.math3.linear.Array2DRowRealMatrix;
/*     */ import org.apache.commons.math3.linear.LUDecomposition;
/*     */ import org.apache.commons.math3.linear.QRDecomposition;
/*     */ import org.apache.commons.math3.linear.RealMatrix;
/*     */ import org.apache.commons.math3.linear.RealVector;
/*     */ import org.apache.commons.math3.stat.StatUtils;
/*     */ import org.apache.commons.math3.stat.descriptive.moment.SecondMoment;
/*     */ 
/*     */ public class OLSMultipleLinearRegression extends AbstractMultipleLinearRegression {
/*  57 */   private QRDecomposition qr = null;
/*     */   
/*     */   public void newSampleData(double[] y, double[][] x) {
/*  69 */     validateSampleData(x, y);
/*  70 */     newYSampleData(y);
/*  71 */     newXSampleData(x);
/*     */   }
/*     */   
/*     */   public void newSampleData(double[] data, int nobs, int nvars) {
/*  80 */     super.newSampleData(data, nobs, nvars);
/*  81 */     this.qr = new QRDecomposition(getX());
/*     */   }
/*     */   
/*     */   public RealMatrix calculateHat() {
/* 101 */     RealMatrix Q = this.qr.getQ();
/* 102 */     int p = this.qr.getR().getColumnDimension();
/* 103 */     int n = Q.getColumnDimension();
/* 104 */     Array2DRowRealMatrix augI = new Array2DRowRealMatrix(n, n);
/* 105 */     double[][] augIData = augI.getDataRef();
/* 106 */     for (int i = 0; i < n; i++) {
/* 107 */       for (int j = 0; j < n; j++) {
/* 108 */         if (i == j && i < p) {
/* 109 */           augIData[i][j] = 1.0D;
/*     */         } else {
/* 111 */           augIData[i][j] = 0.0D;
/*     */         } 
/*     */       } 
/*     */     } 
/* 117 */     return Q.multiply((RealMatrix)augI).multiply(Q.transpose());
/*     */   }
/*     */   
/*     */   public double calculateTotalSumOfSquares() {
/* 134 */     if (isNoIntercept())
/* 135 */       return StatUtils.sumSq(getY().toArray()); 
/* 137 */     return (new SecondMoment()).evaluate(getY().toArray());
/*     */   }
/*     */   
/*     */   public double calculateResidualSumOfSquares() {
/* 148 */     RealVector residuals = calculateResiduals();
/* 149 */     return residuals.dotProduct(residuals);
/*     */   }
/*     */   
/*     */   public double calculateRSquared() {
/* 163 */     return 1.0D - calculateResidualSumOfSquares() / calculateTotalSumOfSquares();
/*     */   }
/*     */   
/*     */   public double calculateAdjustedRSquared() {
/* 183 */     double n = getX().getRowDimension();
/* 184 */     if (isNoIntercept())
/* 185 */       return 1.0D - (1.0D - calculateRSquared()) * n / (n - getX().getColumnDimension()); 
/* 187 */     return 1.0D - calculateResidualSumOfSquares() * (n - 1.0D) / calculateTotalSumOfSquares() * (n - getX().getColumnDimension());
/*     */   }
/*     */   
/*     */   protected void newXSampleData(double[][] x) {
/* 199 */     super.newXSampleData(x);
/* 200 */     this.qr = new QRDecomposition(getX());
/*     */   }
/*     */   
/*     */   protected RealVector calculateBeta() {
/* 210 */     return this.qr.getSolver().solve(getY());
/*     */   }
/*     */   
/*     */   protected RealMatrix calculateBetaVariance() {
/* 226 */     int p = getX().getColumnDimension();
/* 227 */     RealMatrix Raug = this.qr.getR().getSubMatrix(0, p - 1, 0, p - 1);
/* 228 */     RealMatrix Rinv = (new LUDecomposition(Raug)).getSolver().getInverse();
/* 229 */     return Rinv.multiply(Rinv.transpose());
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\stat\regression\OLSMultipleLinearRegression.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */