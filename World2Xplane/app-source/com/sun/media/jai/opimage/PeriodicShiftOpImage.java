/*     */ package com.sun.media.jai.opimage;
/*     */ 
/*     */ import com.sun.media.jai.util.JDKWorkarounds;
/*     */ import java.awt.Point;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.util.Map;
/*     */ import javax.media.jai.ImageLayout;
/*     */ import javax.media.jai.OpImage;
/*     */ 
/*     */ final class PeriodicShiftOpImage extends OpImage {
/*     */   private int[] xTrans;
/*     */   
/*     */   private int[] yTrans;
/*     */   
/*     */   private TranslateIntOpImage[] images;
/*     */   
/*     */   private Rectangle[] bounds;
/*     */   
/*     */   public PeriodicShiftOpImage(RenderedImage source, Map config, ImageLayout layout, int shiftX, int shiftY) {
/*  66 */     super(vectorize(source), (layout == null) ? new ImageLayout() : (ImageLayout)layout.clone(), config, false);
/*  72 */     this.xTrans = new int[] { -shiftX, -shiftX, this.width - shiftX, this.width - shiftX };
/*  74 */     this.yTrans = new int[] { -shiftY, this.height - shiftY, -shiftY, this.height - shiftY };
/*  78 */     this.images = new TranslateIntOpImage[4];
/*  79 */     for (int i = 0; i < 4; i++)
/*  80 */       this.images[i] = new TranslateIntOpImage(source, null, this.xTrans[i], this.yTrans[i]); 
/*  85 */     Rectangle destBounds = getBounds();
/*  86 */     this.bounds = new Rectangle[4];
/*  87 */     for (int j = 0; j < 4; j++)
/*  88 */       this.bounds[j] = destBounds.intersection(this.images[j].getBounds()); 
/*     */   }
/*     */   
/*     */   public Raster computeTile(int tileX, int tileY) {
/* 101 */     Point org = new Point(tileXToX(tileX), tileYToY(tileY));
/* 102 */     WritableRaster dest = createWritableRaster(this.sampleModel, org);
/* 105 */     Rectangle rect = new Rectangle(org.x, org.y, this.sampleModel.getWidth(), this.sampleModel.getHeight());
/* 108 */     Rectangle destRect = rect.intersection(getBounds());
/* 111 */     for (int i = 0; i < 4; i++) {
/* 113 */       Rectangle overlap = destRect.intersection(this.bounds[i]);
/* 116 */       if (!overlap.isEmpty())
/* 118 */         JDKWorkarounds.setRect(dest, this.images[i].getData(overlap)); 
/*     */     } 
/* 122 */     return dest;
/*     */   }
/*     */   
/*     */   public Rectangle mapSourceRect(Rectangle sourceRect, int sourceIndex) {
/* 141 */     if (sourceRect == null)
/* 142 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 145 */     if (sourceIndex < 0 || sourceIndex >= getNumSources())
/* 146 */       throw new IllegalArgumentException(JaiI18N.getString("PeriodicShiftOpImage0")); 
/* 149 */     Rectangle destRect = null;
/* 150 */     for (int i = 0; i < 4; i++) {
/* 151 */       Rectangle srcRect = sourceRect;
/* 152 */       srcRect.translate(this.xTrans[i], this.yTrans[i]);
/* 153 */       Rectangle overlap = srcRect.intersection(getBounds());
/* 154 */       if (!overlap.isEmpty())
/* 155 */         destRect = (destRect == null) ? overlap : destRect.union(overlap); 
/*     */     } 
/* 160 */     return destRect;
/*     */   }
/*     */   
/*     */   public Rectangle mapDestRect(Rectangle destRect, int sourceIndex) {
/* 178 */     if (destRect == null)
/* 179 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 182 */     if (sourceIndex < 0 || sourceIndex >= getNumSources())
/* 183 */       throw new IllegalArgumentException(JaiI18N.getString("PeriodicShiftOpImage0")); 
/* 186 */     Rectangle sourceRect = null;
/* 187 */     for (int i = 0; i < 4; i++) {
/* 188 */       Rectangle overlap = destRect.intersection(this.bounds[i]);
/* 189 */       if (!overlap.isEmpty()) {
/* 190 */         overlap.translate(-this.xTrans[i], -this.yTrans[i]);
/* 191 */         sourceRect = (sourceRect == null) ? overlap : sourceRect.union(overlap);
/*     */       } 
/*     */     } 
/* 196 */     return sourceRect;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\PeriodicShiftOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */