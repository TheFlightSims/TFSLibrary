/*     */ package com.sun.media.jai.opimage;
/*     */ 
/*     */ import com.sun.media.jai.util.ImageUtil;
/*     */ import com.sun.media.jai.util.JDKWorkarounds;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.util.Map;
/*     */ import javax.media.jai.ImageLayout;
/*     */ import javax.media.jai.PointOpImage;
/*     */ import javax.media.jai.RasterAccessor;
/*     */ import javax.media.jai.RasterFactory;
/*     */ import javax.media.jai.RasterFormatTag;
/*     */ 
/*     */ final class MagnitudePhaseOpImage extends PointOpImage {
/*     */   public static final int MAGNITUDE = 1;
/*     */   
/*     */   public static final int MAGNITUDE_SQUARED = 2;
/*     */   
/*     */   public static final int PHASE = 3;
/*     */   
/*     */   protected int operationType;
/*     */   
/*  67 */   private double phaseGain = 1.0D;
/*     */   
/*  70 */   private double phaseBias = 0.0D;
/*     */   
/*     */   public MagnitudePhaseOpImage(RenderedImage source, Map config, ImageLayout layout, int operationType) {
/*  88 */     super(source, layout, config, true);
/*  91 */     this.operationType = operationType;
/*  94 */     boolean needNewSampleModel = false;
/*  98 */     int dataType = this.sampleModel.getTransferType();
/*  99 */     if (layout != null && dataType != layout.getSampleModel(source).getTransferType()) {
/* 101 */       dataType = layout.getSampleModel(source).getTransferType();
/* 102 */       needNewSampleModel = true;
/*     */     } 
/* 106 */     int numBands = this.sampleModel.getNumBands();
/* 107 */     if (numBands > source.getSampleModel().getNumBands() / 2) {
/* 108 */       numBands = source.getSampleModel().getNumBands() / 2;
/* 109 */       needNewSampleModel = true;
/*     */     } 
/* 113 */     if (needNewSampleModel) {
/* 114 */       this.sampleModel = RasterFactory.createComponentSampleModel(this.sampleModel, dataType, this.sampleModel.getWidth(), this.sampleModel.getHeight(), numBands);
/* 120 */       if (this.colorModel != null && !JDKWorkarounds.areCompatibleDataModels(this.sampleModel, this.colorModel))
/* 123 */         this.colorModel = ImageUtil.getCompatibleColorModel(this.sampleModel, config); 
/*     */     } 
/* 128 */     if (operationType == 3)
/* 130 */       switch (dataType) {
/*     */         case 0:
/* 132 */           this.phaseGain = 40.58451048843331D;
/* 133 */           this.phaseBias = Math.PI;
/*     */           break;
/*     */         case 2:
/* 136 */           this.phaseGain = 5215.030020292134D;
/* 137 */           this.phaseBias = Math.PI;
/*     */           break;
/*     */         case 1:
/* 140 */           this.phaseGain = 10430.219195527361D;
/* 141 */           this.phaseBias = Math.PI;
/*     */           break;
/*     */         case 3:
/* 144 */           this.phaseGain = 3.4178263762906086E8D;
/* 145 */           this.phaseBias = Math.PI;
/*     */           break;
/*     */       }  
/*     */   }
/*     */   
/*     */   protected void computeRect(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/* 165 */     RasterFormatTag[] formatTags = getFormatTags();
/* 168 */     RasterAccessor srcAccessor = new RasterAccessor(sources[0], destRect, formatTags[0], getSourceImage(0).getColorModel());
/* 172 */     RasterAccessor dstAccessor = new RasterAccessor(dest, destRect, formatTags[1], getColorModel());
/* 176 */     switch (dstAccessor.getDataType()) {
/*     */       case 0:
/* 178 */         computeRectByte(srcAccessor, dstAccessor, destRect.height, destRect.width);
/*     */         break;
/*     */       case 2:
/* 182 */         computeRectShort(srcAccessor, dstAccessor, destRect.height, destRect.width);
/*     */         break;
/*     */       case 1:
/* 186 */         computeRectUShort(srcAccessor, dstAccessor, destRect.height, destRect.width);
/*     */         break;
/*     */       case 3:
/* 190 */         computeRectInt(srcAccessor, dstAccessor, destRect.height, destRect.width);
/*     */         break;
/*     */       case 4:
/* 194 */         computeRectFloat(srcAccessor, dstAccessor, destRect.height, destRect.width);
/*     */         break;
/*     */       case 5:
/* 198 */         computeRectDouble(srcAccessor, dstAccessor, destRect.height, destRect.width);
/*     */         break;
/*     */       default:
/* 203 */         throw new RuntimeException(JaiI18N.getString("MagnitudePhaseOpImage0"));
/*     */     } 
/* 206 */     if (dstAccessor.needsClamping())
/* 207 */       dstAccessor.clampDataArrays(); 
/* 211 */     dstAccessor.copyDataToRaster();
/*     */   }
/*     */   
/*     */   private void computeRectDouble(RasterAccessor srcAccessor, RasterAccessor dstAccessor, int numRows, int numCols) {
/* 219 */     int srcPixelStride = srcAccessor.getPixelStride();
/* 220 */     int srcScanlineStride = srcAccessor.getScanlineStride();
/* 221 */     int dstPixelStride = dstAccessor.getPixelStride();
/* 222 */     int dstScanlineStride = dstAccessor.getScanlineStride();
/* 225 */     int numDstBands = this.sampleModel.getNumBands();
/* 226 */     for (int dstBand = 0; dstBand < numDstBands; dstBand++) {
/* 228 */       int srcBandReal = 2 * dstBand;
/* 229 */       int srcBandImag = srcBandReal + 1;
/* 232 */       double[] srcReal = srcAccessor.getDoubleDataArray(srcBandReal);
/* 233 */       double[] srcImag = srcAccessor.getDoubleDataArray(srcBandImag);
/* 234 */       double[] dstData = dstAccessor.getDoubleDataArray(dstBand);
/* 237 */       int srcOffsetReal = srcAccessor.getBandOffset(srcBandReal);
/* 238 */       int srcOffsetImag = srcAccessor.getBandOffset(srcBandImag);
/* 239 */       int dstOffset = dstAccessor.getBandOffset(dstBand);
/* 242 */       int srcLineReal = srcOffsetReal;
/* 243 */       int srcLineImag = srcOffsetImag;
/* 244 */       int dstLine = dstOffset;
/* 246 */       for (int row = 0; row < numRows; row++) {
/* 248 */         int col, srcPixelReal = srcLineReal;
/* 249 */         int srcPixelImag = srcLineImag;
/* 250 */         int dstPixel = dstLine;
/* 253 */         switch (this.operationType) {
/*     */           case 1:
/* 255 */             for (col = 0; col < numCols; col++) {
/* 256 */               double real = srcReal[srcPixelReal];
/* 257 */               double imag = srcImag[srcPixelImag];
/* 259 */               dstData[dstPixel] = Math.sqrt(real * real + imag * imag);
/* 262 */               srcPixelReal += srcPixelStride;
/* 263 */               srcPixelImag += srcPixelStride;
/* 264 */               dstPixel += dstPixelStride;
/*     */             } 
/*     */             break;
/*     */           case 2:
/* 268 */             for (col = 0; col < numCols; col++) {
/* 269 */               double real = srcReal[srcPixelReal];
/* 270 */               double imag = srcImag[srcPixelImag];
/* 272 */               dstData[dstPixel] = real * real + imag * imag;
/* 274 */               srcPixelReal += srcPixelStride;
/* 275 */               srcPixelImag += srcPixelStride;
/* 276 */               dstPixel += dstPixelStride;
/*     */             } 
/*     */             break;
/*     */           case 3:
/* 280 */             for (col = 0; col < numCols; col++) {
/* 281 */               double real = srcReal[srcPixelReal];
/* 282 */               double imag = srcImag[srcPixelImag];
/* 284 */               dstData[dstPixel] = Math.atan2(imag, real);
/* 286 */               srcPixelReal += srcPixelStride;
/* 287 */               srcPixelImag += srcPixelStride;
/* 288 */               dstPixel += dstPixelStride;
/*     */             } 
/*     */             break;
/*     */         } 
/* 294 */         srcLineReal += srcScanlineStride;
/* 295 */         srcLineImag += srcScanlineStride;
/* 296 */         dstLine += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeRectFloat(RasterAccessor srcAccessor, RasterAccessor dstAccessor, int numRows, int numCols) {
/* 306 */     int srcPixelStride = srcAccessor.getPixelStride();
/* 307 */     int srcScanlineStride = srcAccessor.getScanlineStride();
/* 308 */     int dstPixelStride = dstAccessor.getPixelStride();
/* 309 */     int dstScanlineStride = dstAccessor.getScanlineStride();
/* 312 */     int numDstBands = this.sampleModel.getNumBands();
/* 313 */     for (int dstBand = 0; dstBand < numDstBands; dstBand++) {
/* 315 */       int srcBandReal = 2 * dstBand;
/* 316 */       int srcBandImag = srcBandReal + 1;
/* 319 */       float[] srcReal = srcAccessor.getFloatDataArray(srcBandReal);
/* 320 */       float[] srcImag = srcAccessor.getFloatDataArray(srcBandImag);
/* 321 */       float[] dstData = dstAccessor.getFloatDataArray(dstBand);
/* 324 */       int srcOffsetReal = srcAccessor.getBandOffset(srcBandReal);
/* 325 */       int srcOffsetImag = srcAccessor.getBandOffset(srcBandImag);
/* 326 */       int dstOffset = dstAccessor.getBandOffset(dstBand);
/* 329 */       int srcLineReal = srcOffsetReal;
/* 330 */       int srcLineImag = srcOffsetImag;
/* 331 */       int dstLine = dstOffset;
/* 333 */       for (int row = 0; row < numRows; row++) {
/* 335 */         int col, srcPixelReal = srcLineReal;
/* 336 */         int srcPixelImag = srcLineImag;
/* 337 */         int dstPixel = dstLine;
/* 340 */         switch (this.operationType) {
/*     */           case 1:
/* 342 */             for (col = 0; col < numCols; col++) {
/* 343 */               float real = srcReal[srcPixelReal];
/* 344 */               float imag = srcImag[srcPixelImag];
/* 346 */               dstData[dstPixel] = ImageUtil.clampFloat(Math.sqrt((real * real + imag * imag)));
/* 349 */               srcPixelReal += srcPixelStride;
/* 350 */               srcPixelImag += srcPixelStride;
/* 351 */               dstPixel += dstPixelStride;
/*     */             } 
/*     */             break;
/*     */           case 2:
/* 355 */             for (col = 0; col < numCols; col++) {
/* 356 */               float real = srcReal[srcPixelReal];
/* 357 */               float imag = srcImag[srcPixelImag];
/* 359 */               dstData[dstPixel] = real * real + imag * imag;
/* 361 */               srcPixelReal += srcPixelStride;
/* 362 */               srcPixelImag += srcPixelStride;
/* 363 */               dstPixel += dstPixelStride;
/*     */             } 
/*     */             break;
/*     */           case 3:
/* 367 */             for (col = 0; col < numCols; col++) {
/* 368 */               float real = srcReal[srcPixelReal];
/* 369 */               float imag = srcImag[srcPixelImag];
/* 371 */               dstData[dstPixel] = ImageUtil.clampFloat(Math.atan2(imag, real));
/* 374 */               srcPixelReal += srcPixelStride;
/* 375 */               srcPixelImag += srcPixelStride;
/* 376 */               dstPixel += dstPixelStride;
/*     */             } 
/*     */             break;
/*     */         } 
/* 382 */         srcLineReal += srcScanlineStride;
/* 383 */         srcLineImag += srcScanlineStride;
/* 384 */         dstLine += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeRectInt(RasterAccessor srcAccessor, RasterAccessor dstAccessor, int numRows, int numCols) {
/* 394 */     int srcPixelStride = srcAccessor.getPixelStride();
/* 395 */     int srcScanlineStride = srcAccessor.getScanlineStride();
/* 396 */     int dstPixelStride = dstAccessor.getPixelStride();
/* 397 */     int dstScanlineStride = dstAccessor.getScanlineStride();
/* 400 */     int numDstBands = this.sampleModel.getNumBands();
/* 401 */     for (int dstBand = 0; dstBand < numDstBands; dstBand++) {
/* 403 */       int srcBandReal = 2 * dstBand;
/* 404 */       int srcBandImag = srcBandReal + 1;
/* 407 */       int[] srcReal = srcAccessor.getIntDataArray(srcBandReal);
/* 408 */       int[] srcImag = srcAccessor.getIntDataArray(srcBandImag);
/* 409 */       int[] dstData = dstAccessor.getIntDataArray(dstBand);
/* 412 */       int srcOffsetReal = srcAccessor.getBandOffset(srcBandReal);
/* 413 */       int srcOffsetImag = srcAccessor.getBandOffset(srcBandImag);
/* 414 */       int dstOffset = dstAccessor.getBandOffset(dstBand);
/* 417 */       int srcLineReal = srcOffsetReal;
/* 418 */       int srcLineImag = srcOffsetImag;
/* 419 */       int dstLine = dstOffset;
/* 421 */       for (int row = 0; row < numRows; row++) {
/* 423 */         int col, srcPixelReal = srcLineReal;
/* 424 */         int srcPixelImag = srcLineImag;
/* 425 */         int dstPixel = dstLine;
/* 428 */         switch (this.operationType) {
/*     */           case 1:
/* 430 */             for (col = 0; col < numCols; col++) {
/* 431 */               int real = srcReal[srcPixelReal];
/* 432 */               int imag = srcImag[srcPixelImag];
/* 434 */               dstData[dstPixel] = ImageUtil.clampRoundInt(Math.sqrt((real * real + imag * imag)));
/* 437 */               srcPixelReal += srcPixelStride;
/* 438 */               srcPixelImag += srcPixelStride;
/* 439 */               dstPixel += dstPixelStride;
/*     */             } 
/*     */             break;
/*     */           case 2:
/* 443 */             for (col = 0; col < numCols; col++) {
/* 444 */               int real = srcReal[srcPixelReal];
/* 445 */               int imag = srcImag[srcPixelImag];
/* 447 */               dstData[dstPixel] = real * real + imag * imag;
/* 449 */               srcPixelReal += srcPixelStride;
/* 450 */               srcPixelImag += srcPixelStride;
/* 451 */               dstPixel += dstPixelStride;
/*     */             } 
/*     */             break;
/*     */           case 3:
/* 455 */             for (col = 0; col < numCols; col++) {
/* 456 */               int real = srcReal[srcPixelReal];
/* 457 */               int imag = srcImag[srcPixelImag];
/* 459 */               dstData[dstPixel] = ImageUtil.clampRoundInt((Math.atan2(imag, real) + this.phaseBias) * this.phaseGain);
/* 463 */               srcPixelReal += srcPixelStride;
/* 464 */               srcPixelImag += srcPixelStride;
/* 465 */               dstPixel += dstPixelStride;
/*     */             } 
/*     */             break;
/*     */         } 
/* 471 */         srcLineReal += srcScanlineStride;
/* 472 */         srcLineImag += srcScanlineStride;
/* 473 */         dstLine += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeRectUShort(RasterAccessor srcAccessor, RasterAccessor dstAccessor, int numRows, int numCols) {
/* 483 */     int srcPixelStride = srcAccessor.getPixelStride();
/* 484 */     int srcScanlineStride = srcAccessor.getScanlineStride();
/* 485 */     int dstPixelStride = dstAccessor.getPixelStride();
/* 486 */     int dstScanlineStride = dstAccessor.getScanlineStride();
/* 489 */     int numDstBands = this.sampleModel.getNumBands();
/* 490 */     for (int dstBand = 0; dstBand < numDstBands; dstBand++) {
/* 492 */       int srcBandReal = 2 * dstBand;
/* 493 */       int srcBandImag = srcBandReal + 1;
/* 496 */       short[] srcReal = srcAccessor.getShortDataArray(srcBandReal);
/* 497 */       short[] srcImag = srcAccessor.getShortDataArray(srcBandImag);
/* 498 */       short[] dstData = dstAccessor.getShortDataArray(dstBand);
/* 501 */       int srcOffsetReal = srcAccessor.getBandOffset(srcBandReal);
/* 502 */       int srcOffsetImag = srcAccessor.getBandOffset(srcBandImag);
/* 503 */       int dstOffset = dstAccessor.getBandOffset(dstBand);
/* 506 */       int srcLineReal = srcOffsetReal;
/* 507 */       int srcLineImag = srcOffsetImag;
/* 508 */       int dstLine = dstOffset;
/* 510 */       for (int row = 0; row < numRows; row++) {
/* 512 */         int col, srcPixelReal = srcLineReal;
/* 513 */         int srcPixelImag = srcLineImag;
/* 514 */         int dstPixel = dstLine;
/* 517 */         switch (this.operationType) {
/*     */           case 1:
/* 519 */             for (col = 0; col < numCols; col++) {
/* 520 */               int real = srcReal[srcPixelReal] & 0xFFFF;
/* 521 */               int imag = srcImag[srcPixelImag] & 0xFFFF;
/* 523 */               dstData[dstPixel] = ImageUtil.clampRoundUShort(Math.sqrt((real * real + imag * imag)));
/* 526 */               srcPixelReal += srcPixelStride;
/* 527 */               srcPixelImag += srcPixelStride;
/* 528 */               dstPixel += dstPixelStride;
/*     */             } 
/*     */             break;
/*     */           case 2:
/* 532 */             for (col = 0; col < numCols; col++) {
/* 533 */               int real = srcReal[srcPixelReal] & 0xFFFF;
/* 534 */               int imag = srcImag[srcPixelImag] & 0xFFFF;
/* 536 */               dstData[dstPixel] = ImageUtil.clampUShort(real * real + imag * imag);
/* 539 */               srcPixelReal += srcPixelStride;
/* 540 */               srcPixelImag += srcPixelStride;
/* 541 */               dstPixel += dstPixelStride;
/*     */             } 
/*     */             break;
/*     */           case 3:
/* 545 */             for (col = 0; col < numCols; col++) {
/* 546 */               int real = srcReal[srcPixelReal] & 0xFFFF;
/* 547 */               int imag = srcImag[srcPixelImag] & 0xFFFF;
/* 549 */               dstData[dstPixel] = ImageUtil.clampRoundUShort((Math.atan2(imag, real) + this.phaseBias) * this.phaseGain);
/* 553 */               srcPixelReal += srcPixelStride;
/* 554 */               srcPixelImag += srcPixelStride;
/* 555 */               dstPixel += dstPixelStride;
/*     */             } 
/*     */             break;
/*     */         } 
/* 561 */         srcLineReal += srcScanlineStride;
/* 562 */         srcLineImag += srcScanlineStride;
/* 563 */         dstLine += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeRectShort(RasterAccessor srcAccessor, RasterAccessor dstAccessor, int numRows, int numCols) {
/* 573 */     int srcPixelStride = srcAccessor.getPixelStride();
/* 574 */     int srcScanlineStride = srcAccessor.getScanlineStride();
/* 575 */     int dstPixelStride = dstAccessor.getPixelStride();
/* 576 */     int dstScanlineStride = dstAccessor.getScanlineStride();
/* 579 */     int numDstBands = this.sampleModel.getNumBands();
/* 580 */     for (int dstBand = 0; dstBand < numDstBands; dstBand++) {
/* 582 */       int srcBandReal = 2 * dstBand;
/* 583 */       int srcBandImag = srcBandReal + 1;
/* 586 */       short[] srcReal = srcAccessor.getShortDataArray(srcBandReal);
/* 587 */       short[] srcImag = srcAccessor.getShortDataArray(srcBandImag);
/* 588 */       short[] dstData = dstAccessor.getShortDataArray(dstBand);
/* 591 */       int srcOffsetReal = srcAccessor.getBandOffset(srcBandReal);
/* 592 */       int srcOffsetImag = srcAccessor.getBandOffset(srcBandImag);
/* 593 */       int dstOffset = dstAccessor.getBandOffset(dstBand);
/* 596 */       int srcLineReal = srcOffsetReal;
/* 597 */       int srcLineImag = srcOffsetImag;
/* 598 */       int dstLine = dstOffset;
/* 600 */       for (int row = 0; row < numRows; row++) {
/* 602 */         int col, srcPixelReal = srcLineReal;
/* 603 */         int srcPixelImag = srcLineImag;
/* 604 */         int dstPixel = dstLine;
/* 607 */         switch (this.operationType) {
/*     */           case 1:
/* 609 */             for (col = 0; col < numCols; col++) {
/* 610 */               short real = srcReal[srcPixelReal];
/* 611 */               short imag = srcImag[srcPixelImag];
/* 613 */               dstData[dstPixel] = ImageUtil.clampRoundShort(Math.sqrt((real * real + imag * imag)));
/* 616 */               srcPixelReal += srcPixelStride;
/* 617 */               srcPixelImag += srcPixelStride;
/* 618 */               dstPixel += dstPixelStride;
/*     */             } 
/*     */             break;
/*     */           case 2:
/* 622 */             for (col = 0; col < numCols; col++) {
/* 623 */               short real = srcReal[srcPixelReal];
/* 624 */               short imag = srcImag[srcPixelImag];
/* 626 */               dstData[dstPixel] = ImageUtil.clampShort(real * real + imag * imag);
/* 628 */               srcPixelReal += srcPixelStride;
/* 629 */               srcPixelImag += srcPixelStride;
/* 630 */               dstPixel += dstPixelStride;
/*     */             } 
/*     */             break;
/*     */           case 3:
/* 634 */             for (col = 0; col < numCols; col++) {
/* 635 */               short real = srcReal[srcPixelReal];
/* 636 */               short imag = srcImag[srcPixelImag];
/* 638 */               dstData[dstPixel] = ImageUtil.clampRoundShort((Math.atan2(imag, real) + this.phaseBias) * this.phaseGain);
/* 642 */               srcPixelReal += srcPixelStride;
/* 643 */               srcPixelImag += srcPixelStride;
/* 644 */               dstPixel += dstPixelStride;
/*     */             } 
/*     */             break;
/*     */         } 
/* 650 */         srcLineReal += srcScanlineStride;
/* 651 */         srcLineImag += srcScanlineStride;
/* 652 */         dstLine += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeRectByte(RasterAccessor srcAccessor, RasterAccessor dstAccessor, int numRows, int numCols) {
/* 662 */     int srcPixelStride = srcAccessor.getPixelStride();
/* 663 */     int srcScanlineStride = srcAccessor.getScanlineStride();
/* 664 */     int dstPixelStride = dstAccessor.getPixelStride();
/* 665 */     int dstScanlineStride = dstAccessor.getScanlineStride();
/* 668 */     int numDstBands = this.sampleModel.getNumBands();
/* 669 */     for (int dstBand = 0; dstBand < numDstBands; dstBand++) {
/* 671 */       int srcBandReal = 2 * dstBand;
/* 672 */       int srcBandImag = srcBandReal + 1;
/* 675 */       byte[] srcReal = srcAccessor.getByteDataArray(srcBandReal);
/* 676 */       byte[] srcImag = srcAccessor.getByteDataArray(srcBandImag);
/* 677 */       byte[] dstData = dstAccessor.getByteDataArray(dstBand);
/* 680 */       int srcOffsetReal = srcAccessor.getBandOffset(srcBandReal);
/* 681 */       int srcOffsetImag = srcAccessor.getBandOffset(srcBandImag);
/* 682 */       int dstOffset = dstAccessor.getBandOffset(dstBand);
/* 685 */       int srcLineReal = srcOffsetReal;
/* 686 */       int srcLineImag = srcOffsetImag;
/* 687 */       int dstLine = dstOffset;
/* 689 */       for (int row = 0; row < numRows; row++) {
/* 691 */         int col, srcPixelReal = srcLineReal;
/* 692 */         int srcPixelImag = srcLineImag;
/* 693 */         int dstPixel = dstLine;
/* 696 */         switch (this.operationType) {
/*     */           case 1:
/* 698 */             for (col = 0; col < numCols; col++) {
/* 699 */               int real = srcReal[srcPixelReal] & 0xFF;
/* 700 */               int imag = srcImag[srcPixelImag] & 0xFF;
/* 702 */               dstData[dstPixel] = ImageUtil.clampRoundByte(Math.sqrt((real * real + imag * imag)));
/* 705 */               srcPixelReal += srcPixelStride;
/* 706 */               srcPixelImag += srcPixelStride;
/* 707 */               dstPixel += dstPixelStride;
/*     */             } 
/*     */             break;
/*     */           case 2:
/* 711 */             for (col = 0; col < numCols; col++) {
/* 712 */               int real = srcReal[srcPixelReal] & 0xFF;
/* 713 */               int imag = srcImag[srcPixelImag] & 0xFF;
/* 715 */               dstData[dstPixel] = ImageUtil.clampByte(real * real + imag * imag);
/* 718 */               srcPixelReal += srcPixelStride;
/* 719 */               srcPixelImag += srcPixelStride;
/* 720 */               dstPixel += dstPixelStride;
/*     */             } 
/*     */             break;
/*     */           case 3:
/* 724 */             for (col = 0; col < numCols; col++) {
/* 725 */               int real = srcReal[srcPixelReal] & 0xFF;
/* 726 */               int imag = srcImag[srcPixelImag] & 0xFF;
/* 728 */               dstData[dstPixel] = ImageUtil.clampRoundByte((Math.atan2(imag, real) + this.phaseBias) * this.phaseGain);
/* 732 */               srcPixelReal += srcPixelStride;
/* 733 */               srcPixelImag += srcPixelStride;
/* 734 */               dstPixel += dstPixelStride;
/*     */             } 
/*     */             break;
/*     */         } 
/* 740 */         srcLineReal += srcScanlineStride;
/* 741 */         srcLineImag += srcScanlineStride;
/* 742 */         dstLine += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\MagnitudePhaseOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */