/*     */ package ch.qos.logback.core.util;
/*     */ 
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ 
/*     */ public class Duration {
/*     */   private static final String DOUBLE_PART = "([0-9]*(.[0-9]+)?)";
/*     */   
/*     */   private static final int DOUBLE_GROUP = 1;
/*     */   
/*     */   private static final String UNIT_PART = "(|milli(second)?|second(e)?|minute|hour|day)s?";
/*     */   
/*     */   private static final int UNIT_GROUP = 3;
/*     */   
/*  40 */   private static final Pattern DURATION_PATTERN = Pattern.compile("([0-9]*(.[0-9]+)?)\\s*(|milli(second)?|second(e)?|minute|hour|day)s?", 2);
/*     */   
/*     */   static final long SECONDS_COEFFICIENT = 1000L;
/*     */   
/*     */   static final long MINUTES_COEFFICIENT = 60000L;
/*     */   
/*     */   static final long HOURS_COEFFICIENT = 3600000L;
/*     */   
/*     */   static final long DAYS_COEFFICIENT = 86400000L;
/*     */   
/*     */   final long millis;
/*     */   
/*     */   public Duration(long millis) {
/*  51 */     this.millis = millis;
/*     */   }
/*     */   
/*     */   public static Duration buildByMilliseconds(double value) {
/*  55 */     return new Duration((long)value);
/*     */   }
/*     */   
/*     */   public static Duration buildBySeconds(double value) {
/*  59 */     return new Duration((long)(1000.0D * value));
/*     */   }
/*     */   
/*     */   public static Duration buildByMinutes(double value) {
/*  63 */     return new Duration((long)(60000.0D * value));
/*     */   }
/*     */   
/*     */   public static Duration buildByHours(double value) {
/*  67 */     return new Duration((long)(3600000.0D * value));
/*     */   }
/*     */   
/*     */   public static Duration buildByDays(double value) {
/*  71 */     return new Duration((long)(8.64E7D * value));
/*     */   }
/*     */   
/*     */   public static Duration buildUnbounded() {
/*  75 */     return new Duration(Long.MAX_VALUE);
/*     */   }
/*     */   
/*     */   public long getMilliseconds() {
/*  79 */     return this.millis;
/*     */   }
/*     */   
/*     */   public static Duration valueOf(String durationStr) {
/*  83 */     Matcher matcher = DURATION_PATTERN.matcher(durationStr);
/*  85 */     if (matcher.matches()) {
/*  86 */       String doubleStr = matcher.group(1);
/*  87 */       String unitStr = matcher.group(3);
/*  89 */       double doubleValue = Double.valueOf(doubleStr).doubleValue();
/*  90 */       if (unitStr.equalsIgnoreCase("milli") || unitStr.equalsIgnoreCase("millisecond") || unitStr.length() == 0)
/*  92 */         return buildByMilliseconds(doubleValue); 
/*  93 */       if (unitStr.equalsIgnoreCase("second") || unitStr.equalsIgnoreCase("seconde"))
/*  95 */         return buildBySeconds(doubleValue); 
/*  96 */       if (unitStr.equalsIgnoreCase("minute"))
/*  97 */         return buildByMinutes(doubleValue); 
/*  98 */       if (unitStr.equalsIgnoreCase("hour"))
/*  99 */         return buildByHours(doubleValue); 
/* 100 */       if (unitStr.equalsIgnoreCase("day"))
/* 101 */         return buildByDays(doubleValue); 
/* 103 */       throw new IllegalStateException("Unexpected " + unitStr);
/*     */     } 
/* 106 */     throw new IllegalArgumentException("String value [" + durationStr + "] is not in the expected format.");
/*     */   }
/*     */   
/*     */   public String toString() {
/* 113 */     if (this.millis < 1000L)
/* 114 */       return this.millis + " milliseconds"; 
/* 115 */     if (this.millis < 60000L)
/* 116 */       return (this.millis / 1000L) + " seconds"; 
/* 117 */     if (this.millis < 3600000L)
/* 118 */       return (this.millis / 60000L) + " minutes"; 
/* 120 */     return (this.millis / 3600000L) + " hours";
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\cor\\util\Duration.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */