/*     */ package org.apache.commons.pool.impl;
/*     */ 
/*     */ import java.lang.ref.Reference;
/*     */ import java.lang.ref.ReferenceQueue;
/*     */ import java.lang.ref.SoftReference;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.NoSuchElementException;
/*     */ import org.apache.commons.pool.BaseObjectPool;
/*     */ import org.apache.commons.pool.ObjectPool;
/*     */ import org.apache.commons.pool.PoolUtils;
/*     */ import org.apache.commons.pool.PoolableObjectFactory;
/*     */ 
/*     */ public class SoftReferenceObjectPool extends BaseObjectPool implements ObjectPool {
/*     */   private List _pool;
/*     */   
/*     */   private PoolableObjectFactory _factory;
/*     */   
/*     */   private final ReferenceQueue refQueue;
/*     */   
/*     */   private int _numActive;
/*     */   
/*     */   public SoftReferenceObjectPool() {
/* 275 */     this._pool = null;
/* 278 */     this._factory = null;
/* 285 */     this.refQueue = new ReferenceQueue();
/* 288 */     this._numActive = 0;
/*     */     this._pool = new ArrayList();
/*     */     this._factory = null;
/*     */   }
/*     */   
/*     */   public SoftReferenceObjectPool(PoolableObjectFactory factory) {
/*     */     this._pool = null;
/*     */     this._factory = null;
/*     */     this.refQueue = new ReferenceQueue();
/* 288 */     this._numActive = 0;
/*     */     this._pool = new ArrayList();
/*     */     this._factory = factory;
/*     */   }
/*     */   
/*     */   public SoftReferenceObjectPool(PoolableObjectFactory factory, int initSize) throws Exception, IllegalArgumentException {
/*     */     this._pool = null;
/*     */     this._factory = null;
/*     */     this.refQueue = new ReferenceQueue();
/* 288 */     this._numActive = 0;
/*     */     if (factory == null)
/*     */       throw new IllegalArgumentException("factory required to prefill the pool."); 
/*     */     this._pool = new ArrayList(initSize);
/*     */     this._factory = factory;
/*     */     PoolUtils.prefill(this, initSize);
/*     */   }
/*     */   
/*     */   public synchronized Object borrowObject() throws Exception {
/*     */     assertOpen();
/*     */     Object obj = null;
/*     */     boolean newlyCreated = false;
/*     */     while (null == obj) {
/*     */       if (this._pool.isEmpty()) {
/*     */         if (null == this._factory)
/*     */           throw new NoSuchElementException(); 
/*     */         newlyCreated = true;
/*     */         obj = this._factory.makeObject();
/*     */       } else {
/*     */         SoftReference ref = this._pool.remove(this._pool.size() - 1);
/*     */         obj = ref.get();
/*     */         ref.clear();
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
/*     */     if (this._factory != null)
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
/*     */     if (success)
/*     */       this._pool.add(new SoftReference(obj, this.refQueue)); 
/*     */     notifyAll();
/*     */     if (shouldDestroy && this._factory != null)
/*     */       try {
/*     */         this._factory.destroyObject(obj);
/*     */       } catch (Exception e) {} 
/*     */   }
/*     */   
/*     */   public synchronized void invalidateObject(Object obj) throws Exception {
/*     */     this._numActive--;
/*     */     if (this._factory != null)
/*     */       this._factory.destroyObject(obj); 
/*     */     notifyAll();
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
/*     */       this._pool.add(new SoftReference(obj, this.refQueue));
/*     */       notifyAll();
/*     */     } 
/*     */     if (shouldDestroy)
/*     */       try {
/*     */         this._factory.destroyObject(obj);
/*     */       } catch (Exception e) {} 
/*     */   }
/*     */   
/*     */   public synchronized int getNumIdle() {
/*     */     pruneClearedReferences();
/*     */     return this._pool.size();
/*     */   }
/*     */   
/*     */   public synchronized int getNumActive() {
/*     */     return this._numActive;
/*     */   }
/*     */   
/*     */   public synchronized void clear() {
/*     */     if (null != this._factory) {
/*     */       Iterator iter = this._pool.iterator();
/*     */       while (iter.hasNext()) {
/*     */         try {
/*     */           Object obj = ((SoftReference)iter.next()).get();
/*     */           if (null != obj)
/*     */             this._factory.destroyObject(obj); 
/*     */         } catch (Exception e) {}
/*     */       } 
/*     */     } 
/*     */     this._pool.clear();
/*     */     pruneClearedReferences();
/*     */   }
/*     */   
/*     */   public void close() throws Exception {
/*     */     super.close();
/*     */     clear();
/*     */   }
/*     */   
/*     */   public synchronized void setFactory(PoolableObjectFactory factory) throws IllegalStateException {
/*     */     assertOpen();
/*     */     if (0 < getNumActive())
/*     */       throw new IllegalStateException("Objects are already active"); 
/*     */     clear();
/*     */     this._factory = factory;
/*     */   }
/*     */   
/*     */   private void pruneClearedReferences() {
/*     */     Reference ref;
/*     */     while ((ref = this.refQueue.poll()) != null) {
/*     */       try {
/*     */         this._pool.remove(ref);
/*     */       } catch (UnsupportedOperationException uoe) {}
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\pool\impl\SoftReferenceObjectPool.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */