/*    */ package org.openstreetmap.osmosis.core.container.v0_6;
/*    */ 
/*    */ import org.openstreetmap.osmosis.core.domain.v0_6.Entity;
/*    */ import org.openstreetmap.osmosis.core.domain.v0_6.Relation;
/*    */ import org.openstreetmap.osmosis.core.store.StoreClassRegister;
/*    */ import org.openstreetmap.osmosis.core.store.StoreReader;
/*    */ import org.openstreetmap.osmosis.core.store.StoreWriter;
/*    */ 
/*    */ public class RelationContainer extends EntityContainer {
/*    */   private Relation relation;
/*    */   
/*    */   public RelationContainer(Relation relation) {
/* 27 */     this.relation = relation;
/*    */   }
/*    */   
/*    */   public RelationContainer(StoreReader sr, StoreClassRegister scr) {
/* 41 */     this.relation = new Relation(sr, scr);
/*    */   }
/*    */   
/*    */   public void store(StoreWriter sw, StoreClassRegister scr) {
/* 49 */     this.relation.store(sw, scr);
/*    */   }
/*    */   
/*    */   public void process(EntityProcessor processor) {
/* 58 */     processor.process(this);
/*    */   }
/*    */   
/*    */   public Relation getEntity() {
/* 67 */     return this.relation;
/*    */   }
/*    */   
/*    */   public RelationContainer getWriteableInstance() {
/* 76 */     if (this.relation.isReadOnly())
/* 77 */       return new RelationContainer(this.relation.getWriteableInstance()); 
/* 79 */     return this;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\container\v0_6\RelationContainer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */