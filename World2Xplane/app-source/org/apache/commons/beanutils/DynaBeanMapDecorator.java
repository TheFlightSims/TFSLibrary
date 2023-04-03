/*     */ package org.apache.commons.beanutils;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class DynaBeanMapDecorator implements Map {
/*     */   private DynaBean dynaBean;
/*     */   
/*     */   private boolean readOnly;
/*     */   
/*     */   private transient Set keySet;
/*     */   
/*     */   public DynaBeanMapDecorator(DynaBean dynaBean) {
/*  89 */     this(dynaBean, true);
/*     */   }
/*     */   
/*     */   public DynaBeanMapDecorator(DynaBean dynaBean, boolean readOnly) {
/* 101 */     if (dynaBean == null)
/* 102 */       throw new IllegalArgumentException("DynaBean is null"); 
/* 104 */     this.dynaBean = dynaBean;
/* 105 */     this.readOnly = readOnly;
/*     */   }
/*     */   
/*     */   public boolean isReadOnly() {
/* 119 */     return this.readOnly;
/*     */   }
/*     */   
/*     */   public void clear() {
/* 130 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean containsKey(Object key) {
/* 142 */     DynaClass dynaClass = getDynaBean().getDynaClass();
/* 143 */     DynaProperty dynaProperty = dynaClass.getDynaProperty(toString(key));
/* 144 */     return !(dynaProperty == null);
/*     */   }
/*     */   
/*     */   public boolean containsValue(Object value) {
/* 157 */     DynaProperty[] properties = getDynaProperties();
/* 158 */     for (int i = 0; i < properties.length; i++) {
/* 159 */       String key = properties[i].getName();
/* 160 */       Object prop = getDynaBean().get(key);
/* 161 */       if (value == null) {
/* 162 */         if (prop == null)
/* 163 */           return true; 
/* 166 */       } else if (value.equals(prop)) {
/* 167 */         return true;
/*     */       } 
/*     */     } 
/* 171 */     return false;
/*     */   }
/*     */   
/*     */   public Set entrySet() {
/* 185 */     DynaProperty[] properties = getDynaProperties();
/* 186 */     Set set = new HashSet(properties.length);
/* 187 */     for (int i = 0; i < properties.length; i++) {
/* 188 */       String key = properties[i].getName();
/* 189 */       Object value = getDynaBean().get(key);
/* 190 */       set.add(new MapEntry(key, value));
/*     */     } 
/* 192 */     return Collections.unmodifiableSet(set);
/*     */   }
/*     */   
/*     */   public Object get(Object key) {
/* 203 */     return getDynaBean().get(toString(key));
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 214 */     return ((getDynaProperties()).length == 0);
/*     */   }
/*     */   
/*     */   public Set keySet() {
/* 229 */     if (this.keySet != null)
/* 230 */       return this.keySet; 
/* 234 */     DynaProperty[] properties = getDynaProperties();
/* 235 */     Set set = new HashSet(properties.length);
/* 236 */     for (int i = 0; i < properties.length; i++)
/* 237 */       set.add(properties[i].getName()); 
/* 239 */     set = Collections.unmodifiableSet(set);
/* 242 */     DynaClass dynaClass = getDynaBean().getDynaClass();
/* 243 */     if (!(dynaClass instanceof MutableDynaClass))
/* 244 */       this.keySet = set; 
/* 247 */     return set;
/*     */   }
/*     */   
/*     */   public Object put(Object key, Object value) {
/* 262 */     if (isReadOnly())
/* 263 */       throw new UnsupportedOperationException("Map is read only"); 
/* 265 */     String property = toString(key);
/* 266 */     Object previous = getDynaBean().get(property);
/* 267 */     getDynaBean().set(property, value);
/* 268 */     return previous;
/*     */   }
/*     */   
/*     */   public void putAll(Map map) {
/* 279 */     if (isReadOnly())
/* 280 */       throw new UnsupportedOperationException("Map is read only"); 
/* 282 */     Iterator keys = map.keySet().iterator();
/* 283 */     while (keys.hasNext()) {
/* 284 */       Object key = keys.next();
/* 285 */       put(key, map.get(key));
/*     */     } 
/*     */   }
/*     */   
/*     */   public Object remove(Object key) {
/* 297 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public int size() {
/* 306 */     return (getDynaProperties()).length;
/*     */   }
/*     */   
/*     */   public Collection values() {
/* 316 */     DynaProperty[] properties = getDynaProperties();
/* 317 */     List values = new ArrayList(properties.length);
/* 318 */     for (int i = 0; i < properties.length; i++) {
/* 319 */       String key = properties[i].getName();
/* 320 */       Object value = getDynaBean().get(key);
/* 321 */       values.add(value);
/*     */     } 
/* 323 */     return Collections.unmodifiableList(values);
/*     */   }
/*     */   
/*     */   public DynaBean getDynaBean() {
/* 335 */     return this.dynaBean;
/*     */   }
/*     */   
/*     */   private DynaProperty[] getDynaProperties() {
/* 347 */     return getDynaBean().getDynaClass().getDynaProperties();
/*     */   }
/*     */   
/*     */   private String toString(Object obj) {
/* 358 */     return (obj == null) ? null : obj.toString();
/*     */   }
/*     */   
/*     */   private static class MapEntry implements Map.Entry {
/*     */     private Object key;
/*     */     
/*     */     private Object value;
/*     */     
/*     */     MapEntry(Object key, Object value) {
/* 368 */       this.key = key;
/* 369 */       this.value = value;
/*     */     }
/*     */     
/*     */     public boolean equals(Object o) {
/* 372 */       if (!(o instanceof Map.Entry))
/* 373 */         return false; 
/* 375 */       Map.Entry e = (Map.Entry)o;
/* 376 */       return (this.key.equals(e.getKey()) && ((this.value == null) ? (e.getValue() == null) : this.value.equals(e.getValue())));
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 381 */       return this.key.hashCode() + ((this.value == null) ? 0 : this.value.hashCode());
/*     */     }
/*     */     
/*     */     public Object getKey() {
/* 384 */       return this.key;
/*     */     }
/*     */     
/*     */     public Object getValue() {
/* 387 */       return this.value;
/*     */     }
/*     */     
/*     */     public Object setValue(Object value) {
/* 390 */       throw new UnsupportedOperationException();
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\beanutils\DynaBeanMapDecorator.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */