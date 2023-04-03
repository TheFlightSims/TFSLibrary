/*     */ package com.ctc.wstx.util;
/*     */ 
/*     */ import java.lang.reflect.Array;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ 
/*     */ public final class DataUtil {
/*   8 */   static final char[] EMPTY_CHAR_ARRAY = new char[0];
/*     */   
/*  16 */   static final Integer[] INTS = new Integer[100];
/*     */   
/*     */   static final String NO_TYPE = "Illegal to pass null; can not determine component type";
/*     */   
/*     */   static {
/*  18 */     for (int i = 0; i < INTS.length; i++)
/*  19 */       INTS[i] = new Integer(i); 
/*     */   }
/*     */   
/*     */   public static char[] getEmptyCharArray() {
/*  32 */     return EMPTY_CHAR_ARRAY;
/*     */   }
/*     */   
/*     */   public static Integer Integer(int i) {
/*  41 */     if (i < 0 || i >= INTS.length)
/*  42 */       return new Integer(i); 
/*  44 */     return INTS[i];
/*     */   }
/*     */   
/*     */   public static boolean anyValuesInCommon(Collection c1, Collection c2) {
/*  63 */     if (c1.size() > c2.size()) {
/*  64 */       Collection tmp = c1;
/*  65 */       c1 = c2;
/*  66 */       c2 = tmp;
/*     */     } 
/*  68 */     Iterator it = c1.iterator();
/*  69 */     while (it.hasNext()) {
/*  70 */       if (c2.contains(it.next()))
/*  71 */         return true; 
/*     */     } 
/*  74 */     return false;
/*     */   }
/*     */   
/*     */   public static Object growArrayBy50Pct(Object arr) {
/*  81 */     if (arr == null)
/*  82 */       throw new IllegalArgumentException("Illegal to pass null; can not determine component type"); 
/*  84 */     Object old = arr;
/*  85 */     int len = Array.getLength(arr);
/*  86 */     arr = Array.newInstance(arr.getClass().getComponentType(), len + (len >> 1));
/*  87 */     System.arraycopy(old, 0, arr, 0, len);
/*  88 */     return arr;
/*     */   }
/*     */   
/*     */   public static Object growArrayToAtLeast(Object arr, int minLen) {
/*  97 */     if (arr == null)
/*  98 */       throw new IllegalArgumentException("Illegal to pass null; can not determine component type"); 
/* 100 */     Object old = arr;
/* 101 */     int oldLen = Array.getLength(arr);
/* 102 */     int newLen = oldLen + (oldLen + 1 >> 1);
/* 103 */     if (newLen < minLen)
/* 104 */       newLen = minLen; 
/* 106 */     arr = Array.newInstance(arr.getClass().getComponentType(), newLen);
/* 107 */     System.arraycopy(old, 0, arr, 0, oldLen);
/* 108 */     return arr;
/*     */   }
/*     */   
/*     */   public static String[] growArrayBy(String[] arr, int more) {
/* 113 */     if (arr == null)
/* 114 */       return new String[more]; 
/* 116 */     String[] old = arr;
/* 117 */     int len = arr.length;
/* 118 */     arr = new String[len + more];
/* 119 */     System.arraycopy(old, 0, arr, 0, len);
/* 120 */     return arr;
/*     */   }
/*     */   
/*     */   public static int[] growArrayBy(int[] arr, int more) {
/* 125 */     if (arr == null)
/* 126 */       return new int[more]; 
/* 128 */     int[] old = arr;
/* 129 */     int len = arr.length;
/* 130 */     arr = new int[len + more];
/* 131 */     System.arraycopy(old, 0, arr, 0, len);
/* 132 */     return arr;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wst\\util\DataUtil.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */