/*    */ package org.openstreetmap.osmosis.core.container.v0_6;
/*    */ 
/*    */ import org.openstreetmap.osmosis.core.domain.v0_6.Entity;
/*    */ import org.openstreetmap.osmosis.core.domain.v0_6.Way;
/*    */ import org.openstreetmap.osmosis.core.store.StoreClassRegister;
/*    */ import org.openstreetmap.osmosis.core.store.StoreReader;
/*    */ import org.openstreetmap.osmosis.core.store.StoreWriter;
/*    */ 
/*    */ public class WayContainer extends EntityContainer {
/*    */   private Way way;
/*    */   
/*    */   public WayContainer(Way way) {
/* 27 */     this.way = way;
/*    */   }
/*    */   
/*    */   public WayContainer(StoreReader sr, StoreClassRegister scr) {
/* 41 */     this.way = new Way(sr, scr);
/*    */   }
/*    */   
/*    */   public void store(StoreWriter sw, StoreClassRegister scr) {
/* 49 */     this.way.store(sw, scr);
/*    */   }
/*    */   
/*    */   public void process(EntityProcessor processor) {
/* 58 */     processor.process(this);
/*    */   }
/*    */   
/*    */   public Way getEntity() {
/* 67 */     return this.way;
/*    */   }
/*    */   
/*    */   public WayContainer getWriteableInstance() {
/* 76 */     if (this.way.isReadOnly())
/* 77 */       return new WayContainer(this.way.getWriteableInstance()); 
/* 79 */     return this;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\container\v0_6\WayContainer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */