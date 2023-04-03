/*    */ package org.openstreetmap.osmosis.core.sort.v0_6;
/*    */ 
/*    */ import java.util.Comparator;
/*    */ import java.util.Map;
/*    */ import org.openstreetmap.osmosis.core.OsmosisRuntimeException;
/*    */ import org.openstreetmap.osmosis.core.container.v0_6.ChangeContainer;
/*    */ import org.openstreetmap.osmosis.core.task.v0_6.ChangeSink;
/*    */ import org.openstreetmap.osmosis.core.task.v0_6.ChangeSinkChangeSource;
/*    */ 
/*    */ public class SortedDeltaChangePipeValidator implements ChangeSinkChangeSource {
/*    */   private ChangeSink changeSink;
/*    */   
/* 30 */   private Comparator<ChangeContainer> comparator = new ChangeAsEntityComparator(new EntityContainerComparator(new EntityByTypeThenIdComparator()));
/*    */   
/*    */   private ChangeContainer previousChangeContainer;
/*    */   
/*    */   public void initialize(Map<String, Object> metaData) {
/* 38 */     this.changeSink.initialize(metaData);
/*    */   }
/*    */   
/*    */   public void complete() {
/* 46 */     this.changeSink.complete();
/*    */   }
/*    */   
/*    */   public void process(ChangeContainer changeContainer) {
/* 56 */     if (this.previousChangeContainer != null && 
/* 57 */       this.comparator.compare(this.previousChangeContainer, changeContainer) >= 0)
/* 58 */       throw new OsmosisRuntimeException("Pipeline entities are not sorted or contain multiple versions of a single entity, previous entity type=" + this.previousChangeContainer.getEntityContainer().getEntity().getType() + ", id=" + this.previousChangeContainer.getEntityContainer().getEntity().getId() + ", version=" + this.previousChangeContainer.getEntityContainer().getEntity().getVersion() + " current entity type=" + changeContainer.getEntityContainer().getEntity().getType() + ", id=" + changeContainer.getEntityContainer().getEntity().getId() + ", version=" + changeContainer.getEntityContainer().getEntity().getVersion() + "."); 
/* 70 */     this.changeSink.process(changeContainer);
/* 72 */     this.previousChangeContainer = changeContainer;
/*    */   }
/*    */   
/*    */   public void release() {
/* 80 */     this.changeSink.release();
/*    */   }
/*    */   
/*    */   public void setChangeSink(ChangeSink changeSink) {
/* 88 */     this.changeSink = changeSink;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\sort\v0_6\SortedDeltaChangePipeValidator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */