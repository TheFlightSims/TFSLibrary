/*    */ package org.openstreetmap.osmosis.core.sort.v0_6;
/*    */ 
/*    */ import java.util.Comparator;
/*    */ import java.util.Map;
/*    */ import org.openstreetmap.osmosis.core.container.v0_6.ChangeContainer;
/*    */ import org.openstreetmap.osmosis.core.lifecycle.ReleasableIterator;
/*    */ import org.openstreetmap.osmosis.core.sort.common.FileBasedSort;
/*    */ import org.openstreetmap.osmosis.core.store.ObjectSerializationFactory;
/*    */ import org.openstreetmap.osmosis.core.store.SingleClassObjectSerializationFactory;
/*    */ import org.openstreetmap.osmosis.core.store.Storeable;
/*    */ import org.openstreetmap.osmosis.core.task.v0_6.ChangeSink;
/*    */ import org.openstreetmap.osmosis.core.task.v0_6.ChangeSinkChangeSource;
/*    */ 
/*    */ public class ChangeSorter implements ChangeSinkChangeSource {
/*    */   private FileBasedSort<ChangeContainer> fileBasedSort;
/*    */   
/*    */   private ChangeSink changeSink;
/*    */   
/*    */   public ChangeSorter(Comparator<ChangeContainer> comparator) {
/* 33 */     this.fileBasedSort = new FileBasedSort((ObjectSerializationFactory)new SingleClassObjectSerializationFactory(ChangeContainer.class), comparator, true);
/*    */   }
/*    */   
/*    */   public void initialize(Map<String, Object> metaData) {
/* 43 */     this.changeSink.initialize(metaData);
/*    */   }
/*    */   
/*    */   public void process(ChangeContainer change) {
/* 51 */     this.fileBasedSort.add((Storeable)change);
/*    */   }
/*    */   
/*    */   public void setChangeSink(ChangeSink changeSink) {
/* 59 */     this.changeSink = changeSink;
/*    */   }
/*    */   
/*    */   public void complete() {
/* 67 */     ReleasableIterator<ChangeContainer> iterator = null;
/*    */     try {
/* 70 */       iterator = this.fileBasedSort.iterate();
/* 72 */       while (iterator.hasNext())
/* 73 */         this.changeSink.process((ChangeContainer)iterator.next()); 
/* 76 */       this.changeSink.complete();
/*    */     } finally {
/* 78 */       if (iterator != null)
/* 79 */         iterator.release(); 
/*    */     } 
/*    */   }
/*    */   
/*    */   public void release() {
/* 89 */     this.fileBasedSort.release();
/* 90 */     this.changeSink.release();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\sort\v0_6\ChangeSorter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */