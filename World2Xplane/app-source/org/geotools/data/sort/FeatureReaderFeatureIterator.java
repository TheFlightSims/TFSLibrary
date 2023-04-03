/*    */ package org.geotools.data.sort;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.util.NoSuchElementException;
/*    */ import org.geotools.data.simple.SimpleFeatureIterator;
/*    */ import org.geotools.data.simple.SimpleFeatureReader;
/*    */ import org.opengis.feature.Feature;
/*    */ import org.opengis.feature.simple.SimpleFeature;
/*    */ 
/*    */ class FeatureReaderFeatureIterator implements SimpleFeatureIterator {
/*    */   SimpleFeatureReader reader;
/*    */   
/*    */   public FeatureReaderFeatureIterator(SimpleFeatureReader reader) {
/* 33 */     this.reader = reader;
/*    */   }
/*    */   
/*    */   public boolean hasNext() {
/*    */     try {
/* 38 */       return this.reader.hasNext();
/* 39 */     } catch (IOException e) {
/* 40 */       throw new RuntimeException("Reader failed", e);
/*    */     } 
/*    */   }
/*    */   
/*    */   public SimpleFeature next() {
/* 45 */     if (!hasNext())
/* 46 */       throw new NoSuchElementException(); 
/*    */     try {
/* 49 */       return (SimpleFeature)this.reader.next();
/* 50 */     } catch (Exception e) {
/* 51 */       throw new RuntimeException("Reader failed", e);
/*    */     } 
/*    */   }
/*    */   
/*    */   public void close() {
/*    */     try {
/* 57 */       this.reader.close();
/* 58 */     } catch (IOException e) {}
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\sort\FeatureReaderFeatureIterator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */