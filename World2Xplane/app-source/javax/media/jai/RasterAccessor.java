/*      */ package javax.media.jai;
/*      */ 
/*      */ import com.sun.media.jai.util.DataBufferUtils;
/*      */ import com.sun.media.jai.util.ImageUtil;
/*      */ import java.awt.Rectangle;
/*      */ import java.awt.image.ColorModel;
/*      */ import java.awt.image.ComponentSampleModel;
/*      */ import java.awt.image.DataBuffer;
/*      */ import java.awt.image.DataBufferByte;
/*      */ import java.awt.image.DataBufferInt;
/*      */ import java.awt.image.DataBufferShort;
/*      */ import java.awt.image.DataBufferUShort;
/*      */ import java.awt.image.IndexColorModel;
/*      */ import java.awt.image.Raster;
/*      */ import java.awt.image.RenderedImage;
/*      */ import java.awt.image.SampleModel;
/*      */ import java.awt.image.WritableRaster;
/*      */ 
/*      */ public class RasterAccessor {
/*      */   private static final int COPY_MASK_SHIFT = 7;
/*      */   
/*      */   private static final int COPY_MASK_SIZE = 2;
/*      */   
/*      */   public static final int COPY_MASK = 384;
/*      */   
/*      */   public static final int UNCOPIED = 0;
/*      */   
/*      */   public static final int COPIED = 128;
/*      */   
/*      */   private static final int EXPANSION_MASK_SHIFT = 9;
/*      */   
/*      */   private static final int EXPANSION_MASK_SIZE = 2;
/*      */   
/*      */   public static final int EXPANSION_MASK = 1536;
/*      */   
/*      */   public static final int DEFAULTEXPANSION = 0;
/*      */   
/*      */   public static final int EXPANDED = 512;
/*      */   
/*      */   public static final int UNEXPANDED = 1024;
/*      */   
/*      */   public static final int DATATYPE_MASK = 127;
/*      */   
/*      */   public static final int TAG_BYTE_UNCOPIED = 0;
/*      */   
/*      */   public static final int TAG_USHORT_UNCOPIED = 1;
/*      */   
/*      */   public static final int TAG_SHORT_UNCOPIED = 2;
/*      */   
/*      */   public static final int TAG_INT_UNCOPIED = 3;
/*      */   
/*      */   public static final int TAG_FLOAT_UNCOPIED = 4;
/*      */   
/*      */   public static final int TAG_DOUBLE_UNCOPIED = 5;
/*      */   
/*      */   public static final int TAG_INT_COPIED = 131;
/*      */   
/*      */   public static final int TAG_FLOAT_COPIED = 132;
/*      */   
/*      */   public static final int TAG_DOUBLE_COPIED = 133;
/*      */   
/*      */   public static final int TAG_BYTE_EXPANDED = 512;
/*      */   
/*      */   private static final int TAG_BINARY = 1152;
/*      */   
/*      */   protected Raster raster;
/*      */   
/*      */   protected int rectWidth;
/*      */   
/*      */   protected int rectHeight;
/*      */   
/*      */   protected int rectX;
/*      */   
/*      */   protected int rectY;
/*      */   
/*      */   protected int formatTagID;
/*      */   
/*  192 */   protected byte[] binaryDataArray = null;
/*      */   
/*  205 */   protected byte[][] byteDataArrays = (byte[][])null;
/*      */   
/*  215 */   protected short[][] shortDataArrays = (short[][])null;
/*      */   
/*  224 */   protected int[][] intDataArrays = (int[][])null;
/*      */   
/*  233 */   protected float[][] floatDataArrays = (float[][])null;
/*      */   
/*  242 */   protected double[][] doubleDataArrays = (double[][])null;
/*      */   
/*      */   protected int[] bandDataOffsets;
/*      */   
/*      */   protected int[] bandOffsets;
/*      */   
/*      */   protected int numBands;
/*      */   
/*      */   protected int scanlineStride;
/*      */   
/*      */   protected int pixelStride;
/*      */   
/*      */   public static RasterFormatTag[] findCompatibleTags(RenderedImage[] srcs, RenderedImage dst) {
/*      */     int[] tagIDs;
/*  278 */     if (srcs != null) {
/*  279 */       tagIDs = new int[srcs.length + 1];
/*      */     } else {
/*  281 */       tagIDs = new int[1];
/*      */     } 
/*  283 */     SampleModel dstSampleModel = dst.getSampleModel();
/*  284 */     int dstDataType = dstSampleModel.getTransferType();
/*  286 */     int defaultDataType = dstDataType;
/*  287 */     boolean binaryDst = ImageUtil.isBinary(dstSampleModel);
/*  288 */     if (binaryDst) {
/*  289 */       defaultDataType = 0;
/*  290 */     } else if (dstDataType == 0 || dstDataType == 1 || dstDataType == 2) {
/*  293 */       defaultDataType = 3;
/*      */     } 
/*  297 */     if (srcs != null) {
/*  298 */       int numSources = srcs.length;
/*  300 */       for (int i = 0; i < numSources; i++) {
/*  301 */         SampleModel srcSampleModel = srcs[i].getSampleModel();
/*  302 */         int srcDataType = srcSampleModel.getTransferType();
/*  303 */         if ((!binaryDst || !ImageUtil.isBinary(srcSampleModel)) && srcDataType > defaultDataType)
/*  305 */           defaultDataType = srcDataType; 
/*      */       } 
/*      */     } 
/*  312 */     int tagID = defaultDataType | 0x80;
/*  314 */     if (dstSampleModel instanceof ComponentSampleModel)
/*  315 */       if (srcs != null) {
/*  316 */         int numSources = srcs.length;
/*      */         int i;
/*  318 */         for (i = 0; i < numSources; i++) {
/*  319 */           SampleModel srcSampleModel = srcs[i].getSampleModel();
/*  320 */           int srcDataType = srcSampleModel.getTransferType();
/*  321 */           if (!(srcSampleModel instanceof ComponentSampleModel) || srcDataType != dstDataType)
/*      */             break; 
/*      */         } 
/*  326 */         if (i == numSources)
/*  327 */           tagID = dstDataType | 0x0; 
/*      */       } else {
/*  330 */         tagID = dstDataType | 0x0;
/*      */       }  
/*  337 */     RasterFormatTag[] rft = new RasterFormatTag[tagIDs.length];
/*  338 */     if (srcs != null) {
/*      */       int i;
/*  339 */       for (i = 0; i < srcs.length; i++) {
/*  341 */         if (srcs[i].getColorModel() instanceof IndexColorModel) {
/*  342 */           if (dst.getColorModel() instanceof IndexColorModel) {
/*  343 */             tagIDs[i] = tagID | 0x400;
/*      */           } else {
/*  345 */             tagIDs[i] = tagID | 0x200;
/*      */           } 
/*  347 */         } else if (srcs[i].getColorModel() instanceof java.awt.image.ComponentColorModel || (binaryDst && ImageUtil.isBinary(srcs[i].getSampleModel()))) {
/*  351 */           tagIDs[i] = tagID | 0x400;
/*      */         } else {
/*  353 */           tagIDs[i] = tagID | 0x0;
/*      */         } 
/*      */       } 
/*  356 */       tagIDs[srcs.length] = tagID | 0x400;
/*  358 */       for (i = 0; i < srcs.length; i++)
/*  359 */         rft[i] = new RasterFormatTag(srcs[i].getSampleModel(), tagIDs[i]); 
/*  363 */       rft[srcs.length] = new RasterFormatTag(dstSampleModel, tagIDs[srcs.length]);
/*      */     } else {
/*  366 */       rft[0] = new RasterFormatTag(dstSampleModel, tagID | 0x400);
/*      */     } 
/*  369 */     return rft;
/*      */   }
/*      */   
/*      */   public static int findCompatibleTag(SampleModel[] srcSampleModels, SampleModel dstSampleModel) {
/*  381 */     int dstDataType = dstSampleModel.getTransferType();
/*  383 */     int tag = dstDataType | 0x80;
/*  384 */     if (ImageUtil.isBinary(dstSampleModel)) {
/*  385 */       tag = 128;
/*  386 */     } else if (dstDataType == 0 || dstDataType == 1 || dstDataType == 2) {
/*  389 */       tag = 131;
/*      */     } 
/*  392 */     if (dstSampleModel instanceof ComponentSampleModel)
/*  393 */       if (srcSampleModels != null) {
/*  394 */         int numSources = srcSampleModels.length;
/*      */         int i;
/*  396 */         for (i = 0; i < numSources; i++) {
/*  397 */           int srcDataType = srcSampleModels[i].getTransferType();
/*  399 */           if (!(srcSampleModels[i] instanceof ComponentSampleModel) || srcDataType != dstDataType)
/*      */             break; 
/*      */         } 
/*  404 */         if (i == numSources)
/*  405 */           tag = dstDataType | 0x0; 
/*      */       } else {
/*  408 */         tag = dstDataType | 0x0;
/*      */       }  
/*  411 */     return tag | 0x400;
/*      */   }
/*      */   
/*      */   public RasterAccessor(Raster raster, Rectangle rect, RasterFormatTag rft, ColorModel theColorModel) {
/*  443 */     if (raster == null || rect == null || rft == null)
/*  444 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*  449 */     if (!raster.getBounds().contains(rect))
/*  450 */       throw new IllegalArgumentException(JaiI18N.getString("RasterAccessor2")); 
/*  454 */     this.raster = raster;
/*  455 */     this.rectX = rect.x;
/*  456 */     this.rectY = rect.y;
/*  457 */     this.rectWidth = rect.width;
/*  458 */     this.rectHeight = rect.height;
/*  459 */     this.formatTagID = rft.getFormatTagID();
/*  460 */     if ((this.formatTagID & 0x180) == 0) {
/*      */       DataBufferByte dbb;
/*      */       int i;
/*      */       DataBufferUShort dbus;
/*      */       int j;
/*      */       DataBufferShort dbs;
/*      */       int k;
/*      */       DataBufferInt dbi;
/*      */       int m;
/*      */       DataBuffer dbf;
/*      */       int n;
/*      */       DataBuffer dbd;
/*      */       int i1;
/*  462 */       this.numBands = rft.getNumBands();
/*  463 */       this.pixelStride = rft.getPixelStride();
/*  465 */       ComponentSampleModel csm = (ComponentSampleModel)raster.getSampleModel();
/*  467 */       this.scanlineStride = csm.getScanlineStride();
/*  469 */       int[] bankIndices = null;
/*  477 */       if (rft.isPixelSequential()) {
/*  478 */         this.bandOffsets = rft.getBandOffsets();
/*  479 */         bankIndices = rft.getBankIndices();
/*      */       } else {
/*  481 */         this.bandOffsets = csm.getBandOffsets();
/*  482 */         bankIndices = csm.getBankIndices();
/*      */       } 
/*  485 */       this.bandDataOffsets = new int[this.numBands];
/*  487 */       int[] dataBufferOffsets = raster.getDataBuffer().getOffsets();
/*  489 */       int subRasterOffset = (this.rectY - raster.getSampleModelTranslateY()) * this.scanlineStride + (this.rectX - raster.getSampleModelTranslateX()) * this.pixelStride;
/*  493 */       if (dataBufferOffsets.length == 1) {
/*  494 */         int theDataBufferOffset = dataBufferOffsets[0];
/*  495 */         for (int i2 = 0; i2 < this.numBands; i2++)
/*  496 */           this.bandDataOffsets[i2] = this.bandOffsets[i2] + theDataBufferOffset + subRasterOffset; 
/*  499 */       } else if (dataBufferOffsets.length == this.bandDataOffsets.length) {
/*  500 */         for (int i2 = 0; i2 < this.numBands; i2++)
/*  501 */           this.bandDataOffsets[i2] = this.bandOffsets[i2] + dataBufferOffsets[i2] + subRasterOffset; 
/*      */       } else {
/*  505 */         throw new RuntimeException(JaiI18N.getString("RasterAccessor0"));
/*      */       } 
/*  508 */       switch (this.formatTagID & 0x7F) {
/*      */         case 0:
/*  510 */           dbb = (DataBufferByte)raster.getDataBuffer();
/*  511 */           this.byteDataArrays = new byte[this.numBands][];
/*  512 */           for (i = 0; i < this.numBands; i++)
/*  513 */             this.byteDataArrays[i] = dbb.getData(bankIndices[i]); 
/*      */           break;
/*      */         case 1:
/*  518 */           dbus = (DataBufferUShort)raster.getDataBuffer();
/*  520 */           this.shortDataArrays = new short[this.numBands][];
/*  521 */           for (j = 0; j < this.numBands; j++)
/*  522 */             this.shortDataArrays[j] = dbus.getData(bankIndices[j]); 
/*      */           break;
/*      */         case 2:
/*  527 */           dbs = (DataBufferShort)raster.getDataBuffer();
/*  528 */           this.shortDataArrays = new short[this.numBands][];
/*  529 */           for (k = 0; k < this.numBands; k++)
/*  530 */             this.shortDataArrays[k] = dbs.getData(bankIndices[k]); 
/*      */           break;
/*      */         case 3:
/*  535 */           dbi = (DataBufferInt)raster.getDataBuffer();
/*  536 */           this.intDataArrays = new int[this.numBands][];
/*  537 */           for (m = 0; m < this.numBands; m++)
/*  538 */             this.intDataArrays[m] = dbi.getData(bankIndices[m]); 
/*      */           break;
/*      */         case 4:
/*  543 */           dbf = raster.getDataBuffer();
/*  544 */           this.floatDataArrays = new float[this.numBands][];
/*  545 */           for (n = 0; n < this.numBands; n++)
/*  546 */             this.floatDataArrays[n] = DataBufferUtils.getDataFloat(dbf, bankIndices[n]); 
/*      */           break;
/*      */         case 5:
/*  552 */           dbd = raster.getDataBuffer();
/*  553 */           this.doubleDataArrays = new double[this.numBands][];
/*  554 */           for (i1 = 0; i1 < this.numBands; i1++)
/*  555 */             this.doubleDataArrays[i1] = DataBufferUtils.getDataDouble(dbd, bankIndices[i1]); 
/*      */           break;
/*      */       } 
/*  561 */       if ((this.formatTagID & 0x600) == 512 && theColorModel instanceof IndexColorModel) {
/*      */         byte[] newBArray;
/*      */         short[] arrayOfShort1;
/*      */         int[] newIArray;
/*      */         float[] newFArray;
/*      */         double[] newDArray;
/*      */         byte[] byteDataArray;
/*      */         short[] shortDataArray;
/*      */         int[] intDataArray;
/*      */         float[] floatDataArray;
/*      */         double[] doubleDataArray;
/*      */         int scanlineOffset, newScanlineOffset, i14, i13, i12, i11, i10, i9, i8, i7, i6, i5, i4, i3;
/*  563 */         IndexColorModel icm = (IndexColorModel)theColorModel;
/*  565 */         int newNumBands = icm.getNumComponents();
/*  567 */         int mapSize = icm.getMapSize();
/*  568 */         int[] newBandDataOffsets = new int[newNumBands];
/*  569 */         int newScanlineStride = this.rectWidth * newNumBands;
/*  570 */         int newPixelStride = newNumBands;
/*  571 */         byte[][] ctable = new byte[newNumBands][mapSize];
/*  573 */         icm.getReds(ctable[0]);
/*  574 */         icm.getGreens(ctable[1]);
/*  575 */         icm.getBlues(ctable[2]);
/*  576 */         byte[] rtable = ctable[0];
/*  577 */         byte[] gtable = ctable[1];
/*  578 */         byte[] btable = ctable[2];
/*  580 */         byte[] atable = null;
/*  581 */         if (newNumBands == 4) {
/*  582 */           icm.getAlphas(ctable[3]);
/*  583 */           atable = ctable[3];
/*      */         } 
/*  586 */         for (int i2 = 0; i2 < newNumBands; i2++)
/*  587 */           newBandDataOffsets[i2] = i2; 
/*  590 */         switch (this.formatTagID & 0x7F) {
/*      */           case 0:
/*  592 */             newBArray = new byte[this.rectWidth * this.rectHeight * newNumBands];
/*  594 */             byteDataArray = this.byteDataArrays[0];
/*  595 */             scanlineOffset = this.bandDataOffsets[0];
/*  596 */             newScanlineOffset = 0;
/*  597 */             for (i14 = 0; i14 < this.rectHeight; i14++) {
/*  598 */               int pixelOffset = scanlineOffset;
/*  599 */               int newPixelOffset = newScanlineOffset;
/*  600 */               for (int i15 = 0; i15 < this.rectWidth; i15++) {
/*  601 */                 int index = byteDataArray[pixelOffset] & 0xFF;
/*  602 */                 for (int i16 = 0; i16 < newNumBands; i16++)
/*  603 */                   newBArray[newPixelOffset + i16] = ctable[i16][index]; 
/*  606 */                 pixelOffset += this.pixelStride;
/*  607 */                 newPixelOffset += newPixelStride;
/*      */               } 
/*  609 */               scanlineOffset += this.scanlineStride;
/*  610 */               newScanlineOffset += newScanlineStride;
/*      */             } 
/*  612 */             this.byteDataArrays = new byte[newNumBands][];
/*  613 */             for (i13 = 0; i13 < newNumBands; i13++)
/*  614 */               this.byteDataArrays[i13] = newBArray; 
/*      */             break;
/*      */           case 1:
/*  620 */             arrayOfShort1 = new short[this.rectWidth * this.rectHeight * newNumBands];
/*  622 */             shortDataArray = this.shortDataArrays[0];
/*  623 */             scanlineOffset = this.bandDataOffsets[0];
/*  624 */             newScanlineOffset = 0;
/*  625 */             for (i12 = 0; i12 < this.rectHeight; i12++) {
/*  626 */               int pixelOffset = scanlineOffset;
/*  627 */               int newPixelOffset = newScanlineOffset;
/*  628 */               for (int i15 = 0; i15 < this.rectWidth; i15++) {
/*  629 */                 int index = shortDataArray[pixelOffset] & 0xFFFF;
/*  630 */                 for (int i16 = 0; i16 < newNumBands; i16++)
/*  631 */                   arrayOfShort1[newPixelOffset + i16] = (short)(ctable[i16][index] & 0xFF); 
/*  634 */                 pixelOffset += this.pixelStride;
/*  635 */                 newPixelOffset += newPixelStride;
/*      */               } 
/*  637 */               scanlineOffset += this.scanlineStride;
/*  638 */               newScanlineOffset += newScanlineStride;
/*      */             } 
/*  641 */             this.shortDataArrays = new short[newNumBands][];
/*  642 */             for (i11 = 0; i11 < newNumBands; i11++)
/*  643 */               this.shortDataArrays[i11] = arrayOfShort1; 
/*      */             break;
/*      */           case 2:
/*  649 */             arrayOfShort1 = new short[this.rectWidth * this.rectHeight * newNumBands];
/*  651 */             shortDataArray = this.shortDataArrays[0];
/*  652 */             scanlineOffset = this.bandDataOffsets[0];
/*  653 */             newScanlineOffset = 0;
/*  654 */             for (i10 = 0; i10 < this.rectHeight; i10++) {
/*  655 */               int pixelOffset = scanlineOffset;
/*  656 */               int newPixelOffset = newScanlineOffset;
/*  657 */               for (int i15 = 0; i15 < this.rectWidth; i15++) {
/*  658 */                 int index = shortDataArray[pixelOffset];
/*  659 */                 for (int i16 = 0; i16 < newNumBands; i16++)
/*  660 */                   arrayOfShort1[newPixelOffset + i16] = (short)(ctable[i16][index] & 0xFF); 
/*  663 */                 pixelOffset += this.pixelStride;
/*  664 */                 newPixelOffset += newPixelStride;
/*      */               } 
/*  666 */               scanlineOffset += this.scanlineStride;
/*  667 */               newScanlineOffset += newScanlineStride;
/*      */             } 
/*  670 */             this.shortDataArrays = new short[newNumBands][];
/*  671 */             for (i9 = 0; i9 < newNumBands; i9++)
/*  672 */               this.shortDataArrays[i9] = arrayOfShort1; 
/*      */             break;
/*      */           case 3:
/*  678 */             newIArray = new int[this.rectWidth * this.rectHeight * newNumBands];
/*  680 */             intDataArray = this.intDataArrays[0];
/*  681 */             scanlineOffset = this.bandDataOffsets[0];
/*  682 */             newScanlineOffset = 0;
/*  683 */             for (i8 = 0; i8 < this.rectHeight; i8++) {
/*  684 */               int pixelOffset = scanlineOffset;
/*  685 */               int newPixelOffset = newScanlineOffset;
/*  686 */               for (int i15 = 0; i15 < this.rectWidth; i15++) {
/*  687 */                 int index = intDataArray[pixelOffset];
/*  688 */                 for (int i16 = 0; i16 < newNumBands; i16++)
/*  689 */                   newIArray[newPixelOffset + i16] = ctable[i16][index] & 0xFF; 
/*  692 */                 pixelOffset += this.pixelStride;
/*  693 */                 newPixelOffset += newPixelStride;
/*      */               } 
/*  695 */               scanlineOffset += this.scanlineStride;
/*  696 */               newScanlineOffset += newScanlineStride;
/*      */             } 
/*  699 */             this.intDataArrays = new int[newNumBands][];
/*  700 */             for (i7 = 0; i7 < newNumBands; i7++)
/*  701 */               this.intDataArrays[i7] = newIArray; 
/*      */             break;
/*      */           case 4:
/*  707 */             newFArray = new float[this.rectWidth * this.rectHeight * newNumBands];
/*  709 */             floatDataArray = this.floatDataArrays[0];
/*  710 */             scanlineOffset = this.bandDataOffsets[0];
/*  711 */             newScanlineOffset = 0;
/*  712 */             for (i6 = 0; i6 < this.rectHeight; i6++) {
/*  713 */               int pixelOffset = scanlineOffset;
/*  714 */               int newPixelOffset = newScanlineOffset;
/*  715 */               for (int i15 = 0; i15 < this.rectWidth; i15++) {
/*  716 */                 int index = (int)floatDataArray[pixelOffset];
/*  717 */                 for (int i16 = 0; i16 < newNumBands; i16++)
/*  718 */                   newFArray[newPixelOffset + i16] = (ctable[i16][index] & 0xFF); 
/*  721 */                 pixelOffset += this.pixelStride;
/*  722 */                 newPixelOffset += newPixelStride;
/*      */               } 
/*  724 */               scanlineOffset += this.scanlineStride;
/*  725 */               newScanlineOffset += newScanlineStride;
/*      */             } 
/*  727 */             this.floatDataArrays = new float[newNumBands][];
/*  728 */             for (i5 = 0; i5 < newNumBands; i5++)
/*  729 */               this.floatDataArrays[i5] = newFArray; 
/*      */             break;
/*      */           case 5:
/*  735 */             newDArray = new double[this.rectWidth * this.rectHeight * newNumBands];
/*  737 */             doubleDataArray = this.doubleDataArrays[0];
/*  738 */             scanlineOffset = this.bandDataOffsets[0];
/*  739 */             newScanlineOffset = 0;
/*  740 */             for (i4 = 0; i4 < this.rectHeight; i4++) {
/*  741 */               int pixelOffset = scanlineOffset;
/*  742 */               int newPixelOffset = newScanlineOffset;
/*  743 */               for (int i15 = 0; i15 < this.rectWidth; i15++) {
/*  744 */                 int index = (int)doubleDataArray[pixelOffset];
/*  745 */                 for (int i16 = 0; i16 < newNumBands; i16++)
/*  746 */                   newDArray[newPixelOffset + i16] = (ctable[i16][index] & 0xFF); 
/*  749 */                 pixelOffset += this.pixelStride;
/*  750 */                 newPixelOffset += newPixelStride;
/*      */               } 
/*  752 */               scanlineOffset += this.scanlineStride;
/*  753 */               newScanlineOffset += newScanlineStride;
/*      */             } 
/*  755 */             this.doubleDataArrays = new double[newNumBands][];
/*  756 */             for (i3 = 0; i3 < newNumBands; i3++)
/*  757 */               this.doubleDataArrays[i3] = newDArray; 
/*      */             break;
/*      */         } 
/*  762 */         this.numBands = newNumBands;
/*  763 */         this.pixelStride = newPixelStride;
/*  764 */         this.scanlineStride = newScanlineStride;
/*  765 */         this.bandDataOffsets = newBandDataOffsets;
/*  766 */         this.bandOffsets = newBandDataOffsets;
/*      */       } 
/*  768 */     } else if ((this.formatTagID & 0x180) == 128 && (this.formatTagID & 0x600) != 1024 && theColorModel != null) {
/*      */       int idata[], k;
/*      */       byte[] bdata;
/*      */       int j;
/*      */       float[] fdata;
/*      */       int n, m;
/*      */       double[] ddata;
/*      */       int i2, i1;
/*  771 */       this.numBands = (theColorModel instanceof IndexColorModel) ? theColorModel.getNumComponents() : raster.getSampleModel().getNumBands();
/*  774 */       this.pixelStride = this.numBands;
/*  775 */       this.scanlineStride = this.rectWidth * this.numBands;
/*  776 */       this.bandOffsets = new int[this.numBands];
/*  778 */       for (int i = 0; i < this.numBands; i++)
/*  779 */         this.bandOffsets[i] = i; 
/*  781 */       this.bandDataOffsets = this.bandOffsets;
/*  783 */       Object odata = null;
/*  784 */       int offset = 0;
/*  786 */       int[] components = new int[theColorModel.getNumComponents()];
/*  788 */       switch (this.formatTagID & 0x7F) {
/*      */         case 3:
/*  791 */           idata = new int[this.rectWidth * this.rectHeight * this.numBands];
/*  792 */           this.intDataArrays = new int[this.numBands][];
/*  793 */           for (k = 0; k < this.numBands; k++)
/*  794 */             this.intDataArrays[k] = idata; 
/*  797 */           odata = raster.getDataElements(this.rectX, this.rectY, null);
/*  798 */           offset = 0;
/*  801 */           bdata = null;
/*  802 */           if (raster instanceof sun.awt.image.BytePackedRaster)
/*  803 */             bdata = (byte[])odata; 
/*  806 */           for (j = this.rectY; j < this.rectY + this.rectHeight; j++) {
/*  807 */             for (int i3 = this.rectX; i3 < this.rectX + this.rectWidth; i3++) {
/*  808 */               if (bdata != null) {
/*  809 */                 bdata[0] = (byte)raster.getSample(i3, j, 0);
/*      */               } else {
/*  811 */                 raster.getDataElements(i3, j, odata);
/*      */               } 
/*  814 */               theColorModel.getComponents(odata, components, 0);
/*  816 */               idata[offset] = components[0];
/*  817 */               idata[offset + 1] = components[1];
/*  818 */               idata[offset + 2] = components[2];
/*  819 */               if (this.numBands > 3)
/*  820 */                 idata[offset + 3] = components[3]; 
/*  823 */               offset += this.pixelStride;
/*      */             } 
/*      */           } 
/*      */           break;
/*      */         case 4:
/*  829 */           fdata = new float[this.rectWidth * this.rectHeight * this.numBands];
/*  830 */           this.floatDataArrays = new float[this.numBands][];
/*  831 */           for (n = 0; n < this.numBands; n++)
/*  832 */             this.floatDataArrays[n] = fdata; 
/*  834 */           odata = null;
/*  835 */           offset = 0;
/*  836 */           for (m = this.rectY; m < this.rectY + this.rectHeight; m++) {
/*  837 */             for (int i3 = this.rectX; i3 < this.rectX + this.rectWidth; i3++) {
/*  838 */               odata = raster.getDataElements(i3, m, odata);
/*  840 */               theColorModel.getComponents(odata, components, 0);
/*  842 */               fdata[offset] = components[0];
/*  843 */               fdata[offset + 1] = components[1];
/*  844 */               fdata[offset + 2] = components[2];
/*  845 */               if (this.numBands > 3)
/*  846 */                 fdata[offset + 3] = components[3]; 
/*  848 */               offset += this.pixelStride;
/*      */             } 
/*      */           } 
/*      */           break;
/*      */         case 5:
/*  854 */           ddata = new double[this.rectWidth * this.rectHeight * this.numBands];
/*  855 */           this.doubleDataArrays = new double[this.numBands][];
/*  856 */           for (i2 = 0; i2 < this.numBands; i2++)
/*  857 */             this.doubleDataArrays[i2] = ddata; 
/*  859 */           odata = null;
/*  860 */           offset = 0;
/*  861 */           for (i1 = this.rectY; i1 < this.rectY + this.rectHeight; i1++) {
/*  862 */             for (int i3 = this.rectX; i3 < this.rectX + this.rectWidth; i3++) {
/*  863 */               odata = raster.getDataElements(i3, i1, odata);
/*  865 */               theColorModel.getComponents(odata, components, 0);
/*  867 */               ddata[offset] = components[0];
/*  868 */               ddata[offset + 1] = components[1];
/*  869 */               ddata[offset + 2] = components[2];
/*  870 */               if (this.numBands > 3)
/*  871 */                 ddata[offset + 3] = components[3]; 
/*  873 */               offset += this.pixelStride;
/*      */             } 
/*      */           } 
/*      */           break;
/*      */       } 
/*      */     } else {
/*      */       int idata[], i;
/*      */       float[] fdata;
/*      */       int j;
/*      */       double[] ddata;
/*      */       int k;
/*  885 */       this.numBands = rft.getNumBands();
/*  886 */       this.pixelStride = this.numBands;
/*  887 */       this.scanlineStride = this.rectWidth * this.numBands;
/*  888 */       this.bandDataOffsets = rft.getBandOffsets();
/*  889 */       this.bandOffsets = this.bandDataOffsets;
/*  891 */       switch (this.formatTagID & 0x7F) {
/*      */         case 3:
/*  893 */           idata = raster.getPixels(this.rectX, this.rectY, this.rectWidth, this.rectHeight, (int[])null);
/*  896 */           this.intDataArrays = new int[this.numBands][];
/*  897 */           for (i = 0; i < this.numBands; i++)
/*  898 */             this.intDataArrays[i] = idata; 
/*      */           break;
/*      */         case 4:
/*  903 */           fdata = raster.getPixels(this.rectX, this.rectY, this.rectWidth, this.rectHeight, (float[])null);
/*  906 */           this.floatDataArrays = new float[this.numBands][];
/*  907 */           for (j = 0; j < this.numBands; j++)
/*  908 */             this.floatDataArrays[j] = fdata; 
/*      */           break;
/*      */         case 5:
/*  913 */           ddata = raster.getPixels(this.rectX, this.rectY, this.rectWidth, this.rectHeight, (double[])null);
/*  916 */           this.doubleDataArrays = new double[this.numBands][];
/*  917 */           for (k = 0; k < this.numBands; k++)
/*  918 */             this.doubleDataArrays[k] = ddata; 
/*      */           break;
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public int getX() {
/*  930 */     return this.rectX;
/*      */   }
/*      */   
/*      */   public int getY() {
/*  938 */     return this.rectY;
/*      */   }
/*      */   
/*      */   public int getWidth() {
/*  945 */     return this.rectWidth;
/*      */   }
/*      */   
/*      */   public int getHeight() {
/*  952 */     return this.rectHeight;
/*      */   }
/*      */   
/*      */   public int getNumBands() {
/*  957 */     return this.numBands;
/*      */   }
/*      */   
/*      */   public boolean isBinary() {
/*  970 */     return ((this.formatTagID & 0x480) == 1152 && ImageUtil.isBinary(this.raster.getSampleModel()));
/*      */   }
/*      */   
/*      */   public byte[] getBinaryDataArray() {
/*  989 */     if (this.binaryDataArray == null && isBinary())
/*  990 */       this.binaryDataArray = ImageUtil.getPackedBinaryData(this.raster, new Rectangle(this.rectX, this.rectY, this.rectWidth, this.rectHeight)); 
/*  995 */     return this.binaryDataArray;
/*      */   }
/*      */   
/*      */   public byte[][] getByteDataArrays() {
/* 1008 */     if (this.byteDataArrays == null && isBinary()) {
/* 1009 */       byte[] bdata = ImageUtil.getUnpackedBinaryData(this.raster, new Rectangle(this.rectX, this.rectY, this.rectWidth, this.rectHeight));
/* 1014 */       this.byteDataArrays = new byte[][] { bdata };
/*      */     } 
/* 1016 */     return this.byteDataArrays;
/*      */   }
/*      */   
/*      */   public byte[] getByteDataArray(int b) {
/* 1024 */     byte[][] bda = getByteDataArrays();
/* 1025 */     return (bda == null) ? null : bda[b];
/*      */   }
/*      */   
/*      */   public short[][] getShortDataArrays() {
/* 1033 */     return this.shortDataArrays;
/*      */   }
/*      */   
/*      */   public short[] getShortDataArray(int b) {
/* 1042 */     return (this.shortDataArrays == null) ? null : this.shortDataArrays[b];
/*      */   }
/*      */   
/*      */   public int[][] getIntDataArrays() {
/* 1050 */     return this.intDataArrays;
/*      */   }
/*      */   
/*      */   public int[] getIntDataArray(int b) {
/* 1058 */     return (this.intDataArrays == null) ? null : this.intDataArrays[b];
/*      */   }
/*      */   
/*      */   public float[][] getFloatDataArrays() {
/* 1066 */     return this.floatDataArrays;
/*      */   }
/*      */   
/*      */   public float[] getFloatDataArray(int b) {
/* 1074 */     return (this.floatDataArrays == null) ? null : this.floatDataArrays[b];
/*      */   }
/*      */   
/*      */   public double[][] getDoubleDataArrays() {
/* 1082 */     return this.doubleDataArrays;
/*      */   }
/*      */   
/*      */   public double[] getDoubleDataArray(int b) {
/* 1090 */     return (this.doubleDataArrays == null) ? null : this.doubleDataArrays[b];
/*      */   }
/*      */   
/*      */   public Object getDataArray(int b) {
/* 1099 */     Object dataArray = null;
/* 1100 */     switch (getDataType()) {
/*      */       case 0:
/* 1102 */         dataArray = getByteDataArray(b);
/* 1126 */         return dataArray;
/*      */       case 1:
/*      */       case 2:
/*      */         dataArray = getShortDataArray(b);
/* 1126 */         return dataArray;
/*      */       case 3:
/*      */         dataArray = getIntDataArray(b);
/* 1126 */         return dataArray;
/*      */       case 4:
/*      */         dataArray = getFloatDataArray(b);
/* 1126 */         return dataArray;
/*      */       case 5:
/*      */         dataArray = getDoubleDataArray(b);
/* 1126 */         return dataArray;
/*      */     } 
/*      */     dataArray = null;
/* 1126 */     return dataArray;
/*      */   }
/*      */   
/*      */   public int[] getBandOffsets() {
/* 1131 */     return this.bandDataOffsets;
/*      */   }
/*      */   
/*      */   public int[] getOffsetsForBands() {
/* 1139 */     return this.bandOffsets;
/*      */   }
/*      */   
/*      */   public int getBandOffset(int b) {
/* 1147 */     return this.bandDataOffsets[b];
/*      */   }
/*      */   
/*      */   public int getOffsetForBand(int b) {
/* 1155 */     return this.bandOffsets[b];
/*      */   }
/*      */   
/*      */   public int getScanlineStride() {
/* 1168 */     return this.scanlineStride;
/*      */   }
/*      */   
/*      */   public int getPixelStride() {
/* 1173 */     return this.pixelStride;
/*      */   }
/*      */   
/*      */   public int getDataType() {
/* 1182 */     return this.formatTagID & 0x7F;
/*      */   }
/*      */   
/*      */   public boolean isDataCopy() {
/* 1190 */     return ((this.formatTagID & 0x180) == 128);
/*      */   }
/*      */   
/*      */   public void copyBinaryDataToRaster() {
/* 1206 */     if (this.binaryDataArray == null || !isBinary())
/*      */       return; 
/* 1210 */     ImageUtil.setPackedBinaryData(this.binaryDataArray, (WritableRaster)this.raster, new Rectangle(this.rectX, this.rectY, this.rectWidth, this.rectHeight));
/*      */   }
/*      */   
/*      */   public void copyDataToRaster() {
/* 1229 */     if (isDataCopy()) {
/* 1233 */       WritableRaster wr = (WritableRaster)this.raster;
/* 1234 */       switch (getDataType()) {
/*      */         case 0:
/* 1238 */           if (!isBinary())
/* 1243 */             throw new RuntimeException(JaiI18N.getString("RasterAccessor1")); 
/* 1248 */           ImageUtil.setUnpackedBinaryData(this.byteDataArrays[0], wr, new Rectangle(this.rectX, this.rectY, this.rectWidth, this.rectHeight));
/*      */           break;
/*      */         case 3:
/* 1255 */           wr.setPixels(this.rectX, this.rectY, this.rectWidth, this.rectHeight, this.intDataArrays[0]);
/*      */           break;
/*      */         case 4:
/* 1261 */           wr.setPixels(this.rectX, this.rectY, this.rectWidth, this.rectHeight, this.floatDataArrays[0]);
/*      */           break;
/*      */         case 5:
/* 1267 */           wr.setPixels(this.rectX, this.rectY, this.rectWidth, this.rectHeight, this.doubleDataArrays[0]);
/*      */           break;
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public boolean needsClamping() {
/* 1282 */     int[] bits = this.raster.getSampleModel().getSampleSize();
/* 1290 */     for (int i = 0; i < bits.length; i++) {
/* 1291 */       if (bits[i] < 32)
/* 1292 */         return true; 
/*      */     } 
/* 1295 */     return false;
/*      */   }
/*      */   
/*      */   public void clampDataArrays() {
/* 1312 */     int[] bits = this.raster.getSampleModel().getSampleSize();
/* 1320 */     boolean needClamp = false;
/* 1321 */     boolean uniformBitSize = true;
/* 1322 */     int bitSize = bits[0];
/* 1323 */     for (int i = 0; i < bits.length; i++) {
/* 1324 */       if (bits[i] < 32)
/* 1325 */         needClamp = true; 
/* 1327 */       if (bits[i] != bitSize)
/* 1328 */         uniformBitSize = false; 
/*      */     } 
/* 1331 */     if (!needClamp)
/*      */       return; 
/* 1335 */     int dataType = this.raster.getDataBuffer().getDataType();
/* 1336 */     double[] hiVals = new double[bits.length];
/* 1337 */     double[] loVals = new double[bits.length];
/* 1339 */     if (dataType == 1 && uniformBitSize && bits[0] == 16) {
/* 1341 */       for (int j = 0; j < bits.length; j++) {
/* 1342 */         hiVals[j] = 65535.0D;
/* 1343 */         loVals[j] = 0.0D;
/*      */       } 
/* 1345 */     } else if (dataType == 2 && uniformBitSize && bits[0] == 16) {
/* 1347 */       for (int j = 0; j < bits.length; j++) {
/* 1348 */         hiVals[j] = 32767.0D;
/* 1349 */         loVals[j] = -32768.0D;
/*      */       } 
/* 1351 */     } else if (dataType == 3 && uniformBitSize && bits[0] == 32) {
/* 1353 */       for (int j = 0; j < bits.length; j++) {
/* 1354 */         hiVals[j] = 2.147483647E9D;
/* 1355 */         loVals[j] = -2.147483648E9D;
/*      */       } 
/*      */     } else {
/* 1358 */       for (int j = 0; j < bits.length; j++) {
/* 1359 */         hiVals[j] = ((1 << bits[j]) - 1);
/* 1360 */         loVals[j] = 0.0D;
/*      */       } 
/*      */     } 
/* 1363 */     clampDataArray(hiVals, loVals);
/*      */   }
/*      */   
/*      */   private void clampDataArray(double[] hiVals, double[] loVals) {
/* 1367 */     switch (getDataType()) {
/*      */       case 3:
/* 1369 */         clampIntArrays(toIntArray(hiVals), toIntArray(loVals));
/*      */         break;
/*      */       case 4:
/* 1373 */         clampFloatArrays(toFloatArray(hiVals), toFloatArray(loVals));
/*      */         break;
/*      */       case 5:
/* 1377 */         clampDoubleArrays(hiVals, loVals);
/*      */         break;
/*      */     } 
/*      */   }
/*      */   
/*      */   private int[] toIntArray(double[] vals) {
/* 1383 */     int[] returnVals = new int[vals.length];
/* 1384 */     for (int i = 0; i < vals.length; i++)
/* 1385 */       returnVals[i] = (int)vals[i]; 
/* 1387 */     return returnVals;
/*      */   }
/*      */   
/*      */   private float[] toFloatArray(double[] vals) {
/* 1391 */     float[] returnVals = new float[vals.length];
/* 1392 */     for (int i = 0; i < vals.length; i++)
/* 1393 */       returnVals[i] = (float)vals[i]; 
/* 1395 */     return returnVals;
/*      */   }
/*      */   
/*      */   private void clampIntArrays(int[] hiVals, int[] loVals) {
/* 1399 */     int width = this.rectWidth;
/* 1400 */     int height = this.rectHeight;
/* 1401 */     for (int k = 0; k < this.numBands; k++) {
/* 1402 */       int[] data = this.intDataArrays[k];
/* 1403 */       int scanlineOffset = this.bandDataOffsets[k];
/* 1404 */       int hiVal = hiVals[k];
/* 1405 */       int loVal = loVals[k];
/* 1406 */       for (int j = 0; j < height; j++) {
/* 1407 */         int pixelOffset = scanlineOffset;
/* 1408 */         for (int i = 0; i < width; i++) {
/* 1409 */           int tmp = data[pixelOffset];
/* 1410 */           if (tmp < loVal) {
/* 1411 */             data[pixelOffset] = loVal;
/* 1412 */           } else if (tmp > hiVal) {
/* 1413 */             data[pixelOffset] = hiVal;
/*      */           } 
/* 1415 */           pixelOffset += this.pixelStride;
/*      */         } 
/* 1417 */         scanlineOffset += this.scanlineStride;
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void clampFloatArrays(float[] hiVals, float[] loVals) {
/* 1423 */     int width = this.rectWidth;
/* 1424 */     int height = this.rectHeight;
/* 1425 */     for (int k = 0; k < this.numBands; k++) {
/* 1426 */       float[] data = this.floatDataArrays[k];
/* 1427 */       int scanlineOffset = this.bandDataOffsets[k];
/* 1428 */       float hiVal = hiVals[k];
/* 1429 */       float loVal = loVals[k];
/* 1430 */       for (int j = 0; j < height; j++) {
/* 1431 */         int pixelOffset = scanlineOffset;
/* 1432 */         for (int i = 0; i < width; i++) {
/* 1433 */           float tmp = data[pixelOffset];
/* 1434 */           if (tmp < loVal) {
/* 1435 */             data[pixelOffset] = loVal;
/* 1436 */           } else if (tmp > hiVal) {
/* 1437 */             data[pixelOffset] = hiVal;
/*      */           } 
/* 1439 */           pixelOffset += this.pixelStride;
/*      */         } 
/* 1441 */         scanlineOffset += this.scanlineStride;
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void clampDoubleArrays(double[] hiVals, double[] loVals) {
/* 1447 */     int width = this.rectWidth;
/* 1448 */     int height = this.rectHeight;
/* 1449 */     for (int k = 0; k < this.numBands; k++) {
/* 1450 */       double[] data = this.doubleDataArrays[k];
/* 1451 */       int scanlineOffset = this.bandDataOffsets[k];
/* 1452 */       double hiVal = hiVals[k];
/* 1453 */       double loVal = loVals[k];
/* 1454 */       for (int j = 0; j < height; j++) {
/* 1455 */         int pixelOffset = scanlineOffset;
/* 1456 */         for (int i = 0; i < width; i++) {
/* 1457 */           double tmp = data[pixelOffset];
/* 1458 */           if (tmp < loVal) {
/* 1459 */             data[pixelOffset] = loVal;
/* 1460 */           } else if (tmp > hiVal) {
/* 1461 */             data[pixelOffset] = hiVal;
/*      */           } 
/* 1463 */           pixelOffset += this.pixelStride;
/*      */         } 
/* 1465 */         scanlineOffset += this.scanlineStride;
/*      */       } 
/*      */     } 
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\RasterAccessor.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */