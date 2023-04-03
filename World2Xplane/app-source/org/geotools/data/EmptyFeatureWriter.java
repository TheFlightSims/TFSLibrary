/*    */ package org.geotools.data;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.util.NoSuchElementException;
/*    */ import org.opengis.feature.Feature;
/*    */ import org.opengis.feature.simple.SimpleFeature;
/*    */ import org.opengis.feature.simple.SimpleFeatureType;
/*    */ import org.opengis.feature.type.FeatureType;
/*    */ 
/*    */ public class EmptyFeatureWriter implements FeatureWriter<SimpleFeatureType, SimpleFeature> {
/*    */   SimpleFeatureType featureType;
/*    */   
/*    */   public EmptyFeatureWriter(SimpleFeatureType featureType) {
/* 42 */     this.featureType = featureType;
/*    */   }
/*    */   
/*    */   public SimpleFeatureType getFeatureType() {
/* 49 */     return this.featureType;
/*    */   }
/*    */   
/*    */   public SimpleFeature next() throws NoSuchElementException {
/* 62 */     throw new NoSuchElementException("FeatureWriter is empty");
/*    */   }
/*    */   
/*    */   public void remove() throws IOException {
/* 69 */     throw new IOException("FeatureWriter is empty and does not support remove()");
/*    */   }
/*    */   
/*    */   public void write() throws IOException {
/* 77 */     throw new IOException("FeatureWriter is empty and does not support write()");
/*    */   }
/*    */   
/*    */   public boolean hasNext() {
/* 89 */     return false;
/*    */   }
/*    */   
/*    */   public void close() {
/* 98 */     this.featureType = null;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\EmptyFeatureWriter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */