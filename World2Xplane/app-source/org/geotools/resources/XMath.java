/*     */ package org.geotools.resources;
/*     */ 
/*     */ import java.text.ChoiceFormat;
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ 
/*     */ public final class XMath {
/*     */   public static final double LN10 = 2.302585092994046D;
/*     */   
/*  49 */   private static final double[] POW10 = new double[] { 
/*  49 */       1.0D, 10.0D, 100.0D, 1000.0D, 10000.0D, 100000.0D, 1000000.0D, 1.0E7D, 1.0E8D, 1.0E9D, 
/*  49 */       1.0E10D, 1.0E11D, 1.0E12D, 1.0E13D, 1.0E14D, 1.0E15D, 1.0E16D, 1.0E17D, 1.0E18D, 1.0E19D, 
/*  49 */       1.0E20D, 1.0E21D, 1.0E22D };
/*     */   
/*     */   @Deprecated
/*     */   public static double hypot(double x, double y) {
/*  68 */     return Math.sqrt(x * x + y * y);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public static double log10(double x) {
/*  79 */     return Math.log(x) / 2.302585092994046D;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public static double pow10(double x) {
/*  89 */     return org.geotools.math.XMath.pow10(x);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public static strictfp double pow10(int x) {
/* 101 */     return org.geotools.math.XMath.pow10(x);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public static int sgn(double x) {
/* 116 */     return org.geotools.math.XMath.sgn(x);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public static int sgn(float x) {
/* 131 */     return org.geotools.math.XMath.sgn(x);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public static int sgn(long x) {
/* 144 */     return org.geotools.math.XMath.sgn(x);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public static int sgn(int x) {
/* 157 */     return org.geotools.math.XMath.sgn(x);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public static short sgn(short x) {
/* 170 */     return org.geotools.math.XMath.sgn(x);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public static byte sgn(byte x) {
/* 183 */     return org.geotools.math.XMath.sgn(x);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public static double round(double value, int flu) {
/* 199 */     return org.geotools.math.XMath.roundIfAlmostInteger(value, flu);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public static double fixRoundingError(double value, int n) {
/* 221 */     return org.geotools.math.XMath.trimDecimalFractionDigits(value, 4, n);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public static int countFractionDigits(double value) {
/* 234 */     return org.geotools.math.XMath.countDecimalFractionDigits(value);
/*     */   }
/*     */   
/*     */   private static float next(float f, boolean positive) {
/* 246 */     int SIGN = Integer.MIN_VALUE;
/* 247 */     int POSITIVEINFINITY = 2139095040;
/* 250 */     if (Float.isNaN(f))
/* 251 */       return f; 
/* 255 */     if (f == 0.0F) {
/* 256 */       float smallestPositiveFloat = Float.intBitsToFloat(1);
/* 257 */       return positive ? smallestPositiveFloat : -smallestPositiveFloat;
/*     */     } 
/* 262 */     int bits = Float.floatToIntBits(f);
/* 265 */     int magnitude = bits & Integer.MAX_VALUE;
/* 269 */     if (((bits > 0)) == positive) {
/* 270 */       if (magnitude != 2139095040)
/* 271 */         magnitude++; 
/*     */     } else {
/* 274 */       magnitude--;
/*     */     } 
/* 278 */     int signbit = bits & Integer.MIN_VALUE;
/* 279 */     return Float.intBitsToFloat(magnitude | signbit);
/*     */   }
/*     */   
/*     */   public static float next(float f) {
/* 289 */     return next(f, true);
/*     */   }
/*     */   
/*     */   public static float previous(float f) {
/* 299 */     return next(f, false);
/*     */   }
/*     */   
/*     */   public static double next(double f) {
/* 311 */     return ChoiceFormat.nextDouble(f);
/*     */   }
/*     */   
/*     */   public static double previous(double f) {
/* 323 */     return ChoiceFormat.previousDouble(f);
/*     */   }
/*     */   
/*     */   public static double rool(Class<?> type, double value, int amount) throws IllegalArgumentException {
/* 361 */     if (Double.class.equals(type)) {
/* 362 */       if (amount < 0) {
/*     */         do {
/* 364 */           value = previous(value);
/* 365 */         } while (++amount != 0);
/* 366 */       } else if (amount != 0) {
/*     */         do {
/* 368 */           value = next(value);
/* 369 */         } while (--amount != 0);
/*     */       } 
/* 371 */       return value;
/*     */     } 
/* 373 */     if (Float.class.equals(type)) {
/* 374 */       float vf = (float)value;
/* 375 */       if (amount < 0) {
/*     */         do {
/* 377 */           vf = previous(vf);
/* 378 */         } while (++amount != 0);
/* 379 */       } else if (amount != 0) {
/*     */         do {
/* 381 */           vf = next(vf);
/* 382 */         } while (--amount != 0);
/*     */       } 
/* 384 */       return vf;
/*     */     } 
/* 386 */     if (isInteger(type))
/* 387 */       return value + amount; 
/* 389 */     throw new IllegalArgumentException(Errors.format(199, type));
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public static float toNaN(int index) throws IndexOutOfBoundsException {
/* 405 */     return org.geotools.math.XMath.toNaN(index);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public static boolean isReal(Class<?> type) {
/* 419 */     return ((type != null && Double.class.equals(type)) || Float.class.equals(type));
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public static boolean isInteger(Class<?> type) {
/* 436 */     return ((type != null && Long.class.equals(type)) || Integer.class.equals(type) || Short.class.equals(type) || Byte.class.equals(type));
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public static int getBitCount(Class<?> type) {
/* 453 */     if (Double.class.equals(type))
/* 453 */       return 64; 
/* 454 */     if (Float.class.equals(type))
/* 454 */       return 32; 
/* 455 */     if (Long.class.equals(type))
/* 455 */       return 64; 
/* 456 */     if (Integer.class.equals(type))
/* 456 */       return 32; 
/* 457 */     if (Short.class.equals(type))
/* 457 */       return 16; 
/* 458 */     if (Byte.class.equals(type))
/* 458 */       return 8; 
/* 459 */     if (Character.class.equals(type))
/* 459 */       return 16; 
/* 460 */     if (Boolean.class.equals(type))
/* 460 */       return 1; 
/* 461 */     return 0;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public static Class<?> primitiveToWrapper(Class<?> type) {
/* 475 */     if (char.class.equals(type))
/* 475 */       return Character.class; 
/* 476 */     if (boolean.class.equals(type))
/* 476 */       return Boolean.class; 
/* 477 */     if (byte.class.equals(type))
/* 477 */       return Byte.class; 
/* 478 */     if (short.class.equals(type))
/* 478 */       return Short.class; 
/* 479 */     if (int.class.equals(type))
/* 479 */       return Integer.class; 
/* 480 */     if (long.class.equals(type))
/* 480 */       return Long.class; 
/* 481 */     if (float.class.equals(type))
/* 481 */       return Float.class; 
/* 482 */     if (double.class.equals(type))
/* 482 */       return Double.class; 
/* 483 */     return type;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public static <T> T valueOf(Class<T> type, String value) throws IllegalArgumentException, NumberFormatException {
/* 507 */     if (value == null)
/* 508 */       return null; 
/* 510 */     if (Double.class.equals(type))
/* 510 */       return (T)Double.valueOf(value); 
/* 511 */     if (Float.class.equals(type))
/* 511 */       return (T)Float.valueOf(value); 
/* 512 */     if (Long.class.equals(type))
/* 512 */       return (T)Long.valueOf(value); 
/* 513 */     if (Integer.class.equals(type))
/* 513 */       return (T)Integer.valueOf(value); 
/* 514 */     if (Short.class.equals(type))
/* 514 */       return (T)Short.valueOf(value); 
/* 515 */     if (Byte.class.equals(type))
/* 515 */       return (T)Byte.valueOf(value); 
/* 516 */     if (Boolean.class.equals(type))
/* 516 */       return (T)Boolean.valueOf(value); 
/* 517 */     throw new IllegalArgumentException(Errors.format(187, type));
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\resources\XMath.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */