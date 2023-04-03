/*     */ package com.sun.media.jai.opimage;
/*     */ 
/*     */ import com.sun.media.jai.util.ImageUtil;
/*     */ import com.sun.media.jai.util.JDKWorkarounds;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.image.ColorModel;
/*     */ import java.awt.image.DataBufferByte;
/*     */ import java.awt.image.IndexColorModel;
/*     */ import java.awt.image.PixelInterleavedSampleModel;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.SampleModel;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.util.Map;
/*     */ import javax.media.jai.GeometricOpImage;
/*     */ import javax.media.jai.ImageLayout;
/*     */ import javax.media.jai.JAI;
/*     */ import javax.media.jai.PackedImageData;
/*     */ import javax.media.jai.PixelAccessor;
/*     */ 
/*     */ public class SubsampleBinaryToGrayOpImage extends GeometricOpImage {
/*     */   protected float scaleX;
/*     */   
/*     */   protected float scaleY;
/*     */   
/*     */   protected float invScaleX;
/*     */   
/*     */   protected float invScaleY;
/*     */   
/*     */   private float floatTol;
/*     */   
/*     */   private int blockX;
/*     */   
/*     */   private int blockY;
/*     */   
/*     */   private int dWidth;
/*     */   
/*     */   private int dHeight;
/*     */   
/*     */   private int[] xValues;
/*     */   
/*     */   private int[] yValues;
/*     */   
/* 103 */   private int[] lut = new int[256];
/*     */   
/*     */   protected byte[] lutGray;
/*     */   
/*     */   static ImageLayout layoutHelper(RenderedImage source, float scaleX, float scaleY, ImageLayout il, Map config) {
/* 119 */     ImageLayout layout = (il == null) ? new ImageLayout() : (ImageLayout)il.clone();
/* 125 */     int srcWidth = source.getWidth();
/* 126 */     int srcHeight = source.getHeight();
/* 128 */     float f_dw = scaleX * srcWidth;
/* 129 */     float f_dh = scaleY * srcHeight;
/* 130 */     float fTol = 0.1F * Math.min(scaleX / (f_dw + 1.0F), scaleY / (f_dh + 1.0F));
/* 132 */     int dWi = (int)f_dw;
/* 133 */     int dHi = (int)f_dh;
/* 137 */     if (Math.abs(Math.round(f_dw) - f_dw) < fTol)
/* 138 */       dWi = Math.round(f_dw); 
/* 141 */     if (Math.abs(Math.round(f_dh) - f_dh) < fTol)
/* 142 */       dHi = Math.round(f_dh); 
/* 146 */     layout.setMinX((int)(scaleX * source.getMinX()));
/* 147 */     layout.setMinY((int)(scaleY * source.getMinY()));
/* 149 */     layout.setWidth(dWi);
/* 150 */     layout.setHeight(dHi);
/* 153 */     SampleModel sm = layout.getSampleModel(null);
/* 155 */     if (sm == null || sm.getDataType() != 0 || (!(sm instanceof PixelInterleavedSampleModel) && (!(sm instanceof java.awt.image.SinglePixelPackedSampleModel) || sm.getNumBands() != 1)))
/* 162 */       sm = new PixelInterleavedSampleModel(0, 1, 1, 1, 1, new int[] { 0 }); 
/* 170 */     layout.setSampleModel(sm);
/* 172 */     ColorModel cm = layout.getColorModel(null);
/* 174 */     if (cm == null || !JDKWorkarounds.areCompatibleDataModels(sm, cm))
/* 177 */       layout.setColorModel(ImageUtil.getCompatibleColorModel(sm, config)); 
/* 181 */     return layout;
/*     */   }
/*     */   
/*     */   private static Map configHelper(Map configuration) {
/*     */     Map config;
/* 190 */     if (configuration == null) {
/* 191 */       config = new RenderingHints(JAI.KEY_REPLACE_INDEX_COLOR_MODEL, Boolean.FALSE);
/*     */     } else {
/* 195 */       config = configuration;
/* 197 */       if (!config.containsKey(JAI.KEY_REPLACE_INDEX_COLOR_MODEL)) {
/* 198 */         RenderingHints hints = (RenderingHints)configuration;
/* 199 */         config = (RenderingHints)hints.clone();
/* 200 */         config.put(JAI.KEY_REPLACE_INDEX_COLOR_MODEL, Boolean.FALSE);
/*     */       } 
/*     */     } 
/* 204 */     return config;
/*     */   }
/*     */   
/*     */   public SubsampleBinaryToGrayOpImage(RenderedImage source, ImageLayout layout, Map config, float scaleX, float scaleY) {
/* 250 */     super(vectorize(source), layoutHelper(source, scaleX, scaleY, layout, config), configHelper(config), true, null, null, null);
/* 258 */     this.scaleX = scaleX;
/* 259 */     this.scaleY = scaleY;
/* 260 */     int srcMinX = source.getMinX();
/* 261 */     int srcMinY = source.getMinY();
/* 262 */     int srcWidth = source.getWidth();
/* 263 */     int srcHeight = source.getHeight();
/* 266 */     computeDestInfo(srcWidth, srcHeight);
/* 268 */     if (this.extender == null) {
/* 269 */       this.computableBounds = new Rectangle(0, 0, this.dWidth, this.dHeight);
/*     */     } else {
/* 272 */       this.computableBounds = getBounds();
/*     */     } 
/* 277 */     buildLookupTables();
/* 280 */     computeXYValues(srcWidth, srcHeight, srcMinX, srcMinY);
/*     */   }
/*     */   
/*     */   public Point2D mapDestPoint(Point2D destPt) {
/* 298 */     if (destPt == null)
/* 299 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 302 */     Point2D pt = (Point2D)destPt.clone();
/* 304 */     pt.setLocation(destPt.getX() / this.scaleX, destPt.getY() / this.scaleY);
/* 306 */     return pt;
/*     */   }
/*     */   
/*     */   public Point2D mapSourcePoint(Point2D sourcePt) {
/* 324 */     if (sourcePt == null)
/* 325 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 328 */     Point2D pt = (Point2D)sourcePt.clone();
/* 330 */     pt.setLocation(sourcePt.getX() * this.scaleX, sourcePt.getY() * this.scaleY);
/* 332 */     return pt;
/*     */   }
/*     */   
/*     */   protected Rectangle forwardMapRect(Rectangle sourceRect, int sourceIndex) {
/* 355 */     if (sourceRect == null)
/* 356 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 359 */     if (sourceIndex != 0)
/* 360 */       throw new IllegalArgumentException(JaiI18N.getString("Generic1")); 
/* 364 */     int x0 = sourceRect.x - this.blockX + 1;
/* 365 */     int y0 = sourceRect.y - this.blockY + 1;
/* 366 */     x0 = (x0 < 0) ? 0 : x0;
/* 367 */     y0 = (y0 < 0) ? 0 : y0;
/* 369 */     int dx0 = (int)(x0 * this.scaleX);
/* 370 */     int dy0 = (int)(y0 * this.scaleY);
/* 371 */     while (this.xValues[dx0] > x0 && dx0 > 0)
/* 372 */       dx0--; 
/* 374 */     while (this.yValues[dy0] > y0 && dy0 > 0)
/* 375 */       dy0--; 
/* 378 */     int x1 = sourceRect.x + sourceRect.width - 1;
/* 379 */     int y1 = sourceRect.y + sourceRect.height - 1;
/* 381 */     int dx1 = Math.round(x1 * this.scaleX);
/* 382 */     int dy1 = Math.round(y1 * this.scaleY);
/* 383 */     dx1 = (dx1 >= this.dWidth) ? (this.dWidth - 1) : dx1;
/* 384 */     dy1 = (dy1 >= this.dHeight) ? (this.dHeight - 1) : dy1;
/* 385 */     while (this.xValues[dx1] < x1 && dx1 < this.dWidth - 1)
/* 386 */       dx1++; 
/* 388 */     while (this.yValues[dy1] < y1 && dy1 < this.dHeight - 1)
/* 389 */       dy1++; 
/* 392 */     dx0 += this.minX;
/* 393 */     dy0 += this.minY;
/* 394 */     dx1 += this.minX;
/* 395 */     dy1 += this.minY;
/* 398 */     return new Rectangle(dx0, dy0, dx1 - dx0 + 1, dy1 - dy0 + 1);
/*     */   }
/*     */   
/*     */   protected Rectangle backwardMapRect(Rectangle destRect, int sourceIndex) {
/* 420 */     if (destRect == null)
/* 421 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 424 */     if (sourceIndex != 0)
/* 425 */       throw new IllegalArgumentException(JaiI18N.getString("Generic1")); 
/* 430 */     int sx0 = this.xValues[destRect.x - this.minX];
/* 431 */     int sy0 = this.yValues[destRect.y - this.minY];
/* 432 */     int sx1 = this.xValues[destRect.x - this.minX + destRect.width - 1];
/* 433 */     int sy1 = this.yValues[destRect.y - this.minY + destRect.height - 1];
/* 435 */     return new Rectangle(sx0, sy0, sx1 - sx0 + this.blockX, sy1 - sy0 + this.blockY);
/*     */   }
/*     */   
/*     */   protected void computeRect(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/* 451 */     Raster source = sources[0];
/* 453 */     switch (source.getSampleModel().getDataType()) {
/*     */       case 0:
/*     */       case 1:
/*     */       case 2:
/*     */       case 3:
/* 458 */         byteLoop(source, dest, destRect);
/*     */         return;
/*     */     } 
/* 461 */     throw new RuntimeException(JaiI18N.getString("SubsampleBinaryToGrayOpImage0"));
/*     */   }
/*     */   
/*     */   private void byteLoop(Raster source, WritableRaster dest, Rectangle destRect) {
/* 467 */     PixelAccessor pa = new PixelAccessor(source.getSampleModel(), null);
/* 468 */     PackedImageData pid = pa.getPackedPixels(source, source.getBounds(), false, false);
/* 470 */     byte[] sourceData = pid.data;
/* 471 */     int sourceDBOffset = pid.offset;
/* 472 */     int dx = destRect.x;
/* 473 */     int dy = destRect.y;
/* 474 */     int dwi = destRect.width;
/* 475 */     int dhi = destRect.height;
/* 476 */     int sourceTransX = pid.rect.x;
/* 477 */     int sourceTransY = pid.rect.y;
/* 479 */     PixelInterleavedSampleModel destSM = (PixelInterleavedSampleModel)dest.getSampleModel();
/* 481 */     DataBufferByte destDB = (DataBufferByte)dest.getDataBuffer();
/* 483 */     int destTransX = dest.getSampleModelTranslateX();
/* 484 */     int destTransY = dest.getSampleModelTranslateY();
/* 485 */     int destScanlineStride = destSM.getScanlineStride();
/* 487 */     byte[] destData = destDB.getData();
/* 488 */     int destDBOffset = destDB.getOffset();
/* 490 */     int[] sbytenum = new int[dwi];
/* 491 */     int[] sstartbit = new int[dwi];
/* 493 */     int[] sAreaBitsOn = new int[dwi];
/* 494 */     for (int i = 0; i < dwi; i++) {
/* 495 */       int x = this.xValues[dx + i - this.minX];
/* 496 */       int sbitnum = pid.bitOffset + x - sourceTransX;
/* 497 */       sbytenum[i] = sbitnum >> 3;
/* 498 */       sstartbit[i] = sbitnum % 8;
/*     */     } 
/* 501 */     for (int j = 0; j < dhi; j++) {
/* 503 */       for (int k = 0; k < dwi; k++)
/* 504 */         sAreaBitsOn[k] = 0; 
/* 507 */       for (int y = this.yValues[dy + j - this.minY]; y < this.yValues[dy + j - this.minY] + this.blockY; y++) {
/* 509 */         int sourceYOffset = (y - sourceTransY) * pid.lineStride + sourceDBOffset;
/* 512 */         int delement = 0;
/* 513 */         for (int n = 0; n < dwi; n++) {
/* 514 */           delement = 0;
/* 515 */           int sendbiti = sstartbit[n] + this.blockX - 1;
/* 516 */           int sendbytenumi = sbytenum[n] + (sendbiti >> 3);
/* 517 */           sendbiti %= 8;
/* 519 */           int selement = 0xFF & sourceData[sourceYOffset + sbytenum[n]];
/* 521 */           if (sbytenum[n] == sendbytenumi) {
/* 522 */             selement <<= 24 + sstartbit[n];
/* 523 */             selement >>>= 31 - sendbiti + sstartbit[n];
/* 524 */             delement += this.lut[selement];
/*     */           } else {
/* 526 */             selement <<= 24 + sstartbit[n];
/* 527 */             selement >>>= 24;
/* 528 */             delement += this.lut[selement];
/* 529 */             for (int b = sbytenum[n] + 1; b < sendbytenumi; b++) {
/* 530 */               selement = 0xFF & sourceData[sourceYOffset + b];
/* 531 */               delement += this.lut[selement];
/*     */             } 
/* 534 */             selement = 0xFF & sourceData[sourceYOffset + sendbytenumi];
/* 535 */             selement >>>= 7 - sendbiti;
/* 536 */             delement += this.lut[selement];
/*     */           } 
/* 538 */           sAreaBitsOn[n] = sAreaBitsOn[n] + delement;
/*     */         } 
/*     */       } 
/* 541 */       int destYOffset = (j + dy - destTransY) * destScanlineStride + destDBOffset;
/* 544 */       destYOffset += dx - destTransX;
/* 547 */       for (int m = 0; m < dwi; m++)
/* 548 */         destData[destYOffset + m] = this.lutGray[sAreaBitsOn[m]]; 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeDestInfo(int srcWidth, int srcHeight) {
/* 557 */     this.invScaleX = 1.0F / this.scaleX;
/* 558 */     this.invScaleY = 1.0F / this.scaleY;
/* 559 */     this.blockX = (int)Math.ceil(this.invScaleX);
/* 560 */     this.blockY = (int)Math.ceil(this.invScaleY);
/* 563 */     float f_dw = this.scaleX * srcWidth;
/* 564 */     float f_dh = this.scaleY * srcHeight;
/* 565 */     this.floatTol = 0.1F * Math.min(this.scaleX / (f_dw + 1.0F), this.scaleY / (f_dh + 1.0F));
/* 567 */     this.dWidth = (int)f_dw;
/* 568 */     this.dHeight = (int)f_dh;
/* 572 */     if (Math.abs(Math.round(f_dw) - f_dw) < this.floatTol)
/* 573 */       this.dWidth = Math.round(f_dw); 
/* 576 */     if (Math.abs(Math.round(f_dh) - f_dh) < this.floatTol)
/* 577 */       this.dHeight = Math.round(f_dh); 
/* 580 */     if (Math.abs(Math.round(this.invScaleX) - this.invScaleX) < this.floatTol) {
/* 581 */       this.invScaleX = Math.round(this.invScaleX);
/* 582 */       this.blockX = (int)this.invScaleX;
/*     */     } 
/* 585 */     if (Math.abs(Math.round(this.invScaleY) - this.invScaleY) < this.floatTol) {
/* 586 */       this.invScaleY = Math.round(this.invScaleY);
/* 587 */       this.blockY = (int)this.invScaleY;
/*     */     } 
/*     */   }
/*     */   
/*     */   private final void buildLookupTables() {
/* 598 */     this.lut[0] = 0;
/* 598 */     this.lut[1] = 1;
/* 598 */     this.lut[2] = 1;
/* 598 */     this.lut[3] = 2;
/* 599 */     this.lut[4] = 1;
/* 599 */     this.lut[5] = 2;
/* 599 */     this.lut[6] = 2;
/* 599 */     this.lut[7] = 3;
/* 600 */     this.lut[8] = 1;
/* 600 */     this.lut[9] = 2;
/* 600 */     this.lut[10] = 2;
/* 600 */     this.lut[11] = 3;
/* 601 */     this.lut[12] = 2;
/* 601 */     this.lut[13] = 3;
/* 601 */     this.lut[14] = 3;
/* 601 */     this.lut[15] = 4;
/*     */     int i;
/* 602 */     for (i = 16; i < 256; i++)
/* 603 */       this.lut[i] = this.lut[i & 0xF] + this.lut[i >> 4 & 0xF]; 
/* 607 */     if (this.lutGray != null)
/*     */       return; 
/* 608 */     this.lutGray = new byte[this.blockX * this.blockY + 1];
/* 609 */     for (i = 0; i < this.lutGray.length; i++) {
/* 610 */       int tmp = Math.round(255.0F * i / (this.lutGray.length - 1.0F));
/* 611 */       this.lutGray[i] = (tmp > 255) ? -1 : (byte)tmp;
/*     */     } 
/* 615 */     if (isMinWhite(getSourceImage(0).getColorModel()))
/* 616 */       for (i = 0; i < this.lutGray.length; i++)
/* 617 */         this.lutGray[i] = (byte)(255 - (0xFF & this.lutGray[i]));  
/*     */   }
/*     */   
/*     */   private void computeXYValues(int srcWidth, int srcHeight, int srcMinX, int srcMinY) {
/* 626 */     if (this.xValues == null || this.yValues == null) {
/* 627 */       this.xValues = new int[this.dWidth];
/* 628 */       this.yValues = new int[this.dHeight];
/*     */     } 
/*     */     int i;
/* 632 */     for (i = 0; i < this.dWidth; i++) {
/* 633 */       float tmp = this.invScaleX * i;
/* 634 */       this.xValues[i] = Math.round(tmp);
/*     */     } 
/* 636 */     if (this.xValues[this.dWidth - 1] + this.blockX > srcWidth)
/* 637 */       this.xValues[this.dWidth - 1] = this.xValues[this.dWidth - 1] - 1; 
/* 640 */     for (i = 0; i < this.dHeight; i++) {
/* 641 */       float tmp = this.invScaleY * i;
/* 642 */       this.yValues[i] = Math.round(tmp);
/*     */     } 
/* 644 */     if (this.yValues[this.dHeight - 1] + this.blockY > srcHeight)
/* 645 */       this.yValues[this.dHeight - 1] = this.yValues[this.dHeight - 1] - 1; 
/* 649 */     if (srcMinX != 0)
/* 650 */       for (i = 0; i < this.dWidth; ) {
/* 650 */         this.xValues[i] = this.xValues[i] + srcMinX;
/* 650 */         i++;
/*     */       }  
/* 651 */     if (srcMinY != 0)
/* 652 */       for (i = 0; i < this.dHeight; ) {
/* 652 */         this.yValues[i] = this.yValues[i] + srcMinY;
/* 652 */         i++;
/*     */       }  
/*     */   }
/*     */   
/*     */   static boolean isMinWhite(ColorModel cm) {
/* 660 */     if (cm == null || !(cm instanceof IndexColorModel))
/* 660 */       return false; 
/* 662 */     byte[] red = new byte[256];
/* 663 */     ((IndexColorModel)cm).getReds(red);
/* 664 */     return (red[0] == -1);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\SubsampleBinaryToGrayOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */