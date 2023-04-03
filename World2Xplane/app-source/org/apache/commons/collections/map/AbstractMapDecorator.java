/*     */ package org.apache.commons.collections.map;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ public abstract class AbstractMapDecorator implements Map {
/*     */   protected transient Map map;
/*     */   
/*     */   protected AbstractMapDecorator() {}
/*     */   
/*     */   public AbstractMapDecorator(Map map) {
/*  62 */     if (map == null)
/*  63 */       throw new IllegalArgumentException("Map must not be null"); 
/*  65 */     this.map = map;
/*     */   }
/*     */   
/*     */   protected Map getMap() {
/*  74 */     return this.map;
/*     */   }
/*     */   
/*     */   public void clear() {
/*  79 */     this.map.clear();
/*     */   }
/*     */   
/*     */   public boolean containsKey(Object key) {
/*  83 */     return this.map.containsKey(key);
/*     */   }
/*     */   
/*     */   public boolean containsValue(Object value) {
/*  87 */     return this.map.containsValue(value);
/*     */   }
/*     */   
/*     */   public Set entrySet() {
/*  91 */     return this.map.entrySet();
/*     */   }
/*     */   
/*     */   public Object get(Object key) {
/*  95 */     return this.map.get(key);
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/*  99 */     return this.map.isEmpty();
/*     */   }
/*     */   
/*     */   public Set keySet() {
/* 103 */     return this.map.keySet();
/*     */   }
/*     */   
/*     */   public Object put(Object key, Object value) {
/* 107 */     return this.map.put(key, value);
/*     */   }
/*     */   
/*     */   public void putAll(Map mapToCopy) {
/* 111 */     this.map.putAll(mapToCopy);
/*     */   }
/*     */   
/*     */   public Object remove(Object key) {
/* 115 */     return this.map.remove(key);
/*     */   }
/*     */   
/*     */   public int size() {
/* 119 */     return this.map.size();
/*     */   }
/*     */   
/*     */   public Collection values() {
/* 123 */     return this.map.values();
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/* 127 */     if (object == this)
/* 128 */       return true; 
/* 130 */     return this.map.equals(object);
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 134 */     return this.map.hashCode();
/*     */   }
/*     */   
/*     */   public String toString() {
/* 138 */     return this.map.toString();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\map\AbstractMapDecorator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */