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
/*      */ final class AffineGeneralOpImage extends AffineOpImage {
/*      */   private int subsampleBits;
/*      */   
/*      */   private int shiftvalue;
/*      */   
/*      */   private int interp_width;
/*      */   
/*      */   private int interp_height;
/*      */   
/*      */   private int interp_left;
/*      */   
/*      */   private int interp_top;
/*      */   
/*      */   private int interp_right;
/*      */   
/*      */   private int interp_bottom;
/*      */   
/*      */   public AffineGeneralOpImage(RenderedImage source, BorderExtender extender, Map config, ImageLayout layout, AffineTransform transform, Interpolation interp, double[] backgroundValues) {
/*   59 */     super(source, extender, config, layout, transform, interp, backgroundValues);
/*   67 */     this.subsampleBits = interp.getSubsampleBitsH();
/*   68 */     this.shiftvalue = 1 << this.subsampleBits;
/*   70 */     this.interp_width = interp.getWidth();
/*   71 */     this.interp_height = interp.getHeight();
/*   72 */     this.interp_left = interp.getLeftPadding();
/*   73 */     this.interp_top = interp.getTopPadding();
/*   74 */     this.interp_right = this.interp_width - this.interp_left - 1;
/*   75 */     this.interp_bottom = this.interp_height - this.interp_top - 1;
/*      */   }
/*      */   
/*      */   protected void computeRect(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/*   91 */     RasterFormatTag[] formatTags = getFormatTags();
/*   93 */     Raster source = sources[0];
/*   95 */     Rectangle srcRect = source.getBounds();
/*   97 */     int srcRectX = srcRect.x;
/*   98 */     int srcRectY = srcRect.y;
/*  107 */     RasterAccessor srcAccessor = new RasterAccessor(source, srcRect, formatTags[0], getSourceImage(0).getColorModel());
/*  112 */     RasterAccessor dstAccessor = new RasterAccessor(dest, destRect, formatTags[1], getColorModel());
/*  118 */     switch (dstAccessor.getDataType()) {
/*      */       case 0:
/*  120 */         byteLoop(srcAccessor, destRect, srcRectX, srcRectY, dstAccessor);
/*      */         break;
/*      */       case 3:
/*  128 */         intLoop(srcAccessor, destRect, srcRectX, srcRectY, dstAccessor);
/*      */         break;
/*      */       case 2:
/*  136 */         shortLoop(srcAccessor, destRect, srcRectX, srcRectY, dstAccessor);
/*      */         break;
/*      */       case 1:
/*  144 */         ushortLoop(srcAccessor, destRect, srcRectX, srcRectY, dstAccessor);
/*      */         break;
/*      */       case 4:
/*  152 */         floatLoop(srcAccessor, destRect, srcRectX, srcRectY, dstAccessor);
/*      */         break;
/*      */       case 5:
/*  160 */         doubleLoop(srcAccessor, destRect, srcRectX, srcRectY, dstAccessor);
/*      */         break;
/*      */     } 
/*  171 */     if (dstAccessor.isDataCopy()) {
/*  172 */       dstAccessor.clampDataArrays();
/*  173 */       dstAccessor.copyDataToRaster();
/*      */     } 
/*      */   }
/*      */   
/*      */   private void byteLoop(RasterAccessor src, Rectangle destRect, int srcRectX, int srcRectY, RasterAccessor dst) {
/*  183 */     float src_rect_x1 = src.getX();
/*  184 */     float src_rect_y1 = src.getY();
/*  185 */     float src_rect_x2 = src_rect_x1 + src.getWidth();
/*  186 */     float src_rect_y2 = src_rect_y1 + src.getHeight();
/*  198 */     int[][] samples = new int[this.interp_height][this.interp_width];
/*  202 */     int dstOffset = 0;
/*  204 */     Point2D dst_pt = new Point2D.Float();
/*  205 */     Point2D src_pt = new Point2D.Float();
/*  207 */     byte[][] dstDataArrays = dst.getByteDataArrays();
/*  208 */     int[] dstBandOffsets = dst.getBandOffsets();
/*  209 */     int dstPixelStride = dst.getPixelStride();
/*  210 */     int dstScanlineStride = dst.getScanlineStride();
/*  212 */     byte[][] srcDataArrays = src.getByteDataArrays();
/*  213 */     int[] bandOffsets = src.getBandOffsets();
/*  214 */     int srcPixelStride = src.getPixelStride();
/*  215 */     int srcScanlineStride = src.getScanlineStride();
/*  217 */     int dst_num_bands = dst.getNumBands();
/*  219 */     int dst_min_x = destRect.x;
/*  220 */     int dst_min_y = destRect.y;
/*  221 */     int dst_max_x = destRect.x + destRect.width;
/*  222 */     int dst_max_y = destRect.y + destRect.height;
/*  224 */     byte[] backgroundByte = new byte[dst_num_bands];
/*  225 */     for (int i = 0; i < dst_num_bands; i++)
/*  226 */       backgroundByte[i] = (byte)(int)this.backgroundValues[i]; 
/*  228 */     for (int y = dst_min_y; y < dst_max_y; y++) {
/*  229 */       int dstPixelOffset = dstOffset;
/*  233 */       dst_pt.setLocation(dst_min_x + 0.5D, y + 0.5D);
/*  235 */       mapDestPoint(dst_pt, src_pt);
/*  238 */       float s_x = (float)src_pt.getX();
/*  239 */       float s_y = (float)src_pt.getY();
/*  241 */       s_x = (float)(s_x - 0.5D);
/*  242 */       s_y = (float)(s_y - 0.5D);
/*  245 */       int s_ix = (int)Math.floor(s_x);
/*  246 */       int s_iy = (int)Math.floor(s_y);
/*  248 */       float fracx = s_x - s_ix;
/*  249 */       float fracy = s_y - s_iy;
/*  252 */       int p_x = (s_ix - srcRectX) * srcPixelStride;
/*  253 */       int p_y = (s_iy - srcRectY) * srcScanlineStride;
/*  255 */       for (int x = dst_min_x; x < dst_max_x; x++) {
/*  260 */         if (s_ix >= src_rect_x1 + this.interp_left && s_ix < src_rect_x2 - this.interp_right && s_iy >= src_rect_y1 + this.interp_top && s_iy < src_rect_y2 - this.interp_bottom) {
/*  264 */           for (int k = 0; k < dst_num_bands; k++) {
/*      */             int result;
/*  265 */             byte[] srcData = srcDataArrays[k];
/*  266 */             int tmp = bandOffsets[k];
/*  269 */             int start = this.interp_left * srcPixelStride + this.interp_top * srcScanlineStride;
/*  271 */             start = p_x + p_y - start;
/*  272 */             int countH = 0, countV = 0;
/*  274 */             for (int j = 0; j < this.interp_height; j++) {
/*  275 */               int startY = start;
/*  276 */               for (int m = 0; m < this.interp_width; m++) {
/*  277 */                 samples[countV][countH++] = srcData[start + tmp] & 0xFF;
/*  279 */                 start += srcPixelStride;
/*      */               } 
/*  281 */               countV++;
/*  282 */               countH = 0;
/*  283 */               start = startY + srcScanlineStride;
/*      */             } 
/*  287 */             int xfrac = (int)(fracx * this.shiftvalue);
/*  288 */             int yfrac = (int)(fracy * this.shiftvalue);
/*  291 */             int s = this.interp.interpolate(samples, xfrac, yfrac);
/*  294 */             if (s < 0) {
/*  295 */               result = 0;
/*  296 */             } else if (s > 255) {
/*  297 */               result = 255;
/*      */             } else {
/*  299 */               result = s;
/*      */             } 
/*  303 */             dstDataArrays[k][dstPixelOffset + dstBandOffsets[k]] = (byte)(result & 0xFF);
/*      */           } 
/*  307 */         } else if (this.setBackground) {
/*  308 */           for (int k = 0; k < dst_num_bands; k++)
/*  309 */             dstDataArrays[k][dstPixelOffset + dstBandOffsets[k]] = backgroundByte[k]; 
/*      */         } 
/*  314 */         if (fracx < this.fracdx1) {
/*  315 */           s_ix += this.incx;
/*  316 */           fracx = (float)(fracx + this.fracdx);
/*      */         } else {
/*  318 */           s_ix += this.incx1;
/*  319 */           fracx = (float)(fracx - this.fracdx1);
/*      */         } 
/*  322 */         if (fracy < this.fracdy1) {
/*  323 */           s_iy += this.incy;
/*  324 */           fracy = (float)(fracy + this.fracdy);
/*      */         } else {
/*  326 */           s_iy += this.incy1;
/*  327 */           fracy = (float)(fracy - this.fracdy1);
/*      */         } 
/*  331 */         p_x = (s_ix - srcRectX) * srcPixelStride;
/*  332 */         p_y = (s_iy - srcRectY) * srcScanlineStride;
/*  334 */         dstPixelOffset += dstPixelStride;
/*      */       } 
/*  337 */       dstOffset += dstScanlineStride;
/*      */     } 
/*      */   }
/*      */   
/*      */   private void intLoop(RasterAccessor src, Rectangle destRect, int srcRectX, int srcRectY, RasterAccessor dst) {
/*  348 */     float src_rect_x1 = src.getX();
/*  349 */     float src_rect_y1 = src.getY();
/*  350 */     float src_rect_x2 = src_rect_x1 + src.getWidth();
/*  351 */     float src_rect_y2 = src_rect_y1 + src.getHeight();
/*  364 */     int dstOffset = 0;
/*  366 */     int[][] samples = new int[this.interp_height][this.interp_width];
/*  369 */     Point2D dst_pt = new Point2D.Float();
/*  370 */     Point2D src_pt = new Point2D.Float();
/*  372 */     int[][] dstDataArrays = dst.getIntDataArrays();
/*  373 */     int[] dstBandOffsets = dst.getBandOffsets();
/*  374 */     int dstPixelStride = dst.getPixelStride();
/*  375 */     int dstScanlineStride = dst.getScanlineStride();
/*  377 */     int[][] srcDataArrays = src.getIntDataArrays();
/*  378 */     int[] bandOffsets = src.getBandOffsets();
/*  379 */     int srcPixelStride = src.getPixelStride();
/*  380 */     int srcScanlineStride = src.getScanlineStride();
/*  382 */     int dst_num_bands = dst.getNumBands();
/*  384 */     int dst_min_x = destRect.x;
/*  385 */     int dst_min_y = destRect.y;
/*  386 */     int dst_max_x = destRect.x + destRect.width;
/*  387 */     int dst_max_y = destRect.y + destRect.height;
/*  389 */     int[] backgroundInt = new int[dst_num_bands];
/*  390 */     for (int i = 0; i < dst_num_bands; i++)
/*  391 */       backgroundInt[i] = (int)this.backgroundValues[i]; 
/*  393 */     for (int y = dst_min_y; y < dst_max_y; y++) {
/*  394 */       int dstPixelOffset = dstOffset;
/*  398 */       dst_pt.setLocation(dst_min_x + 0.5D, y + 0.5D);
/*  400 */       mapDestPoint(dst_pt, src_pt);
/*  403 */       float s_x = (float)src_pt.getX();
/*  404 */       float s_y = (float)src_pt.getY();
/*  407 */       s_x = (float)(s_x - 0.5D);
/*  408 */       s_y = (float)(s_y - 0.5D);
/*  411 */       int s_ix = (int)Math.floor(s_x);
/*  412 */       int s_iy = (int)Math.floor(s_y);
/*  414 */       float fracx = s_x - s_ix;
/*  415 */       float fracy = s_y - s_iy;
/*  418 */       int p_x = (s_ix - srcRectX) * srcPixelStride;
/*  419 */       int p_y = (s_iy - srcRectY) * srcScanlineStride;
/*  421 */       for (int x = dst_min_x; x < dst_max_x; x++) {
/*  425 */         if (s_ix >= src_rect_x1 + this.interp_left && s_ix < src_rect_x2 - this.interp_right && s_iy >= src_rect_y1 + this.interp_top && s_iy < src_rect_y2 - this.interp_bottom) {
/*  429 */           for (int k = 0; k < dst_num_bands; k++) {
/*  430 */             int result, srcData[] = srcDataArrays[k];
/*  431 */             int tmp = bandOffsets[k];
/*  434 */             int start = this.interp_left * srcPixelStride + this.interp_top * srcScanlineStride;
/*  436 */             start = p_x + p_y - start;
/*  437 */             int countH = 0, countV = 0;
/*  439 */             for (int j = 0; j < this.interp_height; j++) {
/*  440 */               int startY = start;
/*  441 */               for (int m = 0; m < this.interp_width; m++) {
/*  442 */                 samples[countV][countH++] = srcData[start + tmp];
/*  444 */                 start += srcPixelStride;
/*      */               } 
/*  446 */               countV++;
/*  447 */               countH = 0;
/*  448 */               start = startY + srcScanlineStride;
/*      */             } 
/*  452 */             int xfrac = (int)(fracx * this.shiftvalue);
/*  453 */             int yfrac = (int)(fracy * this.shiftvalue);
/*  456 */             int s = this.interp.interpolate(samples, xfrac, yfrac);
/*  459 */             if (s < Integer.MIN_VALUE) {
/*  460 */               result = Integer.MIN_VALUE;
/*  461 */             } else if (s > Integer.MAX_VALUE) {
/*  462 */               result = Integer.MAX_VALUE;
/*      */             } else {
/*  464 */               result = s;
/*      */             } 
/*  468 */             dstDataArrays[k][dstPixelOffset + dstBandOffsets[k]] = result;
/*      */           } 
/*  471 */         } else if (this.setBackground) {
/*  472 */           for (int k = 0; k < dst_num_bands; k++)
/*  473 */             dstDataArrays[k][dstPixelOffset + dstBandOffsets[k]] = backgroundInt[k]; 
/*      */         } 
/*  478 */         if (fracx < this.fracdx1) {
/*  479 */           s_ix += this.incx;
/*  480 */           fracx = (float)(fracx + this.fracdx);
/*      */         } else {
/*  482 */           s_ix += this.incx1;
/*  483 */           fracx = (float)(fracx - this.fracdx1);
/*      */         } 
/*  486 */         if (fracy < this.fracdy1) {
/*  487 */           s_iy += this.incy;
/*  488 */           fracy = (float)(fracy + this.fracdy);
/*      */         } else {
/*  490 */           s_iy += this.incy1;
/*  491 */           fracy = (float)(fracy - this.fracdy1);
/*      */         } 
/*  495 */         p_x = (s_ix - srcRectX) * srcPixelStride;
/*  496 */         p_y = (s_iy - srcRectY) * srcScanlineStride;
/*  498 */         dstPixelOffset += dstPixelStride;
/*      */       } 
/*  501 */       dstOffset += dstScanlineStride;
/*      */     } 
/*      */   }
/*      */   
/*      */   private void shortLoop(RasterAccessor src, Rectangle destRect, int srcRectX, int srcRectY, RasterAccessor dst) {
/*  511 */     float src_rect_x1 = src.getX();
/*  512 */     float src_rect_y1 = src.getY();
/*  513 */     float src_rect_x2 = src_rect_x1 + src.getWidth();
/*  514 */     float src_rect_y2 = src_rect_y1 + src.getHeight();
/*  525 */     int[][] samples = new int[this.interp_height][this.interp_width];
/*  531 */     int dstOffset = 0;
/*  533 */     Point2D dst_pt = new Point2D.Float();
/*  534 */     Point2D src_pt = new Point2D.Float();
/*  536 */     short[][] dstDataArrays = dst.getShortDataArrays();
/*  537 */     int[] dstBandOffsets = dst.getBandOffsets();
/*  538 */     int dstPixelStride = dst.getPixelStride();
/*  539 */     int dstScanlineStride = dst.getScanlineStride();
/*  541 */     short[][] srcDataArrays = src.getShortDataArrays();
/*  542 */     int[] bandOffsets = src.getBandOffsets();
/*  543 */     int srcPixelStride = src.getPixelStride();
/*  544 */     int srcScanlineStride = src.getScanlineStride();
/*  546 */     int dst_num_bands = dst.getNumBands();
/*  548 */     int dst_min_x = destRect.x;
/*  549 */     int dst_min_y = destRect.y;
/*  550 */     int dst_max_x = destRect.x + destRect.width;
/*  551 */     int dst_max_y = destRect.y + destRect.height;
/*  553 */     short[] backgroundShort = new short[dst_num_bands];
/*  554 */     for (int i = 0; i < dst_num_bands; i++)
/*  555 */       backgroundShort[i] = (short)(int)this.backgroundValues[i]; 
/*  557 */     for (int y = dst_min_y; y < dst_max_y; y++) {
/*  559 */       int dstPixelOffset = dstOffset;
/*  563 */       dst_pt.setLocation(dst_min_x + 0.5D, y + 0.5D);
/*  565 */       mapDestPoint(dst_pt, src_pt);
/*  568 */       float s_x = (float)src_pt.getX();
/*  569 */       float s_y = (float)src_pt.getY();
/*  572 */       s_x = (float)(s_x - 0.5D);
/*  573 */       s_y = (float)(s_y - 0.5D);
/*  576 */       int s_ix = (int)Math.floor(s_x);
/*  577 */       int s_iy = (int)Math.floor(s_y);
/*  579 */       float fracx = s_x - s_ix;
/*  580 */       float fracy = s_y - s_iy;
/*  583 */       int p_x = (s_ix - srcRectX) * srcPixelStride;
/*  584 */       int p_y = (s_iy - srcRectY) * srcScanlineStride;
/*  586 */       for (int x = dst_min_x; x < dst_max_x; x++) {
/*  590 */         if (s_ix >= src_rect_x1 + this.interp_left && s_ix < src_rect_x2 - this.interp_right && s_iy >= src_rect_y1 + this.interp_top && s_iy < src_rect_y2 - this.interp_bottom) {
/*  594 */           for (int k = 0; k < dst_num_bands; k++) {
/*  595 */             short result, srcData[] = srcDataArrays[k];
/*  596 */             int tmp = bandOffsets[k];
/*  599 */             int start = this.interp_left * srcPixelStride + this.interp_top * srcScanlineStride;
/*  601 */             start = p_x + p_y - start;
/*  602 */             int countH = 0, countV = 0;
/*  604 */             for (int j = 0; j < this.interp_height; j++) {
/*  605 */               int startY = start;
/*  606 */               for (int m = 0; m < this.interp_width; m++) {
/*  607 */                 samples[countV][countH++] = srcData[start + tmp];
/*  609 */                 start += srcPixelStride;
/*      */               } 
/*  611 */               countV++;
/*  612 */               countH = 0;
/*  613 */               start = startY + srcScanlineStride;
/*      */             } 
/*  617 */             int xfrac = (int)(fracx * this.shiftvalue);
/*  618 */             int yfrac = (int)(fracy * this.shiftvalue);
/*  621 */             int s = this.interp.interpolate(samples, xfrac, yfrac);
/*  624 */             if (s < -32768) {
/*  625 */               result = Short.MIN_VALUE;
/*  626 */             } else if (s > 32767) {
/*  627 */               result = Short.MAX_VALUE;
/*      */             } else {
/*  629 */               result = (short)s;
/*      */             } 
/*  633 */             dstDataArrays[k][dstPixelOffset + dstBandOffsets[k]] = result;
/*      */           } 
/*  636 */         } else if (this.setBackground) {
/*  637 */           for (int k = 0; k < dst_num_bands; k++)
/*  638 */             dstDataArrays[k][dstPixelOffset + dstBandOffsets[k]] = backgroundShort[k]; 
/*      */         } 
/*  643 */         if (fracx < this.fracdx1) {
/*  644 */           s_ix += this.incx;
/*  645 */           fracx = (float)(fracx + this.fracdx);
/*      */         } else {
/*  647 */           s_ix += this.incx1;
/*  648 */           fracx = (float)(fracx - this.fracdx1);
/*      */         } 
/*  651 */         if (fracy < this.fracdy1) {
/*  652 */           s_iy += this.incy;
/*  653 */           fracy = (float)(fracy + this.fracdy);
/*      */         } else {
/*  655 */           s_iy += this.incy1;
/*  656 */           fracy = (float)(fracy - this.fracdy1);
/*      */         } 
/*  660 */         p_x = (s_ix - srcRectX) * srcPixelStride;
/*  661 */         p_y = (s_iy - srcRectY) * srcScanlineStride;
/*  663 */         dstPixelOffset += dstPixelStride;
/*      */       } 
/*  666 */       dstOffset += dstScanlineStride;
/*      */     } 
/*      */   }
/*      */   
/*      */   private void ushortLoop(RasterAccessor src, Rectangle destRect, int srcRectX, int srcRectY, RasterAccessor dst) {
/*  676 */     float src_rect_x1 = src.getX();
/*  677 */     float src_rect_y1 = src.getY();
/*  678 */     float src_rect_x2 = src_rect_x1 + src.getWidth();
/*  679 */     float src_rect_y2 = src_rect_y1 + src.getHeight();
/*  690 */     int[][] samples = new int[this.interp_height][this.interp_width];
/*  696 */     int dstOffset = 0;
/*  698 */     Point2D dst_pt = new Point2D.Float();
/*  699 */     Point2D src_pt = new Point2D.Float();
/*  701 */     short[][] dstDataArrays = dst.getShortDataArrays();
/*  702 */     int[] dstBandOffsets = dst.getBandOffsets();
/*  703 */     int dstPixelStride = dst.getPixelStride();
/*  704 */     int dstScanlineStride = dst.getScanlineStride();
/*  706 */     short[][] srcDataArrays = src.getShortDataArrays();
/*  707 */     int[] bandOffsets = src.getBandOffsets();
/*  708 */     int srcPixelStride = src.getPixelStride();
/*  709 */     int srcScanlineStride = src.getScanlineStride();
/*  711 */     int dst_num_bands = dst.getNumBands();
/*  713 */     int dst_min_x = destRect.x;
/*  714 */     int dst_min_y = destRect.y;
/*  715 */     int dst_max_x = destRect.x + destRect.width;
/*  716 */     int dst_max_y = destRect.y + destRect.height;
/*  718 */     short[] backgroundUShort = new short[dst_num_bands];
/*  719 */     for (int i = 0; i < dst_num_bands; i++)
/*  720 */       backgroundUShort[i] = (short)(int)this.backgroundValues[i]; 
/*  722 */     for (int y = dst_min_y; y < dst_max_y; y++) {
/*  723 */       int dstPixelOffset = dstOffset;
/*  727 */       dst_pt.setLocation(dst_min_x + 0.5D, y + 0.5D);
/*  729 */       mapDestPoint(dst_pt, src_pt);
/*  732 */       float s_x = (float)src_pt.getX();
/*  733 */       float s_y = (float)src_pt.getY();
/*  736 */       s_x = (float)(s_x - 0.5D);
/*  737 */       s_y = (float)(s_y - 0.5D);
/*  740 */       int s_ix = (int)Math.floor(s_x);
/*  741 */       int s_iy = (int)Math.floor(s_y);
/*  743 */       float fracx = s_x - s_ix;
/*  744 */       float fracy = s_y - s_iy;
/*  747 */       int p_x = (s_ix - srcRectX) * srcPixelStride;
/*  748 */       int p_y = (s_iy - srcRectY) * srcScanlineStride;
/*  750 */       for (int x = dst_min_x; x < dst_max_x; x++) {
/*  754 */         if (s_ix >= src_rect_x1 + this.interp_left && s_ix < src_rect_x2 - this.interp_right && s_iy >= src_rect_y1 + this.interp_top && s_iy < src_rect_y2 - this.interp_bottom) {
/*  758 */           for (int k = 0; k < dst_num_bands; k++) {
/*      */             int result;
/*  759 */             short[] srcData = srcDataArrays[k];
/*  760 */             int tmp = bandOffsets[k];
/*  763 */             int start = this.interp_left * srcPixelStride + this.interp_top * srcScanlineStride;
/*  765 */             start = p_x + p_y - start;
/*  766 */             int countH = 0, countV = 0;
/*  768 */             for (int j = 0; j < this.interp_height; j++) {
/*  769 */               int startY = start;
/*  770 */               for (int m = 0; m < this.interp_width; m++) {
/*  771 */                 samples[countV][countH++] = srcData[start + tmp] & 0xFFFF;
/*  773 */                 start += srcPixelStride;
/*      */               } 
/*  775 */               countV++;
/*  776 */               countH = 0;
/*  777 */               start = startY + srcScanlineStride;
/*      */             } 
/*  781 */             int xfrac = (int)(fracx * this.shiftvalue);
/*  782 */             int yfrac = (int)(fracy * this.shiftvalue);
/*  785 */             int s = this.interp.interpolate(samples, xfrac, yfrac);
/*  788 */             if (s < 0) {
/*  789 */               result = 0;
/*  790 */             } else if (s > 65535) {
/*  791 */               result = 65535;
/*      */             } else {
/*  793 */               result = s;
/*      */             } 
/*  797 */             dstDataArrays[k][dstPixelOffset + dstBandOffsets[k]] = (short)(result & 0xFFFF);
/*      */           } 
/*  801 */         } else if (this.setBackground) {
/*  802 */           for (int k = 0; k < dst_num_bands; k++)
/*  803 */             dstDataArrays[k][dstPixelOffset + dstBandOffsets[k]] = backgroundUShort[k]; 
/*      */         } 
/*  808 */         if (fracx < this.fracdx1) {
/*  809 */           s_ix += this.incx;
/*  810 */           fracx = (float)(fracx + this.fracdx);
/*      */         } else {
/*  812 */           s_ix += this.incx1;
/*  813 */           fracx = (float)(fracx - this.fracdx1);
/*      */         } 
/*  816 */         if (fracy < this.fracdy1) {
/*  817 */           s_iy += this.incy;
/*  818 */           fracy = (float)(fracy + this.fracdy);
/*      */         } else {
/*  820 */           s_iy += this.incy1;
/*  821 */           fracy = (float)(fracy - this.fracdy1);
/*      */         } 
/*  825 */         p_x = (s_ix - srcRectX) * srcPixelStride;
/*  826 */         p_y = (s_iy - srcRectY) * srcScanlineStride;
/*  828 */         dstPixelOffset += dstPixelStride;
/*      */       } 
/*  831 */       dstOffset += dstScanlineStride;
/*      */     } 
/*      */   }
/*      */   
/*      */   private void floatLoop(RasterAccessor src, Rectangle destRect, int srcRectX, int srcRectY, RasterAccessor dst) {
/*  841 */     float src_rect_x1 = src.getX();
/*  842 */     float src_rect_y1 = src.getY();
/*  843 */     float src_rect_x2 = src_rect_x1 + src.getWidth();
/*  844 */     float src_rect_y2 = src_rect_y1 + src.getHeight();
/*  855 */     float[][] samples = new float[this.interp_height][this.interp_width];
/*  859 */     int dstOffset = 0;
/*  861 */     Point2D dst_pt = new Point2D.Float();
/*  862 */     Point2D src_pt = new Point2D.Float();
/*  864 */     float[][] dstDataArrays = dst.getFloatDataArrays();
/*  865 */     int[] dstBandOffsets = dst.getBandOffsets();
/*  866 */     int dstPixelStride = dst.getPixelStride();
/*  867 */     int dstScanlineStride = dst.getScanlineStride();
/*  869 */     float[][] srcDataArrays = src.getFloatDataArrays();
/*  870 */     int[] bandOffsets = src.getBandOffsets();
/*  871 */     int srcPixelStride = src.getPixelStride();
/*  872 */     int srcScanlineStride = src.getScanlineStride();
/*  874 */     int dst_num_bands = dst.getNumBands();
/*  876 */     int dst_min_x = destRect.x;
/*  877 */     int dst_min_y = destRect.y;
/*  878 */     int dst_max_x = destRect.x + destRect.width;
/*  879 */     int dst_max_y = destRect.y + destRect.height;
/*  881 */     float[] backgroundFloat = new float[dst_num_bands];
/*  882 */     for (int i = 0; i < dst_num_bands; i++)
/*  883 */       backgroundFloat[i] = (float)this.backgroundValues[i]; 
/*  885 */     for (int y = dst_min_y; y < dst_max_y; y++) {
/*  887 */       int dstPixelOffset = dstOffset;
/*  891 */       dst_pt.setLocation(dst_min_x + 0.5D, y + 0.5D);
/*  893 */       mapDestPoint(dst_pt, src_pt);
/*  896 */       float s_x = (float)src_pt.getX();
/*  897 */       float s_y = (float)src_pt.getY();
/*  900 */       s_x = (float)(s_x - 0.5D);
/*  901 */       s_y = (float)(s_y - 0.5D);
/*  904 */       int s_ix = (int)Math.floor(s_x);
/*  905 */       int s_iy = (int)Math.floor(s_y);
/*  907 */       float fracx = s_x - s_ix;
/*  908 */       float fracy = s_y - s_iy;
/*  911 */       int p_x = (s_ix - srcRectX) * srcPixelStride;
/*  912 */       int p_y = (s_iy - srcRectY) * srcScanlineStride;
/*  914 */       for (int x = dst_min_x; x < dst_max_x; x++) {
/*  918 */         if (s_ix >= src_rect_x1 + this.interp_left && s_ix < src_rect_x2 - this.interp_right && s_iy >= src_rect_y1 + this.interp_top && s_iy < src_rect_y2 - this.interp_bottom) {
/*  922 */           for (int k = 0; k < dst_num_bands; k++) {
/*  923 */             float[] srcData = srcDataArrays[k];
/*  924 */             int tmp = bandOffsets[k];
/*  927 */             int start = this.interp_left * srcPixelStride + this.interp_top * srcScanlineStride;
/*  929 */             start = p_x + p_y - start;
/*  930 */             int countH = 0, countV = 0;
/*  932 */             for (int j = 0; j < this.interp_height; j++) {
/*  933 */               int startY = start;
/*  934 */               for (int m = 0; m < this.interp_width; m++) {
/*  935 */                 samples[countV][countH++] = srcData[start + tmp];
/*  937 */                 start += srcPixelStride;
/*      */               } 
/*  939 */               countV++;
/*  940 */               countH = 0;
/*  941 */               start = startY + srcScanlineStride;
/*      */             } 
/*  945 */             float s = this.interp.interpolate(samples, fracx, fracy);
/*  948 */             dstDataArrays[k][dstPixelOffset + dstBandOffsets[k]] = s;
/*      */           } 
/*  951 */         } else if (this.setBackground) {
/*  952 */           for (int k = 0; k < dst_num_bands; k++)
/*  953 */             dstDataArrays[k][dstPixelOffset + dstBandOffsets[k]] = backgroundFloat[k]; 
/*      */         } 
/*  958 */         if (fracx < this.fracdx1) {
/*  959 */           s_ix += this.incx;
/*  960 */           fracx = (float)(fracx + this.fracdx);
/*      */         } else {
/*  962 */           s_ix += this.incx1;
/*  963 */           fracx = (float)(fracx - this.fracdx1);
/*      */         } 
/*  966 */         if (fracy < this.fracdy1) {
/*  967 */           s_iy += this.incy;
/*  968 */           fracy = (float)(fracy + this.fracdy);
/*      */         } else {
/*  970 */           s_iy += this.incy1;
/*  971 */           fracy = (float)(fracy - this.fracdy1);
/*      */         } 
/*  975 */         p_x = (s_ix - srcRectX) * srcPixelStride;
/*  976 */         p_y = (s_iy - srcRectY) * srcScanlineStride;
/*  978 */         dstPixelOffset += dstPixelStride;
/*      */       } 
/*  981 */       dstOffset += dstScanlineStride;
/*      */     } 
/*      */   }
/*      */   
/*      */   private void doubleLoop(RasterAccessor src, Rectangle destRect, int srcRectX, int srcRectY, RasterAccessor dst) {
/*  991 */     float src_rect_x1 = src.getX();
/*  992 */     float src_rect_y1 = src.getY();
/*  993 */     float src_rect_x2 = src_rect_x1 + src.getWidth();
/*  994 */     float src_rect_y2 = src_rect_y1 + src.getHeight();
/* 1005 */     double[][] samples = new double[this.interp_height][this.interp_width];
/* 1009 */     int dstOffset = 0;
/* 1011 */     Point2D dst_pt = new Point2D.Float();
/* 1012 */     Point2D src_pt = new Point2D.Float();
/* 1014 */     double[][] dstDataArrays = dst.getDoubleDataArrays();
/* 1015 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 1016 */     int dstPixelStride = dst.getPixelStride();
/* 1017 */     int dstScanlineStride = dst.getScanlineStride();
/* 1019 */     double[][] srcDataArrays = src.getDoubleDataArrays();
/* 1020 */     int[] bandOffsets = src.getBandOffsets();
/* 1021 */     int srcPixelStride = src.getPixelStride();
/* 1022 */     int srcScanlineStride = src.getScanlineStride();
/* 1024 */     int dst_num_bands = dst.getNumBands();
/* 1026 */     int dst_min_x = destRect.x;
/* 1027 */     int dst_min_y = destRect.y;
/* 1028 */     int dst_max_x = destRect.x + destRect.width;
/* 1029 */     int dst_max_y = destRect.y + destRect.height;
/* 1031 */     for (int y = dst_min_y; y < dst_max_y; y++) {
/* 1033 */       int dstPixelOffset = dstOffset;
/* 1038 */       dst_pt.setLocation(dst_min_x + 0.5D, y + 0.5D);
/* 1040 */       mapDestPoint(dst_pt, src_pt);
/* 1043 */       double s_x = src_pt.getX();
/* 1044 */       double s_y = src_pt.getY();
/* 1047 */       s_x -= 0.5D;
/* 1048 */       s_y -= 0.5D;
/* 1051 */       int s_ix = (int)Math.floor(s_x);
/* 1052 */       int s_iy = (int)Math.floor(s_y);
/* 1054 */       double fracx = s_x - s_ix;
/* 1055 */       double fracy = s_y - s_iy;
/* 1058 */       int p_x = (s_ix - srcRectX) * srcPixelStride;
/* 1059 */       int p_y = (s_iy - srcRectY) * srcScanlineStride;
/* 1061 */       for (int x = dst_min_x; x < dst_max_x; x++) {
/* 1065 */         if (s_ix >= src_rect_x1 + this.interp_left && s_ix < src_rect_x2 - this.interp_right && s_iy >= src_rect_y1 + this.interp_top && s_iy < src_rect_y2 - this.interp_bottom) {
/* 1069 */           for (int k = 0; k < dst_num_bands; k++) {
/* 1070 */             double[] srcData = srcDataArrays[k];
/* 1071 */             int tmp = bandOffsets[k];
/* 1074 */             int start = this.interp_left * srcPixelStride + this.interp_top * srcScanlineStride;
/* 1076 */             start = p_x + p_y - start;
/* 1077 */             int countH = 0, countV = 0;
/* 1079 */             for (int i = 0; i < this.interp_height; i++) {
/* 1080 */               int startY = start;
/* 1081 */               for (int j = 0; j < this.interp_width; j++) {
/* 1082 */                 samples[countV][countH++] = srcData[start + tmp];
/* 1084 */                 start += srcPixelStride;
/*      */               } 
/* 1086 */               countV++;
/* 1087 */               countH = 0;
/* 1088 */               start = startY + srcScanlineStride;
/*      */             } 
/* 1092 */             double s = this.interp.interpolate(samples, (float)fracx, (float)fracy);
/* 1097 */             dstDataArrays[k][dstPixelOffset + dstBandOffsets[k]] = s;
/*      */           } 
/* 1100 */         } else if (this.setBackground) {
/* 1101 */           for (int k = 0; k < dst_num_bands; k++)
/* 1102 */             dstDataArrays[k][dstPixelOffset + dstBandOffsets[k]] = this.backgroundValues[k]; 
/*      */         } 
/* 1106 */         if (fracx < this.fracdx1) {
/* 1107 */           s_ix += this.incx;
/* 1108 */           fracx += this.fracdx;
/*      */         } else {
/* 1110 */           s_ix += this.incx1;
/* 1111 */           fracx -= this.fracdx1;
/*      */         } 
/* 1114 */         if (fracy < this.fracdy1) {
/* 1115 */           s_iy += this.incy;
/* 1116 */           fracy += this.fracdy;
/*      */         } else {
/* 1118 */           s_iy += this.incy1;
/* 1119 */           fracy -= this.fracdy1;
/*      */         } 
/* 1123 */         p_x = (s_ix - srcRectX) * srcPixelStride;
/* 1124 */         p_y = (s_iy - srcRectY) * srcScanlineStride;
/* 1126 */         dstPixelOffset += dstPixelStride;
/*      */       } 
/* 1129 */       dstOffset += dstScanlineStride;
/*      */     } 
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\AffineGeneralOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */