/*     */ package com.sun.media.jai.opimage;
/*     */ 
/*     */ import com.sun.media.jai.util.JDKWorkarounds;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.image.ColorModel;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.SampleModel;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.util.Map;
/*     */ import java.util.Vector;
/*     */ import javax.media.jai.ImageLayout;
/*     */ import javax.media.jai.PixelAccessor;
/*     */ import javax.media.jai.PointOpImage;
/*     */ import javax.media.jai.RasterFactory;
/*     */ import javax.media.jai.UnpackedImageData;
/*     */ 
/*     */ class BandMergeOpImage extends PointOpImage {
/*     */   ColorModel[] colorModels;
/*     */   
/*     */   public BandMergeOpImage(Vector sources, Map config, ImageLayout layout) {
/*  79 */     super(sources, layoutHelper(sources, layout), config, true);
/*  82 */     permitInPlaceOperation();
/*  85 */     int numSrcs = sources.size();
/*  86 */     this.colorModels = new ColorModel[numSrcs];
/*  88 */     for (int i = 0; i < numSrcs; i++)
/*  89 */       this.colorModels[i] = ((RenderedImage)sources.get(i)).getColorModel(); 
/*     */   }
/*     */   
/*     */   private static int totalNumBands(Vector sources) {
/*  95 */     int total = 0;
/*  97 */     for (int i = 0; i < sources.size(); i++) {
/*  98 */       RenderedImage image = sources.get(i);
/* 100 */       if (image.getColorModel() instanceof java.awt.image.IndexColorModel) {
/* 101 */         total += image.getColorModel().getNumComponents();
/*     */       } else {
/* 103 */         total += image.getSampleModel().getNumBands();
/*     */       } 
/*     */     } 
/* 107 */     return total;
/*     */   }
/*     */   
/*     */   private static ImageLayout layoutHelper(Vector sources, ImageLayout il) {
/* 113 */     ImageLayout layout = (il == null) ? new ImageLayout() : (ImageLayout)il.clone();
/* 115 */     int numSources = sources.size();
/* 121 */     int destNumBands = totalNumBands(sources);
/* 123 */     int destDataType = 0;
/* 124 */     RenderedImage srci = sources.get(0);
/* 125 */     Rectangle destBounds = new Rectangle(srci.getMinX(), srci.getMinY(), srci.getWidth(), srci.getHeight());
/* 127 */     for (int i = 0; i < numSources; i++) {
/* 128 */       srci = sources.get(i);
/* 129 */       destBounds = destBounds.intersection(new Rectangle(srci.getMinX(), srci.getMinY(), srci.getWidth(), srci.getHeight()));
/* 132 */       int typei = srci.getSampleModel().getTransferType();
/* 135 */       destDataType = (typei > destDataType) ? typei : destDataType;
/*     */     } 
/* 138 */     SampleModel sm = layout.getSampleModel(sources.get(0));
/* 140 */     if (sm.getNumBands() < destNumBands) {
/* 141 */       int[] destOffsets = new int[destNumBands];
/* 143 */       for (int j = 0; j < destNumBands; j++)
/* 144 */         destOffsets[j] = j; 
/* 148 */       int destTileWidth = sm.getWidth();
/* 149 */       int destTileHeight = sm.getHeight();
/* 150 */       if (layout.isValid(64))
/* 152 */         destTileWidth = layout.getTileWidth(sources.get(0)); 
/* 155 */       if (layout.isValid(128))
/* 157 */         destTileHeight = layout.getTileHeight(sources.get(0)); 
/* 161 */       sm = RasterFactory.createComponentSampleModel(sm, destDataType, destTileWidth, destTileHeight, destNumBands);
/* 168 */       layout.setSampleModel(sm);
/*     */     } 
/* 171 */     ColorModel cm = layout.getColorModel(null);
/* 173 */     if (cm != null && !JDKWorkarounds.areCompatibleDataModels(sm, cm))
/* 176 */       layout.unsetValid(512); 
/* 179 */     return layout;
/*     */   }
/*     */   
/*     */   protected void computeRect(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/* 196 */     int destType = dest.getTransferType();
/* 198 */     switch (destType) {
/*     */       case 0:
/* 200 */         byteLoop(sources, dest, destRect);
/*     */         return;
/*     */       case 1:
/*     */       case 2:
/* 204 */         shortLoop(sources, dest, destRect);
/*     */         return;
/*     */       case 3:
/* 207 */         intLoop(sources, dest, destRect);
/*     */         return;
/*     */       case 4:
/* 210 */         floatLoop(sources, dest, destRect);
/*     */         return;
/*     */       case 5:
/* 213 */         doubleLoop(sources, dest, destRect);
/*     */         return;
/*     */     } 
/* 216 */     throw new RuntimeException();
/*     */   }
/*     */   
/*     */   private void byteLoop(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/* 224 */     int nSrcs = sources.length;
/* 225 */     int[] snbands = new int[nSrcs];
/* 226 */     PixelAccessor[] pas = new PixelAccessor[nSrcs];
/* 228 */     for (int i = 0; i < nSrcs; i++) {
/* 229 */       pas[i] = new PixelAccessor(sources[i].getSampleModel(), this.colorModels[i]);
/* 231 */       if (this.colorModels[i] instanceof java.awt.image.IndexColorModel) {
/* 232 */         snbands[i] = this.colorModels[i].getNumComponents();
/*     */       } else {
/* 234 */         snbands[i] = sources[i].getNumBands();
/*     */       } 
/*     */     } 
/* 238 */     int dnbands = dest.getNumBands();
/* 239 */     int destType = dest.getTransferType();
/* 240 */     PixelAccessor d = new PixelAccessor(dest.getSampleModel(), null);
/* 242 */     UnpackedImageData dimd = d.getPixels(dest, destRect, destType, true);
/* 247 */     byte[][] dstdata = (byte[][])dimd.data;
/* 249 */     for (int sindex = 0, db = 0; sindex < nSrcs; sindex++) {
/* 251 */       UnpackedImageData simd = (this.colorModels[sindex] instanceof java.awt.image.IndexColorModel) ? pas[sindex].getComponents(sources[sindex], destRect, sources[sindex].getSampleModel().getTransferType()) : pas[sindex].getPixels(sources[sindex], destRect, sources[sindex].getSampleModel().getTransferType(), false);
/* 262 */       int srcPixelStride = simd.pixelStride;
/* 263 */       int srcLineStride = simd.lineStride;
/* 264 */       int dstPixelStride = dimd.pixelStride;
/* 265 */       int dstLineStride = dimd.lineStride;
/* 266 */       int dRectWidth = destRect.width;
/* 268 */       for (int sb = 0; sb < snbands[sindex] && 
/* 269 */         db < dnbands; sb++, db++) {
/* 274 */         byte[] dstdatabandb = dstdata[db];
/* 275 */         byte[][] srcdata = (byte[][])simd.data;
/* 276 */         byte[] srcdatabandsb = srcdata[sb];
/* 277 */         int srcstart = simd.bandOffsets[sb];
/* 278 */         int dststart = dimd.bandOffsets[db];
/* 280 */         int y = 0;
/* 281 */         for (; y < destRect.height; 
/* 282 */           y++, srcstart += srcLineStride, dststart += dstLineStride) {
/* 284 */           int j = 0, srcpos = srcstart, dstpos = dststart;
/* 285 */           for (; j < dRectWidth; 
/* 286 */             j++, srcpos += srcPixelStride, dstpos += dstPixelStride)
/* 288 */             dstdatabandb[dstpos] = srcdatabandsb[srcpos]; 
/*     */         } 
/*     */       } 
/*     */     } 
/* 294 */     d.setPixels(dimd);
/*     */   }
/*     */   
/*     */   private void shortLoop(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/* 301 */     int nSrcs = sources.length;
/* 302 */     int[] snbands = new int[nSrcs];
/* 303 */     PixelAccessor[] pas = new PixelAccessor[nSrcs];
/* 305 */     for (int i = 0; i < nSrcs; i++) {
/* 306 */       pas[i] = new PixelAccessor(sources[i].getSampleModel(), this.colorModels[i]);
/* 308 */       if (this.colorModels[i] instanceof java.awt.image.IndexColorModel) {
/* 309 */         snbands[i] = this.colorModels[i].getNumComponents();
/*     */       } else {
/* 311 */         snbands[i] = sources[i].getNumBands();
/*     */       } 
/*     */     } 
/* 315 */     int dnbands = dest.getNumBands();
/* 316 */     int destType = dest.getTransferType();
/* 317 */     PixelAccessor d = new PixelAccessor(dest.getSampleModel(), null);
/* 319 */     UnpackedImageData dimd = d.getPixels(dest, destRect, destType, true);
/* 324 */     short[][] dstdata = (short[][])dimd.data;
/* 326 */     for (int sindex = 0, db = 0; sindex < nSrcs; sindex++) {
/* 328 */       UnpackedImageData simd = (this.colorModels[sindex] instanceof java.awt.image.IndexColorModel) ? pas[sindex].getComponents(sources[sindex], destRect, sources[sindex].getSampleModel().getTransferType()) : pas[sindex].getPixels(sources[sindex], destRect, sources[sindex].getSampleModel().getTransferType(), false);
/* 338 */       int srcPixelStride = simd.pixelStride;
/* 339 */       int srcLineStride = simd.lineStride;
/* 340 */       int dstPixelStride = dimd.pixelStride;
/* 341 */       int dstLineStride = dimd.lineStride;
/* 342 */       int dRectWidth = destRect.width;
/* 344 */       for (int sb = 0; sb < snbands[sindex]; sb++, db++) {
/* 345 */         if (db < dnbands) {
/* 346 */           short[][] srcdata = (short[][])simd.data;
/* 347 */           int srcstart = simd.bandOffsets[sb];
/* 348 */           int dststart = dimd.bandOffsets[db];
/* 349 */           int y = 0;
/* 350 */           for (; y < destRect.height; 
/* 351 */             y++, srcstart += srcLineStride, dststart += dstLineStride) {
/* 353 */             int j = 0, srcpos = srcstart, dstpos = dststart;
/* 354 */             for (; j < dRectWidth; 
/* 355 */               j++, srcpos += srcPixelStride, dstpos += dstPixelStride)
/* 357 */               dstdata[db][dstpos] = srcdata[sb][srcpos]; 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 364 */     d.setPixels(dimd);
/*     */   }
/*     */   
/*     */   private void intLoop(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/* 371 */     int nSrcs = sources.length;
/* 372 */     int[] snbands = new int[nSrcs];
/* 373 */     PixelAccessor[] pas = new PixelAccessor[nSrcs];
/* 375 */     for (int i = 0; i < nSrcs; i++) {
/* 376 */       pas[i] = new PixelAccessor(sources[i].getSampleModel(), this.colorModels[i]);
/* 378 */       if (this.colorModels[i] instanceof java.awt.image.IndexColorModel) {
/* 379 */         snbands[i] = this.colorModels[i].getNumComponents();
/*     */       } else {
/* 381 */         snbands[i] = sources[i].getNumBands();
/*     */       } 
/*     */     } 
/* 385 */     int dnbands = dest.getNumBands();
/* 386 */     int destType = dest.getTransferType();
/* 387 */     PixelAccessor d = new PixelAccessor(dest.getSampleModel(), null);
/* 389 */     UnpackedImageData dimd = d.getPixels(dest, destRect, destType, true);
/* 394 */     int[][] dstdata = (int[][])dimd.data;
/* 396 */     for (int sindex = 0, db = 0; sindex < nSrcs; sindex++) {
/* 398 */       UnpackedImageData simd = (this.colorModels[sindex] instanceof java.awt.image.IndexColorModel) ? pas[sindex].getComponents(sources[sindex], destRect, sources[sindex].getSampleModel().getTransferType()) : pas[sindex].getPixels(sources[sindex], destRect, sources[sindex].getSampleModel().getTransferType(), false);
/* 408 */       int srcPixelStride = simd.pixelStride;
/* 409 */       int srcLineStride = simd.lineStride;
/* 410 */       int dstPixelStride = dimd.pixelStride;
/* 411 */       int dstLineStride = dimd.lineStride;
/* 412 */       int dRectWidth = destRect.width;
/* 414 */       for (int sb = 0; sb < snbands[sindex]; sb++, db++) {
/* 415 */         if (db < dnbands) {
/* 416 */           int[][] srcdata = (int[][])simd.data;
/* 417 */           int srcstart = simd.bandOffsets[sb];
/* 418 */           int dststart = dimd.bandOffsets[db];
/* 419 */           int y = 0;
/* 420 */           for (; y < destRect.height; 
/* 421 */             y++, srcstart += srcLineStride, dststart += dstLineStride) {
/* 423 */             int j = 0, srcpos = srcstart, dstpos = dststart;
/* 424 */             for (; j < dRectWidth; 
/* 425 */               j++, srcpos += srcPixelStride, dstpos += dstPixelStride)
/* 427 */               dstdata[db][dstpos] = srcdata[sb][srcpos]; 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 434 */     d.setPixels(dimd);
/*     */   }
/*     */   
/*     */   private void floatLoop(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/* 442 */     int nSrcs = sources.length;
/* 443 */     int[] snbands = new int[nSrcs];
/* 444 */     PixelAccessor[] pas = new PixelAccessor[nSrcs];
/* 446 */     for (int i = 0; i < nSrcs; i++) {
/* 447 */       pas[i] = new PixelAccessor(sources[i].getSampleModel(), this.colorModels[i]);
/* 449 */       if (this.colorModels[i] instanceof java.awt.image.IndexColorModel) {
/* 450 */         snbands[i] = this.colorModels[i].getNumComponents();
/*     */       } else {
/* 452 */         snbands[i] = sources[i].getNumBands();
/*     */       } 
/*     */     } 
/* 456 */     int dnbands = dest.getNumBands();
/* 457 */     int destType = dest.getTransferType();
/* 458 */     PixelAccessor d = new PixelAccessor(dest.getSampleModel(), null);
/* 460 */     UnpackedImageData dimd = d.getPixels(dest, destRect, destType, true);
/* 465 */     float[][] dstdata = (float[][])dimd.data;
/* 467 */     for (int sindex = 0, db = 0; sindex < nSrcs; sindex++) {
/* 469 */       UnpackedImageData simd = (this.colorModels[sindex] instanceof java.awt.image.IndexColorModel) ? pas[sindex].getComponents(sources[sindex], destRect, sources[sindex].getSampleModel().getTransferType()) : pas[sindex].getPixels(sources[sindex], destRect, sources[sindex].getSampleModel().getTransferType(), false);
/* 479 */       int srcPixelStride = simd.pixelStride;
/* 480 */       int srcLineStride = simd.lineStride;
/* 481 */       int dstPixelStride = dimd.pixelStride;
/* 482 */       int dstLineStride = dimd.lineStride;
/* 483 */       int dRectWidth = destRect.width;
/* 485 */       for (int sb = 0; sb < snbands[sindex]; sb++, db++) {
/* 486 */         if (db < dnbands) {
/* 487 */           float[][] srcdata = (float[][])simd.data;
/* 488 */           int srcstart = simd.bandOffsets[sb];
/* 489 */           int dststart = dimd.bandOffsets[db];
/* 490 */           int y = 0;
/* 491 */           for (; y < destRect.height; 
/* 492 */             y++, srcstart += srcLineStride, dststart += dstLineStride) {
/* 494 */             int j = 0, srcpos = srcstart, dstpos = dststart;
/* 495 */             for (; j < dRectWidth; 
/* 496 */               j++, srcpos += srcPixelStride, dstpos += dstPixelStride)
/* 498 */               dstdata[db][dstpos] = srcdata[sb][srcpos]; 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 505 */     d.setPixels(dimd);
/*     */   }
/*     */   
/*     */   private void doubleLoop(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/* 512 */     int nSrcs = sources.length;
/* 513 */     int[] snbands = new int[nSrcs];
/* 514 */     PixelAccessor[] pas = new PixelAccessor[nSrcs];
/* 516 */     for (int i = 0; i < nSrcs; i++) {
/* 517 */       pas[i] = new PixelAccessor(sources[i].getSampleModel(), this.colorModels[i]);
/* 519 */       if (this.colorModels[i] instanceof java.awt.image.IndexColorModel) {
/* 520 */         snbands[i] = this.colorModels[i].getNumComponents();
/*     */       } else {
/* 522 */         snbands[i] = sources[i].getNumBands();
/*     */       } 
/*     */     } 
/* 526 */     int dnbands = dest.getNumBands();
/* 527 */     int destType = dest.getTransferType();
/* 528 */     PixelAccessor d = new PixelAccessor(dest.getSampleModel(), null);
/* 530 */     UnpackedImageData dimd = d.getPixels(dest, destRect, destType, true);
/* 535 */     double[][] dstdata = (double[][])dimd.data;
/* 537 */     for (int sindex = 0, db = 0; sindex < nSrcs; sindex++) {
/* 539 */       UnpackedImageData simd = (this.colorModels[sindex] instanceof java.awt.image.IndexColorModel) ? pas[sindex].getComponents(sources[sindex], destRect, sources[sindex].getSampleModel().getTransferType()) : pas[sindex].getPixels(sources[sindex], destRect, sources[sindex].getSampleModel().getTransferType(), false);
/* 549 */       int srcPixelStride = simd.pixelStride;
/* 550 */       int srcLineStride = simd.lineStride;
/* 551 */       int dstPixelStride = dimd.pixelStride;
/* 552 */       int dstLineStride = dimd.lineStride;
/* 553 */       int dRectWidth = destRect.width;
/* 555 */       for (int sb = 0; sb < snbands[sindex]; sb++, db++) {
/* 556 */         if (db < dnbands) {
/* 557 */           double[][] srcdata = (double[][])simd.data;
/* 558 */           int srcstart = simd.bandOffsets[sb];
/* 559 */           int dststart = dimd.bandOffsets[db];
/* 560 */           int y = 0;
/* 561 */           for (; y < destRect.height; 
/* 562 */             y++, srcstart += srcLineStride, dststart += dstLineStride) {
/* 564 */             int j = 0, srcpos = srcstart, dstpos = dststart;
/* 565 */             for (; j < dRectWidth; 
/* 566 */               j++, srcpos += srcPixelStride, dstpos += dstPixelStride)
/* 568 */               dstdata[db][dstpos] = srcdata[sb][srcpos]; 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 575 */     d.setPixels(dimd);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\BandMergeOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */