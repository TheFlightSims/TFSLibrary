/*     */ package org.jfree.chart.axis;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.text.DateFormat;
/*     */ import java.text.FieldPosition;
/*     */ import java.text.NumberFormat;
/*     */ import java.text.ParsePosition;
/*     */ import java.util.Arrays;
/*     */ import java.util.Date;
/*     */ import java.util.GregorianCalendar;
/*     */ import java.util.TimeZone;
/*     */ 
/*     */ public class QuarterDateFormat extends DateFormat implements Cloneable, Serializable {
/*     */   private static final long serialVersionUID = -6738465248529797176L;
/*     */   
/*  69 */   public static final String[] REGULAR_QUARTERS = new String[] { "1", "2", "3", "4" };
/*     */   
/*  73 */   public static final String[] ROMAN_QUARTERS = new String[] { "I", "II", "III", "IV" };
/*     */   
/*  77 */   private String[] quarters = REGULAR_QUARTERS;
/*     */   
/*     */   public QuarterDateFormat() {
/*  83 */     this(TimeZone.getDefault());
/*     */   }
/*     */   
/*     */   public QuarterDateFormat(TimeZone zone) {
/*  92 */     this(zone, REGULAR_QUARTERS);
/*     */   }
/*     */   
/*     */   public QuarterDateFormat(TimeZone zone, String[] quarterSymbols) {
/* 102 */     if (zone == null)
/* 103 */       throw new IllegalArgumentException("Null 'zone' argument."); 
/* 105 */     this.calendar = new GregorianCalendar(zone);
/* 106 */     this.quarters = quarterSymbols;
/* 111 */     this.numberFormat = NumberFormat.getNumberInstance();
/*     */   }
/*     */   
/*     */   public StringBuffer format(Date date, StringBuffer toAppendTo, FieldPosition fieldPosition) {
/* 125 */     this.calendar.setTime(date);
/* 126 */     int year = this.calendar.get(1);
/* 127 */     int month = this.calendar.get(2);
/* 128 */     toAppendTo.append(year);
/* 129 */     toAppendTo.append(" ");
/* 130 */     int quarter = month / 3;
/* 131 */     toAppendTo.append(this.quarters[quarter]);
/* 132 */     return toAppendTo;
/*     */   }
/*     */   
/*     */   public Date parse(String source, ParsePosition pos) {
/* 144 */     return null;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 155 */     if (obj == this)
/* 156 */       return true; 
/* 158 */     if (!(obj instanceof QuarterDateFormat))
/* 159 */       return false; 
/* 161 */     if (!super.equals(obj))
/* 162 */       return false; 
/* 164 */     QuarterDateFormat that = (QuarterDateFormat)obj;
/* 165 */     if (!Arrays.equals((Object[])this.quarters, (Object[])that.quarters))
/* 166 */       return false; 
/* 168 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\axis\QuarterDateFormat.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */