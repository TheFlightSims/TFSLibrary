/*     */ package org.apache.commons.math3.util;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ import org.apache.commons.math3.exception.MathArithmeticException;
/*     */ import org.apache.commons.math3.exception.NotFiniteNumberException;
/*     */ import org.apache.commons.math3.exception.NullArgumentException;
/*     */ import org.apache.commons.math3.exception.util.Localizable;
/*     */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*     */ 
/*     */ public final class MathUtils {
/*     */   public static final double TWO_PI = 6.283185307179586D;
/*     */   
/*     */   public static int hash(double value) {
/*  57 */     return (new Double(value)).hashCode();
/*     */   }
/*     */   
/*     */   public static int hash(double[] value) {
/*  68 */     return Arrays.hashCode(value);
/*     */   }
/*     */   
/*     */   public static double normalizeAngle(double a, double center) {
/*  91 */     return a - 6.283185307179586D * FastMath.floor((a + Math.PI - center) / 6.283185307179586D);
/*     */   }
/*     */   
/*     */   public static double reduce(double a, double period, double offset) {
/* 113 */     double p = FastMath.abs(period);
/* 114 */     return a - p * FastMath.floor((a - offset) / p) - offset;
/*     */   }
/*     */   
/*     */   public static byte copySign(byte magnitude, byte sign) {
/* 128 */     if ((magnitude >= 0 && sign >= 0) || (magnitude < 0 && sign < 0))
/* 130 */       return magnitude; 
/* 131 */     if (sign >= 0 && magnitude == Byte.MIN_VALUE)
/* 133 */       throw new MathArithmeticException(LocalizedFormats.OVERFLOW, new Object[0]); 
/* 135 */     return (byte)-magnitude;
/*     */   }
/*     */   
/*     */   public static short copySign(short magnitude, short sign) {
/* 150 */     if ((magnitude >= 0 && sign >= 0) || (magnitude < 0 && sign < 0))
/* 152 */       return magnitude; 
/* 153 */     if (sign >= 0 && magnitude == Short.MIN_VALUE)
/* 155 */       throw new MathArithmeticException(LocalizedFormats.OVERFLOW, new Object[0]); 
/* 157 */     return (short)-magnitude;
/*     */   }
/*     */   
/*     */   public static int copySign(int magnitude, int sign) {
/* 172 */     if ((magnitude >= 0 && sign >= 0) || (magnitude < 0 && sign < 0))
/* 174 */       return magnitude; 
/* 175 */     if (sign >= 0 && magnitude == Integer.MIN_VALUE)
/* 177 */       throw new MathArithmeticException(LocalizedFormats.OVERFLOW, new Object[0]); 
/* 179 */     return -magnitude;
/*     */   }
/*     */   
/*     */   public static long copySign(long magnitude, long sign) {
/* 194 */     if ((magnitude >= 0L && sign >= 0L) || (magnitude < 0L && sign < 0L))
/* 196 */       return magnitude; 
/* 197 */     if (sign >= 0L && magnitude == Long.MIN_VALUE)
/* 199 */       throw new MathArithmeticException(LocalizedFormats.OVERFLOW, new Object[0]); 
/* 201 */     return -magnitude;
/*     */   }
/*     */   
/*     */   public static void checkFinite(double x) {
/* 212 */     if (Double.isInfinite(x) || Double.isNaN(x))
/* 213 */       throw new NotFiniteNumberException(Double.valueOf(x), new Object[0]); 
/*     */   }
/*     */   
/*     */   public static void checkFinite(double[] val) {
/* 225 */     for (int i = 0; i < val.length; i++) {
/* 226 */       double x = val[i];
/* 227 */       if (Double.isInfinite(x) || Double.isNaN(x))
/* 228 */         throw new NotFiniteNumberException(LocalizedFormats.ARRAY_ELEMENT, Double.valueOf(x), new Object[] { Integer.valueOf(i) }); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void checkNotNull(Object o, Localizable pattern, Object... args) {
/* 244 */     if (o == null)
/* 245 */       throw new NullArgumentException(pattern, args); 
/*     */   }
/*     */   
/*     */   public static void checkNotNull(Object o) throws NullArgumentException {
/* 257 */     if (o == null)
/* 258 */       throw new NullArgumentException(); 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math\\util\MathUtils.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */