/*     */ package org.mapdb;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Queue;
/*     */ import java.util.Random;
/*     */ import java.util.concurrent.ConcurrentLinkedQueue;
/*     */ import java.util.concurrent.ConcurrentNavigableMap;
/*     */ import java.util.concurrent.ConcurrentSkipListMap;
/*     */ import java.util.concurrent.atomic.AtomicLong;
/*     */ import java.util.concurrent.locks.Lock;
/*     */ 
/*     */ public class StoreHeap extends Store implements Serializable {
/*  33 */   protected static final Fun.Tuple2 TOMBSTONE = Fun.t2(null, null);
/*     */   
/*  35 */   protected static final Object NULL = new Object();
/*     */   
/*     */   private static final long serialVersionUID = 150060834534309445L;
/*     */   
/*  39 */   protected final ConcurrentNavigableMap<Long, Fun.Tuple2> records = new ConcurrentSkipListMap<Long, Fun.Tuple2>();
/*     */   
/*  43 */   protected final ConcurrentNavigableMap<Long, Fun.Tuple2> rollback = new ConcurrentSkipListMap<Long, Fun.Tuple2>();
/*     */   
/*  48 */   protected final Queue<Long> freeRecids = new ConcurrentLinkedQueue<Long>();
/*     */   
/*  51 */   protected final AtomicLong maxRecid = new AtomicLong(7L);
/*     */   
/*     */   public StoreHeap() {
/*  54 */     super(false, false, null, false);
/*  55 */     for (long recid = 1L; recid <= 7L; recid++)
/*  56 */       this.records.put(Long.valueOf(recid), Fun.t2(null, (Serializer)null)); 
/*     */   }
/*     */   
/*     */   public long preallocate() {
/*  62 */     Lock lock = this.locks[(new Random()).nextInt(this.locks.length)].writeLock();
/*  63 */     lock.lock();
/*     */     try {
/*  65 */       Long recid = this.freeRecids.poll();
/*  66 */       if (recid == null)
/*  66 */         recid = Long.valueOf(this.maxRecid.incrementAndGet()); 
/*  67 */       return recid.longValue();
/*     */     } finally {
/*  69 */       lock.unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void preallocate(long[] recids) {
/*  75 */     Lock lock = this.locks[(new Random()).nextInt(this.locks.length)].writeLock();
/*  76 */     lock.lock();
/*     */     try {
/*  78 */       for (int i = 0; i < recids.length; i++) {
/*  79 */         Long recid = this.freeRecids.poll();
/*  80 */         if (recid == null)
/*  80 */           recid = Long.valueOf(this.maxRecid.incrementAndGet()); 
/*  81 */         recids[i] = recid.longValue();
/*     */       } 
/*     */     } finally {
/*  84 */       lock.unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   public <A> long put(A value, Serializer<A> serializer) {
/*  89 */     if (value == null)
/*  89 */       value = (A)NULL; 
/*  90 */     Lock lock = this.locks[(new Random()).nextInt(this.locks.length)].writeLock();
/*  91 */     lock.lock();
/*     */     try {
/*  93 */       Long recid = this.freeRecids.poll();
/*  94 */       if (recid == null)
/*  94 */         recid = Long.valueOf(this.maxRecid.incrementAndGet()); 
/*  95 */       this.records.put(recid, Fun.t2(value, serializer));
/*  96 */       this.rollback.put(recid, Fun.t2(TOMBSTONE, serializer));
/*  97 */       assert recid.longValue() > 0L;
/*  98 */       return recid.longValue();
/*     */     } finally {
/* 100 */       lock.unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   public <A> A get(long recid, Serializer<A> serializer) {
/* 106 */     assert recid > 0L;
/* 107 */     Lock lock = this.locks[Store.lockPos(recid)].readLock();
/* 108 */     lock.lock();
/*     */     try {
/* 111 */       Fun.Tuple2 t = this.records.get(Long.valueOf(recid));
/* 112 */       if (t == null || t.a == NULL)
/* 113 */         return null; 
/* 114 */       return t.a;
/*     */     } finally {
/* 116 */       lock.unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   public <A> void update(long recid, A value, Serializer<A> serializer) {
/* 122 */     assert recid > 0L;
/* 123 */     assert serializer != null;
/* 124 */     assert recid > 0L;
/* 125 */     if (value == null)
/* 125 */       value = (A)NULL; 
/* 126 */     Lock lock = this.locks[Store.lockPos(recid)].writeLock();
/* 127 */     lock.lock();
/*     */     try {
/* 129 */       Fun.Tuple2 old = this.records.put(Long.valueOf(recid), Fun.t2(value, serializer));
/* 130 */       if (old != null)
/* 131 */         this.rollback.putIfAbsent(Long.valueOf(recid), old); 
/*     */     } finally {
/* 133 */       lock.unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   public <A> boolean compareAndSwap(long recid, A expectedOldValue, A newValue, Serializer<A> serializer) {
/* 139 */     assert recid > 0L;
/* 140 */     if (expectedOldValue == null)
/* 140 */       expectedOldValue = (A)NULL; 
/* 141 */     if (newValue == null)
/* 141 */       newValue = (A)NULL; 
/* 142 */     Lock lock = this.locks[Store.lockPos(recid)].writeLock();
/* 143 */     lock.lock();
/*     */     try {
/* 145 */       Fun.Tuple2<A, Serializer<A>> old = Fun.t2(expectedOldValue, serializer);
/* 146 */       boolean ret = this.records.replace(Long.valueOf(recid), old, Fun.t2(newValue, serializer));
/* 147 */       if (ret)
/* 147 */         this.rollback.putIfAbsent(Long.valueOf(recid), old); 
/* 148 */       return ret;
/*     */     } finally {
/* 150 */       lock.unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   public <A> void delete(long recid, Serializer<A> serializer) {
/* 156 */     assert recid > 0L;
/* 157 */     Lock lock = this.locks[Store.lockPos(recid)].writeLock();
/* 158 */     lock.lock();
/*     */     try {
/* 160 */       Fun.Tuple2 t2 = this.records.remove(Long.valueOf(recid));
/* 161 */       if (t2 != null)
/* 161 */         this.rollback.putIfAbsent(Long.valueOf(recid), t2); 
/* 162 */       this.freeRecids.add(Long.valueOf(recid));
/*     */     } finally {
/* 164 */       lock.unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void close() {
/* 170 */     for (Runnable closeListener : this.closeListeners)
/* 171 */       closeListener.run(); 
/* 173 */     lockAllWrite();
/*     */     try {
/* 175 */       this.records.clear();
/* 176 */       this.freeRecids.clear();
/* 177 */       this.rollback.clear();
/*     */     } finally {
/* 179 */       unlockAllWrite();
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean isClosed() {
/* 185 */     return false;
/*     */   }
/*     */   
/*     */   public void commit() {
/* 190 */     lockAllWrite();
/*     */     try {
/* 192 */       this.rollback.clear();
/*     */     } finally {
/* 194 */       unlockAllWrite();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void rollback() throws UnsupportedOperationException {
/* 200 */     lockAllWrite();
/*     */     try {
/* 203 */       for (Map.Entry<Long, Fun.Tuple2> e : this.rollback.entrySet()) {
/* 204 */         Long recid = e.getKey();
/* 205 */         Fun.Tuple2 val = e.getValue();
/* 206 */         if (val == TOMBSTONE) {
/* 206 */           this.records.remove(recid);
/*     */           continue;
/*     */         } 
/* 207 */         this.records.put(recid, val);
/*     */       } 
/* 209 */       this.rollback.clear();
/*     */     } finally {
/* 211 */       unlockAllWrite();
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean isReadOnly() {
/* 217 */     return false;
/*     */   }
/*     */   
/*     */   public void clearCache() {}
/*     */   
/*     */   public void compact() {}
/*     */   
/*     */   public boolean canRollback() {
/* 230 */     return true;
/*     */   }
/*     */   
/*     */   public long getMaxRecid() {
/* 236 */     return this.maxRecid.get();
/*     */   }
/*     */   
/*     */   public ByteBuffer getRaw(long recid) {
/* 241 */     Fun.Tuple2 t = this.records.get(Long.valueOf(recid));
/* 242 */     if (t == null || t.a == null)
/* 242 */       return null; 
/* 243 */     return ByteBuffer.wrap(serialize(t.a, (Serializer<A>)t.b).copyBytes());
/*     */   }
/*     */   
/*     */   public Iterator<Long> getFreeRecids() {
/* 248 */     return Collections.<Long>unmodifiableCollection(this.freeRecids).iterator();
/*     */   }
/*     */   
/*     */   public void updateRaw(long recid, ByteBuffer data) {
/* 253 */     throw new UnsupportedOperationException("can not put raw data into StoreHeap");
/*     */   }
/*     */   
/*     */   public long getSizeLimit() {
/* 258 */     return 0L;
/*     */   }
/*     */   
/*     */   public long getCurrSize() {
/* 263 */     return this.records.size();
/*     */   }
/*     */   
/*     */   public long getFreeSize() {
/* 268 */     return 0L;
/*     */   }
/*     */   
/*     */   public String calculateStatistics() {
/* 273 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\mapdb\StoreHeap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */