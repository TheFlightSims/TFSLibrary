/*     */ package org.apache.commons.beanutils.locale.converters;
/*     */ 
/*     */ import java.text.ParseException;
/*     */ import java.util.Locale;
/*     */ import org.apache.commons.beanutils.ConversionException;
/*     */ 
/*     */ public class IntegerLocaleConverter extends DecimalLocaleConverter {
/*     */   public IntegerLocaleConverter() {
/*  53 */     this(false);
/*     */   }
/*     */   
/*     */   public IntegerLocaleConverter(boolean locPattern) {
/*  66 */     this(Locale.getDefault(), locPattern);
/*     */   }
/*     */   
/*     */   public IntegerLocaleConverter(Locale locale) {
/*  78 */     this(locale, false);
/*     */   }
/*     */   
/*     */   public IntegerLocaleConverter(Locale locale, boolean locPattern) {
/*  91 */     this(locale, (String)null, locPattern);
/*     */   }
/*     */   
/*     */   public IntegerLocaleConverter(Locale locale, String pattern) {
/* 104 */     this(locale, pattern, false);
/*     */   }
/*     */   
/*     */   public IntegerLocaleConverter(Locale locale, String pattern, boolean locPattern) {
/* 118 */     super(locale, pattern, locPattern);
/*     */   }
/*     */   
/*     */   public IntegerLocaleConverter(Object defaultValue) {
/* 132 */     this(defaultValue, false);
/*     */   }
/*     */   
/*     */   public IntegerLocaleConverter(Object defaultValue, boolean locPattern) {
/* 146 */     this(defaultValue, Locale.getDefault(), locPattern);
/*     */   }
/*     */   
/*     */   public IntegerLocaleConverter(Object defaultValue, Locale locale) {
/* 159 */     this(defaultValue, locale, false);
/*     */   }
/*     */   
/*     */   public IntegerLocaleConverter(Object defaultValue, Locale locale, boolean locPattern) {
/* 173 */     this(defaultValue, locale, null, locPattern);
/*     */   }
/*     */   
/*     */   public IntegerLocaleConverter(Object defaultValue, Locale locale, String pattern) {
/* 187 */     this(defaultValue, locale, pattern, false);
/*     */   }
/*     */   
/*     */   public IntegerLocaleConverter(Object defaultValue, Locale locale, String pattern, boolean locPattern) {
/* 202 */     super(defaultValue, locale, pattern, locPattern);
/*     */   }
/*     */   
/*     */   protected Object parse(Object value, String pattern) throws ParseException {
/* 218 */     Number parsed = (Number)super.parse(value, pattern);
/* 219 */     if (parsed.longValue() != parsed.intValue())
/* 220 */       throw new ConversionException("Suplied number is not of type Integer: " + parsed.longValue()); 
/* 222 */     return new Integer(parsed.intValue());
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\beanutils\locale\converters\IntegerLocaleConverter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */