/*     */ package org.jfree.util;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ 
/*     */ public class ArrayUtilities {
/*     */   public static float[][] clone(float[][] array) {
/*  70 */     if (array == null)
/*  71 */       return (float[][])null; 
/*  73 */     float[][] result = new float[array.length][];
/*  74 */     System.arraycopy(array, 0, result, 0, array.length);
/*  76 */     for (int i = 0; i < array.length; i++) {
/*  77 */       float[] child = array[i];
/*  78 */       float[] copychild = new float[child.length];
/*  79 */       System.arraycopy(child, 0, copychild, 0, child.length);
/*  80 */       result[i] = copychild;
/*     */     } 
/*  83 */     return result;
/*     */   }
/*     */   
/*     */   public static boolean equalReferencesInArrays(Object[] array1, Object[] array2) {
/*  99 */     if (array1 == null)
/* 100 */       return (array2 == null); 
/* 102 */     if (array2 == null)
/* 103 */       return false; 
/* 105 */     if (array1.length != array2.length)
/* 106 */       return false; 
/* 108 */     for (int i = 0; i < array1.length; i++) {
/* 109 */       if (array1[i] == null && 
/* 110 */         array2[i] != null)
/* 111 */         return false; 
/* 114 */       if (array2[i] == null && 
/* 115 */         array1[i] != null)
/* 116 */         return false; 
/* 119 */       if (array1[i] != array2[i])
/* 120 */         return false; 
/*     */     } 
/* 123 */     return true;
/*     */   }
/*     */   
/*     */   public static boolean equal(float[][] array1, float[][] array2) {
/* 136 */     if (array1 == null)
/* 137 */       return (array2 == null); 
/* 140 */     if (array2 == null)
/* 141 */       return false; 
/* 144 */     if (array1.length != array2.length)
/* 145 */       return false; 
/* 148 */     for (int i = 0; i < array1.length; i++) {
/* 149 */       if (!Arrays.equals(array1[i], array2[i]))
/* 150 */         return false; 
/*     */     } 
/* 153 */     return true;
/*     */   }
/*     */   
/*     */   public static boolean hasDuplicateItems(Object[] array) {
/* 165 */     for (int i = 0; i < array.length; i++) {
/* 166 */       for (int j = 0; j < i; j++) {
/* 167 */         Object o1 = array[i];
/* 168 */         Object o2 = array[j];
/* 169 */         if (o1 != null && o2 != null && 
/* 170 */           o1.equals(o2))
/* 171 */           return true; 
/*     */       } 
/*     */     } 
/* 176 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfre\\util\ArrayUtilities.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */