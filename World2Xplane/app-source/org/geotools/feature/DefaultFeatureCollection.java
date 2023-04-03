/*     */ package org.geotools.feature;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.NoSuchElementException;
/*     */ import java.util.Set;
/*     */ import java.util.SortedMap;
/*     */ import java.util.TreeMap;
/*     */ import java.util.logging.Logger;
/*     */ import org.geotools.data.DataSourceException;
/*     */ import org.geotools.data.DataUtilities;
/*     */ import org.geotools.data.FeatureReader;
/*     */ import org.geotools.data.simple.SimpleFeatureCollection;
/*     */ import org.geotools.data.simple.SimpleFeatureIterator;
/*     */ import org.geotools.feature.collection.FeatureIteratorImpl;
/*     */ import org.geotools.feature.collection.SimpleFeatureIteratorImpl;
/*     */ import org.geotools.feature.collection.SubFeatureCollection;
/*     */ import org.geotools.feature.simple.SimpleFeatureBuilder;
/*     */ import org.geotools.filter.SortBy2;
/*     */ import org.geotools.geometry.jts.ReferencedEnvelope;
/*     */ import org.geotools.util.logging.Logging;
/*     */ import org.opengis.feature.Feature;
/*     */ import org.opengis.feature.FeatureVisitor;
/*     */ import org.opengis.feature.simple.SimpleFeature;
/*     */ import org.opengis.feature.simple.SimpleFeatureType;
/*     */ import org.opengis.feature.type.FeatureType;
/*     */ import org.opengis.filter.Filter;
/*     */ import org.opengis.filter.sort.SortBy;
/*     */ import org.opengis.geometry.BoundingBox;
/*     */ import org.opengis.util.ProgressListener;
/*     */ 
/*     */ public class DefaultFeatureCollection implements SimpleFeatureCollection, Collection<SimpleFeature> {
/*  65 */   protected static Logger LOGGER = Logging.getLogger("org.geotools.feature");
/*     */   
/*  74 */   private SortedMap<String, SimpleFeature> contents = new TreeMap<String, SimpleFeature>();
/*     */   
/*  80 */   private ReferencedEnvelope bounds = null;
/*     */   
/*     */   protected String id;
/*     */   
/*     */   protected SimpleFeatureType schema;
/*     */   
/*     */   public DefaultFeatureCollection() {
/*  90 */     this(null, null);
/*     */   }
/*     */   
/*     */   public DefaultFeatureCollection(FeatureCollection<SimpleFeatureType, SimpleFeature> collection) {
/* 100 */     this(collection.getID(), collection.getSchema());
/* 101 */     addAll(collection);
/*     */   }
/*     */   
/*     */   public DefaultFeatureCollection(String id) {
/* 112 */     this(id, null);
/*     */   }
/*     */   
/*     */   public DefaultFeatureCollection(String id, SimpleFeatureType memberType) {
/* 122 */     this.id = (id == null) ? "featureCollection" : id;
/* 123 */     this.schema = memberType;
/*     */   }
/*     */   
/*     */   public ReferencedEnvelope getBounds() {
/* 140 */     if (this.bounds == null) {
/* 141 */       this.bounds = new ReferencedEnvelope();
/* 143 */       for (Iterator<SimpleFeature> i = this.contents.values().iterator(); i.hasNext(); ) {
/* 144 */         BoundingBox geomBounds = ((SimpleFeature)i.next()).getBounds();
/* 148 */         if (!geomBounds.isEmpty())
/* 149 */           this.bounds.include(geomBounds); 
/*     */       } 
/*     */     } 
/* 153 */     return this.bounds;
/*     */   }
/*     */   
/*     */   public boolean add(SimpleFeature o) {
/* 184 */     return add(o, true);
/*     */   }
/*     */   
/*     */   protected boolean add(SimpleFeature feature, boolean fire) {
/* 188 */     if (feature == null)
/* 188 */       return false; 
/* 189 */     String ID = feature.getID();
/* 190 */     if (ID == null)
/* 190 */       return false; 
/* 191 */     if (this.contents.containsKey(ID))
/* 191 */       return false; 
/* 193 */     if (this.schema == null)
/* 194 */       this.schema = feature.getFeatureType(); 
/* 196 */     SimpleFeatureType childType = getSchema();
/* 197 */     if (!feature.getFeatureType().equals(childType))
/* 198 */       LOGGER.warning("Feature Collection contains a heterogeneous mix of features"); 
/* 202 */     this.contents.put(ID, feature);
/* 203 */     return true;
/*     */   }
/*     */   
/*     */   public boolean addAll(Collection<? extends SimpleFeature> collection) {
/* 223 */     boolean changed = false;
/* 225 */     Iterator<?> iterator = collection.iterator();
/*     */     try {
/* 227 */       while (iterator.hasNext()) {
/* 228 */         SimpleFeature f = (SimpleFeature)iterator.next();
/* 229 */         boolean added = add(f, false);
/* 230 */         changed |= added;
/*     */       } 
/* 232 */       return changed;
/*     */     } finally {
/* 235 */       if (iterator instanceof FeatureIterator)
/* 236 */         ((FeatureIterator)iterator).close(); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean addAll(FeatureCollection<?, ?> collection) {
/* 243 */     boolean changed = false;
/* 245 */     FeatureIterator<?> iterator = collection.features();
/*     */     try {
/* 247 */       while (iterator.hasNext()) {
/* 248 */         SimpleFeature f = (SimpleFeature)iterator.next();
/* 249 */         boolean added = add(f, false);
/* 250 */         changed |= added;
/*     */       } 
/* 252 */       return changed;
/*     */     } finally {
/* 255 */       iterator.close();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void clear() {
/* 265 */     this.contents.clear();
/*     */   }
/*     */   
/*     */   public boolean contains(Object o) {
/* 282 */     if (!(o instanceof SimpleFeature))
/* 282 */       return false; 
/* 284 */     SimpleFeature feature = (SimpleFeature)o;
/* 285 */     String ID = feature.getID();
/* 287 */     return this.contents.containsKey(ID);
/*     */   }
/*     */   
/*     */   public boolean containsAll(Collection<?> collection) {
/* 297 */     Iterator<?> iterator = collection.iterator();
/*     */     try {
/* 299 */       while (iterator.hasNext()) {
/* 300 */         SimpleFeature feature = (SimpleFeature)iterator.next();
/* 301 */         if (!this.contents.containsKey(feature.getID()))
/* 302 */           return false; 
/*     */       } 
/* 305 */       return true;
/*     */     } finally {
/* 308 */       if (iterator instanceof FeatureIterator)
/* 309 */         ((FeatureIterator)iterator).close(); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 320 */     return this.contents.isEmpty();
/*     */   }
/*     */   
/*     */   public Iterator<SimpleFeature> iterator() {
/* 333 */     final Iterator<SimpleFeature> iterator = this.contents.values().iterator();
/* 334 */     return new Iterator<SimpleFeature>() {
/* 335 */         SimpleFeature currFeature = null;
/*     */         
/*     */         public boolean hasNext() {
/* 338 */           return iterator.hasNext();
/*     */         }
/*     */         
/*     */         public SimpleFeature next() {
/* 342 */           this.currFeature = iterator.next();
/* 343 */           return this.currFeature;
/*     */         }
/*     */         
/*     */         public void remove() {
/* 347 */           iterator.remove();
/* 348 */           DefaultFeatureCollection.this.bounds = null;
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   public SimpleFeatureIterator features() {
/* 360 */     return (SimpleFeatureIterator)new SimpleFeatureIteratorImpl(this.contents.values());
/*     */   }
/*     */   
/*     */   public boolean remove(Object o) {
/* 377 */     if (!(o instanceof SimpleFeature))
/* 377 */       return false; 
/* 379 */     SimpleFeature f = (SimpleFeature)o;
/* 380 */     boolean changed = this.contents.values().remove(f);
/* 381 */     return changed;
/*     */   }
/*     */   
/*     */   public boolean removeAll(Collection<?> collection) {
/* 398 */     boolean changed = false;
/* 399 */     Iterator<?> iterator = collection.iterator();
/*     */     try {
/* 401 */       while (iterator.hasNext()) {
/* 402 */         SimpleFeature f = (SimpleFeature)iterator.next();
/* 403 */         boolean removed = this.contents.values().remove(f);
/* 405 */         if (removed)
/* 406 */           changed = true; 
/*     */       } 
/* 409 */       return changed;
/*     */     } finally {
/* 412 */       if (iterator instanceof FeatureIterator)
/* 413 */         ((FeatureIterator)iterator).close(); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean retainAll(Collection<?> collection) {
/* 432 */     boolean modified = false;
/* 433 */     for (Iterator<?> it = this.contents.values().iterator(); it.hasNext(); ) {
/* 434 */       SimpleFeature f = (SimpleFeature)it.next();
/* 435 */       if (!collection.contains(f)) {
/* 436 */         it.remove();
/* 437 */         modified = true;
/*     */       } 
/*     */     } 
/* 440 */     return modified;
/*     */   }
/*     */   
/*     */   public int size() {
/* 451 */     return this.contents.size();
/*     */   }
/*     */   
/*     */   public Object[] toArray() {
/* 475 */     return this.contents.values().toArray();
/*     */   }
/*     */   
/*     */   public <T> T[] toArray(T[] a) {
/* 528 */     return (T[])this.contents.values().toArray((Object[])a);
/*     */   }
/*     */   
/*     */   public void close(FeatureIterator<SimpleFeature> close) {
/* 533 */     if (close instanceof FeatureIteratorImpl) {
/* 534 */       FeatureIteratorImpl<SimpleFeature> wrapper = (FeatureIteratorImpl<SimpleFeature>)close;
/* 535 */       wrapper.close();
/*     */     } 
/*     */   }
/*     */   
/*     */   public FeatureReader<SimpleFeatureType, SimpleFeature> reader() throws IOException {
/* 544 */     final SimpleFeatureIterator iterator = features();
/* 545 */     return new FeatureReader<SimpleFeatureType, SimpleFeature>() {
/*     */         public SimpleFeatureType getFeatureType() {
/* 547 */           return DefaultFeatureCollection.this.getSchema();
/*     */         }
/*     */         
/*     */         public SimpleFeature next() throws IOException, IllegalAttributeException, NoSuchElementException {
/* 550 */           return (SimpleFeature)iterator.next();
/*     */         }
/*     */         
/*     */         public boolean hasNext() throws IOException {
/* 554 */           return iterator.hasNext();
/*     */         }
/*     */         
/*     */         public void close() throws IOException {
/* 558 */           DefaultFeatureCollection.this.close((FeatureIterator<SimpleFeature>)iterator);
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   public int getCount() throws IOException {
/* 564 */     return this.contents.size();
/*     */   }
/*     */   
/*     */   public SimpleFeatureCollection collection() throws IOException {
/* 568 */     DefaultFeatureCollection copy = new DefaultFeatureCollection(null, getSchema());
/* 569 */     List<SimpleFeature> list = new ArrayList<SimpleFeature>(this.contents.size());
/* 570 */     SimpleFeatureIterator iterator = features();
/*     */     try {
/* 572 */       while (iterator.hasNext()) {
/* 573 */         SimpleFeature duplicate, feature = (SimpleFeature)iterator.next();
/*     */         try {
/* 576 */           duplicate = SimpleFeatureBuilder.copy(feature);
/* 577 */         } catch (IllegalAttributeException e) {
/* 578 */           throw new DataSourceException("Unable to copy " + feature.getID(), e);
/*     */         } 
/* 580 */         list.add(duplicate);
/*     */       } 
/*     */     } finally {
/* 584 */       iterator.close();
/*     */     } 
/* 586 */     copy.addAll(list);
/* 587 */     return copy;
/*     */   }
/*     */   
/*     */   public Set fids() {
/* 597 */     return Collections.unmodifiableSet(this.contents.keySet());
/*     */   }
/*     */   
/*     */   public void accepts(FeatureVisitor visitor, ProgressListener progress) throws IOException {
/* 601 */     DataUtilities.visit((FeatureCollection)this, visitor, progress);
/*     */   }
/*     */   
/*     */   public SimpleFeatureCollection subCollection(Filter filter) {
/* 618 */     if (filter == Filter.INCLUDE)
/* 619 */       return this; 
/* 621 */     return (SimpleFeatureCollection)new SubFeatureCollection(this, filter);
/*     */   }
/*     */   
/*     */   public SimpleFeatureCollection sort(SortBy order) {
/* 642 */     if (order == SortBy.NATURAL_ORDER)
/* 643 */       return this; 
/* 645 */     if (order instanceof SortBy2) {
/* 646 */       SortBy2 advanced = (SortBy2)order;
/* 647 */       return sort(advanced);
/*     */     } 
/* 649 */     return null;
/*     */   }
/*     */   
/*     */   public SimpleFeatureCollection sort(SortBy2 order) {
/* 662 */     if (order == SortBy.NATURAL_ORDER)
/* 663 */       return this; 
/* 665 */     if (order == SortBy.REVERSE_ORDER);
/* 669 */     return null;
/*     */   }
/*     */   
/*     */   public void purge() {}
/*     */   
/*     */   public void validate() {}
/*     */   
/*     */   public String getID() {
/* 679 */     return this.id;
/*     */   }
/*     */   
/*     */   public SimpleFeatureType getSchema() {
/* 683 */     return this.schema;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\feature\DefaultFeatureCollection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */