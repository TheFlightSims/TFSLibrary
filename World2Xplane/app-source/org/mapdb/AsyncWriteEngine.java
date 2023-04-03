/*     */ package org.mapdb;
/*     */ 
/*     */ import java.lang.ref.WeakReference;
/*     */ import java.util.concurrent.CountDownLatch;
/*     */ import java.util.concurrent.Executor;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ import java.util.concurrent.atomic.AtomicInteger;
/*     */ import java.util.concurrent.atomic.AtomicLong;
/*     */ import java.util.concurrent.atomic.AtomicReference;
/*     */ import java.util.concurrent.locks.LockSupport;
/*     */ import java.util.concurrent.locks.ReentrantReadWriteLock;
/*     */ 
/*     */ public class AsyncWriteEngine extends EngineWrapper implements Engine {
/*  79 */   protected static final AtomicLong threadCounter = new AtomicLong();
/*     */   
/*  83 */   protected static final Object TOMBSTONE = new Object();
/*     */   
/*     */   protected final int maxSize;
/*     */   
/*  88 */   protected final AtomicInteger size = new AtomicInteger();
/*     */   
/*  96 */   protected final LongConcurrentHashMap<Fun.Tuple2<Object, Serializer>> writeCache = new LongConcurrentHashMap<Fun.Tuple2<Object, Serializer>>();
/*     */   
/* 102 */   protected final ReentrantReadWriteLock commitLock = new ReentrantReadWriteLock(false);
/*     */   
/* 105 */   protected final CountDownLatch activeThreadsCount = new CountDownLatch(1);
/*     */   
/* 108 */   protected volatile Throwable threadFailedException = null;
/*     */   
/*     */   protected volatile boolean closeInProgress = false;
/*     */   
/*     */   protected final int asyncFlushDelay;
/*     */   
/* 116 */   protected final AtomicReference<CountDownLatch> action = new AtomicReference<CountDownLatch>(null);
/*     */   
/*     */   public AsyncWriteEngine(Engine engine, int _asyncFlushDelay, int queueSize, Executor executor) {
/* 130 */     super(engine);
/* 131 */     this.asyncFlushDelay = _asyncFlushDelay;
/* 132 */     this.maxSize = queueSize;
/* 133 */     startThreads(executor);
/*     */   }
/*     */   
/*     */   public AsyncWriteEngine(Engine engine) {
/* 137 */     this(engine, 100, 32000, null);
/*     */   }
/*     */   
/*     */   protected static final class WriterRunnable implements Runnable {
/*     */     protected final WeakReference<AsyncWriteEngine> engineRef;
/*     */     
/*     */     protected final long asyncFlushDelay;
/*     */     
/*     */     protected final AtomicInteger size;
/*     */     
/*     */     protected final int maxParkSize;
/*     */     
/*     */     private final ReentrantReadWriteLock commitLock;
/*     */     
/*     */     public WriterRunnable(AsyncWriteEngine engine) {
/* 151 */       this.engineRef = new WeakReference<AsyncWriteEngine>(engine);
/* 152 */       this.asyncFlushDelay = engine.asyncFlushDelay;
/* 153 */       this.commitLock = engine.commitLock;
/* 154 */       this.size = engine.size;
/* 155 */       this.maxParkSize = engine.maxSize / 4;
/*     */     }
/*     */     
/*     */     public void run() {
/*     */       try {
/*     */         AsyncWriteEngine engine;
/*     */         do {
/* 164 */           if (this.asyncFlushDelay != 0L && !this.commitLock.isWriteLocked() && this.size.get() < this.maxParkSize)
/* 165 */             LockSupport.parkNanos(1000000L * this.asyncFlushDelay); 
/* 168 */           engine = this.engineRef.get();
/* 169 */           if (engine == null)
/*     */             return; 
/* 170 */           if (engine.threadFailedException != null)
/*     */             return; 
/* 172 */         } while (engine.runWriter());
/*     */         return;
/* 174 */       } catch (Throwable e) {
/* 175 */         AsyncWriteEngine asyncWriteEngine1 = this.engineRef.get();
/* 176 */         if (asyncWriteEngine1 != null)
/* 176 */           asyncWriteEngine1.threadFailedException = e; 
/*     */       } finally {
/* 178 */         AsyncWriteEngine engine = this.engineRef.get();
/* 179 */         if (engine != null)
/* 179 */           engine.activeThreadsCount.countDown(); 
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   protected void startThreads(Executor executor) {
/* 191 */     Runnable writerRun = new WriterRunnable(this);
/* 193 */     if (executor != null) {
/* 194 */       executor.execute(writerRun);
/*     */       return;
/*     */     } 
/* 197 */     long threadNum = threadCounter.incrementAndGet();
/* 198 */     Thread writerThread = new Thread(writerRun, "MapDB writer #" + threadNum);
/* 199 */     writerThread.setDaemon(true);
/* 200 */     writerThread.start();
/*     */   }
/*     */   
/*     */   protected boolean runWriter() throws InterruptedException {
/* 206 */     CountDownLatch latch = this.action.getAndSet(null);
/*     */     do {
/* 209 */       LongMap.LongMapIterator<Fun.Tuple2<Object, Serializer>> iter = this.writeCache.longMapIterator();
/* 210 */       while (iter.moveToNext()) {
/* 212 */         long recid = iter.key();
/* 213 */         Fun.Tuple2<Object, Serializer> item = iter.value();
/* 214 */         if (item == null)
/*     */           continue; 
/* 215 */         if (item.a == TOMBSTONE) {
/* 217 */           delete(recid, (Serializer)item.b);
/*     */         } else {
/* 220 */           update(recid, item.a, (Serializer)item.b);
/*     */         } 
/* 223 */         if (this.writeCache.remove(recid, item))
/* 224 */           this.size.decrementAndGet(); 
/*     */       } 
/* 227 */     } while (latch != null && !this.writeCache.isEmpty());
/* 233 */     if (latch != null) {
/* 234 */       assert this.writeCache.isEmpty();
/* 236 */       long count = latch.getCount();
/* 237 */       if (count == 0L)
/* 238 */         return false; 
/* 239 */       if (count == 1L) {
/* 240 */         commit();
/* 241 */         latch.countDown();
/* 242 */       } else if (count == 2L) {
/* 243 */         rollback();
/* 244 */         latch.countDown();
/* 245 */         latch.countDown();
/* 246 */       } else if (count == 3L) {
/* 247 */         compact();
/* 248 */         latch.countDown();
/* 249 */         latch.countDown();
/* 250 */         latch.countDown();
/*     */       } else {
/* 251 */         throw new AssertionError();
/*     */       } 
/*     */     } 
/* 253 */     return true;
/*     */   }
/*     */   
/*     */   protected void checkState() {
/* 261 */     if (this.closeInProgress)
/* 261 */       throw new IllegalAccessError("db has been closed"); 
/* 262 */     if (this.threadFailedException != null)
/* 262 */       throw new RuntimeException("Writer thread failed", this.threadFailedException); 
/*     */   }
/*     */   
/*     */   public <A> long put(A value, Serializer<A> serializer) {
/* 296 */     int size2 = 0;
/* 297 */     long recid = 0L;
/* 298 */     this.commitLock.readLock().lock();
/*     */     try {
/* 300 */       checkState();
/* 301 */       recid = preallocate();
/* 302 */       if (this.writeCache.put(recid, new Fun.Tuple2<Object, Serializer>(value, serializer)) == null)
/* 303 */         size2 = this.size.incrementAndGet(); 
/*     */     } finally {
/* 305 */       this.commitLock.readLock().unlock();
/*     */     } 
/* 307 */     if (size2 > this.maxSize)
/* 308 */       clearCache(); 
/* 309 */     return recid;
/*     */   }
/*     */   
/*     */   public <A> A get(long recid, Serializer<A> serializer) {
/* 324 */     this.commitLock.readLock().lock();
/*     */     try {
/* 326 */       checkState();
/* 327 */       Fun.Tuple2<Object, Serializer> item = this.writeCache.get(recid);
/* 328 */       if (item != null) {
/* 329 */         if (item.a == TOMBSTONE)
/* 329 */           return null; 
/* 330 */         return item.a;
/*     */       } 
/* 333 */       return (A)super.get(recid, (Serializer)serializer);
/*     */     } finally {
/* 335 */       this.commitLock.readLock().unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   public <A> void update(long recid, A value, Serializer<A> serializer) {
/* 359 */     int size2 = 0;
/* 360 */     this.commitLock.readLock().lock();
/*     */     try {
/* 362 */       checkState();
/* 363 */       if (this.writeCache.put(recid, new Fun.Tuple2<Object, Serializer>(value, serializer)) == null)
/* 364 */         size2 = this.size.incrementAndGet(); 
/*     */     } finally {
/* 366 */       this.commitLock.readLock().unlock();
/*     */     } 
/* 368 */     if (size2 > this.maxSize)
/* 369 */       clearCache(); 
/*     */   }
/*     */   
/*     */   public <A> boolean compareAndSwap(long recid, A expectedOldValue, A newValue, Serializer<A> serializer) {
/*     */     boolean ret;
/* 381 */     int size2 = 0;
/* 383 */     this.commitLock.writeLock().lock();
/*     */     try {
/* 385 */       checkState();
/* 386 */       Fun.Tuple2<Object, Serializer> existing = this.writeCache.get(recid);
/* 387 */       A oldValue = (existing != null) ? existing.a : super.<A>get(recid, serializer);
/* 388 */       if (oldValue == expectedOldValue || (oldValue != null && oldValue.equals(expectedOldValue))) {
/* 389 */         if (this.writeCache.put(recid, new Fun.Tuple2<Object, Serializer>(newValue, serializer)) == null)
/* 390 */           size2 = this.size.incrementAndGet(); 
/* 391 */         ret = true;
/*     */       } else {
/* 393 */         ret = false;
/*     */       } 
/*     */     } finally {
/* 396 */       this.commitLock.writeLock().unlock();
/*     */     } 
/* 398 */     if (size2 > this.maxSize)
/* 399 */       clearCache(); 
/* 400 */     return ret;
/*     */   }
/*     */   
/*     */   public <A> void delete(long recid, Serializer<A> serializer) {
/* 411 */     update(recid, TOMBSTONE, serializer);
/*     */   }
/*     */   
/*     */   public void close() {
/* 422 */     this.commitLock.writeLock().lock();
/*     */     try {
/* 424 */       if (this.closeInProgress)
/*     */         return; 
/* 425 */       checkState();
/* 426 */       this.closeInProgress = true;
/* 428 */       if (!this.action.compareAndSet(null, new CountDownLatch(0)))
/* 429 */         throw new AssertionError(); 
/* 433 */       while (!this.activeThreadsCount.await(1000L, TimeUnit.MILLISECONDS));
/* 437 */       close();
/* 439 */     } catch (InterruptedException e) {
/* 440 */       throw new RuntimeException(e);
/*     */     } finally {
/* 442 */       this.commitLock.writeLock().unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void waitForAction(int actionNumber) {
/* 449 */     this.commitLock.writeLock().lock();
/*     */     try {
/* 451 */       checkState();
/* 453 */       CountDownLatch msg = new CountDownLatch(actionNumber);
/* 454 */       if (!this.action.compareAndSet(null, msg))
/* 455 */         throw new AssertionError(); 
/* 458 */       while (!msg.await(100L, TimeUnit.MILLISECONDS))
/* 459 */         checkState(); 
/* 461 */     } catch (InterruptedException e) {
/* 462 */       throw new RuntimeException(e);
/*     */     } finally {
/* 464 */       this.commitLock.writeLock().unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void commit() {
/* 478 */     waitForAction(1);
/*     */   }
/*     */   
/*     */   public void rollback() {
/* 490 */     waitForAction(2);
/*     */   }
/*     */   
/*     */   public void compact() {
/* 501 */     waitForAction(3);
/*     */   }
/*     */   
/*     */   public void clearCache() {
/* 513 */     this.commitLock.writeLock().lock();
/*     */     try {
/* 516 */       checkState();
/* 518 */       while (!this.writeCache.isEmpty()) {
/* 519 */         checkState();
/* 520 */         Thread.sleep(100L);
/*     */       } 
/* 522 */     } catch (InterruptedException e) {
/* 523 */       throw new RuntimeException(e);
/*     */     } finally {
/* 525 */       this.commitLock.writeLock().unlock();
/*     */     } 
/* 527 */     super.clearCache();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\mapdb\AsyncWriteEngine.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */