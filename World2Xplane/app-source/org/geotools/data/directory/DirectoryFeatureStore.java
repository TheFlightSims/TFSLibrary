/*     */ package org.geotools.data.directory;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.List;
/*     */ import org.geotools.data.FeatureListener;
/*     */ import org.geotools.data.FeatureReader;
/*     */ import org.geotools.data.Transaction;
/*     */ import org.geotools.data.simple.SimpleFeatureSource;
/*     */ import org.geotools.data.simple.SimpleFeatureStore;
/*     */ import org.geotools.feature.FeatureCollection;
/*     */ import org.opengis.feature.simple.SimpleFeature;
/*     */ import org.opengis.feature.simple.SimpleFeatureType;
/*     */ import org.opengis.feature.type.AttributeDescriptor;
/*     */ import org.opengis.feature.type.Name;
/*     */ import org.opengis.filter.Filter;
/*     */ import org.opengis.filter.identity.FeatureId;
/*     */ 
/*     */ public class DirectoryFeatureStore extends DirectoryFeatureSource implements SimpleFeatureStore {
/*     */   SimpleFeatureStore fstore;
/*     */   
/*     */   public DirectoryFeatureStore(SimpleFeatureStore store) {
/*  46 */     super((SimpleFeatureSource)store);
/*  47 */     this.fstore = store;
/*     */   }
/*     */   
/*     */   public Transaction getTransaction() {
/*  51 */     return this.fstore.getTransaction();
/*     */   }
/*     */   
/*     */   public void modifyFeatures(Name attributeName, Object attributeValue, Filter filter) throws IOException {
/*  56 */     this.fstore.modifyFeatures(attributeName, attributeValue, filter);
/*     */   }
/*     */   
/*     */   public void modifyFeatures(AttributeDescriptor type, Object value, Filter filter) throws IOException {
/*  61 */     this.fstore.modifyFeatures(type, value, filter);
/*     */   }
/*     */   
/*     */   public void modifyFeatures(Name[] name, Object[] value, Filter filter) throws IOException {
/*  66 */     this.fstore.modifyFeatures(name, value, filter);
/*     */   }
/*     */   
/*     */   public void modifyFeatures(String name, Object value, Filter filter) throws IOException {
/*  71 */     this.fstore.modifyFeatures(name, value, filter);
/*     */   }
/*     */   
/*     */   public void modifyFeatures(String[] names, Object[] values, Filter filter) throws IOException {
/*  75 */     this.fstore.modifyFeatures(names, values, filter);
/*     */   }
/*     */   
/*     */   public void modifyFeatures(AttributeDescriptor[] type, Object[] value, Filter filter) throws IOException {
/*  80 */     this.fstore.modifyFeatures(type, value, filter);
/*     */   }
/*     */   
/*     */   public void removeFeatureListener(FeatureListener listener) {
/*  84 */     this.fstore.removeFeatureListener(listener);
/*     */   }
/*     */   
/*     */   public void removeFeatures(Filter filter) throws IOException {
/*  88 */     this.fstore.removeFeatures(filter);
/*     */   }
/*     */   
/*     */   public void setFeatures(FeatureReader<SimpleFeatureType, SimpleFeature> reader) throws IOException {
/*  94 */     this.fstore.setFeatures(reader);
/*     */   }
/*     */   
/*     */   public void setTransaction(Transaction transaction) {
/*  98 */     this.fstore.setTransaction(transaction);
/*     */   }
/*     */   
/*     */   public List<FeatureId> addFeatures(FeatureCollection collection) throws IOException {
/* 104 */     return this.fstore.addFeatures(collection);
/*     */   }
/*     */   
/*     */   public SimpleFeatureStore unwrap() {
/* 109 */     return this.fstore;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\directory\DirectoryFeatureStore.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */