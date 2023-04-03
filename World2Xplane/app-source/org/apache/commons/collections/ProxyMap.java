/*     */ package org.apache.commons.collections;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ public abstract class ProxyMap implements Map {
/*     */   protected Map map;
/*     */   
/*     */   public ProxyMap(Map map) {
/*  62 */     this.map = map;
/*     */   }
/*     */   
/*     */   public void clear() {
/*  69 */     this.map.clear();
/*     */   }
/*     */   
/*     */   public boolean containsKey(Object key) {
/*  76 */     return this.map.containsKey(key);
/*     */   }
/*     */   
/*     */   public boolean containsValue(Object value) {
/*  83 */     return this.map.containsValue(value);
/*     */   }
/*     */   
/*     */   public Set entrySet() {
/*  90 */     return this.map.entrySet();
/*     */   }
/*     */   
/*     */   public boolean equals(Object m) {
/*  97 */     return this.map.equals(m);
/*     */   }
/*     */   
/*     */   public Object get(Object key) {
/* 104 */     return this.map.get(key);
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 111 */     return this.map.hashCode();
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 118 */     return this.map.isEmpty();
/*     */   }
/*     */   
/*     */   public Set keySet() {
/* 125 */     return this.map.keySet();
/*     */   }
/*     */   
/*     */   public Object put(Object key, Object value) {
/* 132 */     return this.map.put(key, value);
/*     */   }
/*     */   
/*     */   public void putAll(Map t) {
/* 139 */     this.map.putAll(t);
/*     */   }
/*     */   
/*     */   public Object remove(Object key) {
/* 146 */     return this.map.remove(key);
/*     */   }
/*     */   
/*     */   public int size() {
/* 153 */     return this.map.size();
/*     */   }
/*     */   
/*     */   public Collection values() {
/* 160 */     return this.map.values();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\ProxyMap.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */