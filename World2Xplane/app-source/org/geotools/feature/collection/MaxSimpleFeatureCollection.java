/*     */ package org.geotools.feature.collection;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.geotools.data.DataUtilities;
/*     */ import org.geotools.data.FeatureReader;
/*     */ import org.geotools.data.collection.DelegateFeatureReader;
/*     */ import org.geotools.data.simple.SimpleFeatureCollection;
/*     */ import org.geotools.data.simple.SimpleFeatureIterator;
/*     */ import org.geotools.feature.FeatureCollection;
/*     */ import org.geotools.feature.FeatureIterator;
/*     */ import org.geotools.geometry.jts.ReferencedEnvelope;
/*     */ import org.opengis.feature.simple.SimpleFeature;
/*     */ import org.opengis.feature.simple.SimpleFeatureType;
/*     */ import org.opengis.feature.type.FeatureType;
/*     */ import org.opengis.filter.Filter;
/*     */ import org.opengis.filter.sort.SortBy;
/*     */ 
/*     */ public class MaxSimpleFeatureCollection extends DecoratingSimpleFeatureCollection {
/*     */   SimpleFeatureCollection delegate;
/*     */   
/*     */   long start;
/*     */   
/*     */   long max;
/*     */   
/*     */   public MaxSimpleFeatureCollection(FeatureCollection<SimpleFeatureType, SimpleFeature> delegate, long max) {
/*  58 */     this(DataUtilities.simple(delegate), 0L, max);
/*     */   }
/*     */   
/*     */   public MaxSimpleFeatureCollection(SimpleFeatureCollection delegate, long max) {
/*  62 */     this(delegate, 0L, max);
/*     */   }
/*     */   
/*     */   public MaxSimpleFeatureCollection(SimpleFeatureCollection delegate, long start, long max) {
/*  66 */     super(delegate);
/*  67 */     this.delegate = delegate;
/*  68 */     this.start = start;
/*  69 */     this.max = max;
/*     */   }
/*     */   
/*     */   FeatureReader<SimpleFeatureType, SimpleFeature> reader() throws IOException {
/*  73 */     return (FeatureReader<SimpleFeatureType, SimpleFeature>)new DelegateFeatureReader((FeatureType)getSchema(), (FeatureIterator)features());
/*     */   }
/*     */   
/*     */   public SimpleFeatureIterator features() {
/*  77 */     return new MaxFeaturesSimpleFeatureIterator(this.delegate.features(), this.start, this.max);
/*     */   }
/*     */   
/*     */   public SimpleFeatureCollection subCollection(Filter filter) {
/*  81 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public SimpleFeatureCollection sort(SortBy order) {
/*  85 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public int size() {
/*  89 */     int size = this.delegate.size();
/*  90 */     if (size < this.start)
/*  91 */       return 0; 
/*  93 */     return (int)Math.min(size - this.start, this.max);
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/*  98 */     return (this.delegate.isEmpty() || this.max == 0L || this.delegate.size() - this.start < 1L);
/*     */   }
/*     */   
/*     */   public Object[] toArray() {
/* 102 */     return toArray(new Object[size()]);
/*     */   }
/*     */   
/*     */   public <T> T[] toArray(T[] a) {
/* 106 */     List<T> list = new ArrayList<T>();
/* 107 */     SimpleFeatureIterator i = features();
/*     */     try {
/* 109 */       while (i.hasNext())
/* 110 */         list.add((T)i.next()); 
/* 112 */       return (T[])list.toArray((Object[])a);
/*     */     } finally {
/* 115 */       i.close();
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean containsAll(Collection<?> c) {
/* 120 */     for (Iterator<?> i = c.iterator(); i.hasNext();) {
/* 121 */       if (!contains(i.next()))
/* 122 */         return false; 
/*     */     } 
/* 126 */     return true;
/*     */   }
/*     */   
/*     */   public ReferencedEnvelope getBounds() {
/* 131 */     return DataUtilities.bounds((FeatureCollection)this);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\feature\collection\MaxSimpleFeatureCollection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */