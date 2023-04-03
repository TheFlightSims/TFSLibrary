/*     */ package org.jfree.data.time;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import java.util.TimeZone;
/*     */ import org.jfree.date.SerialDate;
/*     */ 
/*     */ public class Quarter extends RegularTimePeriod implements Serializable {
/*     */   private static final long serialVersionUID = 3810061714380888671L;
/*     */   
/*     */   public static final int FIRST_QUARTER = 1;
/*     */   
/*     */   public static final int LAST_QUARTER = 4;
/*     */   
/*  84 */   public static final int[] FIRST_MONTH_IN_QUARTER = new int[] { 0, 1, 4, 7, 10 };
/*     */   
/*  90 */   public static final int[] LAST_MONTH_IN_QUARTER = new int[] { 0, 3, 6, 9, 12 };
/*     */   
/*     */   private Year year;
/*     */   
/*     */   private int quarter;
/*     */   
/*     */   public Quarter() {
/* 105 */     this(new Date());
/*     */   }
/*     */   
/*     */   public Quarter(int quarter, int year) {
/* 115 */     this(quarter, new Year(year));
/*     */   }
/*     */   
/*     */   public Quarter(int quarter, Year year) {
/* 125 */     if (quarter < 1 && quarter > 4)
/* 126 */       throw new IllegalArgumentException("Quarter outside valid range."); 
/* 128 */     this.year = year;
/* 129 */     this.quarter = quarter;
/*     */   }
/*     */   
/*     */   public Quarter(Date time) {
/* 138 */     this(time, RegularTimePeriod.DEFAULT_TIME_ZONE);
/*     */   }
/*     */   
/*     */   public Quarter(Date time, TimeZone zone) {
/* 149 */     Calendar calendar = Calendar.getInstance(zone);
/* 150 */     calendar.setTime(time);
/* 151 */     int month = calendar.get(2) + 1;
/* 152 */     this.quarter = SerialDate.monthCodeToQuarter(month);
/* 153 */     this.year = new Year(calendar.get(1));
/*     */   }
/*     */   
/*     */   public int getQuarter() {
/* 163 */     return this.quarter;
/*     */   }
/*     */   
/*     */   public Year getYear() {
/* 172 */     return this.year;
/*     */   }
/*     */   
/*     */   public RegularTimePeriod previous() {
/*     */     Quarter result;
/* 183 */     if (this.quarter > 1) {
/* 184 */       result = new Quarter(this.quarter - 1, this.year);
/*     */     } else {
/* 187 */       Year prevYear = (Year)this.year.previous();
/* 188 */       if (prevYear != null) {
/* 189 */         result = new Quarter(4, prevYear);
/*     */       } else {
/* 192 */         result = null;
/*     */       } 
/*     */     } 
/* 195 */     return result;
/*     */   }
/*     */   
/*     */   public RegularTimePeriod next() {
/*     */     Quarter result;
/* 207 */     if (this.quarter < 4) {
/* 208 */       result = new Quarter(this.quarter + 1, this.year);
/*     */     } else {
/* 211 */       Year nextYear = (Year)this.year.next();
/* 212 */       if (nextYear != null) {
/* 213 */         result = new Quarter(1, nextYear);
/*     */       } else {
/* 216 */         result = null;
/*     */       } 
/*     */     } 
/* 219 */     return result;
/*     */   }
/*     */   
/*     */   public long getSerialIndex() {
/* 229 */     return this.year.getYear() * 4L + this.quarter;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 244 */     if (obj != null) {
/* 245 */       if (obj instanceof Quarter) {
/* 246 */         Quarter target = (Quarter)obj;
/* 247 */         return (this.quarter == target.getQuarter() && this.year.equals(target.getYear()));
/*     */       } 
/* 253 */       return false;
/*     */     } 
/* 257 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 272 */     int result = 17;
/* 273 */     result = 37 * result + this.quarter;
/* 274 */     result = 37 * result + this.year.hashCode();
/* 275 */     return result;
/*     */   }
/*     */   
/*     */   public int compareTo(Object o1) {
/*     */     int result;
/* 294 */     if (o1 instanceof Quarter) {
/* 295 */       Quarter q = (Quarter)o1;
/* 296 */       result = this.year.getYear() - q.getYear().getYear();
/* 297 */       if (result == 0)
/* 298 */         result = this.quarter - q.getQuarter(); 
/* 304 */     } else if (o1 instanceof RegularTimePeriod) {
/* 306 */       result = 0;
/*     */     } else {
/* 313 */       result = 1;
/*     */     } 
/* 316 */     return result;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 326 */     return "Q" + this.quarter + "/" + this.year;
/*     */   }
/*     */   
/*     */   public long getFirstMillisecond(Calendar calendar) {
/* 339 */     int month = FIRST_MONTH_IN_QUARTER[this.quarter];
/* 340 */     Day first = new Day(1, month, this.year.getYear());
/* 341 */     return first.getFirstMillisecond(calendar);
/*     */   }
/*     */   
/*     */   public long getLastMillisecond(Calendar calendar) {
/* 355 */     int month = LAST_MONTH_IN_QUARTER[this.quarter];
/* 356 */     int eom = SerialDate.lastDayOfMonth(month, this.year.getYear());
/* 357 */     Day last = new Day(eom, month, this.year.getYear());
/* 358 */     return last.getLastMillisecond(calendar);
/*     */   }
/*     */   
/*     */   public static Quarter parseQuarter(String s) {
/* 375 */     int i = s.indexOf("Q");
/* 376 */     if (i == -1)
/* 377 */       throw new TimePeriodFormatException("Missing Q."); 
/* 380 */     if (i == s.length() - 1)
/* 381 */       throw new TimePeriodFormatException("Q found at end of string."); 
/* 384 */     String qstr = s.substring(i + 1, i + 2);
/* 385 */     int quarter = Integer.parseInt(qstr);
/* 386 */     String remaining = s.substring(0, i) + s.substring(i + 2, s.length());
/* 389 */     remaining = remaining.replace('/', ' ');
/* 390 */     remaining = remaining.replace(',', ' ');
/* 391 */     remaining = remaining.replace('-', ' ');
/* 394 */     Year year = Year.parseYear(remaining.trim());
/* 395 */     Quarter result = new Quarter(quarter, year);
/* 396 */     return result;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\data\time\Quarter.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */