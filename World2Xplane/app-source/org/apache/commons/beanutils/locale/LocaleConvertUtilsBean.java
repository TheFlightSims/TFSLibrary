/*     */ package org.apache.commons.beanutils.locale;
/*     */ 
/*     */ import java.lang.reflect.Array;
/*     */ import java.math.BigDecimal;
/*     */ import java.math.BigInteger;
/*     */ import java.sql.Date;
/*     */ import java.sql.Time;
/*     */ import java.sql.Timestamp;
/*     */ import java.util.Collection;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.beanutils.BeanUtils;
/*     */ import org.apache.commons.beanutils.locale.converters.BigDecimalLocaleConverter;
/*     */ import org.apache.commons.beanutils.locale.converters.BigIntegerLocaleConverter;
/*     */ import org.apache.commons.beanutils.locale.converters.ByteLocaleConverter;
/*     */ import org.apache.commons.beanutils.locale.converters.DoubleLocaleConverter;
/*     */ import org.apache.commons.beanutils.locale.converters.FloatLocaleConverter;
/*     */ import org.apache.commons.beanutils.locale.converters.IntegerLocaleConverter;
/*     */ import org.apache.commons.beanutils.locale.converters.LongLocaleConverter;
/*     */ import org.apache.commons.beanutils.locale.converters.ShortLocaleConverter;
/*     */ import org.apache.commons.beanutils.locale.converters.SqlDateLocaleConverter;
/*     */ import org.apache.commons.beanutils.locale.converters.SqlTimeLocaleConverter;
/*     */ import org.apache.commons.beanutils.locale.converters.SqlTimestampLocaleConverter;
/*     */ import org.apache.commons.beanutils.locale.converters.StringLocaleConverter;
/*     */ import org.apache.commons.collections.FastHashMap;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ 
/*     */ public class LocaleConvertUtilsBean {
/*     */   public static LocaleConvertUtilsBean getInstance() {
/*  94 */     return LocaleBeanUtilsBean.getLocaleBeanUtilsInstance().getLocaleConvertUtils();
/*     */   }
/*     */   
/* 100 */   private Locale defaultLocale = Locale.getDefault();
/*     */   
/*     */   private boolean applyLocalized = false;
/*     */   
/* 106 */   private Log log = LogFactory.getLog(LocaleConvertUtils.class);
/*     */   
/* 112 */   private FastHashMap mapConverters = new DelegateFastHashMap(BeanUtils.createCache());
/*     */   
/*     */   public LocaleConvertUtilsBean() {
/* 121 */     this.mapConverters.setFast(false);
/* 122 */     deregister();
/* 123 */     this.mapConverters.setFast(true);
/*     */   }
/*     */   
/*     */   public Locale getDefaultLocale() {
/* 134 */     return this.defaultLocale;
/*     */   }
/*     */   
/*     */   public void setDefaultLocale(Locale locale) {
/* 143 */     if (locale == null) {
/* 144 */       this.defaultLocale = Locale.getDefault();
/*     */     } else {
/* 147 */       this.defaultLocale = locale;
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean getApplyLocalized() {
/* 158 */     return this.applyLocalized;
/*     */   }
/*     */   
/*     */   public void setApplyLocalized(boolean newApplyLocalized) {
/* 168 */     this.applyLocalized = newApplyLocalized;
/*     */   }
/*     */   
/*     */   public String convert(Object value) {
/* 183 */     return convert(value, this.defaultLocale, (String)null);
/*     */   }
/*     */   
/*     */   public String convert(Object value, String pattern) {
/* 198 */     return convert(value, this.defaultLocale, pattern);
/*     */   }
/*     */   
/*     */   public String convert(Object value, Locale locale, String pattern) {
/* 215 */     LocaleConverter converter = lookup(String.class, locale);
/* 217 */     return (String)converter.convert(String.class, value, pattern);
/*     */   }
/*     */   
/*     */   public Object convert(String value, Class clazz) {
/* 233 */     return convert(value, clazz, this.defaultLocale, (String)null);
/*     */   }
/*     */   
/*     */   public Object convert(String value, Class clazz, String pattern) {
/* 251 */     return convert(value, clazz, this.defaultLocale, pattern);
/*     */   }
/*     */   
/*     */   public Object convert(String value, Class clazz, Locale locale, String pattern) {
/* 270 */     if (this.log.isDebugEnabled())
/* 271 */       this.log.debug("Convert string " + value + " to class " + clazz.getName() + " using " + locale + " locale and " + pattern + " pattern"); 
/* 276 */     LocaleConverter converter = lookup(clazz, locale);
/* 278 */     if (converter == null)
/* 279 */       converter = lookup(String.class, locale); 
/* 281 */     if (this.log.isTraceEnabled())
/* 282 */       this.log.trace("  Using converter " + converter); 
/* 285 */     return converter.convert(clazz, value, pattern);
/*     */   }
/*     */   
/*     */   public Object convert(String[] values, Class clazz, String pattern) {
/* 302 */     return convert(values, clazz, getDefaultLocale(), pattern);
/*     */   }
/*     */   
/*     */   public Object convert(String[] values, Class clazz) {
/* 318 */     return convert(values, clazz, getDefaultLocale(), (String)null);
/*     */   }
/*     */   
/*     */   public Object convert(String[] values, Class clazz, Locale locale, String pattern) {
/* 336 */     Class type = clazz;
/* 337 */     if (clazz.isArray())
/* 338 */       type = clazz.getComponentType(); 
/* 340 */     if (this.log.isDebugEnabled())
/* 341 */       this.log.debug("Convert String[" + values.length + "] to class " + type.getName() + "[] using " + locale + " locale and " + pattern + " pattern"); 
/* 346 */     Object array = Array.newInstance(type, values.length);
/* 347 */     for (int i = 0; i < values.length; i++)
/* 348 */       Array.set(array, i, convert(values[i], type, locale, pattern)); 
/* 351 */     return array;
/*     */   }
/*     */   
/*     */   public void register(LocaleConverter converter, Class clazz, Locale locale) {
/* 365 */     lookup(locale).put(clazz, converter);
/*     */   }
/*     */   
/*     */   public void deregister() {
/* 373 */     FastHashMap defaultConverter = lookup(this.defaultLocale);
/* 375 */     this.mapConverters.setFast(false);
/* 377 */     this.mapConverters.clear();
/* 378 */     this.mapConverters.put(this.defaultLocale, defaultConverter);
/* 380 */     this.mapConverters.setFast(true);
/*     */   }
/*     */   
/*     */   public void deregister(Locale locale) {
/* 391 */     this.mapConverters.remove(locale);
/*     */   }
/*     */   
/*     */   public void deregister(Class clazz, Locale locale) {
/* 403 */     lookup(locale).remove(clazz);
/*     */   }
/*     */   
/*     */   public LocaleConverter lookup(Class clazz, Locale locale) {
/* 417 */     LocaleConverter converter = (LocaleConverter)lookup(locale).get(clazz);
/* 419 */     if (this.log.isTraceEnabled())
/* 420 */       this.log.trace("LocaleConverter:" + converter); 
/* 423 */     return converter;
/*     */   }
/*     */   
/*     */   protected FastHashMap lookup(Locale locale) {
/*     */     FastHashMap localeConverters;
/* 438 */     if (locale == null) {
/* 439 */       localeConverters = (FastHashMap)this.mapConverters.get(this.defaultLocale);
/*     */     } else {
/* 442 */       localeConverters = (FastHashMap)this.mapConverters.get(locale);
/* 444 */       if (localeConverters == null) {
/* 445 */         localeConverters = create(locale);
/* 446 */         this.mapConverters.put(locale, localeConverters);
/*     */       } 
/*     */     } 
/* 450 */     return localeConverters;
/*     */   }
/*     */   
/*     */   protected FastHashMap create(Locale locale) {
/* 463 */     FastHashMap converter = new DelegateFastHashMap(BeanUtils.createCache());
/* 464 */     converter.setFast(false);
/* 466 */     converter.put(BigDecimal.class, new BigDecimalLocaleConverter(locale, this.applyLocalized));
/* 467 */     converter.put(BigInteger.class, new BigIntegerLocaleConverter(locale, this.applyLocalized));
/* 469 */     converter.put(Byte.class, new ByteLocaleConverter(locale, this.applyLocalized));
/* 470 */     converter.put(byte.class, new ByteLocaleConverter(locale, this.applyLocalized));
/* 472 */     converter.put(Double.class, new DoubleLocaleConverter(locale, this.applyLocalized));
/* 473 */     converter.put(double.class, new DoubleLocaleConverter(locale, this.applyLocalized));
/* 475 */     converter.put(Float.class, new FloatLocaleConverter(locale, this.applyLocalized));
/* 476 */     converter.put(float.class, new FloatLocaleConverter(locale, this.applyLocalized));
/* 478 */     converter.put(Integer.class, new IntegerLocaleConverter(locale, this.applyLocalized));
/* 479 */     converter.put(int.class, new IntegerLocaleConverter(locale, this.applyLocalized));
/* 481 */     converter.put(Long.class, new LongLocaleConverter(locale, this.applyLocalized));
/* 482 */     converter.put(long.class, new LongLocaleConverter(locale, this.applyLocalized));
/* 484 */     converter.put(Short.class, new ShortLocaleConverter(locale, this.applyLocalized));
/* 485 */     converter.put(short.class, new ShortLocaleConverter(locale, this.applyLocalized));
/* 487 */     converter.put(String.class, new StringLocaleConverter(locale, this.applyLocalized));
/* 491 */     converter.put(Date.class, new SqlDateLocaleConverter(locale, "yyyy-MM-dd"));
/* 492 */     converter.put(Time.class, new SqlTimeLocaleConverter(locale, "HH:mm:ss"));
/* 493 */     converter.put(Timestamp.class, new SqlTimestampLocaleConverter(locale, "yyyy-MM-dd HH:mm:ss.S"));
/* 497 */     converter.setFast(true);
/* 499 */     return converter;
/*     */   }
/*     */   
/*     */   private static class DelegateFastHashMap extends FastHashMap {
/*     */     private final Map map;
/*     */     
/*     */     private DelegateFastHashMap(Map map) {
/* 515 */       this.map = map;
/*     */     }
/*     */     
/*     */     public void clear() {
/* 518 */       this.map.clear();
/*     */     }
/*     */     
/*     */     public boolean containsKey(Object key) {
/* 521 */       return this.map.containsKey(key);
/*     */     }
/*     */     
/*     */     public boolean containsValue(Object value) {
/* 524 */       return this.map.containsValue(value);
/*     */     }
/*     */     
/*     */     public Set entrySet() {
/* 527 */       return this.map.entrySet();
/*     */     }
/*     */     
/*     */     public boolean equals(Object o) {
/* 530 */       return this.map.equals(o);
/*     */     }
/*     */     
/*     */     public Object get(Object key) {
/* 533 */       return this.map.get(key);
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 536 */       return this.map.hashCode();
/*     */     }
/*     */     
/*     */     public boolean isEmpty() {
/* 539 */       return this.map.isEmpty();
/*     */     }
/*     */     
/*     */     public Set keySet() {
/* 542 */       return this.map.keySet();
/*     */     }
/*     */     
/*     */     public Object put(Object key, Object value) {
/* 545 */       return this.map.put(key, value);
/*     */     }
/*     */     
/*     */     public void putAll(Map m) {
/* 548 */       this.map.putAll(m);
/*     */     }
/*     */     
/*     */     public Object remove(Object key) {
/* 551 */       return this.map.remove(key);
/*     */     }
/*     */     
/*     */     public int size() {
/* 554 */       return this.map.size();
/*     */     }
/*     */     
/*     */     public Collection values() {
/* 557 */       return this.map.values();
/*     */     }
/*     */     
/*     */     public boolean getFast() {
/* 560 */       return BeanUtils.getCacheFast(this.map);
/*     */     }
/*     */     
/*     */     public void setFast(boolean fast) {
/* 563 */       BeanUtils.setCacheFast(this.map, fast);
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\beanutils\locale\LocaleConvertUtilsBean.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */