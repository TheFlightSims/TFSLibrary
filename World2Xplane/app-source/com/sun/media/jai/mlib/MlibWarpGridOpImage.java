/*     */ package com.sun.media.jai.mlib;
/*     */ 
/*     */ import com.sun.media.jai.util.ImageUtil;
/*     */ import com.sun.medialib.mlib.Image;
/*     */ import com.sun.medialib.mlib.mediaLibImage;
/*     */ import java.awt.Point;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.util.Map;
/*     */ import javax.media.jai.BorderExtender;
/*     */ import javax.media.jai.ImageLayout;
/*     */ import javax.media.jai.Interpolation;
/*     */ import javax.media.jai.Warp;
/*     */ import javax.media.jai.WarpGrid;
/*     */ import javax.media.jai.WarpOpImage;
/*     */ 
/*     */ final class MlibWarpGridOpImage extends WarpOpImage {
/*     */   private int xStart;
/*     */   
/*     */   private int xStep;
/*     */   
/*     */   private int xNumCells;
/*     */   
/*     */   private int xEnd;
/*     */   
/*     */   private int yStart;
/*     */   
/*     */   private int yStep;
/*     */   
/*     */   private int yNumCells;
/*     */   
/*     */   private int yEnd;
/*     */   
/*     */   private float[] xWarpPos;
/*     */   
/*     */   private float[] yWarpPos;
/*     */   
/*     */   private int filter;
/*     */   
/*     */   protected Rectangle backwardMapRect(Rectangle destRect, int sourceIndex) {
/*  99 */     Rectangle wrect = super.backwardMapRect(destRect, sourceIndex);
/* 105 */     wrect.setBounds(wrect.x - 1, wrect.y - 1, wrect.width + 2, wrect.height + 2);
/* 108 */     return wrect;
/*     */   }
/*     */   
/*     */   public MlibWarpGridOpImage(RenderedImage source, BorderExtender extender, Map config, ImageLayout layout, WarpGrid warp, Interpolation interp, int filter, double[] backgroundValues) {
/* 127 */     super(source, layout, config, true, extender, interp, (Warp)warp, backgroundValues);
/* 136 */     this.filter = filter;
/* 138 */     this.xStart = warp.getXStart();
/* 139 */     this.xStep = warp.getXStep();
/* 140 */     this.xNumCells = warp.getXNumCells();
/* 141 */     this.xEnd = this.xStart + this.xStep * this.xNumCells;
/* 143 */     this.yStart = warp.getYStart();
/* 144 */     this.yStep = warp.getYStep();
/* 145 */     this.yNumCells = warp.getYNumCells();
/* 146 */     this.yEnd = this.yStart + this.yStep * this.yNumCells;
/* 148 */     this.xWarpPos = warp.getXWarpPos();
/* 149 */     this.yWarpPos = warp.getYWarpPos();
/*     */   }
/*     */   
/*     */   public Raster computeTile(int tileX, int tileY) {
/* 170 */     Point org = new Point(tileXToX(tileX), tileYToY(tileY));
/* 173 */     WritableRaster dest = createWritableRaster(this.sampleModel, org);
/* 176 */     Rectangle rect0 = new Rectangle(org.x, org.y, this.tileWidth, this.tileHeight);
/* 177 */     Rectangle destRect = rect0.intersection(this.computableBounds);
/* 178 */     Rectangle destRect1 = rect0.intersection(getBounds());
/* 180 */     if (destRect.isEmpty()) {
/* 181 */       if (this.setBackground)
/* 182 */         ImageUtil.fillBackground(dest, destRect1, this.backgroundValues); 
/* 184 */       return dest;
/*     */     } 
/* 187 */     if (!destRect1.equals(destRect))
/* 189 */       ImageUtil.fillBordersWithBackgroundValues(destRect1, destRect, dest, this.backgroundValues); 
/* 192 */     Raster[] sources = new Raster[1];
/* 193 */     Rectangle srcBounds = getSourceImage(0).getBounds();
/* 195 */     int x0 = destRect.x;
/* 196 */     int x1 = x0 + destRect.width - 1;
/* 197 */     int y0 = destRect.y;
/* 198 */     int y1 = y0 + destRect.height - 1;
/* 200 */     if (x0 >= this.xEnd || x1 < this.xStart || y0 >= this.yEnd || y1 < this.yStart) {
/* 202 */       Rectangle rect = srcBounds.intersection(destRect);
/* 204 */       if (!rect.isEmpty()) {
/* 205 */         sources[0] = getSourceImage(0).getData(rect);
/* 206 */         copyRect(sources, dest, rect);
/* 209 */         if (getSourceImage(0).overlapsMultipleTiles(rect))
/* 210 */           recycleTile(sources[0]); 
/*     */       } 
/* 214 */       return dest;
/*     */     } 
/* 217 */     if (x0 < this.xStart) {
/* 218 */       Rectangle rect = srcBounds.intersection(new Rectangle(x0, y0, this.xStart - x0, y1 - y0 + 1));
/* 221 */       if (!rect.isEmpty()) {
/* 222 */         sources[0] = getSourceImage(0).getData(rect);
/* 223 */         copyRect(sources, dest, rect);
/* 226 */         if (getSourceImage(0).overlapsMultipleTiles(rect))
/* 227 */           recycleTile(sources[0]); 
/*     */       } 
/* 231 */       x0 = this.xStart;
/*     */     } 
/* 234 */     if (x1 >= this.xEnd) {
/* 235 */       Rectangle rect = srcBounds.intersection(new Rectangle(this.xEnd, y0, x1 - this.xEnd + 1, y1 - y0 + 1));
/* 238 */       if (!rect.isEmpty()) {
/* 239 */         sources[0] = getSourceImage(0).getData(rect);
/* 240 */         copyRect(sources, dest, rect);
/* 243 */         if (getSourceImage(0).overlapsMultipleTiles(rect))
/* 244 */           recycleTile(sources[0]); 
/*     */       } 
/* 248 */       x1 = this.xEnd - 1;
/*     */     } 
/* 251 */     if (y0 < this.yStart) {
/* 252 */       Rectangle rect = srcBounds.intersection(new Rectangle(x0, y0, x1 - x0 + 1, this.yStart - y0));
/* 255 */       if (!rect.isEmpty()) {
/* 256 */         sources[0] = getSourceImage(0).getData(rect);
/* 257 */         copyRect(sources, dest, rect);
/* 260 */         if (getSourceImage(0).overlapsMultipleTiles(rect))
/* 261 */           recycleTile(sources[0]); 
/*     */       } 
/* 265 */       y0 = this.yStart;
/*     */     } 
/* 268 */     if (y1 >= this.yEnd) {
/* 269 */       Rectangle rect = srcBounds.intersection(new Rectangle(x0, this.yEnd, x1 - x0 + 1, y1 - this.yEnd + 1));
/* 272 */       if (!rect.isEmpty()) {
/* 273 */         sources[0] = getSourceImage(0).getData(rect);
/* 274 */         copyRect(sources, dest, rect);
/* 277 */         if (getSourceImage(0).overlapsMultipleTiles(rect))
/* 278 */           recycleTile(sources[0]); 
/*     */       } 
/* 282 */       y1 = this.yEnd - 1;
/*     */     } 
/* 286 */     destRect = new Rectangle(x0, y0, x1 - x0 + 1, y1 - y0 + 1);
/* 289 */     Rectangle srcRect = backwardMapRect(destRect, 0).intersection(srcBounds);
/* 291 */     if (!srcRect.isEmpty()) {
/* 293 */       int l = (this.interp == null) ? 0 : this.interp.getLeftPadding();
/* 294 */       int r = (this.interp == null) ? 0 : this.interp.getRightPadding();
/* 295 */       int t = (this.interp == null) ? 0 : this.interp.getTopPadding();
/* 296 */       int b = (this.interp == null) ? 0 : this.interp.getBottomPadding();
/* 298 */       srcRect = new Rectangle(srcRect.x - l, srcRect.y - t, srcRect.width + l + r, srcRect.height + t + b);
/* 303 */       sources[0] = (getBorderExtender() != null) ? getSourceImage(0).getExtendedData(srcRect, this.extender) : getSourceImage(0).getData(srcRect);
/* 307 */       computeRect(sources, dest, destRect);
/* 310 */       if (getSourceImage(0).overlapsMultipleTiles(srcRect))
/* 311 */         recycleTile(sources[0]); 
/*     */     } 
/* 315 */     return dest;
/*     */   }
/*     */   
/*     */   protected void computeRect(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/* 325 */     int i, formatTag = MediaLibAccessor.findCompatibleTag(sources, dest);
/* 327 */     Raster source = sources[0];
/* 329 */     MediaLibAccessor srcMA = new MediaLibAccessor(source, source.getBounds(), formatTag);
/* 331 */     MediaLibAccessor dstMA = new MediaLibAccessor(dest, destRect, formatTag);
/* 334 */     mediaLibImage[] srcMLI = srcMA.getMediaLibImages();
/* 335 */     mediaLibImage[] dstMLI = dstMA.getMediaLibImages();
/* 337 */     switch (dstMA.getDataType()) {
/*     */       case 0:
/*     */       case 1:
/*     */       case 2:
/*     */       case 3:
/* 342 */         if (this.setBackground) {
/* 343 */           for (int j = 0; j < dstMLI.length; j++)
/* 344 */             Image.GridWarp2(dstMLI[j], srcMLI[j], this.xWarpPos, this.yWarpPos, source.getMinX(), source.getMinY(), this.xStart - destRect.x, this.xStep, this.xNumCells, this.yStart - destRect.y, this.yStep, this.yNumCells, this.filter, 0, this.intBackgroundValues); 
/*     */           break;
/*     */         } 
/* 357 */         for (i = 0; i < dstMLI.length; i++) {
/* 358 */           Image.GridWarp(dstMLI[i], srcMLI[i], this.xWarpPos, this.yWarpPos, source.getMinX(), source.getMinY(), this.xStart - destRect.x, this.xStep, this.xNumCells, this.yStart - destRect.y, this.yStep, this.yNumCells, this.filter, 0);
/* 368 */           MlibUtils.clampImage(dstMLI[i], getColorModel());
/*     */         } 
/*     */         break;
/*     */       case 4:
/*     */       case 5:
/* 374 */         if (this.setBackground) {
/* 375 */           for (i = 0; i < dstMLI.length; i++)
/* 376 */             Image.GridWarp2_Fp(dstMLI[i], srcMLI[i], this.xWarpPos, this.yWarpPos, source.getMinX(), source.getMinY(), this.xStart - destRect.x, this.xStep, this.xNumCells, this.yStart - destRect.y, this.yStep, this.yNumCells, this.filter, 0, this.backgroundValues); 
/*     */           break;
/*     */         } 
/* 389 */         for (i = 0; i < dstMLI.length; i++)
/* 390 */           Image.GridWarp_Fp(dstMLI[i], srcMLI[i], this.xWarpPos, this.yWarpPos, source.getMinX(), source.getMinY(), this.xStart - destRect.x, this.xStep, this.xNumCells, this.yStart - destRect.y, this.yStep, this.yNumCells, this.filter, 0); 
/*     */         break;
/*     */       default:
/* 404 */         throw new RuntimeException(JaiI18N.getString("Generic2"));
/*     */     } 
/* 407 */     if (dstMA.isDataCopy()) {
/* 408 */       dstMA.clampDataArrays();
/* 409 */       dstMA.copyDataToRaster();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void copyRect(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/* 421 */     int formatTag = MediaLibAccessor.findCompatibleTag(sources, dest);
/* 423 */     MediaLibAccessor srcMA = new MediaLibAccessor(sources[0], destRect, formatTag);
/* 425 */     MediaLibAccessor dstMA = new MediaLibAccessor(dest, destRect, formatTag);
/* 428 */     mediaLibImage[] srcMLI = srcMA.getMediaLibImages();
/* 429 */     mediaLibImage[] dstMLI = dstMA.getMediaLibImages();
/* 431 */     for (int i = 0; i < dstMLI.length; i++)
/* 432 */       Image.Copy(dstMLI[i], srcMLI[i]); 
/* 435 */     if (dstMA.isDataCopy())
/* 436 */       dstMA.copyDataToRaster(); 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\mlib\MlibWarpGridOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */