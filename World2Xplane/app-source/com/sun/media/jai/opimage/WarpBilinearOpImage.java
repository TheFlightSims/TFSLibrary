/*     */ package com.sun.media.jai.opimage;
/*     */ 
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.image.ColorModel;
/*     */ import java.awt.image.IndexColorModel;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.util.Map;
/*     */ import javax.media.jai.BorderExtender;
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
/*     */ final class WarpBilinearOpImage extends WarpOpImage {
/*  46 */   private byte[][] ctable = (byte[][])null;
/*     */   
/*     */   public WarpBilinearOpImage(RenderedImage source, BorderExtender extender, Map config, ImageLayout layout, Warp warp, Interpolation interp, double[] backgroundValues) {
/*  64 */     super(source, layout, config, false, extender, interp, warp, backgroundValues);
/*  78 */     ColorModel srcColorModel = source.getColorModel();
/*  79 */     if (srcColorModel instanceof IndexColorModel) {
/*  80 */       IndexColorModel icm = (IndexColorModel)srcColorModel;
/*  81 */       this.ctable = new byte[3][icm.getMapSize()];
/*  82 */       icm.getReds(this.ctable[0]);
/*  83 */       icm.getGreens(this.ctable[1]);
/*  84 */       icm.getBlues(this.ctable[2]);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void computeRect(PlanarImage[] sources, WritableRaster dest, Rectangle destRect) {
/*  93 */     RasterFormatTag[] formatTags = getFormatTags();
/*  95 */     RasterAccessor d = new RasterAccessor(dest, destRect, formatTags[1], getColorModel());
/*  98 */     switch (d.getDataType()) {
/*     */       case 0:
/* 100 */         computeRectByte(sources[0], d);
/*     */         break;
/*     */       case 1:
/* 103 */         computeRectUShort(sources[0], d);
/*     */         break;
/*     */       case 2:
/* 106 */         computeRectShort(sources[0], d);
/*     */         break;
/*     */       case 3:
/* 109 */         computeRectInt(sources[0], d);
/*     */         break;
/*     */       case 4:
/* 112 */         computeRectFloat(sources[0], d);
/*     */         break;
/*     */       case 5:
/* 115 */         computeRectDouble(sources[0], d);
/*     */         break;
/*     */     } 
/* 119 */     if (d.isDataCopy()) {
/* 120 */       d.clampDataArrays();
/* 121 */       d.copyDataToRaster();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeRectByte(PlanarImage src, RasterAccessor dst) {
/*     */     RandomIter iter;
/* 127 */     if (this.extender != null) {
/* 128 */       Rectangle bounds = new Rectangle(src.getMinX(), src.getMinY(), src.getWidth() + 1, src.getHeight() + 1);
/* 131 */       iter = RandomIterFactory.create(src.getExtendedData(bounds, this.extender), bounds);
/*     */     } else {
/* 135 */       iter = RandomIterFactory.create((RenderedImage)src, src.getBounds());
/*     */     } 
/* 138 */     int minX = src.getMinX();
/* 139 */     int maxX = src.getMaxX() - ((this.extender != null) ? 0 : 1);
/* 141 */     int minY = src.getMinY();
/* 142 */     int maxY = src.getMaxY() - ((this.extender != null) ? 0 : 1);
/* 145 */     int dstWidth = dst.getWidth();
/* 146 */     int dstHeight = dst.getHeight();
/* 147 */     int dstBands = dst.getNumBands();
/* 149 */     int lineStride = dst.getScanlineStride();
/* 150 */     int pixelStride = dst.getPixelStride();
/* 151 */     int[] bandOffsets = dst.getBandOffsets();
/* 152 */     byte[][] data = dst.getByteDataArrays();
/* 154 */     float[] warpData = new float[2 * dstWidth];
/* 156 */     int lineOffset = 0;
/* 158 */     byte[] backgroundByte = new byte[dstBands];
/* 159 */     for (int i = 0; i < dstBands; i++)
/* 160 */       backgroundByte[i] = (byte)(int)this.backgroundValues[i]; 
/* 162 */     if (this.ctable == null) {
/* 163 */       for (int h = 0; h < dstHeight; h++) {
/* 164 */         int pixelOffset = lineOffset;
/* 165 */         lineOffset += lineStride;
/* 167 */         this.warp.warpRect(dst.getX(), dst.getY() + h, dstWidth, 1, warpData);
/* 169 */         int count = 0;
/* 170 */         for (int w = 0; w < dstWidth; w++) {
/* 171 */           float sx = warpData[count++];
/* 172 */           float sy = warpData[count++];
/* 174 */           int xint = floor(sx);
/* 175 */           int yint = floor(sy);
/* 176 */           float xfrac = sx - xint;
/* 177 */           float yfrac = sy - yint;
/* 179 */           if (xint < minX || xint >= maxX || yint < minY || yint >= maxY) {
/* 182 */             if (this.setBackground)
/* 183 */               for (int b = 0; b < dstBands; b++)
/* 184 */                 data[b][pixelOffset + bandOffsets[b]] = backgroundByte[b];  
/*     */           } else {
/* 189 */             for (int b = 0; b < dstBands; b++) {
/* 190 */               int s00 = iter.getSample(xint, yint, b) & 0xFF;
/* 191 */               int s01 = iter.getSample(xint + 1, yint, b) & 0xFF;
/* 192 */               int s10 = iter.getSample(xint, yint + 1, b) & 0xFF;
/* 193 */               int s11 = iter.getSample(xint + 1, yint + 1, b) & 0xFF;
/* 195 */               float s0 = (s01 - s00) * xfrac + s00;
/* 196 */               float s1 = (s11 - s10) * xfrac + s10;
/* 197 */               float s = (s1 - s0) * yfrac + s0;
/* 199 */               data[b][pixelOffset + bandOffsets[b]] = (byte)(int)s;
/*     */             } 
/*     */           } 
/* 203 */           pixelOffset += pixelStride;
/*     */         } 
/*     */       } 
/*     */     } else {
/* 207 */       for (int h = 0; h < dstHeight; h++) {
/* 208 */         int pixelOffset = lineOffset;
/* 209 */         lineOffset += lineStride;
/* 211 */         this.warp.warpRect(dst.getX(), dst.getY() + h, dstWidth, 1, warpData);
/* 213 */         int count = 0;
/* 214 */         for (int w = 0; w < dstWidth; w++) {
/* 215 */           float sx = warpData[count++];
/* 216 */           float sy = warpData[count++];
/* 218 */           int xint = floor(sx);
/* 219 */           int yint = floor(sy);
/* 220 */           float xfrac = sx - xint;
/* 221 */           float yfrac = sy - yint;
/* 223 */           if (xint < minX || xint >= maxX || yint < minY || yint >= maxY) {
/* 226 */             if (this.setBackground)
/* 227 */               for (int b = 0; b < dstBands; b++)
/* 228 */                 data[b][pixelOffset + bandOffsets[b]] = backgroundByte[b];  
/*     */           } else {
/* 233 */             for (int b = 0; b < dstBands; b++) {
/* 234 */               byte[] t = this.ctable[b];
/* 236 */               int s00 = t[iter.getSample(xint, yint, 0) & 0xFF] & 0xFF;
/* 238 */               int s01 = t[iter.getSample(xint + 1, yint, 0) & 0xFF] & 0xFF;
/* 240 */               int s10 = t[iter.getSample(xint, yint + 1, 0) & 0xFF] & 0xFF;
/* 242 */               int s11 = t[iter.getSample(xint + 1, yint + 1, 0) & 0xFF] & 0xFF;
/* 245 */               float s0 = (s01 - s00) * xfrac + s00;
/* 246 */               float s1 = (s11 - s10) * xfrac + s10;
/* 247 */               float s = (s1 - s0) * yfrac + s0;
/* 249 */               data[b][pixelOffset + bandOffsets[b]] = (byte)(int)s;
/*     */             } 
/*     */           } 
/* 253 */           pixelOffset += pixelStride;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeRectUShort(PlanarImage src, RasterAccessor dst) {
/*     */     RandomIter iter;
/* 261 */     if (this.extender != null) {
/* 262 */       Rectangle bounds = new Rectangle(src.getMinX(), src.getMinY(), src.getWidth() + 1, src.getHeight() + 1);
/* 265 */       iter = RandomIterFactory.create(src.getExtendedData(bounds, this.extender), bounds);
/*     */     } else {
/* 269 */       iter = RandomIterFactory.create((RenderedImage)src, src.getBounds());
/*     */     } 
/* 272 */     int minX = src.getMinX();
/* 273 */     int maxX = src.getMaxX() - ((this.extender != null) ? 0 : 1);
/* 275 */     int minY = src.getMinY();
/* 276 */     int maxY = src.getMaxY() - ((this.extender != null) ? 0 : 1);
/* 279 */     int dstWidth = dst.getWidth();
/* 280 */     int dstHeight = dst.getHeight();
/* 281 */     int dstBands = dst.getNumBands();
/* 283 */     int lineStride = dst.getScanlineStride();
/* 284 */     int pixelStride = dst.getPixelStride();
/* 285 */     int[] bandOffsets = dst.getBandOffsets();
/* 286 */     short[][] data = dst.getShortDataArrays();
/* 288 */     float[] warpData = new float[2 * dstWidth];
/* 290 */     int lineOffset = 0;
/* 292 */     short[] backgroundUShort = new short[dstBands];
/* 293 */     for (int i = 0; i < dstBands; i++)
/* 294 */       backgroundUShort[i] = (short)(int)this.backgroundValues[i]; 
/* 296 */     for (int h = 0; h < dstHeight; h++) {
/* 297 */       int pixelOffset = lineOffset;
/* 298 */       lineOffset += lineStride;
/* 300 */       this.warp.warpRect(dst.getX(), dst.getY() + h, dstWidth, 1, warpData);
/* 302 */       int count = 0;
/* 303 */       for (int w = 0; w < dstWidth; w++) {
/* 304 */         float sx = warpData[count++];
/* 305 */         float sy = warpData[count++];
/* 307 */         int xint = floor(sx);
/* 308 */         int yint = floor(sy);
/* 309 */         float xfrac = sx - xint;
/* 310 */         float yfrac = sy - yint;
/* 312 */         if (xint < minX || xint >= maxX || yint < minY || yint >= maxY) {
/* 315 */           if (this.setBackground)
/* 316 */             for (int b = 0; b < dstBands; b++)
/* 317 */               data[b][pixelOffset + bandOffsets[b]] = backgroundUShort[b];  
/*     */         } else {
/* 322 */           for (int b = 0; b < dstBands; b++) {
/* 323 */             int s00 = iter.getSample(xint, yint, b) & 0xFFFF;
/* 324 */             int s01 = iter.getSample(xint + 1, yint, b) & 0xFFFF;
/* 325 */             int s10 = iter.getSample(xint, yint + 1, b) & 0xFFFF;
/* 326 */             int s11 = iter.getSample(xint + 1, yint + 1, b) & 0xFFFF;
/* 328 */             float s0 = (s01 - s00) * xfrac + s00;
/* 329 */             float s1 = (s11 - s10) * xfrac + s10;
/* 330 */             float s = (s1 - s0) * yfrac + s0;
/* 332 */             data[b][pixelOffset + bandOffsets[b]] = (short)(int)s;
/*     */           } 
/*     */         } 
/* 336 */         pixelOffset += pixelStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeRectShort(PlanarImage src, RasterAccessor dst) {
/*     */     RandomIter iter;
/* 343 */     if (this.extender != null) {
/* 344 */       Rectangle bounds = new Rectangle(src.getMinX(), src.getMinY(), src.getWidth() + 1, src.getHeight() + 1);
/* 347 */       iter = RandomIterFactory.create(src.getExtendedData(bounds, this.extender), bounds);
/*     */     } else {
/* 351 */       iter = RandomIterFactory.create((RenderedImage)src, src.getBounds());
/*     */     } 
/* 354 */     int minX = src.getMinX();
/* 355 */     int maxX = src.getMaxX() - ((this.extender != null) ? 0 : 1);
/* 357 */     int minY = src.getMinY();
/* 358 */     int maxY = src.getMaxY() - ((this.extender != null) ? 0 : 1);
/* 361 */     int dstWidth = dst.getWidth();
/* 362 */     int dstHeight = dst.getHeight();
/* 363 */     int dstBands = dst.getNumBands();
/* 365 */     int lineStride = dst.getScanlineStride();
/* 366 */     int pixelStride = dst.getPixelStride();
/* 367 */     int[] bandOffsets = dst.getBandOffsets();
/* 368 */     short[][] data = dst.getShortDataArrays();
/* 370 */     float[] warpData = new float[2 * dstWidth];
/* 372 */     int lineOffset = 0;
/* 374 */     short[] backgroundShort = new short[dstBands];
/* 375 */     for (int i = 0; i < dstBands; i++)
/* 376 */       backgroundShort[i] = (short)(int)this.backgroundValues[i]; 
/* 378 */     for (int h = 0; h < dstHeight; h++) {
/* 379 */       int pixelOffset = lineOffset;
/* 380 */       lineOffset += lineStride;
/* 382 */       this.warp.warpRect(dst.getX(), dst.getY() + h, dstWidth, 1, warpData);
/* 384 */       int count = 0;
/* 385 */       for (int w = 0; w < dstWidth; w++) {
/* 386 */         float sx = warpData[count++];
/* 387 */         float sy = warpData[count++];
/* 389 */         int xint = floor(sx);
/* 390 */         int yint = floor(sy);
/* 391 */         float xfrac = sx - xint;
/* 392 */         float yfrac = sy - yint;
/* 394 */         if (xint < minX || xint >= maxX || yint < minY || yint >= maxY) {
/* 397 */           if (this.setBackground)
/* 398 */             for (int b = 0; b < dstBands; b++)
/* 399 */               data[b][pixelOffset + bandOffsets[b]] = backgroundShort[b];  
/*     */         } else {
/* 404 */           for (int b = 0; b < dstBands; b++) {
/* 405 */             int s00 = iter.getSample(xint, yint, b);
/* 406 */             int s01 = iter.getSample(xint + 1, yint, b);
/* 407 */             int s10 = iter.getSample(xint, yint + 1, b);
/* 408 */             int s11 = iter.getSample(xint + 1, yint + 1, b);
/* 410 */             float s0 = (s01 - s00) * xfrac + s00;
/* 411 */             float s1 = (s11 - s10) * xfrac + s10;
/* 412 */             float s = (s1 - s0) * yfrac + s0;
/* 414 */             data[b][pixelOffset + bandOffsets[b]] = (short)(int)s;
/*     */           } 
/*     */         } 
/* 418 */         pixelOffset += pixelStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeRectInt(PlanarImage src, RasterAccessor dst) {
/*     */     RandomIter iter;
/* 425 */     if (this.extender != null) {
/* 426 */       Rectangle bounds = new Rectangle(src.getMinX(), src.getMinY(), src.getWidth() + 1, src.getHeight() + 1);
/* 429 */       iter = RandomIterFactory.create(src.getExtendedData(bounds, this.extender), bounds);
/*     */     } else {
/* 433 */       iter = RandomIterFactory.create((RenderedImage)src, src.getBounds());
/*     */     } 
/* 436 */     int minX = src.getMinX();
/* 437 */     int maxX = src.getMaxX() - ((this.extender != null) ? 0 : 1);
/* 439 */     int minY = src.getMinY();
/* 440 */     int maxY = src.getMaxY() - ((this.extender != null) ? 0 : 1);
/* 443 */     int dstWidth = dst.getWidth();
/* 444 */     int dstHeight = dst.getHeight();
/* 445 */     int dstBands = dst.getNumBands();
/* 447 */     int lineStride = dst.getScanlineStride();
/* 448 */     int pixelStride = dst.getPixelStride();
/* 449 */     int[] bandOffsets = dst.getBandOffsets();
/* 450 */     int[][] data = dst.getIntDataArrays();
/* 452 */     float[] warpData = new float[2 * dstWidth];
/* 454 */     int lineOffset = 0;
/* 456 */     int[] backgroundInt = new int[dstBands];
/* 457 */     for (int i = 0; i < dstBands; i++)
/* 458 */       backgroundInt[i] = (int)this.backgroundValues[i]; 
/* 460 */     for (int h = 0; h < dstHeight; h++) {
/* 461 */       int pixelOffset = lineOffset;
/* 462 */       lineOffset += lineStride;
/* 464 */       this.warp.warpRect(dst.getX(), dst.getY() + h, dstWidth, 1, warpData);
/* 466 */       int count = 0;
/* 467 */       for (int w = 0; w < dstWidth; w++) {
/* 468 */         float sx = warpData[count++];
/* 469 */         float sy = warpData[count++];
/* 471 */         int xint = floor(sx);
/* 472 */         int yint = floor(sy);
/* 473 */         float xfrac = sx - xint;
/* 474 */         float yfrac = sy - yint;
/* 476 */         if (xint < minX || xint >= maxX || yint < minY || yint >= maxY) {
/* 479 */           if (this.setBackground)
/* 480 */             for (int b = 0; b < dstBands; b++)
/* 481 */               data[b][pixelOffset + bandOffsets[b]] = backgroundInt[b];  
/*     */         } else {
/* 486 */           for (int b = 0; b < dstBands; b++) {
/* 487 */             int s00 = iter.getSample(xint, yint, b);
/* 488 */             int s01 = iter.getSample(xint + 1, yint, b);
/* 489 */             int s10 = iter.getSample(xint, yint + 1, b);
/* 490 */             int s11 = iter.getSample(xint + 1, yint + 1, b);
/* 492 */             float s0 = (s01 - s00) * xfrac + s00;
/* 493 */             float s1 = (s11 - s10) * xfrac + s10;
/* 494 */             float s = (s1 - s0) * yfrac + s0;
/* 496 */             data[b][pixelOffset + bandOffsets[b]] = (int)s;
/*     */           } 
/*     */         } 
/* 500 */         pixelOffset += pixelStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeRectFloat(PlanarImage src, RasterAccessor dst) {
/*     */     RandomIter iter;
/* 507 */     if (this.extender != null) {
/* 508 */       Rectangle bounds = new Rectangle(src.getMinX(), src.getMinY(), src.getWidth() + 1, src.getHeight() + 1);
/* 511 */       iter = RandomIterFactory.create(src.getExtendedData(bounds, this.extender), bounds);
/*     */     } else {
/* 515 */       iter = RandomIterFactory.create((RenderedImage)src, src.getBounds());
/*     */     } 
/* 518 */     int minX = src.getMinX();
/* 519 */     int maxX = src.getMaxX() - ((this.extender != null) ? 0 : 1);
/* 521 */     int minY = src.getMinY();
/* 522 */     int maxY = src.getMaxY() - ((this.extender != null) ? 0 : 1);
/* 525 */     int dstWidth = dst.getWidth();
/* 526 */     int dstHeight = dst.getHeight();
/* 527 */     int dstBands = dst.getNumBands();
/* 529 */     int lineStride = dst.getScanlineStride();
/* 530 */     int pixelStride = dst.getPixelStride();
/* 531 */     int[] bandOffsets = dst.getBandOffsets();
/* 532 */     float[][] data = dst.getFloatDataArrays();
/* 534 */     float[] warpData = new float[2 * dstWidth];
/* 536 */     int lineOffset = 0;
/* 538 */     float[] backgroundFloat = new float[dstBands];
/* 539 */     for (int i = 0; i < dstBands; i++)
/* 540 */       backgroundFloat[i] = (float)this.backgroundValues[i]; 
/* 542 */     for (int h = 0; h < dstHeight; h++) {
/* 543 */       int pixelOffset = lineOffset;
/* 544 */       lineOffset += lineStride;
/* 546 */       this.warp.warpRect(dst.getX(), dst.getY() + h, dstWidth, 1, warpData);
/* 548 */       int count = 0;
/* 549 */       for (int w = 0; w < dstWidth; w++) {
/* 550 */         float sx = warpData[count++];
/* 551 */         float sy = warpData[count++];
/* 553 */         int xint = floor(sx);
/* 554 */         int yint = floor(sy);
/* 555 */         float xfrac = sx - xint;
/* 556 */         float yfrac = sy - yint;
/* 558 */         if (xint < minX || xint >= maxX || yint < minY || yint >= maxY) {
/* 561 */           if (this.setBackground)
/* 562 */             for (int b = 0; b < dstBands; b++)
/* 563 */               data[b][pixelOffset + bandOffsets[b]] = backgroundFloat[b];  
/*     */         } else {
/* 568 */           for (int b = 0; b < dstBands; b++) {
/* 569 */             float s00 = iter.getSampleFloat(xint, yint, b);
/* 570 */             float s01 = iter.getSampleFloat(xint + 1, yint, b);
/* 571 */             float s10 = iter.getSampleFloat(xint, yint + 1, b);
/* 572 */             float s11 = iter.getSampleFloat(xint + 1, yint + 1, b);
/* 574 */             float s0 = (s01 - s00) * xfrac + s00;
/* 575 */             float s1 = (s11 - s10) * xfrac + s10;
/* 576 */             float s = (s1 - s0) * yfrac + s0;
/* 578 */             data[b][pixelOffset + bandOffsets[b]] = s;
/*     */           } 
/*     */         } 
/* 582 */         pixelOffset += pixelStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeRectDouble(PlanarImage src, RasterAccessor dst) {
/*     */     RandomIter iter;
/* 589 */     if (this.extender != null) {
/* 590 */       Rectangle bounds = new Rectangle(src.getMinX(), src.getMinY(), src.getWidth() + 1, src.getHeight() + 1);
/* 593 */       iter = RandomIterFactory.create(src.getExtendedData(bounds, this.extender), bounds);
/*     */     } else {
/* 597 */       iter = RandomIterFactory.create((RenderedImage)src, src.getBounds());
/*     */     } 
/* 600 */     int minX = src.getMinX();
/* 601 */     int maxX = src.getMaxX() - ((this.extender != null) ? 0 : 1);
/* 603 */     int minY = src.getMinY();
/* 604 */     int maxY = src.getMaxY() - ((this.extender != null) ? 0 : 1);
/* 607 */     int dstWidth = dst.getWidth();
/* 608 */     int dstHeight = dst.getHeight();
/* 609 */     int dstBands = dst.getNumBands();
/* 611 */     int lineStride = dst.getScanlineStride();
/* 612 */     int pixelStride = dst.getPixelStride();
/* 613 */     int[] bandOffsets = dst.getBandOffsets();
/* 614 */     double[][] data = dst.getDoubleDataArrays();
/* 616 */     float[] warpData = new float[2 * dstWidth];
/* 618 */     int lineOffset = 0;
/* 620 */     for (int h = 0; h < dstHeight; h++) {
/* 621 */       int pixelOffset = lineOffset;
/* 622 */       lineOffset += lineStride;
/* 624 */       this.warp.warpRect(dst.getX(), dst.getY() + h, dstWidth, 1, warpData);
/* 626 */       int count = 0;
/* 627 */       for (int w = 0; w < dstWidth; w++) {
/* 628 */         float sx = warpData[count++];
/* 629 */         float sy = warpData[count++];
/* 631 */         int xint = floor(sx);
/* 632 */         int yint = floor(sy);
/* 633 */         float xfrac = sx - xint;
/* 634 */         float yfrac = sy - yint;
/* 636 */         if (xint < minX || xint >= maxX || yint < minY || yint >= maxY) {
/* 639 */           if (this.setBackground)
/* 640 */             for (int b = 0; b < dstBands; b++)
/* 641 */               data[b][pixelOffset + bandOffsets[b]] = this.backgroundValues[b];  
/*     */         } else {
/* 646 */           for (int b = 0; b < dstBands; b++) {
/* 647 */             double s00 = iter.getSampleDouble(xint, yint, b);
/* 648 */             double s01 = iter.getSampleDouble(xint + 1, yint, b);
/* 649 */             double s10 = iter.getSampleDouble(xint, yint + 1, b);
/* 650 */             double s11 = iter.getSampleDouble(xint + 1, yint + 1, b);
/* 652 */             double s0 = (s01 - s00) * xfrac + s00;
/* 653 */             double s1 = (s11 - s10) * xfrac + s10;
/* 654 */             double s = (s1 - s0) * yfrac + s0;
/* 656 */             data[b][pixelOffset + bandOffsets[b]] = s;
/*     */           } 
/*     */         } 
/* 660 */         pixelOffset += pixelStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private static final int floor(float f) {
/* 667 */     return (f >= 0.0F) ? (int)f : ((int)f - 1);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\WarpBilinearOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */