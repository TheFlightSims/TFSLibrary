/*      */ package org.geotools.data.store;
/*      */ 
/*      */ import com.vividsolutions.jts.geom.Envelope;
/*      */ import java.io.IOException;
/*      */ import java.lang.reflect.Constructor;
/*      */ import java.net.URI;
/*      */ import java.net.URISyntaxException;
/*      */ import java.util.Collections;
/*      */ import java.util.HashSet;
/*      */ import java.util.Iterator;
/*      */ import java.util.Set;
/*      */ import java.util.logging.Level;
/*      */ import java.util.logging.Logger;
/*      */ import org.geotools.data.DataAccess;
/*      */ import org.geotools.data.DataUtilities;
/*      */ import org.geotools.data.Diff;
/*      */ import org.geotools.data.DiffFeatureReader;
/*      */ import org.geotools.data.FeatureListener;
/*      */ import org.geotools.data.FeatureLock;
/*      */ import org.geotools.data.FeatureLockException;
/*      */ import org.geotools.data.FeatureReader;
/*      */ import org.geotools.data.FilteringFeatureReader;
/*      */ import org.geotools.data.MaxFeatureReader;
/*      */ import org.geotools.data.Query;
/*      */ import org.geotools.data.QueryCapabilities;
/*      */ import org.geotools.data.ReTypeFeatureReader;
/*      */ import org.geotools.data.ResourceInfo;
/*      */ import org.geotools.data.Transaction;
/*      */ import org.geotools.data.TransactionStateDiff;
/*      */ import org.geotools.data.crs.ReprojectFeatureReader;
/*      */ import org.geotools.data.simple.SimpleFeatureCollection;
/*      */ import org.geotools.data.simple.SimpleFeatureSource;
/*      */ import org.geotools.data.sort.SortedFeatureReader;
/*      */ import org.geotools.factory.CommonFactoryFinder;
/*      */ import org.geotools.factory.Hints;
/*      */ import org.geotools.feature.FeatureCollection;
/*      */ import org.geotools.feature.simple.SimpleFeatureTypeBuilder;
/*      */ import org.geotools.geometry.jts.ReferencedEnvelope;
/*      */ import org.geotools.util.NullProgressListener;
/*      */ import org.opengis.feature.Feature;
/*      */ import org.opengis.feature.FeatureVisitor;
/*      */ import org.opengis.feature.simple.SimpleFeature;
/*      */ import org.opengis.feature.simple.SimpleFeatureType;
/*      */ import org.opengis.feature.type.FeatureType;
/*      */ import org.opengis.feature.type.Name;
/*      */ import org.opengis.filter.Filter;
/*      */ import org.opengis.filter.FilterFactory2;
/*      */ import org.opengis.filter.Id;
/*      */ import org.opengis.filter.identity.FeatureId;
/*      */ import org.opengis.filter.sort.SortBy;
/*      */ import org.opengis.geometry.BoundingBox;
/*      */ import org.opengis.referencing.crs.CoordinateReferenceSystem;
/*      */ import org.opengis.util.ProgressListener;
/*      */ 
/*      */ public abstract class ContentFeatureSource implements SimpleFeatureSource {
/*      */   protected ContentEntry entry;
/*      */   
/*      */   protected Transaction transaction;
/*      */   
/*  121 */   protected FeatureLock lock = FeatureLock.TRANSACTION;
/*      */   
/*      */   protected Set<Hints.Key> hints;
/*      */   
/*      */   protected Query query;
/*      */   
/*      */   protected SimpleFeatureType schema;
/*      */   
/*      */   protected QueryCapabilities queryCapabilities;
/*      */   
/*      */   public ContentFeatureSource(ContentEntry entry, Query query) {
/*  152 */     this.entry = entry;
/*  153 */     this.query = query;
/*  156 */     this.hints = new HashSet<Hints.Key>();
/*  157 */     this.hints.add(Hints.JTS_GEOMETRY_FACTORY);
/*  158 */     this.hints.add(Hints.JTS_COORDINATE_SEQUENCE_FACTORY);
/*  161 */     addHints(this.hints);
/*  164 */     this.hints = Collections.unmodifiableSet(this.hints);
/*      */   }
/*      */   
/*      */   public ContentEntry getEntry() {
/*  170 */     return this.entry;
/*      */   }
/*      */   
/*      */   public Transaction getTransaction() {
/*  183 */     return this.transaction;
/*      */   }
/*      */   
/*      */   public void setTransaction(Transaction transaction) {
/*  195 */     this.transaction = transaction;
/*      */   }
/*      */   
/*      */   public ContentState getState() {
/*  206 */     return this.entry.getState(this.transaction);
/*      */   }
/*      */   
/*      */   public ContentDataStore getDataStore() {
/*  217 */     return this.entry.getDataStore();
/*      */   }
/*      */   
/*      */   public final boolean isView() {
/*  224 */     return (this.query != null && this.query != Query.ALL);
/*      */   }
/*      */   
/*      */   public ResourceInfo getInfo() {
/*  234 */     return new ResourceInfo() {
/*      */         final Set<String> words;
/*      */         
/*      */         public ReferencedEnvelope getBounds() {
/*      */           try {
/*  242 */             return ContentFeatureSource.this.getBounds();
/*  243 */           } catch (IOException e) {
/*  244 */             return null;
/*      */           } 
/*      */         }
/*      */         
/*      */         public CoordinateReferenceSystem getCRS() {
/*  248 */           return ContentFeatureSource.this.getSchema().getCoordinateReferenceSystem();
/*      */         }
/*      */         
/*      */         public String getDescription() {
/*  252 */           return null;
/*      */         }
/*      */         
/*      */         public Set<String> getKeywords() {
/*  256 */           return this.words;
/*      */         }
/*      */         
/*      */         public String getName() {
/*  260 */           return ContentFeatureSource.this.getSchema().getTypeName();
/*      */         }
/*      */         
/*      */         public URI getSchema() {
/*  264 */           Name name = ContentFeatureSource.this.getSchema().getName();
/*      */           try {
/*  267 */             URI namespace = new URI(name.getNamespaceURI());
/*  268 */             return namespace;
/*  269 */           } catch (URISyntaxException e) {
/*  270 */             return null;
/*      */           } 
/*      */         }
/*      */         
/*      */         public String getTitle() {
/*  275 */           Name name = ContentFeatureSource.this.getSchema().getName();
/*  276 */           return name.getLocalPart();
/*      */         }
/*      */       };
/*      */   }
/*      */   
/*      */   public Name getName() {
/*  291 */     return getSchema().getName();
/*      */   }
/*      */   
/*      */   public final SimpleFeatureType getSchema() {
/*  305 */     if (this.schema != null)
/*  306 */       return this.schema; 
/*  309 */     SimpleFeatureType featureType = getAbsoluteSchema();
/*  312 */     if (this.query != null && this.query.getPropertyNames() != Query.ALL_NAMES) {
/*  313 */       synchronized (this) {
/*  314 */         if (this.schema == null)
/*  315 */           this.schema = SimpleFeatureTypeBuilder.retype(featureType, this.query.getPropertyNames()); 
/*      */       } 
/*  319 */       return this.schema;
/*      */     } 
/*  322 */     return featureType;
/*      */   }
/*      */   
/*      */   protected final SimpleFeatureType getAbsoluteSchema() {
/*  332 */     ContentState state = this.entry.getState(this.transaction);
/*  333 */     SimpleFeatureType featureType = state.getFeatureType();
/*  335 */     if (featureType == null)
/*  337 */       synchronized (state) {
/*  338 */         if (featureType == null) {
/*      */           try {
/*  340 */             featureType = buildFeatureType();
/*  341 */           } catch (IOException e) {
/*  342 */             throw new RuntimeException(e);
/*      */           } 
/*  344 */           state.setFeatureType(featureType);
/*      */         } 
/*      */       }  
/*  349 */     return featureType;
/*      */   }
/*      */   
/*      */   public final ReferencedEnvelope getBounds() throws IOException {
/*  364 */     return getBounds(Query.ALL);
/*      */   }
/*      */   
/*      */   public final ReferencedEnvelope getBounds(Query query) throws IOException {
/*      */     ReferencedEnvelope bounds;
/*  377 */     query = joinQuery(query);
/*  378 */     query = resolvePropertyNames(query);
/*  384 */     if (!canTransact() && this.transaction != null && this.transaction != Transaction.AUTO_COMMIT) {
/*  386 */       DiffTransactionState state = (DiffTransactionState)getTransaction().getState(getEntry());
/*  387 */       Diff diff = state.getDiff();
/*  390 */       Iterator<SimpleFeature> it = diff.getModified().values().iterator();
/*  391 */       FilterFactory2 ff = CommonFactoryFinder.getFilterFactory2();
/*  392 */       Set<FeatureId> modifiedFids = new HashSet<FeatureId>();
/*  393 */       while (it.hasNext()) {
/*  394 */         SimpleFeature feature = it.next();
/*  395 */         modifiedFids.add(ff.featureId(feature.getID()));
/*      */       } 
/*  397 */       Id idFilter = ff.id(modifiedFids);
/*  398 */       Query q = new Query(query);
/*  399 */       q.setFilter((Filter)ff.and((Filter)idFilter, query.getFilter()));
/*  400 */       bounds = getBoundsInternal(q);
/*  403 */       if (bounds != null) {
/*  405 */         it = diff.getAdded().values().iterator();
/*  406 */         while (it.hasNext()) {
/*  407 */           SimpleFeature feature = it.next();
/*  408 */           BoundingBox fb = feature.getBounds();
/*  409 */           if (fb != null)
/*  410 */             bounds.expandToInclude((Envelope)ReferencedEnvelope.reference(fb)); 
/*      */         } 
/*  415 */         it = diff.getModified().values().iterator();
/*  416 */         while (it.hasNext()) {
/*  417 */           SimpleFeature feature = it.next();
/*  418 */           if (feature != TransactionStateDiff.NULL) {
/*  419 */             BoundingBox fb = feature.getBounds();
/*  420 */             if (fb != null)
/*  421 */               bounds.expandToInclude((Envelope)ReferencedEnvelope.reference(fb)); 
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } else {
/*  427 */       bounds = getBoundsInternal(query);
/*      */     } 
/*  430 */     return bounds;
/*      */   }
/*      */   
/*      */   public final int getCount(Query query) throws IOException {
/*  448 */     query = joinQuery(query);
/*  449 */     query = resolvePropertyNames(query);
/*  452 */     int count = getCountInternal(query);
/*  455 */     if (count >= 0 && !canTransact() && this.transaction != null && this.transaction != Transaction.AUTO_COMMIT) {
/*  456 */       DiffTransactionState state = (DiffTransactionState)getTransaction().getState(getEntry());
/*  457 */       Diff diff = state.getDiff();
/*  458 */       synchronized (diff) {
/*  460 */         Iterator<SimpleFeature> it = diff.getAdded().values().iterator();
/*  461 */         Filter filter = query.getFilter();
/*  462 */         while (it.hasNext()) {
/*  463 */           Object feature = it.next();
/*  464 */           if (filter.evaluate(feature))
/*  465 */             count++; 
/*      */         } 
/*  471 */         it = diff.getModified().values().iterator();
/*  472 */         FilterFactory2 ff = CommonFactoryFinder.getFilterFactory2();
/*  473 */         Set<FeatureId> modifiedFids = new HashSet<FeatureId>();
/*  474 */         int modifiedPostCount = 0;
/*  475 */         while (it.hasNext()) {
/*  476 */           SimpleFeature feature = it.next();
/*  478 */           if (feature == TransactionStateDiff.NULL) {
/*  479 */             count--;
/*      */             continue;
/*      */           } 
/*  481 */           modifiedFids.add(ff.featureId(feature.getID()));
/*  482 */           if (filter.evaluate(feature))
/*  483 */             modifiedPostCount++; 
/*      */         } 
/*  490 */         if (modifiedFids.size() > 0) {
/*  491 */           Id idFilter = ff.id(modifiedFids);
/*  492 */           Query q = new Query(query);
/*  493 */           q.setFilter((Filter)ff.and((Filter)idFilter, query.getFilter()));
/*  494 */           int modifiedPreCount = getCountInternal(q);
/*  495 */           if (modifiedPreCount == -1)
/*  496 */             return -1; 
/*  498 */           count = count + modifiedPostCount - modifiedPreCount;
/*      */         } 
/*      */       } 
/*      */     } 
/*  504 */     return count;
/*      */   }
/*      */   
/*      */   public final ContentFeatureCollection getFeatures() throws IOException {
/*  517 */     Query query = joinQuery(Query.ALL);
/*  518 */     return new ContentFeatureCollection(this, query);
/*      */   }
/*      */   
/*      */   public final FeatureReader<SimpleFeatureType, SimpleFeature> getReader() throws IOException {
/*  528 */     return getReader(Query.ALL);
/*      */   }
/*      */   
/*      */   public final ContentFeatureCollection getFeatures(Query query) throws IOException {
/*  537 */     query = joinQuery(query);
/*  539 */     return new ContentFeatureCollection(this, query);
/*      */   }
/*      */   
/*      */   public final FeatureReader<SimpleFeatureType, SimpleFeature> getReader(Query query) throws IOException {
/*      */     DiffFeatureReader diffFeatureReader;
/*      */     FilteringFeatureReader filteringFeatureReader;
/*      */     ReTypeFeatureReader reTypeFeatureReader;
/*      */     SortedFeatureReader sortedFeatureReader;
/*      */     MaxFeatureReader maxFeatureReader;
/*      */     ReprojectFeatureReader reprojectFeatureReader;
/*  547 */     query = joinQuery(query);
/*  548 */     query = resolvePropertyNames(query);
/*  551 */     if (query.getStartIndex() != null && (query.getSortBy() == null || (query.getSortBy()).length == 0)) {
/*  553 */       Query dq = new Query(query);
/*  554 */       dq.setSortBy(new SortBy[] { SortBy.NATURAL_ORDER });
/*  555 */       query = dq;
/*      */     } 
/*  559 */     if (!query.getJoins().isEmpty() && getQueryCapabilities().isJoiningSupported())
/*  560 */       throw new IOException("Feature source does not support joins"); 
/*  563 */     FeatureReader<SimpleFeatureType, SimpleFeature> reader = getReaderInternal(query);
/*  569 */     if (!canTransact() && this.transaction != null && this.transaction != Transaction.AUTO_COMMIT) {
/*  570 */       DiffTransactionState state = (DiffTransactionState)getTransaction().getState(getEntry());
/*  571 */       diffFeatureReader = new DiffFeatureReader(reader, state.getDiff());
/*      */     } 
/*  575 */     if (!canFilter() && 
/*  576 */       query.getFilter() != null && query.getFilter() != Filter.INCLUDE)
/*  577 */       filteringFeatureReader = new FilteringFeatureReader((FeatureReader)diffFeatureReader, query.getFilter()); 
/*  582 */     if (!canRetype() && 
/*  583 */       query.getPropertyNames() != Query.ALL_NAMES) {
/*  585 */       SimpleFeatureType target = SimpleFeatureTypeBuilder.retype(getSchema(), query.getPropertyNames());
/*  590 */       if (!target.equals(filteringFeatureReader.getFeatureType()))
/*  591 */         reTypeFeatureReader = new ReTypeFeatureReader((FeatureReader)filteringFeatureReader, target, false); 
/*      */     } 
/*  597 */     if (query.getSortBy() != null && (query.getSortBy()).length != 0 && 
/*  598 */       !canSort())
/*  599 */       sortedFeatureReader = new SortedFeatureReader(DataUtilities.simple((FeatureReader)reTypeFeatureReader), query); 
/*  605 */     int offset = (query.getStartIndex() != null) ? query.getStartIndex().intValue() : 0;
/*  606 */     if (!canOffset() && offset > 0)
/*  608 */       for (int i = 0; i < offset && sortedFeatureReader.hasNext(); i++)
/*  609 */         sortedFeatureReader.next();  
/*  614 */     if (!canLimit() && 
/*  615 */       query.getMaxFeatures() != -1 && query.getMaxFeatures() < Integer.MAX_VALUE)
/*  616 */       maxFeatureReader = new MaxFeatureReader((FeatureReader)sortedFeatureReader, query.getMaxFeatures()); 
/*  621 */     if (!canReproject()) {
/*  622 */       CoordinateReferenceSystem targetCRS = query.getCoordinateSystemReproject();
/*  623 */       if (targetCRS != null) {
/*  624 */         CoordinateReferenceSystem nativeCRS = ((SimpleFeatureType)maxFeatureReader.getFeatureType()).getCoordinateReferenceSystem();
/*  625 */         if (nativeCRS == null)
/*  626 */           throw new IOException("Cannot reproject data, the source CRS is not available"); 
/*  627 */         if (!nativeCRS.equals(targetCRS))
/*      */           try {
/*  629 */             reprojectFeatureReader = new ReprojectFeatureReader((FeatureReader)maxFeatureReader, targetCRS);
/*  630 */           } catch (Exception e) {
/*  631 */             if (e instanceof IOException)
/*  632 */               throw (IOException)e; 
/*  634 */             throw (IOException)(new IOException("Error occurred trying to reproject data")).initCause(e);
/*      */           }  
/*      */       } 
/*      */     } 
/*  641 */     if (!canLock());
/*  647 */     return (FeatureReader<SimpleFeatureType, SimpleFeature>)reprojectFeatureReader;
/*      */   }
/*      */   
/*      */   public void accepts(Query query, FeatureVisitor visitor, ProgressListener progress) throws IOException {
/*      */     NullProgressListener nullProgressListener;
/*  675 */     if (progress == null)
/*  676 */       nullProgressListener = new NullProgressListener(); 
/*  680 */     if (handleVisitor(query, visitor))
/*      */       return; 
/*  686 */     FeatureReader<SimpleFeatureType, SimpleFeature> reader = getReader(query);
/*      */     try {
/*  688 */       float size = (nullProgressListener instanceof NullProgressListener) ? 0.0F : getCount(query);
/*  689 */       float position = 0.0F;
/*  690 */       nullProgressListener.started();
/*  691 */       while (reader.hasNext()) {
/*  692 */         SimpleFeature feature = null;
/*  693 */         if (size > 0.0F)
/*  693 */           nullProgressListener.progress(position++ / size); 
/*      */         try {
/*  695 */           feature = (SimpleFeature)reader.next();
/*  696 */           visitor.visit((Feature)feature);
/*  698 */         } catch (IOException erp) {
/*  699 */           nullProgressListener.exceptionOccurred(erp);
/*  700 */           throw erp;
/*  702 */         } catch (Exception unexpected) {
/*  703 */           nullProgressListener.exceptionOccurred(unexpected);
/*  704 */           String fid = (feature == null) ? "feature" : feature.getIdentifier().toString();
/*  705 */           throw new IOException("Problem visiting " + query.getTypeName() + " visiting " + fid + ":" + unexpected, unexpected);
/*      */         } 
/*      */       } 
/*      */     } finally {
/*  711 */       nullProgressListener.complete();
/*  712 */       reader.close();
/*      */     } 
/*      */   }
/*      */   
/*      */   protected boolean handleVisitor(Query query, FeatureVisitor visitor) throws IOException {
/*  729 */     return false;
/*      */   }
/*      */   
/*      */   protected boolean canReproject() {
/*  777 */     return false;
/*      */   }
/*      */   
/*      */   protected boolean canLimit() {
/*  796 */     return false;
/*      */   }
/*      */   
/*      */   protected boolean canOffset() {
/*  814 */     return false;
/*      */   }
/*      */   
/*      */   protected boolean canFilter() {
/*  835 */     return false;
/*      */   }
/*      */   
/*      */   protected boolean canRetype() {
/*  854 */     return false;
/*      */   }
/*      */   
/*      */   protected boolean canSort() {
/*  870 */     return false;
/*      */   }
/*      */   
/*      */   protected boolean canTransact() {
/*  882 */     return false;
/*      */   }
/*      */   
/*      */   protected boolean canEvent() {
/*  894 */     return false;
/*      */   }
/*      */   
/*      */   public final ContentFeatureSource getView(Query query) throws IOException {
/*  909 */     query = joinQuery(query);
/*  910 */     query = resolvePropertyNames(query);
/*  913 */     Class<?> clazz = getClass();
/*      */     try {
/*  916 */       Constructor<?> c = clazz.getConstructor(new Class[] { ContentEntry.class, Query.class });
/*  917 */       ContentFeatureSource source = (ContentFeatureSource)c.newInstance(new Object[] { getEntry(), query });
/*  920 */       source.setTransaction(this.transaction);
/*  921 */       return source;
/*  923 */     } catch (Exception e) {
/*  924 */       String msg = "Subclass must implement Constructor(ContentEntry,Query)";
/*  925 */       throw (IOException)(new IOException(msg)).initCause(e);
/*      */     } 
/*      */   }
/*      */   
/*      */   public final ContentFeatureCollection getFeatures(Filter filter) throws IOException {
/*  938 */     return getFeatures(new Query(getSchema().getTypeName(), filter));
/*      */   }
/*      */   
/*      */   public final FeatureReader<SimpleFeatureType, SimpleFeature> getReader(Filter filter) throws IOException {
/*  948 */     return getReader(new Query(getSchema().getTypeName(), filter));
/*      */   }
/*      */   
/*      */   public final ContentFeatureSource getView(Filter filter) throws IOException {
/*  952 */     return getView(new Query(getSchema().getTypeName(), filter));
/*      */   }
/*      */   
/*      */   public final void addFeatureListener(FeatureListener listener) {
/*  962 */     this.entry.getState(this.transaction).addListener(listener);
/*      */   }
/*      */   
/*      */   public final void removeFeatureListener(FeatureListener listener) {
/*  969 */     this.entry.getState(this.transaction).removeListener(listener);
/*      */   }
/*      */   
/*      */   public final Set getSupportedHints() {
/*  982 */     return this.hints;
/*      */   }
/*      */   
/*      */   protected void addHints(Set<Hints.Key> hints) {}
/*      */   
/*      */   protected Query joinQuery(Query query) {
/* 1009 */     return DataUtilities.mixQueries(this.query, query, null);
/*      */   }
/*      */   
/*      */   protected Query resolvePropertyNames(Query query) {
/* 1021 */     return DataUtilities.resolvePropertyNames(query, getSchema());
/*      */   }
/*      */   
/*      */   protected Filter resolvePropertyNames(Filter filter) {
/* 1026 */     return DataUtilities.resolvePropertyNames(filter, getSchema());
/*      */   }
/*      */   
/*      */   protected QueryCapabilities buildQueryCapabilities() {
/* 1054 */     return new QueryCapabilities();
/*      */   }
/*      */   
/*      */   public QueryCapabilities getQueryCapabilities() {
/* 1080 */     if (this.queryCapabilities == null)
/* 1081 */       this.queryCapabilities = buildQueryCapabilities(); 
/* 1086 */     return new QueryCapabilities() {
/*      */         public boolean isOffsetSupported() {
/* 1089 */           return true;
/*      */         }
/*      */         
/*      */         public boolean supportsSorting(SortBy[] sortAttributes) {
/* 1093 */           if (ContentFeatureSource.this.queryCapabilities.supportsSorting(sortAttributes))
/* 1095 */             return true; 
/* 1098 */           return SortedFeatureReader.canSort(ContentFeatureSource.this.getSchema(), sortAttributes);
/*      */         }
/*      */         
/*      */         public boolean isReliableFIDSupported() {
/* 1103 */           return ContentFeatureSource.this.queryCapabilities.isReliableFIDSupported();
/*      */         }
/*      */         
/*      */         public boolean isUseProvidedFIDSupported() {
/* 1107 */           return ContentFeatureSource.this.queryCapabilities.isUseProvidedFIDSupported();
/*      */         }
/*      */       };
/*      */   }
/*      */   
/*      */   public final void setFeatureLock(FeatureLock lock) {
/* 1120 */     if (canLock())
/* 1120 */       lock = processLock(lock); 
/* 1121 */     this.lock = lock;
/*      */   }
/*      */   
/*      */   public final int lockFeatures() throws IOException {
/* 1131 */     return lockFeatures((Filter)Filter.INCLUDE);
/*      */   }
/*      */   
/*      */   public final int lockFeatures(Query query) throws IOException {
/* 1141 */     return lockFeatures(query.getFilter());
/*      */   }
/*      */   
/*      */   public final int lockFeatures(Filter filter) throws IOException {
/* 1148 */     Logger logger = getDataStore().getLogger();
/* 1150 */     String typeName = getSchema().getTypeName();
/* 1152 */     FeatureReader<SimpleFeatureType, SimpleFeature> reader = getReader(filter);
/*      */     try {
/* 1154 */       int locked = 0;
/* 1155 */       while (reader.hasNext()) {
/* 1156 */         SimpleFeature feature = (SimpleFeature)reader.next();
/*      */         try {
/* 1161 */           if (canLock()) {
/* 1162 */             doLockInternal(typeName, feature);
/*      */           } else {
/* 1164 */             getDataStore().getLockingManager().lockFeatureID(typeName, feature.getID(), this.transaction, this.lock);
/*      */           } 
/* 1168 */           logger.fine("Locked feature: " + feature.getID());
/* 1169 */           locked++;
/* 1171 */         } catch (FeatureLockException e) {
/* 1173 */           String msg = "Unable to lock feature:" + feature.getID() + "." + " Change logging to FINEST for stack trace";
/* 1175 */           logger.fine(msg);
/* 1176 */           logger.log(Level.FINEST, "Unable to lock feature: " + feature.getID(), (Throwable)e);
/*      */         } 
/*      */       } 
/* 1180 */       return locked;
/*      */     } finally {
/* 1183 */       reader.close();
/*      */     } 
/*      */   }
/*      */   
/*      */   public final void unLockFeatures() throws IOException {
/* 1195 */     unLockFeatures((Filter)Filter.INCLUDE);
/*      */   }
/*      */   
/*      */   public final void unLockFeatures(Query query) throws IOException {
/* 1206 */     unLockFeatures(query.getFilter());
/*      */   }
/*      */   
/*      */   public final void unLockFeatures(Filter filter) throws IOException {
/* 1213 */     filter = resolvePropertyNames(filter);
/* 1214 */     String typeName = getSchema().getTypeName();
/* 1216 */     FeatureReader<SimpleFeatureType, SimpleFeature> reader = getReader(filter);
/*      */     try {
/* 1218 */       while (reader.hasNext()) {
/* 1219 */         SimpleFeature feature = (SimpleFeature)reader.next();
/* 1223 */         if (canLock()) {
/* 1224 */           doLockInternal(typeName, feature);
/*      */           continue;
/*      */         } 
/* 1226 */         getDataStore().getLockingManager().unLockFeatureID(typeName, feature.getID(), this.transaction, this.lock);
/*      */       } 
/*      */     } finally {
/* 1232 */       reader.close();
/*      */     } 
/*      */   }
/*      */   
/*      */   protected boolean canLock() {
/* 1251 */     return false;
/*      */   }
/*      */   
/*      */   protected FeatureLock processLock(FeatureLock lock) {
/* 1262 */     return lock;
/*      */   }
/*      */   
/*      */   protected void doLockInternal(String typeName, SimpleFeature feature) throws IOException {
/* 1272 */     throw new UnsupportedOperationException("native locking not implemented");
/*      */   }
/*      */   
/*      */   protected void doUnlockInternal(String typeName, SimpleFeature feature) throws IOException {
/* 1282 */     throw new UnsupportedOperationException("native locking not implemented");
/*      */   }
/*      */   
/*      */   protected abstract ReferencedEnvelope getBoundsInternal(Query paramQuery) throws IOException;
/*      */   
/*      */   protected abstract int getCountInternal(Query paramQuery) throws IOException;
/*      */   
/*      */   protected abstract FeatureReader<SimpleFeatureType, SimpleFeature> getReaderInternal(Query paramQuery) throws IOException;
/*      */   
/*      */   protected abstract SimpleFeatureType buildFeatureType() throws IOException;
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\store\ContentFeatureSource.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */