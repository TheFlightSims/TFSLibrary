/*     */ package org.apache.commons.lang.math;
/*     */ 
/*     */ public class IEEE754rUtils {
/*     */   public static double min(double[] array) {
/*  40 */     if (array == null)
/*  41 */       throw new IllegalArgumentException("The Array must not be null"); 
/*  42 */     if (array.length == 0)
/*  43 */       throw new IllegalArgumentException("Array cannot be empty."); 
/*  47 */     double min = array[0];
/*  48 */     for (int i = 1; i < array.length; i++)
/*  49 */       min = min(array[i], min); 
/*  52 */     return min;
/*     */   }
/*     */   
/*     */   public static float min(float[] array) {
/*  65 */     if (array == null)
/*  66 */       throw new IllegalArgumentException("The Array must not be null"); 
/*  67 */     if (array.length == 0)
/*  68 */       throw new IllegalArgumentException("Array cannot be empty."); 
/*  72 */     float min = array[0];
/*  73 */     for (int i = 1; i < array.length; i++)
/*  74 */       min = min(array[i], min); 
/*  77 */     return min;
/*     */   }
/*     */   
/*     */   public static double min(double a, double b, double c) {
/*  91 */     return min(min(a, b), c);
/*     */   }
/*     */   
/*     */   public static double min(double a, double b) {
/* 104 */     if (Double.isNaN(a))
/* 105 */       return b; 
/* 107 */     if (Double.isNaN(b))
/* 108 */       return a; 
/* 110 */     return Math.min(a, b);
/*     */   }
/*     */   
/*     */   public static float min(float a, float b, float c) {
/* 125 */     return min(min(a, b), c);
/*     */   }
/*     */   
/*     */   public static float min(float a, float b) {
/* 138 */     if (Float.isNaN(a))
/* 139 */       return b; 
/* 141 */     if (Float.isNaN(b))
/* 142 */       return a; 
/* 144 */     return Math.min(a, b);
/*     */   }
/*     */   
/*     */   public static double max(double[] array) {
/* 158 */     if (array == null)
/* 159 */       throw new IllegalArgumentException("The Array must not be null"); 
/* 160 */     if (array.length == 0)
/* 161 */       throw new IllegalArgumentException("Array cannot be empty."); 
/* 165 */     double max = array[0];
/* 166 */     for (int j = 1; j < array.length; j++)
/* 167 */       max = max(array[j], max); 
/* 170 */     return max;
/*     */   }
/*     */   
/*     */   public static float max(float[] array) {
/* 183 */     if (array == null)
/* 184 */       throw new IllegalArgumentException("The Array must not be null"); 
/* 185 */     if (array.length == 0)
/* 186 */       throw new IllegalArgumentException("Array cannot be empty."); 
/* 190 */     float max = array[0];
/* 191 */     for (int j = 1; j < array.length; j++)
/* 192 */       max = max(array[j], max); 
/* 195 */     return max;
/*     */   }
/*     */   
/*     */   public static double max(double a, double b, double c) {
/* 209 */     return max(max(a, b), c);
/*     */   }
/*     */   
/*     */   public static double max(double a, double b) {
/* 222 */     if (Double.isNaN(a))
/* 223 */       return b; 
/* 225 */     if (Double.isNaN(b))
/* 226 */       return a; 
/* 228 */     return Math.max(a, b);
/*     */   }
/*     */   
/*     */   public static float max(float a, float b, float c) {
/* 243 */     return max(max(a, b), c);
/*     */   }
/*     */   
/*     */   public static float max(float a, float b) {
/* 256 */     if (Float.isNaN(a))
/* 257 */       return b; 
/* 259 */     if (Float.isNaN(b))
/* 260 */       return a; 
/* 262 */     return Math.max(a, b);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\lang\math\IEEE754rUtils.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */