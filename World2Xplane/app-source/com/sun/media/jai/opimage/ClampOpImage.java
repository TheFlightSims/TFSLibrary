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
/*     */ final class ClampOpImage extends PointOpImage {
/*  49 */   private byte[][] byteTable = (byte[][])null;
/*     */   
/*     */   private final double[] low;
/*     */   
/*     */   private final double[] high;
/*     */   
/*     */   private synchronized void initByteTable() {
/*  59 */     if (this.byteTable == null) {
/*  61 */       int numBands = getSampleModel().getNumBands();
/*  62 */       this.byteTable = new byte[numBands][256];
/*  63 */       for (int b = 0; b < numBands; b++) {
/*  64 */         byte[] t = this.byteTable[b];
/*  65 */         int l = (int)this.low[b];
/*  66 */         int h = (int)this.high[b];
/*  68 */         byte bl = (byte)l;
/*  69 */         byte bh = (byte)h;
/*  71 */         for (int i = 0; i < 256; i++) {
/*  72 */           if (i < l) {
/*  73 */             t[i] = bl;
/*  74 */           } else if (i > h) {
/*  75 */             t[i] = bh;
/*     */           } else {
/*  77 */             t[i] = (byte)i;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public ClampOpImage(RenderedImage source, Map config, ImageLayout layout, double[] low, double[] high) {
/*  97 */     super(source, layout, config, true);
/*  99 */     int numBands = getSampleModel().getNumBands();
/* 101 */     if (low.length < numBands || high.length < numBands) {
/* 102 */       this.low = new double[numBands];
/* 103 */       this.high = new double[numBands];
/* 104 */       for (int i = 0; i < numBands; i++) {
/* 105 */         this.low[i] = low[0];
/* 106 */         this.high[i] = high[0];
/*     */       } 
/*     */     } else {
/* 109 */       this.low = (double[])low.clone();
/* 110 */       this.high = (double[])high.clone();
/*     */     } 
/* 114 */     permitInPlaceOperation();
/*     */   }
/*     */   
/*     */   protected void computeRect(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/* 130 */     RasterFormatTag[] formatTags = getFormatTags();
/* 132 */     Rectangle srcRect = mapDestRect(destRect, 0);
/* 134 */     RasterAccessor src = new RasterAccessor(sources[0], srcRect, formatTags[0], getSourceImage(0).getColorModel());
/* 137 */     RasterAccessor dst = new RasterAccessor(dest, destRect, formatTags[1], getColorModel());
/* 140 */     switch (dst.getDataType()) {
/*     */       case 0:
/* 142 */         computeRectByte(src, dst);
/*     */         break;
/*     */       case 1:
/* 145 */         computeRectUShort(src, dst);
/*     */         break;
/*     */       case 2:
/* 148 */         computeRectShort(src, dst);
/*     */         break;
/*     */       case 3:
/* 151 */         computeRectInt(src, dst);
/*     */         break;
/*     */       case 4:
/* 154 */         computeRectFloat(src, dst);
/*     */         break;
/*     */       case 5:
/* 157 */         computeRectDouble(src, dst);
/*     */         break;
/*     */     } 
/* 161 */     dst.copyDataToRaster();
/*     */   }
/*     */   
/*     */   private void computeRectByte(RasterAccessor src, RasterAccessor dst) {
/* 166 */     initByteTable();
/* 168 */     int dstWidth = dst.getWidth();
/* 169 */     int dstHeight = dst.getHeight();
/* 170 */     int dstBands = dst.getNumBands();
/* 172 */     int dstLineStride = dst.getScanlineStride();
/* 173 */     int dstPixelStride = dst.getPixelStride();
/* 174 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 175 */     byte[][] dstData = dst.getByteDataArrays();
/* 177 */     int srcLineStride = src.getScanlineStride();
/* 178 */     int srcPixelStride = src.getPixelStride();
/* 179 */     int[] srcBandOffsets = src.getBandOffsets();
/* 180 */     byte[][] srcData = src.getByteDataArrays();
/* 182 */     for (int b = 0; b < dstBands; b++) {
/* 183 */       byte[] d = dstData[b];
/* 184 */       byte[] s = srcData[b];
/* 185 */       byte[] t = this.byteTable[b];
/* 187 */       int dstLineOffset = dstBandOffsets[b];
/* 188 */       int srcLineOffset = srcBandOffsets[b];
/* 190 */       for (int h = 0; h < dstHeight; h++) {
/* 191 */         int dstPixelOffset = dstLineOffset;
/* 192 */         int srcPixelOffset = srcLineOffset;
/* 194 */         dstLineOffset += dstLineStride;
/* 195 */         srcLineOffset += srcLineStride;
/* 197 */         for (int w = 0; w < dstWidth; w++) {
/* 198 */           d[dstPixelOffset] = t[s[srcPixelOffset] & 0xFF];
/* 201 */           dstPixelOffset += dstPixelStride;
/* 202 */           srcPixelOffset += srcPixelStride;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeRectUShort(RasterAccessor src, RasterAccessor dst) {
/* 210 */     int dstWidth = dst.getWidth();
/* 211 */     int dstHeight = dst.getHeight();
/* 212 */     int dstBands = dst.getNumBands();
/* 214 */     int dstLineStride = dst.getScanlineStride();
/* 215 */     int dstPixelStride = dst.getPixelStride();
/* 216 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 217 */     short[][] dstData = dst.getShortDataArrays();
/* 219 */     int srcLineStride = src.getScanlineStride();
/* 220 */     int srcPixelStride = src.getPixelStride();
/* 221 */     int[] srcBandOffsets = src.getBandOffsets();
/* 222 */     short[][] srcData = src.getShortDataArrays();
/* 224 */     for (int b = 0; b < dstBands; b++) {
/* 225 */       short[] d = dstData[b];
/* 226 */       short[] s = srcData[b];
/* 227 */       int lo = (int)this.low[b];
/* 228 */       int hi = (int)this.high[b];
/* 230 */       short slo = (short)lo;
/* 231 */       short shi = (short)hi;
/* 233 */       int dstLineOffset = dstBandOffsets[b];
/* 234 */       int srcLineOffset = srcBandOffsets[b];
/* 236 */       for (int h = 0; h < dstHeight; h++) {
/* 237 */         int dstPixelOffset = dstLineOffset;
/* 238 */         int srcPixelOffset = srcLineOffset;
/* 240 */         dstLineOffset += dstLineStride;
/* 241 */         srcLineOffset += srcLineStride;
/* 243 */         for (int w = 0; w < dstWidth; w++) {
/* 244 */           int p = s[srcPixelOffset] & 0xFFFF;
/* 245 */           if (p < lo) {
/* 246 */             d[dstPixelOffset] = slo;
/* 247 */           } else if (p > hi) {
/* 248 */             d[dstPixelOffset] = shi;
/*     */           } else {
/* 250 */             d[dstPixelOffset] = (short)p;
/*     */           } 
/* 253 */           dstPixelOffset += dstPixelStride;
/* 254 */           srcPixelOffset += srcPixelStride;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeRectShort(RasterAccessor src, RasterAccessor dst) {
/* 262 */     int dstWidth = dst.getWidth();
/* 263 */     int dstHeight = dst.getHeight();
/* 264 */     int dstBands = dst.getNumBands();
/* 266 */     int dstLineStride = dst.getScanlineStride();
/* 267 */     int dstPixelStride = dst.getPixelStride();
/* 268 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 269 */     short[][] dstData = dst.getShortDataArrays();
/* 271 */     int srcLineStride = src.getScanlineStride();
/* 272 */     int srcPixelStride = src.getPixelStride();
/* 273 */     int[] srcBandOffsets = src.getBandOffsets();
/* 274 */     short[][] srcData = src.getShortDataArrays();
/* 276 */     for (int b = 0; b < dstBands; b++) {
/* 277 */       short[] d = dstData[b];
/* 278 */       short[] s = srcData[b];
/* 279 */       int lo = (int)this.low[b];
/* 280 */       int hi = (int)this.high[b];
/* 282 */       short slo = (short)lo;
/* 283 */       short shi = (short)hi;
/* 285 */       int dstLineOffset = dstBandOffsets[b];
/* 286 */       int srcLineOffset = srcBandOffsets[b];
/* 288 */       for (int h = 0; h < dstHeight; h++) {
/* 289 */         int dstPixelOffset = dstLineOffset;
/* 290 */         int srcPixelOffset = srcLineOffset;
/* 292 */         dstLineOffset += dstLineStride;
/* 293 */         srcLineOffset += srcLineStride;
/* 295 */         for (int w = 0; w < dstWidth; w++) {
/* 296 */           short p = s[srcPixelOffset];
/* 297 */           if (p < lo) {
/* 298 */             d[dstPixelOffset] = slo;
/* 299 */           } else if (p > hi) {
/* 300 */             d[dstPixelOffset] = shi;
/*     */           } else {
/* 302 */             d[dstPixelOffset] = p;
/*     */           } 
/* 305 */           dstPixelOffset += dstPixelStride;
/* 306 */           srcPixelOffset += srcPixelStride;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeRectInt(RasterAccessor src, RasterAccessor dst) {
/* 314 */     int dstWidth = dst.getWidth();
/* 315 */     int dstHeight = dst.getHeight();
/* 316 */     int dstBands = dst.getNumBands();
/* 318 */     int dstLineStride = dst.getScanlineStride();
/* 319 */     int dstPixelStride = dst.getPixelStride();
/* 320 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 321 */     int[][] dstData = dst.getIntDataArrays();
/* 323 */     int srcLineStride = src.getScanlineStride();
/* 324 */     int srcPixelStride = src.getPixelStride();
/* 325 */     int[] srcBandOffsets = src.getBandOffsets();
/* 326 */     int[][] srcData = src.getIntDataArrays();
/* 328 */     for (int b = 0; b < dstBands; b++) {
/* 329 */       int[] d = dstData[b];
/* 330 */       int[] s = srcData[b];
/* 331 */       double lo = this.low[b];
/* 332 */       double hi = this.high[b];
/* 334 */       int ilo = (int)lo;
/* 335 */       int ihi = (int)hi;
/* 337 */       int dstLineOffset = dstBandOffsets[b];
/* 338 */       int srcLineOffset = srcBandOffsets[b];
/* 340 */       for (int h = 0; h < dstHeight; h++) {
/* 341 */         int dstPixelOffset = dstLineOffset;
/* 342 */         int srcPixelOffset = srcLineOffset;
/* 344 */         dstLineOffset += dstLineStride;
/* 345 */         srcLineOffset += srcLineStride;
/* 347 */         for (int w = 0; w < dstWidth; w++) {
/* 348 */           int p = s[srcPixelOffset];
/* 349 */           if (p < lo) {
/* 350 */             d[dstPixelOffset] = ilo;
/* 351 */           } else if (p > hi) {
/* 352 */             d[dstPixelOffset] = ihi;
/*     */           } else {
/* 354 */             d[dstPixelOffset] = p;
/*     */           } 
/* 357 */           dstPixelOffset += dstPixelStride;
/* 358 */           srcPixelOffset += srcPixelStride;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeRectFloat(RasterAccessor src, RasterAccessor dst) {
/* 366 */     int dstWidth = dst.getWidth();
/* 367 */     int dstHeight = dst.getHeight();
/* 368 */     int dstBands = dst.getNumBands();
/* 370 */     int dstLineStride = dst.getScanlineStride();
/* 371 */     int dstPixelStride = dst.getPixelStride();
/* 372 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 373 */     float[][] dstData = dst.getFloatDataArrays();
/* 375 */     int srcLineStride = src.getScanlineStride();
/* 376 */     int srcPixelStride = src.getPixelStride();
/* 377 */     int[] srcBandOffsets = src.getBandOffsets();
/* 378 */     float[][] srcData = src.getFloatDataArrays();
/* 380 */     for (int b = 0; b < dstBands; b++) {
/* 381 */       float[] d = dstData[b];
/* 382 */       float[] s = srcData[b];
/* 383 */       double lo = this.low[b];
/* 384 */       double hi = this.high[b];
/* 386 */       float flo = (float)lo;
/* 387 */       float fhi = (float)hi;
/* 389 */       int dstLineOffset = dstBandOffsets[b];
/* 390 */       int srcLineOffset = srcBandOffsets[b];
/* 392 */       for (int h = 0; h < dstHeight; h++) {
/* 393 */         int dstPixelOffset = dstLineOffset;
/* 394 */         int srcPixelOffset = srcLineOffset;
/* 396 */         dstLineOffset += dstLineStride;
/* 397 */         srcLineOffset += srcLineStride;
/* 399 */         for (int w = 0; w < dstWidth; w++) {
/* 400 */           float p = s[srcPixelOffset];
/* 401 */           if (p < lo) {
/* 402 */             d[dstPixelOffset] = flo;
/* 403 */           } else if (p > hi) {
/* 404 */             d[dstPixelOffset] = fhi;
/*     */           } else {
/* 406 */             d[dstPixelOffset] = p;
/*     */           } 
/* 409 */           dstPixelOffset += dstPixelStride;
/* 410 */           srcPixelOffset += srcPixelStride;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeRectDouble(RasterAccessor src, RasterAccessor dst) {
/* 418 */     int dstWidth = dst.getWidth();
/* 419 */     int dstHeight = dst.getHeight();
/* 420 */     int dstBands = dst.getNumBands();
/* 422 */     int dstLineStride = dst.getScanlineStride();
/* 423 */     int dstPixelStride = dst.getPixelStride();
/* 424 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 425 */     double[][] dstData = dst.getDoubleDataArrays();
/* 427 */     int srcLineStride = src.getScanlineStride();
/* 428 */     int srcPixelStride = src.getPixelStride();
/* 429 */     int[] srcBandOffsets = src.getBandOffsets();
/* 430 */     double[][] srcData = src.getDoubleDataArrays();
/* 432 */     for (int b = 0; b < dstBands; b++) {
/* 433 */       double[] d = dstData[b];
/* 434 */       double[] s = srcData[b];
/* 435 */       double lo = this.low[b];
/* 436 */       double hi = this.high[b];
/* 438 */       int dstLineOffset = dstBandOffsets[b];
/* 439 */       int srcLineOffset = srcBandOffsets[b];
/* 441 */       for (int h = 0; h < dstHeight; h++) {
/* 442 */         int dstPixelOffset = dstLineOffset;
/* 443 */         int srcPixelOffset = srcLineOffset;
/* 445 */         dstLineOffset += dstLineStride;
/* 446 */         srcLineOffset += srcLineStride;
/* 448 */         for (int w = 0; w < dstWidth; w++) {
/* 449 */           double p = s[srcPixelOffset];
/* 450 */           if (p < lo) {
/* 451 */             d[dstPixelOffset] = lo;
/* 452 */           } else if (p > hi) {
/* 453 */             d[dstPixelOffset] = hi;
/*     */           } else {
/* 455 */             d[dstPixelOffset] = p;
/*     */           } 
/* 458 */           dstPixelOffset += dstPixelStride;
/* 459 */           srcPixelOffset += srcPixelStride;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\ClampOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */