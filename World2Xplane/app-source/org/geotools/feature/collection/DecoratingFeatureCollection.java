/*     */ package org.geotools.feature.collection;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.Collection;
/*     */ import org.geotools.data.DataUtilities;
/*     */ import org.geotools.feature.FeatureCollection;
/*     */ import org.geotools.feature.FeatureIterator;
/*     */ import org.geotools.geometry.jts.ReferencedEnvelope;
/*     */ import org.opengis.feature.Feature;
/*     */ import org.opengis.feature.FeatureVisitor;
/*     */ import org.opengis.feature.type.FeatureType;
/*     */ import org.opengis.filter.Filter;
/*     */ import org.opengis.filter.sort.SortBy;
/*     */ import org.opengis.util.ProgressListener;
/*     */ 
/*     */ public class DecoratingFeatureCollection<T extends FeatureType, F extends Feature> implements FeatureCollection<T, F> {
/*     */   protected FeatureCollection<T, F> delegate;
/*     */   
/*     */   protected DecoratingFeatureCollection(FeatureCollection<T, F> delegate) {
/*  51 */     this.delegate = delegate;
/*     */   }
/*     */   
/*     */   public void accepts(FeatureVisitor visitor, ProgressListener progress) throws IOException {
/*  56 */     DataUtilities.visit(this, visitor, progress);
/*     */   }
/*     */   
/*     */   public boolean contains(Object o) {
/*  60 */     return this.delegate.contains(o);
/*     */   }
/*     */   
/*     */   public boolean containsAll(Collection<?> c) {
/*  64 */     return this.delegate.containsAll(c);
/*     */   }
/*     */   
/*     */   public boolean equals(Object o) {
/*  68 */     return this.delegate.equals(o);
/*     */   }
/*     */   
/*     */   public FeatureIterator<F> features() {
/*  72 */     return this.delegate.features();
/*     */   }
/*     */   
/*     */   public ReferencedEnvelope getBounds() {
/*  76 */     return this.delegate.getBounds();
/*     */   }
/*     */   
/*     */   public T getSchema() {
/*  80 */     return (T)this.delegate.getSchema();
/*     */   }
/*     */   
/*     */   public int hashCode() {
/*  84 */     return this.delegate.hashCode();
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/*  88 */     return this.delegate.isEmpty();
/*     */   }
/*     */   
/*     */   public int size() {
/*  92 */     return this.delegate.size();
/*     */   }
/*     */   
/*     */   public FeatureCollection<T, F> sort(SortBy order) {
/*  96 */     return this.delegate.sort(order);
/*     */   }
/*     */   
/*     */   public FeatureCollection<T, F> subCollection(Filter filter) {
/* 100 */     return this.delegate.subCollection(filter);
/*     */   }
/*     */   
/*     */   public Object[] toArray() {
/* 104 */     return this.delegate.toArray();
/*     */   }
/*     */   
/*     */   public <O> O[] toArray(O[] a) {
/* 108 */     return (O[])this.delegate.toArray((Object[])a);
/*     */   }
/*     */   
/*     */   public String getID() {
/* 111 */     return this.delegate.getID();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\feature\collection\DecoratingFeatureCollection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */