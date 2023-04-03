/*     */ package org.geotools.util;
/*     */ 
/*     */ import java.lang.ref.Reference;
/*     */ import java.lang.ref.SoftReference;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.concurrent.locks.ReentrantLock;
/*     */ 
/*     */ final class SoftObjectCache implements ObjectCache {
/*     */   private final Map cache;
/*     */   
/*     */   private final Map locks;
/*     */   
/*     */   public SoftObjectCache() {
/*  46 */     this(50);
/*     */   }
/*     */   
/*     */   public SoftObjectCache(int initialSize) {
/*  53 */     this.cache = Collections.synchronizedMap(new HashMap<Object, Object>(initialSize));
/*  54 */     this.locks = new HashMap<Object, Object>();
/*     */   }
/*     */   
/*     */   public void clear() {
/*  61 */     synchronized (this.locks) {
/*  62 */       this.locks.clear();
/*  63 */       this.cache.clear();
/*     */     } 
/*     */   }
/*     */   
/*     */   public Object get(Object key) {
/*  75 */     Object stored = this.cache.get(key);
/*  76 */     if (stored instanceof Reference) {
/*  77 */       Reference reference = (Reference)stored;
/*  78 */       Object value = reference.get();
/*  79 */       if (value == null)
/*  80 */         this.cache.remove(key); 
/*  82 */       return value;
/*     */     } 
/*  84 */     return stored;
/*     */   }
/*     */   
/*     */   public Set<Object> getKeys() {
/*  91 */     Set<Object> keys = null;
/*  92 */     keys = new HashSet(this.cache.keySet());
/*  93 */     return keys;
/*     */   }
/*     */   
/*     */   public Object peek(Object key) {
/*  97 */     Object stored = this.cache.get(key);
/*  98 */     if (stored instanceof Reference) {
/*  99 */       Reference reference = (Reference)stored;
/* 100 */       return reference.get();
/*     */     } 
/* 102 */     return stored;
/*     */   }
/*     */   
/*     */   public void put(Object key, Object object) {
/* 109 */     writeLock(key);
/* 110 */     SoftReference reference = new SoftReference(object);
/* 111 */     this.cache.put(key, reference);
/* 112 */     writeUnLock(key);
/*     */   }
/*     */   
/*     */   public void remove(Object key) {
/* 119 */     synchronized (this.locks) {
/* 120 */       this.locks.remove(key);
/* 121 */       this.cache.remove(key);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void writeLock(Object key) {
/*     */     ReentrantLock lock;
/* 127 */     synchronized (this.locks) {
/* 128 */       lock = (ReentrantLock)this.locks.get(key);
/* 129 */       if (lock == null) {
/* 130 */         lock = new ReentrantLock();
/* 131 */         this.locks.put(key, lock);
/*     */       } 
/*     */     } 
/* 136 */     lock.lock();
/*     */   }
/*     */   
/*     */   public void writeUnLock(Object key) {
/* 140 */     synchronized (this.locks) {
/* 141 */       ReentrantLock lock = (ReentrantLock)this.locks.get(key);
/* 142 */       if (lock == null)
/* 143 */         throw new IllegalMonitorStateException("Cannot unlock prior to locking"); 
/* 146 */       if (lock.getHoldCount() == 0)
/* 147 */         throw new IllegalMonitorStateException("Cannot unlock prior to locking"); 
/* 150 */       lock.unlock();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotool\\util\SoftObjectCache.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */