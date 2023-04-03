/*     */ package org.geotools.feature.collection;
/*     */ 
/*     */ import org.geotools.data.DataUtilities;
/*     */ import org.geotools.data.simple.SimpleFeatureCollection;
/*     */ import org.geotools.data.simple.SimpleFeatureIterator;
/*     */ import org.geotools.data.store.EmptyFeatureCollection;
/*     */ import org.geotools.factory.CommonFactoryFinder;
/*     */ import org.geotools.feature.FeatureCollection;
/*     */ import org.geotools.feature.FeatureIterator;
/*     */ import org.geotools.geometry.jts.ReferencedEnvelope;
/*     */ import org.opengis.feature.simple.SimpleFeatureType;
/*     */ import org.opengis.filter.Filter;
/*     */ import org.opengis.filter.FilterFactory;
/*     */ import org.opengis.filter.IncludeFilter;
/*     */ import org.opengis.filter.sort.SortBy;
/*     */ 
/*     */ public class SubFeatureCollection extends BaseSimpleFeatureCollection {
/*     */   protected Filter filter;
/*     */   
/*     */   protected SimpleFeatureCollection collection;
/*     */   
/*     */   protected FilterFactory ff;
/*     */   
/*     */   public SubFeatureCollection(SimpleFeatureCollection collection) {
/*  52 */     this(collection, (Filter)Filter.INCLUDE);
/*     */   }
/*     */   
/*     */   public SubFeatureCollection(SimpleFeatureCollection collection, Filter subfilter) {
/*  59 */     super((SimpleFeatureType)collection.getSchema());
/*     */     IncludeFilter includeFilter;
/*     */     this.ff = CommonFactoryFinder.getFilterFactory();
/*  61 */     if (subfilter == null)
/*  62 */       includeFilter = Filter.INCLUDE; 
/*  64 */     if (includeFilter.equals(Filter.EXCLUDE))
/*  65 */       throw new IllegalArgumentException("A subcollection with Filter.EXCLUDE would be empty"); 
/*  67 */     if (collection instanceof SubFeatureCollection) {
/*  68 */       SubFeatureCollection filtered = (SubFeatureCollection)collection;
/*  69 */       if (includeFilter.equals(Filter.INCLUDE)) {
/*  70 */         this.collection = filtered.collection;
/*  71 */         this.filter = filtered.filter();
/*     */       } else {
/*  73 */         this.collection = filtered.collection;
/*  74 */         this.filter = (Filter)this.ff.and(filtered.filter(), (Filter)includeFilter);
/*     */       } 
/*     */     } else {
/*  77 */       this.collection = collection;
/*  78 */       this.filter = (Filter)includeFilter;
/*     */     } 
/*     */   }
/*     */   
/*     */   public SimpleFeatureIterator features() {
/*  83 */     return new FilteringSimpleFeatureIterator(this.collection.features(), filter());
/*     */   }
/*     */   
/*     */   public int size() {
/*  87 */     int count = 0;
/*  88 */     SimpleFeatureIterator i = features();
/*     */     try {
/*  90 */       while (i.hasNext()) {
/*  91 */         i.next();
/*  92 */         count++;
/*     */       } 
/*     */     } finally {
/*  95 */       i.close();
/*     */     } 
/*  97 */     return count;
/*     */   }
/*     */   
/*     */   protected Filter filter() {
/* 106 */     if (this.filter == null)
/* 107 */       this.filter = createFilter(); 
/* 109 */     return this.filter;
/*     */   }
/*     */   
/*     */   protected Filter createFilter() {
/* 114 */     return (Filter)Filter.INCLUDE;
/*     */   }
/*     */   
/*     */   public SimpleFeatureCollection subCollection(Filter filter) {
/* 118 */     if (filter.equals(Filter.INCLUDE))
/* 119 */       return this; 
/* 121 */     if (filter.equals(Filter.EXCLUDE))
/* 122 */       return (SimpleFeatureCollection)new EmptyFeatureCollection(this.schema); 
/* 124 */     return new SubFeatureCollection(this, filter);
/*     */   }
/*     */   
/*     */   public SimpleFeatureCollection sort(SortBy order) {
/* 128 */     return new SubFeatureList(this.collection, this.filter, order);
/*     */   }
/*     */   
/*     */   public String getID() {
/* 132 */     return this.collection.getID();
/*     */   }
/*     */   
/*     */   public ReferencedEnvelope getBounds() {
/* 140 */     return DataUtilities.bounds(this);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\feature\collection\SubFeatureCollection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */