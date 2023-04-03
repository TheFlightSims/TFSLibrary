/*     */ package org.openstreetmap.osmosis.core.util;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class LazyHashMap<K, V> implements Map<K, V> {
/*     */   private Map<K, V> internalMap;
/*     */   
/*     */   public void clear() {
/*  33 */     if (this.internalMap != null)
/*  34 */       this.internalMap.clear(); 
/*     */   }
/*     */   
/*     */   public boolean containsKey(Object key) {
/*  44 */     if (this.internalMap != null)
/*  45 */       return this.internalMap.containsKey(key); 
/*  47 */     return false;
/*     */   }
/*     */   
/*     */   public boolean containsValue(Object value) {
/*  57 */     if (this.internalMap != null)
/*  58 */       return this.internalMap.containsValue(value); 
/*  60 */     return false;
/*     */   }
/*     */   
/*     */   public Set<Map.Entry<K, V>> entrySet() {
/*  70 */     if (this.internalMap != null)
/*  71 */       return this.internalMap.entrySet(); 
/*  73 */     return Collections.emptySet();
/*     */   }
/*     */   
/*     */   public V get(Object key) {
/*  83 */     if (this.internalMap != null)
/*  84 */       return this.internalMap.get(key); 
/*  86 */     return null;
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/*  96 */     if (this.internalMap != null)
/*  97 */       return this.internalMap.isEmpty(); 
/*  99 */     return true;
/*     */   }
/*     */   
/*     */   public Set<K> keySet() {
/* 109 */     if (this.internalMap != null)
/* 110 */       return this.internalMap.keySet(); 
/* 112 */     return Collections.emptySet();
/*     */   }
/*     */   
/*     */   public V put(K key, V value) {
/* 122 */     if (this.internalMap == null)
/* 123 */       this.internalMap = new HashMap<K, V>(); 
/* 126 */     return this.internalMap.put(key, value);
/*     */   }
/*     */   
/*     */   public void putAll(Map<? extends K, ? extends V> m) {
/* 135 */     if (this.internalMap == null)
/* 136 */       this.internalMap = new HashMap<K, V>(); 
/* 139 */     this.internalMap.putAll(m);
/*     */   }
/*     */   
/*     */   public V remove(Object key) {
/* 148 */     if (this.internalMap != null)
/* 149 */       return this.internalMap.remove(key); 
/* 151 */     return null;
/*     */   }
/*     */   
/*     */   public int size() {
/* 161 */     if (this.internalMap != null)
/* 162 */       return this.internalMap.size(); 
/* 164 */     return 0;
/*     */   }
/*     */   
/*     */   public Collection<V> values() {
/* 174 */     if (this.internalMap != null)
/* 175 */       return this.internalMap.values(); 
/* 177 */     return Collections.emptyMap().values();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\cor\\util\LazyHashMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */