/*    */ package org.openstreetmap.osmosis.core.store;
/*    */ 
/*    */ public class IntegerLongIndexElement implements IndexElement<Integer> {
/*    */   private int id;
/*    */   
/*    */   private long value;
/*    */   
/*    */   public IntegerLongIndexElement(int id, long value) {
/* 32 */     this.id = id;
/* 33 */     this.value = value;
/*    */   }
/*    */   
/*    */   public IntegerLongIndexElement(StoreReader sr, StoreClassRegister scr) {
/* 47 */     this(sr.readInteger(), sr.readLong());
/*    */   }
/*    */   
/*    */   public void store(StoreWriter writer, StoreClassRegister storeClassRegister) {
/* 55 */     writer.writeInteger(this.id);
/* 56 */     writer.writeLong(this.value);
/*    */   }
/*    */   
/*    */   public Integer getKey() {
/* 67 */     return Integer.valueOf(this.id);
/*    */   }
/*    */   
/*    */   public int getId() {
/* 77 */     return this.id;
/*    */   }
/*    */   
/*    */   public long getValue() {
/* 87 */     return this.value;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\store\IntegerLongIndexElement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */