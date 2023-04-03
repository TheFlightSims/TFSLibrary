/*     */ package org.geotools.data.shapefile;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Envelope;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import java.io.IOException;
/*     */ import org.geotools.data.shapefile.dbf.DbaseFileReader;
/*     */ import org.geotools.data.shapefile.dbf.IndexedDbaseFileReader;
/*     */ import org.geotools.data.shapefile.fid.IndexedFidReader;
/*     */ import org.geotools.data.shapefile.index.CloseableIterator;
/*     */ import org.geotools.data.shapefile.index.Data;
/*     */ import org.geotools.data.shapefile.shp.ShapefileReader;
/*     */ import org.opengis.feature.simple.SimpleFeatureType;
/*     */ 
/*     */ class IndexedShapefileFeatureReader extends ShapefileFeatureReader {
/*     */   protected CloseableIterator<Data> goodRecs;
/*     */   
/*     */   private Data next;
/*     */   
/*     */   private IndexedFidReader fidReader;
/*     */   
/*     */   public IndexedShapefileFeatureReader(SimpleFeatureType schema, ShapefileReader shp, DbaseFileReader dbf, IndexedFidReader fidReader, CloseableIterator<Data> goodRecs) throws IOException {
/*  60 */     super(schema, shp, dbf, fidReader);
/*  61 */     this.goodRecs = goodRecs;
/*  62 */     this.fidReader = fidReader;
/*     */   }
/*     */   
/*     */   public void close() throws IOException {
/*     */     try {
/*  67 */       super.close();
/*     */     } finally {
/*  69 */       if (this.goodRecs != null)
/*  70 */         this.goodRecs.close(); 
/*  72 */       this.goodRecs = null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean hasNext() throws IOException {
/*  77 */     while (this.nextFeature == null && this.goodRecs.hasNext()) {
/*     */       DbaseFileReader.Row row;
/*  78 */       this.next = (Data)this.goodRecs.next();
/*  80 */       Long l = (Long)this.next.getValue(1);
/*  81 */       this.shp.goTo((int)l.longValue());
/*  83 */       ShapefileReader.Record record = this.shp.nextRecord();
/*  86 */       Envelope envelope = record.envelope();
/*  87 */       Geometry geometry = null;
/*  89 */       if (this.schema.getGeometryDescriptor() != null) {
/*  91 */         if (this.targetBBox != null && !this.targetBBox.isNull() && !this.targetBBox.intersects(envelope))
/*     */           continue; 
/*  94 */         if (this.simplificationDistance > 0.0D && envelope.getWidth() < this.simplificationDistance && envelope.getHeight() < this.simplificationDistance) {
/*     */           try {
/*  97 */             if (this.screenMap != null && this.screenMap.checkAndSet(envelope))
/*     */               continue; 
/* 103 */             geometry = (Geometry)record.getSimplifiedShape(this.screenMap);
/* 105 */           } catch (Exception e) {
/* 106 */             geometry = (Geometry)record.getSimplifiedShape();
/*     */           } 
/*     */         } else {
/* 110 */           geometry = (Geometry)record.shape();
/*     */         } 
/*     */       } 
/* 116 */       if (this.dbf != null) {
/* 117 */         ((IndexedDbaseFileReader)this.dbf).goTo(record.number);
/* 118 */         row = this.dbf.readRow();
/*     */       } else {
/* 120 */         row = null;
/*     */       } 
/* 123 */       this.nextFeature = buildFeature(record.number, geometry, row);
/*     */     } 
/* 126 */     return (this.nextFeature != null);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\shapefile\IndexedShapefileFeatureReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */