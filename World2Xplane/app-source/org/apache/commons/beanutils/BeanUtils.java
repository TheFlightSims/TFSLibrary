/*     */ package org.apache.commons.beanutils;
/*     */ 
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class BeanUtils {
/*  60 */   private static int debug = 0;
/*     */   
/*     */   public static int getDebug() {
/*  70 */     return debug;
/*     */   }
/*     */   
/*     */   public static void setDebug(int newDebug) {
/*  81 */     debug = newDebug;
/*     */   }
/*     */   
/*     */   public static Object cloneBean(Object bean) throws IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {
/* 110 */     return BeanUtilsBean.getInstance().cloneBean(bean);
/*     */   }
/*     */   
/*     */   public static void copyProperties(Object dest, Object orig) throws IllegalAccessException, InvocationTargetException {
/* 137 */     BeanUtilsBean.getInstance().copyProperties(dest, orig);
/*     */   }
/*     */   
/*     */   public static void copyProperty(Object bean, String name, Object value) throws IllegalAccessException, InvocationTargetException {
/* 160 */     BeanUtilsBean.getInstance().copyProperty(bean, name, value);
/*     */   }
/*     */   
/*     */   public static Map describe(Object bean) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
/* 185 */     return BeanUtilsBean.getInstance().describe(bean);
/*     */   }
/*     */   
/*     */   public static String[] getArrayProperty(Object bean, String name) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
/* 211 */     return BeanUtilsBean.getInstance().getArrayProperty(bean, name);
/*     */   }
/*     */   
/*     */   public static String getIndexedProperty(Object bean, String name) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
/* 238 */     return BeanUtilsBean.getInstance().getIndexedProperty(bean, name);
/*     */   }
/*     */   
/*     */   public static String getIndexedProperty(Object bean, String name, int index) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
/* 268 */     return BeanUtilsBean.getInstance().getIndexedProperty(bean, name, index);
/*     */   }
/*     */   
/*     */   public static String getMappedProperty(Object bean, String name) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
/* 296 */     return BeanUtilsBean.getInstance().getMappedProperty(bean, name);
/*     */   }
/*     */   
/*     */   public static String getMappedProperty(Object bean, String name, String key) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
/* 325 */     return BeanUtilsBean.getInstance().getMappedProperty(bean, name, key);
/*     */   }
/*     */   
/*     */   public static String getNestedProperty(Object bean, String name) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
/* 354 */     return BeanUtilsBean.getInstance().getNestedProperty(bean, name);
/*     */   }
/*     */   
/*     */   public static String getProperty(Object bean, String name) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
/* 382 */     return BeanUtilsBean.getInstance().getProperty(bean, name);
/*     */   }
/*     */   
/*     */   public static String getSimpleProperty(Object bean, String name) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
/* 409 */     return BeanUtilsBean.getInstance().getSimpleProperty(bean, name);
/*     */   }
/*     */   
/*     */   public static void populate(Object bean, Map properties) throws IllegalAccessException, InvocationTargetException {
/* 433 */     BeanUtilsBean.getInstance().populate(bean, properties);
/*     */   }
/*     */   
/*     */   public static void setProperty(Object bean, String name, Object value) throws IllegalAccessException, InvocationTargetException {
/* 456 */     BeanUtilsBean.getInstance().setProperty(bean, name, value);
/*     */   }
/*     */   
/*     */   public static boolean initCause(Throwable throwable, Throwable cause) {
/* 468 */     return BeanUtilsBean.getInstance().initCause(throwable, cause);
/*     */   }
/*     */   
/*     */   public static Map createCache() {
/* 477 */     return new WeakFastHashMap();
/*     */   }
/*     */   
/*     */   public static boolean getCacheFast(Map map) {
/* 487 */     if (map instanceof WeakFastHashMap)
/* 488 */       return ((WeakFastHashMap)map).getFast(); 
/* 490 */     return false;
/*     */   }
/*     */   
/*     */   public static void setCacheFast(Map map, boolean fast) {
/* 501 */     if (map instanceof WeakFastHashMap)
/* 502 */       ((WeakFastHashMap)map).setFast(fast); 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\beanutils\BeanUtils.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */