/*     */ package org.apache.commons.beanutils.locale.converters;
/*     */ 
/*     */ import java.text.DateFormat;
/*     */ import java.text.DateFormatSymbols;
/*     */ import java.text.ParseException;
/*     */ import java.text.ParsePosition;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Calendar;
/*     */ import java.util.Locale;
/*     */ import org.apache.commons.beanutils.ConversionException;
/*     */ import org.apache.commons.beanutils.locale.BaseLocaleConverter;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ 
/*     */ public class DateLocaleConverter extends BaseLocaleConverter {
/*  50 */   private Log log = LogFactory.getLog(DateLocaleConverter.class);
/*     */   
/*     */   boolean isLenient = false;
/*     */   
/*  59 */   private static final String DEFAULT_PATTERN_CHARS = initDefaultChars();
/*     */   
/*     */   public DateLocaleConverter() {
/*  73 */     this(false);
/*     */   }
/*     */   
/*     */   public DateLocaleConverter(boolean locPattern) {
/*  86 */     this(Locale.getDefault(), locPattern);
/*     */   }
/*     */   
/*     */   public DateLocaleConverter(Locale locale) {
/*  98 */     this(locale, false);
/*     */   }
/*     */   
/*     */   public DateLocaleConverter(Locale locale, boolean locPattern) {
/* 111 */     this(locale, (String)null, locPattern);
/*     */   }
/*     */   
/*     */   public DateLocaleConverter(Locale locale, String pattern) {
/* 124 */     this(locale, pattern, false);
/*     */   }
/*     */   
/*     */   public DateLocaleConverter(Locale locale, String pattern, boolean locPattern) {
/* 138 */     super(locale, pattern, locPattern);
/*     */   }
/*     */   
/*     */   public DateLocaleConverter(Object defaultValue) {
/* 152 */     this(defaultValue, false);
/*     */   }
/*     */   
/*     */   public DateLocaleConverter(Object defaultValue, boolean locPattern) {
/* 166 */     this(defaultValue, Locale.getDefault(), locPattern);
/*     */   }
/*     */   
/*     */   public DateLocaleConverter(Object defaultValue, Locale locale) {
/* 179 */     this(defaultValue, locale, false);
/*     */   }
/*     */   
/*     */   public DateLocaleConverter(Object defaultValue, Locale locale, boolean locPattern) {
/* 193 */     this(defaultValue, locale, (String)null, locPattern);
/*     */   }
/*     */   
/*     */   public DateLocaleConverter(Object defaultValue, Locale locale, String pattern) {
/* 208 */     this(defaultValue, locale, pattern, false);
/*     */   }
/*     */   
/*     */   public DateLocaleConverter(Object defaultValue, Locale locale, String pattern, boolean locPattern) {
/* 223 */     super(defaultValue, locale, pattern, locPattern);
/*     */   }
/*     */   
/*     */   public boolean isLenient() {
/* 235 */     return this.isLenient;
/*     */   }
/*     */   
/*     */   public void setLenient(boolean lenient) {
/* 245 */     this.isLenient = lenient;
/*     */   }
/*     */   
/*     */   protected Object parse(Object value, String pattern) throws ParseException {
/* 265 */     if (value instanceof java.util.Date)
/* 266 */       return value; 
/* 270 */     if (value instanceof Calendar)
/* 271 */       return ((Calendar)value).getTime(); 
/* 274 */     if (this.locPattern)
/* 275 */       pattern = convertLocalizedPattern(pattern, this.locale); 
/* 279 */     DateFormat formatter = (pattern == null) ? DateFormat.getDateInstance(3, this.locale) : new SimpleDateFormat(pattern, this.locale);
/* 281 */     formatter.setLenient(this.isLenient);
/* 285 */     ParsePosition pos = new ParsePosition(0);
/* 286 */     String strValue = value.toString();
/* 287 */     Object parsedValue = formatter.parseObject(strValue, pos);
/* 288 */     if (pos.getErrorIndex() > -1)
/* 289 */       throw new ConversionException("Error parsing date '" + value + "' at position=" + pos.getErrorIndex()); 
/* 292 */     if (pos.getIndex() < strValue.length())
/* 293 */       throw new ConversionException("Date '" + value + "' contains unparsed characters from position=" + pos.getIndex()); 
/* 297 */     return parsedValue;
/*     */   }
/*     */   
/*     */   private String convertLocalizedPattern(String localizedPattern, Locale locale) {
/* 309 */     if (localizedPattern == null)
/* 310 */       return null; 
/* 318 */     DateFormatSymbols localizedSymbols = new DateFormatSymbols(locale);
/* 319 */     String localChars = localizedSymbols.getLocalPatternChars();
/* 321 */     if (DEFAULT_PATTERN_CHARS.equals(localChars))
/* 322 */       return localizedPattern; 
/* 326 */     String convertedPattern = null;
/*     */     try {
/* 328 */       convertedPattern = convertPattern(localizedPattern, localChars, DEFAULT_PATTERN_CHARS);
/* 331 */     } catch (Exception ex) {
/* 332 */       this.log.debug("Converting pattern '" + localizedPattern + "' for " + locale, ex);
/*     */     } 
/* 334 */     return convertedPattern;
/*     */   }
/*     */   
/*     */   private String convertPattern(String pattern, String fromChars, String toChars) {
/* 342 */     StringBuffer converted = new StringBuffer();
/* 343 */     boolean quoted = false;
/* 345 */     for (int i = 0; i < pattern.length(); i++) {
/* 346 */       char thisChar = pattern.charAt(i);
/* 347 */       if (quoted) {
/* 348 */         if (thisChar == '\'')
/* 349 */           quoted = false; 
/* 352 */       } else if (thisChar == '\'') {
/* 353 */         quoted = true;
/* 354 */       } else if ((thisChar >= 'a' && thisChar <= 'z') || (thisChar >= 'A' && thisChar <= 'Z')) {
/* 356 */         int index = fromChars.indexOf(thisChar);
/* 357 */         if (index == -1)
/* 358 */           throw new IllegalArgumentException("Illegal pattern character '" + thisChar + "'"); 
/* 361 */         thisChar = toChars.charAt(index);
/*     */       } 
/* 364 */       converted.append(thisChar);
/*     */     } 
/* 367 */     if (quoted)
/* 368 */       throw new IllegalArgumentException("Unfinished quote in pattern"); 
/* 371 */     return converted.toString();
/*     */   }
/*     */   
/*     */   private static String initDefaultChars() {
/* 380 */     DateFormatSymbols defaultSymbols = new DateFormatSymbols(Locale.US);
/* 381 */     return defaultSymbols.getLocalPatternChars();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\beanutils\locale\converters\DateLocaleConverter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */