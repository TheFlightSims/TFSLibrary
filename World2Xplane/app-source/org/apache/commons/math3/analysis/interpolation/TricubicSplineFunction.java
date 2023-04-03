/*     */ package org.apache.commons.math3.analysis.interpolation;
/*     */ 
/*     */ import org.apache.commons.math3.analysis.TrivariateFunction;
/*     */ import org.apache.commons.math3.exception.OutOfRangeException;
/*     */ 
/*     */ class TricubicSplineFunction implements TrivariateFunction {
/*     */   private static final short N = 4;
/*     */   
/* 428 */   private final double[][][] a = new double[4][4][4];
/*     */   
/*     */   public TricubicSplineFunction(double[] aV) {
/* 434 */     for (int i = 0; i < 4; i++) {
/* 435 */       for (int j = 0; j < 4; j++) {
/* 436 */         for (int k = 0; k < 4; k++)
/* 437 */           this.a[i][j][k] = aV[i + 4 * (j + 4 * k)]; 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public double value(double x, double y, double z) {
/* 450 */     if (x < 0.0D || x > 1.0D)
/* 451 */       throw new OutOfRangeException(Double.valueOf(x), Integer.valueOf(0), Integer.valueOf(1)); 
/* 453 */     if (y < 0.0D || y > 1.0D)
/* 454 */       throw new OutOfRangeException(Double.valueOf(y), Integer.valueOf(0), Integer.valueOf(1)); 
/* 456 */     if (z < 0.0D || z > 1.0D)
/* 457 */       throw new OutOfRangeException(Double.valueOf(z), Integer.valueOf(0), Integer.valueOf(1)); 
/* 460 */     double x2 = x * x;
/* 461 */     double x3 = x2 * x;
/* 462 */     double[] pX = { 1.0D, x, x2, x3 };
/* 464 */     double y2 = y * y;
/* 465 */     double y3 = y2 * y;
/* 466 */     double[] pY = { 1.0D, y, y2, y3 };
/* 468 */     double z2 = z * z;
/* 469 */     double z3 = z2 * z;
/* 470 */     double[] pZ = { 1.0D, z, z2, z3 };
/* 472 */     double result = 0.0D;
/* 473 */     for (int i = 0; i < 4; i++) {
/* 474 */       for (int j = 0; j < 4; j++) {
/* 475 */         for (int k = 0; k < 4; k++)
/* 476 */           result += this.a[i][j][k] * pX[i] * pY[j] * pZ[k]; 
/*     */       } 
/*     */     } 
/* 481 */     return result;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\analysis\interpolation\TricubicSplineFunction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */