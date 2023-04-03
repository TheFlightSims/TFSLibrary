/*     */ package org.geotools.data;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Envelope;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import java.io.IOException;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.NoSuchElementException;
/*     */ import java.util.Set;
/*     */ import org.geotools.feature.IllegalAttributeException;
/*     */ import org.geotools.filter.AttributeExpression;
/*     */ import org.opengis.feature.Feature;
/*     */ import org.opengis.feature.simple.SimpleFeature;
/*     */ import org.opengis.feature.type.FeatureType;
/*     */ import org.opengis.filter.Filter;
/*     */ import org.opengis.filter.Id;
/*     */ import org.opengis.filter.expression.Expression;
/*     */ import org.opengis.filter.expression.Literal;
/*     */ import org.opengis.filter.identity.Identifier;
/*     */ import org.opengis.filter.spatial.BinarySpatialOperator;
/*     */ 
/*     */ public class DiffFeatureReader<T extends FeatureType, F extends Feature> implements FeatureReader<T, F> {
/*     */   FeatureReader<T, F> reader;
/*     */   
/*     */   Diff diff;
/*     */   
/*  63 */   F next = null;
/*     */   
/*     */   private Filter filter;
/*     */   
/*     */   private Set encounteredFids;
/*     */   
/*     */   private Iterator<F> addedIterator;
/*     */   
/*     */   private Iterator<F> modifiedIterator;
/*     */   
/*     */   private Iterator<Identifier> fids;
/*     */   
/*     */   private Iterator<F> spatialIndexIterator;
/*     */   
/*     */   private boolean indexedGeometryFilter = false;
/*     */   
/*     */   private boolean fidFilter = false;
/*     */   
/*     */   public DiffFeatureReader(FeatureReader<T, F> reader, Diff diff2) {
/*  86 */     this(reader, diff2, (Filter)Filter.INCLUDE);
/*     */   }
/*     */   
/*     */   public DiffFeatureReader(FeatureReader<T, F> reader, Diff diff2, Filter filter) {
/* 100 */     this.reader = reader;
/* 101 */     this.diff = diff2;
/* 102 */     this.filter = filter;
/* 103 */     this.encounteredFids = new HashSet();
/* 105 */     if (filter instanceof Id) {
/* 106 */       this.fidFilter = true;
/* 107 */     } else if (isSubsetOfBboxFilter(filter)) {
/* 108 */       this.indexedGeometryFilter = true;
/*     */     } 
/* 111 */     synchronized (this.diff) {
/* 112 */       if (this.indexedGeometryFilter)
/* 113 */         this.spatialIndexIterator = getIndexedFeatures().iterator(); 
/* 115 */       this.addedIterator = this.diff.getAdded().values().iterator();
/* 116 */       this.modifiedIterator = this.diff.getModified().values().iterator();
/*     */     } 
/*     */   }
/*     */   
/*     */   public T getFeatureType() {
/* 124 */     return this.reader.getFeatureType();
/*     */   }
/*     */   
/*     */   public F next() throws IOException, IllegalAttributeException, NoSuchElementException {
/* 131 */     if (hasNext()) {
/* 132 */       F live = this.next;
/* 133 */       this.next = null;
/* 135 */       return live;
/*     */     } 
/* 138 */     throw new NoSuchElementException("No more Feature exists");
/*     */   }
/*     */   
/*     */   public boolean hasNext() throws IOException {
/* 145 */     if (this.next != null)
/* 147 */       return true; 
/* 151 */     if (this.filter == Filter.EXCLUDE)
/* 152 */       return false; 
/* 154 */     while (this.reader != null && this.reader.hasNext()) {
/*     */       F peek;
/*     */       try {
/* 157 */         peek = this.reader.next();
/* 158 */       } catch (NoSuchElementException e) {
/* 159 */         throw new DataSourceException("Could not aquire the next Feature", e);
/* 160 */       } catch (IllegalAttributeException e) {
/* 161 */         throw new DataSourceException("Could not aquire the next Feature", e);
/*     */       } 
/* 164 */       String fid = peek.getIdentifier().getID();
/* 165 */       this.encounteredFids.add(fid);
/* 167 */       Map<String, SimpleFeature> modified = this.diff.getModified();
/* 168 */       if (modified.containsKey(fid)) {
/* 169 */         Feature feature = (Feature)modified.get(fid);
/* 170 */         if (feature == TransactionStateDiff.NULL || !this.filter.evaluate(feature))
/*     */           continue; 
/* 173 */         this.next = (F)feature;
/* 174 */         return true;
/*     */       } 
/* 178 */       this.next = peek;
/* 179 */       return true;
/*     */     } 
/* 183 */     queryDiff();
/* 184 */     return (this.next != null);
/*     */   }
/*     */   
/*     */   public void close() throws IOException {
/* 191 */     if (this.reader != null) {
/* 192 */       this.reader.close();
/* 193 */       this.reader = null;
/*     */     } 
/* 196 */     if (this.diff != null) {
/* 197 */       this.diff = null;
/* 198 */       this.addedIterator = null;
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void queryDiff() {
/* 203 */     if (this.fidFilter) {
/* 204 */       queryFidFilter();
/* 205 */     } else if (this.indexedGeometryFilter) {
/* 206 */       querySpatialIndex();
/*     */     } else {
/* 208 */       queryAdded();
/* 209 */       queryModified();
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void querySpatialIndex() {
/* 214 */     while (this.spatialIndexIterator.hasNext() && this.next == null) {
/* 215 */       Feature feature = (Feature)this.spatialIndexIterator.next();
/* 216 */       if (this.encounteredFids.contains(feature.getIdentifier().getID()) || !this.filter.evaluate(feature))
/*     */         continue; 
/* 219 */       this.next = (F)feature;
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void queryAdded() {
/* 224 */     while (this.addedIterator.hasNext() && this.next == null) {
/* 225 */       this.next = this.addedIterator.next();
/* 226 */       if (this.encounteredFids.contains(this.next.getIdentifier().getID()) || !this.filter.evaluate(this.next))
/* 227 */         this.next = null; 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void queryModified() {
/* 233 */     while (this.modifiedIterator.hasNext() && this.next == null) {
/* 234 */       this.next = this.modifiedIterator.next();
/* 235 */       if (this.next == TransactionStateDiff.NULL || this.encounteredFids.contains(this.next.getIdentifier().getID()) || !this.filter.evaluate(this.next))
/* 236 */         this.next = null; 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void queryFidFilter() {
/* 242 */     Id fidFilter = (Id)this.filter;
/* 243 */     if (this.fids == null)
/* 244 */       this.fids = fidFilter.getIdentifiers().iterator(); 
/* 246 */     while (this.fids.hasNext() && this.next == null) {
/* 247 */       String fid = ((Identifier)this.fids.next()).toString();
/* 248 */       if (!this.encounteredFids.contains(fid)) {
/* 249 */         this.next = (F)this.diff.getModified().get(fid);
/* 250 */         if (this.next == null)
/* 251 */           this.next = (F)this.diff.getAdded().get(fid); 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected List getIndexedFeatures() {
/* 259 */     Envelope env = null;
/* 260 */     env = extractBboxForSpatialIndexQuery((BinarySpatialOperator)this.filter);
/* 261 */     return this.diff.queryIndex(env);
/*     */   }
/*     */   
/*     */   protected Envelope extractBboxForSpatialIndexQuery(BinarySpatialOperator filter) {
/*     */     Geometry g;
/* 265 */     Expression leftGeom = filter.getExpression1();
/* 266 */     Expression rightGeom = filter.getExpression2();
/* 269 */     if (leftGeom instanceof Literal) {
/* 270 */       g = (Geometry)((Literal)leftGeom).getValue();
/*     */     } else {
/* 272 */       g = (Geometry)((Literal)rightGeom).getValue();
/*     */     } 
/* 274 */     return g.getEnvelopeInternal();
/*     */   }
/*     */   
/*     */   protected boolean isDefaultGeometry(AttributeExpression ae) {
/* 278 */     return this.reader.getFeatureType().getGeometryDescriptor().getLocalName().equals(ae.getAttributePath());
/*     */   }
/*     */   
/*     */   protected boolean isSubsetOfBboxFilter(Filter f) {
/* 282 */     return (this.filter instanceof org.opengis.filter.spatial.Contains || this.filter instanceof org.opengis.filter.spatial.Crosses || this.filter instanceof org.opengis.filter.spatial.Overlaps || this.filter instanceof org.opengis.filter.spatial.Touches || this.filter instanceof org.opengis.filter.spatial.Within || this.filter instanceof org.opengis.filter.spatial.BBOX);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\DiffFeatureReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */