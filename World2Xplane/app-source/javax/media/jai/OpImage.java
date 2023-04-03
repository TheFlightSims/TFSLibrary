/*      */ package javax.media.jai;
/*      */ 
/*      */ import com.sun.media.jai.util.ImageUtil;
/*      */ import com.sun.media.jai.util.JDKWorkarounds;
/*      */ import java.awt.Dimension;
/*      */ import java.awt.Point;
/*      */ import java.awt.Rectangle;
/*      */ import java.awt.geom.Point2D;
/*      */ import java.awt.image.ColorModel;
/*      */ import java.awt.image.IndexColorModel;
/*      */ import java.awt.image.Raster;
/*      */ import java.awt.image.RenderedImage;
/*      */ import java.awt.image.SampleModel;
/*      */ import java.awt.image.WritableRaster;
/*      */ import java.util.Map;
/*      */ import java.util.Vector;
/*      */ 
/*      */ public abstract class OpImage extends PlanarImage {
/*      */   public static final int OP_COMPUTE_BOUND = 1;
/*      */   
/*      */   public static final int OP_IO_BOUND = 2;
/*      */   
/*      */   public static final int OP_NETWORK_BOUND = 3;
/*      */   
/*      */   private static final int LAYOUT_MASK_ALL = 1023;
/*      */   
/*      */   protected transient TileCache cache;
/*      */   
/*      */   protected Object tileCacheMetric;
/*      */   
/*  233 */   private transient TileScheduler scheduler = JAI.getDefaultInstance().getTileScheduler();
/*      */   
/*      */   private boolean isSunTileScheduler = false;
/*      */   
/*      */   protected boolean cobbleSources;
/*      */   
/*      */   private boolean isDisposed = false;
/*      */   
/*      */   private boolean isCachedTileRecyclingEnabled = false;
/*      */   
/*      */   protected TileRecycler tileRecycler;
/*      */   
/*  271 */   private RasterFormatTag[] formatTags = null;
/*      */   
/*      */   private static ImageLayout layoutHelper(ImageLayout layout, Vector sources, Map config) {
/*  313 */     ImageLayout il = layout;
/*  316 */     if (sources != null)
/*  317 */       checkSourceVector(sources, true); 
/*  322 */     RenderedImage im = (sources != null && sources.size() > 0 && sources.firstElement() instanceof RenderedImage) ? sources.firstElement() : null;
/*  328 */     if (im != null) {
/*  331 */       if (layout == null) {
/*  333 */         il = layout = new ImageLayout(im);
/*  336 */         il.unsetValid(512);
/*      */       } else {
/*  339 */         il = new ImageLayout(layout.getMinX(im), layout.getMinY(im), layout.getWidth(im), layout.getHeight(im), layout.getTileGridXOffset(im), layout.getTileGridYOffset(im), layout.getTileWidth(im), layout.getTileHeight(im), layout.getSampleModel(im), null);
/*      */       } 
/*  356 */       if (layout.isValid(512) && layout.getColorModel(null) == null) {
/*  359 */         il.setColorModel(null);
/*  361 */       } else if (il.getSampleModel(null) != null) {
/*  366 */         SampleModel sm = il.getSampleModel(null);
/*  369 */         ColorModel cmLayout = layout.getColorModel(null);
/*  373 */         if (cmLayout != null)
/*  375 */           if (JDKWorkarounds.areCompatibleDataModels(sm, cmLayout)) {
/*  377 */             il.setColorModel(cmLayout);
/*  379 */           } else if (layout.getSampleModel(null) == null) {
/*  385 */             il.setColorModel(cmLayout);
/*  388 */             SampleModel derivedSM = cmLayout.createCompatibleSampleModel(il.getTileWidth(null), il.getTileHeight(null));
/*  394 */             il.setSampleModel(derivedSM);
/*      */           }  
/*  402 */         if (!il.isValid(512) && !setColorModelFromFactory(sm, sources, config, il)) {
/*  405 */           ColorModel cmSource = im.getColorModel();
/*  406 */           if (cmSource != null && JDKWorkarounds.areCompatibleDataModels(sm, cmSource))
/*  409 */             if (cmSource != null && cmSource instanceof IndexColorModel && config != null && config.containsKey(JAI.KEY_REPLACE_INDEX_COLOR_MODEL) && ((Boolean)config.get(JAI.KEY_REPLACE_INDEX_COLOR_MODEL)).booleanValue()) {
/*      */               SampleModel newSM;
/*  416 */               ColorModel newCM = PlanarImage.getDefaultColorModel(sm.getDataType(), cmSource.getNumComponents());
/*  422 */               if (newCM != null) {
/*  423 */                 newSM = newCM.createCompatibleSampleModel(il.getTileWidth(null), il.getTileHeight(null));
/*      */               } else {
/*  428 */                 newSM = RasterFactory.createPixelInterleavedSampleModel(sm.getDataType(), il.getTileWidth(null), il.getTileHeight(null), cmSource.getNumComponents());
/*      */               } 
/*  436 */               il.setSampleModel(newSM);
/*  437 */               if (newCM != null)
/*  438 */                 il.setColorModel(newCM); 
/*      */             } else {
/*  440 */               il.setColorModel(cmSource);
/*      */             }  
/*      */         } 
/*  444 */       } else if (il.getSampleModel(null) == null) {
/*  446 */         il.setColorModel(layout.getColorModel(im));
/*      */       } 
/*  449 */     } else if (il != null) {
/*  452 */       il = (ImageLayout)layout.clone();
/*  456 */       if (il.getColorModel(null) != null && il.getSampleModel(null) == null) {
/*  459 */         int smWidth = il.getTileWidth(null);
/*  460 */         if (smWidth == 0)
/*  461 */           smWidth = 512; 
/*  463 */         int smHeight = il.getTileHeight(null);
/*  464 */         if (smHeight == 0)
/*  465 */           smHeight = 512; 
/*  469 */         SampleModel derivedSM = il.getColorModel(null).createCompatibleSampleModel(smWidth, smHeight);
/*  474 */         il.setSampleModel(derivedSM);
/*      */       } 
/*      */     } 
/*  483 */     if (il != null && !il.isValid(512) && il.getSampleModel(null) != null && !setColorModelFromFactory(il.getSampleModel(null), sources, config, il)) {
/*  489 */       ColorModel cm = null;
/*  490 */       SampleModel srcSM = il.getSampleModel(null);
/*  498 */       if (im != null && im.getColorModel() != null && im.getColorModel() instanceof IndexColorModel && config != null && config.containsKey(JAI.KEY_REPLACE_INDEX_COLOR_MODEL) && ((Boolean)config.get(JAI.KEY_REPLACE_INDEX_COLOR_MODEL)).booleanValue()) {
/*      */         SampleModel newSM;
/*  505 */         IndexColorModel icm = (IndexColorModel)im.getColorModel();
/*  511 */         cm = PlanarImage.getDefaultColorModel(srcSM.getDataType(), icm.getNumComponents());
/*  515 */         if (cm != null) {
/*  516 */           newSM = cm.createCompatibleSampleModel(srcSM.getWidth(), srcSM.getHeight());
/*      */         } else {
/*  519 */           newSM = RasterFactory.createPixelInterleavedSampleModel(srcSM.getDataType(), srcSM.getWidth(), srcSM.getHeight(), icm.getNumComponents());
/*      */         } 
/*  526 */         il.setSampleModel(newSM);
/*      */       } else {
/*  530 */         cm = ImageUtil.getCompatibleColorModel(il.getSampleModel(null), config);
/*      */       } 
/*  535 */       if (cm != null)
/*  536 */         il.setColorModel(cm); 
/*      */     } 
/*  543 */     if (layout != null && il != null && !layout.isValid(192)) {
/*  546 */       Dimension defaultTileSize = JAI.getDefaultTileSize();
/*  547 */       if (defaultTileSize != null) {
/*  548 */         if (!layout.isValid(64))
/*  549 */           if (il.getTileWidth(null) <= 0) {
/*  550 */             il.setTileWidth(defaultTileSize.width);
/*      */           } else {
/*  553 */             int numX = XToTileX(il.getMinX(null) + il.getWidth(null) - 1, il.getTileGridXOffset(null), il.getTileWidth(null)) - XToTileX(il.getMinX(null), il.getTileGridXOffset(null), il.getTileWidth(null)) + 1;
/*  562 */             if (numX <= 1 && il.getWidth(null) >= 2 * defaultTileSize.width)
/*  564 */               il.setTileWidth(defaultTileSize.width); 
/*      */           }  
/*  569 */         if (!layout.isValid(128))
/*  570 */           if (il.getTileHeight(null) <= 0) {
/*  571 */             il.setTileHeight(defaultTileSize.height);
/*      */           } else {
/*  574 */             int numY = YToTileY(il.getMinY(null) + il.getHeight(null) - 1, il.getTileGridYOffset(null), il.getTileHeight(null)) - YToTileY(il.getMinY(null), il.getTileGridYOffset(null), il.getTileHeight(null)) + 1;
/*  583 */             if (numY <= 1 && il.getHeight(null) >= 2 * defaultTileSize.height)
/*  585 */               il.setTileHeight(defaultTileSize.height); 
/*      */           }  
/*      */       } 
/*      */     } 
/*  598 */     if ((layout == null || !layout.isValid(64)) && il.isValid(68) && il.getTileWidth(null) > il.getWidth(null))
/*  603 */       il.setTileWidth(il.getWidth(null)); 
/*  607 */     if ((layout == null || !layout.isValid(128)) && il.isValid(136) && il.getTileHeight(null) > il.getHeight(null))
/*  612 */       il.setTileHeight(il.getHeight(null)); 
/*  615 */     return il;
/*      */   }
/*      */   
/*      */   private static boolean setColorModelFromFactory(SampleModel sampleModel, Vector sources, Map config, ImageLayout layout) {
/*  647 */     boolean isColorModelSet = false;
/*  649 */     if (config != null && config.containsKey(JAI.KEY_COLOR_MODEL_FACTORY)) {
/*  651 */       ColorModelFactory cmf = (ColorModelFactory)config.get(JAI.KEY_COLOR_MODEL_FACTORY);
/*  653 */       ColorModel cm = cmf.createColorModel(sampleModel, sources, config);
/*  656 */       if (cm != null && JDKWorkarounds.areCompatibleDataModels(sampleModel, cm)) {
/*  658 */         layout.setColorModel(cm);
/*  659 */         isColorModelSet = true;
/*      */       } 
/*      */     } 
/*  663 */     return isColorModelSet;
/*      */   }
/*      */   
/*      */   public OpImage(Vector sources, ImageLayout layout, Map configuration, boolean cobbleSources) {
/*  829 */     super(layoutHelper(layout, sources, configuration), sources, configuration);
/*  832 */     if (configuration != null) {
/*  834 */       Object cacheConfig = configuration.get(JAI.KEY_TILE_CACHE);
/*  837 */       if (cacheConfig != null && cacheConfig instanceof TileCache && ((TileCache)cacheConfig).getMemoryCapacity() > 0L)
/*  840 */         this.cache = (TileCache)cacheConfig; 
/*  844 */       Object schedulerConfig = configuration.get(JAI.KEY_TILE_SCHEDULER);
/*  847 */       if (schedulerConfig != null && schedulerConfig instanceof TileScheduler)
/*  849 */         this.scheduler = (TileScheduler)schedulerConfig; 
/*      */       try {
/*  854 */         Class sunScheduler = Class.forName("com.sun.media.jai.util.SunTileScheduler");
/*  857 */         this.isSunTileScheduler = sunScheduler.isInstance(this.scheduler);
/*  858 */       } catch (Exception e) {}
/*  863 */       this.tileCacheMetric = configuration.get(JAI.KEY_TILE_CACHE_METRIC);
/*  866 */       Object recyclingEnabledValue = configuration.get(JAI.KEY_CACHED_TILE_RECYCLING_ENABLED);
/*  868 */       if (recyclingEnabledValue instanceof Boolean)
/*  869 */         this.isCachedTileRecyclingEnabled = ((Boolean)recyclingEnabledValue).booleanValue(); 
/*  874 */       Object recyclerValue = configuration.get(JAI.KEY_TILE_RECYCLER);
/*  875 */       if (recyclerValue instanceof TileRecycler)
/*  876 */         this.tileRecycler = (TileRecycler)recyclerValue; 
/*      */     } 
/*  880 */     this.cobbleSources = cobbleSources;
/*      */   }
/*      */   
/*      */   private class TCL implements TileComputationListener {
/*      */     OpImage opImage;
/*      */     
/*      */     private final OpImage this$0;
/*      */     
/*      */     private TCL(OpImage this$0, OpImage opImage) {
/*  891 */       OpImage.this = OpImage.this;
/*  892 */       this.opImage = opImage;
/*      */     }
/*      */     
/*      */     public void tileComputed(Object eventSource, TileRequest[] requests, PlanarImage image, int tileX, int tileY, Raster tile) {
/*  899 */       if (image == this.opImage)
/*  901 */         OpImage.this.addTileToCache(tileX, tileY, tile); 
/*      */     }
/*      */     
/*      */     public void tileCancelled(Object eventSource, TileRequest[] requests, PlanarImage image, int tileX, int tileY) {}
/*      */     
/*      */     public void tileComputationFailure(Object eventSource, TileRequest[] requests, PlanarImage image, int tileX, int tileY, Throwable situation) {}
/*      */   }
/*      */   
/*      */   protected static Vector vectorize(RenderedImage image) {
/*  933 */     if (image == null)
/*  934 */       throw new IllegalArgumentException(JaiI18N.getString("OpImage3")); 
/*  936 */     Vector v = new Vector(1);
/*  937 */     v.addElement(image);
/*  938 */     return v;
/*      */   }
/*      */   
/*      */   protected static Vector vectorize(RenderedImage image1, RenderedImage image2) {
/*  956 */     if (image1 == null || image2 == null)
/*  957 */       throw new IllegalArgumentException(JaiI18N.getString("OpImage3")); 
/*  959 */     Vector v = new Vector(2);
/*  960 */     v.addElement(image1);
/*  961 */     v.addElement(image2);
/*  962 */     return v;
/*      */   }
/*      */   
/*      */   protected static Vector vectorize(RenderedImage image1, RenderedImage image2, RenderedImage image3) {
/*  982 */     if (image1 == null || image2 == null || image3 == null)
/*  983 */       throw new IllegalArgumentException(JaiI18N.getString("OpImage3")); 
/*  985 */     Vector v = new Vector(3);
/*  986 */     v.addElement(image1);
/*  987 */     v.addElement(image2);
/*  988 */     v.addElement(image3);
/*  989 */     return v;
/*      */   }
/*      */   
/*      */   static Vector checkSourceVector(Vector sources, boolean checkElements) {
/* 1012 */     if (sources == null)
/* 1013 */       throw new IllegalArgumentException(JaiI18N.getString("OpImage2")); 
/* 1016 */     if (checkElements) {
/* 1018 */       int numSources = sources.size();
/* 1019 */       for (int i = 0; i < numSources; i++) {
/* 1021 */         if (sources.get(i) == null)
/* 1022 */           throw new IllegalArgumentException(JaiI18N.getString("OpImage3")); 
/*      */       } 
/*      */     } 
/* 1028 */     return sources;
/*      */   }
/*      */   
/*      */   public TileCache getTileCache() {
/* 1039 */     return this.cache;
/*      */   }
/*      */   
/*      */   public void setTileCache(TileCache cache) {
/* 1054 */     if (this.cache != null)
/* 1055 */       this.cache.removeTiles(this); 
/* 1057 */     this.cache = cache;
/*      */   }
/*      */   
/*      */   protected Raster getTileFromCache(int tileX, int tileY) {
/* 1072 */     return (this.cache != null) ? this.cache.getTile(this, tileX, tileY) : null;
/*      */   }
/*      */   
/*      */   protected void addTileToCache(int tileX, int tileY, Raster tile) {
/* 1086 */     if (this.cache != null)
/* 1087 */       this.cache.add(this, tileX, tileY, tile, this.tileCacheMetric); 
/*      */   }
/*      */   
/*      */   public Object getTileCacheMetric() {
/* 1097 */     return this.tileCacheMetric;
/*      */   }
/*      */   
/*      */   public Raster getTile(int tileX, int tileY) {
/* 1119 */     Raster tile = null;
/* 1122 */     if (tileX >= getMinTileX() && tileX <= getMaxTileX() && tileY >= getMinTileY() && tileY <= getMaxTileY()) {
/* 1125 */       tile = getTileFromCache(tileX, tileY);
/* 1127 */       if (tile == null) {
/*      */         try {
/* 1129 */           tile = this.scheduler.scheduleTile(this, tileX, tileY);
/* 1130 */         } catch (OutOfMemoryError e) {
/* 1132 */           if (this.cache != null) {
/* 1133 */             this.cache.flush();
/* 1134 */             System.gc();
/*      */           } 
/* 1138 */           tile = this.scheduler.scheduleTile(this, tileX, tileY);
/*      */         } 
/* 1142 */         addTileToCache(tileX, tileY, tile);
/*      */       } 
/*      */     } 
/* 1146 */     return tile;
/*      */   }
/*      */   
/*      */   public Raster computeTile(int tileX, int tileY) {
/* 1180 */     WritableRaster dest = createWritableRaster(this.sampleModel, new Point(tileXToX(tileX), tileYToY(tileY)));
/* 1185 */     Rectangle destRect = getTileRect(tileX, tileY);
/* 1187 */     int numSources = getNumSources();
/* 1189 */     if (this.cobbleSources) {
/* 1190 */       Raster[] rasterSources = new Raster[numSources];
/*      */       int i;
/* 1192 */       for (i = 0; i < numSources; i++) {
/* 1193 */         PlanarImage source = getSource(i);
/* 1194 */         Rectangle srcRect = mapDestRect(destRect, i);
/* 1200 */         rasterSources[i] = (srcRect != null && srcRect.isEmpty()) ? null : source.getData(srcRect);
/*      */       } 
/* 1203 */       computeRect(rasterSources, dest, destRect);
/* 1205 */       for (i = 0; i < numSources; i++) {
/* 1206 */         Raster sourceData = rasterSources[i];
/* 1207 */         if (sourceData != null) {
/* 1208 */           PlanarImage source = getSourceImage(i);
/* 1211 */           if (source.overlapsMultipleTiles(sourceData.getBounds()))
/* 1212 */             recycleTile(sourceData); 
/*      */         } 
/*      */       } 
/*      */     } else {
/* 1217 */       PlanarImage[] imageSources = new PlanarImage[numSources];
/* 1218 */       for (int i = 0; i < numSources; i++)
/* 1219 */         imageSources[i] = getSource(i); 
/* 1221 */       computeRect(imageSources, dest, destRect);
/*      */     } 
/* 1224 */     return dest;
/*      */   }
/*      */   
/*      */   protected void computeRect(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/* 1261 */     String className = getClass().getName();
/* 1262 */     throw new RuntimeException(className + " " + JaiI18N.getString("OpImage0"));
/*      */   }
/*      */   
/*      */   protected void computeRect(PlanarImage[] sources, WritableRaster dest, Rectangle destRect) {
/* 1294 */     String className = getClass().getName();
/* 1295 */     throw new RuntimeException(className + " " + JaiI18N.getString("OpImage1"));
/*      */   }
/*      */   
/*      */   public Point[] getTileDependencies(int tileX, int tileY, int sourceIndex) {
/* 1334 */     if (sourceIndex < 0 || sourceIndex >= getNumSources())
/* 1336 */       throw new IllegalArgumentException(JaiI18N.getString("Generic1")); 
/* 1340 */     Rectangle rect = getTileRect(tileX, tileY);
/* 1341 */     if (rect.isEmpty())
/* 1343 */       return null; 
/* 1356 */     PlanarImage src = getSource(sourceIndex);
/* 1357 */     Rectangle srcRect = mapDestRect(rect, sourceIndex);
/* 1359 */     int minTileX = src.XToTileX(srcRect.x);
/* 1360 */     int maxTileX = src.XToTileX(srcRect.x + srcRect.width - 1);
/* 1362 */     int minTileY = src.YToTileY(srcRect.y);
/* 1363 */     int maxTileY = src.YToTileY(srcRect.y + srcRect.height - 1);
/* 1366 */     minTileX = Math.max(minTileX, src.getMinTileX());
/* 1367 */     maxTileX = Math.min(maxTileX, src.getMaxTileX());
/* 1369 */     minTileY = Math.max(minTileY, src.getMinTileY());
/* 1370 */     maxTileY = Math.min(maxTileY, src.getMaxTileY());
/* 1372 */     int numXTiles = maxTileX - minTileX + 1;
/* 1373 */     int numYTiles = maxTileY - minTileY + 1;
/* 1374 */     if (numXTiles <= 0 || numYTiles <= 0)
/* 1376 */       return null; 
/* 1379 */     Point[] ret = new Point[numYTiles * numXTiles];
/* 1380 */     int i = 0;
/* 1382 */     for (int y = minTileY; y <= maxTileY; y++) {
/* 1383 */       for (int x = minTileX; x <= maxTileX; x++)
/* 1384 */         ret[i++] = new Point(x, y); 
/*      */     } 
/* 1388 */     return ret;
/*      */   }
/*      */   
/*      */   public Raster[] getTiles(Point[] tileIndices) {
/* 1410 */     if (tileIndices == null)
/* 1411 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 1414 */     int numTiles = tileIndices.length;
/* 1417 */     Raster[] tiles = new Raster[numTiles];
/* 1420 */     boolean[] computeTiles = new boolean[numTiles];
/* 1422 */     int minTileX = getMinTileX();
/* 1423 */     int maxTileX = getMaxTileX();
/* 1424 */     int minTileY = getMinTileY();
/* 1425 */     int maxTileY = getMaxTileY();
/* 1427 */     int count = 0;
/*      */     int i;
/* 1429 */     for (i = 0; i < numTiles; i++) {
/* 1430 */       int tileX = (tileIndices[i]).x;
/* 1431 */       int tileY = (tileIndices[i]).y;
/* 1434 */       if (tileX >= minTileX && tileX <= maxTileX && tileY >= minTileY && tileY <= maxTileY) {
/* 1437 */         tiles[i] = getTileFromCache(tileX, tileY);
/* 1439 */         if (tiles[i] == null) {
/* 1441 */           computeTiles[i] = true;
/* 1442 */           count++;
/*      */         } 
/*      */       } 
/*      */     } 
/* 1447 */     if (count > 0)
/* 1448 */       if (count == numTiles) {
/* 1450 */         tiles = this.scheduler.scheduleTiles(this, tileIndices);
/* 1452 */         if (this.cache != null && 
/* 1453 */           this.cache != null)
/* 1454 */           for (i = 0; i < numTiles; i++)
/* 1455 */             this.cache.add(this, (tileIndices[i]).x, (tileIndices[i]).y, tiles[i], this.tileCacheMetric);  
/*      */       } else {
/* 1466 */         Point[] indices = new Point[count];
/* 1467 */         count = 0;
/* 1468 */         for (int j = 0; j < numTiles; j++) {
/* 1469 */           if (computeTiles[j])
/* 1470 */             indices[count++] = tileIndices[j]; 
/*      */         } 
/* 1475 */         Raster[] newTiles = this.scheduler.scheduleTiles(this, indices);
/* 1477 */         count = 0;
/* 1478 */         for (int k = 0; k < numTiles; k++) {
/* 1479 */           if (computeTiles[k]) {
/* 1480 */             tiles[k] = newTiles[count++];
/* 1481 */             addTileToCache((tileIndices[k]).x, (tileIndices[k]).y, tiles[k]);
/*      */           } 
/*      */         } 
/*      */       }  
/* 1488 */     return tiles;
/*      */   }
/*      */   
/*      */   private static TileComputationListener[] prependListener(TileComputationListener[] listeners, TileComputationListener listener) {
/* 1494 */     if (listeners == null)
/* 1495 */       return new TileComputationListener[] { listener }; 
/* 1498 */     TileComputationListener[] newListeners = new TileComputationListener[listeners.length + 1];
/* 1500 */     newListeners[0] = listener;
/* 1501 */     System.arraycopy(listeners, 0, newListeners, 1, listeners.length);
/* 1503 */     return newListeners;
/*      */   }
/*      */   
/*      */   public TileRequest queueTiles(Point[] tileIndices) {
/* 1544 */     if (tileIndices == null)
/* 1545 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 1559 */     TileComputationListener[] tileListeners = getTileComputationListeners();
/* 1564 */     if (!this.isSunTileScheduler) {
/* 1566 */       TileComputationListener localListener = new TCL(this);
/* 1569 */       tileListeners = prependListener(tileListeners, localListener);
/*      */     } 
/* 1573 */     return this.scheduler.scheduleTiles(this, tileIndices, tileListeners);
/*      */   }
/*      */   
/*      */   public void cancelTiles(TileRequest request, Point[] tileIndices) {
/* 1592 */     if (request == null)
/* 1593 */       throw new IllegalArgumentException(JaiI18N.getString("Generic4")); 
/* 1595 */     this.scheduler.cancelTiles(request, tileIndices);
/*      */   }
/*      */   
/*      */   public void prefetchTiles(Point[] tileIndices) {
/* 1610 */     if (tileIndices == null)
/* 1611 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 1620 */     if (tileIndices == null)
/*      */       return; 
/* 1625 */     this.scheduler.prefetchTiles(this, tileIndices);
/*      */   }
/*      */   
/*      */   public Point2D mapDestPoint(Point2D destPt, int sourceIndex) {
/* 1671 */     if (destPt == null)
/* 1672 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 1673 */     if (sourceIndex < 0 || sourceIndex >= getNumSources())
/* 1674 */       throw new IndexOutOfBoundsException(JaiI18N.getString("Generic1")); 
/* 1677 */     Rectangle destRect = new Rectangle((int)destPt.getX(), (int)destPt.getY(), 1, 1);
/* 1681 */     Rectangle sourceRect = mapDestRect(destRect, sourceIndex);
/* 1683 */     Point2D pt = (Point2D)destPt.clone();
/* 1684 */     pt.setLocation(sourceRect.x + (sourceRect.width - 1.0D) / 2.0D, sourceRect.y + (sourceRect.height - 1.0D) / 2.0D);
/* 1687 */     return pt;
/*      */   }
/*      */   
/*      */   public Point2D mapSourcePoint(Point2D sourcePt, int sourceIndex) {
/* 1731 */     if (sourcePt == null)
/* 1732 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 1733 */     if (sourceIndex < 0 || sourceIndex >= getNumSources())
/* 1734 */       throw new IndexOutOfBoundsException(JaiI18N.getString("Generic1")); 
/* 1737 */     Rectangle sourceRect = new Rectangle((int)sourcePt.getX(), (int)sourcePt.getY(), 1, 1);
/* 1741 */     Rectangle destRect = mapSourceRect(sourceRect, sourceIndex);
/* 1744 */     if (destRect == null)
/* 1745 */       return null; 
/* 1748 */     Point2D pt = (Point2D)sourcePt.clone();
/* 1749 */     pt.setLocation(destRect.x + (destRect.width - 1.0D) / 2.0D, destRect.y + (destRect.height - 1.0D) / 2.0D);
/* 1752 */     return pt;
/*      */   }
/*      */   
/*      */   public abstract Rectangle mapSourceRect(Rectangle paramRectangle, int paramInt);
/*      */   
/*      */   public abstract Rectangle mapDestRect(Rectangle paramRectangle, int paramInt);
/*      */   
/*      */   public int getOperationComputeType() {
/* 1836 */     return 1;
/*      */   }
/*      */   
/*      */   public boolean computesUniqueTiles() {
/* 1850 */     return true;
/*      */   }
/*      */   
/*      */   public synchronized void dispose() {
/* 1869 */     if (this.isDisposed)
/*      */       return; 
/* 1873 */     this.isDisposed = true;
/* 1875 */     if (this.cache != null) {
/* 1876 */       if (this.isCachedTileRecyclingEnabled && this.tileRecycler != null) {
/* 1877 */         Raster[] tiles = this.cache.getTiles(this);
/* 1878 */         if (tiles != null) {
/* 1879 */           int numTiles = tiles.length;
/* 1880 */           for (int i = 0; i < numTiles; i++)
/* 1881 */             this.tileRecycler.recycleTile(tiles[i]); 
/*      */         } 
/*      */       } 
/* 1885 */       this.cache.removeTiles(this);
/*      */     } 
/* 1887 */     super.dispose();
/*      */   }
/*      */   
/*      */   public boolean hasExtender(int sourceIndex) {
/* 1902 */     if (sourceIndex != 0)
/* 1903 */       throw new ArrayIndexOutOfBoundsException(); 
/* 1904 */     if (this instanceof AreaOpImage)
/* 1905 */       return (((AreaOpImage)this).getBorderExtender() != null); 
/* 1906 */     if (this instanceof GeometricOpImage)
/* 1907 */       return (((GeometricOpImage)this).getBorderExtender() != null); 
/* 1909 */     return false;
/*      */   }
/*      */   
/*      */   public static int getExpandedNumBands(SampleModel sampleModel, ColorModel colorModel) {
/* 1925 */     if (colorModel instanceof IndexColorModel)
/* 1926 */       return colorModel.getNumComponents(); 
/* 1928 */     return sampleModel.getNumBands();
/*      */   }
/*      */   
/*      */   protected synchronized RasterFormatTag[] getFormatTags() {
/* 1947 */     if (this.formatTags == null) {
/* 1948 */       RenderedImage[] sourceArray = new RenderedImage[getNumSources()];
/* 1949 */       if (sourceArray.length > 0)
/* 1950 */         getSources().toArray((Object[])sourceArray); 
/* 1952 */       this.formatTags = RasterAccessor.findCompatibleTags(sourceArray, this);
/*      */     } 
/* 1955 */     return this.formatTags;
/*      */   }
/*      */   
/*      */   public TileRecycler getTileRecycler() {
/* 1964 */     return this.tileRecycler;
/*      */   }
/*      */   
/*      */   protected final WritableRaster createTile(int tileX, int tileY) {
/* 1981 */     return createWritableRaster(this.sampleModel, new Point(tileXToX(tileX), tileYToY(tileY)));
/*      */   }
/*      */   
/*      */   protected void recycleTile(Raster tile) {
/* 2008 */     if (tile == null)
/* 2009 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 2011 */     if (this.tileRecycler != null)
/* 2012 */       this.tileRecycler.recycleTile(tile); 
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\OpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */