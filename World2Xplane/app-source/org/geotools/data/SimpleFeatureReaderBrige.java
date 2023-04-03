/*    */ package org.geotools.data;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.util.NoSuchElementException;
/*    */ import org.geotools.data.simple.SimpleFeatureReader;
/*    */ import org.opengis.feature.Feature;
/*    */ import org.opengis.feature.simple.SimpleFeature;
/*    */ import org.opengis.feature.simple.SimpleFeatureType;
/*    */ import org.opengis.feature.type.FeatureType;
/*    */ 
/*    */ class SimpleFeatureReaderBrige implements SimpleFeatureReader {
/*    */   FeatureReader<SimpleFeatureType, SimpleFeature> reader;
/*    */   
/*    */   public SimpleFeatureReaderBrige(FeatureReader<SimpleFeatureType, SimpleFeature> reader) {
/* 35 */     this.reader = reader;
/*    */   }
/*    */   
/*    */   public SimpleFeatureType getFeatureType() {
/* 39 */     return this.reader.getFeatureType();
/*    */   }
/*    */   
/*    */   public SimpleFeature next() throws IOException, IllegalArgumentException, NoSuchElementException {
/* 44 */     return this.reader.next();
/*    */   }
/*    */   
/*    */   public boolean hasNext() throws IOException {
/* 48 */     return this.reader.hasNext();
/*    */   }
/*    */   
/*    */   public void close() throws IOException {
/* 52 */     this.reader.close();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\SimpleFeatureReaderBrige.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */