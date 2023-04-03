/*     */ package org.geotools.data.store;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.geotools.data.DataUtilities;
/*     */ import org.geotools.data.FeatureReader;
/*     */ import org.geotools.data.collection.DelegateFeatureReader;
/*     */ import org.geotools.feature.FeatureCollection;
/*     */ import org.geotools.feature.FeatureIterator;
/*     */ import org.geotools.feature.collection.DecoratingFeatureCollection;
/*     */ import org.geotools.geometry.jts.ReferencedEnvelope;
/*     */ import org.opengis.feature.Feature;
/*     */ import org.opengis.feature.type.FeatureType;
/*     */ import org.opengis.filter.Filter;
/*     */ import org.opengis.filter.sort.SortBy;
/*     */ 
/*     */ public class FilteringFeatureCollection<T extends FeatureType, F extends Feature> extends DecoratingFeatureCollection<T, F> {
/*     */   FeatureCollection<T, F> delegate;
/*     */   
/*     */   Filter filter;
/*     */   
/*     */   public FilteringFeatureCollection(FeatureCollection<T, F> delegate, Filter filter) {
/*  60 */     super(delegate);
/*  61 */     this.delegate = delegate;
/*  62 */     this.filter = filter;
/*     */   }
/*     */   
/*     */   public FeatureCollection<T, F> subCollection(Filter filter) {
/*  66 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public FeatureCollection<T, F> sort(SortBy order) {
/*  70 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public int size() {
/*  74 */     int count = 0;
/*  75 */     FeatureIterator<F> i = features();
/*     */     try {
/*  77 */       while (i.hasNext()) {
/*  78 */         count++;
/*  78 */         i.next();
/*     */       } 
/*  81 */       return count;
/*     */     } finally {
/*  84 */       i.close();
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/*  89 */     return (size() == 0);
/*     */   }
/*     */   
/*     */   public Object[] toArray() {
/*  93 */     return toArray(new Object[size()]);
/*     */   }
/*     */   
/*     */   public <O> O[] toArray(O[] a) {
/*  97 */     List<F> list = new ArrayList<F>();
/*  98 */     FeatureIterator<F> i = features();
/*     */     try {
/* 100 */       while (i.hasNext())
/* 101 */         list.add((F)i.next()); 
/* 103 */       return (O[])list.toArray((Object[])a);
/*     */     } finally {
/* 106 */       i.close();
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean contains(Object o) {
/* 111 */     return (this.delegate.contains(o) && this.filter.evaluate(o));
/*     */   }
/*     */   
/*     */   public boolean containsAll(Collection<?> c) {
/* 115 */     for (Iterator<?> i = c.iterator(); i.hasNext();) {
/* 116 */       if (!contains(i.next()))
/* 117 */         return false; 
/*     */     } 
/* 120 */     return true;
/*     */   }
/*     */   
/*     */   public FeatureIterator<F> features() {
/* 124 */     return new FilteringFeatureIterator<F>(this.delegate.features(), this.filter);
/*     */   }
/*     */   
/*     */   public FeatureReader<T, F> reader() throws IOException {
/* 128 */     return (FeatureReader<T, F>)new DelegateFeatureReader(getSchema(), features());
/*     */   }
/*     */   
/*     */   public ReferencedEnvelope getBounds() {
/* 133 */     return DataUtilities.bounds((FeatureCollection)this);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\store\FilteringFeatureCollection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */