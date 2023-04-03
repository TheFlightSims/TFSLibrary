/*      */ package org.apache.commons.math3.util;
/*      */ 
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collections;
/*      */ import java.util.Comparator;
/*      */ import java.util.List;
/*      */ import org.apache.commons.math3.exception.DimensionMismatchException;
/*      */ import org.apache.commons.math3.exception.MathArithmeticException;
/*      */ import org.apache.commons.math3.exception.MathIllegalArgumentException;
/*      */ import org.apache.commons.math3.exception.MathInternalError;
/*      */ import org.apache.commons.math3.exception.NonMonotonicSequenceException;
/*      */ import org.apache.commons.math3.exception.NullArgumentException;
/*      */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*      */ 
/*      */ public class MathArrays {
/*      */   private static final int SPLIT_FACTOR = 134217729;
/*      */   
/*      */   public static double distance1(double[] p1, double[] p2) {
/*   56 */     double sum = 0.0D;
/*   57 */     for (int i = 0; i < p1.length; i++)
/*   58 */       sum += FastMath.abs(p1[i] - p2[i]); 
/*   60 */     return sum;
/*      */   }
/*      */   
/*      */   public static int distance1(int[] p1, int[] p2) {
/*   71 */     int sum = 0;
/*   72 */     for (int i = 0; i < p1.length; i++)
/*   73 */       sum += FastMath.abs(p1[i] - p2[i]); 
/*   75 */     return sum;
/*      */   }
/*      */   
/*      */   public static double distance(double[] p1, double[] p2) {
/*   86 */     double sum = 0.0D;
/*   87 */     for (int i = 0; i < p1.length; i++) {
/*   88 */       double dp = p1[i] - p2[i];
/*   89 */       sum += dp * dp;
/*      */     } 
/*   91 */     return FastMath.sqrt(sum);
/*      */   }
/*      */   
/*      */   public static double distance(int[] p1, int[] p2) {
/*  102 */     double sum = 0.0D;
/*  103 */     for (int i = 0; i < p1.length; i++) {
/*  104 */       double dp = (p1[i] - p2[i]);
/*  105 */       sum += dp * dp;
/*      */     } 
/*  107 */     return FastMath.sqrt(sum);
/*      */   }
/*      */   
/*      */   public static double distanceInf(double[] p1, double[] p2) {
/*  118 */     double max = 0.0D;
/*  119 */     for (int i = 0; i < p1.length; i++)
/*  120 */       max = FastMath.max(max, FastMath.abs(p1[i] - p2[i])); 
/*  122 */     return max;
/*      */   }
/*      */   
/*      */   public static int distanceInf(int[] p1, int[] p2) {
/*  133 */     int max = 0;
/*  134 */     for (int i = 0; i < p1.length; i++)
/*  135 */       max = FastMath.max(max, FastMath.abs(p1[i] - p2[i])); 
/*  137 */     return max;
/*      */   }
/*      */   
/*      */   public enum OrderDirection {
/*  145 */     INCREASING, DECREASING;
/*      */   }
/*      */   
/*      */   public static boolean isMonotonic(Comparable[] val, OrderDirection dir, boolean strict) {
/*  161 */     Comparable<Comparable> previous = val[0];
/*  162 */     int max = val.length;
/*  163 */     for (int i = 1; i < max; i++) {
/*      */       int comp;
/*  165 */       switch (dir) {
/*      */         case INCREASING:
/*  167 */           comp = previous.compareTo(val[i]);
/*  168 */           if (strict) {
/*  169 */             if (comp >= 0)
/*  170 */               return false; 
/*      */             break;
/*      */           } 
/*  173 */           if (comp > 0)
/*  174 */             return false; 
/*      */           break;
/*      */         case DECREASING:
/*  179 */           comp = val[i].compareTo(previous);
/*  180 */           if (strict) {
/*  181 */             if (comp >= 0)
/*  182 */               return false; 
/*      */             break;
/*      */           } 
/*  185 */           if (comp > 0)
/*  186 */             return false; 
/*      */           break;
/*      */         default:
/*  192 */           throw new MathInternalError();
/*      */       } 
/*  195 */       previous = val[i];
/*      */     } 
/*  197 */     return true;
/*      */   }
/*      */   
/*      */   public static boolean isMonotonic(double[] val, OrderDirection dir, boolean strict) {
/*  211 */     return checkOrder(val, dir, strict, false);
/*      */   }
/*      */   
/*      */   public static boolean checkOrder(double[] val, OrderDirection dir, boolean strict, boolean abort) {
/*  227 */     double previous = val[0];
/*  228 */     int max = val.length;
/*      */     int index;
/*  232 */     for (index = 1; index < max; index++) {
/*  233 */       switch (dir) {
/*      */         case INCREASING:
/*  235 */           if (strict ? (
/*  236 */             val[index] <= previous) : (
/*      */             
/*  240 */             val[index] < previous))
/*      */             break; 
/*      */           break;
/*      */         case DECREASING:
/*  246 */           if (strict ? (
/*  247 */             val[index] >= previous) : (
/*      */             
/*  251 */             val[index] > previous))
/*      */             break; 
/*      */           break;
/*      */         default:
/*  258 */           throw new MathInternalError();
/*      */       } 
/*  261 */       previous = val[index];
/*      */     } 
/*  264 */     if (index == max)
/*  266 */       return true; 
/*  270 */     if (abort)
/*  271 */       throw new NonMonotonicSequenceException(Double.valueOf(val[index]), Double.valueOf(previous), index, dir, strict); 
/*  273 */     return false;
/*      */   }
/*      */   
/*      */   public static void checkOrder(double[] val, OrderDirection dir, boolean strict) {
/*  288 */     checkOrder(val, dir, strict, true);
/*      */   }
/*      */   
/*      */   public static void checkOrder(double[] val) {
/*  299 */     checkOrder(val, OrderDirection.INCREASING, true);
/*      */   }
/*      */   
/*      */   public static double safeNorm(double[] v) {
/*  363 */     double norm, rdwarf = 3.834E-20D;
/*  364 */     double rgiant = 1.304E19D;
/*  365 */     double s1 = 0.0D;
/*  366 */     double s2 = 0.0D;
/*  367 */     double s3 = 0.0D;
/*  368 */     double x1max = 0.0D;
/*  369 */     double x3max = 0.0D;
/*  370 */     double floatn = v.length;
/*  371 */     double agiant = rgiant / floatn;
/*  372 */     for (int i = 0; i < v.length; i++) {
/*  373 */       double xabs = Math.abs(v[i]);
/*  374 */       if (xabs < rdwarf || xabs > agiant) {
/*  375 */         if (xabs > rdwarf) {
/*  376 */           if (xabs > x1max) {
/*  377 */             double r = x1max / xabs;
/*  378 */             s1 = 1.0D + s1 * r * r;
/*  379 */             x1max = xabs;
/*      */           } else {
/*  381 */             double r = xabs / x1max;
/*  382 */             s1 += r * r;
/*      */           } 
/*  385 */         } else if (xabs > x3max) {
/*  386 */           double r = x3max / xabs;
/*  387 */           s3 = 1.0D + s3 * r * r;
/*  388 */           x3max = xabs;
/*  390 */         } else if (xabs != 0.0D) {
/*  391 */           double r = xabs / x3max;
/*  392 */           s3 += r * r;
/*      */         } 
/*      */       } else {
/*  397 */         s2 += xabs * xabs;
/*      */       } 
/*      */     } 
/*  401 */     if (s1 != 0.0D) {
/*  402 */       norm = x1max * Math.sqrt(s1 + s2 / x1max / x1max);
/*  404 */     } else if (s2 == 0.0D) {
/*  405 */       norm = x3max * Math.sqrt(s3);
/*  407 */     } else if (s2 >= x3max) {
/*  408 */       norm = Math.sqrt(s2 * (1.0D + x3max / s2 * x3max * s3));
/*      */     } else {
/*  410 */       norm = Math.sqrt(x3max * (s2 / x3max + x3max * s3));
/*      */     } 
/*  414 */     return norm;
/*      */   }
/*      */   
/*      */   public static void sortInPlace(double[] x, double[]... yList) {
/*  435 */     sortInPlace(x, OrderDirection.INCREASING, yList);
/*      */   }
/*      */   
/*      */   public static void sortInPlace(double[] x, final OrderDirection dir, double[]... yList) {
/*  458 */     if (x == null)
/*  459 */       throw new NullArgumentException(); 
/*  462 */     int len = x.length;
/*  463 */     List<Pair<Double, double[]>> list = new ArrayList<Pair<Double, double[]>>(len);
/*  466 */     int yListLen = yList.length;
/*  467 */     for (int i = 0; i < len; i++) {
/*  468 */       double[] yValues = new double[yListLen];
/*  469 */       for (int k = 0; k < yListLen; k++) {
/*  470 */         double[] y = yList[k];
/*  471 */         if (y == null)
/*  472 */           throw new NullArgumentException(); 
/*  474 */         if (y.length != len)
/*  475 */           throw new DimensionMismatchException(y.length, len); 
/*  477 */         yValues[k] = y[i];
/*      */       } 
/*  479 */       list.add((Pair)new Pair<Double, double>(Double.valueOf(x[i]), yValues));
/*      */     } 
/*  482 */     Comparator<Pair<Double, double[]>> comp = new Comparator<Pair<Double, double[]>>() {
/*      */         public int compare(Pair<Double, double[]> o1, Pair<Double, double[]> o2) {
/*      */           int val;
/*  487 */           switch (dir) {
/*      */             case INCREASING:
/*  489 */               val = ((Double)o1.getKey()).compareTo(o2.getKey());
/*  498 */               return val;
/*      */             case DECREASING:
/*      */               val = ((Double)o2.getKey()).compareTo(o1.getKey());
/*  498 */               return val;
/*      */           } 
/*      */           throw new MathInternalError();
/*      */         }
/*      */       };
/*  502 */     Collections.sort(list, comp);
/*  504 */     for (int j = 0; j < len; j++) {
/*  505 */       Pair<Double, double[]> e = list.get(j);
/*  506 */       x[j] = ((Double)e.getKey()).doubleValue();
/*  507 */       double[] yValues = e.getValue();
/*  508 */       for (int k = 0; k < yListLen; k++)
/*  509 */         yList[k][j] = yValues[k]; 
/*      */     } 
/*      */   }
/*      */   
/*      */   public static int[] copyOf(int[] source) {
/*  521 */     return copyOf(source, source.length);
/*      */   }
/*      */   
/*      */   public static double[] copyOf(double[] source) {
/*  531 */     return copyOf(source, source.length);
/*      */   }
/*      */   
/*      */   public static int[] copyOf(int[] source, int len) {
/*  544 */     int[] output = new int[len];
/*  545 */     System.arraycopy(source, 0, output, 0, FastMath.min(len, source.length));
/*  546 */     return output;
/*      */   }
/*      */   
/*      */   public static double[] copyOf(double[] source, int len) {
/*  559 */     double[] output = new double[len];
/*  560 */     System.arraycopy(source, 0, output, 0, FastMath.min(len, source.length));
/*  561 */     return output;
/*      */   }
/*      */   
/*      */   public static double linearCombination(double[] a, double[] b) {
/*  581 */     int len = a.length;
/*  582 */     if (len != b.length)
/*  583 */       throw new DimensionMismatchException(len, b.length); 
/*  586 */     double[] prodHigh = new double[len];
/*  587 */     double prodLowSum = 0.0D;
/*  589 */     for (int i = 0; i < len; i++) {
/*  590 */       double ai = a[i];
/*  591 */       double ca = 1.34217729E8D * ai;
/*  592 */       double aHigh = ca - ca - ai;
/*  593 */       double aLow = ai - aHigh;
/*  595 */       double bi = b[i];
/*  596 */       double cb = 1.34217729E8D * bi;
/*  597 */       double bHigh = cb - cb - bi;
/*  598 */       double bLow = bi - bHigh;
/*  599 */       prodHigh[i] = ai * bi;
/*  600 */       double prodLow = aLow * bLow - prodHigh[i] - aHigh * bHigh - aLow * bHigh - aHigh * bLow;
/*  604 */       prodLowSum += prodLow;
/*      */     } 
/*  608 */     double prodHighCur = prodHigh[0];
/*  609 */     double prodHighNext = prodHigh[1];
/*  610 */     double sHighPrev = prodHighCur + prodHighNext;
/*  611 */     double sPrime = sHighPrev - prodHighNext;
/*  612 */     double sLowSum = prodHighNext - sHighPrev - sPrime + prodHighCur - sPrime;
/*  614 */     int lenMinusOne = len - 1;
/*  615 */     for (int j = 1; j < lenMinusOne; j++) {
/*  616 */       prodHighNext = prodHigh[j + 1];
/*  617 */       double sHighCur = sHighPrev + prodHighNext;
/*  618 */       sPrime = sHighCur - prodHighNext;
/*  619 */       sLowSum += prodHighNext - sHighCur - sPrime + sHighPrev - sPrime;
/*  620 */       sHighPrev = sHighCur;
/*      */     } 
/*  623 */     double result = sHighPrev + prodLowSum + sLowSum;
/*  625 */     if (Double.isNaN(result)) {
/*  628 */       result = 0.0D;
/*  629 */       for (int k = 0; k < len; k++)
/*  630 */         result += a[k] * b[k]; 
/*      */     } 
/*  634 */     return result;
/*      */   }
/*      */   
/*      */   public static double linearCombination(double a1, double b1, double a2, double b2) {
/*  673 */     double ca1 = 1.34217729E8D * a1;
/*  674 */     double a1High = ca1 - ca1 - a1;
/*  675 */     double a1Low = a1 - a1High;
/*  676 */     double cb1 = 1.34217729E8D * b1;
/*  677 */     double b1High = cb1 - cb1 - b1;
/*  678 */     double b1Low = b1 - b1High;
/*  681 */     double prod1High = a1 * b1;
/*  682 */     double prod1Low = a1Low * b1Low - prod1High - a1High * b1High - a1Low * b1High - a1High * b1Low;
/*  685 */     double ca2 = 1.34217729E8D * a2;
/*  686 */     double a2High = ca2 - ca2 - a2;
/*  687 */     double a2Low = a2 - a2High;
/*  688 */     double cb2 = 1.34217729E8D * b2;
/*  689 */     double b2High = cb2 - cb2 - b2;
/*  690 */     double b2Low = b2 - b2High;
/*  693 */     double prod2High = a2 * b2;
/*  694 */     double prod2Low = a2Low * b2Low - prod2High - a2High * b2High - a2Low * b2High - a2High * b2Low;
/*  697 */     double s12High = prod1High + prod2High;
/*  698 */     double s12Prime = s12High - prod2High;
/*  699 */     double s12Low = prod2High - s12High - s12Prime + prod1High - s12Prime;
/*  703 */     double result = s12High + prod1Low + prod2Low + s12Low;
/*  705 */     if (Double.isNaN(result))
/*  708 */       result = a1 * b1 + a2 * b2; 
/*  711 */     return result;
/*      */   }
/*      */   
/*      */   public static double linearCombination(double a1, double b1, double a2, double b2, double a3, double b3) {
/*  753 */     double ca1 = 1.34217729E8D * a1;
/*  754 */     double a1High = ca1 - ca1 - a1;
/*  755 */     double a1Low = a1 - a1High;
/*  756 */     double cb1 = 1.34217729E8D * b1;
/*  757 */     double b1High = cb1 - cb1 - b1;
/*  758 */     double b1Low = b1 - b1High;
/*  761 */     double prod1High = a1 * b1;
/*  762 */     double prod1Low = a1Low * b1Low - prod1High - a1High * b1High - a1Low * b1High - a1High * b1Low;
/*  765 */     double ca2 = 1.34217729E8D * a2;
/*  766 */     double a2High = ca2 - ca2 - a2;
/*  767 */     double a2Low = a2 - a2High;
/*  768 */     double cb2 = 1.34217729E8D * b2;
/*  769 */     double b2High = cb2 - cb2 - b2;
/*  770 */     double b2Low = b2 - b2High;
/*  773 */     double prod2High = a2 * b2;
/*  774 */     double prod2Low = a2Low * b2Low - prod2High - a2High * b2High - a2Low * b2High - a2High * b2Low;
/*  777 */     double ca3 = 1.34217729E8D * a3;
/*  778 */     double a3High = ca3 - ca3 - a3;
/*  779 */     double a3Low = a3 - a3High;
/*  780 */     double cb3 = 1.34217729E8D * b3;
/*  781 */     double b3High = cb3 - cb3 - b3;
/*  782 */     double b3Low = b3 - b3High;
/*  785 */     double prod3High = a3 * b3;
/*  786 */     double prod3Low = a3Low * b3Low - prod3High - a3High * b3High - a3Low * b3High - a3High * b3Low;
/*  789 */     double s12High = prod1High + prod2High;
/*  790 */     double s12Prime = s12High - prod2High;
/*  791 */     double s12Low = prod2High - s12High - s12Prime + prod1High - s12Prime;
/*  794 */     double s123High = s12High + prod3High;
/*  795 */     double s123Prime = s123High - prod3High;
/*  796 */     double s123Low = prod3High - s123High - s123Prime + s12High - s123Prime;
/*  800 */     double result = s123High + prod1Low + prod2Low + prod3Low + s12Low + s123Low;
/*  802 */     if (Double.isNaN(result))
/*  805 */       result = a1 * b1 + a2 * b2 + a3 * b3; 
/*  808 */     return result;
/*      */   }
/*      */   
/*      */   public static double linearCombination(double a1, double b1, double a2, double b2, double a3, double b3, double a4, double b4) {
/*  855 */     double ca1 = 1.34217729E8D * a1;
/*  856 */     double a1High = ca1 - ca1 - a1;
/*  857 */     double a1Low = a1 - a1High;
/*  858 */     double cb1 = 1.34217729E8D * b1;
/*  859 */     double b1High = cb1 - cb1 - b1;
/*  860 */     double b1Low = b1 - b1High;
/*  863 */     double prod1High = a1 * b1;
/*  864 */     double prod1Low = a1Low * b1Low - prod1High - a1High * b1High - a1Low * b1High - a1High * b1Low;
/*  867 */     double ca2 = 1.34217729E8D * a2;
/*  868 */     double a2High = ca2 - ca2 - a2;
/*  869 */     double a2Low = a2 - a2High;
/*  870 */     double cb2 = 1.34217729E8D * b2;
/*  871 */     double b2High = cb2 - cb2 - b2;
/*  872 */     double b2Low = b2 - b2High;
/*  875 */     double prod2High = a2 * b2;
/*  876 */     double prod2Low = a2Low * b2Low - prod2High - a2High * b2High - a2Low * b2High - a2High * b2Low;
/*  879 */     double ca3 = 1.34217729E8D * a3;
/*  880 */     double a3High = ca3 - ca3 - a3;
/*  881 */     double a3Low = a3 - a3High;
/*  882 */     double cb3 = 1.34217729E8D * b3;
/*  883 */     double b3High = cb3 - cb3 - b3;
/*  884 */     double b3Low = b3 - b3High;
/*  887 */     double prod3High = a3 * b3;
/*  888 */     double prod3Low = a3Low * b3Low - prod3High - a3High * b3High - a3Low * b3High - a3High * b3Low;
/*  891 */     double ca4 = 1.34217729E8D * a4;
/*  892 */     double a4High = ca4 - ca4 - a4;
/*  893 */     double a4Low = a4 - a4High;
/*  894 */     double cb4 = 1.34217729E8D * b4;
/*  895 */     double b4High = cb4 - cb4 - b4;
/*  896 */     double b4Low = b4 - b4High;
/*  899 */     double prod4High = a4 * b4;
/*  900 */     double prod4Low = a4Low * b4Low - prod4High - a4High * b4High - a4Low * b4High - a4High * b4Low;
/*  903 */     double s12High = prod1High + prod2High;
/*  904 */     double s12Prime = s12High - prod2High;
/*  905 */     double s12Low = prod2High - s12High - s12Prime + prod1High - s12Prime;
/*  908 */     double s123High = s12High + prod3High;
/*  909 */     double s123Prime = s123High - prod3High;
/*  910 */     double s123Low = prod3High - s123High - s123Prime + s12High - s123Prime;
/*  913 */     double s1234High = s123High + prod4High;
/*  914 */     double s1234Prime = s1234High - prod4High;
/*  915 */     double s1234Low = prod4High - s1234High - s1234Prime + s123High - s1234Prime;
/*  919 */     double result = s1234High + prod1Low + prod2Low + prod3Low + prod4Low + s12Low + s123Low + s1234Low;
/*  921 */     if (Double.isNaN(result))
/*  924 */       result = a1 * b1 + a2 * b2 + a3 * b3 + a4 * b4; 
/*  927 */     return result;
/*      */   }
/*      */   
/*      */   public static boolean equals(float[] x, float[] y) {
/*  941 */     if (x == null || y == null)
/*  942 */       return ((((x == null) ? 1 : 0) ^ ((y == null) ? 1 : 0)) == 0); 
/*  944 */     if (x.length != y.length)
/*  945 */       return false; 
/*  947 */     for (int i = 0; i < x.length; i++) {
/*  948 */       if (!Precision.equals(x[i], y[i]))
/*  949 */         return false; 
/*      */     } 
/*  952 */     return true;
/*      */   }
/*      */   
/*      */   public static boolean equalsIncludingNaN(float[] x, float[] y) {
/*  967 */     if (x == null || y == null)
/*  968 */       return ((((x == null) ? 1 : 0) ^ ((y == null) ? 1 : 0)) == 0); 
/*  970 */     if (x.length != y.length)
/*  971 */       return false; 
/*  973 */     for (int i = 0; i < x.length; i++) {
/*  974 */       if (!Precision.equalsIncludingNaN(x[i], y[i]))
/*  975 */         return false; 
/*      */     } 
/*  978 */     return true;
/*      */   }
/*      */   
/*      */   public static boolean equals(double[] x, double[] y) {
/*  992 */     if (x == null || y == null)
/*  993 */       return ((((x == null) ? 1 : 0) ^ ((y == null) ? 1 : 0)) == 0); 
/*  995 */     if (x.length != y.length)
/*  996 */       return false; 
/*  998 */     for (int i = 0; i < x.length; i++) {
/*  999 */       if (!Precision.equals(x[i], y[i]))
/* 1000 */         return false; 
/*      */     } 
/* 1003 */     return true;
/*      */   }
/*      */   
/*      */   public static boolean equalsIncludingNaN(double[] x, double[] y) {
/* 1018 */     if (x == null || y == null)
/* 1019 */       return ((((x == null) ? 1 : 0) ^ ((y == null) ? 1 : 0)) == 0); 
/* 1021 */     if (x.length != y.length)
/* 1022 */       return false; 
/* 1024 */     for (int i = 0; i < x.length; i++) {
/* 1025 */       if (!Precision.equalsIncludingNaN(x[i], y[i]))
/* 1026 */         return false; 
/*      */     } 
/* 1029 */     return true;
/*      */   }
/*      */   
/*      */   public static double[] normalizeArray(double[] values, double normalizedSum) {
/* 1055 */     if (Double.isInfinite(normalizedSum))
/* 1056 */       throw new MathIllegalArgumentException(LocalizedFormats.NORMALIZE_INFINITE, new Object[0]); 
/* 1058 */     if (Double.isNaN(normalizedSum))
/* 1059 */       throw new MathIllegalArgumentException(LocalizedFormats.NORMALIZE_NAN, new Object[0]); 
/* 1061 */     double sum = 0.0D;
/* 1062 */     int len = values.length;
/* 1063 */     double[] out = new double[len];
/*      */     int i;
/* 1064 */     for (i = 0; i < len; i++) {
/* 1065 */       if (Double.isInfinite(values[i]))
/* 1066 */         throw new MathIllegalArgumentException(LocalizedFormats.INFINITE_ARRAY_ELEMENT, new Object[] { Double.valueOf(values[i]), Integer.valueOf(i) }); 
/* 1068 */       if (!Double.isNaN(values[i]))
/* 1069 */         sum += values[i]; 
/*      */     } 
/* 1072 */     if (sum == 0.0D)
/* 1073 */       throw new MathArithmeticException(LocalizedFormats.ARRAY_SUMS_TO_ZERO, new Object[0]); 
/* 1075 */     for (i = 0; i < len; i++) {
/* 1076 */       if (Double.isNaN(values[i])) {
/* 1077 */         out[i] = Double.NaN;
/*      */       } else {
/* 1079 */         out[i] = values[i] * normalizedSum / sum;
/*      */       } 
/*      */     } 
/* 1082 */     return out;
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math\\util\MathArrays.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */