/*     */ package org.mapdb;
/*     */ 
/*     */ import java.io.IOError;
/*     */ import java.io.IOException;
/*     */ import java.util.Arrays;
/*     */ import java.util.Iterator;
/*     */ import java.util.concurrent.atomic.AtomicBoolean;
/*     */ 
/*     */ public class EngineWrapper implements Engine {
/*     */   private Engine engine;
/*     */   
/*     */   protected EngineWrapper(Engine engine) {
/*  37 */     if (engine == null)
/*  37 */       throw new IllegalArgumentException(); 
/*  38 */     this.engine = engine;
/*     */   }
/*     */   
/*     */   public long preallocate() {
/*  43 */     return getWrappedEngine().preallocate();
/*     */   }
/*     */   
/*     */   public void preallocate(long[] recids) {
/*  48 */     getWrappedEngine().preallocate(recids);
/*     */   }
/*     */   
/*     */   public <A> long put(A value, Serializer<A> serializer) {
/*  53 */     return getWrappedEngine().put(value, serializer);
/*     */   }
/*     */   
/*     */   public <A> A get(long recid, Serializer<A> serializer) {
/*  58 */     return getWrappedEngine().get(recid, serializer);
/*     */   }
/*     */   
/*     */   public <A> void update(long recid, A value, Serializer<A> serializer) {
/*  63 */     getWrappedEngine().update(recid, value, serializer);
/*     */   }
/*     */   
/*     */   public <A> boolean compareAndSwap(long recid, A expectedOldValue, A newValue, Serializer<A> serializer) {
/*  68 */     return getWrappedEngine().compareAndSwap(recid, expectedOldValue, newValue, serializer);
/*     */   }
/*     */   
/*     */   public <A> void delete(long recid, Serializer<A> serializer) {
/*  73 */     getWrappedEngine().delete(recid, serializer);
/*     */   }
/*     */   
/*     */   public void close() {
/*  78 */     Engine e = this.engine;
/*     */     try {
/*  80 */       if (e != null)
/*  81 */         e.close(); 
/*     */     } finally {
/*  83 */       this.engine = CLOSED;
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean isClosed() {
/*  89 */     return (this.engine == CLOSED || this.engine == null);
/*     */   }
/*     */   
/*     */   public void commit() {
/*  94 */     getWrappedEngine().commit();
/*     */   }
/*     */   
/*     */   public void rollback() {
/*  99 */     getWrappedEngine().rollback();
/*     */   }
/*     */   
/*     */   public boolean isReadOnly() {
/* 105 */     return getWrappedEngine().isReadOnly();
/*     */   }
/*     */   
/*     */   public boolean canRollback() {
/* 110 */     return getWrappedEngine().canRollback();
/*     */   }
/*     */   
/*     */   public boolean canSnapshot() {
/* 115 */     return getWrappedEngine().canSnapshot();
/*     */   }
/*     */   
/*     */   public Engine snapshot() throws UnsupportedOperationException {
/* 120 */     return getWrappedEngine().snapshot();
/*     */   }
/*     */   
/*     */   public void clearCache() {
/* 125 */     getWrappedEngine().clearCache();
/*     */   }
/*     */   
/*     */   public void compact() {
/* 130 */     getWrappedEngine().compact();
/*     */   }
/*     */   
/*     */   public SerializerPojo getSerializerPojo() {
/* 135 */     return getWrappedEngine().getSerializerPojo();
/*     */   }
/*     */   
/*     */   public void closeListenerRegister(Runnable closeListener) {
/* 140 */     getWrappedEngine().closeListenerRegister(closeListener);
/*     */   }
/*     */   
/*     */   public void closeListenerUnregister(Runnable closeListener) {
/* 146 */     getWrappedEngine().closeListenerUnregister(closeListener);
/*     */   }
/*     */   
/*     */   public Engine getWrappedEngine() {
/* 151 */     return checkClosed(this.engine);
/*     */   }
/*     */   
/*     */   protected static <V> V checkClosed(V v) {
/* 155 */     if (v == null)
/* 155 */       throw new IllegalAccessError("DB has been closed"); 
/* 156 */     return v;
/*     */   }
/*     */   
/*     */   public static class ReadOnlyEngine extends EngineWrapper {
/*     */     public ReadOnlyEngine(Engine engine) {
/* 169 */       super(engine);
/*     */     }
/*     */     
/*     */     public long preallocate() {
/* 175 */       throw new UnsupportedOperationException("Read-only");
/*     */     }
/*     */     
/*     */     public void preallocate(long[] recids) {
/* 180 */       throw new UnsupportedOperationException("Read-only");
/*     */     }
/*     */     
/*     */     public <A> boolean compareAndSwap(long recid, A expectedOldValue, A newValue, Serializer<A> serializer) {
/* 186 */       throw new UnsupportedOperationException("Read-only");
/*     */     }
/*     */     
/*     */     public <A> long put(A value, Serializer<A> serializer) {
/* 191 */       throw new UnsupportedOperationException("Read-only");
/*     */     }
/*     */     
/*     */     public <A> void update(long recid, A value, Serializer<A> serializer) {
/* 196 */       throw new UnsupportedOperationException("Read-only");
/*     */     }
/*     */     
/*     */     public <A> void delete(long recid, Serializer<A> serializer) {
/* 201 */       throw new UnsupportedOperationException("Read-only");
/*     */     }
/*     */     
/*     */     public void commit() {
/* 206 */       throw new UnsupportedOperationException("Read-only");
/*     */     }
/*     */     
/*     */     public void rollback() {
/* 211 */       throw new UnsupportedOperationException("Read-only");
/*     */     }
/*     */     
/*     */     public boolean isReadOnly() {
/* 217 */       return true;
/*     */     }
/*     */     
/*     */     public boolean canSnapshot() {
/* 222 */       return true;
/*     */     }
/*     */     
/*     */     public Engine snapshot() throws UnsupportedOperationException {
/* 227 */       return this;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class ImmutabilityCheckEngine extends EngineWrapper {
/*     */     protected static class Item {
/*     */       final Serializer serializer;
/*     */       
/*     */       final Object item;
/*     */       
/*     */       final int oldChecksum;
/*     */       
/*     */       public Item(Serializer serializer, Object item) {
/* 245 */         if (item == null || serializer == null)
/* 245 */           throw new AssertionError("null"); 
/* 246 */         this.serializer = serializer;
/* 247 */         this.item = item;
/* 248 */         this.oldChecksum = checksum();
/* 249 */         if (this.oldChecksum != checksum())
/* 249 */           throw new AssertionError("inconsistent serialization"); 
/*     */       }
/*     */       
/*     */       private int checksum() {
/*     */         try {
/* 254 */           DataOutput2 out = new DataOutput2();
/* 255 */           this.serializer.serialize(out, this.item);
/* 256 */           byte[] bb = out.copyBytes();
/* 257 */           return Arrays.hashCode(bb);
/* 258 */         } catch (IOException e) {
/* 259 */           throw new IOError(e);
/*     */         } 
/*     */       }
/*     */       
/*     */       void check() {
/* 264 */         int newChecksum = checksum();
/* 265 */         if (this.oldChecksum != newChecksum)
/* 265 */           throw new AssertionError("Record instance was modified: \n  " + this.item + "\n  " + this.serializer); 
/*     */       }
/*     */     }
/*     */     
/* 269 */     protected LongConcurrentHashMap<Item> items = new LongConcurrentHashMap<Item>();
/*     */     
/*     */     protected ImmutabilityCheckEngine(Engine engine) {
/* 272 */       super(engine);
/*     */     }
/*     */     
/*     */     public <A> A get(long recid, Serializer<A> serializer) {
/* 277 */       Item item = this.items.get(recid);
/* 278 */       if (item != null)
/* 278 */         item.check(); 
/* 279 */       A ret = super.get(recid, serializer);
/* 280 */       if (ret != null)
/* 280 */         this.items.put(recid, new Item(serializer, ret)); 
/* 281 */       return ret;
/*     */     }
/*     */     
/*     */     public <A> long put(A value, Serializer<A> serializer) {
/* 286 */       long ret = super.put(value, serializer);
/* 287 */       if (value != null)
/* 287 */         this.items.put(ret, new Item(serializer, value)); 
/* 288 */       return ret;
/*     */     }
/*     */     
/*     */     public <A> void update(long recid, A value, Serializer<A> serializer) {
/* 293 */       Item item = this.items.get(recid);
/* 294 */       if (item != null)
/* 294 */         item.check(); 
/* 295 */       super.update(recid, value, serializer);
/* 296 */       if (value != null)
/* 296 */         this.items.put(recid, new Item(serializer, value)); 
/*     */     }
/*     */     
/*     */     public <A> boolean compareAndSwap(long recid, A expectedOldValue, A newValue, Serializer<A> serializer) {
/* 301 */       Item item = this.items.get(recid);
/* 302 */       if (item != null)
/* 302 */         item.check(); 
/* 303 */       boolean ret = super.compareAndSwap(recid, expectedOldValue, newValue, serializer);
/* 304 */       if (ret && newValue != null)
/* 304 */         this.items.put(recid, new Item(serializer, item)); 
/* 305 */       return ret;
/*     */     }
/*     */     
/*     */     public void close() {
/* 310 */       super.close();
/* 311 */       for (Iterator<Item> iter = this.items.valuesIterator(); iter.hasNext();)
/* 312 */         ((Item)iter.next()).check(); 
/* 314 */       this.items.clear();
/*     */     }
/*     */   }
/*     */   
/*     */   public static class SynchronizedEngineWrapper extends EngineWrapper {
/*     */     protected SynchronizedEngineWrapper(Engine engine) {
/* 323 */       super(engine);
/*     */     }
/*     */     
/*     */     public synchronized long preallocate() {
/* 328 */       return super.preallocate();
/*     */     }
/*     */     
/*     */     public synchronized void preallocate(long[] recids) {
/* 333 */       super.preallocate(recids);
/*     */     }
/*     */     
/*     */     public synchronized <A> long put(A value, Serializer<A> serializer) {
/* 339 */       return super.put(value, serializer);
/*     */     }
/*     */     
/*     */     public synchronized <A> A get(long recid, Serializer<A> serializer) {
/* 344 */       return super.get(recid, serializer);
/*     */     }
/*     */     
/*     */     public synchronized <A> void update(long recid, A value, Serializer<A> serializer) {
/* 349 */       super.update(recid, value, serializer);
/*     */     }
/*     */     
/*     */     public synchronized <A> boolean compareAndSwap(long recid, A expectedOldValue, A newValue, Serializer<A> serializer) {
/* 354 */       return super.compareAndSwap(recid, expectedOldValue, newValue, serializer);
/*     */     }
/*     */     
/*     */     public synchronized <A> void delete(long recid, Serializer<A> serializer) {
/* 359 */       super.delete(recid, serializer);
/*     */     }
/*     */     
/*     */     public synchronized void close() {
/* 364 */       super.close();
/*     */     }
/*     */     
/*     */     public synchronized boolean isClosed() {
/* 369 */       return super.isClosed();
/*     */     }
/*     */     
/*     */     public synchronized void commit() {
/* 374 */       super.commit();
/*     */     }
/*     */     
/*     */     public synchronized void rollback() {
/* 379 */       super.rollback();
/*     */     }
/*     */     
/*     */     public synchronized boolean isReadOnly() {
/* 384 */       return super.isReadOnly();
/*     */     }
/*     */     
/*     */     public synchronized boolean canSnapshot() {
/* 389 */       return super.canSnapshot();
/*     */     }
/*     */     
/*     */     public synchronized Engine snapshot() throws UnsupportedOperationException {
/* 394 */       return super.snapshot();
/*     */     }
/*     */     
/*     */     public synchronized void compact() {
/* 399 */       super.compact();
/*     */     }
/*     */   }
/*     */   
/*     */   public static class SerializerCheckEngineWrapper extends EngineWrapper {
/* 407 */     protected LongMap<Serializer> recid2serializer = new LongConcurrentHashMap<Serializer>();
/*     */     
/*     */     protected SerializerCheckEngineWrapper(Engine engine) {
/* 410 */       super(engine);
/*     */     }
/*     */     
/*     */     protected synchronized <A> void checkSerializer(long recid, Serializer<A> serializer) {
/* 415 */       Serializer<A> other = this.recid2serializer.get(recid);
/* 416 */       if (other != null) {
/* 417 */         if (other != serializer && other.getClass() != serializer.getClass())
/* 418 */           throw new IllegalArgumentException("Serializer does not match. \n found: " + serializer + " \n expected: " + other); 
/*     */       } else {
/* 420 */         this.recid2serializer.put(recid, serializer);
/*     */       } 
/*     */     }
/*     */     
/*     */     public <A> A get(long recid, Serializer<A> serializer) {
/* 425 */       checkSerializer(recid, serializer);
/* 426 */       return super.get(recid, serializer);
/*     */     }
/*     */     
/*     */     public <A> void update(long recid, A value, Serializer<A> serializer) {
/* 432 */       checkSerializer(recid, serializer);
/* 433 */       super.update(recid, value, serializer);
/*     */     }
/*     */     
/*     */     public <A> boolean compareAndSwap(long recid, A expectedOldValue, A newValue, Serializer<A> serializer) {
/* 438 */       checkSerializer(recid, serializer);
/* 439 */       return super.compareAndSwap(recid, expectedOldValue, newValue, serializer);
/*     */     }
/*     */     
/*     */     public <A> void delete(long recid, Serializer<A> serializer) {
/* 444 */       checkSerializer(recid, serializer);
/* 445 */       this.recid2serializer.remove(recid);
/* 446 */       super.delete(recid, serializer);
/*     */     }
/*     */   }
/*     */   
/* 452 */   public static final Engine CLOSED = new Engine() {
/*     */       public long preallocate() {
/* 457 */         throw new IllegalAccessError("already closed");
/*     */       }
/*     */       
/*     */       public void preallocate(long[] recids) {
/* 462 */         throw new IllegalAccessError("already closed");
/*     */       }
/*     */       
/*     */       public <A> long put(A value, Serializer<A> serializer) {
/* 467 */         throw new IllegalAccessError("already closed");
/*     */       }
/*     */       
/*     */       public <A> A get(long recid, Serializer<A> serializer) {
/* 472 */         throw new IllegalAccessError("already closed");
/*     */       }
/*     */       
/*     */       public <A> void update(long recid, A value, Serializer<A> serializer) {
/* 477 */         throw new IllegalAccessError("already closed");
/*     */       }
/*     */       
/*     */       public <A> boolean compareAndSwap(long recid, A expectedOldValue, A newValue, Serializer<A> serializer) {
/* 482 */         throw new IllegalAccessError("already closed");
/*     */       }
/*     */       
/*     */       public <A> void delete(long recid, Serializer<A> serializer) {
/* 487 */         throw new IllegalAccessError("already closed");
/*     */       }
/*     */       
/*     */       public void close() {
/* 492 */         throw new IllegalAccessError("already closed");
/*     */       }
/*     */       
/*     */       public boolean isClosed() {
/* 497 */         return true;
/*     */       }
/*     */       
/*     */       public void commit() {
/* 502 */         throw new IllegalAccessError("already closed");
/*     */       }
/*     */       
/*     */       public void rollback() throws UnsupportedOperationException {
/* 507 */         throw new IllegalAccessError("already closed");
/*     */       }
/*     */       
/*     */       public boolean isReadOnly() {
/* 512 */         throw new IllegalAccessError("already closed");
/*     */       }
/*     */       
/*     */       public boolean canRollback() {
/* 517 */         throw new IllegalAccessError("already closed");
/*     */       }
/*     */       
/*     */       public boolean canSnapshot() {
/* 522 */         throw new IllegalAccessError("already closed");
/*     */       }
/*     */       
/*     */       public Engine snapshot() throws UnsupportedOperationException {
/* 527 */         throw new IllegalAccessError("already closed");
/*     */       }
/*     */       
/*     */       public void clearCache() {
/* 532 */         throw new IllegalAccessError("already closed");
/*     */       }
/*     */       
/*     */       public void compact() {
/* 537 */         throw new IllegalAccessError("already closed");
/*     */       }
/*     */       
/*     */       public SerializerPojo getSerializerPojo() {
/* 542 */         throw new IllegalAccessError("already closed");
/*     */       }
/*     */       
/*     */       public void closeListenerRegister(Runnable closeListener) {
/* 547 */         throw new IllegalAccessError("already closed");
/*     */       }
/*     */       
/*     */       public void closeListenerUnregister(Runnable closeListener) {}
/*     */     };
/*     */   
/*     */   public static class CloseOnJVMShutdown extends EngineWrapper {
/* 562 */     protected final AtomicBoolean shutdownHappened = new AtomicBoolean(false);
/*     */     
/* 564 */     final Runnable hookRunnable = new Runnable() {
/*     */         public void run() {
/* 567 */           EngineWrapper.CloseOnJVMShutdown.this.shutdownHappened.set(true);
/* 568 */           EngineWrapper.CloseOnJVMShutdown.this.hook = null;
/* 569 */           if (EngineWrapper.CloseOnJVMShutdown.this.isClosed())
/*     */             return; 
/* 571 */           EngineWrapper.CloseOnJVMShutdown.this.close();
/*     */         }
/*     */       };
/*     */     
/*     */     Thread hook;
/*     */     
/*     */     public CloseOnJVMShutdown(Engine engine) {
/* 579 */       super(engine);
/* 580 */       this.hook = new Thread(this.hookRunnable, "MapDB shutdown hook");
/* 581 */       Runtime.getRuntime().addShutdownHook(this.hook);
/*     */     }
/*     */     
/*     */     public void close() {
/* 586 */       super.close();
/* 587 */       if (!this.shutdownHappened.get() && this.hook != null)
/* 588 */         Runtime.getRuntime().removeShutdownHook(this.hook); 
/* 590 */       this.hook = null;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\mapdb\EngineWrapper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */