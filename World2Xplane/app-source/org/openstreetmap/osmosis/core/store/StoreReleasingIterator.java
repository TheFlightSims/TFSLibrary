/*    */ package org.openstreetmap.osmosis.core.store;
/*    */ 
/*    */ import org.openstreetmap.osmosis.core.OsmosisRuntimeException;
/*    */ import org.openstreetmap.osmosis.core.lifecycle.Releasable;
/*    */ import org.openstreetmap.osmosis.core.lifecycle.ReleasableIterator;
/*    */ 
/*    */ public class StoreReleasingIterator<DataType> implements ReleasableIterator<DataType> {
/*    */   private Releasable store;
/*    */   
/*    */   private ReleasableIterator<DataType> iterator;
/*    */   
/*    */   public StoreReleasingIterator(ReleasableIterator<DataType> iterator, Releasable store) {
/* 32 */     this.iterator = iterator;
/* 33 */     this.store = store;
/*    */   }
/*    */   
/*    */   public boolean hasNext() {
/* 41 */     if (this.iterator == null)
/* 42 */       throw new OsmosisRuntimeException("Iterator has been released."); 
/* 45 */     return this.iterator.hasNext();
/*    */   }
/*    */   
/*    */   public DataType next() {
/* 53 */     if (this.iterator == null)
/* 54 */       throw new OsmosisRuntimeException("Iterator has been released."); 
/* 57 */     return (DataType)this.iterator.next();
/*    */   }
/*    */   
/*    */   public void remove() {
/* 65 */     if (this.iterator == null)
/* 66 */       throw new OsmosisRuntimeException("Iterator has been released."); 
/* 69 */     this.iterator.remove();
/*    */   }
/*    */   
/*    */   public void release() {
/* 77 */     if (this.iterator != null) {
/* 78 */       this.iterator.release();
/* 80 */       this.iterator = null;
/*    */     } 
/* 83 */     if (this.store != null) {
/* 84 */       this.store.release();
/* 86 */       this.store = null;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\store\StoreReleasingIterator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */