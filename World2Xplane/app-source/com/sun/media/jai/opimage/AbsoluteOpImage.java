/*     */ package com.sun.media.jai.opimage;
/*     */ 
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.util.Map;
/*     */ import javax.media.jai.ImageLayout;
/*     */ import javax.media.jai.PointOpImage;
/*     */ import javax.media.jai.RasterAccessor;
/*     */ import javax.media.jai.RasterFormatTag;
/*     */ 
/*     */ final class AbsoluteOpImage extends PointOpImage {
/*     */   public AbsoluteOpImage(RenderedImage source, Map config, ImageLayout layout) {
/*  63 */     super(source, layout, config, true);
/*  66 */     permitInPlaceOperation();
/*     */   }
/*     */   
/*     */   protected void computeRect(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/*  82 */     RasterFormatTag[] formatTags = getFormatTags();
/*  84 */     RasterAccessor src = new RasterAccessor(sources[0], destRect, formatTags[0], getSourceImage(0).getColorModel());
/*  87 */     RasterAccessor dst = new RasterAccessor(dest, destRect, formatTags[1], getColorModel());
/*  91 */     if (dst.isBinary()) {
/*  92 */       byte[] dstBits = dst.getBinaryDataArray();
/*  93 */       System.arraycopy(src.getBinaryDataArray(), 0, dstBits, 0, dstBits.length);
/*  96 */       dst.copyBinaryDataToRaster();
/*     */       return;
/*     */     } 
/* 102 */     switch (dst.getDataType()) {
/*     */       case 0:
/* 104 */         byteAbsolute(dst.getNumBands(), dst.getWidth(), dst.getHeight(), src.getScanlineStride(), src.getPixelStride(), src.getBandOffsets(), src.getByteDataArrays(), dst.getScanlineStride(), dst.getPixelStride(), dst.getBandOffsets(), dst.getByteDataArrays());
/*     */         break;
/*     */       case 2:
/* 118 */         shortAbsolute(dst.getNumBands(), dst.getWidth(), dst.getHeight(), src.getScanlineStride(), src.getPixelStride(), src.getBandOffsets(), src.getShortDataArrays(), dst.getScanlineStride(), dst.getPixelStride(), dst.getBandOffsets(), dst.getShortDataArrays());
/*     */         break;
/*     */       case 1:
/* 132 */         ushortAbsolute(dst.getNumBands(), dst.getWidth(), dst.getHeight(), src.getScanlineStride(), src.getPixelStride(), src.getBandOffsets(), src.getShortDataArrays(), dst.getScanlineStride(), dst.getPixelStride(), dst.getBandOffsets(), dst.getShortDataArrays());
/*     */         break;
/*     */       case 3:
/* 146 */         intAbsolute(dst.getNumBands(), dst.getWidth(), dst.getHeight(), src.getScanlineStride(), src.getPixelStride(), src.getBandOffsets(), src.getIntDataArrays(), dst.getScanlineStride(), dst.getPixelStride(), dst.getBandOffsets(), dst.getIntDataArrays());
/*     */         break;
/*     */       case 4:
/* 160 */         floatAbsolute(dst.getNumBands(), dst.getWidth(), dst.getHeight(), src.getScanlineStride(), src.getPixelStride(), src.getBandOffsets(), src.getFloatDataArrays(), dst.getScanlineStride(), dst.getPixelStride(), dst.getBandOffsets(), dst.getFloatDataArrays());
/*     */         break;
/*     */       case 5:
/* 174 */         doubleAbsolute(dst.getNumBands(), dst.getWidth(), dst.getHeight(), src.getScanlineStride(), src.getPixelStride(), src.getBandOffsets(), src.getDoubleDataArrays(), dst.getScanlineStride(), dst.getPixelStride(), dst.getBandOffsets(), dst.getDoubleDataArrays());
/*     */         break;
/*     */     } 
/* 187 */     if (dst.needsClamping())
/* 188 */       dst.clampDataArrays(); 
/* 190 */     dst.copyDataToRaster();
/*     */   }
/*     */   
/*     */   private void byteAbsolute(int numBands, int dstWidth, int dstHeight, int srcScanlineStride, int srcPixelStride, int[] srcBandOffsets, byte[][] srcData, int dstScanlineStride, int dstPixelStride, int[] dstBandOffsets, byte[][] dstData) {
/* 204 */     for (int band = 0; band < numBands; band++) {
/* 205 */       byte[] src = srcData[band];
/* 206 */       byte[] dst = dstData[band];
/* 208 */       int srcLineOffset = srcBandOffsets[band];
/* 209 */       int dstLineOffset = dstBandOffsets[band];
/* 211 */       for (int h = 0; h < dstHeight; h++) {
/* 212 */         int srcPixelOffset = srcLineOffset;
/* 213 */         int dstPixelOffset = dstLineOffset;
/* 215 */         for (int w = 0; w < dstWidth; w++) {
/* 217 */           dst[dstPixelOffset] = src[srcPixelOffset];
/* 218 */           srcPixelOffset += srcPixelStride;
/* 219 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/* 222 */         srcLineOffset += srcScanlineStride;
/* 223 */         dstLineOffset += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void shortAbsolute(int numBands, int dstWidth, int dstHeight, int srcScanlineStride, int srcPixelStride, int[] srcBandOffsets, short[][] srcData, int dstScanlineStride, int dstPixelStride, int[] dstBandOffsets, short[][] dstData) {
/* 239 */     for (int band = 0; band < numBands; band++) {
/* 240 */       short[] src = srcData[band];
/* 241 */       short[] dst = dstData[band];
/* 243 */       int srcLineOffset = srcBandOffsets[band];
/* 244 */       int dstLineOffset = dstBandOffsets[band];
/* 246 */       for (int h = 0; h < dstHeight; h++) {
/* 247 */         int srcPixelOffset = srcLineOffset;
/* 248 */         int dstPixelOffset = dstLineOffset;
/* 250 */         for (int w = 0; w < dstWidth; w++) {
/* 251 */           short pixelValue = src[srcPixelOffset];
/* 253 */           if (pixelValue != Short.MIN_VALUE && (pixelValue & Short.MIN_VALUE) != 0) {
/* 256 */             dst[dstPixelOffset] = (short)-src[srcPixelOffset];
/*     */           } else {
/* 260 */             dst[dstPixelOffset] = src[srcPixelOffset];
/*     */           } 
/* 263 */           srcPixelOffset += srcPixelStride;
/* 264 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/* 267 */         srcLineOffset += srcScanlineStride;
/* 268 */         dstLineOffset += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void ushortAbsolute(int numBands, int dstWidth, int dstHeight, int srcScanlineStride, int srcPixelStride, int[] srcBandOffsets, short[][] srcData, int dstScanlineStride, int dstPixelStride, int[] dstBandOffsets, short[][] dstData) {
/* 284 */     for (int band = 0; band < numBands; band++) {
/* 285 */       short[] src = srcData[band];
/* 286 */       short[] dst = dstData[band];
/* 287 */       int srcLineOffset = srcBandOffsets[band];
/* 288 */       int dstLineOffset = dstBandOffsets[band];
/* 290 */       for (int h = 0; h < dstHeight; h++) {
/* 291 */         int srcPixelOffset = srcLineOffset;
/* 292 */         int dstPixelOffset = dstLineOffset;
/* 294 */         for (int w = 0; w < dstWidth; w++) {
/* 296 */           dst[dstPixelOffset] = src[srcPixelOffset];
/* 297 */           srcPixelOffset += srcPixelStride;
/* 298 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/* 301 */         srcLineOffset += srcScanlineStride;
/* 302 */         dstLineOffset += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void intAbsolute(int numBands, int dstWidth, int dstHeight, int srcScanlineStride, int srcPixelStride, int[] srcBandOffsets, int[][] srcData, int dstScanlineStride, int dstPixelStride, int[] dstBandOffsets, int[][] dstData) {
/* 318 */     for (int band = 0; band < numBands; band++) {
/* 319 */       int[] src = srcData[band];
/* 320 */       int[] dst = dstData[band];
/* 322 */       int srcLineOffset = srcBandOffsets[band];
/* 323 */       int dstLineOffset = dstBandOffsets[band];
/* 325 */       for (int h = 0; h < dstHeight; h++) {
/* 326 */         int srcPixelOffset = srcLineOffset;
/* 327 */         int dstPixelOffset = dstLineOffset;
/* 329 */         for (int w = 0; w < dstWidth; w++) {
/* 330 */           int pixelValue = src[srcPixelOffset];
/* 332 */           if (pixelValue != Integer.MIN_VALUE && (pixelValue & Integer.MIN_VALUE) != 0) {
/* 335 */             dst[dstPixelOffset] = -src[srcPixelOffset];
/*     */           } else {
/* 339 */             dst[dstPixelOffset] = src[srcPixelOffset];
/*     */           } 
/* 342 */           srcPixelOffset += srcPixelStride;
/* 343 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/* 346 */         srcLineOffset += srcScanlineStride;
/* 347 */         dstLineOffset += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void floatAbsolute(int numBands, int dstWidth, int dstHeight, int srcScanlineStride, int srcPixelStride, int[] srcBandOffsets, float[][] srcData, int dstScanlineStride, int dstPixelStride, int[] dstBandOffsets, float[][] dstData) {
/* 363 */     for (int band = 0; band < numBands; band++) {
/* 364 */       float[] src = srcData[band];
/* 365 */       float[] dst = dstData[band];
/* 366 */       int srcLineOffset = srcBandOffsets[band];
/* 367 */       int dstLineOffset = dstBandOffsets[band];
/* 369 */       for (int h = 0; h < dstHeight; h++) {
/* 370 */         int srcPixelOffset = srcLineOffset;
/* 371 */         int dstPixelOffset = dstLineOffset;
/* 374 */         for (int w = 0; w < dstWidth; w++) {
/* 375 */           if (src[srcPixelOffset] <= 0.0F) {
/* 376 */             dst[dstPixelOffset] = 0.0F - src[srcPixelOffset];
/*     */           } else {
/* 378 */             dst[dstPixelOffset] = src[srcPixelOffset];
/*     */           } 
/* 381 */           srcPixelOffset += srcPixelStride;
/* 382 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/* 385 */         srcLineOffset += srcScanlineStride;
/* 386 */         dstLineOffset += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void doubleAbsolute(int numBands, int dstWidth, int dstHeight, int srcScanlineStride, int srcPixelStride, int[] srcBandOffsets, double[][] srcData, int dstScanlineStride, int dstPixelStride, int[] dstBandOffsets, double[][] dstData) {
/* 402 */     for (int band = 0; band < numBands; band++) {
/* 403 */       double[] src = srcData[band];
/* 404 */       double[] dst = dstData[band];
/* 405 */       int srcLineOffset = srcBandOffsets[band];
/* 406 */       int dstLineOffset = dstBandOffsets[band];
/* 408 */       for (int h = 0; h < dstHeight; h++) {
/* 409 */         int srcPixelOffset = srcLineOffset;
/* 410 */         int dstPixelOffset = dstLineOffset;
/* 413 */         for (int w = 0; w < dstWidth; w++) {
/* 414 */           if (src[srcPixelOffset] <= 0.0D) {
/* 415 */             dst[dstPixelOffset] = 0.0D - src[srcPixelOffset];
/*     */           } else {
/* 417 */             dst[dstPixelOffset] = src[srcPixelOffset];
/*     */           } 
/* 420 */           srcPixelOffset += srcPixelStride;
/* 421 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/* 424 */         srcLineOffset += srcScanlineStride;
/* 425 */         dstLineOffset += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\AbsoluteOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */