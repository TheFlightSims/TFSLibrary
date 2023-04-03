/*    */ package org.openstreetmap.osmosis.core.store;
/*    */ 
/*    */ public class GenericObjectReader extends BaseObjectReader {
/*    */   public GenericObjectReader(StoreReader storeReader, StoreClassRegister storeClassRegister) {
/* 22 */     super(storeReader, storeClassRegister);
/*    */   }
/*    */   
/*    */   protected Class<?> readClassFromIdentifier(StoreReader sr, StoreClassRegister scr) {
/* 31 */     return scr.getClassFromIdentifier(sr);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\store\GenericObjectReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */