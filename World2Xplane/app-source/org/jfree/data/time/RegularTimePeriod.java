/*     */ package org.jfree.data.time;
/*     */ 
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import java.util.TimeZone;
/*     */ import org.jfree.date.MonthConstants;
/*     */ 
/*     */ public abstract class RegularTimePeriod implements TimePeriod, Comparable, MonthConstants {
/*     */   public static RegularTimePeriod createInstance(Class c, Date millisecond, TimeZone zone) {
/*  84 */     RegularTimePeriod result = null;
/*     */     try {
/*  86 */       Constructor constructor = c.getDeclaredConstructor(new Class[] { Date.class, TimeZone.class });
/*  89 */       result = constructor.newInstance(new Object[] { millisecond, zone });
/*  93 */     } catch (Exception e) {}
/*  96 */     return result;
/*     */   }
/*     */   
/*     */   public static Class downsize(Class c) {
/* 108 */     if (c.equals(Year.class))
/* 109 */       return Quarter.class; 
/* 111 */     if (c.equals(Quarter.class))
/* 112 */       return Month.class; 
/* 114 */     if (c.equals(Month.class))
/* 115 */       return Day.class; 
/* 117 */     if (c.equals(Day.class))
/* 118 */       return Hour.class; 
/* 120 */     if (c.equals(Hour.class))
/* 121 */       return Minute.class; 
/* 123 */     if (c.equals(Minute.class))
/* 124 */       return Second.class; 
/* 126 */     if (c.equals(Second.class))
/* 127 */       return Millisecond.class; 
/* 130 */     return Millisecond.class;
/*     */   }
/*     */   
/* 160 */   public static final TimeZone DEFAULT_TIME_ZONE = TimeZone.getDefault();
/*     */   
/* 163 */   public static final Calendar WORKING_CALENDAR = Calendar.getInstance(DEFAULT_TIME_ZONE);
/*     */   
/*     */   public Date getStart() {
/* 172 */     return new Date(getFirstMillisecond());
/*     */   }
/*     */   
/*     */   public Date getEnd() {
/* 181 */     return new Date(getLastMillisecond());
/*     */   }
/*     */   
/*     */   public long getFirstMillisecond() {
/* 191 */     return getFirstMillisecond(DEFAULT_TIME_ZONE);
/*     */   }
/*     */   
/*     */   public long getFirstMillisecond(TimeZone zone) {
/* 203 */     WORKING_CALENDAR.setTimeZone(zone);
/* 204 */     return getFirstMillisecond(WORKING_CALENDAR);
/*     */   }
/*     */   
/*     */   public long getLastMillisecond() {
/* 224 */     return getLastMillisecond(DEFAULT_TIME_ZONE);
/*     */   }
/*     */   
/*     */   public long getLastMillisecond(TimeZone zone) {
/* 236 */     WORKING_CALENDAR.setTimeZone(zone);
/* 237 */     return getLastMillisecond(WORKING_CALENDAR);
/*     */   }
/*     */   
/*     */   public long getMiddleMillisecond() {
/* 257 */     long m1 = getFirstMillisecond();
/* 258 */     long m2 = getLastMillisecond();
/* 259 */     return m1 + (m2 - m1) / 2L;
/*     */   }
/*     */   
/*     */   public long getMiddleMillisecond(TimeZone zone) {
/* 271 */     long m1 = getFirstMillisecond(zone);
/* 272 */     long m2 = getLastMillisecond(zone);
/* 273 */     return m1 + (m2 - m1) / 2L;
/*     */   }
/*     */   
/*     */   public long getMiddleMillisecond(Calendar calendar) {
/* 285 */     long m1 = getFirstMillisecond(calendar);
/* 286 */     long m2 = getLastMillisecond(calendar);
/* 287 */     return m1 + (m2 - m1) / 2L;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 296 */     return String.valueOf(getStart());
/*     */   }
/*     */   
/*     */   public abstract RegularTimePeriod previous();
/*     */   
/*     */   public abstract RegularTimePeriod next();
/*     */   
/*     */   public abstract long getSerialIndex();
/*     */   
/*     */   public abstract long getFirstMillisecond(Calendar paramCalendar);
/*     */   
/*     */   public abstract long getLastMillisecond(Calendar paramCalendar);
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\data\time\RegularTimePeriod.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */