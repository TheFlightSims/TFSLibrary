/*     */ package org.geotools.data.shapefile;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Envelope;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.net.URL;
/*     */ import java.nio.channels.ReadableByteChannel;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.TreeSet;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import org.geotools.data.DataSourceException;
/*     */ import org.geotools.data.DataUtilities;
/*     */ import org.geotools.data.shapefile.fid.FidIndexer;
/*     */ import org.geotools.data.shapefile.fid.IndexedFidReader;
/*     */ import org.geotools.data.shapefile.files.FileReader;
/*     */ import org.geotools.data.shapefile.files.FileWriter;
/*     */ import org.geotools.data.shapefile.files.ShpFileType;
/*     */ import org.geotools.data.shapefile.files.ShpFiles;
/*     */ import org.geotools.data.shapefile.index.CachedQuadTree;
/*     */ import org.geotools.data.shapefile.index.CloseableIterator;
/*     */ import org.geotools.data.shapefile.index.Data;
/*     */ import org.geotools.data.shapefile.index.DataDefinition;
/*     */ import org.geotools.data.shapefile.index.TreeException;
/*     */ import org.geotools.data.shapefile.index.quadtree.QuadTree;
/*     */ import org.geotools.data.shapefile.index.quadtree.StoreException;
/*     */ import org.geotools.data.shapefile.shp.IndexFile;
/*     */ import org.geotools.util.NullProgressListener;
/*     */ import org.geotools.util.logging.Logging;
/*     */ import org.opengis.filter.Id;
/*     */ import org.opengis.filter.identity.Identifier;
/*     */ import org.opengis.util.ProgressListener;
/*     */ 
/*     */ class IndexManager {
/*  45 */   static final Logger LOGGER = Logging.getLogger(IndexManager.class);
/*     */   
/*     */   static final int DEFAULT_MAX_QIX_CACHE_SIZE;
/*     */   
/*     */   ShpFiles shpFiles;
/*     */   
/*  51 */   int maxQixCacheSize = DEFAULT_MAX_QIX_CACHE_SIZE;
/*     */   
/*     */   CachedQuadTree cachedTree;
/*     */   
/*     */   ShapefileDataStore store;
/*     */   
/*  60 */   FileWriter writer = new FileWriter() {
/*     */       public String id() {
/*  64 */         return "ShapefileDataStore-" + IndexManager.this.store.getTypeName().getLocalPart();
/*     */       }
/*     */     };
/*     */   
/*     */   static {
/*  69 */     int max = -1;
/*     */     try {
/*  71 */       String smax = System.getProperty("org.geotools.shapefile.maxQixCacheSize");
/*  72 */       if (smax != null)
/*  73 */         max = Integer.parseInt(smax); 
/*  75 */     } catch (Throwable t) {
/*  76 */       LOGGER.log(Level.SEVERE, "Could not set the max qix cache size", t);
/*     */     } 
/*  78 */     DEFAULT_MAX_QIX_CACHE_SIZE = max;
/*     */   }
/*     */   
/*     */   public IndexManager(ShpFiles shpFiles, ShapefileDataStore store) {
/*  82 */     this.shpFiles = shpFiles;
/*  83 */     this.store = store;
/*     */   }
/*     */   
/*     */   public boolean createSpatialIndex(boolean force) {
/*     */     try {
/*  95 */       if (this.shpFiles.isLocal() && (isIndexStale(ShpFileType.QIX) || force)) {
/*  96 */         ShapefileDataStoreFactory.LOGGER.fine("Creating spatial index for " + this.shpFiles.get(ShpFileType.SHP));
/*  99 */         ShapeFileIndexer indexer = new ShapeFileIndexer();
/* 100 */         indexer.setShapeFileName(this.shpFiles);
/* 101 */         indexer.index(false, (ProgressListener)new NullProgressListener());
/* 103 */         return true;
/*     */       } 
/* 105 */     } catch (Throwable t) {
/* 106 */       ShapefileDataStoreFactory.LOGGER.log(Level.SEVERE, t.getLocalizedMessage(), t);
/*     */     } 
/* 108 */     return false;
/*     */   }
/*     */   
/*     */   boolean hasFidIndex(boolean createIfMissing) {
/* 117 */     if (isIndexUseable(ShpFileType.FIX))
/* 118 */       return true; 
/* 120 */     if (this.shpFiles.isLocal() && (this.shpFiles.exists(ShpFileType.FIX) || createIfMissing))
/* 121 */       return createFidIndex(); 
/* 123 */     return false;
/*     */   }
/*     */   
/*     */   public boolean createFidIndex() {
/*     */     try {
/* 131 */       FidIndexer.generate(this.shpFiles);
/* 132 */       return true;
/* 133 */     } catch (IOException e) {
/* 134 */       LOGGER.log(Level.WARNING, "Failed to create fid index");
/* 135 */       return false;
/*     */     } 
/*     */   }
/*     */   
/*     */   boolean isIndexUseable(ShpFileType indexType) {
/* 146 */     if (this.shpFiles.isLocal()) {
/* 147 */       if (isIndexStale(indexType) || !this.shpFiles.exists(indexType))
/* 148 */         return false; 
/*     */     } else {
/* 152 */       ReadableByteChannel read = null;
/*     */       try {
/* 154 */         read = this.shpFiles.getReadChannel(indexType, (FileReader)this.writer);
/* 155 */       } catch (IOException e) {
/* 156 */         return false;
/*     */       } finally {
/* 158 */         if (read != null)
/*     */           try {
/* 160 */             read.close();
/* 161 */           } catch (IOException e) {
/* 162 */             ShapefileDataStoreFactory.LOGGER.log(Level.WARNING, "could not close stream", e);
/*     */           }  
/*     */       } 
/*     */     } 
/* 169 */     return true;
/*     */   }
/*     */   
/*     */   boolean isSpatialIndexAvailable() {
/* 179 */     return (this.shpFiles.isLocal() && this.shpFiles.exists(ShpFileType.QIX));
/*     */   }
/*     */   
/*     */   boolean isIndexStale(ShpFileType indexType) {
/* 190 */     if (!this.shpFiles.isLocal())
/* 191 */       throw new IllegalStateException("This method only applies if the files are local and the file can be created"); 
/* 194 */     URL indexURL = this.shpFiles.acquireRead(indexType, (FileReader)this.writer);
/* 195 */     URL shpURL = this.shpFiles.acquireRead(ShpFileType.SHP, (FileReader)this.writer);
/*     */     try {
/* 198 */       if (indexURL == null)
/* 199 */         return true; 
/* 204 */       if (!this.shpFiles.exists(ShpFileType.SHX) || !this.shpFiles.exists(ShpFileType.SHP))
/* 205 */         return false; 
/* 208 */       File indexFile = DataUtilities.urlToFile(indexURL);
/* 209 */       File shpFile = DataUtilities.urlToFile(shpURL);
/* 210 */       long indexLastModified = indexFile.lastModified();
/* 211 */       long shpLastModified = shpFile.lastModified();
/* 212 */       boolean shpChangedMoreRecently = (indexLastModified < shpLastModified);
/* 213 */       return (!indexFile.exists() || shpChangedMoreRecently);
/*     */     } finally {
/* 215 */       if (shpURL != null)
/* 216 */         this.shpFiles.unlockRead(shpURL, (FileReader)this.writer); 
/* 218 */       if (indexURL != null)
/* 219 */         this.shpFiles.unlockRead(indexURL, (FileReader)this.writer); 
/*     */     } 
/*     */   }
/*     */   
/*     */   List<Data> queryFidIndex(Id fidFilter) throws IOException {
/* 235 */     TreeSet<Identifier> idsSet = new TreeSet<Identifier>(new IdentifierComparator(this.store.getTypeName().getLocalPart()));
/* 236 */     idsSet.addAll(fidFilter.getIdentifiers());
/* 238 */     IndexedFidReader reader = new IndexedFidReader(this.shpFiles);
/* 240 */     List<Data> records = new ArrayList<Data>(idsSet.size());
/*     */     try {
/* 242 */       IndexFile shx = this.store.shpManager.openIndexFile();
/*     */       try {
/* 245 */         DataDefinition def = new DataDefinition("US-ASCII");
/* 246 */         def.addField(Integer.class);
/* 247 */         def.addField(Long.class);
/* 248 */         for (Identifier identifier : idsSet) {
/* 249 */           String fid = identifier.toString();
/* 250 */           long recno = reader.findFid(fid);
/* 251 */           if (recno == -1L) {
/* 252 */             if (LOGGER.isLoggable(Level.FINEST))
/* 253 */               LOGGER.finest("fid " + fid + " not found in index, continuing with next queried fid..."); 
/*     */             continue;
/*     */           } 
/*     */           try {
/* 259 */             Data data = new Data(def);
/* 260 */             data.addValue(new Integer((int)recno + 1));
/* 261 */             data.addValue(new Long(shx.getOffsetInBytes((int)recno)));
/* 262 */             if (LOGGER.isLoggable(Level.FINEST))
/* 263 */               LOGGER.finest("fid " + fid + " found for record #" + data.getValue(0) + " at index file offset " + data.getValue(1)); 
/* 266 */             records.add(data);
/* 267 */           } catch (Exception e) {
/* 268 */             IOException exception = new IOException();
/* 269 */             exception.initCause(e);
/* 270 */             throw exception;
/*     */           } 
/*     */         } 
/*     */       } finally {
/* 274 */         shx.close();
/*     */       } 
/*     */     } finally {
/* 277 */       reader.close();
/*     */     } 
/* 280 */     return records;
/*     */   }
/*     */   
/*     */   protected CloseableIterator<Data> querySpatialIndex(Envelope bbox) throws DataSourceException, IOException, TreeException {
/* 295 */     CloseableIterator<Data> tmp = null;
/* 298 */     createSpatialIndex(false);
/* 300 */     if (this.cachedTree == null) {
/* 301 */       boolean canCache = false;
/* 302 */       URL treeURL = this.shpFiles.acquireRead(ShpFileType.QIX, (FileReader)this.writer);
/*     */       try {
/* 304 */         File treeFile = DataUtilities.urlToFile(treeURL);
/* 306 */         if (treeFile != null && treeFile.exists() && treeFile.length() < (1024 * this.maxQixCacheSize))
/* 307 */           canCache = true; 
/*     */       } finally {
/* 310 */         this.shpFiles.unlockRead(treeURL, (FileReader)this.writer);
/*     */       } 
/* 313 */       if (canCache) {
/* 314 */         QuadTree quadTree = openQuadTree();
/* 315 */         if (quadTree != null) {
/* 316 */           LOGGER.warning("Experimental: loading in memory the quadtree for " + this.shpFiles.get(ShpFileType.SHP));
/* 318 */           this.cachedTree = new CachedQuadTree(quadTree);
/* 319 */           quadTree.close();
/*     */         } 
/*     */       } 
/*     */     } 
/* 323 */     if (this.cachedTree != null) {
/* 324 */       if (!bbox.contains(this.cachedTree.getBounds()))
/* 325 */         return this.cachedTree.search(bbox); 
/* 327 */       return null;
/*     */     } 
/*     */     try {
/* 331 */       QuadTree quadTree = openQuadTree();
/* 332 */       if (quadTree != null && !bbox.contains(quadTree.getRoot().getBounds()))
/* 333 */         tmp = quadTree.search(bbox); 
/* 335 */       if (tmp == null && quadTree != null)
/* 336 */         quadTree.close(); 
/* 338 */     } catch (Exception e) {
/* 339 */       throw new DataSourceException("Error querying QuadTree", e);
/*     */     } 
/* 343 */     return tmp;
/*     */   }
/*     */   
/*     */   protected QuadTree openQuadTree() throws StoreException {
/* 354 */     if (!this.shpFiles.isLocal())
/* 355 */       return null; 
/* 357 */     URL treeURL = this.shpFiles.acquireRead(ShpFileType.QIX, (FileReader)this.writer);
/*     */     try {
/* 359 */       File treeFile = DataUtilities.urlToFile(treeURL);
/* 361 */       if (!treeFile.exists() || treeFile.length() == 0L)
/* 362 */         return null; 
/*     */     } finally {
/* 372 */       this.shpFiles.unlockRead(treeURL, (FileReader)this.writer);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void dispose() {
/* 377 */     this.cachedTree = null;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\shapefile\IndexManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */