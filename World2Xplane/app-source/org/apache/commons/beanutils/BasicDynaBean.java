/*     */ package org.apache.commons.beanutils;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.lang.reflect.Array;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class BasicDynaBean implements DynaBean, Serializable {
/*     */   protected DynaClass dynaClass;
/*     */   
/*     */   protected HashMap values;
/*     */   
/*     */   private transient Map mapDecorator;
/*     */   
/*     */   public BasicDynaBean(DynaClass dynaClass) {
/*  71 */     this.dynaClass = null;
/*  77 */     this.values = new HashMap();
/*     */     this.dynaClass = dynaClass;
/*     */   }
/*     */   
/*     */   public Map getMap() {
/*  95 */     if (this.mapDecorator == null)
/*  96 */       this.mapDecorator = new DynaBeanMapDecorator(this); 
/*  98 */     return this.mapDecorator;
/*     */   }
/*     */   
/*     */   public boolean contains(String name, String key) {
/* 119 */     Object value = this.values.get(name);
/* 120 */     if (value == null)
/* 121 */       throw new NullPointerException("No mapped value for '" + name + "(" + key + ")'"); 
/* 123 */     if (value instanceof Map)
/* 124 */       return ((Map)value).containsKey(key); 
/* 126 */     throw new IllegalArgumentException("Non-mapped property for '" + name + "(" + key + ")'");
/*     */   }
/*     */   
/*     */   public Object get(String name) {
/* 145 */     Object value = this.values.get(name);
/* 146 */     if (value != null)
/* 147 */       return value; 
/* 151 */     Class type = getDynaProperty(name).getType();
/* 152 */     if (!type.isPrimitive())
/* 153 */       return value; 
/* 157 */     if (type == boolean.class)
/* 158 */       return Boolean.FALSE; 
/* 159 */     if (type == byte.class)
/* 160 */       return new Byte((byte)0); 
/* 161 */     if (type == char.class)
/* 162 */       return new Character(false); 
/* 163 */     if (type == double.class)
/* 164 */       return new Double(0.0D); 
/* 165 */     if (type == float.class)
/* 166 */       return new Float(0.0F); 
/* 167 */     if (type == int.class)
/* 168 */       return new Integer(0); 
/* 169 */     if (type == long.class)
/* 170 */       return new Long(0L); 
/* 171 */     if (type == short.class)
/* 172 */       return new Short((short)0); 
/* 174 */     return null;
/*     */   }
/*     */   
/*     */   public Object get(String name, int index) {
/* 198 */     Object value = this.values.get(name);
/* 199 */     if (value == null)
/* 200 */       throw new NullPointerException("No indexed value for '" + name + "[" + index + "]'"); 
/* 202 */     if (value.getClass().isArray())
/* 203 */       return Array.get(value, index); 
/* 204 */     if (value instanceof List)
/* 205 */       return ((List)value).get(index); 
/* 207 */     throw new IllegalArgumentException("Non-indexed property for '" + name + "[" + index + "]'");
/*     */   }
/*     */   
/*     */   public Object get(String name, String key) {
/* 229 */     Object value = this.values.get(name);
/* 230 */     if (value == null)
/* 231 */       throw new NullPointerException("No mapped value for '" + name + "(" + key + ")'"); 
/* 233 */     if (value instanceof Map)
/* 234 */       return ((Map)value).get(key); 
/* 236 */     throw new IllegalArgumentException("Non-mapped property for '" + name + "(" + key + ")'");
/*     */   }
/*     */   
/*     */   public DynaClass getDynaClass() {
/* 251 */     return this.dynaClass;
/*     */   }
/*     */   
/*     */   public void remove(String name, String key) {
/* 269 */     Object value = this.values.get(name);
/* 270 */     if (value == null)
/* 271 */       throw new NullPointerException("No mapped value for '" + name + "(" + key + ")'"); 
/* 273 */     if (value instanceof Map) {
/* 274 */       ((Map)value).remove(key);
/*     */     } else {
/* 276 */       throw new IllegalArgumentException("Non-mapped property for '" + name + "(" + key + ")'");
/*     */     } 
/*     */   }
/*     */   
/*     */   public void set(String name, Object value) {
/* 298 */     DynaProperty descriptor = getDynaProperty(name);
/* 299 */     if (value == null) {
/* 300 */       if (descriptor.getType().isPrimitive())
/* 301 */         throw new NullPointerException("Primitive value for '" + name + "'"); 
/* 304 */     } else if (!isAssignable(descriptor.getType(), value.getClass())) {
/* 305 */       throw new ConversionException("Cannot assign value of type '" + value.getClass().getName() + "' to property '" + name + "' of type '" + descriptor.getType().getName() + "'");
/*     */     } 
/* 311 */     this.values.put(name, value);
/*     */   }
/*     */   
/*     */   public void set(String name, int index, Object value) {
/* 334 */     Object prop = this.values.get(name);
/* 335 */     if (prop == null)
/* 336 */       throw new NullPointerException("No indexed value for '" + name + "[" + index + "]'"); 
/* 338 */     if (prop.getClass().isArray()) {
/* 339 */       Array.set(prop, index, value);
/* 340 */     } else if (prop instanceof List) {
/*     */       try {
/* 342 */         ((List)prop).set(index, value);
/* 343 */       } catch (ClassCastException e) {
/* 344 */         throw new ConversionException(e.getMessage());
/*     */       } 
/*     */     } else {
/* 347 */       throw new IllegalArgumentException("Non-indexed property for '" + name + "[" + index + "]'");
/*     */     } 
/*     */   }
/*     */   
/*     */   public void set(String name, String key, Object value) {
/* 370 */     Object prop = this.values.get(name);
/* 371 */     if (prop == null)
/* 372 */       throw new NullPointerException("No mapped value for '" + name + "(" + key + ")'"); 
/* 374 */     if (prop instanceof Map) {
/* 375 */       ((Map)prop).put(key, value);
/*     */     } else {
/* 377 */       throw new IllegalArgumentException("Non-mapped property for '" + name + "(" + key + ")'");
/*     */     } 
/*     */   }
/*     */   
/*     */   protected DynaProperty getDynaProperty(String name) {
/* 398 */     DynaProperty descriptor = getDynaClass().getDynaProperty(name);
/* 399 */     if (descriptor == null)
/* 400 */       throw new IllegalArgumentException("Invalid property name '" + name + "'"); 
/* 403 */     return descriptor;
/*     */   }
/*     */   
/*     */   protected boolean isAssignable(Class dest, Class source) {
/* 418 */     if (dest.isAssignableFrom(source) || (dest == boolean.class && source == Boolean.class) || (dest == byte.class && source == Byte.class) || (dest == char.class && source == Character.class) || (dest == double.class && source == Double.class) || (dest == float.class && source == Float.class) || (dest == int.class && source == Integer.class) || (dest == long.class && source == Long.class) || (dest == short.class && source == Short.class))
/* 427 */       return true; 
/* 429 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\beanutils\BasicDynaBean.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */