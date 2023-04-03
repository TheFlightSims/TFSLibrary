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
/*     */ public abstract class AbstractFeatureCollection implements SimpleFeatureCollection {
/*     */   protected String id;
/*     */   
/*     */   protected SimpleFeatureType schema;
/*     */   
/*     */   protected AbstractFeatureCollection(SimpleFeatureType memberType) {
/*  61 */     this.id = (this.id == null) ? "featureCollection" : this.id;
/*  62 */     this.schema = memberType;
/*     */   }
/*     */   
/*     */   public SimpleFeatureIterator features() {
/*  69 */     Iterator<SimpleFeature> iterator = openIterator();
/*  70 */     if (iterator instanceof SimpleFeatureIterator)
/*  71 */       return (SimpleFeatureIterator)iterator; 
/*  73 */     SimpleFeatureIterator iter = new DelegateSimpleFeatureIterator((FeatureCollection<SimpleFeatureType, SimpleFeature>)this, iterator);
/*  74 */     return iter;
/*     */   }
/*     */   
/*     */   public boolean contains(Object o) {
/* 110 */     Iterator<SimpleFeature> e = null;
/* 111 */     e = iterator();
/*     */     try {
/* 113 */       if (o == null) {
/* 114 */         while (e.hasNext()) {
/* 115 */           if (e.next() == null)
/* 116 */             return true; 
/*     */         } 
/*     */       } else {
/* 118 */         while (e.hasNext()) {
/* 119 */           if (o.equals(e.next()))
/* 120 */             return true; 
/*     */         } 
/*     */       } 
/* 122 */       return false;
/*     */     } finally {
/* 124 */       if (e instanceof FeatureIterator)
/* 125 */         ((FeatureIterator)e).close(); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean containsAll(Collection<?> c) {
/* 142 */     Iterator<?> e = c.iterator();
/* 143 */     while (e.hasNext()) {
/* 144 */       if (!contains(e.next()))
/* 145 */         return false; 
/*     */     } 
/* 148 */     return true;
/*     */   }
/*     */   
/*     */   public final Iterator<SimpleFeature> iterator() {
/* 163 */     Iterator<SimpleFeature> iterator = openIterator();
/* 164 */     return iterator;
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 171 */     Iterator<SimpleFeature> iterator = iterator();
/*     */     try {
/* 173 */       return !iterator.hasNext();
/*     */     } finally {
/* 176 */       if (iterator instanceof FeatureIterator)
/* 177 */         ((FeatureIterator)iterator).close(); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public Object[] toArray() {
/* 188 */     Object[] result = new Object[size()];
/* 189 */     Iterator<SimpleFeature> e = null;
/*     */     try {
/* 191 */       e = iterator();
/* 192 */       for (int i = 0; e.hasNext(); i++)
/* 193 */         result[i] = e.next(); 
/* 194 */       return result;
/*     */     } finally {
/* 197 */       if (e instanceof FeatureIterator)
/* 198 */         ((FeatureIterator)e).close(); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public <O> O[] toArray(O[] a) {
/* 205 */     int size = size();
/* 206 */     if (a.length < size)
/* 207 */       a = (O[])Array.newInstance(a.getClass().getComponentType(), size); 
/* 209 */     Iterator<SimpleFeature> it = iterator();
/*     */     try {
/* 211 */       O[] arrayOfO = a;
/* 212 */       for (int i = 0; i < size; i++)
/* 213 */         arrayOfO[i] = (O)it.next(); 
/* 214 */       if (a.length > size)
/* 215 */         a[size] = null; 
/* 216 */       return a;
/*     */     } finally {
/* 219 */       if (it instanceof FeatureIterator)
/* 220 */         ((FeatureIterator)it).close(); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void accepts(FeatureVisitor visitor, ProgressListener progress) throws IOException {
/* 226 */     DataUtilities.visit((FeatureCollection)this, visitor, progress);
/*     */   }
/*     */   
/*     */   public SimpleFeatureCollection subCollection(Filter filter) {
/* 233 */     if (filter == Filter.INCLUDE)
/* 234 */       return this; 
/* 236 */     return new SubFeatureCollection(this, filter);
/*     */   }
/*     */   
/*     */   public SimpleFeatureCollection sort(SortBy order) {
/* 240 */     return new SubFeatureList(this, order);
/*     */   }
/*     */   
/*     */   public String getID() {
/* 244 */     return this.id;
/*     */   }
/*     */   
/*     */   public SimpleFeatureType getSchema() {
/* 248 */     return this.schema;
/*     */   }
/*     */   
/*     */   protected abstract Iterator<SimpleFeature> openIterator();
/*     */   
/*     */   public abstract int size();
/*     */   
/*     */   public abstract ReferencedEnvelope getBounds();
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\feature\collection\AbstractFeatureCollection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */