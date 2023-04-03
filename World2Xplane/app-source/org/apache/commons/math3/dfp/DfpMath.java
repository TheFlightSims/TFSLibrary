/*     */ package org.apache.commons.math3.dfp;
/*     */ 
/*     */ public class DfpMath {
/*     */   private static final String POW_TRAP = "pow";
/*     */   
/*     */   protected static Dfp[] split(DfpField field, String a) {
/*  46 */     Dfp[] result = new Dfp[2];
/*  48 */     boolean leading = true;
/*  49 */     int sp = 0;
/*  50 */     int sig = 0;
/*  52 */     char[] buf = new char[a.length()];
/*     */     int i;
/*  54 */     for (i = 0; i < buf.length; i++) {
/*  55 */       buf[i] = a.charAt(i);
/*  57 */       if (buf[i] >= '1' && buf[i] <= '9')
/*  58 */         leading = false; 
/*  61 */       if (buf[i] == '.') {
/*  62 */         sig += (400 - sig) % 4;
/*  63 */         leading = false;
/*     */       } 
/*  66 */       if (sig == field.getRadixDigits() / 2 * 4) {
/*  67 */         sp = i;
/*     */         break;
/*     */       } 
/*  71 */       if (buf[i] >= '0' && buf[i] <= '9' && !leading)
/*  72 */         sig++; 
/*     */     } 
/*  76 */     result[0] = field.newDfp(new String(buf, 0, sp));
/*  78 */     for (i = 0; i < buf.length; i++) {
/*  79 */       buf[i] = a.charAt(i);
/*  80 */       if (buf[i] >= '0' && buf[i] <= '9' && i < sp)
/*  81 */         buf[i] = '0'; 
/*     */     } 
/*  85 */     result[1] = field.newDfp(new String(buf));
/*  87 */     return result;
/*     */   }
/*     */   
/*     */   protected static Dfp[] split(Dfp a) {
/*  95 */     Dfp[] result = new Dfp[2];
/*  96 */     Dfp shift = a.multiply(a.power10K(a.getRadixDigits() / 2));
/*  97 */     result[0] = a.add(shift).subtract(shift);
/*  98 */     result[1] = a.subtract(result[0]);
/*  99 */     return result;
/*     */   }
/*     */   
/*     */   protected static Dfp[] splitMult(Dfp[] a, Dfp[] b) {
/* 111 */     Dfp[] result = new Dfp[2];
/* 113 */     result[1] = a[0].getZero();
/* 114 */     result[0] = a[0].multiply(b[0]);
/* 120 */     if (result[0].classify() == 1 || result[0].equals(result[1]))
/* 121 */       return result; 
/* 124 */     result[1] = a[0].multiply(b[1]).add(a[1].multiply(b[0])).add(a[1].multiply(b[1]));
/* 126 */     return result;
/*     */   }
/*     */   
/*     */   protected static Dfp[] splitDiv(Dfp[] a, Dfp[] b) {
/* 139 */     Dfp[] result = new Dfp[2];
/* 141 */     result[0] = a[0].divide(b[0]);
/* 142 */     result[1] = a[1].multiply(b[0]).subtract(a[0].multiply(b[1]));
/* 143 */     result[1] = result[1].divide(b[0].multiply(b[0]).add(b[0].multiply(b[1])));
/* 145 */     return result;
/*     */   }
/*     */   
/*     */   protected static Dfp splitPow(Dfp[] base, int a) {
/* 154 */     boolean invert = false;
/* 156 */     Dfp[] r = new Dfp[2];
/* 158 */     Dfp[] result = new Dfp[2];
/* 159 */     result[0] = base[0].getOne();
/* 160 */     result[1] = base[0].getZero();
/* 162 */     if (a == 0)
/* 164 */       return result[0].add(result[1]); 
/* 167 */     if (a < 0) {
/* 169 */       invert = true;
/* 170 */       a = -a;
/*     */     } 
/*     */     do {
/*     */       int prevtrial;
/* 175 */       r[0] = new Dfp(base[0]);
/* 176 */       r[1] = new Dfp(base[1]);
/* 177 */       int trial = 1;
/*     */       while (true) {
/* 181 */         prevtrial = trial;
/* 182 */         trial *= 2;
/* 183 */         if (trial > a)
/*     */           break; 
/* 186 */         r = splitMult(r, r);
/*     */       } 
/* 189 */       trial = prevtrial;
/* 191 */       a -= trial;
/* 192 */       result = splitMult(result, r);
/* 194 */     } while (a >= 1);
/* 196 */     result[0] = result[0].add(result[1]);
/* 198 */     if (invert)
/* 199 */       result[0] = base[0].getOne().divide(result[0]); 
/* 202 */     return result[0];
/*     */   }
/*     */   
/*     */   public static Dfp pow(Dfp base, int a) {
/* 213 */     boolean invert = false;
/* 215 */     Dfp result = base.getOne();
/* 217 */     if (a == 0)
/* 219 */       return result; 
/* 222 */     if (a < 0) {
/* 223 */       invert = true;
/* 224 */       a = -a;
/*     */     } 
/*     */     do {
/*     */       Dfp prevr;
/*     */       int prevtrial;
/* 229 */       Dfp r = new Dfp(base);
/* 231 */       int trial = 1;
/*     */       do {
/* 235 */         prevr = new Dfp(r);
/* 236 */         prevtrial = trial;
/* 237 */         r = r.multiply(r);
/* 238 */         trial *= 2;
/* 239 */       } while (a > trial);
/* 241 */       r = prevr;
/* 242 */       trial = prevtrial;
/* 244 */       a -= trial;
/* 245 */       result = result.multiply(r);
/* 247 */     } while (a >= 1);
/* 249 */     if (invert)
/* 250 */       result = base.getOne().divide(result); 
/* 253 */     return base.newInstance(result);
/*     */   }
/*     */   
/*     */   public static Dfp exp(Dfp a) {
/* 266 */     Dfp inta = a.rint();
/* 267 */     Dfp fraca = a.subtract(inta);
/* 269 */     int ia = inta.intValue();
/* 270 */     if (ia > 2147483646)
/* 272 */       return a.newInstance((byte)1, (byte)1); 
/* 275 */     if (ia < -2147483646)
/* 277 */       return a.newInstance(); 
/* 280 */     Dfp einta = splitPow(a.getField().getESplit(), ia);
/* 281 */     Dfp efraca = expInternal(fraca);
/* 283 */     return einta.multiply(efraca);
/*     */   }
/*     */   
/*     */   protected static Dfp expInternal(Dfp a) {
/* 292 */     Dfp y = a.getOne();
/* 293 */     Dfp x = a.getOne();
/* 294 */     Dfp fact = a.getOne();
/* 295 */     Dfp py = new Dfp(y);
/* 297 */     for (int i = 1; i < 90; i++) {
/* 298 */       x = x.multiply(a);
/* 299 */       fact = fact.divide(i);
/* 300 */       y = y.add(x.multiply(fact));
/* 301 */       if (y.equals(py))
/*     */         break; 
/* 304 */       py = new Dfp(y);
/*     */     } 
/* 307 */     return y;
/*     */   }
/*     */   
/*     */   public static Dfp log(Dfp a) {
/* 321 */     int p2 = 0;
/* 324 */     if (a.equals(a.getZero()) || a.lessThan(a.getZero()) || a.isNaN()) {
/* 326 */       a.getField().setIEEEFlagsBits(1);
/* 327 */       return a.dotrap(1, "ln", a, a.newInstance((byte)1, (byte)3));
/*     */     } 
/* 330 */     if (a.classify() == 1)
/* 331 */       return a; 
/* 334 */     Dfp x = new Dfp(a);
/* 335 */     int lr = x.log10K();
/* 337 */     x = x.divide(pow(a.newInstance(10000), lr));
/* 338 */     int ix = x.floor().intValue();
/* 340 */     while (ix > 2) {
/* 341 */       ix >>= 1;
/* 342 */       p2++;
/*     */     } 
/* 346 */     Dfp[] spx = split(x);
/* 347 */     Dfp[] spy = new Dfp[2];
/* 348 */     spy[0] = pow(a.getTwo(), p2);
/* 349 */     spx[0] = spx[0].divide(spy[0]);
/* 350 */     spx[1] = spx[1].divide(spy[0]);
/* 352 */     spy[0] = a.newInstance("1.33333");
/* 353 */     while (spx[0].add(spx[1]).greaterThan(spy[0])) {
/* 354 */       spx[0] = spx[0].divide(2);
/* 355 */       spx[1] = spx[1].divide(2);
/* 356 */       p2++;
/*     */     } 
/* 360 */     Dfp[] spz = logInternal(spx);
/* 362 */     spx[0] = a.newInstance(p2 + 4 * lr);
/* 363 */     spx[1] = a.getZero();
/* 364 */     spy = splitMult(a.getField().getLn2Split(), spx);
/* 366 */     spz[0] = spz[0].add(spy[0]);
/* 367 */     spz[1] = spz[1].add(spy[1]);
/* 369 */     spx[0] = a.newInstance(4 * lr);
/* 370 */     spx[1] = a.getZero();
/* 371 */     spy = splitMult(a.getField().getLn5Split(), spx);
/* 373 */     spz[0] = spz[0].add(spy[0]);
/* 374 */     spz[1] = spz[1].add(spy[1]);
/* 376 */     return a.newInstance(spz[0].add(spz[1]));
/*     */   }
/*     */   
/*     */   protected static Dfp[] logInternal(Dfp[] a) {
/* 440 */     Dfp t = a[0].divide(4).add(a[1].divide(4));
/* 441 */     Dfp x = t.add(a[0].newInstance("-0.25")).divide(t.add(a[0].newInstance("0.25")));
/* 443 */     Dfp y = new Dfp(x);
/* 444 */     Dfp num = new Dfp(x);
/* 445 */     Dfp py = new Dfp(y);
/* 446 */     int den = 1;
/* 447 */     for (int i = 0; i < 10000; i++) {
/* 448 */       num = num.multiply(x);
/* 449 */       num = num.multiply(x);
/* 450 */       den += 2;
/* 451 */       t = num.divide(den);
/* 452 */       y = y.add(t);
/* 453 */       if (y.equals(py))
/*     */         break; 
/* 456 */       py = new Dfp(y);
/*     */     } 
/* 459 */     y = y.multiply(a[0].getTwo());
/* 461 */     return split(y);
/*     */   }
/*     */   
/*     */   public static Dfp pow(Dfp x, Dfp y) {
/*     */     Dfp r;
/* 508 */     if (x.getField().getRadixDigits() != y.getField().getRadixDigits()) {
/* 509 */       x.getField().setIEEEFlagsBits(1);
/* 510 */       Dfp result = x.newInstance(x.getZero());
/* 511 */       result.nans = 3;
/* 512 */       return x.dotrap(1, "pow", x, result);
/*     */     } 
/* 515 */     Dfp zero = x.getZero();
/* 516 */     Dfp one = x.getOne();
/* 517 */     Dfp two = x.getTwo();
/* 518 */     boolean invert = false;
/* 522 */     if (y.equals(zero))
/* 523 */       return x.newInstance(one); 
/* 526 */     if (y.equals(one)) {
/* 527 */       if (x.isNaN()) {
/* 529 */         x.getField().setIEEEFlagsBits(1);
/* 530 */         return x.dotrap(1, "pow", x, x);
/*     */       } 
/* 532 */       return x;
/*     */     } 
/* 535 */     if (x.isNaN() || y.isNaN()) {
/* 537 */       x.getField().setIEEEFlagsBits(1);
/* 538 */       return x.dotrap(1, "pow", x, x.newInstance((byte)1, (byte)3));
/*     */     } 
/* 542 */     if (x.equals(zero)) {
/* 543 */       if (Dfp.copysign(one, x).greaterThan(zero)) {
/* 545 */         if (y.greaterThan(zero))
/* 546 */           return x.newInstance(zero); 
/* 548 */         return x.newInstance(x.newInstance((byte)1, (byte)1));
/*     */       } 
/* 552 */       if (y.classify() == 0 && y.rint().equals(y) && !y.remainder(two).equals(zero)) {
/* 554 */         if (y.greaterThan(zero))
/* 555 */           return x.newInstance(zero.negate()); 
/* 557 */         return x.newInstance(x.newInstance((byte)-1, (byte)1));
/*     */       } 
/* 561 */       if (y.greaterThan(zero))
/* 562 */         return x.newInstance(zero); 
/* 564 */       return x.newInstance(x.newInstance((byte)1, (byte)1));
/*     */     } 
/* 570 */     if (x.lessThan(zero)) {
/* 572 */       x = x.negate();
/* 573 */       invert = true;
/*     */     } 
/* 576 */     if (x.greaterThan(one) && y.classify() == 1) {
/* 577 */       if (y.greaterThan(zero))
/* 578 */         return y; 
/* 580 */       return x.newInstance(zero);
/*     */     } 
/* 584 */     if (x.lessThan(one) && y.classify() == 1) {
/* 585 */       if (y.greaterThan(zero))
/* 586 */         return x.newInstance(zero); 
/* 588 */       return x.newInstance(Dfp.copysign(y, one));
/*     */     } 
/* 592 */     if (x.equals(one) && y.classify() == 1) {
/* 593 */       x.getField().setIEEEFlagsBits(1);
/* 594 */       return x.dotrap(1, "pow", x, x.newInstance((byte)1, (byte)3));
/*     */     } 
/* 597 */     if (x.classify() == 1) {
/* 599 */       if (invert) {
/* 601 */         if (y.classify() == 0 && y.rint().equals(y) && !y.remainder(two).equals(zero)) {
/* 603 */           if (y.greaterThan(zero))
/* 604 */             return x.newInstance(x.newInstance((byte)-1, (byte)1)); 
/* 606 */           return x.newInstance(zero.negate());
/*     */         } 
/* 610 */         if (y.greaterThan(zero))
/* 611 */           return x.newInstance(x.newInstance((byte)1, (byte)1)); 
/* 613 */         return x.newInstance(zero);
/*     */       } 
/* 618 */       if (y.greaterThan(zero))
/* 619 */         return x; 
/* 621 */       return x.newInstance(zero);
/*     */     } 
/* 626 */     if (invert && !y.rint().equals(y)) {
/* 627 */       x.getField().setIEEEFlagsBits(1);
/* 628 */       return x.dotrap(1, "pow", x, x.newInstance((byte)1, (byte)3));
/*     */     } 
/* 634 */     if (y.lessThan(x.newInstance(100000000)) && y.greaterThan(x.newInstance(-100000000))) {
/* 635 */       Dfp u = y.rint();
/* 636 */       int ui = u.intValue();
/* 638 */       Dfp v = y.subtract(u);
/* 640 */       if (v.unequal(zero)) {
/* 641 */         Dfp a = v.multiply(log(x));
/* 642 */         Dfp b = a.divide(x.getField().getLn2()).rint();
/* 644 */         Dfp c = a.subtract(b.multiply(x.getField().getLn2()));
/* 645 */         r = splitPow(split(x), ui);
/* 646 */         r = r.multiply(pow(two, b.intValue()));
/* 647 */         r = r.multiply(exp(c));
/*     */       } else {
/* 649 */         r = splitPow(split(x), ui);
/*     */       } 
/*     */     } else {
/* 653 */       r = exp(log(x).multiply(y));
/*     */     } 
/* 656 */     if (invert)
/* 658 */       if (y.rint().equals(y) && !y.remainder(two).equals(zero))
/* 659 */         r = r.negate();  
/* 663 */     return x.newInstance(r);
/*     */   }
/*     */   
/*     */   protected static Dfp sinInternal(Dfp[] a) {
/* 674 */     Dfp c = a[0].add(a[1]);
/* 675 */     Dfp y = c;
/* 676 */     c = c.multiply(c);
/* 677 */     Dfp x = y;
/* 678 */     Dfp fact = a[0].getOne();
/* 679 */     Dfp py = new Dfp(y);
/* 681 */     for (int i = 3; i < 90; i += 2) {
/* 682 */       x = x.multiply(c);
/* 683 */       x = x.negate();
/* 685 */       fact = fact.divide((i - 1) * i);
/* 686 */       y = y.add(x.multiply(fact));
/* 687 */       if (y.equals(py))
/*     */         break; 
/* 690 */       py = new Dfp(y);
/*     */     } 
/* 693 */     return y;
/*     */   }
/*     */   
/*     */   protected static Dfp cosInternal(Dfp[] a) {
/* 703 */     Dfp one = a[0].getOne();
/* 706 */     Dfp x = one;
/* 707 */     Dfp y = one;
/* 708 */     Dfp c = a[0].add(a[1]);
/* 709 */     c = c.multiply(c);
/* 711 */     Dfp fact = one;
/* 712 */     Dfp py = new Dfp(y);
/* 714 */     for (int i = 2; i < 90; i += 2) {
/* 715 */       x = x.multiply(c);
/* 716 */       x = x.negate();
/* 718 */       fact = fact.divide((i - 1) * i);
/* 720 */       y = y.add(x.multiply(fact));
/* 721 */       if (y.equals(py))
/*     */         break; 
/* 724 */       py = new Dfp(y);
/*     */     } 
/* 727 */     return y;
/*     */   }
/*     */   
/*     */   public static Dfp sin(Dfp a) {
/* 736 */     Dfp y, pi = a.getField().getPi();
/* 737 */     Dfp zero = a.getField().getZero();
/* 738 */     boolean neg = false;
/* 741 */     Dfp x = a.remainder(pi.multiply(2));
/* 745 */     if (x.lessThan(zero)) {
/* 746 */       x = x.negate();
/* 747 */       neg = true;
/*     */     } 
/* 754 */     if (x.greaterThan(pi.divide(2)))
/* 755 */       x = pi.subtract(x); 
/* 759 */     if (x.lessThan(pi.divide(4))) {
/* 760 */       Dfp[] c = new Dfp[2];
/* 761 */       c[0] = x;
/* 762 */       c[1] = zero;
/* 765 */       y = sinInternal(split(x));
/*     */     } else {
/* 767 */       Dfp[] c = new Dfp[2];
/* 768 */       Dfp[] piSplit = a.getField().getPiSplit();
/* 769 */       c[0] = piSplit[0].divide(2).subtract(x);
/* 770 */       c[1] = piSplit[1].divide(2);
/* 771 */       y = cosInternal(c);
/*     */     } 
/* 774 */     if (neg)
/* 775 */       y = y.negate(); 
/* 778 */     return a.newInstance(y);
/*     */   }
/*     */   
/*     */   public static Dfp cos(Dfp a) {
/* 787 */     Dfp y, pi = a.getField().getPi();
/* 788 */     Dfp zero = a.getField().getZero();
/* 789 */     boolean neg = false;
/* 792 */     Dfp x = a.remainder(pi.multiply(2));
/* 796 */     if (x.lessThan(zero))
/* 797 */       x = x.negate(); 
/* 804 */     if (x.greaterThan(pi.divide(2))) {
/* 805 */       x = pi.subtract(x);
/* 806 */       neg = true;
/*     */     } 
/* 810 */     if (x.lessThan(pi.divide(4))) {
/* 811 */       Dfp[] c = new Dfp[2];
/* 812 */       c[0] = x;
/* 813 */       c[1] = zero;
/* 815 */       y = cosInternal(c);
/*     */     } else {
/* 817 */       Dfp[] c = new Dfp[2];
/* 818 */       Dfp[] piSplit = a.getField().getPiSplit();
/* 819 */       c[0] = piSplit[0].divide(2).subtract(x);
/* 820 */       c[1] = piSplit[1].divide(2);
/* 821 */       y = sinInternal(c);
/*     */     } 
/* 824 */     if (neg)
/* 825 */       y = y.negate(); 
/* 828 */     return a.newInstance(y);
/*     */   }
/*     */   
/*     */   public static Dfp tan(Dfp a) {
/* 837 */     return sin(a).divide(cos(a));
/*     */   }
/*     */   
/*     */   protected static Dfp atanInternal(Dfp a) {
/* 846 */     Dfp y = new Dfp(a);
/* 847 */     Dfp x = new Dfp(y);
/* 848 */     Dfp py = new Dfp(y);
/* 850 */     for (int i = 3; i < 90; i += 2) {
/* 851 */       x = x.multiply(a);
/* 852 */       x = x.multiply(a);
/* 853 */       x = x.negate();
/* 854 */       y = y.add(x.divide(i));
/* 855 */       if (y.equals(py))
/*     */         break; 
/* 858 */       py = new Dfp(y);
/*     */     } 
/* 861 */     return y;
/*     */   }
/*     */   
/*     */   public static Dfp atan(Dfp a) {
/* 879 */     Dfp zero = a.getField().getZero();
/* 880 */     Dfp one = a.getField().getOne();
/* 881 */     Dfp[] sqr2Split = a.getField().getSqr2Split();
/* 882 */     Dfp[] piSplit = a.getField().getPiSplit();
/* 883 */     boolean recp = false;
/* 884 */     boolean neg = false;
/* 885 */     boolean sub = false;
/* 887 */     Dfp ty = sqr2Split[0].subtract(one).add(sqr2Split[1]);
/* 889 */     Dfp x = new Dfp(a);
/* 890 */     if (x.lessThan(zero)) {
/* 891 */       neg = true;
/* 892 */       x = x.negate();
/*     */     } 
/* 895 */     if (x.greaterThan(one)) {
/* 896 */       recp = true;
/* 897 */       x = one.divide(x);
/*     */     } 
/* 900 */     if (x.greaterThan(ty)) {
/* 901 */       Dfp[] sty = new Dfp[2];
/* 902 */       sub = true;
/* 904 */       sty[0] = sqr2Split[0].subtract(one);
/* 905 */       sty[1] = sqr2Split[1];
/* 907 */       Dfp[] xs = split(x);
/* 909 */       Dfp[] ds = splitMult(xs, sty);
/* 910 */       ds[0] = ds[0].add(one);
/* 912 */       xs[0] = xs[0].subtract(sty[0]);
/* 913 */       xs[1] = xs[1].subtract(sty[1]);
/* 915 */       xs = splitDiv(xs, ds);
/* 916 */       x = xs[0].add(xs[1]);
/*     */     } 
/* 921 */     Dfp y = atanInternal(x);
/* 923 */     if (sub)
/* 924 */       y = y.add(piSplit[0].divide(8)).add(piSplit[1].divide(8)); 
/* 927 */     if (recp)
/* 928 */       y = piSplit[0].divide(2).subtract(y).add(piSplit[1].divide(2)); 
/* 931 */     if (neg)
/* 932 */       y = y.negate(); 
/* 935 */     return a.newInstance(y);
/*     */   }
/*     */   
/*     */   public static Dfp asin(Dfp a) {
/* 944 */     return atan(a.divide(a.getOne().subtract(a.multiply(a)).sqrt()));
/*     */   }
/*     */   
/*     */   public static Dfp acos(Dfp a) {
/* 953 */     boolean negative = false;
/* 955 */     if (a.lessThan(a.getZero()))
/* 956 */       negative = true; 
/* 959 */     a = Dfp.copysign(a, a.getOne());
/* 961 */     Dfp result = atan(a.getOne().subtract(a.multiply(a)).sqrt().divide(a));
/* 963 */     if (negative)
/* 964 */       result = a.getField().getPi().subtract(result); 
/* 967 */     return a.newInstance(result);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\dfp\DfpMath.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */