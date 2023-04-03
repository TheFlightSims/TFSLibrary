/*      */ package com.sun.media.jai.opimage;
/*      */ 
/*      */ import java.awt.Point;
/*      */ import java.awt.Rectangle;
/*      */ import java.awt.geom.AffineTransform;
/*      */ import java.awt.geom.Point2D;
/*      */ import java.awt.image.ColorModel;
/*      */ import java.awt.image.Raster;
/*      */ import java.awt.image.RenderedImage;
/*      */ import java.awt.image.WritableRaster;
/*      */ import java.util.Map;
/*      */ import javax.media.jai.BorderExtender;
/*      */ import javax.media.jai.ImageLayout;
/*      */ import javax.media.jai.Interpolation;
/*      */ import javax.media.jai.RasterAccessor;
/*      */ import javax.media.jai.RasterFormatTag;
/*      */ import javax.media.jai.util.Range;
/*      */ 
/*      */ class AffineNearestOpImage extends AffineOpImage {
/*      */   public AffineNearestOpImage(RenderedImage source, BorderExtender extender, Map config, ImageLayout layout, AffineTransform transform, Interpolation interp, double[] backgroundValues) {
/*   54 */     super(source, extender, config, layout, transform, interp, backgroundValues);
/*   66 */     ColorModel srcColorModel = source.getColorModel();
/*   67 */     if (srcColorModel instanceof java.awt.image.IndexColorModel) {
/*   68 */       this.sampleModel = source.getSampleModel().createCompatibleSampleModel(this.tileWidth, this.tileHeight);
/*   70 */       this.colorModel = srcColorModel;
/*      */     } 
/*      */   }
/*      */   
/*      */   protected Range performScanlineClipping(float src_rect_x1, float src_rect_y1, float src_rect_x2, float src_rect_y2, int s_ix, int s_iy, int ifracx, int ifracy, int dst_min_x, int dst_max_x, int lpad, int rpad, int tpad, int bpad) {
/*   96 */     int clipMinX = dst_min_x;
/*   97 */     int clipMaxX = dst_max_x;
/*   99 */     long xdenom = (this.incx * 1048576 + this.ifracdx);
/*  100 */     if (xdenom != 0L) {
/*  101 */       long clipx1 = (long)src_rect_x1 + lpad;
/*  102 */       long clipx2 = (long)src_rect_x2 - rpad;
/*  104 */       long x1 = (clipx1 - s_ix) * 1048576L - ifracx + dst_min_x * xdenom;
/*  106 */       long x2 = (clipx2 - s_ix) * 1048576L - ifracx + dst_min_x * xdenom;
/*  110 */       if (xdenom < 0L) {
/*  111 */         long tmp = x1;
/*  112 */         x1 = x2;
/*  113 */         x2 = tmp;
/*      */       } 
/*  116 */       int dx1 = ceilRatio(x1, xdenom);
/*  117 */       clipMinX = Math.max(clipMinX, dx1);
/*  119 */       int dx2 = floorRatio(x2, xdenom) + 1;
/*  120 */       clipMaxX = Math.min(clipMaxX, dx2);
/*  123 */     } else if (s_ix < src_rect_x1 || s_ix >= src_rect_x2) {
/*  124 */       clipMinX = clipMaxX = dst_min_x;
/*  125 */       return new Range(Integer.class, new Integer(clipMinX), new Integer(clipMaxX));
/*      */     } 
/*  131 */     long ydenom = (this.incy * 1048576 + this.ifracdy);
/*  132 */     if (ydenom != 0L) {
/*  133 */       long clipy1 = (long)src_rect_y1 + tpad;
/*  134 */       long clipy2 = (long)src_rect_y2 - bpad;
/*  136 */       long y1 = (clipy1 - s_iy) * 1048576L - ifracy + dst_min_x * ydenom;
/*  138 */       long y2 = (clipy2 - s_iy) * 1048576L - ifracy + dst_min_x * ydenom;
/*  142 */       if (ydenom < 0L) {
/*  143 */         long tmp = y1;
/*  144 */         y1 = y2;
/*  145 */         y2 = tmp;
/*      */       } 
/*  148 */       int dx1 = ceilRatio(y1, ydenom);
/*  149 */       clipMinX = Math.max(clipMinX, dx1);
/*  151 */       int dx2 = floorRatio(y2, ydenom) + 1;
/*  152 */       clipMaxX = Math.min(clipMaxX, dx2);
/*  155 */     } else if (s_iy < src_rect_y1 || s_iy >= src_rect_y2) {
/*  156 */       clipMinX = clipMaxX = dst_min_x;
/*      */     } 
/*  160 */     if (clipMinX > dst_max_x)
/*  161 */       clipMinX = dst_max_x; 
/*  162 */     if (clipMaxX < dst_min_x)
/*  163 */       clipMaxX = dst_min_x; 
/*  165 */     return new Range(Integer.class, new Integer(clipMinX), new Integer(clipMaxX));
/*      */   }
/*      */   
/*      */   protected Point[] advanceToStartOfScanline(int dst_min_x, int clipMinX, int s_ix, int s_iy, int ifracx, int ifracy) {
/*  182 */     long skip = (clipMinX - dst_min_x);
/*  183 */     long dx = (ifracx + skip * this.ifracdx) / 1048576L;
/*  185 */     long dy = (ifracy + skip * this.ifracdy) / 1048576L;
/*  187 */     s_ix = (int)(s_ix + skip * this.incx + (int)dx);
/*  188 */     s_iy = (int)(s_iy + skip * this.incy + (int)dy);
/*  190 */     long lfracx = ifracx + skip * this.ifracdx;
/*  191 */     if (lfracx >= 0L) {
/*  192 */       ifracx = (int)(lfracx % 1048576L);
/*      */     } else {
/*  194 */       ifracx = (int)-(-lfracx % 1048576L);
/*      */     } 
/*  197 */     long lfracy = ifracy + skip * this.ifracdy;
/*  198 */     if (lfracy >= 0L) {
/*  199 */       ifracy = (int)(lfracy % 1048576L);
/*      */     } else {
/*  201 */       ifracy = (int)-(-lfracy % 1048576L);
/*      */     } 
/*  204 */     return new Point[] { new Point(s_ix, s_iy), new Point(ifracx, ifracy) };
/*      */   }
/*      */   
/*      */   protected void computeRect(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/*      */     int dstNumBands;
/*  222 */     RasterFormatTag[] formatTags = getFormatTags();
/*  224 */     Raster source = sources[0];
/*  226 */     Rectangle srcRect = source.getBounds();
/*  228 */     int srcRectX = srcRect.x;
/*  229 */     int srcRectY = srcRect.y;
/*  234 */     RasterAccessor srcAccessor = new RasterAccessor(source, srcRect, formatTags[0], getSourceImage(0).getColorModel());
/*  240 */     RasterAccessor dstAccessor = new RasterAccessor(dest, destRect, formatTags[1], getColorModel());
/*  246 */     switch (dstAccessor.getDataType()) {
/*      */       case 0:
/*  248 */         dstNumBands = dstAccessor.getNumBands();
/*  249 */         if (dstNumBands == 1) {
/*  250 */           byteLoop_1band(srcAccessor, destRect, srcRectX, srcRectY, dstAccessor);
/*      */           break;
/*      */         } 
/*  255 */         if (dstNumBands == 3) {
/*  256 */           byteLoop_3band(srcAccessor, destRect, srcRectX, srcRectY, dstAccessor);
/*      */           break;
/*      */         } 
/*  262 */         byteLoop(srcAccessor, destRect, srcRectX, srcRectY, dstAccessor);
/*      */         break;
/*      */       case 3:
/*  271 */         intLoop(srcAccessor, destRect, srcRectX, srcRectY, dstAccessor);
/*      */         break;
/*      */       case 1:
/*      */       case 2:
/*  280 */         shortLoop(srcAccessor, destRect, srcRectX, srcRectY, dstAccessor);
/*      */         break;
/*      */       case 4:
/*  288 */         floatLoop(srcAccessor, destRect, srcRectX, srcRectY, dstAccessor);
/*      */         break;
/*      */       case 5:
/*  296 */         doubleLoop(srcAccessor, destRect, srcRectX, srcRectY, dstAccessor);
/*      */         break;
/*      */     } 
/*  309 */     if (dstAccessor.isDataCopy()) {
/*  310 */       dstAccessor.clampDataArrays();
/*  311 */       dstAccessor.copyDataToRaster();
/*      */     } 
/*      */   }
/*      */   
/*      */   private void byteLoop(RasterAccessor src, Rectangle destRect, int srcRectX, int srcRectY, RasterAccessor dst) {
/*  321 */     float src_rect_x1 = src.getX();
/*  322 */     float src_rect_y1 = src.getY();
/*  323 */     float src_rect_x2 = src_rect_x1 + src.getWidth();
/*  324 */     float src_rect_y2 = src_rect_y1 + src.getHeight();
/*  333 */     int dstOffset = 0;
/*  335 */     Point2D dst_pt = new Point2D.Float();
/*  336 */     Point2D src_pt = new Point2D.Float();
/*  338 */     int dwidth = dst.getWidth();
/*  339 */     int dheight = dst.getHeight();
/*  341 */     byte[][] dstDataArrays = dst.getByteDataArrays();
/*  342 */     int[] dstBandOffsets = dst.getBandOffsets();
/*  343 */     int dstPixelStride = dst.getPixelStride();
/*  344 */     int dstScanlineStride = dst.getScanlineStride();
/*  346 */     byte[][] srcDataArrays = src.getByteDataArrays();
/*  347 */     int[] bandOffsets = src.getBandOffsets();
/*  348 */     int srcPixelStride = src.getPixelStride();
/*  349 */     int srcScanlineStride = src.getScanlineStride();
/*  351 */     int dst_num_bands = dst.getNumBands();
/*  353 */     int dst_min_x = destRect.x;
/*  354 */     int dst_min_y = destRect.y;
/*  355 */     int dst_max_x = destRect.x + destRect.width;
/*  356 */     int dst_max_y = destRect.y + destRect.height;
/*  358 */     int incxStride = this.incx * srcPixelStride;
/*  359 */     int incx1Stride = this.incx1 * srcPixelStride;
/*  360 */     int incyStride = this.incy * srcScanlineStride;
/*  361 */     int incy1Stride = this.incy1 * srcScanlineStride;
/*  363 */     byte[] backgroundByte = new byte[dst_num_bands];
/*  364 */     for (int i = 0; i < dst_num_bands; i++)
/*  365 */       backgroundByte[i] = (byte)(int)this.backgroundValues[i]; 
/*  367 */     for (int y = dst_min_y; y < dst_max_y; y++) {
/*  368 */       int dstPixelOffset = dstOffset;
/*  372 */       dst_pt.setLocation(dst_min_x + 0.5D, y + 0.5D);
/*  375 */       mapDestPoint(dst_pt, src_pt);
/*  378 */       float s_x = (float)src_pt.getX();
/*  379 */       float s_y = (float)src_pt.getY();
/*  382 */       int s_ix = (int)Math.floor(s_x);
/*  383 */       int s_iy = (int)Math.floor(s_y);
/*  385 */       double fracx = s_x - s_ix;
/*  386 */       double fracy = s_y - s_iy;
/*  388 */       int ifracx = (int)Math.floor(fracx * 1048576.0D);
/*  389 */       int ifracy = (int)Math.floor(fracy * 1048576.0D);
/*  392 */       Range clipRange = performScanlineClipping(src_rect_x1, src_rect_y1, src_rect_x2 - 1.0F, src_rect_y2 - 1.0F, s_ix, s_iy, ifracx, ifracy, dst_min_x, dst_max_x, 0, 0, 0, 0);
/*  402 */       int clipMinX = ((Integer)clipRange.getMinValue()).intValue();
/*  403 */       int clipMaxX = ((Integer)clipRange.getMaxValue()).intValue();
/*  406 */       Point[] startPts = advanceToStartOfScanline(dst_min_x, clipMinX, s_ix, s_iy, ifracx, ifracy);
/*  409 */       s_ix = (startPts[0]).x;
/*  410 */       s_iy = (startPts[0]).y;
/*  411 */       ifracx = (startPts[1]).x;
/*  412 */       ifracy = (startPts[1]).y;
/*  415 */       int src_pos = (s_iy - srcRectY) * srcScanlineStride + (s_ix - srcRectX) * srcPixelStride;
/*  418 */       if (this.setBackground) {
/*  419 */         for (int j = dst_min_x; j < clipMinX; j++) {
/*  420 */           for (int k2 = 0; k2 < dst_num_bands; k2++)
/*  421 */             dstDataArrays[k2][dstPixelOffset + dstBandOffsets[k2]] = backgroundByte[k2]; 
/*  424 */           dstPixelOffset += dstPixelStride;
/*      */         } 
/*      */       } else {
/*  427 */         dstPixelOffset += (clipMinX - dst_min_x) * dstPixelStride;
/*      */       } 
/*      */       int x;
/*  429 */       for (x = clipMinX; x < clipMaxX; x++) {
/*  430 */         for (int k2 = 0; k2 < dst_num_bands; k2++)
/*  431 */           dstDataArrays[k2][dstPixelOffset + dstBandOffsets[k2]] = srcDataArrays[k2][src_pos + bandOffsets[k2]]; 
/*  437 */         if (ifracx < this.ifracdx1) {
/*  442 */           src_pos += incxStride;
/*  443 */           ifracx += this.ifracdx;
/*      */         } else {
/*  449 */           src_pos += incx1Stride;
/*  450 */           ifracx -= this.ifracdx1;
/*      */         } 
/*  453 */         if (ifracy < this.ifracdy1) {
/*  458 */           src_pos += incyStride;
/*  459 */           ifracy += this.ifracdy;
/*      */         } else {
/*  465 */           src_pos += incy1Stride;
/*  466 */           ifracy -= this.ifracdy1;
/*      */         } 
/*  470 */         dstPixelOffset += dstPixelStride;
/*      */       } 
/*  473 */       if (this.setBackground && clipMinX <= clipMaxX)
/*  474 */         for (x = clipMaxX; x < dst_max_x; x++) {
/*  475 */           for (int k2 = 0; k2 < dst_num_bands; k2++)
/*  476 */             dstDataArrays[k2][dstPixelOffset + dstBandOffsets[k2]] = backgroundByte[k2]; 
/*  479 */           dstPixelOffset += dstPixelStride;
/*      */         }  
/*  484 */       dstOffset += dstScanlineStride;
/*      */     } 
/*      */   }
/*      */   
/*      */   private void byteLoop_1band(RasterAccessor src, Rectangle destRect, int srcRectX, int srcRectY, RasterAccessor dst) {
/*  495 */     float src_rect_x1 = src.getX();
/*  496 */     float src_rect_y1 = src.getY();
/*  497 */     float src_rect_x2 = src_rect_x1 + src.getWidth();
/*  498 */     float src_rect_y2 = src_rect_y1 + src.getHeight();
/*  507 */     int dstOffset = 0;
/*  509 */     Point2D dst_pt = new Point2D.Float();
/*  510 */     Point2D src_pt = new Point2D.Float();
/*  512 */     int dwidth = dst.getWidth();
/*  513 */     int dheight = dst.getHeight();
/*  515 */     byte[][] dstDataArrays = dst.getByteDataArrays();
/*  516 */     int[] dstBandOffsets = dst.getBandOffsets();
/*  517 */     int dstPixelStride = dst.getPixelStride();
/*  518 */     int dstScanlineStride = dst.getScanlineStride();
/*  520 */     byte[] dstDataArray0 = dstDataArrays[0];
/*  521 */     int dstBandOffset0 = dstBandOffsets[0];
/*  523 */     byte[][] srcDataArrays = src.getByteDataArrays();
/*  524 */     int[] bandOffsets = src.getBandOffsets();
/*  525 */     int srcPixelStride = src.getPixelStride();
/*  526 */     int srcScanlineStride = src.getScanlineStride();
/*  528 */     byte[] srcDataArray0 = srcDataArrays[0];
/*  529 */     int bandOffsets0 = bandOffsets[0];
/*  531 */     int dst_min_x = destRect.x;
/*  532 */     int dst_min_y = destRect.y;
/*  533 */     int dst_max_x = destRect.x + destRect.width;
/*  534 */     int dst_max_y = destRect.y + destRect.height;
/*  536 */     int incxStride = this.incx * srcPixelStride;
/*  537 */     int incx1Stride = this.incx1 * srcPixelStride;
/*  538 */     int incyStride = this.incy * srcScanlineStride;
/*  539 */     int incy1Stride = this.incy1 * srcScanlineStride;
/*  541 */     byte backgroundByte = (byte)(int)this.backgroundValues[0];
/*  543 */     for (int y = dst_min_y; y < dst_max_y; y++) {
/*  544 */       int dstPixelOffset = dstOffset;
/*  548 */       dst_pt.setLocation(dst_min_x + 0.5D, y + 0.5D);
/*  551 */       mapDestPoint(dst_pt, src_pt);
/*  554 */       float s_x = (float)src_pt.getX();
/*  555 */       float s_y = (float)src_pt.getY();
/*  558 */       int s_ix = (int)Math.floor(s_x);
/*  559 */       int s_iy = (int)Math.floor(s_y);
/*  561 */       double fracx = s_x - s_ix;
/*  562 */       double fracy = s_y - s_iy;
/*  564 */       int ifracx = (int)Math.floor(fracx * 1048576.0D);
/*  565 */       int ifracy = (int)Math.floor(fracy * 1048576.0D);
/*  568 */       Range clipRange = performScanlineClipping(src_rect_x1, src_rect_y1, src_rect_x2 - 1.0F, src_rect_y2 - 1.0F, s_ix, s_iy, ifracx, ifracy, dst_min_x, dst_max_x, 0, 0, 0, 0);
/*  578 */       int clipMinX = ((Integer)clipRange.getMinValue()).intValue();
/*  579 */       int clipMaxX = ((Integer)clipRange.getMaxValue()).intValue();
/*  582 */       Point[] startPts = advanceToStartOfScanline(dst_min_x, clipMinX, s_ix, s_iy, ifracx, ifracy);
/*  585 */       s_ix = (startPts[0]).x;
/*  586 */       s_iy = (startPts[0]).y;
/*  587 */       ifracx = (startPts[1]).x;
/*  588 */       ifracy = (startPts[1]).y;
/*  591 */       int src_pos = (s_iy - srcRectY) * srcScanlineStride + (s_ix - srcRectX) * srcPixelStride;
/*  594 */       if (this.setBackground) {
/*  595 */         for (int i = dst_min_x; i < clipMinX; i++) {
/*  596 */           dstDataArray0[dstPixelOffset + dstBandOffset0] = backgroundByte;
/*  598 */           dstPixelOffset += dstPixelStride;
/*      */         } 
/*      */       } else {
/*  601 */         dstPixelOffset += (clipMinX - dst_min_x) * dstPixelStride;
/*      */       } 
/*      */       int x;
/*  603 */       for (x = clipMinX; x < clipMaxX; x++) {
/*  604 */         dstDataArray0[dstPixelOffset + dstBandOffset0] = srcDataArray0[src_pos + bandOffsets0];
/*  608 */         if (ifracx < this.ifracdx1) {
/*  613 */           src_pos += incxStride;
/*  614 */           ifracx += this.ifracdx;
/*      */         } else {
/*  620 */           src_pos += incx1Stride;
/*  621 */           ifracx -= this.ifracdx1;
/*      */         } 
/*  624 */         if (ifracy < this.ifracdy1) {
/*  629 */           src_pos += incyStride;
/*  630 */           ifracy += this.ifracdy;
/*      */         } else {
/*  636 */           src_pos += incy1Stride;
/*  637 */           ifracy -= this.ifracdy1;
/*      */         } 
/*  641 */         dstPixelOffset += dstPixelStride;
/*      */       } 
/*  644 */       if (this.setBackground && clipMinX <= clipMaxX)
/*  645 */         for (x = clipMaxX; x < dst_max_x; x++) {
/*  646 */           dstDataArray0[dstPixelOffset + dstBandOffset0] = backgroundByte;
/*  648 */           dstPixelOffset += dstPixelStride;
/*      */         }  
/*  653 */       dstOffset += dstScanlineStride;
/*      */     } 
/*      */   }
/*      */   
/*      */   private void byteLoop_3band(RasterAccessor src, Rectangle destRect, int srcRectX, int srcRectY, RasterAccessor dst) {
/*  663 */     float src_rect_x1 = src.getX();
/*  664 */     float src_rect_y1 = src.getY();
/*  665 */     float src_rect_x2 = src_rect_x1 + src.getWidth();
/*  666 */     float src_rect_y2 = src_rect_y1 + src.getHeight();
/*  675 */     int dstOffset = 0;
/*  677 */     Point2D dst_pt = new Point2D.Float();
/*  678 */     Point2D src_pt = new Point2D.Float();
/*  680 */     int dwidth = dst.getWidth();
/*  681 */     int dheight = dst.getHeight();
/*  683 */     byte[][] dstDataArrays = dst.getByteDataArrays();
/*  684 */     int[] dstBandOffsets = dst.getBandOffsets();
/*  685 */     int dstPixelStride = dst.getPixelStride();
/*  686 */     int dstScanlineStride = dst.getScanlineStride();
/*  688 */     byte[] dstDataArray0 = dstDataArrays[0];
/*  689 */     byte[] dstDataArray1 = dstDataArrays[1];
/*  690 */     byte[] dstDataArray2 = dstDataArrays[2];
/*  692 */     int dstBandOffset0 = dstBandOffsets[0];
/*  693 */     int dstBandOffset1 = dstBandOffsets[1];
/*  694 */     int dstBandOffset2 = dstBandOffsets[2];
/*  696 */     byte[][] srcDataArrays = src.getByteDataArrays();
/*  697 */     int[] bandOffsets = src.getBandOffsets();
/*  698 */     int srcPixelStride = src.getPixelStride();
/*  699 */     int srcScanlineStride = src.getScanlineStride();
/*  701 */     byte[] srcDataArray0 = srcDataArrays[0];
/*  702 */     byte[] srcDataArray1 = srcDataArrays[1];
/*  703 */     byte[] srcDataArray2 = srcDataArrays[2];
/*  705 */     int bandOffsets0 = bandOffsets[0];
/*  706 */     int bandOffsets1 = bandOffsets[1];
/*  707 */     int bandOffsets2 = bandOffsets[2];
/*  709 */     int dst_min_x = destRect.x;
/*  710 */     int dst_min_y = destRect.y;
/*  711 */     int dst_max_x = destRect.x + destRect.width;
/*  712 */     int dst_max_y = destRect.y + destRect.height;
/*  714 */     int incxStride = this.incx * srcPixelStride;
/*  715 */     int incx1Stride = this.incx1 * srcPixelStride;
/*  716 */     int incyStride = this.incy * srcScanlineStride;
/*  717 */     int incy1Stride = this.incy1 * srcScanlineStride;
/*  719 */     byte background0 = (byte)(int)this.backgroundValues[0];
/*  720 */     byte background1 = (byte)(int)this.backgroundValues[1];
/*  721 */     byte background2 = (byte)(int)this.backgroundValues[2];
/*  723 */     for (int y = dst_min_y; y < dst_max_y; y++) {
/*  724 */       int dstPixelOffset = dstOffset;
/*  728 */       dst_pt.setLocation(dst_min_x + 0.5D, y + 0.5D);
/*  731 */       mapDestPoint(dst_pt, src_pt);
/*  734 */       float s_x = (float)src_pt.getX();
/*  735 */       float s_y = (float)src_pt.getY();
/*  738 */       int s_ix = (int)Math.floor(s_x);
/*  739 */       int s_iy = (int)Math.floor(s_y);
/*  741 */       double fracx = s_x - s_ix;
/*  742 */       double fracy = s_y - s_iy;
/*  744 */       int ifracx = (int)Math.floor(fracx * 1048576.0D);
/*  745 */       int ifracy = (int)Math.floor(fracy * 1048576.0D);
/*  748 */       Range clipRange = performScanlineClipping(src_rect_x1, src_rect_y1, src_rect_x2 - 1.0F, src_rect_y2 - 1.0F, s_ix, s_iy, ifracx, ifracy, dst_min_x, dst_max_x, 0, 0, 0, 0);
/*  758 */       int clipMinX = ((Integer)clipRange.getMinValue()).intValue();
/*  759 */       int clipMaxX = ((Integer)clipRange.getMaxValue()).intValue();
/*  762 */       Point[] startPts = advanceToStartOfScanline(dst_min_x, clipMinX, s_ix, s_iy, ifracx, ifracy);
/*  765 */       s_ix = (startPts[0]).x;
/*  766 */       s_iy = (startPts[0]).y;
/*  767 */       ifracx = (startPts[1]).x;
/*  768 */       ifracy = (startPts[1]).y;
/*  771 */       int src_pos = (s_iy - srcRectY) * srcScanlineStride + (s_ix - srcRectX) * srcPixelStride;
/*  774 */       if (this.setBackground) {
/*  775 */         for (int i = dst_min_x; i < clipMinX; i++) {
/*  776 */           dstDataArray0[dstPixelOffset + dstBandOffset0] = background0;
/*  778 */           dstDataArray1[dstPixelOffset + dstBandOffset1] = background1;
/*  780 */           dstDataArray2[dstPixelOffset + dstBandOffset2] = background2;
/*  783 */           dstPixelOffset += dstPixelStride;
/*      */         } 
/*      */       } else {
/*  786 */         dstPixelOffset += (clipMinX - dst_min_x) * dstPixelStride;
/*      */       } 
/*      */       int x;
/*  788 */       for (x = clipMinX; x < clipMaxX; x++) {
/*  789 */         dstDataArray0[dstPixelOffset + dstBandOffset0] = srcDataArray0[src_pos + bandOffsets0];
/*  791 */         dstDataArray1[dstPixelOffset + dstBandOffset1] = srcDataArray1[src_pos + bandOffsets1];
/*  793 */         dstDataArray2[dstPixelOffset + dstBandOffset2] = srcDataArray2[src_pos + bandOffsets2];
/*  797 */         if (ifracx < this.ifracdx1) {
/*  802 */           src_pos += incxStride;
/*  803 */           ifracx += this.ifracdx;
/*      */         } else {
/*  809 */           src_pos += incx1Stride;
/*  810 */           ifracx -= this.ifracdx1;
/*      */         } 
/*  813 */         if (ifracy < this.ifracdy1) {
/*  818 */           src_pos += incyStride;
/*  819 */           ifracy += this.ifracdy;
/*      */         } else {
/*  825 */           src_pos += incy1Stride;
/*  826 */           ifracy -= this.ifracdy1;
/*      */         } 
/*  830 */         dstPixelOffset += dstPixelStride;
/*      */       } 
/*  833 */       if (this.setBackground && clipMinX <= clipMaxX)
/*  834 */         for (x = clipMaxX; x < dst_max_x; x++) {
/*  835 */           dstDataArray0[dstPixelOffset + dstBandOffset0] = background0;
/*  837 */           dstDataArray1[dstPixelOffset + dstBandOffset1] = background1;
/*  839 */           dstDataArray2[dstPixelOffset + dstBandOffset2] = background2;
/*  841 */           dstPixelOffset += dstPixelStride;
/*      */         }  
/*  846 */       dstOffset += dstScanlineStride;
/*      */     } 
/*      */   }
/*      */   
/*      */   private void intLoop(RasterAccessor src, Rectangle destRect, int srcRectX, int srcRectY, RasterAccessor dst) {
/*  856 */     float src_rect_x1 = src.getX();
/*  857 */     float src_rect_y1 = src.getY();
/*  858 */     float src_rect_x2 = src_rect_x1 + src.getWidth();
/*  859 */     float src_rect_y2 = src_rect_y1 + src.getHeight();
/*  868 */     int dstOffset = 0;
/*  870 */     Point2D dst_pt = new Point2D.Float();
/*  871 */     Point2D src_pt = new Point2D.Float();
/*  873 */     int dwidth = dst.getWidth();
/*  874 */     int dheight = dst.getHeight();
/*  876 */     int[][] dstDataArrays = dst.getIntDataArrays();
/*  877 */     int[] dstBandOffsets = dst.getBandOffsets();
/*  878 */     int dstPixelStride = dst.getPixelStride();
/*  879 */     int dstScanlineStride = dst.getScanlineStride();
/*  881 */     int[][] srcDataArrays = src.getIntDataArrays();
/*  882 */     int[] bandOffsets = src.getBandOffsets();
/*  883 */     int srcPixelStride = src.getPixelStride();
/*  884 */     int srcScanlineStride = src.getScanlineStride();
/*  886 */     int dst_num_bands = dst.getNumBands();
/*  888 */     int dst_min_x = destRect.x;
/*  889 */     int dst_min_y = destRect.y;
/*  890 */     int dst_max_x = destRect.x + destRect.width;
/*  891 */     int dst_max_y = destRect.y + destRect.height;
/*  893 */     int incxStride = this.incx * srcPixelStride;
/*  894 */     int incx1Stride = this.incx1 * srcPixelStride;
/*  895 */     int incyStride = this.incy * srcScanlineStride;
/*  896 */     int incy1Stride = this.incy1 * srcScanlineStride;
/*  898 */     int[] backgroundInt = new int[dst_num_bands];
/*  899 */     for (int i = 0; i < dst_num_bands; i++)
/*  900 */       backgroundInt[i] = (int)this.backgroundValues[i]; 
/*  902 */     for (int y = dst_min_y; y < dst_max_y; y++) {
/*  903 */       int dstPixelOffset = dstOffset;
/*  907 */       dst_pt.setLocation(dst_min_x + 0.5D, y + 0.5D);
/*  909 */       mapDestPoint(dst_pt, src_pt);
/*  912 */       float s_x = (float)src_pt.getX();
/*  913 */       float s_y = (float)src_pt.getY();
/*  916 */       int s_ix = (int)Math.floor(s_x);
/*  917 */       int s_iy = (int)Math.floor(s_y);
/*  919 */       double fracx = s_x - s_ix;
/*  920 */       double fracy = s_y - s_iy;
/*  922 */       int ifracx = (int)Math.floor(fracx * 1048576.0D);
/*  923 */       int ifracy = (int)Math.floor(fracy * 1048576.0D);
/*  926 */       Range clipRange = performScanlineClipping(src_rect_x1, src_rect_y1, src_rect_x2 - 1.0F, src_rect_y2 - 1.0F, s_ix, s_iy, ifracx, ifracy, dst_min_x, dst_max_x, 0, 0, 0, 0);
/*  936 */       int clipMinX = ((Integer)clipRange.getMinValue()).intValue();
/*  937 */       int clipMaxX = ((Integer)clipRange.getMaxValue()).intValue();
/*  940 */       Point[] startPts = advanceToStartOfScanline(dst_min_x, clipMinX, s_ix, s_iy, ifracx, ifracy);
/*  943 */       s_ix = (startPts[0]).x;
/*  944 */       s_iy = (startPts[0]).y;
/*  945 */       ifracx = (startPts[1]).x;
/*  946 */       ifracy = (startPts[1]).y;
/*  949 */       int src_pos = (s_iy - srcRectY) * srcScanlineStride + (s_ix - srcRectX) * srcPixelStride;
/*  952 */       if (this.setBackground) {
/*  953 */         for (int j = dst_min_x; j < clipMinX; j++) {
/*  954 */           for (int k2 = 0; k2 < dst_num_bands; k2++)
/*  955 */             dstDataArrays[k2][dstPixelOffset + dstBandOffsets[k2]] = backgroundInt[k2]; 
/*  958 */           dstPixelOffset += dstPixelStride;
/*      */         } 
/*      */       } else {
/*  961 */         dstPixelOffset += (clipMinX - dst_min_x) * dstPixelStride;
/*      */       } 
/*      */       int x;
/*  963 */       for (x = clipMinX; x < clipMaxX; x++) {
/*  964 */         for (int k2 = 0; k2 < dst_num_bands; k2++)
/*  965 */           dstDataArrays[k2][dstPixelOffset + dstBandOffsets[k2]] = srcDataArrays[k2][src_pos + bandOffsets[k2]]; 
/*  971 */         if (ifracx < this.ifracdx1) {
/*  976 */           src_pos += incxStride;
/*  977 */           ifracx += this.ifracdx;
/*      */         } else {
/*  983 */           src_pos += incx1Stride;
/*  984 */           ifracx -= this.ifracdx1;
/*      */         } 
/*  987 */         if (ifracy < this.ifracdy1) {
/*  992 */           src_pos += incyStride;
/*  993 */           ifracy += this.ifracdy;
/*      */         } else {
/*  999 */           src_pos += incy1Stride;
/* 1000 */           ifracy -= this.ifracdy1;
/*      */         } 
/* 1003 */         dstPixelOffset += dstPixelStride;
/*      */       } 
/* 1006 */       if (this.setBackground && clipMinX <= clipMaxX)
/* 1007 */         for (x = clipMaxX; x < dst_max_x; x++) {
/* 1008 */           for (int k2 = 0; k2 < dst_num_bands; k2++)
/* 1009 */             dstDataArrays[k2][dstPixelOffset + dstBandOffsets[k2]] = backgroundInt[k2]; 
/* 1012 */           dstPixelOffset += dstPixelStride;
/*      */         }  
/* 1016 */       dstOffset += dstScanlineStride;
/*      */     } 
/*      */   }
/*      */   
/*      */   private void shortLoop(RasterAccessor src, Rectangle destRect, int srcRectX, int srcRectY, RasterAccessor dst) {
/* 1026 */     float src_rect_x1 = src.getX();
/* 1027 */     float src_rect_y1 = src.getY();
/* 1028 */     float src_rect_x2 = src_rect_x1 + src.getWidth();
/* 1029 */     float src_rect_y2 = src_rect_y1 + src.getHeight();
/* 1038 */     int dstOffset = 0;
/* 1040 */     Point2D dst_pt = new Point2D.Float();
/* 1041 */     Point2D src_pt = new Point2D.Float();
/* 1043 */     int dwidth = dst.getWidth();
/* 1044 */     int dheight = dst.getHeight();
/* 1046 */     short[][] dstDataArrays = dst.getShortDataArrays();
/* 1047 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 1048 */     int dstPixelStride = dst.getPixelStride();
/* 1049 */     int dstScanlineStride = dst.getScanlineStride();
/* 1051 */     short[][] srcDataArrays = src.getShortDataArrays();
/* 1052 */     int[] bandOffsets = src.getBandOffsets();
/* 1053 */     int srcPixelStride = src.getPixelStride();
/* 1054 */     int srcScanlineStride = src.getScanlineStride();
/* 1056 */     int dst_num_bands = dst.getNumBands();
/* 1058 */     int dst_min_x = destRect.x;
/* 1059 */     int dst_min_y = destRect.y;
/* 1060 */     int dst_max_x = destRect.x + destRect.width;
/* 1061 */     int dst_max_y = destRect.y + destRect.height;
/* 1063 */     int incxStride = this.incx * srcPixelStride;
/* 1064 */     int incx1Stride = this.incx1 * srcPixelStride;
/* 1065 */     int incyStride = this.incy * srcScanlineStride;
/* 1066 */     int incy1Stride = this.incy1 * srcScanlineStride;
/* 1068 */     short[] backgroundShort = new short[dst_num_bands];
/* 1069 */     for (int i = 0; i < dst_num_bands; i++)
/* 1070 */       backgroundShort[i] = (short)(int)this.backgroundValues[i]; 
/* 1072 */     for (int y = dst_min_y; y < dst_max_y; y++) {
/* 1073 */       int dstPixelOffset = dstOffset;
/* 1077 */       dst_pt.setLocation(dst_min_x + 0.5D, y + 0.5D);
/* 1079 */       mapDestPoint(dst_pt, src_pt);
/* 1082 */       float s_x = (float)src_pt.getX();
/* 1083 */       float s_y = (float)src_pt.getY();
/* 1086 */       int s_ix = (int)Math.floor(s_x);
/* 1087 */       int s_iy = (int)Math.floor(s_y);
/* 1089 */       double fracx = s_x - s_ix;
/* 1090 */       double fracy = s_y - s_iy;
/* 1092 */       int ifracx = (int)Math.floor(fracx * 1048576.0D);
/* 1093 */       int ifracy = (int)Math.floor(fracy * 1048576.0D);
/* 1096 */       Range clipRange = performScanlineClipping(src_rect_x1, src_rect_y1, src_rect_x2 - 1.0F, src_rect_y2 - 1.0F, s_ix, s_iy, ifracx, ifracy, dst_min_x, dst_max_x, 0, 0, 0, 0);
/* 1106 */       int clipMinX = ((Integer)clipRange.getMinValue()).intValue();
/* 1107 */       int clipMaxX = ((Integer)clipRange.getMaxValue()).intValue();
/* 1110 */       Point[] startPts = advanceToStartOfScanline(dst_min_x, clipMinX, s_ix, s_iy, ifracx, ifracy);
/* 1113 */       s_ix = (startPts[0]).x;
/* 1114 */       s_iy = (startPts[0]).y;
/* 1115 */       ifracx = (startPts[1]).x;
/* 1116 */       ifracy = (startPts[1]).y;
/* 1119 */       int src_pos = (s_iy - srcRectY) * srcScanlineStride + (s_ix - srcRectX) * srcPixelStride;
/* 1122 */       if (this.setBackground) {
/* 1123 */         for (int j = dst_min_x; j < clipMinX; j++) {
/* 1124 */           for (int k2 = 0; k2 < dst_num_bands; k2++)
/* 1125 */             dstDataArrays[k2][dstPixelOffset + dstBandOffsets[k2]] = backgroundShort[k2]; 
/* 1128 */           dstPixelOffset += dstPixelStride;
/*      */         } 
/*      */       } else {
/* 1131 */         dstPixelOffset += (clipMinX - dst_min_x) * dstPixelStride;
/*      */       } 
/*      */       int x;
/* 1133 */       for (x = clipMinX; x < clipMaxX; x++) {
/* 1134 */         for (int k2 = 0; k2 < dst_num_bands; k2++)
/* 1135 */           dstDataArrays[k2][dstPixelOffset + dstBandOffsets[k2]] = srcDataArrays[k2][src_pos + bandOffsets[k2]]; 
/* 1141 */         if (ifracx < this.ifracdx1) {
/* 1146 */           src_pos += incxStride;
/* 1147 */           ifracx += this.ifracdx;
/*      */         } else {
/* 1153 */           src_pos += incx1Stride;
/* 1154 */           ifracx -= this.ifracdx1;
/*      */         } 
/* 1157 */         if (ifracy < this.ifracdy1) {
/* 1162 */           src_pos += incyStride;
/* 1163 */           ifracy += this.ifracdy;
/*      */         } else {
/* 1169 */           src_pos += incy1Stride;
/* 1170 */           ifracy -= this.ifracdy1;
/*      */         } 
/* 1173 */         dstPixelOffset += dstPixelStride;
/*      */       } 
/* 1176 */       if (this.setBackground && clipMinX <= clipMaxX)
/* 1177 */         for (x = clipMaxX; x < dst_max_x; x++) {
/* 1178 */           for (int k2 = 0; k2 < dst_num_bands; k2++)
/* 1179 */             dstDataArrays[k2][dstPixelOffset + dstBandOffsets[k2]] = backgroundShort[k2]; 
/* 1182 */           dstPixelOffset += dstPixelStride;
/*      */         }  
/* 1186 */       dstOffset += dstScanlineStride;
/*      */     } 
/*      */   }
/*      */   
/*      */   private void floatLoop(RasterAccessor src, Rectangle destRect, int srcRectX, int srcRectY, RasterAccessor dst) {
/* 1196 */     float src_rect_x1 = src.getX();
/* 1197 */     float src_rect_y1 = src.getY();
/* 1198 */     float src_rect_x2 = src_rect_x1 + src.getWidth();
/* 1199 */     float src_rect_y2 = src_rect_y1 + src.getHeight();
/* 1208 */     int dstOffset = 0;
/* 1210 */     Point2D dst_pt = new Point2D.Float();
/* 1211 */     Point2D src_pt = new Point2D.Float();
/* 1213 */     int dwidth = dst.getWidth();
/* 1214 */     int dheight = dst.getHeight();
/* 1216 */     float[][] dstDataArrays = dst.getFloatDataArrays();
/* 1217 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 1218 */     int dstPixelStride = dst.getPixelStride();
/* 1219 */     int dstScanlineStride = dst.getScanlineStride();
/* 1221 */     float[][] srcDataArrays = src.getFloatDataArrays();
/* 1222 */     int[] bandOffsets = src.getBandOffsets();
/* 1223 */     int srcPixelStride = src.getPixelStride();
/* 1224 */     int srcScanlineStride = src.getScanlineStride();
/* 1226 */     int dst_num_bands = dst.getNumBands();
/* 1228 */     int dst_min_x = destRect.x;
/* 1229 */     int dst_min_y = destRect.y;
/* 1230 */     int dst_max_x = destRect.x + destRect.width;
/* 1231 */     int dst_max_y = destRect.y + destRect.height;
/* 1233 */     int incxStride = this.incx * srcPixelStride;
/* 1234 */     int incx1Stride = this.incx1 * srcPixelStride;
/* 1235 */     int incyStride = this.incy * srcScanlineStride;
/* 1236 */     int incy1Stride = this.incy1 * srcScanlineStride;
/* 1238 */     float[] backgroundFloat = new float[dst_num_bands];
/* 1239 */     for (int i = 0; i < dst_num_bands; i++)
/* 1240 */       backgroundFloat[i] = (float)this.backgroundValues[i]; 
/* 1242 */     for (int y = dst_min_y; y < dst_max_y; y++) {
/* 1243 */       int dstPixelOffset = dstOffset;
/* 1247 */       dst_pt.setLocation(dst_min_x + 0.5D, y + 0.5D);
/* 1249 */       mapDestPoint(dst_pt, src_pt);
/* 1252 */       float s_x = (float)src_pt.getX();
/* 1253 */       float s_y = (float)src_pt.getY();
/* 1256 */       int s_ix = (int)Math.floor(s_x);
/* 1257 */       int s_iy = (int)Math.floor(s_y);
/* 1259 */       double fracx = s_x - s_ix;
/* 1260 */       double fracy = s_y - s_iy;
/* 1262 */       int ifracx = (int)Math.floor(fracx * 1048576.0D);
/* 1263 */       int ifracy = (int)Math.floor(fracy * 1048576.0D);
/* 1266 */       Range clipRange = performScanlineClipping(src_rect_x1, src_rect_y1, src_rect_x2 - 1.0F, src_rect_y2 - 1.0F, s_ix, s_iy, ifracx, ifracy, dst_min_x, dst_max_x, 0, 0, 0, 0);
/* 1276 */       int clipMinX = ((Integer)clipRange.getMinValue()).intValue();
/* 1277 */       int clipMaxX = ((Integer)clipRange.getMaxValue()).intValue();
/* 1280 */       Point[] startPts = advanceToStartOfScanline(dst_min_x, clipMinX, s_ix, s_iy, ifracx, ifracy);
/* 1283 */       s_ix = (startPts[0]).x;
/* 1284 */       s_iy = (startPts[0]).y;
/* 1285 */       ifracx = (startPts[1]).x;
/* 1286 */       ifracy = (startPts[1]).y;
/* 1289 */       int src_pos = (s_iy - srcRectY) * srcScanlineStride + (s_ix - srcRectX) * srcPixelStride;
/* 1292 */       if (this.setBackground) {
/* 1293 */         for (int j = dst_min_x; j < clipMinX; j++) {
/* 1294 */           for (int k2 = 0; k2 < dst_num_bands; k2++)
/* 1295 */             dstDataArrays[k2][dstPixelOffset + dstBandOffsets[k2]] = backgroundFloat[k2]; 
/* 1298 */           dstPixelOffset += dstPixelStride;
/*      */         } 
/*      */       } else {
/* 1301 */         dstPixelOffset += (clipMinX - dst_min_x) * dstPixelStride;
/*      */       } 
/*      */       int x;
/* 1303 */       for (x = clipMinX; x < clipMaxX; x++) {
/* 1304 */         for (int k2 = 0; k2 < dst_num_bands; k2++)
/* 1305 */           dstDataArrays[k2][dstPixelOffset + dstBandOffsets[k2]] = srcDataArrays[k2][src_pos + bandOffsets[k2]]; 
/* 1311 */         if (ifracx < this.ifracdx1) {
/* 1316 */           src_pos += incxStride;
/* 1317 */           ifracx += this.ifracdx;
/*      */         } else {
/* 1323 */           src_pos += incx1Stride;
/* 1324 */           ifracx -= this.ifracdx1;
/*      */         } 
/* 1327 */         if (ifracy < this.ifracdy1) {
/* 1332 */           src_pos += incyStride;
/* 1333 */           ifracy += this.ifracdy;
/*      */         } else {
/* 1339 */           src_pos += incy1Stride;
/* 1340 */           ifracy -= this.ifracdy1;
/*      */         } 
/* 1343 */         dstPixelOffset += dstPixelStride;
/*      */       } 
/* 1346 */       if (this.setBackground && clipMinX <= clipMaxX)
/* 1347 */         for (x = clipMaxX; x < dst_max_x; x++) {
/* 1348 */           for (int k2 = 0; k2 < dst_num_bands; k2++)
/* 1349 */             dstDataArrays[k2][dstPixelOffset + dstBandOffsets[k2]] = backgroundFloat[k2]; 
/* 1352 */           dstPixelOffset += dstPixelStride;
/*      */         }  
/* 1356 */       dstOffset += dstScanlineStride;
/*      */     } 
/*      */   }
/*      */   
/*      */   private void doubleLoop(RasterAccessor src, Rectangle destRect, int srcRectX, int srcRectY, RasterAccessor dst) {
/* 1366 */     float src_rect_x1 = src.getX();
/* 1367 */     float src_rect_y1 = src.getY();
/* 1368 */     float src_rect_x2 = src_rect_x1 + src.getWidth();
/* 1369 */     float src_rect_y2 = src_rect_y1 + src.getHeight();
/* 1378 */     int dstOffset = 0;
/* 1380 */     Point2D dst_pt = new Point2D.Float();
/* 1381 */     Point2D src_pt = new Point2D.Float();
/* 1383 */     int dwidth = dst.getWidth();
/* 1384 */     int dheight = dst.getHeight();
/* 1386 */     double[][] dstDataArrays = dst.getDoubleDataArrays();
/* 1387 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 1388 */     int dstPixelStride = dst.getPixelStride();
/* 1389 */     int dstScanlineStride = dst.getScanlineStride();
/* 1391 */     double[][] srcDataArrays = src.getDoubleDataArrays();
/* 1392 */     int[] bandOffsets = src.getBandOffsets();
/* 1393 */     int srcPixelStride = src.getPixelStride();
/* 1394 */     int srcScanlineStride = src.getScanlineStride();
/* 1396 */     int dst_num_bands = dst.getNumBands();
/* 1398 */     int dst_min_x = destRect.x;
/* 1399 */     int dst_min_y = destRect.y;
/* 1400 */     int dst_max_x = destRect.x + destRect.width;
/* 1401 */     int dst_max_y = destRect.y + destRect.height;
/* 1403 */     int incxStride = this.incx * srcPixelStride;
/* 1404 */     int incx1Stride = this.incx1 * srcPixelStride;
/* 1405 */     int incyStride = this.incy * srcScanlineStride;
/* 1406 */     int incy1Stride = this.incy1 * srcScanlineStride;
/* 1408 */     for (int y = dst_min_y; y < dst_max_y; y++) {
/* 1409 */       int dstPixelOffset = dstOffset;
/* 1413 */       dst_pt.setLocation(dst_min_x + 0.5D, y + 0.5D);
/* 1415 */       mapDestPoint(dst_pt, src_pt);
/* 1418 */       float s_x = (float)src_pt.getX();
/* 1419 */       float s_y = (float)src_pt.getY();
/* 1422 */       int s_ix = (int)Math.floor(s_x);
/* 1423 */       int s_iy = (int)Math.floor(s_y);
/* 1425 */       double fracx = s_x - s_ix;
/* 1426 */       double fracy = s_y - s_iy;
/* 1428 */       int ifracx = (int)Math.floor(fracx * 1048576.0D);
/* 1429 */       int ifracy = (int)Math.floor(fracy * 1048576.0D);
/* 1432 */       Range clipRange = performScanlineClipping(src_rect_x1, src_rect_y1, src_rect_x2 - 1.0F, src_rect_y2 - 1.0F, s_ix, s_iy, ifracx, ifracy, dst_min_x, dst_max_x, 0, 0, 0, 0);
/* 1439 */       int clipMinX = ((Integer)clipRange.getMinValue()).intValue();
/* 1440 */       int clipMaxX = ((Integer)clipRange.getMaxValue()).intValue();
/* 1443 */       Point[] startPts = advanceToStartOfScanline(dst_min_x, clipMinX, s_ix, s_iy, ifracx, ifracy);
/* 1446 */       s_ix = (startPts[0]).x;
/* 1447 */       s_iy = (startPts[0]).y;
/* 1448 */       ifracx = (startPts[1]).x;
/* 1449 */       ifracy = (startPts[1]).y;
/* 1452 */       int src_pos = (s_iy - srcRectY) * srcScanlineStride + (s_ix - srcRectX) * srcPixelStride;
/* 1455 */       if (this.setBackground) {
/* 1456 */         for (int i = dst_min_x; i < clipMinX; i++) {
/* 1457 */           for (int k2 = 0; k2 < dst_num_bands; k2++)
/* 1458 */             dstDataArrays[k2][dstPixelOffset + dstBandOffsets[k2]] = this.backgroundValues[k2]; 
/* 1461 */           dstPixelOffset += dstPixelStride;
/*      */         } 
/*      */       } else {
/* 1464 */         dstPixelOffset += (clipMinX - dst_min_x) * dstPixelStride;
/*      */       } 
/*      */       int x;
/* 1466 */       for (x = clipMinX; x < clipMaxX; x++) {
/* 1467 */         for (int k2 = 0; k2 < dst_num_bands; k2++)
/* 1468 */           dstDataArrays[k2][dstPixelOffset + dstBandOffsets[k2]] = srcDataArrays[k2][src_pos + bandOffsets[k2]]; 
/* 1474 */         if (ifracx < this.ifracdx1) {
/* 1479 */           src_pos += incxStride;
/* 1480 */           ifracx += this.ifracdx;
/*      */         } else {
/* 1486 */           src_pos += incx1Stride;
/* 1487 */           ifracx -= this.ifracdx1;
/*      */         } 
/* 1490 */         if (ifracy < this.ifracdy1) {
/* 1495 */           src_pos += incyStride;
/* 1496 */           ifracy += this.ifracdy;
/*      */         } else {
/* 1502 */           src_pos += incy1Stride;
/* 1503 */           ifracy -= this.ifracdy1;
/*      */         } 
/* 1506 */         dstPixelOffset += dstPixelStride;
/*      */       } 
/* 1509 */       if (this.setBackground && clipMinX <= clipMaxX)
/* 1510 */         for (x = clipMaxX; x < dst_max_x; x++) {
/* 1511 */           for (int k2 = 0; k2 < dst_num_bands; k2++)
/* 1512 */             dstDataArrays[k2][dstPixelOffset + dstBandOffsets[k2]] = this.backgroundValues[k2]; 
/* 1515 */           dstPixelOffset += dstPixelStride;
/*      */         }  
/* 1519 */       dstOffset += dstScanlineStride;
/*      */     } 
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\AffineNearestOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */