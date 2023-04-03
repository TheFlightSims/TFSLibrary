/*     */ package com.sun.media.jai.opimage;
/*     */ 
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.util.Map;
/*     */ import javax.media.jai.BorderExtender;
/*     */ import javax.media.jai.ImageLayout;
/*     */ import javax.media.jai.RasterAccessor;
/*     */ import javax.media.jai.operator.MedianFilterDescriptor;
/*     */ 
/*     */ final class MedianFilterSquareOpImage extends MedianFilterOpImage {
/*     */   public MedianFilterSquareOpImage(RenderedImage source, BorderExtender extender, Map config, ImageLayout layout, int maskSize) {
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
/*  80 */     int[] values = new int[filterSize * filterSize];
/*  81 */     int wp = filterSize;
/*  83 */     for (int k = 0; k < dnumBands; k++) {
/*  84 */       byte[] dstData = dstDataArrays[k];
/*  85 */       byte[] srcData = srcDataArrays[k];
/*  86 */       int srcScanlineOffset = srcBandOffsets[k];
/*  87 */       int dstScanlineOffset = dstBandOffsets[k];
/*  88 */       for (int j = 0; j < dheight; j++) {
/*  89 */         int srcPixelOffset = srcScanlineOffset;
/*  90 */         int dstPixelOffset = dstScanlineOffset;
/*  92 */         for (int i = 0; i < dwidth; i++) {
/*  93 */           int imageVerticalOffset = srcPixelOffset;
/*  95 */           int valueCount = 0;
/*  96 */           for (int u = 0; u < wp; u++) {
/*  97 */             int imageOffset = imageVerticalOffset;
/*  98 */             for (int v = 0; v < wp; v++) {
/*  99 */               values[valueCount++] = srcData[imageOffset] & 0xFF;
/* 101 */               imageOffset += srcPixelStride;
/*     */             } 
/* 103 */             imageVerticalOffset += srcScanlineStride;
/*     */           } 
/* 105 */           int val = medianFilter(values);
/* 107 */           dstData[dstPixelOffset] = (byte)val;
/* 108 */           srcPixelOffset += srcPixelStride;
/* 109 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/* 111 */         srcScanlineOffset += srcScanlineStride;
/* 112 */         dstScanlineOffset += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void shortLoop(RasterAccessor src, RasterAccessor dst, int filterSize) {
/* 120 */     int dwidth = dst.getWidth();
/* 121 */     int dheight = dst.getHeight();
/* 122 */     int dnumBands = dst.getNumBands();
/* 124 */     short[][] dstDataArrays = dst.getShortDataArrays();
/* 125 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 126 */     int dstPixelStride = dst.getPixelStride();
/* 127 */     int dstScanlineStride = dst.getScanlineStride();
/* 129 */     short[][] srcDataArrays = src.getShortDataArrays();
/* 130 */     int[] srcBandOffsets = src.getBandOffsets();
/* 131 */     int srcPixelStride = src.getPixelStride();
/* 132 */     int srcScanlineStride = src.getScanlineStride();
/* 134 */     int[] values = new int[filterSize * filterSize];
/* 135 */     int wp = filterSize;
/* 137 */     for (int k = 0; k < dnumBands; k++) {
/* 138 */       short[] dstData = dstDataArrays[k];
/* 139 */       short[] srcData = srcDataArrays[k];
/* 140 */       int srcScanlineOffset = srcBandOffsets[k];
/* 141 */       int dstScanlineOffset = dstBandOffsets[k];
/* 142 */       for (int j = 0; j < dheight; j++) {
/* 143 */         int srcPixelOffset = srcScanlineOffset;
/* 144 */         int dstPixelOffset = dstScanlineOffset;
/* 146 */         for (int i = 0; i < dwidth; i++) {
/* 147 */           int imageVerticalOffset = srcPixelOffset;
/* 149 */           int valueCount = 0;
/* 150 */           for (int u = 0; u < wp; u++) {
/* 151 */             int imageOffset = imageVerticalOffset;
/* 152 */             for (int v = 0; v < wp; v++) {
/* 153 */               values[valueCount++] = srcData[imageOffset];
/* 155 */               imageOffset += srcPixelStride;
/*     */             } 
/* 157 */             imageVerticalOffset += srcScanlineStride;
/*     */           } 
/* 159 */           int val = medianFilter(values);
/* 161 */           dstData[dstPixelOffset] = (short)val;
/* 162 */           srcPixelOffset += srcPixelStride;
/* 163 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/* 165 */         srcScanlineOffset += srcScanlineStride;
/* 166 */         dstScanlineOffset += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void ushortLoop(RasterAccessor src, RasterAccessor dst, int filterSize) {
/* 174 */     int dwidth = dst.getWidth();
/* 175 */     int dheight = dst.getHeight();
/* 176 */     int dnumBands = dst.getNumBands();
/* 178 */     short[][] dstDataArrays = dst.getShortDataArrays();
/* 179 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 180 */     int dstPixelStride = dst.getPixelStride();
/* 181 */     int dstScanlineStride = dst.getScanlineStride();
/* 183 */     short[][] srcDataArrays = src.getShortDataArrays();
/* 184 */     int[] srcBandOffsets = src.getBandOffsets();
/* 185 */     int srcPixelStride = src.getPixelStride();
/* 186 */     int srcScanlineStride = src.getScanlineStride();
/* 188 */     int[] values = new int[filterSize * filterSize];
/* 189 */     int wp = filterSize;
/* 191 */     for (int k = 0; k < dnumBands; k++) {
/* 192 */       short[] dstData = dstDataArrays[k];
/* 193 */       short[] srcData = srcDataArrays[k];
/* 194 */       int srcScanlineOffset = srcBandOffsets[k];
/* 195 */       int dstScanlineOffset = dstBandOffsets[k];
/* 196 */       for (int j = 0; j < dheight; j++) {
/* 197 */         int srcPixelOffset = srcScanlineOffset;
/* 198 */         int dstPixelOffset = dstScanlineOffset;
/* 200 */         for (int i = 0; i < dwidth; i++) {
/* 201 */           int imageVerticalOffset = srcPixelOffset;
/* 203 */           int valueCount = 0;
/* 204 */           for (int u = 0; u < wp; u++) {
/* 205 */             int imageOffset = imageVerticalOffset;
/* 206 */             for (int v = 0; v < wp; v++) {
/* 207 */               values[valueCount++] = srcData[imageOffset] & 0xFFFF;
/* 209 */               imageOffset += srcPixelStride;
/*     */             } 
/* 211 */             imageVerticalOffset += srcScanlineStride;
/*     */           } 
/* 213 */           int val = medianFilter(values);
/* 215 */           dstData[dstPixelOffset] = (short)val;
/* 216 */           srcPixelOffset += srcPixelStride;
/* 217 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/* 219 */         srcScanlineOffset += srcScanlineStride;
/* 220 */         dstScanlineOffset += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void intLoop(RasterAccessor src, RasterAccessor dst, int filterSize) {
/* 228 */     int dwidth = dst.getWidth();
/* 229 */     int dheight = dst.getHeight();
/* 230 */     int dnumBands = dst.getNumBands();
/* 232 */     int[][] dstDataArrays = dst.getIntDataArrays();
/* 233 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 234 */     int dstPixelStride = dst.getPixelStride();
/* 235 */     int dstScanlineStride = dst.getScanlineStride();
/* 237 */     int[][] srcDataArrays = src.getIntDataArrays();
/* 238 */     int[] srcBandOffsets = src.getBandOffsets();
/* 239 */     int srcPixelStride = src.getPixelStride();
/* 240 */     int srcScanlineStride = src.getScanlineStride();
/* 242 */     int[] values = new int[filterSize * filterSize];
/* 243 */     int wp = filterSize;
/* 245 */     for (int k = 0; k < dnumBands; k++) {
/* 246 */       int[] dstData = dstDataArrays[k];
/* 247 */       int[] srcData = srcDataArrays[k];
/* 248 */       int srcScanlineOffset = srcBandOffsets[k];
/* 249 */       int dstScanlineOffset = dstBandOffsets[k];
/* 250 */       for (int j = 0; j < dheight; j++) {
/* 251 */         int srcPixelOffset = srcScanlineOffset;
/* 252 */         int dstPixelOffset = dstScanlineOffset;
/* 254 */         for (int i = 0; i < dwidth; i++) {
/* 255 */           int imageVerticalOffset = srcPixelOffset;
/* 257 */           int valueCount = 0;
/* 258 */           for (int u = 0; u < wp; u++) {
/* 259 */             int imageOffset = imageVerticalOffset;
/* 260 */             for (int v = 0; v < wp; v++) {
/* 261 */               values[valueCount++] = srcData[imageOffset];
/* 263 */               imageOffset += srcPixelStride;
/*     */             } 
/* 265 */             imageVerticalOffset += srcScanlineStride;
/*     */           } 
/* 267 */           int val = medianFilter(values);
/* 269 */           dstData[dstPixelOffset] = val;
/* 270 */           srcPixelOffset += srcPixelStride;
/* 271 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/* 273 */         srcScanlineOffset += srcScanlineStride;
/* 274 */         dstScanlineOffset += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void floatLoop(RasterAccessor src, RasterAccessor dst, int filterSize) {
/* 282 */     int dwidth = dst.getWidth();
/* 283 */     int dheight = dst.getHeight();
/* 284 */     int dnumBands = dst.getNumBands();
/* 286 */     float[][] dstDataArrays = dst.getFloatDataArrays();
/* 287 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 288 */     int dstPixelStride = dst.getPixelStride();
/* 289 */     int dstScanlineStride = dst.getScanlineStride();
/* 291 */     float[][] srcDataArrays = src.getFloatDataArrays();
/* 292 */     int[] srcBandOffsets = src.getBandOffsets();
/* 293 */     int srcPixelStride = src.getPixelStride();
/* 294 */     int srcScanlineStride = src.getScanlineStride();
/* 296 */     float[] values = new float[filterSize * filterSize];
/* 297 */     int wp = filterSize;
/* 299 */     for (int k = 0; k < dnumBands; k++) {
/* 300 */       float[] dstData = dstDataArrays[k];
/* 301 */       float[] srcData = srcDataArrays[k];
/* 302 */       int srcScanlineOffset = srcBandOffsets[k];
/* 303 */       int dstScanlineOffset = dstBandOffsets[k];
/* 304 */       for (int j = 0; j < dheight; j++) {
/* 305 */         int srcPixelOffset = srcScanlineOffset;
/* 306 */         int dstPixelOffset = dstScanlineOffset;
/* 308 */         for (int i = 0; i < dwidth; i++) {
/* 309 */           int imageVerticalOffset = srcPixelOffset;
/* 311 */           int valueCount = 0;
/* 312 */           for (int u = 0; u < wp; u++) {
/* 313 */             int imageOffset = imageVerticalOffset;
/* 314 */             for (int v = 0; v < wp; v++) {
/* 315 */               values[valueCount++] = srcData[imageOffset];
/* 317 */               imageOffset += srcPixelStride;
/*     */             } 
/* 319 */             imageVerticalOffset += srcScanlineStride;
/*     */           } 
/* 321 */           float val = medianFilterFloat(values);
/* 323 */           dstData[dstPixelOffset] = val;
/* 324 */           srcPixelOffset += srcPixelStride;
/* 325 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/* 327 */         srcScanlineOffset += srcScanlineStride;
/* 328 */         dstScanlineOffset += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void doubleLoop(RasterAccessor src, RasterAccessor dst, int filterSize) {
/* 336 */     int dwidth = dst.getWidth();
/* 337 */     int dheight = dst.getHeight();
/* 338 */     int dnumBands = dst.getNumBands();
/* 340 */     double[][] dstDataArrays = dst.getDoubleDataArrays();
/* 341 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 342 */     int dstPixelStride = dst.getPixelStride();
/* 343 */     int dstScanlineStride = dst.getScanlineStride();
/* 345 */     double[][] srcDataArrays = src.getDoubleDataArrays();
/* 346 */     int[] srcBandOffsets = src.getBandOffsets();
/* 347 */     int srcPixelStride = src.getPixelStride();
/* 348 */     int srcScanlineStride = src.getScanlineStride();
/* 350 */     double[] values = new double[filterSize * filterSize];
/* 351 */     int wp = filterSize;
/* 353 */     for (int k = 0; k < dnumBands; k++) {
/* 354 */       double[] dstData = dstDataArrays[k];
/* 355 */       double[] srcData = srcDataArrays[k];
/* 356 */       int srcScanlineOffset = srcBandOffsets[k];
/* 357 */       int dstScanlineOffset = dstBandOffsets[k];
/* 358 */       for (int j = 0; j < dheight; j++) {
/* 359 */         int srcPixelOffset = srcScanlineOffset;
/* 360 */         int dstPixelOffset = dstScanlineOffset;
/* 362 */         for (int i = 0; i < dwidth; i++) {
/* 363 */           int imageVerticalOffset = srcPixelOffset;
/* 365 */           int valueCount = 0;
/* 366 */           for (int u = 0; u < wp; u++) {
/* 367 */             int imageOffset = imageVerticalOffset;
/* 368 */             for (int v = 0; v < wp; v++) {
/* 369 */               values[valueCount++] = srcData[imageOffset];
/* 371 */               imageOffset += srcPixelStride;
/*     */             } 
/* 373 */             imageVerticalOffset += srcScanlineStride;
/*     */           } 
/* 375 */           double val = medianFilterDouble(values);
/* 377 */           dstData[dstPixelOffset] = val;
/* 378 */           srcPixelOffset += srcPixelStride;
/* 379 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/* 381 */         srcScanlineOffset += srcScanlineStride;
/* 382 */         dstScanlineOffset += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\MedianFilterSquareOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */