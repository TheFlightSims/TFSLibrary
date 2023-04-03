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
/*     */ final class GradientOpImage extends AreaOpImage {
/*     */   protected KernelJAI kernel_h;
/*     */   
/*     */   protected KernelJAI kernel_v;
/*     */   
/*     */   private int kw;
/*     */   
/*     */   private int kh;
/*     */   
/*     */   public GradientOpImage(RenderedImage source, BorderExtender extender, Map config, ImageLayout layout, KernelJAI kernel_h, KernelJAI kernel_v) {
/*  69 */     super(source, layout, config, true, extender, kernel_h.getLeftPadding(), kernel_h.getRightPadding(), kernel_h.getTopPadding(), kernel_h.getBottomPadding());
/*  80 */     this.kernel_h = kernel_h;
/*  81 */     this.kernel_v = kernel_v;
/*  87 */     this.kw = kernel_h.getWidth();
/*  88 */     this.kh = kernel_h.getHeight();
/*     */   }
/*     */   
/*     */   protected void computeRect(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/* 104 */     RasterFormatTag[] formatTags = getFormatTags();
/* 106 */     Raster source = sources[0];
/* 107 */     Rectangle srcRect = mapDestRect(destRect, 0);
/* 109 */     RasterAccessor srcAccessor = new RasterAccessor(source, srcRect, formatTags[0], getSourceImage(0).getColorModel());
/* 113 */     RasterAccessor dstAccessor = new RasterAccessor(dest, destRect, formatTags[1], getColorModel());
/* 118 */     switch (dstAccessor.getDataType()) {
/*     */       case 0:
/* 120 */         byteLoop(srcAccessor, dstAccessor);
/*     */         break;
/*     */       case 3:
/* 123 */         intLoop(srcAccessor, dstAccessor);
/*     */         break;
/*     */       case 2:
/* 126 */         shortLoop(srcAccessor, dstAccessor);
/*     */         break;
/*     */       case 1:
/* 129 */         ushortLoop(srcAccessor, dstAccessor);
/*     */         break;
/*     */       case 4:
/* 132 */         floatLoop(srcAccessor, dstAccessor);
/*     */         break;
/*     */       case 5:
/* 135 */         doubleLoop(srcAccessor, dstAccessor);
/*     */         break;
/*     */     } 
/* 144 */     if (dstAccessor.isDataCopy()) {
/* 145 */       dstAccessor.clampDataArrays();
/* 146 */       dstAccessor.copyDataToRaster();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void byteLoop(RasterAccessor src, RasterAccessor dst) {
/* 151 */     int dwidth = dst.getWidth();
/* 152 */     int dheight = dst.getHeight();
/* 153 */     int dnumBands = dst.getNumBands();
/* 155 */     float[] kdata_h = this.kernel_h.getKernelData();
/* 156 */     float[] kdata_v = this.kernel_v.getKernelData();
/* 158 */     byte[][] dstDataArrays = dst.getByteDataArrays();
/* 159 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 160 */     int dstPixelStride = dst.getPixelStride();
/* 161 */     int dstScanlineStride = dst.getScanlineStride();
/* 163 */     byte[][] srcDataArrays = src.getByteDataArrays();
/* 164 */     int[] srcBandOffsets = src.getBandOffsets();
/* 165 */     int srcPixelStride = src.getPixelStride();
/* 166 */     int srcScanlineStride = src.getScanlineStride();
/* 168 */     for (int k = 0; k < dnumBands; k++) {
/* 169 */       byte[] dstData = dstDataArrays[k];
/* 170 */       byte[] srcData = srcDataArrays[k];
/* 172 */       int srcScanlineOffset = srcBandOffsets[k];
/* 173 */       int dstScanlineOffset = dstBandOffsets[k];
/* 175 */       for (int j = 0; j < dheight; j++) {
/* 176 */         int srcPixelOffset = srcScanlineOffset;
/* 177 */         int dstPixelOffset = dstScanlineOffset;
/* 179 */         for (int i = 0; i < dwidth; i++) {
/* 180 */           float f_h = 0.0F;
/* 181 */           float f_v = 0.0F;
/* 183 */           int kernelVerticalOffset = 0;
/* 184 */           int imageVerticalOffset = srcPixelOffset;
/* 186 */           for (int u = 0; u < this.kh; u++) {
/* 187 */             int imageOffset = imageVerticalOffset;
/* 189 */             for (int v = 0; v < this.kw; v++) {
/* 191 */               f_h += (srcData[imageOffset] & 0xFF) * kdata_h[kernelVerticalOffset + v];
/* 193 */               f_v += (srcData[imageOffset] & 0xFF) * kdata_v[kernelVerticalOffset + v];
/* 196 */               imageOffset += srcPixelStride;
/*     */             } 
/* 199 */             kernelVerticalOffset += this.kw;
/* 200 */             imageVerticalOffset += srcScanlineStride;
/*     */           } 
/* 204 */           float sqr_f_h = f_h * f_h;
/* 205 */           float sqr_f_v = f_v * f_v;
/* 206 */           float result = (float)Math.sqrt((sqr_f_h + sqr_f_v));
/* 208 */           int val = (int)(result + 0.5F);
/* 209 */           if (val < 0) {
/* 210 */             val = 0;
/* 211 */           } else if (val > 255) {
/* 212 */             val = 255;
/*     */           } 
/* 214 */           dstData[dstPixelOffset] = (byte)val;
/* 215 */           srcPixelOffset += srcPixelStride;
/* 216 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/* 218 */         srcScanlineOffset += srcScanlineStride;
/* 219 */         dstScanlineOffset += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void shortLoop(RasterAccessor src, RasterAccessor dst) {
/* 225 */     int dwidth = dst.getWidth();
/* 226 */     int dheight = dst.getHeight();
/* 227 */     int dnumBands = dst.getNumBands();
/* 229 */     float[] kdata_h = this.kernel_h.getKernelData();
/* 230 */     float[] kdata_v = this.kernel_v.getKernelData();
/* 232 */     short[][] dstDataArrays = dst.getShortDataArrays();
/* 233 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 234 */     int dstPixelStride = dst.getPixelStride();
/* 235 */     int dstScanlineStride = dst.getScanlineStride();
/* 237 */     short[][] srcDataArrays = src.getShortDataArrays();
/* 238 */     int[] srcBandOffsets = src.getBandOffsets();
/* 239 */     int srcPixelStride = src.getPixelStride();
/* 240 */     int srcScanlineStride = src.getScanlineStride();
/* 242 */     for (int k = 0; k < dnumBands; k++) {
/* 243 */       short[] dstData = dstDataArrays[k];
/* 244 */       short[] srcData = srcDataArrays[k];
/* 245 */       int srcScanlineOffset = srcBandOffsets[k];
/* 246 */       int dstScanlineOffset = dstBandOffsets[k];
/* 247 */       for (int j = 0; j < dheight; j++) {
/* 248 */         int srcPixelOffset = srcScanlineOffset;
/* 249 */         int dstPixelOffset = dstScanlineOffset;
/* 250 */         for (int i = 0; i < dwidth; i++) {
/* 251 */           float f_h = 0.0F;
/* 252 */           float f_v = 0.0F;
/* 253 */           int kernelVerticalOffset = 0;
/* 254 */           int imageVerticalOffset = srcPixelOffset;
/* 255 */           for (int u = 0; u < this.kh; u++) {
/* 256 */             int imageOffset = imageVerticalOffset;
/* 257 */             for (int v = 0; v < this.kw; v++) {
/* 258 */               f_h += srcData[imageOffset] * kdata_h[kernelVerticalOffset + v];
/* 260 */               f_v += srcData[imageOffset] * kdata_v[kernelVerticalOffset + v];
/* 262 */               imageOffset += srcPixelStride;
/*     */             } 
/* 264 */             kernelVerticalOffset += this.kw;
/* 265 */             imageVerticalOffset += srcScanlineStride;
/*     */           } 
/* 269 */           float sqr_f_h = f_h * f_h;
/* 270 */           float sqr_f_v = f_v * f_v;
/* 271 */           float result = (float)Math.sqrt((sqr_f_h + sqr_f_v));
/* 273 */           int val = (int)(result + 0.5F);
/* 274 */           if (val < -32768) {
/* 275 */             val = -32768;
/* 276 */           } else if (val > 32767) {
/* 277 */             val = 32767;
/*     */           } 
/* 280 */           dstData[dstPixelOffset] = (short)val;
/* 281 */           srcPixelOffset += srcPixelStride;
/* 282 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/* 284 */         srcScanlineOffset += srcScanlineStride;
/* 285 */         dstScanlineOffset += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void ushortLoop(RasterAccessor src, RasterAccessor dst) {
/* 291 */     int dwidth = dst.getWidth();
/* 292 */     int dheight = dst.getHeight();
/* 293 */     int dnumBands = dst.getNumBands();
/* 295 */     float[] kdata_h = this.kernel_h.getKernelData();
/* 296 */     float[] kdata_v = this.kernel_v.getKernelData();
/* 298 */     short[][] dstDataArrays = dst.getShortDataArrays();
/* 299 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 300 */     int dstPixelStride = dst.getPixelStride();
/* 301 */     int dstScanlineStride = dst.getScanlineStride();
/* 303 */     short[][] srcDataArrays = src.getShortDataArrays();
/* 304 */     int[] srcBandOffsets = src.getBandOffsets();
/* 305 */     int srcPixelStride = src.getPixelStride();
/* 306 */     int srcScanlineStride = src.getScanlineStride();
/* 308 */     for (int k = 0; k < dnumBands; k++) {
/* 309 */       short[] dstData = dstDataArrays[k];
/* 310 */       short[] srcData = srcDataArrays[k];
/* 311 */       int srcScanlineOffset = srcBandOffsets[k];
/* 312 */       int dstScanlineOffset = dstBandOffsets[k];
/* 313 */       for (int j = 0; j < dheight; j++) {
/* 314 */         int srcPixelOffset = srcScanlineOffset;
/* 315 */         int dstPixelOffset = dstScanlineOffset;
/* 316 */         for (int i = 0; i < dwidth; i++) {
/* 317 */           float f_h = 0.0F;
/* 318 */           float f_v = 0.0F;
/* 319 */           int kernelVerticalOffset = 0;
/* 320 */           int imageVerticalOffset = srcPixelOffset;
/* 321 */           for (int u = 0; u < this.kh; u++) {
/* 322 */             int imageOffset = imageVerticalOffset;
/* 323 */             for (int v = 0; v < this.kw; v++) {
/* 324 */               f_h += (srcData[imageOffset] & 0xFFFF) * kdata_h[kernelVerticalOffset + v];
/* 326 */               f_v += (srcData[imageOffset] & 0xFFFF) * kdata_v[kernelVerticalOffset + v];
/* 328 */               imageOffset += srcPixelStride;
/*     */             } 
/* 330 */             kernelVerticalOffset += this.kw;
/* 331 */             imageVerticalOffset += srcScanlineStride;
/*     */           } 
/* 335 */           float sqr_f_h = f_h * f_h;
/* 336 */           float sqr_f_v = f_v * f_v;
/* 337 */           float result = (float)Math.sqrt((sqr_f_h + sqr_f_v));
/* 339 */           int val = (int)(result + 0.5F);
/* 340 */           if (val < 0) {
/* 341 */             val = 0;
/* 342 */           } else if (val > 65535) {
/* 343 */             val = 65535;
/*     */           } 
/* 346 */           dstData[dstPixelOffset] = (short)val;
/* 347 */           srcPixelOffset += srcPixelStride;
/* 348 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/* 350 */         srcScanlineOffset += srcScanlineStride;
/* 351 */         dstScanlineOffset += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void intLoop(RasterAccessor src, RasterAccessor dst) {
/* 357 */     int dwidth = dst.getWidth();
/* 358 */     int dheight = dst.getHeight();
/* 359 */     int dnumBands = dst.getNumBands();
/* 361 */     float[] kdata_h = this.kernel_h.getKernelData();
/* 362 */     float[] kdata_v = this.kernel_v.getKernelData();
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
/* 382 */         for (int i = 0; i < dwidth; i++) {
/* 383 */           float f_h = 0.0F;
/* 384 */           float f_v = 0.0F;
/* 385 */           int kernelVerticalOffset = 0;
/* 386 */           int imageVerticalOffset = srcPixelOffset;
/* 387 */           for (int u = 0; u < this.kh; u++) {
/* 388 */             int imageOffset = imageVerticalOffset;
/* 389 */             for (int v = 0; v < this.kw; v++) {
/* 390 */               f_h += srcData[imageOffset] * kdata_h[kernelVerticalOffset + v];
/* 392 */               f_v += srcData[imageOffset] * kdata_v[kernelVerticalOffset + v];
/* 394 */               imageOffset += srcPixelStride;
/*     */             } 
/* 396 */             kernelVerticalOffset += this.kw;
/* 397 */             imageVerticalOffset += srcScanlineStride;
/*     */           } 
/* 401 */           float sqr_f_h = f_h * f_h;
/* 402 */           float sqr_f_v = f_v * f_v;
/* 403 */           float result = (float)Math.sqrt((sqr_f_h + sqr_f_v));
/* 405 */           dstData[dstPixelOffset] = (int)(result + 0.5F);
/* 406 */           srcPixelOffset += srcPixelStride;
/* 407 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/* 409 */         srcScanlineOffset += srcScanlineStride;
/* 410 */         dstScanlineOffset += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void floatLoop(RasterAccessor src, RasterAccessor dst) {
/* 416 */     int dwidth = dst.getWidth();
/* 417 */     int dheight = dst.getHeight();
/* 418 */     int dnumBands = dst.getNumBands();
/* 420 */     float[] kdata_h = this.kernel_h.getKernelData();
/* 421 */     float[] kdata_v = this.kernel_v.getKernelData();
/* 423 */     float[][] dstDataArrays = dst.getFloatDataArrays();
/* 424 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 425 */     int dstPixelStride = dst.getPixelStride();
/* 426 */     int dstScanlineStride = dst.getScanlineStride();
/* 428 */     float[][] srcDataArrays = src.getFloatDataArrays();
/* 429 */     int[] srcBandOffsets = src.getBandOffsets();
/* 430 */     int srcPixelStride = src.getPixelStride();
/* 431 */     int srcScanlineStride = src.getScanlineStride();
/* 433 */     for (int k = 0; k < dnumBands; k++) {
/* 434 */       float[] dstData = dstDataArrays[k];
/* 435 */       float[] srcData = srcDataArrays[k];
/* 436 */       int srcScanlineOffset = srcBandOffsets[k];
/* 437 */       int dstScanlineOffset = dstBandOffsets[k];
/* 438 */       for (int j = 0; j < dheight; j++) {
/* 439 */         int srcPixelOffset = srcScanlineOffset;
/* 440 */         int dstPixelOffset = dstScanlineOffset;
/* 441 */         for (int i = 0; i < dwidth; i++) {
/* 442 */           float f_h = 0.0F;
/* 443 */           float f_v = 0.0F;
/* 444 */           int kernelVerticalOffset = 0;
/* 445 */           int imageVerticalOffset = srcPixelOffset;
/* 446 */           for (int u = 0; u < this.kh; u++) {
/* 447 */             int imageOffset = imageVerticalOffset;
/* 448 */             for (int v = 0; v < this.kw; v++) {
/* 449 */               f_h += srcData[imageOffset] * kdata_h[kernelVerticalOffset + v];
/* 451 */               f_v += srcData[imageOffset] * kdata_v[kernelVerticalOffset + v];
/* 453 */               imageOffset += srcPixelStride;
/*     */             } 
/* 455 */             kernelVerticalOffset += this.kw;
/* 456 */             imageVerticalOffset += srcScanlineStride;
/*     */           } 
/* 460 */           float sqr_f_h = f_h * f_h;
/* 461 */           float sqr_f_v = f_v * f_v;
/* 462 */           float result = (float)Math.sqrt((sqr_f_h + sqr_f_v));
/* 464 */           dstData[dstPixelOffset] = result;
/* 465 */           srcPixelOffset += srcPixelStride;
/* 466 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/* 468 */         srcScanlineOffset += srcScanlineStride;
/* 469 */         dstScanlineOffset += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void doubleLoop(RasterAccessor src, RasterAccessor dst) {
/* 475 */     int dwidth = dst.getWidth();
/* 476 */     int dheight = dst.getHeight();
/* 477 */     int dnumBands = dst.getNumBands();
/* 479 */     float[] kdata_h = this.kernel_h.getKernelData();
/* 480 */     float[] kdata_v = this.kernel_v.getKernelData();
/* 482 */     double[][] dstDataArrays = dst.getDoubleDataArrays();
/* 483 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 484 */     int dstPixelStride = dst.getPixelStride();
/* 485 */     int dstScanlineStride = dst.getScanlineStride();
/* 487 */     double[][] srcDataArrays = src.getDoubleDataArrays();
/* 488 */     int[] srcBandOffsets = src.getBandOffsets();
/* 489 */     int srcPixelStride = src.getPixelStride();
/* 490 */     int srcScanlineStride = src.getScanlineStride();
/* 492 */     for (int k = 0; k < dnumBands; k++) {
/* 493 */       double[] dstData = dstDataArrays[k];
/* 494 */       double[] srcData = srcDataArrays[k];
/* 495 */       int srcScanlineOffset = srcBandOffsets[k];
/* 496 */       int dstScanlineOffset = dstBandOffsets[k];
/* 497 */       for (int j = 0; j < dheight; j++) {
/* 498 */         int srcPixelOffset = srcScanlineOffset;
/* 499 */         int dstPixelOffset = dstScanlineOffset;
/* 501 */         for (int i = 0; i < dwidth; i++) {
/* 502 */           double f_h = 0.0D;
/* 503 */           double f_v = 0.0D;
/* 504 */           int kernelVerticalOffset = 0;
/* 505 */           int imageVerticalOffset = srcPixelOffset;
/* 506 */           for (int u = 0; u < this.kh; u++) {
/* 507 */             int imageOffset = imageVerticalOffset;
/* 508 */             for (int v = 0; v < this.kw; v++) {
/* 509 */               f_h += srcData[imageOffset] * kdata_h[kernelVerticalOffset + v];
/* 511 */               f_v += srcData[imageOffset] * kdata_v[kernelVerticalOffset + v];
/* 513 */               imageOffset += srcPixelStride;
/*     */             } 
/* 515 */             kernelVerticalOffset += this.kw;
/* 516 */             imageVerticalOffset += srcScanlineStride;
/*     */           } 
/* 520 */           double sqr_f_h = f_h * f_h;
/* 521 */           double sqr_f_v = f_v * f_v;
/* 522 */           double result = Math.sqrt(sqr_f_h + sqr_f_v);
/* 524 */           dstData[dstPixelOffset] = result;
/* 525 */           srcPixelOffset += srcPixelStride;
/* 526 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/* 528 */         srcScanlineOffset += srcScanlineStride;
/* 529 */         dstScanlineOffset += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\GradientOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */