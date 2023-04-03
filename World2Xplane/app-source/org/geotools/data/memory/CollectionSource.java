/*     */ package org.geotools.data.memory;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.geotools.data.Transaction;
/*     */ import org.geotools.feature.NameImpl;
/*     */ import org.opengis.feature.type.Name;
/*     */ import org.opengis.filter.Filter;
/*     */ import org.opengis.filter.capability.FilterCapabilities;
/*     */ import org.opengis.referencing.crs.CoordinateReferenceSystem;
/*     */ 
/*     */ public final class CollectionSource {
/*     */   private Collection collection;
/*     */   
/*     */   private CoordinateReferenceSystem crs;
/*     */   
/*     */   public CollectionSource(Collection collection) {
/*  50 */     this(collection, null);
/*     */   }
/*     */   
/*     */   public CollectionSource(Collection<?> collection, CoordinateReferenceSystem crs) {
/*  54 */     this.collection = Collections.unmodifiableCollection(collection);
/*  55 */     this.crs = crs;
/*     */   }
/*     */   
/*     */   public Collection content() {
/*  59 */     return this.collection;
/*     */   }
/*     */   
/*     */   public Collection content(String query, String queryLanguage) {
/*  63 */     throw new UnsupportedOperationException("Please help me hook up the parser!");
/*     */   }
/*     */   
/*     */   public Collection content(Filter filter) {
/*  67 */     return content(filter, 2147483647);
/*     */   }
/*     */   
/*     */   public Collection content(Filter filter, int countLimit) {
/*  71 */     List<Object> list = new ArrayList();
/*  72 */     int count = 0;
/*  73 */     for (Iterator i = this.collection.iterator(); i.hasNext() && count < countLimit; ) {
/*  74 */       Object obj = i.next();
/*  75 */       if (filter.evaluate(obj)) {
/*  76 */         list.add(obj);
/*  77 */         count++;
/*     */       } 
/*     */     } 
/*  80 */     return Collections.unmodifiableList(list);
/*     */   }
/*     */   
/*     */   public Object describe() {
/*  84 */     return Object.class;
/*     */   }
/*     */   
/*     */   public void dispose() {
/*  88 */     this.collection = null;
/*     */   }
/*     */   
/*     */   public FilterCapabilities getFilterCapabilities() {
/*  92 */     return null;
/*     */   }
/*     */   
/*     */   public Name getName() {
/*  96 */     return (Name)new NameImpl("localhost/memory");
/*     */   }
/*     */   
/*     */   public void setTransaction(Transaction t) {}
/*     */   
/*     */   public CoordinateReferenceSystem getCRS() {
/* 104 */     return this.crs;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\memory\CollectionSource.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */