/*     */ package com.sun.media.jai.mlib;
/*     */ 
/*     */ import com.sun.media.jai.util.ImageUtil;
/*     */ import com.sun.medialib.mlib.Image;
/*     */ import com.sun.medialib.mlib.mediaLibImage;
/*     */ import com.sun.medialib.mlib.mediaLibImageInterpTable;
/*     */ import java.awt.Point;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.util.Map;
/*     */ import javax.media.jai.BorderExtender;
/*     */ import javax.media.jai.ImageLayout;
/*     */ import javax.media.jai.Interpolation;
/*     */ import javax.media.jai.InterpolationTable;
/*     */ import javax.media.jai.Warp;
/*     */ import javax.media.jai.WarpGrid;
/*     */ import javax.media.jai.WarpOpImage;
/*     */ 
/*     */ final class MlibWarpGridTableOpImage extends WarpOpImage {
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
/*     */   private mediaLibImageInterpTable mlibInterpTableI;
/*     */   
/*     */   private mediaLibImageInterpTable mlibInterpTableF;
/*     */   
/*     */   private mediaLibImageInterpTable mlibInterpTableD;
/*     */   
/*     */   public MlibWarpGridTableOpImage(RenderedImage source, BorderExtender extender, Map config, ImageLayout layout, WarpGrid warp, Interpolation interp, double[] backgroundValues) {
/*  95 */     super(source, layout, config, true, extender, interp, (Warp)warp, backgroundValues);
/* 104 */     this.mlibInterpTableI = null;
/* 105 */     this.mlibInterpTableF = null;
/* 106 */     this.mlibInterpTableD = null;
/* 108 */     this.xStart = warp.getXStart();
/* 109 */     this.xStep = warp.getXStep();
/* 110 */     this.xNumCells = warp.getXNumCells();
/* 111 */     this.xEnd = this.xStart + this.xStep * this.xNumCells;
/* 113 */     this.yStart = warp.getYStart();
/* 114 */     this.yStep = warp.getYStep();
/* 115 */     this.yNumCells = warp.getYNumCells();
/* 116 */     this.yEnd = this.yStart + this.yStep * this.yNumCells;
/* 118 */     this.xWarpPos = warp.getXWarpPos();
/* 119 */     this.yWarpPos = warp.getYWarpPos();
/*     */   }
/*     */   
/*     */   protected Rectangle backwardMapRect(Rectangle destRect, int sourceIndex) {
/* 141 */     Rectangle wrect = super.backwardMapRect(destRect, sourceIndex);
/* 147 */     wrect.setBounds(wrect.x - 1, wrect.y - 1, wrect.width + 2, wrect.height + 2);
/* 150 */     return wrect;
/*     */   }
/*     */   
/*     */   public Raster computeTile(int tileX, int tileY) {
/* 171 */     Point org = new Point(tileXToX(tileX), tileYToY(tileY));
/* 174 */     WritableRaster dest = createWritableRaster(this.sampleModel, org);
/* 177 */     Rectangle rect0 = new Rectangle(org.x, org.y, this.tileWidth, this.tileHeight);
/* 178 */     Rectangle destRect = rect0.intersection(this.computableBounds);
/* 179 */     Rectangle destRect1 = rect0.intersection(getBounds());
/* 181 */     if (destRect.isEmpty()) {
/* 182 */       if (this.setBackground)
/* 183 */         ImageUtil.fillBackground(dest, destRect1, this.backgroundValues); 
/* 185 */       return dest;
/*     */     } 
/* 188 */     if (!destRect1.equals(destRect))
/* 190 */       ImageUtil.fillBordersWithBackgroundValues(destRect1, destRect, dest, this.backgroundValues); 
/* 193 */     Raster[] sources = new Raster[1];
/* 194 */     Rectangle srcBounds = getSourceImage(0).getBounds();
/* 196 */     int x0 = destRect.x;
/* 197 */     int x1 = x0 + destRect.width - 1;
/* 198 */     int y0 = destRect.y;
/* 199 */     int y1 = y0 + destRect.height - 1;
/* 201 */     if (x0 >= this.xEnd || x1 < this.xStart || y0 >= this.yEnd || y1 < this.yStart) {
/* 203 */       Rectangle rect = srcBounds.intersection(destRect);
/* 205 */       if (!rect.isEmpty()) {
/* 206 */         sources[0] = getSourceImage(0).getData(rect);
/* 207 */         copyRect(sources, dest, rect);
/* 210 */         if (getSourceImage(0).overlapsMultipleTiles(rect))
/* 211 */           recycleTile(sources[0]); 
/*     */       } 
/* 215 */       return dest;
/*     */     } 
/* 218 */     if (x0 < this.xStart) {
/* 219 */       Rectangle rect = srcBounds.intersection(new Rectangle(x0, y0, this.xStart - x0, y1 - y0 + 1));
/* 222 */       if (!rect.isEmpty()) {
/* 223 */         sources[0] = getSourceImage(0).getData(rect);
/* 224 */         copyRect(sources, dest, rect);
/* 227 */         if (getSourceImage(0).overlapsMultipleTiles(rect))
/* 228 */           recycleTile(sources[0]); 
/*     */       } 
/* 232 */       x0 = this.xStart;
/*     */     } 
/* 235 */     if (x1 >= this.xEnd) {
/* 236 */       Rectangle rect = srcBounds.intersection(new Rectangle(this.xEnd, y0, x1 - this.xEnd + 1, y1 - y0 + 1));
/* 239 */       if (!rect.isEmpty()) {
/* 240 */         sources[0] = getSourceImage(0).getData(rect);
/* 241 */         copyRect(sources, dest, rect);
/* 244 */         if (getSourceImage(0).overlapsMultipleTiles(rect))
/* 245 */           recycleTile(sources[0]); 
/*     */       } 
/* 249 */       x1 = this.xEnd - 1;
/*     */     } 
/* 252 */     if (y0 < this.yStart) {
/* 253 */       Rectangle rect = srcBounds.intersection(new Rectangle(x0, y0, x1 - x0 + 1, this.yStart - y0));
/* 256 */       if (!rect.isEmpty()) {
/* 257 */         sources[0] = getSourceImage(0).getData(rect);
/* 258 */         copyRect(sources, dest, rect);
/* 261 */         if (getSourceImage(0).overlapsMultipleTiles(rect))
/* 262 */           recycleTile(sources[0]); 
/*     */       } 
/* 266 */       y0 = this.yStart;
/*     */     } 
/* 269 */     if (y1 >= this.yEnd) {
/* 270 */       Rectangle rect = srcBounds.intersection(new Rectangle(x0, this.yEnd, x1 - x0 + 1, y1 - this.yEnd + 1));
/* 273 */       if (!rect.isEmpty()) {
/* 274 */         sources[0] = getSourceImage(0).getData(rect);
/* 275 */         copyRect(sources, dest, rect);
/* 278 */         if (getSourceImage(0).overlapsMultipleTiles(rect))
/* 279 */           recycleTile(sources[0]); 
/*     */       } 
/* 283 */       y1 = this.yEnd - 1;
/*     */     } 
/* 287 */     destRect = new Rectangle(x0, y0, x1 - x0 + 1, y1 - y0 + 1);
/* 290 */     Rectangle srcRect = backwardMapRect(destRect, 0).intersection(srcBounds);
/* 292 */     if (!srcRect.isEmpty()) {
/* 294 */       int l = (this.interp == null) ? 0 : this.interp.getLeftPadding();
/* 295 */       int r = (this.interp == null) ? 0 : this.interp.getRightPadding();
/* 296 */       int t = (this.interp == null) ? 0 : this.interp.getTopPadding();
/* 297 */       int b = (this.interp == null) ? 0 : this.interp.getBottomPadding();
/* 299 */       srcRect = new Rectangle(srcRect.x - l, srcRect.y - t, srcRect.width + l + r, srcRect.height + t + b);
/* 304 */       sources[0] = (getBorderExtender() != null) ? getSourceImage(0).getExtendedData(srcRect, this.extender) : getSourceImage(0).getData(srcRect);
/* 308 */       computeRect(sources, dest, destRect);
/* 311 */       if (getSourceImage(0).overlapsMultipleTiles(srcRect))
/* 312 */         recycleTile(sources[0]); 
/*     */     } 
/* 316 */     return dest;
/*     */   }
/*     */   
/*     */   protected void computeRect(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/* 327 */     int i, formatTag = MediaLibAccessor.findCompatibleTag(sources, dest);
/* 329 */     Raster source = sources[0];
/* 331 */     MediaLibAccessor srcMA = new MediaLibAccessor(source, source.getBounds(), formatTag);
/* 333 */     MediaLibAccessor dstMA = new MediaLibAccessor(dest, destRect, formatTag);
/* 336 */     mediaLibImage[] srcMLI = srcMA.getMediaLibImages();
/* 337 */     mediaLibImage[] dstMLI = dstMA.getMediaLibImages();
/* 339 */     switch (dstMA.getDataType()) {
/*     */       case 0:
/*     */       case 1:
/*     */       case 2:
/*     */       case 3:
/* 346 */         if (this.mlibInterpTableI == null) {
/* 347 */           InterpolationTable jtable = (InterpolationTable)this.interp;
/* 348 */           this.mlibInterpTableI = new mediaLibImageInterpTable(3, jtable.getWidth(), jtable.getHeight(), jtable.getLeftPadding(), jtable.getTopPadding(), jtable.getSubsampleBitsH(), jtable.getSubsampleBitsV(), jtable.getPrecisionBits(), jtable.getHorizontalTableData(), jtable.getVerticalTableData());
/*     */         } 
/* 362 */         if (this.setBackground) {
/* 363 */           for (int j = 0; j < dstMLI.length; j++)
/* 364 */             Image.GridWarpTable2(dstMLI[j], srcMLI[j], this.xWarpPos, this.yWarpPos, source.getMinX(), source.getMinY(), this.xStart - destRect.x, this.xStep, this.xNumCells, this.yStart - destRect.y, this.yStep, this.yNumCells, this.mlibInterpTableI, 0, this.intBackgroundValues); 
/*     */           break;
/*     */         } 
/* 377 */         for (i = 0; i < dstMLI.length; i++) {
/* 378 */           Image.GridWarpTable(dstMLI[i], srcMLI[i], this.xWarpPos, this.yWarpPos, source.getMinX(), source.getMinY(), this.xStart - destRect.x, this.xStep, this.xNumCells, this.yStart - destRect.y, this.yStep, this.yNumCells, this.mlibInterpTableI, 0);
/* 388 */           MlibUtils.clampImage(dstMLI[i], getColorModel());
/*     */         } 
/*     */         break;
/*     */       case 4:
/* 394 */         if (this.mlibInterpTableF == null) {
/* 395 */           InterpolationTable jtable = (InterpolationTable)this.interp;
/* 396 */           this.mlibInterpTableF = new mediaLibImageInterpTable(4, jtable.getWidth(), jtable.getHeight(), jtable.getLeftPadding(), jtable.getTopPadding(), jtable.getSubsampleBitsH(), jtable.getSubsampleBitsV(), jtable.getPrecisionBits(), jtable.getHorizontalTableDataFloat(), jtable.getVerticalTableDataFloat());
/*     */         } 
/* 410 */         if (this.setBackground) {
/* 411 */           for (i = 0; i < dstMLI.length; i++)
/* 412 */             Image.GridWarpTable2_Fp(dstMLI[i], srcMLI[i], this.xWarpPos, this.yWarpPos, source.getMinX(), source.getMinY(), this.xStart - destRect.x, this.xStep, this.xNumCells, this.yStart - destRect.y, this.yStep, this.yNumCells, this.mlibInterpTableF, 0, this.backgroundValues); 
/*     */           break;
/*     */         } 
/* 425 */         for (i = 0; i < dstMLI.length; i++)
/* 426 */           Image.GridWarpTable_Fp(dstMLI[i], srcMLI[i], this.xWarpPos, this.yWarpPos, source.getMinX(), source.getMinY(), this.xStart - destRect.x, this.xStep, this.xNumCells, this.yStart - destRect.y, this.yStep, this.yNumCells, this.mlibInterpTableF, 0); 
/*     */         break;
/*     */       case 5:
/* 440 */         if (this.mlibInterpTableD == null) {
/* 441 */           InterpolationTable jtable = (InterpolationTable)this.interp;
/* 442 */           this.mlibInterpTableD = new mediaLibImageInterpTable(5, jtable.getWidth(), jtable.getHeight(), jtable.getLeftPadding(), jtable.getTopPadding(), jtable.getSubsampleBitsH(), jtable.getSubsampleBitsV(), jtable.getPrecisionBits(), jtable.getHorizontalTableDataDouble(), jtable.getVerticalTableDataDouble());
/*     */         } 
/* 455 */         if (this.setBackground) {
/* 456 */           for (i = 0; i < dstMLI.length; i++)
/* 457 */             Image.GridWarpTable2_Fp(dstMLI[i], srcMLI[i], this.xWarpPos, this.yWarpPos, source.getMinX(), source.getMinY(), this.xStart - destRect.x, this.xStep, this.xNumCells, this.yStart - destRect.y, this.yStep, this.yNumCells, this.mlibInterpTableD, 0, this.backgroundValues); 
/*     */           break;
/*     */         } 
/* 470 */         for (i = 0; i < dstMLI.length; i++)
/* 471 */           Image.GridWarpTable_Fp(dstMLI[i], srcMLI[i], this.xWarpPos, this.yWarpPos, source.getMinX(), source.getMinY(), this.xStart - destRect.x, this.xStep, this.xNumCells, this.yStart - destRect.y, this.yStep, this.yNumCells, this.mlibInterpTableD, 0); 
/*     */         break;
/*     */       default:
/* 485 */         throw new RuntimeException(JaiI18N.getString("Generic2"));
/*     */     } 
/* 488 */     if (dstMA.isDataCopy()) {
/* 489 */       dstMA.clampDataArrays();
/* 490 */       dstMA.copyDataToRaster();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void copyRect(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/* 502 */     int formatTag = MediaLibAccessor.findCompatibleTag(sources, dest);
/* 504 */     MediaLibAccessor srcMA = new MediaLibAccessor(sources[0], destRect, formatTag);
/* 506 */     MediaLibAccessor dstMA = new MediaLibAccessor(dest, destRect, formatTag);
/* 509 */     mediaLibImage[] srcMLI = srcMA.getMediaLibImages();
/* 510 */     mediaLibImage[] dstMLI = dstMA.getMediaLibImages();
/* 512 */     for (int i = 0; i < dstMLI.length; i++)
/* 513 */       Image.Copy(dstMLI[i], srcMLI[i]); 
/* 516 */     if (dstMA.isDataCopy())
/* 517 */       dstMA.copyDataToRaster(); 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\mlib\MlibWarpGridTableOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */