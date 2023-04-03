/*      */ package com.sun.media.jai.opimage;
/*      */ 
/*      */ import com.sun.media.jai.util.ImageUtil;
/*      */ import com.sun.media.jai.util.JDKWorkarounds;
/*      */ import java.awt.Rectangle;
/*      */ import java.awt.image.ColorModel;
/*      */ import java.awt.image.IndexColorModel;
/*      */ import java.awt.image.MultiPixelPackedSampleModel;
/*      */ import java.awt.image.Raster;
/*      */ import java.awt.image.RenderedImage;
/*      */ import java.awt.image.SampleModel;
/*      */ import java.awt.image.WritableRaster;
/*      */ import java.lang.ref.SoftReference;
/*      */ import java.util.Arrays;
/*      */ import java.util.Map;
/*      */ import java.util.Vector;
/*      */ import javax.media.jai.ColorCube;
/*      */ import javax.media.jai.ImageLayout;
/*      */ import javax.media.jai.KernelJAI;
/*      */ import javax.media.jai.PointOpImage;
/*      */ import javax.media.jai.RasterAccessor;
/*      */ import javax.media.jai.RasterFactory;
/*      */ import javax.media.jai.RasterFormatTag;
/*      */ 
/*      */ final class OrderedDitherOpImage extends PointOpImage {
/*      */   private static final int TYPE_OD_GENERAL = 0;
/*      */   
/*      */   private static final int TYPE_OD_BYTE_LUT_3BAND = 1;
/*      */   
/*      */   private static final int TYPE_OD_BYTE_LUT_NBAND = 2;
/*      */   
/*      */   private static final int DITHER_LUT_LENGTH_MAX = 262144;
/*      */   
/*      */   private static final int DITHER_LUT_CACHE_LENGTH_MAX = 4;
/*      */   
/*   83 */   private static Vector ditherLUTCache = new Vector(0, 4);
/*      */   
/*   89 */   private int odType = 0;
/*      */   
/*      */   protected int numBands;
/*      */   
/*      */   protected int[] dims;
/*      */   
/*      */   protected int[] mults;
/*      */   
/*      */   protected int adjustedOffset;
/*      */   
/*      */   protected int maskWidth;
/*      */   
/*      */   protected int maskHeight;
/*      */   
/*      */   protected byte[][] maskDataByte;
/*      */   
/*      */   protected int[][] maskDataInt;
/*      */   
/*      */   protected long[][] maskDataLong;
/*      */   
/*      */   protected float[][] maskDataFloat;
/*      */   
/*  145 */   protected DitherLUT odLUT = null;
/*      */   
/*      */   private static ImageLayout layoutHelper(ImageLayout layout, RenderedImage source, ColorCube colorMap) {
/*      */     ImageLayout il;
/*  154 */     if (layout == null) {
/*  155 */       il = new ImageLayout(source);
/*      */     } else {
/*  157 */       il = (ImageLayout)layout.clone();
/*      */     } 
/*  161 */     SampleModel sm = il.getSampleModel(source);
/*  164 */     if (colorMap.getNumBands() == 1 && colorMap.getNumEntries() == 2 && !ImageUtil.isBinary(il.getSampleModel(source))) {
/*  167 */       sm = new MultiPixelPackedSampleModel(0, il.getTileWidth(source), il.getTileHeight(source), 1);
/*  171 */       il.setSampleModel(sm);
/*      */     } 
/*  175 */     if (sm.getNumBands() != 1) {
/*  177 */       sm = RasterFactory.createComponentSampleModel(sm, sm.getTransferType(), sm.getWidth(), sm.getHeight(), 1);
/*  182 */       il.setSampleModel(sm);
/*  185 */       ColorModel cm = il.getColorModel(null);
/*  186 */       if (cm != null && !JDKWorkarounds.areCompatibleDataModels(sm, cm))
/*  189 */         il.unsetValid(512); 
/*      */     } 
/*  199 */     if ((layout == null || !il.isValid(512)) && source.getSampleModel().getDataType() == 0 && il.getSampleModel(null).getDataType() == 0 && colorMap.getDataType() == 0 && colorMap.getNumBands() == 3) {
/*  204 */       ColorModel cm = source.getColorModel();
/*  205 */       if (cm == null || (cm != null && cm.getColorSpace().isCS_sRGB())) {
/*  207 */         int size = colorMap.getNumEntries();
/*  208 */         byte[][] cmap = new byte[3][256];
/*  209 */         for (int i = 0; i < 3; i++) {
/*  210 */           byte[] band = cmap[i];
/*  211 */           byte[] data = colorMap.getByteData(i);
/*  212 */           int offset = colorMap.getOffset(i);
/*  213 */           int end = offset + size;
/*      */           int j;
/*  214 */           for (j = 0; j < offset; j++)
/*  215 */             band[j] = 0; 
/*  217 */           for (j = offset; j < end; j++)
/*  218 */             band[j] = data[j - offset]; 
/*  220 */           for (j = end; j < 256; j++)
/*  221 */             band[j] = -1; 
/*      */         } 
/*  225 */         il.setColorModel(new IndexColorModel(8, 256, cmap[0], cmap[1], cmap[2]));
/*      */       } 
/*      */     } 
/*  231 */     return il;
/*      */   }
/*      */   
/*      */   public OrderedDitherOpImage(RenderedImage source, Map config, ImageLayout layout, ColorCube colorMap, KernelJAI[] ditherMask) {
/*  261 */     super(source, layoutHelper(layout, source, colorMap), config, true);
/*  265 */     this.numBands = colorMap.getNumBands();
/*  266 */     this.mults = (int[])colorMap.getMultipliers().clone();
/*  267 */     this.dims = (int[])colorMap.getDimsLessOne().clone();
/*  268 */     this.adjustedOffset = colorMap.getAdjustedOffset();
/*  271 */     this.maskWidth = ditherMask[0].getWidth();
/*  272 */     this.maskHeight = ditherMask[0].getHeight();
/*  276 */     initializeDitherData(this.sampleModel.getTransferType(), ditherMask);
/*  279 */     permitInPlaceOperation();
/*      */   }
/*      */   
/*      */   private class DitherLUT {
/*      */     private int[] dimsCache;
/*      */     
/*      */     private int[] multsCache;
/*      */     
/*      */     private byte[][] maskDataCache;
/*      */     
/*      */     public int ditherLUTBandStride;
/*      */     
/*      */     public int ditherLUTRowStride;
/*      */     
/*      */     public int ditherLUTColStride;
/*      */     
/*      */     public byte[] ditherLUT;
/*      */     
/*      */     private final OrderedDitherOpImage this$0;
/*      */     
/*      */     DitherLUT(OrderedDitherOpImage this$0, int[] dims, int[] mults, byte[][] maskData) {
/*  309 */       this.this$0 = this$0;
/*  311 */       this.dimsCache = (int[])dims.clone();
/*  312 */       this.multsCache = (int[])mults.clone();
/*  313 */       this.maskDataCache = new byte[maskData.length][];
/*  314 */       for (int i = 0; i < maskData.length; i++)
/*  315 */         this.maskDataCache[i] = (byte[])maskData[i].clone(); 
/*  319 */       this.ditherLUTColStride = 256;
/*  320 */       this.ditherLUTRowStride = this$0.maskWidth * this.ditherLUTColStride;
/*  321 */       this.ditherLUTBandStride = this$0.maskHeight * this.ditherLUTRowStride;
/*  337 */       this.ditherLUT = new byte[this$0.numBands * this.ditherLUTBandStride];
/*  339 */       int pDithBand = 0;
/*  340 */       int maskSize2D = this$0.maskWidth * this$0.maskHeight;
/*  341 */       for (int band = 0; band < this$0.numBands; band++) {
/*  342 */         int step = dims[band];
/*  343 */         int delta = mults[band];
/*  344 */         byte[] maskDataBand = maskData[band];
/*  345 */         int sum = 0;
/*  346 */         for (int gray = 0; gray < 256; gray++) {
/*  347 */           int tmp = sum;
/*  348 */           int frac = tmp & 0xFF;
/*  349 */           int bin = tmp >> 8;
/*  350 */           int lowVal = bin * delta;
/*  351 */           int highVal = lowVal + delta;
/*  352 */           int pDith = pDithBand + gray;
/*  353 */           for (int dcount = 0; dcount < maskSize2D; dcount++) {
/*  354 */             int threshold = maskDataBand[dcount] & 0xFF;
/*  355 */             if (frac > threshold) {
/*  356 */               this.ditherLUT[pDith] = (byte)(highVal & 0xFF);
/*      */             } else {
/*  358 */               this.ditherLUT[pDith] = (byte)(lowVal & 0xFF);
/*      */             } 
/*  360 */             pDith += 256;
/*      */           } 
/*  362 */           sum += step;
/*      */         } 
/*  364 */         pDithBand += this.ditherLUTBandStride;
/*      */       } 
/*      */     }
/*      */     
/*      */     public boolean equals(int[] dims, int[] mults, byte[][] maskData) {
/*  381 */       if (dims.length != this.dimsCache.length)
/*  382 */         return false; 
/*      */       int i;
/*  385 */       for (i = 0; i < dims.length; i++) {
/*  386 */         if (dims[i] != this.dimsCache[i])
/*  386 */           return false; 
/*      */       } 
/*  390 */       if (mults.length != this.multsCache.length)
/*  391 */         return false; 
/*  394 */       for (i = 0; i < mults.length; i++) {
/*  395 */         if (mults[i] != this.multsCache[i])
/*  395 */           return false; 
/*      */       } 
/*  399 */       if (maskData.length != this.this$0.maskDataByte.length)
/*  400 */         return false; 
/*  403 */       for (i = 0; i < maskData.length; i++) {
/*  404 */         if ((maskData[i]).length != (this.maskDataCache[i]).length)
/*  404 */           return false; 
/*  405 */         byte[] refData = this.maskDataCache[i];
/*  406 */         byte[] data = maskData[i];
/*  407 */         for (int j = 0; j < (maskData[i]).length; j++) {
/*  408 */           if (data[j] != refData[j])
/*  408 */             return false; 
/*      */         } 
/*      */       } 
/*  412 */       return true;
/*      */     }
/*      */   }
/*      */   
/*      */   private void initializeDitherData(int dataType, KernelJAI[] ditherMask) {
/*      */     int k;
/*      */     int j;
/*      */     long scaleFactor;
/*      */     int i;
/*      */     int m;
/*      */     int n;
/*  425 */     switch (dataType) {
/*      */       case 0:
/*  428 */         this.maskDataByte = new byte[ditherMask.length][];
/*  429 */         for (k = 0; k < this.maskDataByte.length; k++) {
/*  430 */           float[] maskData = ditherMask[k].getKernelData();
/*  431 */           this.maskDataByte[k] = new byte[maskData.length];
/*  432 */           for (int i1 = 0; i1 < maskData.length; i1++)
/*  433 */             this.maskDataByte[k][i1] = (byte)((int)(maskData[i1] * 255.0F) & 0xFF); 
/*      */         } 
/*  438 */         initializeDitherLUT();
/*      */         return;
/*      */       case 1:
/*      */       case 2:
/*  445 */         j = 65535;
/*  446 */         this.maskDataInt = new int[ditherMask.length][];
/*  447 */         for (m = 0; m < this.maskDataInt.length; m++) {
/*  448 */           float[] maskData = ditherMask[m].getKernelData();
/*  449 */           this.maskDataInt[m] = new int[maskData.length];
/*  450 */           for (int i1 = 0; i1 < maskData.length; i1++)
/*  451 */             this.maskDataInt[m][i1] = (int)(maskData[i1] * j); 
/*      */         } 
/*      */         return;
/*      */       case 3:
/*  459 */         scaleFactor = 4294967295L;
/*  461 */         this.maskDataLong = new long[ditherMask.length][];
/*  462 */         for (n = 0; n < this.maskDataLong.length; n++) {
/*  463 */           float[] maskData = ditherMask[n].getKernelData();
/*  464 */           this.maskDataLong[n] = new long[maskData.length];
/*  465 */           for (int i1 = 0; i1 < maskData.length; i1++)
/*  466 */             this.maskDataLong[n][i1] = (long)(maskData[i1] * (float)scaleFactor); 
/*      */         } 
/*      */         return;
/*      */       case 4:
/*      */       case 5:
/*  475 */         this.maskDataFloat = new float[ditherMask.length][];
/*  476 */         for (i = 0; i < this.maskDataFloat.length; i++)
/*  477 */           this.maskDataFloat[i] = ditherMask[i].getKernelData(); 
/*      */         return;
/*      */     } 
/*  483 */     throw new RuntimeException(JaiI18N.getString("OrderedDitherOpImage0"));
/*      */   }
/*      */   
/*      */   private synchronized void initializeDitherLUT() {
/*  493 */     if (this.numBands * this.maskHeight * this.maskWidth * 256 > 262144) {
/*  494 */       this.odType = 0;
/*      */       return;
/*      */     } 
/*  500 */     this.odType = (this.numBands == 3) ? 1 : 2;
/*  504 */     int index = 0;
/*  505 */     while (index < ditherLUTCache.size()) {
/*  506 */       SoftReference lutRef = ditherLUTCache.get(index);
/*  507 */       DitherLUT lut = lutRef.get();
/*  508 */       if (lut == null) {
/*  511 */         ditherLUTCache.remove(index);
/*      */         continue;
/*      */       } 
/*  513 */       if (lut.equals(this.dims, this.mults, this.maskDataByte)) {
/*  515 */         this.odLUT = lut;
/*      */         break;
/*      */       } 
/*  519 */       index++;
/*      */     } 
/*  524 */     if (this.odLUT == null) {
/*  525 */       this.odLUT = new DitherLUT(this, this.dims, this.mults, this.maskDataByte);
/*  527 */       if (ditherLUTCache.size() < 4)
/*  528 */         ditherLUTCache.add(new SoftReference(this.odLUT)); 
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void computeRect(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/*  545 */     RasterFormatTag[] formatTags = null;
/*  546 */     if (ImageUtil.isBinary(getSampleModel()) && !ImageUtil.isBinary(getSourceImage(0).getSampleModel())) {
/*  550 */       RenderedImage[] sourceArray = { (RenderedImage)getSourceImage(0) };
/*  552 */       RasterFormatTag[] sourceTags = RasterAccessor.findCompatibleTags(sourceArray, sourceArray[0]);
/*  554 */       RasterFormatTag[] destTags = RasterAccessor.findCompatibleTags(sourceArray, (RenderedImage)this);
/*  556 */       formatTags = new RasterFormatTag[] { sourceTags[0], destTags[1] };
/*      */     } else {
/*  559 */       formatTags = getFormatTags();
/*      */     } 
/*  562 */     RasterAccessor src = new RasterAccessor(sources[0], destRect, formatTags[0], getSource(0).getColorModel());
/*  565 */     RasterAccessor dst = new RasterAccessor(dest, destRect, formatTags[1], getColorModel());
/*  568 */     switch (src.getDataType()) {
/*      */       case 0:
/*  570 */         computeRectByte(src, dst);
/*      */         break;
/*      */       case 2:
/*  573 */         computeRectShort(src, dst);
/*      */         break;
/*      */       case 1:
/*  576 */         computeRectUShort(src, dst);
/*      */         break;
/*      */       case 3:
/*  579 */         computeRectInt(src, dst);
/*      */         break;
/*      */       case 4:
/*  582 */         computeRectFloat(src, dst);
/*      */         break;
/*      */       case 5:
/*  585 */         computeRectDouble(src, dst);
/*      */         break;
/*      */       default:
/*  588 */         throw new RuntimeException(JaiI18N.getString("OrderedDitherOpImage1"));
/*      */     } 
/*  591 */     dst.copyDataToRaster();
/*      */   }
/*      */   
/*      */   private void computeRectByte(RasterAccessor src, RasterAccessor dst) {
/*  598 */     int srcLineOffsets[], srcPixelOffsets[], dLineOffset, h, sbands = src.getNumBands();
/*  599 */     int sLineStride = src.getScanlineStride();
/*  600 */     int sPixelStride = src.getPixelStride();
/*  601 */     int[] sBandOffsets = src.getBandOffsets();
/*  602 */     byte[][] sData = src.getByteDataArrays();
/*  604 */     int dwidth = dst.getWidth();
/*  605 */     int dheight = dst.getHeight();
/*  606 */     int dLineStride = dst.getScanlineStride();
/*  607 */     int dPixelStride = dst.getPixelStride();
/*  608 */     int dBandOffset = dst.getBandOffset(0);
/*  609 */     byte[] dData = dst.getByteDataArray(0);
/*  611 */     int xMod = dst.getX() % this.maskWidth;
/*  612 */     int y0 = dst.getY();
/*  614 */     switch (this.odType) {
/*      */       case 1:
/*      */       case 2:
/*  617 */         srcLineOffsets = (int[])sBandOffsets.clone();
/*  618 */         srcPixelOffsets = (int[])srcLineOffsets.clone();
/*  619 */         dLineOffset = dBandOffset;
/*  621 */         for (h = 0; h < dheight; h++) {
/*  622 */           int yMod = (y0 + h) % this.maskHeight;
/*  624 */           if (this.odType == 1) {
/*  625 */             computeLineByteLUT3(sData, srcPixelOffsets, sPixelStride, dData, dLineOffset, dPixelStride, dwidth, xMod, yMod);
/*      */           } else {
/*  629 */             computeLineByteLUTN(sData, srcPixelOffsets, sPixelStride, dData, dLineOffset, dPixelStride, dwidth, xMod, yMod);
/*      */           } 
/*  634 */           for (int i = 0; i < sbands; i++) {
/*  635 */             srcLineOffsets[i] = srcLineOffsets[i] + sLineStride;
/*  636 */             srcPixelOffsets[i] = srcLineOffsets[i];
/*      */           } 
/*  638 */           dLineOffset += dLineStride;
/*      */         } 
/*      */         return;
/*      */     } 
/*  644 */     computeRectByteGeneral(sData, sBandOffsets, sLineStride, sPixelStride, dData, dBandOffset, dLineStride, dPixelStride, dwidth, dheight, xMod, y0);
/*      */   }
/*      */   
/*      */   private void computeLineByteLUT3(byte[][] sData, int[] sPixelOffsets, int sPixelStride, byte[] dData, int dPixelOffset, int dPixelStride, int dwidth, int xMod, int yMod) {
/*  661 */     int ditherLUTBandStride = this.odLUT.ditherLUTBandStride;
/*  662 */     int ditherLUTRowStride = this.odLUT.ditherLUTRowStride;
/*  663 */     int ditherLUTColStride = this.odLUT.ditherLUTColStride;
/*  664 */     byte[] ditherLUT = this.odLUT.ditherLUT;
/*  666 */     int base = this.adjustedOffset;
/*  668 */     int dlut0 = yMod * ditherLUTRowStride;
/*  669 */     int dlut1 = dlut0 + ditherLUTBandStride;
/*  670 */     int dlut2 = dlut1 + ditherLUTBandStride;
/*  672 */     int dlutLimit = dlut0 + ditherLUTRowStride;
/*  674 */     int xDelta = xMod * ditherLUTColStride;
/*  675 */     int pDtab0 = dlut0 + xDelta;
/*  676 */     int pDtab1 = dlut1 + xDelta;
/*  677 */     int pDtab2 = dlut2 + xDelta;
/*  679 */     byte[] sData0 = sData[0];
/*  680 */     byte[] sData1 = sData[1];
/*  681 */     byte[] sData2 = sData[2];
/*  683 */     for (int count = dwidth; count > 0; count--) {
/*  684 */       int idx = (ditherLUT[pDtab0 + (sData0[sPixelOffsets[0]] & 0xFF)] & 0xFF) + (ditherLUT[pDtab1 + (sData1[sPixelOffsets[1]] & 0xFF)] & 0xFF) + (ditherLUT[pDtab2 + (sData2[sPixelOffsets[2]] & 0xFF)] & 0xFF);
/*  689 */       dData[dPixelOffset] = (byte)(idx + base & 0xFF);
/*  691 */       sPixelOffsets[0] = sPixelOffsets[0] + sPixelStride;
/*  692 */       sPixelOffsets[1] = sPixelOffsets[1] + sPixelStride;
/*  693 */       sPixelOffsets[2] = sPixelOffsets[2] + sPixelStride;
/*  695 */       dPixelOffset += dPixelStride;
/*  697 */       pDtab0 += ditherLUTColStride;
/*  699 */       if (pDtab0 >= dlutLimit) {
/*  700 */         pDtab0 = dlut0;
/*  701 */         pDtab1 = dlut1;
/*  702 */         pDtab2 = dlut2;
/*      */       } else {
/*  704 */         pDtab1 += ditherLUTColStride;
/*  705 */         pDtab2 += ditherLUTColStride;
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void computeLineByteLUTN(byte[][] sData, int[] sPixelOffsets, int sPixelStride, byte[] dData, int dPixelOffset, int dPixelStride, int dwidth, int xMod, int yMod) {
/*  718 */     int ditherLUTBandStride = this.odLUT.ditherLUTBandStride;
/*  719 */     int ditherLUTRowStride = this.odLUT.ditherLUTRowStride;
/*  720 */     int ditherLUTColStride = this.odLUT.ditherLUTColStride;
/*  721 */     byte[] ditherLUT = this.odLUT.ditherLUT;
/*  723 */     int base = this.adjustedOffset;
/*  725 */     int dlutRow = yMod * ditherLUTRowStride;
/*  726 */     int dlutCol = dlutRow + xMod * ditherLUTColStride;
/*  727 */     int dlutLimit = dlutRow + ditherLUTRowStride;
/*  729 */     for (int count = dwidth; count > 0; count--) {
/*  730 */       int dlutBand = dlutCol;
/*  731 */       int idx = base;
/*  732 */       for (int i = 0; i < this.numBands; i++) {
/*  733 */         idx += ditherLUT[dlutBand + (sData[i][sPixelOffsets[i]] & 0xFF)] & 0xFF;
/*  735 */         dlutBand += ditherLUTBandStride;
/*  736 */         sPixelOffsets[i] = sPixelOffsets[i] + sPixelStride;
/*      */       } 
/*  739 */       dData[dPixelOffset] = (byte)(idx & 0xFF);
/*  741 */       dPixelOffset += dPixelStride;
/*  743 */       dlutCol += ditherLUTColStride;
/*  745 */       if (dlutCol >= dlutLimit)
/*  746 */         dlutCol = dlutRow; 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void computeRectByteGeneral(byte[][] sData, int[] sBandOffsets, int sLineStride, int sPixelStride, byte[] dData, int dBandOffset, int dLineStride, int dPixelStride, int dwidth, int dheight, int xMod, int y0) {
/*  761 */     if (this.adjustedOffset > 0)
/*  762 */       Arrays.fill(dData, (byte)(this.adjustedOffset & 0xFF)); 
/*  765 */     int sbands = sBandOffsets.length;
/*  766 */     for (int b = 0; b < sbands; b++) {
/*  767 */       byte[] s = sData[b];
/*  768 */       byte[] d = dData;
/*  770 */       byte[] maskData = this.maskDataByte[b];
/*  772 */       int sLineOffset = sBandOffsets[b];
/*  773 */       int dLineOffset = dBandOffset;
/*  775 */       for (int h = 0; h < dheight; h++) {
/*  776 */         int yMod = (y0 + h) % this.maskHeight;
/*  780 */         int maskYBase = yMod * this.maskWidth;
/*  784 */         int maskLimit = maskYBase + this.maskWidth;
/*  788 */         int maskIndex = maskYBase + xMod;
/*  790 */         int sPixelOffset = sLineOffset;
/*  791 */         int dPixelOffset = dLineOffset;
/*  793 */         for (int w = 0; w < dwidth; w++) {
/*  794 */           int tmp = (s[sPixelOffset] & 0xFF) * this.dims[b];
/*  795 */           int frac = tmp & 0xFF;
/*  796 */           tmp >>= 8;
/*  797 */           if (frac > (maskData[maskIndex] & 0xFF))
/*  798 */             tmp++; 
/*  802 */           int result = (d[dPixelOffset] & 0xFF) + tmp * this.mults[b];
/*  803 */           d[dPixelOffset] = (byte)(result & 0xFF);
/*  805 */           sPixelOffset += sPixelStride;
/*  806 */           dPixelOffset += dPixelStride;
/*  808 */           if (++maskIndex >= maskLimit)
/*  809 */             maskIndex = maskYBase; 
/*      */         } 
/*  813 */         sLineOffset += sLineStride;
/*  814 */         dLineOffset += dLineStride;
/*      */       } 
/*      */     } 
/*  818 */     if (this.adjustedOffset < 0) {
/*  820 */       int length = dData.length;
/*  821 */       for (int i = 0; i < length; i++)
/*  822 */         dData[i] = (byte)((dData[i] & 0xFF) + this.adjustedOffset); 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void computeRectShort(RasterAccessor src, RasterAccessor dst) {
/*  831 */     int sbands = src.getNumBands();
/*  832 */     int sLineStride = src.getScanlineStride();
/*  833 */     int sPixelStride = src.getPixelStride();
/*  834 */     int[] sBandOffsets = src.getBandOffsets();
/*  835 */     short[][] sData = src.getShortDataArrays();
/*  837 */     int dwidth = dst.getWidth();
/*  838 */     int dheight = dst.getHeight();
/*  839 */     int dLineStride = dst.getScanlineStride();
/*  840 */     int dPixelStride = dst.getPixelStride();
/*  841 */     int dBandOffset = dst.getBandOffset(0);
/*  842 */     short[] dData = dst.getShortDataArray(0);
/*  846 */     if (this.adjustedOffset != 0)
/*  847 */       Arrays.fill(dData, (short)(this.adjustedOffset & 0xFFFF)); 
/*  850 */     int xMod = dst.getX() % this.maskWidth;
/*  851 */     int y0 = dst.getY();
/*  853 */     for (int b = 0; b < sbands; b++) {
/*  854 */       short[] s = sData[b];
/*  855 */       short[] d = dData;
/*  857 */       int[] maskData = this.maskDataInt[b];
/*  859 */       int sLineOffset = sBandOffsets[b];
/*  860 */       int dLineOffset = dBandOffset;
/*  862 */       for (int h = 0; h < dheight; h++) {
/*  863 */         int sPixelOffset = sLineOffset;
/*  864 */         int dPixelOffset = dLineOffset;
/*  866 */         sLineOffset += sLineStride;
/*  867 */         dLineOffset += dLineStride;
/*  871 */         int maskYBase = (y0 + h) % this.maskHeight * this.maskWidth;
/*  875 */         int maskLimit = maskYBase + this.maskWidth;
/*  879 */         int maskIndex = maskYBase + xMod;
/*  881 */         for (int w = 0; w < dwidth; w++) {
/*  882 */           int tmp = (s[sPixelOffset] - -32768) * this.dims[b];
/*  883 */           int frac = tmp & 0xFFFF;
/*  886 */           int result = (d[dPixelOffset] & 0xFFFF) + (tmp >> 16) * this.mults[b];
/*  888 */           if (frac > maskData[maskIndex])
/*  889 */             result += this.mults[b]; 
/*  891 */           d[dPixelOffset] = (short)(result & 0xFFFF);
/*  893 */           sPixelOffset += sPixelStride;
/*  894 */           dPixelOffset += dPixelStride;
/*  896 */           if (++maskIndex >= maskLimit)
/*  897 */             maskIndex = maskYBase; 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void computeRectUShort(RasterAccessor src, RasterAccessor dst) {
/*  908 */     int sbands = src.getNumBands();
/*  909 */     int sLineStride = src.getScanlineStride();
/*  910 */     int sPixelStride = src.getPixelStride();
/*  911 */     int[] sBandOffsets = src.getBandOffsets();
/*  912 */     short[][] sData = src.getShortDataArrays();
/*  914 */     int dwidth = dst.getWidth();
/*  915 */     int dheight = dst.getHeight();
/*  916 */     int dLineStride = dst.getScanlineStride();
/*  917 */     int dPixelStride = dst.getPixelStride();
/*  918 */     int dBandOffset = dst.getBandOffset(0);
/*  919 */     short[] dData = dst.getShortDataArray(0);
/*  923 */     if (this.adjustedOffset != 0)
/*  924 */       Arrays.fill(dData, (short)(this.adjustedOffset & 0xFFFF)); 
/*  927 */     int xMod = dst.getX() % this.maskWidth;
/*  928 */     int y0 = dst.getY();
/*  930 */     for (int b = 0; b < sbands; b++) {
/*  931 */       short[] s = sData[b];
/*  932 */       short[] d = dData;
/*  934 */       int[] maskData = this.maskDataInt[b];
/*  936 */       int sLineOffset = sBandOffsets[b];
/*  937 */       int dLineOffset = dBandOffset;
/*  939 */       for (int h = 0; h < dheight; h++) {
/*  940 */         int sPixelOffset = sLineOffset;
/*  941 */         int dPixelOffset = dLineOffset;
/*  943 */         sLineOffset += sLineStride;
/*  944 */         dLineOffset += dLineStride;
/*  948 */         int maskYBase = (y0 + h) % this.maskHeight * this.maskWidth;
/*  952 */         int maskLimit = maskYBase + this.maskWidth;
/*  956 */         int maskIndex = maskYBase + xMod;
/*  958 */         for (int w = 0; w < dwidth; w++) {
/*  959 */           int tmp = (s[sPixelOffset] & 0xFFFF) * this.dims[b];
/*  960 */           int frac = tmp & 0xFFFF;
/*  963 */           int result = (d[dPixelOffset] & 0xFFFF) + (tmp >> 16) * this.mults[b];
/*  965 */           if (frac > maskData[maskIndex])
/*  966 */             result += this.mults[b]; 
/*  968 */           d[dPixelOffset] = (short)(result & 0xFFFF);
/*  970 */           sPixelOffset += sPixelStride;
/*  971 */           dPixelOffset += dPixelStride;
/*  973 */           if (++maskIndex >= maskLimit)
/*  974 */             maskIndex = maskYBase; 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void computeRectInt(RasterAccessor src, RasterAccessor dst) {
/*  985 */     int sbands = src.getNumBands();
/*  986 */     int sLineStride = src.getScanlineStride();
/*  987 */     int sPixelStride = src.getPixelStride();
/*  988 */     int[] sBandOffsets = src.getBandOffsets();
/*  989 */     int[][] sData = src.getIntDataArrays();
/*  991 */     int dwidth = dst.getWidth();
/*  992 */     int dheight = dst.getHeight();
/*  993 */     int dLineStride = dst.getScanlineStride();
/*  994 */     int dPixelStride = dst.getPixelStride();
/*  995 */     int dBandOffset = dst.getBandOffset(0);
/*  996 */     int[] dData = dst.getIntDataArray(0);
/* 1000 */     if (this.adjustedOffset != 0)
/* 1001 */       Arrays.fill(dData, this.adjustedOffset); 
/* 1004 */     int xMod = dst.getX() % this.maskWidth;
/* 1005 */     int y0 = dst.getY();
/* 1007 */     for (int b = 0; b < sbands; b++) {
/* 1008 */       int[] s = sData[b];
/* 1009 */       int[] d = dData;
/* 1011 */       long[] maskData = this.maskDataLong[b];
/* 1013 */       int sLineOffset = sBandOffsets[b];
/* 1014 */       int dLineOffset = dBandOffset;
/* 1016 */       for (int h = 0; h < dheight; h++) {
/* 1017 */         int sPixelOffset = sLineOffset;
/* 1018 */         int dPixelOffset = dLineOffset;
/* 1020 */         sLineOffset += sLineStride;
/* 1021 */         dLineOffset += dLineStride;
/* 1025 */         int maskYBase = (y0 + h) % this.maskHeight * this.maskWidth;
/* 1029 */         int maskLimit = maskYBase + this.maskWidth;
/* 1033 */         int maskIndex = maskYBase + xMod;
/* 1035 */         for (int w = 0; w < dwidth; w++) {
/* 1036 */           long tmp = (s[sPixelOffset] - -2147483648L) * this.dims[b];
/* 1039 */           long frac = tmp & 0xFFFFFFFFFFFFFFFFL;
/* 1042 */           int result = d[dPixelOffset] + (int)(tmp >> 32L) * this.mults[b];
/* 1044 */           if (frac > maskData[maskIndex])
/* 1045 */             result += this.mults[b]; 
/* 1047 */           d[dPixelOffset] = result;
/* 1049 */           sPixelOffset += sPixelStride;
/* 1050 */           dPixelOffset += dPixelStride;
/* 1052 */           if (++maskIndex >= maskLimit)
/* 1053 */             maskIndex = maskYBase; 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void computeRectFloat(RasterAccessor src, RasterAccessor dst) {
/* 1064 */     int sbands = src.getNumBands();
/* 1065 */     int sLineStride = src.getScanlineStride();
/* 1066 */     int sPixelStride = src.getPixelStride();
/* 1067 */     int[] sBandOffsets = src.getBandOffsets();
/* 1068 */     float[][] sData = src.getFloatDataArrays();
/* 1070 */     int dwidth = dst.getWidth();
/* 1071 */     int dheight = dst.getHeight();
/* 1072 */     int dLineStride = dst.getScanlineStride();
/* 1073 */     int dPixelStride = dst.getPixelStride();
/* 1074 */     int dBandOffset = dst.getBandOffset(0);
/* 1075 */     float[] dData = dst.getFloatDataArray(0);
/* 1079 */     if (this.adjustedOffset != 0)
/* 1080 */       Arrays.fill(dData, this.adjustedOffset); 
/* 1083 */     int xMod = dst.getX() % this.maskWidth;
/* 1084 */     int y0 = dst.getY();
/* 1086 */     for (int b = 0; b < sbands; b++) {
/* 1087 */       float[] s = sData[b];
/* 1088 */       float[] d = dData;
/* 1090 */       float[] maskData = this.maskDataFloat[b];
/* 1092 */       int sLineOffset = sBandOffsets[b];
/* 1093 */       int dLineOffset = dBandOffset;
/* 1095 */       for (int h = 0; h < dheight; h++) {
/* 1096 */         int sPixelOffset = sLineOffset;
/* 1097 */         int dPixelOffset = dLineOffset;
/* 1099 */         sLineOffset += sLineStride;
/* 1100 */         dLineOffset += dLineStride;
/* 1104 */         int maskYBase = (y0 + h) % this.maskHeight * this.maskWidth;
/* 1108 */         int maskLimit = maskYBase + this.maskWidth;
/* 1112 */         int maskIndex = maskYBase + xMod;
/* 1114 */         for (int w = 0; w < dwidth; w++) {
/* 1115 */           int tmp = (int)(s[sPixelOffset] * this.dims[b]);
/* 1116 */           float frac = s[sPixelOffset] * this.dims[b] - tmp;
/* 1119 */           float result = d[dPixelOffset] + (tmp * this.mults[b]);
/* 1120 */           if (frac > maskData[maskIndex])
/* 1121 */             result += this.mults[b]; 
/* 1123 */           d[dPixelOffset] = result;
/* 1125 */           sPixelOffset += sPixelStride;
/* 1126 */           dPixelOffset += dPixelStride;
/* 1128 */           if (++maskIndex >= maskLimit)
/* 1129 */             maskIndex = maskYBase; 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void computeRectDouble(RasterAccessor src, RasterAccessor dst) {
/* 1140 */     int sbands = src.getNumBands();
/* 1141 */     int sLineStride = src.getScanlineStride();
/* 1142 */     int sPixelStride = src.getPixelStride();
/* 1143 */     int[] sBandOffsets = src.getBandOffsets();
/* 1144 */     double[][] sData = src.getDoubleDataArrays();
/* 1146 */     int dwidth = dst.getWidth();
/* 1147 */     int dheight = dst.getHeight();
/* 1148 */     int dLineStride = dst.getScanlineStride();
/* 1149 */     int dPixelStride = dst.getPixelStride();
/* 1150 */     int dBandOffset = dst.getBandOffset(0);
/* 1151 */     double[] dData = dst.getDoubleDataArray(0);
/* 1155 */     if (this.adjustedOffset != 0)
/* 1156 */       Arrays.fill(dData, this.adjustedOffset); 
/* 1159 */     int xMod = dst.getX() % this.maskWidth;
/* 1160 */     int y0 = dst.getY();
/* 1162 */     for (int b = 0; b < sbands; b++) {
/* 1163 */       double[] s = sData[b];
/* 1164 */       double[] d = dData;
/* 1166 */       float[] maskData = this.maskDataFloat[b];
/* 1168 */       int sLineOffset = sBandOffsets[b];
/* 1169 */       int dLineOffset = dBandOffset;
/* 1171 */       for (int h = 0; h < dheight; h++) {
/* 1172 */         int sPixelOffset = sLineOffset;
/* 1173 */         int dPixelOffset = dLineOffset;
/* 1175 */         sLineOffset += sLineStride;
/* 1176 */         dLineOffset += dLineStride;
/* 1180 */         int maskYBase = (y0 + h) % this.maskHeight * this.maskWidth;
/* 1184 */         int maskLimit = maskYBase + this.maskWidth;
/* 1188 */         int maskIndex = maskYBase + xMod;
/* 1190 */         for (int w = 0; w < dwidth; w++) {
/* 1191 */           int tmp = (int)(s[sPixelOffset] * this.dims[b]);
/* 1192 */           float frac = (float)(s[sPixelOffset] * this.dims[b] - tmp);
/* 1195 */           double result = d[dPixelOffset] + (tmp * this.mults[b]);
/* 1196 */           if (frac > maskData[maskIndex])
/* 1197 */             result += this.mults[b]; 
/* 1199 */           d[dPixelOffset] = result;
/* 1201 */           sPixelOffset += sPixelStride;
/* 1202 */           dPixelOffset += dPixelStride;
/* 1204 */           if (++maskIndex >= maskLimit)
/* 1205 */             maskIndex = maskYBase; 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\OrderedDitherOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */