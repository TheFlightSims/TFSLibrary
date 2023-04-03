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
/*     */ final class ErodeOpImage extends AreaOpImage {
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
/*     */   public ErodeOpImage(RenderedImage source, BorderExtender extender, Map config, ImageLayout layout, KernelJAI kernel) {
/* 159 */     super(source, layout, config, true, extender, kernel.getLeftPadding(), kernel.getRightPadding(), kernel.getTopPadding(), kernel.getBottomPadding());
/* 169 */     this.kernel = kernel;
/* 170 */     this.kw = kernel.getWidth();
/* 171 */     this.kh = kernel.getHeight();
/* 172 */     this.kx = kernel.getXOrigin();
/* 173 */     this.ky = kernel.getYOrigin();
/* 175 */     this.kdata = kernel.getKernelData();
/*     */   }
/*     */   
/*     */   protected void computeRect(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/* 191 */     RasterFormatTag[] formatTags = getFormatTags();
/* 193 */     Raster source = sources[0];
/* 194 */     Rectangle srcRect = mapDestRect(destRect, 0);
/* 197 */     RasterAccessor srcAccessor = new RasterAccessor(source, srcRect, formatTags[0], getSourceImage(0).getColorModel());
/* 200 */     RasterAccessor dstAccessor = new RasterAccessor(dest, destRect, formatTags[1], getColorModel());
/* 204 */     switch (dstAccessor.getDataType()) {
/*     */       case 0:
/* 206 */         byteLoop(srcAccessor, dstAccessor);
/*     */         break;
/*     */       case 3:
/* 209 */         intLoop(srcAccessor, dstAccessor);
/*     */         break;
/*     */       case 2:
/* 212 */         shortLoop(srcAccessor, dstAccessor);
/*     */         break;
/*     */       case 1:
/* 215 */         ushortLoop(srcAccessor, dstAccessor);
/*     */         break;
/*     */       case 4:
/* 218 */         floatLoop(srcAccessor, dstAccessor);
/*     */         break;
/*     */       case 5:
/* 221 */         doubleLoop(srcAccessor, dstAccessor);
/*     */         break;
/*     */     } 
/* 230 */     if (dstAccessor.isDataCopy()) {
/* 231 */       dstAccessor.clampDataArrays();
/* 232 */       dstAccessor.copyDataToRaster();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void byteLoop(RasterAccessor src, RasterAccessor dst) {
/* 237 */     int dwidth = dst.getWidth();
/* 238 */     int dheight = dst.getHeight();
/* 239 */     int dnumBands = dst.getNumBands();
/* 241 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 242 */     int dstPixelStride = dst.getPixelStride();
/* 243 */     int dstScanlineStride = dst.getScanlineStride();
/* 245 */     int[] srcBandOffsets = src.getBandOffsets();
/* 246 */     int srcPixelStride = src.getPixelStride();
/* 247 */     int srcScanlineStride = src.getScanlineStride();
/* 249 */     byte[][] dstDataArrays = dst.getByteDataArrays();
/* 250 */     byte[][] srcDataArrays = src.getByteDataArrays();
/* 252 */     for (int k = 0; k < dnumBands; k++) {
/* 253 */       byte[] dstData = dstDataArrays[k];
/* 254 */       byte[] srcData = srcDataArrays[k];
/* 255 */       int srcScanlineOffset = srcBandOffsets[k];
/* 256 */       int dstScanlineOffset = dstBandOffsets[k];
/* 257 */       for (int j = 0; j < dheight; j++) {
/* 258 */         int srcPixelOffset = srcScanlineOffset;
/* 259 */         int dstPixelOffset = dstScanlineOffset;
/* 261 */         for (int i = 0; i < dwidth; i++) {
/* 262 */           int kernelVerticalOffset = 0;
/* 263 */           int imageVerticalOffset = srcPixelOffset;
/* 264 */           float f = Float.POSITIVE_INFINITY;
/* 265 */           for (int u = 0; u < this.kh; u++) {
/* 266 */             int imageOffset = imageVerticalOffset;
/* 267 */             for (int v = 0; v < this.kw; v++) {
/* 268 */               float tmpIK = (srcData[imageOffset] & 0xFF) - this.kdata[kernelVerticalOffset + v];
/* 270 */               if (tmpIK < f)
/* 271 */                 f = tmpIK; 
/* 273 */               imageOffset += srcPixelStride;
/*     */             } 
/* 275 */             kernelVerticalOffset += this.kw;
/* 276 */             imageVerticalOffset += srcScanlineStride;
/*     */           } 
/* 279 */           if (Float.isInfinite(f))
/* 280 */             f = 0.0F; 
/* 282 */           int val = (int)f;
/* 284 */           if (val < 0) {
/* 285 */             val = 0;
/* 286 */           } else if (val > 255) {
/* 287 */             val = 255;
/*     */           } 
/* 289 */           dstData[dstPixelOffset] = (byte)val;
/* 290 */           srcPixelOffset += srcPixelStride;
/* 291 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/* 293 */         srcScanlineOffset += srcScanlineStride;
/* 294 */         dstScanlineOffset += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void shortLoop(RasterAccessor src, RasterAccessor dst) {
/* 302 */     int dwidth = dst.getWidth();
/* 303 */     int dheight = dst.getHeight();
/* 304 */     int dnumBands = dst.getNumBands();
/* 306 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 307 */     int dstPixelStride = dst.getPixelStride();
/* 308 */     int dstScanlineStride = dst.getScanlineStride();
/* 310 */     int[] srcBandOffsets = src.getBandOffsets();
/* 311 */     int srcPixelStride = src.getPixelStride();
/* 312 */     int srcScanlineStride = src.getScanlineStride();
/* 314 */     short[][] dstDataArrays = dst.getShortDataArrays();
/* 315 */     short[][] srcDataArrays = src.getShortDataArrays();
/* 317 */     for (int k = 0; k < dnumBands; k++) {
/* 318 */       short[] dstData = dstDataArrays[k];
/* 319 */       short[] srcData = srcDataArrays[k];
/* 320 */       int srcScanlineOffset = srcBandOffsets[k];
/* 321 */       int dstScanlineOffset = dstBandOffsets[k];
/* 322 */       for (int j = 0; j < dheight; j++) {
/* 323 */         int srcPixelOffset = srcScanlineOffset;
/* 324 */         int dstPixelOffset = dstScanlineOffset;
/* 326 */         for (int i = 0; i < dwidth; i++) {
/* 327 */           int kernelVerticalOffset = 0;
/* 328 */           int imageVerticalOffset = srcPixelOffset;
/* 329 */           float f = Float.POSITIVE_INFINITY;
/* 330 */           for (int u = 0; u < this.kh; u++) {
/* 331 */             int imageOffset = imageVerticalOffset;
/* 332 */             for (int v = 0; v < this.kw; v++) {
/* 333 */               float tmpIK = srcData[imageOffset] - this.kdata[kernelVerticalOffset + v];
/* 335 */               if (tmpIK < f)
/* 336 */                 f = tmpIK; 
/* 338 */               imageOffset += srcPixelStride;
/*     */             } 
/* 340 */             kernelVerticalOffset += this.kw;
/* 341 */             imageVerticalOffset += srcScanlineStride;
/*     */           } 
/* 343 */           if (Float.isInfinite(f))
/* 344 */             f = 0.0F; 
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
/* 392 */           float f = Float.POSITIVE_INFINITY;
/* 393 */           for (int u = 0; u < this.kh; u++) {
/* 394 */             int imageOffset = imageVerticalOffset;
/* 395 */             for (int v = 0; v < this.kw; v++) {
/* 396 */               float tmpIK = (srcData[imageOffset] & 0xFFFF) - this.kdata[kernelVerticalOffset + v];
/* 398 */               if (tmpIK < f)
/* 399 */                 f = tmpIK; 
/* 401 */               imageOffset += srcPixelStride;
/*     */             } 
/* 403 */             kernelVerticalOffset += this.kw;
/* 404 */             imageVerticalOffset += srcScanlineStride;
/*     */           } 
/* 406 */           if (Float.isInfinite(f))
/* 407 */             f = 0.0F; 
/* 409 */           int val = (int)f;
/* 410 */           if (val < 0) {
/* 411 */             val = 0;
/* 412 */           } else if (val > 65535) {
/* 413 */             val = 65535;
/*     */           } 
/* 415 */           dstData[dstPixelOffset] = (short)val;
/* 416 */           srcPixelOffset += srcPixelStride;
/* 417 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/* 419 */         srcScanlineOffset += srcScanlineStride;
/* 420 */         dstScanlineOffset += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void intLoop(RasterAccessor src, RasterAccessor dst) {
/* 427 */     int dwidth = dst.getWidth();
/* 428 */     int dheight = dst.getHeight();
/* 429 */     int dnumBands = dst.getNumBands();
/* 431 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 432 */     int dstPixelStride = dst.getPixelStride();
/* 433 */     int dstScanlineStride = dst.getScanlineStride();
/* 435 */     int[] srcBandOffsets = src.getBandOffsets();
/* 436 */     int srcPixelStride = src.getPixelStride();
/* 437 */     int srcScanlineStride = src.getScanlineStride();
/* 439 */     int[][] dstDataArrays = dst.getIntDataArrays();
/* 440 */     int[][] srcDataArrays = src.getIntDataArrays();
/* 442 */     for (int k = 0; k < dnumBands; k++) {
/* 443 */       int[] dstData = dstDataArrays[k];
/* 444 */       int[] srcData = srcDataArrays[k];
/* 445 */       int srcScanlineOffset = srcBandOffsets[k];
/* 446 */       int dstScanlineOffset = dstBandOffsets[k];
/* 447 */       for (int j = 0; j < dheight; j++) {
/* 448 */         int srcPixelOffset = srcScanlineOffset;
/* 449 */         int dstPixelOffset = dstScanlineOffset;
/* 451 */         for (int i = 0; i < dwidth; i++) {
/* 452 */           int kernelVerticalOffset = 0;
/* 453 */           int imageVerticalOffset = srcPixelOffset;
/* 454 */           float f = Float.POSITIVE_INFINITY;
/* 455 */           for (int u = 0; u < this.kh; u++) {
/* 456 */             int imageOffset = imageVerticalOffset;
/* 457 */             for (int v = 0; v < this.kw; v++) {
/* 458 */               float tmpIK = srcData[imageOffset] - this.kdata[kernelVerticalOffset + v];
/* 460 */               if (tmpIK < f)
/* 461 */                 f = tmpIK; 
/* 463 */               imageOffset += srcPixelStride;
/*     */             } 
/* 465 */             kernelVerticalOffset += this.kw;
/* 466 */             imageVerticalOffset += srcScanlineStride;
/*     */           } 
/* 468 */           if (Float.isInfinite(f))
/* 469 */             f = 0.0F; 
/* 471 */           dstData[dstPixelOffset] = (int)f;
/* 472 */           srcPixelOffset += srcPixelStride;
/* 473 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/* 475 */         srcScanlineOffset += srcScanlineStride;
/* 476 */         dstScanlineOffset += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void floatLoop(RasterAccessor src, RasterAccessor dst) {
/* 483 */     int dwidth = dst.getWidth();
/* 484 */     int dheight = dst.getHeight();
/* 485 */     int dnumBands = dst.getNumBands();
/* 487 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 488 */     int dstPixelStride = dst.getPixelStride();
/* 489 */     int dstScanlineStride = dst.getScanlineStride();
/* 491 */     int[] srcBandOffsets = src.getBandOffsets();
/* 492 */     int srcPixelStride = src.getPixelStride();
/* 493 */     int srcScanlineStride = src.getScanlineStride();
/* 495 */     float[][] dstDataArrays = dst.getFloatDataArrays();
/* 496 */     float[][] srcDataArrays = src.getFloatDataArrays();
/* 498 */     for (int k = 0; k < dnumBands; k++) {
/* 499 */       float[] dstData = dstDataArrays[k];
/* 500 */       float[] srcData = srcDataArrays[k];
/* 501 */       int srcScanlineOffset = srcBandOffsets[k];
/* 502 */       int dstScanlineOffset = dstBandOffsets[k];
/* 503 */       for (int j = 0; j < dheight; j++) {
/* 504 */         int srcPixelOffset = srcScanlineOffset;
/* 505 */         int dstPixelOffset = dstScanlineOffset;
/* 507 */         for (int i = 0; i < dwidth; i++) {
/* 508 */           int kernelVerticalOffset = 0;
/* 509 */           int imageVerticalOffset = srcPixelOffset;
/* 510 */           float f = Float.POSITIVE_INFINITY;
/* 511 */           for (int u = 0; u < this.kh; u++) {
/* 512 */             int imageOffset = imageVerticalOffset;
/* 513 */             for (int v = 0; v < this.kw; v++) {
/* 514 */               float tmpIK = srcData[imageOffset] - this.kdata[kernelVerticalOffset + v];
/* 516 */               if (tmpIK < f)
/* 517 */                 f = tmpIK; 
/* 519 */               imageOffset += srcPixelStride;
/*     */             } 
/* 521 */             kernelVerticalOffset += this.kw;
/* 522 */             imageVerticalOffset += srcScanlineStride;
/*     */           } 
/* 524 */           if (Float.isInfinite(f))
/* 525 */             f = 0.0F; 
/* 527 */           dstData[dstPixelOffset] = f;
/* 528 */           srcPixelOffset += srcPixelStride;
/* 529 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/* 531 */         srcScanlineOffset += srcScanlineStride;
/* 532 */         dstScanlineOffset += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void doubleLoop(RasterAccessor src, RasterAccessor dst) {
/* 540 */     int dwidth = dst.getWidth();
/* 541 */     int dheight = dst.getHeight();
/* 542 */     int dnumBands = dst.getNumBands();
/* 544 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 545 */     int dstPixelStride = dst.getPixelStride();
/* 546 */     int dstScanlineStride = dst.getScanlineStride();
/* 548 */     int[] srcBandOffsets = src.getBandOffsets();
/* 549 */     int srcPixelStride = src.getPixelStride();
/* 550 */     int srcScanlineStride = src.getScanlineStride();
/* 552 */     double[][] dstDataArrays = dst.getDoubleDataArrays();
/* 553 */     double[][] srcDataArrays = src.getDoubleDataArrays();
/* 555 */     for (int k = 0; k < dnumBands; k++) {
/* 556 */       double[] dstData = dstDataArrays[k];
/* 557 */       double[] srcData = srcDataArrays[k];
/* 558 */       int srcScanlineOffset = srcBandOffsets[k];
/* 559 */       int dstScanlineOffset = dstBandOffsets[k];
/* 560 */       for (int j = 0; j < dheight; j++) {
/* 561 */         int srcPixelOffset = srcScanlineOffset;
/* 562 */         int dstPixelOffset = dstScanlineOffset;
/* 564 */         for (int i = 0; i < dwidth; i++) {
/* 565 */           int kernelVerticalOffset = 0;
/* 566 */           int imageVerticalOffset = srcPixelOffset;
/* 567 */           double f = Double.POSITIVE_INFINITY;
/* 568 */           for (int u = 0; u < this.kh; u++) {
/* 569 */             int imageOffset = imageVerticalOffset;
/* 570 */             for (int v = 0; v < this.kw; v++) {
/* 571 */               double tmpIK = srcData[imageOffset] - this.kdata[kernelVerticalOffset + v];
/* 573 */               if (tmpIK < f)
/* 574 */                 f = tmpIK; 
/* 576 */               imageOffset += srcPixelStride;
/*     */             } 
/* 578 */             kernelVerticalOffset += this.kw;
/* 579 */             imageVerticalOffset += srcScanlineStride;
/*     */           } 
/* 582 */           if (Double.isInfinite(f))
/* 583 */             f = 0.0D; 
/* 585 */           dstData[dstPixelOffset] = f;
/* 586 */           srcPixelOffset += srcPixelStride;
/* 587 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/* 589 */         srcScanlineOffset += srcScanlineStride;
/* 590 */         dstScanlineOffset += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\ErodeOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */