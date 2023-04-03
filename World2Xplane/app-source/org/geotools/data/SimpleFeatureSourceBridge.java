/*    */ package org.geotools.data;
/*    */ 
/*    */ import java.awt.RenderingHints;
/*    */ import java.io.IOException;
/*    */ import java.util.Set;
/*    */ import org.geotools.data.simple.SimpleFeatureCollection;
/*    */ import org.geotools.data.simple.SimpleFeatureSource;
/*    */ import org.geotools.feature.FeatureCollection;
/*    */ import org.geotools.geometry.jts.ReferencedEnvelope;
/*    */ import org.opengis.feature.simple.SimpleFeature;
/*    */ import org.opengis.feature.simple.SimpleFeatureType;
/*    */ import org.opengis.feature.type.FeatureType;
/*    */ import org.opengis.feature.type.Name;
/*    */ import org.opengis.filter.Filter;
/*    */ 
/*    */ class SimpleFeatureSourceBridge implements SimpleFeatureSource {
/*    */   protected FeatureSource<SimpleFeatureType, SimpleFeature> delegate;
/*    */   
/*    */   public SimpleFeatureSourceBridge(FeatureSource<SimpleFeatureType, SimpleFeature> delegate) {
/* 41 */     this.delegate = delegate;
/*    */   }
/*    */   
/*    */   public void addFeatureListener(FeatureListener listener) {
/* 45 */     this.delegate.addFeatureListener(listener);
/*    */   }
/*    */   
/*    */   public ReferencedEnvelope getBounds() throws IOException {
/* 49 */     return this.delegate.getBounds();
/*    */   }
/*    */   
/*    */   public ReferencedEnvelope getBounds(Query query) throws IOException {
/* 53 */     return this.delegate.getBounds(query);
/*    */   }
/*    */   
/*    */   public int getCount(Query query) throws IOException {
/* 57 */     return this.delegate.getCount(query);
/*    */   }
/*    */   
/*    */   public DataAccess<SimpleFeatureType, SimpleFeature> getDataStore() {
/* 61 */     return this.delegate.getDataStore();
/*    */   }
/*    */   
/*    */   public SimpleFeatureCollection getFeatures() throws IOException {
/* 65 */     return DataUtilities.simple(this.delegate.getFeatures());
/*    */   }
/*    */   
/*    */   public SimpleFeatureCollection getFeatures(Filter filter) throws IOException {
/* 69 */     return DataUtilities.simple(this.delegate.getFeatures(filter));
/*    */   }
/*    */   
/*    */   public SimpleFeatureCollection getFeatures(Query query) throws IOException {
/* 73 */     return DataUtilities.simple(this.delegate.getFeatures(query));
/*    */   }
/*    */   
/*    */   public ResourceInfo getInfo() {
/* 77 */     return this.delegate.getInfo();
/*    */   }
/*    */   
/*    */   public Name getName() {
/* 81 */     return this.delegate.getName();
/*    */   }
/*    */   
/*    */   public QueryCapabilities getQueryCapabilities() {
/* 85 */     return this.delegate.getQueryCapabilities();
/*    */   }
/*    */   
/*    */   public SimpleFeatureType getSchema() {
/* 89 */     return this.delegate.getSchema();
/*    */   }
/*    */   
/*    */   public Set<RenderingHints.Key> getSupportedHints() {
/* 93 */     return this.delegate.getSupportedHints();
/*    */   }
/*    */   
/*    */   public void removeFeatureListener(FeatureListener listener) {
/* 97 */     this.delegate.removeFeatureListener(listener);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\SimpleFeatureSourceBridge.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */