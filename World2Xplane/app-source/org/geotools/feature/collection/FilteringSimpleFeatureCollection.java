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
/*     */ import org.geotools.factory.CommonFactoryFinder;
/*     */ import org.geotools.feature.FeatureCollection;
/*     */ import org.geotools.feature.FeatureIterator;
/*     */ import org.geotools.geometry.jts.ReferencedEnvelope;
/*     */ import org.opengis.feature.simple.SimpleFeature;
/*     */ import org.opengis.feature.simple.SimpleFeatureType;
/*     */ import org.opengis.feature.type.FeatureType;
/*     */ import org.opengis.filter.And;
/*     */ import org.opengis.filter.Filter;
/*     */ import org.opengis.filter.FilterFactory2;
/*     */ import org.opengis.filter.sort.SortBy;
/*     */ 
/*     */ public class FilteringSimpleFeatureCollection extends DecoratingSimpleFeatureCollection {
/*     */   SimpleFeatureCollection delegate;
/*     */   
/*     */   Filter filter;
/*     */   
/*     */   public FilteringSimpleFeatureCollection(FeatureCollection<SimpleFeatureType, SimpleFeature> delegate, Filter filter) {
/*  58 */     this(DataUtilities.simple(delegate), filter);
/*     */   }
/*     */   
/*     */   public FilteringSimpleFeatureCollection(SimpleFeatureCollection delegate, Filter filter) {
/*  62 */     super(delegate);
/*  63 */     this.delegate = delegate;
/*  64 */     this.filter = filter;
/*     */   }
/*     */   
/*     */   public SimpleFeatureIterator features() {
/*  68 */     return new FilteringSimpleFeatureIterator(this.delegate.features(), this.filter);
/*     */   }
/*     */   
/*     */   public void close(SimpleFeatureIterator close) {
/*  72 */     close.close();
/*     */   }
/*     */   
/*     */   public SimpleFeatureCollection subCollection(Filter filter) {
/*  76 */     FilterFactory2 ff = CommonFactoryFinder.getFilterFactory2();
/*  77 */     And and = ff.and(this.filter, filter);
/*  79 */     return new FilteringSimpleFeatureCollection(this.delegate, (Filter)and);
/*     */   }
/*     */   
/*     */   public SimpleFeatureCollection sort(SortBy order) {
/*  83 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public int size() {
/*  87 */     int count = 0;
/*  88 */     SimpleFeatureIterator i = features();
/*     */     try {
/*  90 */       while (i.hasNext()) {
/*  91 */         count++;
/*  91 */         i.next();
/*     */       } 
/*  94 */       return count;
/*     */     } finally {
/*  97 */       i.close();
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 102 */     return (size() == 0);
/*     */   }
/*     */   
/*     */   public Object[] toArray() {
/* 106 */     return toArray(new Object[size()]);
/*     */   }
/*     */   
/*     */   public <T> T[] toArray(T[] a) {
/* 110 */     List<SimpleFeature> list = new ArrayList<SimpleFeature>();
/* 111 */     SimpleFeatureIterator i = features();
/*     */     try {
/* 113 */       while (i.hasNext())
/* 114 */         list.add(i.next()); 
/* 117 */       return (T[])list.toArray((Object[])a);
/*     */     } finally {
/* 120 */       i.close();
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean contains(Object o) {
/* 125 */     return (this.delegate.contains(o) && this.filter.evaluate(o));
/*     */   }
/*     */   
/*     */   public boolean containsAll(Collection<?> c) {
/* 129 */     for (Iterator<?> i = c.iterator(); i.hasNext();) {
/* 130 */       if (!contains(i.next()))
/* 131 */         return false; 
/*     */     } 
/* 135 */     return true;
/*     */   }
/*     */   
/*     */   public FeatureReader<SimpleFeatureType, SimpleFeature> reader() throws IOException {
/* 139 */     return (FeatureReader<SimpleFeatureType, SimpleFeature>)new DelegateFeatureReader((FeatureType)getSchema(), (FeatureIterator)features());
/*     */   }
/*     */   
/*     */   public ReferencedEnvelope getBounds() {
/* 144 */     return DataUtilities.bounds((FeatureCollection)this);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\feature\collection\FilteringSimpleFeatureCollection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */