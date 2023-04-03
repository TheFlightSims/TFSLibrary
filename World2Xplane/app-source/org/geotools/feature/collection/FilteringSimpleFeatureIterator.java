/*    */ package org.geotools.feature.collection;
/*    */ 
/*    */ import org.geotools.data.simple.SimpleFeatureIterator;
/*    */ import org.geotools.data.store.FilteringFeatureIterator;
/*    */ import org.geotools.feature.FeatureIterator;
/*    */ import org.opengis.feature.simple.SimpleFeature;
/*    */ import org.opengis.filter.Filter;
/*    */ 
/*    */ public class FilteringSimpleFeatureIterator extends FilteringFeatureIterator<SimpleFeature> implements SimpleFeatureIterator {
/*    */   public FilteringSimpleFeatureIterator(SimpleFeatureIterator delegate, Filter filter) {
/* 34 */     super((FeatureIterator)delegate, filter);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\feature\collection\FilteringSimpleFeatureIterator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */