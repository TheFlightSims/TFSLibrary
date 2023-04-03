/*     */ package com.sun.media.jai.opimage;
/*     */ 
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.util.Map;
/*     */ import javax.media.jai.AreaOpImage;
/*     */ import javax.media.jai.BorderExtender;
/*     */ import javax.media.jai.ImageLayout;
/*     */ import javax.media.jai.KernelJAI;
/*     */ import javax.media.jai.RasterAccessor;
/*     */ import javax.media.jai.RasterFormatTag;
/*     */ 
/*     */ final class DilateOpImage extends AreaOpImage {
/*     */   protected KernelJAI kernel;
/*     */   
/*     */   private int kw;
/*     */   
/*     */   private int kh;
/*     */   
/*     */   private int kx;
/*     */   
/*     */   private int ky;
/*     */   
/*     */   private float[] kdata;
/*     */   
/*     */   public DilateOpImage(RenderedImage source, BorderExtender extender, Map config, ImageLayout layout, KernelJAI kernel) {
/* 165 */     super(source, layout, config, true, extender, kernel.getLeftPadding(), kernel.getRightPadding(), kernel.getTopPadding(), kernel.getBottomPadding());
/* 175 */     this.kernel = kernel;
/* 176 */     this.kw = kernel.getWidth();
/* 177 */     this.kh = kernel.getHeight();
/* 178 */     this.kx = kernel.getXOrigin();
/* 179 */     this.ky = kernel.getYOrigin();
/* 181 */     this.kdata = kernel.getKernelData();
/*     */   }
/*     */   
/*     */   protected void computeRect(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/* 197 */     RasterFormatTag[] formatTags = getFormatTags();
/* 199 */     Raster source = sources[0];
/* 200 */     Rectangle srcRect = mapDestRect(destRect, 0);
/* 203 */     RasterAccessor srcAccessor = new RasterAccessor(source, srcRect, formatTags[0], getSourceImage(0).getColorModel());
/* 206 */     RasterAccessor dstAccessor = new RasterAccessor(dest, destRect, formatTags[1], getColorModel());
/* 210 */     switch (dstAccessor.getDataType()) {
/*     */       case 0:
/* 212 */         byteLoop(srcAccessor, dstAccessor);
/*     */         break;
/*     */       case 3:
/* 215 */         intLoop(srcAccessor, dstAccessor);
/*     */         break;
/*     */       case 2:
/* 218 */         shortLoop(srcAccessor, dstAccessor);
/*     */         break;
/*     */       case 1:
/* 221 */         ushortLoop(srcAccessor, dstAccessor);
/*     */         break;
/*     */       case 4:
/* 224 */         floatLoop(srcAccessor, dstAccessor);
/*     */         break;
/*     */       case 5:
/* 227 */         doubleLoop(srcAccessor, dstAccessor);
/*     */         break;
/*     */     } 
/* 236 */     if (dstAccessor.isDataCopy()) {
/* 237 */       dstAccessor.clampDataArrays();
/* 238 */       dstAccessor.copyDataToRaster();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void byteLoop(RasterAccessor src, RasterAccessor dst) {
/* 244 */     int dwidth = dst.getWidth();
/* 245 */     int dheight = dst.getHeight();
/* 246 */     int dnumBands = dst.getNumBands();
/* 248 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 249 */     int dstPixelStride = dst.getPixelStride();
/* 250 */     int dstScanlineStride = dst.getScanlineStride();
/* 252 */     int[] srcBandOffsets = src.getBandOffsets();
/* 253 */     int srcPixelStride = src.getPixelStride();
/* 254 */     int srcScanlineStride = src.getScanlineStride();
/* 256 */     byte[][] dstDataArrays = dst.getByteDataArrays();
/* 257 */     byte[][] srcDataArrays = src.getByteDataArrays();
/* 259 */     for (int k = 0; k < dnumBands; k++) {
/* 260 */       byte[] dstData = dstDataArrays[k];
/* 261 */       byte[] srcData = srcDataArrays[k];
/* 262 */       int srcScanlineOffset = srcBandOffsets[k];
/* 263 */       int dstScanlineOffset = dstBandOffsets[k];
/* 264 */       for (int j = 0; j < dheight; j++) {
/* 265 */         int srcPixelOffset = srcScanlineOffset;
/* 266 */         int dstPixelOffset = dstScanlineOffset;
/* 268 */         for (int i = 0; i < dwidth; i++) {
/* 269 */           int kernelVerticalOffset = 0;
/* 270 */           int imageVerticalOffset = srcPixelOffset;
/* 271 */           float f = Float.NEGATIVE_INFINITY;
/* 272 */           for (int u = 0; u < this.kh; u++) {
/* 273 */             int imageOffset = imageVerticalOffset;
/* 274 */             for (int v = 0; v < this.kw; v++) {
/* 275 */               float tmpIK = (srcData[imageOffset] & 0xFF) + this.kdata[kernelVerticalOffset + v];
/* 277 */               if (tmpIK > f)
/* 278 */                 f = tmpIK; 
/* 280 */               imageOffset += srcPixelStride;
/*     */             } 
/* 282 */             kernelVerticalOffset += this.kw;
/* 283 */             imageVerticalOffset += srcScanlineStride;
/*     */           } 
/* 286 */           int val = (int)f;
/* 287 */           if (val < 0) {
/* 288 */             val = 0;
/* 289 */           } else if (val > 255) {
/* 290 */             val = 255;
/*     */           } 
/* 292 */           dstData[dstPixelOffset] = (byte)val;
/* 293 */           srcPixelOffset += srcPixelStride;
/* 294 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/* 296 */         srcScanlineOffset += srcScanlineStride;
/* 297 */         dstScanlineOffset += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void shortLoop(RasterAccessor src, RasterAccessor dst) {
/* 304 */     int dwidth = dst.getWidth();
/* 305 */     int dheight = dst.getHeight();
/* 306 */     int dnumBands = dst.getNumBands();
/* 308 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 309 */     int dstPixelStride = dst.getPixelStride();
/* 310 */     int dstScanlineStride = dst.getScanlineStride();
/* 312 */     int[] srcBandOffsets = src.getBandOffsets();
/* 313 */     int srcPixelStride = src.getPixelStride();
/* 314 */     int srcScanlineStride = src.getScanlineStride();
/* 316 */     short[][] dstDataArrays = dst.getShortDataArrays();
/* 317 */     short[][] srcDataArrays = src.getShortDataArrays();
/* 319 */     for (int k = 0; k < dnumBands; k++) {
/* 320 */       short[] dstData = dstDataArrays[k];
/* 321 */       short[] srcData = srcDataArrays[k];
/* 322 */       int srcScanlineOffset = srcBandOffsets[k];
/* 323 */       int dstScanlineOffset = dstBandOffsets[k];
/* 324 */       for (int j = 0; j < dheight; j++) {
/* 325 */         int srcPixelOffset = srcScanlineOffset;
/* 326 */         int dstPixelOffset = dstScanlineOffset;
/* 328 */         for (int i = 0; i < dwidth; i++) {
/* 329 */           int kernelVerticalOffset = 0;
/* 330 */           int imageVerticalOffset = srcPixelOffset;
/* 331 */           float f = Float.NEGATIVE_INFINITY;
/* 332 */           for (int u = 0; u < this.kh; u++) {
/* 333 */             int imageOffset = imageVerticalOffset;
/* 334 */             for (int v = 0; v < this.kw; v++) {
/* 335 */               float tmpIK = srcData[imageOffset] + this.kdata[kernelVerticalOffset + v];
/* 337 */               if (tmpIK > f)
/* 338 */                 f = tmpIK; 
/* 340 */               imageOffset += srcPixelStride;
/*     */             } 
/* 342 */             kernelVerticalOffset += this.kw;
/* 343 */             imageVerticalOffset += srcScanlineStride;
/*     */           } 
/* 346 */           int val = (int)f;
/* 347 */           if (val < -32768) {
/* 348 */             val = -32768;
/* 349 */           } else if (val > 32767) {
/* 350 */             val = 32767;
/*     */           } 
/* 352 */           dstData[dstPixelOffset] = (short)val;
/* 353 */           srcPixelOffset += srcPixelStride;
/* 354 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/* 356 */         srcScanlineOffset += srcScanlineStride;
/* 357 */         dstScanlineOffset += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void ushortLoop(RasterAccessor src, RasterAccessor dst) {
/* 365 */     int dwidth = dst.getWidth();
/* 366 */     int dheight = dst.getHeight();
/* 367 */     int dnumBands = dst.getNumBands();
/* 369 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 370 */     int dstPixelStride = dst.getPixelStride();
/* 371 */     int dstScanlineStride = dst.getScanlineStride();
/* 373 */     int[] srcBandOffsets = src.getBandOffsets();
/* 374 */     int srcPixelStride = src.getPixelStride();
/* 375 */     int srcScanlineStride = src.getScanlineStride();
/* 377 */     short[][] dstDataArrays = dst.getShortDataArrays();
/* 378 */     short[][] srcDataArrays = src.getShortDataArrays();
/* 380 */     for (int k = 0; k < dnumBands; k++) {
/* 381 */       short[] dstData = dstDataArrays[k];
/* 382 */       short[] srcData = srcDataArrays[k];
/* 383 */       int srcScanlineOffset = srcBandOffsets[k];
/* 384 */       int dstScanlineOffset = dstBandOffsets[k];
/* 385 */       for (int j = 0; j < dheight; j++) {
/* 386 */         int srcPixelOffset = srcScanlineOffset;
/* 387 */         int dstPixelOffset = dstScanlineOffset;
/* 389 */         for (int i = 0; i < dwidth; i++) {
/* 390 */           int kernelVerticalOffset = 0;
/* 391 */           int imageVerticalOffset = srcPixelOffset;
/* 392 */           float f = Float.NEGATIVE_INFINITY;
/* 393 */           for (int u = 0; u < this.kh; u++) {
/* 394 */             int imageOffset = imageVerticalOffset;
/* 395 */             for (int v = 0; v < this.kw; v++) {
/* 396 */               float tmpIK = (srcData[imageOffset] & 0xFFFF) + this.kdata[kernelVerticalOffset + v];
/* 398 */               if (tmpIK > f)
/* 399 */                 f = tmpIK; 
/* 401 */               imageOffset += srcPixelStride;
/*     */             } 
/* 403 */             kernelVerticalOffset += this.kw;
/* 404 */             imageVerticalOffset += srcScanlineStride;
/*     */           } 
/* 407 */           int val = (int)f;
/* 408 */           if (val < 0) {
/* 409 */             val = 0;
/* 410 */           } else if (val > 65535) {
/* 411 */             val = 65535;
/*     */           } 
/* 413 */           dstData[dstPixelOffset] = (short)val;
/* 414 */           srcPixelOffset += srcPixelStride;
/* 415 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/* 417 */         srcScanlineOffset += srcScanlineStride;
/* 418 */         dstScanlineOffset += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void intLoop(RasterAccessor src, RasterAccessor dst) {
/* 425 */     int dwidth = dst.getWidth();
/* 426 */     int dheight = dst.getHeight();
/* 427 */     int dnumBands = dst.getNumBands();
/* 429 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 430 */     int dstPixelStride = dst.getPixelStride();
/* 431 */     int dstScanlineStride = dst.getScanlineStride();
/* 433 */     int[] srcBandOffsets = src.getBandOffsets();
/* 434 */     int srcPixelStride = src.getPixelStride();
/* 435 */     int srcScanlineStride = src.getScanlineStride();
/* 437 */     int[][] dstDataArrays = dst.getIntDataArrays();
/* 438 */     int[][] srcDataArrays = src.getIntDataArrays();
/* 440 */     for (int k = 0; k < dnumBands; k++) {
/* 441 */       int[] dstData = dstDataArrays[k];
/* 442 */       int[] srcData = srcDataArrays[k];
/* 443 */       int srcScanlineOffset = srcBandOffsets[k];
/* 444 */       int dstScanlineOffset = dstBandOffsets[k];
/* 445 */       for (int j = 0; j < dheight; j++) {
/* 446 */         int srcPixelOffset = srcScanlineOffset;
/* 447 */         int dstPixelOffset = dstScanlineOffset;
/* 449 */         for (int i = 0; i < dwidth; i++) {
/* 450 */           int kernelVerticalOffset = 0;
/* 451 */           int imageVerticalOffset = srcPixelOffset;
/* 452 */           float f = Float.NEGATIVE_INFINITY;
/* 453 */           for (int u = 0; u < this.kh; u++) {
/* 454 */             int imageOffset = imageVerticalOffset;
/* 455 */             for (int v = 0; v < this.kw; v++) {
/* 456 */               float tmpIK = srcData[imageOffset] + this.kdata[kernelVerticalOffset + v];
/* 458 */               if (tmpIK > f)
/* 459 */                 f = tmpIK; 
/* 461 */               imageOffset += srcPixelStride;
/*     */             } 
/* 463 */             kernelVerticalOffset += this.kw;
/* 464 */             imageVerticalOffset += srcScanlineStride;
/*     */           } 
/* 467 */           dstData[dstPixelOffset] = (int)f;
/* 468 */           srcPixelOffset += srcPixelStride;
/* 469 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/* 471 */         srcScanlineOffset += srcScanlineStride;
/* 472 */         dstScanlineOffset += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void floatLoop(RasterAccessor src, RasterAccessor dst) {
/* 479 */     int dwidth = dst.getWidth();
/* 480 */     int dheight = dst.getHeight();
/* 481 */     int dnumBands = dst.getNumBands();
/* 483 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 484 */     int dstPixelStride = dst.getPixelStride();
/* 485 */     int dstScanlineStride = dst.getScanlineStride();
/* 487 */     int[] srcBandOffsets = src.getBandOffsets();
/* 488 */     int srcPixelStride = src.getPixelStride();
/* 489 */     int srcScanlineStride = src.getScanlineStride();
/* 491 */     float[][] dstDataArrays = dst.getFloatDataArrays();
/* 492 */     float[][] srcDataArrays = src.getFloatDataArrays();
/* 494 */     for (int k = 0; k < dnumBands; k++) {
/* 495 */       float[] dstData = dstDataArrays[k];
/* 496 */       float[] srcData = srcDataArrays[k];
/* 497 */       int srcScanlineOffset = srcBandOffsets[k];
/* 498 */       int dstScanlineOffset = dstBandOffsets[k];
/* 499 */       for (int j = 0; j < dheight; j++) {
/* 500 */         int srcPixelOffset = srcScanlineOffset;
/* 501 */         int dstPixelOffset = dstScanlineOffset;
/* 503 */         for (int i = 0; i < dwidth; i++) {
/* 504 */           int kernelVerticalOffset = 0;
/* 505 */           int imageVerticalOffset = srcPixelOffset;
/* 506 */           float f = Float.NEGATIVE_INFINITY;
/* 507 */           for (int u = 0; u < this.kh; u++) {
/* 508 */             int imageOffset = imageVerticalOffset;
/* 509 */             for (int v = 0; v < this.kw; v++) {
/* 510 */               float tmpIK = srcData[imageOffset] + this.kdata[kernelVerticalOffset + v];
/* 512 */               if (tmpIK > f)
/* 513 */                 f = tmpIK; 
/* 515 */               imageOffset += srcPixelStride;
/*     */             } 
/* 517 */             kernelVerticalOffset += this.kw;
/* 518 */             imageVerticalOffset += srcScanlineStride;
/*     */           } 
/* 521 */           dstData[dstPixelOffset] = f;
/* 522 */           srcPixelOffset += srcPixelStride;
/* 523 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/* 525 */         srcScanlineOffset += srcScanlineStride;
/* 526 */         dstScanlineOffset += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void doubleLoop(RasterAccessor src, RasterAccessor dst) {
/* 534 */     int dwidth = dst.getWidth();
/* 535 */     int dheight = dst.getHeight();
/* 536 */     int dnumBands = dst.getNumBands();
/* 538 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 539 */     int dstPixelStride = dst.getPixelStride();
/* 540 */     int dstScanlineStride = dst.getScanlineStride();
/* 542 */     int[] srcBandOffsets = src.getBandOffsets();
/* 543 */     int srcPixelStride = src.getPixelStride();
/* 544 */     int srcScanlineStride = src.getScanlineStride();
/* 546 */     double[][] dstDataArrays = dst.getDoubleDataArrays();
/* 547 */     double[][] srcDataArrays = src.getDoubleDataArrays();
/* 549 */     for (int k = 0; k < dnumBands; k++) {
/* 550 */       double[] dstData = dstDataArrays[k];
/* 551 */       double[] srcData = srcDataArrays[k];
/* 552 */       int srcScanlineOffset = srcBandOffsets[k];
/* 553 */       int dstScanlineOffset = dstBandOffsets[k];
/* 554 */       for (int j = 0; j < dheight; j++) {
/* 555 */         int srcPixelOffset = srcScanlineOffset;
/* 556 */         int dstPixelOffset = dstScanlineOffset;
/* 558 */         for (int i = 0; i < dwidth; i++) {
/* 559 */           int kernelVerticalOffset = 0;
/* 560 */           int imageVerticalOffset = srcPixelOffset;
/* 561 */           double f = Double.NEGATIVE_INFINITY;
/* 562 */           for (int u = 0; u < this.kh; u++) {
/* 563 */             int imageOffset = imageVerticalOffset;
/* 564 */             for (int v = 0; v < this.kw; v++) {
/* 565 */               double tmpIK = srcData[imageOffset] + this.kdata[kernelVerticalOffset + v];
/* 567 */               if (tmpIK > f)
/* 568 */                 f = tmpIK; 
/* 570 */               imageOffset += srcPixelStride;
/*     */             } 
/* 572 */             kernelVerticalOffset += this.kw;
/* 573 */             imageVerticalOffset += srcScanlineStride;
/*     */           } 
/* 576 */           dstData[dstPixelOffset] = f;
/* 577 */           srcPixelOffset += srcPixelStride;
/* 578 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/* 580 */         srcScanlineOffset += srcScanlineStride;
/* 581 */         dstScanlineOffset += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\DilateOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */