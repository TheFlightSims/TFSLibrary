/*     */ package org.apache.commons.math3.special;
/*     */ 
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ 
/*     */ public class Erf {
/*     */   private static final double X_CRIT = 0.4769362762044697D;
/*     */   
/*     */   public static double erf(double x) {
/*  67 */     if (FastMath.abs(x) > 40.0D)
/*  68 */       return (x > 0.0D) ? 1.0D : -1.0D; 
/*  70 */     double ret = Gamma.regularizedGammaP(0.5D, x * x, 1.0E-15D, 10000);
/*  71 */     return (x < 0.0D) ? -ret : ret;
/*     */   }
/*     */   
/*     */   public static double erfc(double x) {
/*  98 */     if (FastMath.abs(x) > 40.0D)
/*  99 */       return (x > 0.0D) ? 0.0D : 2.0D; 
/* 101 */     double ret = Gamma.regularizedGammaQ(0.5D, x * x, 1.0E-15D, 10000);
/* 102 */     return (x < 0.0D) ? (2.0D - ret) : ret;
/*     */   }
/*     */   
/*     */   public static double erf(double x1, double x2) {
/* 116 */     if (x1 > x2)
/* 117 */       return -erf(x2, x1); 
/* 120 */     return (x1 < -0.4769362762044697D) ? ((x2 < 0.0D) ? (erfc(-x2) - erfc(-x1)) : (erf(x2) - erf(x1))) : ((x2 > 0.4769362762044697D && x1 > 0.0D) ? (erfc(x1) - erfc(x2)) : (erf(x2) - erf(x1)));
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\special\Erf.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */