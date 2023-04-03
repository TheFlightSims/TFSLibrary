/*     */ package org.apache.commons.beanutils.locale.converters;
/*     */ 
/*     */ import java.text.ParseException;
/*     */ import java.util.Locale;
/*     */ 
/*     */ public class DoubleLocaleConverter extends DecimalLocaleConverter {
/*     */   public DoubleLocaleConverter() {
/*  51 */     this(false);
/*     */   }
/*     */   
/*     */   public DoubleLocaleConverter(boolean locPattern) {
/*  64 */     this(Locale.getDefault(), locPattern);
/*     */   }
/*     */   
/*     */   public DoubleLocaleConverter(Locale locale) {
/*  76 */     this(locale, false);
/*     */   }
/*     */   
/*     */   public DoubleLocaleConverter(Locale locale, boolean locPattern) {
/*  89 */     this(locale, (String)null, locPattern);
/*     */   }
/*     */   
/*     */   public DoubleLocaleConverter(Locale locale, String pattern) {
/* 102 */     this(locale, pattern, false);
/*     */   }
/*     */   
/*     */   public DoubleLocaleConverter(Locale locale, String pattern, boolean locPattern) {
/* 116 */     super(locale, pattern, locPattern);
/*     */   }
/*     */   
/*     */   public DoubleLocaleConverter(Object defaultValue) {
/* 130 */     this(defaultValue, false);
/*     */   }
/*     */   
/*     */   public DoubleLocaleConverter(Object defaultValue, boolean locPattern) {
/* 144 */     this(defaultValue, Locale.getDefault(), locPattern);
/*     */   }
/*     */   
/*     */   public DoubleLocaleConverter(Object defaultValue, Locale locale) {
/* 157 */     this(defaultValue, locale, false);
/*     */   }
/*     */   
/*     */   public DoubleLocaleConverter(Object defaultValue, Locale locale, boolean locPattern) {
/* 171 */     this(defaultValue, locale, null, locPattern);
/*     */   }
/*     */   
/*     */   public DoubleLocaleConverter(Object defaultValue, Locale locale, String pattern) {
/* 185 */     this(defaultValue, locale, pattern, false);
/*     */   }
/*     */   
/*     */   public DoubleLocaleConverter(Object defaultValue, Locale locale, String pattern, boolean locPattern) {
/* 200 */     super(defaultValue, locale, pattern, locPattern);
/*     */   }
/*     */   
/*     */   protected Object parse(Object value, String pattern) throws ParseException {
/* 216 */     Number result = (Number)super.parse(value, pattern);
/* 217 */     if (result instanceof Long)
/* 218 */       return new Double(result.doubleValue()); 
/* 220 */     return result;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\beanutils\locale\converters\DoubleLocaleConverter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */