/*     */ package org.apache.commons.math3.linear;
/*     */ 
/*     */ import org.apache.commons.math3.exception.DimensionMismatchException;
/*     */ import org.apache.commons.math3.exception.MaxCountExceededException;
/*     */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ 
/*     */ public class EigenDecomposition {
/*  69 */   private byte maxIter = 30;
/*     */   
/*     */   private double[] main;
/*     */   
/*     */   private double[] secondary;
/*     */   
/*     */   private TriDiagonalTransformer transformer;
/*     */   
/*     */   private double[] realEigenvalues;
/*     */   
/*     */   private double[] imagEigenvalues;
/*     */   
/*     */   private ArrayRealVector[] eigenvectors;
/*     */   
/*     */   private RealMatrix cachedV;
/*     */   
/*     */   private RealMatrix cachedD;
/*     */   
/*     */   private RealMatrix cachedVt;
/*     */   
/*     */   public EigenDecomposition(RealMatrix matrix, double splitTolerance) {
/* 103 */     if (isSymmetric(matrix, true)) {
/* 104 */       transformToTridiagonal(matrix);
/* 105 */       findEigenVectors(this.transformer.getQ().getData());
/*     */     } 
/*     */   }
/*     */   
/*     */   public EigenDecomposition(double[] main, double[] secondary, double splitTolerance) {
/* 121 */     this.main = (double[])main.clone();
/* 122 */     this.secondary = (double[])secondary.clone();
/* 123 */     this.transformer = null;
/* 124 */     int size = main.length;
/* 125 */     double[][] z = new double[size][size];
/* 126 */     for (int i = 0; i < size; i++)
/* 127 */       z[i][i] = 1.0D; 
/* 129 */     findEigenVectors(z);
/*     */   }
/*     */   
/*     */   private boolean isSymmetric(RealMatrix matrix, boolean raiseException) {
/* 144 */     int rows = matrix.getRowDimension();
/* 145 */     int columns = matrix.getColumnDimension();
/* 146 */     double eps = (10 * rows * columns) * 1.1102230246251565E-16D;
/* 147 */     for (int i = 0; i < rows; i++) {
/* 148 */       for (int j = i + 1; j < columns; j++) {
/* 149 */         double mij = matrix.getEntry(i, j);
/* 150 */         double mji = matrix.getEntry(j, i);
/* 151 */         if (FastMath.abs(mij - mji) > FastMath.max(FastMath.abs(mij), FastMath.abs(mji)) * eps) {
/* 153 */           if (raiseException)
/* 154 */             throw new NonSymmetricMatrixException(i, j, eps); 
/* 156 */           return false;
/*     */         } 
/*     */       } 
/*     */     } 
/* 160 */     return true;
/*     */   }
/*     */   
/*     */   public RealMatrix getV() {
/* 175 */     if (this.cachedV == null) {
/* 176 */       int m = this.eigenvectors.length;
/* 177 */       this.cachedV = MatrixUtils.createRealMatrix(m, m);
/* 178 */       for (int k = 0; k < m; k++)
/* 179 */         this.cachedV.setColumnVector(k, this.eigenvectors[k]); 
/*     */     } 
/* 183 */     return this.cachedV;
/*     */   }
/*     */   
/*     */   public RealMatrix getD() {
/* 199 */     if (this.cachedD == null)
/* 201 */       this.cachedD = MatrixUtils.createRealDiagonalMatrix(this.realEigenvalues); 
/* 203 */     return this.cachedD;
/*     */   }
/*     */   
/*     */   public RealMatrix getVT() {
/* 218 */     if (this.cachedVt == null) {
/* 219 */       int m = this.eigenvectors.length;
/* 220 */       this.cachedVt = MatrixUtils.createRealMatrix(m, m);
/* 221 */       for (int k = 0; k < m; k++)
/* 222 */         this.cachedVt.setRowVector(k, this.eigenvectors[k]); 
/*     */     } 
/* 228 */     return this.cachedVt;
/*     */   }
/*     */   
/*     */   public double[] getRealEigenvalues() {
/* 241 */     return (double[])this.realEigenvalues.clone();
/*     */   }
/*     */   
/*     */   public double getRealEigenvalue(int i) {
/* 257 */     return this.realEigenvalues[i];
/*     */   }
/*     */   
/*     */   public double[] getImagEigenvalues() {
/* 272 */     return (double[])this.imagEigenvalues.clone();
/*     */   }
/*     */   
/*     */   public double getImagEigenvalue(int i) {
/* 288 */     return this.imagEigenvalues[i];
/*     */   }
/*     */   
/*     */   public RealVector getEigenvector(int i) {
/* 299 */     return this.eigenvectors[i].copy();
/*     */   }
/*     */   
/*     */   public double getDeterminant() {
/* 308 */     double determinant = 1.0D;
/* 309 */     for (double lambda : this.realEigenvalues)
/* 310 */       determinant *= lambda; 
/* 312 */     return determinant;
/*     */   }
/*     */   
/*     */   public DecompositionSolver getSolver() {
/* 322 */     return new Solver(this.realEigenvalues, this.imagEigenvalues, this.eigenvectors);
/*     */   }
/*     */   
/*     */   private static class Solver implements DecompositionSolver {
/*     */     private double[] realEigenvalues;
/*     */     
/*     */     private double[] imagEigenvalues;
/*     */     
/*     */     private final ArrayRealVector[] eigenvectors;
/*     */     
/*     */     private Solver(double[] realEigenvalues, double[] imagEigenvalues, ArrayRealVector[] eigenvectors) {
/* 344 */       this.realEigenvalues = realEigenvalues;
/* 345 */       this.imagEigenvalues = imagEigenvalues;
/* 346 */       this.eigenvectors = eigenvectors;
/*     */     }
/*     */     
/*     */     public RealVector solve(RealVector b) {
/* 363 */       if (!isNonSingular())
/* 364 */         throw new SingularMatrixException(); 
/* 367 */       int m = this.realEigenvalues.length;
/* 368 */       if (b.getDimension() != m)
/* 369 */         throw new DimensionMismatchException(b.getDimension(), m); 
/* 372 */       double[] bp = new double[m];
/* 373 */       for (int i = 0; i < m; i++) {
/* 374 */         ArrayRealVector v = this.eigenvectors[i];
/* 375 */         double[] vData = v.getDataRef();
/* 376 */         double s = v.dotProduct(b) / this.realEigenvalues[i];
/* 377 */         for (int j = 0; j < m; j++)
/* 378 */           bp[j] = bp[j] + s * vData[j]; 
/*     */       } 
/* 382 */       return new ArrayRealVector(bp, false);
/*     */     }
/*     */     
/*     */     public RealMatrix solve(RealMatrix b) {
/* 388 */       if (!isNonSingular())
/* 389 */         throw new SingularMatrixException(); 
/* 392 */       int m = this.realEigenvalues.length;
/* 393 */       if (b.getRowDimension() != m)
/* 394 */         throw new DimensionMismatchException(b.getRowDimension(), m); 
/* 397 */       int nColB = b.getColumnDimension();
/* 398 */       double[][] bp = new double[m][nColB];
/* 399 */       double[] tmpCol = new double[m];
/* 400 */       for (int k = 0; k < nColB; k++) {
/*     */         int i;
/* 401 */         for (i = 0; i < m; i++) {
/* 402 */           tmpCol[i] = b.getEntry(i, k);
/* 403 */           bp[i][k] = 0.0D;
/*     */         } 
/* 405 */         for (i = 0; i < m; i++) {
/* 406 */           ArrayRealVector v = this.eigenvectors[i];
/* 407 */           double[] vData = v.getDataRef();
/* 408 */           double s = 0.0D;
/*     */           int j;
/* 409 */           for (j = 0; j < m; j++)
/* 410 */             s += v.getEntry(j) * tmpCol[j]; 
/* 412 */           s /= this.realEigenvalues[i];
/* 413 */           for (j = 0; j < m; j++)
/* 414 */             bp[j][k] = bp[j][k] + s * vData[j]; 
/*     */         } 
/*     */       } 
/* 419 */       return new Array2DRowRealMatrix(bp, false);
/*     */     }
/*     */     
/*     */     public boolean isNonSingular() {
/* 429 */       for (int i = 0; i < this.realEigenvalues.length; i++) {
/* 430 */         if (this.realEigenvalues[i] == 0.0D && this.imagEigenvalues[i] == 0.0D)
/* 432 */           return false; 
/*     */       } 
/* 435 */       return true;
/*     */     }
/*     */     
/*     */     public RealMatrix getInverse() {
/* 445 */       if (!isNonSingular())
/* 446 */         throw new SingularMatrixException(); 
/* 449 */       int m = this.realEigenvalues.length;
/* 450 */       double[][] invData = new double[m][m];
/* 452 */       for (int i = 0; i < m; i++) {
/* 453 */         double[] invI = invData[i];
/* 454 */         for (int j = 0; j < m; j++) {
/* 455 */           double invIJ = 0.0D;
/* 456 */           for (int k = 0; k < m; k++) {
/* 457 */             double[] vK = this.eigenvectors[k].getDataRef();
/* 458 */             invIJ += vK[i] * vK[j] / this.realEigenvalues[k];
/*     */           } 
/* 460 */           invI[j] = invIJ;
/*     */         } 
/*     */       } 
/* 463 */       return MatrixUtils.createRealMatrix(invData);
/*     */     }
/*     */   }
/*     */   
/*     */   private void transformToTridiagonal(RealMatrix matrix) {
/* 474 */     this.transformer = new TriDiagonalTransformer(matrix);
/* 475 */     this.main = this.transformer.getMainDiagonalRef();
/* 476 */     this.secondary = this.transformer.getSecondaryDiagonalRef();
/*     */   }
/*     */   
/*     */   private void findEigenVectors(double[][] householderMatrix) {
/* 486 */     double[][] z = (double[][])householderMatrix.clone();
/* 487 */     int n = this.main.length;
/* 488 */     this.realEigenvalues = new double[n];
/* 489 */     this.imagEigenvalues = new double[n];
/* 490 */     double[] e = new double[n];
/* 491 */     for (int i = 0; i < n - 1; i++) {
/* 492 */       this.realEigenvalues[i] = this.main[i];
/* 493 */       e[i] = this.secondary[i];
/*     */     } 
/* 495 */     this.realEigenvalues[n - 1] = this.main[n - 1];
/* 496 */     e[n - 1] = 0.0D;
/* 499 */     double maxAbsoluteValue = 0.0D;
/*     */     int m;
/* 500 */     for (m = 0; m < n; m++) {
/* 501 */       if (FastMath.abs(this.realEigenvalues[m]) > maxAbsoluteValue)
/* 502 */         maxAbsoluteValue = FastMath.abs(this.realEigenvalues[m]); 
/* 504 */       if (FastMath.abs(e[m]) > maxAbsoluteValue)
/* 505 */         maxAbsoluteValue = FastMath.abs(e[m]); 
/*     */     } 
/* 509 */     if (maxAbsoluteValue != 0.0D)
/* 510 */       for (m = 0; m < n; m++) {
/* 511 */         if (FastMath.abs(this.realEigenvalues[m]) <= 1.1102230246251565E-16D * maxAbsoluteValue)
/* 512 */           this.realEigenvalues[m] = 0.0D; 
/* 514 */         if (FastMath.abs(e[m]) <= 1.1102230246251565E-16D * maxAbsoluteValue)
/* 515 */           e[m] = 0.0D; 
/*     */       }  
/* 520 */     for (int j = 0; j < n; ) {
/* 521 */       int its = 0;
/*     */       while (true) {
/*     */         int i2;
/* 524 */         for (i2 = j; i2 < n - 1; i2++) {
/* 525 */           double delta = FastMath.abs(this.realEigenvalues[i2]) + FastMath.abs(this.realEigenvalues[i2 + 1]);
/* 527 */           if (FastMath.abs(e[i2]) + delta == delta)
/*     */             break; 
/*     */         } 
/* 531 */         if (i2 != j) {
/* 532 */           if (its == this.maxIter)
/* 533 */             throw new MaxCountExceededException(LocalizedFormats.CONVERGENCE_FAILED, Byte.valueOf(this.maxIter), new Object[0]); 
/* 536 */           its++;
/* 537 */           double q = (this.realEigenvalues[j + 1] - this.realEigenvalues[j]) / 2.0D * e[j];
/* 538 */           double t = FastMath.sqrt(1.0D + q * q);
/* 539 */           if (q < 0.0D) {
/* 540 */             q = this.realEigenvalues[i2] - this.realEigenvalues[j] + e[j] / (q - t);
/*     */           } else {
/* 542 */             q = this.realEigenvalues[i2] - this.realEigenvalues[j] + e[j] / (q + t);
/*     */           } 
/* 544 */           double u = 0.0D;
/* 545 */           double s = 1.0D;
/* 546 */           double c = 1.0D;
/*     */           int i3;
/* 548 */           for (i3 = i2 - 1; i3 >= j; i3--) {
/* 549 */             double p = s * e[i3];
/* 550 */             double h = c * e[i3];
/* 551 */             if (FastMath.abs(p) >= FastMath.abs(q)) {
/* 552 */               c = q / p;
/* 553 */               t = FastMath.sqrt(c * c + 1.0D);
/* 554 */               e[i3 + 1] = p * t;
/* 555 */               s = 1.0D / t;
/* 556 */               c *= s;
/*     */             } else {
/* 558 */               s = p / q;
/* 559 */               t = FastMath.sqrt(s * s + 1.0D);
/* 560 */               e[i3 + 1] = q * t;
/* 561 */               c = 1.0D / t;
/* 562 */               s *= c;
/*     */             } 
/* 564 */             if (e[i3 + 1] == 0.0D) {
/* 565 */               this.realEigenvalues[i3 + 1] = this.realEigenvalues[i3 + 1] - u;
/* 566 */               e[i2] = 0.0D;
/*     */               break;
/*     */             } 
/* 569 */             q = this.realEigenvalues[i3 + 1] - u;
/* 570 */             t = (this.realEigenvalues[i3] - q) * s + 2.0D * c * h;
/* 571 */             u = s * t;
/* 572 */             this.realEigenvalues[i3 + 1] = q + u;
/* 573 */             q = c * t - h;
/* 574 */             for (int ia = 0; ia < n; ia++) {
/* 575 */               p = z[ia][i3 + 1];
/* 576 */               z[ia][i3 + 1] = s * z[ia][i3] + c * p;
/* 577 */               z[ia][i3] = c * z[ia][i3] - s * p;
/*     */             } 
/*     */           } 
/* 580 */           if (t != 0.0D || i3 < j) {
/* 583 */             this.realEigenvalues[j] = this.realEigenvalues[j] - u;
/* 584 */             e[j] = q;
/* 585 */             e[i2] = 0.0D;
/*     */           } 
/*     */         } 
/* 587 */         if (i2 == j)
/*     */           j++; 
/*     */       } 
/*     */     } 
/*     */     int k;
/* 591 */     for (k = 0; k < n; k++) {
/* 592 */       int i2 = k;
/* 593 */       double p = this.realEigenvalues[k];
/*     */       int i3;
/* 594 */       for (i3 = k + 1; i3 < n; i3++) {
/* 595 */         if (this.realEigenvalues[i3] > p) {
/* 596 */           i2 = i3;
/* 597 */           p = this.realEigenvalues[i3];
/*     */         } 
/*     */       } 
/* 600 */       if (i2 != k) {
/* 601 */         this.realEigenvalues[i2] = this.realEigenvalues[k];
/* 602 */         this.realEigenvalues[k] = p;
/* 603 */         for (i3 = 0; i3 < n; i3++) {
/* 604 */           p = z[i3][k];
/* 605 */           z[i3][k] = z[i3][i2];
/* 606 */           z[i3][i2] = p;
/*     */         } 
/*     */       } 
/*     */     } 
/* 612 */     maxAbsoluteValue = 0.0D;
/* 613 */     for (k = 0; k < n; k++) {
/* 614 */       if (FastMath.abs(this.realEigenvalues[k]) > maxAbsoluteValue)
/* 615 */         maxAbsoluteValue = FastMath.abs(this.realEigenvalues[k]); 
/*     */     } 
/* 619 */     if (maxAbsoluteValue != 0.0D)
/* 620 */       for (k = 0; k < n; k++) {
/* 621 */         if (FastMath.abs(this.realEigenvalues[k]) < 1.1102230246251565E-16D * maxAbsoluteValue)
/* 622 */           this.realEigenvalues[k] = 0.0D; 
/*     */       }  
/* 626 */     this.eigenvectors = new ArrayRealVector[n];
/* 627 */     double[] tmp = new double[n];
/* 628 */     for (int i1 = 0; i1 < n; i1++) {
/* 629 */       for (int i2 = 0; i2 < n; i2++)
/* 630 */         tmp[i2] = z[i2][i1]; 
/* 632 */       this.eigenvectors[i1] = new ArrayRealVector(tmp);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\linear\EigenDecomposition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */