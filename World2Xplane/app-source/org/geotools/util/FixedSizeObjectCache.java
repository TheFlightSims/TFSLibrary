/*     */ package org.geotools.util;
/*     */ 
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.concurrent.locks.ReentrantLock;
/*     */ 
/*     */ final class FixedSizeObjectCache implements ObjectCache {
/*     */   private final int LIMIT;
/*     */   
/*     */   private final Map cache;
/*     */   
/*     */   private final Map locks;
/*     */   
/*     */   public FixedSizeObjectCache() {
/*  60 */     this(50);
/*     */   }
/*     */   
/*     */   public FixedSizeObjectCache(int initialSize) {
/*  67 */     this.LIMIT = initialSize;
/*  68 */     this.cache = Collections.synchronizedMap(new WeakValueHashMap<Object, Object>(initialSize));
/*  69 */     this.locks = new HashMap<Object, Object>(initialSize);
/*     */   }
/*     */   
/*     */   public void clear() {
/*  76 */     synchronized (this.locks) {
/*  77 */       this.locks.clear();
/*  78 */       this.cache.clear();
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean containsKey(Object key) {
/*  89 */     return this.cache.containsKey(key);
/*     */   }
/*     */   
/*     */   public Object get(Object key) {
/*  99 */     return this.cache.get(key);
/*     */   }
/*     */   
/*     */   public Object peek(Object key) {
/* 103 */     return this.cache.get(key);
/*     */   }
/*     */   
/*     */   public void writeLock(Object key) {
/*     */     ReentrantLock lock;
/* 108 */     synchronized (this.locks) {
/* 109 */       lock = (ReentrantLock)this.locks.get(key);
/* 110 */       if (lock == null) {
/* 111 */         lock = new ReentrantLock();
/* 112 */         this.locks.put(key, lock);
/*     */       } 
/*     */     } 
/* 116 */     lock.lock();
/*     */   }
/*     */   
/*     */   public void writeUnLock(Object key) {
/* 120 */     synchronized (this.locks) {
/* 121 */       ReentrantLock lock = (ReentrantLock)this.locks.get(key);
/* 122 */       if (lock == null)
/* 123 */         throw new IllegalMonitorStateException("Cannot unlock prior to locking"); 
/* 125 */       if (lock.getHoldCount() == 0)
/* 126 */         throw new IllegalMonitorStateException("Cannot unlock prior to locking"); 
/* 128 */       lock.unlock();
/* 129 */       if (lock.getHoldCount() == 0)
/* 130 */         this.locks.remove(key); 
/*     */     } 
/*     */   }
/*     */   
/*     */   boolean holdsLock(Object key) {
/* 136 */     synchronized (this.locks) {
/* 137 */       ReentrantLock lock = (ReentrantLock)this.locks.get(key);
/* 138 */       if (lock != null)
/* 139 */         return (lock.getHoldCount() != 0); 
/*     */     } 
/* 142 */     return false;
/*     */   }
/*     */   
/*     */   public void put(Object key, Object object) {
/* 148 */     if (this.cache.size() < this.LIMIT) {
/* 149 */       writeLock(key);
/* 150 */       this.cache.put(key, object);
/* 151 */       writeUnLock(key);
/*     */     } 
/*     */   }
/*     */   
/*     */   public Set<Object> getKeys() {
/* 159 */     Set<Object> keys = null;
/* 160 */     keys = new HashSet(this.cache.keySet());
/* 161 */     return keys;
/*     */   }
/*     */   
/*     */   public void remove(Object key) {
/* 169 */     synchronized (this.locks) {
/* 170 */       this.locks.remove(key);
/* 171 */       this.cache.remove(key);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotool\\util\FixedSizeObjectCache.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */