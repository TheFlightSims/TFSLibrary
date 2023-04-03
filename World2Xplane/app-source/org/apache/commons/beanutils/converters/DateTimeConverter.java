/*     */ package org.apache.commons.beanutils.converters;
/*     */ 
/*     */ import java.sql.Date;
/*     */ import java.sql.Time;
/*     */ import java.sql.Timestamp;
/*     */ import java.text.DateFormat;
/*     */ import java.text.ParsePosition;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import java.util.Locale;
/*     */ import java.util.TimeZone;
/*     */ import org.apache.commons.beanutils.ConversionException;
/*     */ 
/*     */ public abstract class DateTimeConverter extends AbstractConverter {
/*     */   private String[] patterns;
/*     */   
/*     */   private String displayPatterns;
/*     */   
/*     */   private Locale locale;
/*     */   
/*     */   private TimeZone timeZone;
/*     */   
/*     */   private boolean useLocaleFormat;
/*     */   
/*     */   public DateTimeConverter() {}
/*     */   
/*     */   public DateTimeConverter(Object defaultValue) {
/* 107 */     super(defaultValue);
/*     */   }
/*     */   
/*     */   public void setUseLocaleFormat(boolean useLocaleFormat) {
/* 120 */     this.useLocaleFormat = useLocaleFormat;
/*     */   }
/*     */   
/*     */   public TimeZone getTimeZone() {
/* 130 */     return this.timeZone;
/*     */   }
/*     */   
/*     */   public void setTimeZone(TimeZone timeZone) {
/* 139 */     this.timeZone = timeZone;
/*     */   }
/*     */   
/*     */   public Locale getLocale() {
/* 149 */     return this.locale;
/*     */   }
/*     */   
/*     */   public void setLocale(Locale locale) {
/* 158 */     this.locale = locale;
/* 159 */     setUseLocaleFormat(true);
/*     */   }
/*     */   
/*     */   public void setPattern(String pattern) {
/* 170 */     setPatterns(new String[] { pattern });
/*     */   }
/*     */   
/*     */   public String[] getPatterns() {
/* 182 */     return this.patterns;
/*     */   }
/*     */   
/*     */   public void setPatterns(String[] patterns) {
/* 193 */     this.patterns = patterns;
/* 194 */     if (patterns != null && patterns.length > 1) {
/* 195 */       StringBuffer buffer = new StringBuffer();
/* 196 */       for (int i = 0; i < patterns.length; i++) {
/* 197 */         if (i > 0)
/* 198 */           buffer.append(", "); 
/* 200 */         buffer.append(patterns[i]);
/*     */       } 
/* 202 */       this.displayPatterns = buffer.toString();
/*     */     } 
/* 204 */     setUseLocaleFormat(true);
/*     */   }
/*     */   
/*     */   protected String convertToString(Object value) throws Throwable {
/* 224 */     Date date = null;
/* 225 */     if (value instanceof Date) {
/* 226 */       date = (Date)value;
/* 227 */     } else if (value instanceof Calendar) {
/* 228 */       date = ((Calendar)value).getTime();
/* 229 */     } else if (value instanceof Long) {
/* 230 */       date = new Date(((Long)value).longValue());
/*     */     } 
/* 233 */     String result = null;
/* 234 */     if (this.useLocaleFormat && date != null) {
/* 235 */       DateFormat format = null;
/* 236 */       if (this.patterns != null && this.patterns.length > 0) {
/* 237 */         format = getFormat(this.patterns[0]);
/*     */       } else {
/* 239 */         format = getFormat(this.locale, this.timeZone);
/*     */       } 
/* 241 */       logFormat("Formatting", format);
/* 242 */       result = format.format(date);
/* 243 */       if (log().isDebugEnabled())
/* 244 */         log().debug("    Converted  to String using format '" + result + "'"); 
/*     */     } else {
/* 247 */       result = value.toString();
/* 248 */       if (log().isDebugEnabled())
/* 249 */         log().debug("    Converted  to String using toString() '" + result + "'"); 
/*     */     } 
/* 252 */     return result;
/*     */   }
/*     */   
/*     */   protected Object convertToType(Class targetType, Object value) throws Exception {
/* 286 */     Class sourceType = value.getClass();
/* 289 */     if (value instanceof Timestamp) {
/* 295 */       Timestamp timestamp = (Timestamp)value;
/* 296 */       long timeInMillis = timestamp.getTime() / 1000L * 1000L;
/* 297 */       timeInMillis += (timestamp.getNanos() / 1000000);
/* 299 */       return toDate(targetType, timeInMillis);
/*     */     } 
/* 303 */     if (value instanceof Date) {
/* 304 */       Date date = (Date)value;
/* 305 */       return toDate(targetType, date.getTime());
/*     */     } 
/* 309 */     if (value instanceof Calendar) {
/* 310 */       Calendar calendar = (Calendar)value;
/* 311 */       return toDate(targetType, calendar.getTime().getTime());
/*     */     } 
/* 315 */     if (value instanceof Long) {
/* 316 */       Long longObj = (Long)value;
/* 317 */       return toDate(targetType, longObj.longValue());
/*     */     } 
/* 321 */     String stringValue = value.toString().trim();
/* 322 */     if (stringValue.length() == 0)
/* 323 */       return handleMissing(targetType); 
/* 327 */     if (this.useLocaleFormat) {
/* 328 */       Calendar calendar = null;
/* 329 */       if (this.patterns != null && this.patterns.length > 0) {
/* 330 */         calendar = parse(sourceType, targetType, stringValue);
/*     */       } else {
/* 332 */         DateFormat format = getFormat(this.locale, this.timeZone);
/* 333 */         calendar = parse(sourceType, targetType, stringValue, format);
/*     */       } 
/* 335 */       if (Calendar.class.isAssignableFrom(targetType))
/* 336 */         return calendar; 
/* 338 */       return toDate(targetType, calendar.getTime().getTime());
/*     */     } 
/* 343 */     return toDate(targetType, stringValue);
/*     */   }
/*     */   
/*     */   private Object toDate(Class type, long value) {
/* 368 */     if (type.equals(Date.class))
/* 369 */       return new Date(value); 
/* 373 */     if (type.equals(Date.class))
/* 374 */       return new Date(value); 
/* 378 */     if (type.equals(Time.class))
/* 379 */       return new Time(value); 
/* 383 */     if (type.equals(Timestamp.class))
/* 384 */       return new Timestamp(value); 
/* 388 */     if (type.equals(Calendar.class)) {
/* 389 */       Calendar calendar = null;
/* 390 */       if (this.locale == null && this.timeZone == null) {
/* 391 */         calendar = Calendar.getInstance();
/* 392 */       } else if (this.locale == null) {
/* 393 */         calendar = Calendar.getInstance(this.timeZone);
/* 394 */       } else if (this.timeZone == null) {
/* 395 */         calendar = Calendar.getInstance(this.locale);
/*     */       } else {
/* 397 */         calendar = Calendar.getInstance(this.timeZone, this.locale);
/*     */       } 
/* 399 */       calendar.setTime(new Date(value));
/* 400 */       calendar.setLenient(false);
/* 401 */       return calendar;
/*     */     } 
/* 404 */     String msg = toString(getClass()) + " cannot handle conversion to '" + toString(type) + "'";
/* 406 */     if (log().isWarnEnabled())
/* 407 */       log().warn("    " + msg); 
/* 409 */     throw new ConversionException(msg);
/*     */   }
/*     */   
/*     */   private Object toDate(Class type, String value) {
/* 432 */     if (type.equals(Date.class))
/*     */       try {
/* 434 */         return Date.valueOf(value);
/* 435 */       } catch (IllegalArgumentException e) {
/* 436 */         throw new ConversionException("String must be in JDBC format [yyyy-MM-dd] to create a java.sql.Date");
/*     */       }  
/* 442 */     if (type.equals(Time.class))
/*     */       try {
/* 444 */         return Time.valueOf(value);
/* 445 */       } catch (IllegalArgumentException e) {
/* 446 */         throw new ConversionException("String must be in JDBC format [HH:mm:ss] to create a java.sql.Time");
/*     */       }  
/* 452 */     if (type.equals(Timestamp.class))
/*     */       try {
/* 454 */         return Timestamp.valueOf(value);
/* 455 */       } catch (IllegalArgumentException e) {
/* 456 */         throw new ConversionException("String must be in JDBC format [yyyy-MM-dd HH:mm:ss.fffffffff] to create a java.sql.Timestamp");
/*     */       }  
/* 462 */     String msg = toString(getClass()) + " does not support default String to '" + toString(type) + "' conversion.";
/* 464 */     if (log().isWarnEnabled()) {
/* 465 */       log().warn("    " + msg);
/* 466 */       log().warn("    (N.B. Re-configure Converter or use alternative implementation)");
/*     */     } 
/* 468 */     throw new ConversionException(msg);
/*     */   }
/*     */   
/*     */   protected DateFormat getFormat(Locale locale, TimeZone timeZone) {
/* 479 */     DateFormat format = null;
/* 480 */     if (locale == null) {
/* 481 */       format = DateFormat.getDateInstance(3);
/*     */     } else {
/* 483 */       format = DateFormat.getDateInstance(3, locale);
/*     */     } 
/* 485 */     if (timeZone != null)
/* 486 */       format.setTimeZone(timeZone); 
/* 488 */     return format;
/*     */   }
/*     */   
/*     */   private DateFormat getFormat(String pattern) {
/* 498 */     DateFormat format = new SimpleDateFormat(pattern);
/* 499 */     if (this.timeZone != null)
/* 500 */       format.setTimeZone(this.timeZone); 
/* 502 */     return format;
/*     */   }
/*     */   
/*     */   private Calendar parse(Class sourceType, Class targetType, String value) throws Exception {
/* 516 */     Exception firstEx = null;
/* 517 */     for (int i = 0; i < this.patterns.length; i++) {
/*     */       try {
/* 519 */         DateFormat format = getFormat(this.patterns[i]);
/* 520 */         Calendar calendar = parse(sourceType, targetType, value, format);
/* 521 */         return calendar;
/* 522 */       } catch (Exception ex) {
/* 523 */         if (firstEx == null)
/* 524 */           firstEx = ex; 
/*     */       } 
/*     */     } 
/* 528 */     if (this.patterns.length > 1)
/* 529 */       throw new ConversionException("Error converting '" + toString(sourceType) + "' to '" + toString(targetType) + "' using  patterns '" + this.displayPatterns + "'"); 
/* 532 */     throw firstEx;
/*     */   }
/*     */   
/*     */   private Calendar parse(Class sourceType, Class targetType, String value, DateFormat format) {
/* 549 */     logFormat("Parsing", format);
/* 550 */     format.setLenient(false);
/* 551 */     ParsePosition pos = new ParsePosition(0);
/* 552 */     Date parsedDate = format.parse(value, pos);
/* 553 */     if (pos.getErrorIndex() >= 0 || pos.getIndex() != value.length() || parsedDate == null) {
/* 554 */       String msg = "Error converting '" + toString(sourceType) + "' to '" + toString(targetType) + "'";
/* 555 */       if (format instanceof SimpleDateFormat)
/* 556 */         msg = msg + " using pattern '" + ((SimpleDateFormat)format).toPattern() + "'"; 
/* 558 */       if (log().isDebugEnabled())
/* 559 */         log().debug("    " + msg); 
/* 561 */       throw new ConversionException(msg);
/*     */     } 
/* 563 */     Calendar calendar = format.getCalendar();
/* 564 */     return calendar;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 573 */     StringBuffer buffer = new StringBuffer();
/* 574 */     buffer.append(toString(getClass()));
/* 575 */     buffer.append("[UseDefault=");
/* 576 */     buffer.append(isUseDefault());
/* 577 */     buffer.append(", UseLocaleFormat=");
/* 578 */     buffer.append(this.useLocaleFormat);
/* 579 */     if (this.displayPatterns != null) {
/* 580 */       buffer.append(", Patterns={");
/* 581 */       buffer.append(this.displayPatterns);
/* 582 */       buffer.append('}');
/*     */     } 
/* 584 */     if (this.locale != null) {
/* 585 */       buffer.append(", Locale=");
/* 586 */       buffer.append(this.locale);
/*     */     } 
/* 588 */     if (this.timeZone != null) {
/* 589 */       buffer.append(", TimeZone=");
/* 590 */       buffer.append(this.timeZone);
/*     */     } 
/* 592 */     buffer.append(']');
/* 593 */     return buffer.toString();
/*     */   }
/*     */   
/*     */   private void logFormat(String action, DateFormat format) {
/* 602 */     if (log().isDebugEnabled()) {
/* 603 */       StringBuffer buffer = new StringBuffer(45);
/* 604 */       buffer.append("    ");
/* 605 */       buffer.append(action);
/* 606 */       buffer.append(" with Format");
/* 607 */       if (format instanceof SimpleDateFormat) {
/* 608 */         buffer.append("[");
/* 609 */         buffer.append(((SimpleDateFormat)format).toPattern());
/* 610 */         buffer.append("]");
/*     */       } 
/* 612 */       buffer.append(" for ");
/* 613 */       if (this.locale == null) {
/* 614 */         buffer.append("default locale");
/*     */       } else {
/* 616 */         buffer.append("locale[");
/* 617 */         buffer.append(this.locale);
/* 618 */         buffer.append("]");
/*     */       } 
/* 620 */       if (this.timeZone != null) {
/* 621 */         buffer.append(", TimeZone[");
/* 622 */         buffer.append(this.timeZone);
/* 623 */         buffer.append("]");
/*     */       } 
/* 625 */       log().debug(buffer.toString());
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\beanutils\converters\DateTimeConverter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */