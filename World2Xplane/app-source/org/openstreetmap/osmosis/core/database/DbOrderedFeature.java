/*    */ package org.openstreetmap.osmosis.core.database;
/*    */ 
/*    */ import org.openstreetmap.osmosis.core.store.StoreClassRegister;
/*    */ import org.openstreetmap.osmosis.core.store.StoreReader;
/*    */ import org.openstreetmap.osmosis.core.store.StoreWriter;
/*    */ import org.openstreetmap.osmosis.core.store.Storeable;
/*    */ 
/*    */ public class DbOrderedFeature<T extends Storeable> extends DbFeature<T> {
/*    */   private int sequenceId;
/*    */   
/*    */   public DbOrderedFeature(long entityId, T feature, int sequenceId) {
/* 34 */     super(entityId, feature);
/* 36 */     this.sequenceId = sequenceId;
/*    */   }
/*    */   
/*    */   public DbOrderedFeature(StoreReader sr, StoreClassRegister scr) {
/* 50 */     super(sr, scr);
/* 51 */     this.sequenceId = sr.readInteger();
/*    */   }
/*    */   
/*    */   public void store(StoreWriter sw, StoreClassRegister scr) {
/* 60 */     super.store(sw, scr);
/* 61 */     sw.writeInteger(this.sequenceId);
/*    */   }
/*    */   
/*    */   public int getSequenceId() {
/* 69 */     return this.sequenceId;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\database\DbOrderedFeature.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */