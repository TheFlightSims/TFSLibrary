/*    */ package org.geotools.gml;
/*    */ 
/*    */ import org.geotools.feature.DefaultFeatureCollection;
/*    */ import org.opengis.feature.simple.SimpleFeature;
/*    */ import org.xml.sax.helpers.XMLFilterImpl;
/*    */ 
/*    */ public class GMLReceiver extends XMLFilterImpl implements GMLHandlerFeature {
/*    */   private DefaultFeatureCollection featureCollection;
/*    */   
/*    */   public GMLReceiver(DefaultFeatureCollection featureCollection) {
/* 43 */     this.featureCollection = featureCollection;
/*    */   }
/*    */   
/*    */   public void feature(SimpleFeature feature) {
/* 52 */     this.featureCollection.add(feature);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\gml\GMLReceiver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */