/*    */ package org.geotools.data.sort;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.util.NoSuchElementException;
/*    */ import org.geotools.data.simple.DelegateSimpleFeatureReader;
/*    */ import org.geotools.data.simple.SimpleFeatureIterator;
/*    */ import org.geotools.data.simple.SimpleFeatureReader;
/*    */ import org.opengis.feature.Feature;
/*    */ import org.opengis.feature.simple.SimpleFeature;
/*    */ import org.opengis.feature.simple.SimpleFeatureType;
/*    */ import org.opengis.filter.sort.SortBy;
/*    */ 
/*    */ public class SortedFeatureIterator implements SimpleFeatureIterator {
/*    */   FeatureReaderFeatureIterator delegate;
/*    */   
/*    */   public static final boolean canSort(SimpleFeatureType schema, SortBy[] sortBy) {
/* 48 */     return MergeSortDumper.canSort(schema, sortBy);
/*    */   }
/*    */   
/*    */   public SortedFeatureIterator(SimpleFeatureIterator iterator, SimpleFeatureType schema, SortBy[] sortBy, int maxFeatures) throws IOException {
/* 62 */     DelegateSimpleFeatureReader reader = new DelegateSimpleFeatureReader(schema, iterator);
/* 63 */     SimpleFeatureReader sorted = new SortedFeatureReader((SimpleFeatureReader)reader, sortBy, maxFeatures);
/* 64 */     this.delegate = new FeatureReaderFeatureIterator(sorted);
/*    */   }
/*    */   
/*    */   public boolean hasNext() {
/* 68 */     return this.delegate.hasNext();
/*    */   }
/*    */   
/*    */   public SimpleFeature next() throws NoSuchElementException {
/* 72 */     return this.delegate.next();
/*    */   }
/*    */   
/*    */   public void close() {
/* 76 */     this.delegate.close();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\sort\SortedFeatureIterator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */