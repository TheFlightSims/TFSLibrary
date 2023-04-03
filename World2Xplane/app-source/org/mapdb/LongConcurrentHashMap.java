/*     */ package org.mapdb;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Iterator;
/*     */ import java.util.NoSuchElementException;
/*     */ import java.util.Random;
/*     */ import java.util.concurrent.locks.ReentrantLock;
/*     */ 
/*     */ public class LongConcurrentHashMap<V> extends LongMap<V> implements Serializable {
/*     */   private static final long serialVersionUID = 7249069246763182397L;
/*     */   
/*     */   static final int DEFAULT_INITIAL_CAPACITY = 16;
/*     */   
/*  58 */   protected final long hashSalt = (new Random()).nextLong();
/*     */   
/*     */   static final float DEFAULT_LOAD_FACTOR = 0.75F;
/*     */   
/*     */   static final int DEFAULT_CONCURRENCY_LEVEL = 16;
/*     */   
/*     */   static final int MAXIMUM_CAPACITY = 1073741824;
/*     */   
/*     */   static final int MAX_SEGMENTS = 65536;
/*     */   
/*     */   static final int RETRIES_BEFORE_LOCK = 2;
/*     */   
/*     */   final int segmentMask;
/*     */   
/*     */   final int segmentShift;
/*     */   
/*     */   final Segment<V>[] segments;
/*     */   
/*     */   final Segment<V> segmentFor(int hash) {
/* 123 */     return this.segments[hash >>> this.segmentShift & this.segmentMask];
/*     */   }
/*     */   
/*     */   static final class HashEntry<V> {
/*     */     final long key;
/*     */     
/*     */     final int hash;
/*     */     
/*     */     volatile V value;
/*     */     
/*     */     final HashEntry<V> next;
/*     */     
/*     */     HashEntry(long key, int hash, HashEntry<V> next, V value) {
/* 148 */       this.key = key;
/* 149 */       this.hash = hash;
/* 150 */       this.next = next;
/* 151 */       this.value = value;
/*     */     }
/*     */     
/*     */     static <V> HashEntry<V>[] newArray(int i) {
/* 156 */       return (HashEntry<V>[])new HashEntry[i];
/*     */     }
/*     */   }
/*     */   
/*     */   static final class Segment<V> extends ReentrantLock implements Serializable {
/*     */     private static final long serialVersionUID = 2249069246763182397L;
/*     */     
/*     */     volatile transient int count;
/*     */     
/*     */     transient int modCount;
/*     */     
/*     */     transient int threshold;
/*     */     
/*     */     volatile transient LongConcurrentHashMap.HashEntry<V>[] table;
/*     */     
/*     */     final float loadFactor;
/*     */     
/*     */     Segment(int initialCapacity, float lf) {
/* 241 */       super(false);
/* 242 */       this.loadFactor = lf;
/* 243 */       setTable(LongConcurrentHashMap.HashEntry.newArray(initialCapacity));
/*     */     }
/*     */     
/*     */     static <V> Segment<V>[] newArray(int i) {
/* 248 */       return (Segment<V>[])new Segment[i];
/*     */     }
/*     */     
/*     */     void setTable(LongConcurrentHashMap.HashEntry<V>[] newTable) {
/* 256 */       this.threshold = (int)(newTable.length * this.loadFactor);
/* 257 */       this.table = newTable;
/*     */     }
/*     */     
/*     */     LongConcurrentHashMap.HashEntry<V> getFirst(int hash) {
/* 264 */       LongConcurrentHashMap.HashEntry<V>[] tab = this.table;
/* 265 */       return tab[hash & tab.length - 1];
/*     */     }
/*     */     
/*     */     V readValueUnderLock(LongConcurrentHashMap.HashEntry<V> e) {
/* 276 */       lock();
/*     */       try {
/* 278 */         return e.value;
/*     */       } finally {
/* 280 */         unlock();
/*     */       } 
/*     */     }
/*     */     
/*     */     V get(long key, int hash) {
/* 287 */       if (this.count != 0) {
/* 288 */         LongConcurrentHashMap.HashEntry<V> e = getFirst(hash);
/* 289 */         while (e != null) {
/* 290 */           if (e.hash == hash && key == e.key) {
/* 291 */             V v = e.value;
/* 292 */             if (v != null)
/* 293 */               return v; 
/* 294 */             return readValueUnderLock(e);
/*     */           } 
/* 296 */           e = e.next;
/*     */         } 
/*     */       } 
/* 299 */       return null;
/*     */     }
/*     */     
/*     */     boolean containsKey(long key, int hash) {
/* 303 */       if (this.count != 0) {
/* 304 */         LongConcurrentHashMap.HashEntry<V> e = getFirst(hash);
/* 305 */         while (e != null) {
/* 306 */           if (e.hash == hash && key == e.key)
/* 307 */             return true; 
/* 308 */           e = e.next;
/*     */         } 
/*     */       } 
/* 311 */       return false;
/*     */     }
/*     */     
/*     */     boolean containsValue(Object value) {
/* 315 */       if (this.count != 0) {
/* 316 */         LongConcurrentHashMap.HashEntry<V>[] tab = this.table;
/* 318 */         for (LongConcurrentHashMap.HashEntry<V> aTab : tab) {
/* 319 */           for (LongConcurrentHashMap.HashEntry<V> e = aTab; e != null; e = e.next) {
/* 320 */             V v = e.value;
/* 321 */             if (v == null)
/* 322 */               v = readValueUnderLock(e); 
/* 323 */             if (value.equals(v))
/* 324 */               return true; 
/*     */           } 
/*     */         } 
/*     */       } 
/* 328 */       return false;
/*     */     }
/*     */     
/*     */     boolean replace(long key, int hash, V oldValue, V newValue) {
/* 332 */       lock();
/*     */       try {
/* 334 */         LongConcurrentHashMap.HashEntry<V> e = getFirst(hash);
/* 335 */         while (e != null && (e.hash != hash || key != e.key))
/* 336 */           e = e.next; 
/* 338 */         boolean replaced = false;
/* 339 */         if (e != null && oldValue.equals(e.value)) {
/* 340 */           replaced = true;
/* 341 */           e.value = newValue;
/*     */         } 
/* 343 */         return replaced;
/*     */       } finally {
/* 345 */         unlock();
/*     */       } 
/*     */     }
/*     */     
/*     */     V replace(long key, int hash, V newValue) {
/* 350 */       lock();
/*     */       try {
/* 352 */         LongConcurrentHashMap.HashEntry<V> e = getFirst(hash);
/* 353 */         while (e != null && (e.hash != hash || key != e.key))
/* 354 */           e = e.next; 
/* 356 */         V oldValue = null;
/* 357 */         if (e != null) {
/* 358 */           oldValue = e.value;
/* 359 */           e.value = newValue;
/*     */         } 
/* 361 */         return oldValue;
/*     */       } finally {
/* 363 */         unlock();
/*     */       } 
/*     */     }
/*     */     
/*     */     V put(long key, int hash, V value, boolean onlyIfAbsent) {
/* 369 */       lock();
/*     */       try {
/*     */         V oldValue;
/* 371 */         int c = this.count;
/* 372 */         if (c++ > this.threshold)
/* 373 */           rehash(); 
/* 374 */         LongConcurrentHashMap.HashEntry<V>[] tab = this.table;
/* 375 */         int index = hash & tab.length - 1;
/* 376 */         LongConcurrentHashMap.HashEntry<V> first = tab[index];
/* 377 */         LongConcurrentHashMap.HashEntry<V> e = first;
/* 378 */         while (e != null && (e.hash != hash || key != e.key))
/* 379 */           e = e.next; 
/* 382 */         if (e != null) {
/* 383 */           oldValue = e.value;
/* 384 */           if (!onlyIfAbsent)
/* 385 */             e.value = value; 
/*     */         } else {
/* 388 */           oldValue = null;
/* 389 */           this.modCount++;
/* 390 */           tab[index] = new LongConcurrentHashMap.HashEntry<V>(key, hash, first, value);
/* 391 */           this.count = c;
/*     */         } 
/* 393 */         return oldValue;
/*     */       } finally {
/* 395 */         unlock();
/*     */       } 
/*     */     }
/*     */     
/*     */     void rehash() {
/* 400 */       LongConcurrentHashMap.HashEntry<V>[] oldTable = this.table;
/* 401 */       int oldCapacity = oldTable.length;
/* 402 */       if (oldCapacity >= 1073741824)
/*     */         return; 
/* 419 */       LongConcurrentHashMap.HashEntry[] arrayOfHashEntry = (LongConcurrentHashMap.HashEntry[])LongConcurrentHashMap.HashEntry.newArray(oldCapacity << 1);
/* 420 */       this.threshold = (int)(arrayOfHashEntry.length * this.loadFactor);
/* 421 */       int sizeMask = arrayOfHashEntry.length - 1;
/* 422 */       for (LongConcurrentHashMap.HashEntry<V> e : oldTable) {
/* 425 */         if (e != null) {
/* 426 */           LongConcurrentHashMap.HashEntry<V> next = e.next;
/* 427 */           int idx = e.hash & sizeMask;
/* 430 */           if (next == null) {
/* 431 */             arrayOfHashEntry[idx] = e;
/*     */           } else {
/* 435 */             LongConcurrentHashMap.HashEntry<V> lastRun = e;
/* 436 */             int lastIdx = idx;
/* 437 */             LongConcurrentHashMap.HashEntry<V> last = next;
/* 438 */             for (; last != null; 
/* 439 */               last = last.next) {
/* 440 */               int k = last.hash & sizeMask;
/* 441 */               if (k != lastIdx) {
/* 442 */                 lastIdx = k;
/* 443 */                 lastRun = last;
/*     */               } 
/*     */             } 
/* 446 */             arrayOfHashEntry[lastIdx] = lastRun;
/* 449 */             for (LongConcurrentHashMap.HashEntry<V> p = e; p != lastRun; p = p.next) {
/* 450 */               int k = p.hash & sizeMask;
/* 451 */               LongConcurrentHashMap.HashEntry<V> n = arrayOfHashEntry[k];
/* 452 */               arrayOfHashEntry[k] = new LongConcurrentHashMap.HashEntry<V>(p.key, p.hash, n, p.value);
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/* 458 */       this.table = (LongConcurrentHashMap.HashEntry<V>[])arrayOfHashEntry;
/*     */     }
/*     */     
/*     */     V remove(long key, int hash, Object value) {
/* 465 */       lock();
/*     */       try {
/* 467 */         int c = this.count - 1;
/* 468 */         LongConcurrentHashMap.HashEntry<V>[] tab = this.table;
/* 469 */         int index = hash & tab.length - 1;
/* 470 */         LongConcurrentHashMap.HashEntry<V> first = tab[index];
/* 471 */         LongConcurrentHashMap.HashEntry<V> e = first;
/* 472 */         while (e != null && (e.hash != hash || key != e.key))
/* 473 */           e = e.next; 
/* 475 */         V oldValue = null;
/* 476 */         if (e != null) {
/* 477 */           V v = e.value;
/* 478 */           if (value == null || value.equals(v)) {
/* 479 */             oldValue = v;
/* 483 */             this.modCount++;
/* 484 */             LongConcurrentHashMap.HashEntry<V> newFirst = e.next;
/* 485 */             for (LongConcurrentHashMap.HashEntry<V> p = first; p != e; p = p.next)
/* 486 */               newFirst = new LongConcurrentHashMap.HashEntry<V>(p.key, p.hash, newFirst, p.value); 
/* 488 */             tab[index] = newFirst;
/* 489 */             this.count = c;
/*     */           } 
/*     */         } 
/* 492 */         return oldValue;
/*     */       } finally {
/* 494 */         unlock();
/*     */       } 
/*     */     }
/*     */     
/*     */     void clear() {
/* 499 */       if (this.count != 0) {
/* 500 */         lock();
/*     */         try {
/* 502 */           LongConcurrentHashMap.HashEntry<V>[] tab = this.table;
/* 503 */           for (int i = 0; i < tab.length; i++)
/* 504 */             tab[i] = null; 
/* 505 */           this.modCount++;
/* 506 */           this.count = 0;
/*     */         } finally {
/* 508 */           unlock();
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public LongConcurrentHashMap(int initialCapacity, float loadFactor, int concurrencyLevel) {
/* 536 */     if (loadFactor <= 0.0F || initialCapacity < 0 || concurrencyLevel <= 0)
/* 537 */       throw new IllegalArgumentException(); 
/* 539 */     if (concurrencyLevel > 65536)
/* 540 */       concurrencyLevel = 65536; 
/* 543 */     int sshift = 0;
/* 544 */     int ssize = 1;
/* 545 */     while (ssize < concurrencyLevel) {
/* 546 */       sshift++;
/* 547 */       ssize <<= 1;
/*     */     } 
/* 549 */     this.segmentShift = 32 - sshift;
/* 550 */     this.segmentMask = ssize - 1;
/* 551 */     this.segments = Segment.newArray(ssize);
/* 553 */     if (initialCapacity > 1073741824)
/* 554 */       initialCapacity = 1073741824; 
/* 555 */     int c = initialCapacity / ssize;
/* 556 */     if (c * ssize < initialCapacity)
/* 557 */       c++; 
/* 558 */     int cap = 1;
/* 559 */     while (cap < c)
/* 560 */       cap <<= 1; 
/* 562 */     for (int i = 0; i < this.segments.length; i++)
/* 563 */       this.segments[i] = new Segment<V>(cap, loadFactor); 
/*     */   }
/*     */   
/*     */   public LongConcurrentHashMap(int initialCapacity) {
/* 576 */     this(initialCapacity, 0.75F, 16);
/*     */   }
/*     */   
/*     */   public LongConcurrentHashMap() {
/* 584 */     this(16, 0.75F, 16);
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 594 */     Segment<V>[] segments = this.segments;
/* 604 */     int[] mc = new int[segments.length];
/* 605 */     int mcsum = 0;
/*     */     int i;
/* 606 */     for (i = 0; i < segments.length; i++) {
/* 607 */       if ((segments[i]).count != 0)
/* 608 */         return false; 
/* 610 */       mc[i] = (segments[i]).modCount;
/* 610 */       mcsum += (segments[i]).modCount;
/*     */     } 
/* 615 */     if (mcsum != 0)
/* 616 */       for (i = 0; i < segments.length; i++) {
/* 617 */         if ((segments[i]).count != 0 || mc[i] != (segments[i]).modCount)
/* 619 */           return false; 
/*     */       }  
/* 622 */     return true;
/*     */   }
/*     */   
/*     */   public int size() {
/* 634 */     Segment<V>[] segments = this.segments;
/* 635 */     long sum = 0L;
/* 636 */     long check = 0L;
/* 637 */     int[] mc = new int[segments.length];
/* 640 */     for (int k = 0; k < 2; k++) {
/* 641 */       check = 0L;
/* 642 */       sum = 0L;
/* 643 */       int mcsum = 0;
/*     */       int i;
/* 644 */       for (i = 0; i < segments.length; i++) {
/* 645 */         sum += (segments[i]).count;
/* 646 */         mc[i] = (segments[i]).modCount;
/* 646 */         mcsum += (segments[i]).modCount;
/*     */       } 
/* 648 */       if (mcsum != 0)
/* 649 */         for (i = 0; i < segments.length; i++) {
/* 650 */           check += (segments[i]).count;
/* 651 */           if (mc[i] != (segments[i]).modCount) {
/* 652 */             check = -1L;
/*     */             break;
/*     */           } 
/*     */         }  
/* 657 */       if (check == sum)
/*     */         break; 
/*     */     } 
/* 660 */     if (check != sum) {
/* 661 */       sum = 0L;
/* 662 */       for (Segment<V> segment : segments)
/* 662 */         segment.lock(); 
/* 663 */       for (Segment<V> segment : segments)
/* 663 */         sum += segment.count; 
/* 664 */       for (Segment<V> segment : segments)
/* 664 */         segment.unlock(); 
/*     */     } 
/* 666 */     if (sum > 2147483647L)
/* 667 */       return Integer.MAX_VALUE; 
/* 669 */     return (int)sum;
/*     */   }
/*     */   
/*     */   public Iterator<V> valuesIterator() {
/* 674 */     return new ValueIterator();
/*     */   }
/*     */   
/*     */   public LongMap.LongMapIterator<V> longMapIterator() {
/* 679 */     return new MapIterator();
/*     */   }
/*     */   
/*     */   public V get(long key) {
/* 695 */     int hash = LongHashMap.longHash(key ^ this.hashSalt);
/* 696 */     return segmentFor(hash).get(key, hash);
/*     */   }
/*     */   
/*     */   public boolean containsKey(long key) {
/* 709 */     int hash = LongHashMap.longHash(key ^ this.hashSalt);
/* 710 */     return segmentFor(hash).containsKey(key, hash);
/*     */   }
/*     */   
/*     */   public boolean containsValue(Object value) {
/* 725 */     if (value == null)
/* 726 */       throw new NullPointerException(); 
/* 730 */     Segment<V>[] segments = this.segments;
/* 731 */     int[] mc = new int[segments.length];
/* 734 */     for (int k = 0; k < 2; k++) {
/* 736 */       int mcsum = 0;
/* 737 */       for (int i = 0; i < segments.length; i++) {
/* 739 */         mc[i] = (segments[i]).modCount;
/* 739 */         mcsum += (segments[i]).modCount;
/* 740 */         if (segments[i].containsValue(value))
/* 741 */           return true; 
/*     */       } 
/* 743 */       boolean cleanSweep = true;
/* 744 */       if (mcsum != 0)
/* 745 */         for (int j = 0; j < segments.length; j++) {
/* 747 */           if (mc[j] != (segments[j]).modCount) {
/* 748 */             cleanSweep = false;
/*     */             break;
/*     */           } 
/*     */         }  
/* 753 */       if (cleanSweep)
/* 754 */         return false; 
/*     */     } 
/* 757 */     for (Segment<V> segment : segments)
/* 757 */       segment.lock(); 
/* 758 */     boolean found = false;
/*     */     try {
/* 760 */       for (Segment<V> segment : segments) {
/* 761 */         if (segment.containsValue(value)) {
/* 762 */           found = true;
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } finally {
/* 767 */       for (Segment<V> segment : segments)
/* 767 */         segment.unlock(); 
/*     */     } 
/* 769 */     return found;
/*     */   }
/*     */   
/*     */   public V put(long key, V value) {
/* 788 */     if (value == null)
/* 789 */       throw new NullPointerException(); 
/* 790 */     int hash = LongHashMap.longHash(key ^ this.hashSalt);
/* 791 */     return segmentFor(hash).put(key, hash, value, false);
/*     */   }
/*     */   
/*     */   public V putIfAbsent(long key, V value) {
/* 802 */     if (value == null)
/* 803 */       throw new NullPointerException(); 
/* 804 */     int hash = LongHashMap.longHash(key ^ this.hashSalt);
/* 805 */     return segmentFor(hash).put(key, hash, value, true);
/*     */   }
/*     */   
/*     */   public V remove(long key) {
/* 820 */     int hash = LongHashMap.longHash(key ^ this.hashSalt);
/* 821 */     return segmentFor(hash).remove(key, hash, (Object)null);
/*     */   }
/*     */   
/*     */   public boolean remove(long key, Object value) {
/* 830 */     int hash = LongHashMap.longHash(key ^ this.hashSalt);
/* 831 */     return (value != null && segmentFor(hash).remove(key, hash, value) != null);
/*     */   }
/*     */   
/*     */   public boolean replace(long key, V oldValue, V newValue) {
/* 840 */     if (oldValue == null || newValue == null)
/* 841 */       throw new NullPointerException(); 
/* 842 */     int hash = LongHashMap.longHash(key ^ this.hashSalt);
/* 843 */     return segmentFor(hash).replace(key, hash, oldValue, newValue);
/*     */   }
/*     */   
/*     */   public V replace(long key, V value) {
/* 854 */     if (value == null)
/* 855 */       throw new NullPointerException(); 
/* 856 */     int hash = LongHashMap.longHash(key ^ this.hashSalt);
/* 857 */     return segmentFor(hash).replace(key, hash, value);
/*     */   }
/*     */   
/*     */   public void clear() {
/* 865 */     for (Segment<V> segment : this.segments)
/* 865 */       segment.clear(); 
/*     */   }
/*     */   
/*     */   abstract class HashIterator {
/*     */     int nextSegmentIndex;
/*     */     
/*     */     int nextTableIndex;
/*     */     
/*     */     LongConcurrentHashMap.HashEntry<V>[] currentTable;
/*     */     
/*     */     LongConcurrentHashMap.HashEntry<V> nextEntry;
/*     */     
/*     */     LongConcurrentHashMap.HashEntry<V> lastReturned;
/*     */     
/*     */     HashIterator() {
/* 882 */       this.nextSegmentIndex = LongConcurrentHashMap.this.segments.length - 1;
/* 883 */       this.nextTableIndex = -1;
/* 884 */       advance();
/*     */     }
/*     */     
/*     */     final void advance() {
/* 889 */       if (this.nextEntry != null && (this.nextEntry = this.nextEntry.next) != null)
/*     */         return; 
/* 892 */       while (this.nextTableIndex >= 0) {
/* 893 */         if ((this.nextEntry = this.currentTable[this.nextTableIndex--]) != null)
/*     */           return; 
/*     */       } 
/* 897 */       while (this.nextSegmentIndex >= 0) {
/* 898 */         LongConcurrentHashMap.Segment<V> seg = LongConcurrentHashMap.this.segments[this.nextSegmentIndex--];
/* 899 */         if (seg.count != 0) {
/* 900 */           this.currentTable = seg.table;
/* 901 */           for (int j = this.currentTable.length - 1; j >= 0; j--) {
/* 902 */             if ((this.nextEntry = this.currentTable[j]) != null) {
/* 903 */               this.nextTableIndex = j - 1;
/*     */               return;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/* 911 */       return (this.nextEntry != null);
/*     */     }
/*     */     
/*     */     LongConcurrentHashMap.HashEntry<V> nextEntry() {
/* 914 */       if (this.nextEntry == null)
/* 915 */         throw new NoSuchElementException(); 
/* 916 */       this.lastReturned = this.nextEntry;
/* 917 */       advance();
/* 918 */       return this.lastReturned;
/*     */     }
/*     */     
/*     */     public void remove() {
/* 922 */       if (this.lastReturned == null)
/* 923 */         throw new IllegalStateException(); 
/* 924 */       LongConcurrentHashMap.this.remove(this.lastReturned.key);
/* 925 */       this.lastReturned = null;
/*     */     }
/*     */   }
/*     */   
/*     */   final class KeyIterator extends HashIterator implements Iterator<Long> {
/*     */     public Long next() {
/* 934 */       return Long.valueOf((nextEntry()).key);
/*     */     }
/*     */   }
/*     */   
/*     */   final class ValueIterator extends HashIterator implements Iterator<V> {
/*     */     public V next() {
/* 942 */       return (nextEntry()).value;
/*     */     }
/*     */   }
/*     */   
/*     */   final class MapIterator extends HashIterator implements LongMap.LongMapIterator<V> {
/*     */     private long key;
/*     */     
/*     */     private V value;
/*     */     
/*     */     public boolean moveToNext() {
/* 953 */       if (!hasNext())
/* 953 */         return false; 
/* 954 */       LongConcurrentHashMap.HashEntry<V> next = nextEntry();
/* 955 */       this.key = next.key;
/* 956 */       this.value = next.value;
/* 957 */       return true;
/*     */     }
/*     */     
/*     */     public long key() {
/* 962 */       return this.key;
/*     */     }
/*     */     
/*     */     public V value() {
/* 967 */       return this.value;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\mapdb\LongConcurrentHashMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */