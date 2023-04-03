/*     */ package com.sun.media.jai.opimage;
/*     */ 
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.util.Map;
/*     */ import javax.media.jai.ImageLayout;
/*     */ import javax.media.jai.JAI;
/*     */ import javax.media.jai.OpImage;
/*     */ 
/*     */ public final class TranslateIntOpImage extends OpImage {
/*     */   private int transX;
/*     */   
/*     */   private int transY;
/*     */   
/*     */   private static ImageLayout layoutHelper(RenderedImage source, int transX, int transY) {
/*  39 */     ImageLayout layout = new ImageLayout(source.getMinX() + transX, source.getMinY() + transY, source.getWidth(), source.getHeight(), source.getTileGridXOffset() + transX, source.getTileGridYOffset() + transY, source.getTileWidth(), source.getTileHeight(), source.getSampleModel(), source.getColorModel());
/*  50 */     return layout;
/*     */   }
/*     */   
/*     */   private static Map configHelper(Map configuration) {
/*     */     Map config;
/*  58 */     if (configuration == null) {
/*  60 */       config = new RenderingHints(JAI.KEY_REPLACE_INDEX_COLOR_MODEL, Boolean.FALSE);
/*     */     } else {
/*  64 */       config = configuration;
/*  66 */       if (!config.containsKey(JAI.KEY_REPLACE_INDEX_COLOR_MODEL)) {
/*  68 */         RenderingHints hints = (RenderingHints)configuration;
/*  69 */         config = (RenderingHints)hints.clone();
/*  70 */         config.put(JAI.KEY_REPLACE_INDEX_COLOR_MODEL, Boolean.FALSE);
/*  71 */         config.remove(JAI.KEY_TILE_CACHE);
/*  73 */       } else if (config.containsKey(JAI.KEY_TILE_CACHE)) {
/*  75 */         RenderingHints hints = (RenderingHints)configuration;
/*  76 */         config = (RenderingHints)hints.clone();
/*  77 */         config.remove(JAI.KEY_TILE_CACHE);
/*     */       } 
/*     */     } 
/*  81 */     return config;
/*     */   }
/*     */   
/*     */   public TranslateIntOpImage(RenderedImage source, Map config, int transX, int transY) {
/* 100 */     super(vectorize(source), layoutHelper(source, transX, transY), configHelper(config), false);
/* 104 */     this.transX = transX;
/* 105 */     this.transY = transY;
/*     */   }
/*     */   
/*     */   public boolean computesUniqueTiles() {
/* 114 */     return false;
/*     */   }
/*     */   
/*     */   public Raster computeTile(int tileX, int tileY) {
/* 122 */     return getTile(tileX, tileY);
/*     */   }
/*     */   
/*     */   public Raster getTile(int tileX, int tileY) {
/* 132 */     Raster tile = getSource(0).getTile(tileX, tileY);
/* 134 */     if (tile == null)
/* 135 */       return null; 
/* 137 */     return tile.createTranslatedChild(tileXToX(tileX), tileYToY(tileY));
/*     */   }
/*     */   
/*     */   public Rectangle mapSourceRect(Rectangle sourceRect, int sourceIndex) {
/* 156 */     if (sourceRect == null)
/* 157 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 160 */     if (sourceIndex < 0 || sourceIndex >= getNumSources())
/* 161 */       throw new IllegalArgumentException(JaiI18N.getString("TranslateIntOpImage0")); 
/* 164 */     Rectangle r = new Rectangle(sourceRect);
/* 165 */     r.translate(this.transX, this.transY);
/* 166 */     return r;
/*     */   }
/*     */   
/*     */   public Rectangle mapDestRect(Rectangle destRect, int sourceIndex) {
/* 184 */     if (destRect == null)
/* 185 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 188 */     if (sourceIndex < 0 || sourceIndex >= getNumSources())
/* 189 */       throw new IllegalArgumentException(JaiI18N.getString("TranslateIntOpImage0")); 
/* 192 */     Rectangle r = new Rectangle(destRect);
/* 193 */     r.translate(-this.transX, -this.transY);
/* 194 */     return r;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\TranslateIntOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */