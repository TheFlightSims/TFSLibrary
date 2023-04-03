/*     */ package org.apache.commons.math3.linear;
/*     */ 
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ 
/*     */ class BiDiagonalTransformer {
/*     */   private final double[][] householderVectors;
/*     */   
/*     */   private final double[] main;
/*     */   
/*     */   private final double[] secondary;
/*     */   
/*     */   private RealMatrix cachedU;
/*     */   
/*     */   private RealMatrix cachedB;
/*     */   
/*     */   private RealMatrix cachedV;
/*     */   
/*     */   public BiDiagonalTransformer(RealMatrix matrix) {
/*  64 */     int m = matrix.getRowDimension();
/*  65 */     int n = matrix.getColumnDimension();
/*  66 */     int p = FastMath.min(m, n);
/*  67 */     this.householderVectors = matrix.getData();
/*  68 */     this.main = new double[p];
/*  69 */     this.secondary = new double[p - 1];
/*  70 */     this.cachedU = null;
/*  71 */     this.cachedB = null;
/*  72 */     this.cachedV = null;
/*  75 */     if (m >= n) {
/*  76 */       transformToUpperBiDiagonal();
/*     */     } else {
/*  78 */       transformToLowerBiDiagonal();
/*     */     } 
/*     */   }
/*     */   
/*     */   public RealMatrix getU() {
/*  90 */     if (this.cachedU == null) {
/*  92 */       int m = this.householderVectors.length;
/*  93 */       int n = (this.householderVectors[0]).length;
/*  94 */       int p = this.main.length;
/*  95 */       int diagOffset = (m >= n) ? 0 : 1;
/*  96 */       double[] diagonal = (m >= n) ? this.main : this.secondary;
/*  97 */       double[][] ua = new double[m][m];
/*     */       int k;
/* 100 */       for (k = m - 1; k >= p; k--)
/* 101 */         ua[k][k] = 1.0D; 
/* 105 */       for (k = p - 1; k >= diagOffset; k--) {
/* 106 */         double[] hK = this.householderVectors[k];
/* 107 */         ua[k][k] = 1.0D;
/* 108 */         if (hK[k - diagOffset] != 0.0D)
/* 109 */           for (int j = k; j < m; j++) {
/* 110 */             double alpha = 0.0D;
/*     */             int i;
/* 111 */             for (i = k; i < m; i++)
/* 112 */               alpha -= ua[i][j] * this.householderVectors[i][k - diagOffset]; 
/* 114 */             alpha /= diagonal[k - diagOffset] * hK[k - diagOffset];
/* 116 */             for (i = k; i < m; i++)
/* 117 */               ua[i][j] = ua[i][j] + -alpha * this.householderVectors[i][k - diagOffset]; 
/*     */           }  
/*     */       } 
/* 122 */       if (diagOffset > 0)
/* 123 */         ua[0][0] = 1.0D; 
/* 125 */       this.cachedU = MatrixUtils.createRealMatrix(ua);
/*     */     } 
/* 129 */     return this.cachedU;
/*     */   }
/*     */   
/*     */   public RealMatrix getB() {
/* 139 */     if (this.cachedB == null) {
/* 141 */       int m = this.householderVectors.length;
/* 142 */       int n = (this.householderVectors[0]).length;
/* 143 */       double[][] ba = new double[m][n];
/* 144 */       for (int i = 0; i < this.main.length; i++) {
/* 145 */         ba[i][i] = this.main[i];
/* 146 */         if (m < n) {
/* 147 */           if (i > 0)
/* 148 */             ba[i][i - 1] = this.secondary[i - 1]; 
/* 151 */         } else if (i < this.main.length - 1) {
/* 152 */           ba[i][i + 1] = this.secondary[i];
/*     */         } 
/*     */       } 
/* 156 */       this.cachedB = MatrixUtils.createRealMatrix(ba);
/*     */     } 
/* 160 */     return this.cachedB;
/*     */   }
/*     */   
/*     */   public RealMatrix getV() {
/* 171 */     if (this.cachedV == null) {
/* 173 */       int m = this.householderVectors.length;
/* 174 */       int n = (this.householderVectors[0]).length;
/* 175 */       int p = this.main.length;
/* 176 */       int diagOffset = (m >= n) ? 1 : 0;
/* 177 */       double[] diagonal = (m >= n) ? this.secondary : this.main;
/* 178 */       double[][] va = new double[n][n];
/*     */       int k;
/* 181 */       for (k = n - 1; k >= p; k--)
/* 182 */         va[k][k] = 1.0D; 
/* 186 */       for (k = p - 1; k >= diagOffset; k--) {
/* 187 */         double[] hK = this.householderVectors[k - diagOffset];
/* 188 */         va[k][k] = 1.0D;
/* 189 */         if (hK[k] != 0.0D)
/* 190 */           for (int j = k; j < n; j++) {
/* 191 */             double beta = 0.0D;
/*     */             int i;
/* 192 */             for (i = k; i < n; i++)
/* 193 */               beta -= va[i][j] * hK[i]; 
/* 195 */             beta /= diagonal[k - diagOffset] * hK[k];
/* 197 */             for (i = k; i < n; i++)
/* 198 */               va[i][j] = va[i][j] + -beta * hK[i]; 
/*     */           }  
/*     */       } 
/* 203 */       if (diagOffset > 0)
/* 204 */         va[0][0] = 1.0D; 
/* 206 */       this.cachedV = MatrixUtils.createRealMatrix(va);
/*     */     } 
/* 210 */     return this.cachedV;
/*     */   }
/*     */   
/*     */   double[][] getHouseholderVectorsRef() {
/* 221 */     return this.householderVectors;
/*     */   }
/*     */   
/*     */   double[] getMainDiagonalRef() {
/* 231 */     return this.main;
/*     */   }
/*     */   
/*     */   double[] getSecondaryDiagonalRef() {
/* 241 */     return this.secondary;
/*     */   }
/*     */   
/*     */   boolean isUpperBiDiagonal() {
/* 249 */     return (this.householderVectors.length >= (this.householderVectors[0]).length);
/*     */   }
/*     */   
/*     */   private void transformToUpperBiDiagonal() {
/* 259 */     int m = this.householderVectors.length;
/* 260 */     int n = (this.householderVectors[0]).length;
/* 261 */     for (int k = 0; k < n; k++) {
/* 264 */       double xNormSqr = 0.0D;
/* 265 */       for (int i = k; i < m; i++) {
/* 266 */         double c = this.householderVectors[i][k];
/* 267 */         xNormSqr += c * c;
/*     */       } 
/* 269 */       double[] hK = this.householderVectors[k];
/* 270 */       double a = (hK[k] > 0.0D) ? -FastMath.sqrt(xNormSqr) : FastMath.sqrt(xNormSqr);
/* 271 */       this.main[k] = a;
/* 272 */       if (a != 0.0D) {
/* 273 */         hK[k] = hK[k] - a;
/* 274 */         for (int j = k + 1; j < n; j++) {
/* 275 */           double alpha = 0.0D;
/*     */           int i1;
/* 276 */           for (i1 = k; i1 < m; i1++) {
/* 277 */             double[] hI = this.householderVectors[i1];
/* 278 */             alpha -= hI[j] * hI[k];
/*     */           } 
/* 280 */           alpha /= a * this.householderVectors[k][k];
/* 281 */           for (i1 = k; i1 < m; i1++) {
/* 282 */             double[] hI = this.householderVectors[i1];
/* 283 */             hI[j] = hI[j] - alpha * hI[k];
/*     */           } 
/*     */         } 
/*     */       } 
/* 288 */       if (k < n - 1) {
/* 290 */         xNormSqr = 0.0D;
/* 291 */         for (int j = k + 1; j < n; j++) {
/* 292 */           double c = hK[j];
/* 293 */           xNormSqr += c * c;
/*     */         } 
/* 295 */         double b = (hK[k + 1] > 0.0D) ? -FastMath.sqrt(xNormSqr) : FastMath.sqrt(xNormSqr);
/* 296 */         this.secondary[k] = b;
/* 297 */         if (b != 0.0D) {
/* 298 */           hK[k + 1] = hK[k + 1] - b;
/* 299 */           for (int i1 = k + 1; i1 < m; i1++) {
/* 300 */             double[] hI = this.householderVectors[i1];
/* 301 */             double beta = 0.0D;
/*     */             int i2;
/* 302 */             for (i2 = k + 1; i2 < n; i2++)
/* 303 */               beta -= hI[i2] * hK[i2]; 
/* 305 */             beta /= b * hK[k + 1];
/* 306 */             for (i2 = k + 1; i2 < n; i2++)
/* 307 */               hI[i2] = hI[i2] - beta * hK[i2]; 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void transformToLowerBiDiagonal() {
/* 323 */     int m = this.householderVectors.length;
/* 324 */     int n = (this.householderVectors[0]).length;
/* 325 */     for (int k = 0; k < m; k++) {
/* 328 */       double[] hK = this.householderVectors[k];
/* 329 */       double xNormSqr = 0.0D;
/* 330 */       for (int j = k; j < n; j++) {
/* 331 */         double c = hK[j];
/* 332 */         xNormSqr += c * c;
/*     */       } 
/* 334 */       double a = (hK[k] > 0.0D) ? -FastMath.sqrt(xNormSqr) : FastMath.sqrt(xNormSqr);
/* 335 */       this.main[k] = a;
/* 336 */       if (a != 0.0D) {
/* 337 */         hK[k] = hK[k] - a;
/* 338 */         for (int i = k + 1; i < m; i++) {
/* 339 */           double[] hI = this.householderVectors[i];
/* 340 */           double alpha = 0.0D;
/*     */           int i1;
/* 341 */           for (i1 = k; i1 < n; i1++)
/* 342 */             alpha -= hI[i1] * hK[i1]; 
/* 344 */           alpha /= a * this.householderVectors[k][k];
/* 345 */           for (i1 = k; i1 < n; i1++)
/* 346 */             hI[i1] = hI[i1] - alpha * hK[i1]; 
/*     */         } 
/*     */       } 
/* 351 */       if (k < m - 1) {
/* 353 */         double[] hKp1 = this.householderVectors[k + 1];
/* 354 */         xNormSqr = 0.0D;
/* 355 */         for (int i = k + 1; i < m; i++) {
/* 356 */           double c = this.householderVectors[i][k];
/* 357 */           xNormSqr += c * c;
/*     */         } 
/* 359 */         double b = (hKp1[k] > 0.0D) ? -FastMath.sqrt(xNormSqr) : FastMath.sqrt(xNormSqr);
/* 360 */         this.secondary[k] = b;
/* 361 */         if (b != 0.0D) {
/* 362 */           hKp1[k] = hKp1[k] - b;
/* 363 */           for (int i1 = k + 1; i1 < n; i1++) {
/* 364 */             double beta = 0.0D;
/*     */             int i2;
/* 365 */             for (i2 = k + 1; i2 < m; i2++) {
/* 366 */               double[] hI = this.householderVectors[i2];
/* 367 */               beta -= hI[i1] * hI[k];
/*     */             } 
/* 369 */             beta /= b * hKp1[k];
/* 370 */             for (i2 = k + 1; i2 < m; i2++) {
/* 371 */               double[] hI = this.householderVectors[i2];
/* 372 */               hI[i1] = hI[i1] - beta * hI[k];
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\linear\BiDiagonalTransformer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */