/*     */ package org.geotools.data;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Envelope;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.index.strtree.STRtree;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import org.geotools.data.simple.SimpleFeatureCollection;
/*     */ import org.geotools.data.simple.SimpleFeatureSource;
/*     */ import org.geotools.data.store.EmptyFeatureCollection;
/*     */ import org.geotools.data.store.FilteringIterator;
/*     */ import org.geotools.data.store.ReTypingIterator;
/*     */ import org.geotools.factory.CommonFactoryFinder;
/*     */ import org.geotools.factory.Hints;
/*     */ import org.geotools.feature.FeatureCollection;
/*     */ import org.geotools.feature.FeatureIterator;
/*     */ import org.geotools.feature.collection.AbstractFeatureCollection;
/*     */ import org.geotools.feature.simple.SimpleFeatureBuilder;
/*     */ import org.geotools.feature.simple.SimpleFeatureTypeBuilder;
/*     */ import org.geotools.geometry.jts.ReferencedEnvelope;
/*     */ import org.opengis.feature.Feature;
/*     */ import org.opengis.feature.IllegalAttributeException;
/*     */ import org.opengis.feature.simple.SimpleFeature;
/*     */ import org.opengis.feature.simple.SimpleFeatureType;
/*     */ import org.opengis.feature.type.AttributeDescriptor;
/*     */ import org.opengis.feature.type.FeatureType;
/*     */ import org.opengis.feature.type.Name;
/*     */ import org.opengis.filter.And;
/*     */ import org.opengis.filter.Filter;
/*     */ import org.opengis.filter.FilterFactory;
/*     */ import org.opengis.filter.expression.Expression;
/*     */ import org.opengis.filter.expression.Literal;
/*     */ import org.opengis.filter.spatial.BBOX;
/*     */ import org.opengis.filter.spatial.BinarySpatialOperator;
/*     */ import org.opengis.filter.spatial.Contains;
/*     */ import org.opengis.filter.spatial.Crosses;
/*     */ import org.opengis.filter.spatial.DWithin;
/*     */ import org.opengis.filter.spatial.Equals;
/*     */ import org.opengis.filter.spatial.Intersects;
/*     */ import org.opengis.filter.spatial.Overlaps;
/*     */ import org.opengis.filter.spatial.Touches;
/*     */ import org.opengis.filter.spatial.Within;
/*     */ 
/*     */ public class CachingFeatureSource implements SimpleFeatureSource {
/*     */   private SimpleFeatureSource wrapped;
/*     */   
/*     */   private STRtree index;
/*     */   
/*     */   private boolean dirty;
/*     */   
/*     */   private Query cachedQuery;
/*     */   
/*     */   private Envelope cachedBounds;
/*     */   
/*     */   private SimpleFeatureType cachedSchema;
/*     */   
/*     */   private Envelope originalBounds;
/*     */   
/*  92 */   private static FilterFactory ff = CommonFactoryFinder.getFilterFactory(null);
/*     */   
/*  94 */   private static final Set<Class> supportedFilterTypes = (Set)new HashSet<Class<?>>(Arrays.asList(new Class[] { BBOX.class, Contains.class, Crosses.class, DWithin.class, Equals.class, Intersects.class, Overlaps.class, Touches.class, Within.class }));
/*     */   
/*     */   public CachingFeatureSource(FeatureSource original) throws IOException {
/*  99 */     this(DataUtilities.simple(original));
/*     */   }
/*     */   
/*     */   public CachingFeatureSource(SimpleFeatureSource original) throws IOException {
/* 103 */     this.wrapped = original;
/* 104 */     this.originalBounds = (Envelope)original.getBounds();
/* 105 */     if (this.originalBounds == null)
/* 106 */       this.originalBounds = new Envelope(-1.7976931348623157E308D, Double.MAX_VALUE, -1.7976931348623157E308D, Double.MAX_VALUE); 
/*     */   }
/*     */   
/*     */   private void fillCache(Query query) throws IOException {
/* 111 */     Query cloned = new DefaultQuery(query);
/* 112 */     cloned.getHints().remove(Hints.GEOMETRY_DISTANCE);
/* 114 */     SimpleFeatureCollection simpleFeatureCollection = this.wrapped.getFeatures(cloned);
/* 115 */     FeatureIterator fi = simpleFeatureCollection.features();
/* 116 */     this.index = null;
/* 117 */     STRtree newIndex = new STRtree();
/* 118 */     while (fi.hasNext()) {
/* 120 */       Feature f = fi.next();
/* 121 */       newIndex.insert((Envelope)ReferencedEnvelope.reference(f.getBounds()), f);
/*     */     } 
/* 123 */     fi.close();
/* 124 */     this.index = newIndex;
/* 125 */     this.cachedQuery = query;
/* 126 */     this.cachedSchema = (SimpleFeatureType)simpleFeatureCollection.getSchema();
/* 127 */     this.cachedBounds = getEnvelope(query.getFilter());
/* 128 */     this.dirty = false;
/*     */   }
/*     */   
/*     */   public void addFeatureListener(FeatureListener listener) {
/* 132 */     this.wrapped.addFeatureListener(listener);
/*     */   }
/*     */   
/*     */   public void removeFeatureListener(FeatureListener listener) {
/* 136 */     this.wrapped.removeFeatureListener(listener);
/*     */   }
/*     */   
/*     */   public DataStore getDataStore() {
/* 140 */     return (DataStore)this.wrapped.getDataStore();
/*     */   }
/*     */   
/*     */   public ReferencedEnvelope getBounds() throws IOException {
/* 144 */     return this.wrapped.getBounds();
/*     */   }
/*     */   
/*     */   public ReferencedEnvelope getBounds(Query query) throws IOException {
/* 148 */     return this.wrapped.getBounds(query);
/*     */   }
/*     */   
/*     */   public int getCount(Query query) throws IOException {
/* 152 */     return this.wrapped.getCount(query);
/*     */   }
/*     */   
/*     */   public SimpleFeatureType getSchema() {
/* 156 */     return (SimpleFeatureType)this.wrapped.getSchema();
/*     */   }
/*     */   
/*     */   public SimpleFeatureCollection getFeatures() throws IOException {
/* 160 */     return getFeatures((Filter)Filter.INCLUDE);
/*     */   }
/*     */   
/*     */   public SimpleFeatureCollection getFeatures(Filter filter) throws IOException {
/* 164 */     return getFeatures(new DefaultQuery(((SimpleFeatureType)this.wrapped.getSchema()).getName().getLocalPart(), filter));
/*     */   }
/*     */   
/*     */   public SimpleFeatureCollection getFeatures(Query query) throws IOException {
/* 168 */     String schemaName = ((SimpleFeatureType)this.wrapped.getSchema()).getName().getLocalPart();
/* 169 */     if (query.getTypeName() != null && !schemaName.equals(query.getTypeName()))
/* 170 */       throw new DataSourceException("Typename mismatch, query asks for '" + query.getTypeName() + " but this feature source provides '" + schemaName + "'"); 
/* 175 */     return getFeatureCollection(query, getEnvelope(query.getFilter()));
/*     */   }
/*     */   
/*     */   private SimpleFeatureCollection getFeatureCollection(Query query, Envelope bounds) throws IOException {
/*     */     try {
/* 180 */       SimpleFeatureType base = (SimpleFeatureType)this.wrapped.getSchema();
/* 181 */       SimpleFeatureType alternate = base;
/* 182 */       if (query.getPropertyNames() != Query.ALL_NAMES) {
/* 183 */         alternate = SimpleFeatureTypeBuilder.retype(base, query.getPropertyNames());
/* 184 */         if (alternate.equals(base))
/* 185 */           alternate = base; 
/*     */       } 
/* 188 */       return (SimpleFeatureCollection)new CachingFeatureCollection(bounds, base, alternate, query);
/* 189 */     } catch (Exception e) {
/* 190 */       throw new DataSourceException("Error occurred extracting features from the spatial index", e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static SimpleFeature reType(SimpleFeatureType featureType, SimpleFeature feature) throws IllegalAttributeException {
/* 205 */     SimpleFeatureType simpleFeatureType = feature.getFeatureType();
/* 207 */     if (featureType.equals(simpleFeatureType))
/* 208 */       return SimpleFeatureBuilder.copy(feature); 
/* 211 */     String id = feature.getID();
/* 212 */     int numAtts = featureType.getAttributeCount();
/* 213 */     Object[] attributes = new Object[numAtts];
/* 216 */     for (int i = 0; i < numAtts; i++) {
/* 217 */       AttributeDescriptor curAttType = featureType.getDescriptor(i);
/* 218 */       attributes[i] = feature.getAttribute(curAttType.getLocalName());
/*     */     } 
/* 221 */     return SimpleFeatureBuilder.build(featureType, attributes, id);
/*     */   }
/*     */   
/*     */   boolean isSubQuery(Query query) {
/* 226 */     if (this.cachedQuery == null)
/* 227 */       return false; 
/* 230 */     String[] cachedPropNames = this.cachedQuery.getPropertyNames();
/* 231 */     String[] propNames = query.getPropertyNames();
/* 232 */     if (cachedPropNames != Query.ALL_NAMES && (propNames == Query.ALL_NAMES || !Arrays.<String>asList(cachedPropNames).containsAll(Arrays.asList((Object[])propNames))))
/* 235 */       return false; 
/* 237 */     Filter[] filters = splitFilters(query);
/* 238 */     Filter[] cachedFilters = splitFilters(this.cachedQuery);
/* 239 */     if (!filters[0].equals(cachedFilters[0]))
/* 240 */       return false; 
/* 242 */     Envelope envelope = getEnvelope(filters[1]);
/* 243 */     return this.cachedBounds.contains(envelope);
/*     */   }
/*     */   
/*     */   Envelope getEnvelope(Filter filter) {
/* 247 */     Envelope result = this.originalBounds;
/* 248 */     if (filter instanceof And) {
/* 249 */       Envelope bounds = new Envelope();
/* 250 */       for (Iterator<Filter> iter = ((And)filter).getChildren().iterator(); iter.hasNext(); ) {
/* 251 */         Filter f = iter.next();
/* 252 */         Envelope e = getEnvelope(f);
/* 253 */         if (e == null)
/* 254 */           return null; 
/* 256 */         bounds.expandToInclude(e);
/*     */       } 
/* 258 */       result = bounds;
/* 259 */     } else if (filter instanceof BinarySpatialOperator) {
/* 260 */       BinarySpatialOperator gf = (BinarySpatialOperator)filter;
/* 261 */       if (supportedFilterTypes.contains(gf.getClass())) {
/* 262 */         Expression lg = gf.getExpression1();
/* 263 */         Expression rg = gf.getExpression2();
/* 264 */         if (lg instanceof Literal) {
/* 265 */           Geometry g = (Geometry)((Literal)lg).getValue();
/* 266 */           if (rg instanceof org.opengis.filter.expression.PropertyName)
/* 267 */             result = g.getEnvelopeInternal(); 
/* 268 */         } else if (rg instanceof Literal) {
/* 269 */           Geometry g = (Geometry)((Literal)rg).getValue();
/* 270 */           if (lg instanceof org.opengis.filter.expression.PropertyName)
/* 271 */             result = g.getEnvelopeInternal(); 
/*     */         } 
/*     */       } 
/*     */     } 
/* 275 */     return result.intersection(this.originalBounds);
/*     */   }
/*     */   
/*     */   Filter[] splitFilters(Query query) {
/* 286 */     Filter filter = query.getFilter();
/* 287 */     if (filter == null || filter.equals(Filter.EXCLUDE))
/* 288 */       return new Filter[] { (Filter)Filter.EXCLUDE, (Filter)bboxFilter(this.originalBounds) }; 
/* 291 */     if (!(filter instanceof And)) {
/* 292 */       Envelope envelope = getEnvelope(filter);
/* 293 */       if (envelope == null)
/* 294 */         return new Filter[] { (Filter)Filter.EXCLUDE, (Filter)bboxFilter(this.originalBounds) }; 
/* 296 */       return new Filter[] { (Filter)Filter.EXCLUDE, (Filter)bboxFilter(envelope) };
/*     */     } 
/* 299 */     And and = (And)filter;
/* 300 */     List<Filter> residuals = new ArrayList();
/* 301 */     List<Filter> bboxBacked = new ArrayList();
/* 302 */     for (Iterator<Filter> it = and.getChildren().iterator(); it.hasNext(); ) {
/* 303 */       Filter child = it.next();
/* 304 */       if (getEnvelope(child) != null) {
/* 305 */         bboxBacked.add(child);
/*     */         continue;
/*     */       } 
/* 307 */       residuals.add(child);
/*     */     } 
/* 311 */     return new Filter[] { (Filter)ff.and(residuals), (Filter)ff.and(bboxBacked) };
/*     */   }
/*     */   
/*     */   private BBOX bboxFilter(Envelope bbox) {
/* 315 */     return ff.bbox(((SimpleFeatureType)this.wrapped.getSchema()).getGeometryDescriptor().getLocalName(), bbox.getMinX(), bbox.getMinY(), bbox.getMaxX(), bbox.getMaxY(), null);
/*     */   }
/*     */   
/*     */   public ResourceInfo getInfo() {
/* 320 */     return this.wrapped.getInfo();
/*     */   }
/*     */   
/*     */   public Name getName() {
/* 324 */     return this.wrapped.getName();
/*     */   }
/*     */   
/*     */   public QueryCapabilities getQueryCapabilities() {
/* 328 */     return this.wrapped.getQueryCapabilities();
/*     */   }
/*     */   
/*     */   public Set getSupportedHints() {
/* 332 */     HashSet hints = new HashSet(this.wrapped.getSupportedHints());
/* 333 */     hints.remove(Hints.FEATURE_DETACHED);
/* 334 */     return hints;
/*     */   }
/*     */   
/*     */   final class CachingFeatureCollection extends AbstractFeatureCollection {
/*     */     private SimpleFeatureType sourceSchema;
/*     */     
/*     */     private SimpleFeatureType targetSchema;
/*     */     
/*     */     private Query query;
/*     */     
/* 347 */     private ReferencedEnvelope queryBounds = null;
/*     */     
/*     */     protected CachingFeatureCollection(Envelope queryBounds, SimpleFeatureType sourceSchema, SimpleFeatureType targetSchema, Query query) {
/* 351 */       super(targetSchema);
/* 352 */       this.sourceSchema = sourceSchema;
/* 353 */       this.targetSchema = targetSchema;
/* 354 */       this.query = query;
/*     */     }
/*     */     
/*     */     public int size() {
/*     */       try {
/* 360 */         return CachingFeatureSource.this.getCount(this.query);
/* 361 */       } catch (Exception e) {
/* 362 */         throw new RuntimeException("Failed to count features", e);
/*     */       } 
/*     */     }
/*     */     
/*     */     public synchronized ReferencedEnvelope getBounds() {
/*     */       try {
/* 368 */         return CachingFeatureSource.this.getBounds(this.query);
/* 369 */       } catch (Exception e) {
/* 370 */         throw new RuntimeException("Failed to count features", e);
/*     */       } 
/*     */     }
/*     */     
/*     */     protected Iterator openIterator() {
/*     */       List features;
/*     */       FilteringIterator filteringIterator;
/*     */       ReTypingIterator reTypingIterator;
/* 378 */       synchronized (CachingFeatureSource.this) {
/*     */         try {
/* 380 */           if (CachingFeatureSource.this.index == null || CachingFeatureSource.this.dirty || !CachingFeatureSource.this.isSubQuery(this.query))
/* 381 */             CachingFeatureSource.this.fillCache(this.query); 
/* 383 */           if (this.queryBounds != null) {
/* 384 */             features = CachingFeatureSource.this.index.query((Envelope)this.queryBounds);
/*     */           } else {
/* 386 */             features = CachingFeatureSource.this.index.query((Envelope)CachingFeatureSource.this.index.getRoot().getBounds());
/*     */           } 
/* 388 */         } catch (Exception e) {
/* 389 */           throw new RuntimeException("Failed to get data", e);
/*     */         } 
/*     */       } 
/* 392 */       Iterator it = features.iterator();
/* 393 */       if (this.query.getFilter() != null && Filter.INCLUDE.equals(this.query.getFilter()))
/* 394 */         filteringIterator = new FilteringIterator(it, this.query.getFilter()); 
/* 396 */       if (this.targetSchema != this.sourceSchema)
/* 397 */         reTypingIterator = new ReTypingIterator((Iterator)filteringIterator, this.sourceSchema, this.targetSchema); 
/* 399 */       return (Iterator)reTypingIterator;
/*     */     }
/*     */     
/*     */     public SimpleFeatureCollection subCollection(Filter filter) {
/* 405 */       Envelope envelope1, filterEnvelope = CachingFeatureSource.this.getEnvelope(filter);
/* 406 */       ReferencedEnvelope referencedEnvelope = this.queryBounds;
/* 407 */       if (filterEnvelope != null)
/* 408 */         envelope1 = referencedEnvelope.intersection((Envelope)this.queryBounds); 
/* 410 */       if (envelope1.isNull())
/* 411 */         return (SimpleFeatureCollection)new EmptyFeatureCollection(this.targetSchema); 
/* 415 */       Query subQuery = new Query(this.query);
/* 416 */       Filter baseFilter = this.query.getFilter();
/* 417 */       if (baseFilter != null && !Filter.INCLUDE.equals(baseFilter)) {
/* 418 */         And and = CachingFeatureSource.ff.and(baseFilter, filter);
/* 419 */         subQuery.setFilter((Filter)and);
/*     */       } 
/* 422 */       return (SimpleFeatureCollection)new CachingFeatureCollection(envelope1, this.sourceSchema, this.targetSchema, subQuery);
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\CachingFeatureSource.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */