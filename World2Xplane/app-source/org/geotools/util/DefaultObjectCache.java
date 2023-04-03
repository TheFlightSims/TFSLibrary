/*     */ package org.geotools.util;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.concurrent.locks.ReadWriteLock;
/*     */ import java.util.concurrent.locks.ReentrantReadWriteLock;
/*     */ 
/*     */ final class DefaultObjectCache implements ObjectCache {
/*     */   private final Map cache;
/*     */   
/*     */   static final class ObjectCacheEntry {
/*     */     private volatile Object value;
/*     */     
/*  79 */     private final ReadWriteLock lock = new ReentrantReadWriteLock();
/*     */     
/*     */     public ObjectCacheEntry() {}
/*     */     
/*     */     public ObjectCacheEntry(Object value) {
/*  92 */       this.value = value;
/*     */     }
/*     */     
/*     */     public Object peek() {
/*     */       try {
/* 111 */         this.lock.writeLock().lock();
/* 112 */         return this.value;
/*     */       } finally {
/* 118 */         this.lock.writeLock().unlock();
/*     */       } 
/*     */     }
/*     */     
/*     */     public Object getValue() {
/*     */       try {
/* 130 */         this.lock.readLock().lock();
/* 131 */         return this.value;
/*     */       } finally {
/* 138 */         this.lock.readLock().unlock();
/*     */       } 
/*     */     }
/*     */     
/*     */     public void setValue(Object value) {
/*     */       try {
/* 151 */         this.lock.writeLock().lock();
/* 152 */         this.value = value;
/*     */       } finally {
/* 157 */         this.lock.writeLock().unlock();
/*     */       } 
/*     */     }
/*     */     
/*     */     public boolean writeLock() {
/* 168 */       this.lock.writeLock().lock();
/* 169 */       return true;
/*     */     }
/*     */     
/*     */     public void writeUnLock() {
/* 176 */       this.lock.writeLock().unlock();
/*     */     }
/*     */   }
/*     */   
/*     */   public DefaultObjectCache() {
/* 184 */     this.cache = new HashMap<Object, Object>();
/*     */   }
/*     */   
/*     */   public DefaultObjectCache(int initialSize) {
/* 191 */     this.cache = new HashMap<Object, Object>(initialSize);
/*     */   }
/*     */   
/*     */   public void clear() {
/* 198 */     synchronized (this.cache) {
/* 199 */       this.cache.clear();
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean containsKey(Object key) {
/* 210 */     return this.cache.containsKey(key);
/*     */   }
/*     */   
/*     */   public Object get(Object key) {
/* 228 */     return getEntry(key).getValue();
/*     */   }
/*     */   
/*     */   public Object peek(Object key) {
/* 232 */     synchronized (this.cache) {
/* 233 */       if (!this.cache.containsKey(key))
/* 235 */         return null; 
/* 237 */       return getEntry(key).peek();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void writeLock(Object key) {
/* 242 */     getEntry(key).writeLock();
/*     */   }
/*     */   
/*     */   public void writeUnLock(Object key) {
/* 246 */     synchronized (this.cache) {
/* 247 */       if (!this.cache.containsKey(key))
/* 248 */         throw new IllegalStateException("Cannot unlock prior to locking"); 
/* 250 */       getEntry(key).writeUnLock();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void put(Object key, Object object) {
/* 258 */     getEntry(key).setValue(object);
/*     */   }
/*     */   
/*     */   private ObjectCacheEntry getEntry(Object key) {
/* 268 */     synchronized (this.cache) {
/* 269 */       ObjectCacheEntry entry = (ObjectCacheEntry)this.cache.get(key);
/* 270 */       if (entry == null) {
/* 271 */         entry = new ObjectCacheEntry();
/* 272 */         this.cache.put(key, entry);
/*     */       } 
/* 274 */       return entry;
/*     */     } 
/*     */   }
/*     */   
/*     */   public Set<Object> getKeys() {
/* 284 */     Set<Object> ret = null;
/* 285 */     synchronized (this.cache) {
/* 286 */       ret = new HashSet(this.cache.keySet());
/*     */     } 
/* 288 */     return ret;
/*     */   }
/*     */   
/*     */   public void remove(Object key) {
/* 295 */     synchronized (this.cache) {
/* 296 */       this.cache.remove(key);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotool\\util\DefaultObjectCache.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */