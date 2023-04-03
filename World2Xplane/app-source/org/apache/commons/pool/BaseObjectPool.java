/*     */ package org.apache.commons.pool;
/*     */ 
/*     */ public abstract class BaseObjectPool implements ObjectPool {
/*     */   public abstract Object borrowObject() throws Exception;
/*     */   
/*     */   public abstract void returnObject(Object paramObject) throws Exception;
/*     */   
/*     */   public abstract void invalidateObject(Object paramObject) throws Exception;
/*     */   
/*     */   public int getNumIdle() throws UnsupportedOperationException {
/*  69 */     return -1;
/*     */   }
/*     */   
/*     */   public int getNumActive() throws UnsupportedOperationException {
/*  79 */     return -1;
/*     */   }
/*     */   
/*     */   public void clear() throws Exception, UnsupportedOperationException {
/*  88 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void addObject() throws Exception, UnsupportedOperationException {
/*  99 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void close() throws Exception {
/* 107 */     this.closed = true;
/*     */   }
/*     */   
/*     */   public void setFactory(PoolableObjectFactory factory) throws IllegalStateException, UnsupportedOperationException {
/* 120 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   protected final boolean isClosed() {
/* 128 */     return this.closed;
/*     */   }
/*     */   
/*     */   protected final void assertOpen() throws IllegalStateException {
/* 137 */     if (isClosed())
/* 138 */       throw new IllegalStateException("Pool not open"); 
/*     */   }
/*     */   
/*     */   private volatile boolean closed = false;
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\pool\BaseObjectPool.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */