/*     */ package org.geotools.util;
/*     */ 
/*     */ import java.lang.ref.Reference;
/*     */ import java.lang.ref.WeakReference;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.concurrent.locks.ReentrantLock;
/*     */ 
/*     */ final class WeakObjectCache implements ObjectCache {
/*     */   private final Map cache;
/*     */   
/*     */   private final Map locks;
/*     */   
/*     */   public WeakObjectCache() {
/*  61 */     this(50);
/*     */   }
/*     */   
/*     */   public WeakObjectCache(int initialSize) {
/*  68 */     this.cache = Collections.synchronizedMap(new HashMap<Object, Object>(initialSize));
/*  69 */     this.locks = new HashMap<Object, Object>();
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
/*  89 */     if (this.cache.containsKey(key)) {
/*  90 */       Object stored = this.cache.get(key);
/*  91 */       if (stored instanceof Reference) {
/*  92 */         Reference reference = (Reference)stored;
/*  93 */         return (reference.get() != null);
/*     */       } 
/*  95 */       return (stored != null);
/*     */     } 
/*  97 */     return false;
/*     */   }
/*     */   
/*     */   public Object get(Object key) {
/* 107 */     Object stored = this.cache.get(key);
/* 108 */     if (stored instanceof Reference) {
/* 109 */       Reference reference = (Reference)stored;
/* 110 */       Object value = reference.get();
/* 111 */       if (value == null)
/* 112 */         this.cache.remove(key); 
/* 114 */       return value;
/*     */     } 
/* 116 */     return stored;
/*     */   }
/*     */   
/*     */   public Object peek(Object key) {
/* 120 */     Object stored = this.cache.get(key);
/* 121 */     if (stored instanceof Reference) {
/* 122 */       Reference reference = (Reference)stored;
/* 123 */       return reference.get();
/*     */     } 
/* 125 */     return stored;
/*     */   }
/*     */   
/*     */   public void writeLock(Object key) {
/*     */     ReentrantLock lock;
/* 130 */     synchronized (this.locks) {
/* 131 */       lock = (ReentrantLock)this.locks.get(key);
/* 132 */       if (lock == null) {
/* 133 */         lock = new ReentrantLock();
/* 134 */         this.locks.put(key, lock);
/*     */       } 
/*     */     } 
/* 139 */     lock.lock();
/*     */   }
/*     */   
/*     */   public void writeUnLock(Object key) {
/* 143 */     synchronized (this.locks) {
/* 144 */       ReentrantLock lock = (ReentrantLock)this.locks.get(key);
/* 145 */       if (lock == null)
/* 146 */         throw new IllegalMonitorStateException("Cannot unlock prior to locking"); 
/* 149 */       if (lock.getHoldCount() == 0)
/* 150 */         throw new IllegalMonitorStateException("Cannot unlock prior to locking"); 
/* 153 */       lock.unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   boolean holdsLock(Object key) {
/* 164 */     synchronized (this.locks) {
/* 165 */       ReentrantLock lock = (ReentrantLock)this.locks.get(key);
/* 166 */       if (lock != null)
/* 167 */         return (lock.getHoldCount() != 0); 
/*     */     } 
/* 170 */     return false;
/*     */   }
/*     */   
/*     */   public void put(Object key, Object object) {
/* 177 */     writeLock(key);
/* 178 */     WeakReference reference = new WeakReference(object);
/* 179 */     this.cache.put(key, reference);
/* 180 */     writeUnLock(key);
/*     */   }
/*     */   
/*     */   public Set<Object> getKeys() {
/* 187 */     Set<Object> keys = null;
/* 189 */     keys = new HashSet(this.cache.keySet());
/* 191 */     return keys;
/*     */   }
/*     */   
/*     */   public void remove(Object key) {
/* 198 */     synchronized (this.locks) {
/* 199 */       this.locks.remove(key);
/* 200 */       this.cache.remove(key);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotool\\util\WeakObjectCache.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */