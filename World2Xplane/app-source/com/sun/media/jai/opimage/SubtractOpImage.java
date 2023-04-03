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
/*     */ final class SubtractOpImage extends PointOpImage {
/*  64 */   private int s1bd = 1;
/*     */   
/*  67 */   private int s2bd = 1;
/*     */   
/*     */   public SubtractOpImage(RenderedImage source1, RenderedImage source2, Map config, ImageLayout layout) {
/*  90 */     super(source1, source2, layout, config, true);
/*  92 */     int numBands1 = source1.getSampleModel().getNumBands();
/*  93 */     int numBands2 = source2.getSampleModel().getNumBands();
/*  98 */     if (layout != null && layout.isValid(256)) {
/*  99 */       SampleModel sm = layout.getSampleModel(null);
/* 100 */       int numBandsDst = sm.getNumBands();
/* 104 */       if (numBandsDst > 1 && ((numBands1 > 1 && numBands2 == 1) || (numBands1 == 1 && numBands2 > 1))) {
/* 109 */         numBandsDst = Math.min(Math.max(numBands1, numBands2), numBandsDst);
/* 113 */         if (numBandsDst != this.sampleModel.getNumBands()) {
/* 114 */           this.sampleModel = RasterFactory.createComponentSampleModel(sm, this.sampleModel.getTransferType(), this.sampleModel.getWidth(), this.sampleModel.getHeight(), numBandsDst);
/* 122 */           if (this.colorModel != null && !JDKWorkarounds.areCompatibleDataModels(this.sampleModel, this.colorModel))
/* 125 */             this.colorModel = ImageUtil.getCompatibleColorModel(this.sampleModel, config); 
/*     */         } 
/* 132 */         this.s1bd = (numBands1 == 1) ? 0 : 1;
/* 133 */         this.s2bd = (numBands2 == 1) ? 0 : 1;
/*     */       } 
/*     */     } 
/* 138 */     permitInPlaceOperation();
/*     */   }
/*     */   
/*     */   protected void computeRect(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/* 154 */     RasterFormatTag[] formatTags = getFormatTags();
/* 156 */     RasterAccessor s1 = new RasterAccessor(sources[0], destRect, formatTags[0], getSource(0).getColorModel());
/* 159 */     RasterAccessor s2 = new RasterAccessor(sources[1], destRect, formatTags[1], getSource(1).getColorModel());
/* 162 */     RasterAccessor d = new RasterAccessor(dest, destRect, formatTags[2], getColorModel());
/* 165 */     if (d.isBinary()) {
/* 166 */       byte[] src1Bits = s1.getBinaryDataArray();
/* 167 */       byte[] src2Bits = s2.getBinaryDataArray();
/* 168 */       byte[] dstBits = d.getBinaryDataArray();
/* 170 */       int length = dstBits.length;
/* 171 */       for (int i = 0; i < length; i++)
/* 174 */         dstBits[i] = (byte)(src1Bits[i] & (byte)(src2Bits[i] ^ 0xFFFFFFFF)); 
/* 177 */       d.copyBinaryDataToRaster();
/*     */       return;
/*     */     } 
/* 182 */     switch (d.getDataType()) {
/*     */       case 0:
/* 184 */         computeRectByte(s1, s2, d);
/*     */         break;
/*     */       case 1:
/* 187 */         computeRectUShort(s1, s2, d);
/*     */         break;
/*     */       case 2:
/* 190 */         computeRectShort(s1, s2, d);
/*     */         break;
/*     */       case 3:
/* 193 */         computeRectInt(s1, s2, d);
/*     */         break;
/*     */       case 4:
/* 196 */         computeRectFloat(s1, s2, d);
/*     */         break;
/*     */       case 5:
/* 199 */         computeRectDouble(s1, s2, d);
/*     */         break;
/*     */     } 
/* 203 */     if (d.needsClamping())
/* 204 */       d.clampDataArrays(); 
/* 207 */     d.copyDataToRaster();
/*     */   }
/*     */   
/*     */   private void computeRectByte(RasterAccessor src1, RasterAccessor src2, RasterAccessor dst) {
/* 213 */     int s1LineStride = src1.getScanlineStride();
/* 214 */     int s1PixelStride = src1.getPixelStride();
/* 215 */     int[] s1BandOffsets = src1.getBandOffsets();
/* 216 */     byte[][] s1Data = src1.getByteDataArrays();
/* 218 */     int s2LineStride = src2.getScanlineStride();
/* 219 */     int s2PixelStride = src2.getPixelStride();
/* 220 */     int[] s2BandOffsets = src2.getBandOffsets();
/* 221 */     byte[][] s2Data = src2.getByteDataArrays();
/* 223 */     int dwidth = dst.getWidth();
/* 224 */     int dheight = dst.getHeight();
/* 225 */     int bands = dst.getNumBands();
/* 226 */     int dLineStride = dst.getScanlineStride();
/* 227 */     int dPixelStride = dst.getPixelStride();
/* 228 */     int[] dBandOffsets = dst.getBandOffsets();
/* 229 */     byte[][] dData = dst.getByteDataArrays();
/*     */     int s2b;
/* 231 */     for (int b = 0, s1b = 0; b < bands; 
/* 232 */       b++, s1b += this.s1bd, s2b += this.s2bd) {
/* 233 */       byte[] s1 = s1Data[s1b];
/* 234 */       byte[] s2 = s2Data[s2b];
/* 235 */       byte[] d = dData[b];
/* 237 */       int s1LineOffset = s1BandOffsets[s1b];
/* 238 */       int s2LineOffset = s2BandOffsets[s2b];
/* 239 */       int dLineOffset = dBandOffsets[b];
/* 241 */       for (int h = 0; h < dheight; h++) {
/* 242 */         int s1PixelOffset = s1LineOffset;
/* 243 */         int s2PixelOffset = s2LineOffset;
/* 244 */         int dPixelOffset = dLineOffset;
/* 246 */         s1LineOffset += s1LineStride;
/* 247 */         s2LineOffset += s2LineStride;
/* 248 */         dLineOffset += dLineStride;
/* 250 */         int diff = 0;
/* 251 */         for (int w = 0; w < dwidth; w++) {
/* 257 */           diff = (s1[s1PixelOffset] & 0xFF) - (s2[s2PixelOffset] & 0xFF);
/* 258 */           d[dPixelOffset] = (byte)(diff & (diff >> 8 ^ 0xFFFFFFFF) & 0xFF);
/* 260 */           s1PixelOffset += s1PixelStride;
/* 261 */           s2PixelOffset += s2PixelStride;
/* 262 */           dPixelOffset += dPixelStride;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeRectUShort(RasterAccessor src1, RasterAccessor src2, RasterAccessor dst) {
/* 271 */     int s1LineStride = src1.getScanlineStride();
/* 272 */     int s1PixelStride = src1.getPixelStride();
/* 273 */     int[] s1BandOffsets = src1.getBandOffsets();
/* 274 */     short[][] s1Data = src1.getShortDataArrays();
/* 276 */     int s2LineStride = src2.getScanlineStride();
/* 277 */     int s2PixelStride = src2.getPixelStride();
/* 278 */     int[] s2BandOffsets = src2.getBandOffsets();
/* 279 */     short[][] s2Data = src2.getShortDataArrays();
/* 281 */     int dwidth = dst.getWidth();
/* 282 */     int dheight = dst.getHeight();
/* 283 */     int bands = dst.getNumBands();
/* 284 */     int dLineStride = dst.getScanlineStride();
/* 285 */     int dPixelStride = dst.getPixelStride();
/* 286 */     int[] dBandOffsets = dst.getBandOffsets();
/* 287 */     short[][] dData = dst.getShortDataArrays();
/*     */     int s2b;
/* 289 */     for (int b = 0, s1b = 0; b < bands; 
/* 290 */       b++, s1b += this.s1bd, s2b += this.s2bd) {
/* 291 */       short[] s1 = s1Data[s1b];
/* 292 */       short[] s2 = s2Data[s2b];
/* 293 */       short[] d = dData[b];
/* 295 */       int s1LineOffset = s1BandOffsets[s1b];
/* 296 */       int s2LineOffset = s2BandOffsets[s2b];
/* 297 */       int dLineOffset = dBandOffsets[b];
/* 299 */       for (int h = 0; h < dheight; h++) {
/* 300 */         int s1PixelOffset = s1LineOffset;
/* 301 */         int s2PixelOffset = s2LineOffset;
/* 302 */         int dPixelOffset = dLineOffset;
/* 304 */         s1LineOffset += s1LineStride;
/* 305 */         s2LineOffset += s2LineStride;
/* 306 */         dLineOffset += dLineStride;
/* 308 */         for (int w = 0; w < dwidth; w++) {
/* 309 */           d[dPixelOffset] = ImageUtil.clampUShortNegative((s1[s1PixelOffset] & 0xFFFF) - (s2[s2PixelOffset] & 0xFFFF));
/* 313 */           s1PixelOffset += s1PixelStride;
/* 314 */           s2PixelOffset += s2PixelStride;
/* 315 */           dPixelOffset += dPixelStride;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeRectShort(RasterAccessor src1, RasterAccessor src2, RasterAccessor dst) {
/* 324 */     int s1LineStride = src1.getScanlineStride();
/* 325 */     int s1PixelStride = src1.getPixelStride();
/* 326 */     int[] s1BandOffsets = src1.getBandOffsets();
/* 327 */     short[][] s1Data = src1.getShortDataArrays();
/* 329 */     int s2LineStride = src2.getScanlineStride();
/* 330 */     int s2PixelStride = src2.getPixelStride();
/* 331 */     int[] s2BandOffsets = src2.getBandOffsets();
/* 332 */     short[][] s2Data = src2.getShortDataArrays();
/* 334 */     int dwidth = dst.getWidth();
/* 335 */     int dheight = dst.getHeight();
/* 336 */     int bands = dst.getNumBands();
/* 337 */     int dLineStride = dst.getScanlineStride();
/* 338 */     int dPixelStride = dst.getPixelStride();
/* 339 */     int[] dBandOffsets = dst.getBandOffsets();
/* 340 */     short[][] dData = dst.getShortDataArrays();
/*     */     int s2b;
/* 342 */     for (int b = 0, s1b = 0; b < bands; 
/* 343 */       b++, s1b += this.s1bd, s2b += this.s2bd) {
/* 344 */       short[] s1 = s1Data[s1b];
/* 345 */       short[] s2 = s2Data[s2b];
/* 346 */       short[] d = dData[b];
/* 348 */       int s1LineOffset = s1BandOffsets[s1b];
/* 349 */       int s2LineOffset = s2BandOffsets[s2b];
/* 350 */       int dLineOffset = dBandOffsets[b];
/* 352 */       for (int h = 0; h < dheight; h++) {
/* 353 */         int s1PixelOffset = s1LineOffset;
/* 354 */         int s2PixelOffset = s2LineOffset;
/* 355 */         int dPixelOffset = dLineOffset;
/* 357 */         s1LineOffset += s1LineStride;
/* 358 */         s2LineOffset += s2LineStride;
/* 359 */         dLineOffset += dLineStride;
/* 361 */         for (int w = 0; w < dwidth; w++) {
/* 362 */           d[dPixelOffset] = ImageUtil.clampShort(s1[s1PixelOffset] - s2[s2PixelOffset]);
/* 365 */           s1PixelOffset += s1PixelStride;
/* 366 */           s2PixelOffset += s2PixelStride;
/* 367 */           dPixelOffset += dPixelStride;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeRectInt(RasterAccessor src1, RasterAccessor src2, RasterAccessor dst) {
/* 376 */     int b, s1b, s2b, s1LineStride = src1.getScanlineStride();
/* 377 */     int s1PixelStride = src1.getPixelStride();
/* 378 */     int[] s1BandOffsets = src1.getBandOffsets();
/* 379 */     int[][] s1Data = src1.getIntDataArrays();
/* 381 */     int s2LineStride = src2.getScanlineStride();
/* 382 */     int s2PixelStride = src2.getPixelStride();
/* 383 */     int[] s2BandOffsets = src2.getBandOffsets();
/* 384 */     int[][] s2Data = src2.getIntDataArrays();
/* 386 */     int dwidth = dst.getWidth();
/* 387 */     int dheight = dst.getHeight();
/* 388 */     int bands = dst.getNumBands();
/* 389 */     int dLineStride = dst.getScanlineStride();
/* 390 */     int dPixelStride = dst.getPixelStride();
/* 391 */     int[] dBandOffsets = dst.getBandOffsets();
/* 392 */     int[][] dData = dst.getIntDataArrays();
/* 399 */     switch (this.sampleModel.getTransferType()) {
/*     */       case 0:
/* 401 */         for (b = 0, s1b = 0, s2b = 0; b < bands; 
/* 402 */           b++, s1b += this.s1bd, s2b += this.s2bd) {
/* 403 */           int[] s1 = s1Data[s1b];
/* 404 */           int[] s2 = s2Data[s2b];
/* 405 */           int[] d = dData[b];
/* 407 */           int s1LineOffset = s1BandOffsets[s1b];
/* 408 */           int s2LineOffset = s2BandOffsets[s2b];
/* 409 */           int dLineOffset = dBandOffsets[b];
/* 411 */           for (int h = 0; h < dheight; h++) {
/* 412 */             int s1PixelOffset = s1LineOffset;
/* 413 */             int s2PixelOffset = s2LineOffset;
/* 414 */             int dPixelOffset = dLineOffset;
/* 416 */             s1LineOffset += s1LineStride;
/* 417 */             s2LineOffset += s2LineStride;
/* 418 */             dLineOffset += dLineStride;
/* 420 */             int diff = 0;
/* 421 */             for (int w = 0; w < dwidth; w++) {
/* 427 */               diff = (s1[s1PixelOffset] & 0xFF) - (s2[s2PixelOffset] & 0xFF);
/* 428 */               d[dPixelOffset] = diff & (diff >> 8 ^ 0xFFFFFFFF) & 0xFF;
/* 430 */               s1PixelOffset += s1PixelStride;
/* 431 */               s2PixelOffset += s2PixelStride;
/* 432 */               dPixelOffset += dPixelStride;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       case 1:
/* 439 */         for (b = 0, s1b = 0, s2b = 0; b < bands; 
/* 440 */           b++, s1b += this.s1bd, s2b += this.s2bd) {
/* 441 */           int[] s1 = s1Data[s1b];
/* 442 */           int[] s2 = s2Data[s2b];
/* 443 */           int[] d = dData[b];
/* 445 */           int s1LineOffset = s1BandOffsets[s1b];
/* 446 */           int s2LineOffset = s2BandOffsets[s2b];
/* 447 */           int dLineOffset = dBandOffsets[b];
/* 449 */           for (int h = 0; h < dheight; h++) {
/* 450 */             int s1PixelOffset = s1LineOffset;
/* 451 */             int s2PixelOffset = s2LineOffset;
/* 452 */             int dPixelOffset = dLineOffset;
/* 454 */             s1LineOffset += s1LineStride;
/* 455 */             s2LineOffset += s2LineStride;
/* 456 */             dLineOffset += dLineStride;
/* 458 */             for (int w = 0; w < dwidth; w++) {
/* 459 */               d[dPixelOffset] = ImageUtil.clampUShortNegative((s1[s1PixelOffset] & 0xFFFF) - (s2[s2PixelOffset] & 0xFFFF));
/* 463 */               s1PixelOffset += s1PixelStride;
/* 464 */               s2PixelOffset += s2PixelStride;
/* 465 */               dPixelOffset += dPixelStride;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       case 2:
/* 472 */         for (b = 0, s1b = 0, s2b = 0; b < bands; 
/* 473 */           b++, s1b += this.s1bd, s2b += this.s2bd) {
/* 474 */           int[] s1 = s1Data[s1b];
/* 475 */           int[] s2 = s2Data[s2b];
/* 476 */           int[] d = dData[b];
/* 478 */           int s1LineOffset = s1BandOffsets[s1b];
/* 479 */           int s2LineOffset = s2BandOffsets[s2b];
/* 480 */           int dLineOffset = dBandOffsets[b];
/* 482 */           for (int h = 0; h < dheight; h++) {
/* 483 */             int s1PixelOffset = s1LineOffset;
/* 484 */             int s2PixelOffset = s2LineOffset;
/* 485 */             int dPixelOffset = dLineOffset;
/* 487 */             s1LineOffset += s1LineStride;
/* 488 */             s2LineOffset += s2LineStride;
/* 489 */             dLineOffset += dLineStride;
/* 491 */             for (int w = 0; w < dwidth; w++) {
/* 492 */               d[dPixelOffset] = ImageUtil.clampShort(s1[s1PixelOffset] - s2[s2PixelOffset]);
/* 495 */               s1PixelOffset += s1PixelStride;
/* 496 */               s2PixelOffset += s2PixelStride;
/* 497 */               dPixelOffset += dPixelStride;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       case 3:
/* 504 */         for (b = 0, s1b = 0, s2b = 0; b < bands; 
/* 505 */           b++, s1b += this.s1bd, s2b += this.s2bd) {
/* 506 */           int[] s1 = s1Data[s1b];
/* 507 */           int[] s2 = s2Data[s2b];
/* 508 */           int[] d = dData[b];
/* 510 */           int s1LineOffset = s1BandOffsets[s1b];
/* 511 */           int s2LineOffset = s2BandOffsets[s2b];
/* 512 */           int dLineOffset = dBandOffsets[b];
/* 514 */           for (int h = 0; h < dheight; h++) {
/* 515 */             int s1PixelOffset = s1LineOffset;
/* 516 */             int s2PixelOffset = s2LineOffset;
/* 517 */             int dPixelOffset = dLineOffset;
/* 519 */             s1LineOffset += s1LineStride;
/* 520 */             s2LineOffset += s2LineStride;
/* 521 */             dLineOffset += dLineStride;
/* 523 */             for (int w = 0; w < dwidth; w++) {
/* 524 */               d[dPixelOffset] = ImageUtil.clampInt(s1[s1PixelOffset] - s2[s2PixelOffset]);
/* 527 */               s1PixelOffset += s1PixelStride;
/* 528 */               s2PixelOffset += s2PixelStride;
/* 529 */               dPixelOffset += dPixelStride;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */         break;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeRectFloat(RasterAccessor src1, RasterAccessor src2, RasterAccessor dst) {
/* 540 */     int s1LineStride = src1.getScanlineStride();
/* 541 */     int s1PixelStride = src1.getPixelStride();
/* 542 */     int[] s1BandOffsets = src1.getBandOffsets();
/* 543 */     float[][] s1Data = src1.getFloatDataArrays();
/* 545 */     int s2LineStride = src2.getScanlineStride();
/* 546 */     int s2PixelStride = src2.getPixelStride();
/* 547 */     int[] s2BandOffsets = src2.getBandOffsets();
/* 548 */     float[][] s2Data = src2.getFloatDataArrays();
/* 550 */     int dwidth = dst.getWidth();
/* 551 */     int dheight = dst.getHeight();
/* 552 */     int bands = dst.getNumBands();
/* 553 */     int dLineStride = dst.getScanlineStride();
/* 554 */     int dPixelStride = dst.getPixelStride();
/* 555 */     int[] dBandOffsets = dst.getBandOffsets();
/* 556 */     float[][] dData = dst.getFloatDataArrays();
/*     */     int s2b;
/* 558 */     for (int b = 0, s1b = 0; b < bands; 
/* 559 */       b++, s1b += this.s1bd, s2b += this.s2bd) {
/* 560 */       float[] s1 = s1Data[s1b];
/* 561 */       float[] s2 = s2Data[s2b];
/* 562 */       float[] d = dData[b];
/* 564 */       int s1LineOffset = s1BandOffsets[s1b];
/* 565 */       int s2LineOffset = s2BandOffsets[s2b];
/* 566 */       int dLineOffset = dBandOffsets[b];
/* 568 */       for (int h = 0; h < dheight; h++) {
/* 569 */         int s1PixelOffset = s1LineOffset;
/* 570 */         int s2PixelOffset = s2LineOffset;
/* 571 */         int dPixelOffset = dLineOffset;
/* 573 */         s1LineOffset += s1LineStride;
/* 574 */         s2LineOffset += s2LineStride;
/* 575 */         dLineOffset += dLineStride;
/* 577 */         for (int w = 0; w < dwidth; w++) {
/* 578 */           d[dPixelOffset] = s1[s1PixelOffset] - s2[s2PixelOffset];
/* 580 */           s1PixelOffset += s1PixelStride;
/* 581 */           s2PixelOffset += s2PixelStride;
/* 582 */           dPixelOffset += dPixelStride;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeRectDouble(RasterAccessor src1, RasterAccessor src2, RasterAccessor dst) {
/* 591 */     int s1LineStride = src1.getScanlineStride();
/* 592 */     int s1PixelStride = src1.getPixelStride();
/* 593 */     int[] s1BandOffsets = src1.getBandOffsets();
/* 594 */     double[][] s1Data = src1.getDoubleDataArrays();
/* 596 */     int s2LineStride = src2.getScanlineStride();
/* 597 */     int s2PixelStride = src2.getPixelStride();
/* 598 */     int[] s2BandOffsets = src2.getBandOffsets();
/* 599 */     double[][] s2Data = src2.getDoubleDataArrays();
/* 601 */     int dwidth = dst.getWidth();
/* 602 */     int dheight = dst.getHeight();
/* 603 */     int bands = dst.getNumBands();
/* 604 */     int dLineStride = dst.getScanlineStride();
/* 605 */     int dPixelStride = dst.getPixelStride();
/* 606 */     int[] dBandOffsets = dst.getBandOffsets();
/* 607 */     double[][] dData = dst.getDoubleDataArrays();
/*     */     int s2b;
/* 609 */     for (int b = 0, s1b = 0; b < bands; 
/* 610 */       b++, s1b += this.s1bd, s2b += this.s2bd) {
/* 611 */       double[] s1 = s1Data[s1b];
/* 612 */       double[] s2 = s2Data[s2b];
/* 613 */       double[] d = dData[b];
/* 615 */       int s1LineOffset = s1BandOffsets[s1b];
/* 616 */       int s2LineOffset = s2BandOffsets[s2b];
/* 617 */       int dLineOffset = dBandOffsets[b];
/* 619 */       for (int h = 0; h < dheight; h++) {
/* 620 */         int s1PixelOffset = s1LineOffset;
/* 621 */         int s2PixelOffset = s2LineOffset;
/* 622 */         int dPixelOffset = dLineOffset;
/* 624 */         s1LineOffset += s1LineStride;
/* 625 */         s2LineOffset += s2LineStride;
/* 626 */         dLineOffset += dLineStride;
/* 628 */         for (int w = 0; w < dwidth; w++) {
/* 629 */           d[dPixelOffset] = s1[s1PixelOffset] - s2[s2PixelOffset];
/* 631 */           s1PixelOffset += s1PixelStride;
/* 632 */           s2PixelOffset += s2PixelStride;
/* 633 */           dPixelOffset += dPixelStride;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\SubtractOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */