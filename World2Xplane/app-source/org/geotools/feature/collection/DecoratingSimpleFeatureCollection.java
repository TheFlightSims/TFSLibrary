/*     */ package org.geotools.feature.collection;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.Collection;
/*     */ import org.geotools.data.DataUtilities;
/*     */ import org.geotools.data.simple.SimpleFeatureCollection;
/*     */ import org.geotools.data.simple.SimpleFeatureIterator;
/*     */ import org.geotools.feature.FeatureCollection;
/*     */ import org.geotools.feature.FeatureIterator;
/*     */ import org.geotools.geometry.jts.ReferencedEnvelope;
/*     */ import org.opengis.feature.FeatureVisitor;
/*     */ import org.opengis.feature.simple.SimpleFeature;
/*     */ import org.opengis.feature.simple.SimpleFeatureType;
/*     */ import org.opengis.feature.type.FeatureType;
/*     */ import org.opengis.filter.Filter;
/*     */ import org.opengis.filter.sort.SortBy;
/*     */ import org.opengis.util.ProgressListener;
/*     */ 
/*     */ public class DecoratingSimpleFeatureCollection implements SimpleFeatureCollection {
/*     */   protected SimpleFeatureCollection delegate;
/*     */   
/*     */   protected DecoratingSimpleFeatureCollection(FeatureCollection<SimpleFeatureType, SimpleFeature> delegate) {
/*  52 */     this.delegate = DataUtilities.simple(delegate);
/*     */   }
/*     */   
/*     */   protected DecoratingSimpleFeatureCollection(SimpleFeatureCollection delegate) {
/*  56 */     this.delegate = delegate;
/*     */   }
/*     */   
/*     */   public void accepts(FeatureVisitor visitor, ProgressListener progress) throws IOException {
/*  61 */     if (canDelegate(visitor)) {
/*  62 */       this.delegate.accepts(visitor, progress);
/*     */     } else {
/*  65 */       DataUtilities.visit((FeatureCollection)this, visitor, progress);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected boolean canDelegate(FeatureVisitor visitor) {
/*  77 */     return false;
/*     */   }
/*     */   
/*     */   public boolean contains(Object o) {
/*  81 */     return this.delegate.contains(o);
/*     */   }
/*     */   
/*     */   public boolean containsAll(Collection<?> c) {
/*  85 */     return this.delegate.containsAll(c);
/*     */   }
/*     */   
/*     */   public boolean equals(Object o) {
/*  89 */     return this.delegate.equals(o);
/*     */   }
/*     */   
/*     */   public SimpleFeatureIterator features() {
/*  93 */     return this.delegate.features();
/*     */   }
/*     */   
/*     */   public ReferencedEnvelope getBounds() {
/*  97 */     return this.delegate.getBounds();
/*     */   }
/*     */   
/*     */   public SimpleFeatureType getSchema() {
/* 101 */     return (SimpleFeatureType)this.delegate.getSchema();
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 105 */     return this.delegate.hashCode();
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 109 */     return this.delegate.isEmpty();
/*     */   }
/*     */   
/*     */   public int size() {
/* 113 */     return this.delegate.size();
/*     */   }
/*     */   
/*     */   public SimpleFeatureCollection sort(SortBy order) {
/* 117 */     return this.delegate.sort(order);
/*     */   }
/*     */   
/*     */   public SimpleFeatureCollection subCollection(Filter filter) {
/* 121 */     return this.delegate.subCollection(filter);
/*     */   }
/*     */   
/*     */   public Object[] toArray() {
/* 125 */     return this.delegate.toArray();
/*     */   }
/*     */   
/*     */   public <F> F[] toArray(F[] a) {
/* 129 */     return (F[])this.delegate.toArray((Object[])a);
/*     */   }
/*     */   
/*     */   public String getID() {
/* 133 */     return this.delegate.getID();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\feature\collection\DecoratingSimpleFeatureCollection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */