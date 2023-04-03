/*     */ package org.apache.commons.math3.linear;
/*     */ 
/*     */ import org.apache.commons.math3.exception.NumberIsTooLargeException;
/*     */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ 
/*     */ public class SingularValueDecomposition {
/*     */   private static final double EPS = 2.220446049250313E-16D;
/*     */   
/*     */   private static final double TINY = 1.6033346880071782E-291D;
/*     */   
/*     */   private final double[] singularValues;
/*     */   
/*     */   private final int m;
/*     */   
/*     */   private final int n;
/*     */   
/*     */   private final boolean transposed;
/*     */   
/*     */   private final RealMatrix cachedU;
/*     */   
/*     */   private RealMatrix cachedUt;
/*     */   
/*     */   private RealMatrix cachedS;
/*     */   
/*     */   private final RealMatrix cachedV;
/*     */   
/*     */   private RealMatrix cachedVt;
/*     */   
/*     */   private final double tol;
/*     */   
/*     */   public SingularValueDecomposition(RealMatrix matrix) {
/*     */     double[][] A;
/*  92 */     if (matrix.getRowDimension() < matrix.getColumnDimension()) {
/*  93 */       this.transposed = true;
/*  94 */       A = matrix.transpose().getData();
/*  95 */       this.m = matrix.getColumnDimension();
/*  96 */       this.n = matrix.getRowDimension();
/*     */     } else {
/*  98 */       this.transposed = false;
/*  99 */       A = matrix.getData();
/* 100 */       this.m = matrix.getRowDimension();
/* 101 */       this.n = matrix.getColumnDimension();
/*     */     } 
/* 104 */     this.singularValues = new double[this.n];
/* 105 */     double[][] U = new double[this.m][this.n];
/* 106 */     double[][] V = new double[this.n][this.n];
/* 107 */     double[] e = new double[this.n];
/* 108 */     double[] work = new double[this.m];
/* 111 */     int nct = FastMath.min(this.m - 1, this.n);
/* 112 */     int nrt = FastMath.max(0, this.n - 2);
/* 113 */     for (int k = 0; k < FastMath.max(nct, nrt); k++) {
/* 114 */       if (k < nct) {
/* 118 */         this.singularValues[k] = 0.0D;
/*     */         int n;
/* 119 */         for (n = k; n < this.m; n++)
/* 120 */           this.singularValues[k] = FastMath.hypot(this.singularValues[k], A[n][k]); 
/* 122 */         if (this.singularValues[k] != 0.0D) {
/* 123 */           if (A[k][k] < 0.0D)
/* 124 */             this.singularValues[k] = -this.singularValues[k]; 
/* 126 */           for (n = k; n < this.m; n++)
/* 127 */             A[n][k] = A[n][k] / this.singularValues[k]; 
/* 129 */           A[k][k] = A[k][k] + 1.0D;
/*     */         } 
/* 131 */         this.singularValues[k] = -this.singularValues[k];
/*     */       } 
/* 133 */       for (int m = k + 1; m < this.n; m++) {
/* 134 */         if (k < nct && this.singularValues[k] != 0.0D) {
/* 137 */           double t = 0.0D;
/*     */           int n;
/* 138 */           for (n = k; n < this.m; n++)
/* 139 */             t += A[n][k] * A[n][m]; 
/* 141 */           t = -t / A[k][k];
/* 142 */           for (n = k; n < this.m; n++)
/* 143 */             A[n][m] = A[n][m] + t * A[n][k]; 
/*     */         } 
/* 148 */         e[m] = A[k][m];
/*     */       } 
/* 150 */       if (k < nct)
/* 153 */         for (int n = k; n < this.m; n++)
/* 154 */           U[n][k] = A[n][k];  
/* 157 */       if (k < nrt) {
/* 161 */         e[k] = 0.0D;
/*     */         int n;
/* 162 */         for (n = k + 1; n < this.n; n++)
/* 163 */           e[k] = FastMath.hypot(e[k], e[n]); 
/* 165 */         if (e[k] != 0.0D) {
/* 166 */           if (e[k + 1] < 0.0D)
/* 167 */             e[k] = -e[k]; 
/* 169 */           for (n = k + 1; n < this.n; n++)
/* 170 */             e[n] = e[n] / e[k]; 
/* 172 */           e[k + 1] = e[k + 1] + 1.0D;
/*     */         } 
/* 174 */         e[k] = -e[k];
/* 175 */         if (k + 1 < this.m && e[k] != 0.0D) {
/* 178 */           for (n = k + 1; n < this.m; n++)
/* 179 */             work[n] = 0.0D; 
/*     */           int i1;
/* 181 */           for (i1 = k + 1; i1 < this.n; i1++) {
/* 182 */             for (int i2 = k + 1; i2 < this.m; i2++)
/* 183 */               work[i2] = work[i2] + e[i1] * A[i2][i1]; 
/*     */           } 
/* 186 */           for (i1 = k + 1; i1 < this.n; i1++) {
/* 187 */             double t = -e[i1] / e[k + 1];
/* 188 */             for (int i2 = k + 1; i2 < this.m; i2++)
/* 189 */               A[i2][i1] = A[i2][i1] + t * work[i2]; 
/*     */           } 
/*     */         } 
/* 196 */         for (n = k + 1; n < this.n; n++)
/* 197 */           V[n][k] = e[n]; 
/*     */       } 
/*     */     } 
/* 202 */     int p = this.n;
/* 203 */     if (nct < this.n)
/* 204 */       this.singularValues[nct] = A[nct][nct]; 
/* 206 */     if (this.m < p)
/* 207 */       this.singularValues[p - 1] = 0.0D; 
/* 209 */     if (nrt + 1 < p)
/* 210 */       e[nrt] = A[nrt][p - 1]; 
/* 212 */     e[p - 1] = 0.0D;
/* 215 */     for (int j = nct; j < this.n; j++) {
/* 216 */       for (int m = 0; m < this.m; m++)
/* 217 */         U[m][j] = 0.0D; 
/* 219 */       U[j][j] = 1.0D;
/*     */     } 
/*     */     int i;
/* 221 */     for (i = nct - 1; i >= 0; i--) {
/* 222 */       if (this.singularValues[i] != 0.0D) {
/* 223 */         for (int n = i + 1; n < this.n; n++) {
/* 224 */           double t = 0.0D;
/*     */           int i1;
/* 225 */           for (i1 = i; i1 < this.m; i1++)
/* 226 */             t += U[i1][i] * U[i1][n]; 
/* 228 */           t = -t / U[i][i];
/* 229 */           for (i1 = i; i1 < this.m; i1++)
/* 230 */             U[i1][n] = U[i1][n] + t * U[i1][i]; 
/*     */         } 
/*     */         int m;
/* 233 */         for (m = i; m < this.m; m++)
/* 234 */           U[m][i] = -U[m][i]; 
/* 236 */         U[i][i] = 1.0D + U[i][i];
/* 237 */         for (m = 0; m < i - 1; m++)
/* 238 */           U[m][i] = 0.0D; 
/*     */       } else {
/* 241 */         for (int m = 0; m < this.m; m++)
/* 242 */           U[m][i] = 0.0D; 
/* 244 */         U[i][i] = 1.0D;
/*     */       } 
/*     */     } 
/* 249 */     for (i = this.n - 1; i >= 0; i--) {
/* 250 */       if (i < nrt && e[i] != 0.0D)
/* 252 */         for (int n = i + 1; n < this.n; n++) {
/* 253 */           double t = 0.0D;
/*     */           int i1;
/* 254 */           for (i1 = i + 1; i1 < this.n; i1++)
/* 255 */             t += V[i1][i] * V[i1][n]; 
/* 257 */           t = -t / V[i + 1][i];
/* 258 */           for (i1 = i + 1; i1 < this.n; i1++)
/* 259 */             V[i1][n] = V[i1][n] + t * V[i1][i]; 
/*     */         }  
/* 263 */       for (int m = 0; m < this.n; m++)
/* 264 */         V[m][i] = 0.0D; 
/* 266 */       V[i][i] = 1.0D;
/*     */     } 
/* 270 */     int pp = p - 1;
/* 271 */     int iter = 0;
/* 272 */     while (p > 0) {
/*     */       int kase;
/*     */       double f, maxPm1Pm2;
/*     */       int n;
/*     */       double scale, sp, spm1, epm1, sk, ek, b, c, shift, d1, g;
/*     */       int i1, m;
/* 284 */       for (m = p - 2; m >= 0; m--) {
/* 285 */         double threshold = 1.6033346880071782E-291D + 2.220446049250313E-16D * (FastMath.abs(this.singularValues[m]) + FastMath.abs(this.singularValues[m + 1]));
/* 288 */         if (FastMath.abs(e[m]) <= threshold) {
/* 289 */           e[m] = 0.0D;
/*     */           break;
/*     */         } 
/*     */       } 
/* 294 */       if (m == p - 2) {
/* 295 */         kase = 4;
/*     */       } else {
/*     */         int ks;
/* 298 */         for (ks = p - 1; ks >= m && 
/* 299 */           ks != m; ks--) {
/* 302 */           double t = ((ks != p) ? FastMath.abs(e[ks]) : 0.0D) + ((ks != m + 1) ? FastMath.abs(e[ks - 1]) : 0.0D);
/* 304 */           if (FastMath.abs(this.singularValues[ks]) <= 1.6033346880071782E-291D + 2.220446049250313E-16D * t) {
/* 305 */             this.singularValues[ks] = 0.0D;
/*     */             break;
/*     */           } 
/*     */         } 
/* 309 */         if (ks == m) {
/* 310 */           kase = 3;
/* 311 */         } else if (ks == p - 1) {
/* 312 */           kase = 1;
/*     */         } else {
/* 314 */           kase = 2;
/* 315 */           m = ks;
/*     */         } 
/*     */       } 
/* 318 */       m++;
/* 320 */       switch (kase) {
/*     */         case 1:
/* 323 */           f = e[p - 2];
/* 324 */           e[p - 2] = 0.0D;
/* 325 */           for (n = p - 2; n >= m; n--) {
/* 326 */             double t = FastMath.hypot(this.singularValues[n], f);
/* 327 */             double cs = this.singularValues[n] / t;
/* 328 */             double sn = f / t;
/* 329 */             this.singularValues[n] = t;
/* 330 */             if (n != m) {
/* 331 */               f = -sn * e[n - 1];
/* 332 */               e[n - 1] = cs * e[n - 1];
/*     */             } 
/* 335 */             for (int i2 = 0; i2 < this.n; i2++) {
/* 336 */               t = cs * V[i2][n] + sn * V[i2][p - 1];
/* 337 */               V[i2][p - 1] = -sn * V[i2][n] + cs * V[i2][p - 1];
/* 338 */               V[i2][n] = t;
/*     */             } 
/*     */           } 
/*     */           continue;
/*     */         case 2:
/* 345 */           f = e[m - 1];
/* 346 */           e[m - 1] = 0.0D;
/* 347 */           for (n = m; n < p; n++) {
/* 348 */             double t = FastMath.hypot(this.singularValues[n], f);
/* 349 */             double cs = this.singularValues[n] / t;
/* 350 */             double sn = f / t;
/* 351 */             this.singularValues[n] = t;
/* 352 */             f = -sn * e[n];
/* 353 */             e[n] = cs * e[n];
/* 355 */             for (int i2 = 0; i2 < this.m; i2++) {
/* 356 */               t = cs * U[i2][n] + sn * U[i2][m - 1];
/* 357 */               U[i2][m - 1] = -sn * U[i2][n] + cs * U[i2][m - 1];
/* 358 */               U[i2][n] = t;
/*     */             } 
/*     */           } 
/*     */           continue;
/*     */         case 3:
/* 366 */           maxPm1Pm2 = FastMath.max(FastMath.abs(this.singularValues[p - 1]), FastMath.abs(this.singularValues[p - 2]));
/* 368 */           scale = FastMath.max(FastMath.max(FastMath.max(maxPm1Pm2, FastMath.abs(e[p - 2])), FastMath.abs(this.singularValues[m])), FastMath.abs(e[m]));
/* 372 */           sp = this.singularValues[p - 1] / scale;
/* 373 */           spm1 = this.singularValues[p - 2] / scale;
/* 374 */           epm1 = e[p - 2] / scale;
/* 375 */           sk = this.singularValues[m] / scale;
/* 376 */           ek = e[m] / scale;
/* 377 */           b = ((spm1 + sp) * (spm1 - sp) + epm1 * epm1) / 2.0D;
/* 378 */           c = sp * epm1 * sp * epm1;
/* 379 */           shift = 0.0D;
/* 380 */           if (b != 0.0D || c != 0.0D) {
/* 382 */             shift = FastMath.sqrt(b * b + c);
/* 383 */             if (b < 0.0D)
/* 384 */               shift = -shift; 
/* 386 */             shift = c / (b + shift);
/*     */           } 
/* 388 */           d1 = (sk + sp) * (sk - sp) + shift;
/* 389 */           g = sk * ek;
/* 391 */           for (i1 = m; i1 < p - 1; i1++) {
/* 392 */             double t = FastMath.hypot(d1, g);
/* 393 */             double cs = d1 / t;
/* 394 */             double sn = g / t;
/* 395 */             if (i1 != m)
/* 396 */               e[i1 - 1] = t; 
/* 398 */             d1 = cs * this.singularValues[i1] + sn * e[i1];
/* 399 */             e[i1] = cs * e[i1] - sn * this.singularValues[i1];
/* 400 */             g = sn * this.singularValues[i1 + 1];
/* 401 */             this.singularValues[i1 + 1] = cs * this.singularValues[i1 + 1];
/*     */             int i2;
/* 403 */             for (i2 = 0; i2 < this.n; i2++) {
/* 404 */               t = cs * V[i2][i1] + sn * V[i2][i1 + 1];
/* 405 */               V[i2][i1 + 1] = -sn * V[i2][i1] + cs * V[i2][i1 + 1];
/* 406 */               V[i2][i1] = t;
/*     */             } 
/* 408 */             t = FastMath.hypot(d1, g);
/* 409 */             cs = d1 / t;
/* 410 */             sn = g / t;
/* 411 */             this.singularValues[i1] = t;
/* 412 */             d1 = cs * e[i1] + sn * this.singularValues[i1 + 1];
/* 413 */             this.singularValues[i1 + 1] = -sn * e[i1] + cs * this.singularValues[i1 + 1];
/* 414 */             g = sn * e[i1 + 1];
/* 415 */             e[i1 + 1] = cs * e[i1 + 1];
/* 416 */             if (i1 < this.m - 1)
/* 417 */               for (i2 = 0; i2 < this.m; i2++) {
/* 418 */                 t = cs * U[i2][i1] + sn * U[i2][i1 + 1];
/* 419 */                 U[i2][i1 + 1] = -sn * U[i2][i1] + cs * U[i2][i1 + 1];
/* 420 */                 U[i2][i1] = t;
/*     */               }  
/*     */           } 
/* 424 */           e[p - 2] = d1;
/* 425 */           iter++;
/*     */           continue;
/*     */       } 
/* 431 */       if (this.singularValues[m] <= 0.0D) {
/* 432 */         this.singularValues[m] = (this.singularValues[m] < 0.0D) ? -this.singularValues[m] : 0.0D;
/* 434 */         for (int i2 = 0; i2 <= pp; i2++)
/* 435 */           V[i2][m] = -V[i2][m]; 
/*     */       } 
/* 439 */       while (m < pp && 
/* 440 */         this.singularValues[m] < this.singularValues[m + 1]) {
/* 443 */         double t = this.singularValues[m];
/* 444 */         this.singularValues[m] = this.singularValues[m + 1];
/* 445 */         this.singularValues[m + 1] = t;
/* 446 */         if (m < this.n - 1)
/* 447 */           for (int i2 = 0; i2 < this.n; i2++) {
/* 448 */             t = V[i2][m + 1];
/* 449 */             V[i2][m + 1] = V[i2][m];
/* 450 */             V[i2][m] = t;
/*     */           }  
/* 453 */         if (m < this.m - 1)
/* 454 */           for (int i2 = 0; i2 < this.m; i2++) {
/* 455 */             t = U[i2][m + 1];
/* 456 */             U[i2][m + 1] = U[i2][m];
/* 457 */             U[i2][m] = t;
/*     */           }  
/* 460 */         m++;
/*     */       } 
/* 462 */       iter = 0;
/* 463 */       p--;
/*     */     } 
/* 470 */     this.tol = FastMath.max(this.m * this.singularValues[0] * 2.220446049250313E-16D, FastMath.sqrt(2.2250738585072014E-308D));
/* 473 */     if (!this.transposed) {
/* 474 */       this.cachedU = MatrixUtils.createRealMatrix(U);
/* 475 */       this.cachedV = MatrixUtils.createRealMatrix(V);
/*     */     } else {
/* 477 */       this.cachedU = MatrixUtils.createRealMatrix(V);
/* 478 */       this.cachedV = MatrixUtils.createRealMatrix(U);
/*     */     } 
/*     */   }
/*     */   
/*     */   public RealMatrix getU() {
/* 490 */     return this.cachedU;
/*     */   }
/*     */   
/*     */   public RealMatrix getUT() {
/* 501 */     if (this.cachedUt == null)
/* 502 */       this.cachedUt = getU().transpose(); 
/* 505 */     return this.cachedUt;
/*     */   }
/*     */   
/*     */   public RealMatrix getS() {
/* 515 */     if (this.cachedS == null)
/* 517 */       this.cachedS = MatrixUtils.createRealDiagonalMatrix(this.singularValues); 
/* 519 */     return this.cachedS;
/*     */   }
/*     */   
/*     */   public double[] getSingularValues() {
/* 529 */     return (double[])this.singularValues.clone();
/*     */   }
/*     */   
/*     */   public RealMatrix getV() {
/* 540 */     return this.cachedV;
/*     */   }
/*     */   
/*     */   public RealMatrix getVT() {
/* 550 */     if (this.cachedVt == null)
/* 551 */       this.cachedVt = getV().transpose(); 
/* 554 */     return this.cachedVt;
/*     */   }
/*     */   
/*     */   public RealMatrix getCovariance(double minSingularValue) {
/* 570 */     int p = this.singularValues.length;
/* 571 */     int dimension = 0;
/* 572 */     while (dimension < p && this.singularValues[dimension] >= minSingularValue)
/* 574 */       dimension++; 
/* 577 */     if (dimension == 0)
/* 578 */       throw new NumberIsTooLargeException(LocalizedFormats.TOO_LARGE_CUTOFF_SINGULAR_VALUE, Double.valueOf(minSingularValue), Double.valueOf(this.singularValues[0]), true); 
/* 582 */     final double[][] data = new double[dimension][p];
/* 583 */     getVT().walkInOptimizedOrder(new DefaultRealMatrixPreservingVisitor() {
/*     */           public void visit(int row, int column, double value) {
/* 588 */             data[row][column] = value / SingularValueDecomposition.this.singularValues[row];
/*     */           }
/*     */         },  0, dimension - 1, 0, p - 1);
/* 592 */     RealMatrix jv = new Array2DRowRealMatrix(data, false);
/* 593 */     return jv.transpose().multiply(jv);
/*     */   }
/*     */   
/*     */   public double getNorm() {
/* 604 */     return this.singularValues[0];
/*     */   }
/*     */   
/*     */   public double getConditionNumber() {
/* 612 */     return this.singularValues[0] / this.singularValues[this.n - 1];
/*     */   }
/*     */   
/*     */   public double getInverseConditionNumber() {
/* 623 */     return this.singularValues[this.n - 1] / this.singularValues[0];
/*     */   }
/*     */   
/*     */   public int getRank() {
/* 635 */     int r = 0;
/* 636 */     for (int i = 0; i < this.singularValues.length; i++) {
/* 637 */       if (this.singularValues[i] > this.tol)
/* 638 */         r++; 
/*     */     } 
/* 641 */     return r;
/*     */   }
/*     */   
/*     */   public DecompositionSolver getSolver() {
/* 649 */     return new Solver(this.singularValues, getUT(), getV(), (getRank() == this.m), this.tol);
/*     */   }
/*     */   
/*     */   private static class Solver implements DecompositionSolver {
/*     */     private final RealMatrix pseudoInverse;
/*     */     
/*     */     private boolean nonSingular;
/*     */     
/*     */     private Solver(double[] singularValues, RealMatrix uT, RealMatrix v, boolean nonSingular, double tol) {
/* 670 */       double[][] suT = uT.getData();
/* 671 */       for (int i = 0; i < singularValues.length; i++) {
/*     */         double a;
/* 673 */         if (singularValues[i] > tol) {
/* 674 */           a = 1.0D / singularValues[i];
/*     */         } else {
/* 676 */           a = 0.0D;
/*     */         } 
/* 678 */         double[] suTi = suT[i];
/* 679 */         for (int j = 0; j < suTi.length; j++)
/* 680 */           suTi[j] = suTi[j] * a; 
/*     */       } 
/* 683 */       this.pseudoInverse = v.multiply(new Array2DRowRealMatrix(suT, false));
/* 684 */       this.nonSingular = nonSingular;
/*     */     }
/*     */     
/*     */     public RealVector solve(RealVector b) {
/* 699 */       return this.pseudoInverse.operate(b);
/*     */     }
/*     */     
/*     */     public RealMatrix solve(RealMatrix b) {
/* 715 */       return this.pseudoInverse.multiply(b);
/*     */     }
/*     */     
/*     */     public boolean isNonSingular() {
/* 724 */       return this.nonSingular;
/*     */     }
/*     */     
/*     */     public RealMatrix getInverse() {
/* 733 */       return this.pseudoInverse;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\linear\SingularValueDecomposition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */