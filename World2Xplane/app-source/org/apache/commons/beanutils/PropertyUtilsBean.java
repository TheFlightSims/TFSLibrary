/*      */ package org.apache.commons.beanutils;
/*      */ 
/*      */ import java.beans.BeanInfo;
/*      */ import java.beans.IndexedPropertyDescriptor;
/*      */ import java.beans.IntrospectionException;
/*      */ import java.beans.Introspector;
/*      */ import java.beans.PropertyDescriptor;
/*      */ import java.lang.reflect.Array;
/*      */ import java.lang.reflect.InvocationTargetException;
/*      */ import java.lang.reflect.Method;
/*      */ import java.util.HashMap;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import org.apache.commons.beanutils.expression.DefaultResolver;
/*      */ import org.apache.commons.beanutils.expression.Resolver;
/*      */ import org.apache.commons.collections.FastHashMap;
/*      */ import org.apache.commons.logging.Log;
/*      */ import org.apache.commons.logging.LogFactory;
/*      */ 
/*      */ public class PropertyUtilsBean {
/*  105 */   private Resolver resolver = (Resolver)new DefaultResolver();
/*      */   
/*      */   protected static PropertyUtilsBean getInstance() {
/*  114 */     return BeanUtilsBean.getInstance().getPropertyUtils();
/*      */   }
/*      */   
/*  123 */   private WeakFastHashMap descriptorsCache = null;
/*      */   
/*  124 */   private WeakFastHashMap mappedDescriptorsCache = null;
/*      */   
/*  125 */   private static final Class[] EMPTY_CLASS_PARAMETERS = new Class[0];
/*      */   
/*  126 */   private static final Class[] LIST_CLASS_PARAMETER = new Class[] { List.class };
/*      */   
/*  129 */   private static final Object[] EMPTY_OBJECT_ARRAY = new Object[0];
/*      */   
/*  132 */   private Log log = LogFactory.getLog(PropertyUtils.class);
/*      */   
/*      */   public PropertyUtilsBean() {
/*  138 */     this.descriptorsCache = new WeakFastHashMap();
/*  139 */     this.descriptorsCache.setFast(true);
/*  140 */     this.mappedDescriptorsCache = new WeakFastHashMap();
/*  141 */     this.mappedDescriptorsCache.setFast(true);
/*      */   }
/*      */   
/*      */   public Resolver getResolver() {
/*  162 */     return this.resolver;
/*      */   }
/*      */   
/*      */   public void setResolver(Resolver resolver) {
/*  179 */     if (resolver == null) {
/*  180 */       this.resolver = (Resolver)new DefaultResolver();
/*      */     } else {
/*  182 */       this.resolver = resolver;
/*      */     } 
/*      */   }
/*      */   
/*      */   public void clearDescriptors() {
/*  193 */     this.descriptorsCache.clear();
/*  194 */     this.mappedDescriptorsCache.clear();
/*  195 */     Introspector.flushCaches();
/*      */   }
/*      */   
/*      */   public void copyProperties(Object dest, Object orig) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
/*  235 */     if (dest == null)
/*  236 */       throw new IllegalArgumentException("No destination bean specified"); 
/*  239 */     if (orig == null)
/*  240 */       throw new IllegalArgumentException("No origin bean specified"); 
/*  243 */     if (orig instanceof DynaBean) {
/*  244 */       DynaProperty[] origDescriptors = ((DynaBean)orig).getDynaClass().getDynaProperties();
/*  246 */       for (int i = 0; i < origDescriptors.length; i++) {
/*  247 */         String name = origDescriptors[i].getName();
/*  248 */         if (isReadable(orig, name) && isWriteable(dest, name))
/*      */           try {
/*  250 */             Object value = ((DynaBean)orig).get(name);
/*  251 */             if (dest instanceof DynaBean) {
/*  252 */               ((DynaBean)dest).set(name, value);
/*      */             } else {
/*  254 */               setSimpleProperty(dest, name, value);
/*      */             } 
/*  256 */           } catch (NoSuchMethodException e) {
/*  257 */             if (this.log.isDebugEnabled())
/*  258 */               this.log.debug("Error writing to '" + name + "' on class '" + dest.getClass() + "'", e); 
/*      */           }  
/*      */       } 
/*  263 */     } else if (orig instanceof Map) {
/*  264 */       Iterator entries = ((Map)orig).entrySet().iterator();
/*  265 */       while (entries.hasNext()) {
/*  266 */         Map.Entry entry = entries.next();
/*  267 */         String name = (String)entry.getKey();
/*  268 */         if (isWriteable(dest, name))
/*      */           try {
/*  270 */             if (dest instanceof DynaBean) {
/*  271 */               ((DynaBean)dest).set(name, entry.getValue());
/*      */               continue;
/*      */             } 
/*  273 */             setSimpleProperty(dest, name, entry.getValue());
/*  275 */           } catch (NoSuchMethodException e) {
/*  276 */             if (this.log.isDebugEnabled())
/*  277 */               this.log.debug("Error writing to '" + name + "' on class '" + dest.getClass() + "'", e); 
/*      */           }  
/*      */       } 
/*      */     } else {
/*  283 */       PropertyDescriptor[] origDescriptors = getPropertyDescriptors(orig);
/*  285 */       for (int i = 0; i < origDescriptors.length; i++) {
/*  286 */         String name = origDescriptors[i].getName();
/*  287 */         if (isReadable(orig, name) && isWriteable(dest, name))
/*      */           try {
/*  289 */             Object value = getSimpleProperty(orig, name);
/*  290 */             if (dest instanceof DynaBean) {
/*  291 */               ((DynaBean)dest).set(name, value);
/*      */             } else {
/*  293 */               setSimpleProperty(dest, name, value);
/*      */             } 
/*  295 */           } catch (NoSuchMethodException e) {
/*  296 */             if (this.log.isDebugEnabled())
/*  297 */               this.log.debug("Error writing to '" + name + "' on class '" + dest.getClass() + "'", e); 
/*      */           }  
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public Map describe(Object bean) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
/*  330 */     if (bean == null)
/*  331 */       throw new IllegalArgumentException("No bean specified"); 
/*  333 */     Map description = new HashMap();
/*  334 */     if (bean instanceof DynaBean) {
/*  335 */       DynaProperty[] descriptors = ((DynaBean)bean).getDynaClass().getDynaProperties();
/*  337 */       for (int i = 0; i < descriptors.length; i++) {
/*  338 */         String name = descriptors[i].getName();
/*  339 */         description.put(name, getProperty(bean, name));
/*      */       } 
/*      */     } else {
/*  342 */       PropertyDescriptor[] descriptors = getPropertyDescriptors(bean);
/*  344 */       for (int i = 0; i < descriptors.length; i++) {
/*  345 */         String name = descriptors[i].getName();
/*  346 */         if (descriptors[i].getReadMethod() != null)
/*  347 */           description.put(name, getProperty(bean, name)); 
/*      */       } 
/*      */     } 
/*  351 */     return description;
/*      */   }
/*      */   
/*      */   public Object getIndexedProperty(Object bean, String name) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
/*  384 */     if (bean == null)
/*  385 */       throw new IllegalArgumentException("No bean specified"); 
/*  387 */     if (name == null)
/*  388 */       throw new IllegalArgumentException("No name specified for bean class '" + bean.getClass() + "'"); 
/*  393 */     int index = -1;
/*      */     try {
/*  395 */       index = this.resolver.getIndex(name);
/*  396 */     } catch (IllegalArgumentException e) {
/*  397 */       throw new IllegalArgumentException("Invalid indexed property '" + name + "' on bean class '" + bean.getClass() + "' " + e.getMessage());
/*      */     } 
/*  401 */     if (index < 0)
/*  402 */       throw new IllegalArgumentException("Invalid indexed property '" + name + "' on bean class '" + bean.getClass() + "'"); 
/*  407 */     name = this.resolver.getProperty(name);
/*  410 */     return getIndexedProperty(bean, name, index);
/*      */   }
/*      */   
/*      */   public Object getIndexedProperty(Object bean, String name, int index) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
/*  442 */     if (bean == null)
/*  443 */       throw new IllegalArgumentException("No bean specified"); 
/*  445 */     if (name == null || name.length() == 0) {
/*  446 */       if (bean.getClass().isArray())
/*  447 */         return Array.get(bean, index); 
/*  448 */       if (bean instanceof List)
/*  449 */         return ((List)bean).get(index); 
/*      */     } 
/*  452 */     if (name == null)
/*  453 */       throw new IllegalArgumentException("No name specified for bean class '" + bean.getClass() + "'"); 
/*  458 */     if (bean instanceof DynaBean) {
/*  459 */       DynaProperty dynaProperty = ((DynaBean)bean).getDynaClass().getDynaProperty(name);
/*  461 */       if (dynaProperty == null)
/*  462 */         throw new NoSuchMethodException("Unknown property '" + name + "' on bean class '" + bean.getClass() + "'"); 
/*  465 */       return ((DynaBean)bean).get(name, index);
/*      */     } 
/*  469 */     PropertyDescriptor descriptor = getPropertyDescriptor(bean, name);
/*  471 */     if (descriptor == null)
/*  472 */       throw new NoSuchMethodException("Unknown property '" + name + "' on bean class '" + bean.getClass() + "'"); 
/*  477 */     if (descriptor instanceof IndexedPropertyDescriptor) {
/*  478 */       Method method = ((IndexedPropertyDescriptor)descriptor).getIndexedReadMethod();
/*  480 */       method = MethodUtils.getAccessibleMethod(bean.getClass(), method);
/*  481 */       if (method != null) {
/*  482 */         Object[] subscript = new Object[1];
/*  483 */         subscript[0] = new Integer(index);
/*      */         try {
/*  485 */           return invokeMethod(method, bean, subscript);
/*  486 */         } catch (InvocationTargetException e) {
/*  487 */           if (e.getTargetException() instanceof IndexOutOfBoundsException)
/*  489 */             throw (IndexOutOfBoundsException)e.getTargetException(); 
/*  492 */           throw e;
/*      */         } 
/*      */       } 
/*      */     } 
/*  499 */     Method readMethod = getReadMethod(bean.getClass(), descriptor);
/*  500 */     if (readMethod == null)
/*  501 */       throw new NoSuchMethodException("Property '" + name + "' has no " + "getter method on bean class '" + bean.getClass() + "'"); 
/*  506 */     Object value = invokeMethod(readMethod, bean, EMPTY_OBJECT_ARRAY);
/*  507 */     if (!value.getClass().isArray()) {
/*  508 */       if (!(value instanceof List))
/*  509 */         throw new IllegalArgumentException("Property '" + name + "' is not indexed on bean class '" + bean.getClass() + "'"); 
/*  513 */       return ((List)value).get(index);
/*      */     } 
/*      */     try {
/*  518 */       return Array.get(value, index);
/*  519 */     } catch (ArrayIndexOutOfBoundsException e) {
/*  520 */       throw new ArrayIndexOutOfBoundsException("Index: " + index + ", Size: " + Array.getLength(value) + " for property '" + name + "'");
/*      */     } 
/*      */   }
/*      */   
/*      */   public Object getMappedProperty(Object bean, String name) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
/*  552 */     if (bean == null)
/*  553 */       throw new IllegalArgumentException("No bean specified"); 
/*  555 */     if (name == null)
/*  556 */       throw new IllegalArgumentException("No name specified for bean class '" + bean.getClass() + "'"); 
/*  561 */     String key = null;
/*      */     try {
/*  563 */       key = this.resolver.getKey(name);
/*  564 */     } catch (IllegalArgumentException e) {
/*  565 */       throw new IllegalArgumentException("Invalid mapped property '" + name + "' on bean class '" + bean.getClass() + "' " + e.getMessage());
/*      */     } 
/*  569 */     if (key == null)
/*  570 */       throw new IllegalArgumentException("Invalid mapped property '" + name + "' on bean class '" + bean.getClass() + "'"); 
/*  575 */     name = this.resolver.getProperty(name);
/*  578 */     return getMappedProperty(bean, name, key);
/*      */   }
/*      */   
/*      */   public Object getMappedProperty(Object bean, String name, String key) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
/*  604 */     if (bean == null)
/*  605 */       throw new IllegalArgumentException("No bean specified"); 
/*  607 */     if (name == null)
/*  608 */       throw new IllegalArgumentException("No name specified for bean class '" + bean.getClass() + "'"); 
/*  611 */     if (key == null)
/*  612 */       throw new IllegalArgumentException("No key specified for property '" + name + "' on bean class " + bean.getClass() + "'"); 
/*  617 */     if (bean instanceof DynaBean) {
/*  618 */       DynaProperty dynaProperty = ((DynaBean)bean).getDynaClass().getDynaProperty(name);
/*  620 */       if (dynaProperty == null)
/*  621 */         throw new NoSuchMethodException("Unknown property '" + name + "'+ on bean class '" + bean.getClass() + "'"); 
/*  624 */       return ((DynaBean)bean).get(name, key);
/*      */     } 
/*  627 */     Object result = null;
/*  630 */     PropertyDescriptor descriptor = getPropertyDescriptor(bean, name);
/*  631 */     if (descriptor == null)
/*  632 */       throw new NoSuchMethodException("Unknown property '" + name + "'+ on bean class '" + bean.getClass() + "'"); 
/*  636 */     if (descriptor instanceof MappedPropertyDescriptor) {
/*  638 */       Method readMethod = ((MappedPropertyDescriptor)descriptor).getMappedReadMethod();
/*  640 */       readMethod = MethodUtils.getAccessibleMethod(bean.getClass(), readMethod);
/*  641 */       if (readMethod != null) {
/*  642 */         Object[] keyArray = new Object[1];
/*  643 */         keyArray[0] = key;
/*  644 */         result = invokeMethod(readMethod, bean, keyArray);
/*      */       } else {
/*  646 */         throw new NoSuchMethodException("Property '" + name + "' has no mapped getter method on bean class '" + bean.getClass() + "'");
/*      */       } 
/*      */     } else {
/*  652 */       Method readMethod = getReadMethod(bean.getClass(), descriptor);
/*  653 */       if (readMethod != null) {
/*  654 */         Object invokeResult = invokeMethod(readMethod, bean, EMPTY_OBJECT_ARRAY);
/*  656 */         if (invokeResult instanceof Map)
/*  657 */           result = ((Map)invokeResult).get(key); 
/*      */       } else {
/*  660 */         throw new NoSuchMethodException("Property '" + name + "' has no mapped getter method on bean class '" + bean.getClass() + "'");
/*      */       } 
/*      */     } 
/*  665 */     return result;
/*      */   }
/*      */   
/*      */   public FastHashMap getMappedPropertyDescriptors(Class beanClass) {
/*  681 */     if (beanClass == null)
/*  682 */       return null; 
/*  686 */     return (FastHashMap)this.mappedDescriptorsCache.get(beanClass);
/*      */   }
/*      */   
/*      */   public FastHashMap getMappedPropertyDescriptors(Object bean) {
/*  702 */     if (bean == null)
/*  703 */       return null; 
/*  705 */     return getMappedPropertyDescriptors(bean.getClass());
/*      */   }
/*      */   
/*      */   public Object getNestedProperty(Object bean, String name) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
/*  733 */     if (bean == null)
/*  734 */       throw new IllegalArgumentException("No bean specified"); 
/*  736 */     if (name == null)
/*  737 */       throw new IllegalArgumentException("No name specified for bean class '" + bean.getClass() + "'"); 
/*  742 */     while (this.resolver.hasNested(name)) {
/*  743 */       String next = this.resolver.next(name);
/*  744 */       Object nestedBean = null;
/*  745 */       if (bean instanceof Map) {
/*  746 */         nestedBean = getPropertyOfMapBean((Map)bean, next);
/*  747 */       } else if (this.resolver.isMapped(next)) {
/*  748 */         nestedBean = getMappedProperty(bean, next);
/*  749 */       } else if (this.resolver.isIndexed(next)) {
/*  750 */         nestedBean = getIndexedProperty(bean, next);
/*      */       } else {
/*  752 */         nestedBean = getSimpleProperty(bean, next);
/*      */       } 
/*  754 */       if (nestedBean == null)
/*  755 */         throw new NestedNullException("Null property value for '" + name + "' on bean class '" + bean.getClass() + "'"); 
/*  759 */       bean = nestedBean;
/*  760 */       name = this.resolver.remove(name);
/*      */     } 
/*  763 */     if (bean instanceof Map) {
/*  764 */       bean = getPropertyOfMapBean((Map)bean, name);
/*  765 */     } else if (this.resolver.isMapped(name)) {
/*  766 */       bean = getMappedProperty(bean, name);
/*  767 */     } else if (this.resolver.isIndexed(name)) {
/*  768 */       bean = getIndexedProperty(bean, name);
/*      */     } else {
/*  770 */       bean = getSimpleProperty(bean, name);
/*      */     } 
/*  772 */     return bean;
/*      */   }
/*      */   
/*      */   protected Object getPropertyOfMapBean(Map bean, String propertyName) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
/*  804 */     if (this.resolver.isMapped(propertyName)) {
/*  805 */       String name = this.resolver.getProperty(propertyName);
/*  806 */       if (name == null || name.length() == 0)
/*  807 */         propertyName = this.resolver.getKey(propertyName); 
/*      */     } 
/*  811 */     if (this.resolver.isIndexed(propertyName) || this.resolver.isMapped(propertyName))
/*  813 */       throw new IllegalArgumentException("Indexed or mapped properties are not supported on objects of type Map: " + propertyName); 
/*  818 */     return bean.get(propertyName);
/*      */   }
/*      */   
/*      */   public Object getProperty(Object bean, String name) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
/*  846 */     return getNestedProperty(bean, name);
/*      */   }
/*      */   
/*      */   public PropertyDescriptor getPropertyDescriptor(Object bean, String name) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
/*  882 */     if (bean == null)
/*  883 */       throw new IllegalArgumentException("No bean specified"); 
/*  885 */     if (name == null)
/*  886 */       throw new IllegalArgumentException("No name specified for bean class '" + bean.getClass() + "'"); 
/*  891 */     while (this.resolver.hasNested(name)) {
/*  892 */       String next = this.resolver.next(name);
/*  893 */       Object nestedBean = getProperty(bean, next);
/*  894 */       if (nestedBean == null)
/*  895 */         throw new NestedNullException("Null property value for '" + next + "' on bean class '" + bean.getClass() + "'"); 
/*  899 */       bean = nestedBean;
/*  900 */       name = this.resolver.remove(name);
/*      */     } 
/*  904 */     name = this.resolver.getProperty(name);
/*  908 */     if (name == null)
/*  909 */       return null; 
/*  912 */     PropertyDescriptor[] descriptors = getPropertyDescriptors(bean);
/*  913 */     if (descriptors != null)
/*  915 */       for (int i = 0; i < descriptors.length; i++) {
/*  916 */         if (name.equals(descriptors[i].getName()))
/*  917 */           return descriptors[i]; 
/*      */       }  
/*  922 */     PropertyDescriptor result = null;
/*  923 */     FastHashMap mappedDescriptors = getMappedPropertyDescriptors(bean);
/*  925 */     if (mappedDescriptors == null) {
/*  926 */       mappedDescriptors = new FastHashMap();
/*  927 */       mappedDescriptors.setFast(true);
/*  928 */       this.mappedDescriptorsCache.put(bean.getClass(), mappedDescriptors);
/*      */     } 
/*  930 */     result = (PropertyDescriptor)mappedDescriptors.get(name);
/*  931 */     if (result == null) {
/*      */       try {
/*  934 */         result = new MappedPropertyDescriptor(name, bean.getClass());
/*  935 */       } catch (IntrospectionException ie) {}
/*  940 */       if (result != null)
/*  941 */         mappedDescriptors.put(name, result); 
/*      */     } 
/*  945 */     return result;
/*      */   }
/*      */   
/*      */   public PropertyDescriptor[] getPropertyDescriptors(Class beanClass) {
/*  965 */     if (beanClass == null)
/*  966 */       throw new IllegalArgumentException("No bean class specified"); 
/*  970 */     PropertyDescriptor[] descriptors = null;
/*  971 */     descriptors = (PropertyDescriptor[])this.descriptorsCache.get(beanClass);
/*  973 */     if (descriptors != null)
/*  974 */       return descriptors; 
/*  978 */     BeanInfo beanInfo = null;
/*      */     try {
/*  980 */       beanInfo = Introspector.getBeanInfo(beanClass);
/*  981 */     } catch (IntrospectionException e) {
/*  982 */       return new PropertyDescriptor[0];
/*      */     } 
/*  984 */     descriptors = beanInfo.getPropertyDescriptors();
/*  985 */     if (descriptors == null)
/*  986 */       descriptors = new PropertyDescriptor[0]; 
/* 1008 */     for (int i = 0; i < descriptors.length; i++) {
/* 1009 */       if (descriptors[i] instanceof IndexedPropertyDescriptor) {
/* 1010 */         IndexedPropertyDescriptor descriptor = (IndexedPropertyDescriptor)descriptors[i];
/* 1011 */         String propName = descriptor.getName().substring(0, 1).toUpperCase() + descriptor.getName().substring(1);
/* 1014 */         if (descriptor.getReadMethod() == null) {
/* 1015 */           String methodName = (descriptor.getIndexedReadMethod() != null) ? descriptor.getIndexedReadMethod().getName() : ("get" + propName);
/* 1018 */           Method readMethod = MethodUtils.getMatchingAccessibleMethod(beanClass, methodName, EMPTY_CLASS_PARAMETERS);
/* 1021 */           if (readMethod != null)
/*      */             try {
/* 1023 */               descriptor.setReadMethod(readMethod);
/* 1024 */             } catch (Exception e) {
/* 1025 */               this.log.error("Error setting indexed property read method", e);
/*      */             }  
/*      */         } 
/* 1029 */         if (descriptor.getWriteMethod() == null) {
/* 1030 */           String methodName = (descriptor.getIndexedWriteMethod() != null) ? descriptor.getIndexedWriteMethod().getName() : ("set" + propName);
/* 1033 */           Method writeMethod = MethodUtils.getMatchingAccessibleMethod(beanClass, methodName, LIST_CLASS_PARAMETER);
/* 1036 */           if (writeMethod == null) {
/* 1037 */             Method[] methods = beanClass.getMethods();
/* 1038 */             for (int j = 0; j < methods.length; j++) {
/* 1039 */               if (methods[j].getName().equals(methodName)) {
/* 1040 */                 Class[] parameterTypes = methods[j].getParameterTypes();
/* 1041 */                 if (parameterTypes.length == 1 && List.class.isAssignableFrom(parameterTypes[0])) {
/* 1043 */                   writeMethod = methods[j];
/*      */                   break;
/*      */                 } 
/*      */               } 
/*      */             } 
/*      */           } 
/* 1049 */           if (writeMethod != null)
/*      */             try {
/* 1051 */               descriptor.setWriteMethod(writeMethod);
/* 1052 */             } catch (Exception e) {
/* 1053 */               this.log.error("Error setting indexed property write method", e);
/*      */             }  
/*      */         } 
/*      */       } 
/*      */     } 
/* 1061 */     this.descriptorsCache.put(beanClass, descriptors);
/* 1062 */     return descriptors;
/*      */   }
/*      */   
/*      */   public PropertyDescriptor[] getPropertyDescriptors(Object bean) {
/* 1081 */     if (bean == null)
/* 1082 */       throw new IllegalArgumentException("No bean specified"); 
/* 1084 */     return getPropertyDescriptors(bean.getClass());
/*      */   }
/*      */   
/*      */   public Class getPropertyEditorClass(Object bean, String name) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
/* 1123 */     if (bean == null)
/* 1124 */       throw new IllegalArgumentException("No bean specified"); 
/* 1126 */     if (name == null)
/* 1127 */       throw new IllegalArgumentException("No name specified for bean class '" + bean.getClass() + "'"); 
/* 1131 */     PropertyDescriptor descriptor = getPropertyDescriptor(bean, name);
/* 1133 */     if (descriptor != null)
/* 1134 */       return descriptor.getPropertyEditorClass(); 
/* 1136 */     return null;
/*      */   }
/*      */   
/*      */   public Class getPropertyType(Object bean, String name) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
/* 1171 */     if (bean == null)
/* 1172 */       throw new IllegalArgumentException("No bean specified"); 
/* 1174 */     if (name == null)
/* 1175 */       throw new IllegalArgumentException("No name specified for bean class '" + bean.getClass() + "'"); 
/* 1180 */     while (this.resolver.hasNested(name)) {
/* 1181 */       String next = this.resolver.next(name);
/* 1182 */       Object nestedBean = getProperty(bean, next);
/* 1183 */       if (nestedBean == null)
/* 1184 */         throw new NestedNullException("Null property value for '" + next + "' on bean class '" + bean.getClass() + "'"); 
/* 1188 */       bean = nestedBean;
/* 1189 */       name = this.resolver.remove(name);
/*      */     } 
/* 1193 */     name = this.resolver.getProperty(name);
/* 1196 */     if (bean instanceof DynaBean) {
/* 1197 */       DynaProperty dynaProperty = ((DynaBean)bean).getDynaClass().getDynaProperty(name);
/* 1199 */       if (dynaProperty == null)
/* 1200 */         return null; 
/* 1202 */       Class type = dynaProperty.getType();
/* 1203 */       if (type == null)
/* 1204 */         return null; 
/* 1205 */       if (type.isArray())
/* 1206 */         return type.getComponentType(); 
/* 1208 */       return type;
/*      */     } 
/* 1212 */     PropertyDescriptor descriptor = getPropertyDescriptor(bean, name);
/* 1214 */     if (descriptor == null)
/* 1215 */       return null; 
/* 1216 */     if (descriptor instanceof IndexedPropertyDescriptor)
/* 1217 */       return ((IndexedPropertyDescriptor)descriptor).getIndexedPropertyType(); 
/* 1219 */     if (descriptor instanceof MappedPropertyDescriptor)
/* 1220 */       return ((MappedPropertyDescriptor)descriptor).getMappedPropertyType(); 
/* 1223 */     return descriptor.getPropertyType();
/*      */   }
/*      */   
/*      */   public Method getReadMethod(PropertyDescriptor descriptor) {
/* 1240 */     return MethodUtils.getAccessibleMethod(descriptor.getReadMethod());
/*      */   }
/*      */   
/*      */   Method getReadMethod(Class clazz, PropertyDescriptor descriptor) {
/* 1256 */     return MethodUtils.getAccessibleMethod(clazz, descriptor.getReadMethod());
/*      */   }
/*      */   
/*      */   public Object getSimpleProperty(Object bean, String name) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
/* 1283 */     if (bean == null)
/* 1284 */       throw new IllegalArgumentException("No bean specified"); 
/* 1286 */     if (name == null)
/* 1287 */       throw new IllegalArgumentException("No name specified for bean class '" + bean.getClass() + "'"); 
/* 1292 */     if (this.resolver.hasNested(name))
/* 1293 */       throw new IllegalArgumentException("Nested property names are not allowed: Property '" + name + "' on bean class '" + bean.getClass() + "'"); 
/* 1296 */     if (this.resolver.isIndexed(name))
/* 1297 */       throw new IllegalArgumentException("Indexed property names are not allowed: Property '" + name + "' on bean class '" + bean.getClass() + "'"); 
/* 1300 */     if (this.resolver.isMapped(name))
/* 1301 */       throw new IllegalArgumentException("Mapped property names are not allowed: Property '" + name + "' on bean class '" + bean.getClass() + "'"); 
/* 1307 */     if (bean instanceof DynaBean) {
/* 1308 */       DynaProperty dynaProperty = ((DynaBean)bean).getDynaClass().getDynaProperty(name);
/* 1310 */       if (dynaProperty == null)
/* 1311 */         throw new NoSuchMethodException("Unknown property '" + name + "' on dynaclass '" + ((DynaBean)bean).getDynaClass() + "'"); 
/* 1315 */       return ((DynaBean)bean).get(name);
/*      */     } 
/* 1319 */     PropertyDescriptor descriptor = getPropertyDescriptor(bean, name);
/* 1321 */     if (descriptor == null)
/* 1322 */       throw new NoSuchMethodException("Unknown property '" + name + "' on class '" + bean.getClass() + "'"); 
/* 1325 */     Method readMethod = getReadMethod(bean.getClass(), descriptor);
/* 1326 */     if (readMethod == null)
/* 1327 */       throw new NoSuchMethodException("Property '" + name + "' has no getter method in class '" + bean.getClass() + "'"); 
/* 1332 */     Object value = invokeMethod(readMethod, bean, EMPTY_OBJECT_ARRAY);
/* 1333 */     return value;
/*      */   }
/*      */   
/*      */   public Method getWriteMethod(PropertyDescriptor descriptor) {
/* 1349 */     return MethodUtils.getAccessibleMethod(descriptor.getWriteMethod());
/*      */   }
/*      */   
/*      */   Method getWriteMethod(Class clazz, PropertyDescriptor descriptor) {
/* 1365 */     return MethodUtils.getAccessibleMethod(clazz, descriptor.getWriteMethod());
/*      */   }
/*      */   
/*      */   public boolean isReadable(Object bean, String name) {
/* 1387 */     if (bean == null)
/* 1388 */       throw new IllegalArgumentException("No bean specified"); 
/* 1390 */     if (name == null)
/* 1391 */       throw new IllegalArgumentException("No name specified for bean class '" + bean.getClass() + "'"); 
/* 1396 */     while (this.resolver.hasNested(name)) {
/* 1397 */       String next = this.resolver.next(name);
/* 1398 */       Object nestedBean = null;
/*      */       try {
/* 1400 */         nestedBean = getProperty(bean, next);
/* 1401 */       } catch (IllegalAccessException e) {
/* 1402 */         return false;
/* 1403 */       } catch (InvocationTargetException e) {
/* 1404 */         return false;
/* 1405 */       } catch (NoSuchMethodException e) {
/* 1406 */         return false;
/*      */       } 
/* 1408 */       if (nestedBean == null)
/* 1409 */         throw new NestedNullException("Null property value for '" + next + "' on bean class '" + bean.getClass() + "'"); 
/* 1413 */       bean = nestedBean;
/* 1414 */       name = this.resolver.remove(name);
/*      */     } 
/* 1418 */     name = this.resolver.getProperty(name);
/* 1422 */     if (bean instanceof WrapDynaBean)
/* 1423 */       bean = ((WrapDynaBean)bean).getInstance(); 
/* 1427 */     if (bean instanceof DynaBean)
/* 1429 */       return (((DynaBean)bean).getDynaClass().getDynaProperty(name) != null); 
/*      */     try {
/* 1432 */       PropertyDescriptor desc = getPropertyDescriptor(bean, name);
/* 1434 */       if (desc != null) {
/* 1435 */         Method readMethod = getReadMethod(bean.getClass(), desc);
/* 1436 */         if (readMethod == null) {
/* 1437 */           if (desc instanceof IndexedPropertyDescriptor) {
/* 1438 */             readMethod = ((IndexedPropertyDescriptor)desc).getIndexedReadMethod();
/* 1439 */           } else if (desc instanceof MappedPropertyDescriptor) {
/* 1440 */             readMethod = ((MappedPropertyDescriptor)desc).getMappedReadMethod();
/*      */           } 
/* 1442 */           readMethod = MethodUtils.getAccessibleMethod(bean.getClass(), readMethod);
/*      */         } 
/* 1444 */         return (readMethod != null);
/*      */       } 
/* 1446 */       return false;
/* 1448 */     } catch (IllegalAccessException e) {
/* 1449 */       return false;
/* 1450 */     } catch (InvocationTargetException e) {
/* 1451 */       return false;
/* 1452 */     } catch (NoSuchMethodException e) {
/* 1453 */       return false;
/*      */     } 
/*      */   }
/*      */   
/*      */   public boolean isWriteable(Object bean, String name) {
/* 1478 */     if (bean == null)
/* 1479 */       throw new IllegalArgumentException("No bean specified"); 
/* 1481 */     if (name == null)
/* 1482 */       throw new IllegalArgumentException("No name specified for bean class '" + bean.getClass() + "'"); 
/* 1487 */     while (this.resolver.hasNested(name)) {
/* 1488 */       String next = this.resolver.next(name);
/* 1489 */       Object nestedBean = null;
/*      */       try {
/* 1491 */         nestedBean = getProperty(bean, next);
/* 1492 */       } catch (IllegalAccessException e) {
/* 1493 */         return false;
/* 1494 */       } catch (InvocationTargetException e) {
/* 1495 */         return false;
/* 1496 */       } catch (NoSuchMethodException e) {
/* 1497 */         return false;
/*      */       } 
/* 1499 */       if (nestedBean == null)
/* 1500 */         throw new NestedNullException("Null property value for '" + next + "' on bean class '" + bean.getClass() + "'"); 
/* 1504 */       bean = nestedBean;
/* 1505 */       name = this.resolver.remove(name);
/*      */     } 
/* 1509 */     name = this.resolver.getProperty(name);
/* 1513 */     if (bean instanceof WrapDynaBean)
/* 1514 */       bean = ((WrapDynaBean)bean).getInstance(); 
/* 1518 */     if (bean instanceof DynaBean)
/* 1520 */       return (((DynaBean)bean).getDynaClass().getDynaProperty(name) != null); 
/*      */     try {
/* 1523 */       PropertyDescriptor desc = getPropertyDescriptor(bean, name);
/* 1525 */       if (desc != null) {
/* 1526 */         Method writeMethod = getWriteMethod(bean.getClass(), desc);
/* 1527 */         if (writeMethod == null) {
/* 1528 */           if (desc instanceof IndexedPropertyDescriptor) {
/* 1529 */             writeMethod = ((IndexedPropertyDescriptor)desc).getIndexedWriteMethod();
/* 1530 */           } else if (desc instanceof MappedPropertyDescriptor) {
/* 1531 */             writeMethod = ((MappedPropertyDescriptor)desc).getMappedWriteMethod();
/*      */           } 
/* 1533 */           writeMethod = MethodUtils.getAccessibleMethod(bean.getClass(), writeMethod);
/*      */         } 
/* 1535 */         return (writeMethod != null);
/*      */       } 
/* 1537 */       return false;
/* 1539 */     } catch (IllegalAccessException e) {
/* 1540 */       return false;
/* 1541 */     } catch (InvocationTargetException e) {
/* 1542 */       return false;
/* 1543 */     } catch (NoSuchMethodException e) {
/* 1544 */       return false;
/*      */     } 
/*      */   }
/*      */   
/*      */   public void setIndexedProperty(Object bean, String name, Object value) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
/* 1581 */     if (bean == null)
/* 1582 */       throw new IllegalArgumentException("No bean specified"); 
/* 1584 */     if (name == null)
/* 1585 */       throw new IllegalArgumentException("No name specified for bean class '" + bean.getClass() + "'"); 
/* 1590 */     int index = -1;
/*      */     try {
/* 1592 */       index = this.resolver.getIndex(name);
/* 1593 */     } catch (IllegalArgumentException e) {
/* 1594 */       throw new IllegalArgumentException("Invalid indexed property '" + name + "' on bean class '" + bean.getClass() + "'");
/*      */     } 
/* 1597 */     if (index < 0)
/* 1598 */       throw new IllegalArgumentException("Invalid indexed property '" + name + "' on bean class '" + bean.getClass() + "'"); 
/* 1603 */     name = this.resolver.getProperty(name);
/* 1606 */     setIndexedProperty(bean, name, index, value);
/*      */   }
/*      */   
/*      */   public void setIndexedProperty(Object bean, String name, int index, Object value) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
/* 1638 */     if (bean == null)
/* 1639 */       throw new IllegalArgumentException("No bean specified"); 
/* 1641 */     if (name == null || name.length() == 0) {
/* 1642 */       if (bean.getClass().isArray()) {
/* 1643 */         Array.set(bean, index, value);
/*      */         return;
/*      */       } 
/* 1645 */       if (bean instanceof List) {
/* 1646 */         ((List)bean).set(index, value);
/*      */         return;
/*      */       } 
/*      */     } 
/* 1650 */     if (name == null)
/* 1651 */       throw new IllegalArgumentException("No name specified for bean class '" + bean.getClass() + "'"); 
/* 1656 */     if (bean instanceof DynaBean) {
/* 1657 */       DynaProperty dynaProperty = ((DynaBean)bean).getDynaClass().getDynaProperty(name);
/* 1659 */       if (dynaProperty == null)
/* 1660 */         throw new NoSuchMethodException("Unknown property '" + name + "' on bean class '" + bean.getClass() + "'"); 
/* 1663 */       ((DynaBean)bean).set(name, index, value);
/*      */       return;
/*      */     } 
/* 1668 */     PropertyDescriptor descriptor = getPropertyDescriptor(bean, name);
/* 1670 */     if (descriptor == null)
/* 1671 */       throw new NoSuchMethodException("Unknown property '" + name + "' on bean class '" + bean.getClass() + "'"); 
/* 1676 */     if (descriptor instanceof IndexedPropertyDescriptor) {
/* 1677 */       Method writeMethod = ((IndexedPropertyDescriptor)descriptor).getIndexedWriteMethod();
/* 1679 */       writeMethod = MethodUtils.getAccessibleMethod(bean.getClass(), writeMethod);
/* 1680 */       if (writeMethod != null) {
/* 1681 */         Object[] subscript = new Object[2];
/* 1682 */         subscript[0] = new Integer(index);
/* 1683 */         subscript[1] = value;
/*      */         try {
/* 1685 */           if (this.log.isTraceEnabled()) {
/* 1686 */             String valueClassName = (value == null) ? "<null>" : value.getClass().getName();
/* 1689 */             this.log.trace("setSimpleProperty: Invoking method " + writeMethod + " with index=" + index + ", value=" + value + " (class " + valueClassName + ")");
/*      */           } 
/* 1694 */           invokeMethod(writeMethod, bean, subscript);
/* 1695 */         } catch (InvocationTargetException e) {
/* 1696 */           if (e.getTargetException() instanceof IndexOutOfBoundsException)
/* 1698 */             throw (IndexOutOfBoundsException)e.getTargetException(); 
/* 1701 */           throw e;
/*      */         } 
/*      */         return;
/*      */       } 
/*      */     } 
/* 1709 */     Method readMethod = getReadMethod(bean.getClass(), descriptor);
/* 1710 */     if (readMethod == null)
/* 1711 */       throw new NoSuchMethodException("Property '" + name + "' has no getter method on bean class '" + bean.getClass() + "'"); 
/* 1716 */     Object array = invokeMethod(readMethod, bean, EMPTY_OBJECT_ARRAY);
/* 1717 */     if (!array.getClass().isArray()) {
/* 1718 */       if (array instanceof List) {
/* 1720 */         ((List)array).set(index, value);
/*      */       } else {
/* 1722 */         throw new IllegalArgumentException("Property '" + name + "' is not indexed on bean class '" + bean.getClass() + "'");
/*      */       } 
/*      */     } else {
/* 1727 */       Array.set(array, index, value);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void setMappedProperty(Object bean, String name, Object value) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
/* 1757 */     if (bean == null)
/* 1758 */       throw new IllegalArgumentException("No bean specified"); 
/* 1760 */     if (name == null)
/* 1761 */       throw new IllegalArgumentException("No name specified for bean class '" + bean.getClass() + "'"); 
/* 1766 */     String key = null;
/*      */     try {
/* 1768 */       key = this.resolver.getKey(name);
/* 1769 */     } catch (IllegalArgumentException e) {
/* 1770 */       throw new IllegalArgumentException("Invalid mapped property '" + name + "' on bean class '" + bean.getClass() + "'");
/*      */     } 
/* 1774 */     if (key == null)
/* 1775 */       throw new IllegalArgumentException("Invalid mapped property '" + name + "' on bean class '" + bean.getClass() + "'"); 
/* 1781 */     name = this.resolver.getProperty(name);
/* 1784 */     setMappedProperty(bean, name, key, value);
/*      */   }
/*      */   
/*      */   public void setMappedProperty(Object bean, String name, String key, Object value) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
/* 1810 */     if (bean == null)
/* 1811 */       throw new IllegalArgumentException("No bean specified"); 
/* 1813 */     if (name == null)
/* 1814 */       throw new IllegalArgumentException("No name specified for bean class '" + bean.getClass() + "'"); 
/* 1817 */     if (key == null)
/* 1818 */       throw new IllegalArgumentException("No key specified for property '" + name + "' on bean class '" + bean.getClass() + "'"); 
/* 1823 */     if (bean instanceof DynaBean) {
/* 1824 */       DynaProperty dynaProperty = ((DynaBean)bean).getDynaClass().getDynaProperty(name);
/* 1826 */       if (dynaProperty == null)
/* 1827 */         throw new NoSuchMethodException("Unknown property '" + name + "' on bean class '" + bean.getClass() + "'"); 
/* 1830 */       ((DynaBean)bean).set(name, key, value);
/*      */       return;
/*      */     } 
/* 1835 */     PropertyDescriptor descriptor = getPropertyDescriptor(bean, name);
/* 1837 */     if (descriptor == null)
/* 1838 */       throw new NoSuchMethodException("Unknown property '" + name + "' on bean class '" + bean.getClass() + "'"); 
/* 1842 */     if (descriptor instanceof MappedPropertyDescriptor) {
/* 1844 */       Method mappedWriteMethod = ((MappedPropertyDescriptor)descriptor).getMappedWriteMethod();
/* 1847 */       mappedWriteMethod = MethodUtils.getAccessibleMethod(bean.getClass(), mappedWriteMethod);
/* 1848 */       if (mappedWriteMethod != null) {
/* 1849 */         Object[] params = new Object[2];
/* 1850 */         params[0] = key;
/* 1851 */         params[1] = value;
/* 1852 */         if (this.log.isTraceEnabled()) {
/* 1853 */           String valueClassName = (value == null) ? "<null>" : value.getClass().getName();
/* 1855 */           this.log.trace("setSimpleProperty: Invoking method " + mappedWriteMethod + " with key=" + key + ", value=" + value + " (class " + valueClassName + ")");
/*      */         } 
/* 1860 */         invokeMethod(mappedWriteMethod, bean, params);
/*      */       } else {
/* 1862 */         throw new NoSuchMethodException("Property '" + name + "' has no mapped setter method" + "on bean class '" + bean.getClass() + "'");
/*      */       } 
/*      */     } else {
/* 1868 */       Method readMethod = getReadMethod(bean.getClass(), descriptor);
/* 1869 */       if (readMethod != null) {
/* 1870 */         Object invokeResult = invokeMethod(readMethod, bean, EMPTY_OBJECT_ARRAY);
/* 1872 */         if (invokeResult instanceof Map)
/* 1873 */           ((Map)invokeResult).put(key, value); 
/*      */       } else {
/* 1876 */         throw new NoSuchMethodException("Property '" + name + "' has no mapped getter method on bean class '" + bean.getClass() + "'");
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public void setNestedProperty(Object bean, String name, Object value) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
/* 1920 */     if (bean == null)
/* 1921 */       throw new IllegalArgumentException("No bean specified"); 
/* 1923 */     if (name == null)
/* 1924 */       throw new IllegalArgumentException("No name specified for bean class '" + bean.getClass() + "'"); 
/* 1929 */     while (this.resolver.hasNested(name)) {
/* 1930 */       String next = this.resolver.next(name);
/* 1931 */       Object nestedBean = null;
/* 1932 */       if (bean instanceof Map) {
/* 1933 */         nestedBean = getPropertyOfMapBean((Map)bean, next);
/* 1934 */       } else if (this.resolver.isMapped(next)) {
/* 1935 */         nestedBean = getMappedProperty(bean, next);
/* 1936 */       } else if (this.resolver.isIndexed(next)) {
/* 1937 */         nestedBean = getIndexedProperty(bean, next);
/*      */       } else {
/* 1939 */         nestedBean = getSimpleProperty(bean, next);
/*      */       } 
/* 1941 */       if (nestedBean == null)
/* 1942 */         throw new NestedNullException("Null property value for '" + name + "' on bean class '" + bean.getClass() + "'"); 
/* 1946 */       bean = nestedBean;
/* 1947 */       name = this.resolver.remove(name);
/*      */     } 
/* 1950 */     if (bean instanceof Map) {
/* 1951 */       setPropertyOfMapBean((Map)bean, name, value);
/* 1952 */     } else if (this.resolver.isMapped(name)) {
/* 1953 */       setMappedProperty(bean, name, value);
/* 1954 */     } else if (this.resolver.isIndexed(name)) {
/* 1955 */       setIndexedProperty(bean, name, value);
/*      */     } else {
/* 1957 */       setSimpleProperty(bean, name, value);
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void setPropertyOfMapBean(Map bean, String propertyName, Object value) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
/* 2022 */     if (this.resolver.isMapped(propertyName)) {
/* 2023 */       String name = this.resolver.getProperty(propertyName);
/* 2024 */       if (name == null || name.length() == 0)
/* 2025 */         propertyName = this.resolver.getKey(propertyName); 
/*      */     } 
/* 2029 */     if (this.resolver.isIndexed(propertyName) || this.resolver.isMapped(propertyName))
/* 2031 */       throw new IllegalArgumentException("Indexed or mapped properties are not supported on objects of type Map: " + propertyName); 
/* 2036 */     bean.put(propertyName, value);
/*      */   }
/*      */   
/*      */   public void setProperty(Object bean, String name, Object value) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
/* 2064 */     setNestedProperty(bean, name, value);
/*      */   }
/*      */   
/*      */   public void setSimpleProperty(Object bean, String name, Object value) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
/* 2093 */     if (bean == null)
/* 2094 */       throw new IllegalArgumentException("No bean specified"); 
/* 2096 */     if (name == null)
/* 2097 */       throw new IllegalArgumentException("No name specified for bean class '" + bean.getClass() + "'"); 
/* 2102 */     if (this.resolver.hasNested(name))
/* 2103 */       throw new IllegalArgumentException("Nested property names are not allowed: Property '" + name + "' on bean class '" + bean.getClass() + "'"); 
/* 2106 */     if (this.resolver.isIndexed(name))
/* 2107 */       throw new IllegalArgumentException("Indexed property names are not allowed: Property '" + name + "' on bean class '" + bean.getClass() + "'"); 
/* 2110 */     if (this.resolver.isMapped(name))
/* 2111 */       throw new IllegalArgumentException("Mapped property names are not allowed: Property '" + name + "' on bean class '" + bean.getClass() + "'"); 
/* 2117 */     if (bean instanceof DynaBean) {
/* 2118 */       DynaProperty dynaProperty = ((DynaBean)bean).getDynaClass().getDynaProperty(name);
/* 2120 */       if (dynaProperty == null)
/* 2121 */         throw new NoSuchMethodException("Unknown property '" + name + "' on dynaclass '" + ((DynaBean)bean).getDynaClass() + "'"); 
/* 2125 */       ((DynaBean)bean).set(name, value);
/*      */       return;
/*      */     } 
/* 2130 */     PropertyDescriptor descriptor = getPropertyDescriptor(bean, name);
/* 2132 */     if (descriptor == null)
/* 2133 */       throw new NoSuchMethodException("Unknown property '" + name + "' on class '" + bean.getClass() + "'"); 
/* 2136 */     Method writeMethod = getWriteMethod(bean.getClass(), descriptor);
/* 2137 */     if (writeMethod == null)
/* 2138 */       throw new NoSuchMethodException("Property '" + name + "' has no setter method in class '" + bean.getClass() + "'"); 
/* 2143 */     Object[] values = new Object[1];
/* 2144 */     values[0] = value;
/* 2145 */     if (this.log.isTraceEnabled()) {
/* 2146 */       String valueClassName = (value == null) ? "<null>" : value.getClass().getName();
/* 2148 */       this.log.trace("setSimpleProperty: Invoking method " + writeMethod + " with value " + value + " (class " + valueClassName + ")");
/*      */     } 
/* 2151 */     invokeMethod(writeMethod, bean, values);
/*      */   }
/*      */   
/*      */   private Object invokeMethod(Method method, Object bean, Object[] values) throws IllegalAccessException, InvocationTargetException {
/* 2163 */     if (bean == null)
/* 2164 */       throw new IllegalArgumentException("No bean specified - this should have been checked before reaching this method"); 
/*      */     try {
/* 2170 */       return method.invoke(bean, values);
/* 2172 */     } catch (NullPointerException cause) {
/* 2175 */       String valueString = "";
/* 2176 */       if (values != null)
/* 2177 */         for (int i = 0; i < values.length; i++) {
/* 2178 */           if (i > 0)
/* 2179 */             valueString = valueString + ", "; 
/* 2181 */           if (values[i] == null) {
/* 2182 */             valueString = valueString + "<null>";
/*      */           } else {
/* 2184 */             valueString = valueString + values[i].getClass().getName();
/*      */           } 
/*      */         }  
/* 2188 */       String expectedString = "";
/* 2189 */       Class[] parTypes = method.getParameterTypes();
/* 2190 */       if (parTypes != null)
/* 2191 */         for (int i = 0; i < parTypes.length; i++) {
/* 2192 */           if (i > 0)
/* 2193 */             expectedString = expectedString + ", "; 
/* 2195 */           expectedString = expectedString + parTypes[i].getName();
/*      */         }  
/* 2198 */       IllegalArgumentException e = new IllegalArgumentException("Cannot invoke " + method.getDeclaringClass().getName() + "." + method.getName() + " on bean class '" + bean.getClass() + "' - " + cause.getMessage() + " - had objects of type \"" + valueString + "\" but expected signature \"" + expectedString + "\"");
/* 2207 */       if (!BeanUtils.initCause(e, cause))
/* 2208 */         this.log.error("Method invocation failed", cause); 
/* 2210 */       throw e;
/* 2211 */     } catch (IllegalArgumentException cause) {
/* 2212 */       String valueString = "";
/* 2213 */       if (values != null)
/* 2214 */         for (int i = 0; i < values.length; i++) {
/* 2215 */           if (i > 0)
/* 2216 */             valueString = valueString + ", "; 
/* 2218 */           if (values[i] == null) {
/* 2219 */             valueString = valueString + "<null>";
/*      */           } else {
/* 2221 */             valueString = valueString + values[i].getClass().getName();
/*      */           } 
/*      */         }  
/* 2225 */       String expectedString = "";
/* 2226 */       Class[] parTypes = method.getParameterTypes();
/* 2227 */       if (parTypes != null)
/* 2228 */         for (int i = 0; i < parTypes.length; i++) {
/* 2229 */           if (i > 0)
/* 2230 */             expectedString = expectedString + ", "; 
/* 2232 */           expectedString = expectedString + parTypes[i].getName();
/*      */         }  
/* 2235 */       IllegalArgumentException e = new IllegalArgumentException("Cannot invoke " + method.getDeclaringClass().getName() + "." + method.getName() + " on bean class '" + bean.getClass() + "' - " + cause.getMessage() + " - had objects of type \"" + valueString + "\" but expected signature \"" + expectedString + "\"");
/* 2244 */       if (!BeanUtils.initCause(e, cause))
/* 2245 */         this.log.error("Method invocation failed", cause); 
/* 2247 */       throw e;
/*      */     } 
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\beanutils\PropertyUtilsBean.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */