/*     */ package com.sun.media.jai.opimage;
/*     */ 
/*     */ import com.sun.media.jai.util.ImageUtil;
/*     */ import com.sun.media.jai.util.InterpAverage;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.util.Map;
/*     */ import javax.media.jai.GeometricOpImage;
/*     */ import javax.media.jai.ImageLayout;
/*     */ import javax.media.jai.Interpolation;
/*     */ import javax.media.jai.RasterAccessor;
/*     */ import javax.media.jai.RasterFormatTag;
/*     */ 
/*     */ public class SubsampleAverageOpImage extends GeometricOpImage {
/*     */   protected double scaleX;
/*     */   
/*     */   protected double scaleY;
/*     */   
/*     */   protected int blockX;
/*     */   
/*     */   protected int blockY;
/*     */   
/*     */   protected int sourceMinX;
/*     */   
/*     */   protected int sourceMinY;
/*     */   
/*     */   private static ImageLayout layoutHelper(RenderedImage source, double scaleX, double scaleY, ImageLayout il) {
/* 114 */     if (scaleX <= 0.0D || scaleX > 1.0D)
/* 115 */       throw new IllegalArgumentException(JaiI18N.getString("SubsampleAverageOpImage0")); 
/* 117 */     if (scaleY <= 0.0D || scaleY > 1.0D)
/* 118 */       throw new IllegalArgumentException(JaiI18N.getString("SubsampleAverageOpImage1")); 
/* 122 */     ImageLayout layout = (il == null) ? new ImageLayout() : (ImageLayout)il.clone();
/* 125 */     layout.setMinX((int)Math.floor(source.getMinX() * scaleX));
/* 126 */     layout.setMinY((int)Math.floor(source.getMinY() * scaleY));
/* 127 */     layout.setWidth((int)(source.getWidth() * scaleX));
/* 128 */     layout.setHeight((int)(source.getHeight() * scaleY));
/* 130 */     return layout;
/*     */   }
/*     */   
/*     */   public SubsampleAverageOpImage(RenderedImage source, ImageLayout layout, Map config, double scaleX, double scaleY) {
/* 138 */     super(vectorize(source), layoutHelper(source, scaleX, scaleY, layout), config, true, null, (Interpolation)new InterpAverage((int)Math.ceil(1.0D / scaleX), (int)Math.ceil(1.0D / scaleY)), null);
/* 147 */     this.scaleX = scaleX;
/* 148 */     this.scaleY = scaleY;
/* 150 */     this.blockX = (int)Math.ceil(1.0D / scaleX);
/* 151 */     this.blockY = (int)Math.ceil(1.0D / scaleY);
/* 153 */     this.sourceMinX = source.getMinX();
/* 154 */     this.sourceMinY = source.getMinY();
/*     */   }
/*     */   
/*     */   public Point2D mapDestPoint(Point2D destPt) {
/* 158 */     if (destPt == null)
/* 159 */       throw new IllegalArgumentException("destPt == null!"); 
/* 162 */     Point2D pt = (Point2D)destPt.clone();
/* 163 */     pt.setLocation(this.sourceMinX + (destPt.getX() + 0.5D - this.minX) / this.scaleX - 0.5D, this.sourceMinY + (destPt.getY() + 0.5D - this.minY) / this.scaleY - 0.5D);
/* 166 */     return pt;
/*     */   }
/*     */   
/*     */   public Point2D mapSourcePoint(Point2D sourcePt) {
/* 170 */     if (sourcePt == null)
/* 171 */       throw new IllegalArgumentException("sourcePt == null!"); 
/* 174 */     Point2D pt = (Point2D)sourcePt.clone();
/* 175 */     pt.setLocation(this.minX + (sourcePt.getX() + 0.5D - this.sourceMinX) * this.scaleX - 0.5D, this.minY + (sourcePt.getY() + 0.5D - this.sourceMinY) * this.scaleY - 0.5D);
/* 180 */     return pt;
/*     */   }
/*     */   
/*     */   protected Rectangle backwardMapRect(Rectangle destRect, int sourceIndex) {
/* 185 */     if (destRect == null)
/* 186 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 188 */     if (sourceIndex != 0)
/* 189 */       throw new IllegalArgumentException(JaiI18N.getString("Generic1")); 
/* 194 */     Point2D p1 = mapDestPoint(new Point2D.Double(destRect.x, destRect.y));
/* 198 */     Point2D p2 = mapDestPoint(new Point2D.Double((destRect.x + destRect.width - 1), (destRect.y + destRect.height - 1)));
/* 203 */     int x1 = (int)Math.floor(p1.getX());
/* 204 */     int y1 = (int)Math.floor(p1.getY());
/* 205 */     int x2 = (int)Math.floor(p2.getX());
/* 206 */     int y2 = (int)Math.floor(p2.getY());
/* 209 */     return new Rectangle(x1, y1, x2 - x1 + 1, y2 - y1 + 1);
/*     */   }
/*     */   
/*     */   protected Rectangle forwardMapRect(Rectangle sourceRect, int sourceIndex) {
/* 214 */     if (sourceRect == null)
/* 215 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 217 */     if (sourceIndex != 0)
/* 218 */       throw new IllegalArgumentException(JaiI18N.getString("Generic1")); 
/* 223 */     Point2D p1 = mapSourcePoint(new Point2D.Double(sourceRect.x, sourceRect.y));
/* 227 */     Point2D p2 = mapSourcePoint(new Point2D.Double((sourceRect.x + sourceRect.width - 1), (sourceRect.y + sourceRect.height - 1)));
/* 234 */     int x1 = (int)Math.floor(p1.getX());
/* 235 */     int y1 = (int)Math.floor(p1.getY());
/* 236 */     int x2 = (int)Math.floor(p2.getX());
/* 237 */     int y2 = (int)Math.floor(p2.getY());
/* 240 */     return new Rectangle(x1, y1, x2 - x1 + 1, y2 - y1 + 1);
/*     */   }
/*     */   
/*     */   protected void computeRect(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/* 256 */     RasterFormatTag[] formatTags = getFormatTags();
/* 259 */     RasterAccessor dst = new RasterAccessor(dest, destRect, formatTags[1], getColorModel());
/* 265 */     Rectangle srcRect = mapDestRect(destRect, 0).intersection(sources[0].getBounds());
/* 269 */     RasterAccessor src = new RasterAccessor(sources[0], srcRect, formatTags[0], getSourceImage(0).getColorModel());
/* 275 */     switch (dst.getDataType()) {
/*     */       case 0:
/* 277 */         computeRectByte(src, dst);
/*     */         break;
/*     */       case 1:
/* 280 */         computeRectUShort(src, dst);
/*     */         break;
/*     */       case 2:
/* 283 */         computeRectShort(src, dst);
/*     */         break;
/*     */       case 3:
/* 286 */         computeRectInt(src, dst);
/*     */         break;
/*     */       case 4:
/* 289 */         computeRectFloat(src, dst);
/*     */         break;
/*     */       case 5:
/* 292 */         computeRectDouble(src, dst);
/*     */         break;
/*     */       default:
/* 295 */         throw new RuntimeException(JaiI18N.getString("Generic3"));
/*     */     } 
/* 301 */     if (dst.isDataCopy()) {
/* 302 */       dst.clampDataArrays();
/* 303 */       dst.copyDataToRaster();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeRectByte(RasterAccessor src, RasterAccessor dst) {
/* 310 */     int dwidth = dst.getWidth();
/* 311 */     int dheight = dst.getHeight();
/* 312 */     int dnumBands = dst.getNumBands();
/* 315 */     byte[][] dstDataArrays = dst.getByteDataArrays();
/* 316 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 317 */     int dstPixelStride = dst.getPixelStride();
/* 318 */     int dstScanlineStride = dst.getScanlineStride();
/* 321 */     byte[][] srcDataArrays = src.getByteDataArrays();
/* 322 */     int[] srcBandOffsets = src.getBandOffsets();
/* 323 */     int srcPixelStride = src.getPixelStride();
/* 324 */     int srcScanlineStride = src.getScanlineStride();
/* 327 */     int[] srcPixelStrideScaled = new int[dwidth];
/* 328 */     for (int i = 0; i < dwidth; i++)
/* 329 */       srcPixelStrideScaled[i] = (int)Math.floor(i / this.scaleX) * srcPixelStride; 
/* 332 */     int[] srcScanlineStrideScaled = new int[dheight];
/* 333 */     for (int j = 0; j < dheight; j++)
/* 334 */       srcScanlineStrideScaled[j] = (int)Math.floor(j / this.scaleY) * srcScanlineStride; 
/* 338 */     float denom = (this.blockX * this.blockY);
/* 340 */     for (int k = 0; k < dnumBands; k++) {
/* 341 */       byte[] dstData = dstDataArrays[k];
/* 342 */       byte[] srcData = srcDataArrays[k];
/* 343 */       int srcScanlineOffset0 = srcBandOffsets[k];
/* 344 */       int dstScanlineOffset = dstBandOffsets[k];
/* 345 */       int srcScanlineOffset = srcScanlineOffset0;
/* 347 */       for (int m = 0; m < dheight; m++) {
/* 348 */         int srcPixelOffset0 = srcScanlineOffset;
/* 349 */         int dstPixelOffset = dstScanlineOffset;
/* 350 */         int srcPixelOffset = srcPixelOffset0;
/* 352 */         for (int n = 0; n < dwidth; n++) {
/* 353 */           int imageVerticalOffset = srcPixelOffset;
/* 356 */           int sum = 0;
/* 357 */           for (int u = 0; u < this.blockY; u++) {
/* 358 */             int imageOffset = imageVerticalOffset;
/* 359 */             for (int v = 0; v < this.blockX; v++) {
/* 360 */               sum += srcData[imageOffset] & 0xFF;
/* 361 */               imageOffset += srcPixelStride;
/*     */             } 
/* 363 */             imageVerticalOffset += srcScanlineStride;
/*     */           } 
/* 366 */           dstData[dstPixelOffset] = ImageUtil.clampRoundByte(sum / denom);
/* 369 */           srcPixelOffset = srcPixelOffset0 + srcPixelStrideScaled[n];
/* 370 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/* 372 */         srcScanlineOffset = srcScanlineOffset0 + srcScanlineStrideScaled[m];
/* 373 */         dstScanlineOffset += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeRectUShort(RasterAccessor src, RasterAccessor dst) {
/* 381 */     int dwidth = dst.getWidth();
/* 382 */     int dheight = dst.getHeight();
/* 383 */     int dnumBands = dst.getNumBands();
/* 386 */     short[][] dstDataArrays = dst.getShortDataArrays();
/* 387 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 388 */     int dstPixelStride = dst.getPixelStride();
/* 389 */     int dstScanlineStride = dst.getScanlineStride();
/* 392 */     short[][] srcDataArrays = src.getShortDataArrays();
/* 393 */     int[] srcBandOffsets = src.getBandOffsets();
/* 394 */     int srcPixelStride = src.getPixelStride();
/* 395 */     int srcScanlineStride = src.getScanlineStride();
/* 398 */     int[] srcPixelStrideScaled = new int[dwidth];
/* 399 */     for (int i = 0; i < dwidth; i++)
/* 400 */       srcPixelStrideScaled[i] = (int)Math.floor(i / this.scaleX) * srcPixelStride; 
/* 403 */     int[] srcScanlineStrideScaled = new int[dheight];
/* 404 */     for (int j = 0; j < dheight; j++)
/* 405 */       srcScanlineStrideScaled[j] = (int)Math.floor(j / this.scaleY) * srcScanlineStride; 
/* 409 */     float denom = (this.blockX * this.blockY);
/* 411 */     for (int k = 0; k < dnumBands; k++) {
/* 412 */       short[] dstData = dstDataArrays[k];
/* 413 */       short[] srcData = srcDataArrays[k];
/* 414 */       int srcScanlineOffset0 = srcBandOffsets[k];
/* 415 */       int dstScanlineOffset = dstBandOffsets[k];
/* 416 */       int srcScanlineOffset = srcScanlineOffset0;
/* 418 */       for (int m = 0; m < dheight; m++) {
/* 419 */         int srcPixelOffset0 = srcScanlineOffset;
/* 420 */         int dstPixelOffset = dstScanlineOffset;
/* 421 */         int srcPixelOffset = srcPixelOffset0;
/* 423 */         for (int n = 0; n < dwidth; n++) {
/* 424 */           int imageVerticalOffset = srcPixelOffset;
/* 427 */           long sum = 0L;
/* 428 */           for (int u = 0; u < this.blockY; u++) {
/* 429 */             int imageOffset = imageVerticalOffset;
/* 430 */             for (int v = 0; v < this.blockX; v++) {
/* 431 */               sum += (srcData[imageOffset] & 0xFFFF);
/* 432 */               imageOffset += srcPixelStride;
/*     */             } 
/* 434 */             imageVerticalOffset += srcScanlineStride;
/*     */           } 
/* 437 */           dstData[dstPixelOffset] = ImageUtil.clampRoundUShort((float)sum / denom);
/* 440 */           srcPixelOffset = srcPixelOffset0 + srcPixelStrideScaled[n];
/* 441 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/* 443 */         srcScanlineOffset = srcScanlineOffset0 + srcScanlineStrideScaled[m];
/* 444 */         dstScanlineOffset += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeRectShort(RasterAccessor src, RasterAccessor dst) {
/* 452 */     int dwidth = dst.getWidth();
/* 453 */     int dheight = dst.getHeight();
/* 454 */     int dnumBands = dst.getNumBands();
/* 457 */     short[][] dstDataArrays = dst.getShortDataArrays();
/* 458 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 459 */     int dstPixelStride = dst.getPixelStride();
/* 460 */     int dstScanlineStride = dst.getScanlineStride();
/* 463 */     short[][] srcDataArrays = src.getShortDataArrays();
/* 464 */     int[] srcBandOffsets = src.getBandOffsets();
/* 465 */     int srcPixelStride = src.getPixelStride();
/* 466 */     int srcScanlineStride = src.getScanlineStride();
/* 469 */     int[] srcPixelStrideScaled = new int[dwidth];
/* 470 */     for (int i = 0; i < dwidth; i++)
/* 471 */       srcPixelStrideScaled[i] = (int)Math.floor(i / this.scaleX) * srcPixelStride; 
/* 474 */     int[] srcScanlineStrideScaled = new int[dheight];
/* 475 */     for (int j = 0; j < dheight; j++)
/* 476 */       srcScanlineStrideScaled[j] = (int)Math.floor(j / this.scaleY) * srcScanlineStride; 
/* 480 */     float denom = (this.blockX * this.blockY);
/* 482 */     for (int k = 0; k < dnumBands; k++) {
/* 483 */       short[] dstData = dstDataArrays[k];
/* 484 */       short[] srcData = srcDataArrays[k];
/* 485 */       int srcScanlineOffset0 = srcBandOffsets[k];
/* 486 */       int dstScanlineOffset = dstBandOffsets[k];
/* 487 */       int srcScanlineOffset = srcScanlineOffset0;
/* 489 */       for (int m = 0; m < dheight; m++) {
/* 490 */         int srcPixelOffset0 = srcScanlineOffset;
/* 491 */         int dstPixelOffset = dstScanlineOffset;
/* 492 */         int srcPixelOffset = srcPixelOffset0;
/* 494 */         for (int n = 0; n < dwidth; n++) {
/* 495 */           int imageVerticalOffset = srcPixelOffset;
/* 498 */           long sum = 0L;
/* 499 */           for (int u = 0; u < this.blockY; u++) {
/* 500 */             int imageOffset = imageVerticalOffset;
/* 501 */             for (int v = 0; v < this.blockX; v++) {
/* 502 */               sum += srcData[imageOffset];
/* 503 */               imageOffset += srcPixelStride;
/*     */             } 
/* 505 */             imageVerticalOffset += srcScanlineStride;
/*     */           } 
/* 508 */           dstData[dstPixelOffset] = ImageUtil.clampRoundShort((float)sum / denom);
/* 511 */           srcPixelOffset = srcPixelOffset0 + srcPixelStrideScaled[n];
/* 512 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/* 514 */         srcScanlineOffset = srcScanlineOffset0 + srcScanlineStrideScaled[m];
/* 515 */         dstScanlineOffset += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeRectInt(RasterAccessor src, RasterAccessor dst) {
/* 523 */     int dwidth = dst.getWidth();
/* 524 */     int dheight = dst.getHeight();
/* 525 */     int dnumBands = dst.getNumBands();
/* 528 */     int[][] dstDataArrays = dst.getIntDataArrays();
/* 529 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 530 */     int dstPixelStride = dst.getPixelStride();
/* 531 */     int dstScanlineStride = dst.getScanlineStride();
/* 534 */     int[][] srcDataArrays = src.getIntDataArrays();
/* 535 */     int[] srcBandOffsets = src.getBandOffsets();
/* 536 */     int srcPixelStride = src.getPixelStride();
/* 537 */     int srcScanlineStride = src.getScanlineStride();
/* 540 */     int[] srcPixelStrideScaled = new int[dwidth];
/* 541 */     for (int i = 0; i < dwidth; i++)
/* 542 */       srcPixelStrideScaled[i] = (int)Math.floor(i / this.scaleX) * srcPixelStride; 
/* 545 */     int[] srcScanlineStrideScaled = new int[dheight];
/* 546 */     for (int j = 0; j < dheight; j++)
/* 547 */       srcScanlineStrideScaled[j] = (int)Math.floor(j / this.scaleY) * srcScanlineStride; 
/* 551 */     float denom = (this.blockX * this.blockY);
/* 553 */     for (int k = 0; k < dnumBands; k++) {
/* 554 */       int[] dstData = dstDataArrays[k];
/* 555 */       int[] srcData = srcDataArrays[k];
/* 556 */       int srcScanlineOffset0 = srcBandOffsets[k];
/* 557 */       int dstScanlineOffset = dstBandOffsets[k];
/* 558 */       int srcScanlineOffset = srcScanlineOffset0;
/* 560 */       for (int m = 0; m < dheight; m++) {
/* 561 */         int srcPixelOffset0 = srcScanlineOffset;
/* 562 */         int dstPixelOffset = dstScanlineOffset;
/* 563 */         int srcPixelOffset = srcPixelOffset0;
/* 565 */         for (int n = 0; n < dwidth; n++) {
/* 566 */           int imageVerticalOffset = srcPixelOffset;
/* 569 */           double sum = 0.0D;
/* 570 */           for (int u = 0; u < this.blockY; u++) {
/* 571 */             int imageOffset = imageVerticalOffset;
/* 572 */             for (int v = 0; v < this.blockX; v++) {
/* 573 */               sum += srcData[imageOffset];
/* 574 */               imageOffset += srcPixelStride;
/*     */             } 
/* 576 */             imageVerticalOffset += srcScanlineStride;
/*     */           } 
/* 579 */           dstData[dstPixelOffset] = ImageUtil.clampRoundInt(sum / denom);
/* 582 */           srcPixelOffset = srcPixelOffset0 + srcPixelStrideScaled[n];
/* 583 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/* 585 */         srcScanlineOffset = srcScanlineOffset0 + srcScanlineStrideScaled[m];
/* 586 */         dstScanlineOffset += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeRectFloat(RasterAccessor src, RasterAccessor dst) {
/* 594 */     int dwidth = dst.getWidth();
/* 595 */     int dheight = dst.getHeight();
/* 596 */     int dnumBands = dst.getNumBands();
/* 599 */     float[][] dstDataArrays = dst.getFloatDataArrays();
/* 600 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 601 */     int dstPixelStride = dst.getPixelStride();
/* 602 */     int dstScanlineStride = dst.getScanlineStride();
/* 605 */     float[][] srcDataArrays = src.getFloatDataArrays();
/* 606 */     int[] srcBandOffsets = src.getBandOffsets();
/* 607 */     int srcPixelStride = src.getPixelStride();
/* 608 */     int srcScanlineStride = src.getScanlineStride();
/* 611 */     int[] srcPixelStrideScaled = new int[dwidth];
/* 612 */     for (int i = 0; i < dwidth; i++)
/* 613 */       srcPixelStrideScaled[i] = (int)Math.floor(i / this.scaleX) * srcPixelStride; 
/* 616 */     int[] srcScanlineStrideScaled = new int[dheight];
/* 617 */     for (int j = 0; j < dheight; j++)
/* 618 */       srcScanlineStrideScaled[j] = (int)Math.floor(j / this.scaleY) * srcScanlineStride; 
/* 622 */     float denom = (this.blockX * this.blockY);
/* 624 */     for (int k = 0; k < dnumBands; k++) {
/* 625 */       float[] dstData = dstDataArrays[k];
/* 626 */       float[] srcData = srcDataArrays[k];
/* 627 */       int srcScanlineOffset0 = srcBandOffsets[k];
/* 628 */       int dstScanlineOffset = dstBandOffsets[k];
/* 629 */       int srcScanlineOffset = srcScanlineOffset0;
/* 631 */       for (int m = 0; m < dheight; m++) {
/* 632 */         int srcPixelOffset0 = srcScanlineOffset;
/* 633 */         int dstPixelOffset = dstScanlineOffset;
/* 634 */         int srcPixelOffset = srcPixelOffset0;
/* 636 */         for (int n = 0; n < dwidth; n++) {
/* 637 */           int imageVerticalOffset = srcPixelOffset;
/* 640 */           double sum = 0.0D;
/* 641 */           for (int u = 0; u < this.blockY; u++) {
/* 642 */             int imageOffset = imageVerticalOffset;
/* 643 */             for (int v = 0; v < this.blockX; v++) {
/* 644 */               sum += srcData[imageOffset];
/* 645 */               imageOffset += srcPixelStride;
/*     */             } 
/* 647 */             imageVerticalOffset += srcScanlineStride;
/*     */           } 
/* 650 */           dstData[dstPixelOffset] = ImageUtil.clampFloat(sum / denom);
/* 653 */           srcPixelOffset = srcPixelOffset0 + srcPixelStrideScaled[n];
/* 654 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/* 656 */         srcScanlineOffset = srcScanlineOffset0 + srcScanlineStrideScaled[m];
/* 657 */         dstScanlineOffset += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeRectDouble(RasterAccessor src, RasterAccessor dst) {
/* 665 */     int dwidth = dst.getWidth();
/* 666 */     int dheight = dst.getHeight();
/* 667 */     int dnumBands = dst.getNumBands();
/* 670 */     double[][] dstDataArrays = dst.getDoubleDataArrays();
/* 671 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 672 */     int dstPixelStride = dst.getPixelStride();
/* 673 */     int dstScanlineStride = dst.getScanlineStride();
/* 676 */     double[][] srcDataArrays = src.getDoubleDataArrays();
/* 677 */     int[] srcBandOffsets = src.getBandOffsets();
/* 678 */     int srcPixelStride = src.getPixelStride();
/* 679 */     int srcScanlineStride = src.getScanlineStride();
/* 682 */     int[] srcPixelStrideScaled = new int[dwidth];
/* 683 */     for (int i = 0; i < dwidth; i++)
/* 684 */       srcPixelStrideScaled[i] = (int)Math.floor(i / this.scaleX) * srcPixelStride; 
/* 687 */     int[] srcScanlineStrideScaled = new int[dheight];
/* 688 */     for (int j = 0; j < dheight; j++)
/* 689 */       srcScanlineStrideScaled[j] = (int)Math.floor(j / this.scaleY) * srcScanlineStride; 
/* 693 */     double denom = (this.blockX * this.blockY);
/* 695 */     for (int k = 0; k < dnumBands; k++) {
/* 696 */       double[] dstData = dstDataArrays[k];
/* 697 */       double[] srcData = srcDataArrays[k];
/* 698 */       int srcScanlineOffset0 = srcBandOffsets[k];
/* 699 */       int dstScanlineOffset = dstBandOffsets[k];
/* 700 */       int srcScanlineOffset = srcScanlineOffset0;
/* 702 */       for (int m = 0; m < dheight; m++) {
/* 703 */         int srcPixelOffset0 = srcScanlineOffset;
/* 704 */         int dstPixelOffset = dstScanlineOffset;
/* 705 */         int srcPixelOffset = srcPixelOffset0;
/* 707 */         for (int n = 0; n < dwidth; n++) {
/* 708 */           int imageVerticalOffset = srcPixelOffset;
/* 711 */           double sum = 0.0D;
/* 712 */           for (int u = 0; u < this.blockY; u++) {
/* 713 */             int imageOffset = imageVerticalOffset;
/* 714 */             for (int v = 0; v < this.blockX; v++) {
/* 715 */               sum += srcData[imageOffset];
/* 716 */               imageOffset += srcPixelStride;
/*     */             } 
/* 718 */             imageVerticalOffset += srcScanlineStride;
/*     */           } 
/* 721 */           dstData[dstPixelOffset] = sum / denom;
/* 723 */           srcPixelOffset = srcPixelOffset0 + srcPixelStrideScaled[n];
/* 724 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/* 726 */         srcScanlineOffset = srcScanlineOffset0 + srcScanlineStrideScaled[m];
/* 727 */         dstScanlineOffset += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\SubsampleAverageOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */