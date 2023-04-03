/*    */ package org.geotools.styling;
/*    */ 
/*    */ import org.geotools.factory.CommonFactoryFinder;
/*    */ import org.geotools.factory.FactoryRegistryException;
/*    */ import org.geotools.factory.GeoTools;
/*    */ 
/*    */ public class StyleFactoryFinder {
/* 33 */   private static StyleFactory factory = null;
/*    */   
/*    */   public static StyleFactory createStyleFactory() throws FactoryRegistryException {
/* 43 */     if (factory == null)
/* 44 */       factory = CommonFactoryFinder.getStyleFactory(GeoTools.getDefaultHints()); 
/* 46 */     return factory;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\styling\StyleFactoryFinder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */