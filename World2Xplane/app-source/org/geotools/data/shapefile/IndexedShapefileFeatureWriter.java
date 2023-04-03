/*     */ package org.geotools.data.shapefile;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.net.URL;
/*     */ import java.nio.charset.Charset;
/*     */ import java.util.TimeZone;
/*     */ import java.util.logging.Level;
/*     */ import org.geotools.data.DataUtilities;
/*     */ import org.geotools.data.shapefile.fid.FidIndexer;
/*     */ import org.geotools.data.shapefile.fid.IndexedFidWriter;
/*     */ import org.geotools.data.shapefile.files.FileWriter;
/*     */ import org.geotools.data.shapefile.files.ShpFileType;
/*     */ import org.geotools.data.shapefile.files.StorageFile;
/*     */ import org.opengis.feature.Feature;
/*     */ import org.opengis.feature.simple.SimpleFeature;
/*     */ 
/*     */ class IndexedShapefileFeatureWriter extends ShapefileFeatureWriter implements FileWriter {
/*     */   private IndexedFidWriter fidWriter;
/*     */   
/*     */   private String currentFid;
/*     */   
/*     */   private IndexManager indexes;
/*     */   
/*     */   public IndexedShapefileFeatureWriter(IndexManager indexes, ShapefileFeatureReader featureReader, Charset charset, TimeZone timeZone) throws IOException {
/*  53 */     super(indexes.shpFiles, featureReader, charset, timeZone);
/*  54 */     this.indexes = indexes;
/*  55 */     if (!indexes.shpFiles.isLocal()) {
/*  56 */       this.fidWriter = IndexedFidWriter.EMPTY_WRITER;
/*     */     } else {
/*  58 */       StorageFile storageFile = this.shpFiles.getStorageFile(ShpFileType.FIX);
/*  59 */       this.storageFiles.put(ShpFileType.FIX, storageFile);
/*  60 */       this.fidWriter = new IndexedFidWriter(this.shpFiles, storageFile);
/*     */     } 
/*     */   }
/*     */   
/*     */   public SimpleFeature next() throws IOException {
/*  67 */     if (this.featureReader == null)
/*  68 */       throw new IOException("Writer closed"); 
/*  72 */     if (this.currentFeature != null)
/*  73 */       write(); 
/*  76 */     long next = this.fidWriter.next();
/*  77 */     this.currentFid = getFeatureType().getTypeName() + "." + next;
/*  78 */     SimpleFeature feature = super.next();
/*  79 */     return feature;
/*     */   }
/*     */   
/*     */   protected String nextFeatureId() {
/*  84 */     return this.currentFid;
/*     */   }
/*     */   
/*     */   public void remove() throws IOException {
/*  89 */     this.fidWriter.remove();
/*  90 */     super.remove();
/*     */   }
/*     */   
/*     */   public void write() throws IOException {
/*  95 */     this.fidWriter.write();
/*  96 */     super.write();
/*     */   }
/*     */   
/*     */   public void close() throws IOException {
/* 103 */     super.close();
/* 104 */     this.fidWriter.close();
/*     */     try {
/* 107 */       if (this.shpFiles.isLocal()) {
/* 108 */         if (this.indexes.isIndexStale(ShpFileType.FIX))
/* 109 */           FidIndexer.generate(this.shpFiles); 
/* 112 */         deleteFile(ShpFileType.QIX);
/*     */       } 
/* 114 */     } catch (Throwable e) {
/* 115 */       ShapefileDataStoreFactory.LOGGER.log(Level.WARNING, "Error creating Spatial index", e);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void doClose() throws IOException {
/* 121 */     super.doClose();
/*     */     try {
/* 123 */       this.fidWriter.close();
/* 124 */     } catch (Throwable e) {
/* 125 */       ShapefileDataStoreFactory.LOGGER.log(Level.WARNING, "Error creating Feature ID index", e);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void deleteFile(ShpFileType shpFileType) {
/* 131 */     URL url = this.shpFiles.acquireWrite(shpFileType, this);
/*     */     try {
/* 133 */       File toDelete = DataUtilities.urlToFile(url);
/* 135 */       if (toDelete.exists())
/* 136 */         toDelete.delete(); 
/*     */     } finally {
/* 139 */       this.shpFiles.unlockWrite(url, this);
/*     */     } 
/*     */   }
/*     */   
/*     */   public String id() {
/* 144 */     return getClass().getName();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\shapefile\IndexedShapefileFeatureWriter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */