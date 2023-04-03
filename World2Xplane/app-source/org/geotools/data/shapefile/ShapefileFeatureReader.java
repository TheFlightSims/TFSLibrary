/*     */ package org.geotools.data.shapefile;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Envelope;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import java.io.IOException;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.NoSuchElementException;
/*     */ import org.geotools.data.FeatureReader;
/*     */ import org.geotools.data.shapefile.dbf.DbaseFileHeader;
/*     */ import org.geotools.data.shapefile.dbf.DbaseFileReader;
/*     */ import org.geotools.data.shapefile.fid.IndexedFidReader;
/*     */ import org.geotools.data.shapefile.shp.ShapeType;
/*     */ import org.geotools.data.shapefile.shp.ShapefileReader;
/*     */ import org.geotools.feature.simple.SimpleFeatureBuilder;
/*     */ import org.geotools.renderer.ScreenMap;
/*     */ import org.opengis.feature.Feature;
/*     */ import org.opengis.feature.simple.SimpleFeature;
/*     */ import org.opengis.feature.simple.SimpleFeatureType;
/*     */ import org.opengis.feature.type.AttributeDescriptor;
/*     */ import org.opengis.feature.type.FeatureType;
/*     */ 
/*     */ class ShapefileFeatureReader implements FeatureReader<SimpleFeatureType, SimpleFeature> {
/*     */   SimpleFeatureType schema;
/*     */   
/*     */   ShapefileReader shp;
/*     */   
/*     */   DbaseFileReader dbf;
/*     */   
/*     */   int[] dbfindexes;
/*     */   
/*     */   SimpleFeatureBuilder builder;
/*     */   
/*     */   SimpleFeature nextFeature;
/*     */   
/*     */   Envelope targetBBox;
/*     */   
/*     */   double simplificationDistance;
/*     */   
/*     */   ScreenMap screenMap;
/*     */   
/*     */   StringBuffer idxBuffer;
/*     */   
/*     */   int idxBaseLen;
/*     */   
/*     */   IndexedFidReader fidReader;
/*     */   
/*     */   public ShapefileFeatureReader(SimpleFeatureType schema, ShapefileReader shp, DbaseFileReader dbf, IndexedFidReader fidReader) throws IOException {
/*  70 */     this.schema = schema;
/*  71 */     this.shp = shp;
/*  72 */     this.dbf = dbf;
/*  73 */     this.fidReader = fidReader;
/*  74 */     this.builder = new SimpleFeatureBuilder(schema);
/*  76 */     this.idxBuffer = new StringBuffer(schema.getTypeName());
/*  77 */     this.idxBuffer.append('.');
/*  78 */     this.idxBaseLen = this.idxBuffer.length();
/*  80 */     if (dbf != null) {
/*  83 */       List<AttributeDescriptor> atts = schema.getAttributeDescriptors();
/*  84 */       this.dbfindexes = new int[atts.size()];
/*  85 */       DbaseFileHeader head = dbf.getHeader();
/*  86 */       for (int i = 0; i < atts.size(); i++) {
/*  87 */         AttributeDescriptor att = atts.get(i);
/*  88 */         if (att instanceof org.opengis.feature.type.GeometryDescriptor) {
/*  89 */           this.dbfindexes[i] = -1;
/*     */         } else {
/*  91 */           String attName = att.getLocalName();
/*  92 */           int count = 0;
/*  93 */           Map<Object, Object> userData = att.getUserData();
/*  94 */           if (userData.get("original") != null) {
/*  95 */             attName = (String)userData.get("original");
/*  96 */             count = ((Integer)userData.get("count")).intValue();
/*     */           } 
/* 100 */           boolean found = false;
/* 101 */           for (int j = 0; j < head.getNumFields(); j++) {
/* 102 */             if (head.getFieldName(j).equals(attName) && count-- <= 0) {
/* 103 */               this.dbfindexes[i] = j;
/* 104 */               found = true;
/*     */               break;
/*     */             } 
/*     */           } 
/* 108 */           if (!found)
/* 109 */             throw new IOException("Could not find attribute " + attName + " (mul count: " + count); 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public SimpleFeatureType getFeatureType() {
/* 119 */     return this.schema;
/*     */   }
/*     */   
/*     */   public SimpleFeature next() throws IOException, IllegalArgumentException, NoSuchElementException {
/* 125 */     if (hasNext()) {
/* 126 */       SimpleFeature result = this.nextFeature;
/* 127 */       this.nextFeature = null;
/* 128 */       return result;
/*     */     } 
/* 130 */     throw new NoSuchElementException("hasNext() returned false");
/*     */   }
/*     */   
/*     */   boolean filesHaveMore() throws IOException {
/* 141 */     if (this.dbf == null)
/* 142 */       return this.shp.hasNext(); 
/* 144 */     boolean dbfHasNext = this.dbf.hasNext();
/* 145 */     boolean shpHasNext = this.shp.hasNext();
/* 146 */     if (dbfHasNext && shpHasNext)
/* 147 */       return true; 
/* 148 */     if (dbfHasNext || shpHasNext)
/* 149 */       throw new IOException((shpHasNext ? "Shp" : "Dbf") + " has extra record"); 
/* 151 */     return false;
/*     */   }
/*     */   
/*     */   public boolean hasNext() throws IOException {
/* 158 */     while (this.nextFeature == null && filesHaveMore()) {
/* 159 */       ShapefileReader.Record record = this.shp.nextRecord();
/* 162 */       Envelope envelope = record.envelope();
/* 163 */       boolean skip = false;
/* 164 */       Geometry geometry = null;
/* 165 */       if (this.schema.getGeometryDescriptor() != null)
/* 167 */         if (this.targetBBox != null && !this.targetBBox.isNull() && !this.targetBBox.intersects(envelope)) {
/* 168 */           skip = true;
/* 170 */         } else if (this.simplificationDistance > 0.0D && envelope.getWidth() < this.simplificationDistance && envelope.getHeight() < this.simplificationDistance) {
/*     */           try {
/* 173 */             if (this.screenMap != null && this.screenMap.checkAndSet(envelope)) {
/* 174 */               geometry = null;
/* 175 */               skip = true;
/*     */             } else {
/* 180 */               geometry = (Geometry)record.getSimplifiedShape(this.screenMap);
/*     */             } 
/* 182 */           } catch (Exception e) {
/* 183 */             geometry = (Geometry)record.getSimplifiedShape();
/*     */           } 
/*     */         } else {
/* 187 */           geometry = (Geometry)record.shape();
/*     */         }  
/* 191 */       if (!skip) {
/*     */         DbaseFileReader.Row row;
/* 194 */         if (this.dbf != null) {
/* 195 */           row = this.dbf.readRow();
/* 196 */           if (row.isDeleted())
/*     */             continue; 
/*     */         } else {
/* 200 */           row = null;
/*     */         } 
/* 203 */         this.nextFeature = buildFeature(record.number, geometry, row);
/*     */         continue;
/*     */       } 
/* 205 */       if (this.dbf != null)
/* 206 */         this.dbf.skip(); 
/*     */     } 
/* 211 */     return (this.nextFeature != null);
/*     */   }
/*     */   
/*     */   SimpleFeature buildFeature(int number, Geometry geometry, DbaseFileReader.Row row) throws IOException {
/* 215 */     if (this.dbfindexes != null) {
/* 216 */       for (int i = 0; i < this.dbfindexes.length; i++) {
/* 217 */         if (this.dbfindexes[i] == -1) {
/* 218 */           this.builder.add(geometry);
/*     */         } else {
/* 220 */           this.builder.add(row.read(this.dbfindexes[i]));
/*     */         } 
/*     */       } 
/* 223 */     } else if (geometry != null) {
/* 224 */       this.builder.add(geometry);
/*     */     } 
/* 227 */     String featureId = buildFeatureId(number);
/* 228 */     return this.builder.buildFeature(featureId);
/*     */   }
/*     */   
/*     */   protected String buildFeatureId(int number) throws IOException {
/* 232 */     if (this.fidReader == null) {
/* 233 */       this.idxBuffer.delete(this.idxBaseLen, this.idxBuffer.length());
/* 234 */       this.idxBuffer.append(number);
/* 235 */       return this.idxBuffer.toString();
/*     */     } 
/* 237 */     this.fidReader.goTo((number - 1));
/* 238 */     return this.fidReader.next();
/*     */   }
/*     */   
/*     */   public void close() throws IOException {
/*     */     try {
/* 245 */       if (this.shp != null)
/* 246 */         this.shp.close(); 
/*     */     } finally {
/*     */       try {
/* 250 */         if (this.dbf != null)
/* 251 */           this.dbf.close(); 
/*     */       } finally {
/*     */         try {
/* 255 */           if (this.fidReader != null)
/* 256 */             this.fidReader.close(); 
/*     */         } finally {
/* 259 */           this.shp = null;
/* 260 */           this.dbf = null;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setTargetBBox(Envelope targetBBox) {
/* 273 */     this.targetBBox = targetBBox;
/*     */   }
/*     */   
/*     */   public void setSimplificationDistance(double simplificationDistance) {
/* 282 */     this.simplificationDistance = simplificationDistance;
/*     */   }
/*     */   
/*     */   public void setScreenMap(ScreenMap screenMap) {
/* 291 */     this.screenMap = screenMap;
/*     */   }
/*     */   
/*     */   void disableShxUsage() throws IOException {
/* 295 */     this.shp.disableShxUsage();
/*     */   }
/*     */   
/*     */   ShapeType getShapeType() {
/* 300 */     return this.shp.getHeader().getShapeType();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\shapefile\ShapefileFeatureReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */