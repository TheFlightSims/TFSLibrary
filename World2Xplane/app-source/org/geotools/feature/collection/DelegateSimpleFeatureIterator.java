/*    */ package org.geotools.feature.collection;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ import org.geotools.data.simple.SimpleFeatureIterator;
/*    */ import org.geotools.feature.FeatureCollection;
/*    */ import org.opengis.feature.simple.SimpleFeature;
/*    */ import org.opengis.feature.simple.SimpleFeatureType;
/*    */ 
/*    */ public class DelegateSimpleFeatureIterator extends DelegateFeatureIterator<SimpleFeature> implements SimpleFeatureIterator {
/*    */   public DelegateSimpleFeatureIterator(Iterator<SimpleFeature> iterator) {
/* 50 */     super(iterator);
/*    */   }
/*    */   
/*    */   public DelegateSimpleFeatureIterator(FeatureCollection<SimpleFeatureType, SimpleFeature> collection, Iterator<SimpleFeature> iterator) {
/* 59 */     super((FeatureCollection)collection, iterator);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\feature\collection\DelegateSimpleFeatureIterator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */