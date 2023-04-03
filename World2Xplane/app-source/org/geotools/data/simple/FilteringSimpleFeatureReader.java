/*    */ package org.geotools.data.simple;
/*    */ 
/*    */ import org.geotools.data.FilteringFeatureReader;
/*    */ import org.opengis.feature.simple.SimpleFeature;
/*    */ import org.opengis.feature.simple.SimpleFeatureType;
/*    */ import org.opengis.filter.Filter;
/*    */ 
/*    */ public class FilteringSimpleFeatureReader extends FilteringFeatureReader<SimpleFeatureType, SimpleFeature> implements SimpleFeatureReader {
/*    */   public FilteringSimpleFeatureReader(SimpleFeatureReader featureReader, Filter filter) {
/* 19 */     super(featureReader, filter);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\simple\FilteringSimpleFeatureReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */