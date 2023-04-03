/*    */ package org.openstreetmap.osmosis.core.database;
/*    */ 
/*    */ import org.openstreetmap.osmosis.core.store.GenericObjectReader;
/*    */ import org.openstreetmap.osmosis.core.store.GenericObjectWriter;
/*    */ import org.openstreetmap.osmosis.core.store.StoreClassRegister;
/*    */ import org.openstreetmap.osmosis.core.store.StoreReader;
/*    */ import org.openstreetmap.osmosis.core.store.StoreWriter;
/*    */ import org.openstreetmap.osmosis.core.store.Storeable;
/*    */ 
/*    */ public class DbFeature<T extends Storeable> implements Storeable {
/*    */   private long entityId;
/*    */   
/*    */   private T feature;
/*    */   
/*    */   public DbFeature(long entityId, T feature) {
/* 36 */     this.entityId = entityId;
/* 37 */     this.feature = feature;
/*    */   }
/*    */   
/*    */   public DbFeature(StoreReader sr, StoreClassRegister scr) {
/* 52 */     this(sr.readLong(), (T)(new GenericObjectReader(sr, scr)).readObject());
/*    */   }
/*    */   
/*    */   public void store(StoreWriter sw, StoreClassRegister scr) {
/* 63 */     sw.writeLong(this.entityId);
/* 64 */     (new GenericObjectWriter(sw, scr)).writeObject((Storeable)this.feature);
/*    */   }
/*    */   
/*    */   public long getEntityId() {
/* 72 */     return this.entityId;
/*    */   }
/*    */   
/*    */   public T getFeature() {
/* 80 */     return this.feature;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\database\DbFeature.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */