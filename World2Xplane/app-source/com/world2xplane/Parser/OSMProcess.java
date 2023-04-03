/*     */ package com.world2xplane.Parser;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.GeometryFactory;
/*     */ import com.world2xplane.DataStore.DataStore;
/*     */ import com.world2xplane.DataStore.JDBMStore;
/*     */ import com.world2xplane.DataStore.MemoryStore;
/*     */ import com.world2xplane.Network.NetworkItem;
/*     */ import com.world2xplane.Rules.GeneratorStore;
/*     */ import com.world2xplane.World2XPlane;
/*     */ import com.world2xplane.XPlane.DSFTile;
/*     */ import com.world2xplane.XPlane.TextureConverter;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import java.util.concurrent.Callable;
/*     */ import java.util.concurrent.ExecutionException;
/*     */ import java.util.concurrent.ExecutorService;
/*     */ import java.util.concurrent.Executors;
/*     */ import java.util.concurrent.Future;
/*     */ import math.geom2d.Point2D;
/*     */ import math.geom2d.polygon.LinearRing2D;
/*     */ import org.apache.commons.io.FileUtils;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ import org.openstreetmap.osmosis.osmbinary.file.BlockInputStream;
/*     */ import org.openstreetmap.osmosis.osmbinary.file.BlockReaderAdapter;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ 
/*     */ public class OSMProcess {
/*  47 */   private final Set<DSFTile> tilePoints = new HashSet<>();
/*     */   
/*     */   private final World2XPlane.StatusReporter statusReporter;
/*     */   
/*     */   private Hook hook;
/*     */   
/*     */   private boolean resume = false;
/*     */   
/*  60 */   private static Logger log = LoggerFactory.getLogger(OSMProcess.class);
/*     */   
/*     */   private final GeneratorStore generatorStore;
/*     */   
/*     */   private String filename;
/*     */   
/*     */   private DataStore dataStore;
/*     */   
/*     */   private TagRejector tagRejector;
/*     */   
/*  66 */   private volatile int count = 0;
/*     */   
/*  67 */   private volatile int tile = 0;
/*     */   
/*     */   private volatile boolean finished = false;
/*     */   
/*  70 */   private volatile float progress = 0.0F;
/*     */   
/*  71 */   private String statusMessage = "";
/*     */   
/*     */   private List<ExternalProcessor> externalProcessors;
/*     */   
/*     */   public OSMProcess(GeneratorStore generatorStore, World2XPlane.StatusReporter statusReporter) throws Exception {
/*  75 */     this.generatorStore = generatorStore;
/*  76 */     this.statusReporter = statusReporter;
/*     */   }
/*     */   
/*     */   public void process(String filename, boolean resume, boolean useDatabase, List<Point2D> tiles) throws Exception {
/*  84 */     process(filename, resume, useDatabase, tiles, new ArrayList<>(), null);
/*     */   }
/*     */   
/*     */   public void process(String filename, boolean resume, boolean useDatabase, List<Point2D> tiles, List<ExternalProcessor> externalProcessors) {
/*  92 */     process(filename, resume, useDatabase, tiles, null);
/*     */   }
/*     */   
/*     */   public void process(String filename, boolean resume, boolean useDatabase, List<Point2D> tiles, List<ExternalProcessor> externalProcessors, TagRejector tagRejector) throws Exception {
/* 103 */     this.tagRejector = tagRejector;
/* 104 */     if (tiles != null && tiles.size() == 0)
/* 105 */       tiles = null; 
/* 107 */     this.externalProcessors = externalProcessors;
/* 111 */     log.info("Output Folder: " + this.generatorStore.getOutputFolder());
/* 112 */     if ((new File(this.generatorStore.getOutputFolder())).exists() && !resume)
/* 113 */       FileUtils.deleteDirectory(new File(this.generatorStore.getOutputFolder())); 
/* 117 */     if (!resume) {
/* 118 */       FileUtils.forceMkdir(new File(this.generatorStore.getOutputFolder()));
/* 119 */       for (String item : this.generatorStore.getFacadeDirectories())
/* 120 */         FileUtils.copyDirectory(new File("./resources" + File.separator + item), new File(this.generatorStore.getOutputFolder() + File.separator + item)); 
/* 122 */       FileUtils.copyDirectory(new File("./resources" + File.separator + "objects"), new File(this.generatorStore.getOutputFolder() + File.separator + "objects"));
/*     */     } else {
/* 124 */       for (String item : this.generatorStore.getFacadeDirectories()) {
/* 125 */         if (!(new File(this.generatorStore.getOutputFolder() + File.separator + item)).exists())
/* 126 */           FileUtils.copyDirectory(new File("./resources" + File.separator + item), new File(this.generatorStore.getOutputFolder() + File.separator + item)); 
/*     */       } 
/* 131 */       if (!(new File(this.generatorStore.getOutputFolder() + File.separator + "objects")).exists())
/* 132 */         FileUtils.copyDirectory(new File("./resources" + File.separator + "objects"), new File(this.generatorStore.getOutputFolder() + File.separator + "objects")); 
/*     */     } 
/* 139 */     if (!resume)
/* 140 */       this.generatorStore.writeFacadeLods(); 
/* 144 */     if (useDatabase) {
/* 145 */       this.dataStore = (DataStore)new JDBMStore(resume, new File(filename));
/*     */     } else {
/* 147 */       this.dataStore = (DataStore)new MemoryStore();
/*     */     } 
/* 150 */     this.resume = resume;
/* 151 */     Set<DSFTile> processingTiles = new HashSet<>();
/* 154 */     if (filename != null) {
/* 156 */       this.filename = filename;
/* 157 */       log.info("Processing " + filename);
/* 159 */       if (this.generatorStore.getPolyFile() != null)
/* 160 */         this.generatorStore.readPolyFile(); 
/* 163 */       this.progress = -1.0F;
/* 164 */       if (!this.dataStore.isValid() || !resume) {
/* 168 */         if (this.statusReporter != null)
/* 169 */           this.statusReporter.showMessage("1/5. Indexing OSM File. (Building Relation Index)"); 
/* 171 */         this.statusMessage = "Building Relation Index";
/* 172 */         log.info("First Pass (Gathering Relations and Way Nodes)");
/* 174 */         InputStream input = new FileInputStream(filename);
/* 175 */         OSMPBFParserPassOne firstPass = new OSMPBFParserPassOne(this.dataStore, this.generatorStore);
/* 176 */         (new BlockInputStream(input, (BlockReaderAdapter)firstPass)).process();
/* 177 */         input.close();
/* 179 */         log.info(String.format("First Pass Complete, stored %d relations, %d nodes", new Object[] { Integer.valueOf(this.dataStore.numberOfRelations()), Integer.valueOf(this.dataStore.numberOfNodes()) }));
/* 181 */         log.info("Second Pass (Gathering Ways for Relations)");
/* 182 */         this.statusMessage = "Building Way Index";
/* 183 */         if (this.statusReporter != null)
/* 184 */           this.statusReporter.showMessage("2/5. Indexing OSM File. (Building Way Index)"); 
/* 186 */         OSMPBFParserPassTwo secondPass = new OSMPBFParserPassTwo(this.dataStore, this.generatorStore);
/* 187 */         input = new FileInputStream(filename);
/* 188 */         (new BlockInputStream(input, (BlockReaderAdapter)secondPass)).process();
/* 189 */         log.info(String.format("Second Pass Complete, stored %d nodes", new Object[] { Integer.valueOf(this.dataStore.numberOfNodes()) }));
/* 190 */         input.close();
/* 193 */         this.statusMessage = "Building Node Index";
/* 194 */         log.info("Third Pass (Gathering Nodes for Relations)");
/* 195 */         if (this.statusReporter != null)
/* 196 */           this.statusReporter.showMessage("3/5. Indexing OSM File. (Building Relation Index)"); 
/* 198 */         OSMPBFParserPassThree thirdPass = new OSMPBFParserPassThree(this.dataStore, this.generatorStore);
/* 199 */         input = new FileInputStream(filename);
/* 200 */         (new BlockInputStream(input, (BlockReaderAdapter)thirdPass)).process();
/* 201 */         log.info(String.format("Third Pass Complete, stored %d nodes", new Object[] { Integer.valueOf(this.dataStore.numberOfNodes()) }));
/* 202 */         input.close();
/* 205 */         log.info("Fourth Pass(Gathering Coordinates for Nodes)");
/* 206 */         if (this.statusReporter != null)
/* 207 */           this.statusReporter.showMessage("4/5. Indexing OSM File. (Collecting Node Coordinates)"); 
/* 209 */         input = new FileInputStream(filename);
/* 210 */         OSMPBFParserPassFour fourthPass = new OSMPBFParserPassFour(this.dataStore, this.generatorStore);
/* 211 */         (new BlockInputStream(input, (BlockReaderAdapter)fourthPass)).process();
/* 212 */         log.info("Fourth Pass Complete");
/* 213 */         input.close();
/* 216 */         this.statusMessage = "Sorting Relations";
/* 217 */         if (this.statusReporter != null)
/* 218 */           this.statusReporter.showMessage("5/5. Indexing OSM File. (Sorting Relations)"); 
/* 220 */         log.info("Fifth Pass(Sorting Relations into tiles)");
/* 221 */         input = new FileInputStream(filename);
/* 222 */         OSMPBFParserPassFive fifthPass = new OSMPBFParserPassFive(this.dataStore, this.generatorStore);
/* 223 */         (new BlockInputStream(input, (BlockReaderAdapter)fifthPass)).process();
/* 224 */         log.info("Fifth Pass Complete");
/* 225 */         input.close();
/* 228 */         this.dataStore.markAsValid();
/* 229 */         this.dataStore.commit();
/*     */       } 
/* 234 */       for (Object node : this.dataStore.getNodes()) {
/* 235 */         double[] coords = (double[])node;
/* 236 */         Point2D loc = new Point2D(coords[0], coords[1]);
/* 237 */         int lon = (int)FastMath.floor(loc.x());
/* 238 */         int lat = (int)FastMath.floor(loc.y());
/* 241 */         Point2D tilePoint = new Point2D(lon, lat);
/* 242 */         DSFTile dsfTile = new DSFTile(lon, lat, this.generatorStore, new DSFTile.Progress() {
/*     */               public void progressChanged(DSFTile tile, String message, float progress) {
/* 246 */                 if (OSMProcess.this.statusReporter != null)
/* 247 */                   OSMProcess.this.statusReporter.tileStatus(tile, progress, message); 
/*     */               }
/*     */             });
/* 252 */         boolean include = true;
/* 253 */         if (this.generatorStore.getRegionPoly() != null) {
/* 254 */           GeometryFactory geometryFactory = new GeometryFactory();
/* 255 */           Geometry box = geometryFactory.toGeometry(dsfTile.getEnvelope());
/* 256 */           include = this.generatorStore.getRegionPoly().intersects(box);
/*     */         } 
/* 259 */         if (include) {
/* 261 */           processingTiles.add(dsfTile);
/* 262 */           if (resume && (new File(dsfTile.getDSFFilename().replaceAll(".txt", ""))).exists()) {
/* 263 */             log.info(String.format("Tile %d:%d already exists, not regenerating", new Object[] { Integer.valueOf(lat), Integer.valueOf(lon) }));
/* 264 */             dsfTile.setCompleted(true);
/*     */             continue;
/*     */           } 
/* 269 */           if (this.generatorStore.isDebugging())
/* 270 */             log.info(String.format("Adding Tile %d:%d to process", new Object[] { Integer.valueOf(lat), Integer.valueOf(lon) })); 
/* 272 */           if (tiles != null && tiles.size() > 0 && tiles.contains(tilePoint)) {
/* 273 */             this.tilePoints.add(dsfTile);
/*     */             continue;
/*     */           } 
/* 274 */           if (tiles == null)
/* 275 */             this.tilePoints.add(dsfTile); 
/*     */         } 
/*     */       } 
/*     */     } 
/* 283 */     if (externalProcessors != null && filename == null)
/* 284 */       for (ExternalProcessor item : externalProcessors) {
/* 286 */         List<Point2D> coords = item.getBoundCoords();
/* 287 */         if (coords == null)
/*     */           continue; 
/* 290 */         for (Point2D coord : coords) {
/* 291 */           Point2D loc = new Point2D(coord.x(), coord.y());
/* 292 */           int lon = (int)FastMath.floor(loc.x());
/* 293 */           int lat = (int)FastMath.floor(loc.y());
/* 295 */           DSFTile dsfTile = new DSFTile(lon, lat, this.generatorStore, new DSFTile.Progress() {
/*     */                 public void progressChanged(DSFTile tile, String message, float progress) {
/* 299 */                   if (OSMProcess.this.statusReporter != null)
/* 300 */                     OSMProcess.this.statusReporter.tileStatus(tile, progress, message); 
/*     */                 }
/*     */               });
/* 304 */           if (!processingTiles.contains(dsfTile))
/* 305 */             processingTiles.add(dsfTile); 
/* 307 */           Point2D tilePoint = new Point2D(lon, lat);
/* 309 */           if (tiles != null && tiles.size() > 0 && tiles.contains(tilePoint)) {
/* 310 */             this.tilePoints.add(dsfTile);
/*     */             continue;
/*     */           } 
/* 311 */           if (tiles == null)
/* 312 */             this.tilePoints.add(dsfTile); 
/*     */         } 
/*     */       }  
/* 320 */     if (this.statusReporter != null)
/* 321 */       this.statusReporter.tileList(processingTiles); 
/* 324 */     buildScenery();
/* 325 */     this.dataStore.close();
/* 327 */     if (this.generatorStore.isCompressTextures()) {
/* 328 */       TextureConverter textureConverter = new TextureConverter();
/* 329 */       textureConverter.convertTexturesToDDS();
/*     */     } 
/*     */     try {
/* 334 */       FileUtils.deleteQuietly(new File(this.generatorStore.getOutputFolder() + File.separator + "objects" + File.separator + "parts"));
/* 335 */       FileUtils.deleteQuietly(new File(this.generatorStore.getOutputFolder() + File.separator + "objects" + File.separator + "parts"));
/* 336 */     } catch (Exception e) {}
/* 339 */     if (this.generatorStore.isCopyToXPlane() && this.generatorStore.getXplaneSceneryFolder() != null) {
/* 340 */       FileUtils.deleteQuietly(new File(this.generatorStore.getXplaneSceneryFolder() + File.separator + "Custom Scenery" + File.separator + this.generatorStore.getOutputFolder()));
/* 342 */       FileUtils.copyDirectory(new File(this.generatorStore.getOutputFolder()), new File(this.generatorStore.getXplaneSceneryFolder() + File.separator + "Custom Scenery" + File.separator + this.generatorStore.getOutputFolder()));
/*     */     } 
/*     */   }
/*     */   
/*     */   public static interface Hook {
/*     */     boolean acceptShape(HashMap<String, String> param1HashMap, LinearRing2D param1LinearRing2D);
/*     */     
/*     */     boolean acceptTags(HashMap<String, String> param1HashMap);
/*     */   }
/*     */   
/*     */   public class Worker implements Callable<DSFTile> {
/*     */     final DSFTile dsfTile;
/*     */     
/*     */     public Worker(DSFTile dsfTile) {
/* 356 */       this.dsfTile = dsfTile;
/*     */     }
/*     */     
/*     */     public DSFTile call() throws Exception {
/* 362 */       if (OSMProcess.this.filename != null) {
/* 363 */         InputStream input = null;
/* 364 */         OSMProcess.log.info(String.format("Reading %s", new Object[] { this.dsfTile }));
/* 365 */         if (OSMProcess.this.statusReporter != null)
/* 366 */           OSMProcess.this.statusReporter.tileProcessing(this.dsfTile); 
/* 368 */         input = new FileInputStream(OSMProcess.this.filename);
/* 369 */         this.dsfTile.setNetworkItems(OSMProcess.this.generatorStore.initialiseNetworkRules());
/* 370 */         OSMPBFTileProcessor tileProcessor = new OSMPBFTileProcessor(OSMProcess.this.dataStore, this.dsfTile, OSMProcess.this.generatorStore, OSMProcess.this.hook, OSMProcess.this.tagRejector);
/* 371 */         (new BlockInputStream(input, (BlockReaderAdapter)tileProcessor)).process();
/* 372 */         input.close();
/*     */       } else {
/* 374 */         this.dsfTile.setNetworkItems(OSMProcess.this.generatorStore.initialiseNetworkRules());
/* 376 */         for (NetworkItem item : this.dsfTile.getNetworkItems())
/* 377 */           item.init(this.dsfTile, OSMProcess.this.dataStore); 
/*     */       } 
/* 381 */       if (OSMProcess.this.externalProcessors != null && OSMProcess.this.externalProcessors.size() > 0)
/* 382 */         for (ExternalProcessor shapeFileProcessor : OSMProcess.this.externalProcessors) {
/* 383 */           if (shapeFileProcessor.getBoundCoords() == null) {
/* 384 */             OSMProcess.log.info(shapeFileProcessor.toString() + " has no bounds");
/*     */             continue;
/*     */           } 
/* 387 */           if (this.dsfTile != null && this.dsfTile.getEnvelope() != null && shapeFileProcessor.getBoundCoords() != null && shapeFileProcessor.acceptsTile(this.dsfTile))
/* 388 */             shapeFileProcessor.process(this.dsfTile); 
/*     */         }  
/* 393 */       OSMProcess.this.statusMessage = "Generating " + this.dsfTile.toString();
/* 394 */       OSMProcess.this.tile++;
/* 395 */       OSMProcess.log.info(String.format("Processing Tile %d/%d : %s", new Object[] { Integer.valueOf(OSMProcess.access$900(this.this$0)), Integer.valueOf(OSMProcess.access$1000(this.this$0).size()), this.dsfTile }));
/* 396 */       this.dsfTile.process();
/*     */       try {
/* 398 */         this.dsfTile.writeFile(OSMProcess.this.generatorStore.getOutputFolder());
/* 399 */       } catch (IOException e) {
/* 400 */         e.printStackTrace();
/*     */       } finally {
/* 402 */         OSMProcess.this.count++;
/*     */       } 
/* 405 */       OSMProcess.log.info(String.format("Processed Tile: %d facades, %d forests, %d objects", new Object[] { Integer.valueOf(this.dsfTile.getBuildingCount()), Integer.valueOf(this.dsfTile.getForestCount()), Integer.valueOf(this.dsfTile.getObjectCount()) }));
/* 406 */       if (this.dsfTile.getBuildingExclusionCount() > 0 || this.dsfTile.getForestExclusionCount() > 0)
/* 407 */         OSMProcess.log.info(String.format("Added %d building exclusions, %d forest exclusions.", new Object[] { Integer.valueOf(this.dsfTile.getBuildingExclusionCount()), Integer.valueOf(this.dsfTile.getForestExclusionCount()) })); 
/* 409 */       if (OSMProcess.this.statusReporter != null)
/* 410 */         OSMProcess.this.statusReporter.tileComplete(this.dsfTile, this.dsfTile.getImageDebugFile()); 
/* 412 */       OSMProcess.log.info(this.dsfTile.regionReport());
/* 413 */       this.dsfTile.clear();
/* 416 */       System.gc();
/* 417 */       OSMProcess.this.progress = OSMProcess.this.count / OSMProcess.this.tilePoints.size() * 100.0F;
/* 419 */       return this.dsfTile;
/*     */     }
/*     */   }
/*     */   
/*     */   public void buildScenery() throws IOException, ExecutionException, InterruptedException {
/* 428 */     log.info("Processing " + this.tilePoints.size() + " tiles");
/* 429 */     this.count = 0;
/* 431 */     if (this.tilePoints.size() == 0) {
/* 432 */       log.info("Nothing to process");
/*     */       return;
/*     */     } 
/* 435 */     int numberOfThreads = this.generatorStore.getProcessorThreads();
/* 436 */     log.info("Using " + numberOfThreads + " threads");
/* 437 */     if (numberOfThreads > 4) {
/* 438 */       log.info("Limiting " + numberOfThreads + " to 4 threads.");
/* 439 */       numberOfThreads = 4;
/*     */     } 
/* 441 */     ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);
/* 444 */     List<Future<DSFTile>> futures = new ArrayList<>();
/* 446 */     for (DSFTile item : this.tilePoints) {
/*     */       try {
/* 449 */         futures.add(executor.submit(new Worker(item)));
/* 450 */       } catch (Exception e) {
/* 451 */         e.printStackTrace();
/*     */       } 
/*     */     } 
/* 456 */     for (Future<DSFTile> item : futures)
/* 457 */       DSFTile dsfTile = item.get(); 
/* 460 */     this.progress = 1.0F;
/* 461 */     log.info("Generation Finished");
/*     */   }
/*     */   
/*     */   public float getProgress() {
/* 466 */     return this.progress;
/*     */   }
/*     */   
/*     */   public String getStatusMessage() {
/* 470 */     return this.statusMessage;
/*     */   }
/*     */   
/*     */   public void setStatusMessage(String statusMessage) {
/* 474 */     this.statusMessage = statusMessage;
/*     */   }
/*     */   
/*     */   public Hook getHook() {
/* 478 */     return this.hook;
/*     */   }
/*     */   
/*     */   public void setHook(Hook hook) {
/* 482 */     this.hook = hook;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\Parser\OSMProcess.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */