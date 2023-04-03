/*     */ package org.geotools.data;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.Collection;
/*     */ import java.util.NoSuchElementException;
/*     */ import org.geotools.data.simple.SimpleFeatureCollection;
/*     */ import org.geotools.data.simple.SimpleFeatureIterator;
/*     */ import org.geotools.feature.FeatureCollection;
/*     */ import org.geotools.feature.FeatureIterator;
/*     */ import org.geotools.geometry.jts.ReferencedEnvelope;
/*     */ import org.opengis.feature.Feature;
/*     */ import org.opengis.feature.FeatureVisitor;
/*     */ import org.opengis.feature.simple.SimpleFeature;
/*     */ import org.opengis.feature.simple.SimpleFeatureType;
/*     */ import org.opengis.feature.type.FeatureType;
/*     */ import org.opengis.filter.Filter;
/*     */ import org.opengis.filter.sort.SortBy;
/*     */ import org.opengis.util.ProgressListener;
/*     */ 
/*     */ class SimpleFeatureCollectionBridge implements SimpleFeatureCollection {
/*     */   private FeatureCollection<SimpleFeatureType, SimpleFeature> collection;
/*     */   
/*     */   public SimpleFeatureCollectionBridge(FeatureCollection<SimpleFeatureType, SimpleFeature> featureCollection) {
/*  54 */     if (featureCollection == null)
/*  55 */       throw new NullPointerException("FeatureCollection required"); 
/*  57 */     if (featureCollection instanceof SimpleFeatureCollection)
/*  58 */       throw new IllegalArgumentException("Already a SimpleFeatureCollection"); 
/*  60 */     this.collection = featureCollection;
/*     */   }
/*     */   
/*     */   public SimpleFeatureIterator features() {
/*  64 */     final FeatureIterator<SimpleFeature> features = this.collection.features();
/*  65 */     return new SimpleFeatureIterator() {
/*     */         public SimpleFeature next() throws NoSuchElementException {
/*  67 */           return (SimpleFeature)features.next();
/*     */         }
/*     */         
/*     */         public boolean hasNext() {
/*  71 */           return features.hasNext();
/*     */         }
/*     */         
/*     */         public void close() {
/*  75 */           features.close();
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   public SimpleFeatureCollection sort(SortBy order) {
/*  81 */     return new SimpleFeatureCollectionBridge(this.collection.sort(order));
/*     */   }
/*     */   
/*     */   public SimpleFeatureCollection subCollection(Filter filter) {
/*  85 */     return new SimpleFeatureCollectionBridge(this.collection.subCollection(filter));
/*     */   }
/*     */   
/*     */   public void accepts(FeatureVisitor visitor, ProgressListener progress) throws IOException {
/*  89 */     this.collection.accepts(visitor, progress);
/*     */   }
/*     */   
/*     */   public boolean contains(Object o) {
/*  93 */     return this.collection.contains(o);
/*     */   }
/*     */   
/*     */   public boolean containsAll(Collection<?> o) {
/*  97 */     return this.collection.containsAll(o);
/*     */   }
/*     */   
/*     */   public ReferencedEnvelope getBounds() {
/* 101 */     return this.collection.getBounds();
/*     */   }
/*     */   
/*     */   public String getID() {
/* 105 */     return this.collection.getID();
/*     */   }
/*     */   
/*     */   public SimpleFeatureType getSchema() {
/* 109 */     return (SimpleFeatureType)this.collection.getSchema();
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 113 */     return this.collection.isEmpty();
/*     */   }
/*     */   
/*     */   public int size() {
/* 117 */     return this.collection.size();
/*     */   }
/*     */   
/*     */   public Object[] toArray() {
/* 121 */     return this.collection.toArray();
/*     */   }
/*     */   
/*     */   public <O> O[] toArray(O[] a) {
/* 125 */     return (O[])this.collection.toArray((Object[])a);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\SimpleFeatureCollectionBridge.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */