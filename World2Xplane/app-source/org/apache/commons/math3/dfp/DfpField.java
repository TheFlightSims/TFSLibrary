/*     */ package org.apache.commons.math3.dfp;
/*     */ 
/*     */ import org.apache.commons.math3.Field;
/*     */ import org.apache.commons.math3.FieldElement;
/*     */ 
/*     */ public class DfpField implements Field<Dfp> {
/*     */   public static final int FLAG_INVALID = 1;
/*     */   
/*     */   public static final int FLAG_DIV_ZERO = 2;
/*     */   
/*     */   public static final int FLAG_OVERFLOW = 4;
/*     */   
/*     */   public static final int FLAG_UNDERFLOW = 8;
/*     */   
/*     */   public static final int FLAG_INEXACT = 16;
/*     */   
/*     */   private static String sqr2String;
/*     */   
/*     */   private static String sqr2ReciprocalString;
/*     */   
/*     */   private static String sqr3String;
/*     */   
/*     */   private static String sqr3ReciprocalString;
/*     */   
/*     */   private static String piString;
/*     */   
/*     */   private static String eString;
/*     */   
/*     */   private static String ln2String;
/*     */   
/*     */   private static String ln5String;
/*     */   
/*     */   private static String ln10String;
/*     */   
/*     */   private final int radixDigits;
/*     */   
/*     */   private final Dfp zero;
/*     */   
/*     */   private final Dfp one;
/*     */   
/*     */   private final Dfp two;
/*     */   
/*     */   private final Dfp sqr2;
/*     */   
/*     */   private final Dfp[] sqr2Split;
/*     */   
/*     */   private final Dfp sqr2Reciprocal;
/*     */   
/*     */   private final Dfp sqr3;
/*     */   
/*     */   private final Dfp sqr3Reciprocal;
/*     */   
/*     */   private final Dfp pi;
/*     */   
/*     */   private final Dfp[] piSplit;
/*     */   
/*     */   private final Dfp e;
/*     */   
/*     */   private final Dfp[] eSplit;
/*     */   
/*     */   private final Dfp ln2;
/*     */   
/*     */   private final Dfp[] ln2Split;
/*     */   
/*     */   private final Dfp ln5;
/*     */   
/*     */   private final Dfp[] ln5Split;
/*     */   
/*     */   private final Dfp ln10;
/*     */   
/*     */   private RoundingMode rMode;
/*     */   
/*     */   private int ieeeFlags;
/*     */   
/*     */   public enum RoundingMode {
/*  33 */     ROUND_DOWN, ROUND_UP, ROUND_HALF_UP, ROUND_HALF_DOWN, ROUND_HALF_EVEN, ROUND_HALF_ODD, ROUND_CEIL, ROUND_FLOOR;
/*     */   }
/*     */   
/*     */   public DfpField(int decimalDigits) {
/* 177 */     this(decimalDigits, true);
/*     */   }
/*     */   
/*     */   private DfpField(int decimalDigits, boolean computeConstants) {
/* 193 */     this.radixDigits = (decimalDigits < 13) ? 4 : ((decimalDigits + 3) / 4);
/* 194 */     this.rMode = RoundingMode.ROUND_HALF_EVEN;
/* 195 */     this.ieeeFlags = 0;
/* 196 */     this.zero = new Dfp(this, 0);
/* 197 */     this.one = new Dfp(this, 1);
/* 198 */     this.two = new Dfp(this, 2);
/* 200 */     if (computeConstants) {
/* 202 */       synchronized (DfpField.class) {
/* 208 */         computeStringConstants((decimalDigits < 67) ? 200 : (3 * decimalDigits));
/* 211 */         this.sqr2 = new Dfp(this, sqr2String);
/* 212 */         this.sqr2Split = split(sqr2String);
/* 213 */         this.sqr2Reciprocal = new Dfp(this, sqr2ReciprocalString);
/* 214 */         this.sqr3 = new Dfp(this, sqr3String);
/* 215 */         this.sqr3Reciprocal = new Dfp(this, sqr3ReciprocalString);
/* 216 */         this.pi = new Dfp(this, piString);
/* 217 */         this.piSplit = split(piString);
/* 218 */         this.e = new Dfp(this, eString);
/* 219 */         this.eSplit = split(eString);
/* 220 */         this.ln2 = new Dfp(this, ln2String);
/* 221 */         this.ln2Split = split(ln2String);
/* 222 */         this.ln5 = new Dfp(this, ln5String);
/* 223 */         this.ln5Split = split(ln5String);
/* 224 */         this.ln10 = new Dfp(this, ln10String);
/*     */       } 
/*     */     } else {
/* 229 */       this.sqr2 = null;
/* 230 */       this.sqr2Split = null;
/* 231 */       this.sqr2Reciprocal = null;
/* 232 */       this.sqr3 = null;
/* 233 */       this.sqr3Reciprocal = null;
/* 234 */       this.pi = null;
/* 235 */       this.piSplit = null;
/* 236 */       this.e = null;
/* 237 */       this.eSplit = null;
/* 238 */       this.ln2 = null;
/* 239 */       this.ln2Split = null;
/* 240 */       this.ln5 = null;
/* 241 */       this.ln5Split = null;
/* 242 */       this.ln10 = null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public int getRadixDigits() {
/* 251 */     return this.radixDigits;
/*     */   }
/*     */   
/*     */   public void setRoundingMode(RoundingMode mode) {
/* 262 */     this.rMode = mode;
/*     */   }
/*     */   
/*     */   public RoundingMode getRoundingMode() {
/* 269 */     return this.rMode;
/*     */   }
/*     */   
/*     */   public int getIEEEFlags() {
/* 284 */     return this.ieeeFlags;
/*     */   }
/*     */   
/*     */   public void clearIEEEFlags() {
/* 298 */     this.ieeeFlags = 0;
/*     */   }
/*     */   
/*     */   public void setIEEEFlags(int flags) {
/* 313 */     this.ieeeFlags = flags & 0x1F;
/*     */   }
/*     */   
/*     */   public void setIEEEFlagsBits(int bits) {
/* 331 */     this.ieeeFlags |= bits & 0x1F;
/*     */   }
/*     */   
/*     */   public Dfp newDfp() {
/* 338 */     return new Dfp(this);
/*     */   }
/*     */   
/*     */   public Dfp newDfp(byte x) {
/* 346 */     return new Dfp(this, x);
/*     */   }
/*     */   
/*     */   public Dfp newDfp(int x) {
/* 354 */     return new Dfp(this, x);
/*     */   }
/*     */   
/*     */   public Dfp newDfp(long x) {
/* 362 */     return new Dfp(this, x);
/*     */   }
/*     */   
/*     */   public Dfp newDfp(double x) {
/* 370 */     return new Dfp(this, x);
/*     */   }
/*     */   
/*     */   public Dfp newDfp(Dfp d) {
/* 378 */     return new Dfp(d);
/*     */   }
/*     */   
/*     */   public Dfp newDfp(String s) {
/* 386 */     return new Dfp(this, s);
/*     */   }
/*     */   
/*     */   public Dfp newDfp(byte sign, byte nans) {
/* 396 */     return new Dfp(this, sign, nans);
/*     */   }
/*     */   
/*     */   public Dfp getZero() {
/* 403 */     return this.zero;
/*     */   }
/*     */   
/*     */   public Dfp getOne() {
/* 410 */     return this.one;
/*     */   }
/*     */   
/*     */   public Class<? extends FieldElement<Dfp>> getRuntimeClass() {
/* 415 */     return (Class)Dfp.class;
/*     */   }
/*     */   
/*     */   public Dfp getTwo() {
/* 422 */     return this.two;
/*     */   }
/*     */   
/*     */   public Dfp getSqr2() {
/* 429 */     return this.sqr2;
/*     */   }
/*     */   
/*     */   public Dfp[] getSqr2Split() {
/* 436 */     return (Dfp[])this.sqr2Split.clone();
/*     */   }
/*     */   
/*     */   public Dfp getSqr2Reciprocal() {
/* 443 */     return this.sqr2Reciprocal;
/*     */   }
/*     */   
/*     */   public Dfp getSqr3() {
/* 450 */     return this.sqr3;
/*     */   }
/*     */   
/*     */   public Dfp getSqr3Reciprocal() {
/* 457 */     return this.sqr3Reciprocal;
/*     */   }
/*     */   
/*     */   public Dfp getPi() {
/* 464 */     return this.pi;
/*     */   }
/*     */   
/*     */   public Dfp[] getPiSplit() {
/* 471 */     return (Dfp[])this.piSplit.clone();
/*     */   }
/*     */   
/*     */   public Dfp getE() {
/* 478 */     return this.e;
/*     */   }
/*     */   
/*     */   public Dfp[] getESplit() {
/* 485 */     return (Dfp[])this.eSplit.clone();
/*     */   }
/*     */   
/*     */   public Dfp getLn2() {
/* 492 */     return this.ln2;
/*     */   }
/*     */   
/*     */   public Dfp[] getLn2Split() {
/* 499 */     return (Dfp[])this.ln2Split.clone();
/*     */   }
/*     */   
/*     */   public Dfp getLn5() {
/* 506 */     return this.ln5;
/*     */   }
/*     */   
/*     */   public Dfp[] getLn5Split() {
/* 513 */     return (Dfp[])this.ln5Split.clone();
/*     */   }
/*     */   
/*     */   public Dfp getLn10() {
/* 520 */     return this.ln10;
/*     */   }
/*     */   
/*     */   private Dfp[] split(String a) {
/* 530 */     Dfp[] result = new Dfp[2];
/* 531 */     boolean leading = true;
/* 532 */     int sp = 0;
/* 533 */     int sig = 0;
/* 535 */     char[] buf = new char[a.length()];
/*     */     int i;
/* 537 */     for (i = 0; i < buf.length; i++) {
/* 538 */       buf[i] = a.charAt(i);
/* 540 */       if (buf[i] >= '1' && buf[i] <= '9')
/* 541 */         leading = false; 
/* 544 */       if (buf[i] == '.') {
/* 545 */         sig += (400 - sig) % 4;
/* 546 */         leading = false;
/*     */       } 
/* 549 */       if (sig == this.radixDigits / 2 * 4) {
/* 550 */         sp = i;
/*     */         break;
/*     */       } 
/* 554 */       if (buf[i] >= '0' && buf[i] <= '9' && !leading)
/* 555 */         sig++; 
/*     */     } 
/* 559 */     result[0] = new Dfp(this, new String(buf, 0, sp));
/* 561 */     for (i = 0; i < buf.length; i++) {
/* 562 */       buf[i] = a.charAt(i);
/* 563 */       if (buf[i] >= '0' && buf[i] <= '9' && i < sp)
/* 564 */         buf[i] = '0'; 
/*     */     } 
/* 568 */     result[1] = new Dfp(this, new String(buf));
/* 570 */     return result;
/*     */   }
/*     */   
/*     */   private static void computeStringConstants(int highPrecisionDecimalDigits) {
/* 578 */     if (sqr2String == null || sqr2String.length() < highPrecisionDecimalDigits - 3) {
/* 581 */       DfpField highPrecisionField = new DfpField(highPrecisionDecimalDigits, false);
/* 582 */       Dfp highPrecisionOne = new Dfp(highPrecisionField, 1);
/* 583 */       Dfp highPrecisionTwo = new Dfp(highPrecisionField, 2);
/* 584 */       Dfp highPrecisionThree = new Dfp(highPrecisionField, 3);
/* 586 */       Dfp highPrecisionSqr2 = highPrecisionTwo.sqrt();
/* 587 */       sqr2String = highPrecisionSqr2.toString();
/* 588 */       sqr2ReciprocalString = highPrecisionOne.divide(highPrecisionSqr2).toString();
/* 590 */       Dfp highPrecisionSqr3 = highPrecisionThree.sqrt();
/* 591 */       sqr3String = highPrecisionSqr3.toString();
/* 592 */       sqr3ReciprocalString = highPrecisionOne.divide(highPrecisionSqr3).toString();
/* 594 */       piString = computePi(highPrecisionOne, highPrecisionTwo, highPrecisionThree).toString();
/* 595 */       eString = computeExp(highPrecisionOne, highPrecisionOne).toString();
/* 596 */       ln2String = computeLn(highPrecisionTwo, highPrecisionOne, highPrecisionTwo).toString();
/* 597 */       ln5String = computeLn(new Dfp(highPrecisionField, 5), highPrecisionOne, highPrecisionTwo).toString();
/* 598 */       ln10String = computeLn(new Dfp(highPrecisionField, 10), highPrecisionOne, highPrecisionTwo).toString();
/*     */     } 
/*     */   }
/*     */   
/*     */   private static Dfp computePi(Dfp one, Dfp two, Dfp three) {
/* 611 */     Dfp sqrt2 = two.sqrt();
/* 612 */     Dfp yk = sqrt2.subtract(one);
/* 613 */     Dfp four = two.add(two);
/* 614 */     Dfp two2kp3 = two;
/* 615 */     Dfp ak = two.multiply(three.subtract(two.multiply(sqrt2)));
/* 623 */     for (int i = 1; i < 20; i++) {
/* 624 */       Dfp ykM1 = yk;
/* 626 */       Dfp y2 = yk.multiply(yk);
/* 627 */       Dfp oneMinusY4 = one.subtract(y2.multiply(y2));
/* 628 */       Dfp s = oneMinusY4.sqrt().sqrt();
/* 629 */       yk = one.subtract(s).divide(one.add(s));
/* 631 */       two2kp3 = two2kp3.multiply(four);
/* 633 */       Dfp p = one.add(yk);
/* 634 */       Dfp p2 = p.multiply(p);
/* 635 */       ak = ak.multiply(p2.multiply(p2)).subtract(two2kp3.multiply(yk).multiply(one.add(yk).add(yk.multiply(yk))));
/* 637 */       if (yk.equals(ykM1))
/*     */         break; 
/*     */     } 
/* 642 */     return one.divide(ak);
/*     */   }
/*     */   
/*     */   public static Dfp computeExp(Dfp a, Dfp one) {
/* 653 */     Dfp y = new Dfp(one);
/* 654 */     Dfp py = new Dfp(one);
/* 655 */     Dfp f = new Dfp(one);
/* 656 */     Dfp fi = new Dfp(one);
/* 657 */     Dfp x = new Dfp(one);
/* 659 */     for (int i = 0; i < 10000; i++) {
/* 660 */       x = x.multiply(a);
/* 661 */       y = y.add(x.divide(f));
/* 662 */       fi = fi.add(one);
/* 663 */       f = f.multiply(fi);
/* 664 */       if (y.equals(py))
/*     */         break; 
/* 667 */       py = new Dfp(y);
/*     */     } 
/* 670 */     return y;
/*     */   }
/*     */   
/*     */   public static Dfp computeLn(Dfp a, Dfp one, Dfp two) {
/* 736 */     int den = 1;
/* 737 */     Dfp x = a.add(new Dfp(a.getField(), -1)).divide(a.add(one));
/* 739 */     Dfp y = new Dfp(x);
/* 740 */     Dfp num = new Dfp(x);
/* 741 */     Dfp py = new Dfp(y);
/* 742 */     for (int i = 0; i < 10000; i++) {
/* 743 */       num = num.multiply(x);
/* 744 */       num = num.multiply(x);
/* 745 */       den += 2;
/* 746 */       Dfp t = num.divide(den);
/* 747 */       y = y.add(t);
/* 748 */       if (y.equals(py))
/*     */         break; 
/* 751 */       py = new Dfp(y);
/*     */     } 
/* 754 */     return y.multiply(two);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\dfp\DfpField.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */