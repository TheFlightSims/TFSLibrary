/*     */ package com.sun.media.jai.opimage;
/*     */ 
/*     */ import com.sun.media.jai.util.Rational;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.image.ColorModel;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.util.Map;
/*     */ import javax.media.jai.BorderExtender;
/*     */ import javax.media.jai.ImageLayout;
/*     */ import javax.media.jai.Interpolation;
/*     */ import javax.media.jai.RasterAccessor;
/*     */ import javax.media.jai.RasterFormatTag;
/*     */ import javax.media.jai.ScaleOpImage;
/*     */ 
/*     */ final class ScaleNearestOpImage extends ScaleOpImage {
/*     */   long invScaleXInt;
/*     */   
/*     */   long invScaleXFrac;
/*     */   
/*     */   long invScaleYInt;
/*     */   
/*     */   long invScaleYFrac;
/*     */   
/*     */   public ScaleNearestOpImage(RenderedImage source, BorderExtender extender, Map config, ImageLayout layout, float xScale, float yScale, float xTrans, float yTrans, Interpolation interp) {
/*  64 */     super(source, layout, config, true, extender, interp, xScale, yScale, xTrans, yTrans);
/*  79 */     ColorModel srcColorModel = source.getColorModel();
/*  80 */     if (srcColorModel instanceof java.awt.image.IndexColorModel) {
/*  81 */       this.sampleModel = source.getSampleModel().createCompatibleSampleModel(this.tileWidth, this.tileHeight);
/*  83 */       this.colorModel = srcColorModel;
/*     */     } 
/*  86 */     if (this.invScaleXRational.num > this.invScaleXRational.denom) {
/*  87 */       this.invScaleXInt = this.invScaleXRational.num / this.invScaleXRational.denom;
/*  88 */       this.invScaleXFrac = this.invScaleXRational.num % this.invScaleXRational.denom;
/*     */     } else {
/*  90 */       this.invScaleXInt = 0L;
/*  91 */       this.invScaleXFrac = this.invScaleXRational.num;
/*     */     } 
/*  94 */     if (this.invScaleYRational.num > this.invScaleYRational.denom) {
/*  95 */       this.invScaleYInt = this.invScaleYRational.num / this.invScaleYRational.denom;
/*  96 */       this.invScaleYFrac = this.invScaleYRational.num % this.invScaleYRational.denom;
/*     */     } else {
/*  98 */       this.invScaleYInt = 0L;
/*  99 */       this.invScaleYFrac = this.invScaleYRational.num;
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void computeRect(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/* 116 */     RasterFormatTag[] formatTags = getFormatTags();
/* 118 */     Raster source = sources[0];
/* 121 */     Rectangle srcRect = source.getBounds();
/* 123 */     int srcRectX = srcRect.x;
/* 124 */     int srcRectY = srcRect.y;
/* 126 */     RasterAccessor srcAccessor = new RasterAccessor(source, srcRect, formatTags[0], getSource(0).getColorModel());
/* 130 */     RasterAccessor dstAccessor = new RasterAccessor(dest, destRect, formatTags[1], getColorModel());
/* 133 */     int srcScanlineStride = srcAccessor.getScanlineStride();
/* 134 */     int srcPixelStride = srcAccessor.getPixelStride();
/* 137 */     int dx = destRect.x;
/* 138 */     int dy = destRect.y;
/* 139 */     int dwidth = destRect.width;
/* 140 */     int dheight = destRect.height;
/* 143 */     int[] xvalues = new int[dwidth];
/* 145 */     long sxNum = dx, sxDenom = 1L;
/* 148 */     sxNum = sxNum * this.transXRationalDenom - this.transXRationalNum * sxDenom;
/* 149 */     sxDenom *= this.transXRationalDenom;
/* 152 */     sxNum = 2L * sxNum + sxDenom;
/* 153 */     sxDenom *= 2L;
/* 156 */     sxNum *= this.invScaleXRationalNum;
/* 157 */     sxDenom *= this.invScaleXRationalDenom;
/* 161 */     int srcXInt = Rational.floor(sxNum, sxDenom);
/* 162 */     long srcXFrac = sxNum % sxDenom;
/* 163 */     if (srcXInt < 0)
/* 164 */       srcXFrac = sxDenom + srcXFrac; 
/* 169 */     long commonXDenom = sxDenom * this.invScaleXRationalDenom;
/* 170 */     srcXFrac *= this.invScaleXRationalDenom;
/* 171 */     long newInvScaleXFrac = this.invScaleXFrac * sxDenom;
/* 173 */     for (int i = 0; i < dwidth; i++) {
/* 176 */       xvalues[i] = (srcXInt - srcRectX) * srcPixelStride;
/* 182 */       srcXInt = (int)(srcXInt + this.invScaleXInt);
/* 186 */       srcXFrac += newInvScaleXFrac;
/* 191 */       if (srcXFrac >= commonXDenom) {
/* 192 */         srcXInt++;
/* 193 */         srcXFrac -= commonXDenom;
/*     */       } 
/*     */     } 
/* 198 */     int[] yvalues = new int[dheight];
/* 200 */     long syNum = dy, syDenom = 1L;
/* 203 */     syNum = syNum * this.transYRationalDenom - this.transYRationalNum * syDenom;
/* 204 */     syDenom *= this.transYRationalDenom;
/* 207 */     syNum = 2L * syNum + syDenom;
/* 208 */     syDenom *= 2L;
/* 211 */     syNum *= this.invScaleYRationalNum;
/* 212 */     syDenom *= this.invScaleYRationalDenom;
/* 215 */     int srcYInt = Rational.floor(syNum, syDenom);
/* 216 */     long srcYFrac = syNum % syDenom;
/* 217 */     if (srcYInt < 0)
/* 218 */       srcYFrac = syDenom + srcYFrac; 
/* 223 */     long commonYDenom = syDenom * this.invScaleYRationalDenom;
/* 224 */     srcYFrac *= this.invScaleYRationalDenom;
/* 225 */     long newInvScaleYFrac = this.invScaleYFrac * syDenom;
/* 227 */     for (int j = 0; j < dheight; j++) {
/* 230 */       yvalues[j] = (srcYInt - srcRectY) * srcScanlineStride;
/* 236 */       srcYInt = (int)(srcYInt + this.invScaleYInt);
/* 240 */       srcYFrac += newInvScaleYFrac;
/* 245 */       if (srcYFrac >= commonYDenom) {
/* 246 */         srcYInt++;
/* 247 */         srcYFrac -= commonYDenom;
/*     */       } 
/*     */     } 
/* 251 */     switch (dstAccessor.getDataType()) {
/*     */       case 0:
/* 254 */         byteLoop(srcAccessor, destRect, dstAccessor, xvalues, yvalues);
/*     */         break;
/*     */       case 1:
/*     */       case 2:
/* 259 */         shortLoop(srcAccessor, destRect, dstAccessor, xvalues, yvalues);
/*     */         break;
/*     */       case 3:
/* 263 */         intLoop(srcAccessor, destRect, dstAccessor, xvalues, yvalues);
/*     */         break;
/*     */       case 4:
/* 267 */         floatLoop(srcAccessor, destRect, dstAccessor, xvalues, yvalues);
/*     */         break;
/*     */       case 5:
/* 271 */         doubleLoop(srcAccessor, destRect, dstAccessor, xvalues, yvalues);
/*     */         break;
/*     */       default:
/* 275 */         throw new RuntimeException(JaiI18N.getString("OrderedDitherOpImage0"));
/*     */     } 
/* 282 */     if (dstAccessor.isDataCopy()) {
/* 283 */       dstAccessor.clampDataArrays();
/* 284 */       dstAccessor.copyDataToRaster();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void byteLoop(RasterAccessor src, Rectangle dstRect, RasterAccessor dst, int[] xvalues, int[] yvalues) {
/* 291 */     int dwidth = dstRect.width;
/* 292 */     int dheight = dstRect.height;
/* 295 */     byte[][] dstDataArrays = dst.getByteDataArrays();
/* 296 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 297 */     int dstPixelStride = dst.getPixelStride();
/* 298 */     int dstScanlineStride = dst.getScanlineStride();
/* 299 */     int dnumBands = dst.getNumBands();
/* 302 */     int[] bandOffsets = src.getBandOffsets();
/* 303 */     byte[][] srcDataArrays = src.getByteDataArrays();
/* 306 */     int dstOffset = 0;
/* 311 */     for (int k = 0; k < dnumBands; k++) {
/* 312 */       byte[] dstData = dstDataArrays[k];
/* 313 */       byte[] srcData = srcDataArrays[k];
/* 314 */       int bandOffset = bandOffsets[k];
/* 315 */       int dstScanlineOffset = dstBandOffsets[k];
/* 316 */       for (int j = 0; j < dheight; j++) {
/* 317 */         int dstPixelOffset = dstScanlineOffset;
/* 318 */         int posy = yvalues[j] + bandOffset;
/* 319 */         for (int i = 0; i < dwidth; i++) {
/* 320 */           int posx = xvalues[i];
/* 321 */           int pos = posx + posy;
/* 322 */           dstData[dstPixelOffset] = srcData[pos];
/* 323 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/* 325 */         dstScanlineOffset += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void shortLoop(RasterAccessor src, Rectangle dstRect, RasterAccessor dst, int[] xvalues, int[] yvalues) {
/* 333 */     int dwidth = dstRect.width;
/* 334 */     int dheight = dstRect.height;
/* 337 */     short[][] dstDataArrays = dst.getShortDataArrays();
/* 338 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 339 */     int dstPixelStride = dst.getPixelStride();
/* 340 */     int dstScanlineStride = dst.getScanlineStride();
/* 341 */     int dnumBands = dst.getNumBands();
/* 344 */     int[] bandOffsets = src.getBandOffsets();
/* 345 */     short[][] srcDataArrays = src.getShortDataArrays();
/* 348 */     int dstOffset = 0;
/* 353 */     for (int k = 0; k < dnumBands; k++) {
/* 354 */       short[] dstData = dstDataArrays[k];
/* 355 */       short[] srcData = srcDataArrays[k];
/* 356 */       int bandOffset = bandOffsets[k];
/* 357 */       int dstScanlineOffset = dstBandOffsets[k];
/* 358 */       for (int j = 0; j < dheight; j++) {
/* 359 */         int dstPixelOffset = dstScanlineOffset;
/* 360 */         int posy = yvalues[j] + bandOffset;
/* 361 */         for (int i = 0; i < dwidth; i++) {
/* 362 */           int posx = xvalues[i];
/* 363 */           int pos = posx + posy;
/* 364 */           dstData[dstPixelOffset] = srcData[pos];
/* 365 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/* 367 */         dstScanlineOffset += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void intLoop(RasterAccessor src, Rectangle dstRect, RasterAccessor dst, int[] xvalues, int[] yvalues) {
/* 377 */     int dwidth = dstRect.width;
/* 378 */     int dheight = dstRect.height;
/* 380 */     int dnumBands = dst.getNumBands();
/* 381 */     int[][] dstDataArrays = dst.getIntDataArrays();
/* 382 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 383 */     int dstPixelStride = dst.getPixelStride();
/* 384 */     int dstScanlineStride = dst.getScanlineStride();
/* 386 */     int[] bandOffsets = src.getBandOffsets();
/* 387 */     int[][] srcDataArrays = src.getIntDataArrays();
/* 390 */     int dstOffset = 0;
/* 395 */     for (int k = 0; k < dnumBands; k++) {
/* 396 */       int[] dstData = dstDataArrays[k];
/* 397 */       int[] srcData = srcDataArrays[k];
/* 398 */       int bandOffset = bandOffsets[k];
/* 399 */       int dstScanlineOffset = dstBandOffsets[k];
/* 400 */       for (int j = 0; j < dheight; j++) {
/* 401 */         int dstPixelOffset = dstScanlineOffset;
/* 402 */         int posy = yvalues[j] + bandOffset;
/* 403 */         for (int i = 0; i < dwidth; i++) {
/* 404 */           int posx = xvalues[i];
/* 405 */           int pos = posx + posy;
/* 406 */           dstData[dstPixelOffset] = srcData[pos];
/* 407 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/* 409 */         dstScanlineOffset += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void floatLoop(RasterAccessor src, Rectangle dstRect, RasterAccessor dst, int[] xvalues, int[] yvalues) {
/* 419 */     int dwidth = dstRect.width;
/* 420 */     int dheight = dstRect.height;
/* 422 */     int dnumBands = dst.getNumBands();
/* 423 */     float[][] dstDataArrays = dst.getFloatDataArrays();
/* 424 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 425 */     int dstPixelStride = dst.getPixelStride();
/* 426 */     int dstScanlineStride = dst.getScanlineStride();
/* 428 */     float[][] srcDataArrays = src.getFloatDataArrays();
/* 429 */     int[] bandOffsets = src.getBandOffsets();
/* 432 */     int dstOffset = 0;
/* 437 */     for (int k = 0; k < dnumBands; k++) {
/* 438 */       float[] dstData = dstDataArrays[k];
/* 439 */       float[] srcData = srcDataArrays[k];
/* 440 */       int bandOffset = bandOffsets[k];
/* 441 */       int dstScanlineOffset = dstBandOffsets[k];
/* 442 */       for (int j = 0; j < dheight; j++) {
/* 443 */         int dstPixelOffset = dstScanlineOffset;
/* 444 */         int posy = yvalues[j] + bandOffset;
/* 445 */         for (int i = 0; i < dwidth; i++) {
/* 446 */           int posx = xvalues[i];
/* 447 */           int pos = posx + posy;
/* 448 */           dstData[dstPixelOffset] = srcData[pos];
/* 449 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/* 451 */         dstScanlineOffset += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void doubleLoop(RasterAccessor src, Rectangle dstRect, RasterAccessor dst, int[] xvalues, int[] yvalues) {
/* 461 */     int dwidth = dstRect.width;
/* 462 */     int dheight = dstRect.height;
/* 464 */     int dnumBands = dst.getNumBands();
/* 465 */     double[][] dstDataArrays = dst.getDoubleDataArrays();
/* 466 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 467 */     int dstPixelStride = dst.getPixelStride();
/* 468 */     int dstScanlineStride = dst.getScanlineStride();
/* 470 */     int[] bandOffsets = src.getBandOffsets();
/* 471 */     double[][] srcDataArrays = src.getDoubleDataArrays();
/* 474 */     int dstOffset = 0;
/* 479 */     for (int k = 0; k < dnumBands; k++) {
/* 480 */       double[] dstData = dstDataArrays[k];
/* 481 */       double[] srcData = srcDataArrays[k];
/* 482 */       int bandOffset = bandOffsets[k];
/* 483 */       int dstScanlineOffset = dstBandOffsets[k];
/* 484 */       for (int j = 0; j < dheight; j++) {
/* 485 */         int dstPixelOffset = dstScanlineOffset;
/* 486 */         int posy = yvalues[j] + bandOffset;
/* 487 */         for (int i = 0; i < dwidth; i++) {
/* 488 */           int posx = xvalues[i];
/* 489 */           int pos = posx + posy;
/* 490 */           dstData[dstPixelOffset] = srcData[pos];
/* 491 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/* 493 */         dstScanlineOffset += dstScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\ScaleNearestOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */