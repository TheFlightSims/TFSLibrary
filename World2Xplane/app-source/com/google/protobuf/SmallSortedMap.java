/*     */ package com.google.protobuf;
/*     */ 
/*     */ import java.util.AbstractMap;
/*     */ import java.util.AbstractSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.NoSuchElementException;
/*     */ import java.util.Set;
/*     */ import java.util.SortedMap;
/*     */ import java.util.TreeMap;
/*     */ 
/*     */ class SmallSortedMap<K extends Comparable<K>, V> extends AbstractMap<K, V> {
/*     */   private final int maxArraySize;
/*     */   
/*     */   private List<Entry> entryList;
/*     */   
/*     */   private Map<K, V> overflowEntries;
/*     */   
/*     */   private boolean isImmutable;
/*     */   
/*     */   private volatile EntrySet lazyEntrySet;
/*     */   
/*     */   static <FieldDescriptorType extends FieldSet.FieldDescriptorLite<FieldDescriptorType>> SmallSortedMap<FieldDescriptorType, Object> newFieldMap(int arraySize) {
/* 100 */     return new SmallSortedMap<FieldDescriptorType, Object>(arraySize) {
/*     */         public void makeImmutable() {
/* 104 */           if (!isImmutable()) {
/* 105 */             for (int i = 0; i < getNumArrayEntries(); i++) {
/* 106 */               Map.Entry<FieldDescriptorType, Object> entry = getArrayEntryAt(i);
/* 108 */               if (((FieldSet.FieldDescriptorLite)entry.getKey()).isRepeated()) {
/* 109 */                 List<?> value = (List)entry.getValue();
/* 110 */                 entry.setValue(Collections.unmodifiableList(value));
/*     */               } 
/*     */             } 
/* 114 */             for (Map.Entry<FieldDescriptorType, Object> entry : getOverflowEntries()) {
/* 115 */               if (((FieldSet.FieldDescriptorLite)entry.getKey()).isRepeated()) {
/* 116 */                 List<?> value = (List)entry.getValue();
/* 117 */                 entry.setValue(Collections.unmodifiableList(value));
/*     */               } 
/*     */             } 
/*     */           } 
/* 121 */           super.makeImmutable();
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   static <K extends Comparable<K>, V> SmallSortedMap<K, V> newInstanceForTest(int arraySize) {
/* 134 */     return new SmallSortedMap<K, V>(arraySize);
/*     */   }
/*     */   
/*     */   private SmallSortedMap(int arraySize) {
/* 154 */     this.maxArraySize = arraySize;
/* 155 */     this.entryList = Collections.emptyList();
/* 156 */     this.overflowEntries = Collections.emptyMap();
/*     */   }
/*     */   
/*     */   public void makeImmutable() {
/* 161 */     if (!this.isImmutable) {
/* 166 */       this.overflowEntries = this.overflowEntries.isEmpty() ? Collections.<K, V>emptyMap() : Collections.<K, V>unmodifiableMap(this.overflowEntries);
/* 169 */       this.isImmutable = true;
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean isImmutable() {
/* 175 */     return this.isImmutable;
/*     */   }
/*     */   
/*     */   public int getNumArrayEntries() {
/* 180 */     return this.entryList.size();
/*     */   }
/*     */   
/*     */   public Map.Entry<K, V> getArrayEntryAt(int index) {
/* 185 */     return this.entryList.get(index);
/*     */   }
/*     */   
/*     */   public int getNumOverflowEntries() {
/* 190 */     return this.overflowEntries.size();
/*     */   }
/*     */   
/*     */   public Iterable<Map.Entry<K, V>> getOverflowEntries() {
/* 195 */     return this.overflowEntries.isEmpty() ? EmptySet.<Map.Entry<K, V>>iterable() : this.overflowEntries.entrySet();
/*     */   }
/*     */   
/*     */   public int size() {
/* 202 */     return this.entryList.size() + this.overflowEntries.size();
/*     */   }
/*     */   
/*     */   public boolean containsKey(Object o) {
/* 214 */     Comparable comparable = (Comparable)o;
/* 215 */     return (binarySearchInArray((K)comparable) >= 0 || this.overflowEntries.containsKey(comparable));
/*     */   }
/*     */   
/*     */   public V get(Object o) {
/* 227 */     Comparable comparable = (Comparable)o;
/* 228 */     int index = binarySearchInArray((K)comparable);
/* 229 */     if (index >= 0)
/* 230 */       return ((Entry)this.entryList.get(index)).getValue(); 
/* 232 */     return this.overflowEntries.get(comparable);
/*     */   }
/*     */   
/*     */   public V put(K key, V value) {
/* 237 */     checkMutable();
/* 238 */     int index = binarySearchInArray(key);
/* 239 */     if (index >= 0)
/* 241 */       return ((Entry)this.entryList.get(index)).setValue(value); 
/* 243 */     ensureEntryArrayMutable();
/* 244 */     int insertionPoint = -(index + 1);
/* 245 */     if (insertionPoint >= this.maxArraySize)
/* 247 */       return getOverflowEntriesMutable().put(key, value); 
/* 250 */     if (this.entryList.size() == this.maxArraySize) {
/* 252 */       Entry lastEntryInArray = this.entryList.remove(this.maxArraySize - 1);
/* 253 */       getOverflowEntriesMutable().put(lastEntryInArray.getKey(), lastEntryInArray.getValue());
/*     */     } 
/* 256 */     this.entryList.add(insertionPoint, new Entry(key, value));
/* 257 */     return null;
/*     */   }
/*     */   
/*     */   public void clear() {
/* 262 */     checkMutable();
/* 263 */     if (!this.entryList.isEmpty())
/* 264 */       this.entryList.clear(); 
/* 266 */     if (!this.overflowEntries.isEmpty())
/* 267 */       this.overflowEntries.clear(); 
/*     */   }
/*     */   
/*     */   public V remove(Object o) {
/* 279 */     checkMutable();
/* 281 */     Comparable comparable = (Comparable)o;
/* 282 */     int index = binarySearchInArray((K)comparable);
/* 283 */     if (index >= 0)
/* 284 */       return removeArrayEntryAt(index); 
/* 288 */     if (this.overflowEntries.isEmpty())
/* 289 */       return null; 
/* 291 */     return this.overflowEntries.remove(comparable);
/*     */   }
/*     */   
/*     */   private V removeArrayEntryAt(int index) {
/* 296 */     checkMutable();
/* 297 */     V removed = ((Entry)this.entryList.remove(index)).getValue();
/* 298 */     if (!this.overflowEntries.isEmpty()) {
/* 301 */       Iterator<Map.Entry<K, V>> iterator = getOverflowEntriesMutable().entrySet().iterator();
/* 303 */       this.entryList.add(new Entry(iterator.next()));
/* 304 */       iterator.remove();
/*     */     } 
/* 306 */     return removed;
/*     */   }
/*     */   
/*     */   private int binarySearchInArray(K key) {
/* 315 */     int left = 0;
/* 316 */     int right = this.entryList.size() - 1;
/* 321 */     if (right >= 0) {
/* 322 */       int cmp = key.compareTo(((Entry)this.entryList.get(right)).getKey());
/* 323 */       if (cmp > 0)
/* 324 */         return -(right + 2); 
/* 325 */       if (cmp == 0)
/* 326 */         return right; 
/*     */     } 
/* 330 */     while (left <= right) {
/* 331 */       int mid = (left + right) / 2;
/* 332 */       int cmp = key.compareTo(((Entry)this.entryList.get(mid)).getKey());
/* 333 */       if (cmp < 0) {
/* 334 */         right = mid - 1;
/*     */         continue;
/*     */       } 
/* 335 */       if (cmp > 0) {
/* 336 */         left = mid + 1;
/*     */         continue;
/*     */       } 
/* 338 */       return mid;
/*     */     } 
/* 341 */     return -(left + 1);
/*     */   }
/*     */   
/*     */   public Set<Map.Entry<K, V>> entrySet() {
/* 353 */     if (this.lazyEntrySet == null)
/* 354 */       this.lazyEntrySet = new EntrySet(); 
/* 356 */     return this.lazyEntrySet;
/*     */   }
/*     */   
/*     */   private void checkMutable() {
/* 364 */     if (this.isImmutable)
/* 365 */       throw new UnsupportedOperationException(); 
/*     */   }
/*     */   
/*     */   private SortedMap<K, V> getOverflowEntriesMutable() {
/* 377 */     checkMutable();
/* 378 */     if (this.overflowEntries.isEmpty() && !(this.overflowEntries instanceof TreeMap))
/* 379 */       this.overflowEntries = new TreeMap<K, V>(); 
/* 381 */     return (SortedMap<K, V>)this.overflowEntries;
/*     */   }
/*     */   
/*     */   private void ensureEntryArrayMutable() {
/* 389 */     checkMutable();
/* 390 */     if (this.entryList.isEmpty() && !(this.entryList instanceof ArrayList))
/* 391 */       this.entryList = new ArrayList<Entry>(this.maxArraySize); 
/*     */   }
/*     */   
/*     */   private class Entry implements Map.Entry<K, V>, Comparable<Entry> {
/*     */     private final K key;
/*     */     
/*     */     private V value;
/*     */     
/*     */     Entry(Map.Entry<K, V> copy) {
/* 406 */       this(copy.getKey(), copy.getValue());
/*     */     }
/*     */     
/*     */     Entry(K key, V value) {
/* 410 */       this.key = key;
/* 411 */       this.value = value;
/*     */     }
/*     */     
/*     */     public K getKey() {
/* 416 */       return this.key;
/*     */     }
/*     */     
/*     */     public V getValue() {
/* 421 */       return this.value;
/*     */     }
/*     */     
/*     */     public int compareTo(Entry other) {
/* 426 */       return getKey().compareTo(other.getKey());
/*     */     }
/*     */     
/*     */     public V setValue(V newValue) {
/* 431 */       SmallSortedMap.this.checkMutable();
/* 432 */       V oldValue = this.value;
/* 433 */       this.value = newValue;
/* 434 */       return oldValue;
/*     */     }
/*     */     
/*     */     public boolean equals(Object o) {
/* 439 */       if (o == this)
/* 440 */         return true; 
/* 442 */       if (!(o instanceof Map.Entry))
/* 443 */         return false; 
/* 446 */       Map.Entry<?, ?> other = (Map.Entry<?, ?>)o;
/* 447 */       return (equals(this.key, other.getKey()) && equals(this.value, other.getValue()));
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 452 */       return ((this.key == null) ? 0 : this.key.hashCode()) ^ ((this.value == null) ? 0 : this.value.hashCode());
/*     */     }
/*     */     
/*     */     public String toString() {
/* 458 */       return (new StringBuilder()).append(this.key).append("=").append(this.value).toString();
/*     */     }
/*     */     
/*     */     private boolean equals(Object o1, Object o2) {
/* 463 */       return (o1 == null) ? ((o2 == null)) : o1.equals(o2);
/*     */     }
/*     */   }
/*     */   
/*     */   private class EntrySet extends AbstractSet<Map.Entry<K, V>> {
/*     */     private EntrySet() {}
/*     */     
/*     */     public Iterator<Map.Entry<K, V>> iterator() {
/* 474 */       return new SmallSortedMap.EntryIterator();
/*     */     }
/*     */     
/*     */     public int size() {
/* 479 */       return SmallSortedMap.this.size();
/*     */     }
/*     */     
/*     */     public boolean contains(Object o) {
/* 490 */       Map.Entry<K, V> entry = (Map.Entry<K, V>)o;
/* 491 */       V existing = (V)SmallSortedMap.this.get(entry.getKey());
/* 492 */       V value = entry.getValue();
/* 493 */       return (existing == value || (existing != null && existing.equals(value)));
/*     */     }
/*     */     
/*     */     public boolean add(Map.Entry<K, V> entry) {
/* 499 */       if (!contains(entry)) {
/* 500 */         SmallSortedMap.this.put((Comparable)entry.getKey(), entry.getValue());
/* 501 */         return true;
/*     */       } 
/* 503 */       return false;
/*     */     }
/*     */     
/*     */     public boolean remove(Object o) {
/* 514 */       Map.Entry<K, V> entry = (Map.Entry<K, V>)o;
/* 515 */       if (contains(entry)) {
/* 516 */         SmallSortedMap.this.remove(entry.getKey());
/* 517 */         return true;
/*     */       } 
/* 519 */       return false;
/*     */     }
/*     */     
/*     */     public void clear() {
/* 524 */       SmallSortedMap.this.clear();
/*     */     }
/*     */   }
/*     */   
/*     */   private class EntryIterator implements Iterator<Map.Entry<K, V>> {
/* 534 */     private int pos = -1;
/*     */     
/*     */     private boolean nextCalledBeforeRemove;
/*     */     
/*     */     private Iterator<Map.Entry<K, V>> lazyOverflowIterator;
/*     */     
/*     */     public boolean hasNext() {
/* 540 */       return (this.pos + 1 < SmallSortedMap.access$400(SmallSortedMap.this).size() || getOverflowIterator().hasNext());
/*     */     }
/*     */     
/*     */     public Map.Entry<K, V> next() {
/* 546 */       this.nextCalledBeforeRemove = true;
/* 549 */       if (++this.pos < SmallSortedMap.access$400(SmallSortedMap.this).size())
/* 550 */         return SmallSortedMap.access$400(SmallSortedMap.this).get(this.pos); 
/* 552 */       return getOverflowIterator().next();
/*     */     }
/*     */     
/*     */     public void remove() {
/* 557 */       if (!this.nextCalledBeforeRemove)
/* 558 */         throw new IllegalStateException("remove() was called before next()"); 
/* 560 */       this.nextCalledBeforeRemove = false;
/* 561 */       SmallSortedMap.this.checkMutable();
/* 563 */       if (this.pos < SmallSortedMap.access$400(SmallSortedMap.this).size()) {
/* 564 */         SmallSortedMap.this.removeArrayEntryAt(this.pos--);
/*     */       } else {
/* 566 */         getOverflowIterator().remove();
/*     */       } 
/*     */     }
/*     */     
/*     */     private Iterator<Map.Entry<K, V>> getOverflowIterator() {
/* 577 */       if (this.lazyOverflowIterator == null)
/* 578 */         this.lazyOverflowIterator = SmallSortedMap.this.overflowEntries.entrySet().iterator(); 
/* 580 */       return this.lazyOverflowIterator;
/*     */     }
/*     */     
/*     */     private EntryIterator() {}
/*     */   }
/*     */   
/*     */   private static class EmptySet {
/* 591 */     private static final Iterator<Object> ITERATOR = new Iterator() {
/*     */         public boolean hasNext() {
/* 594 */           return false;
/*     */         }
/*     */         
/*     */         public Object next() {
/* 598 */           throw new NoSuchElementException();
/*     */         }
/*     */         
/*     */         public void remove() {
/* 602 */           throw new UnsupportedOperationException();
/*     */         }
/*     */       };
/*     */     
/* 606 */     private static final Iterable<Object> ITERABLE = new Iterable() {
/*     */         public Iterator<Object> iterator() {
/* 609 */           return SmallSortedMap.EmptySet.ITERATOR;
/*     */         }
/*     */       };
/*     */     
/*     */     static <T> Iterable<T> iterable() {
/* 615 */       return (Iterable)ITERABLE;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\google\protobuf\SmallSortedMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */