/*    */ package org.geotools.data.simple;
/*    */ 
/*    */ import org.geotools.data.collection.DelegateFeatureReader;
/*    */ import org.opengis.feature.simple.SimpleFeature;
/*    */ import org.opengis.feature.simple.SimpleFeatureType;
/*    */ import org.opengis.feature.type.FeatureType;
/*    */ 
/*    */ public class DelegateSimpleFeatureReader extends DelegateFeatureReader<SimpleFeatureType, SimpleFeature> implements SimpleFeatureReader {
/*    */   public DelegateSimpleFeatureReader(SimpleFeatureType featureType, SimpleFeatureIterator features) {
/* 18 */     super((FeatureType)featureType, features);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\simple\DelegateSimpleFeatureReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */