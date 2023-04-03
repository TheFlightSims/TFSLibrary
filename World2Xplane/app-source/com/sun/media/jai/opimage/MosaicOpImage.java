/*      */ package com.sun.media.jai.opimage;
/*      */ 
/*      */ import com.sun.media.jai.util.ImageUtil;
/*      */ import java.awt.Point;
/*      */ import java.awt.Rectangle;
/*      */ import java.awt.image.ColorModel;
/*      */ import java.awt.image.Raster;
/*      */ import java.awt.image.RenderedImage;
/*      */ import java.awt.image.SampleModel;
/*      */ import java.awt.image.WritableRaster;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Arrays;
/*      */ import java.util.Map;
/*      */ import java.util.Vector;
/*      */ import javax.media.jai.BorderExtender;
/*      */ import javax.media.jai.BorderExtenderConstant;
/*      */ import javax.media.jai.ImageLayout;
/*      */ import javax.media.jai.OpImage;
/*      */ import javax.media.jai.PlanarImage;
/*      */ import javax.media.jai.ROI;
/*      */ import javax.media.jai.RasterAccessor;
/*      */ import javax.media.jai.RasterFormatTag;
/*      */ import javax.media.jai.operator.MosaicDescriptor;
/*      */ import javax.media.jai.operator.MosaicType;
/*      */ 
/*      */ public class MosaicOpImage extends OpImage {
/*      */   private static final int WEIGHT_TYPE_ALPHA = 1;
/*      */   
/*      */   private static final int WEIGHT_TYPE_ROI = 2;
/*      */   
/*      */   private static final int WEIGHT_TYPE_THRESHOLD = 3;
/*      */   
/*      */   protected MosaicType mosaicType;
/*      */   
/*      */   protected PlanarImage[] sourceAlpha;
/*      */   
/*      */   protected ROI[] sourceROI;
/*      */   
/*      */   protected double[][] sourceThreshold;
/*      */   
/*      */   protected double[] backgroundValues;
/*      */   
/*      */   protected int numBands;
/*      */   
/*      */   protected int[] background;
/*      */   
/*      */   protected int[][] threshold;
/*      */   
/*      */   protected boolean isAlphaBitmask;
/*      */   
/*      */   private BorderExtender sourceExtender;
/*      */   
/*      */   private BorderExtender zeroExtender;
/*      */   
/*      */   private PlanarImage[] roiImage;
/*      */   
/*      */   private static final ImageLayout getLayout(Vector sources, ImageLayout layout) {
/*   67 */     RenderedImage source0 = null;
/*   68 */     SampleModel targetSM = null;
/*   69 */     ColorModel targetCM = null;
/*   72 */     int numSources = sources.size();
/*   74 */     if (numSources > 0) {
/*   76 */       source0 = sources.get(0);
/*   77 */       targetSM = source0.getSampleModel();
/*   78 */       targetCM = source0.getColorModel();
/*   79 */     } else if (layout != null && layout.isValid(268)) {
/*   84 */       targetSM = layout.getSampleModel(null);
/*   85 */       if (targetSM == null)
/*   86 */         throw new IllegalArgumentException(JaiI18N.getString("MosaicOpImage7")); 
/*      */     } else {
/*   90 */       throw new IllegalArgumentException(JaiI18N.getString("MosaicOpImage8"));
/*      */     } 
/*   95 */     int dataType = targetSM.getDataType();
/*   96 */     int numBands = targetSM.getNumBands();
/*   97 */     int sampleSize = targetSM.getSampleSize(0);
/*      */     int i;
/*  100 */     for (i = 1; i < numBands; i++) {
/*  101 */       if (targetSM.getSampleSize(i) != sampleSize)
/*  102 */         throw new IllegalArgumentException(JaiI18N.getString("MosaicOpImage1")); 
/*      */     } 
/*  108 */     if (numSources < 1)
/*  109 */       return (ImageLayout)layout.clone(); 
/*  113 */     for (i = 1; i < numSources; i++) {
/*  114 */       RenderedImage source = sources.get(i);
/*  115 */       SampleModel sourceSM = source.getSampleModel();
/*  118 */       if (sourceSM.getDataType() != dataType)
/*  119 */         throw new IllegalArgumentException(JaiI18N.getString("MosaicOpImage2")); 
/*  120 */       if (sourceSM.getNumBands() != numBands)
/*  121 */         throw new IllegalArgumentException(JaiI18N.getString("MosaicOpImage3")); 
/*  125 */       for (int j = 0; j < numBands; j++) {
/*  126 */         if (sourceSM.getSampleSize(j) != sampleSize)
/*  127 */           throw new IllegalArgumentException(JaiI18N.getString("MosaicOpImage1")); 
/*      */       } 
/*      */     } 
/*  133 */     ImageLayout mosaicLayout = (layout == null) ? new ImageLayout() : (ImageLayout)layout.clone();
/*  137 */     Rectangle mosaicBounds = new Rectangle();
/*  138 */     if (mosaicLayout.isValid(15)) {
/*  143 */       mosaicBounds.setBounds(mosaicLayout.getMinX(null), mosaicLayout.getMinY(null), mosaicLayout.getWidth(null), mosaicLayout.getHeight(null));
/*  147 */     } else if (numSources > 0) {
/*  149 */       mosaicBounds.setBounds(source0.getMinX(), source0.getMinY(), source0.getWidth(), source0.getHeight());
/*  151 */       for (int j = 1; j < numSources; j++) {
/*  152 */         RenderedImage source = sources.get(j);
/*  153 */         Rectangle sourceBounds = new Rectangle(source.getMinX(), source.getMinY(), source.getWidth(), source.getHeight());
/*  156 */         mosaicBounds = mosaicBounds.union(sourceBounds);
/*      */       } 
/*      */     } 
/*  161 */     mosaicLayout.setMinX(mosaicBounds.x);
/*  162 */     mosaicLayout.setMinY(mosaicBounds.y);
/*  163 */     mosaicLayout.setWidth(mosaicBounds.width);
/*  164 */     mosaicLayout.setHeight(mosaicBounds.height);
/*  167 */     if (mosaicLayout.isValid(256)) {
/*  169 */       SampleModel destSM = mosaicLayout.getSampleModel(null);
/*  172 */       boolean unsetSampleModel = (destSM.getNumBands() != numBands || destSM.getDataType() != dataType);
/*  177 */       for (int j = 0; !unsetSampleModel && j < numBands; j++) {
/*  178 */         if (destSM.getSampleSize(j) != sampleSize)
/*  179 */           unsetSampleModel = true; 
/*      */       } 
/*  184 */       if (unsetSampleModel)
/*  185 */         mosaicLayout.unsetValid(256); 
/*      */     } 
/*  189 */     return mosaicLayout;
/*      */   }
/*      */   
/*      */   public MosaicOpImage(Vector sources, ImageLayout layout, Map config, MosaicType mosaicType, PlanarImage[] sourceAlpha, ROI[] sourceROI, double[][] sourceThreshold, double[] backgroundValues) {
/*  200 */     super(sources, getLayout(sources, layout), config, true);
/*      */     double sourceExtensionConstant;
/*      */     this.isAlphaBitmask = false;
/*  203 */     this.numBands = this.sampleModel.getNumBands();
/*  206 */     int numSources = getNumSources();
/*  209 */     this.mosaicType = mosaicType;
/*  212 */     this.sourceAlpha = null;
/*  213 */     if (sourceAlpha != null) {
/*  215 */       for (int k = 0; k < sourceAlpha.length; k++) {
/*  216 */         if (sourceAlpha[k] != null) {
/*  217 */           SampleModel alphaSM = sourceAlpha[k].getSampleModel();
/*  219 */           if (alphaSM.getNumBands() != 1)
/*  220 */             throw new IllegalArgumentException(JaiI18N.getString("MosaicOpImage4")); 
/*  221 */           if (alphaSM.getDataType() != this.sampleModel.getDataType())
/*  223 */             throw new IllegalArgumentException(JaiI18N.getString("MosaicOpImage5")); 
/*  224 */           if (alphaSM.getSampleSize(0) != this.sampleModel.getSampleSize(0))
/*  226 */             throw new IllegalArgumentException(JaiI18N.getString("MosaicOpImage6")); 
/*      */         } 
/*      */       } 
/*  231 */       this.sourceAlpha = new PlanarImage[numSources];
/*  232 */       System.arraycopy(sourceAlpha, 0, this.sourceAlpha, 0, Math.min(sourceAlpha.length, numSources));
/*      */     } 
/*  238 */     this.sourceROI = null;
/*  239 */     if (sourceROI != null) {
/*  240 */       this.sourceROI = new ROI[numSources];
/*  241 */       System.arraycopy(sourceROI, 0, this.sourceROI, 0, Math.min(sourceROI.length, numSources));
/*      */     } 
/*  248 */     this.isAlphaBitmask = (mosaicType != MosaicDescriptor.MOSAIC_TYPE_BLEND || sourceAlpha == null || sourceAlpha.length < numSources);
/*  251 */     if (!this.isAlphaBitmask)
/*  252 */       for (int k = 0; k < numSources; k++) {
/*  253 */         if (sourceAlpha[k] == null) {
/*  254 */           this.isAlphaBitmask = true;
/*      */           break;
/*      */         } 
/*      */       }  
/*  261 */     this.sourceThreshold = new double[numSources][this.numBands];
/*  264 */     if (sourceThreshold == null)
/*  265 */       sourceThreshold = new double[][] { { 1.0D } }; 
/*      */     int i;
/*  267 */     for (i = 0; i < numSources; i++) {
/*  269 */       if (i < sourceThreshold.length && sourceThreshold[i] != null) {
/*  270 */         if ((sourceThreshold[i]).length < this.numBands) {
/*  272 */           Arrays.fill(this.sourceThreshold[i], sourceThreshold[i][0]);
/*      */         } else {
/*  276 */           System.arraycopy(sourceThreshold[i], 0, this.sourceThreshold[i], 0, this.numBands);
/*      */         } 
/*      */       } else {
/*  282 */         this.sourceThreshold[i] = this.sourceThreshold[0];
/*      */       } 
/*      */     } 
/*  287 */     this.threshold = new int[numSources][this.numBands];
/*  288 */     for (i = 0; i < numSources; i++) {
/*  289 */       for (int k = 0; k < this.numBands; k++)
/*  291 */         this.threshold[i][k] = (int)this.sourceThreshold[i][k]; 
/*      */     } 
/*  296 */     this.backgroundValues = new double[this.numBands];
/*  297 */     if (backgroundValues == null)
/*  298 */       backgroundValues = new double[] { 0.0D }; 
/*  300 */     if (backgroundValues.length < this.numBands) {
/*  301 */       Arrays.fill(this.backgroundValues, backgroundValues[0]);
/*      */     } else {
/*  303 */       System.arraycopy(backgroundValues, 0, this.backgroundValues, 0, this.numBands);
/*      */     } 
/*  309 */     this.background = new int[this.backgroundValues.length];
/*  310 */     int dataType = this.sampleModel.getDataType();
/*  311 */     for (int j = 0; j < this.background.length; j++) {
/*  312 */       switch (dataType) {
/*      */         case 0:
/*  314 */           this.background[j] = ImageUtil.clampRoundByte(this.backgroundValues[j]);
/*      */           break;
/*      */         case 1:
/*  318 */           this.background[j] = ImageUtil.clampRoundUShort(this.backgroundValues[j]);
/*      */           break;
/*      */         case 2:
/*  322 */           this.background[j] = ImageUtil.clampRoundShort(this.backgroundValues[j]);
/*      */           break;
/*      */         case 3:
/*  326 */           this.background[j] = ImageUtil.clampRoundInt(this.backgroundValues[j]);
/*      */           break;
/*      */       } 
/*      */     } 
/*  335 */     switch (dataType) {
/*      */       case 0:
/*  337 */         sourceExtensionConstant = 0.0D;
/*      */         break;
/*      */       case 1:
/*  340 */         sourceExtensionConstant = 0.0D;
/*      */         break;
/*      */       case 2:
/*  343 */         sourceExtensionConstant = -32768.0D;
/*      */         break;
/*      */       case 3:
/*  346 */         sourceExtensionConstant = -2.147483648E9D;
/*      */         break;
/*      */       case 4:
/*  349 */         sourceExtensionConstant = -3.4028234663852886E38D;
/*      */         break;
/*      */       default:
/*  353 */         sourceExtensionConstant = -1.7976931348623157E308D;
/*      */         break;
/*      */     } 
/*  358 */     this.sourceExtender = (sourceExtensionConstant == 0.0D) ? BorderExtender.createInstance(0) : (BorderExtender)new BorderExtenderConstant(new double[] { sourceExtensionConstant });
/*  364 */     if (sourceAlpha != null || sourceROI != null)
/*  365 */       this.zeroExtender = BorderExtender.createInstance(0); 
/*  370 */     if (sourceROI != null) {
/*  371 */       this.roiImage = new PlanarImage[numSources];
/*  372 */       for (int k = 0; k < sourceROI.length; k++) {
/*  373 */         if (sourceROI[k] != null)
/*  374 */           this.roiImage[k] = sourceROI[k].getAsImage(); 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public Rectangle mapDestRect(Rectangle destRect, int sourceIndex) {
/*  382 */     if (destRect == null)
/*  383 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*  386 */     if (sourceIndex < 0 || sourceIndex >= getNumSources())
/*  387 */       throw new IllegalArgumentException(JaiI18N.getString("Generic1")); 
/*  390 */     return destRect.intersection(getSourceImage(sourceIndex).getBounds());
/*      */   }
/*      */   
/*      */   public Rectangle mapSourceRect(Rectangle sourceRect, int sourceIndex) {
/*  395 */     if (sourceRect == null)
/*  396 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*  399 */     if (sourceIndex < 0 || sourceIndex >= getNumSources())
/*  400 */       throw new IllegalArgumentException(JaiI18N.getString("Generic1")); 
/*  403 */     return sourceRect.intersection(getBounds());
/*      */   }
/*      */   
/*      */   public Raster computeTile(int tileX, int tileY) {
/*  408 */     WritableRaster dest = createWritableRaster(this.sampleModel, new Point(tileXToX(tileX), tileYToY(tileY)));
/*  413 */     Rectangle destRect = getTileRect(tileX, tileY);
/*  415 */     int numSources = getNumSources();
/*  417 */     Raster[] rasterSources = new Raster[numSources];
/*  418 */     Raster[] alpha = (this.sourceAlpha != null) ? new Raster[numSources] : null;
/*  420 */     Raster[] roi = (this.sourceROI != null) ? new Raster[numSources] : null;
/*      */     int i;
/*  424 */     for (i = 0; i < numSources; i++) {
/*  425 */       PlanarImage source = getSourceImage(i);
/*  426 */       Rectangle srcRect = mapDestRect(destRect, i);
/*  432 */       rasterSources[i] = (srcRect != null && srcRect.isEmpty()) ? null : source.getExtendedData(destRect, this.sourceExtender);
/*  435 */       if (rasterSources[i] != null) {
/*  436 */         if (this.sourceAlpha != null && this.sourceAlpha[i] != null)
/*  437 */           alpha[i] = this.sourceAlpha[i].getExtendedData(destRect, this.zeroExtender); 
/*  441 */         if (this.sourceROI != null && this.sourceROI[i] != null)
/*  442 */           roi[i] = this.roiImage[i].getExtendedData(destRect, this.zeroExtender); 
/*      */       } 
/*      */     } 
/*  448 */     computeRect(rasterSources, dest, destRect, alpha, roi);
/*  450 */     for (i = 0; i < numSources; i++) {
/*  451 */       Raster sourceData = rasterSources[i];
/*  452 */       if (sourceData != null) {
/*  453 */         PlanarImage source = getSourceImage(i);
/*  456 */         if (source.overlapsMultipleTiles(sourceData.getBounds()))
/*  457 */           recycleTile(sourceData); 
/*      */       } 
/*      */     } 
/*  462 */     return dest;
/*      */   }
/*      */   
/*      */   protected void computeRect(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/*  468 */     computeRect(sources, dest, destRect, (Raster[])null, (Raster[])null);
/*      */   }
/*      */   
/*      */   protected void computeRect(Raster[] sources, WritableRaster dest, Rectangle destRect, Raster[] alphaRaster, Raster[] roiRaster) {
/*  477 */     int numSources = sources.length;
/*  480 */     ArrayList sourceList = new ArrayList(numSources);
/*  481 */     for (int i = 0; i < numSources; i++) {
/*  482 */       if (sources[i] != null)
/*  483 */         sourceList.add(sources[i]); 
/*      */     } 
/*  488 */     int numNonNullSources = sourceList.size();
/*  489 */     if (numNonNullSources == 0) {
/*  490 */       ImageUtil.fillBackground(dest, destRect, this.backgroundValues);
/*      */       return;
/*      */     } 
/*  495 */     SampleModel[] sourceSM = new SampleModel[numNonNullSources];
/*  496 */     for (int j = 0; j < numNonNullSources; j++)
/*  497 */       sourceSM[j] = ((Raster)sourceList.get(j)).getSampleModel(); 
/*  499 */     int formatTagID = RasterAccessor.findCompatibleTag(sourceSM, dest.getSampleModel());
/*  504 */     RasterAccessor[] s = new RasterAccessor[numSources];
/*  505 */     for (int k = 0; k < numSources; k++) {
/*  506 */       if (sources[k] != null) {
/*  507 */         RasterFormatTag formatTag = new RasterFormatTag(sources[k].getSampleModel(), formatTagID);
/*  510 */         s[k] = new RasterAccessor(sources[k], destRect, formatTag, null);
/*      */       } 
/*      */     } 
/*  516 */     RasterAccessor d = new RasterAccessor(dest, destRect, new RasterFormatTag(dest.getSampleModel(), formatTagID), null);
/*  523 */     RasterAccessor[] a = new RasterAccessor[numSources];
/*  524 */     if (alphaRaster != null)
/*  525 */       for (int m = 0; m < numSources; m++) {
/*  526 */         if (alphaRaster[m] != null) {
/*  527 */           SampleModel alphaSM = alphaRaster[m].getSampleModel();
/*  528 */           int alphaFormatTagID = RasterAccessor.findCompatibleTag(null, alphaSM);
/*  530 */           RasterFormatTag alphaFormatTag = new RasterFormatTag(alphaSM, alphaFormatTagID);
/*  532 */           a[m] = new RasterAccessor(alphaRaster[m], destRect, alphaFormatTag, this.sourceAlpha[m].getColorModel());
/*      */         } 
/*      */       }  
/*  540 */     switch (d.getDataType()) {
/*      */       case 0:
/*  542 */         computeRectByte(s, d, a, roiRaster);
/*      */         break;
/*      */       case 1:
/*  545 */         computeRectUShort(s, d, a, roiRaster);
/*      */         break;
/*      */       case 2:
/*  548 */         computeRectShort(s, d, a, roiRaster);
/*      */         break;
/*      */       case 3:
/*  551 */         computeRectInt(s, d, a, roiRaster);
/*      */         break;
/*      */       case 4:
/*  554 */         computeRectFloat(s, d, a, roiRaster);
/*      */         break;
/*      */       case 5:
/*  557 */         computeRectDouble(s, d, a, roiRaster);
/*      */         break;
/*      */     } 
/*  561 */     d.copyDataToRaster();
/*      */   }
/*      */   
/*      */   private void computeRectByte(RasterAccessor[] src, RasterAccessor dst, RasterAccessor[] alfa, Raster[] roi) {
/*  569 */     int numSources = src.length;
/*  572 */     int[] srcLineStride = new int[numSources];
/*  573 */     int[] srcPixelStride = new int[numSources];
/*  574 */     int[][] srcBandOffsets = new int[numSources][];
/*  575 */     byte[][][] srcData = new byte[numSources][][];
/*  578 */     for (int i = 0; i < numSources; i++) {
/*  579 */       if (src[i] != null) {
/*  580 */         srcLineStride[i] = src[i].getScanlineStride();
/*  581 */         srcPixelStride[i] = src[i].getPixelStride();
/*  582 */         srcBandOffsets[i] = src[i].getBandOffsets();
/*  583 */         srcData[i] = src[i].getByteDataArrays();
/*      */       } 
/*      */     } 
/*  588 */     int dstMinX = dst.getX();
/*  589 */     int dstMinY = dst.getY();
/*  590 */     int dstWidth = dst.getWidth();
/*  591 */     int dstHeight = dst.getHeight();
/*  592 */     int dstMaxX = dstMinX + dstWidth;
/*  593 */     int dstMaxY = dstMinY + dstHeight;
/*  594 */     int dstBands = dst.getNumBands();
/*  595 */     int dstLineStride = dst.getScanlineStride();
/*  596 */     int dstPixelStride = dst.getPixelStride();
/*  597 */     int[] dstBandOffsets = dst.getBandOffsets();
/*  598 */     byte[][] dstData = dst.getByteDataArrays();
/*  601 */     boolean hasAlpha = false;
/*  602 */     for (int j = 0; j < numSources; j++) {
/*  603 */       if (alfa[j] != null) {
/*  604 */         hasAlpha = true;
/*      */         break;
/*      */       } 
/*      */     } 
/*  610 */     int[] alfaLineStride = null;
/*  611 */     int[] alfaPixelStride = null;
/*  612 */     int[][] alfaBandOffsets = (int[][])null;
/*  613 */     byte[][][] alfaData = (byte[][][])null;
/*  615 */     if (hasAlpha) {
/*  617 */       alfaLineStride = new int[numSources];
/*  618 */       alfaPixelStride = new int[numSources];
/*  619 */       alfaBandOffsets = new int[numSources][];
/*  620 */       alfaData = new byte[numSources][][];
/*  623 */       for (int m = 0; m < numSources; m++) {
/*  624 */         if (alfa[m] != null) {
/*  625 */           alfaLineStride[m] = alfa[m].getScanlineStride();
/*  626 */           alfaPixelStride[m] = alfa[m].getPixelStride();
/*  627 */           alfaBandOffsets[m] = alfa[m].getBandOffsets();
/*  628 */           alfaData[m] = alfa[m].getByteDataArrays();
/*      */         } 
/*      */       } 
/*      */     } 
/*  634 */     int[] weightTypes = new int[numSources];
/*  635 */     for (int k = 0; k < numSources; k++) {
/*  636 */       weightTypes[k] = 3;
/*  637 */       if (alfa[k] != null) {
/*  638 */         weightTypes[k] = 1;
/*  639 */       } else if (this.sourceROI != null && this.sourceROI[k] != null) {
/*  640 */         weightTypes[k] = 2;
/*      */       } 
/*      */     } 
/*  645 */     int[] sLineOffsets = new int[numSources];
/*  646 */     int[] sPixelOffsets = new int[numSources];
/*  647 */     byte[][] sBandData = new byte[numSources][];
/*  650 */     int[] aLineOffsets = null;
/*  651 */     int[] aPixelOffsets = null;
/*  652 */     byte[][] aBandData = (byte[][])null;
/*  653 */     if (hasAlpha) {
/*  654 */       aLineOffsets = new int[numSources];
/*  655 */       aPixelOffsets = new int[numSources];
/*  656 */       aBandData = new byte[numSources][];
/*      */     } 
/*  659 */     for (int b = 0; b < dstBands; b++) {
/*  661 */       for (int s = 0; s < numSources; s++) {
/*  662 */         if (src[s] != null) {
/*  663 */           sBandData[s] = srcData[s][b];
/*  664 */           sLineOffsets[s] = srcBandOffsets[s][b];
/*      */         } 
/*  666 */         if (weightTypes[s] == 1) {
/*  667 */           aBandData[s] = alfaData[s][0];
/*  668 */           aLineOffsets[s] = alfaBandOffsets[s][0];
/*      */         } 
/*      */       } 
/*  673 */       byte[] dBandData = dstData[b];
/*  674 */       int dLineOffset = dstBandOffsets[b];
/*  676 */       if (this.mosaicType == MosaicDescriptor.MOSAIC_TYPE_OVERLAY) {
/*  677 */         for (int dstY = dstMinY; dstY < dstMaxY; dstY++) {
/*  680 */           for (int m = 0; m < numSources; m++) {
/*  681 */             if (src[m] != null) {
/*  682 */               sPixelOffsets[m] = sLineOffsets[m];
/*  683 */               sLineOffsets[m] = sLineOffsets[m] + srcLineStride[m];
/*      */             } 
/*  685 */             if (alfa[m] != null) {
/*  686 */               aPixelOffsets[m] = aLineOffsets[m];
/*  687 */               aLineOffsets[m] = aLineOffsets[m] + alfaLineStride[m];
/*      */             } 
/*      */           } 
/*  693 */           int dPixelOffset = dLineOffset;
/*  694 */           dLineOffset += dstLineStride;
/*  696 */           for (int dstX = dstMinX; dstX < dstMaxX; dstX++) {
/*  699 */             boolean setDestValue = false;
/*  703 */             for (int n = 0; n < numSources; n++) {
/*  704 */               if (src[n] != null) {
/*  706 */                 byte sourceValue = sBandData[n][sPixelOffsets[n]];
/*  708 */                 sPixelOffsets[n] = sPixelOffsets[n] + srcPixelStride[n];
/*  710 */                 switch (weightTypes[n]) {
/*      */                   case 1:
/*  712 */                     setDestValue = (aBandData[n][aPixelOffsets[n]] != 0);
/*  714 */                     aPixelOffsets[n] = aPixelOffsets[n] + alfaPixelStride[n];
/*      */                     break;
/*      */                   case 2:
/*  717 */                     setDestValue = (roi[n].getSample(dstX, dstY, 0) > 0);
/*      */                     break;
/*      */                   default:
/*  721 */                     setDestValue = ((sourceValue & 0xFF) >= this.sourceThreshold[n][b]);
/*      */                     break;
/*      */                 } 
/*  728 */                 if (setDestValue) {
/*  729 */                   dBandData[dPixelOffset] = sourceValue;
/*  732 */                   for (int i1 = n + 1; i1 < numSources; i1++) {
/*  733 */                     if (src[i1] != null)
/*  734 */                       sPixelOffsets[i1] = sPixelOffsets[i1] + srcPixelStride[i1]; 
/*  736 */                     if (alfa[i1] != null)
/*  737 */                       aPixelOffsets[i1] = aPixelOffsets[i1] + alfaPixelStride[i1]; 
/*      */                   } 
/*      */                   break;
/*      */                 } 
/*      */               } 
/*      */             } 
/*  746 */             if (!setDestValue)
/*  747 */               dBandData[dPixelOffset] = (byte)this.background[b]; 
/*  750 */             dPixelOffset += dstPixelStride;
/*      */           } 
/*      */         } 
/*      */       } else {
/*  754 */         for (int dstY = dstMinY; dstY < dstMaxY; dstY++) {
/*  757 */           for (int m = 0; m < numSources; m++) {
/*  758 */             if (src[m] != null) {
/*  759 */               sPixelOffsets[m] = sLineOffsets[m];
/*  760 */               sLineOffsets[m] = sLineOffsets[m] + srcLineStride[m];
/*      */             } 
/*  762 */             if (weightTypes[m] == 1) {
/*  763 */               aPixelOffsets[m] = aLineOffsets[m];
/*  764 */               aLineOffsets[m] = aLineOffsets[m] + alfaLineStride[m];
/*      */             } 
/*      */           } 
/*  770 */           int dPixelOffset = dLineOffset;
/*  771 */           dLineOffset += dstLineStride;
/*  773 */           for (int dstX = dstMinX; dstX < dstMaxX; dstX++) {
/*  776 */             float numerator = 0.0F;
/*  777 */             float denominator = 0.0F;
/*  780 */             for (int n = 0; n < numSources; n++) {
/*  781 */               if (src[n] != null) {
/*  783 */                 byte sourceValue = sBandData[n][sPixelOffsets[n]];
/*  785 */                 sPixelOffsets[n] = sPixelOffsets[n] + srcPixelStride[n];
/*  787 */                 float weight = 0.0F;
/*  788 */                 switch (weightTypes[n]) {
/*      */                   case 1:
/*  790 */                     weight = (aBandData[n][aPixelOffsets[n]] & 0xFF);
/*  791 */                     if (weight > 0.0F && this.isAlphaBitmask) {
/*  792 */                       weight = 1.0F;
/*      */                     } else {
/*  794 */                       weight /= 255.0F;
/*      */                     } 
/*  796 */                     aPixelOffsets[n] = aPixelOffsets[n] + alfaPixelStride[n];
/*      */                     break;
/*      */                   case 2:
/*  799 */                     weight = (roi[n].getSample(dstX, dstY, 0) > 0) ? 1.0F : 0.0F;
/*      */                     break;
/*      */                   default:
/*  804 */                     weight = ((sourceValue & 0xFF) >= this.sourceThreshold[n][b]) ? 1.0F : 0.0F;
/*      */                     break;
/*      */                 } 
/*  811 */                 numerator += weight * (sourceValue & 0xFF);
/*  812 */                 denominator += weight;
/*      */               } 
/*      */             } 
/*  817 */             if (denominator == 0.0D) {
/*  818 */               dBandData[dPixelOffset] = (byte)this.background[b];
/*      */             } else {
/*  820 */               dBandData[dPixelOffset] = ImageUtil.clampRoundByte(numerator / denominator);
/*      */             } 
/*  825 */             dPixelOffset += dstPixelStride;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void computeRectUShort(RasterAccessor[] src, RasterAccessor dst, RasterAccessor[] alfa, Raster[] roi) {
/*  837 */     int numSources = src.length;
/*  840 */     int[] srcLineStride = new int[numSources];
/*  841 */     int[] srcPixelStride = new int[numSources];
/*  842 */     int[][] srcBandOffsets = new int[numSources][];
/*  843 */     short[][][] srcData = new short[numSources][][];
/*  846 */     for (int i = 0; i < numSources; i++) {
/*  847 */       if (src[i] != null) {
/*  848 */         srcLineStride[i] = src[i].getScanlineStride();
/*  849 */         srcPixelStride[i] = src[i].getPixelStride();
/*  850 */         srcBandOffsets[i] = src[i].getBandOffsets();
/*  851 */         srcData[i] = src[i].getShortDataArrays();
/*      */       } 
/*      */     } 
/*  856 */     int dstMinX = dst.getX();
/*  857 */     int dstMinY = dst.getY();
/*  858 */     int dstWidth = dst.getWidth();
/*  859 */     int dstHeight = dst.getHeight();
/*  860 */     int dstMaxX = dstMinX + dstWidth;
/*  861 */     int dstMaxY = dstMinY + dstHeight;
/*  862 */     int dstBands = dst.getNumBands();
/*  863 */     int dstLineStride = dst.getScanlineStride();
/*  864 */     int dstPixelStride = dst.getPixelStride();
/*  865 */     int[] dstBandOffsets = dst.getBandOffsets();
/*  866 */     short[][] dstData = dst.getShortDataArrays();
/*  869 */     boolean hasAlpha = false;
/*  870 */     for (int j = 0; j < numSources; j++) {
/*  871 */       if (alfa[j] != null) {
/*  872 */         hasAlpha = true;
/*      */         break;
/*      */       } 
/*      */     } 
/*  878 */     int[] alfaLineStride = null;
/*  879 */     int[] alfaPixelStride = null;
/*  880 */     int[][] alfaBandOffsets = (int[][])null;
/*  881 */     short[][][] alfaData = (short[][][])null;
/*  883 */     if (hasAlpha) {
/*  885 */       alfaLineStride = new int[numSources];
/*  886 */       alfaPixelStride = new int[numSources];
/*  887 */       alfaBandOffsets = new int[numSources][];
/*  888 */       alfaData = new short[numSources][][];
/*  891 */       for (int m = 0; m < numSources; m++) {
/*  892 */         if (alfa[m] != null) {
/*  893 */           alfaLineStride[m] = alfa[m].getScanlineStride();
/*  894 */           alfaPixelStride[m] = alfa[m].getPixelStride();
/*  895 */           alfaBandOffsets[m] = alfa[m].getBandOffsets();
/*  896 */           alfaData[m] = alfa[m].getShortDataArrays();
/*      */         } 
/*      */       } 
/*      */     } 
/*  902 */     int[] weightTypes = new int[numSources];
/*  903 */     for (int k = 0; k < numSources; k++) {
/*  904 */       weightTypes[k] = 3;
/*  905 */       if (alfa[k] != null) {
/*  906 */         weightTypes[k] = 1;
/*  907 */       } else if (this.sourceROI != null && this.sourceROI[k] != null) {
/*  908 */         weightTypes[k] = 2;
/*      */       } 
/*      */     } 
/*  913 */     int[] sLineOffsets = new int[numSources];
/*  914 */     int[] sPixelOffsets = new int[numSources];
/*  915 */     short[][] sBandData = new short[numSources][];
/*  918 */     int[] aLineOffsets = null;
/*  919 */     int[] aPixelOffsets = null;
/*  920 */     short[][] aBandData = (short[][])null;
/*  921 */     if (hasAlpha) {
/*  922 */       aLineOffsets = new int[numSources];
/*  923 */       aPixelOffsets = new int[numSources];
/*  924 */       aBandData = new short[numSources][];
/*      */     } 
/*  927 */     for (int b = 0; b < dstBands; b++) {
/*  929 */       for (int s = 0; s < numSources; s++) {
/*  930 */         if (src[s] != null) {
/*  931 */           sBandData[s] = srcData[s][b];
/*  932 */           sLineOffsets[s] = srcBandOffsets[s][b];
/*      */         } 
/*  934 */         if (weightTypes[s] == 1) {
/*  935 */           aBandData[s] = alfaData[s][0];
/*  936 */           aLineOffsets[s] = alfaBandOffsets[s][0];
/*      */         } 
/*      */       } 
/*  941 */       short[] dBandData = dstData[b];
/*  942 */       int dLineOffset = dstBandOffsets[b];
/*  944 */       if (this.mosaicType == MosaicDescriptor.MOSAIC_TYPE_OVERLAY) {
/*  945 */         for (int dstY = dstMinY; dstY < dstMaxY; dstY++) {
/*  948 */           for (int m = 0; m < numSources; m++) {
/*  949 */             if (src[m] != null) {
/*  950 */               sPixelOffsets[m] = sLineOffsets[m];
/*  951 */               sLineOffsets[m] = sLineOffsets[m] + srcLineStride[m];
/*      */             } 
/*  953 */             if (alfa[m] != null) {
/*  954 */               aPixelOffsets[m] = aLineOffsets[m];
/*  955 */               aLineOffsets[m] = aLineOffsets[m] + alfaLineStride[m];
/*      */             } 
/*      */           } 
/*  961 */           int dPixelOffset = dLineOffset;
/*  962 */           dLineOffset += dstLineStride;
/*  964 */           for (int dstX = dstMinX; dstX < dstMaxX; dstX++) {
/*  967 */             boolean setDestValue = false;
/*  971 */             for (int n = 0; n < numSources; n++) {
/*  972 */               if (src[n] != null) {
/*  974 */                 short sourceValue = sBandData[n][sPixelOffsets[n]];
/*  976 */                 sPixelOffsets[n] = sPixelOffsets[n] + srcPixelStride[n];
/*  978 */                 switch (weightTypes[n]) {
/*      */                   case 1:
/*  980 */                     setDestValue = (aBandData[n][aPixelOffsets[n]] != 0);
/*  982 */                     aPixelOffsets[n] = aPixelOffsets[n] + alfaPixelStride[n];
/*      */                     break;
/*      */                   case 2:
/*  985 */                     setDestValue = (roi[n].getSample(dstX, dstY, 0) > 0);
/*      */                     break;
/*      */                   default:
/*  989 */                     setDestValue = ((sourceValue & 0xFFFF) >= this.sourceThreshold[n][b]);
/*      */                     break;
/*      */                 } 
/*  996 */                 if (setDestValue) {
/*  997 */                   dBandData[dPixelOffset] = sourceValue;
/* 1000 */                   for (int i1 = n + 1; i1 < numSources; i1++) {
/* 1001 */                     if (src[i1] != null)
/* 1002 */                       sPixelOffsets[i1] = sPixelOffsets[i1] + srcPixelStride[i1]; 
/* 1004 */                     if (alfa[i1] != null)
/* 1005 */                       aPixelOffsets[i1] = aPixelOffsets[i1] + alfaPixelStride[i1]; 
/*      */                   } 
/*      */                   break;
/*      */                 } 
/*      */               } 
/*      */             } 
/* 1014 */             if (!setDestValue)
/* 1015 */               dBandData[dPixelOffset] = (short)this.background[b]; 
/* 1018 */             dPixelOffset += dstPixelStride;
/*      */           } 
/*      */         } 
/*      */       } else {
/* 1022 */         for (int dstY = dstMinY; dstY < dstMaxY; dstY++) {
/* 1025 */           for (int m = 0; m < numSources; m++) {
/* 1026 */             if (src[m] != null) {
/* 1027 */               sPixelOffsets[m] = sLineOffsets[m];
/* 1028 */               sLineOffsets[m] = sLineOffsets[m] + srcLineStride[m];
/*      */             } 
/* 1030 */             if (weightTypes[m] == 1) {
/* 1031 */               aPixelOffsets[m] = aLineOffsets[m];
/* 1032 */               aLineOffsets[m] = aLineOffsets[m] + alfaLineStride[m];
/*      */             } 
/*      */           } 
/* 1038 */           int dPixelOffset = dLineOffset;
/* 1039 */           dLineOffset += dstLineStride;
/* 1041 */           for (int dstX = dstMinX; dstX < dstMaxX; dstX++) {
/* 1044 */             float numerator = 0.0F;
/* 1045 */             float denominator = 0.0F;
/* 1048 */             for (int n = 0; n < numSources; n++) {
/* 1049 */               if (src[n] != null) {
/* 1051 */                 short sourceValue = sBandData[n][sPixelOffsets[n]];
/* 1053 */                 sPixelOffsets[n] = sPixelOffsets[n] + srcPixelStride[n];
/* 1055 */                 float weight = 0.0F;
/* 1056 */                 switch (weightTypes[n]) {
/*      */                   case 1:
/* 1058 */                     weight = (aBandData[n][aPixelOffsets[n]] & 0xFFFF);
/* 1059 */                     if (weight > 0.0F && this.isAlphaBitmask) {
/* 1060 */                       weight = 1.0F;
/*      */                     } else {
/* 1062 */                       weight /= 65535.0F;
/*      */                     } 
/* 1064 */                     aPixelOffsets[n] = aPixelOffsets[n] + alfaPixelStride[n];
/*      */                     break;
/*      */                   case 2:
/* 1067 */                     weight = (roi[n].getSample(dstX, dstY, 0) > 0) ? 1.0F : 0.0F;
/*      */                     break;
/*      */                   default:
/* 1072 */                     weight = ((sourceValue & 0xFFFF) >= this.sourceThreshold[n][b]) ? 1.0F : 0.0F;
/*      */                     break;
/*      */                 } 
/* 1079 */                 numerator += weight * (sourceValue & 0xFFFF);
/* 1080 */                 denominator += weight;
/*      */               } 
/*      */             } 
/* 1085 */             if (denominator == 0.0D) {
/* 1086 */               dBandData[dPixelOffset] = (short)this.background[b];
/*      */             } else {
/* 1088 */               dBandData[dPixelOffset] = ImageUtil.clampRoundUShort(numerator / denominator);
/*      */             } 
/* 1093 */             dPixelOffset += dstPixelStride;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void computeRectShort(RasterAccessor[] src, RasterAccessor dst, RasterAccessor[] alfa, Raster[] roi) {
/* 1105 */     int numSources = src.length;
/* 1108 */     int[] srcLineStride = new int[numSources];
/* 1109 */     int[] srcPixelStride = new int[numSources];
/* 1110 */     int[][] srcBandOffsets = new int[numSources][];
/* 1111 */     short[][][] srcData = new short[numSources][][];
/* 1114 */     for (int i = 0; i < numSources; i++) {
/* 1115 */       if (src[i] != null) {
/* 1116 */         srcLineStride[i] = src[i].getScanlineStride();
/* 1117 */         srcPixelStride[i] = src[i].getPixelStride();
/* 1118 */         srcBandOffsets[i] = src[i].getBandOffsets();
/* 1119 */         srcData[i] = src[i].getShortDataArrays();
/*      */       } 
/*      */     } 
/* 1124 */     int dstMinX = dst.getX();
/* 1125 */     int dstMinY = dst.getY();
/* 1126 */     int dstWidth = dst.getWidth();
/* 1127 */     int dstHeight = dst.getHeight();
/* 1128 */     int dstMaxX = dstMinX + dstWidth;
/* 1129 */     int dstMaxY = dstMinY + dstHeight;
/* 1130 */     int dstBands = dst.getNumBands();
/* 1131 */     int dstLineStride = dst.getScanlineStride();
/* 1132 */     int dstPixelStride = dst.getPixelStride();
/* 1133 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 1134 */     short[][] dstData = dst.getShortDataArrays();
/* 1137 */     boolean hasAlpha = false;
/* 1138 */     for (int j = 0; j < numSources; j++) {
/* 1139 */       if (alfa[j] != null) {
/* 1140 */         hasAlpha = true;
/*      */         break;
/*      */       } 
/*      */     } 
/* 1146 */     int[] alfaLineStride = null;
/* 1147 */     int[] alfaPixelStride = null;
/* 1148 */     int[][] alfaBandOffsets = (int[][])null;
/* 1149 */     short[][][] alfaData = (short[][][])null;
/* 1151 */     if (hasAlpha) {
/* 1153 */       alfaLineStride = new int[numSources];
/* 1154 */       alfaPixelStride = new int[numSources];
/* 1155 */       alfaBandOffsets = new int[numSources][];
/* 1156 */       alfaData = new short[numSources][][];
/* 1159 */       for (int m = 0; m < numSources; m++) {
/* 1160 */         if (alfa[m] != null) {
/* 1161 */           alfaLineStride[m] = alfa[m].getScanlineStride();
/* 1162 */           alfaPixelStride[m] = alfa[m].getPixelStride();
/* 1163 */           alfaBandOffsets[m] = alfa[m].getBandOffsets();
/* 1164 */           alfaData[m] = alfa[m].getShortDataArrays();
/*      */         } 
/*      */       } 
/*      */     } 
/* 1170 */     int[] weightTypes = new int[numSources];
/* 1171 */     for (int k = 0; k < numSources; k++) {
/* 1172 */       weightTypes[k] = 3;
/* 1173 */       if (alfa[k] != null) {
/* 1174 */         weightTypes[k] = 1;
/* 1175 */       } else if (this.sourceROI != null && this.sourceROI[k] != null) {
/* 1176 */         weightTypes[k] = 2;
/*      */       } 
/*      */     } 
/* 1181 */     int[] sLineOffsets = new int[numSources];
/* 1182 */     int[] sPixelOffsets = new int[numSources];
/* 1183 */     short[][] sBandData = new short[numSources][];
/* 1186 */     int[] aLineOffsets = null;
/* 1187 */     int[] aPixelOffsets = null;
/* 1188 */     short[][] aBandData = (short[][])null;
/* 1189 */     if (hasAlpha) {
/* 1190 */       aLineOffsets = new int[numSources];
/* 1191 */       aPixelOffsets = new int[numSources];
/* 1192 */       aBandData = new short[numSources][];
/*      */     } 
/* 1195 */     for (int b = 0; b < dstBands; b++) {
/* 1197 */       for (int s = 0; s < numSources; s++) {
/* 1198 */         if (src[s] != null) {
/* 1199 */           sBandData[s] = srcData[s][b];
/* 1200 */           sLineOffsets[s] = srcBandOffsets[s][b];
/*      */         } 
/* 1202 */         if (weightTypes[s] == 1) {
/* 1203 */           aBandData[s] = alfaData[s][0];
/* 1204 */           aLineOffsets[s] = alfaBandOffsets[s][0];
/*      */         } 
/*      */       } 
/* 1209 */       short[] dBandData = dstData[b];
/* 1210 */       int dLineOffset = dstBandOffsets[b];
/* 1212 */       if (this.mosaicType == MosaicDescriptor.MOSAIC_TYPE_OVERLAY) {
/* 1213 */         for (int dstY = dstMinY; dstY < dstMaxY; dstY++) {
/* 1216 */           for (int m = 0; m < numSources; m++) {
/* 1217 */             if (src[m] != null) {
/* 1218 */               sPixelOffsets[m] = sLineOffsets[m];
/* 1219 */               sLineOffsets[m] = sLineOffsets[m] + srcLineStride[m];
/*      */             } 
/* 1221 */             if (alfa[m] != null) {
/* 1222 */               aPixelOffsets[m] = aLineOffsets[m];
/* 1223 */               aLineOffsets[m] = aLineOffsets[m] + alfaLineStride[m];
/*      */             } 
/*      */           } 
/* 1229 */           int dPixelOffset = dLineOffset;
/* 1230 */           dLineOffset += dstLineStride;
/* 1232 */           for (int dstX = dstMinX; dstX < dstMaxX; dstX++) {
/* 1235 */             boolean setDestValue = false;
/* 1239 */             for (int n = 0; n < numSources; n++) {
/* 1240 */               if (src[n] != null) {
/* 1242 */                 short sourceValue = sBandData[n][sPixelOffsets[n]];
/* 1244 */                 sPixelOffsets[n] = sPixelOffsets[n] + srcPixelStride[n];
/* 1246 */                 switch (weightTypes[n]) {
/*      */                   case 1:
/* 1248 */                     setDestValue = (aBandData[n][aPixelOffsets[n]] != 0);
/* 1250 */                     aPixelOffsets[n] = aPixelOffsets[n] + alfaPixelStride[n];
/*      */                     break;
/*      */                   case 2:
/* 1253 */                     setDestValue = (roi[n].getSample(dstX, dstY, 0) > 0);
/*      */                     break;
/*      */                   default:
/* 1257 */                     setDestValue = (sourceValue >= this.sourceThreshold[n][b]);
/*      */                     break;
/*      */                 } 
/* 1264 */                 if (setDestValue) {
/* 1265 */                   dBandData[dPixelOffset] = sourceValue;
/* 1268 */                   for (int i1 = n + 1; i1 < numSources; i1++) {
/* 1269 */                     if (src[i1] != null)
/* 1270 */                       sPixelOffsets[i1] = sPixelOffsets[i1] + srcPixelStride[i1]; 
/* 1272 */                     if (alfa[i1] != null)
/* 1273 */                       aPixelOffsets[i1] = aPixelOffsets[i1] + alfaPixelStride[i1]; 
/*      */                   } 
/*      */                   break;
/*      */                 } 
/*      */               } 
/*      */             } 
/* 1282 */             if (!setDestValue)
/* 1283 */               dBandData[dPixelOffset] = (short)this.background[b]; 
/* 1286 */             dPixelOffset += dstPixelStride;
/*      */           } 
/*      */         } 
/*      */       } else {
/* 1290 */         for (int dstY = dstMinY; dstY < dstMaxY; dstY++) {
/* 1293 */           for (int m = 0; m < numSources; m++) {
/* 1294 */             if (src[m] != null) {
/* 1295 */               sPixelOffsets[m] = sLineOffsets[m];
/* 1296 */               sLineOffsets[m] = sLineOffsets[m] + srcLineStride[m];
/*      */             } 
/* 1298 */             if (weightTypes[m] == 1) {
/* 1299 */               aPixelOffsets[m] = aLineOffsets[m];
/* 1300 */               aLineOffsets[m] = aLineOffsets[m] + alfaLineStride[m];
/*      */             } 
/*      */           } 
/* 1306 */           int dPixelOffset = dLineOffset;
/* 1307 */           dLineOffset += dstLineStride;
/* 1309 */           for (int dstX = dstMinX; dstX < dstMaxX; dstX++) {
/* 1312 */             float numerator = 0.0F;
/* 1313 */             float denominator = 0.0F;
/* 1316 */             for (int n = 0; n < numSources; n++) {
/* 1317 */               if (src[n] != null) {
/* 1319 */                 short sourceValue = sBandData[n][sPixelOffsets[n]];
/* 1321 */                 sPixelOffsets[n] = sPixelOffsets[n] + srcPixelStride[n];
/* 1323 */                 float weight = 0.0F;
/* 1324 */                 switch (weightTypes[n]) {
/*      */                   case 1:
/* 1326 */                     weight = aBandData[n][aPixelOffsets[n]];
/* 1327 */                     if (weight > 0.0F && this.isAlphaBitmask) {
/* 1328 */                       weight = 1.0F;
/*      */                     } else {
/* 1330 */                       weight /= 32767.0F;
/*      */                     } 
/* 1332 */                     aPixelOffsets[n] = aPixelOffsets[n] + alfaPixelStride[n];
/*      */                     break;
/*      */                   case 2:
/* 1335 */                     weight = (roi[n].getSample(dstX, dstY, 0) > 0) ? 1.0F : 0.0F;
/*      */                     break;
/*      */                   default:
/* 1340 */                     weight = (sourceValue >= this.sourceThreshold[n][b]) ? 1.0F : 0.0F;
/*      */                     break;
/*      */                 } 
/* 1347 */                 numerator += weight * sourceValue;
/* 1348 */                 denominator += weight;
/*      */               } 
/*      */             } 
/* 1353 */             if (denominator == 0.0D) {
/* 1354 */               dBandData[dPixelOffset] = (short)this.background[b];
/*      */             } else {
/* 1356 */               dBandData[dPixelOffset] = ImageUtil.clampRoundShort(numerator / denominator);
/*      */             } 
/* 1361 */             dPixelOffset += dstPixelStride;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void computeRectInt(RasterAccessor[] src, RasterAccessor dst, RasterAccessor[] alfa, Raster[] roi) {
/* 1373 */     int numSources = src.length;
/* 1376 */     int[] srcLineStride = new int[numSources];
/* 1377 */     int[] srcPixelStride = new int[numSources];
/* 1378 */     int[][] srcBandOffsets = new int[numSources][];
/* 1379 */     int[][][] srcData = new int[numSources][][];
/* 1382 */     for (int i = 0; i < numSources; i++) {
/* 1383 */       if (src[i] != null) {
/* 1384 */         srcLineStride[i] = src[i].getScanlineStride();
/* 1385 */         srcPixelStride[i] = src[i].getPixelStride();
/* 1386 */         srcBandOffsets[i] = src[i].getBandOffsets();
/* 1387 */         srcData[i] = src[i].getIntDataArrays();
/*      */       } 
/*      */     } 
/* 1392 */     int dstMinX = dst.getX();
/* 1393 */     int dstMinY = dst.getY();
/* 1394 */     int dstWidth = dst.getWidth();
/* 1395 */     int dstHeight = dst.getHeight();
/* 1396 */     int dstMaxX = dstMinX + dstWidth;
/* 1397 */     int dstMaxY = dstMinY + dstHeight;
/* 1398 */     int dstBands = dst.getNumBands();
/* 1399 */     int dstLineStride = dst.getScanlineStride();
/* 1400 */     int dstPixelStride = dst.getPixelStride();
/* 1401 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 1402 */     int[][] dstData = dst.getIntDataArrays();
/* 1405 */     boolean hasAlpha = false;
/* 1406 */     for (int j = 0; j < numSources; j++) {
/* 1407 */       if (alfa[j] != null) {
/* 1408 */         hasAlpha = true;
/*      */         break;
/*      */       } 
/*      */     } 
/* 1414 */     int[] alfaLineStride = null;
/* 1415 */     int[] alfaPixelStride = null;
/* 1416 */     int[][] alfaBandOffsets = (int[][])null;
/* 1417 */     int[][][] alfaData = (int[][][])null;
/* 1419 */     if (hasAlpha) {
/* 1421 */       alfaLineStride = new int[numSources];
/* 1422 */       alfaPixelStride = new int[numSources];
/* 1423 */       alfaBandOffsets = new int[numSources][];
/* 1424 */       alfaData = new int[numSources][][];
/* 1427 */       for (int m = 0; m < numSources; m++) {
/* 1428 */         if (alfa[m] != null) {
/* 1429 */           alfaLineStride[m] = alfa[m].getScanlineStride();
/* 1430 */           alfaPixelStride[m] = alfa[m].getPixelStride();
/* 1431 */           alfaBandOffsets[m] = alfa[m].getBandOffsets();
/* 1432 */           alfaData[m] = alfa[m].getIntDataArrays();
/*      */         } 
/*      */       } 
/*      */     } 
/* 1438 */     int[] weightTypes = new int[numSources];
/* 1439 */     for (int k = 0; k < numSources; k++) {
/* 1440 */       weightTypes[k] = 3;
/* 1441 */       if (alfa[k] != null) {
/* 1442 */         weightTypes[k] = 1;
/* 1443 */       } else if (this.sourceROI != null && this.sourceROI[k] != null) {
/* 1444 */         weightTypes[k] = 2;
/*      */       } 
/*      */     } 
/* 1449 */     int[] sLineOffsets = new int[numSources];
/* 1450 */     int[] sPixelOffsets = new int[numSources];
/* 1451 */     int[][] sBandData = new int[numSources][];
/* 1454 */     int[] aLineOffsets = null;
/* 1455 */     int[] aPixelOffsets = null;
/* 1456 */     int[][] aBandData = (int[][])null;
/* 1457 */     if (hasAlpha) {
/* 1458 */       aLineOffsets = new int[numSources];
/* 1459 */       aPixelOffsets = new int[numSources];
/* 1460 */       aBandData = new int[numSources][];
/*      */     } 
/* 1463 */     for (int b = 0; b < dstBands; b++) {
/* 1465 */       for (int s = 0; s < numSources; s++) {
/* 1466 */         if (src[s] != null) {
/* 1467 */           sBandData[s] = srcData[s][b];
/* 1468 */           sLineOffsets[s] = srcBandOffsets[s][b];
/*      */         } 
/* 1470 */         if (weightTypes[s] == 1) {
/* 1471 */           aBandData[s] = alfaData[s][0];
/* 1472 */           aLineOffsets[s] = alfaBandOffsets[s][0];
/*      */         } 
/*      */       } 
/* 1477 */       int[] dBandData = dstData[b];
/* 1478 */       int dLineOffset = dstBandOffsets[b];
/* 1480 */       if (this.mosaicType == MosaicDescriptor.MOSAIC_TYPE_OVERLAY) {
/* 1481 */         for (int dstY = dstMinY; dstY < dstMaxY; dstY++) {
/* 1484 */           for (int m = 0; m < numSources; m++) {
/* 1485 */             if (src[m] != null) {
/* 1486 */               sPixelOffsets[m] = sLineOffsets[m];
/* 1487 */               sLineOffsets[m] = sLineOffsets[m] + srcLineStride[m];
/*      */             } 
/* 1489 */             if (alfa[m] != null) {
/* 1490 */               aPixelOffsets[m] = aLineOffsets[m];
/* 1491 */               aLineOffsets[m] = aLineOffsets[m] + alfaLineStride[m];
/*      */             } 
/*      */           } 
/* 1497 */           int dPixelOffset = dLineOffset;
/* 1498 */           dLineOffset += dstLineStride;
/* 1500 */           for (int dstX = dstMinX; dstX < dstMaxX; dstX++) {
/* 1503 */             boolean setDestValue = false;
/* 1507 */             for (int n = 0; n < numSources; n++) {
/* 1508 */               if (src[n] != null) {
/* 1510 */                 int sourceValue = sBandData[n][sPixelOffsets[n]];
/* 1512 */                 sPixelOffsets[n] = sPixelOffsets[n] + srcPixelStride[n];
/* 1514 */                 switch (weightTypes[n]) {
/*      */                   case 1:
/* 1516 */                     setDestValue = (aBandData[n][aPixelOffsets[n]] != 0);
/* 1518 */                     aPixelOffsets[n] = aPixelOffsets[n] + alfaPixelStride[n];
/*      */                     break;
/*      */                   case 2:
/* 1521 */                     setDestValue = (roi[n].getSample(dstX, dstY, 0) > 0);
/*      */                     break;
/*      */                   default:
/* 1525 */                     setDestValue = (sourceValue >= this.sourceThreshold[n][b]);
/*      */                     break;
/*      */                 } 
/* 1532 */                 if (setDestValue) {
/* 1533 */                   dBandData[dPixelOffset] = sourceValue;
/* 1536 */                   for (int i1 = n + 1; i1 < numSources; i1++) {
/* 1537 */                     if (src[i1] != null)
/* 1538 */                       sPixelOffsets[i1] = sPixelOffsets[i1] + srcPixelStride[i1]; 
/* 1540 */                     if (alfa[i1] != null)
/* 1541 */                       aPixelOffsets[i1] = aPixelOffsets[i1] + alfaPixelStride[i1]; 
/*      */                   } 
/*      */                   break;
/*      */                 } 
/*      */               } 
/*      */             } 
/* 1550 */             if (!setDestValue)
/* 1551 */               dBandData[dPixelOffset] = this.background[b]; 
/* 1554 */             dPixelOffset += dstPixelStride;
/*      */           } 
/*      */         } 
/*      */       } else {
/* 1558 */         for (int dstY = dstMinY; dstY < dstMaxY; dstY++) {
/* 1561 */           for (int m = 0; m < numSources; m++) {
/* 1562 */             if (src[m] != null) {
/* 1563 */               sPixelOffsets[m] = sLineOffsets[m];
/* 1564 */               sLineOffsets[m] = sLineOffsets[m] + srcLineStride[m];
/*      */             } 
/* 1566 */             if (weightTypes[m] == 1) {
/* 1567 */               aPixelOffsets[m] = aLineOffsets[m];
/* 1568 */               aLineOffsets[m] = aLineOffsets[m] + alfaLineStride[m];
/*      */             } 
/*      */           } 
/* 1574 */           int dPixelOffset = dLineOffset;
/* 1575 */           dLineOffset += dstLineStride;
/* 1577 */           for (int dstX = dstMinX; dstX < dstMaxX; dstX++) {
/* 1580 */             double numerator = 0.0D;
/* 1581 */             double denominator = 0.0D;
/* 1584 */             for (int n = 0; n < numSources; n++) {
/* 1585 */               if (src[n] != null) {
/* 1587 */                 int sourceValue = sBandData[n][sPixelOffsets[n]];
/* 1589 */                 sPixelOffsets[n] = sPixelOffsets[n] + srcPixelStride[n];
/* 1591 */                 double weight = 0.0D;
/* 1592 */                 switch (weightTypes[n]) {
/*      */                   case 1:
/* 1594 */                     weight = aBandData[n][aPixelOffsets[n]];
/* 1595 */                     if (weight > 0.0D && this.isAlphaBitmask) {
/* 1596 */                       weight = 1.0D;
/*      */                     } else {
/* 1598 */                       weight /= 2.147483647E9D;
/*      */                     } 
/* 1600 */                     aPixelOffsets[n] = aPixelOffsets[n] + alfaPixelStride[n];
/*      */                     break;
/*      */                   case 2:
/* 1603 */                     weight = (roi[n].getSample(dstX, dstY, 0) > 0) ? 1.0D : 0.0D;
/*      */                     break;
/*      */                   default:
/* 1608 */                     weight = (sourceValue >= this.sourceThreshold[n][b]) ? 1.0D : 0.0D;
/*      */                     break;
/*      */                 } 
/* 1615 */                 numerator += weight * sourceValue;
/* 1616 */                 denominator += weight;
/*      */               } 
/*      */             } 
/* 1621 */             if (denominator == 0.0D) {
/* 1622 */               dBandData[dPixelOffset] = this.background[b];
/*      */             } else {
/* 1624 */               dBandData[dPixelOffset] = ImageUtil.clampRoundInt(numerator / denominator);
/*      */             } 
/* 1629 */             dPixelOffset += dstPixelStride;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void computeRectFloat(RasterAccessor[] src, RasterAccessor dst, RasterAccessor[] alfa, Raster[] roi) {
/* 1641 */     int numSources = src.length;
/* 1644 */     int[] srcLineStride = new int[numSources];
/* 1645 */     int[] srcPixelStride = new int[numSources];
/* 1646 */     int[][] srcBandOffsets = new int[numSources][];
/* 1647 */     float[][][] srcData = new float[numSources][][];
/* 1650 */     for (int i = 0; i < numSources; i++) {
/* 1651 */       if (src[i] != null) {
/* 1652 */         srcLineStride[i] = src[i].getScanlineStride();
/* 1653 */         srcPixelStride[i] = src[i].getPixelStride();
/* 1654 */         srcBandOffsets[i] = src[i].getBandOffsets();
/* 1655 */         srcData[i] = src[i].getFloatDataArrays();
/*      */       } 
/*      */     } 
/* 1660 */     int dstMinX = dst.getX();
/* 1661 */     int dstMinY = dst.getY();
/* 1662 */     int dstWidth = dst.getWidth();
/* 1663 */     int dstHeight = dst.getHeight();
/* 1664 */     int dstMaxX = dstMinX + dstWidth;
/* 1665 */     int dstMaxY = dstMinY + dstHeight;
/* 1666 */     int dstBands = dst.getNumBands();
/* 1667 */     int dstLineStride = dst.getScanlineStride();
/* 1668 */     int dstPixelStride = dst.getPixelStride();
/* 1669 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 1670 */     float[][] dstData = dst.getFloatDataArrays();
/* 1673 */     boolean hasAlpha = false;
/* 1674 */     for (int j = 0; j < numSources; j++) {
/* 1675 */       if (alfa[j] != null) {
/* 1676 */         hasAlpha = true;
/*      */         break;
/*      */       } 
/*      */     } 
/* 1682 */     int[] alfaLineStride = null;
/* 1683 */     int[] alfaPixelStride = null;
/* 1684 */     int[][] alfaBandOffsets = (int[][])null;
/* 1685 */     float[][][] alfaData = (float[][][])null;
/* 1687 */     if (hasAlpha) {
/* 1689 */       alfaLineStride = new int[numSources];
/* 1690 */       alfaPixelStride = new int[numSources];
/* 1691 */       alfaBandOffsets = new int[numSources][];
/* 1692 */       alfaData = new float[numSources][][];
/* 1695 */       for (int m = 0; m < numSources; m++) {
/* 1696 */         if (alfa[m] != null) {
/* 1697 */           alfaLineStride[m] = alfa[m].getScanlineStride();
/* 1698 */           alfaPixelStride[m] = alfa[m].getPixelStride();
/* 1699 */           alfaBandOffsets[m] = alfa[m].getBandOffsets();
/* 1700 */           alfaData[m] = alfa[m].getFloatDataArrays();
/*      */         } 
/*      */       } 
/*      */     } 
/* 1706 */     int[] weightTypes = new int[numSources];
/* 1707 */     for (int k = 0; k < numSources; k++) {
/* 1708 */       weightTypes[k] = 3;
/* 1709 */       if (alfa[k] != null) {
/* 1710 */         weightTypes[k] = 1;
/* 1711 */       } else if (this.sourceROI != null && this.sourceROI[k] != null) {
/* 1712 */         weightTypes[k] = 2;
/*      */       } 
/*      */     } 
/* 1717 */     int[] sLineOffsets = new int[numSources];
/* 1718 */     int[] sPixelOffsets = new int[numSources];
/* 1719 */     float[][] sBandData = new float[numSources][];
/* 1722 */     int[] aLineOffsets = null;
/* 1723 */     int[] aPixelOffsets = null;
/* 1724 */     float[][] aBandData = (float[][])null;
/* 1725 */     if (hasAlpha) {
/* 1726 */       aLineOffsets = new int[numSources];
/* 1727 */       aPixelOffsets = new int[numSources];
/* 1728 */       aBandData = new float[numSources][];
/*      */     } 
/* 1731 */     for (int b = 0; b < dstBands; b++) {
/* 1733 */       for (int s = 0; s < numSources; s++) {
/* 1734 */         if (src[s] != null) {
/* 1735 */           sBandData[s] = srcData[s][b];
/* 1736 */           sLineOffsets[s] = srcBandOffsets[s][b];
/*      */         } 
/* 1738 */         if (weightTypes[s] == 1) {
/* 1739 */           aBandData[s] = alfaData[s][0];
/* 1740 */           aLineOffsets[s] = alfaBandOffsets[s][0];
/*      */         } 
/*      */       } 
/* 1745 */       float[] dBandData = dstData[b];
/* 1746 */       int dLineOffset = dstBandOffsets[b];
/* 1748 */       if (this.mosaicType == MosaicDescriptor.MOSAIC_TYPE_OVERLAY) {
/* 1749 */         for (int dstY = dstMinY; dstY < dstMaxY; dstY++) {
/* 1752 */           for (int m = 0; m < numSources; m++) {
/* 1753 */             if (src[m] != null) {
/* 1754 */               sPixelOffsets[m] = sLineOffsets[m];
/* 1755 */               sLineOffsets[m] = sLineOffsets[m] + srcLineStride[m];
/*      */             } 
/* 1757 */             if (alfa[m] != null) {
/* 1758 */               aPixelOffsets[m] = aLineOffsets[m];
/* 1759 */               aLineOffsets[m] = aLineOffsets[m] + alfaLineStride[m];
/*      */             } 
/*      */           } 
/* 1765 */           int dPixelOffset = dLineOffset;
/* 1766 */           dLineOffset += dstLineStride;
/* 1768 */           for (int dstX = dstMinX; dstX < dstMaxX; dstX++) {
/* 1771 */             boolean setDestValue = false;
/* 1775 */             for (int n = 0; n < numSources; n++) {
/* 1776 */               if (src[n] != null) {
/* 1778 */                 float sourceValue = sBandData[n][sPixelOffsets[n]];
/* 1780 */                 sPixelOffsets[n] = sPixelOffsets[n] + srcPixelStride[n];
/* 1782 */                 switch (weightTypes[n]) {
/*      */                   case 1:
/* 1784 */                     setDestValue = (aBandData[n][aPixelOffsets[n]] != 0.0F);
/* 1786 */                     aPixelOffsets[n] = aPixelOffsets[n] + alfaPixelStride[n];
/*      */                     break;
/*      */                   case 2:
/* 1789 */                     setDestValue = (roi[n].getSample(dstX, dstY, 0) > 0);
/*      */                     break;
/*      */                   default:
/* 1793 */                     setDestValue = (sourceValue >= this.sourceThreshold[n][b]);
/*      */                     break;
/*      */                 } 
/* 1800 */                 if (setDestValue) {
/* 1801 */                   dBandData[dPixelOffset] = sourceValue;
/* 1804 */                   for (int i1 = n + 1; i1 < numSources; i1++) {
/* 1805 */                     if (src[i1] != null)
/* 1806 */                       sPixelOffsets[i1] = sPixelOffsets[i1] + srcPixelStride[i1]; 
/* 1808 */                     if (alfa[i1] != null)
/* 1809 */                       aPixelOffsets[i1] = aPixelOffsets[i1] + alfaPixelStride[i1]; 
/*      */                   } 
/*      */                   break;
/*      */                 } 
/*      */               } 
/*      */             } 
/* 1818 */             if (!setDestValue)
/* 1819 */               dBandData[dPixelOffset] = (float)this.backgroundValues[b]; 
/* 1822 */             dPixelOffset += dstPixelStride;
/*      */           } 
/*      */         } 
/*      */       } else {
/* 1826 */         for (int dstY = dstMinY; dstY < dstMaxY; dstY++) {
/* 1829 */           for (int m = 0; m < numSources; m++) {
/* 1830 */             if (src[m] != null) {
/* 1831 */               sPixelOffsets[m] = sLineOffsets[m];
/* 1832 */               sLineOffsets[m] = sLineOffsets[m] + srcLineStride[m];
/*      */             } 
/* 1834 */             if (weightTypes[m] == 1) {
/* 1835 */               aPixelOffsets[m] = aLineOffsets[m];
/* 1836 */               aLineOffsets[m] = aLineOffsets[m] + alfaLineStride[m];
/*      */             } 
/*      */           } 
/* 1842 */           int dPixelOffset = dLineOffset;
/* 1843 */           dLineOffset += dstLineStride;
/* 1845 */           for (int dstX = dstMinX; dstX < dstMaxX; dstX++) {
/* 1848 */             float numerator = 0.0F;
/* 1849 */             float denominator = 0.0F;
/* 1852 */             for (int n = 0; n < numSources; n++) {
/* 1853 */               if (src[n] != null) {
/* 1855 */                 float sourceValue = sBandData[n][sPixelOffsets[n]];
/* 1857 */                 sPixelOffsets[n] = sPixelOffsets[n] + srcPixelStride[n];
/* 1859 */                 float weight = 0.0F;
/* 1860 */                 switch (weightTypes[n]) {
/*      */                   case 1:
/* 1862 */                     weight = aBandData[n][aPixelOffsets[n]];
/* 1863 */                     if (weight > 0.0F && this.isAlphaBitmask)
/* 1864 */                       weight = 1.0F; 
/* 1866 */                     aPixelOffsets[n] = aPixelOffsets[n] + alfaPixelStride[n];
/*      */                     break;
/*      */                   case 2:
/* 1869 */                     weight = (roi[n].getSample(dstX, dstY, 0) > 0) ? 1.0F : 0.0F;
/*      */                     break;
/*      */                   default:
/* 1874 */                     weight = (sourceValue >= this.sourceThreshold[n][b]) ? 1.0F : 0.0F;
/*      */                     break;
/*      */                 } 
/* 1881 */                 numerator += weight * sourceValue;
/* 1882 */                 denominator += weight;
/*      */               } 
/*      */             } 
/* 1887 */             if (denominator == 0.0D) {
/* 1888 */               dBandData[dPixelOffset] = (float)this.backgroundValues[b];
/*      */             } else {
/* 1890 */               dBandData[dPixelOffset] = numerator / denominator;
/*      */             } 
/* 1895 */             dPixelOffset += dstPixelStride;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void computeRectDouble(RasterAccessor[] src, RasterAccessor dst, RasterAccessor[] alfa, Raster[] roi) {
/* 1907 */     int numSources = src.length;
/* 1910 */     int[] srcLineStride = new int[numSources];
/* 1911 */     int[] srcPixelStride = new int[numSources];
/* 1912 */     int[][] srcBandOffsets = new int[numSources][];
/* 1913 */     double[][][] srcData = new double[numSources][][];
/* 1916 */     for (int i = 0; i < numSources; i++) {
/* 1917 */       if (src[i] != null) {
/* 1918 */         srcLineStride[i] = src[i].getScanlineStride();
/* 1919 */         srcPixelStride[i] = src[i].getPixelStride();
/* 1920 */         srcBandOffsets[i] = src[i].getBandOffsets();
/* 1921 */         srcData[i] = src[i].getDoubleDataArrays();
/*      */       } 
/*      */     } 
/* 1926 */     int dstMinX = dst.getX();
/* 1927 */     int dstMinY = dst.getY();
/* 1928 */     int dstWidth = dst.getWidth();
/* 1929 */     int dstHeight = dst.getHeight();
/* 1930 */     int dstMaxX = dstMinX + dstWidth;
/* 1931 */     int dstMaxY = dstMinY + dstHeight;
/* 1932 */     int dstBands = dst.getNumBands();
/* 1933 */     int dstLineStride = dst.getScanlineStride();
/* 1934 */     int dstPixelStride = dst.getPixelStride();
/* 1935 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 1936 */     double[][] dstData = dst.getDoubleDataArrays();
/* 1939 */     boolean hasAlpha = false;
/* 1940 */     for (int j = 0; j < numSources; j++) {
/* 1941 */       if (alfa[j] != null) {
/* 1942 */         hasAlpha = true;
/*      */         break;
/*      */       } 
/*      */     } 
/* 1948 */     int[] alfaLineStride = null;
/* 1949 */     int[] alfaPixelStride = null;
/* 1950 */     int[][] alfaBandOffsets = (int[][])null;
/* 1951 */     double[][][] alfaData = (double[][][])null;
/* 1953 */     if (hasAlpha) {
/* 1955 */       alfaLineStride = new int[numSources];
/* 1956 */       alfaPixelStride = new int[numSources];
/* 1957 */       alfaBandOffsets = new int[numSources][];
/* 1958 */       alfaData = new double[numSources][][];
/* 1961 */       for (int m = 0; m < numSources; m++) {
/* 1962 */         if (alfa[m] != null) {
/* 1963 */           alfaLineStride[m] = alfa[m].getScanlineStride();
/* 1964 */           alfaPixelStride[m] = alfa[m].getPixelStride();
/* 1965 */           alfaBandOffsets[m] = alfa[m].getBandOffsets();
/* 1966 */           alfaData[m] = alfa[m].getDoubleDataArrays();
/*      */         } 
/*      */       } 
/*      */     } 
/* 1972 */     int[] weightTypes = new int[numSources];
/* 1973 */     for (int k = 0; k < numSources; k++) {
/* 1974 */       weightTypes[k] = 3;
/* 1975 */       if (alfa[k] != null) {
/* 1976 */         weightTypes[k] = 1;
/* 1977 */       } else if (this.sourceROI != null && this.sourceROI[k] != null) {
/* 1978 */         weightTypes[k] = 2;
/*      */       } 
/*      */     } 
/* 1983 */     int[] sLineOffsets = new int[numSources];
/* 1984 */     int[] sPixelOffsets = new int[numSources];
/* 1985 */     double[][] sBandData = new double[numSources][];
/* 1988 */     int[] aLineOffsets = null;
/* 1989 */     int[] aPixelOffsets = null;
/* 1990 */     double[][] aBandData = (double[][])null;
/* 1991 */     if (hasAlpha) {
/* 1992 */       aLineOffsets = new int[numSources];
/* 1993 */       aPixelOffsets = new int[numSources];
/* 1994 */       aBandData = new double[numSources][];
/*      */     } 
/* 1997 */     for (int b = 0; b < dstBands; b++) {
/* 1999 */       for (int s = 0; s < numSources; s++) {
/* 2000 */         if (src[s] != null) {
/* 2001 */           sBandData[s] = srcData[s][b];
/* 2002 */           sLineOffsets[s] = srcBandOffsets[s][b];
/*      */         } 
/* 2004 */         if (weightTypes[s] == 1) {
/* 2005 */           aBandData[s] = alfaData[s][0];
/* 2006 */           aLineOffsets[s] = alfaBandOffsets[s][0];
/*      */         } 
/*      */       } 
/* 2011 */       double[] dBandData = dstData[b];
/* 2012 */       int dLineOffset = dstBandOffsets[b];
/* 2014 */       if (this.mosaicType == MosaicDescriptor.MOSAIC_TYPE_OVERLAY) {
/* 2015 */         for (int dstY = dstMinY; dstY < dstMaxY; dstY++) {
/* 2018 */           for (int m = 0; m < numSources; m++) {
/* 2019 */             if (src[m] != null) {
/* 2020 */               sPixelOffsets[m] = sLineOffsets[m];
/* 2021 */               sLineOffsets[m] = sLineOffsets[m] + srcLineStride[m];
/*      */             } 
/* 2023 */             if (alfa[m] != null) {
/* 2024 */               aPixelOffsets[m] = aLineOffsets[m];
/* 2025 */               aLineOffsets[m] = aLineOffsets[m] + alfaLineStride[m];
/*      */             } 
/*      */           } 
/* 2031 */           int dPixelOffset = dLineOffset;
/* 2032 */           dLineOffset += dstLineStride;
/* 2034 */           for (int dstX = dstMinX; dstX < dstMaxX; dstX++) {
/* 2037 */             boolean setDestValue = false;
/* 2041 */             for (int n = 0; n < numSources; n++) {
/* 2042 */               if (src[n] != null) {
/* 2044 */                 double sourceValue = sBandData[n][sPixelOffsets[n]];
/* 2046 */                 sPixelOffsets[n] = sPixelOffsets[n] + srcPixelStride[n];
/* 2048 */                 switch (weightTypes[n]) {
/*      */                   case 1:
/* 2050 */                     setDestValue = (aBandData[n][aPixelOffsets[n]] != 0.0D);
/* 2052 */                     aPixelOffsets[n] = aPixelOffsets[n] + alfaPixelStride[n];
/*      */                     break;
/*      */                   case 2:
/* 2055 */                     setDestValue = (roi[n].getSample(dstX, dstY, 0) > 0);
/*      */                     break;
/*      */                   default:
/* 2059 */                     setDestValue = (sourceValue >= this.sourceThreshold[n][b]);
/*      */                     break;
/*      */                 } 
/* 2066 */                 if (setDestValue) {
/* 2067 */                   dBandData[dPixelOffset] = sourceValue;
/* 2070 */                   for (int i1 = n + 1; i1 < numSources; i1++) {
/* 2071 */                     if (src[i1] != null)
/* 2072 */                       sPixelOffsets[i1] = sPixelOffsets[i1] + srcPixelStride[i1]; 
/* 2074 */                     if (alfa[i1] != null)
/* 2075 */                       aPixelOffsets[i1] = aPixelOffsets[i1] + alfaPixelStride[i1]; 
/*      */                   } 
/*      */                   break;
/*      */                 } 
/*      */               } 
/*      */             } 
/* 2084 */             if (!setDestValue)
/* 2085 */               dBandData[dPixelOffset] = this.backgroundValues[b]; 
/* 2088 */             dPixelOffset += dstPixelStride;
/*      */           } 
/*      */         } 
/*      */       } else {
/* 2092 */         for (int dstY = dstMinY; dstY < dstMaxY; dstY++) {
/* 2095 */           for (int m = 0; m < numSources; m++) {
/* 2096 */             if (src[m] != null) {
/* 2097 */               sPixelOffsets[m] = sLineOffsets[m];
/* 2098 */               sLineOffsets[m] = sLineOffsets[m] + srcLineStride[m];
/*      */             } 
/* 2100 */             if (weightTypes[m] == 1) {
/* 2101 */               aPixelOffsets[m] = aLineOffsets[m];
/* 2102 */               aLineOffsets[m] = aLineOffsets[m] + alfaLineStride[m];
/*      */             } 
/*      */           } 
/* 2108 */           int dPixelOffset = dLineOffset;
/* 2109 */           dLineOffset += dstLineStride;
/* 2111 */           for (int dstX = dstMinX; dstX < dstMaxX; dstX++) {
/* 2114 */             double numerator = 0.0D;
/* 2115 */             double denominator = 0.0D;
/* 2118 */             for (int n = 0; n < numSources; n++) {
/* 2119 */               if (src[n] != null) {
/* 2121 */                 double sourceValue = sBandData[n][sPixelOffsets[n]];
/* 2123 */                 sPixelOffsets[n] = sPixelOffsets[n] + srcPixelStride[n];
/* 2125 */                 double weight = 0.0D;
/* 2126 */                 switch (weightTypes[n]) {
/*      */                   case 1:
/* 2128 */                     weight = aBandData[n][aPixelOffsets[n]];
/* 2129 */                     if (weight > 0.0D && this.isAlphaBitmask)
/* 2130 */                       weight = 1.0D; 
/* 2132 */                     aPixelOffsets[n] = aPixelOffsets[n] + alfaPixelStride[n];
/*      */                     break;
/*      */                   case 2:
/* 2135 */                     weight = (roi[n].getSample(dstX, dstY, 0) > 0) ? 1.0D : 0.0D;
/*      */                     break;
/*      */                   default:
/* 2140 */                     weight = (sourceValue >= this.sourceThreshold[n][b]) ? 1.0D : 0.0D;
/*      */                     break;
/*      */                 } 
/* 2147 */                 numerator += weight * sourceValue;
/* 2148 */                 denominator += weight;
/*      */               } 
/*      */             } 
/* 2153 */             if (denominator == 0.0D) {
/* 2154 */               dBandData[dPixelOffset] = this.backgroundValues[b];
/*      */             } else {
/* 2156 */               dBandData[dPixelOffset] = numerator / denominator;
/*      */             } 
/* 2161 */             dPixelOffset += dstPixelStride;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\MosaicOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */