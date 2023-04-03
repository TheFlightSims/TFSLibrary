/*     */ package org.geotools.data;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Envelope;
/*     */ import java.io.IOException;
/*     */ import java.net.URI;
/*     */ import java.net.URISyntaxException;
/*     */ import java.util.Collections;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.Set;
/*     */ import java.util.logging.Logger;
/*     */ import org.geotools.data.simple.SimpleFeatureCollection;
/*     */ import org.geotools.data.simple.SimpleFeatureSource;
/*     */ import org.geotools.data.store.EmptyFeatureCollection;
/*     */ import org.geotools.factory.Hints;
/*     */ import org.geotools.feature.FeatureCollection;
/*     */ import org.geotools.geometry.jts.ReferencedEnvelope;
/*     */ import org.geotools.util.logging.Logging;
/*     */ import org.opengis.feature.simple.SimpleFeatureType;
/*     */ import org.opengis.feature.type.Name;
/*     */ import org.opengis.filter.Filter;
/*     */ import org.opengis.referencing.crs.CoordinateReferenceSystem;
/*     */ 
/*     */ public abstract class AbstractFeatureSource implements SimpleFeatureSource {
/*  82 */   private static final Logger LOGGER = Logging.getLogger("org.geotools.data");
/*     */   
/*  84 */   protected Set hints = Collections.EMPTY_SET;
/*     */   
/*     */   protected QueryCapabilities queryCapabilities;
/*     */   
/*     */   public AbstractFeatureSource() {
/*  90 */     this.queryCapabilities = new QueryCapabilities();
/*     */   }
/*     */   
/*     */   public Name getName() {
/* 107 */     return ((SimpleFeatureType)getSchema()).getName();
/*     */   }
/*     */   
/*     */   public AbstractFeatureSource(Set<?> hints) {
/* 115 */     this.hints = Collections.unmodifiableSet(new HashSet(hints));
/* 116 */     this.queryCapabilities = new QueryCapabilities() {
/*     */         public boolean isUseProvidedFIDSupported() {
/* 119 */           return AbstractFeatureSource.this.hints.contains(Hints.USE_PROVIDED_FID);
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   public ResourceInfo getInfo() {
/* 125 */     return new ResourceInfo() {
/*     */         final Set<String> words;
/*     */         
/*     */         public ReferencedEnvelope getBounds() {
/*     */           try {
/* 133 */             return AbstractFeatureSource.this.getBounds();
/* 134 */           } catch (IOException e) {
/* 135 */             return null;
/*     */           } 
/*     */         }
/*     */         
/*     */         public CoordinateReferenceSystem getCRS() {
/* 139 */           return ((SimpleFeatureType)AbstractFeatureSource.this.getSchema()).getCoordinateReferenceSystem();
/*     */         }
/*     */         
/*     */         public String getDescription() {
/* 143 */           return null;
/*     */         }
/*     */         
/*     */         public Set<String> getKeywords() {
/* 147 */           return this.words;
/*     */         }
/*     */         
/*     */         public String getName() {
/* 151 */           return ((SimpleFeatureType)AbstractFeatureSource.this.getSchema()).getTypeName();
/*     */         }
/*     */         
/*     */         public URI getSchema() {
/* 155 */           Name name = ((SimpleFeatureType)AbstractFeatureSource.this.getSchema()).getName();
/*     */           try {
/* 158 */             URI namespace = new URI(name.getNamespaceURI());
/* 159 */             return namespace;
/* 160 */           } catch (URISyntaxException e) {
/* 161 */             return null;
/*     */           } 
/*     */         }
/*     */         
/*     */         public String getTitle() {
/* 166 */           Name name = ((SimpleFeatureType)AbstractFeatureSource.this.getSchema()).getName();
/* 167 */           return name.getLocalPart();
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   public QueryCapabilities getQueryCapabilities() {
/* 174 */     return this.queryCapabilities;
/*     */   }
/*     */   
/*     */   public Transaction getTransaction() {
/* 187 */     return Transaction.AUTO_COMMIT;
/*     */   }
/*     */   
/*     */   public SimpleFeatureCollection getFeatures(Query query) throws IOException {
/* 203 */     SimpleFeatureType schema = (SimpleFeatureType)getSchema();
/* 204 */     String typeName = schema.getTypeName();
/* 206 */     if (query.getTypeName() == null) {
/* 207 */       DefaultQuery defaultQuery = new DefaultQuery(query);
/* 208 */       defaultQuery.setTypeName(typeName);
/* 210 */     } else if (!typeName.equals(query.getTypeName())) {
/* 211 */       return (SimpleFeatureCollection)new EmptyFeatureCollection(schema);
/*     */     } 
/* 214 */     QueryCapabilities queryCapabilities = getQueryCapabilities();
/* 215 */     if (!queryCapabilities.supportsSorting(query.getSortBy()))
/* 216 */       throw new DataSourceException("DataStore cannot provide the requested sort order"); 
/* 219 */     DefaultFeatureResults defaultFeatureResults = new DefaultFeatureResults(this, query);
/* 220 */     if (((SimpleFeatureType)defaultFeatureResults.getSchema()).getGeometryDescriptor() == null)
/* 221 */       return (SimpleFeatureCollection)defaultFeatureResults; 
/* 242 */     return (SimpleFeatureCollection)defaultFeatureResults;
/*     */   }
/*     */   
/*     */   public SimpleFeatureCollection getFeatures(Filter filter) throws IOException {
/* 255 */     return getFeatures(new DefaultQuery(((SimpleFeatureType)getSchema()).getTypeName(), filter));
/*     */   }
/*     */   
/*     */   public SimpleFeatureCollection getFeatures() throws IOException {
/* 266 */     return getFeatures((Filter)Filter.INCLUDE);
/*     */   }
/*     */   
/*     */   public ReferencedEnvelope getBounds() throws IOException {
/* 286 */     return getBounds((getSchema() == null) ? Query.ALL : new DefaultQuery(((SimpleFeatureType)getSchema()).getTypeName()));
/*     */   }
/*     */   
/*     */   public ReferencedEnvelope getBounds(Query query) throws IOException {
/* 307 */     if (query.getFilter() == Filter.EXCLUDE)
/* 308 */       return new ReferencedEnvelope(new Envelope(), ((SimpleFeatureType)getSchema()).getCoordinateReferenceSystem()); 
/* 311 */     DataStore dataStore = getDataStore();
/* 313 */     if (dataStore == null || !(dataStore instanceof AbstractDataStore))
/* 315 */       return null; 
/* 318 */     return ((AbstractDataStore)dataStore).getBounds(namedQuery(query));
/*     */   }
/*     */   
/*     */   protected Query namedQuery(Query query) {
/* 332 */     String typeName = ((SimpleFeatureType)getSchema()).getTypeName();
/* 333 */     if (query.getTypeName() == null || !query.getTypeName().equals(typeName))
/* 336 */       return new DefaultQuery(typeName, query.getFilter(), query.getMaxFeatures(), query.getPropertyNames(), query.getHandle()); 
/* 344 */     return query;
/*     */   }
/*     */   
/*     */   public int getCount(Query query) throws IOException {
/* 363 */     if (query.getFilter() == Filter.EXCLUDE)
/* 364 */       return 0; 
/* 367 */     DataStore dataStore = getDataStore();
/* 368 */     if (dataStore == null || !(dataStore instanceof AbstractDataStore))
/* 370 */       return -1; 
/* 373 */     Transaction t = getTransaction();
/* 375 */     int nativeCount = ((AbstractDataStore)dataStore).getCount(namedQuery(query));
/* 376 */     if (nativeCount == -1)
/* 377 */       return -1; 
/* 380 */     int delta = 0;
/* 381 */     if (t != Transaction.AUTO_COMMIT) {
/* 382 */       if (t.getState(dataStore) == null)
/* 383 */         return nativeCount; 
/* 385 */       if (!(t.getState(dataStore) instanceof TransactionStateDiff))
/* 387 */         return -1; 
/* 389 */       Diff diff = ((AbstractDataStore)dataStore).state(t).diff(namedQuery(query).getTypeName());
/* 390 */       synchronized (diff) {
/* 391 */         Iterator it = diff.getAdded().values().iterator();
/* 392 */         while (it.hasNext()) {
/* 393 */           Object feature = it.next();
/* 394 */           if (query.getFilter().evaluate(feature))
/* 395 */             delta++; 
/*     */         } 
/* 398 */         it = diff.getModified().values().iterator();
/* 399 */         while (it.hasNext()) {
/* 400 */           Object feature = it.next();
/* 402 */           if (feature == TransactionStateDiff.NULL && query.getFilter().evaluate(feature))
/* 403 */             delta--; 
/*     */         } 
/*     */       } 
/*     */     } 
/* 409 */     return nativeCount + delta;
/*     */   }
/*     */   
/*     */   public Set getSupportedHints() {
/* 416 */     return this.hints;
/*     */   }
/*     */   
/*     */   public abstract DataStore getDataStore();
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\AbstractFeatureSource.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */