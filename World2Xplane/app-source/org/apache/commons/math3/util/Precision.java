/*     */ package org.apache.commons.math3.util;
/*     */ 
/*     */ import java.math.BigDecimal;
/*     */ import org.apache.commons.math3.exception.MathArithmeticException;
/*     */ import org.apache.commons.math3.exception.MathIllegalArgumentException;
/*     */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*     */ 
/*     */ public class Precision {
/*     */   public static final double EPSILON = 1.1102230246251565E-16D;
/*     */   
/*     */   public static final double SAFE_MIN = 2.2250738585072014E-308D;
/*     */   
/*     */   private static final long SGN_MASK = -9223372036854775808L;
/*     */   
/*     */   private static final int SGN_MASK_FLOAT = -2147483648;
/*     */   
/*     */   public static int compareTo(double x, double y, double eps) {
/*  65 */     if (equals(x, y, eps))
/*  66 */       return 0; 
/*  67 */     if (x < y)
/*  68 */       return -1; 
/*  70 */     return 1;
/*     */   }
/*     */   
/*     */   public static int compareTo(double x, double y, int maxUlps) {
/*  91 */     if (equals(x, y, maxUlps))
/*  92 */       return 0; 
/*  93 */     if (x < y)
/*  94 */       return -1; 
/*  96 */     return 1;
/*     */   }
/*     */   
/*     */   public static boolean equals(float x, float y) {
/* 108 */     return equals(x, y, 1);
/*     */   }
/*     */   
/*     */   public static boolean equalsIncludingNaN(float x, float y) {
/* 121 */     return ((Float.isNaN(x) && Float.isNaN(y)) || equals(x, y, 1));
/*     */   }
/*     */   
/*     */   public static boolean equals(float x, float y, float eps) {
/* 135 */     return (equals(x, y, 1) || FastMath.abs(y - x) <= eps);
/*     */   }
/*     */   
/*     */   public static boolean equalsIncludingNaN(float x, float y, float eps) {
/* 150 */     return (equalsIncludingNaN(x, y) || FastMath.abs(y - x) <= eps);
/*     */   }
/*     */   
/*     */   public static boolean equals(float x, float y, int maxUlps) {
/* 172 */     int xInt = Float.floatToIntBits(x);
/* 173 */     int yInt = Float.floatToIntBits(y);
/* 176 */     if (xInt < 0)
/* 177 */       xInt = Integer.MIN_VALUE - xInt; 
/* 179 */     if (yInt < 0)
/* 180 */       yInt = Integer.MIN_VALUE - yInt; 
/* 183 */     boolean isEqual = (FastMath.abs(xInt - yInt) <= maxUlps);
/* 185 */     return (isEqual && !Float.isNaN(x) && !Float.isNaN(y));
/*     */   }
/*     */   
/*     */   public static boolean equalsIncludingNaN(float x, float y, int maxUlps) {
/* 201 */     return ((Float.isNaN(x) && Float.isNaN(y)) || equals(x, y, maxUlps));
/*     */   }
/*     */   
/*     */   public static boolean equals(double x, double y) {
/* 213 */     return equals(x, y, 1);
/*     */   }
/*     */   
/*     */   public static boolean equalsIncludingNaN(double x, double y) {
/* 226 */     return ((Double.isNaN(x) && Double.isNaN(y)) || equals(x, y, 1));
/*     */   }
/*     */   
/*     */   public static boolean equals(double x, double y, double eps) {
/* 241 */     return (equals(x, y, 1) || FastMath.abs(y - x) <= eps);
/*     */   }
/*     */   
/*     */   public static boolean equalsIncludingNaN(double x, double y, double eps) {
/* 256 */     return (equalsIncludingNaN(x, y) || FastMath.abs(y - x) <= eps);
/*     */   }
/*     */   
/*     */   public static boolean equals(double x, double y, int maxUlps) {
/* 277 */     long xInt = Double.doubleToLongBits(x);
/* 278 */     long yInt = Double.doubleToLongBits(y);
/* 281 */     if (xInt < 0L)
/* 282 */       xInt = Long.MIN_VALUE - xInt; 
/* 284 */     if (yInt < 0L)
/* 285 */       yInt = Long.MIN_VALUE - yInt; 
/* 288 */     boolean isEqual = (FastMath.abs(xInt - yInt) <= maxUlps);
/* 290 */     return (isEqual && !Double.isNaN(x) && !Double.isNaN(y));
/*     */   }
/*     */   
/*     */   public static boolean equalsIncludingNaN(double x, double y, int maxUlps) {
/* 306 */     return ((Double.isNaN(x) && Double.isNaN(y)) || equals(x, y, maxUlps));
/*     */   }
/*     */   
/*     */   public static double round(double x, int scale) {
/* 319 */     return round(x, scale, 4);
/*     */   }
/*     */   
/*     */   public static double round(double x, int scale, int roundingMethod) {
/*     */     try {
/* 341 */       return (new BigDecimal(Double.toString(x))).setScale(scale, roundingMethod).doubleValue();
/* 345 */     } catch (NumberFormatException ex) {
/* 346 */       if (Double.isInfinite(x))
/* 347 */         return x; 
/* 349 */       return Double.NaN;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static float round(float x, int scale) {
/* 364 */     return round(x, scale, 4);
/*     */   }
/*     */   
/*     */   public static float round(float x, int scale, int roundingMethod) {
/* 379 */     float sign = FastMath.copySign(1.0F, x);
/* 380 */     float factor = (float)FastMath.pow(10.0D, scale) * sign;
/* 381 */     return (float)roundUnscaled((x * factor), sign, roundingMethod) / factor;
/*     */   }
/*     */   
/*     */   private static double roundUnscaled(double unscaled, double sign, int roundingMethod) {
/*     */     double fraction;
/* 399 */     switch (roundingMethod) {
/*     */       case 2:
/* 401 */         if (sign == -1.0D) {
/* 402 */           unscaled = FastMath.floor(FastMath.nextAfter(unscaled, Double.NEGATIVE_INFINITY));
/*     */         } else {
/* 404 */           unscaled = FastMath.ceil(FastMath.nextAfter(unscaled, Double.POSITIVE_INFINITY));
/*     */         } 
/* 474 */         return unscaled;
/*     */       case 1:
/*     */         unscaled = FastMath.floor(FastMath.nextAfter(unscaled, Double.NEGATIVE_INFINITY));
/* 474 */         return unscaled;
/*     */       case 3:
/*     */         if (sign == -1.0D) {
/*     */           unscaled = FastMath.ceil(FastMath.nextAfter(unscaled, Double.POSITIVE_INFINITY));
/*     */         } else {
/*     */           unscaled = FastMath.floor(FastMath.nextAfter(unscaled, Double.NEGATIVE_INFINITY));
/*     */         } 
/* 474 */         return unscaled;
/*     */       case 5:
/*     */         unscaled = FastMath.nextAfter(unscaled, Double.NEGATIVE_INFINITY);
/*     */         fraction = unscaled - FastMath.floor(unscaled);
/*     */         if (fraction > 0.5D) {
/*     */           unscaled = FastMath.ceil(unscaled);
/*     */         } else {
/*     */           unscaled = FastMath.floor(unscaled);
/*     */         } 
/* 474 */         return unscaled;
/*     */       case 6:
/*     */         fraction = unscaled - FastMath.floor(unscaled);
/*     */         if (fraction > 0.5D) {
/*     */           unscaled = FastMath.ceil(unscaled);
/*     */         } else if (fraction < 0.5D) {
/*     */           unscaled = FastMath.floor(unscaled);
/*     */         } else if (FastMath.floor(unscaled) / 2.0D == FastMath.floor(Math.floor(unscaled) / 2.0D)) {
/*     */           unscaled = FastMath.floor(unscaled);
/*     */         } else {
/*     */           unscaled = FastMath.ceil(unscaled);
/*     */         } 
/* 474 */         return unscaled;
/*     */       case 4:
/*     */         unscaled = FastMath.nextAfter(unscaled, Double.POSITIVE_INFINITY);
/*     */         fraction = unscaled - FastMath.floor(unscaled);
/*     */         if (fraction >= 0.5D) {
/*     */           unscaled = FastMath.ceil(unscaled);
/*     */         } else {
/*     */           unscaled = FastMath.floor(unscaled);
/*     */         } 
/* 474 */         return unscaled;
/*     */       case 7:
/*     */         if (unscaled != FastMath.floor(unscaled))
/*     */           throw new MathArithmeticException(); 
/* 474 */         return unscaled;
/*     */       case 0:
/*     */         unscaled = FastMath.ceil(FastMath.nextAfter(unscaled, Double.POSITIVE_INFINITY));
/* 474 */         return unscaled;
/*     */     } 
/*     */     throw new MathIllegalArgumentException(LocalizedFormats.INVALID_ROUNDING_METHOD, new Object[] { 
/*     */           Integer.valueOf(roundingMethod), "ROUND_CEILING", Integer.valueOf(2), "ROUND_DOWN", Integer.valueOf(1), "ROUND_FLOOR", Integer.valueOf(3), "ROUND_HALF_DOWN", Integer.valueOf(5), "ROUND_HALF_EVEN", 
/*     */           Integer.valueOf(6), "ROUND_HALF_UP", Integer.valueOf(4), "ROUND_UNNECESSARY", Integer.valueOf(7), "ROUND_UP", Integer.valueOf(0) });
/*     */   }
/*     */   
/*     */   public static double representableDelta(double x, double originalDelta) {
/* 494 */     return x + originalDelta - x;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math\\util\Precision.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */