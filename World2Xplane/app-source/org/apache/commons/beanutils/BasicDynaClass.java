/*     */ package org.apache.commons.beanutils;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.util.HashMap;
/*     */ 
/*     */ public class BasicDynaClass implements DynaClass, Serializable {
/*     */   public BasicDynaClass() {
/*  51 */     this(null, null, null);
/*     */   }
/*     */   
/*     */   public BasicDynaClass(String name, Class dynaBeanClass) {
/*  64 */     this(name, dynaBeanClass, null);
/*     */   }
/*     */   
/*     */   public BasicDynaClass(String name, Class dynaBeanClass, DynaProperty[] properties) {
/*  80 */     if (name != null)
/*  81 */       this.name = name; 
/*  83 */     if (dynaBeanClass == null)
/*  84 */       dynaBeanClass = BasicDynaBean.class; 
/*  86 */     setDynaBeanClass(dynaBeanClass);
/*  87 */     if (properties != null)
/*  88 */       setProperties(properties); 
/*     */   }
/*     */   
/* 101 */   protected transient Constructor constructor = null;
/*     */   
/* 108 */   protected static Class[] constructorTypes = new Class[] { DynaClass.class };
/*     */   
/* 115 */   protected Object[] constructorValues = new Object[] { this };
/*     */   
/* 122 */   protected Class dynaBeanClass = BasicDynaBean.class;
/*     */   
/* 128 */   protected String name = getClass().getName();
/*     */   
/* 134 */   protected DynaProperty[] properties = new DynaProperty[0];
/*     */   
/* 142 */   protected HashMap propertiesMap = new HashMap();
/*     */   
/*     */   public String getName() {
/* 158 */     return this.name;
/*     */   }
/*     */   
/*     */   public DynaProperty getDynaProperty(String name) {
/* 175 */     if (name == null)
/* 176 */       throw new IllegalArgumentException("No property name specified"); 
/* 179 */     return (DynaProperty)this.propertiesMap.get(name);
/*     */   }
/*     */   
/*     */   public DynaProperty[] getDynaProperties() {
/* 197 */     return this.properties;
/*     */   }
/*     */   
/*     */   public DynaBean newInstance() throws IllegalAccessException, InstantiationException {
/*     */     try {
/* 218 */       if (this.constructor == null)
/* 219 */         setDynaBeanClass(this.dynaBeanClass); 
/* 222 */       return this.constructor.newInstance(this.constructorValues);
/* 223 */     } catch (InvocationTargetException e) {
/* 224 */       throw new InstantiationException(e.getTargetException().getMessage());
/*     */     } 
/*     */   }
/*     */   
/*     */   public Class getDynaBeanClass() {
/* 243 */     return this.dynaBeanClass;
/*     */   }
/*     */   
/*     */   protected void setDynaBeanClass(Class dynaBeanClass) {
/* 264 */     if (dynaBeanClass.isInterface())
/* 265 */       throw new IllegalArgumentException("Class " + dynaBeanClass.getName() + " is an interface, not a class"); 
/* 269 */     if (!DynaBean.class.isAssignableFrom(dynaBeanClass))
/* 270 */       throw new IllegalArgumentException("Class " + dynaBeanClass.getName() + " does not implement DynaBean"); 
/*     */     try {
/* 277 */       this.constructor = dynaBeanClass.getConstructor(constructorTypes);
/* 278 */     } catch (NoSuchMethodException e) {
/* 279 */       throw new IllegalArgumentException("Class " + dynaBeanClass.getName() + " does not have an appropriate constructor");
/*     */     } 
/* 283 */     this.dynaBeanClass = dynaBeanClass;
/*     */   }
/*     */   
/*     */   protected void setProperties(DynaProperty[] properties) {
/* 295 */     this.properties = properties;
/* 296 */     this.propertiesMap.clear();
/* 297 */     for (int i = 0; i < properties.length; i++)
/* 298 */       this.propertiesMap.put(properties[i].getName(), properties[i]); 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\beanutils\BasicDynaClass.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */