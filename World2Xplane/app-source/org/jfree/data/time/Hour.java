/*     */ package org.jfree.data.time;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import java.util.TimeZone;
/*     */ 
/*     */ public class Hour extends RegularTimePeriod implements Serializable {
/*     */   private static final long serialVersionUID = -835471579831937652L;
/*     */   
/*     */   public static final int FIRST_HOUR_IN_DAY = 0;
/*     */   
/*     */   public static final int LAST_HOUR_IN_DAY = 23;
/*     */   
/*     */   private Day day;
/*     */   
/*     */   private int hour;
/*     */   
/*     */   public Hour() {
/*  92 */     this(new Date());
/*     */   }
/*     */   
/*     */   public Hour(int hour, Day day) {
/* 102 */     if (day == null)
/* 103 */       throw new IllegalArgumentException("Null 'day' argument."); 
/* 105 */     this.hour = hour;
/* 106 */     this.day = day;
/*     */   }
/*     */   
/*     */   public Hour(int hour, int day, int month, int year) {
/* 118 */     this(hour, new Day(day, month, year));
/*     */   }
/*     */   
/*     */   public Hour(Date time) {
/* 128 */     this(time, RegularTimePeriod.DEFAULT_TIME_ZONE);
/*     */   }
/*     */   
/*     */   public Hour(Date time, TimeZone zone) {
/* 139 */     if (time == null)
/* 140 */       throw new IllegalArgumentException("Null 'time' argument."); 
/* 142 */     if (zone == null)
/* 143 */       throw new IllegalArgumentException("Null 'zone' argument."); 
/* 145 */     Calendar calendar = Calendar.getInstance(zone);
/* 146 */     calendar.setTime(time);
/* 147 */     this.hour = calendar.get(11);
/* 148 */     this.day = new Day(time, zone);
/*     */   }
/*     */   
/*     */   public int getHour() {
/* 157 */     return this.hour;
/*     */   }
/*     */   
/*     */   public Day getDay() {
/* 166 */     return this.day;
/*     */   }
/*     */   
/*     */   public int getYear() {
/* 175 */     return this.day.getYear();
/*     */   }
/*     */   
/*     */   public int getMonth() {
/* 184 */     return this.day.getMonth();
/*     */   }
/*     */   
/*     */   public int getDayOfMonth() {
/* 193 */     return this.day.getDayOfMonth();
/*     */   }
/*     */   
/*     */   public RegularTimePeriod previous() {
/*     */     Hour result;
/* 204 */     if (this.hour != 0) {
/* 205 */       result = new Hour(this.hour - 1, this.day);
/*     */     } else {
/* 208 */       Day prevDay = (Day)this.day.previous();
/* 209 */       if (prevDay != null) {
/* 210 */         result = new Hour(23, prevDay);
/*     */       } else {
/* 213 */         result = null;
/*     */       } 
/*     */     } 
/* 216 */     return result;
/*     */   }
/*     */   
/*     */   public RegularTimePeriod next() {
/*     */     Hour result;
/* 228 */     if (this.hour != 23) {
/* 229 */       result = new Hour(this.hour + 1, this.day);
/*     */     } else {
/* 232 */       Day nextDay = (Day)this.day.next();
/* 233 */       if (nextDay != null) {
/* 234 */         result = new Hour(0, nextDay);
/*     */       } else {
/* 237 */         result = null;
/*     */       } 
/*     */     } 
/* 240 */     return result;
/*     */   }
/*     */   
/*     */   public long getSerialIndex() {
/* 250 */     return this.day.getSerialIndex() * 24L + this.hour;
/*     */   }
/*     */   
/*     */   public long getFirstMillisecond(Calendar calendar) {
/* 262 */     int year = this.day.getYear();
/* 263 */     int month = this.day.getMonth() - 1;
/* 264 */     int dom = this.day.getDayOfMonth();
/* 266 */     calendar.set(year, month, dom, this.hour, 0, 0);
/* 267 */     calendar.set(14, 0);
/* 270 */     return calendar.getTime().getTime();
/*     */   }
/*     */   
/*     */   public long getLastMillisecond(Calendar calendar) {
/* 283 */     int year = this.day.getYear();
/* 284 */     int month = this.day.getMonth() - 1;
/* 285 */     int dom = this.day.getDayOfMonth();
/* 287 */     calendar.set(year, month, dom, this.hour, 59, 59);
/* 288 */     calendar.set(14, 999);
/* 291 */     return calendar.getTime().getTime();
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 307 */     if (obj == this)
/* 308 */       return true; 
/* 310 */     if (!(obj instanceof Hour))
/* 311 */       return false; 
/* 313 */     Hour that = (Hour)obj;
/* 314 */     if (this.hour != that.hour)
/* 315 */       return false; 
/* 317 */     if (!this.day.equals(that.day))
/* 318 */       return false; 
/* 320 */     return true;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 333 */     int result = 17;
/* 334 */     result = 37 * result + this.hour;
/* 335 */     result = 37 * result + this.day.hashCode();
/* 336 */     return result;
/*     */   }
/*     */   
/*     */   public int compareTo(Object o1) {
/*     */     int result;
/* 355 */     if (o1 instanceof Hour) {
/* 356 */       Hour h = (Hour)o1;
/* 357 */       result = getDay().compareTo(h.getDay());
/* 358 */       if (result == 0)
/* 359 */         result = this.hour - h.getHour(); 
/* 365 */     } else if (o1 instanceof RegularTimePeriod) {
/* 367 */       result = 0;
/*     */     } else {
/* 374 */       result = 1;
/*     */     } 
/* 377 */     return result;
/*     */   }
/*     */   
/*     */   public static Hour parseHour(String s) {
/* 393 */     Hour result = null;
/* 394 */     s = s.trim();
/* 396 */     String daystr = s.substring(0, Math.min(10, s.length()));
/* 397 */     Day day = Day.parseDay(daystr);
/* 398 */     if (day != null) {
/* 399 */       String hourstr = s.substring(Math.min(daystr.length() + 1, s.length()), s.length());
/* 402 */       hourstr = hourstr.trim();
/* 403 */       int hour = Integer.parseInt(hourstr);
/* 405 */       if (hour >= 0 && hour <= 23)
/* 406 */         result = new Hour(hour, day); 
/*     */     } 
/* 410 */     return result;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\data\time\Hour.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */