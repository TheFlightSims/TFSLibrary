/*     */ package com.sun.media.jai.opimage;
/*     */ 
/*     */ import com.sun.media.jai.util.ImageUtil;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.util.Map;
/*     */ import javax.media.jai.ColormapOpImage;
/*     */ import javax.media.jai.ImageLayout;
/*     */ import javax.media.jai.LookupTableJAI;
/*     */ import javax.media.jai.RasterAccessor;
/*     */ import javax.media.jai.RasterFormatTag;
/*     */ 
/*     */ final class PiecewiseOpImage extends ColormapOpImage {
/*     */   private float[][] abscissas;
/*     */   
/*     */   private float[][] slopes;
/*     */   
/*     */   private float[][] intercepts;
/*     */   
/*     */   private float[] minOrdinates;
/*     */   
/*     */   private float[] maxOrdinates;
/*     */   
/*     */   private boolean isByteData = false;
/*     */   
/*     */   private LookupTableJAI lut;
/*     */   
/*     */   private static float binarySearch(float[] x, float minValue, float maxValue, float[] a, float[] b, float value) {
/*  80 */     int highIndex = x.length - 1;
/*  82 */     if (value <= x[0])
/*  83 */       return minValue; 
/*  84 */     if (value >= x[highIndex])
/*  85 */       return maxValue; 
/*  88 */     int lowIndex = 0;
/*  89 */     int deltaIndex = highIndex - lowIndex;
/*  91 */     while (deltaIndex > 1) {
/*  92 */       int meanIndex = lowIndex + deltaIndex / 2;
/*  93 */       if (value >= x[meanIndex]) {
/*  94 */         lowIndex = meanIndex;
/*     */       } else {
/*  96 */         highIndex = meanIndex;
/*     */       } 
/*  98 */       deltaIndex = highIndex - lowIndex;
/*     */     } 
/* 101 */     return a[lowIndex] * value + b[lowIndex];
/*     */   }
/*     */   
/*     */   public PiecewiseOpImage(RenderedImage source, Map config, ImageLayout layout, float[][][] breakpoints) {
/* 119 */     super(source, layout, config, true);
/* 123 */     int numBands = this.sampleModel.getNumBands();
/* 126 */     initFields(numBands, breakpoints);
/* 129 */     this.isByteData = (this.sampleModel.getTransferType() == 0);
/* 132 */     if (this.isByteData) {
/* 134 */       createLUT();
/* 137 */       unsetFields();
/*     */     } 
/* 141 */     permitInPlaceOperation();
/* 144 */     initializeColormapOperation();
/*     */   }
/*     */   
/*     */   protected void transformColormap(byte[][] colormap) {
/* 152 */     byte[][] byteTable = this.lut.getByteData();
/* 154 */     for (int b = 0; b < 3; b++) {
/* 155 */       byte[] map = colormap[b];
/* 156 */       byte[] luTable = byteTable[(b >= byteTable.length) ? 0 : b];
/* 157 */       int mapSize = map.length;
/* 159 */       for (int i = 0; i < mapSize; i++)
/* 160 */         map[i] = luTable[map[i] & 0xFF]; 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void initFields(int numBands, float[][][] breakpoints) {
/* 174 */     this.abscissas = new float[numBands][];
/* 175 */     this.slopes = new float[numBands][];
/* 176 */     this.intercepts = new float[numBands][];
/* 177 */     this.minOrdinates = new float[numBands];
/* 178 */     this.maxOrdinates = new float[numBands];
/* 180 */     for (int band = 0; band < numBands; band++) {
/* 181 */       this.abscissas[band] = (breakpoints.length == 1) ? breakpoints[0][0] : breakpoints[band][0];
/* 183 */       int maxIndex = (this.abscissas[band]).length - 1;
/* 185 */       this.minOrdinates[band] = (breakpoints.length == 1) ? breakpoints[0][1][0] : breakpoints[band][1][0];
/* 187 */       this.maxOrdinates[band] = (breakpoints.length == 1) ? breakpoints[0][1][maxIndex] : breakpoints[band][1][maxIndex];
/* 190 */       this.slopes[band] = new float[maxIndex];
/* 191 */       this.intercepts[band] = new float[maxIndex];
/* 193 */       float[] x = this.abscissas[band];
/* 194 */       float[] y = (breakpoints.length == 1) ? breakpoints[0][1] : breakpoints[band][1];
/* 196 */       float[] a = this.slopes[band];
/* 197 */       float[] b = this.intercepts[band];
/* 198 */       for (int i1 = 0; i1 < maxIndex; i1++) {
/* 199 */         int i2 = i1 + 1;
/* 200 */         a[i1] = (y[i2] - y[i1]) / (x[i2] - x[i1]);
/* 201 */         b[i1] = y[i1] - x[i1] * a[i1];
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void unsetFields() {
/* 212 */     this.abscissas = (float[][])null;
/* 213 */     this.slopes = (float[][])null;
/* 214 */     this.intercepts = (float[][])null;
/* 215 */     this.minOrdinates = null;
/* 216 */     this.maxOrdinates = null;
/*     */   }
/*     */   
/*     */   private void createLUT() {
/* 224 */     int numBands = this.abscissas.length;
/* 225 */     byte[][] data = new byte[numBands][];
/* 228 */     for (int band = 0; band < numBands; band++) {
/* 230 */       data[band] = new byte[256];
/* 233 */       byte[] table = data[band];
/* 234 */       float[] x = this.abscissas[band];
/* 235 */       float[] a = this.slopes[band];
/* 236 */       float[] b = this.intercepts[band];
/* 237 */       float yL = this.minOrdinates[band];
/* 238 */       float yH = this.maxOrdinates[band];
/* 241 */       for (int value = 0; value < 256; value++)
/* 242 */         table[value] = ImageUtil.clampRoundByte(binarySearch(x, yL, yH, a, b, value)); 
/*     */     } 
/* 248 */     this.lut = new LookupTableJAI(data);
/*     */   }
/*     */   
/*     */   protected void computeRect(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/* 263 */     RasterFormatTag[] formatTags = getFormatTags();
/* 265 */     if (this.isByteData) {
/* 266 */       computeRectByte(sources, dest, destRect);
/*     */     } else {
/* 268 */       RasterAccessor dst = new RasterAccessor(dest, destRect, formatTags[1], getColorModel());
/* 271 */       RasterAccessor src = new RasterAccessor(sources[0], destRect, formatTags[0], getSource(0).getColorModel());
/* 275 */       switch (dst.getDataType()) {
/*     */         case 1:
/* 277 */           computeRectUShort(src, dst);
/*     */           break;
/*     */         case 2:
/* 280 */           computeRectShort(src, dst);
/*     */           break;
/*     */         case 3:
/* 283 */           computeRectInt(src, dst);
/*     */           break;
/*     */         case 4:
/* 286 */           computeRectFloat(src, dst);
/*     */           break;
/*     */         case 5:
/* 289 */           computeRectDouble(src, dst);
/*     */           break;
/*     */       } 
/* 293 */       dst.copyDataToRaster();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeRectByte(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/* 300 */     this.lut.lookup(sources[0], dest, destRect);
/*     */   }
/*     */   
/*     */   private void computeRectUShort(RasterAccessor src, RasterAccessor dst) {
/* 305 */     int dstWidth = dst.getWidth();
/* 306 */     int dstHeight = dst.getHeight();
/* 307 */     int dstBands = dst.getNumBands();
/* 309 */     int dstLineStride = dst.getScanlineStride();
/* 310 */     int dstPixelStride = dst.getPixelStride();
/* 311 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 312 */     short[][] dstData = dst.getShortDataArrays();
/* 314 */     int srcLineStride = src.getScanlineStride();
/* 315 */     int srcPixelStride = src.getPixelStride();
/* 316 */     int[] srcBandOffsets = src.getBandOffsets();
/* 317 */     short[][] srcData = src.getShortDataArrays();
/* 319 */     for (int b = 0; b < dstBands; b++) {
/* 320 */       short[] d = dstData[b];
/* 321 */       short[] s = srcData[b];
/* 323 */       int dstLineOffset = dstBandOffsets[b];
/* 324 */       int srcLineOffset = srcBandOffsets[b];
/* 327 */       float[] x = this.abscissas[b];
/* 328 */       float[] gain = this.slopes[b];
/* 329 */       float[] bias = this.intercepts[b];
/* 330 */       float yL = this.minOrdinates[b];
/* 331 */       float yH = this.maxOrdinates[b];
/* 333 */       for (int h = 0; h < dstHeight; h++) {
/* 334 */         int dstPixelOffset = dstLineOffset;
/* 335 */         int srcPixelOffset = srcLineOffset;
/* 337 */         dstLineOffset += dstLineStride;
/* 338 */         srcLineOffset += srcLineStride;
/* 340 */         for (int w = 0; w < dstWidth; w++) {
/* 341 */           d[dstPixelOffset] = ImageUtil.clampRoundUShort(binarySearch(x, yL, yH, gain, bias, (s[srcPixelOffset] & 0xFFFF)));
/* 346 */           dstPixelOffset += dstPixelStride;
/* 347 */           srcPixelOffset += srcPixelStride;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeRectShort(RasterAccessor src, RasterAccessor dst) {
/* 355 */     int dstWidth = dst.getWidth();
/* 356 */     int dstHeight = dst.getHeight();
/* 357 */     int dstBands = dst.getNumBands();
/* 359 */     int dstLineStride = dst.getScanlineStride();
/* 360 */     int dstPixelStride = dst.getPixelStride();
/* 361 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 362 */     short[][] dstData = dst.getShortDataArrays();
/* 364 */     int srcLineStride = src.getScanlineStride();
/* 365 */     int srcPixelStride = src.getPixelStride();
/* 366 */     int[] srcBandOffsets = src.getBandOffsets();
/* 367 */     short[][] srcData = src.getShortDataArrays();
/* 369 */     for (int b = 0; b < dstBands; b++) {
/* 370 */       short[] d = dstData[b];
/* 371 */       short[] s = srcData[b];
/* 373 */       int dstLineOffset = dstBandOffsets[b];
/* 374 */       int srcLineOffset = srcBandOffsets[b];
/* 377 */       float[] x = this.abscissas[b];
/* 378 */       float[] gain = this.slopes[b];
/* 379 */       float[] bias = this.intercepts[b];
/* 380 */       float yL = this.minOrdinates[b];
/* 381 */       float yH = this.maxOrdinates[b];
/* 383 */       for (int h = 0; h < dstHeight; h++) {
/* 384 */         int dstPixelOffset = dstLineOffset;
/* 385 */         int srcPixelOffset = srcLineOffset;
/* 387 */         dstLineOffset += dstLineStride;
/* 388 */         srcLineOffset += srcLineStride;
/* 390 */         for (int w = 0; w < dstWidth; w++) {
/* 391 */           d[dstPixelOffset] = ImageUtil.clampRoundShort(binarySearch(x, yL, yH, gain, bias, s[srcPixelOffset]));
/* 395 */           dstPixelOffset += dstPixelStride;
/* 396 */           srcPixelOffset += srcPixelStride;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeRectInt(RasterAccessor src, RasterAccessor dst) {
/* 404 */     int dstWidth = dst.getWidth();
/* 405 */     int dstHeight = dst.getHeight();
/* 406 */     int dstBands = dst.getNumBands();
/* 408 */     int dstLineStride = dst.getScanlineStride();
/* 409 */     int dstPixelStride = dst.getPixelStride();
/* 410 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 411 */     int[][] dstData = dst.getIntDataArrays();
/* 413 */     int srcLineStride = src.getScanlineStride();
/* 414 */     int srcPixelStride = src.getPixelStride();
/* 415 */     int[] srcBandOffsets = src.getBandOffsets();
/* 416 */     int[][] srcData = src.getIntDataArrays();
/* 418 */     for (int b = 0; b < dstBands; b++) {
/* 419 */       int[] d = dstData[b];
/* 420 */       int[] s = srcData[b];
/* 422 */       int dstLineOffset = dstBandOffsets[b];
/* 423 */       int srcLineOffset = srcBandOffsets[b];
/* 426 */       float[] x = this.abscissas[b];
/* 427 */       float[] gain = this.slopes[b];
/* 428 */       float[] bias = this.intercepts[b];
/* 429 */       float yL = this.minOrdinates[b];
/* 430 */       float yH = this.maxOrdinates[b];
/* 432 */       for (int h = 0; h < dstHeight; h++) {
/* 433 */         int dstPixelOffset = dstLineOffset;
/* 434 */         int srcPixelOffset = srcLineOffset;
/* 436 */         dstLineOffset += dstLineStride;
/* 437 */         srcLineOffset += srcLineStride;
/* 439 */         for (int w = 0; w < dstWidth; w++) {
/* 440 */           d[dstPixelOffset] = ImageUtil.clampRoundInt(binarySearch(x, yL, yH, gain, bias, s[srcPixelOffset]));
/* 444 */           dstPixelOffset += dstPixelStride;
/* 445 */           srcPixelOffset += srcPixelStride;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeRectFloat(RasterAccessor src, RasterAccessor dst) {
/* 453 */     int dstWidth = dst.getWidth();
/* 454 */     int dstHeight = dst.getHeight();
/* 455 */     int dstBands = dst.getNumBands();
/* 457 */     int dstLineStride = dst.getScanlineStride();
/* 458 */     int dstPixelStride = dst.getPixelStride();
/* 459 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 460 */     float[][] dstData = dst.getFloatDataArrays();
/* 462 */     int srcLineStride = src.getScanlineStride();
/* 463 */     int srcPixelStride = src.getPixelStride();
/* 464 */     int[] srcBandOffsets = src.getBandOffsets();
/* 465 */     float[][] srcData = src.getFloatDataArrays();
/* 467 */     for (int b = 0; b < dstBands; b++) {
/* 468 */       float[] d = dstData[b];
/* 469 */       float[] s = srcData[b];
/* 471 */       int dstLineOffset = dstBandOffsets[b];
/* 472 */       int srcLineOffset = srcBandOffsets[b];
/* 475 */       float[] x = this.abscissas[b];
/* 476 */       float[] gain = this.slopes[b];
/* 477 */       float[] bias = this.intercepts[b];
/* 478 */       float yL = this.minOrdinates[b];
/* 479 */       float yH = this.maxOrdinates[b];
/* 481 */       for (int h = 0; h < dstHeight; h++) {
/* 482 */         int dstPixelOffset = dstLineOffset;
/* 483 */         int srcPixelOffset = srcLineOffset;
/* 485 */         dstLineOffset += dstLineStride;
/* 486 */         srcLineOffset += srcLineStride;
/* 488 */         for (int w = 0; w < dstWidth; w++) {
/* 489 */           d[dstPixelOffset] = binarySearch(x, yL, yH, gain, bias, s[srcPixelOffset]);
/* 493 */           dstPixelOffset += dstPixelStride;
/* 494 */           srcPixelOffset += srcPixelStride;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeRectDouble(RasterAccessor src, RasterAccessor dst) {
/* 502 */     int dstWidth = dst.getWidth();
/* 503 */     int dstHeight = dst.getHeight();
/* 504 */     int dstBands = dst.getNumBands();
/* 506 */     int dstLineStride = dst.getScanlineStride();
/* 507 */     int dstPixelStride = dst.getPixelStride();
/* 508 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 509 */     double[][] dstData = dst.getDoubleDataArrays();
/* 511 */     int srcLineStride = src.getScanlineStride();
/* 512 */     int srcPixelStride = src.getPixelStride();
/* 513 */     int[] srcBandOffsets = src.getBandOffsets();
/* 514 */     double[][] srcData = src.getDoubleDataArrays();
/* 516 */     for (int b = 0; b < dstBands; b++) {
/* 517 */       double[] d = dstData[b];
/* 518 */       double[] s = srcData[b];
/* 520 */       int dstLineOffset = dstBandOffsets[b];
/* 521 */       int srcLineOffset = srcBandOffsets[b];
/* 524 */       float[] x = this.abscissas[b];
/* 525 */       float[] gain = this.slopes[b];
/* 526 */       float[] bias = this.intercepts[b];
/* 527 */       float yL = this.minOrdinates[b];
/* 528 */       float yH = this.maxOrdinates[b];
/* 530 */       for (int h = 0; h < dstHeight; h++) {
/* 531 */         int dstPixelOffset = dstLineOffset;
/* 532 */         int srcPixelOffset = srcLineOffset;
/* 534 */         dstLineOffset += dstLineStride;
/* 535 */         srcLineOffset += srcLineStride;
/* 537 */         for (int w = 0; w < dstWidth; w++) {
/* 538 */           d[dstPixelOffset] = binarySearch(x, yL, yH, gain, bias, (float)s[srcPixelOffset]);
/* 542 */           dstPixelOffset += dstPixelStride;
/* 543 */           srcPixelOffset += srcPixelStride;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\PiecewiseOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */