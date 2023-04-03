/*     */ package org.apache.commons.beanutils.locale;
/*     */ 
/*     */ import java.util.Locale;
/*     */ import org.apache.commons.collections.FastHashMap;
/*     */ 
/*     */ public class LocaleConvertUtils {
/*     */   public static Locale getDefaultLocale() {
/*  49 */     return LocaleConvertUtilsBean.getInstance().getDefaultLocale();
/*     */   }
/*     */   
/*     */   public static void setDefaultLocale(Locale locale) {
/*  63 */     LocaleConvertUtilsBean.getInstance().setDefaultLocale(locale);
/*     */   }
/*     */   
/*     */   public static boolean getApplyLocalized() {
/*  76 */     return LocaleConvertUtilsBean.getInstance().getApplyLocalized();
/*     */   }
/*     */   
/*     */   public static void setApplyLocalized(boolean newApplyLocalized) {
/*  89 */     LocaleConvertUtilsBean.getInstance().setApplyLocalized(newApplyLocalized);
/*     */   }
/*     */   
/*     */   public static String convert(Object value) {
/* 104 */     return LocaleConvertUtilsBean.getInstance().convert(value);
/*     */   }
/*     */   
/*     */   public static String convert(Object value, String pattern) {
/* 119 */     return LocaleConvertUtilsBean.getInstance().convert(value, pattern);
/*     */   }
/*     */   
/*     */   public static String convert(Object value, Locale locale, String pattern) {
/* 136 */     return LocaleConvertUtilsBean.getInstance().convert(value, locale, pattern);
/*     */   }
/*     */   
/*     */   public static Object convert(String value, Class clazz) {
/* 152 */     return LocaleConvertUtilsBean.getInstance().convert(value, clazz);
/*     */   }
/*     */   
/*     */   public static Object convert(String value, Class clazz, String pattern) {
/* 170 */     return LocaleConvertUtilsBean.getInstance().convert(value, clazz, pattern);
/*     */   }
/*     */   
/*     */   public static Object convert(String value, Class clazz, Locale locale, String pattern) {
/* 189 */     return LocaleConvertUtilsBean.getInstance().convert(value, clazz, locale, pattern);
/*     */   }
/*     */   
/*     */   public static Object convert(String[] values, Class clazz, String pattern) {
/* 206 */     return LocaleConvertUtilsBean.getInstance().convert(values, clazz, pattern);
/*     */   }
/*     */   
/*     */   public static Object convert(String[] values, Class clazz) {
/* 222 */     return LocaleConvertUtilsBean.getInstance().convert(values, clazz);
/*     */   }
/*     */   
/*     */   public static Object convert(String[] values, Class clazz, Locale locale, String pattern) {
/* 240 */     return LocaleConvertUtilsBean.getInstance().convert(values, clazz, locale, pattern);
/*     */   }
/*     */   
/*     */   public static void register(LocaleConverter converter, Class clazz, Locale locale) {
/* 257 */     LocaleConvertUtilsBean.getInstance().register(converter, clazz, locale);
/*     */   }
/*     */   
/*     */   public static void deregister() {
/* 269 */     LocaleConvertUtilsBean.getInstance().deregister();
/*     */   }
/*     */   
/*     */   public static void deregister(Locale locale) {
/* 283 */     LocaleConvertUtilsBean.getInstance().deregister(locale);
/*     */   }
/*     */   
/*     */   public static void deregister(Class clazz, Locale locale) {
/* 298 */     LocaleConvertUtilsBean.getInstance().deregister(clazz, locale);
/*     */   }
/*     */   
/*     */   public static LocaleConverter lookup(Class clazz, Locale locale) {
/* 315 */     return LocaleConvertUtilsBean.getInstance().lookup(clazz, locale);
/*     */   }
/*     */   
/*     */   protected static FastHashMap lookup(Locale locale) {
/* 330 */     return LocaleConvertUtilsBean.getInstance().lookup(locale);
/*     */   }
/*     */   
/*     */   protected static FastHashMap create(Locale locale) {
/* 346 */     return LocaleConvertUtilsBean.getInstance().create(locale);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\beanutils\locale\LocaleConvertUtils.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */