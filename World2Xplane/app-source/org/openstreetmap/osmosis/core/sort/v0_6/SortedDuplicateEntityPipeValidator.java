/*    */ package org.openstreetmap.osmosis.core.sort.v0_6;
/*    */ 
/*    */ import java.util.Map;
/*    */ import org.openstreetmap.osmosis.core.OsmosisRuntimeException;
/*    */ import org.openstreetmap.osmosis.core.container.v0_6.EntityContainer;
/*    */ import org.openstreetmap.osmosis.core.task.v0_6.Sink;
/*    */ import org.openstreetmap.osmosis.core.task.v0_6.SinkSource;
/*    */ 
/*    */ public class SortedDuplicateEntityPipeValidator implements SinkSource {
/*    */   private Sink sink;
/*    */   
/* 29 */   private EntityContainerComparator comparator = new EntityContainerComparator(new EntityByTypeThenIdComparator());
/*    */   
/*    */   private EntityContainer previousEntityContainer;
/*    */   
/*    */   public void initialize(Map<String, Object> metaData) {
/* 37 */     this.sink.initialize(metaData);
/*    */   }
/*    */   
/*    */   public void complete() {
/* 45 */     this.sink.complete();
/*    */   }
/*    */   
/*    */   public void process(EntityContainer entityContainer) {
/* 55 */     if (this.previousEntityContainer != null && 
/* 56 */       this.comparator.compare(this.previousEntityContainer, entityContainer) > 0)
/* 57 */       throw new OsmosisRuntimeException("Pipeline entities are not sorted, previous entity type=" + this.previousEntityContainer.getEntity().getType() + ", id=" + this.previousEntityContainer.getEntity().getId() + ", version=" + this.previousEntityContainer.getEntity().getVersion() + " current entity type=" + entityContainer.getEntity().getType() + ", id=" + entityContainer.getEntity().getId() + ", version=" + entityContainer.getEntity().getVersion() + "."); 
/* 69 */     this.sink.process(entityContainer);
/* 71 */     this.previousEntityContainer = entityContainer;
/*    */   }
/*    */   
/*    */   public void release() {
/* 79 */     this.sink.release();
/*    */   }
/*    */   
/*    */   public void setSink(Sink sink) {
/* 87 */     this.sink = sink;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\sort\v0_6\SortedDuplicateEntityPipeValidator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */