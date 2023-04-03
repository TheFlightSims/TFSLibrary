/*      */ package org.apache.commons.pool.impl;
/*      */ 
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collection;
/*      */ import java.util.Iterator;
/*      */ import java.util.LinkedList;
/*      */ import java.util.List;
/*      */ import java.util.NoSuchElementException;
/*      */ import java.util.TimerTask;
/*      */ import org.apache.commons.pool.BaseObjectPool;
/*      */ import org.apache.commons.pool.ObjectPool;
/*      */ import org.apache.commons.pool.PoolableObjectFactory;
/*      */ 
/*      */ public class GenericObjectPool extends BaseObjectPool implements ObjectPool {
/*      */   public static final byte WHEN_EXHAUSTED_FAIL = 0;
/*      */   
/*      */   public static final byte WHEN_EXHAUSTED_BLOCK = 1;
/*      */   
/*      */   public static final byte WHEN_EXHAUSTED_GROW = 2;
/*      */   
/*      */   public static final int DEFAULT_MAX_IDLE = 8;
/*      */   
/*      */   public static final int DEFAULT_MIN_IDLE = 0;
/*      */   
/*      */   public static final int DEFAULT_MAX_ACTIVE = 8;
/*      */   
/*      */   public static final byte DEFAULT_WHEN_EXHAUSTED_ACTION = 1;
/*      */   
/*      */   public static final boolean DEFAULT_LIFO = true;
/*      */   
/*      */   public static final long DEFAULT_MAX_WAIT = -1L;
/*      */   
/*      */   public static final boolean DEFAULT_TEST_ON_BORROW = false;
/*      */   
/*      */   public static final boolean DEFAULT_TEST_ON_RETURN = false;
/*      */   
/*      */   public static final boolean DEFAULT_TEST_WHILE_IDLE = false;
/*      */   
/*      */   public static final long DEFAULT_TIME_BETWEEN_EVICTION_RUNS_MILLIS = -1L;
/*      */   
/*      */   public static final int DEFAULT_NUM_TESTS_PER_EVICTION_RUN = 3;
/*      */   
/*      */   public static final long DEFAULT_MIN_EVICTABLE_IDLE_TIME_MILLIS = 1800000L;
/*      */   
/*      */   public static final long DEFAULT_SOFT_MIN_EVICTABLE_IDLE_TIME_MILLIS = -1L;
/*      */   
/*      */   private int _maxIdle;
/*      */   
/*      */   private int _minIdle;
/*      */   
/*      */   private int _maxActive;
/*      */   
/*      */   private long _maxWait;
/*      */   
/*      */   private byte _whenExhaustedAction;
/*      */   
/*      */   private volatile boolean _testOnBorrow;
/*      */   
/*      */   private volatile boolean _testOnReturn;
/*      */   
/*      */   private boolean _testWhileIdle;
/*      */   
/*      */   private long _timeBetweenEvictionRunsMillis;
/*      */   
/*      */   private int _numTestsPerEvictionRun;
/*      */   
/*      */   private long _minEvictableIdleTimeMillis;
/*      */   
/*      */   private long _softMinEvictableIdleTimeMillis;
/*      */   
/*      */   private boolean _lifo;
/*      */   
/*      */   private CursorableLinkedList _pool;
/*      */   
/*      */   private CursorableLinkedList.Cursor _evictionCursor;
/*      */   
/*      */   private PoolableObjectFactory _factory;
/*      */   
/*      */   private int _numActive;
/*      */   
/*      */   private Evictor _evictor;
/*      */   
/*      */   private int _numInternalProcessing;
/*      */   
/*      */   private LinkedList _allocationQueue;
/*      */   
/*      */   public GenericObjectPool() {
/*  342 */     this((PoolableObjectFactory)null, 8, (byte)1, -1L, 8, 0, false, false, -1L, 3, 1800000L, false);
/*      */   }
/*      */   
/*      */   public GenericObjectPool(PoolableObjectFactory factory) {
/*  352 */     this(factory, 8, (byte)1, -1L, 8, 0, false, false, -1L, 3, 1800000L, false);
/*      */   }
/*      */   
/*      */   public GenericObjectPool(PoolableObjectFactory factory, Config config) {
/*  363 */     this(factory, config.maxActive, config.whenExhaustedAction, config.maxWait, config.maxIdle, config.minIdle, config.testOnBorrow, config.testOnReturn, config.timeBetweenEvictionRunsMillis, config.numTestsPerEvictionRun, config.minEvictableIdleTimeMillis, config.testWhileIdle, config.softMinEvictableIdleTimeMillis, config.lifo);
/*      */   }
/*      */   
/*      */   public GenericObjectPool(PoolableObjectFactory factory, int maxActive) {
/*  375 */     this(factory, maxActive, (byte)1, -1L, 8, 0, false, false, -1L, 3, 1800000L, false);
/*      */   }
/*      */   
/*      */   public GenericObjectPool(PoolableObjectFactory factory, int maxActive, byte whenExhaustedAction, long maxWait) {
/*  389 */     this(factory, maxActive, whenExhaustedAction, maxWait, 8, 0, false, false, -1L, 3, 1800000L, false);
/*      */   }
/*      */   
/*      */   public GenericObjectPool(PoolableObjectFactory factory, int maxActive, byte whenExhaustedAction, long maxWait, boolean testOnBorrow, boolean testOnReturn) {
/*  408 */     this(factory, maxActive, whenExhaustedAction, maxWait, 8, 0, testOnBorrow, testOnReturn, -1L, 3, 1800000L, false);
/*      */   }
/*      */   
/*      */   public GenericObjectPool(PoolableObjectFactory factory, int maxActive, byte whenExhaustedAction, long maxWait, int maxIdle) {
/*  423 */     this(factory, maxActive, whenExhaustedAction, maxWait, maxIdle, 0, false, false, -1L, 3, 1800000L, false);
/*      */   }
/*      */   
/*      */   public GenericObjectPool(PoolableObjectFactory factory, int maxActive, byte whenExhaustedAction, long maxWait, int maxIdle, boolean testOnBorrow, boolean testOnReturn) {
/*  443 */     this(factory, maxActive, whenExhaustedAction, maxWait, maxIdle, 0, testOnBorrow, testOnReturn, -1L, 3, 1800000L, false);
/*      */   }
/*      */   
/*      */   public GenericObjectPool(PoolableObjectFactory factory, int maxActive, byte whenExhaustedAction, long maxWait, int maxIdle, boolean testOnBorrow, boolean testOnReturn, long timeBetweenEvictionRunsMillis, int numTestsPerEvictionRun, long minEvictableIdleTimeMillis, boolean testWhileIdle) {
/*  472 */     this(factory, maxActive, whenExhaustedAction, maxWait, maxIdle, 0, testOnBorrow, testOnReturn, timeBetweenEvictionRunsMillis, numTestsPerEvictionRun, minEvictableIdleTimeMillis, testWhileIdle);
/*      */   }
/*      */   
/*      */   public GenericObjectPool(PoolableObjectFactory factory, int maxActive, byte whenExhaustedAction, long maxWait, int maxIdle, int minIdle, boolean testOnBorrow, boolean testOnReturn, long timeBetweenEvictionRunsMillis, int numTestsPerEvictionRun, long minEvictableIdleTimeMillis, boolean testWhileIdle) {
/*  501 */     this(factory, maxActive, whenExhaustedAction, maxWait, maxIdle, minIdle, testOnBorrow, testOnReturn, timeBetweenEvictionRunsMillis, numTestsPerEvictionRun, minEvictableIdleTimeMillis, testWhileIdle, -1L);
/*      */   }
/*      */   
/*      */   public GenericObjectPool(PoolableObjectFactory factory, int maxActive, byte whenExhaustedAction, long maxWait, int maxIdle, int minIdle, boolean testOnBorrow, boolean testOnReturn, long timeBetweenEvictionRunsMillis, int numTestsPerEvictionRun, long minEvictableIdleTimeMillis, boolean testWhileIdle, long softMinEvictableIdleTimeMillis) {
/*  536 */     this(factory, maxActive, whenExhaustedAction, maxWait, maxIdle, minIdle, testOnBorrow, testOnReturn, timeBetweenEvictionRunsMillis, numTestsPerEvictionRun, minEvictableIdleTimeMillis, testWhileIdle, softMinEvictableIdleTimeMillis, true);
/*      */   }
/*      */   
/*      */   public GenericObjectPool(PoolableObjectFactory factory, int maxActive, byte whenExhaustedAction, long maxWait, int maxIdle, int minIdle, boolean testOnBorrow, boolean testOnReturn, long timeBetweenEvictionRunsMillis, int numTestsPerEvictionRun, long minEvictableIdleTimeMillis, boolean testWhileIdle, long softMinEvictableIdleTimeMillis, boolean lifo) {
/* 1842 */     this._maxIdle = 8;
/* 1849 */     this._minIdle = 0;
/* 1856 */     this._maxActive = 8;
/* 1874 */     this._maxWait = -1L;
/* 1888 */     this._whenExhaustedAction = 1;
/* 1901 */     this._testOnBorrow = false;
/* 1912 */     this._testOnReturn = false;
/* 1925 */     this._testWhileIdle = false;
/* 1936 */     this._timeBetweenEvictionRunsMillis = -1L;
/* 1951 */     this._numTestsPerEvictionRun = 3;
/* 1965 */     this._minEvictableIdleTimeMillis = 1800000L;
/* 1978 */     this._softMinEvictableIdleTimeMillis = -1L;
/* 1981 */     this._lifo = true;
/* 1984 */     this._pool = null;
/* 1987 */     this._evictionCursor = null;
/* 1990 */     this._factory = null;
/* 1996 */     this._numActive = 0;
/* 2001 */     this._evictor = null;
/* 2008 */     this._numInternalProcessing = 0;
/* 2015 */     this._allocationQueue = new LinkedList();
/*      */     this._factory = factory;
/*      */     this._maxActive = maxActive;
/*      */     this._lifo = lifo;
/*      */     switch (whenExhaustedAction) {
/*      */       case 0:
/*      */       case 1:
/*      */       case 2:
/*      */         this._whenExhaustedAction = whenExhaustedAction;
/*      */         break;
/*      */       default:
/*      */         throw new IllegalArgumentException("whenExhaustedAction " + whenExhaustedAction + " not recognized.");
/*      */     } 
/*      */     this._maxWait = maxWait;
/*      */     this._maxIdle = maxIdle;
/*      */     this._minIdle = minIdle;
/*      */     this._testOnBorrow = testOnBorrow;
/*      */     this._testOnReturn = testOnReturn;
/*      */     this._timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
/*      */     this._numTestsPerEvictionRun = numTestsPerEvictionRun;
/*      */     this._minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
/*      */     this._softMinEvictableIdleTimeMillis = softMinEvictableIdleTimeMillis;
/*      */     this._testWhileIdle = testWhileIdle;
/*      */     this._pool = new CursorableLinkedList();
/*      */     startEvictor(this._timeBetweenEvictionRunsMillis);
/*      */   }
/*      */   
/*      */   public synchronized int getMaxActive() {
/*      */     return this._maxActive;
/*      */   }
/*      */   
/*      */   public synchronized void setMaxActive(int maxActive) {
/*      */     this._maxActive = maxActive;
/*      */     allocate();
/*      */   }
/*      */   
/*      */   public synchronized byte getWhenExhaustedAction() {
/*      */     return this._whenExhaustedAction;
/*      */   }
/*      */   
/*      */   public synchronized void setWhenExhaustedAction(byte whenExhaustedAction) {
/*      */     switch (whenExhaustedAction) {
/*      */       case 0:
/*      */       case 1:
/*      */       case 2:
/*      */         this._whenExhaustedAction = whenExhaustedAction;
/*      */         allocate();
/*      */         return;
/*      */     } 
/*      */     throw new IllegalArgumentException("whenExhaustedAction " + whenExhaustedAction + " not recognized.");
/*      */   }
/*      */   
/*      */   public synchronized long getMaxWait() {
/*      */     return this._maxWait;
/*      */   }
/*      */   
/*      */   public synchronized void setMaxWait(long maxWait) {
/*      */     this._maxWait = maxWait;
/*      */     allocate();
/*      */   }
/*      */   
/*      */   public synchronized int getMaxIdle() {
/*      */     return this._maxIdle;
/*      */   }
/*      */   
/*      */   public synchronized void setMaxIdle(int maxIdle) {
/*      */     this._maxIdle = maxIdle;
/*      */     allocate();
/*      */   }
/*      */   
/*      */   public synchronized void setMinIdle(int minIdle) {
/*      */     this._minIdle = minIdle;
/*      */     allocate();
/*      */   }
/*      */   
/*      */   public synchronized int getMinIdle() {
/*      */     return this._minIdle;
/*      */   }
/*      */   
/*      */   public boolean getTestOnBorrow() {
/*      */     return this._testOnBorrow;
/*      */   }
/*      */   
/*      */   public void setTestOnBorrow(boolean testOnBorrow) {
/*      */     this._testOnBorrow = testOnBorrow;
/*      */   }
/*      */   
/*      */   public boolean getTestOnReturn() {
/*      */     return this._testOnReturn;
/*      */   }
/*      */   
/*      */   public void setTestOnReturn(boolean testOnReturn) {
/*      */     this._testOnReturn = testOnReturn;
/*      */   }
/*      */   
/*      */   public synchronized long getTimeBetweenEvictionRunsMillis() {
/*      */     return this._timeBetweenEvictionRunsMillis;
/*      */   }
/*      */   
/*      */   public synchronized void setTimeBetweenEvictionRunsMillis(long timeBetweenEvictionRunsMillis) {
/*      */     this._timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
/*      */     startEvictor(this._timeBetweenEvictionRunsMillis);
/*      */   }
/*      */   
/*      */   public synchronized int getNumTestsPerEvictionRun() {
/*      */     return this._numTestsPerEvictionRun;
/*      */   }
/*      */   
/*      */   public synchronized void setNumTestsPerEvictionRun(int numTestsPerEvictionRun) {
/*      */     this._numTestsPerEvictionRun = numTestsPerEvictionRun;
/*      */   }
/*      */   
/*      */   public synchronized long getMinEvictableIdleTimeMillis() {
/*      */     return this._minEvictableIdleTimeMillis;
/*      */   }
/*      */   
/*      */   public synchronized void setMinEvictableIdleTimeMillis(long minEvictableIdleTimeMillis) {
/*      */     this._minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
/*      */   }
/*      */   
/*      */   public synchronized long getSoftMinEvictableIdleTimeMillis() {
/*      */     return this._softMinEvictableIdleTimeMillis;
/*      */   }
/*      */   
/*      */   public synchronized void setSoftMinEvictableIdleTimeMillis(long softMinEvictableIdleTimeMillis) {
/*      */     this._softMinEvictableIdleTimeMillis = softMinEvictableIdleTimeMillis;
/*      */   }
/*      */   
/*      */   public synchronized boolean getTestWhileIdle() {
/*      */     return this._testWhileIdle;
/*      */   }
/*      */   
/*      */   public synchronized void setTestWhileIdle(boolean testWhileIdle) {
/*      */     this._testWhileIdle = testWhileIdle;
/*      */   }
/*      */   
/*      */   public synchronized boolean getLifo() {
/*      */     return this._lifo;
/*      */   }
/*      */   
/*      */   public synchronized void setLifo(boolean lifo) {
/*      */     this._lifo = lifo;
/*      */   }
/*      */   
/*      */   public synchronized void setConfig(Config conf) {
/*      */     setMaxIdle(conf.maxIdle);
/*      */     setMinIdle(conf.minIdle);
/*      */     setMaxActive(conf.maxActive);
/*      */     setMaxWait(conf.maxWait);
/*      */     setWhenExhaustedAction(conf.whenExhaustedAction);
/*      */     setTestOnBorrow(conf.testOnBorrow);
/*      */     setTestOnReturn(conf.testOnReturn);
/*      */     setTestWhileIdle(conf.testWhileIdle);
/*      */     setNumTestsPerEvictionRun(conf.numTestsPerEvictionRun);
/*      */     setMinEvictableIdleTimeMillis(conf.minEvictableIdleTimeMillis);
/*      */     setTimeBetweenEvictionRunsMillis(conf.timeBetweenEvictionRunsMillis);
/*      */     setSoftMinEvictableIdleTimeMillis(conf.softMinEvictableIdleTimeMillis);
/*      */     setLifo(conf.lifo);
/*      */     allocate();
/*      */   }
/*      */   
/*      */   public Object borrowObject() throws Exception {
/*      */     byte whenExhaustedAction;
/*      */     long maxWait, starttime = System.currentTimeMillis();
/*      */     Latch latch = new Latch();
/*      */     synchronized (this) {
/*      */       whenExhaustedAction = this._whenExhaustedAction;
/*      */       maxWait = this._maxWait;
/*      */       this._allocationQueue.add(latch);
/*      */       allocate();
/*      */     } 
/*      */     while (true) {
/*      */       synchronized (this) {
/*      */         assertOpen();
/*      */       } 
/*      */       if (latch.getPair() == null)
/*      */         if (!latch.mayCreate())
/*      */           switch (whenExhaustedAction) {
/*      */             case 2:
/*      */               synchronized (this) {
/*      */                 if (latch.getPair() == null && !latch.mayCreate()) {
/*      */                   this._allocationQueue.remove(latch);
/*      */                   this._numInternalProcessing++;
/*      */                 } 
/*      */               } 
/*      */               break;
/*      */             case 0:
/*      */               synchronized (this) {
/*      */                 if (latch.getPair() != null || latch.mayCreate())
/*      */                   break; 
/*      */                 this._allocationQueue.remove(latch);
/*      */               } 
/*      */               throw new NoSuchElementException("Pool exhausted");
/*      */             case 1:
/*      */               try {
/*      */                 synchronized (latch) {
/*      */                   if (latch.getPair() == null && !latch.mayCreate()) {
/*      */                     if (maxWait <= 0L) {
/*      */                       latch.wait();
/*      */                     } else {
/*      */                       long elapsed = System.currentTimeMillis() - starttime;
/*      */                       long waitTime = maxWait - elapsed;
/*      */                       if (waitTime > 0L)
/*      */                         latch.wait(waitTime); 
/*      */                     } 
/*      */                   } else {
/*      */                     break;
/*      */                   } 
/*      */                 } 
/*      */               } catch (InterruptedException e) {
/*      */                 Thread.currentThread().interrupt();
/*      */                 throw e;
/*      */               } 
/*      */               if (maxWait > 0L && System.currentTimeMillis() - starttime >= maxWait) {
/*      */                 synchronized (this) {
/*      */                   if (latch.getPair() == null && !latch.mayCreate()) {
/*      */                     this._allocationQueue.remove(latch);
/*      */                   } else {
/*      */                     break;
/*      */                   } 
/*      */                 } 
/*      */                 throw new NoSuchElementException("Timeout waiting for idle object");
/*      */               } 
/*      */               continue;
/*      */             default:
/*      */               throw new IllegalArgumentException("WhenExhaustedAction property " + whenExhaustedAction + " not recognized.");
/*      */           }   
/*      */       boolean newlyCreated = false;
/*      */       if (null == latch.getPair())
/*      */         try {
/*      */           Object obj = this._factory.makeObject();
/*      */           latch.setPair(new GenericKeyedObjectPool.ObjectTimestampPair(obj));
/*      */           newlyCreated = true;
/*      */         } finally {
/*      */           if (!newlyCreated)
/*      */             synchronized (this) {
/*      */               this._numInternalProcessing--;
/*      */               allocate();
/*      */             }  
/*      */         }  
/*      */       try {
/*      */         this._factory.activateObject((latch.getPair()).value);
/*      */         if (this._testOnBorrow && !this._factory.validateObject((latch.getPair()).value))
/*      */           throw new Exception("ValidateObject failed"); 
/*      */         synchronized (this) {
/*      */           this._numInternalProcessing--;
/*      */           this._numActive++;
/*      */         } 
/*      */         return (latch.getPair()).value;
/*      */       } catch (Throwable e) {
/*      */         try {
/*      */           this._factory.destroyObject((latch.getPair()).value);
/*      */         } catch (Throwable e2) {}
/*      */         synchronized (this) {
/*      */           this._numInternalProcessing--;
/*      */           if (!newlyCreated) {
/*      */             latch.reset();
/*      */             this._allocationQueue.add(0, latch);
/*      */           } 
/*      */           allocate();
/*      */         } 
/*      */         if (newlyCreated)
/*      */           throw new NoSuchElementException("Could not create a validated object, cause: " + e.getMessage()); 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private synchronized void allocate() {
/*      */     if (isClosed())
/*      */       return; 
/*      */     while (!this._pool.isEmpty() && !this._allocationQueue.isEmpty()) {
/*      */       Latch latch = this._allocationQueue.removeFirst();
/*      */       latch.setPair((GenericKeyedObjectPool.ObjectTimestampPair)this._pool.removeFirst());
/*      */       this._numInternalProcessing++;
/*      */       synchronized (latch) {
/*      */         latch.notify();
/*      */       } 
/*      */     } 
/*      */     while (!this._allocationQueue.isEmpty() && (this._maxActive < 0 || this._numActive + this._numInternalProcessing < this._maxActive)) {
/*      */       Latch latch = this._allocationQueue.removeFirst();
/*      */       latch.setMayCreate(true);
/*      */       this._numInternalProcessing++;
/*      */       synchronized (latch) {
/*      */         latch.notify();
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public void invalidateObject(Object obj) throws Exception {
/*      */     try {
/*      */       if (this._factory != null)
/*      */         this._factory.destroyObject(obj); 
/*      */     } finally {
/*      */       synchronized (this) {
/*      */         this._numActive--;
/*      */         allocate();
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public void clear() {
/*      */     List toDestroy = new ArrayList();
/*      */     synchronized (this) {
/*      */       toDestroy.addAll(this._pool);
/*      */       this._numInternalProcessing += this._pool._size;
/*      */       this._pool.clear();
/*      */     } 
/*      */     destroy(toDestroy);
/*      */   }
/*      */   
/*      */   private void destroy(Collection c) {
/*      */     for (Iterator it = c.iterator(); it.hasNext();) {
/*      */       try {
/*      */         this._factory.destroyObject(((GenericKeyedObjectPool.ObjectTimestampPair)it.next()).value);
/*      */       } catch (Exception e) {
/*      */       
/*      */       } finally {
/*      */         synchronized (this) {
/*      */           this._numInternalProcessing--;
/*      */           allocate();
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public synchronized int getNumActive() {
/*      */     return this._numActive;
/*      */   }
/*      */   
/*      */   public synchronized int getNumIdle() {
/*      */     return this._pool.size();
/*      */   }
/*      */   
/*      */   public void returnObject(Object obj) throws Exception {
/*      */     try {
/*      */       addObjectToPool(obj, true);
/*      */     } catch (Exception e) {
/*      */       if (this._factory != null) {
/*      */         try {
/*      */           this._factory.destroyObject(obj);
/*      */         } catch (Exception e2) {}
/*      */         synchronized (this) {
/*      */           this._numActive--;
/*      */           allocate();
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void addObjectToPool(Object obj, boolean decrementNumActive) throws Exception {
/*      */     boolean success = true;
/*      */     if (this._testOnReturn && !this._factory.validateObject(obj)) {
/*      */       success = false;
/*      */     } else {
/*      */       this._factory.passivateObject(obj);
/*      */     } 
/*      */     boolean shouldDestroy = !success;
/*      */     synchronized (this) {
/*      */       if (isClosed()) {
/*      */         shouldDestroy = true;
/*      */       } else if (this._maxIdle >= 0 && this._pool.size() >= this._maxIdle) {
/*      */         shouldDestroy = true;
/*      */       } else if (success) {
/*      */         if (this._lifo) {
/*      */           this._pool.addFirst(new GenericKeyedObjectPool.ObjectTimestampPair(obj));
/*      */         } else {
/*      */           this._pool.addLast(new GenericKeyedObjectPool.ObjectTimestampPair(obj));
/*      */         } 
/*      */         if (decrementNumActive)
/*      */           this._numActive--; 
/*      */         allocate();
/*      */       } 
/*      */     } 
/*      */     if (shouldDestroy) {
/*      */       try {
/*      */         this._factory.destroyObject(obj);
/*      */       } catch (Exception e) {}
/*      */       if (decrementNumActive)
/*      */         synchronized (this) {
/*      */           this._numActive--;
/*      */           allocate();
/*      */         }  
/*      */     } 
/*      */   }
/*      */   
/*      */   public void close() throws Exception {
/*      */     super.close();
/*      */     synchronized (this) {
/*      */       clear();
/*      */       startEvictor(-1L);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void setFactory(PoolableObjectFactory factory) throws IllegalStateException {
/*      */     List toDestroy = new ArrayList();
/*      */     synchronized (this) {
/*      */       assertOpen();
/*      */       if (0 < getNumActive())
/*      */         throw new IllegalStateException("Objects are already active"); 
/*      */       toDestroy.addAll(this._pool);
/*      */       this._numInternalProcessing += this._pool._size;
/*      */       this._pool.clear();
/*      */       this._factory = factory;
/*      */     } 
/*      */     destroy(toDestroy);
/*      */   }
/*      */   
/*      */   public void evict() throws Exception {
/*      */     assertOpen();
/*      */     synchronized (this) {
/*      */       if (this._pool.isEmpty())
/*      */         return; 
/*      */       if (null == this._evictionCursor)
/*      */         this._evictionCursor = this._pool.cursor(this._lifo ? this._pool.size() : 0); 
/*      */     } 
/*      */     for (int i = 0, m = getNumTests(); i < m; i++) {
/*      */       GenericKeyedObjectPool.ObjectTimestampPair pair;
/*      */       synchronized (this) {
/*      */         if ((this._lifo && !this._evictionCursor.hasPrevious()) || (!this._lifo && !this._evictionCursor.hasNext())) {
/*      */           this._evictionCursor.close();
/*      */           this._evictionCursor = this._pool.cursor(this._lifo ? this._pool.size() : 0);
/*      */         } 
/*      */         pair = this._lifo ? (GenericKeyedObjectPool.ObjectTimestampPair)this._evictionCursor.previous() : (GenericKeyedObjectPool.ObjectTimestampPair)this._evictionCursor.next();
/*      */         this._evictionCursor.remove();
/*      */         this._numInternalProcessing++;
/*      */       } 
/*      */       boolean removeObject = false;
/*      */       long idleTimeMilis = System.currentTimeMillis() - pair.tstamp;
/*      */       if (getMinEvictableIdleTimeMillis() > 0L && idleTimeMilis > getMinEvictableIdleTimeMillis()) {
/*      */         removeObject = true;
/*      */       } else if (getSoftMinEvictableIdleTimeMillis() > 0L && idleTimeMilis > getSoftMinEvictableIdleTimeMillis() && getNumIdle() + 1 > getMinIdle()) {
/*      */         removeObject = true;
/*      */       } 
/*      */       if (getTestWhileIdle() && !removeObject) {
/*      */         boolean active = false;
/*      */         try {
/*      */           this._factory.activateObject(pair.value);
/*      */           active = true;
/*      */         } catch (Exception e) {
/*      */           removeObject = true;
/*      */         } 
/*      */         if (active)
/*      */           if (!this._factory.validateObject(pair.value)) {
/*      */             removeObject = true;
/*      */           } else {
/*      */             try {
/*      */               this._factory.passivateObject(pair.value);
/*      */             } catch (Exception e) {
/*      */               removeObject = true;
/*      */             } 
/*      */           }  
/*      */       } 
/*      */       if (removeObject)
/*      */         try {
/*      */           this._factory.destroyObject(pair.value);
/*      */         } catch (Exception e) {} 
/*      */       synchronized (this) {
/*      */         if (!removeObject) {
/*      */           this._evictionCursor.add(pair);
/*      */           if (this._lifo)
/*      */             this._evictionCursor.previous(); 
/*      */         } 
/*      */         this._numInternalProcessing--;
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void ensureMinIdle() throws Exception {
/*      */     int objectDeficit = calculateDeficit(false);
/*      */     for (int j = 0; j < objectDeficit && calculateDeficit(true) > 0; j++) {
/*      */       try {
/*      */         addObject();
/*      */         synchronized (this) {
/*      */           this._numInternalProcessing--;
/*      */           allocate();
/*      */         } 
/*      */       } finally {
/*      */         synchronized (this) {
/*      */           this._numInternalProcessing--;
/*      */           allocate();
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private synchronized int calculateDeficit(boolean incrementInternal) {
/*      */     int objectDeficit = getMinIdle() - getNumIdle();
/*      */     if (this._maxActive > 0) {
/*      */       int growLimit = Math.max(0, getMaxActive() - getNumActive() - getNumIdle() - this._numInternalProcessing);
/*      */       objectDeficit = Math.min(objectDeficit, growLimit);
/*      */     } 
/*      */     if (incrementInternal && objectDeficit > 0)
/*      */       this._numInternalProcessing++; 
/*      */     return objectDeficit;
/*      */   }
/*      */   
/*      */   public void addObject() throws Exception {
/*      */     assertOpen();
/*      */     if (this._factory == null)
/*      */       throw new IllegalStateException("Cannot add objects without a factory."); 
/*      */     Object obj = this._factory.makeObject();
/*      */     try {
/*      */       assertOpen();
/*      */       addObjectToPool(obj, false);
/*      */     } catch (IllegalStateException ex) {
/*      */       try {
/*      */         this._factory.destroyObject(obj);
/*      */       } catch (Exception ex2) {}
/*      */       throw ex;
/*      */     } 
/*      */   }
/*      */   
/*      */   protected synchronized void startEvictor(long delay) {
/*      */     if (null != this._evictor) {
/*      */       EvictionTimer.cancel(this._evictor);
/*      */       this._evictor = null;
/*      */     } 
/*      */     if (delay > 0L) {
/*      */       this._evictor = new Evictor();
/*      */       EvictionTimer.schedule(this._evictor, delay, delay);
/*      */     } 
/*      */   }
/*      */   
/*      */   synchronized String debugInfo() {
/*      */     StringBuffer buf = new StringBuffer();
/*      */     buf.append("Active: ").append(getNumActive()).append("\n");
/*      */     buf.append("Idle: ").append(getNumIdle()).append("\n");
/*      */     buf.append("Idle Objects:\n");
/*      */     Iterator it = this._pool.iterator();
/*      */     long time = System.currentTimeMillis();
/*      */     while (it.hasNext()) {
/*      */       GenericKeyedObjectPool.ObjectTimestampPair pair = it.next();
/*      */       buf.append("\t").append(pair.value).append("\t").append(time - pair.tstamp).append("\n");
/*      */     } 
/*      */     return buf.toString();
/*      */   }
/*      */   
/*      */   private int getNumTests() {
/*      */     if (this._numTestsPerEvictionRun >= 0)
/*      */       return Math.min(this._numTestsPerEvictionRun, this._pool.size()); 
/*      */     return (int)Math.ceil(this._pool.size() / Math.abs(this._numTestsPerEvictionRun));
/*      */   }
/*      */   
/*      */   private class Evictor extends TimerTask {
/*      */     private final GenericObjectPool this$0;
/*      */     
/*      */     private Evictor(GenericObjectPool this$0) {
/*      */       GenericObjectPool.this = GenericObjectPool.this;
/*      */     }
/*      */     
/*      */     public void run() {
/*      */       try {
/*      */         GenericObjectPool.this.evict();
/*      */       } catch (Exception e) {
/*      */       
/*      */       } catch (OutOfMemoryError oome) {
/*      */         oome.printStackTrace(System.err);
/*      */       } 
/*      */       try {
/*      */         GenericObjectPool.this.ensureMinIdle();
/*      */       } catch (Exception e) {}
/*      */     }
/*      */   }
/*      */   
/*      */   public static class Config {
/*      */     public int maxIdle = 8;
/*      */     
/*      */     public int minIdle = 0;
/*      */     
/*      */     public int maxActive = 8;
/*      */     
/*      */     public long maxWait = -1L;
/*      */     
/*      */     public byte whenExhaustedAction = 1;
/*      */     
/*      */     public boolean testOnBorrow = false;
/*      */     
/*      */     public boolean testOnReturn = false;
/*      */     
/*      */     public boolean testWhileIdle = false;
/*      */     
/*      */     public long timeBetweenEvictionRunsMillis = -1L;
/*      */     
/*      */     public int numTestsPerEvictionRun = 3;
/*      */     
/*      */     public long minEvictableIdleTimeMillis = 1800000L;
/*      */     
/*      */     public long softMinEvictableIdleTimeMillis = -1L;
/*      */     
/*      */     public boolean lifo = true;
/*      */   }
/*      */   
/*      */   private static final class Latch {
/*      */     private GenericKeyedObjectPool.ObjectTimestampPair _pair;
/*      */     
/*      */     private boolean _mayCreate = false;
/*      */     
/*      */     private synchronized GenericKeyedObjectPool.ObjectTimestampPair getPair() {
/*      */       return this._pair;
/*      */     }
/*      */     
/*      */     private synchronized void setPair(GenericKeyedObjectPool.ObjectTimestampPair pair) {
/*      */       this._pair = pair;
/*      */     }
/*      */     
/*      */     private synchronized boolean mayCreate() {
/*      */       return this._mayCreate;
/*      */     }
/*      */     
/*      */     private synchronized void setMayCreate(boolean mayCreate) {
/*      */       this._mayCreate = mayCreate;
/*      */     }
/*      */     
/*      */     private synchronized void reset() {
/*      */       this._pair = null;
/*      */       this._mayCreate = false;
/*      */     }
/*      */     
/*      */     private Latch() {}
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\pool\impl\GenericObjectPool.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */