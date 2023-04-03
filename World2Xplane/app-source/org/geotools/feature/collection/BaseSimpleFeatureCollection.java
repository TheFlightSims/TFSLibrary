/*    */ package org.geotools.feature.collection;
/*    */ 
/*    */ import org.geotools.data.simple.SimpleFeatureCollection;
/*    */ import org.geotools.data.simple.SimpleFeatureIterator;
/*    */ import org.geotools.data.store.EmptyFeatureCollection;
/*    */ import org.geotools.feature.FeatureCollection;
/*    */ import org.geotools.feature.FeatureIterator;
/*    */ import org.opengis.feature.simple.SimpleFeature;
/*    */ import org.opengis.feature.simple.SimpleFeatureType;
/*    */ import org.opengis.filter.Filter;
/*    */ import org.opengis.filter.sort.SortBy;
/*    */ 
/*    */ public abstract class BaseSimpleFeatureCollection extends BaseFeatureCollection<SimpleFeatureType, SimpleFeature> implements SimpleFeatureCollection {
/*    */   protected BaseSimpleFeatureCollection(SimpleFeatureType schema) {
/* 31 */     super(schema);
/*    */   }
/*    */   
/*    */   public SimpleFeatureCollection subCollection(Filter filter) {
/* 43 */     if (filter == Filter.INCLUDE)
/* 44 */       return this; 
/* 46 */     if (filter == Filter.EXCLUDE)
/* 47 */       return (SimpleFeatureCollection)new EmptyFeatureCollection(this.schema); 
/* 50 */     return new FilteringSimpleFeatureCollection(this, filter);
/*    */   }
/*    */   
/*    */   public SimpleFeatureCollection sort(SortBy order) {
/* 55 */     return new SortedSimpleFeatureCollection(this, new SortBy[] { order });
/*    */   }
/*    */   
/*    */   public abstract SimpleFeatureIterator features();
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\feature\collection\BaseSimpleFeatureCollection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */