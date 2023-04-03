/*    */ package org.openstreetmap.osmosis.core.store;
/*    */ 
/*    */ public class LongLongIndexElement implements IndexElement<Long> {
/*    */   private long id;
/*    */   
/*    */   private long value;
/*    */   
/*    */   public LongLongIndexElement(long id, long value) {
/* 32 */     this.id = id;
/* 33 */     this.value = value;
/*    */   }
/*    */   
/*    */   public LongLongIndexElement(StoreReader sr, StoreClassRegister scr) {
/* 47 */     this(sr.readLong(), sr.readLong());
/*    */   }
/*    */   
/*    */   public void store(StoreWriter writer, StoreClassRegister storeClassRegister) {
/* 55 */     writer.writeLong(this.id);
/* 56 */     writer.writeLong(this.value);
/*    */   }
/*    */   
/*    */   public Long getKey() {
/* 65 */     return Long.valueOf(this.id);
/*    */   }
/*    */   
/*    */   public long getId() {
/* 75 */     return this.id;
/*    */   }
/*    */   
/*    */   public long getValue() {
/* 85 */     return this.value;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\store\LongLongIndexElement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */