/*     */ package com.sun.media.jai.opimage;
/*     */ 
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.util.Map;
/*     */ import javax.media.jai.BorderExtender;
/*     */ import javax.media.jai.ImageLayout;
/*     */ import javax.media.jai.RasterAccessor;
/*     */ import javax.media.jai.operator.MaxFilterDescriptor;
/*     */ 
/*     */ final class MaxFilterPlusOpImage extends MaxFilterOpImage {
/*     */   public MaxFilterPlusOpImage(RenderedImage source, BorderExtender extender, Map config, ImageLayout layout, int maskSize) {
/*  54 */     super(source, extender, config, layout, MaxFilterDescriptor.MAX_MASK_PLUS, maskSize);
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
/*  93 */           int maxval = Integer.MIN_VALUE;
/*  96 */           int imageOffset = srcPixelOffset + srcPixelStride * offset;
/*  98 */           for (int u = 0; u < wp; u++) {
/*  99 */             int val = srcData[imageOffset] & 0xFF;
/* 100 */             imageOffset += srcScanlineStride;
/* 101 */             maxval = (val > maxval) ? val : maxval;
/*     */           } 
/* 105 */           imageOffset = srcPixelOffset + srcScanlineStride * offset;
/* 108 */           for (int v = 0; v < wp; v++) {
/* 109 */             int val = srcData[imageOffset] & 0xFF;
/* 110 */             imageOffset += srcPixelStride;
/* 111 */             maxval = (val > maxval) ? val : maxval;
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
/* 141 */     int wp = filterSize;
/* 142 */     int offset = filterSize / 2;
/* 144 */     for (int k = 0; k < dnumBands; k++) {
/* 145 */       short[] dstData = dstDataArrays[k];
/* 146 */       short[] srcData = srcDataArrays[k];
/* 147 */       int srcScanlineOffset = srcBandOffsets[k];
/* 148 */       int dstScanlineOffset = dstBandOffsets[k];
/* 149 */       for (int j = 0; j < dheight; j++) {
/* 150 */         int srcPixelOffset = srcScanlineOffset;
/* 151 */         int dstPixelOffset = dstScanlineOffset;
/* 153 */         for (int i = 0; i < dwidth; i++) {
/* 154 */           int maxval = Integer.MIN_VALUE;
/* 157 */           int imageOffset = srcPixelOffset + srcPixelStride * offset;
/* 159 */           for (int u = 0; u < wp; u++) {
/* 160 */             int val = srcData[imageOffset];
/* 161 */             imageOffset += srcScanlineStride;
/* 162 */             maxval = (val > maxval) ? val : maxval;
/*     */           } 
/* 166 */           imageOffset = srcPixelOffset + srcScanlineStride * offset;
/* 169 */           for (int v = 0; v < wp; v++) {
/* 170 */             int val = srcData[imageOffset];
/* 171 */             imageOffset += srcPixelStride;
/* 172 */             maxval = (val > maxval) ? val : maxval;
/*     */           } 
/* 175 */           dstData[dstPixelOffset] = (short)maxval;
/* 176 */           srcPixelOffset += srcPixelStride;
/* 177 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/* 179 */         srcScanlineOffset += srcScanlineStride;
/* 180 */         dstScanlineOffset += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void ushortLoop(RasterAccessor src, RasterAccessor dst, int filterSize) {
/* 188 */     int dwidth = dst.getWidth();
/* 189 */     int dheight = dst.getHeight();
/* 190 */     int dnumBands = dst.getNumBands();
/* 192 */     short[][] dstDataArrays = dst.getShortDataArrays();
/* 193 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 194 */     int dstPixelStride = dst.getPixelStride();
/* 195 */     int dstScanlineStride = dst.getScanlineStride();
/* 197 */     short[][] srcDataArrays = src.getShortDataArrays();
/* 198 */     int[] srcBandOffsets = src.getBandOffsets();
/* 199 */     int srcPixelStride = src.getPixelStride();
/* 200 */     int srcScanlineStride = src.getScanlineStride();
/* 203 */     int wp = filterSize;
/* 204 */     int offset = filterSize / 2;
/* 206 */     for (int k = 0; k < dnumBands; k++) {
/* 207 */       short[] dstData = dstDataArrays[k];
/* 208 */       short[] srcData = srcDataArrays[k];
/* 209 */       int srcScanlineOffset = srcBandOffsets[k];
/* 210 */       int dstScanlineOffset = dstBandOffsets[k];
/* 211 */       for (int j = 0; j < dheight; j++) {
/* 212 */         int srcPixelOffset = srcScanlineOffset;
/* 213 */         int dstPixelOffset = dstScanlineOffset;
/* 215 */         for (int i = 0; i < dwidth; i++) {
/* 216 */           int maxval = Integer.MIN_VALUE;
/* 219 */           int imageOffset = srcPixelOffset + srcPixelStride * offset;
/* 221 */           for (int u = 0; u < wp; u++) {
/* 222 */             int val = srcData[imageOffset] & 0xFFFF;
/* 223 */             imageOffset += srcScanlineStride;
/* 224 */             maxval = (val > maxval) ? val : maxval;
/*     */           } 
/* 228 */           imageOffset = srcPixelOffset + srcScanlineStride * offset;
/* 231 */           for (int v = 0; v < wp; v++) {
/* 232 */             int val = srcData[imageOffset] & 0xFFFF;
/* 233 */             imageOffset += srcPixelStride;
/* 234 */             maxval = (val > maxval) ? val : maxval;
/*     */           } 
/* 237 */           dstData[dstPixelOffset] = (short)maxval;
/* 238 */           srcPixelOffset += srcPixelStride;
/* 239 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/* 241 */         srcScanlineOffset += srcScanlineStride;
/* 242 */         dstScanlineOffset += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void intLoop(RasterAccessor src, RasterAccessor dst, int filterSize) {
/* 250 */     int dwidth = dst.getWidth();
/* 251 */     int dheight = dst.getHeight();
/* 252 */     int dnumBands = dst.getNumBands();
/* 254 */     int[][] dstDataArrays = dst.getIntDataArrays();
/* 255 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 256 */     int dstPixelStride = dst.getPixelStride();
/* 257 */     int dstScanlineStride = dst.getScanlineStride();
/* 259 */     int[][] srcDataArrays = src.getIntDataArrays();
/* 260 */     int[] srcBandOffsets = src.getBandOffsets();
/* 261 */     int srcPixelStride = src.getPixelStride();
/* 262 */     int srcScanlineStride = src.getScanlineStride();
/* 265 */     int wp = filterSize;
/* 266 */     int offset = filterSize / 2;
/* 268 */     for (int k = 0; k < dnumBands; k++) {
/* 269 */       int[] dstData = dstDataArrays[k];
/* 270 */       int[] srcData = srcDataArrays[k];
/* 271 */       int srcScanlineOffset = srcBandOffsets[k];
/* 272 */       int dstScanlineOffset = dstBandOffsets[k];
/* 273 */       for (int j = 0; j < dheight; j++) {
/* 274 */         int srcPixelOffset = srcScanlineOffset;
/* 275 */         int dstPixelOffset = dstScanlineOffset;
/* 277 */         for (int i = 0; i < dwidth; i++) {
/* 278 */           int maxval = Integer.MIN_VALUE;
/* 281 */           int imageOffset = srcPixelOffset + srcPixelStride * offset;
/* 283 */           for (int u = 0; u < wp; u++) {
/* 284 */             int val = srcData[imageOffset];
/* 285 */             imageOffset += srcScanlineStride;
/* 286 */             maxval = (val > maxval) ? val : maxval;
/*     */           } 
/* 290 */           imageOffset = srcPixelOffset + srcScanlineStride * offset;
/* 293 */           for (int v = 0; v < wp; v++) {
/* 294 */             int val = srcData[imageOffset];
/* 295 */             imageOffset += srcPixelStride;
/* 296 */             maxval = (val > maxval) ? val : maxval;
/*     */           } 
/* 298 */           dstData[dstPixelOffset] = maxval;
/* 299 */           srcPixelOffset += srcPixelStride;
/* 300 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/* 302 */         srcScanlineOffset += srcScanlineStride;
/* 303 */         dstScanlineOffset += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void floatLoop(RasterAccessor src, RasterAccessor dst, int filterSize) {
/* 311 */     int dwidth = dst.getWidth();
/* 312 */     int dheight = dst.getHeight();
/* 313 */     int dnumBands = dst.getNumBands();
/* 315 */     float[][] dstDataArrays = dst.getFloatDataArrays();
/* 316 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 317 */     int dstPixelStride = dst.getPixelStride();
/* 318 */     int dstScanlineStride = dst.getScanlineStride();
/* 320 */     float[][] srcDataArrays = src.getFloatDataArrays();
/* 321 */     int[] srcBandOffsets = src.getBandOffsets();
/* 322 */     int srcPixelStride = src.getPixelStride();
/* 323 */     int srcScanlineStride = src.getScanlineStride();
/* 326 */     int wp = filterSize;
/* 327 */     int offset = filterSize / 2;
/* 329 */     for (int k = 0; k < dnumBands; k++) {
/* 330 */       float[] dstData = dstDataArrays[k];
/* 331 */       float[] srcData = srcDataArrays[k];
/* 332 */       int srcScanlineOffset = srcBandOffsets[k];
/* 333 */       int dstScanlineOffset = dstBandOffsets[k];
/* 334 */       for (int j = 0; j < dheight; j++) {
/* 335 */         int srcPixelOffset = srcScanlineOffset;
/* 336 */         int dstPixelOffset = dstScanlineOffset;
/* 338 */         for (int i = 0; i < dwidth; i++) {
/* 339 */           float maxval = -3.4028235E38F;
/* 342 */           int imageOffset = srcPixelOffset + srcPixelStride * offset;
/* 344 */           for (int u = 0; u < wp; u++) {
/* 345 */             float val = srcData[imageOffset];
/* 346 */             imageOffset += srcScanlineStride;
/* 347 */             maxval = (val > maxval) ? val : maxval;
/*     */           } 
/* 351 */           imageOffset = srcPixelOffset + srcScanlineStride * offset;
/* 354 */           for (int v = 0; v < wp; v++) {
/* 355 */             float val = srcData[imageOffset];
/* 356 */             imageOffset += srcPixelStride;
/* 357 */             maxval = (val > maxval) ? val : maxval;
/*     */           } 
/* 360 */           dstData[dstPixelOffset] = maxval;
/* 361 */           srcPixelOffset += srcPixelStride;
/* 362 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/* 364 */         srcScanlineOffset += srcScanlineStride;
/* 365 */         dstScanlineOffset += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void doubleLoop(RasterAccessor src, RasterAccessor dst, int filterSize) {
/* 373 */     int dwidth = dst.getWidth();
/* 374 */     int dheight = dst.getHeight();
/* 375 */     int dnumBands = dst.getNumBands();
/* 377 */     double[][] dstDataArrays = dst.getDoubleDataArrays();
/* 378 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 379 */     int dstPixelStride = dst.getPixelStride();
/* 380 */     int dstScanlineStride = dst.getScanlineStride();
/* 382 */     double[][] srcDataArrays = src.getDoubleDataArrays();
/* 383 */     int[] srcBandOffsets = src.getBandOffsets();
/* 384 */     int srcPixelStride = src.getPixelStride();
/* 385 */     int srcScanlineStride = src.getScanlineStride();
/* 388 */     int wp = filterSize;
/* 389 */     int offset = filterSize / 2;
/* 391 */     for (int k = 0; k < dnumBands; k++) {
/* 392 */       double[] dstData = dstDataArrays[k];
/* 393 */       double[] srcData = srcDataArrays[k];
/* 394 */       int srcScanlineOffset = srcBandOffsets[k];
/* 395 */       int dstScanlineOffset = dstBandOffsets[k];
/* 396 */       for (int j = 0; j < dheight; j++) {
/* 397 */         int srcPixelOffset = srcScanlineOffset;
/* 398 */         int dstPixelOffset = dstScanlineOffset;
/* 400 */         for (int i = 0; i < dwidth; i++) {
/* 401 */           double maxval = -1.7976931348623157E308D;
/* 404 */           int imageOffset = srcPixelOffset + srcPixelStride * offset;
/* 406 */           for (int u = 0; u < wp; u++) {
/* 407 */             double val = srcData[imageOffset];
/* 408 */             imageOffset += srcScanlineStride;
/* 409 */             maxval = (val > maxval) ? val : maxval;
/*     */           } 
/* 413 */           imageOffset = srcPixelOffset + srcScanlineStride * offset;
/* 416 */           for (int v = 0; v < wp; v++) {
/* 417 */             double val = srcData[imageOffset];
/* 418 */             imageOffset += srcPixelStride;
/* 419 */             maxval = (val > maxval) ? val : maxval;
/*     */           } 
/* 422 */           dstData[dstPixelOffset] = maxval;
/* 423 */           srcPixelOffset += srcPixelStride;
/* 424 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/* 426 */         srcScanlineOffset += srcScanlineStride;
/* 427 */         dstScanlineOffset += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\MaxFilterPlusOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */