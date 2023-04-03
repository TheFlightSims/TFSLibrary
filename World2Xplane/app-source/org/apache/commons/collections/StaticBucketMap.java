/*     */ package org.apache.commons.collections;
/*     */ 
/*     */ import java.util.AbstractCollection;
/*     */ import java.util.AbstractSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.NoSuchElementException;
/*     */ import java.util.Set;
/*     */ 
/*     */ public final class StaticBucketMap implements Map {
/*     */   private static final int DEFAULT_BUCKETS = 255;
/*     */   
/*     */   private Node[] m_buckets;
/*     */   
/*     */   private Lock[] m_locks;
/*     */   
/*     */   public StaticBucketMap() {
/* 114 */     this(255);
/*     */   }
/*     */   
/*     */   public StaticBucketMap(int numBuckets) {
/* 129 */     int size = Math.max(17, numBuckets);
/* 132 */     if (size % 2 == 0)
/* 134 */       size--; 
/* 137 */     this.m_buckets = new Node[size];
/* 138 */     this.m_locks = new Lock[size];
/* 140 */     for (int i = 0; i < size; i++)
/* 142 */       this.m_locks[i] = new Lock(); 
/*     */   }
/*     */   
/*     */   private final int getHash(Object key) {
/* 161 */     if (key == null)
/* 161 */       return 0; 
/* 162 */     int hash = key.hashCode();
/* 163 */     hash += hash << 15 ^ 0xFFFFFFFF;
/* 164 */     hash ^= hash >>> 10;
/* 165 */     hash += hash << 3;
/* 166 */     hash ^= hash >>> 6;
/* 167 */     hash += hash << 11 ^ 0xFFFFFFFF;
/* 168 */     hash ^= hash >>> 16;
/* 169 */     hash %= this.m_buckets.length;
/* 170 */     return (hash < 0) ? (hash * -1) : hash;
/*     */   }
/*     */   
/*     */   public Set keySet() {
/* 178 */     return new KeySet();
/*     */   }
/*     */   
/*     */   public int size() {
/* 186 */     int cnt = 0;
/* 188 */     for (int i = 0; i < this.m_buckets.length; i++)
/* 190 */       cnt += (this.m_locks[i]).size; 
/* 193 */     return cnt;
/*     */   }
/*     */   
/*     */   public Object put(Object key, Object value) {
/* 201 */     int hash = getHash(key);
/* 203 */     synchronized (this.m_locks[hash]) {
/* 205 */       Node n = this.m_buckets[hash];
/* 207 */       if (n == null) {
/* 209 */         n = new Node();
/* 210 */         n.key = key;
/* 211 */         n.value = value;
/* 212 */         this.m_buckets[hash] = n;
/* 213 */         (this.m_locks[hash]).size++;
/* 214 */         return null;
/*     */       } 
/* 220 */       for (Node next = n; next != null; next = next.next) {
/* 222 */         n = next;
/* 224 */         if (n.key == key || (n.key != null && n.key.equals(key))) {
/* 226 */           Object returnVal = n.value;
/* 227 */           n.value = value;
/* 228 */           return returnVal;
/*     */         } 
/*     */       } 
/* 234 */       Node newNode = new Node();
/* 235 */       newNode.key = key;
/* 236 */       newNode.value = value;
/* 237 */       n.next = newNode;
/* 238 */       (this.m_locks[hash]).size++;
/*     */     } 
/* 241 */     return null;
/*     */   }
/*     */   
/*     */   public Object get(Object key) {
/* 249 */     int hash = getHash(key);
/* 251 */     synchronized (this.m_locks[hash]) {
/* 253 */       Node n = this.m_buckets[hash];
/* 255 */       while (n != null) {
/* 257 */         if (n.key == key || (n.key != null && n.key.equals(key)))
/* 259 */           return n.value; 
/* 262 */         n = n.next;
/*     */       } 
/*     */     } 
/* 266 */     return null;
/*     */   }
/*     */   
/*     */   public boolean containsKey(Object key) {
/* 274 */     int hash = getHash(key);
/* 276 */     synchronized (this.m_locks[hash]) {
/* 278 */       Node n = this.m_buckets[hash];
/* 280 */       while (n != null) {
/* 282 */         if (n.key == key || (n.key != null && n.key.equals(key)))
/* 284 */           return true; 
/* 287 */         n = n.next;
/*     */       } 
/*     */     } 
/* 291 */     return false;
/*     */   }
/*     */   
/*     */   public boolean containsValue(Object value) {
/* 299 */     for (int i = 0; i < this.m_buckets.length; i++) {
/* 301 */       synchronized (this.m_locks[i]) {
/* 303 */         Node n = this.m_buckets[i];
/* 305 */         while (n != null) {
/* 307 */           if (n.value == value || (n.value != null && n.value.equals(value)))
/* 310 */             return true; 
/* 313 */           n = n.next;
/*     */         } 
/*     */       } 
/*     */     } 
/* 318 */     return false;
/*     */   }
/*     */   
/*     */   public Collection values() {
/* 326 */     return new Values();
/*     */   }
/*     */   
/*     */   public Set entrySet() {
/* 334 */     return new EntrySet();
/*     */   }
/*     */   
/*     */   public void putAll(Map other) {
/* 342 */     Iterator i = other.keySet().iterator();
/* 344 */     while (i.hasNext()) {
/* 346 */       Object key = i.next();
/* 347 */       put(key, other.get(key));
/*     */     } 
/*     */   }
/*     */   
/*     */   public Object remove(Object key) {
/* 356 */     int hash = getHash(key);
/* 358 */     synchronized (this.m_locks[hash]) {
/* 360 */       Node n = this.m_buckets[hash];
/* 361 */       Node prev = null;
/* 363 */       while (n != null) {
/* 365 */         if (n.key == key || (n.key != null && n.key.equals(key))) {
/* 368 */           if (null == prev) {
/* 371 */             this.m_buckets[hash] = n.next;
/*     */           } else {
/* 376 */             prev.next = n.next;
/*     */           } 
/* 378 */           (this.m_locks[hash]).size--;
/* 379 */           return n.value;
/*     */         } 
/* 382 */         prev = n;
/* 383 */         n = n.next;
/*     */       } 
/*     */     } 
/* 387 */     return null;
/*     */   }
/*     */   
/*     */   public final boolean isEmpty() {
/* 395 */     return (size() == 0);
/*     */   }
/*     */   
/*     */   public final void clear() {
/* 403 */     for (int i = 0; i < this.m_buckets.length; i++) {
/* 405 */       Lock lock = this.m_locks[i];
/* 406 */       synchronized (lock) {
/* 407 */         this.m_buckets[i] = null;
/* 408 */         lock.size = 0;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public final boolean equals(Object obj) {
/* 418 */     if (obj == null)
/* 418 */       return false; 
/* 419 */     if (obj == this)
/* 419 */       return true; 
/* 421 */     if (!(obj instanceof Map))
/* 421 */       return false; 
/* 423 */     Map other = (Map)obj;
/* 425 */     return entrySet().equals(other.entrySet());
/*     */   }
/*     */   
/*     */   public final int hashCode() {
/* 433 */     int hashCode = 0;
/* 435 */     for (int i = 0; i < this.m_buckets.length; i++) {
/* 437 */       synchronized (this.m_locks[i]) {
/* 439 */         Node n = this.m_buckets[i];
/* 441 */         while (n != null) {
/* 443 */           hashCode += n.hashCode();
/* 444 */           n = n.next;
/*     */         } 
/*     */       } 
/*     */     } 
/* 448 */     return hashCode;
/*     */   }
/*     */   
/*     */   private static final class Node implements Map.Entry, KeyValue {
/*     */     protected Object key;
/*     */     
/*     */     protected Object value;
/*     */     
/*     */     protected Node next;
/*     */     
/*     */     private Node() {}
/*     */     
/*     */     public Object getKey() {
/* 462 */       return this.key;
/*     */     }
/*     */     
/*     */     public Object getValue() {
/* 467 */       return this.value;
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 472 */       return ((this.key == null) ? 0 : this.key.hashCode()) ^ ((this.value == null) ? 0 : this.value.hashCode());
/*     */     }
/*     */     
/*     */     public boolean equals(Object o) {
/* 477 */       if (o == null)
/* 477 */         return false; 
/* 478 */       if (o == this)
/* 478 */         return true; 
/* 480 */       if (!(o instanceof Map.Entry))
/* 481 */         return false; 
/* 483 */       Map.Entry e2 = (Map.Entry)o;
/* 485 */       if ((this.key == null) ? (e2.getKey() == null) : this.key.equals(e2.getKey()))
/* 485 */         if ((this.value == null) ? (e2.getValue() == null) : this.value.equals(e2.getValue())); 
/* 485 */       return false;
/*     */     }
/*     */     
/*     */     public Object setValue(Object val) {
/* 493 */       Object retVal = this.value;
/* 494 */       this.value = val;
/* 495 */       return retVal;
/*     */     }
/*     */   }
/*     */   
/*     */   private static final class Lock {
/*     */     public int size;
/*     */     
/*     */     private Lock() {}
/*     */   }
/*     */   
/*     */   private class EntryIterator implements Iterator {
/*     */     private ArrayList current;
/*     */     
/*     */     private int bucket;
/*     */     
/*     */     private Map.Entry last;
/*     */     
/*     */     private final StaticBucketMap this$0;
/*     */     
/*     */     private EntryIterator(StaticBucketMap this$0) {
/* 506 */       StaticBucketMap.this = StaticBucketMap.this;
/* 508 */       this.current = new ArrayList();
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/* 514 */       if (this.current.size() > 0)
/* 514 */         return true; 
/* 515 */       while (this.bucket < StaticBucketMap.this.m_buckets.length) {
/* 516 */         synchronized (StaticBucketMap.this.m_locks[this.bucket]) {
/* 517 */           StaticBucketMap.Node n = StaticBucketMap.this.m_buckets[this.bucket];
/* 518 */           while (n != null) {
/* 519 */             this.current.add(n);
/* 520 */             n = n.next;
/*     */           } 
/* 522 */           this.bucket++;
/* 523 */           if (this.current.size() > 0)
/* 523 */             return true; 
/*     */         } 
/*     */       } 
/* 526 */       return false;
/*     */     }
/*     */     
/*     */     protected Map.Entry nextEntry() {
/* 530 */       if (!hasNext())
/* 530 */         throw new NoSuchElementException(); 
/* 531 */       this.last = this.current.remove(this.current.size() - 1);
/* 532 */       return this.last;
/*     */     }
/*     */     
/*     */     public Object next() {
/* 536 */       return nextEntry();
/*     */     }
/*     */     
/*     */     public void remove() {
/* 540 */       if (this.last == null)
/* 540 */         throw new IllegalStateException(); 
/* 541 */       StaticBucketMap.this.remove(this.last.getKey());
/* 542 */       this.last = null;
/*     */     }
/*     */   }
/*     */   
/*     */   private class ValueIterator extends EntryIterator {
/*     */     private final StaticBucketMap this$0;
/*     */     
/*     */     private ValueIterator(StaticBucketMap this$0) {
/* 547 */       StaticBucketMap.this = StaticBucketMap.this;
/*     */     }
/*     */     
/*     */     public Object next() {
/* 550 */       return nextEntry().getValue();
/*     */     }
/*     */   }
/*     */   
/*     */   private class KeyIterator extends EntryIterator {
/*     */     private final StaticBucketMap this$0;
/*     */     
/*     */     private KeyIterator(StaticBucketMap this$0) {
/* 555 */       StaticBucketMap.this = StaticBucketMap.this;
/*     */     }
/*     */     
/*     */     public Object next() {
/* 558 */       return nextEntry().getKey();
/*     */     }
/*     */   }
/*     */   
/*     */   private class EntrySet extends AbstractSet {
/*     */     private final StaticBucketMap this$0;
/*     */     
/*     */     private EntrySet(StaticBucketMap this$0) {
/* 563 */       StaticBucketMap.this = StaticBucketMap.this;
/*     */     }
/*     */     
/*     */     public int size() {
/* 566 */       return StaticBucketMap.this.size();
/*     */     }
/*     */     
/*     */     public void clear() {
/* 570 */       StaticBucketMap.this.clear();
/*     */     }
/*     */     
/*     */     public Iterator iterator() {
/* 574 */       return new StaticBucketMap.EntryIterator();
/*     */     }
/*     */     
/*     */     public boolean contains(Object o) {
/* 578 */       Map.Entry entry = (Map.Entry)o;
/* 579 */       int hash = StaticBucketMap.this.getHash(entry.getKey());
/* 580 */       synchronized (StaticBucketMap.this.m_locks[hash]) {
/* 581 */         for (StaticBucketMap.Node n = StaticBucketMap.this.m_buckets[hash]; n != null; n = n.next) {
/* 582 */           if (n.equals(entry))
/* 582 */             return true; 
/*     */         } 
/*     */       } 
/* 585 */       return false;
/*     */     }
/*     */     
/*     */     public boolean remove(Object obj) {
/* 589 */       if (!(obj instanceof Map.Entry))
/* 590 */         return false; 
/* 592 */       Map.Entry entry = (Map.Entry)obj;
/* 593 */       int hash = StaticBucketMap.this.getHash(entry.getKey());
/* 594 */       synchronized (StaticBucketMap.this.m_locks[hash]) {
/* 595 */         for (StaticBucketMap.Node n = StaticBucketMap.this.m_buckets[hash]; n != null; n = n.next) {
/* 596 */           if (n.equals(entry)) {
/* 597 */             StaticBucketMap.this.remove(n.getKey());
/* 598 */             return true;
/*     */           } 
/*     */         } 
/*     */       } 
/* 602 */       return false;
/*     */     }
/*     */   }
/*     */   
/*     */   private class KeySet extends AbstractSet {
/*     */     private final StaticBucketMap this$0;
/*     */     
/*     */     private KeySet(StaticBucketMap this$0) {
/* 608 */       StaticBucketMap.this = StaticBucketMap.this;
/*     */     }
/*     */     
/*     */     public int size() {
/* 611 */       return StaticBucketMap.this.size();
/*     */     }
/*     */     
/*     */     public void clear() {
/* 615 */       StaticBucketMap.this.clear();
/*     */     }
/*     */     
/*     */     public Iterator iterator() {
/* 619 */       return new StaticBucketMap.KeyIterator();
/*     */     }
/*     */     
/*     */     public boolean contains(Object o) {
/* 623 */       return StaticBucketMap.this.containsKey(o);
/*     */     }
/*     */     
/*     */     public boolean remove(Object o) {
/* 627 */       int hash = StaticBucketMap.this.getHash(o);
/* 628 */       synchronized (StaticBucketMap.this.m_locks[hash]) {
/* 629 */         for (StaticBucketMap.Node n = StaticBucketMap.this.m_buckets[hash]; n != null; n = n.next) {
/* 630 */           Object k = n.getKey();
/* 631 */           if (k == o || (k != null && k.equals(o))) {
/* 632 */             StaticBucketMap.this.remove(k);
/* 633 */             return true;
/*     */           } 
/*     */         } 
/*     */       } 
/* 637 */       return false;
/*     */     }
/*     */   }
/*     */   
/*     */   private class Values extends AbstractCollection {
/*     */     private final StaticBucketMap this$0;
/*     */     
/*     */     private Values(StaticBucketMap this$0) {
/* 644 */       StaticBucketMap.this = StaticBucketMap.this;
/*     */     }
/*     */     
/*     */     public int size() {
/* 647 */       return StaticBucketMap.this.size();
/*     */     }
/*     */     
/*     */     public void clear() {
/* 651 */       StaticBucketMap.this.clear();
/*     */     }
/*     */     
/*     */     public Iterator iterator() {
/* 655 */       return new StaticBucketMap.ValueIterator();
/*     */     }
/*     */   }
/*     */   
/*     */   public void atomic(Runnable r) {
/* 696 */     if (r == null)
/* 696 */       throw new NullPointerException(); 
/* 697 */     atomic(r, 0);
/*     */   }
/*     */   
/*     */   private void atomic(Runnable r, int bucket) {
/* 701 */     if (bucket >= this.m_buckets.length) {
/* 702 */       r.run();
/*     */       return;
/*     */     } 
/* 705 */     synchronized (this.m_locks[bucket]) {
/* 706 */       atomic(r, bucket + 1);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\StaticBucketMap.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */