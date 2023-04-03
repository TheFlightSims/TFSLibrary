/*     */ package org.jfree.data.time;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import java.util.TimeZone;
/*     */ 
/*     */ public class Week extends RegularTimePeriod implements Serializable {
/*     */   private static final long serialVersionUID = 1856387786939865061L;
/*     */   
/*     */   public static final int FIRST_WEEK_IN_YEAR = 1;
/*     */   
/*     */   public static final int LAST_WEEK_IN_YEAR = 53;
/*     */   
/*     */   private Year year;
/*     */   
/*     */   private int week;
/*     */   
/*     */   public Week() {
/* 104 */     this(new Date());
/*     */   }
/*     */   
/*     */   public Week(int week, int year) {
/* 114 */     this(week, new Year(year));
/*     */   }
/*     */   
/*     */   public Week(int week, Year year) {
/* 124 */     if (week < 1 && week > 53)
/* 125 */       throw new IllegalArgumentException("The 'week' argument must be in the range 1 - 53."); 
/* 129 */     this.week = week;
/* 130 */     this.year = year;
/*     */   }
/*     */   
/*     */   public Week(Date time) {
/* 141 */     this(time, RegularTimePeriod.DEFAULT_TIME_ZONE);
/*     */   }
/*     */   
/*     */   public Week(Date time, TimeZone zone) {
/* 152 */     if (time == null)
/* 153 */       throw new IllegalArgumentException("Null 'time' argument."); 
/* 155 */     if (zone == null)
/* 156 */       throw new IllegalArgumentException("Null 'zone' argument."); 
/* 158 */     Calendar calendar = Calendar.getInstance(zone);
/* 159 */     calendar.setTime(time);
/* 164 */     int tempWeek = calendar.get(3);
/* 165 */     if (tempWeek == 1 && calendar.get(2) == 11) {
/* 167 */       this.week = 1;
/* 168 */       this.year = new Year(calendar.get(1) + 1);
/*     */     } else {
/* 171 */       this.week = Math.min(tempWeek, 53);
/* 172 */       this.year = new Year(calendar.get(1));
/*     */     } 
/*     */   }
/*     */   
/*     */   public Year getYear() {
/* 183 */     return this.year;
/*     */   }
/*     */   
/*     */   public int getYearValue() {
/* 192 */     return this.year.getYear();
/*     */   }
/*     */   
/*     */   public int getWeek() {
/* 201 */     return this.week;
/*     */   }
/*     */   
/*     */   public RegularTimePeriod previous() {
/*     */     Week result;
/* 215 */     if (this.week != 1) {
/* 216 */       result = new Week(this.week - 1, this.year);
/*     */     } else {
/* 220 */       Year prevYear = (Year)this.year.previous();
/* 221 */       if (prevYear != null) {
/* 222 */         int yy = prevYear.getYear();
/* 223 */         Calendar prevYearCalendar = Calendar.getInstance();
/* 224 */         prevYearCalendar.set(yy, 11, 31);
/* 225 */         result = new Week(prevYearCalendar.getActualMaximum(3), prevYear);
/*     */       } else {
/* 231 */         result = null;
/*     */       } 
/*     */     } 
/* 234 */     return result;
/*     */   }
/*     */   
/*     */   public RegularTimePeriod next() {
/*     */     Week result;
/* 250 */     if (this.week < 52) {
/* 251 */       result = new Week(this.week + 1, this.year);
/*     */     } else {
/* 254 */       Calendar calendar = Calendar.getInstance();
/* 255 */       calendar.set(this.year.getYear(), 11, 31);
/* 256 */       int actualMaxWeek = calendar.getActualMaximum(3);
/* 258 */       if (this.week != actualMaxWeek) {
/* 259 */         result = new Week(this.week + 1, this.year);
/*     */       } else {
/* 262 */         Year nextYear = (Year)this.year.next();
/* 263 */         if (nextYear != null) {
/* 264 */           result = new Week(1, nextYear);
/*     */         } else {
/* 267 */           result = null;
/*     */         } 
/*     */       } 
/*     */     } 
/* 271 */     return result;
/*     */   }
/*     */   
/*     */   public long getSerialIndex() {
/* 281 */     return this.year.getYear() * 53L + this.week;
/*     */   }
/*     */   
/*     */   public long getFirstMillisecond(Calendar calendar) {
/* 293 */     Calendar c = (Calendar)calendar.clone();
/* 294 */     c.clear();
/* 295 */     c.set(1, this.year.getYear());
/* 296 */     c.set(3, this.week);
/* 297 */     c.set(7, c.getFirstDayOfWeek());
/* 298 */     c.set(10, 0);
/* 299 */     c.set(12, 0);
/* 300 */     c.set(13, 0);
/* 301 */     c.set(14, 0);
/* 303 */     return c.getTime().getTime();
/*     */   }
/*     */   
/*     */   public long getLastMillisecond(Calendar calendar) {
/* 315 */     RegularTimePeriod next = next();
/* 316 */     return next.getFirstMillisecond(calendar) - 1L;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 327 */     return "Week " + this.week + ", " + this.year;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 341 */     if (obj == this)
/* 342 */       return true; 
/* 344 */     if (!(obj instanceof Week))
/* 345 */       return false; 
/* 347 */     Week that = (Week)obj;
/* 348 */     if (this.week != that.week)
/* 349 */       return false; 
/* 351 */     if (!this.year.equals(that.year))
/* 352 */       return false; 
/* 354 */     return true;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 368 */     int result = 17;
/* 369 */     result = 37 * result + this.week;
/* 370 */     result = 37 * result + this.year.hashCode();
/* 371 */     return result;
/*     */   }
/*     */   
/*     */   public int compareTo(Object o1) {
/*     */     int result;
/* 390 */     if (o1 instanceof Week) {
/* 391 */       Week w = (Week)o1;
/* 392 */       result = this.year.getYear() - w.getYear().getYear();
/* 393 */       if (result == 0)
/* 394 */         result = this.week - w.getWeek(); 
/* 400 */     } else if (o1 instanceof RegularTimePeriod) {
/* 402 */       result = 0;
/*     */     } else {
/* 409 */       result = 1;
/*     */     } 
/* 412 */     return result;
/*     */   }
/*     */   
/*     */   public static Week parseWeek(String s) {
/* 429 */     Week result = null;
/* 430 */     if (s != null) {
/* 433 */       s = s.trim();
/* 435 */       int i = findSeparator(s);
/* 436 */       if (i != -1) {
/* 437 */         String s1 = s.substring(0, i).trim();
/* 438 */         String s2 = s.substring(i + 1, s.length()).trim();
/* 440 */         Year y = evaluateAsYear(s1);
/* 442 */         if (y != null) {
/* 443 */           int w = stringToWeek(s2);
/* 444 */           if (w == -1)
/* 445 */             throw new TimePeriodFormatException("Can't evaluate the week."); 
/* 449 */           result = new Week(w, y);
/*     */         } else {
/* 452 */           y = evaluateAsYear(s2);
/* 453 */           if (y != null) {
/* 454 */             int w = stringToWeek(s1);
/* 455 */             if (w == -1)
/* 456 */               throw new TimePeriodFormatException("Can't evaluate the week."); 
/* 460 */             result = new Week(w, y);
/*     */           } else {
/* 463 */             throw new TimePeriodFormatException("Can't evaluate the year.");
/*     */           } 
/*     */         } 
/*     */       } else {
/* 471 */         throw new TimePeriodFormatException("Could not find separator.");
/*     */       } 
/*     */     } 
/* 477 */     return result;
/*     */   }
/*     */   
/*     */   private static int findSeparator(String s) {
/* 491 */     int result = s.indexOf('-');
/* 492 */     if (result == -1)
/* 493 */       result = s.indexOf(','); 
/* 495 */     if (result == -1)
/* 496 */       result = s.indexOf(' '); 
/* 498 */     if (result == -1)
/* 499 */       result = s.indexOf('.'); 
/* 501 */     return result;
/*     */   }
/*     */   
/*     */   private static Year evaluateAsYear(String s) {
/* 515 */     Year result = null;
/*     */     try {
/* 517 */       result = Year.parseYear(s);
/* 519 */     } catch (TimePeriodFormatException e) {}
/* 522 */     return result;
/*     */   }
/*     */   
/*     */   private static int stringToWeek(String s) {
/* 535 */     int result = -1;
/* 536 */     s = s.replace('W', ' ');
/* 537 */     s = s.trim();
/*     */     try {
/* 539 */       result = Integer.parseInt(s);
/* 540 */       if (result < 1 || result > 53)
/* 541 */         result = -1; 
/* 544 */     } catch (NumberFormatException e) {}
/* 547 */     return result;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\data\time\Week.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */