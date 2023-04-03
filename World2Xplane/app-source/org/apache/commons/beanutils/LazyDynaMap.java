/*     */ package org.apache.commons.beanutils;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class LazyDynaMap extends LazyDynaBean implements MutableDynaClass {
/*     */   protected String name;
/*     */   
/*     */   protected boolean restricted;
/*     */   
/*     */   protected boolean returnNull = false;
/*     */   
/*     */   public LazyDynaMap() {
/*  77 */     this((String)null, (Map)null);
/*     */   }
/*     */   
/*     */   public LazyDynaMap(String name) {
/*  86 */     this(name, (Map)null);
/*     */   }
/*     */   
/*     */   public LazyDynaMap(Map values) {
/*  95 */     this((String)null, values);
/*     */   }
/*     */   
/*     */   public LazyDynaMap(String name, Map values) {
/* 105 */     this.name = (name == null) ? "LazyDynaMap" : name;
/* 106 */     this.values = (values == null) ? newMap() : values;
/* 107 */     this.dynaClass = this;
/*     */   }
/*     */   
/*     */   public LazyDynaMap(DynaProperty[] properties) {
/* 116 */     this((String)null, properties);
/*     */   }
/*     */   
/*     */   public LazyDynaMap(String name, DynaProperty[] properties) {
/* 126 */     this(name, (Map)null);
/* 127 */     if (properties != null)
/* 128 */       for (int i = 0; i < properties.length; i++)
/* 129 */         add(properties[i]);  
/*     */   }
/*     */   
/*     */   public LazyDynaMap(DynaClass dynaClass) {
/* 140 */     this(dynaClass.getName(), dynaClass.getDynaProperties());
/*     */   }
/*     */   
/*     */   public void setMap(Map values) {
/* 151 */     this.values = values;
/*     */   }
/*     */   
/*     */   public Map getMap() {
/* 160 */     return this.values;
/*     */   }
/*     */   
/*     */   public void set(String name, Object value) {
/* 173 */     if (isRestricted() && !this.values.containsKey(name))
/* 174 */       throw new IllegalArgumentException("Invalid property name '" + name + "' (DynaClass is restricted)"); 
/* 178 */     this.values.put(name, value);
/*     */   }
/*     */   
/*     */   public String getName() {
/* 191 */     return this.name;
/*     */   }
/*     */   
/*     */   public DynaProperty getDynaProperty(String name) {
/* 219 */     if (name == null)
/* 220 */       throw new IllegalArgumentException("Property name is missing."); 
/* 225 */     if (!this.values.containsKey(name) && isReturnNull())
/* 226 */       return null; 
/* 229 */     Object value = this.values.get(name);
/* 231 */     if (value == null)
/* 232 */       return new DynaProperty(name); 
/* 234 */     return new DynaProperty(name, value.getClass());
/*     */   }
/*     */   
/*     */   public DynaProperty[] getDynaProperties() {
/* 251 */     int i = 0;
/* 252 */     DynaProperty[] properties = new DynaProperty[this.values.size()];
/* 253 */     Iterator iterator = this.values.keySet().iterator();
/* 255 */     while (iterator.hasNext()) {
/* 256 */       String name = iterator.next();
/* 257 */       Object value = this.values.get(name);
/* 258 */       properties[i++] = new DynaProperty(name, (value == null) ? null : value.getClass());
/*     */     } 
/* 261 */     return properties;
/*     */   }
/*     */   
/*     */   public DynaBean newInstance() {
/* 273 */     Map newMap = null;
/*     */     try {
/* 275 */       newMap = (Map)getMap().getClass().newInstance();
/* 276 */     } catch (Exception ex) {
/* 277 */       newMap = newMap();
/*     */     } 
/* 281 */     LazyDynaMap lazyMap = new LazyDynaMap(newMap);
/* 282 */     DynaProperty[] properties = getDynaProperties();
/* 283 */     if (properties != null)
/* 284 */       for (int i = 0; i < properties.length; i++)
/* 285 */         lazyMap.add(properties[i]);  
/* 288 */     return lazyMap;
/*     */   }
/*     */   
/*     */   public boolean isRestricted() {
/* 303 */     return this.restricted;
/*     */   }
/*     */   
/*     */   public void setRestricted(boolean restricted) {
/* 314 */     this.restricted = restricted;
/*     */   }
/*     */   
/*     */   public void add(String name) {
/* 326 */     add(name, (Class)null);
/*     */   }
/*     */   
/*     */   public void add(String name, Class type) {
/* 343 */     if (name == null)
/* 344 */       throw new IllegalArgumentException("Property name is missing."); 
/* 347 */     if (isRestricted())
/* 348 */       throw new IllegalStateException("DynaClass is currently restricted. No new properties can be added."); 
/* 351 */     Object value = this.values.get(name);
/* 354 */     if (value == null)
/* 355 */       this.values.put(name, (type == null) ? null : createProperty(name, type)); 
/*     */   }
/*     */   
/*     */   public void add(String name, Class type, boolean readable, boolean writeable) {
/* 382 */     throw new UnsupportedOperationException("readable/writable properties not supported");
/*     */   }
/*     */   
/*     */   protected void add(DynaProperty property) {
/* 393 */     add(property.getName(), property.getType());
/*     */   }
/*     */   
/*     */   public void remove(String name) {
/* 411 */     if (name == null)
/* 412 */       throw new IllegalArgumentException("Property name is missing."); 
/* 415 */     if (isRestricted())
/* 416 */       throw new IllegalStateException("DynaClass is currently restricted. No properties can be removed."); 
/* 420 */     if (this.values.containsKey(name))
/* 421 */       this.values.remove(name); 
/*     */   }
/*     */   
/*     */   public boolean isReturnNull() {
/* 439 */     return this.returnNull;
/*     */   }
/*     */   
/*     */   public void setReturnNull(boolean returnNull) {
/* 452 */     this.returnNull = returnNull;
/*     */   }
/*     */   
/*     */   protected boolean isDynaProperty(String name) {
/* 473 */     if (name == null)
/* 474 */       throw new IllegalArgumentException("Property name is missing."); 
/* 477 */     return this.values.containsKey(name);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\beanutils\LazyDynaMap.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */