/*     */ package org.geotools.math;
/*     */ 
/*     */ import org.geotools.resources.Java6;
/*     */ import org.geotools.resources.XArray;
/*     */ 
/*     */ public final class XMath {
/*  38 */   private static final double[] POW10 = new double[] { 
/*  38 */       1.0D, 10.0D, 100.0D, 1000.0D, 10000.0D, 100000.0D, 1000000.0D, 1.0E7D, 1.0E8D, 1.0E9D, 
/*  38 */       1.0E10D, 1.0E11D, 1.0E12D, 1.0E13D, 1.0E14D, 1.0E15D, 1.0E16D, 1.0E17D, 1.0E18D, 1.0E19D, 
/*  38 */       1.0E20D, 1.0E21D, 1.0E22D };
/*     */   
/*  51 */   private static short[] primes = new short[] { 2, 3 };
/*     */   
/*     */   private static final int MAX_PRIMES_LENGTH = 6542;
/*     */   
/*     */   public static double pow10(double x) {
/*  74 */     int ix = (int)x;
/*  75 */     if (ix == x)
/*  76 */       return pow10(ix); 
/*  78 */     return Math.pow(10.0D, x);
/*     */   }
/*     */   
/*     */   public static strictfp double pow10(int x) {
/*  96 */     if (x >= 0) {
/*  97 */       if (x < POW10.length)
/*  98 */         return POW10[x]; 
/* 100 */     } else if (x != Integer.MIN_VALUE) {
/* 101 */       int nx = -x;
/* 102 */       if (nx < POW10.length)
/* 103 */         return 1.0D / POW10[nx]; 
/*     */     } 
/*     */     try {
/* 112 */       return Double.parseDouble("1E" + x);
/* 113 */     } catch (NumberFormatException exception) {
/* 114 */       return StrictMath.pow(10.0D, x);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static int sgn(double x) {
/* 130 */     if (x > 0.0D)
/* 130 */       return 1; 
/* 131 */     if (x < 0.0D)
/* 131 */       return -1; 
/* 132 */     return 0;
/*     */   }
/*     */   
/*     */   public static int sgn(float x) {
/* 147 */     if (x > 0.0F)
/* 147 */       return 1; 
/* 148 */     if (x < 0.0F)
/* 148 */       return -1; 
/* 149 */     return 0;
/*     */   }
/*     */   
/*     */   public static int sgn(long x) {
/* 162 */     if (x > 0L)
/* 162 */       return 1; 
/* 163 */     if (x < 0L)
/* 163 */       return -1; 
/* 164 */     return 0;
/*     */   }
/*     */   
/*     */   public static int sgn(int x) {
/* 177 */     if (x > 0)
/* 177 */       return 1; 
/* 178 */     if (x < 0)
/* 178 */       return -1; 
/* 179 */     return 0;
/*     */   }
/*     */   
/*     */   public static short sgn(short x) {
/* 192 */     if (x > 0)
/* 192 */       return 1; 
/* 193 */     if (x < 0)
/* 193 */       return -1; 
/* 194 */     return 0;
/*     */   }
/*     */   
/*     */   public static byte sgn(byte x) {
/* 207 */     if (x > 0)
/* 207 */       return 1; 
/* 208 */     if (x < 0)
/* 208 */       return -1; 
/* 209 */     return 0;
/*     */   }
/*     */   
/*     */   public static double roundIfAlmostInteger(double value, int maxULP) {
/* 222 */     double target = Math.rint(value);
/* 223 */     if (value != target) {
/* 224 */       boolean pos = (value < target);
/* 225 */       double candidate = value;
/* 226 */       while (--maxULP >= 0) {
/* 227 */         candidate = pos ? org.geotools.resources.XMath.next(candidate) : org.geotools.resources.XMath.previous(candidate);
/* 228 */         if (candidate == target)
/* 229 */           return target; 
/*     */       } 
/*     */     } 
/* 233 */     return value;
/*     */   }
/*     */   
/*     */   public static double trimDecimalFractionDigits(double value, int maxULP, int n) {
/* 255 */     double lower = value;
/* 256 */     double upper = value;
/* 257 */     n = countDecimalFractionDigits(value) - n;
/* 258 */     if (n > 0)
/* 259 */       for (int i = 0; i < maxULP; i++) {
/* 260 */         if (countDecimalFractionDigits(lower = org.geotools.resources.XMath.previous(lower)) <= n)
/* 260 */           return lower; 
/* 261 */         if (countDecimalFractionDigits(upper = org.geotools.resources.XMath.next(upper)) <= n)
/* 261 */           return upper; 
/*     */       }  
/* 264 */     return value;
/*     */   }
/*     */   
/*     */   public static int countDecimalFractionDigits(double value) {
/*     */     int upper, power;
/* 276 */     String asText = Double.toString(value);
/* 277 */     int exp = asText.indexOf('E');
/* 279 */     if (exp >= 0) {
/* 280 */       upper = exp;
/* 281 */       power = Integer.parseInt(asText.substring(exp + 1));
/*     */     } else {
/* 283 */       upper = asText.length();
/* 284 */       power = 0;
/*     */     } 
/* 286 */     while (asText.charAt(--upper) == '0');
/* 287 */     return Math.max(upper - asText.indexOf('.') - power, 0);
/*     */   }
/*     */   
/*     */   public static float toNaN(int index) throws IndexOutOfBoundsException {
/* 303 */     index += 2097152;
/* 304 */     if (index >= 0 && index <= 4194303) {
/* 305 */       float value = Float.intBitsToFloat(2143289344 + index);
/* 306 */       assert Float.isNaN(value) : value;
/* 307 */       return value;
/*     */     } 
/* 310 */     throw new IndexOutOfBoundsException(Integer.toHexString(index));
/*     */   }
/*     */   
/*     */   public static synchronized int primeNumber(int index) throws IndexOutOfBoundsException {
/* 327 */     if (index < 0 || index >= 6542)
/* 328 */       throw new IndexOutOfBoundsException(String.valueOf(index)); 
/* 330 */     short[] primes = XMath.primes;
/* 331 */     if (index >= primes.length) {
/* 332 */       int i = primes.length;
/* 333 */       int n = primes[i - 1] & 0xFFFF;
/* 334 */       primes = XArray.resize(primes, Math.min((index | 0xF) + 1, 6542));
/*     */       label23: while (true) {
/* 337 */         n += 2;
/* 338 */         for (int j = 1; j < i; j++) {
/* 339 */           if (n % (primes[j] & 0xFFFF) == 0)
/*     */             continue label23; 
/*     */         } 
/* 346 */         assert n < 65535 : i;
/* 347 */         primes[i] = (short)n;
/* 350 */         if (++i >= primes.length) {
/* 351 */           XMath.primes = primes;
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } 
/* 353 */     return primes[index] & 0xFFFF;
/*     */   }
/*     */   
/*     */   public static int[] divisors(int number) {
/* 365 */     if (number == 0)
/* 366 */       return new int[0]; 
/* 368 */     number = Math.abs(number);
/* 369 */     int[] divisors = new int[16];
/* 370 */     divisors[0] = 1;
/* 371 */     int count = 1;
/* 378 */     int sqrt = (int)(Math.sqrt(number) + 1.0E-6D);
/*     */     int p;
/* 379 */     for (int i = 0; (p = primeNumber(i)) <= sqrt; i++) {
/* 380 */       if (number % p == 0) {
/* 381 */         if (count == divisors.length)
/* 382 */           divisors = XArray.resize(divisors, count * 2); 
/* 384 */         divisors[count++] = p;
/*     */       } 
/*     */     } 
/* 392 */     int source = count;
/* 393 */     if (count * 2 > divisors.length)
/* 394 */       divisors = XArray.resize(divisors, count * 2); 
/* 396 */     int d1 = divisors[--source];
/* 397 */     int d2 = number / d1;
/* 398 */     if (d1 != d2)
/* 399 */       divisors[count++] = d2; 
/* 401 */     while (--source >= 0)
/* 402 */       divisors[count++] = number / divisors[source]; 
/* 409 */     for (int j = 1; j < count; j++) {
/* 410 */       d1 = divisors[j];
/* 411 */       for (int k = j; k < count; k++) {
/* 412 */         d2 = d1 * divisors[k];
/* 413 */         if (number % d2 == 0) {
/* 414 */           int m = Java6.binarySearch(divisors, k, count, d2);
/* 415 */           if (m < 0) {
/* 416 */             m ^= 0xFFFFFFFF;
/* 417 */             if (count == divisors.length)
/* 418 */               divisors = XArray.resize(divisors, count * 2); 
/* 420 */             System.arraycopy(divisors, m, divisors, m + 1, count - m);
/* 421 */             divisors[m] = d2;
/* 422 */             count++;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 427 */     divisors = XArray.resize(divisors, count);
/* 428 */     assert XArray.isStrictlySorted(divisors);
/* 429 */     return divisors;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\math\XMath.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */