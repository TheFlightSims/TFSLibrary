/*     */ package com.sun.media.jai.opimage;
/*     */ 
/*     */ import com.sun.media.jai.util.Rational;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.image.DataBufferByte;
/*     */ import java.awt.image.DataBufferInt;
/*     */ import java.awt.image.DataBufferUShort;
/*     */ import java.awt.image.MultiPixelPackedSampleModel;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.util.Map;
/*     */ import javax.media.jai.BorderExtender;
/*     */ import javax.media.jai.ImageLayout;
/*     */ import javax.media.jai.Interpolation;
/*     */ import javax.media.jai.ScaleOpImage;
/*     */ 
/*     */ final class ScaleNearestBinaryOpImage extends ScaleOpImage {
/*     */   long invScaleXInt;
/*     */   
/*     */   long invScaleXFrac;
/*     */   
/*     */   long invScaleYInt;
/*     */   
/*     */   long invScaleYFrac;
/*     */   
/*     */   public ScaleNearestBinaryOpImage(RenderedImage source, BorderExtender extender, Map config, ImageLayout layout, float xScale, float yScale, float xTrans, float yTrans, Interpolation interp) {
/*  68 */     super(source, layout, config, true, extender, interp, xScale, yScale, xTrans, yTrans);
/*  80 */     if (layout != null) {
/*  81 */       this.colorModel = layout.getColorModel(source);
/*     */     } else {
/*  83 */       this.colorModel = source.getColorModel();
/*     */     } 
/*  85 */     this.sampleModel = source.getSampleModel().createCompatibleSampleModel(this.tileWidth, this.tileHeight);
/*  89 */     if (this.invScaleXRational.num > this.invScaleXRational.denom) {
/*  90 */       this.invScaleXInt = this.invScaleXRational.num / this.invScaleXRational.denom;
/*  91 */       this.invScaleXFrac = this.invScaleXRational.num % this.invScaleXRational.denom;
/*     */     } else {
/*  93 */       this.invScaleXInt = 0L;
/*  94 */       this.invScaleXFrac = this.invScaleXRational.num;
/*     */     } 
/*  97 */     if (this.invScaleYRational.num > this.invScaleYRational.denom) {
/*  98 */       this.invScaleYInt = this.invScaleYRational.num / this.invScaleYRational.denom;
/*  99 */       this.invScaleYFrac = this.invScaleYRational.num % this.invScaleYRational.denom;
/*     */     } else {
/* 101 */       this.invScaleYInt = 0L;
/* 102 */       this.invScaleYFrac = this.invScaleYRational.num;
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void computeRect(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/* 118 */     Raster source = sources[0];
/* 121 */     Rectangle srcRect = source.getBounds();
/* 123 */     int srcRectX = srcRect.x;
/* 124 */     int srcRectY = srcRect.y;
/* 127 */     int dx = destRect.x;
/* 128 */     int dy = destRect.y;
/* 129 */     int dwidth = destRect.width;
/* 130 */     int dheight = destRect.height;
/* 133 */     int[] xvalues = new int[dwidth];
/* 135 */     long sxNum = dx, sxDenom = 1L;
/* 138 */     sxNum = sxNum * this.transXRationalDenom - this.transXRationalNum * sxDenom;
/* 139 */     sxDenom *= this.transXRationalDenom;
/* 142 */     sxNum = 2L * sxNum + sxDenom;
/* 143 */     sxDenom *= 2L;
/* 146 */     sxNum *= this.invScaleXRationalNum;
/* 147 */     sxDenom *= this.invScaleXRationalDenom;
/* 151 */     int srcXInt = Rational.floor(sxNum, sxDenom);
/* 152 */     long srcXFrac = sxNum % sxDenom;
/* 153 */     if (srcXInt < 0)
/* 154 */       srcXFrac = sxDenom + srcXFrac; 
/* 159 */     long commonXDenom = sxDenom * this.invScaleXRationalDenom;
/* 160 */     srcXFrac *= this.invScaleXRationalDenom;
/* 161 */     long newInvScaleXFrac = this.invScaleXFrac * sxDenom;
/* 163 */     for (int i = 0; i < dwidth; i++) {
/* 165 */       xvalues[i] = srcXInt;
/* 171 */       srcXInt = (int)(srcXInt + this.invScaleXInt);
/* 175 */       srcXFrac += newInvScaleXFrac;
/* 180 */       if (srcXFrac >= commonXDenom) {
/* 181 */         srcXInt++;
/* 182 */         srcXFrac -= commonXDenom;
/*     */       } 
/*     */     } 
/* 187 */     int[] yvalues = new int[dheight];
/* 189 */     long syNum = dy, syDenom = 1L;
/* 192 */     syNum = syNum * this.transYRationalDenom - this.transYRationalNum * syDenom;
/* 193 */     syDenom *= this.transYRationalDenom;
/* 196 */     syNum = 2L * syNum + syDenom;
/* 197 */     syDenom *= 2L;
/* 200 */     syNum *= this.invScaleYRationalNum;
/* 201 */     syDenom *= this.invScaleYRationalDenom;
/* 204 */     int srcYInt = Rational.floor(syNum, syDenom);
/* 205 */     long srcYFrac = syNum % syDenom;
/* 206 */     if (srcYInt < 0)
/* 207 */       srcYFrac = syDenom + srcYFrac; 
/* 212 */     long commonYDenom = syDenom * this.invScaleYRationalDenom;
/* 213 */     srcYFrac *= this.invScaleYRationalDenom;
/* 214 */     long newInvScaleYFrac = this.invScaleYFrac * syDenom;
/* 216 */     for (int j = 0; j < dheight; j++) {
/* 218 */       yvalues[j] = srcYInt;
/* 224 */       srcYInt = (int)(srcYInt + this.invScaleYInt);
/* 228 */       srcYFrac += newInvScaleYFrac;
/* 233 */       if (srcYFrac >= commonYDenom) {
/* 234 */         srcYInt++;
/* 235 */         srcYFrac -= commonYDenom;
/*     */       } 
/*     */     } 
/* 239 */     switch (source.getSampleModel().getDataType()) {
/*     */       case 0:
/* 241 */         byteLoop(source, dest, destRect, xvalues, yvalues);
/*     */         return;
/*     */       case 1:
/*     */       case 2:
/* 246 */         shortLoop(source, dest, destRect, xvalues, yvalues);
/*     */         return;
/*     */       case 3:
/* 250 */         intLoop(source, dest, destRect, xvalues, yvalues);
/*     */         return;
/*     */     } 
/* 254 */     throw new RuntimeException(JaiI18N.getString("OrderedDitherOpImage0"));
/*     */   }
/*     */   
/*     */   private void byteLoop(Raster source, WritableRaster dest, Rectangle destRect, int[] xvalues, int[] yvalues) {
/* 262 */     int dx = destRect.x;
/* 263 */     int dy = destRect.y;
/* 264 */     int dwidth = destRect.width;
/* 265 */     int dheight = destRect.height;
/* 267 */     MultiPixelPackedSampleModel sourceSM = (MultiPixelPackedSampleModel)source.getSampleModel();
/* 269 */     DataBufferByte sourceDB = (DataBufferByte)source.getDataBuffer();
/* 271 */     int sourceTransX = source.getSampleModelTranslateX();
/* 272 */     int sourceTransY = source.getSampleModelTranslateY();
/* 273 */     int sourceDataBitOffset = sourceSM.getDataBitOffset();
/* 274 */     int sourceScanlineStride = sourceSM.getScanlineStride();
/* 276 */     MultiPixelPackedSampleModel destSM = (MultiPixelPackedSampleModel)dest.getSampleModel();
/* 278 */     DataBufferByte destDB = (DataBufferByte)dest.getDataBuffer();
/* 280 */     int destMinX = dest.getMinX();
/* 281 */     int destMinY = dest.getMinY();
/* 282 */     int destTransX = dest.getSampleModelTranslateX();
/* 283 */     int destTransY = dest.getSampleModelTranslateY();
/* 284 */     int destDataBitOffset = destSM.getDataBitOffset();
/* 285 */     int destScanlineStride = destSM.getScanlineStride();
/* 287 */     byte[] sourceData = sourceDB.getData();
/* 288 */     int sourceDBOffset = sourceDB.getOffset();
/* 290 */     byte[] destData = destDB.getData();
/* 291 */     int destDBOffset = destDB.getOffset();
/* 293 */     int[] sbytenum = new int[dwidth];
/* 294 */     int[] sshift = new int[dwidth];
/* 296 */     for (int i = 0; i < dwidth; i++) {
/* 297 */       int x = xvalues[i];
/* 298 */       int sbitnum = sourceDataBitOffset + x - sourceTransX;
/* 299 */       sbytenum[i] = sbitnum >> 3;
/* 300 */       sshift[i] = 7 - (sbitnum & 0x7);
/*     */     } 
/* 303 */     for (int j = 0; j < dheight; j++) {
/* 304 */       int y = yvalues[j];
/* 306 */       int sourceYOffset = (y - sourceTransY) * sourceScanlineStride + sourceDBOffset;
/* 308 */       int destYOffset = (j + dy - destTransY) * destScanlineStride + destDBOffset;
/* 311 */       int dbitnum = destDataBitOffset + dx - destTransX;
/* 315 */       int k = 0;
/* 316 */       while (k < dwidth && (dbitnum & 0x7) != 0) {
/* 317 */         int selement = sourceData[sourceYOffset + sbytenum[k]];
/* 318 */         int val = selement >> sshift[k] & 0x1;
/* 319 */         int m = destYOffset + (dbitnum >> 3);
/* 320 */         int dshift = 7 - (dbitnum & 0x7);
/* 321 */         int delement = destData[m];
/* 322 */         delement |= val << dshift;
/* 323 */         destData[m] = (byte)delement;
/* 324 */         dbitnum++;
/* 325 */         k++;
/*     */       } 
/* 328 */       int dindex = destYOffset + (dbitnum >> 3);
/* 329 */       int nbytes = dwidth - k + 1 >> 3;
/* 331 */       if (nbytes > 0 && j > 0 && y == yvalues[j - 1]) {
/* 333 */         System.arraycopy(destData, dindex - destScanlineStride, destData, dindex, nbytes);
/* 336 */         k += nbytes * 8;
/* 337 */         dbitnum += nbytes * 8;
/*     */       } else {
/* 339 */         while (k < dwidth - 7) {
/* 340 */           int selement = sourceData[sourceYOffset + sbytenum[k]];
/* 341 */           int val = selement >> sshift[k] & 0x1;
/* 343 */           int delement = val << 7;
/* 344 */           k++;
/* 346 */           selement = sourceData[sourceYOffset + sbytenum[k]];
/* 347 */           val = selement >> sshift[k] & 0x1;
/* 349 */           delement |= val << 6;
/* 350 */           k++;
/* 352 */           selement = sourceData[sourceYOffset + sbytenum[k]];
/* 353 */           val = selement >> sshift[k] & 0x1;
/* 355 */           delement |= val << 5;
/* 356 */           k++;
/* 358 */           selement = sourceData[sourceYOffset + sbytenum[k]];
/* 359 */           val = selement >> sshift[k] & 0x1;
/* 361 */           delement |= val << 4;
/* 362 */           k++;
/* 364 */           selement = sourceData[sourceYOffset + sbytenum[k]];
/* 365 */           val = selement >> sshift[k] & 0x1;
/* 367 */           delement |= val << 3;
/* 368 */           k++;
/* 370 */           selement = sourceData[sourceYOffset + sbytenum[k]];
/* 371 */           val = selement >> sshift[k] & 0x1;
/* 373 */           delement |= val << 2;
/* 374 */           k++;
/* 376 */           selement = sourceData[sourceYOffset + sbytenum[k]];
/* 377 */           val = selement >> sshift[k] & 0x1;
/* 379 */           delement |= val << 1;
/* 380 */           k++;
/* 382 */           selement = sourceData[sourceYOffset + sbytenum[k]];
/* 383 */           val = selement >> sshift[k] & 0x1;
/* 385 */           delement |= val;
/* 386 */           k++;
/* 388 */           destData[dindex++] = (byte)delement;
/* 389 */           dbitnum += 8;
/*     */         } 
/*     */       } 
/* 393 */       if (k < dwidth) {
/* 394 */         dindex = destYOffset + (dbitnum >> 3);
/* 395 */         int delement = destData[dindex];
/* 396 */         while (k < dwidth) {
/* 397 */           int selement = sourceData[sourceYOffset + sbytenum[k]];
/* 398 */           int val = selement >> sshift[k] & 0x1;
/* 400 */           int dshift = 7 - (dbitnum & 0x7);
/* 401 */           delement |= val << dshift;
/* 402 */           dbitnum++;
/* 403 */           k++;
/*     */         } 
/* 405 */         destData[dindex] = (byte)delement;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void shortLoop(Raster source, WritableRaster dest, Rectangle destRect, int[] xvalues, int[] yvalues) {
/* 413 */     int dx = destRect.x;
/* 414 */     int dy = destRect.y;
/* 415 */     int dwidth = destRect.width;
/* 416 */     int dheight = destRect.height;
/* 418 */     MultiPixelPackedSampleModel sourceSM = (MultiPixelPackedSampleModel)source.getSampleModel();
/* 420 */     int sourceTransX = source.getSampleModelTranslateX();
/* 421 */     int sourceTransY = source.getSampleModelTranslateY();
/* 422 */     int sourceDataBitOffset = sourceSM.getDataBitOffset();
/* 423 */     int sourceScanlineStride = sourceSM.getScanlineStride();
/* 425 */     MultiPixelPackedSampleModel destSM = (MultiPixelPackedSampleModel)dest.getSampleModel();
/* 427 */     int destMinX = dest.getMinX();
/* 428 */     int destMinY = dest.getMinY();
/* 429 */     int destTransX = dest.getSampleModelTranslateX();
/* 430 */     int destTransY = dest.getSampleModelTranslateY();
/* 431 */     int destDataBitOffset = destSM.getDataBitOffset();
/* 432 */     int destScanlineStride = destSM.getScanlineStride();
/* 434 */     DataBufferUShort sourceDB = (DataBufferUShort)source.getDataBuffer();
/* 435 */     short[] sourceData = sourceDB.getData();
/* 436 */     int sourceDBOffset = sourceDB.getOffset();
/* 438 */     DataBufferUShort destDB = (DataBufferUShort)dest.getDataBuffer();
/* 439 */     short[] destData = destDB.getData();
/* 440 */     int destDBOffset = destDB.getOffset();
/* 442 */     int[] sshortnum = new int[dwidth];
/* 443 */     int[] sshift = new int[dwidth];
/* 445 */     for (int i = 0; i < dwidth; i++) {
/* 446 */       int x = xvalues[i];
/* 447 */       int sbitnum = sourceDataBitOffset + x - sourceTransX;
/* 448 */       sshortnum[i] = sbitnum >> 4;
/* 449 */       sshift[i] = 15 - (sbitnum & 0xF);
/*     */     } 
/* 452 */     for (int j = 0; j < dheight; j++) {
/* 453 */       int y = yvalues[j];
/* 455 */       int sourceYOffset = (y - sourceTransY) * sourceScanlineStride + sourceDBOffset;
/* 457 */       int destYOffset = (j + dy - destTransY) * destScanlineStride + destDBOffset;
/* 460 */       int dbitnum = destDataBitOffset + dx - destTransX;
/* 464 */       int k = 0;
/* 465 */       while (k < dwidth && (dbitnum & 0xF) != 0) {
/* 466 */         int selement = sourceData[sourceYOffset + sshortnum[k]];
/* 467 */         int val = selement >> sshift[k] & 0x1;
/* 469 */         int m = destYOffset + (dbitnum >> 4);
/* 470 */         int dshift = 15 - (dbitnum & 0xF);
/* 471 */         int delement = destData[m];
/* 472 */         delement |= val << dshift;
/* 473 */         destData[m] = (short)delement;
/* 474 */         dbitnum++;
/* 475 */         k++;
/*     */       } 
/* 478 */       int dindex = destYOffset + (dbitnum >> 4);
/* 480 */       int nshorts = dwidth - k >> 4;
/* 482 */       if (nshorts > 0 && j > 0 && y == yvalues[j - 1]) {
/* 484 */         int offset = destYOffset + (dbitnum >> 4);
/* 485 */         System.arraycopy(destData, offset - destScanlineStride, destData, offset, nshorts);
/* 488 */         k += nshorts >> 4;
/* 489 */         dbitnum += nshorts >> 4;
/*     */       } else {
/* 491 */         while (k < dwidth - 15) {
/* 492 */           int delement = 0;
/* 493 */           for (int b = 15; b >= 0; b--) {
/* 494 */             int selement = sourceData[sourceYOffset + sshortnum[k]];
/* 495 */             int val = selement >> sshift[k] & 0x1;
/* 496 */             delement |= val << b;
/* 497 */             k++;
/*     */           } 
/* 500 */           destData[dindex++] = (short)delement;
/* 501 */           dbitnum += 16;
/*     */         } 
/*     */       } 
/* 505 */       if (k < dwidth) {
/* 506 */         dindex = destYOffset + (dbitnum >> 4);
/* 507 */         int delement = destData[dindex];
/* 508 */         while (k < dwidth) {
/* 509 */           int selement = sourceData[sourceYOffset + sshortnum[k]];
/* 510 */           int val = selement >> sshift[k] & 0x1;
/* 512 */           int dshift = 15 - (dbitnum & 0xF);
/* 513 */           delement |= val << dshift;
/* 514 */           dbitnum++;
/* 515 */           k++;
/*     */         } 
/* 517 */         destData[dindex] = (short)delement;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void intLoop(Raster source, WritableRaster dest, Rectangle destRect, int[] xvalues, int[] yvalues) {
/* 525 */     int dx = destRect.x;
/* 526 */     int dy = destRect.y;
/* 527 */     int dwidth = destRect.width;
/* 528 */     int dheight = destRect.height;
/* 530 */     MultiPixelPackedSampleModel sourceSM = (MultiPixelPackedSampleModel)source.getSampleModel();
/* 532 */     DataBufferInt sourceDB = (DataBufferInt)source.getDataBuffer();
/* 534 */     int sourceTransX = source.getSampleModelTranslateX();
/* 535 */     int sourceTransY = source.getSampleModelTranslateY();
/* 536 */     int sourceDataBitOffset = sourceSM.getDataBitOffset();
/* 537 */     int sourceScanlineStride = sourceSM.getScanlineStride();
/* 539 */     MultiPixelPackedSampleModel destSM = (MultiPixelPackedSampleModel)dest.getSampleModel();
/* 541 */     DataBufferInt destDB = (DataBufferInt)dest.getDataBuffer();
/* 543 */     int destMinX = dest.getMinX();
/* 544 */     int destMinY = dest.getMinY();
/* 545 */     int destTransX = dest.getSampleModelTranslateX();
/* 546 */     int destTransY = dest.getSampleModelTranslateY();
/* 547 */     int destDataBitOffset = destSM.getDataBitOffset();
/* 548 */     int destScanlineStride = destSM.getScanlineStride();
/* 550 */     int[] sourceData = sourceDB.getData();
/* 551 */     int sourceDBOffset = sourceDB.getOffset();
/* 553 */     int[] destData = destDB.getData();
/* 554 */     int destDBOffset = destDB.getOffset();
/* 556 */     int[] sintnum = new int[dwidth];
/* 557 */     int[] sshift = new int[dwidth];
/* 559 */     for (int i = 0; i < dwidth; i++) {
/* 560 */       int x = xvalues[i];
/* 561 */       int sbitnum = sourceDataBitOffset + x - sourceTransX;
/* 562 */       sintnum[i] = sbitnum >> 5;
/* 563 */       sshift[i] = 31 - (sbitnum & 0x1F);
/*     */     } 
/* 566 */     for (int j = 0; j < dheight; j++) {
/* 567 */       int y = yvalues[j];
/* 569 */       int sourceYOffset = (y - sourceTransY) * sourceScanlineStride + sourceDBOffset;
/* 571 */       int destYOffset = (j + dy - destTransY) * destScanlineStride + destDBOffset;
/* 574 */       int dbitnum = destDataBitOffset + dx - destTransX;
/* 579 */       int k = 0;
/* 580 */       while (k < dwidth && (dbitnum & 0x1F) != 0) {
/* 581 */         int selement = sourceData[sourceYOffset + sintnum[k]];
/* 582 */         int val = selement >> sshift[k] & 0x1;
/* 584 */         int m = destYOffset + (dbitnum >> 5);
/* 585 */         int dshift = 31 - (dbitnum & 0x1F);
/* 586 */         int delement = destData[m];
/* 587 */         delement |= val << dshift;
/* 588 */         destData[m] = delement;
/* 589 */         dbitnum++;
/* 590 */         k++;
/*     */       } 
/* 593 */       int dindex = destYOffset + (dbitnum >> 5);
/* 594 */       int nints = dwidth - k >> 5;
/* 596 */       if (nints > 0 && j > 0 && y == yvalues[j - 1]) {
/* 598 */         int offset = destYOffset + (dbitnum >> 5);
/* 599 */         System.arraycopy(destData, offset - destScanlineStride, destData, offset, nints);
/* 602 */         k += nints >> 5;
/* 603 */         dbitnum += nints >> 5;
/*     */       } else {
/* 605 */         while (k < dwidth - 31) {
/* 606 */           int delement = 0;
/* 607 */           for (int b = 31; b >= 0; b--) {
/* 608 */             int selement = sourceData[sourceYOffset + sintnum[k]];
/* 609 */             int val = selement >> sshift[k] & 0x1;
/* 610 */             delement |= val << b;
/* 611 */             k++;
/*     */           } 
/* 614 */           destData[dindex++] = delement;
/* 615 */           dbitnum += 32;
/*     */         } 
/*     */       } 
/* 619 */       if (k < dwidth) {
/* 620 */         dindex = destYOffset + (dbitnum >> 5);
/* 621 */         int delement = destData[dindex];
/* 622 */         while (k < dwidth) {
/* 623 */           int selement = sourceData[sourceYOffset + sintnum[k]];
/* 624 */           int val = selement >> sshift[k] & 0x1;
/* 626 */           int dshift = 31 - (dbitnum & 0x1F);
/* 627 */           delement |= val << dshift;
/* 628 */           dbitnum++;
/* 629 */           k++;
/*     */         } 
/* 631 */         destData[dindex] = delement;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\ScaleNearestBinaryOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */