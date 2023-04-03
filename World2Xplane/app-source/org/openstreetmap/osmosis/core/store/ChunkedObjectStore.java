/*     */ package org.openstreetmap.osmosis.core.store;
/*     */ 
/*     */ import org.openstreetmap.osmosis.core.lifecycle.Completable;
/*     */ import org.openstreetmap.osmosis.core.lifecycle.ReleasableIterator;
/*     */ 
/*     */ public class ChunkedObjectStore<T extends Storeable> implements Completable {
/*     */   private SegmentedObjectStore<T> objectStore;
/*     */   
/*     */   private IndexStore<Long, LongLongIndexElement> indexStore;
/*     */   
/*     */   private IndexStoreReader<Long, LongLongIndexElement> indexStoreReader;
/*     */   
/*     */   private long chunkCount;
/*     */   
/*     */   private boolean chunkInProgress;
/*     */   
/*     */   private long newChunkFilePosition;
/*     */   
/*     */   private long chunkObjectCount;
/*     */   
/*     */   public ChunkedObjectStore(ObjectSerializationFactory serializationFactory, String storageFilePrefix, String indexFilePrefix, boolean useCompression) {
/*  56 */     this.objectStore = new SegmentedObjectStore<T>(serializationFactory, storageFilePrefix, useCompression);
/*  58 */     this.indexStore = new IndexStore<Long, LongLongIndexElement>(LongLongIndexElement.class, new ComparableComparator<Long>(), indexFilePrefix);
/*  64 */     this.chunkCount = 0L;
/*  65 */     this.chunkInProgress = false;
/*  66 */     this.newChunkFilePosition = 0L;
/*  67 */     this.chunkObjectCount = 0L;
/*     */   }
/*     */   
/*     */   public void add(T data) {
/*  78 */     this.objectStore.add(data);
/*  79 */     this.chunkObjectCount++;
/*  81 */     if (!this.chunkInProgress) {
/*  83 */       this.indexStore.write(new LongLongIndexElement(this.chunkCount * 2L, this.newChunkFilePosition));
/*  85 */       this.chunkInProgress = true;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void closeChunk() {
/*  96 */     if (this.chunkInProgress) {
/*  99 */       this.newChunkFilePosition = this.objectStore.closeChunk();
/* 103 */       this.chunkInProgress = false;
/* 106 */       this.indexStore.write(new LongLongIndexElement(this.chunkCount * 2L + 1L, this.chunkObjectCount));
/* 107 */       this.chunkObjectCount = 0L;
/* 110 */       this.chunkCount++;
/*     */     } 
/*     */   }
/*     */   
/*     */   public long getChunkCount() {
/* 123 */     if (this.chunkInProgress)
/* 124 */       return this.chunkCount + 1L; 
/* 126 */     return this.chunkCount;
/*     */   }
/*     */   
/*     */   public ReleasableIterator<T> iterate(long chunk) {
/* 139 */     complete();
/* 141 */     if (this.indexStoreReader == null)
/* 142 */       this.indexStoreReader = this.indexStore.createReader(); 
/* 147 */     return this.objectStore.iterate(((LongLongIndexElement)this.indexStoreReader.get(Long.valueOf(chunk * 2L))).getValue(), ((LongLongIndexElement)this.indexStoreReader.get(Long.valueOf(chunk * 2L + 1L))).getValue());
/*     */   }
/*     */   
/*     */   public void complete() {
/* 160 */     closeChunk();
/* 162 */     this.indexStore.complete();
/*     */   }
/*     */   
/*     */   public void release() {
/* 170 */     this.objectStore.release();
/* 171 */     if (this.indexStoreReader != null) {
/* 172 */       this.indexStoreReader.release();
/* 173 */       this.indexStoreReader = null;
/*     */     } 
/* 175 */     this.indexStore.release();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\store\ChunkedObjectStore.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */