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
/*      */ final class AffineBicubicOpImage extends AffineOpImage {
/*      */   public AffineBicubicOpImage(RenderedImage source, BorderExtender extender, Map config, ImageLayout layout, AffineTransform transform, Interpolation interp, double[] backgroundValues) {
/*   53 */     super(source, extender, config, layout, transform, interp, backgroundValues);
/*      */   }
/*      */   
/*      */   protected void computeRect(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/*   75 */     RasterFormatTag[] formatTags = getFormatTags();
/*   77 */     Raster source = sources[0];
/*   79 */     Rectangle srcRect = source.getBounds();
/*   81 */     int srcRectX = srcRect.x;
/*   82 */     int srcRectY = srcRect.y;
/*   91 */     RasterAccessor srcAccessor = new RasterAccessor(source, srcRect, formatTags[0], getSourceImage(0).getColorModel());
/*   96 */     RasterAccessor dstAccessor = new RasterAccessor(dest, destRect, formatTags[1], getColorModel());
/*  102 */     switch (dstAccessor.getDataType()) {
/*      */       case 0:
/*  104 */         byteLoop(srcAccessor, destRect, srcRectX, srcRectY, dstAccessor);
/*      */         break;
/*      */       case 3:
/*  112 */         intLoop(srcAccessor, destRect, srcRectX, srcRectY, dstAccessor);
/*      */         break;
/*      */       case 2:
/*  120 */         shortLoop(srcAccessor, destRect, srcRectX, srcRectY, dstAccessor);
/*      */         break;
/*      */       case 1:
/*  128 */         ushortLoop(srcAccessor, destRect, srcRectX, srcRectY, dstAccessor);
/*      */         break;
/*      */       case 4:
/*  136 */         floatLoop(srcAccessor, destRect, srcRectX, srcRectY, dstAccessor);
/*      */         break;
/*      */       case 5:
/*  144 */         doubleLoop(srcAccessor, destRect, srcRectX, srcRectY, dstAccessor);
/*      */         break;
/*      */     } 
/*  155 */     if (dstAccessor.isDataCopy()) {
/*  156 */       dstAccessor.clampDataArrays();
/*  157 */       dstAccessor.copyDataToRaster();
/*      */     } 
/*      */   }
/*      */   
/*      */   private void byteLoop(RasterAccessor src, Rectangle destRect, int srcRectX, int srcRectY, RasterAccessor dst) {
/*  167 */     float src_rect_x1 = src.getX();
/*  168 */     float src_rect_y1 = src.getY();
/*  169 */     float src_rect_x2 = src_rect_x1 + src.getWidth();
/*  170 */     float src_rect_y2 = src_rect_y1 + src.getHeight();
/*  197 */     int dstOffset = 0;
/*  199 */     Point2D dst_pt = new Point2D.Float();
/*  200 */     Point2D src_pt = new Point2D.Float();
/*  202 */     byte[][] dstDataArrays = dst.getByteDataArrays();
/*  203 */     int[] dstBandOffsets = dst.getBandOffsets();
/*  204 */     int dstPixelStride = dst.getPixelStride();
/*  205 */     int dstScanlineStride = dst.getScanlineStride();
/*  207 */     byte[][] srcDataArrays = src.getByteDataArrays();
/*  208 */     int[] bandOffsets = src.getBandOffsets();
/*  209 */     int srcPixelStride = src.getPixelStride();
/*  210 */     int srcScanlineStride = src.getScanlineStride();
/*  212 */     int dst_num_bands = dst.getNumBands();
/*  214 */     int dst_min_x = destRect.x;
/*  215 */     int dst_min_y = destRect.y;
/*  216 */     int dst_max_x = destRect.x + destRect.width;
/*  217 */     int dst_max_y = destRect.y + destRect.height;
/*  219 */     byte[] backgroundByte = new byte[dst_num_bands];
/*  220 */     for (int i = 0; i < dst_num_bands; i++)
/*  221 */       backgroundByte[i] = (byte)(int)this.backgroundValues[i]; 
/*  223 */     for (int y = dst_min_y; y < dst_max_y; y++) {
/*  224 */       int dstPixelOffset = dstOffset;
/*  228 */       dst_pt.setLocation(dst_min_x + 0.5D, y + 0.5D);
/*  230 */       mapDestPoint(dst_pt, src_pt);
/*  233 */       float s_x = (float)src_pt.getX();
/*  234 */       float s_y = (float)src_pt.getY();
/*  237 */       s_x = (float)(s_x - 0.5D);
/*  238 */       s_y = (float)(s_y - 0.5D);
/*  241 */       int s_ix = (int)Math.floor(s_x);
/*  242 */       int s_iy = (int)Math.floor(s_y);
/*  244 */       float fracx = s_x - s_ix;
/*  245 */       float fracy = s_y - s_iy;
/*  248 */       int p_x = (s_ix - srcRectX) * srcPixelStride;
/*  249 */       int p_y = (s_iy - srcRectY) * srcScanlineStride;
/*  260 */       int p__ = p_x + p_y - srcScanlineStride - srcPixelStride;
/*  261 */       int p0_ = p__ + srcPixelStride;
/*  262 */       int p1_ = p0_ + srcPixelStride;
/*  263 */       int p2_ = p1_ + srcPixelStride;
/*  264 */       int p_0 = p__ + srcScanlineStride;
/*  265 */       int p00 = p_0 + srcPixelStride;
/*  266 */       int p10 = p00 + srcPixelStride;
/*  267 */       int p20 = p10 + srcPixelStride;
/*  268 */       int p_1 = p_0 + srcScanlineStride;
/*  269 */       int p01 = p_1 + srcPixelStride;
/*  270 */       int p11 = p01 + srcPixelStride;
/*  271 */       int p21 = p11 + srcPixelStride;
/*  272 */       int p_2 = p_1 + srcScanlineStride;
/*  273 */       int p02 = p_2 + srcPixelStride;
/*  274 */       int p12 = p02 + srcPixelStride;
/*  275 */       int p22 = p12 + srcPixelStride;
/*  277 */       for (int x = dst_min_x; x < dst_max_x; x++) {
/*  282 */         if (s_ix >= src_rect_x1 + 1.0F && s_ix < src_rect_x2 - 2.0F && s_iy >= src_rect_y1 + 1.0F && s_iy < src_rect_y2 - 2.0F) {
/*  286 */           for (int k2 = 0; k2 < dst_num_bands; k2++) {
/*      */             int result;
/*  293 */             byte[] tmp_row = srcDataArrays[k2];
/*  294 */             int tmp_col = bandOffsets[k2];
/*  296 */             int s__ = tmp_row[p__ + tmp_col] & 0xFF;
/*  297 */             int s0_ = tmp_row[p0_ + tmp_col] & 0xFF;
/*  298 */             int s1_ = tmp_row[p1_ + tmp_col] & 0xFF;
/*  299 */             int s2_ = tmp_row[p2_ + tmp_col] & 0xFF;
/*  300 */             int s_0 = tmp_row[p_0 + tmp_col] & 0xFF;
/*  301 */             int s00 = tmp_row[p00 + tmp_col] & 0xFF;
/*  302 */             int s10 = tmp_row[p10 + tmp_col] & 0xFF;
/*  303 */             int s20 = tmp_row[p20 + tmp_col] & 0xFF;
/*  304 */             int s_1 = tmp_row[p_1 + tmp_col] & 0xFF;
/*  305 */             int s01 = tmp_row[p01 + tmp_col] & 0xFF;
/*  306 */             int s11 = tmp_row[p11 + tmp_col] & 0xFF;
/*  307 */             int s21 = tmp_row[p21 + tmp_col] & 0xFF;
/*  308 */             int s_2 = tmp_row[p_2 + tmp_col] & 0xFF;
/*  309 */             int s02 = tmp_row[p02 + tmp_col] & 0xFF;
/*  310 */             int s12 = tmp_row[p12 + tmp_col] & 0xFF;
/*  311 */             int s22 = tmp_row[p22 + tmp_col] & 0xFF;
/*  314 */             float float_fracx = fracx;
/*  315 */             float float_fracy = fracy;
/*  316 */             float frac_xx = float_fracx * (1.0F - float_fracx);
/*  317 */             float frac_yy = float_fracx * (1.0F - float_fracy);
/*  319 */             float s0 = s00 + (s10 - s00) * float_fracx;
/*  320 */             float s1 = s01 + (s11 - s01) * float_fracx;
/*  321 */             float s_ = s0_ + (s1_ - s0_) * float_fracx;
/*  322 */             float s2 = s02 + (s12 - s02) * float_fracx;
/*  324 */             float q_ = (s1_ + s__) + (s2_ + s0_ - s1_ + s__) * float_fracx;
/*  326 */             float q0 = (s10 + s_0) + (s20 + s00 - s10 + s_0) * float_fracx;
/*  328 */             float q1 = (s11 + s_1) + (s21 + s01 - s11 + s_1) * float_fracx;
/*  330 */             float q2 = (s12 + s_2) + (s22 + s02 - s12 + s_2) * float_fracx;
/*  333 */             q_ = s_ - q_ / 2.0F;
/*  334 */             q0 = s0 - q0 / 2.0F;
/*  335 */             q1 = s1 - q1 / 2.0F;
/*  336 */             q2 = s2 - q2 / 2.0F;
/*  338 */             s_ += q_ * frac_xx;
/*  339 */             s0 += q0 * frac_xx;
/*  340 */             s1 += q1 * frac_xx;
/*  341 */             s2 += q2 * frac_xx;
/*  343 */             float s = s0 + (s1 - s0) * float_fracy;
/*  344 */             float q = s1 + s_ + (s2 + s0 - s1 + s_) * float_fracy;
/*  347 */             q = s - q / 2.0F;
/*  349 */             s += q * frac_yy;
/*  352 */             if (s < 0.5F) {
/*  353 */               result = 0;
/*  354 */             } else if (s > 254.5F) {
/*  355 */               result = 255;
/*      */             } else {
/*  357 */               result = (int)(s + 0.5F);
/*      */             } 
/*  361 */             dstDataArrays[k2][dstPixelOffset + dstBandOffsets[k2]] = (byte)(result & 0xFF);
/*      */           } 
/*  365 */         } else if (this.setBackground) {
/*  366 */           for (int k = 0; k < dst_num_bands; k++)
/*  367 */             dstDataArrays[k][dstPixelOffset + dstBandOffsets[k]] = backgroundByte[k]; 
/*      */         } 
/*  372 */         if (fracx < this.fracdx1) {
/*  373 */           s_ix += this.incx;
/*  374 */           fracx = (float)(fracx + this.fracdx);
/*      */         } else {
/*  376 */           s_ix += this.incx1;
/*  377 */           fracx = (float)(fracx - this.fracdx1);
/*      */         } 
/*  380 */         if (fracy < this.fracdy1) {
/*  381 */           s_iy += this.incy;
/*  382 */           fracy = (float)(fracy + this.fracdy);
/*      */         } else {
/*  384 */           s_iy += this.incy1;
/*  385 */           fracy = (float)(fracy - this.fracdy1);
/*      */         } 
/*  389 */         p_x = (s_ix - srcRectX) * srcPixelStride;
/*  390 */         p_y = (s_iy - srcRectY) * srcScanlineStride;
/*  401 */         p__ = p_x + p_y - srcScanlineStride - srcPixelStride;
/*  402 */         p0_ = p__ + srcPixelStride;
/*  403 */         p1_ = p0_ + srcPixelStride;
/*  404 */         p2_ = p1_ + srcPixelStride;
/*  405 */         p_0 = p__ + srcScanlineStride;
/*  406 */         p00 = p_0 + srcPixelStride;
/*  407 */         p10 = p00 + srcPixelStride;
/*  408 */         p20 = p10 + srcPixelStride;
/*  409 */         p_1 = p_0 + srcScanlineStride;
/*  410 */         p01 = p_1 + srcPixelStride;
/*  411 */         p11 = p01 + srcPixelStride;
/*  412 */         p21 = p11 + srcPixelStride;
/*  413 */         p_2 = p_1 + srcScanlineStride;
/*  414 */         p02 = p_2 + srcPixelStride;
/*  415 */         p12 = p02 + srcPixelStride;
/*  416 */         p22 = p12 + srcPixelStride;
/*  418 */         dstPixelOffset += dstPixelStride;
/*      */       } 
/*  421 */       dstOffset += dstScanlineStride;
/*      */     } 
/*      */   }
/*      */   
/*      */   private void intLoop(RasterAccessor src, Rectangle destRect, int srcRectX, int srcRectY, RasterAccessor dst) {
/*  432 */     float src_rect_x1 = src.getX();
/*  433 */     float src_rect_y1 = src.getY();
/*  434 */     float src_rect_x2 = src_rect_x1 + src.getWidth();
/*  435 */     float src_rect_y2 = src_rect_y1 + src.getHeight();
/*  462 */     int dstOffset = 0;
/*  464 */     Point2D dst_pt = new Point2D.Float();
/*  465 */     Point2D src_pt = new Point2D.Float();
/*  467 */     int[][] dstDataArrays = dst.getIntDataArrays();
/*  468 */     int[] dstBandOffsets = dst.getBandOffsets();
/*  469 */     int dstPixelStride = dst.getPixelStride();
/*  470 */     int dstScanlineStride = dst.getScanlineStride();
/*  472 */     int[][] srcDataArrays = src.getIntDataArrays();
/*  473 */     int[] bandOffsets = src.getBandOffsets();
/*  474 */     int srcPixelStride = src.getPixelStride();
/*  475 */     int srcScanlineStride = src.getScanlineStride();
/*  477 */     int dst_num_bands = dst.getNumBands();
/*  479 */     int dst_min_x = destRect.x;
/*  480 */     int dst_min_y = destRect.y;
/*  481 */     int dst_max_x = destRect.x + destRect.width;
/*  482 */     int dst_max_y = destRect.y + destRect.height;
/*  484 */     int[] backgroundInt = new int[dst_num_bands];
/*  485 */     for (int i = 0; i < dst_num_bands; i++)
/*  486 */       backgroundInt[i] = (int)this.backgroundValues[i]; 
/*  488 */     for (int y = dst_min_y; y < dst_max_y; y++) {
/*  489 */       int dstPixelOffset = dstOffset;
/*  493 */       dst_pt.setLocation(dst_min_x + 0.5D, y + 0.5D);
/*  495 */       mapDestPoint(dst_pt, src_pt);
/*  498 */       float s_x = (float)src_pt.getX();
/*  499 */       float s_y = (float)src_pt.getY();
/*  502 */       s_x = (float)(s_x - 0.5D);
/*  503 */       s_y = (float)(s_y - 0.5D);
/*  506 */       int s_ix = (int)Math.floor(s_x);
/*  507 */       int s_iy = (int)Math.floor(s_y);
/*  509 */       float fracx = s_x - s_ix;
/*  510 */       float fracy = s_y - s_iy;
/*  513 */       int p_x = (s_ix - srcRectX) * srcPixelStride;
/*  514 */       int p_y = (s_iy - srcRectY) * srcScanlineStride;
/*  525 */       int p__ = p_x + p_y - srcScanlineStride - srcPixelStride;
/*  526 */       int p0_ = p__ + srcPixelStride;
/*  527 */       int p1_ = p0_ + srcPixelStride;
/*  528 */       int p2_ = p1_ + srcPixelStride;
/*  529 */       int p_0 = p__ + srcScanlineStride;
/*  530 */       int p00 = p_0 + srcPixelStride;
/*  531 */       int p10 = p00 + srcPixelStride;
/*  532 */       int p20 = p10 + srcPixelStride;
/*  533 */       int p_1 = p_0 + srcScanlineStride;
/*  534 */       int p01 = p_1 + srcPixelStride;
/*  535 */       int p11 = p01 + srcPixelStride;
/*  536 */       int p21 = p11 + srcPixelStride;
/*  537 */       int p_2 = p_1 + srcScanlineStride;
/*  538 */       int p02 = p_2 + srcPixelStride;
/*  539 */       int p12 = p02 + srcPixelStride;
/*  540 */       int p22 = p12 + srcPixelStride;
/*  542 */       for (int x = dst_min_x; x < dst_max_x; x++) {
/*  546 */         if (s_ix >= src_rect_x1 + 1.0F && s_ix < src_rect_x2 - 2.0F && s_iy >= src_rect_y1 + 1.0F && s_iy < src_rect_y2 - 2.0F) {
/*  550 */           for (int k2 = 0; k2 < dst_num_bands; k2++) {
/*  557 */             int result, tmp_row[] = srcDataArrays[k2];
/*  558 */             int tmp_col = bandOffsets[k2];
/*  560 */             int s__ = tmp_row[p__ + tmp_col];
/*  561 */             int s0_ = tmp_row[p0_ + tmp_col];
/*  562 */             int s1_ = tmp_row[p1_ + tmp_col];
/*  563 */             int s2_ = tmp_row[p2_ + tmp_col];
/*  564 */             int s_0 = tmp_row[p_0 + tmp_col];
/*  565 */             int s00 = tmp_row[p00 + tmp_col];
/*  566 */             int s10 = tmp_row[p10 + tmp_col];
/*  567 */             int s20 = tmp_row[p20 + tmp_col];
/*  568 */             int s_1 = tmp_row[p_1 + tmp_col];
/*  569 */             int s01 = tmp_row[p01 + tmp_col];
/*  570 */             int s11 = tmp_row[p11 + tmp_col];
/*  571 */             int s21 = tmp_row[p21 + tmp_col];
/*  572 */             int s_2 = tmp_row[p_2 + tmp_col];
/*  573 */             int s02 = tmp_row[p02 + tmp_col];
/*  574 */             int s12 = tmp_row[p12 + tmp_col];
/*  575 */             int s22 = tmp_row[p22 + tmp_col];
/*  578 */             float float_fracx = fracx;
/*  579 */             float float_fracy = fracy;
/*  580 */             float frac_xx = float_fracx * (1.0F - float_fracx);
/*  581 */             float frac_yy = float_fracx * (1.0F - float_fracy);
/*  583 */             float s0 = s00 + (s10 - s00) * float_fracx;
/*  584 */             float s1 = s01 + (s11 - s01) * float_fracx;
/*  585 */             float s_ = s0_ + (s1_ - s0_) * float_fracx;
/*  586 */             float s2 = s02 + (s12 - s02) * float_fracx;
/*  588 */             float q_ = (s1_ + s__) + (s2_ + s0_ - s1_ + s__) * float_fracx;
/*  590 */             float q0 = (s10 + s_0) + (s20 + s00 - s10 + s_0) * float_fracx;
/*  592 */             float q1 = (s11 + s_1) + (s21 + s01 - s11 + s_1) * float_fracx;
/*  594 */             float q2 = (s12 + s_2) + (s22 + s02 - s12 + s_2) * float_fracx;
/*  597 */             q_ = s_ - q_ / 2.0F;
/*  598 */             q0 = s0 - q0 / 2.0F;
/*  599 */             q1 = s1 - q1 / 2.0F;
/*  600 */             q2 = s2 - q2 / 2.0F;
/*  602 */             s_ += q_ * frac_xx;
/*  603 */             s0 += q0 * frac_xx;
/*  604 */             s1 += q1 * frac_xx;
/*  605 */             s2 += q2 * frac_xx;
/*  607 */             float s = s0 + (s1 - s0) * float_fracy;
/*  608 */             float q = s1 + s_ + (s2 + s0 - s1 + s_) * float_fracy;
/*  611 */             q = s - q / 2.0F;
/*  613 */             s += q * frac_yy;
/*  616 */             if (s < -2.1474836E9F) {
/*  617 */               result = Integer.MIN_VALUE;
/*  618 */             } else if (s > 2.1474836E9F) {
/*  619 */               result = Integer.MAX_VALUE;
/*  620 */             } else if (s > 0.0D) {
/*  621 */               result = (int)(s + 0.5F);
/*      */             } else {
/*  623 */               result = (int)(s - 0.5F);
/*      */             } 
/*  627 */             dstDataArrays[k2][dstPixelOffset + dstBandOffsets[k2]] = result;
/*      */           } 
/*  630 */         } else if (this.setBackground) {
/*  631 */           for (int k = 0; k < dst_num_bands; k++)
/*  632 */             dstDataArrays[k][dstPixelOffset + dstBandOffsets[k]] = backgroundInt[k]; 
/*      */         } 
/*  637 */         if (fracx < this.fracdx1) {
/*  638 */           s_ix += this.incx;
/*  639 */           fracx = (float)(fracx + this.fracdx);
/*      */         } else {
/*  641 */           s_ix += this.incx1;
/*  642 */           fracx = (float)(fracx - this.fracdx1);
/*      */         } 
/*  645 */         if (fracy < this.fracdy1) {
/*  646 */           s_iy += this.incy;
/*  647 */           fracy = (float)(fracy + this.fracdy);
/*      */         } else {
/*  649 */           s_iy += this.incy1;
/*  650 */           fracy = (float)(fracy - this.fracdy1);
/*      */         } 
/*  654 */         p_x = (s_ix - srcRectX) * srcPixelStride;
/*  655 */         p_y = (s_iy - srcRectY) * srcScanlineStride;
/*  666 */         p__ = p_x + p_y - srcScanlineStride - srcPixelStride;
/*  667 */         p0_ = p__ + srcPixelStride;
/*  668 */         p1_ = p0_ + srcPixelStride;
/*  669 */         p2_ = p1_ + srcPixelStride;
/*  670 */         p_0 = p__ + srcScanlineStride;
/*  671 */         p00 = p_0 + srcPixelStride;
/*  672 */         p10 = p00 + srcPixelStride;
/*  673 */         p20 = p10 + srcPixelStride;
/*  674 */         p_1 = p_0 + srcScanlineStride;
/*  675 */         p01 = p_1 + srcPixelStride;
/*  676 */         p11 = p01 + srcPixelStride;
/*  677 */         p21 = p11 + srcPixelStride;
/*  678 */         p_2 = p_1 + srcScanlineStride;
/*  679 */         p02 = p_2 + srcPixelStride;
/*  680 */         p12 = p02 + srcPixelStride;
/*  681 */         p22 = p12 + srcPixelStride;
/*  683 */         dstPixelOffset += dstPixelStride;
/*      */       } 
/*  686 */       dstOffset += dstScanlineStride;
/*      */     } 
/*      */   }
/*      */   
/*      */   private void shortLoop(RasterAccessor src, Rectangle destRect, int srcRectX, int srcRectY, RasterAccessor dst) {
/*  696 */     float src_rect_x1 = src.getX();
/*  697 */     float src_rect_y1 = src.getY();
/*  698 */     float src_rect_x2 = src_rect_x1 + src.getWidth();
/*  699 */     float src_rect_y2 = src_rect_y1 + src.getHeight();
/*  727 */     int dstOffset = 0;
/*  729 */     Point2D dst_pt = new Point2D.Float();
/*  730 */     Point2D src_pt = new Point2D.Float();
/*  732 */     short[][] dstDataArrays = dst.getShortDataArrays();
/*  733 */     int[] dstBandOffsets = dst.getBandOffsets();
/*  734 */     int dstPixelStride = dst.getPixelStride();
/*  735 */     int dstScanlineStride = dst.getScanlineStride();
/*  737 */     short[][] srcDataArrays = src.getShortDataArrays();
/*  738 */     int[] bandOffsets = src.getBandOffsets();
/*  739 */     int srcPixelStride = src.getPixelStride();
/*  740 */     int srcScanlineStride = src.getScanlineStride();
/*  742 */     int dst_num_bands = dst.getNumBands();
/*  744 */     int dst_min_x = destRect.x;
/*  745 */     int dst_min_y = destRect.y;
/*  746 */     int dst_max_x = destRect.x + destRect.width;
/*  747 */     int dst_max_y = destRect.y + destRect.height;
/*  749 */     short[] backgroundShort = new short[dst_num_bands];
/*  750 */     for (int i = 0; i < dst_num_bands; i++)
/*  751 */       backgroundShort[i] = (short)(int)this.backgroundValues[i]; 
/*  753 */     for (int y = dst_min_y; y < dst_max_y; y++) {
/*  755 */       int dstPixelOffset = dstOffset;
/*  759 */       dst_pt.setLocation(dst_min_x + 0.5D, y + 0.5D);
/*  761 */       mapDestPoint(dst_pt, src_pt);
/*  764 */       float s_x = (float)src_pt.getX();
/*  765 */       float s_y = (float)src_pt.getY();
/*  768 */       s_x = (float)(s_x - 0.5D);
/*  769 */       s_y = (float)(s_y - 0.5D);
/*  772 */       int s_ix = (int)Math.floor(s_x);
/*  773 */       int s_iy = (int)Math.floor(s_y);
/*  775 */       float fracx = s_x - s_ix;
/*  776 */       float fracy = s_y - s_iy;
/*  779 */       int p_x = (s_ix - srcRectX) * srcPixelStride;
/*  780 */       int p_y = (s_iy - srcRectY) * srcScanlineStride;
/*  791 */       int p__ = p_x + p_y - srcScanlineStride - srcPixelStride;
/*  792 */       int p0_ = p__ + srcPixelStride;
/*  793 */       int p1_ = p0_ + srcPixelStride;
/*  794 */       int p2_ = p1_ + srcPixelStride;
/*  795 */       int p_0 = p__ + srcScanlineStride;
/*  796 */       int p00 = p_0 + srcPixelStride;
/*  797 */       int p10 = p00 + srcPixelStride;
/*  798 */       int p20 = p10 + srcPixelStride;
/*  799 */       int p_1 = p_0 + srcScanlineStride;
/*  800 */       int p01 = p_1 + srcPixelStride;
/*  801 */       int p11 = p01 + srcPixelStride;
/*  802 */       int p21 = p11 + srcPixelStride;
/*  803 */       int p_2 = p_1 + srcScanlineStride;
/*  804 */       int p02 = p_2 + srcPixelStride;
/*  805 */       int p12 = p02 + srcPixelStride;
/*  806 */       int p22 = p12 + srcPixelStride;
/*  808 */       for (int x = dst_min_x; x < dst_max_x; x++) {
/*  812 */         if (s_ix >= src_rect_x1 + 1.0F && s_ix < src_rect_x2 - 2.0F && s_iy >= src_rect_y1 + 1.0F && s_iy < src_rect_y2 - 2.0F) {
/*  816 */           for (int k2 = 0; k2 < dst_num_bands; k2++) {
/*  823 */             short result, tmp_row[] = srcDataArrays[k2];
/*  824 */             int tmp_col = bandOffsets[k2];
/*  826 */             short s__ = tmp_row[p__ + tmp_col];
/*  827 */             short s0_ = tmp_row[p0_ + tmp_col];
/*  828 */             short s1_ = tmp_row[p1_ + tmp_col];
/*  829 */             short s2_ = tmp_row[p2_ + tmp_col];
/*  830 */             short s_0 = tmp_row[p_0 + tmp_col];
/*  831 */             short s00 = tmp_row[p00 + tmp_col];
/*  832 */             short s10 = tmp_row[p10 + tmp_col];
/*  833 */             short s20 = tmp_row[p20 + tmp_col];
/*  834 */             short s_1 = tmp_row[p_1 + tmp_col];
/*  835 */             short s01 = tmp_row[p01 + tmp_col];
/*  836 */             short s11 = tmp_row[p11 + tmp_col];
/*  837 */             short s21 = tmp_row[p21 + tmp_col];
/*  838 */             short s_2 = tmp_row[p_2 + tmp_col];
/*  839 */             short s02 = tmp_row[p02 + tmp_col];
/*  840 */             short s12 = tmp_row[p12 + tmp_col];
/*  841 */             short s22 = tmp_row[p22 + tmp_col];
/*  844 */             float float_fracx = fracx;
/*  845 */             float float_fracy = fracy;
/*  846 */             float frac_xx = float_fracx * (1.0F - float_fracx);
/*  847 */             float frac_yy = float_fracx * (1.0F - float_fracy);
/*  849 */             float s0 = s00 + (s10 - s00) * float_fracx;
/*  850 */             float s1 = s01 + (s11 - s01) * float_fracx;
/*  851 */             float s_ = s0_ + (s1_ - s0_) * float_fracx;
/*  852 */             float s2 = s02 + (s12 - s02) * float_fracx;
/*  854 */             float q_ = (s1_ + s__) + (s2_ + s0_ - s1_ + s__) * float_fracx;
/*  856 */             float q0 = (s10 + s_0) + (s20 + s00 - s10 + s_0) * float_fracx;
/*  858 */             float q1 = (s11 + s_1) + (s21 + s01 - s11 + s_1) * float_fracx;
/*  860 */             float q2 = (s12 + s_2) + (s22 + s02 - s12 + s_2) * float_fracx;
/*  863 */             q_ = s_ - q_ / 2.0F;
/*  864 */             q0 = s0 - q0 / 2.0F;
/*  865 */             q1 = s1 - q1 / 2.0F;
/*  866 */             q2 = s2 - q2 / 2.0F;
/*  868 */             s_ += q_ * frac_xx;
/*  869 */             s0 += q0 * frac_xx;
/*  870 */             s1 += q1 * frac_xx;
/*  871 */             s2 += q2 * frac_xx;
/*  873 */             float s = s0 + (s1 - s0) * float_fracy;
/*  874 */             float q = s1 + s_ + (s2 + s0 - s1 + s_) * float_fracy;
/*  877 */             q = s - q / 2.0F;
/*  879 */             s += q * frac_yy;
/*  882 */             if (s < -32768.0F) {
/*  883 */               result = Short.MIN_VALUE;
/*  884 */             } else if (s > 32767.0F) {
/*  885 */               result = Short.MAX_VALUE;
/*  886 */             } else if (s > 0.0D) {
/*  887 */               result = (short)(int)(s + 0.5F);
/*      */             } else {
/*  889 */               result = (short)(int)(s - 0.5F);
/*      */             } 
/*  893 */             dstDataArrays[k2][dstPixelOffset + dstBandOffsets[k2]] = result;
/*      */           } 
/*  896 */         } else if (this.setBackground) {
/*  897 */           for (int k = 0; k < dst_num_bands; k++)
/*  898 */             dstDataArrays[k][dstPixelOffset + dstBandOffsets[k]] = backgroundShort[k]; 
/*      */         } 
/*  903 */         if (fracx < this.fracdx1) {
/*  904 */           s_ix += this.incx;
/*  905 */           fracx = (float)(fracx + this.fracdx);
/*      */         } else {
/*  907 */           s_ix += this.incx1;
/*  908 */           fracx = (float)(fracx - this.fracdx1);
/*      */         } 
/*  911 */         if (fracy < this.fracdy1) {
/*  912 */           s_iy += this.incy;
/*  913 */           fracy = (float)(fracy + this.fracdy);
/*      */         } else {
/*  915 */           s_iy += this.incy1;
/*  916 */           fracy = (float)(fracy - this.fracdy1);
/*      */         } 
/*  920 */         p_x = (s_ix - srcRectX) * srcPixelStride;
/*  921 */         p_y = (s_iy - srcRectY) * srcScanlineStride;
/*  932 */         p__ = p_x + p_y - srcScanlineStride - srcPixelStride;
/*  933 */         p0_ = p__ + srcPixelStride;
/*  934 */         p1_ = p0_ + srcPixelStride;
/*  935 */         p2_ = p1_ + srcPixelStride;
/*  936 */         p_0 = p__ + srcScanlineStride;
/*  937 */         p00 = p_0 + srcPixelStride;
/*  938 */         p10 = p00 + srcPixelStride;
/*  939 */         p20 = p10 + srcPixelStride;
/*  940 */         p_1 = p_0 + srcScanlineStride;
/*  941 */         p01 = p_1 + srcPixelStride;
/*  942 */         p11 = p01 + srcPixelStride;
/*  943 */         p21 = p11 + srcPixelStride;
/*  944 */         p_2 = p_1 + srcScanlineStride;
/*  945 */         p02 = p_2 + srcPixelStride;
/*  946 */         p12 = p02 + srcPixelStride;
/*  947 */         p22 = p12 + srcPixelStride;
/*  949 */         dstPixelOffset += dstPixelStride;
/*      */       } 
/*  952 */       dstOffset += dstScanlineStride;
/*      */     } 
/*      */   }
/*      */   
/*      */   private void ushortLoop(RasterAccessor src, Rectangle destRect, int srcRectX, int srcRectY, RasterAccessor dst) {
/*  962 */     float src_rect_x1 = src.getX();
/*  963 */     float src_rect_y1 = src.getY();
/*  964 */     float src_rect_x2 = src_rect_x1 + src.getWidth();
/*  965 */     float src_rect_y2 = src_rect_y1 + src.getHeight();
/*  993 */     int dstOffset = 0;
/*  995 */     Point2D dst_pt = new Point2D.Float();
/*  996 */     Point2D src_pt = new Point2D.Float();
/*  998 */     short[][] dstDataArrays = dst.getShortDataArrays();
/*  999 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 1000 */     int dstPixelStride = dst.getPixelStride();
/* 1001 */     int dstScanlineStride = dst.getScanlineStride();
/* 1003 */     short[][] srcDataArrays = src.getShortDataArrays();
/* 1004 */     int[] bandOffsets = src.getBandOffsets();
/* 1005 */     int srcPixelStride = src.getPixelStride();
/* 1006 */     int srcScanlineStride = src.getScanlineStride();
/* 1008 */     int dst_num_bands = dst.getNumBands();
/* 1010 */     int dst_min_x = destRect.x;
/* 1011 */     int dst_min_y = destRect.y;
/* 1012 */     int dst_max_x = destRect.x + destRect.width;
/* 1013 */     int dst_max_y = destRect.y + destRect.height;
/* 1015 */     short[] backgroundUShort = new short[dst_num_bands];
/* 1016 */     for (int i = 0; i < dst_num_bands; i++)
/* 1017 */       backgroundUShort[i] = (short)(int)this.backgroundValues[i]; 
/* 1019 */     for (int y = dst_min_y; y < dst_max_y; y++) {
/* 1020 */       int dstPixelOffset = dstOffset;
/* 1024 */       dst_pt.setLocation(dst_min_x + 0.5D, y + 0.5D);
/* 1026 */       mapDestPoint(dst_pt, src_pt);
/* 1029 */       float s_x = (float)src_pt.getX();
/* 1030 */       float s_y = (float)src_pt.getY();
/* 1033 */       s_x = (float)(s_x - 0.5D);
/* 1034 */       s_y = (float)(s_y - 0.5D);
/* 1037 */       int s_ix = (int)Math.floor(s_x);
/* 1038 */       int s_iy = (int)Math.floor(s_y);
/* 1040 */       float fracx = s_x - s_ix;
/* 1041 */       float fracy = s_y - s_iy;
/* 1044 */       int p_x = (s_ix - srcRectX) * srcPixelStride;
/* 1045 */       int p_y = (s_iy - srcRectY) * srcScanlineStride;
/* 1056 */       int p__ = p_x + p_y - srcScanlineStride - srcPixelStride;
/* 1057 */       int p0_ = p__ + srcPixelStride;
/* 1058 */       int p1_ = p0_ + srcPixelStride;
/* 1059 */       int p2_ = p1_ + srcPixelStride;
/* 1060 */       int p_0 = p__ + srcScanlineStride;
/* 1061 */       int p00 = p_0 + srcPixelStride;
/* 1062 */       int p10 = p00 + srcPixelStride;
/* 1063 */       int p20 = p10 + srcPixelStride;
/* 1064 */       int p_1 = p_0 + srcScanlineStride;
/* 1065 */       int p01 = p_1 + srcPixelStride;
/* 1066 */       int p11 = p01 + srcPixelStride;
/* 1067 */       int p21 = p11 + srcPixelStride;
/* 1068 */       int p_2 = p_1 + srcScanlineStride;
/* 1069 */       int p02 = p_2 + srcPixelStride;
/* 1070 */       int p12 = p02 + srcPixelStride;
/* 1071 */       int p22 = p12 + srcPixelStride;
/* 1073 */       for (int x = dst_min_x; x < dst_max_x; x++) {
/* 1077 */         if (s_ix >= src_rect_x1 + 1.0F && s_ix < src_rect_x2 - 2.0F && s_iy >= src_rect_y1 + 1.0F && s_iy < src_rect_y2 - 2.0F) {
/* 1081 */           for (int k2 = 0; k2 < dst_num_bands; k2++) {
/*      */             int result;
/* 1088 */             short[] tmp_row = srcDataArrays[k2];
/* 1089 */             int tmp_col = bandOffsets[k2];
/* 1091 */             int s__ = tmp_row[p__ + tmp_col] & 0xFFFF;
/* 1092 */             int s0_ = tmp_row[p0_ + tmp_col] & 0xFFFF;
/* 1093 */             int s1_ = tmp_row[p1_ + tmp_col] & 0xFFFF;
/* 1094 */             int s2_ = tmp_row[p2_ + tmp_col] & 0xFFFF;
/* 1095 */             int s_0 = tmp_row[p_0 + tmp_col] & 0xFFFF;
/* 1096 */             int s00 = tmp_row[p00 + tmp_col] & 0xFFFF;
/* 1097 */             int s10 = tmp_row[p10 + tmp_col] & 0xFFFF;
/* 1098 */             int s20 = tmp_row[p20 + tmp_col] & 0xFFFF;
/* 1099 */             int s_1 = tmp_row[p_1 + tmp_col] & 0xFFFF;
/* 1100 */             int s01 = tmp_row[p01 + tmp_col] & 0xFFFF;
/* 1101 */             int s11 = tmp_row[p11 + tmp_col] & 0xFFFF;
/* 1102 */             int s21 = tmp_row[p21 + tmp_col] & 0xFFFF;
/* 1103 */             int s_2 = tmp_row[p_2 + tmp_col] & 0xFFFF;
/* 1104 */             int s02 = tmp_row[p02 + tmp_col] & 0xFFFF;
/* 1105 */             int s12 = tmp_row[p12 + tmp_col] & 0xFFFF;
/* 1106 */             int s22 = tmp_row[p22 + tmp_col] & 0xFFFF;
/* 1109 */             float float_fracx = fracx;
/* 1110 */             float float_fracy = fracy;
/* 1111 */             float frac_xx = float_fracx * (1.0F - float_fracx);
/* 1112 */             float frac_yy = float_fracx * (1.0F - float_fracy);
/* 1114 */             float s0 = s00 + (s10 - s00) * float_fracx;
/* 1115 */             float s1 = s01 + (s11 - s01) * float_fracx;
/* 1116 */             float s_ = s0_ + (s1_ - s0_) * float_fracx;
/* 1117 */             float s2 = s02 + (s12 - s02) * float_fracx;
/* 1119 */             float q_ = (s1_ + s__) + (s2_ + s0_ - s1_ + s__) * float_fracx;
/* 1121 */             float q0 = (s10 + s_0) + (s20 + s00 - s10 + s_0) * float_fracx;
/* 1123 */             float q1 = (s11 + s_1) + (s21 + s01 - s11 + s_1) * float_fracx;
/* 1125 */             float q2 = (s12 + s_2) + (s22 + s02 - s12 + s_2) * float_fracx;
/* 1128 */             q_ = s_ - q_ / 2.0F;
/* 1129 */             q0 = s0 - q0 / 2.0F;
/* 1130 */             q1 = s1 - q1 / 2.0F;
/* 1131 */             q2 = s2 - q2 / 2.0F;
/* 1133 */             s_ += q_ * frac_xx;
/* 1134 */             s0 += q0 * frac_xx;
/* 1135 */             s1 += q1 * frac_xx;
/* 1136 */             s2 += q2 * frac_xx;
/* 1138 */             float s = s0 + (s1 - s0) * float_fracy;
/* 1139 */             float q = s1 + s_ + (s2 + s0 - s1 + s_) * float_fracy;
/* 1143 */             q = s - q / 2.0F;
/* 1145 */             s += q * frac_yy;
/* 1148 */             if (s < 0.0D) {
/* 1149 */               result = 0;
/* 1150 */             } else if (s > 65535.0F) {
/* 1151 */               result = 65535;
/*      */             } else {
/* 1153 */               result = (int)(s + 0.5F);
/*      */             } 
/* 1157 */             dstDataArrays[k2][dstPixelOffset + dstBandOffsets[k2]] = (short)(result & 0xFFFF);
/*      */           } 
/* 1161 */         } else if (this.setBackground) {
/* 1162 */           for (int k = 0; k < dst_num_bands; k++)
/* 1163 */             dstDataArrays[k][dstPixelOffset + dstBandOffsets[k]] = backgroundUShort[k]; 
/*      */         } 
/* 1168 */         if (fracx < this.fracdx1) {
/* 1169 */           s_ix += this.incx;
/* 1170 */           fracx = (float)(fracx + this.fracdx);
/*      */         } else {
/* 1172 */           s_ix += this.incx1;
/* 1173 */           fracx = (float)(fracx - this.fracdx1);
/*      */         } 
/* 1176 */         if (fracy < this.fracdy1) {
/* 1177 */           s_iy += this.incy;
/* 1178 */           fracy = (float)(fracy + this.fracdy);
/*      */         } else {
/* 1180 */           s_iy += this.incy1;
/* 1181 */           fracy = (float)(fracy - this.fracdy1);
/*      */         } 
/* 1185 */         p_x = (s_ix - srcRectX) * srcPixelStride;
/* 1186 */         p_y = (s_iy - srcRectY) * srcScanlineStride;
/* 1197 */         p__ = p_x + p_y - srcScanlineStride - srcPixelStride;
/* 1198 */         p0_ = p__ + srcPixelStride;
/* 1199 */         p1_ = p0_ + srcPixelStride;
/* 1200 */         p2_ = p1_ + srcPixelStride;
/* 1201 */         p_0 = p__ + srcScanlineStride;
/* 1202 */         p00 = p_0 + srcPixelStride;
/* 1203 */         p10 = p00 + srcPixelStride;
/* 1204 */         p20 = p10 + srcPixelStride;
/* 1205 */         p_1 = p_0 + srcScanlineStride;
/* 1206 */         p01 = p_1 + srcPixelStride;
/* 1207 */         p11 = p01 + srcPixelStride;
/* 1208 */         p21 = p11 + srcPixelStride;
/* 1209 */         p_2 = p_1 + srcScanlineStride;
/* 1210 */         p02 = p_2 + srcPixelStride;
/* 1211 */         p12 = p02 + srcPixelStride;
/* 1212 */         p22 = p12 + srcPixelStride;
/* 1214 */         dstPixelOffset += dstPixelStride;
/*      */       } 
/* 1217 */       dstOffset += dstScanlineStride;
/*      */     } 
/*      */   }
/*      */   
/*      */   private void floatLoop(RasterAccessor src, Rectangle destRect, int srcRectX, int srcRectY, RasterAccessor dst) {
/* 1227 */     float src_rect_x1 = src.getX();
/* 1228 */     float src_rect_y1 = src.getY();
/* 1229 */     float src_rect_x2 = src_rect_x1 + src.getWidth();
/* 1230 */     float src_rect_y2 = src_rect_y1 + src.getHeight();
/* 1256 */     int dstOffset = 0;
/* 1258 */     Point2D dst_pt = new Point2D.Float();
/* 1259 */     Point2D src_pt = new Point2D.Float();
/* 1261 */     float[][] dstDataArrays = dst.getFloatDataArrays();
/* 1262 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 1263 */     int dstPixelStride = dst.getPixelStride();
/* 1264 */     int dstScanlineStride = dst.getScanlineStride();
/* 1266 */     float[][] srcDataArrays = src.getFloatDataArrays();
/* 1267 */     int[] bandOffsets = src.getBandOffsets();
/* 1268 */     int srcPixelStride = src.getPixelStride();
/* 1269 */     int srcScanlineStride = src.getScanlineStride();
/* 1271 */     int dst_num_bands = dst.getNumBands();
/* 1273 */     int dst_min_x = destRect.x;
/* 1274 */     int dst_min_y = destRect.y;
/* 1275 */     int dst_max_x = destRect.x + destRect.width;
/* 1276 */     int dst_max_y = destRect.y + destRect.height;
/* 1278 */     float[] backgroundFloat = new float[dst_num_bands];
/* 1279 */     for (int i = 0; i < dst_num_bands; i++)
/* 1280 */       backgroundFloat[i] = (float)this.backgroundValues[i]; 
/* 1282 */     for (int y = dst_min_y; y < dst_max_y; y++) {
/* 1284 */       int dstPixelOffset = dstOffset;
/* 1288 */       dst_pt.setLocation(dst_min_x + 0.5D, y + 0.5D);
/* 1290 */       mapDestPoint(dst_pt, src_pt);
/* 1293 */       float s_x = (float)src_pt.getX();
/* 1294 */       float s_y = (float)src_pt.getY();
/* 1297 */       s_x = (float)(s_x - 0.5D);
/* 1298 */       s_y = (float)(s_y - 0.5D);
/* 1301 */       int s_ix = (int)Math.floor(s_x);
/* 1302 */       int s_iy = (int)Math.floor(s_y);
/* 1304 */       float fracx = s_x - s_ix;
/* 1305 */       float fracy = s_y - s_iy;
/* 1308 */       int p_x = (s_ix - srcRectX) * srcPixelStride;
/* 1309 */       int p_y = (s_iy - srcRectY) * srcScanlineStride;
/* 1320 */       int p__ = p_x + p_y - srcScanlineStride - srcPixelStride;
/* 1321 */       int p0_ = p__ + srcPixelStride;
/* 1322 */       int p1_ = p0_ + srcPixelStride;
/* 1323 */       int p2_ = p1_ + srcPixelStride;
/* 1324 */       int p_0 = p__ + srcScanlineStride;
/* 1325 */       int p00 = p_0 + srcPixelStride;
/* 1326 */       int p10 = p00 + srcPixelStride;
/* 1327 */       int p20 = p10 + srcPixelStride;
/* 1328 */       int p_1 = p_0 + srcScanlineStride;
/* 1329 */       int p01 = p_1 + srcPixelStride;
/* 1330 */       int p11 = p01 + srcPixelStride;
/* 1331 */       int p21 = p11 + srcPixelStride;
/* 1332 */       int p_2 = p_1 + srcScanlineStride;
/* 1333 */       int p02 = p_2 + srcPixelStride;
/* 1334 */       int p12 = p02 + srcPixelStride;
/* 1335 */       int p22 = p12 + srcPixelStride;
/* 1337 */       for (int x = dst_min_x; x < dst_max_x; x++) {
/* 1341 */         if (s_ix >= src_rect_x1 + 1.0F && s_ix < src_rect_x2 - 2.0F && s_iy >= src_rect_y1 + 1.0F && s_iy < src_rect_y2 - 2.0F) {
/* 1345 */           for (int k2 = 0; k2 < dst_num_bands; k2++) {
/* 1352 */             float[] tmp_row = srcDataArrays[k2];
/* 1353 */             int tmp_col = bandOffsets[k2];
/* 1355 */             float s__ = tmp_row[p__ + tmp_col];
/* 1356 */             float s0_ = tmp_row[p0_ + tmp_col];
/* 1357 */             float s1_ = tmp_row[p1_ + tmp_col];
/* 1358 */             float s2_ = tmp_row[p2_ + tmp_col];
/* 1359 */             float s_0 = tmp_row[p_0 + tmp_col];
/* 1360 */             float s00 = tmp_row[p00 + tmp_col];
/* 1361 */             float s10 = tmp_row[p10 + tmp_col];
/* 1362 */             float s20 = tmp_row[p20 + tmp_col];
/* 1363 */             float s_1 = tmp_row[p_1 + tmp_col];
/* 1364 */             float s01 = tmp_row[p01 + tmp_col];
/* 1365 */             float s11 = tmp_row[p11 + tmp_col];
/* 1366 */             float s21 = tmp_row[p21 + tmp_col];
/* 1367 */             float s_2 = tmp_row[p_2 + tmp_col];
/* 1368 */             float s02 = tmp_row[p02 + tmp_col];
/* 1369 */             float s12 = tmp_row[p12 + tmp_col];
/* 1370 */             float s22 = tmp_row[p22 + tmp_col];
/* 1373 */             float float_fracx = fracx;
/* 1374 */             float float_fracy = fracy;
/* 1375 */             float frac_xx = float_fracx * (1.0F - float_fracx);
/* 1376 */             float frac_yy = float_fracx * (1.0F - float_fracy);
/* 1378 */             float s0 = s00 + (s10 - s00) * float_fracx;
/* 1379 */             float s1 = s01 + (s11 - s01) * float_fracx;
/* 1380 */             float s_ = s0_ + (s1_ - s0_) * float_fracx;
/* 1381 */             float s2 = s02 + (s12 - s02) * float_fracx;
/* 1383 */             float q_ = s1_ + s__ + (s2_ + s0_ - s1_ + s__) * float_fracx;
/* 1385 */             float q0 = s10 + s_0 + (s20 + s00 - s10 + s_0) * float_fracx;
/* 1387 */             float q1 = s11 + s_1 + (s21 + s01 - s11 + s_1) * float_fracx;
/* 1389 */             float q2 = s12 + s_2 + (s22 + s02 - s12 + s_2) * float_fracx;
/* 1392 */             q_ = s_ - q_ / 2.0F;
/* 1393 */             q0 = s0 - q0 / 2.0F;
/* 1394 */             q1 = s1 - q1 / 2.0F;
/* 1395 */             q2 = s2 - q2 / 2.0F;
/* 1397 */             s_ += q_ * frac_xx;
/* 1398 */             s0 += q0 * frac_xx;
/* 1399 */             s1 += q1 * frac_xx;
/* 1400 */             s2 += q2 * frac_xx;
/* 1402 */             float s = s0 + (s1 - s0) * float_fracy;
/* 1403 */             float q = s1 + s_ + (s2 + s0 - s1 + s_) * float_fracy;
/* 1407 */             q = s - q / 2.0F;
/* 1409 */             s += q * frac_yy;
/* 1412 */             dstDataArrays[k2][dstPixelOffset + dstBandOffsets[k2]] = s;
/*      */           } 
/* 1415 */         } else if (this.setBackground) {
/* 1416 */           for (int k = 0; k < dst_num_bands; k++)
/* 1417 */             dstDataArrays[k][dstPixelOffset + dstBandOffsets[k]] = backgroundFloat[k]; 
/*      */         } 
/* 1422 */         if (fracx < this.fracdx1) {
/* 1423 */           s_ix += this.incx;
/* 1424 */           fracx = (float)(fracx + this.fracdx);
/*      */         } else {
/* 1426 */           s_ix += this.incx1;
/* 1427 */           fracx = (float)(fracx - this.fracdx1);
/*      */         } 
/* 1430 */         if (fracy < this.fracdy1) {
/* 1431 */           s_iy += this.incy;
/* 1432 */           fracy = (float)(fracy + this.fracdy);
/*      */         } else {
/* 1434 */           s_iy += this.incy1;
/* 1435 */           fracy = (float)(fracy - this.fracdy1);
/*      */         } 
/* 1439 */         p_x = (s_ix - srcRectX) * srcPixelStride;
/* 1440 */         p_y = (s_iy - srcRectY) * srcScanlineStride;
/* 1451 */         p__ = p_x + p_y - srcScanlineStride - srcPixelStride;
/* 1452 */         p0_ = p__ + srcPixelStride;
/* 1453 */         p1_ = p0_ + srcPixelStride;
/* 1454 */         p2_ = p1_ + srcPixelStride;
/* 1455 */         p_0 = p__ + srcScanlineStride;
/* 1456 */         p00 = p_0 + srcPixelStride;
/* 1457 */         p10 = p00 + srcPixelStride;
/* 1458 */         p20 = p10 + srcPixelStride;
/* 1459 */         p_1 = p_0 + srcScanlineStride;
/* 1460 */         p01 = p_1 + srcPixelStride;
/* 1461 */         p11 = p01 + srcPixelStride;
/* 1462 */         p21 = p11 + srcPixelStride;
/* 1463 */         p_2 = p_1 + srcScanlineStride;
/* 1464 */         p02 = p_2 + srcPixelStride;
/* 1465 */         p12 = p02 + srcPixelStride;
/* 1466 */         p22 = p12 + srcPixelStride;
/* 1468 */         dstPixelOffset += dstPixelStride;
/*      */       } 
/* 1471 */       dstOffset += dstScanlineStride;
/*      */     } 
/*      */   }
/*      */   
/*      */   private void doubleLoop(RasterAccessor src, Rectangle destRect, int srcRectX, int srcRectY, RasterAccessor dst) {
/* 1481 */     float src_rect_x1 = src.getX();
/* 1482 */     float src_rect_y1 = src.getY();
/* 1483 */     float src_rect_x2 = src_rect_x1 + src.getWidth();
/* 1484 */     float src_rect_y2 = src_rect_y1 + src.getHeight();
/* 1510 */     int dstOffset = 0;
/* 1512 */     Point2D dst_pt = new Point2D.Float();
/* 1513 */     Point2D src_pt = new Point2D.Float();
/* 1515 */     double[][] dstDataArrays = dst.getDoubleDataArrays();
/* 1516 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 1517 */     int dstPixelStride = dst.getPixelStride();
/* 1518 */     int dstScanlineStride = dst.getScanlineStride();
/* 1520 */     double[][] srcDataArrays = src.getDoubleDataArrays();
/* 1521 */     int[] bandOffsets = src.getBandOffsets();
/* 1522 */     int srcPixelStride = src.getPixelStride();
/* 1523 */     int srcScanlineStride = src.getScanlineStride();
/* 1525 */     int dst_num_bands = dst.getNumBands();
/* 1527 */     int dst_min_x = destRect.x;
/* 1528 */     int dst_min_y = destRect.y;
/* 1529 */     int dst_max_x = destRect.x + destRect.width;
/* 1530 */     int dst_max_y = destRect.y + destRect.height;
/* 1532 */     for (int y = dst_min_y; y < dst_max_y; y++) {
/* 1534 */       int dstPixelOffset = dstOffset;
/* 1539 */       dst_pt.setLocation(dst_min_x + 0.5D, y + 0.5D);
/* 1541 */       mapDestPoint(dst_pt, src_pt);
/* 1544 */       double s_x = src_pt.getX();
/* 1545 */       double s_y = src_pt.getY();
/* 1548 */       s_x -= 0.5D;
/* 1549 */       s_y -= 0.5D;
/* 1552 */       int s_ix = (int)Math.floor(s_x);
/* 1553 */       int s_iy = (int)Math.floor(s_y);
/* 1555 */       double fracx = s_x - s_ix;
/* 1556 */       double fracy = s_y - s_iy;
/* 1559 */       int p_x = (s_ix - srcRectX) * srcPixelStride;
/* 1560 */       int p_y = (s_iy - srcRectY) * srcScanlineStride;
/* 1571 */       int p__ = p_x + p_y - srcScanlineStride - srcPixelStride;
/* 1572 */       int p0_ = p__ + srcPixelStride;
/* 1573 */       int p1_ = p0_ + srcPixelStride;
/* 1574 */       int p2_ = p1_ + srcPixelStride;
/* 1575 */       int p_0 = p__ + srcScanlineStride;
/* 1576 */       int p00 = p_0 + srcPixelStride;
/* 1577 */       int p10 = p00 + srcPixelStride;
/* 1578 */       int p20 = p10 + srcPixelStride;
/* 1579 */       int p_1 = p_0 + srcScanlineStride;
/* 1580 */       int p01 = p_1 + srcPixelStride;
/* 1581 */       int p11 = p01 + srcPixelStride;
/* 1582 */       int p21 = p11 + srcPixelStride;
/* 1583 */       int p_2 = p_1 + srcScanlineStride;
/* 1584 */       int p02 = p_2 + srcPixelStride;
/* 1585 */       int p12 = p02 + srcPixelStride;
/* 1586 */       int p22 = p12 + srcPixelStride;
/* 1588 */       for (int x = dst_min_x; x < dst_max_x; x++) {
/* 1592 */         if (s_ix >= src_rect_x1 + 1.0F && s_ix < src_rect_x2 - 2.0F && s_iy >= src_rect_y1 + 1.0F && s_iy < src_rect_y2 - 2.0F) {
/* 1596 */           for (int k2 = 0; k2 < dst_num_bands; k2++) {
/* 1603 */             double[] tmp_row = srcDataArrays[k2];
/* 1604 */             int tmp_col = bandOffsets[k2];
/* 1606 */             double s__ = tmp_row[p__ + tmp_col];
/* 1607 */             double s0_ = tmp_row[p0_ + tmp_col];
/* 1608 */             double s1_ = tmp_row[p1_ + tmp_col];
/* 1609 */             double s2_ = tmp_row[p2_ + tmp_col];
/* 1610 */             double s_0 = tmp_row[p_0 + tmp_col];
/* 1611 */             double s00 = tmp_row[p00 + tmp_col];
/* 1612 */             double s10 = tmp_row[p10 + tmp_col];
/* 1613 */             double s20 = tmp_row[p20 + tmp_col];
/* 1614 */             double s_1 = tmp_row[p_1 + tmp_col];
/* 1615 */             double s01 = tmp_row[p01 + tmp_col];
/* 1616 */             double s11 = tmp_row[p11 + tmp_col];
/* 1617 */             double s21 = tmp_row[p21 + tmp_col];
/* 1618 */             double s_2 = tmp_row[p_2 + tmp_col];
/* 1619 */             double s02 = tmp_row[p02 + tmp_col];
/* 1620 */             double s12 = tmp_row[p12 + tmp_col];
/* 1621 */             double s22 = tmp_row[p22 + tmp_col];
/* 1624 */             double float_fracx = fracx;
/* 1625 */             double float_fracy = fracy;
/* 1626 */             double frac_xx = float_fracx * (1.0D - float_fracx);
/* 1627 */             double frac_yy = float_fracx * (1.0D - float_fracy);
/* 1629 */             double s0 = s00 + (s10 - s00) * float_fracx;
/* 1630 */             double s1 = s01 + (s11 - s01) * float_fracx;
/* 1631 */             double s_ = s0_ + (s1_ - s0_) * float_fracx;
/* 1632 */             double s2 = s02 + (s12 - s02) * float_fracx;
/* 1634 */             double q_ = s1_ + s__ + (s2_ + s0_ - s1_ + s__) * float_fracx;
/* 1636 */             double q0 = s10 + s_0 + (s20 + s00 - s10 + s_0) * float_fracx;
/* 1638 */             double q1 = s11 + s_1 + (s21 + s01 - s11 + s_1) * float_fracx;
/* 1640 */             double q2 = s12 + s_2 + (s22 + s02 - s12 + s_2) * float_fracx;
/* 1643 */             q_ = s_ - q_ / 2.0D;
/* 1644 */             q0 = s0 - q0 / 2.0D;
/* 1645 */             q1 = s1 - q1 / 2.0D;
/* 1646 */             q2 = s2 - q2 / 2.0D;
/* 1648 */             s_ += q_ * frac_xx;
/* 1649 */             s0 += q0 * frac_xx;
/* 1650 */             s1 += q1 * frac_xx;
/* 1651 */             s2 += q2 * frac_xx;
/* 1653 */             double s = s0 + (s1 - s0) * float_fracy;
/* 1654 */             double q = s1 + s_ + (s2 + s0 - s1 + s_) * float_fracy;
/* 1657 */             q = s - q / 2.0D;
/* 1659 */             s += q * frac_yy;
/* 1662 */             dstDataArrays[k2][dstPixelOffset + dstBandOffsets[k2]] = s;
/*      */           } 
/* 1665 */         } else if (this.setBackground) {
/* 1666 */           for (int k = 0; k < dst_num_bands; k++)
/* 1667 */             dstDataArrays[k][dstPixelOffset + dstBandOffsets[k]] = this.backgroundValues[k]; 
/*      */         } 
/* 1672 */         if (fracx < this.fracdx1) {
/* 1673 */           s_ix += this.incx;
/* 1674 */           fracx += this.fracdx;
/*      */         } else {
/* 1676 */           s_ix += this.incx1;
/* 1677 */           fracx -= this.fracdx1;
/*      */         } 
/* 1680 */         if (fracy < this.fracdy1) {
/* 1681 */           s_iy += this.incy;
/* 1682 */           fracy += this.fracdy;
/*      */         } else {
/* 1684 */           s_iy += this.incy1;
/* 1685 */           fracy -= this.fracdy1;
/*      */         } 
/* 1689 */         p_x = (s_ix - srcRectX) * srcPixelStride;
/* 1690 */         p_y = (s_iy - srcRectY) * srcScanlineStride;
/* 1701 */         p__ = p_x + p_y - srcScanlineStride - srcPixelStride;
/* 1702 */         p0_ = p__ + srcPixelStride;
/* 1703 */         p1_ = p0_ + srcPixelStride;
/* 1704 */         p2_ = p1_ + srcPixelStride;
/* 1705 */         p_0 = p__ + srcScanlineStride;
/* 1706 */         p00 = p_0 + srcPixelStride;
/* 1707 */         p10 = p00 + srcPixelStride;
/* 1708 */         p20 = p10 + srcPixelStride;
/* 1709 */         p_1 = p_0 + srcScanlineStride;
/* 1710 */         p01 = p_1 + srcPixelStride;
/* 1711 */         p11 = p01 + srcPixelStride;
/* 1712 */         p21 = p11 + srcPixelStride;
/* 1713 */         p_2 = p_1 + srcScanlineStride;
/* 1714 */         p02 = p_2 + srcPixelStride;
/* 1715 */         p12 = p02 + srcPixelStride;
/* 1716 */         p22 = p12 + srcPixelStride;
/* 1718 */         dstPixelOffset += dstPixelStride;
/*      */       } 
/* 1721 */       dstOffset += dstScanlineStride;
/*      */     } 
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\AffineBicubicOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */