/*     */ package com.sun.media.jai.opimage;
/*     */ 
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.image.DataBufferByte;
/*     */ import java.awt.image.PixelInterleavedSampleModel;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.util.Map;
/*     */ import javax.media.jai.GeometricOpImage;
/*     */ import javax.media.jai.ImageLayout;
/*     */ import javax.media.jai.PackedImageData;
/*     */ import javax.media.jai.PixelAccessor;
/*     */ 
/*     */ class SubsampleBinaryToGray4x4OpImage extends GeometricOpImage {
/*  74 */   private int blockX = 4;
/*     */   
/*  75 */   private int blockY = 4;
/*     */   
/*     */   private int dWidth;
/*     */   
/*     */   private int dHeight;
/*     */   
/*     */   private int[] xValues;
/*     */   
/*     */   private int[] yValues;
/*     */   
/*     */   private int[] lut;
/*     */   
/*     */   private byte[] lutGray;
/*     */   
/*     */   public SubsampleBinaryToGray4x4OpImage(RenderedImage source, ImageLayout layout, Map config) {
/* 144 */     super(vectorize(source), SubsampleBinaryToGrayOpImage.layoutHelper(source, 0.25F, 0.25F, layout, config), config, true, null, null, null);
/* 156 */     int srcWidth = source.getWidth();
/* 157 */     int srcHeight = source.getHeight();
/* 159 */     this.blockX = this.blockY = 4;
/* 161 */     this.dWidth = srcWidth / this.blockX;
/* 162 */     this.dHeight = srcHeight / this.blockY;
/* 164 */     if (this.extender == null) {
/* 165 */       this.computableBounds = new Rectangle(0, 0, this.dWidth, this.dHeight);
/*     */     } else {
/* 168 */       this.computableBounds = getBounds();
/*     */     } 
/* 173 */     buildLookupTables();
/* 176 */     computeXYValues(this.dWidth, this.dHeight);
/*     */   }
/*     */   
/*     */   public Point2D mapDestPoint(Point2D destPt) {
/* 194 */     if (destPt == null)
/* 195 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 198 */     Point2D pt = (Point2D)destPt.clone();
/* 200 */     pt.setLocation(destPt.getX() * 4.0D, destPt.getY() * 4.0D);
/* 202 */     return pt;
/*     */   }
/*     */   
/*     */   public Point2D mapSourcePoint(Point2D sourcePt) {
/* 220 */     if (sourcePt == null)
/* 221 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 224 */     Point2D pt = (Point2D)sourcePt.clone();
/* 226 */     pt.setLocation(sourcePt.getX() / 4.0D, sourcePt.getY() / 4.0D);
/* 228 */     return pt;
/*     */   }
/*     */   
/*     */   protected Rectangle forwardMapRect(Rectangle sourceRect, int sourceIndex) {
/* 251 */     if (sourceRect == null)
/* 252 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 255 */     if (sourceIndex != 0)
/* 256 */       throw new IllegalArgumentException(JaiI18N.getString("Generic1")); 
/* 260 */     int x0 = sourceRect.x;
/* 261 */     int y0 = sourceRect.y;
/* 262 */     int dx0 = x0 / this.blockX;
/* 263 */     int dy0 = y0 / this.blockY;
/* 265 */     int x1 = sourceRect.x + sourceRect.width - 1;
/* 266 */     int y1 = sourceRect.y + sourceRect.height - 1;
/* 268 */     int dx1 = x1 / this.blockX;
/* 269 */     int dy1 = y1 / this.blockY;
/* 272 */     return new Rectangle(dx0, dy0, dx1 - dx0 + 1, dy1 - dy0 + 1);
/*     */   }
/*     */   
/*     */   protected Rectangle backwardMapRect(Rectangle destRect, int sourceIndex) {
/* 294 */     if (destRect == null)
/* 295 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 298 */     if (sourceIndex != 0)
/* 299 */       throw new IllegalArgumentException(JaiI18N.getString("Generic1")); 
/* 303 */     int sx0 = destRect.x * this.blockX;
/* 304 */     int sy0 = destRect.y * this.blockY;
/* 305 */     int sx1 = (destRect.x + destRect.width - 1) * this.blockX;
/* 306 */     int sy1 = (destRect.y + destRect.height - 1) * this.blockY;
/* 308 */     return new Rectangle(sx0, sy0, sx1 - sx0 + this.blockX, sy1 - sy0 + this.blockY);
/*     */   }
/*     */   
/*     */   protected void computeRect(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/* 323 */     Raster source = sources[0];
/* 325 */     switch (source.getSampleModel().getDataType()) {
/*     */       case 0:
/*     */       case 1:
/*     */       case 2:
/*     */       case 3:
/* 330 */         byteLoop4x4(source, dest, destRect);
/*     */         return;
/*     */     } 
/* 333 */     throw new RuntimeException(JaiI18N.getString("SubsampleBinaryToGrayOpImage0"));
/*     */   }
/*     */   
/*     */   private void byteLoop4x4(Raster source, WritableRaster dest, Rectangle destRect) {
/* 342 */     PixelAccessor pa = new PixelAccessor(source.getSampleModel(), null);
/* 343 */     PackedImageData pid = pa.getPackedPixels(source, source.getBounds(), false, false);
/* 346 */     if (pid.bitOffset % 4 != 0) {
/* 348 */       byteLoop(source, dest, destRect);
/*     */       return;
/*     */     } 
/* 352 */     byte[] sourceData = pid.data;
/* 353 */     int sourceDBOffset = pid.offset;
/* 354 */     int dx = destRect.x, dy = destRect.y;
/* 355 */     int dwi = destRect.width, dhi = destRect.height;
/* 356 */     int sourceTransX = pid.rect.x;
/* 357 */     int sourceTransY = pid.rect.y;
/* 358 */     int sourceDataBitOffset = pid.bitOffset;
/* 359 */     int sourceScanlineStride = pid.lineStride;
/* 361 */     PixelInterleavedSampleModel destSM = (PixelInterleavedSampleModel)dest.getSampleModel();
/* 363 */     DataBufferByte destDB = (DataBufferByte)dest.getDataBuffer();
/* 365 */     int destTransX = dest.getSampleModelTranslateX();
/* 366 */     int destTransY = dest.getSampleModelTranslateY();
/* 367 */     int destScanlineStride = destSM.getScanlineStride();
/* 369 */     byte[] destData = destDB.getData();
/* 370 */     int destDBOffset = destDB.getOffset();
/* 372 */     int[] sAreaBitsOn = new int[2];
/* 374 */     for (int j = 0; j < dhi; j++) {
/* 375 */       int y = dy + j << 2;
/* 376 */       int sourceYOffset = (y - sourceTransY) * sourceScanlineStride + sourceDBOffset;
/* 379 */       int destYOffset = (j + dy - destTransY) * destScanlineStride + destDBOffset;
/* 381 */       destYOffset += dx - destTransX;
/* 387 */       int sbitnumi = (dx << 2) - sourceTransX + sourceDataBitOffset;
/* 389 */       for (int i = 0; i < dwi; ) {
/* 390 */         int sbytenumi = sbitnumi >> 3;
/* 391 */         int sstartbiti = sbitnumi % 8;
/* 392 */         int byteindex = sourceYOffset + sbytenumi;
/* 393 */         sAreaBitsOn[1] = 0;
/* 393 */         sAreaBitsOn[0] = 0;
/* 394 */         for (int k = 0; k < 4; k++, byteindex += sourceScanlineStride) {
/* 395 */           int selement = 0xFF & sourceData[byteindex];
/* 396 */           sAreaBitsOn[1] = sAreaBitsOn[1] + this.lut[selement & 0xF];
/* 397 */           sAreaBitsOn[0] = sAreaBitsOn[0] + this.lut[selement >> 4];
/*     */         } 
/* 408 */         sstartbiti >>= 2;
/* 410 */         while (sstartbiti < 2 && i < dwi) {
/* 411 */           destData[destYOffset + i] = this.lutGray[sAreaBitsOn[sstartbiti]];
/* 412 */           sstartbiti++;
/* 413 */           i++;
/* 414 */           sbitnumi += this.blockX;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void byteLoop(Raster source, WritableRaster dest, Rectangle destRect) {
/* 425 */     PixelAccessor pa = new PixelAccessor(source.getSampleModel(), null);
/* 426 */     PackedImageData pid = pa.getPackedPixels(source, source.getBounds(), false, false);
/* 428 */     byte[] sourceData = pid.data;
/* 429 */     int sourceDBOffset = pid.offset;
/* 430 */     int dx = destRect.x, dy = destRect.y;
/* 431 */     int dwi = destRect.width, dhi = destRect.height;
/* 432 */     int sourceTransX = pid.rect.x;
/* 433 */     int sourceTransY = pid.rect.y;
/* 434 */     int sourceDataBitOffset = pid.bitOffset;
/* 435 */     int sourceScanlineStride = pid.lineStride;
/* 437 */     PixelInterleavedSampleModel destSM = (PixelInterleavedSampleModel)dest.getSampleModel();
/* 439 */     DataBufferByte destDB = (DataBufferByte)dest.getDataBuffer();
/* 441 */     int destTransX = dest.getSampleModelTranslateX();
/* 442 */     int destTransY = dest.getSampleModelTranslateY();
/* 443 */     int destScanlineStride = destSM.getScanlineStride();
/* 445 */     byte[] destData = destDB.getData();
/* 446 */     int destDBOffset = destDB.getOffset();
/* 448 */     int[] sbytenum = new int[dwi];
/* 449 */     int[] sstartbit = new int[dwi];
/* 450 */     int[] sAreaBitsOn = new int[dwi];
/* 451 */     for (int i = 0; i < dwi; i++) {
/* 452 */       int x = this.xValues[dx + i];
/* 453 */       int sbitnum = sourceDataBitOffset + x - sourceTransX;
/* 454 */       sbytenum[i] = sbitnum >> 3;
/* 455 */       sstartbit[i] = sbitnum % 8;
/*     */     } 
/* 458 */     for (int j = 0; j < dhi; j++) {
/* 460 */       for (int k = 0; k < dwi; k++)
/* 461 */         sAreaBitsOn[k] = 0; 
/* 464 */       for (int y = this.yValues[dy + j]; y < this.yValues[dy + j] + this.blockY; y++) {
/* 466 */         int sourceYOffset = (y - sourceTransY) * sourceScanlineStride + sourceDBOffset;
/* 470 */         for (int n = 0; n < dwi; n++) {
/* 471 */           int delement = 0;
/* 472 */           int sendbiti = sstartbit[n] + this.blockX - 1;
/* 473 */           int sendbytenumi = sbytenum[n] + (sendbiti >> 3);
/* 474 */           sendbiti %= 8;
/* 475 */           int selement = 0xFF & sourceData[sourceYOffset + sbytenum[n]];
/* 477 */           int swingBits = 24 + sstartbit[n];
/* 478 */           if (sbytenum[n] == sendbytenumi) {
/* 480 */             selement <<= swingBits;
/* 481 */             selement >>>= 31 - sendbiti + sstartbit[n];
/* 482 */             delement += this.lut[selement];
/*     */           } else {
/* 484 */             selement <<= swingBits;
/* 485 */             selement >>>= swingBits;
/* 488 */             delement += this.lut[selement];
/* 489 */             for (int b = sbytenum[n] + 1; b < sendbytenumi; b++) {
/* 490 */               selement = 0xFF & sourceData[sourceYOffset + b];
/* 491 */               delement += this.lut[selement];
/*     */             } 
/* 493 */             selement = 0xFF & sourceData[sourceYOffset + sendbytenumi];
/* 494 */             selement >>>= 7 - sendbiti;
/* 495 */             delement += this.lut[selement];
/*     */           } 
/* 497 */           sAreaBitsOn[n] = sAreaBitsOn[n] + delement;
/*     */         } 
/*     */       } 
/* 500 */       int destYOffset = (j + dy - destTransY) * destScanlineStride + destDBOffset;
/* 503 */       destYOffset += dx - destTransX;
/* 507 */       for (int m = 0; m < dwi; m++)
/* 508 */         destData[destYOffset + m] = this.lutGray[sAreaBitsOn[m]]; 
/*     */     } 
/*     */   }
/*     */   
/*     */   private final void buildLookupTables() {
/* 520 */     this.lut = new int[16];
/* 521 */     this.lut[0] = 0;
/* 521 */     this.lut[1] = 1;
/* 521 */     this.lut[2] = 1;
/* 521 */     this.lut[3] = 2;
/* 522 */     this.lut[4] = 1;
/* 522 */     this.lut[5] = 2;
/* 522 */     this.lut[6] = 2;
/* 522 */     this.lut[7] = 3;
/*     */     int i;
/* 523 */     for (i = 8; i < 16; ) {
/* 523 */       this.lut[i] = 1 + this.lut[i - 8];
/* 523 */       i++;
/*     */     } 
/* 527 */     if (this.lutGray != null)
/*     */       return; 
/* 528 */     this.lutGray = new byte[this.blockX * this.blockY + 1];
/* 529 */     for (i = 0; i < this.lutGray.length; i++) {
/* 530 */       int tmp = Math.round(255.0F * i / (this.lutGray.length - 1.0F));
/* 531 */       this.lutGray[i] = (tmp > 255) ? -1 : (byte)tmp;
/*     */     } 
/* 535 */     if (SubsampleBinaryToGrayOpImage.isMinWhite(getSourceImage(0).getColorModel()))
/* 537 */       for (i = 0; i < this.lutGray.length; i++)
/* 538 */         this.lutGray[i] = (byte)(255 - (0xFF & this.lutGray[i]));  
/*     */   }
/*     */   
/*     */   private void computeXYValues(int dstWidth, int dstHeight) {
/* 543 */     if (this.xValues == null || this.yValues == null) {
/* 544 */       this.xValues = new int[dstWidth];
/* 545 */       this.yValues = new int[dstHeight];
/*     */     } 
/*     */     int i;
/* 548 */     for (i = 0; i < dstWidth; i++)
/* 550 */       this.xValues[i] = i << 2; 
/* 553 */     for (i = 0; i < dstHeight; i++)
/* 555 */       this.yValues[i] = i << 2; 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\SubsampleBinaryToGray4x4OpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */