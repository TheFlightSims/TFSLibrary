/*     */ package com.sun.media.jai.opimage;
/*     */ 
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Point2D;
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
/*     */ import javax.media.jai.JAI;
/*     */ import javax.media.jai.util.Range;
/*     */ 
/*     */ final class AffineNearestBinaryOpImage extends AffineNearestOpImage {
/*  44 */   private int black = 0;
/*     */   
/*     */   private static Map configHelper(Map configuration) {
/*     */     Map config;
/*  52 */     if (configuration == null) {
/*  54 */       config = new RenderingHints(JAI.KEY_REPLACE_INDEX_COLOR_MODEL, Boolean.FALSE);
/*     */     } else {
/*  59 */       config = configuration;
/*  61 */       if (!config.containsKey(JAI.KEY_REPLACE_INDEX_COLOR_MODEL)) {
/*  62 */         RenderingHints hints = (RenderingHints)configuration;
/*  63 */         config = (RenderingHints)hints.clone();
/*  64 */         config.put(JAI.KEY_REPLACE_INDEX_COLOR_MODEL, Boolean.FALSE);
/*     */       } 
/*     */     } 
/*  68 */     return config;
/*     */   }
/*     */   
/*     */   public AffineNearestBinaryOpImage(RenderedImage source, BorderExtender extender, Map config, ImageLayout layout, AffineTransform transform, Interpolation interp, double[] backgroundValues) {
/*  87 */     super(source, extender, configHelper(config), layout, transform, interp, backgroundValues);
/*  96 */     if (layout != null) {
/*  97 */       this.colorModel = layout.getColorModel(source);
/*     */     } else {
/*  99 */       this.colorModel = source.getColorModel();
/*     */     } 
/* 101 */     this.sampleModel = source.getSampleModel().createCompatibleSampleModel(this.tileWidth, this.tileHeight);
/*     */   }
/*     */   
/*     */   protected void computeRect(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/* 131 */     Raster source = sources[0];
/* 133 */     switch (source.getSampleModel().getDataType()) {
/*     */       case 0:
/* 135 */         byteLoop(source, dest, destRect);
/*     */         break;
/*     */       case 3:
/* 139 */         intLoop(source, dest, destRect);
/*     */         break;
/*     */       case 1:
/*     */       case 2:
/* 144 */         shortLoop(source, dest, destRect);
/*     */         break;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void byteLoop(Raster source, WritableRaster dest, Rectangle destRect) {
/* 152 */     float src_rect_x1 = source.getMinX();
/* 153 */     float src_rect_y1 = source.getMinY();
/* 154 */     float src_rect_x2 = src_rect_x1 + source.getWidth();
/* 155 */     float src_rect_y2 = src_rect_y1 + source.getHeight();
/* 157 */     MultiPixelPackedSampleModel sourceSM = (MultiPixelPackedSampleModel)source.getSampleModel();
/* 159 */     DataBufferByte sourceDB = (DataBufferByte)source.getDataBuffer();
/* 161 */     int sourceTransX = source.getSampleModelTranslateX();
/* 162 */     int sourceTransY = source.getSampleModelTranslateY();
/* 163 */     int sourceDataBitOffset = sourceSM.getDataBitOffset();
/* 164 */     int sourceScanlineStride = sourceSM.getScanlineStride();
/* 166 */     MultiPixelPackedSampleModel destSM = (MultiPixelPackedSampleModel)dest.getSampleModel();
/* 168 */     DataBufferByte destDB = (DataBufferByte)dest.getDataBuffer();
/* 170 */     int destMinX = dest.getMinX();
/* 171 */     int destMinY = dest.getMinY();
/* 172 */     int destTransX = dest.getSampleModelTranslateX();
/* 173 */     int destTransY = dest.getSampleModelTranslateY();
/* 174 */     int destDataBitOffset = destSM.getDataBitOffset();
/* 175 */     int destScanlineStride = destSM.getScanlineStride();
/* 177 */     byte[] sourceData = sourceDB.getData();
/* 178 */     int sourceDBOffset = sourceDB.getOffset();
/* 180 */     byte[] destData = destDB.getData();
/* 181 */     int destDBOffset = destDB.getOffset();
/* 183 */     Point2D dst_pt = new Point2D.Float();
/* 184 */     Point2D src_pt = new Point2D.Float();
/* 186 */     int dst_min_x = destRect.x;
/* 187 */     int dst_min_y = destRect.y;
/* 188 */     int dst_max_x = destRect.x + destRect.width;
/* 189 */     int dst_max_y = destRect.y + destRect.height;
/* 191 */     int incyStride = this.incy * sourceScanlineStride;
/* 192 */     int incy1Stride = this.incy1 * sourceScanlineStride;
/* 194 */     this.black = (int)this.backgroundValues[0] & 0x1;
/* 196 */     for (int y = dst_min_y; y < dst_max_y; y++) {
/* 199 */       dst_pt.setLocation(dst_min_x + 0.5D, y + 0.5D);
/* 201 */       mapDestPoint(dst_pt, src_pt);
/* 204 */       float s_x = (float)src_pt.getX();
/* 205 */       float s_y = (float)src_pt.getY();
/* 208 */       int s_ix = (int)Math.floor(s_x);
/* 209 */       int s_iy = (int)Math.floor(s_y);
/* 211 */       double fracx = s_x - s_ix;
/* 212 */       double fracy = s_y - s_iy;
/* 214 */       int ifracx = (int)Math.floor(fracx * 1048576.0D);
/* 215 */       int ifracy = (int)Math.floor(fracy * 1048576.0D);
/* 217 */       int start_s_ix = s_ix;
/* 218 */       int start_s_iy = s_iy;
/* 219 */       int start_ifracx = ifracx;
/* 220 */       int start_ifracy = ifracy;
/* 223 */       Range clipRange = performScanlineClipping(src_rect_x1, src_rect_y1, src_rect_x2 - 1.0F, src_rect_y2 - 1.0F, s_ix, s_iy, ifracx, ifracy, dst_min_x, dst_max_x, 0, 0, 0, 0);
/* 233 */       int clipMinX = ((Integer)clipRange.getMinValue()).intValue();
/* 234 */       int clipMaxX = ((Integer)clipRange.getMaxValue()).intValue();
/* 236 */       if (clipMinX <= clipMaxX) {
/* 238 */         int destYOffset = (y - destTransY) * destScanlineStride + destDBOffset;
/* 240 */         int destXOffset = destDataBitOffset + dst_min_x - destTransX;
/* 242 */         int sourceYOffset = (s_iy - sourceTransY) * sourceScanlineStride + sourceDBOffset;
/* 244 */         int sourceXOffset = s_ix - sourceTransX + sourceDataBitOffset;
/*     */         int x;
/* 247 */         for (x = dst_min_x; x < clipMinX; x++) {
/* 249 */           if (this.setBackground) {
/* 250 */             int dindex = destYOffset + (destXOffset >> 3);
/* 251 */             int dshift = 7 - (destXOffset & 0x7);
/* 252 */             int delement = destData[dindex];
/* 253 */             delement |= this.black << dshift;
/* 254 */             destData[dindex] = (byte)delement;
/*     */           } 
/* 258 */           if (ifracx < this.ifracdx1) {
/* 263 */             ifracx += this.ifracdx;
/* 264 */             sourceXOffset += this.incx;
/*     */           } else {
/* 270 */             ifracx -= this.ifracdx1;
/* 271 */             sourceXOffset += this.incx1;
/*     */           } 
/* 274 */           if (ifracy < this.ifracdy1) {
/* 279 */             ifracy += this.ifracdy;
/* 280 */             sourceYOffset += incyStride;
/*     */           } else {
/* 286 */             ifracy -= this.ifracdy1;
/* 287 */             sourceYOffset += incy1Stride;
/*     */           } 
/* 290 */           destXOffset++;
/*     */         } 
/* 293 */         for (x = clipMinX; x < clipMaxX; x++) {
/* 294 */           int sindex = sourceYOffset + (sourceXOffset >> 3);
/* 295 */           byte selement = sourceData[sindex];
/* 296 */           int val = selement >> 7 - (sourceXOffset & 0x7) & 0x1;
/* 298 */           int dindex = destYOffset + (destXOffset >> 3);
/* 299 */           int dshift = 7 - (destXOffset & 0x7);
/* 300 */           int delement = destData[dindex];
/* 301 */           delement |= val << dshift;
/* 302 */           destData[dindex] = (byte)delement;
/* 305 */           if (ifracx < this.ifracdx1) {
/* 310 */             ifracx += this.ifracdx;
/* 311 */             sourceXOffset += this.incx;
/*     */           } else {
/* 317 */             ifracx -= this.ifracdx1;
/* 318 */             sourceXOffset += this.incx1;
/*     */           } 
/* 321 */           if (ifracy < this.ifracdy1) {
/* 326 */             ifracy += this.ifracdy;
/* 327 */             sourceYOffset += incyStride;
/*     */           } else {
/* 333 */             ifracy -= this.ifracdy1;
/* 334 */             sourceYOffset += incy1Stride;
/*     */           } 
/* 337 */           destXOffset++;
/*     */         } 
/* 340 */         for (x = clipMaxX; x < dst_max_x; x++) {
/* 342 */           if (this.setBackground) {
/* 343 */             int dindex = destYOffset + (destXOffset >> 3);
/* 344 */             int dshift = 7 - (destXOffset & 0x7);
/* 345 */             int delement = destData[dindex];
/* 346 */             delement |= this.black << dshift;
/* 347 */             destData[dindex] = (byte)delement;
/*     */           } 
/* 351 */           if (ifracx < this.ifracdx1) {
/* 356 */             ifracx += this.ifracdx;
/* 357 */             sourceXOffset += this.incx;
/*     */           } else {
/* 363 */             ifracx -= this.ifracdx1;
/* 364 */             sourceXOffset += this.incx1;
/*     */           } 
/* 367 */           if (ifracy < this.ifracdy1) {
/* 372 */             ifracy += this.ifracdy;
/* 373 */             sourceYOffset += incyStride;
/*     */           } else {
/* 379 */             ifracy -= this.ifracdy1;
/* 380 */             sourceYOffset += incy1Stride;
/*     */           } 
/* 383 */           destXOffset++;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void shortLoop(Raster source, WritableRaster dest, Rectangle destRect) {
/* 391 */     float src_rect_x1 = source.getMinX();
/* 392 */     float src_rect_y1 = source.getMinY();
/* 393 */     float src_rect_x2 = src_rect_x1 + source.getWidth();
/* 394 */     float src_rect_y2 = src_rect_y1 + source.getHeight();
/* 396 */     MultiPixelPackedSampleModel sourceSM = (MultiPixelPackedSampleModel)source.getSampleModel();
/* 398 */     DataBufferUShort sourceDB = (DataBufferUShort)source.getDataBuffer();
/* 400 */     int sourceTransX = source.getSampleModelTranslateX();
/* 401 */     int sourceTransY = source.getSampleModelTranslateY();
/* 402 */     int sourceDataBitOffset = sourceSM.getDataBitOffset();
/* 403 */     int sourceScanlineStride = sourceSM.getScanlineStride();
/* 405 */     MultiPixelPackedSampleModel destSM = (MultiPixelPackedSampleModel)dest.getSampleModel();
/* 407 */     DataBufferUShort destDB = (DataBufferUShort)dest.getDataBuffer();
/* 409 */     int destMinX = dest.getMinX();
/* 410 */     int destMinY = dest.getMinY();
/* 411 */     int destTransX = dest.getSampleModelTranslateX();
/* 412 */     int destTransY = dest.getSampleModelTranslateY();
/* 413 */     int destDataBitOffset = destSM.getDataBitOffset();
/* 414 */     int destScanlineStride = destSM.getScanlineStride();
/* 416 */     short[] sourceData = sourceDB.getData();
/* 417 */     int sourceDBOffset = sourceDB.getOffset();
/* 419 */     short[] destData = destDB.getData();
/* 420 */     int destDBOffset = destDB.getOffset();
/* 422 */     Point2D dst_pt = new Point2D.Float();
/* 423 */     Point2D src_pt = new Point2D.Float();
/* 425 */     int dst_min_x = destRect.x;
/* 426 */     int dst_min_y = destRect.y;
/* 427 */     int dst_max_x = destRect.x + destRect.width;
/* 428 */     int dst_max_y = destRect.y + destRect.height;
/* 430 */     int incyStride = this.incy * sourceScanlineStride;
/* 431 */     int incy1Stride = this.incy1 * sourceScanlineStride;
/* 433 */     this.black = (int)this.backgroundValues[0] & 0x1;
/* 435 */     for (int y = dst_min_y; y < dst_max_y; y++) {
/* 438 */       dst_pt.setLocation(dst_min_x + 0.5D, y + 0.5D);
/* 440 */       mapDestPoint(dst_pt, src_pt);
/* 443 */       float s_x = (float)src_pt.getX();
/* 444 */       float s_y = (float)src_pt.getY();
/* 447 */       int s_ix = (int)Math.floor(s_x);
/* 448 */       int s_iy = (int)Math.floor(s_y);
/* 450 */       double fracx = s_x - s_ix;
/* 451 */       double fracy = s_y - s_iy;
/* 453 */       int ifracx = (int)Math.floor(fracx * 1048576.0D);
/* 454 */       int ifracy = (int)Math.floor(fracy * 1048576.0D);
/* 456 */       int start_s_ix = s_ix;
/* 457 */       int start_s_iy = s_iy;
/* 458 */       int start_ifracx = ifracx;
/* 459 */       int start_ifracy = ifracy;
/* 462 */       Range clipRange = performScanlineClipping(src_rect_x1, src_rect_y1, src_rect_x2 - 1.0F, src_rect_y2 - 1.0F, s_ix, s_iy, ifracx, ifracy, dst_min_x, dst_max_x, 0, 0, 0, 0);
/* 472 */       int clipMinX = ((Integer)clipRange.getMinValue()).intValue();
/* 473 */       int clipMaxX = ((Integer)clipRange.getMaxValue()).intValue();
/* 475 */       if (clipMinX <= clipMaxX) {
/* 477 */         int destYOffset = (y - destTransY) * destScanlineStride + destDBOffset;
/* 479 */         int destXOffset = destDataBitOffset + dst_min_x - destTransX;
/* 481 */         int sourceYOffset = (s_iy - sourceTransY) * sourceScanlineStride + sourceDBOffset;
/* 483 */         int sourceXOffset = s_ix - sourceTransX + sourceDataBitOffset;
/*     */         int x;
/* 486 */         for (x = dst_min_x; x < clipMinX; x++) {
/* 488 */           if (this.setBackground) {
/* 489 */             int dindex = destYOffset + (destXOffset >> 4);
/* 490 */             int dshift = 15 - (destXOffset & 0xF);
/* 491 */             int delement = destData[dindex];
/* 492 */             delement |= this.black << dshift;
/* 493 */             destData[dindex] = (short)delement;
/*     */           } 
/* 497 */           if (ifracx < this.ifracdx1) {
/* 502 */             ifracx += this.ifracdx;
/* 503 */             sourceXOffset += this.incx;
/*     */           } else {
/* 509 */             ifracx -= this.ifracdx1;
/* 510 */             sourceXOffset += this.incx1;
/*     */           } 
/* 513 */           if (ifracy < this.ifracdy1) {
/* 518 */             ifracy += this.ifracdy;
/* 519 */             sourceYOffset += incyStride;
/*     */           } else {
/* 525 */             ifracy -= this.ifracdy1;
/* 526 */             sourceYOffset += incy1Stride;
/*     */           } 
/* 529 */           destXOffset++;
/*     */         } 
/* 532 */         for (x = clipMinX; x < clipMaxX; x++) {
/* 533 */           int sindex = sourceYOffset + (sourceXOffset >> 4);
/* 534 */           short selement = sourceData[sindex];
/* 535 */           int val = selement >> 15 - (sourceXOffset & 0xF) & 0x1;
/* 537 */           int dindex = destYOffset + (destXOffset >> 4);
/* 538 */           int dshift = 15 - (destXOffset & 0xF);
/* 539 */           int delement = destData[dindex];
/* 540 */           delement |= val << dshift;
/* 541 */           destData[dindex] = (short)delement;
/* 544 */           if (ifracx < this.ifracdx1) {
/* 549 */             ifracx += this.ifracdx;
/* 550 */             sourceXOffset += this.incx;
/*     */           } else {
/* 556 */             ifracx -= this.ifracdx1;
/* 557 */             sourceXOffset += this.incx1;
/*     */           } 
/* 560 */           if (ifracy < this.ifracdy1) {
/* 565 */             ifracy += this.ifracdy;
/* 566 */             sourceYOffset += incyStride;
/*     */           } else {
/* 572 */             ifracy -= this.ifracdy1;
/* 573 */             sourceYOffset += incy1Stride;
/*     */           } 
/* 576 */           destXOffset++;
/*     */         } 
/* 579 */         for (x = clipMaxX; x < dst_max_x; x++) {
/* 580 */           if (this.setBackground) {
/* 581 */             int dindex = destYOffset + (destXOffset >> 4);
/* 582 */             int dshift = 15 - (destXOffset & 0xF);
/* 583 */             int delement = destData[dindex];
/* 584 */             delement |= this.black << dshift;
/* 585 */             destData[dindex] = (short)delement;
/*     */           } 
/* 589 */           if (ifracx < this.ifracdx1) {
/* 594 */             ifracx += this.ifracdx;
/* 595 */             sourceXOffset += this.incx;
/*     */           } else {
/* 601 */             ifracx -= this.ifracdx1;
/* 602 */             sourceXOffset += this.incx1;
/*     */           } 
/* 605 */           if (ifracy < this.ifracdy1) {
/* 610 */             ifracy += this.ifracdy;
/* 611 */             sourceYOffset += incyStride;
/*     */           } else {
/* 617 */             ifracy -= this.ifracdy1;
/* 618 */             sourceYOffset += incy1Stride;
/*     */           } 
/* 621 */           destXOffset++;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void intLoop(Raster source, WritableRaster dest, Rectangle destRect) {
/* 629 */     float src_rect_x1 = source.getMinX();
/* 630 */     float src_rect_y1 = source.getMinY();
/* 631 */     float src_rect_x2 = src_rect_x1 + source.getWidth();
/* 632 */     float src_rect_y2 = src_rect_y1 + source.getHeight();
/* 634 */     MultiPixelPackedSampleModel sourceSM = (MultiPixelPackedSampleModel)source.getSampleModel();
/* 636 */     DataBufferInt sourceDB = (DataBufferInt)source.getDataBuffer();
/* 638 */     int sourceTransX = source.getSampleModelTranslateX();
/* 639 */     int sourceTransY = source.getSampleModelTranslateY();
/* 640 */     int sourceDataBitOffset = sourceSM.getDataBitOffset();
/* 641 */     int sourceScanlineStride = sourceSM.getScanlineStride();
/* 643 */     MultiPixelPackedSampleModel destSM = (MultiPixelPackedSampleModel)dest.getSampleModel();
/* 645 */     DataBufferInt destDB = (DataBufferInt)dest.getDataBuffer();
/* 647 */     int destMinX = dest.getMinX();
/* 648 */     int destMinY = dest.getMinY();
/* 649 */     int destTransX = dest.getSampleModelTranslateX();
/* 650 */     int destTransY = dest.getSampleModelTranslateY();
/* 651 */     int destDataBitOffset = destSM.getDataBitOffset();
/* 652 */     int destScanlineStride = destSM.getScanlineStride();
/* 654 */     int[] sourceData = sourceDB.getData();
/* 655 */     int sourceDBOffset = sourceDB.getOffset();
/* 657 */     int[] destData = destDB.getData();
/* 658 */     int destDBOffset = destDB.getOffset();
/* 660 */     Point2D dst_pt = new Point2D.Float();
/* 661 */     Point2D src_pt = new Point2D.Float();
/* 663 */     int dst_min_x = destRect.x;
/* 664 */     int dst_min_y = destRect.y;
/* 665 */     int dst_max_x = destRect.x + destRect.width;
/* 666 */     int dst_max_y = destRect.y + destRect.height;
/* 668 */     int incyStride = this.incy * sourceScanlineStride;
/* 669 */     int incy1Stride = this.incy1 * sourceScanlineStride;
/* 671 */     this.black = (int)this.backgroundValues[0] & 0x1;
/* 673 */     for (int y = dst_min_y; y < dst_max_y; y++) {
/* 676 */       dst_pt.setLocation(dst_min_x + 0.5D, y + 0.5D);
/* 678 */       mapDestPoint(dst_pt, src_pt);
/* 681 */       float s_x = (float)src_pt.getX();
/* 682 */       float s_y = (float)src_pt.getY();
/* 685 */       int s_ix = (int)Math.floor(s_x);
/* 686 */       int s_iy = (int)Math.floor(s_y);
/* 688 */       double fracx = s_x - s_ix;
/* 689 */       double fracy = s_y - s_iy;
/* 691 */       int ifracx = (int)Math.floor(fracx * 1048576.0D);
/* 692 */       int ifracy = (int)Math.floor(fracy * 1048576.0D);
/* 694 */       int start_s_ix = s_ix;
/* 695 */       int start_s_iy = s_iy;
/* 696 */       int start_ifracx = ifracx;
/* 697 */       int start_ifracy = ifracy;
/* 700 */       Range clipRange = performScanlineClipping(src_rect_x1, src_rect_y1, src_rect_x2 - 1.0F, src_rect_y2 - 1.0F, s_ix, s_iy, ifracx, ifracy, dst_min_x, dst_max_x, 0, 0, 0, 0);
/* 710 */       int clipMinX = ((Integer)clipRange.getMinValue()).intValue();
/* 711 */       int clipMaxX = ((Integer)clipRange.getMaxValue()).intValue();
/* 713 */       if (clipMinX <= clipMaxX) {
/* 715 */         int destYOffset = (y - destTransY) * destScanlineStride + destDBOffset;
/* 717 */         int destXOffset = destDataBitOffset + dst_min_x - destTransX;
/* 719 */         int sourceYOffset = (s_iy - sourceTransY) * sourceScanlineStride + sourceDBOffset;
/* 721 */         int sourceXOffset = s_ix - sourceTransX + sourceDataBitOffset;
/*     */         int x;
/* 724 */         for (x = dst_min_x; x < clipMinX; x++) {
/* 725 */           if (this.setBackground) {
/* 726 */             int dindex = destYOffset + (destXOffset >> 5);
/* 727 */             int dshift = 31 - (destXOffset & 0x1F);
/* 728 */             int delement = destData[dindex];
/* 729 */             delement |= this.black << dshift;
/* 730 */             destData[dindex] = delement;
/*     */           } 
/* 734 */           if (ifracx < this.ifracdx1) {
/* 739 */             ifracx += this.ifracdx;
/* 740 */             sourceXOffset += this.incx;
/*     */           } else {
/* 746 */             ifracx -= this.ifracdx1;
/* 747 */             sourceXOffset += this.incx1;
/*     */           } 
/* 750 */           if (ifracy < this.ifracdy1) {
/* 755 */             ifracy += this.ifracdy;
/* 756 */             sourceYOffset += incyStride;
/*     */           } else {
/* 762 */             ifracy -= this.ifracdy1;
/* 763 */             sourceYOffset += incy1Stride;
/*     */           } 
/* 766 */           destXOffset++;
/*     */         } 
/* 769 */         for (x = clipMinX; x < clipMaxX; x++) {
/* 770 */           int sindex = sourceYOffset + (sourceXOffset >> 5);
/* 771 */           int selement = sourceData[sindex];
/* 772 */           int val = selement >> 31 - (sourceXOffset & 0x1F) & 0x1;
/* 774 */           int dindex = destYOffset + (destXOffset >> 5);
/* 775 */           int dshift = 31 - (destXOffset & 0x1F);
/* 776 */           int delement = destData[dindex];
/* 777 */           delement |= val << dshift;
/* 778 */           destData[dindex] = delement;
/* 781 */           if (ifracx < this.ifracdx1) {
/* 786 */             ifracx += this.ifracdx;
/* 787 */             sourceXOffset += this.incx;
/*     */           } else {
/* 793 */             ifracx -= this.ifracdx1;
/* 794 */             sourceXOffset += this.incx1;
/*     */           } 
/* 797 */           if (ifracy < this.ifracdy1) {
/* 802 */             ifracy += this.ifracdy;
/* 803 */             sourceYOffset += incyStride;
/*     */           } else {
/* 809 */             ifracy -= this.ifracdy1;
/* 810 */             sourceYOffset += incy1Stride;
/*     */           } 
/* 813 */           destXOffset++;
/*     */         } 
/* 816 */         for (x = clipMaxX; x < dst_max_x; x++) {
/* 817 */           if (this.setBackground) {
/* 818 */             int dindex = destYOffset + (destXOffset >> 5);
/* 819 */             int dshift = 31 - (destXOffset & 0x1F);
/* 820 */             int delement = destData[dindex];
/* 821 */             delement |= this.black << dshift;
/* 822 */             destData[dindex] = delement;
/*     */           } 
/* 826 */           if (ifracx < this.ifracdx1) {
/* 831 */             ifracx += this.ifracdx;
/* 832 */             sourceXOffset += this.incx;
/*     */           } else {
/* 838 */             ifracx -= this.ifracdx1;
/* 839 */             sourceXOffset += this.incx1;
/*     */           } 
/* 842 */           if (ifracy < this.ifracdy1) {
/* 847 */             ifracy += this.ifracdy;
/* 848 */             sourceYOffset += incyStride;
/*     */           } else {
/* 854 */             ifracy -= this.ifracdy1;
/* 855 */             sourceYOffset += incy1Stride;
/*     */           } 
/* 858 */           destXOffset++;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\AffineNearestBinaryOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */