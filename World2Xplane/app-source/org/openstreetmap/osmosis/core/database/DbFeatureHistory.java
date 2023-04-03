/*    */ package org.openstreetmap.osmosis.core.database;
/*    */ 
/*    */ import org.openstreetmap.osmosis.core.store.GenericObjectReader;
/*    */ import org.openstreetmap.osmosis.core.store.GenericObjectWriter;
/*    */ import org.openstreetmap.osmosis.core.store.StoreClassRegister;
/*    */ import org.openstreetmap.osmosis.core.store.StoreReader;
/*    */ import org.openstreetmap.osmosis.core.store.StoreWriter;
/*    */ import org.openstreetmap.osmosis.core.store.Storeable;
/*    */ 
/*    */ public class DbFeatureHistory<T extends Storeable> implements Storeable {
/*    */   private T feature;
/*    */   
/*    */   private int version;
/*    */   
/*    */   public DbFeatureHistory(T feature, int version) {
/* 34 */     this.feature = feature;
/* 35 */     this.version = version;
/*    */   }
/*    */   
/*    */   public DbFeatureHistory(StoreReader sr, StoreClassRegister scr) {
/* 50 */     this.feature = (T)(new GenericObjectReader(sr, scr)).readObject();
/* 52 */     this.version = sr.readInteger();
/*    */   }
/*    */   
/*    */   public void store(StoreWriter sw, StoreClassRegister scr) {
/* 60 */     (new GenericObjectWriter(sw, scr)).writeObject((Storeable)this.feature);
/* 61 */     sw.writeInteger(this.version);
/*    */   }
/*    */   
/*    */   public T getFeature() {
/* 71 */     return this.feature;
/*    */   }
/*    */   
/*    */   public int getVersion() {
/* 81 */     return this.version;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\database\DbFeatureHistory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */