/*     */ package com.sun.media.jai.opimage;
/*     */ 
/*     */ import com.sun.media.jai.util.Rational;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.image.DataBufferByte;
/*     */ import java.awt.image.DataBufferInt;
/*     */ import java.awt.image.DataBufferUShort;
/*     */ import java.awt.image.MultiPixelPackedSampleModel;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.util.Map;
/*     */ import javax.media.jai.BorderExtender;
/*     */ import javax.media.jai.ImageLayout;
/*     */ import javax.media.jai.Interpolation;
/*     */ import javax.media.jai.ScaleOpImage;
/*     */ 
/*     */ public final class ScaleBilinearBinaryOpImage extends ScaleOpImage {
/*     */   private int subsampleBits;
/*     */   
/*     */   int one;
/*     */   
/*     */   int shift2;
/*     */   
/*     */   int round2;
/*     */   
/*     */   long invScaleXInt;
/*     */   
/*     */   long invScaleXFrac;
/*     */   
/*     */   long invScaleYInt;
/*     */   
/*     */   long invScaleYFrac;
/*     */   
/*     */   public ScaleBilinearBinaryOpImage(RenderedImage source, BorderExtender extender, Map config, ImageLayout layout, float xScale, float yScale, float xTrans, float yTrans, Interpolation interp) {
/*  79 */     super(source, layout, config, true, extender, interp, xScale, yScale, xTrans, yTrans);
/*  90 */     this.subsampleBits = interp.getSubsampleBitsH();
/*  93 */     this.one = 1 << this.subsampleBits;
/*  96 */     this.shift2 = 2 * this.subsampleBits;
/*  97 */     this.round2 = 1 << this.shift2 - 1;
/* 100 */     if (layout != null) {
/* 102 */       this.colorModel = layout.getColorModel(source);
/*     */     } else {
/* 106 */       this.colorModel = source.getColorModel();
/*     */     } 
/* 109 */     this.sampleModel = source.getSampleModel().createCompatibleSampleModel(this.tileWidth, this.tileHeight);
/* 111 */     if (this.invScaleXRational.num > this.invScaleXRational.denom) {
/* 113 */       this.invScaleXInt = this.invScaleXRational.num / this.invScaleXRational.denom;
/* 114 */       this.invScaleXFrac = this.invScaleXRational.num % this.invScaleXRational.denom;
/*     */     } else {
/* 118 */       this.invScaleXInt = 0L;
/* 119 */       this.invScaleXFrac = this.invScaleXRational.num;
/*     */     } 
/* 122 */     if (this.invScaleYRational.num > this.invScaleYRational.denom) {
/* 124 */       this.invScaleYInt = this.invScaleYRational.num / this.invScaleYRational.denom;
/* 125 */       this.invScaleYFrac = this.invScaleYRational.num % this.invScaleYRational.denom;
/*     */     } else {
/* 129 */       this.invScaleYInt = 0L;
/* 130 */       this.invScaleYFrac = this.invScaleYRational.num;
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void computeRect(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/* 148 */     Raster source = sources[0];
/* 151 */     Rectangle srcRect = source.getBounds();
/* 153 */     int srcRectX = srcRect.x;
/* 154 */     int srcRectY = srcRect.y;
/* 158 */     int dx = destRect.x;
/* 159 */     int dy = destRect.y;
/* 161 */     int dwidth = destRect.width;
/* 162 */     int dheight = destRect.height;
/* 165 */     int[] xvalues = new int[dwidth];
/* 166 */     int[] yvalues = new int[dheight];
/* 168 */     int[] xfracvalues = new int[dwidth];
/* 169 */     int[] yfracvalues = new int[dheight];
/* 172 */     long sxNum = dx, sxDenom = 1L;
/* 173 */     long syNum = dy, syDenom = 1L;
/* 176 */     sxNum = sxNum * this.transXRationalDenom - this.transXRationalNum * sxDenom;
/* 177 */     sxDenom *= this.transXRationalDenom;
/* 179 */     syNum = syNum * this.transYRationalDenom - this.transYRationalNum * syDenom;
/* 180 */     syDenom *= this.transYRationalDenom;
/* 183 */     sxNum = 2L * sxNum + sxDenom;
/* 184 */     sxDenom *= 2L;
/* 186 */     syNum = 2L * syNum + syDenom;
/* 187 */     syDenom *= 2L;
/* 191 */     sxNum *= this.invScaleXRationalNum;
/* 192 */     sxDenom *= this.invScaleXRationalDenom;
/* 194 */     syNum *= this.invScaleYRationalNum;
/* 195 */     syDenom *= this.invScaleYRationalDenom;
/* 200 */     sxNum = 2L * sxNum - sxDenom;
/* 201 */     sxDenom *= 2L;
/* 203 */     syNum = 2L * syNum - syDenom;
/* 204 */     syDenom *= 2L;
/* 209 */     int srcXInt = Rational.floor(sxNum, sxDenom);
/* 210 */     long srcXFrac = sxNum % sxDenom;
/* 211 */     if (srcXInt < 0)
/* 213 */       srcXFrac = sxDenom + srcXFrac; 
/* 216 */     int srcYInt = Rational.floor(syNum, syDenom);
/* 217 */     long srcYFrac = syNum % syDenom;
/* 218 */     if (srcYInt < 0)
/* 220 */       srcYFrac = syDenom + srcYFrac; 
/* 225 */     long commonXDenom = sxDenom * this.invScaleXRationalDenom;
/* 226 */     srcXFrac *= this.invScaleXRationalDenom;
/* 227 */     long newInvScaleXFrac = this.invScaleXFrac * sxDenom;
/* 229 */     long commonYDenom = syDenom * this.invScaleYRationalDenom;
/* 230 */     srcYFrac *= this.invScaleYRationalDenom;
/* 231 */     long newInvScaleYFrac = this.invScaleYFrac * syDenom;
/*     */     int i;
/* 233 */     for (i = 0; i < dwidth; i++) {
/* 239 */       xvalues[i] = srcXInt;
/* 251 */       xfracvalues[i] = (int)((float)srcXFrac / (float)commonXDenom * this.one);
/* 257 */       srcXInt = (int)(srcXInt + this.invScaleXInt);
/* 261 */       srcXFrac += newInvScaleXFrac;
/* 266 */       if (srcXFrac >= commonXDenom) {
/* 268 */         srcXInt++;
/* 269 */         srcXFrac -= commonXDenom;
/*     */       } 
/*     */     } 
/* 275 */     for (i = 0; i < dheight; i++) {
/* 278 */       yvalues[i] = srcYInt;
/* 279 */       yfracvalues[i] = (int)((float)srcYFrac / (float)commonYDenom * this.one);
/* 296 */       srcYInt = (int)(srcYInt + this.invScaleYInt);
/* 300 */       srcYFrac += newInvScaleYFrac;
/* 305 */       if (srcYFrac >= commonYDenom) {
/* 307 */         srcYInt++;
/* 308 */         srcYFrac -= commonYDenom;
/*     */       } 
/*     */     } 
/* 312 */     switch (source.getSampleModel().getDataType()) {
/*     */       case 0:
/* 315 */         byteLoop(source, dest, dx, dy, dwidth, dheight, xvalues, yvalues, xfracvalues, yfracvalues);
/*     */         return;
/*     */       case 1:
/*     */       case 2:
/* 321 */         shortLoop(source, dest, dx, dy, dwidth, dheight, xvalues, yvalues, xfracvalues, yfracvalues);
/*     */         return;
/*     */       case 3:
/* 326 */         intLoop(source, dest, dx, dy, dwidth, dheight, xvalues, yvalues, xfracvalues, yfracvalues);
/*     */         return;
/*     */     } 
/* 331 */     throw new RuntimeException(JaiI18N.getString("OrderedDitherOpImage0"));
/*     */   }
/*     */   
/*     */   private void byteLoop(Raster source, WritableRaster dest, int dx, int dy, int dwidth, int dheight, int[] xvalues, int[] yvalues, int[] xfracvalues, int[] yfracvalues) {
/* 340 */     MultiPixelPackedSampleModel sourceSM = (MultiPixelPackedSampleModel)source.getSampleModel();
/* 341 */     DataBufferByte sourceDB = (DataBufferByte)source.getDataBuffer();
/* 342 */     int sourceTransX = source.getSampleModelTranslateX();
/* 343 */     int sourceTransY = source.getSampleModelTranslateY();
/* 344 */     int sourceDataBitOffset = sourceSM.getDataBitOffset();
/* 345 */     int sourceScanlineStride = sourceSM.getScanlineStride();
/* 347 */     MultiPixelPackedSampleModel destSM = (MultiPixelPackedSampleModel)dest.getSampleModel();
/* 348 */     DataBufferByte destDB = (DataBufferByte)dest.getDataBuffer();
/* 349 */     int destMinX = dest.getMinX();
/* 350 */     int destMinY = dest.getMinY();
/* 351 */     int destTransX = dest.getSampleModelTranslateX();
/* 352 */     int destTransY = dest.getSampleModelTranslateY();
/* 353 */     int destDataBitOffset = destSM.getDataBitOffset();
/* 354 */     int destScanlineStride = destSM.getScanlineStride();
/* 356 */     byte[] sourceData = sourceDB.getData();
/* 357 */     int sourceDBOffset = sourceDB.getOffset();
/* 359 */     byte[] destData = destDB.getData();
/* 360 */     int destDBOffset = destDB.getOffset();
/* 362 */     int[] sbytenum = new int[dwidth];
/* 363 */     int[] sshift = new int[dwidth];
/* 371 */     for (int i = 0; i < dwidth; i++) {
/* 373 */       int m = xvalues[i];
/* 374 */       int sbitnum = sourceDataBitOffset + m - sourceTransX;
/* 375 */       sbytenum[i] = sbitnum >> 3;
/* 376 */       sshift[i] = 7 - (sbitnum & 0x7);
/*     */     } 
/* 383 */     int x = 0, y = 0;
/* 390 */     int destYOffset = (dy - destTransY) * destScanlineStride + destDBOffset;
/* 391 */     int dbitnum = destDataBitOffset + dx - destTransX;
/* 397 */     int k = 0, j = 0;
/* 400 */     for (j = 0; j < dheight; j++) {
/* 403 */       y = yvalues[j];
/* 404 */       int yfrac = yfracvalues[j];
/* 406 */       int sourceYOffset = (y - sourceTransY) * sourceScanlineStride + sourceDBOffset;
/* 407 */       dbitnum = destDataBitOffset + dx - destTransX;
/* 411 */       for (k = 0; k < dwidth; k++) {
/* 413 */         int xfrac = xfracvalues[k];
/* 414 */         x = xvalues[k];
/* 415 */         int xNextBitNo = sourceDataBitOffset + x + 1 - sourceTransX;
/* 416 */         int xNextByteNo = xNextBitNo >> 3;
/* 417 */         int xNextShiftNo = 7 - (xNextBitNo & 0x7);
/* 438 */         int s00 = sourceData[sourceYOffset + sbytenum[k]] >> sshift[k] & 0x1;
/* 439 */         int s01 = sourceData[sourceYOffset + xNextByteNo] >> xNextShiftNo & 0x1;
/* 440 */         int s10 = sourceData[sourceYOffset + sourceScanlineStride + sbytenum[k]] >> sshift[k] & 0x1;
/* 441 */         int s11 = sourceData[sourceYOffset + sourceScanlineStride + xNextByteNo] >> xNextShiftNo & 0x1;
/* 444 */         int s0 = (s01 - s00) * xfrac + (s00 << this.subsampleBits);
/* 445 */         int s1 = (s11 - s10) * xfrac + (s10 << this.subsampleBits);
/* 448 */         int s = (s1 - s0) * yfrac + (s0 << this.subsampleBits) + this.round2 >> this.shift2;
/* 451 */         int destByteNum = dbitnum >> 3;
/* 452 */         int destBitShift = 7 - (dbitnum & 0x7);
/* 454 */         if (s == 1) {
/* 457 */           destData[destYOffset + destByteNum] = (byte)(destData[destYOffset + destByteNum] | 1 << destBitShift);
/*     */         } else {
/* 462 */           destData[destYOffset + destByteNum] = (byte)(destData[destYOffset + destByteNum] & 255 - (1 << destBitShift));
/*     */         } 
/* 464 */         dbitnum++;
/*     */       } 
/* 466 */       destYOffset += destScanlineStride;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void shortLoop(Raster source, WritableRaster dest, int dx, int dy, int dwidth, int dheight, int[] xvalues, int[] yvalues, int[] xfracvalues, int[] yfracvalues) {
/* 476 */     MultiPixelPackedSampleModel sourceSM = (MultiPixelPackedSampleModel)source.getSampleModel();
/* 477 */     int sourceTransX = source.getSampleModelTranslateX();
/* 478 */     int sourceTransY = source.getSampleModelTranslateY();
/* 479 */     int sourceDataBitOffset = sourceSM.getDataBitOffset();
/* 480 */     int sourceScanlineStride = sourceSM.getScanlineStride();
/* 482 */     MultiPixelPackedSampleModel destSM = (MultiPixelPackedSampleModel)dest.getSampleModel();
/* 483 */     int destMinX = dest.getMinX();
/* 484 */     int destMinY = dest.getMinY();
/* 485 */     int destTransX = dest.getSampleModelTranslateX();
/* 486 */     int destTransY = dest.getSampleModelTranslateY();
/* 487 */     int destDataBitOffset = destSM.getDataBitOffset();
/* 488 */     int destScanlineStride = destSM.getScanlineStride();
/* 490 */     DataBufferUShort sourceDB = (DataBufferUShort)source.getDataBuffer();
/* 491 */     short[] sourceData = sourceDB.getData();
/* 492 */     int sourceDBOffset = sourceDB.getOffset();
/* 494 */     DataBufferUShort destDB = (DataBufferUShort)dest.getDataBuffer();
/* 495 */     short[] destData = destDB.getData();
/* 496 */     int destDBOffset = destDB.getOffset();
/* 498 */     int[] sshortnum = new int[dwidth];
/* 499 */     int[] sshift = new int[dwidth];
/* 501 */     for (int i = 0; i < dwidth; i++) {
/* 503 */       int x = xvalues[i];
/* 504 */       int sbitnum = sourceDataBitOffset + x - sourceTransX;
/* 505 */       sshortnum[i] = sbitnum >> 4;
/* 506 */       sshift[i] = 15 - (sbitnum & 0xF);
/*     */     } 
/* 520 */     int destYOffset = (dy - destTransY) * destScanlineStride + destDBOffset;
/* 521 */     int dbitnum = destDataBitOffset + dx - destTransX;
/* 526 */     for (int j = 0; j < dheight; j++) {
/* 528 */       int y = yvalues[j];
/* 529 */       int yfrac = yfracvalues[j];
/* 531 */       int sourceYOffset = (y - sourceTransY) * sourceScanlineStride + sourceDBOffset;
/* 532 */       dbitnum = destDataBitOffset + dx - destTransX;
/* 534 */       for (int k = 0; k < dwidth; k++) {
/* 536 */         int xfrac = xfracvalues[k];
/* 537 */         int x = xvalues[k];
/* 538 */         int xNextBitNo = sourceDataBitOffset + x + 1 - sourceTransX;
/* 539 */         int xNextShortNo = xNextBitNo >> 4;
/* 540 */         int xNextShiftNo = 15 - (xNextBitNo & 0xF);
/* 542 */         int s00 = sourceData[sourceYOffset + sshortnum[k]] >> sshift[k] & 0x1;
/* 543 */         int s01 = sourceData[sourceYOffset + xNextShortNo] >> xNextShiftNo & 0x1;
/* 544 */         int s10 = sourceData[sourceYOffset + sourceScanlineStride + sshortnum[k]] >> sshift[k] & 0x1;
/* 545 */         int s11 = sourceData[sourceYOffset + sourceScanlineStride + xNextShortNo] >> xNextShiftNo & 0x1;
/* 547 */         int s0 = (s01 - s00) * xfrac + (s00 << this.subsampleBits);
/* 548 */         int s1 = (s11 - s10) * xfrac + (s10 << this.subsampleBits);
/* 549 */         int s = (s1 - s0) * yfrac + (s0 << this.subsampleBits) + this.round2 >> this.shift2;
/* 551 */         int destShortNum = dbitnum >> 4;
/* 552 */         int destBitShift = 15 - (dbitnum & 0xF);
/* 554 */         if (s == 1) {
/* 556 */           destData[destYOffset + destShortNum] = (short)(destData[destYOffset + destShortNum] | 1 << destBitShift);
/*     */         } else {
/* 560 */           destData[destYOffset + destShortNum] = (short)(destData[destYOffset + destShortNum] & 65535 - (1 << destBitShift));
/*     */         } 
/* 562 */         dbitnum++;
/*     */       } 
/* 564 */       destYOffset += destScanlineStride;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void intLoop(Raster source, WritableRaster dest, int dx, int dy, int dwidth, int dheight, int[] xvalues, int[] yvalues, int[] xfracvalues, int[] yfracvalues) {
/* 573 */     MultiPixelPackedSampleModel sourceSM = (MultiPixelPackedSampleModel)source.getSampleModel();
/* 574 */     DataBufferInt sourceDB = (DataBufferInt)source.getDataBuffer();
/* 575 */     int sourceTransX = source.getSampleModelTranslateX();
/* 576 */     int sourceTransY = source.getSampleModelTranslateY();
/* 577 */     int sourceDataBitOffset = sourceSM.getDataBitOffset();
/* 578 */     int sourceScanlineStride = sourceSM.getScanlineStride();
/* 580 */     MultiPixelPackedSampleModel destSM = (MultiPixelPackedSampleModel)dest.getSampleModel();
/* 581 */     DataBufferInt destDB = (DataBufferInt)dest.getDataBuffer();
/* 582 */     int destMinX = dest.getMinX();
/* 583 */     int destMinY = dest.getMinY();
/* 584 */     int destTransX = dest.getSampleModelTranslateX();
/* 585 */     int destTransY = dest.getSampleModelTranslateY();
/* 586 */     int destDataBitOffset = destSM.getDataBitOffset();
/* 587 */     int destScanlineStride = destSM.getScanlineStride();
/* 589 */     int[] sourceData = sourceDB.getData();
/* 590 */     int sourceDBOffset = sourceDB.getOffset();
/* 592 */     int[] destData = destDB.getData();
/* 593 */     int destDBOffset = destDB.getOffset();
/* 595 */     int[] sintnum = new int[dwidth];
/* 596 */     int[] sshift = new int[dwidth];
/* 598 */     for (int i = 0; i < dwidth; i++) {
/* 600 */       int x = xvalues[i];
/* 601 */       int sbitnum = sourceDataBitOffset + x - sourceTransX;
/* 602 */       sintnum[i] = sbitnum >> 5;
/* 603 */       sshift[i] = 31 - (sbitnum & 0x1F);
/*     */     } 
/* 616 */     int destYOffset = (dy - destTransY) * destScanlineStride + destDBOffset;
/* 617 */     int dbitnum = destDataBitOffset + dx - destTransX;
/* 622 */     for (int j = 0; j < dheight; j++) {
/* 624 */       int y = yvalues[j];
/* 625 */       int yfrac = yfracvalues[j];
/* 627 */       int sourceYOffset = (y - sourceTransY) * sourceScanlineStride + sourceDBOffset;
/* 628 */       dbitnum = destDataBitOffset + dx - destTransX;
/* 630 */       for (int k = 0; k < dwidth; k++) {
/* 632 */         int xfrac = xfracvalues[k];
/* 633 */         int x = xvalues[k];
/* 635 */         int xNextBitNo = sourceDataBitOffset + x + 1 - sourceTransX;
/* 636 */         int xNextIntNo = xNextBitNo >> 5;
/* 637 */         int xNextShiftNo = 31 - (xNextBitNo & 0x1F);
/* 639 */         int s00 = sourceData[sourceYOffset + sintnum[k]] >> sshift[k] & 0x1;
/* 640 */         int s01 = sourceData[sourceYOffset + xNextIntNo] >> xNextShiftNo & 0x1;
/* 641 */         int s10 = sourceData[sourceYOffset + sourceScanlineStride + sintnum[k]] >> sshift[k] & 0x1;
/* 642 */         int s11 = sourceData[sourceYOffset + sourceScanlineStride + xNextIntNo] >> xNextShiftNo & 0x1;
/* 644 */         int s0 = (s01 - s00) * xfrac + (s00 << this.subsampleBits);
/* 645 */         int s1 = (s11 - s10) * xfrac + (s10 << this.subsampleBits);
/* 646 */         int s = (s1 - s0) * yfrac + (s0 << this.subsampleBits) + this.round2 >> this.shift2;
/* 648 */         int destIntNum = dbitnum >> 5;
/* 649 */         int destBitShift = 31 - (dbitnum & 0x1F);
/* 651 */         if (s == 1) {
/* 654 */           destData[destYOffset + destIntNum] = destData[destYOffset + destIntNum] | 1 << destBitShift;
/*     */         } else {
/* 658 */           destData[destYOffset + destIntNum] = destData[destYOffset + destIntNum] & 255 - (1 << destBitShift);
/*     */         } 
/* 660 */         dbitnum++;
/*     */       } 
/* 662 */       destYOffset += destScanlineStride;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\ScaleBilinearBinaryOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */