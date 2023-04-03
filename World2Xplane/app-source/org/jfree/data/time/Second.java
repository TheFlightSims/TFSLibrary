/*     */ package org.jfree.data.time;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import java.util.TimeZone;
/*     */ 
/*     */ public class Second extends RegularTimePeriod implements Serializable {
/*     */   private static final long serialVersionUID = -6536564190712383466L;
/*     */   
/*     */   public static final int FIRST_SECOND_IN_MINUTE = 0;
/*     */   
/*     */   public static final int LAST_SECOND_IN_MINUTE = 59;
/*     */   
/*     */   private Minute minute;
/*     */   
/*     */   private int second;
/*     */   
/*     */   public Second() {
/*  90 */     this(new Date());
/*     */   }
/*     */   
/*     */   public Second(int second, Minute minute) {
/* 100 */     if (minute == null)
/* 101 */       throw new IllegalArgumentException("Null 'minute' argument."); 
/* 103 */     this.minute = minute;
/* 104 */     this.second = second;
/*     */   }
/*     */   
/*     */   public Second(int second, int minute, int hour, int day, int month, int year) {
/* 119 */     this(second, new Minute(minute, hour, day, month, year));
/*     */   }
/*     */   
/*     */   public Second(Date time) {
/* 128 */     this(time, RegularTimePeriod.DEFAULT_TIME_ZONE);
/*     */   }
/*     */   
/*     */   public Second(Date time, TimeZone zone) {
/* 138 */     this.minute = new Minute(time, zone);
/* 139 */     Calendar calendar = Calendar.getInstance(zone);
/* 140 */     calendar.setTime(time);
/* 141 */     this.second = calendar.get(13);
/*     */   }
/*     */   
/*     */   public int getSecond() {
/* 150 */     return this.second;
/*     */   }
/*     */   
/*     */   public Minute getMinute() {
/* 159 */     return this.minute;
/*     */   }
/*     */   
/*     */   public RegularTimePeriod previous() {
/* 169 */     Second result = null;
/* 170 */     if (this.second != 0) {
/* 171 */       result = new Second(this.second - 1, this.minute);
/*     */     } else {
/* 174 */       Minute previous = (Minute)this.minute.previous();
/* 175 */       if (previous != null)
/* 176 */         result = new Second(59, previous); 
/*     */     } 
/* 179 */     return result;
/*     */   }
/*     */   
/*     */   public RegularTimePeriod next() {
/* 190 */     Second result = null;
/* 191 */     if (this.second != 59) {
/* 192 */       result = new Second(this.second + 1, this.minute);
/*     */     } else {
/* 195 */       Minute next = (Minute)this.minute.next();
/* 196 */       if (next != null)
/* 197 */         result = new Second(0, next); 
/*     */     } 
/* 200 */     return result;
/*     */   }
/*     */   
/*     */   public long getSerialIndex() {
/* 210 */     return this.minute.getSerialIndex() * 60L + this.second;
/*     */   }
/*     */   
/*     */   public long getFirstMillisecond(Calendar calendar) {
/* 221 */     return this.minute.getFirstMillisecond(calendar) + this.second * 1000L;
/*     */   }
/*     */   
/*     */   public long getLastMillisecond(Calendar calendar) {
/* 232 */     return this.minute.getFirstMillisecond(calendar) + this.second * 1000L + 999L;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 248 */     if (obj instanceof Second) {
/* 249 */       Second s = (Second)obj;
/* 250 */       return (this.second == s.getSecond() && this.minute.equals(s.getMinute()));
/*     */     } 
/* 254 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 268 */     int result = 17;
/* 269 */     result = 37 * result + this.second;
/* 270 */     result = 37 * result + this.minute.hashCode();
/* 271 */     return result;
/*     */   }
/*     */   
/*     */   public int compareTo(Object o1) {
/*     */     int result;
/* 289 */     if (o1 instanceof Second) {
/* 290 */       Second s = (Second)o1;
/* 291 */       result = this.minute.compareTo(s.minute);
/* 292 */       if (result == 0)
/* 293 */         result = this.second - s.second; 
/* 299 */     } else if (o1 instanceof RegularTimePeriod) {
/* 301 */       result = 0;
/*     */     } else {
/* 308 */       result = 1;
/*     */     } 
/* 311 */     return result;
/*     */   }
/*     */   
/*     */   public static Second parseSecond(String s) {
/* 326 */     Second result = null;
/* 327 */     s = s.trim();
/* 329 */     String daystr = s.substring(0, Math.min(10, s.length()));
/* 330 */     Day day = Day.parseDay(daystr);
/* 331 */     if (day != null) {
/* 332 */       String hmsstr = s.substring(Math.min(daystr.length() + 1, s.length()), s.length());
/* 335 */       hmsstr = hmsstr.trim();
/* 337 */       int l = hmsstr.length();
/* 338 */       String hourstr = hmsstr.substring(0, Math.min(2, l));
/* 339 */       String minstr = hmsstr.substring(Math.min(3, l), Math.min(5, l));
/* 340 */       String secstr = hmsstr.substring(Math.min(6, l), Math.min(8, l));
/* 341 */       int hour = Integer.parseInt(hourstr);
/* 343 */       if (hour >= 0 && hour <= 23) {
/* 345 */         int minute = Integer.parseInt(minstr);
/* 346 */         if (minute >= 0 && minute <= 59) {
/* 348 */           Minute m = new Minute(minute, new Hour(hour, day));
/* 349 */           int second = Integer.parseInt(secstr);
/* 350 */           if (second >= 0 && second <= 59)
/* 351 */             result = new Second(second, m); 
/*     */         } 
/*     */       } 
/*     */     } 
/* 357 */     return result;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\data\time\Second.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */