/*     */ package org.geotools.data.shapefile;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import org.geotools.data.DataAccess;
/*     */ import org.geotools.data.FeatureReader;
/*     */ import org.geotools.data.FeatureWriter;
/*     */ import org.geotools.data.FilteringFeatureWriter;
/*     */ import org.geotools.data.Query;
/*     */ import org.geotools.data.QueryCapabilities;
/*     */ import org.geotools.data.ResourceInfo;
/*     */ import org.geotools.data.Transaction;
/*     */ import org.geotools.data.shapefile.files.ShpFiles;
/*     */ import org.geotools.data.store.ContentDataStore;
/*     */ import org.geotools.data.store.ContentEntry;
/*     */ import org.geotools.data.store.ContentFeatureStore;
/*     */ import org.geotools.data.store.ContentState;
/*     */ import org.geotools.geometry.jts.ReferencedEnvelope;
/*     */ import org.opengis.feature.FeatureVisitor;
/*     */ import org.opengis.feature.simple.SimpleFeature;
/*     */ import org.opengis.feature.simple.SimpleFeatureType;
/*     */ import org.opengis.feature.type.Name;
/*     */ import org.opengis.filter.Filter;
/*     */ 
/*     */ class ShapefileFeatureStore extends ContentFeatureStore {
/*     */   ShapefileFeatureSource delegate;
/*     */   
/*     */   public ShapefileFeatureStore(ContentEntry entry, ShpFiles files) {
/*  51 */     super(entry, Query.ALL);
/*  52 */     this.delegate = new ShapefileFeatureSource(entry, files);
/*  53 */     this.hints = this.delegate.getSupportedHints();
/*     */   }
/*     */   
/*     */   protected FeatureWriter<SimpleFeatureType, SimpleFeature> getWriterInternal(Query query, int flags) throws IOException {
/*     */     FeatureWriter<SimpleFeatureType, SimpleFeature> writer;
/*     */     FilteringFeatureWriter filteringFeatureWriter;
/*  59 */     if (flags == 0)
/*  60 */       throw new IllegalArgumentException("no write flags set"); 
/*  63 */     ShapefileFeatureReader reader = (ShapefileFeatureReader)this.delegate.getReaderInternal(Query.ALL);
/*  66 */     ShapefileDataStore ds = getDataStore();
/*  67 */     if (ds.indexManager.hasFidIndex(false) || (ds.isFidIndexed() && ds.indexManager.hasFidIndex(true))) {
/*  68 */       writer = new IndexedShapefileFeatureWriter(ds.indexManager, reader, ds.getCharset(), ds.getTimeZone());
/*     */     } else {
/*  70 */       writer = new ShapefileFeatureWriter(this.delegate.shpFiles, reader, ds.getCharset(), ds.getTimeZone());
/*     */     } 
/*  77 */     if ((flags | 0x1) == 1)
/*  78 */       while (writer.hasNext())
/*  79 */         writer.next();  
/*  85 */     Filter filter = query.getFilter();
/*  86 */     if (filter != null && !Filter.INCLUDE.equals(filter))
/*  87 */       filteringFeatureWriter = new FilteringFeatureWriter(writer, filter); 
/*  90 */     return (FeatureWriter<SimpleFeatureType, SimpleFeature>)filteringFeatureWriter;
/*     */   }
/*     */   
/*     */   public ShapefileDataStore getDataStore() {
/*  98 */     return this.delegate.getDataStore();
/*     */   }
/*     */   
/*     */   public Transaction getTransaction() {
/* 102 */     return this.delegate.getTransaction();
/*     */   }
/*     */   
/*     */   public ResourceInfo getInfo() {
/* 106 */     return this.delegate.getInfo();
/*     */   }
/*     */   
/*     */   public QueryCapabilities getQueryCapabilities() {
/* 110 */     return this.delegate.getQueryCapabilities();
/*     */   }
/*     */   
/*     */   protected ReferencedEnvelope getBoundsInternal(Query query) throws IOException {
/* 115 */     return this.delegate.getBoundsInternal(query);
/*     */   }
/*     */   
/*     */   protected int getCountInternal(Query query) throws IOException {
/* 120 */     return this.delegate.getCountInternal(query);
/*     */   }
/*     */   
/*     */   protected FeatureReader<SimpleFeatureType, SimpleFeature> getReaderInternal(Query query) throws IOException {
/* 126 */     return this.delegate.getReaderInternal(query);
/*     */   }
/*     */   
/*     */   protected SimpleFeatureType buildFeatureType() throws IOException {
/* 131 */     return this.delegate.buildFeatureType();
/*     */   }
/*     */   
/*     */   public ContentEntry getEntry() {
/* 136 */     return this.delegate.getEntry();
/*     */   }
/*     */   
/*     */   public Name getName() {
/* 141 */     return this.delegate.getName();
/*     */   }
/*     */   
/*     */   public ContentState getState() {
/* 146 */     return this.delegate.getState();
/*     */   }
/*     */   
/*     */   public void setTransaction(Transaction transaction) {
/* 151 */     super.setTransaction(transaction);
/* 153 */     if (this.delegate.getTransaction() != transaction)
/* 154 */       this.delegate.setTransaction(transaction); 
/*     */   }
/*     */   
/*     */   protected boolean canFilter() {
/* 160 */     return this.delegate.canFilter();
/*     */   }
/*     */   
/*     */   protected boolean canRetype() {
/* 165 */     return this.delegate.canRetype();
/*     */   }
/*     */   
/*     */   protected boolean handleVisitor(Query query, FeatureVisitor visitor) throws IOException {
/* 170 */     return this.delegate.handleVisitor(query, visitor);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\shapefile\ShapefileFeatureStore.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */