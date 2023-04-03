/*     */ package org.apache.commons.math3.special;
/*     */ 
/*     */ import org.apache.commons.math3.exception.MaxCountExceededException;
/*     */ import org.apache.commons.math3.util.ContinuedFraction;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ 
/*     */ public class Gamma {
/*     */   public static final double GAMMA = 0.5772156649015329D;
/*     */   
/*     */   private static final double DEFAULT_EPSILON = 1.0E-14D;
/*     */   
/*  38 */   private static final double[] LANCZOS = new double[] { 
/*  38 */       0.9999999999999971D, 57.15623566586292D, -59.59796035547549D, 14.136097974741746D, -0.4919138160976202D, 3.399464998481189E-5D, 4.652362892704858E-5D, -9.837447530487956E-5D, 1.580887032249125E-4D, -2.1026444172410488E-4D, 
/*  38 */       2.1743961811521265E-4D, -1.643181065367639E-4D, 8.441822398385275E-5D, -2.6190838401581408E-5D, 3.6899182659531625E-6D };
/*     */   
/*  56 */   private static final double HALF_LOG_2_PI = 0.5D * FastMath.log(6.283185307179586D);
/*     */   
/*     */   private static final double C_LIMIT = 49.0D;
/*     */   
/*     */   private static final double S_LIMIT = 1.0E-5D;
/*     */   
/*     */   public static double logGamma(double x) {
/*     */     double ret;
/*  88 */     if (Double.isNaN(x) || x <= 0.0D) {
/*  89 */       ret = Double.NaN;
/*     */     } else {
/*  91 */       double g = 4.7421875D;
/*  93 */       double sum = 0.0D;
/*  94 */       for (int i = LANCZOS.length - 1; i > 0; i--)
/*  95 */         sum += LANCZOS[i] / (x + i); 
/*  97 */       sum += LANCZOS[0];
/*  99 */       double tmp = x + g + 0.5D;
/* 100 */       ret = (x + 0.5D) * FastMath.log(tmp) - tmp + HALF_LOG_2_PI + FastMath.log(sum / x);
/*     */     } 
/* 104 */     return ret;
/*     */   }
/*     */   
/*     */   public static double regularizedGammaP(double a, double x) {
/* 116 */     return regularizedGammaP(a, x, 1.0E-14D, 2147483647);
/*     */   }
/*     */   
/*     */   public static double regularizedGammaP(double a, double x, double epsilon, int maxIterations) {
/*     */     double ret;
/* 153 */     if (Double.isNaN(a) || Double.isNaN(x) || a <= 0.0D || x < 0.0D) {
/* 154 */       ret = Double.NaN;
/* 155 */     } else if (x == 0.0D) {
/* 156 */       ret = 0.0D;
/* 157 */     } else if (x >= a + 1.0D) {
/* 160 */       ret = 1.0D - regularizedGammaQ(a, x, epsilon, maxIterations);
/*     */     } else {
/* 163 */       double n = 0.0D;
/* 164 */       double an = 1.0D / a;
/* 165 */       double sum = an;
/* 167 */       while (FastMath.abs(an / sum) > epsilon && n < maxIterations && sum < Double.POSITIVE_INFINITY) {
/* 170 */         n++;
/* 171 */         an *= x / (a + n);
/* 174 */         sum += an;
/*     */       } 
/* 176 */       if (n >= maxIterations)
/* 177 */         throw new MaxCountExceededException(Integer.valueOf(maxIterations)); 
/* 178 */       if (Double.isInfinite(sum)) {
/* 179 */         ret = 1.0D;
/*     */       } else {
/* 181 */         ret = FastMath.exp(-x + a * FastMath.log(x) - logGamma(a)) * sum;
/*     */       } 
/*     */     } 
/* 185 */     return ret;
/*     */   }
/*     */   
/*     */   public static double regularizedGammaQ(double a, double x) {
/* 197 */     return regularizedGammaQ(a, x, 1.0E-14D, 2147483647);
/*     */   }
/*     */   
/*     */   public static double regularizedGammaQ(final double a, double x, double epsilon, int maxIterations) {
/*     */     double ret;
/* 231 */     if (Double.isNaN(a) || Double.isNaN(x) || a <= 0.0D || x < 0.0D) {
/* 232 */       ret = Double.NaN;
/* 233 */     } else if (x == 0.0D) {
/* 234 */       ret = 1.0D;
/* 235 */     } else if (x < a + 1.0D) {
/* 238 */       ret = 1.0D - regularizedGammaP(a, x, epsilon, maxIterations);
/*     */     } else {
/* 241 */       ContinuedFraction cf = new ContinuedFraction() {
/*     */           protected double getA(int n, double x) {
/* 245 */             return 2.0D * n + 1.0D - a + x;
/*     */           }
/*     */           
/*     */           protected double getB(int n, double x) {
/* 250 */             return n * (a - n);
/*     */           }
/*     */         };
/* 254 */       ret = 1.0D / cf.evaluate(x, epsilon, maxIterations);
/* 255 */       ret = FastMath.exp(-x + a * FastMath.log(x) - logGamma(a)) * ret;
/*     */     } 
/* 258 */     return ret;
/*     */   }
/*     */   
/*     */   public static double digamma(double x) {
/* 283 */     if (x > 0.0D && x <= 1.0E-5D)
/* 286 */       return -0.5772156649015329D - 1.0D / x; 
/* 289 */     if (x >= 49.0D) {
/* 291 */       double inv = 1.0D / x * x;
/* 295 */       return FastMath.log(x) - 0.5D / x - inv * (0.08333333333333333D + inv * (0.008333333333333333D - inv / 252.0D));
/*     */     } 
/* 298 */     return digamma(x + 1.0D) - 1.0D / x;
/*     */   }
/*     */   
/*     */   public static double trigamma(double x) {
/* 313 */     if (x > 0.0D && x <= 1.0E-5D)
/* 314 */       return 1.0D / x * x; 
/* 317 */     if (x >= 49.0D) {
/* 318 */       double inv = 1.0D / x * x;
/* 323 */       return 1.0D / x + inv / 2.0D + inv / x * (0.16666666666666666D - inv * (0.03333333333333333D + inv / 42.0D));
/*     */     } 
/* 326 */     return trigamma(x + 1.0D) + 1.0D / x * x;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\special\Gamma.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */