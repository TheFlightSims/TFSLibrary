/*     */ package org.geotools.data.shapefile;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Envelope;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.LineString;
/*     */ import com.vividsolutions.jts.geom.MultiLineString;
/*     */ import com.vividsolutions.jts.geom.MultiPoint;
/*     */ import com.vividsolutions.jts.geom.MultiPolygon;
/*     */ import com.vividsolutions.jts.geom.Point;
/*     */ import com.vividsolutions.jts.geom.Polygon;
/*     */ import java.io.FileWriter;
/*     */ import java.io.IOException;
/*     */ import java.math.BigInteger;
/*     */ import java.net.URL;
/*     */ import java.nio.channels.FileChannel;
/*     */ import java.nio.channels.WritableByteChannel;
/*     */ import java.nio.charset.Charset;
/*     */ import java.util.Calendar;
/*     */ import java.util.Collections;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import java.util.TimeZone;
/*     */ import java.util.UUID;
/*     */ import java.util.logging.Level;
/*     */ import org.geotools.data.DataSourceException;
/*     */ import org.geotools.data.FeatureReader;
/*     */ import org.geotools.data.FeatureWriter;
/*     */ import org.geotools.data.FileDataStore;
/*     */ import org.geotools.data.Query;
/*     */ import org.geotools.data.Transaction;
/*     */ import org.geotools.data.shapefile.dbf.DbaseFileException;
/*     */ import org.geotools.data.shapefile.dbf.DbaseFileHeader;
/*     */ import org.geotools.data.shapefile.files.ShpFileType;
/*     */ import org.geotools.data.shapefile.files.ShpFiles;
/*     */ import org.geotools.data.shapefile.files.StorageFile;
/*     */ import org.geotools.data.shapefile.shp.ShapeType;
/*     */ import org.geotools.data.shapefile.shp.ShapefileWriter;
/*     */ import org.geotools.data.simple.SimpleFeatureSource;
/*     */ import org.geotools.data.store.ContentDataStore;
/*     */ import org.geotools.data.store.ContentEntry;
/*     */ import org.geotools.data.store.ContentFeatureSource;
/*     */ import org.geotools.feature.FeatureTypes;
/*     */ import org.geotools.feature.NameImpl;
/*     */ import org.geotools.referencing.wkt.Formattable;
/*     */ import org.opengis.feature.simple.SimpleFeature;
/*     */ import org.opengis.feature.simple.SimpleFeatureType;
/*     */ import org.opengis.feature.type.AttributeDescriptor;
/*     */ import org.opengis.feature.type.FeatureType;
/*     */ import org.opengis.feature.type.Name;
/*     */ import org.opengis.feature.type.PropertyDescriptor;
/*     */ import org.opengis.filter.Filter;
/*     */ import org.opengis.referencing.crs.CoordinateReferenceSystem;
/*     */ 
/*     */ public class ShapefileDataStore extends ContentDataStore implements FileDataStore {
/*     */   public static final String ORIGINAL_FIELD_NAME = "original";
/*     */   
/*     */   public static final String ORIGINAL_FIELD_DUPLICITY_COUNT = "count";
/*     */   
/*  80 */   public static final Charset DEFAULT_STRING_CHARSET = (Charset)ShapefileDataStoreFactory.DBFCHARSET.getDefaultValue();
/*     */   
/*  83 */   public static final TimeZone DEFAULT_TIMEZONE = (TimeZone)ShapefileDataStoreFactory.DBFTIMEZONE.getDefaultValue();
/*     */   
/*  90 */   protected static final Boolean TRACE_ENABLED = Boolean.valueOf("true".equalsIgnoreCase(System.getProperty("gt2.shapefile.trace")));
/*     */   
/*     */   Exception trace;
/*     */   
/*     */   ShpFiles shpFiles;
/*     */   
/* 100 */   Charset charset = DEFAULT_STRING_CHARSET;
/*     */   
/* 102 */   TimeZone timeZone = DEFAULT_TIMEZONE;
/*     */   
/*     */   boolean memoryMapped = false;
/*     */   
/*     */   boolean bufferCachingEnabled = true;
/*     */   
/*     */   boolean indexed = true;
/*     */   
/*     */   boolean indexCreationEnabled = true;
/*     */   
/*     */   boolean fidIndexed = true;
/*     */   
/*     */   IndexManager indexManager;
/*     */   
/*     */   ShapefileSetManager shpManager;
/*     */   
/*     */   public ShapefileDataStore(URL url) {
/* 119 */     this.shpFiles = new ShpFiles(url);
/* 120 */     if (TRACE_ENABLED.booleanValue()) {
/* 121 */       this.trace = new Exception();
/* 122 */       this.trace.fillInStackTrace();
/*     */     } 
/* 124 */     this.shpManager = new ShapefileSetManager(this.shpFiles, this);
/* 125 */     this.indexManager = new IndexManager(this.shpFiles, this);
/*     */   }
/*     */   
/*     */   protected List<Name> createTypeNames() throws IOException {
/* 130 */     return Collections.singletonList(getTypeName());
/*     */   }
/*     */   
/*     */   Name getTypeName() {
/* 134 */     return (Name)new NameImpl(this.namespaceURI, this.shpFiles.getTypeName());
/*     */   }
/*     */   
/*     */   protected ContentFeatureSource createFeatureSource(ContentEntry entry) throws IOException {
/* 139 */     return getFeatureSource();
/*     */   }
/*     */   
/*     */   public ContentFeatureSource getFeatureSource() throws IOException {
/* 143 */     ContentEntry entry = ensureEntry(getTypeName());
/* 144 */     if (this.shpFiles.isWritable())
/* 145 */       return (ContentFeatureSource)new ShapefileFeatureStore(entry, this.shpFiles); 
/* 147 */     return new ShapefileFeatureSource(entry, this.shpFiles);
/*     */   }
/*     */   
/*     */   public Charset getCharset() {
/* 152 */     return this.charset;
/*     */   }
/*     */   
/*     */   public void setCharset(Charset charset) {
/* 156 */     this.charset = charset;
/*     */   }
/*     */   
/*     */   public TimeZone getTimeZone() {
/* 160 */     return this.timeZone;
/*     */   }
/*     */   
/*     */   public void setTimeZone(TimeZone timeZone) {
/* 164 */     this.timeZone = timeZone;
/*     */   }
/*     */   
/*     */   public boolean isMemoryMapped() {
/* 168 */     return this.memoryMapped;
/*     */   }
/*     */   
/*     */   public void setMemoryMapped(boolean memoryMapped) {
/* 172 */     this.memoryMapped = memoryMapped;
/*     */   }
/*     */   
/*     */   public boolean isBufferCachingEnabled() {
/* 176 */     return this.bufferCachingEnabled;
/*     */   }
/*     */   
/*     */   public void setBufferCachingEnabled(boolean bufferCachingEnabled) {
/* 180 */     this.bufferCachingEnabled = bufferCachingEnabled;
/*     */   }
/*     */   
/*     */   public boolean isIndexed() {
/* 184 */     return this.indexed;
/*     */   }
/*     */   
/*     */   public void setIndexed(boolean indexed) {
/* 193 */     this.indexed = indexed;
/*     */   }
/*     */   
/*     */   public SimpleFeatureType getSchema() throws IOException {
/* 197 */     return getSchema(getTypeName());
/*     */   }
/*     */   
/*     */   public FeatureReader<SimpleFeatureType, SimpleFeature> getFeatureReader() throws IOException {
/* 201 */     return getFeatureReader(new Query(getTypeName().getLocalPart()), Transaction.AUTO_COMMIT);
/*     */   }
/*     */   
/*     */   public long getCount(Query query) throws IOException {
/* 206 */     return getFeatureSource().getCount(query);
/*     */   }
/*     */   
/*     */   public void createSchema(SimpleFeatureType featureType) throws IOException {
/*     */     ShapeType shapeType;
/* 218 */     if (!this.shpFiles.isLocal())
/* 219 */       throw new IOException("Cannot create FeatureType on remote or in-classpath shapefile"); 
/* 222 */     this.shpFiles.delete();
/* 224 */     CoordinateReferenceSystem crs = featureType.getGeometryDescriptor().getCoordinateReferenceSystem();
/* 226 */     Class<?> geomType = featureType.getGeometryDescriptor().getType().getBinding();
/* 229 */     if (Point.class.isAssignableFrom(geomType)) {
/* 230 */       shapeType = ShapeType.POINT;
/* 231 */     } else if (MultiPoint.class.isAssignableFrom(geomType)) {
/* 232 */       shapeType = ShapeType.MULTIPOINT;
/* 233 */     } else if (LineString.class.isAssignableFrom(geomType) || MultiLineString.class.isAssignableFrom(geomType)) {
/* 235 */       shapeType = ShapeType.ARC;
/* 236 */     } else if (Polygon.class.isAssignableFrom(geomType) || MultiPolygon.class.isAssignableFrom(geomType)) {
/* 238 */       shapeType = ShapeType.POLYGON;
/*     */     } else {
/* 240 */       throw new DataSourceException("Cannot create a shapefile whose geometry type is " + geomType);
/*     */     } 
/* 244 */     StorageFile shpStoragefile = this.shpFiles.getStorageFile(ShpFileType.SHP);
/* 245 */     StorageFile shxStoragefile = this.shpFiles.getStorageFile(ShpFileType.SHX);
/* 246 */     StorageFile dbfStoragefile = this.shpFiles.getStorageFile(ShpFileType.DBF);
/* 247 */     StorageFile prjStoragefile = this.shpFiles.getStorageFile(ShpFileType.PRJ);
/* 249 */     FileChannel shpChannel = shpStoragefile.getWriteChannel();
/* 250 */     FileChannel shxChannel = shxStoragefile.getWriteChannel();
/* 252 */     ShapefileWriter writer = new ShapefileWriter(shpChannel, shxChannel);
/*     */     try {
/* 255 */       writer.writeHeaders(new Envelope(), shapeType, 0, 100);
/*     */     } finally {
/* 257 */       writer.close();
/* 258 */       assert !shpChannel.isOpen();
/* 259 */       assert !shxChannel.isOpen();
/*     */     } 
/* 262 */     DbaseFileHeader dbfheader = createDbaseHeader(featureType);
/* 264 */     dbfheader.setNumRecords(0);
/* 266 */     WritableByteChannel dbfChannel = dbfStoragefile.getWriteChannel();
/*     */     try {
/* 269 */       dbfheader.writeHeader(dbfChannel);
/*     */     } finally {
/* 271 */       dbfChannel.close();
/*     */     } 
/* 274 */     if (crs != null) {
/* 275 */       String s = toSingleLineWKT(crs);
/* 277 */       FileWriter prjWriter = new FileWriter(prjStoragefile.getFile());
/*     */       try {
/* 279 */         prjWriter.write(s);
/*     */       } finally {
/* 281 */         prjWriter.close();
/*     */       } 
/*     */     } else {
/* 284 */       this.LOGGER.warning("PRJ file not generated for null CoordinateReferenceSystem");
/*     */     } 
/* 286 */     StorageFile.replaceOriginals(new StorageFile[] { shpStoragefile, shxStoragefile, dbfStoragefile, prjStoragefile });
/*     */   }
/*     */   
/*     */   String toSingleLineWKT(CoordinateReferenceSystem crs) {
/* 296 */     String wkt = null;
/*     */     try {
/* 299 */       Formattable formattable = (Formattable)crs;
/* 300 */       wkt = formattable.toWKT(0, false);
/* 301 */     } catch (ClassCastException e) {
/* 302 */       wkt = crs.toWKT();
/*     */     } 
/* 305 */     wkt = wkt.replaceAll("\n", "").replaceAll("  ", "");
/* 306 */     return wkt;
/*     */   }
/*     */   
/*     */   protected static DbaseFileHeader createDbaseHeader(SimpleFeatureType featureType) throws IOException, DbaseFileException {
/* 323 */     DbaseFileHeader header = new DbaseFileHeader();
/* 325 */     for (int i = 0, ii = featureType.getAttributeCount(); i < ii; i++) {
/* 326 */       AttributeDescriptor type = featureType.getDescriptor(i);
/* 328 */       Class<?> colType = type.getType().getBinding();
/* 329 */       String colName = type.getLocalName();
/* 331 */       int fieldLen = FeatureTypes.getFieldLength((PropertyDescriptor)type);
/* 332 */       if (fieldLen == -1)
/* 333 */         fieldLen = 255; 
/* 334 */       if (colType == Integer.class || colType == Short.class || colType == Byte.class) {
/* 335 */         header.addColumn(colName, 'N', Math.min(fieldLen, 9), 0);
/* 336 */       } else if (colType == Long.class) {
/* 337 */         header.addColumn(colName, 'N', Math.min(fieldLen, 19), 0);
/* 338 */       } else if (colType == BigInteger.class) {
/* 339 */         header.addColumn(colName, 'N', Math.min(fieldLen, 33), 0);
/* 340 */       } else if (Number.class.isAssignableFrom(colType)) {
/* 341 */         int l = Math.min(fieldLen, 33);
/* 342 */         int d = Math.max(l - 2, 0);
/* 343 */         header.addColumn(colName, 'N', l, d);
/* 347 */       } else if (Date.class.isAssignableFrom(colType) && Boolean.getBoolean("org.geotools.shapefile.datetime")) {
/* 349 */         header.addColumn(colName, '@', fieldLen, 0);
/* 350 */       } else if (Date.class.isAssignableFrom(colType) || Calendar.class.isAssignableFrom(colType)) {
/* 352 */         header.addColumn(colName, 'D', fieldLen, 0);
/* 353 */       } else if (colType == Boolean.class) {
/* 354 */         header.addColumn(colName, 'L', 1, 0);
/* 355 */       } else if (CharSequence.class.isAssignableFrom(colType) || colType == UUID.class) {
/* 358 */         header.addColumn(colName, 'C', Math.min(254, fieldLen), 0);
/* 359 */       } else if (!Geometry.class.isAssignableFrom(colType)) {
/* 362 */         if (colType != byte[].class)
/* 365 */           throw new IOException("Unable to write column " + colName + " : " + colType.getName()); 
/*     */       } 
/*     */     } 
/* 369 */     return header;
/*     */   }
/*     */   
/*     */   public void forceSchemaCRS(CoordinateReferenceSystem crs) throws IOException {
/* 383 */     if (crs == null)
/* 384 */       throw new NullPointerException("CRS required for .prj file"); 
/* 386 */     String s = toSingleLineWKT(crs);
/* 387 */     StorageFile storageFile = this.shpFiles.getStorageFile(ShpFileType.PRJ);
/* 388 */     FileWriter out = new FileWriter(storageFile.getFile());
/*     */     try {
/* 391 */       out.write(s);
/*     */     } finally {
/* 393 */       out.close();
/*     */     } 
/* 395 */     storageFile.replaceOriginal();
/* 396 */     this.entries.clear();
/*     */   }
/*     */   
/*     */   public void dispose() {
/* 401 */     super.dispose();
/* 402 */     if (this.shpFiles != null) {
/* 403 */       this.shpFiles.dispose();
/* 404 */       this.shpFiles = null;
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void finalize() throws Throwable {
/* 410 */     super.finalize();
/* 411 */     if (this.shpFiles != null && this.trace != null)
/* 412 */       this.LOGGER.log(Level.SEVERE, "Undisposed of shapefile, you should call dispose() on all shapefile stores", this.trace); 
/* 416 */     dispose();
/*     */   }
/*     */   
/*     */   public boolean isFidIndexed() {
/* 428 */     return this.fidIndexed;
/*     */   }
/*     */   
/*     */   public void setFidIndexed(boolean fidIndexed) {
/* 436 */     this.fidIndexed = fidIndexed;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 441 */     return "ShapefileDataStore [file=" + this.shpFiles.get(ShpFileType.SHP) + ", charset=" + this.charset + ", timeZone=" + this.timeZone + ", memoryMapped=" + this.memoryMapped + ", bufferCachingEnabled=" + this.bufferCachingEnabled + ", indexed=" + this.indexed + ", fidIndexed=" + this.fidIndexed + "]";
/*     */   }
/*     */   
/*     */   public void updateSchema(SimpleFeatureType featureType) throws IOException {
/* 449 */     updateSchema(getTypeName().getLocalPart(), featureType);
/*     */   }
/*     */   
/*     */   public FeatureWriter<SimpleFeatureType, SimpleFeature> getFeatureWriter(Filter filter, Transaction transaction) throws IOException {
/* 455 */     return getFeatureWriter(getTypeName().getLocalPart(), filter, transaction);
/*     */   }
/*     */   
/*     */   public FeatureWriter<SimpleFeatureType, SimpleFeature> getFeatureWriter(Transaction transaction) throws IOException {
/* 461 */     return getFeatureWriter(getTypeName().getLocalPart(), transaction);
/*     */   }
/*     */   
/*     */   public FeatureWriter<SimpleFeatureType, SimpleFeature> getFeatureWriterAppend(Transaction transaction) throws IOException {
/* 467 */     return getFeatureWriterAppend(getTypeName().getLocalPart(), transaction);
/*     */   }
/*     */   
/*     */   public boolean isIndexCreationEnabled() {
/* 471 */     return this.indexCreationEnabled;
/*     */   }
/*     */   
/*     */   public void setIndexCreationEnabled(boolean indexCreationEnabled) {
/* 479 */     this.indexCreationEnabled = indexCreationEnabled;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\shapefile\ShapefileDataStore.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */