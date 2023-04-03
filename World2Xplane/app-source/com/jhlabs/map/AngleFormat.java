/*     */ package com.jhlabs.map;
/*     */ 
/*     */ import java.text.DecimalFormat;
/*     */ import java.text.FieldPosition;
/*     */ import java.text.NumberFormat;
/*     */ import java.text.ParsePosition;
/*     */ 
/*     */ public class AngleFormat extends NumberFormat {
/*     */   public static final String ddmmssPattern = "DdM";
/*     */   
/*     */   public static final String ddmmssPattern2 = "DdM'S\"";
/*     */   
/*     */   public static final String ddmmssLongPattern = "DdM'S\"W";
/*     */   
/*     */   public static final String ddmmssLatPattern = "DdM'S\"N";
/*     */   
/*     */   public static final String ddmmssPattern4 = "DdMmSs";
/*     */   
/*     */   public static final String decimalPattern = "D.F";
/*     */   
/*     */   private DecimalFormat format;
/*     */   
/*     */   private String pattern;
/*     */   
/*     */   private boolean isDegrees;
/*     */   
/*     */   public AngleFormat() {
/*  38 */     this("DdM");
/*     */   }
/*     */   
/*     */   public AngleFormat(String pattern) {
/*  42 */     this(pattern, false);
/*     */   }
/*     */   
/*     */   public AngleFormat(String pattern, boolean isDegrees) {
/*  46 */     this.pattern = pattern;
/*  47 */     this.isDegrees = isDegrees;
/*  48 */     this.format = new DecimalFormat();
/*  49 */     this.format.setMaximumFractionDigits(0);
/*  50 */     this.format.setGroupingUsed(false);
/*     */   }
/*     */   
/*     */   public StringBuffer format(long number, StringBuffer result, FieldPosition fieldPosition) {
/*  54 */     return format(number, result, fieldPosition);
/*     */   }
/*     */   
/*     */   public StringBuffer format(double number, StringBuffer result, FieldPosition fieldPosition) {
/*  58 */     int length = this.pattern.length();
/*  60 */     boolean negative = false;
/*  62 */     if (number < 0.0D)
/*  63 */       for (int j = length - 1; j >= 0; j--) {
/*  64 */         char c = this.pattern.charAt(j);
/*  65 */         if (c == 'W' || c == 'N') {
/*  66 */           number = -number;
/*  67 */           negative = true;
/*     */           break;
/*     */         } 
/*     */       }  
/*  73 */     double ddmmss = this.isDegrees ? number : Math.toDegrees(number);
/*  74 */     int iddmmss = (int)Math.round(ddmmss * 3600.0D);
/*  75 */     if (iddmmss < 0)
/*  76 */       iddmmss = -iddmmss; 
/*  77 */     int fraction = iddmmss % 3600;
/*  79 */     for (int i = 0; i < length; i++) {
/*     */       int f;
/*  80 */       char c = this.pattern.charAt(i);
/*  81 */       switch (c) {
/*     */         case 'R':
/*  83 */           result.append(number);
/*     */           break;
/*     */         case 'D':
/*  86 */           result.append((int)ddmmss);
/*     */           break;
/*     */         case 'M':
/*  89 */           f = fraction / 60;
/*  90 */           if (f < 10)
/*  91 */             result.append('0'); 
/*  92 */           result.append(f);
/*     */           break;
/*     */         case 'S':
/*  95 */           f = fraction % 60;
/*  96 */           if (f < 10)
/*  97 */             result.append('0'); 
/*  98 */           result.append(f);
/*     */           break;
/*     */         case 'F':
/* 101 */           result.append(fraction);
/*     */           break;
/*     */         case 'W':
/* 104 */           if (negative) {
/* 105 */             result.append('W');
/*     */             break;
/*     */           } 
/* 107 */           result.append('E');
/*     */           break;
/*     */         case 'N':
/* 110 */           if (negative) {
/* 111 */             result.append('S');
/*     */             break;
/*     */           } 
/* 113 */           result.append('N');
/*     */           break;
/*     */         default:
/* 116 */           result.append(c);
/*     */           break;
/*     */       } 
/*     */     } 
/* 120 */     return result;
/*     */   }
/*     */   
/*     */   public Number parse(String text, ParsePosition parsePosition) {
/* 124 */     double result, d = 0.0D, m = 0.0D, s = 0.0D;
/* 126 */     boolean negate = false;
/* 127 */     int length = text.length();
/* 128 */     if (length > 0) {
/* 129 */       char c = Character.toUpperCase(text.charAt(length - 1));
/* 130 */       switch (c) {
/*     */         case 'S':
/*     */         case 'W':
/* 133 */           negate = true;
/*     */         case 'E':
/*     */         case 'N':
/* 137 */           text = text.substring(0, length - 1);
/*     */           break;
/*     */       } 
/*     */     } 
/* 141 */     int i = text.indexOf('d');
/* 142 */     if (i == -1)
/* 143 */       i = text.indexOf('°'); 
/* 144 */     if (i != -1) {
/* 145 */       String dd = text.substring(0, i);
/* 146 */       String mmss = text.substring(i + 1);
/* 147 */       d = Double.valueOf(dd).doubleValue();
/* 148 */       i = mmss.indexOf('m');
/* 149 */       if (i == -1)
/* 150 */         i = mmss.indexOf('\''); 
/* 151 */       if (i != -1) {
/* 152 */         if (i != 0) {
/* 153 */           String mm = mmss.substring(0, i);
/* 154 */           m = Double.valueOf(mm).doubleValue();
/*     */         } 
/* 156 */         if (mmss.endsWith("s") || mmss.endsWith("\""))
/* 157 */           mmss = mmss.substring(0, mmss.length() - 1); 
/* 158 */         if (i != mmss.length() - 1) {
/* 159 */           String ss = mmss.substring(i + 1);
/* 160 */           s = Double.valueOf(ss).doubleValue();
/*     */         } 
/* 162 */         if (m < 0.0D || m > 59.0D)
/* 163 */           throw new NumberFormatException("Minutes must be between 0 and 59"); 
/* 164 */         if (s < 0.0D || s >= 60.0D)
/* 165 */           throw new NumberFormatException("Seconds must be between 0 and 59"); 
/* 166 */       } else if (i != 0) {
/* 167 */         m = Double.valueOf(mmss).doubleValue();
/*     */       } 
/* 168 */       if (this.isDegrees) {
/* 169 */         result = MapMath.dmsToDeg(d, m, s);
/*     */       } else {
/* 171 */         result = MapMath.dmsToRad(d, m, s);
/*     */       } 
/*     */     } else {
/* 173 */       result = Double.parseDouble(text);
/* 174 */       if (!this.isDegrees)
/* 175 */         result = Math.toRadians(result); 
/*     */     } 
/* 177 */     if (parsePosition != null)
/* 178 */       parsePosition.setIndex(text.length()); 
/* 179 */     if (negate)
/* 180 */       result = -result; 
/* 181 */     return new Double(result);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\jhlabs\map\AngleFormat.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */