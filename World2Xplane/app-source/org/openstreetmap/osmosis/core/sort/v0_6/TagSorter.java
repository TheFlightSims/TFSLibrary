/*    */ package org.openstreetmap.osmosis.core.sort.v0_6;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collection;
/*    */ import java.util.Collections;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import org.openstreetmap.osmosis.core.container.v0_6.EntityContainer;
/*    */ import org.openstreetmap.osmosis.core.domain.v0_6.Entity;
/*    */ import org.openstreetmap.osmosis.core.domain.v0_6.Tag;
/*    */ import org.openstreetmap.osmosis.core.task.v0_6.Sink;
/*    */ import org.openstreetmap.osmosis.core.task.v0_6.SinkSource;
/*    */ 
/*    */ public class TagSorter implements SinkSource {
/*    */   private Sink sink;
/*    */   
/*    */   public void initialize(Map<String, Object> metaData) {
/* 31 */     this.sink.initialize(metaData);
/*    */   }
/*    */   
/*    */   public void process(EntityContainer entityContainer) {
/* 43 */     EntityContainer writeableContainer = entityContainer.getWriteableInstance();
/* 45 */     Entity entity = writeableContainer.getEntity();
/* 46 */     Collection<Tag> sortedTags = sortTags(entity.getTags());
/* 47 */     entity.getTags().clear();
/* 48 */     entity.getTags().addAll(sortedTags);
/* 50 */     this.sink.process(writeableContainer);
/*    */   }
/*    */   
/*    */   private List<Tag> sortTags(Collection<Tag> tagList) {
/* 64 */     List<Tag> sortedTagList = new ArrayList<Tag>(tagList);
/* 65 */     Collections.sort(sortedTagList);
/* 67 */     return sortedTagList;
/*    */   }
/*    */   
/*    */   public void setSink(Sink sink) {
/* 75 */     this.sink = sink;
/*    */   }
/*    */   
/*    */   public void complete() {
/* 83 */     this.sink.complete();
/*    */   }
/*    */   
/*    */   public void release() {
/* 91 */     this.sink.release();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\sort\v0_6\TagSorter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */