/*      */ package com.sun.media.jai.opimage;
/*      */ 
/*      */ import com.sun.media.jai.util.ImageUtil;
/*      */ import com.sun.media.jai.util.JDKWorkarounds;
/*      */ import java.awt.Rectangle;
/*      */ import java.awt.image.ColorModel;
/*      */ import java.awt.image.Raster;
/*      */ import java.awt.image.RenderedImage;
/*      */ import java.awt.image.SampleModel;
/*      */ import java.awt.image.WritableRaster;
/*      */ import java.util.Map;
/*      */ import javax.media.jai.ImageLayout;
/*      */ import javax.media.jai.PointOpImage;
/*      */ import javax.media.jai.RasterAccessor;
/*      */ import javax.media.jai.RasterFactory;
/*      */ import javax.media.jai.RasterFormatTag;
/*      */ 
/*      */ final class CompositeOpImage extends PointOpImage {
/*      */   protected RenderedImage source1Alpha;
/*      */   
/*      */   protected RenderedImage source2Alpha;
/*      */   
/*      */   protected boolean alphaPremultiplied;
/*      */   
/*      */   private int aOffset;
/*      */   
/*      */   private int cOffset;
/*      */   
/*      */   private byte maxValueByte;
/*      */   
/*      */   private short maxValueShort;
/*      */   
/*      */   private int maxValue;
/*      */   
/*      */   private float invMaxValue;
/*      */   
/*      */   public CompositeOpImage(RenderedImage source1, RenderedImage source2, Map config, ImageLayout layout, RenderedImage source1Alpha, RenderedImage source2Alpha, boolean alphaPremultiplied, boolean alphaFirst) {
/*  117 */     super(source1, source2, layout, config, true);
/*      */     int bands;
/*  119 */     this.source1Alpha = source1Alpha;
/*  120 */     this.source2Alpha = source2Alpha;
/*  121 */     this.alphaPremultiplied = alphaPremultiplied;
/*  123 */     SampleModel sm = source1.getSampleModel();
/*  124 */     ColorModel cm = source1.getColorModel();
/*  125 */     int dtype = sm.getTransferType();
/*  127 */     if (cm instanceof java.awt.image.IndexColorModel) {
/*  128 */       bands = cm.getNumComponents();
/*      */     } else {
/*  130 */       bands = sm.getNumBands();
/*      */     } 
/*  132 */     bands++;
/*  134 */     if (this.sampleModel.getTransferType() != dtype || this.sampleModel.getNumBands() != bands) {
/*  141 */       this.sampleModel = RasterFactory.createComponentSampleModel(this.sampleModel, dtype, this.tileWidth, this.tileHeight, bands);
/*  144 */       if (this.colorModel != null && !JDKWorkarounds.areCompatibleDataModels(this.sampleModel, this.colorModel))
/*  147 */         this.colorModel = ImageUtil.getCompatibleColorModel(this.sampleModel, config); 
/*      */     } 
/*  152 */     this.aOffset = alphaFirst ? 0 : (bands - 1);
/*  153 */     this.cOffset = alphaFirst ? 1 : 0;
/*  155 */     switch (dtype) {
/*      */       case 0:
/*  157 */         this.maxValue = 255;
/*  158 */         this.maxValueByte = -1;
/*      */         break;
/*      */       case 1:
/*  161 */         this.maxValue = 65535;
/*  162 */         this.maxValueShort = -1;
/*      */         break;
/*      */       case 2:
/*  165 */         this.maxValue = 32767;
/*  166 */         this.maxValueShort = Short.MAX_VALUE;
/*      */         break;
/*      */       case 3:
/*  169 */         this.maxValue = Integer.MAX_VALUE;
/*      */         break;
/*      */     } 
/*  173 */     this.invMaxValue = 1.0F / this.maxValue;
/*      */   }
/*      */   
/*      */   protected void computeRect(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/*  188 */     RenderedImage[] renderedSources = (this.source2Alpha == null) ? new RenderedImage[3] : new RenderedImage[4];
/*  191 */     renderedSources[0] = (RenderedImage)getSourceImage(0);
/*  192 */     renderedSources[1] = (RenderedImage)getSourceImage(1);
/*  193 */     renderedSources[2] = this.source1Alpha;
/*  194 */     Raster source1AlphaRaster = this.source1Alpha.getData(destRect);
/*  195 */     Raster source2AlphaRaster = null;
/*  196 */     if (this.source2Alpha != null) {
/*  197 */       renderedSources[3] = this.source2Alpha;
/*  198 */       source2AlphaRaster = this.source2Alpha.getData(destRect);
/*      */     } 
/*  201 */     RasterFormatTag[] tags = RasterAccessor.findCompatibleTags(renderedSources, (RenderedImage)this);
/*  203 */     RasterAccessor s1 = new RasterAccessor(sources[0], destRect, tags[0], getSourceImage(0).getColorModel());
/*  205 */     RasterAccessor s2 = new RasterAccessor(sources[1], destRect, tags[1], getSourceImage(1).getColorModel());
/*  207 */     RasterAccessor a1 = new RasterAccessor(source1AlphaRaster, destRect, tags[2], this.source1Alpha.getColorModel());
/*  210 */     RasterAccessor a2 = null, d = null;
/*  212 */     if (this.source2Alpha != null) {
/*  213 */       a2 = new RasterAccessor(source2AlphaRaster, destRect, tags[3], this.source2Alpha.getColorModel());
/*  215 */       d = new RasterAccessor(dest, destRect, tags[4], getColorModel());
/*      */     } else {
/*  218 */       a2 = null;
/*  219 */       d = new RasterAccessor(dest, destRect, tags[3], getColorModel());
/*      */     } 
/*  223 */     switch (d.getDataType()) {
/*      */       case 0:
/*  225 */         byteLoop(s1, s2, a1, a2, d);
/*      */         break;
/*      */       case 1:
/*  228 */         ushortLoop(s1, s2, a1, a2, d);
/*      */         break;
/*      */       case 2:
/*  231 */         shortLoop(s1, s2, a1, a2, d);
/*      */         break;
/*      */       case 3:
/*  234 */         intLoop(s1, s2, a1, a2, d);
/*      */         break;
/*      */       case 4:
/*  237 */         floatLoop(s1, s2, a1, a2, d);
/*      */         break;
/*      */       case 5:
/*  240 */         doubleLoop(s1, s2, a1, a2, d);
/*      */         break;
/*      */     } 
/*  243 */     d.copyDataToRaster();
/*      */   }
/*      */   
/*      */   private void byteLoop(RasterAccessor src1, RasterAccessor src2, RasterAccessor afa1, RasterAccessor afa2, RasterAccessor dst) {
/*  281 */     int dwidth = dst.getWidth();
/*  282 */     int dheight = dst.getHeight();
/*  283 */     int numBands = src1.getNumBands();
/*  286 */     byte[][] s1 = src1.getByteDataArrays();
/*  287 */     int s1ss = src1.getScanlineStride();
/*  288 */     int s1ps = src1.getPixelStride();
/*  289 */     int[] s1bo = src1.getBandOffsets();
/*  292 */     byte[][] s2 = src2.getByteDataArrays();
/*  293 */     int s2ss = src2.getScanlineStride();
/*  294 */     int s2ps = src2.getPixelStride();
/*  295 */     int[] s2bo = src2.getBandOffsets();
/*  298 */     byte[] a1 = afa1.getByteDataArray(0);
/*  299 */     int a1ss = afa1.getScanlineStride();
/*  300 */     int a1ps = afa1.getPixelStride();
/*  301 */     int a1bo = afa1.getBandOffset(0);
/*  304 */     byte[] a2 = null;
/*  305 */     int a2ss = 0;
/*  306 */     int a2ps = 0;
/*  307 */     int a2bo = 0;
/*  308 */     if (afa2 != null) {
/*  309 */       a2 = afa2.getByteDataArray(0);
/*  310 */       a2ss = afa2.getScanlineStride();
/*  311 */       a2ps = afa2.getPixelStride();
/*  312 */       a2bo = afa2.getBandOffset(0);
/*      */     } 
/*  316 */     byte[][] d = dst.getByteDataArrays();
/*  317 */     int dss = dst.getScanlineStride();
/*  318 */     int dps = dst.getPixelStride();
/*  319 */     int[] dbo = dst.getBandOffsets();
/*  321 */     int s1so = 0, s2so = 0, a1so = 0, a2so = 0, dso = 0;
/*  323 */     if (this.alphaPremultiplied) {
/*  324 */       if (afa2 == null) {
/*  325 */         for (int h = 0; h < dheight; h++) {
/*  326 */           int s1po = s1so;
/*  327 */           int s2po = s2so;
/*  328 */           int a1po = a1so;
/*  329 */           int dpo = dso;
/*  331 */           for (int w = 0; w < dwidth; w++) {
/*  332 */             float t = 1.0F - (a1[a1po + a1bo] & 0xFF) * this.invMaxValue;
/*  335 */             d[this.aOffset][dpo + dbo[this.aOffset]] = this.maxValueByte;
/*  338 */             for (int b = 0; b < numBands; b++) {
/*  339 */               int i = b + this.cOffset;
/*  340 */               d[i][dpo + dbo[i]] = (byte)(int)((s1[b][s1po + s1bo[b]] & 0xFF) + (s2[b][s2po + s2bo[b]] & 0xFF) * t);
/*      */             } 
/*  345 */             s1po += s1ps;
/*  346 */             s2po += s2ps;
/*  347 */             a1po += a1ps;
/*  348 */             dpo += dps;
/*      */           } 
/*  351 */           s1so += s1ss;
/*  352 */           s2so += s2ss;
/*  353 */           a1so += a1ss;
/*  354 */           dso += dss;
/*      */         } 
/*      */       } else {
/*  357 */         for (int h = 0; h < dheight; h++) {
/*  358 */           int s1po = s1so;
/*  359 */           int s2po = s2so;
/*  360 */           int a1po = a1so;
/*  361 */           int a2po = a2so;
/*  362 */           int dpo = dso;
/*  364 */           for (int w = 0; w < dwidth; w++) {
/*  365 */             int t1 = a1[a1po + a1bo] & 0xFF;
/*  366 */             float t2 = 1.0F - t1 * this.invMaxValue;
/*  369 */             d[this.aOffset][dpo + dbo[this.aOffset]] = (byte)(int)(t1 + (a2[a2po + a2bo] & 0xFF) * t2);
/*  373 */             for (int b = 0; b < numBands; b++) {
/*  374 */               int i = b + this.cOffset;
/*  375 */               d[i][dpo + dbo[i]] = (byte)(int)((s1[b][s1po + s1bo[b]] & 0xFF) + (s2[b][s2po + s2bo[b]] & 0xFF) * t2);
/*      */             } 
/*  380 */             s1po += s1ps;
/*  381 */             s2po += s2ps;
/*  382 */             a1po += a1ps;
/*  383 */             a2po += a2ps;
/*  384 */             dpo += dps;
/*      */           } 
/*  387 */           s1so += s1ss;
/*  388 */           s2so += s2ss;
/*  389 */           a1so += a1ss;
/*  390 */           a2so += a2ss;
/*  391 */           dso += dss;
/*      */         } 
/*      */       } 
/*  395 */     } else if (afa2 == null) {
/*  396 */       for (int h = 0; h < dheight; h++) {
/*  397 */         int s1po = s1so;
/*  398 */         int s2po = s2so;
/*  399 */         int a1po = a1so;
/*  400 */         int dpo = dso;
/*  402 */         for (int w = 0; w < dwidth; w++) {
/*  403 */           float t1 = (a1[a1po + a1bo] & 0xFF) * this.invMaxValue;
/*  404 */           float t2 = 1.0F - t1;
/*  407 */           d[this.aOffset][dpo + dbo[this.aOffset]] = this.maxValueByte;
/*  410 */           for (int b = 0; b < numBands; b++) {
/*  411 */             int i = b + this.cOffset;
/*  412 */             d[i][dpo + dbo[i]] = (byte)(int)((s1[b][s1po + s1bo[b]] & 0xFF) * t1 + (s2[b][s2po + s2bo[b]] & 0xFF) * t2);
/*      */           } 
/*  417 */           s1po += s1ps;
/*  418 */           s2po += s2ps;
/*  419 */           a1po += a1ps;
/*  420 */           dpo += dps;
/*      */         } 
/*  423 */         s1so += s1ss;
/*  424 */         s2so += s2ss;
/*  425 */         a1so += a1ss;
/*  426 */         dso += dss;
/*      */       } 
/*      */     } else {
/*  429 */       for (int h = 0; h < dheight; h++) {
/*  430 */         int s1po = s1so;
/*  431 */         int s2po = s2so;
/*  432 */         int a1po = a1so;
/*  433 */         int a2po = a2so;
/*  434 */         int dpo = dso;
/*  436 */         for (int w = 0; w < dwidth; w++) {
/*      */           float t4, t5;
/*  437 */           int t1 = a1[a1po + a1bo] & 0xFF;
/*  438 */           float t2 = (1.0F - t1 * this.invMaxValue) * (a2[a2po + a2bo] & 0xFF);
/*  440 */           float t3 = t1 + t2;
/*  442 */           if (t3 == 0.0F) {
/*  443 */             t4 = 0.0F;
/*  444 */             t5 = 0.0F;
/*      */           } else {
/*  446 */             t4 = t1 / t3;
/*  447 */             t5 = t2 / t3;
/*      */           } 
/*  451 */           d[this.aOffset][dpo + dbo[this.aOffset]] = (byte)(int)t3;
/*  454 */           for (int b = 0; b < numBands; b++) {
/*  455 */             int i = b + this.cOffset;
/*  456 */             d[i][dpo + dbo[i]] = (byte)(int)((s1[b][s1po + s1bo[b]] & 0xFF) * t4 + (s2[b][s2po + s2bo[b]] & 0xFF) * t5);
/*      */           } 
/*  461 */           s1po += s1ps;
/*  462 */           s2po += s2ps;
/*  463 */           a1po += a1ps;
/*  464 */           a2po += a2ps;
/*  465 */           dpo += dps;
/*      */         } 
/*  468 */         s1so += s1ss;
/*  469 */         s2so += s2ss;
/*  470 */         a1so += a1ss;
/*  471 */         a2so += a2ss;
/*  472 */         dso += dss;
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void ushortLoop(RasterAccessor src1, RasterAccessor src2, RasterAccessor afa1, RasterAccessor afa2, RasterAccessor dst) {
/*  481 */     int dwidth = dst.getWidth();
/*  482 */     int dheight = dst.getHeight();
/*  483 */     int numBands = src1.getNumBands();
/*  486 */     short[][] s1 = src1.getShortDataArrays();
/*  487 */     int s1ss = src1.getScanlineStride();
/*  488 */     int s1ps = src1.getPixelStride();
/*  489 */     int[] s1bo = src1.getBandOffsets();
/*  492 */     short[][] s2 = src2.getShortDataArrays();
/*  493 */     int s2ss = src2.getScanlineStride();
/*  494 */     int s2ps = src2.getPixelStride();
/*  495 */     int[] s2bo = src2.getBandOffsets();
/*  498 */     short[] a1 = afa1.getShortDataArray(0);
/*  499 */     int a1ss = afa1.getScanlineStride();
/*  500 */     int a1ps = afa1.getPixelStride();
/*  501 */     int a1bo = afa1.getBandOffset(0);
/*  504 */     short[] a2 = null;
/*  505 */     int a2ss = 0;
/*  506 */     int a2ps = 0;
/*  507 */     int a2bo = 0;
/*  508 */     if (afa2 != null) {
/*  509 */       a2 = afa2.getShortDataArray(0);
/*  510 */       a2ss = afa2.getScanlineStride();
/*  511 */       a2ps = afa2.getPixelStride();
/*  512 */       a2bo = afa2.getBandOffset(0);
/*      */     } 
/*  516 */     short[][] d = dst.getShortDataArrays();
/*  517 */     int dss = dst.getScanlineStride();
/*  518 */     int dps = dst.getPixelStride();
/*  519 */     int[] dbo = dst.getBandOffsets();
/*  521 */     int s1so = 0, s2so = 0, a1so = 0, a2so = 0, dso = 0;
/*  523 */     if (this.alphaPremultiplied) {
/*  524 */       if (afa2 == null) {
/*  525 */         for (int h = 0; h < dheight; h++) {
/*  526 */           int s1po = s1so;
/*  527 */           int s2po = s2so;
/*  528 */           int a1po = a1so;
/*  529 */           int dpo = dso;
/*  531 */           for (int w = 0; w < dwidth; w++) {
/*  532 */             float t = 1.0F - (a1[a1po + a1bo] & 0xFFFF) * this.invMaxValue;
/*  535 */             d[this.aOffset][dpo + dbo[this.aOffset]] = this.maxValueShort;
/*  538 */             for (int b = 0; b < numBands; b++) {
/*  539 */               int i = b + this.cOffset;
/*  540 */               d[i][dpo + dbo[i]] = (short)(int)((s1[b][s1po + s1bo[b]] & 0xFFFF) + (s2[b][s2po + s2bo[b]] & 0xFFFF) * t);
/*      */             } 
/*  545 */             s1po += s1ps;
/*  546 */             s2po += s2ps;
/*  547 */             a1po += a1ps;
/*  548 */             dpo += dps;
/*      */           } 
/*  551 */           s1so += s1ss;
/*  552 */           s2so += s2ss;
/*  553 */           a1so += a1ss;
/*  554 */           dso += dss;
/*      */         } 
/*      */       } else {
/*  557 */         for (int h = 0; h < dheight; h++) {
/*  558 */           int s1po = s1so;
/*  559 */           int s2po = s2so;
/*  560 */           int a1po = a1so;
/*  561 */           int a2po = a2so;
/*  562 */           int dpo = dso;
/*  564 */           for (int w = 0; w < dwidth; w++) {
/*  565 */             int t1 = a1[a1po + a1bo] & 0xFFFF;
/*  566 */             float t2 = 1.0F - t1 * this.invMaxValue;
/*  569 */             d[this.aOffset][dpo + dbo[this.aOffset]] = (short)(int)(t1 + (a2[a2po + a2bo] & 0xFFFF) * t2);
/*  573 */             for (int b = 0; b < numBands; b++) {
/*  574 */               int i = b + this.cOffset;
/*  575 */               d[i][dpo + dbo[i]] = (short)(int)((s1[b][s1po + s1bo[b]] & 0xFFFF) + (s2[b][s2po + s2bo[b]] & 0xFFFF) * t2);
/*      */             } 
/*  580 */             s1po += s1ps;
/*  581 */             s2po += s2ps;
/*  582 */             a1po += a1ps;
/*  583 */             a2po += a2ps;
/*  584 */             dpo += dps;
/*      */           } 
/*  587 */           s1so += s1ss;
/*  588 */           s2so += s2ss;
/*  589 */           a1so += a1ss;
/*  590 */           a2so += a2ss;
/*  591 */           dso += dss;
/*      */         } 
/*      */       } 
/*  595 */     } else if (afa2 == null) {
/*  596 */       for (int h = 0; h < dheight; h++) {
/*  597 */         int s1po = s1so;
/*  598 */         int s2po = s2so;
/*  599 */         int a1po = a1so;
/*  600 */         int dpo = dso;
/*  602 */         for (int w = 0; w < dwidth; w++) {
/*  603 */           float t1 = (a1[a1po + a1bo] & 0xFFFF) * this.invMaxValue;
/*  604 */           float t2 = 1.0F - t1;
/*  607 */           d[this.aOffset][dpo + dbo[this.aOffset]] = this.maxValueShort;
/*  610 */           for (int b = 0; b < numBands; b++) {
/*  611 */             int i = b + this.cOffset;
/*  612 */             d[i][dpo + dbo[i]] = (short)(int)((s1[b][s1po + s1bo[b]] & 0xFFFF) * t1 + (s2[b][s2po + s2bo[b]] & 0xFFFF) * t2);
/*      */           } 
/*  617 */           s1po += s1ps;
/*  618 */           s2po += s2ps;
/*  619 */           a1po += a1ps;
/*  620 */           dpo += dps;
/*      */         } 
/*  623 */         s1so += s1ss;
/*  624 */         s2so += s2ss;
/*  625 */         a1so += a1ss;
/*  626 */         dso += dss;
/*      */       } 
/*      */     } else {
/*  629 */       for (int h = 0; h < dheight; h++) {
/*  630 */         int s1po = s1so;
/*  631 */         int s2po = s2so;
/*  632 */         int a1po = a1so;
/*  633 */         int a2po = a2so;
/*  634 */         int dpo = dso;
/*  636 */         for (int w = 0; w < dwidth; w++) {
/*      */           float t4, t5;
/*  637 */           int t1 = a1[a1po + a1bo] & 0xFFFF;
/*  638 */           float t2 = (1.0F - t1 * this.invMaxValue) * (a2[a2po + a2bo] & 0xFFFF);
/*  640 */           float t3 = t1 + t2;
/*  642 */           if (t3 == 0.0F) {
/*  643 */             t4 = 0.0F;
/*  644 */             t5 = 0.0F;
/*      */           } else {
/*  646 */             t4 = t1 / t3;
/*  647 */             t5 = t2 / t3;
/*      */           } 
/*  651 */           d[this.aOffset][dpo + dbo[this.aOffset]] = (short)(int)t3;
/*  654 */           for (int b = 0; b < numBands; b++) {
/*  655 */             int i = b + this.cOffset;
/*  656 */             d[i][dpo + dbo[i]] = (short)(int)((s1[b][s1po + s1bo[b]] & 0xFFFF) * t4 + (s2[b][s2po + s2bo[b]] & 0xFFFF) * t5);
/*      */           } 
/*  661 */           s1po += s1ps;
/*  662 */           s2po += s2ps;
/*  663 */           a1po += a1ps;
/*  664 */           a2po += a2ps;
/*  665 */           dpo += dps;
/*      */         } 
/*  668 */         s1so += s1ss;
/*  669 */         s2so += s2ss;
/*  670 */         a1so += a1ss;
/*  671 */         a2so += a2ss;
/*  672 */         dso += dss;
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void shortLoop(RasterAccessor src1, RasterAccessor src2, RasterAccessor afa1, RasterAccessor afa2, RasterAccessor dst) {
/*  681 */     int dwidth = dst.getWidth();
/*  682 */     int dheight = dst.getHeight();
/*  683 */     int numBands = src1.getNumBands();
/*  686 */     short[][] s1 = src1.getShortDataArrays();
/*  687 */     int s1ss = src1.getScanlineStride();
/*  688 */     int s1ps = src1.getPixelStride();
/*  689 */     int[] s1bo = src1.getBandOffsets();
/*  692 */     short[][] s2 = src2.getShortDataArrays();
/*  693 */     int s2ss = src2.getScanlineStride();
/*  694 */     int s2ps = src2.getPixelStride();
/*  695 */     int[] s2bo = src2.getBandOffsets();
/*  698 */     short[] a1 = afa1.getShortDataArray(0);
/*  699 */     int a1ss = afa1.getScanlineStride();
/*  700 */     int a1ps = afa1.getPixelStride();
/*  701 */     int a1bo = afa1.getBandOffset(0);
/*  704 */     short[] a2 = null;
/*  705 */     int a2ss = 0;
/*  706 */     int a2ps = 0;
/*  707 */     int a2bo = 0;
/*  708 */     if (afa2 != null) {
/*  709 */       a2 = afa2.getShortDataArray(0);
/*  710 */       a2ss = afa2.getScanlineStride();
/*  711 */       a2ps = afa2.getPixelStride();
/*  712 */       a2bo = afa2.getBandOffset(0);
/*      */     } 
/*  716 */     short[][] d = dst.getShortDataArrays();
/*  717 */     int dss = dst.getScanlineStride();
/*  718 */     int dps = dst.getPixelStride();
/*  719 */     int[] dbo = dst.getBandOffsets();
/*  721 */     int s1so = 0, s2so = 0, a1so = 0, a2so = 0, dso = 0;
/*  723 */     if (this.alphaPremultiplied) {
/*  724 */       if (afa2 == null) {
/*  725 */         for (int h = 0; h < dheight; h++) {
/*  726 */           int s1po = s1so;
/*  727 */           int s2po = s2so;
/*  728 */           int a1po = a1so;
/*  729 */           int dpo = dso;
/*  731 */           for (int w = 0; w < dwidth; w++) {
/*  732 */             float t = 1.0F - a1[a1po + a1bo] * this.invMaxValue;
/*  735 */             d[this.aOffset][dpo + dbo[this.aOffset]] = this.maxValueShort;
/*  738 */             for (int b = 0; b < numBands; b++) {
/*  739 */               int i = b + this.cOffset;
/*  740 */               d[i][dpo + dbo[i]] = (short)(int)(s1[b][s1po + s1bo[b]] + s2[b][s2po + s2bo[b]] * t);
/*      */             } 
/*  744 */             s1po += s1ps;
/*  745 */             s2po += s2ps;
/*  746 */             a1po += a1ps;
/*  747 */             dpo += dps;
/*      */           } 
/*  750 */           s1so += s1ss;
/*  751 */           s2so += s2ss;
/*  752 */           a1so += a1ss;
/*  753 */           dso += dss;
/*      */         } 
/*      */       } else {
/*  756 */         for (int h = 0; h < dheight; h++) {
/*  757 */           int s1po = s1so;
/*  758 */           int s2po = s2so;
/*  759 */           int a1po = a1so;
/*  760 */           int a2po = a2so;
/*  761 */           int dpo = dso;
/*  763 */           for (int w = 0; w < dwidth; w++) {
/*  764 */             int t1 = a1[a1po + a1bo];
/*  765 */             float t2 = 1.0F - t1 * this.invMaxValue;
/*  768 */             d[this.aOffset][dpo + dbo[this.aOffset]] = (short)(int)(t1 + a2[a2po + a2bo] * t2);
/*  772 */             for (int b = 0; b < numBands; b++) {
/*  773 */               int i = b + this.cOffset;
/*  774 */               d[i][dpo + dbo[i]] = (short)(int)(s1[b][s1po + s1bo[b]] + s2[b][s2po + s2bo[b]] * t2);
/*      */             } 
/*  779 */             s1po += s1ps;
/*  780 */             s2po += s2ps;
/*  781 */             a1po += a1ps;
/*  782 */             a2po += a2ps;
/*  783 */             dpo += dps;
/*      */           } 
/*  786 */           s1so += s1ss;
/*  787 */           s2so += s2ss;
/*  788 */           a1so += a1ss;
/*  789 */           a2so += a2ss;
/*  790 */           dso += dss;
/*      */         } 
/*      */       } 
/*  794 */     } else if (afa2 == null) {
/*  795 */       for (int h = 0; h < dheight; h++) {
/*  796 */         int s1po = s1so;
/*  797 */         int s2po = s2so;
/*  798 */         int a1po = a1so;
/*  799 */         int dpo = dso;
/*  801 */         for (int w = 0; w < dwidth; w++) {
/*  802 */           float t1 = a1[a1po + a1bo] * this.invMaxValue;
/*  803 */           float t2 = 1.0F - t1;
/*  806 */           d[this.aOffset][dpo + dbo[this.aOffset]] = this.maxValueShort;
/*  809 */           for (int b = 0; b < numBands; b++) {
/*  810 */             int i = b + this.cOffset;
/*  811 */             d[i][dpo + dbo[i]] = (short)(int)(s1[b][s1po + s1bo[b]] * t1 + s2[b][s2po + s2bo[b]] * t2);
/*      */           } 
/*  816 */           s1po += s1ps;
/*  817 */           s2po += s2ps;
/*  818 */           a1po += a1ps;
/*  819 */           dpo += dps;
/*      */         } 
/*  822 */         s1so += s1ss;
/*  823 */         s2so += s2ss;
/*  824 */         a1so += a1ss;
/*  825 */         dso += dss;
/*      */       } 
/*      */     } else {
/*  828 */       for (int h = 0; h < dheight; h++) {
/*  829 */         int s1po = s1so;
/*  830 */         int s2po = s2so;
/*  831 */         int a1po = a1so;
/*  832 */         int a2po = a2so;
/*  833 */         int dpo = dso;
/*  835 */         for (int w = 0; w < dwidth; w++) {
/*      */           float t4, t5;
/*  836 */           int t1 = a1[a1po + a1bo];
/*  837 */           float t2 = (1.0F - t1 * this.invMaxValue) * a2[a2po + a2bo];
/*  839 */           float t3 = t1 + t2;
/*  841 */           if (t3 == 0.0F) {
/*  842 */             t4 = 0.0F;
/*  843 */             t5 = 0.0F;
/*      */           } else {
/*  845 */             t4 = t1 / t3;
/*  846 */             t5 = t2 / t3;
/*      */           } 
/*  850 */           d[this.aOffset][dpo + dbo[this.aOffset]] = (short)(int)t3;
/*  853 */           for (int b = 0; b < numBands; b++) {
/*  854 */             int i = b + this.cOffset;
/*  855 */             d[i][dpo + dbo[i]] = (short)(int)(s1[b][s1po + s1bo[b]] * t4 + s2[b][s2po + s2bo[b]] * t5);
/*      */           } 
/*  860 */           s1po += s1ps;
/*  861 */           s2po += s2ps;
/*  862 */           a1po += a1ps;
/*  863 */           a2po += a2ps;
/*  864 */           dpo += dps;
/*      */         } 
/*  867 */         s1so += s1ss;
/*  868 */         s2so += s2ss;
/*  869 */         a1so += a1ss;
/*  870 */         a2so += a2ss;
/*  871 */         dso += dss;
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void intLoop(RasterAccessor src1, RasterAccessor src2, RasterAccessor afa1, RasterAccessor afa2, RasterAccessor dst) {
/*  880 */     int dwidth = dst.getWidth();
/*  881 */     int dheight = dst.getHeight();
/*  882 */     int numBands = src1.getNumBands();
/*  885 */     int[][] s1 = src1.getIntDataArrays();
/*  886 */     int s1ss = src1.getScanlineStride();
/*  887 */     int s1ps = src1.getPixelStride();
/*  888 */     int[] s1bo = src1.getBandOffsets();
/*  891 */     int[][] s2 = src2.getIntDataArrays();
/*  892 */     int s2ss = src2.getScanlineStride();
/*  893 */     int s2ps = src2.getPixelStride();
/*  894 */     int[] s2bo = src2.getBandOffsets();
/*  897 */     int[] a1 = afa1.getIntDataArray(0);
/*  898 */     int a1ss = afa1.getScanlineStride();
/*  899 */     int a1ps = afa1.getPixelStride();
/*  900 */     int a1bo = afa1.getBandOffset(0);
/*  903 */     int[] a2 = null;
/*  904 */     int a2ss = 0;
/*  905 */     int a2ps = 0;
/*  906 */     int a2bo = 0;
/*  907 */     if (afa2 != null) {
/*  908 */       a2 = afa2.getIntDataArray(0);
/*  909 */       a2ss = afa2.getScanlineStride();
/*  910 */       a2ps = afa2.getPixelStride();
/*  911 */       a2bo = afa2.getBandOffset(0);
/*      */     } 
/*  915 */     int[][] d = dst.getIntDataArrays();
/*  916 */     int dss = dst.getScanlineStride();
/*  917 */     int dps = dst.getPixelStride();
/*  918 */     int[] dbo = dst.getBandOffsets();
/*  920 */     int s1so = 0, s2so = 0, a1so = 0, a2so = 0, dso = 0;
/*  922 */     if (this.alphaPremultiplied) {
/*  923 */       if (afa2 == null) {
/*  924 */         for (int h = 0; h < dheight; h++) {
/*  925 */           int s1po = s1so;
/*  926 */           int s2po = s2so;
/*  927 */           int a1po = a1so;
/*  928 */           int dpo = dso;
/*  930 */           for (int w = 0; w < dwidth; w++) {
/*  931 */             float t = 1.0F - a1[a1po + a1bo] * this.invMaxValue;
/*  934 */             d[this.aOffset][dpo + dbo[this.aOffset]] = this.maxValue;
/*  937 */             for (int b = 0; b < numBands; b++) {
/*  938 */               int i = b + this.cOffset;
/*  939 */               d[i][dpo + dbo[i]] = (int)(s1[b][s1po + s1bo[b]] + s2[b][s2po + s2bo[b]] * t);
/*      */             } 
/*  943 */             s1po += s1ps;
/*  944 */             s2po += s2ps;
/*  945 */             a1po += a1ps;
/*  946 */             dpo += dps;
/*      */           } 
/*  949 */           s1so += s1ss;
/*  950 */           s2so += s2ss;
/*  951 */           a1so += a1ss;
/*  952 */           dso += dss;
/*      */         } 
/*      */       } else {
/*  955 */         for (int h = 0; h < dheight; h++) {
/*  956 */           int s1po = s1so;
/*  957 */           int s2po = s2so;
/*  958 */           int a1po = a1so;
/*  959 */           int a2po = a2so;
/*  960 */           int dpo = dso;
/*  962 */           for (int w = 0; w < dwidth; w++) {
/*  963 */             int t1 = a1[a1po + a1bo];
/*  964 */             float t2 = 1.0F - t1 * this.invMaxValue;
/*  967 */             d[this.aOffset][dpo + dbo[this.aOffset]] = (int)(t1 + a2[a2po + a2bo] * t2);
/*  971 */             for (int b = 0; b < numBands; b++) {
/*  972 */               int i = b + this.cOffset;
/*  973 */               d[i][dpo + dbo[i]] = (int)(s1[b][s1po + s1bo[b]] + s2[b][s2po + s2bo[b]] * t2);
/*      */             } 
/*  977 */             s1po += s1ps;
/*  978 */             s2po += s2ps;
/*  979 */             a1po += a1ps;
/*  980 */             a2po += a2ps;
/*  981 */             dpo += dps;
/*      */           } 
/*  984 */           s1so += s1ss;
/*  985 */           s2so += s2ss;
/*  986 */           a1so += a1ss;
/*  987 */           a2so += a2ss;
/*  988 */           dso += dss;
/*      */         } 
/*      */       } 
/*  992 */     } else if (afa2 == null) {
/*  993 */       for (int h = 0; h < dheight; h++) {
/*  994 */         int s1po = s1so;
/*  995 */         int s2po = s2so;
/*  996 */         int a1po = a1so;
/*  997 */         int dpo = dso;
/*  999 */         for (int w = 0; w < dwidth; w++) {
/* 1000 */           float t1 = a1[a1po + a1bo] * this.invMaxValue;
/* 1001 */           float t2 = 1.0F - t1;
/* 1004 */           d[this.aOffset][dpo + dbo[this.aOffset]] = this.maxValue;
/* 1007 */           for (int b = 0; b < numBands; b++) {
/* 1008 */             int i = b + this.cOffset;
/* 1009 */             d[i][dpo + dbo[i]] = (int)(s1[b][s1po + s1bo[b]] * t1 + s2[b][s2po + s2bo[b]] * t2);
/*      */           } 
/* 1014 */           s1po += s1ps;
/* 1015 */           s2po += s2ps;
/* 1016 */           a1po += a1ps;
/* 1017 */           dpo += dps;
/*      */         } 
/* 1020 */         s1so += s1ss;
/* 1021 */         s2so += s2ss;
/* 1022 */         a1so += a1ss;
/* 1023 */         dso += dss;
/*      */       } 
/*      */     } else {
/* 1026 */       for (int h = 0; h < dheight; h++) {
/* 1027 */         int s1po = s1so;
/* 1028 */         int s2po = s2so;
/* 1029 */         int a1po = a1so;
/* 1030 */         int a2po = a2so;
/* 1031 */         int dpo = dso;
/* 1033 */         for (int w = 0; w < dwidth; w++) {
/*      */           float t4, t5;
/* 1034 */           int t1 = a1[a1po + a1bo];
/* 1035 */           float t2 = (1.0F - t1 * this.invMaxValue) * a2[a2po + a2bo];
/* 1037 */           float t3 = t1 + t2;
/* 1039 */           if (t3 == 0.0F) {
/* 1040 */             t4 = 0.0F;
/* 1041 */             t5 = 0.0F;
/*      */           } else {
/* 1043 */             t4 = t1 / t3;
/* 1044 */             t5 = t2 / t3;
/*      */           } 
/* 1048 */           d[this.aOffset][dpo + dbo[this.aOffset]] = (int)t3;
/* 1051 */           for (int b = 0; b < numBands; b++) {
/* 1052 */             int i = b + this.cOffset;
/* 1053 */             d[i][dpo + dbo[i]] = (int)(s1[b][s1po + s1bo[b]] * t4 + s2[b][s2po + s2bo[b]] * t5);
/*      */           } 
/* 1057 */           s1po += s1ps;
/* 1058 */           s2po += s2ps;
/* 1059 */           a1po += a1ps;
/* 1060 */           a2po += a2ps;
/* 1061 */           dpo += dps;
/*      */         } 
/* 1064 */         s1so += s1ss;
/* 1065 */         s2so += s2ss;
/* 1066 */         a1so += a1ss;
/* 1067 */         a2so += a2ss;
/* 1068 */         dso += dss;
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void floatLoop(RasterAccessor src1, RasterAccessor src2, RasterAccessor afa1, RasterAccessor afa2, RasterAccessor dst) {
/* 1077 */     int dwidth = dst.getWidth();
/* 1078 */     int dheight = dst.getHeight();
/* 1079 */     int numBands = src1.getNumBands();
/* 1082 */     float[][] s1 = src1.getFloatDataArrays();
/* 1083 */     int s1ss = src1.getScanlineStride();
/* 1084 */     int s1ps = src1.getPixelStride();
/* 1085 */     int[] s1bo = src1.getBandOffsets();
/* 1088 */     float[][] s2 = src2.getFloatDataArrays();
/* 1089 */     int s2ss = src2.getScanlineStride();
/* 1090 */     int s2ps = src2.getPixelStride();
/* 1091 */     int[] s2bo = src2.getBandOffsets();
/* 1094 */     float[] a1 = afa1.getFloatDataArray(0);
/* 1095 */     int a1ss = afa1.getScanlineStride();
/* 1096 */     int a1ps = afa1.getPixelStride();
/* 1097 */     int a1bo = afa1.getBandOffset(0);
/* 1100 */     float[] a2 = null;
/* 1101 */     int a2ss = 0;
/* 1102 */     int a2ps = 0;
/* 1103 */     int a2bo = 0;
/* 1104 */     if (afa2 != null) {
/* 1105 */       a2 = afa2.getFloatDataArray(0);
/* 1106 */       a2ss = afa2.getScanlineStride();
/* 1107 */       a2ps = afa2.getPixelStride();
/* 1108 */       a2bo = afa2.getBandOffset(0);
/*      */     } 
/* 1112 */     float[][] d = dst.getFloatDataArrays();
/* 1113 */     int dss = dst.getScanlineStride();
/* 1114 */     int dps = dst.getPixelStride();
/* 1115 */     int[] dbo = dst.getBandOffsets();
/* 1117 */     int s1so = 0, s2so = 0, a1so = 0, a2so = 0, dso = 0;
/* 1119 */     float invMaxValue = 2.938736E-39F;
/* 1120 */     if (this.alphaPremultiplied) {
/* 1121 */       if (afa2 == null) {
/* 1122 */         for (int h = 0; h < dheight; h++) {
/* 1123 */           int s1po = s1so;
/* 1124 */           int s2po = s2so;
/* 1125 */           int a1po = a1so;
/* 1126 */           int dpo = dso;
/* 1128 */           for (int w = 0; w < dwidth; w++) {
/* 1129 */             float t = 1.0F - a1[a1po + a1bo] * invMaxValue;
/* 1132 */             d[this.aOffset][dpo + dbo[this.aOffset]] = Float.MAX_VALUE;
/* 1135 */             for (int b = 0; b < numBands; b++) {
/* 1136 */               int i = b + this.cOffset;
/* 1137 */               d[i][dpo + dbo[i]] = s1[b][s1po + s1bo[b]] + s2[b][s2po + s2bo[b]] * t;
/*      */             } 
/* 1141 */             s1po += s1ps;
/* 1142 */             s2po += s2ps;
/* 1143 */             a1po += a1ps;
/* 1144 */             dpo += dps;
/*      */           } 
/* 1147 */           s1so += s1ss;
/* 1148 */           s2so += s2ss;
/* 1149 */           a1so += a1ss;
/* 1150 */           dso += dss;
/*      */         } 
/*      */       } else {
/* 1153 */         for (int h = 0; h < dheight; h++) {
/* 1154 */           int s1po = s1so;
/* 1155 */           int s2po = s2so;
/* 1156 */           int a1po = a1so;
/* 1157 */           int a2po = a2so;
/* 1158 */           int dpo = dso;
/* 1160 */           for (int w = 0; w < dwidth; w++) {
/* 1161 */             float t1 = a1[a1po + a1bo];
/* 1162 */             float t2 = 1.0F - t1 * invMaxValue;
/* 1165 */             d[this.aOffset][dpo + dbo[this.aOffset]] = t1 + a2[a2po + a2bo] * t2;
/* 1168 */             for (int b = 0; b < numBands; b++) {
/* 1169 */               int i = b + this.cOffset;
/* 1170 */               d[i][dpo + dbo[i]] = s1[b][s1po + s1bo[b]] + s2[b][s2po + s2bo[b]] * t2;
/*      */             } 
/* 1174 */             s1po += s1ps;
/* 1175 */             s2po += s2ps;
/* 1176 */             a1po += a1ps;
/* 1177 */             a2po += a2ps;
/* 1178 */             dpo += dps;
/*      */           } 
/* 1181 */           s1so += s1ss;
/* 1182 */           s2so += s2ss;
/* 1183 */           a1so += a1ss;
/* 1184 */           a2so += a2ss;
/* 1185 */           dso += dss;
/*      */         } 
/*      */       } 
/* 1189 */     } else if (afa2 == null) {
/* 1190 */       for (int h = 0; h < dheight; h++) {
/* 1191 */         int s1po = s1so;
/* 1192 */         int s2po = s2so;
/* 1193 */         int a1po = a1so;
/* 1194 */         int dpo = dso;
/* 1196 */         for (int w = 0; w < dwidth; w++) {
/* 1197 */           float t1 = a1[a1po + a1bo] * invMaxValue;
/* 1198 */           float t2 = 1.0F - t1;
/* 1201 */           d[this.aOffset][dpo + dbo[this.aOffset]] = Float.MAX_VALUE;
/* 1204 */           for (int b = 0; b < numBands; b++) {
/* 1205 */             int i = b + this.cOffset;
/* 1206 */             d[i][dpo + dbo[i]] = s1[b][s1po + s1bo[b]] * t1 + s2[b][s2po + s2bo[b]] * t2;
/*      */           } 
/* 1210 */           s1po += s1ps;
/* 1211 */           s2po += s2ps;
/* 1212 */           a1po += a1ps;
/* 1213 */           dpo += dps;
/*      */         } 
/* 1216 */         s1so += s1ss;
/* 1217 */         s2so += s2ss;
/* 1218 */         a1so += a1ss;
/* 1219 */         dso += dss;
/*      */       } 
/*      */     } else {
/* 1222 */       for (int h = 0; h < dheight; h++) {
/* 1223 */         int s1po = s1so;
/* 1224 */         int s2po = s2so;
/* 1225 */         int a1po = a1so;
/* 1226 */         int a2po = a2so;
/* 1227 */         int dpo = dso;
/* 1229 */         for (int w = 0; w < dwidth; w++) {
/* 1230 */           float t4, t5, t1 = a1[a1po + a1bo];
/* 1231 */           float t2 = (1.0F - t1 * invMaxValue) * a2[a2po + a2bo];
/* 1233 */           float t3 = t1 + t2;
/* 1235 */           if (t3 == 0.0F) {
/* 1236 */             t4 = 0.0F;
/* 1237 */             t5 = 0.0F;
/*      */           } else {
/* 1239 */             t4 = t1 / t3;
/* 1240 */             t5 = t2 / t3;
/*      */           } 
/* 1244 */           d[this.aOffset][dpo + dbo[this.aOffset]] = t3;
/* 1247 */           for (int b = 0; b < numBands; b++) {
/* 1248 */             int i = b + this.cOffset;
/* 1249 */             d[i][dpo + dbo[i]] = s1[b][s1po + s1bo[b]] * t4 + s2[b][s2po + s2bo[b]] * t5;
/*      */           } 
/* 1253 */           s1po += s1ps;
/* 1254 */           s2po += s2ps;
/* 1255 */           a1po += a1ps;
/* 1256 */           a2po += a2ps;
/* 1257 */           dpo += dps;
/*      */         } 
/* 1260 */         s1so += s1ss;
/* 1261 */         s2so += s2ss;
/* 1262 */         a1so += a1ss;
/* 1263 */         a2so += a2ss;
/* 1264 */         dso += dss;
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void doubleLoop(RasterAccessor src1, RasterAccessor src2, RasterAccessor afa1, RasterAccessor afa2, RasterAccessor dst) {
/* 1273 */     int dwidth = dst.getWidth();
/* 1274 */     int dheight = dst.getHeight();
/* 1275 */     int numBands = src1.getNumBands();
/* 1278 */     double[][] s1 = src1.getDoubleDataArrays();
/* 1279 */     int s1ss = src1.getScanlineStride();
/* 1280 */     int s1ps = src1.getPixelStride();
/* 1281 */     int[] s1bo = src1.getBandOffsets();
/* 1284 */     double[][] s2 = src2.getDoubleDataArrays();
/* 1285 */     int s2ss = src2.getScanlineStride();
/* 1286 */     int s2ps = src2.getPixelStride();
/* 1287 */     int[] s2bo = src2.getBandOffsets();
/* 1290 */     double[] a1 = afa1.getDoubleDataArray(0);
/* 1291 */     int a1ss = afa1.getScanlineStride();
/* 1292 */     int a1ps = afa1.getPixelStride();
/* 1293 */     int a1bo = afa1.getBandOffset(0);
/* 1296 */     double[] a2 = null;
/* 1297 */     int a2ss = 0;
/* 1298 */     int a2ps = 0;
/* 1299 */     int a2bo = 0;
/* 1300 */     if (afa2 != null) {
/* 1301 */       a2 = afa2.getDoubleDataArray(0);
/* 1302 */       a2ss = afa2.getScanlineStride();
/* 1303 */       a2ps = afa2.getPixelStride();
/* 1304 */       a2bo = afa2.getBandOffset(0);
/*      */     } 
/* 1308 */     double[][] d = dst.getDoubleDataArrays();
/* 1309 */     int dss = dst.getScanlineStride();
/* 1310 */     int dps = dst.getPixelStride();
/* 1311 */     int[] dbo = dst.getBandOffsets();
/* 1313 */     int s1so = 0, s2so = 0, a1so = 0, a2so = 0, dso = 0;
/* 1315 */     double invMaxValue = 5.562684646268003E-309D;
/* 1316 */     if (this.alphaPremultiplied) {
/* 1317 */       if (afa2 == null) {
/* 1318 */         for (int h = 0; h < dheight; h++) {
/* 1319 */           int s1po = s1so;
/* 1320 */           int s2po = s2so;
/* 1321 */           int a1po = a1so;
/* 1322 */           int dpo = dso;
/* 1324 */           for (int w = 0; w < dwidth; w++) {
/* 1325 */             double t = 1.0D - a1[a1po + a1bo] * invMaxValue;
/* 1328 */             d[this.aOffset][dpo + dbo[this.aOffset]] = Double.MAX_VALUE;
/* 1331 */             for (int b = 0; b < numBands; b++) {
/* 1332 */               int i = b + this.cOffset;
/* 1333 */               d[i][dpo + dbo[i]] = s1[b][s1po + s1bo[b]] + s2[b][s2po + s2bo[b]] * t;
/*      */             } 
/* 1337 */             s1po += s1ps;
/* 1338 */             s2po += s2ps;
/* 1339 */             a1po += a1ps;
/* 1340 */             dpo += dps;
/*      */           } 
/* 1343 */           s1so += s1ss;
/* 1344 */           s2so += s2ss;
/* 1345 */           a1so += a1ss;
/* 1346 */           dso += dss;
/*      */         } 
/*      */       } else {
/* 1349 */         for (int h = 0; h < dheight; h++) {
/* 1350 */           int s1po = s1so;
/* 1351 */           int s2po = s2so;
/* 1352 */           int a1po = a1so;
/* 1353 */           int a2po = a2so;
/* 1354 */           int dpo = dso;
/* 1356 */           for (int w = 0; w < dwidth; w++) {
/* 1357 */             double t1 = a1[a1po + a1bo];
/* 1358 */             double t2 = 1.0D - t1 * invMaxValue;
/* 1361 */             d[this.aOffset][dpo + dbo[this.aOffset]] = t1 + a2[a2po + a2bo] * t2;
/* 1364 */             for (int b = 0; b < numBands; b++) {
/* 1365 */               int i = b + this.cOffset;
/* 1366 */               d[i][dpo + dbo[i]] = s1[b][s1po + s1bo[b]] + s2[b][s2po + s2bo[b]] * t2;
/*      */             } 
/* 1370 */             s1po += s1ps;
/* 1371 */             s2po += s2ps;
/* 1372 */             a1po += a1ps;
/* 1373 */             a2po += a2ps;
/* 1374 */             dpo += dps;
/*      */           } 
/* 1377 */           s1so += s1ss;
/* 1378 */           s2so += s2ss;
/* 1379 */           a1so += a1ss;
/* 1380 */           a2so += a2ss;
/* 1381 */           dso += dss;
/*      */         } 
/*      */       } 
/* 1385 */     } else if (afa2 == null) {
/* 1386 */       for (int h = 0; h < dheight; h++) {
/* 1387 */         int s1po = s1so;
/* 1388 */         int s2po = s2so;
/* 1389 */         int a1po = a1so;
/* 1390 */         int dpo = dso;
/* 1392 */         for (int w = 0; w < dwidth; w++) {
/* 1393 */           double t1 = a1[a1po + a1bo] * invMaxValue;
/* 1394 */           double t2 = 1.0D - t1;
/* 1397 */           d[this.aOffset][dpo + dbo[this.aOffset]] = Double.MAX_VALUE;
/* 1400 */           for (int b = 0; b < numBands; b++) {
/* 1401 */             int i = b + this.cOffset;
/* 1402 */             d[i][dpo + dbo[i]] = s1[b][s1po + s1bo[b]] * t1 + s2[b][s2po + s2bo[b]] * t2;
/*      */           } 
/* 1406 */           s1po += s1ps;
/* 1407 */           s2po += s2ps;
/* 1408 */           a1po += a1ps;
/* 1409 */           dpo += dps;
/*      */         } 
/* 1412 */         s1so += s1ss;
/* 1413 */         s2so += s2ss;
/* 1414 */         a1so += a1ss;
/* 1415 */         dso += dss;
/*      */       } 
/*      */     } else {
/* 1418 */       for (int h = 0; h < dheight; h++) {
/* 1419 */         int s1po = s1so;
/* 1420 */         int s2po = s2so;
/* 1421 */         int a1po = a1so;
/* 1422 */         int a2po = a2so;
/* 1423 */         int dpo = dso;
/* 1425 */         for (int w = 0; w < dwidth; w++) {
/* 1426 */           double t4, t5, t1 = a1[a1po + a1bo];
/* 1427 */           double t2 = (1.0D - t1 * invMaxValue) * a2[a2po + a2bo];
/* 1429 */           double t3 = t1 + t2;
/* 1431 */           if (t3 == 0.0D) {
/* 1432 */             t4 = 0.0D;
/* 1433 */             t5 = 0.0D;
/*      */           } else {
/* 1435 */             t4 = t1 / t3;
/* 1436 */             t5 = t2 / t3;
/*      */           } 
/* 1440 */           d[this.aOffset][dpo + dbo[this.aOffset]] = t3;
/* 1443 */           for (int b = 0; b < numBands; b++) {
/* 1444 */             int i = b + this.cOffset;
/* 1445 */             d[i][dpo + dbo[i]] = s1[b][s1po + s1bo[b]] * t4 + s2[b][s2po + s2bo[b]] * t5;
/*      */           } 
/* 1449 */           s1po += s1ps;
/* 1450 */           s2po += s2ps;
/* 1451 */           a1po += a1ps;
/* 1452 */           a2po += a2ps;
/* 1453 */           dpo += dps;
/*      */         } 
/* 1456 */         s1so += s1ss;
/* 1457 */         s2so += s2ss;
/* 1458 */         a1so += a1ss;
/* 1459 */         a2so += a2ss;
/* 1460 */         dso += dss;
/*      */       } 
/*      */     } 
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\CompositeOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */