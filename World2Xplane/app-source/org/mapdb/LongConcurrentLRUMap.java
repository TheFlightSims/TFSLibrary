/*     */ package org.mapdb;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.concurrent.atomic.AtomicInteger;
/*     */ import java.util.concurrent.atomic.AtomicLong;
/*     */ import java.util.concurrent.locks.ReentrantLock;
/*     */ 
/*     */ public class LongConcurrentLRUMap<V> extends LongMap<V> {
/*     */   protected final LongConcurrentHashMap<CacheEntry<V>> map;
/*     */   
/*     */   protected final int upperWaterMark;
/*     */   
/*     */   protected final int lowerWaterMark;
/*     */   
/*  44 */   protected final ReentrantLock markAndSweepLock = new ReentrantLock(true);
/*     */   
/*     */   protected boolean isCleaning = false;
/*     */   
/*     */   protected final int acceptableWaterMark;
/*     */   
/*  48 */   protected long oldestEntry = 0L;
/*     */   
/*  51 */   protected final AtomicLong accessCounter = new AtomicLong(0L);
/*     */   
/*  51 */   protected final AtomicLong putCounter = new AtomicLong(0L);
/*     */   
/*  51 */   protected final AtomicLong missCounter = new AtomicLong();
/*     */   
/*  51 */   protected final AtomicLong evictionCounter = new AtomicLong();
/*     */   
/*  55 */   protected final AtomicInteger size = new AtomicInteger();
/*     */   
/*     */   public LongConcurrentLRUMap(int upperWaterMark, int lowerWaterMark, int acceptableWatermark, int initialSize) {
/*  61 */     if (upperWaterMark < 1)
/*  61 */       throw new IllegalArgumentException("upperWaterMark must be > 0"); 
/*  62 */     if (lowerWaterMark >= upperWaterMark)
/*  63 */       throw new IllegalArgumentException("lowerWaterMark must be  < upperWaterMark"); 
/*  64 */     this.map = new LongConcurrentHashMap<CacheEntry<V>>(initialSize);
/*  65 */     this.upperWaterMark = upperWaterMark;
/*  66 */     this.lowerWaterMark = lowerWaterMark;
/*  67 */     this.acceptableWaterMark = acceptableWatermark;
/*     */   }
/*     */   
/*     */   public LongConcurrentLRUMap(int size, int lowerWatermark) {
/*  71 */     this(size, lowerWatermark, (int)Math.floor(((lowerWatermark + size) / 2)), (int)Math.ceil(0.75D * size));
/*     */   }
/*     */   
/*     */   public V get(long key) {
/*  76 */     CacheEntry<V> e = this.map.get(key);
/*  77 */     if (e == null) {
/*  78 */       this.missCounter.incrementAndGet();
/*  79 */       return null;
/*     */     } 
/*  81 */     e.lastAccessed = this.accessCounter.incrementAndGet();
/*  82 */     return e.value;
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/*  87 */     return this.map.isEmpty();
/*     */   }
/*     */   
/*     */   public V remove(long key) {
/*  91 */     CacheEntry<V> cacheEntry = this.map.remove(key);
/*  92 */     if (cacheEntry != null) {
/*  93 */       this.size.decrementAndGet();
/*  94 */       return cacheEntry.value;
/*     */     } 
/*  96 */     return null;
/*     */   }
/*     */   
/*     */   public V put(long key, V val) {
/*     */     int currentSize;
/* 100 */     if (val == null)
/* 100 */       return null; 
/* 101 */     CacheEntry<V> e = new CacheEntry<V>(key, val, this.accessCounter.incrementAndGet());
/* 102 */     CacheEntry<V> oldCacheEntry = this.map.put(key, e);
/* 104 */     if (oldCacheEntry == null) {
/* 105 */       currentSize = this.size.incrementAndGet();
/*     */     } else {
/* 107 */       currentSize = this.size.get();
/*     */     } 
/* 110 */     this.putCounter.incrementAndGet();
/* 122 */     if (currentSize > this.upperWaterMark && !this.isCleaning)
/* 123 */       markAndSweep(); 
/* 125 */     return (oldCacheEntry == null) ? null : oldCacheEntry.value;
/*     */   }
/*     */   
/*     */   private void markAndSweep() {
/* 148 */     if (!this.markAndSweepLock.tryLock())
/*     */       return; 
/*     */     try {
/* 150 */       long oldestEntry = this.oldestEntry;
/* 151 */       this.isCleaning = true;
/* 152 */       this.oldestEntry = oldestEntry;
/* 154 */       long timeCurrent = this.accessCounter.get();
/* 155 */       int sz = this.size.get();
/* 157 */       int numRemoved = 0;
/* 158 */       int numKept = 0;
/* 159 */       long newestEntry = timeCurrent;
/* 160 */       long newNewestEntry = -1L;
/* 161 */       long newOldestEntry = Long.MAX_VALUE;
/* 163 */       int wantToKeep = this.lowerWaterMark;
/* 164 */       int wantToRemove = sz - this.lowerWaterMark;
/* 166 */       CacheEntry[] eset = new CacheEntry[sz];
/* 167 */       int eSize = 0;
/* 172 */       for (Iterator<CacheEntry<V>> iter = this.map.valuesIterator(); iter.hasNext(); ) {
/* 173 */         CacheEntry<V> ce = iter.next();
/* 175 */         ce.lastAccessedCopy = ce.lastAccessed;
/* 176 */         long thisEntry = ce.lastAccessedCopy;
/* 179 */         if (thisEntry > newestEntry - wantToKeep) {
/* 182 */           numKept++;
/* 183 */           newOldestEntry = Math.min(thisEntry, newOldestEntry);
/*     */           continue;
/*     */         } 
/* 184 */         if (thisEntry < oldestEntry + wantToRemove) {
/* 187 */           evictEntry(ce.key);
/* 188 */           numRemoved++;
/*     */           continue;
/*     */         } 
/* 194 */         if (eSize < eset.length - 1) {
/* 195 */           eset[eSize++] = ce;
/* 196 */           newNewestEntry = Math.max(thisEntry, newNewestEntry);
/* 197 */           newOldestEntry = Math.min(thisEntry, newOldestEntry);
/*     */         } 
/*     */       } 
/* 204 */       int numPasses = 1;
/* 208 */       while (sz - numRemoved > this.acceptableWaterMark && --numPasses >= 0) {
/* 210 */         oldestEntry = (newOldestEntry == Long.MAX_VALUE) ? oldestEntry : newOldestEntry;
/* 211 */         newOldestEntry = Long.MAX_VALUE;
/* 212 */         newestEntry = newNewestEntry;
/* 213 */         newNewestEntry = -1L;
/* 214 */         wantToKeep = this.lowerWaterMark - numKept;
/* 215 */         wantToRemove = sz - this.lowerWaterMark - numRemoved;
/* 218 */         for (int i = eSize - 1; i >= 0; i--) {
/* 219 */           CacheEntry ce = eset[i];
/* 220 */           long thisEntry = ce.lastAccessedCopy;
/* 222 */           if (thisEntry > newestEntry - wantToKeep) {
/* 225 */             numKept++;
/* 227 */             eset[i] = eset[eSize - 1];
/* 228 */             eSize--;
/* 230 */             newOldestEntry = Math.min(thisEntry, newOldestEntry);
/* 232 */           } else if (thisEntry < oldestEntry + wantToRemove) {
/* 236 */             evictEntry(ce.key);
/* 237 */             numRemoved++;
/* 240 */             eset[i] = eset[eSize - 1];
/* 241 */             eSize--;
/*     */           } else {
/* 245 */             newNewestEntry = Math.max(thisEntry, newNewestEntry);
/* 246 */             newOldestEntry = Math.min(thisEntry, newOldestEntry);
/*     */           } 
/*     */         } 
/*     */       } 
/* 256 */       if (sz - numRemoved > this.acceptableWaterMark) {
/* 258 */         oldestEntry = (newOldestEntry == Long.MAX_VALUE) ? oldestEntry : newOldestEntry;
/* 259 */         newOldestEntry = Long.MAX_VALUE;
/* 260 */         newestEntry = newNewestEntry;
/* 261 */         newNewestEntry = -1L;
/* 262 */         wantToKeep = this.lowerWaterMark - numKept;
/* 263 */         wantToRemove = sz - this.lowerWaterMark - numRemoved;
/* 265 */         PQueue<V> queue = new PQueue<V>(wantToRemove);
/* 267 */         for (int i = eSize - 1; i >= 0; i--) {
/* 268 */           CacheEntry<V> ce = eset[i];
/* 269 */           long thisEntry = ce.lastAccessedCopy;
/* 271 */           if (thisEntry > newestEntry - wantToKeep) {
/* 274 */             numKept++;
/* 279 */             newOldestEntry = Math.min(thisEntry, newOldestEntry);
/* 281 */           } else if (thisEntry < oldestEntry + wantToRemove) {
/* 284 */             evictEntry(ce.key);
/* 285 */             numRemoved++;
/*     */           } else {
/* 300 */             queue.myMaxSize = sz - this.lowerWaterMark - numRemoved;
/* 301 */             while (queue.size() > queue.myMaxSize && queue.size() > 0) {
/* 302 */               CacheEntry<V> otherEntry = queue.pop();
/* 303 */               newOldestEntry = Math.min(otherEntry.lastAccessedCopy, newOldestEntry);
/*     */             } 
/* 305 */             if (queue.myMaxSize <= 0)
/*     */               break; 
/* 307 */             Object<V> o = (Object<V>)queue.myInsertWithOverflow(ce);
/* 308 */             if (o != null)
/* 309 */               newOldestEntry = Math.min(((CacheEntry)o).lastAccessedCopy, newOldestEntry); 
/*     */           } 
/*     */         } 
/* 316 */         for (CacheEntry<V> ce : queue.getValues()) {
/* 317 */           if (ce == null)
/*     */             continue; 
/* 318 */           evictEntry(ce.key);
/* 319 */           numRemoved++;
/*     */         } 
/*     */       } 
/* 325 */       oldestEntry = (newOldestEntry == Long.MAX_VALUE) ? oldestEntry : newOldestEntry;
/* 326 */       this.oldestEntry = oldestEntry;
/*     */     } finally {
/* 328 */       this.isCleaning = false;
/* 329 */       this.markAndSweepLock.unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   private static class PQueue<V> extends PriorityQueue<CacheEntry<V>> {
/*     */     int myMaxSize;
/*     */     
/*     */     final Object[] heap;
/*     */     
/*     */     PQueue(int maxSz) {
/* 338 */       super(maxSz);
/* 339 */       this.heap = (Object[])getHeapArray();
/* 340 */       this.myMaxSize = maxSz;
/*     */     }
/*     */     
/*     */     Iterable<LongConcurrentLRUMap.CacheEntry<V>> getValues() {
/* 345 */       return Collections.unmodifiableCollection(Arrays.asList(this.heap));
/*     */     }
/*     */     
/*     */     protected boolean lessThan(LongConcurrentLRUMap.CacheEntry<V> a, LongConcurrentLRUMap.CacheEntry<V> b) {
/* 351 */       return (b.lastAccessedCopy < a.lastAccessedCopy);
/*     */     }
/*     */     
/*     */     public LongConcurrentLRUMap.CacheEntry<V> myInsertWithOverflow(LongConcurrentLRUMap.CacheEntry<V> element) {
/* 356 */       if (size() < this.myMaxSize) {
/* 357 */         add(element);
/* 358 */         return null;
/*     */       } 
/* 359 */       if (size() > 0 && !lessThan(element, (LongConcurrentLRUMap.CacheEntry<V>)this.heap[1])) {
/* 360 */         LongConcurrentLRUMap.CacheEntry<V> ret = (LongConcurrentLRUMap.CacheEntry<V>)this.heap[1];
/* 361 */         this.heap[1] = element;
/* 362 */         updateTop();
/* 363 */         return ret;
/*     */       } 
/* 365 */       return element;
/*     */     }
/*     */   }
/*     */   
/*     */   private static abstract class PriorityQueue<T> {
/*     */     private int size;
/*     */     
/*     */     private final int maxSize;
/*     */     
/*     */     private final T[] heap;
/*     */     
/*     */     public PriorityQueue(int maxSize) {
/* 387 */       this(maxSize, true);
/*     */     }
/*     */     
/*     */     public PriorityQueue(int maxSize, boolean prepopulate) {
/*     */       int heapSize;
/* 391 */       this.size = 0;
/* 393 */       if (0 == maxSize) {
/* 395 */         heapSize = 2;
/* 397 */       } else if (maxSize == Integer.MAX_VALUE) {
/* 406 */         heapSize = Integer.MAX_VALUE;
/*     */       } else {
/* 410 */         heapSize = maxSize + 1;
/*     */       } 
/* 413 */       this.heap = (T[])new Object[heapSize];
/* 414 */       this.maxSize = maxSize;
/* 416 */       if (prepopulate) {
/* 418 */         T sentinel = getSentinelObject();
/* 419 */         if (sentinel != null) {
/* 420 */           this.heap[1] = sentinel;
/* 421 */           for (int i = 2; i < this.heap.length; i++)
/* 422 */             this.heap[i] = getSentinelObject(); 
/* 424 */           this.size = maxSize;
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/*     */     protected abstract boolean lessThan(T param1T1, T param1T2);
/*     */     
/*     */     protected T getSentinelObject() {
/* 476 */       return null;
/*     */     }
/*     */     
/*     */     public final T add(T element) {
/* 487 */       this.size++;
/* 488 */       this.heap[this.size] = element;
/* 489 */       upHeap();
/* 490 */       return this.heap[1];
/*     */     }
/*     */     
/*     */     public T insertWithOverflow(T element) {
/* 504 */       if (this.size < this.maxSize) {
/* 505 */         add(element);
/* 506 */         return null;
/*     */       } 
/* 507 */       if (this.size > 0 && !lessThan(element, this.heap[1])) {
/* 508 */         T ret = this.heap[1];
/* 509 */         this.heap[1] = element;
/* 510 */         updateTop();
/* 511 */         return ret;
/*     */       } 
/* 513 */       return element;
/*     */     }
/*     */     
/*     */     public final T top() {
/* 522 */       return this.heap[1];
/*     */     }
/*     */     
/*     */     public final T pop() {
/* 528 */       if (this.size > 0) {
/* 529 */         T result = this.heap[1];
/* 530 */         this.heap[1] = this.heap[this.size];
/* 531 */         this.heap[this.size] = null;
/* 532 */         this.size--;
/* 533 */         downHeap();
/* 534 */         return result;
/*     */       } 
/* 536 */       return null;
/*     */     }
/*     */     
/*     */     public final T updateTop() {
/* 559 */       downHeap();
/* 560 */       return this.heap[1];
/*     */     }
/*     */     
/*     */     public final int size() {
/* 565 */       return this.size;
/*     */     }
/*     */     
/*     */     public final void clear() {
/* 570 */       for (int i = 0; i <= this.size; i++)
/* 571 */         this.heap[i] = null; 
/* 573 */       this.size = 0;
/*     */     }
/*     */     
/*     */     private void upHeap() {
/* 577 */       int i = this.size;
/* 578 */       T node = this.heap[i];
/* 579 */       int j = i >>> 1;
/* 580 */       while (j > 0 && lessThan(node, this.heap[j])) {
/* 581 */         this.heap[i] = this.heap[j];
/* 582 */         i = j;
/* 583 */         j >>>= 1;
/*     */       } 
/* 585 */       this.heap[i] = node;
/*     */     }
/*     */     
/*     */     private void downHeap() {
/* 589 */       int i = 1;
/* 590 */       T node = this.heap[i];
/* 591 */       int j = i << 1;
/* 592 */       int k = j + 1;
/* 593 */       if (k <= this.size && lessThan(this.heap[k], this.heap[j]))
/* 594 */         j = k; 
/* 596 */       while (j <= this.size && lessThan(this.heap[j], node)) {
/* 597 */         this.heap[i] = this.heap[j];
/* 598 */         i = j;
/* 599 */         j = i << 1;
/* 600 */         k = j + 1;
/* 601 */         if (k <= this.size && lessThan(this.heap[k], this.heap[j]))
/* 602 */           j = k; 
/*     */       } 
/* 605 */       this.heap[i] = node;
/*     */     }
/*     */     
/*     */     protected final T[] getHeapArray() {
/* 612 */       return this.heap;
/*     */     }
/*     */   }
/*     */   
/*     */   private void evictEntry(long key) {
/* 618 */     CacheEntry<V> o = this.map.remove(key);
/* 619 */     if (o == null)
/*     */       return; 
/* 620 */     this.size.decrementAndGet();
/* 621 */     this.evictionCounter.incrementAndGet();
/* 622 */     evictedEntry(o.key, o.value);
/*     */   }
/*     */   
/*     */   public int size() {
/* 627 */     return this.size.get();
/*     */   }
/*     */   
/*     */   public Iterator<V> valuesIterator() {
/* 632 */     final Iterator<CacheEntry<V>> iter = this.map.valuesIterator();
/* 633 */     return new Iterator<V>() {
/*     */         public boolean hasNext() {
/* 637 */           return iter.hasNext();
/*     */         }
/*     */         
/*     */         public V next() {
/* 642 */           return ((LongConcurrentLRUMap.CacheEntry)iter.next()).value;
/*     */         }
/*     */         
/*     */         public void remove() {
/* 647 */           throw new UnsupportedOperationException();
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   public LongMap.LongMapIterator<V> longMapIterator() {
/* 654 */     final LongMap.LongMapIterator<CacheEntry<V>> iter = this.map.longMapIterator();
/* 655 */     return new LongMap.LongMapIterator<V>() {
/*     */         public boolean moveToNext() {
/* 658 */           return iter.moveToNext();
/*     */         }
/*     */         
/*     */         public long key() {
/* 663 */           return iter.key();
/*     */         }
/*     */         
/*     */         public V value() {
/* 668 */           return ((LongConcurrentLRUMap.CacheEntry)iter.value()).value;
/*     */         }
/*     */         
/*     */         public void remove() {
/* 673 */           throw new UnsupportedOperationException();
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   public void clear() {
/* 679 */     this.map.clear();
/*     */   }
/*     */   
/*     */   public LongMap<CacheEntry<V>> getMap() {
/* 683 */     return this.map;
/*     */   }
/*     */   
/*     */   private static final class CacheEntry<V> implements Comparable<CacheEntry<V>> {
/*     */     final long key;
/*     */     
/*     */     final V value;
/*     */     
/* 689 */     volatile long lastAccessed = 0L;
/*     */     
/* 690 */     long lastAccessedCopy = 0L;
/*     */     
/*     */     public CacheEntry(long key, V value, long lastAccessed) {
/* 694 */       this.key = key;
/* 695 */       this.value = value;
/* 696 */       this.lastAccessed = lastAccessed;
/*     */     }
/*     */     
/*     */     public int compareTo(CacheEntry<V> that) {
/* 701 */       if (this.lastAccessedCopy == that.lastAccessedCopy)
/* 701 */         return 0; 
/* 702 */       return (this.lastAccessedCopy < that.lastAccessedCopy) ? 1 : -1;
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 707 */       return this.value.hashCode();
/*     */     }
/*     */     
/*     */     public boolean equals(Object obj) {
/* 712 */       return this.value.equals(obj);
/*     */     }
/*     */     
/*     */     public String toString() {
/* 717 */       return "key: " + this.key + " value: " + this.value + " lastAccessed:" + this.lastAccessed;
/*     */     }
/*     */   }
/*     */   
/*     */   protected void evictedEntry(long key, V value) {}
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\mapdb\LongConcurrentLRUMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */