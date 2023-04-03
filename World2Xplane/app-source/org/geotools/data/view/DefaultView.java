/*     */ package org.geotools.data.view;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.net.URI;
/*     */ import java.net.URISyntaxException;
/*     */ import java.util.HashSet;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import java.util.logging.Logger;
/*     */ import org.geotools.data.DataAccess;
/*     */ import org.geotools.data.DataSourceException;
/*     */ import org.geotools.data.DataStore;
/*     */ import org.geotools.data.DataUtilities;
/*     */ import org.geotools.data.DefaultQuery;
/*     */ import org.geotools.data.FeatureListener;
/*     */ import org.geotools.data.Query;
/*     */ import org.geotools.data.QueryCapabilities;
/*     */ import org.geotools.data.ResourceInfo;
/*     */ import org.geotools.data.crs.ForceCoordinateSystemFeatureResults;
/*     */ import org.geotools.data.crs.ReprojectFeatureResults;
/*     */ import org.geotools.data.simple.SimpleFeatureCollection;
/*     */ import org.geotools.data.simple.SimpleFeatureSource;
/*     */ import org.geotools.factory.CommonFactoryFinder;
/*     */ import org.geotools.feature.FeatureCollection;
/*     */ import org.geotools.feature.SchemaException;
/*     */ import org.geotools.geometry.jts.ReferencedEnvelope;
/*     */ import org.geotools.util.logging.Logging;
/*     */ import org.opengis.feature.simple.SimpleFeatureType;
/*     */ import org.opengis.feature.type.FeatureType;
/*     */ import org.opengis.feature.type.Name;
/*     */ import org.opengis.filter.And;
/*     */ import org.opengis.filter.Filter;
/*     */ import org.opengis.filter.FilterFactory;
/*     */ import org.opengis.referencing.crs.CoordinateReferenceSystem;
/*     */ 
/*     */ public class DefaultView implements SimpleFeatureSource {
/*  78 */   private static final Logger LOGGER = Logging.getLogger("org.geotools.data.view");
/*     */   
/*     */   protected SimpleFeatureSource source;
/*     */   
/*     */   private SimpleFeatureType schema;
/*     */   
/*     */   private Query constraintQuery;
/*     */   
/*     */   public DefaultView(SimpleFeatureSource source, Query query) throws SchemaException {
/* 113 */     this.source = source;
/* 114 */     this.constraintQuery = query;
/* 116 */     SimpleFeatureType origionalType = (SimpleFeatureType)source.getSchema();
/* 118 */     CoordinateReferenceSystem cs = null;
/* 119 */     if (query.getCoordinateSystemReproject() != null) {
/* 120 */       cs = query.getCoordinateSystemReproject();
/* 121 */     } else if (query.getCoordinateSystem() != null) {
/* 122 */       cs = query.getCoordinateSystem();
/*     */     } 
/* 124 */     this.schema = DataUtilities.createSubType(origionalType, query.getPropertyNames(), cs, query.getTypeName(), null);
/*     */   }
/*     */   
/*     */   public Name getName() {
/* 134 */     return getSchema().getName();
/*     */   }
/*     */   
/*     */   public static SimpleFeatureSource create(SimpleFeatureSource source, Query query) throws SchemaException {
/* 158 */     if (!(source instanceof org.geotools.data.FeatureLocking))
/* 161 */       if (source instanceof org.geotools.data.FeatureStore); 
/* 165 */     return new DefaultView(source, query);
/*     */   }
/*     */   
/*     */   protected DefaultQuery makeDefinitionQuery(Query query) throws IOException {
/* 192 */     if (query == Query.ALL || query.equals(Query.ALL))
/* 193 */       return new DefaultQuery(this.constraintQuery); 
/*     */     try {
/* 197 */       String[] propNames = extractAllowedAttributes(query);
/* 199 */       String typeName = query.getTypeName();
/* 200 */       if (typeName == null)
/* 201 */         typeName = this.constraintQuery.getTypeName(); 
/* 204 */       URI namespace = query.getNamespace();
/* 205 */       if (namespace == null || namespace == Query.NO_NAMESPACE)
/* 206 */         namespace = this.constraintQuery.getNamespace(); 
/* 208 */       Filter filter = makeDefinitionFilter(query.getFilter());
/* 210 */       int maxFeatures = Math.min(query.getMaxFeatures(), this.constraintQuery.getMaxFeatures());
/* 212 */       String handle = query.getHandle();
/* 213 */       if (handle == null) {
/* 214 */         handle = this.constraintQuery.getHandle();
/* 215 */       } else if (this.constraintQuery.getHandle() != null) {
/* 216 */         handle = handle + "(" + this.constraintQuery.getHandle() + ")";
/*     */       } 
/* 219 */       DefaultQuery defaultQuery = new DefaultQuery(typeName, namespace, filter, maxFeatures, propNames, handle);
/* 220 */       defaultQuery.setSortBy(query.getSortBy());
/* 221 */       return defaultQuery;
/* 222 */     } catch (Exception ex) {
/* 223 */       throw new DataSourceException("Could not restrict the query to the definition criteria: " + ex.getMessage(), ex);
/*     */     } 
/*     */   }
/*     */   
/*     */   private String[] extractAllowedAttributes(Query query) {
/* 249 */     String[] propNames = null;
/* 251 */     if (query.retrieveAllProperties()) {
/* 252 */       propNames = new String[this.schema.getAttributeCount()];
/* 254 */       for (int i = 0; i < this.schema.getAttributeCount(); i++)
/* 255 */         propNames[i] = this.schema.getDescriptor(i).getLocalName(); 
/*     */     } else {
/* 258 */       String[] queriedAtts = query.getPropertyNames();
/* 259 */       int queriedAttCount = queriedAtts.length;
/* 260 */       List<String> allowedAtts = new LinkedList();
/* 262 */       for (int i = 0; i < queriedAttCount; i++) {
/* 263 */         if (this.schema.getDescriptor(queriedAtts[i]) != null) {
/* 264 */           allowedAtts.add(queriedAtts[i]);
/*     */         } else {
/* 266 */           LOGGER.info("queried a not allowed property: " + queriedAtts[i] + ". Ommitting it from query");
/*     */         } 
/*     */       } 
/* 271 */       propNames = allowedAtts.<String>toArray(new String[allowedAtts.size()]);
/*     */     } 
/* 274 */     return propNames;
/*     */   }
/*     */   
/*     */   protected Filter makeDefinitionFilter(Filter filter) throws DataSourceException {
/*     */     And and;
/* 292 */     Filter newFilter = filter;
/* 293 */     Filter constraintFilter = this.constraintQuery.getFilter();
/*     */     try {
/* 295 */       if (constraintFilter != Filter.INCLUDE) {
/* 296 */         FilterFactory ff = CommonFactoryFinder.getFilterFactory(null);
/* 297 */         and = ff.and(constraintFilter, filter);
/*     */       } 
/* 299 */     } catch (Exception ex) {
/* 300 */       throw new DataSourceException("Can't create the constraint filter", ex);
/*     */     } 
/* 302 */     return (Filter)and;
/*     */   }
/*     */   
/*     */   public DataStore getDataStore() {
/* 315 */     return (DataStore)this.source.getDataStore();
/*     */   }
/*     */   
/*     */   public void addFeatureListener(FeatureListener listener) {
/* 330 */     this.source.addFeatureListener(listener);
/*     */   }
/*     */   
/*     */   public void removeFeatureListener(FeatureListener listener) {
/* 345 */     this.source.removeFeatureListener(listener);
/*     */   }
/*     */   
/*     */   public SimpleFeatureCollection getFeatures(Query query) throws IOException {
/*     */     ReprojectFeatureResults reprojectFeatureResults;
/* 363 */     DefaultQuery mergedQuery = makeDefinitionQuery(query);
/* 364 */     SimpleFeatureCollection results = this.source.getFeatures((Query)mergedQuery);
/* 367 */     CoordinateReferenceSystem cCs = this.constraintQuery.getCoordinateSystem();
/* 368 */     CoordinateReferenceSystem cCsr = this.constraintQuery.getCoordinateSystemReproject();
/* 369 */     CoordinateReferenceSystem qCs = query.getCoordinateSystem();
/* 370 */     CoordinateReferenceSystem qCsr = query.getCoordinateSystemReproject();
/*     */     try {
/* 381 */       if (qCsr != null && cCsr != null) {
/*     */         ForceCoordinateSystemFeatureResults forceCoordinateSystemFeatureResults2, forceCoordinateSystemFeatureResults1;
/* 382 */         if (cCs != null)
/* 383 */           forceCoordinateSystemFeatureResults2 = new ForceCoordinateSystemFeatureResults((FeatureCollection)results, cCs); 
/* 384 */         ReprojectFeatureResults reprojectFeatureResults1 = new ReprojectFeatureResults((FeatureCollection)forceCoordinateSystemFeatureResults2, cCsr);
/* 385 */         if (qCs != null)
/* 386 */           forceCoordinateSystemFeatureResults1 = new ForceCoordinateSystemFeatureResults((FeatureCollection)reprojectFeatureResults1, qCs); 
/* 387 */         reprojectFeatureResults = new ReprojectFeatureResults((FeatureCollection)forceCoordinateSystemFeatureResults1, qCsr);
/*     */       } else {
/*     */         ForceCoordinateSystemFeatureResults forceCoordinateSystemFeatureResults;
/* 388 */         if (qCs != null && cCsr != null) {
/*     */           try {
/* 393 */             if (cCs != null)
/* 394 */               ForceCoordinateSystemFeatureResults forceCoordinateSystemFeatureResults1 = new ForceCoordinateSystemFeatureResults((FeatureCollection)reprojectFeatureResults, cCs); 
/* 395 */             reprojectFeatureResults = new ReprojectFeatureResults((FeatureCollection)this.source.getFeatures((Query)mergedQuery), cCsr);
/* 397 */             forceCoordinateSystemFeatureResults = new ForceCoordinateSystemFeatureResults((FeatureCollection)reprojectFeatureResults, qCs);
/* 398 */           } catch (SchemaException e) {
/* 399 */             throw new DataSourceException("This should not happen", e);
/*     */           } 
/*     */         } else {
/* 409 */           CoordinateReferenceSystem forcedCS = (qCs != null) ? qCs : cCs;
/* 410 */           CoordinateReferenceSystem reprojectCS = (qCsr != null) ? qCsr : cCsr;
/* 412 */           if (forcedCS != null)
/* 413 */             forceCoordinateSystemFeatureResults = new ForceCoordinateSystemFeatureResults((FeatureCollection)forceCoordinateSystemFeatureResults, forcedCS); 
/* 414 */           if (reprojectCS != null)
/* 415 */             reprojectFeatureResults = new ReprojectFeatureResults((FeatureCollection)forceCoordinateSystemFeatureResults, reprojectCS); 
/*     */         } 
/*     */       } 
/* 417 */     } catch (IOException e) {
/* 418 */       throw e;
/* 419 */     } catch (Exception e) {
/* 420 */       throw new DataSourceException("A problem occurred while handling forced coordinate systems and reprojection", e);
/*     */     } 
/* 424 */     return (SimpleFeatureCollection)reprojectFeatureResults;
/*     */   }
/*     */   
/*     */   public SimpleFeatureCollection getFeatures(Filter filter) throws IOException {
/* 440 */     return getFeatures((Query)new DefaultQuery(this.schema.getTypeName(), filter));
/*     */   }
/*     */   
/*     */   public SimpleFeatureCollection getFeatures() throws IOException {
/* 456 */     return getFeatures(Query.ALL);
/*     */   }
/*     */   
/*     */   public SimpleFeatureType getSchema() {
/* 469 */     return this.schema;
/*     */   }
/*     */   
/*     */   public ResourceInfo getInfo() {
/* 473 */     return new ResourceInfo() {
/*     */         final Set<String> words;
/*     */         
/*     */         public ReferencedEnvelope getBounds() {
/*     */           try {
/* 482 */             return DefaultView.this.getBounds();
/* 483 */           } catch (IOException e) {
/* 484 */             return null;
/*     */           } 
/*     */         }
/*     */         
/*     */         public CoordinateReferenceSystem getCRS() {
/* 488 */           return DefaultView.this.getSchema().getCoordinateReferenceSystem();
/*     */         }
/*     */         
/*     */         public String getDescription() {
/* 492 */           return null;
/*     */         }
/*     */         
/*     */         public Set<String> getKeywords() {
/* 496 */           return this.words;
/*     */         }
/*     */         
/*     */         public String getName() {
/* 500 */           return DefaultView.this.getSchema().getTypeName();
/*     */         }
/*     */         
/*     */         public URI getSchema() {
/* 504 */           Name name = DefaultView.this.getSchema().getName();
/*     */           try {
/* 507 */             URI namespace = new URI(name.getNamespaceURI());
/* 508 */             return namespace;
/* 509 */           } catch (URISyntaxException e) {
/* 510 */             return null;
/*     */           } 
/*     */         }
/*     */         
/*     */         public String getTitle() {
/* 515 */           Name name = DefaultView.this.getSchema().getName();
/* 516 */           return name.getLocalPart();
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   public ReferencedEnvelope getBounds() throws IOException {
/* 536 */     if (this.constraintQuery.getCoordinateSystemReproject() == null) {
/* 537 */       if (this.constraintQuery.getFilter() == null || this.constraintQuery.getFilter() == Filter.INCLUDE || Filter.INCLUDE.equals(this.constraintQuery.getFilter()))
/* 539 */         return this.source.getBounds(); 
/* 541 */       return this.source.getBounds(this.constraintQuery);
/*     */     } 
/* 547 */     return getFeatures().getBounds();
/*     */   }
/*     */   
/*     */   public ReferencedEnvelope getBounds(Query query) throws IOException {
/*     */     DefaultQuery defaultQuery;
/* 575 */     if (this.constraintQuery.getCoordinateSystemReproject() == null) {
/*     */       try {
/* 577 */         defaultQuery = makeDefinitionQuery(query);
/* 578 */       } catch (IOException ex) {
/* 579 */         return null;
/*     */       } 
/* 582 */       return this.source.getBounds((Query)defaultQuery);
/*     */     } 
/* 587 */     return getFeatures((Query)defaultQuery).getBounds();
/*     */   }
/*     */   
/*     */   public int getCount(Query query) {
/*     */     DefaultQuery defaultQuery;
/*     */     try {
/* 612 */       defaultQuery = makeDefinitionQuery(query);
/* 613 */     } catch (IOException ex) {
/* 614 */       return -1;
/*     */     } 
/*     */     try {
/* 617 */       return this.source.getCount((Query)defaultQuery);
/* 618 */     } catch (IOException e) {
/* 619 */       return 0;
/*     */     } 
/*     */   }
/*     */   
/*     */   public Set getSupportedHints() {
/* 624 */     return this.source.getSupportedHints();
/*     */   }
/*     */   
/*     */   public QueryCapabilities getQueryCapabilities() {
/* 629 */     return this.source.getQueryCapabilities();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\view\DefaultView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */