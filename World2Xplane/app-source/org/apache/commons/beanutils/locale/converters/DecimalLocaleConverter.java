/*     */ package org.apache.commons.beanutils.locale.converters;
/*     */ 
/*     */ import java.text.DecimalFormat;
/*     */ import java.text.ParseException;
/*     */ import java.util.Locale;
/*     */ import org.apache.commons.beanutils.locale.BaseLocaleConverter;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ 
/*     */ public class DecimalLocaleConverter extends BaseLocaleConverter {
/*  48 */   private Log log = LogFactory.getLog(DecimalLocaleConverter.class);
/*     */   
/*     */   public DecimalLocaleConverter() {
/*  62 */     this(false);
/*     */   }
/*     */   
/*     */   public DecimalLocaleConverter(boolean locPattern) {
/*  75 */     this(Locale.getDefault(), locPattern);
/*     */   }
/*     */   
/*     */   public DecimalLocaleConverter(Locale locale) {
/*  87 */     this(locale, false);
/*     */   }
/*     */   
/*     */   public DecimalLocaleConverter(Locale locale, boolean locPattern) {
/* 100 */     this(locale, (String)null, locPattern);
/*     */   }
/*     */   
/*     */   public DecimalLocaleConverter(Locale locale, String pattern) {
/* 113 */     this(locale, pattern, false);
/*     */   }
/*     */   
/*     */   public DecimalLocaleConverter(Locale locale, String pattern, boolean locPattern) {
/* 127 */     super(locale, pattern, locPattern);
/*     */   }
/*     */   
/*     */   public DecimalLocaleConverter(Object defaultValue) {
/* 141 */     this(defaultValue, false);
/*     */   }
/*     */   
/*     */   public DecimalLocaleConverter(Object defaultValue, boolean locPattern) {
/* 155 */     this(defaultValue, Locale.getDefault(), locPattern);
/*     */   }
/*     */   
/*     */   public DecimalLocaleConverter(Object defaultValue, Locale locale) {
/* 168 */     this(defaultValue, locale, false);
/*     */   }
/*     */   
/*     */   public DecimalLocaleConverter(Object defaultValue, Locale locale, boolean locPattern) {
/* 182 */     this(defaultValue, locale, (String)null, locPattern);
/*     */   }
/*     */   
/*     */   public DecimalLocaleConverter(Object defaultValue, Locale locale, String pattern) {
/* 196 */     this(defaultValue, locale, pattern, false);
/*     */   }
/*     */   
/*     */   public DecimalLocaleConverter(Object defaultValue, Locale locale, String pattern, boolean locPattern) {
/* 211 */     super(defaultValue, locale, pattern, locPattern);
/*     */   }
/*     */   
/*     */   protected Object parse(Object value, String pattern) throws ParseException {
/* 231 */     if (value instanceof Number)
/* 232 */       return value; 
/* 239 */     DecimalFormat formatter = (DecimalFormat)DecimalFormat.getInstance(this.locale);
/* 243 */     if (pattern != null) {
/* 244 */       if (this.locPattern) {
/* 245 */         formatter.applyLocalizedPattern(pattern);
/*     */       } else {
/* 247 */         formatter.applyPattern(pattern);
/*     */       } 
/*     */     } else {
/* 250 */       this.log.debug("No pattern provided, using default.");
/*     */     } 
/* 253 */     return formatter.parse((String)value);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\beanutils\locale\converters\DecimalLocaleConverter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */