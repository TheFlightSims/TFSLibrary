/*    */ package org.geotools.data;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.util.NoSuchElementException;
/*    */ import org.geotools.feature.IllegalAttributeException;
/*    */ import org.opengis.feature.Feature;
/*    */ import org.opengis.feature.type.FeatureType;
/*    */ 
/*    */ public class MaxFeatureReader<T extends FeatureType, F extends Feature> implements DelegatingFeatureReader<T, F> {
/*    */   protected final FeatureReader<T, F> featureReader;
/*    */   
/*    */   protected final int maxFeatures;
/*    */   
/* 41 */   protected int counter = 0;
/*    */   
/*    */   public MaxFeatureReader(FeatureReader<T, F> featureReader, int maxFeatures) {
/* 50 */     this.featureReader = featureReader;
/* 51 */     this.maxFeatures = maxFeatures;
/*    */   }
/*    */   
/*    */   public FeatureReader<T, F> getDelegate() {
/* 55 */     return this.featureReader;
/*    */   }
/*    */   
/*    */   public F next() throws IOException, IllegalAttributeException, NoSuchElementException {
/* 60 */     if (hasNext()) {
/* 61 */       this.counter++;
/* 63 */       return this.featureReader.next();
/*    */     } 
/* 65 */     throw new NoSuchElementException("No such Feature exists");
/*    */   }
/*    */   
/*    */   public void close() throws IOException {
/* 70 */     this.featureReader.close();
/*    */   }
/*    */   
/*    */   public T getFeatureType() {
/* 74 */     return this.featureReader.getFeatureType();
/*    */   }
/*    */   
/*    */   public boolean hasNext() throws IOException {
/* 86 */     return (this.featureReader.hasNext() && this.counter < this.maxFeatures);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\MaxFeatureReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */