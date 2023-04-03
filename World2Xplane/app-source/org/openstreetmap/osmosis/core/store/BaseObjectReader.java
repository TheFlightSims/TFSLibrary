/*    */ package org.openstreetmap.osmosis.core.store;
/*    */ 
/*    */ import java.lang.reflect.Constructor;
/*    */ import java.lang.reflect.InvocationTargetException;
/*    */ import org.openstreetmap.osmosis.core.OsmosisRuntimeException;
/*    */ 
/*    */ public abstract class BaseObjectReader implements ObjectReader {
/*    */   private StoreReader storeReader;
/*    */   
/*    */   private StoreClassRegister storeClassRegister;
/*    */   
/*    */   private StoreableConstructorCache constructorCache;
/*    */   
/*    */   public BaseObjectReader(StoreReader storeReader, StoreClassRegister storeClassRegister) {
/* 31 */     this.storeReader = storeReader;
/* 32 */     this.storeClassRegister = storeClassRegister;
/* 34 */     this.constructorCache = new StoreableConstructorCache();
/*    */   }
/*    */   
/*    */   protected abstract Class<?> readClassFromIdentifier(StoreReader paramStoreReader, StoreClassRegister paramStoreClassRegister);
/*    */   
/*    */   public Storeable readObject() {
/* 60 */     Class<?> clazz = readClassFromIdentifier(this.storeReader, this.storeClassRegister);
/* 62 */     Constructor<?> constructor = this.constructorCache.getStoreableConstructor(clazz);
/*    */     try {
/* 65 */       return (Storeable)constructor.newInstance(new Object[] { this.storeReader, this.storeClassRegister });
/* 67 */     } catch (IllegalAccessException e) {
/* 68 */       throw new OsmosisRuntimeException("The class " + constructor.getDeclaringClass().getName() + " could not be instantiated.", e);
/* 70 */     } catch (InvocationTargetException e) {
/* 71 */       Throwable cause = e.getCause();
/* 72 */       if (cause instanceof EndOfStoreException)
/* 73 */         throw (EndOfStoreException)cause; 
/* 75 */       throw new OsmosisRuntimeException("The class " + constructor.getDeclaringClass().getName() + " could not be instantiated.", e);
/* 77 */     } catch (InstantiationException e) {
/* 78 */       throw new OsmosisRuntimeException("The class " + constructor.getDeclaringClass().getName() + " could not be instantiated.", e);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\store\BaseObjectReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */