/*     */ package org.apache.commons.beanutils.locale.converters;
/*     */ 
/*     */ import java.text.ParseException;
/*     */ import java.util.Locale;
/*     */ import org.apache.commons.beanutils.ConversionException;
/*     */ 
/*     */ public class ShortLocaleConverter extends DecimalLocaleConverter {
/*     */   public ShortLocaleConverter() {
/*  50 */     this(false);
/*     */   }
/*     */   
/*     */   public ShortLocaleConverter(boolean locPattern) {
/*  63 */     this(Locale.getDefault(), locPattern);
/*     */   }
/*     */   
/*     */   public ShortLocaleConverter(Locale locale) {
/*  75 */     this(locale, false);
/*     */   }
/*     */   
/*     */   public ShortLocaleConverter(Locale locale, boolean locPattern) {
/*  88 */     this(locale, (String)null, locPattern);
/*     */   }
/*     */   
/*     */   public ShortLocaleConverter(Locale locale, String pattern) {
/* 101 */     this(locale, pattern, false);
/*     */   }
/*     */   
/*     */   public ShortLocaleConverter(Locale locale, String pattern, boolean locPattern) {
/* 115 */     super(locale, pattern, locPattern);
/*     */   }
/*     */   
/*     */   public ShortLocaleConverter(Object defaultValue) {
/* 129 */     this(defaultValue, false);
/*     */   }
/*     */   
/*     */   public ShortLocaleConverter(Object defaultValue, boolean locPattern) {
/* 143 */     this(defaultValue, Locale.getDefault(), locPattern);
/*     */   }
/*     */   
/*     */   public ShortLocaleConverter(Object defaultValue, Locale locale) {
/* 156 */     this(defaultValue, locale, false);
/*     */   }
/*     */   
/*     */   public ShortLocaleConverter(Object defaultValue, Locale locale, boolean locPattern) {
/* 170 */     this(defaultValue, locale, null, locPattern);
/*     */   }
/*     */   
/*     */   public ShortLocaleConverter(Object defaultValue, Locale locale, String pattern) {
/* 184 */     this(defaultValue, locale, pattern, false);
/*     */   }
/*     */   
/*     */   public ShortLocaleConverter(Object defaultValue, Locale locale, String pattern, boolean locPattern) {
/* 199 */     super(defaultValue, locale, pattern, locPattern);
/*     */   }
/*     */   
/*     */   protected Object parse(Object value, String pattern) throws ParseException {
/* 217 */     Object result = super.parse(value, pattern);
/* 219 */     if (result == null || result instanceof Short)
/* 220 */       return result; 
/* 223 */     Number parsed = (Number)result;
/* 224 */     if (parsed.longValue() != parsed.shortValue())
/* 225 */       throw new ConversionException("Supplied number is not of type Short: " + parsed.longValue()); 
/* 229 */     return new Short(parsed.shortValue());
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\beanutils\locale\converters\ShortLocaleConverter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */