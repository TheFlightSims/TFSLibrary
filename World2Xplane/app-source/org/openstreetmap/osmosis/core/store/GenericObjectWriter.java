/*    */ package org.openstreetmap.osmosis.core.store;
/*    */ 
/*    */ public class GenericObjectWriter extends BaseObjectWriter {
/*    */   public GenericObjectWriter(StoreWriter storeWriter, StoreClassRegister storeClassRegister) {
/* 22 */     super(storeWriter, storeClassRegister);
/*    */   }
/*    */   
/*    */   protected void writeClassIdentifier(StoreWriter sw, StoreClassRegister scr, Class<?> clazz) {
/* 31 */     scr.storeIdentifierForClass(sw, clazz);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\store\GenericObjectWriter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */