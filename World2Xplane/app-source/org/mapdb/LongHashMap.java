/*     */ package org.mapdb;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Arrays;
/*     */ import java.util.ConcurrentModificationException;
/*     */ import java.util.Iterator;
/*     */ import java.util.NoSuchElementException;
/*     */ import java.util.Random;
/*     */ 
/*     */ public class LongHashMap<V> extends LongMap<V> implements Serializable {
/*     */   private static final long serialVersionUID = 362340234235222265L;
/*     */   
/*     */   transient int elementCount;
/*     */   
/*     */   transient Entry<V>[] elementData;
/*     */   
/*  45 */   transient int modCount = 0;
/*     */   
/*     */   private static final int DEFAULT_SIZE = 16;
/*     */   
/*     */   final float loadFactor;
/*     */   
/*  62 */   protected final long hashSalt = hashSaltValue();
/*     */   
/*     */   int threshold;
/*     */   
/*     */   protected long hashSaltValue() {
/*  65 */     return (new Random()).nextLong();
/*     */   }
/*     */   
/*     */   static class Entry<V> {
/*     */     final int origKeyHash;
/*     */     
/*     */     final long key;
/*     */     
/*     */     V value;
/*     */     
/*     */     Entry<V> next;
/*     */     
/*     */     public Entry(long key, int hash) {
/*  84 */       this.key = key;
/*  85 */       this.origKeyHash = hash;
/*     */     }
/*     */   }
/*     */   
/*     */   private static class AbstractMapIterator<V> {
/*  90 */     private int position = 0;
/*     */     
/*     */     int expectedModCount;
/*     */     
/*     */     LongHashMap.Entry<V> futureEntry;
/*     */     
/*     */     LongHashMap.Entry<V> currentEntry;
/*     */     
/*     */     LongHashMap.Entry<V> prevEntry;
/*     */     
/*     */     final LongHashMap<V> associatedMap;
/*     */     
/*     */     AbstractMapIterator(LongHashMap<V> hm) {
/*  99 */       this.associatedMap = hm;
/* 100 */       this.expectedModCount = hm.modCount;
/* 101 */       this.futureEntry = null;
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/* 105 */       if (this.futureEntry != null)
/* 106 */         return true; 
/* 108 */       while (this.position < this.associatedMap.elementData.length) {
/* 109 */         if (this.associatedMap.elementData[this.position] == null) {
/* 110 */           this.position++;
/*     */           continue;
/*     */         } 
/* 112 */         return true;
/*     */       } 
/* 115 */       return false;
/*     */     }
/*     */     
/*     */     final void checkConcurrentMod() throws ConcurrentModificationException {
/* 119 */       if (this.expectedModCount != this.associatedMap.modCount)
/* 120 */         throw new ConcurrentModificationException(); 
/*     */     }
/*     */     
/*     */     final void makeNext() {
/* 125 */       checkConcurrentMod();
/* 126 */       if (!hasNext())
/* 127 */         throw new NoSuchElementException(); 
/* 129 */       if (this.futureEntry == null) {
/* 130 */         this.currentEntry = this.associatedMap.elementData[this.position++];
/* 131 */         this.futureEntry = this.currentEntry.next;
/* 132 */         this.prevEntry = null;
/*     */       } else {
/* 134 */         if (this.currentEntry != null)
/* 135 */           this.prevEntry = this.currentEntry; 
/* 137 */         this.currentEntry = this.futureEntry;
/* 138 */         this.futureEntry = this.futureEntry.next;
/*     */       } 
/*     */     }
/*     */     
/*     */     public final void remove() {
/* 143 */       checkConcurrentMod();
/* 144 */       if (this.currentEntry == null)
/* 145 */         throw new IllegalStateException(); 
/* 147 */       if (this.prevEntry == null) {
/* 148 */         int index = this.currentEntry.origKeyHash & this.associatedMap.elementData.length - 1;
/* 149 */         this.associatedMap.elementData[index] = (this.associatedMap.elementData[index]).next;
/*     */       } else {
/* 151 */         this.prevEntry.next = this.currentEntry.next;
/*     */       } 
/* 153 */       this.currentEntry = null;
/* 154 */       this.expectedModCount++;
/* 155 */       this.associatedMap.modCount++;
/* 156 */       this.associatedMap.elementCount--;
/*     */     }
/*     */   }
/*     */   
/*     */   private static class EntryIterator<V> extends AbstractMapIterator<V> implements LongMap.LongMapIterator<V> {
/*     */     EntryIterator(LongHashMap<V> map) {
/* 165 */       super(map);
/*     */     }
/*     */     
/*     */     public boolean moveToNext() {
/* 171 */       if (!hasNext())
/* 171 */         return false; 
/* 172 */       makeNext();
/* 173 */       return true;
/*     */     }
/*     */     
/*     */     public long key() {
/* 178 */       return this.currentEntry.key;
/*     */     }
/*     */     
/*     */     public V value() {
/* 183 */       return this.currentEntry.value;
/*     */     }
/*     */   }
/*     */   
/*     */   private static class ValueIterator<V> extends AbstractMapIterator<V> implements Iterator<V> {
/*     */     ValueIterator(LongHashMap<V> map) {
/* 191 */       super(map);
/*     */     }
/*     */     
/*     */     public V next() {
/* 196 */       makeNext();
/* 197 */       return this.currentEntry.value;
/*     */     }
/*     */   }
/*     */   
/*     */   Entry<V>[] newElementArray(int s) {
/* 208 */     return (Entry<V>[])new Entry[s];
/*     */   }
/*     */   
/*     */   public LongHashMap() {
/* 215 */     this(16);
/*     */   }
/*     */   
/*     */   public LongHashMap(int capacity) {
/* 227 */     this(capacity, 0.75F);
/*     */   }
/*     */   
/*     */   private static int calculateCapacity(int x) {
/* 239 */     if (x >= 1073741824)
/* 240 */       return 1073741824; 
/* 242 */     if (x == 0)
/* 243 */       return 16; 
/* 245 */     x--;
/* 246 */     x |= x >> 1;
/* 247 */     x |= x >> 2;
/* 248 */     x |= x >> 4;
/* 249 */     x |= x >> 8;
/* 250 */     x |= x >> 16;
/* 251 */     return x + 1;
/*     */   }
/*     */   
/*     */   public LongHashMap(int capacity, float loadFactor) {
/* 267 */     if (capacity >= 0 && loadFactor > 0.0F) {
/* 268 */       capacity = calculateCapacity(capacity);
/* 269 */       this.elementCount = 0;
/* 270 */       this.elementData = newElementArray(capacity);
/* 271 */       this.loadFactor = loadFactor;
/* 272 */       computeThreshold();
/*     */     } else {
/* 274 */       throw new IllegalArgumentException();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void clear() {
/* 286 */     if (this.elementCount > 0) {
/* 287 */       this.elementCount = 0;
/* 288 */       Arrays.fill((Object[])this.elementData, (Object)null);
/* 289 */       this.modCount++;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeThreshold() {
/* 298 */     this.threshold = (int)(this.elementData.length * this.loadFactor);
/*     */   }
/*     */   
/*     */   public V get(long key) {
/* 311 */     Entry<V> m = getEntry(key);
/* 312 */     if (m != null)
/* 313 */       return m.value; 
/* 315 */     return null;
/*     */   }
/*     */   
/*     */   final Entry<V> getEntry(long key) {
/* 319 */     int hash = longHash(key ^ this.hashSalt);
/* 320 */     int index = hash & this.elementData.length - 1;
/* 321 */     return findNonNullKeyEntry(key, index, hash);
/*     */   }
/*     */   
/*     */   final Entry<V> findNonNullKeyEntry(long key, int index, int keyHash) {
/* 325 */     Entry<V> m = this.elementData[index];
/* 326 */     while (m != null && (m.origKeyHash != keyHash || key != m.key))
/* 328 */       m = m.next; 
/* 330 */     return m;
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 344 */     return (this.elementCount == 0);
/*     */   }
/*     */   
/*     */   public V put(long key, V value) {
/* 360 */     int hash = longHash(key ^ this.hashSalt);
/* 361 */     int index = hash & this.elementData.length - 1;
/* 362 */     Entry<V> entry = findNonNullKeyEntry(key, index, hash);
/* 364 */     this.modCount++;
/* 365 */     entry = createHashedEntry(key, index, hash);
/* 366 */     if (entry == null && ++this.elementCount > this.threshold)
/* 367 */       rehash(); 
/* 371 */     V result = entry.value;
/* 372 */     entry.value = value;
/* 373 */     return result;
/*     */   }
/*     */   
/*     */   Entry<V> createHashedEntry(long key, int index, int hash) {
/* 378 */     Entry<V> entry = new Entry<V>(key, hash);
/* 379 */     entry.next = this.elementData[index];
/* 380 */     this.elementData[index] = entry;
/* 381 */     return entry;
/*     */   }
/*     */   
/*     */   void rehash(int capacity) {
/* 387 */     int length = calculateCapacity((capacity == 0) ? 1 : (capacity << 1));
/* 389 */     Entry[] arrayOfEntry = (Entry[])newElementArray(length);
/* 390 */     for (int i = 0; i < this.elementData.length; i++) {
/* 391 */       Entry<V> entry = this.elementData[i];
/* 392 */       this.elementData[i] = null;
/* 393 */       while (entry != null) {
/* 394 */         int index = entry.origKeyHash & length - 1;
/* 395 */         Entry<V> next = entry.next;
/* 396 */         entry.next = arrayOfEntry[index];
/* 397 */         arrayOfEntry[index] = entry;
/* 398 */         entry = next;
/*     */       } 
/*     */     } 
/* 401 */     this.elementData = (Entry<V>[])arrayOfEntry;
/* 402 */     computeThreshold();
/*     */   }
/*     */   
/*     */   void rehash() {
/* 406 */     rehash(this.elementData.length);
/*     */   }
/*     */   
/*     */   public V remove(long key) {
/* 419 */     Entry<V> entry = removeEntry(key);
/* 420 */     if (entry != null)
/* 421 */       return entry.value; 
/* 423 */     return null;
/*     */   }
/*     */   
/*     */   final Entry<V> removeEntry(long key) {
/* 428 */     int index = 0;
/* 430 */     Entry<V> last = null;
/* 432 */     int hash = longHash(key ^ this.hashSalt);
/* 433 */     index = hash & this.elementData.length - 1;
/* 434 */     Entry<V> entry = this.elementData[index];
/* 435 */     while (entry != null && (entry.origKeyHash != hash || key != entry.key)) {
/* 436 */       last = entry;
/* 437 */       entry = entry.next;
/*     */     } 
/* 440 */     if (entry == null)
/* 441 */       return null; 
/* 443 */     if (last == null) {
/* 444 */       this.elementData[index] = entry.next;
/*     */     } else {
/* 446 */       last.next = entry.next;
/*     */     } 
/* 448 */     this.modCount++;
/* 449 */     this.elementCount--;
/* 450 */     return entry;
/*     */   }
/*     */   
/*     */   public int size() {
/* 460 */     return this.elementCount;
/*     */   }
/*     */   
/*     */   public Iterator<V> valuesIterator() {
/* 465 */     return new ValueIterator<V>(this);
/*     */   }
/*     */   
/*     */   public LongMap.LongMapIterator<V> longMapIterator() {
/* 470 */     return new EntryIterator<V>(this);
/*     */   }
/*     */   
/*     */   public static int longHash(long key) {
/* 476 */     int h = (int)(key ^ key >>> 32L);
/* 477 */     h ^= h >>> 20 ^ h >>> 12;
/* 478 */     return h ^ h >>> 7 ^ h >>> 4;
/*     */   }
/*     */   
/*     */   public static int intHash(int h) {
/* 484 */     h ^= h >>> 20 ^ h >>> 12;
/* 485 */     return h ^ h >>> 7 ^ h >>> 4;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\mapdb\LongHashMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */