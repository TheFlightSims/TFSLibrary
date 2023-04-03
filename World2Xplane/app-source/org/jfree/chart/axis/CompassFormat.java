/*     */ package org.jfree.chart.axis;
/*     */ 
/*     */ import java.text.FieldPosition;
/*     */ import java.text.NumberFormat;
/*     */ import java.text.ParsePosition;
/*     */ 
/*     */ public class CompassFormat extends NumberFormat {
/*     */   private static final String N = "N";
/*     */   
/*     */   private static final String E = "E";
/*     */   
/*     */   private static final String S = "S";
/*     */   
/*     */   private static final String W = "W";
/*     */   
/*  67 */   public static final String[] DIRECTIONS = new String[] { 
/*  67 */       "N", "NNE", "NE", "ENE", "E", "ESE", "SE", "SSE", "S", "SSW", 
/*  67 */       "SW", "WSW", "W", "WNW", "NW", "NNW", "N" };
/*     */   
/*     */   public String getDirectionCode(double direction) {
/*  88 */     direction %= 360.0D;
/*  89 */     if (direction < 0.0D)
/*  90 */       direction += 360.0D; 
/*  92 */     int index = ((int)Math.floor(direction / 11.25D) + 1) / 2;
/*  93 */     return DIRECTIONS[index];
/*     */   }
/*     */   
/*     */   public StringBuffer format(double number, StringBuffer toAppendTo, FieldPosition pos) {
/* 103 */     return toAppendTo.append(getDirectionCode(number));
/*     */   }
/*     */   
/*     */   public StringBuffer format(long number, StringBuffer toAppendTo, FieldPosition pos) {
/* 112 */     return toAppendTo.append(getDirectionCode(number));
/*     */   }
/*     */   
/*     */   public Number parse(String source, ParsePosition parsePosition) {
/* 125 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\axis\CompassFormat.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */