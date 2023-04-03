/*     */ package org.apache.commons.lang.time;
/*     */ 
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import java.util.Locale;
/*     */ import java.util.TimeZone;
/*     */ 
/*     */ public class DateFormatUtils {
/*  44 */   public static final FastDateFormat ISO_DATETIME_FORMAT = FastDateFormat.getInstance("yyyy-MM-dd'T'HH:mm:ss");
/*     */   
/*  51 */   public static final FastDateFormat ISO_DATETIME_TIME_ZONE_FORMAT = FastDateFormat.getInstance("yyyy-MM-dd'T'HH:mm:ssZZ");
/*     */   
/*  58 */   public static final FastDateFormat ISO_DATE_FORMAT = FastDateFormat.getInstance("yyyy-MM-dd");
/*     */   
/*  67 */   public static final FastDateFormat ISO_DATE_TIME_ZONE_FORMAT = FastDateFormat.getInstance("yyyy-MM-ddZZ");
/*     */   
/*  74 */   public static final FastDateFormat ISO_TIME_FORMAT = FastDateFormat.getInstance("'T'HH:mm:ss");
/*     */   
/*  81 */   public static final FastDateFormat ISO_TIME_TIME_ZONE_FORMAT = FastDateFormat.getInstance("'T'HH:mm:ssZZ");
/*     */   
/*  90 */   public static final FastDateFormat ISO_TIME_NO_T_FORMAT = FastDateFormat.getInstance("HH:mm:ss");
/*     */   
/*  99 */   public static final FastDateFormat ISO_TIME_NO_T_TIME_ZONE_FORMAT = FastDateFormat.getInstance("HH:mm:ssZZ");
/*     */   
/* 106 */   public static final FastDateFormat SMTP_DATETIME_FORMAT = FastDateFormat.getInstance("EEE, dd MMM yyyy HH:mm:ss Z", Locale.US);
/*     */   
/*     */   public static String formatUTC(long millis, String pattern) {
/* 128 */     return format(new Date(millis), pattern, DateUtils.UTC_TIME_ZONE, (Locale)null);
/*     */   }
/*     */   
/*     */   public static String formatUTC(Date date, String pattern) {
/* 139 */     return format(date, pattern, DateUtils.UTC_TIME_ZONE, (Locale)null);
/*     */   }
/*     */   
/*     */   public static String formatUTC(long millis, String pattern, Locale locale) {
/* 151 */     return format(new Date(millis), pattern, DateUtils.UTC_TIME_ZONE, locale);
/*     */   }
/*     */   
/*     */   public static String formatUTC(Date date, String pattern, Locale locale) {
/* 163 */     return format(date, pattern, DateUtils.UTC_TIME_ZONE, locale);
/*     */   }
/*     */   
/*     */   public static String format(long millis, String pattern) {
/* 174 */     return format(new Date(millis), pattern, (TimeZone)null, (Locale)null);
/*     */   }
/*     */   
/*     */   public static String format(Date date, String pattern) {
/* 185 */     return format(date, pattern, (TimeZone)null, (Locale)null);
/*     */   }
/*     */   
/*     */   public static String format(Calendar calendar, String pattern) {
/* 198 */     return format(calendar, pattern, (TimeZone)null, (Locale)null);
/*     */   }
/*     */   
/*     */   public static String format(long millis, String pattern, TimeZone timeZone) {
/* 210 */     return format(new Date(millis), pattern, timeZone, (Locale)null);
/*     */   }
/*     */   
/*     */   public static String format(Date date, String pattern, TimeZone timeZone) {
/* 222 */     return format(date, pattern, timeZone, (Locale)null);
/*     */   }
/*     */   
/*     */   public static String format(Calendar calendar, String pattern, TimeZone timeZone) {
/* 236 */     return format(calendar, pattern, timeZone, (Locale)null);
/*     */   }
/*     */   
/*     */   public static String format(long millis, String pattern, Locale locale) {
/* 248 */     return format(new Date(millis), pattern, (TimeZone)null, locale);
/*     */   }
/*     */   
/*     */   public static String format(Date date, String pattern, Locale locale) {
/* 260 */     return format(date, pattern, (TimeZone)null, locale);
/*     */   }
/*     */   
/*     */   public static String format(Calendar calendar, String pattern, Locale locale) {
/* 274 */     return format(calendar, pattern, (TimeZone)null, locale);
/*     */   }
/*     */   
/*     */   public static String format(long millis, String pattern, TimeZone timeZone, Locale locale) {
/* 287 */     return format(new Date(millis), pattern, timeZone, locale);
/*     */   }
/*     */   
/*     */   public static String format(Date date, String pattern, TimeZone timeZone, Locale locale) {
/* 300 */     FastDateFormat df = FastDateFormat.getInstance(pattern, timeZone, locale);
/* 301 */     return df.format(date);
/*     */   }
/*     */   
/*     */   public static String format(Calendar calendar, String pattern, TimeZone timeZone, Locale locale) {
/* 316 */     FastDateFormat df = FastDateFormat.getInstance(pattern, timeZone, locale);
/* 317 */     return df.format(calendar);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\lang\time\DateFormatUtils.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */