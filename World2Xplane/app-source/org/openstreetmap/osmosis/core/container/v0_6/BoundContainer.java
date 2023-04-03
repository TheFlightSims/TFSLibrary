/*    */ package org.openstreetmap.osmosis.core.container.v0_6;
/*    */ 
/*    */ import org.openstreetmap.osmosis.core.domain.v0_6.Bound;
/*    */ import org.openstreetmap.osmosis.core.domain.v0_6.Entity;
/*    */ import org.openstreetmap.osmosis.core.store.StoreClassRegister;
/*    */ import org.openstreetmap.osmosis.core.store.StoreReader;
/*    */ import org.openstreetmap.osmosis.core.store.StoreWriter;
/*    */ 
/*    */ public class BoundContainer extends EntityContainer {
/*    */   private Bound bound;
/*    */   
/*    */   public BoundContainer(Bound bound) {
/* 27 */     this.bound = bound;
/*    */   }
/*    */   
/*    */   public BoundContainer(StoreReader sr, StoreClassRegister scr) {
/* 41 */     this.bound = new Bound(sr, scr);
/*    */   }
/*    */   
/*    */   public void store(StoreWriter sw, StoreClassRegister scr) {
/* 50 */     this.bound.store(sw, scr);
/*    */   }
/*    */   
/*    */   public void process(EntityProcessor processor) {
/* 59 */     processor.process(this);
/*    */   }
/*    */   
/*    */   public Bound getEntity() {
/* 68 */     return this.bound;
/*    */   }
/*    */   
/*    */   public BoundContainer getWriteableInstance() {
/* 77 */     if (this.bound.isReadOnly())
/* 78 */       return new BoundContainer(this.bound.getWriteableInstance()); 
/* 80 */     return this;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\container\v0_6\BoundContainer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */