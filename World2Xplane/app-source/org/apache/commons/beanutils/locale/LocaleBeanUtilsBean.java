/*      */ package org.apache.commons.beanutils.locale;
/*      */ 
/*      */ import java.beans.IndexedPropertyDescriptor;
/*      */ import java.beans.PropertyDescriptor;
/*      */ import java.lang.reflect.InvocationTargetException;
/*      */ import java.util.Locale;
/*      */ import org.apache.commons.beanutils.BeanUtilsBean;
/*      */ import org.apache.commons.beanutils.ContextClassLoaderLocal;
/*      */ import org.apache.commons.beanutils.ConvertUtils;
/*      */ import org.apache.commons.beanutils.ConvertUtilsBean;
/*      */ import org.apache.commons.beanutils.DynaBean;
/*      */ import org.apache.commons.beanutils.DynaClass;
/*      */ import org.apache.commons.beanutils.DynaProperty;
/*      */ import org.apache.commons.beanutils.MappedPropertyDescriptor;
/*      */ import org.apache.commons.beanutils.PropertyUtilsBean;
/*      */ import org.apache.commons.beanutils.expression.Resolver;
/*      */ import org.apache.commons.logging.Log;
/*      */ import org.apache.commons.logging.LogFactory;
/*      */ 
/*      */ public class LocaleBeanUtilsBean extends BeanUtilsBean {
/*   59 */   private static final ContextClassLoaderLocal LOCALE_BEANS_BY_CLASSLOADER = new ContextClassLoaderLocal() {
/*      */       protected Object initialValue() {
/*   62 */         return new LocaleBeanUtilsBean();
/*      */       }
/*      */     };
/*      */   
/*      */   public static LocaleBeanUtilsBean getLocaleBeanUtilsInstance() {
/*   72 */     return (LocaleBeanUtilsBean)LOCALE_BEANS_BY_CLASSLOADER.get();
/*      */   }
/*      */   
/*      */   public static void setInstance(LocaleBeanUtilsBean newInstance) {
/*   83 */     LOCALE_BEANS_BY_CLASSLOADER.set(newInstance);
/*      */   }
/*      */   
/*   87 */   private Log log = LogFactory.getLog(LocaleBeanUtilsBean.class);
/*      */   
/*      */   private LocaleConvertUtilsBean localeConvertUtils;
/*      */   
/*      */   public LocaleBeanUtilsBean() {
/*   98 */     this.localeConvertUtils = new LocaleConvertUtilsBean();
/*      */   }
/*      */   
/*      */   public LocaleBeanUtilsBean(LocaleConvertUtilsBean localeConvertUtils, ConvertUtilsBean convertUtilsBean, PropertyUtilsBean propertyUtilsBean) {
/*  113 */     super(convertUtilsBean, propertyUtilsBean);
/*  114 */     this.localeConvertUtils = localeConvertUtils;
/*      */   }
/*      */   
/*      */   public LocaleBeanUtilsBean(LocaleConvertUtilsBean localeConvertUtils) {
/*  124 */     this.localeConvertUtils = localeConvertUtils;
/*      */   }
/*      */   
/*      */   public LocaleConvertUtilsBean getLocaleConvertUtils() {
/*  135 */     return this.localeConvertUtils;
/*      */   }
/*      */   
/*      */   public Locale getDefaultLocale() {
/*  144 */     return getLocaleConvertUtils().getDefaultLocale();
/*      */   }
/*      */   
/*      */   public void setDefaultLocale(Locale locale) {
/*  155 */     getLocaleConvertUtils().setDefaultLocale(locale);
/*      */   }
/*      */   
/*      */   public boolean getApplyLocalized() {
/*  167 */     return getLocaleConvertUtils().getApplyLocalized();
/*      */   }
/*      */   
/*      */   public void setApplyLocalized(boolean newApplyLocalized) {
/*  179 */     getLocaleConvertUtils().setApplyLocalized(newApplyLocalized);
/*      */   }
/*      */   
/*      */   public String getIndexedProperty(Object bean, String name, String pattern) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
/*  214 */     Object value = getPropertyUtils().getIndexedProperty(bean, name);
/*  215 */     return getLocaleConvertUtils().convert(value, pattern);
/*      */   }
/*      */   
/*      */   public String getIndexedProperty(Object bean, String name) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
/*  245 */     return getIndexedProperty(bean, name, (String)null);
/*      */   }
/*      */   
/*      */   public String getIndexedProperty(Object bean, String name, int index, String pattern) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
/*  272 */     Object value = getPropertyUtils().getIndexedProperty(bean, name, index);
/*  273 */     return getLocaleConvertUtils().convert(value, pattern);
/*      */   }
/*      */   
/*      */   public String getIndexedProperty(Object bean, String name, int index) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
/*  299 */     return getIndexedProperty(bean, name, index, null);
/*      */   }
/*      */   
/*      */   public String getSimpleProperty(Object bean, String name, String pattern) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
/*  323 */     Object value = getPropertyUtils().getSimpleProperty(bean, name);
/*  324 */     return getLocaleConvertUtils().convert(value, pattern);
/*      */   }
/*      */   
/*      */   public String getSimpleProperty(Object bean, String name) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
/*  347 */     return getSimpleProperty(bean, name, null);
/*      */   }
/*      */   
/*      */   public String getMappedProperty(Object bean, String name, String key, String pattern) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
/*  379 */     Object value = getPropertyUtils().getMappedProperty(bean, name, key);
/*  380 */     return getLocaleConvertUtils().convert(value, pattern);
/*      */   }
/*      */   
/*      */   public String getMappedProperty(Object bean, String name, String key) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
/*  406 */     return getMappedProperty(bean, name, key, null);
/*      */   }
/*      */   
/*      */   public String getMappedPropertyLocale(Object bean, String name, String pattern) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
/*  440 */     Object value = getPropertyUtils().getMappedProperty(bean, name);
/*  441 */     return getLocaleConvertUtils().convert(value, pattern);
/*      */   }
/*      */   
/*      */   public String getMappedProperty(Object bean, String name) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
/*  472 */     return getMappedPropertyLocale(bean, name, null);
/*      */   }
/*      */   
/*      */   public String getNestedProperty(Object bean, String name, String pattern) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
/*  503 */     Object value = getPropertyUtils().getNestedProperty(bean, name);
/*  504 */     return getLocaleConvertUtils().convert(value, pattern);
/*      */   }
/*      */   
/*      */   public String getNestedProperty(Object bean, String name) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
/*  531 */     return getNestedProperty(bean, name, null);
/*      */   }
/*      */   
/*      */   public String getProperty(Object bean, String name, String pattern) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
/*  558 */     return getNestedProperty(bean, name, pattern);
/*      */   }
/*      */   
/*      */   public String getProperty(Object bean, String name) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
/*  585 */     return getNestedProperty(bean, name);
/*      */   }
/*      */   
/*      */   public void setProperty(Object bean, String name, Object value) throws IllegalAccessException, InvocationTargetException {
/*  607 */     setProperty(bean, name, value, null);
/*      */   }
/*      */   
/*      */   public void setProperty(Object bean, String name, Object value, String pattern) throws IllegalAccessException, InvocationTargetException {
/*  635 */     if (this.log.isTraceEnabled()) {
/*  636 */       StringBuffer sb = new StringBuffer("  setProperty(");
/*  637 */       sb.append(bean);
/*  638 */       sb.append(", ");
/*  639 */       sb.append(name);
/*  640 */       sb.append(", ");
/*  641 */       if (value == null) {
/*  642 */         sb.append("<NULL>");
/*  644 */       } else if (value instanceof String) {
/*  645 */         sb.append((String)value);
/*  647 */       } else if (value instanceof String[]) {
/*  648 */         String[] values = (String[])value;
/*  649 */         sb.append('[');
/*  650 */         for (int i = 0; i < values.length; i++) {
/*  651 */           if (i > 0)
/*  652 */             sb.append(','); 
/*  654 */           sb.append(values[i]);
/*      */         } 
/*  656 */         sb.append(']');
/*      */       } else {
/*  659 */         sb.append(value.toString());
/*      */       } 
/*  661 */       sb.append(')');
/*  662 */       this.log.trace(sb.toString());
/*      */     } 
/*  666 */     Object target = bean;
/*  667 */     Resolver resolver = getPropertyUtils().getResolver();
/*  668 */     while (resolver.hasNested(name)) {
/*      */       try {
/*  670 */         target = getPropertyUtils().getProperty(target, resolver.next(name));
/*  671 */         name = resolver.remove(name);
/*  672 */       } catch (NoSuchMethodException e) {
/*      */         return;
/*      */       } 
/*      */     } 
/*  676 */     if (this.log.isTraceEnabled()) {
/*  677 */       this.log.trace("    Target bean = " + target);
/*  678 */       this.log.trace("    Target name = " + name);
/*      */     } 
/*  682 */     String propName = resolver.getProperty(name);
/*  683 */     int index = resolver.getIndex(name);
/*  684 */     String key = resolver.getKey(name);
/*  686 */     Class type = definePropertyType(target, name, propName);
/*  687 */     if (type != null) {
/*  688 */       Object newValue = convert(type, index, value, pattern);
/*  689 */       invokeSetter(target, propName, key, index, newValue);
/*      */     } 
/*      */   }
/*      */   
/*      */   protected Class definePropertyType(Object target, String name, String propName) throws IllegalAccessException, InvocationTargetException {
/*  709 */     Class type = null;
/*  711 */     if (target instanceof DynaBean) {
/*  712 */       DynaClass dynaClass = ((DynaBean)target).getDynaClass();
/*  713 */       DynaProperty dynaProperty = dynaClass.getDynaProperty(propName);
/*  714 */       if (dynaProperty == null)
/*  715 */         return null; 
/*  717 */       type = dynaProperty.getType();
/*      */     } else {
/*  720 */       PropertyDescriptor descriptor = null;
/*      */       try {
/*  722 */         descriptor = getPropertyUtils().getPropertyDescriptor(target, name);
/*  724 */         if (descriptor == null)
/*  725 */           return null; 
/*  728 */       } catch (NoSuchMethodException e) {
/*  729 */         return null;
/*      */       } 
/*  731 */       if (descriptor instanceof MappedPropertyDescriptor) {
/*  732 */         type = ((MappedPropertyDescriptor)descriptor).getMappedPropertyType();
/*  735 */       } else if (descriptor instanceof IndexedPropertyDescriptor) {
/*  736 */         type = ((IndexedPropertyDescriptor)descriptor).getIndexedPropertyType();
/*      */       } else {
/*  740 */         type = descriptor.getPropertyType();
/*      */       } 
/*      */     } 
/*  743 */     return type;
/*      */   }
/*      */   
/*      */   protected Object convert(Class type, int index, Object value, String pattern) {
/*  758 */     if (this.log.isTraceEnabled())
/*  759 */       this.log.trace("Converting value '" + value + "' to type:" + type); 
/*  762 */     Object newValue = null;
/*  764 */     if (type.isArray() && index < 0) {
/*  765 */       if (value instanceof String) {
/*  766 */         String[] values = new String[1];
/*  767 */         values[0] = (String)value;
/*  768 */         newValue = getLocaleConvertUtils().convert(values, type, pattern);
/*  770 */       } else if (value instanceof String[]) {
/*  771 */         newValue = getLocaleConvertUtils().convert((String[])value, type, pattern);
/*      */       } else {
/*  774 */         newValue = value;
/*      */       } 
/*  777 */     } else if (type.isArray()) {
/*  778 */       if (value instanceof String) {
/*  779 */         newValue = getLocaleConvertUtils().convert((String)value, type.getComponentType(), pattern);
/*  782 */       } else if (value instanceof String[]) {
/*  783 */         newValue = getLocaleConvertUtils().convert(((String[])value)[0], type.getComponentType(), pattern);
/*      */       } else {
/*  787 */         newValue = value;
/*      */       } 
/*  791 */     } else if (value instanceof String) {
/*  792 */       newValue = getLocaleConvertUtils().convert((String)value, type, pattern);
/*  794 */     } else if (value instanceof String[]) {
/*  795 */       newValue = getLocaleConvertUtils().convert(((String[])value)[0], type, pattern);
/*      */     } else {
/*  799 */       newValue = value;
/*      */     } 
/*  802 */     return newValue;
/*      */   }
/*      */   
/*      */   protected Object convert(Class type, int index, Object value) {
/*  815 */     Object newValue = null;
/*  817 */     if (type.isArray() && index < 0) {
/*  818 */       if (value instanceof String) {
/*  819 */         String[] values = new String[1];
/*  820 */         values[0] = (String)value;
/*  821 */         newValue = ConvertUtils.convert(values, type);
/*  823 */       } else if (value instanceof String[]) {
/*  824 */         newValue = ConvertUtils.convert((String[])value, type);
/*      */       } else {
/*  827 */         newValue = value;
/*      */       } 
/*  830 */     } else if (type.isArray()) {
/*  831 */       if (value instanceof String) {
/*  832 */         newValue = ConvertUtils.convert((String)value, type.getComponentType());
/*  835 */       } else if (value instanceof String[]) {
/*  836 */         newValue = ConvertUtils.convert(((String[])value)[0], type.getComponentType());
/*      */       } else {
/*  840 */         newValue = value;
/*      */       } 
/*  844 */     } else if (value instanceof String) {
/*  845 */       newValue = ConvertUtils.convert((String)value, type);
/*  847 */     } else if (value instanceof String[]) {
/*  848 */       newValue = ConvertUtils.convert(((String[])value)[0], type);
/*      */     } else {
/*  852 */       newValue = value;
/*      */     } 
/*  855 */     return newValue;
/*      */   }
/*      */   
/*      */   protected void invokeSetter(Object target, String propName, String key, int index, Object newValue) throws IllegalAccessException, InvocationTargetException {
/*      */     try {
/*  876 */       if (index >= 0) {
/*  877 */         getPropertyUtils().setIndexedProperty(target, propName, index, newValue);
/*  880 */       } else if (key != null) {
/*  881 */         getPropertyUtils().setMappedProperty(target, propName, key, newValue);
/*      */       } else {
/*  885 */         getPropertyUtils().setProperty(target, propName, newValue);
/*      */       } 
/*  888 */     } catch (NoSuchMethodException e) {
/*  889 */       throw new InvocationTargetException(e, "Cannot set " + propName);
/*      */     } 
/*      */   }
/*      */   
/*      */   protected Descriptor calculate(Object bean, String name) throws IllegalAccessException, InvocationTargetException {
/*  913 */     Object target = bean;
/*  914 */     Resolver resolver = getPropertyUtils().getResolver();
/*  915 */     while (resolver.hasNested(name)) {
/*      */       try {
/*  917 */         target = getPropertyUtils().getProperty(target, resolver.next(name));
/*  918 */         name = resolver.remove(name);
/*  919 */       } catch (NoSuchMethodException e) {
/*  920 */         return null;
/*      */       } 
/*      */     } 
/*  923 */     if (this.log.isTraceEnabled()) {
/*  924 */       this.log.trace("    Target bean = " + target);
/*  925 */       this.log.trace("    Target name = " + name);
/*      */     } 
/*  929 */     String propName = resolver.getProperty(name);
/*  930 */     int index = resolver.getIndex(name);
/*  931 */     String key = resolver.getKey(name);
/*  933 */     return new Descriptor(this, target, name, propName, key, index);
/*      */   }
/*      */   
/*      */   protected class Descriptor {
/*      */     private int index;
/*      */     
/*      */     private String name;
/*      */     
/*      */     private String propName;
/*      */     
/*      */     private String key;
/*      */     
/*      */     private Object target;
/*      */     
/*      */     private final LocaleBeanUtilsBean this$0;
/*      */     
/*      */     public Descriptor(LocaleBeanUtilsBean this$0, Object target, String name, String propName, String key, int index) {
/*  958 */       this.this$0 = this$0;
/*      */       this.index = -1;
/*  960 */       setTarget(target);
/*  961 */       setName(name);
/*  962 */       setPropName(propName);
/*  963 */       setKey(key);
/*  964 */       setIndex(index);
/*      */     }
/*      */     
/*      */     public Object getTarget() {
/*  973 */       return this.target;
/*      */     }
/*      */     
/*      */     public void setTarget(Object target) {
/*  982 */       this.target = target;
/*      */     }
/*      */     
/*      */     public String getKey() {
/*  991 */       return this.key;
/*      */     }
/*      */     
/*      */     public void setKey(String key) {
/* 1000 */       this.key = key;
/*      */     }
/*      */     
/*      */     public int getIndex() {
/* 1009 */       return this.index;
/*      */     }
/*      */     
/*      */     public void setIndex(int index) {
/* 1018 */       this.index = index;
/*      */     }
/*      */     
/*      */     public String getName() {
/* 1027 */       return this.name;
/*      */     }
/*      */     
/*      */     public void setName(String name) {
/* 1036 */       this.name = name;
/*      */     }
/*      */     
/*      */     public String getPropName() {
/* 1045 */       return this.propName;
/*      */     }
/*      */     
/*      */     public void setPropName(String propName) {
/* 1054 */       this.propName = propName;
/*      */     }
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\beanutils\locale\LocaleBeanUtilsBean.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */