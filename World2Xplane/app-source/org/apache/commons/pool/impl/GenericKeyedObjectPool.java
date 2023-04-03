/*      */ package org.apache.commons.pool.impl;
/*      */ 
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collection;
/*      */ import java.util.HashMap;
/*      */ import java.util.Iterator;
/*      */ import java.util.LinkedList;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.NoSuchElementException;
/*      */ import java.util.Set;
/*      */ import java.util.TimerTask;
/*      */ import java.util.TreeMap;
/*      */ import org.apache.commons.pool.BaseKeyedObjectPool;
/*      */ import org.apache.commons.pool.KeyedObjectPool;
/*      */ import org.apache.commons.pool.KeyedPoolableObjectFactory;
/*      */ 
/*      */ public class GenericKeyedObjectPool extends BaseKeyedObjectPool implements KeyedObjectPool {
/*      */   public static final byte WHEN_EXHAUSTED_FAIL = 0;
/*      */   
/*      */   public static final byte WHEN_EXHAUSTED_BLOCK = 1;
/*      */   
/*      */   public static final byte WHEN_EXHAUSTED_GROW = 2;
/*      */   
/*      */   public static final int DEFAULT_MAX_IDLE = 8;
/*      */   
/*      */   public static final int DEFAULT_MAX_ACTIVE = 8;
/*      */   
/*      */   public static final int DEFAULT_MAX_TOTAL = -1;
/*      */   
/*      */   public static final byte DEFAULT_WHEN_EXHAUSTED_ACTION = 1;
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
/*      */   public static final int DEFAULT_MIN_IDLE = 0;
/*      */   
/*      */   public static final boolean DEFAULT_LIFO = true;
/*      */   
/*      */   private int _maxIdle;
/*      */   
/*      */   private int _minIdle;
/*      */   
/*      */   private int _maxActive;
/*      */   
/*      */   private int _maxTotal;
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
/*      */   private Map _poolMap;
/*      */   
/*      */   private int _totalActive;
/*      */   
/*      */   private int _totalIdle;
/*      */   
/*      */   private int _totalInternalProcessing;
/*      */   
/*      */   private KeyedPoolableObjectFactory _factory;
/*      */   
/*      */   private Evictor _evictor;
/*      */   
/*      */   private CursorableLinkedList _poolList;
/*      */   
/*      */   private CursorableLinkedList.Cursor _evictionCursor;
/*      */   
/*      */   private CursorableLinkedList.Cursor _evictionKeyCursor;
/*      */   
/*      */   private boolean _lifo;
/*      */   
/*      */   private LinkedList _allocationQueue;
/*      */   
/*      */   public GenericKeyedObjectPool() {
/*  360 */     this((KeyedPoolableObjectFactory)null, 8, (byte)1, -1L, 8, false, false, -1L, 3, 1800000L, false);
/*      */   }
/*      */   
/*      */   public GenericKeyedObjectPool(KeyedPoolableObjectFactory factory) {
/*  371 */     this(factory, 8, (byte)1, -1L, 8, false, false, -1L, 3, 1800000L, false);
/*      */   }
/*      */   
/*      */   public GenericKeyedObjectPool(KeyedPoolableObjectFactory factory, Config config) {
/*  383 */     this(factory, config.maxActive, config.whenExhaustedAction, config.maxWait, config.maxIdle, config.maxTotal, config.minIdle, config.testOnBorrow, config.testOnReturn, config.timeBetweenEvictionRunsMillis, config.numTestsPerEvictionRun, config.minEvictableIdleTimeMillis, config.testWhileIdle, config.lifo);
/*      */   }
/*      */   
/*      */   public GenericKeyedObjectPool(KeyedPoolableObjectFactory factory, int maxActive) {
/*  395 */     this(factory, maxActive, (byte)1, -1L, 8, false, false, -1L, 3, 1800000L, false);
/*      */   }
/*      */   
/*      */   public GenericKeyedObjectPool(KeyedPoolableObjectFactory factory, int maxActive, byte whenExhaustedAction, long maxWait) {
/*  411 */     this(factory, maxActive, whenExhaustedAction, maxWait, 8, false, false, -1L, 3, 1800000L, false);
/*      */   }
/*      */   
/*      */   public GenericKeyedObjectPool(KeyedPoolableObjectFactory factory, int maxActive, byte whenExhaustedAction, long maxWait, boolean testOnBorrow, boolean testOnReturn) {
/*  431 */     this(factory, maxActive, whenExhaustedAction, maxWait, 8, testOnBorrow, testOnReturn, -1L, 3, 1800000L, false);
/*      */   }
/*      */   
/*      */   public GenericKeyedObjectPool(KeyedPoolableObjectFactory factory, int maxActive, byte whenExhaustedAction, long maxWait, int maxIdle) {
/*  449 */     this(factory, maxActive, whenExhaustedAction, maxWait, maxIdle, false, false, -1L, 3, 1800000L, false);
/*      */   }
/*      */   
/*      */   public GenericKeyedObjectPool(KeyedPoolableObjectFactory factory, int maxActive, byte whenExhaustedAction, long maxWait, int maxIdle, boolean testOnBorrow, boolean testOnReturn) {
/*  471 */     this(factory, maxActive, whenExhaustedAction, maxWait, maxIdle, testOnBorrow, testOnReturn, -1L, 3, 1800000L, false);
/*      */   }
/*      */   
/*      */   public GenericKeyedObjectPool(KeyedPoolableObjectFactory factory, int maxActive, byte whenExhaustedAction, long maxWait, int maxIdle, boolean testOnBorrow, boolean testOnReturn, long timeBetweenEvictionRunsMillis, int numTestsPerEvictionRun, long minEvictableIdleTimeMillis, boolean testWhileIdle) {
/*  503 */     this(factory, maxActive, whenExhaustedAction, maxWait, maxIdle, -1, testOnBorrow, testOnReturn, timeBetweenEvictionRunsMillis, numTestsPerEvictionRun, minEvictableIdleTimeMillis, testWhileIdle);
/*      */   }
/*      */   
/*      */   public GenericKeyedObjectPool(KeyedPoolableObjectFactory factory, int maxActive, byte whenExhaustedAction, long maxWait, int maxIdle, int maxTotal, boolean testOnBorrow, boolean testOnReturn, long timeBetweenEvictionRunsMillis, int numTestsPerEvictionRun, long minEvictableIdleTimeMillis, boolean testWhileIdle) {
/*  536 */     this(factory, maxActive, whenExhaustedAction, maxWait, maxIdle, maxTotal, 0, testOnBorrow, testOnReturn, timeBetweenEvictionRunsMillis, numTestsPerEvictionRun, minEvictableIdleTimeMillis, testWhileIdle);
/*      */   }
/*      */   
/*      */   public GenericKeyedObjectPool(KeyedPoolableObjectFactory factory, int maxActive, byte whenExhaustedAction, long maxWait, int maxIdle, int maxTotal, int minIdle, boolean testOnBorrow, boolean testOnReturn, long timeBetweenEvictionRunsMillis, int numTestsPerEvictionRun, long minEvictableIdleTimeMillis, boolean testWhileIdle) {
/*  571 */     this(factory, maxActive, whenExhaustedAction, maxWait, maxIdle, maxTotal, minIdle, testOnBorrow, testOnReturn, timeBetweenEvictionRunsMillis, numTestsPerEvictionRun, minEvictableIdleTimeMillis, testWhileIdle, true);
/*      */   }
/*      */   
/*      */   public GenericKeyedObjectPool(KeyedPoolableObjectFactory factory, int maxActive, byte whenExhaustedAction, long maxWait, int maxIdle, int maxTotal, int minIdle, boolean testOnBorrow, boolean testOnReturn, long timeBetweenEvictionRunsMillis, int numTestsPerEvictionRun, long minEvictableIdleTimeMillis, boolean testWhileIdle, boolean lifo) {
/* 2454 */     this._maxIdle = 8;
/* 2461 */     this._minIdle = 0;
/* 2468 */     this._maxActive = 8;
/* 2475 */     this._maxTotal = -1;
/* 2493 */     this._maxWait = -1L;
/* 2507 */     this._whenExhaustedAction = 1;
/* 2520 */     this._testOnBorrow = false;
/* 2531 */     this._testOnReturn = false;
/* 2544 */     this._testWhileIdle = false;
/* 2555 */     this._timeBetweenEvictionRunsMillis = -1L;
/* 2570 */     this._numTestsPerEvictionRun = 3;
/* 2584 */     this._minEvictableIdleTimeMillis = 1800000L;
/* 2587 */     this._poolMap = null;
/* 2590 */     this._totalActive = 0;
/* 2593 */     this._totalIdle = 0;
/* 2600 */     this._totalInternalProcessing = 0;
/* 2603 */     this._factory = null;
/* 2608 */     this._evictor = null;
/* 2614 */     this._poolList = null;
/* 2617 */     this._evictionCursor = null;
/* 2620 */     this._evictionKeyCursor = null;
/* 2623 */     this._lifo = true;
/* 2630 */     this._allocationQueue = new LinkedList();
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
/*      */     this._maxTotal = maxTotal;
/*      */     this._minIdle = minIdle;
/*      */     this._testOnBorrow = testOnBorrow;
/*      */     this._testOnReturn = testOnReturn;
/*      */     this._timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
/*      */     this._numTestsPerEvictionRun = numTestsPerEvictionRun;
/*      */     this._minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
/*      */     this._testWhileIdle = testWhileIdle;
/*      */     this._poolMap = new HashMap();
/*      */     this._poolList = new CursorableLinkedList();
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
/*      */   public synchronized int getMaxTotal() {
/*      */     return this._maxTotal;
/*      */   }
/*      */   
/*      */   public synchronized void setMaxTotal(int maxTotal) {
/*      */     this._maxTotal = maxTotal;
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
/*      */   public synchronized void setMinIdle(int poolSize) {
/*      */     this._minIdle = poolSize;
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
/*      */   public synchronized boolean getTestWhileIdle() {
/*      */     return this._testWhileIdle;
/*      */   }
/*      */   
/*      */   public synchronized void setTestWhileIdle(boolean testWhileIdle) {
/*      */     this._testWhileIdle = testWhileIdle;
/*      */   }
/*      */   
/*      */   public synchronized void setConfig(Config conf) {
/*      */     setMaxIdle(conf.maxIdle);
/*      */     setMaxActive(conf.maxActive);
/*      */     setMaxTotal(conf.maxTotal);
/*      */     setMinIdle(conf.minIdle);
/*      */     setMaxWait(conf.maxWait);
/*      */     setWhenExhaustedAction(conf.whenExhaustedAction);
/*      */     setTestOnBorrow(conf.testOnBorrow);
/*      */     setTestOnReturn(conf.testOnReturn);
/*      */     setTestWhileIdle(conf.testWhileIdle);
/*      */     setNumTestsPerEvictionRun(conf.numTestsPerEvictionRun);
/*      */     setMinEvictableIdleTimeMillis(conf.minEvictableIdleTimeMillis);
/*      */     setTimeBetweenEvictionRunsMillis(conf.timeBetweenEvictionRunsMillis);
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
/*      */   public Object borrowObject(Object key) throws Exception {
/*      */     byte whenExhaustedAction;
/*      */     long maxWait, starttime = System.currentTimeMillis();
/*      */     Latch latch = new Latch(key);
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
/*      */       if (null == latch.getPair())
/*      */         if (!latch.mayCreate())
/*      */           switch (whenExhaustedAction) {
/*      */             case 2:
/*      */               synchronized (this) {
/*      */                 if (latch.getPair() == null && !latch.mayCreate()) {
/*      */                   this._allocationQueue.remove(latch);
/*      */                   latch.getPool().incrementInternalProcessingCount();
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
/*      */               throw new IllegalArgumentException("whenExhaustedAction " + whenExhaustedAction + " not recognized.");
/*      */           }   
/*      */       boolean newlyCreated = false;
/*      */       if (null == latch.getPair())
/*      */         try {
/*      */           Object obj = this._factory.makeObject(key);
/*      */           latch.setPair(new ObjectTimestampPair(obj));
/*      */           newlyCreated = true;
/*      */         } finally {
/*      */           if (!newlyCreated)
/*      */             synchronized (this) {
/*      */               latch.getPool().decrementInternalProcessingCount();
/*      */               allocate();
/*      */             }  
/*      */         }  
/*      */       try {
/*      */         this._factory.activateObject(key, (latch.getPair()).value);
/*      */         if (this._testOnBorrow && !this._factory.validateObject(key, (latch.getPair()).value))
/*      */           throw new Exception("ValidateObject failed"); 
/*      */         synchronized (this) {
/*      */           latch.getPool().decrementInternalProcessingCount();
/*      */           latch.getPool().incrementActiveCount();
/*      */         } 
/*      */         return (latch.getPair()).value;
/*      */       } catch (Throwable e) {
/*      */         try {
/*      */           this._factory.destroyObject(key, (latch.getPair()).value);
/*      */         } catch (Throwable e2) {}
/*      */         synchronized (this) {
/*      */           latch.getPool().decrementInternalProcessingCount();
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
/*      */   private void allocate() {
/*      */     boolean clearOldest = false;
/*      */     synchronized (this) {
/*      */       if (isClosed())
/*      */         return; 
/*      */       Iterator allocationQueueIter = this._allocationQueue.iterator();
/*      */       while (allocationQueueIter.hasNext()) {
/*      */         Latch latch = allocationQueueIter.next();
/*      */         ObjectQueue pool = (ObjectQueue)this._poolMap.get(latch.getkey());
/*      */         if (null == pool) {
/*      */           pool = new ObjectQueue();
/*      */           this._poolMap.put(latch.getkey(), pool);
/*      */           this._poolList.add(latch.getkey());
/*      */         } 
/*      */         latch.setPool(pool);
/*      */         if (!pool.queue.isEmpty()) {
/*      */           allocationQueueIter.remove();
/*      */           latch.setPair((ObjectTimestampPair)pool.queue.removeFirst());
/*      */           pool.incrementInternalProcessingCount();
/*      */           this._totalIdle--;
/*      */           synchronized (latch) {
/*      */             latch.notify();
/*      */           } 
/*      */           continue;
/*      */         } 
/*      */         if (this._maxTotal > 0 && this._totalActive + this._totalIdle + this._totalInternalProcessing >= this._maxTotal) {
/*      */           clearOldest = true;
/*      */           break;
/*      */         } 
/*      */         if ((this._maxActive < 0 || pool.activeCount + pool.internalProcessingCount < this._maxActive) && (this._maxTotal < 0 || this._totalActive + this._totalIdle + this._totalInternalProcessing < this._maxTotal)) {
/*      */           allocationQueueIter.remove();
/*      */           latch.setMayCreate(true);
/*      */           pool.incrementInternalProcessingCount();
/*      */           synchronized (latch) {
/*      */             latch.notify();
/*      */           } 
/*      */           continue;
/*      */         } 
/*      */         if (this._maxActive < 0)
/*      */           break; 
/*      */       } 
/*      */     } 
/*      */     if (clearOldest)
/*      */       clearOldest(); 
/*      */   }
/*      */   
/*      */   public void clear() {
/*      */     Map toDestroy = new HashMap();
/*      */     synchronized (this) {
/*      */       for (Iterator it = this._poolMap.keySet().iterator(); it.hasNext(); ) {
/*      */         Object key = it.next();
/*      */         ObjectQueue pool = (ObjectQueue)this._poolMap.get(key);
/*      */         List objects = new ArrayList();
/*      */         objects.addAll(pool.queue);
/*      */         toDestroy.put(key, objects);
/*      */         it.remove();
/*      */         this._poolList.remove(key);
/*      */         this._totalIdle -= pool.queue.size();
/*      */         this._totalInternalProcessing += pool.queue.size();
/*      */         pool.queue.clear();
/*      */       } 
/*      */     } 
/*      */     destroy(toDestroy);
/*      */   }
/*      */   
/*      */   public void clearOldest() {
/*      */     Map toDestroy = new HashMap();
/*      */     Map map = new TreeMap();
/*      */     synchronized (this) {
/*      */       for (Iterator keyiter = this._poolMap.keySet().iterator(); keyiter.hasNext(); ) {
/*      */         Object key = keyiter.next();
/*      */         CursorableLinkedList list = ((ObjectQueue)this._poolMap.get(key)).queue;
/*      */         for (Iterator it = list.iterator(); it.hasNext();)
/*      */           map.put(it.next(), key); 
/*      */       } 
/*      */       Set setPairKeys = map.entrySet();
/*      */       int itemsToRemove = (int)(map.size() * 0.15D) + 1;
/*      */       Iterator iter = setPairKeys.iterator();
/*      */       while (iter.hasNext() && itemsToRemove > 0) {
/*      */         Map.Entry entry = iter.next();
/*      */         Object key = entry.getValue();
/*      */         ObjectTimestampPair pairTimeStamp = (ObjectTimestampPair)entry.getKey();
/*      */         CursorableLinkedList list = ((ObjectQueue)this._poolMap.get(key)).queue;
/*      */         list.remove(pairTimeStamp);
/*      */         if (toDestroy.containsKey(key)) {
/*      */           ((List)toDestroy.get(key)).add(pairTimeStamp);
/*      */         } else {
/*      */           List listForKey = new ArrayList();
/*      */           listForKey.add(pairTimeStamp);
/*      */           toDestroy.put(key, listForKey);
/*      */         } 
/*      */         if (list.isEmpty()) {
/*      */           this._poolMap.remove(key);
/*      */           this._poolList.remove(key);
/*      */         } 
/*      */         this._totalIdle--;
/*      */         this._totalInternalProcessing++;
/*      */         itemsToRemove--;
/*      */       } 
/*      */     } 
/*      */     destroy(toDestroy);
/*      */   }
/*      */   
/*      */   public void clear(Object key) {
/*      */     Map toDestroy = new HashMap();
/*      */     synchronized (this) {
/*      */       ObjectQueue pool = (ObjectQueue)this._poolMap.remove(key);
/*      */       if (pool == null)
/*      */         return; 
/*      */       this._poolList.remove(key);
/*      */       List objects = new ArrayList();
/*      */       objects.addAll(pool.queue);
/*      */       toDestroy.put(key, objects);
/*      */       this._totalIdle -= pool.queue.size();
/*      */       this._totalInternalProcessing += pool.queue.size();
/*      */       pool.queue.clear();
/*      */     } 
/*      */     destroy(toDestroy);
/*      */   }
/*      */   
/*      */   private void destroy(Map m) {
/*      */     for (Iterator keys = m.keySet().iterator(); keys.hasNext(); ) {
/*      */       Object key = keys.next();
/*      */       Collection c = (Collection)m.get(key);
/*      */       for (Iterator it = c.iterator(); it.hasNext();) {
/*      */         try {
/*      */           this._factory.destroyObject(key, ((ObjectTimestampPair)it.next()).value);
/*      */         } catch (Exception e) {
/*      */         
/*      */         } finally {
/*      */           synchronized (this) {
/*      */             this._totalInternalProcessing--;
/*      */             allocate();
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public synchronized int getNumActive() {
/*      */     return this._totalActive;
/*      */   }
/*      */   
/*      */   public synchronized int getNumIdle() {
/*      */     return this._totalIdle;
/*      */   }
/*      */   
/*      */   public synchronized int getNumActive(Object key) {
/*      */     ObjectQueue pool = (ObjectQueue)this._poolMap.get(key);
/*      */     return (pool != null) ? pool.activeCount : 0;
/*      */   }
/*      */   
/*      */   public synchronized int getNumIdle(Object key) {
/*      */     ObjectQueue pool = (ObjectQueue)this._poolMap.get(key);
/*      */     return (pool != null) ? pool.queue.size() : 0;
/*      */   }
/*      */   
/*      */   public void returnObject(Object key, Object obj) throws Exception {
/*      */     try {
/*      */       addObjectToPool(key, obj, true);
/*      */     } catch (Exception e) {
/*      */       if (this._factory != null) {
/*      */         try {
/*      */           this._factory.destroyObject(key, obj);
/*      */         } catch (Exception e2) {}
/*      */         ObjectQueue pool = (ObjectQueue)this._poolMap.get(key);
/*      */         if (pool != null)
/*      */           synchronized (this) {
/*      */             pool.decrementActiveCount();
/*      */             allocate();
/*      */           }  
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void addObjectToPool(Object key, Object obj, boolean decrementNumActive) throws Exception {
/*      */     ObjectQueue pool;
/*      */     boolean success = true;
/*      */     if (this._testOnReturn && !this._factory.validateObject(key, obj)) {
/*      */       success = false;
/*      */     } else {
/*      */       this._factory.passivateObject(key, obj);
/*      */     } 
/*      */     boolean shouldDestroy = !success;
/*      */     synchronized (this) {
/*      */       pool = (ObjectQueue)this._poolMap.get(key);
/*      */       if (null == pool) {
/*      */         pool = new ObjectQueue();
/*      */         this._poolMap.put(key, pool);
/*      */         this._poolList.add(key);
/*      */       } 
/*      */       if (isClosed()) {
/*      */         shouldDestroy = true;
/*      */       } else if (this._maxIdle >= 0 && pool.queue.size() >= this._maxIdle) {
/*      */         shouldDestroy = true;
/*      */       } else if (success) {
/*      */         if (this._lifo) {
/*      */           pool.queue.addFirst(new ObjectTimestampPair(obj));
/*      */         } else {
/*      */           pool.queue.addLast(new ObjectTimestampPair(obj));
/*      */         } 
/*      */         this._totalIdle++;
/*      */         if (decrementNumActive)
/*      */           pool.decrementActiveCount(); 
/*      */         allocate();
/*      */       } 
/*      */     } 
/*      */     if (shouldDestroy) {
/*      */       try {
/*      */         this._factory.destroyObject(key, obj);
/*      */       } catch (Exception e) {}
/*      */       if (decrementNumActive)
/*      */         synchronized (this) {
/*      */           pool.decrementActiveCount();
/*      */           allocate();
/*      */         }  
/*      */     } 
/*      */   }
/*      */   
/*      */   public void invalidateObject(Object key, Object obj) throws Exception {
/*      */     try {
/*      */       this._factory.destroyObject(key, obj);
/*      */     } finally {
/*      */       synchronized (this) {
/*      */         ObjectQueue pool = (ObjectQueue)this._poolMap.get(key);
/*      */         if (null == pool) {
/*      */           pool = new ObjectQueue();
/*      */           this._poolMap.put(key, pool);
/*      */           this._poolList.add(key);
/*      */         } 
/*      */         pool.decrementActiveCount();
/*      */         allocate();
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public void addObject(Object key) throws Exception {
/*      */     assertOpen();
/*      */     if (this._factory == null)
/*      */       throw new IllegalStateException("Cannot add objects without a factory."); 
/*      */     Object obj = this._factory.makeObject(key);
/*      */     try {
/*      */       assertOpen();
/*      */       addObjectToPool(key, obj, false);
/*      */     } catch (IllegalStateException ex) {
/*      */       try {
/*      */         this._factory.destroyObject(key, obj);
/*      */       } catch (Exception ex2) {}
/*      */       throw ex;
/*      */     } 
/*      */   }
/*      */   
/*      */   public synchronized void preparePool(Object key, boolean populateImmediately) {
/*      */     ObjectQueue pool = (ObjectQueue)this._poolMap.get(key);
/*      */     if (null == pool) {
/*      */       pool = new ObjectQueue();
/*      */       this._poolMap.put(key, pool);
/*      */       this._poolList.add(key);
/*      */     } 
/*      */     if (populateImmediately)
/*      */       try {
/*      */         ensureMinIdle(key);
/*      */       } catch (Exception e) {} 
/*      */   }
/*      */   
/*      */   public void close() throws Exception {
/*      */     super.close();
/*      */     synchronized (this) {
/*      */       clear();
/*      */       if (null != this._evictionCursor) {
/*      */         this._evictionCursor.close();
/*      */         this._evictionCursor = null;
/*      */       } 
/*      */       if (null != this._evictionKeyCursor) {
/*      */         this._evictionKeyCursor.close();
/*      */         this._evictionKeyCursor = null;
/*      */       } 
/*      */       startEvictor(-1L);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void setFactory(KeyedPoolableObjectFactory factory) throws IllegalStateException {
/*      */     Map toDestroy = new HashMap();
/*      */     synchronized (this) {
/*      */       assertOpen();
/*      */       if (0 < getNumActive())
/*      */         throw new IllegalStateException("Objects are already active"); 
/*      */       for (Iterator it = this._poolMap.keySet().iterator(); it.hasNext(); ) {
/*      */         Object key = it.next();
/*      */         ObjectQueue pool = (ObjectQueue)this._poolMap.get(key);
/*      */         if (pool != null) {
/*      */           List objects = new ArrayList();
/*      */           objects.addAll(pool.queue);
/*      */           toDestroy.put(key, objects);
/*      */           it.remove();
/*      */           this._poolList.remove(key);
/*      */           this._totalIdle -= pool.queue.size();
/*      */           this._totalInternalProcessing += pool.queue.size();
/*      */           pool.queue.clear();
/*      */         } 
/*      */       } 
/*      */       this._factory = factory;
/*      */     } 
/*      */     destroy(toDestroy);
/*      */   }
/*      */   
/*      */   public void evict() throws Exception {
/*      */     boolean testWhileIdle;
/*      */     long minEvictableIdleTimeMillis;
/*      */     Object key = null;
/*      */     synchronized (this) {
/*      */       testWhileIdle = this._testWhileIdle;
/*      */       minEvictableIdleTimeMillis = this._minEvictableIdleTimeMillis;
/*      */       if (this._evictionKeyCursor != null && this._evictionKeyCursor._lastReturned != null)
/*      */         key = this._evictionKeyCursor._lastReturned.value(); 
/*      */     } 
/*      */     for (int i = 0, m = getNumTests(); i < m; i++) {
/*      */       GenericKeyedObjectPool genericKeyedObjectPool;
/*      */       boolean removeObject;
/*      */       synchronized (this) {
/*      */         if (this._poolMap == null || this._poolMap.size() == 0) {
/*      */         
/*      */         } else {
/*      */           if (null == this._evictionKeyCursor) {
/*      */             resetEvictionKeyCursor();
/*      */             key = null;
/*      */           } 
/*      */           if (null == this._evictionCursor)
/*      */             if (this._evictionKeyCursor.hasNext()) {
/*      */               key = this._evictionKeyCursor.next();
/*      */               resetEvictionObjectCursor(key);
/*      */             } else {
/*      */               resetEvictionKeyCursor();
/*      */               if (this._evictionKeyCursor != null && this._evictionKeyCursor.hasNext()) {
/*      */                 key = this._evictionKeyCursor.next();
/*      */                 resetEvictionObjectCursor(key);
/*      */               } 
/*      */             }  
/*      */           if (this._evictionCursor == null) {
/*      */           
/*      */           } else {
/*      */             if ((this._lifo && !this._evictionCursor.hasPrevious()) || (!this._lifo && !this._evictionCursor.hasNext()))
/*      */               if (this._evictionKeyCursor != null)
/*      */                 if (this._evictionKeyCursor.hasNext()) {
/*      */                   key = this._evictionKeyCursor.next();
/*      */                   resetEvictionObjectCursor(key);
/*      */                 } else {
/*      */                   resetEvictionKeyCursor();
/*      */                   if (this._evictionKeyCursor != null && this._evictionKeyCursor.hasNext()) {
/*      */                     key = this._evictionKeyCursor.next();
/*      */                     resetEvictionObjectCursor(key);
/*      */                   } 
/*      */                 }   
/*      */             if ((this._lifo && !this._evictionCursor.hasPrevious()) || (!this._lifo && !this._evictionCursor.hasNext())) {
/*      */             
/*      */             } else {
/*      */               ObjectTimestampPair pair = this._lifo ? (ObjectTimestampPair)this._evictionCursor.previous() : (ObjectTimestampPair)this._evictionCursor.next();
/*      */               this._evictionCursor.remove();
/*      */               this._totalIdle--;
/*      */               this._totalInternalProcessing++;
/*      */               removeObject = false;
/*      */               if (minEvictableIdleTimeMillis > 0L && System.currentTimeMillis() - pair.tstamp > minEvictableIdleTimeMillis)
/*      */                 removeObject = true; 
/*      */               if (testWhileIdle && !removeObject) {
/*      */                 boolean active = false;
/*      */                 try {
/*      */                   this._factory.activateObject(key, pair.value);
/*      */                   active = true;
/*      */                 } catch (Exception e) {
/*      */                   removeObject = true;
/*      */                 } 
/*      */                 if (active)
/*      */                   if (!this._factory.validateObject(key, pair.value)) {
/*      */                     removeObject = true;
/*      */                   } else {
/*      */                     try {
/*      */                       this._factory.passivateObject(key, pair.value);
/*      */                     } catch (Exception e) {
/*      */                       removeObject = true;
/*      */                     } 
/*      */                   }  
/*      */               } 
/*      */               if (removeObject)
/*      */                 try {
/*      */                   this._factory.destroyObject(key, pair.value);
/*      */                 } catch (Exception e) {
/*      */                 
/*      */                 } finally {
/*      */                   if (this._minIdle == 0)
/*      */                     synchronized (this) {
/*      */                       ObjectQueue objectQueue = (ObjectQueue)this._poolMap.get(key);
/*      */                       if (objectQueue != null && objectQueue.queue.isEmpty()) {
/*      */                         this._poolMap.remove(key);
/*      */                         this._poolList.remove(key);
/*      */                       } 
/*      */                     }  
/*      */                 }  
/*      */               synchronized (this) {
/*      */                 if (!removeObject) {
/*      */                   this._evictionCursor.add(pair);
/*      */                   this._totalIdle++;
/*      */                   if (this._lifo)
/*      */                     this._evictionCursor.previous(); 
/*      */                 } 
/*      */                 this._totalInternalProcessing--;
/*      */               } 
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void resetEvictionKeyCursor() {
/*      */     if (this._evictionKeyCursor != null)
/*      */       this._evictionKeyCursor.close(); 
/*      */     this._evictionKeyCursor = this._poolList.cursor();
/*      */     if (null != this._evictionCursor) {
/*      */       this._evictionCursor.close();
/*      */       this._evictionCursor = null;
/*      */     } 
/*      */   }
/*      */   
/*      */   private void resetEvictionObjectCursor(Object key) {
/*      */     if (this._evictionCursor != null)
/*      */       this._evictionCursor.close(); 
/*      */     if (this._poolMap == null)
/*      */       return; 
/*      */     ObjectQueue pool = (ObjectQueue)this._poolMap.get(key);
/*      */     if (pool != null) {
/*      */       CursorableLinkedList queue = pool.queue;
/*      */       this._evictionCursor = queue.cursor(this._lifo ? queue.size() : 0);
/*      */     } 
/*      */   }
/*      */   
/*      */   private void ensureMinIdle() throws Exception {
/*      */     if (this._minIdle > 0) {
/*      */       Object[] keysCopy;
/*      */       synchronized (this) {
/*      */         keysCopy = this._poolMap.keySet().toArray();
/*      */       } 
/*      */       for (int i = 0; i < keysCopy.length; i++)
/*      */         ensureMinIdle(keysCopy[i]); 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void ensureMinIdle(Object key) throws Exception {
/*      */     ObjectQueue pool;
/*      */     synchronized (this) {
/*      */       pool = (ObjectQueue)this._poolMap.get(key);
/*      */     } 
/*      */     if (pool == null)
/*      */       return; 
/*      */     int objectDeficit = calculateDeficit(pool, false);
/*      */     for (int i = 0; i < objectDeficit && calculateDeficit(pool, true) > 0; i++) {
/*      */       try {
/*      */         addObject(key);
/*      */         synchronized (this) {
/*      */           pool.decrementInternalProcessingCount();
/*      */           allocate();
/*      */         } 
/*      */       } finally {
/*      */         synchronized (this) {
/*      */           pool.decrementInternalProcessingCount();
/*      */           allocate();
/*      */         } 
/*      */       } 
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
/*      */     Iterator it = this._poolMap.keySet().iterator();
/*      */     while (it.hasNext()) {
/*      */       Object key = it.next();
/*      */       buf.append("\t").append(key).append(" ").append(this._poolMap.get(key)).append("\n");
/*      */     } 
/*      */     return buf.toString();
/*      */   }
/*      */   
/*      */   private synchronized int getNumTests() {
/*      */     if (this._numTestsPerEvictionRun >= 0)
/*      */       return Math.min(this._numTestsPerEvictionRun, this._totalIdle); 
/*      */     return (int)Math.ceil(this._totalIdle / Math.abs(this._numTestsPerEvictionRun));
/*      */   }
/*      */   
/*      */   private synchronized int calculateDeficit(ObjectQueue pool, boolean incrementInternal) {
/*      */     int objectDefecit = 0;
/*      */     objectDefecit = getMinIdle() - pool.queue.size();
/*      */     if (getMaxActive() > 0) {
/*      */       int growLimit = Math.max(0, getMaxActive() - pool.activeCount - pool.queue.size() - pool.internalProcessingCount);
/*      */       objectDefecit = Math.min(objectDefecit, growLimit);
/*      */     } 
/*      */     if (getMaxTotal() > 0) {
/*      */       int growLimit = Math.max(0, getMaxTotal() - getNumActive() - getNumIdle() - this._totalInternalProcessing);
/*      */       objectDefecit = Math.min(objectDefecit, growLimit);
/*      */     } 
/*      */     if (incrementInternal && objectDefecit > 0)
/*      */       pool.incrementInternalProcessingCount(); 
/*      */     return objectDefecit;
/*      */   }
/*      */   
/*      */   private class ObjectQueue {
/*      */     private int activeCount;
/*      */     
/*      */     private final CursorableLinkedList queue;
/*      */     
/*      */     private int internalProcessingCount;
/*      */     
/*      */     private final GenericKeyedObjectPool this$0;
/*      */     
/*      */     private ObjectQueue(GenericKeyedObjectPool this$0) {
/*      */       GenericKeyedObjectPool.this = GenericKeyedObjectPool.this;
/*      */       this.activeCount = 0;
/*      */       this.queue = new CursorableLinkedList();
/*      */       this.internalProcessingCount = 0;
/*      */     }
/*      */     
/*      */     void incrementActiveCount() {
/*      */       synchronized (GenericKeyedObjectPool.this) {
/*      */         GenericKeyedObjectPool.this._totalActive++;
/*      */       } 
/*      */       this.activeCount++;
/*      */     }
/*      */     
/*      */     void decrementActiveCount() {
/*      */       synchronized (GenericKeyedObjectPool.this) {
/*      */         GenericKeyedObjectPool.this._totalActive--;
/*      */       } 
/*      */       if (this.activeCount > 0)
/*      */         this.activeCount--; 
/*      */     }
/*      */     
/*      */     void incrementInternalProcessingCount() {
/*      */       synchronized (GenericKeyedObjectPool.this) {
/*      */         GenericKeyedObjectPool.this._totalInternalProcessing++;
/*      */       } 
/*      */       this.internalProcessingCount++;
/*      */     }
/*      */     
/*      */     void decrementInternalProcessingCount() {
/*      */       synchronized (GenericKeyedObjectPool.this) {
/*      */         GenericKeyedObjectPool.this._totalInternalProcessing--;
/*      */       } 
/*      */       this.internalProcessingCount--;
/*      */     }
/*      */   }
/*      */   
/*      */   static class ObjectTimestampPair implements Comparable {
/*      */     Object value;
/*      */     
/*      */     long tstamp;
/*      */     
/*      */     ObjectTimestampPair(Object val) {
/*      */       this(val, System.currentTimeMillis());
/*      */     }
/*      */     
/*      */     ObjectTimestampPair(Object val, long time) {
/*      */       this.value = val;
/*      */       this.tstamp = time;
/*      */     }
/*      */     
/*      */     public String toString() {
/*      */       return this.value + ";" + this.tstamp;
/*      */     }
/*      */     
/*      */     public int compareTo(Object obj) {
/*      */       return compareTo((ObjectTimestampPair)obj);
/*      */     }
/*      */     
/*      */     public int compareTo(ObjectTimestampPair other) {
/*      */       long tstampdiff = this.tstamp - other.tstamp;
/*      */       if (tstampdiff == 0L)
/*      */         return System.identityHashCode(this) - System.identityHashCode(other); 
/*      */       return (int)Math.min(Math.max(tstampdiff, -2147483648L), 2147483647L);
/*      */     }
/*      */   }
/*      */   
/*      */   private class Evictor extends TimerTask {
/*      */     private final GenericKeyedObjectPool this$0;
/*      */     
/*      */     private Evictor(GenericKeyedObjectPool this$0) {
/*      */       GenericKeyedObjectPool.this = GenericKeyedObjectPool.this;
/*      */     }
/*      */     
/*      */     public void run() {
/*      */       try {
/*      */         GenericKeyedObjectPool.this.evict();
/*      */       } catch (Exception e) {
/*      */       
/*      */       } catch (OutOfMemoryError oome) {
/*      */         oome.printStackTrace(System.err);
/*      */       } 
/*      */       try {
/*      */         GenericKeyedObjectPool.this.ensureMinIdle();
/*      */       } catch (Exception e) {}
/*      */     }
/*      */   }
/*      */   
/*      */   public static class Config {
/*      */     public int maxIdle = 8;
/*      */     
/*      */     public int maxActive = 8;
/*      */     
/*      */     public int maxTotal = -1;
/*      */     
/*      */     public int minIdle = 0;
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
/*      */     public boolean lifo = true;
/*      */   }
/*      */   
/*      */   private static final class Latch {
/*      */     private final Object _key;
/*      */     
/*      */     private GenericKeyedObjectPool.ObjectQueue _pool;
/*      */     
/*      */     private GenericKeyedObjectPool.ObjectTimestampPair _pair;
/*      */     
/*      */     private boolean _mayCreate = false;
/*      */     
/*      */     private Latch(Object key) {
/*      */       this._key = key;
/*      */     }
/*      */     
/*      */     private synchronized Object getkey() {
/*      */       return this._key;
/*      */     }
/*      */     
/*      */     private synchronized GenericKeyedObjectPool.ObjectQueue getPool() {
/*      */       return this._pool;
/*      */     }
/*      */     
/*      */     private synchronized void setPool(GenericKeyedObjectPool.ObjectQueue pool) {
/*      */       this._pool = pool;
/*      */     }
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
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\pool\impl\GenericKeyedObjectPool.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */