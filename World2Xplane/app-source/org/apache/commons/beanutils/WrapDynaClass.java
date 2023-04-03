/*     */ package org.apache.commons.beanutils;
/*     */ 
/*     */ import java.beans.PropertyDescriptor;
/*     */ import java.lang.ref.Reference;
/*     */ import java.lang.ref.SoftReference;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.WeakHashMap;
/*     */ import org.apache.commons.collections.FastHashMap;
/*     */ 
/*     */ public class WrapDynaClass implements DynaClass {
/*     */   private String beanClassName;
/*     */   
/*     */   private Reference beanClassRef;
/*     */   
/*     */   protected Class beanClass;
/*     */   
/*     */   protected PropertyDescriptor[] descriptors;
/*     */   
/*     */   protected HashMap descriptorsMap;
/*     */   
/*     */   protected DynaProperty[] properties;
/*     */   
/*     */   protected HashMap propertiesMap;
/*     */   
/*     */   private WrapDynaClass(Class beanClass) {
/*  78 */     this.beanClassName = null;
/*  83 */     this.beanClassRef = null;
/*  91 */     this.beanClass = null;
/*  97 */     this.descriptors = null;
/* 105 */     this.descriptorsMap = new HashMap();
/* 111 */     this.properties = null;
/* 119 */     this.propertiesMap = new HashMap();
/*     */     this.beanClassRef = new SoftReference(beanClass);
/*     */     this.beanClassName = beanClass.getName();
/*     */     introspect();
/*     */   }
/*     */   
/* 125 */   private static final ContextClassLoaderLocal CLASSLOADER_CACHE = new ContextClassLoaderLocal() {
/*     */       protected Object initialValue() {
/* 128 */         return new WeakHashMap();
/*     */       }
/*     */     };
/*     */   
/*     */   private static Map getDynaClassesMap() {
/* 136 */     return (Map)CLASSLOADER_CACHE.get();
/*     */   }
/*     */   
/* 175 */   protected static HashMap dynaClasses = new HashMap() {
/*     */       public void clear() {
/* 177 */         WrapDynaClass.getDynaClassesMap().clear();
/*     */       }
/*     */       
/*     */       public boolean containsKey(Object key) {
/* 180 */         return WrapDynaClass.getDynaClassesMap().containsKey(key);
/*     */       }
/*     */       
/*     */       public boolean containsValue(Object value) {
/* 183 */         return WrapDynaClass.getDynaClassesMap().containsValue(value);
/*     */       }
/*     */       
/*     */       public Set entrySet() {
/* 186 */         return WrapDynaClass.getDynaClassesMap().entrySet();
/*     */       }
/*     */       
/*     */       public boolean equals(Object o) {
/* 189 */         return WrapDynaClass.getDynaClassesMap().equals(o);
/*     */       }
/*     */       
/*     */       public Object get(Object key) {
/* 192 */         return WrapDynaClass.getDynaClassesMap().get(key);
/*     */       }
/*     */       
/*     */       public int hashCode() {
/* 195 */         return WrapDynaClass.getDynaClassesMap().hashCode();
/*     */       }
/*     */       
/*     */       public boolean isEmpty() {
/* 198 */         return WrapDynaClass.getDynaClassesMap().isEmpty();
/*     */       }
/*     */       
/*     */       public Set keySet() {
/* 201 */         return WrapDynaClass.getDynaClassesMap().keySet();
/*     */       }
/*     */       
/*     */       public Object put(Object key, Object value) {
/* 204 */         return WrapDynaClass.getDynaClassesMap().put(key, value);
/*     */       }
/*     */       
/*     */       public void putAll(Map m) {
/* 207 */         WrapDynaClass.getDynaClassesMap().putAll(m);
/*     */       }
/*     */       
/*     */       public Object remove(Object key) {
/* 210 */         return WrapDynaClass.getDynaClassesMap().remove(key);
/*     */       }
/*     */       
/*     */       public int size() {
/* 213 */         return WrapDynaClass.getDynaClassesMap().size();
/*     */       }
/*     */       
/*     */       public Collection values() {
/* 216 */         return WrapDynaClass.getDynaClassesMap().values();
/*     */       }
/*     */     };
/*     */   
/*     */   protected Class getBeanClass() {
/* 230 */     return this.beanClassRef.get();
/*     */   }
/*     */   
/*     */   public String getName() {
/* 243 */     return this.beanClassName;
/*     */   }
/*     */   
/*     */   public DynaProperty getDynaProperty(String name) {
/* 260 */     if (name == null)
/* 261 */       throw new IllegalArgumentException("No property name specified"); 
/* 264 */     return (DynaProperty)this.propertiesMap.get(name);
/*     */   }
/*     */   
/*     */   public DynaProperty[] getDynaProperties() {
/* 282 */     return this.properties;
/*     */   }
/*     */   
/*     */   public DynaBean newInstance() throws IllegalAccessException, InstantiationException {
/* 315 */     return new WrapDynaBean(getBeanClass().newInstance());
/*     */   }
/*     */   
/*     */   public PropertyDescriptor getPropertyDescriptor(String name) {
/* 332 */     return (PropertyDescriptor)this.descriptorsMap.get(name);
/*     */   }
/*     */   
/*     */   public static void clear() {
/* 345 */     getDynaClassesMap().clear();
/*     */   }
/*     */   
/*     */   public static WrapDynaClass createDynaClass(Class beanClass) {
/* 359 */     WrapDynaClass dynaClass = (WrapDynaClass)getDynaClassesMap().get(beanClass);
/* 361 */     if (dynaClass == null) {
/* 362 */       dynaClass = new WrapDynaClass(beanClass);
/* 363 */       getDynaClassesMap().put(beanClass, dynaClass);
/*     */     } 
/* 365 */     return dynaClass;
/*     */   }
/*     */   
/*     */   protected void introspect() {
/*     */     HashMap hashMap;
/* 379 */     Class beanClass = getBeanClass();
/* 380 */     PropertyDescriptor[] regulars = PropertyUtils.getPropertyDescriptors(beanClass);
/* 382 */     if (regulars == null)
/* 383 */       regulars = new PropertyDescriptor[0]; 
/* 385 */     FastHashMap fastHashMap = PropertyUtils.getMappedPropertyDescriptors(beanClass);
/* 387 */     if (fastHashMap == null)
/* 388 */       hashMap = new HashMap(); 
/* 392 */     this.properties = new DynaProperty[regulars.length + hashMap.size()];
/* 393 */     for (int i = 0; i < regulars.length; i++) {
/* 394 */       this.descriptorsMap.put(regulars[i].getName(), regulars[i]);
/* 396 */       this.properties[i] = new DynaProperty(regulars[i].getName(), regulars[i].getPropertyType());
/* 399 */       this.propertiesMap.put(this.properties[i].getName(), this.properties[i]);
/*     */     } 
/* 402 */     int j = regulars.length;
/* 403 */     Iterator names = hashMap.keySet().iterator();
/* 404 */     while (names.hasNext()) {
/* 405 */       String name = names.next();
/* 406 */       PropertyDescriptor descriptor = (PropertyDescriptor)hashMap.get(name);
/* 408 */       this.properties[j] = new DynaProperty(descriptor.getName(), Map.class);
/* 411 */       this.propertiesMap.put(this.properties[j].getName(), this.properties[j]);
/* 413 */       j++;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\beanutils\WrapDynaClass.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */