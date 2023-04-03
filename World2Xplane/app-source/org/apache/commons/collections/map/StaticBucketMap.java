/*     */ package org.apache.commons.collections.map;
/*     */ 
/*     */ import java.util.AbstractCollection;
/*     */ import java.util.AbstractSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.NoSuchElementException;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.collections.KeyValue;
/*     */ 
/*     */ public final class StaticBucketMap implements Map {
/*     */   private static final int DEFAULT_BUCKETS = 255;
/*     */   
/*     */   private Node[] buckets;
/*     */   
/*     */   private Lock[] locks;
/*     */   
/*     */   public StaticBucketMap() {
/* 117 */     this(255);
/*     */   }
/*     */   
/*     */   public StaticBucketMap(int numBuckets) {
/* 131 */     int size = Math.max(17, numBuckets);
/* 134 */     if (size % 2 == 0)
/* 135 */       size--; 
/* 138 */     this.buckets = new Node[size];
/* 139 */     this.locks = new Lock[size];
/* 141 */     for (int i = 0; i < size; i++)
/* 142 */       this.locks[i] = new Lock(); 
/*     */   }
/*     */   
/*     */   private final int getHash(Object key) {
/* 161 */     if (key == null)
/* 162 */       return 0; 
/* 164 */     int hash = key.hashCode();
/* 165 */     hash += hash << 15 ^ 0xFFFFFFFF;
/* 166 */     hash ^= hash >>> 10;
/* 167 */     hash += hash << 3;
/* 168 */     hash ^= hash >>> 6;
/* 169 */     hash += hash << 11 ^ 0xFFFFFFFF;
/* 170 */     hash ^= hash >>> 16;
/* 171 */     hash %= this.buckets.length;
/* 172 */     return (hash < 0) ? (hash * -1) : hash;
/*     */   }
/*     */   
/*     */   public int size() {
/* 182 */     int cnt = 0;
/* 184 */     for (int i = 0; i < this.buckets.length; i++)
/* 185 */       cnt += (this.locks[i]).size; 
/* 187 */     return cnt;
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 196 */     return (size() == 0);
/*     */   }
/*     */   
/*     */   public Object get(Object key) {
/* 206 */     int hash = getHash(key);
/* 208 */     synchronized (this.locks[hash]) {
/* 209 */       Node n = this.buckets[hash];
/* 211 */       while (n != null) {
/* 212 */         if (n.key == key || (n.key != null && n.key.equals(key)))
/* 213 */           return n.value; 
/* 216 */         n = n.next;
/*     */       } 
/*     */     } 
/* 219 */     return null;
/*     */   }
/*     */   
/*     */   public boolean containsKey(Object key) {
/* 229 */     int hash = getHash(key);
/* 231 */     synchronized (this.locks[hash]) {
/* 232 */       Node n = this.buckets[hash];
/* 234 */       while (n != null) {
/* 235 */         if (n.key == key || (n.key != null && n.key.equals(key)))
/* 236 */           return true; 
/* 239 */         n = n.next;
/*     */       } 
/*     */     } 
/* 242 */     return false;
/*     */   }
/*     */   
/*     */   public boolean containsValue(Object value) {
/* 252 */     for (int i = 0; i < this.buckets.length; i++) {
/* 253 */       synchronized (this.locks[i]) {
/* 254 */         Node n = this.buckets[i];
/* 256 */         while (n != null) {
/* 257 */           if (n.value == value || (n.value != null && n.value.equals(value)))
/* 258 */             return true; 
/* 261 */           n = n.next;
/*     */         } 
/*     */       } 
/*     */     } 
/* 265 */     return false;
/*     */   }
/*     */   
/*     */   public Object put(Object key, Object value) {
/* 277 */     int hash = getHash(key);
/* 279 */     synchronized (this.locks[hash]) {
/* 280 */       Node n = this.buckets[hash];
/* 282 */       if (n == null) {
/* 283 */         n = new Node();
/* 284 */         n.key = key;
/* 285 */         n.value = value;
/* 286 */         this.buckets[hash] = n;
/* 287 */         (this.locks[hash]).size++;
/* 288 */         return null;
/*     */       } 
/* 294 */       for (Node next = n; next != null; next = next.next) {
/* 295 */         n = next;
/* 297 */         if (n.key == key || (n.key != null && n.key.equals(key))) {
/* 298 */           Object returnVal = n.value;
/* 299 */           n.value = value;
/* 300 */           return returnVal;
/*     */         } 
/*     */       } 
/* 306 */       Node newNode = new Node();
/* 307 */       newNode.key = key;
/* 308 */       newNode.value = value;
/* 309 */       n.next = newNode;
/* 310 */       (this.locks[hash]).size++;
/*     */     } 
/* 312 */     return null;
/*     */   }
/*     */   
/*     */   public Object remove(Object key) {
/* 322 */     int hash = getHash(key);
/* 324 */     synchronized (this.locks[hash]) {
/* 325 */       Node n = this.buckets[hash];
/* 326 */       Node prev = null;
/* 328 */       while (n != null) {
/* 329 */         if (n.key == key || (n.key != null && n.key.equals(key))) {
/* 331 */           if (null == prev) {
/* 333 */             this.buckets[hash] = n.next;
/*     */           } else {
/* 336 */             prev.next = n.next;
/*     */           } 
/* 338 */           (this.locks[hash]).size--;
/* 339 */           return n.value;
/*     */         } 
/* 342 */         prev = n;
/* 343 */         n = n.next;
/*     */       } 
/*     */     } 
/* 346 */     return null;
/*     */   }
/*     */   
/*     */   public Set keySet() {
/* 356 */     return new KeySet();
/*     */   }
/*     */   
/*     */   public Collection values() {
/* 365 */     return new Values();
/*     */   }
/*     */   
/*     */   public Set entrySet() {
/* 374 */     return new EntrySet();
/*     */   }
/*     */   
/*     */   public void putAll(Map map) {
/* 385 */     Iterator i = map.keySet().iterator();
/* 387 */     while (i.hasNext()) {
/* 388 */       Object key = i.next();
/* 389 */       put(key, map.get(key));
/*     */     } 
/*     */   }
/*     */   
/*     */   public void clear() {
/* 397 */     for (int i = 0; i < this.buckets.length; i++) {
/* 398 */       Lock lock = this.locks[i];
/* 399 */       synchronized (lock) {
/* 400 */         this.buckets[i] = null;
/* 401 */         lock.size = 0;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 413 */     if (obj == this)
/* 414 */       return true; 
/* 416 */     if (!(obj instanceof Map))
/* 417 */       return false; 
/* 419 */     Map other = (Map)obj;
/* 420 */     return entrySet().equals(other.entrySet());
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 429 */     int hashCode = 0;
/* 431 */     for (int i = 0; i < this.buckets.length; i++) {
/* 432 */       synchronized (this.locks[i]) {
/* 433 */         Node n = this.buckets[i];
/* 435 */         while (n != null) {
/* 436 */           hashCode += n.hashCode();
/* 437 */           n = n.next;
/*     */         } 
/*     */       } 
/*     */     } 
/* 441 */     return hashCode;
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
/* 454 */       return this.key;
/*     */     }
/*     */     
/*     */     public Object getValue() {
/* 458 */       return this.value;
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 462 */       return ((this.key == null) ? 0 : this.key.hashCode()) ^ ((this.value == null) ? 0 : this.value.hashCode());
/*     */     }
/*     */     
/*     */     public boolean equals(Object obj) {
/* 467 */       if (obj == this)
/* 468 */         return true; 
/* 470 */       if (!(obj instanceof Map.Entry))
/* 471 */         return false; 
/* 474 */       Map.Entry e2 = (Map.Entry)obj;
/* 475 */       return (((this.key == null) ? (e2.getKey() == null) : this.key.equals(e2.getKey())) && ((this.value == null) ? (e2.getValue() == null) : this.value.equals(e2.getValue())));
/*     */     }
/*     */     
/*     */     public Object setValue(Object obj) {
/* 481 */       Object retVal = this.value;
/* 482 */       this.value = obj;
/* 483 */       return retVal;
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
/* 497 */       StaticBucketMap.this = StaticBucketMap.this;
/* 499 */       this.current = new ArrayList();
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/* 505 */       if (this.current.size() > 0)
/* 505 */         return true; 
/* 506 */       while (this.bucket < StaticBucketMap.this.buckets.length) {
/* 507 */         synchronized (StaticBucketMap.this.locks[this.bucket]) {
/* 508 */           StaticBucketMap.Node n = StaticBucketMap.this.buckets[this.bucket];
/* 509 */           while (n != null) {
/* 510 */             this.current.add(n);
/* 511 */             n = n.next;
/*     */           } 
/* 513 */           this.bucket++;
/* 514 */           if (this.current.size() > 0)
/* 514 */             return true; 
/*     */         } 
/*     */       } 
/* 517 */       return false;
/*     */     }
/*     */     
/*     */     protected Map.Entry nextEntry() {
/* 521 */       if (!hasNext())
/* 521 */         throw new NoSuchElementException(); 
/* 522 */       this.last = this.current.remove(this.current.size() - 1);
/* 523 */       return this.last;
/*     */     }
/*     */     
/*     */     public Object next() {
/* 527 */       return nextEntry();
/*     */     }
/*     */     
/*     */     public void remove() {
/* 531 */       if (this.last == null)
/* 531 */         throw new IllegalStateException(); 
/* 532 */       StaticBucketMap.this.remove(this.last.getKey());
/* 533 */       this.last = null;
/*     */     }
/*     */   }
/*     */   
/*     */   private class ValueIterator extends EntryIterator {
/*     */     private final StaticBucketMap this$0;
/*     */     
/*     */     private ValueIterator(StaticBucketMap this$0) {
/* 538 */       StaticBucketMap.this = StaticBucketMap.this;
/*     */     }
/*     */     
/*     */     public Object next() {
/* 541 */       return nextEntry().getValue();
/*     */     }
/*     */   }
/*     */   
/*     */   private class KeyIterator extends EntryIterator {
/*     */     private final StaticBucketMap this$0;
/*     */     
/*     */     private KeyIterator(StaticBucketMap this$0) {
/* 546 */       StaticBucketMap.this = StaticBucketMap.this;
/*     */     }
/*     */     
/*     */     public Object next() {
/* 549 */       return nextEntry().getKey();
/*     */     }
/*     */   }
/*     */   
/*     */   private class EntrySet extends AbstractSet {
/*     */     private final StaticBucketMap this$0;
/*     */     
/*     */     private EntrySet(StaticBucketMap this$0) {
/* 554 */       StaticBucketMap.this = StaticBucketMap.this;
/*     */     }
/*     */     
/*     */     public int size() {
/* 557 */       return StaticBucketMap.this.size();
/*     */     }
/*     */     
/*     */     public void clear() {
/* 561 */       StaticBucketMap.this.clear();
/*     */     }
/*     */     
/*     */     public Iterator iterator() {
/* 565 */       return new StaticBucketMap.EntryIterator();
/*     */     }
/*     */     
/*     */     public boolean contains(Object obj) {
/* 569 */       Map.Entry entry = (Map.Entry)obj;
/* 570 */       int hash = StaticBucketMap.this.getHash(entry.getKey());
/* 571 */       synchronized (StaticBucketMap.this.locks[hash]) {
/* 572 */         for (StaticBucketMap.Node n = StaticBucketMap.this.buckets[hash]; n != null; n = n.next) {
/* 573 */           if (n.equals(entry))
/* 573 */             return true; 
/*     */         } 
/*     */       } 
/* 576 */       return false;
/*     */     }
/*     */     
/*     */     public boolean remove(Object obj) {
/* 580 */       if (!(obj instanceof Map.Entry))
/* 581 */         return false; 
/* 583 */       Map.Entry entry = (Map.Entry)obj;
/* 584 */       int hash = StaticBucketMap.this.getHash(entry.getKey());
/* 585 */       synchronized (StaticBucketMap.this.locks[hash]) {
/* 586 */         for (StaticBucketMap.Node n = StaticBucketMap.this.buckets[hash]; n != null; n = n.next) {
/* 587 */           if (n.equals(entry)) {
/* 588 */             StaticBucketMap.this.remove(n.getKey());
/* 589 */             return true;
/*     */           } 
/*     */         } 
/*     */       } 
/* 593 */       return false;
/*     */     }
/*     */   }
/*     */   
/*     */   private class KeySet extends AbstractSet {
/*     */     private final StaticBucketMap this$0;
/*     */     
/*     */     private KeySet(StaticBucketMap this$0) {
/* 599 */       StaticBucketMap.this = StaticBucketMap.this;
/*     */     }
/*     */     
/*     */     public int size() {
/* 602 */       return StaticBucketMap.this.size();
/*     */     }
/*     */     
/*     */     public void clear() {
/* 606 */       StaticBucketMap.this.clear();
/*     */     }
/*     */     
/*     */     public Iterator iterator() {
/* 610 */       return new StaticBucketMap.KeyIterator();
/*     */     }
/*     */     
/*     */     public boolean contains(Object obj) {
/* 614 */       return StaticBucketMap.this.containsKey(obj);
/*     */     }
/*     */     
/*     */     public boolean remove(Object obj) {
/* 618 */       int hash = StaticBucketMap.this.getHash(obj);
/* 619 */       synchronized (StaticBucketMap.this.locks[hash]) {
/* 620 */         for (StaticBucketMap.Node n = StaticBucketMap.this.buckets[hash]; n != null; n = n.next) {
/* 621 */           Object k = n.getKey();
/* 622 */           if (k == obj || (k != null && k.equals(obj))) {
/* 623 */             StaticBucketMap.this.remove(k);
/* 624 */             return true;
/*     */           } 
/*     */         } 
/*     */       } 
/* 628 */       return false;
/*     */     }
/*     */   }
/*     */   
/*     */   private class Values extends AbstractCollection {
/*     */     private final StaticBucketMap this$0;
/*     */     
/*     */     private Values(StaticBucketMap this$0) {
/* 635 */       StaticBucketMap.this = StaticBucketMap.this;
/*     */     }
/*     */     
/*     */     public int size() {
/* 638 */       return StaticBucketMap.this.size();
/*     */     }
/*     */     
/*     */     public void clear() {
/* 642 */       StaticBucketMap.this.clear();
/*     */     }
/*     */     
/*     */     public Iterator iterator() {
/* 646 */       return new StaticBucketMap.ValueIterator();
/*     */     }
/*     */   }
/*     */   
/*     */   public void atomic(Runnable r) {
/* 687 */     if (r == null)
/* 687 */       throw new NullPointerException(); 
/* 688 */     atomic(r, 0);
/*     */   }
/*     */   
/*     */   private void atomic(Runnable r, int bucket) {
/* 692 */     if (bucket >= this.buckets.length) {
/* 693 */       r.run();
/*     */       return;
/*     */     } 
/* 696 */     synchronized (this.locks[bucket]) {
/* 697 */       atomic(r, bucket + 1);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\map\StaticBucketMap.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */