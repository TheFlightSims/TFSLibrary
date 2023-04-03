/*     */ package org.geotools.data.collection;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import java.io.IOException;
/*     */ import org.geotools.data.AbstractDataStore;
/*     */ import org.geotools.data.FeatureReader;
/*     */ import org.geotools.data.Query;
/*     */ import org.geotools.data.SchemaNotFoundException;
/*     */ import org.geotools.feature.DefaultFeatureCollection;
/*     */ import org.geotools.feature.FeatureCollection;
/*     */ import org.geotools.feature.FeatureIterator;
/*     */ import org.geotools.feature.FeatureTypes;
/*     */ import org.geotools.geometry.jts.ReferencedEnvelope;
/*     */ import org.opengis.feature.simple.SimpleFeature;
/*     */ import org.opengis.feature.simple.SimpleFeatureType;
/*     */ import org.opengis.filter.Filter;
/*     */ 
/*     */ public class CollectionDataStore extends AbstractDataStore {
/*     */   SimpleFeatureType featureType;
/*     */   
/*     */   FeatureCollection<SimpleFeatureType, SimpleFeature> collection;
/*     */   
/*     */   public CollectionDataStore(SimpleFeatureType schema) {
/*  58 */     this.collection = (FeatureCollection<SimpleFeatureType, SimpleFeature>)new DefaultFeatureCollection();
/*  59 */     this.featureType = schema;
/*     */   }
/*     */   
/*     */   public CollectionDataStore(FeatureCollection<SimpleFeatureType, SimpleFeature> collection) {
/*  68 */     this.collection = collection;
/*  69 */     if (collection.size() == 0) {
/*  70 */       this.featureType = FeatureTypes.EMPTY;
/*     */     } else {
/*  72 */       this.featureType = (SimpleFeatureType)collection.getSchema();
/*     */     } 
/*     */   }
/*     */   
/*     */   public String[] getTypeNames() {
/*  80 */     return new String[] { this.featureType.getTypeName() };
/*     */   }
/*     */   
/*     */   public SimpleFeatureType getSchema(String typeName) throws IOException {
/*  87 */     if (typeName != null && typeName.equals(this.featureType.getTypeName()))
/*  88 */       return this.featureType; 
/*  91 */     throw new IOException(typeName + " not available");
/*     */   }
/*     */   
/*     */   public FeatureReader<SimpleFeatureType, SimpleFeature> getFeatureReader(String typeName) throws IOException {
/* 111 */     return new DelegateFeatureReader<SimpleFeatureType, SimpleFeature>(getSchema(typeName), this.collection.features());
/*     */   }
/*     */   
/*     */   public FeatureCollection<SimpleFeatureType, SimpleFeature> getCollection() {
/* 119 */     return this.collection;
/*     */   }
/*     */   
/*     */   protected ReferencedEnvelope getBounds(Query query) throws SchemaNotFoundException {
/* 128 */     String featureTypeName = query.getTypeName();
/* 129 */     if (!this.featureType.getTypeName().equals(featureTypeName))
/* 130 */       throw new SchemaNotFoundException(featureTypeName); 
/* 133 */     return getBoundsInternal(query);
/*     */   }
/*     */   
/*     */   protected ReferencedEnvelope getBoundsInternal(Query query) {
/* 140 */     ReferencedEnvelope envelope = new ReferencedEnvelope(this.featureType.getCoordinateReferenceSystem());
/* 142 */     FeatureIterator<SimpleFeature> iterator = this.collection.features();
/*     */     try {
/* 144 */       if (iterator.hasNext()) {
/* 145 */         int count = 1;
/* 146 */         Filter filter = query.getFilter();
/* 148 */         while (iterator.hasNext() && count < query.getMaxFeatures()) {
/* 149 */           SimpleFeature feature = (SimpleFeature)iterator.next();
/* 150 */           if (filter.evaluate(feature)) {
/* 151 */             count++;
/* 152 */             envelope.expandToInclude(((Geometry)feature.getDefaultGeometry()).getEnvelopeInternal());
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } finally {
/* 158 */       iterator.close();
/*     */     } 
/* 160 */     return envelope;
/*     */   }
/*     */   
/*     */   protected int getCount(Query query) throws IOException {
/* 168 */     String featureTypeName = query.getTypeName();
/* 169 */     if (!this.featureType.getTypeName().equals(featureTypeName))
/* 170 */       throw new SchemaNotFoundException(featureTypeName); 
/* 172 */     int count = 0;
/* 173 */     FeatureIterator<SimpleFeature> iterator = this.collection.features();
/*     */     try {
/* 175 */       Filter filter = query.getFilter();
/* 176 */       while (iterator.hasNext() && count < query.getMaxFeatures()) {
/* 177 */         if (filter.evaluate(iterator.next()))
/* 178 */           count++; 
/*     */       } 
/*     */     } finally {
/* 183 */       iterator.close();
/*     */     } 
/* 185 */     return count;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\collection\CollectionDataStore.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */