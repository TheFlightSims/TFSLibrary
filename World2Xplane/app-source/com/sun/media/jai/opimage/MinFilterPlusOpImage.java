/*     */ package com.sun.media.jai.opimage;
/*     */ 
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.util.Map;
/*     */ import javax.media.jai.BorderExtender;
/*     */ import javax.media.jai.ImageLayout;
/*     */ import javax.media.jai.RasterAccessor;
/*     */ import javax.media.jai.operator.MinFilterDescriptor;
/*     */ 
/*     */ final class MinFilterPlusOpImage extends MinFilterOpImage {
/*     */   public MinFilterPlusOpImage(RenderedImage source, BorderExtender extender, Map config, ImageLayout layout, int maskSize) {
/*  54 */     super(source, extender, config, layout, MinFilterDescriptor.MIN_MASK_PLUS, maskSize);
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
/*  80 */     int wp = filterSize;
/*  81 */     int offset = filterSize / 2;
/*  83 */     for (int k = 0; k < dnumBands; k++) {
/*  84 */       byte[] dstData = dstDataArrays[k];
/*  85 */       byte[] srcData = srcDataArrays[k];
/*  86 */       int srcScanlineOffset = srcBandOffsets[k];
/*  87 */       int dstScanlineOffset = dstBandOffsets[k];
/*  88 */       for (int j = 0; j < dheight; j++) {
/*  89 */         int srcPixelOffset = srcScanlineOffset;
/*  90 */         int dstPixelOffset = dstScanlineOffset;
/*  92 */         for (int i = 0; i < dwidth; i++) {
/*  95 */           int imageOffset = srcPixelOffset + srcPixelStride * offset;
/*  98 */           int minval = Integer.MAX_VALUE;
/* 100 */           for (int u = 0; u < wp; u++) {
/* 101 */             int val = srcData[imageOffset] & 0xFF;
/* 102 */             imageOffset += srcScanlineStride;
/* 103 */             minval = (val < minval) ? val : minval;
/*     */           } 
/* 107 */           imageOffset = srcPixelOffset + srcScanlineStride * offset;
/* 110 */           for (int v = 0; v < wp; v++) {
/* 111 */             int val = srcData[imageOffset] & 0xFF;
/* 112 */             imageOffset += srcPixelStride;
/* 113 */             minval = (val < minval) ? val : minval;
/*     */           } 
/* 116 */           dstData[dstPixelOffset] = (byte)minval;
/* 117 */           srcPixelOffset += srcPixelStride;
/* 118 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/* 120 */         srcScanlineOffset += srcScanlineStride;
/* 121 */         dstScanlineOffset += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void shortLoop(RasterAccessor src, RasterAccessor dst, int filterSize) {
/* 129 */     int dwidth = dst.getWidth();
/* 130 */     int dheight = dst.getHeight();
/* 131 */     int dnumBands = dst.getNumBands();
/* 133 */     short[][] dstDataArrays = dst.getShortDataArrays();
/* 134 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 135 */     int dstPixelStride = dst.getPixelStride();
/* 136 */     int dstScanlineStride = dst.getScanlineStride();
/* 138 */     short[][] srcDataArrays = src.getShortDataArrays();
/* 139 */     int[] srcBandOffsets = src.getBandOffsets();
/* 140 */     int srcPixelStride = src.getPixelStride();
/* 141 */     int srcScanlineStride = src.getScanlineStride();
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
/* 159 */           int imageOffset = srcPixelOffset + srcPixelStride * offset;
/* 162 */           int minval = Integer.MAX_VALUE;
/* 164 */           for (int u = 0; u < wp; u++) {
/* 165 */             int val = srcData[imageOffset];
/* 166 */             imageOffset += srcScanlineStride;
/* 167 */             minval = (val < minval) ? val : minval;
/*     */           } 
/* 171 */           imageOffset = srcPixelOffset + srcScanlineStride * offset;
/* 174 */           for (int v = 0; v < wp; v++) {
/* 175 */             int val = srcData[imageOffset];
/* 176 */             imageOffset += srcPixelStride;
/* 177 */             minval = (val < minval) ? val : minval;
/*     */           } 
/* 180 */           dstData[dstPixelOffset] = (short)minval;
/* 181 */           srcPixelOffset += srcPixelStride;
/* 182 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/* 184 */         srcScanlineOffset += srcScanlineStride;
/* 185 */         dstScanlineOffset += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void ushortLoop(RasterAccessor src, RasterAccessor dst, int filterSize) {
/* 193 */     int dwidth = dst.getWidth();
/* 194 */     int dheight = dst.getHeight();
/* 195 */     int dnumBands = dst.getNumBands();
/* 197 */     short[][] dstDataArrays = dst.getShortDataArrays();
/* 198 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 199 */     int dstPixelStride = dst.getPixelStride();
/* 200 */     int dstScanlineStride = dst.getScanlineStride();
/* 202 */     short[][] srcDataArrays = src.getShortDataArrays();
/* 203 */     int[] srcBandOffsets = src.getBandOffsets();
/* 204 */     int srcPixelStride = src.getPixelStride();
/* 205 */     int srcScanlineStride = src.getScanlineStride();
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
/* 223 */           int imageOffset = srcPixelOffset + srcPixelStride * offset;
/* 226 */           int minval = Integer.MAX_VALUE;
/* 228 */           for (int u = 0; u < wp; u++) {
/* 229 */             int val = srcData[imageOffset] & 0xFFFF;
/* 230 */             imageOffset += srcScanlineStride;
/* 231 */             minval = (val < minval) ? val : minval;
/*     */           } 
/* 235 */           imageOffset = srcPixelOffset + srcScanlineStride * offset;
/* 238 */           for (int v = 0; v < wp; v++) {
/* 239 */             int val = srcData[imageOffset] & 0xFFFF;
/* 240 */             imageOffset += srcPixelStride;
/* 241 */             minval = (val < minval) ? val : minval;
/*     */           } 
/* 244 */           dstData[dstPixelOffset] = (short)minval;
/* 245 */           srcPixelOffset += srcPixelStride;
/* 246 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/* 248 */         srcScanlineOffset += srcScanlineStride;
/* 249 */         dstScanlineOffset += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void intLoop(RasterAccessor src, RasterAccessor dst, int filterSize) {
/* 257 */     int dwidth = dst.getWidth();
/* 258 */     int dheight = dst.getHeight();
/* 259 */     int dnumBands = dst.getNumBands();
/* 261 */     int[][] dstDataArrays = dst.getIntDataArrays();
/* 262 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 263 */     int dstPixelStride = dst.getPixelStride();
/* 264 */     int dstScanlineStride = dst.getScanlineStride();
/* 266 */     int[][] srcDataArrays = src.getIntDataArrays();
/* 267 */     int[] srcBandOffsets = src.getBandOffsets();
/* 268 */     int srcPixelStride = src.getPixelStride();
/* 269 */     int srcScanlineStride = src.getScanlineStride();
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
/* 287 */           int imageOffset = srcPixelOffset + srcPixelStride * offset;
/* 290 */           int minval = Integer.MAX_VALUE;
/* 292 */           for (int u = 0; u < wp; u++) {
/* 293 */             int val = srcData[imageOffset];
/* 294 */             imageOffset += srcScanlineStride;
/* 295 */             minval = (val < minval) ? val : minval;
/*     */           } 
/* 299 */           imageOffset = srcPixelOffset + srcScanlineStride * offset;
/* 302 */           for (int v = 0; v < wp; v++) {
/* 303 */             int val = srcData[imageOffset];
/* 304 */             imageOffset += srcPixelStride;
/* 305 */             minval = (val < minval) ? val : minval;
/*     */           } 
/* 308 */           dstData[dstPixelOffset] = minval;
/* 309 */           srcPixelOffset += srcPixelStride;
/* 310 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/* 312 */         srcScanlineOffset += srcScanlineStride;
/* 313 */         dstScanlineOffset += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void floatLoop(RasterAccessor src, RasterAccessor dst, int filterSize) {
/* 321 */     int dwidth = dst.getWidth();
/* 322 */     int dheight = dst.getHeight();
/* 323 */     int dnumBands = dst.getNumBands();
/* 325 */     float[][] dstDataArrays = dst.getFloatDataArrays();
/* 326 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 327 */     int dstPixelStride = dst.getPixelStride();
/* 328 */     int dstScanlineStride = dst.getScanlineStride();
/* 330 */     float[][] srcDataArrays = src.getFloatDataArrays();
/* 331 */     int[] srcBandOffsets = src.getBandOffsets();
/* 332 */     int srcPixelStride = src.getPixelStride();
/* 333 */     int srcScanlineStride = src.getScanlineStride();
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
/* 351 */           int imageOffset = srcPixelOffset + srcPixelStride * offset;
/* 354 */           float minval = Float.MAX_VALUE;
/* 356 */           for (int u = 0; u < wp; u++) {
/* 357 */             float val = srcData[imageOffset];
/* 358 */             imageOffset += srcScanlineStride;
/* 359 */             minval = (val < minval) ? val : minval;
/*     */           } 
/* 363 */           imageOffset = srcPixelOffset + srcScanlineStride * offset;
/* 366 */           for (int v = 0; v < wp; v++) {
/* 367 */             float val = srcData[imageOffset];
/* 368 */             imageOffset += srcPixelStride;
/* 369 */             minval = (val < minval) ? val : minval;
/*     */           } 
/* 372 */           dstData[dstPixelOffset] = minval;
/* 373 */           srcPixelOffset += srcPixelStride;
/* 374 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/* 376 */         srcScanlineOffset += srcScanlineStride;
/* 377 */         dstScanlineOffset += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void doubleLoop(RasterAccessor src, RasterAccessor dst, int filterSize) {
/* 385 */     int dwidth = dst.getWidth();
/* 386 */     int dheight = dst.getHeight();
/* 387 */     int dnumBands = dst.getNumBands();
/* 389 */     double[][] dstDataArrays = dst.getDoubleDataArrays();
/* 390 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 391 */     int dstPixelStride = dst.getPixelStride();
/* 392 */     int dstScanlineStride = dst.getScanlineStride();
/* 394 */     double[][] srcDataArrays = src.getDoubleDataArrays();
/* 395 */     int[] srcBandOffsets = src.getBandOffsets();
/* 396 */     int srcPixelStride = src.getPixelStride();
/* 397 */     int srcScanlineStride = src.getScanlineStride();
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
/* 415 */           int imageOffset = srcPixelOffset + srcPixelStride * offset;
/* 418 */           double minval = Double.MAX_VALUE;
/* 420 */           for (int u = 0; u < wp; u++) {
/* 421 */             double val = srcData[imageOffset];
/* 422 */             imageOffset += srcScanlineStride;
/* 423 */             minval = (val < minval) ? val : minval;
/*     */           } 
/* 427 */           imageOffset = srcPixelOffset + srcScanlineStride * offset;
/* 430 */           for (int v = 0; v < wp; v++) {
/* 431 */             double val = srcData[imageOffset];
/* 432 */             imageOffset += srcPixelStride;
/* 433 */             minval = (val < minval) ? val : minval;
/*     */           } 
/* 436 */           dstData[dstPixelOffset] = minval;
/* 437 */           srcPixelOffset += srcPixelStride;
/* 438 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/* 440 */         srcScanlineOffset += srcScanlineStride;
/* 441 */         dstScanlineOffset += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\MinFilterPlusOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */