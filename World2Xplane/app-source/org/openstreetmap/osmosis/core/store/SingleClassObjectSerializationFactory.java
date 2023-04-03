/*    */ package org.openstreetmap.osmosis.core.store;
/*    */ 
/*    */ public class SingleClassObjectSerializationFactory implements ObjectSerializationFactory {
/*    */   private Class<?> storeableType;
/*    */   
/*    */   public SingleClassObjectSerializationFactory(Class<?> storeableType) {
/* 23 */     this.storeableType = storeableType;
/*    */   }
/*    */   
/*    */   public ObjectReader createObjectReader(StoreReader storeReader, StoreClassRegister storeClassRegister) {
/* 32 */     return new SingleClassObjectReader(storeReader, storeClassRegister, this.storeableType);
/*    */   }
/*    */   
/*    */   public ObjectWriter createObjectWriter(StoreWriter storeWriter, StoreClassRegister storeClassRegister) {
/* 41 */     return new SingleClassObjectWriter(storeWriter, storeClassRegister, this.storeableType);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\store\SingleClassObjectSerializationFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */