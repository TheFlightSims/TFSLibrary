/*     */ package org.apache.commons.pool.impl;
/*     */ 
/*     */ import org.apache.commons.pool.KeyedObjectPool;
/*     */ import org.apache.commons.pool.KeyedObjectPoolFactory;
/*     */ import org.apache.commons.pool.KeyedPoolableObjectFactory;
/*     */ 
/*     */ public class StackKeyedObjectPoolFactory implements KeyedObjectPoolFactory {
/*     */   protected KeyedPoolableObjectFactory _factory;
/*     */   
/*     */   protected int _maxSleeping;
/*     */   
/*     */   protected int _initCapacity;
/*     */   
/*     */   public StackKeyedObjectPoolFactory() {
/*  41 */     this((KeyedPoolableObjectFactory)null, 8, 4);
/*     */   }
/*     */   
/*     */   public StackKeyedObjectPoolFactory(int max) {
/*  51 */     this((KeyedPoolableObjectFactory)null, max, 4);
/*     */   }
/*     */   
/*     */   public StackKeyedObjectPoolFactory(int max, int init) {
/*  62 */     this((KeyedPoolableObjectFactory)null, max, init);
/*     */   }
/*     */   
/*     */   public StackKeyedObjectPoolFactory(KeyedPoolableObjectFactory factory) {
/*  72 */     this(factory, 8, 4);
/*     */   }
/*     */   
/*     */   public StackKeyedObjectPoolFactory(KeyedPoolableObjectFactory factory, int max) {
/*  83 */     this(factory, max, 4);
/*     */   }
/*     */   
/*     */   public StackKeyedObjectPoolFactory(KeyedPoolableObjectFactory factory, int max, int init) {
/* 104 */     this._factory = null;
/* 105 */     this._maxSleeping = 8;
/* 106 */     this._initCapacity = 4;
/*     */     this._factory = factory;
/*     */     this._maxSleeping = max;
/*     */     this._initCapacity = init;
/*     */   }
/*     */   
/*     */   public KeyedObjectPool createPool() {
/*     */     return new StackKeyedObjectPool(this._factory, this._maxSleeping, this._initCapacity);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\pool\impl\StackKeyedObjectPoolFactory.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */