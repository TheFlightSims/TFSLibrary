/*     */ package org.jfree.data.time;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.text.DateFormat;
/*     */ import java.text.ParseException;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import java.util.TimeZone;
/*     */ import org.jfree.date.SerialDate;
/*     */ 
/*     */ public class Day extends RegularTimePeriod implements Serializable {
/*     */   private static final long serialVersionUID = -7082667380758962755L;
/*     */   
/*  84 */   protected static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
/*     */   
/*  89 */   protected static final DateFormat DATE_FORMAT_SHORT = DateFormat.getDateInstance(3);
/*     */   
/*  93 */   protected static final DateFormat DATE_FORMAT_MEDIUM = DateFormat.getDateInstance(2);
/*     */   
/*  97 */   protected static final DateFormat DATE_FORMAT_LONG = DateFormat.getDateInstance(1);
/*     */   
/*     */   private SerialDate serialDate;
/*     */   
/*     */   public Day() {
/* 107 */     this(new Date());
/*     */   }
/*     */   
/*     */   public Day(int day, int month, int year) {
/* 118 */     this.serialDate = SerialDate.createInstance(day, month, year);
/*     */   }
/*     */   
/*     */   public Day(SerialDate serialDate) {
/* 127 */     if (serialDate == null)
/* 128 */       throw new IllegalArgumentException("Null 'serialDate' argument."); 
/* 130 */     this.serialDate = serialDate;
/*     */   }
/*     */   
/*     */   public Day(Date time) {
/* 141 */     this(time, RegularTimePeriod.DEFAULT_TIME_ZONE);
/*     */   }
/*     */   
/*     */   public Day(Date time, TimeZone zone) {
/* 151 */     if (time == null)
/* 152 */       throw new IllegalArgumentException("Null 'time' argument."); 
/* 154 */     if (zone == null)
/* 155 */       throw new IllegalArgumentException("Null 'zone' argument."); 
/* 157 */     Calendar calendar = Calendar.getInstance(zone);
/* 158 */     calendar.setTime(time);
/* 159 */     int d = calendar.get(5);
/* 160 */     int m = calendar.get(2) + 1;
/* 161 */     int y = calendar.get(1);
/* 162 */     this.serialDate = SerialDate.createInstance(d, m, y);
/*     */   }
/*     */   
/*     */   public SerialDate getSerialDate() {
/* 175 */     return this.serialDate;
/*     */   }
/*     */   
/*     */   public int getYear() {
/* 184 */     return this.serialDate.getYYYY();
/*     */   }
/*     */   
/*     */   public int getMonth() {
/* 193 */     return this.serialDate.getMonth();
/*     */   }
/*     */   
/*     */   public int getDayOfMonth() {
/* 202 */     return this.serialDate.getDayOfMonth();
/*     */   }
/*     */   
/*     */   public RegularTimePeriod previous() {
/* 213 */     int serial = this.serialDate.toSerial();
/* 214 */     if (serial > 2) {
/* 215 */       SerialDate yesterday = SerialDate.createInstance(serial - 1);
/* 216 */       return new Day(yesterday);
/*     */     } 
/* 219 */     Day result = null;
/* 221 */     return result;
/*     */   }
/*     */   
/*     */   public RegularTimePeriod next() {
/* 235 */     int serial = this.serialDate.toSerial();
/* 236 */     if (serial < 2958465) {
/* 237 */       SerialDate tomorrow = SerialDate.createInstance(serial + 1);
/* 238 */       return new Day(tomorrow);
/*     */     } 
/* 241 */     Day result = null;
/* 243 */     return result;
/*     */   }
/*     */   
/*     */   public long getSerialIndex() {
/* 253 */     return this.serialDate.toSerial();
/*     */   }
/*     */   
/*     */   public long getFirstMillisecond(Calendar calendar) {
/* 266 */     int year = this.serialDate.getYYYY();
/* 267 */     int month = this.serialDate.getMonth();
/* 268 */     int day = this.serialDate.getDayOfMonth();
/* 269 */     calendar.clear();
/* 270 */     calendar.set(year, month - 1, day, 0, 0, 0);
/* 271 */     calendar.set(14, 0);
/* 273 */     return calendar.getTime().getTime();
/*     */   }
/*     */   
/*     */   public long getLastMillisecond(Calendar calendar) {
/* 287 */     int year = this.serialDate.getYYYY();
/* 288 */     int month = this.serialDate.getMonth();
/* 289 */     int day = this.serialDate.getDayOfMonth();
/* 290 */     calendar.clear();
/* 291 */     calendar.set(year, month - 1, day, 23, 59, 59);
/* 292 */     calendar.set(14, 999);
/* 294 */     return calendar.getTime().getTime();
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 310 */     if (obj == this)
/* 311 */       return true; 
/* 313 */     if (!(obj instanceof Day))
/* 314 */       return false; 
/* 316 */     Day that = (Day)obj;
/* 317 */     if (!this.serialDate.equals(that.getSerialDate()))
/* 318 */       return false; 
/* 320 */     return true;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 334 */     return this.serialDate.hashCode();
/*     */   }
/*     */   
/*     */   public int compareTo(Object o1) {
/*     */     int result;
/* 353 */     if (o1 instanceof Day) {
/* 354 */       Day d = (Day)o1;
/* 355 */       result = -d.getSerialDate().compare(this.serialDate);
/* 360 */     } else if (o1 instanceof RegularTimePeriod) {
/* 362 */       result = 0;
/*     */     } else {
/* 369 */       result = 1;
/*     */     } 
/* 372 */     return result;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 382 */     return this.serialDate.toString();
/*     */   }
/*     */   
/*     */   public static Day parseDay(String s) {
/*     */     try {
/* 399 */       return new Day(DATE_FORMAT.parse(s));
/* 401 */     } catch (ParseException e1) {
/*     */       try {
/* 403 */         return new Day(DATE_FORMAT_SHORT.parse(s));
/* 405 */       } catch (ParseException e2) {
/* 409 */         return null;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\data\time\Day.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */