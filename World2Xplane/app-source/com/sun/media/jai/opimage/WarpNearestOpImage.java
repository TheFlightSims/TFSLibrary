/*     */ package com.sun.media.jai.opimage;
/*     */ 
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.image.ColorModel;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.util.Map;
/*     */ import javax.media.jai.ImageLayout;
/*     */ import javax.media.jai.Interpolation;
/*     */ import javax.media.jai.PlanarImage;
/*     */ import javax.media.jai.RasterAccessor;
/*     */ import javax.media.jai.RasterFormatTag;
/*     */ import javax.media.jai.Warp;
/*     */ import javax.media.jai.WarpOpImage;
/*     */ import javax.media.jai.iterator.RandomIter;
/*     */ import javax.media.jai.iterator.RandomIterFactory;
/*     */ 
/*     */ final class WarpNearestOpImage extends WarpOpImage {
/*     */   public WarpNearestOpImage(RenderedImage source, Map config, ImageLayout layout, Warp warp, Interpolation interp, double[] backgroundValues) {
/*  64 */     super(source, layout, config, false, null, interp, warp, backgroundValues);
/*  79 */     ColorModel srcColorModel = source.getColorModel();
/*  80 */     if (srcColorModel instanceof java.awt.image.IndexColorModel) {
/*  81 */       this.sampleModel = source.getSampleModel().createCompatibleSampleModel(this.tileWidth, this.tileHeight);
/*  83 */       this.colorModel = srcColorModel;
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void computeRect(PlanarImage[] sources, WritableRaster dest, Rectangle destRect) {
/*  92 */     RasterFormatTag[] formatTags = getFormatTags();
/*  94 */     RasterAccessor d = new RasterAccessor(dest, destRect, formatTags[1], getColorModel());
/*  97 */     switch (d.getDataType()) {
/*     */       case 0:
/*  99 */         computeRectByte(sources[0], d);
/*     */         break;
/*     */       case 1:
/* 102 */         computeRectUShort(sources[0], d);
/*     */         break;
/*     */       case 2:
/* 105 */         computeRectShort(sources[0], d);
/*     */         break;
/*     */       case 3:
/* 108 */         computeRectInt(sources[0], d);
/*     */         break;
/*     */       case 4:
/* 111 */         computeRectFloat(sources[0], d);
/*     */         break;
/*     */       case 5:
/* 114 */         computeRectDouble(sources[0], d);
/*     */         break;
/*     */     } 
/* 118 */     if (d.isDataCopy()) {
/* 119 */       d.clampDataArrays();
/* 120 */       d.copyDataToRaster();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeRectByte(PlanarImage src, RasterAccessor dst) {
/* 125 */     RandomIter iter = RandomIterFactory.create((RenderedImage)src, src.getBounds());
/* 127 */     int minX = src.getMinX();
/* 128 */     int maxX = src.getMaxX();
/* 129 */     int minY = src.getMinY();
/* 130 */     int maxY = src.getMaxY();
/* 132 */     int dstWidth = dst.getWidth();
/* 133 */     int dstHeight = dst.getHeight();
/* 134 */     int dstBands = dst.getNumBands();
/* 136 */     int lineStride = dst.getScanlineStride();
/* 137 */     int pixelStride = dst.getPixelStride();
/* 138 */     int[] bandOffsets = dst.getBandOffsets();
/* 139 */     byte[][] data = dst.getByteDataArrays();
/* 141 */     float[] warpData = new float[2 * dstWidth];
/* 143 */     int lineOffset = 0;
/* 145 */     byte[] backgroundByte = new byte[dstBands];
/* 146 */     for (int i = 0; i < dstBands; i++)
/* 147 */       backgroundByte[i] = (byte)(int)this.backgroundValues[i]; 
/* 149 */     for (int h = 0; h < dstHeight; h++) {
/* 150 */       int pixelOffset = lineOffset;
/* 151 */       lineOffset += lineStride;
/* 153 */       this.warp.warpRect(dst.getX(), dst.getY() + h, dstWidth, 1, warpData);
/* 155 */       int count = 0;
/* 156 */       for (int w = 0; w < dstWidth; w++) {
/* 163 */         int sx = round(warpData[count++]);
/* 164 */         int sy = round(warpData[count++]);
/* 166 */         if (sx < minX || sx >= maxX || sy < minY || sy >= maxY) {
/* 168 */           if (this.setBackground)
/* 169 */             for (int b = 0; b < dstBands; b++)
/* 170 */               data[b][pixelOffset + bandOffsets[b]] = backgroundByte[b];  
/*     */         } else {
/* 175 */           for (int b = 0; b < dstBands; b++)
/* 176 */             data[b][pixelOffset + bandOffsets[b]] = (byte)(iter.getSample(sx, sy, b) & 0xFF); 
/*     */         } 
/* 181 */         pixelOffset += pixelStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeRectUShort(PlanarImage src, RasterAccessor dst) {
/* 187 */     RandomIter iter = RandomIterFactory.create((RenderedImage)src, src.getBounds());
/* 189 */     int minX = src.getMinX();
/* 190 */     int maxX = src.getMaxX();
/* 191 */     int minY = src.getMinY();
/* 192 */     int maxY = src.getMaxY();
/* 194 */     int dstWidth = dst.getWidth();
/* 195 */     int dstHeight = dst.getHeight();
/* 196 */     int dstBands = dst.getNumBands();
/* 198 */     int lineStride = dst.getScanlineStride();
/* 199 */     int pixelStride = dst.getPixelStride();
/* 200 */     int[] bandOffsets = dst.getBandOffsets();
/* 201 */     short[][] data = dst.getShortDataArrays();
/* 203 */     float[] warpData = new float[2 * dstWidth];
/* 205 */     int lineOffset = 0;
/* 207 */     short[] backgroundUShort = new short[dstBands];
/* 208 */     for (int i = 0; i < dstBands; i++)
/* 209 */       backgroundUShort[i] = (short)(int)this.backgroundValues[i]; 
/* 211 */     for (int h = 0; h < dstHeight; h++) {
/* 212 */       int pixelOffset = lineOffset;
/* 213 */       lineOffset += lineStride;
/* 215 */       this.warp.warpRect(dst.getX(), dst.getY() + h, dstWidth, 1, warpData);
/* 217 */       int count = 0;
/* 218 */       for (int w = 0; w < dstWidth; w++) {
/* 225 */         int sx = round(warpData[count++]);
/* 226 */         int sy = round(warpData[count++]);
/* 228 */         if (sx < minX || sx >= maxX || sy < minY || sy >= maxY) {
/* 230 */           if (this.setBackground)
/* 231 */             for (int b = 0; b < dstBands; b++)
/* 232 */               data[b][pixelOffset + bandOffsets[b]] = backgroundUShort[b];  
/*     */         } else {
/* 237 */           for (int b = 0; b < dstBands; b++)
/* 238 */             data[b][pixelOffset + bandOffsets[b]] = (short)(iter.getSample(sx, sy, b) & 0xFFFF); 
/*     */         } 
/* 243 */         pixelOffset += pixelStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeRectShort(PlanarImage src, RasterAccessor dst) {
/* 249 */     RandomIter iter = RandomIterFactory.create((RenderedImage)src, src.getBounds());
/* 251 */     int minX = src.getMinX();
/* 252 */     int maxX = src.getMaxX();
/* 253 */     int minY = src.getMinY();
/* 254 */     int maxY = src.getMaxY();
/* 256 */     int dstWidth = dst.getWidth();
/* 257 */     int dstHeight = dst.getHeight();
/* 258 */     int dstBands = dst.getNumBands();
/* 260 */     int lineStride = dst.getScanlineStride();
/* 261 */     int pixelStride = dst.getPixelStride();
/* 262 */     int[] bandOffsets = dst.getBandOffsets();
/* 263 */     short[][] data = dst.getShortDataArrays();
/* 265 */     float[] warpData = new float[2 * dstWidth];
/* 267 */     int lineOffset = 0;
/* 269 */     short[] backgroundShort = new short[dstBands];
/* 270 */     for (int i = 0; i < dstBands; i++)
/* 271 */       backgroundShort[i] = (short)(int)this.backgroundValues[i]; 
/* 273 */     for (int h = 0; h < dstHeight; h++) {
/* 274 */       int pixelOffset = lineOffset;
/* 275 */       lineOffset += lineStride;
/* 277 */       this.warp.warpRect(dst.getX(), dst.getY() + h, dstWidth, 1, warpData);
/* 279 */       int count = 0;
/* 280 */       for (int w = 0; w < dstWidth; w++) {
/* 287 */         int sx = round(warpData[count++]);
/* 288 */         int sy = round(warpData[count++]);
/* 290 */         if (sx < minX || sx >= maxX || sy < minY || sy >= maxY) {
/* 292 */           if (this.setBackground)
/* 293 */             for (int b = 0; b < dstBands; b++)
/* 294 */               data[b][pixelOffset + bandOffsets[b]] = backgroundShort[b];  
/*     */         } else {
/* 299 */           for (int b = 0; b < dstBands; b++)
/* 300 */             data[b][pixelOffset + bandOffsets[b]] = (short)iter.getSample(sx, sy, b); 
/*     */         } 
/* 305 */         pixelOffset += pixelStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeRectInt(PlanarImage src, RasterAccessor dst) {
/* 311 */     RandomIter iter = RandomIterFactory.create((RenderedImage)src, src.getBounds());
/* 313 */     int minX = src.getMinX();
/* 314 */     int maxX = src.getMaxX();
/* 315 */     int minY = src.getMinY();
/* 316 */     int maxY = src.getMaxY();
/* 318 */     int dstWidth = dst.getWidth();
/* 319 */     int dstHeight = dst.getHeight();
/* 320 */     int dstBands = dst.getNumBands();
/* 322 */     int lineStride = dst.getScanlineStride();
/* 323 */     int pixelStride = dst.getPixelStride();
/* 324 */     int[] bandOffsets = dst.getBandOffsets();
/* 325 */     int[][] data = dst.getIntDataArrays();
/* 327 */     float[] warpData = new float[2 * dstWidth];
/* 329 */     int lineOffset = 0;
/* 331 */     int[] backgroundInt = new int[dstBands];
/* 332 */     for (int i = 0; i < dstBands; i++)
/* 333 */       backgroundInt[i] = (int)this.backgroundValues[i]; 
/* 335 */     for (int h = 0; h < dstHeight; h++) {
/* 336 */       int pixelOffset = lineOffset;
/* 337 */       lineOffset += lineStride;
/* 339 */       this.warp.warpRect(dst.getX(), dst.getY() + h, dstWidth, 1, warpData);
/* 341 */       int count = 0;
/* 342 */       for (int w = 0; w < dstWidth; w++) {
/* 349 */         int sx = round(warpData[count++]);
/* 350 */         int sy = round(warpData[count++]);
/* 352 */         if (sx < minX || sx >= maxX || sy < minY || sy >= maxY) {
/* 354 */           if (this.setBackground)
/* 355 */             for (int b = 0; b < dstBands; b++)
/* 356 */               data[b][pixelOffset + bandOffsets[b]] = backgroundInt[b];  
/*     */         } else {
/* 361 */           for (int b = 0; b < dstBands; b++)
/* 362 */             data[b][pixelOffset + bandOffsets[b]] = iter.getSample(sx, sy, b); 
/*     */         } 
/* 367 */         pixelOffset += pixelStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeRectFloat(PlanarImage src, RasterAccessor dst) {
/* 373 */     RandomIter iter = RandomIterFactory.create((RenderedImage)src, src.getBounds());
/* 375 */     int minX = src.getMinX();
/* 376 */     int maxX = src.getMaxX();
/* 377 */     int minY = src.getMinY();
/* 378 */     int maxY = src.getMaxY();
/* 380 */     int dstWidth = dst.getWidth();
/* 381 */     int dstHeight = dst.getHeight();
/* 382 */     int dstBands = dst.getNumBands();
/* 384 */     int lineStride = dst.getScanlineStride();
/* 385 */     int pixelStride = dst.getPixelStride();
/* 386 */     int[] bandOffsets = dst.getBandOffsets();
/* 387 */     float[][] data = dst.getFloatDataArrays();
/* 389 */     float[] warpData = new float[2 * dstWidth];
/* 391 */     int lineOffset = 0;
/* 393 */     float[] backgroundFloat = new float[dstBands];
/* 394 */     for (int i = 0; i < dstBands; i++)
/* 395 */       backgroundFloat[i] = (float)this.backgroundValues[i]; 
/* 397 */     for (int h = 0; h < dstHeight; h++) {
/* 398 */       int pixelOffset = lineOffset;
/* 399 */       lineOffset += lineStride;
/* 401 */       this.warp.warpRect(dst.getX(), dst.getY() + h, dstWidth, 1, warpData);
/* 403 */       int count = 0;
/* 404 */       for (int w = 0; w < dstWidth; w++) {
/* 411 */         int sx = round(warpData[count++]);
/* 412 */         int sy = round(warpData[count++]);
/* 414 */         if (sx < minX || sx >= maxX || sy < minY || sy >= maxY) {
/* 416 */           if (this.setBackground)
/* 417 */             for (int b = 0; b < dstBands; b++)
/* 418 */               data[b][pixelOffset + bandOffsets[b]] = backgroundFloat[b];  
/*     */         } else {
/* 423 */           for (int b = 0; b < dstBands; b++)
/* 424 */             data[b][pixelOffset + bandOffsets[b]] = iter.getSampleFloat(sx, sy, b); 
/*     */         } 
/* 429 */         pixelOffset += pixelStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeRectDouble(PlanarImage src, RasterAccessor dst) {
/* 435 */     RandomIter iter = RandomIterFactory.create((RenderedImage)src, src.getBounds());
/* 437 */     int minX = src.getMinX();
/* 438 */     int maxX = src.getMaxX();
/* 439 */     int minY = src.getMinY();
/* 440 */     int maxY = src.getMaxY();
/* 442 */     int dstWidth = dst.getWidth();
/* 443 */     int dstHeight = dst.getHeight();
/* 444 */     int dstBands = dst.getNumBands();
/* 446 */     int lineStride = dst.getScanlineStride();
/* 447 */     int pixelStride = dst.getPixelStride();
/* 448 */     int[] bandOffsets = dst.getBandOffsets();
/* 449 */     double[][] data = dst.getDoubleDataArrays();
/* 451 */     float[] warpData = new float[2 * dstWidth];
/* 453 */     int lineOffset = 0;
/* 455 */     for (int h = 0; h < dstHeight; h++) {
/* 456 */       int pixelOffset = lineOffset;
/* 457 */       lineOffset += lineStride;
/* 459 */       this.warp.warpRect(dst.getX(), dst.getY() + h, dstWidth, 1, warpData);
/* 461 */       int count = 0;
/* 462 */       for (int w = 0; w < dstWidth; w++) {
/* 469 */         int sx = round(warpData[count++]);
/* 470 */         int sy = round(warpData[count++]);
/* 472 */         if (sx < minX || sx >= maxX || sy < minY || sy >= maxY) {
/* 474 */           if (this.setBackground)
/* 475 */             for (int b = 0; b < dstBands; b++)
/* 476 */               data[b][pixelOffset + bandOffsets[b]] = this.backgroundValues[b];  
/*     */         } else {
/* 481 */           for (int b = 0; b < dstBands; b++)
/* 482 */             data[b][pixelOffset + bandOffsets[b]] = iter.getSampleDouble(sx, sy, b); 
/*     */         } 
/* 487 */         pixelOffset += pixelStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private static final int round(float f) {
/* 494 */     return (f >= 0.0F) ? (int)(f + 0.5F) : (int)(f - 0.5F);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\WarpNearestOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */