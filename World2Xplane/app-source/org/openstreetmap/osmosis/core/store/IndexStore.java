/*     */ package org.openstreetmap.osmosis.core.store;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.util.Comparator;
/*     */ import java.util.Iterator;
/*     */ import org.openstreetmap.osmosis.core.OsmosisRuntimeException;
/*     */ import org.openstreetmap.osmosis.core.lifecycle.Completable;
/*     */ import org.openstreetmap.osmosis.core.lifecycle.ReleasableIterator;
/*     */ import org.openstreetmap.osmosis.core.sort.common.FileBasedSort;
/*     */ 
/*     */ public class IndexStore<K, T extends IndexElement<K>> implements Completable {
/*     */   private ObjectSerializationFactory serializationFactory;
/*     */   
/*     */   private RandomAccessObjectStore<T> indexStore;
/*     */   
/*     */   private Comparator<K> ordering;
/*     */   
/*     */   private String tempFilePrefix;
/*     */   
/*     */   private File indexFile;
/*     */   
/*     */   private K previousKey;
/*     */   
/*     */   private boolean sorted;
/*     */   
/*     */   private long elementCount;
/*     */   
/*     */   private long elementSize;
/*     */   
/*     */   private boolean complete;
/*     */   
/*     */   public IndexStore(Class<T> elementType, Comparator<K> ordering, File indexFile) {
/*  50 */     this.ordering = ordering;
/*  51 */     this.indexFile = indexFile;
/*  53 */     this.serializationFactory = new SingleClassObjectSerializationFactory(elementType);
/*  55 */     this.indexStore = (RandomAccessObjectStore)new RandomAccessObjectStore<Storeable>(this.serializationFactory, indexFile);
/*  57 */     this.sorted = true;
/*  58 */     this.elementCount = 0L;
/*  59 */     this.elementSize = -1L;
/*  60 */     this.complete = false;
/*     */   }
/*     */   
/*     */   public IndexStore(Class<T> elementType, Comparator<K> ordering, String tempFilePrefix) {
/*  77 */     this.ordering = ordering;
/*  78 */     this.tempFilePrefix = tempFilePrefix;
/*  80 */     this.serializationFactory = new SingleClassObjectSerializationFactory(elementType);
/*  82 */     this.indexStore = (RandomAccessObjectStore)new RandomAccessObjectStore<Storeable>(this.serializationFactory, tempFilePrefix);
/*  84 */     this.sorted = true;
/*  85 */     this.elementCount = 0L;
/*  86 */     this.elementSize = -1L;
/*  87 */     this.complete = false;
/*     */   }
/*     */   
/*     */   public void write(T element) {
/* 101 */     if (this.complete)
/* 102 */       throw new OsmosisRuntimeException("Cannot write new data once reading has begun."); 
/* 105 */     long fileOffset = this.indexStore.add(element);
/* 107 */     K key = element.getKey();
/* 111 */     if (this.previousKey != null && 
/* 112 */       this.ordering.compare(this.previousKey, key) > 0)
/* 113 */       this.sorted = false; 
/* 116 */     this.previousKey = key;
/* 118 */     this.elementCount++;
/* 124 */     if (this.elementCount == 2L) {
/* 125 */       this.elementSize = fileOffset;
/* 126 */     } else if (this.elementCount > 2L) {
/* 129 */       long expectedOffset = (this.elementCount - 1L) * this.elementSize;
/* 131 */       if (expectedOffset != fileOffset)
/* 132 */         throw new OsmosisRuntimeException("Inconsistent element sizes, new file offset=" + fileOffset + ", expected offset=" + expectedOffset + ", element size=" + this.elementSize + ", element count=" + this.elementCount); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public IndexStoreReader<K, T> createReader() {
/* 151 */     return new IndexStoreReader<K, T>(this.indexStore.createReader(), this.ordering);
/*     */   }
/*     */   
/*     */   public void complete() {
/* 159 */     if (!this.complete) {
/* 160 */       this.indexStore.complete();
/* 162 */       if (!this.sorted) {
/* 163 */         final Comparator<K> keyOrdering = this.ordering;
/* 169 */         FileBasedSort<T> fileSort = new FileBasedSort(this.serializationFactory, new Comparator<T>() {
/* 172 */               private Comparator<K> elementKeyOrdering = keyOrdering;
/*     */               
/*     */               public int compare(T o1, T o2) {
/* 176 */                 return this.elementKeyOrdering.compare(o1.getKey(), o2.getKey());
/*     */               }
/*     */             }true);
/*     */         try {
/* 187 */           RandomAccessObjectStoreReader<T> indexStoreReader = this.indexStore.createReader();
/*     */           try {
/* 191 */             Iterator<T> indexIterator = indexStoreReader.iterate();
/* 193 */             while (indexIterator.hasNext())
/* 194 */               fileSort.add((Storeable)indexIterator.next()); 
/*     */           } finally {
/* 197 */             indexStoreReader.release();
/*     */           } 
/* 201 */           this.indexStore.release();
/* 202 */           if (this.indexFile != null) {
/* 203 */             this.indexStore = (RandomAccessObjectStore)new RandomAccessObjectStore<Storeable>(this.serializationFactory, this.indexFile);
/*     */           } else {
/* 205 */             this.indexStore = (RandomAccessObjectStore)new RandomAccessObjectStore<Storeable>(this.serializationFactory, this.tempFilePrefix);
/*     */           } 
/* 209 */           ReleasableIterator<T> sortIterator = fileSort.iterate();
/*     */           try {
/* 211 */             while (sortIterator.hasNext())
/* 212 */               this.indexStore.add((T)sortIterator.next()); 
/*     */           } finally {
/* 215 */             sortIterator.release();
/*     */           } 
/*     */         } finally {
/* 219 */           fileSort.release();
/*     */         } 
/*     */       } 
/* 223 */       this.complete = true;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void release() {
/* 232 */     this.indexStore.release();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\store\IndexStore.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */