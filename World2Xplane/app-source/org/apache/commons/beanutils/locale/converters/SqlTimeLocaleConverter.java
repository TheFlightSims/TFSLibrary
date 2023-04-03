/*     */ package org.apache.commons.beanutils.locale.converters;
/*     */ 
/*     */ import java.sql.Time;
/*     */ import java.text.ParseException;
/*     */ import java.util.Date;
/*     */ import java.util.Locale;
/*     */ 
/*     */ public class SqlTimeLocaleConverter extends DateLocaleConverter {
/*     */   public SqlTimeLocaleConverter() {
/*  50 */     this(false);
/*     */   }
/*     */   
/*     */   public SqlTimeLocaleConverter(boolean locPattern) {
/*  63 */     this(Locale.getDefault(), locPattern);
/*     */   }
/*     */   
/*     */   public SqlTimeLocaleConverter(Locale locale) {
/*  75 */     this(locale, false);
/*     */   }
/*     */   
/*     */   public SqlTimeLocaleConverter(Locale locale, boolean locPattern) {
/*  88 */     this(locale, (String)null, locPattern);
/*     */   }
/*     */   
/*     */   public SqlTimeLocaleConverter(Locale locale, String pattern) {
/* 101 */     this(locale, pattern, false);
/*     */   }
/*     */   
/*     */   public SqlTimeLocaleConverter(Locale locale, String pattern, boolean locPattern) {
/* 115 */     super(locale, pattern, locPattern);
/*     */   }
/*     */   
/*     */   public SqlTimeLocaleConverter(Object defaultValue) {
/* 129 */     this(defaultValue, false);
/*     */   }
/*     */   
/*     */   public SqlTimeLocaleConverter(Object defaultValue, boolean locPattern) {
/* 143 */     this(defaultValue, Locale.getDefault(), false);
/*     */   }
/*     */   
/*     */   public SqlTimeLocaleConverter(Object defaultValue, Locale locale) {
/* 156 */     this(defaultValue, locale, false);
/*     */   }
/*     */   
/*     */   public SqlTimeLocaleConverter(Object defaultValue, Locale locale, boolean locPattern) {
/* 170 */     this(defaultValue, locale, null, locPattern);
/*     */   }
/*     */   
/*     */   public SqlTimeLocaleConverter(Object defaultValue, Locale locale, String pattern) {
/* 184 */     this(defaultValue, locale, pattern, false);
/*     */   }
/*     */   
/*     */   public SqlTimeLocaleConverter(Object defaultValue, Locale locale, String pattern, boolean locPattern) {
/* 199 */     super(defaultValue, locale, pattern, locPattern);
/*     */   }
/*     */   
/*     */   protected Object parse(Object value, String pattern) throws ParseException {
/* 218 */     return new Time(((Date)super.parse(value, pattern)).getTime());
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\beanutils\locale\converters\SqlTimeLocaleConverter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */