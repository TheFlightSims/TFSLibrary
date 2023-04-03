/*     */ package org.apache.commons.configuration.beanutils;
/*     */ 
/*     */ import java.beans.PropertyDescriptor;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.beanutils.BeanUtils;
/*     */ import org.apache.commons.beanutils.PropertyUtils;
/*     */ import org.apache.commons.configuration.ConfigurationRuntimeException;
/*     */ import org.apache.commons.lang.ClassUtils;
/*     */ 
/*     */ public final class BeanHelper {
/*  62 */   private static Map beanFactories = Collections.synchronizedMap(new HashMap());
/*     */   
/*  68 */   private static BeanFactory defaultBeanFactory = DefaultBeanFactory.INSTANCE;
/*     */   
/*     */   public static void registerBeanFactory(String name, BeanFactory factory) {
/*  88 */     if (name == null)
/*  90 */       throw new IllegalArgumentException("Name for bean factory must not be null!"); 
/*  93 */     if (factory == null)
/*  95 */       throw new IllegalArgumentException("Bean factory must not be null!"); 
/*  98 */     beanFactories.put(name, factory);
/*     */   }
/*     */   
/*     */   public static BeanFactory deregisterBeanFactory(String name) {
/* 111 */     return (BeanFactory)beanFactories.remove(name);
/*     */   }
/*     */   
/*     */   public static Set registeredFactoryNames() {
/* 121 */     return beanFactories.keySet();
/*     */   }
/*     */   
/*     */   public static BeanFactory getDefaultBeanFactory() {
/* 131 */     return defaultBeanFactory;
/*     */   }
/*     */   
/*     */   public static void setDefaultBeanFactory(BeanFactory factory) {
/* 143 */     if (factory == null)
/* 145 */       throw new IllegalArgumentException("Default bean factory must not be null!"); 
/* 148 */     defaultBeanFactory = factory;
/*     */   }
/*     */   
/*     */   public static void initBean(Object bean, BeanDeclaration data) throws ConfigurationRuntimeException {
/* 164 */     initBeanProperties(bean, data);
/* 166 */     Map nestedBeans = data.getNestedBeanDeclarations();
/* 167 */     if (nestedBeans != null)
/* 169 */       if (bean instanceof Collection) {
/* 171 */         Collection coll = (Collection)bean;
/* 172 */         if (nestedBeans.size() == 1) {
/* 174 */           Map.Entry e = nestedBeans.entrySet().iterator().next();
/* 175 */           String propName = (String)e.getKey();
/* 176 */           Class defaultClass = getDefaultClass(bean, propName);
/* 177 */           if (e.getValue() instanceof List) {
/* 179 */             Iterator iter = ((List)e.getValue()).iterator();
/* 180 */             while (iter.hasNext())
/* 182 */               coll.add(createBean(iter.next(), defaultClass)); 
/*     */           } else {
/* 187 */             BeanDeclaration decl = (BeanDeclaration)e.getValue();
/* 188 */             coll.add(createBean(decl, defaultClass));
/*     */           } 
/*     */         } 
/*     */       } else {
/* 194 */         for (Iterator it = nestedBeans.entrySet().iterator(); it.hasNext(); ) {
/* 196 */           Map.Entry e = it.next();
/* 197 */           String propName = (String)e.getKey();
/* 198 */           Class defaultClass = getDefaultClass(bean, propName);
/* 199 */           initProperty(bean, propName, createBean((BeanDeclaration)e.getValue(), defaultClass));
/*     */         } 
/*     */       }  
/*     */   }
/*     */   
/*     */   public static void initBeanProperties(Object bean, BeanDeclaration data) throws ConfigurationRuntimeException {
/* 216 */     Map properties = data.getBeanProperties();
/* 217 */     if (properties != null)
/* 219 */       for (Iterator it = properties.entrySet().iterator(); it.hasNext(); ) {
/* 221 */         Map.Entry e = it.next();
/* 222 */         String propName = (String)e.getKey();
/* 223 */         initProperty(bean, propName, e.getValue());
/*     */       }  
/*     */   }
/*     */   
/*     */   private static Class getDefaultClass(Object bean, String propName) {
/*     */     try {
/* 238 */       PropertyDescriptor desc = PropertyUtils.getPropertyDescriptor(bean, propName);
/* 239 */       if (desc == null)
/* 241 */         return null; 
/* 243 */       return desc.getPropertyType();
/* 245 */     } catch (Exception ex) {
/* 247 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void initProperty(Object bean, String propName, Object value) throws ConfigurationRuntimeException {
/* 263 */     if (!PropertyUtils.isWriteable(bean, propName))
/* 265 */       throw new ConfigurationRuntimeException("Property " + propName + " cannot be set on " + bean.getClass().getName()); 
/*     */     try {
/* 271 */       BeanUtils.setProperty(bean, propName, value);
/* 273 */     } catch (IllegalAccessException iaex) {
/* 275 */       throw new ConfigurationRuntimeException(iaex);
/* 277 */     } catch (InvocationTargetException itex) {
/* 279 */       throw new ConfigurationRuntimeException(itex);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void setProperty(Object bean, String propName, Object value) {
/* 294 */     if (PropertyUtils.isWriteable(bean, propName))
/* 296 */       initProperty(bean, propName, value); 
/*     */   }
/*     */   
/*     */   public static Object createBean(BeanDeclaration data, Class defaultClass, Object param) throws ConfigurationRuntimeException {
/* 321 */     if (data == null)
/* 323 */       throw new IllegalArgumentException("Bean declaration must not be null!"); 
/* 327 */     BeanFactory factory = fetchBeanFactory(data);
/*     */     try {
/* 330 */       return factory.createBean(fetchBeanClass(data, defaultClass, factory), data, param);
/* 333 */     } catch (Exception ex) {
/* 335 */       throw new ConfigurationRuntimeException(ex);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static Object createBean(BeanDeclaration data, Class defaultClass) throws ConfigurationRuntimeException {
/* 352 */     return createBean(data, defaultClass, null);
/*     */   }
/*     */   
/*     */   public static Object createBean(BeanDeclaration data) throws ConfigurationRuntimeException {
/* 366 */     return createBean(data, null);
/*     */   }
/*     */   
/*     */   static Class loadClass(String name, Class callingClass) throws ClassNotFoundException {
/* 383 */     return ClassUtils.getClass(name);
/*     */   }
/*     */   
/*     */   private static Class fetchBeanClass(BeanDeclaration data, Class defaultClass, BeanFactory factory) throws ConfigurationRuntimeException {
/* 403 */     String clsName = data.getBeanClassName();
/* 404 */     if (clsName != null)
/*     */       try {
/* 408 */         return loadClass(clsName, factory.getClass());
/* 410 */       } catch (ClassNotFoundException cex) {
/* 412 */         throw new ConfigurationRuntimeException(cex);
/*     */       }  
/* 416 */     if (defaultClass != null)
/* 418 */       return defaultClass; 
/* 421 */     Class clazz = factory.getDefaultBeanClass();
/* 422 */     if (clazz == null)
/* 424 */       throw new ConfigurationRuntimeException("Bean class is not specified!"); 
/* 427 */     return clazz;
/*     */   }
/*     */   
/*     */   private static BeanFactory fetchBeanFactory(BeanDeclaration data) throws ConfigurationRuntimeException {
/* 442 */     String factoryName = data.getBeanFactoryName();
/* 443 */     if (factoryName != null) {
/* 445 */       BeanFactory factory = (BeanFactory)beanFactories.get(factoryName);
/* 446 */       if (factory == null)
/* 448 */         throw new ConfigurationRuntimeException("Unknown bean factory: " + factoryName); 
/* 453 */       return factory;
/*     */     } 
/* 458 */     return getDefaultBeanFactory();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\configuration\beanutils\BeanHelper.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */