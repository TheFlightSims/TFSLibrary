/*     */ package org.geotools.data.collection;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Envelope;
/*     */ import com.vividsolutions.jts.index.ItemVisitor;
/*     */ import com.vividsolutions.jts.index.strtree.STRtree;
/*     */ import java.io.IOException;
/*     */ import java.lang.reflect.Array;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.NoSuchElementException;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import org.geotools.data.simple.SimpleFeatureCollection;
/*     */ import org.geotools.data.simple.SimpleFeatureIterator;
/*     */ import org.geotools.feature.CollectionEvent;
/*     */ import org.geotools.feature.CollectionListener;
/*     */ import org.geotools.feature.FeatureCollection;
/*     */ import org.geotools.feature.FeatureIterator;
/*     */ import org.geotools.geometry.jts.ReferencedEnvelope;
/*     */ import org.geotools.util.NullProgressListener;
/*     */ import org.geotools.util.logging.Logging;
/*     */ import org.opengis.feature.Feature;
/*     */ import org.opengis.feature.FeatureVisitor;
/*     */ import org.opengis.feature.simple.SimpleFeature;
/*     */ import org.opengis.feature.simple.SimpleFeatureType;
/*     */ import org.opengis.feature.type.FeatureType;
/*     */ import org.opengis.filter.Filter;
/*     */ import org.opengis.filter.sort.SortBy;
/*     */ import org.opengis.referencing.crs.CoordinateReferenceSystem;
/*     */ import org.opengis.util.ProgressListener;
/*     */ 
/*     */ public class SpatialIndexFeatureCollection implements SimpleFeatureCollection {
/*  47 */   static Logger LOGGER = Logging.getLogger(SpatialIndexFeatureCollection.class);
/*     */   
/*     */   protected STRtree index;
/*     */   
/*     */   protected SimpleFeatureType schema;
/*     */   
/*  55 */   protected List<CollectionListener> listeners = null;
/*     */   
/*     */   public SpatialIndexFeatureCollection() {
/*  58 */     this.index = new STRtree();
/*     */   }
/*     */   
/*     */   public SpatialIndexFeatureCollection(SimpleFeatureType schema) {
/*  62 */     this.index = new STRtree();
/*  63 */     this.schema = schema;
/*     */   }
/*     */   
/*     */   public SpatialIndexFeatureCollection(SimpleFeatureCollection copy) throws IOException {
/*  67 */     this((SimpleFeatureType)copy.getSchema());
/*  68 */     addAll((FeatureCollection<? extends SimpleFeatureType, ? extends SimpleFeature>)copy);
/*     */   }
/*     */   
/*     */   public synchronized void addListener(CollectionListener listener) throws NullPointerException {
/*  72 */     if (this.listeners == null)
/*  73 */       this.listeners = Collections.synchronizedList(new ArrayList<CollectionListener>()); 
/*  75 */     this.listeners.add(listener);
/*     */   }
/*     */   
/*     */   public synchronized void removeListener(CollectionListener listener) throws NullPointerException {
/*  80 */     if (this.listeners == null)
/*     */       return; 
/*  83 */     this.listeners.remove(listener);
/*     */   }
/*     */   
/*     */   protected void fire(SimpleFeature[] features, int eventType) {
/*  87 */     if (this.listeners == null || this.listeners.isEmpty())
/*     */       return; 
/*  90 */     CollectionEvent event = new CollectionEvent((FeatureCollection)this, features, eventType);
/*  91 */     CollectionListener[] notify = this.listeners.<CollectionListener>toArray(new CollectionListener[this.listeners.size()]);
/*  93 */     for (CollectionListener listener : notify) {
/*     */       try {
/*  95 */         listener.collectionChanged(event);
/*  96 */       } catch (Throwable t) {
/*  97 */         LOGGER.log(Level.WARNING, "Problem encountered during notification of " + event, t);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public SimpleFeatureIterator features() {
/* 104 */     Envelope everything = new Envelope(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
/* 107 */     List<SimpleFeature> list = this.index.query(everything);
/* 108 */     final Iterator<SimpleFeature> iterator = list.iterator();
/* 109 */     return new SimpleFeatureIterator() {
/*     */         public SimpleFeature next() throws NoSuchElementException {
/* 111 */           return iterator.next();
/*     */         }
/*     */         
/*     */         public boolean hasNext() {
/* 115 */           return iterator.hasNext();
/*     */         }
/*     */         
/*     */         public void close() {}
/*     */       };
/*     */   }
/*     */   
/*     */   public SimpleFeatureCollection sort(SortBy order) {
/* 124 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public SimpleFeatureCollection subCollection(Filter filter) {
/* 128 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void accepts(final FeatureVisitor visitor, ProgressListener listener) throws IOException {
/* 132 */     Envelope everything = new Envelope(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
/* 134 */     final ProgressListener progress = (listener != null) ? listener : (ProgressListener)new NullProgressListener();
/* 135 */     progress.started();
/* 136 */     final float size = size();
/* 137 */     final IOException[] problem = new IOException[1];
/* 138 */     this.index.query(everything, new ItemVisitor() {
/* 139 */           float count = 0.0F;
/*     */           
/*     */           public void visitItem(Object item) {
/* 142 */             SimpleFeature feature = null;
/*     */             try {
/* 144 */               feature = (SimpleFeature)item;
/* 145 */               visitor.visit((Feature)feature);
/* 146 */             } catch (Throwable t) {
/* 147 */               progress.exceptionOccurred(t);
/* 148 */               String fid = (feature == null) ? "feature" : feature.getIdentifier().toString();
/* 149 */               problem[0] = new IOException("Problem visiting " + fid + ":" + t, t);
/*     */             } finally {
/* 151 */               progress.progress(this.count / size);
/*     */             } 
/*     */           }
/*     */         });
/* 155 */     if (problem[0] != null)
/* 156 */       throw problem[0]; 
/* 158 */     progress.complete();
/*     */   }
/*     */   
/*     */   public boolean add(SimpleFeature feature) {
/* 162 */     ReferencedEnvelope bounds = ReferencedEnvelope.reference(feature.getBounds());
/* 163 */     this.index.insert((Envelope)bounds, feature);
/* 165 */     return false;
/*     */   }
/*     */   
/*     */   public boolean addAll(Collection<? extends SimpleFeature> collection) {
/* 169 */     for (SimpleFeature feature : collection) {
/*     */       try {
/* 171 */         ReferencedEnvelope bounds = ReferencedEnvelope.reference(feature.getBounds());
/* 172 */         this.index.insert((Envelope)bounds, feature);
/* 173 */       } catch (Throwable t) {}
/*     */     } 
/* 176 */     return false;
/*     */   }
/*     */   
/*     */   public boolean addAll(FeatureCollection<? extends SimpleFeatureType, ? extends SimpleFeature> collection) {
/* 181 */     FeatureIterator<? extends SimpleFeature> iter = collection.features();
/*     */     try {
/* 183 */       while (iter.hasNext()) {
/*     */         try {
/* 185 */           SimpleFeature feature = (SimpleFeature)iter.next();
/* 186 */           ReferencedEnvelope bounds = ReferencedEnvelope.reference(feature.getBounds());
/* 187 */           this.index.insert((Envelope)bounds, feature);
/* 188 */         } catch (Throwable t) {}
/*     */       } 
/*     */     } finally {
/* 192 */       iter.close();
/*     */     } 
/* 194 */     return false;
/*     */   }
/*     */   
/*     */   public synchronized void clear() {
/* 198 */     this.index = null;
/* 199 */     this.index = new STRtree();
/* 200 */     this.listeners.clear();
/* 201 */     this.listeners = null;
/*     */   }
/*     */   
/*     */   public void close(FeatureIterator<SimpleFeature> close) {}
/*     */   
/*     */   public void close(Iterator<SimpleFeature> close) {}
/*     */   
/*     */   public boolean contains(Object obj) {
/* 212 */     if (obj instanceof SimpleFeature) {
/* 213 */       SimpleFeature feature = (SimpleFeature)obj;
/* 214 */       ReferencedEnvelope bounds = ReferencedEnvelope.reference(feature.getBounds());
/* 215 */       Iterator<SimpleFeature> iter = (Iterator<SimpleFeature>)this.index.query((Envelope)bounds);
/* 216 */       while (iter.hasNext()) {
/* 217 */         SimpleFeature sample = iter.next();
/* 218 */         if (sample == feature)
/* 219 */           return true; 
/*     */       } 
/*     */     } 
/* 224 */     return false;
/*     */   }
/*     */   
/*     */   public boolean containsAll(Collection<?> collection) {
/* 228 */     boolean containsAll = true;
/* 229 */     for (Object obj : collection) {
/* 230 */       boolean contains = contains(obj);
/* 231 */       if (!contains) {
/* 232 */         containsAll = false;
/*     */         break;
/*     */       } 
/*     */     } 
/* 236 */     return containsAll;
/*     */   }
/*     */   
/*     */   public ReferencedEnvelope getBounds() {
/* 240 */     CoordinateReferenceSystem crs = this.schema.getCoordinateReferenceSystem();
/* 241 */     Envelope bounds = (Envelope)this.index.getRoot().getBounds();
/* 242 */     return new ReferencedEnvelope(bounds, crs);
/*     */   }
/*     */   
/*     */   public String getID() {
/* 246 */     return null;
/*     */   }
/*     */   
/*     */   public SimpleFeatureType getSchema() {
/* 250 */     return this.schema;
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 254 */     return this.index.itemsTree().isEmpty();
/*     */   }
/*     */   
/*     */   public Iterator<SimpleFeature> iterator() {
/* 259 */     Envelope everything = new Envelope(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
/* 261 */     List<SimpleFeature> list = this.index.query(everything);
/* 262 */     return list.iterator();
/*     */   }
/*     */   
/*     */   public void purge() {}
/*     */   
/*     */   public boolean remove(Object o) {
/* 269 */     throw new UnsupportedOperationException("Cannot remove items from STRtree");
/*     */   }
/*     */   
/*     */   public boolean removeAll(Collection<?> c) {
/* 273 */     throw new UnsupportedOperationException("Cannot remove items from STRtree");
/*     */   }
/*     */   
/*     */   public boolean retainAll(Collection<?> c) {
/* 278 */     throw new UnsupportedOperationException("Cannot remove items from STRtree");
/*     */   }
/*     */   
/*     */   public int size() {
/* 285 */     return this.index.size();
/*     */   }
/*     */   
/*     */   public Object[] toArray() {
/* 289 */     return toArray(new Object[size()]);
/*     */   }
/*     */   
/*     */   public <O> O[] toArray(O[] array) {
/* 294 */     int size = size();
/* 295 */     if (array.length < size)
/* 296 */       array = (O[])Array.newInstance(array.getClass().getComponentType(), size); 
/* 299 */     Iterator<SimpleFeature> it = iterator();
/*     */     try {
/* 301 */       O[] arrayOfO = array;
/* 302 */       for (int i = 0; i < size; i++)
/* 303 */         arrayOfO[i] = (O)it.next(); 
/* 305 */       if (array.length > size)
/* 306 */         array[size] = null; 
/* 308 */       return array;
/*     */     } finally {
/* 310 */       close(it);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\collection\SpatialIndexFeatureCollection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */