/*     */ package org.geotools.data;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.List;
/*     */ import org.geotools.data.simple.SimpleFeatureStore;
/*     */ import org.geotools.feature.FeatureCollection;
/*     */ import org.geotools.feature.NameImpl;
/*     */ import org.opengis.feature.simple.SimpleFeature;
/*     */ import org.opengis.feature.simple.SimpleFeatureType;
/*     */ import org.opengis.feature.type.AttributeDescriptor;
/*     */ import org.opengis.feature.type.Name;
/*     */ import org.opengis.filter.Filter;
/*     */ import org.opengis.filter.identity.FeatureId;
/*     */ 
/*     */ class SimpleFeatureStoreBridge extends SimpleFeatureSourceBridge implements SimpleFeatureStore {
/*     */   public SimpleFeatureStoreBridge(FeatureStore<SimpleFeatureType, SimpleFeature> delegate) {
/*  39 */     super(delegate);
/*     */   }
/*     */   
/*     */   private FeatureStore<SimpleFeatureType, SimpleFeature> delegate() {
/*  43 */     return (FeatureStore<SimpleFeatureType, SimpleFeature>)this.delegate;
/*     */   }
/*     */   
/*     */   public List<FeatureId> addFeatures(FeatureCollection<SimpleFeatureType, SimpleFeature> collection) throws IOException {
/*  48 */     return delegate().addFeatures(collection);
/*     */   }
/*     */   
/*     */   public Transaction getTransaction() {
/*  52 */     return delegate().getTransaction();
/*     */   }
/*     */   
/*     */   public void modifyFeatures(AttributeDescriptor[] type, Object[] value, Filter filter) throws IOException {
/*  57 */     delegate().modifyFeatures(type, value, filter);
/*     */   }
/*     */   
/*     */   public void modifyFeatures(AttributeDescriptor type, Object value, Filter filter) throws IOException {
/*  63 */     delegate().modifyFeatures(type, value, filter);
/*     */   }
/*     */   
/*     */   public void modifyFeatures(Name[] names, Object[] values, Filter filter) throws IOException {
/*  69 */     delegate().modifyFeatures(names, values, filter);
/*     */   }
/*     */   
/*     */   public void modifyFeatures(Name name, Object value, Filter filter) throws IOException {
/*  75 */     delegate().modifyFeatures(name, value, filter);
/*     */   }
/*     */   
/*     */   public void modifyFeatures(String name, Object attributeValue, Filter filter) throws IOException {
/*  80 */     if (this.delegate instanceof SimpleFeatureStore) {
/*  81 */       ((SimpleFeatureStore)this.delegate).modifyFeatures(name, attributeValue, filter);
/*     */     } else {
/*  84 */       modifyFeatures(new Name[] { (Name)new NameImpl(name) }, new Object[] { attributeValue }, filter);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void modifyFeatures(String[] names, Object[] values, Filter filter) throws IOException {
/*  89 */     if (this.delegate instanceof SimpleFeatureStore) {
/*  90 */       ((SimpleFeatureStore)this.delegate).modifyFeatures(names, values, filter);
/*     */     } else {
/*  93 */       Name[] attributeNames = new Name[names.length];
/*  94 */       for (int i = 0; i < names.length; i++)
/*  95 */         attributeNames[i] = (Name)new NameImpl(names[i]); 
/*  97 */       modifyFeatures(attributeNames, values, filter);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void removeFeatures(Filter filter) throws IOException {
/* 102 */     delegate().removeFeatures(filter);
/*     */   }
/*     */   
/*     */   public void setFeatures(FeatureReader<SimpleFeatureType, SimpleFeature> reader) throws IOException {
/* 107 */     delegate().setFeatures(reader);
/*     */   }
/*     */   
/*     */   public void setTransaction(Transaction transaction) {
/* 111 */     delegate().setTransaction(transaction);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\SimpleFeatureStoreBridge.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */