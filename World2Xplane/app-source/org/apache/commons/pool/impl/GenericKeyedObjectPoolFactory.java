/*     */ package org.apache.commons.pool.impl;
/*     */ 
/*     */ import org.apache.commons.pool.KeyedObjectPool;
/*     */ import org.apache.commons.pool.KeyedObjectPoolFactory;
/*     */ import org.apache.commons.pool.KeyedPoolableObjectFactory;
/*     */ 
/*     */ public class GenericKeyedObjectPoolFactory implements KeyedObjectPoolFactory {
/*     */   protected int _maxIdle;
/*     */   
/*     */   protected int _maxActive;
/*     */   
/*     */   protected int _maxTotal;
/*     */   
/*     */   protected int _minIdle;
/*     */   
/*     */   protected long _maxWait;
/*     */   
/*     */   protected byte _whenExhaustedAction;
/*     */   
/*     */   protected boolean _testOnBorrow;
/*     */   
/*     */   protected boolean _testOnReturn;
/*     */   
/*     */   protected boolean _testWhileIdle;
/*     */   
/*     */   protected long _timeBetweenEvictionRunsMillis;
/*     */   
/*     */   protected int _numTestsPerEvictionRun;
/*     */   
/*     */   protected long _minEvictableIdleTimeMillis;
/*     */   
/*     */   protected KeyedPoolableObjectFactory _factory;
/*     */   
/*     */   protected boolean _lifo;
/*     */   
/*     */   public GenericKeyedObjectPoolFactory(KeyedPoolableObjectFactory factory) {
/*  43 */     this(factory, 8, (byte)1, -1L, 8, false, false, -1L, 3, 1800000L, false);
/*     */   }
/*     */   
/*     */   public GenericKeyedObjectPoolFactory(KeyedPoolableObjectFactory factory, GenericKeyedObjectPool.Config config) throws NullPointerException {
/*  55 */     this(factory, config.maxActive, config.whenExhaustedAction, config.maxWait, config.maxIdle, config.maxTotal, config.minIdle, config.testOnBorrow, config.testOnReturn, config.timeBetweenEvictionRunsMillis, config.numTestsPerEvictionRun, config.minEvictableIdleTimeMillis, config.testWhileIdle, config.lifo);
/*     */   }
/*     */   
/*     */   public GenericKeyedObjectPoolFactory(KeyedPoolableObjectFactory factory, int maxActive) {
/*  66 */     this(factory, maxActive, (byte)1, -1L, 8, -1, false, false, -1L, 3, 1800000L, false);
/*     */   }
/*     */   
/*     */   public GenericKeyedObjectPoolFactory(KeyedPoolableObjectFactory factory, int maxActive, byte whenExhaustedAction, long maxWait) {
/*  79 */     this(factory, maxActive, whenExhaustedAction, maxWait, 8, -1, false, false, -1L, 3, 1800000L, false);
/*     */   }
/*     */   
/*     */   public GenericKeyedObjectPoolFactory(KeyedPoolableObjectFactory factory, int maxActive, byte whenExhaustedAction, long maxWait, boolean testOnBorrow, boolean testOnReturn) {
/*  94 */     this(factory, maxActive, whenExhaustedAction, maxWait, 8, -1, testOnBorrow, testOnReturn, -1L, 3, 1800000L, false);
/*     */   }
/*     */   
/*     */   public GenericKeyedObjectPoolFactory(KeyedPoolableObjectFactory factory, int maxActive, byte whenExhaustedAction, long maxWait, int maxIdle) {
/* 108 */     this(factory, maxActive, whenExhaustedAction, maxWait, maxIdle, -1, false, false, -1L, 3, 1800000L, false);
/*     */   }
/*     */   
/*     */   public GenericKeyedObjectPoolFactory(KeyedPoolableObjectFactory factory, int maxActive, byte whenExhaustedAction, long maxWait, int maxIdle, int maxTotal) {
/* 122 */     this(factory, maxActive, whenExhaustedAction, maxWait, maxIdle, maxTotal, false, false, -1L, 3, 1800000L, false);
/*     */   }
/*     */   
/*     */   public GenericKeyedObjectPoolFactory(KeyedPoolableObjectFactory factory, int maxActive, byte whenExhaustedAction, long maxWait, int maxIdle, boolean testOnBorrow, boolean testOnReturn) {
/* 138 */     this(factory, maxActive, whenExhaustedAction, maxWait, maxIdle, -1, testOnBorrow, testOnReturn, -1L, 3, 1800000L, false);
/*     */   }
/*     */   
/*     */   public GenericKeyedObjectPoolFactory(KeyedPoolableObjectFactory factory, int maxActive, byte whenExhaustedAction, long maxWait, int maxIdle, boolean testOnBorrow, boolean testOnReturn, long timeBetweenEvictionRunsMillis, int numTestsPerEvictionRun, long minEvictableIdleTimeMillis, boolean testWhileIdle) {
/* 158 */     this(factory, maxActive, whenExhaustedAction, maxWait, maxIdle, -1, testOnBorrow, testOnReturn, timeBetweenEvictionRunsMillis, numTestsPerEvictionRun, minEvictableIdleTimeMillis, testWhileIdle);
/*     */   }
/*     */   
/*     */   public GenericKeyedObjectPoolFactory(KeyedPoolableObjectFactory factory, int maxActive, byte whenExhaustedAction, long maxWait, int maxIdle, int maxTotal, boolean testOnBorrow, boolean testOnReturn, long timeBetweenEvictionRunsMillis, int numTestsPerEvictionRun, long minEvictableIdleTimeMillis, boolean testWhileIdle) {
/* 179 */     this(factory, maxActive, whenExhaustedAction, maxWait, maxIdle, maxTotal, 0, testOnBorrow, testOnReturn, timeBetweenEvictionRunsMillis, numTestsPerEvictionRun, minEvictableIdleTimeMillis, testWhileIdle);
/*     */   }
/*     */   
/*     */   public GenericKeyedObjectPoolFactory(KeyedPoolableObjectFactory factory, int maxActive, byte whenExhaustedAction, long maxWait, int maxIdle, int maxTotal, int minIdle, boolean testOnBorrow, boolean testOnReturn, long timeBetweenEvictionRunsMillis, int numTestsPerEvictionRun, long minEvictableIdleTimeMillis, boolean testWhileIdle) {
/* 202 */     this(factory, maxActive, whenExhaustedAction, maxWait, maxIdle, maxTotal, minIdle, testOnBorrow, testOnReturn, timeBetweenEvictionRunsMillis, numTestsPerEvictionRun, minEvictableIdleTimeMillis, testWhileIdle, true);
/*     */   }
/*     */   
/*     */   public GenericKeyedObjectPoolFactory(KeyedPoolableObjectFactory factory, int maxActive, byte whenExhaustedAction, long maxWait, int maxIdle, int maxTotal, int minIdle, boolean testOnBorrow, boolean testOnReturn, long timeBetweenEvictionRunsMillis, int numTestsPerEvictionRun, long minEvictableIdleTimeMillis, boolean testWhileIdle, boolean lifo) {
/* 248 */     this._maxIdle = 8;
/* 249 */     this._maxActive = 8;
/* 250 */     this._maxTotal = -1;
/* 251 */     this._minIdle = 0;
/* 252 */     this._maxWait = -1L;
/* 253 */     this._whenExhaustedAction = 1;
/* 254 */     this._testOnBorrow = false;
/* 255 */     this._testOnReturn = false;
/* 256 */     this._testWhileIdle = false;
/* 257 */     this._timeBetweenEvictionRunsMillis = -1L;
/* 258 */     this._numTestsPerEvictionRun = 3;
/* 259 */     this._minEvictableIdleTimeMillis = 1800000L;
/* 260 */     this._factory = null;
/* 261 */     this._lifo = true;
/*     */     this._maxIdle = maxIdle;
/*     */     this._maxActive = maxActive;
/*     */     this._maxTotal = maxTotal;
/*     */     this._minIdle = minIdle;
/*     */     this._maxWait = maxWait;
/*     */     this._whenExhaustedAction = whenExhaustedAction;
/*     */     this._testOnBorrow = testOnBorrow;
/*     */     this._testOnReturn = testOnReturn;
/*     */     this._testWhileIdle = testWhileIdle;
/*     */     this._timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
/*     */     this._numTestsPerEvictionRun = numTestsPerEvictionRun;
/*     */     this._minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
/*     */     this._factory = factory;
/*     */     this._lifo = lifo;
/*     */   }
/*     */   
/*     */   public KeyedObjectPool createPool() {
/*     */     return new GenericKeyedObjectPool(this._factory, this._maxActive, this._whenExhaustedAction, this._maxWait, this._maxIdle, this._maxTotal, this._minIdle, this._testOnBorrow, this._testOnReturn, this._timeBetweenEvictionRunsMillis, this._numTestsPerEvictionRun, this._minEvictableIdleTimeMillis, this._testWhileIdle, this._lifo);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\pool\impl\GenericKeyedObjectPoolFactory.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */