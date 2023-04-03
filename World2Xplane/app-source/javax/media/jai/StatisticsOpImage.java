/*     */ package javax.media.jai;
/*     */ 
/*     */ import com.sun.media.jai.util.PropertyUtil;
/*     */ import java.awt.Image;
/*     */ import java.awt.Point;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.util.Map;
/*     */ import java.util.Vector;
/*     */ 
/*     */ public abstract class StatisticsOpImage extends OpImage {
/*     */   protected ROI roi;
/*     */   
/*     */   protected int xStart;
/*     */   
/*     */   protected int yStart;
/*     */   
/*     */   protected int xPeriod;
/*     */   
/*     */   protected int yPeriod;
/*     */   
/*     */   private boolean checkForSkippedTiles;
/*     */   
/*     */   public StatisticsOpImage(RenderedImage source, ROI roi, int xStart, int yStart, int xPeriod, int yPeriod) {
/* 100 */     super(vectorize(source), new ImageLayout(source), (Map)null, false);
/* 105 */     this.roi = (roi == null) ? new ROIShape(getSource(0).getBounds()) : roi;
/* 107 */     this.xStart = xStart;
/* 108 */     this.yStart = yStart;
/* 109 */     this.xPeriod = xPeriod;
/* 110 */     this.yPeriod = yPeriod;
/* 112 */     this.checkForSkippedTiles = (xPeriod > this.tileWidth || yPeriod > this.tileHeight);
/*     */   }
/*     */   
/*     */   public boolean computesUniqueTiles() {
/* 124 */     return false;
/*     */   }
/*     */   
/*     */   public Raster getTile(int tileX, int tileY) {
/* 143 */     return getSource(0).getTile(tileX, tileY);
/*     */   }
/*     */   
/*     */   public Raster computeTile(int tileX, int tileY) {
/* 158 */     return getSource(0).getTile(tileX, tileY);
/*     */   }
/*     */   
/*     */   public Raster[] getTiles(Point[] tileIndices) {
/* 171 */     if (tileIndices == null)
/* 172 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 175 */     return getSource(0).getTiles(tileIndices);
/*     */   }
/*     */   
/*     */   public Rectangle mapSourceRect(Rectangle sourceRect, int sourceIndex) {
/* 193 */     if (sourceRect == null)
/* 194 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 197 */     if (sourceIndex != 0)
/* 198 */       throw new IllegalArgumentException(JaiI18N.getString("Generic1")); 
/* 200 */     return new Rectangle(sourceRect);
/*     */   }
/*     */   
/*     */   public Rectangle mapDestRect(Rectangle destRect, int sourceIndex) {
/* 218 */     if (destRect == null)
/* 219 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 222 */     if (sourceIndex != 0)
/* 223 */       throw new IllegalArgumentException(JaiI18N.getString("Generic1")); 
/* 225 */     return new Rectangle(destRect);
/*     */   }
/*     */   
/*     */   public Object getProperty(String name) {
/* 237 */     if (name == null)
/* 238 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 242 */     Object stats = super.getProperty(name);
/* 244 */     if (stats.equals(Image.UndefinedProperty))
/* 246 */       synchronized (this) {
/* 247 */         stats = createStatistics(name);
/* 249 */         if (!stats.equals(Image.UndefinedProperty)) {
/* 250 */           PlanarImage source = getSource(0);
/* 253 */           int minTileX = source.getMinTileX();
/* 254 */           int maxTileX = source.getMaxTileX();
/* 255 */           int minTileY = source.getMinTileY();
/* 256 */           int maxTileY = source.getMaxTileY();
/* 258 */           for (int y = minTileY; y <= maxTileY; y++) {
/* 259 */             for (int x = minTileX; x <= maxTileX; x++) {
/* 263 */               Rectangle tileRect = getTileRect(x, y);
/* 266 */               if (this.roi.intersects(tileRect)) {
/* 270 */                 if (this.checkForSkippedTiles && tileRect.x >= this.xStart && tileRect.y >= this.yStart) {
/* 274 */                   int offsetX = (this.xPeriod - (tileRect.x - this.xStart) % this.xPeriod) % this.xPeriod;
/* 278 */                   int offsetY = (this.yPeriod - (tileRect.y - this.yStart) % this.yPeriod) % this.yPeriod;
/* 285 */                   if (offsetX >= tileRect.width || offsetY >= tileRect.height)
/*     */                     continue; 
/*     */                 } 
/* 292 */                 accumulateStatistics(name, source.getData(tileRect), stats);
/*     */               } 
/*     */               continue;
/*     */             } 
/*     */           } 
/* 300 */           setProperty(name, stats);
/*     */         } 
/*     */       }  
/* 305 */     return stats;
/*     */   }
/*     */   
/*     */   public String[] getPropertyNames() {
/* 316 */     String[] statsNames = getStatisticsNames();
/* 317 */     String[] superNames = super.getPropertyNames();
/* 320 */     if (superNames == null)
/* 321 */       return statsNames; 
/* 325 */     Vector extraNames = new Vector();
/* 326 */     for (int i = 0; i < statsNames.length; i++) {
/* 327 */       String prefix = statsNames[i];
/* 328 */       String[] names = PropertyUtil.getPropertyNames(superNames, prefix);
/* 329 */       if (names != null)
/* 330 */         for (int k = 0; k < names.length; k++) {
/* 331 */           if (names[k].equalsIgnoreCase(prefix))
/* 332 */             extraNames.add(prefix); 
/*     */         }  
/*     */     } 
/* 339 */     if (extraNames.size() == 0)
/* 340 */       return superNames; 
/* 344 */     String[] propNames = new String[superNames.length + extraNames.size()];
/* 345 */     System.arraycopy(superNames, 0, propNames, 0, superNames.length);
/* 346 */     int offset = superNames.length;
/* 347 */     for (int j = 0; j < extraNames.size(); j++)
/* 348 */       propNames[offset++] = extraNames.get(j); 
/* 352 */     return propNames;
/*     */   }
/*     */   
/*     */   protected abstract String[] getStatisticsNames();
/*     */   
/*     */   protected abstract Object createStatistics(String paramString);
/*     */   
/*     */   protected abstract void accumulateStatistics(String paramString, Raster paramRaster, Object paramObject);
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\StatisticsOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */