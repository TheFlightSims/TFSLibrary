/*    */ package org.geotools.feature.collection;
/*    */ 
/*    */ import java.util.Collection;
/*    */ import org.geotools.data.simple.SimpleFeatureIterator;
/*    */ import org.opengis.feature.simple.SimpleFeature;
/*    */ 
/*    */ public class SimpleFeatureIteratorImpl extends FeatureIteratorImpl<SimpleFeature> implements SimpleFeatureIterator {
/*    */   public SimpleFeatureIteratorImpl(Collection<SimpleFeature> collection) {
/* 45 */     super(collection);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\feature\collection\SimpleFeatureIteratorImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */