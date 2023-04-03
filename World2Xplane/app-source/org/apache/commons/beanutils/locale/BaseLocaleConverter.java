/*     */ package org.apache.commons.beanutils.locale;
/*     */ 
/*     */ import java.text.ParseException;
/*     */ import java.util.Locale;
/*     */ import org.apache.commons.beanutils.ConversionException;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ 
/*     */ public abstract class BaseLocaleConverter implements LocaleConverter {
/*  43 */   private Log log = LogFactory.getLog(BaseLocaleConverter.class);
/*     */   
/*  46 */   private Object defaultValue = null;
/*     */   
/*     */   protected boolean useDefault = false;
/*     */   
/*  52 */   protected Locale locale = Locale.getDefault();
/*     */   
/*  55 */   protected String pattern = null;
/*     */   
/*     */   protected boolean locPattern = false;
/*     */   
/*     */   protected BaseLocaleConverter(Locale locale, String pattern) {
/*  72 */     this(null, locale, pattern, false, false);
/*     */   }
/*     */   
/*     */   protected BaseLocaleConverter(Locale locale, String pattern, boolean locPattern) {
/*  85 */     this(null, locale, pattern, false, locPattern);
/*     */   }
/*     */   
/*     */   protected BaseLocaleConverter(Object defaultValue, Locale locale, String pattern) {
/*  99 */     this(defaultValue, locale, pattern, false);
/*     */   }
/*     */   
/*     */   protected BaseLocaleConverter(Object defaultValue, Locale locale, String pattern, boolean locPattern) {
/* 113 */     this(defaultValue, locale, pattern, true, locPattern);
/*     */   }
/*     */   
/*     */   private BaseLocaleConverter(Object defaultValue, Locale locale, String pattern, boolean useDefault, boolean locPattern) {
/* 129 */     if (useDefault) {
/* 130 */       this.defaultValue = defaultValue;
/* 131 */       this.useDefault = true;
/*     */     } 
/* 134 */     if (locale != null)
/* 135 */       this.locale = locale; 
/* 138 */     this.pattern = pattern;
/* 139 */     this.locPattern = locPattern;
/*     */   }
/*     */   
/*     */   public Object convert(Object value) {
/* 170 */     return convert(value, (String)null);
/*     */   }
/*     */   
/*     */   public Object convert(Object value, String pattern) {
/* 184 */     return convert(null, value, pattern);
/*     */   }
/*     */   
/*     */   public Object convert(Class type, Object value) {
/* 199 */     return convert(type, value, null);
/*     */   }
/*     */   
/*     */   public Object convert(Class type, Object value, String pattern) {
/* 217 */     if (value == null) {
/* 218 */       if (this.useDefault)
/* 219 */         return this.defaultValue; 
/* 223 */       this.log.debug("Null value specified for conversion, returing null");
/* 224 */       return null;
/*     */     } 
/*     */     try {
/* 229 */       if (pattern != null)
/* 230 */         return parse(value, pattern); 
/* 232 */       return parse(value, this.pattern);
/* 234 */     } catch (Exception e) {
/* 235 */       if (this.useDefault)
/* 236 */         return this.defaultValue; 
/* 238 */       if (e instanceof ConversionException)
/* 239 */         throw (ConversionException)e; 
/* 241 */       throw new ConversionException(e);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected abstract Object parse(Object paramObject, String paramString) throws ParseException;
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\beanutils\locale\BaseLocaleConverter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */