/*     */ package org.apache.commons.beanutils.locale.converters;
/*     */ 
/*     */ import java.text.DecimalFormat;
/*     */ import java.text.NumberFormat;
/*     */ import java.text.ParseException;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Locale;
/*     */ import org.apache.commons.beanutils.locale.BaseLocaleConverter;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ 
/*     */ public class StringLocaleConverter extends BaseLocaleConverter {
/*  50 */   private Log log = LogFactory.getLog(StringLocaleConverter.class);
/*     */   
/*     */   public StringLocaleConverter() {
/*  65 */     this(false);
/*     */   }
/*     */   
/*     */   public StringLocaleConverter(boolean locPattern) {
/*  78 */     this(Locale.getDefault(), locPattern);
/*     */   }
/*     */   
/*     */   public StringLocaleConverter(Locale locale) {
/*  90 */     this(locale, false);
/*     */   }
/*     */   
/*     */   public StringLocaleConverter(Locale locale, boolean locPattern) {
/* 103 */     this(locale, (String)null, locPattern);
/*     */   }
/*     */   
/*     */   public StringLocaleConverter(Locale locale, String pattern) {
/* 116 */     this(locale, pattern, false);
/*     */   }
/*     */   
/*     */   public StringLocaleConverter(Locale locale, String pattern, boolean locPattern) {
/* 130 */     super(locale, pattern, locPattern);
/*     */   }
/*     */   
/*     */   public StringLocaleConverter(Object defaultValue) {
/* 144 */     this(defaultValue, false);
/*     */   }
/*     */   
/*     */   public StringLocaleConverter(Object defaultValue, boolean locPattern) {
/* 158 */     this(defaultValue, Locale.getDefault(), locPattern);
/*     */   }
/*     */   
/*     */   public StringLocaleConverter(Object defaultValue, Locale locale) {
/* 171 */     this(defaultValue, locale, false);
/*     */   }
/*     */   
/*     */   public StringLocaleConverter(Object defaultValue, Locale locale, boolean locPattern) {
/* 185 */     this(defaultValue, locale, (String)null, locPattern);
/*     */   }
/*     */   
/*     */   public StringLocaleConverter(Object defaultValue, Locale locale, String pattern) {
/* 199 */     this(defaultValue, locale, pattern, false);
/*     */   }
/*     */   
/*     */   public StringLocaleConverter(Object defaultValue, Locale locale, String pattern, boolean locPattern) {
/* 214 */     super(defaultValue, locale, pattern, locPattern);
/*     */   }
/*     */   
/*     */   protected Object parse(Object value, String pattern) throws ParseException {
/* 233 */     String result = null;
/* 235 */     if (value instanceof Integer || value instanceof Long || value instanceof java.math.BigInteger || value instanceof Byte || value instanceof Short) {
/* 241 */       result = getDecimalFormat(this.locale, pattern).format(((Number)value).longValue());
/* 243 */     } else if (value instanceof Double || value instanceof java.math.BigDecimal || value instanceof Float) {
/* 247 */       result = getDecimalFormat(this.locale, pattern).format(((Number)value).doubleValue());
/* 249 */     } else if (value instanceof java.util.Date) {
/* 251 */       SimpleDateFormat dateFormat = new SimpleDateFormat(pattern, this.locale);
/* 254 */       result = dateFormat.format(value);
/*     */     } else {
/* 257 */       result = value.toString();
/*     */     } 
/* 260 */     return result;
/*     */   }
/*     */   
/*     */   private DecimalFormat getDecimalFormat(Locale locale, String pattern) {
/* 276 */     DecimalFormat numberFormat = (DecimalFormat)NumberFormat.getInstance(locale);
/* 279 */     if (pattern != null) {
/* 280 */       if (this.locPattern) {
/* 281 */         numberFormat.applyLocalizedPattern(pattern);
/*     */       } else {
/* 283 */         numberFormat.applyPattern(pattern);
/*     */       } 
/*     */     } else {
/* 286 */       this.log.debug("No pattern provided, using default.");
/*     */     } 
/* 289 */     return numberFormat;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\beanutils\locale\converters\StringLocaleConverter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */