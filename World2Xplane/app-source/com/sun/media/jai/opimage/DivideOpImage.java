/*     */ package com.sun.media.jai.opimage;
/*     */ 
/*     */ import com.sun.media.jai.util.ImageUtil;
/*     */ import com.sun.media.jai.util.JDKWorkarounds;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.SampleModel;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.util.Map;
/*     */ import javax.media.jai.ImageLayout;
/*     */ import javax.media.jai.PointOpImage;
/*     */ import javax.media.jai.RasterAccessor;
/*     */ import javax.media.jai.RasterFactory;
/*     */ import javax.media.jai.RasterFormatTag;
/*     */ 
/*     */ final class DivideOpImage extends PointOpImage {
/*     */   private byte[][] divideTableByte;
/*     */   
/*  67 */   private int s1bd = 1;
/*     */   
/*  70 */   private int s2bd = 1;
/*     */   
/*     */   public DivideOpImage(RenderedImage source1, RenderedImage source2, Map config, ImageLayout layout) {
/*  93 */     super(source1, source2, layout, config, true);
/*  96 */     int numBands1 = source1.getSampleModel().getNumBands();
/*  97 */     int numBands2 = source2.getSampleModel().getNumBands();
/* 102 */     if (layout != null && layout.isValid(256)) {
/* 103 */       SampleModel sm = layout.getSampleModel(null);
/* 104 */       int numBandsDst = sm.getNumBands();
/* 108 */       if (numBandsDst > 1 && ((numBands1 > 1 && numBands2 == 1) || (numBands1 == 1 && numBands2 > 1))) {
/* 113 */         numBandsDst = Math.min(Math.max(numBands1, numBands2), numBandsDst);
/* 117 */         if (numBandsDst != this.sampleModel.getNumBands()) {
/* 118 */           this.sampleModel = RasterFactory.createComponentSampleModel(sm, this.sampleModel.getTransferType(), this.sampleModel.getWidth(), this.sampleModel.getHeight(), numBandsDst);
/* 126 */           if (this.colorModel != null && !JDKWorkarounds.areCompatibleDataModels(this.sampleModel, this.colorModel))
/* 129 */             this.colorModel = ImageUtil.getCompatibleColorModel(this.sampleModel, config); 
/*     */         } 
/* 136 */         this.s1bd = (numBands1 == 1) ? 0 : 1;
/* 137 */         this.s2bd = (numBands2 == 1) ? 0 : 1;
/*     */       } 
/*     */     } 
/* 141 */     if (this.sampleModel.getTransferType() == 0) {
/* 143 */       this.divideTableByte = new byte[256][256];
/* 144 */       for (int j = 0; j < 256; j++) {
/* 145 */         byte[] array = this.divideTableByte[j];
/* 147 */         if (j > 0) {
/* 148 */           array[0] = -1;
/*     */         } else {
/* 150 */           array[0] = 0;
/*     */         } 
/* 153 */         for (int i = 1; i < 256; i++)
/* 154 */           array[i] = ImageUtil.clampRoundByte(j / i); 
/*     */       } 
/*     */     } 
/* 160 */     permitInPlaceOperation();
/*     */   }
/*     */   
/*     */   protected void computeRect(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/* 176 */     RasterFormatTag[] formatTags = getFormatTags();
/* 179 */     RasterAccessor s1 = new RasterAccessor(sources[0], destRect, formatTags[0], getSourceImage(0).getColorModel());
/* 182 */     RasterAccessor s2 = new RasterAccessor(sources[1], destRect, formatTags[1], getSourceImage(1).getColorModel());
/* 185 */     RasterAccessor d = new RasterAccessor(dest, destRect, formatTags[2], getColorModel());
/* 188 */     if (d.isBinary()) {
/* 189 */       byte[] dstBits = d.getBinaryDataArray();
/* 192 */       System.arraycopy(s1.getBinaryDataArray(), 0, dstBits, 0, dstBits.length);
/* 195 */       d.copyBinaryDataToRaster();
/*     */       return;
/*     */     } 
/* 200 */     int src1LineStride = s1.getScanlineStride();
/* 201 */     int src1PixelStride = s1.getPixelStride();
/* 202 */     int[] src1BandOffsets = s1.getBandOffsets();
/* 204 */     int src2LineStride = s2.getScanlineStride();
/* 205 */     int src2PixelStride = s2.getPixelStride();
/* 206 */     int[] src2BandOffsets = s2.getBandOffsets();
/* 208 */     int dstNumBands = d.getNumBands();
/* 209 */     int dstWidth = d.getWidth();
/* 210 */     int dstHeight = d.getHeight();
/* 211 */     int dstLineStride = d.getScanlineStride();
/* 212 */     int dstPixelStride = d.getPixelStride();
/* 213 */     int[] dstBandOffsets = d.getBandOffsets();
/* 215 */     switch (d.getDataType()) {
/*     */       case 0:
/* 218 */         byteLoop(dstNumBands, dstWidth, dstHeight, src1LineStride, src1PixelStride, src1BandOffsets, s1.getByteDataArrays(), src2LineStride, src2PixelStride, src2BandOffsets, s2.getByteDataArrays(), dstLineStride, dstPixelStride, dstBandOffsets, d.getByteDataArrays());
/*     */         break;
/*     */       case 1:
/* 228 */         ushortLoop(dstNumBands, dstWidth, dstHeight, src1LineStride, src1PixelStride, src1BandOffsets, s1.getShortDataArrays(), src2LineStride, src2PixelStride, src2BandOffsets, s2.getShortDataArrays(), dstLineStride, dstPixelStride, dstBandOffsets, d.getShortDataArrays());
/*     */         break;
/*     */       case 2:
/* 238 */         shortLoop(dstNumBands, dstWidth, dstHeight, src1LineStride, src1PixelStride, src1BandOffsets, s1.getShortDataArrays(), src2LineStride, src2PixelStride, src2BandOffsets, s2.getShortDataArrays(), dstLineStride, dstPixelStride, dstBandOffsets, d.getShortDataArrays());
/*     */         break;
/*     */       case 3:
/* 248 */         intLoop(dstNumBands, dstWidth, dstHeight, src1LineStride, src1PixelStride, src1BandOffsets, s1.getIntDataArrays(), src2LineStride, src2PixelStride, src2BandOffsets, s2.getIntDataArrays(), dstLineStride, dstPixelStride, dstBandOffsets, d.getIntDataArrays());
/*     */         break;
/*     */       case 4:
/* 258 */         floatLoop(dstNumBands, dstWidth, dstHeight, src1LineStride, src1PixelStride, src1BandOffsets, s1.getFloatDataArrays(), src2LineStride, src2PixelStride, src2BandOffsets, s2.getFloatDataArrays(), dstLineStride, dstPixelStride, dstBandOffsets, d.getFloatDataArrays());
/*     */         break;
/*     */       case 5:
/* 268 */         doubleLoop(dstNumBands, dstWidth, dstHeight, src1LineStride, src1PixelStride, src1BandOffsets, s1.getDoubleDataArrays(), src2LineStride, src2PixelStride, src2BandOffsets, s2.getDoubleDataArrays(), dstLineStride, dstPixelStride, dstBandOffsets, d.getDoubleDataArrays());
/*     */         break;
/*     */     } 
/* 278 */     if (d.needsClamping())
/* 279 */       d.clampDataArrays(); 
/* 282 */     d.copyDataToRaster();
/*     */   }
/*     */   
/*     */   private void byteLoop(int dstNumBands, int dstWidth, int dstHeight, int src1LineStride, int src1PixelStride, int[] src1BandOffsets, byte[][] src1Data, int src2LineStride, int src2PixelStride, int[] src2BandOffsets, byte[][] src2Data, int dstLineStride, int dstPixelStride, int[] dstBandOffsets, byte[][] dstData) {
/*     */     int s2b;
/* 293 */     for (int b = 0, s1b = 0; b < dstNumBands; 
/* 294 */       b++, s1b += this.s1bd, s2b += this.s2bd) {
/* 295 */       byte[] s1 = src1Data[s1b];
/* 296 */       byte[] s2 = src2Data[s2b];
/* 297 */       byte[] d = dstData[b];
/* 298 */       int src1LineOffset = src1BandOffsets[s1b];
/* 299 */       int src2LineOffset = src2BandOffsets[s2b];
/* 300 */       int dstLineOffset = dstBandOffsets[b];
/* 302 */       for (int h = 0; h < dstHeight; h++) {
/* 303 */         int src1PixelOffset = src1LineOffset;
/* 304 */         int src2PixelOffset = src2LineOffset;
/* 305 */         int dstPixelOffset = dstLineOffset;
/* 306 */         src1LineOffset += src1LineStride;
/* 307 */         src2LineOffset += src2LineStride;
/* 308 */         dstLineOffset += dstLineStride;
/* 310 */         for (int w = 0; w < dstWidth; w++) {
/* 311 */           d[dstPixelOffset] = this.divideTableByte[s1[src1PixelOffset] & 0xFF][s2[src2PixelOffset] & 0xFF];
/* 314 */           src1PixelOffset += src1PixelStride;
/* 315 */           src2PixelOffset += src2PixelStride;
/* 316 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void ushortLoop(int dstNumBands, int dstWidth, int dstHeight, int src1LineStride, int src1PixelStride, int[] src1BandOffsets, short[][] src1Data, int src2LineStride, int src2PixelStride, int[] src2BandOffsets, short[][] src2Data, int dstLineStride, int dstPixelStride, int[] dstBandOffsets, short[][] dstData) {
/*     */     int s2b;
/* 330 */     for (int b = 0, s1b = 0; b < dstNumBands; 
/* 331 */       b++, s1b += this.s1bd, s2b += this.s2bd) {
/* 332 */       short[] s1 = src1Data[s1b];
/* 333 */       short[] s2 = src2Data[s2b];
/* 334 */       short[] d = dstData[b];
/* 336 */       int src1LineOffset = src1BandOffsets[s1b];
/* 337 */       int src2LineOffset = src2BandOffsets[s2b];
/* 338 */       int dstLineOffset = dstBandOffsets[b];
/* 340 */       for (int h = 0; h < dstHeight; h++) {
/* 341 */         int src1PixelOffset = src1LineOffset;
/* 342 */         int src2PixelOffset = src2LineOffset;
/* 343 */         int dstPixelOffset = dstLineOffset;
/* 344 */         src1LineOffset += src1LineStride;
/* 345 */         src2LineOffset += src2LineStride;
/* 346 */         dstLineOffset += dstLineStride;
/* 348 */         for (int w = 0; w < dstWidth; w++) {
/* 349 */           float f1 = (s1[src1PixelOffset] & 0xFFFF);
/* 350 */           float f2 = (s2[src2PixelOffset] & 0xFFFF);
/* 352 */           if (f1 == 0.0F) {
/* 354 */             d[dstPixelOffset] = 0;
/* 355 */           } else if (f2 == 0.0F) {
/* 358 */             d[dstPixelOffset] = -1;
/*     */           } else {
/* 360 */             d[dstPixelOffset] = ImageUtil.clampRoundUShort(f1 / f2);
/*     */           } 
/* 363 */           src1PixelOffset += src1PixelStride;
/* 364 */           src2PixelOffset += src2PixelStride;
/* 365 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void shortLoop(int dstNumBands, int dstWidth, int dstHeight, int src1LineStride, int src1PixelStride, int[] src1BandOffsets, short[][] src1Data, int src2LineStride, int src2PixelStride, int[] src2BandOffsets, short[][] src2Data, int dstLineStride, int dstPixelStride, int[] dstBandOffsets, short[][] dstData) {
/*     */     int s2b;
/* 379 */     for (int b = 0, s1b = 0; b < dstNumBands; 
/* 380 */       b++, s1b += this.s1bd, s2b += this.s2bd) {
/* 381 */       short[] s1 = src1Data[s1b];
/* 382 */       short[] s2 = src2Data[s2b];
/* 383 */       short[] d = dstData[b];
/* 385 */       int src1LineOffset = src1BandOffsets[s1b];
/* 386 */       int src2LineOffset = src2BandOffsets[s2b];
/* 387 */       int dstLineOffset = dstBandOffsets[b];
/* 389 */       for (int h = 0; h < dstHeight; h++) {
/* 390 */         int src1PixelOffset = src1LineOffset;
/* 391 */         int src2PixelOffset = src2LineOffset;
/* 392 */         int dstPixelOffset = dstLineOffset;
/* 393 */         src1LineOffset += src1LineStride;
/* 394 */         src2LineOffset += src2LineStride;
/* 395 */         dstLineOffset += dstLineStride;
/* 397 */         for (int w = 0; w < dstWidth; w++) {
/* 398 */           float f1 = s1[src1PixelOffset];
/* 399 */           float f2 = s2[src2PixelOffset];
/* 401 */           if (f1 == 0.0F) {
/* 403 */             d[dstPixelOffset] = 0;
/* 404 */           } else if (f2 == 0.0F) {
/* 405 */             if (f1 < 0.0F) {
/* 406 */               d[dstPixelOffset] = Short.MIN_VALUE;
/*     */             } else {
/* 408 */               d[dstPixelOffset] = Short.MAX_VALUE;
/*     */             } 
/*     */           } else {
/* 411 */             d[dstPixelOffset] = ImageUtil.clampRoundShort(f1 / f2);
/*     */           } 
/* 414 */           src1PixelOffset += src1PixelStride;
/* 415 */           src2PixelOffset += src2PixelStride;
/* 416 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void intLoop(int dstNumBands, int dstWidth, int dstHeight, int src1LineStride, int src1PixelStride, int[] src1BandOffsets, int[][] src1Data, int src2LineStride, int src2PixelStride, int[] src2BandOffsets, int[][] src2Data, int dstLineStride, int dstPixelStride, int[] dstBandOffsets, int[][] dstData) {
/*     */     int b;
/*     */     int s1b;
/*     */     int s2b;
/* 434 */     switch (this.sampleModel.getTransferType()) {
/*     */       case 0:
/* 437 */         for (b = 0, s1b = 0, s2b = 0; b < dstNumBands; 
/* 438 */           b++, s1b += this.s1bd, s2b += this.s2bd) {
/* 439 */           int[] s1 = src1Data[s1b];
/* 440 */           int[] s2 = src2Data[s2b];
/* 441 */           int[] d = dstData[b];
/* 443 */           int src1LineOffset = src1BandOffsets[s1b];
/* 444 */           int src2LineOffset = src2BandOffsets[s2b];
/* 445 */           int dstLineOffset = dstBandOffsets[b];
/* 447 */           for (int h = 0; h < dstHeight; h++) {
/* 448 */             int src1PixelOffset = src1LineOffset;
/* 449 */             int src2PixelOffset = src2LineOffset;
/* 450 */             int dstPixelOffset = dstLineOffset;
/* 451 */             src1LineOffset += src1LineStride;
/* 452 */             src2LineOffset += src2LineStride;
/* 453 */             dstLineOffset += dstLineStride;
/* 455 */             for (int w = 0; w < dstWidth; w++) {
/* 456 */               float f1 = (s1[src1PixelOffset] & 0xFF);
/* 457 */               float f2 = (s2[src2PixelOffset] & 0xFF);
/* 459 */               if (f1 == 0.0F) {
/* 461 */                 d[dstPixelOffset] = 0;
/* 462 */               } else if (f2 == 0.0F) {
/* 464 */                 d[dstPixelOffset] = 255;
/*     */               } else {
/* 466 */                 d[dstPixelOffset] = ImageUtil.clampRoundByte(f1 / f2);
/*     */               } 
/* 469 */               src1PixelOffset += src1PixelStride;
/* 470 */               src2PixelOffset += src2PixelStride;
/* 471 */               dstPixelOffset += dstPixelStride;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       case 1:
/* 478 */         for (b = 0, s1b = 0, s2b = 0; b < dstNumBands; 
/* 479 */           b++, s1b += this.s1bd, s2b += this.s2bd) {
/* 480 */           int[] s1 = src1Data[s1b];
/* 481 */           int[] s2 = src2Data[s2b];
/* 482 */           int[] d = dstData[b];
/* 484 */           int src1LineOffset = src1BandOffsets[s1b];
/* 485 */           int src2LineOffset = src2BandOffsets[s2b];
/* 486 */           int dstLineOffset = dstBandOffsets[b];
/* 488 */           for (int h = 0; h < dstHeight; h++) {
/* 489 */             int src1PixelOffset = src1LineOffset;
/* 490 */             int src2PixelOffset = src2LineOffset;
/* 491 */             int dstPixelOffset = dstLineOffset;
/* 492 */             src1LineOffset += src1LineStride;
/* 493 */             src2LineOffset += src2LineStride;
/* 494 */             dstLineOffset += dstLineStride;
/* 496 */             for (int w = 0; w < dstWidth; w++) {
/* 497 */               float f1 = (s1[src1PixelOffset] & 0xFFFF);
/* 498 */               float f2 = (s2[src2PixelOffset] & 0xFFFF);
/* 500 */               if (f1 == 0.0F) {
/* 502 */                 d[dstPixelOffset] = 0;
/* 503 */               } else if (f2 == 0.0F) {
/* 505 */                 d[dstPixelOffset] = -1;
/*     */               } else {
/* 507 */                 d[dstPixelOffset] = ImageUtil.clampRoundUShort(f1 / f2);
/*     */               } 
/* 510 */               src1PixelOffset += src1PixelStride;
/* 511 */               src2PixelOffset += src2PixelStride;
/* 512 */               dstPixelOffset += dstPixelStride;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       case 2:
/* 519 */         for (b = 0, s1b = 0, s2b = 0; b < dstNumBands; 
/* 520 */           b++, s1b += this.s1bd, s2b += this.s2bd) {
/* 521 */           int[] s1 = src1Data[s1b];
/* 522 */           int[] s2 = src2Data[s2b];
/* 523 */           int[] d = dstData[b];
/* 525 */           int src1LineOffset = src1BandOffsets[s1b];
/* 526 */           int src2LineOffset = src2BandOffsets[s2b];
/* 527 */           int dstLineOffset = dstBandOffsets[b];
/* 529 */           for (int h = 0; h < dstHeight; h++) {
/* 530 */             int src1PixelOffset = src1LineOffset;
/* 531 */             int src2PixelOffset = src2LineOffset;
/* 532 */             int dstPixelOffset = dstLineOffset;
/* 533 */             src1LineOffset += src1LineStride;
/* 534 */             src2LineOffset += src2LineStride;
/* 535 */             dstLineOffset += dstLineStride;
/* 537 */             for (int w = 0; w < dstWidth; w++) {
/* 538 */               float f1 = s1[src1PixelOffset];
/* 539 */               float f2 = s2[src2PixelOffset];
/* 541 */               if (f1 == 0.0F) {
/* 542 */                 d[dstPixelOffset] = 0;
/* 543 */               } else if (f2 == 0.0F) {
/* 544 */                 if (f1 < 0.0F) {
/* 547 */                   d[dstPixelOffset] = -32768;
/*     */                 } else {
/* 549 */                   d[dstPixelOffset] = 32767;
/*     */                 } 
/*     */               } else {
/* 552 */                 d[dstPixelOffset] = ImageUtil.clampRoundShort(f1 / f2);
/*     */               } 
/* 555 */               src1PixelOffset += src1PixelStride;
/* 556 */               src2PixelOffset += src2PixelStride;
/* 557 */               dstPixelOffset += dstPixelStride;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       case 3:
/* 564 */         for (b = 0, s1b = 0, s2b = 0; b < dstNumBands; 
/* 565 */           b++, s1b += this.s1bd, s2b += this.s2bd) {
/* 566 */           int[] s1 = src1Data[s1b];
/* 567 */           int[] s2 = src2Data[s2b];
/* 568 */           int[] d = dstData[b];
/* 570 */           int src1LineOffset = src1BandOffsets[s1b];
/* 571 */           int src2LineOffset = src2BandOffsets[s2b];
/* 572 */           int dstLineOffset = dstBandOffsets[b];
/* 574 */           for (int h = 0; h < dstHeight; h++) {
/* 575 */             int src1PixelOffset = src1LineOffset;
/* 576 */             int src2PixelOffset = src2LineOffset;
/* 577 */             int dstPixelOffset = dstLineOffset;
/* 578 */             src1LineOffset += src1LineStride;
/* 579 */             src2LineOffset += src2LineStride;
/* 580 */             dstLineOffset += dstLineStride;
/* 582 */             for (int w = 0; w < dstWidth; w++) {
/* 583 */               float f1 = s1[src1PixelOffset];
/* 584 */               float f2 = s2[src2PixelOffset];
/* 586 */               if (f1 == 0.0F) {
/* 588 */                 d[dstPixelOffset] = 0;
/* 589 */               } else if (f2 == 0.0F) {
/* 590 */                 if (f1 < 0.0F) {
/* 591 */                   d[dstPixelOffset] = Integer.MIN_VALUE;
/*     */                 } else {
/* 593 */                   d[dstPixelOffset] = Integer.MAX_VALUE;
/*     */                 } 
/*     */               } else {
/* 596 */                 d[dstPixelOffset] = ImageUtil.clampRoundInt(f1 / f2);
/*     */               } 
/* 599 */               src1PixelOffset += src1PixelStride;
/* 600 */               src2PixelOffset += src2PixelStride;
/* 601 */               dstPixelOffset += dstPixelStride;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */         break;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void floatLoop(int dstNumBands, int dstWidth, int dstHeight, int src1LineStride, int src1PixelStride, int[] src1BandOffsets, float[][] src1Data, int src2LineStride, int src2PixelStride, int[] src2BandOffsets, float[][] src2Data, int dstLineStride, int dstPixelStride, int[] dstBandOffsets, float[][] dstData) {
/*     */     int s2b;
/* 617 */     for (int b = 0, s1b = 0; b < dstNumBands; 
/* 618 */       b++, s1b += this.s1bd, s2b += this.s2bd) {
/* 619 */       float[] s1 = src1Data[s1b];
/* 620 */       float[] s2 = src2Data[s2b];
/* 621 */       float[] d = dstData[b];
/* 622 */       int src1LineOffset = src1BandOffsets[s1b];
/* 623 */       int src2LineOffset = src2BandOffsets[s2b];
/* 624 */       int dstLineOffset = dstBandOffsets[b];
/* 626 */       for (int h = 0; h < dstHeight; h++) {
/* 627 */         int src1PixelOffset = src1LineOffset;
/* 628 */         int src2PixelOffset = src2LineOffset;
/* 629 */         int dstPixelOffset = dstLineOffset;
/* 630 */         src1LineOffset += src1LineStride;
/* 631 */         src2LineOffset += src2LineStride;
/* 632 */         dstLineOffset += dstLineStride;
/* 634 */         for (int w = 0; w < dstWidth; w++) {
/* 636 */           d[dstPixelOffset] = s1[src1PixelOffset] / s2[src2PixelOffset];
/* 638 */           src1PixelOffset += src1PixelStride;
/* 639 */           src2PixelOffset += src2PixelStride;
/* 640 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void doubleLoop(int dstNumBands, int dstWidth, int dstHeight, int src1LineStride, int src1PixelStride, int[] src1BandOffsets, double[][] src1Data, int src2LineStride, int src2PixelStride, int[] src2BandOffsets, double[][] src2Data, int dstLineStride, int dstPixelStride, int[] dstBandOffsets, double[][] dstData) {
/*     */     int s2b;
/* 654 */     for (int b = 0, s1b = 0; b < dstNumBands; 
/* 655 */       b++, s1b += this.s1bd, s2b += this.s2bd) {
/* 656 */       double[] s1 = src1Data[s1b];
/* 657 */       double[] s2 = src2Data[s2b];
/* 658 */       double[] d = dstData[b];
/* 659 */       int src1LineOffset = src1BandOffsets[s1b];
/* 660 */       int src2LineOffset = src2BandOffsets[s2b];
/* 661 */       int dstLineOffset = dstBandOffsets[b];
/* 663 */       for (int h = 0; h < dstHeight; h++) {
/* 664 */         int src1PixelOffset = src1LineOffset;
/* 665 */         int src2PixelOffset = src2LineOffset;
/* 666 */         int dstPixelOffset = dstLineOffset;
/* 667 */         src1LineOffset += src1LineStride;
/* 668 */         src2LineOffset += src2LineStride;
/* 669 */         dstLineOffset += dstLineStride;
/* 671 */         for (int w = 0; w < dstWidth; w++) {
/* 673 */           d[dstPixelOffset] = s1[src1PixelOffset] / s2[src2PixelOffset];
/* 675 */           src1PixelOffset += src1PixelStride;
/* 676 */           src2PixelOffset += src2PixelStride;
/* 677 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\DivideOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */