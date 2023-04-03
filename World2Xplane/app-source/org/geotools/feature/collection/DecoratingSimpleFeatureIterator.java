/*    */ package org.geotools.feature.collection;
/*    */ 
/*    */ import org.geotools.data.simple.SimpleFeatureIterator;
/*    */ import org.geotools.feature.FeatureIterator;
/*    */ import org.opengis.feature.simple.SimpleFeature;
/*    */ 
/*    */ public class DecoratingSimpleFeatureIterator extends DecoratingFeatureIterator<SimpleFeature> implements SimpleFeatureIterator {
/*    */   public DecoratingSimpleFeatureIterator(SimpleFeatureIterator iterator) {
/* 50 */     super((FeatureIterator<SimpleFeature>)iterator);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\feature\collection\DecoratingSimpleFeatureIterator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */