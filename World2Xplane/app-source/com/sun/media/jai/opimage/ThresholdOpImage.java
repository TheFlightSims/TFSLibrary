/*     */ package com.sun.media.jai.opimage;
/*     */ 
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.util.Map;
/*     */ import javax.media.jai.ColormapOpImage;
/*     */ import javax.media.jai.ImageLayout;
/*     */ import javax.media.jai.RasterAccessor;
/*     */ import javax.media.jai.RasterFormatTag;
/*     */ 
/*     */ final class ThresholdOpImage extends ColormapOpImage {
/*     */   private double[] low;
/*     */   
/*     */   private double[] high;
/*     */   
/*     */   private double[] constants;
/*     */   
/*  54 */   private byte[][] byteTable = (byte[][])null;
/*     */   
/*     */   public ThresholdOpImage(RenderedImage source, Map config, ImageLayout layout, double[] low, double[] high, double[] constants) {
/*  71 */     super(source, layout, config, true);
/*  73 */     int numBands = getSampleModel().getNumBands();
/*  74 */     this.low = new double[numBands];
/*  75 */     this.high = new double[numBands];
/*  76 */     this.constants = new double[numBands];
/*  78 */     for (int i = 0; i < numBands; i++) {
/*  79 */       if (low.length < numBands) {
/*  80 */         this.low[i] = low[0];
/*     */       } else {
/*  82 */         this.low[i] = low[i];
/*     */       } 
/*  84 */       if (high.length < numBands) {
/*  85 */         this.high[i] = high[0];
/*     */       } else {
/*  87 */         this.high[i] = high[i];
/*     */       } 
/*  89 */       if (constants.length < numBands) {
/*  90 */         this.constants[i] = constants[0];
/*     */       } else {
/*  92 */         this.constants[i] = constants[i];
/*     */       } 
/*     */     } 
/*  97 */     permitInPlaceOperation();
/* 100 */     initializeColormapOperation();
/*     */   }
/*     */   
/*     */   protected void transformColormap(byte[][] colormap) {
/* 107 */     initByteTable();
/* 109 */     for (int b = 0; b < 3; b++) {
/* 110 */       byte[] map = colormap[b];
/* 111 */       byte[] luTable = this.byteTable[(b >= this.byteTable.length) ? 0 : b];
/* 112 */       int mapSize = map.length;
/* 114 */       for (int i = 0; i < mapSize; i++)
/* 115 */         map[i] = luTable[map[i] & 0xFF]; 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void computeRect(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/* 133 */     RasterFormatTag[] formatTags = getFormatTags();
/* 135 */     Rectangle srcRect = mapDestRect(destRect, 0);
/* 137 */     RasterAccessor src = new RasterAccessor(sources[0], srcRect, formatTags[0], getSource(0).getColorModel());
/* 140 */     RasterAccessor dst = new RasterAccessor(dest, destRect, formatTags[1], getColorModel());
/* 143 */     int srcPixelStride = src.getPixelStride();
/* 144 */     int srcLineStride = src.getScanlineStride();
/* 145 */     int[] srcBandOffsets = src.getBandOffsets();
/* 147 */     int dstPixelStride = dst.getPixelStride();
/* 148 */     int dstLineStride = dst.getScanlineStride();
/* 149 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 151 */     int width = dst.getWidth() * dstPixelStride;
/* 152 */     int height = dst.getHeight() * dstLineStride;
/* 153 */     int bands = dst.getNumBands();
/* 155 */     switch (dst.getDataType()) {
/*     */       case 0:
/* 157 */         byteLoop(width, height, bands, srcPixelStride, srcLineStride, srcBandOffsets, src.getByteDataArrays(), dstPixelStride, dstLineStride, dstBandOffsets, dst.getByteDataArrays());
/*     */         break;
/*     */       case 2:
/* 165 */         shortLoop(width, height, bands, srcPixelStride, srcLineStride, srcBandOffsets, src.getShortDataArrays(), dstPixelStride, dstLineStride, dstBandOffsets, dst.getShortDataArrays());
/*     */         break;
/*     */       case 1:
/* 173 */         ushortLoop(width, height, bands, srcPixelStride, srcLineStride, srcBandOffsets, src.getShortDataArrays(), dstPixelStride, dstLineStride, dstBandOffsets, dst.getShortDataArrays());
/*     */         break;
/*     */       case 3:
/* 181 */         intLoop(width, height, bands, srcPixelStride, srcLineStride, srcBandOffsets, src.getIntDataArrays(), dstPixelStride, dstLineStride, dstBandOffsets, dst.getIntDataArrays());
/*     */         break;
/*     */       case 4:
/* 189 */         floatLoop(width, height, bands, srcPixelStride, srcLineStride, srcBandOffsets, src.getFloatDataArrays(), dstPixelStride, dstLineStride, dstBandOffsets, dst.getFloatDataArrays());
/*     */         break;
/*     */       case 5:
/* 197 */         doubleLoop(width, height, bands, srcPixelStride, srcLineStride, srcBandOffsets, src.getDoubleDataArrays(), dstPixelStride, dstLineStride, dstBandOffsets, dst.getDoubleDataArrays());
/*     */         break;
/*     */     } 
/* 205 */     if (dst.isDataCopy()) {
/* 206 */       dst.clampDataArrays();
/* 207 */       dst.copyDataToRaster();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void byteLoop(int width, int height, int bands, int srcPixelStride, int srcLineStride, int[] srcBandOffsets, byte[][] srcData, int dstPixelStride, int dstLineStride, int[] dstBandOffsets, byte[][] dstData) {
/* 217 */     initByteTable();
/* 219 */     for (int b = 0; b < bands; b++) {
/* 220 */       byte[] s = srcData[b];
/* 221 */       byte[] d = dstData[b];
/* 222 */       byte[] t = this.byteTable[b];
/* 224 */       int heightEnd = dstBandOffsets[b] + height;
/* 226 */       int dstLineOffset = dstBandOffsets[b];
/* 227 */       int srcLineOffset = srcBandOffsets[b];
/* 228 */       for (; dstLineOffset < heightEnd; 
/* 229 */         dstLineOffset += dstLineStride, 
/* 230 */         srcLineOffset += srcLineStride) {
/* 232 */         int widthEnd = dstLineOffset + width;
/* 234 */         int dstPixelOffset = dstLineOffset;
/* 235 */         int srcPixelOffset = srcLineOffset;
/* 236 */         for (; dstPixelOffset < widthEnd; 
/* 237 */           dstPixelOffset += dstPixelStride, 
/* 238 */           srcPixelOffset += srcPixelStride)
/* 240 */           d[dstPixelOffset] = t[s[srcPixelOffset] & 0xFF]; 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void shortLoop(int width, int height, int bands, int srcPixelStride, int srcLineStride, int[] srcBandOffsets, short[][] srcData, int dstPixelStride, int dstLineStride, int[] dstBandOffsets, short[][] dstData) {
/* 251 */     for (int b = 0; b < bands; b++) {
/* 252 */       short[] s = srcData[b];
/* 253 */       short[] d = dstData[b];
/* 255 */       double l = this.low[b];
/* 256 */       double h = this.high[b];
/* 257 */       short c = (short)(int)this.constants[b];
/* 259 */       int heightEnd = dstBandOffsets[b] + height;
/* 261 */       int dstLineOffset = dstBandOffsets[b];
/* 262 */       int srcLineOffset = srcBandOffsets[b];
/* 263 */       for (; dstLineOffset < heightEnd; 
/* 264 */         dstLineOffset += dstLineStride, 
/* 265 */         srcLineOffset += srcLineStride) {
/* 267 */         int widthEnd = dstLineOffset + width;
/* 269 */         int dstPixelOffset = dstLineOffset;
/* 270 */         int srcPixelOffset = srcLineOffset;
/* 271 */         for (; dstPixelOffset < widthEnd; 
/* 272 */           dstPixelOffset += dstPixelStride, 
/* 273 */           srcPixelOffset += srcPixelStride) {
/* 275 */           short p = s[srcPixelOffset];
/* 277 */           if (p >= l && p <= h) {
/* 278 */             d[dstPixelOffset] = c;
/*     */           } else {
/* 280 */             d[dstPixelOffset] = p;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void ushortLoop(int width, int height, int bands, int srcPixelStride, int srcLineStride, int[] srcBandOffsets, short[][] srcData, int dstPixelStride, int dstLineStride, int[] dstBandOffsets, short[][] dstData) {
/* 293 */     for (int b = 0; b < bands; b++) {
/* 294 */       short[] s = srcData[b];
/* 295 */       short[] d = dstData[b];
/* 297 */       double l = this.low[b];
/* 298 */       double h = this.high[b];
/* 299 */       short c = (short)(int)this.constants[b];
/* 301 */       int heightEnd = dstBandOffsets[b] + height;
/* 303 */       int dstLineOffset = dstBandOffsets[b];
/* 304 */       int srcLineOffset = srcBandOffsets[b];
/* 305 */       for (; dstLineOffset < heightEnd; 
/* 306 */         dstLineOffset += dstLineStride, 
/* 307 */         srcLineOffset += srcLineStride) {
/* 309 */         int widthEnd = dstLineOffset + width;
/* 311 */         int dstPixelOffset = dstLineOffset;
/* 312 */         int srcPixelOffset = srcLineOffset;
/* 313 */         for (; dstPixelOffset < widthEnd; 
/* 314 */           dstPixelOffset += dstPixelStride, 
/* 315 */           srcPixelOffset += srcPixelStride) {
/* 317 */           int p = s[srcPixelOffset] & 0xFFFF;
/* 319 */           if (p >= l && p <= h) {
/* 320 */             d[dstPixelOffset] = c;
/*     */           } else {
/* 322 */             d[dstPixelOffset] = (short)p;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void intLoop(int width, int height, int bands, int srcPixelStride, int srcLineStride, int[] srcBandOffsets, int[][] srcData, int dstPixelStride, int dstLineStride, int[] dstBandOffsets, int[][] dstData) {
/* 335 */     for (int b = 0; b < bands; b++) {
/* 336 */       int[] s = srcData[b];
/* 337 */       int[] d = dstData[b];
/* 339 */       double l = this.low[b];
/* 340 */       double h = this.high[b];
/* 341 */       int c = (int)this.constants[b];
/* 343 */       int heightEnd = dstBandOffsets[b] + height;
/* 345 */       int dstLineOffset = dstBandOffsets[b];
/* 346 */       int srcLineOffset = srcBandOffsets[b];
/* 347 */       for (; dstLineOffset < heightEnd; 
/* 348 */         dstLineOffset += dstLineStride, 
/* 349 */         srcLineOffset += srcLineStride) {
/* 351 */         int widthEnd = dstLineOffset + width;
/* 353 */         int dstPixelOffset = dstLineOffset;
/* 354 */         int srcPixelOffset = srcLineOffset;
/* 355 */         for (; dstPixelOffset < widthEnd; 
/* 356 */           dstPixelOffset += dstPixelStride, 
/* 357 */           srcPixelOffset += srcPixelStride) {
/* 359 */           int p = s[srcPixelOffset];
/* 361 */           if (p >= l && p <= h) {
/* 362 */             d[dstPixelOffset] = c;
/*     */           } else {
/* 364 */             d[dstPixelOffset] = p;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void floatLoop(int width, int height, int bands, int srcPixelStride, int srcLineStride, int[] srcBandOffsets, float[][] srcData, int dstPixelStride, int dstLineStride, int[] dstBandOffsets, float[][] dstData) {
/* 377 */     for (int b = 0; b < bands; b++) {
/* 378 */       float[] s = srcData[b];
/* 379 */       float[] d = dstData[b];
/* 381 */       double l = this.low[b];
/* 382 */       double h = this.high[b];
/* 383 */       float c = (float)this.constants[b];
/* 385 */       int heightEnd = dstBandOffsets[b] + height;
/* 387 */       int dstLineOffset = dstBandOffsets[b];
/* 388 */       int srcLineOffset = srcBandOffsets[b];
/* 389 */       for (; dstLineOffset < heightEnd; 
/* 390 */         dstLineOffset += dstLineStride, 
/* 391 */         srcLineOffset += srcLineStride) {
/* 393 */         int widthEnd = dstLineOffset + width;
/* 395 */         int dstPixelOffset = dstLineOffset;
/* 396 */         int srcPixelOffset = srcLineOffset;
/* 397 */         for (; dstPixelOffset < widthEnd; 
/* 398 */           dstPixelOffset += dstPixelStride, 
/* 399 */           srcPixelOffset += srcPixelStride) {
/* 401 */           float p = s[srcPixelOffset];
/* 403 */           if (p >= l && p <= h) {
/* 404 */             d[dstPixelOffset] = c;
/*     */           } else {
/* 406 */             d[dstPixelOffset] = p;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void doubleLoop(int width, int height, int bands, int srcPixelStride, int srcLineStride, int[] srcBandOffsets, double[][] srcData, int dstPixelStride, int dstLineStride, int[] dstBandOffsets, double[][] dstData) {
/* 419 */     for (int b = 0; b < bands; b++) {
/* 420 */       double[] s = srcData[b];
/* 421 */       double[] d = dstData[b];
/* 423 */       double l = this.low[b];
/* 424 */       double h = this.high[b];
/* 425 */       double c = this.constants[b];
/* 427 */       int heightEnd = dstBandOffsets[b] + height;
/* 429 */       int dstLineOffset = dstBandOffsets[b];
/* 430 */       int srcLineOffset = srcBandOffsets[b];
/* 431 */       for (; dstLineOffset < heightEnd; 
/* 432 */         dstLineOffset += dstLineStride, 
/* 433 */         srcLineOffset += srcLineStride) {
/* 435 */         int widthEnd = dstLineOffset + width;
/* 437 */         int dstPixelOffset = dstLineOffset;
/* 438 */         int srcPixelOffset = srcLineOffset;
/* 439 */         for (; dstPixelOffset < widthEnd; 
/* 440 */           dstPixelOffset += dstPixelStride, 
/* 441 */           srcPixelOffset += srcPixelStride) {
/* 443 */           double p = s[srcPixelOffset];
/* 445 */           if (p >= l && p <= h) {
/* 446 */             d[dstPixelOffset] = c;
/*     */           } else {
/* 448 */             d[dstPixelOffset] = p;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private synchronized void initByteTable() {
/* 457 */     if (this.byteTable != null)
/*     */       return; 
/* 461 */     int numBands = getSampleModel().getNumBands();
/* 462 */     this.byteTable = new byte[numBands][256];
/* 464 */     for (int b = 0; b < numBands; b++) {
/* 465 */       double l = this.low[b];
/* 466 */       double h = this.high[b];
/* 467 */       byte c = (byte)(int)this.constants[b];
/* 469 */       byte[] t = this.byteTable[b];
/* 471 */       for (int i = 0; i < 256; i++) {
/* 472 */         if (i >= l && i <= h) {
/* 473 */           t[i] = c;
/*     */         } else {
/* 475 */           t[i] = (byte)i;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\ThresholdOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */