/*    */ package org.openstreetmap.osmosis.core.sort.v0_6;
/*    */ 
/*    */ import java.util.Comparator;
/*    */ import java.util.Map;
/*    */ import org.openstreetmap.osmosis.core.container.v0_6.EntityContainer;
/*    */ import org.openstreetmap.osmosis.core.lifecycle.ReleasableIterator;
/*    */ import org.openstreetmap.osmosis.core.sort.common.FileBasedSort;
/*    */ import org.openstreetmap.osmosis.core.store.GenericObjectSerializationFactory;
/*    */ import org.openstreetmap.osmosis.core.store.ObjectSerializationFactory;
/*    */ import org.openstreetmap.osmosis.core.store.Storeable;
/*    */ import org.openstreetmap.osmosis.core.task.v0_6.Sink;
/*    */ import org.openstreetmap.osmosis.core.task.v0_6.SinkSource;
/*    */ 
/*    */ public class EntitySorter implements SinkSource {
/*    */   private FileBasedSort<EntityContainer> fileBasedSort;
/*    */   
/*    */   private Sink sink;
/*    */   
/*    */   public EntitySorter(Comparator<EntityContainer> comparator) {
/* 33 */     this.fileBasedSort = new FileBasedSort((ObjectSerializationFactory)new GenericObjectSerializationFactory(), comparator, true);
/*    */   }
/*    */   
/*    */   public void initialize(Map<String, Object> metaData) {
/* 41 */     this.sink.initialize(metaData);
/*    */   }
/*    */   
/*    */   public void process(EntityContainer entityContainer) {
/* 49 */     this.fileBasedSort.add((Storeable)entityContainer);
/*    */   }
/*    */   
/*    */   public void setSink(Sink sink) {
/* 57 */     this.sink = sink;
/*    */   }
/*    */   
/*    */   public void complete() {
/* 65 */     ReleasableIterator<EntityContainer> iterator = null;
/*    */     try {
/* 68 */       iterator = this.fileBasedSort.iterate();
/* 70 */       while (iterator.hasNext())
/* 71 */         this.sink.process((EntityContainer)iterator.next()); 
/* 74 */       this.sink.complete();
/*    */     } finally {
/* 76 */       if (iterator != null)
/* 77 */         iterator.release(); 
/*    */     } 
/*    */   }
/*    */   
/*    */   public void release() {
/* 87 */     this.fileBasedSort.release();
/* 88 */     this.sink.release();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\sort\v0_6\EntitySorter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */