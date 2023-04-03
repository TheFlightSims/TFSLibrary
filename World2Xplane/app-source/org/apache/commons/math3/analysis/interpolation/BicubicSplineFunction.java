/*     */ package org.apache.commons.math3.analysis.interpolation;
/*     */ 
/*     */ import org.apache.commons.math3.analysis.BivariateFunction;
/*     */ import org.apache.commons.math3.exception.OutOfRangeException;
/*     */ 
/*     */ class BicubicSplineFunction implements BivariateFunction {
/*     */   private static final short N = 4;
/*     */   
/* 366 */   private final double[][] a = new double[4][4];
/*     */   
/*     */   private BivariateFunction partialDerivativeX;
/*     */   
/*     */   private BivariateFunction partialDerivativeY;
/*     */   
/*     */   private BivariateFunction partialDerivativeXX;
/*     */   
/*     */   private BivariateFunction partialDerivativeYY;
/*     */   
/*     */   private BivariateFunction partialDerivativeXY;
/*     */   
/*     */   public BicubicSplineFunction(double[] a) {
/* 367 */     for (int i = 0; i < 4; i++) {
/* 368 */       for (int j = 0; j < 4; j++)
/* 369 */         this.a[i][j] = a[i + 4 * j]; 
/*     */     } 
/*     */   }
/*     */   
/*     */   public double value(double x, double y) {
/* 378 */     if (x < 0.0D || x > 1.0D)
/* 379 */       throw new OutOfRangeException(Double.valueOf(x), Integer.valueOf(0), Integer.valueOf(1)); 
/* 381 */     if (y < 0.0D || y > 1.0D)
/* 382 */       throw new OutOfRangeException(Double.valueOf(y), Integer.valueOf(0), Integer.valueOf(1)); 
/* 385 */     double x2 = x * x;
/* 386 */     double x3 = x2 * x;
/* 387 */     double[] pX = { 1.0D, x, x2, x3 };
/* 389 */     double y2 = y * y;
/* 390 */     double y3 = y2 * y;
/* 391 */     double[] pY = { 1.0D, y, y2, y3 };
/* 393 */     return apply(pX, pY, this.a);
/*     */   }
/*     */   
/*     */   private double apply(double[] pX, double[] pY, double[][] coeff) {
/* 405 */     double result = 0.0D;
/* 406 */     for (int i = 0; i < 4; i++) {
/* 407 */       for (int j = 0; j < 4; j++)
/* 408 */         result += coeff[i][j] * pX[i] * pY[j]; 
/*     */     } 
/* 412 */     return result;
/*     */   }
/*     */   
/*     */   public BivariateFunction partialDerivativeX() {
/* 419 */     if (this.partialDerivativeX == null)
/* 420 */       computePartialDerivatives(); 
/* 423 */     return this.partialDerivativeX;
/*     */   }
/*     */   
/*     */   public BivariateFunction partialDerivativeY() {
/* 429 */     if (this.partialDerivativeY == null)
/* 430 */       computePartialDerivatives(); 
/* 433 */     return this.partialDerivativeY;
/*     */   }
/*     */   
/*     */   public BivariateFunction partialDerivativeXX() {
/* 439 */     if (this.partialDerivativeXX == null)
/* 440 */       computePartialDerivatives(); 
/* 443 */     return this.partialDerivativeXX;
/*     */   }
/*     */   
/*     */   public BivariateFunction partialDerivativeYY() {
/* 449 */     if (this.partialDerivativeYY == null)
/* 450 */       computePartialDerivatives(); 
/* 453 */     return this.partialDerivativeYY;
/*     */   }
/*     */   
/*     */   public BivariateFunction partialDerivativeXY() {
/* 459 */     if (this.partialDerivativeXY == null)
/* 460 */       computePartialDerivatives(); 
/* 463 */     return this.partialDerivativeXY;
/*     */   }
/*     */   
/*     */   private void computePartialDerivatives() {
/* 470 */     final double[][] aX = new double[4][4];
/* 471 */     final double[][] aY = new double[4][4];
/* 472 */     final double[][] aXX = new double[4][4];
/* 473 */     final double[][] aYY = new double[4][4];
/* 474 */     final double[][] aXY = new double[4][4];
/* 476 */     for (int i = 0; i < 4; i++) {
/* 477 */       for (int j = 0; j < 4; j++) {
/* 478 */         double c = this.a[i][j];
/* 479 */         aX[i][j] = i * c;
/* 480 */         aY[i][j] = j * c;
/* 481 */         aXX[i][j] = (i - 1) * aX[i][j];
/* 482 */         aYY[i][j] = (j - 1) * aY[i][j];
/* 483 */         aXY[i][j] = j * aX[i][j];
/*     */       } 
/*     */     } 
/* 487 */     this.partialDerivativeX = new BivariateFunction() {
/*     */         public double value(double x, double y) {
/* 489 */           double x2 = x * x;
/* 490 */           double[] pX = { 0.0D, 1.0D, x, x2 };
/* 492 */           double y2 = y * y;
/* 493 */           double y3 = y2 * y;
/* 494 */           double[] pY = { 1.0D, y, y2, y3 };
/* 496 */           return BicubicSplineFunction.this.apply(pX, pY, aX);
/*     */         }
/*     */       };
/* 499 */     this.partialDerivativeY = new BivariateFunction() {
/*     */         public double value(double x, double y) {
/* 501 */           double x2 = x * x;
/* 502 */           double x3 = x2 * x;
/* 503 */           double[] pX = { 1.0D, x, x2, x3 };
/* 505 */           double y2 = y * y;
/* 506 */           double[] pY = { 0.0D, 1.0D, y, y2 };
/* 508 */           return BicubicSplineFunction.this.apply(pX, pY, aY);
/*     */         }
/*     */       };
/* 511 */     this.partialDerivativeXX = new BivariateFunction() {
/*     */         public double value(double x, double y) {
/* 513 */           double[] pX = { 0.0D, 0.0D, 1.0D, x };
/* 515 */           double y2 = y * y;
/* 516 */           double y3 = y2 * y;
/* 517 */           double[] pY = { 1.0D, y, y2, y3 };
/* 519 */           return BicubicSplineFunction.this.apply(pX, pY, aXX);
/*     */         }
/*     */       };
/* 522 */     this.partialDerivativeYY = new BivariateFunction() {
/*     */         public double value(double x, double y) {
/* 524 */           double x2 = x * x;
/* 525 */           double x3 = x2 * x;
/* 526 */           double[] pX = { 1.0D, x, x2, x3 };
/* 528 */           double[] pY = { 0.0D, 0.0D, 1.0D, y };
/* 530 */           return BicubicSplineFunction.this.apply(pX, pY, aYY);
/*     */         }
/*     */       };
/* 533 */     this.partialDerivativeXY = new BivariateFunction() {
/*     */         public double value(double x, double y) {
/* 535 */           double x2 = x * x;
/* 536 */           double[] pX = { 0.0D, 1.0D, x, x2 };
/* 538 */           double y2 = y * y;
/* 539 */           double[] pY = { 0.0D, 1.0D, y, y2 };
/* 541 */           return BicubicSplineFunction.this.apply(pX, pY, aXY);
/*     */         }
/*     */       };
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\analysis\interpolation\BicubicSplineFunction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */