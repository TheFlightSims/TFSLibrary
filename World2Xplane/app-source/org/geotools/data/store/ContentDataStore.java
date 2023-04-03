/*     */ package org.geotools.data.store;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.GeometryFactory;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import java.util.logging.Logger;
/*     */ import org.geotools.data.DataStore;
/*     */ import org.geotools.data.DataStoreFactorySpi;
/*     */ import org.geotools.data.DefaultServiceInfo;
/*     */ import org.geotools.data.FeatureReader;
/*     */ import org.geotools.data.FeatureSource;
/*     */ import org.geotools.data.FeatureWriter;
/*     */ import org.geotools.data.InProcessLockingManager;
/*     */ import org.geotools.data.LockingManager;
/*     */ import org.geotools.data.Query;
/*     */ import org.geotools.data.ServiceInfo;
/*     */ import org.geotools.data.Transaction;
/*     */ import org.geotools.data.simple.SimpleFeatureSource;
/*     */ import org.geotools.feature.FeatureTypes;
/*     */ import org.geotools.feature.NameImpl;
/*     */ import org.geotools.util.logging.Logging;
/*     */ import org.opengis.feature.FeatureFactory;
/*     */ import org.opengis.feature.simple.SimpleFeature;
/*     */ import org.opengis.feature.simple.SimpleFeatureType;
/*     */ import org.opengis.feature.type.FeatureType;
/*     */ import org.opengis.feature.type.FeatureTypeFactory;
/*     */ import org.opengis.feature.type.Name;
/*     */ import org.opengis.filter.Filter;
/*     */ import org.opengis.filter.FilterFactory;
/*     */ 
/*     */ public abstract class ContentDataStore implements DataStore {
/*     */   protected static final int WRITER_ADD = 1;
/*     */   
/*     */   protected static final int WRITER_UPDATE = 2;
/*     */   
/*     */   protected final Map<Name, ContentEntry> entries;
/*     */   
/*     */   protected final Logger LOGGER;
/*     */   
/*     */   protected FeatureTypeFactory typeFactory;
/*     */   
/*     */   protected FeatureFactory featureFactory;
/*     */   
/*     */   protected FilterFactory filterFactory;
/*     */   
/*     */   protected GeometryFactory geometryFactory;
/*     */   
/*     */   protected String namespaceURI;
/*     */   
/* 150 */   protected LockingManager lockingManager = (LockingManager)new InProcessLockingManager();
/*     */   
/*     */   protected DataStoreFactorySpi dataStoreFactory;
/*     */   
/*     */   public ContentDataStore() {
/* 160 */     this.entries = new ConcurrentHashMap<Name, ContentEntry>();
/* 163 */     this.LOGGER = Logging.getLogger(getClass().getPackage().getName());
/*     */   }
/*     */   
/*     */   public FeatureTypeFactory getFeatureTypeFactory() {
/* 176 */     return this.typeFactory;
/*     */   }
/*     */   
/*     */   public void setFeatureTypeFactory(FeatureTypeFactory typeFactory) {
/* 183 */     this.typeFactory = typeFactory;
/*     */   }
/*     */   
/*     */   public void setFeatureFactory(FeatureFactory featureFactory) {
/* 191 */     this.featureFactory = featureFactory;
/*     */   }
/*     */   
/*     */   public FilterFactory getFilterFactory() {
/* 198 */     return this.filterFactory;
/*     */   }
/*     */   
/*     */   public FeatureFactory getFeatureFactory() {
/* 205 */     return this.featureFactory;
/*     */   }
/*     */   
/*     */   public void setFilterFactory(FilterFactory filterFactory) {
/* 212 */     this.filterFactory = filterFactory;
/*     */   }
/*     */   
/*     */   public GeometryFactory getGeometryFactory() {
/* 220 */     return this.geometryFactory;
/*     */   }
/*     */   
/*     */   public void setGeometryFactory(GeometryFactory geometryFactory) {
/* 228 */     this.geometryFactory = geometryFactory;
/*     */   }
/*     */   
/*     */   public DataStoreFactorySpi getDataStoreFactory() {
/* 237 */     return this.dataStoreFactory;
/*     */   }
/*     */   
/*     */   public void setDataStoreFactory(DataStoreFactorySpi dataStoreFactory) {
/* 249 */     this.dataStoreFactory = dataStoreFactory;
/*     */   }
/*     */   
/*     */   public String getNamespaceURI() {
/* 258 */     return this.namespaceURI;
/*     */   }
/*     */   
/*     */   public void setNamespaceURI(String namespaceURI) {
/* 269 */     this.namespaceURI = namespaceURI;
/*     */   }
/*     */   
/*     */   public Logger getLogger() {
/* 276 */     return this.LOGGER;
/*     */   }
/*     */   
/*     */   public ServiceInfo getInfo() {
/* 284 */     DefaultServiceInfo info = new DefaultServiceInfo();
/* 285 */     info.setDescription("Features from " + getClass().getSimpleName());
/* 286 */     info.setSchema(FeatureTypes.DEFAULT_NAMESPACE);
/* 287 */     return (ServiceInfo)info;
/*     */   }
/*     */   
/*     */   public final String[] getTypeNames() throws IOException {
/* 300 */     List<Name> typeNames = createTypeNames();
/* 301 */     String[] names = new String[typeNames.size()];
/* 303 */     for (int i = 0; i < typeNames.size(); i++) {
/* 304 */       Name typeName = typeNames.get(i);
/* 305 */       names[i] = typeName.getLocalPart();
/*     */     } 
/* 308 */     return names;
/*     */   }
/*     */   
/*     */   public void createSchema(SimpleFeatureType featureType) throws IOException {
/* 323 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public final SimpleFeatureType getSchema(String typeName) throws IOException {
/* 336 */     ContentFeatureSource featureSource = getFeatureSource(typeName);
/* 337 */     return featureSource.getSchema();
/*     */   }
/*     */   
/*     */   public ContentFeatureSource getFeatureSource(String typeName) throws IOException {
/* 352 */     return getFeatureSource((Name)new NameImpl(this.namespaceURI, typeName), Transaction.AUTO_COMMIT);
/*     */   }
/*     */   
/*     */   public ContentFeatureSource getFeatureSource(String typeName, Transaction tx) throws IOException {
/* 368 */     return getFeatureSource(name(typeName), tx);
/*     */   }
/*     */   
/*     */   public ContentFeatureSource getFeatureSource(Name typeName, Transaction tx) throws IOException {
/* 385 */     ContentEntry entry = ensureEntry(typeName);
/* 387 */     ContentFeatureSource featureSource = createFeatureSource(entry);
/* 388 */     featureSource.setTransaction(tx);
/* 399 */     return featureSource;
/*     */   }
/*     */   
/*     */   public FeatureReader<SimpleFeatureType, SimpleFeature> getFeatureReader(Query query, Transaction tx) throws IOException {
/* 413 */     if (query.getTypeName() == null)
/* 414 */       throw new IllegalArgumentException("Query does not specify type."); 
/* 417 */     return getFeatureSource(query.getTypeName(), tx).getReader(query);
/*     */   }
/*     */   
/*     */   public FeatureWriter<SimpleFeatureType, SimpleFeature> getFeatureWriter(String typeName, Filter filter, Transaction tx) throws IOException {
/* 431 */     ContentFeatureStore featureStore = ensureFeatureStore(typeName, tx);
/* 432 */     return featureStore.getWriter(filter, 3);
/*     */   }
/*     */   
/*     */   protected final ContentFeatureStore ensureFeatureStore(String typeName, Transaction tx) throws IOException {
/* 449 */     ContentFeatureSource featureSource = getFeatureSource(typeName, tx);
/* 450 */     if (!(featureSource instanceof ContentFeatureStore))
/* 451 */       throw new IOException(typeName + " is read only"); 
/* 454 */     return (ContentFeatureStore)featureSource;
/*     */   }
/*     */   
/*     */   public final FeatureWriter<SimpleFeatureType, SimpleFeature> getFeatureWriter(String typeName, Transaction tx) throws IOException {
/* 466 */     return getFeatureWriter(typeName, (Filter)Filter.INCLUDE, tx);
/*     */   }
/*     */   
/*     */   public final FeatureWriter<SimpleFeatureType, SimpleFeature> getFeatureWriterAppend(String typeName, Transaction tx) throws IOException {
/* 481 */     ContentFeatureStore featureStore = ensureFeatureStore(typeName, tx);
/* 482 */     FeatureWriter<SimpleFeatureType, SimpleFeature> writer = featureStore.getWriter((Filter)Filter.INCLUDE, 1);
/* 485 */     while (writer.hasNext())
/* 486 */       writer.next(); 
/* 488 */     return writer;
/*     */   }
/*     */   
/*     */   public final LockingManager getLockingManager() {
/* 492 */     return this.lockingManager;
/*     */   }
/*     */   
/*     */   public final void updateSchema(String typeName, SimpleFeatureType featureType) throws IOException {
/* 497 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void dispose() {
/* 501 */     for (ContentEntry entry : this.entries.values())
/* 502 */       entry.dispose(); 
/*     */   }
/*     */   
/*     */   public ContentEntry getEntry(Name name) {
/* 511 */     return this.entries.get(name);
/*     */   }
/*     */   
/*     */   protected ContentState createContentState(ContentEntry entry) {
/* 530 */     return new ContentState(entry);
/*     */   }
/*     */   
/*     */   protected final Name name(String typeName) {
/* 537 */     return (Name)new NameImpl(this.namespaceURI, typeName);
/*     */   }
/*     */   
/*     */   protected final ContentEntry entry(Name name) throws IOException {
/* 557 */     ContentEntry entry = null;
/* 560 */     if (!this.entries.containsKey(name)) {
/* 562 */       List<Name> typeNames = createTypeNames();
/* 564 */       if (typeNames.contains(name)) {
/* 566 */         synchronized (this) {
/* 567 */           if (!this.entries.containsKey(name)) {
/* 568 */             entry = new ContentEntry(this, name);
/* 569 */             this.entries.put(name, entry);
/*     */           } 
/*     */         } 
/* 573 */         entry = this.entries.get(name);
/*     */       } 
/*     */     } 
/* 577 */     return this.entries.get(name);
/*     */   }
/*     */   
/*     */   protected final ContentEntry ensureEntry(Name name) throws IOException {
/* 593 */     ContentEntry entry = entry(name);
/* 595 */     if (entry == null)
/* 596 */       throw new IOException("Schema '" + name + "' does not exist."); 
/* 599 */     return entry;
/*     */   }
/*     */   
/*     */   protected final void removeEntry(Name name) {
/* 608 */     if (this.entries.containsKey(name))
/* 609 */       this.entries.remove(name); 
/*     */   }
/*     */   
/*     */   public SimpleFeatureSource getFeatureSource(Name typeName) throws IOException {
/* 663 */     return getFeatureSource(typeName.getLocalPart());
/*     */   }
/*     */   
/*     */   public List<Name> getNames() throws IOException {
/* 674 */     String[] typeNames = getTypeNames();
/* 675 */     List<Name> names = new ArrayList<Name>(typeNames.length);
/* 676 */     for (String typeName : typeNames)
/* 677 */       names.add(new NameImpl(typeName)); 
/* 679 */     return names;
/*     */   }
/*     */   
/*     */   public SimpleFeatureType getSchema(Name name) throws IOException {
/* 689 */     return getSchema(name.getLocalPart());
/*     */   }
/*     */   
/*     */   public void updateSchema(Name typeName, SimpleFeatureType featureType) throws IOException {
/* 700 */     updateSchema(typeName.getLocalPart(), featureType);
/*     */   }
/*     */   
/*     */   public void removeSchema(Name typeName) throws IOException {
/* 707 */     throw new UnsupportedOperationException("Schema removal not supported");
/*     */   }
/*     */   
/*     */   public void removeSchema(String typeName) throws IOException {
/* 714 */     throw new UnsupportedOperationException("Schema removal not supported");
/*     */   }
/*     */   
/*     */   protected abstract List<Name> createTypeNames() throws IOException;
/*     */   
/*     */   protected abstract ContentFeatureSource createFeatureSource(ContentEntry paramContentEntry) throws IOException;
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\store\ContentDataStore.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */