/*    */ package com.world2xplane.XPlane.ModelBuilder;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class ModelTextureStore {
/* 32 */   private Map<String, String> cache = new HashMap<>();
/*    */   
/*    */   private static ModelTextureStore modelStore;
/*    */   
/*    */   public static ModelTextureStore getModelStore() {
/* 36 */     if (modelStore == null)
/* 37 */       modelStore = new ModelTextureStore(); 
/* 39 */     return modelStore;
/*    */   }
/*    */   
/*    */   public static void clearCache() {
/* 44 */     if (modelStore == null)
/* 45 */       modelStore = new ModelTextureStore(); 
/* 47 */     modelStore.cache.clear();
/*    */   }
/*    */   
/*    */   public Map<String, String> getCache() {
/* 51 */     return this.cache;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\XPlane\ModelBuilder\ModelTextureStore.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */