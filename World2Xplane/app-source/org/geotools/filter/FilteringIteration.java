/*    */ package org.geotools.filter;
/*    */ 
/*    */ import org.geotools.data.store.FilteringFeatureCollection;
/*    */ import org.geotools.feature.FeatureCollection;
/*    */ import org.geotools.feature.FeatureCollectionIteration;
/*    */ import org.geotools.feature.FeatureIterator;
/*    */ import org.opengis.feature.Feature;
/*    */ import org.opengis.feature.type.PropertyDescriptor;
/*    */ import org.opengis.filter.Filter;
/*    */ 
/*    */ public class FilteringIteration extends FeatureCollectionIteration {
/*    */   public FilteringIteration(Filter filter, FeatureCollection<?, ?> collection) {
/* 50 */     super(new FilterHandler(filter), (FeatureCollection)new FilteringFeatureCollection(collection, filter));
/*    */   }
/*    */   
/*    */   public static void filter(FeatureCollection<?, ?> features, Filter filter) {
/* 55 */     FilteringIteration i = new FilteringIteration(filter, features);
/* 56 */     i.iterate();
/*    */   }
/*    */   
/*    */   protected void iterate(FeatureIterator<?> iterator) {
/* 60 */     ((FilterHandler)this.handler).iterator = iterator;
/* 61 */     super.iterate(iterator);
/*    */   }
/*    */   
/*    */   static class FilterHandler implements FeatureCollectionIteration.Handler {
/*    */     FeatureIterator<?> iterator;
/*    */     
/*    */     final Filter filter;
/*    */     
/*    */     public FilterHandler(Filter filter) {
/* 69 */       this.filter = filter;
/*    */     }
/*    */     
/*    */     public void endFeature(Feature f) {}
/*    */     
/*    */     public void endFeatureCollection(FeatureCollection<?, ?> fc) {}
/*    */     
/*    */     public void handleAttribute(PropertyDescriptor type, Object value) {}
/*    */     
/*    */     public void handleFeature(Feature f) {
/* 83 */       if (!this.filter.evaluate(f));
/*    */     }
/*    */     
/*    */     public void handleFeatureCollection(FeatureCollection<?, ?> fc) {}
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\FilteringIteration.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */