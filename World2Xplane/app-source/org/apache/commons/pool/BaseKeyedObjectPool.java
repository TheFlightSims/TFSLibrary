/*     */ package org.apache.commons.pool;
/*     */ 
/*     */ public abstract class BaseKeyedObjectPool implements KeyedObjectPool {
/*     */   public abstract Object borrowObject(Object paramObject) throws Exception;
/*     */   
/*     */   public abstract void returnObject(Object paramObject1, Object paramObject2) throws Exception;
/*     */   
/*     */   public abstract void invalidateObject(Object paramObject1, Object paramObject2) throws Exception;
/*     */   
/*     */   public void addObject(Object key) throws Exception, UnsupportedOperationException {
/*  41 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public int getNumIdle(Object key) throws UnsupportedOperationException {
/*  49 */     return -1;
/*     */   }
/*     */   
/*     */   public int getNumActive(Object key) throws UnsupportedOperationException {
/*  57 */     return -1;
/*     */   }
/*     */   
/*     */   public int getNumIdle() throws UnsupportedOperationException {
/*  65 */     return -1;
/*     */   }
/*     */   
/*     */   public int getNumActive() throws UnsupportedOperationException {
/*  73 */     return -1;
/*     */   }
/*     */   
/*     */   public void clear() throws Exception, UnsupportedOperationException {
/*  80 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void clear(Object key) throws Exception, UnsupportedOperationException {
/*  87 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void close() throws Exception {
/*  95 */     this.closed = true;
/*     */   }
/*     */   
/*     */   public void setFactory(KeyedPoolableObjectFactory factory) throws IllegalStateException, UnsupportedOperationException {
/* 104 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   protected final boolean isClosed() {
/* 113 */     return this.closed;
/*     */   }
/*     */   
/*     */   protected final void assertOpen() throws IllegalStateException {
/* 123 */     if (isClosed())
/* 124 */       throw new IllegalStateException("Pool not open"); 
/*     */   }
/*     */   
/*     */   private volatile boolean closed = false;
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\pool\BaseKeyedObjectPool.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */