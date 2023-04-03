/*     */ package com.sun.media.jai.opimage;
/*     */ 
/*     */ import com.sun.media.jai.util.ImageUtil;
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
/*     */ final class WarpGeneralOpImage extends WarpOpImage {
/*  47 */   private byte[][] ctable = (byte[][])null;
/*     */   
/*     */   public WarpGeneralOpImage(RenderedImage source, BorderExtender extender, Map config, ImageLayout layout, Warp warp, Interpolation interp, double[] backgroundValues) {
/*  65 */     super(source, layout, config, false, extender, interp, warp, backgroundValues);
/*  79 */     ColorModel srcColorModel = source.getColorModel();
/*  80 */     if (srcColorModel instanceof IndexColorModel) {
/*  81 */       IndexColorModel icm = (IndexColorModel)srcColorModel;
/*  82 */       this.ctable = new byte[3][icm.getMapSize()];
/*  83 */       icm.getReds(this.ctable[0]);
/*  84 */       icm.getGreens(this.ctable[1]);
/*  85 */       icm.getBlues(this.ctable[2]);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void computeRect(PlanarImage[] sources, WritableRaster dest, Rectangle destRect) {
/*  94 */     RasterFormatTag[] formatTags = getFormatTags();
/*  96 */     RasterAccessor d = new RasterAccessor(dest, destRect, formatTags[1], getColorModel());
/*  99 */     switch (d.getDataType()) {
/*     */       case 0:
/* 101 */         computeRectByte(sources[0], d);
/*     */         break;
/*     */       case 1:
/* 104 */         computeRectUShort(sources[0], d);
/*     */         break;
/*     */       case 2:
/* 107 */         computeRectShort(sources[0], d);
/*     */         break;
/*     */       case 3:
/* 110 */         computeRectInt(sources[0], d);
/*     */         break;
/*     */       case 4:
/* 113 */         computeRectFloat(sources[0], d);
/*     */         break;
/*     */       case 5:
/* 116 */         computeRectDouble(sources[0], d);
/*     */         break;
/*     */     } 
/* 120 */     if (d.isDataCopy()) {
/* 121 */       d.clampDataArrays();
/* 122 */       d.copyDataToRaster();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeRectByte(PlanarImage src, RasterAccessor dst) {
/*     */     int lpad, rpad, tpad, bpad, minX, maxX, minY, maxY;
/*     */     RandomIter iter;
/* 128 */     if (this.interp != null) {
/* 129 */       lpad = this.interp.getLeftPadding();
/* 130 */       rpad = this.interp.getRightPadding();
/* 131 */       tpad = this.interp.getTopPadding();
/* 132 */       bpad = this.interp.getBottomPadding();
/*     */     } else {
/* 134 */       lpad = rpad = tpad = bpad = 0;
/*     */     } 
/* 139 */     if (this.extender != null) {
/* 140 */       minX = src.getMinX();
/* 141 */       maxX = src.getMaxX();
/* 142 */       minY = src.getMinY();
/* 143 */       maxY = src.getMaxY();
/* 144 */       Rectangle bounds = new Rectangle(src.getMinX() - lpad, src.getMinY() - tpad, src.getWidth() + lpad + rpad, src.getHeight() + tpad + bpad);
/* 148 */       iter = RandomIterFactory.create(src.getExtendedData(bounds, this.extender), bounds);
/*     */     } else {
/* 152 */       minX = src.getMinX() + lpad;
/* 153 */       maxX = src.getMaxX() - rpad;
/* 154 */       minY = src.getMinY() + tpad;
/* 155 */       maxY = src.getMaxY() - bpad;
/* 156 */       iter = RandomIterFactory.create((RenderedImage)src, src.getBounds());
/*     */     } 
/* 159 */     int kwidth = this.interp.getWidth();
/* 160 */     int kheight = this.interp.getHeight();
/* 162 */     int dstWidth = dst.getWidth();
/* 163 */     int dstHeight = dst.getHeight();
/* 164 */     int dstBands = dst.getNumBands();
/* 166 */     int lineStride = dst.getScanlineStride();
/* 167 */     int pixelStride = dst.getPixelStride();
/* 168 */     int[] bandOffsets = dst.getBandOffsets();
/* 169 */     byte[][] data = dst.getByteDataArrays();
/* 171 */     int precH = 1 << this.interp.getSubsampleBitsH();
/* 172 */     int precV = 1 << this.interp.getSubsampleBitsV();
/* 174 */     float[] warpData = new float[2 * dstWidth];
/* 176 */     int[][] samples = new int[kheight][kwidth];
/* 178 */     int lineOffset = 0;
/* 180 */     byte[] backgroundByte = new byte[dstBands];
/* 181 */     for (int i = 0; i < dstBands; i++)
/* 182 */       backgroundByte[i] = (byte)(int)this.backgroundValues[i]; 
/* 184 */     if (this.ctable == null) {
/* 185 */       for (int h = 0; h < dstHeight; h++) {
/* 186 */         int pixelOffset = lineOffset;
/* 187 */         lineOffset += lineStride;
/* 189 */         this.warp.warpRect(dst.getX(), dst.getY() + h, dstWidth, 1, warpData);
/* 191 */         int count = 0;
/* 192 */         for (int w = 0; w < dstWidth; w++) {
/* 193 */           float sx = warpData[count++];
/* 194 */           float sy = warpData[count++];
/* 196 */           int xint = floor(sx);
/* 197 */           int yint = floor(sy);
/* 198 */           int xfrac = (int)((sx - xint) * precH);
/* 199 */           int yfrac = (int)((sy - yint) * precV);
/* 201 */           if (xint < minX || xint >= maxX || yint < minY || yint >= maxY) {
/* 204 */             if (this.setBackground)
/* 205 */               for (int b = 0; b < dstBands; b++)
/* 206 */                 data[b][pixelOffset + bandOffsets[b]] = backgroundByte[b];  
/*     */           } else {
/* 211 */             xint -= lpad;
/* 212 */             yint -= tpad;
/* 214 */             for (int b = 0; b < dstBands; b++) {
/* 215 */               for (int j = 0; j < kheight; j++) {
/* 216 */                 for (int k = 0; k < kwidth; k++)
/* 217 */                   samples[j][k] = iter.getSample(xint + k, yint + j, b) & 0xFF; 
/*     */               } 
/* 222 */               data[b][pixelOffset + bandOffsets[b]] = ImageUtil.clampByte(this.interp.interpolate(samples, xfrac, yfrac));
/*     */             } 
/*     */           } 
/* 228 */           pixelOffset += pixelStride;
/*     */         } 
/*     */       } 
/*     */     } else {
/* 232 */       for (int h = 0; h < dstHeight; h++) {
/* 233 */         int pixelOffset = lineOffset;
/* 234 */         lineOffset += lineStride;
/* 236 */         this.warp.warpRect(dst.getX(), dst.getY() + h, dstWidth, 1, warpData);
/* 238 */         int count = 0;
/* 239 */         for (int w = 0; w < dstWidth; w++) {
/* 240 */           float sx = warpData[count++];
/* 241 */           float sy = warpData[count++];
/* 243 */           int xint = floor(sx);
/* 244 */           int yint = floor(sy);
/* 245 */           int xfrac = (int)((sx - xint) * precH);
/* 246 */           int yfrac = (int)((sy - yint) * precV);
/* 248 */           if (xint < minX || xint >= maxX || yint < minY || yint >= maxY) {
/* 251 */             if (this.setBackground)
/* 252 */               for (int b = 0; b < dstBands; b++)
/* 253 */                 data[b][pixelOffset + bandOffsets[b]] = backgroundByte[b];  
/*     */           } else {
/* 258 */             xint -= lpad;
/* 259 */             yint -= tpad;
/* 261 */             for (int b = 0; b < dstBands; b++) {
/* 262 */               byte[] t = this.ctable[b];
/* 264 */               for (int j = 0; j < kheight; j++) {
/* 265 */                 for (int k = 0; k < kwidth; k++)
/* 266 */                   samples[j][k] = t[iter.getSample(xint + k, yint + j, 0) & 0xFF] & 0xFF; 
/*     */               } 
/* 271 */               data[b][pixelOffset + bandOffsets[b]] = ImageUtil.clampByte(this.interp.interpolate(samples, xfrac, yfrac));
/*     */             } 
/*     */           } 
/* 277 */           pixelOffset += pixelStride;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeRectUShort(PlanarImage src, RasterAccessor dst) {
/*     */     int lpad, rpad, tpad, bpad, minX, maxX, minY, maxY;
/*     */     RandomIter iter;
/* 285 */     if (this.interp != null) {
/* 286 */       lpad = this.interp.getLeftPadding();
/* 287 */       rpad = this.interp.getRightPadding();
/* 288 */       tpad = this.interp.getTopPadding();
/* 289 */       bpad = this.interp.getBottomPadding();
/*     */     } else {
/* 291 */       lpad = rpad = tpad = bpad = 0;
/*     */     } 
/* 296 */     if (this.extender != null) {
/* 297 */       minX = src.getMinX();
/* 298 */       maxX = src.getMaxX();
/* 299 */       minY = src.getMinY();
/* 300 */       maxY = src.getMaxY();
/* 301 */       Rectangle bounds = new Rectangle(src.getMinX() - lpad, src.getMinY() - tpad, src.getWidth() + lpad + rpad, src.getHeight() + tpad + bpad);
/* 305 */       iter = RandomIterFactory.create(src.getExtendedData(bounds, this.extender), bounds);
/*     */     } else {
/* 309 */       minX = src.getMinX() + lpad;
/* 310 */       maxX = src.getMaxX() - rpad;
/* 311 */       minY = src.getMinY() + tpad;
/* 312 */       maxY = src.getMaxY() - bpad;
/* 313 */       iter = RandomIterFactory.create((RenderedImage)src, src.getBounds());
/*     */     } 
/* 316 */     int kwidth = this.interp.getWidth();
/* 317 */     int kheight = this.interp.getHeight();
/* 319 */     int dstWidth = dst.getWidth();
/* 320 */     int dstHeight = dst.getHeight();
/* 321 */     int dstBands = dst.getNumBands();
/* 323 */     int lineStride = dst.getScanlineStride();
/* 324 */     int pixelStride = dst.getPixelStride();
/* 325 */     int[] bandOffsets = dst.getBandOffsets();
/* 326 */     short[][] data = dst.getShortDataArrays();
/* 328 */     int precH = 1 << this.interp.getSubsampleBitsH();
/* 329 */     int precV = 1 << this.interp.getSubsampleBitsV();
/* 331 */     float[] warpData = new float[2 * dstWidth];
/* 333 */     int[][] samples = new int[kheight][kwidth];
/* 335 */     int lineOffset = 0;
/* 337 */     short[] backgroundUShort = new short[dstBands];
/* 338 */     for (int i = 0; i < dstBands; i++)
/* 339 */       backgroundUShort[i] = (short)(int)this.backgroundValues[i]; 
/* 341 */     for (int h = 0; h < dstHeight; h++) {
/* 342 */       int pixelOffset = lineOffset;
/* 343 */       lineOffset += lineStride;
/* 345 */       this.warp.warpRect(dst.getX(), dst.getY() + h, dstWidth, 1, warpData);
/* 347 */       int count = 0;
/* 348 */       for (int w = 0; w < dstWidth; w++) {
/* 349 */         float sx = warpData[count++];
/* 350 */         float sy = warpData[count++];
/* 352 */         int xint = floor(sx);
/* 353 */         int yint = floor(sy);
/* 354 */         int xfrac = (int)((sx - xint) * precH);
/* 355 */         int yfrac = (int)((sy - yint) * precV);
/* 357 */         if (xint < minX || xint >= maxX || yint < minY || yint >= maxY) {
/* 360 */           if (this.setBackground)
/* 361 */             for (int b = 0; b < dstBands; b++)
/* 362 */               data[b][pixelOffset + bandOffsets[b]] = backgroundUShort[b];  
/*     */         } else {
/* 367 */           xint -= lpad;
/* 368 */           yint -= tpad;
/* 370 */           for (int b = 0; b < dstBands; b++) {
/* 371 */             for (int j = 0; j < kheight; j++) {
/* 372 */               for (int k = 0; k < kwidth; k++)
/* 373 */                 samples[j][k] = iter.getSample(xint + k, yint + j, b) & 0xFFFF; 
/*     */             } 
/* 378 */             data[b][pixelOffset + bandOffsets[b]] = ImageUtil.clampUShort(this.interp.interpolate(samples, xfrac, yfrac));
/*     */           } 
/*     */         } 
/* 384 */         pixelOffset += pixelStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeRectShort(PlanarImage src, RasterAccessor dst) {
/*     */     int lpad, rpad, tpad, bpad, minX, maxX, minY, maxY;
/*     */     RandomIter iter;
/* 391 */     if (this.interp != null) {
/* 392 */       lpad = this.interp.getLeftPadding();
/* 393 */       rpad = this.interp.getRightPadding();
/* 394 */       tpad = this.interp.getTopPadding();
/* 395 */       bpad = this.interp.getBottomPadding();
/*     */     } else {
/* 397 */       lpad = rpad = tpad = bpad = 0;
/*     */     } 
/* 402 */     if (this.extender != null) {
/* 403 */       minX = src.getMinX();
/* 404 */       maxX = src.getMaxX();
/* 405 */       minY = src.getMinY();
/* 406 */       maxY = src.getMaxY();
/* 407 */       Rectangle bounds = new Rectangle(src.getMinX() - lpad, src.getMinY() - tpad, src.getWidth() + lpad + rpad, src.getHeight() + tpad + bpad);
/* 411 */       iter = RandomIterFactory.create(src.getExtendedData(bounds, this.extender), bounds);
/*     */     } else {
/* 415 */       minX = src.getMinX() + lpad;
/* 416 */       maxX = src.getMaxX() - rpad;
/* 417 */       minY = src.getMinY() + tpad;
/* 418 */       maxY = src.getMaxY() - bpad;
/* 419 */       iter = RandomIterFactory.create((RenderedImage)src, src.getBounds());
/*     */     } 
/* 422 */     int kwidth = this.interp.getWidth();
/* 423 */     int kheight = this.interp.getHeight();
/* 425 */     int dstWidth = dst.getWidth();
/* 426 */     int dstHeight = dst.getHeight();
/* 427 */     int dstBands = dst.getNumBands();
/* 429 */     int lineStride = dst.getScanlineStride();
/* 430 */     int pixelStride = dst.getPixelStride();
/* 431 */     int[] bandOffsets = dst.getBandOffsets();
/* 432 */     short[][] data = dst.getShortDataArrays();
/* 434 */     int precH = 1 << this.interp.getSubsampleBitsH();
/* 435 */     int precV = 1 << this.interp.getSubsampleBitsV();
/* 437 */     float[] warpData = new float[2 * dstWidth];
/* 439 */     int[][] samples = new int[kheight][kwidth];
/* 441 */     int lineOffset = 0;
/* 443 */     short[] backgroundShort = new short[dstBands];
/* 444 */     for (int i = 0; i < dstBands; i++)
/* 445 */       backgroundShort[i] = (short)(int)this.backgroundValues[i]; 
/* 447 */     for (int h = 0; h < dstHeight; h++) {
/* 448 */       int pixelOffset = lineOffset;
/* 449 */       lineOffset += lineStride;
/* 451 */       this.warp.warpRect(dst.getX(), dst.getY() + h, dstWidth, 1, warpData);
/* 453 */       int count = 0;
/* 454 */       for (int w = 0; w < dstWidth; w++) {
/* 455 */         float sx = warpData[count++];
/* 456 */         float sy = warpData[count++];
/* 458 */         int xint = floor(sx);
/* 459 */         int yint = floor(sy);
/* 460 */         int xfrac = (int)((sx - xint) * precH);
/* 461 */         int yfrac = (int)((sy - yint) * precV);
/* 463 */         if (xint < minX || xint >= maxX || yint < minY || yint >= maxY) {
/* 466 */           if (this.setBackground)
/* 467 */             for (int b = 0; b < dstBands; b++)
/* 468 */               data[b][pixelOffset + bandOffsets[b]] = backgroundShort[b];  
/*     */         } else {
/* 473 */           xint -= lpad;
/* 474 */           yint -= tpad;
/* 476 */           for (int b = 0; b < dstBands; b++) {
/* 477 */             for (int j = 0; j < kheight; j++) {
/* 478 */               for (int k = 0; k < kwidth; k++)
/* 479 */                 samples[j][k] = iter.getSample(xint + k, yint + j, b); 
/*     */             } 
/* 484 */             data[b][pixelOffset + bandOffsets[b]] = ImageUtil.clampShort(this.interp.interpolate(samples, xfrac, yfrac));
/*     */           } 
/*     */         } 
/* 490 */         pixelOffset += pixelStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeRectInt(PlanarImage src, RasterAccessor dst) {
/*     */     int lpad, rpad, tpad, bpad, minX, maxX, minY, maxY;
/*     */     RandomIter iter;
/* 497 */     if (this.interp != null) {
/* 498 */       lpad = this.interp.getLeftPadding();
/* 499 */       rpad = this.interp.getRightPadding();
/* 500 */       tpad = this.interp.getTopPadding();
/* 501 */       bpad = this.interp.getBottomPadding();
/*     */     } else {
/* 503 */       lpad = rpad = tpad = bpad = 0;
/*     */     } 
/* 508 */     if (this.extender != null) {
/* 509 */       minX = src.getMinX();
/* 510 */       maxX = src.getMaxX();
/* 511 */       minY = src.getMinY();
/* 512 */       maxY = src.getMaxY();
/* 513 */       Rectangle bounds = new Rectangle(src.getMinX() - lpad, src.getMinY() - tpad, src.getWidth() + lpad + rpad, src.getHeight() + tpad + bpad);
/* 517 */       iter = RandomIterFactory.create(src.getExtendedData(bounds, this.extender), bounds);
/*     */     } else {
/* 521 */       minX = src.getMinX() + lpad;
/* 522 */       maxX = src.getMaxX() - rpad;
/* 523 */       minY = src.getMinY() + tpad;
/* 524 */       maxY = src.getMaxY() - bpad;
/* 525 */       iter = RandomIterFactory.create((RenderedImage)src, src.getBounds());
/*     */     } 
/* 528 */     int kwidth = this.interp.getWidth();
/* 529 */     int kheight = this.interp.getHeight();
/* 531 */     int dstWidth = dst.getWidth();
/* 532 */     int dstHeight = dst.getHeight();
/* 533 */     int dstBands = dst.getNumBands();
/* 535 */     int lineStride = dst.getScanlineStride();
/* 536 */     int pixelStride = dst.getPixelStride();
/* 537 */     int[] bandOffsets = dst.getBandOffsets();
/* 538 */     int[][] data = dst.getIntDataArrays();
/* 540 */     int precH = 1 << this.interp.getSubsampleBitsH();
/* 541 */     int precV = 1 << this.interp.getSubsampleBitsV();
/* 543 */     float[] warpData = new float[2 * dstWidth];
/* 545 */     int[][] samples = new int[kheight][kwidth];
/* 547 */     int lineOffset = 0;
/* 549 */     int[] backgroundInt = new int[dstBands];
/* 550 */     for (int i = 0; i < dstBands; i++)
/* 551 */       backgroundInt[i] = (int)this.backgroundValues[i]; 
/* 553 */     for (int h = 0; h < dstHeight; h++) {
/* 554 */       int pixelOffset = lineOffset;
/* 555 */       lineOffset += lineStride;
/* 557 */       this.warp.warpRect(dst.getX(), dst.getY() + h, dstWidth, 1, warpData);
/* 559 */       int count = 0;
/* 560 */       for (int w = 0; w < dstWidth; w++) {
/* 561 */         float sx = warpData[count++];
/* 562 */         float sy = warpData[count++];
/* 564 */         int xint = floor(sx);
/* 565 */         int yint = floor(sy);
/* 566 */         int xfrac = (int)((sx - xint) * precH);
/* 567 */         int yfrac = (int)((sy - yint) * precV);
/* 569 */         if (xint < minX || xint >= maxX || yint < minY || yint >= maxY) {
/* 572 */           if (this.setBackground)
/* 573 */             for (int b = 0; b < dstBands; b++)
/* 574 */               data[b][pixelOffset + bandOffsets[b]] = backgroundInt[b];  
/*     */         } else {
/* 579 */           xint -= lpad;
/* 580 */           yint -= tpad;
/* 582 */           for (int b = 0; b < dstBands; b++) {
/* 583 */             for (int j = 0; j < kheight; j++) {
/* 584 */               for (int k = 0; k < kwidth; k++)
/* 585 */                 samples[j][k] = iter.getSample(xint + k, yint + j, b); 
/*     */             } 
/* 590 */             data[b][pixelOffset + bandOffsets[b]] = this.interp.interpolate(samples, xfrac, yfrac);
/*     */           } 
/*     */         } 
/* 595 */         pixelOffset += pixelStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeRectFloat(PlanarImage src, RasterAccessor dst) {
/*     */     int lpad, rpad, tpad, bpad, minX, maxX, minY, maxY;
/*     */     RandomIter iter;
/* 602 */     if (this.interp != null) {
/* 603 */       lpad = this.interp.getLeftPadding();
/* 604 */       rpad = this.interp.getRightPadding();
/* 605 */       tpad = this.interp.getTopPadding();
/* 606 */       bpad = this.interp.getBottomPadding();
/*     */     } else {
/* 608 */       lpad = rpad = tpad = bpad = 0;
/*     */     } 
/* 613 */     if (this.extender != null) {
/* 614 */       minX = src.getMinX();
/* 615 */       maxX = src.getMaxX();
/* 616 */       minY = src.getMinY();
/* 617 */       maxY = src.getMaxY();
/* 618 */       Rectangle bounds = new Rectangle(src.getMinX() - lpad, src.getMinY() - tpad, src.getWidth() + lpad + rpad, src.getHeight() + tpad + bpad);
/* 622 */       iter = RandomIterFactory.create(src.getExtendedData(bounds, this.extender), bounds);
/*     */     } else {
/* 626 */       minX = src.getMinX() + lpad;
/* 627 */       maxX = src.getMaxX() - rpad;
/* 628 */       minY = src.getMinY() + tpad;
/* 629 */       maxY = src.getMaxY() - bpad;
/* 630 */       iter = RandomIterFactory.create((RenderedImage)src, src.getBounds());
/*     */     } 
/* 633 */     int kwidth = this.interp.getWidth();
/* 634 */     int kheight = this.interp.getHeight();
/* 636 */     int dstWidth = dst.getWidth();
/* 637 */     int dstHeight = dst.getHeight();
/* 638 */     int dstBands = dst.getNumBands();
/* 640 */     int lineStride = dst.getScanlineStride();
/* 641 */     int pixelStride = dst.getPixelStride();
/* 642 */     int[] bandOffsets = dst.getBandOffsets();
/* 643 */     float[][] data = dst.getFloatDataArrays();
/* 645 */     float[] warpData = new float[2 * dstWidth];
/* 647 */     float[][] samples = new float[kheight][kwidth];
/* 649 */     int lineOffset = 0;
/* 651 */     float[] backgroundFloat = new float[dstBands];
/* 652 */     for (int i = 0; i < dstBands; i++)
/* 653 */       backgroundFloat[i] = (float)this.backgroundValues[i]; 
/* 655 */     for (int h = 0; h < dstHeight; h++) {
/* 656 */       int pixelOffset = lineOffset;
/* 657 */       lineOffset += lineStride;
/* 659 */       this.warp.warpRect(dst.getX(), dst.getY() + h, dstWidth, 1, warpData);
/* 661 */       int count = 0;
/* 662 */       for (int w = 0; w < dstWidth; w++) {
/* 663 */         float sx = warpData[count++];
/* 664 */         float sy = warpData[count++];
/* 666 */         int xint = floor(sx);
/* 667 */         int yint = floor(sy);
/* 668 */         float xfrac = sx - xint;
/* 669 */         float yfrac = sy - yint;
/* 671 */         if (xint < minX || xint >= maxX || yint < minY || yint >= maxY) {
/* 674 */           if (this.setBackground)
/* 675 */             for (int b = 0; b < dstBands; b++)
/* 676 */               data[b][pixelOffset + bandOffsets[b]] = backgroundFloat[b];  
/*     */         } else {
/* 681 */           xint -= lpad;
/* 682 */           yint -= tpad;
/* 684 */           for (int b = 0; b < dstBands; b++) {
/* 685 */             for (int j = 0; j < kheight; j++) {
/* 686 */               for (int k = 0; k < kwidth; k++)
/* 687 */                 samples[j][k] = iter.getSampleFloat(xint + k, yint + j, b); 
/*     */             } 
/* 692 */             data[b][pixelOffset + bandOffsets[b]] = this.interp.interpolate(samples, xfrac, yfrac);
/*     */           } 
/*     */         } 
/* 697 */         pixelOffset += pixelStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeRectDouble(PlanarImage src, RasterAccessor dst) {
/*     */     int lpad, rpad, tpad, bpad, minX, maxX, minY, maxY;
/*     */     RandomIter iter;
/* 704 */     if (this.interp != null) {
/* 705 */       lpad = this.interp.getLeftPadding();
/* 706 */       rpad = this.interp.getRightPadding();
/* 707 */       tpad = this.interp.getTopPadding();
/* 708 */       bpad = this.interp.getBottomPadding();
/*     */     } else {
/* 710 */       lpad = rpad = tpad = bpad = 0;
/*     */     } 
/* 715 */     if (this.extender != null) {
/* 716 */       minX = src.getMinX();
/* 717 */       maxX = src.getMaxX();
/* 718 */       minY = src.getMinY();
/* 719 */       maxY = src.getMaxY();
/* 720 */       Rectangle bounds = new Rectangle(src.getMinX() - lpad, src.getMinY() - tpad, src.getWidth() + lpad + rpad, src.getHeight() + tpad + bpad);
/* 724 */       iter = RandomIterFactory.create(src.getExtendedData(bounds, this.extender), bounds);
/*     */     } else {
/* 728 */       minX = src.getMinX() + lpad;
/* 729 */       maxX = src.getMaxX() - rpad;
/* 730 */       minY = src.getMinY() + tpad;
/* 731 */       maxY = src.getMaxY() - bpad;
/* 732 */       iter = RandomIterFactory.create((RenderedImage)src, src.getBounds());
/*     */     } 
/* 735 */     int kwidth = this.interp.getWidth();
/* 736 */     int kheight = this.interp.getHeight();
/* 738 */     int dstWidth = dst.getWidth();
/* 739 */     int dstHeight = dst.getHeight();
/* 740 */     int dstBands = dst.getNumBands();
/* 742 */     int lineStride = dst.getScanlineStride();
/* 743 */     int pixelStride = dst.getPixelStride();
/* 744 */     int[] bandOffsets = dst.getBandOffsets();
/* 745 */     double[][] data = dst.getDoubleDataArrays();
/* 747 */     float[] warpData = new float[2 * dstWidth];
/* 749 */     double[][] samples = new double[kheight][kwidth];
/* 751 */     int lineOffset = 0;
/* 753 */     for (int h = 0; h < dstHeight; h++) {
/* 754 */       int pixelOffset = lineOffset;
/* 755 */       lineOffset += lineStride;
/* 757 */       this.warp.warpRect(dst.getX(), dst.getY() + h, dstWidth, 1, warpData);
/* 759 */       int count = 0;
/* 760 */       for (int w = 0; w < dstWidth; w++) {
/* 761 */         float sx = warpData[count++];
/* 762 */         float sy = warpData[count++];
/* 764 */         int xint = floor(sx);
/* 765 */         int yint = floor(sy);
/* 766 */         float xfrac = sx - xint;
/* 767 */         float yfrac = sy - yint;
/* 769 */         if (xint < minX || xint >= maxX || yint < minY || yint >= maxY) {
/* 772 */           if (this.setBackground)
/* 773 */             for (int b = 0; b < dstBands; b++)
/* 774 */               data[b][pixelOffset + bandOffsets[b]] = this.backgroundValues[b];  
/*     */         } else {
/* 779 */           xint -= lpad;
/* 780 */           yint -= tpad;
/* 782 */           for (int b = 0; b < dstBands; b++) {
/* 783 */             for (int j = 0; j < kheight; j++) {
/* 784 */               for (int i = 0; i < kwidth; i++)
/* 785 */                 samples[j][i] = iter.getSampleDouble(xint + i, yint + j, b); 
/*     */             } 
/* 790 */             data[b][pixelOffset + bandOffsets[b]] = this.interp.interpolate(samples, xfrac, yfrac);
/*     */           } 
/*     */         } 
/* 795 */         pixelOffset += pixelStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private static final int floor(float f) {
/* 802 */     return (f >= 0.0F) ? (int)f : ((int)f - 1);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\WarpGeneralOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */