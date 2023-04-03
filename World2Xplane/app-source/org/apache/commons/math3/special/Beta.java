/*     */ package org.apache.commons.math3.special;
/*     */ 
/*     */ import org.apache.commons.math3.util.ContinuedFraction;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ 
/*     */ public class Beta {
/*     */   private static final double DEFAULT_EPSILON = 1.0E-14D;
/*     */   
/*     */   public static double regularizedBeta(double x, double a, double b) {
/*  50 */     return regularizedBeta(x, a, b, 1.0E-14D, 2147483647);
/*     */   }
/*     */   
/*     */   public static double regularizedBeta(double x, double a, double b, double epsilon) {
/*  71 */     return regularizedBeta(x, a, b, epsilon, 2147483647);
/*     */   }
/*     */   
/*     */   public static double regularizedBeta(double x, double a, double b, int maxIterations) {
/*  88 */     return regularizedBeta(x, a, b, 1.0E-14D, maxIterations);
/*     */   }
/*     */   
/*     */   public static double regularizedBeta(double x, final double a, final double b, double epsilon, int maxIterations) {
/*     */     double ret;
/* 120 */     if (Double.isNaN(x) || Double.isNaN(a) || Double.isNaN(b) || x < 0.0D || x > 1.0D || a <= 0.0D || b <= 0.0D) {
/* 127 */       ret = Double.NaN;
/* 128 */     } else if (x > (a + 1.0D) / (a + b + 2.0D)) {
/* 129 */       ret = 1.0D - regularizedBeta(1.0D - x, b, a, epsilon, maxIterations);
/*     */     } else {
/* 131 */       ContinuedFraction fraction = new ContinuedFraction() {
/*     */           protected double getB(int n, double x) {
/*     */             double ret;
/* 137 */             if (n % 2 == 0) {
/* 138 */               double m = n / 2.0D;
/* 139 */               ret = m * (b - m) * x / (a + 2.0D * m - 1.0D) * (a + 2.0D * m);
/*     */             } else {
/* 142 */               double m = (n - 1.0D) / 2.0D;
/* 143 */               ret = -((a + m) * (a + b + m) * x) / (a + 2.0D * m) * (a + 2.0D * m + 1.0D);
/*     */             } 
/* 146 */             return ret;
/*     */           }
/*     */           
/*     */           protected double getA(int n, double x) {
/* 151 */             return 1.0D;
/*     */           }
/*     */         };
/* 154 */       ret = FastMath.exp(a * FastMath.log(x) + b * FastMath.log(1.0D - x) - FastMath.log(a) - logBeta(a, b, epsilon, maxIterations)) * 1.0D / fraction.evaluate(x, epsilon, maxIterations);
/*     */     } 
/* 159 */     return ret;
/*     */   }
/*     */   
/*     */   public static double logBeta(double a, double b) {
/* 170 */     return logBeta(a, b, 1.0E-14D, 2147483647);
/*     */   }
/*     */   
/*     */   public static double logBeta(double a, double b, double epsilon, int maxIterations) {
/*     */     double ret;
/* 195 */     if (Double.isNaN(a) || Double.isNaN(b) || a <= 0.0D || b <= 0.0D) {
/* 199 */       ret = Double.NaN;
/*     */     } else {
/* 201 */       ret = Gamma.logGamma(a) + Gamma.logGamma(b) - Gamma.logGamma(a + b);
/*     */     } 
/* 205 */     return ret;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\special\Beta.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */