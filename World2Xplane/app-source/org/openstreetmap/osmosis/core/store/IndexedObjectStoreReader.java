/*    */ package org.openstreetmap.osmosis.core.store;
/*    */ 
/*    */ import org.openstreetmap.osmosis.core.lifecycle.Releasable;
/*    */ 
/*    */ public class IndexedObjectStoreReader<T> implements Releasable {
/*    */   private RandomAccessObjectStoreReader<T> objectStoreReader;
/*    */   
/*    */   private IndexStoreReader<Long, LongLongIndexElement> indexStoreReader;
/*    */   
/*    */   public IndexedObjectStoreReader(RandomAccessObjectStoreReader<T> objectStoreReader, IndexStoreReader<Long, LongLongIndexElement> indexStoreReader) {
/* 34 */     this.objectStoreReader = objectStoreReader;
/* 35 */     this.indexStoreReader = indexStoreReader;
/*    */   }
/*    */   
/*    */   public T get(long id) {
/* 51 */     long objectOffset = ((LongLongIndexElement)this.indexStoreReader.get(Long.valueOf(id))).getValue();
/* 54 */     T data = this.objectStoreReader.get(objectOffset);
/* 56 */     return data;
/*    */   }
/*    */   
/*    */   public void release() {
/* 65 */     this.objectStoreReader.release();
/* 66 */     this.indexStoreReader.release();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\store\IndexedObjectStoreReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */