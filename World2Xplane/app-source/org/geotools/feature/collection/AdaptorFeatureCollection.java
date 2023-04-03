/*     */ package org.geotools.feature.collection;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.lang.reflect.Array;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import org.geotools.data.DataUtilities;
/*     */ import org.geotools.data.simple.SimpleFeatureCollection;
/*     */ import org.geotools.data.simple.SimpleFeatureIterator;
/*     */ import org.geotools.feature.FeatureCollection;
/*     */ import org.geotools.feature.FeatureIterator;
/*     */ import org.geotools.geometry.jts.ReferencedEnvelope;
/*     */ import org.opengis.feature.FeatureVisitor;
/*     */ import org.opengis.feature.simple.SimpleFeature;
/*     */ import org.opengis.feature.simple.SimpleFeatureType;
/*     */ import org.opengis.feature.type.FeatureType;
/*     */ import org.opengis.filter.Filter;
/*     */ import org.opengis.filter.sort.SortBy;
/*     */ import org.opengis.util.ProgressListener;
/*     */ 
/*     */ public abstract class AdaptorFeatureCollection implements SimpleFeatureCollection {
/*     */   protected String id;
/*     */   
/*     */   protected SimpleFeatureType schema;
/*     */   
/*     */   public AdaptorFeatureCollection(String id, SimpleFeatureType memberType) {
/*  59 */     this.id = (id == null) ? "featureCollection" : id;
/*  60 */     this.schema = memberType;
/*     */   }
/*     */   
/*     */   public SimpleFeatureIterator features() {
/*  67 */     SimpleFeatureIterator iter = new DelegateSimpleFeatureIterator((FeatureCollection<SimpleFeatureType, SimpleFeature>)this, openIterator());
/*  68 */     return iter;
/*     */   }
/*     */   
/*     */   public void close(FeatureIterator<SimpleFeature> close) {
/*  71 */     if (close != null)
/*  72 */       close.close(); 
/*     */   }
/*     */   
/*     */   public void close(SimpleFeatureIterator close) {
/*  76 */     if (close != null)
/*  77 */       closeIterator(close); 
/*     */   }
/*     */   
/*     */   public void closeIterator(SimpleFeatureIterator close) {
/*  81 */     DelegateSimpleFeatureIterator iter = (DelegateSimpleFeatureIterator)close;
/*  82 */     closeIterator(iter.delegate);
/*  83 */     iter.close();
/*     */   }
/*     */   
/*     */   public void accepts(FeatureVisitor visitor, ProgressListener progress) throws IOException {
/*  91 */     DataUtilities.visit((FeatureCollection)this, visitor, progress);
/*     */   }
/*     */   
/*     */   public SimpleFeatureCollection subList(Filter filter) {
/*  98 */     return new SubFeatureList(this, filter);
/*     */   }
/*     */   
/*     */   public SimpleFeatureCollection subCollection(Filter filter) {
/* 102 */     if (filter == Filter.INCLUDE)
/* 103 */       return this; 
/* 105 */     return new SubFeatureCollection(this, filter);
/*     */   }
/*     */   
/*     */   public SimpleFeatureCollection sort(SortBy order) {
/* 109 */     return new SubFeatureList(this, order);
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 119 */     return (size() == 0);
/*     */   }
/*     */   
/*     */   public boolean contains(Object o) {
/* 134 */     Iterator<SimpleFeature> e = null;
/*     */     try {
/* 136 */       e = iterator();
/* 137 */       if (o == null) {
/* 138 */         while (e.hasNext()) {
/* 139 */           if (e.next() == null)
/* 140 */             return true; 
/*     */         } 
/*     */       } else {
/* 142 */         while (e.hasNext()) {
/* 143 */           if (o.equals(e.next()))
/* 144 */             return true; 
/*     */         } 
/*     */       } 
/* 146 */       return false;
/*     */     } finally {
/* 149 */       close(e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public Object[] toArray() {
/* 160 */     Object[] result = new Object[size()];
/* 161 */     Iterator<SimpleFeature> e = null;
/*     */     try {
/* 163 */       e = iterator();
/* 164 */       for (int i = 0; e.hasNext(); i++)
/* 165 */         result[i] = e.next(); 
/* 166 */       return result;
/*     */     } finally {
/* 168 */       close(e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public Object[] toArray(Object[] a) {
/* 173 */     int size = size();
/* 174 */     if (a.length < size)
/* 175 */       a = (Object[])Array.newInstance(a.getClass().getComponentType(), size); 
/* 178 */     Iterator<SimpleFeature> it = iterator();
/*     */     try {
/* 181 */       Object[] result = a;
/* 182 */       for (int i = 0; i < size; i++)
/* 183 */         result[i] = it.next(); 
/* 184 */       if (a.length > size)
/* 185 */         a[size] = null; 
/* 186 */       return a;
/*     */     } finally {
/* 189 */       close(it);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean containsAll(Collection c) {
/* 207 */     Iterator e = c.iterator();
/*     */     try {
/* 209 */       while (e.hasNext()) {
/* 210 */         if (!contains(e.next()))
/* 211 */           return false; 
/*     */       } 
/* 212 */       return true;
/*     */     } finally {
/* 214 */       close(e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public String toString() {
/* 225 */     StringBuffer buf = new StringBuffer();
/* 226 */     buf.append("[");
/* 227 */     Iterator<SimpleFeature> i = iterator();
/*     */     try {
/* 229 */       boolean hasNext = i.hasNext();
/* 230 */       while (hasNext) {
/* 231 */         Object o = i.next();
/* 232 */         buf.append((o == this) ? "(this Collection)" : String.valueOf(o));
/* 233 */         hasNext = i.hasNext();
/* 234 */         if (hasNext)
/* 235 */           buf.append(", "); 
/*     */       } 
/* 237 */       buf.append("]");
/* 238 */       return buf.toString();
/*     */     } finally {
/* 240 */       close(i);
/*     */     } 
/*     */   }
/*     */   
/*     */   public final Iterator<SimpleFeature> iterator() {
/* 262 */     Iterator<SimpleFeature> iterator = openIterator();
/* 263 */     return iterator;
/*     */   }
/*     */   
/*     */   public final void close(Iterator<SimpleFeature> close) {
/* 292 */     if (close == null)
/*     */       return; 
/*     */     try {
/* 294 */       closeIterator(close);
/* 296 */     } catch (Throwable e) {}
/*     */   }
/*     */   
/*     */   public String getID() {
/* 328 */     return this.id;
/*     */   }
/*     */   
/*     */   public SimpleFeatureType getSchema() {
/* 332 */     return this.schema;
/*     */   }
/*     */   
/*     */   public ReferencedEnvelope getBounds() {
/* 339 */     throw new UnsupportedOperationException("Subclasses " + getClass().getSimpleName() + " should override");
/*     */   }
/*     */   
/*     */   public abstract int size();
/*     */   
/*     */   protected abstract Iterator<SimpleFeature> openIterator();
/*     */   
/*     */   protected abstract void closeIterator(Iterator<SimpleFeature> paramIterator);
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\feature\collection\AdaptorFeatureCollection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */