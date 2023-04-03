/*     */ package org.openstreetmap.osmosis.core.store;
/*     */ 
/*     */ import java.io.File;
/*     */ import org.openstreetmap.osmosis.core.lifecycle.Completable;
/*     */ 
/*     */ public class IndexedObjectStore<T extends Storeable> implements Completable {
/*     */   private RandomAccessObjectStore<T> objectStore;
/*     */   
/*     */   private IndexStore<Long, LongLongIndexElement> indexStore;
/*     */   
/*     */   public IndexedObjectStore(ObjectSerializationFactory serializationFactory, String tmpFilePrefix) {
/*  30 */     this.objectStore = new RandomAccessObjectStore<T>(serializationFactory, tmpFilePrefix + "d");
/*  32 */     this.indexStore = new IndexStore<Long, LongLongIndexElement>(LongLongIndexElement.class, new ComparableComparator<Long>(), tmpFilePrefix + "i");
/*     */   }
/*     */   
/*     */   public IndexedObjectStore(ObjectSerializationFactory serializationFactory, File objectStorageFile, File indexStorageFile) {
/*  52 */     this.objectStore = new RandomAccessObjectStore<T>(serializationFactory, objectStorageFile);
/*  53 */     this.indexStore = new IndexStore<Long, LongLongIndexElement>(LongLongIndexElement.class, new ComparableComparator<Long>(), indexStorageFile);
/*     */   }
/*     */   
/*     */   public void add(long id, T data) {
/*  73 */     long objectOffset = this.objectStore.add(data);
/*  76 */     this.indexStore.write(new LongLongIndexElement(id, objectOffset));
/*     */   }
/*     */   
/*     */   public IndexedObjectStoreReader<T> createReader() {
/*  88 */     RandomAccessObjectStoreReader<T> objectStoreReader = null;
/*  90 */     objectStoreReader = this.objectStore.createReader();
/*     */     try {
/*  96 */       IndexStoreReader<Long, LongLongIndexElement> indexStoreReader = this.indexStore.createReader();
/*  98 */       IndexedObjectStoreReader<T> reader = new IndexedObjectStoreReader<T>(objectStoreReader, indexStoreReader);
/* 100 */       objectStoreReader = null;
/* 102 */       return reader;
/*     */     } finally {
/* 105 */       if (objectStoreReader != null)
/* 106 */         objectStoreReader.release(); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void complete() {
/* 117 */     this.objectStore.complete();
/* 118 */     this.indexStore.complete();
/*     */   }
/*     */   
/*     */   public void release() {
/* 126 */     this.objectStore.release();
/* 127 */     this.indexStore.release();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\store\IndexedObjectStore.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */