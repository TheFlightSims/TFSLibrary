/*     */ package com.vividsolutions.jts;
/*     */ 
/*     */ public class JTSVersion {
/*  50 */   public static final JTSVersion CURRENT_VERSION = new JTSVersion();
/*     */   
/*     */   public static final int MAJOR = 1;
/*     */   
/*     */   public static final int MINOR = 14;
/*     */   
/*     */   public static final int PATCH = 0;
/*     */   
/*     */   private static final String releaseInfo = "";
/*     */   
/*     */   public static void main(String[] args) {
/*  79 */     System.out.println(CURRENT_VERSION);
/*     */   }
/*     */   
/*     */   public int getMajor() {
/*  90 */     return 1;
/*     */   }
/*     */   
/*     */   public int getMinor() {
/*  97 */     return 14;
/*     */   }
/*     */   
/*     */   public int getPatch() {
/* 104 */     return 0;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 113 */     String ver = "1.14.0";
/* 114 */     if ("" != null && "".length() > 0)
/* 115 */       return ver + " " + ""; 
/* 116 */     return ver;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\JTSVersion.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */