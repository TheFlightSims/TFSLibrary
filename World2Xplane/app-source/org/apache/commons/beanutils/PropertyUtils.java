/*     */ package org.apache.commons.beanutils;
/*     */ 
/*     */ import java.beans.PropertyDescriptor;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.collections.FastHashMap;
/*     */ 
/*     */ public class PropertyUtils {
/*     */   public static final char INDEXED_DELIM = '[';
/*     */   
/*     */   public static final char INDEXED_DELIM2 = ']';
/*     */   
/*     */   public static final char MAPPED_DELIM = '(';
/*     */   
/*     */   public static final char MAPPED_DELIM2 = ')';
/*     */   
/*     */   public static final char NESTED_DELIM = '.';
/*     */   
/* 119 */   private static int debug = 0;
/*     */   
/*     */   public static int getDebug() {
/* 127 */     return debug;
/*     */   }
/*     */   
/*     */   public static void setDebug(int newDebug) {
/* 136 */     debug = newDebug;
/*     */   }
/*     */   
/*     */   public static void clearDescriptors() {
/* 153 */     PropertyUtilsBean.getInstance().clearDescriptors();
/*     */   }
/*     */   
/*     */   public static void copyProperties(Object dest, Object orig) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
/* 183 */     PropertyUtilsBean.getInstance().copyProperties(dest, orig);
/*     */   }
/*     */   
/*     */   public static Map describe(Object bean) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
/* 209 */     return PropertyUtilsBean.getInstance().describe(bean);
/*     */   }
/*     */   
/*     */   public static Object getIndexedProperty(Object bean, String name) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
/* 241 */     return PropertyUtilsBean.getInstance().getIndexedProperty(bean, name);
/*     */   }
/*     */   
/*     */   public static Object getIndexedProperty(Object bean, String name, int index) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
/* 274 */     return PropertyUtilsBean.getInstance().getIndexedProperty(bean, name, index);
/*     */   }
/*     */   
/*     */   public static Object getMappedProperty(Object bean, String name) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
/* 301 */     return PropertyUtilsBean.getInstance().getMappedProperty(bean, name);
/*     */   }
/*     */   
/*     */   public static Object getMappedProperty(Object bean, String name, String key) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
/* 330 */     return PropertyUtilsBean.getInstance().getMappedProperty(bean, name, key);
/*     */   }
/*     */   
/*     */   public static FastHashMap getMappedPropertyDescriptors(Class beanClass) {
/* 347 */     return PropertyUtilsBean.getInstance().getMappedPropertyDescriptors(beanClass);
/*     */   }
/*     */   
/*     */   public static FastHashMap getMappedPropertyDescriptors(Object bean) {
/* 364 */     return PropertyUtilsBean.getInstance().getMappedPropertyDescriptors(bean);
/*     */   }
/*     */   
/*     */   public static Object getNestedProperty(Object bean, String name) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
/* 395 */     return PropertyUtilsBean.getInstance().getNestedProperty(bean, name);
/*     */   }
/*     */   
/*     */   public static Object getProperty(Object bean, String name) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
/* 426 */     return PropertyUtilsBean.getInstance().getProperty(bean, name);
/*     */   }
/*     */   
/*     */   public static PropertyDescriptor getPropertyDescriptor(Object bean, String name) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
/* 460 */     return PropertyUtilsBean.getInstance().getPropertyDescriptor(bean, name);
/*     */   }
/*     */   
/*     */   public static PropertyDescriptor[] getPropertyDescriptors(Class beanClass) {
/* 480 */     return PropertyUtilsBean.getInstance().getPropertyDescriptors(beanClass);
/*     */   }
/*     */   
/*     */   public static PropertyDescriptor[] getPropertyDescriptors(Object bean) {
/* 499 */     return PropertyUtilsBean.getInstance().getPropertyDescriptors(bean);
/*     */   }
/*     */   
/*     */   public static Class getPropertyEditorClass(Object bean, String name) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
/* 531 */     return PropertyUtilsBean.getInstance().getPropertyEditorClass(bean, name);
/*     */   }
/*     */   
/*     */   public static Class getPropertyType(Object bean, String name) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
/* 564 */     return PropertyUtilsBean.getInstance().getPropertyType(bean, name);
/*     */   }
/*     */   
/*     */   public static Method getReadMethod(PropertyDescriptor descriptor) {
/* 580 */     return PropertyUtilsBean.getInstance().getReadMethod(descriptor);
/*     */   }
/*     */   
/*     */   public static Object getSimpleProperty(Object bean, String name) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
/* 611 */     return PropertyUtilsBean.getInstance().getSimpleProperty(bean, name);
/*     */   }
/*     */   
/*     */   public static Method getWriteMethod(PropertyDescriptor descriptor) {
/* 628 */     return PropertyUtilsBean.getInstance().getWriteMethod(descriptor);
/*     */   }
/*     */   
/*     */   public static boolean isReadable(Object bean, String name) {
/* 652 */     return PropertyUtilsBean.getInstance().isReadable(bean, name);
/*     */   }
/*     */   
/*     */   public static boolean isWriteable(Object bean, String name) {
/* 675 */     return PropertyUtilsBean.getInstance().isWriteable(bean, name);
/*     */   }
/*     */   
/*     */   public static void setIndexedProperty(Object bean, String name, Object value) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
/* 708 */     PropertyUtilsBean.getInstance().setIndexedProperty(bean, name, value);
/*     */   }
/*     */   
/*     */   public static void setIndexedProperty(Object bean, String name, int index, Object value) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
/* 741 */     PropertyUtilsBean.getInstance().setIndexedProperty(bean, name, index, value);
/*     */   }
/*     */   
/*     */   public static void setMappedProperty(Object bean, String name, Object value) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
/* 769 */     PropertyUtilsBean.getInstance().setMappedProperty(bean, name, value);
/*     */   }
/*     */   
/*     */   public static void setMappedProperty(Object bean, String name, String key, Object value) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
/* 797 */     PropertyUtilsBean.getInstance().setMappedProperty(bean, name, key, value);
/*     */   }
/*     */   
/*     */   public static void setNestedProperty(Object bean, String name, Object value) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
/* 828 */     PropertyUtilsBean.getInstance().setNestedProperty(bean, name, value);
/*     */   }
/*     */   
/*     */   public static void setProperty(Object bean, String name, Object value) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
/* 858 */     PropertyUtilsBean.getInstance().setProperty(bean, name, value);
/*     */   }
/*     */   
/*     */   public static void setSimpleProperty(Object bean, String name, Object value) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
/* 890 */     PropertyUtilsBean.getInstance().setSimpleProperty(bean, name, value);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\beanutils\PropertyUtils.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */