/*     */ package com.sun.media.jai.opimage;
/*     */ 
/*     */ import com.sun.media.jai.util.ImageUtil;
/*     */ import com.sun.media.jai.util.JDKWorkarounds;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.image.ColorModel;
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
/*     */ final class ComplexArithmeticOpImage extends PointOpImage {
/*     */   protected boolean isDivision = false;
/*     */   
/*     */   private int[] s1r;
/*     */   
/*     */   private int[] s1i;
/*     */   
/*     */   private int[] s2r;
/*     */   
/*     */   private int[] s2i;
/*     */   
/*     */   private static ImageLayout layoutHelper(ImageLayout layout, RenderedImage source) {
/*     */     ImageLayout il;
/*  66 */     if (layout == null) {
/*  67 */       il = new ImageLayout();
/*     */     } else {
/*  69 */       il = (ImageLayout)layout.clone();
/*     */     } 
/*  72 */     if (il.isValid(256)) {
/*  73 */       SampleModel sm = il.getSampleModel(null);
/*  74 */       int nBands = sm.getNumBands();
/*  75 */       if (nBands % 2 != 0) {
/*  76 */         nBands++;
/*  77 */         sm = RasterFactory.createComponentSampleModel(sm, sm.getTransferType(), sm.getWidth(), sm.getHeight(), nBands);
/*  82 */         il.setSampleModel(sm);
/*  85 */         ColorModel cm = layout.getColorModel(null);
/*  86 */         if (cm != null && !JDKWorkarounds.areCompatibleDataModels(sm, cm))
/*  89 */           il.unsetValid(512); 
/*     */       } 
/*     */     } 
/*  94 */     return il;
/*     */   }
/*     */   
/*     */   public ComplexArithmeticOpImage(RenderedImage source1, RenderedImage source2, Map config, ImageLayout layout, boolean isDivision) {
/* 121 */     super(source1, source2, layoutHelper(layout, source1), config, true);
/* 124 */     this.isDivision = isDivision;
/* 127 */     int numBands1 = source1.getSampleModel().getNumBands();
/* 128 */     int numBands2 = source2.getSampleModel().getNumBands();
/* 131 */     int numBandsDst = Math.min(numBands1, numBands2);
/* 133 */     int numBandsFromHint = 0;
/* 134 */     if (layout != null)
/* 135 */       numBandsFromHint = layout.getSampleModel(null).getNumBands(); 
/* 137 */     if (layout != null && layout.isValid(256) && ((numBands1 == 2 && numBands2 > 2) || (numBands2 == 2 && numBands1 > 2) || (numBands1 >= numBandsFromHint && numBands2 >= numBandsFromHint && numBandsFromHint > 0)))
/* 141 */       if (numBandsFromHint % 2 == 0) {
/* 142 */         numBandsDst = numBandsFromHint;
/* 145 */         numBandsDst = Math.min(Math.max(numBands1, numBands2), numBandsDst);
/*     */       }  
/* 150 */     if (numBandsDst != this.sampleModel.getNumBands()) {
/* 151 */       this.sampleModel = RasterFactory.createComponentSampleModel(this.sampleModel, this.sampleModel.getTransferType(), this.sampleModel.getWidth(), this.sampleModel.getHeight(), numBandsDst);
/* 159 */       if (this.colorModel != null && !JDKWorkarounds.areCompatibleDataModels(this.sampleModel, this.colorModel))
/* 162 */         this.colorModel = ImageUtil.getCompatibleColorModel(this.sampleModel, config); 
/*     */     } 
/* 168 */     int numElements = this.sampleModel.getNumBands() / 2;
/* 169 */     this.s1r = new int[numElements];
/* 170 */     this.s1i = new int[numElements];
/* 171 */     this.s2r = new int[numElements];
/* 172 */     this.s2i = new int[numElements];
/* 173 */     int s1Inc = (numBands1 > 2) ? 2 : 0;
/* 174 */     int s2Inc = (numBands2 > 2) ? 2 : 0;
/* 175 */     int i1 = 0;
/* 176 */     int i2 = 0;
/* 177 */     for (int b = 0; b < numElements; b++) {
/* 178 */       this.s1r[b] = i1;
/* 179 */       this.s1i[b] = i1 + 1;
/* 180 */       this.s2r[b] = i2;
/* 181 */       this.s2i[b] = i2 + 1;
/* 182 */       i1 += s1Inc;
/* 183 */       i2 += s2Inc;
/*     */     } 
/* 187 */     permitInPlaceOperation();
/*     */   }
/*     */   
/*     */   protected void computeRect(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/* 202 */     RasterFormatTag[] formatTags = getFormatTags();
/* 204 */     RasterAccessor src1Accessor = new RasterAccessor(sources[0], destRect, formatTags[0], getSourceImage(0).getColorModel());
/* 208 */     RasterAccessor src2Accessor = new RasterAccessor(sources[1], destRect, formatTags[1], getSourceImage(1).getColorModel());
/* 212 */     RasterAccessor dstAccessor = new RasterAccessor(dest, destRect, formatTags[2], getColorModel());
/* 217 */     switch (dstAccessor.getDataType()) {
/*     */       case 0:
/* 219 */         computeRectByte(src1Accessor, src2Accessor, dstAccessor);
/*     */         break;
/*     */       case 2:
/* 222 */         computeRectShort(src1Accessor, src2Accessor, dstAccessor);
/*     */         break;
/*     */       case 1:
/* 225 */         computeRectUShort(src1Accessor, src2Accessor, dstAccessor);
/*     */         break;
/*     */       case 3:
/* 228 */         computeRectInt(src1Accessor, src2Accessor, dstAccessor);
/*     */         break;
/*     */       case 4:
/* 231 */         computeRectFloat(src1Accessor, src2Accessor, dstAccessor);
/*     */         break;
/*     */       case 5:
/* 234 */         computeRectDouble(src1Accessor, src2Accessor, dstAccessor);
/*     */         break;
/*     */       default:
/* 238 */         throw new RuntimeException(JaiI18N.getString("ComplexArithmeticOpImage0"));
/*     */     } 
/* 241 */     if (dstAccessor.needsClamping())
/* 242 */       dstAccessor.clampDataArrays(); 
/* 245 */     dstAccessor.copyDataToRaster();
/*     */   }
/*     */   
/*     */   private void computeRectDouble(RasterAccessor src1Accessor, RasterAccessor src2Accessor, RasterAccessor dstAccessor) {
/* 252 */     int numRows = dstAccessor.getHeight();
/* 253 */     int numCols = dstAccessor.getWidth();
/* 256 */     int src1PixelStride = src1Accessor.getPixelStride();
/* 257 */     int src1ScanlineStride = src1Accessor.getScanlineStride();
/* 258 */     int src2PixelStride = src2Accessor.getPixelStride();
/* 259 */     int src2ScanlineStride = src2Accessor.getScanlineStride();
/* 260 */     int dstPixelStride = dstAccessor.getPixelStride();
/* 261 */     int dstScanlineStride = dstAccessor.getScanlineStride();
/* 264 */     int numElements = this.sampleModel.getNumBands() / 2;
/* 265 */     for (int element = 0; element < numElements; element++) {
/* 267 */       int realBand = 2 * element;
/* 268 */       int imagBand = realBand + 1;
/* 271 */       double[] src1Real = src1Accessor.getDoubleDataArray(this.s1r[element]);
/* 272 */       double[] src1Imag = src1Accessor.getDoubleDataArray(this.s1i[element]);
/* 273 */       double[] src2Real = src2Accessor.getDoubleDataArray(this.s2r[element]);
/* 274 */       double[] src2Imag = src2Accessor.getDoubleDataArray(this.s2i[element]);
/* 275 */       double[] dstReal = dstAccessor.getDoubleDataArray(realBand);
/* 276 */       double[] dstImag = dstAccessor.getDoubleDataArray(imagBand);
/* 279 */       int src1OffsetReal = src1Accessor.getBandOffset(this.s1r[element]);
/* 280 */       int src1OffsetImag = src1Accessor.getBandOffset(this.s1i[element]);
/* 281 */       int src2OffsetReal = src2Accessor.getBandOffset(this.s2r[element]);
/* 282 */       int src2OffsetImag = src2Accessor.getBandOffset(this.s2i[element]);
/* 283 */       int dstOffsetReal = dstAccessor.getBandOffset(realBand);
/* 284 */       int dstOffsetImag = dstAccessor.getBandOffset(imagBand);
/* 287 */       int src1LineReal = src1OffsetReal;
/* 288 */       int src1LineImag = src1OffsetImag;
/* 289 */       int src2LineReal = src2OffsetReal;
/* 290 */       int src2LineImag = src2OffsetImag;
/* 291 */       int dstLineReal = dstOffsetReal;
/* 292 */       int dstLineImag = dstOffsetImag;
/* 294 */       for (int row = 0; row < numRows; row++) {
/* 296 */         int src1PixelReal = src1LineReal;
/* 297 */         int src1PixelImag = src1LineImag;
/* 298 */         int src2PixelReal = src2LineReal;
/* 299 */         int src2PixelImag = src2LineImag;
/* 300 */         int dstPixelReal = dstLineReal;
/* 301 */         int dstPixelImag = dstLineImag;
/* 304 */         if (this.isDivision) {
/* 305 */           for (int col = 0; col < numCols; col++) {
/* 306 */             double a = src1Real[src1PixelReal];
/* 307 */             double b = src1Imag[src1PixelImag];
/* 308 */             double c = src2Real[src2PixelReal];
/* 309 */             double d = src2Imag[src2PixelImag];
/* 311 */             double denom = c * c + d * d;
/* 312 */             dstReal[dstPixelReal] = (a * c + b * d) / denom;
/* 313 */             dstImag[dstPixelImag] = (b * c - a * d) / denom;
/* 315 */             src1PixelReal += src1PixelStride;
/* 316 */             src1PixelImag += src1PixelStride;
/* 317 */             src2PixelReal += src2PixelStride;
/* 318 */             src2PixelImag += src2PixelStride;
/* 319 */             dstPixelReal += dstPixelStride;
/* 320 */             dstPixelImag += dstPixelStride;
/*     */           } 
/*     */         } else {
/* 323 */           for (int col = 0; col < numCols; col++) {
/* 324 */             double a = src1Real[src1PixelReal];
/* 325 */             double b = src1Imag[src1PixelImag];
/* 326 */             double c = src2Real[src2PixelReal];
/* 327 */             double d = src2Imag[src2PixelImag];
/* 329 */             dstReal[dstPixelReal] = a * c - b * d;
/* 330 */             dstImag[dstPixelImag] = a * d + b * c;
/* 332 */             src1PixelReal += src1PixelStride;
/* 333 */             src1PixelImag += src1PixelStride;
/* 334 */             src2PixelReal += src2PixelStride;
/* 335 */             src2PixelImag += src2PixelStride;
/* 336 */             dstPixelReal += dstPixelStride;
/* 337 */             dstPixelImag += dstPixelStride;
/*     */           } 
/*     */         } 
/* 342 */         src1LineReal += src1ScanlineStride;
/* 343 */         src1LineImag += src1ScanlineStride;
/* 344 */         src2LineReal += src2ScanlineStride;
/* 345 */         src2LineImag += src2ScanlineStride;
/* 346 */         dstLineReal += dstScanlineStride;
/* 347 */         dstLineImag += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeRectFloat(RasterAccessor src1Accessor, RasterAccessor src2Accessor, RasterAccessor dstAccessor) {
/* 356 */     int numRows = dstAccessor.getHeight();
/* 357 */     int numCols = dstAccessor.getWidth();
/* 360 */     int src1PixelStride = src1Accessor.getPixelStride();
/* 361 */     int src1ScanlineStride = src1Accessor.getScanlineStride();
/* 362 */     int src2PixelStride = src2Accessor.getPixelStride();
/* 363 */     int src2ScanlineStride = src2Accessor.getScanlineStride();
/* 364 */     int dstPixelStride = dstAccessor.getPixelStride();
/* 365 */     int dstScanlineStride = dstAccessor.getScanlineStride();
/* 368 */     int numElements = this.sampleModel.getNumBands() / 2;
/* 369 */     for (int element = 0; element < numElements; element++) {
/* 371 */       int realBand = 2 * element;
/* 372 */       int imagBand = realBand + 1;
/* 375 */       float[] src1Real = src1Accessor.getFloatDataArray(this.s1r[element]);
/* 376 */       float[] src1Imag = src1Accessor.getFloatDataArray(this.s1i[element]);
/* 377 */       float[] src2Real = src2Accessor.getFloatDataArray(this.s2r[element]);
/* 378 */       float[] src2Imag = src2Accessor.getFloatDataArray(this.s2i[element]);
/* 379 */       float[] dstReal = dstAccessor.getFloatDataArray(realBand);
/* 380 */       float[] dstImag = dstAccessor.getFloatDataArray(imagBand);
/* 383 */       int src1OffsetReal = src1Accessor.getBandOffset(this.s1r[element]);
/* 384 */       int src1OffsetImag = src1Accessor.getBandOffset(this.s1i[element]);
/* 385 */       int src2OffsetReal = src2Accessor.getBandOffset(this.s2r[element]);
/* 386 */       int src2OffsetImag = src2Accessor.getBandOffset(this.s2i[element]);
/* 387 */       int dstOffsetReal = dstAccessor.getBandOffset(realBand);
/* 388 */       int dstOffsetImag = dstAccessor.getBandOffset(imagBand);
/* 391 */       int src1LineReal = src1OffsetReal;
/* 392 */       int src1LineImag = src1OffsetImag;
/* 393 */       int src2LineReal = src2OffsetReal;
/* 394 */       int src2LineImag = src2OffsetImag;
/* 395 */       int dstLineReal = dstOffsetReal;
/* 396 */       int dstLineImag = dstOffsetImag;
/* 398 */       for (int row = 0; row < numRows; row++) {
/* 400 */         int src1PixelReal = src1LineReal;
/* 401 */         int src1PixelImag = src1LineImag;
/* 402 */         int src2PixelReal = src2LineReal;
/* 403 */         int src2PixelImag = src2LineImag;
/* 404 */         int dstPixelReal = dstLineReal;
/* 405 */         int dstPixelImag = dstLineImag;
/* 408 */         if (this.isDivision) {
/* 409 */           for (int col = 0; col < numCols; col++) {
/* 410 */             float a = src1Real[src1PixelReal];
/* 411 */             float b = src1Imag[src1PixelImag];
/* 412 */             float c = src2Real[src2PixelReal];
/* 413 */             float d = src2Imag[src2PixelImag];
/* 415 */             float denom = c * c + d * d;
/* 416 */             dstReal[dstPixelReal] = (a * c + b * d) / denom;
/* 417 */             dstImag[dstPixelImag] = (b * c - a * d) / denom;
/* 419 */             src1PixelReal += src1PixelStride;
/* 420 */             src1PixelImag += src1PixelStride;
/* 421 */             src2PixelReal += src2PixelStride;
/* 422 */             src2PixelImag += src2PixelStride;
/* 423 */             dstPixelReal += dstPixelStride;
/* 424 */             dstPixelImag += dstPixelStride;
/*     */           } 
/*     */         } else {
/* 427 */           for (int col = 0; col < numCols; col++) {
/* 428 */             float a = src1Real[src1PixelReal];
/* 429 */             float b = src1Imag[src1PixelImag];
/* 430 */             float c = src2Real[src2PixelReal];
/* 431 */             float d = src2Imag[src2PixelImag];
/* 433 */             dstReal[dstPixelReal] = a * c - b * d;
/* 434 */             dstImag[dstPixelImag] = a * d + b * c;
/* 436 */             src1PixelReal += src1PixelStride;
/* 437 */             src1PixelImag += src1PixelStride;
/* 438 */             src2PixelReal += src2PixelStride;
/* 439 */             src2PixelImag += src2PixelStride;
/* 440 */             dstPixelReal += dstPixelStride;
/* 441 */             dstPixelImag += dstPixelStride;
/*     */           } 
/*     */         } 
/* 446 */         src1LineReal += src1ScanlineStride;
/* 447 */         src1LineImag += src1ScanlineStride;
/* 448 */         src2LineReal += src2ScanlineStride;
/* 449 */         src2LineImag += src2ScanlineStride;
/* 450 */         dstLineReal += dstScanlineStride;
/* 451 */         dstLineImag += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeRectInt(RasterAccessor src1Accessor, RasterAccessor src2Accessor, RasterAccessor dstAccessor) {
/* 460 */     int numRows = dstAccessor.getHeight();
/* 461 */     int numCols = dstAccessor.getWidth();
/* 464 */     int src1PixelStride = src1Accessor.getPixelStride();
/* 465 */     int src1ScanlineStride = src1Accessor.getScanlineStride();
/* 466 */     int src2PixelStride = src2Accessor.getPixelStride();
/* 467 */     int src2ScanlineStride = src2Accessor.getScanlineStride();
/* 468 */     int dstPixelStride = dstAccessor.getPixelStride();
/* 469 */     int dstScanlineStride = dstAccessor.getScanlineStride();
/* 472 */     int numElements = this.sampleModel.getNumBands() / 2;
/* 473 */     for (int element = 0; element < numElements; element++) {
/* 475 */       int realBand = 2 * element;
/* 476 */       int imagBand = realBand + 1;
/* 479 */       int[] src1Real = src1Accessor.getIntDataArray(this.s1r[element]);
/* 480 */       int[] src1Imag = src1Accessor.getIntDataArray(this.s1i[element]);
/* 481 */       int[] src2Real = src2Accessor.getIntDataArray(this.s2r[element]);
/* 482 */       int[] src2Imag = src2Accessor.getIntDataArray(this.s2i[element]);
/* 483 */       int[] dstReal = dstAccessor.getIntDataArray(realBand);
/* 484 */       int[] dstImag = dstAccessor.getIntDataArray(imagBand);
/* 487 */       int src1OffsetReal = src1Accessor.getBandOffset(this.s1r[element]);
/* 488 */       int src1OffsetImag = src1Accessor.getBandOffset(this.s1i[element]);
/* 489 */       int src2OffsetReal = src2Accessor.getBandOffset(this.s2r[element]);
/* 490 */       int src2OffsetImag = src2Accessor.getBandOffset(this.s2i[element]);
/* 491 */       int dstOffsetReal = dstAccessor.getBandOffset(realBand);
/* 492 */       int dstOffsetImag = dstAccessor.getBandOffset(imagBand);
/* 495 */       int src1LineReal = src1OffsetReal;
/* 496 */       int src1LineImag = src1OffsetImag;
/* 497 */       int src2LineReal = src2OffsetReal;
/* 498 */       int src2LineImag = src2OffsetImag;
/* 499 */       int dstLineReal = dstOffsetReal;
/* 500 */       int dstLineImag = dstOffsetImag;
/* 502 */       for (int row = 0; row < numRows; row++) {
/* 504 */         int src1PixelReal = src1LineReal;
/* 505 */         int src1PixelImag = src1LineImag;
/* 506 */         int src2PixelReal = src2LineReal;
/* 507 */         int src2PixelImag = src2LineImag;
/* 508 */         int dstPixelReal = dstLineReal;
/* 509 */         int dstPixelImag = dstLineImag;
/* 512 */         if (this.isDivision) {
/* 513 */           for (int col = 0; col < numCols; col++) {
/* 514 */             int a = src1Real[src1PixelReal];
/* 515 */             int b = src1Imag[src1PixelImag];
/* 516 */             int c = src2Real[src2PixelReal];
/* 517 */             int d = src2Imag[src2PixelImag];
/* 519 */             float denom = (c * c + d * d);
/* 520 */             dstReal[dstPixelReal] = ImageUtil.clampRoundInt((a * c + b * d) / denom);
/* 522 */             dstImag[dstPixelImag] = ImageUtil.clampRoundInt((b * c - a * d) / denom);
/* 525 */             src1PixelReal += src1PixelStride;
/* 526 */             src1PixelImag += src1PixelStride;
/* 527 */             src2PixelReal += src2PixelStride;
/* 528 */             src2PixelImag += src2PixelStride;
/* 529 */             dstPixelReal += dstPixelStride;
/* 530 */             dstPixelImag += dstPixelStride;
/*     */           } 
/*     */         } else {
/* 533 */           for (int col = 0; col < numCols; col++) {
/* 534 */             long a = src1Real[src1PixelReal];
/* 535 */             long b = src1Imag[src1PixelImag];
/* 536 */             long c = src2Real[src2PixelReal];
/* 537 */             long d = src2Imag[src2PixelImag];
/* 539 */             dstReal[dstPixelReal] = ImageUtil.clampInt(a * c - b * d);
/* 540 */             dstImag[dstPixelImag] = ImageUtil.clampInt(a * d + b * c);
/* 542 */             src1PixelReal += src1PixelStride;
/* 543 */             src1PixelImag += src1PixelStride;
/* 544 */             src2PixelReal += src2PixelStride;
/* 545 */             src2PixelImag += src2PixelStride;
/* 546 */             dstPixelReal += dstPixelStride;
/* 547 */             dstPixelImag += dstPixelStride;
/*     */           } 
/*     */         } 
/* 552 */         src1LineReal += src1ScanlineStride;
/* 553 */         src1LineImag += src1ScanlineStride;
/* 554 */         src2LineReal += src2ScanlineStride;
/* 555 */         src2LineImag += src2ScanlineStride;
/* 556 */         dstLineReal += dstScanlineStride;
/* 557 */         dstLineImag += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeRectUShort(RasterAccessor src1Accessor, RasterAccessor src2Accessor, RasterAccessor dstAccessor) {
/* 566 */     int numRows = dstAccessor.getHeight();
/* 567 */     int numCols = dstAccessor.getWidth();
/* 570 */     int src1PixelStride = src1Accessor.getPixelStride();
/* 571 */     int src1ScanlineStride = src1Accessor.getScanlineStride();
/* 572 */     int src2PixelStride = src2Accessor.getPixelStride();
/* 573 */     int src2ScanlineStride = src2Accessor.getScanlineStride();
/* 574 */     int dstPixelStride = dstAccessor.getPixelStride();
/* 575 */     int dstScanlineStride = dstAccessor.getScanlineStride();
/* 578 */     int numElements = this.sampleModel.getNumBands() / 2;
/* 579 */     for (int element = 0; element < numElements; element++) {
/* 581 */       int realBand = 2 * element;
/* 582 */       int imagBand = realBand + 1;
/* 585 */       short[] src1Real = src1Accessor.getShortDataArray(this.s1r[element]);
/* 586 */       short[] src1Imag = src1Accessor.getShortDataArray(this.s1i[element]);
/* 587 */       short[] src2Real = src2Accessor.getShortDataArray(this.s2r[element]);
/* 588 */       short[] src2Imag = src2Accessor.getShortDataArray(this.s2i[element]);
/* 589 */       short[] dstReal = dstAccessor.getShortDataArray(realBand);
/* 590 */       short[] dstImag = dstAccessor.getShortDataArray(imagBand);
/* 593 */       int src1OffsetReal = src1Accessor.getBandOffset(this.s1r[element]);
/* 594 */       int src1OffsetImag = src1Accessor.getBandOffset(this.s1i[element]);
/* 595 */       int src2OffsetReal = src2Accessor.getBandOffset(this.s2r[element]);
/* 596 */       int src2OffsetImag = src2Accessor.getBandOffset(this.s2i[element]);
/* 597 */       int dstOffsetReal = dstAccessor.getBandOffset(realBand);
/* 598 */       int dstOffsetImag = dstAccessor.getBandOffset(imagBand);
/* 601 */       int src1LineReal = src1OffsetReal;
/* 602 */       int src1LineImag = src1OffsetImag;
/* 603 */       int src2LineReal = src2OffsetReal;
/* 604 */       int src2LineImag = src2OffsetImag;
/* 605 */       int dstLineReal = dstOffsetReal;
/* 606 */       int dstLineImag = dstOffsetImag;
/* 608 */       for (int row = 0; row < numRows; row++) {
/* 610 */         int src1PixelReal = src1LineReal;
/* 611 */         int src1PixelImag = src1LineImag;
/* 612 */         int src2PixelReal = src2LineReal;
/* 613 */         int src2PixelImag = src2LineImag;
/* 614 */         int dstPixelReal = dstLineReal;
/* 615 */         int dstPixelImag = dstLineImag;
/* 618 */         if (this.isDivision) {
/* 619 */           for (int col = 0; col < numCols; col++) {
/* 620 */             int a = src1Real[src1PixelReal] & 0xFFFF;
/* 621 */             int b = src1Imag[src1PixelImag] & 0xFFFF;
/* 622 */             int c = src2Real[src2PixelReal] & 0xFFFF;
/* 623 */             int d = src2Imag[src2PixelImag] & 0xFFFF;
/* 625 */             int denom = c * c + d * d;
/* 626 */             dstReal[dstPixelReal] = ImageUtil.clampUShort((a * c + b * d) / denom);
/* 627 */             dstImag[dstPixelImag] = ImageUtil.clampUShort((b * c - a * d) / denom);
/* 629 */             src1PixelReal += src1PixelStride;
/* 630 */             src1PixelImag += src1PixelStride;
/* 631 */             src2PixelReal += src2PixelStride;
/* 632 */             src2PixelImag += src2PixelStride;
/* 633 */             dstPixelReal += dstPixelStride;
/* 634 */             dstPixelImag += dstPixelStride;
/*     */           } 
/*     */         } else {
/* 637 */           for (int col = 0; col < numCols; col++) {
/* 638 */             int a = src1Real[src1PixelReal] & 0xFFFF;
/* 639 */             int b = src1Imag[src1PixelImag] & 0xFFFF;
/* 640 */             int c = src2Real[src2PixelReal] & 0xFFFF;
/* 641 */             int d = src2Imag[src2PixelImag] & 0xFFFF;
/* 643 */             dstReal[dstPixelReal] = ImageUtil.clampUShort(a * c - b * d);
/* 644 */             dstImag[dstPixelImag] = ImageUtil.clampUShort(a * d + b * c);
/* 646 */             src1PixelReal += src1PixelStride;
/* 647 */             src1PixelImag += src1PixelStride;
/* 648 */             src2PixelReal += src2PixelStride;
/* 649 */             src2PixelImag += src2PixelStride;
/* 650 */             dstPixelReal += dstPixelStride;
/* 651 */             dstPixelImag += dstPixelStride;
/*     */           } 
/*     */         } 
/* 656 */         src1LineReal += src1ScanlineStride;
/* 657 */         src1LineImag += src1ScanlineStride;
/* 658 */         src2LineReal += src2ScanlineStride;
/* 659 */         src2LineImag += src2ScanlineStride;
/* 660 */         dstLineReal += dstScanlineStride;
/* 661 */         dstLineImag += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeRectShort(RasterAccessor src1Accessor, RasterAccessor src2Accessor, RasterAccessor dstAccessor) {
/* 670 */     int numRows = dstAccessor.getHeight();
/* 671 */     int numCols = dstAccessor.getWidth();
/* 674 */     int src1PixelStride = src1Accessor.getPixelStride();
/* 675 */     int src1ScanlineStride = src1Accessor.getScanlineStride();
/* 676 */     int src2PixelStride = src2Accessor.getPixelStride();
/* 677 */     int src2ScanlineStride = src2Accessor.getScanlineStride();
/* 678 */     int dstPixelStride = dstAccessor.getPixelStride();
/* 679 */     int dstScanlineStride = dstAccessor.getScanlineStride();
/* 682 */     int numElements = this.sampleModel.getNumBands() / 2;
/* 683 */     for (int element = 0; element < numElements; element++) {
/* 685 */       int realBand = 2 * element;
/* 686 */       int imagBand = realBand + 1;
/* 689 */       short[] src1Real = src1Accessor.getShortDataArray(this.s1r[element]);
/* 690 */       short[] src1Imag = src1Accessor.getShortDataArray(this.s1i[element]);
/* 691 */       short[] src2Real = src2Accessor.getShortDataArray(this.s2r[element]);
/* 692 */       short[] src2Imag = src2Accessor.getShortDataArray(this.s2i[element]);
/* 693 */       short[] dstReal = dstAccessor.getShortDataArray(realBand);
/* 694 */       short[] dstImag = dstAccessor.getShortDataArray(imagBand);
/* 697 */       int src1OffsetReal = src1Accessor.getBandOffset(this.s1r[element]);
/* 698 */       int src1OffsetImag = src1Accessor.getBandOffset(this.s1i[element]);
/* 699 */       int src2OffsetReal = src2Accessor.getBandOffset(this.s2r[element]);
/* 700 */       int src2OffsetImag = src2Accessor.getBandOffset(this.s2i[element]);
/* 701 */       int dstOffsetReal = dstAccessor.getBandOffset(realBand);
/* 702 */       int dstOffsetImag = dstAccessor.getBandOffset(imagBand);
/* 705 */       int src1LineReal = src1OffsetReal;
/* 706 */       int src1LineImag = src1OffsetImag;
/* 707 */       int src2LineReal = src2OffsetReal;
/* 708 */       int src2LineImag = src2OffsetImag;
/* 709 */       int dstLineReal = dstOffsetReal;
/* 710 */       int dstLineImag = dstOffsetImag;
/* 712 */       for (int row = 0; row < numRows; row++) {
/* 714 */         int src1PixelReal = src1LineReal;
/* 715 */         int src1PixelImag = src1LineImag;
/* 716 */         int src2PixelReal = src2LineReal;
/* 717 */         int src2PixelImag = src2LineImag;
/* 718 */         int dstPixelReal = dstLineReal;
/* 719 */         int dstPixelImag = dstLineImag;
/* 722 */         if (this.isDivision) {
/* 723 */           for (int col = 0; col < numCols; col++) {
/* 724 */             int a = src1Real[src1PixelReal];
/* 725 */             int b = src1Imag[src1PixelImag];
/* 726 */             int c = src2Real[src2PixelReal];
/* 727 */             int d = src2Imag[src2PixelImag];
/* 729 */             int denom = c * c + d * d;
/* 730 */             dstReal[dstPixelReal] = ImageUtil.clampShort((a * c + b * d) / denom);
/* 731 */             dstImag[dstPixelImag] = ImageUtil.clampShort((b * c - a * d) / denom);
/* 733 */             src1PixelReal += src1PixelStride;
/* 734 */             src1PixelImag += src1PixelStride;
/* 735 */             src2PixelReal += src2PixelStride;
/* 736 */             src2PixelImag += src2PixelStride;
/* 737 */             dstPixelReal += dstPixelStride;
/* 738 */             dstPixelImag += dstPixelStride;
/*     */           } 
/*     */         } else {
/* 741 */           for (int col = 0; col < numCols; col++) {
/* 742 */             int a = src1Real[src1PixelReal];
/* 743 */             int b = src1Imag[src1PixelImag];
/* 744 */             int c = src2Real[src2PixelReal];
/* 745 */             int d = src2Imag[src2PixelImag];
/* 747 */             dstReal[dstPixelReal] = ImageUtil.clampShort(a * c - b * d);
/* 748 */             dstImag[dstPixelImag] = ImageUtil.clampShort(a * d + b * c);
/* 750 */             src1PixelReal += src1PixelStride;
/* 751 */             src1PixelImag += src1PixelStride;
/* 752 */             src2PixelReal += src2PixelStride;
/* 753 */             src2PixelImag += src2PixelStride;
/* 754 */             dstPixelReal += dstPixelStride;
/* 755 */             dstPixelImag += dstPixelStride;
/*     */           } 
/*     */         } 
/* 760 */         src1LineReal += src1ScanlineStride;
/* 761 */         src1LineImag += src1ScanlineStride;
/* 762 */         src2LineReal += src2ScanlineStride;
/* 763 */         src2LineImag += src2ScanlineStride;
/* 764 */         dstLineReal += dstScanlineStride;
/* 765 */         dstLineImag += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeRectByte(RasterAccessor src1Accessor, RasterAccessor src2Accessor, RasterAccessor dstAccessor) {
/* 774 */     int numRows = dstAccessor.getHeight();
/* 775 */     int numCols = dstAccessor.getWidth();
/* 778 */     int src1PixelStride = src1Accessor.getPixelStride();
/* 779 */     int src1ScanlineStride = src1Accessor.getScanlineStride();
/* 780 */     int src2PixelStride = src2Accessor.getPixelStride();
/* 781 */     int src2ScanlineStride = src2Accessor.getScanlineStride();
/* 782 */     int dstPixelStride = dstAccessor.getPixelStride();
/* 783 */     int dstScanlineStride = dstAccessor.getScanlineStride();
/* 786 */     int numElements = this.sampleModel.getNumBands() / 2;
/* 787 */     for (int element = 0; element < numElements; element++) {
/* 789 */       int realBand = 2 * element;
/* 790 */       int imagBand = realBand + 1;
/* 793 */       byte[] src1Real = src1Accessor.getByteDataArray(this.s1r[element]);
/* 794 */       byte[] src1Imag = src1Accessor.getByteDataArray(this.s1i[element]);
/* 795 */       byte[] src2Real = src2Accessor.getByteDataArray(this.s2r[element]);
/* 796 */       byte[] src2Imag = src2Accessor.getByteDataArray(this.s2i[element]);
/* 797 */       byte[] dstReal = dstAccessor.getByteDataArray(realBand);
/* 798 */       byte[] dstImag = dstAccessor.getByteDataArray(imagBand);
/* 801 */       int src1OffsetReal = src1Accessor.getBandOffset(this.s1r[element]);
/* 802 */       int src1OffsetImag = src1Accessor.getBandOffset(this.s1i[element]);
/* 803 */       int src2OffsetReal = src2Accessor.getBandOffset(this.s2r[element]);
/* 804 */       int src2OffsetImag = src2Accessor.getBandOffset(this.s2i[element]);
/* 805 */       int dstOffsetReal = dstAccessor.getBandOffset(realBand);
/* 806 */       int dstOffsetImag = dstAccessor.getBandOffset(imagBand);
/* 809 */       int src1LineReal = src1OffsetReal;
/* 810 */       int src1LineImag = src1OffsetImag;
/* 811 */       int src2LineReal = src2OffsetReal;
/* 812 */       int src2LineImag = src2OffsetImag;
/* 813 */       int dstLineReal = dstOffsetReal;
/* 814 */       int dstLineImag = dstOffsetImag;
/* 816 */       for (int row = 0; row < numRows; row++) {
/* 818 */         int src1PixelReal = src1LineReal;
/* 819 */         int src1PixelImag = src1LineImag;
/* 820 */         int src2PixelReal = src2LineReal;
/* 821 */         int src2PixelImag = src2LineImag;
/* 822 */         int dstPixelReal = dstLineReal;
/* 823 */         int dstPixelImag = dstLineImag;
/* 826 */         if (this.isDivision) {
/* 827 */           for (int col = 0; col < numCols; col++) {
/* 828 */             int a = src1Real[src1PixelReal] & 0xFF;
/* 829 */             int b = src1Imag[src1PixelImag] & 0xFF;
/* 830 */             int c = src2Real[src2PixelReal] & 0xFF;
/* 831 */             int d = src2Imag[src2PixelImag] & 0xFF;
/* 833 */             int denom = c * c + d * d;
/* 834 */             dstReal[dstPixelReal] = ImageUtil.clampByte((a * c + b * d) / denom);
/* 835 */             dstImag[dstPixelImag] = ImageUtil.clampByte((b * c - a * d) / denom);
/* 837 */             src1PixelReal += src1PixelStride;
/* 838 */             src1PixelImag += src1PixelStride;
/* 839 */             src2PixelReal += src2PixelStride;
/* 840 */             src2PixelImag += src2PixelStride;
/* 841 */             dstPixelReal += dstPixelStride;
/* 842 */             dstPixelImag += dstPixelStride;
/*     */           } 
/*     */         } else {
/* 845 */           for (int col = 0; col < numCols; col++) {
/* 846 */             int a = src1Real[src1PixelReal] & 0xFF;
/* 847 */             int b = src1Imag[src1PixelImag] & 0xFF;
/* 848 */             int c = src2Real[src2PixelReal] & 0xFF;
/* 849 */             int d = src2Imag[src2PixelImag] & 0xFF;
/* 851 */             dstReal[dstPixelReal] = ImageUtil.clampByte(a * c - b * d);
/* 852 */             dstImag[dstPixelImag] = ImageUtil.clampByte(a * d + b * c);
/* 854 */             src1PixelReal += src1PixelStride;
/* 855 */             src1PixelImag += src1PixelStride;
/* 856 */             src2PixelReal += src2PixelStride;
/* 857 */             src2PixelImag += src2PixelStride;
/* 858 */             dstPixelReal += dstPixelStride;
/* 859 */             dstPixelImag += dstPixelStride;
/*     */           } 
/*     */         } 
/* 864 */         src1LineReal += src1ScanlineStride;
/* 865 */         src1LineImag += src1ScanlineStride;
/* 866 */         src2LineReal += src2ScanlineStride;
/* 867 */         src2LineImag += src2ScanlineStride;
/* 868 */         dstLineReal += dstScanlineStride;
/* 869 */         dstLineImag += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\ComplexArithmeticOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */