/*     */ package org.openstreetmap.osmosis.core.sort.common;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Comparator;
/*     */ import java.util.List;
/*     */ import org.openstreetmap.osmosis.core.lifecycle.Releasable;
/*     */ import org.openstreetmap.osmosis.core.lifecycle.ReleasableIterator;
/*     */ import org.openstreetmap.osmosis.core.store.ChunkedObjectStore;
/*     */ import org.openstreetmap.osmosis.core.store.ObjectSerializationFactory;
/*     */ import org.openstreetmap.osmosis.core.store.PersistentIterator;
/*     */ import org.openstreetmap.osmosis.core.store.Storeable;
/*     */ 
/*     */ public class FileBasedSort<T extends Storeable> implements Releasable {
/*     */   private static final int MAX_MEMORY_SORT_COUNT = 16384;
/*     */   
/*     */   private static final int MAX_MERGE_SOURCE_COUNT = 2;
/*     */   
/*     */   private static final int MAX_MEMORY_SORT_DEPTH = 8;
/*     */   
/*     */   private ObjectSerializationFactory serializationFactory;
/*     */   
/*     */   private Comparator<T> comparator;
/*     */   
/*     */   private ChunkedObjectStore<T> chunkedEntityStore;
/*     */   
/*     */   private List<T> addBuffer;
/*     */   
/*     */   private boolean useCompression;
/*     */   
/*     */   public FileBasedSort(ObjectSerializationFactory serializationFactory, Comparator<T> comparator, boolean useCompression) {
/*  73 */     this.serializationFactory = serializationFactory;
/*  74 */     this.comparator = comparator;
/*  75 */     this.useCompression = useCompression;
/*  77 */     this.chunkedEntityStore = new ChunkedObjectStore(serializationFactory, "emta", "idx", useCompression);
/*  78 */     this.addBuffer = new ArrayList<T>(16384);
/*     */   }
/*     */   
/*     */   private void flushAddBuffer() {
/*  87 */     if (this.addBuffer.size() >= 0) {
/*  89 */       Collections.sort(this.addBuffer, this.comparator);
/*  92 */       for (Storeable storeable : this.addBuffer)
/*  93 */         this.chunkedEntityStore.add(storeable); 
/*  96 */       this.addBuffer.clear();
/* 100 */       this.chunkedEntityStore.closeChunk();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void add(T value) {
/* 113 */     this.addBuffer.add(value);
/* 117 */     if (this.addBuffer.size() >= 16384)
/* 118 */       flushAddBuffer(); 
/*     */   }
/*     */   
/*     */   private ReleasableIterator<T> iteratePersisted(int nestLevel, long beginChunkIndex, long chunkCount) {
/* 143 */     PersistentIterator persistentIterator = new PersistentIterator(this.serializationFactory, iterate(nestLevel, beginChunkIndex, chunkCount), "emtb", this.useCompression);
/*     */     try {
/* 155 */       PersistentIterator persistentIterator1 = persistentIterator;
/* 159 */       persistentIterator.hasNext();
/* 161 */       persistentIterator = null;
/* 163 */       return (ReleasableIterator<T>)persistentIterator1;
/*     */     } finally {
/* 168 */       if (persistentIterator != null)
/* 169 */         persistentIterator.release(); 
/*     */     } 
/*     */   }
/*     */   
/*     */   private ReleasableIterator<T> iterate(int nestLevel, long beginChunkIndex, long chunkCount) {
/* 192 */     List<ReleasableIterator<T>> sources = new ArrayList<ReleasableIterator<T>>();
/*     */     try {
/* 199 */       if (chunkCount <= 2L) {
/* 200 */         for (int i = 0; i < chunkCount; i++)
/* 201 */           sources.add(this.chunkedEntityStore.iterate(beginChunkIndex + i)); 
/*     */       } else {
/* 218 */         long subChunkCount = chunkCount / 2L;
/* 219 */         subChunkCount += chunkCount % 2L;
/* 223 */         long maxChunkIndex = beginChunkIndex + chunkCount;
/* 226 */         long subFirstChunk = beginChunkIndex;
/* 227 */         for (; subFirstChunk < maxChunkIndex; 
/* 228 */           subFirstChunk += subChunkCount) {
/* 232 */           if (subFirstChunk + subChunkCount > maxChunkIndex)
/* 233 */             subChunkCount = maxChunkIndex - subFirstChunk; 
/* 244 */           if ((nestLevel + 1) % 8 == 0 && subChunkCount > 1L) {
/* 245 */             sources.add(iteratePersisted(nestLevel + 1, subFirstChunk, subChunkCount));
/*     */           } else {
/* 249 */             sources.add(iterate(nestLevel + 1, subFirstChunk, subChunkCount));
/*     */           } 
/*     */         } 
/*     */       } 
/* 257 */       MergingIterator<T> mergingIterator = new MergingIterator<T>(sources, this.comparator);
/* 261 */       sources.clear();
/* 263 */       return mergingIterator;
/*     */     } finally {
/* 266 */       for (ReleasableIterator<T> source : sources)
/* 267 */         source.release(); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public ReleasableIterator<T> iterate() {
/* 279 */     flushAddBuffer();
/* 281 */     return iterate(0, 0L, this.chunkedEntityStore.getChunkCount());
/*     */   }
/*     */   
/*     */   public void release() {
/* 289 */     this.chunkedEntityStore.release();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\sort\common\FileBasedSort.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */