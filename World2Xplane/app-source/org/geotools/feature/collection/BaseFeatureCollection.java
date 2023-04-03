/*     */ package org.geotools.feature.collection;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.lang.reflect.Array;
/*     */ import java.util.Collection;
/*     */ import org.geotools.data.DataUtilities;
/*     */ import org.geotools.data.simple.SimpleFeatureCollection;
/*     */ import org.geotools.data.store.FilteringFeatureCollection;
/*     */ import org.geotools.feature.FeatureCollection;
/*     */ import org.geotools.feature.FeatureIterator;
/*     */ import org.geotools.geometry.jts.ReferencedEnvelope;
/*     */ import org.opengis.feature.Feature;
/*     */ import org.opengis.feature.FeatureVisitor;
/*     */ import org.opengis.feature.type.FeatureType;
/*     */ import org.opengis.filter.Filter;
/*     */ import org.opengis.filter.sort.SortBy;
/*     */ import org.opengis.geometry.BoundingBox;
/*     */ import org.opengis.util.ProgressListener;
/*     */ 
/*     */ public abstract class BaseFeatureCollection<T extends FeatureType, F extends Feature> implements FeatureCollection<T, F> {
/*     */   protected String id;
/*     */   
/*     */   protected T schema;
/*     */   
/*     */   protected BaseFeatureCollection() {
/*  65 */     this(null, null);
/*     */   }
/*     */   
/*     */   protected BaseFeatureCollection(T schema) {
/*  68 */     this(schema, null);
/*     */   }
/*     */   
/*     */   protected BaseFeatureCollection(T schema, String id) {
/*  71 */     this.id = (id == null) ? "featureCollection" : id;
/*  72 */     this.schema = schema;
/*     */   }
/*     */   
/*     */   public String getID() {
/*  76 */     return this.id;
/*     */   }
/*     */   
/*     */   public T getSchema() {
/*  80 */     return this.schema;
/*     */   }
/*     */   
/*     */   public abstract FeatureIterator<F> features();
/*     */   
/*     */   public boolean contains(Object o) {
/* 104 */     FeatureIterator<F> e = features();
/*     */     try {
/* 106 */       if (o == null) {
/* 107 */         while (e.hasNext()) {
/* 108 */           if (e.next() == null)
/* 109 */             return true; 
/*     */         } 
/*     */       } else {
/* 113 */         while (e.hasNext()) {
/* 114 */           if (o.equals(e.next()))
/* 115 */             return true; 
/*     */         } 
/*     */       } 
/* 119 */       return false;
/*     */     } finally {
/* 121 */       e.close();
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean containsAll(Collection<?> c) {
/* 136 */     FeatureIterator<F> e = features();
/*     */     try {
/* 138 */       while (e.hasNext()) {
/* 139 */         Feature feature = e.next();
/* 140 */         if (!c.contains(feature))
/* 141 */           return false; 
/*     */       } 
/* 144 */       return true;
/*     */     } finally {
/* 146 */       e.close();
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 154 */     FeatureIterator<F> iterator = features();
/*     */     try {
/* 156 */       return !iterator.hasNext();
/*     */     } finally {
/* 158 */       iterator.close();
/*     */     } 
/*     */   }
/*     */   
/*     */   public Object[] toArray() {
/* 168 */     Object[] result = new Object[size()];
/* 169 */     FeatureIterator<F> e = null;
/*     */     try {
/* 171 */       e = features();
/* 172 */       for (int i = 0; e.hasNext(); i++)
/* 173 */         result[i] = e.next(); 
/* 174 */       return result;
/*     */     } finally {
/* 176 */       e.close();
/*     */     } 
/*     */   }
/*     */   
/*     */   public <O> O[] toArray(O[] a) {
/* 182 */     int size = size();
/* 183 */     if (a.length < size)
/* 184 */       a = (O[])Array.newInstance(a.getClass().getComponentType(), size); 
/* 186 */     FeatureIterator<F> it = features();
/*     */     try {
/* 188 */       O[] arrayOfO = a;
/* 189 */       for (int i = 0; i < size; i++)
/* 190 */         arrayOfO[i] = (O)it.next(); 
/* 191 */       if (a.length > size)
/* 192 */         a[size] = null; 
/* 193 */       return a;
/*     */     } finally {
/* 195 */       it.close();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void accepts(FeatureVisitor visitor, ProgressListener progress) throws IOException {
/* 201 */     DataUtilities.visit(this, visitor, progress);
/*     */   }
/*     */   
/*     */   public FeatureCollection<T, F> subCollection(Filter filter) {
/* 216 */     if (filter == Filter.INCLUDE)
/* 217 */       return this; 
/* 219 */     return (FeatureCollection<T, F>)new FilteringFeatureCollection(this, filter);
/*     */   }
/*     */   
/*     */   public FeatureCollection<T, F> sort(SortBy order) {
/* 231 */     if (getSchema() instanceof org.opengis.feature.simple.SimpleFeatureType) {
/* 234 */       SimpleFeatureCollection simple = DataUtilities.simple(this);
/* 235 */       return (FeatureCollection<T, F>)new SortedSimpleFeatureCollection(simple, new SortBy[] { order });
/*     */     } 
/* 239 */     throw new UnsupportedOperationException("Cannot sort on complex features at the moment");
/*     */   }
/*     */   
/*     */   public int size() {
/* 249 */     int count = 0;
/* 250 */     FeatureIterator<F> it = features();
/*     */     try {
/* 252 */       while (it.hasNext()) {
/* 254 */         Feature feature = it.next();
/* 255 */         count++;
/*     */       } 
/*     */     } finally {
/* 258 */       it.close();
/*     */     } 
/* 260 */     return count;
/*     */   }
/*     */   
/*     */   public ReferencedEnvelope getBounds() {
/* 269 */     ReferencedEnvelope bounds = null;
/* 270 */     FeatureIterator<F> it = features();
/*     */     try {
/* 272 */       while (it.hasNext()) {
/* 273 */         Feature feature = it.next();
/* 274 */         BoundingBox bbox = feature.getBounds();
/* 275 */         if (bbox != null) {
/* 276 */           if (bounds == null) {
/* 277 */             bounds = new ReferencedEnvelope(bbox);
/*     */             continue;
/*     */           } 
/* 280 */           bounds.include(bbox);
/*     */         } 
/*     */       } 
/*     */     } finally {
/* 285 */       it.close();
/*     */     } 
/* 287 */     return bounds;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\feature\collection\BaseFeatureCollection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */