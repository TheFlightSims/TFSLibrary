/*     */ package org.apache.commons.beanutils.locale.converters;
/*     */ 
/*     */ import java.text.ParseException;
/*     */ import java.util.Locale;
/*     */ import org.apache.commons.beanutils.ConversionException;
/*     */ 
/*     */ public class ByteLocaleConverter extends DecimalLocaleConverter {
/*     */   public ByteLocaleConverter() {
/*  52 */     this(false);
/*     */   }
/*     */   
/*     */   public ByteLocaleConverter(boolean locPattern) {
/*  65 */     this(Locale.getDefault(), locPattern);
/*     */   }
/*     */   
/*     */   public ByteLocaleConverter(Locale locale) {
/*  77 */     this(locale, false);
/*     */   }
/*     */   
/*     */   public ByteLocaleConverter(Locale locale, boolean locPattern) {
/*  90 */     this(locale, (String)null, locPattern);
/*     */   }
/*     */   
/*     */   public ByteLocaleConverter(Locale locale, String pattern) {
/* 103 */     this(locale, pattern, false);
/*     */   }
/*     */   
/*     */   public ByteLocaleConverter(Locale locale, String pattern, boolean locPattern) {
/* 117 */     super(locale, pattern, locPattern);
/*     */   }
/*     */   
/*     */   public ByteLocaleConverter(Object defaultValue) {
/* 131 */     this(defaultValue, false);
/*     */   }
/*     */   
/*     */   public ByteLocaleConverter(Object defaultValue, boolean locPattern) {
/* 145 */     this(defaultValue, Locale.getDefault(), locPattern);
/*     */   }
/*     */   
/*     */   public ByteLocaleConverter(Object defaultValue, Locale locale) {
/* 158 */     this(defaultValue, locale, false);
/*     */   }
/*     */   
/*     */   public ByteLocaleConverter(Object defaultValue, Locale locale, boolean locPattern) {
/* 172 */     this(defaultValue, locale, null, locPattern);
/*     */   }
/*     */   
/*     */   public ByteLocaleConverter(Object defaultValue, Locale locale, String pattern) {
/* 186 */     this(defaultValue, locale, pattern, false);
/*     */   }
/*     */   
/*     */   public ByteLocaleConverter(Object defaultValue, Locale locale, String pattern, boolean locPattern) {
/* 201 */     super(defaultValue, locale, pattern, locPattern);
/*     */   }
/*     */   
/*     */   protected Object parse(Object value, String pattern) throws ParseException {
/* 217 */     Number parsed = (Number)super.parse(value, pattern);
/* 218 */     if (parsed.longValue() != parsed.byteValue())
/* 219 */       throw new ConversionException("Supplied number is not of type Byte: " + parsed.longValue()); 
/* 222 */     return new Byte(parsed.byteValue());
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\beanutils\locale\converters\ByteLocaleConverter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */