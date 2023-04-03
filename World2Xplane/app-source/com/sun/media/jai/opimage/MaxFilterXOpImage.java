/*     */ package com.sun.media.jai.opimage;
/*     */ 
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.util.Map;
/*     */ import javax.media.jai.BorderExtender;
/*     */ import javax.media.jai.ImageLayout;
/*     */ import javax.media.jai.RasterAccessor;
/*     */ import javax.media.jai.operator.MaxFilterDescriptor;
/*     */ 
/*     */ final class MaxFilterXOpImage extends MaxFilterOpImage {
/*     */   public MaxFilterXOpImage(RenderedImage source, BorderExtender extender, Map config, ImageLayout layout, int maskSize) {
/*  52 */     super(source, extender, config, layout, MaxFilterDescriptor.MAX_MASK_PLUS, maskSize);
/*     */   }
/*     */   
/*     */   protected void byteLoop(RasterAccessor src, RasterAccessor dst, int filterSize) {
/*  63 */     int dwidth = dst.getWidth();
/*  64 */     int dheight = dst.getHeight();
/*  65 */     int dnumBands = dst.getNumBands();
/*  67 */     byte[][] dstDataArrays = dst.getByteDataArrays();
/*  68 */     int[] dstBandOffsets = dst.getBandOffsets();
/*  69 */     int dstPixelStride = dst.getPixelStride();
/*  70 */     int dstScanlineStride = dst.getScanlineStride();
/*  72 */     byte[][] srcDataArrays = src.getByteDataArrays();
/*  73 */     int[] srcBandOffsets = src.getBandOffsets();
/*  74 */     int srcPixelStride = src.getPixelStride();
/*  75 */     int srcScanlineStride = src.getScanlineStride();
/*  76 */     int scanPlusPixelStride = srcScanlineStride + srcPixelStride;
/*  77 */     int scanMinusPixelStride = srcScanlineStride - srcPixelStride;
/*  78 */     int topRightOffset = srcPixelStride * (filterSize - 1);
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
/*  94 */           int maxval = Integer.MIN_VALUE;
/*  97 */           int imageOffset = srcPixelOffset;
/*  98 */           for (int u = 0; u < wp; u++) {
/*  99 */             int val = srcData[imageOffset] & 0xFF;
/* 100 */             imageOffset += scanPlusPixelStride;
/* 101 */             maxval = (val > maxval) ? val : maxval;
/*     */           } 
/* 105 */           imageOffset = srcPixelOffset + topRightOffset;
/* 107 */           for (int v = 0; v < wp; v++) {
/* 108 */             int val = srcData[imageOffset] & 0xFF;
/* 109 */             imageOffset += scanMinusPixelStride;
/* 110 */             maxval = (val > maxval) ? val : maxval;
/*     */           } 
/* 113 */           dstData[dstPixelOffset] = (byte)maxval;
/* 114 */           srcPixelOffset += srcPixelStride;
/* 115 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/* 117 */         srcScanlineOffset += srcScanlineStride;
/* 118 */         dstScanlineOffset += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void shortLoop(RasterAccessor src, RasterAccessor dst, int filterSize) {
/* 126 */     int dwidth = dst.getWidth();
/* 127 */     int dheight = dst.getHeight();
/* 128 */     int dnumBands = dst.getNumBands();
/* 130 */     short[][] dstDataArrays = dst.getShortDataArrays();
/* 131 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 132 */     int dstPixelStride = dst.getPixelStride();
/* 133 */     int dstScanlineStride = dst.getScanlineStride();
/* 135 */     short[][] srcDataArrays = src.getShortDataArrays();
/* 136 */     int[] srcBandOffsets = src.getBandOffsets();
/* 137 */     int srcPixelStride = src.getPixelStride();
/* 138 */     int srcScanlineStride = src.getScanlineStride();
/* 139 */     int scanPlusPixelStride = srcScanlineStride + srcPixelStride;
/* 140 */     int scanMinusPixelStride = srcScanlineStride - srcPixelStride;
/* 141 */     int topRightOffset = srcPixelStride * (filterSize - 1);
/* 144 */     int wp = filterSize;
/* 145 */     int offset = filterSize / 2;
/* 147 */     for (int k = 0; k < dnumBands; k++) {
/* 148 */       short[] dstData = dstDataArrays[k];
/* 149 */       short[] srcData = srcDataArrays[k];
/* 150 */       int srcScanlineOffset = srcBandOffsets[k];
/* 151 */       int dstScanlineOffset = dstBandOffsets[k];
/* 152 */       for (int j = 0; j < dheight; j++) {
/* 153 */         int srcPixelOffset = srcScanlineOffset;
/* 154 */         int dstPixelOffset = dstScanlineOffset;
/* 156 */         for (int i = 0; i < dwidth; i++) {
/* 157 */           int maxval = Integer.MIN_VALUE;
/* 160 */           int imageOffset = srcPixelOffset;
/* 162 */           for (int u = 0; u < wp; u++) {
/* 163 */             int val = srcData[imageOffset];
/* 164 */             imageOffset += scanPlusPixelStride;
/* 165 */             maxval = (val > maxval) ? val : maxval;
/*     */           } 
/* 169 */           imageOffset = srcPixelOffset + topRightOffset;
/* 171 */           for (int v = 0; v < wp; v++) {
/* 172 */             int val = srcData[imageOffset];
/* 173 */             imageOffset += scanMinusPixelStride;
/* 174 */             maxval = (val > maxval) ? val : maxval;
/*     */           } 
/* 177 */           dstData[dstPixelOffset] = (short)maxval;
/* 178 */           srcPixelOffset += srcPixelStride;
/* 179 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/* 181 */         srcScanlineOffset += srcScanlineStride;
/* 182 */         dstScanlineOffset += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void ushortLoop(RasterAccessor src, RasterAccessor dst, int filterSize) {
/* 190 */     int dwidth = dst.getWidth();
/* 191 */     int dheight = dst.getHeight();
/* 192 */     int dnumBands = dst.getNumBands();
/* 194 */     short[][] dstDataArrays = dst.getShortDataArrays();
/* 195 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 196 */     int dstPixelStride = dst.getPixelStride();
/* 197 */     int dstScanlineStride = dst.getScanlineStride();
/* 199 */     short[][] srcDataArrays = src.getShortDataArrays();
/* 200 */     int[] srcBandOffsets = src.getBandOffsets();
/* 201 */     int srcPixelStride = src.getPixelStride();
/* 202 */     int srcScanlineStride = src.getScanlineStride();
/* 203 */     int scanPlusPixelStride = srcScanlineStride + srcPixelStride;
/* 204 */     int scanMinusPixelStride = srcScanlineStride - srcPixelStride;
/* 205 */     int topRightOffset = srcPixelStride * (filterSize - 1);
/* 208 */     int wp = filterSize;
/* 209 */     int offset = filterSize / 2;
/* 211 */     for (int k = 0; k < dnumBands; k++) {
/* 212 */       short[] dstData = dstDataArrays[k];
/* 213 */       short[] srcData = srcDataArrays[k];
/* 214 */       int srcScanlineOffset = srcBandOffsets[k];
/* 215 */       int dstScanlineOffset = dstBandOffsets[k];
/* 216 */       for (int j = 0; j < dheight; j++) {
/* 217 */         int srcPixelOffset = srcScanlineOffset;
/* 218 */         int dstPixelOffset = dstScanlineOffset;
/* 220 */         for (int i = 0; i < dwidth; i++) {
/* 221 */           int maxval = Integer.MIN_VALUE;
/* 224 */           int imageOffset = srcPixelOffset;
/* 226 */           for (int u = 0; u < wp; u++) {
/* 227 */             int val = srcData[imageOffset] & 0xFFFF;
/* 228 */             imageOffset += scanPlusPixelStride;
/* 229 */             maxval = (val > maxval) ? val : maxval;
/*     */           } 
/* 233 */           imageOffset = srcPixelOffset + topRightOffset;
/* 235 */           for (int v = 0; v < wp; v++) {
/* 236 */             int val = srcData[imageOffset] & 0xFFFF;
/* 237 */             imageOffset += scanMinusPixelStride;
/* 238 */             maxval = (val > maxval) ? val : maxval;
/*     */           } 
/* 241 */           dstData[dstPixelOffset] = (short)maxval;
/* 242 */           srcPixelOffset += srcPixelStride;
/* 243 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/* 245 */         srcScanlineOffset += srcScanlineStride;
/* 246 */         dstScanlineOffset += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void intLoop(RasterAccessor src, RasterAccessor dst, int filterSize) {
/* 254 */     int dwidth = dst.getWidth();
/* 255 */     int dheight = dst.getHeight();
/* 256 */     int dnumBands = dst.getNumBands();
/* 258 */     int[][] dstDataArrays = dst.getIntDataArrays();
/* 259 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 260 */     int dstPixelStride = dst.getPixelStride();
/* 261 */     int dstScanlineStride = dst.getScanlineStride();
/* 263 */     int[][] srcDataArrays = src.getIntDataArrays();
/* 264 */     int[] srcBandOffsets = src.getBandOffsets();
/* 265 */     int srcPixelStride = src.getPixelStride();
/* 266 */     int srcScanlineStride = src.getScanlineStride();
/* 267 */     int scanPlusPixelStride = srcScanlineStride + srcPixelStride;
/* 268 */     int scanMinusPixelStride = srcScanlineStride - srcPixelStride;
/* 269 */     int topRightOffset = srcPixelStride * (filterSize - 1);
/* 272 */     int wp = filterSize;
/* 273 */     int offset = filterSize / 2;
/* 275 */     for (int k = 0; k < dnumBands; k++) {
/* 276 */       int[] dstData = dstDataArrays[k];
/* 277 */       int[] srcData = srcDataArrays[k];
/* 278 */       int srcScanlineOffset = srcBandOffsets[k];
/* 279 */       int dstScanlineOffset = dstBandOffsets[k];
/* 280 */       for (int j = 0; j < dheight; j++) {
/* 281 */         int srcPixelOffset = srcScanlineOffset;
/* 282 */         int dstPixelOffset = dstScanlineOffset;
/* 284 */         for (int i = 0; i < dwidth; i++) {
/* 285 */           int maxval = Integer.MIN_VALUE;
/* 288 */           int imageOffset = srcPixelOffset;
/* 290 */           for (int u = 0; u < wp; u++) {
/* 291 */             int val = srcData[imageOffset];
/* 292 */             imageOffset += scanPlusPixelStride;
/* 293 */             maxval = (val > maxval) ? val : maxval;
/*     */           } 
/* 297 */           imageOffset = srcPixelOffset + topRightOffset;
/* 299 */           for (int v = 0; v < wp; v++) {
/* 300 */             int val = srcData[imageOffset];
/* 301 */             imageOffset += scanMinusPixelStride;
/* 302 */             maxval = (val > maxval) ? val : maxval;
/*     */           } 
/* 305 */           dstData[dstPixelOffset] = maxval;
/* 306 */           srcPixelOffset += srcPixelStride;
/* 307 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/* 309 */         srcScanlineOffset += srcScanlineStride;
/* 310 */         dstScanlineOffset += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void floatLoop(RasterAccessor src, RasterAccessor dst, int filterSize) {
/* 318 */     int dwidth = dst.getWidth();
/* 319 */     int dheight = dst.getHeight();
/* 320 */     int dnumBands = dst.getNumBands();
/* 322 */     float[][] dstDataArrays = dst.getFloatDataArrays();
/* 323 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 324 */     int dstPixelStride = dst.getPixelStride();
/* 325 */     int dstScanlineStride = dst.getScanlineStride();
/* 327 */     float[][] srcDataArrays = src.getFloatDataArrays();
/* 328 */     int[] srcBandOffsets = src.getBandOffsets();
/* 329 */     int srcPixelStride = src.getPixelStride();
/* 330 */     int srcScanlineStride = src.getScanlineStride();
/* 331 */     int scanPlusPixelStride = srcScanlineStride + srcPixelStride;
/* 332 */     int scanMinusPixelStride = srcScanlineStride - srcPixelStride;
/* 333 */     int topRightOffset = srcPixelStride * (filterSize - 1);
/* 336 */     int wp = filterSize;
/* 337 */     int offset = filterSize / 2;
/* 339 */     for (int k = 0; k < dnumBands; k++) {
/* 340 */       float[] dstData = dstDataArrays[k];
/* 341 */       float[] srcData = srcDataArrays[k];
/* 342 */       int srcScanlineOffset = srcBandOffsets[k];
/* 343 */       int dstScanlineOffset = dstBandOffsets[k];
/* 344 */       for (int j = 0; j < dheight; j++) {
/* 345 */         int srcPixelOffset = srcScanlineOffset;
/* 346 */         int dstPixelOffset = dstScanlineOffset;
/* 348 */         for (int i = 0; i < dwidth; i++) {
/* 349 */           float maxval = -3.4028235E38F;
/* 352 */           int imageOffset = srcPixelOffset;
/* 354 */           for (int u = 0; u < wp; u++) {
/* 355 */             float val = srcData[imageOffset];
/* 356 */             imageOffset += scanPlusPixelStride;
/* 357 */             maxval = (val > maxval) ? val : maxval;
/*     */           } 
/* 361 */           imageOffset = srcPixelOffset + topRightOffset;
/* 363 */           for (int v = 0; v < wp; v++) {
/* 364 */             float val = srcData[imageOffset];
/* 365 */             imageOffset += scanMinusPixelStride;
/* 366 */             maxval = (val > maxval) ? val : maxval;
/*     */           } 
/* 369 */           dstData[dstPixelOffset] = maxval;
/* 370 */           srcPixelOffset += srcPixelStride;
/* 371 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/* 373 */         srcScanlineOffset += srcScanlineStride;
/* 374 */         dstScanlineOffset += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void doubleLoop(RasterAccessor src, RasterAccessor dst, int filterSize) {
/* 382 */     int dwidth = dst.getWidth();
/* 383 */     int dheight = dst.getHeight();
/* 384 */     int dnumBands = dst.getNumBands();
/* 386 */     double[][] dstDataArrays = dst.getDoubleDataArrays();
/* 387 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 388 */     int dstPixelStride = dst.getPixelStride();
/* 389 */     int dstScanlineStride = dst.getScanlineStride();
/* 391 */     double[][] srcDataArrays = src.getDoubleDataArrays();
/* 392 */     int[] srcBandOffsets = src.getBandOffsets();
/* 393 */     int srcPixelStride = src.getPixelStride();
/* 394 */     int srcScanlineStride = src.getScanlineStride();
/* 395 */     int scanPlusPixelStride = srcScanlineStride + srcPixelStride;
/* 396 */     int scanMinusPixelStride = srcScanlineStride - srcPixelStride;
/* 397 */     int topRightOffset = srcPixelStride * (filterSize - 1);
/* 400 */     int wp = filterSize;
/* 401 */     int offset = filterSize / 2;
/* 403 */     for (int k = 0; k < dnumBands; k++) {
/* 404 */       double[] dstData = dstDataArrays[k];
/* 405 */       double[] srcData = srcDataArrays[k];
/* 406 */       int srcScanlineOffset = srcBandOffsets[k];
/* 407 */       int dstScanlineOffset = dstBandOffsets[k];
/* 408 */       for (int j = 0; j < dheight; j++) {
/* 409 */         int srcPixelOffset = srcScanlineOffset;
/* 410 */         int dstPixelOffset = dstScanlineOffset;
/* 412 */         for (int i = 0; i < dwidth; i++) {
/* 413 */           double maxval = -1.7976931348623157E308D;
/* 416 */           int imageOffset = srcPixelOffset;
/* 418 */           for (int u = 0; u < wp; u++) {
/* 419 */             double val = srcData[imageOffset];
/* 420 */             imageOffset += scanPlusPixelStride;
/* 421 */             maxval = (val > maxval) ? val : maxval;
/*     */           } 
/* 425 */           imageOffset = srcPixelOffset + topRightOffset;
/* 427 */           for (int v = 0; v < wp; v++) {
/* 428 */             double val = srcData[imageOffset];
/* 429 */             imageOffset += scanMinusPixelStride;
/* 430 */             maxval = (val > maxval) ? val : maxval;
/*     */           } 
/* 433 */           dstData[dstPixelOffset] = maxval;
/* 434 */           srcPixelOffset += srcPixelStride;
/* 435 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/* 437 */         srcScanlineOffset += srcScanlineStride;
/* 438 */         dstScanlineOffset += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\MaxFilterXOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */