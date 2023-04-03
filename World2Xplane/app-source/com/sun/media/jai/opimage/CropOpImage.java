/*     */ package com.sun.media.jai.opimage;
/*     */ 
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.RenderedImage;
/*     */ import javax.media.jai.ImageLayout;
/*     */ import javax.media.jai.PointOpImage;
/*     */ 
/*     */ final class CropOpImage extends PointOpImage {
/*     */   private static ImageLayout layoutHelper(RenderedImage source, float originX, float originY, float width, float height) {
/*  47 */     Rectangle bounds = (new Rectangle2D.Float(originX, originY, width, height)).getBounds();
/*  50 */     return new ImageLayout(bounds.x, bounds.y, bounds.width, bounds.height, source.getTileGridXOffset(), source.getTileGridYOffset(), source.getTileWidth(), source.getTileHeight(), source.getSampleModel(), source.getColorModel());
/*     */   }
/*     */   
/*     */   public CropOpImage(RenderedImage source, float originX, float originY, float width, float height) {
/*  77 */     super(source, layoutHelper(source, originX, originY, width, height), null, false);
/*     */   }
/*     */   
/*     */   public boolean computesUniqueTiles() {
/*  89 */     return false;
/*     */   }
/*     */   
/*     */   public Raster computeTile(int tileX, int tileY) {
/*  97 */     return getTile(tileX, tileY);
/*     */   }
/*     */   
/*     */   public Raster getTile(int tileX, int tileY) {
/* 112 */     Raster tile = null;
/* 115 */     if (tileX >= getMinTileX() && tileX <= getMaxTileX() && tileY >= getMinTileY() && tileY <= getMaxTileY())
/* 122 */       tile = getSourceImage(0).getTile(tileX, tileY); 
/* 125 */     return tile;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\CropOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */