/*     */ package org.apache.commons.beanutils;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ 
/*     */ public class WrapDynaBean implements DynaBean, Serializable {
/*     */   protected transient WrapDynaClass dynaClass;
/*     */   
/*     */   protected Object instance;
/*     */   
/*     */   public WrapDynaBean(Object instance) {
/*  76 */     this.dynaClass = null;
/*  82 */     this.instance = null;
/*     */     this.instance = instance;
/*     */     this.dynaClass = (WrapDynaClass)getDynaClass();
/*     */   }
/*     */   
/*     */   public boolean contains(String name, String key) {
/* 102 */     throw new UnsupportedOperationException("WrapDynaBean does not support contains()");
/*     */   }
/*     */   
/*     */   public Object get(String name) {
/* 119 */     Object value = null;
/*     */     try {
/* 121 */       value = PropertyUtils.getSimpleProperty(this.instance, name);
/* 122 */     } catch (InvocationTargetException ite) {
/* 123 */       Throwable cause = ite.getTargetException();
/* 124 */       throw new IllegalArgumentException("Error reading property '" + name + "' nested exception - " + cause);
/* 127 */     } catch (Throwable t) {
/* 128 */       throw new IllegalArgumentException("Error reading property '" + name + "', exception - " + t);
/*     */     } 
/* 132 */     return value;
/*     */   }
/*     */   
/*     */   public Object get(String name, int index) {
/* 155 */     Object value = null;
/*     */     try {
/* 157 */       value = PropertyUtils.getIndexedProperty(this.instance, name, index);
/* 158 */     } catch (IndexOutOfBoundsException e) {
/* 159 */       throw e;
/* 160 */     } catch (InvocationTargetException ite) {
/* 161 */       Throwable cause = ite.getTargetException();
/* 162 */       throw new IllegalArgumentException("Error reading indexed property '" + name + "' nested exception - " + cause);
/* 165 */     } catch (Throwable t) {
/* 166 */       throw new IllegalArgumentException("Error reading indexed property '" + name + "', exception - " + t);
/*     */     } 
/* 170 */     return value;
/*     */   }
/*     */   
/*     */   public Object get(String name, String key) {
/* 190 */     Object value = null;
/*     */     try {
/* 192 */       value = PropertyUtils.getMappedProperty(this.instance, name, key);
/* 193 */     } catch (InvocationTargetException ite) {
/* 194 */       Throwable cause = ite.getTargetException();
/* 195 */       throw new IllegalArgumentException("Error reading mapped property '" + name + "' nested exception - " + cause);
/* 198 */     } catch (Throwable t) {
/* 199 */       throw new IllegalArgumentException("Error reading mapped property '" + name + "', exception - " + t);
/*     */     } 
/* 203 */     return value;
/*     */   }
/*     */   
/*     */   public DynaClass getDynaClass() {
/* 215 */     if (this.dynaClass == null)
/* 216 */       this.dynaClass = WrapDynaClass.createDynaClass(this.instance.getClass()); 
/* 219 */     return this.dynaClass;
/*     */   }
/*     */   
/*     */   public void remove(String name, String key) {
/* 238 */     throw new UnsupportedOperationException("WrapDynaBean does not support remove()");
/*     */   }
/*     */   
/*     */   public void set(String name, Object value) {
/*     */     try {
/* 260 */       PropertyUtils.setSimpleProperty(this.instance, name, value);
/* 261 */     } catch (InvocationTargetException ite) {
/* 262 */       Throwable cause = ite.getTargetException();
/* 263 */       throw new IllegalArgumentException("Error setting property '" + name + "' nested exception -" + cause);
/* 266 */     } catch (Throwable t) {
/* 267 */       throw new IllegalArgumentException("Error setting property '" + name + "', exception - " + t);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void set(String name, int index, Object value) {
/*     */     try {
/* 294 */       PropertyUtils.setIndexedProperty(this.instance, name, index, value);
/* 295 */     } catch (IndexOutOfBoundsException e) {
/* 296 */       throw e;
/* 297 */     } catch (InvocationTargetException ite) {
/* 298 */       Throwable cause = ite.getTargetException();
/* 299 */       throw new IllegalArgumentException("Error setting indexed property '" + name + "' nested exception - " + cause);
/* 302 */     } catch (Throwable t) {
/* 303 */       throw new IllegalArgumentException("Error setting indexed property '" + name + "', exception - " + t);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void set(String name, String key, Object value) {
/*     */     try {
/* 328 */       PropertyUtils.setMappedProperty(this.instance, name, key, value);
/* 329 */     } catch (InvocationTargetException ite) {
/* 330 */       Throwable cause = ite.getTargetException();
/* 331 */       throw new IllegalArgumentException("Error setting mapped property '" + name + "' nested exception - " + cause);
/* 334 */     } catch (Throwable t) {
/* 335 */       throw new IllegalArgumentException("Error setting mapped property '" + name + "', exception - " + t);
/*     */     } 
/*     */   }
/*     */   
/*     */   public Object getInstance() {
/* 353 */     return this.instance;
/*     */   }
/*     */   
/*     */   protected DynaProperty getDynaProperty(String name) {
/* 371 */     DynaProperty descriptor = getDynaClass().getDynaProperty(name);
/* 372 */     if (descriptor == null)
/* 373 */       throw new IllegalArgumentException("Invalid property name '" + name + "'"); 
/* 376 */     return descriptor;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\beanutils\WrapDynaBean.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */