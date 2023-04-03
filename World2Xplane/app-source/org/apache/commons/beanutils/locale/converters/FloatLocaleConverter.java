/*     */ package org.apache.commons.beanutils.locale.converters;
/*     */ 
/*     */ import java.text.ParseException;
/*     */ import java.util.Locale;
/*     */ import org.apache.commons.beanutils.ConversionException;
/*     */ 
/*     */ public class FloatLocaleConverter extends DecimalLocaleConverter {
/*     */   public FloatLocaleConverter() {
/*  52 */     this(false);
/*     */   }
/*     */   
/*     */   public FloatLocaleConverter(boolean locPattern) {
/*  65 */     this(Locale.getDefault(), locPattern);
/*     */   }
/*     */   
/*     */   public FloatLocaleConverter(Locale locale) {
/*  77 */     this(locale, false);
/*     */   }
/*     */   
/*     */   public FloatLocaleConverter(Locale locale, boolean locPattern) {
/*  90 */     this(locale, (String)null, locPattern);
/*     */   }
/*     */   
/*     */   public FloatLocaleConverter(Locale locale, String pattern) {
/* 103 */     this(locale, pattern, false);
/*     */   }
/*     */   
/*     */   public FloatLocaleConverter(Locale locale, String pattern, boolean locPattern) {
/* 117 */     super(locale, pattern, locPattern);
/*     */   }
/*     */   
/*     */   public FloatLocaleConverter(Object defaultValue) {
/* 131 */     this(defaultValue, false);
/*     */   }
/*     */   
/*     */   public FloatLocaleConverter(Object defaultValue, boolean locPattern) {
/* 145 */     this(defaultValue, Locale.getDefault(), locPattern);
/*     */   }
/*     */   
/*     */   public FloatLocaleConverter(Object defaultValue, Locale locale) {
/* 158 */     this(defaultValue, locale, false);
/*     */   }
/*     */   
/*     */   public FloatLocaleConverter(Object defaultValue, Locale locale, boolean locPattern) {
/* 172 */     this(defaultValue, locale, null, locPattern);
/*     */   }
/*     */   
/*     */   public FloatLocaleConverter(Object defaultValue, Locale locale, String pattern) {
/* 186 */     this(defaultValue, locale, pattern, false);
/*     */   }
/*     */   
/*     */   public FloatLocaleConverter(Object defaultValue, Locale locale, String pattern, boolean locPattern) {
/* 201 */     super(defaultValue, locale, pattern, locPattern);
/*     */   }
/*     */   
/*     */   protected Object parse(Object value, String pattern) throws ParseException {
/* 218 */     Number parsed = (Number)super.parse(value, pattern);
/* 219 */     double doubleValue = parsed.doubleValue();
/* 220 */     double posDouble = (doubleValue >= 0.0D) ? doubleValue : (doubleValue * -1.0D);
/* 221 */     if (posDouble != 0.0D && (posDouble < 1.401298464324817E-45D || posDouble > 3.4028234663852886E38D))
/* 222 */       throw new ConversionException("Supplied number is not of type Float: " + parsed); 
/* 224 */     return new Float(parsed.floatValue());
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\beanutils\locale\converters\FloatLocaleConverter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */