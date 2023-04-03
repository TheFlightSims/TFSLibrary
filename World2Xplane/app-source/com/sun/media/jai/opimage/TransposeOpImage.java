/*     */ package com.sun.media.jai.opimage;
/*     */ 
/*     */ import java.awt.Point;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.image.ColorModel;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.util.Map;
/*     */ import javax.media.jai.GeometricOpImage;
/*     */ import javax.media.jai.ImageLayout;
/*     */ import javax.media.jai.IntegerSequence;
/*     */ import javax.media.jai.PlanarImage;
/*     */ import javax.media.jai.RasterAccessor;
/*     */ import javax.media.jai.RasterFormatTag;
/*     */ 
/*     */ public class TransposeOpImage extends GeometricOpImage {
/*     */   protected int type;
/*     */   
/*     */   protected int src_width;
/*     */   
/*     */   protected int src_height;
/*     */   
/*     */   protected Rectangle sourceBounds;
/*     */   
/*     */   private static ImageLayout layoutHelper(ImageLayout layout, RenderedImage source, int type) {
/*     */     ImageLayout newLayout;
/*  56 */     if (layout != null) {
/*  57 */       newLayout = (ImageLayout)layout.clone();
/*     */     } else {
/*  59 */       newLayout = new ImageLayout();
/*     */     } 
/*  64 */     Rectangle sourceBounds = new Rectangle(source.getMinX(), source.getMinY(), source.getWidth(), source.getHeight());
/*  68 */     Rectangle rect = mapRect(sourceBounds, sourceBounds, type, true);
/*  70 */     newLayout.setMinX(rect.x);
/*  71 */     newLayout.setMinY(rect.y);
/*  72 */     newLayout.setWidth(rect.width);
/*  73 */     newLayout.setHeight(rect.height);
/*  76 */     Rectangle tileRect = new Rectangle(source.getTileGridXOffset(), source.getTileGridYOffset(), source.getTileWidth(), source.getTileHeight());
/*  80 */     rect = mapRect(tileRect, sourceBounds, type, true);
/*  83 */     if (newLayout.isValid(16))
/*  84 */       newLayout.setTileGridXOffset(rect.x); 
/*  86 */     if (newLayout.isValid(32))
/*  87 */       newLayout.setTileGridYOffset(rect.y); 
/*  89 */     if (newLayout.isValid(64))
/*  90 */       newLayout.setTileWidth(Math.abs(rect.width)); 
/*  92 */     if (newLayout.isValid(128))
/*  93 */       newLayout.setTileHeight(Math.abs(rect.height)); 
/*  96 */     return newLayout;
/*     */   }
/*     */   
/*     */   public TransposeOpImage(RenderedImage source, Map config, ImageLayout layout, int type) {
/* 116 */     super(vectorize(source), layoutHelper(layout, source, type), config, true, null, null, null);
/* 130 */     ColorModel srcColorModel = source.getColorModel();
/* 131 */     if (srcColorModel instanceof java.awt.image.IndexColorModel) {
/* 132 */       this.sampleModel = source.getSampleModel().createCompatibleSampleModel(this.tileWidth, this.tileHeight);
/* 134 */       this.colorModel = srcColorModel;
/*     */     } 
/* 138 */     this.type = type;
/* 141 */     this.src_width = source.getWidth();
/* 142 */     this.src_height = source.getHeight();
/* 144 */     this.sourceBounds = new Rectangle(source.getMinX(), source.getMinY(), source.getWidth(), source.getHeight());
/*     */   }
/*     */   
/*     */   protected Rectangle forwardMapRect(Rectangle sourceRect, int sourceIndex) {
/* 155 */     return mapRect(sourceRect, this.sourceBounds, this.type, true);
/*     */   }
/*     */   
/*     */   protected Rectangle backwardMapRect(Rectangle destRect, int sourceIndex) {
/* 163 */     return mapRect(destRect, this.sourceBounds, this.type, false);
/*     */   }
/*     */   
/*     */   protected static void mapPoint(int[] pt, int minX, int minY, int maxX, int maxY, int type, boolean mapForwards) {
/* 184 */     int sx = pt[0];
/* 185 */     int sy = pt[1];
/* 186 */     int dx = -1;
/* 187 */     int dy = -1;
/* 189 */     switch (type) {
/*     */       case 0:
/* 191 */         dx = sx;
/* 192 */         dy = minY + maxY - sy;
/*     */         break;
/*     */       case 1:
/* 196 */         dx = minX + maxX - sx;
/* 197 */         dy = sy;
/*     */         break;
/*     */       case 2:
/* 201 */         dx = minX - minY + sy;
/* 202 */         dy = minY - minX + sx;
/*     */         break;
/*     */       case 3:
/* 206 */         if (mapForwards) {
/* 207 */           dx = minX + maxY - sy;
/* 208 */           dy = minY + maxX - sx;
/*     */           break;
/*     */         } 
/* 210 */         dx = minY + maxX - sy;
/* 211 */         dy = minX + maxY - sx;
/*     */         break;
/*     */       case 4:
/* 216 */         if (mapForwards) {
/* 217 */           dx = minX + maxY - sy;
/* 218 */           dy = minY - minX + sx;
/*     */           break;
/*     */         } 
/* 220 */         dx = minX - minY + sy;
/* 221 */         dy = minX + maxY - sx;
/*     */         break;
/*     */       case 5:
/* 226 */         dx = minX + maxX - sx;
/* 227 */         dy = minY + maxY - sy;
/*     */         break;
/*     */       case 6:
/* 231 */         if (mapForwards) {
/* 232 */           dx = minX - minY + sy;
/* 233 */           dy = maxX + minY - sx;
/*     */           break;
/*     */         } 
/* 235 */         dx = maxX + minY - sy;
/* 236 */         dy = minY - minX + sx;
/*     */         break;
/*     */     } 
/* 241 */     pt[0] = dx;
/* 242 */     pt[1] = dy;
/*     */   }
/*     */   
/*     */   private static Rectangle mapRect(Rectangle rect, Rectangle sourceBounds, int type, boolean mapForwards) {
/* 249 */     int sMinX = sourceBounds.x;
/* 250 */     int sMinY = sourceBounds.y;
/* 251 */     int sMaxX = sMinX + sourceBounds.width - 1;
/* 252 */     int sMaxY = sMinY + sourceBounds.height - 1;
/* 255 */     int[] pt = new int[2];
/* 256 */     pt[0] = rect.x;
/* 257 */     pt[1] = rect.y;
/* 258 */     mapPoint(pt, sMinX, sMinY, sMaxX, sMaxY, type, mapForwards);
/* 259 */     int dMaxX = pt[0], dMinX = dMaxX;
/* 260 */     int dMaxY = pt[1], dMinY = dMaxY;
/* 262 */     pt[0] = rect.x + rect.width - 1;
/* 263 */     pt[1] = rect.y;
/* 264 */     mapPoint(pt, sMinX, sMinY, sMaxX, sMaxY, type, mapForwards);
/* 265 */     dMinX = Math.min(dMinX, pt[0]);
/* 266 */     dMinY = Math.min(dMinY, pt[1]);
/* 267 */     dMaxX = Math.max(dMaxX, pt[0]);
/* 268 */     dMaxY = Math.max(dMaxY, pt[1]);
/* 270 */     pt[0] = rect.x;
/* 271 */     pt[1] = rect.y + rect.height - 1;
/* 272 */     mapPoint(pt, sMinX, sMinY, sMaxX, sMaxY, type, mapForwards);
/* 273 */     dMinX = Math.min(dMinX, pt[0]);
/* 274 */     dMinY = Math.min(dMinY, pt[1]);
/* 275 */     dMaxX = Math.max(dMaxX, pt[0]);
/* 276 */     dMaxY = Math.max(dMaxY, pt[1]);
/* 278 */     pt[0] = rect.x + rect.width - 1;
/* 279 */     pt[1] = rect.y + rect.height - 1;
/* 280 */     mapPoint(pt, sMinX, sMinY, sMaxX, sMaxY, type, mapForwards);
/* 281 */     dMinX = Math.min(dMinX, pt[0]);
/* 282 */     dMinY = Math.min(dMinY, pt[1]);
/* 283 */     dMaxX = Math.max(dMaxX, pt[0]);
/* 284 */     dMaxY = Math.max(dMaxY, pt[1]);
/* 286 */     return new Rectangle(dMinX, dMinY, dMaxX - dMinX + 1, dMaxY - dMinY + 1);
/*     */   }
/*     */   
/*     */   public Raster computeTile(int tileX, int tileY) {
/* 293 */     Point org = new Point(tileXToX(tileX), tileYToY(tileY));
/* 294 */     WritableRaster dest = createWritableRaster(this.sampleModel, org);
/* 297 */     int destMinX = dest.getMinX();
/* 298 */     int destMinY = dest.getMinY();
/* 299 */     int destMaxX = destMinX + dest.getWidth();
/* 300 */     int destMaxY = destMinY + dest.getHeight();
/* 303 */     Rectangle bounds = getBounds();
/* 304 */     if (destMinX < bounds.x)
/* 305 */       destMinX = bounds.x; 
/* 307 */     int boundsMaxX = bounds.x + bounds.width;
/* 308 */     if (destMaxX > boundsMaxX)
/* 309 */       destMaxX = boundsMaxX; 
/* 311 */     if (destMinY < bounds.y)
/* 312 */       destMinY = bounds.y; 
/* 314 */     int boundsMaxY = bounds.y + bounds.height;
/* 315 */     if (destMaxY > boundsMaxY)
/* 316 */       destMaxY = boundsMaxY; 
/* 319 */     if (destMinX >= destMaxX || destMinY >= destMaxY)
/* 320 */       return dest; 
/* 324 */     Rectangle destRect = new Rectangle(destMinX, destMinY, destMaxX - destMinX, destMaxY - destMinY);
/* 329 */     IntegerSequence xSplits = new IntegerSequence(destMinX, destMaxX);
/* 331 */     xSplits.insert(destMinX);
/* 332 */     xSplits.insert(destMaxX);
/* 334 */     IntegerSequence ySplits = new IntegerSequence(destMinY, destMaxY);
/* 336 */     ySplits.insert(destMinY);
/* 337 */     ySplits.insert(destMaxY);
/* 340 */     PlanarImage src = getSource(0);
/* 341 */     int sMinX = src.getMinX();
/* 342 */     int sMinY = src.getMinY();
/* 343 */     int sWidth = src.getWidth();
/* 344 */     int sHeight = src.getHeight();
/* 345 */     int sMaxX = sMinX + sWidth - 1;
/* 346 */     int sMaxY = sMinY + sHeight - 1;
/* 347 */     int sTileWidth = src.getTileWidth();
/* 348 */     int sTileHeight = src.getTileHeight();
/* 349 */     int sTileGridXOffset = src.getTileGridXOffset();
/* 350 */     int sTileGridYOffset = src.getTileGridYOffset();
/* 352 */     int xStart = 0;
/* 353 */     int xGap = 0;
/* 354 */     int yStart = 0;
/* 355 */     int yGap = 0;
/* 374 */     int[] pt = new int[2];
/* 375 */     pt[0] = sTileGridXOffset;
/* 376 */     pt[1] = sTileGridYOffset;
/* 377 */     mapPoint(pt, sMinX, sMinY, sMaxX, sMaxY, this.type, true);
/* 378 */     xStart = pt[0];
/* 379 */     yStart = pt[1];
/* 382 */     switch (this.type) {
/*     */       case 0:
/* 384 */         yStart++;
/* 385 */         xGap = sTileWidth;
/* 386 */         yGap = sTileHeight;
/*     */         break;
/*     */       case 1:
/* 390 */         xStart++;
/* 391 */         xGap = sTileWidth;
/* 392 */         yGap = sTileHeight;
/*     */         break;
/*     */       case 2:
/* 396 */         xGap = sTileHeight;
/* 397 */         yGap = sTileWidth;
/*     */         break;
/*     */       case 3:
/* 401 */         xStart++;
/* 402 */         yStart++;
/* 403 */         xGap = sTileHeight;
/* 404 */         yGap = sTileWidth;
/*     */         break;
/*     */       case 4:
/* 408 */         xStart++;
/* 409 */         xGap = sTileHeight;
/* 410 */         yGap = sTileWidth;
/*     */         break;
/*     */       case 5:
/* 414 */         xStart++;
/* 415 */         yStart++;
/* 416 */         xGap = sTileWidth;
/* 417 */         yGap = sTileHeight;
/*     */         break;
/*     */       case 6:
/* 421 */         yStart++;
/* 422 */         xGap = sTileHeight;
/* 423 */         yGap = sTileWidth;
/*     */         break;
/*     */     } 
/* 429 */     int kx = (int)Math.floor((destMinX - xStart) / xGap);
/* 430 */     int xSplit = xStart + kx * xGap;
/* 431 */     while (xSplit < destMaxX) {
/* 432 */       xSplits.insert(xSplit);
/* 433 */       xSplit += xGap;
/*     */     } 
/* 436 */     int ky = (int)Math.floor((destMinY - yStart) / yGap);
/* 437 */     int ySplit = yStart + ky * yGap;
/* 438 */     while (ySplit < destMaxY) {
/* 439 */       ySplits.insert(ySplit);
/* 440 */       ySplit += yGap;
/*     */     } 
/* 444 */     Raster[] sources = new Raster[1];
/* 451 */     Rectangle subRect = new Rectangle();
/* 453 */     ySplits.startEnumeration();
/*     */     int y1;
/* 454 */     for (y1 = ySplits.nextElement(); ySplits.hasMoreElements(); y1 = y2) {
/* 455 */       int y2 = ySplits.nextElement();
/* 456 */       int h = y2 - y1;
/* 458 */       xSplits.startEnumeration();
/* 459 */       int x1 = xSplits.nextElement();
/* 460 */       for (; xSplits.hasMoreElements(); x1 = x2) {
/* 461 */         int x2 = xSplits.nextElement();
/* 462 */         int w = x2 - x1;
/* 467 */         pt[0] = x1;
/* 468 */         pt[1] = y1;
/* 469 */         mapPoint(pt, sMinX, sMinY, sMaxX, sMaxY, this.type, false);
/* 472 */         int tx = src.XToTileX(pt[0]);
/* 473 */         int ty = src.YToTileY(pt[1]);
/* 474 */         sources[0] = src.getTile(tx, ty);
/* 476 */         subRect.x = x1;
/* 477 */         subRect.y = y1;
/* 478 */         subRect.width = w;
/* 479 */         subRect.height = h;
/* 480 */         computeRect(sources, dest, subRect);
/*     */       } 
/*     */     } 
/* 484 */     return dest;
/*     */   }
/*     */   
/*     */   protected void computeRect(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/* 491 */     RasterFormatTag[] formatTags = getFormatTags();
/* 492 */     Raster src = sources[0];
/* 497 */     PlanarImage source = getSource(0);
/* 498 */     int sMinX = source.getMinX();
/* 499 */     int sMinY = source.getMinY();
/* 500 */     int sWidth = source.getWidth();
/* 501 */     int sHeight = source.getHeight();
/* 502 */     int sMaxX = sMinX + sWidth - 1;
/* 503 */     int sMaxY = sMinY + sHeight - 1;
/* 505 */     int translateX = src.getSampleModelTranslateX();
/* 506 */     int translateY = src.getSampleModelTranslateY();
/* 510 */     Rectangle srcRect = src.getBounds();
/* 512 */     RasterAccessor srcAccessor = new RasterAccessor(src, srcRect, formatTags[0], getSource(0).getColorModel());
/* 516 */     RasterAccessor dstAccessor = new RasterAccessor(dest, destRect, formatTags[1], getColorModel());
/* 519 */     int incr1 = 0, incr2 = 0, s_x = 0, s_y = 0;
/* 520 */     int srcPixelStride = srcAccessor.getPixelStride();
/* 521 */     int srcScanlineStride = srcAccessor.getScanlineStride();
/* 524 */     int[] pt = new int[2];
/* 525 */     pt[0] = destRect.x;
/* 526 */     pt[1] = destRect.y;
/* 527 */     mapPoint(pt, sMinX, sMinY, sMaxX, sMaxY, this.type, false);
/* 528 */     s_x = pt[0];
/* 529 */     s_y = pt[1];
/* 532 */     switch (this.type) {
/*     */       case 0:
/* 534 */         incr1 = srcPixelStride;
/* 535 */         incr2 = -srcScanlineStride;
/*     */         break;
/*     */       case 1:
/* 539 */         incr1 = -srcPixelStride;
/* 540 */         incr2 = srcScanlineStride;
/*     */         break;
/*     */       case 2:
/* 544 */         incr1 = srcScanlineStride;
/* 545 */         incr2 = srcPixelStride;
/*     */         break;
/*     */       case 3:
/* 549 */         incr1 = -srcScanlineStride;
/* 550 */         incr2 = -srcPixelStride;
/*     */         break;
/*     */       case 4:
/* 554 */         incr1 = -srcScanlineStride;
/* 555 */         incr2 = srcPixelStride;
/*     */         break;
/*     */       case 5:
/* 559 */         incr1 = -srcPixelStride;
/* 560 */         incr2 = -srcScanlineStride;
/*     */         break;
/*     */       case 6:
/* 564 */         incr1 = srcScanlineStride;
/* 565 */         incr2 = -srcPixelStride;
/*     */         break;
/*     */     } 
/* 569 */     switch (dstAccessor.getDataType()) {
/*     */       case 0:
/* 571 */         byteLoop(srcAccessor, destRect, translateX, translateY, dstAccessor, incr1, incr2, s_x, s_y);
/*     */         break;
/*     */       case 3:
/* 580 */         intLoop(srcAccessor, destRect, translateX, translateY, dstAccessor, incr1, incr2, s_x, s_y);
/*     */         break;
/*     */       case 1:
/*     */       case 2:
/* 590 */         shortLoop(srcAccessor, destRect, translateX, translateY, dstAccessor, incr1, incr2, s_x, s_y);
/*     */         break;
/*     */       case 4:
/* 599 */         floatLoop(srcAccessor, destRect, translateX, translateY, dstAccessor, incr1, incr2, s_x, s_y);
/*     */         break;
/*     */       case 5:
/* 608 */         doubleLoop(srcAccessor, destRect, translateX, translateY, dstAccessor, incr1, incr2, s_x, s_y);
/*     */         break;
/*     */     } 
/* 622 */     if (dstAccessor.isDataCopy()) {
/* 623 */       dstAccessor.clampDataArrays();
/* 624 */       dstAccessor.copyDataToRaster();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void byteLoop(RasterAccessor src, Rectangle destRect, int srcTranslateX, int srcTranslateY, RasterAccessor dst, int incr1, int incr2, int s_x, int s_y) {
/* 634 */     int dwidth = dst.getWidth();
/* 635 */     int dheight = dst.getHeight();
/* 636 */     int dnumBands = dst.getNumBands();
/* 638 */     byte[][] dstDataArrays = dst.getByteDataArrays();
/* 639 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 640 */     int dstPixelStride = dst.getPixelStride();
/* 641 */     int dstScanlineStride = dst.getScanlineStride();
/* 643 */     byte[][] srcDataArrays = src.getByteDataArrays();
/* 644 */     int[] bandOffsets = src.getOffsetsForBands();
/* 645 */     int srcPixelStride = src.getPixelStride();
/* 646 */     int srcScanlineStride = src.getScanlineStride();
/* 648 */     int dst_num_bands = dst.getNumBands();
/* 650 */     int dst_min_x = destRect.x;
/* 651 */     int dst_min_y = destRect.y;
/* 652 */     int dst_max_x = destRect.x + destRect.width;
/* 653 */     int dst_max_y = destRect.y + destRect.height;
/* 656 */     int posy = (s_y - srcTranslateY) * srcScanlineStride;
/* 657 */     int posx = (s_x - srcTranslateX) * srcPixelStride;
/* 658 */     int srcScanlineOffset = posx + posy;
/* 659 */     int dstScanlineOffset = 0;
/* 662 */     for (int y = dst_min_y; y < dst_max_y; y++) {
/* 663 */       for (int k2 = 0; k2 < dst_num_bands; k2++) {
/* 664 */         byte[] srcDataArray = srcDataArrays[k2];
/* 665 */         byte[] dstDataArray = dstDataArrays[k2];
/* 667 */         int dstPixelOffset = dstScanlineOffset + dstBandOffsets[k2];
/* 668 */         int srcPixelOffset = srcScanlineOffset + bandOffsets[k2];
/* 670 */         for (int x = dst_min_x; x < dst_max_x; x++) {
/* 671 */           dstDataArray[dstPixelOffset] = srcDataArray[srcPixelOffset];
/* 673 */           srcPixelOffset += incr1;
/* 676 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/*     */       } 
/* 680 */       srcScanlineOffset += incr2;
/* 683 */       dstScanlineOffset += dstScanlineStride;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void intLoop(RasterAccessor src, Rectangle destRect, int srcTranslateX, int srcTranslateY, RasterAccessor dst, int incr1, int incr2, int s_x, int s_y) {
/* 693 */     int dwidth = dst.getWidth();
/* 694 */     int dheight = dst.getHeight();
/* 695 */     int dnumBands = dst.getNumBands();
/* 697 */     int[][] dstDataArrays = dst.getIntDataArrays();
/* 698 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 699 */     int dstPixelStride = dst.getPixelStride();
/* 700 */     int dstScanlineStride = dst.getScanlineStride();
/* 702 */     int[][] srcDataArrays = src.getIntDataArrays();
/* 703 */     int[] bandOffsets = src.getOffsetsForBands();
/* 704 */     int srcPixelStride = src.getPixelStride();
/* 705 */     int srcScanlineStride = src.getScanlineStride();
/* 707 */     int dst_num_bands = dst.getNumBands();
/* 709 */     int dst_min_x = destRect.x;
/* 710 */     int dst_min_y = destRect.y;
/* 711 */     int dst_max_x = destRect.x + destRect.width;
/* 712 */     int dst_max_y = destRect.y + destRect.height;
/* 715 */     int posy = (s_y - srcTranslateY) * srcScanlineStride;
/* 716 */     int posx = (s_x - srcTranslateX) * srcPixelStride;
/* 717 */     int srcScanlineOffset = posx + posy;
/* 718 */     int dstScanlineOffset = 0;
/* 721 */     for (int y = dst_min_y; y < dst_max_y; y++) {
/* 722 */       for (int k2 = 0; k2 < dst_num_bands; k2++) {
/* 723 */         int[] srcDataArray = srcDataArrays[k2];
/* 724 */         int[] dstDataArray = dstDataArrays[k2];
/* 726 */         int dstPixelOffset = dstScanlineOffset + dstBandOffsets[k2];
/* 727 */         int srcPixelOffset = srcScanlineOffset + bandOffsets[k2];
/* 729 */         for (int x = dst_min_x; x < dst_max_x; x++) {
/* 730 */           dstDataArray[dstPixelOffset] = srcDataArray[srcPixelOffset];
/* 732 */           srcPixelOffset += incr1;
/* 735 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/*     */       } 
/* 739 */       srcScanlineOffset += incr2;
/* 742 */       dstScanlineOffset += dstScanlineStride;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void shortLoop(RasterAccessor src, Rectangle destRect, int srcTranslateX, int srcTranslateY, RasterAccessor dst, int incr1, int incr2, int s_x, int s_y) {
/* 752 */     int dwidth = dst.getWidth();
/* 753 */     int dheight = dst.getHeight();
/* 754 */     int dnumBands = dst.getNumBands();
/* 756 */     short[][] dstDataArrays = dst.getShortDataArrays();
/* 757 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 758 */     int dstPixelStride = dst.getPixelStride();
/* 759 */     int dstScanlineStride = dst.getScanlineStride();
/* 761 */     short[][] srcDataArrays = src.getShortDataArrays();
/* 762 */     int[] bandOffsets = src.getOffsetsForBands();
/* 763 */     int srcPixelStride = src.getPixelStride();
/* 764 */     int srcScanlineStride = src.getScanlineStride();
/* 766 */     int dst_num_bands = dst.getNumBands();
/* 768 */     int dst_min_x = destRect.x;
/* 769 */     int dst_min_y = destRect.y;
/* 770 */     int dst_max_x = destRect.x + destRect.width;
/* 771 */     int dst_max_y = destRect.y + destRect.height;
/* 774 */     int posy = (s_y - srcTranslateY) * srcScanlineStride;
/* 775 */     int posx = (s_x - srcTranslateX) * srcPixelStride;
/* 776 */     int srcScanlineOffset = posx + posy;
/* 777 */     int dstScanlineOffset = 0;
/* 780 */     for (int y = dst_min_y; y < dst_max_y; y++) {
/* 781 */       for (int k2 = 0; k2 < dst_num_bands; k2++) {
/* 782 */         short[] srcDataArray = srcDataArrays[k2];
/* 783 */         short[] dstDataArray = dstDataArrays[k2];
/* 785 */         int dstPixelOffset = dstScanlineOffset + dstBandOffsets[k2];
/* 786 */         int srcPixelOffset = srcScanlineOffset + bandOffsets[k2];
/* 788 */         for (int x = dst_min_x; x < dst_max_x; x++) {
/* 789 */           dstDataArray[dstPixelOffset] = srcDataArray[srcPixelOffset];
/* 791 */           srcPixelOffset += incr1;
/* 794 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/*     */       } 
/* 798 */       srcScanlineOffset += incr2;
/* 801 */       dstScanlineOffset += dstScanlineStride;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void floatLoop(RasterAccessor src, Rectangle destRect, int srcTranslateX, int srcTranslateY, RasterAccessor dst, int incr1, int incr2, int s_x, int s_y) {
/* 811 */     int dwidth = dst.getWidth();
/* 812 */     int dheight = dst.getHeight();
/* 813 */     int dnumBands = dst.getNumBands();
/* 815 */     float[][] dstDataArrays = dst.getFloatDataArrays();
/* 816 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 817 */     int dstPixelStride = dst.getPixelStride();
/* 818 */     int dstScanlineStride = dst.getScanlineStride();
/* 820 */     float[][] srcDataArrays = src.getFloatDataArrays();
/* 821 */     int[] bandOffsets = src.getOffsetsForBands();
/* 822 */     int srcPixelStride = src.getPixelStride();
/* 823 */     int srcScanlineStride = src.getScanlineStride();
/* 825 */     int dst_num_bands = dst.getNumBands();
/* 827 */     int dst_min_x = destRect.x;
/* 828 */     int dst_min_y = destRect.y;
/* 829 */     int dst_max_x = destRect.x + destRect.width;
/* 830 */     int dst_max_y = destRect.y + destRect.height;
/* 833 */     int posy = (s_y - srcTranslateY) * srcScanlineStride;
/* 834 */     int posx = (s_x - srcTranslateX) * srcPixelStride;
/* 835 */     int srcScanlineOffset = posx + posy;
/* 836 */     int dstScanlineOffset = 0;
/* 839 */     for (int y = dst_min_y; y < dst_max_y; y++) {
/* 840 */       for (int k2 = 0; k2 < dst_num_bands; k2++) {
/* 841 */         float[] srcDataArray = srcDataArrays[k2];
/* 842 */         float[] dstDataArray = dstDataArrays[k2];
/* 844 */         int dstPixelOffset = dstScanlineOffset + dstBandOffsets[k2];
/* 845 */         int srcPixelOffset = srcScanlineOffset + bandOffsets[k2];
/* 847 */         for (int x = dst_min_x; x < dst_max_x; x++) {
/* 848 */           dstDataArray[dstPixelOffset] = srcDataArray[srcPixelOffset];
/* 850 */           srcPixelOffset += incr1;
/* 853 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/*     */       } 
/* 857 */       srcScanlineOffset += incr2;
/* 860 */       dstScanlineOffset += dstScanlineStride;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void doubleLoop(RasterAccessor src, Rectangle destRect, int srcTranslateX, int srcTranslateY, RasterAccessor dst, int incr1, int incr2, int s_x, int s_y) {
/* 870 */     int dwidth = dst.getWidth();
/* 871 */     int dheight = dst.getHeight();
/* 872 */     int dnumBands = dst.getNumBands();
/* 874 */     double[][] dstDataArrays = dst.getDoubleDataArrays();
/* 875 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 876 */     int dstPixelStride = dst.getPixelStride();
/* 877 */     int dstScanlineStride = dst.getScanlineStride();
/* 879 */     double[][] srcDataArrays = src.getDoubleDataArrays();
/* 880 */     int[] bandOffsets = src.getOffsetsForBands();
/* 881 */     int srcPixelStride = src.getPixelStride();
/* 882 */     int srcScanlineStride = src.getScanlineStride();
/* 884 */     int dst_num_bands = dst.getNumBands();
/* 886 */     int dst_min_x = destRect.x;
/* 887 */     int dst_min_y = destRect.y;
/* 888 */     int dst_max_x = destRect.x + destRect.width;
/* 889 */     int dst_max_y = destRect.y + destRect.height;
/* 892 */     int posy = (s_y - srcTranslateY) * srcScanlineStride;
/* 893 */     int posx = (s_x - srcTranslateX) * srcPixelStride;
/* 894 */     int srcScanlineOffset = posx + posy;
/* 895 */     int dstScanlineOffset = 0;
/* 898 */     for (int y = dst_min_y; y < dst_max_y; y++) {
/* 899 */       for (int k2 = 0; k2 < dst_num_bands; k2++) {
/* 900 */         double[] srcDataArray = srcDataArrays[k2];
/* 901 */         double[] dstDataArray = dstDataArrays[k2];
/* 903 */         int dstPixelOffset = dstScanlineOffset + dstBandOffsets[k2];
/* 904 */         int srcPixelOffset = srcScanlineOffset + bandOffsets[k2];
/* 906 */         for (int x = dst_min_x; x < dst_max_x; x++) {
/* 907 */           dstDataArray[dstPixelOffset] = srcDataArray[srcPixelOffset];
/* 909 */           srcPixelOffset += incr1;
/* 912 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/*     */       } 
/* 916 */       srcScanlineOffset += incr2;
/* 919 */       dstScanlineOffset += dstScanlineStride;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\TransposeOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */