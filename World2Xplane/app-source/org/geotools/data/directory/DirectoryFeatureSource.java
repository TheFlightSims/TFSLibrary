/*     */ package org.geotools.data.directory;
/*     */ 
/*     */ import java.awt.RenderingHints;
/*     */ import java.io.IOException;
/*     */ import java.util.Set;
/*     */ import org.geotools.data.DataAccess;
/*     */ import org.geotools.data.FeatureListener;
/*     */ import org.geotools.data.Query;
/*     */ import org.geotools.data.QueryCapabilities;
/*     */ import org.geotools.data.ResourceInfo;
/*     */ import org.geotools.data.simple.SimpleFeatureCollection;
/*     */ import org.geotools.data.simple.SimpleFeatureSource;
/*     */ import org.geotools.feature.FeatureCollection;
/*     */ import org.geotools.geometry.jts.ReferencedEnvelope;
/*     */ import org.opengis.feature.simple.SimpleFeature;
/*     */ import org.opengis.feature.simple.SimpleFeatureType;
/*     */ import org.opengis.feature.type.FeatureType;
/*     */ import org.opengis.feature.type.Name;
/*     */ import org.opengis.filter.Filter;
/*     */ 
/*     */ public class DirectoryFeatureSource implements SimpleFeatureSource {
/*     */   SimpleFeatureSource fsource;
/*     */   
/*     */   public DirectoryFeatureSource(SimpleFeatureSource delegate) {
/*  46 */     this.fsource = delegate;
/*     */   }
/*     */   
/*     */   public void addFeatureListener(FeatureListener listener) {
/*  50 */     this.fsource.addFeatureListener(listener);
/*     */   }
/*     */   
/*     */   public ReferencedEnvelope getBounds() throws IOException {
/*  54 */     return this.fsource.getBounds();
/*     */   }
/*     */   
/*     */   public ReferencedEnvelope getBounds(Query query) throws IOException {
/*  58 */     return this.fsource.getBounds(query);
/*     */   }
/*     */   
/*     */   public int getCount(Query query) throws IOException {
/*  62 */     return this.fsource.getCount(query);
/*     */   }
/*     */   
/*     */   public DataAccess<SimpleFeatureType, SimpleFeature> getDataStore() {
/*  67 */     return this.fsource.getDataStore();
/*     */   }
/*     */   
/*     */   public SimpleFeatureCollection getFeatures() throws IOException {
/*  72 */     return this.fsource.getFeatures();
/*     */   }
/*     */   
/*     */   public SimpleFeatureCollection getFeatures(Filter filter) throws IOException {
/*  77 */     return this.fsource.getFeatures(filter);
/*     */   }
/*     */   
/*     */   public SimpleFeatureCollection getFeatures(Query query) throws IOException {
/*  82 */     return this.fsource.getFeatures(query);
/*     */   }
/*     */   
/*     */   public ResourceInfo getInfo() {
/*  86 */     return this.fsource.getInfo();
/*     */   }
/*     */   
/*     */   public Name getName() {
/*  90 */     return this.fsource.getName();
/*     */   }
/*     */   
/*     */   public QueryCapabilities getQueryCapabilities() {
/*  94 */     return this.fsource.getQueryCapabilities();
/*     */   }
/*     */   
/*     */   public SimpleFeatureType getSchema() {
/*  98 */     return (SimpleFeatureType)this.fsource.getSchema();
/*     */   }
/*     */   
/*     */   public Set<RenderingHints.Key> getSupportedHints() {
/* 102 */     return this.fsource.getSupportedHints();
/*     */   }
/*     */   
/*     */   public void removeFeatureListener(FeatureListener listener) {
/* 106 */     this.fsource.removeFeatureListener(listener);
/*     */   }
/*     */   
/*     */   public SimpleFeatureSource unwrap() {
/* 110 */     return this.fsource;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\directory\DirectoryFeatureSource.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */