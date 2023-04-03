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
/*      */ final class AffineBilinearOpImage extends AffineOpImage {
/*      */   public AffineBilinearOpImage(RenderedImage source, BorderExtender extender, Map config, ImageLayout layout, AffineTransform transform, Interpolation interp, double[] backgroundValues) {
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
/*  183 */     int dstOffset = 0;
/*  185 */     Point2D dst_pt = new Point2D.Float();
/*  186 */     Point2D src_pt = new Point2D.Float();
/*  188 */     int dwidth = dst.getWidth();
/*  189 */     int dheight = dst.getHeight();
/*  190 */     int dnumBands = dst.getNumBands();
/*  192 */     byte[][] dstDataArrays = dst.getByteDataArrays();
/*  193 */     int[] dstBandOffsets = dst.getBandOffsets();
/*  194 */     int dstPixelStride = dst.getPixelStride();
/*  195 */     int dstScanlineStride = dst.getScanlineStride();
/*  197 */     byte[][] srcDataArrays = src.getByteDataArrays();
/*  198 */     int[] bandOffsets = src.getBandOffsets();
/*  199 */     int srcPixelStride = src.getPixelStride();
/*  200 */     int srcScanlineStride = src.getScanlineStride();
/*  202 */     int dst_num_bands = dst.getNumBands();
/*  204 */     int dst_min_x = destRect.x;
/*  205 */     int dst_min_y = destRect.y;
/*  206 */     int dst_max_x = destRect.x + destRect.width;
/*  207 */     int dst_max_y = destRect.y + destRect.height;
/*  209 */     byte[] backgroundByte = new byte[dst_num_bands];
/*  210 */     for (int i = 0; i < dst_num_bands; i++)
/*  211 */       backgroundByte[i] = (byte)(int)this.backgroundValues[i]; 
/*  213 */     for (int y = dst_min_y; y < dst_max_y; y++) {
/*  215 */       int dstPixelOffset = dstOffset;
/*  219 */       dst_pt.setLocation(dst_min_x + 0.5D, y + 0.5D);
/*  221 */       mapDestPoint(dst_pt, src_pt);
/*  224 */       float s_x = (float)src_pt.getX();
/*  225 */       float s_y = (float)src_pt.getY();
/*  228 */       s_x = (float)(s_x - 0.5D);
/*  229 */       s_y = (float)(s_y - 0.5D);
/*  232 */       int s_ix = (int)Math.floor(s_x);
/*  233 */       int s_iy = (int)Math.floor(s_y);
/*  235 */       float fracx = s_x - s_ix;
/*  236 */       float fracy = s_y - s_iy;
/*  239 */       int pylow = (s_iy - srcRectY) * srcScanlineStride;
/*  240 */       int pxlow = (s_ix - srcRectX) * srcPixelStride;
/*  241 */       int pyhigh = pylow + srcScanlineStride;
/*  242 */       int pxhigh = pxlow + srcPixelStride;
/*  244 */       int tmp00 = pxlow + pylow;
/*  245 */       int tmp01 = pxhigh + pylow;
/*  246 */       int tmp10 = pxlow + pyhigh;
/*  247 */       int tmp11 = pxhigh + pyhigh;
/*  249 */       for (int x = dst_min_x; x < dst_max_x; x++) {
/*  253 */         if (s_ix >= src_rect_x1 && s_ix < src_rect_x2 - 1.0F && s_iy >= src_rect_y1 && s_iy < src_rect_y2 - 1.0F) {
/*  257 */           for (int k2 = 0; k2 < dst_num_bands; k2++) {
/*      */             int s;
/*  265 */             byte[] tmp_row = srcDataArrays[k2];
/*  268 */             int tmp_col = bandOffsets[k2];
/*  270 */             int s00 = tmp_row[tmp00 + tmp_col] & 0xFF;
/*  271 */             int s01 = tmp_row[tmp01 + tmp_col] & 0xFF;
/*  272 */             int s10 = tmp_row[tmp10 + tmp_col] & 0xFF;
/*  273 */             int s11 = tmp_row[tmp11 + tmp_col] & 0xFF;
/*  276 */             float s0 = s00 + (s01 - s00) * fracx;
/*  277 */             float s1 = s10 + (s11 - s10) * fracx;
/*  279 */             float tmp = s0 + (s1 - s0) * fracy;
/*  282 */             if (tmp < 0.5F) {
/*  283 */               s = 0;
/*  284 */             } else if (tmp > 254.5F) {
/*  285 */               s = 255;
/*      */             } else {
/*  287 */               s = (int)(tmp + 0.5F);
/*      */             } 
/*  291 */             dstDataArrays[k2][dstPixelOffset + dstBandOffsets[k2]] = (byte)(s & 0xFF);
/*      */           } 
/*  295 */         } else if (this.setBackground) {
/*  296 */           for (int k = 0; k < dst_num_bands; k++)
/*  297 */             dstDataArrays[k][dstPixelOffset + dstBandOffsets[k]] = backgroundByte[k]; 
/*      */         } 
/*  302 */         if (fracx < this.fracdx1) {
/*  303 */           s_ix += this.incx;
/*  304 */           fracx = (float)(fracx + this.fracdx);
/*      */         } else {
/*  306 */           s_ix += this.incx1;
/*  307 */           fracx = (float)(fracx - this.fracdx1);
/*      */         } 
/*  310 */         if (fracy < this.fracdy1) {
/*  311 */           s_iy += this.incy;
/*  312 */           fracy = (float)(fracy + this.fracdy);
/*      */         } else {
/*  314 */           s_iy += this.incy1;
/*  315 */           fracy = (float)(fracy - this.fracdy1);
/*      */         } 
/*  319 */         pylow = (s_iy - srcRectY) * srcScanlineStride;
/*  320 */         pxlow = (s_ix - srcRectX) * srcPixelStride;
/*  321 */         pyhigh = pylow + srcScanlineStride;
/*  322 */         pxhigh = pxlow + srcPixelStride;
/*  324 */         tmp00 = pxlow + pylow;
/*  325 */         tmp01 = pxhigh + pylow;
/*  326 */         tmp10 = pxlow + pyhigh;
/*  327 */         tmp11 = pxhigh + pyhigh;
/*  330 */         dstPixelOffset += dstPixelStride;
/*      */       } 
/*  334 */       dstOffset += dstScanlineStride;
/*      */     } 
/*      */   }
/*      */   
/*      */   private void intLoop(RasterAccessor src, Rectangle destRect, int srcRectX, int srcRectY, RasterAccessor dst) {
/*  344 */     float src_rect_x1 = src.getX();
/*  345 */     float src_rect_y1 = src.getY();
/*  346 */     float src_rect_x2 = src_rect_x1 + src.getWidth();
/*  347 */     float src_rect_y2 = src_rect_y1 + src.getHeight();
/*  360 */     int dstOffset = 0;
/*  362 */     Point2D dst_pt = new Point2D.Float();
/*  363 */     Point2D src_pt = new Point2D.Float();
/*  365 */     int dwidth = dst.getWidth();
/*  366 */     int dheight = dst.getHeight();
/*  367 */     int dnumBands = dst.getNumBands();
/*  369 */     int[][] dstDataArrays = dst.getIntDataArrays();
/*  370 */     int[] dstBandOffsets = dst.getBandOffsets();
/*  371 */     int dstPixelStride = dst.getPixelStride();
/*  372 */     int dstScanlineStride = dst.getScanlineStride();
/*  374 */     int[][] srcDataArrays = src.getIntDataArrays();
/*  375 */     int[] bandOffsets = src.getBandOffsets();
/*  376 */     int srcPixelStride = src.getPixelStride();
/*  377 */     int srcScanlineStride = src.getScanlineStride();
/*  379 */     int dst_num_bands = dst.getNumBands();
/*  381 */     int dst_min_x = destRect.x;
/*  382 */     int dst_min_y = destRect.y;
/*  383 */     int dst_max_x = destRect.x + destRect.width;
/*  384 */     int dst_max_y = destRect.y + destRect.height;
/*  386 */     int[] backgroundInt = new int[dst_num_bands];
/*  387 */     for (int i = 0; i < dst_num_bands; i++)
/*  388 */       backgroundInt[i] = (int)this.backgroundValues[i]; 
/*  390 */     for (int y = dst_min_y; y < dst_max_y; y++) {
/*  392 */       int dstPixelOffset = dstOffset;
/*  395 */       dst_pt.setLocation(dst_min_x + 0.5D, y + 0.5D);
/*  397 */       mapDestPoint(dst_pt, src_pt);
/*  400 */       float s_x = (float)src_pt.getX();
/*  401 */       float s_y = (float)src_pt.getY();
/*  404 */       s_x = (float)(s_x - 0.5D);
/*  405 */       s_y = (float)(s_y - 0.5D);
/*  408 */       int s_ix = (int)Math.floor(s_x);
/*  409 */       int s_iy = (int)Math.floor(s_y);
/*  411 */       float fracx = s_x - s_ix;
/*  412 */       float fracy = s_y - s_iy;
/*  415 */       int pylow = (s_iy - srcRectY) * srcScanlineStride;
/*  416 */       int pxlow = (s_ix - srcRectX) * srcPixelStride;
/*  417 */       int pyhigh = pylow + srcScanlineStride;
/*  418 */       int pxhigh = pxlow + srcPixelStride;
/*  420 */       int tmp00 = pxlow + pylow;
/*  421 */       int tmp01 = pxhigh + pylow;
/*  422 */       int tmp10 = pxlow + pyhigh;
/*  423 */       int tmp11 = pxhigh + pyhigh;
/*  425 */       for (int x = dst_min_x; x < dst_max_x; x++) {
/*  429 */         if (s_ix >= src_rect_x1 && s_ix < src_rect_x2 - 1.0F && s_iy >= src_rect_y1 && s_iy < src_rect_y2 - 1.0F) {
/*  433 */           for (int k2 = 0; k2 < dst_num_bands; k2++) {
/*  441 */             int s, tmp_row[] = srcDataArrays[k2];
/*  444 */             int tmp_col = bandOffsets[k2];
/*  446 */             int s00 = tmp_row[tmp00 + tmp_col];
/*  447 */             int s01 = tmp_row[tmp01 + tmp_col];
/*  448 */             int s10 = tmp_row[tmp10 + tmp_col];
/*  449 */             int s11 = tmp_row[tmp11 + tmp_col];
/*  452 */             float s0 = s00 + (s01 - s00) * fracx;
/*  453 */             float s1 = s10 + (s11 - s10) * fracx;
/*  455 */             float tmp = s0 + (s1 - s0) * fracy;
/*  458 */             if (tmp < -2.1474836E9F) {
/*  459 */               s = Integer.MIN_VALUE;
/*  460 */             } else if (tmp > 2.1474836E9F) {
/*  461 */               s = Integer.MAX_VALUE;
/*  462 */             } else if (tmp > 0.0F) {
/*  463 */               s = (int)(tmp + 0.5F);
/*      */             } else {
/*  465 */               s = (int)(tmp - 0.5F);
/*      */             } 
/*  469 */             dstDataArrays[k2][dstPixelOffset + dstBandOffsets[k2]] = s;
/*      */           } 
/*  472 */         } else if (this.setBackground) {
/*  473 */           for (int k = 0; k < dst_num_bands; k++)
/*  474 */             dstDataArrays[k][dstPixelOffset + dstBandOffsets[k]] = backgroundInt[k]; 
/*      */         } 
/*  479 */         if (fracx < this.fracdx1) {
/*  480 */           s_ix += this.incx;
/*  481 */           fracx = (float)(fracx + this.fracdx);
/*      */         } else {
/*  483 */           s_ix += this.incx1;
/*  484 */           fracx = (float)(fracx - this.fracdx1);
/*      */         } 
/*  487 */         if (fracy < this.fracdy1) {
/*  488 */           s_iy += this.incy;
/*  489 */           fracy = (float)(fracy + this.fracdy);
/*      */         } else {
/*  491 */           s_iy += this.incy1;
/*  492 */           fracy = (float)(fracy - this.fracdy1);
/*      */         } 
/*  496 */         pylow = (s_iy - srcRectY) * srcScanlineStride;
/*  497 */         pxlow = (s_ix - srcRectX) * srcPixelStride;
/*  498 */         pyhigh = pylow + srcScanlineStride;
/*  499 */         pxhigh = pxlow + srcPixelStride;
/*  501 */         tmp00 = pxlow + pylow;
/*  502 */         tmp01 = pxhigh + pylow;
/*  503 */         tmp10 = pxlow + pyhigh;
/*  504 */         tmp11 = pxhigh + pyhigh;
/*  506 */         dstPixelOffset += dstPixelStride;
/*      */       } 
/*  509 */       dstOffset += dstScanlineStride;
/*      */     } 
/*      */   }
/*      */   
/*      */   private void shortLoop(RasterAccessor src, Rectangle destRect, int srcRectX, int srcRectY, RasterAccessor dst) {
/*  519 */     float src_rect_x1 = src.getX();
/*  520 */     float src_rect_y1 = src.getY();
/*  521 */     float src_rect_x2 = src_rect_x1 + src.getWidth();
/*  522 */     float src_rect_y2 = src_rect_y1 + src.getHeight();
/*  535 */     int dstOffset = 0;
/*  537 */     Point2D dst_pt = new Point2D.Float();
/*  538 */     Point2D src_pt = new Point2D.Float();
/*  540 */     int dwidth = dst.getWidth();
/*  541 */     int dheight = dst.getHeight();
/*  542 */     int dnumBands = dst.getNumBands();
/*  544 */     short[][] dstDataArrays = dst.getShortDataArrays();
/*  545 */     int[] dstBandOffsets = dst.getBandOffsets();
/*  546 */     int dstPixelStride = dst.getPixelStride();
/*  547 */     int dstScanlineStride = dst.getScanlineStride();
/*  549 */     short[][] srcDataArrays = src.getShortDataArrays();
/*  550 */     int[] bandOffsets = src.getBandOffsets();
/*  551 */     int srcPixelStride = src.getPixelStride();
/*  552 */     int srcScanlineStride = src.getScanlineStride();
/*  554 */     int dst_num_bands = dst.getNumBands();
/*  556 */     int dst_min_x = destRect.x;
/*  557 */     int dst_min_y = destRect.y;
/*  558 */     int dst_max_x = destRect.x + destRect.width;
/*  559 */     int dst_max_y = destRect.y + destRect.height;
/*  561 */     short[] backgroundShort = new short[dst_num_bands];
/*  562 */     for (int i = 0; i < dst_num_bands; i++)
/*  563 */       backgroundShort[i] = (short)(int)this.backgroundValues[i]; 
/*  565 */     for (int y = dst_min_y; y < dst_max_y; y++) {
/*  567 */       int dstPixelOffset = dstOffset;
/*  570 */       dst_pt.setLocation(dst_min_x + 0.5D, y + 0.5D);
/*  572 */       mapDestPoint(dst_pt, src_pt);
/*  575 */       float s_x = (float)src_pt.getX();
/*  576 */       float s_y = (float)src_pt.getY();
/*  579 */       s_x = (float)(s_x - 0.5D);
/*  580 */       s_y = (float)(s_y - 0.5D);
/*  583 */       int s_ix = (int)Math.floor(s_x);
/*  584 */       int s_iy = (int)Math.floor(s_y);
/*  586 */       float fracx = s_x - s_ix;
/*  587 */       float fracy = s_y - s_iy;
/*  590 */       int pylow = (s_iy - srcRectY) * srcScanlineStride;
/*  591 */       int pxlow = (s_ix - srcRectX) * srcPixelStride;
/*  592 */       int pyhigh = pylow + srcScanlineStride;
/*  593 */       int pxhigh = pxlow + srcPixelStride;
/*  595 */       int tmp00 = pxlow + pylow;
/*  596 */       int tmp01 = pxhigh + pylow;
/*  597 */       int tmp10 = pxlow + pyhigh;
/*  598 */       int tmp11 = pxhigh + pyhigh;
/*  600 */       for (int x = dst_min_x; x < dst_max_x; x++) {
/*  604 */         if (s_ix >= src_rect_x1 && s_ix < src_rect_x2 - 1.0F && s_iy >= src_rect_y1 && s_iy < src_rect_y2 - 1.0F) {
/*  608 */           for (int k2 = 0; k2 < dst_num_bands; k2++) {
/*      */             int s;
/*  616 */             short[] tmp_row = srcDataArrays[k2];
/*  619 */             int tmp_col = bandOffsets[k2];
/*  621 */             int s00 = tmp_row[tmp00 + tmp_col];
/*  622 */             int s01 = tmp_row[tmp01 + tmp_col];
/*  623 */             int s10 = tmp_row[tmp10 + tmp_col];
/*  624 */             int s11 = tmp_row[tmp11 + tmp_col];
/*  627 */             float s0 = s00 + (s01 - s00) * fracx;
/*  628 */             float s1 = s10 + (s11 - s10) * fracx;
/*  629 */             float tmp = s0 + (s1 - s0) * fracy;
/*  632 */             if (tmp < -32768.0F) {
/*  633 */               s = -32768;
/*  634 */             } else if (tmp > 32767.0F) {
/*  635 */               s = 32767;
/*  636 */             } else if (tmp > 0.0F) {
/*  637 */               s = (int)(tmp + 0.5F);
/*      */             } else {
/*  639 */               s = (int)(tmp - 0.5F);
/*      */             } 
/*  643 */             dstDataArrays[k2][dstPixelOffset + dstBandOffsets[k2]] = (short)s;
/*      */           } 
/*  646 */         } else if (this.setBackground) {
/*  647 */           for (int k = 0; k < dst_num_bands; k++)
/*  648 */             dstDataArrays[k][dstPixelOffset + dstBandOffsets[k]] = backgroundShort[k]; 
/*      */         } 
/*  653 */         if (fracx < this.fracdx1) {
/*  654 */           s_ix += this.incx;
/*  655 */           fracx = (float)(fracx + this.fracdx);
/*      */         } else {
/*  657 */           s_ix += this.incx1;
/*  658 */           fracx = (float)(fracx - this.fracdx1);
/*      */         } 
/*  661 */         if (fracy < this.fracdy1) {
/*  662 */           s_iy += this.incy;
/*  663 */           fracy = (float)(fracy + this.fracdy);
/*      */         } else {
/*  665 */           s_iy += this.incy1;
/*  666 */           fracy = (float)(fracy - this.fracdy1);
/*      */         } 
/*  670 */         pylow = (s_iy - srcRectY) * srcScanlineStride;
/*  671 */         pxlow = (s_ix - srcRectX) * srcPixelStride;
/*  672 */         pyhigh = pylow + srcScanlineStride;
/*  673 */         pxhigh = pxlow + srcPixelStride;
/*  675 */         tmp00 = pxlow + pylow;
/*  676 */         tmp01 = pxhigh + pylow;
/*  677 */         tmp10 = pxlow + pyhigh;
/*  678 */         tmp11 = pxhigh + pyhigh;
/*  680 */         dstPixelOffset += dstPixelStride;
/*      */       } 
/*  683 */       dstOffset += dstScanlineStride;
/*      */     } 
/*      */   }
/*      */   
/*      */   private void ushortLoop(RasterAccessor src, Rectangle destRect, int srcRectX, int srcRectY, RasterAccessor dst) {
/*  693 */     float src_rect_x1 = src.getX();
/*  694 */     float src_rect_y1 = src.getY();
/*  695 */     float src_rect_x2 = src_rect_x1 + src.getWidth();
/*  696 */     float src_rect_y2 = src_rect_y1 + src.getHeight();
/*  709 */     int dstOffset = 0;
/*  711 */     Point2D dst_pt = new Point2D.Float();
/*  712 */     Point2D src_pt = new Point2D.Float();
/*  714 */     int dwidth = dst.getWidth();
/*  715 */     int dheight = dst.getHeight();
/*  716 */     int dnumBands = dst.getNumBands();
/*  718 */     short[][] dstDataArrays = dst.getShortDataArrays();
/*  719 */     int[] dstBandOffsets = dst.getBandOffsets();
/*  720 */     int dstPixelStride = dst.getPixelStride();
/*  721 */     int dstScanlineStride = dst.getScanlineStride();
/*  723 */     short[][] srcDataArrays = src.getShortDataArrays();
/*  724 */     int[] bandOffsets = src.getBandOffsets();
/*  725 */     int srcPixelStride = src.getPixelStride();
/*  726 */     int srcScanlineStride = src.getScanlineStride();
/*  728 */     int dst_num_bands = dst.getNumBands();
/*  730 */     int dst_min_x = destRect.x;
/*  731 */     int dst_min_y = destRect.y;
/*  732 */     int dst_max_x = destRect.x + destRect.width;
/*  733 */     int dst_max_y = destRect.y + destRect.height;
/*  735 */     short[] backgroundUShort = new short[dst_num_bands];
/*  736 */     for (int i = 0; i < dst_num_bands; i++)
/*  737 */       backgroundUShort[i] = (short)(int)this.backgroundValues[i]; 
/*  739 */     for (int y = dst_min_y; y < dst_max_y; y++) {
/*  741 */       int dstPixelOffset = dstOffset;
/*  744 */       dst_pt.setLocation(dst_min_x + 0.5D, y + 0.5D);
/*  746 */       mapDestPoint(dst_pt, src_pt);
/*  749 */       float s_x = (float)src_pt.getX();
/*  750 */       float s_y = (float)src_pt.getY();
/*  753 */       s_x = (float)(s_x - 0.5D);
/*  754 */       s_y = (float)(s_y - 0.5D);
/*  757 */       int s_ix = (int)Math.floor(s_x);
/*  758 */       int s_iy = (int)Math.floor(s_y);
/*  760 */       float fracx = s_x - s_ix;
/*  761 */       float fracy = s_y - s_iy;
/*  764 */       int pylow = (s_iy - srcRectY) * srcScanlineStride;
/*  765 */       int pxlow = (s_ix - srcRectX) * srcPixelStride;
/*  766 */       int pyhigh = pylow + srcScanlineStride;
/*  767 */       int pxhigh = pxlow + srcPixelStride;
/*  769 */       int tmp00 = pxlow + pylow;
/*  770 */       int tmp01 = pxhigh + pylow;
/*  771 */       int tmp10 = pxlow + pyhigh;
/*  772 */       int tmp11 = pxhigh + pyhigh;
/*  774 */       for (int x = dst_min_x; x < dst_max_x; x++) {
/*  778 */         if (s_ix >= src_rect_x1 && s_ix < src_rect_x2 - 1.0F && s_iy >= src_rect_y1 && s_iy < src_rect_y2 - 1.0F) {
/*  782 */           for (int k2 = 0; k2 < dst_num_bands; k2++) {
/*      */             int s;
/*  790 */             short[] tmp_row = srcDataArrays[k2];
/*  793 */             int tmp_col = bandOffsets[k2];
/*  795 */             int s00 = tmp_row[tmp00 + tmp_col] & 0xFFFF;
/*  796 */             int s01 = tmp_row[tmp01 + tmp_col] & 0xFFFF;
/*  797 */             int s10 = tmp_row[tmp10 + tmp_col] & 0xFFFF;
/*  798 */             int s11 = tmp_row[tmp11 + tmp_col] & 0xFFFF;
/*  801 */             float s0 = s00 + (s01 - s00) * fracx;
/*  802 */             float s1 = s10 + (s11 - s10) * fracx;
/*  803 */             float tmp = s0 + (s1 - s0) * fracy;
/*  806 */             if (tmp < 0.0D) {
/*  807 */               s = 0;
/*  808 */             } else if (tmp > 65535.0F) {
/*  809 */               s = 65535;
/*      */             } else {
/*  811 */               s = (int)(tmp + 0.5F);
/*      */             } 
/*  815 */             dstDataArrays[k2][dstPixelOffset + dstBandOffsets[k2]] = (short)(s & 0xFFFF);
/*      */           } 
/*  819 */         } else if (this.setBackground) {
/*  820 */           for (int k = 0; k < dst_num_bands; k++)
/*  821 */             dstDataArrays[k][dstPixelOffset + dstBandOffsets[k]] = backgroundUShort[k]; 
/*      */         } 
/*  826 */         if (fracx < this.fracdx1) {
/*  827 */           s_ix += this.incx;
/*  828 */           fracx = (float)(fracx + this.fracdx);
/*      */         } else {
/*  830 */           s_ix += this.incx1;
/*  831 */           fracx = (float)(fracx - this.fracdx1);
/*      */         } 
/*  834 */         if (fracy < this.fracdy1) {
/*  835 */           s_iy += this.incy;
/*  836 */           fracy = (float)(fracy + this.fracdy);
/*      */         } else {
/*  838 */           s_iy += this.incy1;
/*  839 */           fracy = (float)(fracy - this.fracdy1);
/*      */         } 
/*  843 */         pylow = (s_iy - srcRectY) * srcScanlineStride;
/*  844 */         pxlow = (s_ix - srcRectX) * srcPixelStride;
/*  845 */         pyhigh = pylow + srcScanlineStride;
/*  846 */         pxhigh = pxlow + srcPixelStride;
/*  848 */         tmp00 = pxlow + pylow;
/*  849 */         tmp01 = pxhigh + pylow;
/*  850 */         tmp10 = pxlow + pyhigh;
/*  851 */         tmp11 = pxhigh + pyhigh;
/*  853 */         dstPixelOffset += dstPixelStride;
/*      */       } 
/*  856 */       dstOffset += dstScanlineStride;
/*      */     } 
/*      */   }
/*      */   
/*      */   private void floatLoop(RasterAccessor src, Rectangle destRect, int srcRectX, int srcRectY, RasterAccessor dst) {
/*  866 */     float src_rect_x1 = src.getX();
/*  867 */     float src_rect_y1 = src.getY();
/*  868 */     float src_rect_x2 = src_rect_x1 + src.getWidth();
/*  869 */     float src_rect_y2 = src_rect_y1 + src.getHeight();
/*  881 */     int dstOffset = 0;
/*  883 */     Point2D dst_pt = new Point2D.Float();
/*  884 */     Point2D src_pt = new Point2D.Float();
/*  886 */     int dwidth = dst.getWidth();
/*  887 */     int dheight = dst.getHeight();
/*  888 */     int dnumBands = dst.getNumBands();
/*  890 */     float[][] dstDataArrays = dst.getFloatDataArrays();
/*  891 */     int[] dstBandOffsets = dst.getBandOffsets();
/*  892 */     int dstPixelStride = dst.getPixelStride();
/*  893 */     int dstScanlineStride = dst.getScanlineStride();
/*  895 */     float[][] srcDataArrays = src.getFloatDataArrays();
/*  896 */     int[] bandOffsets = src.getBandOffsets();
/*  897 */     int srcPixelStride = src.getPixelStride();
/*  898 */     int srcScanlineStride = src.getScanlineStride();
/*  900 */     int dst_num_bands = dst.getNumBands();
/*  902 */     int dst_min_x = destRect.x;
/*  903 */     int dst_min_y = destRect.y;
/*  904 */     int dst_max_x = destRect.x + destRect.width;
/*  905 */     int dst_max_y = destRect.y + destRect.height;
/*  907 */     float[] backgroundFloat = new float[dst_num_bands];
/*  908 */     for (int i = 0; i < dst_num_bands; i++)
/*  909 */       backgroundFloat[i] = (float)this.backgroundValues[i]; 
/*  911 */     for (int y = dst_min_y; y < dst_max_y; y++) {
/*  913 */       int dstPixelOffset = dstOffset;
/*  916 */       dst_pt.setLocation(dst_min_x + 0.5D, y + 0.5D);
/*  918 */       mapDestPoint(dst_pt, src_pt);
/*  921 */       float s_x = (float)src_pt.getX();
/*  922 */       float s_y = (float)src_pt.getY();
/*  925 */       s_x = (float)(s_x - 0.5D);
/*  926 */       s_y = (float)(s_y - 0.5D);
/*  929 */       int s_ix = (int)Math.floor(s_x);
/*  930 */       int s_iy = (int)Math.floor(s_y);
/*  932 */       float fracx = s_x - s_ix;
/*  933 */       float fracy = s_y - s_iy;
/*  936 */       int pylow = (s_iy - srcRectY) * srcScanlineStride;
/*  937 */       int pxlow = (s_ix - srcRectX) * srcPixelStride;
/*  938 */       int pyhigh = pylow + srcScanlineStride;
/*  939 */       int pxhigh = pxlow + srcPixelStride;
/*  941 */       int tmp00 = pxlow + pylow;
/*  942 */       int tmp01 = pxhigh + pylow;
/*  943 */       int tmp10 = pxlow + pyhigh;
/*  944 */       int tmp11 = pxhigh + pyhigh;
/*  946 */       for (int x = dst_min_x; x < dst_max_x; x++) {
/*  950 */         if (s_ix >= src_rect_x1 && s_ix < src_rect_x2 - 1.0F && s_iy >= src_rect_y1 && s_iy < src_rect_y2 - 1.0F) {
/*  954 */           for (int k2 = 0; k2 < dst_num_bands; k2++) {
/*  962 */             float[] tmp_row = srcDataArrays[k2];
/*  965 */             int tmp_col = bandOffsets[k2];
/*  967 */             float s00 = tmp_row[tmp00 + tmp_col];
/*  968 */             float s01 = tmp_row[tmp01 + tmp_col];
/*  969 */             float s10 = tmp_row[tmp10 + tmp_col];
/*  970 */             float s11 = tmp_row[tmp11 + tmp_col];
/*  973 */             float s0 = s00 + (s01 - s00) * fracx;
/*  974 */             float s1 = s10 + (s11 - s10) * fracx;
/*  975 */             float s = s0 + (s1 - s0) * fracy;
/*  978 */             dstDataArrays[k2][dstPixelOffset + dstBandOffsets[k2]] = s;
/*      */           } 
/*  981 */         } else if (this.setBackground) {
/*  982 */           for (int k = 0; k < dst_num_bands; k++)
/*  983 */             dstDataArrays[k][dstPixelOffset + dstBandOffsets[k]] = backgroundFloat[k]; 
/*      */         } 
/*  988 */         if (fracx < this.fracdx1) {
/*  989 */           s_ix += this.incx;
/*  990 */           fracx = (float)(fracx + this.fracdx);
/*      */         } else {
/*  992 */           s_ix += this.incx1;
/*  993 */           fracx = (float)(fracx - this.fracdx1);
/*      */         } 
/*  996 */         if (fracy < this.fracdy1) {
/*  997 */           s_iy += this.incy;
/*  998 */           fracy = (float)(fracy + this.fracdy);
/*      */         } else {
/* 1000 */           s_iy += this.incy1;
/* 1001 */           fracy = (float)(fracy - this.fracdy1);
/*      */         } 
/* 1005 */         pylow = (s_iy - srcRectY) * srcScanlineStride;
/* 1006 */         pxlow = (s_ix - srcRectX) * srcPixelStride;
/* 1007 */         pyhigh = pylow + srcScanlineStride;
/* 1008 */         pxhigh = pxlow + srcPixelStride;
/* 1010 */         tmp00 = pxlow + pylow;
/* 1011 */         tmp01 = pxhigh + pylow;
/* 1012 */         tmp10 = pxlow + pyhigh;
/* 1013 */         tmp11 = pxhigh + pyhigh;
/* 1015 */         dstPixelOffset += dstPixelStride;
/*      */       } 
/* 1018 */       dstOffset += dstScanlineStride;
/*      */     } 
/*      */   }
/*      */   
/*      */   private void doubleLoop(RasterAccessor src, Rectangle destRect, int srcRectX, int srcRectY, RasterAccessor dst) {
/* 1028 */     float src_rect_x1 = src.getX();
/* 1029 */     float src_rect_y1 = src.getY();
/* 1030 */     float src_rect_x2 = src_rect_x1 + src.getWidth();
/* 1031 */     float src_rect_y2 = src_rect_y1 + src.getHeight();
/* 1043 */     int dstOffset = 0;
/* 1045 */     Point2D dst_pt = new Point2D.Float();
/* 1046 */     Point2D src_pt = new Point2D.Float();
/* 1048 */     int dwidth = dst.getWidth();
/* 1049 */     int dheight = dst.getHeight();
/* 1050 */     int dnumBands = dst.getNumBands();
/* 1052 */     double[][] dstDataArrays = dst.getDoubleDataArrays();
/* 1053 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 1054 */     int dstPixelStride = dst.getPixelStride();
/* 1055 */     int dstScanlineStride = dst.getScanlineStride();
/* 1057 */     double[][] srcDataArrays = src.getDoubleDataArrays();
/* 1058 */     int[] bandOffsets = src.getBandOffsets();
/* 1059 */     int srcPixelStride = src.getPixelStride();
/* 1060 */     int srcScanlineStride = src.getScanlineStride();
/* 1062 */     int dst_num_bands = dst.getNumBands();
/* 1064 */     int dst_min_x = destRect.x;
/* 1065 */     int dst_min_y = destRect.y;
/* 1066 */     int dst_max_x = destRect.x + destRect.width;
/* 1067 */     int dst_max_y = destRect.y + destRect.height;
/* 1069 */     for (int y = dst_min_y; y < dst_max_y; y++) {
/* 1071 */       int dstPixelOffset = dstOffset;
/* 1074 */       dst_pt.setLocation(dst_min_x + 0.5D, y + 0.5D);
/* 1076 */       mapDestPoint(dst_pt, src_pt);
/* 1079 */       float s_x = (float)src_pt.getX();
/* 1080 */       float s_y = (float)src_pt.getY();
/* 1083 */       s_x = (float)(s_x - 0.5D);
/* 1084 */       s_y = (float)(s_y - 0.5D);
/* 1087 */       int s_ix = (int)Math.floor(s_x);
/* 1088 */       int s_iy = (int)Math.floor(s_y);
/* 1090 */       double fracx = (s_x - s_ix);
/* 1091 */       double fracy = (s_y - s_iy);
/* 1094 */       int pylow = (s_iy - srcRectY) * srcScanlineStride;
/* 1095 */       int pxlow = (s_ix - srcRectX) * srcPixelStride;
/* 1096 */       int pyhigh = pylow + srcScanlineStride;
/* 1097 */       int pxhigh = pxlow + srcPixelStride;
/* 1099 */       int tmp00 = pxlow + pylow;
/* 1100 */       int tmp01 = pxhigh + pylow;
/* 1101 */       int tmp10 = pxlow + pyhigh;
/* 1102 */       int tmp11 = pxhigh + pyhigh;
/* 1104 */       for (int x = dst_min_x; x < dst_max_x; x++) {
/* 1108 */         if (s_ix >= src_rect_x1 && s_ix < src_rect_x2 - 1.0F && s_iy >= src_rect_y1 && s_iy < src_rect_y2 - 1.0F) {
/* 1112 */           for (int k2 = 0; k2 < dst_num_bands; k2++) {
/* 1120 */             double[] tmp_row = srcDataArrays[k2];
/* 1123 */             int tmp_col = bandOffsets[k2];
/* 1125 */             double s00 = tmp_row[tmp00 + tmp_col];
/* 1126 */             double s01 = tmp_row[tmp01 + tmp_col];
/* 1127 */             double s10 = tmp_row[tmp10 + tmp_col];
/* 1128 */             double s11 = tmp_row[tmp11 + tmp_col];
/* 1131 */             double s0 = s00 + (s01 - s00) * fracx;
/* 1132 */             double s1 = s10 + (s11 - s10) * fracx;
/* 1133 */             double s = s0 + (s1 - s0) * fracy;
/* 1136 */             dstDataArrays[k2][dstPixelOffset + dstBandOffsets[k2]] = s;
/*      */           } 
/* 1139 */         } else if (this.setBackground) {
/* 1140 */           for (int k = 0; k < dst_num_bands; k++)
/* 1141 */             dstDataArrays[k][dstPixelOffset + dstBandOffsets[k]] = this.backgroundValues[k]; 
/*      */         } 
/* 1146 */         if (fracx < this.fracdx1) {
/* 1147 */           s_ix += this.incx;
/* 1148 */           fracx += this.fracdx;
/*      */         } else {
/* 1150 */           s_ix += this.incx1;
/* 1151 */           fracx -= this.fracdx1;
/*      */         } 
/* 1154 */         if (fracy < this.fracdy1) {
/* 1155 */           s_iy += this.incy;
/* 1156 */           fracy += this.fracdy;
/*      */         } else {
/* 1158 */           s_iy += this.incy1;
/* 1159 */           fracy -= this.fracdy1;
/*      */         } 
/* 1163 */         pylow = (s_iy - srcRectY) * srcScanlineStride;
/* 1164 */         pxlow = (s_ix - srcRectX) * srcPixelStride;
/* 1165 */         pyhigh = pylow + srcScanlineStride;
/* 1166 */         pxhigh = pxlow + srcPixelStride;
/* 1168 */         tmp00 = pxlow + pylow;
/* 1169 */         tmp01 = pxhigh + pylow;
/* 1170 */         tmp10 = pxlow + pyhigh;
/* 1171 */         tmp11 = pxhigh + pyhigh;
/* 1173 */         dstPixelOffset += dstPixelStride;
/*      */       } 
/* 1176 */       dstOffset += dstScanlineStride;
/*      */     } 
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\AffineBilinearOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */