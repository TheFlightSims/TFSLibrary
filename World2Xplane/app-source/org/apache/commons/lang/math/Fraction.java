/*     */ package org.apache.commons.lang.math;
/*     */ 
/*     */ import java.math.BigInteger;
/*     */ import org.apache.commons.lang.text.StrBuilder;
/*     */ 
/*     */ public final class Fraction extends Number implements Comparable {
/*     */   private static final long serialVersionUID = 65382027393090L;
/*     */   
/*  50 */   public static final Fraction ZERO = new Fraction(0, 1);
/*     */   
/*  54 */   public static final Fraction ONE = new Fraction(1, 1);
/*     */   
/*  58 */   public static final Fraction ONE_HALF = new Fraction(1, 2);
/*     */   
/*  62 */   public static final Fraction ONE_THIRD = new Fraction(1, 3);
/*     */   
/*  66 */   public static final Fraction TWO_THIRDS = new Fraction(2, 3);
/*     */   
/*  70 */   public static final Fraction ONE_QUARTER = new Fraction(1, 4);
/*     */   
/*  74 */   public static final Fraction TWO_QUARTERS = new Fraction(2, 4);
/*     */   
/*  78 */   public static final Fraction THREE_QUARTERS = new Fraction(3, 4);
/*     */   
/*  82 */   public static final Fraction ONE_FIFTH = new Fraction(1, 5);
/*     */   
/*  86 */   public static final Fraction TWO_FIFTHS = new Fraction(2, 5);
/*     */   
/*  90 */   public static final Fraction THREE_FIFTHS = new Fraction(3, 5);
/*     */   
/*  94 */   public static final Fraction FOUR_FIFTHS = new Fraction(4, 5);
/*     */   
/*     */   private final int numerator;
/*     */   
/*     */   private final int denominator;
/*     */   
/* 109 */   private transient int hashCode = 0;
/*     */   
/* 113 */   private transient String toString = null;
/*     */   
/* 117 */   private transient String toProperString = null;
/*     */   
/*     */   private Fraction(int numerator, int denominator) {
/* 128 */     this.numerator = numerator;
/* 129 */     this.denominator = denominator;
/*     */   }
/*     */   
/*     */   public static Fraction getFraction(int numerator, int denominator) {
/* 144 */     if (denominator == 0)
/* 145 */       throw new ArithmeticException("The denominator must not be zero"); 
/* 147 */     if (denominator < 0) {
/* 148 */       if (numerator == Integer.MIN_VALUE || denominator == Integer.MIN_VALUE)
/* 150 */         throw new ArithmeticException("overflow: can't negate"); 
/* 152 */       numerator = -numerator;
/* 153 */       denominator = -denominator;
/*     */     } 
/* 155 */     return new Fraction(numerator, denominator);
/*     */   }
/*     */   
/*     */   public static Fraction getFraction(int whole, int numerator, int denominator) {
/*     */     long numeratorValue;
/* 175 */     if (denominator == 0)
/* 176 */       throw new ArithmeticException("The denominator must not be zero"); 
/* 178 */     if (denominator < 0)
/* 179 */       throw new ArithmeticException("The denominator must not be negative"); 
/* 181 */     if (numerator < 0)
/* 182 */       throw new ArithmeticException("The numerator must not be negative"); 
/* 185 */     if (whole < 0) {
/* 186 */       numeratorValue = whole * denominator - numerator;
/*     */     } else {
/* 188 */       numeratorValue = whole * denominator + numerator;
/*     */     } 
/* 190 */     if (numeratorValue < -2147483648L || numeratorValue > 2147483647L)
/* 192 */       throw new ArithmeticException("Numerator too large to represent as an Integer."); 
/* 194 */     return new Fraction((int)numeratorValue, denominator);
/*     */   }
/*     */   
/*     */   public static Fraction getReducedFraction(int numerator, int denominator) {
/* 212 */     if (denominator == 0)
/* 213 */       throw new ArithmeticException("The denominator must not be zero"); 
/* 215 */     if (numerator == 0)
/* 216 */       return ZERO; 
/* 219 */     if (denominator == Integer.MIN_VALUE && (numerator & 0x1) == 0) {
/* 220 */       numerator /= 2;
/* 220 */       denominator /= 2;
/*     */     } 
/* 222 */     if (denominator < 0) {
/* 223 */       if (numerator == Integer.MIN_VALUE || denominator == Integer.MIN_VALUE)
/* 225 */         throw new ArithmeticException("overflow: can't negate"); 
/* 227 */       numerator = -numerator;
/* 228 */       denominator = -denominator;
/*     */     } 
/* 231 */     int gcd = greatestCommonDivisor(numerator, denominator);
/* 232 */     numerator /= gcd;
/* 233 */     denominator /= gcd;
/* 234 */     return new Fraction(numerator, denominator);
/*     */   }
/*     */   
/*     */   public static Fraction getFraction(double value) {
/*     */     double delta1;
/* 252 */     int sign = (value < 0.0D) ? -1 : 1;
/* 253 */     value = Math.abs(value);
/* 254 */     if (value > 2.147483647E9D || Double.isNaN(value))
/* 255 */       throw new ArithmeticException("The value must not be greater than Integer.MAX_VALUE or NaN"); 
/* 258 */     int wholeNumber = (int)value;
/* 259 */     value -= wholeNumber;
/* 261 */     int numer0 = 0;
/* 262 */     int denom0 = 1;
/* 263 */     int numer1 = 1;
/* 264 */     int denom1 = 0;
/* 265 */     int numer2 = 0;
/* 266 */     int denom2 = 0;
/* 267 */     int a1 = (int)value;
/* 268 */     int a2 = 0;
/* 269 */     double x1 = 1.0D;
/* 270 */     double x2 = 0.0D;
/* 271 */     double y1 = value - a1;
/* 272 */     double y2 = 0.0D;
/* 273 */     double delta2 = Double.MAX_VALUE;
/* 275 */     int i = 1;
/*     */     do {
/* 278 */       delta1 = delta2;
/* 279 */       a2 = (int)(x1 / y1);
/* 280 */       x2 = y1;
/* 281 */       y2 = x1 - a2 * y1;
/* 282 */       numer2 = a1 * numer1 + numer0;
/* 283 */       denom2 = a1 * denom1 + denom0;
/* 284 */       double fraction = numer2 / denom2;
/* 285 */       delta2 = Math.abs(value - fraction);
/* 287 */       a1 = a2;
/* 288 */       x1 = x2;
/* 289 */       y1 = y2;
/* 290 */       numer0 = numer1;
/* 291 */       denom0 = denom1;
/* 292 */       numer1 = numer2;
/* 293 */       denom1 = denom2;
/* 294 */       i++;
/* 296 */     } while (delta1 > delta2 && denom2 <= 10000 && denom2 > 0 && i < 25);
/* 297 */     if (i == 25)
/* 298 */       throw new ArithmeticException("Unable to convert double to fraction"); 
/* 300 */     return getReducedFraction((numer0 + wholeNumber * denom0) * sign, denom0);
/*     */   }
/*     */   
/*     */   public static Fraction getFraction(String str) {
/* 322 */     if (str == null)
/* 323 */       throw new IllegalArgumentException("The string must not be null"); 
/* 326 */     int pos = str.indexOf('.');
/* 327 */     if (pos >= 0)
/* 328 */       return getFraction(Double.parseDouble(str)); 
/* 332 */     pos = str.indexOf(' ');
/* 333 */     if (pos > 0) {
/* 334 */       int whole = Integer.parseInt(str.substring(0, pos));
/* 335 */       str = str.substring(pos + 1);
/* 336 */       pos = str.indexOf('/');
/* 337 */       if (pos < 0)
/* 338 */         throw new NumberFormatException("The fraction could not be parsed as the format X Y/Z"); 
/* 340 */       int i = Integer.parseInt(str.substring(0, pos));
/* 341 */       int j = Integer.parseInt(str.substring(pos + 1));
/* 342 */       return getFraction(whole, i, j);
/*     */     } 
/* 347 */     pos = str.indexOf('/');
/* 348 */     if (pos < 0)
/* 350 */       return getFraction(Integer.parseInt(str), 1); 
/* 352 */     int numer = Integer.parseInt(str.substring(0, pos));
/* 353 */     int denom = Integer.parseInt(str.substring(pos + 1));
/* 354 */     return getFraction(numer, denom);
/*     */   }
/*     */   
/*     */   public int getNumerator() {
/* 370 */     return this.numerator;
/*     */   }
/*     */   
/*     */   public int getDenominator() {
/* 379 */     return this.denominator;
/*     */   }
/*     */   
/*     */   public int getProperNumerator() {
/* 394 */     return Math.abs(this.numerator % this.denominator);
/*     */   }
/*     */   
/*     */   public int getProperWhole() {
/* 409 */     return this.numerator / this.denominator;
/*     */   }
/*     */   
/*     */   public int intValue() {
/* 422 */     return this.numerator / this.denominator;
/*     */   }
/*     */   
/*     */   public long longValue() {
/* 432 */     return this.numerator / this.denominator;
/*     */   }
/*     */   
/*     */   public float floatValue() {
/* 442 */     return this.numerator / this.denominator;
/*     */   }
/*     */   
/*     */   public double doubleValue() {
/* 452 */     return this.numerator / this.denominator;
/*     */   }
/*     */   
/*     */   public Fraction reduce() {
/* 468 */     if (this.numerator == 0)
/* 469 */       return equals(ZERO) ? this : ZERO; 
/* 471 */     int gcd = greatestCommonDivisor(Math.abs(this.numerator), this.denominator);
/* 472 */     if (gcd == 1)
/* 473 */       return this; 
/* 475 */     return getFraction(this.numerator / gcd, this.denominator / gcd);
/*     */   }
/*     */   
/*     */   public Fraction invert() {
/* 488 */     if (this.numerator == 0)
/* 489 */       throw new ArithmeticException("Unable to invert zero."); 
/* 491 */     if (this.numerator == Integer.MIN_VALUE)
/* 492 */       throw new ArithmeticException("overflow: can't negate numerator"); 
/* 494 */     if (this.numerator < 0)
/* 495 */       return new Fraction(-this.denominator, -this.numerator); 
/* 497 */     return new Fraction(this.denominator, this.numerator);
/*     */   }
/*     */   
/*     */   public Fraction negate() {
/* 510 */     if (this.numerator == Integer.MIN_VALUE)
/* 511 */       throw new ArithmeticException("overflow: too large to negate"); 
/* 513 */     return new Fraction(-this.numerator, this.denominator);
/*     */   }
/*     */   
/*     */   public Fraction abs() {
/* 526 */     if (this.numerator >= 0)
/* 527 */       return this; 
/* 529 */     return negate();
/*     */   }
/*     */   
/*     */   public Fraction pow(int power) {
/* 545 */     if (power == 1)
/* 546 */       return this; 
/* 547 */     if (power == 0)
/* 548 */       return ONE; 
/* 549 */     if (power < 0) {
/* 550 */       if (power == Integer.MIN_VALUE)
/* 551 */         return invert().pow(2).pow(-(power / 2)); 
/* 553 */       return invert().pow(-power);
/*     */     } 
/* 555 */     Fraction f = multiplyBy(this);
/* 556 */     if (power % 2 == 0)
/* 557 */       return f.pow(power / 2); 
/* 559 */     return f.pow(power / 2).multiplyBy(this);
/*     */   }
/*     */   
/*     */   private static int greatestCommonDivisor(int u, int v) {
/* 576 */     if (Math.abs(u) <= 1 || Math.abs(v) <= 1)
/* 577 */       return 1; 
/* 583 */     if (u > 0)
/* 583 */       u = -u; 
/* 584 */     if (v > 0)
/* 584 */       v = -v; 
/* 586 */     int k = 0;
/* 587 */     while ((u & 0x1) == 0 && (v & 0x1) == 0 && k < 31) {
/* 588 */       u /= 2;
/* 588 */       v /= 2;
/* 588 */       k++;
/*     */     } 
/* 590 */     if (k == 31)
/* 591 */       throw new ArithmeticException("overflow: gcd is 2^31"); 
/* 595 */     int t = ((u & 0x1) == 1) ? v : -(u / 2);
/*     */     while (true) {
/* 601 */       while ((t & 0x1) == 0)
/* 602 */         t /= 2; 
/* 605 */       if (t > 0) {
/* 606 */         u = -t;
/*     */       } else {
/* 608 */         v = t;
/*     */       } 
/* 611 */       t = (v - u) / 2;
/* 614 */       if (t == 0)
/* 615 */         return -u * (1 << k); 
/*     */     } 
/*     */   }
/*     */   
/*     */   private static int mulAndCheck(int x, int y) {
/* 631 */     long m = x * y;
/* 632 */     if (m < -2147483648L || m > 2147483647L)
/* 634 */       throw new ArithmeticException("overflow: mul"); 
/* 636 */     return (int)m;
/*     */   }
/*     */   
/*     */   private static int mulPosAndCheck(int x, int y) {
/* 650 */     long m = x * y;
/* 651 */     if (m > 2147483647L)
/* 652 */       throw new ArithmeticException("overflow: mulPos"); 
/* 654 */     return (int)m;
/*     */   }
/*     */   
/*     */   private static int addAndCheck(int x, int y) {
/* 667 */     long s = x + y;
/* 668 */     if (s < -2147483648L || s > 2147483647L)
/* 670 */       throw new ArithmeticException("overflow: add"); 
/* 672 */     return (int)s;
/*     */   }
/*     */   
/*     */   private static int subAndCheck(int x, int y) {
/* 685 */     long s = x - y;
/* 686 */     if (s < -2147483648L || s > 2147483647L)
/* 688 */       throw new ArithmeticException("overflow: add"); 
/* 690 */     return (int)s;
/*     */   }
/*     */   
/*     */   public Fraction add(Fraction fraction) {
/* 704 */     return addSub(fraction, true);
/*     */   }
/*     */   
/*     */   public Fraction subtract(Fraction fraction) {
/* 718 */     return addSub(fraction, false);
/*     */   }
/*     */   
/*     */   private Fraction addSub(Fraction fraction, boolean isAdd) {
/* 732 */     if (fraction == null)
/* 733 */       throw new IllegalArgumentException("The fraction must not be null"); 
/* 736 */     if (this.numerator == 0)
/* 737 */       return isAdd ? fraction : fraction.negate(); 
/* 739 */     if (fraction.numerator == 0)
/* 740 */       return this; 
/* 744 */     int d1 = greatestCommonDivisor(this.denominator, fraction.denominator);
/* 745 */     if (d1 == 1) {
/* 747 */       int i = mulAndCheck(this.numerator, fraction.denominator);
/* 748 */       int j = mulAndCheck(fraction.numerator, this.denominator);
/* 749 */       return new Fraction(isAdd ? addAndCheck(i, j) : subAndCheck(i, j), mulPosAndCheck(this.denominator, fraction.denominator));
/*     */     } 
/* 756 */     BigInteger uvp = BigInteger.valueOf(this.numerator).multiply(BigInteger.valueOf((fraction.denominator / d1)));
/* 758 */     BigInteger upv = BigInteger.valueOf(fraction.numerator).multiply(BigInteger.valueOf((this.denominator / d1)));
/* 760 */     BigInteger t = isAdd ? uvp.add(upv) : uvp.subtract(upv);
/* 763 */     int tmodd1 = t.mod(BigInteger.valueOf(d1)).intValue();
/* 764 */     int d2 = (tmodd1 == 0) ? d1 : greatestCommonDivisor(tmodd1, d1);
/* 767 */     BigInteger w = t.divide(BigInteger.valueOf(d2));
/* 768 */     if (w.bitLength() > 31)
/* 769 */       throw new ArithmeticException("overflow: numerator too large after multiply"); 
/* 772 */     return new Fraction(w.intValue(), mulPosAndCheck(this.denominator / d1, fraction.denominator / d2));
/*     */   }
/*     */   
/*     */   public Fraction multiplyBy(Fraction fraction) {
/* 788 */     if (fraction == null)
/* 789 */       throw new IllegalArgumentException("The fraction must not be null"); 
/* 791 */     if (this.numerator == 0 || fraction.numerator == 0)
/* 792 */       return ZERO; 
/* 796 */     int d1 = greatestCommonDivisor(this.numerator, fraction.denominator);
/* 797 */     int d2 = greatestCommonDivisor(fraction.numerator, this.denominator);
/* 798 */     return getReducedFraction(mulAndCheck(this.numerator / d1, fraction.numerator / d2), mulPosAndCheck(this.denominator / d2, fraction.denominator / d1));
/*     */   }
/*     */   
/*     */   public Fraction divideBy(Fraction fraction) {
/* 814 */     if (fraction == null)
/* 815 */       throw new IllegalArgumentException("The fraction must not be null"); 
/* 817 */     if (fraction.numerator == 0)
/* 818 */       throw new ArithmeticException("The fraction to divide by must not be zero"); 
/* 820 */     return multiplyBy(fraction.invert());
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 835 */     if (obj == this)
/* 836 */       return true; 
/* 838 */     if (!(obj instanceof Fraction))
/* 839 */       return false; 
/* 841 */     Fraction other = (Fraction)obj;
/* 842 */     return (getNumerator() == other.getNumerator() && getDenominator() == other.getDenominator());
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 852 */     if (this.hashCode == 0)
/* 854 */       this.hashCode = 37 * (629 + getNumerator()) + getDenominator(); 
/* 856 */     return this.hashCode;
/*     */   }
/*     */   
/*     */   public int compareTo(Object object) {
/* 872 */     Fraction other = (Fraction)object;
/* 873 */     if (this == other)
/* 874 */       return 0; 
/* 876 */     if (this.numerator == other.numerator && this.denominator == other.denominator)
/* 877 */       return 0; 
/* 881 */     long first = this.numerator * other.denominator;
/* 882 */     long second = other.numerator * this.denominator;
/* 883 */     if (first == second)
/* 884 */       return 0; 
/* 885 */     if (first < second)
/* 886 */       return -1; 
/* 888 */     return 1;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 900 */     if (this.toString == null)
/* 901 */       this.toString = (new StrBuilder(32)).append(getNumerator()).append('/').append(getDenominator()).toString(); 
/* 906 */     return this.toString;
/*     */   }
/*     */   
/*     */   public String toProperString() {
/* 919 */     if (this.toProperString == null)
/* 920 */       if (this.numerator == 0) {
/* 921 */         this.toProperString = "0";
/* 922 */       } else if (this.numerator == this.denominator) {
/* 923 */         this.toProperString = "1";
/* 924 */       } else if (this.numerator == -1 * this.denominator) {
/* 925 */         this.toProperString = "-1";
/* 926 */       } else if (((this.numerator > 0) ? -this.numerator : this.numerator) < -this.denominator) {
/* 931 */         int properNumerator = getProperNumerator();
/* 932 */         if (properNumerator == 0) {
/* 933 */           this.toProperString = Integer.toString(getProperWhole());
/*     */         } else {
/* 935 */           this.toProperString = (new StrBuilder(32)).append(getProperWhole()).append(' ').append(properNumerator).append('/').append(getDenominator()).toString();
/*     */         } 
/*     */       } else {
/* 941 */         this.toProperString = (new StrBuilder(32)).append(getNumerator()).append('/').append(getDenominator()).toString();
/*     */       }  
/* 946 */     return this.toProperString;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\lang\math\Fraction.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */