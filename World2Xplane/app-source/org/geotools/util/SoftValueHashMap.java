/*     */ package org.geotools.util;
/*     */ 
/*     */ import java.lang.ref.SoftReference;
/*     */ import java.util.AbstractMap;
/*     */ import java.util.AbstractSet;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedList;
/*     */ import java.util.Map;
/*     */ import java.util.NoSuchElementException;
/*     */ import java.util.Set;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ import org.geotools.util.logging.Logging;
/*     */ 
/*     */ public class SoftValueHashMap<K, V> extends AbstractMap<K, V> {
/*  65 */   static final Logger LOGGER = Logging.getLogger(SoftValueHashMap.class);
/*     */   
/*     */   private static final int DEFAULT_HARD_REFERENCE_COUNT = 20;
/*     */   
/*  76 */   private final Map<K, Object> hash = new HashMap<K, Object>();
/*     */   
/*  82 */   private final LinkedList<K> hardCache = new LinkedList<K>();
/*     */   
/*     */   private final int hardReferencesCount;
/*     */   
/*     */   private transient Set<Map.Entry<K, V>> entries;
/*     */   
/*     */   protected ValueCleaner cleaner;
/*     */   
/*     */   public SoftValueHashMap() {
/* 103 */     this.cleaner = null;
/* 104 */     this.hardReferencesCount = 20;
/*     */   }
/*     */   
/*     */   public SoftValueHashMap(int hardReferencesCount) {
/* 113 */     this.cleaner = null;
/* 114 */     this.hardReferencesCount = hardReferencesCount;
/*     */   }
/*     */   
/*     */   public SoftValueHashMap(int hardReferencesCount, ValueCleaner cleaner) {
/* 123 */     this.cleaner = cleaner;
/* 124 */     this.hardReferencesCount = hardReferencesCount;
/*     */   }
/*     */   
/*     */   public int getHardReferencesCount() {
/* 132 */     return this.hardReferencesCount;
/*     */   }
/*     */   
/*     */   private static void ensureNotNull(Object value) throws IllegalArgumentException {
/* 139 */     if (value == null)
/* 140 */       throw new IllegalArgumentException(Errors.format(143, "value")); 
/*     */   }
/*     */   
/*     */   final boolean isValid() {
/* 149 */     int count = 0, size = 0;
/* 150 */     synchronized (this.hash) {
/* 151 */       for (Map.Entry<K, ?> entry : this.hash.entrySet()) {
/* 152 */         if (entry.getValue() instanceof Reference) {
/* 153 */           count++;
/*     */         } else {
/* 155 */           assert this.hardCache.contains(entry.getKey());
/*     */         } 
/* 157 */         size++;
/*     */       } 
/* 159 */       assert size == this.hash.size();
/* 160 */       assert this.hardCache.size() == Math.min(size, this.hardReferencesCount);
/*     */     } 
/* 162 */     return (count == Math.max(size - this.hardReferencesCount, 0));
/*     */   }
/*     */   
/*     */   public int size() {
/* 170 */     synchronized (this.hash) {
/* 171 */       return this.hash.size();
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean containsKey(Object key) {
/* 180 */     synchronized (this.hash) {
/* 181 */       return this.hash.containsKey(key);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean containsValue(Object value) {
/* 190 */     ensureNotNull(value);
/* 191 */     synchronized (this.hash) {
/* 196 */       return super.containsValue(value);
/*     */     } 
/*     */   }
/*     */   
/*     */   public V get(Object key) {
/* 209 */     synchronized (this.hash) {
/* 210 */       Object value = this.hash.get(key);
/* 211 */       if (value instanceof Reference) {
/* 222 */         value = ((Reference)value).getAndClear();
/* 223 */         if (value != null) {
/* 230 */           K k = (K)key;
/* 231 */           this.hash.put(k, value);
/* 232 */           retainStrongly(k);
/*     */         } else {
/* 235 */           this.hash.remove(key);
/*     */         } 
/*     */       } 
/* 243 */       V v = (V)value;
/* 244 */       return v;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void retainStrongly(K key) {
/* 254 */     assert Thread.holdsLock(this.hash);
/* 255 */     assert !this.hardCache.contains(key) : key;
/* 256 */     this.hardCache.addFirst(key);
/* 257 */     if (this.hardCache.size() > this.hardReferencesCount) {
/* 259 */       K toRemove = this.hardCache.removeLast();
/* 260 */       Object value = this.hash.get(toRemove);
/* 261 */       assert value != null && !(value instanceof Reference) : toRemove;
/* 263 */       V v = (V)value;
/* 264 */       this.hash.put(toRemove, new Reference<K, V>(this.hash, toRemove, v, this.cleaner));
/* 265 */       assert this.hardCache.size() == this.hardReferencesCount;
/*     */     } 
/* 267 */     assert isValid();
/*     */   }
/*     */   
/*     */   public V put(K key, V value) {
/* 281 */     ensureNotNull(value);
/* 282 */     synchronized (this.hash) {
/* 283 */       Object oldValue = this.hash.put(key, value);
/* 284 */       if (oldValue instanceof Reference) {
/* 285 */         oldValue = ((Reference)oldValue).getAndClear();
/* 286 */       } else if (oldValue != null) {
/* 296 */         if (!this.hardCache.remove(key))
/* 297 */           throw new AssertionError(key); 
/*     */       } 
/* 300 */       retainStrongly(key);
/* 302 */       V v = (V)oldValue;
/* 303 */       return v;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void putAll(Map<? extends K, ? extends V> map) {
/* 314 */     synchronized (this.hash) {
/* 315 */       super.putAll(map);
/*     */     } 
/*     */   }
/*     */   
/*     */   public V remove(Object key) {
/* 328 */     synchronized (this.hash) {
/* 329 */       Object oldValue = this.hash.remove(key);
/* 330 */       if (oldValue instanceof Reference) {
/* 331 */         oldValue = ((Reference)oldValue).getAndClear();
/* 332 */       } else if (oldValue != null) {
/* 336 */         if (!this.hardCache.remove(key))
/* 337 */           throw new AssertionError(key); 
/*     */       } 
/* 341 */       V v = (V)oldValue;
/* 342 */       return v;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void clear() {
/* 351 */     synchronized (this.hash) {
/* 352 */       for (Iterator it = this.hash.values().iterator(); it.hasNext(); ) {
/* 353 */         Object value = it.next();
/* 354 */         if (value instanceof Reference)
/* 355 */           ((Reference)value).getAndClear(); 
/*     */       } 
/* 358 */       this.hash.clear();
/* 359 */       this.hardCache.clear();
/*     */     } 
/*     */   }
/*     */   
/*     */   public Set<Map.Entry<K, V>> entrySet() {
/* 368 */     synchronized (this.hash) {
/* 369 */       if (this.entries == null)
/* 370 */         this.entries = new Entries(); 
/* 372 */       return this.entries;
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/* 383 */     synchronized (this.hash) {
/* 384 */       return super.equals(object);
/*     */     } 
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 393 */     synchronized (this.hash) {
/* 394 */       return super.hashCode();
/*     */     } 
/*     */   }
/*     */   
/*     */   public String toString() {
/* 403 */     synchronized (this.hash) {
/* 404 */       return super.toString();
/*     */     } 
/*     */   }
/*     */   
/*     */   private final class Entries extends AbstractSet<Map.Entry<K, V>> {
/*     */     private Entries() {}
/*     */     
/*     */     public Iterator<Map.Entry<K, V>> iterator() {
/* 416 */       synchronized (SoftValueHashMap.this.hash) {
/* 417 */         return new SoftValueHashMap.Iter<K, V>(SoftValueHashMap.this.hash);
/*     */       } 
/*     */     }
/*     */     
/*     */     public int size() {
/* 425 */       return SoftValueHashMap.this.size();
/*     */     }
/*     */     
/*     */     public boolean contains(Object entry) {
/* 433 */       synchronized (SoftValueHashMap.this.hash) {
/* 434 */         return super.contains(entry);
/*     */       } 
/*     */     }
/*     */     
/*     */     public Object[] toArray() {
/* 443 */       synchronized (SoftValueHashMap.this.hash) {
/* 444 */         return super.toArray();
/*     */       } 
/*     */     }
/*     */     
/*     */     public <T> T[] toArray(T[] array) {
/* 453 */       synchronized (SoftValueHashMap.this.hash) {
/* 454 */         return (T[])super.toArray((Object[])array);
/*     */       } 
/*     */     }
/*     */     
/*     */     public boolean remove(Object entry) {
/* 464 */       synchronized (SoftValueHashMap.this.hash) {
/* 465 */         return super.remove(entry);
/*     */       } 
/*     */     }
/*     */     
/*     */     public boolean containsAll(Collection<?> collection) {
/* 475 */       synchronized (SoftValueHashMap.this.hash) {
/* 476 */         return super.containsAll(collection);
/*     */       } 
/*     */     }
/*     */     
/*     */     public boolean addAll(Collection<? extends Map.Entry<K, V>> collection) {
/* 485 */       synchronized (SoftValueHashMap.this.hash) {
/* 486 */         return super.addAll(collection);
/*     */       } 
/*     */     }
/*     */     
/*     */     public boolean removeAll(Collection<?> collection) {
/* 496 */       synchronized (SoftValueHashMap.this.hash) {
/* 497 */         return super.removeAll(collection);
/*     */       } 
/*     */     }
/*     */     
/*     */     public boolean retainAll(Collection<?> collection) {
/* 507 */       synchronized (SoftValueHashMap.this.hash) {
/* 508 */         return super.retainAll(collection);
/*     */       } 
/*     */     }
/*     */     
/*     */     public void clear() {
/* 517 */       SoftValueHashMap.this.clear();
/*     */     }
/*     */     
/*     */     public String toString() {
/* 525 */       synchronized (SoftValueHashMap.this.hash) {
/* 526 */         return super.toString();
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   private static final class Iter<K, V> implements Iterator<Map.Entry<K, V>> {
/*     */     private final Map<K, Object> hash;
/*     */     
/*     */     private final Iterator<Map.Entry<K, Object>> iterator;
/*     */     
/*     */     private transient Map.Entry<K, V> entry;
/*     */     
/*     */     Iter(Map<K, Object> hash) {
/* 555 */       this.hash = hash;
/* 556 */       this.iterator = hash.entrySet().iterator();
/*     */     }
/*     */     
/*     */     private boolean findNext() {
/* 565 */       assert Thread.holdsLock(this.hash);
/* 566 */       while (this.iterator.hasNext()) {
/* 567 */         Map.Entry<K, Object> candidate = this.iterator.next();
/* 568 */         Object value = candidate.getValue();
/* 569 */         if (value instanceof SoftValueHashMap.Reference) {
/* 570 */           value = ((SoftValueHashMap.Reference)value).get();
/* 571 */           this.entry = new MapEntry<K, V>(candidate.getKey(), (V)value);
/* 572 */           return true;
/*     */         } 
/* 574 */         if (value != null) {
/* 575 */           this.entry = (Map.Entry)candidate;
/* 576 */           return true;
/*     */         } 
/*     */       } 
/* 579 */       return false;
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/* 586 */       synchronized (this.hash) {
/* 587 */         return (this.entry != null || findNext());
/*     */       } 
/*     */     }
/*     */     
/*     */     public Map.Entry<K, V> next() {
/* 598 */       synchronized (this.hash) {
/* 599 */         if (this.entry == null && !findNext())
/* 600 */           throw new NoSuchElementException(); 
/* 602 */         Map.Entry<K, V> next = this.entry;
/* 603 */         this.entry = null;
/* 604 */         return next;
/*     */       } 
/*     */     }
/*     */     
/*     */     public void remove() {
/* 612 */       synchronized (this.hash) {
/* 613 */         this.iterator.remove();
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   private static final class Reference<K, V> extends SoftReference<V> {
/*     */     private final Map<K, Object> hash;
/*     */     
/*     */     private final K key;
/*     */     
/*     */     private SoftValueHashMap.ValueCleaner cleaner;
/*     */     
/*     */     Reference(Map<K, Object> hash, K key, V value, SoftValueHashMap.ValueCleaner cleaner) {
/* 645 */       super(value, WeakCollectionCleaner.DEFAULT.referenceQueue);
/* 646 */       this.hash = hash;
/* 647 */       this.key = key;
/* 648 */       this.cleaner = cleaner;
/*     */     }
/*     */     
/*     */     final Object getAndClear() {
/* 663 */       assert Thread.holdsLock(this.hash);
/* 664 */       Object value = get();
/* 665 */       super.clear();
/* 666 */       return value;
/*     */     }
/*     */     
/*     */     public void clear() {
/* 676 */       if (this.cleaner != null) {
/* 677 */         Object value = get();
/* 678 */         if (value != null)
/*     */           try {
/* 680 */             this.cleaner.clean(this.key, value);
/* 681 */           } catch (Throwable t) {
/* 683 */             SoftValueHashMap.LOGGER.log(Level.SEVERE, "Exception occurred while cleaning soft referenced object", t);
/*     */           }  
/*     */       } 
/* 688 */       super.clear();
/* 689 */       synchronized (this.hash) {
/* 690 */         Object old = this.hash.remove(this.key);
/* 696 */         if (old != this && old != null)
/* 697 */           this.hash.put(this.key, old); 
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public static interface ValueCleaner {
/*     */     void clean(Object param1Object1, Object param1Object2);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotool\\util\SoftValueHashMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */