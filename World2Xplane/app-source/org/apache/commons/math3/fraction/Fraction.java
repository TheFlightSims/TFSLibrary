/*     */ package org.apache.commons.math3.fraction;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.math.BigInteger;
/*     */ import org.apache.commons.math3.Field;
/*     */ import org.apache.commons.math3.FieldElement;
/*     */ import org.apache.commons.math3.exception.MathArithmeticException;
/*     */ import org.apache.commons.math3.exception.NullArgumentException;
/*     */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*     */ import org.apache.commons.math3.util.ArithmeticUtils;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ 
/*     */ public class Fraction extends Number implements FieldElement<Fraction>, Comparable<Fraction>, Serializable {
/*  42 */   public static final Fraction TWO = new Fraction(2, 1);
/*     */   
/*  45 */   public static final Fraction ONE = new Fraction(1, 1);
/*     */   
/*  48 */   public static final Fraction ZERO = new Fraction(0, 1);
/*     */   
/*  51 */   public static final Fraction FOUR_FIFTHS = new Fraction(4, 5);
/*     */   
/*  54 */   public static final Fraction ONE_FIFTH = new Fraction(1, 5);
/*     */   
/*  57 */   public static final Fraction ONE_HALF = new Fraction(1, 2);
/*     */   
/*  60 */   public static final Fraction ONE_QUARTER = new Fraction(1, 4);
/*     */   
/*  63 */   public static final Fraction ONE_THIRD = new Fraction(1, 3);
/*     */   
/*  66 */   public static final Fraction THREE_FIFTHS = new Fraction(3, 5);
/*     */   
/*  69 */   public static final Fraction THREE_QUARTERS = new Fraction(3, 4);
/*     */   
/*  72 */   public static final Fraction TWO_FIFTHS = new Fraction(2, 5);
/*     */   
/*  75 */   public static final Fraction TWO_QUARTERS = new Fraction(2, 4);
/*     */   
/*  78 */   public static final Fraction TWO_THIRDS = new Fraction(2, 3);
/*     */   
/*  81 */   public static final Fraction MINUS_ONE = new Fraction(-1, 1);
/*     */   
/*     */   private static final long serialVersionUID = 3698073679419233275L;
/*     */   
/*     */   private final int denominator;
/*     */   
/*     */   private final int numerator;
/*     */   
/*     */   public Fraction(double value) throws FractionConversionException {
/*  99 */     this(value, 1.0E-5D, 100);
/*     */   }
/*     */   
/*     */   public Fraction(double value, double epsilon, int maxIterations) throws FractionConversionException {
/* 121 */     this(value, epsilon, 2147483647, maxIterations);
/*     */   }
/*     */   
/*     */   public Fraction(double value, int maxDenominator) throws FractionConversionException {
/* 141 */     this(value, 0.0D, maxDenominator, 100);
/*     */   }
/*     */   
/*     */   private Fraction(double value, double epsilon, int maxDenominator, int maxIterations) throws FractionConversionException {
/* 178 */     long overflow = 2147483647L;
/* 179 */     double r0 = value;
/* 180 */     long a0 = (long)FastMath.floor(r0);
/* 181 */     if (a0 > overflow)
/* 182 */       throw new FractionConversionException(value, a0, 1L); 
/* 187 */     if (FastMath.abs(a0 - value) < epsilon) {
/* 188 */       this.numerator = (int)a0;
/* 189 */       this.denominator = 1;
/*     */       return;
/*     */     } 
/* 193 */     long p0 = 1L;
/* 194 */     long q0 = 0L;
/* 195 */     long p1 = a0;
/* 196 */     long q1 = 1L;
/* 198 */     long p2 = 0L;
/* 199 */     long q2 = 1L;
/* 201 */     int n = 0;
/* 202 */     boolean stop = false;
/*     */     do {
/* 204 */       n++;
/* 205 */       double r1 = 1.0D / (r0 - a0);
/* 206 */       long a1 = (long)FastMath.floor(r1);
/* 207 */       p2 = a1 * p1 + p0;
/* 208 */       q2 = a1 * q1 + q0;
/* 209 */       if (p2 > overflow || q2 > overflow)
/* 210 */         throw new FractionConversionException(value, p2, q2); 
/* 213 */       double convergent = p2 / q2;
/* 214 */       if (n < maxIterations && FastMath.abs(convergent - value) > epsilon && q2 < maxDenominator) {
/* 215 */         p0 = p1;
/* 216 */         p1 = p2;
/* 217 */         q0 = q1;
/* 218 */         q1 = q2;
/* 219 */         a0 = a1;
/* 220 */         r0 = r1;
/*     */       } else {
/* 222 */         stop = true;
/*     */       } 
/* 224 */     } while (!stop);
/* 226 */     if (n >= maxIterations)
/* 227 */       throw new FractionConversionException(value, maxIterations); 
/* 230 */     if (q2 < maxDenominator) {
/* 231 */       this.numerator = (int)p2;
/* 232 */       this.denominator = (int)q2;
/*     */     } else {
/* 234 */       this.numerator = (int)p1;
/* 235 */       this.denominator = (int)q1;
/*     */     } 
/*     */   }
/*     */   
/*     */   public Fraction(int num) {
/* 246 */     this(num, 1);
/*     */   }
/*     */   
/*     */   public Fraction(int num, int den) {
/* 257 */     if (den == 0)
/* 258 */       throw new MathArithmeticException(LocalizedFormats.ZERO_DENOMINATOR_IN_FRACTION, new Object[] { Integer.valueOf(num), Integer.valueOf(den) }); 
/* 261 */     if (den < 0) {
/* 262 */       if (num == Integer.MIN_VALUE || den == Integer.MIN_VALUE)
/* 264 */         throw new MathArithmeticException(LocalizedFormats.OVERFLOW_IN_FRACTION, new Object[] { Integer.valueOf(num), Integer.valueOf(den) }); 
/* 267 */       num = -num;
/* 268 */       den = -den;
/*     */     } 
/* 271 */     int d = ArithmeticUtils.gcd(num, den);
/* 272 */     if (d > 1) {
/* 273 */       num /= d;
/* 274 */       den /= d;
/*     */     } 
/* 278 */     if (den < 0) {
/* 279 */       num = -num;
/* 280 */       den = -den;
/*     */     } 
/* 282 */     this.numerator = num;
/* 283 */     this.denominator = den;
/*     */   }
/*     */   
/*     */   public Fraction abs() {
/*     */     Fraction ret;
/* 292 */     if (this.numerator >= 0) {
/* 293 */       ret = this;
/*     */     } else {
/* 295 */       ret = negate();
/*     */     } 
/* 297 */     return ret;
/*     */   }
/*     */   
/*     */   public int compareTo(Fraction object) {
/* 307 */     long nOd = this.numerator * object.denominator;
/* 308 */     long dOn = this.denominator * object.numerator;
/* 309 */     return (nOd < dOn) ? -1 : ((nOd > dOn) ? 1 : 0);
/*     */   }
/*     */   
/*     */   public double doubleValue() {
/* 319 */     return this.numerator / this.denominator;
/*     */   }
/*     */   
/*     */   public boolean equals(Object other) {
/* 333 */     if (this == other)
/* 334 */       return true; 
/* 336 */     if (other instanceof Fraction) {
/* 339 */       Fraction rhs = (Fraction)other;
/* 340 */       return (this.numerator == rhs.numerator && this.denominator == rhs.denominator);
/*     */     } 
/* 343 */     return false;
/*     */   }
/*     */   
/*     */   public float floatValue() {
/* 353 */     return (float)doubleValue();
/*     */   }
/*     */   
/*     */   public int getDenominator() {
/* 361 */     return this.denominator;
/*     */   }
/*     */   
/*     */   public int getNumerator() {
/* 369 */     return this.numerator;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 378 */     return 37 * (629 + this.numerator) + this.denominator;
/*     */   }
/*     */   
/*     */   public int intValue() {
/* 388 */     return (int)doubleValue();
/*     */   }
/*     */   
/*     */   public long longValue() {
/* 398 */     return (long)doubleValue();
/*     */   }
/*     */   
/*     */   public Fraction negate() {
/* 406 */     if (this.numerator == Integer.MIN_VALUE)
/* 407 */       throw new MathArithmeticException(LocalizedFormats.OVERFLOW_IN_FRACTION, new Object[] { Integer.valueOf(this.numerator), Integer.valueOf(this.denominator) }); 
/* 409 */     return new Fraction(-this.numerator, this.denominator);
/*     */   }
/*     */   
/*     */   public Fraction reciprocal() {
/* 417 */     return new Fraction(this.denominator, this.numerator);
/*     */   }
/*     */   
/*     */   public Fraction add(Fraction fraction) {
/* 431 */     return addSub(fraction, true);
/*     */   }
/*     */   
/*     */   public Fraction add(int i) {
/* 440 */     return new Fraction(this.numerator + i * this.denominator, this.denominator);
/*     */   }
/*     */   
/*     */   public Fraction subtract(Fraction fraction) {
/* 454 */     return addSub(fraction, false);
/*     */   }
/*     */   
/*     */   public Fraction subtract(int i) {
/* 463 */     return new Fraction(this.numerator - i * this.denominator, this.denominator);
/*     */   }
/*     */   
/*     */   private Fraction addSub(Fraction fraction, boolean isAdd) {
/* 477 */     if (fraction == null)
/* 478 */       throw new NullArgumentException(LocalizedFormats.FRACTION, new Object[0]); 
/* 481 */     if (this.numerator == 0)
/* 482 */       return isAdd ? fraction : fraction.negate(); 
/* 484 */     if (fraction.numerator == 0)
/* 485 */       return this; 
/* 489 */     int d1 = ArithmeticUtils.gcd(this.denominator, fraction.denominator);
/* 490 */     if (d1 == 1) {
/* 492 */       int i = ArithmeticUtils.mulAndCheck(this.numerator, fraction.denominator);
/* 493 */       int j = ArithmeticUtils.mulAndCheck(fraction.numerator, this.denominator);
/* 494 */       return new Fraction(isAdd ? ArithmeticUtils.addAndCheck(i, j) : ArithmeticUtils.subAndCheck(i, j), ArithmeticUtils.mulAndCheck(this.denominator, fraction.denominator));
/*     */     } 
/* 502 */     BigInteger uvp = BigInteger.valueOf(this.numerator).multiply(BigInteger.valueOf((fraction.denominator / d1)));
/* 504 */     BigInteger upv = BigInteger.valueOf(fraction.numerator).multiply(BigInteger.valueOf((this.denominator / d1)));
/* 506 */     BigInteger t = isAdd ? uvp.add(upv) : uvp.subtract(upv);
/* 509 */     int tmodd1 = t.mod(BigInteger.valueOf(d1)).intValue();
/* 510 */     int d2 = (tmodd1 == 0) ? d1 : ArithmeticUtils.gcd(tmodd1, d1);
/* 513 */     BigInteger w = t.divide(BigInteger.valueOf(d2));
/* 514 */     if (w.bitLength() > 31)
/* 515 */       throw new MathArithmeticException(LocalizedFormats.NUMERATOR_OVERFLOW_AFTER_MULTIPLY, new Object[] { w }); 
/* 518 */     return new Fraction(w.intValue(), ArithmeticUtils.mulAndCheck(this.denominator / d1, fraction.denominator / d2));
/*     */   }
/*     */   
/*     */   public Fraction multiply(Fraction fraction) {
/* 534 */     if (fraction == null)
/* 535 */       throw new NullArgumentException(LocalizedFormats.FRACTION, new Object[0]); 
/* 537 */     if (this.numerator == 0 || fraction.numerator == 0)
/* 538 */       return ZERO; 
/* 542 */     int d1 = ArithmeticUtils.gcd(this.numerator, fraction.denominator);
/* 543 */     int d2 = ArithmeticUtils.gcd(fraction.numerator, this.denominator);
/* 544 */     return getReducedFraction(ArithmeticUtils.mulAndCheck(this.numerator / d1, fraction.numerator / d2), ArithmeticUtils.mulAndCheck(this.denominator / d2, fraction.denominator / d1));
/*     */   }
/*     */   
/*     */   public Fraction multiply(int i) {
/* 555 */     return new Fraction(this.numerator * i, this.denominator);
/*     */   }
/*     */   
/*     */   public Fraction divide(Fraction fraction) {
/* 569 */     if (fraction == null)
/* 570 */       throw new NullArgumentException(LocalizedFormats.FRACTION, new Object[0]); 
/* 572 */     if (fraction.numerator == 0)
/* 573 */       throw new MathArithmeticException(LocalizedFormats.ZERO_FRACTION_TO_DIVIDE_BY, new Object[] { Integer.valueOf(fraction.numerator), Integer.valueOf(fraction.denominator) }); 
/* 576 */     return multiply(fraction.reciprocal());
/*     */   }
/*     */   
/*     */   public Fraction divide(int i) {
/* 585 */     return new Fraction(this.numerator, this.denominator * i);
/*     */   }
/*     */   
/*     */   public double percentageValue() {
/* 597 */     return multiply(100).doubleValue();
/*     */   }
/*     */   
/*     */   public static Fraction getReducedFraction(int numerator, int denominator) {
/* 612 */     if (denominator == 0)
/* 613 */       throw new MathArithmeticException(LocalizedFormats.ZERO_DENOMINATOR_IN_FRACTION, new Object[] { Integer.valueOf(numerator), Integer.valueOf(denominator) }); 
/* 616 */     if (numerator == 0)
/* 617 */       return ZERO; 
/* 620 */     if (denominator == Integer.MIN_VALUE && (numerator & 0x1) == 0) {
/* 621 */       numerator /= 2;
/* 621 */       denominator /= 2;
/*     */     } 
/* 623 */     if (denominator < 0) {
/* 624 */       if (numerator == Integer.MIN_VALUE || denominator == Integer.MIN_VALUE)
/* 626 */         throw new MathArithmeticException(LocalizedFormats.OVERFLOW_IN_FRACTION, new Object[] { Integer.valueOf(numerator), Integer.valueOf(denominator) }); 
/* 629 */       numerator = -numerator;
/* 630 */       denominator = -denominator;
/*     */     } 
/* 633 */     int gcd = ArithmeticUtils.gcd(numerator, denominator);
/* 634 */     numerator /= gcd;
/* 635 */     denominator /= gcd;
/* 636 */     return new Fraction(numerator, denominator);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 650 */     String str = null;
/* 651 */     if (this.denominator == 1) {
/* 652 */       str = Integer.toString(this.numerator);
/* 653 */     } else if (this.numerator == 0) {
/* 654 */       str = "0";
/*     */     } else {
/* 656 */       str = this.numerator + " / " + this.denominator;
/*     */     } 
/* 658 */     return str;
/*     */   }
/*     */   
/*     */   public FractionField getField() {
/* 663 */     return FractionField.getInstance();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\fraction\Fraction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */