/*     */ package org.geotools.data.store;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Envelope;
/*     */ import com.vividsolutions.jts.geom.Point;
/*     */ import java.io.IOException;
/*     */ import java.lang.reflect.Array;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.Date;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.NoSuchElementException;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import org.geotools.data.DataUtilities;
/*     */ import org.geotools.data.FeatureReader;
/*     */ import org.geotools.data.Join;
/*     */ import org.geotools.data.Query;
/*     */ import org.geotools.data.simple.SimpleFeatureCollection;
/*     */ import org.geotools.data.simple.SimpleFeatureIterator;
/*     */ import org.geotools.feature.FeatureCollection;
/*     */ import org.geotools.feature.FeatureIterator;
/*     */ import org.geotools.feature.FeatureTypes;
/*     */ import org.geotools.feature.SchemaException;
/*     */ import org.geotools.feature.simple.SimpleFeatureTypeBuilder;
/*     */ import org.geotools.filter.SortBy;
/*     */ import org.geotools.geometry.jts.ReferencedEnvelope;
/*     */ import org.geotools.util.logging.Logging;
/*     */ import org.opengis.feature.Feature;
/*     */ import org.opengis.feature.FeatureVisitor;
/*     */ import org.opengis.feature.simple.SimpleFeature;
/*     */ import org.opengis.feature.simple.SimpleFeatureType;
/*     */ import org.opengis.feature.type.AttributeDescriptor;
/*     */ import org.opengis.feature.type.FeatureType;
/*     */ import org.opengis.feature.type.PropertyDescriptor;
/*     */ import org.opengis.filter.Filter;
/*     */ import org.opengis.filter.sort.SortBy;
/*     */ import org.opengis.util.ProgressListener;
/*     */ 
/*     */ public class ContentFeatureCollection implements SimpleFeatureCollection {
/*  61 */   protected static final Logger LOGGER = Logging.getLogger("org.geotools.data.store");
/*     */   
/*     */   protected ContentFeatureSource featureSource;
/*     */   
/*     */   protected Query query;
/*     */   
/*     */   protected SimpleFeatureType featureType;
/*     */   
/*     */   protected ContentState state;
/*     */   
/*     */   protected ContentFeatureCollection(ContentFeatureSource featureSource, Query query) {
/*  78 */     this.featureSource = featureSource;
/*  79 */     this.query = query;
/*  81 */     this.featureType = featureSource.getSchema();
/*  84 */     if (query.getPropertyNames() != Query.ALL_NAMES)
/*  85 */       this.featureType = SimpleFeatureTypeBuilder.retype(this.featureType, query.getPropertyNames()); 
/*     */     try {
/*  92 */       if (query.getCoordinateSystemReproject() != null) {
/*  93 */         this.featureType = FeatureTypes.transform(this.featureType, query.getCoordinateSystemReproject());
/*  95 */       } else if (query.getCoordinateSystem() != null) {
/*  96 */         this.featureType = FeatureTypes.transform(this.featureType, query.getCoordinateSystem());
/*     */       } 
/*  98 */     } catch (SchemaException e) {
/*  99 */       LOGGER.log(Level.FINER, "Problem handling Query change of CoordinateReferenceSystem:" + e, (Throwable)e);
/*     */     } 
/* 103 */     if (!query.getJoins().isEmpty()) {
/* 104 */       SimpleFeatureTypeBuilder tb = new SimpleFeatureTypeBuilder();
/* 105 */       tb.init(this.featureType);
/* 107 */       for (Join join : query.getJoins())
/* 108 */         tb.add(join.attributeName(), SimpleFeature.class); 
/* 110 */       this.featureType = tb.buildFeatureType();
/*     */     } 
/*     */   }
/*     */   
/*     */   public SimpleFeatureType getSchema() {
/* 115 */     return this.featureType;
/*     */   }
/*     */   
/*     */   public void accepts(FeatureVisitor visitor, ProgressListener progress) throws IOException {
/* 121 */     this.featureSource.accepts(this.query, visitor, progress);
/*     */   }
/*     */   
/*     */   public static class WrappingFeatureIterator implements SimpleFeatureIterator {
/*     */     FeatureReader<SimpleFeatureType, SimpleFeature> delegate;
/*     */     
/*     */     public WrappingFeatureIterator(FeatureReader<SimpleFeatureType, SimpleFeature> delegate) {
/* 130 */       this.delegate = delegate;
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/*     */       try {
/* 135 */         return this.delegate.hasNext();
/* 137 */       } catch (IOException e) {
/* 138 */         throw new RuntimeException(e);
/*     */       } 
/*     */     }
/*     */     
/*     */     public SimpleFeature next() throws NoSuchElementException {
/*     */       try {
/* 145 */         return (SimpleFeature)this.delegate.next();
/* 147 */       } catch (IOException e) {
/* 148 */         throw new RuntimeException(e);
/*     */       } 
/*     */     }
/*     */     
/*     */     public void close() {
/*     */       try {
/* 154 */         this.delegate.close();
/* 156 */       } catch (IOException e) {
/* 157 */         throw new RuntimeException(e);
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public SimpleFeatureIterator features() {
/*     */     try {
/* 165 */       return new WrappingFeatureIterator(this.featureSource.getReader(this.query));
/* 167 */     } catch (IOException e) {
/* 168 */       throw new RuntimeException(e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public ReferencedEnvelope getBounds() {
/* 173 */     FeatureReader<SimpleFeatureType, SimpleFeature> reader = null;
/*     */     try {
/* 175 */       ReferencedEnvelope result = this.featureSource.getBounds(this.query);
/* 176 */       if (result != null)
/* 177 */         return result; 
/* 182 */       Query q = new Query(this.query);
/* 183 */       List<String> geometries = new ArrayList<String>();
/* 184 */       for (AttributeDescriptor ad : getSchema().getAttributeDescriptors()) {
/* 185 */         if (ad instanceof org.opengis.feature.type.GeometryDescriptor)
/* 186 */           geometries.add(ad.getLocalName()); 
/*     */       } 
/* 190 */       if (geometries.size() == 0)
/* 191 */         return new ReferencedEnvelope(); 
/* 193 */       q.setPropertyNames(geometries);
/* 196 */       reader = this.featureSource.getReader(q);
/* 197 */       while (reader.hasNext()) {
/* 198 */         SimpleFeature f = (SimpleFeature)reader.next();
/* 199 */         ReferencedEnvelope featureBounds = ReferencedEnvelope.reference(f.getBounds());
/* 200 */         if (result == null) {
/* 201 */           result = featureBounds;
/*     */           continue;
/*     */         } 
/* 202 */         if (featureBounds != null)
/* 203 */           result.expandToInclude((Envelope)featureBounds); 
/*     */       } 
/* 207 */       if (result != null)
/* 208 */         return result; 
/* 210 */       return new ReferencedEnvelope(getSchema().getCoordinateReferenceSystem());
/* 212 */     } catch (IOException e) {
/* 213 */       throw new RuntimeException(e);
/*     */     } finally {
/* 215 */       if (reader != null)
/*     */         try {
/* 217 */           reader.close();
/* 218 */         } catch (IOException ex) {} 
/*     */     } 
/*     */   }
/*     */   
/*     */   public int size() {
/* 226 */     FeatureReader<?, ?> fr = null;
/*     */     try {
/* 228 */       int size = this.featureSource.getCount(this.query);
/* 229 */       if (size >= 0)
/* 230 */         return size; 
/* 234 */       AttributeDescriptor chosen = getSmallAttributeInSchema();
/* 237 */       Query q = new Query(this.query);
/* 238 */       if (chosen != null)
/* 239 */         q.setPropertyNames(Collections.singletonList(chosen.getLocalName())); 
/* 242 */       fr = this.featureSource.getReader(q);
/* 243 */       int count = 0;
/* 244 */       while (fr.hasNext()) {
/* 245 */         fr.next();
/* 246 */         count++;
/*     */       } 
/* 248 */       return count;
/* 250 */     } catch (IOException e) {
/* 251 */       throw new RuntimeException(e);
/*     */     } finally {
/* 253 */       if (fr != null)
/*     */         try {
/* 255 */           fr.close();
/* 256 */         } catch (IOException e) {
/* 257 */           throw new RuntimeException(e);
/*     */         }  
/*     */     } 
/*     */   }
/*     */   
/*     */   private AttributeDescriptor getSmallAttributeInSchema() {
/* 264 */     AttributeDescriptor chosen = null;
/* 265 */     for (AttributeDescriptor ad : getSchema().getAttributeDescriptors()) {
/* 266 */       if (chosen == null || size(ad) < size(chosen))
/* 267 */         chosen = ad; 
/*     */     } 
/* 270 */     return chosen;
/*     */   }
/*     */   
/*     */   int size(AttributeDescriptor ad) {
/* 284 */     Class<?> binding = ad.getType().getBinding();
/* 285 */     if (binding.isPrimitive() || Number.class.isAssignableFrom(binding) || Date.class.isAssignableFrom(binding))
/* 287 */       return 4; 
/* 288 */     if (binding.equals(String.class)) {
/* 289 */       int fieldLen = FeatureTypes.getFieldLength((PropertyDescriptor)ad);
/* 290 */       if (fieldLen > 0)
/* 291 */         return fieldLen * 2; 
/* 293 */       return Integer.MAX_VALUE;
/*     */     } 
/* 295 */     if (Point.class.isAssignableFrom(binding))
/* 296 */       return 12; 
/* 298 */     return Integer.MAX_VALUE;
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 304 */     Query notEmptyQuery = new Query(this.query);
/* 305 */     notEmptyQuery.setMaxFeatures(1);
/* 307 */     AttributeDescriptor smallAttribute = getSmallAttributeInSchema();
/* 308 */     if (smallAttribute != null)
/* 309 */       notEmptyQuery.setPropertyNames(Collections.singletonList(smallAttribute.getLocalName())); 
/*     */     try {
/* 313 */       FeatureReader<?, ?> fr = this.featureSource.getReader(notEmptyQuery);
/*     */       try {
/* 315 */         return !fr.hasNext();
/*     */       } finally {
/* 317 */         fr.close();
/*     */       } 
/* 319 */     } catch (IOException e) {
/* 320 */       throw new RuntimeException(e);
/*     */     } 
/*     */   }
/*     */   
/*     */   ContentFeatureStore ensureFeatureStore() {
/* 325 */     if (this.featureSource instanceof ContentFeatureStore)
/* 326 */       return (ContentFeatureStore)this.featureSource; 
/* 328 */     throw new UnsupportedOperationException("read only");
/*     */   }
/*     */   
/*     */   public SimpleFeatureCollection sort(SortBy order) {
/* 332 */     return sort((SortBy)order);
/*     */   }
/*     */   
/*     */   public SimpleFeatureCollection sort(SortBy sort) {
/* 336 */     Query query = new Query();
/* 337 */     query.setSortBy(new SortBy[] { sort });
/* 339 */     query = DataUtilities.mixQueries(this.query, query, null);
/* 340 */     return new ContentFeatureCollection(this.featureSource, query);
/*     */   }
/*     */   
/*     */   public SimpleFeatureCollection subCollection(Filter filter) {
/* 344 */     Query query = new Query();
/* 345 */     query.setFilter(filter);
/* 347 */     query = DataUtilities.mixQueries(this.query, query, null);
/* 348 */     return new ContentFeatureCollection(this.featureSource, query);
/*     */   }
/*     */   
/*     */   public boolean contains(Object o) {
/* 370 */     SimpleFeatureIterator e = null;
/*     */     try {
/* 372 */       e = features();
/* 373 */       if (o == null) {
/* 374 */         while (e.hasNext()) {
/* 375 */           if (e.next() == null)
/* 376 */             return true; 
/*     */         } 
/*     */       } else {
/* 380 */         while (e.hasNext()) {
/* 381 */           if (o.equals(e.next()))
/* 382 */             return true; 
/*     */         } 
/*     */       } 
/* 386 */       return false;
/*     */     } finally {
/* 389 */       if (e != null)
/* 390 */         e.close(); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean containsAll(Collection<?> c) {
/* 406 */     Iterator<?> e = c.iterator();
/*     */     try {
/* 408 */       while (e.hasNext()) {
/* 409 */         if (!contains(e.next()))
/* 410 */           return false; 
/*     */       } 
/* 413 */       return true;
/*     */     } finally {
/* 415 */       if (e instanceof FeatureIterator)
/* 416 */         ((FeatureIterator)e).close(); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean remove(Object o) {
/* 442 */     throw new UnsupportedOperationException("Content is not writable; FeatureStore not available");
/*     */   }
/*     */   
/*     */   public Object[] toArray() {
/*     */     SimpleFeatureIterator simpleFeatureIterator;
/* 453 */     ArrayList<SimpleFeature> array = new ArrayList<SimpleFeature>();
/* 454 */     FeatureIterator<SimpleFeature> e = null;
/*     */     try {
/* 456 */       simpleFeatureIterator = features();
/* 457 */       while (simpleFeatureIterator.hasNext())
/* 458 */         array.add(simpleFeatureIterator.next()); 
/* 460 */       return array.toArray((Object[])new SimpleFeature[array.size()]);
/*     */     } finally {
/* 462 */       if (simpleFeatureIterator != null)
/* 463 */         simpleFeatureIterator.close(); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public <T> T[] toArray(T[] array) {
/* 470 */     int size = size();
/* 471 */     if (array.length < size)
/* 472 */       array = (T[])Array.newInstance(array.getClass().getComponentType(), size); 
/* 474 */     SimpleFeatureIterator simpleFeatureIterator = features();
/*     */     try {
/* 476 */       T[] arrayOfT = array;
/* 477 */       for (int i = 0; simpleFeatureIterator.hasNext() && i < size; i++)
/* 478 */         arrayOfT[i] = (T)simpleFeatureIterator.next(); 
/* 480 */       if (array.length > size)
/* 481 */         array[size] = null; 
/* 483 */       return array;
/*     */     } finally {
/* 486 */       if (simpleFeatureIterator != null)
/* 487 */         simpleFeatureIterator.close(); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public String getID() {
/* 492 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\store\ContentFeatureCollection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */