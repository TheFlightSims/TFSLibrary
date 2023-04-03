/*     */ package com.sun.media.jai.opimage;
/*     */ 
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.util.Map;
/*     */ import javax.media.jai.BorderExtender;
/*     */ import javax.media.jai.ImageLayout;
/*     */ import javax.media.jai.OpImage;
/*     */ 
/*     */ final class BorderOpImage extends OpImage {
/*     */   protected BorderExtender extender;
/*     */   
/*     */   public BorderOpImage(RenderedImage source, Map config, ImageLayout layout, int leftPad, int rightPad, int topPad, int bottomPad, BorderExtender extender) {
/* 103 */     super(vectorize(source), layoutHelper(layout, source, leftPad, rightPad, topPad, bottomPad), config, true);
/* 108 */     this.extender = extender;
/*     */   }
/*     */   
/*     */   private static ImageLayout layoutHelper(ImageLayout layout, RenderedImage source, int leftPad, int rightPad, int topPad, int bottomPad) {
/* 125 */     ImageLayout il = (layout == null) ? new ImageLayout() : (ImageLayout)layout.clone();
/* 129 */     il.setMinX(source.getMinX() - leftPad);
/* 130 */     il.setMinY(source.getMinY() - topPad);
/* 131 */     il.setWidth(source.getWidth() + leftPad + rightPad);
/* 132 */     il.setHeight(source.getHeight() + topPad + bottomPad);
/* 136 */     if (!il.isValid(16))
/* 137 */       il.setTileGridXOffset(il.getMinX(null)); 
/* 140 */     if (!il.isValid(32))
/* 141 */       il.setTileGridYOffset(il.getMinY(null)); 
/* 145 */     il.setSampleModel(source.getSampleModel());
/* 146 */     il.setColorModel(source.getColorModel());
/* 148 */     return il;
/*     */   }
/*     */   
/*     */   public Rectangle mapSourceRect(Rectangle sourceRect, int sourceIndex) {
/* 167 */     if (sourceRect == null)
/* 168 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 171 */     if (sourceIndex < 0 || sourceIndex >= getNumSources())
/* 172 */       throw new IllegalArgumentException(JaiI18N.getString("BorderOpImage0")); 
/* 175 */     return new Rectangle(sourceRect);
/*     */   }
/*     */   
/*     */   public Rectangle mapDestRect(Rectangle destRect, int sourceIndex) {
/* 193 */     if (destRect == null)
/* 194 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 197 */     if (sourceIndex < 0 || sourceIndex >= getNumSources())
/* 198 */       throw new IllegalArgumentException(JaiI18N.getString("BorderOpImage2")); 
/* 201 */     Rectangle srcBounds = getSourceImage(0).getBounds();
/* 202 */     return destRect.intersection(srcBounds);
/*     */   }
/*     */   
/*     */   public Raster computeTile(int tileX, int tileY) {
/* 208 */     WritableRaster dest = createTile(tileX, tileY);
/* 211 */     getSourceImage(0).copyExtendedData(dest, this.extender);
/* 213 */     return dest;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\BorderOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */