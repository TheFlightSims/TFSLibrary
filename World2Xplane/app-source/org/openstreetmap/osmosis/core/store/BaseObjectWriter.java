/*    */ package org.openstreetmap.osmosis.core.store;
/*    */ 
/*    */ public abstract class BaseObjectWriter implements ObjectWriter {
/*    */   private StoreWriter storeWriter;
/*    */   
/*    */   private StoreClassRegister storeClassRegister;
/*    */   
/*    */   private StoreableConstructorCache constructorCache;
/*    */   
/*    */   protected BaseObjectWriter(StoreWriter storeWriter, StoreClassRegister storeClassRegister) {
/* 26 */     this.storeWriter = storeWriter;
/* 27 */     this.storeClassRegister = storeClassRegister;
/* 29 */     this.constructorCache = new StoreableConstructorCache();
/*    */   }
/*    */   
/*    */   protected abstract void writeClassIdentifier(StoreWriter paramStoreWriter, StoreClassRegister paramStoreClassRegister, Class<?> paramClass);
/*    */   
/*    */   public void writeObject(Storeable value) {
/* 57 */     Class<?> clazz = value.getClass();
/* 60 */     this.constructorCache.getStoreableConstructor(clazz);
/* 62 */     writeClassIdentifier(this.storeWriter, this.storeClassRegister, clazz);
/* 63 */     value.store(this.storeWriter, this.storeClassRegister);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\store\BaseObjectWriter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */