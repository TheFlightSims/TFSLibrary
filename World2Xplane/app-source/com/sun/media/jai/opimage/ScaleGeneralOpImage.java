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
/*     */ final class ScaleGeneralOpImage extends ScaleOpImage {
/*     */   private int subsampleBits;
/*     */   
/*     */   private int one;
/*     */   
/*  42 */   Rational half = new Rational(1L, 2L);
/*     */   
/*     */   private int interp_width;
/*     */   
/*     */   private int interp_height;
/*     */   
/*     */   private int interp_left;
/*     */   
/*     */   private int interp_top;
/*     */   
/*     */   long invScaleYInt;
/*     */   
/*     */   long invScaleYFrac;
/*     */   
/*     */   long invScaleXInt;
/*     */   
/*     */   long invScaleXFrac;
/*     */   
/*     */   public ScaleGeneralOpImage(RenderedImage source, BorderExtender extender, Map config, ImageLayout layout, float xScale, float yScale, float xTrans, float yTrans, Interpolation interp) {
/*  74 */     super(source, layout, config, true, extender, interp, xScale, yScale, xTrans, yTrans);
/*  85 */     this.subsampleBits = interp.getSubsampleBitsH();
/*  88 */     this.one = 1 << this.subsampleBits;
/*  91 */     this.interp_width = interp.getWidth();
/*  92 */     this.interp_height = interp.getHeight();
/*  93 */     this.interp_left = interp.getLeftPadding();
/*  94 */     this.interp_top = interp.getTopPadding();
/*  96 */     if (this.invScaleYRational.num > this.invScaleYRational.denom) {
/*  97 */       this.invScaleYInt = this.invScaleYRational.num / this.invScaleYRational.denom;
/*  98 */       this.invScaleYFrac = this.invScaleYRational.num % this.invScaleYRational.denom;
/*     */     } else {
/* 100 */       this.invScaleYInt = 0L;
/* 101 */       this.invScaleYFrac = this.invScaleYRational.num;
/*     */     } 
/* 104 */     if (this.invScaleXRational.num > this.invScaleXRational.denom) {
/* 105 */       this.invScaleXInt = this.invScaleXRational.num / this.invScaleXRational.denom;
/* 106 */       this.invScaleXFrac = this.invScaleXRational.num % this.invScaleXRational.denom;
/*     */     } else {
/* 108 */       this.invScaleXInt = 0L;
/* 109 */       this.invScaleXFrac = this.invScaleXRational.num;
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void computeRect(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/* 126 */     RasterFormatTag[] formatTags = getFormatTags();
/* 128 */     Raster source = sources[0];
/* 130 */     Rectangle srcRect = source.getBounds();
/* 132 */     RasterAccessor srcAccessor = new RasterAccessor(source, srcRect, formatTags[0], getSource(0).getColorModel());
/* 136 */     RasterAccessor dstAccessor = new RasterAccessor(dest, destRect, formatTags[1], getColorModel());
/* 139 */     int dwidth = destRect.width;
/* 140 */     int dheight = destRect.height;
/* 141 */     int srcPixelStride = srcAccessor.getPixelStride();
/* 142 */     int srcScanlineStride = srcAccessor.getScanlineStride();
/* 144 */     int[] ypos = new int[dheight];
/* 145 */     int[] xpos = new int[dwidth];
/* 147 */     int[] xfracvalues = null, yfracvalues = null;
/* 148 */     float[] xfracvaluesFloat = null, yfracvaluesFloat = null;
/* 150 */     switch (dstAccessor.getDataType()) {
/*     */       case 0:
/*     */       case 1:
/*     */       case 2:
/*     */       case 3:
/* 155 */         yfracvalues = new int[dheight];
/* 156 */         xfracvalues = new int[dwidth];
/* 157 */         preComputePositionsInt(destRect, srcRect.x, srcRect.y, srcPixelStride, srcScanlineStride, xpos, ypos, xfracvalues, yfracvalues);
/*     */         break;
/*     */       case 4:
/*     */       case 5:
/* 164 */         yfracvaluesFloat = new float[dheight];
/* 165 */         xfracvaluesFloat = new float[dwidth];
/* 166 */         preComputePositionsFloat(destRect, srcRect.x, srcRect.y, srcPixelStride, srcScanlineStride, xpos, ypos, xfracvaluesFloat, yfracvaluesFloat);
/*     */         break;
/*     */       default:
/* 172 */         throw new RuntimeException(JaiI18N.getString("OrderedDitherOpImage0"));
/*     */     } 
/* 176 */     switch (dstAccessor.getDataType()) {
/*     */       case 0:
/* 179 */         byteLoop(srcAccessor, destRect, dstAccessor, xpos, ypos, xfracvalues, yfracvalues);
/*     */         break;
/*     */       case 2:
/* 184 */         shortLoop(srcAccessor, destRect, dstAccessor, xpos, ypos, xfracvalues, yfracvalues);
/*     */         break;
/*     */       case 1:
/* 189 */         ushortLoop(srcAccessor, destRect, dstAccessor, xpos, ypos, xfracvalues, yfracvalues);
/*     */         break;
/*     */       case 3:
/* 194 */         intLoop(srcAccessor, destRect, dstAccessor, xpos, ypos, xfracvalues, yfracvalues);
/*     */         break;
/*     */       case 4:
/* 199 */         floatLoop(srcAccessor, destRect, dstAccessor, xpos, ypos, xfracvaluesFloat, yfracvaluesFloat);
/*     */         break;
/*     */       case 5:
/* 204 */         doubleLoop(srcAccessor, destRect, dstAccessor, xpos, ypos, xfracvaluesFloat, yfracvaluesFloat);
/*     */         break;
/*     */       default:
/* 209 */         throw new RuntimeException(JaiI18N.getString("OrderedDitherOpImage0"));
/*     */     } 
/* 216 */     if (dstAccessor.isDataCopy()) {
/* 217 */       dstAccessor.clampDataArrays();
/* 218 */       dstAccessor.copyDataToRaster();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void preComputePositionsInt(Rectangle destRect, int srcRectX, int srcRectY, int srcPixelStride, int srcScanlineStride, int[] xpos, int[] ypos, int[] xfracvalues, int[] yfracvalues) {
/* 229 */     int dwidth = destRect.width;
/* 230 */     int dheight = destRect.height;
/* 233 */     int dx = destRect.x;
/* 234 */     int dy = destRect.y;
/* 236 */     long syNum = dy, syDenom = 1L;
/* 239 */     syNum = syNum * this.transYRationalDenom - this.transYRationalNum * syDenom;
/* 240 */     syDenom *= this.transYRationalDenom;
/* 243 */     syNum = 2L * syNum + syDenom;
/* 244 */     syDenom *= 2L;
/* 247 */     syNum *= this.invScaleYRationalNum;
/* 248 */     syDenom *= this.invScaleYRationalDenom;
/* 251 */     syNum = 2L * syNum - syDenom;
/* 252 */     syDenom *= 2L;
/* 255 */     int srcYInt = Rational.floor(syNum, syDenom);
/* 256 */     long srcYFrac = syNum % syDenom;
/* 257 */     if (srcYInt < 0)
/* 258 */       srcYFrac = syDenom + srcYFrac; 
/* 263 */     long commonYDenom = syDenom * this.invScaleYRationalDenom;
/* 264 */     srcYFrac *= this.invScaleYRationalDenom;
/* 265 */     long newInvScaleYFrac = this.invScaleYFrac * syDenom;
/* 268 */     long sxNum = dx, sxDenom = 1L;
/* 271 */     sxNum = sxNum * this.transXRationalDenom - this.transXRationalNum * sxDenom;
/* 272 */     sxDenom *= this.transXRationalDenom;
/* 275 */     sxNum = 2L * sxNum + sxDenom;
/* 276 */     sxDenom *= 2L;
/* 279 */     sxNum *= this.invScaleXRationalNum;
/* 280 */     sxDenom *= this.invScaleXRationalDenom;
/* 283 */     sxNum = 2L * sxNum - sxDenom;
/* 284 */     sxDenom *= 2L;
/* 287 */     int srcXInt = Rational.floor(sxNum, sxDenom);
/* 288 */     long srcXFrac = sxNum % sxDenom;
/* 289 */     if (srcXInt < 0)
/* 290 */       srcXFrac = sxDenom + srcXFrac; 
/* 295 */     long commonXDenom = sxDenom * this.invScaleXRationalDenom;
/* 296 */     srcXFrac *= this.invScaleXRationalDenom;
/* 297 */     long newInvScaleXFrac = this.invScaleXFrac * sxDenom;
/*     */     int i;
/* 299 */     for (i = 0; i < dwidth; i++) {
/* 300 */       xpos[i] = (srcXInt - srcRectX) * srcPixelStride;
/* 301 */       xfracvalues[i] = (int)((float)srcXFrac / (float)commonXDenom * this.one);
/* 307 */       srcXInt = (int)(srcXInt + this.invScaleXInt);
/* 311 */       srcXFrac += newInvScaleXFrac;
/* 316 */       if (srcXFrac >= commonXDenom) {
/* 317 */         srcXInt++;
/* 318 */         srcXFrac -= commonXDenom;
/*     */       } 
/*     */     } 
/* 322 */     for (i = 0; i < dheight; i++) {
/* 325 */       ypos[i] = (srcYInt - srcRectY) * srcScanlineStride;
/* 328 */       yfracvalues[i] = (int)((float)srcYFrac / (float)commonYDenom * this.one);
/* 334 */       srcYInt = (int)(srcYInt + this.invScaleYInt);
/* 338 */       srcYFrac += newInvScaleYFrac;
/* 343 */       if (srcYFrac >= commonYDenom) {
/* 344 */         srcYInt++;
/* 345 */         srcYFrac -= commonYDenom;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void preComputePositionsFloat(Rectangle destRect, int srcRectX, int srcRectY, int srcPixelStride, int srcScanlineStride, int[] xpos, int[] ypos, float[] xfracvaluesFloat, float[] yfracvaluesFloat) {
/* 356 */     int dwidth = destRect.width;
/* 357 */     int dheight = destRect.height;
/* 360 */     int dx = destRect.x;
/* 361 */     int dy = destRect.y;
/* 363 */     long syNum = dy, syDenom = 1L;
/* 366 */     syNum = syNum * this.transYRationalDenom - this.transYRationalNum * syDenom;
/* 367 */     syDenom *= this.transYRationalDenom;
/* 370 */     syNum = 2L * syNum + syDenom;
/* 371 */     syDenom *= 2L;
/* 374 */     syNum *= this.invScaleYRationalNum;
/* 375 */     syDenom *= this.invScaleYRationalDenom;
/* 378 */     syNum = 2L * syNum - syDenom;
/* 379 */     syDenom *= 2L;
/* 382 */     int srcYInt = Rational.floor(syNum, syDenom);
/* 383 */     long srcYFrac = syNum % syDenom;
/* 384 */     if (srcYInt < 0)
/* 385 */       srcYFrac = syDenom + srcYFrac; 
/* 390 */     long commonYDenom = syDenom * this.invScaleYRationalDenom;
/* 391 */     srcYFrac *= this.invScaleYRationalDenom;
/* 392 */     long newInvScaleYFrac = this.invScaleYFrac * syDenom;
/* 395 */     long sxNum = dx, sxDenom = 1L;
/* 398 */     sxNum = sxNum * this.transXRationalDenom - this.transXRationalNum * sxDenom;
/* 399 */     sxDenom *= this.transXRationalDenom;
/* 402 */     sxNum = 2L * sxNum + sxDenom;
/* 403 */     sxDenom *= 2L;
/* 406 */     sxNum *= this.invScaleXRationalNum;
/* 407 */     sxDenom *= this.invScaleXRationalDenom;
/* 410 */     sxNum = 2L * sxNum - sxDenom;
/* 411 */     sxDenom *= 2L;
/* 414 */     int srcXInt = Rational.floor(sxNum, sxDenom);
/* 415 */     long srcXFrac = sxNum % sxDenom;
/* 416 */     if (srcXInt < 0)
/* 417 */       srcXFrac = sxDenom + srcXFrac; 
/* 422 */     long commonXDenom = sxDenom * this.invScaleXRationalDenom;
/* 423 */     srcXFrac *= this.invScaleXRationalDenom;
/* 424 */     long newInvScaleXFrac = this.invScaleXFrac * sxDenom;
/*     */     int i;
/* 426 */     for (i = 0; i < dwidth; i++) {
/* 428 */       xpos[i] = (srcXInt - srcRectX) * srcPixelStride;
/* 429 */       xfracvaluesFloat[i] = (float)srcXFrac / (float)commonXDenom;
/* 435 */       srcXInt = (int)(srcXInt + this.invScaleXInt);
/* 439 */       srcXFrac += newInvScaleXFrac;
/* 444 */       if (srcXFrac >= commonXDenom) {
/* 445 */         srcXInt++;
/* 446 */         srcXFrac -= commonXDenom;
/*     */       } 
/*     */     } 
/* 450 */     for (i = 0; i < dheight; i++) {
/* 453 */       ypos[i] = (srcYInt - srcRectY) * srcScanlineStride;
/* 456 */       yfracvaluesFloat[i] = (float)srcYFrac / (float)commonYDenom;
/* 462 */       srcYInt = (int)(srcYInt + this.invScaleYInt);
/* 466 */       srcYFrac += newInvScaleYFrac;
/* 471 */       if (srcYFrac >= commonYDenom) {
/* 472 */         srcYInt++;
/* 473 */         srcYFrac -= commonYDenom;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void byteLoop(RasterAccessor src, Rectangle destRect, RasterAccessor dst, int[] xpos, int[] ypos, int[] xfracvalues, int[] yfracvalues) {
/* 482 */     int srcPixelStride = src.getPixelStride();
/* 483 */     int srcScanlineStride = src.getScanlineStride();
/* 485 */     int dwidth = destRect.width;
/* 486 */     int dheight = destRect.height;
/* 487 */     int dnumBands = dst.getNumBands();
/* 488 */     byte[][] dstDataArrays = dst.getByteDataArrays();
/* 489 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 490 */     int dstPixelStride = dst.getPixelStride();
/* 491 */     int dstScanlineStride = dst.getScanlineStride();
/* 493 */     byte[][] srcDataArrays = src.getByteDataArrays();
/* 494 */     int[] bandOffsets = src.getBandOffsets();
/* 496 */     int dstOffset = 0;
/* 499 */     int[][] samples = new int[this.interp_height][this.interp_width];
/* 505 */     for (int k = 0; k < dnumBands; k++) {
/* 506 */       byte[] dstData = dstDataArrays[k];
/* 507 */       byte[] srcData = srcDataArrays[k];
/* 508 */       int dstScanlineOffset = dstBandOffsets[k];
/* 509 */       int bandOffset = bandOffsets[k];
/* 511 */       for (int j = 0; j < dheight; j++) {
/* 513 */         int dstPixelOffset = dstScanlineOffset;
/* 514 */         int yfrac = yfracvalues[j];
/* 516 */         int posy = ypos[j] + bandOffset;
/* 518 */         for (int i = 0; i < dwidth; i++) {
/* 519 */           int xfrac = xfracvalues[i];
/* 520 */           int posx = xpos[i];
/* 525 */           int start = this.interp_left * srcPixelStride + this.interp_top * srcScanlineStride;
/* 527 */           start = posx + posy - start;
/* 528 */           int countH = 0, countV = 0;
/* 530 */           for (int yloop = 0; yloop < this.interp_height; yloop++) {
/* 532 */             int startY = start;
/* 534 */             for (int xloop = 0; xloop < this.interp_width; xloop++) {
/* 535 */               samples[countV][countH++] = srcData[start] & 0xFF;
/* 536 */               start += srcPixelStride;
/*     */             } 
/* 539 */             countV++;
/* 540 */             countH = 0;
/* 541 */             start = startY + srcScanlineStride;
/*     */           } 
/* 545 */           int s = this.interp.interpolate(samples, xfrac, yfrac);
/* 548 */           if (s > 255) {
/* 549 */             s = 255;
/* 550 */           } else if (s < 0) {
/* 551 */             s = 0;
/*     */           } 
/* 554 */           dstData[dstPixelOffset] = (byte)(s & 0xFF);
/* 555 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/* 557 */         dstScanlineOffset += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void shortLoop(RasterAccessor src, Rectangle destRect, RasterAccessor dst, int[] xpos, int[] ypos, int[] xfracvalues, int[] yfracvalues) {
/* 566 */     int srcPixelStride = src.getPixelStride();
/* 567 */     int srcScanlineStride = src.getScanlineStride();
/* 569 */     int dwidth = destRect.width;
/* 570 */     int dheight = destRect.height;
/* 571 */     int dnumBands = dst.getNumBands();
/* 572 */     short[][] dstDataArrays = dst.getShortDataArrays();
/* 573 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 574 */     int dstPixelStride = dst.getPixelStride();
/* 575 */     int dstScanlineStride = dst.getScanlineStride();
/* 577 */     short[][] srcDataArrays = src.getShortDataArrays();
/* 578 */     int[] bandOffsets = src.getBandOffsets();
/* 580 */     int dstOffset = 0;
/* 583 */     int[][] samples = new int[this.interp_height][this.interp_width];
/* 590 */     for (int k = 0; k < dnumBands; k++) {
/* 591 */       short[] dstData = dstDataArrays[k];
/* 592 */       short[] srcData = srcDataArrays[k];
/* 593 */       int dstScanlineOffset = dstBandOffsets[k];
/* 594 */       int bandOffset = bandOffsets[k];
/* 596 */       for (int j = 0; j < dheight; j++) {
/* 598 */         int dstPixelOffset = dstScanlineOffset;
/* 599 */         int yfrac = yfracvalues[j];
/* 600 */         int posy = ypos[j] + bandOffset;
/* 602 */         for (int i = 0; i < dwidth; i++) {
/* 603 */           int xfrac = xfracvalues[i];
/* 604 */           int posx = xpos[i];
/* 607 */           int start = this.interp_left * srcPixelStride + this.interp_top * srcScanlineStride;
/* 609 */           start = posx + posy - start;
/* 610 */           int countH = 0, countV = 0;
/* 612 */           for (int yloop = 0; yloop < this.interp_height; yloop++) {
/* 614 */             int startY = start;
/* 616 */             for (int xloop = 0; xloop < this.interp_width; xloop++) {
/* 617 */               samples[countV][countH++] = srcData[start];
/* 618 */               start += srcPixelStride;
/*     */             } 
/* 621 */             countV++;
/* 622 */             countH = 0;
/* 623 */             start = startY + srcScanlineStride;
/*     */           } 
/* 626 */           int s = this.interp.interpolate(samples, xfrac, yfrac);
/* 629 */           if (s > 32767) {
/* 630 */             s = 32767;
/* 631 */           } else if (s < -32768) {
/* 632 */             s = -32768;
/*     */           } 
/* 635 */           dstData[dstPixelOffset] = (short)s;
/* 636 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/* 638 */         dstScanlineOffset += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void ushortLoop(RasterAccessor src, Rectangle destRect, RasterAccessor dst, int[] xpos, int[] ypos, int[] xfracvalues, int[] yfracvalues) {
/* 647 */     int srcPixelStride = src.getPixelStride();
/* 648 */     int srcScanlineStride = src.getScanlineStride();
/* 650 */     int dwidth = destRect.width;
/* 651 */     int dheight = destRect.height;
/* 652 */     int dnumBands = dst.getNumBands();
/* 653 */     short[][] dstDataArrays = dst.getShortDataArrays();
/* 654 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 655 */     int dstPixelStride = dst.getPixelStride();
/* 656 */     int dstScanlineStride = dst.getScanlineStride();
/* 658 */     short[][] srcDataArrays = src.getShortDataArrays();
/* 659 */     int[] bandOffsets = src.getBandOffsets();
/* 661 */     int dstOffset = 0;
/* 664 */     int[][] samples = new int[this.interp_height][this.interp_width];
/* 670 */     for (int k = 0; k < dnumBands; k++) {
/* 672 */       short[] dstData = dstDataArrays[k];
/* 673 */       short[] srcData = srcDataArrays[k];
/* 674 */       int dstScanlineOffset = dstBandOffsets[k];
/* 675 */       int bandOffset = bandOffsets[k];
/* 677 */       for (int j = 0; j < dheight; j++) {
/* 679 */         int dstPixelOffset = dstScanlineOffset;
/* 680 */         int yfrac = yfracvalues[j];
/* 681 */         int posy = ypos[j] + bandOffset;
/* 683 */         for (int i = 0; i < dwidth; i++) {
/* 685 */           int xfrac = xfracvalues[i];
/* 686 */           int posx = xpos[i];
/* 689 */           int start = this.interp_left * srcPixelStride + this.interp_top * srcScanlineStride;
/* 691 */           start = posx + posy - start;
/* 692 */           int countH = 0, countV = 0;
/* 693 */           for (int yloop = 0; yloop < this.interp_height; yloop++) {
/* 695 */             int startY = start;
/* 697 */             for (int xloop = 0; xloop < this.interp_width; xloop++) {
/* 698 */               samples[countV][countH++] = srcData[start] & 0xFFFF;
/* 699 */               start += srcPixelStride;
/*     */             } 
/* 702 */             countV++;
/* 703 */             countH = 0;
/* 704 */             start = startY + srcScanlineStride;
/*     */           } 
/* 707 */           int s = this.interp.interpolate(samples, xfrac, yfrac);
/* 710 */           if (s > 65536) {
/* 711 */             s = 65536;
/* 712 */           } else if (s < 0) {
/* 713 */             s = 0;
/*     */           } 
/* 716 */           dstData[dstPixelOffset] = (short)(s & 0xFFFF);
/* 717 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/* 719 */         dstScanlineOffset += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void intLoop(RasterAccessor src, Rectangle destRect, RasterAccessor dst, int[] xpos, int[] ypos, int[] xfracvalues, int[] yfracvalues) {
/* 730 */     int srcPixelStride = src.getPixelStride();
/* 731 */     int srcScanlineStride = src.getScanlineStride();
/* 733 */     int dwidth = destRect.width;
/* 734 */     int dheight = destRect.height;
/* 735 */     int dnumBands = dst.getNumBands();
/* 736 */     int[][] dstDataArrays = dst.getIntDataArrays();
/* 737 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 738 */     int dstPixelStride = dst.getPixelStride();
/* 739 */     int dstScanlineStride = dst.getScanlineStride();
/* 741 */     int[][] srcDataArrays = src.getIntDataArrays();
/* 742 */     int[] bandOffsets = src.getBandOffsets();
/* 744 */     int dstOffset = 0;
/* 747 */     int[][] samples = new int[this.interp_height][this.interp_width];
/* 753 */     for (int k = 0; k < dnumBands; k++) {
/* 755 */       int[] dstData = dstDataArrays[k];
/* 756 */       int[] srcData = srcDataArrays[k];
/* 757 */       int dstScanlineOffset = dstBandOffsets[k];
/* 758 */       int bandOffset = bandOffsets[k];
/* 760 */       for (int j = 0; j < dheight; j++) {
/* 762 */         int dstPixelOffset = dstScanlineOffset;
/* 763 */         int yfrac = yfracvalues[j];
/* 764 */         int posy = ypos[j] + bandOffset;
/* 766 */         for (int i = 0; i < dwidth; i++) {
/* 768 */           int xfrac = xfracvalues[i];
/* 769 */           int posx = xpos[i];
/* 772 */           int start = this.interp_left * srcPixelStride + this.interp_top * srcScanlineStride;
/* 774 */           start = posx + posy - start;
/* 775 */           int countH = 0, countV = 0;
/* 776 */           for (int yloop = 0; yloop < this.interp_height; yloop++) {
/* 778 */             int startY = start;
/* 780 */             for (int xloop = 0; xloop < this.interp_width; xloop++) {
/* 781 */               samples[countV][countH++] = srcData[start];
/* 782 */               start += srcPixelStride;
/*     */             } 
/* 785 */             countV++;
/* 786 */             countH = 0;
/* 787 */             start = startY + srcScanlineStride;
/*     */           } 
/* 790 */           int s = this.interp.interpolate(samples, xfrac, yfrac);
/* 792 */           dstData[dstPixelOffset] = s;
/* 793 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/* 795 */         dstScanlineOffset += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void floatLoop(RasterAccessor src, Rectangle destRect, RasterAccessor dst, int[] xpos, int[] ypos, float[] xfracvaluesFloat, float[] yfracvaluesFloat) {
/* 804 */     int srcPixelStride = src.getPixelStride();
/* 805 */     int srcScanlineStride = src.getScanlineStride();
/* 807 */     int dwidth = destRect.width;
/* 808 */     int dheight = destRect.height;
/* 809 */     int dnumBands = dst.getNumBands();
/* 810 */     float[][] dstDataArrays = dst.getFloatDataArrays();
/* 811 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 812 */     int dstPixelStride = dst.getPixelStride();
/* 813 */     int dstScanlineStride = dst.getScanlineStride();
/* 815 */     float[][] srcDataArrays = src.getFloatDataArrays();
/* 816 */     int[] bandOffsets = src.getBandOffsets();
/* 818 */     int dstOffset = 0;
/* 821 */     float[][] samples = new float[this.interp_height][this.interp_width];
/* 828 */     for (int k = 0; k < dnumBands; k++) {
/* 830 */       float[] dstData = dstDataArrays[k];
/* 831 */       float[] srcData = srcDataArrays[k];
/* 832 */       int dstScanlineOffset = dstBandOffsets[k];
/* 833 */       int bandOffset = bandOffsets[k];
/* 835 */       for (int j = 0; j < dheight; j++) {
/* 837 */         int dstPixelOffset = dstScanlineOffset;
/* 838 */         float yfrac = yfracvaluesFloat[j];
/* 839 */         int posy = ypos[j] + bandOffset;
/* 841 */         for (int i = 0; i < dwidth; i++) {
/* 843 */           float xfrac = xfracvaluesFloat[i];
/* 844 */           int posx = xpos[i];
/* 847 */           int start = this.interp_left * srcPixelStride + this.interp_top * srcScanlineStride;
/* 849 */           start = posx + posy - start;
/* 850 */           int countH = 0, countV = 0;
/* 851 */           for (int yloop = 0; yloop < this.interp_height; yloop++) {
/* 853 */             int startY = start;
/* 855 */             for (int xloop = 0; xloop < this.interp_width; xloop++) {
/* 856 */               samples[countV][countH++] = srcData[start];
/* 857 */               start += srcPixelStride;
/*     */             } 
/* 860 */             countV++;
/* 861 */             countH = 0;
/* 862 */             start = startY + srcScanlineStride;
/*     */           } 
/* 865 */           float s = this.interp.interpolate(samples, xfrac, yfrac);
/* 867 */           if (s > Float.MAX_VALUE) {
/* 868 */             s = Float.MAX_VALUE;
/* 869 */           } else if (s < -3.4028235E38F) {
/* 870 */             s = -3.4028235E38F;
/*     */           } 
/* 873 */           dstData[dstPixelOffset] = s;
/* 874 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/* 876 */         dstScanlineOffset += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void doubleLoop(RasterAccessor src, Rectangle destRect, RasterAccessor dst, int[] xpos, int[] ypos, float[] xfracvaluesFloat, float[] yfracvaluesFloat) {
/* 885 */     int srcPixelStride = src.getPixelStride();
/* 886 */     int srcScanlineStride = src.getScanlineStride();
/* 888 */     int dwidth = destRect.width;
/* 889 */     int dheight = destRect.height;
/* 890 */     int dnumBands = dst.getNumBands();
/* 891 */     double[][] dstDataArrays = dst.getDoubleDataArrays();
/* 892 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 893 */     int dstPixelStride = dst.getPixelStride();
/* 894 */     int dstScanlineStride = dst.getScanlineStride();
/* 896 */     double[][] srcDataArrays = src.getDoubleDataArrays();
/* 897 */     int[] bandOffsets = src.getBandOffsets();
/* 899 */     int dstOffset = 0;
/* 902 */     double[][] samples = new double[this.interp_height][this.interp_width];
/* 909 */     for (int k = 0; k < dnumBands; k++) {
/* 911 */       double[] dstData = dstDataArrays[k];
/* 912 */       double[] srcData = srcDataArrays[k];
/* 913 */       int dstScanlineOffset = dstBandOffsets[k];
/* 914 */       int bandOffset = bandOffsets[k];
/* 916 */       for (int j = 0; j < dheight; j++) {
/* 918 */         int dstPixelOffset = dstScanlineOffset;
/* 919 */         float yfrac = yfracvaluesFloat[j];
/* 920 */         int posy = ypos[j] + bandOffset;
/* 922 */         for (int i = 0; i < dwidth; i++) {
/* 924 */           float xfrac = xfracvaluesFloat[i];
/* 925 */           int posx = xpos[i];
/* 928 */           int start = this.interp_left * srcPixelStride + this.interp_top * srcScanlineStride;
/* 930 */           start = posx + posy - start;
/* 931 */           int countH = 0, countV = 0;
/* 932 */           for (int yloop = 0; yloop < this.interp_height; yloop++) {
/* 934 */             int startY = start;
/* 936 */             for (int xloop = 0; xloop < this.interp_width; xloop++) {
/* 937 */               samples[countV][countH++] = srcData[start];
/* 938 */               start += srcPixelStride;
/*     */             } 
/* 941 */             countV++;
/* 942 */             countH = 0;
/* 943 */             start = startY + srcScanlineStride;
/*     */           } 
/* 946 */           double s = this.interp.interpolate(samples, xfrac, yfrac);
/* 948 */           dstData[dstPixelOffset] = s;
/* 949 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/* 951 */         dstScanlineOffset += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\ScaleGeneralOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */