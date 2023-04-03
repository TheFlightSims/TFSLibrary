/*     */ package org.apache.commons.beanutils.locale.converters;
/*     */ 
/*     */ import java.sql.Date;
/*     */ import java.text.ParseException;
/*     */ import java.util.Date;
/*     */ import java.util.Locale;
/*     */ 
/*     */ public class SqlDateLocaleConverter extends DateLocaleConverter {
/*     */   public SqlDateLocaleConverter() {
/*  50 */     this(false);
/*     */   }
/*     */   
/*     */   public SqlDateLocaleConverter(boolean locPattern) {
/*  63 */     this(Locale.getDefault(), locPattern);
/*     */   }
/*     */   
/*     */   public SqlDateLocaleConverter(Locale locale) {
/*  75 */     this(locale, false);
/*     */   }
/*     */   
/*     */   public SqlDateLocaleConverter(Locale locale, boolean locPattern) {
/*  88 */     this(locale, (String)null, locPattern);
/*     */   }
/*     */   
/*     */   public SqlDateLocaleConverter(Locale locale, String pattern) {
/* 101 */     this(locale, pattern, false);
/*     */   }
/*     */   
/*     */   public SqlDateLocaleConverter(Locale locale, String pattern, boolean locPattern) {
/* 115 */     super(locale, pattern, locPattern);
/*     */   }
/*     */   
/*     */   public SqlDateLocaleConverter(Object defaultValue) {
/* 129 */     this(defaultValue, false);
/*     */   }
/*     */   
/*     */   public SqlDateLocaleConverter(Object defaultValue, boolean locPattern) {
/* 143 */     this(defaultValue, Locale.getDefault(), locPattern);
/*     */   }
/*     */   
/*     */   public SqlDateLocaleConverter(Object defaultValue, Locale locale) {
/* 156 */     this(defaultValue, locale, false);
/*     */   }
/*     */   
/*     */   public SqlDateLocaleConverter(Object defaultValue, Locale locale, boolean locPattern) {
/* 170 */     this(defaultValue, locale, null, locPattern);
/*     */   }
/*     */   
/*     */   public SqlDateLocaleConverter(Object defaultValue, Locale locale, String pattern) {
/* 184 */     this(defaultValue, locale, pattern, false);
/*     */   }
/*     */   
/*     */   public SqlDateLocaleConverter(Object defaultValue, Locale locale, String pattern, boolean locPattern) {
/* 199 */     super(defaultValue, locale, pattern, locPattern);
/*     */   }
/*     */   
/*     */   protected Object parse(Object value, String pattern) throws ParseException {
/* 219 */     return new Date(((Date)super.parse(value, pattern)).getTime());
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\beanutils\locale\converters\SqlDateLocaleConverter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */