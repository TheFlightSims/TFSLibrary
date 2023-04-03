/*      */ package com.sun.media.jai.opimage;
/*      */ 
/*      */ import com.sun.media.jai.util.Rational;
/*      */ import java.awt.Rectangle;
/*      */ import java.awt.image.Raster;
/*      */ import java.awt.image.RenderedImage;
/*      */ import java.awt.image.WritableRaster;
/*      */ import java.util.Map;
/*      */ import javax.media.jai.BorderExtender;
/*      */ import javax.media.jai.ImageLayout;
/*      */ import javax.media.jai.Interpolation;
/*      */ import javax.media.jai.InterpolationTable;
/*      */ import javax.media.jai.RasterAccessor;
/*      */ import javax.media.jai.RasterFormatTag;
/*      */ import javax.media.jai.ScaleOpImage;
/*      */ 
/*      */ final class ScaleBicubicOpImage extends ScaleOpImage {
/*      */   private int subsampleBits;
/*      */   
/*      */   private int one;
/*      */   
/*   47 */   private int[] tableDataHi = null;
/*      */   
/*   50 */   private int[] tableDataVi = null;
/*      */   
/*   53 */   private float[] tableDataHf = null;
/*      */   
/*   56 */   private float[] tableDataVf = null;
/*      */   
/*   59 */   private double[] tableDataHd = null;
/*      */   
/*   62 */   private double[] tableDataVd = null;
/*      */   
/*      */   private int precisionBits;
/*      */   
/*      */   private int round;
/*      */   
/*   70 */   private Rational half = new Rational(1L, 2L);
/*      */   
/*      */   InterpolationTable interpTable;
/*      */   
/*      */   long invScaleYInt;
/*      */   
/*      */   long invScaleYFrac;
/*      */   
/*      */   long invScaleXInt;
/*      */   
/*      */   long invScaleXFrac;
/*      */   
/*      */   public ScaleBicubicOpImage(RenderedImage source, BorderExtender extender, Map config, ImageLayout layout, float xScale, float yScale, float xTrans, float yTrans, Interpolation interp) {
/*  100 */     super(source, layout, config, true, extender, interp, xScale, yScale, xTrans, yTrans);
/*  111 */     this.subsampleBits = interp.getSubsampleBitsH();
/*  112 */     this.interpTable = (InterpolationTable)interp;
/*  115 */     this.one = 1 << this.subsampleBits;
/*  116 */     this.precisionBits = this.interpTable.getPrecisionBits();
/*  117 */     if (this.precisionBits > 0)
/*  118 */       this.round = 1 << this.precisionBits - 1; 
/*  121 */     if (this.invScaleYRational.num > this.invScaleYRational.denom) {
/*  122 */       this.invScaleYInt = this.invScaleYRational.num / this.invScaleYRational.denom;
/*  123 */       this.invScaleYFrac = this.invScaleYRational.num % this.invScaleYRational.denom;
/*      */     } else {
/*  125 */       this.invScaleYInt = 0L;
/*  126 */       this.invScaleYFrac = this.invScaleYRational.num;
/*      */     } 
/*  129 */     if (this.invScaleXRational.num > this.invScaleXRational.denom) {
/*  130 */       this.invScaleXInt = this.invScaleXRational.num / this.invScaleXRational.denom;
/*  131 */       this.invScaleXFrac = this.invScaleXRational.num % this.invScaleXRational.denom;
/*      */     } else {
/*  133 */       this.invScaleXInt = 0L;
/*  134 */       this.invScaleXFrac = this.invScaleXRational.num;
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void computeRect(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/*  151 */     RasterFormatTag[] formatTags = getFormatTags();
/*  153 */     Raster source = sources[0];
/*  155 */     Rectangle srcRect = source.getBounds();
/*  157 */     int srcRectX = srcRect.x;
/*  158 */     int srcRectY = srcRect.y;
/*  160 */     RasterAccessor srcAccessor = new RasterAccessor(source, srcRect, formatTags[0], getSource(0).getColorModel());
/*  164 */     RasterAccessor dstAccessor = new RasterAccessor(dest, destRect, formatTags[1], getColorModel());
/*  168 */     int dx = destRect.x;
/*  169 */     int dy = destRect.y;
/*  170 */     int dwidth = destRect.width;
/*  171 */     int dheight = destRect.height;
/*  172 */     int srcPixelStride = srcAccessor.getPixelStride();
/*  173 */     int srcScanlineStride = srcAccessor.getScanlineStride();
/*  175 */     int[] ypos = new int[dheight];
/*  176 */     int[] xpos = new int[dwidth];
/*  179 */     int[] yfracvalues = new int[dheight];
/*  181 */     int[] xfracvalues = new int[dwidth];
/*  183 */     long syNum = dy, syDenom = 1L;
/*  186 */     syNum = syNum * this.transYRationalDenom - this.transYRationalNum * syDenom;
/*  187 */     syDenom *= this.transYRationalDenom;
/*  190 */     syNum = 2L * syNum + syDenom;
/*  191 */     syDenom *= 2L;
/*  194 */     syNum *= this.invScaleYRationalNum;
/*  195 */     syDenom *= this.invScaleYRationalDenom;
/*  198 */     syNum = 2L * syNum - syDenom;
/*  199 */     syDenom *= 2L;
/*  202 */     int srcYInt = Rational.floor(syNum, syDenom);
/*  203 */     long srcYFrac = syNum % syDenom;
/*  204 */     if (srcYInt < 0)
/*  205 */       srcYFrac = syDenom + srcYFrac; 
/*  210 */     long commonYDenom = syDenom * this.invScaleYRationalDenom;
/*  211 */     srcYFrac *= this.invScaleYRationalDenom;
/*  212 */     long newInvScaleYFrac = this.invScaleYFrac * syDenom;
/*  214 */     long sxNum = dx, sxDenom = 1L;
/*  217 */     sxNum = sxNum * this.transXRationalDenom - this.transXRationalNum * sxDenom;
/*  218 */     sxDenom *= this.transXRationalDenom;
/*  221 */     sxNum = 2L * sxNum + sxDenom;
/*  222 */     sxDenom *= 2L;
/*  225 */     sxNum *= this.invScaleXRationalNum;
/*  226 */     sxDenom *= this.invScaleXRationalDenom;
/*  229 */     sxNum = 2L * sxNum - sxDenom;
/*  230 */     sxDenom *= 2L;
/*  234 */     int srcXInt = Rational.floor(sxNum, sxDenom);
/*  235 */     long srcXFrac = sxNum % sxDenom;
/*  236 */     if (srcXInt < 0)
/*  237 */       srcXFrac = sxDenom + srcXFrac; 
/*  242 */     long commonXDenom = sxDenom * this.invScaleXRationalDenom;
/*  243 */     srcXFrac *= this.invScaleXRationalDenom;
/*  244 */     long newInvScaleXFrac = this.invScaleXFrac * sxDenom;
/*      */     int i;
/*  246 */     for (i = 0; i < dwidth; i++) {
/*  247 */       xpos[i] = (srcXInt - srcRectX) * srcPixelStride;
/*  248 */       xfracvalues[i] = (int)((float)srcXFrac / (float)commonXDenom * this.one);
/*  254 */       srcXInt = (int)(srcXInt + this.invScaleXInt);
/*  258 */       srcXFrac += newInvScaleXFrac;
/*  263 */       if (srcXFrac >= commonXDenom) {
/*  264 */         srcXInt++;
/*  265 */         srcXFrac -= commonXDenom;
/*      */       } 
/*      */     } 
/*  269 */     for (i = 0; i < dheight; i++) {
/*  272 */       ypos[i] = (srcYInt - srcRectY) * srcScanlineStride;
/*  275 */       yfracvalues[i] = (int)((float)srcYFrac / (float)commonYDenom * this.one);
/*  281 */       srcYInt = (int)(srcYInt + this.invScaleYInt);
/*  285 */       srcYFrac += newInvScaleYFrac;
/*  290 */       if (srcYFrac >= commonYDenom) {
/*  291 */         srcYInt++;
/*  292 */         srcYFrac -= commonYDenom;
/*      */       } 
/*      */     } 
/*  296 */     switch (dstAccessor.getDataType()) {
/*      */       case 0:
/*  299 */         initTableDataI();
/*  300 */         byteLoop(srcAccessor, destRect, dstAccessor, xpos, ypos, xfracvalues, yfracvalues);
/*      */         break;
/*      */       case 2:
/*  305 */         initTableDataI();
/*  306 */         shortLoop(srcAccessor, destRect, dstAccessor, xpos, ypos, xfracvalues, yfracvalues);
/*      */         break;
/*      */       case 1:
/*  311 */         initTableDataI();
/*  312 */         ushortLoop(srcAccessor, destRect, dstAccessor, xpos, ypos, xfracvalues, yfracvalues);
/*      */         break;
/*      */       case 3:
/*  317 */         initTableDataI();
/*  318 */         intLoop(srcAccessor, destRect, dstAccessor, xpos, ypos, xfracvalues, yfracvalues);
/*      */         break;
/*      */       case 4:
/*  323 */         initTableDataF();
/*  324 */         floatLoop(srcAccessor, destRect, dstAccessor, xpos, ypos, xfracvalues, yfracvalues);
/*      */         break;
/*      */       case 5:
/*  329 */         initTableDataD();
/*  330 */         doubleLoop(srcAccessor, destRect, dstAccessor, xpos, ypos, xfracvalues, yfracvalues);
/*      */         break;
/*      */       default:
/*  335 */         throw new RuntimeException(JaiI18N.getString("OrderedDitherOpImage0"));
/*      */     } 
/*  342 */     if (dstAccessor.isDataCopy()) {
/*  343 */       dstAccessor.clampDataArrays();
/*  344 */       dstAccessor.copyDataToRaster();
/*      */     } 
/*      */   }
/*      */   
/*      */   private void byteLoop(RasterAccessor src, Rectangle destRect, RasterAccessor dst, int[] xpos, int[] ypos, int[] xfracvalues, int[] yfracvalues) {
/*  352 */     int srcPixelStride = src.getPixelStride();
/*  353 */     int srcScanlineStride = src.getScanlineStride();
/*  355 */     int dwidth = destRect.width;
/*  356 */     int dheight = destRect.height;
/*  357 */     int dnumBands = dst.getNumBands();
/*  358 */     byte[][] dstDataArrays = dst.getByteDataArrays();
/*  359 */     int[] dstBandOffsets = dst.getBandOffsets();
/*  360 */     int dstPixelStride = dst.getPixelStride();
/*  361 */     int dstScanlineStride = dst.getScanlineStride();
/*  363 */     byte[][] srcDataArrays = src.getByteDataArrays();
/*  364 */     int[] bandOffsets = src.getBandOffsets();
/*  373 */     int dstOffset = 0;
/*  376 */     for (int k = 0; k < dnumBands; k++) {
/*  377 */       byte[] dstData = dstDataArrays[k];
/*  378 */       byte[] srcData = srcDataArrays[k];
/*  379 */       int dstScanlineOffset = dstBandOffsets[k];
/*  380 */       int bandOffset = bandOffsets[k];
/*  382 */       for (int j = 0; j < dheight; j++) {
/*  383 */         int dstPixelOffset = dstScanlineOffset;
/*  384 */         int yfrac = yfracvalues[j];
/*  385 */         int posy = ypos[j] + bandOffset;
/*  386 */         int posylow = posy - srcScanlineStride;
/*  387 */         int posyhigh = posy + srcScanlineStride;
/*  388 */         int posyhigh2 = posyhigh + srcScanlineStride;
/*  389 */         for (int i = 0; i < dwidth; i++) {
/*  390 */           int xfrac = xfracvalues[i];
/*  391 */           int posx = xpos[i];
/*  392 */           int posxlow = posx - srcPixelStride;
/*  393 */           int posxhigh = posx + srcPixelStride;
/*  394 */           int posxhigh2 = posxhigh + srcPixelStride;
/*  397 */           int s__ = srcData[posxlow + posylow] & 0xFF;
/*  398 */           int s_0 = srcData[posx + posylow] & 0xFF;
/*  399 */           int s_1 = srcData[posxhigh + posylow] & 0xFF;
/*  400 */           int s_2 = srcData[posxhigh2 + posylow] & 0xFF;
/*  402 */           int s0_ = srcData[posxlow + posy] & 0xFF;
/*  403 */           int s00 = srcData[posx + posy] & 0xFF;
/*  404 */           int s01 = srcData[posxhigh + posy] & 0xFF;
/*  405 */           int s02 = srcData[posxhigh2 + posy] & 0xFF;
/*  407 */           int s1_ = srcData[posxlow + posyhigh] & 0xFF;
/*  408 */           int s10 = srcData[posx + posyhigh] & 0xFF;
/*  409 */           int s11 = srcData[posxhigh + posyhigh] & 0xFF;
/*  410 */           int s12 = srcData[posxhigh2 + posyhigh] & 0xFF;
/*  412 */           int s2_ = srcData[posxlow + posyhigh2] & 0xFF;
/*  413 */           int s20 = srcData[posx + posyhigh2] & 0xFF;
/*  414 */           int s21 = srcData[posxhigh + posyhigh2] & 0xFF;
/*  415 */           int s22 = srcData[posxhigh2 + posyhigh2] & 0xFF;
/*  418 */           int offsetX = 4 * xfrac;
/*  419 */           int offsetX1 = offsetX + 1;
/*  420 */           int offsetX2 = offsetX + 2;
/*  421 */           int offsetX3 = offsetX + 3;
/*  423 */           long sum_ = this.tableDataHi[offsetX] * s__;
/*  424 */           sum_ += this.tableDataHi[offsetX1] * s_0;
/*  425 */           sum_ += this.tableDataHi[offsetX2] * s_1;
/*  426 */           sum_ += this.tableDataHi[offsetX3] * s_2;
/*  428 */           long sum0 = this.tableDataHi[offsetX] * s0_;
/*  429 */           sum0 += this.tableDataHi[offsetX1] * s00;
/*  430 */           sum0 += this.tableDataHi[offsetX2] * s01;
/*  431 */           sum0 += this.tableDataHi[offsetX3] * s02;
/*  433 */           long sum1 = this.tableDataHi[offsetX] * s1_;
/*  434 */           sum1 += this.tableDataHi[offsetX1] * s10;
/*  435 */           sum1 += this.tableDataHi[offsetX2] * s11;
/*  436 */           sum1 += this.tableDataHi[offsetX3] * s12;
/*  438 */           long sum2 = this.tableDataHi[offsetX] * s2_;
/*  439 */           sum2 += this.tableDataHi[offsetX1] * s20;
/*  440 */           sum2 += this.tableDataHi[offsetX2] * s21;
/*  441 */           sum2 += this.tableDataHi[offsetX3] * s22;
/*  444 */           sum_ = sum_ + this.round >> this.precisionBits;
/*  445 */           sum0 = sum0 + this.round >> this.precisionBits;
/*  446 */           sum1 = sum1 + this.round >> this.precisionBits;
/*  447 */           sum2 = sum2 + this.round >> this.precisionBits;
/*  450 */           int offsetY = 4 * yfrac;
/*  451 */           long sum = this.tableDataVi[offsetY] * sum_;
/*  452 */           sum += this.tableDataVi[offsetY + 1] * sum0;
/*  453 */           sum += this.tableDataVi[offsetY + 2] * sum1;
/*  454 */           sum += this.tableDataVi[offsetY + 3] * sum2;
/*  456 */           int s = (int)(sum + this.round >> this.precisionBits);
/*  459 */           if (s > 255) {
/*  460 */             s = 255;
/*  461 */           } else if (s < 0) {
/*  462 */             s = 0;
/*      */           } 
/*  465 */           dstData[dstPixelOffset] = (byte)(s & 0xFF);
/*  466 */           dstPixelOffset += dstPixelStride;
/*      */         } 
/*  468 */         dstScanlineOffset += dstScanlineStride;
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void shortLoop(RasterAccessor src, Rectangle destRect, RasterAccessor dst, int[] xpos, int[] ypos, int[] xfracvalues, int[] yfracvalues) {
/*  477 */     int srcPixelStride = src.getPixelStride();
/*  478 */     int srcScanlineStride = src.getScanlineStride();
/*  480 */     int dwidth = destRect.width;
/*  481 */     int dheight = destRect.height;
/*  482 */     int dnumBands = dst.getNumBands();
/*  483 */     short[][] dstDataArrays = dst.getShortDataArrays();
/*  484 */     int[] dstBandOffsets = dst.getBandOffsets();
/*  485 */     int dstPixelStride = dst.getPixelStride();
/*  486 */     int dstScanlineStride = dst.getScanlineStride();
/*  488 */     short[][] srcDataArrays = src.getShortDataArrays();
/*  489 */     int[] bandOffsets = src.getBandOffsets();
/*  491 */     int dstOffset = 0;
/*  503 */     for (int k = 0; k < dnumBands; k++) {
/*  504 */       short[] dstData = dstDataArrays[k];
/*  505 */       short[] srcData = srcDataArrays[k];
/*  506 */       int dstScanlineOffset = dstBandOffsets[k];
/*  507 */       int bandOffset = bandOffsets[k];
/*  509 */       for (int j = 0; j < dheight; j++) {
/*  510 */         int dstPixelOffset = dstScanlineOffset;
/*  511 */         int yfrac = yfracvalues[j];
/*  512 */         int posy = ypos[j] + bandOffset;
/*  513 */         int posylow = posy - srcScanlineStride;
/*  514 */         int posyhigh = posy + srcScanlineStride;
/*  515 */         int posyhigh2 = posyhigh + srcScanlineStride;
/*  516 */         for (int i = 0; i < dwidth; i++) {
/*  517 */           int xfrac = xfracvalues[i];
/*  518 */           int posx = xpos[i];
/*  519 */           int posxlow = posx - srcPixelStride;
/*  520 */           int posxhigh = posx + srcPixelStride;
/*  521 */           int posxhigh2 = posxhigh + srcPixelStride;
/*  524 */           int s__ = srcData[posxlow + posylow];
/*  525 */           int s_0 = srcData[posx + posylow];
/*  526 */           int s_1 = srcData[posxhigh + posylow];
/*  527 */           int s_2 = srcData[posxhigh2 + posylow];
/*  529 */           int s0_ = srcData[posxlow + posy];
/*  530 */           int s00 = srcData[posx + posy];
/*  531 */           int s01 = srcData[posxhigh + posy];
/*  532 */           int s02 = srcData[posxhigh2 + posy];
/*  534 */           int s1_ = srcData[posxlow + posyhigh];
/*  535 */           int s10 = srcData[posx + posyhigh];
/*  536 */           int s11 = srcData[posxhigh + posyhigh];
/*  537 */           int s12 = srcData[posxhigh2 + posyhigh];
/*  539 */           int s2_ = srcData[posxlow + posyhigh2];
/*  540 */           int s20 = srcData[posx + posyhigh2];
/*  541 */           int s21 = srcData[posxhigh + posyhigh2];
/*  542 */           int s22 = srcData[posxhigh2 + posyhigh2];
/*  545 */           int offsetX = 4 * xfrac;
/*  546 */           int offsetX1 = offsetX + 1;
/*  547 */           int offsetX2 = offsetX + 2;
/*  548 */           int offsetX3 = offsetX + 3;
/*  550 */           long sum_ = this.tableDataHi[offsetX] * s__;
/*  551 */           sum_ += this.tableDataHi[offsetX1] * s_0;
/*  552 */           sum_ += this.tableDataHi[offsetX2] * s_1;
/*  553 */           sum_ += this.tableDataHi[offsetX3] * s_2;
/*  555 */           long sum0 = this.tableDataHi[offsetX] * s0_;
/*  556 */           sum0 += this.tableDataHi[offsetX1] * s00;
/*  557 */           sum0 += this.tableDataHi[offsetX2] * s01;
/*  558 */           sum0 += this.tableDataHi[offsetX3] * s02;
/*  560 */           long sum1 = this.tableDataHi[offsetX] * s1_;
/*  561 */           sum1 += this.tableDataHi[offsetX1] * s10;
/*  562 */           sum1 += this.tableDataHi[offsetX2] * s11;
/*  563 */           sum1 += this.tableDataHi[offsetX3] * s12;
/*  565 */           long sum2 = this.tableDataHi[offsetX] * s2_;
/*  566 */           sum2 += this.tableDataHi[offsetX1] * s20;
/*  567 */           sum2 += this.tableDataHi[offsetX2] * s21;
/*  568 */           sum2 += this.tableDataHi[offsetX3] * s22;
/*  571 */           sum_ = sum_ + this.round >> this.precisionBits;
/*  572 */           sum0 = sum0 + this.round >> this.precisionBits;
/*  573 */           sum1 = sum1 + this.round >> this.precisionBits;
/*  574 */           sum2 = sum2 + this.round >> this.precisionBits;
/*  577 */           int offsetY = 4 * yfrac;
/*  578 */           long sum = this.tableDataVi[offsetY] * sum_;
/*  579 */           sum += this.tableDataVi[offsetY + 1] * sum0;
/*  580 */           sum += this.tableDataVi[offsetY + 2] * sum1;
/*  581 */           sum += this.tableDataVi[offsetY + 3] * sum2;
/*  583 */           int s = (int)(sum + this.round >> this.precisionBits);
/*  586 */           if (s > 32767) {
/*  587 */             s = 32767;
/*  588 */           } else if (s < -32768) {
/*  589 */             s = -32768;
/*      */           } 
/*  592 */           dstData[dstPixelOffset] = (short)s;
/*  593 */           dstPixelOffset += dstPixelStride;
/*      */         } 
/*  595 */         dstScanlineOffset += dstScanlineStride;
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void ushortLoop(RasterAccessor src, Rectangle destRect, RasterAccessor dst, int[] xpos, int[] ypos, int[] xfracvalues, int[] yfracvalues) {
/*  604 */     int srcPixelStride = src.getPixelStride();
/*  605 */     int srcScanlineStride = src.getScanlineStride();
/*  607 */     int dwidth = destRect.width;
/*  608 */     int dheight = destRect.height;
/*  609 */     int dnumBands = dst.getNumBands();
/*  610 */     short[][] dstDataArrays = dst.getShortDataArrays();
/*  611 */     int[] dstBandOffsets = dst.getBandOffsets();
/*  612 */     int dstPixelStride = dst.getPixelStride();
/*  613 */     int dstScanlineStride = dst.getScanlineStride();
/*  615 */     short[][] srcDataArrays = src.getShortDataArrays();
/*  616 */     int[] bandOffsets = src.getBandOffsets();
/*  618 */     int dstOffset = 0;
/*  630 */     for (int k = 0; k < dnumBands; k++) {
/*  631 */       short[] dstData = dstDataArrays[k];
/*  632 */       short[] srcData = srcDataArrays[k];
/*  633 */       int dstScanlineOffset = dstBandOffsets[k];
/*  634 */       int bandOffset = bandOffsets[k];
/*  636 */       for (int j = 0; j < dheight; j++) {
/*  637 */         int dstPixelOffset = dstScanlineOffset;
/*  638 */         int yfrac = yfracvalues[j];
/*  639 */         int posy = ypos[j] + bandOffset;
/*  640 */         int posylow = posy - srcScanlineStride;
/*  641 */         int posyhigh = posy + srcScanlineStride;
/*  642 */         int posyhigh2 = posyhigh + srcScanlineStride;
/*  643 */         for (int i = 0; i < dwidth; i++) {
/*  644 */           int xfrac = xfracvalues[i];
/*  645 */           int posx = xpos[i];
/*  646 */           int posxlow = posx - srcPixelStride;
/*  647 */           int posxhigh = posx + srcPixelStride;
/*  648 */           int posxhigh2 = posxhigh + srcPixelStride;
/*  651 */           int s__ = srcData[posxlow + posylow] & 0xFFFF;
/*  652 */           int s_0 = srcData[posx + posylow] & 0xFFFF;
/*  653 */           int s_1 = srcData[posxhigh + posylow] & 0xFFFF;
/*  654 */           int s_2 = srcData[posxhigh2 + posylow] & 0xFFFF;
/*  656 */           int s0_ = srcData[posxlow + posy] & 0xFFFF;
/*  657 */           int s00 = srcData[posx + posy] & 0xFFFF;
/*  658 */           int s01 = srcData[posxhigh + posy] & 0xFFFF;
/*  659 */           int s02 = srcData[posxhigh2 + posy] & 0xFFFF;
/*  661 */           int s1_ = srcData[posxlow + posyhigh] & 0xFFFF;
/*  662 */           int s10 = srcData[posx + posyhigh] & 0xFFFF;
/*  663 */           int s11 = srcData[posxhigh + posyhigh] & 0xFFFF;
/*  664 */           int s12 = srcData[posxhigh2 + posyhigh] & 0xFFFF;
/*  666 */           int s2_ = srcData[posxlow + posyhigh2] & 0xFFFF;
/*  667 */           int s20 = srcData[posx + posyhigh2] & 0xFFFF;
/*  668 */           int s21 = srcData[posxhigh + posyhigh2] & 0xFFFF;
/*  669 */           int s22 = srcData[posxhigh2 + posyhigh2] & 0xFFFF;
/*  672 */           int offsetX = 4 * xfrac;
/*  673 */           int offsetX1 = offsetX + 1;
/*  674 */           int offsetX2 = offsetX + 2;
/*  675 */           int offsetX3 = offsetX + 3;
/*  677 */           long sum_ = this.tableDataHi[offsetX] * s__;
/*  678 */           sum_ += this.tableDataHi[offsetX1] * s_0;
/*  679 */           sum_ += this.tableDataHi[offsetX2] * s_1;
/*  680 */           sum_ += this.tableDataHi[offsetX3] * s_2;
/*  682 */           long sum0 = this.tableDataHi[offsetX] * s0_;
/*  683 */           sum0 += this.tableDataHi[offsetX1] * s00;
/*  684 */           sum0 += this.tableDataHi[offsetX2] * s01;
/*  685 */           sum0 += this.tableDataHi[offsetX3] * s02;
/*  687 */           long sum1 = this.tableDataHi[offsetX] * s1_;
/*  688 */           sum1 += this.tableDataHi[offsetX1] * s10;
/*  689 */           sum1 += this.tableDataHi[offsetX2] * s11;
/*  690 */           sum1 += this.tableDataHi[offsetX3] * s12;
/*  692 */           long sum2 = this.tableDataHi[offsetX] * s2_;
/*  693 */           sum2 += this.tableDataHi[offsetX1] * s20;
/*  694 */           sum2 += this.tableDataHi[offsetX2] * s21;
/*  695 */           sum2 += this.tableDataHi[offsetX3] * s22;
/*  698 */           sum_ = sum_ + this.round >> this.precisionBits;
/*  699 */           sum0 = sum0 + this.round >> this.precisionBits;
/*  700 */           sum1 = sum1 + this.round >> this.precisionBits;
/*  701 */           sum2 = sum2 + this.round >> this.precisionBits;
/*  704 */           int offsetY = 4 * yfrac;
/*  705 */           long sum = this.tableDataVi[offsetY] * sum_;
/*  706 */           sum += this.tableDataVi[offsetY + 1] * sum0;
/*  707 */           sum += this.tableDataVi[offsetY + 2] * sum1;
/*  708 */           sum += this.tableDataVi[offsetY + 3] * sum2;
/*  710 */           int s = (int)(sum + this.round >> this.precisionBits);
/*  713 */           if (s > 65536) {
/*  714 */             s = 65536;
/*  715 */           } else if (s < 0) {
/*  716 */             s = 0;
/*      */           } 
/*  719 */           dstData[dstPixelOffset] = (short)(s & 0xFFFF);
/*  720 */           dstPixelOffset += dstPixelStride;
/*      */         } 
/*  722 */         dstScanlineOffset += dstScanlineStride;
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void intLoop(RasterAccessor src, Rectangle destRect, RasterAccessor dst, int[] xpos, int[] ypos, int[] xfracvalues, int[] yfracvalues) {
/*  733 */     int srcPixelStride = src.getPixelStride();
/*  734 */     int srcScanlineStride = src.getScanlineStride();
/*  736 */     int dwidth = destRect.width;
/*  737 */     int dheight = destRect.height;
/*  738 */     int dnumBands = dst.getNumBands();
/*  739 */     int[][] dstDataArrays = dst.getIntDataArrays();
/*  740 */     int[] dstBandOffsets = dst.getBandOffsets();
/*  741 */     int dstPixelStride = dst.getPixelStride();
/*  742 */     int dstScanlineStride = dst.getScanlineStride();
/*  744 */     int[][] srcDataArrays = src.getIntDataArrays();
/*  745 */     int[] bandOffsets = src.getBandOffsets();
/*  747 */     int dstOffset = 0;
/*  759 */     for (int k = 0; k < dnumBands; k++) {
/*  760 */       int[] dstData = dstDataArrays[k];
/*  761 */       int[] srcData = srcDataArrays[k];
/*  762 */       int dstScanlineOffset = dstBandOffsets[k];
/*  763 */       int bandOffset = bandOffsets[k];
/*  765 */       for (int j = 0; j < dheight; j++) {
/*  766 */         int dstPixelOffset = dstScanlineOffset;
/*  767 */         long yfrac = yfracvalues[j];
/*  768 */         int posy = ypos[j] + bandOffset;
/*  769 */         int posylow = posy - srcScanlineStride;
/*  770 */         int posyhigh = posy + srcScanlineStride;
/*  771 */         int posyhigh2 = posyhigh + srcScanlineStride;
/*  772 */         for (int i = 0; i < dwidth; i++) {
/*  773 */           long xfrac = xfracvalues[i];
/*  774 */           int posx = xpos[i];
/*  775 */           int posxlow = posx - srcPixelStride;
/*  776 */           int posxhigh = posx + srcPixelStride;
/*  777 */           int posxhigh2 = posxhigh + srcPixelStride;
/*  780 */           int s__ = srcData[posxlow + posylow];
/*  781 */           int s_0 = srcData[posx + posylow];
/*  782 */           int s_1 = srcData[posxhigh + posylow];
/*  783 */           int s_2 = srcData[posxhigh2 + posylow];
/*  785 */           int s0_ = srcData[posxlow + posy];
/*  786 */           int s00 = srcData[posx + posy];
/*  787 */           int s01 = srcData[posxhigh + posy];
/*  788 */           int s02 = srcData[posxhigh2 + posy];
/*  790 */           int s1_ = srcData[posxlow + posyhigh];
/*  791 */           int s10 = srcData[posx + posyhigh];
/*  792 */           int s11 = srcData[posxhigh + posyhigh];
/*  793 */           int s12 = srcData[posxhigh2 + posyhigh];
/*  795 */           int s2_ = srcData[posxlow + posyhigh2];
/*  796 */           int s20 = srcData[posx + posyhigh2];
/*  797 */           int s21 = srcData[posxhigh + posyhigh2];
/*  798 */           int s22 = srcData[posxhigh2 + posyhigh2];
/*  801 */           int offsetX = (int)(4L * xfrac);
/*  802 */           int offsetX1 = offsetX + 1;
/*  803 */           int offsetX2 = offsetX + 2;
/*  804 */           int offsetX3 = offsetX + 3;
/*  806 */           long sum_ = this.tableDataHi[offsetX] * s__;
/*  807 */           sum_ += this.tableDataHi[offsetX1] * s_0;
/*  808 */           sum_ += this.tableDataHi[offsetX2] * s_1;
/*  809 */           sum_ += this.tableDataHi[offsetX3] * s_2;
/*  811 */           long sum0 = this.tableDataHi[offsetX] * s0_;
/*  812 */           sum0 += this.tableDataHi[offsetX1] * s00;
/*  813 */           sum0 += this.tableDataHi[offsetX2] * s01;
/*  814 */           sum0 += this.tableDataHi[offsetX3] * s02;
/*  816 */           long sum1 = this.tableDataHi[offsetX] * s1_;
/*  817 */           sum1 += this.tableDataHi[offsetX1] * s10;
/*  818 */           sum1 += this.tableDataHi[offsetX2] * s11;
/*  819 */           sum1 += this.tableDataHi[offsetX3] * s12;
/*  821 */           long sum2 = this.tableDataHi[offsetX] * s2_;
/*  822 */           sum2 += this.tableDataHi[offsetX1] * s20;
/*  823 */           sum2 += this.tableDataHi[offsetX2] * s21;
/*  824 */           sum2 += this.tableDataHi[offsetX3] * s22;
/*  827 */           sum_ = sum_ + this.round >> this.precisionBits;
/*  828 */           sum0 = sum0 + this.round >> this.precisionBits;
/*  829 */           sum1 = sum1 + this.round >> this.precisionBits;
/*  830 */           sum2 = sum2 + this.round >> this.precisionBits;
/*  833 */           int offsetY = (int)(4L * yfrac);
/*  834 */           long sum = this.tableDataVi[offsetY] * sum_;
/*  835 */           sum += this.tableDataVi[offsetY + 1] * sum0;
/*  836 */           sum += this.tableDataVi[offsetY + 2] * sum1;
/*  837 */           sum += this.tableDataVi[offsetY + 3] * sum2;
/*  839 */           int s = (int)(sum + this.round >> this.precisionBits);
/*  841 */           dstData[dstPixelOffset] = s;
/*  842 */           dstPixelOffset += dstPixelStride;
/*      */         } 
/*  844 */         dstScanlineOffset += dstScanlineStride;
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void floatLoop(RasterAccessor src, Rectangle destRect, RasterAccessor dst, int[] xpos, int[] ypos, int[] xfracvalues, int[] yfracvalues) {
/*  853 */     int srcPixelStride = src.getPixelStride();
/*  854 */     int srcScanlineStride = src.getScanlineStride();
/*  856 */     int dwidth = destRect.width;
/*  857 */     int dheight = destRect.height;
/*  858 */     int dnumBands = dst.getNumBands();
/*  859 */     float[][] dstDataArrays = dst.getFloatDataArrays();
/*  860 */     int[] dstBandOffsets = dst.getBandOffsets();
/*  861 */     int dstPixelStride = dst.getPixelStride();
/*  862 */     int dstScanlineStride = dst.getScanlineStride();
/*  864 */     float[][] srcDataArrays = src.getFloatDataArrays();
/*  865 */     int[] bandOffsets = src.getBandOffsets();
/*  867 */     int dstOffset = 0;
/*  879 */     for (int k = 0; k < dnumBands; k++) {
/*  880 */       float[] dstData = dstDataArrays[k];
/*  881 */       float[] srcData = srcDataArrays[k];
/*  882 */       int dstScanlineOffset = dstBandOffsets[k];
/*  883 */       int bandOffset = bandOffsets[k];
/*  885 */       for (int j = 0; j < dheight; j++) {
/*  886 */         int dstPixelOffset = dstScanlineOffset;
/*  887 */         int yfrac = yfracvalues[j];
/*  888 */         int posy = ypos[j] + bandOffset;
/*  889 */         int posylow = posy - srcScanlineStride;
/*  890 */         int posyhigh = posy + srcScanlineStride;
/*  891 */         int posyhigh2 = posyhigh + srcScanlineStride;
/*  892 */         for (int i = 0; i < dwidth; i++) {
/*  893 */           int xfrac = xfracvalues[i];
/*  894 */           int posx = xpos[i];
/*  895 */           int posxlow = posx - srcPixelStride;
/*  896 */           int posxhigh = posx + srcPixelStride;
/*  897 */           int posxhigh2 = posxhigh + srcPixelStride;
/*  900 */           float s__ = srcData[posxlow + posylow];
/*  901 */           float s_0 = srcData[posx + posylow];
/*  902 */           float s_1 = srcData[posxhigh + posylow];
/*  903 */           float s_2 = srcData[posxhigh2 + posylow];
/*  905 */           float s0_ = srcData[posxlow + posy];
/*  906 */           float s00 = srcData[posx + posy];
/*  907 */           float s01 = srcData[posxhigh + posy];
/*  908 */           float s02 = srcData[posxhigh2 + posy];
/*  910 */           float s1_ = srcData[posxlow + posyhigh];
/*  911 */           float s10 = srcData[posx + posyhigh];
/*  912 */           float s11 = srcData[posxhigh + posyhigh];
/*  913 */           float s12 = srcData[posxhigh2 + posyhigh];
/*  915 */           float s2_ = srcData[posxlow + posyhigh2];
/*  916 */           float s20 = srcData[posx + posyhigh2];
/*  917 */           float s21 = srcData[posxhigh + posyhigh2];
/*  918 */           float s22 = srcData[posxhigh2 + posyhigh2];
/*  923 */           int offsetX = 4 * xfrac;
/*  924 */           int offsetX1 = offsetX + 1;
/*  925 */           int offsetX2 = offsetX + 2;
/*  926 */           int offsetX3 = offsetX + 3;
/*  928 */           double sum_ = (this.tableDataHf[offsetX] * s__);
/*  929 */           sum_ += (this.tableDataHf[offsetX1] * s_0);
/*  930 */           sum_ += (this.tableDataHf[offsetX2] * s_1);
/*  931 */           sum_ += (this.tableDataHf[offsetX3] * s_2);
/*  933 */           double sum0 = (this.tableDataHf[offsetX] * s0_);
/*  934 */           sum0 += (this.tableDataHf[offsetX1] * s00);
/*  935 */           sum0 += (this.tableDataHf[offsetX2] * s01);
/*  936 */           sum0 += (this.tableDataHf[offsetX3] * s02);
/*  938 */           double sum1 = (this.tableDataHf[offsetX] * s1_);
/*  939 */           sum1 += (this.tableDataHf[offsetX1] * s10);
/*  940 */           sum1 += (this.tableDataHf[offsetX2] * s11);
/*  941 */           sum1 += (this.tableDataHf[offsetX3] * s12);
/*  943 */           double sum2 = (this.tableDataHf[offsetX] * s2_);
/*  944 */           sum2 += (this.tableDataHf[offsetX1] * s20);
/*  945 */           sum2 += (this.tableDataHf[offsetX2] * s21);
/*  946 */           sum2 += (this.tableDataHf[offsetX3] * s22);
/*  949 */           int offsetY = 4 * yfrac;
/*  951 */           double sum = this.tableDataVf[offsetY] * sum_;
/*  952 */           sum += this.tableDataVf[offsetY + 1] * sum0;
/*  953 */           sum += this.tableDataVf[offsetY + 2] * sum1;
/*  954 */           sum += this.tableDataVf[offsetY + 3] * sum2;
/*  956 */           if (sum > 3.4028234663852886E38D) {
/*  957 */             sum = 3.4028234663852886E38D;
/*  958 */           } else if (sum < -3.4028234663852886E38D) {
/*  959 */             sum = -3.4028234663852886E38D;
/*      */           } 
/*  962 */           dstData[dstPixelOffset] = (float)sum;
/*  963 */           dstPixelOffset += dstPixelStride;
/*      */         } 
/*  965 */         dstScanlineOffset += dstScanlineStride;
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void doubleLoop(RasterAccessor src, Rectangle destRect, RasterAccessor dst, int[] xpos, int[] ypos, int[] xfracvalues, int[] yfracvalues) {
/*  974 */     int srcPixelStride = src.getPixelStride();
/*  975 */     int srcScanlineStride = src.getScanlineStride();
/*  977 */     int dwidth = destRect.width;
/*  978 */     int dheight = destRect.height;
/*  979 */     int dnumBands = dst.getNumBands();
/*  980 */     double[][] dstDataArrays = dst.getDoubleDataArrays();
/*  981 */     int[] dstBandOffsets = dst.getBandOffsets();
/*  982 */     int dstPixelStride = dst.getPixelStride();
/*  983 */     int dstScanlineStride = dst.getScanlineStride();
/*  985 */     double[][] srcDataArrays = src.getDoubleDataArrays();
/*  986 */     int[] bandOffsets = src.getBandOffsets();
/*  988 */     int dstOffset = 0;
/* 1000 */     for (int k = 0; k < dnumBands; k++) {
/* 1001 */       double[] dstData = dstDataArrays[k];
/* 1002 */       double[] srcData = srcDataArrays[k];
/* 1003 */       int dstScanlineOffset = dstBandOffsets[k];
/* 1004 */       int bandOffset = bandOffsets[k];
/* 1006 */       for (int j = 0; j < dheight; j++) {
/* 1007 */         int dstPixelOffset = dstScanlineOffset;
/* 1008 */         int yfrac = yfracvalues[j];
/* 1009 */         int posy = ypos[j] + bandOffset;
/* 1010 */         int posylow = posy - srcScanlineStride;
/* 1011 */         int posyhigh = posy + srcScanlineStride;
/* 1012 */         int posyhigh2 = posyhigh + srcScanlineStride;
/* 1013 */         for (int i = 0; i < dwidth; i++) {
/* 1014 */           int xfrac = xfracvalues[i];
/* 1015 */           int posx = xpos[i];
/* 1016 */           int posxlow = posx - srcPixelStride;
/* 1017 */           int posxhigh = posx + srcPixelStride;
/* 1018 */           int posxhigh2 = posxhigh + srcPixelStride;
/* 1021 */           double s__ = srcData[posxlow + posylow];
/* 1022 */           double s_0 = srcData[posx + posylow];
/* 1023 */           double s_1 = srcData[posxhigh + posylow];
/* 1024 */           double s_2 = srcData[posxhigh2 + posylow];
/* 1026 */           double s0_ = srcData[posxlow + posy];
/* 1027 */           double s00 = srcData[posx + posy];
/* 1028 */           double s01 = srcData[posxhigh + posy];
/* 1029 */           double s02 = srcData[posxhigh2 + posy];
/* 1031 */           double s1_ = srcData[posxlow + posyhigh];
/* 1032 */           double s10 = srcData[posx + posyhigh];
/* 1033 */           double s11 = srcData[posxhigh + posyhigh];
/* 1034 */           double s12 = srcData[posxhigh2 + posyhigh];
/* 1036 */           double s2_ = srcData[posxlow + posyhigh2];
/* 1037 */           double s20 = srcData[posx + posyhigh2];
/* 1038 */           double s21 = srcData[posxhigh + posyhigh2];
/* 1039 */           double s22 = srcData[posxhigh2 + posyhigh2];
/* 1044 */           int offsetX = 4 * xfrac;
/* 1045 */           int offsetX1 = offsetX + 1;
/* 1046 */           int offsetX2 = offsetX + 2;
/* 1047 */           int offsetX3 = offsetX + 3;
/* 1049 */           double sum_ = this.tableDataHd[offsetX] * s__;
/* 1050 */           sum_ += this.tableDataHd[offsetX1] * s_0;
/* 1051 */           sum_ += this.tableDataHd[offsetX2] * s_1;
/* 1052 */           sum_ += this.tableDataHd[offsetX3] * s_2;
/* 1054 */           double sum0 = this.tableDataHd[offsetX] * s0_;
/* 1055 */           sum0 += this.tableDataHd[offsetX1] * s00;
/* 1056 */           sum0 += this.tableDataHd[offsetX2] * s01;
/* 1057 */           sum0 += this.tableDataHd[offsetX3] * s02;
/* 1059 */           double sum1 = this.tableDataHd[offsetX] * s1_;
/* 1060 */           sum1 += this.tableDataHd[offsetX1] * s10;
/* 1061 */           sum1 += this.tableDataHd[offsetX2] * s11;
/* 1062 */           sum1 += this.tableDataHd[offsetX3] * s12;
/* 1064 */           double sum2 = this.tableDataHd[offsetX] * s2_;
/* 1065 */           sum2 += this.tableDataHd[offsetX1] * s20;
/* 1066 */           sum2 += this.tableDataHd[offsetX2] * s21;
/* 1067 */           sum2 += this.tableDataHd[offsetX3] * s22;
/* 1070 */           int offsetY = 4 * yfrac;
/* 1071 */           double s = this.tableDataVd[offsetY] * sum_;
/* 1072 */           s += this.tableDataVd[offsetY + 1] * sum0;
/* 1073 */           s += this.tableDataVd[offsetY + 2] * sum1;
/* 1074 */           s += this.tableDataVd[offsetY + 3] * sum2;
/* 1076 */           dstData[dstPixelOffset] = s;
/* 1077 */           dstPixelOffset += dstPixelStride;
/*      */         } 
/* 1079 */         dstScanlineOffset += dstScanlineStride;
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private synchronized void initTableDataI() {
/* 1085 */     if (this.tableDataHi == null || this.tableDataVi == null) {
/* 1086 */       this.tableDataHi = this.interpTable.getHorizontalTableData();
/* 1087 */       this.tableDataVi = this.interpTable.getVerticalTableData();
/*      */     } 
/*      */   }
/*      */   
/*      */   private synchronized void initTableDataF() {
/* 1092 */     if (this.tableDataHf == null || this.tableDataVf == null) {
/* 1093 */       this.tableDataHf = this.interpTable.getHorizontalTableDataFloat();
/* 1094 */       this.tableDataVf = this.interpTable.getVerticalTableDataFloat();
/*      */     } 
/*      */   }
/*      */   
/*      */   private synchronized void initTableDataD() {
/* 1099 */     if (this.tableDataHd == null || this.tableDataVd == null) {
/* 1100 */       this.tableDataHd = this.interpTable.getHorizontalTableDataDouble();
/* 1101 */       this.tableDataVd = this.interpTable.getVerticalTableDataDouble();
/*      */     } 
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\ScaleBicubicOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */