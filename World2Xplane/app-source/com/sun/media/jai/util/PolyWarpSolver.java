/*     */ package com.sun.media.jai.util;
/*     */ 
/*     */ import java.util.Random;
/*     */ 
/*     */ public class PolyWarpSolver {
/*     */   private static double sign(double a, double b) {
/*  27 */     a = Math.abs(a);
/*  28 */     if (b >= 0.0D)
/*  29 */       return a; 
/*  31 */     return -a;
/*     */   }
/*     */   
/*     */   private static final double square(double x) {
/*  36 */     return x * x;
/*     */   }
/*     */   
/*     */   private static final double sqrt(double x) {
/*  40 */     return Math.sqrt(x);
/*     */   }
/*     */   
/*     */   private static final double hypot(double x, double y) {
/*  44 */     double xabs = Math.abs(x);
/*  45 */     double yabs = Math.abs(y);
/*  47 */     if (xabs > yabs)
/*  48 */       return xabs * sqrt(square(yabs / xabs) + 1.0D); 
/*  49 */     if (yabs != 0.0D)
/*  50 */       return yabs * sqrt(square(xabs / yabs) + 1.0D); 
/*  52 */     return xabs;
/*     */   }
/*     */   
/*     */   public static double[][] matmul_t(double[][] A, double[][] B) {
/*  58 */     int rowsA = A.length;
/*  59 */     int colsA = (A[0]).length;
/*  61 */     int rowsB = (B[0]).length;
/*  62 */     int colsB = B.length;
/*  66 */     double[][] out = new double[rowsA][colsB];
/*  68 */     for (int i = 0; i < rowsA; i++) {
/*  69 */       double[] outi = out[i];
/*  70 */       double[] Ai = A[i];
/*  72 */       for (int j = 0; j < colsB; j++) {
/*  73 */         double tmp = 0.0D;
/*  74 */         for (int k = 0; k < colsA; k++)
/*  75 */           tmp += Ai[k] * B[j][k]; 
/*  77 */         outi[j] = tmp;
/*     */       } 
/*     */     } 
/*  81 */     return out;
/*     */   }
/*     */   
/*     */   private static boolean SVD(double[][] a, double[] w, double[][] u, double[][] v) {
/* 122 */     int l = 0;
/* 123 */     int l1 = 0;
/* 124 */     int m = a.length;
/* 125 */     int n = (a[0]).length;
/* 127 */     double[] rv1 = new double[n];
/*     */     int i;
/* 129 */     for (i = 0; i < m; i++) {
/* 130 */       for (int j = 0; j < n; j++)
/* 131 */         u[i][j] = a[i][j]; 
/*     */     } 
/* 135 */     double g = 0.0D;
/* 136 */     double scale = 0.0D;
/* 137 */     double x = 0.0D;
/* 139 */     for (i = 0; i < n; i++) {
/* 140 */       l = i + 1;
/* 141 */       rv1[i] = scale * g;
/* 142 */       g = 0.0D;
/* 143 */       double s = 0.0D;
/* 144 */       scale = 0.0D;
/* 146 */       if (i < m) {
/*     */         int j;
/* 147 */         for (j = i; j < m; j++)
/* 148 */           scale += Math.abs(u[j][i]); 
/* 151 */         if (scale != 0.0D) {
/* 152 */           for (j = i; j < m; j++) {
/* 153 */             u[j][i] = u[j][i] / scale;
/* 154 */             s += square(u[j][i]);
/*     */           } 
/* 157 */           double f = u[i][i];
/* 158 */           g = -sign(sqrt(s), f);
/* 159 */           double h = f * g - s;
/* 160 */           u[i][i] = f - g;
/* 162 */           for (int i1 = l; i1 < n; i1++) {
/* 163 */             s = 0.0D;
/* 165 */             for (j = i; j < m; j++)
/* 166 */               s += u[j][i] * u[j][i1]; 
/* 168 */             f = s / h;
/* 169 */             for (j = i; j < m; j++)
/* 170 */               u[j][i1] = u[j][i1] + f * u[j][i]; 
/*     */           } 
/* 174 */           for (j = i; j < m; j++)
/* 175 */             u[j][i] = u[j][i] * scale; 
/*     */         } 
/*     */       } 
/* 180 */       w[i] = scale * g;
/* 181 */       g = 0.0D;
/* 182 */       s = 0.0D;
/* 183 */       scale = 0.0D;
/* 185 */       if (i < m && i != n - 1) {
/*     */         int j;
/* 186 */         for (j = l; j < n; j++)
/* 187 */           scale += Math.abs(u[i][j]); 
/* 190 */         if (scale != 0.0D) {
/* 191 */           for (j = l; j < n; j++) {
/* 192 */             u[i][j] = u[i][j] / scale;
/* 193 */             s += square(u[i][j]);
/*     */           } 
/* 196 */           double f = u[i][l];
/* 197 */           g = -sign(sqrt(s), f);
/* 198 */           double h = f * g - s;
/* 199 */           u[i][l] = f - g;
/* 201 */           for (j = l; j < n; j++)
/* 202 */             rv1[j] = u[i][j] / h; 
/* 205 */           for (int i1 = l; i1 < m; i1++) {
/* 206 */             s = 0.0D;
/* 208 */             for (j = l; j < n; j++)
/* 209 */               s += u[i1][j] * u[i][j]; 
/* 212 */             for (j = l; j < n; j++)
/* 213 */               u[i1][j] = u[i1][j] + s * rv1[j]; 
/*     */           } 
/* 217 */           for (j = l; j < n; j++)
/* 218 */             u[i][j] = u[i][j] * scale; 
/*     */         } 
/*     */       } 
/* 224 */       x = Math.max(x, Math.abs(w[i]) + Math.abs(rv1[i]));
/*     */     } 
/* 227 */     for (i = n - 1; i >= 0; i--) {
/* 228 */       if (i != n - 1) {
/* 229 */         if (g != 0.0D) {
/*     */           int i1;
/* 230 */           for (i1 = l; i1 < n; i1++)
/* 231 */             v[i1][i] = u[i][i1] / u[i][l] / g; 
/* 234 */           for (i1 = l; i1 < n; i1++) {
/* 235 */             double s = 0.0D;
/*     */             int i2;
/* 236 */             for (i2 = l; i2 < n; i2++)
/* 237 */               s += u[i][i2] * v[i2][i1]; 
/* 239 */             for (i2 = l; i2 < n; i2++)
/* 240 */               v[i2][i1] = v[i2][i1] + s * v[i2][i]; 
/*     */           } 
/*     */         } 
/* 245 */         for (int j = l; j < n; j++) {
/* 246 */           v[j][i] = 0.0D;
/* 246 */           v[i][j] = 0.0D;
/*     */         } 
/*     */       } 
/* 250 */       v[i][i] = 1.0D;
/* 251 */       g = rv1[i];
/* 252 */       l = i;
/*     */     } 
/* 255 */     int mn = Math.min(m, n);
/* 257 */     for (i = mn - 1; i >= 0; i--) {
/* 258 */       l = i + 1;
/* 259 */       g = w[i];
/* 261 */       if (i != n - 1)
/* 262 */         for (int j = l; j < n; j++)
/* 263 */           u[i][j] = 0.0D;  
/* 267 */       if (g != 0.0D) {
/* 268 */         if (i != mn - 1)
/* 269 */           for (int i1 = l; i1 < n; i1++) {
/* 270 */             double s = 0.0D;
/*     */             int i2;
/* 272 */             for (i2 = l; i2 < m; i2++)
/* 273 */               s += u[i2][i] * u[i2][i1]; 
/* 275 */             double f = s / u[i][i] / g;
/* 276 */             for (i2 = i; i2 < m; i2++)
/* 277 */               u[i2][i1] = u[i2][i1] + f * u[i2][i]; 
/*     */           }  
/* 282 */         for (int j = i; j < m; j++)
/* 283 */           u[j][i] = u[j][i] / g; 
/*     */       } else {
/* 286 */         for (int j = i; j < m; j++)
/* 287 */           u[j][i] = 0.0D; 
/*     */       } 
/* 290 */       u[i][i] = u[i][i] + 1.0D;
/*     */     } 
/* 293 */     double tst1 = x;
/* 295 */     for (int k = n - 1; k >= 0; k--) {
/* 296 */       int k1 = k - 1;
/* 297 */       int its = 0;
/*     */       while (true) {
/* 300 */         boolean flag = true;
/* 302 */         for (l = k; l >= 0; l--) {
/* 303 */           l1 = l - 1;
/* 304 */           double tst2 = tst1 + Math.abs(rv1[l]);
/* 305 */           if (tst2 == tst1) {
/* 306 */             flag = false;
/*     */             break;
/*     */           } 
/* 310 */           tst2 = tst1 + Math.abs(w[l1]);
/* 311 */           if (tst2 == tst1) {
/* 312 */             flag = true;
/*     */             break;
/*     */           } 
/*     */         } 
/* 317 */         if (flag) {
/* 318 */           double d1 = 0.0D;
/* 319 */           double d2 = 1.0D;
/* 321 */           for (i = l; i < k + 1; i++) {
/* 322 */             double d3 = d2 * rv1[i];
/* 323 */             rv1[i] = rv1[i] * d1;
/* 325 */             double tst2 = tst1 + Math.abs(d3);
/* 326 */             if (tst2 != tst1) {
/* 327 */               g = w[i];
/* 329 */               double d = hypot(d3, g);
/* 330 */               w[i] = d;
/* 331 */               d1 = g / d;
/* 332 */               d2 = -d3 / d;
/* 334 */               for (int j = 0; j < m; j++) {
/* 335 */                 double d4 = u[j][l1];
/* 336 */                 double d5 = u[j][i];
/* 337 */                 u[j][l1] = d4 * d1 + d5 * d2;
/* 338 */                 u[j][i] = -d4 * d2 + d5 * d1;
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/* 344 */         double z = w[k];
/* 346 */         if (l == k) {
/* 347 */           if (z < 0.0D) {
/* 348 */             w[k] = -z;
/* 349 */             for (int j = 0; j < n; j++)
/* 350 */               v[j][k] = -v[j][k]; 
/*     */           } 
/*     */           break;
/*     */         } 
/* 356 */         if (its == 30)
/* 357 */           return false; 
/* 360 */         its++;
/* 362 */         x = w[l];
/* 363 */         double y = w[k1];
/* 364 */         g = rv1[k1];
/* 365 */         double h = rv1[k];
/* 366 */         double f = 0.5D * ((g + z) / h * (g - z) / y + y / h - h / y);
/* 368 */         g = hypot(f, 1.0D);
/* 369 */         f = x - z / x * z + h / x * (y / (f + sign(g, f)) - h);
/* 371 */         double c = 1.0D;
/* 372 */         double s = 1.0D;
/* 374 */         for (int i1 = l; i1 <= k1; i1++) {
/* 375 */           i = i1 + 1;
/* 376 */           g = rv1[i];
/* 377 */           y = w[i];
/* 378 */           h = s * g;
/* 379 */           g = c * g;
/* 381 */           z = hypot(f, h);
/* 382 */           rv1[i1] = z;
/* 383 */           c = f / z;
/* 384 */           s = h / z;
/* 385 */           f = x * c + g * s;
/* 386 */           g = -x * s + g * c;
/* 387 */           h = y * s;
/* 388 */           y *= c;
/*     */           int j;
/* 390 */           for (j = 0; j < n; j++) {
/* 391 */             x = v[j][i1];
/* 392 */             z = v[j][i];
/* 393 */             v[j][i1] = x * c + z * s;
/* 394 */             v[j][i] = -x * s + z * c;
/*     */           } 
/* 397 */           z = hypot(f, h);
/* 398 */           w[i1] = z;
/* 400 */           if (z != 0.0D) {
/* 401 */             c = f / z;
/* 402 */             s = h / z;
/*     */           } 
/* 405 */           f = c * g + s * y;
/* 406 */           x = -s * g + c * y;
/* 408 */           for (j = 0; j < m; j++) {
/* 409 */             y = u[j][i1];
/* 410 */             z = u[j][i];
/* 411 */             u[j][i1] = y * c + z * s;
/* 412 */             u[j][i] = -y * s + z * c;
/*     */           } 
/*     */         } 
/* 416 */         rv1[l] = 0.0D;
/* 417 */         rv1[k] = f;
/* 418 */         w[k] = x;
/*     */       } 
/*     */     } 
/* 422 */     return true;
/*     */   }
/*     */   
/*     */   public static float[] getCoeffs(float[] sourceCoords, int sourceOffset, float[] destCoords, int destOffset, int numCoords, float preScaleX, float preScaleY, float postScaleX, float postScaleY, int degree) {
/* 448 */     int equations = numCoords / 2;
/* 460 */     int unknowns = (degree + 1) * (degree + 2) / 2;
/* 461 */     float[] out = new float[2 * unknowns];
/* 464 */     if (degree == 1 && numCoords == 3) {
/* 468 */       double x0 = (sourceCoords[0] * preScaleX);
/* 469 */       double y0 = (sourceCoords[1] * preScaleY);
/* 470 */       double x1 = (sourceCoords[2] * preScaleX);
/* 471 */       double y1 = (sourceCoords[3] * preScaleY);
/* 472 */       double x2 = (sourceCoords[4] * preScaleY);
/* 473 */       double y2 = (sourceCoords[5] * preScaleY);
/* 475 */       double u0 = (destCoords[0] / postScaleX);
/* 476 */       double v0 = (destCoords[1] / postScaleY);
/* 477 */       double u1 = (destCoords[2] / postScaleX);
/* 478 */       double v1 = (destCoords[3] / postScaleY);
/* 479 */       double u2 = (destCoords[4] / postScaleX);
/* 480 */       double v2 = (destCoords[5] / postScaleY);
/* 482 */       double v0mv1 = v0 - v1;
/* 483 */       double v1mv2 = v1 - v2;
/* 484 */       double v2mv0 = v2 - v0;
/* 485 */       double u1mu0 = u1 - u0;
/* 486 */       double u2mu1 = u2 - u1;
/* 487 */       double u0mu2 = u0 - u2;
/* 488 */       double u1v2mu2v1 = u1 * v2 - u2 * v1;
/* 489 */       double u2v0mu0v2 = u2 * v0 - u0 * v2;
/* 490 */       double u0v1mu1v0 = u0 * v1 - u1 * v0;
/* 491 */       double invdet = 1.0D / (u0 * v1mv2 + v0 * u2mu1 + u1v2mu2v1);
/* 493 */       out[0] = (float)((v1mv2 * x0 + v2mv0 * x1 + v0mv1 * x2) * invdet);
/* 494 */       out[1] = (float)((u2mu1 * x0 + u0mu2 * x1 + u1mu0 * x2) * invdet);
/* 495 */       out[2] = (float)((u1v2mu2v1 * x0 + u2v0mu0v2 * x1 + u0v1mu1v0 * x2) * invdet);
/* 497 */       out[3] = (float)((v1mv2 * y0 + v2mv0 * y1 + v0mv1 * y2) * invdet);
/* 498 */       out[4] = (float)((u2mu1 * y0 + u0mu2 * y1 + u1mu0 * y2) * invdet);
/* 499 */       out[5] = (float)((u1v2mu2v1 * y0 + u2v0mu0v2 * y1 + u0v1mu1v0 * y2) * invdet);
/* 502 */       return out;
/*     */     } 
/* 505 */     double[][] A = new double[equations][unknowns];
/* 519 */     double[] xpow = new double[degree + 1];
/* 520 */     double[] ypow = new double[degree + 1];
/*     */     int i;
/* 522 */     for (i = 0; i < equations; i++) {
/* 523 */       double[] Ai = A[i];
/* 524 */       double x = (destCoords[2 * i + destOffset] / postScaleX);
/* 525 */       double y = (destCoords[2 * i + 1 + destOffset] / postScaleY);
/* 527 */       double xtmp = 1.0D;
/* 528 */       double ytmp = 1.0D;
/* 529 */       for (int d = 0; d <= degree; d++) {
/* 530 */         xpow[d] = xtmp;
/* 531 */         ypow[d] = ytmp;
/* 532 */         xtmp *= x;
/* 533 */         ytmp *= y;
/*     */       } 
/* 536 */       int index = 0;
/* 537 */       for (int deg = 0; deg <= degree; deg++) {
/* 538 */         for (int ydeg = 0; ydeg <= deg; ydeg++)
/* 539 */           Ai[index++] = xpow[deg - ydeg] * ypow[ydeg]; 
/*     */       } 
/*     */     } 
/* 544 */     double[][] V = new double[unknowns][unknowns];
/* 545 */     double[] W = new double[unknowns];
/* 546 */     double[][] U = new double[equations][unknowns];
/* 547 */     SVD(A, W, U, V);
/*     */     int j;
/* 550 */     for (j = 0; j < unknowns; j++) {
/* 551 */       double winv = W[j];
/* 552 */       if (winv != 0.0D)
/* 553 */         winv = 1.0D / winv; 
/* 555 */       for (i = 0; i < unknowns; i++)
/* 556 */         V[i][j] = V[i][j] * winv; 
/*     */     } 
/* 561 */     double[][] VWINVUT = matmul_t(V, U);
/* 564 */     for (i = 0; i < unknowns; i++) {
/* 565 */       double tmp0 = 0.0D;
/* 566 */       double tmp1 = 0.0D;
/* 567 */       for (j = 0; j < equations; j++) {
/* 568 */         double val = VWINVUT[i][j];
/* 569 */         tmp0 += val * sourceCoords[2 * j + sourceOffset] * preScaleX;
/* 570 */         tmp1 += val * sourceCoords[2 * j + 1 + sourceOffset] * preScaleY;
/*     */       } 
/* 572 */       out[i] = (float)tmp0;
/* 573 */       out[i + unknowns] = (float)tmp1;
/*     */     } 
/* 576 */     return out;
/*     */   }
/*     */   
/* 581 */   private static Random myRandom = new Random(0L);
/*     */   
/* 582 */   private static double[] c0 = new double[6];
/*     */   
/* 583 */   private static double[] c1 = new double[6];
/*     */   
/* 584 */   private static double noise = 0.0D;
/*     */   
/*     */   private static float xpoly(float x, float y) {
/* 587 */     return (float)(c0[0] + c0[1] * x + c0[2] * y + c0[3] * x * x + c0[4] * x * y + c0[5] * y * y + myRandom.nextDouble() * noise);
/*     */   }
/*     */   
/*     */   private static float ypoly(float x, float y) {
/* 593 */     return (float)(c1[0] + c1[1] * x + c1[2] * y + c1[3] * x * x + c1[4] * x * y + c1[5] * y * y + myRandom.nextDouble() * noise);
/*     */   }
/*     */   
/*     */   private static void doTest(int equations, boolean print) {
/* 600 */     for (int i = 0; i < 6; i++) {
/* 601 */       c0[i] = myRandom.nextDouble() * 100.0D;
/* 602 */       c1[i] = myRandom.nextDouble() * 100.0D;
/*     */     } 
/* 606 */     float[] destCoords = new float[2 * equations];
/* 607 */     for (int j = 0; j < 2 * equations; j++)
/* 608 */       destCoords[j] = myRandom.nextFloat() * 100.0F; 
/* 612 */     float[] sourceCoords = new float[2 * equations];
/* 613 */     for (int k = 0; k < equations; k++) {
/* 614 */       sourceCoords[2 * k] = xpoly(destCoords[2 * k], destCoords[2 * k + 1]);
/* 616 */       sourceCoords[2 * k + 1] = ypoly(destCoords[2 * k], destCoords[2 * k + 1]);
/*     */     } 
/* 621 */     float[] coeffs = getCoeffs(sourceCoords, 0, destCoords, 0, sourceCoords.length, 0.5F, 0.5F, 2.0F, 2.0F, 2);
/* 628 */     if (print) {
/* 629 */       System.out.println("Using " + equations + " equations:");
/* 630 */       for (int m = 0; m < 6; m++) {
/* 631 */         System.out.println("c0[" + m + "] = " + c0[m] + ", recovered as " + coeffs[m] + " (ratio = " + (c0[m] / coeffs[m]) + ")");
/* 634 */         System.out.println("c1[" + m + "] = " + c1[m] + ", recovered as " + coeffs[m + 6] + " (ratio = " + (c1[m] / coeffs[m + 6]) + ")");
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void main(String[] args) {
/* 642 */     for (int times = 0; times < 3; times++) {
/* 643 */       doTest(6 + 50 * times, true);
/* 644 */       System.out.println();
/*     */     } 
/* 647 */     int trials = 10000;
/* 648 */     int points = 6;
/* 650 */     long startTime = System.currentTimeMillis();
/* 651 */     for (int i = 0; i < trials; i++)
/* 652 */       doTest(points, false); 
/* 654 */     long endTime = System.currentTimeMillis();
/* 655 */     System.out.println("Did " + trials + " " + points + "-point solutions in " + ((float)(endTime - startTime) / 1000.0F) + " seconds.");
/* 658 */     System.out.println("Rate = " + (trials * 1000.0F / (float)(endTime - startTime)) + " trials/second");
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\ja\\util\PolyWarpSolver.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */