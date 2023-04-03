/*     */ package org.jfree.data.time;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import java.util.TimeZone;
/*     */ 
/*     */ public class Minute extends RegularTimePeriod implements Serializable {
/*     */   private static final long serialVersionUID = 2144572840034842871L;
/*     */   
/*     */   public static final int FIRST_MINUTE_IN_HOUR = 0;
/*     */   
/*     */   public static final int LAST_MINUTE_IN_HOUR = 59;
/*     */   
/*     */   private Hour hour;
/*     */   
/*     */   private int minute;
/*     */   
/*     */   public Minute() {
/*  94 */     this(new Date());
/*     */   }
/*     */   
/*     */   public Minute(int minute, Hour hour) {
/* 104 */     if (hour == null)
/* 105 */       throw new IllegalArgumentException("Null 'hour' argument."); 
/* 107 */     this.minute = minute;
/* 108 */     this.hour = hour;
/*     */   }
/*     */   
/*     */   public Minute(Date time) {
/* 118 */     this(time, RegularTimePeriod.DEFAULT_TIME_ZONE);
/*     */   }
/*     */   
/*     */   public Minute(Date time, TimeZone zone) {
/* 129 */     if (time == null)
/* 130 */       throw new IllegalArgumentException("Null 'time' argument."); 
/* 132 */     if (zone == null)
/* 133 */       throw new IllegalArgumentException("Null 'zone' argument."); 
/* 135 */     Calendar calendar = Calendar.getInstance(zone);
/* 136 */     calendar.setTime(time);
/* 137 */     int min = calendar.get(12);
/* 138 */     this.minute = min;
/* 139 */     this.hour = new Hour(time, zone);
/*     */   }
/*     */   
/*     */   public Minute(int minute, int hour, int day, int month, int year) {
/* 157 */     this(minute, new Hour(hour, new Day(day, month, year)));
/*     */   }
/*     */   
/*     */   public Hour getHour() {
/* 166 */     return this.hour;
/*     */   }
/*     */   
/*     */   public int getMinute() {
/* 175 */     return this.minute;
/*     */   }
/*     */   
/*     */   public RegularTimePeriod previous() {
/*     */     Minute result;
/* 186 */     if (this.minute != 0) {
/* 187 */       result = new Minute(this.minute - 1, this.hour);
/*     */     } else {
/* 190 */       Hour prevHour = (Hour)this.hour.previous();
/* 191 */       if (prevHour != null) {
/* 192 */         result = new Minute(59, prevHour);
/*     */       } else {
/* 195 */         result = null;
/*     */       } 
/*     */     } 
/* 198 */     return result;
/*     */   }
/*     */   
/*     */   public RegularTimePeriod next() {
/*     */     Minute result;
/* 210 */     if (this.minute != 59) {
/* 211 */       result = new Minute(this.minute + 1, this.hour);
/*     */     } else {
/* 214 */       Hour nextHour = (Hour)this.hour.next();
/* 215 */       if (nextHour != null) {
/* 216 */         result = new Minute(0, nextHour);
/*     */       } else {
/* 219 */         result = null;
/*     */       } 
/*     */     } 
/* 222 */     return result;
/*     */   }
/*     */   
/*     */   public long getSerialIndex() {
/* 232 */     return this.hour.getSerialIndex() * 60L + this.minute;
/*     */   }
/*     */   
/*     */   public long getFirstMillisecond(Calendar calendar) {
/* 244 */     int year = this.hour.getDay().getYear();
/* 245 */     int month = this.hour.getDay().getMonth() - 1;
/* 246 */     int day = this.hour.getDay().getDayOfMonth();
/* 248 */     calendar.clear();
/* 249 */     calendar.set(year, month, day, this.hour.getHour(), this.minute, 0);
/* 250 */     calendar.set(14, 0);
/* 253 */     return calendar.getTime().getTime();
/*     */   }
/*     */   
/*     */   public long getLastMillisecond(Calendar calendar) {
/* 266 */     int year = this.hour.getDay().getYear();
/* 267 */     int month = this.hour.getDay().getMonth() - 1;
/* 268 */     int day = this.hour.getDay().getDayOfMonth();
/* 270 */     calendar.clear();
/* 271 */     calendar.set(year, month, day, this.hour.getHour(), this.minute, 59);
/* 272 */     calendar.set(14, 999);
/* 275 */     return calendar.getTime().getTime();
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 291 */     if (obj == this)
/* 292 */       return true; 
/* 294 */     if (!(obj instanceof Minute))
/* 295 */       return false; 
/* 297 */     Minute that = (Minute)obj;
/* 298 */     if (this.minute != that.minute)
/* 299 */       return false; 
/* 301 */     if (!this.hour.equals(that.hour))
/* 302 */       return false; 
/* 304 */     return true;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 317 */     int result = 17;
/* 318 */     result = 37 * result + this.minute;
/* 319 */     result = 37 * result + this.hour.hashCode();
/* 320 */     return result;
/*     */   }
/*     */   
/*     */   public int compareTo(Object o1) {
/*     */     int result;
/* 339 */     if (o1 instanceof Minute) {
/* 340 */       Minute m = (Minute)o1;
/* 341 */       result = getHour().compareTo(m.getHour());
/* 342 */       if (result == 0)
/* 343 */         result = this.minute - m.getMinute(); 
/* 349 */     } else if (o1 instanceof RegularTimePeriod) {
/* 351 */       result = 0;
/*     */     } else {
/* 358 */       result = 1;
/*     */     } 
/* 361 */     return result;
/*     */   }
/*     */   
/*     */   public static Minute parseMinute(String s) {
/* 377 */     Minute result = null;
/* 378 */     s = s.trim();
/* 380 */     String daystr = s.substring(0, Math.min(10, s.length()));
/* 381 */     Day day = Day.parseDay(daystr);
/* 382 */     if (day != null) {
/* 383 */       String hmstr = s.substring(Math.min(daystr.length() + 1, s.length()), s.length());
/* 386 */       hmstr = hmstr.trim();
/* 388 */       String hourstr = hmstr.substring(0, Math.min(2, hmstr.length()));
/* 389 */       int hour = Integer.parseInt(hourstr);
/* 391 */       if (hour >= 0 && hour <= 23) {
/* 392 */         String minstr = hmstr.substring(Math.min(hourstr.length() + 1, hmstr.length()), hmstr.length());
/* 396 */         int minute = Integer.parseInt(minstr);
/* 397 */         if (minute >= 0 && minute <= 59)
/* 398 */           result = new Minute(minute, new Hour(hour, day)); 
/*     */       } 
/*     */     } 
/* 403 */     return result;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\data\time\Minute.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */