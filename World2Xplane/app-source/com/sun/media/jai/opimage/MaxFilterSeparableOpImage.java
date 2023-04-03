/*     */ package com.sun.media.jai.opimage;
/*     */ 
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.util.Map;
/*     */ import javax.media.jai.BorderExtender;
/*     */ import javax.media.jai.ImageLayout;
/*     */ import javax.media.jai.RasterAccessor;
/*     */ import javax.media.jai.operator.MaxFilterDescriptor;
/*     */ 
/*     */ final class MaxFilterSeparableOpImage extends MaxFilterOpImage {
/*     */   public MaxFilterSeparableOpImage(RenderedImage source, BorderExtender extender, Map config, ImageLayout layout, int maskSize) {
/*  54 */     super(source, extender, config, layout, MaxFilterDescriptor.MAX_MASK_SQUARE, maskSize);
/*     */   }
/*     */   
/*     */   protected void byteLoop(RasterAccessor src, RasterAccessor dst, int filterSize) {
/*  65 */     int dwidth = dst.getWidth();
/*  66 */     int dheight = dst.getHeight();
/*  67 */     int dnumBands = dst.getNumBands();
/*  69 */     byte[][] dstDataArrays = dst.getByteDataArrays();
/*  70 */     int[] dstBandOffsets = dst.getBandOffsets();
/*  71 */     int dstPixelStride = dst.getPixelStride();
/*  72 */     int dstScanlineStride = dst.getScanlineStride();
/*  74 */     byte[][] srcDataArrays = src.getByteDataArrays();
/*  75 */     int[] srcBandOffsets = src.getBandOffsets();
/*  76 */     int srcPixelStride = src.getPixelStride();
/*  77 */     int srcScanlineStride = src.getScanlineStride();
/*  79 */     int[] maxValues = new int[filterSize];
/*  81 */     int wp = filterSize;
/*  82 */     int[] tmpBuffer = new int[filterSize * dwidth];
/*  83 */     int tmpBufferSize = filterSize * dwidth;
/*  85 */     for (int k = 0; k < dnumBands; k++) {
/*  86 */       byte[] dstData = dstDataArrays[k];
/*  87 */       byte[] srcData = srcDataArrays[k];
/*  88 */       int srcScanlineOffset = srcBandOffsets[k];
/*  89 */       int dstScanlineOffset = dstBandOffsets[k];
/*  91 */       int revolver = 0;
/*     */       int j;
/*  92 */       for (j = 0; j < filterSize - 1; j++) {
/*  93 */         int srcPixelOffset = srcScanlineOffset;
/*  95 */         for (int i = 0; i < dwidth; i++) {
/*  96 */           int imageOffset = srcPixelOffset;
/*  97 */           int maxval = Integer.MIN_VALUE;
/*  98 */           for (int v = 0; v < wp; v++) {
/*  99 */             int val = srcData[imageOffset] & 0xFF;
/* 100 */             imageOffset += srcPixelStride;
/* 101 */             maxval = (val > maxval) ? val : maxval;
/*     */           } 
/* 103 */           tmpBuffer[revolver + i] = maxval;
/* 104 */           srcPixelOffset += srcPixelStride;
/*     */         } 
/* 106 */         revolver += dwidth;
/* 107 */         srcScanlineOffset += srcScanlineStride;
/*     */       } 
/* 113 */       for (j = 0; j < dheight; j++) {
/* 114 */         int srcPixelOffset = srcScanlineOffset;
/* 115 */         int dstPixelOffset = dstScanlineOffset;
/* 117 */         for (int i = 0; i < dwidth; i++) {
/* 118 */           int imageOffset = srcPixelOffset;
/* 119 */           int maxval = Integer.MIN_VALUE;
/* 120 */           for (int v = 0; v < wp; v++) {
/* 121 */             int val = srcData[imageOffset] & 0xFF;
/* 122 */             imageOffset += srcPixelStride;
/* 123 */             maxval = (val > maxval) ? val : maxval;
/*     */           } 
/* 125 */           tmpBuffer[revolver + i] = maxval;
/* 127 */           maxval = Integer.MIN_VALUE;
/*     */           int b;
/* 128 */           for (b = i; b < tmpBufferSize; b += dwidth) {
/* 129 */             int val = tmpBuffer[b];
/* 130 */             maxval = (val > maxval) ? val : maxval;
/*     */           } 
/* 133 */           dstData[dstPixelOffset] = (byte)maxval;
/* 134 */           srcPixelOffset += srcPixelStride;
/* 135 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/* 138 */         revolver += dwidth;
/* 139 */         if (revolver == tmpBufferSize)
/* 140 */           revolver = 0; 
/* 143 */         srcScanlineOffset += srcScanlineStride;
/* 144 */         dstScanlineOffset += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void shortLoop(RasterAccessor src, RasterAccessor dst, int filterSize) {
/* 152 */     int dwidth = dst.getWidth();
/* 153 */     int dheight = dst.getHeight();
/* 154 */     int dnumBands = dst.getNumBands();
/* 156 */     short[][] dstDataArrays = dst.getShortDataArrays();
/* 157 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 158 */     int dstPixelStride = dst.getPixelStride();
/* 159 */     int dstScanlineStride = dst.getScanlineStride();
/* 161 */     short[][] srcDataArrays = src.getShortDataArrays();
/* 162 */     int[] srcBandOffsets = src.getBandOffsets();
/* 163 */     int srcPixelStride = src.getPixelStride();
/* 164 */     int srcScanlineStride = src.getScanlineStride();
/* 166 */     int[] maxValues = new int[filterSize];
/* 168 */     int wp = filterSize;
/* 169 */     int[] tmpBuffer = new int[filterSize * dwidth];
/* 170 */     int tmpBufferSize = filterSize * dwidth;
/* 172 */     for (int k = 0; k < dnumBands; k++) {
/* 173 */       short[] dstData = dstDataArrays[k];
/* 174 */       short[] srcData = srcDataArrays[k];
/* 175 */       int srcScanlineOffset = srcBandOffsets[k];
/* 176 */       int dstScanlineOffset = dstBandOffsets[k];
/* 178 */       int revolver = 0;
/*     */       int j;
/* 179 */       for (j = 0; j < filterSize - 1; j++) {
/* 180 */         int srcPixelOffset = srcScanlineOffset;
/* 182 */         for (int i = 0; i < dwidth; i++) {
/* 183 */           int imageOffset = srcPixelOffset;
/* 184 */           int maxval = Integer.MIN_VALUE;
/* 185 */           for (int v = 0; v < wp; v++) {
/* 186 */             int val = srcData[imageOffset];
/* 187 */             imageOffset += srcPixelStride;
/* 188 */             maxval = (val > maxval) ? val : maxval;
/*     */           } 
/* 190 */           tmpBuffer[revolver + i] = maxval;
/* 191 */           srcPixelOffset += srcPixelStride;
/*     */         } 
/* 193 */         revolver += dwidth;
/* 194 */         srcScanlineOffset += srcScanlineStride;
/*     */       } 
/* 200 */       for (j = 0; j < dheight; j++) {
/* 201 */         int srcPixelOffset = srcScanlineOffset;
/* 202 */         int dstPixelOffset = dstScanlineOffset;
/* 204 */         for (int i = 0; i < dwidth; i++) {
/* 205 */           int imageOffset = srcPixelOffset;
/* 206 */           int maxval = Integer.MIN_VALUE;
/* 207 */           for (int v = 0; v < wp; v++) {
/* 208 */             int val = srcData[imageOffset];
/* 209 */             imageOffset += srcPixelStride;
/* 210 */             maxval = (val > maxval) ? val : maxval;
/*     */           } 
/* 212 */           tmpBuffer[revolver + i] = maxval;
/* 214 */           maxval = Integer.MIN_VALUE;
/*     */           int b;
/* 215 */           for (b = i; b < tmpBufferSize; b += dwidth) {
/* 216 */             int val = tmpBuffer[b];
/* 217 */             maxval = (val > maxval) ? val : maxval;
/*     */           } 
/* 220 */           dstData[dstPixelOffset] = (short)maxval;
/* 221 */           srcPixelOffset += srcPixelStride;
/* 222 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/* 224 */         revolver += dwidth;
/* 225 */         if (revolver == tmpBufferSize)
/* 226 */           revolver = 0; 
/* 228 */         srcScanlineOffset += srcScanlineStride;
/* 229 */         dstScanlineOffset += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void ushortLoop(RasterAccessor src, RasterAccessor dst, int filterSize) {
/* 239 */     int dwidth = dst.getWidth();
/* 240 */     int dheight = dst.getHeight();
/* 241 */     int dnumBands = dst.getNumBands();
/* 243 */     short[][] dstDataArrays = dst.getShortDataArrays();
/* 244 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 245 */     int dstPixelStride = dst.getPixelStride();
/* 246 */     int dstScanlineStride = dst.getScanlineStride();
/* 248 */     short[][] srcDataArrays = src.getShortDataArrays();
/* 249 */     int[] srcBandOffsets = src.getBandOffsets();
/* 250 */     int srcPixelStride = src.getPixelStride();
/* 251 */     int srcScanlineStride = src.getScanlineStride();
/* 253 */     int[] maxValues = new int[filterSize];
/* 255 */     int wp = filterSize;
/* 256 */     int[] tmpBuffer = new int[filterSize * dwidth];
/* 257 */     int tmpBufferSize = filterSize * dwidth;
/* 259 */     for (int k = 0; k < dnumBands; k++) {
/* 260 */       short[] dstData = dstDataArrays[k];
/* 261 */       short[] srcData = srcDataArrays[k];
/* 262 */       int srcScanlineOffset = srcBandOffsets[k];
/* 263 */       int dstScanlineOffset = dstBandOffsets[k];
/* 265 */       int revolver = 0;
/*     */       int j;
/* 266 */       for (j = 0; j < filterSize - 1; j++) {
/* 267 */         int srcPixelOffset = srcScanlineOffset;
/* 269 */         for (int i = 0; i < dwidth; i++) {
/* 270 */           int imageOffset = srcPixelOffset;
/* 271 */           int maxval = Integer.MIN_VALUE;
/* 272 */           for (int v = 0; v < wp; v++) {
/* 273 */             int val = srcData[imageOffset] & 0xFFF;
/* 274 */             imageOffset += srcPixelStride;
/* 275 */             maxval = (val > maxval) ? val : maxval;
/*     */           } 
/* 277 */           tmpBuffer[revolver + i] = maxval;
/* 278 */           srcPixelOffset += srcPixelStride;
/*     */         } 
/* 280 */         revolver += dwidth;
/* 281 */         srcScanlineOffset += srcScanlineStride;
/*     */       } 
/* 287 */       for (j = 0; j < dheight; j++) {
/* 288 */         int srcPixelOffset = srcScanlineOffset;
/* 289 */         int dstPixelOffset = dstScanlineOffset;
/* 291 */         for (int i = 0; i < dwidth; i++) {
/* 292 */           int imageOffset = srcPixelOffset;
/* 293 */           int maxval = Integer.MIN_VALUE;
/* 294 */           for (int v = 0; v < wp; v++) {
/* 295 */             int val = srcData[imageOffset] & 0xFFFF;
/* 296 */             imageOffset += srcPixelStride;
/* 297 */             maxval = (val > maxval) ? val : maxval;
/*     */           } 
/* 299 */           tmpBuffer[revolver + i] = maxval;
/* 301 */           maxval = Integer.MIN_VALUE;
/*     */           int b;
/* 302 */           for (b = i; b < tmpBufferSize; b += dwidth) {
/* 303 */             int val = tmpBuffer[b];
/* 304 */             maxval = (val > maxval) ? val : maxval;
/*     */           } 
/* 307 */           dstData[dstPixelOffset] = (short)maxval;
/* 308 */           srcPixelOffset += srcPixelStride;
/* 309 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/* 311 */         revolver += dwidth;
/* 312 */         if (revolver == tmpBufferSize)
/* 313 */           revolver = 0; 
/* 315 */         srcScanlineOffset += srcScanlineStride;
/* 316 */         dstScanlineOffset += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void intLoop(RasterAccessor src, RasterAccessor dst, int filterSize) {
/* 324 */     int dwidth = dst.getWidth();
/* 325 */     int dheight = dst.getHeight();
/* 326 */     int dnumBands = dst.getNumBands();
/* 328 */     int[][] dstDataArrays = dst.getIntDataArrays();
/* 329 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 330 */     int dstPixelStride = dst.getPixelStride();
/* 331 */     int dstScanlineStride = dst.getScanlineStride();
/* 333 */     int[][] srcDataArrays = src.getIntDataArrays();
/* 334 */     int[] srcBandOffsets = src.getBandOffsets();
/* 335 */     int srcPixelStride = src.getPixelStride();
/* 336 */     int srcScanlineStride = src.getScanlineStride();
/* 338 */     int[] maxValues = new int[filterSize];
/* 340 */     int wp = filterSize;
/* 341 */     int[] tmpBuffer = new int[filterSize * dwidth];
/* 342 */     int tmpBufferSize = filterSize * dwidth;
/* 344 */     for (int k = 0; k < dnumBands; k++) {
/* 345 */       int[] dstData = dstDataArrays[k];
/* 346 */       int[] srcData = srcDataArrays[k];
/* 347 */       int srcScanlineOffset = srcBandOffsets[k];
/* 348 */       int dstScanlineOffset = dstBandOffsets[k];
/* 350 */       int revolver = 0;
/*     */       int j;
/* 351 */       for (j = 0; j < filterSize - 1; j++) {
/* 352 */         int srcPixelOffset = srcScanlineOffset;
/* 354 */         for (int i = 0; i < dwidth; i++) {
/* 355 */           int imageOffset = srcPixelOffset;
/* 356 */           int maxval = Integer.MIN_VALUE;
/* 357 */           for (int v = 0; v < wp; v++) {
/* 358 */             int val = srcData[imageOffset];
/* 359 */             imageOffset += srcPixelStride;
/* 360 */             maxval = (val > maxval) ? val : maxval;
/*     */           } 
/* 362 */           tmpBuffer[revolver + i] = maxval;
/* 363 */           srcPixelOffset += srcPixelStride;
/*     */         } 
/* 365 */         revolver += dwidth;
/* 366 */         srcScanlineOffset += srcScanlineStride;
/*     */       } 
/* 372 */       for (j = 0; j < dheight; j++) {
/* 373 */         int srcPixelOffset = srcScanlineOffset;
/* 374 */         int dstPixelOffset = dstScanlineOffset;
/* 376 */         for (int i = 0; i < dwidth; i++) {
/* 377 */           int imageOffset = srcPixelOffset;
/* 378 */           int maxval = Integer.MIN_VALUE;
/* 379 */           for (int v = 0; v < wp; v++) {
/* 380 */             int val = srcData[imageOffset];
/* 381 */             imageOffset += srcPixelStride;
/* 382 */             maxval = (val > maxval) ? val : maxval;
/*     */           } 
/* 384 */           tmpBuffer[revolver + i] = maxval;
/* 386 */           maxval = Integer.MIN_VALUE;
/*     */           int b;
/* 387 */           for (b = i; b < tmpBufferSize; b += dwidth) {
/* 388 */             int val = tmpBuffer[b];
/* 389 */             maxval = (val > maxval) ? val : maxval;
/*     */           } 
/* 392 */           dstData[dstPixelOffset] = maxval;
/* 393 */           srcPixelOffset += srcPixelStride;
/* 394 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/* 396 */         revolver += dwidth;
/* 397 */         if (revolver == tmpBufferSize)
/* 398 */           revolver = 0; 
/* 400 */         srcScanlineOffset += srcScanlineStride;
/* 401 */         dstScanlineOffset += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void floatLoop(RasterAccessor src, RasterAccessor dst, int filterSize) {
/* 410 */     int dwidth = dst.getWidth();
/* 411 */     int dheight = dst.getHeight();
/* 412 */     int dnumBands = dst.getNumBands();
/* 414 */     float[][] dstDataArrays = dst.getFloatDataArrays();
/* 415 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 416 */     int dstPixelStride = dst.getPixelStride();
/* 417 */     int dstScanlineStride = dst.getScanlineStride();
/* 419 */     float[][] srcDataArrays = src.getFloatDataArrays();
/* 420 */     int[] srcBandOffsets = src.getBandOffsets();
/* 421 */     int srcPixelStride = src.getPixelStride();
/* 422 */     int srcScanlineStride = src.getScanlineStride();
/* 424 */     float[] maxValues = new float[filterSize];
/* 426 */     int wp = filterSize;
/* 427 */     float[] tmpBuffer = new float[filterSize * dwidth];
/* 428 */     int tmpBufferSize = filterSize * dwidth;
/* 430 */     for (int k = 0; k < dnumBands; k++) {
/* 431 */       float[] dstData = dstDataArrays[k];
/* 432 */       float[] srcData = srcDataArrays[k];
/* 433 */       int srcScanlineOffset = srcBandOffsets[k];
/* 434 */       int dstScanlineOffset = dstBandOffsets[k];
/* 436 */       int revolver = 0;
/*     */       int j;
/* 437 */       for (j = 0; j < filterSize - 1; j++) {
/* 438 */         int srcPixelOffset = srcScanlineOffset;
/* 440 */         for (int i = 0; i < dwidth; i++) {
/* 441 */           int imageOffset = srcPixelOffset;
/* 442 */           float maxval = -3.4028235E38F;
/* 443 */           for (int v = 0; v < wp; v++) {
/* 444 */             float val = srcData[imageOffset];
/* 445 */             imageOffset += srcPixelStride;
/* 446 */             maxval = (val > maxval) ? val : maxval;
/*     */           } 
/* 448 */           tmpBuffer[revolver + i] = maxval;
/* 449 */           srcPixelOffset += srcPixelStride;
/*     */         } 
/* 451 */         revolver += dwidth;
/* 452 */         srcScanlineOffset += srcScanlineStride;
/*     */       } 
/* 458 */       for (j = 0; j < dheight; j++) {
/* 459 */         int srcPixelOffset = srcScanlineOffset;
/* 460 */         int dstPixelOffset = dstScanlineOffset;
/* 462 */         for (int i = 0; i < dwidth; i++) {
/* 463 */           int imageOffset = srcPixelOffset;
/* 464 */           float maxval = -3.4028235E38F;
/* 465 */           for (int v = 0; v < wp; v++) {
/* 466 */             float val = srcData[imageOffset];
/* 467 */             imageOffset += srcPixelStride;
/* 468 */             maxval = (val > maxval) ? val : maxval;
/*     */           } 
/* 470 */           tmpBuffer[revolver + i] = maxval;
/* 472 */           maxval = -3.4028235E38F;
/*     */           int b;
/* 473 */           for (b = i; b < tmpBufferSize; b += dwidth) {
/* 474 */             float val = tmpBuffer[b];
/* 475 */             maxval = (val > maxval) ? val : maxval;
/*     */           } 
/* 478 */           dstData[dstPixelOffset] = maxval;
/* 479 */           srcPixelOffset += srcPixelStride;
/* 480 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/* 482 */         revolver += dwidth;
/* 483 */         if (revolver == tmpBufferSize)
/* 484 */           revolver = 0; 
/* 486 */         srcScanlineOffset += srcScanlineStride;
/* 487 */         dstScanlineOffset += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void doubleLoop(RasterAccessor src, RasterAccessor dst, int filterSize) {
/* 495 */     int dwidth = dst.getWidth();
/* 496 */     int dheight = dst.getHeight();
/* 497 */     int dnumBands = dst.getNumBands();
/* 499 */     double[][] dstDataArrays = dst.getDoubleDataArrays();
/* 500 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 501 */     int dstPixelStride = dst.getPixelStride();
/* 502 */     int dstScanlineStride = dst.getScanlineStride();
/* 504 */     double[][] srcDataArrays = src.getDoubleDataArrays();
/* 505 */     int[] srcBandOffsets = src.getBandOffsets();
/* 506 */     int srcPixelStride = src.getPixelStride();
/* 507 */     int srcScanlineStride = src.getScanlineStride();
/* 509 */     double[] maxValues = new double[filterSize];
/* 511 */     int wp = filterSize;
/* 512 */     double[] tmpBuffer = new double[filterSize * dwidth];
/* 513 */     int tmpBufferSize = filterSize * dwidth;
/* 515 */     for (int k = 0; k < dnumBands; k++) {
/* 516 */       double[] dstData = dstDataArrays[k];
/* 517 */       double[] srcData = srcDataArrays[k];
/* 518 */       int srcScanlineOffset = srcBandOffsets[k];
/* 519 */       int dstScanlineOffset = dstBandOffsets[k];
/* 521 */       int revolver = 0;
/*     */       int j;
/* 522 */       for (j = 0; j < filterSize - 1; j++) {
/* 523 */         int srcPixelOffset = srcScanlineOffset;
/* 525 */         for (int i = 0; i < dwidth; i++) {
/* 526 */           int imageOffset = srcPixelOffset;
/* 527 */           double maxval = -1.7976931348623157E308D;
/* 528 */           for (int v = 0; v < wp; v++) {
/* 529 */             double val = srcData[imageOffset];
/* 530 */             imageOffset += srcPixelStride;
/* 531 */             maxval = (val > maxval) ? val : maxval;
/*     */           } 
/* 533 */           tmpBuffer[revolver + i] = maxval;
/* 534 */           srcPixelOffset += srcPixelStride;
/*     */         } 
/* 536 */         revolver += dwidth;
/* 537 */         srcScanlineOffset += srcScanlineStride;
/*     */       } 
/* 543 */       for (j = 0; j < dheight; j++) {
/* 544 */         int srcPixelOffset = srcScanlineOffset;
/* 545 */         int dstPixelOffset = dstScanlineOffset;
/* 547 */         for (int i = 0; i < dwidth; i++) {
/* 548 */           int imageOffset = srcPixelOffset;
/* 549 */           double maxval = -1.7976931348623157E308D;
/* 550 */           for (int v = 0; v < wp; v++) {
/* 551 */             double val = srcData[imageOffset];
/* 552 */             imageOffset += srcPixelStride;
/* 553 */             maxval = (val > maxval) ? val : maxval;
/*     */           } 
/* 555 */           tmpBuffer[revolver + i] = maxval;
/* 557 */           maxval = -1.7976931348623157E308D;
/*     */           int b;
/* 558 */           for (b = i; b < tmpBufferSize; b += dwidth) {
/* 559 */             double val = tmpBuffer[b];
/* 560 */             maxval = (val > maxval) ? val : maxval;
/*     */           } 
/* 563 */           dstData[dstPixelOffset] = maxval;
/* 564 */           srcPixelOffset += srcPixelStride;
/* 565 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/* 567 */         revolver += dwidth;
/* 568 */         if (revolver == tmpBufferSize)
/* 569 */           revolver = 0; 
/* 571 */         srcScanlineOffset += srcScanlineStride;
/* 572 */         dstScanlineOffset += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\MaxFilterSeparableOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */