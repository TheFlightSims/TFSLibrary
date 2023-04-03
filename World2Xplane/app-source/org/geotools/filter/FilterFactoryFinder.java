/*    */ package org.geotools.filter;
/*    */ 
/*    */ import org.geotools.factory.CommonFactoryFinder;
/*    */ import org.geotools.factory.FactoryRegistryException;
/*    */ import org.geotools.factory.GeoTools;
/*    */ import org.geotools.factory.Hints;
/*    */ 
/*    */ public abstract class FilterFactoryFinder {
/*    */   public static FilterFactory createFilterFactory() throws FactoryRegistryException {
/* 58 */     Hints hints = GeoTools.getDefaultHints();
/* 59 */     return (FilterFactory)CommonFactoryFinder.getFilterFactory(hints);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\FilterFactoryFinder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */