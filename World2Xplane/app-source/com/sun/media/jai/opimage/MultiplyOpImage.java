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
/*     */ final class MultiplyOpImage extends PointOpImage {
/*     */   private byte[][] multiplyTableByte;
/*     */   
/*  66 */   private int s1bd = 1;
/*     */   
/*  69 */   private int s2bd = 1;
/*     */   
/*     */   public MultiplyOpImage(RenderedImage source1, RenderedImage source2, Map config, ImageLayout layout) {
/*  92 */     super(source1, source2, layout, config, true);
/*  95 */     int numBands1 = source1.getSampleModel().getNumBands();
/*  96 */     int numBands2 = source2.getSampleModel().getNumBands();
/* 101 */     if (layout != null && layout.isValid(256)) {
/* 102 */       SampleModel sm = layout.getSampleModel(null);
/* 103 */       int numBandsDst = sm.getNumBands();
/* 107 */       if (numBandsDst > 1 && ((numBands1 == 1 && numBands2 > 1) || (numBands2 == 1 && numBands1 > 1))) {
/* 112 */         numBandsDst = Math.min(Math.max(numBands1, numBands2), numBandsDst);
/* 116 */         if (numBandsDst != this.sampleModel.getNumBands()) {
/* 117 */           this.sampleModel = RasterFactory.createComponentSampleModel(sm, this.sampleModel.getTransferType(), this.sampleModel.getWidth(), this.sampleModel.getHeight(), numBandsDst);
/* 125 */           if (this.colorModel != null && !JDKWorkarounds.areCompatibleDataModels(this.sampleModel, this.colorModel))
/* 128 */             this.colorModel = ImageUtil.getCompatibleColorModel(this.sampleModel, config); 
/*     */         } 
/* 135 */         this.s1bd = (numBands1 == 1) ? 0 : 1;
/* 136 */         this.s2bd = (numBands2 == 1) ? 0 : 1;
/*     */       } 
/*     */     } 
/* 140 */     if (this.sampleModel.getTransferType() == 0) {
/* 142 */       this.multiplyTableByte = new byte[256][256];
/* 143 */       for (int j = 0; j < 256; j++) {
/* 144 */         byte[] array = this.multiplyTableByte[j];
/* 145 */         for (int i = 0; i < 256; i++)
/* 146 */           array[i] = ImageUtil.clampByte(i * j); 
/*     */       } 
/*     */     } 
/* 152 */     permitInPlaceOperation();
/*     */   }
/*     */   
/*     */   protected void computeRect(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/* 168 */     RasterFormatTag[] formatTags = getFormatTags();
/* 171 */     RasterAccessor s1 = new RasterAccessor(sources[0], destRect, formatTags[0], getSource(0).getColorModel());
/* 174 */     RasterAccessor s2 = new RasterAccessor(sources[1], destRect, formatTags[1], getSource(1).getColorModel());
/* 177 */     RasterAccessor d = new RasterAccessor(dest, destRect, formatTags[2], getColorModel());
/* 180 */     if (d.isBinary()) {
/* 181 */       byte[] src1Bits = s1.getBinaryDataArray();
/* 182 */       byte[] src2Bits = s2.getBinaryDataArray();
/* 183 */       byte[] dstBits = d.getBinaryDataArray();
/* 185 */       int length = dstBits.length;
/* 186 */       for (int i = 0; i < length; i++)
/* 188 */         dstBits[i] = (byte)(src1Bits[i] & src2Bits[i]); 
/* 191 */       d.copyBinaryDataToRaster();
/*     */       return;
/*     */     } 
/* 196 */     int src1LineStride = s1.getScanlineStride();
/* 197 */     int src1PixelStride = s1.getPixelStride();
/* 198 */     int[] src1BandOffsets = s1.getBandOffsets();
/* 200 */     int src2LineStride = s2.getScanlineStride();
/* 201 */     int src2PixelStride = s2.getPixelStride();
/* 202 */     int[] src2BandOffsets = s2.getBandOffsets();
/* 204 */     int dstNumBands = d.getNumBands();
/* 205 */     int dstWidth = d.getWidth();
/* 206 */     int dstHeight = d.getHeight();
/* 207 */     int dstLineStride = d.getScanlineStride();
/* 208 */     int dstPixelStride = d.getPixelStride();
/* 209 */     int[] dstBandOffsets = d.getBandOffsets();
/* 211 */     switch (d.getDataType()) {
/*     */       case 0:
/* 214 */         byteLoop(dstNumBands, dstWidth, dstHeight, src1LineStride, src1PixelStride, src1BandOffsets, s1.getByteDataArrays(), src2LineStride, src2PixelStride, src2BandOffsets, s2.getByteDataArrays(), dstLineStride, dstPixelStride, dstBandOffsets, d.getByteDataArrays());
/*     */         break;
/*     */       case 1:
/* 224 */         ushortLoop(dstNumBands, dstWidth, dstHeight, src1LineStride, src1PixelStride, src1BandOffsets, s1.getShortDataArrays(), src2LineStride, src2PixelStride, src2BandOffsets, s2.getShortDataArrays(), dstLineStride, dstPixelStride, dstBandOffsets, d.getShortDataArrays());
/*     */         break;
/*     */       case 2:
/* 234 */         shortLoop(dstNumBands, dstWidth, dstHeight, src1LineStride, src1PixelStride, src1BandOffsets, s1.getShortDataArrays(), src2LineStride, src2PixelStride, src2BandOffsets, s2.getShortDataArrays(), dstLineStride, dstPixelStride, dstBandOffsets, d.getShortDataArrays());
/*     */         break;
/*     */       case 3:
/* 244 */         intLoop(dstNumBands, dstWidth, dstHeight, src1LineStride, src1PixelStride, src1BandOffsets, s1.getIntDataArrays(), src2LineStride, src2PixelStride, src2BandOffsets, s2.getIntDataArrays(), dstLineStride, dstPixelStride, dstBandOffsets, d.getIntDataArrays());
/*     */         break;
/*     */       case 4:
/* 254 */         floatLoop(dstNumBands, dstWidth, dstHeight, src1LineStride, src1PixelStride, src1BandOffsets, s1.getFloatDataArrays(), src2LineStride, src2PixelStride, src2BandOffsets, s2.getFloatDataArrays(), dstLineStride, dstPixelStride, dstBandOffsets, d.getFloatDataArrays());
/*     */         break;
/*     */       case 5:
/* 264 */         doubleLoop(dstNumBands, dstWidth, dstHeight, src1LineStride, src1PixelStride, src1BandOffsets, s1.getDoubleDataArrays(), src2LineStride, src2PixelStride, src2BandOffsets, s2.getDoubleDataArrays(), dstLineStride, dstPixelStride, dstBandOffsets, d.getDoubleDataArrays());
/*     */         break;
/*     */     } 
/* 274 */     if (d.needsClamping())
/* 275 */       d.clampDataArrays(); 
/* 278 */     d.copyDataToRaster();
/*     */   }
/*     */   
/*     */   private void byteLoop(int dstNumBands, int dstWidth, int dstHeight, int src1LineStride, int src1PixelStride, int[] src1BandOffsets, byte[][] src1Data, int src2LineStride, int src2PixelStride, int[] src2BandOffsets, byte[][] src2Data, int dstLineStride, int dstPixelStride, int[] dstBandOffsets, byte[][] dstData) {
/*     */     int s2b;
/* 289 */     for (int b = 0, s1b = 0; b < dstNumBands; 
/* 290 */       b++, s1b += this.s1bd, s2b += this.s2bd) {
/* 291 */       byte[] s1 = src1Data[s1b];
/* 292 */       byte[] s2 = src2Data[s2b];
/* 293 */       byte[] d = dstData[b];
/* 294 */       int src1LineOffset = src1BandOffsets[s1b];
/* 295 */       int src2LineOffset = src2BandOffsets[s2b];
/* 296 */       int dstLineOffset = dstBandOffsets[b];
/* 298 */       for (int h = 0; h < dstHeight; h++) {
/* 299 */         int src1PixelOffset = src1LineOffset;
/* 300 */         int src2PixelOffset = src2LineOffset;
/* 301 */         int dstPixelOffset = dstLineOffset;
/* 302 */         src1LineOffset += src1LineStride;
/* 303 */         src2LineOffset += src2LineStride;
/* 304 */         dstLineOffset += dstLineStride;
/* 306 */         for (int w = 0; w < dstWidth; w++) {
/* 307 */           d[dstPixelOffset] = this.multiplyTableByte[s1[src1PixelOffset] & 0xFF][s2[src2PixelOffset] & 0xFF];
/* 309 */           src1PixelOffset += src1PixelStride;
/* 310 */           src2PixelOffset += src2PixelStride;
/* 311 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void ushortLoop(int dstNumBands, int dstWidth, int dstHeight, int src1LineStride, int src1PixelStride, int[] src1BandOffsets, short[][] src1Data, int src2LineStride, int src2PixelStride, int[] src2BandOffsets, short[][] src2Data, int dstLineStride, int dstPixelStride, int[] dstBandOffsets, short[][] dstData) {
/*     */     int s2b;
/* 325 */     for (int b = 0, s1b = 0; b < dstNumBands; 
/* 326 */       b++, s1b += this.s1bd, s2b += this.s2bd) {
/* 327 */       short[] s1 = src1Data[s1b];
/* 328 */       short[] s2 = src2Data[s2b];
/* 329 */       short[] d = dstData[b];
/* 330 */       int src1LineOffset = src1BandOffsets[s1b];
/* 331 */       int src2LineOffset = src2BandOffsets[s2b];
/* 332 */       int dstLineOffset = dstBandOffsets[b];
/* 334 */       for (int h = 0; h < dstHeight; h++) {
/* 335 */         int src1PixelOffset = src1LineOffset;
/* 336 */         int src2PixelOffset = src2LineOffset;
/* 337 */         int dstPixelOffset = dstLineOffset;
/* 338 */         src1LineOffset += src1LineStride;
/* 339 */         src2LineOffset += src2LineStride;
/* 340 */         dstLineOffset += dstLineStride;
/* 342 */         for (int w = 0; w < dstWidth; w++) {
/* 343 */           d[dstPixelOffset] = ImageUtil.clampUShort((s1[src1PixelOffset] & 0xFFFF) * (s2[src2PixelOffset] & 0xFFFF));
/* 347 */           src1PixelOffset += src1PixelStride;
/* 348 */           src2PixelOffset += src2PixelStride;
/* 349 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void shortLoop(int dstNumBands, int dstWidth, int dstHeight, int src1LineStride, int src1PixelStride, int[] src1BandOffsets, short[][] src1Data, int src2LineStride, int src2PixelStride, int[] src2BandOffsets, short[][] src2Data, int dstLineStride, int dstPixelStride, int[] dstBandOffsets, short[][] dstData) {
/*     */     int s2b;
/* 363 */     for (int b = 0, s1b = 0; b < dstNumBands; 
/* 364 */       b++, s1b += this.s1bd, s2b += this.s2bd) {
/* 365 */       short[] s1 = src1Data[s1b];
/* 366 */       short[] s2 = src2Data[s2b];
/* 367 */       short[] d = dstData[b];
/* 368 */       int src1LineOffset = src1BandOffsets[s1b];
/* 369 */       int src2LineOffset = src2BandOffsets[s2b];
/* 370 */       int dstLineOffset = dstBandOffsets[b];
/* 372 */       for (int h = 0; h < dstHeight; h++) {
/* 373 */         int src1PixelOffset = src1LineOffset;
/* 374 */         int src2PixelOffset = src2LineOffset;
/* 375 */         int dstPixelOffset = dstLineOffset;
/* 376 */         src1LineOffset += src1LineStride;
/* 377 */         src2LineOffset += src2LineStride;
/* 378 */         dstLineOffset += dstLineStride;
/* 380 */         for (int w = 0; w < dstWidth; w++) {
/* 381 */           d[dstPixelOffset] = ImageUtil.clampShort(s1[src1PixelOffset] * s2[src2PixelOffset]);
/* 383 */           src1PixelOffset += src1PixelStride;
/* 384 */           src2PixelOffset += src2PixelStride;
/* 385 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void intLoop(int dstNumBands, int dstWidth, int dstHeight, int src1LineStride, int src1PixelStride, int[] src1BandOffsets, int[][] src1Data, int src2LineStride, int src2PixelStride, int[] src2BandOffsets, int[][] src2Data, int dstLineStride, int dstPixelStride, int[] dstBandOffsets, int[][] dstData) {
/*     */     int b;
/*     */     int s1b;
/*     */     int s2b;
/* 403 */     switch (this.sampleModel.getTransferType()) {
/*     */       case 0:
/* 406 */         for (b = 0, s1b = 0, s2b = 0; b < dstNumBands; 
/* 407 */           b++, s1b += this.s1bd, s2b += this.s2bd) {
/* 408 */           int[] s1 = src1Data[s1b];
/* 409 */           int[] s2 = src2Data[s2b];
/* 410 */           int[] d = dstData[b];
/* 411 */           int src1LineOffset = src1BandOffsets[s1b];
/* 412 */           int src2LineOffset = src2BandOffsets[s2b];
/* 413 */           int dstLineOffset = dstBandOffsets[b];
/* 415 */           for (int h = 0; h < dstHeight; h++) {
/* 416 */             int src1PixelOffset = src1LineOffset;
/* 417 */             int src2PixelOffset = src2LineOffset;
/* 418 */             int dstPixelOffset = dstLineOffset;
/* 419 */             src1LineOffset += src1LineStride;
/* 420 */             src2LineOffset += src2LineStride;
/* 421 */             dstLineOffset += dstLineStride;
/* 423 */             for (int w = 0; w < dstWidth; w++) {
/* 424 */               d[dstPixelOffset] = ImageUtil.clampByte(s1[src1PixelOffset] * s2[src2PixelOffset]);
/* 427 */               src1PixelOffset += src1PixelStride;
/* 428 */               src2PixelOffset += src2PixelStride;
/* 429 */               dstPixelOffset += dstPixelStride;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       case 1:
/* 436 */         for (b = 0, s1b = 0, s2b = 0; b < dstNumBands; 
/* 437 */           b++, s1b += this.s1bd, s2b += this.s2bd) {
/* 438 */           int[] s1 = src1Data[s1b];
/* 439 */           int[] s2 = src2Data[s2b];
/* 440 */           int[] d = dstData[b];
/* 441 */           int src1LineOffset = src1BandOffsets[s1b];
/* 442 */           int src2LineOffset = src2BandOffsets[s2b];
/* 443 */           int dstLineOffset = dstBandOffsets[b];
/* 445 */           for (int h = 0; h < dstHeight; h++) {
/* 446 */             int src1PixelOffset = src1LineOffset;
/* 447 */             int src2PixelOffset = src2LineOffset;
/* 448 */             int dstPixelOffset = dstLineOffset;
/* 449 */             src1LineOffset += src1LineStride;
/* 450 */             src2LineOffset += src2LineStride;
/* 451 */             dstLineOffset += dstLineStride;
/* 453 */             for (int w = 0; w < dstWidth; w++) {
/* 454 */               d[dstPixelOffset] = ImageUtil.clampUShort(s1[src1PixelOffset] * s2[src2PixelOffset]);
/* 456 */               src1PixelOffset += src1PixelStride;
/* 457 */               src2PixelOffset += src2PixelStride;
/* 458 */               dstPixelOffset += dstPixelStride;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       case 2:
/* 465 */         for (b = 0, s1b = 0, s2b = 0; b < dstNumBands; 
/* 466 */           b++, s1b += this.s1bd, s2b += this.s2bd) {
/* 467 */           int[] s1 = src1Data[s1b];
/* 468 */           int[] s2 = src2Data[s2b];
/* 469 */           int[] d = dstData[b];
/* 470 */           int src1LineOffset = src1BandOffsets[s1b];
/* 471 */           int src2LineOffset = src2BandOffsets[s2b];
/* 472 */           int dstLineOffset = dstBandOffsets[b];
/* 474 */           for (int h = 0; h < dstHeight; h++) {
/* 475 */             int src1PixelOffset = src1LineOffset;
/* 476 */             int src2PixelOffset = src2LineOffset;
/* 477 */             int dstPixelOffset = dstLineOffset;
/* 478 */             src1LineOffset += src1LineStride;
/* 479 */             src2LineOffset += src2LineStride;
/* 480 */             dstLineOffset += dstLineStride;
/* 482 */             for (int w = 0; w < dstWidth; w++) {
/* 483 */               d[dstPixelOffset] = ImageUtil.clampShort(s1[src1PixelOffset] * s2[src2PixelOffset]);
/* 485 */               src1PixelOffset += src1PixelStride;
/* 486 */               src2PixelOffset += src2PixelStride;
/* 487 */               dstPixelOffset += dstPixelStride;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       case 3:
/* 494 */         for (b = 0, s1b = 0, s2b = 0; b < dstNumBands; 
/* 495 */           b++, s1b += this.s1bd, s2b += this.s2bd) {
/* 496 */           int[] s1 = src1Data[s1b];
/* 497 */           int[] s2 = src2Data[s2b];
/* 498 */           int[] d = dstData[b];
/* 499 */           int src1LineOffset = src1BandOffsets[s1b];
/* 500 */           int src2LineOffset = src2BandOffsets[s2b];
/* 501 */           int dstLineOffset = dstBandOffsets[b];
/* 503 */           for (int h = 0; h < dstHeight; h++) {
/* 504 */             int src1PixelOffset = src1LineOffset;
/* 505 */             int src2PixelOffset = src2LineOffset;
/* 506 */             int dstPixelOffset = dstLineOffset;
/* 507 */             src1LineOffset += src1LineStride;
/* 508 */             src2LineOffset += src2LineStride;
/* 509 */             dstLineOffset += dstLineStride;
/* 511 */             for (int w = 0; w < dstWidth; w++) {
/* 512 */               d[dstPixelOffset] = ImageUtil.clampInt(s1[src1PixelOffset] * s2[src2PixelOffset]);
/* 514 */               src1PixelOffset += src1PixelStride;
/* 515 */               src2PixelOffset += src2PixelStride;
/* 516 */               dstPixelOffset += dstPixelStride;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */         break;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void floatLoop(int dstNumBands, int dstWidth, int dstHeight, int src1LineStride, int src1PixelStride, int[] src1BandOffsets, float[][] src1Data, int src2LineStride, int src2PixelStride, int[] src2BandOffsets, float[][] src2Data, int dstLineStride, int dstPixelStride, int[] dstBandOffsets, float[][] dstData) {
/*     */     int s2b;
/* 532 */     for (int b = 0, s1b = 0; b < dstNumBands; 
/* 533 */       b++, s1b += this.s1bd, s2b += this.s2bd) {
/* 534 */       float[] s1 = src1Data[s1b];
/* 535 */       float[] s2 = src2Data[s2b];
/* 536 */       float[] d = dstData[b];
/* 537 */       int src1LineOffset = src1BandOffsets[s1b];
/* 538 */       int src2LineOffset = src2BandOffsets[s2b];
/* 539 */       int dstLineOffset = dstBandOffsets[b];
/* 541 */       for (int h = 0; h < dstHeight; h++) {
/* 542 */         int src1PixelOffset = src1LineOffset;
/* 543 */         int src2PixelOffset = src2LineOffset;
/* 544 */         int dstPixelOffset = dstLineOffset;
/* 545 */         src1LineOffset += src1LineStride;
/* 546 */         src2LineOffset += src2LineStride;
/* 547 */         dstLineOffset += dstLineStride;
/* 549 */         for (int w = 0; w < dstWidth; w++) {
/* 550 */           d[dstPixelOffset] = s1[src1PixelOffset] * s2[src2PixelOffset];
/* 552 */           src1PixelOffset += src1PixelStride;
/* 553 */           src2PixelOffset += src2PixelStride;
/* 554 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void doubleLoop(int dstNumBands, int dstWidth, int dstHeight, int src1LineStride, int src1PixelStride, int[] src1BandOffsets, double[][] src1Data, int src2LineStride, int src2PixelStride, int[] src2BandOffsets, double[][] src2Data, int dstLineStride, int dstPixelStride, int[] dstBandOffsets, double[][] dstData) {
/*     */     int s2b;
/* 568 */     for (int b = 0, s1b = 0; b < dstNumBands; 
/* 569 */       b++, s1b += this.s1bd, s2b += this.s2bd) {
/* 570 */       double[] s1 = src1Data[s1b];
/* 571 */       double[] s2 = src2Data[s2b];
/* 572 */       double[] d = dstData[b];
/* 573 */       int src1LineOffset = src1BandOffsets[s1b];
/* 574 */       int src2LineOffset = src2BandOffsets[s2b];
/* 575 */       int dstLineOffset = dstBandOffsets[b];
/* 577 */       for (int h = 0; h < dstHeight; h++) {
/* 578 */         int src1PixelOffset = src1LineOffset;
/* 579 */         int src2PixelOffset = src2LineOffset;
/* 580 */         int dstPixelOffset = dstLineOffset;
/* 581 */         src1LineOffset += src1LineStride;
/* 582 */         src2LineOffset += src2LineStride;
/* 583 */         dstLineOffset += dstLineStride;
/* 585 */         for (int w = 0; w < dstWidth; w++) {
/* 586 */           d[dstPixelOffset] = s1[src1PixelOffset] * s2[src2PixelOffset];
/* 588 */           src1PixelOffset += src1PixelStride;
/* 589 */           src2PixelOffset += src2PixelStride;
/* 590 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\MultiplyOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */