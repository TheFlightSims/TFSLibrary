/*     */ package org.geotools.data;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import org.geotools.data.crs.ReprojectFeatureReader;
/*     */ import org.geotools.data.simple.SimpleFeatureCollection;
/*     */ import org.geotools.data.simple.SimpleFeatureSource;
/*     */ import org.geotools.data.simple.SimpleFeatureStore;
/*     */ import org.geotools.data.store.DataFeatureCollection;
/*     */ import org.geotools.feature.DefaultFeatureCollection;
/*     */ import org.geotools.feature.SchemaException;
/*     */ import org.geotools.geometry.jts.ReferencedEnvelope;
/*     */ import org.geotools.referencing.CRS;
/*     */ import org.geotools.util.logging.Logging;
/*     */ import org.opengis.feature.IllegalAttributeException;
/*     */ import org.opengis.feature.simple.SimpleFeature;
/*     */ import org.opengis.feature.simple.SimpleFeatureType;
/*     */ import org.opengis.feature.type.AttributeDescriptor;
/*     */ import org.opengis.feature.type.FeatureType;
/*     */ import org.opengis.referencing.FactoryException;
/*     */ import org.opengis.referencing.crs.CoordinateReferenceSystem;
/*     */ import org.opengis.referencing.operation.MathTransform;
/*     */ 
/*     */ public class DefaultFeatureResults extends DataFeatureCollection {
/*  60 */   private static final Logger LOGGER = Logging.getLogger("org.geotools.data");
/*     */   
/*     */   protected Query query;
/*     */   
/*     */   protected SimpleFeatureSource featureSource;
/*     */   
/*     */   protected MathTransform transform;
/*     */   
/*     */   public DefaultFeatureResults(SimpleFeatureSource source, Query query) throws IOException {
/*  91 */     super(null, getSchemaInternal(source, query));
/*  92 */     this.featureSource = source;
/*  94 */     SimpleFeatureType originalType = (SimpleFeatureType)source.getSchema();
/*  96 */     String typeName = originalType.getTypeName();
/*  97 */     if (typeName.equals(query.getTypeName())) {
/*  98 */       this.query = query;
/*     */     } else {
/* 105 */       this.query = new DefaultQuery(query);
/* 106 */       ((DefaultQuery)this.query).setTypeName(typeName);
/*     */     } 
/* 111 */     if (originalType.getGeometryDescriptor() == null)
/*     */       return; 
/* 115 */     CoordinateReferenceSystem cs = null;
/* 116 */     if (query.getCoordinateSystemReproject() != null) {
/* 117 */       cs = query.getCoordinateSystemReproject();
/* 118 */     } else if (query.getCoordinateSystem() != null) {
/* 119 */       cs = query.getCoordinateSystem();
/*     */     } 
/* 121 */     CoordinateReferenceSystem originalCRS = originalType.getGeometryDescriptor().getCoordinateReferenceSystem();
/* 122 */     if (query.getCoordinateSystem() != null)
/* 123 */       originalCRS = query.getCoordinateSystem(); 
/* 125 */     if (cs != null && cs != originalCRS)
/*     */       try {
/* 127 */         this.transform = CRS.findMathTransform(originalCRS, cs, true);
/* 128 */       } catch (FactoryException noTransform) {
/* 129 */         throw (IOException)(new IOException("Could not reproject data to " + cs)).initCause(noTransform);
/*     */       }  
/*     */   }
/*     */   
/*     */   static SimpleFeatureType getSchemaInternal(SimpleFeatureSource featureSource, Query query) {
/* 136 */     SimpleFeatureType originalType = (SimpleFeatureType)featureSource.getSchema();
/* 137 */     SimpleFeatureType schema = null;
/* 139 */     CoordinateReferenceSystem cs = null;
/* 140 */     if (query.getCoordinateSystemReproject() != null) {
/* 141 */       cs = query.getCoordinateSystemReproject();
/* 142 */     } else if (query.getCoordinateSystem() != null) {
/* 143 */       cs = query.getCoordinateSystem();
/*     */     } 
/*     */     try {
/* 146 */       if (cs == null) {
/* 147 */         if (query.retrieveAllProperties()) {
/* 148 */           schema = (SimpleFeatureType)featureSource.getSchema();
/*     */         } else {
/* 150 */           schema = DataUtilities.createSubType((SimpleFeatureType)featureSource.getSchema(), query.getPropertyNames());
/*     */         } 
/*     */       } else {
/* 155 */         schema = DataUtilities.createSubType(originalType, query.getPropertyNames(), cs, query.getTypeName(), null);
/*     */       } 
/* 158 */     } catch (SchemaException e) {
/* 161 */       LOGGER.log(Level.WARNING, "Could not change projection to " + cs, (Throwable)e);
/* 162 */       schema = null;
/*     */     } 
/* 165 */     return schema;
/*     */   }
/*     */   
/*     */   public SimpleFeatureType getSchema() {
/* 186 */     return super.getSchema();
/*     */   }
/*     */   
/*     */   protected Transaction getTransaction() {
/* 196 */     if (this.featureSource instanceof FeatureStore) {
/* 198 */       SimpleFeatureStore featureStore = (SimpleFeatureStore)this.featureSource;
/* 200 */       return featureStore.getTransaction();
/*     */     } 
/* 202 */     return Transaction.AUTO_COMMIT;
/*     */   }
/*     */   
/*     */   public FeatureReader<SimpleFeatureType, SimpleFeature> reader() throws IOException {
/*     */     ReprojectFeatureReader reprojectFeatureReader;
/* 215 */     FeatureReader<SimpleFeatureType, SimpleFeature> reader = ((DataStore)this.featureSource.getDataStore()).getFeatureReader(this.query, getTransaction());
/* 218 */     int maxFeatures = this.query.getMaxFeatures();
/* 219 */     if (maxFeatures != Integer.MAX_VALUE)
/* 220 */       reader = new MaxFeatureReader<SimpleFeatureType, SimpleFeature>(reader, maxFeatures); 
/* 222 */     if (this.transform != null)
/* 223 */       reprojectFeatureReader = new ReprojectFeatureReader(reader, getSchema(), this.transform); 
/* 225 */     return (FeatureReader<SimpleFeatureType, SimpleFeature>)reprojectFeatureReader;
/*     */   }
/*     */   
/*     */   protected FeatureReader<SimpleFeatureType, SimpleFeature> boundsReader() throws IOException {
/* 233 */     List<String> attributes = new ArrayList();
/* 234 */     SimpleFeatureType schema = (SimpleFeatureType)this.featureSource.getSchema();
/* 235 */     for (int i = 0; i < schema.getAttributeCount(); i++) {
/* 236 */       AttributeDescriptor at = schema.getDescriptor(i);
/* 237 */       if (at instanceof org.geotools.feature.type.GeometryDescriptorImpl)
/* 238 */         attributes.add(at.getLocalName()); 
/*     */     } 
/* 241 */     DefaultQuery q = new DefaultQuery(this.query);
/* 242 */     q.setPropertyNames(attributes);
/* 243 */     FeatureReader<SimpleFeatureType, SimpleFeature> reader = ((DataStore)this.featureSource.getDataStore()).getFeatureReader(q, getTransaction());
/* 245 */     int maxFeatures = this.query.getMaxFeatures();
/* 247 */     if (maxFeatures == Integer.MAX_VALUE)
/* 248 */       return reader; 
/* 250 */     return new MaxFeatureReader<SimpleFeatureType, SimpleFeature>(reader, maxFeatures);
/*     */   }
/*     */   
/*     */   public ReferencedEnvelope getBounds() {
/*     */     ReferencedEnvelope referencedEnvelope;
/*     */     try {
/* 273 */       referencedEnvelope = this.featureSource.getBounds(this.query);
/* 274 */     } catch (IOException e1) {
/* 275 */       referencedEnvelope = new ReferencedEnvelope((CoordinateReferenceSystem)null);
/*     */     } 
/* 278 */     if (referencedEnvelope == null)
/*     */       try {
/* 281 */         referencedEnvelope = new ReferencedEnvelope();
/* 283 */         FeatureReader<SimpleFeatureType, SimpleFeature> reader = boundsReader();
/*     */         try {
/* 285 */           while (reader.hasNext()) {
/* 286 */             SimpleFeature feature = reader.next();
/* 287 */             referencedEnvelope.include(feature.getBounds());
/*     */           } 
/*     */         } finally {
/* 290 */           reader.close();
/*     */         } 
/* 292 */       } catch (IllegalAttributeException e) {
/* 294 */         referencedEnvelope = new ReferencedEnvelope();
/* 295 */       } catch (IOException e) {
/* 296 */         referencedEnvelope = new ReferencedEnvelope();
/*     */       }  
/* 300 */     return referencedEnvelope;
/*     */   }
/*     */   
/*     */   public int getCount() throws IOException {
/* 320 */     int count = this.featureSource.getCount(this.query);
/* 322 */     if (count != -1) {
/* 325 */       int maxFeatures = this.query.getMaxFeatures();
/* 326 */       return (count < maxFeatures) ? count : maxFeatures;
/*     */     } 
/*     */     try {
/* 331 */       count = 0;
/* 333 */       FeatureReader<SimpleFeatureType, SimpleFeature> reader = reader();
/*     */       try {
/* 335 */         for (; reader.hasNext(); count++)
/* 336 */           reader.next(); 
/*     */       } finally {
/* 339 */         reader.close();
/*     */       } 
/* 342 */       return count;
/* 343 */     } catch (IllegalAttributeException e) {
/* 344 */       throw new DataSourceException("Could not read feature ", e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public SimpleFeatureCollection collection() throws IOException {
/*     */     try {
/* 350 */       DefaultFeatureCollection collection = new DefaultFeatureCollection(null, null);
/* 352 */       FeatureReader<SimpleFeatureType, SimpleFeature> reader = reader();
/*     */       try {
/* 354 */         while (reader.hasNext())
/* 355 */           collection.add(reader.next()); 
/*     */       } finally {
/* 358 */         reader.close();
/*     */       } 
/* 361 */       return (SimpleFeatureCollection)collection;
/* 362 */     } catch (IllegalAttributeException e) {
/* 363 */       throw new DataSourceException("Could not read feature ", e);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\DefaultFeatureResults.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */