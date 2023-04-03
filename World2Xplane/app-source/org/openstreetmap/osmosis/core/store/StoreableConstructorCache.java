/*    */ package org.openstreetmap.osmosis.core.store;
/*    */ 
/*    */ import java.lang.reflect.Constructor;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import org.openstreetmap.osmosis.core.OsmosisRuntimeException;
/*    */ 
/*    */ public class StoreableConstructorCache {
/* 28 */   private Map<Class<?>, Constructor<?>> cache = new HashMap<Class<?>, Constructor<?>>();
/*    */   
/*    */   public Constructor<?> getStoreableConstructor(Class<?> clazz) {
/*    */     Constructor<?> constructor;
/* 43 */     if (this.cache.containsKey(clazz)) {
/* 44 */       constructor = this.cache.get(clazz);
/*    */     } else {
/*    */       try {
/* 47 */         constructor = clazz.getConstructor(new Class[] { StoreReader.class, StoreClassRegister.class });
/* 49 */       } catch (NoSuchMethodException e) {
/* 50 */         throw new OsmosisRuntimeException("Class " + clazz.getName() + " does not have a constructor accepting a " + StoreReader.class.getName() + " argument, this is required for all Storeable classes.", e);
/*    */       } 
/* 55 */       this.cache.put(clazz, constructor);
/*    */     } 
/* 58 */     return constructor;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\store\StoreableConstructorCache.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */