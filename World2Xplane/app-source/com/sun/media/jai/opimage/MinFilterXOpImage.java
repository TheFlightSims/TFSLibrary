/*     */ package com.sun.media.jai.opimage;
/*     */ 
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.util.Map;
/*     */ import javax.media.jai.BorderExtender;
/*     */ import javax.media.jai.ImageLayout;
/*     */ import javax.media.jai.RasterAccessor;
/*     */ import javax.media.jai.operator.MinFilterDescriptor;
/*     */ 
/*     */ final class MinFilterXOpImage extends MinFilterOpImage {
/*     */   public MinFilterXOpImage(RenderedImage source, BorderExtender extender, Map config, ImageLayout layout, int maskSize) {
/*  52 */     super(source, extender, config, layout, MinFilterDescriptor.MIN_MASK_PLUS, maskSize);
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
/*  94 */           int minval = Integer.MAX_VALUE;
/*  97 */           int imageOffset = srcPixelOffset;
/*  98 */           for (int u = 0; u < wp; u++) {
/*  99 */             int val = srcData[imageOffset] & 0xFF;
/* 100 */             imageOffset += scanPlusPixelStride;
/* 101 */             minval = (val < minval) ? val : minval;
/*     */           } 
/* 105 */           imageOffset = srcPixelOffset + topRightOffset;
/* 107 */           for (int v = 0; v < wp; v++) {
/* 108 */             int val = srcData[imageOffset] & 0xFF;
/* 109 */             imageOffset += scanMinusPixelStride;
/* 110 */             minval = (val < minval) ? val : minval;
/*     */           } 
/* 113 */           dstData[dstPixelOffset] = (byte)minval;
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
/* 157 */           int minval = Integer.MAX_VALUE;
/* 160 */           int imageOffset = srcPixelOffset;
/* 161 */           for (int u = 0; u < wp; u++) {
/* 162 */             int val = srcData[imageOffset];
/* 163 */             imageOffset += scanPlusPixelStride;
/* 164 */             minval = (val < minval) ? val : minval;
/*     */           } 
/* 168 */           imageOffset = srcPixelOffset + topRightOffset;
/* 170 */           for (int v = 0; v < wp; v++) {
/* 171 */             int val = srcData[imageOffset];
/* 172 */             imageOffset += scanMinusPixelStride;
/* 173 */             minval = (val < minval) ? val : minval;
/*     */           } 
/* 176 */           dstData[dstPixelOffset] = (short)minval;
/* 177 */           srcPixelOffset += srcPixelStride;
/* 178 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/* 180 */         srcScanlineOffset += srcScanlineStride;
/* 181 */         dstScanlineOffset += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void ushortLoop(RasterAccessor src, RasterAccessor dst, int filterSize) {
/* 189 */     int dwidth = dst.getWidth();
/* 190 */     int dheight = dst.getHeight();
/* 191 */     int dnumBands = dst.getNumBands();
/* 193 */     short[][] dstDataArrays = dst.getShortDataArrays();
/* 194 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 195 */     int dstPixelStride = dst.getPixelStride();
/* 196 */     int dstScanlineStride = dst.getScanlineStride();
/* 198 */     short[][] srcDataArrays = src.getShortDataArrays();
/* 199 */     int[] srcBandOffsets = src.getBandOffsets();
/* 200 */     int srcPixelStride = src.getPixelStride();
/* 201 */     int srcScanlineStride = src.getScanlineStride();
/* 202 */     int scanPlusPixelStride = srcScanlineStride + srcPixelStride;
/* 203 */     int scanMinusPixelStride = srcScanlineStride - srcPixelStride;
/* 204 */     int topRightOffset = srcPixelStride * (filterSize - 1);
/* 207 */     int wp = filterSize;
/* 208 */     int offset = filterSize / 2;
/* 210 */     for (int k = 0; k < dnumBands; k++) {
/* 211 */       short[] dstData = dstDataArrays[k];
/* 212 */       short[] srcData = srcDataArrays[k];
/* 213 */       int srcScanlineOffset = srcBandOffsets[k];
/* 214 */       int dstScanlineOffset = dstBandOffsets[k];
/* 215 */       for (int j = 0; j < dheight; j++) {
/* 216 */         int srcPixelOffset = srcScanlineOffset;
/* 217 */         int dstPixelOffset = dstScanlineOffset;
/* 219 */         for (int i = 0; i < dwidth; i++) {
/* 220 */           int minval = Integer.MAX_VALUE;
/* 223 */           int imageOffset = srcPixelOffset;
/* 224 */           for (int u = 0; u < wp; u++) {
/* 225 */             int val = srcData[imageOffset] & 0xFFFF;
/* 226 */             imageOffset += scanPlusPixelStride;
/* 227 */             minval = (val < minval) ? val : minval;
/*     */           } 
/* 231 */           imageOffset = srcPixelOffset + topRightOffset;
/* 233 */           for (int v = 0; v < wp; v++) {
/* 234 */             int val = srcData[imageOffset] & 0xFFFF;
/* 235 */             imageOffset += scanMinusPixelStride;
/* 236 */             minval = (val < minval) ? val : minval;
/*     */           } 
/* 239 */           dstData[dstPixelOffset] = (short)minval;
/* 240 */           srcPixelOffset += srcPixelStride;
/* 241 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/* 243 */         srcScanlineOffset += srcScanlineStride;
/* 244 */         dstScanlineOffset += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void intLoop(RasterAccessor src, RasterAccessor dst, int filterSize) {
/* 252 */     int dwidth = dst.getWidth();
/* 253 */     int dheight = dst.getHeight();
/* 254 */     int dnumBands = dst.getNumBands();
/* 256 */     int[][] dstDataArrays = dst.getIntDataArrays();
/* 257 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 258 */     int dstPixelStride = dst.getPixelStride();
/* 259 */     int dstScanlineStride = dst.getScanlineStride();
/* 261 */     int[][] srcDataArrays = src.getIntDataArrays();
/* 262 */     int[] srcBandOffsets = src.getBandOffsets();
/* 263 */     int srcPixelStride = src.getPixelStride();
/* 264 */     int srcScanlineStride = src.getScanlineStride();
/* 265 */     int scanPlusPixelStride = srcScanlineStride + srcPixelStride;
/* 266 */     int scanMinusPixelStride = srcScanlineStride - srcPixelStride;
/* 267 */     int topRightOffset = srcPixelStride * (filterSize - 1);
/* 270 */     int wp = filterSize;
/* 271 */     int offset = filterSize / 2;
/* 273 */     for (int k = 0; k < dnumBands; k++) {
/* 274 */       int[] dstData = dstDataArrays[k];
/* 275 */       int[] srcData = srcDataArrays[k];
/* 276 */       int srcScanlineOffset = srcBandOffsets[k];
/* 277 */       int dstScanlineOffset = dstBandOffsets[k];
/* 278 */       for (int j = 0; j < dheight; j++) {
/* 279 */         int srcPixelOffset = srcScanlineOffset;
/* 280 */         int dstPixelOffset = dstScanlineOffset;
/* 282 */         for (int i = 0; i < dwidth; i++) {
/* 283 */           int minval = Integer.MAX_VALUE;
/* 286 */           int imageOffset = srcPixelOffset;
/* 287 */           for (int u = 0; u < wp; u++) {
/* 288 */             int val = srcData[imageOffset];
/* 289 */             imageOffset += scanPlusPixelStride;
/* 290 */             minval = (val < minval) ? val : minval;
/*     */           } 
/* 294 */           imageOffset = srcPixelOffset + topRightOffset;
/* 296 */           for (int v = 0; v < wp; v++) {
/* 297 */             int val = srcData[imageOffset];
/* 298 */             imageOffset += scanMinusPixelStride;
/* 299 */             minval = (val < minval) ? val : minval;
/*     */           } 
/* 302 */           dstData[dstPixelOffset] = minval;
/* 303 */           srcPixelOffset += srcPixelStride;
/* 304 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/* 306 */         srcScanlineOffset += srcScanlineStride;
/* 307 */         dstScanlineOffset += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void floatLoop(RasterAccessor src, RasterAccessor dst, int filterSize) {
/* 315 */     int dwidth = dst.getWidth();
/* 316 */     int dheight = dst.getHeight();
/* 317 */     int dnumBands = dst.getNumBands();
/* 319 */     float[][] dstDataArrays = dst.getFloatDataArrays();
/* 320 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 321 */     int dstPixelStride = dst.getPixelStride();
/* 322 */     int dstScanlineStride = dst.getScanlineStride();
/* 324 */     float[][] srcDataArrays = src.getFloatDataArrays();
/* 325 */     int[] srcBandOffsets = src.getBandOffsets();
/* 326 */     int srcPixelStride = src.getPixelStride();
/* 327 */     int srcScanlineStride = src.getScanlineStride();
/* 328 */     int scanPlusPixelStride = srcScanlineStride + srcPixelStride;
/* 329 */     int scanMinusPixelStride = srcScanlineStride - srcPixelStride;
/* 330 */     int topRightOffset = srcPixelStride * (filterSize - 1);
/* 333 */     int wp = filterSize;
/* 334 */     int offset = filterSize / 2;
/* 336 */     for (int k = 0; k < dnumBands; k++) {
/* 337 */       float[] dstData = dstDataArrays[k];
/* 338 */       float[] srcData = srcDataArrays[k];
/* 339 */       int srcScanlineOffset = srcBandOffsets[k];
/* 340 */       int dstScanlineOffset = dstBandOffsets[k];
/* 341 */       for (int j = 0; j < dheight; j++) {
/* 342 */         int srcPixelOffset = srcScanlineOffset;
/* 343 */         int dstPixelOffset = dstScanlineOffset;
/* 345 */         for (int i = 0; i < dwidth; i++) {
/* 346 */           float minval = Float.MAX_VALUE;
/* 349 */           int imageOffset = srcPixelOffset;
/* 350 */           for (int u = 0; u < wp; u++) {
/* 351 */             float val = srcData[imageOffset];
/* 352 */             imageOffset += scanPlusPixelStride;
/* 353 */             minval = (val < minval) ? val : minval;
/*     */           } 
/* 357 */           imageOffset = srcPixelOffset + topRightOffset;
/* 359 */           for (int v = 0; v < wp; v++) {
/* 360 */             float val = srcData[imageOffset];
/* 361 */             imageOffset += scanMinusPixelStride;
/* 362 */             minval = (val < minval) ? val : minval;
/*     */           } 
/* 365 */           dstData[dstPixelOffset] = minval;
/* 366 */           srcPixelOffset += srcPixelStride;
/* 367 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/* 369 */         srcScanlineOffset += srcScanlineStride;
/* 370 */         dstScanlineOffset += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void doubleLoop(RasterAccessor src, RasterAccessor dst, int filterSize) {
/* 378 */     int dwidth = dst.getWidth();
/* 379 */     int dheight = dst.getHeight();
/* 380 */     int dnumBands = dst.getNumBands();
/* 382 */     double[][] dstDataArrays = dst.getDoubleDataArrays();
/* 383 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 384 */     int dstPixelStride = dst.getPixelStride();
/* 385 */     int dstScanlineStride = dst.getScanlineStride();
/* 387 */     double[][] srcDataArrays = src.getDoubleDataArrays();
/* 388 */     int[] srcBandOffsets = src.getBandOffsets();
/* 389 */     int srcPixelStride = src.getPixelStride();
/* 390 */     int srcScanlineStride = src.getScanlineStride();
/* 391 */     int scanPlusPixelStride = srcScanlineStride + srcPixelStride;
/* 392 */     int scanMinusPixelStride = srcScanlineStride - srcPixelStride;
/* 393 */     int topRightOffset = srcPixelStride * (filterSize - 1);
/* 396 */     int wp = filterSize;
/* 397 */     int offset = filterSize / 2;
/* 399 */     for (int k = 0; k < dnumBands; k++) {
/* 400 */       double[] dstData = dstDataArrays[k];
/* 401 */       double[] srcData = srcDataArrays[k];
/* 402 */       int srcScanlineOffset = srcBandOffsets[k];
/* 403 */       int dstScanlineOffset = dstBandOffsets[k];
/* 404 */       for (int j = 0; j < dheight; j++) {
/* 405 */         int srcPixelOffset = srcScanlineOffset;
/* 406 */         int dstPixelOffset = dstScanlineOffset;
/* 408 */         for (int i = 0; i < dwidth; i++) {
/* 409 */           double minval = Double.MAX_VALUE;
/* 412 */           int imageOffset = srcPixelOffset;
/* 414 */           for (int u = 0; u < wp; u++) {
/* 415 */             double val = srcData[imageOffset];
/* 416 */             imageOffset += scanPlusPixelStride;
/* 417 */             minval = (val < minval) ? val : minval;
/*     */           } 
/* 421 */           imageOffset = srcPixelOffset + topRightOffset;
/* 423 */           for (int v = 0; v < wp; v++) {
/* 424 */             double val = srcData[imageOffset];
/* 425 */             imageOffset += scanMinusPixelStride;
/* 426 */             minval = (val < minval) ? val : minval;
/*     */           } 
/* 429 */           dstData[dstPixelOffset] = minval;
/* 430 */           srcPixelOffset += srcPixelStride;
/* 431 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/* 433 */         srcScanlineOffset += srcScanlineStride;
/* 434 */         dstScanlineOffset += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\MinFilterXOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */