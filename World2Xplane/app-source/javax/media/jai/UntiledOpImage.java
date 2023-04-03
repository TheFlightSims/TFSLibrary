/*     */ package javax.media.jai;
/*     */ 
/*     */ import java.awt.Point;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.util.Map;
/*     */ import java.util.Vector;
/*     */ 
/*     */ public abstract class UntiledOpImage extends OpImage {
/*     */   private static ImageLayout layoutHelper(ImageLayout layout, Vector sources) {
/*  58 */     if (sources.size() < 1)
/*  59 */       throw new IllegalArgumentException(JaiI18N.getString("Generic5")); 
/*  62 */     RenderedImage source = sources.get(0);
/*  64 */     ImageLayout il = (layout == null) ? new ImageLayout() : (ImageLayout)layout.clone();
/*  69 */     il.setTileGridXOffset(il.getMinX(source));
/*  70 */     il.setTileGridYOffset(il.getMinY(source));
/*  71 */     il.setTileWidth(il.getWidth(source));
/*  72 */     il.setTileHeight(il.getHeight(source));
/*  74 */     return il;
/*     */   }
/*     */   
/*     */   public UntiledOpImage(Vector sources, Map configuration, ImageLayout layout) {
/* 114 */     super(checkSourceVector(sources, true), layoutHelper(layout, sources), configuration, true);
/*     */   }
/*     */   
/*     */   public UntiledOpImage(RenderedImage source, Map configuration, ImageLayout layout) {
/* 150 */     super(vectorize(source), layoutHelper(layout, vectorize(source)), configuration, true);
/*     */   }
/*     */   
/*     */   public Rectangle mapSourceRect(Rectangle sourceRect, int sourceIndex) {
/* 166 */     return getBounds();
/*     */   }
/*     */   
/*     */   public Rectangle mapDestRect(Rectangle destRect, int sourceIndex) {
/* 181 */     if (sourceIndex < 0 || sourceIndex >= getNumSources())
/* 182 */       throw new IllegalArgumentException(JaiI18N.getString("Generic1")); 
/* 185 */     return getSource(sourceIndex).getBounds();
/*     */   }
/*     */   
/*     */   public Raster computeTile(int tileX, int tileY) {
/* 198 */     Point org = new Point(getMinX(), getMinY());
/* 199 */     WritableRaster dest = createWritableRaster(this.sampleModel, org);
/* 204 */     Rectangle destRect = getBounds();
/* 207 */     int numSources = getNumSources();
/* 208 */     Raster[] rasterSources = new Raster[numSources];
/*     */     int i;
/* 209 */     for (i = 0; i < numSources; i++) {
/* 210 */       PlanarImage source = getSource(i);
/* 211 */       Rectangle srcRect = mapDestRect(destRect, i);
/* 212 */       rasterSources[i] = source.getData(srcRect);
/*     */     } 
/* 216 */     computeImage(rasterSources, dest, destRect);
/* 218 */     for (i = 0; i < numSources; i++) {
/* 219 */       Raster sourceData = rasterSources[i];
/* 220 */       if (sourceData != null) {
/* 221 */         PlanarImage source = getSourceImage(i);
/* 224 */         if (source.overlapsMultipleTiles(sourceData.getBounds()))
/* 225 */           recycleTile(sourceData); 
/*     */       } 
/*     */     } 
/* 230 */     return dest;
/*     */   }
/*     */   
/*     */   protected abstract void computeImage(Raster[] paramArrayOfRaster, WritableRaster paramWritableRaster, Rectangle paramRectangle);
/*     */   
/*     */   public Point[] getTileDependencies(int tileX, int tileY, int sourceIndex) {
/* 258 */     PlanarImage source = getSource(sourceIndex);
/* 260 */     int minTileX = source.getMinTileX();
/* 261 */     int minTileY = source.getMinTileY();
/* 262 */     int maxTileX = minTileX + source.getNumXTiles() - 1;
/* 263 */     int maxTileY = minTileY + source.getNumYTiles() - 1;
/* 265 */     Point[] tileDependencies = new Point[(maxTileX - minTileX + 1) * (maxTileY - minTileY + 1)];
/* 268 */     int count = 0;
/* 269 */     for (int ty = minTileY; ty <= maxTileY; ty++) {
/* 270 */       for (int tx = minTileX; tx <= maxTileX; tx++)
/* 271 */         tileDependencies[count++] = new Point(tx, ty); 
/*     */     } 
/* 275 */     return tileDependencies;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\UntiledOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */