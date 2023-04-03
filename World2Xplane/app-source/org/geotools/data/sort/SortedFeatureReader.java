/*    */ package org.geotools.data.sort;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.util.NoSuchElementException;
/*    */ import org.geotools.data.Query;
/*    */ import org.geotools.data.simple.SimpleFeatureReader;
/*    */ import org.opengis.feature.Feature;
/*    */ import org.opengis.feature.simple.SimpleFeature;
/*    */ import org.opengis.feature.simple.SimpleFeatureType;
/*    */ import org.opengis.feature.type.FeatureType;
/*    */ import org.opengis.filter.sort.SortBy;
/*    */ 
/*    */ public class SortedFeatureReader implements SimpleFeatureReader {
/*    */   SimpleFeatureReader delegate;
/*    */   
/*    */   public static final boolean canSort(SimpleFeatureType schema, SortBy[] sortBy) {
/* 50 */     return MergeSortDumper.canSort(schema, sortBy);
/*    */   }
/*    */   
/*    */   public SortedFeatureReader(SimpleFeatureReader reader, Query query) throws IOException {
/* 61 */     this.delegate = MergeSortDumper.getDelegateReader(reader, query);
/*    */   }
/*    */   
/*    */   public SortedFeatureReader(SimpleFeatureReader reader, SortBy[] sortBy, int maxFeatures) throws IOException {
/* 74 */     this.delegate = MergeSortDumper.getDelegateReader(reader, sortBy, maxFeatures);
/*    */   }
/*    */   
/*    */   public SimpleFeatureType getFeatureType() {
/* 78 */     return (SimpleFeatureType)this.delegate.getFeatureType();
/*    */   }
/*    */   
/*    */   public SimpleFeature next() throws IOException, IllegalArgumentException, NoSuchElementException {
/* 83 */     return (SimpleFeature)this.delegate.next();
/*    */   }
/*    */   
/*    */   public boolean hasNext() throws IOException {
/* 87 */     return this.delegate.hasNext();
/*    */   }
/*    */   
/*    */   public void close() throws IOException {
/* 91 */     this.delegate.close();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\sort\SortedFeatureReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */