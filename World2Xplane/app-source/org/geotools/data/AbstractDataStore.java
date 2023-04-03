/*     */ package org.geotools.data;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import org.geotools.data.simple.SimpleFeatureSource;
/*     */ import org.geotools.feature.FeatureTypes;
/*     */ import org.geotools.feature.NameImpl;
/*     */ import org.geotools.feature.SchemaException;
/*     */ import org.geotools.geometry.jts.ReferencedEnvelope;
/*     */ import org.geotools.util.logging.Logging;
/*     */ import org.opengis.feature.Feature;
/*     */ import org.opengis.feature.simple.SimpleFeature;
/*     */ import org.opengis.feature.simple.SimpleFeatureType;
/*     */ import org.opengis.feature.type.FeatureType;
/*     */ import org.opengis.feature.type.Name;
/*     */ import org.opengis.filter.Filter;
/*     */ 
/*     */ public abstract class AbstractDataStore implements DataStore {
/*  91 */   protected static final Logger LOGGER = Logging.getLogger("org.geotools.data");
/*     */   
/*  94 */   public FeatureListenerManager listenerManager = new FeatureListenerManager();
/*     */   
/*     */   protected final boolean isWriteable;
/*     */   
/*     */   private InProcessLockingManager lockingManager;
/*     */   
/*     */   public AbstractDataStore() {
/* 115 */     this(true);
/*     */   }
/*     */   
/*     */   public AbstractDataStore(boolean isWriteable) {
/* 124 */     this.isWriteable = isWriteable;
/* 125 */     this.lockingManager = createLockingManager();
/*     */   }
/*     */   
/*     */   protected InProcessLockingManager createLockingManager() {
/* 139 */     return new InProcessLockingManager();
/*     */   }
/*     */   
/*     */   protected Map createMetadata(String typeName) {
/* 166 */     return Collections.EMPTY_MAP;
/*     */   }
/*     */   
/*     */   public ServiceInfo getInfo() {
/* 173 */     DefaultServiceInfo info = new DefaultServiceInfo();
/* 174 */     info.setDescription("Features from " + getClass().getSimpleName());
/* 175 */     info.setSchema(FeatureTypes.DEFAULT_NAMESPACE);
/* 176 */     return info;
/*     */   }
/*     */   
/*     */   protected FeatureWriter<SimpleFeatureType, SimpleFeature> createFeatureWriter(String typeName, Transaction transaction) throws IOException {
/* 210 */     throw new UnsupportedOperationException("FeatureWriter not supported");
/*     */   }
/*     */   
/*     */   public void createSchema(SimpleFeatureType featureType) throws IOException {
/* 222 */     throw new UnsupportedOperationException("Schema creation not supported");
/*     */   }
/*     */   
/*     */   public void updateSchema(String typeName, SimpleFeatureType featureType) {
/* 228 */     throw new UnsupportedOperationException("Schema modification not supported");
/*     */   }
/*     */   
/*     */   public SimpleFeatureSource getFeatureSource(final String typeName) throws IOException {
/* 242 */     final SimpleFeatureType featureType = getSchema(typeName);
/* 244 */     if (this.isWriteable) {
/* 245 */       if (this.lockingManager != null)
/* 246 */         return new AbstractFeatureLocking(getSupportedHints()) {
/*     */             public DataStore getDataStore() {
/* 248 */               return AbstractDataStore.this;
/*     */             }
/*     */             
/*     */             public String toString() {
/* 251 */               return "AbstractDataStore.AbstractFeatureLocking(" + typeName + ")";
/*     */             }
/*     */             
/*     */             public void addFeatureListener(FeatureListener listener) {
/* 254 */               AbstractDataStore.this.listenerManager.addFeatureListener(this, listener);
/*     */             }
/*     */             
/*     */             public void removeFeatureListener(FeatureListener listener) {
/* 259 */               AbstractDataStore.this.listenerManager.removeFeatureListener(this, listener);
/*     */             }
/*     */             
/*     */             public SimpleFeatureType getSchema() {
/* 263 */               return featureType;
/*     */             }
/*     */           }; 
/* 266 */       return new AbstractFeatureStore(getSupportedHints()) {
/*     */           public DataStore getDataStore() {
/* 268 */             return AbstractDataStore.this;
/*     */           }
/*     */           
/*     */           public String toString() {
/* 271 */             return "AbstractDataStore.AbstractFeatureStore(" + typeName + ")";
/*     */           }
/*     */           
/*     */           public void addFeatureListener(FeatureListener listener) {
/* 274 */             AbstractDataStore.this.listenerManager.addFeatureListener((FeatureSource<? extends FeatureType, ? extends Feature>)this, listener);
/*     */           }
/*     */           
/*     */           public void removeFeatureListener(FeatureListener listener) {
/* 279 */             AbstractDataStore.this.listenerManager.removeFeatureListener((FeatureSource<? extends FeatureType, ? extends Feature>)this, listener);
/*     */           }
/*     */           
/*     */           public SimpleFeatureType getSchema() {
/* 283 */             return featureType;
/*     */           }
/*     */         };
/*     */     } 
/* 287 */     return new AbstractFeatureSource(getSupportedHints()) {
/*     */         public DataStore getDataStore() {
/* 289 */           return AbstractDataStore.this;
/*     */         }
/*     */         
/*     */         public String toString() {
/* 292 */           return "AbstractDataStore.AbstractFeatureSource(" + typeName + ")";
/*     */         }
/*     */         
/*     */         public void addFeatureListener(FeatureListener listener) {
/* 295 */           AbstractDataStore.this.listenerManager.addFeatureListener((FeatureSource<? extends FeatureType, ? extends Feature>)this, listener);
/*     */         }
/*     */         
/*     */         public void removeFeatureListener(FeatureListener listener) {
/* 299 */           AbstractDataStore.this.listenerManager.removeFeatureListener((FeatureSource<? extends FeatureType, ? extends Feature>)this, listener);
/*     */         }
/*     */         
/*     */         public SimpleFeatureType getSchema() {
/* 303 */           return featureType;
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   public FeatureReader<SimpleFeatureType, SimpleFeature> getFeatureReader(Query query, Transaction transaction) throws IOException {
/* 312 */     Filter filter = query.getFilter();
/* 313 */     String typeName = query.getTypeName();
/* 314 */     String[] propertyNames = query.getPropertyNames();
/* 316 */     if (filter == null)
/* 317 */       throw new NullPointerException("getFeatureReader requires Filter: did you mean Filter.INCLUDE?"); 
/* 320 */     if (typeName == null)
/* 321 */       throw new NullPointerException("getFeatureReader requires typeName: use getTypeNames() for a list of available types"); 
/* 325 */     if (transaction == null)
/* 326 */       throw new NullPointerException("getFeatureReader requires Transaction: did you mean to use Transaction.AUTO_COMMIT?"); 
/* 330 */     SimpleFeatureType featureType = getSchema(query.getTypeName());
/* 332 */     if (propertyNames != null || query.getCoordinateSystem() != null)
/*     */       try {
/* 334 */         featureType = DataUtilities.createSubType(featureType, propertyNames, query.getCoordinateSystem());
/* 335 */       } catch (SchemaException e) {
/* 336 */         LOGGER.log(Level.FINEST, e.getMessage(), (Throwable)e);
/* 337 */         throw new DataSourceException("Could not create Feature Type for query", e);
/*     */       }  
/* 341 */     if (filter == Filter.EXCLUDE || filter.equals(Filter.EXCLUDE))
/* 342 */       return new EmptyFeatureReader<SimpleFeatureType, SimpleFeature>(featureType); 
/* 346 */     filter = getUnsupportedFilter(typeName, filter);
/* 347 */     if (filter == null)
/* 348 */       throw new NullPointerException("getUnsupportedFilter shouldn't return null. Do you mean Filter.INCLUDE?"); 
/* 357 */     Diff diff = null;
/* 358 */     if (transaction != Transaction.AUTO_COMMIT) {
/* 359 */       TransactionStateDiff state = state(transaction);
/* 360 */       if (state != null)
/* 361 */         diff = state.diff(typeName); 
/*     */     } 
/* 369 */     FeatureReader<SimpleFeatureType, SimpleFeature> reader = getFeatureReader(typeName, query);
/* 371 */     if (diff != null)
/* 372 */       reader = new DiffFeatureReader<SimpleFeatureType, SimpleFeature>(reader, diff, query.getFilter()); 
/* 374 */     if (!filter.equals(Filter.INCLUDE))
/* 375 */       reader = new FilteringFeatureReader<SimpleFeatureType, SimpleFeature>(reader, filter); 
/* 378 */     if (!featureType.equals(reader.getFeatureType())) {
/* 379 */       LOGGER.fine("Recasting feature type to subtype by using a ReTypeFeatureReader");
/* 380 */       reader = new ReTypeFeatureReader(reader, featureType, false);
/*     */     } 
/* 383 */     if (query.getMaxFeatures() != Integer.MAX_VALUE)
/* 384 */       reader = new MaxFeatureReader<SimpleFeatureType, SimpleFeature>(reader, query.getMaxFeatures()); 
/* 387 */     return reader;
/*     */   }
/*     */   
/*     */   protected FeatureReader<SimpleFeatureType, SimpleFeature> getFeatureReader(String typeName, Query query) throws IOException {
/* 403 */     return getFeatureReader(typeName);
/*     */   }
/*     */   
/*     */   protected Filter getUnsupportedFilter(String typeName, Filter filter) {
/* 416 */     return filter;
/*     */   }
/*     */   
/*     */   protected TransactionStateDiff state(Transaction transaction) {
/* 431 */     synchronized (transaction) {
/* 432 */       TransactionStateDiff state = (TransactionStateDiff)transaction.getState(this);
/* 435 */       if (state == null) {
/* 436 */         state = new TransactionStateDiff(this);
/* 437 */         transaction.putState(this, state);
/*     */       } 
/* 440 */       return state;
/*     */     } 
/*     */   }
/*     */   
/*     */   public FeatureWriter<SimpleFeatureType, SimpleFeature> getFeatureWriter(String typeName, Filter filter, Transaction transaction) throws IOException {
/*     */     FeatureWriter<SimpleFeatureType, SimpleFeature> writer;
/* 449 */     if (filter == null)
/* 450 */       throw new NullPointerException("getFeatureReader requires Filter: did you mean Filter.INCLUDE?"); 
/* 454 */     if (filter == Filter.EXCLUDE) {
/* 455 */       SimpleFeatureType featureType = getSchema(typeName);
/* 457 */       return new EmptyFeatureWriter(featureType);
/*     */     } 
/* 460 */     if (transaction == null)
/* 461 */       throw new NullPointerException("getFeatureWriter requires Transaction: did you mean to use Transaction.AUTO_COMMIT?"); 
/* 468 */     if (transaction == Transaction.AUTO_COMMIT) {
/*     */       try {
/* 470 */         writer = createFeatureWriter(typeName, transaction);
/* 471 */       } catch (UnsupportedOperationException e) {
/* 472 */         throw e;
/*     */       } 
/*     */     } else {
/* 475 */       TransactionStateDiff state = state(transaction);
/* 476 */       if (state != null) {
/* 477 */         writer = state.writer(typeName, filter);
/*     */       } else {
/* 480 */         throw new UnsupportedOperationException("Subclass sould implement");
/*     */       } 
/*     */     } 
/* 484 */     if (this.lockingManager != null)
/* 487 */       writer = this.lockingManager.checkedWriter(writer, transaction); 
/* 490 */     if (filter != Filter.INCLUDE)
/* 491 */       writer = new FilteringFeatureWriter(writer, filter); 
/* 494 */     return writer;
/*     */   }
/*     */   
/*     */   public FeatureWriter<SimpleFeatureType, SimpleFeature> getFeatureWriter(String typeName, Transaction transaction) throws IOException {
/* 503 */     return getFeatureWriter(typeName, (Filter)Filter.INCLUDE, transaction);
/*     */   }
/*     */   
/*     */   public FeatureWriter<SimpleFeatureType, SimpleFeature> getFeatureWriterAppend(String typeName, Transaction transaction) throws IOException {
/* 512 */     FeatureWriter<SimpleFeatureType, SimpleFeature> writer = getFeatureWriter(typeName, transaction);
/* 514 */     while (writer.hasNext())
/* 515 */       writer.next(); 
/* 518 */     return writer;
/*     */   }
/*     */   
/*     */   public LockingManager getLockingManager() {
/* 532 */     return this.lockingManager;
/*     */   }
/*     */   
/*     */   protected ReferencedEnvelope getBounds(Query query) throws IOException {
/* 549 */     return null;
/*     */   }
/*     */   
/*     */   protected int getCount(Query query) throws IOException {
/* 569 */     return -1;
/*     */   }
/*     */   
/*     */   protected Set getSupportedHints() {
/* 578 */     return Collections.EMPTY_SET;
/*     */   }
/*     */   
/*     */   public void dispose() {}
/*     */   
/*     */   public SimpleFeatureSource getFeatureSource(Name typeName) throws IOException {
/* 598 */     return getFeatureSource(typeName.getLocalPart());
/*     */   }
/*     */   
/*     */   public List<Name> getNames() throws IOException {
/* 609 */     String[] typeNames = getTypeNames();
/* 610 */     List<Name> names = new ArrayList<Name>(typeNames.length);
/* 611 */     for (String typeName : typeNames)
/* 612 */       names.add(new NameImpl(typeName)); 
/* 614 */     return names;
/*     */   }
/*     */   
/*     */   public SimpleFeatureType getSchema(Name name) throws IOException {
/* 624 */     return getSchema(name.getLocalPart());
/*     */   }
/*     */   
/*     */   public void updateSchema(Name typeName, SimpleFeatureType featureType) throws IOException {
/* 635 */     updateSchema(typeName.getLocalPart(), featureType);
/*     */   }
/*     */   
/*     */   public void removeSchema(Name typeName) throws IOException {
/* 642 */     throw new UnsupportedOperationException("Schema removal not supported");
/*     */   }
/*     */   
/*     */   public void removeSchema(String typeName) throws IOException {
/* 649 */     throw new UnsupportedOperationException("Schema removal not supported");
/*     */   }
/*     */   
/*     */   public abstract String[] getTypeNames() throws IOException;
/*     */   
/*     */   public abstract SimpleFeatureType getSchema(String paramString) throws IOException;
/*     */   
/*     */   protected abstract FeatureReader<SimpleFeatureType, SimpleFeature> getFeatureReader(String paramString) throws IOException;
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\AbstractDataStore.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */