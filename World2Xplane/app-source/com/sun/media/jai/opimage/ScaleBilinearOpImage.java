/*     */ package com.sun.media.jai.opimage;
/*     */ 
/*     */ import com.sun.media.jai.util.Rational;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.util.Map;
/*     */ import javax.media.jai.BorderExtender;
/*     */ import javax.media.jai.ImageLayout;
/*     */ import javax.media.jai.Interpolation;
/*     */ import javax.media.jai.RasterAccessor;
/*     */ import javax.media.jai.RasterFormatTag;
/*     */ import javax.media.jai.ScaleOpImage;
/*     */ 
/*     */ final class ScaleBilinearOpImage extends ScaleOpImage {
/*     */   private int subsampleBits;
/*     */   
/*     */   int one;
/*     */   
/*     */   int shift2;
/*     */   
/*     */   int round2;
/*     */   
/*  44 */   Rational half = new Rational(1L, 2L);
/*     */   
/*     */   long invScaleYInt;
/*     */   
/*     */   long invScaleYFrac;
/*     */   
/*     */   long invScaleXInt;
/*     */   
/*     */   long invScaleXFrac;
/*     */   
/*     */   public ScaleBilinearOpImage(RenderedImage source, BorderExtender extender, Map config, ImageLayout layout, float xScale, float yScale, float xTrans, float yTrans, Interpolation interp) {
/*  70 */     super(source, layout, config, true, extender, interp, xScale, yScale, xTrans, yTrans);
/*  81 */     this.subsampleBits = interp.getSubsampleBitsH();
/*  84 */     this.one = 1 << this.subsampleBits;
/*  87 */     this.shift2 = 2 * this.subsampleBits;
/*  88 */     this.round2 = 1 << this.shift2 - 1;
/*  90 */     if (this.invScaleYRational.num > this.invScaleYRational.denom) {
/*  91 */       this.invScaleYInt = this.invScaleYRational.num / this.invScaleYRational.denom;
/*  92 */       this.invScaleYFrac = this.invScaleYRational.num % this.invScaleYRational.denom;
/*     */     } else {
/*  94 */       this.invScaleYInt = 0L;
/*  95 */       this.invScaleYFrac = this.invScaleYRational.num;
/*     */     } 
/*  98 */     if (this.invScaleXRational.num > this.invScaleXRational.denom) {
/*  99 */       this.invScaleXInt = this.invScaleXRational.num / this.invScaleXRational.denom;
/* 100 */       this.invScaleXFrac = this.invScaleXRational.num % this.invScaleXRational.denom;
/*     */     } else {
/* 102 */       this.invScaleXInt = 0L;
/* 103 */       this.invScaleXFrac = this.invScaleXRational.num;
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void computeRect(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/* 120 */     RasterFormatTag[] formatTags = getFormatTags();
/* 122 */     Raster source = sources[0];
/* 124 */     Rectangle srcRect = source.getBounds();
/* 126 */     RasterAccessor srcAccessor = new RasterAccessor(source, srcRect, formatTags[0], getSource(0).getColorModel());
/* 130 */     RasterAccessor dstAccessor = new RasterAccessor(dest, destRect, formatTags[1], getColorModel());
/* 133 */     int dwidth = destRect.width;
/* 134 */     int dheight = destRect.height;
/* 135 */     int srcPixelStride = srcAccessor.getPixelStride();
/* 136 */     int srcScanlineStride = srcAccessor.getScanlineStride();
/* 138 */     int[] ypos = new int[dheight];
/* 139 */     int[] xpos = new int[dwidth];
/* 141 */     int[] xfracvalues = null, yfracvalues = null;
/* 142 */     float[] xfracvaluesFloat = null, yfracvaluesFloat = null;
/* 144 */     switch (dstAccessor.getDataType()) {
/*     */       case 0:
/*     */       case 1:
/*     */       case 2:
/*     */       case 3:
/* 149 */         yfracvalues = new int[dheight];
/* 150 */         xfracvalues = new int[dwidth];
/* 151 */         preComputePositionsInt(destRect, srcRect.x, srcRect.y, srcPixelStride, srcScanlineStride, xpos, ypos, xfracvalues, yfracvalues);
/*     */         break;
/*     */       case 4:
/*     */       case 5:
/* 158 */         yfracvaluesFloat = new float[dheight];
/* 159 */         xfracvaluesFloat = new float[dwidth];
/* 160 */         preComputePositionsFloat(destRect, srcRect.x, srcRect.y, srcPixelStride, srcScanlineStride, xpos, ypos, xfracvaluesFloat, yfracvaluesFloat);
/*     */         break;
/*     */       default:
/* 166 */         throw new RuntimeException(JaiI18N.getString("OrderedDitherOpImage0"));
/*     */     } 
/* 170 */     switch (dstAccessor.getDataType()) {
/*     */       case 0:
/* 172 */         byteLoop(srcAccessor, destRect, dstAccessor, xpos, ypos, xfracvalues, yfracvalues);
/*     */         break;
/*     */       case 2:
/* 177 */         shortLoop(srcAccessor, destRect, dstAccessor, xpos, ypos, xfracvalues, yfracvalues);
/*     */         break;
/*     */       case 1:
/* 182 */         ushortLoop(srcAccessor, destRect, dstAccessor, xpos, ypos, xfracvalues, yfracvalues);
/*     */         break;
/*     */       case 3:
/* 187 */         intLoop(srcAccessor, destRect, dstAccessor, xpos, ypos, xfracvalues, yfracvalues);
/*     */         break;
/*     */       case 4:
/* 192 */         floatLoop(srcAccessor, destRect, dstAccessor, xpos, ypos, xfracvaluesFloat, yfracvaluesFloat);
/*     */         break;
/*     */       case 5:
/* 197 */         doubleLoop(srcAccessor, destRect, dstAccessor, xpos, ypos, xfracvaluesFloat, yfracvaluesFloat);
/*     */         break;
/*     */     } 
/* 205 */     if (dstAccessor.isDataCopy()) {
/* 206 */       dstAccessor.clampDataArrays();
/* 207 */       dstAccessor.copyDataToRaster();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void preComputePositionsInt(Rectangle destRect, int srcRectX, int srcRectY, int srcPixelStride, int srcScanlineStride, int[] xpos, int[] ypos, int[] xfracvalues, int[] yfracvalues) {
/* 217 */     int dwidth = destRect.width;
/* 218 */     int dheight = destRect.height;
/* 221 */     int dx = destRect.x;
/* 222 */     int dy = destRect.y;
/* 224 */     long syNum = dy, syDenom = 1L;
/* 227 */     syNum = syNum * this.transYRationalDenom - this.transYRationalNum * syDenom;
/* 228 */     syDenom *= this.transYRationalDenom;
/* 231 */     syNum = 2L * syNum + syDenom;
/* 232 */     syDenom *= 2L;
/* 235 */     syNum *= this.invScaleYRationalNum;
/* 236 */     syDenom *= this.invScaleYRationalDenom;
/* 239 */     syNum = 2L * syNum - syDenom;
/* 240 */     syDenom *= 2L;
/* 243 */     int srcYInt = Rational.floor(syNum, syDenom);
/* 244 */     long srcYFrac = syNum % syDenom;
/* 245 */     if (srcYInt < 0)
/* 246 */       srcYFrac = syDenom + srcYFrac; 
/* 251 */     long commonYDenom = syDenom * this.invScaleYRationalDenom;
/* 252 */     srcYFrac *= this.invScaleYRationalDenom;
/* 253 */     long newInvScaleYFrac = this.invScaleYFrac * syDenom;
/* 256 */     long sxNum = dx, sxDenom = 1L;
/* 259 */     sxNum = sxNum * this.transXRationalDenom - this.transXRationalNum * sxDenom;
/* 260 */     sxDenom *= this.transXRationalDenom;
/* 263 */     sxNum = 2L * sxNum + sxDenom;
/* 264 */     sxDenom *= 2L;
/* 267 */     sxNum *= this.invScaleXRationalNum;
/* 268 */     sxDenom *= this.invScaleXRationalDenom;
/* 271 */     sxNum = 2L * sxNum - sxDenom;
/* 272 */     sxDenom *= 2L;
/* 275 */     int srcXInt = Rational.floor(sxNum, sxDenom);
/* 276 */     long srcXFrac = sxNum % sxDenom;
/* 277 */     if (srcXInt < 0)
/* 278 */       srcXFrac = sxDenom + srcXFrac; 
/* 283 */     long commonXDenom = sxDenom * this.invScaleXRationalDenom;
/* 284 */     srcXFrac *= this.invScaleXRationalDenom;
/* 285 */     long newInvScaleXFrac = this.invScaleXFrac * sxDenom;
/*     */     int i;
/* 287 */     for (i = 0; i < dwidth; i++) {
/* 288 */       xpos[i] = (srcXInt - srcRectX) * srcPixelStride;
/* 289 */       xfracvalues[i] = (int)((float)srcXFrac / (float)commonXDenom * this.one);
/* 295 */       srcXInt = (int)(srcXInt + this.invScaleXInt);
/* 299 */       srcXFrac += newInvScaleXFrac;
/* 304 */       if (srcXFrac >= commonXDenom) {
/* 305 */         srcXInt++;
/* 306 */         srcXFrac -= commonXDenom;
/*     */       } 
/*     */     } 
/* 310 */     for (i = 0; i < dheight; i++) {
/* 313 */       ypos[i] = (srcYInt - srcRectY) * srcScanlineStride;
/* 316 */       yfracvalues[i] = (int)((float)srcYFrac / (float)commonYDenom * this.one);
/* 322 */       srcYInt = (int)(srcYInt + this.invScaleYInt);
/* 326 */       srcYFrac += newInvScaleYFrac;
/* 331 */       if (srcYFrac >= commonYDenom) {
/* 332 */         srcYInt++;
/* 333 */         srcYFrac -= commonYDenom;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void preComputePositionsFloat(Rectangle destRect, int srcRectX, int srcRectY, int srcPixelStride, int srcScanlineStride, int[] xpos, int[] ypos, float[] xfracvaluesFloat, float[] yfracvaluesFloat) {
/* 344 */     int dwidth = destRect.width;
/* 345 */     int dheight = destRect.height;
/* 348 */     int dx = destRect.x;
/* 349 */     int dy = destRect.y;
/* 351 */     long syNum = dy, syDenom = 1L;
/* 354 */     syNum = syNum * this.transYRationalDenom - this.transYRationalNum * syDenom;
/* 355 */     syDenom *= this.transYRationalDenom;
/* 358 */     syNum = 2L * syNum + syDenom;
/* 359 */     syDenom *= 2L;
/* 362 */     syNum *= this.invScaleYRationalNum;
/* 363 */     syDenom *= this.invScaleYRationalDenom;
/* 366 */     syNum = 2L * syNum - syDenom;
/* 367 */     syDenom *= 2L;
/* 370 */     int srcYInt = Rational.floor(syNum, syDenom);
/* 371 */     long srcYFrac = syNum % syDenom;
/* 372 */     if (srcYInt < 0)
/* 373 */       srcYFrac = syDenom + srcYFrac; 
/* 378 */     long commonYDenom = syDenom * this.invScaleYRationalDenom;
/* 379 */     srcYFrac *= this.invScaleYRationalDenom;
/* 380 */     long newInvScaleYFrac = this.invScaleYFrac * syDenom;
/* 383 */     long sxNum = dx, sxDenom = 1L;
/* 386 */     sxNum = sxNum * this.transXRationalDenom - this.transXRationalNum * sxDenom;
/* 387 */     sxDenom *= this.transXRationalDenom;
/* 390 */     sxNum = 2L * sxNum + sxDenom;
/* 391 */     sxDenom *= 2L;
/* 394 */     sxNum *= this.invScaleXRationalNum;
/* 395 */     sxDenom *= this.invScaleXRationalDenom;
/* 398 */     sxNum = 2L * sxNum - sxDenom;
/* 399 */     sxDenom *= 2L;
/* 402 */     int srcXInt = Rational.floor(sxNum, sxDenom);
/* 403 */     long srcXFrac = sxNum % sxDenom;
/* 404 */     if (srcXInt < 0)
/* 405 */       srcXFrac = sxDenom + srcXFrac; 
/* 410 */     long commonXDenom = sxDenom * this.invScaleXRationalDenom;
/* 411 */     srcXFrac *= this.invScaleXRationalDenom;
/* 412 */     long newInvScaleXFrac = this.invScaleXFrac * sxDenom;
/*     */     int i;
/* 414 */     for (i = 0; i < dwidth; i++) {
/* 416 */       xpos[i] = (srcXInt - srcRectX) * srcPixelStride;
/* 417 */       xfracvaluesFloat[i] = (float)srcXFrac / (float)commonXDenom;
/* 423 */       srcXInt = (int)(srcXInt + this.invScaleXInt);
/* 427 */       srcXFrac += newInvScaleXFrac;
/* 432 */       if (srcXFrac >= commonXDenom) {
/* 433 */         srcXInt++;
/* 434 */         srcXFrac -= commonXDenom;
/*     */       } 
/*     */     } 
/* 438 */     for (i = 0; i < dheight; i++) {
/* 441 */       ypos[i] = (srcYInt - srcRectY) * srcScanlineStride;
/* 444 */       yfracvaluesFloat[i] = (float)srcYFrac / (float)commonYDenom;
/* 450 */       srcYInt = (int)(srcYInt + this.invScaleYInt);
/* 454 */       srcYFrac += newInvScaleYFrac;
/* 459 */       if (srcYFrac >= commonYDenom) {
/* 460 */         srcYInt++;
/* 461 */         srcYFrac -= commonYDenom;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void byteLoop(RasterAccessor src, Rectangle dstRect, RasterAccessor dst, int[] xpos, int[] ypos, int[] xfracvalues, int[] yfracvalues) {
/* 471 */     int srcPixelStride = src.getPixelStride();
/* 472 */     int srcScanlineStride = src.getScanlineStride();
/* 473 */     int srcLastXDataPos = (src.getWidth() - 1) * srcPixelStride;
/* 475 */     int dwidth = dstRect.width;
/* 476 */     int dheight = dstRect.height;
/* 477 */     int dnumBands = dst.getNumBands();
/* 478 */     byte[][] dstDataArrays = dst.getByteDataArrays();
/* 479 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 480 */     int dstPixelStride = dst.getPixelStride();
/* 481 */     int dstScanlineStride = dst.getScanlineStride();
/* 483 */     byte[][] srcDataArrays = src.getByteDataArrays();
/* 484 */     int[] bandOffsets = src.getBandOffsets();
/* 486 */     int dstOffset = 0;
/* 513 */     for (int k = 0; k < dnumBands; k++) {
/* 514 */       byte[] dstData = dstDataArrays[k];
/* 515 */       byte[] srcData = srcDataArrays[k];
/* 516 */       int dstScanlineOffset = dstBandOffsets[k];
/* 517 */       int bandOffset = bandOffsets[k];
/* 518 */       for (int j = 0; j < dheight; j++) {
/* 520 */         int dstPixelOffset = dstScanlineOffset;
/* 521 */         int yfrac = yfracvalues[j];
/* 522 */         int posylow = ypos[j] + bandOffset;
/* 523 */         int posyhigh = posylow + srcScanlineStride;
/* 525 */         for (int i = 0; i < dwidth; i++) {
/* 526 */           int xfrac = xfracvalues[i];
/* 527 */           int posxlow = xpos[i];
/* 528 */           int posxhigh = posxlow + srcPixelStride;
/* 531 */           int s00 = srcData[posxlow + posylow] & 0xFF;
/* 532 */           int s01 = srcData[posxhigh + posylow] & 0xFF;
/* 533 */           int s10 = srcData[posxlow + posyhigh] & 0xFF;
/* 534 */           int s11 = srcData[posxhigh + posyhigh] & 0xFF;
/* 537 */           int s0 = (s01 - s00) * xfrac + (s00 << this.subsampleBits);
/* 538 */           int s1 = (s11 - s10) * xfrac + (s10 << this.subsampleBits);
/* 539 */           int s = (s1 - s0) * yfrac + (s0 << this.subsampleBits) + this.round2 >> this.shift2;
/* 542 */           dstData[dstPixelOffset] = (byte)(s & 0xFF);
/* 543 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/* 545 */         dstScanlineOffset += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void shortLoop(RasterAccessor src, Rectangle dstRect, RasterAccessor dst, int[] xpos, int[] ypos, int[] xfracvalues, int[] yfracvalues) {
/* 555 */     int srcPixelStride = src.getPixelStride();
/* 556 */     int srcScanlineStride = src.getScanlineStride();
/* 557 */     int srcLastXDataPos = (src.getWidth() - 1) * srcPixelStride;
/* 559 */     int dwidth = dstRect.width;
/* 560 */     int dheight = dstRect.height;
/* 561 */     int dnumBands = dst.getNumBands();
/* 562 */     short[][] dstDataArrays = dst.getShortDataArrays();
/* 563 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 564 */     int dstPixelStride = dst.getPixelStride();
/* 565 */     int dstScanlineStride = dst.getScanlineStride();
/* 567 */     short[][] srcDataArrays = src.getShortDataArrays();
/* 568 */     int[] bandOffsets = src.getBandOffsets();
/* 570 */     int dstOffset = 0;
/* 576 */     for (int k = 0; k < dnumBands; k++) {
/* 577 */       short[] dstData = dstDataArrays[k];
/* 578 */       short[] srcData = srcDataArrays[k];
/* 579 */       int dstScanlineOffset = dstBandOffsets[k];
/* 580 */       int bandOffset = bandOffsets[k];
/* 581 */       for (int j = 0; j < dheight; j++) {
/* 582 */         int dstPixelOffset = dstScanlineOffset;
/* 583 */         int yfrac = yfracvalues[j];
/* 584 */         int posylow = ypos[j] + bandOffset;
/* 585 */         int posyhigh = posylow + srcScanlineStride;
/* 587 */         for (int i = 0; i < dwidth; i++) {
/* 588 */           int xfrac = xfracvalues[i];
/* 589 */           int posxlow = xpos[i];
/* 590 */           int posxhigh = posxlow + srcPixelStride;
/* 593 */           int s00 = srcData[posxlow + posylow];
/* 594 */           int s01 = srcData[posxhigh + posylow];
/* 595 */           int s10 = srcData[posxlow + posyhigh];
/* 596 */           int s11 = srcData[posxhigh + posyhigh];
/* 599 */           int s0 = (s01 - s00) * xfrac + (s00 << this.subsampleBits);
/* 600 */           int s1 = (s11 - s10) * xfrac + (s10 << this.subsampleBits);
/* 601 */           int s = (s1 - s0) * yfrac + (s0 << this.subsampleBits) + this.round2 >> this.shift2;
/* 604 */           dstData[dstPixelOffset] = (short)s;
/* 605 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/* 607 */         dstScanlineOffset += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void ushortLoop(RasterAccessor src, Rectangle dstRect, RasterAccessor dst, int[] xpos, int[] ypos, int[] xfracvalues, int[] yfracvalues) {
/* 616 */     int srcPixelStride = src.getPixelStride();
/* 617 */     int srcScanlineStride = src.getScanlineStride();
/* 618 */     int srcLastXDataPos = (src.getWidth() - 1) * srcPixelStride;
/* 620 */     int dwidth = dstRect.width;
/* 621 */     int dheight = dstRect.height;
/* 622 */     int dnumBands = dst.getNumBands();
/* 623 */     short[][] dstDataArrays = dst.getShortDataArrays();
/* 624 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 625 */     int dstPixelStride = dst.getPixelStride();
/* 626 */     int dstScanlineStride = dst.getScanlineStride();
/* 628 */     short[][] srcDataArrays = src.getShortDataArrays();
/* 629 */     int[] bandOffsets = src.getBandOffsets();
/* 631 */     int dstOffset = 0;
/* 637 */     for (int k = 0; k < dnumBands; k++) {
/* 638 */       short[] dstData = dstDataArrays[k];
/* 639 */       short[] srcData = srcDataArrays[k];
/* 640 */       int dstScanlineOffset = dstBandOffsets[k];
/* 641 */       int bandOffset = bandOffsets[k];
/* 642 */       for (int j = 0; j < dheight; j++) {
/* 643 */         int dstPixelOffset = dstScanlineOffset;
/* 644 */         int yfrac = yfracvalues[j];
/* 645 */         int posylow = ypos[j] + bandOffset;
/* 646 */         int posyhigh = posylow + srcScanlineStride;
/* 648 */         for (int i = 0; i < dwidth; i++) {
/* 649 */           int xfrac = xfracvalues[i];
/* 650 */           int posxlow = xpos[i];
/* 651 */           int posxhigh = posxlow + srcPixelStride;
/* 654 */           int s00 = srcData[posxlow + posylow] & 0xFFFF;
/* 655 */           int s01 = srcData[posxhigh + posylow] & 0xFFFF;
/* 656 */           int s10 = srcData[posxlow + posyhigh] & 0xFFFF;
/* 657 */           int s11 = srcData[posxhigh + posyhigh] & 0xFFFF;
/* 660 */           int s0 = (s01 - s00) * xfrac + (s00 << this.subsampleBits);
/* 661 */           int s1 = (s11 - s10) * xfrac + (s10 << this.subsampleBits);
/* 662 */           int s = (s1 - s0) * yfrac + (s0 << this.subsampleBits) + this.round2 >> this.shift2;
/* 665 */           dstData[dstPixelOffset] = (short)(s & 0xFFFF);
/* 666 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/* 668 */         dstScanlineOffset += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void intLoop(RasterAccessor src, Rectangle dstRect, RasterAccessor dst, int[] xpos, int[] ypos, int[] xfracvalues, int[] yfracvalues) {
/* 679 */     int srcPixelStride = src.getPixelStride();
/* 680 */     int srcScanlineStride = src.getScanlineStride();
/* 681 */     int srcLastXDataPos = (src.getWidth() - 1) * srcPixelStride;
/* 683 */     int dwidth = dstRect.width;
/* 684 */     int dheight = dstRect.height;
/* 685 */     int dnumBands = dst.getNumBands();
/* 686 */     int[][] dstDataArrays = dst.getIntDataArrays();
/* 687 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 688 */     int dstPixelStride = dst.getPixelStride();
/* 689 */     int dstScanlineStride = dst.getScanlineStride();
/* 691 */     int[][] srcDataArrays = src.getIntDataArrays();
/* 692 */     int[] bandOffsets = src.getBandOffsets();
/* 694 */     int dstOffset = 0;
/* 699 */     int shift = 29 - this.subsampleBits;
/* 702 */     for (int k = 0; k < dnumBands; k++) {
/* 703 */       int[] dstData = dstDataArrays[k];
/* 704 */       int[] srcData = srcDataArrays[k];
/* 705 */       int dstScanlineOffset = dstBandOffsets[k];
/* 706 */       int bandOffset = bandOffsets[k];
/* 707 */       for (int j = 0; j < dheight; j++) {
/* 708 */         int dstPixelOffset = dstScanlineOffset;
/* 709 */         int yfrac = yfracvalues[j];
/* 710 */         int posylow = ypos[j] + bandOffset;
/* 711 */         int posyhigh = posylow + srcScanlineStride;
/* 713 */         for (int i = 0; i < dwidth; i++) {
/*     */           long s0, s1;
/* 714 */           int xfrac = xfracvalues[i];
/* 715 */           int posxlow = xpos[i];
/* 716 */           int posxhigh = posxlow + srcPixelStride;
/* 719 */           int s00 = srcData[posxlow + posylow];
/* 720 */           int s01 = srcData[posxhigh + posylow];
/* 721 */           int s10 = srcData[posxlow + posyhigh];
/* 722 */           int s11 = srcData[posxhigh + posyhigh];
/* 725 */           if ((s00 | s10) >>> shift == 0) {
/* 726 */             if ((s01 | s11) >>> shift == 0) {
/* 727 */               s0 = ((s01 - s00) * xfrac + (s00 << this.subsampleBits));
/* 728 */               s1 = ((s11 - s10) * xfrac + (s10 << this.subsampleBits));
/*     */             } else {
/* 730 */               s0 = (s01 - s00) * xfrac + (s00 << this.subsampleBits);
/* 731 */               s1 = (s11 - s10) * xfrac + (s10 << this.subsampleBits);
/*     */             } 
/*     */           } else {
/* 734 */             s0 = (s01 - s00) * xfrac + (s00 << this.subsampleBits);
/* 735 */             s1 = (s11 - s10) * xfrac + (s10 << this.subsampleBits);
/*     */           } 
/* 738 */           dstData[dstPixelOffset] = (int)((s1 - s0) * yfrac + (s0 << this.subsampleBits) + this.round2 >> this.shift2);
/* 742 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/* 744 */         dstScanlineOffset += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void floatLoop(RasterAccessor src, Rectangle dstRect, RasterAccessor dst, int[] xpos, int[] ypos, float[] xfracvaluesFloat, float[] yfracvaluesFloat) {
/* 760 */     int srcPixelStride = src.getPixelStride();
/* 761 */     int srcScanlineStride = src.getScanlineStride();
/* 762 */     int srcLastXDataPos = (src.getWidth() - 1) * srcPixelStride;
/* 764 */     int dwidth = dstRect.width;
/* 765 */     int dheight = dstRect.height;
/* 766 */     int dnumBands = dst.getNumBands();
/* 767 */     float[][] dstDataArrays = dst.getFloatDataArrays();
/* 768 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 769 */     int dstPixelStride = dst.getPixelStride();
/* 770 */     int dstScanlineStride = dst.getScanlineStride();
/* 772 */     float[][] srcDataArrays = src.getFloatDataArrays();
/* 773 */     int[] bandOffsets = src.getBandOffsets();
/* 778 */     int dstOffset = 0;
/* 782 */     for (int k = 0; k < dnumBands; k++) {
/* 783 */       float[] dstData = dstDataArrays[k];
/* 784 */       float[] srcData = srcDataArrays[k];
/* 785 */       int dstScanlineOffset = dstBandOffsets[k];
/* 786 */       int bandOffset = bandOffsets[k];
/* 787 */       for (int j = 0; j < dheight; j++) {
/* 788 */         int dstPixelOffset = dstScanlineOffset;
/* 789 */         float yfrac = yfracvaluesFloat[j];
/* 790 */         int posylow = ypos[j] + bandOffset;
/* 791 */         int posyhigh = posylow + srcScanlineStride;
/* 793 */         for (int i = 0; i < dwidth; i++) {
/* 794 */           float xfrac = xfracvaluesFloat[i];
/* 795 */           int posxlow = xpos[i];
/* 796 */           int posxhigh = posxlow + srcPixelStride;
/* 799 */           float s00 = srcData[posxlow + posylow];
/* 800 */           float s01 = srcData[posxhigh + posylow];
/* 801 */           float s10 = srcData[posxlow + posyhigh];
/* 802 */           float s11 = srcData[posxhigh + posyhigh];
/* 805 */           float s0 = (s01 - s00) * xfrac + s00;
/* 806 */           float s1 = (s11 - s10) * xfrac + s10;
/* 808 */           dstData[dstPixelOffset] = (s1 - s0) * yfrac + s0;
/* 810 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/* 812 */         dstScanlineOffset += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void doubleLoop(RasterAccessor src, Rectangle dstRect, RasterAccessor dst, int[] xpos, int[] ypos, float[] xfracvaluesFloat, float[] yfracvaluesFloat) {
/* 821 */     int srcPixelStride = src.getPixelStride();
/* 822 */     int srcScanlineStride = src.getScanlineStride();
/* 823 */     int srcLastXDataPos = (src.getWidth() - 1) * srcPixelStride;
/* 825 */     int dwidth = dstRect.width;
/* 826 */     int dheight = dstRect.height;
/* 827 */     int dnumBands = dst.getNumBands();
/* 828 */     double[][] dstDataArrays = dst.getDoubleDataArrays();
/* 829 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 830 */     int dstPixelStride = dst.getPixelStride();
/* 831 */     int dstScanlineStride = dst.getScanlineStride();
/* 833 */     double[][] srcDataArrays = src.getDoubleDataArrays();
/* 834 */     int[] bandOffsets = src.getBandOffsets();
/* 839 */     int dstOffset = 0;
/* 843 */     for (int k = 0; k < dnumBands; k++) {
/* 844 */       double[] dstData = dstDataArrays[k];
/* 845 */       double[] srcData = srcDataArrays[k];
/* 846 */       int dstScanlineOffset = dstBandOffsets[k];
/* 847 */       int bandOffset = bandOffsets[k];
/* 848 */       for (int j = 0; j < dheight; j++) {
/* 849 */         int dstPixelOffset = dstScanlineOffset;
/* 850 */         double yfrac = yfracvaluesFloat[j];
/* 851 */         int posylow = ypos[j] + bandOffset;
/* 852 */         int posyhigh = posylow + srcScanlineStride;
/* 854 */         for (int i = 0; i < dwidth; i++) {
/* 855 */           double xfrac = xfracvaluesFloat[i];
/* 856 */           int posxlow = xpos[i];
/* 857 */           int posxhigh = posxlow + srcPixelStride;
/* 860 */           double s00 = srcData[posxlow + posylow];
/* 861 */           double s01 = srcData[posxhigh + posylow];
/* 862 */           double s10 = srcData[posxlow + posyhigh];
/* 863 */           double s11 = srcData[posxhigh + posyhigh];
/* 866 */           double s0 = (s01 - s00) * xfrac + s00;
/* 867 */           double s1 = (s11 - s10) * xfrac + s10;
/* 869 */           dstData[dstPixelOffset] = (s1 - s0) * yfrac + s0;
/* 871 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/* 873 */         dstScanlineOffset += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\ScaleBilinearOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */