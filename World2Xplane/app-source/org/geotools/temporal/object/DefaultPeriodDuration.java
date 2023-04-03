/*     */ package org.geotools.temporal.object;
/*     */ 
/*     */ import org.geotools.util.SimpleInternationalString;
/*     */ import org.geotools.util.Utilities;
/*     */ import org.opengis.temporal.PeriodDuration;
/*     */ import org.opengis.util.InternationalString;
/*     */ 
/*     */ public class DefaultPeriodDuration extends DefaultDuration implements PeriodDuration {
/*  36 */   private static final InternationalString DESIGNATOR = (InternationalString)new SimpleInternationalString("P");
/*     */   
/*     */   private InternationalString years;
/*     */   
/*     */   private InternationalString months;
/*     */   
/*     */   private InternationalString weeks;
/*     */   
/*     */   private InternationalString days;
/*     */   
/*  41 */   private static final InternationalString TIME_INDICATOR = (InternationalString)new SimpleInternationalString("T");
/*     */   
/*     */   private InternationalString hours;
/*     */   
/*     */   private InternationalString minutes;
/*     */   
/*     */   private InternationalString seconds;
/*     */   
/*     */   public DefaultPeriodDuration(InternationalString years, InternationalString months, InternationalString week, InternationalString days, InternationalString hours, InternationalString minutes, InternationalString seconds) {
/*  58 */     this.years = years;
/*  59 */     this.months = months;
/*  60 */     this.weeks = week;
/*  61 */     this.days = days;
/*  62 */     this.hours = hours;
/*  63 */     this.minutes = minutes;
/*  64 */     this.seconds = seconds;
/*     */   }
/*     */   
/*     */   public DefaultPeriodDuration(long durationInMilliSeconds) {
/*     */     SimpleInternationalString simpleInternationalString1, simpleInternationalString2, simpleInternationalString3, simpleInternationalString4, simpleInternationalString5, simpleInternationalString6, simpleInternationalString7;
/*  73 */     long yearMS = 31536000000L;
/*  74 */     long monthMS = 2628000000L;
/*  75 */     long weekMS = 604800000L;
/*  76 */     long dayMS = 86400000L;
/*  77 */     long hourMS = 3600000L;
/*  78 */     long minMS = 60000L;
/*  79 */     long secondMS = 1000L;
/*  81 */     InternationalString _years = null;
/*  82 */     InternationalString _months = null;
/*  83 */     InternationalString _week = null;
/*  84 */     InternationalString _days = null;
/*  85 */     InternationalString _hours = null;
/*  86 */     InternationalString _minutes = null;
/*  87 */     InternationalString _seconds = null;
/*  89 */     long temp = durationInMilliSeconds / yearMS;
/*  90 */     if (temp >= 1L) {
/*  91 */       simpleInternationalString1 = new SimpleInternationalString(String.valueOf(temp));
/*  92 */       durationInMilliSeconds -= temp * yearMS;
/*     */     } 
/*  94 */     this.years = (InternationalString)simpleInternationalString1;
/*  96 */     temp = durationInMilliSeconds / monthMS;
/*  97 */     if (temp >= 1L) {
/*  98 */       simpleInternationalString2 = new SimpleInternationalString(String.valueOf(temp));
/*  99 */       durationInMilliSeconds -= temp * monthMS;
/*     */     } 
/* 101 */     this.months = (InternationalString)simpleInternationalString2;
/* 103 */     temp = durationInMilliSeconds / weekMS;
/* 104 */     if (temp >= 1L) {
/* 105 */       simpleInternationalString3 = new SimpleInternationalString(String.valueOf(temp));
/* 106 */       durationInMilliSeconds -= temp * weekMS;
/*     */     } 
/* 108 */     this.weeks = (InternationalString)simpleInternationalString3;
/* 111 */     temp = durationInMilliSeconds / dayMS;
/* 112 */     if (temp >= 1L) {
/* 113 */       simpleInternationalString4 = new SimpleInternationalString(String.valueOf(temp));
/* 114 */       durationInMilliSeconds -= temp * dayMS;
/*     */     } 
/* 116 */     this.days = (InternationalString)simpleInternationalString4;
/* 118 */     temp = durationInMilliSeconds / hourMS;
/* 119 */     if (temp >= 1L) {
/* 120 */       simpleInternationalString5 = new SimpleInternationalString(String.valueOf(temp));
/* 121 */       durationInMilliSeconds -= temp * hourMS;
/*     */     } 
/* 123 */     this.hours = (InternationalString)simpleInternationalString5;
/* 125 */     temp = durationInMilliSeconds / minMS;
/* 126 */     if (temp >= 1L) {
/* 127 */       simpleInternationalString6 = new SimpleInternationalString(String.valueOf(temp));
/* 128 */       durationInMilliSeconds -= temp * minMS;
/*     */     } 
/* 130 */     this.minutes = (InternationalString)simpleInternationalString6;
/* 132 */     temp = durationInMilliSeconds / secondMS;
/* 133 */     if (temp >= 1L) {
/* 134 */       simpleInternationalString7 = new SimpleInternationalString(String.valueOf(temp));
/* 135 */       durationInMilliSeconds -= temp * secondMS;
/*     */     } 
/* 137 */     this.seconds = (InternationalString)simpleInternationalString7;
/*     */   }
/*     */   
/*     */   public InternationalString getDesignator() {
/* 149 */     return DESIGNATOR;
/*     */   }
/*     */   
/*     */   public InternationalString getYears() {
/* 157 */     return this.years;
/*     */   }
/*     */   
/*     */   public InternationalString getMonths() {
/* 165 */     return this.months;
/*     */   }
/*     */   
/*     */   public InternationalString getDays() {
/* 173 */     return this.days;
/*     */   }
/*     */   
/*     */   public InternationalString getTimeIndicator() {
/* 181 */     return TIME_INDICATOR;
/*     */   }
/*     */   
/*     */   public InternationalString getHours() {
/* 189 */     return this.hours;
/*     */   }
/*     */   
/*     */   public InternationalString getMinutes() {
/* 197 */     return this.minutes;
/*     */   }
/*     */   
/*     */   public InternationalString getSeconds() {
/* 205 */     return this.seconds;
/*     */   }
/*     */   
/*     */   public void setYears(InternationalString years) {
/* 209 */     this.years = years;
/*     */   }
/*     */   
/*     */   public void setMonths(InternationalString months) {
/* 213 */     this.months = months;
/*     */   }
/*     */   
/*     */   public void setDays(InternationalString days) {
/* 217 */     this.days = days;
/*     */   }
/*     */   
/*     */   public void setHours(InternationalString hours) {
/* 221 */     this.hours = hours;
/*     */   }
/*     */   
/*     */   public void setMinutes(InternationalString minutes) {
/* 225 */     this.minutes = minutes;
/*     */   }
/*     */   
/*     */   public void setSeconds(InternationalString seconds) {
/* 229 */     this.seconds = seconds;
/*     */   }
/*     */   
/*     */   public InternationalString getWeek() {
/* 233 */     return this.weeks;
/*     */   }
/*     */   
/*     */   public void setWeek(InternationalString week) {
/* 237 */     this.weeks = week;
/*     */   }
/*     */   
/*     */   public long getTimeInMillis() {
/* 245 */     String periodDescription = toString();
/* 246 */     long yearMS = 31536000000L;
/* 247 */     long monthMS = 2628000000L;
/* 248 */     long weekMS = 604800000L;
/* 249 */     long dayMS = 86400000L;
/* 250 */     long hourMS = 3600000L;
/* 251 */     long minMS = 60000L;
/* 252 */     long secondMS = 1000L;
/* 253 */     long response = 0L;
/* 255 */     periodDescription = periodDescription.substring(1);
/* 258 */     if (periodDescription.indexOf('Y') != -1) {
/* 259 */       int nbYear = Integer.parseInt(periodDescription.substring(0, periodDescription.indexOf('Y')));
/* 260 */       response += nbYear * yearMS;
/* 261 */       periodDescription = periodDescription.substring(periodDescription.indexOf('Y') + 1);
/*     */     } 
/* 265 */     if ((periodDescription.indexOf('M') != -1 && periodDescription.indexOf('T') == -1) || (periodDescription.indexOf('T') != -1 && periodDescription.indexOf('M') < periodDescription.indexOf('T') && periodDescription.indexOf('M') != -1)) {
/* 269 */       int nbMonth = Integer.parseInt(periodDescription.substring(0, periodDescription.indexOf('M')));
/* 270 */       response += nbMonth * monthMS;
/* 271 */       periodDescription = periodDescription.substring(periodDescription.indexOf('M') + 1);
/*     */     } 
/* 275 */     if (periodDescription.indexOf('W') != -1) {
/* 276 */       int nbWeek = Integer.parseInt(periodDescription.substring(0, periodDescription.indexOf('W')));
/* 277 */       response += nbWeek * weekMS;
/* 278 */       periodDescription = periodDescription.substring(periodDescription.indexOf('W') + 1);
/*     */     } 
/* 282 */     if (periodDescription.indexOf('D') != -1) {
/* 283 */       int nbDay = Integer.parseInt(periodDescription.substring(0, periodDescription.indexOf('D')));
/* 284 */       response += nbDay * dayMS;
/* 285 */       periodDescription = periodDescription.substring(periodDescription.indexOf('D') + 1);
/*     */     } 
/* 289 */     if (periodDescription.indexOf('T') != -1)
/* 290 */       periodDescription = periodDescription.substring(1); 
/* 294 */     if (periodDescription.indexOf('H') != -1) {
/* 295 */       int nbHour = Integer.parseInt(periodDescription.substring(0, periodDescription.indexOf('H')));
/* 296 */       response += nbHour * hourMS;
/* 297 */       periodDescription = periodDescription.substring(periodDescription.indexOf('H') + 1);
/*     */     } 
/* 301 */     if (periodDescription.indexOf('M') != -1) {
/* 302 */       int nbMin = Integer.parseInt(periodDescription.substring(0, periodDescription.indexOf('M')));
/* 303 */       response += nbMin * minMS;
/* 304 */       periodDescription = periodDescription.substring(periodDescription.indexOf('M') + 1);
/*     */     } 
/* 308 */     if (periodDescription.indexOf('S') != -1) {
/* 309 */       int nbSec = Integer.parseInt(periodDescription.substring(0, periodDescription.indexOf('S')));
/* 310 */       response += nbSec * secondMS;
/* 311 */       periodDescription = periodDescription.substring(periodDescription.indexOf('S') + 1);
/*     */     } 
/* 314 */     if (periodDescription.length() != 0)
/* 315 */       throw new IllegalArgumentException("The period duration string is malformed"); 
/* 317 */     return response;
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/* 322 */     if (object == this)
/* 323 */       return true; 
/* 325 */     if (object instanceof DefaultPeriodDuration) {
/* 326 */       DefaultPeriodDuration that = (DefaultPeriodDuration)object;
/* 328 */       return (Utilities.equals(this.days, that.days) && Utilities.equals(this.hours, that.hours) && Utilities.equals(this.minutes, that.minutes) && Utilities.equals(this.months, that.months) && Utilities.equals(this.seconds, that.seconds) && Utilities.equals(this.years, that.years));
/*     */     } 
/* 335 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 340 */     int hash = 5;
/* 341 */     hash = 37 * hash + ((this.days != null) ? this.days.hashCode() : 0);
/* 342 */     hash = 37 * hash + ((this.hours != null) ? this.hours.hashCode() : 0);
/* 343 */     hash = 37 * hash + ((this.minutes != null) ? this.minutes.hashCode() : 0);
/* 344 */     hash = 37 * hash + ((this.months != null) ? this.months.hashCode() : 0);
/* 345 */     hash = 37 * hash + ((this.seconds != null) ? this.seconds.hashCode() : 0);
/* 346 */     hash = 37 * hash + ((this.years != null) ? this.years.hashCode() : 0);
/* 347 */     return hash;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 355 */     StringBuilder s = new StringBuilder();
/* 356 */     s.append((CharSequence)DESIGNATOR);
/* 357 */     if (this.years != null)
/* 358 */       s.append((CharSequence)this.years).append("Y"); 
/* 360 */     if (this.months != null)
/* 361 */       s.append((CharSequence)this.months).append("M"); 
/* 363 */     if (this.weeks != null)
/* 364 */       s.append((CharSequence)this.weeks).append("W"); 
/* 366 */     if (this.days != null)
/* 367 */       s.append((CharSequence)this.days).append("D"); 
/* 369 */     if (this.hours != null || this.minutes != null || this.seconds != null)
/* 370 */       s.append((CharSequence)TIME_INDICATOR); 
/* 372 */     if (this.hours != null)
/* 373 */       s.append((CharSequence)this.hours).append("H"); 
/* 375 */     if (this.minutes != null)
/* 376 */       s.append((CharSequence)this.minutes).append("M"); 
/* 378 */     if (this.seconds != null)
/* 379 */       s.append((CharSequence)this.seconds).append("S"); 
/* 382 */     return s.toString();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\temporal\object\DefaultPeriodDuration.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */