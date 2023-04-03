/*     */ package org.apache.commons.beanutils.locale;
/*     */ 
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.util.Locale;
/*     */ import org.apache.commons.beanutils.BeanUtils;
/*     */ 
/*     */ public class LocaleBeanUtils extends BeanUtils {
/*     */   public static Locale getDefaultLocale() {
/*  58 */     return LocaleBeanUtilsBean.getLocaleBeanUtilsInstance().getDefaultLocale();
/*     */   }
/*     */   
/*     */   public static void setDefaultLocale(Locale locale) {
/*  72 */     LocaleBeanUtilsBean.getLocaleBeanUtilsInstance().setDefaultLocale(locale);
/*     */   }
/*     */   
/*     */   public static boolean getApplyLocalized() {
/*  86 */     return LocaleBeanUtilsBean.getLocaleBeanUtilsInstance().getApplyLocalized();
/*     */   }
/*     */   
/*     */   public static void setApplyLocalized(boolean newApplyLocalized) {
/* 100 */     LocaleBeanUtilsBean.getLocaleBeanUtilsInstance().setApplyLocalized(newApplyLocalized);
/*     */   }
/*     */   
/*     */   public static String getIndexedProperty(Object bean, String name, String pattern) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
/* 131 */     return LocaleBeanUtilsBean.getLocaleBeanUtilsInstance().getIndexedProperty(bean, name, pattern);
/*     */   }
/*     */   
/*     */   public static String getIndexedProperty(Object bean, String name) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
/* 159 */     return LocaleBeanUtilsBean.getLocaleBeanUtilsInstance().getIndexedProperty(bean, name);
/*     */   }
/*     */   
/*     */   public static String getIndexedProperty(Object bean, String name, int index, String pattern) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
/* 188 */     return LocaleBeanUtilsBean.getLocaleBeanUtilsInstance().getIndexedProperty(bean, name, index, pattern);
/*     */   }
/*     */   
/*     */   public static String getIndexedProperty(Object bean, String name, int index) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
/* 216 */     return LocaleBeanUtilsBean.getLocaleBeanUtilsInstance().getIndexedProperty(bean, name, index);
/*     */   }
/*     */   
/*     */   public static String getSimpleProperty(Object bean, String name, String pattern) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
/* 244 */     return LocaleBeanUtilsBean.getLocaleBeanUtilsInstance().getSimpleProperty(bean, name, pattern);
/*     */   }
/*     */   
/*     */   public static String getSimpleProperty(Object bean, String name) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
/* 271 */     return LocaleBeanUtilsBean.getLocaleBeanUtilsInstance().getSimpleProperty(bean, name);
/*     */   }
/*     */   
/*     */   public static String getMappedProperty(Object bean, String name, String key, String pattern) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
/* 300 */     return LocaleBeanUtilsBean.getLocaleBeanUtilsInstance().getMappedProperty(bean, name, key, pattern);
/*     */   }
/*     */   
/*     */   public static String getMappedProperty(Object bean, String name, String key) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
/* 330 */     return LocaleBeanUtilsBean.getLocaleBeanUtilsInstance().getMappedProperty(bean, name, key);
/*     */   }
/*     */   
/*     */   public static String getMappedPropertyLocale(Object bean, String name, String pattern) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
/* 359 */     return LocaleBeanUtilsBean.getLocaleBeanUtilsInstance().getMappedPropertyLocale(bean, name, pattern);
/*     */   }
/*     */   
/*     */   public static String getMappedProperty(Object bean, String name) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
/* 388 */     return LocaleBeanUtilsBean.getLocaleBeanUtilsInstance().getMappedProperty(bean, name);
/*     */   }
/*     */   
/*     */   public static String getNestedProperty(Object bean, String name, String pattern) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
/* 416 */     return LocaleBeanUtilsBean.getLocaleBeanUtilsInstance().getNestedProperty(bean, name, pattern);
/*     */   }
/*     */   
/*     */   public static String getNestedProperty(Object bean, String name) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
/* 442 */     return LocaleBeanUtilsBean.getLocaleBeanUtilsInstance().getNestedProperty(bean, name);
/*     */   }
/*     */   
/*     */   public static String getProperty(Object bean, String name, String pattern) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
/* 470 */     return LocaleBeanUtilsBean.getLocaleBeanUtilsInstance().getProperty(bean, name, pattern);
/*     */   }
/*     */   
/*     */   public static String getProperty(Object bean, String name) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
/* 497 */     return LocaleBeanUtilsBean.getLocaleBeanUtilsInstance().getProperty(bean, name);
/*     */   }
/*     */   
/*     */   public static void setProperty(Object bean, String name, Object value) throws IllegalAccessException, InvocationTargetException {
/* 521 */     LocaleBeanUtilsBean.getLocaleBeanUtilsInstance().setProperty(bean, name, value);
/*     */   }
/*     */   
/*     */   public static void setProperty(Object bean, String name, Object value, String pattern) throws IllegalAccessException, InvocationTargetException {
/* 546 */     LocaleBeanUtilsBean.getLocaleBeanUtilsInstance().setProperty(bean, name, value, pattern);
/*     */   }
/*     */   
/*     */   protected static Class definePropertyType(Object target, String name, String propName) throws IllegalAccessException, InvocationTargetException {
/* 569 */     return LocaleBeanUtilsBean.getLocaleBeanUtilsInstance().definePropertyType(target, name, propName);
/*     */   }
/*     */   
/*     */   protected static Object convert(Class type, int index, Object value, String pattern) {
/* 587 */     return LocaleBeanUtilsBean.getLocaleBeanUtilsInstance().convert(type, index, value, pattern);
/*     */   }
/*     */   
/*     */   protected static Object convert(Class type, int index, Object value) {
/* 603 */     return LocaleBeanUtilsBean.getLocaleBeanUtilsInstance().convert(type, index, value);
/*     */   }
/*     */   
/*     */   protected static void invokeSetter(Object target, String propName, String key, int index, Object newValue) throws IllegalAccessException, InvocationTargetException {
/* 627 */     LocaleBeanUtilsBean.getLocaleBeanUtilsInstance().invokeSetter(target, propName, key, index, newValue);
/*     */   }
/*     */   
/*     */   protected static Descriptor calculate(Object bean, String name) throws IllegalAccessException, InvocationTargetException {
/* 646 */     LocaleBeanUtilsBean.Descriptor descriptor = LocaleBeanUtilsBean.getLocaleBeanUtilsInstance().calculate(bean, name);
/* 648 */     return new Descriptor(descriptor.getTarget(), descriptor.getName(), descriptor.getPropName(), descriptor.getKey(), descriptor.getIndex());
/*     */   }
/*     */   
/*     */   protected static class Descriptor {
/* 659 */     private int index = -1;
/*     */     
/*     */     private String name;
/*     */     
/*     */     private String propName;
/*     */     
/*     */     private String key;
/*     */     
/*     */     private Object target;
/*     */     
/*     */     public Descriptor(Object target, String name, String propName, String key, int index) {
/* 676 */       setTarget(target);
/* 677 */       setName(name);
/* 678 */       setPropName(propName);
/* 679 */       setKey(key);
/* 680 */       setIndex(index);
/*     */     }
/*     */     
/*     */     public Object getTarget() {
/* 689 */       return this.target;
/*     */     }
/*     */     
/*     */     public void setTarget(Object target) {
/* 698 */       this.target = target;
/*     */     }
/*     */     
/*     */     public String getKey() {
/* 707 */       return this.key;
/*     */     }
/*     */     
/*     */     public void setKey(String key) {
/* 716 */       this.key = key;
/*     */     }
/*     */     
/*     */     public int getIndex() {
/* 725 */       return this.index;
/*     */     }
/*     */     
/*     */     public void setIndex(int index) {
/* 734 */       this.index = index;
/*     */     }
/*     */     
/*     */     public String getName() {
/* 743 */       return this.name;
/*     */     }
/*     */     
/*     */     public void setName(String name) {
/* 752 */       this.name = name;
/*     */     }
/*     */     
/*     */     public String getPropName() {
/* 761 */       return this.propName;
/*     */     }
/*     */     
/*     */     public void setPropName(String propName) {
/* 770 */       this.propName = propName;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\beanutils\locale\LocaleBeanUtils.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */