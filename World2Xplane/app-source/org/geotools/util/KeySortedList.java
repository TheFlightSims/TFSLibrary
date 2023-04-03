/*     */ package org.geotools.util;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.AbstractSequentialList;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.ListIterator;
/*     */ import java.util.Map;
/*     */ import java.util.NoSuchElementException;
/*     */ import java.util.SortedMap;
/*     */ import java.util.TreeMap;
/*     */ 
/*     */ public class KeySortedList<K extends Comparable<K>, V> extends AbstractSequentialList<V> implements Serializable {
/*     */   private static final long serialVersionUID = 6969483179756527012L;
/*     */   
/*     */   private final SortedMap<K, List<V>> map;
/*     */   
/*     */   public KeySortedList() {
/*  66 */     this.map = new TreeMap<K, List<V>>();
/*     */   }
/*     */   
/*     */   private KeySortedList(SortedMap<K, List<V>> map) {
/*  73 */     this.map = map;
/*     */   }
/*     */   
/*     */   public void clear() {
/*  81 */     this.map.clear();
/*     */   }
/*     */   
/*     */   public int size() {
/*  88 */     int count = 0;
/*  89 */     for (List<V> list : this.map.values())
/*  90 */       count += list.size(); 
/*  92 */     return count;
/*     */   }
/*     */   
/*     */   public void add(K key, V element) {
/* 105 */     List<V> values = this.map.get(key);
/* 106 */     if (values == null) {
/* 107 */       values = new ArrayList<V>();
/* 108 */       this.map.put(key, values);
/*     */     } 
/* 110 */     values.add(element);
/*     */   }
/*     */   
/*     */   public int removeAll(K key) {
/* 121 */     List<V> values = this.map.remove(key);
/* 122 */     return (values != null) ? values.size() : 0;
/*     */   }
/*     */   
/*     */   public int count(K key) {
/* 133 */     List<V> values = this.map.get(key);
/* 134 */     return (values != null) ? values.size() : 0;
/*     */   }
/*     */   
/*     */   public boolean containsKey(K key) {
/* 143 */     return this.map.containsKey(key);
/*     */   }
/*     */   
/*     */   public V first(K key) throws NoSuchElementException {
/* 155 */     List<V> values = this.map.get(key);
/* 156 */     if (values == null || values.isEmpty())
/* 157 */       throw new NoSuchElementException(); 
/* 159 */     return values.get(0);
/*     */   }
/*     */   
/*     */   public V last(K key) throws NoSuchElementException {
/* 171 */     List<V> values = this.map.get(key);
/* 172 */     if (values == null || values.isEmpty())
/* 173 */       throw new NoSuchElementException(); 
/* 175 */     return values.get(values.size() - 1);
/*     */   }
/*     */   
/*     */   public ListIterator<V> listIterator(K fromKey) {
/* 187 */     return new Iter(fromKey);
/*     */   }
/*     */   
/*     */   public ListIterator<V> listIterator(int index) {
/* 200 */     return new Iter(index);
/*     */   }
/*     */   
/*     */   private final class Iter implements ListIterator<V> {
/*     */     private Iterator<Map.Entry<K, List<V>>> entriesIter;
/*     */     
/*     */     private K key;
/*     */     
/*     */     private List<V> values;
/*     */     
/*     */     private ListIterator<V> valuesIter;
/*     */     
/*     */     private int base;
/*     */     
/*     */     public Iter(K fromKey) {
/* 236 */       this.entriesIter = KeySortedList.this.map.entrySet().iterator();
/* 237 */       while (this.entriesIter.hasNext()) {
/* 238 */         Map.Entry<K, List<V>> entry = this.entriesIter.next();
/* 239 */         this.key = entry.getKey();
/* 240 */         this.values = entry.getValue();
/* 241 */         if (fromKey.compareTo(this.key) <= 0) {
/* 242 */           this.valuesIter = this.values.listIterator();
/* 243 */           assert equals(new Iter(this.base));
/*     */           return;
/*     */         } 
/* 246 */         this.base += this.values.size();
/*     */       } 
/* 248 */       this.key = null;
/* 249 */       this.values = Collections.emptyList();
/* 250 */       this.valuesIter = this.values.listIterator();
/*     */     }
/*     */     
/*     */     public Iter(int index) {
/* 257 */       this.entriesIter = KeySortedList.this.map.entrySet().iterator();
/* 258 */       while (this.entriesIter.hasNext()) {
/* 259 */         Map.Entry<K, List<V>> entry = this.entriesIter.next();
/* 260 */         this.key = entry.getKey();
/* 261 */         this.values = entry.getValue();
/* 262 */         int size = this.values.size();
/* 263 */         if (index < size) {
/* 264 */           this.valuesIter = this.values.listIterator(index);
/*     */           return;
/*     */         } 
/* 267 */         index -= size;
/* 268 */         this.base += size;
/*     */       } 
/* 270 */       if (index != 0)
/* 271 */         throw new IndexOutOfBoundsException(); 
/* 273 */       this.key = null;
/* 274 */       this.values = Collections.emptyList();
/* 275 */       this.valuesIter = this.values.listIterator();
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/* 283 */       return (this.valuesIter.hasNext() || this.entriesIter.hasNext());
/*     */     }
/*     */     
/*     */     public V next() {
/* 290 */       while (!this.valuesIter.hasNext()) {
/* 291 */         if (this.entriesIter.hasNext()) {
/* 292 */           Map.Entry<K, List<V>> entry = this.entriesIter.next();
/* 293 */           this.base += this.values.size();
/* 294 */           this.key = entry.getKey();
/* 295 */           this.values = entry.getValue();
/* 296 */           this.valuesIter = this.values.listIterator();
/*     */           continue;
/*     */         } 
/* 298 */         this.key = null;
/* 299 */         this.values = Collections.emptyList();
/* 300 */         this.valuesIter = this.values.listIterator();
/*     */       } 
/* 304 */       return this.valuesIter.next();
/*     */     }
/*     */     
/*     */     public boolean hasPrevious() {
/* 312 */       return (this.valuesIter.hasPrevious() || this.base != 0);
/*     */     }
/*     */     
/*     */     public V previous() {
/* 319 */       while (!this.valuesIter.hasPrevious() && this.base != 0) {
/* 326 */         this.key = (K)KeySortedList.this.map.headMap(this.key).lastKey();
/* 327 */         this.entriesIter = KeySortedList.this.map.tailMap(this.key).entrySet().iterator();
/* 328 */         Map.Entry<K, List<V>> entry = this.entriesIter.next();
/* 329 */         assert this.key == entry.getKey() : this.key;
/* 334 */         this.values = entry.getValue();
/* 335 */         int size = this.values.size();
/* 336 */         this.valuesIter = this.values.listIterator(Math.max(size - 1, 0));
/* 337 */         this.base -= size;
/* 338 */         assert this.base >= 0 : this.base;
/*     */       } 
/* 340 */       return this.valuesIter.previous();
/*     */     }
/*     */     
/*     */     public int nextIndex() {
/* 348 */       return this.base + this.valuesIter.nextIndex();
/*     */     }
/*     */     
/*     */     public int previousIndex() {
/* 356 */       return this.base + this.valuesIter.previousIndex();
/*     */     }
/*     */     
/*     */     public void remove() {
/* 365 */       this.valuesIter.remove();
/*     */     }
/*     */     
/*     */     public void set(V o) {
/* 373 */       this.valuesIter.set(o);
/*     */     }
/*     */     
/*     */     public void add(V o) {
/* 381 */       this.valuesIter.add(o);
/*     */     }
/*     */     
/*     */     private boolean equals(Iter that) {
/* 389 */       return (this.key == that.key && this.values == that.values && this.base == that.base && this.valuesIter.nextIndex() == that.valuesIter.nextIndex());
/*     */     }
/*     */   }
/*     */   
/*     */   public KeySortedList<K, V> headList(K toKey) {
/* 405 */     return new KeySortedList(this.map.headMap(toKey));
/*     */   }
/*     */   
/*     */   public KeySortedList<K, V> tailList(K fromKey) {
/* 417 */     return new KeySortedList(this.map.tailMap(fromKey));
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotool\\util\KeySortedList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */