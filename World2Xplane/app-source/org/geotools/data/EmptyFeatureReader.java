/*    */ package org.geotools.data;
/*    */ 
/*    */ import java.util.NoSuchElementException;
/*    */ import org.opengis.feature.Feature;
/*    */ import org.opengis.feature.type.FeatureType;
/*    */ 
/*    */ public class EmptyFeatureReader<T extends FeatureType, F extends Feature> implements FeatureReader<T, F> {
/*    */   T featureType;
/*    */   
/*    */   public EmptyFeatureReader(T featureType) {
/* 42 */     this.featureType = featureType;
/*    */   }
/*    */   
/*    */   public T getFeatureType() {
/* 49 */     return this.featureType;
/*    */   }
/*    */   
/*    */   public F next() throws NoSuchElementException {
/* 62 */     throw new NoSuchElementException("FeatureReader is empty");
/*    */   }
/*    */   
/*    */   public boolean hasNext() {
/* 73 */     return false;
/*    */   }
/*    */   
/*    */   public void close() {
/* 82 */     this.featureType = null;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\EmptyFeatureReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */