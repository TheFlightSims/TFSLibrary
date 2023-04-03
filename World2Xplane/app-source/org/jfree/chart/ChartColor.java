/*     */ package org.jfree.chart;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Paint;
/*     */ 
/*     */ public class ChartColor extends Color {
/*  61 */   public static final Color VERY_DARK_RED = new Color(128, 0, 0);
/*     */   
/*  64 */   public static final Color DARK_RED = new Color(192, 0, 0);
/*     */   
/*  67 */   public static final Color LIGHT_RED = new Color(255, 64, 64);
/*     */   
/*  70 */   public static final Color VERY_LIGHT_RED = new Color(255, 128, 128);
/*     */   
/*  73 */   public static final Color VERY_DARK_YELLOW = new Color(128, 128, 0);
/*     */   
/*  76 */   public static final Color DARK_YELLOW = new Color(192, 192, 0);
/*     */   
/*  79 */   public static final Color LIGHT_YELLOW = new Color(255, 255, 64);
/*     */   
/*  82 */   public static final Color VERY_LIGHT_YELLOW = new Color(255, 255, 128);
/*     */   
/*  85 */   public static final Color VERY_DARK_GREEN = new Color(0, 128, 0);
/*     */   
/*  88 */   public static final Color DARK_GREEN = new Color(0, 192, 0);
/*     */   
/*  91 */   public static final Color LIGHT_GREEN = new Color(64, 255, 64);
/*     */   
/*  94 */   public static final Color VERY_LIGHT_GREEN = new Color(128, 255, 128);
/*     */   
/*  97 */   public static final Color VERY_DARK_CYAN = new Color(0, 128, 128);
/*     */   
/* 100 */   public static final Color DARK_CYAN = new Color(0, 192, 192);
/*     */   
/* 103 */   public static final Color LIGHT_CYAN = new Color(64, 255, 255);
/*     */   
/* 106 */   public static final Color VERY_LIGHT_CYAN = new Color(128, 255, 255);
/*     */   
/* 109 */   public static final Color VERY_DARK_BLUE = new Color(0, 0, 128);
/*     */   
/* 112 */   public static final Color DARK_BLUE = new Color(0, 0, 192);
/*     */   
/* 115 */   public static final Color LIGHT_BLUE = new Color(64, 64, 255);
/*     */   
/* 118 */   public static final Color VERY_LIGHT_BLUE = new Color(128, 128, 255);
/*     */   
/* 121 */   public static final Color VERY_DARK_MAGENTA = new Color(128, 0, 128);
/*     */   
/* 124 */   public static final Color DARK_MAGENTA = new Color(192, 0, 192);
/*     */   
/* 127 */   public static final Color LIGHT_MAGENTA = new Color(255, 64, 255);
/*     */   
/* 130 */   public static final Color VERY_LIGHT_MAGENTA = new Color(255, 128, 255);
/*     */   
/*     */   public ChartColor(int r, int g, int b) {
/* 141 */     super(r, g, b);
/*     */   }
/*     */   
/*     */   public static Paint[] createDefaultPaintArray() {
/* 153 */     return new Paint[] { 
/* 153 */         new Color(255, 85, 85), new Color(85, 85, 255), new Color(85, 255, 85), new Color(255, 255, 85), new Color(255, 85, 255), new Color(85, 255, 255), Color.pink, Color.gray, DARK_RED, DARK_BLUE, 
/* 153 */         DARK_GREEN, DARK_YELLOW, DARK_MAGENTA, DARK_CYAN, Color.darkGray, LIGHT_RED, LIGHT_BLUE, LIGHT_GREEN, LIGHT_YELLOW, LIGHT_MAGENTA, 
/* 153 */         LIGHT_CYAN, Color.lightGray, VERY_DARK_RED, VERY_DARK_BLUE, VERY_DARK_GREEN, VERY_DARK_YELLOW, VERY_DARK_MAGENTA, VERY_DARK_CYAN, VERY_LIGHT_RED, VERY_LIGHT_BLUE, 
/* 153 */         VERY_LIGHT_GREEN, VERY_LIGHT_YELLOW, VERY_LIGHT_MAGENTA, VERY_LIGHT_CYAN };
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\ChartColor.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */