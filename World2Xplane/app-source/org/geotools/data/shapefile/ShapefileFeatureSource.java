/*     */ package org.geotools.data.shapefile;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.CoordinateSequenceFactory;
/*     */ import com.vividsolutions.jts.geom.Envelope;
/*     */ import com.vividsolutions.jts.geom.GeometryFactory;
/*     */ import com.vividsolutions.jts.geom.LineString;
/*     */ import com.vividsolutions.jts.geom.MultiLineString;
/*     */ import com.vividsolutions.jts.geom.MultiPoint;
/*     */ import com.vividsolutions.jts.geom.MultiPolygon;
/*     */ import com.vividsolutions.jts.geom.Point;
/*     */ import com.vividsolutions.jts.geom.Polygon;
/*     */ import java.io.IOException;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.channels.ReadableByteChannel;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collections;
/*     */ import java.util.HashSet;
/*     */ import java.util.LinkedHashSet;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import org.geotools.data.DataAccess;
/*     */ import org.geotools.data.DataSourceException;
/*     */ import org.geotools.data.EmptyFeatureReader;
/*     */ import org.geotools.data.FeatureReader;
/*     */ import org.geotools.data.FilteringFeatureReader;
/*     */ import org.geotools.data.PrjFileReader;
/*     */ import org.geotools.data.Query;
/*     */ import org.geotools.data.ReTypeFeatureReader;
/*     */ import org.geotools.data.shapefile.dbf.DbaseFileHeader;
/*     */ import org.geotools.data.shapefile.dbf.DbaseFileReader;
/*     */ import org.geotools.data.shapefile.fid.IndexedFidReader;
/*     */ import org.geotools.data.shapefile.files.FileReader;
/*     */ import org.geotools.data.shapefile.files.ShpFileType;
/*     */ import org.geotools.data.shapefile.files.ShpFiles;
/*     */ import org.geotools.data.shapefile.index.CloseableIterator;
/*     */ import org.geotools.data.shapefile.index.Data;
/*     */ import org.geotools.data.shapefile.index.TreeException;
/*     */ import org.geotools.data.shapefile.shp.IndexFile;
/*     */ import org.geotools.data.shapefile.shp.JTSUtilities;
/*     */ import org.geotools.data.shapefile.shp.ShapefileReader;
/*     */ import org.geotools.data.store.ContentDataStore;
/*     */ import org.geotools.data.store.ContentEntry;
/*     */ import org.geotools.data.store.ContentFeatureSource;
/*     */ import org.geotools.factory.Hints;
/*     */ import org.geotools.feature.AttributeTypeBuilder;
/*     */ import org.geotools.feature.FeatureTypes;
/*     */ import org.geotools.feature.simple.SimpleFeatureTypeBuilder;
/*     */ import org.geotools.feature.type.BasicFeatureTypes;
/*     */ import org.geotools.filter.FilterAttributeExtractor;
/*     */ import org.geotools.filter.visitor.ExtractBoundsFilterVisitor;
/*     */ import org.geotools.geometry.jts.ReferencedEnvelope;
/*     */ import org.geotools.renderer.ScreenMap;
/*     */ import org.geotools.resources.Classes;
/*     */ import org.geotools.util.logging.Logging;
/*     */ import org.opengis.feature.FeatureVisitor;
/*     */ import org.opengis.feature.simple.SimpleFeature;
/*     */ import org.opengis.feature.simple.SimpleFeatureType;
/*     */ import org.opengis.feature.type.AttributeDescriptor;
/*     */ import org.opengis.feature.type.FeatureType;
/*     */ import org.opengis.feature.type.GeometryDescriptor;
/*     */ import org.opengis.feature.type.GeometryType;
/*     */ import org.opengis.filter.Filter;
/*     */ import org.opengis.filter.FilterVisitor;
/*     */ import org.opengis.filter.Id;
/*     */ import org.opengis.referencing.FactoryException;
/*     */ import org.opengis.referencing.crs.CoordinateReferenceSystem;
/*     */ 
/*     */ class ShapefileFeatureSource extends ContentFeatureSource {
/*  96 */   static final Logger LOGGER = Logging.getLogger(ShapefileFeatureSource.class);
/*     */   
/*     */   ShpFiles shpFiles;
/*     */   
/*     */   public ShapefileFeatureSource(ContentEntry entry, ShpFiles shpFiles) {
/* 101 */     super(entry, Query.ALL);
/* 102 */     this.shpFiles = shpFiles;
/* 103 */     HashSet<Hints.Key> hints = new HashSet<Hints.Key>();
/* 104 */     hints.add(Hints.FEATURE_DETACHED);
/* 105 */     hints.add(Hints.JTS_GEOMETRY_FACTORY);
/* 106 */     hints.add(Hints.JTS_COORDINATE_SEQUENCE_FACTORY);
/* 107 */     hints.add(Hints.GEOMETRY_DISTANCE);
/* 108 */     hints.add(Hints.SCREENMAP);
/* 109 */     this.hints = Collections.unmodifiableSet(hints);
/*     */   }
/*     */   
/*     */   public ShapefileDataStore getDataStore() {
/* 114 */     return (ShapefileDataStore)super.getDataStore();
/*     */   }
/*     */   
/*     */   protected boolean canFilter() {
/* 119 */     return true;
/*     */   }
/*     */   
/*     */   protected boolean canRetype() {
/* 124 */     return true;
/*     */   }
/*     */   
/*     */   protected ReferencedEnvelope getBoundsInternal(Query query) throws IOException {
/* 129 */     if (query.getFilter() != Filter.INCLUDE)
/* 130 */       return null; 
/* 133 */     ReadableByteChannel in = null;
/*     */     try {
/* 135 */       ByteBuffer buffer = ByteBuffer.allocate(100);
/* 136 */       FileReader reader = new FileReader() {
/*     */           public String id() {
/* 138 */             return "Shapefile Datastore's getBounds Method";
/*     */           }
/*     */         };
/* 142 */       in = this.shpFiles.getReadChannel(ShpFileType.SHP, reader);
/* 168 */     } catch (IOException ioe) {
/* 169 */       throw new DataSourceException("Problem getting Bbox", ioe);
/*     */     } finally {
/*     */       try {
/* 172 */         if (in != null)
/* 173 */           in.close(); 
/* 175 */       } catch (IOException ioe) {}
/*     */     } 
/*     */   }
/*     */   
/*     */   protected int getCountInternal(Query query) throws IOException {
/* 183 */     if (query.getFilter() == Filter.INCLUDE) {
/* 184 */       IndexFile file = (getDataStore()).shpManager.openIndexFile();
/* 185 */       if (file != null)
/*     */         try {
/* 187 */           return file.getRecordCount();
/*     */         } finally {
/* 189 */           file.close();
/*     */         }  
/* 194 */       ShapefileReader reader = (getDataStore()).shpManager.openShapeReader(new GeometryFactory(), false);
/* 196 */       int count = -1;
/*     */       try {
/* 199 */         count = reader.getCount(count);
/* 200 */       } catch (IOException e) {
/* 201 */         throw e;
/*     */       } finally {
/* 203 */         reader.close();
/*     */       } 
/* 206 */       return count;
/*     */     } 
/* 210 */     return -1;
/*     */   }
/*     */   
/*     */   protected FeatureReader<SimpleFeatureType, SimpleFeature> getReaderInternal(Query q) throws IOException {
/*     */     Envelope envelope;
/*     */     ShapefileFeatureReader result;
/*     */     FeatureReader<SimpleFeatureType, SimpleFeature> reader;
/* 217 */     SimpleFeatureType resultSchema = getResultSchema(q);
/* 218 */     SimpleFeatureType readSchema = getReadSchema(q);
/* 219 */     GeometryFactory geometryFactory = getGeometryFactory(q);
/* 222 */     ReferencedEnvelope referencedEnvelope = new ReferencedEnvelope();
/* 223 */     if (q.getFilter() != null)
/* 224 */       envelope = (Envelope)q.getFilter().accept((FilterVisitor)ExtractBoundsFilterVisitor.BOUNDS_VISITOR, referencedEnvelope); 
/* 228 */     Filter filter = (q != null) ? q.getFilter() : null;
/* 229 */     IndexManager indexManager = (getDataStore()).indexManager;
/* 230 */     CloseableIterator<Data> goodRecs = null;
/* 231 */     if (getDataStore().isFidIndexed() && filter instanceof Id && indexManager.hasFidIndex(false)) {
/* 232 */       Id fidFilter = (Id)filter;
/* 233 */       List<Data> records = indexManager.queryFidIndex(fidFilter);
/* 234 */       if (records != null)
/* 235 */         goodRecs = new CloseableIteratorWrapper<Data>(records.iterator()); 
/* 237 */     } else if (getDataStore().isIndexed() && !envelope.isNull() && !Double.isInfinite(envelope.getWidth()) && !Double.isInfinite(envelope.getHeight())) {
/*     */       try {
/* 240 */         if (indexManager.isSpatialIndexAvailable() || getDataStore().isIndexCreationEnabled())
/* 241 */           goodRecs = indexManager.querySpatialIndex(envelope); 
/* 243 */       } catch (TreeException e) {
/* 244 */         throw new IOException("Error querying index: " + e.getMessage());
/*     */       } 
/*     */     } 
/* 248 */     if (goodRecs != null && !goodRecs.hasNext()) {
/* 249 */       LOGGER.log(Level.FINE, "Empty results for " + resultSchema.getName().getLocalPart() + ", skipping read");
/* 251 */       goodRecs.close();
/* 252 */       return (FeatureReader<SimpleFeatureType, SimpleFeature>)new EmptyFeatureReader((FeatureType)resultSchema);
/*     */     } 
/* 256 */     IndexedFidReader fidReader = null;
/* 257 */     if (getDataStore().isFidIndexed() && filter instanceof Id && indexManager.hasFidIndex(false))
/* 258 */       fidReader = new IndexedFidReader(this.shpFiles); 
/* 262 */     ShapefileSetManager shpManager = (getDataStore()).shpManager;
/* 263 */     ShapefileReader shapeReader = shpManager.openShapeReader(geometryFactory, (goodRecs != null));
/* 264 */     DbaseFileReader dbfReader = null;
/* 265 */     List<AttributeDescriptor> attributes = readSchema.getAttributeDescriptors();
/* 266 */     if (attributes.size() < 1 || (attributes.size() == 1 && readSchema.getGeometryDescriptor() != null)) {
/* 268 */       LOGGER.fine("The DBF file won't be opened since no attributes will be read from it");
/*     */     } else {
/* 270 */       dbfReader = shpManager.openDbfReader((goodRecs != null));
/*     */     } 
/* 273 */     if (goodRecs != null) {
/* 274 */       result = new IndexedShapefileFeatureReader(readSchema, shapeReader, dbfReader, fidReader, goodRecs);
/*     */     } else {
/* 277 */       result = new ShapefileFeatureReader(readSchema, shapeReader, dbfReader, fidReader);
/*     */     } 
/* 281 */     if (q != null) {
/* 282 */       if (envelope != null && !envelope.isNull())
/* 283 */         result.setTargetBBox(envelope); 
/* 286 */       Hints hints = q.getHints();
/* 287 */       if (hints != null) {
/* 288 */         Number simplificationDistance = (Number)hints.get(Hints.GEOMETRY_DISTANCE);
/* 289 */         if (simplificationDistance != null)
/* 290 */           result.setSimplificationDistance(simplificationDistance.doubleValue()); 
/* 292 */         result.setScreenMap((ScreenMap)hints.get(Hints.SCREENMAP));
/* 294 */         if (Boolean.TRUE.equals(hints.get(Hints.FEATURE_2D)))
/* 295 */           shapeReader.setFlatGeometry(true); 
/*     */       } 
/*     */     } 
/* 303 */     if (filter != null && !Filter.INCLUDE.equals(filter)) {
/* 304 */       FilteringFeatureReader filteringFeatureReader = new FilteringFeatureReader(result, filter);
/*     */     } else {
/* 306 */       reader = result;
/*     */     } 
/* 310 */     if (!FeatureTypes.equals(readSchema, resultSchema))
/* 311 */       return (FeatureReader<SimpleFeatureType, SimpleFeature>)new ReTypeFeatureReader(reader, resultSchema); 
/* 313 */     return reader;
/*     */   }
/*     */   
/*     */   SimpleFeatureType getResultSchema(Query q) {
/* 318 */     if (q.getPropertyNames() == null)
/* 319 */       return getSchema(); 
/* 321 */     return SimpleFeatureTypeBuilder.retype(getSchema(), q.getPropertyNames());
/*     */   }
/*     */   
/*     */   SimpleFeatureType getReadSchema(Query q) {
/* 326 */     if (q.getPropertyNames() == null)
/* 327 */       return getSchema(); 
/* 329 */     LinkedHashSet<String> attributes = new LinkedHashSet<String>();
/* 330 */     attributes.addAll(Arrays.asList(q.getPropertyNames()));
/* 331 */     Filter filter = q.getFilter();
/* 332 */     if (filter != null && !Filter.INCLUDE.equals(filter)) {
/* 333 */       FilterAttributeExtractor fat = new FilterAttributeExtractor();
/* 334 */       filter.accept((FilterVisitor)fat, null);
/* 335 */       attributes.addAll(fat.getAttributeNameSet());
/*     */     } 
/* 338 */     return SimpleFeatureTypeBuilder.retype(getSchema(), new ArrayList<String>(attributes));
/*     */   }
/*     */   
/*     */   protected GeometryFactory getGeometryFactory(Query query) {
/* 350 */     if (query == null || query.getHints() == null)
/* 351 */       return new GeometryFactory(); 
/* 355 */     Hints hints = query.getHints();
/* 356 */     GeometryFactory geometryFactory = (GeometryFactory)hints.get(Hints.JTS_GEOMETRY_FACTORY);
/* 357 */     if (geometryFactory == null) {
/* 359 */       CoordinateSequenceFactory csFactory = (CoordinateSequenceFactory)hints.get(Hints.JTS_COORDINATE_SEQUENCE_FACTORY);
/* 362 */       if (csFactory != null)
/* 363 */         geometryFactory = new GeometryFactory(csFactory); 
/*     */     } 
/* 367 */     if (geometryFactory == null)
/* 369 */       geometryFactory = new GeometryFactory(); 
/* 371 */     return geometryFactory;
/*     */   }
/*     */   
/*     */   protected SimpleFeatureType buildFeatureType() throws IOException {
/* 376 */     List<AttributeDescriptor> types = readAttributes();
/* 378 */     SimpleFeatureType parent = null;
/* 379 */     GeometryDescriptor geomDescriptor = (GeometryDescriptor)types.get(0);
/* 380 */     Class<?> geomBinding = geomDescriptor.getType().getBinding();
/* 382 */     if (geomBinding == Point.class || geomBinding == MultiPoint.class) {
/* 383 */       parent = BasicFeatureTypes.POINT;
/* 384 */     } else if (geomBinding == Polygon.class || geomBinding == MultiPolygon.class) {
/* 385 */       parent = BasicFeatureTypes.POLYGON;
/* 386 */     } else if (geomBinding == LineString.class || geomBinding == MultiLineString.class) {
/* 387 */       parent = BasicFeatureTypes.LINE;
/*     */     } 
/* 390 */     SimpleFeatureTypeBuilder builder = new SimpleFeatureTypeBuilder();
/* 391 */     builder.setDefaultGeometry(geomDescriptor.getLocalName());
/* 392 */     builder.addAll(types);
/* 393 */     builder.setName(this.entry.getName());
/* 394 */     builder.setAbstract(false);
/* 395 */     if (parent != null)
/* 396 */       builder.setSuperType(parent); 
/* 398 */     return builder.buildFeatureType();
/*     */   }
/*     */   
/*     */   protected List<AttributeDescriptor> readAttributes() throws IOException {
/* 408 */     ShapefileSetManager shpManager = (getDataStore()).shpManager;
/* 409 */     PrjFileReader prj = null;
/* 410 */     ShapefileReader shp = null;
/* 411 */     DbaseFileReader dbf = null;
/* 412 */     CoordinateReferenceSystem crs = null;
/* 414 */     AttributeTypeBuilder build = new AttributeTypeBuilder();
/* 415 */     List<AttributeDescriptor> attributes = new ArrayList<AttributeDescriptor>();
/*     */     try {
/* 417 */       shp = shpManager.openShapeReader(new GeometryFactory(), false);
/* 418 */       dbf = shpManager.openDbfReader(false);
/*     */       try {
/* 420 */         prj = shpManager.openPrjReader();
/* 422 */         if (prj != null)
/* 423 */           crs = prj.getCoordinateReferenceSystem(); 
/* 425 */       } catch (FactoryException fe) {
/* 426 */         crs = null;
/*     */       } 
/* 429 */       Class<?> geometryClass = JTSUtilities.findBestGeometryClass(shp.getHeader().getShapeType());
/* 431 */       build.setName(Classes.getShortName(geometryClass));
/* 432 */       build.setNillable(true);
/* 433 */       build.setCRS(crs);
/* 434 */       build.setBinding(geometryClass);
/* 436 */       GeometryType geometryType = build.buildGeometryType();
/* 437 */       attributes.add(build.buildDescriptor("the_geom", geometryType));
/* 439 */       Set<String> usedNames = new HashSet<String>();
/* 442 */       usedNames.add("the_geom");
/* 446 */       if (dbf != null) {
/* 447 */         DbaseFileHeader header = dbf.getHeader();
/* 448 */         for (int i = 0, ii = header.getNumFields(); i < ii; i++) {
/* 449 */           Class attributeClass = header.getFieldClass(i);
/* 450 */           String name = header.getFieldName(i);
/* 451 */           if (usedNames.contains(name)) {
/* 452 */             String origional = name;
/* 453 */             int count = 1;
/* 454 */             name = name + count;
/* 455 */             while (usedNames.contains(name)) {
/* 456 */               count++;
/* 457 */               name = origional + count;
/*     */             } 
/* 459 */             build.addUserData("original", origional);
/* 460 */             build.addUserData("count", Integer.valueOf(count));
/*     */           } 
/* 462 */           usedNames.add(name);
/* 463 */           int length = header.getFieldLength(i);
/* 465 */           build.setNillable(true);
/* 466 */           build.setLength(length);
/* 467 */           build.setBinding(attributeClass);
/* 468 */           attributes.add(build.buildDescriptor(name));
/*     */         } 
/*     */       } 
/* 471 */       return attributes;
/*     */     } finally {
/*     */       try {
/* 475 */         if (prj != null)
/* 476 */           prj.close(); 
/* 478 */       } catch (IOException ioe) {}
/*     */       try {
/* 482 */         if (dbf != null)
/* 483 */           dbf.close(); 
/* 485 */       } catch (IOException ioe) {}
/*     */       try {
/* 489 */         if (shp != null)
/* 490 */           shp.close(); 
/* 492 */       } catch (IOException ioe) {}
/*     */     } 
/*     */   }
/*     */   
/*     */   protected boolean handleVisitor(Query query, FeatureVisitor visitor) throws IOException {
/* 500 */     return super.handleVisitor(query, visitor);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\shapefile\ShapefileFeatureSource.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */