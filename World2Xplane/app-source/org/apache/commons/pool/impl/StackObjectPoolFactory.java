/*     */ package org.apache.commons.pool.impl;
/*     */ 
/*     */ import org.apache.commons.pool.ObjectPool;
/*     */ import org.apache.commons.pool.ObjectPoolFactory;
/*     */ import org.apache.commons.pool.PoolableObjectFactory;
/*     */ 
/*     */ public class StackObjectPoolFactory implements ObjectPoolFactory {
/*     */   protected PoolableObjectFactory _factory;
/*     */   
/*     */   protected int _maxSleeping;
/*     */   
/*     */   protected int _initCapacity;
/*     */   
/*     */   public StackObjectPoolFactory() {
/*  41 */     this((PoolableObjectFactory)null, 8, 4);
/*     */   }
/*     */   
/*     */   public StackObjectPoolFactory(int maxIdle) {
/*  51 */     this((PoolableObjectFactory)null, maxIdle, 4);
/*     */   }
/*     */   
/*     */   public StackObjectPoolFactory(int maxIdle, int initIdleCapacity) {
/*  62 */     this((PoolableObjectFactory)null, maxIdle, initIdleCapacity);
/*     */   }
/*     */   
/*     */   public StackObjectPoolFactory(PoolableObjectFactory factory) {
/*  72 */     this(factory, 8, 4);
/*     */   }
/*     */   
/*     */   public StackObjectPoolFactory(PoolableObjectFactory factory, int maxIdle) {
/*  82 */     this(factory, maxIdle, 4);
/*     */   }
/*     */   
/*     */   public StackObjectPoolFactory(PoolableObjectFactory factory, int maxIdle, int initIdleCapacity) {
/* 102 */     this._factory = null;
/* 103 */     this._maxSleeping = 8;
/* 104 */     this._initCapacity = 4;
/*     */     this._factory = factory;
/*     */     this._maxSleeping = maxIdle;
/*     */     this._initCapacity = initIdleCapacity;
/*     */   }
/*     */   
/*     */   public ObjectPool createPool() {
/*     */     return new StackObjectPool(this._factory, this._maxSleeping, this._initCapacity);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\pool\impl\StackObjectPoolFactory.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */