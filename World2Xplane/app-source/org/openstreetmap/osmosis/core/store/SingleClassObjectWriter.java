/*    */ package org.openstreetmap.osmosis.core.store;
/*    */ 
/*    */ import org.openstreetmap.osmosis.core.OsmosisRuntimeException;
/*    */ 
/*    */ public class SingleClassObjectWriter extends BaseObjectWriter {
/*    */   private Class<?> storeableType;
/*    */   
/*    */   protected SingleClassObjectWriter(StoreWriter storeWriter, StoreClassRegister storeClassRegister, Class<?> storeableType) {
/* 30 */     super(storeWriter, storeClassRegister);
/* 32 */     this.storeableType = storeableType;
/*    */   }
/*    */   
/*    */   protected void writeClassIdentifier(StoreWriter sw, StoreClassRegister scr, Class<?> clazz) {
/* 43 */     if (!this.storeableType.equals(clazz))
/* 44 */       throw new OsmosisRuntimeException("Received class " + clazz.getName() + ", expected class " + this.storeableType.getName() + "."); 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\store\SingleClassObjectWriter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */