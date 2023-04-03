/*     */ package org.openstreetmap.osmosis.core.store;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Comparator;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.openstreetmap.osmosis.core.lifecycle.Releasable;
/*     */ 
/*     */ public class IndexStoreReader<K, T extends IndexElement<K>> implements Releasable {
/*     */   private RandomAccessObjectStoreReader<T> indexStoreReader;
/*     */   
/*     */   private Comparator<K> ordering;
/*     */   
/*     */   private boolean elementDetailsInitialized;
/*     */   
/*     */   private long elementCount;
/*     */   
/*     */   private long elementSize;
/*     */   
/*     */   private long binarySearchElementCount;
/*     */   
/*     */   private int binarySearchDepth;
/*     */   
/*     */   private List<ComparisonElement<K>> binarySearchCache;
/*     */   
/*     */   public IndexStoreReader(RandomAccessObjectStoreReader<T> indexStoreReader, Comparator<K> ordering) {
/*  46 */     this.indexStoreReader = indexStoreReader;
/*  47 */     this.ordering = ordering;
/*  49 */     this.elementDetailsInitialized = false;
/*     */   }
/*     */   
/*     */   private void initializeElementDetails() {
/*  60 */     long dataLength = this.indexStoreReader.length();
/*  62 */     if (dataLength <= 0L) {
/*  63 */       this.elementCount = 0L;
/*  64 */       this.elementSize = 0L;
/*     */     } else {
/*  66 */       this.indexStoreReader.get(0L);
/*  67 */       this.elementSize = this.indexStoreReader.position();
/*  68 */       this.elementCount = dataLength / this.elementSize;
/*     */     } 
/*  72 */     this.binarySearchDepth = 0;
/*  73 */     this.binarySearchElementCount = 1L;
/*  75 */     while (this.binarySearchElementCount < this.elementCount + 1L) {
/*  76 */       this.binarySearchDepth++;
/*  77 */       this.binarySearchElementCount *= 2L;
/*     */     } 
/*  81 */     this.binarySearchCache = new ArrayList<ComparisonElement<K>>(this.binarySearchDepth);
/*  82 */     for (int i = 0; i < this.binarySearchDepth; i++)
/*  83 */       this.binarySearchCache.add(null); 
/*  86 */     this.elementDetailsInitialized = true;
/*     */   }
/*     */   
/*     */   private long getKeyIndex(K searchKey) {
/* 106 */     if (!this.elementDetailsInitialized)
/* 107 */       initializeElementDetails(); 
/* 110 */     long intervalBegin = -1L;
/* 111 */     long intervalEnd = this.binarySearchElementCount;
/* 112 */     int currentSearchDepth = 0;
/* 113 */     boolean useCache = true;
/* 114 */     boolean higherThanPrevious = true;
/* 115 */     while (intervalBegin + 1L < intervalEnd) {
/* 119 */       long intervalMid = (intervalBegin + intervalEnd) / 2L;
/* 123 */       if (intervalMid < this.elementCount) {
/* 124 */         K intervalMidKey = null;
/* 130 */         if (useCache) {
/* 131 */           ComparisonElement<K> searchElement = this.binarySearchCache.get(currentSearchDepth);
/* 133 */           if (searchElement != null && searchElement.getIndexOffset() == intervalMid) {
/* 134 */             intervalMidKey = searchElement.getKey();
/*     */           } else {
/* 136 */             useCache = false;
/*     */           } 
/*     */         } 
/* 142 */         if (!useCache)
/* 143 */           intervalMidKey = ((IndexElement<K>)this.indexStoreReader.get(intervalMid * this.elementSize)).getKey(); 
/* 147 */         boolean comparisonHigher = (this.ordering.compare(searchKey, intervalMidKey) > 0);
/* 149 */         if (!useCache)
/* 150 */           this.binarySearchCache.set(currentSearchDepth, new ComparisonElement<K>(intervalMid, intervalMidKey)); 
/* 153 */         higherThanPrevious = comparisonHigher;
/*     */       } else {
/* 156 */         higherThanPrevious = false;
/*     */       } 
/* 160 */       currentSearchDepth++;
/* 161 */       if (higherThanPrevious) {
/* 162 */         intervalBegin = intervalMid;
/*     */         continue;
/*     */       } 
/* 164 */       intervalEnd = intervalMid;
/*     */     } 
/* 168 */     return intervalEnd;
/*     */   }
/*     */   
/*     */   public T get(K key) {
/* 183 */     long keyIndex = getKeyIndex(key);
/* 185 */     if (keyIndex < this.elementCount) {
/* 189 */       IndexElement<K> indexElement = (IndexElement)this.indexStoreReader.get(keyIndex * this.elementSize);
/* 190 */       K locatedKey = indexElement.getKey();
/* 192 */       if (this.ordering.compare(key, locatedKey) == 0)
/* 193 */         return (T)indexElement; 
/*     */     } 
/* 197 */     throw new NoSuchIndexElementException("Requested key " + key + " does not exist.");
/*     */   }
/*     */   
/*     */   public Iterator<T> getRange(K beginKey, K endKey) {
/* 217 */     long keyIndex = getKeyIndex(beginKey);
/* 220 */     return new IndexRangeIterator<K, T>(this.indexStoreReader.iterate(keyIndex * this.elementSize), beginKey, endKey, this.ordering);
/*     */   }
/*     */   
/*     */   public void release() {
/* 234 */     this.indexStoreReader.release();
/*     */   }
/*     */   
/*     */   private static class ComparisonElement<K> {
/*     */     private long indexOffset;
/*     */     
/*     */     private K key;
/*     */     
/*     */     public ComparisonElement(long indexOffset, K key) {
/* 258 */       this.indexOffset = indexOffset;
/* 259 */       this.key = key;
/*     */     }
/*     */     
/*     */     public long getIndexOffset() {
/* 269 */       return this.indexOffset;
/*     */     }
/*     */     
/*     */     public K getKey() {
/* 279 */       return this.key;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\store\IndexStoreReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */