/*     */ package org.geotools.data.memory;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Envelope;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import java.io.IOException;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.Map;
/*     */ import java.util.NoSuchElementException;
/*     */ import java.util.logging.Level;
/*     */ import org.geotools.data.AbstractDataStore;
/*     */ import org.geotools.data.DataSourceException;
/*     */ import org.geotools.data.FeatureReader;
/*     */ import org.geotools.data.FeatureWriter;
/*     */ import org.geotools.data.Query;
/*     */ import org.geotools.data.SchemaNotFoundException;
/*     */ import org.geotools.data.Transaction;
/*     */ import org.geotools.data.simple.SimpleFeatureCollection;
/*     */ import org.geotools.data.simple.SimpleFeatureIterator;
/*     */ import org.geotools.feature.FeatureCollection;
/*     */ import org.geotools.feature.simple.SimpleFeatureBuilder;
/*     */ import org.geotools.geometry.jts.ReferencedEnvelope;
/*     */ import org.opengis.feature.Feature;
/*     */ import org.opengis.feature.FeatureVisitor;
/*     */ import org.opengis.feature.IllegalAttributeException;
/*     */ import org.opengis.feature.simple.SimpleFeature;
/*     */ import org.opengis.feature.simple.SimpleFeatureType;
/*     */ import org.opengis.feature.type.FeatureType;
/*     */ import org.opengis.filter.Filter;
/*     */ import org.opengis.referencing.crs.CoordinateReferenceSystem;
/*     */ 
/*     */ public class MemoryDataStore extends AbstractDataStore {
/*  76 */   protected Map<String, Map<String, SimpleFeature>> memory = new LinkedHashMap<String, Map<String, SimpleFeature>>();
/*     */   
/*  79 */   protected Map<String, SimpleFeatureType> schema = new HashMap<String, SimpleFeatureType>();
/*     */   
/*     */   public MemoryDataStore() {
/*  82 */     super(true);
/*     */   }
/*     */   
/*     */   public MemoryDataStore(SimpleFeatureType featureType) {
/*  90 */     Map<String, SimpleFeature> featureMap = new LinkedHashMap<String, SimpleFeature>();
/*  91 */     String typeName = featureType.getTypeName();
/*  92 */     this.schema.put(typeName, featureType);
/*  93 */     this.memory.put(typeName, featureMap);
/*     */   }
/*     */   
/*     */   public MemoryDataStore(FeatureCollection<SimpleFeatureType, SimpleFeature> collection) {
/*  96 */     addFeatures(collection);
/*     */   }
/*     */   
/*     */   public MemoryDataStore(SimpleFeatureCollection collection) {
/*  99 */     addFeatures((FeatureCollection<SimpleFeatureType, SimpleFeature>)collection);
/*     */   }
/*     */   
/*     */   public MemoryDataStore(SimpleFeature[] array) {
/* 103 */     addFeatures(array);
/*     */   }
/*     */   
/*     */   public MemoryDataStore(FeatureReader<SimpleFeatureType, SimpleFeature> reader) throws IOException {
/* 107 */     addFeatures(reader);
/*     */   }
/*     */   
/*     */   public MemoryDataStore(SimpleFeatureIterator reader) throws IOException {
/* 110 */     addFeatures(reader);
/*     */   }
/*     */   
/*     */   public void addFeatures(FeatureReader<SimpleFeatureType, SimpleFeature> reader) throws IOException {
/*     */     try {
/* 127 */       Map<String, SimpleFeature> featureMap = new LinkedHashMap<String, SimpleFeature>();
/* 131 */       SimpleFeature feature = (SimpleFeature)reader.next();
/* 133 */       if (feature == null)
/* 134 */         throw new IllegalArgumentException("Provided  FeatureReader<SimpleFeatureType, SimpleFeature> is closed"); 
/* 137 */       SimpleFeatureType featureType = feature.getFeatureType();
/* 138 */       String typeName = featureType.getTypeName();
/* 140 */       featureMap.put(feature.getID(), feature);
/* 142 */       while (reader.hasNext()) {
/* 143 */         feature = (SimpleFeature)reader.next();
/* 144 */         featureMap.put(feature.getID(), feature);
/*     */       } 
/* 147 */       this.schema.put(typeName, featureType);
/* 148 */       this.memory.put(typeName, featureMap);
/* 149 */     } catch (IllegalAttributeException e) {
/* 150 */       throw new DataSourceException("Problem using reader", e);
/*     */     } finally {
/* 153 */       reader.close();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void addFeatures(SimpleFeatureIterator reader) throws IOException {
/*     */     try {
/* 168 */       Map<String, SimpleFeature> featureMap = new LinkedHashMap<String, SimpleFeature>();
/* 172 */       SimpleFeature feature = (SimpleFeature)reader.next();
/* 174 */       if (feature == null)
/* 175 */         throw new IllegalArgumentException("Provided  FeatureReader<SimpleFeatureType, SimpleFeature> is closed"); 
/* 178 */       SimpleFeatureType featureType = feature.getFeatureType();
/* 179 */       String typeName = featureType.getTypeName();
/* 181 */       featureMap.put(feature.getID(), feature);
/* 183 */       while (reader.hasNext()) {
/* 184 */         feature = (SimpleFeature)reader.next();
/* 185 */         featureMap.put(feature.getID(), feature);
/*     */       } 
/* 188 */       this.schema.put(typeName, featureType);
/* 189 */       this.memory.put(typeName, featureMap);
/*     */     } finally {
/* 192 */       reader.close();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void addFeatures(Collection<?> collection) {
/* 207 */     if (collection == null || collection.isEmpty())
/* 208 */       throw new IllegalArgumentException("Provided SimpleFeatureCollection is empty"); 
/* 211 */     synchronized (this.memory) {
/* 212 */       for (Iterator<?> i = collection.iterator(); i.hasNext();)
/* 213 */         addFeatureInternal((SimpleFeature)i.next()); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void addFeatures(FeatureCollection<SimpleFeatureType, SimpleFeature> collection) {
/* 218 */     if (collection == null)
/* 219 */       throw new IllegalArgumentException("Provided SimpleFeatureCollection is empty"); 
/* 221 */     synchronized (this.memory) {
/*     */       try {
/* 223 */         collection.accepts(new FeatureVisitor() {
/*     */               public void visit(Feature feature) {
/* 225 */                 MemoryDataStore.this.addFeatureInternal((SimpleFeature)feature);
/*     */               }
/*     */             },  null);
/* 229 */       } catch (IOException ignore) {
/* 230 */         LOGGER.log(Level.FINE, "Unable to add all features", ignore);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void addFeatures(SimpleFeature[] features) {
/* 242 */     if (features == null || features.length == 0)
/* 243 */       throw new IllegalArgumentException("Provided features are empty"); 
/* 246 */     synchronized (this.memory) {
/* 247 */       for (int i = 0; i < features.length; i++)
/* 248 */         addFeatureInternal(features[i]); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void addFeature(SimpleFeature feature) {
/* 268 */     synchronized (this.memory) {
/* 269 */       addFeatureInternal(feature);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void addFeatureInternal(SimpleFeature feature) {
/* 274 */     if (feature == null)
/* 275 */       throw new IllegalArgumentException("Provided Feature is empty"); 
/* 279 */     SimpleFeatureType featureType = feature.getFeatureType();
/* 281 */     String typeName = featureType.getTypeName();
/* 285 */     if (!this.memory.containsKey(typeName))
/*     */       try {
/* 287 */         createSchema(featureType);
/* 288 */       } catch (IOException e) {} 
/* 295 */     Map<String, SimpleFeature> featuresMap = this.memory.get(typeName);
/* 296 */     featuresMap.put(feature.getID(), feature);
/*     */   }
/*     */   
/*     */   protected Map<String, SimpleFeature> features(String typeName) throws IOException {
/* 309 */     synchronized (this.memory) {
/* 310 */       if (this.memory.containsKey(typeName))
/* 311 */         return this.memory.get(typeName); 
/*     */     } 
/* 315 */     throw new IOException("Type name " + typeName + " not found");
/*     */   }
/*     */   
/*     */   public String[] getTypeNames() {
/* 326 */     synchronized (this.memory) {
/* 327 */       String[] types = new String[this.schema.size()];
/* 328 */       int index = 0;
/* 330 */       for (Iterator<String> i = this.schema.keySet().iterator(); i.hasNext(); index++)
/* 331 */         types[index] = i.next(); 
/* 334 */       return types;
/*     */     } 
/*     */   }
/*     */   
/*     */   public SimpleFeatureType getSchema(String typeName) throws IOException {
/* 351 */     synchronized (this.memory) {
/* 352 */       if (this.schema.containsKey(typeName))
/* 353 */         return this.schema.get(typeName); 
/* 355 */       throw new SchemaNotFoundException(typeName);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void createSchema(SimpleFeatureType featureType) throws IOException {
/* 374 */     String typeName = featureType.getTypeName();
/* 376 */     if (this.memory.containsKey(typeName))
/* 378 */       throw new IOException(typeName + " already exists"); 
/* 381 */     Map<String, SimpleFeature> featuresMap = new LinkedHashMap<String, SimpleFeature>();
/* 382 */     this.schema.put(typeName, featureType);
/* 383 */     this.memory.put(typeName, featuresMap);
/*     */   }
/*     */   
/*     */   public FeatureReader<SimpleFeatureType, SimpleFeature> getFeatureReader(final String typeName) throws IOException {
/* 403 */     return new FeatureReader<SimpleFeatureType, SimpleFeature>() {
/* 404 */         SimpleFeatureType featureType = MemoryDataStore.this.getSchema(typeName);
/*     */         
/* 405 */         Iterator<SimpleFeature> iterator = MemoryDataStore.this.features(typeName).values().iterator();
/*     */         
/*     */         public SimpleFeatureType getFeatureType() {
/* 408 */           return this.featureType;
/*     */         }
/*     */         
/*     */         public SimpleFeature next() throws IOException, IllegalAttributeException, NoSuchElementException {
/* 413 */           if (this.iterator == null)
/* 414 */             throw new IOException("Feature Reader has been closed"); 
/*     */           try {
/* 418 */             return SimpleFeatureBuilder.copy(this.iterator.next());
/* 419 */           } catch (NoSuchElementException end) {
/* 420 */             throw new DataSourceException("There are no more Features", end);
/*     */           } 
/*     */         }
/*     */         
/*     */         public boolean hasNext() {
/* 425 */           return (this.iterator != null && this.iterator.hasNext());
/*     */         }
/*     */         
/*     */         public void close() {
/* 429 */           if (this.iterator != null)
/* 430 */             this.iterator = null; 
/* 433 */           if (this.featureType != null)
/* 434 */             this.featureType = null; 
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   public FeatureWriter<SimpleFeatureType, SimpleFeature> createFeatureWriter(final String typeName, final Transaction transaction) throws IOException {
/* 458 */     return new FeatureWriter<SimpleFeatureType, SimpleFeature>() {
/* 459 */         SimpleFeatureType featureType = MemoryDataStore.this.getSchema(typeName);
/*     */         
/* 460 */         Map<String, SimpleFeature> contents = MemoryDataStore.this.features(typeName);
/*     */         
/* 461 */         Iterator<SimpleFeature> iterator = this.contents.values().iterator();
/*     */         
/* 462 */         SimpleFeature live = null;
/*     */         
/* 464 */         SimpleFeature current = null;
/*     */         
/*     */         public SimpleFeatureType getFeatureType() {
/* 467 */           return this.featureType;
/*     */         }
/*     */         
/*     */         public SimpleFeature next() throws IOException, NoSuchElementException {
/* 471 */           if (hasNext()) {
/* 473 */             this.live = this.iterator.next();
/*     */             try {
/* 476 */               this.current = SimpleFeatureBuilder.copy(this.live);
/* 477 */             } catch (IllegalAttributeException e) {
/* 478 */               throw new DataSourceException("Unable to edit " + this.live.getID() + " of " + typeName);
/*     */             } 
/*     */           } else {
/* 483 */             this.live = null;
/*     */             try {
/* 486 */               this.current = SimpleFeatureBuilder.template(this.featureType, null);
/* 487 */             } catch (IllegalAttributeException e) {
/* 488 */               throw new DataSourceException("Unable to add additional Features of " + typeName);
/*     */             } 
/*     */           } 
/* 493 */           return this.current;
/*     */         }
/*     */         
/*     */         public void remove() throws IOException {
/* 497 */           if (this.contents == null)
/* 498 */             throw new IOException("FeatureWriter has been closed"); 
/* 501 */           if (this.current == null)
/* 502 */             throw new IOException("No feature available to remove"); 
/* 505 */           if (this.live != null) {
/* 507 */             this.iterator.remove();
/* 508 */             MemoryDataStore.this.listenerManager.fireFeaturesRemoved(typeName, transaction, new ReferencedEnvelope(this.live.getBounds()), true);
/* 510 */             this.live = null;
/* 511 */             this.current = null;
/*     */           } else {
/* 514 */             this.current = null;
/*     */           } 
/*     */         }
/*     */         
/*     */         public void write() throws IOException {
/* 519 */           if (this.contents == null)
/* 520 */             throw new IOException("FeatureWriter has been closed"); 
/* 523 */           if (this.current == null)
/* 524 */             throw new IOException("No feature available to write"); 
/* 527 */           if (this.live != null) {
/* 528 */             if (this.live.equals(this.current)) {
/* 531 */               this.live = null;
/* 532 */               this.current = null;
/*     */             } else {
/*     */               try {
/* 537 */                 this.live.setAttributes(this.current.getAttributes());
/* 538 */               } catch (Exception e) {
/* 539 */                 throw new DataSourceException("Unable to accept modifications to " + this.live.getID() + " on " + typeName);
/*     */               } 
/* 543 */               ReferencedEnvelope bounds = new ReferencedEnvelope();
/* 544 */               bounds.expandToInclude((Envelope)new ReferencedEnvelope(this.live.getBounds()));
/* 545 */               bounds.expandToInclude((Envelope)new ReferencedEnvelope(this.current.getBounds()));
/* 546 */               MemoryDataStore.this.listenerManager.fireFeaturesChanged(typeName, transaction, bounds, true);
/* 548 */               this.live = null;
/* 549 */               this.current = null;
/*     */             } 
/*     */           } else {
/* 554 */             this.contents.put(this.current.getID(), this.current);
/* 555 */             MemoryDataStore.this.listenerManager.fireFeaturesAdded(typeName, transaction, new ReferencedEnvelope(this.current.getBounds()), true);
/* 557 */             this.current = null;
/*     */           } 
/*     */         }
/*     */         
/*     */         public boolean hasNext() throws IOException {
/* 562 */           if (this.contents == null)
/* 563 */             throw new IOException("FeatureWriter has been closed"); 
/* 566 */           return (this.iterator != null && this.iterator.hasNext());
/*     */         }
/*     */         
/*     */         public void close() {
/* 570 */           if (this.iterator != null)
/* 571 */             this.iterator = null; 
/* 574 */           if (this.featureType != null)
/* 575 */             this.featureType = null; 
/* 578 */           this.contents = null;
/* 579 */           this.current = null;
/* 580 */           this.live = null;
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   protected ReferencedEnvelope getBounds(Query query) throws IOException {
/* 591 */     String typeName = query.getTypeName();
/* 592 */     Map<String, SimpleFeature> contents = features(typeName);
/* 593 */     Iterator<SimpleFeature> iterator = contents.values().iterator();
/* 595 */     CoordinateReferenceSystem coordinateSystem = query.getCoordinateSystem();
/* 596 */     if (coordinateSystem == null) {
/* 597 */       SimpleFeatureType type = this.schema.get(typeName);
/* 598 */       if (type != null)
/* 599 */         coordinateSystem = type.getCoordinateReferenceSystem(); 
/*     */     } 
/* 602 */     ReferencedEnvelope envelope = null;
/* 604 */     Filter filter = query.getFilter();
/* 606 */     int count = 0;
/* 607 */     while (iterator.hasNext() && count < query.getMaxFeatures()) {
/* 608 */       count++;
/* 609 */       SimpleFeature feature = iterator.next();
/* 610 */       if (filter.evaluate(feature)) {
/* 611 */         count++;
/* 612 */         if (null == envelope)
/* 613 */           envelope = new ReferencedEnvelope(coordinateSystem); 
/* 615 */         Geometry geom = (Geometry)feature.getDefaultGeometry();
/* 616 */         Envelope env = (geom != null) ? geom.getEnvelopeInternal() : null;
/* 617 */         if (env != null)
/* 618 */           envelope.expandToInclude(env); 
/*     */       } 
/*     */     } 
/* 623 */     return envelope;
/*     */   }
/*     */   
/*     */   protected int getCount(Query query) throws IOException {
/* 631 */     String typeName = query.getTypeName();
/* 632 */     Map<String, SimpleFeature> contents = features(typeName);
/* 633 */     Iterator<SimpleFeature> iterator = contents.values().iterator();
/* 635 */     int count = 0;
/* 637 */     Filter filter = query.getFilter();
/* 639 */     while (iterator.hasNext() && count < query.getMaxFeatures()) {
/* 640 */       if (filter.evaluate(iterator.next()))
/* 641 */         count++; 
/*     */     } 
/* 645 */     return count;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\memory\MemoryDataStore.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */