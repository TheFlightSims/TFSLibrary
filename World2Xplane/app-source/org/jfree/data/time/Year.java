/*     */ package org.jfree.data.time;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import java.util.TimeZone;
/*     */ 
/*     */ public class Year extends RegularTimePeriod implements Serializable {
/*     */   private static final long serialVersionUID = -7659990929736074836L;
/*     */   
/*     */   private int year;
/*     */   
/*     */   public Year() {
/*  84 */     this(new Date());
/*     */   }
/*     */   
/*     */   public Year(int year) {
/*  95 */     if (year < 1900 || year > 9999)
/*  98 */       throw new IllegalArgumentException("Year constructor: year (" + year + ") outside valid range."); 
/* 103 */     this.year = year;
/*     */   }
/*     */   
/*     */   public Year(Date time) {
/* 114 */     this(time, RegularTimePeriod.DEFAULT_TIME_ZONE);
/*     */   }
/*     */   
/*     */   public Year(Date time, TimeZone zone) {
/* 125 */     Calendar calendar = Calendar.getInstance(zone);
/* 126 */     calendar.setTime(time);
/* 127 */     this.year = calendar.get(1);
/*     */   }
/*     */   
/*     */   public int getYear() {
/* 137 */     return this.year;
/*     */   }
/*     */   
/*     */   public RegularTimePeriod previous() {
/* 147 */     if (this.year > 1900)
/* 148 */       return new Year(this.year - 1); 
/* 151 */     return null;
/*     */   }
/*     */   
/*     */   public RegularTimePeriod next() {
/* 162 */     if (this.year < 9999)
/* 163 */       return new Year(this.year + 1); 
/* 166 */     return null;
/*     */   }
/*     */   
/*     */   public long getSerialIndex() {
/* 178 */     return this.year;
/*     */   }
/*     */   
/*     */   public long getFirstMillisecond(Calendar calendar) {
/* 190 */     Day jan1 = new Day(1, 1, this.year);
/* 191 */     return jan1.getFirstMillisecond(calendar);
/*     */   }
/*     */   
/*     */   public long getLastMillisecond(Calendar calendar) {
/* 203 */     Day dec31 = new Day(31, 12, this.year);
/* 204 */     return dec31.getLastMillisecond(calendar);
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/* 219 */     if (object != null) {
/* 220 */       if (object instanceof Year) {
/* 221 */         Year target = (Year)object;
/* 222 */         return (this.year == target.getYear());
/*     */       } 
/* 225 */       return false;
/*     */     } 
/* 229 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 243 */     int result = 17;
/* 244 */     int c = this.year;
/* 245 */     result = 37 * result + c;
/* 246 */     return result;
/*     */   }
/*     */   
/*     */   public int compareTo(Object o1) {
/*     */     int result;
/* 265 */     if (o1 instanceof Year) {
/* 266 */       Year y = (Year)o1;
/* 267 */       result = this.year - y.getYear();
/* 272 */     } else if (o1 instanceof RegularTimePeriod) {
/* 274 */       result = 0;
/*     */     } else {
/* 281 */       result = 1;
/*     */     } 
/* 284 */     return result;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 294 */     return Integer.toString(this.year);
/*     */   }
/*     */   
/*     */   public static Year parseYear(String s) {
/*     */     int y;
/*     */     try {
/* 312 */       y = Integer.parseInt(s.trim());
/* 314 */     } catch (NumberFormatException e) {
/* 315 */       throw new TimePeriodFormatException("Cannot parse string.");
/*     */     } 
/*     */     try {
/* 320 */       return new Year(y);
/* 322 */     } catch (IllegalArgumentException e) {
/* 323 */       throw new TimePeriodFormatException("Year outside valid range.");
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\data\time\Year.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */