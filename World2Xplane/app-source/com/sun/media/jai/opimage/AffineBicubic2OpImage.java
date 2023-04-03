/*      */ package com.sun.media.jai.opimage;
/*      */ 
/*      */ import java.awt.Rectangle;
/*      */ import java.awt.geom.AffineTransform;
/*      */ import java.awt.geom.Point2D;
/*      */ import java.awt.image.Raster;
/*      */ import java.awt.image.RenderedImage;
/*      */ import java.awt.image.WritableRaster;
/*      */ import java.util.Map;
/*      */ import javax.media.jai.BorderExtender;
/*      */ import javax.media.jai.ImageLayout;
/*      */ import javax.media.jai.Interpolation;
/*      */ import javax.media.jai.RasterAccessor;
/*      */ import javax.media.jai.RasterFormatTag;
/*      */ 
/*      */ final class AffineBicubic2OpImage extends AffineOpImage {
/*      */   private int subsampleBits;
/*      */   
/*      */   private int shiftvalue;
/*      */   
/*      */   public AffineBicubic2OpImage(RenderedImage source, BorderExtender extender, Map config, ImageLayout layout, AffineTransform transform, Interpolation interp, double[] backgroundValues) {
/*   55 */     super(source, extender, config, layout, transform, interp, backgroundValues);
/*   63 */     this.subsampleBits = interp.getSubsampleBitsH();
/*   64 */     this.shiftvalue = 1 << this.subsampleBits;
/*      */   }
/*      */   
/*      */   protected void computeRect(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/*   80 */     RasterFormatTag[] formatTags = getFormatTags();
/*   82 */     Raster source = sources[0];
/*   84 */     Rectangle srcRect = source.getBounds();
/*   86 */     int srcRectX = srcRect.x;
/*   87 */     int srcRectY = srcRect.y;
/*   96 */     RasterAccessor srcAccessor = new RasterAccessor(source, srcRect, formatTags[0], getSourceImage(0).getColorModel());
/*  101 */     RasterAccessor dstAccessor = new RasterAccessor(dest, destRect, formatTags[1], getColorModel());
/*  107 */     switch (dstAccessor.getDataType()) {
/*      */       case 0:
/*  109 */         byteLoop(srcAccessor, destRect, srcRectX, srcRectY, dstAccessor);
/*      */         break;
/*      */       case 3:
/*  117 */         intLoop(srcAccessor, destRect, srcRectX, srcRectY, dstAccessor);
/*      */         break;
/*      */       case 2:
/*  125 */         shortLoop(srcAccessor, destRect, srcRectX, srcRectY, dstAccessor);
/*      */         break;
/*      */       case 1:
/*  133 */         ushortLoop(srcAccessor, destRect, srcRectX, srcRectY, dstAccessor);
/*      */         break;
/*      */       case 4:
/*  141 */         floatLoop(srcAccessor, destRect, srcRectX, srcRectY, dstAccessor);
/*      */         break;
/*      */       case 5:
/*  149 */         doubleLoop(srcAccessor, destRect, srcRectX, srcRectY, dstAccessor);
/*      */         break;
/*      */     } 
/*  160 */     if (dstAccessor.isDataCopy()) {
/*  161 */       dstAccessor.clampDataArrays();
/*  162 */       dstAccessor.copyDataToRaster();
/*      */     } 
/*      */   }
/*      */   
/*      */   private void byteLoop(RasterAccessor src, Rectangle destRect, int srcRectX, int srcRectY, RasterAccessor dst) {
/*  172 */     float src_rect_x1 = src.getX();
/*  173 */     float src_rect_y1 = src.getY();
/*  174 */     float src_rect_x2 = src_rect_x1 + src.getWidth();
/*  175 */     float src_rect_y2 = src_rect_y1 + src.getHeight();
/*  204 */     int dstOffset = 0;
/*  206 */     Point2D dst_pt = new Point2D.Float();
/*  207 */     Point2D src_pt = new Point2D.Float();
/*  209 */     byte[][] dstDataArrays = dst.getByteDataArrays();
/*  210 */     int[] dstBandOffsets = dst.getBandOffsets();
/*  211 */     int dstPixelStride = dst.getPixelStride();
/*  212 */     int dstScanlineStride = dst.getScanlineStride();
/*  214 */     byte[][] srcDataArrays = src.getByteDataArrays();
/*  215 */     int[] bandOffsets = src.getBandOffsets();
/*  216 */     int srcPixelStride = src.getPixelStride();
/*  217 */     int srcScanlineStride = src.getScanlineStride();
/*  219 */     int dst_num_bands = dst.getNumBands();
/*  221 */     int dst_min_x = destRect.x;
/*  222 */     int dst_min_y = destRect.y;
/*  223 */     int dst_max_x = destRect.x + destRect.width;
/*  224 */     int dst_max_y = destRect.y + destRect.height;
/*  226 */     byte[] backgroundByte = new byte[dst_num_bands];
/*  227 */     for (int i = 0; i < dst_num_bands; i++)
/*  228 */       backgroundByte[i] = (byte)(int)this.backgroundValues[i]; 
/*  230 */     for (int y = dst_min_y; y < dst_max_y; y++) {
/*  231 */       int dstPixelOffset = dstOffset;
/*  235 */       dst_pt.setLocation(dst_min_x + 0.5D, y + 0.5D);
/*  237 */       mapDestPoint(dst_pt, src_pt);
/*  240 */       float s_x = (float)src_pt.getX();
/*  241 */       float s_y = (float)src_pt.getY();
/*  244 */       s_x = (float)(s_x - 0.5D);
/*  245 */       s_y = (float)(s_y - 0.5D);
/*  248 */       int s_ix = (int)Math.floor(s_x);
/*  249 */       int s_iy = (int)Math.floor(s_y);
/*  251 */       float fracx = s_x - s_ix;
/*  252 */       float fracy = s_y - s_iy;
/*  255 */       int p_x = (s_ix - srcRectX) * srcPixelStride;
/*  256 */       int p_y = (s_iy - srcRectY) * srcScanlineStride;
/*  267 */       int p__ = p_x + p_y - srcScanlineStride - srcPixelStride;
/*  268 */       int p0_ = p__ + srcPixelStride;
/*  269 */       int p1_ = p0_ + srcPixelStride;
/*  270 */       int p2_ = p1_ + srcPixelStride;
/*  271 */       int p_0 = p__ + srcScanlineStride;
/*  272 */       int p00 = p_0 + srcPixelStride;
/*  273 */       int p10 = p00 + srcPixelStride;
/*  274 */       int p20 = p10 + srcPixelStride;
/*  275 */       int p_1 = p_0 + srcScanlineStride;
/*  276 */       int p01 = p_1 + srcPixelStride;
/*  277 */       int p11 = p01 + srcPixelStride;
/*  278 */       int p21 = p11 + srcPixelStride;
/*  279 */       int p_2 = p_1 + srcScanlineStride;
/*  280 */       int p02 = p_2 + srcPixelStride;
/*  281 */       int p12 = p02 + srcPixelStride;
/*  282 */       int p22 = p12 + srcPixelStride;
/*  284 */       for (int x = dst_min_x; x < dst_max_x; x++) {
/*  289 */         if (s_ix >= src_rect_x1 + 1.0F && s_ix < src_rect_x2 - 2.0F && s_iy >= src_rect_y1 + 1.0F && s_iy < src_rect_y2 - 2.0F) {
/*  293 */           for (int k2 = 0; k2 < dst_num_bands; k2++) {
/*      */             int result;
/*  300 */             byte[] tmp_row = srcDataArrays[k2];
/*  301 */             int tmp_col = bandOffsets[k2];
/*  303 */             int s__ = tmp_row[p__ + tmp_col] & 0xFF;
/*  304 */             int s0_ = tmp_row[p0_ + tmp_col] & 0xFF;
/*  305 */             int s1_ = tmp_row[p1_ + tmp_col] & 0xFF;
/*  306 */             int s2_ = tmp_row[p2_ + tmp_col] & 0xFF;
/*  307 */             int s_0 = tmp_row[p_0 + tmp_col] & 0xFF;
/*  308 */             int s00 = tmp_row[p00 + tmp_col] & 0xFF;
/*  309 */             int s10 = tmp_row[p10 + tmp_col] & 0xFF;
/*  310 */             int s20 = tmp_row[p20 + tmp_col] & 0xFF;
/*  311 */             int s_1 = tmp_row[p_1 + tmp_col] & 0xFF;
/*  312 */             int s01 = tmp_row[p01 + tmp_col] & 0xFF;
/*  313 */             int s11 = tmp_row[p11 + tmp_col] & 0xFF;
/*  314 */             int s21 = tmp_row[p21 + tmp_col] & 0xFF;
/*  315 */             int s_2 = tmp_row[p_2 + tmp_col] & 0xFF;
/*  316 */             int s02 = tmp_row[p02 + tmp_col] & 0xFF;
/*  317 */             int s12 = tmp_row[p12 + tmp_col] & 0xFF;
/*  318 */             int s22 = tmp_row[p22 + tmp_col] & 0xFF;
/*  321 */             int xfrac = (int)(fracx * this.shiftvalue);
/*  322 */             int yfrac = (int)(fracy * this.shiftvalue);
/*  329 */             float s = this.interp.interpolate(s__, s0_, s1_, s2_, s_0, s00, s10, s20, s_1, s01, s11, s21, s_2, s02, s12, s22, xfrac, yfrac);
/*  337 */             if (s < 0.5F) {
/*  338 */               result = 0;
/*  339 */             } else if (s > 254.5F) {
/*  340 */               result = 255;
/*      */             } else {
/*  342 */               result = (int)(s + 0.5F);
/*      */             } 
/*  346 */             dstDataArrays[k2][dstPixelOffset + dstBandOffsets[k2]] = (byte)(result & 0xFF);
/*      */           } 
/*  350 */         } else if (this.setBackground) {
/*  351 */           for (int k = 0; k < dst_num_bands; k++)
/*  352 */             dstDataArrays[k][dstPixelOffset + dstBandOffsets[k]] = backgroundByte[k]; 
/*      */         } 
/*  357 */         if (fracx < this.fracdx1) {
/*  358 */           s_ix += this.incx;
/*  359 */           fracx = (float)(fracx + this.fracdx);
/*  360 */           if (fracx == 1.0F)
/*  362 */             fracx = 0.999999F; 
/*      */         } else {
/*  365 */           s_ix += this.incx1;
/*  366 */           fracx = (float)(fracx - this.fracdx1);
/*      */         } 
/*  369 */         if (fracy < this.fracdy1) {
/*  370 */           s_iy += this.incy;
/*  371 */           fracy = (float)(fracy + this.fracdy);
/*  372 */           if (fracy == 1.0F)
/*  374 */             fracy = 0.999999F; 
/*      */         } else {
/*  377 */           s_iy += this.incy1;
/*  378 */           fracy = (float)(fracy - this.fracdy1);
/*      */         } 
/*  382 */         p_x = (s_ix - srcRectX) * srcPixelStride;
/*  383 */         p_y = (s_iy - srcRectY) * srcScanlineStride;
/*  394 */         p__ = p_x + p_y - srcScanlineStride - srcPixelStride;
/*  395 */         p0_ = p__ + srcPixelStride;
/*  396 */         p1_ = p0_ + srcPixelStride;
/*  397 */         p2_ = p1_ + srcPixelStride;
/*  398 */         p_0 = p__ + srcScanlineStride;
/*  399 */         p00 = p_0 + srcPixelStride;
/*  400 */         p10 = p00 + srcPixelStride;
/*  401 */         p20 = p10 + srcPixelStride;
/*  402 */         p_1 = p_0 + srcScanlineStride;
/*  403 */         p01 = p_1 + srcPixelStride;
/*  404 */         p11 = p01 + srcPixelStride;
/*  405 */         p21 = p11 + srcPixelStride;
/*  406 */         p_2 = p_1 + srcScanlineStride;
/*  407 */         p02 = p_2 + srcPixelStride;
/*  408 */         p12 = p02 + srcPixelStride;
/*  409 */         p22 = p12 + srcPixelStride;
/*  411 */         dstPixelOffset += dstPixelStride;
/*      */       } 
/*  414 */       dstOffset += dstScanlineStride;
/*      */     } 
/*      */   }
/*      */   
/*      */   private void intLoop(RasterAccessor src, Rectangle destRect, int srcRectX, int srcRectY, RasterAccessor dst) {
/*  425 */     float src_rect_x1 = src.getX();
/*  426 */     float src_rect_y1 = src.getY();
/*  427 */     float src_rect_x2 = src_rect_x1 + src.getWidth();
/*  428 */     float src_rect_y2 = src_rect_y1 + src.getHeight();
/*  455 */     int dstOffset = 0;
/*  459 */     Point2D dst_pt = new Point2D.Float();
/*  460 */     Point2D src_pt = new Point2D.Float();
/*  462 */     int[][] dstDataArrays = dst.getIntDataArrays();
/*  463 */     int[] dstBandOffsets = dst.getBandOffsets();
/*  464 */     int dstPixelStride = dst.getPixelStride();
/*  465 */     int dstScanlineStride = dst.getScanlineStride();
/*  467 */     int[][] srcDataArrays = src.getIntDataArrays();
/*  468 */     int[] bandOffsets = src.getBandOffsets();
/*  469 */     int srcPixelStride = src.getPixelStride();
/*  470 */     int srcScanlineStride = src.getScanlineStride();
/*  472 */     int dst_num_bands = dst.getNumBands();
/*  474 */     int dst_min_x = destRect.x;
/*  475 */     int dst_min_y = destRect.y;
/*  476 */     int dst_max_x = destRect.x + destRect.width;
/*  477 */     int dst_max_y = destRect.y + destRect.height;
/*  479 */     int[] backgroundInt = new int[dst_num_bands];
/*  480 */     for (int i = 0; i < dst_num_bands; i++)
/*  481 */       backgroundInt[i] = (int)this.backgroundValues[i]; 
/*  483 */     for (int y = dst_min_y; y < dst_max_y; y++) {
/*  484 */       int dstPixelOffset = dstOffset;
/*  488 */       dst_pt.setLocation(dst_min_x + 0.5D, y + 0.5D);
/*  490 */       mapDestPoint(dst_pt, src_pt);
/*  493 */       float s_x = (float)src_pt.getX();
/*  494 */       float s_y = (float)src_pt.getY();
/*  497 */       s_x = (float)(s_x - 0.5D);
/*  498 */       s_y = (float)(s_y - 0.5D);
/*  501 */       int s_ix = (int)Math.floor(s_x);
/*  502 */       int s_iy = (int)Math.floor(s_y);
/*  504 */       float fracx = s_x - s_ix;
/*  505 */       float fracy = s_y - s_iy;
/*  508 */       int p_x = (s_ix - srcRectX) * srcPixelStride;
/*  509 */       int p_y = (s_iy - srcRectY) * srcScanlineStride;
/*  520 */       int p__ = p_x + p_y - srcScanlineStride - srcPixelStride;
/*  521 */       int p0_ = p__ + srcPixelStride;
/*  522 */       int p1_ = p0_ + srcPixelStride;
/*  523 */       int p2_ = p1_ + srcPixelStride;
/*  524 */       int p_0 = p__ + srcScanlineStride;
/*  525 */       int p00 = p_0 + srcPixelStride;
/*  526 */       int p10 = p00 + srcPixelStride;
/*  527 */       int p20 = p10 + srcPixelStride;
/*  528 */       int p_1 = p_0 + srcScanlineStride;
/*  529 */       int p01 = p_1 + srcPixelStride;
/*  530 */       int p11 = p01 + srcPixelStride;
/*  531 */       int p21 = p11 + srcPixelStride;
/*  532 */       int p_2 = p_1 + srcScanlineStride;
/*  533 */       int p02 = p_2 + srcPixelStride;
/*  534 */       int p12 = p02 + srcPixelStride;
/*  535 */       int p22 = p12 + srcPixelStride;
/*  537 */       for (int x = dst_min_x; x < dst_max_x; x++) {
/*  541 */         if (s_ix >= src_rect_x1 + 1.0F && s_ix < src_rect_x2 - 2.0F && s_iy >= src_rect_y1 + 1.0F && s_iy < src_rect_y2 - 2.0F) {
/*  545 */           for (int k2 = 0; k2 < dst_num_bands; k2++) {
/*  552 */             int result, tmp_row[] = srcDataArrays[k2];
/*  553 */             int tmp_col = bandOffsets[k2];
/*  555 */             int s__ = tmp_row[p__ + tmp_col];
/*  556 */             int s0_ = tmp_row[p0_ + tmp_col];
/*  557 */             int s1_ = tmp_row[p1_ + tmp_col];
/*  558 */             int s2_ = tmp_row[p2_ + tmp_col];
/*  559 */             int s_0 = tmp_row[p_0 + tmp_col];
/*  560 */             int s00 = tmp_row[p00 + tmp_col];
/*  561 */             int s10 = tmp_row[p10 + tmp_col];
/*  562 */             int s20 = tmp_row[p20 + tmp_col];
/*  563 */             int s_1 = tmp_row[p_1 + tmp_col];
/*  564 */             int s01 = tmp_row[p01 + tmp_col];
/*  565 */             int s11 = tmp_row[p11 + tmp_col];
/*  566 */             int s21 = tmp_row[p21 + tmp_col];
/*  567 */             int s_2 = tmp_row[p_2 + tmp_col];
/*  568 */             int s02 = tmp_row[p02 + tmp_col];
/*  569 */             int s12 = tmp_row[p12 + tmp_col];
/*  570 */             int s22 = tmp_row[p22 + tmp_col];
/*  573 */             int xfrac = (int)(fracx * this.shiftvalue);
/*  574 */             int yfrac = (int)(fracy * this.shiftvalue);
/*  581 */             float s = this.interp.interpolate(s__, s0_, s1_, s2_, s_0, s00, s10, s20, s_1, s01, s11, s21, s_2, s02, s12, s22, xfrac, yfrac);
/*  588 */             if (s < -2.1474836E9F) {
/*  589 */               result = Integer.MIN_VALUE;
/*  590 */             } else if (s > 2.1474836E9F) {
/*  591 */               result = Integer.MAX_VALUE;
/*  592 */             } else if (s > 0.0D) {
/*  593 */               result = (int)(s + 0.5F);
/*      */             } else {
/*  595 */               result = (int)(s - 0.5F);
/*      */             } 
/*  599 */             dstDataArrays[k2][dstPixelOffset + dstBandOffsets[k2]] = result;
/*      */           } 
/*  602 */         } else if (this.setBackground) {
/*  603 */           for (int k = 0; k < dst_num_bands; k++)
/*  604 */             dstDataArrays[k][dstPixelOffset + dstBandOffsets[k]] = backgroundInt[k]; 
/*      */         } 
/*  609 */         if (fracx < this.fracdx1) {
/*  610 */           s_ix += this.incx;
/*  611 */           fracx = (float)(fracx + this.fracdx);
/*  612 */           if (fracx == 1.0F)
/*  614 */             fracx = 0.999999F; 
/*      */         } else {
/*  617 */           s_ix += this.incx1;
/*  618 */           fracx = (float)(fracx - this.fracdx1);
/*      */         } 
/*  621 */         if (fracy < this.fracdy1) {
/*  622 */           s_iy += this.incy;
/*  623 */           fracy = (float)(fracy + this.fracdy);
/*  624 */           if (fracy == 1.0F)
/*  626 */             fracy = 0.999999F; 
/*      */         } else {
/*  629 */           s_iy += this.incy1;
/*  630 */           fracy = (float)(fracy - this.fracdy1);
/*      */         } 
/*  634 */         p_x = (s_ix - srcRectX) * srcPixelStride;
/*  635 */         p_y = (s_iy - srcRectY) * srcScanlineStride;
/*  646 */         p__ = p_x + p_y - srcScanlineStride - srcPixelStride;
/*  647 */         p0_ = p__ + srcPixelStride;
/*  648 */         p1_ = p0_ + srcPixelStride;
/*  649 */         p2_ = p1_ + srcPixelStride;
/*  650 */         p_0 = p__ + srcScanlineStride;
/*  651 */         p00 = p_0 + srcPixelStride;
/*  652 */         p10 = p00 + srcPixelStride;
/*  653 */         p20 = p10 + srcPixelStride;
/*  654 */         p_1 = p_0 + srcScanlineStride;
/*  655 */         p01 = p_1 + srcPixelStride;
/*  656 */         p11 = p01 + srcPixelStride;
/*  657 */         p21 = p11 + srcPixelStride;
/*  658 */         p_2 = p_1 + srcScanlineStride;
/*  659 */         p02 = p_2 + srcPixelStride;
/*  660 */         p12 = p02 + srcPixelStride;
/*  661 */         p22 = p12 + srcPixelStride;
/*  663 */         dstPixelOffset += dstPixelStride;
/*      */       } 
/*  666 */       dstOffset += dstScanlineStride;
/*      */     } 
/*      */   }
/*      */   
/*      */   private void shortLoop(RasterAccessor src, Rectangle destRect, int srcRectX, int srcRectY, RasterAccessor dst) {
/*  676 */     float src_rect_x1 = src.getX();
/*  677 */     float src_rect_y1 = src.getY();
/*  678 */     float src_rect_x2 = src_rect_x1 + src.getWidth();
/*  679 */     float src_rect_y2 = src_rect_y1 + src.getHeight();
/*  709 */     int dstOffset = 0;
/*  711 */     Point2D dst_pt = new Point2D.Float();
/*  712 */     Point2D src_pt = new Point2D.Float();
/*  714 */     short[][] dstDataArrays = dst.getShortDataArrays();
/*  715 */     int[] dstBandOffsets = dst.getBandOffsets();
/*  716 */     int dstPixelStride = dst.getPixelStride();
/*  717 */     int dstScanlineStride = dst.getScanlineStride();
/*  719 */     short[][] srcDataArrays = src.getShortDataArrays();
/*  720 */     int[] bandOffsets = src.getBandOffsets();
/*  721 */     int srcPixelStride = src.getPixelStride();
/*  722 */     int srcScanlineStride = src.getScanlineStride();
/*  724 */     int dst_num_bands = dst.getNumBands();
/*  726 */     int dst_min_x = destRect.x;
/*  727 */     int dst_min_y = destRect.y;
/*  728 */     int dst_max_x = destRect.x + destRect.width;
/*  729 */     int dst_max_y = destRect.y + destRect.height;
/*  731 */     short[] backgroundShort = new short[dst_num_bands];
/*  732 */     for (int i = 0; i < dst_num_bands; i++)
/*  733 */       backgroundShort[i] = (short)(int)this.backgroundValues[i]; 
/*  735 */     for (int y = dst_min_y; y < dst_max_y; y++) {
/*  737 */       int dstPixelOffset = dstOffset;
/*  741 */       dst_pt.setLocation(dst_min_x + 0.5D, y + 0.5D);
/*  743 */       mapDestPoint(dst_pt, src_pt);
/*  746 */       float s_x = (float)src_pt.getX();
/*  747 */       float s_y = (float)src_pt.getY();
/*  750 */       s_x = (float)(s_x - 0.5D);
/*  751 */       s_y = (float)(s_y - 0.5D);
/*  754 */       int s_ix = (int)Math.floor(s_x);
/*  755 */       int s_iy = (int)Math.floor(s_y);
/*  757 */       float fracx = s_x - s_ix;
/*  758 */       float fracy = s_y - s_iy;
/*  761 */       int p_x = (s_ix - srcRectX) * srcPixelStride;
/*  762 */       int p_y = (s_iy - srcRectY) * srcScanlineStride;
/*  773 */       int p__ = p_x + p_y - srcScanlineStride - srcPixelStride;
/*  774 */       int p0_ = p__ + srcPixelStride;
/*  775 */       int p1_ = p0_ + srcPixelStride;
/*  776 */       int p2_ = p1_ + srcPixelStride;
/*  777 */       int p_0 = p__ + srcScanlineStride;
/*  778 */       int p00 = p_0 + srcPixelStride;
/*  779 */       int p10 = p00 + srcPixelStride;
/*  780 */       int p20 = p10 + srcPixelStride;
/*  781 */       int p_1 = p_0 + srcScanlineStride;
/*  782 */       int p01 = p_1 + srcPixelStride;
/*  783 */       int p11 = p01 + srcPixelStride;
/*  784 */       int p21 = p11 + srcPixelStride;
/*  785 */       int p_2 = p_1 + srcScanlineStride;
/*  786 */       int p02 = p_2 + srcPixelStride;
/*  787 */       int p12 = p02 + srcPixelStride;
/*  788 */       int p22 = p12 + srcPixelStride;
/*  790 */       for (int x = dst_min_x; x < dst_max_x; x++) {
/*  794 */         if (s_ix >= src_rect_x1 + 1.0F && s_ix < src_rect_x2 - 2.0F && s_iy >= src_rect_y1 + 1.0F && s_iy < src_rect_y2 - 2.0F) {
/*  798 */           for (int k2 = 0; k2 < dst_num_bands; k2++) {
/*  805 */             short result, tmp_row[] = srcDataArrays[k2];
/*  806 */             int tmp_col = bandOffsets[k2];
/*  808 */             short s__ = tmp_row[p__ + tmp_col];
/*  809 */             short s0_ = tmp_row[p0_ + tmp_col];
/*  810 */             short s1_ = tmp_row[p1_ + tmp_col];
/*  811 */             short s2_ = tmp_row[p2_ + tmp_col];
/*  812 */             short s_0 = tmp_row[p_0 + tmp_col];
/*  813 */             short s00 = tmp_row[p00 + tmp_col];
/*  814 */             short s10 = tmp_row[p10 + tmp_col];
/*  815 */             short s20 = tmp_row[p20 + tmp_col];
/*  816 */             short s_1 = tmp_row[p_1 + tmp_col];
/*  817 */             short s01 = tmp_row[p01 + tmp_col];
/*  818 */             short s11 = tmp_row[p11 + tmp_col];
/*  819 */             short s21 = tmp_row[p21 + tmp_col];
/*  820 */             short s_2 = tmp_row[p_2 + tmp_col];
/*  821 */             short s02 = tmp_row[p02 + tmp_col];
/*  822 */             short s12 = tmp_row[p12 + tmp_col];
/*  823 */             short s22 = tmp_row[p22 + tmp_col];
/*  826 */             int xfrac = (int)(fracx * this.shiftvalue);
/*  827 */             int yfrac = (int)(fracy * this.shiftvalue);
/*  834 */             float s = this.interp.interpolate(s__, s0_, s1_, s2_, s_0, s00, s10, s20, s_1, s01, s11, s21, s_2, s02, s12, s22, xfrac, yfrac);
/*  841 */             if (s < -32768.0F) {
/*  842 */               result = Short.MIN_VALUE;
/*  843 */             } else if (s > 32767.0F) {
/*  844 */               result = Short.MAX_VALUE;
/*  845 */             } else if (s > 0.0D) {
/*  846 */               result = (short)(int)(s + 0.5F);
/*      */             } else {
/*  848 */               result = (short)(int)(s - 0.5F);
/*      */             } 
/*  852 */             dstDataArrays[k2][dstPixelOffset + dstBandOffsets[k2]] = result;
/*      */           } 
/*  855 */         } else if (this.setBackground) {
/*  856 */           for (int k = 0; k < dst_num_bands; k++)
/*  857 */             dstDataArrays[k][dstPixelOffset + dstBandOffsets[k]] = backgroundShort[k]; 
/*      */         } 
/*  862 */         if (fracx < this.fracdx1) {
/*  863 */           s_ix += this.incx;
/*  864 */           fracx = (float)(fracx + this.fracdx);
/*  865 */           if (fracx == 1.0F)
/*  867 */             fracx = 0.999999F; 
/*      */         } else {
/*  870 */           s_ix += this.incx1;
/*  871 */           fracx = (float)(fracx - this.fracdx1);
/*      */         } 
/*  874 */         if (fracy < this.fracdy1) {
/*  875 */           s_iy += this.incy;
/*  876 */           fracy = (float)(fracy + this.fracdy);
/*  877 */           if (fracy == 1.0F)
/*  879 */             fracy = 0.999999F; 
/*      */         } else {
/*  882 */           s_iy += this.incy1;
/*  883 */           fracy = (float)(fracy - this.fracdy1);
/*      */         } 
/*  887 */         p_x = (s_ix - srcRectX) * srcPixelStride;
/*  888 */         p_y = (s_iy - srcRectY) * srcScanlineStride;
/*  899 */         p__ = p_x + p_y - srcScanlineStride - srcPixelStride;
/*  900 */         p0_ = p__ + srcPixelStride;
/*  901 */         p1_ = p0_ + srcPixelStride;
/*  902 */         p2_ = p1_ + srcPixelStride;
/*  903 */         p_0 = p__ + srcScanlineStride;
/*  904 */         p00 = p_0 + srcPixelStride;
/*  905 */         p10 = p00 + srcPixelStride;
/*  906 */         p20 = p10 + srcPixelStride;
/*  907 */         p_1 = p_0 + srcScanlineStride;
/*  908 */         p01 = p_1 + srcPixelStride;
/*  909 */         p11 = p01 + srcPixelStride;
/*  910 */         p21 = p11 + srcPixelStride;
/*  911 */         p_2 = p_1 + srcScanlineStride;
/*  912 */         p02 = p_2 + srcPixelStride;
/*  913 */         p12 = p02 + srcPixelStride;
/*  914 */         p22 = p12 + srcPixelStride;
/*  916 */         dstPixelOffset += dstPixelStride;
/*      */       } 
/*  919 */       dstOffset += dstScanlineStride;
/*      */     } 
/*      */   }
/*      */   
/*      */   private void ushortLoop(RasterAccessor src, Rectangle destRect, int srcRectX, int srcRectY, RasterAccessor dst) {
/*  929 */     float src_rect_x1 = src.getX();
/*  930 */     float src_rect_y1 = src.getY();
/*  931 */     float src_rect_x2 = src_rect_x1 + src.getWidth();
/*  932 */     float src_rect_y2 = src_rect_y1 + src.getHeight();
/*  962 */     int dstOffset = 0;
/*  964 */     Point2D dst_pt = new Point2D.Float();
/*  965 */     Point2D src_pt = new Point2D.Float();
/*  967 */     short[][] dstDataArrays = dst.getShortDataArrays();
/*  968 */     int[] dstBandOffsets = dst.getBandOffsets();
/*  969 */     int dstPixelStride = dst.getPixelStride();
/*  970 */     int dstScanlineStride = dst.getScanlineStride();
/*  972 */     short[][] srcDataArrays = src.getShortDataArrays();
/*  973 */     int[] bandOffsets = src.getBandOffsets();
/*  974 */     int srcPixelStride = src.getPixelStride();
/*  975 */     int srcScanlineStride = src.getScanlineStride();
/*  977 */     int dst_num_bands = dst.getNumBands();
/*  979 */     int dst_min_x = destRect.x;
/*  980 */     int dst_min_y = destRect.y;
/*  981 */     int dst_max_x = destRect.x + destRect.width;
/*  982 */     int dst_max_y = destRect.y + destRect.height;
/*  984 */     short[] backgroundUShort = new short[dst_num_bands];
/*  985 */     for (int i = 0; i < dst_num_bands; i++)
/*  986 */       backgroundUShort[i] = (short)(int)this.backgroundValues[i]; 
/*  988 */     for (int y = dst_min_y; y < dst_max_y; y++) {
/*  989 */       int dstPixelOffset = dstOffset;
/*  993 */       dst_pt.setLocation(dst_min_x + 0.5D, y + 0.5D);
/*  995 */       mapDestPoint(dst_pt, src_pt);
/*  998 */       float s_x = (float)src_pt.getX();
/*  999 */       float s_y = (float)src_pt.getY();
/* 1002 */       s_x = (float)(s_x - 0.5D);
/* 1003 */       s_y = (float)(s_y - 0.5D);
/* 1006 */       int s_ix = (int)Math.floor(s_x);
/* 1007 */       int s_iy = (int)Math.floor(s_y);
/* 1009 */       float fracx = s_x - s_ix;
/* 1010 */       float fracy = s_y - s_iy;
/* 1013 */       int p_x = (s_ix - srcRectX) * srcPixelStride;
/* 1014 */       int p_y = (s_iy - srcRectY) * srcScanlineStride;
/* 1025 */       int p__ = p_x + p_y - srcScanlineStride - srcPixelStride;
/* 1026 */       int p0_ = p__ + srcPixelStride;
/* 1027 */       int p1_ = p0_ + srcPixelStride;
/* 1028 */       int p2_ = p1_ + srcPixelStride;
/* 1029 */       int p_0 = p__ + srcScanlineStride;
/* 1030 */       int p00 = p_0 + srcPixelStride;
/* 1031 */       int p10 = p00 + srcPixelStride;
/* 1032 */       int p20 = p10 + srcPixelStride;
/* 1033 */       int p_1 = p_0 + srcScanlineStride;
/* 1034 */       int p01 = p_1 + srcPixelStride;
/* 1035 */       int p11 = p01 + srcPixelStride;
/* 1036 */       int p21 = p11 + srcPixelStride;
/* 1037 */       int p_2 = p_1 + srcScanlineStride;
/* 1038 */       int p02 = p_2 + srcPixelStride;
/* 1039 */       int p12 = p02 + srcPixelStride;
/* 1040 */       int p22 = p12 + srcPixelStride;
/* 1042 */       for (int x = dst_min_x; x < dst_max_x; x++) {
/* 1046 */         if (s_ix >= src_rect_x1 + 1.0F && s_ix < src_rect_x2 - 2.0F && s_iy >= src_rect_y1 + 1.0F && s_iy < src_rect_y2 - 2.0F) {
/* 1050 */           for (int k2 = 0; k2 < dst_num_bands; k2++) {
/*      */             int result;
/* 1057 */             short[] tmp_row = srcDataArrays[k2];
/* 1058 */             int tmp_col = bandOffsets[k2];
/* 1060 */             int s__ = tmp_row[p__ + tmp_col] & 0xFFFF;
/* 1061 */             int s0_ = tmp_row[p0_ + tmp_col] & 0xFFFF;
/* 1062 */             int s1_ = tmp_row[p1_ + tmp_col] & 0xFFFF;
/* 1063 */             int s2_ = tmp_row[p2_ + tmp_col] & 0xFFFF;
/* 1064 */             int s_0 = tmp_row[p_0 + tmp_col] & 0xFFFF;
/* 1065 */             int s00 = tmp_row[p00 + tmp_col] & 0xFFFF;
/* 1066 */             int s10 = tmp_row[p10 + tmp_col] & 0xFFFF;
/* 1067 */             int s20 = tmp_row[p20 + tmp_col] & 0xFFFF;
/* 1068 */             int s_1 = tmp_row[p_1 + tmp_col] & 0xFFFF;
/* 1069 */             int s01 = tmp_row[p01 + tmp_col] & 0xFFFF;
/* 1070 */             int s11 = tmp_row[p11 + tmp_col] & 0xFFFF;
/* 1071 */             int s21 = tmp_row[p21 + tmp_col] & 0xFFFF;
/* 1072 */             int s_2 = tmp_row[p_2 + tmp_col] & 0xFFFF;
/* 1073 */             int s02 = tmp_row[p02 + tmp_col] & 0xFFFF;
/* 1074 */             int s12 = tmp_row[p12 + tmp_col] & 0xFFFF;
/* 1075 */             int s22 = tmp_row[p22 + tmp_col] & 0xFFFF;
/* 1079 */             int xfrac = (int)(fracx * this.shiftvalue);
/* 1080 */             int yfrac = (int)(fracy * this.shiftvalue);
/* 1087 */             float s = this.interp.interpolate(s__, s0_, s1_, s2_, s_0, s00, s10, s20, s_1, s01, s11, s21, s_2, s02, s12, s22, xfrac, yfrac);
/* 1094 */             if (s < 0.0D) {
/* 1095 */               result = 0;
/* 1096 */             } else if (s > 65535.0F) {
/* 1097 */               result = 65535;
/*      */             } else {
/* 1099 */               result = (int)(s + 0.5F);
/*      */             } 
/* 1103 */             dstDataArrays[k2][dstPixelOffset + dstBandOffsets[k2]] = (short)(result & 0xFFFF);
/*      */           } 
/* 1107 */         } else if (this.setBackground) {
/* 1108 */           for (int k = 0; k < dst_num_bands; k++)
/* 1109 */             dstDataArrays[k][dstPixelOffset + dstBandOffsets[k]] = backgroundUShort[k]; 
/*      */         } 
/* 1114 */         if (fracx < this.fracdx1) {
/* 1115 */           s_ix += this.incx;
/* 1116 */           fracx = (float)(fracx + this.fracdx);
/* 1117 */           if (fracx == 1.0F)
/* 1119 */             fracx = 0.999999F; 
/*      */         } else {
/* 1122 */           s_ix += this.incx1;
/* 1123 */           fracx = (float)(fracx - this.fracdx1);
/*      */         } 
/* 1126 */         if (fracy < this.fracdy1) {
/* 1127 */           s_iy += this.incy;
/* 1128 */           fracy = (float)(fracy + this.fracdy);
/* 1129 */           if (fracy == 1.0F)
/* 1131 */             fracy = 0.999999F; 
/*      */         } else {
/* 1134 */           s_iy += this.incy1;
/* 1135 */           fracy = (float)(fracy - this.fracdy1);
/*      */         } 
/* 1139 */         p_x = (s_ix - srcRectX) * srcPixelStride;
/* 1140 */         p_y = (s_iy - srcRectY) * srcScanlineStride;
/* 1151 */         p__ = p_x + p_y - srcScanlineStride - srcPixelStride;
/* 1152 */         p0_ = p__ + srcPixelStride;
/* 1153 */         p1_ = p0_ + srcPixelStride;
/* 1154 */         p2_ = p1_ + srcPixelStride;
/* 1155 */         p_0 = p__ + srcScanlineStride;
/* 1156 */         p00 = p_0 + srcPixelStride;
/* 1157 */         p10 = p00 + srcPixelStride;
/* 1158 */         p20 = p10 + srcPixelStride;
/* 1159 */         p_1 = p_0 + srcScanlineStride;
/* 1160 */         p01 = p_1 + srcPixelStride;
/* 1161 */         p11 = p01 + srcPixelStride;
/* 1162 */         p21 = p11 + srcPixelStride;
/* 1163 */         p_2 = p_1 + srcScanlineStride;
/* 1164 */         p02 = p_2 + srcPixelStride;
/* 1165 */         p12 = p02 + srcPixelStride;
/* 1166 */         p22 = p12 + srcPixelStride;
/* 1168 */         dstPixelOffset += dstPixelStride;
/*      */       } 
/* 1171 */       dstOffset += dstScanlineStride;
/*      */     } 
/*      */   }
/*      */   
/*      */   private void floatLoop(RasterAccessor src, Rectangle destRect, int srcRectX, int srcRectY, RasterAccessor dst) {
/* 1181 */     float src_rect_x1 = src.getX();
/* 1182 */     float src_rect_y1 = src.getY();
/* 1183 */     float src_rect_x2 = src_rect_x1 + src.getWidth();
/* 1184 */     float src_rect_y2 = src_rect_y1 + src.getHeight();
/* 1210 */     int dstOffset = 0;
/* 1212 */     Point2D dst_pt = new Point2D.Float();
/* 1213 */     Point2D src_pt = new Point2D.Float();
/* 1215 */     float[][] dstDataArrays = dst.getFloatDataArrays();
/* 1216 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 1217 */     int dstPixelStride = dst.getPixelStride();
/* 1218 */     int dstScanlineStride = dst.getScanlineStride();
/* 1220 */     float[][] srcDataArrays = src.getFloatDataArrays();
/* 1221 */     int[] bandOffsets = src.getBandOffsets();
/* 1222 */     int srcPixelStride = src.getPixelStride();
/* 1223 */     int srcScanlineStride = src.getScanlineStride();
/* 1225 */     int dst_num_bands = dst.getNumBands();
/* 1227 */     int dst_min_x = destRect.x;
/* 1228 */     int dst_min_y = destRect.y;
/* 1229 */     int dst_max_x = destRect.x + destRect.width;
/* 1230 */     int dst_max_y = destRect.y + destRect.height;
/* 1232 */     float[] backgroundFloat = new float[dst_num_bands];
/* 1233 */     for (int i = 0; i < dst_num_bands; i++)
/* 1234 */       backgroundFloat[i] = (float)this.backgroundValues[i]; 
/* 1236 */     for (int y = dst_min_y; y < dst_max_y; y++) {
/* 1238 */       int dstPixelOffset = dstOffset;
/* 1242 */       dst_pt.setLocation(dst_min_x + 0.5D, y + 0.5D);
/* 1244 */       mapDestPoint(dst_pt, src_pt);
/* 1247 */       float s_x = (float)src_pt.getX();
/* 1248 */       float s_y = (float)src_pt.getY();
/* 1251 */       s_x = (float)(s_x - 0.5D);
/* 1252 */       s_y = (float)(s_y - 0.5D);
/* 1255 */       int s_ix = (int)Math.floor(s_x);
/* 1256 */       int s_iy = (int)Math.floor(s_y);
/* 1258 */       float fracx = s_x - s_ix;
/* 1259 */       float fracy = s_y - s_iy;
/* 1262 */       int p_x = (s_ix - srcRectX) * srcPixelStride;
/* 1263 */       int p_y = (s_iy - srcRectY) * srcScanlineStride;
/* 1274 */       int p__ = p_x + p_y - srcScanlineStride - srcPixelStride;
/* 1275 */       int p0_ = p__ + srcPixelStride;
/* 1276 */       int p1_ = p0_ + srcPixelStride;
/* 1277 */       int p2_ = p1_ + srcPixelStride;
/* 1278 */       int p_0 = p__ + srcScanlineStride;
/* 1279 */       int p00 = p_0 + srcPixelStride;
/* 1280 */       int p10 = p00 + srcPixelStride;
/* 1281 */       int p20 = p10 + srcPixelStride;
/* 1282 */       int p_1 = p_0 + srcScanlineStride;
/* 1283 */       int p01 = p_1 + srcPixelStride;
/* 1284 */       int p11 = p01 + srcPixelStride;
/* 1285 */       int p21 = p11 + srcPixelStride;
/* 1286 */       int p_2 = p_1 + srcScanlineStride;
/* 1287 */       int p02 = p_2 + srcPixelStride;
/* 1288 */       int p12 = p02 + srcPixelStride;
/* 1289 */       int p22 = p12 + srcPixelStride;
/* 1291 */       for (int x = dst_min_x; x < dst_max_x; x++) {
/* 1295 */         if (s_ix >= src_rect_x1 + 1.0F && s_ix < src_rect_x2 - 2.0F && s_iy >= src_rect_y1 + 1.0F && s_iy < src_rect_y2 - 2.0F) {
/* 1299 */           for (int k2 = 0; k2 < dst_num_bands; k2++) {
/* 1306 */             float[] tmp_row = srcDataArrays[k2];
/* 1307 */             int tmp_col = bandOffsets[k2];
/* 1309 */             float s__ = tmp_row[p__ + tmp_col];
/* 1310 */             float s0_ = tmp_row[p0_ + tmp_col];
/* 1311 */             float s1_ = tmp_row[p1_ + tmp_col];
/* 1312 */             float s2_ = tmp_row[p2_ + tmp_col];
/* 1313 */             float s_0 = tmp_row[p_0 + tmp_col];
/* 1314 */             float s00 = tmp_row[p00 + tmp_col];
/* 1315 */             float s10 = tmp_row[p10 + tmp_col];
/* 1316 */             float s20 = tmp_row[p20 + tmp_col];
/* 1317 */             float s_1 = tmp_row[p_1 + tmp_col];
/* 1318 */             float s01 = tmp_row[p01 + tmp_col];
/* 1319 */             float s11 = tmp_row[p11 + tmp_col];
/* 1320 */             float s21 = tmp_row[p21 + tmp_col];
/* 1321 */             float s_2 = tmp_row[p_2 + tmp_col];
/* 1322 */             float s02 = tmp_row[p02 + tmp_col];
/* 1323 */             float s12 = tmp_row[p12 + tmp_col];
/* 1324 */             float s22 = tmp_row[p22 + tmp_col];
/* 1331 */             float s = this.interp.interpolate(s__, s0_, s1_, s2_, s_0, s00, s10, s20, s_1, s01, s11, s21, s_2, s02, s12, s22, fracx, fracy);
/* 1338 */             dstDataArrays[k2][dstPixelOffset + dstBandOffsets[k2]] = s;
/*      */           } 
/* 1341 */         } else if (this.setBackground) {
/* 1342 */           for (int k = 0; k < dst_num_bands; k++)
/* 1343 */             dstDataArrays[k][dstPixelOffset + dstBandOffsets[k]] = backgroundFloat[k]; 
/*      */         } 
/* 1348 */         if (fracx < this.fracdx1) {
/* 1349 */           s_ix += this.incx;
/* 1350 */           fracx = (float)(fracx + this.fracdx);
/* 1351 */           if (fracx == 1.0F)
/* 1353 */             fracx = 0.999999F; 
/*      */         } else {
/* 1356 */           s_ix += this.incx1;
/* 1357 */           fracx = (float)(fracx - this.fracdx1);
/*      */         } 
/* 1360 */         if (fracy < this.fracdy1) {
/* 1361 */           s_iy += this.incy;
/* 1362 */           fracy = (float)(fracy + this.fracdy);
/* 1363 */           if (fracy == 1.0F)
/* 1365 */             fracy = 0.999999F; 
/*      */         } else {
/* 1368 */           s_iy += this.incy1;
/* 1369 */           fracy = (float)(fracy - this.fracdy1);
/*      */         } 
/* 1373 */         p_x = (s_ix - srcRectX) * srcPixelStride;
/* 1374 */         p_y = (s_iy - srcRectY) * srcScanlineStride;
/* 1385 */         p__ = p_x + p_y - srcScanlineStride - srcPixelStride;
/* 1386 */         p0_ = p__ + srcPixelStride;
/* 1387 */         p1_ = p0_ + srcPixelStride;
/* 1388 */         p2_ = p1_ + srcPixelStride;
/* 1389 */         p_0 = p__ + srcScanlineStride;
/* 1390 */         p00 = p_0 + srcPixelStride;
/* 1391 */         p10 = p00 + srcPixelStride;
/* 1392 */         p20 = p10 + srcPixelStride;
/* 1393 */         p_1 = p_0 + srcScanlineStride;
/* 1394 */         p01 = p_1 + srcPixelStride;
/* 1395 */         p11 = p01 + srcPixelStride;
/* 1396 */         p21 = p11 + srcPixelStride;
/* 1397 */         p_2 = p_1 + srcScanlineStride;
/* 1398 */         p02 = p_2 + srcPixelStride;
/* 1399 */         p12 = p02 + srcPixelStride;
/* 1400 */         p22 = p12 + srcPixelStride;
/* 1402 */         dstPixelOffset += dstPixelStride;
/*      */       } 
/* 1405 */       dstOffset += dstScanlineStride;
/*      */     } 
/*      */   }
/*      */   
/*      */   private void doubleLoop(RasterAccessor src, Rectangle destRect, int srcRectX, int srcRectY, RasterAccessor dst) {
/* 1415 */     float src_rect_x1 = src.getX();
/* 1416 */     float src_rect_y1 = src.getY();
/* 1417 */     float src_rect_x2 = src_rect_x1 + src.getWidth();
/* 1418 */     float src_rect_y2 = src_rect_y1 + src.getHeight();
/* 1444 */     int dstOffset = 0;
/* 1446 */     Point2D dst_pt = new Point2D.Float();
/* 1447 */     Point2D src_pt = new Point2D.Float();
/* 1449 */     double[][] dstDataArrays = dst.getDoubleDataArrays();
/* 1450 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 1451 */     int dstPixelStride = dst.getPixelStride();
/* 1452 */     int dstScanlineStride = dst.getScanlineStride();
/* 1454 */     double[][] srcDataArrays = src.getDoubleDataArrays();
/* 1455 */     int[] bandOffsets = src.getBandOffsets();
/* 1456 */     int srcPixelStride = src.getPixelStride();
/* 1457 */     int srcScanlineStride = src.getScanlineStride();
/* 1459 */     int dst_num_bands = dst.getNumBands();
/* 1461 */     int dst_min_x = destRect.x;
/* 1462 */     int dst_min_y = destRect.y;
/* 1463 */     int dst_max_x = destRect.x + destRect.width;
/* 1464 */     int dst_max_y = destRect.y + destRect.height;
/* 1466 */     for (int y = dst_min_y; y < dst_max_y; y++) {
/* 1468 */       int dstPixelOffset = dstOffset;
/* 1473 */       dst_pt.setLocation(dst_min_x + 0.5D, y + 0.5D);
/* 1475 */       mapDestPoint(dst_pt, src_pt);
/* 1478 */       double s_x = src_pt.getX();
/* 1479 */       double s_y = src_pt.getY();
/* 1482 */       s_x -= 0.5D;
/* 1483 */       s_y -= 0.5D;
/* 1486 */       int s_ix = (int)Math.floor(s_x);
/* 1487 */       int s_iy = (int)Math.floor(s_y);
/* 1489 */       double fracx = s_x - s_ix;
/* 1490 */       double fracy = s_y - s_iy;
/* 1493 */       int p_x = (s_ix - srcRectX) * srcPixelStride;
/* 1494 */       int p_y = (s_iy - srcRectY) * srcScanlineStride;
/* 1505 */       int p__ = p_x + p_y - srcScanlineStride - srcPixelStride;
/* 1506 */       int p0_ = p__ + srcPixelStride;
/* 1507 */       int p1_ = p0_ + srcPixelStride;
/* 1508 */       int p2_ = p1_ + srcPixelStride;
/* 1509 */       int p_0 = p__ + srcScanlineStride;
/* 1510 */       int p00 = p_0 + srcPixelStride;
/* 1511 */       int p10 = p00 + srcPixelStride;
/* 1512 */       int p20 = p10 + srcPixelStride;
/* 1513 */       int p_1 = p_0 + srcScanlineStride;
/* 1514 */       int p01 = p_1 + srcPixelStride;
/* 1515 */       int p11 = p01 + srcPixelStride;
/* 1516 */       int p21 = p11 + srcPixelStride;
/* 1517 */       int p_2 = p_1 + srcScanlineStride;
/* 1518 */       int p02 = p_2 + srcPixelStride;
/* 1519 */       int p12 = p02 + srcPixelStride;
/* 1520 */       int p22 = p12 + srcPixelStride;
/* 1522 */       for (int x = dst_min_x; x < dst_max_x; x++) {
/* 1526 */         if (s_ix >= src_rect_x1 + 1.0F && s_ix < src_rect_x2 - 2.0F && s_iy >= src_rect_y1 + 1.0F && s_iy < src_rect_y2 - 2.0F) {
/* 1530 */           for (int k2 = 0; k2 < dst_num_bands; k2++) {
/* 1537 */             double[] tmp_row = srcDataArrays[k2];
/* 1538 */             int tmp_col = bandOffsets[k2];
/* 1540 */             double s__ = tmp_row[p__ + tmp_col];
/* 1541 */             double s0_ = tmp_row[p0_ + tmp_col];
/* 1542 */             double s1_ = tmp_row[p1_ + tmp_col];
/* 1543 */             double s2_ = tmp_row[p2_ + tmp_col];
/* 1544 */             double s_0 = tmp_row[p_0 + tmp_col];
/* 1545 */             double s00 = tmp_row[p00 + tmp_col];
/* 1546 */             double s10 = tmp_row[p10 + tmp_col];
/* 1547 */             double s20 = tmp_row[p20 + tmp_col];
/* 1548 */             double s_1 = tmp_row[p_1 + tmp_col];
/* 1549 */             double s01 = tmp_row[p01 + tmp_col];
/* 1550 */             double s11 = tmp_row[p11 + tmp_col];
/* 1551 */             double s21 = tmp_row[p21 + tmp_col];
/* 1552 */             double s_2 = tmp_row[p_2 + tmp_col];
/* 1553 */             double s02 = tmp_row[p02 + tmp_col];
/* 1554 */             double s12 = tmp_row[p12 + tmp_col];
/* 1555 */             double s22 = tmp_row[p22 + tmp_col];
/* 1562 */             double s = this.interp.interpolate(s__, s0_, s1_, s2_, s_0, s00, s10, s20, s_1, s01, s11, s21, s_2, s02, s12, s22, (float)fracx, (float)fracy);
/* 1569 */             dstDataArrays[k2][dstPixelOffset + dstBandOffsets[k2]] = s;
/*      */           } 
/* 1572 */         } else if (this.setBackground) {
/* 1573 */           for (int k = 0; k < dst_num_bands; k++)
/* 1574 */             dstDataArrays[k][dstPixelOffset + dstBandOffsets[k]] = this.backgroundValues[k]; 
/*      */         } 
/* 1579 */         if (fracx < this.fracdx1) {
/* 1580 */           s_ix += this.incx;
/* 1581 */           fracx += this.fracdx;
/* 1582 */           if (fracx == 1.0D)
/* 1584 */             fracx = 0.999999D; 
/*      */         } else {
/* 1587 */           s_ix += this.incx1;
/* 1588 */           fracx -= this.fracdx1;
/*      */         } 
/* 1591 */         if (fracy < this.fracdy1) {
/* 1592 */           s_iy += this.incy;
/* 1593 */           fracy += this.fracdy;
/* 1594 */           if (fracy == 1.0D)
/* 1596 */             fracy = 0.999999D; 
/*      */         } else {
/* 1599 */           s_iy += this.incy1;
/* 1600 */           fracy -= this.fracdy1;
/*      */         } 
/* 1604 */         p_x = (s_ix - srcRectX) * srcPixelStride;
/* 1605 */         p_y = (s_iy - srcRectY) * srcScanlineStride;
/* 1616 */         p__ = p_x + p_y - srcScanlineStride - srcPixelStride;
/* 1617 */         p0_ = p__ + srcPixelStride;
/* 1618 */         p1_ = p0_ + srcPixelStride;
/* 1619 */         p2_ = p1_ + srcPixelStride;
/* 1620 */         p_0 = p__ + srcScanlineStride;
/* 1621 */         p00 = p_0 + srcPixelStride;
/* 1622 */         p10 = p00 + srcPixelStride;
/* 1623 */         p20 = p10 + srcPixelStride;
/* 1624 */         p_1 = p_0 + srcScanlineStride;
/* 1625 */         p01 = p_1 + srcPixelStride;
/* 1626 */         p11 = p01 + srcPixelStride;
/* 1627 */         p21 = p11 + srcPixelStride;
/* 1628 */         p_2 = p_1 + srcScanlineStride;
/* 1629 */         p02 = p_2 + srcPixelStride;
/* 1630 */         p12 = p02 + srcPixelStride;
/* 1631 */         p22 = p12 + srcPixelStride;
/* 1633 */         dstPixelOffset += dstPixelStride;
/*      */       } 
/* 1636 */       dstOffset += dstScanlineStride;
/*      */     } 
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\AffineBicubic2OpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */