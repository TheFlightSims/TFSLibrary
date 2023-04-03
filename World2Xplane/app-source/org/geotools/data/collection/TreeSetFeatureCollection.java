/*     */ package org.geotools.data.collection;
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
/*     */ import org.geotools.data.Query;
/*     */ import org.geotools.data.simple.SimpleFeatureCollection;
/*     */ import org.geotools.data.simple.SimpleFeatureIterator;
/*     */ import org.geotools.feature.FeatureCollection;
/*     */ import org.geotools.feature.FeatureIterator;
/*     */ import org.geotools.feature.collection.FeatureIteratorImpl;
/*     */ import org.geotools.feature.collection.SimpleFeatureIteratorImpl;
/*     */ import org.geotools.feature.simple.SimpleFeatureBuilder;
/*     */ import org.geotools.geometry.jts.ReferencedEnvelope;
/*     */ import org.geotools.util.logging.Logging;
/*     */ import org.opengis.feature.Feature;
/*     */ import org.opengis.feature.FeatureVisitor;
/*     */ import org.opengis.feature.IllegalAttributeException;
/*     */ import org.opengis.feature.simple.SimpleFeature;
/*     */ import org.opengis.feature.simple.SimpleFeatureType;
/*     */ import org.opengis.feature.type.FeatureType;
/*     */ import org.opengis.filter.Filter;
/*     */ import org.opengis.filter.sort.SortBy;
/*     */ import org.opengis.geometry.BoundingBox;
/*     */ import org.opengis.util.ProgressListener;
/*     */ 
/*     */ public class TreeSetFeatureCollection implements SimpleFeatureCollection {
/*  85 */   protected static Logger LOGGER = Logging.getLogger("org.geotools.data.collection");
/*     */   
/*  94 */   private SortedMap<String, SimpleFeature> contents = new TreeMap<String, SimpleFeature>();
/*     */   
/*  97 */   private ReferencedEnvelope bounds = null;
/*     */   
/*     */   protected String id;
/*     */   
/*     */   protected SimpleFeatureType schema;
/*     */   
/*     */   public TreeSetFeatureCollection() {
/* 118 */     this((String)null, (SimpleFeatureType)null);
/*     */   }
/*     */   
/*     */   public TreeSetFeatureCollection(FeatureCollection<SimpleFeatureType, SimpleFeature> collection) {
/* 128 */     this(collection.getID(), (SimpleFeatureType)collection.getSchema());
/* 129 */     addAll(collection);
/*     */   }
/*     */   
/*     */   public TreeSetFeatureCollection(String id, SimpleFeatureType memberType) {
/* 147 */     this.id = (id == null) ? "featureCollection" : id;
/* 148 */     this.schema = memberType;
/*     */   }
/*     */   
/*     */   public ReferencedEnvelope getBounds() {
/* 157 */     if (this.bounds == null) {
/* 158 */       this.bounds = new ReferencedEnvelope();
/* 160 */       for (Iterator<SimpleFeature> i = this.contents.values().iterator(); i.hasNext(); ) {
/* 161 */         BoundingBox geomBounds = ((SimpleFeature)i.next()).getBounds();
/* 165 */         if (!geomBounds.isEmpty())
/* 166 */           this.bounds.include(geomBounds); 
/*     */       } 
/*     */     } 
/* 170 */     return this.bounds;
/*     */   }
/*     */   
/*     */   public boolean add(SimpleFeature feature) {
/* 201 */     if (feature == null)
/* 202 */       return false; 
/* 203 */     String ID = feature.getID();
/* 204 */     if (ID == null)
/* 205 */       return false; 
/* 206 */     if (this.contents.containsKey(ID))
/* 207 */       return false; 
/* 209 */     if (this.schema == null)
/* 210 */       this.schema = feature.getFeatureType(); 
/* 212 */     SimpleFeatureType childType = getSchema();
/* 216 */     if (!feature.getFeatureType().equals(childType))
/* 217 */       LOGGER.warning("Feature Collection contains a heterogeneous mix of features"); 
/* 221 */     this.contents.put(ID, feature);
/* 222 */     return true;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   protected boolean add(SimpleFeature feature, boolean fire) {
/* 227 */     return add(feature);
/*     */   }
/*     */   
/*     */   public boolean addAll(Collection<SimpleFeature> collection) {
/* 246 */     boolean changed = false;
/* 248 */     Iterator<SimpleFeature> iterator = collection.iterator();
/*     */     try {
/* 250 */       List<SimpleFeature> featuresAdded = new ArrayList(collection.size());
/* 251 */       while (iterator.hasNext()) {
/* 252 */         SimpleFeature f = iterator.next();
/* 253 */         boolean added = add(f);
/* 254 */         changed |= added;
/* 256 */         if (added)
/* 257 */           featuresAdded.add(f); 
/*     */       } 
/* 259 */       return changed;
/*     */     } finally {
/* 261 */       DataUtilities.close(iterator);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean addAll(FeatureCollection<?, ?> collection) {
/* 268 */     boolean changed = false;
/* 270 */     FeatureIterator<?> iterator = collection.features();
/*     */     try {
/* 272 */       List<SimpleFeature> featuresAdded = new ArrayList<SimpleFeature>(collection.size());
/* 273 */       while (iterator.hasNext()) {
/* 274 */         SimpleFeature f = (SimpleFeature)iterator.next();
/* 275 */         boolean added = add(f);
/* 276 */         changed |= added;
/* 278 */         if (added)
/* 279 */           featuresAdded.add(f); 
/*     */       } 
/* 281 */       return changed;
/*     */     } finally {
/* 283 */       iterator.close();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void clear() {
/* 292 */     if (this.contents.isEmpty())
/*     */       return; 
/* 295 */     SimpleFeature[] oldFeatures = new SimpleFeature[this.contents.size()];
/* 296 */     oldFeatures = (SimpleFeature[])this.contents.values().toArray((Object[])oldFeatures);
/* 298 */     this.contents.clear();
/*     */   }
/*     */   
/*     */   public boolean contains(Object o) {
/* 317 */     if (!(o instanceof SimpleFeature))
/* 318 */       return false; 
/* 320 */     SimpleFeature feature = (SimpleFeature)o;
/* 321 */     String ID = feature.getID();
/* 323 */     return this.contents.containsKey(ID);
/*     */   }
/*     */   
/*     */   public boolean containsAll(Collection collection) {
/* 333 */     Iterator<SimpleFeature> iterator = collection.iterator();
/*     */     try {
/* 335 */       while (iterator.hasNext()) {
/* 336 */         SimpleFeature feature = iterator.next();
/* 337 */         if (!this.contents.containsKey(feature.getID()))
/* 338 */           return false; 
/*     */       } 
/* 341 */       return true;
/*     */     } finally {
/* 343 */       DataUtilities.close(iterator);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 353 */     return this.contents.isEmpty();
/*     */   }
/*     */   
/*     */   public Iterator<SimpleFeature> iterator() {
/* 364 */     final Iterator<SimpleFeature> iterator = this.contents.values().iterator();
/* 366 */     return new Iterator<SimpleFeature>() {
/* 367 */         SimpleFeature currFeature = null;
/*     */         
/*     */         public boolean hasNext() {
/* 370 */           return iterator.hasNext();
/*     */         }
/*     */         
/*     */         public SimpleFeature next() {
/* 374 */           this.currFeature = iterator.next();
/* 375 */           return this.currFeature;
/*     */         }
/*     */         
/*     */         public void remove() {
/* 379 */           iterator.remove();
/* 380 */           TreeSetFeatureCollection.this.bounds = null;
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   public SimpleFeatureIterator features() {
/* 392 */     return (SimpleFeatureIterator)new SimpleFeatureIteratorImpl(this.contents.values());
/*     */   }
/*     */   
/*     */   public boolean remove(Object o) {
/* 409 */     if (!(o instanceof SimpleFeature))
/* 410 */       return false; 
/* 412 */     SimpleFeature f = (SimpleFeature)o;
/* 413 */     boolean changed = this.contents.values().remove(f);
/* 418 */     return changed;
/*     */   }
/*     */   
/*     */   public boolean removeAll(Collection collection) {
/* 436 */     boolean changed = false;
/* 437 */     Iterator<SimpleFeature> iterator = collection.iterator();
/*     */     try {
/* 439 */       List<SimpleFeature> removedFeatures = new ArrayList(collection.size());
/* 440 */       while (iterator.hasNext()) {
/* 441 */         SimpleFeature f = iterator.next();
/* 442 */         boolean removed = this.contents.values().remove(f);
/* 444 */         if (removed) {
/* 445 */           changed = true;
/* 446 */           removedFeatures.add(f);
/*     */         } 
/*     */       } 
/* 449 */       return changed;
/*     */     } finally {
/* 451 */       DataUtilities.close(iterator);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean retainAll(Collection collection) {
/* 470 */     List<SimpleFeature> removedFeatures = new ArrayList(this.contents.size() - collection.size());
/* 471 */     boolean modified = false;
/* 473 */     for (Iterator<SimpleFeature> it = this.contents.values().iterator(); it.hasNext(); ) {
/* 474 */       SimpleFeature f = it.next();
/* 475 */       if (!collection.contains(f)) {
/* 476 */         it.remove();
/* 477 */         modified = true;
/* 478 */         removedFeatures.add(f);
/*     */       } 
/*     */     } 
/* 481 */     return modified;
/*     */   }
/*     */   
/*     */   public int size() {
/* 491 */     return this.contents.size();
/*     */   }
/*     */   
/*     */   public Object[] toArray() {
/* 512 */     return this.contents.values().toArray();
/*     */   }
/*     */   
/*     */   public Object[] toArray(Object[] a) {
/* 563 */     return this.contents.values().toArray((a != null) ? a : new Object[this.contents.size()]);
/*     */   }
/*     */   
/*     */   public void close(FeatureIterator<SimpleFeature> close) {
/* 567 */     if (close instanceof FeatureIteratorImpl) {
/* 568 */       FeatureIteratorImpl<SimpleFeature> wrapper = (FeatureIteratorImpl<SimpleFeature>)close;
/* 569 */       wrapper.close();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void close(Iterator close) {}
/*     */   
/*     */   public FeatureReader<SimpleFeatureType, SimpleFeature> reader() throws IOException {
/* 578 */     final SimpleFeatureIterator iterator = features();
/* 579 */     return new FeatureReader<SimpleFeatureType, SimpleFeature>() {
/*     */         public SimpleFeatureType getFeatureType() {
/* 581 */           return TreeSetFeatureCollection.this.getSchema();
/*     */         }
/*     */         
/*     */         public SimpleFeature next() throws IOException, IllegalAttributeException, NoSuchElementException {
/* 586 */           return (SimpleFeature)iterator.next();
/*     */         }
/*     */         
/*     */         public boolean hasNext() throws IOException {
/* 590 */           return iterator.hasNext();
/*     */         }
/*     */         
/*     */         public void close() throws IOException {
/* 594 */           TreeSetFeatureCollection.this.close((FeatureIterator<SimpleFeature>)iterator);
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   public int getCount() throws IOException {
/* 600 */     return this.contents.size();
/*     */   }
/*     */   
/*     */   public SimpleFeatureCollection collection() throws IOException {
/* 604 */     TreeSetFeatureCollection copy = new TreeSetFeatureCollection(null, getSchema());
/* 605 */     List<SimpleFeature> list = new ArrayList<SimpleFeature>(this.contents.size());
/* 606 */     SimpleFeatureIterator iterator = features();
/*     */     try {
/* 608 */       while (iterator.hasNext()) {
/* 609 */         SimpleFeature duplicate, feature = (SimpleFeature)iterator.next();
/*     */         try {
/* 612 */           duplicate = SimpleFeatureBuilder.copy(feature);
/* 613 */         } catch (IllegalAttributeException e) {
/* 614 */           throw new DataSourceException("Unable to copy " + feature.getID(), e);
/*     */         } 
/* 616 */         list.add(duplicate);
/*     */       } 
/*     */     } finally {
/* 619 */       iterator.close();
/*     */     } 
/* 621 */     copy.addAll(list);
/* 622 */     return copy;
/*     */   }
/*     */   
/*     */   public Set<String> fids() {
/* 632 */     return Collections.unmodifiableSet(this.contents.keySet());
/*     */   }
/*     */   
/*     */   public void accepts(FeatureVisitor visitor, ProgressListener progress) throws IOException {
/* 637 */     DataUtilities.visit((FeatureCollection)this, visitor, progress);
/*     */   }
/*     */   
/*     */   public SimpleFeatureCollection subCollection(Filter filter) {
/* 654 */     if (filter == Filter.INCLUDE)
/* 655 */       return this; 
/* 657 */     CollectionFeatureSource temp = new CollectionFeatureSource(this);
/* 658 */     return temp.getFeatures(filter);
/*     */   }
/*     */   
/*     */   public SimpleFeatureCollection sort(SortBy order) {
/* 662 */     Query subQuery = new Query(getSchema().getTypeName());
/* 663 */     subQuery.setSortBy(new SortBy[] { order });
/* 665 */     CollectionFeatureSource temp = new CollectionFeatureSource(this);
/* 666 */     return temp.getFeatures(subQuery);
/*     */   }
/*     */   
/*     */   public void purge() {}
/*     */   
/*     */   public String getID() {
/* 674 */     return this.id;
/*     */   }
/*     */   
/*     */   public SimpleFeatureType getSchema() {
/* 686 */     return this.schema;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\collection\TreeSetFeatureCollection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */