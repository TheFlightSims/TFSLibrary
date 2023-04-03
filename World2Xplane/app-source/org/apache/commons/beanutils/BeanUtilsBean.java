/*      */ package org.apache.commons.beanutils;
/*      */ 
/*      */ import java.beans.IndexedPropertyDescriptor;
/*      */ import java.beans.PropertyDescriptor;
/*      */ import java.lang.reflect.Array;
/*      */ import java.lang.reflect.InvocationTargetException;
/*      */ import java.lang.reflect.Method;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collection;
/*      */ import java.util.HashMap;
/*      */ import java.util.Iterator;
/*      */ import java.util.Map;
/*      */ import org.apache.commons.beanutils.expression.Resolver;
/*      */ import org.apache.commons.logging.Log;
/*      */ import org.apache.commons.logging.LogFactory;
/*      */ 
/*      */ public class BeanUtilsBean {
/*   65 */   private static final ContextClassLoaderLocal BEANS_BY_CLASSLOADER = new ContextClassLoaderLocal() {
/*      */       protected Object initialValue() {
/*   68 */         return new BeanUtilsBean();
/*      */       }
/*      */     };
/*      */   
/*      */   public static BeanUtilsBean getInstance() {
/*   80 */     return (BeanUtilsBean)BEANS_BY_CLASSLOADER.get();
/*      */   }
/*      */   
/*      */   public static void setInstance(BeanUtilsBean newInstance) {
/*   91 */     BEANS_BY_CLASSLOADER.set(newInstance);
/*      */   }
/*      */   
/*   99 */   private Log log = LogFactory.getLog(BeanUtils.class);
/*      */   
/*      */   private ConvertUtilsBean convertUtilsBean;
/*      */   
/*      */   private PropertyUtilsBean propertyUtilsBean;
/*      */   
/*  108 */   private static final Method INIT_CAUSE_METHOD = getInitCauseMethod();
/*      */   
/*      */   public BeanUtilsBean() {
/*  117 */     this(new ConvertUtilsBean(), new PropertyUtilsBean());
/*      */   }
/*      */   
/*      */   public BeanUtilsBean(ConvertUtilsBean convertUtilsBean) {
/*  130 */     this(convertUtilsBean, new PropertyUtilsBean());
/*      */   }
/*      */   
/*      */   public BeanUtilsBean(ConvertUtilsBean convertUtilsBean, PropertyUtilsBean propertyUtilsBean) {
/*  145 */     this.convertUtilsBean = convertUtilsBean;
/*  146 */     this.propertyUtilsBean = propertyUtilsBean;
/*      */   }
/*      */   
/*      */   public Object cloneBean(Object bean) throws IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {
/*  177 */     if (this.log.isDebugEnabled())
/*  178 */       this.log.debug("Cloning bean: " + bean.getClass().getName()); 
/*  180 */     Object newBean = null;
/*  181 */     if (bean instanceof DynaBean) {
/*  182 */       newBean = ((DynaBean)bean).getDynaClass().newInstance();
/*      */     } else {
/*  184 */       newBean = bean.getClass().newInstance();
/*      */     } 
/*  186 */     getPropertyUtils().copyProperties(newBean, bean);
/*  187 */     return newBean;
/*      */   }
/*      */   
/*      */   public void copyProperties(Object dest, Object orig) throws IllegalAccessException, InvocationTargetException {
/*  238 */     if (dest == null)
/*  239 */       throw new IllegalArgumentException("No destination bean specified"); 
/*  242 */     if (orig == null)
/*  243 */       throw new IllegalArgumentException("No origin bean specified"); 
/*  245 */     if (this.log.isDebugEnabled())
/*  246 */       this.log.debug("BeanUtils.copyProperties(" + dest + ", " + orig + ")"); 
/*  251 */     if (orig instanceof DynaBean) {
/*  252 */       DynaProperty[] origDescriptors = ((DynaBean)orig).getDynaClass().getDynaProperties();
/*  254 */       for (int i = 0; i < origDescriptors.length; i++) {
/*  255 */         String name = origDescriptors[i].getName();
/*  258 */         if (getPropertyUtils().isReadable(orig, name) && getPropertyUtils().isWriteable(dest, name)) {
/*  260 */           Object value = ((DynaBean)orig).get(name);
/*  261 */           copyProperty(dest, name, value);
/*      */         } 
/*      */       } 
/*  264 */     } else if (orig instanceof Map) {
/*  265 */       Iterator entries = ((Map)orig).entrySet().iterator();
/*  266 */       while (entries.hasNext()) {
/*  267 */         Map.Entry entry = entries.next();
/*  268 */         String name = (String)entry.getKey();
/*  269 */         if (getPropertyUtils().isWriteable(dest, name))
/*  270 */           copyProperty(dest, name, entry.getValue()); 
/*      */       } 
/*      */     } else {
/*  274 */       PropertyDescriptor[] origDescriptors = getPropertyUtils().getPropertyDescriptors(orig);
/*  276 */       for (int i = 0; i < origDescriptors.length; i++) {
/*  277 */         String name = origDescriptors[i].getName();
/*  278 */         if (!"class".equals(name))
/*  281 */           if (getPropertyUtils().isReadable(orig, name) && getPropertyUtils().isWriteable(dest, name))
/*      */             try {
/*  284 */               Object value = getPropertyUtils().getSimpleProperty(orig, name);
/*  286 */               copyProperty(dest, name, value);
/*  287 */             } catch (NoSuchMethodException e) {}  
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public void copyProperty(Object bean, String name, Object value) throws IllegalAccessException, InvocationTargetException {
/*  332 */     if (this.log.isTraceEnabled()) {
/*  333 */       StringBuffer sb = new StringBuffer("  copyProperty(");
/*  334 */       sb.append(bean);
/*  335 */       sb.append(", ");
/*  336 */       sb.append(name);
/*  337 */       sb.append(", ");
/*  338 */       if (value == null) {
/*  339 */         sb.append("<NULL>");
/*  340 */       } else if (value instanceof String) {
/*  341 */         sb.append((String)value);
/*  342 */       } else if (value instanceof String[]) {
/*  343 */         String[] values = (String[])value;
/*  344 */         sb.append('[');
/*  345 */         for (int i = 0; i < values.length; i++) {
/*  346 */           if (i > 0)
/*  347 */             sb.append(','); 
/*  349 */           sb.append(values[i]);
/*      */         } 
/*  351 */         sb.append(']');
/*      */       } else {
/*  353 */         sb.append(value.toString());
/*      */       } 
/*  355 */       sb.append(')');
/*  356 */       this.log.trace(sb.toString());
/*      */     } 
/*  360 */     Object target = bean;
/*  361 */     Resolver resolver = getPropertyUtils().getResolver();
/*  362 */     while (resolver.hasNested(name)) {
/*      */       try {
/*  364 */         target = getPropertyUtils().getProperty(target, resolver.next(name));
/*  365 */         name = resolver.remove(name);
/*  366 */       } catch (NoSuchMethodException e) {
/*      */         return;
/*      */       } 
/*      */     } 
/*  370 */     if (this.log.isTraceEnabled()) {
/*  371 */       this.log.trace("    Target bean = " + target);
/*  372 */       this.log.trace("    Target name = " + name);
/*      */     } 
/*  376 */     String propName = resolver.getProperty(name);
/*  377 */     Class type = null;
/*  378 */     int index = resolver.getIndex(name);
/*  379 */     String key = resolver.getKey(name);
/*  382 */     if (target instanceof DynaBean) {
/*  383 */       DynaClass dynaClass = ((DynaBean)target).getDynaClass();
/*  384 */       DynaProperty dynaProperty = dynaClass.getDynaProperty(propName);
/*  385 */       if (dynaProperty == null)
/*      */         return; 
/*  388 */       type = dynaProperty.getType();
/*      */     } else {
/*  390 */       PropertyDescriptor descriptor = null;
/*      */       try {
/*  392 */         descriptor = getPropertyUtils().getPropertyDescriptor(target, name);
/*  394 */         if (descriptor == null)
/*      */           return; 
/*  397 */       } catch (NoSuchMethodException e) {
/*      */         return;
/*      */       } 
/*  400 */       type = descriptor.getPropertyType();
/*  401 */       if (type == null) {
/*  403 */         if (this.log.isTraceEnabled())
/*  404 */           this.log.trace("    target type for property '" + propName + "' is null, so skipping ths setter"); 
/*      */         return;
/*      */       } 
/*      */     } 
/*  410 */     if (this.log.isTraceEnabled())
/*  411 */       this.log.trace("    target propName=" + propName + ", type=" + type + ", index=" + index + ", key=" + key); 
/*  416 */     if (index >= 0) {
/*  417 */       value = convert(value, type.getComponentType());
/*      */       try {
/*  419 */         getPropertyUtils().setIndexedProperty(target, propName, index, value);
/*  421 */       } catch (NoSuchMethodException e) {
/*  422 */         throw new InvocationTargetException(e, "Cannot set " + propName);
/*      */       } 
/*  425 */     } else if (key != null) {
/*      */       try {
/*  430 */         getPropertyUtils().setMappedProperty(target, propName, key, value);
/*  432 */       } catch (NoSuchMethodException e) {
/*  433 */         throw new InvocationTargetException(e, "Cannot set " + propName);
/*      */       } 
/*      */     } else {
/*  437 */       value = convert(value, type);
/*      */       try {
/*  439 */         getPropertyUtils().setSimpleProperty(target, propName, value);
/*  440 */       } catch (NoSuchMethodException e) {
/*  441 */         throw new InvocationTargetException(e, "Cannot set " + propName);
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public Map describe(Object bean) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
/*  490 */     if (bean == null)
/*  492 */       return new HashMap(); 
/*  495 */     if (this.log.isDebugEnabled())
/*  496 */       this.log.debug("Describing bean: " + bean.getClass().getName()); 
/*  499 */     Map description = new HashMap();
/*  500 */     if (bean instanceof DynaBean) {
/*  501 */       DynaProperty[] descriptors = ((DynaBean)bean).getDynaClass().getDynaProperties();
/*  503 */       for (int i = 0; i < descriptors.length; i++) {
/*  504 */         String name = descriptors[i].getName();
/*  505 */         description.put(name, getProperty(bean, name));
/*      */       } 
/*      */     } else {
/*  508 */       PropertyDescriptor[] descriptors = getPropertyUtils().getPropertyDescriptors(bean);
/*  510 */       Class clazz = bean.getClass();
/*  511 */       for (int i = 0; i < descriptors.length; i++) {
/*  512 */         String name = descriptors[i].getName();
/*  513 */         if (getPropertyUtils().getReadMethod(clazz, descriptors[i]) != null)
/*  514 */           description.put(name, getProperty(bean, name)); 
/*      */       } 
/*      */     } 
/*  518 */     return description;
/*      */   }
/*      */   
/*      */   public String[] getArrayProperty(Object bean, String name) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
/*  542 */     Object value = getPropertyUtils().getProperty(bean, name);
/*  543 */     if (value == null)
/*  544 */       return null; 
/*  545 */     if (value instanceof Collection) {
/*  546 */       ArrayList values = new ArrayList();
/*  547 */       Iterator items = ((Collection)value).iterator();
/*  548 */       while (items.hasNext()) {
/*  549 */         Object item = items.next();
/*  550 */         if (item == null) {
/*  551 */           values.add((String)null);
/*      */           continue;
/*      */         } 
/*  554 */         values.add(getConvertUtils().convert(item));
/*      */       } 
/*  557 */       return values.<String>toArray(new String[values.size()]);
/*      */     } 
/*  558 */     if (value.getClass().isArray()) {
/*  559 */       int n = Array.getLength(value);
/*  560 */       String[] arrayOfString = new String[n];
/*  561 */       for (int i = 0; i < n; i++) {
/*  562 */         Object item = Array.get(value, i);
/*  563 */         if (item == null) {
/*  564 */           arrayOfString[i] = null;
/*      */         } else {
/*  567 */           arrayOfString[i] = getConvertUtils().convert(item);
/*      */         } 
/*      */       } 
/*  570 */       return arrayOfString;
/*      */     } 
/*  572 */     String[] results = new String[1];
/*  573 */     results[0] = getConvertUtils().convert(value);
/*  574 */     return results;
/*      */   }
/*      */   
/*      */   public String getIndexedProperty(Object bean, String name) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
/*  603 */     Object value = getPropertyUtils().getIndexedProperty(bean, name);
/*  604 */     return getConvertUtils().convert(value);
/*      */   }
/*      */   
/*      */   public String getIndexedProperty(Object bean, String name, int index) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
/*  631 */     Object value = getPropertyUtils().getIndexedProperty(bean, name, index);
/*  632 */     return getConvertUtils().convert(value);
/*      */   }
/*      */   
/*      */   public String getMappedProperty(Object bean, String name) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
/*  660 */     Object value = getPropertyUtils().getMappedProperty(bean, name);
/*  661 */     return getConvertUtils().convert(value);
/*      */   }
/*      */   
/*      */   public String getMappedProperty(Object bean, String name, String key) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
/*  688 */     Object value = getPropertyUtils().getMappedProperty(bean, name, key);
/*  689 */     return getConvertUtils().convert(value);
/*      */   }
/*      */   
/*      */   public String getNestedProperty(Object bean, String name) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
/*  715 */     Object value = getPropertyUtils().getNestedProperty(bean, name);
/*  716 */     return getConvertUtils().convert(value);
/*      */   }
/*      */   
/*      */   public String getProperty(Object bean, String name) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
/*  741 */     return getNestedProperty(bean, name);
/*      */   }
/*      */   
/*      */   public String getSimpleProperty(Object bean, String name) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
/*  765 */     Object value = getPropertyUtils().getSimpleProperty(bean, name);
/*  766 */     return getConvertUtils().convert(value);
/*      */   }
/*      */   
/*      */   public void populate(Object bean, Map properties) throws IllegalAccessException, InvocationTargetException {
/*  810 */     if (bean == null || properties == null)
/*      */       return; 
/*  813 */     if (this.log.isDebugEnabled())
/*  814 */       this.log.debug("BeanUtils.populate(" + bean + ", " + properties + ")"); 
/*  819 */     Iterator entries = properties.entrySet().iterator();
/*  820 */     while (entries.hasNext()) {
/*  823 */       Map.Entry entry = entries.next();
/*  824 */       String name = (String)entry.getKey();
/*  825 */       if (name == null)
/*      */         continue; 
/*  830 */       setProperty(bean, name, entry.getValue());
/*      */     } 
/*      */   }
/*      */   
/*      */   public void setProperty(Object bean, String name, Object value) throws IllegalAccessException, InvocationTargetException {
/*  871 */     if (this.log.isTraceEnabled()) {
/*  872 */       StringBuffer sb = new StringBuffer("  setProperty(");
/*  873 */       sb.append(bean);
/*  874 */       sb.append(", ");
/*  875 */       sb.append(name);
/*  876 */       sb.append(", ");
/*  877 */       if (value == null) {
/*  878 */         sb.append("<NULL>");
/*  879 */       } else if (value instanceof String) {
/*  880 */         sb.append((String)value);
/*  881 */       } else if (value instanceof String[]) {
/*  882 */         String[] values = (String[])value;
/*  883 */         sb.append('[');
/*  884 */         for (int i = 0; i < values.length; i++) {
/*  885 */           if (i > 0)
/*  886 */             sb.append(','); 
/*  888 */           sb.append(values[i]);
/*      */         } 
/*  890 */         sb.append(']');
/*      */       } else {
/*  892 */         sb.append(value.toString());
/*      */       } 
/*  894 */       sb.append(')');
/*  895 */       this.log.trace(sb.toString());
/*      */     } 
/*  899 */     Object target = bean;
/*  900 */     Resolver resolver = getPropertyUtils().getResolver();
/*  901 */     while (resolver.hasNested(name)) {
/*      */       try {
/*  903 */         target = getPropertyUtils().getProperty(target, resolver.next(name));
/*  904 */         name = resolver.remove(name);
/*  905 */       } catch (NoSuchMethodException e) {
/*      */         return;
/*      */       } 
/*      */     } 
/*  909 */     if (this.log.isTraceEnabled()) {
/*  910 */       this.log.trace("    Target bean = " + target);
/*  911 */       this.log.trace("    Target name = " + name);
/*      */     } 
/*  915 */     String propName = resolver.getProperty(name);
/*  916 */     Class type = null;
/*  917 */     int index = resolver.getIndex(name);
/*  918 */     String key = resolver.getKey(name);
/*  921 */     if (target instanceof DynaBean) {
/*  922 */       DynaClass dynaClass = ((DynaBean)target).getDynaClass();
/*  923 */       DynaProperty dynaProperty = dynaClass.getDynaProperty(propName);
/*  924 */       if (dynaProperty == null)
/*      */         return; 
/*  927 */       type = dynaProperty.getType();
/*  928 */     } else if (target instanceof Map) {
/*  929 */       type = Object.class;
/*  930 */     } else if (target != null && target.getClass().isArray() && index >= 0) {
/*  931 */       type = (Class)Array.get(target, index).getClass();
/*      */     } else {
/*  933 */       PropertyDescriptor descriptor = null;
/*      */       try {
/*  935 */         descriptor = getPropertyUtils().getPropertyDescriptor(target, name);
/*  937 */         if (descriptor == null)
/*      */           return; 
/*  940 */       } catch (NoSuchMethodException e) {
/*      */         return;
/*      */       } 
/*  943 */       if (descriptor instanceof MappedPropertyDescriptor) {
/*  944 */         if (((MappedPropertyDescriptor)descriptor).getMappedWriteMethod() == null) {
/*  945 */           if (this.log.isDebugEnabled())
/*  946 */             this.log.debug("Skipping read-only property"); 
/*      */           return;
/*      */         } 
/*  950 */         type = ((MappedPropertyDescriptor)descriptor).getMappedPropertyType();
/*  952 */       } else if (index >= 0 && descriptor instanceof IndexedPropertyDescriptor) {
/*  953 */         if (((IndexedPropertyDescriptor)descriptor).getIndexedWriteMethod() == null) {
/*  954 */           if (this.log.isDebugEnabled())
/*  955 */             this.log.debug("Skipping read-only property"); 
/*      */           return;
/*      */         } 
/*  959 */         type = (Class)((IndexedPropertyDescriptor)descriptor).getIndexedPropertyType();
/*  961 */       } else if (key != null) {
/*  962 */         if (descriptor.getReadMethod() == null) {
/*  963 */           if (this.log.isDebugEnabled())
/*  964 */             this.log.debug("Skipping read-only property"); 
/*      */           return;
/*      */         } 
/*  968 */         type = (value == null) ? Object.class : (Class)value.getClass();
/*      */       } else {
/*  970 */         if (descriptor.getWriteMethod() == null) {
/*  971 */           if (this.log.isDebugEnabled())
/*  972 */             this.log.debug("Skipping read-only property"); 
/*      */           return;
/*      */         } 
/*  976 */         type = (Class)descriptor.getPropertyType();
/*      */       } 
/*      */     } 
/*  981 */     Object newValue = null;
/*  982 */     if (type.isArray() && index < 0) {
/*  983 */       if (value == null) {
/*  984 */         String[] values = new String[1];
/*  985 */         values[0] = null;
/*  986 */         newValue = getConvertUtils().convert(values, type);
/*  987 */       } else if (value instanceof String) {
/*  988 */         newValue = getConvertUtils().convert(value, type);
/*  989 */       } else if (value instanceof String[]) {
/*  990 */         newValue = getConvertUtils().convert((String[])value, type);
/*      */       } else {
/*  992 */         newValue = convert(value, type);
/*      */       } 
/*  994 */     } else if (type.isArray()) {
/*  995 */       if (value instanceof String || value == null) {
/*  996 */         newValue = getConvertUtils().convert((String)value, type.getComponentType());
/*  998 */       } else if (value instanceof String[]) {
/*  999 */         newValue = getConvertUtils().convert(((String[])value)[0], type.getComponentType());
/*      */       } else {
/* 1002 */         newValue = convert(value, type.getComponentType());
/*      */       } 
/* 1005 */     } else if (value instanceof String) {
/* 1006 */       newValue = getConvertUtils().convert((String)value, type);
/* 1007 */     } else if (value instanceof String[]) {
/* 1008 */       newValue = getConvertUtils().convert(((String[])value)[0], type);
/*      */     } else {
/* 1011 */       newValue = convert(value, type);
/*      */     } 
/*      */     try {
/* 1017 */       getPropertyUtils().setProperty(target, name, newValue);
/* 1018 */     } catch (NoSuchMethodException e) {
/* 1019 */       throw new InvocationTargetException(e, "Cannot set " + propName);
/*      */     } 
/*      */   }
/*      */   
/*      */   public ConvertUtilsBean getConvertUtils() {
/* 1031 */     return this.convertUtilsBean;
/*      */   }
/*      */   
/*      */   public PropertyUtilsBean getPropertyUtils() {
/* 1040 */     return this.propertyUtilsBean;
/*      */   }
/*      */   
/*      */   public boolean initCause(Throwable throwable, Throwable cause) {
/* 1052 */     if (INIT_CAUSE_METHOD != null && cause != null)
/*      */       try {
/* 1054 */         INIT_CAUSE_METHOD.invoke(throwable, new Object[] { cause });
/* 1055 */         return true;
/* 1056 */       } catch (Throwable e) {
/* 1057 */         return false;
/*      */       }  
/* 1060 */     return false;
/*      */   }
/*      */   
/*      */   protected Object convert(Object value, Class type) {
/* 1075 */     Converter converter = getConvertUtils().lookup(type);
/* 1076 */     if (converter != null) {
/* 1077 */       this.log.trace("        USING CONVERTER " + converter);
/* 1078 */       return converter.convert(type, value);
/*      */     } 
/* 1080 */     return value;
/*      */   }
/*      */   
/*      */   private static Method getInitCauseMethod() {
/*      */     try {
/* 1095 */       Class[] paramsClasses = { Throwable.class };
/* 1096 */       return Throwable.class.getMethod("initCause", paramsClasses);
/* 1097 */     } catch (NoSuchMethodException e) {
/* 1098 */       Log log = LogFactory.getLog(BeanUtils.class);
/* 1099 */       if (log.isWarnEnabled())
/* 1100 */         log.warn("Throwable does not have initCause() method in JDK 1.3"); 
/* 1102 */       return null;
/* 1103 */     } catch (Throwable e) {
/* 1104 */       Log log = LogFactory.getLog(BeanUtils.class);
/* 1105 */       if (log.isWarnEnabled())
/* 1106 */         log.warn("Error getting the Throwable initCause() method", e); 
/* 1108 */       return null;
/*      */     } 
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\beanutils\BeanUtilsBean.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */