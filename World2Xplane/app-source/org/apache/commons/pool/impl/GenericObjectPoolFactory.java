/*     */ package org.apache.commons.pool.impl;
/*     */ 
/*     */ import org.apache.commons.pool.ObjectPool;
/*     */ import org.apache.commons.pool.ObjectPoolFactory;
/*     */ import org.apache.commons.pool.PoolableObjectFactory;
/*     */ 
/*     */ public class GenericObjectPoolFactory implements ObjectPoolFactory {
/*     */   protected int _maxIdle;
/*     */   
/*     */   protected int _minIdle;
/*     */   
/*     */   protected int _maxActive;
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
/*     */   protected long _softMinEvictableIdleTimeMillis;
/*     */   
/*     */   protected boolean _lifo;
/*     */   
/*     */   protected PoolableObjectFactory _factory;
/*     */   
/*     */   public GenericObjectPoolFactory(PoolableObjectFactory factory) {
/*  42 */     this(factory, 8, (byte)1, -1L, 8, 0, false, false, -1L, 3, 1800000L, false);
/*     */   }
/*     */   
/*     */   public GenericObjectPoolFactory(PoolableObjectFactory factory, GenericObjectPool.Config config) throws NullPointerException {
/*  54 */     this(factory, config.maxActive, config.whenExhaustedAction, config.maxWait, config.maxIdle, config.minIdle, config.testOnBorrow, config.testOnReturn, config.timeBetweenEvictionRunsMillis, config.numTestsPerEvictionRun, config.minEvictableIdleTimeMillis, config.testWhileIdle, config.softMinEvictableIdleTimeMillis, config.lifo);
/*     */   }
/*     */   
/*     */   public GenericObjectPoolFactory(PoolableObjectFactory factory, int maxActive) {
/*  65 */     this(factory, maxActive, (byte)1, -1L, 8, 0, false, false, -1L, 3, 1800000L, false);
/*     */   }
/*     */   
/*     */   public GenericObjectPoolFactory(PoolableObjectFactory factory, int maxActive, byte whenExhaustedAction, long maxWait) {
/*  78 */     this(factory, maxActive, whenExhaustedAction, maxWait, 8, 0, false, false, -1L, 3, 1800000L, false);
/*     */   }
/*     */   
/*     */   public GenericObjectPoolFactory(PoolableObjectFactory factory, int maxActive, byte whenExhaustedAction, long maxWait, boolean testOnBorrow, boolean testOnReturn) {
/*  93 */     this(factory, maxActive, whenExhaustedAction, maxWait, 8, 0, testOnBorrow, testOnReturn, -1L, 3, 1800000L, false);
/*     */   }
/*     */   
/*     */   public GenericObjectPoolFactory(PoolableObjectFactory factory, int maxActive, byte whenExhaustedAction, long maxWait, int maxIdle) {
/* 107 */     this(factory, maxActive, whenExhaustedAction, maxWait, maxIdle, 0, false, false, -1L, 3, 1800000L, false);
/*     */   }
/*     */   
/*     */   public GenericObjectPoolFactory(PoolableObjectFactory factory, int maxActive, byte whenExhaustedAction, long maxWait, int maxIdle, boolean testOnBorrow, boolean testOnReturn) {
/* 123 */     this(factory, maxActive, whenExhaustedAction, maxWait, maxIdle, 0, testOnBorrow, testOnReturn, -1L, 3, 1800000L, false);
/*     */   }
/*     */   
/*     */   public GenericObjectPoolFactory(PoolableObjectFactory factory, int maxActive, byte whenExhaustedAction, long maxWait, int maxIdle, boolean testOnBorrow, boolean testOnReturn, long timeBetweenEvictionRunsMillis, int numTestsPerEvictionRun, long minEvictableIdleTimeMillis, boolean testWhileIdle) {
/* 143 */     this(factory, maxActive, whenExhaustedAction, maxWait, maxIdle, 0, testOnBorrow, testOnReturn, timeBetweenEvictionRunsMillis, numTestsPerEvictionRun, minEvictableIdleTimeMillis, testWhileIdle, -1L);
/*     */   }
/*     */   
/*     */   public GenericObjectPoolFactory(PoolableObjectFactory factory, int maxActive, byte whenExhaustedAction, long maxWait, int maxIdle, int minIdle, boolean testOnBorrow, boolean testOnReturn, long timeBetweenEvictionRunsMillis, int numTestsPerEvictionRun, long minEvictableIdleTimeMillis, boolean testWhileIdle) {
/* 164 */     this(factory, maxActive, whenExhaustedAction, maxWait, maxIdle, minIdle, testOnBorrow, testOnReturn, timeBetweenEvictionRunsMillis, numTestsPerEvictionRun, minEvictableIdleTimeMillis, testWhileIdle, -1L);
/*     */   }
/*     */   
/*     */   public GenericObjectPoolFactory(PoolableObjectFactory factory, int maxActive, byte whenExhaustedAction, long maxWait, int maxIdle, int minIdle, boolean testOnBorrow, boolean testOnReturn, long timeBetweenEvictionRunsMillis, int numTestsPerEvictionRun, long minEvictableIdleTimeMillis, boolean testWhileIdle, long softMinEvictableIdleTimeMillis) {
/* 187 */     this(factory, maxActive, whenExhaustedAction, maxWait, maxIdle, minIdle, testOnBorrow, testOnReturn, timeBetweenEvictionRunsMillis, numTestsPerEvictionRun, minEvictableIdleTimeMillis, testWhileIdle, softMinEvictableIdleTimeMillis, true);
/*     */   }
/*     */   
/*     */   public GenericObjectPoolFactory(PoolableObjectFactory factory, int maxActive, byte whenExhaustedAction, long maxWait, int maxIdle, int minIdle, boolean testOnBorrow, boolean testOnReturn, long timeBetweenEvictionRunsMillis, int numTestsPerEvictionRun, long minEvictableIdleTimeMillis, boolean testWhileIdle, long softMinEvictableIdleTimeMillis, boolean lifo) {
/* 231 */     this._maxIdle = 8;
/* 232 */     this._minIdle = 0;
/* 233 */     this._maxActive = 8;
/* 234 */     this._maxWait = -1L;
/* 235 */     this._whenExhaustedAction = 1;
/* 236 */     this._testOnBorrow = false;
/* 237 */     this._testOnReturn = false;
/* 238 */     this._testWhileIdle = false;
/* 239 */     this._timeBetweenEvictionRunsMillis = -1L;
/* 240 */     this._numTestsPerEvictionRun = 3;
/* 241 */     this._minEvictableIdleTimeMillis = 1800000L;
/* 242 */     this._softMinEvictableIdleTimeMillis = 1800000L;
/* 243 */     this._lifo = true;
/* 244 */     this._factory = null;
/*     */     this._maxIdle = maxIdle;
/*     */     this._minIdle = minIdle;
/*     */     this._maxActive = maxActive;
/*     */     this._maxWait = maxWait;
/*     */     this._whenExhaustedAction = whenExhaustedAction;
/*     */     this._testOnBorrow = testOnBorrow;
/*     */     this._testOnReturn = testOnReturn;
/*     */     this._testWhileIdle = testWhileIdle;
/*     */     this._timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
/*     */     this._numTestsPerEvictionRun = numTestsPerEvictionRun;
/*     */     this._minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
/*     */     this._softMinEvictableIdleTimeMillis = softMinEvictableIdleTimeMillis;
/*     */     this._lifo = lifo;
/*     */     this._factory = factory;
/*     */   }
/*     */   
/*     */   public ObjectPool createPool() {
/*     */     return new GenericObjectPool(this._factory, this._maxActive, this._whenExhaustedAction, this._maxWait, this._maxIdle, this._minIdle, this._testOnBorrow, this._testOnReturn, this._timeBetweenEvictionRunsMillis, this._numTestsPerEvictionRun, this._minEvictableIdleTimeMillis, this._testWhileIdle, this._softMinEvictableIdleTimeMillis, this._lifo);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\pool\impl\GenericObjectPoolFactory.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */