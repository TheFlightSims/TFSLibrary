/*     */ package org.apache.commons.pool.impl;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.NoSuchElementException;
/*     */ import java.util.Stack;
/*     */ import org.apache.commons.pool.BaseKeyedObjectPool;
/*     */ import org.apache.commons.pool.KeyedObjectPool;
/*     */ import org.apache.commons.pool.KeyedPoolableObjectFactory;
/*     */ 
/*     */ public class StackKeyedObjectPool extends BaseKeyedObjectPool implements KeyedObjectPool {
/*     */   protected static final int DEFAULT_MAX_SLEEPING = 8;
/*     */   
/*     */   protected static final int DEFAULT_INIT_SLEEPING_CAPACITY = 4;
/*     */   
/*     */   protected HashMap _pools;
/*     */   
/*     */   protected KeyedPoolableObjectFactory _factory;
/*     */   
/*     */   protected int _maxSleeping;
/*     */   
/*     */   protected int _initSleepingCapacity;
/*     */   
/*     */   protected int _totActive;
/*     */   
/*     */   protected int _totIdle;
/*     */   
/*     */   protected HashMap _activeCount;
/*     */   
/*     */   public StackKeyedObjectPool() {
/*  59 */     this((KeyedPoolableObjectFactory)null, 8, 4);
/*     */   }
/*     */   
/*     */   public StackKeyedObjectPool(int max) {
/*  73 */     this((KeyedPoolableObjectFactory)null, max, 4);
/*     */   }
/*     */   
/*     */   public StackKeyedObjectPool(int max, int init) {
/*  89 */     this((KeyedPoolableObjectFactory)null, max, init);
/*     */   }
/*     */   
/*     */   public StackKeyedObjectPool(KeyedPoolableObjectFactory factory) {
/*  99 */     this(factory, 8);
/*     */   }
/*     */   
/*     */   public StackKeyedObjectPool(KeyedPoolableObjectFactory factory, int max) {
/* 111 */     this(factory, max, 4);
/*     */   }
/*     */   
/*     */   public StackKeyedObjectPool(KeyedPoolableObjectFactory factory, int max, int init) {
/* 479 */     this._pools = null;
/* 482 */     this._factory = null;
/* 485 */     this._maxSleeping = 8;
/* 488 */     this._initSleepingCapacity = 4;
/* 491 */     this._totActive = 0;
/* 494 */     this._totIdle = 0;
/* 497 */     this._activeCount = null;
/*     */     this._factory = factory;
/*     */     this._maxSleeping = (max < 0) ? 8 : max;
/*     */     this._initSleepingCapacity = (init < 1) ? 4 : init;
/*     */     this._pools = new HashMap();
/*     */     this._activeCount = new HashMap();
/*     */   }
/*     */   
/*     */   public synchronized Object borrowObject(Object key) throws Exception {
/*     */     assertOpen();
/*     */     Stack stack = (Stack)this._pools.get(key);
/*     */     if (null == stack) {
/*     */       stack = new Stack();
/*     */       stack.ensureCapacity((this._initSleepingCapacity > this._maxSleeping) ? this._maxSleeping : this._initSleepingCapacity);
/*     */       this._pools.put(key, stack);
/*     */     } 
/*     */     Object obj = null;
/*     */     while (true) {
/*     */       boolean newlyMade = false;
/*     */       if (!stack.empty()) {
/*     */         obj = stack.pop();
/*     */         this._totIdle--;
/*     */       } else {
/*     */         if (null == this._factory)
/*     */           throw new NoSuchElementException("pools without a factory cannot create new objects as needed."); 
/*     */         obj = this._factory.makeObject(key);
/*     */         newlyMade = true;
/*     */       } 
/*     */       if (null != this._factory && null != obj)
/*     */         try {
/*     */           this._factory.activateObject(key, obj);
/*     */           if (!this._factory.validateObject(key, obj))
/*     */             throw new Exception("ValidateObject failed"); 
/*     */         } catch (Throwable t) {
/*     */           try {
/*     */             this._factory.destroyObject(key, obj);
/*     */           } catch (Throwable t2) {
/*     */           
/*     */           } finally {
/*     */             obj = null;
/*     */           } 
/*     */           if (newlyMade)
/*     */             throw new NoSuchElementException("Could not create a validated object, cause: " + t.getMessage()); 
/*     */         }  
/*     */       if (obj != null) {
/*     */         incrementActiveCount(key);
/*     */         return obj;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public synchronized void returnObject(Object key, Object obj) throws Exception {
/*     */     decrementActiveCount(key);
/*     */     if (null != this._factory)
/*     */       if (this._factory.validateObject(key, obj)) {
/*     */         try {
/*     */           this._factory.passivateObject(key, obj);
/*     */         } catch (Exception ex) {
/*     */           this._factory.destroyObject(key, obj);
/*     */           return;
/*     */         } 
/*     */       } else {
/*     */         return;
/*     */       }  
/*     */     if (isClosed()) {
/*     */       if (null != this._factory)
/*     */         try {
/*     */           this._factory.destroyObject(key, obj);
/*     */         } catch (Exception e) {} 
/*     */       return;
/*     */     } 
/*     */     Stack stack = (Stack)this._pools.get(key);
/*     */     if (null == stack) {
/*     */       stack = new Stack();
/*     */       stack.ensureCapacity((this._initSleepingCapacity > this._maxSleeping) ? this._maxSleeping : this._initSleepingCapacity);
/*     */       this._pools.put(key, stack);
/*     */     } 
/*     */     int stackSize = stack.size();
/*     */     if (stackSize >= this._maxSleeping) {
/*     */       Object staleObj;
/*     */       if (stackSize > 0) {
/*     */         staleObj = stack.remove(0);
/*     */         this._totIdle--;
/*     */       } else {
/*     */         staleObj = obj;
/*     */       } 
/*     */       if (null != this._factory)
/*     */         try {
/*     */           this._factory.destroyObject(key, staleObj);
/*     */         } catch (Exception e) {} 
/*     */     } 
/*     */     stack.push(obj);
/*     */     this._totIdle++;
/*     */   }
/*     */   
/*     */   public synchronized void invalidateObject(Object key, Object obj) throws Exception {
/*     */     decrementActiveCount(key);
/*     */     if (null != this._factory)
/*     */       this._factory.destroyObject(key, obj); 
/*     */     notifyAll();
/*     */   }
/*     */   
/*     */   public synchronized void addObject(Object key) throws Exception {
/*     */     assertOpen();
/*     */     if (this._factory == null)
/*     */       throw new IllegalStateException("Cannot add objects without a factory."); 
/*     */     Object obj = this._factory.makeObject(key);
/*     */     try {
/*     */       if (!this._factory.validateObject(key, obj))
/*     */         return; 
/*     */     } catch (Exception e) {
/*     */       try {
/*     */         this._factory.destroyObject(key, obj);
/*     */       } catch (Exception e2) {}
/*     */       return;
/*     */     } 
/*     */     this._factory.passivateObject(key, obj);
/*     */     Stack stack = (Stack)this._pools.get(key);
/*     */     if (null == stack) {
/*     */       stack = new Stack();
/*     */       stack.ensureCapacity((this._initSleepingCapacity > this._maxSleeping) ? this._maxSleeping : this._initSleepingCapacity);
/*     */       this._pools.put(key, stack);
/*     */     } 
/*     */     int stackSize = stack.size();
/*     */     if (stackSize >= this._maxSleeping) {
/*     */       Object staleObj;
/*     */       if (stackSize > 0) {
/*     */         staleObj = stack.remove(0);
/*     */         this._totIdle--;
/*     */       } else {
/*     */         staleObj = obj;
/*     */       } 
/*     */       try {
/*     */         this._factory.destroyObject(key, staleObj);
/*     */       } catch (Exception e) {
/*     */         if (obj == staleObj)
/*     */           throw e; 
/*     */       } 
/*     */     } else {
/*     */       stack.push(obj);
/*     */       this._totIdle++;
/*     */     } 
/*     */   }
/*     */   
/*     */   public synchronized int getNumIdle() {
/*     */     return this._totIdle;
/*     */   }
/*     */   
/*     */   public synchronized int getNumActive() {
/*     */     return this._totActive;
/*     */   }
/*     */   
/*     */   public synchronized int getNumActive(Object key) {
/*     */     return getActiveCount(key);
/*     */   }
/*     */   
/*     */   public synchronized int getNumIdle(Object key) {
/*     */     try {
/*     */       return ((Stack)this._pools.get(key)).size();
/*     */     } catch (Exception e) {
/*     */       return 0;
/*     */     } 
/*     */   }
/*     */   
/*     */   public synchronized void clear() {
/*     */     Iterator it = this._pools.keySet().iterator();
/*     */     while (it.hasNext()) {
/*     */       Object key = it.next();
/*     */       Stack stack = (Stack)this._pools.get(key);
/*     */       destroyStack(key, stack);
/*     */     } 
/*     */     this._totIdle = 0;
/*     */     this._pools.clear();
/*     */     this._activeCount.clear();
/*     */   }
/*     */   
/*     */   public synchronized void clear(Object key) {
/*     */     Stack stack = (Stack)this._pools.remove(key);
/*     */     destroyStack(key, stack);
/*     */   }
/*     */   
/*     */   private synchronized void destroyStack(Object key, Stack stack) {
/*     */     if (null == stack)
/*     */       return; 
/*     */     if (null != this._factory) {
/*     */       Iterator it = stack.iterator();
/*     */       while (it.hasNext()) {
/*     */         try {
/*     */           this._factory.destroyObject(key, it.next());
/*     */         } catch (Exception e) {}
/*     */       } 
/*     */     } 
/*     */     this._totIdle -= stack.size();
/*     */     this._activeCount.remove(key);
/*     */     stack.clear();
/*     */   }
/*     */   
/*     */   public synchronized String toString() {
/*     */     StringBuffer buf = new StringBuffer();
/*     */     buf.append(getClass().getName());
/*     */     buf.append(" contains ").append(this._pools.size()).append(" distinct pools: ");
/*     */     Iterator it = this._pools.keySet().iterator();
/*     */     while (it.hasNext()) {
/*     */       Object key = it.next();
/*     */       buf.append(" |").append(key).append("|=");
/*     */       Stack s = (Stack)this._pools.get(key);
/*     */       buf.append(s.size());
/*     */     } 
/*     */     return buf.toString();
/*     */   }
/*     */   
/*     */   public void close() throws Exception {
/*     */     super.close();
/*     */     clear();
/*     */   }
/*     */   
/*     */   public synchronized void setFactory(KeyedPoolableObjectFactory factory) throws IllegalStateException {
/*     */     if (0 < getNumActive())
/*     */       throw new IllegalStateException("Objects are already active"); 
/*     */     clear();
/*     */     this._factory = factory;
/*     */   }
/*     */   
/*     */   private int getActiveCount(Object key) {
/*     */     try {
/*     */       return ((Integer)this._activeCount.get(key)).intValue();
/*     */     } catch (NoSuchElementException e) {
/*     */       return 0;
/*     */     } catch (NullPointerException e) {
/*     */       return 0;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void incrementActiveCount(Object key) {
/*     */     this._totActive++;
/*     */     Integer old = (Integer)this._activeCount.get(key);
/*     */     if (null == old) {
/*     */       this._activeCount.put(key, new Integer(1));
/*     */     } else {
/*     */       this._activeCount.put(key, new Integer(old.intValue() + 1));
/*     */     } 
/*     */   }
/*     */   
/*     */   private void decrementActiveCount(Object key) {
/*     */     this._totActive--;
/*     */     Integer active = (Integer)this._activeCount.get(key);
/*     */     if (null != active)
/*     */       if (active.intValue() <= 1) {
/*     */         this._activeCount.remove(key);
/*     */       } else {
/*     */         this._activeCount.put(key, new Integer(active.intValue() - 1));
/*     */       }  
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\pool\impl\StackKeyedObjectPool.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */