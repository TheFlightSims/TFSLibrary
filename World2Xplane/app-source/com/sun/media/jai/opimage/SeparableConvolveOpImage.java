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
/*     */ final class SeparableConvolveOpImage extends AreaOpImage {
/*  38 */   static int byteLoopCounter = 0;
/*     */   
/*     */   protected KernelJAI kernel;
/*     */   
/*     */   protected int kw;
/*     */   
/*     */   protected int kh;
/*     */   
/*     */   protected int kx;
/*     */   
/*     */   protected int ky;
/*     */   
/*     */   protected float[] hValues;
/*     */   
/*     */   protected float[] vValues;
/*     */   
/*     */   protected float[][] hTables;
/*     */   
/*     */   public SeparableConvolveOpImage(RenderedImage source, BorderExtender extender, Map config, ImageLayout layout, KernelJAI kernel) {
/*  65 */     super(source, layout, config, true, extender, kernel.getLeftPadding(), kernel.getRightPadding(), kernel.getTopPadding(), kernel.getBottomPadding());
/*  75 */     this.kernel = kernel;
/*  76 */     this.kw = kernel.getWidth();
/*  77 */     this.kh = kernel.getHeight();
/*  78 */     this.kx = kernel.getXOrigin();
/*  79 */     this.ky = kernel.getYOrigin();
/*  80 */     this.hValues = kernel.getHorizontalKernelData();
/*  81 */     this.vValues = kernel.getVerticalKernelData();
/*  83 */     if (this.sampleModel.getDataType() == 0) {
/*  84 */       this.hTables = new float[this.hValues.length][256];
/*  85 */       for (int i = 0; i < this.hValues.length; i++) {
/*  86 */         float k = this.hValues[i];
/*  87 */         for (int j = 0; j < 256; j++) {
/*  88 */           byte b = (byte)j;
/*  89 */           float f = j;
/*  90 */           this.hTables[i][b + 128] = k * f;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void computeRect(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/* 109 */     RasterFormatTag[] formatTags = getFormatTags();
/* 111 */     Raster source = sources[0];
/* 112 */     Rectangle srcRect = mapDestRect(destRect, 0);
/* 115 */     RasterAccessor srcAccessor = new RasterAccessor(source, srcRect, formatTags[0], getSource(0).getColorModel());
/* 118 */     RasterAccessor dstAccessor = new RasterAccessor(dest, destRect, formatTags[1], getColorModel());
/* 122 */     switch (dstAccessor.getDataType()) {
/*     */       case 0:
/* 124 */         byteLoop(srcAccessor, dstAccessor);
/*     */         break;
/*     */       case 3:
/* 127 */         intLoop(srcAccessor, dstAccessor);
/*     */         break;
/*     */       case 2:
/* 130 */         shortLoop(srcAccessor, dstAccessor);
/*     */         break;
/*     */       case 1:
/* 133 */         ushortLoop(srcAccessor, dstAccessor);
/*     */         break;
/*     */       case 4:
/* 136 */         floatLoop(srcAccessor, dstAccessor);
/*     */         break;
/*     */       case 5:
/* 139 */         doubleLoop(srcAccessor, dstAccessor);
/*     */         break;
/*     */     } 
/* 148 */     if (dstAccessor.isDataCopy()) {
/* 149 */       dstAccessor.clampDataArrays();
/* 150 */       dstAccessor.copyDataToRaster();
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void byteLoop(RasterAccessor src, RasterAccessor dst) {
/* 156 */     int dwidth = dst.getWidth();
/* 157 */     int dheight = dst.getHeight();
/* 158 */     int dnumBands = dst.getNumBands();
/* 160 */     byte[][] dstDataArrays = dst.getByteDataArrays();
/* 161 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 162 */     int dstPixelStride = dst.getPixelStride();
/* 163 */     int dstScanlineStride = dst.getScanlineStride();
/* 165 */     byte[][] srcDataArrays = src.getByteDataArrays();
/* 166 */     int[] srcBandOffsets = src.getBandOffsets();
/* 167 */     int srcPixelStride = src.getPixelStride();
/* 168 */     int srcScanlineStride = src.getScanlineStride();
/* 170 */     float[] tmpBuffer = new float[this.kh * dwidth];
/* 171 */     int tmpBufferSize = this.kh * dwidth;
/* 173 */     for (int k = 0; k < dnumBands; k++) {
/* 174 */       byte[] dstData = dstDataArrays[k];
/* 175 */       byte[] srcData = srcDataArrays[k];
/* 176 */       int srcScanlineOffset = srcBandOffsets[k];
/* 177 */       int dstScanlineOffset = dstBandOffsets[k];
/* 179 */       int revolver = 0;
/* 180 */       int kvRevolver = 0;
/*     */       int j;
/* 181 */       for (j = 0; j < this.kh - 1; j++) {
/* 182 */         int srcPixelOffset = srcScanlineOffset;
/* 184 */         for (int i = 0; i < dwidth; i++) {
/* 185 */           int imageOffset = srcPixelOffset;
/* 186 */           float f = 0.0F;
/* 187 */           for (int v = 0; v < this.kw; v++) {
/* 188 */             f += this.hTables[v][srcData[imageOffset] + 128];
/* 189 */             imageOffset += srcPixelStride;
/*     */           } 
/* 191 */           tmpBuffer[revolver + i] = f;
/* 192 */           srcPixelOffset += srcPixelStride;
/*     */         } 
/* 194 */         revolver += dwidth;
/* 195 */         srcScanlineOffset += srcScanlineStride;
/*     */       } 
/* 200 */       for (j = 0; j < dheight; j++) {
/* 201 */         int srcPixelOffset = srcScanlineOffset;
/* 202 */         int dstPixelOffset = dstScanlineOffset;
/* 204 */         for (int i = 0; i < dwidth; i++) {
/* 205 */           int imageOffset = srcPixelOffset;
/* 206 */           float f = 0.0F;
/* 207 */           for (int v = 0; v < this.kw; v++) {
/* 208 */             f += this.hTables[v][srcData[imageOffset] + 128];
/* 209 */             imageOffset += srcPixelStride;
/*     */           } 
/* 211 */           tmpBuffer[revolver + i] = f;
/* 213 */           f = 0.5F;
/* 216 */           int b = kvRevolver + i;
/* 217 */           for (int a = 0; a < this.kh; a++) {
/* 218 */             f += tmpBuffer[b] * this.vValues[a];
/* 219 */             b += dwidth;
/* 220 */             if (b >= tmpBufferSize)
/* 220 */               b -= tmpBufferSize; 
/*     */           } 
/* 223 */           int val = (int)f;
/* 224 */           if (val < 0) {
/* 225 */             val = 0;
/* 226 */           } else if (val > 255) {
/* 227 */             val = 255;
/*     */           } 
/* 230 */           dstData[dstPixelOffset] = (byte)val;
/* 231 */           srcPixelOffset += srcPixelStride;
/* 232 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/* 235 */         revolver += dwidth;
/* 236 */         if (revolver == tmpBufferSize)
/* 237 */           revolver = 0; 
/* 239 */         kvRevolver += dwidth;
/* 240 */         if (kvRevolver == tmpBufferSize)
/* 241 */           kvRevolver = 0; 
/* 243 */         srcScanlineOffset += srcScanlineStride;
/* 244 */         dstScanlineOffset += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void shortLoop(RasterAccessor src, RasterAccessor dst) {
/* 253 */     int dwidth = dst.getWidth();
/* 254 */     int dheight = dst.getHeight();
/* 255 */     int dnumBands = dst.getNumBands();
/* 257 */     short[][] dstDataArrays = dst.getShortDataArrays();
/* 258 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 259 */     int dstPixelStride = dst.getPixelStride();
/* 260 */     int dstScanlineStride = dst.getScanlineStride();
/* 262 */     short[][] srcDataArrays = src.getShortDataArrays();
/* 263 */     int[] srcBandOffsets = src.getBandOffsets();
/* 264 */     int srcPixelStride = src.getPixelStride();
/* 265 */     int srcScanlineStride = src.getScanlineStride();
/* 267 */     float[] tmpBuffer = new float[this.kh * dwidth];
/* 268 */     int tmpBufferSize = this.kh * dwidth;
/* 270 */     for (int k = 0; k < dnumBands; k++) {
/* 271 */       short[] dstData = dstDataArrays[k];
/* 272 */       short[] srcData = srcDataArrays[k];
/* 273 */       int srcScanlineOffset = srcBandOffsets[k];
/* 274 */       int dstScanlineOffset = dstBandOffsets[k];
/* 276 */       int revolver = 0;
/* 277 */       int kvRevolver = 0;
/*     */       int j;
/* 278 */       for (j = 0; j < this.kh - 1; j++) {
/* 279 */         int srcPixelOffset = srcScanlineOffset;
/* 281 */         for (int i = 0; i < dwidth; i++) {
/* 282 */           int imageOffset = srcPixelOffset;
/* 283 */           float f = 0.0F;
/* 284 */           for (int v = 0; v < this.kw; v++) {
/* 285 */             f += srcData[imageOffset] * this.hValues[v];
/* 286 */             imageOffset += srcPixelStride;
/*     */           } 
/* 288 */           tmpBuffer[revolver + i] = f;
/* 289 */           srcPixelOffset += srcPixelStride;
/*     */         } 
/* 291 */         revolver += dwidth;
/* 292 */         srcScanlineOffset += srcScanlineStride;
/*     */       } 
/* 298 */       for (j = 0; j < dheight; j++) {
/* 299 */         int srcPixelOffset = srcScanlineOffset;
/* 300 */         int dstPixelOffset = dstScanlineOffset;
/* 302 */         for (int i = 0; i < dwidth; i++) {
/* 303 */           int imageOffset = srcPixelOffset;
/* 304 */           float f = 0.0F;
/* 305 */           for (int v = 0; v < this.kw; v++) {
/* 306 */             f += srcData[imageOffset] * this.hValues[v];
/* 307 */             imageOffset += srcPixelStride;
/*     */           } 
/* 309 */           tmpBuffer[revolver + i] = f;
/* 311 */           f = 0.5F;
/* 312 */           int b = kvRevolver + i;
/* 313 */           for (int a = 0; a < this.kh; a++) {
/* 314 */             f += tmpBuffer[b] * this.vValues[a];
/* 315 */             b += dwidth;
/* 316 */             if (b >= tmpBufferSize)
/* 316 */               b -= tmpBufferSize; 
/*     */           } 
/* 319 */           int val = (int)f;
/* 320 */           if (val < -32768) {
/* 321 */             val = -32768;
/* 322 */           } else if (val > 32767) {
/* 323 */             val = 32767;
/*     */           } 
/* 326 */           dstData[dstPixelOffset] = (short)val;
/* 327 */           srcPixelOffset += srcPixelStride;
/* 328 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/* 330 */         revolver += dwidth;
/* 332 */         if (revolver == tmpBufferSize)
/* 333 */           revolver = 0; 
/* 335 */         kvRevolver += dwidth;
/* 336 */         if (kvRevolver == tmpBufferSize)
/* 337 */           kvRevolver = 0; 
/* 340 */         srcScanlineOffset += srcScanlineStride;
/* 341 */         dstScanlineOffset += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void ushortLoop(RasterAccessor src, RasterAccessor dst) {
/* 350 */     int dwidth = dst.getWidth();
/* 351 */     int dheight = dst.getHeight();
/* 352 */     int dnumBands = dst.getNumBands();
/* 354 */     short[][] dstDataArrays = dst.getShortDataArrays();
/* 355 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 356 */     int dstPixelStride = dst.getPixelStride();
/* 357 */     int dstScanlineStride = dst.getScanlineStride();
/* 359 */     short[][] srcDataArrays = src.getShortDataArrays();
/* 360 */     int[] srcBandOffsets = src.getBandOffsets();
/* 361 */     int srcPixelStride = src.getPixelStride();
/* 362 */     int srcScanlineStride = src.getScanlineStride();
/* 363 */     float[] tmpBuffer = new float[this.kh * dwidth];
/* 364 */     int tmpBufferSize = this.kh * dwidth;
/* 366 */     for (int k = 0; k < dnumBands; k++) {
/* 367 */       short[] dstData = dstDataArrays[k];
/* 368 */       short[] srcData = srcDataArrays[k];
/* 369 */       int srcScanlineOffset = srcBandOffsets[k];
/* 370 */       int dstScanlineOffset = dstBandOffsets[k];
/* 372 */       int revolver = 0;
/* 373 */       int kvRevolver = 0;
/*     */       int j;
/* 374 */       for (j = 0; j < this.kh - 1; j++) {
/* 375 */         int srcPixelOffset = srcScanlineOffset;
/* 377 */         for (int i = 0; i < dwidth; i++) {
/* 378 */           int imageOffset = srcPixelOffset;
/* 379 */           float f = 0.0F;
/* 380 */           for (int v = 0; v < this.kw; v++) {
/* 381 */             f += (srcData[imageOffset] & 0xFFFF) * this.hValues[v];
/* 382 */             imageOffset += srcPixelStride;
/*     */           } 
/* 384 */           tmpBuffer[revolver + i] = f;
/* 385 */           srcPixelOffset += srcPixelStride;
/*     */         } 
/* 387 */         revolver += dwidth;
/* 388 */         srcScanlineOffset += srcScanlineStride;
/*     */       } 
/* 394 */       for (j = 0; j < dheight; j++) {
/* 395 */         int srcPixelOffset = srcScanlineOffset;
/* 396 */         int dstPixelOffset = dstScanlineOffset;
/* 398 */         for (int i = 0; i < dwidth; i++) {
/* 399 */           int imageOffset = srcPixelOffset;
/* 400 */           float f = 0.0F;
/* 401 */           for (int v = 0; v < this.kw; v++) {
/* 402 */             f += (srcData[imageOffset] & 0xFFFF) * this.hValues[v];
/* 403 */             imageOffset += srcPixelStride;
/*     */           } 
/* 405 */           tmpBuffer[revolver + i] = f;
/* 407 */           f = 0.5F;
/* 409 */           int b = kvRevolver + i;
/* 410 */           for (int a = 0; a < this.kh; a++) {
/* 411 */             f += tmpBuffer[b] * this.vValues[a];
/* 412 */             b += dwidth;
/* 413 */             if (b >= tmpBufferSize)
/* 413 */               b -= tmpBufferSize; 
/*     */           } 
/* 416 */           int val = (int)f;
/* 417 */           if (val < 0) {
/* 418 */             val = 0;
/* 419 */           } else if (val > 65535) {
/* 420 */             val = 65535;
/*     */           } 
/* 423 */           dstData[dstPixelOffset] = (short)val;
/* 424 */           srcPixelOffset += srcPixelStride;
/* 425 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/* 427 */         revolver += dwidth;
/* 428 */         if (revolver == tmpBufferSize)
/* 429 */           revolver = 0; 
/* 431 */         kvRevolver += dwidth;
/* 432 */         if (kvRevolver == tmpBufferSize)
/* 433 */           kvRevolver = 0; 
/* 435 */         srcScanlineOffset += srcScanlineStride;
/* 436 */         dstScanlineOffset += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void intLoop(RasterAccessor src, RasterAccessor dst) {
/* 443 */     int dwidth = dst.getWidth();
/* 444 */     int dheight = dst.getHeight();
/* 445 */     int dnumBands = dst.getNumBands();
/* 447 */     int[][] dstDataArrays = dst.getIntDataArrays();
/* 448 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 449 */     int dstPixelStride = dst.getPixelStride();
/* 450 */     int dstScanlineStride = dst.getScanlineStride();
/* 452 */     int[][] srcDataArrays = src.getIntDataArrays();
/* 453 */     int[] srcBandOffsets = src.getBandOffsets();
/* 454 */     int srcPixelStride = src.getPixelStride();
/* 455 */     int srcScanlineStride = src.getScanlineStride();
/* 457 */     float[] tmpBuffer = new float[this.kh * dwidth];
/* 458 */     int tmpBufferSize = this.kh * dwidth;
/* 460 */     for (int k = 0; k < dnumBands; k++) {
/* 461 */       int[] dstData = dstDataArrays[k];
/* 462 */       int[] srcData = srcDataArrays[k];
/* 463 */       int srcScanlineOffset = srcBandOffsets[k];
/* 464 */       int dstScanlineOffset = dstBandOffsets[k];
/* 466 */       int revolver = 0;
/* 467 */       int kvRevolver = 0;
/*     */       int j;
/* 468 */       for (j = 0; j < this.kh - 1; j++) {
/* 469 */         int srcPixelOffset = srcScanlineOffset;
/* 471 */         for (int i = 0; i < dwidth; i++) {
/* 472 */           int imageOffset = srcPixelOffset;
/* 473 */           float f = 0.0F;
/* 474 */           for (int v = 0; v < this.kw; v++) {
/* 475 */             f += srcData[imageOffset] * this.hValues[v];
/* 476 */             imageOffset += srcPixelStride;
/*     */           } 
/* 478 */           tmpBuffer[revolver + i] = f;
/* 479 */           srcPixelOffset += srcPixelStride;
/*     */         } 
/* 481 */         revolver += dwidth;
/* 482 */         srcScanlineOffset += srcScanlineStride;
/*     */       } 
/* 487 */       for (j = 0; j < dheight; j++) {
/* 488 */         int srcPixelOffset = srcScanlineOffset;
/* 489 */         int dstPixelOffset = dstScanlineOffset;
/* 491 */         for (int i = 0; i < dwidth; i++) {
/* 492 */           int imageOffset = srcPixelOffset;
/* 493 */           float f = 0.0F;
/* 494 */           for (int v = 0; v < this.kw; v++) {
/* 495 */             f += srcData[imageOffset] * this.hValues[v];
/* 496 */             imageOffset += srcPixelStride;
/*     */           } 
/* 498 */           tmpBuffer[revolver + i] = f;
/* 500 */           f = 0.5F;
/* 502 */           int b = kvRevolver + i;
/* 503 */           for (int a = 0; a < this.kh; a++) {
/* 504 */             f += tmpBuffer[b] * this.vValues[a];
/* 505 */             b += dwidth;
/* 506 */             if (b >= tmpBufferSize)
/* 506 */               b -= tmpBufferSize; 
/*     */           } 
/* 509 */           int val = (int)f;
/* 511 */           dstData[dstPixelOffset] = val;
/* 512 */           srcPixelOffset += srcPixelStride;
/* 513 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/* 515 */         revolver += dwidth;
/* 516 */         if (revolver == tmpBufferSize)
/* 517 */           revolver = 0; 
/* 519 */         kvRevolver += dwidth;
/* 520 */         if (kvRevolver == tmpBufferSize)
/* 521 */           kvRevolver = 0; 
/* 523 */         srcScanlineOffset += srcScanlineStride;
/* 524 */         dstScanlineOffset += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void floatLoop(RasterAccessor src, RasterAccessor dst) {
/* 532 */     int dwidth = dst.getWidth();
/* 533 */     int dheight = dst.getHeight();
/* 534 */     int dnumBands = dst.getNumBands();
/* 536 */     float[][] dstDataArrays = dst.getFloatDataArrays();
/* 537 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 538 */     int dstPixelStride = dst.getPixelStride();
/* 539 */     int dstScanlineStride = dst.getScanlineStride();
/* 541 */     float[][] srcDataArrays = src.getFloatDataArrays();
/* 542 */     int[] srcBandOffsets = src.getBandOffsets();
/* 543 */     int srcPixelStride = src.getPixelStride();
/* 544 */     int srcScanlineStride = src.getScanlineStride();
/* 546 */     float[] tmpBuffer = new float[this.kh * dwidth];
/* 547 */     int tmpBufferSize = this.kh * dwidth;
/* 549 */     for (int k = 0; k < dnumBands; k++) {
/* 550 */       float[] dstData = dstDataArrays[k];
/* 551 */       float[] srcData = srcDataArrays[k];
/* 552 */       int srcScanlineOffset = srcBandOffsets[k];
/* 553 */       int dstScanlineOffset = dstBandOffsets[k];
/* 555 */       int revolver = 0;
/* 556 */       int kvRevolver = 0;
/*     */       int j;
/* 557 */       for (j = 0; j < this.kh - 1; j++) {
/* 558 */         int srcPixelOffset = srcScanlineOffset;
/* 560 */         for (int i = 0; i < dwidth; i++) {
/* 561 */           int imageOffset = srcPixelOffset;
/* 562 */           float f = 0.0F;
/* 563 */           for (int v = 0; v < this.kw; v++) {
/* 564 */             f += srcData[imageOffset] * this.hValues[v];
/* 565 */             imageOffset += srcPixelStride;
/*     */           } 
/* 567 */           tmpBuffer[revolver + i] = f;
/* 568 */           srcPixelOffset += srcPixelStride;
/*     */         } 
/* 570 */         revolver += dwidth;
/* 571 */         srcScanlineOffset += srcScanlineStride;
/*     */       } 
/* 577 */       for (j = 0; j < dheight; j++) {
/* 578 */         int srcPixelOffset = srcScanlineOffset;
/* 579 */         int dstPixelOffset = dstScanlineOffset;
/* 581 */         for (int i = 0; i < dwidth; i++) {
/* 582 */           int imageOffset = srcPixelOffset;
/* 583 */           float f = 0.0F;
/* 584 */           for (int v = 0; v < this.kw; v++) {
/* 585 */             f += srcData[imageOffset] * this.hValues[v];
/* 586 */             imageOffset += srcPixelStride;
/*     */           } 
/* 588 */           tmpBuffer[revolver + i] = f;
/* 590 */           f = 0.0F;
/* 592 */           int b = kvRevolver + i;
/* 593 */           for (int a = 0; a < this.kh; a++) {
/* 594 */             f += tmpBuffer[b] * this.vValues[a];
/* 595 */             b += dwidth;
/* 596 */             if (b >= tmpBufferSize)
/* 596 */               b -= tmpBufferSize; 
/*     */           } 
/* 599 */           dstData[dstPixelOffset] = f;
/* 600 */           srcPixelOffset += srcPixelStride;
/* 601 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/* 603 */         revolver += dwidth;
/* 604 */         if (revolver == tmpBufferSize)
/* 605 */           revolver = 0; 
/* 607 */         kvRevolver += dwidth;
/* 608 */         if (kvRevolver == tmpBufferSize)
/* 609 */           kvRevolver = 0; 
/* 611 */         srcScanlineOffset += srcScanlineStride;
/* 612 */         dstScanlineOffset += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void doubleLoop(RasterAccessor src, RasterAccessor dst) {
/* 619 */     int dwidth = dst.getWidth();
/* 620 */     int dheight = dst.getHeight();
/* 621 */     int dnumBands = dst.getNumBands();
/* 623 */     double[][] dstDataArrays = dst.getDoubleDataArrays();
/* 624 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 625 */     int dstPixelStride = dst.getPixelStride();
/* 626 */     int dstScanlineStride = dst.getScanlineStride();
/* 628 */     double[][] srcDataArrays = src.getDoubleDataArrays();
/* 629 */     int[] srcBandOffsets = src.getBandOffsets();
/* 630 */     int srcPixelStride = src.getPixelStride();
/* 631 */     int srcScanlineStride = src.getScanlineStride();
/* 633 */     double[] tmpBuffer = new double[this.kh * dwidth];
/* 634 */     int tmpBufferSize = this.kh * dwidth;
/* 636 */     for (int k = 0; k < dnumBands; k++) {
/* 637 */       double[] dstData = dstDataArrays[k];
/* 638 */       double[] srcData = srcDataArrays[k];
/* 639 */       int srcScanlineOffset = srcBandOffsets[k];
/* 640 */       int dstScanlineOffset = dstBandOffsets[k];
/* 642 */       int revolver = 0;
/* 643 */       int kvRevolver = 0;
/*     */       int j;
/* 644 */       for (j = 0; j < this.kh - 1; j++) {
/* 645 */         int srcPixelOffset = srcScanlineOffset;
/* 647 */         for (int i = 0; i < dwidth; i++) {
/* 648 */           int imageOffset = srcPixelOffset;
/* 649 */           double f = 0.0D;
/* 650 */           for (int v = 0; v < this.kw; v++) {
/* 651 */             f += srcData[imageOffset] * this.hValues[v];
/* 652 */             imageOffset += srcPixelStride;
/*     */           } 
/* 654 */           tmpBuffer[revolver + i] = f;
/* 655 */           srcPixelOffset += srcPixelStride;
/*     */         } 
/* 657 */         revolver += dwidth;
/* 658 */         srcScanlineOffset += srcScanlineStride;
/*     */       } 
/* 664 */       for (j = 0; j < dheight; j++) {
/* 665 */         int srcPixelOffset = srcScanlineOffset;
/* 666 */         int dstPixelOffset = dstScanlineOffset;
/* 668 */         for (int i = 0; i < dwidth; i++) {
/* 669 */           int imageOffset = srcPixelOffset;
/* 670 */           double f = 0.0D;
/* 671 */           for (int v = 0; v < this.kw; v++) {
/* 672 */             f += srcData[imageOffset] * this.hValues[v];
/* 673 */             imageOffset += srcPixelStride;
/*     */           } 
/* 675 */           tmpBuffer[revolver + i] = f;
/* 677 */           f = 0.0D;
/* 679 */           int b = kvRevolver + i;
/* 680 */           for (int a = 0; a < this.kh; a++) {
/* 681 */             f += tmpBuffer[b] * this.vValues[a];
/* 682 */             b += dwidth;
/* 683 */             if (b >= tmpBufferSize)
/* 683 */               b -= tmpBufferSize; 
/*     */           } 
/* 686 */           dstData[dstPixelOffset] = f;
/* 687 */           srcPixelOffset += srcPixelStride;
/* 688 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/* 690 */         revolver += dwidth;
/* 691 */         if (revolver == tmpBufferSize)
/* 692 */           revolver = 0; 
/* 694 */         kvRevolver += dwidth;
/* 695 */         if (kvRevolver == tmpBufferSize)
/* 696 */           kvRevolver = 0; 
/* 698 */         srcScanlineOffset += srcScanlineStride;
/* 699 */         dstScanlineOffset += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\SeparableConvolveOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */