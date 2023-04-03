/*     */ package org.geotools.data;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import org.geotools.data.simple.SimpleFeatureSource;
/*     */ import org.opengis.feature.simple.SimpleFeature;
/*     */ import org.opengis.feature.simple.SimpleFeatureType;
/*     */ import org.opengis.filter.Filter;
/*     */ 
/*     */ public abstract class AbstractFileDataStore extends AbstractDataStore implements FileDataStore {
/*     */   public SimpleFeatureType getSchema() throws IOException {
/*  48 */     return getSchema(getTypeNames()[0]);
/*     */   }
/*     */   
/*     */   public FeatureReader<SimpleFeatureType, SimpleFeature> getFeatureReader() throws IOException {
/*  59 */     return getFeatureReader(getTypeNames()[0]);
/*     */   }
/*     */   
/*     */   public void updateSchema(SimpleFeatureType featureType) throws IOException {
/*  68 */     updateSchema(getSchema().getTypeName(), featureType);
/*     */   }
/*     */   
/*     */   public SimpleFeatureSource getFeatureSource() throws IOException {
/*  77 */     return getFeatureSource(getSchema().getTypeName());
/*     */   }
/*     */   
/*     */   public FeatureWriter<SimpleFeatureType, SimpleFeature> getFeatureWriter(Filter filter, Transaction transaction) throws IOException {
/*  85 */     return getFeatureWriter(getSchema().getTypeName(), filter, transaction);
/*     */   }
/*     */   
/*     */   public FeatureWriter<SimpleFeatureType, SimpleFeature> getFeatureWriter(Transaction transaction) throws IOException {
/*  94 */     return getFeatureWriter(getSchema().getTypeName(), transaction);
/*     */   }
/*     */   
/*     */   public FeatureWriter<SimpleFeatureType, SimpleFeature> getFeatureWriterAppend(Transaction transaction) throws IOException {
/* 103 */     return getFeatureWriterAppend(getSchema().getTypeName(), transaction);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\AbstractFileDataStore.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */