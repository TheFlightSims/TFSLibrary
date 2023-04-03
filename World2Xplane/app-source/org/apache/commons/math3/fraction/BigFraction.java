/*      */ package org.apache.commons.math3.fraction;
/*      */ 
/*      */ import java.io.Serializable;
/*      */ import java.math.BigDecimal;
/*      */ import java.math.BigInteger;
/*      */ import org.apache.commons.math3.Field;
/*      */ import org.apache.commons.math3.FieldElement;
/*      */ import org.apache.commons.math3.exception.MathArithmeticException;
/*      */ import org.apache.commons.math3.exception.MathIllegalArgumentException;
/*      */ import org.apache.commons.math3.exception.NullArgumentException;
/*      */ import org.apache.commons.math3.exception.ZeroException;
/*      */ import org.apache.commons.math3.exception.util.Localizable;
/*      */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*      */ import org.apache.commons.math3.util.ArithmeticUtils;
/*      */ import org.apache.commons.math3.util.FastMath;
/*      */ import org.apache.commons.math3.util.MathUtils;
/*      */ 
/*      */ public class BigFraction extends Number implements FieldElement<BigFraction>, Comparable<BigFraction>, Serializable {
/*   45 */   public static final BigFraction TWO = new BigFraction(2);
/*      */   
/*   48 */   public static final BigFraction ONE = new BigFraction(1);
/*      */   
/*   51 */   public static final BigFraction ZERO = new BigFraction(0);
/*      */   
/*   54 */   public static final BigFraction MINUS_ONE = new BigFraction(-1);
/*      */   
/*   57 */   public static final BigFraction FOUR_FIFTHS = new BigFraction(4, 5);
/*      */   
/*   60 */   public static final BigFraction ONE_FIFTH = new BigFraction(1, 5);
/*      */   
/*   63 */   public static final BigFraction ONE_HALF = new BigFraction(1, 2);
/*      */   
/*   66 */   public static final BigFraction ONE_QUARTER = new BigFraction(1, 4);
/*      */   
/*   69 */   public static final BigFraction ONE_THIRD = new BigFraction(1, 3);
/*      */   
/*   72 */   public static final BigFraction THREE_FIFTHS = new BigFraction(3, 5);
/*      */   
/*   75 */   public static final BigFraction THREE_QUARTERS = new BigFraction(3, 4);
/*      */   
/*   78 */   public static final BigFraction TWO_FIFTHS = new BigFraction(2, 5);
/*      */   
/*   81 */   public static final BigFraction TWO_QUARTERS = new BigFraction(2, 4);
/*      */   
/*   84 */   public static final BigFraction TWO_THIRDS = new BigFraction(2, 3);
/*      */   
/*      */   private static final long serialVersionUID = -5630213147331578515L;
/*      */   
/*   90 */   private static final BigInteger ONE_HUNDRED = BigInteger.valueOf(100L);
/*      */   
/*      */   private final BigInteger numerator;
/*      */   
/*      */   private final BigInteger denominator;
/*      */   
/*      */   public BigFraction(BigInteger num) {
/*  108 */     this(num, BigInteger.ONE);
/*      */   }
/*      */   
/*      */   public BigFraction(BigInteger num, BigInteger den) {
/*  121 */     MathUtils.checkNotNull(num, (Localizable)LocalizedFormats.NUMERATOR, new Object[0]);
/*  122 */     MathUtils.checkNotNull(den, (Localizable)LocalizedFormats.DENOMINATOR, new Object[0]);
/*  123 */     if (BigInteger.ZERO.equals(den))
/*  124 */       throw new ZeroException(LocalizedFormats.ZERO_DENOMINATOR, new Object[0]); 
/*  126 */     if (BigInteger.ZERO.equals(num)) {
/*  127 */       this.numerator = BigInteger.ZERO;
/*  128 */       this.denominator = BigInteger.ONE;
/*      */     } else {
/*  132 */       BigInteger gcd = num.gcd(den);
/*  133 */       if (BigInteger.ONE.compareTo(gcd) < 0) {
/*  134 */         num = num.divide(gcd);
/*  135 */         den = den.divide(gcd);
/*      */       } 
/*  139 */       if (BigInteger.ZERO.compareTo(den) > 0) {
/*  140 */         num = num.negate();
/*  141 */         den = den.negate();
/*      */       } 
/*  145 */       this.numerator = num;
/*  146 */       this.denominator = den;
/*      */     } 
/*      */   }
/*      */   
/*      */   public BigFraction(double value) throws MathIllegalArgumentException {
/*  173 */     if (Double.isNaN(value))
/*  174 */       throw new MathIllegalArgumentException(LocalizedFormats.NAN_VALUE_CONVERSION, new Object[0]); 
/*  176 */     if (Double.isInfinite(value))
/*  177 */       throw new MathIllegalArgumentException(LocalizedFormats.INFINITE_VALUE_CONVERSION, new Object[0]); 
/*  181 */     long bits = Double.doubleToLongBits(value);
/*  182 */     long sign = bits & Long.MIN_VALUE;
/*  183 */     long exponent = bits & 0x7FF0000000000000L;
/*  184 */     long m = bits & 0xFFFFFFFFFFFFFL;
/*  185 */     if (exponent != 0L)
/*  187 */       m |= 0x10000000000000L; 
/*  189 */     if (sign != 0L)
/*  190 */       m = -m; 
/*  192 */     int k = (int)(exponent >> 52L) - 1075;
/*  193 */     while ((m & 0x1FFFFFFFFFFFFEL) != 0L && (m & 0x1L) == 0L) {
/*  194 */       m >>= 1L;
/*  195 */       k++;
/*      */     } 
/*  198 */     if (k < 0) {
/*  199 */       this.numerator = BigInteger.valueOf(m);
/*  200 */       this.denominator = BigInteger.ZERO.flipBit(-k);
/*      */     } else {
/*  202 */       this.numerator = BigInteger.valueOf(m).multiply(BigInteger.ZERO.flipBit(k));
/*  203 */       this.denominator = BigInteger.ONE;
/*      */     } 
/*      */   }
/*      */   
/*      */   public BigFraction(double value, double epsilon, int maxIterations) throws FractionConversionException {
/*  232 */     this(value, epsilon, 2147483647, maxIterations);
/*      */   }
/*      */   
/*      */   private BigFraction(double value, double epsilon, int maxDenominator, int maxIterations) throws FractionConversionException {
/*  272 */     long overflow = 2147483647L;
/*  273 */     double r0 = value;
/*  274 */     long a0 = (long)FastMath.floor(r0);
/*  275 */     if (a0 > overflow)
/*  276 */       throw new FractionConversionException(value, a0, 1L); 
/*  281 */     if (FastMath.abs(a0 - value) < epsilon) {
/*  282 */       this.numerator = BigInteger.valueOf(a0);
/*  283 */       this.denominator = BigInteger.ONE;
/*      */       return;
/*      */     } 
/*  287 */     long p0 = 1L;
/*  288 */     long q0 = 0L;
/*  289 */     long p1 = a0;
/*  290 */     long q1 = 1L;
/*  292 */     long p2 = 0L;
/*  293 */     long q2 = 1L;
/*  295 */     int n = 0;
/*  296 */     boolean stop = false;
/*      */     do {
/*  298 */       n++;
/*  299 */       double r1 = 1.0D / (r0 - a0);
/*  300 */       long a1 = (long)FastMath.floor(r1);
/*  301 */       p2 = a1 * p1 + p0;
/*  302 */       q2 = a1 * q1 + q0;
/*  303 */       if (p2 > overflow || q2 > overflow)
/*  304 */         throw new FractionConversionException(value, p2, q2); 
/*  307 */       double convergent = p2 / q2;
/*  308 */       if (n < maxIterations && FastMath.abs(convergent - value) > epsilon && q2 < maxDenominator) {
/*  311 */         p0 = p1;
/*  312 */         p1 = p2;
/*  313 */         q0 = q1;
/*  314 */         q1 = q2;
/*  315 */         a0 = a1;
/*  316 */         r0 = r1;
/*      */       } else {
/*  318 */         stop = true;
/*      */       } 
/*  320 */     } while (!stop);
/*  322 */     if (n >= maxIterations)
/*  323 */       throw new FractionConversionException(value, maxIterations); 
/*  326 */     if (q2 < maxDenominator) {
/*  327 */       this.numerator = BigInteger.valueOf(p2);
/*  328 */       this.denominator = BigInteger.valueOf(q2);
/*      */     } else {
/*  330 */       this.numerator = BigInteger.valueOf(p1);
/*  331 */       this.denominator = BigInteger.valueOf(q1);
/*      */     } 
/*      */   }
/*      */   
/*      */   public BigFraction(double value, int maxDenominator) throws FractionConversionException {
/*  354 */     this(value, 0.0D, maxDenominator, 100);
/*      */   }
/*      */   
/*      */   public BigFraction(int num) {
/*  367 */     this(BigInteger.valueOf(num), BigInteger.ONE);
/*      */   }
/*      */   
/*      */   public BigFraction(int num, int den) {
/*  382 */     this(BigInteger.valueOf(num), BigInteger.valueOf(den));
/*      */   }
/*      */   
/*      */   public BigFraction(long num) {
/*  394 */     this(BigInteger.valueOf(num), BigInteger.ONE);
/*      */   }
/*      */   
/*      */   public BigFraction(long num, long den) {
/*  409 */     this(BigInteger.valueOf(num), BigInteger.valueOf(den));
/*      */   }
/*      */   
/*      */   public static BigFraction getReducedFraction(int numerator, int denominator) {
/*  433 */     if (numerator == 0)
/*  434 */       return ZERO; 
/*  437 */     return new BigFraction(numerator, denominator);
/*      */   }
/*      */   
/*      */   public BigFraction abs() {
/*  448 */     return (BigInteger.ZERO.compareTo(this.numerator) <= 0) ? this : negate();
/*      */   }
/*      */   
/*      */   public BigFraction add(BigInteger bg) throws NullArgumentException {
/*  464 */     MathUtils.checkNotNull(bg);
/*  465 */     return new BigFraction(this.numerator.add(this.denominator.multiply(bg)), this.denominator);
/*      */   }
/*      */   
/*      */   public BigFraction add(int i) {
/*  479 */     return add(BigInteger.valueOf(i));
/*      */   }
/*      */   
/*      */   public BigFraction add(long l) {
/*  493 */     return add(BigInteger.valueOf(l));
/*      */   }
/*      */   
/*      */   public BigFraction add(BigFraction fraction) {
/*  508 */     if (fraction == null)
/*  509 */       throw new NullArgumentException(LocalizedFormats.FRACTION, new Object[0]); 
/*  511 */     if (ZERO.equals(fraction))
/*  512 */       return this; 
/*  515 */     BigInteger num = null;
/*  516 */     BigInteger den = null;
/*  518 */     if (this.denominator.equals(fraction.denominator)) {
/*  519 */       num = this.numerator.add(fraction.numerator);
/*  520 */       den = this.denominator;
/*      */     } else {
/*  522 */       num = this.numerator.multiply(fraction.denominator).add(fraction.numerator.multiply(this.denominator));
/*  523 */       den = this.denominator.multiply(fraction.denominator);
/*      */     } 
/*  525 */     return new BigFraction(num, den);
/*      */   }
/*      */   
/*      */   public BigDecimal bigDecimalValue() {
/*  542 */     return (new BigDecimal(this.numerator)).divide(new BigDecimal(this.denominator));
/*      */   }
/*      */   
/*      */   public BigDecimal bigDecimalValue(int roundingMode) {
/*  561 */     return (new BigDecimal(this.numerator)).divide(new BigDecimal(this.denominator), roundingMode);
/*      */   }
/*      */   
/*      */   public BigDecimal bigDecimalValue(int scale, int roundingMode) {
/*  580 */     return (new BigDecimal(this.numerator)).divide(new BigDecimal(this.denominator), scale, roundingMode);
/*      */   }
/*      */   
/*      */   public int compareTo(BigFraction object) {
/*  595 */     BigInteger nOd = this.numerator.multiply(object.denominator);
/*  596 */     BigInteger dOn = this.denominator.multiply(object.numerator);
/*  597 */     return nOd.compareTo(dOn);
/*      */   }
/*      */   
/*      */   public BigFraction divide(BigInteger bg) {
/*  612 */     if (bg == null)
/*  613 */       throw new NullArgumentException(LocalizedFormats.FRACTION, new Object[0]); 
/*  615 */     if (BigInteger.ZERO.equals(bg))
/*  616 */       throw new MathArithmeticException(LocalizedFormats.ZERO_DENOMINATOR, new Object[0]); 
/*  618 */     return new BigFraction(this.numerator, this.denominator.multiply(bg));
/*      */   }
/*      */   
/*      */   public BigFraction divide(int i) {
/*  632 */     return divide(BigInteger.valueOf(i));
/*      */   }
/*      */   
/*      */   public BigFraction divide(long l) {
/*  646 */     return divide(BigInteger.valueOf(l));
/*      */   }
/*      */   
/*      */   public BigFraction divide(BigFraction fraction) {
/*  661 */     if (fraction == null)
/*  662 */       throw new NullArgumentException(LocalizedFormats.FRACTION, new Object[0]); 
/*  664 */     if (BigInteger.ZERO.equals(fraction.numerator))
/*  665 */       throw new MathArithmeticException(LocalizedFormats.ZERO_DENOMINATOR, new Object[0]); 
/*  668 */     return multiply(fraction.reciprocal());
/*      */   }
/*      */   
/*      */   public double doubleValue() {
/*  682 */     double result = this.numerator.doubleValue() / this.denominator.doubleValue();
/*  683 */     if (Double.isNaN(result)) {
/*  686 */       int shift = Math.max(this.numerator.bitLength(), this.denominator.bitLength()) - FastMath.getExponent(Double.MAX_VALUE);
/*  688 */       result = this.numerator.shiftRight(shift).doubleValue() / this.denominator.shiftRight(shift).doubleValue();
/*      */     } 
/*  691 */     return result;
/*      */   }
/*      */   
/*      */   public boolean equals(Object other) {
/*  711 */     boolean ret = false;
/*  713 */     if (this == other) {
/*  714 */       ret = true;
/*  715 */     } else if (other instanceof BigFraction) {
/*  716 */       BigFraction rhs = ((BigFraction)other).reduce();
/*  717 */       BigFraction thisOne = reduce();
/*  718 */       ret = (thisOne.numerator.equals(rhs.numerator) && thisOne.denominator.equals(rhs.denominator));
/*      */     } 
/*  721 */     return ret;
/*      */   }
/*      */   
/*      */   public float floatValue() {
/*  735 */     float result = this.numerator.floatValue() / this.denominator.floatValue();
/*  736 */     if (Double.isNaN(result)) {
/*  739 */       int shift = Math.max(this.numerator.bitLength(), this.denominator.bitLength()) - FastMath.getExponent(Float.MAX_VALUE);
/*  741 */       result = this.numerator.shiftRight(shift).floatValue() / this.denominator.shiftRight(shift).floatValue();
/*      */     } 
/*  744 */     return result;
/*      */   }
/*      */   
/*      */   public BigInteger getDenominator() {
/*  755 */     return this.denominator;
/*      */   }
/*      */   
/*      */   public int getDenominatorAsInt() {
/*  766 */     return this.denominator.intValue();
/*      */   }
/*      */   
/*      */   public long getDenominatorAsLong() {
/*  777 */     return this.denominator.longValue();
/*      */   }
/*      */   
/*      */   public BigInteger getNumerator() {
/*  788 */     return this.numerator;
/*      */   }
/*      */   
/*      */   public int getNumeratorAsInt() {
/*  799 */     return this.numerator.intValue();
/*      */   }
/*      */   
/*      */   public long getNumeratorAsLong() {
/*  810 */     return this.numerator.longValue();
/*      */   }
/*      */   
/*      */   public int hashCode() {
/*  823 */     return 37 * (629 + this.numerator.hashCode()) + this.denominator.hashCode();
/*      */   }
/*      */   
/*      */   public int intValue() {
/*  837 */     return this.numerator.divide(this.denominator).intValue();
/*      */   }
/*      */   
/*      */   public long longValue() {
/*  851 */     return this.numerator.divide(this.denominator).longValue();
/*      */   }
/*      */   
/*      */   public BigFraction multiply(BigInteger bg) {
/*  865 */     if (bg == null)
/*  866 */       throw new NullArgumentException(); 
/*  868 */     return new BigFraction(bg.multiply(this.numerator), this.denominator);
/*      */   }
/*      */   
/*      */   public BigFraction multiply(int i) {
/*  882 */     return multiply(BigInteger.valueOf(i));
/*      */   }
/*      */   
/*      */   public BigFraction multiply(long l) {
/*  896 */     return multiply(BigInteger.valueOf(l));
/*      */   }
/*      */   
/*      */   public BigFraction multiply(BigFraction fraction) {
/*  910 */     if (fraction == null)
/*  911 */       throw new NullArgumentException(LocalizedFormats.FRACTION, new Object[0]); 
/*  913 */     if (this.numerator.equals(BigInteger.ZERO) || fraction.numerator.equals(BigInteger.ZERO))
/*  915 */       return ZERO; 
/*  917 */     return new BigFraction(this.numerator.multiply(fraction.numerator), this.denominator.multiply(fraction.denominator));
/*      */   }
/*      */   
/*      */   public BigFraction negate() {
/*  930 */     return new BigFraction(this.numerator.negate(), this.denominator);
/*      */   }
/*      */   
/*      */   public double percentageValue() {
/*  942 */     return multiply(ONE_HUNDRED).doubleValue();
/*      */   }
/*      */   
/*      */   public BigFraction pow(int exponent) {
/*  957 */     if (exponent < 0)
/*  958 */       return new BigFraction(this.denominator.pow(-exponent), this.numerator.pow(-exponent)); 
/*  960 */     return new BigFraction(this.numerator.pow(exponent), this.denominator.pow(exponent));
/*      */   }
/*      */   
/*      */   public BigFraction pow(long exponent) {
/*  974 */     if (exponent < 0L)
/*  975 */       return new BigFraction(ArithmeticUtils.pow(this.denominator, -exponent), ArithmeticUtils.pow(this.numerator, -exponent)); 
/*  978 */     return new BigFraction(ArithmeticUtils.pow(this.numerator, exponent), ArithmeticUtils.pow(this.denominator, exponent));
/*      */   }
/*      */   
/*      */   public BigFraction pow(BigInteger exponent) {
/*  993 */     if (exponent.compareTo(BigInteger.ZERO) < 0) {
/*  994 */       BigInteger eNeg = exponent.negate();
/*  995 */       return new BigFraction(ArithmeticUtils.pow(this.denominator, eNeg), ArithmeticUtils.pow(this.numerator, eNeg));
/*      */     } 
/*  998 */     return new BigFraction(ArithmeticUtils.pow(this.numerator, exponent), ArithmeticUtils.pow(this.denominator, exponent));
/*      */   }
/*      */   
/*      */   public double pow(double exponent) {
/* 1013 */     return FastMath.pow(this.numerator.doubleValue(), exponent) / FastMath.pow(this.denominator.doubleValue(), exponent);
/*      */   }
/*      */   
/*      */   public BigFraction reciprocal() {
/* 1025 */     return new BigFraction(this.denominator, this.numerator);
/*      */   }
/*      */   
/*      */   public BigFraction reduce() {
/* 1037 */     BigInteger gcd = this.numerator.gcd(this.denominator);
/* 1038 */     return new BigFraction(this.numerator.divide(gcd), this.denominator.divide(gcd));
/*      */   }
/*      */   
/*      */   public BigFraction subtract(BigInteger bg) {
/* 1052 */     if (bg == null)
/* 1053 */       throw new NullArgumentException(); 
/* 1055 */     return new BigFraction(this.numerator.subtract(this.denominator.multiply(bg)), this.denominator);
/*      */   }
/*      */   
/*      */   public BigFraction subtract(int i) {
/* 1068 */     return subtract(BigInteger.valueOf(i));
/*      */   }
/*      */   
/*      */   public BigFraction subtract(long l) {
/* 1081 */     return subtract(BigInteger.valueOf(l));
/*      */   }
/*      */   
/*      */   public BigFraction subtract(BigFraction fraction) {
/* 1095 */     if (fraction == null)
/* 1096 */       throw new NullArgumentException(LocalizedFormats.FRACTION, new Object[0]); 
/* 1098 */     if (ZERO.equals(fraction))
/* 1099 */       return this; 
/* 1102 */     BigInteger num = null;
/* 1103 */     BigInteger den = null;
/* 1104 */     if (this.denominator.equals(fraction.denominator)) {
/* 1105 */       num = this.numerator.subtract(fraction.numerator);
/* 1106 */       den = this.denominator;
/*      */     } else {
/* 1108 */       num = this.numerator.multiply(fraction.denominator).subtract(fraction.numerator.multiply(this.denominator));
/* 1109 */       den = this.denominator.multiply(fraction.denominator);
/*      */     } 
/* 1111 */     return new BigFraction(num, den);
/*      */   }
/*      */   
/*      */   public String toString() {
/* 1126 */     String str = null;
/* 1127 */     if (BigInteger.ONE.equals(this.denominator)) {
/* 1128 */       str = this.numerator.toString();
/* 1129 */     } else if (BigInteger.ZERO.equals(this.numerator)) {
/* 1130 */       str = "0";
/*      */     } else {
/* 1132 */       str = this.numerator + " / " + this.denominator;
/*      */     } 
/* 1134 */     return str;
/*      */   }
/*      */   
/*      */   public BigFractionField getField() {
/* 1139 */     return BigFractionField.getInstance();
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\fraction\BigFraction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */