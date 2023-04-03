/*     */ package org.apache.commons.beanutils.locale.converters;
/*     */ 
/*     */ import java.sql.Timestamp;
/*     */ import java.text.ParseException;
/*     */ import java.util.Date;
/*     */ import java.util.Locale;
/*     */ 
/*     */ public class SqlTimestampLocaleConverter extends DateLocaleConverter {
/*     */   public SqlTimestampLocaleConverter() {
/*  50 */     this(false);
/*     */   }
/*     */   
/*     */   public SqlTimestampLocaleConverter(boolean locPattern) {
/*  63 */     this(Locale.getDefault(), locPattern);
/*     */   }
/*     */   
/*     */   public SqlTimestampLocaleConverter(Locale locale) {
/*  75 */     this(locale, (String)null);
/*     */   }
/*     */   
/*     */   public SqlTimestampLocaleConverter(Locale locale, boolean locPattern) {
/*  88 */     this(locale, (String)null);
/*     */   }
/*     */   
/*     */   public SqlTimestampLocaleConverter(Locale locale, String pattern) {
/* 101 */     this(locale, pattern, false);
/*     */   }
/*     */   
/*     */   public SqlTimestampLocaleConverter(Locale locale, String pattern, boolean locPattern) {
/* 115 */     super(locale, pattern, locPattern);
/*     */   }
/*     */   
/*     */   public SqlTimestampLocaleConverter(Object defaultValue) {
/* 128 */     this(defaultValue, false);
/*     */   }
/*     */   
/*     */   public SqlTimestampLocaleConverter(Object defaultValue, boolean locPattern) {
/* 142 */     this(defaultValue, Locale.getDefault(), locPattern);
/*     */   }
/*     */   
/*     */   public SqlTimestampLocaleConverter(Object defaultValue, Locale locale) {
/* 155 */     this(defaultValue, locale, false);
/*     */   }
/*     */   
/*     */   public SqlTimestampLocaleConverter(Object defaultValue, Locale locale, boolean locPattern) {
/* 169 */     this(defaultValue, locale, null, locPattern);
/*     */   }
/*     */   
/*     */   public SqlTimestampLocaleConverter(Object defaultValue, Locale locale, String pattern) {
/* 183 */     this(defaultValue, locale, pattern, false);
/*     */   }
/*     */   
/*     */   public SqlTimestampLocaleConverter(Object defaultValue, Locale locale, String pattern, boolean locPattern) {
/* 198 */     super(defaultValue, locale, pattern, locPattern);
/*     */   }
/*     */   
/*     */   protected Object parse(Object value, String pattern) throws ParseException {
/* 217 */     return new Timestamp(((Date)super.parse(value, pattern)).getTime());
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\beanutils\locale\converters\SqlTimestampLocaleConverter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */