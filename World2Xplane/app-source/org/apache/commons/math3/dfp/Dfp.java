/*      */ package org.apache.commons.math3.dfp;
/*      */ 
/*      */ import java.util.Arrays;
/*      */ import org.apache.commons.math3.Field;
/*      */ import org.apache.commons.math3.FieldElement;
/*      */ 
/*      */ public class Dfp implements FieldElement<Dfp> {
/*      */   public static final int RADIX = 10000;
/*      */   
/*      */   public static final int MIN_EXP = -32767;
/*      */   
/*      */   public static final int MAX_EXP = 32768;
/*      */   
/*      */   public static final int ERR_SCALE = 32760;
/*      */   
/*      */   public static final byte FINITE = 0;
/*      */   
/*      */   public static final byte INFINITE = 1;
/*      */   
/*      */   public static final byte SNAN = 2;
/*      */   
/*      */   public static final byte QNAN = 3;
/*      */   
/*      */   private static final String NAN_STRING = "NaN";
/*      */   
/*      */   private static final String POS_INFINITY_STRING = "Infinity";
/*      */   
/*      */   private static final String NEG_INFINITY_STRING = "-Infinity";
/*      */   
/*      */   private static final String ADD_TRAP = "add";
/*      */   
/*      */   private static final String MULTIPLY_TRAP = "multiply";
/*      */   
/*      */   private static final String DIVIDE_TRAP = "divide";
/*      */   
/*      */   private static final String SQRT_TRAP = "sqrt";
/*      */   
/*      */   private static final String ALIGN_TRAP = "align";
/*      */   
/*      */   private static final String TRUNC_TRAP = "trunc";
/*      */   
/*      */   private static final String NEXT_AFTER_TRAP = "nextAfter";
/*      */   
/*      */   private static final String LESS_THAN_TRAP = "lessThan";
/*      */   
/*      */   private static final String GREATER_THAN_TRAP = "greaterThan";
/*      */   
/*      */   private static final String NEW_INSTANCE_TRAP = "newInstance";
/*      */   
/*      */   protected int[] mant;
/*      */   
/*      */   protected byte sign;
/*      */   
/*      */   protected int exp;
/*      */   
/*      */   protected byte nans;
/*      */   
/*      */   private final DfpField field;
/*      */   
/*      */   protected Dfp(DfpField field) {
/*  182 */     this.mant = new int[field.getRadixDigits()];
/*  183 */     this.sign = 1;
/*  184 */     this.exp = 0;
/*  185 */     this.nans = 0;
/*  186 */     this.field = field;
/*      */   }
/*      */   
/*      */   protected Dfp(DfpField field, byte x) {
/*  194 */     this(field, x);
/*      */   }
/*      */   
/*      */   protected Dfp(DfpField field, int x) {
/*  202 */     this(field, x);
/*      */   }
/*      */   
/*      */   protected Dfp(DfpField field, long x) {
/*  212 */     this.mant = new int[field.getRadixDigits()];
/*  213 */     this.nans = 0;
/*  214 */     this.field = field;
/*  216 */     boolean isLongMin = false;
/*  217 */     if (x == Long.MIN_VALUE) {
/*  220 */       isLongMin = true;
/*  221 */       x++;
/*      */     } 
/*  225 */     if (x < 0L) {
/*  226 */       this.sign = -1;
/*  227 */       x = -x;
/*      */     } else {
/*  229 */       this.sign = 1;
/*      */     } 
/*  232 */     this.exp = 0;
/*  233 */     while (x != 0L) {
/*  234 */       System.arraycopy(this.mant, this.mant.length - this.exp, this.mant, this.mant.length - 1 - this.exp, this.exp);
/*  235 */       this.mant[this.mant.length - 1] = (int)(x % 10000L);
/*  236 */       x /= 10000L;
/*  237 */       this.exp++;
/*      */     } 
/*  240 */     if (isLongMin)
/*  243 */       for (int i = 0; i < this.mant.length - 1; i++) {
/*  244 */         if (this.mant[i] != 0) {
/*  245 */           this.mant[i] = this.mant[i] + 1;
/*      */           break;
/*      */         } 
/*      */       }  
/*      */   }
/*      */   
/*      */   protected Dfp(DfpField field, double x) {
/*  259 */     this.mant = new int[field.getRadixDigits()];
/*  260 */     this.sign = 1;
/*  261 */     this.exp = 0;
/*  262 */     this.nans = 0;
/*  263 */     this.field = field;
/*  265 */     long bits = Double.doubleToLongBits(x);
/*  266 */     long mantissa = bits & 0xFFFFFFFFFFFFFL;
/*  267 */     int exponent = (int)((bits & 0x7FF0000000000000L) >> 52L) - 1023;
/*  269 */     if (exponent == -1023) {
/*  271 */       if (x == 0.0D) {
/*  273 */         if ((bits & Long.MIN_VALUE) != 0L)
/*  274 */           this.sign = -1; 
/*      */         return;
/*      */       } 
/*  279 */       exponent++;
/*  282 */       while ((mantissa & 0x10000000000000L) == 0L) {
/*  283 */         exponent--;
/*  284 */         mantissa <<= 1L;
/*      */       } 
/*  286 */       mantissa &= 0xFFFFFFFFFFFFFL;
/*      */     } 
/*  289 */     if (exponent == 1024) {
/*  291 */       if (x != x) {
/*  292 */         this.sign = 1;
/*  293 */         this.nans = 3;
/*  294 */       } else if (x < 0.0D) {
/*  295 */         this.sign = -1;
/*  296 */         this.nans = 1;
/*      */       } else {
/*  298 */         this.sign = 1;
/*  299 */         this.nans = 1;
/*      */       } 
/*      */       return;
/*      */     } 
/*  304 */     Dfp xdfp = new Dfp(field, mantissa);
/*  305 */     xdfp = xdfp.divide(new Dfp(field, 4503599627370496L)).add(field.getOne());
/*  306 */     xdfp = xdfp.multiply(DfpMath.pow(field.getTwo(), exponent));
/*  308 */     if ((bits & Long.MIN_VALUE) != 0L)
/*  309 */       xdfp = xdfp.negate(); 
/*  312 */     System.arraycopy(xdfp.mant, 0, this.mant, 0, this.mant.length);
/*  313 */     this.sign = xdfp.sign;
/*  314 */     this.exp = xdfp.exp;
/*  315 */     this.nans = xdfp.nans;
/*      */   }
/*      */   
/*      */   public Dfp(Dfp d) {
/*  323 */     this.mant = (int[])d.mant.clone();
/*  324 */     this.sign = d.sign;
/*  325 */     this.exp = d.exp;
/*  326 */     this.nans = d.nans;
/*  327 */     this.field = d.field;
/*      */   }
/*      */   
/*      */   protected Dfp(DfpField field, String s) {
/*      */     String fpdecimal;
/*  337 */     this.mant = new int[field.getRadixDigits()];
/*  338 */     this.sign = 1;
/*  339 */     this.exp = 0;
/*  340 */     this.nans = 0;
/*  341 */     this.field = field;
/*  343 */     boolean decimalFound = false;
/*  344 */     int rsize = 4;
/*  345 */     int offset = 4;
/*  346 */     char[] striped = new char[getRadixDigits() * 4 + 8];
/*  349 */     if (s.equals("Infinity")) {
/*  350 */       this.sign = 1;
/*  351 */       this.nans = 1;
/*      */       return;
/*      */     } 
/*  355 */     if (s.equals("-Infinity")) {
/*  356 */       this.sign = -1;
/*  357 */       this.nans = 1;
/*      */       return;
/*      */     } 
/*  361 */     if (s.equals("NaN")) {
/*  362 */       this.sign = 1;
/*  363 */       this.nans = 3;
/*      */       return;
/*      */     } 
/*  368 */     int p = s.indexOf("e");
/*  369 */     if (p == -1)
/*  370 */       p = s.indexOf("E"); 
/*  374 */     int sciexp = 0;
/*  375 */     if (p != -1) {
/*  377 */       fpdecimal = s.substring(0, p);
/*  378 */       String fpexp = s.substring(p + 1);
/*  379 */       boolean negative = false;
/*  381 */       for (int j = 0; j < fpexp.length(); j++) {
/*  383 */         if (fpexp.charAt(j) == '-') {
/*  385 */           negative = true;
/*  388 */         } else if (fpexp.charAt(j) >= '0' && fpexp.charAt(j) <= '9') {
/*  389 */           sciexp = sciexp * 10 + fpexp.charAt(j) - 48;
/*      */         } 
/*      */       } 
/*  393 */       if (negative)
/*  394 */         sciexp = -sciexp; 
/*      */     } else {
/*  398 */       fpdecimal = s;
/*      */     } 
/*  402 */     if (fpdecimal.indexOf("-") != -1)
/*  403 */       this.sign = -1; 
/*  407 */     p = 0;
/*  410 */     int decimalPos = 0;
/*  412 */     while (fpdecimal.charAt(p) < '1' || fpdecimal.charAt(p) > '9') {
/*  416 */       if (decimalFound && fpdecimal.charAt(p) == '0')
/*  417 */         decimalPos--; 
/*  420 */       if (fpdecimal.charAt(p) == '.')
/*  421 */         decimalFound = true; 
/*  424 */       p++;
/*  426 */       if (p == fpdecimal.length())
/*      */         break; 
/*      */     } 
/*  432 */     int q = 4;
/*  433 */     striped[0] = '0';
/*  434 */     striped[1] = '0';
/*  435 */     striped[2] = '0';
/*  436 */     striped[3] = '0';
/*  437 */     int significantDigits = 0;
/*  439 */     while (p != fpdecimal.length()) {
/*  444 */       if (q == this.mant.length * 4 + 4 + 1)
/*      */         break; 
/*  448 */       if (fpdecimal.charAt(p) == '.') {
/*  449 */         decimalFound = true;
/*  450 */         decimalPos = significantDigits;
/*  451 */         p++;
/*      */         continue;
/*      */       } 
/*  455 */       if (fpdecimal.charAt(p) < '0' || fpdecimal.charAt(p) > '9') {
/*  456 */         p++;
/*      */         continue;
/*      */       } 
/*  460 */       striped[q] = fpdecimal.charAt(p);
/*  461 */       q++;
/*  462 */       p++;
/*  463 */       significantDigits++;
/*      */     } 
/*  468 */     if (decimalFound && q != 4) {
/*  470 */       q--;
/*  471 */       while (q != 4) {
/*  474 */         if (striped[q] == '0') {
/*  475 */           significantDigits--;
/*      */           continue;
/*      */         } 
/*      */         break;
/*      */       } 
/*      */     } 
/*  483 */     if (decimalFound && significantDigits == 0)
/*  484 */       decimalPos = 0; 
/*  488 */     if (!decimalFound)
/*  489 */       decimalPos = q - 4; 
/*  493 */     q = 4;
/*  494 */     p = significantDigits - 1 + 4;
/*  496 */     while (p > q && 
/*  497 */       striped[p] == '0')
/*  500 */       p--; 
/*  504 */     int i = (400 - decimalPos - sciexp % 4) % 4;
/*  505 */     q -= i;
/*  506 */     decimalPos += i;
/*  509 */     while (p - q < this.mant.length * 4) {
/*  510 */       for (i = 0; i < 4; i++)
/*  511 */         striped[++p] = '0'; 
/*      */     } 
/*  517 */     for (i = this.mant.length - 1; i >= 0; i--) {
/*  518 */       this.mant[i] = (striped[q] - 48) * 1000 + (striped[q + 1] - 48) * 100 + (striped[q + 2] - 48) * 10 + striped[q + 3] - 48;
/*  522 */       q += 4;
/*      */     } 
/*  526 */     this.exp = (decimalPos + sciexp) / 4;
/*  528 */     if (q < striped.length)
/*  530 */       round((striped[q] - 48) * 1000); 
/*      */   }
/*      */   
/*      */   protected Dfp(DfpField field, byte sign, byte nans) {
/*  542 */     this.field = field;
/*  543 */     this.mant = new int[field.getRadixDigits()];
/*  544 */     this.sign = sign;
/*  545 */     this.exp = 0;
/*  546 */     this.nans = nans;
/*      */   }
/*      */   
/*      */   public Dfp newInstance() {
/*  554 */     return new Dfp(getField());
/*      */   }
/*      */   
/*      */   public Dfp newInstance(byte x) {
/*  562 */     return new Dfp(getField(), x);
/*      */   }
/*      */   
/*      */   public Dfp newInstance(int x) {
/*  570 */     return new Dfp(getField(), x);
/*      */   }
/*      */   
/*      */   public Dfp newInstance(long x) {
/*  578 */     return new Dfp(getField(), x);
/*      */   }
/*      */   
/*      */   public Dfp newInstance(double x) {
/*  586 */     return new Dfp(getField(), x);
/*      */   }
/*      */   
/*      */   public Dfp newInstance(Dfp d) {
/*  597 */     if (this.field.getRadixDigits() != d.field.getRadixDigits()) {
/*  598 */       this.field.setIEEEFlagsBits(1);
/*  599 */       Dfp result = newInstance(getZero());
/*  600 */       result.nans = 3;
/*  601 */       return dotrap(1, "newInstance", d, result);
/*      */     } 
/*  604 */     return new Dfp(d);
/*      */   }
/*      */   
/*      */   public Dfp newInstance(String s) {
/*  614 */     return new Dfp(this.field, s);
/*      */   }
/*      */   
/*      */   public Dfp newInstance(byte sig, byte code) {
/*  624 */     return this.field.newDfp(sig, code);
/*      */   }
/*      */   
/*      */   public DfpField getField() {
/*  635 */     return this.field;
/*      */   }
/*      */   
/*      */   public int getRadixDigits() {
/*  642 */     return this.field.getRadixDigits();
/*      */   }
/*      */   
/*      */   public Dfp getZero() {
/*  649 */     return this.field.getZero();
/*      */   }
/*      */   
/*      */   public Dfp getOne() {
/*  656 */     return this.field.getOne();
/*      */   }
/*      */   
/*      */   public Dfp getTwo() {
/*  663 */     return this.field.getTwo();
/*      */   }
/*      */   
/*      */   protected void shiftLeft() {
/*  669 */     for (int i = this.mant.length - 1; i > 0; i--)
/*  670 */       this.mant[i] = this.mant[i - 1]; 
/*  672 */     this.mant[0] = 0;
/*  673 */     this.exp--;
/*      */   }
/*      */   
/*      */   protected void shiftRight() {
/*  681 */     for (int i = 0; i < this.mant.length - 1; i++)
/*  682 */       this.mant[i] = this.mant[i + 1]; 
/*  684 */     this.mant[this.mant.length - 1] = 0;
/*  685 */     this.exp++;
/*      */   }
/*      */   
/*      */   protected int align(int e) {
/*  697 */     int lostdigit = 0;
/*  698 */     boolean inexact = false;
/*  700 */     int diff = this.exp - e;
/*  702 */     int adiff = diff;
/*  703 */     if (adiff < 0)
/*  704 */       adiff = -adiff; 
/*  707 */     if (diff == 0)
/*  708 */       return 0; 
/*  711 */     if (adiff > this.mant.length + 1) {
/*  713 */       Arrays.fill(this.mant, 0);
/*  714 */       this.exp = e;
/*  716 */       this.field.setIEEEFlagsBits(16);
/*  717 */       dotrap(16, "align", this, this);
/*  719 */       return 0;
/*      */     } 
/*  722 */     for (int i = 0; i < adiff; i++) {
/*  723 */       if (diff < 0) {
/*  728 */         if (lostdigit != 0)
/*  729 */           inexact = true; 
/*  732 */         lostdigit = this.mant[0];
/*  734 */         shiftRight();
/*      */       } else {
/*  736 */         shiftLeft();
/*      */       } 
/*      */     } 
/*  740 */     if (inexact) {
/*  741 */       this.field.setIEEEFlagsBits(16);
/*  742 */       dotrap(16, "align", this, this);
/*      */     } 
/*  745 */     return lostdigit;
/*      */   }
/*      */   
/*      */   public boolean lessThan(Dfp x) {
/*  756 */     if (this.field.getRadixDigits() != x.field.getRadixDigits()) {
/*  757 */       this.field.setIEEEFlagsBits(1);
/*  758 */       Dfp result = newInstance(getZero());
/*  759 */       result.nans = 3;
/*  760 */       dotrap(1, "lessThan", x, result);
/*  761 */       return false;
/*      */     } 
/*  765 */     if (isNaN() || x.isNaN()) {
/*  766 */       this.field.setIEEEFlagsBits(1);
/*  767 */       dotrap(1, "lessThan", x, newInstance(getZero()));
/*  768 */       return false;
/*      */     } 
/*  771 */     return (compare(this, x) < 0);
/*      */   }
/*      */   
/*      */   public boolean greaterThan(Dfp x) {
/*  781 */     if (this.field.getRadixDigits() != x.field.getRadixDigits()) {
/*  782 */       this.field.setIEEEFlagsBits(1);
/*  783 */       Dfp result = newInstance(getZero());
/*  784 */       result.nans = 3;
/*  785 */       dotrap(1, "greaterThan", x, result);
/*  786 */       return false;
/*      */     } 
/*  790 */     if (isNaN() || x.isNaN()) {
/*  791 */       this.field.setIEEEFlagsBits(1);
/*  792 */       dotrap(1, "greaterThan", x, newInstance(getZero()));
/*  793 */       return false;
/*      */     } 
/*  796 */     return (compare(this, x) > 0);
/*      */   }
/*      */   
/*      */   public boolean negativeOrNull() {
/*  804 */     if (isNaN()) {
/*  805 */       this.field.setIEEEFlagsBits(1);
/*  806 */       dotrap(1, "lessThan", this, newInstance(getZero()));
/*  807 */       return false;
/*      */     } 
/*  810 */     return (this.sign < 0 || (this.mant[this.mant.length - 1] == 0 && !isInfinite()));
/*      */   }
/*      */   
/*      */   public boolean strictlyNegative() {
/*  819 */     if (isNaN()) {
/*  820 */       this.field.setIEEEFlagsBits(1);
/*  821 */       dotrap(1, "lessThan", this, newInstance(getZero()));
/*  822 */       return false;
/*      */     } 
/*  825 */     return (this.sign < 0 && (this.mant[this.mant.length - 1] != 0 || isInfinite()));
/*      */   }
/*      */   
/*      */   public boolean positiveOrNull() {
/*  834 */     if (isNaN()) {
/*  835 */       this.field.setIEEEFlagsBits(1);
/*  836 */       dotrap(1, "lessThan", this, newInstance(getZero()));
/*  837 */       return false;
/*      */     } 
/*  840 */     return (this.sign > 0 || (this.mant[this.mant.length - 1] == 0 && !isInfinite()));
/*      */   }
/*      */   
/*      */   public boolean strictlyPositive() {
/*  849 */     if (isNaN()) {
/*  850 */       this.field.setIEEEFlagsBits(1);
/*  851 */       dotrap(1, "lessThan", this, newInstance(getZero()));
/*  852 */       return false;
/*      */     } 
/*  855 */     return (this.sign > 0 && (this.mant[this.mant.length - 1] != 0 || isInfinite()));
/*      */   }
/*      */   
/*      */   public Dfp abs() {
/*  863 */     Dfp result = newInstance(this);
/*  864 */     result.sign = 1;
/*  865 */     return result;
/*      */   }
/*      */   
/*      */   public boolean isInfinite() {
/*  872 */     return (this.nans == 1);
/*      */   }
/*      */   
/*      */   public boolean isNaN() {
/*  879 */     return (this.nans == 3 || this.nans == 2);
/*      */   }
/*      */   
/*      */   public boolean isZero() {
/*  887 */     if (isNaN()) {
/*  888 */       this.field.setIEEEFlagsBits(1);
/*  889 */       dotrap(1, "lessThan", this, newInstance(getZero()));
/*  890 */       return false;
/*      */     } 
/*  893 */     return (this.mant[this.mant.length - 1] == 0 && !isInfinite());
/*      */   }
/*      */   
/*      */   public boolean equals(Object other) {
/*  904 */     if (other instanceof Dfp) {
/*  905 */       Dfp x = (Dfp)other;
/*  906 */       if (isNaN() || x.isNaN() || this.field.getRadixDigits() != x.field.getRadixDigits())
/*  907 */         return false; 
/*  910 */       return (compare(this, x) == 0);
/*      */     } 
/*  913 */     return false;
/*      */   }
/*      */   
/*      */   public int hashCode() {
/*  923 */     return 17 + (this.sign << 8) + (this.nans << 16) + this.exp + Arrays.hashCode(this.mant);
/*      */   }
/*      */   
/*      */   public boolean unequal(Dfp x) {
/*  931 */     if (isNaN() || x.isNaN() || this.field.getRadixDigits() != x.field.getRadixDigits())
/*  932 */       return false; 
/*  935 */     return (greaterThan(x) || lessThan(x));
/*      */   }
/*      */   
/*      */   private static int compare(Dfp a, Dfp b) {
/*  946 */     if (a.mant[a.mant.length - 1] == 0 && b.mant[b.mant.length - 1] == 0 && a.nans == 0 && b.nans == 0)
/*  948 */       return 0; 
/*  951 */     if (a.sign != b.sign) {
/*  952 */       if (a.sign == -1)
/*  953 */         return -1; 
/*  955 */       return 1;
/*      */     } 
/*  960 */     if (a.nans == 1 && b.nans == 0)
/*  961 */       return a.sign; 
/*  964 */     if (a.nans == 0 && b.nans == 1)
/*  965 */       return -b.sign; 
/*  968 */     if (a.nans == 1 && b.nans == 1)
/*  969 */       return 0; 
/*  973 */     if (b.mant[b.mant.length - 1] != 0 && a.mant[b.mant.length - 1] != 0) {
/*  974 */       if (a.exp < b.exp)
/*  975 */         return -a.sign; 
/*  978 */       if (a.exp > b.exp)
/*  979 */         return a.sign; 
/*      */     } 
/*  984 */     for (int i = a.mant.length - 1; i >= 0; i--) {
/*  985 */       if (a.mant[i] > b.mant[i])
/*  986 */         return a.sign; 
/*  989 */       if (a.mant[i] < b.mant[i])
/*  990 */         return -a.sign; 
/*      */     } 
/*  994 */     return 0;
/*      */   }
/*      */   
/*      */   public Dfp rint() {
/* 1004 */     return trunc(DfpField.RoundingMode.ROUND_HALF_EVEN);
/*      */   }
/*      */   
/*      */   public Dfp floor() {
/* 1012 */     return trunc(DfpField.RoundingMode.ROUND_FLOOR);
/*      */   }
/*      */   
/*      */   public Dfp ceil() {
/* 1020 */     return trunc(DfpField.RoundingMode.ROUND_CEIL);
/*      */   }
/*      */   
/*      */   public Dfp remainder(Dfp d) {
/* 1029 */     Dfp result = subtract(divide(d).rint().multiply(d));
/* 1032 */     if (result.mant[this.mant.length - 1] == 0)
/* 1033 */       result.sign = this.sign; 
/* 1036 */     return result;
/*      */   }
/*      */   
/*      */   protected Dfp trunc(DfpField.RoundingMode rmode) {
/*      */     int j;
/* 1045 */     boolean changed = false;
/* 1047 */     if (isNaN())
/* 1048 */       return newInstance(this); 
/* 1051 */     if (this.nans == 1)
/* 1052 */       return newInstance(this); 
/* 1055 */     if (this.mant[this.mant.length - 1] == 0)
/* 1057 */       return newInstance(this); 
/* 1062 */     if (this.exp < 0) {
/* 1063 */       this.field.setIEEEFlagsBits(16);
/* 1064 */       Dfp dfp = newInstance(getZero());
/* 1065 */       dfp = dotrap(16, "trunc", this, dfp);
/* 1066 */       return dfp;
/*      */     } 
/* 1073 */     if (this.exp >= this.mant.length)
/* 1074 */       return newInstance(this); 
/* 1080 */     Dfp result = newInstance(this);
/* 1081 */     for (int i = 0; i < this.mant.length - result.exp; i++) {
/* 1082 */       j = changed | ((result.mant[i] != 0) ? 1 : 0);
/* 1083 */       result.mant[i] = 0;
/*      */     } 
/* 1086 */     if (j != 0) {
/* 1087 */       switch (rmode) {
/*      */         case ROUND_FLOOR:
/* 1089 */           if (result.sign == -1)
/* 1091 */             result = result.add(newInstance(-1)); 
/* 1122 */           this.field.setIEEEFlagsBits(16);
/* 1123 */           result = dotrap(16, "trunc", this, result);
/* 1124 */           return result;
/*      */         case ROUND_CEIL:
/*      */           if (result.sign == 1)
/*      */             result = result.add(getOne()); 
/*      */           this.field.setIEEEFlagsBits(16);
/*      */           result = dotrap(16, "trunc", this, result);
/* 1124 */           return result;
/*      */       } 
/*      */       Dfp half = newInstance("0.5");
/*      */       Dfp a = subtract(result);
/*      */       a.sign = 1;
/*      */       if (a.greaterThan(half)) {
/*      */         a = newInstance(getOne());
/*      */         a.sign = this.sign;
/*      */         result = result.add(a);
/*      */       } 
/*      */       if (a.equals(half) && result.exp > 0 && (result.mant[this.mant.length - result.exp] & 0x1) != 0) {
/*      */         a = newInstance(getOne());
/*      */         a.sign = this.sign;
/*      */         result = result.add(a);
/*      */       } 
/*      */       this.field.setIEEEFlagsBits(16);
/*      */       result = dotrap(16, "trunc", this, result);
/* 1124 */       return result;
/*      */     } 
/* 1127 */     return result;
/*      */   }
/*      */   
/*      */   public int intValue() {
/* 1136 */     int result = 0;
/* 1138 */     Dfp rounded = rint();
/* 1140 */     if (rounded.greaterThan(newInstance(2147483647)))
/* 1141 */       return Integer.MAX_VALUE; 
/* 1144 */     if (rounded.lessThan(newInstance(-2147483648)))
/* 1145 */       return Integer.MIN_VALUE; 
/* 1148 */     for (int i = this.mant.length - 1; i >= this.mant.length - rounded.exp; i--)
/* 1149 */       result = result * 10000 + rounded.mant[i]; 
/* 1152 */     if (rounded.sign == -1)
/* 1153 */       result = -result; 
/* 1156 */     return result;
/*      */   }
/*      */   
/*      */   public int log10K() {
/* 1165 */     return this.exp - 1;
/*      */   }
/*      */   
/*      */   public Dfp power10K(int e) {
/* 1173 */     Dfp d = newInstance(getOne());
/* 1174 */     d.exp = e + 1;
/* 1175 */     return d;
/*      */   }
/*      */   
/*      */   public int log10() {
/* 1182 */     if (this.mant[this.mant.length - 1] > 1000)
/* 1183 */       return this.exp * 4 - 1; 
/* 1185 */     if (this.mant[this.mant.length - 1] > 100)
/* 1186 */       return this.exp * 4 - 2; 
/* 1188 */     if (this.mant[this.mant.length - 1] > 10)
/* 1189 */       return this.exp * 4 - 3; 
/* 1191 */     return this.exp * 4 - 4;
/*      */   }
/*      */   
/*      */   public Dfp power10(int e) {
/* 1199 */     Dfp d = newInstance(getOne());
/* 1201 */     if (e >= 0) {
/* 1202 */       d.exp = e / 4 + 1;
/*      */     } else {
/* 1204 */       d.exp = (e + 1) / 4;
/*      */     } 
/* 1207 */     switch ((e % 4 + 4) % 4) {
/*      */       case 0:
/* 1220 */         return d;
/*      */       case 1:
/*      */         d = d.multiply(10);
/*      */       case 2:
/*      */         d = d.multiply(100);
/*      */     } 
/*      */     d = d.multiply(1000);
/*      */   }
/*      */   
/*      */   protected int complement(int extra) {
/* 1231 */     extra = 10000 - extra;
/* 1232 */     for (int i = 0; i < this.mant.length; i++)
/* 1233 */       this.mant[i] = 10000 - this.mant[i] - 1; 
/* 1236 */     int rh = extra / 10000;
/* 1237 */     extra -= rh * 10000;
/* 1238 */     for (int j = 0; j < this.mant.length; j++) {
/* 1239 */       int r = this.mant[j] + rh;
/* 1240 */       rh = r / 10000;
/* 1241 */       this.mant[j] = r - rh * 10000;
/*      */     } 
/* 1244 */     return extra;
/*      */   }
/*      */   
/*      */   public Dfp add(Dfp x) {
/* 1254 */     if (this.field.getRadixDigits() != x.field.getRadixDigits()) {
/* 1255 */       this.field.setIEEEFlagsBits(1);
/* 1256 */       Dfp dfp = newInstance(getZero());
/* 1257 */       dfp.nans = 3;
/* 1258 */       return dotrap(1, "add", x, dfp);
/*      */     } 
/* 1262 */     if (this.nans != 0 || x.nans != 0) {
/* 1263 */       if (isNaN())
/* 1264 */         return this; 
/* 1267 */       if (x.isNaN())
/* 1268 */         return x; 
/* 1271 */       if (this.nans == 1 && x.nans == 0)
/* 1272 */         return this; 
/* 1275 */       if (x.nans == 1 && this.nans == 0)
/* 1276 */         return x; 
/* 1279 */       if (x.nans == 1 && this.nans == 1 && this.sign == x.sign)
/* 1280 */         return x; 
/* 1283 */       if (x.nans == 1 && this.nans == 1 && this.sign != x.sign) {
/* 1284 */         this.field.setIEEEFlagsBits(1);
/* 1285 */         Dfp dfp = newInstance(getZero());
/* 1286 */         dfp.nans = 3;
/* 1287 */         dfp = dotrap(1, "add", x, dfp);
/* 1288 */         return dfp;
/*      */       } 
/*      */     } 
/* 1293 */     Dfp a = newInstance(this);
/* 1294 */     Dfp b = newInstance(x);
/* 1297 */     Dfp result = newInstance(getZero());
/* 1300 */     byte asign = a.sign;
/* 1301 */     byte bsign = b.sign;
/* 1303 */     a.sign = 1;
/* 1304 */     b.sign = 1;
/* 1307 */     byte rsign = bsign;
/* 1308 */     if (compare(a, b) > 0)
/* 1309 */       rsign = asign; 
/* 1315 */     if (b.mant[this.mant.length - 1] == 0)
/* 1316 */       b.exp = a.exp; 
/* 1319 */     if (a.mant[this.mant.length - 1] == 0)
/* 1320 */       a.exp = b.exp; 
/* 1324 */     int aextradigit = 0;
/* 1325 */     int bextradigit = 0;
/* 1326 */     if (a.exp < b.exp) {
/* 1327 */       aextradigit = a.align(b.exp);
/*      */     } else {
/* 1329 */       bextradigit = b.align(a.exp);
/*      */     } 
/* 1333 */     if (asign != bsign)
/* 1334 */       if (asign == rsign) {
/* 1335 */         bextradigit = b.complement(bextradigit);
/*      */       } else {
/* 1337 */         aextradigit = a.complement(aextradigit);
/*      */       }  
/* 1342 */     int rh = 0;
/*      */     int i;
/* 1343 */     for (i = 0; i < this.mant.length; i++) {
/* 1344 */       int r = a.mant[i] + b.mant[i] + rh;
/* 1345 */       rh = r / 10000;
/* 1346 */       result.mant[i] = r - rh * 10000;
/*      */     } 
/* 1348 */     result.exp = a.exp;
/* 1349 */     result.sign = rsign;
/* 1354 */     if (rh != 0 && asign == bsign) {
/* 1355 */       int lostdigit = result.mant[0];
/* 1356 */       result.shiftRight();
/* 1357 */       result.mant[this.mant.length - 1] = rh;
/* 1358 */       int j = result.round(lostdigit);
/* 1359 */       if (j != 0)
/* 1360 */         result = dotrap(j, "add", x, result); 
/*      */     } 
/* 1365 */     for (i = 0; i < this.mant.length && 
/* 1366 */       result.mant[this.mant.length - 1] == 0; i++) {
/* 1369 */       result.shiftLeft();
/* 1370 */       if (i == 0) {
/* 1371 */         result.mant[0] = aextradigit + bextradigit;
/* 1372 */         aextradigit = 0;
/* 1373 */         bextradigit = 0;
/*      */       } 
/*      */     } 
/* 1378 */     if (result.mant[this.mant.length - 1] == 0) {
/* 1379 */       result.exp = 0;
/* 1381 */       if (asign != bsign)
/* 1383 */         result.sign = 1; 
/*      */     } 
/* 1388 */     int excp = result.round(aextradigit + bextradigit);
/* 1389 */     if (excp != 0)
/* 1390 */       result = dotrap(excp, "add", x, result); 
/* 1393 */     return result;
/*      */   }
/*      */   
/*      */   public Dfp negate() {
/* 1400 */     Dfp result = newInstance(this);
/* 1401 */     result.sign = (byte)-result.sign;
/* 1402 */     return result;
/*      */   }
/*      */   
/*      */   public Dfp subtract(Dfp x) {
/* 1410 */     return add(x.negate());
/*      */   }
/*      */   
/*      */   protected int round(int n) {
/* 1418 */     boolean inc = false;
/* 1419 */     switch (this.field.getRoundingMode()) {
/*      */       case ROUND_DOWN:
/* 1421 */         inc = false;
/*      */         break;
/*      */       case ROUND_UP:
/* 1425 */         inc = (n != 0);
/*      */         break;
/*      */       case ROUND_HALF_UP:
/* 1429 */         inc = (n >= 5000);
/*      */         break;
/*      */       case ROUND_HALF_DOWN:
/* 1433 */         inc = (n > 5000);
/*      */         break;
/*      */       case ROUND_HALF_EVEN:
/* 1437 */         inc = (n > 5000 || (n == 5000 && (this.mant[0] & 0x1) == 1));
/*      */         break;
/*      */       case ROUND_HALF_ODD:
/* 1441 */         inc = (n > 5000 || (n == 5000 && (this.mant[0] & 0x1) == 0));
/*      */         break;
/*      */       case ROUND_CEIL:
/* 1445 */         inc = (this.sign == 1 && n != 0);
/*      */         break;
/*      */       default:
/* 1450 */         inc = (this.sign == -1 && n != 0);
/*      */         break;
/*      */     } 
/* 1454 */     if (inc) {
/* 1456 */       int rh = 1;
/* 1457 */       for (int i = 0; i < this.mant.length; i++) {
/* 1458 */         int r = this.mant[i] + rh;
/* 1459 */         rh = r / 10000;
/* 1460 */         this.mant[i] = r - rh * 10000;
/*      */       } 
/* 1463 */       if (rh != 0) {
/* 1464 */         shiftRight();
/* 1465 */         this.mant[this.mant.length - 1] = rh;
/*      */       } 
/*      */     } 
/* 1470 */     if (this.exp < -32767) {
/* 1472 */       this.field.setIEEEFlagsBits(8);
/* 1473 */       return 8;
/*      */     } 
/* 1476 */     if (this.exp > 32768) {
/* 1478 */       this.field.setIEEEFlagsBits(4);
/* 1479 */       return 4;
/*      */     } 
/* 1482 */     if (n != 0) {
/* 1484 */       this.field.setIEEEFlagsBits(16);
/* 1485 */       return 16;
/*      */     } 
/* 1488 */     return 0;
/*      */   }
/*      */   
/*      */   public Dfp multiply(Dfp x) {
/*      */     int excp;
/* 1499 */     if (this.field.getRadixDigits() != x.field.getRadixDigits()) {
/* 1500 */       this.field.setIEEEFlagsBits(1);
/* 1501 */       Dfp dfp = newInstance(getZero());
/* 1502 */       dfp.nans = 3;
/* 1503 */       return dotrap(1, "multiply", x, dfp);
/*      */     } 
/* 1506 */     Dfp result = newInstance(getZero());
/* 1509 */     if (this.nans != 0 || x.nans != 0) {
/* 1510 */       if (isNaN())
/* 1511 */         return this; 
/* 1514 */       if (x.isNaN())
/* 1515 */         return x; 
/* 1518 */       if (this.nans == 1 && x.nans == 0 && x.mant[this.mant.length - 1] != 0) {
/* 1519 */         result = newInstance(this);
/* 1520 */         result.sign = (byte)(this.sign * x.sign);
/* 1521 */         return result;
/*      */       } 
/* 1524 */       if (x.nans == 1 && this.nans == 0 && this.mant[this.mant.length - 1] != 0) {
/* 1525 */         result = newInstance(x);
/* 1526 */         result.sign = (byte)(this.sign * x.sign);
/* 1527 */         return result;
/*      */       } 
/* 1530 */       if (x.nans == 1 && this.nans == 1) {
/* 1531 */         result = newInstance(this);
/* 1532 */         result.sign = (byte)(this.sign * x.sign);
/* 1533 */         return result;
/*      */       } 
/* 1536 */       if ((x.nans == 1 && this.nans == 0 && this.mant[this.mant.length - 1] == 0) || (this.nans == 1 && x.nans == 0 && x.mant[this.mant.length - 1] == 0)) {
/* 1538 */         this.field.setIEEEFlagsBits(1);
/* 1539 */         result = newInstance(getZero());
/* 1540 */         result.nans = 3;
/* 1541 */         result = dotrap(1, "multiply", x, result);
/* 1542 */         return result;
/*      */       } 
/*      */     } 
/* 1546 */     int[] product = new int[this.mant.length * 2];
/* 1548 */     for (int i = 0; i < this.mant.length; i++) {
/* 1549 */       int rh = 0;
/* 1550 */       for (int k = 0; k < this.mant.length; k++) {
/* 1551 */         int r = this.mant[i] * x.mant[k];
/* 1552 */         r = r + product[i + k] + rh;
/* 1554 */         rh = r / 10000;
/* 1555 */         product[i + k] = r - rh * 10000;
/*      */       } 
/* 1557 */       product[i + this.mant.length] = rh;
/*      */     } 
/* 1561 */     int md = this.mant.length * 2 - 1;
/*      */     int j;
/* 1562 */     for (j = this.mant.length * 2 - 1; j >= 0; j--) {
/* 1563 */       if (product[j] != 0) {
/* 1564 */         md = j;
/*      */         break;
/*      */       } 
/*      */     } 
/* 1570 */     for (j = 0; j < this.mant.length; j++)
/* 1571 */       result.mant[this.mant.length - j - 1] = product[md - j]; 
/* 1575 */     result.exp = this.exp + x.exp + md - 2 * this.mant.length + 1;
/* 1576 */     result.sign = (byte)((this.sign == x.sign) ? 1 : -1);
/* 1578 */     if (result.mant[this.mant.length - 1] == 0)
/* 1580 */       result.exp = 0; 
/* 1584 */     if (md > this.mant.length - 1) {
/* 1585 */       excp = result.round(product[md - this.mant.length]);
/*      */     } else {
/* 1587 */       excp = result.round(0);
/*      */     } 
/* 1590 */     if (excp != 0)
/* 1591 */       result = dotrap(excp, "multiply", x, result); 
/* 1594 */     return result;
/*      */   }
/*      */   
/*      */   public Dfp multiply(int x) {
/* 1604 */     Dfp result = newInstance(this);
/* 1607 */     if (this.nans != 0) {
/* 1608 */       if (isNaN())
/* 1609 */         return this; 
/* 1612 */       if (this.nans == 1 && x != 0) {
/* 1613 */         result = newInstance(this);
/* 1614 */         return result;
/*      */       } 
/* 1617 */       if (this.nans == 1 && x == 0) {
/* 1618 */         this.field.setIEEEFlagsBits(1);
/* 1619 */         result = newInstance(getZero());
/* 1620 */         result.nans = 3;
/* 1621 */         result = dotrap(1, "multiply", newInstance(getZero()), result);
/* 1622 */         return result;
/*      */       } 
/*      */     } 
/* 1627 */     if (x < 0 || x >= 10000) {
/* 1628 */       this.field.setIEEEFlagsBits(1);
/* 1629 */       result = newInstance(getZero());
/* 1630 */       result.nans = 3;
/* 1631 */       result = dotrap(1, "multiply", result, result);
/* 1632 */       return result;
/*      */     } 
/* 1635 */     int rh = 0;
/* 1636 */     for (int i = 0; i < this.mant.length; i++) {
/* 1637 */       int r = this.mant[i] * x + rh;
/* 1638 */       rh = r / 10000;
/* 1639 */       result.mant[i] = r - rh * 10000;
/*      */     } 
/* 1642 */     int lostdigit = 0;
/* 1643 */     if (rh != 0) {
/* 1644 */       lostdigit = result.mant[0];
/* 1645 */       result.shiftRight();
/* 1646 */       result.mant[this.mant.length - 1] = rh;
/*      */     } 
/* 1649 */     if (result.mant[this.mant.length - 1] == 0)
/* 1650 */       result.exp = 0; 
/* 1653 */     int excp = result.round(lostdigit);
/* 1654 */     if (excp != 0)
/* 1655 */       result = dotrap(excp, "multiply", result, result); 
/* 1658 */     return result;
/*      */   }
/*      */   
/*      */   public Dfp divide(Dfp divisor) {
/* 1671 */     int excp, trial = 0;
/* 1674 */     int md = 0;
/* 1678 */     if (this.field.getRadixDigits() != divisor.field.getRadixDigits()) {
/* 1679 */       this.field.setIEEEFlagsBits(1);
/* 1680 */       Dfp dfp = newInstance(getZero());
/* 1681 */       dfp.nans = 3;
/* 1682 */       return dotrap(1, "divide", divisor, dfp);
/*      */     } 
/* 1685 */     Dfp result = newInstance(getZero());
/* 1688 */     if (this.nans != 0 || divisor.nans != 0) {
/* 1689 */       if (isNaN())
/* 1690 */         return this; 
/* 1693 */       if (divisor.isNaN())
/* 1694 */         return divisor; 
/* 1697 */       if (this.nans == 1 && divisor.nans == 0) {
/* 1698 */         result = newInstance(this);
/* 1699 */         result.sign = (byte)(this.sign * divisor.sign);
/* 1700 */         return result;
/*      */       } 
/* 1703 */       if (divisor.nans == 1 && this.nans == 0) {
/* 1704 */         result = newInstance(getZero());
/* 1705 */         result.sign = (byte)(this.sign * divisor.sign);
/* 1706 */         return result;
/*      */       } 
/* 1709 */       if (divisor.nans == 1 && this.nans == 1) {
/* 1710 */         this.field.setIEEEFlagsBits(1);
/* 1711 */         result = newInstance(getZero());
/* 1712 */         result.nans = 3;
/* 1713 */         result = dotrap(1, "divide", divisor, result);
/* 1714 */         return result;
/*      */       } 
/*      */     } 
/* 1719 */     if (divisor.mant[this.mant.length - 1] == 0) {
/* 1720 */       this.field.setIEEEFlagsBits(2);
/* 1721 */       result = newInstance(getZero());
/* 1722 */       result.sign = (byte)(this.sign * divisor.sign);
/* 1723 */       result.nans = 1;
/* 1724 */       result = dotrap(2, "divide", divisor, result);
/* 1725 */       return result;
/*      */     } 
/* 1728 */     int[] dividend = new int[this.mant.length + 1];
/* 1729 */     int[] quotient = new int[this.mant.length + 2];
/* 1730 */     int[] remainder = new int[this.mant.length + 1];
/* 1734 */     dividend[this.mant.length] = 0;
/* 1735 */     quotient[this.mant.length] = 0;
/* 1736 */     quotient[this.mant.length + 1] = 0;
/* 1737 */     remainder[this.mant.length] = 0;
/*      */     int i;
/* 1742 */     for (i = 0; i < this.mant.length; i++) {
/* 1743 */       dividend[i] = this.mant[i];
/* 1744 */       quotient[i] = 0;
/* 1745 */       remainder[i] = 0;
/*      */     } 
/* 1749 */     int nsqd = 0;
/* 1750 */     for (int qd = this.mant.length + 1; qd >= 0; qd--) {
/* 1754 */       int divMsb = dividend[this.mant.length] * 10000 + dividend[this.mant.length - 1];
/* 1755 */       int min = divMsb / (divisor.mant[this.mant.length - 1] + 1);
/* 1756 */       int max = (divMsb + 1) / divisor.mant[this.mant.length - 1];
/* 1758 */       boolean trialgood = false;
/* 1759 */       while (!trialgood) {
/* 1761 */         trial = (min + max) / 2;
/* 1764 */         int rh = 0;
/*      */         int k;
/* 1765 */         for (k = 0; k < this.mant.length + 1; k++) {
/* 1766 */           int dm = (k < this.mant.length) ? divisor.mant[k] : 0;
/* 1767 */           int r = dm * trial + rh;
/* 1768 */           rh = r / 10000;
/* 1769 */           remainder[k] = r - rh * 10000;
/*      */         } 
/* 1773 */         rh = 1;
/* 1774 */         for (k = 0; k < this.mant.length + 1; k++) {
/* 1775 */           int r = 9999 - remainder[k] + dividend[k] + rh;
/* 1776 */           rh = r / 10000;
/* 1777 */           remainder[k] = r - rh * 10000;
/*      */         } 
/* 1781 */         if (rh == 0) {
/* 1783 */           max = trial - 1;
/*      */           continue;
/*      */         } 
/* 1788 */         int minadj = remainder[this.mant.length] * 10000 + remainder[this.mant.length - 1];
/* 1789 */         minadj /= divisor.mant[this.mant.length - 1] + 1;
/* 1791 */         if (minadj >= 2) {
/* 1792 */           min = trial + minadj;
/*      */           continue;
/*      */         } 
/* 1798 */         trialgood = false;
/* 1799 */         for (k = this.mant.length - 1; k >= 0; k--) {
/* 1800 */           if (divisor.mant[k] > remainder[k])
/* 1801 */             trialgood = true; 
/* 1803 */           if (divisor.mant[k] < remainder[k])
/*      */             break; 
/*      */         } 
/* 1808 */         if (remainder[this.mant.length] != 0)
/* 1809 */           trialgood = false; 
/* 1812 */         if (!trialgood)
/* 1813 */           min = trial + 1; 
/*      */       } 
/* 1818 */       quotient[qd] = trial;
/* 1819 */       if (trial != 0 || nsqd != 0)
/* 1820 */         nsqd++; 
/* 1823 */       if (this.field.getRoundingMode() == DfpField.RoundingMode.ROUND_DOWN && nsqd == this.mant.length)
/*      */         break; 
/* 1828 */       if (nsqd > this.mant.length)
/*      */         break; 
/* 1834 */       dividend[0] = 0;
/* 1835 */       for (int j = 0; j < this.mant.length; j++)
/* 1836 */         dividend[j + 1] = remainder[j]; 
/*      */     } 
/* 1841 */     md = this.mant.length;
/* 1842 */     for (i = this.mant.length + 1; i >= 0; i--) {
/* 1843 */       if (quotient[i] != 0) {
/* 1844 */         md = i;
/*      */         break;
/*      */       } 
/*      */     } 
/* 1850 */     for (i = 0; i < this.mant.length; i++)
/* 1851 */       result.mant[this.mant.length - i - 1] = quotient[md - i]; 
/* 1855 */     result.exp = this.exp - divisor.exp + md - this.mant.length;
/* 1856 */     result.sign = (byte)((this.sign == divisor.sign) ? 1 : -1);
/* 1858 */     if (result.mant[this.mant.length - 1] == 0)
/* 1859 */       result.exp = 0; 
/* 1862 */     if (md > this.mant.length - 1) {
/* 1863 */       excp = result.round(quotient[md - this.mant.length]);
/*      */     } else {
/* 1865 */       excp = result.round(0);
/*      */     } 
/* 1868 */     if (excp != 0)
/* 1869 */       result = dotrap(excp, "divide", divisor, result); 
/* 1872 */     return result;
/*      */   }
/*      */   
/*      */   public Dfp divide(int divisor) {
/* 1883 */     if (this.nans != 0) {
/* 1884 */       if (isNaN())
/* 1885 */         return this; 
/* 1888 */       if (this.nans == 1)
/* 1889 */         return newInstance(this); 
/*      */     } 
/* 1894 */     if (divisor == 0) {
/* 1895 */       this.field.setIEEEFlagsBits(2);
/* 1896 */       Dfp dfp = newInstance(getZero());
/* 1897 */       dfp.sign = this.sign;
/* 1898 */       dfp.nans = 1;
/* 1899 */       dfp = dotrap(2, "divide", getZero(), dfp);
/* 1900 */       return dfp;
/*      */     } 
/* 1904 */     if (divisor < 0 || divisor >= 10000) {
/* 1905 */       this.field.setIEEEFlagsBits(1);
/* 1906 */       Dfp dfp = newInstance(getZero());
/* 1907 */       dfp.nans = 3;
/* 1908 */       dfp = dotrap(1, "divide", dfp, dfp);
/* 1909 */       return dfp;
/*      */     } 
/* 1912 */     Dfp result = newInstance(this);
/* 1914 */     int rl = 0;
/* 1915 */     for (int i = this.mant.length - 1; i >= 0; i--) {
/* 1916 */       int r = rl * 10000 + result.mant[i];
/* 1917 */       int rh = r / divisor;
/* 1918 */       rl = r - rh * divisor;
/* 1919 */       result.mant[i] = rh;
/*      */     } 
/* 1922 */     if (result.mant[this.mant.length - 1] == 0) {
/* 1924 */       result.shiftLeft();
/* 1925 */       int r = rl * 10000;
/* 1926 */       int rh = r / divisor;
/* 1927 */       rl = r - rh * divisor;
/* 1928 */       result.mant[0] = rh;
/*      */     } 
/* 1931 */     int excp = result.round(rl * 10000 / divisor);
/* 1932 */     if (excp != 0)
/* 1933 */       result = dotrap(excp, "divide", result, result); 
/* 1936 */     return result;
/*      */   }
/*      */   
/*      */   public Dfp reciprocal() {
/* 1942 */     return this.field.getOne().divide(this);
/*      */   }
/*      */   
/*      */   public Dfp sqrt() {
/* 1951 */     if (this.nans == 0 && this.mant[this.mant.length - 1] == 0)
/* 1953 */       return newInstance(this); 
/* 1956 */     if (this.nans != 0) {
/* 1957 */       if (this.nans == 1 && this.sign == 1)
/* 1959 */         return newInstance(this); 
/* 1962 */       if (this.nans == 3)
/* 1963 */         return newInstance(this); 
/* 1966 */       if (this.nans == 2) {
/* 1969 */         this.field.setIEEEFlagsBits(1);
/* 1970 */         Dfp result = newInstance(this);
/* 1971 */         result = dotrap(1, "sqrt", null, result);
/* 1972 */         return result;
/*      */       } 
/*      */     } 
/* 1976 */     if (this.sign == -1) {
/* 1980 */       this.field.setIEEEFlagsBits(1);
/* 1981 */       Dfp result = newInstance(this);
/* 1982 */       result.nans = 3;
/* 1983 */       result = dotrap(1, "sqrt", null, result);
/* 1984 */       return result;
/*      */     } 
/* 1987 */     Dfp x = newInstance(this);
/* 1990 */     if (x.exp < -1 || x.exp > 1)
/* 1991 */       this.exp /= 2; 
/* 1995 */     switch (x.mant[this.mant.length - 1] / 2000) {
/*      */       case 0:
/* 1997 */         x.mant[this.mant.length - 1] = x.mant[this.mant.length - 1] / 2 + 1;
/*      */         break;
/*      */       case 2:
/* 2000 */         x.mant[this.mant.length - 1] = 1500;
/*      */         break;
/*      */       case 3:
/* 2003 */         x.mant[this.mant.length - 1] = 2200;
/*      */         break;
/*      */       default:
/* 2006 */         x.mant[this.mant.length - 1] = 3000;
/*      */         break;
/*      */     } 
/* 2009 */     Dfp dx = newInstance(x);
/* 2014 */     Dfp px = getZero();
/* 2015 */     Dfp ppx = getZero();
/* 2016 */     while (x.unequal(px)) {
/* 2017 */       dx = newInstance(x);
/* 2018 */       dx.sign = -1;
/* 2019 */       dx = dx.add(divide(x));
/* 2020 */       dx = dx.divide(2);
/* 2021 */       ppx = px;
/* 2022 */       px = x;
/* 2023 */       x = x.add(dx);
/* 2025 */       if (x.equals(ppx))
/*      */         break; 
/* 2032 */       if (dx.mant[this.mant.length - 1] == 0)
/*      */         break; 
/*      */     } 
/* 2037 */     return x;
/*      */   }
/*      */   
/*      */   public String toString() {
/* 2046 */     if (this.nans != 0) {
/* 2048 */       if (this.nans == 1)
/* 2049 */         return (this.sign < 0) ? "-Infinity" : "Infinity"; 
/* 2051 */       return "NaN";
/*      */     } 
/* 2055 */     if (this.exp > this.mant.length || this.exp < -1)
/* 2056 */       return dfp2sci(); 
/* 2059 */     return dfp2string();
/*      */   }
/*      */   
/*      */   protected String dfp2sci() {
/* 2067 */     char[] rawdigits = new char[this.mant.length * 4];
/* 2068 */     char[] outputbuffer = new char[this.mant.length * 4 + 20];
/* 2076 */     int p = 0;
/* 2077 */     for (int i = this.mant.length - 1; i >= 0; i--) {
/* 2078 */       rawdigits[p++] = (char)(this.mant[i] / 1000 + 48);
/* 2079 */       rawdigits[p++] = (char)(this.mant[i] / 100 % 10 + 48);
/* 2080 */       rawdigits[p++] = (char)(this.mant[i] / 10 % 10 + 48);
/* 2081 */       rawdigits[p++] = (char)(this.mant[i] % 10 + 48);
/*      */     } 
/* 2085 */     for (p = 0; p < rawdigits.length && 
/* 2086 */       rawdigits[p] == '0'; p++);
/* 2090 */     int shf = p;
/* 2093 */     int q = 0;
/* 2094 */     if (this.sign == -1)
/* 2095 */       outputbuffer[q++] = '-'; 
/* 2098 */     if (p != rawdigits.length) {
/* 2100 */       outputbuffer[q++] = rawdigits[p++];
/* 2101 */       outputbuffer[q++] = '.';
/* 2103 */       while (p < rawdigits.length)
/* 2104 */         outputbuffer[q++] = rawdigits[p++]; 
/*      */     } else {
/* 2107 */       outputbuffer[q++] = '0';
/* 2108 */       outputbuffer[q++] = '.';
/* 2109 */       outputbuffer[q++] = '0';
/* 2110 */       outputbuffer[q++] = 'e';
/* 2111 */       outputbuffer[q++] = '0';
/* 2112 */       return new String(outputbuffer, 0, 5);
/*      */     } 
/* 2115 */     outputbuffer[q++] = 'e';
/* 2119 */     int e = this.exp * 4 - shf - 1;
/* 2120 */     int ae = e;
/* 2121 */     if (e < 0)
/* 2122 */       ae = -e; 
/* 2126 */     for (p = 1000000000; p > ae; p /= 10);
/* 2130 */     if (e < 0)
/* 2131 */       outputbuffer[q++] = '-'; 
/* 2134 */     while (p > 0) {
/* 2135 */       outputbuffer[q++] = (char)(ae / p + 48);
/* 2136 */       ae %= p;
/* 2137 */       p /= 10;
/*      */     } 
/* 2140 */     return new String(outputbuffer, 0, q);
/*      */   }
/*      */   
/*      */   protected String dfp2string() {
/* 2148 */     char[] buffer = new char[this.mant.length * 4 + 20];
/* 2149 */     int p = 1;
/* 2151 */     int e = this.exp;
/* 2152 */     boolean pointInserted = false;
/* 2154 */     buffer[0] = ' ';
/* 2156 */     if (e <= 0) {
/* 2157 */       buffer[p++] = '0';
/* 2158 */       buffer[p++] = '.';
/* 2159 */       pointInserted = true;
/*      */     } 
/* 2162 */     while (e < 0) {
/* 2163 */       buffer[p++] = '0';
/* 2164 */       buffer[p++] = '0';
/* 2165 */       buffer[p++] = '0';
/* 2166 */       buffer[p++] = '0';
/* 2167 */       e++;
/*      */     } 
/* 2170 */     for (int i = this.mant.length - 1; i >= 0; i--) {
/* 2171 */       buffer[p++] = (char)(this.mant[i] / 1000 + 48);
/* 2172 */       buffer[p++] = (char)(this.mant[i] / 100 % 10 + 48);
/* 2173 */       buffer[p++] = (char)(this.mant[i] / 10 % 10 + 48);
/* 2174 */       buffer[p++] = (char)(this.mant[i] % 10 + 48);
/* 2175 */       if (--e == 0) {
/* 2176 */         buffer[p++] = '.';
/* 2177 */         pointInserted = true;
/*      */       } 
/*      */     } 
/* 2181 */     while (e > 0) {
/* 2182 */       buffer[p++] = '0';
/* 2183 */       buffer[p++] = '0';
/* 2184 */       buffer[p++] = '0';
/* 2185 */       buffer[p++] = '0';
/* 2186 */       e--;
/*      */     } 
/* 2189 */     if (!pointInserted)
/* 2191 */       buffer[p++] = '.'; 
/* 2195 */     int q = 1;
/* 2196 */     while (buffer[q] == '0')
/* 2197 */       q++; 
/* 2199 */     if (buffer[q] == '.')
/* 2200 */       q--; 
/* 2204 */     while (buffer[p - 1] == '0')
/* 2205 */       p--; 
/* 2209 */     if (this.sign < 0)
/* 2210 */       buffer[--q] = '-'; 
/* 2213 */     return new String(buffer, q, p - q);
/*      */   }
/*      */   
/*      */   public Dfp dotrap(int type, String what, Dfp oper, Dfp result) {
/* 2225 */     Dfp def = result;
/* 2227 */     switch (type) {
/*      */       case 1:
/* 2229 */         def = newInstance(getZero());
/* 2230 */         def.sign = result.sign;
/* 2231 */         def.nans = 3;
/* 2279 */         return trap(type, what, oper, def, result);
/*      */       case 2:
/*      */         if (this.nans == 0 && this.mant[this.mant.length - 1] != 0) {
/*      */           def = newInstance(getZero());
/*      */           def.sign = (byte)(this.sign * oper.sign);
/*      */           def.nans = 1;
/*      */         } 
/*      */         if (this.nans == 0 && this.mant[this.mant.length - 1] == 0) {
/*      */           def = newInstance(getZero());
/*      */           def.nans = 3;
/*      */         } 
/*      */         if (this.nans == 1 || this.nans == 3) {
/*      */           def = newInstance(getZero());
/*      */           def.nans = 3;
/*      */         } 
/*      */         if (this.nans == 1 || this.nans == 2) {
/*      */           def = newInstance(getZero());
/*      */           def.nans = 3;
/*      */         } 
/* 2279 */         return trap(type, what, oper, def, result);
/*      */       case 8:
/*      */         if (result.exp + this.mant.length < -32767) {
/*      */           def = newInstance(getZero());
/*      */           def.sign = result.sign;
/*      */         } else {
/*      */           def = newInstance(result);
/*      */         } 
/*      */         result.exp += 32760;
/* 2279 */         return trap(type, what, oper, def, result);
/*      */       case 4:
/*      */         result.exp -= 32760;
/*      */         def = newInstance(getZero());
/*      */         def.sign = result.sign;
/*      */         def.nans = 1;
/* 2279 */         return trap(type, what, oper, def, result);
/*      */     } 
/*      */     def = result;
/* 2279 */     return trap(type, what, oper, def, result);
/*      */   }
/*      */   
/*      */   protected Dfp trap(int type, String what, Dfp oper, Dfp def, Dfp result) {
/* 2295 */     return def;
/*      */   }
/*      */   
/*      */   public int classify() {
/* 2302 */     return this.nans;
/*      */   }
/*      */   
/*      */   public static Dfp copysign(Dfp x, Dfp y) {
/* 2312 */     Dfp result = x.newInstance(x);
/* 2313 */     result.sign = y.sign;
/* 2314 */     return result;
/*      */   }
/*      */   
/*      */   public Dfp nextAfter(Dfp x) {
/*      */     Dfp result;
/* 2325 */     if (this.field.getRadixDigits() != x.field.getRadixDigits()) {
/* 2326 */       this.field.setIEEEFlagsBits(1);
/* 2327 */       Dfp dfp = newInstance(getZero());
/* 2328 */       dfp.nans = 3;
/* 2329 */       return dotrap(1, "nextAfter", x, dfp);
/*      */     } 
/* 2333 */     boolean up = false;
/* 2334 */     if (lessThan(x))
/* 2335 */       up = true; 
/* 2338 */     if (compare(this, x) == 0)
/* 2339 */       return newInstance(x); 
/* 2342 */     if (lessThan(getZero()))
/* 2343 */       up = !up; 
/* 2348 */     if (up) {
/* 2349 */       Dfp inc = newInstance(getOne());
/* 2350 */       inc.exp = this.exp - this.mant.length + 1;
/* 2351 */       inc.sign = this.sign;
/* 2353 */       if (equals(getZero()))
/* 2354 */         inc.exp = -32767 - this.mant.length; 
/* 2357 */       result = add(inc);
/*      */     } else {
/* 2359 */       Dfp inc = newInstance(getOne());
/* 2360 */       inc.exp = this.exp;
/* 2361 */       inc.sign = this.sign;
/* 2363 */       if (equals(inc)) {
/* 2364 */         this.exp -= this.mant.length;
/*      */       } else {
/* 2366 */         inc.exp = this.exp - this.mant.length + 1;
/*      */       } 
/* 2369 */       if (equals(getZero()))
/* 2370 */         inc.exp = -32767 - this.mant.length; 
/* 2373 */       result = subtract(inc);
/*      */     } 
/* 2376 */     if (result.classify() == 1 && classify() != 1) {
/* 2377 */       this.field.setIEEEFlagsBits(16);
/* 2378 */       result = dotrap(16, "nextAfter", x, result);
/*      */     } 
/* 2381 */     if (result.equals(getZero()) && !equals(getZero())) {
/* 2382 */       this.field.setIEEEFlagsBits(16);
/* 2383 */       result = dotrap(16, "nextAfter", x, result);
/*      */     } 
/* 2386 */     return result;
/*      */   }
/*      */   
/*      */   public double toDouble() {
/* 2396 */     if (isInfinite()) {
/* 2397 */       if (lessThan(getZero()))
/* 2398 */         return Double.NEGATIVE_INFINITY; 
/* 2400 */       return Double.POSITIVE_INFINITY;
/*      */     } 
/* 2404 */     if (isNaN())
/* 2405 */       return Double.NaN; 
/* 2408 */     Dfp y = this;
/* 2409 */     boolean negate = false;
/* 2410 */     int cmp0 = compare(this, getZero());
/* 2411 */     if (cmp0 == 0)
/* 2412 */       return (this.sign < 0) ? -0.0D : 0.0D; 
/* 2413 */     if (cmp0 < 0) {
/* 2414 */       y = negate();
/* 2415 */       negate = true;
/*      */     } 
/* 2420 */     int exponent = (int)(y.log10() * 3.32D);
/* 2421 */     if (exponent < 0)
/* 2422 */       exponent--; 
/* 2425 */     Dfp tempDfp = DfpMath.pow(getTwo(), exponent);
/* 2426 */     while (tempDfp.lessThan(y) || tempDfp.equals(y)) {
/* 2427 */       tempDfp = tempDfp.multiply(2);
/* 2428 */       exponent++;
/*      */     } 
/* 2430 */     exponent--;
/* 2434 */     y = y.divide(DfpMath.pow(getTwo(), exponent));
/* 2435 */     if (exponent > -1023)
/* 2436 */       y = y.subtract(getOne()); 
/* 2439 */     if (exponent < -1074)
/* 2440 */       return 0.0D; 
/* 2443 */     if (exponent > 1023)
/* 2444 */       return negate ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY; 
/* 2448 */     y = y.multiply(newInstance(4503599627370496L)).rint();
/* 2449 */     String str = y.toString();
/* 2450 */     str = str.substring(0, str.length() - 1);
/* 2451 */     long mantissa = Long.parseLong(str);
/* 2453 */     if (mantissa == 4503599627370496L) {
/* 2455 */       mantissa = 0L;
/* 2456 */       exponent++;
/*      */     } 
/* 2460 */     if (exponent <= -1023)
/* 2461 */       exponent--; 
/* 2464 */     while (exponent < -1023) {
/* 2465 */       exponent++;
/* 2466 */       mantissa >>>= 1L;
/*      */     } 
/* 2469 */     long bits = mantissa | exponent + 1023L << 52L;
/* 2470 */     double x = Double.longBitsToDouble(bits);
/* 2472 */     if (negate)
/* 2473 */       x = -x; 
/* 2476 */     return x;
/*      */   }
/*      */   
/*      */   public double[] toSplitDouble() {
/* 2485 */     double[] split = new double[2];
/* 2486 */     long mask = -1073741824L;
/* 2488 */     split[0] = Double.longBitsToDouble(Double.doubleToLongBits(toDouble()) & mask);
/* 2489 */     split[1] = subtract(newInstance(split[0])).toDouble();
/* 2491 */     return split;
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\dfp\Dfp.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */