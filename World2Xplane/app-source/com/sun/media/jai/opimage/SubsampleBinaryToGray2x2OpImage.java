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
/*     */ class SubsampleBinaryToGray2x2OpImage extends GeometricOpImage {
/*     */   private int blockX;
/*     */   
/*     */   private int blockY;
/*     */   
/*     */   private int dWidth;
/*     */   
/*     */   private int dHeight;
/*     */   
/*     */   private int[] lut4_45;
/*     */   
/*     */   private int[] lut4_67;
/*     */   
/*     */   private byte[] lutGray;
/*     */   
/*     */   public SubsampleBinaryToGray2x2OpImage(RenderedImage source, ImageLayout layout, Map config) {
/* 129 */     super(vectorize(source), SubsampleBinaryToGrayOpImage.layoutHelper(source, 0.5F, 0.5F, layout, config), config, true, null, null, null);
/* 141 */     this.blockX = 2;
/* 142 */     this.blockY = 2;
/* 143 */     int srcWidth = source.getWidth();
/* 144 */     int srcHeight = source.getHeight();
/* 146 */     this.dWidth = srcWidth / this.blockX;
/* 147 */     this.dHeight = srcHeight / this.blockY;
/* 149 */     if (this.extender == null) {
/* 150 */       this.computableBounds = new Rectangle(0, 0, this.dWidth, this.dHeight);
/*     */     } else {
/* 153 */       this.computableBounds = getBounds();
/*     */     } 
/* 158 */     buildLookupTables();
/*     */   }
/*     */   
/*     */   public Point2D mapDestPoint(Point2D destPt) {
/* 176 */     if (destPt == null)
/* 177 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 180 */     Point2D pt = (Point2D)destPt.clone();
/* 182 */     pt.setLocation(destPt.getX() * 2.0D, destPt.getY() * 2.0D);
/* 184 */     return pt;
/*     */   }
/*     */   
/*     */   public Point2D mapSourcePoint(Point2D sourcePt) {
/* 202 */     if (sourcePt == null)
/* 203 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 206 */     Point2D pt = (Point2D)sourcePt.clone();
/* 208 */     pt.setLocation(sourcePt.getX() / 2.0D, sourcePt.getY() / 2.0D);
/* 210 */     return pt;
/*     */   }
/*     */   
/*     */   protected Rectangle forwardMapRect(Rectangle sourceRect, int sourceIndex) {
/* 233 */     if (sourceRect == null)
/* 234 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 237 */     if (sourceIndex != 0)
/* 238 */       throw new IllegalArgumentException(JaiI18N.getString("Generic1")); 
/* 243 */     int dx0 = sourceRect.x / this.blockX;
/* 244 */     int dy0 = sourceRect.y / this.blockY;
/* 245 */     int dx1 = (sourceRect.x + sourceRect.width - 1) / this.blockX;
/* 246 */     int dy1 = (sourceRect.y + sourceRect.height - 1) / this.blockY;
/* 248 */     return new Rectangle(dx0, dy0, dx1 - dx0 + 1, dy1 - dy0 + 1);
/*     */   }
/*     */   
/*     */   protected Rectangle backwardMapRect(Rectangle destRect, int sourceIndex) {
/* 270 */     if (destRect == null)
/* 271 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 274 */     if (sourceIndex != 0)
/* 275 */       throw new IllegalArgumentException(JaiI18N.getString("Generic1")); 
/* 279 */     int sx0 = destRect.x * this.blockX;
/* 280 */     int sy0 = destRect.y * this.blockY;
/* 281 */     int sx1 = (destRect.x + destRect.width - 1) * this.blockX;
/* 282 */     int sy1 = (destRect.y + destRect.height - 1) * this.blockY;
/* 284 */     return new Rectangle(sx0, sy0, sx1 - sx0 + this.blockX, sy1 - sy0 + this.blockY);
/*     */   }
/*     */   
/*     */   protected void computeRect(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/* 300 */     Raster source = sources[0];
/* 302 */     switch (source.getSampleModel().getDataType()) {
/*     */       case 0:
/*     */       case 1:
/*     */       case 2:
/*     */       case 3:
/* 307 */         byteLoop2x2(source, dest, destRect);
/*     */         return;
/*     */     } 
/* 310 */     throw new RuntimeException(JaiI18N.getString("SubsampleBinaryToGrayOpImage0"));
/*     */   }
/*     */   
/*     */   private void byteLoop2x2(Raster source, WritableRaster dest, Rectangle destRect) {
/* 316 */     PixelAccessor pa = new PixelAccessor(source.getSampleModel(), null);
/* 317 */     PackedImageData pid = pa.getPackedPixels(source, source.getBounds(), false, false);
/* 319 */     byte[] sourceData = pid.data;
/* 320 */     int sourceDBOffset = pid.offset;
/* 321 */     int dx = destRect.x, dy = destRect.y;
/* 322 */     int dwi = destRect.width, dhi = destRect.height;
/* 323 */     int sourceTransX = pid.rect.x;
/* 324 */     int sourceTransY = pid.rect.y;
/* 325 */     int sourceDataBitOffset = pid.bitOffset;
/* 326 */     int sourceScanlineStride = pid.lineStride;
/* 328 */     PixelInterleavedSampleModel destSM = (PixelInterleavedSampleModel)dest.getSampleModel();
/* 330 */     DataBufferByte destDB = (DataBufferByte)dest.getDataBuffer();
/* 332 */     int destTransX = dest.getSampleModelTranslateX();
/* 333 */     int destTransY = dest.getSampleModelTranslateY();
/* 334 */     int destScanlineStride = destSM.getScanlineStride();
/* 336 */     byte[] destData = destDB.getData();
/* 337 */     int destDBOffset = destDB.getOffset();
/* 339 */     int[] sAreaBitsOn = new int[4];
/* 341 */     if ((sourceDataBitOffset & 0x1) == 0) {
/* 343 */       for (int j = 0; j < dhi; j++) {
/* 344 */         int y = dy + j << 1;
/* 345 */         int sourceYOffset = (y - sourceTransY) * sourceScanlineStride + sourceDBOffset;
/* 347 */         int sourceYOffset2 = sourceYOffset + sourceScanlineStride;
/* 349 */         int destYOffset = (j + dy - destTransY) * destScanlineStride + destDBOffset;
/* 351 */         destYOffset += dx - destTransX;
/* 357 */         int sbitnumi = (dx << 1) - sourceTransX + sourceDataBitOffset;
/* 358 */         for (int i = 0; i < dwi; ) {
/* 359 */           int sbytenumi = sbitnumi >> 3;
/* 361 */           int sstartbiti = sbitnumi % 8;
/* 362 */           int selement = 0xFF & sourceData[sourceYOffset + sbytenumi];
/* 364 */           sAreaBitsOn[2] = this.lut4_45[selement & 0xF];
/* 365 */           sAreaBitsOn[3] = this.lut4_67[selement & 0xF];
/* 366 */           selement >>= 4;
/* 367 */           sAreaBitsOn[0] = this.lut4_45[selement];
/* 368 */           sAreaBitsOn[1] = this.lut4_67[selement];
/* 371 */           selement = 0xFF & sourceData[sourceYOffset2 + sbytenumi];
/* 372 */           sAreaBitsOn[2] = sAreaBitsOn[2] + this.lut4_45[selement & 0xF];
/* 373 */           sAreaBitsOn[3] = sAreaBitsOn[3] + this.lut4_67[selement & 0xF];
/* 374 */           selement >>= 4;
/* 375 */           sAreaBitsOn[0] = sAreaBitsOn[0] + this.lut4_45[selement];
/* 376 */           sAreaBitsOn[1] = sAreaBitsOn[1] + this.lut4_67[selement];
/* 385 */           sstartbiti >>= 1;
/* 387 */           while (sstartbiti < 4 && i < dwi) {
/* 388 */             destData[destYOffset + i] = this.lutGray[sAreaBitsOn[sstartbiti]];
/* 389 */             sstartbiti++;
/* 390 */             i++;
/* 391 */             sbitnumi += this.blockX;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } else {
/* 397 */       for (int j = 0; j < dhi; j++) {
/* 398 */         int y = dy + j << 1;
/* 399 */         int sourceYOffset = (y - sourceTransY) * sourceScanlineStride + sourceDBOffset;
/* 401 */         int sourceYOffset2 = sourceYOffset + sourceScanlineStride;
/* 403 */         int destYOffset = (j + dy - destTransY) * destScanlineStride + destDBOffset;
/* 405 */         destYOffset += dx - destTransX;
/* 411 */         int sbitnumi = (dx << 1) - sourceTransX + sourceDataBitOffset;
/* 413 */         for (int i = 0; i < dwi; ) {
/* 414 */           int sbytenumi = sbitnumi >> 3;
/* 416 */           int sstartbiti = sbitnumi % 8;
/* 419 */           int selement = 0xFF & sourceData[sourceYOffset + sbytenumi] << 1;
/* 421 */           sAreaBitsOn[2] = this.lut4_45[selement & 0xF];
/* 422 */           sAreaBitsOn[3] = this.lut4_67[selement & 0xF];
/* 423 */           selement >>= 4;
/* 424 */           sAreaBitsOn[0] = this.lut4_45[selement];
/* 425 */           sAreaBitsOn[1] = this.lut4_67[selement];
/* 429 */           selement = 0xFF & sourceData[sourceYOffset2 + sbytenumi] << 1;
/* 430 */           sAreaBitsOn[2] = sAreaBitsOn[2] + this.lut4_45[selement & 0xF];
/* 431 */           sAreaBitsOn[3] = sAreaBitsOn[3] + this.lut4_67[selement & 0xF];
/* 432 */           selement >>= 4;
/* 433 */           sAreaBitsOn[0] = sAreaBitsOn[0] + this.lut4_45[selement];
/* 434 */           sAreaBitsOn[1] = sAreaBitsOn[1] + this.lut4_67[selement];
/* 440 */           sbytenumi++;
/* 441 */           if (sbytenumi < sourceData.length - sourceYOffset2) {
/* 442 */             sAreaBitsOn[3] = sAreaBitsOn[3] + ((sourceData[sourceYOffset + sbytenumi] < 0) ? 1 : 0);
/* 443 */             sAreaBitsOn[3] = sAreaBitsOn[3] + ((sourceData[sourceYOffset2 + sbytenumi] < 0) ? 1 : 0);
/*     */           } 
/* 450 */           sstartbiti >>= 1;
/* 452 */           while (sstartbiti < 4 && i < dwi) {
/* 453 */             destData[destYOffset + i] = this.lutGray[sAreaBitsOn[sstartbiti]];
/* 454 */             sstartbiti++;
/* 455 */             i++;
/* 456 */             sbitnumi += this.blockX;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private final void buildLookupTables() {
/* 473 */     this.lut4_45 = new int[16];
/* 474 */     this.lut4_67 = new int[16];
/* 476 */     this.lut4_67[0] = 0;
/* 476 */     this.lut4_67[1] = 1;
/* 476 */     this.lut4_67[2] = 1;
/* 476 */     this.lut4_67[3] = 2;
/*     */     int i;
/* 477 */     for (i = 4; i < 16; ) {
/* 477 */       this.lut4_67[i] = this.lut4_67[i & 0x3];
/* 477 */       i++;
/*     */     } 
/* 479 */     for (i = 0; i < 16; ) {
/* 479 */       this.lut4_45[i] = this.lut4_67[i >> 2];
/* 479 */       i++;
/*     */     } 
/* 482 */     if (this.lutGray != null)
/*     */       return; 
/* 483 */     this.lutGray = new byte[this.blockX * this.blockY + 1];
/* 484 */     for (i = 0; i < this.lutGray.length; i++) {
/* 485 */       int tmp = Math.round(255.0F * i / (this.lutGray.length - 1.0F));
/* 486 */       this.lutGray[i] = (tmp > 255) ? -1 : (byte)tmp;
/*     */     } 
/* 490 */     if (SubsampleBinaryToGrayOpImage.isMinWhite(getSourceImage(0).getColorModel()))
/* 492 */       for (i = 0; i < this.lutGray.length; i++)
/* 493 */         this.lutGray[i] = (byte)(255 - (0xFF & this.lutGray[i]));  
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\SubsampleBinaryToGray2x2OpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */