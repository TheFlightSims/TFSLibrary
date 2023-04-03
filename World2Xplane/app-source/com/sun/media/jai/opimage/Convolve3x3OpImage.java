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
/*     */ final class Convolve3x3OpImage extends AreaOpImage {
/*     */   protected KernelJAI kernel;
/*     */   
/*  63 */   float[][] tables = new float[9][256];
/*     */   
/*     */   public Convolve3x3OpImage(RenderedImage source, BorderExtender extender, Map config, ImageLayout layout, KernelJAI kernel) {
/*  86 */     super(source, layout, config, true, extender, kernel.getLeftPadding(), kernel.getRightPadding(), kernel.getTopPadding(), kernel.getBottomPadding());
/*  96 */     this.kernel = kernel;
/*  97 */     if (kernel.getWidth() != 3 || kernel.getHeight() != 3 || kernel.getXOrigin() != 1 || kernel.getYOrigin() != 1)
/* 101 */       throw new RuntimeException(JaiI18N.getString("Convolve3x3OpImage0")); 
/* 104 */     if (this.sampleModel.getDataType() == 0) {
/* 105 */       float[] kdata = kernel.getKernelData();
/* 106 */       float k0 = kdata[0];
/* 107 */       float k1 = kdata[1];
/* 108 */       float k2 = kdata[2];
/* 109 */       float k3 = kdata[3];
/* 110 */       float k4 = kdata[4];
/* 111 */       float k5 = kdata[5];
/* 112 */       float k6 = kdata[6];
/* 113 */       float k7 = kdata[7];
/* 114 */       float k8 = kdata[8];
/* 116 */       for (int j = 0; j < 256; j++) {
/* 117 */         byte b = (byte)j;
/* 118 */         float f = j;
/* 119 */         this.tables[0][b + 128] = k0 * f + 0.5F;
/* 120 */         this.tables[1][b + 128] = k1 * f;
/* 121 */         this.tables[2][b + 128] = k2 * f;
/* 122 */         this.tables[3][b + 128] = k3 * f;
/* 123 */         this.tables[4][b + 128] = k4 * f;
/* 124 */         this.tables[5][b + 128] = k5 * f;
/* 125 */         this.tables[6][b + 128] = k6 * f;
/* 126 */         this.tables[7][b + 128] = k7 * f;
/* 127 */         this.tables[8][b + 128] = k8 * f;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void computeRect(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/*     */     String className;
/* 145 */     RasterFormatTag[] formatTags = getFormatTags();
/* 147 */     Raster source = sources[0];
/* 148 */     Rectangle srcRect = mapDestRect(destRect, 0);
/* 150 */     RasterAccessor srcAccessor = new RasterAccessor(source, srcRect, formatTags[0], getSourceImage(0).getColorModel());
/* 154 */     RasterAccessor dstAccessor = new RasterAccessor(dest, destRect, formatTags[1], getColorModel());
/* 158 */     switch (dstAccessor.getDataType()) {
/*     */       case 0:
/* 160 */         byteLoop(srcAccessor, dstAccessor);
/*     */         break;
/*     */       case 2:
/* 163 */         shortLoop(srcAccessor, dstAccessor);
/*     */         break;
/*     */       case 3:
/* 166 */         intLoop(srcAccessor, dstAccessor);
/*     */         break;
/*     */       default:
/* 169 */         className = getClass().getName();
/* 170 */         throw new RuntimeException(JaiI18N.getString("Convolve3x3OpImage1"));
/*     */     } 
/* 176 */     if (dstAccessor.isDataCopy()) {
/* 177 */       dstAccessor.clampDataArrays();
/* 178 */       dstAccessor.copyDataToRaster();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void byteLoop(RasterAccessor src, RasterAccessor dst) {
/* 183 */     int dwidth = dst.getWidth();
/* 184 */     int dheight = dst.getHeight();
/* 185 */     int dnumBands = dst.getNumBands();
/* 188 */     float[] t0 = this.tables[0];
/* 189 */     float[] t1 = this.tables[1];
/* 190 */     float[] t2 = this.tables[2];
/* 191 */     float[] t3 = this.tables[3];
/* 192 */     float[] t4 = this.tables[4];
/* 193 */     float[] t5 = this.tables[5];
/* 194 */     float[] t6 = this.tables[6];
/* 195 */     float[] t7 = this.tables[7];
/* 196 */     float[] t8 = this.tables[8];
/* 198 */     float[] kdata = this.kernel.getKernelData();
/* 200 */     byte[][] dstDataArrays = dst.getByteDataArrays();
/* 201 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 202 */     int dstPixelStride = dst.getPixelStride();
/* 203 */     int dstScanlineStride = dst.getScanlineStride();
/* 205 */     byte[][] srcDataArrays = src.getByteDataArrays();
/* 206 */     int[] srcBandOffsets = src.getBandOffsets();
/* 207 */     int srcPixelStride = src.getPixelStride();
/* 208 */     int srcScanlineStride = src.getScanlineStride();
/* 211 */     int centerScanlineOffset = srcScanlineStride;
/* 212 */     int bottomScanlineOffset = srcScanlineStride * 2;
/* 213 */     int middlePixelOffset = dnumBands;
/* 214 */     int rightPixelOffset = dnumBands * 2;
/* 216 */     for (int k = 0; k < dnumBands; k++) {
/* 217 */       byte[] dstData = dstDataArrays[k];
/* 218 */       byte[] srcData = srcDataArrays[k];
/* 219 */       int srcScanlineOffset = srcBandOffsets[k];
/* 220 */       int dstScanlineOffset = dstBandOffsets[k];
/* 221 */       for (int j = 0; j < dheight; j++) {
/* 222 */         int srcPixelOffset = srcScanlineOffset;
/* 223 */         int dstPixelOffset = dstScanlineOffset;
/* 224 */         for (int i = 0; i < dwidth; i++) {
/* 225 */           float f = t0[128 + srcData[srcPixelOffset]] + t1[128 + srcData[srcPixelOffset + middlePixelOffset]] + t2[128 + srcData[srcPixelOffset + rightPixelOffset]] + t3[128 + srcData[srcPixelOffset + centerScanlineOffset]] + t4[128 + srcData[srcPixelOffset + centerScanlineOffset + middlePixelOffset]] + t5[128 + srcData[srcPixelOffset + centerScanlineOffset + rightPixelOffset]] + t6[128 + srcData[srcPixelOffset + bottomScanlineOffset]] + t7[128 + srcData[srcPixelOffset + bottomScanlineOffset + middlePixelOffset]] + t8[128 + srcData[srcPixelOffset + bottomScanlineOffset + rightPixelOffset]];
/* 249 */           int val = (int)f;
/* 250 */           if (val < 0) {
/* 251 */             val = 0;
/* 252 */           } else if (val > 255) {
/* 253 */             val = 255;
/*     */           } 
/* 255 */           dstData[dstPixelOffset] = (byte)val;
/* 256 */           srcPixelOffset += srcPixelStride;
/* 257 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/* 259 */         srcScanlineOffset += srcScanlineStride;
/* 260 */         dstScanlineOffset += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void shortLoop(RasterAccessor src, RasterAccessor dst) {
/* 266 */     int dwidth = dst.getWidth();
/* 267 */     int dheight = dst.getHeight();
/* 268 */     int dnumBands = dst.getNumBands();
/* 270 */     short[][] dstDataArrays = dst.getShortDataArrays();
/* 271 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 272 */     int dstPixelStride = dst.getPixelStride();
/* 273 */     int dstScanlineStride = dst.getScanlineStride();
/* 275 */     short[][] srcDataArrays = src.getShortDataArrays();
/* 276 */     int[] srcBandOffsets = src.getBandOffsets();
/* 277 */     int srcPixelStride = src.getPixelStride();
/* 278 */     int srcScanlineStride = src.getScanlineStride();
/* 281 */     int centerScanlineOffset = srcScanlineStride;
/* 282 */     int bottomScanlineOffset = srcScanlineStride * 2;
/* 283 */     int middlePixelOffset = dnumBands;
/* 284 */     int rightPixelOffset = dnumBands * 2;
/* 286 */     float[] kdata = this.kernel.getKernelData();
/* 287 */     float k0 = kdata[0];
/* 288 */     float k1 = kdata[1];
/* 289 */     float k2 = kdata[2];
/* 290 */     float k3 = kdata[3];
/* 291 */     float k4 = kdata[4];
/* 292 */     float k5 = kdata[5];
/* 293 */     float k6 = kdata[6];
/* 294 */     float k7 = kdata[7];
/* 295 */     float k8 = kdata[8];
/* 297 */     for (int k = 0; k < dnumBands; k++) {
/* 298 */       short[] dstData = dstDataArrays[k];
/* 299 */       short[] srcData = srcDataArrays[k];
/* 300 */       int srcScanlineOffset = srcBandOffsets[k];
/* 301 */       int dstScanlineOffset = dstBandOffsets[k];
/* 302 */       for (int j = 0; j < dheight; j++) {
/* 303 */         int srcPixelOffset = srcScanlineOffset;
/* 304 */         int dstPixelOffset = dstScanlineOffset;
/* 305 */         for (int i = 0; i < dwidth; i++) {
/* 306 */           float f = k0 * srcData[srcPixelOffset] + k1 * srcData[srcPixelOffset + middlePixelOffset] + k2 * srcData[srcPixelOffset + rightPixelOffset] + k3 * srcData[srcPixelOffset + centerScanlineOffset] + k4 * srcData[srcPixelOffset + centerScanlineOffset + middlePixelOffset] + k5 * srcData[srcPixelOffset + centerScanlineOffset + rightPixelOffset] + k6 * srcData[srcPixelOffset + bottomScanlineOffset] + k7 * srcData[srcPixelOffset + bottomScanlineOffset + middlePixelOffset] + k8 * srcData[srcPixelOffset + bottomScanlineOffset + rightPixelOffset];
/* 329 */           int val = (int)f;
/* 330 */           if (val < -32768) {
/* 331 */             val = -32768;
/* 332 */           } else if (val > 32767) {
/* 333 */             val = 32767;
/*     */           } 
/* 335 */           dstData[dstPixelOffset] = (short)val;
/* 336 */           srcPixelOffset += srcPixelStride;
/* 337 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/* 339 */         srcScanlineOffset += srcScanlineStride;
/* 340 */         dstScanlineOffset += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void intLoop(RasterAccessor src, RasterAccessor dst) {
/* 346 */     int dwidth = dst.getWidth();
/* 347 */     int dheight = dst.getHeight();
/* 348 */     int dnumBands = dst.getNumBands();
/* 350 */     int[][] dstDataArrays = dst.getIntDataArrays();
/* 351 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 352 */     int dstPixelStride = dst.getPixelStride();
/* 353 */     int dstScanlineStride = dst.getScanlineStride();
/* 355 */     int[][] srcDataArrays = src.getIntDataArrays();
/* 356 */     int[] srcBandOffsets = src.getBandOffsets();
/* 357 */     int srcPixelStride = src.getPixelStride();
/* 358 */     int srcScanlineStride = src.getScanlineStride();
/* 361 */     int centerScanlineOffset = srcScanlineStride;
/* 362 */     int bottomScanlineOffset = srcScanlineStride * 2;
/* 363 */     int middlePixelOffset = dnumBands;
/* 364 */     int rightPixelOffset = dnumBands * 2;
/* 366 */     float[] kdata = this.kernel.getKernelData();
/* 367 */     float k0 = kdata[0];
/* 368 */     float k1 = kdata[1];
/* 369 */     float k2 = kdata[2];
/* 370 */     float k3 = kdata[3];
/* 371 */     float k4 = kdata[4];
/* 372 */     float k5 = kdata[5];
/* 373 */     float k6 = kdata[6];
/* 374 */     float k7 = kdata[7];
/* 375 */     float k8 = kdata[8];
/* 377 */     for (int k = 0; k < dnumBands; k++) {
/* 378 */       int[] dstData = dstDataArrays[k];
/* 379 */       int[] srcData = srcDataArrays[k];
/* 380 */       int srcScanlineOffset = srcBandOffsets[k];
/* 381 */       int dstScanlineOffset = dstBandOffsets[k];
/* 382 */       for (int j = 0; j < dheight; j++) {
/* 383 */         int srcPixelOffset = srcScanlineOffset;
/* 384 */         int dstPixelOffset = dstScanlineOffset;
/* 385 */         for (int i = 0; i < dwidth; i++) {
/* 386 */           float f = k0 * srcData[srcPixelOffset] + k1 * srcData[srcPixelOffset + middlePixelOffset] + k2 * srcData[srcPixelOffset + rightPixelOffset] + k3 * srcData[srcPixelOffset + centerScanlineOffset] + k4 * srcData[srcPixelOffset + centerScanlineOffset + middlePixelOffset] + k5 * srcData[srcPixelOffset + centerScanlineOffset + rightPixelOffset] + k6 * srcData[srcPixelOffset + bottomScanlineOffset] + k7 * srcData[srcPixelOffset + bottomScanlineOffset + middlePixelOffset] + k8 * srcData[srcPixelOffset + bottomScanlineOffset + rightPixelOffset];
/* 409 */           dstData[dstPixelOffset] = (int)f;
/* 410 */           srcPixelOffset += srcPixelStride;
/* 411 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/* 413 */         srcScanlineOffset += srcScanlineStride;
/* 414 */         dstScanlineOffset += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\Convolve3x3OpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */