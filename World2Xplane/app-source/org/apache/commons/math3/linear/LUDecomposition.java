/*     */ package org.apache.commons.math3.linear;
/*     */ 
/*     */ import org.apache.commons.math3.exception.DimensionMismatchException;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ 
/*     */ public class LUDecomposition {
/*     */   private static final double DEFAULT_TOO_SMALL = 1.0E-11D;
/*     */   
/*     */   private final double[][] lu;
/*     */   
/*     */   private final int[] pivot;
/*     */   
/*     */   private boolean even;
/*     */   
/*     */   private boolean singular;
/*     */   
/*     */   private RealMatrix cachedL;
/*     */   
/*     */   private RealMatrix cachedU;
/*     */   
/*     */   private RealMatrix cachedP;
/*     */   
/*     */   public LUDecomposition(RealMatrix matrix) {
/*  76 */     this(matrix, 1.0E-11D);
/*     */   }
/*     */   
/*     */   public LUDecomposition(RealMatrix matrix, double singularityThreshold) {
/*  87 */     if (!matrix.isSquare())
/*  88 */       throw new NonSquareMatrixException(matrix.getRowDimension(), matrix.getColumnDimension()); 
/*  92 */     int m = matrix.getColumnDimension();
/*  93 */     this.lu = matrix.getData();
/*  94 */     this.pivot = new int[m];
/*  95 */     this.cachedL = null;
/*  96 */     this.cachedU = null;
/*  97 */     this.cachedP = null;
/* 100 */     for (int row = 0; row < m; row++)
/* 101 */       this.pivot[row] = row; 
/* 103 */     this.even = true;
/* 104 */     this.singular = false;
/* 107 */     for (int col = 0; col < m; col++) {
/* 110 */       for (int i = 0; i < col; i++) {
/* 111 */         double[] luRow = this.lu[i];
/* 112 */         double sum = luRow[col];
/* 113 */         for (int n = 0; n < i; n++)
/* 114 */           sum -= luRow[n] * this.lu[n][col]; 
/* 116 */         luRow[col] = sum;
/*     */       } 
/* 120 */       int max = col;
/* 121 */       double largest = Double.NEGATIVE_INFINITY;
/* 122 */       for (int j = col; j < m; j++) {
/* 123 */         double[] luRow = this.lu[j];
/* 124 */         double sum = luRow[col];
/* 125 */         for (int n = 0; n < col; n++)
/* 126 */           sum -= luRow[n] * this.lu[n][col]; 
/* 128 */         luRow[col] = sum;
/* 131 */         if (FastMath.abs(sum) > largest) {
/* 132 */           largest = FastMath.abs(sum);
/* 133 */           max = j;
/*     */         } 
/*     */       } 
/* 138 */       if (FastMath.abs(this.lu[max][col]) < singularityThreshold) {
/* 139 */         this.singular = true;
/*     */         return;
/*     */       } 
/* 144 */       if (max != col) {
/* 145 */         double tmp = 0.0D;
/* 146 */         double[] luMax = this.lu[max];
/* 147 */         double[] luCol = this.lu[col];
/* 148 */         for (int n = 0; n < m; n++) {
/* 149 */           tmp = luMax[n];
/* 150 */           luMax[n] = luCol[n];
/* 151 */           luCol[n] = tmp;
/*     */         } 
/* 153 */         int temp = this.pivot[max];
/* 154 */         this.pivot[max] = this.pivot[col];
/* 155 */         this.pivot[col] = temp;
/* 156 */         this.even = !this.even;
/*     */       } 
/* 160 */       double luDiag = this.lu[col][col];
/* 161 */       for (int k = col + 1; k < m; k++)
/* 162 */         this.lu[k][col] = this.lu[k][col] / luDiag; 
/*     */     } 
/*     */   }
/*     */   
/*     */   public RealMatrix getL() {
/* 173 */     if (this.cachedL == null && !this.singular) {
/* 174 */       int m = this.pivot.length;
/* 175 */       this.cachedL = MatrixUtils.createRealMatrix(m, m);
/* 176 */       for (int i = 0; i < m; i++) {
/* 177 */         double[] luI = this.lu[i];
/* 178 */         for (int j = 0; j < i; j++)
/* 179 */           this.cachedL.setEntry(i, j, luI[j]); 
/* 181 */         this.cachedL.setEntry(i, i, 1.0D);
/*     */       } 
/*     */     } 
/* 184 */     return this.cachedL;
/*     */   }
/*     */   
/*     */   public RealMatrix getU() {
/* 193 */     if (this.cachedU == null && !this.singular) {
/* 194 */       int m = this.pivot.length;
/* 195 */       this.cachedU = MatrixUtils.createRealMatrix(m, m);
/* 196 */       for (int i = 0; i < m; i++) {
/* 197 */         double[] luI = this.lu[i];
/* 198 */         for (int j = i; j < m; j++)
/* 199 */           this.cachedU.setEntry(i, j, luI[j]); 
/*     */       } 
/*     */     } 
/* 203 */     return this.cachedU;
/*     */   }
/*     */   
/*     */   public RealMatrix getP() {
/* 216 */     if (this.cachedP == null && !this.singular) {
/* 217 */       int m = this.pivot.length;
/* 218 */       this.cachedP = MatrixUtils.createRealMatrix(m, m);
/* 219 */       for (int i = 0; i < m; i++)
/* 220 */         this.cachedP.setEntry(i, this.pivot[i], 1.0D); 
/*     */     } 
/* 223 */     return this.cachedP;
/*     */   }
/*     */   
/*     */   public int[] getPivot() {
/* 232 */     return (int[])this.pivot.clone();
/*     */   }
/*     */   
/*     */   public double getDeterminant() {
/* 240 */     if (this.singular)
/* 241 */       return 0.0D; 
/* 243 */     int m = this.pivot.length;
/* 244 */     double determinant = this.even ? 1.0D : -1.0D;
/* 245 */     for (int i = 0; i < m; i++)
/* 246 */       determinant *= this.lu[i][i]; 
/* 248 */     return determinant;
/*     */   }
/*     */   
/*     */   public DecompositionSolver getSolver() {
/* 258 */     return new Solver(this.lu, this.pivot, this.singular);
/*     */   }
/*     */   
/*     */   private static class Solver implements DecompositionSolver {
/*     */     private final double[][] lu;
/*     */     
/*     */     private final int[] pivot;
/*     */     
/*     */     private final boolean singular;
/*     */     
/*     */     private Solver(double[][] lu, int[] pivot, boolean singular) {
/* 280 */       this.lu = lu;
/* 281 */       this.pivot = pivot;
/* 282 */       this.singular = singular;
/*     */     }
/*     */     
/*     */     public boolean isNonSingular() {
/* 287 */       return !this.singular;
/*     */     }
/*     */     
/*     */     public RealVector solve(RealVector b) {
/* 292 */       int m = this.pivot.length;
/* 293 */       if (b.getDimension() != m)
/* 294 */         throw new DimensionMismatchException(b.getDimension(), m); 
/* 296 */       if (this.singular)
/* 297 */         throw new SingularMatrixException(); 
/* 300 */       double[] bp = new double[m];
/* 303 */       for (int row = 0; row < m; row++)
/* 304 */         bp[row] = b.getEntry(this.pivot[row]); 
/*     */       int col;
/* 308 */       for (col = 0; col < m; col++) {
/* 309 */         double bpCol = bp[col];
/* 310 */         for (int i = col + 1; i < m; i++)
/* 311 */           bp[i] = bp[i] - bpCol * this.lu[i][col]; 
/*     */       } 
/* 316 */       for (col = m - 1; col >= 0; col--) {
/* 317 */         bp[col] = bp[col] / this.lu[col][col];
/* 318 */         double bpCol = bp[col];
/* 319 */         for (int i = 0; i < col; i++)
/* 320 */           bp[i] = bp[i] - bpCol * this.lu[i][col]; 
/*     */       } 
/* 324 */       return new ArrayRealVector(bp, false);
/*     */     }
/*     */     
/*     */     public RealMatrix solve(RealMatrix b) {
/* 330 */       int m = this.pivot.length;
/* 331 */       if (b.getRowDimension() != m)
/* 332 */         throw new DimensionMismatchException(b.getRowDimension(), m); 
/* 334 */       if (this.singular)
/* 335 */         throw new SingularMatrixException(); 
/* 338 */       int nColB = b.getColumnDimension();
/* 341 */       double[][] bp = new double[m][nColB];
/* 342 */       for (int row = 0; row < m; row++) {
/* 343 */         double[] bpRow = bp[row];
/* 344 */         int pRow = this.pivot[row];
/* 345 */         for (int i = 0; i < nColB; i++)
/* 346 */           bpRow[i] = b.getEntry(pRow, i); 
/*     */       } 
/*     */       int col;
/* 351 */       for (col = 0; col < m; col++) {
/* 352 */         double[] bpCol = bp[col];
/* 353 */         for (int i = col + 1; i < m; i++) {
/* 354 */           double[] bpI = bp[i];
/* 355 */           double luICol = this.lu[i][col];
/* 356 */           for (int j = 0; j < nColB; j++)
/* 357 */             bpI[j] = bpI[j] - bpCol[j] * luICol; 
/*     */         } 
/*     */       } 
/* 363 */       for (col = m - 1; col >= 0; col--) {
/* 364 */         double[] bpCol = bp[col];
/* 365 */         double luDiag = this.lu[col][col];
/* 366 */         for (int j = 0; j < nColB; j++)
/* 367 */           bpCol[j] = bpCol[j] / luDiag; 
/* 369 */         for (int i = 0; i < col; i++) {
/* 370 */           double[] bpI = bp[i];
/* 371 */           double luICol = this.lu[i][col];
/* 372 */           for (int k = 0; k < nColB; k++)
/* 373 */             bpI[k] = bpI[k] - bpCol[k] * luICol; 
/*     */         } 
/*     */       } 
/* 378 */       return new Array2DRowRealMatrix(bp, false);
/*     */     }
/*     */     
/*     */     public RealMatrix getInverse() {
/* 383 */       return solve(MatrixUtils.createRealIdentityMatrix(this.pivot.length));
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\linear\LUDecomposition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */