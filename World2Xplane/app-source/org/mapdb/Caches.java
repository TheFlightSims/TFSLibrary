/*     */ package org.mapdb;
/*     */ 
/*     */ import java.lang.ref.ReferenceQueue;
/*     */ import java.lang.ref.SoftReference;
/*     */ import java.lang.ref.WeakReference;
/*     */ import java.util.Arrays;
/*     */ import java.util.Random;
/*     */ import java.util.concurrent.locks.Lock;
/*     */ import java.util.concurrent.locks.ReentrantLock;
/*     */ 
/*     */ public final class Caches {
/*     */   public static class LRU extends EngineWrapper {
/*     */     protected LongMap<Object> cache;
/*     */     
/*  43 */     protected final ReentrantLock[] locks = new ReentrantLock[128];
/*     */     
/*     */     public LRU(Engine engine, int cacheSize, boolean disableLocks) {
/*  51 */       this(engine, new LongConcurrentLRUMap(cacheSize, (int)(cacheSize * 0.8D)), disableLocks);
/*     */     }
/*     */     
/*     */     public LRU(Engine engine, LongMap<Object> cache, boolean disableLocks) {
/*  55 */       super(engine);
/*     */       for (int i = 0; i < this.locks.length; i++)
/*     */         this.locks[i] = new ReentrantLock(false); 
/*  56 */       this.cache = cache;
/*     */     }
/*     */     
/*     */     public <A> long put(A value, Serializer<A> serializer) {
/*  61 */       long recid = super.put(value, serializer);
/*  62 */       LongMap<Object> cache2 = checkClosed(this.cache);
/*  63 */       Lock lock = this.locks[Store.lockPos(recid)];
/*  64 */       lock.lock();
/*     */       try {
/*  66 */         cache2.put(recid, value);
/*     */       } finally {
/*  68 */         lock.unlock();
/*     */       } 
/*  70 */       return recid;
/*     */     }
/*     */     
/*     */     public <A> A get(long recid, Serializer<A> serializer) {
/*  76 */       LongMap<Object> cache2 = checkClosed(this.cache);
/*  77 */       Object ret = cache2.get(recid);
/*  78 */       if (ret != null)
/*  79 */         return (A)ret; 
/*  81 */       Lock lock = this.locks[Store.lockPos(recid)];
/*  82 */       lock.lock();
/*     */       try {
/*  84 */         ret = super.get(recid, serializer);
/*  85 */         if (ret != null)
/*  86 */           cache2.put(recid, ret); 
/*  87 */         return (A)ret;
/*     */       } finally {
/*  89 */         lock.unlock();
/*     */       } 
/*     */     }
/*     */     
/*     */     public <A> void update(long recid, A value, Serializer<A> serializer) {
/*  95 */       LongMap<Object> cache2 = checkClosed(this.cache);
/*  97 */       Lock lock = this.locks[Store.lockPos(recid)];
/*  98 */       lock.lock();
/*     */       try {
/* 100 */         cache2.put(recid, value);
/* 101 */         super.update(recid, value, serializer);
/*     */       } finally {
/* 103 */         lock.unlock();
/*     */       } 
/*     */     }
/*     */     
/*     */     public <A> void delete(long recid, Serializer<A> serializer) {
/* 109 */       LongMap<Object> cache2 = checkClosed(this.cache);
/* 111 */       Lock lock = this.locks[Store.lockPos(recid)];
/* 112 */       lock.lock();
/*     */       try {
/* 114 */         cache2.remove(recid);
/* 115 */         super.delete(recid, serializer);
/*     */       } finally {
/* 117 */         lock.unlock();
/*     */       } 
/*     */     }
/*     */     
/*     */     public <A> boolean compareAndSwap(long recid, A expectedOldValue, A newValue, Serializer<A> serializer) {
/* 123 */       Engine engine = getWrappedEngine();
/* 124 */       LongMap<A> cache2 = checkClosed(this.cache);
/* 126 */       Lock lock = this.locks[Store.lockPos(recid)];
/* 127 */       lock.lock();
/*     */       try {
/* 129 */         Object oldValue = cache2.get(recid);
/* 130 */         if (oldValue == expectedOldValue || (oldValue != null && oldValue.equals(expectedOldValue))) {
/* 132 */           cache2.put(recid, newValue);
/* 133 */           engine.update(recid, newValue, serializer);
/* 134 */           return true;
/*     */         } 
/* 136 */         boolean ret = engine.compareAndSwap(recid, expectedOldValue, newValue, serializer);
/* 137 */         if (ret)
/* 137 */           cache2.put(recid, newValue); 
/* 138 */         return ret;
/*     */       } finally {
/* 141 */         lock.unlock();
/*     */       } 
/*     */     }
/*     */     
/*     */     public void close() {
/* 148 */       this.cache = null;
/* 149 */       super.close();
/*     */     }
/*     */     
/*     */     public void rollback() {
/* 155 */       ((LongMap)checkClosed(this.cache)).clear();
/* 156 */       super.rollback();
/*     */     }
/*     */     
/*     */     public void clearCache() {
/* 161 */       this.cache.clear();
/* 162 */       super.clearCache();
/*     */     }
/*     */   }
/*     */   
/*     */   public static class HashTable extends EngineWrapper implements Engine {
/* 178 */     protected final ReentrantLock[] locks = new ReentrantLock[128];
/*     */     
/*     */     protected HashItem[] items;
/*     */     
/*     */     protected final int cacheMaxSize;
/*     */     
/*     */     protected final int cacheMaxSizeMask;
/*     */     
/*     */     protected final long hashSalt;
/*     */     
/*     */     private static class HashItem {
/*     */       final long key;
/*     */       
/*     */       final Object val;
/*     */       
/*     */       private HashItem(long key, Object val) {
/* 200 */         this.key = key;
/* 201 */         this.val = val;
/*     */       }
/*     */     }
/*     */     
/*     */     public HashTable(Engine engine, int cacheMaxSize, boolean disableLocks) {
/* 208 */       super(engine);
/*     */       for (int i = 0; i < this.locks.length; i++)
/*     */         this.locks[i] = new ReentrantLock(false); 
/*     */       this.hashSalt = (new Random()).nextLong();
/* 209 */       this.items = new HashItem[cacheMaxSize];
/* 210 */       this.cacheMaxSize = 1 << 32 - Integer.numberOfLeadingZeros(cacheMaxSize - 1);
/* 211 */       this.cacheMaxSizeMask = cacheMaxSize - 1;
/*     */     }
/*     */     
/*     */     public <A> long put(A value, Serializer<A> serializer) {
/* 216 */       long recid = getWrappedEngine().put(value, serializer);
/* 217 */       HashItem[] items2 = checkClosed(this.items);
/* 219 */       Lock lock = this.locks[Store.lockPos(recid)];
/* 220 */       lock.lock();
/*     */       try {
/* 222 */         items2[position(recid)] = new HashItem(recid, value);
/*     */       } finally {
/* 224 */         lock.unlock();
/*     */       } 
/* 226 */       return recid;
/*     */     }
/*     */     
/*     */     public <A> A get(long recid, Serializer<A> serializer) {
/* 232 */       int pos = position(recid);
/* 233 */       Lock lock = this.locks[Store.lockPos(recid)];
/* 234 */       lock.lock();
/*     */       try {
/* 238 */         HashItem[] items2 = checkClosed(this.items);
/* 239 */         HashItem item = items2[pos];
/* 240 */         if (item != null && recid == item.key)
/* 241 */           return (A)item.val; 
/* 243 */         Engine engine = getWrappedEngine();
/* 246 */         A value = engine.get(recid, serializer);
/* 247 */         if (value != null)
/* 248 */           items2[pos] = new HashItem(recid, value); 
/* 249 */         return value;
/*     */       } finally {
/* 251 */         lock.unlock();
/*     */       } 
/*     */     }
/*     */     
/*     */     private int position(long recid) {
/* 256 */       return LongHashMap.longHash(recid ^ this.hashSalt) & this.cacheMaxSizeMask;
/*     */     }
/*     */     
/*     */     public <A> void update(long recid, A value, Serializer<A> serializer) {
/* 261 */       int pos = position(recid);
/* 262 */       HashItem[] items2 = checkClosed(this.items);
/* 263 */       HashItem item = new HashItem(recid, value);
/* 264 */       Engine engine = getWrappedEngine();
/* 266 */       Lock lock = this.locks[Store.lockPos(recid)];
/* 267 */       lock.lock();
/*     */       try {
/* 269 */         items2[pos] = item;
/* 270 */         engine.update(recid, value, serializer);
/*     */       } finally {
/* 272 */         lock.unlock();
/*     */       } 
/*     */     }
/*     */     
/*     */     public <A> boolean compareAndSwap(long recid, A expectedOldValue, A newValue, Serializer<A> serializer) {
/* 278 */       int pos = position(recid);
/* 279 */       HashItem[] items2 = checkClosed(this.items);
/* 280 */       Engine engine = getWrappedEngine();
/* 282 */       Lock lock = this.locks[Store.lockPos(recid)];
/* 283 */       lock.lock();
/*     */       try {
/* 285 */         HashItem item = items2[pos];
/* 286 */         if (item != null && item.key == recid) {
/* 288 */           if (item.val == expectedOldValue || item.val.equals(expectedOldValue)) {
/* 290 */             items2[pos] = new HashItem(recid, newValue);
/* 291 */             engine.update(recid, newValue, serializer);
/* 292 */             return true;
/*     */           } 
/* 294 */           return false;
/*     */         } 
/* 297 */         boolean ret = engine.compareAndSwap(recid, expectedOldValue, newValue, serializer);
/* 298 */         if (ret)
/* 298 */           items2[pos] = new HashItem(recid, newValue); 
/* 299 */         return ret;
/*     */       } finally {
/* 302 */         lock.unlock();
/*     */       } 
/*     */     }
/*     */     
/*     */     public <A> void delete(long recid, Serializer<A> serializer) {
/* 308 */       int pos = position(recid);
/* 309 */       HashItem[] items2 = checkClosed(this.items);
/* 310 */       Engine engine = getWrappedEngine();
/* 312 */       Lock lock = this.locks[Store.lockPos(recid)];
/* 313 */       lock.lock();
/*     */       try {
/* 315 */         HashItem item = items2[pos];
/* 316 */         if (item != null && recid == item.key)
/* 317 */           this.items[pos] = null; 
/* 318 */         engine.delete(recid, serializer);
/*     */       } finally {
/* 320 */         lock.unlock();
/*     */       } 
/*     */     }
/*     */     
/*     */     public void close() {
/* 328 */       super.close();
/* 330 */       this.items = null;
/*     */     }
/*     */     
/*     */     public void rollback() {
/* 336 */       for (int i = 0; i < this.items.length; i++)
/* 337 */         this.items[i] = null; 
/* 338 */       super.rollback();
/*     */     }
/*     */     
/*     */     public void clearCache() {
/* 343 */       Arrays.fill((Object[])this.items, (Object)null);
/* 344 */       super.clearCache();
/*     */     }
/*     */   }
/*     */   
/*     */   public static class WeakSoftRef extends EngineWrapper implements Engine {
/* 359 */     protected final ReentrantLock[] locks = new ReentrantLock[128];
/*     */     
/*     */     protected ReferenceQueue<Object> queue;
/*     */     
/*     */     protected Thread queueThread;
/*     */     
/*     */     protected LongConcurrentHashMap<CacheItem> items;
/*     */     
/*     */     protected final boolean useWeakRef;
/*     */     
/*     */     protected static final class CacheWeakItem<A> extends WeakReference<A> implements CacheItem {
/*     */       final long recid;
/*     */       
/*     */       public CacheWeakItem(A referent, ReferenceQueue<A> q, long recid) {
/* 376 */         super(referent, q);
/* 377 */         this.recid = recid;
/*     */       }
/*     */       
/*     */       public long getRecid() {
/* 382 */         return this.recid;
/*     */       }
/*     */     }
/*     */     
/*     */     protected static final class CacheSoftItem<A> extends SoftReference<A> implements CacheItem {
/*     */       final long recid;
/*     */       
/*     */       public CacheSoftItem(A referent, ReferenceQueue<A> q, long recid) {
/* 391 */         super(referent, q);
/* 392 */         this.recid = recid;
/*     */       }
/*     */       
/*     */       public long getRecid() {
/* 397 */         return this.recid;
/*     */       }
/*     */     }
/*     */     
/*     */     public WeakSoftRef(Engine engine, boolean useWeakRef, boolean disableLocks) {
/* 417 */       super(engine);
/*     */       for (int i = 0; i < this.locks.length; i++)
/*     */         this.locks[i] = new ReentrantLock(false); 
/*     */       this.queue = new ReferenceQueue();
/*     */       this.queueThread = new Thread("MapDB GC collector") {
/*     */           public void run() {
/*     */             Caches.WeakSoftRef.this.runRefQueue();
/*     */           }
/*     */         };
/*     */       this.items = new LongConcurrentHashMap<CacheItem>();
/* 418 */       this.useWeakRef = useWeakRef;
/* 420 */       this.queueThread.setDaemon(true);
/* 421 */       this.queueThread.start();
/*     */     }
/*     */     
/*     */     protected void runRefQueue() {
/*     */       try {
/* 428 */         ReferenceQueue<?> queue = this.queue;
/* 429 */         if (queue == null)
/*     */           return; 
/* 430 */         LongConcurrentHashMap<CacheItem> items = this.items;
/*     */         do {
/* 433 */           CacheItem item = (CacheItem)queue.remove();
/* 434 */           items.remove(item.getRecid(), item);
/* 435 */         } while (!Thread.interrupted());
/*     */         return;
/* 437 */       } catch (InterruptedException e) {
/*     */         return;
/*     */       } 
/*     */     }
/*     */     
/*     */     public <A> long put(A value, Serializer<A> serializer) {
/* 444 */       long recid = getWrappedEngine().put(value, serializer);
/* 445 */       ReferenceQueue<A> q = (ReferenceQueue<A>)checkClosed(this.queue);
/* 446 */       LongConcurrentHashMap<CacheItem> items2 = checkClosed(this.items);
/* 447 */       CacheItem item = this.useWeakRef ? new CacheWeakItem<A>(value, q, recid) : new CacheSoftItem<A>(value, q, recid);
/* 451 */       Lock lock = this.locks[Store.lockPos(recid)];
/* 452 */       lock.lock();
/*     */       try {
/* 454 */         items2.put(recid, item);
/*     */       } finally {
/* 456 */         lock.unlock();
/*     */       } 
/* 458 */       return recid;
/*     */     }
/*     */     
/*     */     public <A> A get(long recid, Serializer<A> serializer) {
/* 464 */       LongConcurrentHashMap<CacheItem> items2 = checkClosed(this.items);
/* 465 */       CacheItem item = items2.get(recid);
/* 466 */       if (item != null) {
/* 467 */         Object o = item.get();
/* 468 */         if (o == null) {
/* 469 */           items2.remove(recid);
/*     */         } else {
/* 471 */           return (A)o;
/*     */         } 
/*     */       } 
/* 475 */       Engine engine = getWrappedEngine();
/* 477 */       Lock lock = this.locks[Store.lockPos(recid)];
/* 478 */       lock.lock();
/*     */       try {
/* 481 */         Object value = engine.get(recid, serializer);
/* 482 */         if (value != null) {
/* 483 */           ReferenceQueue<A> q = (ReferenceQueue<A>)checkClosed(this.queue);
/* 484 */           item = this.useWeakRef ? new CacheWeakItem(value, q, recid) : new CacheSoftItem(value, q, recid);
/* 487 */           items2.put(recid, item);
/*     */         } 
/* 490 */         return (A)value;
/*     */       } finally {
/* 492 */         lock.unlock();
/*     */       } 
/*     */     }
/*     */     
/*     */     public <A> void update(long recid, A value, Serializer<A> serializer) {
/* 499 */       Engine engine = getWrappedEngine();
/* 500 */       ReferenceQueue<A> q = (ReferenceQueue<A>)checkClosed(this.queue);
/* 501 */       LongConcurrentHashMap<CacheItem> items2 = checkClosed(this.items);
/* 502 */       CacheItem item = this.useWeakRef ? new CacheWeakItem<A>(value, q, recid) : new CacheSoftItem<A>(value, q, recid);
/* 506 */       Lock lock = this.locks[Store.lockPos(recid)];
/* 507 */       lock.lock();
/*     */       try {
/* 509 */         items2.put(recid, item);
/* 510 */         engine.update(recid, value, serializer);
/*     */       } finally {
/* 512 */         lock.unlock();
/*     */       } 
/*     */     }
/*     */     
/*     */     public <A> void delete(long recid, Serializer<A> serializer) {
/* 519 */       Engine engine = getWrappedEngine();
/* 520 */       LongMap items2 = checkClosed(this.items);
/* 522 */       Lock lock = this.locks[Store.lockPos(recid)];
/* 523 */       lock.lock();
/*     */       try {
/* 525 */         items2.remove(recid);
/* 526 */         engine.delete(recid, serializer);
/*     */       } finally {
/* 528 */         lock.unlock();
/*     */       } 
/*     */     }
/*     */     
/*     */     public <A> boolean compareAndSwap(long recid, A expectedOldValue, A newValue, Serializer<A> serializer) {
/* 535 */       Engine engine = getWrappedEngine();
/* 536 */       LongMap<CacheItem> items2 = checkClosed(this.items);
/* 537 */       ReferenceQueue<A> q = (ReferenceQueue<A>)checkClosed(this.queue);
/* 540 */       Lock lock = this.locks[Store.lockPos(recid)];
/* 541 */       lock.lock();
/*     */       try {
/* 543 */         CacheItem item = items2.get(recid);
/* 544 */         Object oldValue = (item == null) ? null : item.get();
/* 545 */         if (item != null && item.getRecid() == recid && (oldValue == expectedOldValue || (oldValue != null && oldValue.equals(expectedOldValue)))) {
/* 548 */           items2.put(recid, this.useWeakRef ? new CacheWeakItem<A>(newValue, q, recid) : new CacheSoftItem<A>(newValue, q, recid));
/* 551 */           engine.update(recid, newValue, serializer);
/* 552 */           return true;
/*     */         } 
/* 554 */         boolean ret = engine.compareAndSwap(recid, expectedOldValue, newValue, serializer);
/* 555 */         if (ret)
/* 556 */           items2.put(recid, this.useWeakRef ? new CacheWeakItem<A>(newValue, q, recid) : new CacheSoftItem<A>(newValue, q, recid)); 
/* 560 */         return ret;
/*     */       } finally {
/* 563 */         lock.unlock();
/*     */       } 
/*     */     }
/*     */     
/*     */     public void close() {
/* 570 */       super.close();
/* 571 */       this.items = null;
/* 572 */       this.queue = null;
/* 574 */       if (this.queueThread != null) {
/* 575 */         this.queueThread.interrupt();
/* 576 */         this.queueThread = null;
/*     */       } 
/*     */     }
/*     */     
/*     */     public void rollback() {
/* 583 */       this.items.clear();
/* 584 */       super.rollback();
/*     */     }
/*     */     
/*     */     public void clearCache() {
/* 589 */       this.items.clear();
/* 590 */       super.clearCache();
/*     */     }
/*     */     
/*     */     protected static interface CacheItem {
/*     */       long getRecid();
/*     */       
/*     */       Object get();
/*     */     }
/*     */   }
/*     */   
/*     */   public static class HardRef extends LRU {
/*     */     static final int CHECK_EVERY_N = 10000;
/*     */     
/* 605 */     int counter = 0;
/*     */     
/*     */     public HardRef(Engine engine, int initialCapacity, boolean disableLocks) {
/* 608 */       super(engine, new LongConcurrentHashMap(initialCapacity), disableLocks);
/*     */     }
/*     */     
/*     */     public <A> A get(long recid, Serializer<A> serializer) {
/* 613 */       checkFreeMem();
/* 614 */       return super.get(recid, serializer);
/*     */     }
/*     */     
/*     */     private void checkFreeMem() {
/* 618 */       if (this.counter++ % 10000 == 0) {
/* 620 */         Runtime r = Runtime.getRuntime();
/* 621 */         long max = r.maxMemory();
/* 622 */         if (max == Long.MAX_VALUE)
/*     */           return; 
/* 625 */         double free = r.freeMemory();
/* 626 */         double total = r.totalMemory();
/* 629 */         free += max - total;
/* 635 */         if (free < 1.0E7D || free * 4.0D < max)
/* 636 */           ((LongMap)checkClosed(this.cache)).clear(); 
/*     */       } 
/*     */     }
/*     */     
/*     */     public <A> void update(long recid, A value, Serializer<A> serializer) {
/* 643 */       checkFreeMem();
/* 644 */       super.update(recid, value, serializer);
/*     */     }
/*     */     
/*     */     public <A> void delete(long recid, Serializer<A> serializer) {
/* 649 */       checkFreeMem();
/* 650 */       super.delete(recid, serializer);
/*     */     }
/*     */     
/*     */     public <A> boolean compareAndSwap(long recid, A expectedOldValue, A newValue, Serializer<A> serializer) {
/* 655 */       checkFreeMem();
/* 656 */       return super.compareAndSwap(recid, expectedOldValue, newValue, serializer);
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\mapdb\Caches.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */