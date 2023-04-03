/*     */ package org.geotools.feature.collection;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.NoSuchElementException;
/*     */ import org.geotools.feature.FeatureIterator;
/*     */ import org.opengis.feature.Feature;
/*     */ import org.opengis.filter.Filter;
/*     */ 
/*     */ public class FilteredIterator<F extends Feature> implements Iterator<F>, FeatureIterator<F> {
/*     */   private Iterator<F> delegate;
/*     */   
/*     */   private Filter filter;
/*     */   
/*     */   private F next;
/*     */   
/*     */   public FilteredIterator(Iterator<F> iterator, Filter filter) {
/*  54 */     this.delegate = iterator;
/*  55 */     this.filter = filter;
/*     */   }
/*     */   
/*     */   public FilteredIterator(Collection<F> collection, Filter filter) {
/*  59 */     this.delegate = collection.iterator();
/*  60 */     this.filter = filter;
/*  61 */     this.next = getNext();
/*     */   }
/*     */   
/*     */   public void close() {
/*  66 */     if (this.delegate instanceof FeatureIterator)
/*  67 */       ((FeatureIterator)this.delegate).close(); 
/*  69 */     this.delegate = null;
/*  70 */     this.filter = null;
/*  71 */     this.next = null;
/*     */   }
/*     */   
/*     */   private F getNext() {
/*  75 */     F item = null;
/*  76 */     while (this.delegate.hasNext()) {
/*  77 */       Feature feature = (Feature)this.delegate.next();
/*  78 */       if (this.filter.evaluate(feature))
/*  79 */         return (F)feature; 
/*     */     } 
/*  82 */     return null;
/*     */   }
/*     */   
/*     */   public boolean hasNext() {
/*  86 */     return (this.next != null);
/*     */   }
/*     */   
/*     */   public F next() {
/*  90 */     if (this.next == null)
/*  91 */       throw new NoSuchElementException(); 
/*  93 */     F current = this.next;
/*  94 */     this.next = getNext();
/*  95 */     return current;
/*     */   }
/*     */   
/*     */   public void remove() {
/*  99 */     if (this.delegate == null)
/*  99 */       throw new IllegalStateException(); 
/* 101 */     this.delegate.remove();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\feature\collection\FilteredIterator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */