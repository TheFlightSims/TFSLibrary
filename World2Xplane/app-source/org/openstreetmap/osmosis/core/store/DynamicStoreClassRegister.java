/*    */ package org.openstreetmap.osmosis.core.store;
/*    */ 
/*    */ public class DynamicStoreClassRegister extends BaseStoreClassRegister {
/* 22 */   private byte idSequence = 0;
/*    */   
/*    */   public void storeIdentifierForClass(StoreWriter storeWriter, Class<?> clazz) {
/* 31 */     if (!isClassRecognized(clazz)) {
/* 34 */       byte id = this.idSequence = (byte)(this.idSequence + 1);
/* 36 */       registerClass(clazz, id);
/*    */     } 
/* 39 */     super.storeIdentifierForClass(storeWriter, clazz);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\store\DynamicStoreClassRegister.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */