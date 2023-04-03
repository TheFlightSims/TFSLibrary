/*     */ package org.geotools.util;
/*     */ 
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.Map;
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ import org.opengis.util.Cloneable;
/*     */ 
/*     */ public class CheckedHashMap<K, V> extends LinkedHashMap<K, V> implements Cloneable {
/*     */   private static final long serialVersionUID = -7777695267921872849L;
/*     */   
/*     */   private final Class<K> keyType;
/*     */   
/*     */   private final Class<V> valueType;
/*     */   
/*     */   public CheckedHashMap(Class<K> keyType, Class<V> valueType) {
/*  75 */     this.keyType = keyType;
/*  76 */     this.valueType = valueType;
/*  77 */     ensureNonNull(keyType, "keyType");
/*  78 */     ensureNonNull(valueType, "valueType");
/*     */   }
/*     */   
/*     */   private static void ensureNonNull(Class<?> type, String name) {
/*  85 */     if (type == null)
/*  86 */       throw new NullPointerException(Errors.format(143, name)); 
/*     */   }
/*     */   
/*     */   private static <E> void ensureValidType(E element, Class<E> type) throws IllegalArgumentException {
/* 100 */     if (element != null && !type.isInstance(element))
/* 101 */       throw new IllegalArgumentException(Errors.format(61, element.getClass(), type)); 
/*     */   }
/*     */   
/*     */   protected void checkWritePermission() throws UnsupportedOperationException {
/* 118 */     assert Thread.holdsLock(getLock());
/*     */   }
/*     */   
/*     */   protected Object getLock() {
/* 131 */     return this;
/*     */   }
/*     */   
/*     */   public int size() {
/* 139 */     synchronized (getLock()) {
/* 140 */       return super.size();
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 149 */     synchronized (getLock()) {
/* 150 */       return super.isEmpty();
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean containsKey(Object key) {
/* 159 */     synchronized (getLock()) {
/* 160 */       return super.containsKey(key);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean containsValue(Object value) {
/* 169 */     synchronized (getLock()) {
/* 170 */       return super.containsValue(value);
/*     */     } 
/*     */   }
/*     */   
/*     */   public V get(Object key) {
/* 179 */     synchronized (getLock()) {
/* 180 */       return super.get(key);
/*     */     } 
/*     */   }
/*     */   
/*     */   public V put(K key, V value) throws IllegalArgumentException, UnsupportedOperationException {
/* 199 */     ensureValidType(key, this.keyType);
/* 200 */     ensureValidType(value, this.valueType);
/* 201 */     synchronized (getLock()) {
/* 202 */       checkWritePermission();
/* 203 */       return super.put(key, value);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void putAll(Map<? extends K, ? extends V> m) throws UnsupportedOperationException {
/* 214 */     for (Map.Entry<? extends K, ? extends V> entry : m.entrySet()) {
/* 215 */       ensureValidType(entry.getKey(), this.keyType);
/* 216 */       ensureValidType(entry.getValue(), this.valueType);
/*     */     } 
/* 218 */     synchronized (getLock()) {
/* 219 */       checkWritePermission();
/* 220 */       super.putAll(m);
/*     */     } 
/*     */   }
/*     */   
/*     */   public V remove(Object key) throws UnsupportedOperationException {
/* 231 */     synchronized (getLock()) {
/* 232 */       checkWritePermission();
/* 233 */       return super.remove(key);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void clear() throws UnsupportedOperationException {
/* 244 */     synchronized (getLock()) {
/* 245 */       checkWritePermission();
/* 246 */       super.clear();
/*     */     } 
/*     */   }
/*     */   
/*     */   public String toString() {
/* 255 */     synchronized (getLock()) {
/* 256 */       return super.toString();
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean equals(Object o) {
/* 265 */     synchronized (getLock()) {
/* 266 */       return super.equals(o);
/*     */     } 
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 275 */     synchronized (getLock()) {
/* 276 */       return super.hashCode();
/*     */     } 
/*     */   }
/*     */   
/*     */   public CheckedHashMap<K, V> clone() {
/* 288 */     synchronized (getLock()) {
/* 289 */       return (CheckedHashMap<K, V>)super.clone();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotool\\util\CheckedHashMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */