/*     */ package com.vividsolutions.jts.util;
/*     */ 
/*     */ public class Memory {
/*     */   public static final double KB = 1024.0D;
/*     */   
/*     */   public static final double MB = 1048576.0D;
/*     */   
/*     */   public static final double GB = 1.073741824E9D;
/*     */   
/*     */   public static long used() {
/*  45 */     Runtime runtime = Runtime.getRuntime();
/*  46 */     return runtime.totalMemory() - runtime.freeMemory();
/*     */   }
/*     */   
/*     */   public static String usedString() {
/*  51 */     return format(used());
/*     */   }
/*     */   
/*     */   public static long free() {
/*  56 */     Runtime runtime = Runtime.getRuntime();
/*  57 */     return runtime.freeMemory();
/*     */   }
/*     */   
/*     */   public static String freeString() {
/*  62 */     return format(free());
/*     */   }
/*     */   
/*     */   public static long total() {
/*  67 */     Runtime runtime = Runtime.getRuntime();
/*  68 */     return runtime.totalMemory();
/*     */   }
/*     */   
/*     */   public static String totalString() {
/*  73 */     return format(total());
/*     */   }
/*     */   
/*     */   public static String usedTotalString() {
/*  78 */     return "Used: " + usedString() + "   Total: " + totalString();
/*     */   }
/*     */   
/*     */   public static String allString() {
/*  84 */     return "Used: " + usedString() + "   Free: " + freeString() + "   Total: " + totalString();
/*     */   }
/*     */   
/*     */   public static String format(long mem) {
/*  95 */     if (mem < 2048.0D)
/*  96 */       return mem + " bytes"; 
/*  97 */     if (mem < 2097152.0D)
/*  98 */       return round(mem / 1024.0D) + " KB"; 
/*  99 */     if (mem < 2.147483648E9D)
/* 100 */       return round(mem / 1048576.0D) + " MB"; 
/* 101 */     return round(mem / 1.073741824E9D) + " GB";
/*     */   }
/*     */   
/*     */   public static double round(double d) {
/* 106 */     return Math.ceil(d * 100.0D) / 100.0D;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jt\\util\Memory.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */