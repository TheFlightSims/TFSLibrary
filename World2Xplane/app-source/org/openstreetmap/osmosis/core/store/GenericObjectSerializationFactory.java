/*    */ package org.openstreetmap.osmosis.core.store;
/*    */ 
/*    */ public class GenericObjectSerializationFactory implements ObjectSerializationFactory {
/*    */   public ObjectReader createObjectReader(StoreReader storeReader, StoreClassRegister storeClassRegister) {
/* 19 */     return new GenericObjectReader(storeReader, storeClassRegister);
/*    */   }
/*    */   
/*    */   public ObjectWriter createObjectWriter(StoreWriter storeWriter, StoreClassRegister storeClassRegister) {
/* 28 */     return new GenericObjectWriter(storeWriter, storeClassRegister);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\store\GenericObjectSerializationFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */