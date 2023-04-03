/*     */ package com.vividsolutions.jts.math;
/*     */ 
/*     */ public class MathUtil {
/*     */   public static double clamp(double x, double min, double max) {
/*  52 */     if (x < min)
/*  52 */       return min; 
/*  53 */     if (x > max)
/*  53 */       return max; 
/*  54 */     return x;
/*     */   }
/*     */   
/*     */   public static int clamp(int x, int min, int max) {
/*  66 */     if (x < min)
/*  66 */       return min; 
/*  67 */     if (x > max)
/*  67 */       return max; 
/*  68 */     return x;
/*     */   }
/*     */   
/*  71 */   private static final double LOG_10 = Math.log(10.0D);
/*     */   
/*     */   public static double log10(double x) {
/*  86 */     double ln = Math.log(x);
/*  87 */     if (Double.isInfinite(ln))
/*  87 */       return ln; 
/*  88 */     if (Double.isNaN(ln))
/*  88 */       return ln; 
/*  89 */     return ln / LOG_10;
/*     */   }
/*     */   
/*     */   public static int wrap(int index, int max) {
/* 103 */     if (index < 0)
/* 104 */       return max - -index % max; 
/* 106 */     return index % max;
/*     */   }
/*     */   
/*     */   public static double average(double x1, double x2) {
/* 118 */     return (x1 + x2) / 2.0D;
/*     */   }
/*     */   
/*     */   public static double max(double v1, double v2, double v3) {
/* 123 */     double max = v1;
/* 124 */     if (v2 > max)
/* 124 */       max = v2; 
/* 125 */     if (v3 > max)
/* 125 */       max = v3; 
/* 126 */     return max;
/*     */   }
/*     */   
/*     */   public static double max(double v1, double v2, double v3, double v4) {
/* 131 */     double max = v1;
/* 132 */     if (v2 > max)
/* 132 */       max = v2; 
/* 133 */     if (v3 > max)
/* 133 */       max = v3; 
/* 134 */     if (v4 > max)
/* 134 */       max = v4; 
/* 135 */     return max;
/*     */   }
/*     */   
/*     */   public static double min(double v1, double v2, double v3, double v4) {
/* 140 */     double min = v1;
/* 141 */     if (v2 < min)
/* 141 */       min = v2; 
/* 142 */     if (v3 < min)
/* 142 */       min = v3; 
/* 143 */     if (v4 < min)
/* 143 */       min = v4; 
/* 144 */     return min;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\math\MathUtil.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */