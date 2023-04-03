/*     */ package org.apache.commons.beanutils.converters;
/*     */ 
/*     */ import java.math.BigDecimal;
/*     */ import java.math.BigInteger;
/*     */ import java.text.DecimalFormat;
/*     */ import java.text.DecimalFormatSymbols;
/*     */ import java.text.NumberFormat;
/*     */ import java.text.ParsePosition;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import java.util.Locale;
/*     */ import org.apache.commons.beanutils.ConversionException;
/*     */ 
/*     */ public abstract class NumberConverter extends AbstractConverter {
/*  87 */   private static final Integer ZERO = new Integer(0);
/*     */   
/*  88 */   private static final Integer ONE = new Integer(1);
/*     */   
/*     */   private String pattern;
/*     */   
/*     */   private boolean allowDecimals;
/*     */   
/*     */   private boolean useLocaleFormat;
/*     */   
/*     */   private Locale locale;
/*     */   
/*     */   public NumberConverter(boolean allowDecimals) {
/* 105 */     this.allowDecimals = allowDecimals;
/*     */   }
/*     */   
/*     */   public NumberConverter(boolean allowDecimals, Object defaultValue) {
/* 117 */     this.allowDecimals = allowDecimals;
/* 118 */     setDefaultValue(defaultValue);
/*     */   }
/*     */   
/*     */   public boolean isAllowDecimals() {
/* 129 */     return this.allowDecimals;
/*     */   }
/*     */   
/*     */   public void setUseLocaleFormat(boolean useLocaleFormat) {
/* 140 */     this.useLocaleFormat = useLocaleFormat;
/*     */   }
/*     */   
/*     */   public String getPattern() {
/* 154 */     return this.pattern;
/*     */   }
/*     */   
/*     */   public void setPattern(String pattern) {
/* 167 */     this.pattern = pattern;
/* 168 */     setUseLocaleFormat(true);
/*     */   }
/*     */   
/*     */   public Locale getLocale() {
/* 178 */     return this.locale;
/*     */   }
/*     */   
/*     */   public void setLocale(Locale locale) {
/* 187 */     this.locale = locale;
/* 188 */     setUseLocaleFormat(true);
/*     */   }
/*     */   
/*     */   protected String convertToString(Object value) throws Throwable {
/* 202 */     String result = null;
/* 203 */     if (this.useLocaleFormat && value instanceof Number) {
/* 204 */       NumberFormat format = getFormat();
/* 205 */       format.setGroupingUsed(false);
/* 206 */       result = format.format(value);
/* 207 */       if (log().isDebugEnabled())
/* 208 */         log().debug("    Converted  to String using format '" + result + "'"); 
/*     */     } else {
/* 212 */       result = value.toString();
/* 213 */       if (log().isDebugEnabled())
/* 214 */         log().debug("    Converted  to String using toString() '" + result + "'"); 
/*     */     } 
/* 217 */     return result;
/*     */   }
/*     */   
/*     */   protected Object convertToType(Class targetType, Object value) throws Throwable {
/* 232 */     Class sourceType = value.getClass();
/* 234 */     if (value instanceof Number)
/* 235 */       return toNumber(sourceType, targetType, (Number)value); 
/* 239 */     if (value instanceof Boolean)
/* 240 */       return toNumber(sourceType, targetType, ((Boolean)value).booleanValue() ? ONE : ZERO); 
/* 244 */     if (value instanceof Date && Long.class.equals(targetType))
/* 245 */       return new Long(((Date)value).getTime()); 
/* 249 */     if (value instanceof Calendar && Long.class.equals(targetType))
/* 250 */       return new Long(((Calendar)value).getTime().getTime()); 
/* 254 */     String stringValue = value.toString().trim();
/* 255 */     if (stringValue.length() == 0)
/* 256 */       return handleMissing(targetType); 
/* 260 */     Number number = null;
/* 261 */     if (this.useLocaleFormat) {
/* 262 */       NumberFormat format = getFormat();
/* 263 */       number = parse(sourceType, targetType, stringValue, format);
/*     */     } else {
/* 265 */       if (log().isDebugEnabled())
/* 266 */         log().debug("    No NumberFormat, using default conversion"); 
/* 268 */       number = toNumber(sourceType, targetType, stringValue);
/*     */     } 
/* 272 */     return toNumber(sourceType, targetType, number);
/*     */   }
/*     */   
/*     */   private Number toNumber(Class sourceType, Class targetType, Number value) {
/* 300 */     if (targetType.equals(value.getClass()))
/* 301 */       return value; 
/* 305 */     if (targetType.equals(Byte.class)) {
/* 306 */       long longValue = value.longValue();
/* 307 */       if (longValue > 127L)
/* 308 */         throw new ConversionException(toString(sourceType) + " value '" + value + "' is too large for " + toString(targetType)); 
/* 311 */       if (longValue < -128L)
/* 312 */         throw new ConversionException(toString(sourceType) + " value '" + value + "' is too small " + toString(targetType)); 
/* 315 */       return new Byte(value.byteValue());
/*     */     } 
/* 319 */     if (targetType.equals(Short.class)) {
/* 320 */       long longValue = value.longValue();
/* 321 */       if (longValue > 32767L)
/* 322 */         throw new ConversionException(toString(sourceType) + " value '" + value + "' is too large for " + toString(targetType)); 
/* 325 */       if (longValue < -32768L)
/* 326 */         throw new ConversionException(toString(sourceType) + " value '" + value + "' is too small " + toString(targetType)); 
/* 329 */       return new Short(value.shortValue());
/*     */     } 
/* 333 */     if (targetType.equals(Integer.class)) {
/* 334 */       long longValue = value.longValue();
/* 335 */       if (longValue > 2147483647L)
/* 336 */         throw new ConversionException(toString(sourceType) + " value '" + value + "' is too large for " + toString(targetType)); 
/* 339 */       if (longValue < -2147483648L)
/* 340 */         throw new ConversionException(toString(sourceType) + " value '" + value + "' is too small " + toString(targetType)); 
/* 343 */       return new Integer(value.intValue());
/*     */     } 
/* 347 */     if (targetType.equals(Long.class))
/* 348 */       return new Long(value.longValue()); 
/* 352 */     if (targetType.equals(Float.class)) {
/* 353 */       if (value.doubleValue() > 3.4028234663852886E38D)
/* 354 */         throw new ConversionException(toString(sourceType) + " value '" + value + "' is too large for " + toString(targetType)); 
/* 357 */       return new Float(value.floatValue());
/*     */     } 
/* 361 */     if (targetType.equals(Double.class))
/* 362 */       return new Double(value.doubleValue()); 
/* 366 */     if (targetType.equals(BigDecimal.class)) {
/* 367 */       if (value instanceof Float || value instanceof Double)
/* 368 */         return new BigDecimal(value.toString()); 
/* 369 */       if (value instanceof BigInteger)
/* 370 */         return new BigDecimal((BigInteger)value); 
/* 372 */       return BigDecimal.valueOf(value.longValue());
/*     */     } 
/* 377 */     if (targetType.equals(BigInteger.class)) {
/* 378 */       if (value instanceof BigDecimal)
/* 379 */         return ((BigDecimal)value).toBigInteger(); 
/* 381 */       return BigInteger.valueOf(value.longValue());
/*     */     } 
/* 385 */     String msg = toString(getClass()) + " cannot handle conversion to '" + toString(targetType) + "'";
/* 387 */     if (log().isWarnEnabled())
/* 388 */       log().warn("    " + msg); 
/* 390 */     throw new ConversionException(msg);
/*     */   }
/*     */   
/*     */   private Number toNumber(Class sourceType, Class targetType, String value) {
/* 417 */     if (targetType.equals(Byte.class))
/* 418 */       return new Byte(value); 
/* 422 */     if (targetType.equals(Short.class))
/* 423 */       return new Short(value); 
/* 427 */     if (targetType.equals(Integer.class))
/* 428 */       return new Integer(value); 
/* 432 */     if (targetType.equals(Long.class))
/* 433 */       return new Long(value); 
/* 437 */     if (targetType.equals(Float.class))
/* 438 */       return new Float(value); 
/* 442 */     if (targetType.equals(Double.class))
/* 443 */       return new Double(value); 
/* 447 */     if (targetType.equals(BigDecimal.class))
/* 448 */       return new BigDecimal(value); 
/* 452 */     if (targetType.equals(BigInteger.class))
/* 453 */       return new BigInteger(value); 
/* 456 */     String msg = toString(getClass()) + " cannot handle conversion from '" + toString(sourceType) + "' to '" + toString(targetType) + "'";
/* 458 */     if (log().isWarnEnabled())
/* 459 */       log().warn("    " + msg); 
/* 461 */     throw new ConversionException(msg);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 470 */     StringBuffer buffer = new StringBuffer();
/* 471 */     buffer.append(toString(getClass()));
/* 472 */     buffer.append("[UseDefault=");
/* 473 */     buffer.append(isUseDefault());
/* 474 */     buffer.append(", UseLocaleFormat=");
/* 475 */     buffer.append(this.useLocaleFormat);
/* 476 */     if (this.pattern != null) {
/* 477 */       buffer.append(", Pattern=");
/* 478 */       buffer.append(this.pattern);
/*     */     } 
/* 480 */     if (this.locale != null) {
/* 481 */       buffer.append(", Locale=");
/* 482 */       buffer.append(this.locale);
/*     */     } 
/* 484 */     buffer.append(']');
/* 485 */     return buffer.toString();
/*     */   }
/*     */   
/*     */   private NumberFormat getFormat() {
/* 494 */     NumberFormat format = null;
/* 495 */     if (this.pattern != null) {
/* 496 */       if (this.locale == null) {
/* 497 */         if (log().isDebugEnabled())
/* 498 */           log().debug("    Using pattern '" + this.pattern + "'"); 
/* 500 */         format = new DecimalFormat(this.pattern);
/*     */       } else {
/* 502 */         if (log().isDebugEnabled())
/* 503 */           log().debug("    Using pattern '" + this.pattern + "'" + " with Locale[" + this.locale + "]"); 
/* 506 */         DecimalFormatSymbols symbols = new DecimalFormatSymbols(this.locale);
/* 507 */         format = new DecimalFormat(this.pattern, symbols);
/*     */       } 
/* 510 */     } else if (this.locale == null) {
/* 511 */       if (log().isDebugEnabled())
/* 512 */         log().debug("    Using default Locale format"); 
/* 514 */       format = NumberFormat.getInstance();
/*     */     } else {
/* 516 */       if (log().isDebugEnabled())
/* 517 */         log().debug("    Using Locale[" + this.locale + "] format"); 
/* 519 */       format = NumberFormat.getInstance(this.locale);
/*     */     } 
/* 522 */     if (!this.allowDecimals)
/* 523 */       format.setParseIntegerOnly(true); 
/* 525 */     return format;
/*     */   }
/*     */   
/*     */   private Number parse(Class sourceType, Class targetType, String value, NumberFormat format) {
/* 539 */     ParsePosition pos = new ParsePosition(0);
/* 540 */     Number parsedNumber = format.parse(value, pos);
/* 541 */     if (pos.getErrorIndex() >= 0 || pos.getIndex() != value.length() || parsedNumber == null) {
/* 542 */       String msg = "Error converting from '" + toString(sourceType) + "' to '" + toString(targetType) + "'";
/* 543 */       if (format instanceof DecimalFormat)
/* 544 */         msg = msg + " using pattern '" + ((DecimalFormat)format).toPattern() + "'"; 
/* 546 */       if (this.locale != null)
/* 547 */         msg = msg + " for locale=[" + this.locale + "]"; 
/* 549 */       if (log().isDebugEnabled())
/* 550 */         log().debug("    " + msg); 
/* 552 */       throw new ConversionException(msg);
/*     */     } 
/* 554 */     return parsedNumber;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\beanutils\converters\NumberConverter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */