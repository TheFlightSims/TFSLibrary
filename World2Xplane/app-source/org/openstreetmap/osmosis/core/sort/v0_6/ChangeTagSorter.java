/*    */ package org.openstreetmap.osmosis.core.sort.v0_6;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collection;
/*    */ import java.util.Collections;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import org.openstreetmap.osmosis.core.container.v0_6.ChangeContainer;
/*    */ import org.openstreetmap.osmosis.core.container.v0_6.EntityContainer;
/*    */ import org.openstreetmap.osmosis.core.domain.v0_6.Entity;
/*    */ import org.openstreetmap.osmosis.core.domain.v0_6.Tag;
/*    */ import org.openstreetmap.osmosis.core.task.v0_6.ChangeSink;
/*    */ import org.openstreetmap.osmosis.core.task.v0_6.ChangeSinkChangeSource;
/*    */ 
/*    */ public class ChangeTagSorter implements ChangeSinkChangeSource {
/*    */   private ChangeSink changeSink;
/*    */   
/*    */   public void initialize(Map<String, Object> metaData) {
/* 32 */     this.changeSink.initialize(metaData);
/*    */   }
/*    */   
/*    */   public void process(ChangeContainer changeContainer) {
/* 45 */     EntityContainer readOnlyContainer = changeContainer.getEntityContainer();
/* 46 */     EntityContainer writeableContainer = readOnlyContainer.getWriteableInstance();
/* 48 */     Entity entity = writeableContainer.getEntity();
/* 49 */     Collection<Tag> sortedTags = sortTags(entity.getTags());
/* 50 */     entity.getTags().clear();
/* 51 */     entity.getTags().addAll(sortedTags);
/* 53 */     this.changeSink.process(new ChangeContainer(writeableContainer, changeContainer.getAction()));
/*    */   }
/*    */   
/*    */   private List<Tag> sortTags(Collection<Tag> tagList) {
/* 67 */     List<Tag> sortedTagList = new ArrayList<Tag>(tagList);
/* 68 */     Collections.sort(sortedTagList);
/* 70 */     return sortedTagList;
/*    */   }
/*    */   
/*    */   public void setChangeSink(ChangeSink changeSink) {
/* 78 */     this.changeSink = changeSink;
/*    */   }
/*    */   
/*    */   public void complete() {
/* 86 */     this.changeSink.complete();
/*    */   }
/*    */   
/*    */   public void release() {
/* 94 */     this.changeSink.release();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\sort\v0_6\ChangeTagSorter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */