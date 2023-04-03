/*    */ package org.geotools.feature;
/*    */ 
/*    */ import org.geotools.data.simple.SimpleFeatureCollection;
/*    */ import org.opengis.feature.simple.SimpleFeatureType;
/*    */ 
/*    */ public class DefaultFeatureCollections extends FeatureCollections {
/*    */   protected SimpleFeatureCollection createCollection() {
/* 46 */     return new DefaultFeatureCollection(null, null);
/*    */   }
/*    */   
/*    */   protected SimpleFeatureCollection createCollection(String id) {
/* 49 */     return new DefaultFeatureCollection(id, null);
/*    */   }
/*    */   
/*    */   protected SimpleFeatureCollection createCollection(String id, SimpleFeatureType ft) {
/* 52 */     return new DefaultFeatureCollection(id, ft);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\feature\DefaultFeatureCollections.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */