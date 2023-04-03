/*     */ package org.geotools.data.store;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import org.geotools.data.FeatureLocking;
/*     */ import org.geotools.data.FeatureReader;
/*     */ import org.geotools.data.FeatureWriter;
/*     */ import org.geotools.data.FilteringFeatureWriter;
/*     */ import org.geotools.data.InProcessLockingManager;
/*     */ import org.geotools.data.LockingManager;
/*     */ import org.geotools.data.Query;
/*     */ import org.geotools.data.Transaction;
/*     */ import org.geotools.data.simple.SimpleFeatureStore;
/*     */ import org.geotools.factory.Hints;
/*     */ import org.geotools.feature.FeatureCollection;
/*     */ import org.geotools.feature.FeatureIterator;
/*     */ import org.geotools.feature.NameImpl;
/*     */ import org.geotools.filter.identity.FeatureIdImpl;
/*     */ import org.opengis.feature.simple.SimpleFeature;
/*     */ import org.opengis.feature.simple.SimpleFeatureType;
/*     */ import org.opengis.feature.type.AttributeDescriptor;
/*     */ import org.opengis.feature.type.Name;
/*     */ import org.opengis.filter.Filter;
/*     */ import org.opengis.filter.identity.FeatureId;
/*     */ 
/*     */ public abstract class ContentFeatureStore extends ContentFeatureSource implements SimpleFeatureStore, FeatureLocking<SimpleFeatureType, SimpleFeature> {
/*  93 */   protected final int WRITER_ADD = 1;
/*     */   
/*  94 */   protected final int WRITER_UPDATE = 2;
/*     */   
/*     */   public ContentFeatureStore(ContentEntry entry, Query query) {
/* 103 */     super(entry, query);
/*     */   }
/*     */   
/*     */   public final FeatureWriter<SimpleFeatureType, SimpleFeature> getWriter(Filter filter) throws IOException {
/* 112 */     return getWriter(filter, 3);
/*     */   }
/*     */   
/*     */   public final FeatureWriter<SimpleFeatureType, SimpleFeature> getWriter(Filter filter, int flags) throws IOException {
/* 122 */     return getWriter(new Query(getSchema().getTypeName(), filter), flags);
/*     */   }
/*     */   
/*     */   public final FeatureWriter<SimpleFeatureType, SimpleFeature> getWriter(Query query) throws IOException {
/* 131 */     return getWriter(query, 3);
/*     */   }
/*     */   
/*     */   public final FeatureWriter<SimpleFeatureType, SimpleFeature> getWriter(Query query, int flags) throws IOException {
/*     */     FeatureWriter<SimpleFeatureType, SimpleFeature> featureWriter;
/* 141 */     query = joinQuery(query);
/* 142 */     query = resolvePropertyNames(query);
/* 146 */     if (!canTransact() && this.transaction != null && this.transaction != Transaction.AUTO_COMMIT) {
/* 147 */       DiffTransactionState state = (DiffTransactionState)getTransaction().getState(getEntry());
/* 148 */       FeatureReader<SimpleFeatureType, SimpleFeature> reader = getReader(query);
/* 149 */       featureWriter = new DiffContentFeatureWriter(this, state.getDiff(), reader);
/*     */     } else {
/*     */       FilteringFeatureWriter filteringFeatureWriter;
/* 151 */       FeatureWriter<SimpleFeatureType, SimpleFeature> writer = getWriterInternal(query, flags);
/* 154 */       if (canTransact() && !canEvent())
/* 155 */         writer = new EventContentFeatureWriter(this, writer); 
/* 158 */       if (!canFilter() && 
/* 159 */         query.getFilter() != null && query.getFilter() != Filter.INCLUDE)
/* 160 */         filteringFeatureWriter = new FilteringFeatureWriter(writer, query.getFilter()); 
/* 165 */       if (!canLock()) {
/* 166 */         LockingManager lockingManager = getDataStore().getLockingManager();
/* 167 */         featureWriter = ((InProcessLockingManager)lockingManager).checkedWriter((FeatureWriter)filteringFeatureWriter, this.transaction);
/*     */       } 
/*     */     } 
/* 173 */     return featureWriter;
/*     */   }
/*     */   
/*     */   protected abstract FeatureWriter<SimpleFeatureType, SimpleFeature> getWriterInternal(Query paramQuery, int paramInt) throws IOException;
/*     */   
/*     */   public List<FeatureId> addFeatures(Collection collection) throws IOException {
/* 215 */     List<FeatureId> ids = new LinkedList<FeatureId>();
/* 217 */     FeatureWriter<SimpleFeatureType, SimpleFeature> writer = getWriterAppend();
/*     */     try {
/* 219 */       for (Iterator<SimpleFeature> f = collection.iterator(); f.hasNext(); ) {
/* 220 */         FeatureId id = addFeature(f.next(), writer);
/* 221 */         ids.add(id);
/*     */       } 
/*     */     } finally {
/* 224 */       writer.close();
/*     */     } 
/* 227 */     return ids;
/*     */   }
/*     */   
/*     */   public List<FeatureId> addFeatures(FeatureCollection<SimpleFeatureType, SimpleFeature> featureCollection) throws IOException {
/* 240 */     List<FeatureId> ids = new LinkedList<FeatureId>();
/* 242 */     FeatureWriter<SimpleFeatureType, SimpleFeature> writer = getWriterAppend();
/* 243 */     FeatureIterator<SimpleFeature> f = featureCollection.features();
/*     */     try {
/* 245 */       while (f.hasNext()) {
/* 246 */         SimpleFeature feature = (SimpleFeature)f.next();
/* 247 */         FeatureId id = addFeature(feature, writer);
/* 248 */         ids.add(id);
/*     */       } 
/*     */     } finally {
/* 251 */       writer.close();
/* 252 */       f.close();
/*     */     } 
/* 254 */     return ids;
/*     */   }
/*     */   
/*     */   private FeatureWriter<SimpleFeatureType, SimpleFeature> getWriterAppend() throws IOException {
/* 263 */     FeatureWriter<SimpleFeatureType, SimpleFeature> writer = getWriter((Filter)Filter.INCLUDE, 1);
/* 264 */     while (writer.hasNext())
/* 265 */       writer.next(); 
/* 267 */     return writer;
/*     */   }
/*     */   
/*     */   FeatureId addFeature(SimpleFeature feature, FeatureWriter<SimpleFeatureType, SimpleFeature> writer) throws IOException {
/* 275 */     SimpleFeature toWrite = (SimpleFeature)writer.next();
/* 276 */     for (int i = 0; i < toWrite.getType().getAttributeCount(); i++) {
/* 277 */       String name = toWrite.getType().getDescriptor(i).getLocalName();
/* 278 */       toWrite.setAttribute(name, feature.getAttribute(name));
/*     */     } 
/* 282 */     if (feature.getUserData().size() > 0)
/* 283 */       toWrite.getUserData().putAll(feature.getUserData()); 
/* 287 */     boolean useExisting = Boolean.TRUE.equals(feature.getUserData().get(Hints.USE_PROVIDED_FID));
/* 288 */     if (getQueryCapabilities().isUseProvidedFIDSupported() && useExisting)
/* 289 */       ((FeatureIdImpl)toWrite.getIdentifier()).setID(feature.getID()); 
/* 293 */     writer.write();
/* 296 */     feature.getUserData().putAll(toWrite.getUserData());
/* 299 */     FeatureId id = toWrite.getIdentifier();
/* 300 */     return id;
/*     */   }
/*     */   
/*     */   public final void setFeatures(FeatureReader<SimpleFeatureType, SimpleFeature> reader) throws IOException {
/* 313 */     removeFeatures((Filter)Filter.INCLUDE);
/* 316 */     FeatureWriter<SimpleFeatureType, SimpleFeature> writer = getWriter((Filter)Filter.INCLUDE, 1);
/*     */     try {
/* 318 */       while (reader.hasNext()) {
/* 319 */         SimpleFeature feature = (SimpleFeature)reader.next();
/* 325 */         SimpleFeature toWrite = (SimpleFeature)writer.next();
/* 326 */         for (int i = 0; i < toWrite.getType().getAttributeCount(); i++) {
/* 327 */           String name = toWrite.getType().getDescriptor(i).getLocalName();
/* 328 */           toWrite.setAttribute(name, feature.getAttribute(name));
/*     */         } 
/* 332 */         writer.write();
/*     */       } 
/*     */     } finally {
/* 336 */       writer.close();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void modifyFeatures(AttributeDescriptor[] type, Object[] value, Filter filter) throws IOException {
/* 342 */     Name[] attributeNames = new Name[type.length];
/* 343 */     for (int i = 0; i < type.length; i++)
/* 344 */       attributeNames[i] = type[i].getName(); 
/* 346 */     modifyFeatures(attributeNames, value, filter);
/*     */   }
/*     */   
/*     */   public void modifyFeatures(Name[] type, Object[] value, Filter filter) throws IOException {
/* 362 */     if (filter == null) {
/* 363 */       String msg = "Must specify a filter, must not be null.";
/* 364 */       throw new IllegalArgumentException(msg);
/*     */     } 
/* 366 */     filter = resolvePropertyNames(filter);
/* 369 */     FeatureWriter<SimpleFeatureType, SimpleFeature> writer = getWriter(filter, 2);
/*     */     try {
/* 371 */       while (writer.hasNext()) {
/* 372 */         SimpleFeature toWrite = (SimpleFeature)writer.next();
/* 374 */         for (int i = 0; i < type.length; i++)
/* 375 */           toWrite.setAttribute(type[i], value[i]); 
/* 378 */         writer.write();
/*     */       } 
/*     */     } finally {
/* 383 */       writer.close();
/*     */     } 
/*     */   }
/*     */   
/*     */   public final void modifyFeatures(String name, Object attributeValue, Filter filter) throws IOException {
/* 389 */     modifyFeatures(new Name[] { (Name)new NameImpl(name) }, new Object[] { attributeValue }, filter);
/*     */   }
/*     */   
/*     */   public final void modifyFeatures(String[] names, Object[] values, Filter filter) throws IOException {
/* 393 */     Name[] attributeNames = new Name[names.length];
/* 394 */     for (int i = 0; i < names.length; i++)
/* 395 */       attributeNames[i] = (Name)new NameImpl(names[i]); 
/* 397 */     modifyFeatures(attributeNames, values, filter);
/*     */   }
/*     */   
/*     */   public final void modifyFeatures(AttributeDescriptor type, Object value, Filter filter) throws IOException {
/* 406 */     modifyFeatures(new Name[] { type.getName() }, new Object[] { value }, filter);
/*     */   }
/*     */   
/*     */   public final void modifyFeatures(Name name, Object value, Filter filter) throws IOException {
/* 415 */     modifyFeatures(new Name[] { name }, new Object[] { value }, filter);
/*     */   }
/*     */   
/*     */   public void removeFeatures(Filter filter) throws IOException {
/* 430 */     if (filter == null) {
/* 431 */       String msg = "Must specify a filter, must not be null.";
/* 432 */       throw new IllegalArgumentException(msg);
/*     */     } 
/* 434 */     filter = resolvePropertyNames(filter);
/* 437 */     FeatureWriter<SimpleFeatureType, SimpleFeature> writer = getWriter(filter, 2);
/*     */     try {
/* 440 */       while (writer.hasNext()) {
/* 441 */         writer.next();
/* 442 */         writer.remove();
/*     */       } 
/*     */     } finally {
/* 447 */       writer.close();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\store\ContentFeatureStore.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */