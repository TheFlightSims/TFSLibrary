/*     */ package org.geotools.util;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.AbstractMap;
/*     */ import java.util.Collection;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ public abstract class DerivedMap<BK, K, V> extends AbstractMap<K, V> implements Serializable {
/*     */   private static final long serialVersionUID = -6994867383669885934L;
/*     */   
/*     */   protected final Map<BK, V> base;
/*     */   
/*     */   private transient Set<K> keySet;
/*     */   
/*     */   private transient Set<Map.Entry<K, V>> entrySet;
/*     */   
/*     */   private final Class<K> keyType;
/*     */   
/*     */   public DerivedMap(Map<BK, V> base) {
/*  91 */     this(base, (Class)Object.class);
/*     */   }
/*     */   
/*     */   public DerivedMap(Map<BK, V> base, Class<K> keyType) {
/* 103 */     this.base = base;
/* 104 */     this.keyType = keyType;
/*     */   }
/*     */   
/*     */   public int size() {
/* 133 */     return super.size();
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 143 */     return (this.base.isEmpty() || super.isEmpty());
/*     */   }
/*     */   
/*     */   public boolean containsValue(Object value) {
/* 155 */     return this.base.containsValue(value);
/*     */   }
/*     */   
/*     */   public boolean containsKey(Object key) {
/* 168 */     if (this.keyType.isInstance(key))
/* 169 */       return this.base.containsKey(derivedToBase(this.keyType.cast(key))); 
/* 171 */     return false;
/*     */   }
/*     */   
/*     */   public V get(Object key) {
/* 185 */     if (this.keyType.isInstance(key))
/* 186 */       return this.base.get(derivedToBase(this.keyType.cast(key))); 
/* 188 */     return null;
/*     */   }
/*     */   
/*     */   public V put(K key, V value) throws UnsupportedOperationException {
/* 206 */     return this.base.put(derivedToBase(key), value);
/*     */   }
/*     */   
/*     */   public V remove(Object key) throws UnsupportedOperationException {
/* 222 */     if (this.keyType.isInstance(key))
/* 223 */       return this.base.remove(derivedToBase(this.keyType.cast(key))); 
/* 225 */     return null;
/*     */   }
/*     */   
/*     */   public Set<K> keySet() {
/* 236 */     if (this.keySet == null)
/* 237 */       this.keySet = new KeySet(this.base.keySet()); 
/* 239 */     return this.keySet;
/*     */   }
/*     */   
/*     */   public Collection<V> values() {
/* 249 */     return this.base.values();
/*     */   }
/*     */   
/*     */   public Set<Map.Entry<K, V>> entrySet() {
/* 260 */     if (this.entrySet == null)
/* 261 */       this.entrySet = new EntrySet(this.base.entrySet()); 
/* 263 */     return this.entrySet;
/*     */   }
/*     */   
/*     */   protected abstract K baseToDerived(BK paramBK);
/*     */   
/*     */   protected abstract BK derivedToBase(K paramK);
/*     */   
/*     */   private final class KeySet extends DerivedSet<BK, K> {
/*     */     private static final long serialVersionUID = -2931806200277420177L;
/*     */     
/*     */     public KeySet(Set<BK> base) {
/* 273 */       super(base, DerivedMap.this.keyType);
/*     */     }
/*     */     
/*     */     protected K baseToDerived(BK element) {
/* 277 */       return (K)DerivedMap.this.baseToDerived(element);
/*     */     }
/*     */     
/*     */     protected BK derivedToBase(K element) {
/* 281 */       return (BK)DerivedMap.this.derivedToBase(element);
/*     */     }
/*     */   }
/*     */   
/*     */   private final class EntrySet extends DerivedSet<Map.Entry<BK, V>, Entry<BK, K, V>> {
/*     */     private static final long serialVersionUID = -2931806200277420177L;
/*     */     
/*     */     public EntrySet(Set<Map.Entry<BK, V>> base) {
/* 293 */       super(base, (Class)DerivedMap.Entry.class);
/*     */     }
/*     */     
/*     */     protected DerivedMap.Entry<BK, K, V> baseToDerived(Map.Entry<BK, V> entry) {
/* 297 */       K derived = (K)DerivedMap.this.baseToDerived(entry.getKey());
/* 298 */       return (derived != null) ? new DerivedMap.Entry<BK, K, V>(entry, derived) : null;
/*     */     }
/*     */     
/*     */     protected Map.Entry<BK, V> derivedToBase(DerivedMap.Entry<BK, K, V> element) {
/* 302 */       return element.entry;
/*     */     }
/*     */   }
/*     */   
/*     */   private static final class Entry<BK, K, V> implements Map.Entry<K, V> {
/*     */     public final Map.Entry<BK, V> entry;
/*     */     
/*     */     private final K derived;
/*     */     
/*     */     public Entry(Map.Entry<BK, V> entry, K derived) {
/* 314 */       this.entry = entry;
/* 315 */       this.derived = derived;
/*     */     }
/*     */     
/*     */     public K getKey() {
/* 319 */       return this.derived;
/*     */     }
/*     */     
/*     */     public V getValue() {
/* 323 */       return this.entry.getValue();
/*     */     }
/*     */     
/*     */     public V setValue(V value) {
/* 327 */       return this.entry.setValue(value);
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotool\\util\DerivedMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */