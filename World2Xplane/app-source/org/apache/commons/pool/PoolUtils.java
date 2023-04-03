/*      */ package org.apache.commons.pool;
/*      */ 
/*      */ import java.util.Collection;
/*      */ import java.util.Collections;
/*      */ import java.util.HashMap;
/*      */ import java.util.Iterator;
/*      */ import java.util.Map;
/*      */ import java.util.NoSuchElementException;
/*      */ import java.util.Timer;
/*      */ import java.util.TimerTask;
/*      */ 
/*      */ public final class PoolUtils {
/*      */   private static Timer MIN_IDLE_TIMER;
/*      */   
/*      */   public static PoolableObjectFactory adapt(KeyedPoolableObjectFactory keyedFactory) throws IllegalArgumentException {
/*   65 */     return adapt(keyedFactory, new Object());
/*      */   }
/*      */   
/*      */   public static PoolableObjectFactory adapt(KeyedPoolableObjectFactory keyedFactory, Object key) throws IllegalArgumentException {
/*   80 */     return new PoolableObjectFactoryAdaptor(keyedFactory, key);
/*      */   }
/*      */   
/*      */   public static KeyedPoolableObjectFactory adapt(PoolableObjectFactory factory) throws IllegalArgumentException {
/*   93 */     return new KeyedPoolableObjectFactoryAdaptor(factory);
/*      */   }
/*      */   
/*      */   public static ObjectPool adapt(KeyedObjectPool keyedPool) throws IllegalArgumentException {
/*  107 */     return adapt(keyedPool, new Object());
/*      */   }
/*      */   
/*      */   public static ObjectPool adapt(KeyedObjectPool keyedPool, Object key) throws IllegalArgumentException {
/*  122 */     return new ObjectPoolAdaptor(keyedPool, key);
/*      */   }
/*      */   
/*      */   public static KeyedObjectPool adapt(ObjectPool pool) throws IllegalArgumentException {
/*  135 */     return new KeyedObjectPoolAdaptor(pool);
/*      */   }
/*      */   
/*      */   public static ObjectPool checkedPool(ObjectPool pool, Class type) {
/*  148 */     if (pool == null)
/*  149 */       throw new IllegalArgumentException("pool must not be null."); 
/*  151 */     if (type == null)
/*  152 */       throw new IllegalArgumentException("type must not be null."); 
/*  154 */     return new CheckedObjectPool(pool, type);
/*      */   }
/*      */   
/*      */   public static KeyedObjectPool checkedPool(KeyedObjectPool keyedPool, Class type) {
/*  167 */     if (keyedPool == null)
/*  168 */       throw new IllegalArgumentException("keyedPool must not be null."); 
/*  170 */     if (type == null)
/*  171 */       throw new IllegalArgumentException("type must not be null."); 
/*  173 */     return new CheckedKeyedObjectPool(keyedPool, type);
/*      */   }
/*      */   
/*      */   public static TimerTask checkMinIdle(ObjectPool pool, int minIdle, long period) throws IllegalArgumentException {
/*  191 */     if (pool == null)
/*  192 */       throw new IllegalArgumentException("keyedPool must not be null."); 
/*  194 */     if (minIdle < 0)
/*  195 */       throw new IllegalArgumentException("minIdle must be non-negative."); 
/*  197 */     TimerTask task = new ObjectPoolMinIdleTimerTask(pool, minIdle);
/*  198 */     getMinIdleTimer().schedule(task, 0L, period);
/*  199 */     return task;
/*      */   }
/*      */   
/*      */   public static TimerTask checkMinIdle(KeyedObjectPool keyedPool, Object key, int minIdle, long period) throws IllegalArgumentException {
/*  219 */     if (keyedPool == null)
/*  220 */       throw new IllegalArgumentException("keyedPool must not be null."); 
/*  222 */     if (key == null)
/*  223 */       throw new IllegalArgumentException("key must not be null."); 
/*  225 */     if (minIdle < 0)
/*  226 */       throw new IllegalArgumentException("minIdle must be non-negative."); 
/*  228 */     TimerTask task = new KeyedObjectPoolMinIdleTimerTask(keyedPool, key, minIdle);
/*  229 */     getMinIdleTimer().schedule(task, 0L, period);
/*  230 */     return task;
/*      */   }
/*      */   
/*      */   public static Map checkMinIdle(KeyedObjectPool keyedPool, Collection keys, int minIdle, long period) throws IllegalArgumentException {
/*  250 */     if (keys == null)
/*  251 */       throw new IllegalArgumentException("keys must not be null."); 
/*  253 */     Map tasks = new HashMap(keys.size());
/*  254 */     Iterator iter = keys.iterator();
/*  255 */     while (iter.hasNext()) {
/*  256 */       Object key = iter.next();
/*  257 */       TimerTask task = checkMinIdle(keyedPool, key, minIdle, period);
/*  258 */       tasks.put(key, task);
/*      */     } 
/*  260 */     return tasks;
/*      */   }
/*      */   
/*      */   public static void prefill(ObjectPool pool, int count) throws Exception, IllegalArgumentException {
/*  273 */     if (pool == null)
/*  274 */       throw new IllegalArgumentException("pool must not be null."); 
/*  276 */     for (int i = 0; i < count; i++)
/*  277 */       pool.addObject(); 
/*      */   }
/*      */   
/*      */   public static void prefill(KeyedObjectPool keyedPool, Object key, int count) throws Exception, IllegalArgumentException {
/*  293 */     if (keyedPool == null)
/*  294 */       throw new IllegalArgumentException("keyedPool must not be null."); 
/*  296 */     if (key == null)
/*  297 */       throw new IllegalArgumentException("key must not be null."); 
/*  299 */     for (int i = 0; i < count; i++)
/*  300 */       keyedPool.addObject(key); 
/*      */   }
/*      */   
/*      */   public static void prefill(KeyedObjectPool keyedPool, Collection keys, int count) throws Exception, IllegalArgumentException {
/*  319 */     if (keys == null)
/*  320 */       throw new IllegalArgumentException("keys must not be null."); 
/*  322 */     Iterator iter = keys.iterator();
/*  323 */     while (iter.hasNext())
/*  324 */       prefill(keyedPool, iter.next(), count); 
/*      */   }
/*      */   
/*      */   public static ObjectPool synchronizedPool(ObjectPool pool) {
/*  343 */     if (pool == null)
/*  344 */       throw new IllegalArgumentException("pool must not be null."); 
/*  356 */     return new SynchronizedObjectPool(pool);
/*      */   }
/*      */   
/*      */   public static KeyedObjectPool synchronizedPool(KeyedObjectPool keyedPool) {
/*  374 */     if (keyedPool == null)
/*  375 */       throw new IllegalArgumentException("keyedPool must not be null."); 
/*  385 */     return new SynchronizedKeyedObjectPool(keyedPool);
/*      */   }
/*      */   
/*      */   public static PoolableObjectFactory synchronizedPoolableFactory(PoolableObjectFactory factory) {
/*  396 */     return new SynchronizedPoolableObjectFactory(factory);
/*      */   }
/*      */   
/*      */   public static KeyedPoolableObjectFactory synchronizedPoolableFactory(KeyedPoolableObjectFactory keyedFactory) {
/*  407 */     return new SynchronizedKeyedPoolableObjectFactory(keyedFactory);
/*      */   }
/*      */   
/*      */   public static ObjectPool erodingPool(ObjectPool pool) {
/*  422 */     return erodingPool(pool, 1.0F);
/*      */   }
/*      */   
/*      */   public static ObjectPool erodingPool(ObjectPool pool, float factor) {
/*  446 */     if (pool == null)
/*  447 */       throw new IllegalArgumentException("pool must not be null."); 
/*  449 */     if (factor <= 0.0F)
/*  450 */       throw new IllegalArgumentException("factor must be positive."); 
/*  452 */     return new ErodingObjectPool(pool, factor);
/*      */   }
/*      */   
/*      */   public static KeyedObjectPool erodingPool(KeyedObjectPool keyedPool) {
/*  469 */     return erodingPool(keyedPool, 1.0F);
/*      */   }
/*      */   
/*      */   public static KeyedObjectPool erodingPool(KeyedObjectPool keyedPool, float factor) {
/*  494 */     return erodingPool(keyedPool, factor, false);
/*      */   }
/*      */   
/*      */   public static KeyedObjectPool erodingPool(KeyedObjectPool keyedPool, float factor, boolean perKey) {
/*  527 */     if (keyedPool == null)
/*  528 */       throw new IllegalArgumentException("keyedPool must not be null."); 
/*  530 */     if (factor <= 0.0F)
/*  531 */       throw new IllegalArgumentException("factor must be positive."); 
/*  533 */     if (perKey)
/*  534 */       return new ErodingPerKeyKeyedObjectPool(keyedPool, factor); 
/*  536 */     return new ErodingKeyedObjectPool(keyedPool, factor);
/*      */   }
/*      */   
/*      */   private static synchronized Timer getMinIdleTimer() {
/*  547 */     if (MIN_IDLE_TIMER == null)
/*  548 */       MIN_IDLE_TIMER = new Timer(true); 
/*  550 */     return MIN_IDLE_TIMER;
/*      */   }
/*      */   
/*      */   private static class PoolableObjectFactoryAdaptor implements PoolableObjectFactory {
/*      */     private final Object key;
/*      */     
/*      */     private final KeyedPoolableObjectFactory keyedFactory;
/*      */     
/*      */     PoolableObjectFactoryAdaptor(KeyedPoolableObjectFactory keyedFactory, Object key) throws IllegalArgumentException {
/*  558 */       if (keyedFactory == null)
/*  559 */         throw new IllegalArgumentException("keyedFactory must not be null."); 
/*  561 */       if (key == null)
/*  562 */         throw new IllegalArgumentException("key must not be null."); 
/*  564 */       this.keyedFactory = keyedFactory;
/*  565 */       this.key = key;
/*      */     }
/*      */     
/*      */     public Object makeObject() throws Exception {
/*  569 */       return this.keyedFactory.makeObject(this.key);
/*      */     }
/*      */     
/*      */     public void destroyObject(Object obj) throws Exception {
/*  573 */       this.keyedFactory.destroyObject(this.key, obj);
/*      */     }
/*      */     
/*      */     public boolean validateObject(Object obj) {
/*  577 */       return this.keyedFactory.validateObject(this.key, obj);
/*      */     }
/*      */     
/*      */     public void activateObject(Object obj) throws Exception {
/*  581 */       this.keyedFactory.activateObject(this.key, obj);
/*      */     }
/*      */     
/*      */     public void passivateObject(Object obj) throws Exception {
/*  585 */       this.keyedFactory.passivateObject(this.key, obj);
/*      */     }
/*      */     
/*      */     public String toString() {
/*  589 */       StringBuffer sb = new StringBuffer();
/*  590 */       sb.append("PoolableObjectFactoryAdaptor");
/*  591 */       sb.append("{key=").append(this.key);
/*  592 */       sb.append(", keyedFactory=").append(this.keyedFactory);
/*  593 */       sb.append('}');
/*  594 */       return sb.toString();
/*      */     }
/*      */   }
/*      */   
/*      */   private static class KeyedPoolableObjectFactoryAdaptor implements KeyedPoolableObjectFactory {
/*      */     private final PoolableObjectFactory factory;
/*      */     
/*      */     KeyedPoolableObjectFactoryAdaptor(PoolableObjectFactory factory) throws IllegalArgumentException {
/*  602 */       if (factory == null)
/*  603 */         throw new IllegalArgumentException("factory must not be null."); 
/*  605 */       this.factory = factory;
/*      */     }
/*      */     
/*      */     public Object makeObject(Object key) throws Exception {
/*  609 */       return this.factory.makeObject();
/*      */     }
/*      */     
/*      */     public void destroyObject(Object key, Object obj) throws Exception {
/*  613 */       this.factory.destroyObject(obj);
/*      */     }
/*      */     
/*      */     public boolean validateObject(Object key, Object obj) {
/*  617 */       return this.factory.validateObject(obj);
/*      */     }
/*      */     
/*      */     public void activateObject(Object key, Object obj) throws Exception {
/*  621 */       this.factory.activateObject(obj);
/*      */     }
/*      */     
/*      */     public void passivateObject(Object key, Object obj) throws Exception {
/*  625 */       this.factory.passivateObject(obj);
/*      */     }
/*      */     
/*      */     public String toString() {
/*  629 */       StringBuffer sb = new StringBuffer();
/*  630 */       sb.append("KeyedPoolableObjectFactoryAdaptor");
/*  631 */       sb.append("{factory=").append(this.factory);
/*  632 */       sb.append('}');
/*  633 */       return sb.toString();
/*      */     }
/*      */   }
/*      */   
/*      */   private static class ObjectPoolAdaptor implements ObjectPool {
/*      */     private final Object key;
/*      */     
/*      */     private final KeyedObjectPool keyedPool;
/*      */     
/*      */     ObjectPoolAdaptor(KeyedObjectPool keyedPool, Object key) throws IllegalArgumentException {
/*  642 */       if (keyedPool == null)
/*  643 */         throw new IllegalArgumentException("keyedPool must not be null."); 
/*  645 */       if (key == null)
/*  646 */         throw new IllegalArgumentException("key must not be null."); 
/*  648 */       this.keyedPool = keyedPool;
/*  649 */       this.key = key;
/*      */     }
/*      */     
/*      */     public Object borrowObject() throws Exception, NoSuchElementException, IllegalStateException {
/*  653 */       return this.keyedPool.borrowObject(this.key);
/*      */     }
/*      */     
/*      */     public void returnObject(Object obj) {
/*      */       try {
/*  658 */         this.keyedPool.returnObject(this.key, obj);
/*  659 */       } catch (Exception e) {}
/*      */     }
/*      */     
/*      */     public void invalidateObject(Object obj) {
/*      */       try {
/*  666 */         this.keyedPool.invalidateObject(this.key, obj);
/*  667 */       } catch (Exception e) {}
/*      */     }
/*      */     
/*      */     public void addObject() throws Exception, IllegalStateException {
/*  673 */       this.keyedPool.addObject(this.key);
/*      */     }
/*      */     
/*      */     public int getNumIdle() throws UnsupportedOperationException {
/*  677 */       return this.keyedPool.getNumIdle(this.key);
/*      */     }
/*      */     
/*      */     public int getNumActive() throws UnsupportedOperationException {
/*  681 */       return this.keyedPool.getNumActive(this.key);
/*      */     }
/*      */     
/*      */     public void clear() throws Exception, UnsupportedOperationException {
/*  685 */       this.keyedPool.clear();
/*      */     }
/*      */     
/*      */     public void close() {
/*      */       try {
/*  690 */         this.keyedPool.close();
/*  691 */       } catch (Exception e) {}
/*      */     }
/*      */     
/*      */     public void setFactory(PoolableObjectFactory factory) throws IllegalStateException, UnsupportedOperationException {
/*  697 */       this.keyedPool.setFactory(PoolUtils.adapt(factory));
/*      */     }
/*      */     
/*      */     public String toString() {
/*  701 */       StringBuffer sb = new StringBuffer();
/*  702 */       sb.append("ObjectPoolAdaptor");
/*  703 */       sb.append("{key=").append(this.key);
/*  704 */       sb.append(", keyedPool=").append(this.keyedPool);
/*  705 */       sb.append('}');
/*  706 */       return sb.toString();
/*      */     }
/*      */   }
/*      */   
/*      */   private static class KeyedObjectPoolAdaptor implements KeyedObjectPool {
/*      */     private final ObjectPool pool;
/*      */     
/*      */     KeyedObjectPoolAdaptor(ObjectPool pool) throws IllegalArgumentException {
/*  714 */       if (pool == null)
/*  715 */         throw new IllegalArgumentException("pool must not be null."); 
/*  717 */       this.pool = pool;
/*      */     }
/*      */     
/*      */     public Object borrowObject(Object key) throws Exception, NoSuchElementException, IllegalStateException {
/*  721 */       return this.pool.borrowObject();
/*      */     }
/*      */     
/*      */     public void returnObject(Object key, Object obj) {
/*      */       try {
/*  726 */         this.pool.returnObject(obj);
/*  727 */       } catch (Exception e) {}
/*      */     }
/*      */     
/*      */     public void invalidateObject(Object key, Object obj) {
/*      */       try {
/*  734 */         this.pool.invalidateObject(obj);
/*  735 */       } catch (Exception e) {}
/*      */     }
/*      */     
/*      */     public void addObject(Object key) throws Exception, IllegalStateException {
/*  741 */       this.pool.addObject();
/*      */     }
/*      */     
/*      */     public int getNumIdle(Object key) throws UnsupportedOperationException {
/*  745 */       return this.pool.getNumIdle();
/*      */     }
/*      */     
/*      */     public int getNumActive(Object key) throws UnsupportedOperationException {
/*  749 */       return this.pool.getNumActive();
/*      */     }
/*      */     
/*      */     public int getNumIdle() throws UnsupportedOperationException {
/*  753 */       return this.pool.getNumIdle();
/*      */     }
/*      */     
/*      */     public int getNumActive() throws UnsupportedOperationException {
/*  757 */       return this.pool.getNumActive();
/*      */     }
/*      */     
/*      */     public void clear() throws Exception, UnsupportedOperationException {
/*  761 */       this.pool.clear();
/*      */     }
/*      */     
/*      */     public void clear(Object key) throws Exception, UnsupportedOperationException {
/*  765 */       this.pool.clear();
/*      */     }
/*      */     
/*      */     public void close() {
/*      */       try {
/*  770 */         this.pool.close();
/*  771 */       } catch (Exception e) {}
/*      */     }
/*      */     
/*      */     public void setFactory(KeyedPoolableObjectFactory factory) throws IllegalStateException, UnsupportedOperationException {
/*  777 */       this.pool.setFactory(PoolUtils.adapt(factory));
/*      */     }
/*      */     
/*      */     public String toString() {
/*  781 */       StringBuffer sb = new StringBuffer();
/*  782 */       sb.append("KeyedObjectPoolAdaptor");
/*  783 */       sb.append("{pool=").append(this.pool);
/*  784 */       sb.append('}');
/*  785 */       return sb.toString();
/*      */     }
/*      */   }
/*      */   
/*      */   private static class CheckedObjectPool implements ObjectPool {
/*      */     private final Class type;
/*      */     
/*      */     private final ObjectPool pool;
/*      */     
/*      */     CheckedObjectPool(ObjectPool pool, Class type) {
/*  794 */       if (pool == null)
/*  795 */         throw new IllegalArgumentException("pool must not be null."); 
/*  797 */       if (type == null)
/*  798 */         throw new IllegalArgumentException("type must not be null."); 
/*  800 */       this.pool = pool;
/*  801 */       this.type = type;
/*      */     }
/*      */     
/*      */     public Object borrowObject() throws Exception, NoSuchElementException, IllegalStateException {
/*  805 */       Object obj = this.pool.borrowObject();
/*  806 */       if (this.type.isInstance(obj))
/*  807 */         return obj; 
/*  809 */       throw new ClassCastException("Borrowed object is not of type: " + this.type.getName() + " was: " + obj);
/*      */     }
/*      */     
/*      */     public void returnObject(Object obj) {
/*  814 */       if (this.type.isInstance(obj)) {
/*      */         try {
/*  816 */           this.pool.returnObject(obj);
/*  817 */         } catch (Exception e) {}
/*      */       } else {
/*  821 */         throw new ClassCastException("Returned object is not of type: " + this.type.getName() + " was: " + obj);
/*      */       } 
/*      */     }
/*      */     
/*      */     public void invalidateObject(Object obj) {
/*  826 */       if (this.type.isInstance(obj)) {
/*      */         try {
/*  828 */           this.pool.invalidateObject(obj);
/*  829 */         } catch (Exception e) {}
/*      */       } else {
/*  833 */         throw new ClassCastException("Invalidated object is not of type: " + this.type.getName() + " was: " + obj);
/*      */       } 
/*      */     }
/*      */     
/*      */     public void addObject() throws Exception, IllegalStateException, UnsupportedOperationException {
/*  838 */       this.pool.addObject();
/*      */     }
/*      */     
/*      */     public int getNumIdle() throws UnsupportedOperationException {
/*  842 */       return this.pool.getNumIdle();
/*      */     }
/*      */     
/*      */     public int getNumActive() throws UnsupportedOperationException {
/*  846 */       return this.pool.getNumActive();
/*      */     }
/*      */     
/*      */     public void clear() throws Exception, UnsupportedOperationException {
/*  850 */       this.pool.clear();
/*      */     }
/*      */     
/*      */     public void close() {
/*      */       try {
/*  855 */         this.pool.close();
/*  856 */       } catch (Exception e) {}
/*      */     }
/*      */     
/*      */     public void setFactory(PoolableObjectFactory factory) throws IllegalStateException, UnsupportedOperationException {
/*  862 */       this.pool.setFactory(factory);
/*      */     }
/*      */     
/*      */     public String toString() {
/*  866 */       StringBuffer sb = new StringBuffer();
/*  867 */       sb.append("CheckedObjectPool");
/*  868 */       sb.append("{type=").append(this.type);
/*  869 */       sb.append(", pool=").append(this.pool);
/*  870 */       sb.append('}');
/*  871 */       return sb.toString();
/*      */     }
/*      */   }
/*      */   
/*      */   private static class CheckedKeyedObjectPool implements KeyedObjectPool {
/*      */     private final Class type;
/*      */     
/*      */     private final KeyedObjectPool keyedPool;
/*      */     
/*      */     CheckedKeyedObjectPool(KeyedObjectPool keyedPool, Class type) {
/*  880 */       if (keyedPool == null)
/*  881 */         throw new IllegalArgumentException("keyedPool must not be null."); 
/*  883 */       if (type == null)
/*  884 */         throw new IllegalArgumentException("type must not be null."); 
/*  886 */       this.keyedPool = keyedPool;
/*  887 */       this.type = type;
/*      */     }
/*      */     
/*      */     public Object borrowObject(Object key) throws Exception, NoSuchElementException, IllegalStateException {
/*  891 */       Object obj = this.keyedPool.borrowObject(key);
/*  892 */       if (this.type.isInstance(obj))
/*  893 */         return obj; 
/*  895 */       throw new ClassCastException("Borrowed object for key: " + key + " is not of type: " + this.type.getName() + " was: " + obj);
/*      */     }
/*      */     
/*      */     public void returnObject(Object key, Object obj) {
/*  900 */       if (this.type.isInstance(obj)) {
/*      */         try {
/*  902 */           this.keyedPool.returnObject(key, obj);
/*  903 */         } catch (Exception e) {}
/*      */       } else {
/*  907 */         throw new ClassCastException("Returned object for key: " + key + " is not of type: " + this.type.getName() + " was: " + obj);
/*      */       } 
/*      */     }
/*      */     
/*      */     public void invalidateObject(Object key, Object obj) {
/*  912 */       if (this.type.isInstance(obj)) {
/*      */         try {
/*  914 */           this.keyedPool.invalidateObject(key, obj);
/*  915 */         } catch (Exception e) {}
/*      */       } else {
/*  919 */         throw new ClassCastException("Invalidated object for key: " + key + " is not of type: " + this.type.getName() + " was: " + obj);
/*      */       } 
/*      */     }
/*      */     
/*      */     public void addObject(Object key) throws Exception, IllegalStateException, UnsupportedOperationException {
/*  924 */       this.keyedPool.addObject(key);
/*      */     }
/*      */     
/*      */     public int getNumIdle(Object key) throws UnsupportedOperationException {
/*  928 */       return this.keyedPool.getNumIdle(key);
/*      */     }
/*      */     
/*      */     public int getNumActive(Object key) throws UnsupportedOperationException {
/*  932 */       return this.keyedPool.getNumActive(key);
/*      */     }
/*      */     
/*      */     public int getNumIdle() throws UnsupportedOperationException {
/*  936 */       return this.keyedPool.getNumIdle();
/*      */     }
/*      */     
/*      */     public int getNumActive() throws UnsupportedOperationException {
/*  940 */       return this.keyedPool.getNumActive();
/*      */     }
/*      */     
/*      */     public void clear() throws Exception, UnsupportedOperationException {
/*  944 */       this.keyedPool.clear();
/*      */     }
/*      */     
/*      */     public void clear(Object key) throws Exception, UnsupportedOperationException {
/*  948 */       this.keyedPool.clear(key);
/*      */     }
/*      */     
/*      */     public void close() {
/*      */       try {
/*  953 */         this.keyedPool.close();
/*  954 */       } catch (Exception e) {}
/*      */     }
/*      */     
/*      */     public void setFactory(KeyedPoolableObjectFactory factory) throws IllegalStateException, UnsupportedOperationException {
/*  960 */       this.keyedPool.setFactory(factory);
/*      */     }
/*      */     
/*      */     public String toString() {
/*  964 */       StringBuffer sb = new StringBuffer();
/*  965 */       sb.append("CheckedKeyedObjectPool");
/*  966 */       sb.append("{type=").append(this.type);
/*  967 */       sb.append(", keyedPool=").append(this.keyedPool);
/*  968 */       sb.append('}');
/*  969 */       return sb.toString();
/*      */     }
/*      */   }
/*      */   
/*      */   private static class ObjectPoolMinIdleTimerTask extends TimerTask {
/*      */     private final int minIdle;
/*      */     
/*      */     private final ObjectPool pool;
/*      */     
/*      */     ObjectPoolMinIdleTimerTask(ObjectPool pool, int minIdle) throws IllegalArgumentException {
/*  978 */       if (pool == null)
/*  979 */         throw new IllegalArgumentException("pool must not be null."); 
/*  981 */       this.pool = pool;
/*  982 */       this.minIdle = minIdle;
/*      */     }
/*      */     
/*      */     public void run() {
/*  986 */       boolean success = false;
/*      */       try {
/*  988 */         if (this.pool.getNumIdle() < this.minIdle)
/*  989 */           this.pool.addObject(); 
/*  991 */         success = true;
/*  993 */       } catch (Exception e) {
/*  994 */         cancel();
/*      */       } finally {
/*  998 */         if (!success)
/*  999 */           cancel(); 
/*      */       } 
/*      */     }
/*      */     
/*      */     public String toString() {
/* 1005 */       StringBuffer sb = new StringBuffer();
/* 1006 */       sb.append("ObjectPoolMinIdleTimerTask");
/* 1007 */       sb.append("{minIdle=").append(this.minIdle);
/* 1008 */       sb.append(", pool=").append(this.pool);
/* 1009 */       sb.append('}');
/* 1010 */       return sb.toString();
/*      */     }
/*      */   }
/*      */   
/*      */   private static class KeyedObjectPoolMinIdleTimerTask extends TimerTask {
/*      */     private final int minIdle;
/*      */     
/*      */     private final Object key;
/*      */     
/*      */     private final KeyedObjectPool keyedPool;
/*      */     
/*      */     KeyedObjectPoolMinIdleTimerTask(KeyedObjectPool keyedPool, Object key, int minIdle) throws IllegalArgumentException {
/* 1020 */       if (keyedPool == null)
/* 1021 */         throw new IllegalArgumentException("keyedPool must not be null."); 
/* 1023 */       this.keyedPool = keyedPool;
/* 1024 */       this.key = key;
/* 1025 */       this.minIdle = minIdle;
/*      */     }
/*      */     
/*      */     public void run() {
/* 1029 */       boolean success = false;
/*      */       try {
/* 1031 */         if (this.keyedPool.getNumIdle(this.key) < this.minIdle)
/* 1032 */           this.keyedPool.addObject(this.key); 
/* 1034 */         success = true;
/* 1036 */       } catch (Exception e) {
/* 1037 */         cancel();
/*      */       } finally {
/* 1041 */         if (!success)
/* 1042 */           cancel(); 
/*      */       } 
/*      */     }
/*      */     
/*      */     public String toString() {
/* 1048 */       StringBuffer sb = new StringBuffer();
/* 1049 */       sb.append("KeyedObjectPoolMinIdleTimerTask");
/* 1050 */       sb.append("{minIdle=").append(this.minIdle);
/* 1051 */       sb.append(", key=").append(this.key);
/* 1052 */       sb.append(", keyedPool=").append(this.keyedPool);
/* 1053 */       sb.append('}');
/* 1054 */       return sb.toString();
/*      */     }
/*      */   }
/*      */   
/*      */   private static class SynchronizedObjectPool implements ObjectPool {
/*      */     private final Object lock;
/*      */     
/*      */     private final ObjectPool pool;
/*      */     
/*      */     SynchronizedObjectPool(ObjectPool pool) throws IllegalArgumentException {
/* 1063 */       if (pool == null)
/* 1064 */         throw new IllegalArgumentException("pool must not be null."); 
/* 1066 */       this.pool = pool;
/* 1067 */       this.lock = new Object();
/*      */     }
/*      */     
/*      */     public Object borrowObject() throws Exception, NoSuchElementException, IllegalStateException {
/* 1071 */       synchronized (this.lock) {
/* 1072 */         return this.pool.borrowObject();
/*      */       } 
/*      */     }
/*      */     
/*      */     public void returnObject(Object obj) {
/* 1077 */       synchronized (this.lock) {
/*      */         try {
/* 1079 */           this.pool.returnObject(obj);
/* 1080 */         } catch (Exception e) {}
/*      */       } 
/*      */     }
/*      */     
/*      */     public void invalidateObject(Object obj) {
/* 1087 */       synchronized (this.lock) {
/*      */         try {
/* 1089 */           this.pool.invalidateObject(obj);
/* 1090 */         } catch (Exception e) {}
/*      */       } 
/*      */     }
/*      */     
/*      */     public void addObject() throws Exception, IllegalStateException, UnsupportedOperationException {
/* 1097 */       synchronized (this.lock) {
/* 1098 */         this.pool.addObject();
/*      */       } 
/*      */     }
/*      */     
/*      */     public int getNumIdle() throws UnsupportedOperationException {
/* 1103 */       synchronized (this.lock) {
/* 1104 */         return this.pool.getNumIdle();
/*      */       } 
/*      */     }
/*      */     
/*      */     public int getNumActive() throws UnsupportedOperationException {
/* 1109 */       synchronized (this.lock) {
/* 1110 */         return this.pool.getNumActive();
/*      */       } 
/*      */     }
/*      */     
/*      */     public void clear() throws Exception, UnsupportedOperationException {
/* 1115 */       synchronized (this.lock) {
/* 1116 */         this.pool.clear();
/*      */       } 
/*      */     }
/*      */     
/*      */     public void close() {
/*      */       try {
/* 1122 */         synchronized (this.lock) {
/* 1123 */           this.pool.close();
/*      */         } 
/* 1125 */       } catch (Exception e) {}
/*      */     }
/*      */     
/*      */     public void setFactory(PoolableObjectFactory factory) throws IllegalStateException, UnsupportedOperationException {
/* 1131 */       synchronized (this.lock) {
/* 1132 */         this.pool.setFactory(factory);
/*      */       } 
/*      */     }
/*      */     
/*      */     public String toString() {
/* 1137 */       StringBuffer sb = new StringBuffer();
/* 1138 */       sb.append("SynchronizedObjectPool");
/* 1139 */       sb.append("{pool=").append(this.pool);
/* 1140 */       sb.append('}');
/* 1141 */       return sb.toString();
/*      */     }
/*      */   }
/*      */   
/*      */   private static class SynchronizedKeyedObjectPool implements KeyedObjectPool {
/*      */     private final Object lock;
/*      */     
/*      */     private final KeyedObjectPool keyedPool;
/*      */     
/*      */     SynchronizedKeyedObjectPool(KeyedObjectPool keyedPool) throws IllegalArgumentException {
/* 1150 */       if (keyedPool == null)
/* 1151 */         throw new IllegalArgumentException("keyedPool must not be null."); 
/* 1153 */       this.keyedPool = keyedPool;
/* 1154 */       this.lock = new Object();
/*      */     }
/*      */     
/*      */     public Object borrowObject(Object key) throws Exception, NoSuchElementException, IllegalStateException {
/* 1158 */       synchronized (this.lock) {
/* 1159 */         return this.keyedPool.borrowObject(key);
/*      */       } 
/*      */     }
/*      */     
/*      */     public void returnObject(Object key, Object obj) {
/* 1164 */       synchronized (this.lock) {
/*      */         try {
/* 1166 */           this.keyedPool.returnObject(key, obj);
/* 1167 */         } catch (Exception e) {}
/*      */       } 
/*      */     }
/*      */     
/*      */     public void invalidateObject(Object key, Object obj) {
/* 1174 */       synchronized (this.lock) {
/*      */         try {
/* 1176 */           this.keyedPool.invalidateObject(key, obj);
/* 1177 */         } catch (Exception e) {}
/*      */       } 
/*      */     }
/*      */     
/*      */     public void addObject(Object key) throws Exception, IllegalStateException, UnsupportedOperationException {
/* 1184 */       synchronized (this.lock) {
/* 1185 */         this.keyedPool.addObject(key);
/*      */       } 
/*      */     }
/*      */     
/*      */     public int getNumIdle(Object key) throws UnsupportedOperationException {
/* 1190 */       synchronized (this.lock) {
/* 1191 */         return this.keyedPool.getNumIdle(key);
/*      */       } 
/*      */     }
/*      */     
/*      */     public int getNumActive(Object key) throws UnsupportedOperationException {
/* 1196 */       synchronized (this.lock) {
/* 1197 */         return this.keyedPool.getNumActive(key);
/*      */       } 
/*      */     }
/*      */     
/*      */     public int getNumIdle() throws UnsupportedOperationException {
/* 1202 */       synchronized (this.lock) {
/* 1203 */         return this.keyedPool.getNumIdle();
/*      */       } 
/*      */     }
/*      */     
/*      */     public int getNumActive() throws UnsupportedOperationException {
/* 1208 */       synchronized (this.lock) {
/* 1209 */         return this.keyedPool.getNumActive();
/*      */       } 
/*      */     }
/*      */     
/*      */     public void clear() throws Exception, UnsupportedOperationException {
/* 1214 */       synchronized (this.lock) {
/* 1215 */         this.keyedPool.clear();
/*      */       } 
/*      */     }
/*      */     
/*      */     public void clear(Object key) throws Exception, UnsupportedOperationException {
/* 1220 */       synchronized (this.lock) {
/* 1221 */         this.keyedPool.clear(key);
/*      */       } 
/*      */     }
/*      */     
/*      */     public void close() {
/*      */       try {
/* 1227 */         synchronized (this.lock) {
/* 1228 */           this.keyedPool.close();
/*      */         } 
/* 1230 */       } catch (Exception e) {}
/*      */     }
/*      */     
/*      */     public void setFactory(KeyedPoolableObjectFactory factory) throws IllegalStateException, UnsupportedOperationException {
/* 1236 */       synchronized (this.lock) {
/* 1237 */         this.keyedPool.setFactory(factory);
/*      */       } 
/*      */     }
/*      */     
/*      */     public String toString() {
/* 1242 */       StringBuffer sb = new StringBuffer();
/* 1243 */       sb.append("SynchronizedKeyedObjectPool");
/* 1244 */       sb.append("{keyedPool=").append(this.keyedPool);
/* 1245 */       sb.append('}');
/* 1246 */       return sb.toString();
/*      */     }
/*      */   }
/*      */   
/*      */   private static class SynchronizedPoolableObjectFactory implements PoolableObjectFactory {
/*      */     private final Object lock;
/*      */     
/*      */     private final PoolableObjectFactory factory;
/*      */     
/*      */     SynchronizedPoolableObjectFactory(PoolableObjectFactory factory) throws IllegalArgumentException {
/* 1255 */       if (factory == null)
/* 1256 */         throw new IllegalArgumentException("factory must not be null."); 
/* 1258 */       this.factory = factory;
/* 1259 */       this.lock = new Object();
/*      */     }
/*      */     
/*      */     public Object makeObject() throws Exception {
/* 1263 */       synchronized (this.lock) {
/* 1264 */         return this.factory.makeObject();
/*      */       } 
/*      */     }
/*      */     
/*      */     public void destroyObject(Object obj) throws Exception {
/* 1269 */       synchronized (this.lock) {
/* 1270 */         this.factory.destroyObject(obj);
/*      */       } 
/*      */     }
/*      */     
/*      */     public boolean validateObject(Object obj) {
/* 1275 */       synchronized (this.lock) {
/* 1276 */         return this.factory.validateObject(obj);
/*      */       } 
/*      */     }
/*      */     
/*      */     public void activateObject(Object obj) throws Exception {
/* 1281 */       synchronized (this.lock) {
/* 1282 */         this.factory.activateObject(obj);
/*      */       } 
/*      */     }
/*      */     
/*      */     public void passivateObject(Object obj) throws Exception {
/* 1287 */       synchronized (this.lock) {
/* 1288 */         this.factory.passivateObject(obj);
/*      */       } 
/*      */     }
/*      */     
/*      */     public String toString() {
/* 1293 */       StringBuffer sb = new StringBuffer();
/* 1294 */       sb.append("SynchronizedPoolableObjectFactory");
/* 1295 */       sb.append("{factory=").append(this.factory);
/* 1296 */       sb.append('}');
/* 1297 */       return sb.toString();
/*      */     }
/*      */   }
/*      */   
/*      */   private static class SynchronizedKeyedPoolableObjectFactory implements KeyedPoolableObjectFactory {
/*      */     private final Object lock;
/*      */     
/*      */     private final KeyedPoolableObjectFactory keyedFactory;
/*      */     
/*      */     SynchronizedKeyedPoolableObjectFactory(KeyedPoolableObjectFactory keyedFactory) throws IllegalArgumentException {
/* 1306 */       if (keyedFactory == null)
/* 1307 */         throw new IllegalArgumentException("keyedFactory must not be null."); 
/* 1309 */       this.keyedFactory = keyedFactory;
/* 1310 */       this.lock = new Object();
/*      */     }
/*      */     
/*      */     public Object makeObject(Object key) throws Exception {
/* 1314 */       synchronized (this.lock) {
/* 1315 */         return this.keyedFactory.makeObject(key);
/*      */       } 
/*      */     }
/*      */     
/*      */     public void destroyObject(Object key, Object obj) throws Exception {
/* 1320 */       synchronized (this.lock) {
/* 1321 */         this.keyedFactory.destroyObject(key, obj);
/*      */       } 
/*      */     }
/*      */     
/*      */     public boolean validateObject(Object key, Object obj) {
/* 1326 */       synchronized (this.lock) {
/* 1327 */         return this.keyedFactory.validateObject(key, obj);
/*      */       } 
/*      */     }
/*      */     
/*      */     public void activateObject(Object key, Object obj) throws Exception {
/* 1332 */       synchronized (this.lock) {
/* 1333 */         this.keyedFactory.activateObject(key, obj);
/*      */       } 
/*      */     }
/*      */     
/*      */     public void passivateObject(Object key, Object obj) throws Exception {
/* 1338 */       synchronized (this.lock) {
/* 1339 */         this.keyedFactory.passivateObject(key, obj);
/*      */       } 
/*      */     }
/*      */     
/*      */     public String toString() {
/* 1344 */       StringBuffer sb = new StringBuffer();
/* 1345 */       sb.append("SynchronizedKeyedPoolableObjectFactory");
/* 1346 */       sb.append("{keyedFactory=").append(this.keyedFactory);
/* 1347 */       sb.append('}');
/* 1348 */       return sb.toString();
/*      */     }
/*      */   }
/*      */   
/*      */   private static class ErodingFactor {
/*      */     private final float factor;
/*      */     
/*      */     private volatile transient long nextShrink;
/*      */     
/*      */     private volatile transient int idleHighWaterMark;
/*      */     
/*      */     public ErodingFactor(float factor) {
/* 1361 */       this.factor = factor;
/* 1362 */       this.nextShrink = System.currentTimeMillis() + (long)(900000.0F * factor);
/* 1363 */       this.idleHighWaterMark = 1;
/*      */     }
/*      */     
/*      */     public void update(int numIdle) {
/* 1367 */       update(System.currentTimeMillis(), numIdle);
/*      */     }
/*      */     
/*      */     public void update(long now, int numIdle) {
/* 1371 */       int idle = Math.max(0, numIdle);
/* 1372 */       this.idleHighWaterMark = Math.max(idle, this.idleHighWaterMark);
/* 1373 */       float maxInterval = 15.0F;
/* 1374 */       float minutes = 15.0F + -14.0F / this.idleHighWaterMark * idle;
/* 1375 */       this.nextShrink = now + (long)(minutes * 60000.0F * this.factor);
/*      */     }
/*      */     
/*      */     public long getNextShrink() {
/* 1379 */       return this.nextShrink;
/*      */     }
/*      */     
/*      */     public String toString() {
/* 1383 */       return "ErodingFactor{factor=" + this.factor + ", idleHighWaterMark=" + this.idleHighWaterMark + '}';
/*      */     }
/*      */   }
/*      */   
/*      */   private static class ErodingObjectPool implements ObjectPool {
/*      */     private final ObjectPool pool;
/*      */     
/*      */     private final PoolUtils.ErodingFactor factor;
/*      */     
/*      */     public ErodingObjectPool(ObjectPool pool, float factor) {
/* 1395 */       this.pool = pool;
/* 1396 */       this.factor = new PoolUtils.ErodingFactor(factor);
/*      */     }
/*      */     
/*      */     public Object borrowObject() throws Exception, NoSuchElementException, IllegalStateException {
/* 1400 */       return this.pool.borrowObject();
/*      */     }
/*      */     
/*      */     public void returnObject(Object obj) {
/* 1404 */       boolean discard = false;
/* 1405 */       long now = System.currentTimeMillis();
/* 1406 */       synchronized (this.pool) {
/* 1407 */         if (this.factor.getNextShrink() < now) {
/* 1408 */           int numIdle = this.pool.getNumIdle();
/* 1409 */           if (numIdle > 0)
/* 1410 */             discard = true; 
/* 1413 */           this.factor.update(now, numIdle);
/*      */         } 
/*      */       } 
/*      */       try {
/* 1417 */         if (discard) {
/* 1418 */           this.pool.invalidateObject(obj);
/*      */         } else {
/* 1420 */           this.pool.returnObject(obj);
/*      */         } 
/* 1422 */       } catch (Exception e) {}
/*      */     }
/*      */     
/*      */     public void invalidateObject(Object obj) {
/*      */       try {
/* 1429 */         this.pool.invalidateObject(obj);
/* 1430 */       } catch (Exception e) {}
/*      */     }
/*      */     
/*      */     public void addObject() throws Exception, IllegalStateException, UnsupportedOperationException {
/* 1436 */       this.pool.addObject();
/*      */     }
/*      */     
/*      */     public int getNumIdle() throws UnsupportedOperationException {
/* 1440 */       return this.pool.getNumIdle();
/*      */     }
/*      */     
/*      */     public int getNumActive() throws UnsupportedOperationException {
/* 1444 */       return this.pool.getNumActive();
/*      */     }
/*      */     
/*      */     public void clear() throws Exception, UnsupportedOperationException {
/* 1448 */       this.pool.clear();
/*      */     }
/*      */     
/*      */     public void close() {
/*      */       try {
/* 1453 */         this.pool.close();
/* 1454 */       } catch (Exception e) {}
/*      */     }
/*      */     
/*      */     public void setFactory(PoolableObjectFactory factory) throws IllegalStateException, UnsupportedOperationException {
/* 1460 */       this.pool.setFactory(factory);
/*      */     }
/*      */     
/*      */     public String toString() {
/* 1464 */       return "ErodingObjectPool{factor=" + this.factor + ", pool=" + this.pool + '}';
/*      */     }
/*      */   }
/*      */   
/*      */   private static class ErodingKeyedObjectPool implements KeyedObjectPool {
/*      */     private final KeyedObjectPool keyedPool;
/*      */     
/*      */     private final PoolUtils.ErodingFactor erodingFactor;
/*      */     
/*      */     public ErodingKeyedObjectPool(KeyedObjectPool keyedPool, float factor) {
/* 1476 */       this(keyedPool, new PoolUtils.ErodingFactor(factor));
/*      */     }
/*      */     
/*      */     protected ErodingKeyedObjectPool(KeyedObjectPool keyedPool, PoolUtils.ErodingFactor erodingFactor) {
/* 1480 */       if (keyedPool == null)
/* 1481 */         throw new IllegalArgumentException("keyedPool must not be null."); 
/* 1483 */       this.keyedPool = keyedPool;
/* 1484 */       this.erodingFactor = erodingFactor;
/*      */     }
/*      */     
/*      */     public Object borrowObject(Object key) throws Exception, NoSuchElementException, IllegalStateException {
/* 1488 */       return this.keyedPool.borrowObject(key);
/*      */     }
/*      */     
/*      */     public void returnObject(Object key, Object obj) throws Exception {
/* 1492 */       boolean discard = false;
/* 1493 */       long now = System.currentTimeMillis();
/* 1494 */       PoolUtils.ErodingFactor factor = getErodingFactor(key);
/* 1495 */       synchronized (this.keyedPool) {
/* 1496 */         if (factor.getNextShrink() < now) {
/* 1497 */           int numIdle = numIdle(key);
/* 1498 */           if (numIdle > 0)
/* 1499 */             discard = true; 
/* 1502 */           factor.update(now, numIdle);
/*      */         } 
/*      */       } 
/*      */       try {
/* 1506 */         if (discard) {
/* 1507 */           this.keyedPool.invalidateObject(key, obj);
/*      */         } else {
/* 1509 */           this.keyedPool.returnObject(key, obj);
/*      */         } 
/* 1511 */       } catch (Exception e) {}
/*      */     }
/*      */     
/*      */     protected int numIdle(Object key) {
/* 1517 */       return getKeyedPool().getNumIdle();
/*      */     }
/*      */     
/*      */     protected PoolUtils.ErodingFactor getErodingFactor(Object key) {
/* 1521 */       return this.erodingFactor;
/*      */     }
/*      */     
/*      */     public void invalidateObject(Object key, Object obj) {
/*      */       try {
/* 1526 */         this.keyedPool.invalidateObject(key, obj);
/* 1527 */       } catch (Exception e) {}
/*      */     }
/*      */     
/*      */     public void addObject(Object key) throws Exception, IllegalStateException, UnsupportedOperationException {
/* 1533 */       this.keyedPool.addObject(key);
/*      */     }
/*      */     
/*      */     public int getNumIdle() throws UnsupportedOperationException {
/* 1537 */       return this.keyedPool.getNumIdle();
/*      */     }
/*      */     
/*      */     public int getNumIdle(Object key) throws UnsupportedOperationException {
/* 1541 */       return this.keyedPool.getNumIdle(key);
/*      */     }
/*      */     
/*      */     public int getNumActive() throws UnsupportedOperationException {
/* 1545 */       return this.keyedPool.getNumActive();
/*      */     }
/*      */     
/*      */     public int getNumActive(Object key) throws UnsupportedOperationException {
/* 1549 */       return this.keyedPool.getNumActive(key);
/*      */     }
/*      */     
/*      */     public void clear() throws Exception, UnsupportedOperationException {
/* 1553 */       this.keyedPool.clear();
/*      */     }
/*      */     
/*      */     public void clear(Object key) throws Exception, UnsupportedOperationException {
/* 1557 */       this.keyedPool.clear(key);
/*      */     }
/*      */     
/*      */     public void close() {
/*      */       try {
/* 1562 */         this.keyedPool.close();
/* 1563 */       } catch (Exception e) {}
/*      */     }
/*      */     
/*      */     public void setFactory(KeyedPoolableObjectFactory factory) throws IllegalStateException, UnsupportedOperationException {
/* 1569 */       this.keyedPool.setFactory(factory);
/*      */     }
/*      */     
/*      */     protected KeyedObjectPool getKeyedPool() {
/* 1573 */       return this.keyedPool;
/*      */     }
/*      */     
/*      */     public String toString() {
/* 1577 */       return "ErodingKeyedObjectPool{erodingFactor=" + this.erodingFactor + ", keyedPool=" + this.keyedPool + '}';
/*      */     }
/*      */   }
/*      */   
/*      */   private static class ErodingPerKeyKeyedObjectPool extends ErodingKeyedObjectPool {
/*      */     private final float factor;
/*      */     
/* 1586 */     private final Map factors = Collections.synchronizedMap(new HashMap());
/*      */     
/*      */     public ErodingPerKeyKeyedObjectPool(KeyedObjectPool keyedPool, float factor) {
/* 1589 */       super(keyedPool, (PoolUtils.ErodingFactor)null);
/* 1590 */       this.factor = factor;
/*      */     }
/*      */     
/*      */     protected int numIdle(Object key) {
/* 1594 */       return getKeyedPool().getNumIdle(key);
/*      */     }
/*      */     
/*      */     protected PoolUtils.ErodingFactor getErodingFactor(Object key) {
/* 1598 */       PoolUtils.ErodingFactor factor = (PoolUtils.ErodingFactor)this.factors.get(key);
/* 1601 */       if (factor == null) {
/* 1602 */         factor = new PoolUtils.ErodingFactor(this.factor);
/* 1603 */         this.factors.put(key, factor);
/*      */       } 
/* 1605 */       return factor;
/*      */     }
/*      */     
/*      */     public String toString() {
/* 1609 */       return "ErodingPerKeyKeyedObjectPool{factor=" + this.factor + ", keyedPool=" + getKeyedPool() + '}';
/*      */     }
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\pool\PoolUtils.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */