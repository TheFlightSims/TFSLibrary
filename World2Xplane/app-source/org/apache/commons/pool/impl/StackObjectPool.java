/*     */ package org.apache.commons.pool.impl;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.NoSuchElementException;
/*     */ import java.util.Stack;
/*     */ import org.apache.commons.pool.BaseObjectPool;
/*     */ import org.apache.commons.pool.ObjectPool;
/*     */ import org.apache.commons.pool.PoolableObjectFactory;
/*     */ 
/*     */ public class StackObjectPool extends BaseObjectPool implements ObjectPool {
/*     */   protected static final int DEFAULT_MAX_SLEEPING = 8;
/*     */   
/*     */   protected static final int DEFAULT_INIT_SLEEPING_CAPACITY = 4;
/*     */   
/*     */   protected Stack _pool;
/*     */   
/*     */   protected PoolableObjectFactory _factory;
/*     */   
/*     */   protected int _maxSleeping;
/*     */   
/*     */   protected int _numActive;
/*     */   
/*     */   public StackObjectPool() {
/*  59 */     this((PoolableObjectFactory)null, 8, 4);
/*     */   }
/*     */   
/*     */   public StackObjectPool(int maxIdle) {
/*  75 */     this((PoolableObjectFactory)null, maxIdle, 4);
/*     */   }
/*     */   
/*     */   public StackObjectPool(int maxIdle, int initIdleCapacity) {
/*  93 */     this((PoolableObjectFactory)null, maxIdle, initIdleCapacity);
/*     */   }
/*     */   
/*     */   public StackObjectPool(PoolableObjectFactory factory) {
/* 103 */     this(factory, 8, 4);
/*     */   }
/*     */   
/*     */   public StackObjectPool(PoolableObjectFactory factory, int maxIdle) {
/* 115 */     this(factory, maxIdle, 4);
/*     */   }
/*     */   
/*     */   public StackObjectPool(PoolableObjectFactory factory, int maxIdle, int initIdleCapacity) {
/* 349 */     this._pool = null;
/* 352 */     this._factory = null;
/* 355 */     this._maxSleeping = 8;
/* 358 */     this._numActive = 0;
/*     */     this._factory = factory;
/*     */     this._maxSleeping = (maxIdle < 0) ? 8 : maxIdle;
/*     */     int initcapacity = (initIdleCapacity < 1) ? 4 : initIdleCapacity;
/*     */     this._pool = new Stack();
/*     */     this._pool.ensureCapacity((initcapacity > this._maxSleeping) ? this._maxSleeping : initcapacity);
/*     */   }
/*     */   
/*     */   public synchronized Object borrowObject() throws Exception {
/*     */     assertOpen();
/*     */     Object obj = null;
/*     */     boolean newlyCreated = false;
/*     */     while (null == obj) {
/*     */       if (!this._pool.empty()) {
/*     */         obj = this._pool.pop();
/*     */       } else {
/*     */         if (null == this._factory)
/*     */           throw new NoSuchElementException(); 
/*     */         obj = this._factory.makeObject();
/*     */         newlyCreated = true;
/*     */         if (obj == null)
/*     */           throw new NoSuchElementException("PoolableObjectFactory.makeObject() returned null."); 
/*     */       } 
/*     */       if (null != this._factory && null != obj)
/*     */         try {
/*     */           this._factory.activateObject(obj);
/*     */           if (!this._factory.validateObject(obj))
/*     */             throw new Exception("ValidateObject failed"); 
/*     */         } catch (Throwable t) {
/*     */           try {
/*     */             this._factory.destroyObject(obj);
/*     */           } catch (Throwable t2) {
/*     */           
/*     */           } finally {
/*     */             obj = null;
/*     */           } 
/*     */           if (newlyCreated)
/*     */             throw new NoSuchElementException("Could not create a validated object, cause: " + t.getMessage()); 
/*     */         }  
/*     */     } 
/*     */     this._numActive++;
/*     */     return obj;
/*     */   }
/*     */   
/*     */   public synchronized void returnObject(Object obj) throws Exception {
/*     */     boolean success = !isClosed();
/*     */     if (null != this._factory)
/*     */       if (!this._factory.validateObject(obj)) {
/*     */         success = false;
/*     */       } else {
/*     */         try {
/*     */           this._factory.passivateObject(obj);
/*     */         } catch (Exception e) {
/*     */           success = false;
/*     */         } 
/*     */       }  
/*     */     boolean shouldDestroy = !success;
/*     */     this._numActive--;
/*     */     if (success) {
/*     */       Object toBeDestroyed = null;
/*     */       if (this._pool.size() >= this._maxSleeping) {
/*     */         shouldDestroy = true;
/*     */         toBeDestroyed = this._pool.remove(0);
/*     */       } 
/*     */       this._pool.push(obj);
/*     */       obj = toBeDestroyed;
/*     */     } 
/*     */     notifyAll();
/*     */     if (shouldDestroy)
/*     */       try {
/*     */         this._factory.destroyObject(obj);
/*     */       } catch (Exception e) {} 
/*     */   }
/*     */   
/*     */   public synchronized void invalidateObject(Object obj) throws Exception {
/*     */     this._numActive--;
/*     */     if (null != this._factory)
/*     */       this._factory.destroyObject(obj); 
/*     */     notifyAll();
/*     */   }
/*     */   
/*     */   public synchronized int getNumIdle() {
/*     */     return this._pool.size();
/*     */   }
/*     */   
/*     */   public synchronized int getNumActive() {
/*     */     return this._numActive;
/*     */   }
/*     */   
/*     */   public synchronized void clear() {
/*     */     if (null != this._factory) {
/*     */       Iterator it = this._pool.iterator();
/*     */       while (it.hasNext()) {
/*     */         try {
/*     */           this._factory.destroyObject(it.next());
/*     */         } catch (Exception e) {}
/*     */       } 
/*     */     } 
/*     */     this._pool.clear();
/*     */   }
/*     */   
/*     */   public void close() throws Exception {
/*     */     super.close();
/*     */     clear();
/*     */   }
/*     */   
/*     */   public synchronized void addObject() throws Exception {
/*     */     assertOpen();
/*     */     if (this._factory == null)
/*     */       throw new IllegalStateException("Cannot add objects without a factory."); 
/*     */     Object obj = this._factory.makeObject();
/*     */     boolean success = true;
/*     */     if (!this._factory.validateObject(obj)) {
/*     */       success = false;
/*     */     } else {
/*     */       this._factory.passivateObject(obj);
/*     */     } 
/*     */     boolean shouldDestroy = !success;
/*     */     if (success) {
/*     */       Object toBeDestroyed = null;
/*     */       if (this._pool.size() >= this._maxSleeping) {
/*     */         shouldDestroy = true;
/*     */         toBeDestroyed = this._pool.remove(0);
/*     */       } 
/*     */       this._pool.push(obj);
/*     */       obj = toBeDestroyed;
/*     */     } 
/*     */     notifyAll();
/*     */     if (shouldDestroy)
/*     */       try {
/*     */         this._factory.destroyObject(obj);
/*     */       } catch (Exception e) {} 
/*     */   }
/*     */   
/*     */   public synchronized void setFactory(PoolableObjectFactory factory) throws IllegalStateException {
/*     */     assertOpen();
/*     */     if (0 < getNumActive())
/*     */       throw new IllegalStateException("Objects are already active"); 
/*     */     clear();
/*     */     this._factory = factory;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\pool\impl\StackObjectPool.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */