/*     */ package org.openstreetmap.osmosis.core.store;
/*     */ 
/*     */ import java.util.NoSuchElementException;
/*     */ import org.openstreetmap.osmosis.core.lifecycle.ReleasableIterator;
/*     */ 
/*     */ public class PersistentIterator<T extends Storeable> implements ReleasableIterator<T> {
/*     */   private ReleasableIterator<T> sourceIterator;
/*     */   
/*     */   private SimpleObjectStore<T> store;
/*     */   
/*     */   private ReleasableIterator<T> storeIterator;
/*     */   
/*     */   private boolean initialized;
/*     */   
/*     */   public PersistentIterator(ObjectSerializationFactory serializationFactory, ReleasableIterator<T> sourceIterator, String storageFilePrefix, boolean useCompression) {
/*  45 */     this.sourceIterator = sourceIterator;
/*  47 */     this.store = new SimpleObjectStore<T>(serializationFactory, storageFilePrefix, useCompression);
/*  49 */     this.initialized = false;
/*     */   }
/*     */   
/*     */   private void initialize() {
/*  54 */     if (!this.initialized) {
/*  55 */       while (this.sourceIterator.hasNext())
/*  56 */         this.store.add((T)this.sourceIterator.next()); 
/*  58 */       this.sourceIterator.release();
/*  59 */       this.sourceIterator = null;
/*  61 */       this.storeIterator = this.store.iterate();
/*  63 */       this.initialized = true;
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean hasNext() {
/*  72 */     initialize();
/*  74 */     return this.storeIterator.hasNext();
/*     */   }
/*     */   
/*     */   public T next() {
/*  82 */     if (!hasNext())
/*  83 */       throw new NoSuchElementException(); 
/*  86 */     return (T)this.storeIterator.next();
/*     */   }
/*     */   
/*     */   public void release() {
/*  94 */     if (this.storeIterator != null) {
/*  95 */       this.storeIterator.release();
/*  96 */       this.storeIterator = null;
/*     */     } 
/*  99 */     this.store.release();
/* 101 */     if (this.sourceIterator != null) {
/* 102 */       this.sourceIterator.release();
/* 103 */       this.sourceIterator = null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void remove() {
/* 112 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\store\PersistentIterator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */