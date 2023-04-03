/*     */ package com.sun.media.jai.opimage;
/*     */ 
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.util.Map;
/*     */ import javax.media.jai.AreaOpImage;
/*     */ import javax.media.jai.BorderExtender;
/*     */ import javax.media.jai.ImageLayout;
/*     */ import javax.media.jai.KernelJAI;
/*     */ import javax.media.jai.RasterAccessor;
/*     */ import javax.media.jai.RasterFormatTag;
/*     */ 
/*     */ final class ConvolveOpImage extends AreaOpImage {
/*     */   protected KernelJAI kernel;
/*     */   
/*     */   private int kw;
/*     */   
/*     */   private int kh;
/*     */   
/*     */   private int kx;
/*     */   
/*     */   private int ky;
/*     */   
/*     */   public ConvolveOpImage(RenderedImage source, BorderExtender extender, Map config, ImageLayout layout, KernelJAI kernel) {
/* 102 */     super(source, layout, config, true, extender, kernel.getLeftPadding(), kernel.getRightPadding(), kernel.getTopPadding(), kernel.getBottomPadding());
/* 112 */     this.kernel = kernel;
/* 113 */     this.kw = kernel.getWidth();
/* 114 */     this.kh = kernel.getHeight();
/* 115 */     this.kx = kernel.getXOrigin();
/* 116 */     this.ky = kernel.getYOrigin();
/*     */   }
/*     */   
/*     */   protected void computeRect(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/* 132 */     RasterFormatTag[] formatTags = getFormatTags();
/* 134 */     Raster source = sources[0];
/* 135 */     Rectangle srcRect = mapDestRect(destRect, 0);
/* 138 */     RasterAccessor srcAccessor = new RasterAccessor(source, srcRect, formatTags[0], getSourceImage(0).getColorModel());
/* 141 */     RasterAccessor dstAccessor = new RasterAccessor(dest, destRect, formatTags[1], getColorModel());
/* 145 */     switch (dstAccessor.getDataType()) {
/*     */       case 0:
/* 147 */         byteLoop(srcAccessor, dstAccessor);
/*     */         break;
/*     */       case 3:
/* 150 */         intLoop(srcAccessor, dstAccessor);
/*     */         break;
/*     */       case 2:
/* 153 */         shortLoop(srcAccessor, dstAccessor);
/*     */         break;
/*     */       case 1:
/* 156 */         ushortLoop(srcAccessor, dstAccessor);
/*     */         break;
/*     */       case 4:
/* 159 */         floatLoop(srcAccessor, dstAccessor);
/*     */         break;
/*     */       case 5:
/* 162 */         doubleLoop(srcAccessor, dstAccessor);
/*     */         break;
/*     */     } 
/* 171 */     if (dstAccessor.isDataCopy()) {
/* 172 */       dstAccessor.clampDataArrays();
/* 173 */       dstAccessor.copyDataToRaster();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void byteLoop(RasterAccessor src, RasterAccessor dst) {
/* 178 */     int dwidth = dst.getWidth();
/* 179 */     int dheight = dst.getHeight();
/* 180 */     int dnumBands = dst.getNumBands();
/* 182 */     float[] kdata = this.kernel.getKernelData();
/* 183 */     int kw = this.kernel.getWidth();
/* 184 */     int kh = this.kernel.getHeight();
/* 186 */     byte[][] dstDataArrays = dst.getByteDataArrays();
/* 187 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 188 */     int dstPixelStride = dst.getPixelStride();
/* 189 */     int dstScanlineStride = dst.getScanlineStride();
/* 191 */     byte[][] srcDataArrays = src.getByteDataArrays();
/* 192 */     int[] srcBandOffsets = src.getBandOffsets();
/* 193 */     int srcPixelStride = src.getPixelStride();
/* 194 */     int srcScanlineStride = src.getScanlineStride();
/* 196 */     for (int k = 0; k < dnumBands; k++) {
/* 197 */       byte[] dstData = dstDataArrays[k];
/* 198 */       byte[] srcData = srcDataArrays[k];
/* 199 */       int srcScanlineOffset = srcBandOffsets[k];
/* 200 */       int dstScanlineOffset = dstBandOffsets[k];
/* 201 */       for (int j = 0; j < dheight; j++) {
/* 202 */         int srcPixelOffset = srcScanlineOffset;
/* 203 */         int dstPixelOffset = dstScanlineOffset;
/* 205 */         for (int i = 0; i < dwidth; i++) {
/* 206 */           float f = 0.5F;
/* 207 */           int kernelVerticalOffset = 0;
/* 208 */           int imageVerticalOffset = srcPixelOffset;
/* 209 */           for (int u = 0; u < kh; u++) {
/* 210 */             int imageOffset = imageVerticalOffset;
/* 211 */             for (int v = 0; v < kw; v++) {
/* 212 */               f += (srcData[imageOffset] & 0xFF) * kdata[kernelVerticalOffset + v];
/* 214 */               imageOffset += srcPixelStride;
/*     */             } 
/* 216 */             kernelVerticalOffset += kw;
/* 217 */             imageVerticalOffset += srcScanlineStride;
/*     */           } 
/* 220 */           int val = (int)f;
/* 221 */           if (val < 0) {
/* 222 */             val = 0;
/* 223 */           } else if (val > 255) {
/* 224 */             val = 255;
/*     */           } 
/* 226 */           dstData[dstPixelOffset] = (byte)val;
/* 227 */           srcPixelOffset += srcPixelStride;
/* 228 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/* 230 */         srcScanlineOffset += srcScanlineStride;
/* 231 */         dstScanlineOffset += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void shortLoop(RasterAccessor src, RasterAccessor dst) {
/* 237 */     int dwidth = dst.getWidth();
/* 238 */     int dheight = dst.getHeight();
/* 239 */     int dnumBands = dst.getNumBands();
/* 241 */     float[] kdata = this.kernel.getKernelData();
/* 242 */     int kw = this.kernel.getWidth();
/* 243 */     int kh = this.kernel.getHeight();
/* 245 */     short[][] dstDataArrays = dst.getShortDataArrays();
/* 246 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 247 */     int dstPixelStride = dst.getPixelStride();
/* 248 */     int dstScanlineStride = dst.getScanlineStride();
/* 250 */     short[][] srcDataArrays = src.getShortDataArrays();
/* 251 */     int[] srcBandOffsets = src.getBandOffsets();
/* 252 */     int srcPixelStride = src.getPixelStride();
/* 253 */     int srcScanlineStride = src.getScanlineStride();
/* 255 */     for (int k = 0; k < dnumBands; k++) {
/* 256 */       short[] dstData = dstDataArrays[k];
/* 257 */       short[] srcData = srcDataArrays[k];
/* 258 */       int srcScanlineOffset = srcBandOffsets[k];
/* 259 */       int dstScanlineOffset = dstBandOffsets[k];
/* 260 */       for (int j = 0; j < dheight; j++) {
/* 261 */         int srcPixelOffset = srcScanlineOffset;
/* 262 */         int dstPixelOffset = dstScanlineOffset;
/* 264 */         for (int i = 0; i < dwidth; i++) {
/* 265 */           float f = 0.5F;
/* 266 */           int kernelVerticalOffset = 0;
/* 267 */           int imageVerticalOffset = srcPixelOffset;
/* 268 */           for (int u = 0; u < kh; u++) {
/* 269 */             int imageOffset = imageVerticalOffset;
/* 270 */             for (int v = 0; v < kw; v++) {
/* 271 */               f += srcData[imageOffset] * kdata[kernelVerticalOffset + v];
/* 273 */               imageOffset += srcPixelStride;
/*     */             } 
/* 275 */             kernelVerticalOffset += kw;
/* 276 */             imageVerticalOffset += srcScanlineStride;
/*     */           } 
/* 279 */           int val = (int)f;
/* 280 */           if (val < -32768) {
/* 281 */             val = -32768;
/* 282 */           } else if (val > 32767) {
/* 283 */             val = 32767;
/*     */           } 
/* 286 */           dstData[dstPixelOffset] = (short)val;
/* 287 */           srcPixelOffset += srcPixelStride;
/* 288 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/* 290 */         srcScanlineOffset += srcScanlineStride;
/* 291 */         dstScanlineOffset += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void ushortLoop(RasterAccessor src, RasterAccessor dst) {
/* 297 */     int dwidth = dst.getWidth();
/* 298 */     int dheight = dst.getHeight();
/* 299 */     int dnumBands = dst.getNumBands();
/* 301 */     float[] kdata = this.kernel.getKernelData();
/* 302 */     int kw = this.kernel.getWidth();
/* 303 */     int kh = this.kernel.getHeight();
/* 305 */     short[][] dstDataArrays = dst.getShortDataArrays();
/* 306 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 307 */     int dstPixelStride = dst.getPixelStride();
/* 308 */     int dstScanlineStride = dst.getScanlineStride();
/* 310 */     short[][] srcDataArrays = src.getShortDataArrays();
/* 311 */     int[] srcBandOffsets = src.getBandOffsets();
/* 312 */     int srcPixelStride = src.getPixelStride();
/* 313 */     int srcScanlineStride = src.getScanlineStride();
/* 315 */     for (int k = 0; k < dnumBands; k++) {
/* 316 */       short[] dstData = dstDataArrays[k];
/* 317 */       short[] srcData = srcDataArrays[k];
/* 318 */       int srcScanlineOffset = srcBandOffsets[k];
/* 319 */       int dstScanlineOffset = dstBandOffsets[k];
/* 320 */       for (int j = 0; j < dheight; j++) {
/* 321 */         int srcPixelOffset = srcScanlineOffset;
/* 322 */         int dstPixelOffset = dstScanlineOffset;
/* 324 */         for (int i = 0; i < dwidth; i++) {
/* 325 */           float f = 0.5F;
/* 326 */           int kernelVerticalOffset = 0;
/* 327 */           int imageVerticalOffset = srcPixelOffset;
/* 328 */           for (int u = 0; u < kh; u++) {
/* 329 */             int imageOffset = imageVerticalOffset;
/* 330 */             for (int v = 0; v < kw; v++) {
/* 331 */               f += (srcData[imageOffset] & 0xFFFF) * kdata[kernelVerticalOffset + v];
/* 333 */               imageOffset += srcPixelStride;
/*     */             } 
/* 335 */             kernelVerticalOffset += kw;
/* 336 */             imageVerticalOffset += srcScanlineStride;
/*     */           } 
/* 338 */           int val = (int)f;
/* 339 */           if (val < 0) {
/* 340 */             val = 0;
/* 341 */           } else if (val > 65535) {
/* 342 */             val = 65535;
/*     */           } 
/* 345 */           dstData[dstPixelOffset] = (short)val;
/* 346 */           srcPixelOffset += srcPixelStride;
/* 347 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/* 349 */         srcScanlineOffset += srcScanlineStride;
/* 350 */         dstScanlineOffset += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void intLoop(RasterAccessor src, RasterAccessor dst) {
/* 356 */     int dwidth = dst.getWidth();
/* 357 */     int dheight = dst.getHeight();
/* 358 */     int dnumBands = dst.getNumBands();
/* 360 */     float[] kdata = this.kernel.getKernelData();
/* 361 */     int kw = this.kernel.getWidth();
/* 362 */     int kh = this.kernel.getHeight();
/* 364 */     int[][] dstDataArrays = dst.getIntDataArrays();
/* 365 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 366 */     int dstPixelStride = dst.getPixelStride();
/* 367 */     int dstScanlineStride = dst.getScanlineStride();
/* 369 */     int[][] srcDataArrays = src.getIntDataArrays();
/* 370 */     int[] srcBandOffsets = src.getBandOffsets();
/* 371 */     int srcPixelStride = src.getPixelStride();
/* 372 */     int srcScanlineStride = src.getScanlineStride();
/* 374 */     for (int k = 0; k < dnumBands; k++) {
/* 375 */       int[] dstData = dstDataArrays[k];
/* 376 */       int[] srcData = srcDataArrays[k];
/* 377 */       int srcScanlineOffset = srcBandOffsets[k];
/* 378 */       int dstScanlineOffset = dstBandOffsets[k];
/* 379 */       for (int j = 0; j < dheight; j++) {
/* 380 */         int srcPixelOffset = srcScanlineOffset;
/* 381 */         int dstPixelOffset = dstScanlineOffset;
/* 383 */         for (int i = 0; i < dwidth; i++) {
/* 384 */           float f = 0.5F;
/* 385 */           int kernelVerticalOffset = 0;
/* 386 */           int imageVerticalOffset = srcPixelOffset;
/* 387 */           for (int u = 0; u < kh; u++) {
/* 388 */             int imageOffset = imageVerticalOffset;
/* 389 */             for (int v = 0; v < kw; v++) {
/* 390 */               f += srcData[imageOffset] * kdata[kernelVerticalOffset + v];
/* 392 */               imageOffset += srcPixelStride;
/*     */             } 
/* 394 */             kernelVerticalOffset += kw;
/* 395 */             imageVerticalOffset += srcScanlineStride;
/*     */           } 
/* 398 */           dstData[dstPixelOffset] = (int)f;
/* 399 */           srcPixelOffset += srcPixelStride;
/* 400 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/* 402 */         srcScanlineOffset += srcScanlineStride;
/* 403 */         dstScanlineOffset += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void floatLoop(RasterAccessor src, RasterAccessor dst) {
/* 409 */     int dwidth = dst.getWidth();
/* 410 */     int dheight = dst.getHeight();
/* 411 */     int dnumBands = dst.getNumBands();
/* 413 */     float[] kdata = this.kernel.getKernelData();
/* 414 */     int kw = this.kernel.getWidth();
/* 415 */     int kh = this.kernel.getHeight();
/* 417 */     float[][] dstDataArrays = dst.getFloatDataArrays();
/* 418 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 419 */     int dstPixelStride = dst.getPixelStride();
/* 420 */     int dstScanlineStride = dst.getScanlineStride();
/* 422 */     float[][] srcDataArrays = src.getFloatDataArrays();
/* 423 */     int[] srcBandOffsets = src.getBandOffsets();
/* 424 */     int srcPixelStride = src.getPixelStride();
/* 425 */     int srcScanlineStride = src.getScanlineStride();
/* 427 */     for (int k = 0; k < dnumBands; k++) {
/* 428 */       float[] dstData = dstDataArrays[k];
/* 429 */       float[] srcData = srcDataArrays[k];
/* 430 */       int srcScanlineOffset = srcBandOffsets[k];
/* 431 */       int dstScanlineOffset = dstBandOffsets[k];
/* 432 */       for (int j = 0; j < dheight; j++) {
/* 433 */         int srcPixelOffset = srcScanlineOffset;
/* 434 */         int dstPixelOffset = dstScanlineOffset;
/* 436 */         for (int i = 0; i < dwidth; i++) {
/* 437 */           float f = 0.0F;
/* 438 */           int kernelVerticalOffset = 0;
/* 439 */           int imageVerticalOffset = srcPixelOffset;
/* 440 */           for (int u = 0; u < kh; u++) {
/* 441 */             int imageOffset = imageVerticalOffset;
/* 442 */             for (int v = 0; v < kw; v++) {
/* 443 */               f += srcData[imageOffset] * kdata[kernelVerticalOffset + v];
/* 445 */               imageOffset += srcPixelStride;
/*     */             } 
/* 447 */             kernelVerticalOffset += kw;
/* 448 */             imageVerticalOffset += srcScanlineStride;
/*     */           } 
/* 451 */           dstData[dstPixelOffset] = f;
/* 452 */           srcPixelOffset += srcPixelStride;
/* 453 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/* 455 */         srcScanlineOffset += srcScanlineStride;
/* 456 */         dstScanlineOffset += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void doubleLoop(RasterAccessor src, RasterAccessor dst) {
/* 462 */     int dwidth = dst.getWidth();
/* 463 */     int dheight = dst.getHeight();
/* 464 */     int dnumBands = dst.getNumBands();
/* 466 */     float[] kdata = this.kernel.getKernelData();
/* 467 */     int kw = this.kernel.getWidth();
/* 468 */     int kh = this.kernel.getHeight();
/* 470 */     double[][] dstDataArrays = dst.getDoubleDataArrays();
/* 471 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 472 */     int dstPixelStride = dst.getPixelStride();
/* 473 */     int dstScanlineStride = dst.getScanlineStride();
/* 475 */     double[][] srcDataArrays = src.getDoubleDataArrays();
/* 476 */     int[] srcBandOffsets = src.getBandOffsets();
/* 477 */     int srcPixelStride = src.getPixelStride();
/* 478 */     int srcScanlineStride = src.getScanlineStride();
/* 480 */     for (int k = 0; k < dnumBands; k++) {
/* 481 */       double[] dstData = dstDataArrays[k];
/* 482 */       double[] srcData = srcDataArrays[k];
/* 483 */       int srcScanlineOffset = srcBandOffsets[k];
/* 484 */       int dstScanlineOffset = dstBandOffsets[k];
/* 485 */       for (int j = 0; j < dheight; j++) {
/* 486 */         int srcPixelOffset = srcScanlineOffset;
/* 487 */         int dstPixelOffset = dstScanlineOffset;
/* 489 */         for (int i = 0; i < dwidth; i++) {
/* 490 */           double f = 0.5D;
/* 491 */           int kernelVerticalOffset = 0;
/* 492 */           int imageVerticalOffset = srcPixelOffset;
/* 493 */           for (int u = 0; u < kh; u++) {
/* 494 */             int imageOffset = imageVerticalOffset;
/* 495 */             for (int v = 0; v < kw; v++) {
/* 496 */               f += srcData[imageOffset] * kdata[kernelVerticalOffset + v];
/* 498 */               imageOffset += srcPixelStride;
/*     */             } 
/* 500 */             kernelVerticalOffset += kw;
/* 501 */             imageVerticalOffset += srcScanlineStride;
/*     */           } 
/* 504 */           dstData[dstPixelOffset] = f;
/* 505 */           srcPixelOffset += srcPixelStride;
/* 506 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/* 508 */         srcScanlineOffset += srcScanlineStride;
/* 509 */         dstScanlineOffset += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\ConvolveOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */