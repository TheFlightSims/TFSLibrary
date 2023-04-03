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
/*     */ final class AddOpImage extends PointOpImage {
/*  64 */   private int s1bd = 1;
/*     */   
/*  67 */   private int s2bd = 1;
/*     */   
/*     */   private boolean areBinarySampleModels = false;
/*     */   
/*     */   public AddOpImage(RenderedImage source1, RenderedImage source2, Map config, ImageLayout layout) {
/*  93 */     super(source1, source2, layout, config, true);
/*  95 */     if (ImageUtil.isBinary(getSampleModel()) && ImageUtil.isBinary(source1.getSampleModel()) && ImageUtil.isBinary(source2.getSampleModel())) {
/*  99 */       this.areBinarySampleModels = true;
/*     */     } else {
/* 102 */       int numBands1 = source1.getSampleModel().getNumBands();
/* 103 */       int numBands2 = source2.getSampleModel().getNumBands();
/* 108 */       if (layout != null && layout.isValid(256)) {
/* 109 */         SampleModel sm = layout.getSampleModel(null);
/* 110 */         int numBandsDst = sm.getNumBands();
/* 114 */         if (numBandsDst > 1 && ((numBands1 == 1 && numBands2 > 1) || (numBands2 == 1 && numBands1 > 1))) {
/* 119 */           numBandsDst = Math.min(Math.max(numBands1, numBands2), numBandsDst);
/* 123 */           if (numBandsDst != this.sampleModel.getNumBands()) {
/* 124 */             this.sampleModel = RasterFactory.createComponentSampleModel(sm, this.sampleModel.getTransferType(), this.sampleModel.getWidth(), this.sampleModel.getHeight(), numBandsDst);
/* 132 */             if (this.colorModel != null && !JDKWorkarounds.areCompatibleDataModels(this.sampleModel, this.colorModel))
/* 135 */               this.colorModel = ImageUtil.getCompatibleColorModel(this.sampleModel, config); 
/*     */           } 
/* 142 */           this.s1bd = (numBands1 == 1) ? 0 : 1;
/* 143 */           this.s2bd = (numBands2 == 1) ? 0 : 1;
/*     */         } 
/*     */       } 
/*     */     } 
/* 149 */     permitInPlaceOperation();
/*     */   }
/*     */   
/*     */   protected void computeRect(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/* 164 */     if (this.areBinarySampleModels) {
/* 166 */       RasterFormatTag[] arrayOfRasterFormatTag = getFormatTags();
/* 169 */       RasterAccessor rasterAccessor1 = new RasterAccessor(sources[0], destRect, arrayOfRasterFormatTag[0], getSourceImage(0).getColorModel());
/* 173 */       RasterAccessor rasterAccessor2 = new RasterAccessor(sources[1], destRect, arrayOfRasterFormatTag[1], getSourceImage(1).getColorModel());
/* 177 */       RasterAccessor rasterAccessor3 = new RasterAccessor(dest, destRect, arrayOfRasterFormatTag[2], getColorModel());
/* 181 */       if (rasterAccessor3.isBinary()) {
/* 182 */         byte[] src1Bits = rasterAccessor1.getBinaryDataArray();
/* 183 */         byte[] src2Bits = rasterAccessor2.getBinaryDataArray();
/* 184 */         byte[] dstBits = rasterAccessor3.getBinaryDataArray();
/* 186 */         int length = dstBits.length;
/* 187 */         for (int i = 0; i < length; i++)
/* 189 */           dstBits[i] = (byte)(src1Bits[i] | src2Bits[i]); 
/* 192 */         rasterAccessor3.copyBinaryDataToRaster();
/*     */         return;
/*     */       } 
/*     */     } 
/* 199 */     RasterFormatTag[] formatTags = getFormatTags();
/* 201 */     RasterAccessor s1 = new RasterAccessor(sources[0], destRect, formatTags[0], getSourceImage(0).getColorModel());
/* 204 */     RasterAccessor s2 = new RasterAccessor(sources[1], destRect, formatTags[1], getSourceImage(1).getColorModel());
/* 207 */     RasterAccessor d = new RasterAccessor(dest, destRect, formatTags[2], getColorModel());
/* 210 */     switch (d.getDataType()) {
/*     */       case 0:
/* 212 */         computeRectByte(s1, s2, d);
/*     */         break;
/*     */       case 1:
/* 215 */         computeRectUShort(s1, s2, d);
/*     */         break;
/*     */       case 2:
/* 218 */         computeRectShort(s1, s2, d);
/*     */         break;
/*     */       case 3:
/* 221 */         computeRectInt(s1, s2, d);
/*     */         break;
/*     */       case 4:
/* 224 */         computeRectFloat(s1, s2, d);
/*     */         break;
/*     */       case 5:
/* 227 */         computeRectDouble(s1, s2, d);
/*     */         break;
/*     */     } 
/* 231 */     if (d.needsClamping())
/* 232 */       d.clampDataArrays(); 
/* 234 */     d.copyDataToRaster();
/*     */   }
/*     */   
/*     */   private void computeRectByte(RasterAccessor src1, RasterAccessor src2, RasterAccessor dst) {
/* 240 */     int s1LineStride = src1.getScanlineStride();
/* 241 */     int s1PixelStride = src1.getPixelStride();
/* 242 */     int[] s1BandOffsets = src1.getBandOffsets();
/* 243 */     byte[][] s1Data = src1.getByteDataArrays();
/* 245 */     int s2LineStride = src2.getScanlineStride();
/* 246 */     int s2PixelStride = src2.getPixelStride();
/* 247 */     int[] s2BandOffsets = src2.getBandOffsets();
/* 248 */     byte[][] s2Data = src2.getByteDataArrays();
/* 250 */     int dwidth = dst.getWidth();
/* 251 */     int dheight = dst.getHeight();
/* 252 */     int bands = dst.getNumBands();
/* 253 */     int dLineStride = dst.getScanlineStride();
/* 254 */     int dPixelStride = dst.getPixelStride();
/* 255 */     int[] dBandOffsets = dst.getBandOffsets();
/* 256 */     byte[][] dData = dst.getByteDataArrays();
/*     */     int s2b;
/* 258 */     for (int b = 0, s1b = 0; b < bands; 
/* 259 */       b++, s1b += this.s1bd, s2b += this.s2bd) {
/* 260 */       byte[] s1 = s1Data[s1b];
/* 261 */       byte[] s2 = s2Data[s2b];
/* 262 */       byte[] d = dData[b];
/* 264 */       int s1LineOffset = s1BandOffsets[s1b];
/* 265 */       int s2LineOffset = s2BandOffsets[s2b];
/* 266 */       int dLineOffset = dBandOffsets[b];
/* 268 */       for (int h = 0; h < dheight; h++) {
/* 269 */         int s1PixelOffset = s1LineOffset;
/* 270 */         int s2PixelOffset = s2LineOffset;
/* 271 */         int dPixelOffset = dLineOffset;
/* 273 */         s1LineOffset += s1LineStride;
/* 274 */         s2LineOffset += s2LineStride;
/* 275 */         dLineOffset += dLineStride;
/* 277 */         int sum = 0;
/* 278 */         for (int w = 0; w < dwidth; w++) {
/* 284 */           sum = (s1[s1PixelOffset] & 0xFF) + (s2[s2PixelOffset] & 0xFF);
/* 285 */           d[dPixelOffset] = (byte)((sum << 23 >> 31 | sum) & 0xFF);
/* 287 */           s1PixelOffset += s1PixelStride;
/* 288 */           s2PixelOffset += s2PixelStride;
/* 289 */           dPixelOffset += dPixelStride;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeRectUShort(RasterAccessor src1, RasterAccessor src2, RasterAccessor dst) {
/* 298 */     int s1LineStride = src1.getScanlineStride();
/* 299 */     int s1PixelStride = src1.getPixelStride();
/* 300 */     int[] s1BandOffsets = src1.getBandOffsets();
/* 301 */     short[][] s1Data = src1.getShortDataArrays();
/* 303 */     int s2LineStride = src2.getScanlineStride();
/* 304 */     int s2PixelStride = src2.getPixelStride();
/* 305 */     int[] s2BandOffsets = src2.getBandOffsets();
/* 306 */     short[][] s2Data = src2.getShortDataArrays();
/* 308 */     int dwidth = dst.getWidth();
/* 309 */     int dheight = dst.getHeight();
/* 310 */     int bands = dst.getNumBands();
/* 311 */     int dLineStride = dst.getScanlineStride();
/* 312 */     int dPixelStride = dst.getPixelStride();
/* 313 */     int[] dBandOffsets = dst.getBandOffsets();
/* 314 */     short[][] dData = dst.getShortDataArrays();
/*     */     int s2b;
/* 316 */     for (int b = 0, s1b = 0; b < bands; 
/* 317 */       b++, s1b += this.s1bd, s2b += this.s2bd) {
/* 318 */       short[] s1 = s1Data[s1b];
/* 319 */       short[] s2 = s2Data[s2b];
/* 320 */       short[] d = dData[b];
/* 322 */       int s1LineOffset = s1BandOffsets[s1b];
/* 323 */       int s2LineOffset = s2BandOffsets[s2b];
/* 324 */       int dLineOffset = dBandOffsets[b];
/* 326 */       for (int h = 0; h < dheight; h++) {
/* 327 */         int s1PixelOffset = s1LineOffset;
/* 328 */         int s2PixelOffset = s2LineOffset;
/* 329 */         int dPixelOffset = dLineOffset;
/* 331 */         s1LineOffset += s1LineStride;
/* 332 */         s2LineOffset += s2LineStride;
/* 333 */         dLineOffset += dLineStride;
/* 335 */         for (int w = 0; w < dwidth; w++) {
/* 336 */           d[dPixelOffset] = ImageUtil.clampUShortPositive((s1[s1PixelOffset] & 0xFFFF) + (s2[s2PixelOffset] & 0xFFFF));
/* 340 */           s1PixelOffset += s1PixelStride;
/* 341 */           s2PixelOffset += s2PixelStride;
/* 342 */           dPixelOffset += dPixelStride;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeRectShort(RasterAccessor src1, RasterAccessor src2, RasterAccessor dst) {
/* 351 */     int s1LineStride = src1.getScanlineStride();
/* 352 */     int s1PixelStride = src1.getPixelStride();
/* 353 */     int[] s1BandOffsets = src1.getBandOffsets();
/* 354 */     short[][] s1Data = src1.getShortDataArrays();
/* 356 */     int s2LineStride = src2.getScanlineStride();
/* 357 */     int s2PixelStride = src2.getPixelStride();
/* 358 */     int[] s2BandOffsets = src2.getBandOffsets();
/* 359 */     short[][] s2Data = src2.getShortDataArrays();
/* 361 */     int dwidth = dst.getWidth();
/* 362 */     int dheight = dst.getHeight();
/* 363 */     int bands = dst.getNumBands();
/* 364 */     int dLineStride = dst.getScanlineStride();
/* 365 */     int dPixelStride = dst.getPixelStride();
/* 366 */     int[] dBandOffsets = dst.getBandOffsets();
/* 367 */     short[][] dData = dst.getShortDataArrays();
/*     */     int s2b;
/* 369 */     for (int b = 0, s1b = 0; b < bands; 
/* 370 */       b++, s1b += this.s1bd, s2b += this.s2bd) {
/* 371 */       short[] s1 = s1Data[s1b];
/* 372 */       short[] s2 = s2Data[s2b];
/* 373 */       short[] d = dData[b];
/* 375 */       int s1LineOffset = s1BandOffsets[s1b];
/* 376 */       int s2LineOffset = s2BandOffsets[s2b];
/* 377 */       int dLineOffset = dBandOffsets[b];
/* 379 */       for (int h = 0; h < dheight; h++) {
/* 380 */         int s1PixelOffset = s1LineOffset;
/* 381 */         int s2PixelOffset = s2LineOffset;
/* 382 */         int dPixelOffset = dLineOffset;
/* 384 */         s1LineOffset += s1LineStride;
/* 385 */         s2LineOffset += s2LineStride;
/* 386 */         dLineOffset += dLineStride;
/* 388 */         for (int w = 0; w < dwidth; w++) {
/* 389 */           d[dPixelOffset] = ImageUtil.clampShort(s1[s1PixelOffset] + s2[s2PixelOffset]);
/* 392 */           s1PixelOffset += s1PixelStride;
/* 393 */           s2PixelOffset += s2PixelStride;
/* 394 */           dPixelOffset += dPixelStride;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeRectInt(RasterAccessor src1, RasterAccessor src2, RasterAccessor dst) {
/* 403 */     int b, s1b, s2b, s1LineStride = src1.getScanlineStride();
/* 404 */     int s1PixelStride = src1.getPixelStride();
/* 405 */     int[] s1BandOffsets = src1.getBandOffsets();
/* 406 */     int[][] s1Data = src1.getIntDataArrays();
/* 408 */     int s2LineStride = src2.getScanlineStride();
/* 409 */     int s2PixelStride = src2.getPixelStride();
/* 410 */     int[] s2BandOffsets = src2.getBandOffsets();
/* 411 */     int[][] s2Data = src2.getIntDataArrays();
/* 413 */     int dwidth = dst.getWidth();
/* 414 */     int dheight = dst.getHeight();
/* 415 */     int bands = dst.getNumBands();
/* 416 */     int dLineStride = dst.getScanlineStride();
/* 417 */     int dPixelStride = dst.getPixelStride();
/* 418 */     int[] dBandOffsets = dst.getBandOffsets();
/* 419 */     int[][] dData = dst.getIntDataArrays();
/* 426 */     switch (this.sampleModel.getTransferType()) {
/*     */       case 0:
/* 428 */         for (b = 0, s1b = 0, s2b = 0; b < bands; 
/* 429 */           b++, s1b += this.s1bd, s2b += this.s2bd) {
/* 430 */           int[] s1 = s1Data[s1b];
/* 431 */           int[] s2 = s2Data[s2b];
/* 432 */           int[] d = dData[b];
/* 434 */           int s1LineOffset = s1BandOffsets[s1b];
/* 435 */           int s2LineOffset = s2BandOffsets[s2b];
/* 436 */           int dLineOffset = dBandOffsets[b];
/* 438 */           for (int h = 0; h < dheight; h++) {
/* 439 */             int s1PixelOffset = s1LineOffset;
/* 440 */             int s2PixelOffset = s2LineOffset;
/* 441 */             int dPixelOffset = dLineOffset;
/* 443 */             s1LineOffset += s1LineStride;
/* 444 */             s2LineOffset += s2LineStride;
/* 445 */             dLineOffset += dLineStride;
/* 447 */             int sum = 0;
/* 448 */             for (int w = 0; w < dwidth; w++) {
/* 454 */               sum = (s1[s1PixelOffset] & 0xFF) + (s2[s2PixelOffset] & 0xFF);
/* 455 */               d[dPixelOffset] = (sum << 23 >> 31 | sum) & 0xFF;
/* 457 */               s1PixelOffset += s1PixelStride;
/* 458 */               s2PixelOffset += s2PixelStride;
/* 459 */               dPixelOffset += dPixelStride;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       case 1:
/* 466 */         for (b = 0, s1b = 0, s2b = 0; b < bands; 
/* 467 */           b++, s1b += this.s1bd, s2b += this.s2bd) {
/* 468 */           int[] s1 = s1Data[s1b];
/* 469 */           int[] s2 = s2Data[s2b];
/* 470 */           int[] d = dData[b];
/* 472 */           int s1LineOffset = s1BandOffsets[s1b];
/* 473 */           int s2LineOffset = s2BandOffsets[s2b];
/* 474 */           int dLineOffset = dBandOffsets[b];
/* 476 */           for (int h = 0; h < dheight; h++) {
/* 477 */             int s1PixelOffset = s1LineOffset;
/* 478 */             int s2PixelOffset = s2LineOffset;
/* 479 */             int dPixelOffset = dLineOffset;
/* 481 */             s1LineOffset += s1LineStride;
/* 482 */             s2LineOffset += s2LineStride;
/* 483 */             dLineOffset += dLineStride;
/* 485 */             for (int w = 0; w < dwidth; w++) {
/* 486 */               d[dPixelOffset] = ImageUtil.clampUShortPositive((s1[s1PixelOffset] & 0xFFFF) + (s2[s2PixelOffset] & 0xFFFF));
/* 490 */               s1PixelOffset += s1PixelStride;
/* 491 */               s2PixelOffset += s2PixelStride;
/* 492 */               dPixelOffset += dPixelStride;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       case 2:
/* 499 */         for (b = 0, s1b = 0, s2b = 0; b < bands; 
/* 500 */           b++, s1b += this.s1bd, s2b += this.s2bd) {
/* 501 */           int[] s1 = s1Data[s1b];
/* 502 */           int[] s2 = s2Data[s2b];
/* 503 */           int[] d = dData[b];
/* 505 */           int s1LineOffset = s1BandOffsets[s1b];
/* 506 */           int s2LineOffset = s2BandOffsets[s2b];
/* 507 */           int dLineOffset = dBandOffsets[b];
/* 509 */           for (int h = 0; h < dheight; h++) {
/* 510 */             int s1PixelOffset = s1LineOffset;
/* 511 */             int s2PixelOffset = s2LineOffset;
/* 512 */             int dPixelOffset = dLineOffset;
/* 514 */             s1LineOffset += s1LineStride;
/* 515 */             s2LineOffset += s2LineStride;
/* 516 */             dLineOffset += dLineStride;
/* 518 */             for (int w = 0; w < dwidth; w++) {
/* 519 */               d[dPixelOffset] = ImageUtil.clampShort(s1[s1PixelOffset] + s2[s2PixelOffset]);
/* 522 */               s1PixelOffset += s1PixelStride;
/* 523 */               s2PixelOffset += s2PixelStride;
/* 524 */               dPixelOffset += dPixelStride;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       case 3:
/* 531 */         for (b = 0, s1b = 0, s2b = 0; b < bands; 
/* 532 */           b++, s1b += this.s1bd, s2b += this.s2bd) {
/* 533 */           int[] s1 = s1Data[s1b];
/* 534 */           int[] s2 = s2Data[s2b];
/* 535 */           int[] d = dData[b];
/* 537 */           int s1LineOffset = s1BandOffsets[s1b];
/* 538 */           int s2LineOffset = s2BandOffsets[s2b];
/* 539 */           int dLineOffset = dBandOffsets[b];
/* 541 */           for (int h = 0; h < dheight; h++) {
/* 542 */             int s1PixelOffset = s1LineOffset;
/* 543 */             int s2PixelOffset = s2LineOffset;
/* 544 */             int dPixelOffset = dLineOffset;
/* 546 */             s1LineOffset += s1LineStride;
/* 547 */             s2LineOffset += s2LineStride;
/* 548 */             dLineOffset += dLineStride;
/* 550 */             for (int w = 0; w < dwidth; w++) {
/* 551 */               d[dPixelOffset] = ImageUtil.clampInt(s1[s1PixelOffset] + s2[s2PixelOffset]);
/* 554 */               s1PixelOffset += s1PixelStride;
/* 555 */               s2PixelOffset += s2PixelStride;
/* 556 */               dPixelOffset += dPixelStride;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */         break;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeRectFloat(RasterAccessor src1, RasterAccessor src2, RasterAccessor dst) {
/* 567 */     int s1LineStride = src1.getScanlineStride();
/* 568 */     int s1PixelStride = src1.getPixelStride();
/* 569 */     int[] s1BandOffsets = src1.getBandOffsets();
/* 570 */     float[][] s1Data = src1.getFloatDataArrays();
/* 572 */     int s2LineStride = src2.getScanlineStride();
/* 573 */     int s2PixelStride = src2.getPixelStride();
/* 574 */     int[] s2BandOffsets = src2.getBandOffsets();
/* 575 */     float[][] s2Data = src2.getFloatDataArrays();
/* 577 */     int dwidth = dst.getWidth();
/* 578 */     int dheight = dst.getHeight();
/* 579 */     int bands = dst.getNumBands();
/* 580 */     int dLineStride = dst.getScanlineStride();
/* 581 */     int dPixelStride = dst.getPixelStride();
/* 582 */     int[] dBandOffsets = dst.getBandOffsets();
/* 583 */     float[][] dData = dst.getFloatDataArrays();
/*     */     int s2b;
/* 585 */     for (int b = 0, s1b = 0; b < bands; 
/* 586 */       b++, s1b += this.s1bd, s2b += this.s2bd) {
/* 587 */       float[] s1 = s1Data[s1b];
/* 588 */       float[] s2 = s2Data[s2b];
/* 589 */       float[] d = dData[b];
/* 591 */       int s1LineOffset = s1BandOffsets[s1b];
/* 592 */       int s2LineOffset = s2BandOffsets[s2b];
/* 593 */       int dLineOffset = dBandOffsets[b];
/* 595 */       for (int h = 0; h < dheight; h++) {
/* 596 */         int s1PixelOffset = s1LineOffset;
/* 597 */         int s2PixelOffset = s2LineOffset;
/* 598 */         int dPixelOffset = dLineOffset;
/* 600 */         s1LineOffset += s1LineStride;
/* 601 */         s2LineOffset += s2LineStride;
/* 602 */         dLineOffset += dLineStride;
/* 604 */         for (int w = 0; w < dwidth; w++) {
/* 605 */           d[dPixelOffset] = s1[s1PixelOffset] + s2[s2PixelOffset];
/* 607 */           s1PixelOffset += s1PixelStride;
/* 608 */           s2PixelOffset += s2PixelStride;
/* 609 */           dPixelOffset += dPixelStride;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeRectDouble(RasterAccessor src1, RasterAccessor src2, RasterAccessor dst) {
/* 618 */     int s1LineStride = src1.getScanlineStride();
/* 619 */     int s1PixelStride = src1.getPixelStride();
/* 620 */     int[] s1BandOffsets = src1.getBandOffsets();
/* 621 */     double[][] s1Data = src1.getDoubleDataArrays();
/* 623 */     int s2LineStride = src2.getScanlineStride();
/* 624 */     int s2PixelStride = src2.getPixelStride();
/* 625 */     int[] s2BandOffsets = src2.getBandOffsets();
/* 626 */     double[][] s2Data = src2.getDoubleDataArrays();
/* 628 */     int dwidth = dst.getWidth();
/* 629 */     int dheight = dst.getHeight();
/* 630 */     int bands = dst.getNumBands();
/* 631 */     int dLineStride = dst.getScanlineStride();
/* 632 */     int dPixelStride = dst.getPixelStride();
/* 633 */     int[] dBandOffsets = dst.getBandOffsets();
/* 634 */     double[][] dData = dst.getDoubleDataArrays();
/*     */     int s2b;
/* 636 */     for (int b = 0, s1b = 0; b < bands; 
/* 637 */       b++, s1b += this.s1bd, s2b += this.s2bd) {
/* 638 */       double[] s1 = s1Data[s1b];
/* 639 */       double[] s2 = s2Data[s2b];
/* 640 */       double[] d = dData[b];
/* 642 */       int s1LineOffset = s1BandOffsets[s1b];
/* 643 */       int s2LineOffset = s2BandOffsets[s2b];
/* 644 */       int dLineOffset = dBandOffsets[b];
/* 646 */       for (int h = 0; h < dheight; h++) {
/* 647 */         int s1PixelOffset = s1LineOffset;
/* 648 */         int s2PixelOffset = s2LineOffset;
/* 649 */         int dPixelOffset = dLineOffset;
/* 651 */         s1LineOffset += s1LineStride;
/* 652 */         s2LineOffset += s2LineStride;
/* 653 */         dLineOffset += dLineStride;
/* 655 */         for (int w = 0; w < dwidth; w++) {
/* 656 */           d[dPixelOffset] = s1[s1PixelOffset] + s2[s2PixelOffset];
/* 658 */           s1PixelOffset += s1PixelStride;
/* 659 */           s2PixelOffset += s2PixelStride;
/* 660 */           dPixelOffset += dPixelStride;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\AddOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */