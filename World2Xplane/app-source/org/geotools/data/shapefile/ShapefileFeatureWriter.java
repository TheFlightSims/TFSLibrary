/*     */ package org.geotools.data.shapefile;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Envelope;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.GeometryFactory;
/*     */ import java.io.IOException;
/*     */ import java.nio.channels.FileChannel;
/*     */ import java.nio.charset.Charset;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.TimeZone;
/*     */ import org.geotools.data.DataUtilities;
/*     */ import org.geotools.data.FeatureWriter;
/*     */ import org.geotools.data.shapefile.dbf.DbaseFileHeader;
/*     */ import org.geotools.data.shapefile.dbf.DbaseFileWriter;
/*     */ import org.geotools.data.shapefile.files.ShpFileType;
/*     */ import org.geotools.data.shapefile.files.ShpFiles;
/*     */ import org.geotools.data.shapefile.files.StorageFile;
/*     */ import org.geotools.data.shapefile.shp.JTSUtilities;
/*     */ import org.geotools.data.shapefile.shp.ShapeHandler;
/*     */ import org.geotools.data.shapefile.shp.ShapeType;
/*     */ import org.geotools.data.shapefile.shp.ShapefileException;
/*     */ import org.geotools.data.shapefile.shp.ShapefileWriter;
/*     */ import org.opengis.feature.Feature;
/*     */ import org.opengis.feature.simple.SimpleFeature;
/*     */ import org.opengis.feature.simple.SimpleFeatureType;
/*     */ import org.opengis.feature.type.FeatureType;
/*     */ import org.opengis.feature.type.GeometryDescriptor;
/*     */ 
/*     */ class ShapefileFeatureWriter implements FeatureWriter<SimpleFeatureType, SimpleFeature> {
/*     */   protected ShapefileFeatureReader featureReader;
/*     */   
/*     */   protected SimpleFeature currentFeature;
/*     */   
/*     */   protected SimpleFeatureType featureType;
/*     */   
/*     */   protected Object[] emptyAtts;
/*     */   
/*     */   protected Object[] transferCache;
/*     */   
/*     */   protected ShapeType shapeType;
/*     */   
/*     */   protected ShapeHandler handler;
/*     */   
/*  83 */   protected int shapefileLength = 100;
/*     */   
/*  86 */   protected int records = 0;
/*     */   
/*     */   protected byte[] writeFlags;
/*     */   
/*     */   protected ShapefileWriter shpWriter;
/*     */   
/*     */   protected DbaseFileWriter dbfWriter;
/*     */   
/*     */   private DbaseFileHeader dbfHeader;
/*     */   
/*  97 */   protected Map<ShpFileType, StorageFile> storageFiles = new HashMap<ShpFileType, StorageFile>();
/*     */   
/* 100 */   protected Envelope bounds = new Envelope();
/*     */   
/*     */   protected ShpFiles shpFiles;
/*     */   
/*     */   private FileChannel dbfChannel;
/*     */   
/*     */   private Charset dbfCharset;
/*     */   
/*     */   private TimeZone dbfTimeZone;
/*     */   
/* 110 */   private GeometryFactory gf = new GeometryFactory();
/*     */   
/*     */   private boolean guessShapeType;
/*     */   
/*     */   public ShapefileFeatureWriter(ShpFiles shpFiles, ShapefileFeatureReader featureReader, Charset charset, TimeZone timezone) throws IOException {
/* 116 */     this.shpFiles = shpFiles;
/* 117 */     this.dbfCharset = charset;
/* 118 */     this.dbfTimeZone = timezone;
/* 120 */     this.featureReader = featureReader;
/* 122 */     this.storageFiles.put(ShpFileType.SHP, shpFiles.getStorageFile(ShpFileType.SHP));
/* 123 */     this.storageFiles.put(ShpFileType.SHX, shpFiles.getStorageFile(ShpFileType.SHX));
/* 124 */     this.storageFiles.put(ShpFileType.DBF, shpFiles.getStorageFile(ShpFileType.DBF));
/* 126 */     this.featureType = featureReader.getFeatureType();
/* 129 */     this.emptyAtts = new Object[this.featureType.getAttributeCount()];
/* 130 */     this.writeFlags = new byte[this.featureType.getAttributeCount()];
/* 132 */     int cnt = 0;
/* 134 */     for (int i = 0, ii = this.featureType.getAttributeCount(); i < ii; i++) {
/* 136 */       if (!(this.featureType.getDescriptor(i) instanceof GeometryDescriptor)) {
/* 137 */         cnt++;
/* 138 */         this.writeFlags[i] = 1;
/*     */       } 
/*     */     } 
/* 143 */     this.transferCache = new Object[cnt];
/* 146 */     FileChannel shpChannel = ((StorageFile)this.storageFiles.get(ShpFileType.SHP)).getWriteChannel();
/* 147 */     FileChannel shxChannel = ((StorageFile)this.storageFiles.get(ShpFileType.SHX)).getWriteChannel();
/* 148 */     this.shpWriter = new ShapefileWriter(shpChannel, shxChannel);
/* 150 */     this.dbfHeader = ShapefileDataStore.createDbaseHeader(this.featureType);
/* 151 */     this.dbfChannel = ((StorageFile)this.storageFiles.get(ShpFileType.DBF)).getWriteChannel();
/* 152 */     this.dbfWriter = new DbaseFileWriter(this.dbfHeader, this.dbfChannel, this.dbfCharset, this.dbfTimeZone);
/* 155 */     featureReader.disableShxUsage();
/* 156 */     this.guessShapeType = !featureReader.hasNext();
/* 157 */     this.shapeType = featureReader.getShapeType();
/* 158 */     this.handler = this.shapeType.getShapeHandler(new GeometryFactory());
/* 159 */     this.shpWriter.writeHeaders(this.bounds, this.shapeType, this.records, this.shapefileLength);
/*     */   }
/*     */   
/*     */   protected void flush() throws IOException {
/* 171 */     if (this.records <= 0 && this.shapeType == null) {
/* 172 */       GeometryDescriptor geometryDescriptor = this.featureType.getGeometryDescriptor();
/* 174 */       this.shapeType = JTSUtilities.getShapeType(geometryDescriptor);
/*     */     } 
/* 177 */     this.shpWriter.writeHeaders(this.bounds, this.shapeType, this.records, this.shapefileLength);
/* 179 */     this.dbfHeader.setNumRecords(this.records);
/* 180 */     this.dbfChannel.position(0L);
/* 181 */     this.dbfHeader.writeHeader(this.dbfChannel);
/*     */   }
/*     */   
/*     */   protected void finalize() throws Throwable {
/* 190 */     if (this.featureReader != null)
/*     */       try {
/* 192 */         close();
/* 193 */       } catch (Exception e) {} 
/*     */   }
/*     */   
/*     */   protected void clean() throws IOException {
/* 205 */     StorageFile.replaceOriginals((StorageFile[])this.storageFiles.values().toArray((Object[])new StorageFile[0]));
/*     */   }
/*     */   
/*     */   public void close() throws IOException {
/* 214 */     if (this.featureReader == null)
/* 215 */       throw new IOException("Writer closed"); 
/* 219 */     if (this.currentFeature != null)
/* 220 */       write(); 
/* 223 */     if (this.featureReader.nextFeature != null) {
/* 224 */       this.currentFeature = this.featureReader.nextFeature;
/* 225 */       write();
/*     */     } 
/* 231 */     if (this.featureReader != null) {
/* 232 */       this.handler = this.shapeType.getShapeHandler(this.gf);
/* 236 */       if (this.records == 0)
/* 237 */         this.shpWriter.writeHeaders(this.bounds, this.shapeType, 0, 0); 
/* 241 */       double[] env = new double[4];
/* 243 */       while (this.featureReader.filesHaveMore()) {
/* 245 */         this.shapefileLength += this.featureReader.shp.transferTo(this.shpWriter, ++this.records, env);
/* 248 */         this.bounds.expandToInclude(env[0], env[1]);
/* 249 */         this.bounds.expandToInclude(env[2], env[3]);
/* 252 */         this.featureReader.dbf.transferTo(this.dbfWriter);
/*     */       } 
/*     */     } 
/* 256 */     doClose();
/* 257 */     clean();
/*     */   }
/*     */   
/*     */   protected void doClose() throws IOException {
/*     */     try {
/* 263 */       this.featureReader.close();
/*     */     } finally {
/*     */       try {
/* 266 */         flush();
/*     */       } finally {
/* 268 */         this.shpWriter.close();
/* 269 */         this.dbfWriter.close();
/*     */       } 
/* 272 */       this.featureReader = null;
/* 273 */       this.shpWriter = null;
/* 274 */       this.dbfWriter = null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public SimpleFeatureType getFeatureType() {
/* 279 */     return this.featureType;
/*     */   }
/*     */   
/*     */   public boolean hasNext() throws IOException {
/* 283 */     if (this.featureReader == null)
/* 284 */       throw new IOException("Writer closed"); 
/* 287 */     return this.featureReader.hasNext();
/*     */   }
/*     */   
/*     */   public SimpleFeature next() throws IOException {
/* 292 */     if (this.featureReader == null)
/* 293 */       throw new IOException("Writer closed"); 
/* 297 */     if (this.currentFeature != null)
/* 298 */       write(); 
/* 302 */     if (this.featureReader.hasNext())
/* 303 */       return this.currentFeature = this.featureReader.next(); 
/* 308 */     String featureID = getFeatureType().getTypeName() + "." + (this.records + 1);
/* 309 */     return this.currentFeature = DataUtilities.template(getFeatureType(), featureID, this.emptyAtts);
/*     */   }
/*     */   
/*     */   protected String nextFeatureId() {
/* 318 */     return getFeatureType().getTypeName() + "." + (this.records + 1);
/*     */   }
/*     */   
/*     */   public void remove() throws IOException {
/* 322 */     if (this.featureReader == null)
/* 323 */       throw new IOException("Writer closed"); 
/* 326 */     if (this.currentFeature == null)
/* 327 */       throw new IOException("Current feature is null"); 
/* 332 */     this.currentFeature = null;
/*     */   }
/*     */   
/*     */   public void write() throws IOException {
/* 336 */     if (this.currentFeature == null)
/* 337 */       throw new IOException("Current feature is null"); 
/* 340 */     if (this.featureReader == null)
/* 341 */       throw new IOException("Writer closed"); 
/* 345 */     Geometry g = (Geometry)this.currentFeature.getDefaultGeometry();
/* 348 */     if (this.guessShapeType)
/*     */       try {
/* 350 */         if (g != null) {
/* 351 */           int dims = JTSUtilities.guessCoorinateDims(g.getCoordinates());
/* 352 */           this.shapeType = JTSUtilities.getShapeType(g, dims);
/*     */         } else {
/* 354 */           this.shapeType = JTSUtilities.getShapeType(this.currentFeature.getType().getGeometryDescriptor());
/*     */         } 
/* 359 */         this.shpWriter.writeHeaders(new Envelope(), this.shapeType, 0, 0);
/* 360 */         this.handler = this.shapeType.getShapeHandler(this.gf);
/* 361 */         this.guessShapeType = false;
/* 362 */       } catch (ShapefileException se) {
/* 363 */         throw new RuntimeException("Unexpected Error", se);
/*     */       }  
/* 368 */     g = JTSUtilities.convertToCollection(g, this.shapeType);
/* 371 */     if (g != null) {
/* 372 */       Envelope b = g.getEnvelopeInternal();
/* 374 */       if (!b.isNull())
/* 375 */         this.bounds.expandToInclude(b); 
/*     */     } 
/* 380 */     if (g != null) {
/* 381 */       this.shapefileLength += this.handler.getLength(g) + 8;
/*     */     } else {
/* 383 */       this.shapefileLength += 12;
/*     */     } 
/* 386 */     this.shpWriter.writeGeometry(g);
/* 389 */     int idx = 0;
/* 391 */     for (int i = 0, ii = this.featureType.getAttributeCount(); i < ii; i++) {
/* 393 */       if (this.writeFlags[i] > 0)
/* 394 */         this.transferCache[idx++] = this.currentFeature.getAttribute(i); 
/*     */     } 
/* 398 */     this.dbfWriter.write(this.transferCache);
/* 401 */     this.records++;
/* 404 */     this.currentFeature = null;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\shapefile\ShapefileFeatureWriter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */