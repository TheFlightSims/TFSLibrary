/*     */ package com.sun.media.jai.opimage;
/*     */ 
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.util.Map;
/*     */ import javax.media.jai.BorderExtender;
/*     */ import javax.media.jai.ImageLayout;
/*     */ import javax.media.jai.RasterAccessor;
/*     */ import javax.media.jai.operator.MedianFilterDescriptor;
/*     */ 
/*     */ final class MedianFilterXOpImage extends MedianFilterOpImage {
/*     */   public MedianFilterXOpImage(RenderedImage source, BorderExtender extender, Map config, ImageLayout layout, int maskSize) {
/*  53 */     super(source, extender, config, layout, MedianFilterDescriptor.MEDIAN_MASK_PLUS, maskSize);
/*     */   }
/*     */   
/*     */   protected void byteLoop(RasterAccessor src, RasterAccessor dst, int filterSize) {
/*  64 */     int dwidth = dst.getWidth();
/*  65 */     int dheight = dst.getHeight();
/*  66 */     int dnumBands = dst.getNumBands();
/*  68 */     byte[][] dstDataArrays = dst.getByteDataArrays();
/*  69 */     int[] dstBandOffsets = dst.getBandOffsets();
/*  70 */     int dstPixelStride = dst.getPixelStride();
/*  71 */     int dstScanlineStride = dst.getScanlineStride();
/*  73 */     byte[][] srcDataArrays = src.getByteDataArrays();
/*  74 */     int[] srcBandOffsets = src.getBandOffsets();
/*  75 */     int srcPixelStride = src.getPixelStride();
/*  76 */     int srcScanlineStride = src.getScanlineStride();
/*  78 */     int[] values = new int[filterSize * 2 - 1];
/*  79 */     int wp = filterSize;
/*  80 */     int offset = filterSize / 2;
/*  82 */     for (int k = 0; k < dnumBands; k++) {
/*  83 */       byte[] dstData = dstDataArrays[k];
/*  84 */       byte[] srcData = srcDataArrays[k];
/*  85 */       int srcScanlineOffset = srcBandOffsets[k];
/*  86 */       int dstScanlineOffset = dstBandOffsets[k];
/*  87 */       for (int j = 0; j < dheight; j++) {
/*  88 */         int srcPixelOffset = srcScanlineOffset;
/*  89 */         int dstPixelOffset = dstScanlineOffset;
/*  91 */         for (int i = 0; i < dwidth; i++) {
/*  92 */           int valueCount = 0;
/*  95 */           int imageOffset = srcPixelOffset;
/*  97 */           for (int u = 0; u < wp; u++) {
/*  98 */             values[valueCount++] = srcData[imageOffset] & 0xFF;
/* 100 */             imageOffset += srcScanlineStride + srcPixelStride;
/*     */           } 
/* 105 */           valueCount--;
/* 106 */           values[offset] = values[valueCount];
/* 109 */           imageOffset = srcPixelOffset + srcPixelStride * (filterSize - 1);
/* 112 */           for (int v = 0; v < wp; v++) {
/* 113 */             values[valueCount++] = srcData[imageOffset] & 0xFF;
/* 115 */             imageOffset += srcScanlineStride - srcPixelStride;
/*     */           } 
/* 118 */           int val = medianFilter(values);
/* 120 */           dstData[dstPixelOffset] = (byte)val;
/* 121 */           srcPixelOffset += srcPixelStride;
/* 122 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/* 124 */         srcScanlineOffset += srcScanlineStride;
/* 125 */         dstScanlineOffset += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void shortLoop(RasterAccessor src, RasterAccessor dst, int filterSize) {
/* 133 */     int dwidth = dst.getWidth();
/* 134 */     int dheight = dst.getHeight();
/* 135 */     int dnumBands = dst.getNumBands();
/* 137 */     short[][] dstDataArrays = dst.getShortDataArrays();
/* 138 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 139 */     int dstPixelStride = dst.getPixelStride();
/* 140 */     int dstScanlineStride = dst.getScanlineStride();
/* 142 */     short[][] srcDataArrays = src.getShortDataArrays();
/* 143 */     int[] srcBandOffsets = src.getBandOffsets();
/* 144 */     int srcPixelStride = src.getPixelStride();
/* 145 */     int srcScanlineStride = src.getScanlineStride();
/* 147 */     int[] values = new int[filterSize * 2 - 1];
/* 148 */     int wp = filterSize;
/* 149 */     int offset = filterSize / 2;
/* 151 */     for (int k = 0; k < dnumBands; k++) {
/* 152 */       short[] dstData = dstDataArrays[k];
/* 153 */       short[] srcData = srcDataArrays[k];
/* 154 */       int srcScanlineOffset = srcBandOffsets[k];
/* 155 */       int dstScanlineOffset = dstBandOffsets[k];
/* 156 */       for (int j = 0; j < dheight; j++) {
/* 157 */         int srcPixelOffset = srcScanlineOffset;
/* 158 */         int dstPixelOffset = dstScanlineOffset;
/* 160 */         for (int i = 0; i < dwidth; i++) {
/* 161 */           int valueCount = 0;
/* 164 */           int imageOffset = srcPixelOffset;
/* 166 */           for (int u = 0; u < wp; u++) {
/* 167 */             values[valueCount++] = srcData[imageOffset];
/* 169 */             imageOffset += srcScanlineStride + srcPixelStride;
/*     */           } 
/* 174 */           valueCount--;
/* 175 */           values[offset] = values[valueCount];
/* 178 */           imageOffset = srcPixelOffset + srcPixelStride * (filterSize - 1);
/* 181 */           for (int v = 0; v < wp; v++) {
/* 182 */             values[valueCount++] = srcData[imageOffset];
/* 184 */             imageOffset += srcScanlineStride - srcPixelStride;
/*     */           } 
/* 187 */           int val = medianFilter(values);
/* 189 */           dstData[dstPixelOffset] = (short)val;
/* 190 */           srcPixelOffset += srcPixelStride;
/* 191 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/* 193 */         srcScanlineOffset += srcScanlineStride;
/* 194 */         dstScanlineOffset += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void ushortLoop(RasterAccessor src, RasterAccessor dst, int filterSize) {
/* 202 */     int dwidth = dst.getWidth();
/* 203 */     int dheight = dst.getHeight();
/* 204 */     int dnumBands = dst.getNumBands();
/* 206 */     short[][] dstDataArrays = dst.getShortDataArrays();
/* 207 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 208 */     int dstPixelStride = dst.getPixelStride();
/* 209 */     int dstScanlineStride = dst.getScanlineStride();
/* 211 */     short[][] srcDataArrays = src.getShortDataArrays();
/* 212 */     int[] srcBandOffsets = src.getBandOffsets();
/* 213 */     int srcPixelStride = src.getPixelStride();
/* 214 */     int srcScanlineStride = src.getScanlineStride();
/* 216 */     int[] values = new int[filterSize * 2 - 1];
/* 217 */     int wp = filterSize;
/* 218 */     int offset = filterSize / 2;
/* 220 */     for (int k = 0; k < dnumBands; k++) {
/* 221 */       short[] dstData = dstDataArrays[k];
/* 222 */       short[] srcData = srcDataArrays[k];
/* 223 */       int srcScanlineOffset = srcBandOffsets[k];
/* 224 */       int dstScanlineOffset = dstBandOffsets[k];
/* 225 */       for (int j = 0; j < dheight; j++) {
/* 226 */         int srcPixelOffset = srcScanlineOffset;
/* 227 */         int dstPixelOffset = dstScanlineOffset;
/* 229 */         for (int i = 0; i < dwidth; i++) {
/* 230 */           int valueCount = 0;
/* 233 */           int imageOffset = srcPixelOffset;
/* 235 */           for (int u = 0; u < wp; u++) {
/* 236 */             values[valueCount++] = srcData[imageOffset] & 0xFFFF;
/* 238 */             imageOffset += srcScanlineStride + srcPixelStride;
/*     */           } 
/* 243 */           valueCount--;
/* 244 */           values[offset] = values[valueCount];
/* 247 */           imageOffset = srcPixelOffset + srcPixelStride * (filterSize - 1);
/* 250 */           for (int v = 0; v < wp; v++) {
/* 251 */             values[valueCount++] = srcData[imageOffset] & 0xFFFF;
/* 253 */             imageOffset += srcScanlineStride - srcPixelStride;
/*     */           } 
/* 256 */           int val = medianFilter(values);
/* 258 */           dstData[dstPixelOffset] = (short)val;
/* 259 */           srcPixelOffset += srcPixelStride;
/* 260 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/* 262 */         srcScanlineOffset += srcScanlineStride;
/* 263 */         dstScanlineOffset += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void intLoop(RasterAccessor src, RasterAccessor dst, int filterSize) {
/* 271 */     int dwidth = dst.getWidth();
/* 272 */     int dheight = dst.getHeight();
/* 273 */     int dnumBands = dst.getNumBands();
/* 275 */     int[][] dstDataArrays = dst.getIntDataArrays();
/* 276 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 277 */     int dstPixelStride = dst.getPixelStride();
/* 278 */     int dstScanlineStride = dst.getScanlineStride();
/* 280 */     int[][] srcDataArrays = src.getIntDataArrays();
/* 281 */     int[] srcBandOffsets = src.getBandOffsets();
/* 282 */     int srcPixelStride = src.getPixelStride();
/* 283 */     int srcScanlineStride = src.getScanlineStride();
/* 285 */     int[] values = new int[filterSize * 2 - 1];
/* 286 */     int wp = filterSize;
/* 287 */     int offset = filterSize / 2;
/* 289 */     for (int k = 0; k < dnumBands; k++) {
/* 290 */       int[] dstData = dstDataArrays[k];
/* 291 */       int[] srcData = srcDataArrays[k];
/* 292 */       int srcScanlineOffset = srcBandOffsets[k];
/* 293 */       int dstScanlineOffset = dstBandOffsets[k];
/* 294 */       for (int j = 0; j < dheight; j++) {
/* 295 */         int srcPixelOffset = srcScanlineOffset;
/* 296 */         int dstPixelOffset = dstScanlineOffset;
/* 298 */         for (int i = 0; i < dwidth; i++) {
/* 299 */           int valueCount = 0;
/* 302 */           int imageOffset = srcPixelOffset;
/* 304 */           for (int u = 0; u < wp; u++) {
/* 305 */             values[valueCount++] = srcData[imageOffset];
/* 306 */             imageOffset += srcScanlineStride + srcPixelStride;
/*     */           } 
/* 311 */           valueCount--;
/* 312 */           values[offset] = values[valueCount];
/* 315 */           imageOffset = srcPixelOffset + srcPixelStride * (filterSize - 1);
/* 318 */           for (int v = 0; v < wp; v++) {
/* 319 */             values[valueCount++] = srcData[imageOffset];
/* 320 */             imageOffset += srcScanlineStride - srcPixelStride;
/*     */           } 
/* 323 */           int val = medianFilter(values);
/* 325 */           dstData[dstPixelOffset] = val;
/* 326 */           srcPixelOffset += srcPixelStride;
/* 327 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/* 329 */         srcScanlineOffset += srcScanlineStride;
/* 330 */         dstScanlineOffset += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void floatLoop(RasterAccessor src, RasterAccessor dst, int filterSize) {
/* 338 */     int dwidth = dst.getWidth();
/* 339 */     int dheight = dst.getHeight();
/* 340 */     int dnumBands = dst.getNumBands();
/* 342 */     float[][] dstDataArrays = dst.getFloatDataArrays();
/* 343 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 344 */     int dstPixelStride = dst.getPixelStride();
/* 345 */     int dstScanlineStride = dst.getScanlineStride();
/* 347 */     float[][] srcDataArrays = src.getFloatDataArrays();
/* 348 */     int[] srcBandOffsets = src.getBandOffsets();
/* 349 */     int srcPixelStride = src.getPixelStride();
/* 350 */     int srcScanlineStride = src.getScanlineStride();
/* 352 */     float[] values = new float[filterSize * 2 - 1];
/* 353 */     int wp = filterSize;
/* 354 */     int offset = filterSize / 2;
/* 356 */     for (int k = 0; k < dnumBands; k++) {
/* 357 */       float[] dstData = dstDataArrays[k];
/* 358 */       float[] srcData = srcDataArrays[k];
/* 359 */       int srcScanlineOffset = srcBandOffsets[k];
/* 360 */       int dstScanlineOffset = dstBandOffsets[k];
/* 361 */       for (int j = 0; j < dheight; j++) {
/* 362 */         int srcPixelOffset = srcScanlineOffset;
/* 363 */         int dstPixelOffset = dstScanlineOffset;
/* 365 */         for (int i = 0; i < dwidth; i++) {
/* 366 */           int valueCount = 0;
/* 369 */           int imageOffset = srcPixelOffset;
/* 371 */           for (int u = 0; u < wp; u++) {
/* 372 */             values[valueCount++] = srcData[imageOffset];
/* 373 */             imageOffset += srcScanlineStride + srcPixelStride;
/*     */           } 
/* 378 */           valueCount--;
/* 379 */           values[offset] = values[valueCount];
/* 382 */           imageOffset = srcPixelOffset + srcPixelStride * (filterSize - 1);
/* 385 */           for (int v = 0; v < wp; v++) {
/* 386 */             values[valueCount++] = srcData[imageOffset];
/* 387 */             imageOffset += srcScanlineStride - srcPixelStride;
/*     */           } 
/* 390 */           float val = medianFilterFloat(values);
/* 392 */           dstData[dstPixelOffset] = val;
/* 393 */           srcPixelOffset += srcPixelStride;
/* 394 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/* 396 */         srcScanlineOffset += srcScanlineStride;
/* 397 */         dstScanlineOffset += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void doubleLoop(RasterAccessor src, RasterAccessor dst, int filterSize) {
/* 405 */     int dwidth = dst.getWidth();
/* 406 */     int dheight = dst.getHeight();
/* 407 */     int dnumBands = dst.getNumBands();
/* 409 */     double[][] dstDataArrays = dst.getDoubleDataArrays();
/* 410 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 411 */     int dstPixelStride = dst.getPixelStride();
/* 412 */     int dstScanlineStride = dst.getScanlineStride();
/* 414 */     double[][] srcDataArrays = src.getDoubleDataArrays();
/* 415 */     int[] srcBandOffsets = src.getBandOffsets();
/* 416 */     int srcPixelStride = src.getPixelStride();
/* 417 */     int srcScanlineStride = src.getScanlineStride();
/* 419 */     double[] values = new double[filterSize * 2 - 1];
/* 420 */     int wp = filterSize;
/* 421 */     int offset = filterSize / 2;
/* 423 */     for (int k = 0; k < dnumBands; k++) {
/* 424 */       double[] dstData = dstDataArrays[k];
/* 425 */       double[] srcData = srcDataArrays[k];
/* 426 */       int srcScanlineOffset = srcBandOffsets[k];
/* 427 */       int dstScanlineOffset = dstBandOffsets[k];
/* 428 */       for (int j = 0; j < dheight; j++) {
/* 429 */         int srcPixelOffset = srcScanlineOffset;
/* 430 */         int dstPixelOffset = dstScanlineOffset;
/* 432 */         for (int i = 0; i < dwidth; i++) {
/* 433 */           int valueCount = 0;
/* 436 */           int imageOffset = srcPixelOffset;
/* 438 */           for (int u = 0; u < wp; u++) {
/* 439 */             values[valueCount++] = srcData[imageOffset];
/* 440 */             imageOffset += srcScanlineStride + srcPixelStride;
/*     */           } 
/* 445 */           valueCount--;
/* 446 */           values[offset] = values[valueCount];
/* 449 */           imageOffset = srcPixelOffset + srcPixelStride * (filterSize - 1);
/* 452 */           for (int v = 0; v < wp; v++) {
/* 453 */             values[valueCount++] = srcData[imageOffset];
/* 454 */             imageOffset += srcScanlineStride - srcPixelStride;
/*     */           } 
/* 457 */           double val = medianFilterDouble(values);
/* 459 */           dstData[dstPixelOffset] = val;
/* 460 */           srcPixelOffset += srcPixelStride;
/* 461 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/* 463 */         srcScanlineOffset += srcScanlineStride;
/* 464 */         dstScanlineOffset += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\MedianFilterXOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */