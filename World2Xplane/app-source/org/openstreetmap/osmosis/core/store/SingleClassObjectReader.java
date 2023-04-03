/*    */ package org.openstreetmap.osmosis.core.store;
/*    */ 
/*    */ public class SingleClassObjectReader extends BaseObjectReader {
/*    */   private Class<?> storeableType;
/*    */   
/*    */   protected SingleClassObjectReader(StoreReader storeReader, StoreClassRegister storeClassRegister, Class<?> storeableType) {
/* 28 */     super(storeReader, storeClassRegister);
/* 30 */     this.storeableType = storeableType;
/*    */   }
/*    */   
/*    */   protected Class<?> readClassFromIdentifier(StoreReader sr, StoreClassRegister scr) {
/* 41 */     return this.storeableType;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\store\SingleClassObjectReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */