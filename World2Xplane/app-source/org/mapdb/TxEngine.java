/*     */ package org.mapdb;
/*     */ 
/*     */ import java.lang.ref.Reference;
/*     */ import java.lang.ref.ReferenceQueue;
/*     */ import java.lang.ref.WeakReference;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.LinkedHashSet;
/*     */ import java.util.Queue;
/*     */ import java.util.Set;
/*     */ import java.util.concurrent.ArrayBlockingQueue;
/*     */ import java.util.concurrent.CopyOnWriteArrayList;
/*     */ import java.util.concurrent.locks.Lock;
/*     */ import java.util.concurrent.locks.ReentrantReadWriteLock;
/*     */ 
/*     */ public class TxEngine extends EngineWrapper {
/*  38 */   protected static final Object TOMBSTONE = new Object();
/*     */   
/*  40 */   protected final ReentrantReadWriteLock commitLock = new ReentrantReadWriteLock(false);
/*     */   
/*  41 */   protected final ReentrantReadWriteLock[] locks = new ReentrantReadWriteLock[128];
/*     */   
/*     */   protected volatile boolean uncommitedData;
/*     */   
/*     */   protected Set<Reference<Tx>> txs;
/*     */   
/*     */   protected ReferenceQueue<Tx> txQueue;
/*     */   
/*     */   protected final boolean fullTx;
/*     */   
/*     */   protected final Queue<Long> preallocRecids;
/*     */   
/*     */   protected final int PREALLOC_RECID_SIZE = 128;
/*     */   
/*     */   protected TxEngine(Engine engine, boolean fullTx) {
/*  59 */     super(engine);
/*     */     for (int i = 0; i < this.locks.length; ) {
/*     */       this.locks[i] = new ReentrantReadWriteLock(false);
/*     */       i++;
/*     */     } 
/*     */     this.uncommitedData = false;
/*     */     this.txs = new LinkedHashSet<Reference<Tx>>();
/*     */     this.txQueue = new ReferenceQueue<Tx>();
/*     */     this.PREALLOC_RECID_SIZE = 128;
/*  60 */     this.fullTx = fullTx;
/*  61 */     this.preallocRecids = fullTx ? new ArrayBlockingQueue<Long>(128) : null;
/*     */   }
/*     */   
/*     */   protected Long preallocRecidTake() {
/*  65 */     assert this.commitLock.isWriteLockedByCurrentThread();
/*  66 */     Long recid = this.preallocRecids.poll();
/*  67 */     if (recid != null)
/*  67 */       return recid; 
/*  69 */     if (this.uncommitedData)
/*  70 */       throw new IllegalAccessError("uncommited data"); 
/*  72 */     for (int i = 0; i < 128; i++)
/*  73 */       this.preallocRecids.add(Long.valueOf(super.preallocate())); 
/*  75 */     recid = Long.valueOf(super.preallocate());
/*  76 */     super.commit();
/*  77 */     this.uncommitedData = false;
/*  78 */     return recid;
/*     */   }
/*     */   
/*     */   public static Engine createSnapshotFor(Engine engine) {
/*  82 */     if (engine.isReadOnly())
/*  83 */       return engine; 
/*  84 */     if (engine instanceof TxEngine)
/*  85 */       return ((TxEngine)engine).snapshot(); 
/*  86 */     if (engine instanceof EngineWrapper)
/*  87 */       return createSnapshotFor(((EngineWrapper)engine).getWrappedEngine()); 
/*  88 */     throw new UnsupportedOperationException("Snapshots are not enabled, use DBMaker.snapshotEnable()");
/*     */   }
/*     */   
/*     */   public boolean canSnapshot() {
/*  93 */     return true;
/*     */   }
/*     */   
/*     */   public Engine snapshot() {
/*  98 */     this.commitLock.writeLock().lock();
/*     */     try {
/* 100 */       cleanTxQueue();
/* 101 */       if (this.uncommitedData && canRollback())
/* 102 */         throw new IllegalAccessError("Can not create snapshot with uncommited data"); 
/* 103 */       return new Tx();
/*     */     } finally {
/* 105 */       this.commitLock.writeLock().unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void cleanTxQueue() {
/* 110 */     assert this.commitLock.writeLock().isHeldByCurrentThread();
/* 111 */     for (Reference<? extends Tx> ref = this.txQueue.poll(); ref != null; ref = this.txQueue.poll())
/* 112 */       this.txs.remove(ref); 
/*     */   }
/*     */   
/*     */   public long preallocate() {
/* 118 */     this.commitLock.writeLock().lock();
/*     */     try {
/* 120 */       this.uncommitedData = true;
/* 121 */       long recid = super.preallocate();
/* 122 */       Lock lock = this.locks[Store.lockPos(recid)].writeLock();
/* 123 */       lock.lock();
/*     */       try {
/* 125 */         for (Reference<Tx> txr : this.txs) {
/* 126 */           Tx tx = txr.get();
/* 127 */           if (tx == null)
/*     */             continue; 
/* 128 */           tx.old.putIfAbsent(recid, TOMBSTONE);
/*     */         } 
/*     */       } finally {
/* 131 */         lock.unlock();
/*     */       } 
/* 133 */       return recid;
/*     */     } finally {
/* 135 */       this.commitLock.writeLock().unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void preallocate(long[] recids) {
/* 141 */     this.commitLock.writeLock().lock();
/*     */     try {
/* 143 */       this.uncommitedData = true;
/* 144 */       super.preallocate(recids);
/* 145 */       for (long recid : recids) {
/* 146 */         Lock lock = this.locks[Store.lockPos(recid)].writeLock();
/* 147 */         lock.lock();
/*     */       } 
/*     */     } finally {
/* 159 */       this.commitLock.writeLock().unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   public <A> long put(A value, Serializer<A> serializer) {
/* 165 */     this.commitLock.readLock().lock();
/*     */     try {
/* 167 */       this.uncommitedData = true;
/* 168 */       long recid = super.put(value, serializer);
/* 169 */       Lock lock = this.locks[Store.lockPos(recid)].writeLock();
/* 170 */       lock.lock();
/*     */       try {
/* 172 */         for (Reference<Tx> txr : this.txs) {
/* 173 */           Tx tx = txr.get();
/* 174 */           if (tx == null)
/*     */             continue; 
/* 175 */           tx.old.putIfAbsent(recid, TOMBSTONE);
/*     */         } 
/*     */       } finally {
/* 178 */         lock.unlock();
/*     */       } 
/* 181 */       return recid;
/*     */     } finally {
/* 183 */       this.commitLock.readLock().unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   public <A> A get(long recid, Serializer<A> serializer) {
/* 190 */     this.commitLock.readLock().lock();
/*     */     try {
/* 192 */       return (A)super.get(recid, (Serializer)serializer);
/*     */     } finally {
/* 194 */       this.commitLock.readLock().unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   public <A> void update(long recid, A value, Serializer<A> serializer) {
/* 200 */     this.commitLock.readLock().lock();
/*     */     try {
/* 202 */       this.uncommitedData = true;
/* 203 */       Lock lock = this.locks[Store.lockPos(recid)].writeLock();
/* 204 */       lock.lock();
/*     */       try {
/* 206 */         Object old = get(recid, serializer);
/* 207 */         for (Reference<Tx> txr : this.txs) {
/* 208 */           Tx tx = txr.get();
/* 209 */           if (tx == null)
/*     */             continue; 
/* 210 */           tx.old.putIfAbsent(recid, old);
/*     */         } 
/* 212 */         super.update(recid, value, serializer);
/*     */       } finally {
/* 214 */         lock.unlock();
/*     */       } 
/*     */     } finally {
/* 217 */       this.commitLock.readLock().unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   public <A> boolean compareAndSwap(long recid, A expectedOldValue, A newValue, Serializer<A> serializer) {
/* 224 */     this.commitLock.readLock().lock();
/*     */     try {
/* 226 */       this.uncommitedData = true;
/* 227 */       Lock lock = this.locks[Store.lockPos(recid)].writeLock();
/* 228 */       lock.lock();
/*     */     } finally {
/* 243 */       this.commitLock.readLock().unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   public <A> void delete(long recid, Serializer<A> serializer) {
/* 250 */     this.commitLock.readLock().lock();
/*     */     try {
/* 252 */       this.uncommitedData = true;
/* 253 */       Lock lock = this.locks[Store.lockPos(recid)].writeLock();
/* 254 */       lock.lock();
/*     */       try {
/* 256 */         Object old = get(recid, serializer);
/* 257 */         for (Reference<Tx> txr : this.txs) {
/* 258 */           Tx tx = txr.get();
/* 259 */           if (tx == null)
/*     */             continue; 
/* 260 */           tx.old.putIfAbsent(recid, old);
/*     */         } 
/* 262 */         super.delete(recid, serializer);
/*     */       } finally {
/* 264 */         lock.unlock();
/*     */       } 
/*     */     } finally {
/* 267 */       this.commitLock.readLock().unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void close() {
/* 273 */     this.commitLock.writeLock().lock();
/*     */     try {
/* 275 */       super.close();
/*     */     } finally {
/* 277 */       this.commitLock.writeLock().unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void commit() {
/* 284 */     this.commitLock.writeLock().lock();
/*     */     try {
/* 286 */       cleanTxQueue();
/* 287 */       super.commit();
/* 288 */       this.uncommitedData = false;
/*     */     } finally {
/* 290 */       this.commitLock.writeLock().unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void rollback() {
/* 297 */     this.commitLock.writeLock().lock();
/*     */     try {
/* 299 */       cleanTxQueue();
/* 300 */       super.rollback();
/* 301 */       this.uncommitedData = false;
/*     */     } finally {
/* 303 */       this.commitLock.writeLock().unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void superCommit() {
/* 309 */     assert this.commitLock.isWriteLockedByCurrentThread();
/* 310 */     super.commit();
/*     */   }
/*     */   
/*     */   protected <A> void superUpdate(long recid, A value, Serializer<A> serializer) {
/* 314 */     assert this.commitLock.isWriteLockedByCurrentThread();
/* 315 */     super.update(recid, value, serializer);
/*     */   }
/*     */   
/*     */   protected <A> void superDelete(long recid, Serializer<A> serializer) {
/* 319 */     assert this.commitLock.isWriteLockedByCurrentThread();
/* 320 */     super.delete(recid, serializer);
/*     */   }
/*     */   
/*     */   protected <A> A superGet(long recid, Serializer<A> serializer) {
/* 324 */     assert this.commitLock.isWriteLockedByCurrentThread();
/* 325 */     return super.get(recid, serializer);
/*     */   }
/*     */   
/*     */   public class Tx implements Engine {
/* 330 */     protected LongConcurrentHashMap old = new LongConcurrentHashMap();
/*     */     
/* 331 */     protected LongConcurrentHashMap<Fun.Tuple2> mod = TxEngine.this.fullTx ? new LongConcurrentHashMap<Fun.Tuple2>() : null;
/*     */     
/* 334 */     protected Collection<Long> usedPreallocatedRecids = TxEngine.this.fullTx ? new ArrayList<Long>() : null;
/*     */     
/* 337 */     protected final Reference<Tx> ref = new WeakReference<Tx>(this, TxEngine.this.txQueue);
/*     */     
/*     */     protected boolean closed = false;
/*     */     
/*     */     private Store parentEngine;
/*     */     
/*     */     SerializerPojo pojo;
/*     */     
/*     */     public long preallocate() {
/* 349 */       if (!TxEngine.this.fullTx)
/* 350 */         throw new UnsupportedOperationException("read-only"); 
/* 352 */       TxEngine.this.commitLock.writeLock().lock();
/*     */       try {
/* 354 */         Long recid = TxEngine.this.preallocRecidTake();
/* 355 */         this.usedPreallocatedRecids.add(recid);
/* 356 */         return recid.longValue();
/*     */       } finally {
/* 358 */         TxEngine.this.commitLock.writeLock().unlock();
/*     */       } 
/*     */     }
/*     */     
/*     */     public void preallocate(long[] recids) {
/* 364 */       if (!TxEngine.this.fullTx)
/* 365 */         throw new UnsupportedOperationException("read-only"); 
/* 367 */       TxEngine.this.commitLock.writeLock().lock();
/*     */       try {
/* 369 */         for (int i = 0; i < recids.length; i++) {
/* 370 */           Long recid = TxEngine.this.preallocRecidTake();
/* 371 */           this.usedPreallocatedRecids.add(recid);
/* 372 */           recids[i] = recid.longValue();
/*     */         } 
/*     */       } finally {
/* 375 */         TxEngine.this.commitLock.writeLock().unlock();
/*     */       } 
/*     */     }
/*     */     
/*     */     public <A> long put(A value, Serializer<A> serializer) {
/* 381 */       if (!TxEngine.this.fullTx)
/* 382 */         throw new UnsupportedOperationException("read-only"); 
/* 383 */       TxEngine.this.commitLock.writeLock().lock();
/*     */       try {
/* 385 */         Long recid = TxEngine.this.preallocRecidTake();
/* 386 */         this.usedPreallocatedRecids.add(recid);
/* 387 */         this.mod.put(recid.longValue(), Fun.t2(value, serializer));
/* 388 */         return recid.longValue();
/*     */       } finally {
/* 390 */         TxEngine.this.commitLock.writeLock().unlock();
/*     */       } 
/*     */     }
/*     */     
/*     */     public <A> A get(long recid, Serializer<A> serializer) {
/* 396 */       TxEngine.this.commitLock.readLock().lock();
/*     */       try {
/* 398 */         if (this.closed)
/* 398 */           throw new IllegalAccessError("closed"); 
/* 399 */         Lock lock = TxEngine.this.locks[Store.lockPos(recid)].readLock();
/* 400 */         lock.lock();
/*     */       } finally {
/* 407 */         TxEngine.this.commitLock.readLock().unlock();
/*     */       } 
/*     */     }
/*     */     
/*     */     private <A> A getNoLock(long recid, Serializer<A> serializer) {
/* 412 */       if (TxEngine.this.fullTx) {
/* 413 */         Fun.Tuple2 tu = this.mod.get(recid);
/* 414 */         if (tu != null) {
/* 415 */           if (tu.a == TxEngine.TOMBSTONE)
/* 416 */             return null; 
/* 417 */           return tu.a;
/*     */         } 
/*     */       } 
/* 421 */       Object oldVal = this.old.get(recid);
/* 422 */       if (oldVal != null) {
/* 423 */         if (oldVal == TxEngine.TOMBSTONE)
/* 424 */           return null; 
/* 425 */         return (A)oldVal;
/*     */       } 
/* 427 */       return TxEngine.this.get(recid, serializer);
/*     */     }
/*     */     
/*     */     public <A> void update(long recid, A value, Serializer<A> serializer) {
/* 432 */       if (!TxEngine.this.fullTx)
/* 433 */         throw new UnsupportedOperationException("read-only"); 
/* 434 */       TxEngine.this.commitLock.readLock().lock();
/*     */       try {
/* 436 */         this.mod.put(recid, Fun.t2(value, serializer));
/*     */       } finally {
/* 438 */         TxEngine.this.commitLock.readLock().unlock();
/*     */       } 
/*     */     }
/*     */     
/*     */     public <A> boolean compareAndSwap(long recid, A expectedOldValue, A newValue, Serializer<A> serializer) {
/* 444 */       if (!TxEngine.this.fullTx)
/* 445 */         throw new UnsupportedOperationException("read-only"); 
/* 447 */       TxEngine.this.commitLock.readLock().lock();
/*     */       try {
/* 450 */         Lock lock = TxEngine.this.locks[Store.lockPos(recid)].writeLock();
/* 451 */         lock.lock();
/*     */       } finally {
/* 463 */         TxEngine.this.commitLock.readLock().unlock();
/*     */       } 
/*     */     }
/*     */     
/*     */     public <A> void delete(long recid, Serializer<A> serializer) {
/* 469 */       if (!TxEngine.this.fullTx)
/* 470 */         throw new UnsupportedOperationException("read-only"); 
/* 472 */       TxEngine.this.commitLock.readLock().lock();
/*     */       try {
/* 474 */         this.mod.put(recid, Fun.t2(TxEngine.TOMBSTONE, serializer));
/*     */       } finally {
/* 476 */         TxEngine.this.commitLock.readLock().unlock();
/*     */       } 
/*     */     }
/*     */     
/*     */     public void close() {
/* 483 */       this.closed = true;
/* 484 */       this.old.clear();
/* 485 */       this.ref.clear();
/*     */     }
/*     */     
/*     */     public boolean isClosed() {
/* 490 */       return this.closed;
/*     */     }
/*     */     
/*     */     public void commit() {
/* 495 */       if (!TxEngine.this.fullTx)
/* 496 */         throw new UnsupportedOperationException("read-only"); 
/* 498 */       TxEngine.this.commitLock.writeLock().lock();
/*     */       try {
/* 500 */         if (this.closed)
/*     */           return; 
/* 501 */         if (TxEngine.this.uncommitedData)
/* 502 */           throw new IllegalAccessError("uncommitted data"); 
/* 503 */         TxEngine.this.txs.remove(this.ref);
/* 504 */         TxEngine.this.cleanTxQueue();
/* 506 */         if (this.pojo.hasUnsavedChanges())
/* 507 */           this.pojo.save(this); 
/* 510 */         LongMap.LongMapIterator oldIter = this.old.longMapIterator();
/* 511 */         while (oldIter.moveToNext()) {
/* 512 */           long recid = oldIter.key();
/* 513 */           for (Reference<Tx> ref2 : TxEngine.this.txs) {
/* 514 */             Tx tx = ref2.get();
/* 515 */             if (tx != this && tx != null && 
/* 516 */               tx.mod.containsKey(recid)) {
/* 517 */               close();
/* 518 */               throw new TxRollbackException();
/*     */             } 
/*     */           } 
/*     */         } 
/* 523 */         LongMap.LongMapIterator<Fun.Tuple2> iter = this.mod.longMapIterator();
/* 524 */         while (iter.moveToNext()) {
/* 525 */           long recid = iter.key();
/* 526 */           if (this.old.containsKey(recid)) {
/* 527 */             close();
/* 528 */             throw new TxRollbackException();
/*     */           } 
/*     */         } 
/* 532 */         iter = this.mod.longMapIterator();
/* 533 */         while (iter.moveToNext()) {
/* 534 */           long recid = iter.key();
/* 536 */           Fun.Tuple2 val = iter.value();
/* 537 */           Serializer<?> ser = (Serializer)val.b;
/* 538 */           Object old = TxEngine.this.superGet(recid, ser);
/* 539 */           if (old == null)
/* 540 */             old = TxEngine.TOMBSTONE; 
/* 541 */           for (Reference<Tx> txr : TxEngine.this.txs) {
/* 542 */             Tx tx = txr.get();
/* 543 */             if (tx == null || tx == this)
/*     */               continue; 
/* 544 */             tx.old.putIfAbsent(recid, old);
/*     */           } 
/* 548 */           if (val.a == TxEngine.TOMBSTONE) {
/* 549 */             TxEngine.this.superDelete(recid, ser);
/*     */             continue;
/*     */           } 
/* 551 */           TxEngine.this.superUpdate(recid, val.a, (Serializer)ser);
/*     */         } 
/* 557 */         (getWrappedEngine().getSerializerPojo()).registered = this.pojo.registered;
/* 558 */         TxEngine.this.superCommit();
/* 560 */         close();
/*     */       } finally {
/* 562 */         TxEngine.this.commitLock.writeLock().unlock();
/*     */       } 
/*     */     }
/*     */     
/*     */     public void rollback() throws UnsupportedOperationException {
/* 568 */       if (!TxEngine.this.fullTx)
/* 569 */         throw new UnsupportedOperationException("read-only"); 
/* 571 */       TxEngine.this.commitLock.writeLock().lock();
/*     */       try {
/* 573 */         if (this.closed)
/*     */           return; 
/* 574 */         if (TxEngine.this.uncommitedData)
/* 575 */           throw new IllegalAccessError("uncommitted data"); 
/* 577 */         TxEngine.this.txs.remove(this.ref);
/* 578 */         TxEngine.this.cleanTxQueue();
/* 580 */         for (Long prealloc : this.usedPreallocatedRecids)
/* 581 */           TxEngine.this.superDelete(prealloc.longValue(), (Serializer<?>)null); 
/* 583 */         TxEngine.this.superCommit();
/* 585 */         close();
/*     */       } finally {
/* 587 */         TxEngine.this.commitLock.writeLock().unlock();
/*     */       } 
/*     */     }
/*     */     
/*     */     public boolean isReadOnly() {
/* 593 */       return !TxEngine.this.fullTx;
/*     */     }
/*     */     
/*     */     public boolean canRollback() {
/* 598 */       return TxEngine.this.fullTx;
/*     */     }
/*     */     
/*     */     public boolean canSnapshot() {
/* 603 */       return false;
/*     */     }
/*     */     
/*     */     public Engine snapshot() throws UnsupportedOperationException {
/* 608 */       throw new UnsupportedOperationException();
/*     */     }
/*     */     
/*     */     public void clearCache() {}
/*     */     
/*     */     public void compact() {}
/*     */     
/*     */     public Tx() {
/* 621 */       this.pojo = new SerializerPojo((CopyOnWriteArrayList<SerializerPojo.ClassInfo>)(TxEngine.this.getSerializerPojo()).registered.clone());
/*     */       assert TxEngine.this.commitLock.isWriteLockedByCurrentThread();
/*     */       TxEngine.this.txs.add(this.ref);
/*     */     }
/*     */     
/*     */     public SerializerPojo getSerializerPojo() {
/* 625 */       return this.pojo;
/*     */     }
/*     */     
/*     */     public void closeListenerRegister(Runnable closeListener) {
/* 630 */       throw new UnsupportedOperationException();
/*     */     }
/*     */     
/*     */     public void closeListenerUnregister(Runnable closeListener) {
/* 636 */       throw new UnsupportedOperationException();
/*     */     }
/*     */     
/*     */     public Engine getWrappedEngine() {
/* 640 */       return TxEngine.this.getWrappedEngine();
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\mapdb\TxEngine.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */