/*      */ package com.sun.media.jai.opimage;
/*      */ 
/*      */ import java.awt.Rectangle;
/*      */ import java.awt.image.Raster;
/*      */ import java.awt.image.RenderedImage;
/*      */ import java.awt.image.WritableRaster;
/*      */ import java.util.Map;
/*      */ import javax.media.jai.ImageLayout;
/*      */ import javax.media.jai.PointOpImage;
/*      */ import javax.media.jai.RasterAccessor;
/*      */ import javax.media.jai.RasterFormatTag;
/*      */ 
/*      */ final class CompositeNoDestAlphaOpImage extends PointOpImage {
/*      */   private RenderedImage alpha1;
/*      */   
/*      */   private RenderedImage alpha2;
/*      */   
/*      */   private boolean premultiplied;
/*      */   
/*      */   private RasterFormatTag[] tags;
/*      */   
/*      */   public CompositeNoDestAlphaOpImage(RenderedImage source1, RenderedImage source2, Map config, ImageLayout layout, RenderedImage alpha1, RenderedImage alpha2, boolean premultiplied) {
/*   83 */     super(source1, source2, layout, config, true);
/*   85 */     this.alpha1 = alpha1;
/*   86 */     this.alpha2 = alpha2;
/*   87 */     this.premultiplied = premultiplied;
/*   89 */     this.tags = getFormatTags();
/*      */   }
/*      */   
/*      */   protected void computeRect(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/*  104 */     RasterAccessor d, s1 = new RasterAccessor(sources[0], destRect, this.tags[0], getSourceImage(0).getColorModel());
/*  107 */     RasterAccessor s2 = new RasterAccessor(sources[1], destRect, this.tags[1], getSourceImage(1).getColorModel());
/*  110 */     RasterAccessor a1 = new RasterAccessor(this.alpha1.getData(destRect), destRect, this.tags[2], this.alpha1.getColorModel());
/*  114 */     RasterAccessor a2 = null;
/*  115 */     if (this.alpha2 == null) {
/*  116 */       d = new RasterAccessor(dest, destRect, this.tags[3], getColorModel());
/*      */     } else {
/*  119 */       a2 = new RasterAccessor(this.alpha2.getData(destRect), destRect, this.tags[3], this.alpha2.getColorModel());
/*  121 */       d = new RasterAccessor(dest, destRect, this.tags[4], getColorModel());
/*      */     } 
/*  125 */     switch (d.getDataType()) {
/*      */       case 0:
/*  127 */         byteLoop(s1, s2, a1, a2, d);
/*      */         break;
/*      */       case 1:
/*  130 */         ushortLoop(s1, s2, a1, a2, d);
/*      */         break;
/*      */       case 2:
/*  133 */         shortLoop(s1, s2, a1, a2, d);
/*      */         break;
/*      */       case 3:
/*  136 */         intLoop(s1, s2, a1, a2, d);
/*      */         break;
/*      */       case 4:
/*  139 */         floatLoop(s1, s2, a1, a2, d);
/*      */         break;
/*      */       case 5:
/*  142 */         doubleLoop(s1, s2, a1, a2, d);
/*      */         break;
/*      */     } 
/*  146 */     if (d.isDataCopy()) {
/*  147 */       d.clampDataArrays();
/*  148 */       d.copyDataToRaster();
/*      */     } 
/*      */   }
/*      */   
/*      */   private void byteLoop(RasterAccessor s1, RasterAccessor s2, RasterAccessor a1, RasterAccessor a2, RasterAccessor d) {
/*  173 */     int s1LineStride = s1.getScanlineStride();
/*  174 */     int s1PixelStride = s1.getPixelStride();
/*  175 */     int[] s1BandOffsets = s1.getBandOffsets();
/*  176 */     byte[][] s1Data = s1.getByteDataArrays();
/*  179 */     int s2LineStride = s2.getScanlineStride();
/*  180 */     int s2PixelStride = s2.getPixelStride();
/*  181 */     int[] s2BandOffsets = s2.getBandOffsets();
/*  182 */     byte[][] s2Data = s2.getByteDataArrays();
/*  185 */     int a1LineStride = a1.getScanlineStride();
/*  186 */     int a1PixelStride = a1.getPixelStride();
/*  187 */     int a1BandOffset = a1.getBandOffset(0);
/*  188 */     byte[] a1Data = a1.getByteDataArray(0);
/*  191 */     int a2LineStride = 0;
/*  192 */     int a2PixelStride = 0;
/*  193 */     int a2BandOffset = 0;
/*  194 */     byte[] a2Data = null;
/*  195 */     if (this.alpha2 != null) {
/*  196 */       a2LineStride = a2.getScanlineStride();
/*  197 */       a2PixelStride = a2.getPixelStride();
/*  198 */       a2BandOffset = a2.getBandOffset(0);
/*  199 */       a2Data = a2.getByteDataArray(0);
/*      */     } 
/*  203 */     int dLineStride = d.getScanlineStride();
/*  204 */     int dPixelStride = d.getPixelStride();
/*  205 */     int[] dBandOffsets = d.getBandOffsets();
/*  206 */     byte[][] dData = d.getByteDataArrays();
/*  208 */     int dwidth = d.getWidth();
/*  209 */     int dheight = d.getHeight();
/*  210 */     int dbands = d.getNumBands();
/*  212 */     float invMax = 0.003921569F;
/*  214 */     int s1LineOffset = 0, s2LineOffset = 0;
/*  215 */     int a1LineOffset = 0, a2LineOffset = 0;
/*  216 */     int dLineOffset = 0;
/*  221 */     if (this.premultiplied) {
/*  224 */       for (int h = 0; h < dheight; h++) {
/*  225 */         int s1PixelOffset = s1LineOffset;
/*  226 */         int s2PixelOffset = s2LineOffset;
/*  227 */         int a1PixelOffset = a1LineOffset + a1BandOffset;
/*  228 */         int dPixelOffset = dLineOffset;
/*  230 */         s1LineOffset += s1LineStride;
/*  231 */         s2LineOffset += s2LineStride;
/*  232 */         a1LineOffset += a1LineStride;
/*  233 */         dLineOffset += dLineStride;
/*  235 */         for (int w = 0; w < dwidth; w++) {
/*  236 */           float t = 1.0F - (a1Data[a1PixelOffset] & 0xFF) * invMax;
/*  239 */           for (int b = 0; b < dbands; b++)
/*  240 */             dData[b][dPixelOffset + dBandOffsets[b]] = (byte)(int)((s1Data[b][s1PixelOffset + s1BandOffsets[b]] & 0xFF) + (s2Data[b][s2PixelOffset + s2BandOffsets[b]] & 0xFF) * t); 
/*  245 */           s1PixelOffset += s1PixelStride;
/*  246 */           s2PixelOffset += s2PixelStride;
/*  247 */           a1PixelOffset += a1PixelStride;
/*  248 */           dPixelOffset += dPixelStride;
/*      */         } 
/*      */       } 
/*  253 */     } else if (this.alpha2 == null) {
/*  256 */       for (int h = 0; h < dheight; h++) {
/*  257 */         int s1PixelOffset = s1LineOffset;
/*  258 */         int s2PixelOffset = s2LineOffset;
/*  259 */         int a1PixelOffset = a1LineOffset + a1BandOffset;
/*  260 */         int dPixelOffset = dLineOffset;
/*  262 */         s1LineOffset += s1LineStride;
/*  263 */         s2LineOffset += s2LineStride;
/*  264 */         a1LineOffset += a1LineStride;
/*  265 */         dLineOffset += dLineStride;
/*  267 */         for (int w = 0; w < dwidth; w++) {
/*  268 */           float t1 = (a1Data[a1PixelOffset] & 0xFF) * invMax;
/*  269 */           float t2 = 1.0F - t1;
/*  272 */           for (int b = 0; b < dbands; b++)
/*  273 */             dData[b][dPixelOffset + dBandOffsets[b]] = (byte)(int)((s1Data[b][s1PixelOffset + s1BandOffsets[b]] & 0xFF) * t1 + (s2Data[b][s2PixelOffset + s2BandOffsets[b]] & 0xFF) * t2); 
/*  278 */           s1PixelOffset += s1PixelStride;
/*  279 */           s2PixelOffset += s2PixelStride;
/*  280 */           a1PixelOffset += a1PixelStride;
/*  281 */           dPixelOffset += dPixelStride;
/*      */         } 
/*      */       } 
/*      */     } else {
/*  291 */       for (int h = 0; h < dheight; h++) {
/*  292 */         int s1PixelOffset = s1LineOffset;
/*  293 */         int s2PixelOffset = s2LineOffset;
/*  294 */         int a1PixelOffset = a1LineOffset + a1BandOffset;
/*  295 */         int a2PixelOffset = a2LineOffset + a2BandOffset;
/*  296 */         int dPixelOffset = dLineOffset;
/*  298 */         s1LineOffset += s1LineStride;
/*  299 */         s2LineOffset += s2LineStride;
/*  300 */         a1LineOffset += a1LineStride;
/*  301 */         a2LineOffset += a2LineStride;
/*  302 */         dLineOffset += dLineStride;
/*  304 */         for (int w = 0; w < dwidth; w++) {
/*      */           float t4, t5;
/*  305 */           int t1 = a1Data[a1PixelOffset] & 0xFF;
/*  306 */           float t2 = (a2Data[a2PixelOffset] & 0xFF) * (1.0F - t1 * invMax);
/*  308 */           float t3 = t1 + t2;
/*  310 */           if (t3 == 0.0F) {
/*  311 */             t4 = 0.0F;
/*  312 */             t5 = 0.0F;
/*      */           } else {
/*  314 */             t4 = t1 / t3;
/*  315 */             t5 = t2 / t3;
/*      */           } 
/*  319 */           for (int b = 0; b < dbands; b++)
/*  320 */             dData[b][dPixelOffset + dBandOffsets[b]] = (byte)(int)((s1Data[b][s1PixelOffset + s1BandOffsets[b]] & 0xFF) * t4 + (s2Data[b][s2PixelOffset + s2BandOffsets[b]] & 0xFF) * t5); 
/*  325 */           s1PixelOffset += s1PixelStride;
/*  326 */           s2PixelOffset += s2PixelStride;
/*  327 */           a1PixelOffset += a1PixelStride;
/*  328 */           a2PixelOffset += a2PixelStride;
/*  329 */           dPixelOffset += dPixelStride;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void ushortLoop(RasterAccessor s1, RasterAccessor s2, RasterAccessor a1, RasterAccessor a2, RasterAccessor d) {
/*  340 */     int s1LineStride = s1.getScanlineStride();
/*  341 */     int s1PixelStride = s1.getPixelStride();
/*  342 */     int[] s1BandOffsets = s1.getBandOffsets();
/*  343 */     short[][] s1Data = s1.getShortDataArrays();
/*  346 */     int s2LineStride = s2.getScanlineStride();
/*  347 */     int s2PixelStride = s2.getPixelStride();
/*  348 */     int[] s2BandOffsets = s2.getBandOffsets();
/*  349 */     short[][] s2Data = s2.getShortDataArrays();
/*  352 */     int a1LineStride = a1.getScanlineStride();
/*  353 */     int a1PixelStride = a1.getPixelStride();
/*  354 */     int a1BandOffset = a1.getBandOffset(0);
/*  355 */     short[] a1Data = a1.getShortDataArray(0);
/*  358 */     int a2LineStride = 0;
/*  359 */     int a2PixelStride = 0;
/*  360 */     int a2BandOffset = 0;
/*  361 */     short[] a2Data = null;
/*  362 */     if (this.alpha2 != null) {
/*  363 */       a2LineStride = a2.getScanlineStride();
/*  364 */       a2PixelStride = a2.getPixelStride();
/*  365 */       a2BandOffset = a2.getBandOffset(0);
/*  366 */       a2Data = a2.getShortDataArray(0);
/*      */     } 
/*  370 */     int dLineStride = d.getScanlineStride();
/*  371 */     int dPixelStride = d.getPixelStride();
/*  372 */     int[] dBandOffsets = d.getBandOffsets();
/*  373 */     short[][] dData = d.getShortDataArrays();
/*  375 */     int dwidth = d.getWidth();
/*  376 */     int dheight = d.getHeight();
/*  377 */     int dbands = d.getNumBands();
/*  379 */     float invMax = 1.5259022E-5F;
/*  381 */     int s1LineOffset = 0, s2LineOffset = 0;
/*  382 */     int a1LineOffset = 0, a2LineOffset = 0;
/*  383 */     int dLineOffset = 0;
/*  388 */     if (this.premultiplied) {
/*  391 */       for (int h = 0; h < dheight; h++) {
/*  392 */         int s1PixelOffset = s1LineOffset;
/*  393 */         int s2PixelOffset = s2LineOffset;
/*  394 */         int a1PixelOffset = a1LineOffset + a1BandOffset;
/*  395 */         int dPixelOffset = dLineOffset;
/*  397 */         s1LineOffset += s1LineStride;
/*  398 */         s2LineOffset += s2LineStride;
/*  399 */         a1LineOffset += a1LineStride;
/*  400 */         dLineOffset += dLineStride;
/*  402 */         for (int w = 0; w < dwidth; w++) {
/*  403 */           float t = 1.0F - (a1Data[a1PixelOffset] & 0xFFFF) * invMax;
/*  406 */           for (int b = 0; b < dbands; b++)
/*  407 */             dData[b][dPixelOffset + dBandOffsets[b]] = (short)(int)((s1Data[b][s1PixelOffset + s1BandOffsets[b]] & 0xFFFF) + (s2Data[b][s2PixelOffset + s2BandOffsets[b]] & 0xFFFF) * t); 
/*  412 */           s1PixelOffset += s1PixelStride;
/*  413 */           s2PixelOffset += s2PixelStride;
/*  414 */           a1PixelOffset += a1PixelStride;
/*  415 */           dPixelOffset += dPixelStride;
/*      */         } 
/*      */       } 
/*  420 */     } else if (this.alpha2 == null) {
/*  423 */       for (int h = 0; h < dheight; h++) {
/*  424 */         int s1PixelOffset = s1LineOffset;
/*  425 */         int s2PixelOffset = s2LineOffset;
/*  426 */         int a1PixelOffset = a1LineOffset + a1BandOffset;
/*  427 */         int dPixelOffset = dLineOffset;
/*  429 */         s1LineOffset += s1LineStride;
/*  430 */         s2LineOffset += s2LineStride;
/*  431 */         a1LineOffset += a1LineStride;
/*  432 */         dLineOffset += dLineStride;
/*  434 */         for (int w = 0; w < dwidth; w++) {
/*  435 */           float t1 = (a1Data[a1PixelOffset] & 0xFFFF) * invMax;
/*  436 */           float t2 = 1.0F - t1;
/*  439 */           for (int b = 0; b < dbands; b++)
/*  440 */             dData[b][dPixelOffset + dBandOffsets[b]] = (short)(int)((s1Data[b][s1PixelOffset + s1BandOffsets[b]] & 0xFFFF) * t1 + (s2Data[b][s2PixelOffset + s2BandOffsets[b]] & 0xFFFF) * t2); 
/*  445 */           s1PixelOffset += s1PixelStride;
/*  446 */           s2PixelOffset += s2PixelStride;
/*  447 */           a1PixelOffset += a1PixelStride;
/*  448 */           dPixelOffset += dPixelStride;
/*      */         } 
/*      */       } 
/*      */     } else {
/*  458 */       for (int h = 0; h < dheight; h++) {
/*  459 */         int s1PixelOffset = s1LineOffset;
/*  460 */         int s2PixelOffset = s2LineOffset;
/*  461 */         int a1PixelOffset = a1LineOffset + a1BandOffset;
/*  462 */         int a2PixelOffset = a2LineOffset + a2BandOffset;
/*  463 */         int dPixelOffset = dLineOffset;
/*  465 */         s1LineOffset += s1LineStride;
/*  466 */         s2LineOffset += s2LineStride;
/*  467 */         a1LineOffset += a1LineStride;
/*  468 */         a2LineOffset += a2LineStride;
/*  469 */         dLineOffset += dLineStride;
/*  471 */         for (int w = 0; w < dwidth; w++) {
/*      */           float t4, t5;
/*  472 */           int t1 = a1Data[a1PixelOffset] & 0xFFFF;
/*  473 */           float t2 = (a2Data[a2PixelOffset] & 0xFFFF) * (1.0F - t1 * invMax);
/*  475 */           float t3 = t1 + t2;
/*  477 */           if (t3 == 0.0F) {
/*  478 */             t4 = 0.0F;
/*  479 */             t5 = 0.0F;
/*      */           } else {
/*  481 */             t4 = t1 / t3;
/*  482 */             t5 = t2 / t3;
/*      */           } 
/*  486 */           for (int b = 0; b < dbands; b++)
/*  487 */             dData[b][dPixelOffset + dBandOffsets[b]] = (short)(int)((s1Data[b][s1PixelOffset + s1BandOffsets[b]] & 0xFFFF) * t4 + (s2Data[b][s2PixelOffset + s2BandOffsets[b]] & 0xFFFF) * t5); 
/*  492 */           s1PixelOffset += s1PixelStride;
/*  493 */           s2PixelOffset += s2PixelStride;
/*  494 */           a1PixelOffset += a1PixelStride;
/*  495 */           a2PixelOffset += a2PixelStride;
/*  496 */           dPixelOffset += dPixelStride;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void shortLoop(RasterAccessor s1, RasterAccessor s2, RasterAccessor a1, RasterAccessor a2, RasterAccessor d) {
/*  507 */     int s1LineStride = s1.getScanlineStride();
/*  508 */     int s1PixelStride = s1.getPixelStride();
/*  509 */     int[] s1BandOffsets = s1.getBandOffsets();
/*  510 */     short[][] s1Data = s1.getShortDataArrays();
/*  513 */     int s2LineStride = s2.getScanlineStride();
/*  514 */     int s2PixelStride = s2.getPixelStride();
/*  515 */     int[] s2BandOffsets = s2.getBandOffsets();
/*  516 */     short[][] s2Data = s2.getShortDataArrays();
/*  519 */     int a1LineStride = a1.getScanlineStride();
/*  520 */     int a1PixelStride = a1.getPixelStride();
/*  521 */     int a1BandOffset = a1.getBandOffset(0);
/*  522 */     short[] a1Data = a1.getShortDataArray(0);
/*  525 */     int a2LineStride = 0;
/*  526 */     int a2PixelStride = 0;
/*  527 */     int a2BandOffset = 0;
/*  528 */     short[] a2Data = null;
/*  529 */     if (this.alpha2 != null) {
/*  530 */       a2LineStride = a2.getScanlineStride();
/*  531 */       a2PixelStride = a2.getPixelStride();
/*  532 */       a2BandOffset = a2.getBandOffset(0);
/*  533 */       a2Data = a2.getShortDataArray(0);
/*      */     } 
/*  537 */     int dLineStride = d.getScanlineStride();
/*  538 */     int dPixelStride = d.getPixelStride();
/*  539 */     int[] dBandOffsets = d.getBandOffsets();
/*  540 */     short[][] dData = d.getShortDataArrays();
/*  542 */     int dwidth = d.getWidth();
/*  543 */     int dheight = d.getHeight();
/*  544 */     int dbands = d.getNumBands();
/*  546 */     float invMax = 3.051851E-5F;
/*  548 */     int s1LineOffset = 0, s2LineOffset = 0;
/*  549 */     int a1LineOffset = 0, a2LineOffset = 0;
/*  550 */     int dLineOffset = 0;
/*  555 */     if (this.premultiplied) {
/*  558 */       for (int h = 0; h < dheight; h++) {
/*  559 */         int s1PixelOffset = s1LineOffset;
/*  560 */         int s2PixelOffset = s2LineOffset;
/*  561 */         int a1PixelOffset = a1LineOffset + a1BandOffset;
/*  562 */         int dPixelOffset = dLineOffset;
/*  564 */         s1LineOffset += s1LineStride;
/*  565 */         s2LineOffset += s2LineStride;
/*  566 */         a1LineOffset += a1LineStride;
/*  567 */         dLineOffset += dLineStride;
/*  569 */         for (int w = 0; w < dwidth; w++) {
/*  570 */           float t = 1.0F - a1Data[a1PixelOffset] * invMax;
/*  573 */           for (int b = 0; b < dbands; b++)
/*  574 */             dData[b][dPixelOffset + dBandOffsets[b]] = (short)(int)(s1Data[b][s1PixelOffset + s1BandOffsets[b]] + s2Data[b][s2PixelOffset + s2BandOffsets[b]] * t); 
/*  579 */           s1PixelOffset += s1PixelStride;
/*  580 */           s2PixelOffset += s2PixelStride;
/*  581 */           a1PixelOffset += a1PixelStride;
/*  582 */           dPixelOffset += dPixelStride;
/*      */         } 
/*      */       } 
/*  587 */     } else if (this.alpha2 == null) {
/*  590 */       for (int h = 0; h < dheight; h++) {
/*  591 */         int s1PixelOffset = s1LineOffset;
/*  592 */         int s2PixelOffset = s2LineOffset;
/*  593 */         int a1PixelOffset = a1LineOffset + a1BandOffset;
/*  594 */         int dPixelOffset = dLineOffset;
/*  596 */         s1LineOffset += s1LineStride;
/*  597 */         s2LineOffset += s2LineStride;
/*  598 */         a1LineOffset += a1LineStride;
/*  599 */         dLineOffset += dLineStride;
/*  601 */         for (int w = 0; w < dwidth; w++) {
/*  602 */           float t1 = a1Data[a1PixelOffset] * invMax;
/*  603 */           float t2 = 1.0F - t1;
/*  606 */           for (int b = 0; b < dbands; b++)
/*  607 */             dData[b][dPixelOffset + dBandOffsets[b]] = (short)(int)(s1Data[b][s1PixelOffset + s1BandOffsets[b]] * t1 + s2Data[b][s2PixelOffset + s2BandOffsets[b]] * t2); 
/*  612 */           s1PixelOffset += s1PixelStride;
/*  613 */           s2PixelOffset += s2PixelStride;
/*  614 */           a1PixelOffset += a1PixelStride;
/*  615 */           dPixelOffset += dPixelStride;
/*      */         } 
/*      */       } 
/*      */     } else {
/*  625 */       for (int h = 0; h < dheight; h++) {
/*  626 */         int s1PixelOffset = s1LineOffset;
/*  627 */         int s2PixelOffset = s2LineOffset;
/*  628 */         int a1PixelOffset = a1LineOffset + a1BandOffset;
/*  629 */         int a2PixelOffset = a2LineOffset + a2BandOffset;
/*  630 */         int dPixelOffset = dLineOffset;
/*  632 */         s1LineOffset += s1LineStride;
/*  633 */         s2LineOffset += s2LineStride;
/*  634 */         a1LineOffset += a1LineStride;
/*  635 */         a2LineOffset += a2LineStride;
/*  636 */         dLineOffset += dLineStride;
/*  638 */         for (int w = 0; w < dwidth; w++) {
/*      */           float t4, t5;
/*  639 */           int t1 = a1Data[a1PixelOffset];
/*  640 */           float t2 = a2Data[a2PixelOffset] * (1.0F - t1 * invMax);
/*  641 */           float t3 = t1 + t2;
/*  643 */           if (t3 == 0.0F) {
/*  644 */             t4 = 0.0F;
/*  645 */             t5 = 0.0F;
/*      */           } else {
/*  647 */             t4 = t1 / t3;
/*  648 */             t5 = t2 / t3;
/*      */           } 
/*  652 */           for (int b = 0; b < dbands; b++)
/*  653 */             dData[b][dPixelOffset + dBandOffsets[b]] = (short)(int)(s1Data[b][s1PixelOffset + s1BandOffsets[b]] * t4 + s2Data[b][s2PixelOffset + s2BandOffsets[b]] * t5); 
/*  658 */           s1PixelOffset += s1PixelStride;
/*  659 */           s2PixelOffset += s2PixelStride;
/*  660 */           a1PixelOffset += a1PixelStride;
/*  661 */           a2PixelOffset += a2PixelStride;
/*  662 */           dPixelOffset += dPixelStride;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void intLoop(RasterAccessor s1, RasterAccessor s2, RasterAccessor a1, RasterAccessor a2, RasterAccessor d) {
/*  673 */     int s1LineStride = s1.getScanlineStride();
/*  674 */     int s1PixelStride = s1.getPixelStride();
/*  675 */     int[] s1BandOffsets = s1.getBandOffsets();
/*  676 */     int[][] s1Data = s1.getIntDataArrays();
/*  679 */     int s2LineStride = s2.getScanlineStride();
/*  680 */     int s2PixelStride = s2.getPixelStride();
/*  681 */     int[] s2BandOffsets = s2.getBandOffsets();
/*  682 */     int[][] s2Data = s2.getIntDataArrays();
/*  685 */     int a1LineStride = a1.getScanlineStride();
/*  686 */     int a1PixelStride = a1.getPixelStride();
/*  687 */     int a1BandOffset = a1.getBandOffset(0);
/*  688 */     int[] a1Data = a1.getIntDataArray(0);
/*  691 */     int a2LineStride = 0;
/*  692 */     int a2PixelStride = 0;
/*  693 */     int a2BandOffset = 0;
/*  694 */     int[] a2Data = null;
/*  695 */     if (this.alpha2 != null) {
/*  696 */       a2LineStride = a2.getScanlineStride();
/*  697 */       a2PixelStride = a2.getPixelStride();
/*  698 */       a2BandOffset = a2.getBandOffset(0);
/*  699 */       a2Data = a2.getIntDataArray(0);
/*      */     } 
/*  703 */     int dLineStride = d.getScanlineStride();
/*  704 */     int dPixelStride = d.getPixelStride();
/*  705 */     int[] dBandOffsets = d.getBandOffsets();
/*  706 */     int[][] dData = d.getIntDataArrays();
/*  708 */     int dwidth = d.getWidth();
/*  709 */     int dheight = d.getHeight();
/*  710 */     int dbands = d.getNumBands();
/*  712 */     float invMax = 4.656613E-10F;
/*  714 */     int s1LineOffset = 0, s2LineOffset = 0;
/*  715 */     int a1LineOffset = 0, a2LineOffset = 0;
/*  716 */     int dLineOffset = 0;
/*  721 */     if (this.premultiplied) {
/*  724 */       for (int h = 0; h < dheight; h++) {
/*  725 */         int s1PixelOffset = s1LineOffset;
/*  726 */         int s2PixelOffset = s2LineOffset;
/*  727 */         int a1PixelOffset = a1LineOffset + a1BandOffset;
/*  728 */         int dPixelOffset = dLineOffset;
/*  730 */         s1LineOffset += s1LineStride;
/*  731 */         s2LineOffset += s2LineStride;
/*  732 */         a1LineOffset += a1LineStride;
/*  733 */         dLineOffset += dLineStride;
/*  735 */         for (int w = 0; w < dwidth; w++) {
/*  736 */           float t = 1.0F - a1Data[a1PixelOffset] * invMax;
/*  739 */           for (int b = 0; b < dbands; b++)
/*  740 */             dData[b][dPixelOffset + dBandOffsets[b]] = (int)(s1Data[b][s1PixelOffset + s1BandOffsets[b]] + s2Data[b][s2PixelOffset + s2BandOffsets[b]] * t); 
/*  745 */           s1PixelOffset += s1PixelStride;
/*  746 */           s2PixelOffset += s2PixelStride;
/*  747 */           a1PixelOffset += a1PixelStride;
/*  748 */           dPixelOffset += dPixelStride;
/*      */         } 
/*      */       } 
/*  753 */     } else if (this.alpha2 == null) {
/*  756 */       for (int h = 0; h < dheight; h++) {
/*  757 */         int s1PixelOffset = s1LineOffset;
/*  758 */         int s2PixelOffset = s2LineOffset;
/*  759 */         int a1PixelOffset = a1LineOffset + a1BandOffset;
/*  760 */         int dPixelOffset = dLineOffset;
/*  762 */         s1LineOffset += s1LineStride;
/*  763 */         s2LineOffset += s2LineStride;
/*  764 */         a1LineOffset += a1LineStride;
/*  765 */         dLineOffset += dLineStride;
/*  767 */         for (int w = 0; w < dwidth; w++) {
/*  768 */           float t1 = a1Data[a1PixelOffset] * invMax;
/*  769 */           float t2 = 1.0F - t1;
/*  772 */           for (int b = 0; b < dbands; b++)
/*  773 */             dData[b][dPixelOffset + dBandOffsets[b]] = (int)(s1Data[b][s1PixelOffset + s1BandOffsets[b]] * t1 + s2Data[b][s2PixelOffset + s2BandOffsets[b]] * t2); 
/*  778 */           s1PixelOffset += s1PixelStride;
/*  779 */           s2PixelOffset += s2PixelStride;
/*  780 */           a1PixelOffset += a1PixelStride;
/*  781 */           dPixelOffset += dPixelStride;
/*      */         } 
/*      */       } 
/*      */     } else {
/*  791 */       for (int h = 0; h < dheight; h++) {
/*  792 */         int s1PixelOffset = s1LineOffset;
/*  793 */         int s2PixelOffset = s2LineOffset;
/*  794 */         int a1PixelOffset = a1LineOffset + a1BandOffset;
/*  795 */         int a2PixelOffset = a2LineOffset + a2BandOffset;
/*  796 */         int dPixelOffset = dLineOffset;
/*  798 */         s1LineOffset += s1LineStride;
/*  799 */         s2LineOffset += s2LineStride;
/*  800 */         a1LineOffset += a1LineStride;
/*  801 */         a2LineOffset += a2LineStride;
/*  802 */         dLineOffset += dLineStride;
/*  804 */         for (int w = 0; w < dwidth; w++) {
/*      */           float t4, t5;
/*  805 */           int t1 = a1Data[a1PixelOffset];
/*  806 */           float t2 = a2Data[a2PixelOffset] * (1.0F - t1 * invMax);
/*  807 */           float t3 = t1 + t2;
/*  809 */           if (t3 == 0.0F) {
/*  810 */             t4 = 0.0F;
/*  811 */             t5 = 0.0F;
/*      */           } else {
/*  813 */             t4 = t1 / t3;
/*  814 */             t5 = t2 / t3;
/*      */           } 
/*  818 */           for (int b = 0; b < dbands; b++)
/*  819 */             dData[b][dPixelOffset + dBandOffsets[b]] = (int)(s1Data[b][s1PixelOffset + s1BandOffsets[b]] * t4 + s2Data[b][s2PixelOffset + s2BandOffsets[b]] * t5); 
/*  824 */           s1PixelOffset += s1PixelStride;
/*  825 */           s2PixelOffset += s2PixelStride;
/*  826 */           a1PixelOffset += a1PixelStride;
/*  827 */           a2PixelOffset += a2PixelStride;
/*  828 */           dPixelOffset += dPixelStride;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void floatLoop(RasterAccessor s1, RasterAccessor s2, RasterAccessor a1, RasterAccessor a2, RasterAccessor d) {
/*  839 */     int s1LineStride = s1.getScanlineStride();
/*  840 */     int s1PixelStride = s1.getPixelStride();
/*  841 */     int[] s1BandOffsets = s1.getBandOffsets();
/*  842 */     float[][] s1Data = s1.getFloatDataArrays();
/*  845 */     int s2LineStride = s2.getScanlineStride();
/*  846 */     int s2PixelStride = s2.getPixelStride();
/*  847 */     int[] s2BandOffsets = s2.getBandOffsets();
/*  848 */     float[][] s2Data = s2.getFloatDataArrays();
/*  851 */     int a1LineStride = a1.getScanlineStride();
/*  852 */     int a1PixelStride = a1.getPixelStride();
/*  853 */     int a1BandOffset = a1.getBandOffset(0);
/*  854 */     float[] a1Data = a1.getFloatDataArray(0);
/*  857 */     int a2LineStride = 0;
/*  858 */     int a2PixelStride = 0;
/*  859 */     int a2BandOffset = 0;
/*  860 */     float[] a2Data = null;
/*  861 */     if (this.alpha2 != null) {
/*  862 */       a2LineStride = a2.getScanlineStride();
/*  863 */       a2PixelStride = a2.getPixelStride();
/*  864 */       a2BandOffset = a2.getBandOffset(0);
/*  865 */       a2Data = a2.getFloatDataArray(0);
/*      */     } 
/*  869 */     int dLineStride = d.getScanlineStride();
/*  870 */     int dPixelStride = d.getPixelStride();
/*  871 */     int[] dBandOffsets = d.getBandOffsets();
/*  872 */     float[][] dData = d.getFloatDataArrays();
/*  874 */     int dwidth = d.getWidth();
/*  875 */     int dheight = d.getHeight();
/*  876 */     int dbands = d.getNumBands();
/*  878 */     int s1LineOffset = 0, s2LineOffset = 0;
/*  879 */     int a1LineOffset = 0, a2LineOffset = 0;
/*  880 */     int dLineOffset = 0;
/*  885 */     if (this.premultiplied) {
/*  888 */       for (int h = 0; h < dheight; h++) {
/*  889 */         int s1PixelOffset = s1LineOffset;
/*  890 */         int s2PixelOffset = s2LineOffset;
/*  891 */         int a1PixelOffset = a1LineOffset + a1BandOffset;
/*  892 */         int dPixelOffset = dLineOffset;
/*  894 */         s1LineOffset += s1LineStride;
/*  895 */         s2LineOffset += s2LineStride;
/*  896 */         a1LineOffset += a1LineStride;
/*  897 */         dLineOffset += dLineStride;
/*  899 */         for (int w = 0; w < dwidth; w++) {
/*  900 */           float t = 1.0F - a1Data[a1PixelOffset];
/*  903 */           for (int b = 0; b < dbands; b++)
/*  904 */             dData[b][dPixelOffset + dBandOffsets[b]] = s1Data[b][s1PixelOffset + s1BandOffsets[b]] + s2Data[b][s2PixelOffset + s2BandOffsets[b]] * t; 
/*  909 */           s1PixelOffset += s1PixelStride;
/*  910 */           s2PixelOffset += s2PixelStride;
/*  911 */           a1PixelOffset += a1PixelStride;
/*  912 */           dPixelOffset += dPixelStride;
/*      */         } 
/*      */       } 
/*  917 */     } else if (this.alpha2 == null) {
/*  920 */       for (int h = 0; h < dheight; h++) {
/*  921 */         int s1PixelOffset = s1LineOffset;
/*  922 */         int s2PixelOffset = s2LineOffset;
/*  923 */         int a1PixelOffset = a1LineOffset + a1BandOffset;
/*  924 */         int dPixelOffset = dLineOffset;
/*  926 */         s1LineOffset += s1LineStride;
/*  927 */         s2LineOffset += s2LineStride;
/*  928 */         a1LineOffset += a1LineStride;
/*  929 */         dLineOffset += dLineStride;
/*  931 */         for (int w = 0; w < dwidth; w++) {
/*  932 */           float t1 = a1Data[a1PixelOffset];
/*  933 */           float t2 = 1.0F - t1;
/*  936 */           for (int b = 0; b < dbands; b++)
/*  937 */             dData[b][dPixelOffset + dBandOffsets[b]] = s1Data[b][s1PixelOffset + s1BandOffsets[b]] * t1 + s2Data[b][s2PixelOffset + s2BandOffsets[b]] * t2; 
/*  942 */           s1PixelOffset += s1PixelStride;
/*  943 */           s2PixelOffset += s2PixelStride;
/*  944 */           a1PixelOffset += a1PixelStride;
/*  945 */           dPixelOffset += dPixelStride;
/*      */         } 
/*      */       } 
/*      */     } else {
/*  954 */       for (int h = 0; h < dheight; h++) {
/*  955 */         int s1PixelOffset = s1LineOffset;
/*  956 */         int s2PixelOffset = s2LineOffset;
/*  957 */         int a1PixelOffset = a1LineOffset + a1BandOffset;
/*  958 */         int a2PixelOffset = a2LineOffset + a2BandOffset;
/*  959 */         int dPixelOffset = dLineOffset;
/*  961 */         s1LineOffset += s1LineStride;
/*  962 */         s2LineOffset += s2LineStride;
/*  963 */         a1LineOffset += a1LineStride;
/*  964 */         a2LineOffset += a2LineStride;
/*  965 */         dLineOffset += dLineStride;
/*  967 */         for (int w = 0; w < dwidth; w++) {
/*  968 */           float t4, t5, t1 = a1Data[a1PixelOffset];
/*  969 */           float t2 = a2Data[a2PixelOffset] * (1.0F - t1);
/*  970 */           float t3 = t1 + t2;
/*  972 */           if (t3 == 0.0F) {
/*  973 */             t4 = 0.0F;
/*  974 */             t5 = 0.0F;
/*      */           } else {
/*  976 */             t4 = t1 / t3;
/*  977 */             t5 = t2 / t3;
/*      */           } 
/*  981 */           for (int b = 0; b < dbands; b++)
/*  982 */             dData[b][dPixelOffset + dBandOffsets[b]] = s1Data[b][s1PixelOffset + s1BandOffsets[b]] * t4 + s2Data[b][s2PixelOffset + s2BandOffsets[b]] * t5; 
/*  987 */           s1PixelOffset += s1PixelStride;
/*  988 */           s2PixelOffset += s2PixelStride;
/*  989 */           a1PixelOffset += a1PixelStride;
/*  990 */           a2PixelOffset += a2PixelStride;
/*  991 */           dPixelOffset += dPixelStride;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void doubleLoop(RasterAccessor s1, RasterAccessor s2, RasterAccessor a1, RasterAccessor a2, RasterAccessor d) {
/* 1002 */     int s1LineStride = s1.getScanlineStride();
/* 1003 */     int s1PixelStride = s1.getPixelStride();
/* 1004 */     int[] s1BandOffsets = s1.getBandOffsets();
/* 1005 */     double[][] s1Data = s1.getDoubleDataArrays();
/* 1008 */     int s2LineStride = s2.getScanlineStride();
/* 1009 */     int s2PixelStride = s2.getPixelStride();
/* 1010 */     int[] s2BandOffsets = s2.getBandOffsets();
/* 1011 */     double[][] s2Data = s2.getDoubleDataArrays();
/* 1014 */     int a1LineStride = a1.getScanlineStride();
/* 1015 */     int a1PixelStride = a1.getPixelStride();
/* 1016 */     int a1BandOffset = a1.getBandOffset(0);
/* 1017 */     double[] a1Data = a1.getDoubleDataArray(0);
/* 1020 */     int a2LineStride = 0;
/* 1021 */     int a2PixelStride = 0;
/* 1022 */     int a2BandOffset = 0;
/* 1023 */     double[] a2Data = null;
/* 1024 */     if (this.alpha2 != null) {
/* 1025 */       a2LineStride = a2.getScanlineStride();
/* 1026 */       a2PixelStride = a2.getPixelStride();
/* 1027 */       a2BandOffset = a2.getBandOffset(0);
/* 1028 */       a2Data = a2.getDoubleDataArray(0);
/*      */     } 
/* 1032 */     int dLineStride = d.getScanlineStride();
/* 1033 */     int dPixelStride = d.getPixelStride();
/* 1034 */     int[] dBandOffsets = d.getBandOffsets();
/* 1035 */     double[][] dData = d.getDoubleDataArrays();
/* 1037 */     int dwidth = d.getWidth();
/* 1038 */     int dheight = d.getHeight();
/* 1039 */     int dbands = d.getNumBands();
/* 1041 */     int s1LineOffset = 0, s2LineOffset = 0;
/* 1042 */     int a1LineOffset = 0, a2LineOffset = 0;
/* 1043 */     int dLineOffset = 0;
/* 1048 */     if (this.premultiplied) {
/* 1051 */       for (int h = 0; h < dheight; h++) {
/* 1052 */         int s1PixelOffset = s1LineOffset;
/* 1053 */         int s2PixelOffset = s2LineOffset;
/* 1054 */         int a1PixelOffset = a1LineOffset + a1BandOffset;
/* 1055 */         int dPixelOffset = dLineOffset;
/* 1057 */         s1LineOffset += s1LineStride;
/* 1058 */         s2LineOffset += s2LineStride;
/* 1059 */         a1LineOffset += a1LineStride;
/* 1060 */         dLineOffset += dLineStride;
/* 1062 */         for (int w = 0; w < dwidth; w++) {
/* 1063 */           double t = 1.0D - a1Data[a1PixelOffset];
/* 1066 */           for (int b = 0; b < dbands; b++)
/* 1067 */             dData[b][dPixelOffset + dBandOffsets[b]] = s1Data[b][s1PixelOffset + s1BandOffsets[b]] + s2Data[b][s2PixelOffset + s2BandOffsets[b]] * t; 
/* 1072 */           s1PixelOffset += s1PixelStride;
/* 1073 */           s2PixelOffset += s2PixelStride;
/* 1074 */           a1PixelOffset += a1PixelStride;
/* 1075 */           dPixelOffset += dPixelStride;
/*      */         } 
/*      */       } 
/* 1080 */     } else if (this.alpha2 == null) {
/* 1083 */       for (int h = 0; h < dheight; h++) {
/* 1084 */         int s1PixelOffset = s1LineOffset;
/* 1085 */         int s2PixelOffset = s2LineOffset;
/* 1086 */         int a1PixelOffset = a1LineOffset + a1BandOffset;
/* 1087 */         int dPixelOffset = dLineOffset;
/* 1089 */         s1LineOffset += s1LineStride;
/* 1090 */         s2LineOffset += s2LineStride;
/* 1091 */         a1LineOffset += a1LineStride;
/* 1092 */         dLineOffset += dLineStride;
/* 1094 */         for (int w = 0; w < dwidth; w++) {
/* 1095 */           double t1 = a1Data[a1PixelOffset];
/* 1096 */           double t2 = 1.0D - t1;
/* 1099 */           for (int b = 0; b < dbands; b++)
/* 1100 */             dData[b][dPixelOffset + dBandOffsets[b]] = s1Data[b][s1PixelOffset + s1BandOffsets[b]] * t1 + s2Data[b][s2PixelOffset + s2BandOffsets[b]] * t2; 
/* 1105 */           s1PixelOffset += s1PixelStride;
/* 1106 */           s2PixelOffset += s2PixelStride;
/* 1107 */           a1PixelOffset += a1PixelStride;
/* 1108 */           dPixelOffset += dPixelStride;
/*      */         } 
/*      */       } 
/*      */     } else {
/* 1117 */       for (int h = 0; h < dheight; h++) {
/* 1118 */         int s1PixelOffset = s1LineOffset;
/* 1119 */         int s2PixelOffset = s2LineOffset;
/* 1120 */         int a1PixelOffset = a1LineOffset + a1BandOffset;
/* 1121 */         int a2PixelOffset = a2LineOffset + a2BandOffset;
/* 1122 */         int dPixelOffset = dLineOffset;
/* 1124 */         s1LineOffset += s1LineStride;
/* 1125 */         s2LineOffset += s2LineStride;
/* 1126 */         a1LineOffset += a1LineStride;
/* 1127 */         a2LineOffset += a2LineStride;
/* 1128 */         dLineOffset += dLineStride;
/* 1130 */         for (int w = 0; w < dwidth; w++) {
/* 1131 */           double t4, t5, t1 = a1Data[a1PixelOffset];
/* 1132 */           double t2 = a2Data[a2PixelOffset] * (1.0D - t1);
/* 1133 */           double t3 = t1 + t2;
/* 1135 */           if (t3 == 0.0D) {
/* 1136 */             t4 = 0.0D;
/* 1137 */             t5 = 0.0D;
/*      */           } else {
/* 1139 */             t4 = t1 / t3;
/* 1140 */             t5 = t2 / t3;
/*      */           } 
/* 1144 */           for (int b = 0; b < dbands; b++)
/* 1145 */             dData[b][dPixelOffset + dBandOffsets[b]] = s1Data[b][s1PixelOffset + s1BandOffsets[b]] * t4 + s2Data[b][s2PixelOffset + s2BandOffsets[b]] * t5; 
/* 1150 */           s1PixelOffset += s1PixelStride;
/* 1151 */           s2PixelOffset += s2PixelStride;
/* 1152 */           a1PixelOffset += a1PixelStride;
/* 1153 */           a2PixelOffset += a2PixelStride;
/* 1154 */           dPixelOffset += dPixelStride;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   protected synchronized RasterFormatTag[] getFormatTags() {
/*      */     RenderedImage[] ri;
/* 1164 */     if (this.alpha2 == null) {
/* 1165 */       ri = new RenderedImage[3];
/*      */     } else {
/* 1167 */       ri = new RenderedImage[4];
/* 1168 */       ri[3] = this.alpha2;
/*      */     } 
/* 1170 */     ri[0] = (RenderedImage)getSourceImage(0);
/* 1171 */     ri[1] = (RenderedImage)getSourceImage(1);
/* 1172 */     ri[2] = this.alpha1;
/* 1174 */     return RasterAccessor.findCompatibleTags(ri, (RenderedImage)this);
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\CompositeNoDestAlphaOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */