/*     */ package org.apache.commons.math3.util;
/*     */ 
/*     */ import java.text.FieldPosition;
/*     */ import java.text.NumberFormat;
/*     */ import java.text.ParsePosition;
/*     */ import java.util.Locale;
/*     */ 
/*     */ public class CompositeFormat {
/*     */   public static NumberFormat getDefaultNumberFormat() {
/*  43 */     return getDefaultNumberFormat(Locale.getDefault());
/*     */   }
/*     */   
/*     */   public static NumberFormat getDefaultNumberFormat(Locale locale) {
/*  54 */     NumberFormat nf = NumberFormat.getInstance(locale);
/*  55 */     nf.setMaximumFractionDigits(2);
/*  56 */     return nf;
/*     */   }
/*     */   
/*     */   public static void parseAndIgnoreWhitespace(String source, ParsePosition pos) {
/*  68 */     parseNextCharacter(source, pos);
/*  69 */     pos.setIndex(pos.getIndex() - 1);
/*     */   }
/*     */   
/*     */   public static char parseNextCharacter(String source, ParsePosition pos) {
/*  81 */     int index = pos.getIndex();
/*  82 */     int n = source.length();
/*  83 */     char ret = Character.MIN_VALUE;
/*  85 */     if (index < n) {
/*     */       char c;
/*     */       do {
/*  88 */         c = source.charAt(index++);
/*  89 */       } while (Character.isWhitespace(c) && index < n);
/*  90 */       pos.setIndex(index);
/*  92 */       if (index < n)
/*  93 */         ret = c; 
/*     */     } 
/*  97 */     return ret;
/*     */   }
/*     */   
/*     */   private static Number parseNumber(String source, double value, ParsePosition pos) {
/* 111 */     Number ret = null;
/* 113 */     StringBuilder sb = new StringBuilder();
/* 114 */     sb.append('(');
/* 115 */     sb.append(value);
/* 116 */     sb.append(')');
/* 118 */     int n = sb.length();
/* 119 */     int startIndex = pos.getIndex();
/* 120 */     int endIndex = startIndex + n;
/* 121 */     if (endIndex < source.length() && 
/* 122 */       source.substring(startIndex, endIndex).compareTo(sb.toString()) == 0) {
/* 123 */       ret = Double.valueOf(value);
/* 124 */       pos.setIndex(endIndex);
/*     */     } 
/* 128 */     return ret;
/*     */   }
/*     */   
/*     */   public static Number parseNumber(String source, NumberFormat format, ParsePosition pos) {
/* 143 */     int startIndex = pos.getIndex();
/* 144 */     Number number = format.parse(source, pos);
/* 145 */     int endIndex = pos.getIndex();
/* 148 */     if (startIndex == endIndex) {
/* 150 */       double[] special = { Double.NaN, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY };
/* 153 */       for (int i = 0; i < special.length; i++) {
/* 154 */         number = parseNumber(source, special[i], pos);
/* 155 */         if (number != null)
/*     */           break; 
/*     */       } 
/*     */     } 
/* 161 */     return number;
/*     */   }
/*     */   
/*     */   public static boolean parseFixedstring(String source, String expected, ParsePosition pos) {
/* 175 */     int startIndex = pos.getIndex();
/* 176 */     int endIndex = startIndex + expected.length();
/* 177 */     if (startIndex >= source.length() || endIndex > source.length() || source.substring(startIndex, endIndex).compareTo(expected) != 0) {
/* 181 */       pos.setIndex(startIndex);
/* 182 */       pos.setErrorIndex(startIndex);
/* 183 */       return false;
/*     */     } 
/* 187 */     pos.setIndex(endIndex);
/* 188 */     return true;
/*     */   }
/*     */   
/*     */   public static StringBuffer formatDouble(double value, NumberFormat format, StringBuffer toAppendTo, FieldPosition pos) {
/* 211 */     if (Double.isNaN(value) || Double.isInfinite(value)) {
/* 212 */       toAppendTo.append('(');
/* 213 */       toAppendTo.append(value);
/* 214 */       toAppendTo.append(')');
/*     */     } else {
/* 216 */       format.format(value, toAppendTo, pos);
/*     */     } 
/* 218 */     return toAppendTo;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math\\util\CompositeFormat.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */