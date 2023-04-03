/*     */ package com.sun.media.jai.opimage;
/*     */ 
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.util.Map;
/*     */ import javax.media.jai.BorderExtender;
/*     */ import javax.media.jai.ImageLayout;
/*     */ import javax.media.jai.RasterAccessor;
/*     */ import javax.media.jai.operator.MedianFilterDescriptor;
/*     */ 
/*     */ final class MedianFilterPlusOpImage extends MedianFilterOpImage {
/*     */   public MedianFilterPlusOpImage(RenderedImage source, BorderExtender extender, Map config, ImageLayout layout, int maskSize) {
/*  55 */     super(source, extender, config, layout, MedianFilterDescriptor.MEDIAN_MASK_PLUS, maskSize);
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
/*  80 */     int[] values = new int[filterSize * 2 - 1];
/*  81 */     int wp = filterSize;
/*  82 */     int offset = filterSize / 2;
/*  84 */     for (int k = 0; k < dnumBands; k++) {
/*  85 */       byte[] dstData = dstDataArrays[k];
/*  86 */       byte[] srcData = srcDataArrays[k];
/*  87 */       int srcScanlineOffset = srcBandOffsets[k];
/*  88 */       int dstScanlineOffset = dstBandOffsets[k];
/*  89 */       for (int j = 0; j < dheight; j++) {
/*  90 */         int srcPixelOffset = srcScanlineOffset;
/*  91 */         int dstPixelOffset = dstScanlineOffset;
/*  93 */         for (int i = 0; i < dwidth; i++) {
/*  94 */           int valueCount = 0;
/*  97 */           int imageOffset = srcPixelOffset + srcPixelStride * offset;
/*  99 */           for (int u = 0; u < wp; u++) {
/* 100 */             values[valueCount++] = srcData[imageOffset] & 0xFF;
/* 102 */             imageOffset += srcScanlineStride;
/*     */           } 
/* 107 */           valueCount--;
/* 108 */           values[offset] = values[valueCount];
/* 111 */           imageOffset = srcPixelOffset + srcScanlineStride * offset;
/* 114 */           for (int v = 0; v < wp; v++) {
/* 115 */             values[valueCount++] = srcData[imageOffset] & 0xFF;
/* 117 */             imageOffset += srcPixelStride;
/*     */           } 
/* 119 */           int val = medianFilter(values);
/* 121 */           dstData[dstPixelOffset] = (byte)val;
/* 122 */           srcPixelOffset += srcPixelStride;
/* 123 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/* 125 */         srcScanlineOffset += srcScanlineStride;
/* 126 */         dstScanlineOffset += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void shortLoop(RasterAccessor src, RasterAccessor dst, int filterSize) {
/* 134 */     int dwidth = dst.getWidth();
/* 135 */     int dheight = dst.getHeight();
/* 136 */     int dnumBands = dst.getNumBands();
/* 138 */     short[][] dstDataArrays = dst.getShortDataArrays();
/* 139 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 140 */     int dstPixelStride = dst.getPixelStride();
/* 141 */     int dstScanlineStride = dst.getScanlineStride();
/* 143 */     short[][] srcDataArrays = src.getShortDataArrays();
/* 144 */     int[] srcBandOffsets = src.getBandOffsets();
/* 145 */     int srcPixelStride = src.getPixelStride();
/* 146 */     int srcScanlineStride = src.getScanlineStride();
/* 148 */     int[] values = new int[filterSize * 2 - 1];
/* 149 */     int wp = filterSize;
/* 150 */     int offset = filterSize / 2;
/* 152 */     for (int k = 0; k < dnumBands; k++) {
/* 153 */       short[] dstData = dstDataArrays[k];
/* 154 */       short[] srcData = srcDataArrays[k];
/* 155 */       int srcScanlineOffset = srcBandOffsets[k];
/* 156 */       int dstScanlineOffset = dstBandOffsets[k];
/* 157 */       for (int j = 0; j < dheight; j++) {
/* 158 */         int srcPixelOffset = srcScanlineOffset;
/* 159 */         int dstPixelOffset = dstScanlineOffset;
/* 161 */         for (int i = 0; i < dwidth; i++) {
/* 162 */           int valueCount = 0;
/* 165 */           int imageOffset = srcPixelOffset + srcPixelStride * offset;
/* 167 */           for (int u = 0; u < wp; u++) {
/* 168 */             values[valueCount++] = srcData[imageOffset];
/* 170 */             imageOffset += srcScanlineStride;
/*     */           } 
/* 175 */           valueCount--;
/* 176 */           values[offset] = values[valueCount];
/* 179 */           imageOffset = srcPixelOffset + srcScanlineStride * offset;
/* 182 */           for (int v = 0; v < wp; v++) {
/* 183 */             values[valueCount++] = srcData[imageOffset];
/* 185 */             imageOffset += srcPixelStride;
/*     */           } 
/* 188 */           int val = medianFilter(values);
/* 190 */           dstData[dstPixelOffset] = (short)val;
/* 191 */           srcPixelOffset += srcPixelStride;
/* 192 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/* 194 */         srcScanlineOffset += srcScanlineStride;
/* 195 */         dstScanlineOffset += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void ushortLoop(RasterAccessor src, RasterAccessor dst, int filterSize) {
/* 203 */     int dwidth = dst.getWidth();
/* 204 */     int dheight = dst.getHeight();
/* 205 */     int dnumBands = dst.getNumBands();
/* 207 */     short[][] dstDataArrays = dst.getShortDataArrays();
/* 208 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 209 */     int dstPixelStride = dst.getPixelStride();
/* 210 */     int dstScanlineStride = dst.getScanlineStride();
/* 212 */     short[][] srcDataArrays = src.getShortDataArrays();
/* 213 */     int[] srcBandOffsets = src.getBandOffsets();
/* 214 */     int srcPixelStride = src.getPixelStride();
/* 215 */     int srcScanlineStride = src.getScanlineStride();
/* 217 */     int[] values = new int[filterSize * 2 - 1];
/* 218 */     int wp = filterSize;
/* 219 */     int offset = filterSize / 2;
/* 221 */     for (int k = 0; k < dnumBands; k++) {
/* 222 */       short[] dstData = dstDataArrays[k];
/* 223 */       short[] srcData = srcDataArrays[k];
/* 224 */       int srcScanlineOffset = srcBandOffsets[k];
/* 225 */       int dstScanlineOffset = dstBandOffsets[k];
/* 226 */       for (int j = 0; j < dheight; j++) {
/* 227 */         int srcPixelOffset = srcScanlineOffset;
/* 228 */         int dstPixelOffset = dstScanlineOffset;
/* 230 */         for (int i = 0; i < dwidth; i++) {
/* 231 */           int valueCount = 0;
/* 234 */           int imageOffset = srcPixelOffset + srcPixelStride * offset;
/* 236 */           for (int u = 0; u < wp; u++) {
/* 237 */             values[valueCount++] = srcData[imageOffset] & 0xFFFF;
/* 239 */             imageOffset += srcScanlineStride;
/*     */           } 
/* 244 */           valueCount--;
/* 245 */           values[offset] = values[valueCount];
/* 248 */           imageOffset = srcPixelOffset + srcScanlineStride * offset;
/* 251 */           for (int v = 0; v < wp; v++) {
/* 252 */             values[valueCount++] = srcData[imageOffset] & 0xFFFF;
/* 254 */             imageOffset += srcPixelStride;
/*     */           } 
/* 257 */           int val = medianFilter(values);
/* 259 */           dstData[dstPixelOffset] = (short)val;
/* 260 */           srcPixelOffset += srcPixelStride;
/* 261 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/* 263 */         srcScanlineOffset += srcScanlineStride;
/* 264 */         dstScanlineOffset += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void intLoop(RasterAccessor src, RasterAccessor dst, int filterSize) {
/* 272 */     int dwidth = dst.getWidth();
/* 273 */     int dheight = dst.getHeight();
/* 274 */     int dnumBands = dst.getNumBands();
/* 276 */     int[][] dstDataArrays = dst.getIntDataArrays();
/* 277 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 278 */     int dstPixelStride = dst.getPixelStride();
/* 279 */     int dstScanlineStride = dst.getScanlineStride();
/* 281 */     int[][] srcDataArrays = src.getIntDataArrays();
/* 282 */     int[] srcBandOffsets = src.getBandOffsets();
/* 283 */     int srcPixelStride = src.getPixelStride();
/* 284 */     int srcScanlineStride = src.getScanlineStride();
/* 286 */     int[] values = new int[filterSize * 2 - 1];
/* 287 */     int wp = filterSize;
/* 288 */     int offset = filterSize / 2;
/* 290 */     for (int k = 0; k < dnumBands; k++) {
/* 291 */       int[] dstData = dstDataArrays[k];
/* 292 */       int[] srcData = srcDataArrays[k];
/* 293 */       int srcScanlineOffset = srcBandOffsets[k];
/* 294 */       int dstScanlineOffset = dstBandOffsets[k];
/* 295 */       for (int j = 0; j < dheight; j++) {
/* 296 */         int srcPixelOffset = srcScanlineOffset;
/* 297 */         int dstPixelOffset = dstScanlineOffset;
/* 299 */         for (int i = 0; i < dwidth; i++) {
/* 300 */           int valueCount = 0;
/* 303 */           int imageOffset = srcPixelOffset + srcPixelStride * offset;
/* 305 */           for (int u = 0; u < wp; u++) {
/* 306 */             values[valueCount++] = srcData[imageOffset];
/* 307 */             imageOffset += srcScanlineStride;
/*     */           } 
/* 312 */           valueCount--;
/* 313 */           values[offset] = values[valueCount];
/* 316 */           imageOffset = srcPixelOffset + srcScanlineStride * offset;
/* 319 */           for (int v = 0; v < wp; v++) {
/* 320 */             values[valueCount++] = srcData[imageOffset];
/* 321 */             imageOffset += srcPixelStride;
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
/* 369 */           int imageOffset = srcPixelOffset + srcPixelStride * offset;
/* 371 */           for (int u = 0; u < wp; u++) {
/* 372 */             values[valueCount++] = srcData[imageOffset];
/* 373 */             imageOffset += srcScanlineStride;
/*     */           } 
/* 378 */           valueCount--;
/* 379 */           values[offset] = values[valueCount];
/* 382 */           imageOffset = srcPixelOffset + srcScanlineStride * offset;
/* 385 */           for (int v = 0; v < wp; v++) {
/* 386 */             values[valueCount++] = srcData[imageOffset];
/* 387 */             imageOffset += srcPixelStride;
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
/* 436 */           int imageOffset = srcPixelOffset + srcPixelStride * offset;
/* 438 */           for (int u = 0; u < wp; u++) {
/* 439 */             values[valueCount++] = srcData[imageOffset];
/* 440 */             imageOffset += srcScanlineStride;
/*     */           } 
/* 445 */           valueCount--;
/* 446 */           values[offset] = values[valueCount];
/* 449 */           imageOffset = srcPixelOffset + srcScanlineStride * offset;
/* 452 */           for (int v = 0; v < wp; v++) {
/* 453 */             values[valueCount++] = srcData[imageOffset];
/* 454 */             imageOffset += srcPixelStride;
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


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\MedianFilterPlusOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */