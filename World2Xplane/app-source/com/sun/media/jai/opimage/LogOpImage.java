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
/*     */ final class LogOpImage extends ColormapOpImage {
/*  52 */   private byte[] byteTable = null;
/*     */   
/*     */   public LogOpImage(RenderedImage source, Map config, ImageLayout layout) {
/*  69 */     super(source, layout, config, true);
/*  72 */     permitInPlaceOperation();
/*  75 */     initializeColormapOperation();
/*     */   }
/*     */   
/*     */   protected void transformColormap(byte[][] colormap) {
/*  82 */     initByteTable();
/*  84 */     for (int b = 0; b < 3; b++) {
/*  85 */       byte[] map = colormap[b];
/*  86 */       int mapSize = map.length;
/*  88 */       for (int i = 0; i < mapSize; i++)
/*  89 */         map[i] = this.byteTable[map[i] & 0xFF]; 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void computeRect(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/* 107 */     RasterFormatTag[] formatTags = getFormatTags();
/* 110 */     RasterAccessor s = new RasterAccessor(sources[0], destRect, formatTags[0], getSourceImage(0).getColorModel());
/* 114 */     RasterAccessor d = new RasterAccessor(dest, destRect, formatTags[1], getColorModel());
/* 119 */     switch (d.getDataType()) {
/*     */       case 0:
/* 121 */         computeRectByte(s, d);
/*     */         break;
/*     */       case 1:
/* 124 */         computeRectUShort(s, d);
/*     */         break;
/*     */       case 2:
/* 127 */         computeRectShort(s, d);
/*     */         break;
/*     */       case 3:
/* 130 */         computeRectInt(s, d);
/*     */         break;
/*     */       case 4:
/* 133 */         computeRectFloat(s, d);
/*     */         break;
/*     */       case 5:
/* 136 */         computeRectDouble(s, d);
/*     */         break;
/*     */     } 
/* 140 */     if (d.needsClamping())
/* 141 */       d.clampDataArrays(); 
/* 143 */     d.copyDataToRaster();
/*     */   }
/*     */   
/*     */   private void computeRectByte(RasterAccessor src, RasterAccessor dst) {
/* 148 */     initByteTable();
/* 150 */     int srcLineStride = src.getScanlineStride();
/* 151 */     int srcPixelStride = src.getPixelStride();
/* 152 */     int[] srcBandOffsets = src.getBandOffsets();
/* 153 */     byte[][] srcData = src.getByteDataArrays();
/* 155 */     int dstLineStride = dst.getScanlineStride();
/* 156 */     int dstPixelStride = dst.getPixelStride();
/* 157 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 158 */     byte[][] dstData = dst.getByteDataArrays();
/* 160 */     int dstWidth = dst.getWidth();
/* 161 */     int dstHeight = dst.getHeight();
/* 162 */     int dstBands = dst.getNumBands();
/* 164 */     for (int b = 0; b < dstBands; b++) {
/* 165 */       byte[] s = srcData[b];
/* 166 */       byte[] d = dstData[b];
/* 168 */       int srcLineOffset = srcBandOffsets[b];
/* 169 */       int dstLineOffset = dstBandOffsets[b];
/* 171 */       for (int h = 0; h < dstHeight; h++) {
/* 172 */         int srcPixelOffset = srcLineOffset;
/* 173 */         int dstPixelOffset = dstLineOffset;
/* 175 */         srcLineOffset += srcLineStride;
/* 176 */         dstLineOffset += dstLineStride;
/* 178 */         for (int w = 0; w < dstWidth; w++) {
/* 179 */           d[dstPixelOffset] = this.byteTable[s[srcPixelOffset] & 0xFF];
/* 181 */           srcPixelOffset += srcPixelStride;
/* 182 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeRectUShort(RasterAccessor src, RasterAccessor dst) {
/* 191 */     int srcLineStride = src.getScanlineStride();
/* 192 */     int srcPixelStride = src.getPixelStride();
/* 193 */     int[] srcBandOffsets = src.getBandOffsets();
/* 194 */     short[][] srcData = src.getShortDataArrays();
/* 196 */     int dstLineStride = dst.getScanlineStride();
/* 197 */     int dstPixelStride = dst.getPixelStride();
/* 198 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 199 */     short[][] dstData = dst.getShortDataArrays();
/* 201 */     int dstWidth = dst.getWidth();
/* 202 */     int dstHeight = dst.getHeight();
/* 203 */     int dstBands = dst.getNumBands();
/* 205 */     for (int b = 0; b < dstBands; b++) {
/* 206 */       short[] s = srcData[b];
/* 207 */       short[] d = dstData[b];
/* 209 */       int srcLineOffset = srcBandOffsets[b];
/* 210 */       int dstLineOffset = dstBandOffsets[b];
/* 212 */       for (int h = 0; h < dstHeight; h++) {
/* 213 */         int srcPixelOffset = srcLineOffset;
/* 214 */         int dstPixelOffset = dstLineOffset;
/* 216 */         srcLineOffset += srcLineStride;
/* 217 */         dstLineOffset += dstLineStride;
/* 219 */         for (int w = 0; w < dstWidth; w++) {
/* 224 */           d[dstPixelOffset] = (short)(int)(Math.log((s[srcPixelOffset] & 0xFFFF)) + 0.5D);
/* 227 */           srcPixelOffset += srcPixelStride;
/* 228 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeRectShort(RasterAccessor src, RasterAccessor dst) {
/* 237 */     int srcLineStride = src.getScanlineStride();
/* 238 */     int srcPixelStride = src.getPixelStride();
/* 239 */     int[] srcBandOffsets = src.getBandOffsets();
/* 240 */     short[][] srcData = src.getShortDataArrays();
/* 242 */     int dstLineStride = dst.getScanlineStride();
/* 243 */     int dstPixelStride = dst.getPixelStride();
/* 244 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 245 */     short[][] dstData = dst.getShortDataArrays();
/* 247 */     int dstWidth = dst.getWidth();
/* 248 */     int dstHeight = dst.getHeight();
/* 249 */     int dstBands = dst.getNumBands();
/* 251 */     for (int b = 0; b < dstBands; b++) {
/* 252 */       short[] s = srcData[b];
/* 253 */       short[] d = dstData[b];
/* 255 */       int srcLineOffset = srcBandOffsets[b];
/* 256 */       int dstLineOffset = dstBandOffsets[b];
/* 258 */       for (int h = 0; h < dstHeight; h++) {
/* 259 */         int srcPixelOffset = srcLineOffset;
/* 260 */         int dstPixelOffset = dstLineOffset;
/* 262 */         srcLineOffset += srcLineStride;
/* 263 */         dstLineOffset += dstLineStride;
/* 265 */         for (int w = 0; w < dstWidth; w++) {
/* 270 */           d[dstPixelOffset] = (short)(int)(Math.log(s[srcPixelOffset]) + 0.5D);
/* 273 */           srcPixelOffset += srcPixelStride;
/* 274 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeRectInt(RasterAccessor src, RasterAccessor dst) {
/* 283 */     int srcLineStride = src.getScanlineStride();
/* 284 */     int srcPixelStride = src.getPixelStride();
/* 285 */     int[] srcBandOffsets = src.getBandOffsets();
/* 286 */     int[][] srcData = src.getIntDataArrays();
/* 288 */     int dstLineStride = dst.getScanlineStride();
/* 289 */     int dstPixelStride = dst.getPixelStride();
/* 290 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 291 */     int[][] dstData = dst.getIntDataArrays();
/* 293 */     int dstWidth = dst.getWidth();
/* 294 */     int dstHeight = dst.getHeight();
/* 295 */     int dstBands = dst.getNumBands();
/* 297 */     for (int b = 0; b < dstBands; b++) {
/* 298 */       int[] s = srcData[b];
/* 299 */       int[] d = dstData[b];
/* 301 */       int srcLineOffset = srcBandOffsets[b];
/* 302 */       int dstLineOffset = dstBandOffsets[b];
/* 304 */       for (int h = 0; h < dstHeight; h++) {
/* 305 */         int srcPixelOffset = srcLineOffset;
/* 306 */         int dstPixelOffset = dstLineOffset;
/* 308 */         srcLineOffset += srcLineStride;
/* 309 */         dstLineOffset += dstLineStride;
/* 311 */         for (int w = 0; w < dstWidth; w++) {
/* 316 */           double p = s[srcPixelOffset];
/* 317 */           if (p > 0.0D) {
/* 318 */             d[dstPixelOffset] = (int)(Math.log(p) + 0.5D);
/* 319 */           } else if (p == 0.0D) {
/* 320 */             d[dstPixelOffset] = 0;
/*     */           } else {
/* 322 */             d[dstPixelOffset] = -1;
/*     */           } 
/* 325 */           srcPixelOffset += srcPixelStride;
/* 326 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeRectFloat(RasterAccessor src, RasterAccessor dst) {
/* 335 */     int srcLineStride = src.getScanlineStride();
/* 336 */     int srcPixelStride = src.getPixelStride();
/* 337 */     int[] srcBandOffsets = src.getBandOffsets();
/* 338 */     float[][] srcData = src.getFloatDataArrays();
/* 340 */     int dstLineStride = dst.getScanlineStride();
/* 341 */     int dstPixelStride = dst.getPixelStride();
/* 342 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 343 */     float[][] dstData = dst.getFloatDataArrays();
/* 345 */     int dstWidth = dst.getWidth();
/* 346 */     int dstHeight = dst.getHeight();
/* 347 */     int dstBands = dst.getNumBands();
/* 349 */     for (int b = 0; b < dstBands; b++) {
/* 350 */       float[] s = srcData[b];
/* 351 */       float[] d = dstData[b];
/* 353 */       int srcLineOffset = srcBandOffsets[b];
/* 354 */       int dstLineOffset = dstBandOffsets[b];
/* 356 */       for (int h = 0; h < dstHeight; h++) {
/* 357 */         int srcPixelOffset = srcLineOffset;
/* 358 */         int dstPixelOffset = dstLineOffset;
/* 360 */         srcLineOffset += srcLineStride;
/* 361 */         dstLineOffset += dstLineStride;
/* 363 */         for (int w = 0; w < dstWidth; w++) {
/* 368 */           d[dstPixelOffset] = (float)Math.log(s[srcPixelOffset]);
/* 370 */           srcPixelOffset += srcPixelStride;
/* 371 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeRectDouble(RasterAccessor src, RasterAccessor dst) {
/* 380 */     int srcLineStride = src.getScanlineStride();
/* 381 */     int srcPixelStride = src.getPixelStride();
/* 382 */     int[] srcBandOffsets = src.getBandOffsets();
/* 383 */     double[][] srcData = src.getDoubleDataArrays();
/* 385 */     int dstLineStride = dst.getScanlineStride();
/* 386 */     int dstPixelStride = dst.getPixelStride();
/* 387 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 388 */     double[][] dstData = dst.getDoubleDataArrays();
/* 390 */     int dstWidth = dst.getWidth();
/* 391 */     int dstHeight = dst.getHeight();
/* 392 */     int dstBands = dst.getNumBands();
/* 394 */     for (int b = 0; b < dstBands; b++) {
/* 395 */       double[] s = srcData[b];
/* 396 */       double[] d = dstData[b];
/* 398 */       int srcLineOffset = srcBandOffsets[b];
/* 399 */       int dstLineOffset = dstBandOffsets[b];
/* 401 */       for (int h = 0; h < dstHeight; h++) {
/* 402 */         int srcPixelOffset = srcLineOffset;
/* 403 */         int dstPixelOffset = dstLineOffset;
/* 405 */         srcLineOffset += srcLineStride;
/* 406 */         dstLineOffset += dstLineStride;
/* 408 */         for (int w = 0; w < dstWidth; w++) {
/* 413 */           d[dstPixelOffset] = Math.log(s[srcPixelOffset]);
/* 415 */           srcPixelOffset += srcPixelStride;
/* 416 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private synchronized void initByteTable() {
/* 424 */     if (this.byteTable != null)
/*     */       return; 
/* 427 */     this.byteTable = new byte[256];
/* 429 */     this.byteTable[0] = 0;
/* 430 */     this.byteTable[1] = 0;
/* 432 */     for (int i = 2; i < 256; i++)
/* 433 */       this.byteTable[i] = (byte)(int)(Math.log(i) + 0.5D); 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\LogOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */