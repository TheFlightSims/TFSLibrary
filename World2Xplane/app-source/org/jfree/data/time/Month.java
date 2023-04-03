/*     */ package org.jfree.data.time;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import java.util.TimeZone;
/*     */ import org.jfree.date.SerialDate;
/*     */ 
/*     */ public class Month extends RegularTimePeriod implements Serializable {
/*     */   private static final long serialVersionUID = -5090216912548722570L;
/*     */   
/*     */   private int month;
/*     */   
/*     */   private Year year;
/*     */   
/*     */   public Month() {
/*  88 */     this(new Date());
/*     */   }
/*     */   
/*     */   public Month(int month, int year) {
/*  98 */     this(month, new Year(year));
/*     */   }
/*     */   
/*     */   public Month(int month, Year year) {
/* 109 */     if (month < 1 || month > 12)
/* 110 */       throw new IllegalArgumentException("Month outside valid range."); 
/* 112 */     this.month = month;
/* 113 */     this.year = year;
/*     */   }
/*     */   
/*     */   public Month(Date time) {
/* 124 */     this(time, RegularTimePeriod.DEFAULT_TIME_ZONE);
/*     */   }
/*     */   
/*     */   public Month(Date time, TimeZone zone) {
/* 134 */     Calendar calendar = Calendar.getInstance(zone);
/* 135 */     calendar.setTime(time);
/* 136 */     this.month = calendar.get(2) + 1;
/* 137 */     this.year = new Year(calendar.get(1));
/*     */   }
/*     */   
/*     */   public Year getYear() {
/* 146 */     return this.year;
/*     */   }
/*     */   
/*     */   public int getYearValue() {
/* 155 */     return this.year.getYear();
/*     */   }
/*     */   
/*     */   public int getMonth() {
/* 164 */     return this.month;
/*     */   }
/*     */   
/*     */   public RegularTimePeriod previous() {
/*     */     Month result;
/* 175 */     if (this.month != 1) {
/* 176 */       result = new Month(this.month - 1, this.year);
/*     */     } else {
/* 179 */       Year prevYear = (Year)this.year.previous();
/* 180 */       if (prevYear != null) {
/* 181 */         result = new Month(12, prevYear);
/*     */       } else {
/* 184 */         result = null;
/*     */       } 
/*     */     } 
/* 187 */     return result;
/*     */   }
/*     */   
/*     */   public RegularTimePeriod next() {
/*     */     Month result;
/* 198 */     if (this.month != 12) {
/* 199 */       result = new Month(this.month + 1, this.year);
/*     */     } else {
/* 202 */       Year nextYear = (Year)this.year.next();
/* 203 */       if (nextYear != null) {
/* 204 */         result = new Month(1, nextYear);
/*     */       } else {
/* 207 */         result = null;
/*     */       } 
/*     */     } 
/* 210 */     return result;
/*     */   }
/*     */   
/*     */   public long getSerialIndex() {
/* 219 */     return this.year.getYear() * 12L + this.month;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 230 */     return SerialDate.monthCodeToString(this.month) + " " + this.year;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 245 */     if (obj != null) {
/* 246 */       if (obj instanceof Month) {
/* 247 */         Month target = (Month)obj;
/* 248 */         return (this.month == target.getMonth() && this.year.equals(target.getYear()));
/*     */       } 
/* 254 */       return false;
/*     */     } 
/* 258 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 273 */     int result = 17;
/* 274 */     result = 37 * result + this.month;
/* 275 */     result = 37 * result + this.year.hashCode();
/* 276 */     return result;
/*     */   }
/*     */   
/*     */   public int compareTo(Object o1) {
/*     */     int result;
/* 294 */     if (o1 instanceof Month) {
/* 295 */       Month m = (Month)o1;
/* 296 */       result = this.year.getYear() - m.getYear().getYear();
/* 297 */       if (result == 0)
/* 298 */         result = this.month - m.getMonth(); 
/* 304 */     } else if (o1 instanceof RegularTimePeriod) {
/* 306 */       result = 0;
/*     */     } else {
/* 313 */       result = 1;
/*     */     } 
/* 316 */     return result;
/*     */   }
/*     */   
/*     */   public long getFirstMillisecond(Calendar calendar) {
/* 329 */     Day first = new Day(1, this.month, this.year.getYear());
/* 330 */     return first.getFirstMillisecond(calendar);
/*     */   }
/*     */   
/*     */   public long getLastMillisecond(Calendar calendar) {
/* 342 */     int eom = SerialDate.lastDayOfMonth(this.month, this.year.getYear());
/* 343 */     Day last = new Day(eom, this.month, this.year.getYear());
/* 344 */     return last.getLastMillisecond(calendar);
/*     */   }
/*     */   
/*     */   public static Month parseMonth(String s) {
/* 360 */     Month result = null;
/* 361 */     if (s != null) {
/* 364 */       s = s.trim();
/* 366 */       int i = findSeparator(s);
/* 367 */       if (i != -1) {
/* 368 */         String s1 = s.substring(0, i).trim();
/* 369 */         String s2 = s.substring(i + 1, s.length()).trim();
/* 371 */         Year year = evaluateAsYear(s1);
/* 373 */         if (year != null) {
/* 374 */           int month = SerialDate.stringToMonthCode(s2);
/* 375 */           if (month == -1)
/* 376 */             throw new TimePeriodFormatException("Can't evaluate the month."); 
/* 380 */           result = new Month(month, year);
/*     */         } else {
/* 383 */           year = evaluateAsYear(s2);
/* 384 */           if (year != null) {
/* 385 */             int month = SerialDate.stringToMonthCode(s1);
/* 386 */             if (month == -1)
/* 387 */               throw new TimePeriodFormatException("Can't evaluate the month."); 
/* 391 */             result = new Month(month, year);
/*     */           } else {
/* 394 */             throw new TimePeriodFormatException("Can't evaluate the year.");
/*     */           } 
/*     */         } 
/*     */       } else {
/* 402 */         throw new TimePeriodFormatException("Could not find separator.");
/*     */       } 
/*     */     } 
/* 408 */     return result;
/*     */   }
/*     */   
/*     */   private static int findSeparator(String s) {
/* 422 */     int result = s.indexOf('-');
/* 423 */     if (result == -1)
/* 424 */       result = s.indexOf(','); 
/* 426 */     if (result == -1)
/* 427 */       result = s.indexOf(' '); 
/* 429 */     if (result == -1)
/* 430 */       result = s.indexOf('.'); 
/* 432 */     return result;
/*     */   }
/*     */   
/*     */   private static Year evaluateAsYear(String s) {
/* 446 */     Year result = null;
/*     */     try {
/* 448 */       result = Year.parseYear(s);
/* 450 */     } catch (TimePeriodFormatException e) {}
/* 453 */     return result;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\data\time\Month.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */