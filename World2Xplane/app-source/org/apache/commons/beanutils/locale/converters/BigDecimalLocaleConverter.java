/*     */ package org.apache.commons.beanutils.locale.converters;
/*     */ 
/*     */ import java.math.BigDecimal;
/*     */ import java.text.ParseException;
/*     */ import java.util.Locale;
/*     */ import org.apache.commons.beanutils.ConversionException;
/*     */ 
/*     */ public class BigDecimalLocaleConverter extends DecimalLocaleConverter {
/*     */   public BigDecimalLocaleConverter() {
/*  50 */     this(false);
/*     */   }
/*     */   
/*     */   public BigDecimalLocaleConverter(boolean locPattern) {
/*  63 */     this(Locale.getDefault(), locPattern);
/*     */   }
/*     */   
/*     */   public BigDecimalLocaleConverter(Locale locale) {
/*  75 */     this(locale, false);
/*     */   }
/*     */   
/*     */   public BigDecimalLocaleConverter(Locale locale, boolean locPattern) {
/*  88 */     this(locale, (String)null, locPattern);
/*     */   }
/*     */   
/*     */   public BigDecimalLocaleConverter(Locale locale, String pattern) {
/* 101 */     this(locale, pattern, false);
/*     */   }
/*     */   
/*     */   public BigDecimalLocaleConverter(Locale locale, String pattern, boolean locPattern) {
/* 115 */     super(locale, pattern, locPattern);
/*     */   }
/*     */   
/*     */   public BigDecimalLocaleConverter(Object defaultValue) {
/* 129 */     this(defaultValue, false);
/*     */   }
/*     */   
/*     */   public BigDecimalLocaleConverter(Object defaultValue, boolean locPattern) {
/* 143 */     this(defaultValue, Locale.getDefault(), locPattern);
/*     */   }
/*     */   
/*     */   public BigDecimalLocaleConverter(Object defaultValue, Locale locale) {
/* 156 */     this(defaultValue, locale, false);
/*     */   }
/*     */   
/*     */   public BigDecimalLocaleConverter(Object defaultValue, Locale locale, boolean locPattern) {
/* 170 */     this(defaultValue, locale, null, locPattern);
/*     */   }
/*     */   
/*     */   public BigDecimalLocaleConverter(Object defaultValue, Locale locale, String pattern) {
/* 184 */     this(defaultValue, locale, pattern, false);
/*     */   }
/*     */   
/*     */   public BigDecimalLocaleConverter(Object defaultValue, Locale locale, String pattern, boolean locPattern) {
/* 199 */     super(defaultValue, locale, pattern, locPattern);
/*     */   }
/*     */   
/*     */   protected Object parse(Object value, String pattern) throws ParseException {
/* 217 */     Object result = super.parse(value, pattern);
/* 219 */     if (result == null || result instanceof BigDecimal)
/* 220 */       return result; 
/*     */     try {
/* 224 */       return new BigDecimal(result.toString());
/* 226 */     } catch (NumberFormatException ex) {
/* 227 */       throw new ConversionException("Suplied number is not of type BigDecimal: " + result);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\beanutils\locale\converters\BigDecimalLocaleConverter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */