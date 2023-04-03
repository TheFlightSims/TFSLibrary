/*     */ package org.geotools.feature.collection;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Comparator;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.NoSuchElementException;
/*     */ import java.util.Set;
/*     */ import org.geotools.data.simple.SimpleFeatureCollection;
/*     */ import org.geotools.data.simple.SimpleFeatureIterator;
/*     */ import org.geotools.factory.CommonFactoryFinder;
/*     */ import org.geotools.util.SoftValueHashMap;
/*     */ import org.opengis.feature.simple.SimpleFeature;
/*     */ import org.opengis.filter.Filter;
/*     */ import org.opengis.filter.FilterFactory;
/*     */ import org.opengis.filter.Id;
/*     */ import org.opengis.filter.expression.PropertyName;
/*     */ import org.opengis.filter.identity.FeatureId;
/*     */ import org.opengis.filter.sort.SortBy;
/*     */ import org.opengis.filter.sort.SortOrder;
/*     */ 
/*     */ public class SubFeatureList extends SubFeatureCollection implements RandomFeatureAccess {
/*     */   List<SortBy> sort;
/*     */   
/*     */   List<FeatureId> index;
/*     */   
/*     */   public SubFeatureList(SimpleFeatureCollection list, Filter filter) {
/*  59 */     this(list, filter, SortBy.NATURAL_ORDER);
/*     */   }
/*     */   
/*     */   public SubFeatureList(SimpleFeatureCollection list, SortBy sort) {
/*  62 */     this(list, (Filter)Filter.INCLUDE, sort);
/*     */   }
/*     */   
/*     */   public SubFeatureList(SimpleFeatureCollection list, Filter filter, SortBy subSort) {
/*  71 */     super(list, filter);
/*  73 */     if (subSort == null || subSort.equals(SortBy.NATURAL_ORDER)) {
/*  74 */       this.sort = Collections.emptyList();
/*     */     } else {
/*  76 */       this.sort = new ArrayList<SortBy>();
/*  77 */       if (this.collection instanceof SubFeatureList) {
/*  78 */         SubFeatureList sorted = (SubFeatureList)this.collection;
/*  79 */         this.sort.addAll(sorted.sort);
/*     */       } 
/*  81 */       this.sort.add(subSort);
/*     */     } 
/*  83 */     this.index = createIndex();
/*     */   }
/*     */   
/*     */   public SubFeatureList(SimpleFeatureCollection list, List<FeatureId> order) {
/*  87 */     super(list);
/*  89 */     this.index = order;
/*  90 */     this.filter = null;
/*     */   }
/*     */   
/*     */   public SimpleFeature get(int position) {
/* 103 */     FeatureId fid = this.index.get(position);
/* 104 */     if (this.collection instanceof RandomFeatureAccess) {
/* 105 */       RandomFeatureAccess random = (RandomFeatureAccess)this.collection;
/* 106 */       return random.getFeatureMember(fid.getID());
/*     */     } 
/* 108 */     SimpleFeatureIterator it = this.collection.features();
/*     */     try {
/* 110 */       while (it.hasNext()) {
/* 111 */         SimpleFeature feature = (SimpleFeature)it.next();
/* 112 */         if (this.id.equals(feature.getID()))
/* 113 */           return feature; 
/*     */       } 
/* 116 */       throw new IndexOutOfBoundsException();
/*     */     } finally {
/* 118 */       it.close();
/*     */     } 
/*     */   }
/*     */   
/*     */   protected Filter createFilter() {
/* 124 */     FilterFactory ff = CommonFactoryFinder.getFilterFactory(null);
/* 125 */     Set<FeatureId> featureIds = new HashSet<FeatureId>();
/* 126 */     for (Iterator<FeatureId> it = this.index.iterator(); it.hasNext(); ) {
/* 127 */       FeatureId fid = it.next();
/* 128 */       featureIds.add(ff.featureId(fid.getID()));
/*     */     } 
/* 130 */     Id fids = ff.id(featureIds);
/* 132 */     return (Filter)fids;
/*     */   }
/*     */   
/*     */   protected List<FeatureId> createIndex() {
/* 137 */     List<FeatureId> fids = new ArrayList<FeatureId>();
/* 138 */     SimpleFeatureIterator it = this.collection.features();
/*     */     try {
/* 140 */       while (it.hasNext()) {
/* 141 */         SimpleFeature feature = (SimpleFeature)it.next();
/* 142 */         if (this.filter.evaluate(feature))
/* 143 */           fids.add(feature.getIdentifier()); 
/*     */       } 
/* 146 */       if (this.sort != null && !this.sort.isEmpty()) {
/* 147 */         final SortBy initialOrder = this.sort.get(this.sort.size() - 1);
/* 148 */         final FeatureIdAccessor idAccessor = new FeatureIdAccessor(true);
/* 149 */         Collections.sort(fids, new Comparator<FeatureId>() {
/*     */               public int compare(FeatureId key1, FeatureId key2) {
/* 151 */                 SimpleFeature feature1 = idAccessor.getFeature(key1.getID());
/* 152 */                 SimpleFeature feature2 = idAccessor.getFeature(key2.getID());
/* 154 */                 int compare = compare(feature1, feature2, initialOrder);
/* 155 */                 if (compare == 0 && SubFeatureList.this.sort.size() > 1)
/* 156 */                   for (int i = SubFeatureList.this.sort.size() - 1; compare == 0 && i >= 0; i--)
/* 157 */                     compare = compare(feature1, feature2, SubFeatureList.this.sort.get(i));  
/* 160 */                 return compare;
/*     */               }
/*     */               
/*     */               protected int compare(SimpleFeature feature1, SimpleFeature feature2, SortBy order) {
/* 165 */                 PropertyName name = order.getPropertyName();
/* 166 */                 Comparable<Comparable> value1 = (Comparable)name.evaluate(feature1);
/* 167 */                 Comparable<Comparable<Comparable>> value2 = (Comparable)name.evaluate(feature2);
/* 169 */                 if (value1 == value2)
/* 170 */                   return 0; 
/* 172 */                 if (order.getSortOrder() == SortOrder.ASCENDING) {
/* 173 */                   if (value1 == null)
/* 174 */                     return -1; 
/* 176 */                   return value1.compareTo(value2);
/*     */                 } 
/* 178 */                 if (value2 == null)
/* 179 */                   return -1; 
/* 181 */                 return value2.compareTo(value1);
/*     */               }
/*     */             });
/*     */       } 
/*     */     } finally {
/* 187 */       it.close();
/*     */     } 
/* 189 */     return fids;
/*     */   }
/*     */   
/*     */   public int indexOf(SimpleFeature feature) {
/* 193 */     return this.index.indexOf(feature.getIdentifier());
/*     */   }
/*     */   
/*     */   public int lastIndexOf(SimpleFeature feature) {
/* 197 */     return this.index.lastIndexOf(feature.getIdentifier());
/*     */   }
/*     */   
/*     */   public SimpleFeatureCollection subList(Filter subfilter) {
/* 211 */     if (this.filter.equals(Filter.INCLUDE))
/* 212 */       return this; 
/* 214 */     if (this.filter.equals(Filter.EXCLUDE));
/* 217 */     return new SubFeatureList(this.collection, (Filter)this.ff.and(this.filter, subfilter), this.sort.get(0));
/*     */   }
/*     */   
/*     */   public SimpleFeature getFeatureMember(String id) throws NoSuchElementException {
/* 225 */     int position = this.index.indexOf(this.ff.featureId(id));
/* 226 */     if (position == -1)
/* 227 */       throw new NoSuchElementException(id); 
/* 229 */     return (new FeatureIdAccessor(false)).getFeature(id);
/*     */   }
/*     */   
/*     */   public Iterator<SimpleFeature> openIterator() {
/* 243 */     return new SortedIteratory();
/*     */   }
/*     */   
/*     */   private class SortedIteratory implements Iterator<SimpleFeature> {
/* 247 */     Iterator<FeatureId> iterator = SubFeatureList.this.index.iterator();
/*     */     
/*     */     String id;
/*     */     
/* 249 */     SubFeatureList.FeatureIdAccessor idAccessor = new SubFeatureList.FeatureIdAccessor(true);
/*     */     
/*     */     public boolean hasNext() {
/* 252 */       return (this.iterator != null && this.iterator.hasNext());
/*     */     }
/*     */     
/*     */     public SimpleFeature next() {
/* 255 */       FeatureId fid = this.iterator.next();
/* 256 */       this.id = fid.getID();
/* 257 */       return this.idAccessor.getFeature(this.id);
/*     */     }
/*     */     
/*     */     public void remove() {
/* 260 */       SubFeatureList.this.removeFeatureMember(this.id);
/*     */     }
/*     */     
/*     */     private SortedIteratory() {}
/*     */   }
/*     */   
/*     */   private class FeatureIdAccessor {
/*     */     SoftValueHashMap<String, SimpleFeature> featureCache;
/*     */     
/*     */     private boolean cacheFeatures;
/*     */     
/*     */     public FeatureIdAccessor(boolean cacheFeatures) {
/* 270 */       this.cacheFeatures = cacheFeatures;
/* 271 */       if (cacheFeatures)
/* 272 */         this.featureCache = new SoftValueHashMap(); 
/*     */     }
/*     */     
/*     */     protected SimpleFeature getFeature(String id) {
/* 277 */       if (SubFeatureList.this.collection instanceof RandomFeatureAccess) {
/* 278 */         RandomFeatureAccess random = (RandomFeatureAccess)SubFeatureList.this.collection;
/* 279 */         return random.getFeatureMember(id);
/*     */       } 
/* 280 */       if (this.cacheFeatures) {
/* 282 */         SimpleFeature result = (SimpleFeature)this.featureCache.get(id);
/* 283 */         if (result != null)
/* 284 */           return result; 
/* 288 */         SimpleFeatureIterator simpleFeatureIterator = SubFeatureList.this.collection.features();
/*     */         try {
/* 290 */           while (simpleFeatureIterator.hasNext()) {
/* 291 */             SimpleFeature feature = (SimpleFeature)simpleFeatureIterator.next();
/* 292 */             this.featureCache.put(id, feature);
/* 293 */             if (id.equals(feature.getID()))
/* 294 */               return feature; 
/*     */           } 
/*     */         } finally {
/* 298 */           simpleFeatureIterator.close();
/*     */         } 
/* 301 */         throw new RuntimeException("Could not find feature with id " + id);
/*     */       } 
/* 304 */       SimpleFeatureIterator it = SubFeatureList.this.collection.features();
/*     */       try {
/* 306 */         while (it.hasNext()) {
/* 307 */           SimpleFeature feature = (SimpleFeature)it.next();
/* 308 */           if (id.equals(feature.getID()))
/* 309 */             return feature; 
/*     */         } 
/*     */       } finally {
/* 313 */         it.close();
/*     */       } 
/* 316 */       throw new RuntimeException("Could not find feature with id " + id);
/*     */     }
/*     */   }
/*     */   
/*     */   public SimpleFeature removeFeatureMember(String id) {
/* 323 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\feature\collection\SubFeatureList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */