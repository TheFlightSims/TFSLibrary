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
/*     */ final class ExpOpImage extends ColormapOpImage {
/*  45 */   private byte[] byteTable = null;
/*     */   
/*  52 */   private static int USHORT_UP_BOUND = 11;
/*     */   
/*  59 */   private static int SHORT_UP_BOUND = 10;
/*     */   
/*  66 */   private static int INT_UP_BOUND = 21;
/*     */   
/*  73 */   private static int LOW_BOUND = 0;
/*     */   
/*     */   public ExpOpImage(RenderedImage source, Map config, ImageLayout layout) {
/*  90 */     super(source, layout, config, true);
/*  93 */     permitInPlaceOperation();
/*  96 */     initializeColormapOperation();
/*     */   }
/*     */   
/*     */   protected void transformColormap(byte[][] colormap) {
/* 103 */     initByteTable();
/* 105 */     for (int b = 0; b < 3; b++) {
/* 106 */       byte[] map = colormap[b];
/* 107 */       int mapSize = map.length;
/* 109 */       for (int i = 0; i < mapSize; i++)
/* 110 */         map[i] = this.byteTable[map[i] & 0xFF]; 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void computeRect(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/* 128 */     RasterFormatTag[] formatTags = getFormatTags();
/* 131 */     RasterAccessor s = new RasterAccessor(sources[0], destRect, formatTags[0], getSourceImage(0).getColorModel());
/* 135 */     RasterAccessor d = new RasterAccessor(dest, destRect, formatTags[1], getColorModel());
/* 140 */     switch (d.getDataType()) {
/*     */       case 0:
/* 142 */         computeRectByte(s, d);
/*     */         break;
/*     */       case 1:
/* 145 */         computeRectUShort(s, d);
/*     */         break;
/*     */       case 2:
/* 148 */         computeRectShort(s, d);
/*     */         break;
/*     */       case 3:
/* 151 */         computeRectInt(s, d);
/*     */         break;
/*     */       case 4:
/* 154 */         computeRectFloat(s, d);
/*     */         break;
/*     */       case 5:
/* 157 */         computeRectDouble(s, d);
/*     */         break;
/*     */     } 
/* 161 */     if (d.needsClamping())
/* 162 */       d.clampDataArrays(); 
/* 164 */     d.copyDataToRaster();
/*     */   }
/*     */   
/*     */   private void computeRectByte(RasterAccessor src, RasterAccessor dst) {
/* 169 */     initByteTable();
/* 171 */     int srcLineStride = src.getScanlineStride();
/* 172 */     int srcPixelStride = src.getPixelStride();
/* 173 */     int[] srcBandOffsets = src.getBandOffsets();
/* 174 */     byte[][] srcData = src.getByteDataArrays();
/* 176 */     int dstLineStride = dst.getScanlineStride();
/* 177 */     int dstPixelStride = dst.getPixelStride();
/* 178 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 179 */     byte[][] dstData = dst.getByteDataArrays();
/* 181 */     int dstWidth = dst.getWidth();
/* 182 */     int dstHeight = dst.getHeight();
/* 183 */     int dstBands = dst.getNumBands();
/* 185 */     for (int b = 0; b < dstBands; b++) {
/* 186 */       byte[] s = srcData[b];
/* 187 */       byte[] d = dstData[b];
/* 189 */       int srcLineOffset = srcBandOffsets[b];
/* 190 */       int dstLineOffset = dstBandOffsets[b];
/* 192 */       for (int h = 0; h < dstHeight; h++) {
/* 193 */         int srcPixelOffset = srcLineOffset;
/* 194 */         int dstPixelOffset = dstLineOffset;
/* 196 */         srcLineOffset += srcLineStride;
/* 197 */         dstLineOffset += dstLineStride;
/* 199 */         for (int w = 0; w < dstWidth; w++) {
/* 200 */           d[dstPixelOffset] = this.byteTable[s[srcPixelOffset] & 0xFF];
/* 202 */           srcPixelOffset += srcPixelStride;
/* 203 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeRectUShort(RasterAccessor src, RasterAccessor dst) {
/* 212 */     int srcLineStride = src.getScanlineStride();
/* 213 */     int srcPixelStride = src.getPixelStride();
/* 214 */     int[] srcBandOffsets = src.getBandOffsets();
/* 215 */     short[][] srcData = src.getShortDataArrays();
/* 217 */     int dstLineStride = dst.getScanlineStride();
/* 218 */     int dstPixelStride = dst.getPixelStride();
/* 219 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 220 */     short[][] dstData = dst.getShortDataArrays();
/* 222 */     int dstWidth = dst.getWidth();
/* 223 */     int dstHeight = dst.getHeight();
/* 224 */     int dstBands = dst.getNumBands();
/* 226 */     short max = -1;
/* 228 */     for (int b = 0; b < dstBands; b++) {
/* 229 */       short[] s = srcData[b];
/* 230 */       short[] d = dstData[b];
/* 232 */       int srcLineOffset = srcBandOffsets[b];
/* 233 */       int dstLineOffset = dstBandOffsets[b];
/* 235 */       for (int h = 0; h < dstHeight; h++) {
/* 236 */         int srcPixelOffset = srcLineOffset;
/* 237 */         int dstPixelOffset = dstLineOffset;
/* 239 */         srcLineOffset += srcLineStride;
/* 240 */         dstLineOffset += dstLineStride;
/* 242 */         for (int w = 0; w < dstWidth; w++) {
/* 243 */           double p = (s[srcPixelOffset] & 0xFFFF);
/* 244 */           if (p == 0.0D) {
/* 245 */             d[dstPixelOffset] = 1;
/* 246 */           } else if (p > USHORT_UP_BOUND) {
/* 247 */             d[dstPixelOffset] = max;
/*     */           } else {
/* 249 */             d[dstPixelOffset] = (short)(int)(Math.exp(p) + 0.5D);
/*     */           } 
/* 252 */           srcPixelOffset += srcPixelStride;
/* 253 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeRectShort(RasterAccessor src, RasterAccessor dst) {
/* 262 */     int srcLineStride = src.getScanlineStride();
/* 263 */     int srcPixelStride = src.getPixelStride();
/* 264 */     int[] srcBandOffsets = src.getBandOffsets();
/* 265 */     short[][] srcData = src.getShortDataArrays();
/* 267 */     int dstLineStride = dst.getScanlineStride();
/* 268 */     int dstPixelStride = dst.getPixelStride();
/* 269 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 270 */     short[][] dstData = dst.getShortDataArrays();
/* 272 */     int dstWidth = dst.getWidth();
/* 273 */     int dstHeight = dst.getHeight();
/* 274 */     int dstBands = dst.getNumBands();
/* 276 */     for (int b = 0; b < dstBands; b++) {
/* 277 */       short[] s = srcData[b];
/* 278 */       short[] d = dstData[b];
/* 280 */       int srcLineOffset = srcBandOffsets[b];
/* 281 */       int dstLineOffset = dstBandOffsets[b];
/* 283 */       for (int h = 0; h < dstHeight; h++) {
/* 284 */         int srcPixelOffset = srcLineOffset;
/* 285 */         int dstPixelOffset = dstLineOffset;
/* 287 */         srcLineOffset += srcLineStride;
/* 288 */         dstLineOffset += dstLineStride;
/* 290 */         for (int w = 0; w < dstWidth; w++) {
/* 291 */           double p = s[srcPixelOffset];
/* 292 */           if (p < LOW_BOUND) {
/* 293 */             d[dstPixelOffset] = 0;
/* 294 */           } else if (p == 0.0D) {
/* 295 */             d[dstPixelOffset] = 1;
/* 296 */           } else if (p > SHORT_UP_BOUND) {
/* 297 */             d[dstPixelOffset] = Short.MAX_VALUE;
/*     */           } else {
/* 299 */             d[dstPixelOffset] = (short)(int)(Math.exp(p) + 0.5D);
/*     */           } 
/* 302 */           srcPixelOffset += srcPixelStride;
/* 303 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeRectInt(RasterAccessor src, RasterAccessor dst) {
/* 312 */     int srcLineStride = src.getScanlineStride();
/* 313 */     int srcPixelStride = src.getPixelStride();
/* 314 */     int[] srcBandOffsets = src.getBandOffsets();
/* 315 */     int[][] srcData = src.getIntDataArrays();
/* 317 */     int dstLineStride = dst.getScanlineStride();
/* 318 */     int dstPixelStride = dst.getPixelStride();
/* 319 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 320 */     int[][] dstData = dst.getIntDataArrays();
/* 322 */     int dstWidth = dst.getWidth();
/* 323 */     int dstHeight = dst.getHeight();
/* 324 */     int dstBands = dst.getNumBands();
/* 326 */     for (int b = 0; b < dstBands; b++) {
/* 327 */       int[] s = srcData[b];
/* 328 */       int[] d = dstData[b];
/* 330 */       int srcLineOffset = srcBandOffsets[b];
/* 331 */       int dstLineOffset = dstBandOffsets[b];
/* 333 */       for (int h = 0; h < dstHeight; h++) {
/* 334 */         int srcPixelOffset = srcLineOffset;
/* 335 */         int dstPixelOffset = dstLineOffset;
/* 337 */         srcLineOffset += srcLineStride;
/* 338 */         dstLineOffset += dstLineStride;
/* 340 */         for (int w = 0; w < dstWidth; w++) {
/* 341 */           double p = s[srcPixelOffset];
/* 342 */           if (p < LOW_BOUND) {
/* 343 */             d[dstPixelOffset] = 0;
/* 344 */           } else if (p == 0.0D) {
/* 345 */             d[dstPixelOffset] = 1;
/* 346 */           } else if (p > INT_UP_BOUND) {
/* 347 */             d[dstPixelOffset] = Integer.MAX_VALUE;
/*     */           } else {
/* 349 */             d[dstPixelOffset] = (int)(Math.exp(p) + 0.5D);
/*     */           } 
/* 352 */           srcPixelOffset += srcPixelStride;
/* 353 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeRectFloat(RasterAccessor src, RasterAccessor dst) {
/* 362 */     int srcLineStride = src.getScanlineStride();
/* 363 */     int srcPixelStride = src.getPixelStride();
/* 364 */     int[] srcBandOffsets = src.getBandOffsets();
/* 365 */     float[][] srcData = src.getFloatDataArrays();
/* 367 */     int dstLineStride = dst.getScanlineStride();
/* 368 */     int dstPixelStride = dst.getPixelStride();
/* 369 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 370 */     float[][] dstData = dst.getFloatDataArrays();
/* 372 */     int dstWidth = dst.getWidth();
/* 373 */     int dstHeight = dst.getHeight();
/* 374 */     int dstBands = dst.getNumBands();
/* 376 */     for (int b = 0; b < dstBands; b++) {
/* 377 */       float[] s = srcData[b];
/* 378 */       float[] d = dstData[b];
/* 380 */       int srcLineOffset = srcBandOffsets[b];
/* 381 */       int dstLineOffset = dstBandOffsets[b];
/* 383 */       for (int h = 0; h < dstHeight; h++) {
/* 384 */         int srcPixelOffset = srcLineOffset;
/* 385 */         int dstPixelOffset = dstLineOffset;
/* 387 */         srcLineOffset += srcLineStride;
/* 388 */         dstLineOffset += dstLineStride;
/* 390 */         for (int w = 0; w < dstWidth; w++) {
/* 391 */           d[dstPixelOffset] = (float)Math.exp(s[srcPixelOffset]);
/* 393 */           srcPixelOffset += srcPixelStride;
/* 394 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeRectDouble(RasterAccessor src, RasterAccessor dst) {
/* 403 */     int srcLineStride = src.getScanlineStride();
/* 404 */     int srcPixelStride = src.getPixelStride();
/* 405 */     int[] srcBandOffsets = src.getBandOffsets();
/* 406 */     double[][] srcData = src.getDoubleDataArrays();
/* 408 */     int dstLineStride = dst.getScanlineStride();
/* 409 */     int dstPixelStride = dst.getPixelStride();
/* 410 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 411 */     double[][] dstData = dst.getDoubleDataArrays();
/* 413 */     int dstWidth = dst.getWidth();
/* 414 */     int dstHeight = dst.getHeight();
/* 415 */     int dstBands = dst.getNumBands();
/* 417 */     for (int b = 0; b < dstBands; b++) {
/* 418 */       double[] s = srcData[b];
/* 419 */       double[] d = dstData[b];
/* 421 */       int srcLineOffset = srcBandOffsets[b];
/* 422 */       int dstLineOffset = dstBandOffsets[b];
/* 424 */       for (int h = 0; h < dstHeight; h++) {
/* 425 */         int srcPixelOffset = srcLineOffset;
/* 426 */         int dstPixelOffset = dstLineOffset;
/* 428 */         srcLineOffset += srcLineStride;
/* 429 */         dstLineOffset += dstLineStride;
/* 431 */         for (int w = 0; w < dstWidth; w++) {
/* 432 */           d[dstPixelOffset] = Math.exp(s[srcPixelOffset]);
/* 434 */           srcPixelOffset += srcPixelStride;
/* 435 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private synchronized void initByteTable() {
/* 443 */     if (this.byteTable != null)
/*     */       return; 
/* 446 */     this.byteTable = new byte[256];
/* 453 */     this.byteTable[0] = 1;
/*     */     int i;
/* 455 */     for (i = 1; i < 6; i++)
/* 456 */       this.byteTable[i] = (byte)(int)(Math.exp(i) + 0.5D); 
/* 459 */     for (i = 6; i < 256; i++)
/* 460 */       this.byteTable[i] = -1; 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\ExpOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */