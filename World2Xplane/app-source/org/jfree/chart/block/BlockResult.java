/*    */ package org.jfree.chart.block;
/*    */ 
/*    */ import org.jfree.chart.entity.EntityCollection;
/*    */ 
/*    */ public class BlockResult implements EntityBlockResult {
/* 60 */   private EntityCollection entities = null;
/*    */   
/*    */   public EntityCollection getEntityCollection() {
/* 69 */     return this.entities;
/*    */   }
/*    */   
/*    */   public void setEntityCollection(EntityCollection entities) {
/* 78 */     this.entities = entities;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\block\BlockResult.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */