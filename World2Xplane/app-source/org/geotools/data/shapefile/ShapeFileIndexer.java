/*     */ package org.geotools.data.shapefile;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Envelope;
/*     */ import com.vividsolutions.jts.geom.GeometryFactory;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.net.MalformedURLException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import org.geotools.data.shapefile.files.FileWriter;
/*     */ import org.geotools.data.shapefile.files.ShpFileType;
/*     */ import org.geotools.data.shapefile.files.ShpFiles;
/*     */ import org.geotools.data.shapefile.files.StorageFile;
/*     */ import org.geotools.data.shapefile.index.LockTimeoutException;
/*     */ import org.geotools.data.shapefile.index.TreeException;
/*     */ import org.geotools.data.shapefile.index.quadtree.Node;
/*     */ import org.geotools.data.shapefile.index.quadtree.QuadTree;
/*     */ import org.geotools.data.shapefile.index.quadtree.StoreException;
/*     */ import org.geotools.data.shapefile.index.quadtree.fs.FileSystemIndexStore;
/*     */ import org.geotools.data.shapefile.shp.IndexFile;
/*     */ import org.geotools.data.shapefile.shp.ShapefileHeader;
/*     */ import org.geotools.data.shapefile.shp.ShapefileReader;
/*     */ import org.geotools.util.NullProgressListener;
/*     */ import org.geotools.util.logging.Logging;
/*     */ import org.opengis.util.ProgressListener;
/*     */ 
/*     */ class ShapeFileIndexer implements FileWriter {
/*  61 */   private static final Logger LOGGER = Logging.getLogger(ShapeFileIndexer.class);
/*     */   
/*  63 */   private int max = -1;
/*     */   
/*  64 */   private int leafSize = 16;
/*     */   
/*     */   private String byteOrder;
/*     */   
/*     */   private boolean interactive = false;
/*     */   
/*     */   private ShpFiles shpFiles;
/*     */   
/*     */   public static void main(String[] args) throws IOException {
/*  71 */     if (args.length < 1 || (args.length - 1) % 2 != 0)
/*  72 */       usage(); 
/*  75 */     long start = System.currentTimeMillis();
/*  77 */     ShapeFileIndexer idx = new ShapeFileIndexer();
/*  78 */     idx.interactive = true;
/*  80 */     for (int i = 0; i < args.length; i++) {
/*  81 */       if (args[i].equals("-t")) {
/*  84 */         i++;
/*  85 */       } else if (args[i].equals("-M")) {
/*  86 */         idx.setMax(Integer.parseInt(args[++i]));
/*  87 */       } else if (args[i].equals("-s")) {
/*  88 */         idx.setLeafSize(Integer.parseInt(args[++i]));
/*  89 */       } else if (args[i].equals("-b")) {
/*  90 */         idx.setByteOrder(args[++i]);
/*     */       } else {
/*  92 */         if (!args[i].toLowerCase().endsWith(".shp")) {
/*  93 */           System.out.println("File extension must be '.shp'");
/*  94 */           System.exit(1);
/*     */         } 
/*  97 */         idx.setShapeFileName(new ShpFiles(args[i]));
/*     */       } 
/*     */     } 
/*     */     try {
/* 102 */       System.out.print("Indexing ");
/* 104 */       int cnt = idx.index(true, (ProgressListener)new NullProgressListener());
/* 105 */       System.out.println();
/* 106 */       System.out.print(cnt + " features indexed ");
/* 107 */       System.out.println("in " + (System.currentTimeMillis() - start) + "ms.");
/* 109 */       System.out.println();
/* 110 */     } catch (Exception e) {
/* 111 */       e.printStackTrace();
/* 112 */       usage();
/* 113 */       System.exit(1);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void usage() {
/* 118 */     System.out.println("Usage: ShapeFileIndexer -t <QIX> [-M <max tree depth>] [-b <byte order NL | NM>] <shape file>[-s <max number of items in a leaf>]");
/* 123 */     System.out.println();
/* 125 */     System.out.println("Options:");
/* 126 */     System.out.println("\t-t Index type: RTREE or QUADTREE");
/* 127 */     System.out.println();
/* 128 */     System.out.println("Following options apllies only to QUADTREE:");
/* 129 */     System.out.println("\t-b byte order to use: NL = LSB; NM = MSB (default)");
/* 132 */     System.exit(1);
/*     */   }
/*     */   
/*     */   public int index(boolean verbose, ProgressListener listener) throws MalformedURLException, IOException, TreeException, StoreException, LockTimeoutException {
/* 159 */     if (this.shpFiles == null)
/* 160 */       throw new IOException("You have to set a shape file name!"); 
/* 163 */     int cnt = 0;
/* 165 */     ShapefileReader reader = null;
/* 168 */     StorageFile storage = this.shpFiles.getStorageFile(ShpFileType.QIX);
/* 169 */     File treeFile = storage.getFile();
/*     */     try {
/* 172 */       reader = new ShapefileReader(this.shpFiles, true, false, new GeometryFactory());
/* 174 */       if (this.max == -1) {
/* 178 */         int features = reader.getCount(0);
/* 179 */         this.max = 1;
/* 180 */         int nodes = 1;
/* 181 */         while (nodes * this.leafSize < features) {
/* 182 */           this.max++;
/* 183 */           nodes *= 4;
/*     */         } 
/* 185 */         if (this.max < 10)
/* 186 */           this.max = 10; 
/* 189 */         reader.close();
/* 190 */         reader = new ShapefileReader(this.shpFiles, true, false, new GeometryFactory());
/*     */       } 
/* 193 */       cnt = buildQuadTree(reader, treeFile, verbose);
/*     */     } finally {
/* 195 */       if (reader != null)
/* 196 */         reader.close(); 
/*     */     } 
/* 200 */     storage.replaceOriginal();
/* 202 */     return cnt;
/*     */   }
/*     */   
/*     */   private int buildQuadTree(ShapefileReader reader, File file, boolean verbose) throws IOException, StoreException {
/* 207 */     LOGGER.fine("Building quadtree spatial index with depth " + this.max + " for file " + file.getAbsolutePath());
/* 209 */     byte order = 0;
/* 211 */     if (this.byteOrder == null || this.byteOrder.equalsIgnoreCase("NM")) {
/* 212 */       order = 2;
/* 213 */     } else if (this.byteOrder.equalsIgnoreCase("NL")) {
/* 214 */       order = 1;
/*     */     } else {
/* 216 */       throw new StoreException("Asked byte order '" + this.byteOrder + "' must be 'NL' or 'NM'!");
/*     */     } 
/* 220 */     IndexFile shpIndex = new IndexFile(this.shpFiles, false);
/* 221 */     QuadTree tree = null;
/* 222 */     int cnt = 0;
/* 223 */     int numRecs = shpIndex.getRecordCount();
/* 224 */     ShapefileHeader header = reader.getHeader();
/* 225 */     Envelope bounds = new Envelope(header.minX(), header.maxX(), header.minY(), header.maxY());
/* 228 */     tree = new QuadTree(numRecs, this.max, bounds, shpIndex);
/*     */     try {
/* 230 */       ShapefileReader.Record rec = null;
/* 232 */       while (reader.hasNext()) {
/* 233 */         rec = reader.nextRecord();
/* 234 */         tree.insert(cnt++, new Envelope(rec.minX, rec.maxX, rec.minY, rec.maxY));
/* 237 */         if (verbose && cnt % 1000 == 0)
/* 238 */           System.out.print('.'); 
/* 240 */         if (cnt % 100000 == 0)
/* 241 */           System.out.print('\n'); 
/*     */       } 
/* 243 */       if (verbose)
/* 244 */         System.out.println("done"); 
/* 245 */       FileSystemIndexStore store = new FileSystemIndexStore(file, order);
/* 247 */       if (this.leafSize > 0) {
/* 248 */         if (LOGGER.isLoggable(Level.FINE))
/* 249 */           LOGGER.fine("Optimizing the tree (this might take some time)"); 
/* 251 */         optimizeTree(tree, tree.getRoot(), 0, reader, shpIndex);
/* 252 */         if (LOGGER.isLoggable(Level.FINE))
/* 253 */           LOGGER.fine("Tree optimized"); 
/*     */       } 
/* 257 */       if (LOGGER.isLoggable(Level.FINE))
/* 258 */         printStats(tree); 
/* 260 */       store.store(tree);
/*     */     } finally {
/* 262 */       tree.close();
/*     */     } 
/* 264 */     return cnt;
/*     */   }
/*     */   
/*     */   private Node optimizeTree(QuadTree tree, Node node, int level, ShapefileReader reader, IndexFile index) throws StoreException, IOException {
/* 269 */     if (node.getNumShapeIds() > this.leafSize && node.getNumSubNodes() == 0 && level < this.max * 2) {
/* 271 */       int[] shapeIds = node.getShapesId();
/* 272 */       int numShapesId = node.getNumShapeIds();
/* 273 */       node.clean();
/* 276 */       int extraLevels = 2;
/* 277 */       int nodes = 4;
/* 278 */       while (nodes * this.leafSize < numShapesId) {
/* 279 */         extraLevels++;
/* 280 */         nodes *= 4;
/*     */       } 
/* 283 */       for (int j = 0; j < numShapesId; j++) {
/* 284 */         int shapeId = shapeIds[j];
/* 285 */         int offset = index.getOffsetInBytes(shapeId);
/* 286 */         reader.goTo(offset);
/* 287 */         ShapefileReader.Record rec = reader.nextRecord();
/* 288 */         Envelope env = new Envelope(rec.minX, rec.maxX, rec.minY, rec.maxY);
/* 289 */         tree.insert(node, shapeId, env, extraLevels);
/*     */       } 
/*     */     } 
/* 294 */     node.pack();
/*     */     int i;
/* 297 */     for (i = 0; i < node.getNumSubNodes(); i++)
/* 298 */       optimizeTree(tree, node.getSubNode(i), level + 1, reader, index); 
/* 302 */     for (i = 0; i < node.getNumSubNodes(); ) {
/* 303 */       Node child = node.getSubNode(i);
/* 304 */       if (child != null && child.getNumShapeIds() == 0 && child.getNumSubNodes() == 0) {
/* 306 */         node.removeSubNode(child);
/*     */         continue;
/*     */       } 
/* 308 */       i++;
/*     */     } 
/* 314 */     if (node.getNumSubNodes() == 1 && node.getNumShapeIds() == 0) {
/* 315 */       Node subnode = node.getSubNode(0);
/* 316 */       node.clearSubNodes();
/* 317 */       node.setShapesId(subnode);
/* 318 */       node.setBounds(subnode.getBounds());
/* 319 */       for (int j = 0; j < subnode.getNumSubNodes(); j++)
/* 320 */         node.addSubNode(subnode.getSubNode(j)); 
/*     */     } else {
/* 324 */       Envelope bounds = new Envelope();
/* 325 */       if (node.getNumShapeIds() > 0) {
/* 326 */         int[] shapeIds = node.getShapesId();
/* 327 */         for (int k = 0; k < shapeIds.length; k++) {
/* 328 */           int shapeId = shapeIds[k];
/* 329 */           int offset = index.getOffsetInBytes(shapeId);
/* 330 */           reader.goTo(offset);
/* 331 */           ShapefileReader.Record rec = reader.nextRecord();
/* 332 */           Envelope env = new Envelope(rec.minX, rec.maxX, rec.minY, rec.maxY);
/* 333 */           bounds.expandToInclude(env);
/*     */         } 
/*     */       } 
/* 336 */       if (node.getNumSubNodes() > 0)
/* 337 */         for (int k = 0; k < node.getNumSubNodes(); k++)
/* 338 */           bounds.expandToInclude(node.getSubNode(k).getBounds());  
/* 341 */       node.setBounds(bounds);
/* 344 */       int count = node.getNumShapeIds();
/*     */       int j;
/* 345 */       for (j = 0; j < node.getNumSubNodes(); j++) {
/* 346 */         Node child = node.getSubNode(j);
/* 347 */         if (child.getNumSubNodes() > 0) {
/* 348 */           count = Integer.MAX_VALUE;
/*     */           break;
/*     */         } 
/* 351 */         count += child.getNumShapeIds();
/*     */       } 
/* 354 */       if (count < this.leafSize) {
/* 355 */         for (j = 0; j < node.getNumSubNodes(); j++) {
/* 356 */           Node child = node.getSubNode(j);
/* 357 */           int[] shapesId = child.getShapesId();
/* 358 */           for (int k = 0; k < child.getNumShapeIds(); k++)
/* 359 */             node.addShapeId(shapesId[k]); 
/*     */         } 
/* 362 */         node.clearSubNodes();
/*     */       } 
/*     */     } 
/* 366 */     return node;
/*     */   }
/*     */   
/*     */   private void printStats(QuadTree tree) throws StoreException {
/* 370 */     Map<Integer, Integer> stats = new HashMap<Integer, Integer>();
/* 371 */     gatherStats(tree.getRoot(), stats);
/* 373 */     List<Integer> nums = new ArrayList<Integer>(stats.keySet());
/* 374 */     Collections.sort(nums);
/* 375 */     LOGGER.log(Level.FINE, "Index statistics");
/* 376 */     for (Integer num : nums)
/* 377 */       LOGGER.log(Level.FINE, num + " -> " + stats.get(num)); 
/*     */   }
/*     */   
/*     */   void gatherStats(Node node, Map<Integer, Integer> stats) throws StoreException {
/* 383 */     int num = node.getNumShapeIds();
/* 384 */     Integer count = stats.get(Integer.valueOf(num));
/* 385 */     if (count == null) {
/* 386 */       stats.put(Integer.valueOf(num), Integer.valueOf(1));
/*     */     } else {
/* 388 */       stats.put(Integer.valueOf(num), Integer.valueOf(count.intValue() + 1));
/*     */     } 
/* 390 */     for (int i = 0; i < node.getNumSubNodes(); i++)
/* 391 */       gatherStats(node.getSubNode(i), stats); 
/*     */   }
/*     */   
/*     */   public void setMax(int i) {
/* 401 */     this.max = i;
/*     */   }
/*     */   
/*     */   public void setShapeFileName(ShpFiles shpFiles) {
/* 410 */     this.shpFiles = shpFiles;
/*     */   }
/*     */   
/*     */   public void setByteOrder(String byteOrder) {
/* 421 */     this.byteOrder = byteOrder;
/*     */   }
/*     */   
/*     */   public String id() {
/* 425 */     return getClass().getName();
/*     */   }
/*     */   
/*     */   public int getLeafSize() {
/* 429 */     return this.leafSize;
/*     */   }
/*     */   
/*     */   public void setLeafSize(int leafSize) {
/* 433 */     this.leafSize = leafSize;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\shapefile\ShapeFileIndexer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */