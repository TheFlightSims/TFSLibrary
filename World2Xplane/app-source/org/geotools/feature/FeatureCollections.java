/*    */ package org.geotools.feature;
/*    */ 
/*    */ import java.awt.RenderingHints;
/*    */ import java.util.Collections;
/*    */ import java.util.Map;
/*    */ import org.geotools.data.simple.SimpleFeatureCollection;
/*    */ import org.geotools.factory.CommonFactoryFinder;
/*    */ import org.geotools.factory.Factory;
/*    */ import org.geotools.factory.GeoTools;
/*    */ 
/*    */ public abstract class FeatureCollections implements Factory {
/*    */   private static FeatureCollections instance() {
/* 40 */     return CommonFactoryFinder.getFeatureCollections(GeoTools.getDefaultHints());
/*    */   }
/*    */   
/*    */   public static SimpleFeatureCollection newCollection() {
/* 49 */     return instance().createCollection();
/*    */   }
/*    */   
/*    */   public static SimpleFeatureCollection newCollection(String id) {
/* 64 */     return instance().createCollection(id);
/*    */   }
/*    */   
/*    */   protected abstract SimpleFeatureCollection createCollection();
/*    */   
/*    */   protected abstract SimpleFeatureCollection createCollection(String paramString);
/*    */   
/*    */   public Map<RenderingHints.Key, Object> getImplementationHints() {
/* 87 */     return Collections.emptyMap();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\feature\FeatureCollections.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */