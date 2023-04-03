/*     */ package org.geotools.temporal.object;
/*     */ 
/*     */ import java.text.ParseException;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import java.util.TimeZone;
/*     */ import java.util.logging.Logger;
/*     */ import javax.measure.unit.NonSI;
/*     */ import javax.measure.unit.SI;
/*     */ import javax.measure.unit.Unit;
/*     */ import org.geotools.temporal.reference.DefaultTemporalCoordinateSystem;
/*     */ import org.opengis.temporal.CalendarDate;
/*     */ import org.opengis.temporal.DateAndTime;
/*     */ import org.opengis.temporal.Duration;
/*     */ import org.opengis.temporal.JulianDate;
/*     */ import org.opengis.temporal.OrdinalPosition;
/*     */ import org.opengis.temporal.TemporalCoordinate;
/*     */ 
/*     */ public class Utils {
/*  48 */   Logger logger = Logger.getLogger("org.geotools.temporal");
/*     */   
/*     */   private static final long yearMS = 31536000000L;
/*     */   
/*     */   private static final long monthMS = 2628000000L;
/*     */   
/*     */   private static final long weekMS = 604800000L;
/*     */   
/*     */   private static final long dayMS = 86400000L;
/*     */   
/*     */   private static final long hourMS = 3600000L;
/*     */   
/*     */   private static final long minMS = 60000L;
/*     */   
/*     */   private static final long secondMS = 1000L;
/*     */   
/*     */   public static Date getDateFromString(String dateString) throws ParseException {
/*  84 */     String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ";
/*  85 */     String DATE_FORMAT2 = "yyyy-MM-dd";
/*  86 */     String DATE_FORMAT3 = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
/*  87 */     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
/*  88 */     SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
/*  89 */     SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
/*  91 */     if (dateString.contains("T")) {
/*  93 */       int index = dateString.lastIndexOf("+");
/*  94 */       if (index == -1)
/*  95 */         index = dateString.lastIndexOf("-"); 
/*  97 */       if (index > dateString.indexOf("T")) {
/*  98 */         String timezoneStr = dateString.substring(index + 1);
/* 100 */         if (timezoneStr.contains(":")) {
/* 102 */           timezoneStr = timezoneStr.replace(":", "");
/* 103 */           dateString = dateString.substring(0, index + 1).concat(timezoneStr);
/* 104 */         } else if (timezoneStr.length() == 2) {
/* 106 */           dateString = dateString.concat("00");
/*     */         } 
/* 108 */       } else if (dateString.endsWith("Z")) {
/* 110 */         dateString = dateString.substring(0, dateString.length() - 1).concat("+0000");
/*     */       } else {
/* 113 */         dateString = dateString + "+0000";
/*     */       } 
/* 115 */       String timezone = getTimeZone(dateString);
/* 116 */       sdf.setTimeZone(TimeZone.getTimeZone(timezone));
/* 118 */       if (dateString.contains("."))
/* 119 */         return sdf3.parse(dateString); 
/* 121 */       return sdf.parse(dateString);
/*     */     } 
/* 123 */     if (dateString.contains("-"))
/* 124 */       return sdf2.parse(dateString); 
/* 126 */     return null;
/*     */   }
/*     */   
/*     */   public static String getTimeZone(String dateString) {
/* 130 */     if (dateString.endsWith("Z"))
/* 131 */       return "GMT+0"; 
/* 133 */     int index = dateString.lastIndexOf("+");
/* 134 */     if (index == -1)
/* 135 */       index = dateString.lastIndexOf("-"); 
/* 137 */     if (index > dateString.indexOf("T"))
/* 138 */       return "GMT" + dateString.substring(index); 
/* 140 */     return TimeZone.getDefault().getID();
/*     */   }
/*     */   
/*     */   public static long getTimeInMillis(String periodDuration) {
/* 151 */     long time = 0L;
/* 153 */     periodDuration = periodDuration.substring(1);
/* 156 */     if (periodDuration.indexOf('Y') != -1) {
/* 157 */       int nbYear = Integer.parseInt(periodDuration.substring(0, periodDuration.indexOf('Y')));
/* 158 */       time += nbYear * 31536000000L;
/* 159 */       periodDuration = periodDuration.substring(periodDuration.indexOf('Y') + 1);
/*     */     } 
/* 163 */     if (periodDuration.indexOf('M') != -1 && (periodDuration.indexOf("T") == -1 || periodDuration.indexOf("T") > periodDuration.indexOf('M'))) {
/* 165 */       int nbMonth = Integer.parseInt(periodDuration.substring(0, periodDuration.indexOf('M')));
/* 166 */       time += nbMonth * 2628000000L;
/* 167 */       periodDuration = periodDuration.substring(periodDuration.indexOf('M') + 1);
/*     */     } 
/* 171 */     if (periodDuration.indexOf('W') != -1) {
/* 172 */       int nbWeek = Integer.parseInt(periodDuration.substring(0, periodDuration.indexOf('W')));
/* 173 */       time += nbWeek * 604800000L;
/* 174 */       periodDuration = periodDuration.substring(periodDuration.indexOf('W') + 1);
/*     */     } 
/* 178 */     if (periodDuration.indexOf('D') != -1) {
/* 179 */       int nbDay = Integer.parseInt(periodDuration.substring(0, periodDuration.indexOf('D')));
/* 180 */       time += nbDay * 86400000L;
/* 181 */       periodDuration = periodDuration.substring(periodDuration.indexOf('D') + 1);
/*     */     } 
/* 185 */     if (periodDuration.indexOf('T') != -1)
/* 186 */       periodDuration = periodDuration.substring(1); 
/* 190 */     if (periodDuration.indexOf('H') != -1) {
/* 191 */       int nbHour = Integer.parseInt(periodDuration.substring(0, periodDuration.indexOf('H')));
/* 192 */       time += nbHour * 3600000L;
/* 193 */       periodDuration = periodDuration.substring(periodDuration.indexOf('H') + 1);
/*     */     } 
/* 197 */     if (periodDuration.indexOf('M') != -1) {
/* 198 */       int nbMin = Integer.parseInt(periodDuration.substring(0, periodDuration.indexOf('M')));
/* 199 */       time += nbMin * 60000L;
/* 200 */       periodDuration = periodDuration.substring(periodDuration.indexOf('M') + 1);
/*     */     } 
/* 204 */     if (periodDuration.indexOf('S') != -1) {
/* 205 */       int nbSec = Integer.parseInt(periodDuration.substring(0, periodDuration.indexOf('S')));
/* 206 */       time += nbSec * 1000L;
/* 207 */       periodDuration = periodDuration.substring(periodDuration.indexOf('S') + 1);
/*     */     } 
/* 210 */     if (periodDuration.length() != 0)
/* 211 */       throw new IllegalArgumentException("The period descritpion is malformed"); 
/* 213 */     return time;
/*     */   }
/*     */   
/*     */   public static Date JulianToDate(JulianDate jdt) {
/* 220 */     if (jdt == null)
/* 221 */       return null; 
/* 223 */     Date response = null;
/* 225 */     int JGREG = 588829;
/* 227 */     int ja = jdt.getCoordinateValue().intValue();
/* 228 */     if (ja >= JGREG) {
/* 229 */       int jalpha = (int)(((ja - 1867216) - 0.25D) / 36524.25D);
/* 230 */       ja = ja + 1 + jalpha - jalpha / 4;
/*     */     } 
/* 233 */     int jb = ja + 1524;
/* 234 */     int jc = (int)(6680.0D + ((jb - 2439870) - 122.1D) / 365.25D);
/* 235 */     int jd = 365 * jc + jc / 4;
/* 236 */     int je = (int)((jb - jd) / 30.6001D);
/* 237 */     int day = jb - jd - (int)(30.6001D * je);
/* 238 */     int month = je - 1;
/* 239 */     if (month > 12)
/* 240 */       month -= 12; 
/* 242 */     int year = jc - 4715;
/* 243 */     if (month > 2)
/* 244 */       year--; 
/* 246 */     if (year <= 0)
/* 247 */       year--; 
/* 249 */     Calendar cal = Calendar.getInstance();
/* 250 */     cal.set(year, month, day);
/* 251 */     response = cal.getTime();
/* 252 */     return response;
/*     */   }
/*     */   
/*     */   public static Date calendarDateToDate(CalendarDate calDate) {
/* 261 */     if (calDate == null)
/* 262 */       return null; 
/* 264 */     Calendar calendar = Calendar.getInstance();
/* 265 */     DefaultCalendarDate caldate = (DefaultCalendarDate)calDate;
/* 266 */     if (caldate != null) {
/* 267 */       int[] cal = calDate.getCalendarDate();
/* 268 */       int year = 0;
/* 269 */       int month = 0;
/* 270 */       int day = 0;
/* 271 */       if (cal.length > 3)
/* 272 */         throw new IllegalArgumentException("The CalendarDate integer array is malformed ! see ISO 8601 format."); 
/* 274 */       year = cal[0];
/* 275 */       if (cal.length > 0)
/* 276 */         month = cal[1]; 
/* 278 */       if (cal.length > 1)
/* 279 */         day = cal[2]; 
/* 281 */       calendar.set(year, month, day);
/* 282 */       return calendar.getTime();
/*     */     } 
/* 285 */     return null;
/*     */   }
/*     */   
/*     */   public static Date dateAndTimeToDate(DateAndTime dateAndTime) {
/* 294 */     if (dateAndTime == null)
/* 295 */       return null; 
/* 297 */     Calendar calendar = Calendar.getInstance();
/* 298 */     DefaultDateAndTime dateTime = (DefaultDateAndTime)dateAndTime;
/* 299 */     if (dateTime != null) {
/* 300 */       int[] cal = dateTime.getCalendarDate();
/* 301 */       int year = 0;
/* 302 */       int month = 0;
/* 303 */       int day = 0;
/* 304 */       if (cal.length > 3)
/* 305 */         throw new IllegalArgumentException("The CalendarDate integer array is malformed ! see ISO 8601 format."); 
/* 307 */       year = cal[0];
/* 308 */       if (cal.length > 0)
/* 309 */         month = cal[1]; 
/* 311 */       if (cal.length > 1)
/* 312 */         day = cal[2]; 
/* 316 */       Number[] clock = dateTime.getClockTime();
/* 317 */       Number hour = Integer.valueOf(0);
/* 318 */       Number minute = Integer.valueOf(0);
/* 319 */       Number second = Integer.valueOf(0);
/* 320 */       if (clock.length > 3)
/* 321 */         throw new IllegalArgumentException("The ClockTime Number array is malformed ! see ISO 8601 format."); 
/* 323 */       hour = clock[0];
/* 324 */       if (clock.length > 0)
/* 325 */         minute = clock[1]; 
/* 327 */       if (clock.length > 1)
/* 328 */         second = clock[2]; 
/* 331 */       calendar.set(year, month, day, hour.intValue(), minute.intValue(), second.intValue());
/* 332 */       return calendar.getTime();
/*     */     } 
/* 334 */     return null;
/*     */   }
/*     */   
/*     */   public static Date temporalCoordToDate(TemporalCoordinate temporalCoord) {
/* 342 */     if (temporalCoord == null)
/* 343 */       return null; 
/* 345 */     Calendar calendar = Calendar.getInstance();
/* 346 */     DefaultTemporalCoordinate timeCoord = (DefaultTemporalCoordinate)temporalCoord;
/* 347 */     Number value = timeCoord.getCoordinateValue();
/* 348 */     if (timeCoord.getFrame() instanceof org.opengis.temporal.TemporalCoordinateSystem) {
/* 349 */       DefaultTemporalCoordinateSystem coordSystem = (DefaultTemporalCoordinateSystem)timeCoord.getFrame();
/* 350 */       Date origin = coordSystem.getOrigin();
/* 351 */       String interval = coordSystem.getInterval().toString();
/* 353 */       Long timeInMS = Long.valueOf(0L);
/* 355 */       if (interval.equals("year")) {
/* 356 */         timeInMS = Long.valueOf(value.longValue() * 31536000000L);
/* 357 */       } else if (interval.equals("month")) {
/* 358 */         timeInMS = Long.valueOf(value.longValue() * 2628000000L);
/* 359 */       } else if (interval.equals("week")) {
/* 360 */         timeInMS = Long.valueOf(value.longValue() * 604800000L);
/* 361 */       } else if (interval.equals("day")) {
/* 362 */         timeInMS = Long.valueOf(value.longValue() * 86400000L);
/* 363 */       } else if (interval.equals("hour")) {
/* 364 */         timeInMS = Long.valueOf(value.longValue() * 3600000L);
/* 365 */       } else if (interval.equals("minute")) {
/* 366 */         timeInMS = Long.valueOf(value.longValue() * 60000L);
/* 367 */       } else if (interval.equals("second")) {
/* 368 */         timeInMS = Long.valueOf(value.longValue() * 1000L);
/*     */       } else {
/* 370 */         throw new IllegalArgumentException(" The interval of TemporalCoordinateSystem for this TemporalCoordinate object is unknown ! ");
/*     */       } 
/* 372 */       timeInMS = Long.valueOf(timeInMS.longValue() + origin.getTime());
/* 373 */       calendar.setTimeInMillis(timeInMS.longValue());
/* 374 */       return calendar.getTime();
/*     */     } 
/* 376 */     throw new IllegalArgumentException("The frame of this TemporalCoordinate object must be an instance of TemporalCoordinateSystem");
/*     */   }
/*     */   
/*     */   public static Date ordinalToDate(OrdinalPosition ordinalPosition) {
/* 381 */     if (ordinalPosition == null)
/* 382 */       return null; 
/* 384 */     Calendar calendar = Calendar.getInstance();
/* 385 */     if (ordinalPosition.getOrdinalPosition() != null) {
/* 386 */       Date beginEra = ordinalPosition.getOrdinalPosition().getBeginning();
/* 387 */       Date endEra = ordinalPosition.getOrdinalPosition().getEnd();
/* 388 */       Long middle = Long.valueOf((endEra.getTime() - beginEra.getTime()) / 2L + beginEra.getTime());
/* 389 */       calendar.setTimeInMillis(middle.longValue());
/* 390 */       return calendar.getTime();
/*     */     } 
/* 392 */     return null;
/*     */   }
/*     */   
/*     */   public static Unit getUnitFromDuration(Duration duration) {
/* 400 */     if (duration == null)
/* 401 */       return null; 
/* 403 */     DefaultDuration duration_ = (DefaultDuration)duration;
/* 404 */     long mills = duration_.getTimeInMillis();
/* 405 */     long temp = mills / 31536000000L;
/* 406 */     if (temp >= 1L)
/* 407 */       return NonSI.YEAR; 
/* 409 */     temp = mills / 2628000000L;
/* 410 */     if (temp >= 1L)
/* 411 */       return NonSI.MONTH; 
/* 413 */     temp = mills / 604800000L;
/* 414 */     if (temp >= 1L)
/* 415 */       return NonSI.WEEK; 
/* 417 */     temp = mills / 86400000L;
/* 418 */     if (temp >= 1L)
/* 419 */       return NonSI.DAY; 
/* 421 */     temp = mills / 3600000L;
/* 422 */     if (temp >= 1L)
/* 423 */       return NonSI.HOUR; 
/* 425 */     temp = mills / 60000L;
/* 426 */     if (temp >= 1L)
/* 427 */       return NonSI.MINUTE; 
/* 429 */     temp = mills / 1000L;
/* 430 */     if (temp >= 1L)
/* 431 */       return (Unit)SI.SECOND; 
/* 433 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\temporal\object\Utils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */