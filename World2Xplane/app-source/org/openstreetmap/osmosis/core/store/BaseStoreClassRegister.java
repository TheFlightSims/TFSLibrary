/*    */ package org.openstreetmap.osmosis.core.store;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import org.openstreetmap.osmosis.core.OsmosisRuntimeException;
/*    */ 
/*    */ public class BaseStoreClassRegister implements StoreClassRegister {
/* 22 */   private Map<Class<?>, Byte> classToByteMap = new HashMap<Class<?>, Byte>();
/*    */   
/* 23 */   private Map<Byte, Class<?>> byteToClassMap = new HashMap<Byte, Class<?>>();
/*    */   
/*    */   protected boolean isClassRecognized(Class<?> clazz) {
/* 35 */     return this.classToByteMap.containsKey(clazz);
/*    */   }
/*    */   
/*    */   protected void registerClass(Class<?> clazz, byte id) {
/* 50 */     Byte objId = Byte.valueOf(id);
/* 52 */     this.classToByteMap.put(clazz, objId);
/* 53 */     this.byteToClassMap.put(objId, clazz);
/*    */   }
/*    */   
/*    */   public void storeIdentifierForClass(StoreWriter storeWriter, Class<?> clazz) {
/*    */     byte id;
/* 63 */     if (this.classToByteMap.containsKey(clazz)) {
/* 64 */       id = ((Byte)this.classToByteMap.get(clazz)).byteValue();
/*    */     } else {
/* 66 */       throw new OsmosisRuntimeException("The class " + clazz + " is not supported by this store class register.");
/*    */     } 
/* 69 */     storeWriter.writeByte(id);
/*    */   }
/*    */   
/*    */   public Class<?> getClassFromIdentifier(StoreReader storeReader) {
/* 80 */     byte classId = storeReader.readByte();
/* 82 */     Byte idObj = Byte.valueOf(classId);
/* 84 */     if (!this.byteToClassMap.containsKey(idObj))
/* 85 */       throw new OsmosisRuntimeException("Byte " + classId + " is not a recognised class identifier, the data stream may be corrupt."); 
/* 89 */     return this.byteToClassMap.get(idObj);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\store\BaseStoreClassRegister.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */