/*    */ package org.geotools.feature.collection;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import org.geotools.data.simple.SimpleFeatureCollection;
/*    */ import org.geotools.data.simple.SimpleFeatureIterator;
/*    */ import org.geotools.data.sort.SortedFeatureIterator;
/*    */ import org.geotools.feature.FeatureIterator;
/*    */ import org.opengis.filter.sort.SortBy;
/*    */ 
/*    */ public class SortedSimpleFeatureCollection extends DecoratingSimpleFeatureCollection {
/*    */   private SortBy[] sort;
/*    */   
/*    */   public SortedSimpleFeatureCollection(SimpleFeatureCollection delegate, SortBy[] sort) {
/* 24 */     super(delegate);
/* 25 */     this.sort = sort;
/*    */   }
/*    */   
/*    */   public SimpleFeatureIterator features() {
/*    */     try {
/*    */       SortedFeatureIterator sortedFeatureIterator;
/* 31 */       SimpleFeatureIterator features = this.delegate.features();
/* 33 */       if (this.sort != null)
/* 34 */         sortedFeatureIterator = new SortedFeatureIterator(features, getSchema(), this.sort, -1); 
/* 36 */       return (SimpleFeatureIterator)sortedFeatureIterator;
/* 37 */     } catch (IOException e) {
/* 38 */       throw new RuntimeException(e);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\feature\collection\SortedSimpleFeatureCollection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */