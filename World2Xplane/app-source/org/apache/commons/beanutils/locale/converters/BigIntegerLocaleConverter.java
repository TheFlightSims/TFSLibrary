/*     */ package org.apache.commons.beanutils.locale.converters;
/*     */ 
/*     */ import java.math.BigInteger;
/*     */ import java.text.ParseException;
/*     */ import java.util.Locale;
/*     */ import org.apache.commons.beanutils.ConversionException;
/*     */ 
/*     */ public class BigIntegerLocaleConverter extends DecimalLocaleConverter {
/*     */   public BigIntegerLocaleConverter() {
/*  51 */     this(false);
/*     */   }
/*     */   
/*     */   public BigIntegerLocaleConverter(boolean locPattern) {
/*  64 */     this(Locale.getDefault(), locPattern);
/*     */   }
/*     */   
/*     */   public BigIntegerLocaleConverter(Locale locale) {
/*  76 */     this(locale, false);
/*     */   }
/*     */   
/*     */   public BigIntegerLocaleConverter(Locale locale, boolean locPattern) {
/*  89 */     this(locale, (String)null, locPattern);
/*     */   }
/*     */   
/*     */   public BigIntegerLocaleConverter(Locale locale, String pattern) {
/* 102 */     this(locale, pattern, false);
/*     */   }
/*     */   
/*     */   public BigIntegerLocaleConverter(Locale locale, String pattern, boolean locPattern) {
/* 116 */     super(locale, pattern, locPattern);
/*     */   }
/*     */   
/*     */   public BigIntegerLocaleConverter(Object defaultValue) {
/* 130 */     this(defaultValue, false);
/*     */   }
/*     */   
/*     */   public BigIntegerLocaleConverter(Object defaultValue, boolean locPattern) {
/* 144 */     this(defaultValue, Locale.getDefault(), locPattern);
/*     */   }
/*     */   
/*     */   public BigIntegerLocaleConverter(Object defaultValue, Locale locale) {
/* 157 */     this(defaultValue, locale, false);
/*     */   }
/*     */   
/*     */   public BigIntegerLocaleConverter(Object defaultValue, Locale locale, boolean locPattern) {
/* 171 */     this(defaultValue, locale, null, locPattern);
/*     */   }
/*     */   
/*     */   public BigIntegerLocaleConverter(Object defaultValue, Locale locale, String pattern) {
/* 185 */     this(defaultValue, locale, pattern, false);
/*     */   }
/*     */   
/*     */   public BigIntegerLocaleConverter(Object defaultValue, Locale locale, String pattern, boolean locPattern) {
/* 200 */     super(defaultValue, locale, pattern, locPattern);
/*     */   }
/*     */   
/*     */   protected Object parse(Object value, String pattern) throws ParseException {
/* 218 */     Object result = super.parse(value, pattern);
/* 220 */     if (result == null || result instanceof BigInteger)
/* 221 */       return result; 
/* 224 */     if (result instanceof Number)
/* 225 */       return BigInteger.valueOf(((Number)result).longValue()); 
/*     */     try {
/* 229 */       return new BigInteger(result.toString());
/* 231 */     } catch (NumberFormatException ex) {
/* 232 */       throw new ConversionException("Suplied number is not of type BigInteger: " + result);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\beanutils\locale\converters\BigIntegerLocaleConverter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */