/*     */ package org.geotools.data.store;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import org.geotools.data.DataUtilities;
/*     */ import org.geotools.data.FeatureReader;
/*     */ import org.geotools.data.FeatureWriter;
/*     */ import org.geotools.data.collection.DelegateFeatureReader;
/*     */ import org.geotools.data.simple.SimpleFeatureCollection;
/*     */ import org.geotools.data.simple.SimpleFeatureIterator;
/*     */ import org.geotools.feature.CollectionEvent;
/*     */ import org.geotools.feature.CollectionListener;
/*     */ import org.geotools.feature.FeatureCollection;
/*     */ import org.geotools.feature.FeatureIterator;
/*     */ import org.geotools.feature.FeatureReaderIterator;
/*     */ import org.geotools.feature.collection.DelegateSimpleFeatureIterator;
/*     */ import org.geotools.feature.collection.SubFeatureCollection;
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
/*     */ import org.opengis.util.ProgressListener;
/*     */ 
/*     */ public abstract class DataFeatureCollection implements SimpleFeatureCollection {
/*  82 */   static Logger LOGGER = Logging.getLogger("org.geotools.data");
/*     */   
/*  84 */   private static int unique = 0;
/*     */   
/*     */   private final Set open;
/*     */   
/*     */   protected List listeners;
/*     */   
/*     */   protected String id;
/*     */   
/*     */   protected SimpleFeatureType schema;
/*     */   
/*     */   protected DataFeatureCollection() {
/*  90 */     this("features" + unique++);
/*     */   }
/*     */   
/*     */   protected DataFeatureCollection(String id) {
/*  96 */     this(id, null);
/*     */   }
/*     */   
/*     */   protected DataFeatureCollection(String id, SimpleFeatureType memberType) {
/* 168 */     this.open = new HashSet();
/* 173 */     this.listeners = new ArrayList();
/*     */     this.id = (id == null) ? "featureCollection" : id;
/*     */     this.schema = memberType;
/*     */   }
/*     */   
/*     */   protected void fireChange(SimpleFeature[] features, int type) {
/*     */     CollectionEvent cEvent = new CollectionEvent((FeatureCollection)this, features, type);
/*     */     for (int i = 0, ii = this.listeners.size(); i < ii; i++)
/*     */       ((CollectionListener)this.listeners.get(i)).collectionChanged(cEvent); 
/*     */   }
/*     */   
/*     */   protected void fireChange(SimpleFeature feature, int type) {
/*     */     fireChange(new SimpleFeature[] { feature }, type);
/*     */   }
/*     */   
/*     */   protected void fireChange(Collection coll, int type) {
/*     */     SimpleFeature[] features = new SimpleFeature[coll.size()];
/*     */     features = (SimpleFeature[])coll.toArray((Object[])features);
/*     */     fireChange(features, type);
/*     */   }
/*     */   
/*     */   public FeatureReader<SimpleFeatureType, SimpleFeature> reader() throws IOException {
/*     */     return (FeatureReader<SimpleFeatureType, SimpleFeature>)new DelegateFeatureReader((FeatureType)getSchema(), (FeatureIterator)features());
/*     */   }
/*     */   
/*     */   protected FeatureWriter<SimpleFeatureType, SimpleFeature> writer() throws IOException {
/*     */     return null;
/*     */   }
/*     */   
/*     */   public SimpleFeatureIterator features() {
/* 188 */     DelegateSimpleFeatureIterator delegateSimpleFeatureIterator = new DelegateSimpleFeatureIterator((FeatureCollection)this, iterator());
/* 189 */     this.open.add(delegateSimpleFeatureIterator);
/* 190 */     return (SimpleFeatureIterator)delegateSimpleFeatureIterator;
/*     */   }
/*     */   
/*     */   public final Iterator<SimpleFeature> iterator() {
/*     */     Iterator<SimpleFeature> iterator;
/*     */     try {
/* 199 */       iterator = openIterator();
/* 201 */     } catch (IOException e) {
/* 202 */       throw new RuntimeException(e);
/*     */     } 
/* 205 */     this.open.add(iterator);
/* 206 */     return iterator;
/*     */   }
/*     */   
/*     */   protected Iterator<SimpleFeature> openIterator() throws IOException {
/*     */     try {
/* 221 */       FeatureWriter<SimpleFeatureType, SimpleFeature> writer = writer();
/* 222 */       if (writer != null)
/* 223 */         return new FeatureWriterIterator(writer()); 
/* 225 */     } catch (IOException badWriter) {
/* 226 */       return new NoContentIterator(badWriter);
/* 227 */     } catch (UnsupportedOperationException readOnly) {}
/*     */     try {
/* 231 */       return (Iterator<SimpleFeature>)new FeatureReaderIterator(reader());
/* 232 */     } catch (IOException e) {
/* 233 */       return new NoContentIterator(e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public final void close(Iterator<SimpleFeature> close) {
/*     */     try {
/* 239 */       closeIterator(close);
/* 241 */     } catch (IOException e) {
/* 242 */       LOGGER.log(Level.WARNING, "Error closing iterator", e);
/*     */     } 
/* 244 */     this.open.remove(close);
/*     */   }
/*     */   
/*     */   protected void closeIterator(Iterator<SimpleFeature> close) throws IOException {
/* 249 */     if (close != null)
/* 252 */       if (close instanceof FeatureReaderIterator) {
/* 253 */         FeatureReaderIterator<SimpleFeature> iterator = (FeatureReaderIterator<SimpleFeature>)close;
/* 254 */         iterator.close();
/* 256 */       } else if (close instanceof FeatureWriterIterator) {
/* 257 */         FeatureWriterIterator iterator = (FeatureWriterIterator)close;
/* 258 */         iterator.close();
/*     */       }  
/*     */   }
/*     */   
/*     */   public void close(FeatureIterator<SimpleFeature> iterator) {
/* 263 */     iterator.close();
/* 264 */     this.open.remove(iterator);
/*     */   }
/*     */   
/*     */   public int size() {
/*     */     try {
/* 272 */       return getCount();
/* 273 */     } catch (IOException e) {
/* 274 */       if (LOGGER.isLoggable(Level.FINE))
/* 275 */         LOGGER.log(Level.FINE, "IOException while calculating size() of FeatureCollection", e); 
/* 276 */       return 0;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void purge() {
/* 280 */     for (Iterator i = this.open.iterator(); i.hasNext(); ) {
/* 281 */       Object iterator = i.next();
/*     */       try {
/* 283 */         if (iterator instanceof Iterator)
/* 284 */           closeIterator((Iterator<SimpleFeature>)iterator); 
/* 286 */         if (iterator instanceof FeatureIterator)
/* 287 */           ((SimpleFeatureIterator)iterator).close(); 
/* 290 */       } catch (Throwable e) {
/*     */       
/*     */       } finally {
/* 294 */         i.remove();
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 308 */     FeatureReader<SimpleFeatureType, SimpleFeature> reader = null;
/*     */     try {
/* 310 */       reader = reader();
/* 316 */     } catch (IOException e) {
/* 317 */       return true;
/*     */     } finally {
/* 320 */       if (reader != null)
/*     */         try {
/* 322 */           reader.close();
/* 323 */         } catch (IOException e) {} 
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean contains(Object o) {
/* 331 */     if (!(o instanceof SimpleFeature))
/* 331 */       return false; 
/* 332 */     SimpleFeature value = (SimpleFeature)o;
/* 333 */     String ID = value.getID();
/* 335 */     FeatureReader<SimpleFeatureType, SimpleFeature> reader = null;
/*     */     try {
/* 337 */       reader = reader();
/* 354 */     } catch (IOException e) {
/* 355 */       return false;
/*     */     } finally {
/* 358 */       if (reader != null)
/*     */         try {
/* 360 */           reader.close();
/* 361 */         } catch (IOException e) {} 
/*     */     } 
/*     */   }
/*     */   
/*     */   public Object[] toArray() {
/* 369 */     return toArray((Object[])new SimpleFeature[size()]);
/*     */   }
/*     */   
/*     */   public <T> T[] toArray(T[] array) {
/* 373 */     List<T> list = new ArrayList<T>();
/* 374 */     Iterator<SimpleFeature> i = iterator();
/*     */     try {
/* 376 */       while (i.hasNext())
/* 377 */         list.add((T)i.next()); 
/*     */     } finally {
/* 381 */       close(i);
/*     */     } 
/* 383 */     return list.toArray(array);
/*     */   }
/*     */   
/*     */   public boolean add(SimpleFeature arg0) {
/* 387 */     return false;
/*     */   }
/*     */   
/*     */   public boolean remove(Object arg0) {
/* 391 */     return false;
/*     */   }
/*     */   
/*     */   public boolean containsAll(Collection<?> collection) {
/* 395 */     for (Object o : collection) {
/* 396 */       if (!contains(o))
/* 396 */         return false; 
/*     */     } 
/* 398 */     return true;
/*     */   }
/*     */   
/*     */   public boolean addAll(Collection collection) {
/* 413 */     if (collection instanceof FeatureCollection)
/* 414 */       return addAll((FeatureCollection)collection); 
/*     */     try {
/* 417 */       FeatureWriter<SimpleFeatureType, SimpleFeature> writer = writer();
/* 418 */       if (writer == null)
/* 419 */         return false; 
/*     */       try {
/* 423 */         while (writer.hasNext())
/* 424 */           Feature feature = writer.next(); 
/* 426 */         for (Object obj : collection) {
/* 427 */           if (obj instanceof SimpleFeature) {
/* 428 */             SimpleFeature copy = (SimpleFeature)obj;
/* 429 */             SimpleFeature feature = (SimpleFeature)writer.next();
/* 431 */             feature.setAttributes(copy.getAttributes());
/* 432 */             writer.write();
/*     */           } 
/*     */         } 
/*     */       } finally {
/* 437 */         if (writer != null)
/* 437 */           writer.close(); 
/*     */       } 
/* 439 */       return true;
/* 441 */     } catch (IOException ignore) {
/* 442 */       return false;
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean addAll(FeatureCollection resource) {
/* 446 */     return false;
/*     */   }
/*     */   
/*     */   public boolean removeAll(Collection arg0) {
/* 449 */     return false;
/*     */   }
/*     */   
/*     */   public boolean retainAll(Collection arg0) {
/* 452 */     return false;
/*     */   }
/*     */   
/*     */   public void clear() {}
/*     */   
/*     */   public void accepts(FeatureVisitor visitor, ProgressListener progress) throws IOException {
/* 458 */     DataUtilities.visit((FeatureCollection)this, visitor, progress);
/*     */   }
/*     */   
/*     */   public SimpleFeatureCollection subCollection(Filter filter) {
/* 481 */     if (filter == Filter.INCLUDE)
/* 482 */       return this; 
/* 484 */     return (SimpleFeatureCollection)new SubFeatureCollection(this, filter);
/*     */   }
/*     */   
/*     */   public SimpleFeatureCollection sort(SortBy order) {
/* 503 */     if (order instanceof SortBy2) {
/* 504 */       SortBy2 advanced = (SortBy2)order;
/* 505 */       return sort(advanced);
/*     */     } 
/* 507 */     return null;
/*     */   }
/*     */   
/*     */   public SimpleFeatureCollection sort(SortBy2 order) {
/* 520 */     return null;
/*     */   }
/*     */   
/*     */   public String getID() {
/* 523 */     return this.id;
/*     */   }
/*     */   
/*     */   public final void addListener(CollectionListener listener) throws NullPointerException {
/* 526 */     this.listeners.add(listener);
/*     */   }
/*     */   
/*     */   public final void removeListener(CollectionListener listener) throws NullPointerException {
/* 530 */     this.listeners.remove(listener);
/*     */   }
/*     */   
/*     */   public SimpleFeatureType getSchema() {
/* 533 */     return this.schema;
/*     */   }
/*     */   
/*     */   public abstract ReferencedEnvelope getBounds();
/*     */   
/*     */   public abstract int getCount() throws IOException;
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\store\DataFeatureCollection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */