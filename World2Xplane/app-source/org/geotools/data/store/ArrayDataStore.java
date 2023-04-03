/*    */ package org.geotools.data.store;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import org.geotools.data.AbstractDataStore;
/*    */ import org.geotools.data.DataUtilities;
/*    */ import org.geotools.data.FeatureReader;
/*    */ import org.geotools.feature.FeatureTypes;
/*    */ import org.opengis.feature.simple.SimpleFeature;
/*    */ import org.opengis.feature.simple.SimpleFeatureType;
/*    */ 
/*    */ public final class ArrayDataStore extends AbstractDataStore {
/*    */   private final SimpleFeatureType featureType;
/*    */   
/*    */   private final SimpleFeature[] featureArray;
/*    */   
/*    */   public ArrayDataStore(SimpleFeature[] featureArray) {
/* 53 */     if (featureArray == null || featureArray.length == 0) {
/* 54 */       this.featureType = FeatureTypes.EMPTY;
/*    */     } else {
/* 56 */       this.featureType = featureArray[0].getFeatureType();
/*    */     } 
/* 58 */     this.featureArray = featureArray;
/*    */   }
/*    */   
/*    */   public String[] getTypeNames() {
/* 62 */     return new String[] { this.featureType.getTypeName() };
/*    */   }
/*    */   
/*    */   public SimpleFeatureType getSchema(String typeName) throws IOException {
/* 66 */     if (typeName != null && typeName.equals(this.featureType.getTypeName()))
/* 67 */       return this.featureType; 
/* 70 */     throw new IOException(typeName + " not available");
/*    */   }
/*    */   
/*    */   protected FeatureReader<SimpleFeatureType, SimpleFeature> getFeatureReader(String typeName) throws IOException {
/* 75 */     return DataUtilities.reader(this.featureArray);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\store\ArrayDataStore.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */