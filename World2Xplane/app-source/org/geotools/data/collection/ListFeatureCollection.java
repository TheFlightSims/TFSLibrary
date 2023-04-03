/*     */ package org.geotools.data.collection;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Envelope;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.NoSuchElementException;
/*     */ import org.geotools.data.Query;
/*     */ import org.geotools.data.simple.SimpleFeatureCollection;
/*     */ import org.geotools.data.simple.SimpleFeatureIterator;
/*     */ import org.geotools.feature.FeatureCollection;
/*     */ import org.geotools.feature.FeatureIterator;
/*     */ import org.geotools.feature.collection.AbstractFeatureCollection;
/*     */ import org.geotools.geometry.jts.ReferencedEnvelope;
/*     */ import org.opengis.feature.Feature;
/*     */ import org.opengis.feature.FeatureVisitor;
/*     */ import org.opengis.feature.simple.SimpleFeature;
/*     */ import org.opengis.feature.simple.SimpleFeatureType;
/*     */ import org.opengis.filter.Filter;
/*     */ import org.opengis.filter.sort.SortBy;
/*     */ import org.opengis.geometry.BoundingBox;
/*     */ 
/*     */ public class ListFeatureCollection extends AbstractFeatureCollection implements Collection<SimpleFeature> {
/*     */   private List<SimpleFeature> list;
/*     */   
/*  68 */   private ReferencedEnvelope bounds = null;
/*     */   
/*     */   public ListFeatureCollection(SimpleFeatureType schema) {
/*  76 */     this(schema, new ArrayList<SimpleFeature>());
/*     */   }
/*     */   
/*     */   public ListFeatureCollection(SimpleFeatureType schema, List<SimpleFeature> list) {
/*  94 */     super(schema);
/*  95 */     this.list = list;
/*     */   }
/*     */   
/*     */   public ListFeatureCollection(SimpleFeatureCollection copy) throws IOException {
/* 113 */     this((SimpleFeatureType)copy.getSchema());
/* 114 */     copy.accepts(new FeatureVisitor() {
/*     */           public void visit(Feature feature) {
/* 116 */             ListFeatureCollection.this.list.add((SimpleFeature)feature);
/*     */           }
/*     */         },  null);
/*     */   }
/*     */   
/*     */   public int size() {
/* 123 */     return this.list.size();
/*     */   }
/*     */   
/*     */   protected Iterator<SimpleFeature> openIterator() {
/* 128 */     Iterator<SimpleFeature> it = this.list.iterator();
/* 129 */     return it;
/*     */   }
/*     */   
/*     */   public boolean add(SimpleFeature f) {
/* 135 */     BoundingBox boundingBox = f.getBounds();
/* 136 */     if (this.bounds == null) {
/* 137 */       this.bounds = new ReferencedEnvelope(boundingBox.getMinX(), boundingBox.getMaxX(), boundingBox.getMinY(), boundingBox.getMaxY(), this.schema.getCoordinateReferenceSystem());
/*     */     } else {
/* 142 */       this.bounds.expandToInclude(boundingBox.getMinX(), boundingBox.getMinY());
/* 143 */       this.bounds.expandToInclude(boundingBox.getMaxX(), boundingBox.getMaxY());
/*     */     } 
/* 145 */     return this.list.add(f);
/*     */   }
/*     */   
/*     */   public void clear() {
/* 150 */     this.list.clear();
/* 152 */     this.bounds = null;
/*     */   }
/*     */   
/*     */   public SimpleFeatureIterator features() {
/* 157 */     return new ListFeatureIterator(this.list);
/*     */   }
/*     */   
/*     */   public synchronized ReferencedEnvelope getBounds() {
/* 162 */     if (this.bounds == null)
/* 163 */       this.bounds = calculateBounds(); 
/* 165 */     return this.bounds;
/*     */   }
/*     */   
/*     */   private ReferencedEnvelope calculateBounds() {
/* 172 */     ReferencedEnvelope extent = new ReferencedEnvelope();
/* 173 */     for (SimpleFeature feature : this.list) {
/* 174 */       if (feature == null)
/*     */         continue; 
/* 175 */       ReferencedEnvelope bbox = ReferencedEnvelope.reference(feature.getBounds());
/* 176 */       if (bbox == null || bbox.isEmpty() || bbox.isNull())
/*     */         continue; 
/* 177 */       extent.expandToInclude((Envelope)bbox);
/*     */     } 
/* 179 */     return new ReferencedEnvelope((Envelope)extent, this.schema.getCoordinateReferenceSystem());
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 184 */     return this.list.isEmpty();
/*     */   }
/*     */   
/*     */   private class ListFeatureIterator implements SimpleFeatureIterator {
/*     */     private Iterator<SimpleFeature> iter;
/*     */     
/*     */     public ListFeatureIterator(List<SimpleFeature> features) {
/* 194 */       this.iter = features.iterator();
/*     */     }
/*     */     
/*     */     public void close() {
/* 199 */       if (this.iter instanceof FeatureIterator)
/* 200 */         ((FeatureIterator)this.iter).close(); 
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/* 205 */       return this.iter.hasNext();
/*     */     }
/*     */     
/*     */     public SimpleFeature next() throws NoSuchElementException {
/* 209 */       return this.iter.next();
/*     */     }
/*     */   }
/*     */   
/*     */   public SimpleFeatureCollection subCollection(Filter filter) {
/* 215 */     CollectionFeatureSource temp = new CollectionFeatureSource((SimpleFeatureCollection)this);
/* 216 */     return temp.getFeatures(filter);
/*     */   }
/*     */   
/*     */   public SimpleFeatureCollection sort(SortBy order) {
/* 221 */     Query subQuery = new Query(getSchema().getTypeName());
/* 222 */     subQuery.setSortBy(new SortBy[] { order });
/* 224 */     CollectionFeatureSource temp = new CollectionFeatureSource((SimpleFeatureCollection)this);
/* 225 */     return temp.getFeatures(subQuery);
/*     */   }
/*     */   
/*     */   public boolean remove(Object o) {
/* 229 */     boolean removed = this.list.remove(o);
/* 230 */     if (removed)
/* 231 */       this.bounds = null; 
/* 233 */     return removed;
/*     */   }
/*     */   
/*     */   public boolean addAll(Collection<? extends SimpleFeature> c) {
/* 237 */     boolean changed = false;
/* 238 */     for (SimpleFeature feature : c) {
/* 239 */       boolean added = add(feature);
/* 240 */       if (!changed && added)
/* 241 */         changed = true; 
/*     */     } 
/* 244 */     return changed;
/*     */   }
/*     */   
/*     */   public boolean removeAll(Collection<?> c) {
/* 248 */     this.bounds = null;
/* 249 */     return this.list.removeAll(c);
/*     */   }
/*     */   
/*     */   public boolean retainAll(Collection<?> c) {
/* 253 */     this.bounds = null;
/* 254 */     return this.list.retainAll(c);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\collection\ListFeatureCollection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */