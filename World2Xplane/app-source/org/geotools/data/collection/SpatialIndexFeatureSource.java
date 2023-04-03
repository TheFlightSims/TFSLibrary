/*     */ package org.geotools.data.collection;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Envelope;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Comparator;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.Set;
/*     */ import org.geotools.data.DataAccess;
/*     */ import org.geotools.data.DataStore;
/*     */ import org.geotools.data.DataUtilities;
/*     */ import org.geotools.data.FeatureListener;
/*     */ import org.geotools.data.Query;
/*     */ import org.geotools.data.QueryCapabilities;
/*     */ import org.geotools.data.ResourceInfo;
/*     */ import org.geotools.data.simple.SimpleFeatureCollection;
/*     */ import org.geotools.data.simple.SimpleFeatureSource;
/*     */ import org.geotools.data.store.EmptyFeatureCollection;
/*     */ import org.geotools.data.store.ReTypingFeatureCollection;
/*     */ import org.geotools.data.store.ReprojectingFeatureCollection;
/*     */ import org.geotools.factory.CommonFactoryFinder;
/*     */ import org.geotools.feature.FeatureCollection;
/*     */ import org.geotools.feature.collection.MaxSimpleFeatureCollection;
/*     */ import org.geotools.feature.simple.SimpleFeatureTypeBuilder;
/*     */ import org.geotools.geometry.jts.ReferencedEnvelope;
/*     */ import org.opengis.feature.simple.SimpleFeature;
/*     */ import org.opengis.feature.simple.SimpleFeatureType;
/*     */ import org.opengis.feature.type.FeatureType;
/*     */ import org.opengis.feature.type.Name;
/*     */ import org.opengis.filter.And;
/*     */ import org.opengis.filter.Filter;
/*     */ import org.opengis.filter.FilterFactory;
/*     */ import org.opengis.filter.expression.Expression;
/*     */ import org.opengis.filter.expression.Literal;
/*     */ import org.opengis.filter.sort.SortBy;
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
/*     */ public class SpatialIndexFeatureSource implements SimpleFeatureSource {
/*     */   SpatialIndexFeatureCollection contents;
/*     */   
/*  68 */   private static FilterFactory ff = CommonFactoryFinder.getFilterFactory(null);
/*     */   
/*  70 */   private static final Set<Class> supportedFilterTypes = (Set)new HashSet<Class<?>>(Arrays.asList(new Class[] { BBOX.class, Contains.class, Crosses.class, DWithin.class, Equals.class, Intersects.class, Overlaps.class, Touches.class, Within.class }));
/*     */   
/*     */   public SpatialIndexFeatureSource(SpatialIndexFeatureCollection original) {
/*  75 */     this.contents = original;
/*     */   }
/*     */   
/*     */   public void addFeatureListener(FeatureListener listener) {}
/*     */   
/*     */   public void removeFeatureListener(FeatureListener listener) {}
/*     */   
/*     */   public DataStore getDataStore() {
/*  85 */     return null;
/*     */   }
/*     */   
/*     */   public ReferencedEnvelope getBounds() throws IOException {
/*  89 */     return this.contents.getBounds();
/*     */   }
/*     */   
/*     */   public ReferencedEnvelope getBounds(Query query) throws IOException {
/*  93 */     return getFeatures(query).getBounds();
/*     */   }
/*     */   
/*     */   public int getCount(Query query) throws IOException {
/*  97 */     return getFeatures(query).size();
/*     */   }
/*     */   
/*     */   public SimpleFeatureType getSchema() {
/* 101 */     return this.contents.getSchema();
/*     */   }
/*     */   
/*     */   public SimpleFeatureCollection getFeatures() throws IOException {
/* 105 */     return this.contents;
/*     */   }
/*     */   
/*     */   public SimpleFeatureCollection getFeatures(Filter filter) throws IOException {
/* 109 */     Query query = new Query(getSchema().getName().getLocalPart(), filter);
/* 110 */     return getFeatures(query);
/*     */   }
/*     */   
/*     */   public SimpleFeatureCollection getFeatures(Query query) throws IOException {
/* 114 */     Envelope bounds = getEnvelope(query.getFilter());
/* 115 */     return getFeatureCollection(query, bounds);
/*     */   }
/*     */   
/*     */   private SimpleFeatureCollection getFeatureCollection(Query query, Envelope bounds) throws IOException {
/*     */     SimpleFeatureCollection collection;
/*     */     ReprojectingFeatureCollection reprojectingFeatureCollection;
/*     */     ListFeatureCollection listFeatureCollection;
/*     */     MaxSimpleFeatureCollection maxSimpleFeatureCollection;
/*     */     ReTypingFeatureCollection reTypingFeatureCollection;
/* 120 */     query = DataUtilities.resolvePropertyNames(query, getSchema());
/* 121 */     int offset = (query.getStartIndex() != null) ? query.getStartIndex().intValue() : 0;
/* 122 */     if ((((offset > 0) ? 1 : 0) & ((query.getSortBy() == null) ? 1 : 0)) != 0) {
/* 123 */       if (!getQueryCapabilities().supportsSorting(query.getSortBy()))
/* 124 */         throw new IllegalStateException("Feature source does not support this sorting so there is no way a stable paging (offset/limit) can be performed"); 
/* 127 */       Query copy = new Query(query);
/* 128 */       copy.setSortBy(new SortBy[] { SortBy.NATURAL_ORDER });
/* 129 */       query = copy;
/*     */     } 
/* 133 */     if (query.getFilter() != null && query.getFilter().equals(Filter.EXCLUDE))
/* 134 */       return (SimpleFeatureCollection)new EmptyFeatureCollection(getSchema()); 
/* 136 */     if (query.getFilter() != null && query.getFilter().equals(Filter.INCLUDE))
/* 137 */       collection = this.contents; 
/* 139 */     if (query.getFilter() != null && query.getFilter().equals(Filter.INCLUDE)) {
/* 140 */       collection = this.contents;
/*     */     } else {
/* 142 */       collection = this.contents.subCollection(query.getFilter());
/*     */     } 
/* 145 */     if (query.getCoordinateSystemReproject() != null)
/* 146 */       reprojectingFeatureCollection = new ReprojectingFeatureCollection(collection, query.getCoordinateSystemReproject()); 
/* 150 */     if (query.getSortBy() != null && (query.getSortBy()).length != 0) {
/* 151 */       SimpleFeature[] array = (SimpleFeature[])reprojectingFeatureCollection.toArray((Object[])new SimpleFeature[reprojectingFeatureCollection.size()]);
/* 153 */       for (SortBy sortBy : query.getSortBy()) {
/* 154 */         Comparator<SimpleFeature> comparator = DataUtilities.sortComparator(sortBy);
/* 155 */         Arrays.sort(array, comparator);
/*     */       } 
/* 157 */       ArrayList<SimpleFeature> list = new ArrayList<SimpleFeature>(Arrays.asList(array));
/* 158 */       listFeatureCollection = new ListFeatureCollection(getSchema(), list);
/*     */     } 
/* 162 */     if (offset > 0 || !query.isMaxFeaturesUnlimited()) {
/* 163 */       long max = Long.MAX_VALUE;
/* 164 */       if (!query.isMaxFeaturesUnlimited())
/* 165 */         max = query.getMaxFeatures(); 
/* 167 */       maxSimpleFeatureCollection = new MaxSimpleFeatureCollection((SimpleFeatureCollection)listFeatureCollection, offset, max);
/*     */     } 
/* 172 */     if (query.getPropertyNames() != Query.ALL_NAMES) {
/* 174 */       SimpleFeatureType schema = (SimpleFeatureType)maxSimpleFeatureCollection.getSchema();
/* 175 */       SimpleFeatureType target = SimpleFeatureTypeBuilder.retype(schema, query.getPropertyNames());
/* 177 */       if (!target.equals(schema))
/* 178 */         reTypingFeatureCollection = new ReTypingFeatureCollection((SimpleFeatureCollection)maxSimpleFeatureCollection, target); 
/*     */     } 
/* 181 */     return (SimpleFeatureCollection)reTypingFeatureCollection;
/*     */   }
/*     */   
/*     */   Envelope getEnvelope(Filter filter) {
/* 185 */     Envelope result = new Envelope();
/* 186 */     if (filter instanceof And) {
/* 187 */       Envelope bounds = new Envelope();
/* 188 */       for (Iterator<Filter> iter = ((And)filter).getChildren().iterator(); iter.hasNext(); ) {
/* 189 */         Filter f = iter.next();
/* 190 */         Envelope e = getEnvelope(f);
/* 191 */         if (e == null)
/* 192 */           return null; 
/* 194 */         bounds.expandToInclude(e);
/*     */       } 
/* 197 */       result = bounds;
/* 198 */     } else if (filter instanceof BinarySpatialOperator) {
/* 199 */       BinarySpatialOperator gf = (BinarySpatialOperator)filter;
/* 200 */       if (supportedFilterTypes.contains(gf.getClass())) {
/* 201 */         Expression lg = gf.getExpression1();
/* 202 */         Expression rg = gf.getExpression2();
/* 203 */         if (lg instanceof Literal) {
/* 204 */           Geometry g = (Geometry)((Literal)lg).getValue();
/* 205 */           if (rg instanceof org.opengis.filter.expression.PropertyName)
/* 206 */             result = g.getEnvelopeInternal(); 
/* 208 */         } else if (rg instanceof Literal) {
/* 209 */           Geometry g = (Geometry)((Literal)rg).getValue();
/* 210 */           if (lg instanceof org.opengis.filter.expression.PropertyName)
/* 211 */             result = g.getEnvelopeInternal(); 
/*     */         } 
/*     */       } 
/*     */     } 
/* 216 */     return result;
/*     */   }
/*     */   
/*     */   private BBOX bboxFilter(Envelope bbox) {
/* 220 */     return ff.bbox(this.contents.getSchema().getGeometryDescriptor().getLocalName(), bbox.getMinX(), bbox.getMinY(), bbox.getMaxX(), bbox.getMaxY(), null);
/*     */   }
/*     */   
/*     */   public ResourceInfo getInfo() {
/* 225 */     return null;
/*     */   }
/*     */   
/*     */   public Name getName() {
/* 229 */     return this.contents.getSchema().getName();
/*     */   }
/*     */   
/*     */   public QueryCapabilities getQueryCapabilities() {
/* 233 */     return new QueryCapabilities() {
/*     */         public boolean isOffsetSupported() {
/* 236 */           return true;
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   public Set getSupportedHints() {
/* 242 */     HashSet hints = new HashSet();
/* 243 */     return hints;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\collection\SpatialIndexFeatureSource.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */