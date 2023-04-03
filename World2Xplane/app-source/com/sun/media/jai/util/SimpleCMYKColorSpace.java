/*     */ package com.sun.media.jai.util;
/*     */ 
/*     */ import java.awt.color.ColorSpace;
/*     */ 
/*     */ public final class SimpleCMYKColorSpace extends ColorSpace {
/*  21 */   private static ColorSpace theInstance = null;
/*     */   
/*     */   private ColorSpace csRGB;
/*     */   
/*     */   private static final double power1 = 0.4166666666666667D;
/*     */   
/*     */   public static final synchronized ColorSpace getInstance() {
/*  28 */     if (theInstance == null)
/*  29 */       theInstance = new SimpleCMYKColorSpace(); 
/*  31 */     return theInstance;
/*     */   }
/*     */   
/*     */   private SimpleCMYKColorSpace() {
/*  35 */     super(9, 4);
/*  36 */     this.csRGB = ColorSpace.getInstance(1004);
/*     */   }
/*     */   
/*     */   public boolean equals(Object o) {
/*  40 */     return (o != null && o instanceof SimpleCMYKColorSpace);
/*     */   }
/*     */   
/*     */   public float[] toRGB(float[] colorvalue) {
/*  44 */     float C = colorvalue[0];
/*  45 */     float M = colorvalue[1];
/*  46 */     float Y = colorvalue[2];
/*  47 */     float K = colorvalue[3];
/*  49 */     float K1 = 1.0F - K;
/*  52 */     float[] rgbvalue = { K1 * (1.0F - C), K1 * (1.0F - M), K1 * (1.0F - Y) };
/*  57 */     for (int i = 0; i < 3; i++) {
/*  58 */       float v = rgbvalue[i];
/*  60 */       if (v < 0.0F)
/*  60 */         v = 0.0F; 
/*  62 */       if (v < 0.0031308F) {
/*  63 */         rgbvalue[i] = 12.92F * v;
/*     */       } else {
/*  65 */         if (v > 1.0F)
/*  65 */           v = 1.0F; 
/*  67 */         rgbvalue[i] = (float)(1.055D * Math.pow(v, 0.4166666666666667D) - 0.055D);
/*     */       } 
/*     */     } 
/*  71 */     return rgbvalue;
/*     */   }
/*     */   
/*     */   public float[] fromRGB(float[] rgbvalue) {
/*  76 */     for (int i = 0; i < 3; i++) {
/*  77 */       if (rgbvalue[i] < 0.040449936F) {
/*  78 */         rgbvalue[i] = rgbvalue[i] / 12.92F;
/*     */       } else {
/*  80 */         rgbvalue[i] = (float)Math.pow((rgbvalue[i] + 0.055D) / 1.055D, 2.4D);
/*     */       } 
/*     */     } 
/*  86 */     float C = 1.0F - rgbvalue[0];
/*  87 */     float M = 1.0F - rgbvalue[1];
/*  88 */     float Y = 1.0F - rgbvalue[2];
/*  89 */     float K = Math.min(C, Math.min(M, Y));
/*  92 */     if (K != 1.0F) {
/*  93 */       float K1 = 1.0F - K;
/*  95 */       C = (C - K) / K1;
/*  96 */       M = (M - K) / K1;
/*  97 */       Y = (Y - K) / K1;
/*     */     } else {
/*  99 */       C = M = Y = 0.0F;
/*     */     } 
/* 102 */     return new float[] { C, M, Y, K };
/*     */   }
/*     */   
/*     */   public float[] toCIEXYZ(float[] colorvalue) {
/* 106 */     return this.csRGB.toCIEXYZ(toRGB(colorvalue));
/*     */   }
/*     */   
/*     */   public float[] fromCIEXYZ(float[] xyzvalue) {
/* 110 */     return fromRGB(this.csRGB.fromCIEXYZ(xyzvalue));
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\ja\\util\SimpleCMYKColorSpace.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */