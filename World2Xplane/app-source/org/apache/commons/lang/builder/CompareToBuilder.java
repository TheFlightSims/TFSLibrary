/*      */ package org.apache.commons.lang.builder;
/*      */ 
/*      */ import java.lang.reflect.AccessibleObject;
/*      */ import java.lang.reflect.Field;
/*      */ import java.lang.reflect.Modifier;
/*      */ import java.util.Collection;
/*      */ import java.util.Comparator;
/*      */ import org.apache.commons.lang.ArrayUtils;
/*      */ import org.apache.commons.lang.math.NumberUtils;
/*      */ 
/*      */ public class CompareToBuilder {
/*  109 */   private int comparison = 0;
/*      */   
/*      */   public static int reflectionCompare(Object lhs, Object rhs) {
/*  140 */     return reflectionCompare(lhs, rhs, false, null, null);
/*      */   }
/*      */   
/*      */   public static int reflectionCompare(Object lhs, Object rhs, boolean compareTransients) {
/*  172 */     return reflectionCompare(lhs, rhs, compareTransients, null, null);
/*      */   }
/*      */   
/*      */   public static int reflectionCompare(Object lhs, Object rhs, Collection excludeFields) {
/*  205 */     return reflectionCompare(lhs, rhs, ReflectionToStringBuilder.toNoNullStringArray(excludeFields));
/*      */   }
/*      */   
/*      */   public static int reflectionCompare(Object lhs, Object rhs, String[] excludeFields) {
/*  238 */     return reflectionCompare(lhs, rhs, false, null, excludeFields);
/*      */   }
/*      */   
/*      */   public static int reflectionCompare(Object lhs, Object rhs, boolean compareTransients, Class reflectUpToClass) {
/*  275 */     return reflectionCompare(lhs, rhs, compareTransients, reflectUpToClass, null);
/*      */   }
/*      */   
/*      */   public static int reflectionCompare(Object lhs, Object rhs, boolean compareTransients, Class reflectUpToClass, String[] excludeFields) {
/*  317 */     if (lhs == rhs)
/*  318 */       return 0; 
/*  320 */     if (lhs == null || rhs == null)
/*  321 */       throw new NullPointerException(); 
/*  323 */     Class lhsClazz = lhs.getClass();
/*  324 */     if (!lhsClazz.isInstance(rhs))
/*  325 */       throw new ClassCastException(); 
/*  327 */     CompareToBuilder compareToBuilder = new CompareToBuilder();
/*  328 */     reflectionAppend(lhs, rhs, lhsClazz, compareToBuilder, compareTransients, excludeFields);
/*  329 */     while (lhsClazz.getSuperclass() != null && lhsClazz != reflectUpToClass) {
/*  330 */       lhsClazz = lhsClazz.getSuperclass();
/*  331 */       reflectionAppend(lhs, rhs, lhsClazz, compareToBuilder, compareTransients, excludeFields);
/*      */     } 
/*  333 */     return compareToBuilder.toComparison();
/*      */   }
/*      */   
/*      */   private static void reflectionAppend(Object lhs, Object rhs, Class clazz, CompareToBuilder builder, boolean useTransients, String[] excludeFields) {
/*  355 */     Field[] fields = clazz.getDeclaredFields();
/*  356 */     AccessibleObject.setAccessible((AccessibleObject[])fields, true);
/*  357 */     for (int i = 0; i < fields.length && builder.comparison == 0; i++) {
/*  358 */       Field f = fields[i];
/*  359 */       if (!ArrayUtils.contains((Object[])excludeFields, f.getName()) && f.getName().indexOf('$') == -1 && (useTransients || !Modifier.isTransient(f.getModifiers())) && !Modifier.isStatic(f.getModifiers()))
/*      */         try {
/*  364 */           builder.append(f.get(lhs), f.get(rhs));
/*  365 */         } catch (IllegalAccessException e) {
/*  368 */           throw new InternalError("Unexpected IllegalAccessException");
/*      */         }  
/*      */     } 
/*      */   }
/*      */   
/*      */   public CompareToBuilder appendSuper(int superCompareTo) {
/*  384 */     if (this.comparison != 0)
/*  385 */       return this; 
/*  387 */     this.comparison = superCompareTo;
/*  388 */     return this;
/*      */   }
/*      */   
/*      */   public CompareToBuilder append(Object lhs, Object rhs) {
/*  412 */     return append(lhs, rhs, (Comparator)null);
/*      */   }
/*      */   
/*      */   public CompareToBuilder append(Object lhs, Object rhs, Comparator comparator) {
/*  441 */     if (this.comparison != 0)
/*  442 */       return this; 
/*  444 */     if (lhs == rhs)
/*  445 */       return this; 
/*  447 */     if (lhs == null) {
/*  448 */       this.comparison = -1;
/*  449 */       return this;
/*      */     } 
/*  451 */     if (rhs == null) {
/*  452 */       this.comparison = 1;
/*  453 */       return this;
/*      */     } 
/*  455 */     if (lhs.getClass().isArray()) {
/*  459 */       if (lhs instanceof long[]) {
/*  460 */         append((long[])lhs, (long[])rhs);
/*  461 */       } else if (lhs instanceof int[]) {
/*  462 */         append((int[])lhs, (int[])rhs);
/*  463 */       } else if (lhs instanceof short[]) {
/*  464 */         append((short[])lhs, (short[])rhs);
/*  465 */       } else if (lhs instanceof char[]) {
/*  466 */         append((char[])lhs, (char[])rhs);
/*  467 */       } else if (lhs instanceof byte[]) {
/*  468 */         append((byte[])lhs, (byte[])rhs);
/*  469 */       } else if (lhs instanceof double[]) {
/*  470 */         append((double[])lhs, (double[])rhs);
/*  471 */       } else if (lhs instanceof float[]) {
/*  472 */         append((float[])lhs, (float[])rhs);
/*  473 */       } else if (lhs instanceof boolean[]) {
/*  474 */         append((boolean[])lhs, (boolean[])rhs);
/*      */       } else {
/*  478 */         append((Object[])lhs, (Object[])rhs, comparator);
/*      */       } 
/*  482 */     } else if (comparator == null) {
/*  483 */       this.comparison = ((Comparable)lhs).compareTo(rhs);
/*      */     } else {
/*  485 */       this.comparison = comparator.compare(lhs, rhs);
/*      */     } 
/*  488 */     return this;
/*      */   }
/*      */   
/*      */   public CompareToBuilder append(long lhs, long rhs) {
/*  501 */     if (this.comparison != 0)
/*  502 */       return this; 
/*  504 */     this.comparison = (lhs < rhs) ? -1 : ((lhs > rhs) ? 1 : 0);
/*  505 */     return this;
/*      */   }
/*      */   
/*      */   public CompareToBuilder append(int lhs, int rhs) {
/*  517 */     if (this.comparison != 0)
/*  518 */       return this; 
/*  520 */     this.comparison = (lhs < rhs) ? -1 : ((lhs > rhs) ? 1 : 0);
/*  521 */     return this;
/*      */   }
/*      */   
/*      */   public CompareToBuilder append(short lhs, short rhs) {
/*  533 */     if (this.comparison != 0)
/*  534 */       return this; 
/*  536 */     this.comparison = (lhs < rhs) ? -1 : ((lhs > rhs) ? 1 : 0);
/*  537 */     return this;
/*      */   }
/*      */   
/*      */   public CompareToBuilder append(char lhs, char rhs) {
/*  549 */     if (this.comparison != 0)
/*  550 */       return this; 
/*  552 */     this.comparison = (lhs < rhs) ? -1 : ((lhs > rhs) ? 1 : 0);
/*  553 */     return this;
/*      */   }
/*      */   
/*      */   public CompareToBuilder append(byte lhs, byte rhs) {
/*  565 */     if (this.comparison != 0)
/*  566 */       return this; 
/*  568 */     this.comparison = (lhs < rhs) ? -1 : ((lhs > rhs) ? 1 : 0);
/*  569 */     return this;
/*      */   }
/*      */   
/*      */   public CompareToBuilder append(double lhs, double rhs) {
/*  586 */     if (this.comparison != 0)
/*  587 */       return this; 
/*  589 */     this.comparison = NumberUtils.compare(lhs, rhs);
/*  590 */     return this;
/*      */   }
/*      */   
/*      */   public CompareToBuilder append(float lhs, float rhs) {
/*  607 */     if (this.comparison != 0)
/*  608 */       return this; 
/*  610 */     this.comparison = NumberUtils.compare(lhs, rhs);
/*  611 */     return this;
/*      */   }
/*      */   
/*      */   public CompareToBuilder append(boolean lhs, boolean rhs) {
/*  623 */     if (this.comparison != 0)
/*  624 */       return this; 
/*  626 */     if (lhs == rhs)
/*  627 */       return this; 
/*  629 */     if (!lhs) {
/*  630 */       this.comparison = -1;
/*      */     } else {
/*  632 */       this.comparison = 1;
/*      */     } 
/*  634 */     return this;
/*      */   }
/*      */   
/*      */   public CompareToBuilder append(Object[] lhs, Object[] rhs) {
/*  659 */     return append(lhs, rhs, (Comparator)null);
/*      */   }
/*      */   
/*      */   public CompareToBuilder append(Object[] lhs, Object[] rhs, Comparator comparator) {
/*  686 */     if (this.comparison != 0)
/*  687 */       return this; 
/*  689 */     if (lhs == rhs)
/*  690 */       return this; 
/*  692 */     if (lhs == null) {
/*  693 */       this.comparison = -1;
/*  694 */       return this;
/*      */     } 
/*  696 */     if (rhs == null) {
/*  697 */       this.comparison = 1;
/*  698 */       return this;
/*      */     } 
/*  700 */     if (lhs.length != rhs.length) {
/*  701 */       this.comparison = (lhs.length < rhs.length) ? -1 : 1;
/*  702 */       return this;
/*      */     } 
/*  704 */     for (int i = 0; i < lhs.length && this.comparison == 0; i++)
/*  705 */       append(lhs[i], rhs[i], comparator); 
/*  707 */     return this;
/*      */   }
/*      */   
/*      */   public CompareToBuilder append(long[] lhs, long[] rhs) {
/*  726 */     if (this.comparison != 0)
/*  727 */       return this; 
/*  729 */     if (lhs == rhs)
/*  730 */       return this; 
/*  732 */     if (lhs == null) {
/*  733 */       this.comparison = -1;
/*  734 */       return this;
/*      */     } 
/*  736 */     if (rhs == null) {
/*  737 */       this.comparison = 1;
/*  738 */       return this;
/*      */     } 
/*  740 */     if (lhs.length != rhs.length) {
/*  741 */       this.comparison = (lhs.length < rhs.length) ? -1 : 1;
/*  742 */       return this;
/*      */     } 
/*  744 */     for (int i = 0; i < lhs.length && this.comparison == 0; i++)
/*  745 */       append(lhs[i], rhs[i]); 
/*  747 */     return this;
/*      */   }
/*      */   
/*      */   public CompareToBuilder append(int[] lhs, int[] rhs) {
/*  766 */     if (this.comparison != 0)
/*  767 */       return this; 
/*  769 */     if (lhs == rhs)
/*  770 */       return this; 
/*  772 */     if (lhs == null) {
/*  773 */       this.comparison = -1;
/*  774 */       return this;
/*      */     } 
/*  776 */     if (rhs == null) {
/*  777 */       this.comparison = 1;
/*  778 */       return this;
/*      */     } 
/*  780 */     if (lhs.length != rhs.length) {
/*  781 */       this.comparison = (lhs.length < rhs.length) ? -1 : 1;
/*  782 */       return this;
/*      */     } 
/*  784 */     for (int i = 0; i < lhs.length && this.comparison == 0; i++)
/*  785 */       append(lhs[i], rhs[i]); 
/*  787 */     return this;
/*      */   }
/*      */   
/*      */   public CompareToBuilder append(short[] lhs, short[] rhs) {
/*  806 */     if (this.comparison != 0)
/*  807 */       return this; 
/*  809 */     if (lhs == rhs)
/*  810 */       return this; 
/*  812 */     if (lhs == null) {
/*  813 */       this.comparison = -1;
/*  814 */       return this;
/*      */     } 
/*  816 */     if (rhs == null) {
/*  817 */       this.comparison = 1;
/*  818 */       return this;
/*      */     } 
/*  820 */     if (lhs.length != rhs.length) {
/*  821 */       this.comparison = (lhs.length < rhs.length) ? -1 : 1;
/*  822 */       return this;
/*      */     } 
/*  824 */     for (int i = 0; i < lhs.length && this.comparison == 0; i++)
/*  825 */       append(lhs[i], rhs[i]); 
/*  827 */     return this;
/*      */   }
/*      */   
/*      */   public CompareToBuilder append(char[] lhs, char[] rhs) {
/*  846 */     if (this.comparison != 0)
/*  847 */       return this; 
/*  849 */     if (lhs == rhs)
/*  850 */       return this; 
/*  852 */     if (lhs == null) {
/*  853 */       this.comparison = -1;
/*  854 */       return this;
/*      */     } 
/*  856 */     if (rhs == null) {
/*  857 */       this.comparison = 1;
/*  858 */       return this;
/*      */     } 
/*  860 */     if (lhs.length != rhs.length) {
/*  861 */       this.comparison = (lhs.length < rhs.length) ? -1 : 1;
/*  862 */       return this;
/*      */     } 
/*  864 */     for (int i = 0; i < lhs.length && this.comparison == 0; i++)
/*  865 */       append(lhs[i], rhs[i]); 
/*  867 */     return this;
/*      */   }
/*      */   
/*      */   public CompareToBuilder append(byte[] lhs, byte[] rhs) {
/*  886 */     if (this.comparison != 0)
/*  887 */       return this; 
/*  889 */     if (lhs == rhs)
/*  890 */       return this; 
/*  892 */     if (lhs == null) {
/*  893 */       this.comparison = -1;
/*  894 */       return this;
/*      */     } 
/*  896 */     if (rhs == null) {
/*  897 */       this.comparison = 1;
/*  898 */       return this;
/*      */     } 
/*  900 */     if (lhs.length != rhs.length) {
/*  901 */       this.comparison = (lhs.length < rhs.length) ? -1 : 1;
/*  902 */       return this;
/*      */     } 
/*  904 */     for (int i = 0; i < lhs.length && this.comparison == 0; i++)
/*  905 */       append(lhs[i], rhs[i]); 
/*  907 */     return this;
/*      */   }
/*      */   
/*      */   public CompareToBuilder append(double[] lhs, double[] rhs) {
/*  926 */     if (this.comparison != 0)
/*  927 */       return this; 
/*  929 */     if (lhs == rhs)
/*  930 */       return this; 
/*  932 */     if (lhs == null) {
/*  933 */       this.comparison = -1;
/*  934 */       return this;
/*      */     } 
/*  936 */     if (rhs == null) {
/*  937 */       this.comparison = 1;
/*  938 */       return this;
/*      */     } 
/*  940 */     if (lhs.length != rhs.length) {
/*  941 */       this.comparison = (lhs.length < rhs.length) ? -1 : 1;
/*  942 */       return this;
/*      */     } 
/*  944 */     for (int i = 0; i < lhs.length && this.comparison == 0; i++)
/*  945 */       append(lhs[i], rhs[i]); 
/*  947 */     return this;
/*      */   }
/*      */   
/*      */   public CompareToBuilder append(float[] lhs, float[] rhs) {
/*  966 */     if (this.comparison != 0)
/*  967 */       return this; 
/*  969 */     if (lhs == rhs)
/*  970 */       return this; 
/*  972 */     if (lhs == null) {
/*  973 */       this.comparison = -1;
/*  974 */       return this;
/*      */     } 
/*  976 */     if (rhs == null) {
/*  977 */       this.comparison = 1;
/*  978 */       return this;
/*      */     } 
/*  980 */     if (lhs.length != rhs.length) {
/*  981 */       this.comparison = (lhs.length < rhs.length) ? -1 : 1;
/*  982 */       return this;
/*      */     } 
/*  984 */     for (int i = 0; i < lhs.length && this.comparison == 0; i++)
/*  985 */       append(lhs[i], rhs[i]); 
/*  987 */     return this;
/*      */   }
/*      */   
/*      */   public CompareToBuilder append(boolean[] lhs, boolean[] rhs) {
/* 1006 */     if (this.comparison != 0)
/* 1007 */       return this; 
/* 1009 */     if (lhs == rhs)
/* 1010 */       return this; 
/* 1012 */     if (lhs == null) {
/* 1013 */       this.comparison = -1;
/* 1014 */       return this;
/*      */     } 
/* 1016 */     if (rhs == null) {
/* 1017 */       this.comparison = 1;
/* 1018 */       return this;
/*      */     } 
/* 1020 */     if (lhs.length != rhs.length) {
/* 1021 */       this.comparison = (lhs.length < rhs.length) ? -1 : 1;
/* 1022 */       return this;
/*      */     } 
/* 1024 */     for (int i = 0; i < lhs.length && this.comparison == 0; i++)
/* 1025 */       append(lhs[i], rhs[i]); 
/* 1027 */     return this;
/*      */   }
/*      */   
/*      */   public int toComparison() {
/* 1040 */     return this.comparison;
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\lang\builder\CompareToBuilder.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */