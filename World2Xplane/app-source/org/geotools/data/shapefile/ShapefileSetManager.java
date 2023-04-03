/*     */ package org.geotools.data.shapefile;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.GeometryFactory;
/*     */ import java.io.IOException;
/*     */ import org.geotools.data.DataSourceException;
/*     */ import org.geotools.data.PrjFileReader;
/*     */ import org.geotools.data.shapefile.dbf.DbaseFileReader;
/*     */ import org.geotools.data.shapefile.dbf.IndexedDbaseFileReader;
/*     */ import org.geotools.data.shapefile.files.FileReader;
/*     */ import org.geotools.data.shapefile.files.ShpFileType;
/*     */ import org.geotools.data.shapefile.files.ShpFiles;
/*     */ import org.geotools.data.shapefile.shp.IndexFile;
/*     */ import org.geotools.data.shapefile.shp.ShapefileException;
/*     */ import org.geotools.data.shapefile.shp.ShapefileReader;
/*     */ import org.opengis.referencing.FactoryException;
/*     */ 
/*     */ class ShapefileSetManager implements FileReader {
/*     */   ShpFiles shpFiles;
/*     */   
/*     */   ShapefileDataStore store;
/*     */   
/*     */   public ShapefileSetManager(ShpFiles shpFiles, ShapefileDataStore store) {
/*  37 */     this.shpFiles = shpFiles;
/*  38 */     this.store = store;
/*     */   }
/*     */   
/*     */   protected ShapefileReader openShapeReader(GeometryFactory gf, boolean onlyRandomAccess) throws IOException {
/*     */     try {
/*  51 */       return new ShapefileReader(this.shpFiles, true, this.store.isMemoryMapped(), gf, onlyRandomAccess);
/*  52 */     } catch (ShapefileException se) {
/*  53 */       throw new DataSourceException("Error creating ShapefileReader", se);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected DbaseFileReader openDbfReader(boolean indexed) throws IOException {
/*  65 */     if (this.shpFiles.get(ShpFileType.DBF) == null)
/*  66 */       return null; 
/*  69 */     if (this.shpFiles.isLocal() && !this.shpFiles.exists(ShpFileType.DBF))
/*  70 */       return null; 
/*     */     try {
/*  74 */       if (indexed)
/*  75 */         return (DbaseFileReader)new IndexedDbaseFileReader(this.shpFiles, this.store.isMemoryMapped(), this.store.getCharset(), this.store.getTimeZone()); 
/*  78 */       return new DbaseFileReader(this.shpFiles, this.store.isMemoryMapped(), this.store.getCharset(), this.store.getTimeZone());
/*  81 */     } catch (IOException e) {
/*  83 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   protected PrjFileReader openPrjReader() throws IOException, FactoryException {
/*  97 */     if (this.shpFiles.get(ShpFileType.PRJ) == null)
/*  98 */       return null; 
/* 101 */     if (this.shpFiles.isLocal() && !this.shpFiles.exists(ShpFileType.PRJ))
/* 102 */       return null; 
/*     */     try {
/* 106 */       return new PrjFileReader(this.shpFiles.getReadChannel(ShpFileType.PRJ, this));
/* 107 */     } catch (IOException e) {
/* 109 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   protected IndexFile openIndexFile() throws IOException {
/* 121 */     if (this.shpFiles.get(ShpFileType.SHX) == null)
/* 122 */       return null; 
/* 125 */     if (this.shpFiles.isLocal() && !this.shpFiles.exists(ShpFileType.SHX))
/* 126 */       return null; 
/*     */     try {
/* 130 */       return new IndexFile(this.shpFiles, this.store.isMemoryMapped());
/* 131 */     } catch (IOException e) {
/* 133 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public String id() {
/* 139 */     return getClass().getName();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\shapefile\ShapefileSetManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */