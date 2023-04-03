/*     */ package org.apache.commons.math3.linear;
/*     */ 
/*     */ import org.apache.commons.math3.exception.DimensionMismatchException;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ 
/*     */ public class CholeskyDecomposition {
/*     */   public static final double DEFAULT_RELATIVE_SYMMETRY_THRESHOLD = 1.0E-15D;
/*     */   
/*     */   public static final double DEFAULT_ABSOLUTE_POSITIVITY_THRESHOLD = 1.0E-10D;
/*     */   
/*     */   private double[][] lTData;
/*     */   
/*     */   private RealMatrix cachedL;
/*     */   
/*     */   private RealMatrix cachedLT;
/*     */   
/*     */   public CholeskyDecomposition(RealMatrix matrix) {
/*  85 */     this(matrix, 1.0E-15D, 1.0E-10D);
/*     */   }
/*     */   
/*     */   public CholeskyDecomposition(RealMatrix matrix, double relativeSymmetryThreshold, double absolutePositivityThreshold) {
/* 107 */     if (!matrix.isSquare())
/* 108 */       throw new NonSquareMatrixException(matrix.getRowDimension(), matrix.getColumnDimension()); 
/* 112 */     int order = matrix.getRowDimension();
/* 113 */     this.lTData = matrix.getData();
/* 114 */     this.cachedL = null;
/* 115 */     this.cachedLT = null;
/*     */     int i;
/* 118 */     for (i = 0; i < order; i++) {
/* 119 */       double[] lI = this.lTData[i];
/* 122 */       for (int j = i + 1; j < order; j++) {
/* 123 */         double[] lJ = this.lTData[j];
/* 124 */         double lIJ = lI[j];
/* 125 */         double lJI = lJ[i];
/* 126 */         double maxDelta = relativeSymmetryThreshold * FastMath.max(FastMath.abs(lIJ), FastMath.abs(lJI));
/* 128 */         if (FastMath.abs(lIJ - lJI) > maxDelta)
/* 129 */           throw new NonSymmetricMatrixException(i, j, relativeSymmetryThreshold); 
/* 131 */         lJ[i] = 0.0D;
/*     */       } 
/*     */     } 
/* 136 */     for (i = 0; i < order; i++) {
/* 138 */       double[] ltI = this.lTData[i];
/* 141 */       if (ltI[i] <= absolutePositivityThreshold)
/* 142 */         throw new NonPositiveDefiniteMatrixException(ltI[i], i, absolutePositivityThreshold); 
/* 145 */       ltI[i] = FastMath.sqrt(ltI[i]);
/* 146 */       double inverse = 1.0D / ltI[i];
/* 148 */       for (int q = order - 1; q > i; q--) {
/* 149 */         ltI[q] = ltI[q] * inverse;
/* 150 */         double[] ltQ = this.lTData[q];
/* 151 */         for (int p = q; p < order; p++)
/* 152 */           ltQ[p] = ltQ[p] - ltI[q] * ltI[p]; 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public RealMatrix getL() {
/* 164 */     if (this.cachedL == null)
/* 165 */       this.cachedL = getLT().transpose(); 
/* 167 */     return this.cachedL;
/*     */   }
/*     */   
/*     */   public RealMatrix getLT() {
/* 177 */     if (this.cachedLT == null)
/* 178 */       this.cachedLT = MatrixUtils.createRealMatrix(this.lTData); 
/* 182 */     return this.cachedLT;
/*     */   }
/*     */   
/*     */   public double getDeterminant() {
/* 190 */     double determinant = 1.0D;
/* 191 */     for (int i = 0; i < this.lTData.length; i++) {
/* 192 */       double lTii = this.lTData[i][i];
/* 193 */       determinant *= lTii * lTii;
/*     */     } 
/* 195 */     return determinant;
/*     */   }
/*     */   
/*     */   public DecompositionSolver getSolver() {
/* 203 */     return new Solver(this.lTData);
/*     */   }
/*     */   
/*     */   private static class Solver implements DecompositionSolver {
/*     */     private final double[][] lTData;
/*     */     
/*     */     private Solver(double[][] lTData) {
/* 216 */       this.lTData = lTData;
/*     */     }
/*     */     
/*     */     public boolean isNonSingular() {
/* 222 */       return true;
/*     */     }
/*     */     
/*     */     public RealVector solve(RealVector b) {
/* 227 */       int m = this.lTData.length;
/* 228 */       if (b.getDimension() != m)
/* 229 */         throw new DimensionMismatchException(b.getDimension(), m); 
/* 232 */       double[] x = b.toArray();
/*     */       int j;
/* 235 */       for (j = 0; j < m; j++) {
/* 236 */         double[] lJ = this.lTData[j];
/* 237 */         x[j] = x[j] / lJ[j];
/* 238 */         double xJ = x[j];
/* 239 */         for (int i = j + 1; i < m; i++)
/* 240 */           x[i] = x[i] - xJ * lJ[i]; 
/*     */       } 
/* 245 */       for (j = m - 1; j >= 0; j--) {
/* 246 */         x[j] = x[j] / this.lTData[j][j];
/* 247 */         double xJ = x[j];
/* 248 */         for (int i = 0; i < j; i++)
/* 249 */           x[i] = x[i] - xJ * this.lTData[i][j]; 
/*     */       } 
/* 253 */       return new ArrayRealVector(x, false);
/*     */     }
/*     */     
/*     */     public RealMatrix solve(RealMatrix b) {
/* 258 */       int m = this.lTData.length;
/* 259 */       if (b.getRowDimension() != m)
/* 260 */         throw new DimensionMismatchException(b.getRowDimension(), m); 
/* 263 */       int nColB = b.getColumnDimension();
/* 264 */       double[][] x = b.getData();
/*     */       int j;
/* 267 */       for (j = 0; j < m; j++) {
/* 268 */         double[] lJ = this.lTData[j];
/* 269 */         double lJJ = lJ[j];
/* 270 */         double[] xJ = x[j];
/* 271 */         for (int k = 0; k < nColB; k++)
/* 272 */           xJ[k] = xJ[k] / lJJ; 
/* 274 */         for (int i = j + 1; i < m; i++) {
/* 275 */           double[] xI = x[i];
/* 276 */           double lJI = lJ[i];
/* 277 */           for (int n = 0; n < nColB; n++)
/* 278 */             xI[n] = xI[n] - xJ[n] * lJI; 
/*     */         } 
/*     */       } 
/* 284 */       for (j = m - 1; j >= 0; j--) {
/* 285 */         double lJJ = this.lTData[j][j];
/* 286 */         double[] xJ = x[j];
/* 287 */         for (int k = 0; k < nColB; k++)
/* 288 */           xJ[k] = xJ[k] / lJJ; 
/* 290 */         for (int i = 0; i < j; i++) {
/* 291 */           double[] xI = x[i];
/* 292 */           double lIJ = this.lTData[i][j];
/* 293 */           for (int n = 0; n < nColB; n++)
/* 294 */             xI[n] = xI[n] - xJ[n] * lIJ; 
/*     */         } 
/*     */       } 
/* 299 */       return new Array2DRowRealMatrix(x);
/*     */     }
/*     */     
/*     */     public RealMatrix getInverse() {
/* 304 */       return solve(MatrixUtils.createRealIdentityMatrix(this.lTData.length));
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\linear\CholeskyDecomposition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */