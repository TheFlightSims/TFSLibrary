/*     */ package com.sun.media.jai.opimage;
/*     */ 
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.util.Map;
/*     */ import javax.media.jai.BorderExtender;
/*     */ import javax.media.jai.ImageLayout;
/*     */ import javax.media.jai.RasterAccessor;
/*     */ import javax.media.jai.operator.MedianFilterDescriptor;
/*     */ 
/*     */ final class MedianFilterSeparableOpImage extends MedianFilterOpImage {
/*     */   public MedianFilterSeparableOpImage(RenderedImage source, BorderExtender extender, Map config, ImageLayout layout, int maskSize) {
/*  55 */     super(source, extender, config, layout, MedianFilterDescriptor.MEDIAN_MASK_SQUARE, maskSize);
/*     */   }
/*     */   
/*     */   protected void byteLoop(RasterAccessor src, RasterAccessor dst, int filterSize) {
/*  66 */     int dwidth = dst.getWidth();
/*  67 */     int dheight = dst.getHeight();
/*  68 */     int dnumBands = dst.getNumBands();
/*  70 */     byte[][] dstDataArrays = dst.getByteDataArrays();
/*  71 */     int[] dstBandOffsets = dst.getBandOffsets();
/*  72 */     int dstPixelStride = dst.getPixelStride();
/*  73 */     int dstScanlineStride = dst.getScanlineStride();
/*  75 */     byte[][] srcDataArrays = src.getByteDataArrays();
/*  76 */     int[] srcBandOffsets = src.getBandOffsets();
/*  77 */     int srcPixelStride = src.getPixelStride();
/*  78 */     int srcScanlineStride = src.getScanlineStride();
/*  80 */     int[] medianValues = new int[filterSize];
/*  81 */     int[] tmpValues = new int[filterSize];
/*  82 */     int wp = filterSize;
/*  83 */     int[] tmpBuffer = new int[filterSize * dwidth];
/*  84 */     int tmpBufferSize = filterSize * dwidth;
/*  86 */     for (int k = 0; k < dnumBands; k++) {
/*  87 */       byte[] dstData = dstDataArrays[k];
/*  88 */       byte[] srcData = srcDataArrays[k];
/*  89 */       int srcScanlineOffset = srcBandOffsets[k];
/*  90 */       int dstScanlineOffset = dstBandOffsets[k];
/*  92 */       int revolver = 0;
/*     */       int j;
/*  93 */       for (j = 0; j < filterSize - 1; j++) {
/*  94 */         int srcPixelOffset = srcScanlineOffset;
/*  96 */         for (int i = 0; i < dwidth; i++) {
/*  97 */           int imageOffset = srcPixelOffset;
/*  98 */           for (int v = 0; v < wp; v++) {
/*  99 */             tmpValues[v] = srcData[imageOffset] & 0xFF;
/* 100 */             imageOffset += srcPixelStride;
/*     */           } 
/* 102 */           tmpBuffer[revolver + i] = medianFilter(tmpValues);
/* 103 */           srcPixelOffset += srcPixelStride;
/*     */         } 
/* 105 */         revolver += dwidth;
/* 106 */         srcScanlineOffset += srcScanlineStride;
/*     */       } 
/* 112 */       for (j = 0; j < dheight; j++) {
/* 113 */         int srcPixelOffset = srcScanlineOffset;
/* 114 */         int dstPixelOffset = dstScanlineOffset;
/* 116 */         for (int i = 0; i < dwidth; i++) {
/* 117 */           int imageOffset = srcPixelOffset;
/* 118 */           for (int v = 0; v < wp; v++) {
/* 119 */             tmpValues[v] = srcData[imageOffset] & 0xFF;
/* 120 */             imageOffset += srcPixelStride;
/*     */           } 
/* 122 */           tmpBuffer[revolver + i] = medianFilter(tmpValues);
/* 124 */           int a = 0;
/*     */           int b;
/* 125 */           for (b = i; b < tmpBufferSize; b += dwidth)
/* 126 */             medianValues[a++] = tmpBuffer[b]; 
/* 128 */           int val = medianFilter(medianValues);
/* 130 */           dstData[dstPixelOffset] = (byte)val;
/* 131 */           srcPixelOffset += srcPixelStride;
/* 132 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/* 135 */         revolver += dwidth;
/* 136 */         if (revolver == tmpBufferSize)
/* 137 */           revolver = 0; 
/* 140 */         srcScanlineOffset += srcScanlineStride;
/* 141 */         dstScanlineOffset += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void shortLoop(RasterAccessor src, RasterAccessor dst, int filterSize) {
/* 149 */     int dwidth = dst.getWidth();
/* 150 */     int dheight = dst.getHeight();
/* 151 */     int dnumBands = dst.getNumBands();
/* 153 */     short[][] dstDataArrays = dst.getShortDataArrays();
/* 154 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 155 */     int dstPixelStride = dst.getPixelStride();
/* 156 */     int dstScanlineStride = dst.getScanlineStride();
/* 158 */     short[][] srcDataArrays = src.getShortDataArrays();
/* 159 */     int[] srcBandOffsets = src.getBandOffsets();
/* 160 */     int srcPixelStride = src.getPixelStride();
/* 161 */     int srcScanlineStride = src.getScanlineStride();
/* 163 */     int[] medianValues = new int[filterSize];
/* 164 */     int[] tmpValues = new int[filterSize];
/* 165 */     int wp = filterSize;
/* 166 */     int[] tmpBuffer = new int[filterSize * dwidth];
/* 167 */     int tmpBufferSize = filterSize * dwidth;
/* 169 */     for (int k = 0; k < dnumBands; k++) {
/* 170 */       short[] dstData = dstDataArrays[k];
/* 171 */       short[] srcData = srcDataArrays[k];
/* 172 */       int srcScanlineOffset = srcBandOffsets[k];
/* 173 */       int dstScanlineOffset = dstBandOffsets[k];
/* 175 */       int revolver = 0;
/*     */       int j;
/* 176 */       for (j = 0; j < filterSize - 1; j++) {
/* 177 */         int srcPixelOffset = srcScanlineOffset;
/* 179 */         for (int i = 0; i < dwidth; i++) {
/* 180 */           int imageOffset = srcPixelOffset;
/* 181 */           for (int v = 0; v < wp; v++) {
/* 182 */             tmpValues[v] = srcData[imageOffset];
/* 183 */             imageOffset += srcPixelStride;
/*     */           } 
/* 185 */           tmpBuffer[revolver + i] = medianFilter(tmpValues);
/* 186 */           srcPixelOffset += srcPixelStride;
/*     */         } 
/* 188 */         revolver += dwidth;
/* 189 */         srcScanlineOffset += srcScanlineStride;
/*     */       } 
/* 195 */       for (j = 0; j < dheight; j++) {
/* 196 */         int srcPixelOffset = srcScanlineOffset;
/* 197 */         int dstPixelOffset = dstScanlineOffset;
/* 199 */         for (int i = 0; i < dwidth; i++) {
/* 200 */           int imageOffset = srcPixelOffset;
/* 201 */           for (int v = 0; v < wp; v++) {
/* 202 */             tmpValues[v] = srcData[imageOffset];
/* 203 */             imageOffset += srcPixelStride;
/*     */           } 
/* 205 */           tmpBuffer[revolver + i] = medianFilter(tmpValues);
/* 207 */           int a = 0;
/*     */           int b;
/* 208 */           for (b = i; b < tmpBufferSize; b += dwidth)
/* 209 */             medianValues[a++] = tmpBuffer[b]; 
/* 211 */           int val = medianFilter(medianValues);
/* 213 */           dstData[dstPixelOffset] = (short)val;
/* 214 */           srcPixelOffset += srcPixelStride;
/* 215 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/* 217 */         revolver += dwidth;
/* 218 */         if (revolver == tmpBufferSize)
/* 219 */           revolver = 0; 
/* 221 */         srcScanlineOffset += srcScanlineStride;
/* 222 */         dstScanlineOffset += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void ushortLoop(RasterAccessor src, RasterAccessor dst, int filterSize) {
/* 232 */     int dwidth = dst.getWidth();
/* 233 */     int dheight = dst.getHeight();
/* 234 */     int dnumBands = dst.getNumBands();
/* 236 */     short[][] dstDataArrays = dst.getShortDataArrays();
/* 237 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 238 */     int dstPixelStride = dst.getPixelStride();
/* 239 */     int dstScanlineStride = dst.getScanlineStride();
/* 241 */     short[][] srcDataArrays = src.getShortDataArrays();
/* 242 */     int[] srcBandOffsets = src.getBandOffsets();
/* 243 */     int srcPixelStride = src.getPixelStride();
/* 244 */     int srcScanlineStride = src.getScanlineStride();
/* 246 */     int[] medianValues = new int[filterSize];
/* 247 */     int[] tmpValues = new int[filterSize];
/* 248 */     int wp = filterSize;
/* 249 */     int[] tmpBuffer = new int[filterSize * dwidth];
/* 250 */     int tmpBufferSize = filterSize * dwidth;
/* 252 */     for (int k = 0; k < dnumBands; k++) {
/* 253 */       short[] dstData = dstDataArrays[k];
/* 254 */       short[] srcData = srcDataArrays[k];
/* 255 */       int srcScanlineOffset = srcBandOffsets[k];
/* 256 */       int dstScanlineOffset = dstBandOffsets[k];
/* 258 */       int revolver = 0;
/*     */       int j;
/* 259 */       for (j = 0; j < filterSize - 1; j++) {
/* 260 */         int srcPixelOffset = srcScanlineOffset;
/* 262 */         for (int i = 0; i < dwidth; i++) {
/* 263 */           int imageOffset = srcPixelOffset;
/* 264 */           for (int v = 0; v < wp; v++) {
/* 265 */             tmpValues[v] = srcData[imageOffset] & 0xFFF;
/* 266 */             imageOffset += srcPixelStride;
/*     */           } 
/* 268 */           tmpBuffer[revolver + i] = medianFilter(tmpValues);
/* 269 */           srcPixelOffset += srcPixelStride;
/*     */         } 
/* 271 */         revolver += dwidth;
/* 272 */         srcScanlineOffset += srcScanlineStride;
/*     */       } 
/* 278 */       for (j = 0; j < dheight; j++) {
/* 279 */         int srcPixelOffset = srcScanlineOffset;
/* 280 */         int dstPixelOffset = dstScanlineOffset;
/* 282 */         for (int i = 0; i < dwidth; i++) {
/* 283 */           int imageOffset = srcPixelOffset;
/* 284 */           for (int v = 0; v < wp; v++) {
/* 285 */             tmpValues[v] = srcData[imageOffset] & 0xFFFF;
/* 286 */             imageOffset += srcPixelStride;
/*     */           } 
/* 288 */           tmpBuffer[revolver + i] = medianFilter(tmpValues);
/* 290 */           int a = 0;
/*     */           int b;
/* 291 */           for (b = i; b < tmpBufferSize; b += dwidth)
/* 292 */             medianValues[a++] = tmpBuffer[b]; 
/* 294 */           int val = medianFilter(medianValues);
/* 296 */           dstData[dstPixelOffset] = (short)val;
/* 297 */           srcPixelOffset += srcPixelStride;
/* 298 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/* 300 */         revolver += dwidth;
/* 301 */         if (revolver == tmpBufferSize)
/* 302 */           revolver = 0; 
/* 304 */         srcScanlineOffset += srcScanlineStride;
/* 305 */         dstScanlineOffset += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void intLoop(RasterAccessor src, RasterAccessor dst, int filterSize) {
/* 313 */     int dwidth = dst.getWidth();
/* 314 */     int dheight = dst.getHeight();
/* 315 */     int dnumBands = dst.getNumBands();
/* 317 */     int[][] dstDataArrays = dst.getIntDataArrays();
/* 318 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 319 */     int dstPixelStride = dst.getPixelStride();
/* 320 */     int dstScanlineStride = dst.getScanlineStride();
/* 322 */     int[][] srcDataArrays = src.getIntDataArrays();
/* 323 */     int[] srcBandOffsets = src.getBandOffsets();
/* 324 */     int srcPixelStride = src.getPixelStride();
/* 325 */     int srcScanlineStride = src.getScanlineStride();
/* 327 */     int[] medianValues = new int[filterSize];
/* 328 */     int[] tmpValues = new int[filterSize];
/* 329 */     int wp = filterSize;
/* 330 */     int[] tmpBuffer = new int[filterSize * dwidth];
/* 331 */     int tmpBufferSize = filterSize * dwidth;
/* 333 */     for (int k = 0; k < dnumBands; k++) {
/* 334 */       int[] dstData = dstDataArrays[k];
/* 335 */       int[] srcData = srcDataArrays[k];
/* 336 */       int srcScanlineOffset = srcBandOffsets[k];
/* 337 */       int dstScanlineOffset = dstBandOffsets[k];
/* 339 */       int revolver = 0;
/*     */       int j;
/* 340 */       for (j = 0; j < filterSize - 1; j++) {
/* 341 */         int srcPixelOffset = srcScanlineOffset;
/* 343 */         for (int i = 0; i < dwidth; i++) {
/* 344 */           int imageOffset = srcPixelOffset;
/* 345 */           for (int v = 0; v < wp; v++) {
/* 346 */             tmpValues[v] = srcData[imageOffset];
/* 347 */             imageOffset += srcPixelStride;
/*     */           } 
/* 349 */           tmpBuffer[revolver + i] = medianFilter(tmpValues);
/* 350 */           srcPixelOffset += srcPixelStride;
/*     */         } 
/* 352 */         revolver += dwidth;
/* 353 */         srcScanlineOffset += srcScanlineStride;
/*     */       } 
/* 359 */       for (j = 0; j < dheight; j++) {
/* 360 */         int srcPixelOffset = srcScanlineOffset;
/* 361 */         int dstPixelOffset = dstScanlineOffset;
/* 363 */         for (int i = 0; i < dwidth; i++) {
/* 364 */           int imageOffset = srcPixelOffset;
/* 365 */           for (int v = 0; v < wp; v++) {
/* 366 */             tmpValues[v] = srcData[imageOffset];
/* 367 */             imageOffset += srcPixelStride;
/*     */           } 
/* 369 */           tmpBuffer[revolver + i] = medianFilter(tmpValues);
/* 371 */           int a = 0;
/*     */           int b;
/* 372 */           for (b = i; b < tmpBufferSize; b += dwidth)
/* 373 */             medianValues[a++] = tmpBuffer[b]; 
/* 375 */           int val = medianFilter(medianValues);
/* 377 */           dstData[dstPixelOffset] = val;
/* 378 */           srcPixelOffset += srcPixelStride;
/* 379 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/* 381 */         revolver += dwidth;
/* 382 */         if (revolver == tmpBufferSize)
/* 383 */           revolver = 0; 
/* 385 */         srcScanlineOffset += srcScanlineStride;
/* 386 */         dstScanlineOffset += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void floatLoop(RasterAccessor src, RasterAccessor dst, int filterSize) {
/* 395 */     int dwidth = dst.getWidth();
/* 396 */     int dheight = dst.getHeight();
/* 397 */     int dnumBands = dst.getNumBands();
/* 399 */     float[][] dstDataArrays = dst.getFloatDataArrays();
/* 400 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 401 */     int dstPixelStride = dst.getPixelStride();
/* 402 */     int dstScanlineStride = dst.getScanlineStride();
/* 404 */     float[][] srcDataArrays = src.getFloatDataArrays();
/* 405 */     int[] srcBandOffsets = src.getBandOffsets();
/* 406 */     int srcPixelStride = src.getPixelStride();
/* 407 */     int srcScanlineStride = src.getScanlineStride();
/* 409 */     float[] medianValues = new float[filterSize];
/* 410 */     float[] tmpValues = new float[filterSize];
/* 411 */     int wp = filterSize;
/* 412 */     float[] tmpBuffer = new float[filterSize * dwidth];
/* 413 */     int tmpBufferSize = filterSize * dwidth;
/* 415 */     for (int k = 0; k < dnumBands; k++) {
/* 416 */       float[] dstData = dstDataArrays[k];
/* 417 */       float[] srcData = srcDataArrays[k];
/* 418 */       int srcScanlineOffset = srcBandOffsets[k];
/* 419 */       int dstScanlineOffset = dstBandOffsets[k];
/* 421 */       int revolver = 0;
/*     */       int j;
/* 422 */       for (j = 0; j < filterSize - 1; j++) {
/* 423 */         int srcPixelOffset = srcScanlineOffset;
/* 425 */         for (int i = 0; i < dwidth; i++) {
/* 426 */           int imageOffset = srcPixelOffset;
/* 427 */           for (int v = 0; v < wp; v++) {
/* 428 */             tmpValues[v] = srcData[imageOffset];
/* 429 */             imageOffset += srcPixelStride;
/*     */           } 
/* 431 */           tmpBuffer[revolver + i] = medianFilterFloat(tmpValues);
/* 432 */           srcPixelOffset += srcPixelStride;
/*     */         } 
/* 434 */         revolver += dwidth;
/* 435 */         srcScanlineOffset += srcScanlineStride;
/*     */       } 
/* 441 */       for (j = 0; j < dheight; j++) {
/* 442 */         int srcPixelOffset = srcScanlineOffset;
/* 443 */         int dstPixelOffset = dstScanlineOffset;
/* 445 */         for (int i = 0; i < dwidth; i++) {
/* 446 */           int imageOffset = srcPixelOffset;
/* 447 */           for (int v = 0; v < wp; v++) {
/* 448 */             tmpValues[v] = srcData[imageOffset];
/* 449 */             imageOffset += srcPixelStride;
/*     */           } 
/* 451 */           tmpBuffer[revolver + i] = medianFilterFloat(tmpValues);
/* 453 */           int a = 0;
/*     */           int b;
/* 454 */           for (b = i; b < tmpBufferSize; b += dwidth)
/* 455 */             medianValues[a++] = tmpBuffer[b]; 
/* 457 */           float val = medianFilterFloat(medianValues);
/* 459 */           dstData[dstPixelOffset] = val;
/* 460 */           srcPixelOffset += srcPixelStride;
/* 461 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/* 463 */         revolver += dwidth;
/* 464 */         if (revolver == tmpBufferSize)
/* 465 */           revolver = 0; 
/* 467 */         srcScanlineOffset += srcScanlineStride;
/* 468 */         dstScanlineOffset += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void doubleLoop(RasterAccessor src, RasterAccessor dst, int filterSize) {
/* 476 */     int dwidth = dst.getWidth();
/* 477 */     int dheight = dst.getHeight();
/* 478 */     int dnumBands = dst.getNumBands();
/* 480 */     double[][] dstDataArrays = dst.getDoubleDataArrays();
/* 481 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 482 */     int dstPixelStride = dst.getPixelStride();
/* 483 */     int dstScanlineStride = dst.getScanlineStride();
/* 485 */     double[][] srcDataArrays = src.getDoubleDataArrays();
/* 486 */     int[] srcBandOffsets = src.getBandOffsets();
/* 487 */     int srcPixelStride = src.getPixelStride();
/* 488 */     int srcScanlineStride = src.getScanlineStride();
/* 490 */     double[] medianValues = new double[filterSize];
/* 491 */     double[] tmpValues = new double[filterSize];
/* 492 */     int wp = filterSize;
/* 493 */     double[] tmpBuffer = new double[filterSize * dwidth];
/* 494 */     int tmpBufferSize = filterSize * dwidth;
/* 496 */     for (int k = 0; k < dnumBands; k++) {
/* 497 */       double[] dstData = dstDataArrays[k];
/* 498 */       double[] srcData = srcDataArrays[k];
/* 499 */       int srcScanlineOffset = srcBandOffsets[k];
/* 500 */       int dstScanlineOffset = dstBandOffsets[k];
/* 502 */       int revolver = 0;
/*     */       int j;
/* 503 */       for (j = 0; j < filterSize - 1; j++) {
/* 504 */         int srcPixelOffset = srcScanlineOffset;
/* 506 */         for (int i = 0; i < dwidth; i++) {
/* 507 */           int imageOffset = srcPixelOffset;
/* 508 */           for (int v = 0; v < wp; v++) {
/* 509 */             tmpValues[v] = srcData[imageOffset];
/* 510 */             imageOffset += srcPixelStride;
/*     */           } 
/* 512 */           tmpBuffer[revolver + i] = medianFilterDouble(tmpValues);
/* 513 */           srcPixelOffset += srcPixelStride;
/*     */         } 
/* 515 */         revolver += dwidth;
/* 516 */         srcScanlineOffset += srcScanlineStride;
/*     */       } 
/* 522 */       for (j = 0; j < dheight; j++) {
/* 523 */         int srcPixelOffset = srcScanlineOffset;
/* 524 */         int dstPixelOffset = dstScanlineOffset;
/* 526 */         for (int i = 0; i < dwidth; i++) {
/* 527 */           int imageOffset = srcPixelOffset;
/* 528 */           for (int v = 0; v < wp; v++) {
/* 529 */             tmpValues[v] = srcData[imageOffset];
/* 530 */             imageOffset += srcPixelStride;
/*     */           } 
/* 532 */           tmpBuffer[revolver + i] = medianFilterDouble(tmpValues);
/* 534 */           int a = 0;
/*     */           int b;
/* 535 */           for (b = i; b < tmpBufferSize; b += dwidth)
/* 536 */             medianValues[a++] = tmpBuffer[b]; 
/* 538 */           double val = medianFilterDouble(medianValues);
/* 540 */           dstData[dstPixelOffset] = val;
/* 541 */           srcPixelOffset += srcPixelStride;
/* 542 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/* 544 */         revolver += dwidth;
/* 545 */         if (revolver == tmpBufferSize)
/* 546 */           revolver = 0; 
/* 548 */         srcScanlineOffset += srcScanlineStride;
/* 549 */         dstScanlineOffset += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\MedianFilterSeparableOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */