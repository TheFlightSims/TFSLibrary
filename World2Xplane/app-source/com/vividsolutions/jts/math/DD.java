/*      */ package com.vividsolutions.jts.math;
/*      */ 
/*      */ import java.io.Serializable;
/*      */ 
/*      */ public final class DD implements Serializable, Comparable, Cloneable {
/*  120 */   public static final DD PI = new DD(Math.PI, 1.2246467991473532E-16D);
/*      */   
/*  127 */   public static final DD TWO_PI = new DD(6.283185307179586D, 2.4492935982947064E-16D);
/*      */   
/*  134 */   public static final DD PI_2 = new DD(1.5707963267948966D, 6.123233995736766E-17D);
/*      */   
/*  141 */   public static final DD E = new DD(Math.E, 1.4456468917292502E-16D);
/*      */   
/*  148 */   public static final DD NaN = new DD(Double.NaN, Double.NaN);
/*      */   
/*      */   public static final double EPS = 1.23259516440783E-32D;
/*      */   
/*      */   private static final double SPLIT = 1.34217729E8D;
/*      */   
/*      */   private static strictfp DD createNaN() {
/*  157 */     return new DD(Double.NaN, Double.NaN);
/*      */   }
/*      */   
/*      */   public static strictfp DD valueOf(String str) throws NumberFormatException {
/*  170 */     return parse(str);
/*      */   }
/*      */   
/*      */   public static strictfp DD valueOf(double x) {
/*  179 */     return new DD(x);
/*      */   }
/*      */   
/*  189 */   private double hi = 0.0D;
/*      */   
/*  194 */   private double lo = 0.0D;
/*      */   
/*      */   private static final int MAX_PRINT_DIGITS = 32;
/*      */   
/*      */   public strictfp DD() {
/*  201 */     init(0.0D);
/*      */   }
/*      */   
/*      */   public strictfp DD(double x) {
/*  211 */     init(x);
/*      */   }
/*      */   
/*      */   public strictfp DD(double hi, double lo) {
/*  222 */     init(hi, lo);
/*      */   }
/*      */   
/*      */   public strictfp DD(DD dd) {
/*  232 */     init(dd);
/*      */   }
/*      */   
/*      */   public strictfp DD(String str) throws NumberFormatException {
/*  244 */     this(parse(str));
/*      */   }
/*      */   
/*      */   public static strictfp DD copy(DD dd) {
/*  255 */     return new DD(dd);
/*      */   }
/*      */   
/*      */   public strictfp Object clone() {
/*      */     try {
/*  266 */       return super.clone();
/*  268 */     } catch (CloneNotSupportedException ex) {
/*  270 */       return null;
/*      */     } 
/*      */   }
/*      */   
/*      */   private final strictfp void init(double x) {
/*  276 */     this.hi = x;
/*  277 */     this.lo = 0.0D;
/*      */   }
/*      */   
/*      */   private final strictfp void init(double hi, double lo) {
/*  282 */     this.hi = hi;
/*  283 */     this.lo = lo;
/*      */   }
/*      */   
/*      */   private final strictfp void init(DD dd) {
/*  288 */     this.hi = dd.hi;
/*  289 */     this.lo = dd.lo;
/*      */   }
/*      */   
/*      */   public strictfp DD setValue(DD value) {
/*  316 */     init(value);
/*  317 */     return this;
/*      */   }
/*      */   
/*      */   public strictfp DD setValue(double value) {
/*  327 */     init(value);
/*  328 */     return this;
/*      */   }
/*      */   
/*      */   public final strictfp DD add(DD y) {
/*  340 */     return copy(this).selfAdd(y);
/*      */   }
/*      */   
/*      */   public final strictfp DD add(double y) {
/*  351 */     return copy(this).selfAdd(y);
/*      */   }
/*      */   
/*      */   public final strictfp DD selfAdd(DD y) {
/*  365 */     return selfAdd(y.hi, y.lo);
/*      */   }
/*      */   
/*      */   public final strictfp DD selfAdd(double y) {
/*  380 */     double S = this.hi + y;
/*  381 */     double e = S - this.hi;
/*  382 */     double s = S - e;
/*  383 */     s = y - e + this.hi - s;
/*  384 */     double f = s + this.lo;
/*  385 */     double H = S + f;
/*  386 */     double h = f + S - H;
/*  387 */     this.hi = H + h;
/*  388 */     this.lo = h + H - this.hi;
/*  389 */     return this;
/*      */   }
/*      */   
/*      */   private final strictfp DD selfAdd(double yhi, double ylo) {
/*  396 */     double S = this.hi + yhi;
/*  397 */     double T = this.lo + ylo;
/*  398 */     double e = S - this.hi;
/*  399 */     double f = T - this.lo;
/*  400 */     double s = S - e;
/*  401 */     double t = T - f;
/*  402 */     s = yhi - e + this.hi - s;
/*  403 */     t = ylo - f + this.lo - t;
/*  404 */     e = s + T;
/*  404 */     double H = S + e, h = e + S - H;
/*  404 */     e = t + h;
/*  406 */     double zhi = H + e;
/*  407 */     double zlo = e + H - zhi;
/*  408 */     this.hi = zhi;
/*  409 */     this.lo = zlo;
/*  410 */     return this;
/*      */   }
/*      */   
/*      */   public final strictfp DD subtract(DD y) {
/*  421 */     return add(y.negate());
/*      */   }
/*      */   
/*      */   public final strictfp DD subtract(double y) {
/*  432 */     return add(-y);
/*      */   }
/*      */   
/*      */   public final strictfp DD selfSubtract(DD y) {
/*  447 */     if (isNaN())
/*  447 */       return this; 
/*  448 */     return selfAdd(-y.hi, -y.lo);
/*      */   }
/*      */   
/*      */   public final strictfp DD selfSubtract(double y) {
/*  462 */     if (isNaN())
/*  462 */       return this; 
/*  463 */     return selfAdd(-y, 0.0D);
/*      */   }
/*      */   
/*      */   public final strictfp DD negate() {
/*  473 */     if (isNaN())
/*  473 */       return this; 
/*  474 */     return new DD(-this.hi, -this.lo);
/*      */   }
/*      */   
/*      */   public final strictfp DD multiply(DD y) {
/*  485 */     if (y.isNaN())
/*  485 */       return createNaN(); 
/*  486 */     return copy(this).selfMultiply(y);
/*      */   }
/*      */   
/*      */   public final strictfp DD multiply(double y) {
/*  497 */     if (Double.isNaN(y))
/*  497 */       return createNaN(); 
/*  498 */     return copy(this).selfMultiply(y, 0.0D);
/*      */   }
/*      */   
/*      */   public final strictfp DD selfMultiply(DD y) {
/*  512 */     return selfMultiply(y.hi, y.lo);
/*      */   }
/*      */   
/*      */   public final strictfp DD selfMultiply(double y) {
/*  526 */     return selfMultiply(y, 0.0D);
/*      */   }
/*      */   
/*      */   private final strictfp DD selfMultiply(double yhi, double ylo) {
/*  532 */     double C = 1.34217729E8D * this.hi, hx = C - this.hi, c = 1.34217729E8D * yhi;
/*  533 */     hx = C - hx;
/*  533 */     double tx = this.hi - hx, hy = c - yhi;
/*  534 */     C = this.hi * yhi;
/*  534 */     hy = c - hy;
/*  534 */     double ty = yhi - hy;
/*  535 */     c = hx * hy - C + hx * ty + tx * hy + tx * ty + this.hi * ylo + this.lo * yhi;
/*  536 */     double zhi = C + c;
/*  536 */     hx = C - zhi;
/*  537 */     double zlo = c + hx;
/*  538 */     this.hi = zhi;
/*  539 */     this.lo = zlo;
/*  540 */     return this;
/*      */   }
/*      */   
/*      */   public final strictfp DD divide(DD y) {
/*  552 */     double C = this.hi / y.hi, c = 1.34217729E8D * C, hc = c - C, u = 1.34217729E8D * y.hi;
/*  552 */     hc = c - hc;
/*  553 */     double tc = C - hc, hy = u - y.hi, U = C * y.hi;
/*  553 */     hy = u - hy;
/*  553 */     double ty = y.hi - hy;
/*  554 */     u = hc * hy - U + hc * ty + tc * hy + tc * ty;
/*  555 */     c = (this.hi - U - u + this.lo - C * y.lo) / y.hi;
/*  556 */     u = C + c;
/*  558 */     double zhi = u;
/*  559 */     double zlo = C - u + c;
/*  560 */     return new DD(zhi, zlo);
/*      */   }
/*      */   
/*      */   public final strictfp DD divide(double y) {
/*  571 */     if (Double.isNaN(y))
/*  571 */       return createNaN(); 
/*  572 */     return copy(this).selfDivide(y, 0.0D);
/*      */   }
/*      */   
/*      */   public final strictfp DD selfDivide(DD y) {
/*  586 */     return selfDivide(y.hi, y.lo);
/*      */   }
/*      */   
/*      */   public final strictfp DD selfDivide(double y) {
/*  600 */     return selfDivide(y, 0.0D);
/*      */   }
/*      */   
/*      */   private final strictfp DD selfDivide(double yhi, double ylo) {
/*  606 */     double C = this.hi / yhi, c = 1.34217729E8D * C, hc = c - C, u = 1.34217729E8D * yhi;
/*  606 */     hc = c - hc;
/*  607 */     double tc = C - hc, hy = u - yhi, U = C * yhi;
/*  607 */     hy = u - hy;
/*  607 */     double ty = yhi - hy;
/*  608 */     u = hc * hy - U + hc * ty + tc * hy + tc * ty;
/*  609 */     c = (this.hi - U - u + this.lo - C * ylo) / yhi;
/*  610 */     u = C + c;
/*  612 */     this.hi = u;
/*  613 */     this.lo = C - u + c;
/*  614 */     return this;
/*      */   }
/*      */   
/*      */   public final strictfp DD reciprocal() {
/*  625 */     double C = 1.0D / this.hi;
/*  626 */     double c = 1.34217729E8D * C;
/*  627 */     double hc = c - C;
/*  628 */     double u = 1.34217729E8D * this.hi;
/*  629 */     hc = c - hc;
/*  629 */     double tc = C - hc, hy = u - this.hi, U = C * this.hi;
/*  629 */     hy = u - hy;
/*  629 */     double ty = this.hi - hy;
/*  630 */     u = hc * hy - U + hc * ty + tc * hy + tc * ty;
/*  631 */     c = (1.0D - U - u - C * this.lo) / this.hi;
/*  633 */     double zhi = C + c;
/*  634 */     double zlo = C - zhi + c;
/*  635 */     return new DD(zhi, zlo);
/*      */   }
/*      */   
/*      */   public strictfp DD floor() {
/*  653 */     if (isNaN())
/*  653 */       return NaN; 
/*  654 */     double fhi = Math.floor(this.hi);
/*  655 */     double flo = 0.0D;
/*  657 */     if (fhi == this.hi)
/*  658 */       flo = Math.floor(this.lo); 
/*  661 */     return new DD(fhi, flo);
/*      */   }
/*      */   
/*      */   public strictfp DD ceil() {
/*  677 */     if (isNaN())
/*  677 */       return NaN; 
/*  678 */     double fhi = Math.ceil(this.hi);
/*  679 */     double flo = 0.0D;
/*  681 */     if (fhi == this.hi)
/*  682 */       flo = Math.ceil(this.lo); 
/*  685 */     return new DD(fhi, flo);
/*      */   }
/*      */   
/*      */   public strictfp int signum() {
/*  701 */     if (this.hi > 0.0D)
/*  701 */       return 1; 
/*  702 */     if (this.hi < 0.0D)
/*  702 */       return -1; 
/*  703 */     if (this.lo > 0.0D)
/*  703 */       return 1; 
/*  704 */     if (this.lo < 0.0D)
/*  704 */       return -1; 
/*  705 */     return 0;
/*      */   }
/*      */   
/*      */   public strictfp DD rint() {
/*  720 */     if (isNaN())
/*  720 */       return this; 
/*  722 */     DD plus5 = add(0.5D);
/*  723 */     return plus5.floor();
/*      */   }
/*      */   
/*      */   public strictfp DD trunc() {
/*  738 */     if (isNaN())
/*  738 */       return NaN; 
/*  739 */     if (isPositive())
/*  740 */       return floor(); 
/*  742 */     return ceil();
/*      */   }
/*      */   
/*      */   public strictfp DD abs() {
/*  756 */     if (isNaN())
/*  756 */       return NaN; 
/*  757 */     if (isNegative())
/*  758 */       return negate(); 
/*  759 */     return new DD(this);
/*      */   }
/*      */   
/*      */   public strictfp DD sqr() {
/*  769 */     return multiply(this);
/*      */   }
/*      */   
/*      */   public strictfp DD selfSqr() {
/*  782 */     return selfMultiply(this);
/*      */   }
/*      */   
/*      */   public static strictfp DD sqr(double x) {
/*  792 */     return valueOf(x).selfMultiply(x);
/*      */   }
/*      */   
/*      */   public strictfp DD sqrt() {
/*  814 */     if (isZero())
/*  815 */       return valueOf(0.0D); 
/*  817 */     if (isNegative())
/*  818 */       return NaN; 
/*  821 */     double x = 1.0D / Math.sqrt(this.hi);
/*  822 */     double ax = this.hi * x;
/*  824 */     DD axdd = valueOf(ax);
/*  825 */     DD diffSq = subtract(axdd.sqr());
/*  826 */     double d2 = diffSq.hi * x * 0.5D;
/*  828 */     return axdd.add(d2);
/*      */   }
/*      */   
/*      */   public static strictfp DD sqrt(double x) {
/*  833 */     return valueOf(x).sqrt();
/*      */   }
/*      */   
/*      */   public strictfp DD pow(int exp) {
/*  845 */     if (exp == 0.0D)
/*  846 */       return valueOf(1.0D); 
/*  848 */     DD r = new DD(this);
/*  849 */     DD s = valueOf(1.0D);
/*  850 */     int n = Math.abs(exp);
/*  852 */     if (n > 1) {
/*  854 */       while (n > 0) {
/*  855 */         if (n % 2 == 1)
/*  856 */           s.selfMultiply(r); 
/*  858 */         n /= 2;
/*  859 */         if (n > 0)
/*  860 */           r = r.sqr(); 
/*      */       } 
/*      */     } else {
/*  863 */       s = r;
/*      */     } 
/*  867 */     if (exp < 0)
/*  868 */       return s.reciprocal(); 
/*  869 */     return s;
/*      */   }
/*      */   
/*      */   public strictfp DD min(DD x) {
/*  885 */     if (le(x))
/*  886 */       return this; 
/*  889 */     return x;
/*      */   }
/*      */   
/*      */   public strictfp DD max(DD x) {
/*  900 */     if (ge(x))
/*  901 */       return this; 
/*  904 */     return x;
/*      */   }
/*      */   
/*      */   public strictfp double doubleValue() {
/*  920 */     return this.hi + this.lo;
/*      */   }
/*      */   
/*      */   public strictfp int intValue() {
/*  930 */     return (int)this.hi;
/*      */   }
/*      */   
/*      */   public strictfp boolean isZero() {
/*  945 */     return (this.hi == 0.0D && this.lo == 0.0D);
/*      */   }
/*      */   
/*      */   public strictfp boolean isNegative() {
/*  955 */     return (this.hi < 0.0D || (this.hi == 0.0D && this.lo < 0.0D));
/*      */   }
/*      */   
/*      */   public strictfp boolean isPositive() {
/*  965 */     return (this.hi > 0.0D || (this.hi == 0.0D && this.lo > 0.0D));
/*      */   }
/*      */   
/*      */   public strictfp boolean isNaN() {
/*  973 */     return Double.isNaN(this.hi);
/*      */   }
/*      */   
/*      */   public strictfp boolean equals(DD y) {
/*  983 */     return (this.hi == y.hi && this.lo == y.lo);
/*      */   }
/*      */   
/*      */   public strictfp boolean gt(DD y) {
/*  993 */     return (this.hi > y.hi || (this.hi == y.hi && this.lo > y.lo));
/*      */   }
/*      */   
/*      */   public strictfp boolean ge(DD y) {
/* 1002 */     return (this.hi > y.hi || (this.hi == y.hi && this.lo >= y.lo));
/*      */   }
/*      */   
/*      */   public strictfp boolean lt(DD y) {
/* 1011 */     return (this.hi < y.hi || (this.hi == y.hi && this.lo < y.lo));
/*      */   }
/*      */   
/*      */   public strictfp boolean le(DD y) {
/* 1020 */     return (this.hi < y.hi || (this.hi == y.hi && this.lo <= y.lo));
/*      */   }
/*      */   
/*      */   public strictfp int compareTo(Object o) {
/* 1031 */     DD other = (DD)o;
/* 1033 */     if (this.hi < other.hi)
/* 1033 */       return -1; 
/* 1034 */     if (this.hi > other.hi)
/* 1034 */       return 1; 
/* 1035 */     if (this.lo < other.lo)
/* 1035 */       return -1; 
/* 1036 */     if (this.lo > other.lo)
/* 1036 */       return 1; 
/* 1037 */     return 0;
/*      */   }
/*      */   
/* 1047 */   private static final DD TEN = valueOf(10.0D);
/*      */   
/* 1048 */   private static final DD ONE = valueOf(1.0D);
/*      */   
/*      */   private static final String SCI_NOT_EXPONENT_CHAR = "E";
/*      */   
/*      */   private static final String SCI_NOT_ZERO = "0.0E0";
/*      */   
/*      */   public strictfp String dump() {
/* 1059 */     return "DD<" + this.hi + ", " + this.lo + ">";
/*      */   }
/*      */   
/*      */   public strictfp String toString() {
/* 1071 */     int mag = magnitude(this.hi);
/* 1072 */     if (mag >= -3 && mag <= 20)
/* 1073 */       return toStandardNotation(); 
/* 1074 */     return toSciNotation();
/*      */   }
/*      */   
/*      */   public strictfp String toStandardNotation() {
/* 1084 */     String specialStr = getSpecialNumberString();
/* 1085 */     if (specialStr != null)
/* 1086 */       return specialStr; 
/* 1088 */     int[] magnitude = new int[1];
/* 1089 */     String sigDigits = extractSignificantDigits(true, magnitude);
/* 1090 */     int decimalPointPos = magnitude[0] + 1;
/* 1092 */     String num = sigDigits;
/* 1094 */     if (sigDigits.charAt(0) == '.') {
/* 1095 */       num = "0" + sigDigits;
/* 1097 */     } else if (decimalPointPos < 0) {
/* 1098 */       num = "0." + stringOfChar('0', -decimalPointPos) + sigDigits;
/* 1100 */     } else if (sigDigits.indexOf('.') == -1) {
/* 1103 */       int numZeroes = decimalPointPos - sigDigits.length();
/* 1104 */       String zeroes = stringOfChar('0', numZeroes);
/* 1105 */       num = sigDigits + zeroes + ".0";
/*      */     } 
/* 1108 */     if (isNegative())
/* 1109 */       return "-" + num; 
/* 1110 */     return num;
/*      */   }
/*      */   
/*      */   public strictfp String toSciNotation() {
/* 1121 */     if (isZero())
/* 1122 */       return "0.0E0"; 
/* 1124 */     String specialStr = getSpecialNumberString();
/* 1125 */     if (specialStr != null)
/* 1126 */       return specialStr; 
/* 1128 */     int[] magnitude = new int[1];
/* 1129 */     String digits = extractSignificantDigits(false, magnitude);
/* 1130 */     String expStr = "E" + magnitude[0];
/* 1134 */     if (digits.charAt(0) == '0')
/* 1135 */       throw new IllegalStateException("Found leading zero: " + digits); 
/* 1139 */     String trailingDigits = "";
/* 1140 */     if (digits.length() > 1)
/* 1141 */       trailingDigits = digits.substring(1); 
/* 1142 */     String digitsWithDecimal = digits.charAt(0) + "." + trailingDigits;
/* 1144 */     if (isNegative())
/* 1145 */       return "-" + digitsWithDecimal + expStr; 
/* 1146 */     return digitsWithDecimal + expStr;
/*      */   }
/*      */   
/*      */   private strictfp String extractSignificantDigits(boolean insertDecimalPoint, int[] magnitude) {
/* 1162 */     DD y = abs();
/* 1164 */     int mag = magnitude(y.hi);
/* 1165 */     DD scale = TEN.pow(mag);
/* 1166 */     y = y.divide(scale);
/* 1169 */     if (y.gt(TEN)) {
/* 1170 */       y = y.divide(TEN);
/* 1171 */       mag++;
/* 1173 */     } else if (y.lt(ONE)) {
/* 1174 */       y = y.multiply(TEN);
/* 1175 */       mag--;
/*      */     } 
/* 1178 */     int decimalPointPos = mag + 1;
/* 1179 */     StringBuffer buf = new StringBuffer();
/* 1180 */     int numDigits = 31;
/* 1181 */     for (int i = 0; i <= numDigits; i++) {
/* 1182 */       if (insertDecimalPoint && i == decimalPointPos)
/* 1183 */         buf.append('.'); 
/* 1185 */       int digit = (int)y.hi;
/* 1191 */       if (digit < 0 || digit > 9);
/* 1201 */       if (digit < 0)
/*      */         break; 
/* 1205 */       boolean rebiasBy10 = false;
/* 1206 */       char digitChar = Character.MIN_VALUE;
/* 1207 */       if (digit > 9) {
/* 1209 */         rebiasBy10 = true;
/* 1211 */         digitChar = '9';
/*      */       } else {
/* 1214 */         digitChar = (char)(48 + digit);
/*      */       } 
/* 1216 */       buf.append(digitChar);
/* 1217 */       y = y.subtract(valueOf(digit)).multiply(TEN);
/* 1219 */       if (rebiasBy10)
/* 1220 */         y.selfAdd(TEN); 
/* 1222 */       boolean continueExtractingDigits = true;
/* 1234 */       int remMag = magnitude(y.hi);
/* 1235 */       if (remMag < 0 && Math.abs(remMag) >= numDigits - i)
/* 1236 */         continueExtractingDigits = false; 
/* 1237 */       if (!continueExtractingDigits)
/*      */         break; 
/*      */     } 
/* 1240 */     magnitude[0] = mag;
/* 1241 */     return buf.toString();
/*      */   }
/*      */   
/*      */   private static strictfp String stringOfChar(char ch, int len) {
/* 1254 */     StringBuffer buf = new StringBuffer();
/* 1255 */     for (int i = 0; i < len; i++)
/* 1256 */       buf.append(ch); 
/* 1258 */     return buf.toString();
/*      */   }
/*      */   
/*      */   private strictfp String getSpecialNumberString() {
/* 1270 */     if (isZero())
/* 1270 */       return "0.0"; 
/* 1271 */     if (isNaN())
/* 1271 */       return "NaN "; 
/* 1272 */     return null;
/*      */   }
/*      */   
/*      */   private static strictfp int magnitude(double x) {
/* 1287 */     double xAbs = Math.abs(x);
/* 1288 */     double xLog10 = Math.log(xAbs) / Math.log(10.0D);
/* 1289 */     int xMag = (int)Math.floor(xLog10);
/* 1295 */     double xApprox = Math.pow(10.0D, xMag);
/* 1296 */     if (xApprox * 10.0D <= xAbs)
/* 1297 */       xMag++; 
/* 1299 */     return xMag;
/*      */   }
/*      */   
/*      */   public static strictfp DD parse(String str) throws NumberFormatException {
/* 1323 */     int i = 0;
/* 1324 */     int strlen = str.length();
/* 1327 */     while (Character.isWhitespace(str.charAt(i)))
/* 1328 */       i++; 
/* 1331 */     boolean isNegative = false;
/* 1332 */     if (i < strlen) {
/* 1333 */       char signCh = str.charAt(i);
/* 1334 */       if (signCh == '-' || signCh == '+') {
/* 1335 */         i++;
/* 1336 */         if (signCh == '-')
/* 1336 */           isNegative = true; 
/*      */       } 
/*      */     } 
/* 1342 */     DD val = new DD();
/* 1344 */     int numDigits = 0;
/* 1345 */     int numBeforeDec = 0;
/* 1346 */     int exp = 0;
/* 1348 */     while (i < strlen) {
/* 1350 */       char ch = str.charAt(i);
/* 1351 */       i++;
/* 1352 */       if (Character.isDigit(ch)) {
/* 1353 */         double d = (ch - 48);
/* 1354 */         val.selfMultiply(TEN);
/* 1356 */         val.selfAdd(d);
/* 1357 */         numDigits++;
/*      */         continue;
/*      */       } 
/* 1360 */       if (ch == '.') {
/* 1361 */         numBeforeDec = numDigits;
/*      */         continue;
/*      */       } 
/* 1364 */       if (ch == 'e' || ch == 'E') {
/* 1365 */         String expStr = str.substring(i);
/*      */         try {
/* 1368 */           exp = Integer.parseInt(expStr);
/* 1370 */         } catch (NumberFormatException ex) {
/* 1371 */           throw new NumberFormatException("Invalid exponent " + expStr + " in string " + str);
/*      */         } 
/*      */         break;
/*      */       } 
/* 1375 */       throw new NumberFormatException("Unexpected character '" + ch + "' at position " + i + " in string " + str);
/*      */     } 
/* 1379 */     DD val2 = val;
/* 1382 */     int numDecPlaces = numDigits - numBeforeDec - exp;
/* 1383 */     if (numDecPlaces == 0) {
/* 1384 */       val2 = val;
/* 1386 */     } else if (numDecPlaces > 0) {
/* 1387 */       DD scale = TEN.pow(numDecPlaces);
/* 1388 */       val2 = val.divide(scale);
/* 1390 */     } else if (numDecPlaces < 0) {
/* 1391 */       DD scale = TEN.pow(-numDecPlaces);
/* 1392 */       val2 = val.multiply(scale);
/*      */     } 
/* 1395 */     if (isNegative)
/* 1396 */       return val2.negate(); 
/* 1398 */     return val2;
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\math\DD.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */