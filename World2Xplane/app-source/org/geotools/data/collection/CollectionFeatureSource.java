/*     */ package org.geotools.data.collection;
/*     */ 
/*     */ import java.awt.RenderingHints;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collections;
/*     */ import java.util.Comparator;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import org.geotools.data.DataAccess;
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
/*     */ import org.geotools.feature.FeatureCollection;
/*     */ import org.geotools.feature.collection.DecoratingSimpleFeatureCollection;
/*     */ import org.geotools.feature.collection.FilteringSimpleFeatureCollection;
/*     */ import org.geotools.feature.collection.MaxSimpleFeatureCollection;
/*     */ import org.geotools.feature.simple.SimpleFeatureTypeBuilder;
/*     */ import org.geotools.geometry.jts.ReferencedEnvelope;
/*     */ import org.opengis.feature.simple.SimpleFeature;
/*     */ import org.opengis.feature.simple.SimpleFeatureType;
/*     */ import org.opengis.feature.type.FeatureType;
/*     */ import org.opengis.feature.type.Name;
/*     */ import org.opengis.filter.Filter;
/*     */ import org.opengis.filter.sort.SortBy;
/*     */ 
/*     */ public class CollectionFeatureSource implements SimpleFeatureSource {
/*     */   protected SimpleFeatureCollection collection;
/*     */   
/*  72 */   protected List<FeatureListener> listeners = null;
/*     */   
/*     */   private QueryCapabilities capabilities;
/*     */   
/*     */   private Set<RenderingHints.Key> hints;
/*     */   
/*     */   public CollectionFeatureSource(SimpleFeatureCollection collection) {
/*  79 */     this.collection = collection;
/*     */   }
/*     */   
/*     */   public SimpleFeatureType getSchema() {
/*  83 */     return (SimpleFeatureType)this.collection.getSchema();
/*     */   }
/*     */   
/*     */   public synchronized void addFeatureListener(FeatureListener listener) {
/*  87 */     if (this.listeners == null)
/*  88 */       this.listeners = Collections.synchronizedList(new ArrayList<FeatureListener>()); 
/*  90 */     this.listeners.add(listener);
/*     */   }
/*     */   
/*     */   public synchronized void removeFeatureListener(FeatureListener listener) {
/*  94 */     if (this.listeners == null)
/*     */       return; 
/*  97 */     this.listeners.remove(listener);
/*     */   }
/*     */   
/*     */   public ReferencedEnvelope getBounds() throws IOException {
/* 101 */     return this.collection.getBounds();
/*     */   }
/*     */   
/*     */   public ReferencedEnvelope getBounds(Query query) throws IOException {
/* 105 */     return getFeatures(query).getBounds();
/*     */   }
/*     */   
/*     */   public int getCount(Query query) throws IOException {
/* 109 */     return getFeatures(query).size();
/*     */   }
/*     */   
/*     */   public DataAccess<SimpleFeatureType, SimpleFeature> getDataStore() {
/* 113 */     throw new UnsupportedOperationException("CollectionFeatureSource is an inmemory wrapper");
/*     */   }
/*     */   
/*     */   public ResourceInfo getInfo() {
/* 117 */     throw new UnsupportedOperationException("CollectionFeatureSource is an inmemory wrapper");
/*     */   }
/*     */   
/*     */   public Name getName() {
/* 121 */     return ((SimpleFeatureType)this.collection.getSchema()).getName();
/*     */   }
/*     */   
/*     */   public synchronized QueryCapabilities getQueryCapabilities() {
/* 125 */     if (this.capabilities == null)
/* 126 */       this.capabilities = new QueryCapabilities() {
/*     */           public boolean isOffsetSupported() {
/* 128 */             return true;
/*     */           }
/*     */           
/*     */           public boolean isReliableFIDSupported() {
/* 132 */             return true;
/*     */           }
/*     */           
/*     */           public boolean supportsSorting(SortBy[] sortAttributes) {
/* 136 */             return true;
/*     */           }
/*     */         }; 
/* 140 */     return this.capabilities;
/*     */   }
/*     */   
/*     */   public synchronized Set<RenderingHints.Key> getSupportedHints() {
/* 144 */     if (this.hints == null) {
/* 145 */       Set<RenderingHints.Key> supports = new HashSet<RenderingHints.Key>();
/* 147 */       this.hints = Collections.unmodifiableSet(supports);
/*     */     } 
/* 149 */     return this.hints;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 154 */     StringBuilder buf = new StringBuilder();
/* 155 */     buf.append("CollectionFeatureSource:");
/* 156 */     buf.append(this.collection);
/* 157 */     return buf.toString();
/*     */   }
/*     */   
/*     */   public SimpleFeatureCollection getFeatures() throws IOException {
/* 166 */     return getFeatures(Query.ALL);
/*     */   }
/*     */   
/*     */   public SimpleFeatureCollection getFeatures(Filter filter) {
/* 170 */     Query query = new Query(getSchema().getTypeName(), filter);
/* 171 */     return getFeatures(query);
/*     */   }
/*     */   
/*     */   public SimpleFeatureCollection getFeatures(Query query) {
/*     */     FilteringSimpleFeatureCollection filteringSimpleFeatureCollection;
/*     */     ReprojectingFeatureCollection reprojectingFeatureCollection;
/*     */     ListFeatureCollection listFeatureCollection;
/*     */     MaxSimpleFeatureCollection maxSimpleFeatureCollection;
/*     */     ReTypingFeatureCollection reTypingFeatureCollection;
/* 175 */     query = DataUtilities.resolvePropertyNames(query, getSchema());
/* 176 */     int offset = (query.getStartIndex() != null) ? query.getStartIndex().intValue() : 0;
/* 177 */     if ((((offset > 0) ? 1 : 0) & ((query.getSortBy() == null) ? 1 : 0)) != 0) {
/* 178 */       if (!getQueryCapabilities().supportsSorting(query.getSortBy()))
/* 179 */         throw new IllegalStateException("Feature source does not support this sorting so there is no way a stable paging (offset/limit) can be performed"); 
/* 182 */       Query copy = new Query(query);
/* 183 */       copy.setSortBy(new SortBy[] { SortBy.NATURAL_ORDER });
/* 184 */       query = copy;
/*     */     } 
/* 186 */     SimpleFeatureCollection features = this.collection;
/* 188 */     if (query.getFilter() != null && query.getFilter().equals(Filter.EXCLUDE))
/* 189 */       return (SimpleFeatureCollection)new EmptyFeatureCollection(getSchema()); 
/* 191 */     if (query.getFilter() != null && query.getFilter() != Filter.INCLUDE)
/* 192 */       filteringSimpleFeatureCollection = new FilteringSimpleFeatureCollection(features, query.getFilter()); 
/* 195 */     if (query.getCoordinateSystemReproject() != null)
/* 196 */       reprojectingFeatureCollection = new ReprojectingFeatureCollection((SimpleFeatureCollection)filteringSimpleFeatureCollection, query.getCoordinateSystemReproject()); 
/* 200 */     if (query.getSortBy() != null && (query.getSortBy()).length != 0) {
/* 201 */       SimpleFeature[] array = (SimpleFeature[])reprojectingFeatureCollection.toArray((Object[])new SimpleFeature[reprojectingFeatureCollection.size()]);
/* 203 */       for (SortBy sortBy : query.getSortBy()) {
/* 204 */         Comparator<SimpleFeature> comparator = DataUtilities.sortComparator(sortBy);
/* 205 */         Arrays.sort(array, comparator);
/*     */       } 
/* 207 */       ArrayList<SimpleFeature> list = new ArrayList<SimpleFeature>(Arrays.asList(array));
/* 208 */       listFeatureCollection = new ListFeatureCollection(getSchema(), list);
/*     */     } 
/* 212 */     if (offset > 0 || !query.isMaxFeaturesUnlimited()) {
/* 213 */       long max = Long.MAX_VALUE;
/* 214 */       if (!query.isMaxFeaturesUnlimited())
/* 215 */         max = query.getMaxFeatures(); 
/* 217 */       maxSimpleFeatureCollection = new MaxSimpleFeatureCollection((SimpleFeatureCollection)listFeatureCollection, offset, max);
/*     */     } 
/* 223 */     if (query.getPropertyNames() != Query.ALL_NAMES) {
/* 225 */       SimpleFeatureType schema = (SimpleFeatureType)maxSimpleFeatureCollection.getSchema();
/* 226 */       SimpleFeatureType target = SimpleFeatureTypeBuilder.retype(schema, query.getPropertyNames());
/* 231 */       if (!target.equals(schema))
/* 232 */         reTypingFeatureCollection = new ReTypingFeatureCollection((SimpleFeatureCollection)maxSimpleFeatureCollection, target); 
/*     */     } 
/* 236 */     return (SimpleFeatureCollection)new SubCollection(query, (SimpleFeatureCollection)reTypingFeatureCollection);
/*     */   }
/*     */   
/*     */   protected class SubCollection extends DecoratingSimpleFeatureCollection {
/*     */     private Query query;
/*     */     
/*     */     protected SubCollection(Query query, SimpleFeatureCollection features) {
/* 251 */       super(features);
/* 252 */       this.query = query;
/*     */     }
/*     */     
/*     */     public SimpleFeatureCollection subCollection(Filter filter) {
/* 255 */       Query q = new Query(getSchema().getTypeName(), filter);
/* 257 */       Query subQuery = DataUtilities.mixQueries(this.query, q, q.getHandle());
/* 258 */       return CollectionFeatureSource.this.getFeatures(subQuery);
/*     */     }
/*     */     
/*     */     public SimpleFeatureCollection sort(SortBy order) {
/* 262 */       Query q = new Query(getSchema().getTypeName());
/* 263 */       q.setSortBy(new SortBy[] { order });
/* 265 */       Query subQuery = DataUtilities.mixQueries(this.query, q, q.getHandle());
/* 266 */       return CollectionFeatureSource.this.getFeatures(subQuery);
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\collection\CollectionFeatureSource.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */