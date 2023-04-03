/*     */ package org.apache.commons.beanutils.locale.converters;
/*     */ 
/*     */ import java.text.ParseException;
/*     */ import java.util.Locale;
/*     */ 
/*     */ public class LongLocaleConverter extends DecimalLocaleConverter {
/*     */   public LongLocaleConverter() {
/*  50 */     this(false);
/*     */   }
/*     */   
/*     */   public LongLocaleConverter(boolean locPattern) {
/*  63 */     this(Locale.getDefault(), locPattern);
/*     */   }
/*     */   
/*     */   public LongLocaleConverter(Locale locale) {
/*  75 */     this(locale, false);
/*     */   }
/*     */   
/*     */   public LongLocaleConverter(Locale locale, boolean locPattern) {
/*  88 */     this(locale, (String)null, locPattern);
/*     */   }
/*     */   
/*     */   public LongLocaleConverter(Locale locale, String pattern) {
/* 101 */     this(locale, pattern, false);
/*     */   }
/*     */   
/*     */   public LongLocaleConverter(Locale locale, String pattern, boolean locPattern) {
/* 115 */     super(locale, pattern, locPattern);
/*     */   }
/*     */   
/*     */   public LongLocaleConverter(Object defaultValue) {
/* 129 */     this(defaultValue, false);
/*     */   }
/*     */   
/*     */   public LongLocaleConverter(Object defaultValue, boolean locPattern) {
/* 143 */     this(defaultValue, Locale.getDefault(), locPattern);
/*     */   }
/*     */   
/*     */   public LongLocaleConverter(Object defaultValue, Locale locale) {
/* 156 */     this(defaultValue, locale, false);
/*     */   }
/*     */   
/*     */   public LongLocaleConverter(Object defaultValue, Locale locale, boolean locPattern) {
/* 170 */     this(defaultValue, locale, null, locPattern);
/*     */   }
/*     */   
/*     */   public LongLocaleConverter(Object defaultValue, Locale locale, String pattern) {
/* 184 */     this(defaultValue, locale, pattern, false);
/*     */   }
/*     */   
/*     */   public LongLocaleConverter(Object defaultValue, Locale locale, String pattern, boolean locPattern) {
/* 199 */     super(defaultValue, locale, pattern, locPattern);
/*     */   }
/*     */   
/*     */   protected Object parse(Object value, String pattern) throws ParseException {
/* 217 */     Object result = super.parse(value, pattern);
/* 219 */     if (result == null || result instanceof Long)
/* 220 */       return result; 
/* 223 */     return new Long(((Number)result).longValue());
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\beanutils\locale\converters\LongLocaleConverter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */