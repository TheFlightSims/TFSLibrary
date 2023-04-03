/*    */ package org.geotools.data.simple;
/*    */ 
/*    */ import org.geotools.data.EmptyFeatureReader;
/*    */ import org.opengis.feature.simple.SimpleFeature;
/*    */ import org.opengis.feature.simple.SimpleFeatureType;
/*    */ import org.opengis.feature.type.FeatureType;
/*    */ 
/*    */ public class EmptySimpleFeatureReader extends EmptyFeatureReader<SimpleFeatureType, SimpleFeature> implements SimpleFeatureReader {
/*    */   public EmptySimpleFeatureReader(SimpleFeatureType featureType) {
/* 17 */     super((FeatureType)featureType);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\simple\EmptySimpleFeatureReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */