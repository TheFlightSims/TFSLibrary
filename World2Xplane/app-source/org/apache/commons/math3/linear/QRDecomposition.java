/*     */ package org.apache.commons.math3.linear;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ import org.apache.commons.math3.exception.DimensionMismatchException;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ 
/*     */ public class QRDecomposition {
/*     */   private double[][] qrt;
/*     */   
/*     */   private double[] rDiag;
/*     */   
/*     */   private RealMatrix cachedQ;
/*     */   
/*     */   private RealMatrix cachedQT;
/*     */   
/*     */   private RealMatrix cachedR;
/*     */   
/*     */   private RealMatrix cachedH;
/*     */   
/*     */   private final double threshold;
/*     */   
/*     */   public QRDecomposition(RealMatrix matrix) {
/*  81 */     this(matrix, 0.0D);
/*     */   }
/*     */   
/*     */   public QRDecomposition(RealMatrix matrix, double threshold) {
/*  92 */     this.threshold = threshold;
/*  94 */     int m = matrix.getRowDimension();
/*  95 */     int n = matrix.getColumnDimension();
/*  96 */     this.qrt = matrix.transpose().getData();
/*  97 */     this.rDiag = new double[FastMath.min(m, n)];
/*  98 */     this.cachedQ = null;
/*  99 */     this.cachedQT = null;
/* 100 */     this.cachedR = null;
/* 101 */     this.cachedH = null;
/* 108 */     for (int minor = 0; minor < FastMath.min(m, n); minor++) {
/* 110 */       double[] qrtMinor = this.qrt[minor];
/* 119 */       double xNormSqr = 0.0D;
/* 120 */       for (int row = minor; row < m; row++) {
/* 121 */         double c = qrtMinor[row];
/* 122 */         xNormSqr += c * c;
/*     */       } 
/* 124 */       double a = (qrtMinor[minor] > 0.0D) ? -FastMath.sqrt(xNormSqr) : FastMath.sqrt(xNormSqr);
/* 125 */       this.rDiag[minor] = a;
/* 127 */       if (a != 0.0D) {
/* 137 */         qrtMinor[minor] = qrtMinor[minor] - a;
/* 151 */         for (int col = minor + 1; col < n; col++) {
/* 152 */           double[] qrtCol = this.qrt[col];
/* 153 */           double alpha = 0.0D;
/*     */           int i;
/* 154 */           for (i = minor; i < m; i++)
/* 155 */             alpha -= qrtCol[i] * qrtMinor[i]; 
/* 157 */           alpha /= a * qrtMinor[minor];
/* 160 */           for (i = minor; i < m; i++)
/* 161 */             qrtCol[i] = qrtCol[i] - alpha * qrtMinor[i]; 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public RealMatrix getR() {
/* 175 */     if (this.cachedR == null) {
/* 178 */       int n = this.qrt.length;
/* 179 */       int m = (this.qrt[0]).length;
/* 180 */       double[][] ra = new double[m][n];
/* 182 */       for (int row = FastMath.min(m, n) - 1; row >= 0; row--) {
/* 183 */         ra[row][row] = this.rDiag[row];
/* 184 */         for (int col = row + 1; col < n; col++)
/* 185 */           ra[row][col] = this.qrt[col][row]; 
/*     */       } 
/* 188 */       this.cachedR = MatrixUtils.createRealMatrix(ra);
/*     */     } 
/* 192 */     return this.cachedR;
/*     */   }
/*     */   
/*     */   public RealMatrix getQ() {
/* 201 */     if (this.cachedQ == null)
/* 202 */       this.cachedQ = getQT().transpose(); 
/* 204 */     return this.cachedQ;
/*     */   }
/*     */   
/*     */   public RealMatrix getQT() {
/* 213 */     if (this.cachedQT == null) {
/* 216 */       int n = this.qrt.length;
/* 217 */       int m = (this.qrt[0]).length;
/* 218 */       double[][] qta = new double[m][m];
/*     */       int minor;
/* 225 */       for (minor = m - 1; minor >= FastMath.min(m, n); minor--)
/* 226 */         qta[minor][minor] = 1.0D; 
/* 229 */       for (minor = FastMath.min(m, n) - 1; minor >= 0; minor--) {
/* 230 */         double[] qrtMinor = this.qrt[minor];
/* 231 */         qta[minor][minor] = 1.0D;
/* 232 */         if (qrtMinor[minor] != 0.0D)
/* 233 */           for (int col = minor; col < m; col++) {
/* 234 */             double alpha = 0.0D;
/*     */             int row;
/* 235 */             for (row = minor; row < m; row++)
/* 236 */               alpha -= qta[col][row] * qrtMinor[row]; 
/* 238 */             alpha /= this.rDiag[minor] * qrtMinor[minor];
/* 240 */             for (row = minor; row < m; row++)
/* 241 */               qta[col][row] = qta[col][row] + -alpha * qrtMinor[row]; 
/*     */           }  
/*     */       } 
/* 246 */       this.cachedQT = MatrixUtils.createRealMatrix(qta);
/*     */     } 
/* 250 */     return this.cachedQT;
/*     */   }
/*     */   
/*     */   public RealMatrix getH() {
/* 261 */     if (this.cachedH == null) {
/* 263 */       int n = this.qrt.length;
/* 264 */       int m = (this.qrt[0]).length;
/* 265 */       double[][] ha = new double[m][n];
/* 266 */       for (int i = 0; i < m; i++) {
/* 267 */         for (int j = 0; j < FastMath.min(i + 1, n); j++)
/* 268 */           ha[i][j] = this.qrt[j][i] / -this.rDiag[j]; 
/*     */       } 
/* 271 */       this.cachedH = MatrixUtils.createRealMatrix(ha);
/*     */     } 
/* 275 */     return this.cachedH;
/*     */   }
/*     */   
/*     */   public DecompositionSolver getSolver() {
/* 283 */     return new Solver(this.qrt, this.rDiag, this.threshold);
/*     */   }
/*     */   
/*     */   private static class Solver implements DecompositionSolver {
/*     */     private final double[][] qrt;
/*     */     
/*     */     private final double[] rDiag;
/*     */     
/*     */     private final double threshold;
/*     */     
/*     */     private Solver(double[][] qrt, double[] rDiag, double threshold) {
/* 310 */       this.qrt = qrt;
/* 311 */       this.rDiag = rDiag;
/* 312 */       this.threshold = threshold;
/*     */     }
/*     */     
/*     */     public boolean isNonSingular() {
/* 317 */       for (double diag : this.rDiag) {
/* 318 */         if (FastMath.abs(diag) <= this.threshold)
/* 319 */           return false; 
/*     */       } 
/* 322 */       return true;
/*     */     }
/*     */     
/*     */     public RealVector solve(RealVector b) {
/* 327 */       int n = this.qrt.length;
/* 328 */       int m = (this.qrt[0]).length;
/* 329 */       if (b.getDimension() != m)
/* 330 */         throw new DimensionMismatchException(b.getDimension(), m); 
/* 332 */       if (!isNonSingular())
/* 333 */         throw new SingularMatrixException(); 
/* 336 */       double[] x = new double[n];
/* 337 */       double[] y = b.toArray();
/* 340 */       for (int minor = 0; minor < FastMath.min(m, n); minor++) {
/* 342 */         double[] qrtMinor = this.qrt[minor];
/* 343 */         double dotProduct = 0.0D;
/*     */         int i;
/* 344 */         for (i = minor; i < m; i++)
/* 345 */           dotProduct += y[i] * qrtMinor[i]; 
/* 347 */         dotProduct /= this.rDiag[minor] * qrtMinor[minor];
/* 349 */         for (i = minor; i < m; i++)
/* 350 */           y[i] = y[i] + dotProduct * qrtMinor[i]; 
/*     */       } 
/* 355 */       for (int row = this.rDiag.length - 1; row >= 0; row--) {
/* 356 */         y[row] = y[row] / this.rDiag[row];
/* 357 */         double yRow = y[row];
/* 358 */         double[] qrtRow = this.qrt[row];
/* 359 */         x[row] = yRow;
/* 360 */         for (int i = 0; i < row; i++)
/* 361 */           y[i] = y[i] - yRow * qrtRow[i]; 
/*     */       } 
/* 365 */       return new ArrayRealVector(x, false);
/*     */     }
/*     */     
/*     */     public RealMatrix solve(RealMatrix b) {
/* 370 */       int n = this.qrt.length;
/* 371 */       int m = (this.qrt[0]).length;
/* 372 */       if (b.getRowDimension() != m)
/* 373 */         throw new DimensionMismatchException(b.getRowDimension(), m); 
/* 375 */       if (!isNonSingular())
/* 376 */         throw new SingularMatrixException(); 
/* 379 */       int columns = b.getColumnDimension();
/* 380 */       int blockSize = 52;
/* 381 */       int cBlocks = (columns + 52 - 1) / 52;
/* 382 */       double[][] xBlocks = BlockRealMatrix.createBlocksLayout(n, columns);
/* 383 */       double[][] y = new double[b.getRowDimension()][52];
/* 384 */       double[] alpha = new double[52];
/* 386 */       for (int kBlock = 0; kBlock < cBlocks; kBlock++) {
/* 387 */         int kStart = kBlock * 52;
/* 388 */         int kEnd = FastMath.min(kStart + 52, columns);
/* 389 */         int kWidth = kEnd - kStart;
/* 392 */         b.copySubMatrix(0, m - 1, kStart, kEnd - 1, y);
/* 395 */         for (int minor = 0; minor < FastMath.min(m, n); minor++) {
/* 396 */           double[] qrtMinor = this.qrt[minor];
/* 397 */           double factor = 1.0D / this.rDiag[minor] * qrtMinor[minor];
/* 399 */           Arrays.fill(alpha, 0, kWidth, 0.0D);
/* 400 */           for (int i = minor; i < m; i++) {
/* 401 */             double d = qrtMinor[i];
/* 402 */             double[] yRow = y[i];
/* 403 */             for (int i1 = 0; i1 < kWidth; i1++)
/* 404 */               alpha[i1] = alpha[i1] + d * yRow[i1]; 
/*     */           } 
/* 407 */           for (int k = 0; k < kWidth; k++)
/* 408 */             alpha[k] = alpha[k] * factor; 
/* 411 */           for (int row = minor; row < m; row++) {
/* 412 */             double d = qrtMinor[row];
/* 413 */             double[] yRow = y[row];
/* 414 */             for (int i1 = 0; i1 < kWidth; i1++)
/* 415 */               yRow[i1] = yRow[i1] + alpha[i1] * d; 
/*     */           } 
/*     */         } 
/* 421 */         for (int j = this.rDiag.length - 1; j >= 0; j--) {
/* 422 */           int jBlock = j / 52;
/* 423 */           int jStart = jBlock * 52;
/* 424 */           double factor = 1.0D / this.rDiag[j];
/* 425 */           double[] yJ = y[j];
/* 426 */           double[] xBlock = xBlocks[jBlock * cBlocks + kBlock];
/* 427 */           int index = (j - jStart) * kWidth;
/* 428 */           for (int k = 0; k < kWidth; k++) {
/* 429 */             yJ[k] = yJ[k] * factor;
/* 430 */             xBlock[index++] = yJ[k];
/*     */           } 
/* 433 */           double[] qrtJ = this.qrt[j];
/* 434 */           for (int i = 0; i < j; i++) {
/* 435 */             double rIJ = qrtJ[i];
/* 436 */             double[] yI = y[i];
/* 437 */             for (int i1 = 0; i1 < kWidth; i1++)
/* 438 */               yI[i1] = yI[i1] - yJ[i1] * rIJ; 
/*     */           } 
/*     */         } 
/*     */       } 
/* 444 */       return new BlockRealMatrix(n, columns, xBlocks, false);
/*     */     }
/*     */     
/*     */     public RealMatrix getInverse() {
/* 449 */       return solve(MatrixUtils.createRealIdentityMatrix(this.rDiag.length));
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\linear\QRDecomposition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */