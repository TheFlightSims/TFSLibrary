/*     */ package org.apache.commons.math3.util;
/*     */ 
/*     */ import java.math.BigInteger;
/*     */ import org.apache.commons.math3.exception.MathArithmeticException;
/*     */ import org.apache.commons.math3.exception.NotPositiveException;
/*     */ import org.apache.commons.math3.exception.NumberIsTooLargeException;
/*     */ import org.apache.commons.math3.exception.util.Localizable;
/*     */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*     */ 
/*     */ public final class ArithmeticUtils {
/*  35 */   static final long[] FACTORIALS = new long[] { 
/*  35 */       1L, 1L, 2L, 6L, 24L, 120L, 720L, 5040L, 40320L, 362880L, 
/*  35 */       3628800L, 39916800L, 479001600L, 6227020800L, 87178291200L, 1307674368000L, 20922789888000L, 355687428096000L, 6402373705728000L, 121645100408832000L, 
/*  35 */       2432902008176640000L };
/*     */   
/*     */   public static int addAndCheck(int x, int y) {
/*  60 */     long s = x + y;
/*  61 */     if (s < -2147483648L || s > 2147483647L)
/*  62 */       throw new MathArithmeticException(LocalizedFormats.OVERFLOW_IN_ADDITION, new Object[] { Integer.valueOf(x), Integer.valueOf(y) }); 
/*  64 */     return (int)s;
/*     */   }
/*     */   
/*     */   public static long addAndCheck(long a, long b) {
/*  78 */     return addAndCheck(a, b, (Localizable)LocalizedFormats.OVERFLOW_IN_ADDITION);
/*     */   }
/*     */   
/*     */   public static long binomialCoefficient(int n, int k) {
/* 108 */     checkBinomial(n, k);
/* 109 */     if (n == k || k == 0)
/* 110 */       return 1L; 
/* 112 */     if (k == 1 || k == n - 1)
/* 113 */       return n; 
/* 116 */     if (k > n / 2)
/* 117 */       return binomialCoefficient(n, n - k); 
/* 125 */     long result = 1L;
/* 126 */     if (n <= 61) {
/* 128 */       int i = n - k + 1;
/* 129 */       for (int j = 1; j <= k; j++) {
/* 130 */         result = result * i / j;
/* 131 */         i++;
/*     */       } 
/* 133 */     } else if (n <= 66) {
/* 136 */       int i = n - k + 1;
/* 137 */       for (int j = 1; j <= k; j++) {
/* 144 */         long d = gcd(i, j);
/* 145 */         result = result / j / d * i / d;
/* 146 */         i++;
/*     */       } 
/*     */     } else {
/* 152 */       int i = n - k + 1;
/* 153 */       for (int j = 1; j <= k; j++) {
/* 154 */         long d = gcd(i, j);
/* 155 */         result = mulAndCheck(result / j / d, i / d);
/* 156 */         i++;
/*     */       } 
/*     */     } 
/* 159 */     return result;
/*     */   }
/*     */   
/*     */   public static double binomialCoefficientDouble(int n, int k) {
/* 186 */     checkBinomial(n, k);
/* 187 */     if (n == k || k == 0)
/* 188 */       return 1.0D; 
/* 190 */     if (k == 1 || k == n - 1)
/* 191 */       return n; 
/* 193 */     if (k > n / 2)
/* 194 */       return binomialCoefficientDouble(n, n - k); 
/* 196 */     if (n < 67)
/* 197 */       return binomialCoefficient(n, k); 
/* 200 */     double result = 1.0D;
/* 201 */     for (int i = 1; i <= k; i++)
/* 202 */       result *= (n - k + i) / i; 
/* 205 */     return FastMath.floor(result + 0.5D);
/*     */   }
/*     */   
/*     */   public static double binomialCoefficientLog(int n, int k) {
/* 228 */     checkBinomial(n, k);
/* 229 */     if (n == k || k == 0)
/* 230 */       return 0.0D; 
/* 232 */     if (k == 1 || k == n - 1)
/* 233 */       return FastMath.log(n); 
/* 240 */     if (n < 67)
/* 241 */       return FastMath.log(binomialCoefficient(n, k)); 
/* 248 */     if (n < 1030)
/* 249 */       return FastMath.log(binomialCoefficientDouble(n, k)); 
/* 252 */     if (k > n / 2)
/* 253 */       return binomialCoefficientLog(n, n - k); 
/* 259 */     double logSum = 0.0D;
/*     */     int i;
/* 262 */     for (i = n - k + 1; i <= n; i++)
/* 263 */       logSum += FastMath.log(i); 
/* 267 */     for (i = 2; i <= k; i++)
/* 268 */       logSum -= FastMath.log(i); 
/* 271 */     return logSum;
/*     */   }
/*     */   
/*     */   public static long factorial(int n) {
/* 299 */     if (n < 0)
/* 300 */       throw new NotPositiveException(LocalizedFormats.FACTORIAL_NEGATIVE_PARAMETER, Integer.valueOf(n)); 
/* 303 */     if (n > 20)
/* 304 */       throw new MathArithmeticException(); 
/* 306 */     return FACTORIALS[n];
/*     */   }
/*     */   
/*     */   public static double factorialDouble(int n) {
/* 323 */     if (n < 0)
/* 324 */       throw new NotPositiveException(LocalizedFormats.FACTORIAL_NEGATIVE_PARAMETER, Integer.valueOf(n)); 
/* 327 */     if (n < 21)
/* 328 */       return factorial(n); 
/* 330 */     return FastMath.floor(FastMath.exp(factorialLog(n)) + 0.5D);
/*     */   }
/*     */   
/*     */   public static double factorialLog(int n) {
/* 341 */     if (n < 0)
/* 342 */       throw new NotPositiveException(LocalizedFormats.FACTORIAL_NEGATIVE_PARAMETER, Integer.valueOf(n)); 
/* 345 */     if (n < 21)
/* 346 */       return FastMath.log(factorial(n)); 
/* 348 */     double logSum = 0.0D;
/* 349 */     for (int i = 2; i <= n; i++)
/* 350 */       logSum += FastMath.log(i); 
/* 352 */     return logSum;
/*     */   }
/*     */   
/*     */   public static int gcd(int p, int q) {
/* 385 */     int u = p;
/* 386 */     int v = q;
/* 387 */     if (u == 0 || v == 0) {
/* 388 */       if (u == Integer.MIN_VALUE || v == Integer.MIN_VALUE)
/* 389 */         throw new MathArithmeticException(LocalizedFormats.GCD_OVERFLOW_32_BITS, new Object[] { Integer.valueOf(p), Integer.valueOf(q) }); 
/* 392 */       return FastMath.abs(u) + FastMath.abs(v);
/*     */     } 
/* 399 */     if (u > 0)
/* 400 */       u = -u; 
/* 402 */     if (v > 0)
/* 403 */       v = -v; 
/* 406 */     int k = 0;
/* 407 */     while ((u & 0x1) == 0 && (v & 0x1) == 0 && k < 31) {
/* 409 */       u /= 2;
/* 410 */       v /= 2;
/* 411 */       k++;
/*     */     } 
/* 413 */     if (k == 31)
/* 414 */       throw new MathArithmeticException(LocalizedFormats.GCD_OVERFLOW_32_BITS, new Object[] { Integer.valueOf(p), Integer.valueOf(q) }); 
/* 419 */     int t = ((u & 0x1) == 1) ? v : -(u / 2);
/*     */     while (true) {
/* 425 */       while ((t & 0x1) == 0)
/* 426 */         t /= 2; 
/* 429 */       if (t > 0) {
/* 430 */         u = -t;
/*     */       } else {
/* 432 */         v = t;
/*     */       } 
/* 435 */       t = (v - u) / 2;
/* 438 */       if (t == 0)
/* 439 */         return -u * (1 << k); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static long gcd(long p, long q) {
/* 472 */     long u = p;
/* 473 */     long v = q;
/* 474 */     if (u == 0L || v == 0L) {
/* 475 */       if (u == Long.MIN_VALUE || v == Long.MIN_VALUE)
/* 476 */         throw new MathArithmeticException(LocalizedFormats.GCD_OVERFLOW_64_BITS, new Object[] { Long.valueOf(p), Long.valueOf(q) }); 
/* 479 */       return FastMath.abs(u) + FastMath.abs(v);
/*     */     } 
/* 486 */     if (u > 0L)
/* 487 */       u = -u; 
/* 489 */     if (v > 0L)
/* 490 */       v = -v; 
/* 493 */     int k = 0;
/* 494 */     while ((u & 0x1L) == 0L && (v & 0x1L) == 0L && k < 63) {
/* 496 */       u /= 2L;
/* 497 */       v /= 2L;
/* 498 */       k++;
/*     */     } 
/* 500 */     if (k == 63)
/* 501 */       throw new MathArithmeticException(LocalizedFormats.GCD_OVERFLOW_64_BITS, new Object[] { Long.valueOf(p), Long.valueOf(q) }); 
/* 506 */     long t = ((u & 0x1L) == 1L) ? v : -(u / 2L);
/*     */     while (true) {
/* 512 */       while ((t & 0x1L) == 0L)
/* 513 */         t /= 2L; 
/* 516 */       if (t > 0L) {
/* 517 */         u = -t;
/*     */       } else {
/* 519 */         v = t;
/*     */       } 
/* 522 */       t = (v - u) / 2L;
/* 525 */       if (t == 0L)
/* 526 */         return -u * (1L << k); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static int lcm(int a, int b) {
/* 552 */     if (a == 0 || b == 0)
/* 553 */       return 0; 
/* 555 */     int lcm = FastMath.abs(mulAndCheck(a / gcd(a, b), b));
/* 556 */     if (lcm == Integer.MIN_VALUE)
/* 557 */       throw new MathArithmeticException(LocalizedFormats.LCM_OVERFLOW_32_BITS, new Object[] { Integer.valueOf(a), Integer.valueOf(b) }); 
/* 560 */     return lcm;
/*     */   }
/*     */   
/*     */   public static long lcm(long a, long b) {
/* 586 */     if (a == 0L || b == 0L)
/* 587 */       return 0L; 
/* 589 */     long lcm = FastMath.abs(mulAndCheck(a / gcd(a, b), b));
/* 590 */     if (lcm == Long.MIN_VALUE)
/* 591 */       throw new MathArithmeticException(LocalizedFormats.LCM_OVERFLOW_64_BITS, new Object[] { Long.valueOf(a), Long.valueOf(b) }); 
/* 594 */     return lcm;
/*     */   }
/*     */   
/*     */   public static int mulAndCheck(int x, int y) {
/* 608 */     long m = x * y;
/* 609 */     if (m < -2147483648L || m > 2147483647L)
/* 610 */       throw new MathArithmeticException(); 
/* 612 */     return (int)m;
/*     */   }
/*     */   
/*     */   public static long mulAndCheck(long a, long b) {
/*     */     long ret;
/* 627 */     if (a > b) {
/* 629 */       ret = mulAndCheck(b, a);
/* 631 */     } else if (a < 0L) {
/* 632 */       if (b < 0L) {
/* 634 */         if (a >= Long.MAX_VALUE / b) {
/* 635 */           ret = a * b;
/*     */         } else {
/* 637 */           throw new MathArithmeticException();
/*     */         } 
/* 639 */       } else if (b > 0L) {
/* 641 */         if (Long.MIN_VALUE / b <= a) {
/* 642 */           ret = a * b;
/*     */         } else {
/* 644 */           throw new MathArithmeticException();
/*     */         } 
/*     */       } else {
/* 649 */         ret = 0L;
/*     */       } 
/* 651 */     } else if (a > 0L) {
/* 656 */       if (a <= Long.MAX_VALUE / b) {
/* 657 */         ret = a * b;
/*     */       } else {
/* 659 */         throw new MathArithmeticException();
/*     */       } 
/*     */     } else {
/* 663 */       ret = 0L;
/*     */     } 
/* 666 */     return ret;
/*     */   }
/*     */   
/*     */   public static int subAndCheck(int x, int y) {
/* 680 */     long s = x - y;
/* 681 */     if (s < -2147483648L || s > 2147483647L)
/* 682 */       throw new MathArithmeticException(LocalizedFormats.OVERFLOW_IN_SUBTRACTION, new Object[] { Integer.valueOf(x), Integer.valueOf(y) }); 
/* 684 */     return (int)s;
/*     */   }
/*     */   
/*     */   public static long subAndCheck(long a, long b) {
/*     */     long ret;
/* 699 */     if (b == Long.MIN_VALUE) {
/* 700 */       if (a < 0L) {
/* 701 */         ret = a - b;
/*     */       } else {
/* 703 */         throw new MathArithmeticException(LocalizedFormats.OVERFLOW_IN_ADDITION, new Object[] { Long.valueOf(a), Long.valueOf(-b) });
/*     */       } 
/*     */     } else {
/* 707 */       ret = addAndCheck(a, -b, (Localizable)LocalizedFormats.OVERFLOW_IN_ADDITION);
/*     */     } 
/* 709 */     return ret;
/*     */   }
/*     */   
/*     */   public static int pow(int k, int e) {
/* 721 */     if (e < 0)
/* 722 */       throw new NotPositiveException(LocalizedFormats.EXPONENT, Integer.valueOf(e)); 
/* 725 */     int result = 1;
/* 726 */     int k2p = k;
/* 727 */     while (e != 0) {
/* 728 */       if ((e & 0x1) != 0)
/* 729 */         result *= k2p; 
/* 731 */       k2p *= k2p;
/* 732 */       e >>= 1;
/*     */     } 
/* 735 */     return result;
/*     */   }
/*     */   
/*     */   public static int pow(int k, long e) {
/* 747 */     if (e < 0L)
/* 748 */       throw new NotPositiveException(LocalizedFormats.EXPONENT, Long.valueOf(e)); 
/* 751 */     int result = 1;
/* 752 */     int k2p = k;
/* 753 */     while (e != 0L) {
/* 754 */       if ((e & 0x1L) != 0L)
/* 755 */         result *= k2p; 
/* 757 */       k2p *= k2p;
/* 758 */       e >>= 1L;
/*     */     } 
/* 761 */     return result;
/*     */   }
/*     */   
/*     */   public static long pow(long k, int e) {
/* 773 */     if (e < 0)
/* 774 */       throw new NotPositiveException(LocalizedFormats.EXPONENT, Integer.valueOf(e)); 
/* 777 */     long result = 1L;
/* 778 */     long k2p = k;
/* 779 */     while (e != 0) {
/* 780 */       if ((e & 0x1) != 0)
/* 781 */         result *= k2p; 
/* 783 */       k2p *= k2p;
/* 784 */       e >>= 1;
/*     */     } 
/* 787 */     return result;
/*     */   }
/*     */   
/*     */   public static long pow(long k, long e) {
/* 799 */     if (e < 0L)
/* 800 */       throw new NotPositiveException(LocalizedFormats.EXPONENT, Long.valueOf(e)); 
/* 803 */     long result = 1L;
/* 804 */     long k2p = k;
/* 805 */     while (e != 0L) {
/* 806 */       if ((e & 0x1L) != 0L)
/* 807 */         result *= k2p; 
/* 809 */       k2p *= k2p;
/* 810 */       e >>= 1L;
/*     */     } 
/* 813 */     return result;
/*     */   }
/*     */   
/*     */   public static BigInteger pow(BigInteger k, int e) {
/* 825 */     if (e < 0)
/* 826 */       throw new NotPositiveException(LocalizedFormats.EXPONENT, Integer.valueOf(e)); 
/* 829 */     return k.pow(e);
/*     */   }
/*     */   
/*     */   public static BigInteger pow(BigInteger k, long e) {
/* 841 */     if (e < 0L)
/* 842 */       throw new NotPositiveException(LocalizedFormats.EXPONENT, Long.valueOf(e)); 
/* 845 */     BigInteger result = BigInteger.ONE;
/* 846 */     BigInteger k2p = k;
/* 847 */     while (e != 0L) {
/* 848 */       if ((e & 0x1L) != 0L)
/* 849 */         result = result.multiply(k2p); 
/* 851 */       k2p = k2p.multiply(k2p);
/* 852 */       e >>= 1L;
/*     */     } 
/* 855 */     return result;
/*     */   }
/*     */   
/*     */   public static BigInteger pow(BigInteger k, BigInteger e) {
/* 868 */     if (e.compareTo(BigInteger.ZERO) < 0)
/* 869 */       throw new NotPositiveException(LocalizedFormats.EXPONENT, e); 
/* 872 */     BigInteger result = BigInteger.ONE;
/* 873 */     BigInteger k2p = k;
/* 874 */     while (!BigInteger.ZERO.equals(e)) {
/* 875 */       if (e.testBit(0))
/* 876 */         result = result.multiply(k2p); 
/* 878 */       k2p = k2p.multiply(k2p);
/* 879 */       e = e.shiftRight(1);
/*     */     } 
/* 882 */     return result;
/*     */   }
/*     */   
/*     */   private static long addAndCheck(long a, long b, Localizable pattern) {
/*     */     long ret;
/* 898 */     if (a > b) {
/* 900 */       ret = addAndCheck(b, a, pattern);
/* 904 */     } else if (a < 0L) {
/* 905 */       if (b < 0L) {
/* 907 */         if (Long.MIN_VALUE - b <= a) {
/* 908 */           ret = a + b;
/*     */         } else {
/* 910 */           throw new MathArithmeticException(pattern, new Object[] { Long.valueOf(a), Long.valueOf(b) });
/*     */         } 
/*     */       } else {
/* 914 */         ret = a + b;
/*     */       } 
/* 921 */     } else if (a <= Long.MAX_VALUE - b) {
/* 922 */       ret = a + b;
/*     */     } else {
/* 924 */       throw new MathArithmeticException(pattern, new Object[] { Long.valueOf(a), Long.valueOf(b) });
/*     */     } 
/* 928 */     return ret;
/*     */   }
/*     */   
/*     */   private static void checkBinomial(int n, int k) {
/* 940 */     if (n < k)
/* 941 */       throw new NumberIsTooLargeException(LocalizedFormats.BINOMIAL_INVALID_PARAMETERS_ORDER, Integer.valueOf(k), Integer.valueOf(n), true); 
/* 944 */     if (n < 0)
/* 945 */       throw new NotPositiveException(LocalizedFormats.BINOMIAL_NEGATIVE_PARAMETER, Integer.valueOf(n)); 
/*     */   }
/*     */   
/*     */   public static boolean isPowerOfTwo(long n) {
/* 956 */     return (n > 0L && (n & n - 1L) == 0L);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math\\util\ArithmeticUtils.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */