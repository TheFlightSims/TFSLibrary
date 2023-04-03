/*     */ package com.sun.media.jai.opimage;
/*     */ 
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.image.ColorModel;
/*     */ import java.awt.image.DataBufferByte;
/*     */ import java.awt.image.DataBufferInt;
/*     */ import java.awt.image.DataBufferUShort;
/*     */ import java.awt.image.MultiPixelPackedSampleModel;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.SampleModel;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.util.Map;
/*     */ import javax.media.jai.ImageLayout;
/*     */ import javax.media.jai.JAI;
/*     */ import javax.media.jai.PlanarImage;
/*     */ 
/*     */ final class TransposeBinaryOpImage extends TransposeOpImage {
/*     */   private static ImageLayout layoutHelper(ImageLayout layout, SampleModel sm, ColorModel cm) {
/*     */     ImageLayout newLayout;
/*  55 */     if (layout != null) {
/*  56 */       newLayout = (ImageLayout)layout.clone();
/*     */     } else {
/*  58 */       newLayout = new ImageLayout();
/*     */     } 
/*  61 */     newLayout.setSampleModel(sm);
/*  62 */     newLayout.setColorModel(cm);
/*  64 */     return newLayout;
/*     */   }
/*     */   
/*     */   private static Map configHelper(Map configuration) {
/*     */     Map config;
/*  73 */     if (configuration == null) {
/*  74 */       config = new RenderingHints(JAI.KEY_REPLACE_INDEX_COLOR_MODEL, Boolean.FALSE);
/*     */     } else {
/*  78 */       config = configuration;
/*  80 */       if (!config.containsKey(JAI.KEY_REPLACE_INDEX_COLOR_MODEL)) {
/*  81 */         RenderingHints hints = (RenderingHints)configuration;
/*  82 */         config = (RenderingHints)hints.clone();
/*  83 */         config.put(JAI.KEY_REPLACE_INDEX_COLOR_MODEL, Boolean.FALSE);
/*     */       } 
/*     */     } 
/*  87 */     return config;
/*     */   }
/*     */   
/*     */   public TransposeBinaryOpImage(RenderedImage source, Map config, ImageLayout layout, int type) {
/* 107 */     super(source, configHelper(config), layoutHelper(layout, source.getSampleModel(), source.getColorModel()), type);
/*     */   }
/*     */   
/*     */   protected void computeRect(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/* 118 */     Raster source = sources[0];
/* 120 */     MultiPixelPackedSampleModel mppsm = (MultiPixelPackedSampleModel)source.getSampleModel();
/* 122 */     int srcScanlineStride = mppsm.getScanlineStride();
/* 124 */     int incr1 = 0, incr2 = 0, s_x = 0, s_y = 0;
/* 126 */     int bits = 8;
/* 127 */     int dataType = source.getSampleModel().getDataType();
/* 128 */     if (dataType == 1) {
/* 129 */       bits = 16;
/* 130 */     } else if (dataType == 3) {
/* 131 */       bits = 32;
/*     */     } 
/* 134 */     PlanarImage src = getSource(0);
/* 135 */     int sMinX = src.getMinX();
/* 136 */     int sMinY = src.getMinY();
/* 137 */     int sWidth = src.getWidth();
/* 138 */     int sHeight = src.getHeight();
/* 139 */     int sMaxX = sMinX + sWidth - 1;
/* 140 */     int sMaxY = sMinY + sHeight - 1;
/* 143 */     int[] pt = new int[2];
/* 144 */     pt[0] = destRect.x;
/* 145 */     pt[1] = destRect.y;
/* 146 */     mapPoint(pt, sMinX, sMinY, sMaxX, sMaxY, this.type, false);
/* 147 */     s_x = pt[0];
/* 148 */     s_y = pt[1];
/* 151 */     switch (this.type) {
/*     */       case 0:
/* 153 */         incr1 = 1;
/* 154 */         incr2 = -bits * srcScanlineStride;
/*     */         break;
/*     */       case 1:
/* 158 */         incr1 = -1;
/* 159 */         incr2 = bits * srcScanlineStride;
/*     */         break;
/*     */       case 2:
/* 163 */         incr1 = bits * srcScanlineStride;
/* 164 */         incr2 = 1;
/*     */         break;
/*     */       case 3:
/* 168 */         incr1 = -bits * srcScanlineStride;
/* 169 */         incr2 = -1;
/*     */         break;
/*     */       case 4:
/* 173 */         incr1 = -bits * srcScanlineStride;
/* 174 */         incr2 = 1;
/*     */         break;
/*     */       case 5:
/* 178 */         incr1 = -1;
/* 179 */         incr2 = -bits * srcScanlineStride;
/*     */         break;
/*     */       case 6:
/* 183 */         incr1 = bits * srcScanlineStride;
/* 184 */         incr2 = -1;
/*     */         break;
/*     */     } 
/* 188 */     switch (source.getSampleModel().getDataType()) {
/*     */       case 0:
/* 190 */         byteLoop(source, dest, destRect, incr1, incr2, s_x, s_y);
/*     */         break;
/*     */       case 1:
/*     */       case 2:
/* 198 */         shortLoop(source, dest, destRect, incr1, incr2, s_x, s_y);
/*     */         break;
/*     */       case 3:
/* 205 */         intLoop(source, dest, destRect, incr1, incr2, s_x, s_y);
/*     */         break;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void byteLoop(Raster source, WritableRaster dest, Rectangle destRect, int incr1, int incr2, int s_x, int s_y) {
/* 217 */     MultiPixelPackedSampleModel sourceSM = (MultiPixelPackedSampleModel)source.getSampleModel();
/* 219 */     DataBufferByte sourceDB = (DataBufferByte)source.getDataBuffer();
/* 221 */     int sourceTransX = source.getSampleModelTranslateX();
/* 222 */     int sourceTransY = source.getSampleModelTranslateY();
/* 223 */     int sourceDataBitOffset = sourceSM.getDataBitOffset();
/* 224 */     int sourceScanlineStride = sourceSM.getScanlineStride();
/* 226 */     MultiPixelPackedSampleModel destSM = (MultiPixelPackedSampleModel)dest.getSampleModel();
/* 228 */     DataBufferByte destDB = (DataBufferByte)dest.getDataBuffer();
/* 230 */     int destMinX = dest.getMinX();
/* 231 */     int destMinY = dest.getMinY();
/* 232 */     int destTransX = dest.getSampleModelTranslateX();
/* 233 */     int destTransY = dest.getSampleModelTranslateY();
/* 234 */     int destDataBitOffset = destSM.getDataBitOffset();
/* 235 */     int destScanlineStride = destSM.getScanlineStride();
/* 237 */     byte[] sourceData = sourceDB.getData();
/* 238 */     int sourceDBOffset = sourceDB.getOffset();
/* 240 */     byte[] destData = destDB.getData();
/* 241 */     int destDBOffset = destDB.getOffset();
/* 243 */     int dx = destRect.x;
/* 244 */     int dy = destRect.y;
/* 245 */     int dwidth = destRect.width;
/* 246 */     int dheight = destRect.height;
/* 248 */     int sourceOffset = 8 * (s_y - sourceTransY) * sourceScanlineStride + 8 * sourceDBOffset + s_x - sourceTransX + sourceDataBitOffset;
/* 254 */     int destOffset = 8 * (dy - destTransY) * destScanlineStride + 8 * destDBOffset + dx - destTransX + destDataBitOffset;
/* 260 */     for (int j = 0; j < dheight; j++) {
/* 261 */       int sOffset = sourceOffset;
/* 262 */       int dOffset = destOffset;
/* 265 */       int i = 0;
/* 266 */       while (i < dwidth && (dOffset & 0x7) != 0) {
/* 267 */         int selement = sourceData[sOffset >> 3];
/* 268 */         int val = selement >> 7 - (sOffset & 0x7) & 0x1;
/* 270 */         int k = dOffset >> 3;
/* 271 */         int dshift = 7 - (dOffset & 0x7);
/* 272 */         int delement = destData[k];
/* 273 */         delement |= val << dshift;
/* 274 */         destData[k] = (byte)delement;
/* 276 */         sOffset += incr1;
/* 277 */         dOffset++;
/* 278 */         i++;
/*     */       } 
/* 281 */       int dindex = dOffset >> 3;
/* 282 */       if ((incr1 & 0x7) == 0) {
/* 288 */         int shift = 7 - (sOffset & 0x7);
/* 289 */         int offset = sOffset >> 3;
/* 290 */         int incr = incr1 >> 3;
/* 292 */         while (i < dwidth - 7) {
/* 293 */           int selement = sourceData[offset];
/* 294 */           int val = selement >> shift & 0x1;
/* 295 */           int delement = val << 7;
/* 296 */           offset += incr;
/* 298 */           selement = sourceData[offset];
/* 299 */           val = selement >> shift & 0x1;
/* 300 */           delement |= val << 6;
/* 301 */           offset += incr;
/* 303 */           selement = sourceData[offset];
/* 304 */           val = selement >> shift & 0x1;
/* 305 */           delement |= val << 5;
/* 306 */           offset += incr;
/* 308 */           selement = sourceData[offset];
/* 309 */           val = selement >> shift & 0x1;
/* 310 */           delement |= val << 4;
/* 311 */           offset += incr;
/* 313 */           selement = sourceData[offset];
/* 314 */           val = selement >> shift & 0x1;
/* 315 */           delement |= val << 3;
/* 316 */           offset += incr;
/* 318 */           selement = sourceData[offset];
/* 319 */           val = selement >> shift & 0x1;
/* 320 */           delement |= val << 2;
/* 321 */           offset += incr;
/* 323 */           selement = sourceData[offset];
/* 324 */           val = selement >> shift & 0x1;
/* 325 */           delement |= val << 1;
/* 326 */           offset += incr;
/* 328 */           selement = sourceData[offset];
/* 329 */           val = selement >> shift & 0x1;
/* 330 */           delement |= val;
/* 331 */           offset += incr;
/* 333 */           destData[dindex] = (byte)delement;
/* 335 */           sOffset += 8 * incr1;
/* 336 */           dOffset += 8;
/* 337 */           i += 8;
/* 338 */           dindex++;
/*     */         } 
/*     */       } else {
/* 345 */         while (i < dwidth - 7) {
/* 346 */           int selement = sourceData[sOffset >> 3];
/* 347 */           int val = selement >> 7 - (sOffset & 0x7) & 0x1;
/* 348 */           int delement = val << 7;
/* 349 */           sOffset += incr1;
/* 351 */           selement = sourceData[sOffset >> 3];
/* 352 */           val = selement >> 7 - (sOffset & 0x7) & 0x1;
/* 353 */           delement |= val << 6;
/* 354 */           sOffset += incr1;
/* 356 */           selement = sourceData[sOffset >> 3];
/* 357 */           val = selement >> 7 - (sOffset & 0x7) & 0x1;
/* 358 */           delement |= val << 5;
/* 359 */           sOffset += incr1;
/* 361 */           selement = sourceData[sOffset >> 3];
/* 362 */           val = selement >> 7 - (sOffset & 0x7) & 0x1;
/* 363 */           delement |= val << 4;
/* 364 */           sOffset += incr1;
/* 366 */           selement = sourceData[sOffset >> 3];
/* 367 */           val = selement >> 7 - (sOffset & 0x7) & 0x1;
/* 368 */           delement |= val << 3;
/* 369 */           sOffset += incr1;
/* 371 */           selement = sourceData[sOffset >> 3];
/* 372 */           val = selement >> 7 - (sOffset & 0x7) & 0x1;
/* 373 */           delement |= val << 2;
/* 374 */           sOffset += incr1;
/* 376 */           selement = sourceData[sOffset >> 3];
/* 377 */           val = selement >> 7 - (sOffset & 0x7) & 0x1;
/* 378 */           delement |= val << 1;
/* 379 */           sOffset += incr1;
/* 381 */           selement = sourceData[sOffset >> 3];
/* 382 */           val = selement >> 7 - (sOffset & 0x7) & 0x1;
/* 383 */           delement |= val;
/* 384 */           sOffset += incr1;
/* 386 */           destData[dindex] = (byte)delement;
/* 388 */           dOffset += 8;
/* 389 */           i += 8;
/* 390 */           dindex++;
/*     */         } 
/*     */       } 
/* 394 */       while (i < dwidth) {
/* 395 */         int selement = sourceData[sOffset >> 3];
/* 396 */         int val = selement >> 7 - (sOffset & 0x7) & 0x1;
/* 398 */         dindex = dOffset >> 3;
/* 399 */         int dshift = 7 - (dOffset & 0x7);
/* 400 */         int delement = destData[dindex];
/* 401 */         delement |= val << dshift;
/* 402 */         destData[dindex] = (byte)delement;
/* 404 */         sOffset += incr1;
/* 405 */         dOffset++;
/* 406 */         i++;
/*     */       } 
/* 409 */       sourceOffset += incr2;
/* 410 */       destOffset += 8 * destScanlineStride;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void shortLoop(Raster source, Raster dest, Rectangle destRect, int incr1, int incr2, int s_x, int s_y) {
/* 418 */     MultiPixelPackedSampleModel sourceSM = (MultiPixelPackedSampleModel)source.getSampleModel();
/* 420 */     DataBufferUShort sourceDB = (DataBufferUShort)source.getDataBuffer();
/* 422 */     int sourceTransX = source.getSampleModelTranslateX();
/* 423 */     int sourceTransY = source.getSampleModelTranslateY();
/* 424 */     int sourceDataBitOffset = sourceSM.getDataBitOffset();
/* 425 */     int sourceScanlineStride = sourceSM.getScanlineStride();
/* 427 */     MultiPixelPackedSampleModel destSM = (MultiPixelPackedSampleModel)dest.getSampleModel();
/* 429 */     DataBufferUShort destDB = (DataBufferUShort)dest.getDataBuffer();
/* 431 */     int destMinX = dest.getMinX();
/* 432 */     int destMinY = dest.getMinY();
/* 433 */     int destTransX = dest.getSampleModelTranslateX();
/* 434 */     int destTransY = dest.getSampleModelTranslateY();
/* 435 */     int destDataBitOffset = destSM.getDataBitOffset();
/* 436 */     int destScanlineStride = destSM.getScanlineStride();
/* 438 */     short[] sourceData = sourceDB.getData();
/* 439 */     int sourceDBOffset = sourceDB.getOffset();
/* 441 */     short[] destData = destDB.getData();
/* 442 */     int destDBOffset = destDB.getOffset();
/* 444 */     int dx = destRect.x;
/* 445 */     int dy = destRect.y;
/* 446 */     int dwidth = destRect.width;
/* 447 */     int dheight = destRect.height;
/* 449 */     int sourceOffset = 16 * (s_y - sourceTransY) * sourceScanlineStride + 16 * sourceDBOffset + s_x - sourceTransX + sourceDataBitOffset;
/* 455 */     int destOffset = 16 * (dy - destTransY) * destScanlineStride + 16 * destDBOffset + dx - destTransX + destDataBitOffset;
/* 461 */     for (int j = 0; j < dheight; j++) {
/* 462 */       int sOffset = sourceOffset;
/* 463 */       int dOffset = destOffset;
/* 466 */       int i = 0;
/* 467 */       while (i < dwidth && (dOffset & 0xF) != 0) {
/* 468 */         int selement = sourceData[sOffset >> 4];
/* 469 */         int val = selement >> 15 - (sOffset & 0xF) & 0x1;
/* 471 */         int k = dOffset >> 4;
/* 472 */         int dshift = 15 - (dOffset & 0xF);
/* 473 */         int delement = destData[k];
/* 474 */         delement |= val << dshift;
/* 475 */         destData[k] = (short)delement;
/* 477 */         sOffset += incr1;
/* 478 */         dOffset++;
/* 479 */         i++;
/*     */       } 
/* 482 */       int dindex = dOffset >> 4;
/* 483 */       if ((incr1 & 0xF) == 0) {
/* 484 */         int shift = 15 - (sOffset & 0x5);
/* 485 */         int offset = sOffset >> 4;
/* 486 */         int incr = incr1 >> 4;
/* 488 */         while (i < dwidth - 15) {
/* 489 */           int delement = 0;
/* 490 */           for (int b = 15; b >= 0; b--) {
/* 491 */             int selement = sourceData[offset];
/* 492 */             int val = selement >> shift & 0x1;
/* 493 */             delement |= val << b;
/* 494 */             offset += incr;
/*     */           } 
/* 497 */           destData[dindex] = (short)delement;
/* 499 */           sOffset += 16 * incr1;
/* 500 */           dOffset += 16;
/* 501 */           i += 16;
/* 502 */           dindex++;
/*     */         } 
/*     */       } else {
/* 505 */         while (i < dwidth - 15) {
/* 506 */           int delement = 0;
/* 507 */           for (int b = 15; b >= 0; b--) {
/* 508 */             int selement = sourceData[sOffset >> 4];
/* 509 */             int val = selement >> 15 - (sOffset & 0xF) & 0x1;
/* 510 */             delement |= val << b;
/* 511 */             sOffset += incr1;
/*     */           } 
/* 514 */           destData[dindex] = (short)delement;
/* 516 */           dOffset += 15;
/* 517 */           i += 16;
/* 518 */           dindex++;
/*     */         } 
/*     */       } 
/* 522 */       while (i < dwidth) {
/* 523 */         int selement = sourceData[sOffset >> 4];
/* 524 */         int val = selement >> 15 - (sOffset & 0xF) & 0x1;
/* 526 */         dindex = dOffset >> 4;
/* 527 */         int dshift = 15 - (dOffset & 0xF);
/* 528 */         int delement = destData[dindex];
/* 529 */         delement |= val << dshift;
/* 530 */         destData[dindex] = (short)delement;
/* 532 */         sOffset += incr1;
/* 533 */         dOffset++;
/* 534 */         i++;
/*     */       } 
/* 537 */       sourceOffset += incr2;
/* 538 */       destOffset += 16 * destScanlineStride;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void intLoop(Raster source, Raster dest, Rectangle destRect, int incr1, int incr2, int s_x, int s_y) {
/* 546 */     MultiPixelPackedSampleModel sourceSM = (MultiPixelPackedSampleModel)source.getSampleModel();
/* 548 */     DataBufferInt sourceDB = (DataBufferInt)source.getDataBuffer();
/* 550 */     int sourceTransX = source.getSampleModelTranslateX();
/* 551 */     int sourceTransY = source.getSampleModelTranslateY();
/* 552 */     int sourceDataBitOffset = sourceSM.getDataBitOffset();
/* 553 */     int sourceScanlineStride = sourceSM.getScanlineStride();
/* 555 */     MultiPixelPackedSampleModel destSM = (MultiPixelPackedSampleModel)dest.getSampleModel();
/* 557 */     DataBufferInt destDB = (DataBufferInt)dest.getDataBuffer();
/* 559 */     int destMinX = dest.getMinX();
/* 560 */     int destMinY = dest.getMinY();
/* 561 */     int destTransX = dest.getSampleModelTranslateX();
/* 562 */     int destTransY = dest.getSampleModelTranslateY();
/* 563 */     int destDataBitOffset = destSM.getDataBitOffset();
/* 564 */     int destScanlineStride = destSM.getScanlineStride();
/* 566 */     int[] sourceData = sourceDB.getData();
/* 567 */     int sourceDBOffset = sourceDB.getOffset();
/* 569 */     int[] destData = destDB.getData();
/* 570 */     int destDBOffset = destDB.getOffset();
/* 572 */     int dx = destRect.x;
/* 573 */     int dy = destRect.y;
/* 574 */     int dwidth = destRect.width;
/* 575 */     int dheight = destRect.height;
/* 577 */     int sourceOffset = 32 * (s_y - sourceTransY) * sourceScanlineStride + 32 * sourceDBOffset + s_x - sourceTransX + sourceDataBitOffset;
/* 583 */     int destOffset = 32 * (dy - destTransY) * destScanlineStride + 32 * destDBOffset + dx - destTransX + destDataBitOffset;
/* 589 */     for (int j = 0; j < dheight; j++) {
/* 590 */       int sOffset = sourceOffset;
/* 591 */       int dOffset = destOffset;
/* 594 */       int i = 0;
/* 595 */       while (i < dwidth && (dOffset & 0x1F) != 0) {
/* 596 */         int selement = sourceData[sOffset >> 5];
/* 597 */         int val = selement >> 31 - (sOffset & 0x1F) & 0x1;
/* 599 */         int k = dOffset >> 5;
/* 600 */         int dshift = 31 - (dOffset & 0x1F);
/* 601 */         int delement = destData[k];
/* 602 */         delement |= val << dshift;
/* 603 */         destData[k] = delement;
/* 605 */         sOffset += incr1;
/* 606 */         dOffset++;
/* 607 */         i++;
/*     */       } 
/* 610 */       int dindex = dOffset >> 5;
/* 611 */       if ((incr1 & 0x1F) == 0) {
/* 612 */         int shift = 31 - (sOffset & 0x5);
/* 613 */         int offset = sOffset >> 5;
/* 614 */         int incr = incr1 >> 5;
/* 616 */         while (i < dwidth - 31) {
/* 617 */           int delement = 0;
/* 618 */           for (int b = 31; b >= 0; b--) {
/* 619 */             int selement = sourceData[offset];
/* 620 */             int val = selement >> shift & 0x1;
/* 621 */             delement |= val << b;
/* 622 */             offset += incr;
/*     */           } 
/* 625 */           destData[dindex] = delement;
/* 627 */           sOffset += 32 * incr1;
/* 628 */           dOffset += 32;
/* 629 */           i += 32;
/* 630 */           dindex++;
/*     */         } 
/*     */       } else {
/* 633 */         while (i < dwidth - 31) {
/* 634 */           int delement = 0;
/* 635 */           for (int b = 31; b >= 0; b--) {
/* 636 */             int selement = sourceData[sOffset >> 5];
/* 637 */             int val = selement >> 31 - (sOffset & 0x1F) & 0x1;
/* 638 */             delement |= val << b;
/* 639 */             sOffset += incr1;
/*     */           } 
/* 642 */           destData[dindex] = delement;
/* 644 */           dOffset += 31;
/* 645 */           i += 32;
/* 646 */           dindex++;
/*     */         } 
/*     */       } 
/* 650 */       while (i < dwidth) {
/* 651 */         int selement = sourceData[sOffset >> 5];
/* 652 */         int val = selement >> 31 - (sOffset & 0x1F) & 0x1;
/* 654 */         dindex = dOffset >> 5;
/* 655 */         int dshift = 31 - (dOffset & 0x1F);
/* 656 */         int delement = destData[dindex];
/* 657 */         delement |= val << dshift;
/* 658 */         destData[dindex] = delement;
/* 660 */         sOffset += incr1;
/* 661 */         dOffset++;
/* 662 */         i++;
/*     */       } 
/* 665 */       sourceOffset += incr2;
/* 666 */       destOffset += 32 * destScanlineStride;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\TransposeBinaryOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */