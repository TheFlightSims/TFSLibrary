/*    */ package org.openstreetmap.osmosis.core.store;
/*    */ 
/*    */ public class StaticStoreClassRegister extends BaseStoreClassRegister {
/*    */   public StaticStoreClassRegister(Class<?>[] classes) {
/* 25 */     byte currentId = 0;
/* 26 */     for (Class<?> clazz : classes) {
/* 27 */       currentId = (byte)(currentId + 1);
/* 27 */       registerClass(clazz, currentId);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\store\StaticStoreClassRegister.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */