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
/*     */ public class MaxFeaturesFeatureCollection<T extends FeatureType, F extends Feature> extends DecoratingFeatureCollection<T, F> {
/*     */   FeatureCollection<T, F> delegate;
/*     */   
/*     */   long max;
/*     */   
/*     */   public MaxFeaturesFeatureCollection(FeatureCollection<T, F> delegate, long max) {
/*  51 */     super(delegate);
/*  52 */     this.delegate = delegate;
/*  53 */     this.max = max;
/*     */   }
/*     */   
/*     */   public FeatureReader<T, F> reader() throws IOException {
/*  57 */     return (FeatureReader<T, F>)new DelegateFeatureReader(getSchema(), features());
/*     */   }
/*     */   
/*     */   public FeatureIterator<F> features() {
/*  61 */     return new MaxFeaturesIterator<F>(this.delegate.features(), this.max);
/*     */   }
/*     */   
/*     */   public FeatureCollection<T, F> subCollection(Filter filter) {
/*  65 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public FeatureCollection<T, F> sort(SortBy order) {
/*  69 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public int size() {
/*  73 */     return (int)Math.min(this.delegate.size(), this.max);
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/*  77 */     return (this.delegate.isEmpty() || this.max == 0L);
/*     */   }
/*     */   
/*     */   public Object[] toArray() {
/*  81 */     return toArray(new Object[size()]);
/*     */   }
/*     */   
/*     */   public <O> O[] toArray(O[] a) {
/*  85 */     List<F> list = new ArrayList<F>();
/*  86 */     FeatureIterator<F> i = features();
/*     */     try {
/*  88 */       while (i.hasNext())
/*  89 */         list.add((F)i.next()); 
/*  91 */       return (O[])list.toArray((Object[])a);
/*     */     } finally {
/*  94 */       i.close();
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean containsAll(Collection<?> c) {
/*  99 */     for (Iterator<?> i = c.iterator(); i.hasNext();) {
/* 100 */       if (!contains(i.next()))
/* 101 */         return false; 
/*     */     } 
/* 104 */     return true;
/*     */   }
/*     */   
/*     */   public ReferencedEnvelope getBounds() {
/* 109 */     return DataUtilities.bounds((FeatureCollection)this);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\store\MaxFeaturesFeatureCollection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */