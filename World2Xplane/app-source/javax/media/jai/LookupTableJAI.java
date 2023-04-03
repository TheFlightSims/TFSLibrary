/*      */ package javax.media.jai;
/*      */ 
/*      */ import com.sun.media.jai.util.DataBufferUtils;
/*      */ import java.awt.Point;
/*      */ import java.awt.Rectangle;
/*      */ import java.awt.image.DataBuffer;
/*      */ import java.awt.image.DataBufferByte;
/*      */ import java.awt.image.DataBufferInt;
/*      */ import java.awt.image.DataBufferShort;
/*      */ import java.awt.image.DataBufferUShort;
/*      */ import java.awt.image.Raster;
/*      */ import java.awt.image.SampleModel;
/*      */ import java.awt.image.WritableRaster;
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.io.Serializable;
/*      */ import javax.media.jai.remote.SerializableState;
/*      */ import javax.media.jai.remote.SerializerFactory;
/*      */ 
/*      */ public class LookupTableJAI implements Serializable {
/*      */   transient DataBuffer data;
/*      */   
/*      */   private int[] tableOffsets;
/*      */   
/*      */   public LookupTableJAI(byte[] data) {
/*   71 */     if (data == null)
/*   72 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*   75 */     this.data = new DataBufferByte(data, data.length);
/*   76 */     initOffsets(1, 0);
/*      */   }
/*      */   
/*      */   public LookupTableJAI(byte[] data, int offset) {
/*   87 */     if (data == null)
/*   88 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*   91 */     initOffsets(1, offset);
/*   92 */     this.data = new DataBufferByte(data, data.length);
/*      */   }
/*      */   
/*      */   public LookupTableJAI(byte[][] data) {
/*  103 */     if (data == null)
/*  104 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*  107 */     initOffsets(data.length, 0);
/*  108 */     this.data = new DataBufferByte(data, (data[0]).length);
/*      */   }
/*      */   
/*      */   public LookupTableJAI(byte[][] data, int offset) {
/*  120 */     if (data == null)
/*  121 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*  124 */     initOffsets(data.length, offset);
/*  125 */     this.data = new DataBufferByte(data, (data[0]).length);
/*      */   }
/*      */   
/*      */   public LookupTableJAI(byte[][] data, int[] offsets) {
/*  137 */     if (data == null)
/*  138 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*  141 */     initOffsets(data.length, offsets);
/*  142 */     this.data = new DataBufferByte(data, (data[0]).length);
/*      */   }
/*      */   
/*      */   public LookupTableJAI(short[] data, boolean isUShort) {
/*  155 */     if (data == null)
/*  156 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*  159 */     initOffsets(1, 0);
/*  160 */     if (isUShort) {
/*  161 */       this.data = new DataBufferUShort(data, data.length);
/*      */     } else {
/*  163 */       this.data = new DataBufferShort(data, data.length);
/*      */     } 
/*      */   }
/*      */   
/*      */   public LookupTableJAI(short[] data, int offset, boolean isUShort) {
/*  178 */     if (data == null)
/*  179 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*  182 */     initOffsets(1, offset);
/*  183 */     if (isUShort) {
/*  184 */       this.data = new DataBufferUShort(data, data.length);
/*      */     } else {
/*  186 */       this.data = new DataBufferShort(data, data.length);
/*      */     } 
/*      */   }
/*      */   
/*      */   public LookupTableJAI(short[][] data, boolean isUShort) {
/*  200 */     if (data == null)
/*  201 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*  204 */     initOffsets(data.length, 0);
/*  205 */     if (isUShort) {
/*  206 */       this.data = new DataBufferUShort(data, (data[0]).length);
/*      */     } else {
/*  208 */       this.data = new DataBufferShort(data, (data[0]).length);
/*      */     } 
/*      */   }
/*      */   
/*      */   public LookupTableJAI(short[][] data, int offset, boolean isUShort) {
/*  223 */     if (data == null)
/*  224 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*  227 */     initOffsets(data.length, offset);
/*  228 */     if (isUShort) {
/*  229 */       this.data = new DataBufferUShort(data, (data[0]).length);
/*      */     } else {
/*  231 */       this.data = new DataBufferShort(data, (data[0]).length);
/*      */     } 
/*      */   }
/*      */   
/*      */   public LookupTableJAI(short[][] data, int[] offsets, boolean isUShort) {
/*  246 */     if (data == null)
/*  247 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*  250 */     initOffsets(data.length, offsets);
/*  252 */     if (isUShort) {
/*  253 */       this.data = new DataBufferUShort(data, (data[0]).length);
/*      */     } else {
/*  255 */       this.data = new DataBufferShort(data, (data[0]).length);
/*      */     } 
/*      */   }
/*      */   
/*      */   public LookupTableJAI(int[] data) {
/*  266 */     if (data == null)
/*  267 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*  270 */     initOffsets(1, 0);
/*  271 */     this.data = new DataBufferInt(data, data.length);
/*      */   }
/*      */   
/*      */   public LookupTableJAI(int[] data, int offset) {
/*  282 */     if (data == null)
/*  283 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*  286 */     initOffsets(1, offset);
/*  287 */     this.data = new DataBufferInt(data, data.length);
/*      */   }
/*      */   
/*      */   public LookupTableJAI(int[][] data) {
/*  298 */     if (data == null)
/*  299 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*  302 */     initOffsets(data.length, 0);
/*  303 */     this.data = new DataBufferInt(data, (data[0]).length);
/*      */   }
/*      */   
/*      */   public LookupTableJAI(int[][] data, int offset) {
/*  315 */     if (data == null)
/*  316 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*  319 */     initOffsets(data.length, offset);
/*  320 */     this.data = new DataBufferInt(data, (data[0]).length);
/*      */   }
/*      */   
/*      */   public LookupTableJAI(int[][] data, int[] offsets) {
/*  332 */     if (data == null)
/*  333 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*  336 */     initOffsets(data.length, offsets);
/*  337 */     this.data = new DataBufferInt(data, (data[0]).length);
/*      */   }
/*      */   
/*      */   public LookupTableJAI(float[] data) {
/*  347 */     if (data == null)
/*  348 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*  351 */     initOffsets(1, 0);
/*  352 */     this.data = DataBufferUtils.createDataBufferFloat(data, data.length);
/*      */   }
/*      */   
/*      */   public LookupTableJAI(float[] data, int offset) {
/*  363 */     if (data == null)
/*  364 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*  367 */     initOffsets(1, offset);
/*  368 */     this.data = DataBufferUtils.createDataBufferFloat(data, data.length);
/*      */   }
/*      */   
/*      */   public LookupTableJAI(float[][] data) {
/*  379 */     if (data == null)
/*  380 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*  383 */     initOffsets(data.length, 0);
/*  384 */     this.data = DataBufferUtils.createDataBufferFloat(data, (data[0]).length);
/*      */   }
/*      */   
/*      */   public LookupTableJAI(float[][] data, int offset) {
/*  396 */     if (data == null)
/*  397 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*  400 */     initOffsets(data.length, offset);
/*  401 */     this.data = DataBufferUtils.createDataBufferFloat(data, (data[0]).length);
/*      */   }
/*      */   
/*      */   public LookupTableJAI(float[][] data, int[] offsets) {
/*  413 */     if (data == null)
/*  414 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*  417 */     initOffsets(data.length, offsets);
/*  418 */     this.data = DataBufferUtils.createDataBufferFloat(data, (data[0]).length);
/*      */   }
/*      */   
/*      */   public LookupTableJAI(double[] data) {
/*  428 */     if (data == null)
/*  429 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*  432 */     initOffsets(1, 0);
/*  433 */     this.data = DataBufferUtils.createDataBufferDouble(data, data.length);
/*      */   }
/*      */   
/*      */   public LookupTableJAI(double[] data, int offset) {
/*  444 */     if (data == null)
/*  445 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*  448 */     initOffsets(1, offset);
/*  449 */     this.data = DataBufferUtils.createDataBufferDouble(data, data.length);
/*      */   }
/*      */   
/*      */   public LookupTableJAI(double[][] data) {
/*  460 */     if (data == null)
/*  461 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*  464 */     initOffsets(data.length, 0);
/*  465 */     this.data = DataBufferUtils.createDataBufferDouble(data, (data[0]).length);
/*      */   }
/*      */   
/*      */   public LookupTableJAI(double[][] data, int offset) {
/*  477 */     if (data == null)
/*  478 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*  481 */     initOffsets(data.length, offset);
/*  482 */     this.data = DataBufferUtils.createDataBufferDouble(data, (data[0]).length);
/*      */   }
/*      */   
/*      */   public LookupTableJAI(double[][] data, int[] offsets) {
/*  494 */     if (data == null)
/*  495 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*  498 */     initOffsets(data.length, offsets);
/*  499 */     this.data = DataBufferUtils.createDataBufferDouble(data, (data[0]).length);
/*      */   }
/*      */   
/*      */   public DataBuffer getData() {
/*  506 */     return this.data;
/*      */   }
/*      */   
/*      */   public byte[][] getByteData() {
/*  514 */     return (this.data instanceof DataBufferByte) ? ((DataBufferByte)this.data).getBankData() : (byte[][])null;
/*      */   }
/*      */   
/*      */   public byte[] getByteData(int band) {
/*  523 */     return (this.data instanceof DataBufferByte) ? ((DataBufferByte)this.data).getData(band) : null;
/*      */   }
/*      */   
/*      */   public short[][] getShortData() {
/*  534 */     if (this.data instanceof DataBufferUShort)
/*  535 */       return ((DataBufferUShort)this.data).getBankData(); 
/*  536 */     if (this.data instanceof DataBufferShort)
/*  537 */       return ((DataBufferShort)this.data).getBankData(); 
/*  539 */     return (short[][])null;
/*      */   }
/*      */   
/*      */   public short[] getShortData(int band) {
/*  549 */     if (this.data instanceof DataBufferUShort)
/*  550 */       return ((DataBufferUShort)this.data).getData(band); 
/*  551 */     if (this.data instanceof DataBufferShort)
/*  552 */       return ((DataBufferShort)this.data).getData(band); 
/*  554 */     return null;
/*      */   }
/*      */   
/*      */   public int[][] getIntData() {
/*  564 */     return (this.data instanceof DataBufferInt) ? ((DataBufferInt)this.data).getBankData() : (int[][])null;
/*      */   }
/*      */   
/*      */   public int[] getIntData(int band) {
/*  574 */     return (this.data instanceof DataBufferInt) ? ((DataBufferInt)this.data).getData(band) : null;
/*      */   }
/*      */   
/*      */   public float[][] getFloatData() {
/*  584 */     return (this.data.getDataType() == 4) ? DataBufferUtils.getBankDataFloat(this.data) : (float[][])null;
/*      */   }
/*      */   
/*      */   public float[] getFloatData(int band) {
/*  594 */     return (this.data.getDataType() == 4) ? DataBufferUtils.getDataFloat(this.data, band) : null;
/*      */   }
/*      */   
/*      */   public double[][] getDoubleData() {
/*  604 */     return (this.data.getDataType() == 5) ? DataBufferUtils.getBankDataDouble(this.data) : (double[][])null;
/*      */   }
/*      */   
/*      */   public double[] getDoubleData(int band) {
/*  614 */     return (this.data.getDataType() == 5) ? DataBufferUtils.getDataDouble(this.data, band) : null;
/*      */   }
/*      */   
/*      */   public int[] getOffsets() {
/*  620 */     return this.tableOffsets;
/*      */   }
/*      */   
/*      */   public int getOffset() {
/*  628 */     return this.tableOffsets[0];
/*      */   }
/*      */   
/*      */   public int getOffset(int band) {
/*  636 */     return this.tableOffsets[band];
/*      */   }
/*      */   
/*      */   public int getNumBands() {
/*  641 */     return this.data.getNumBanks();
/*      */   }
/*      */   
/*      */   public int getNumEntries() {
/*  649 */     return this.data.getSize();
/*      */   }
/*      */   
/*      */   public int getDataType() {
/*  656 */     return this.data.getDataType();
/*      */   }
/*      */   
/*      */   public int getDestNumBands(int srcNumBands) {
/*  667 */     int tblNumBands = getNumBands();
/*  668 */     return (srcNumBands == 1) ? tblNumBands : srcNumBands;
/*      */   }
/*      */   
/*      */   public SampleModel getDestSampleModel(SampleModel srcSampleModel) {
/*  684 */     if (srcSampleModel == null)
/*  685 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*  688 */     return getDestSampleModel(srcSampleModel, srcSampleModel.getWidth(), srcSampleModel.getHeight());
/*      */   }
/*      */   
/*      */   public SampleModel getDestSampleModel(SampleModel srcSampleModel, int width, int height) {
/*  709 */     if (srcSampleModel == null)
/*  710 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*  713 */     if (!isIntegralDataType(srcSampleModel))
/*  714 */       return null; 
/*  717 */     return RasterFactory.createComponentSampleModel(srcSampleModel, getDataType(), width, height, getDestNumBands(srcSampleModel.getNumBands()));
/*      */   }
/*      */   
/*      */   public boolean isIntegralDataType(SampleModel sampleModel) {
/*  729 */     if (sampleModel == null)
/*  730 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*  733 */     return isIntegralDataType(sampleModel.getTransferType());
/*      */   }
/*      */   
/*      */   public boolean isIntegralDataType(int dataType) {
/*  741 */     if (dataType == 0 || dataType == 1 || dataType == 2 || dataType == 3)
/*  745 */       return true; 
/*  747 */     return false;
/*      */   }
/*      */   
/*      */   public int lookup(int band, int value) {
/*  759 */     return this.data.getElem(band, value - this.tableOffsets[band]);
/*      */   }
/*      */   
/*      */   public float lookupFloat(int band, int value) {
/*  770 */     return this.data.getElemFloat(band, value - this.tableOffsets[band]);
/*      */   }
/*      */   
/*      */   public double lookupDouble(int band, int value) {
/*  781 */     return this.data.getElemDouble(band, value - this.tableOffsets[band]);
/*      */   }
/*      */   
/*      */   public WritableRaster lookup(WritableRaster src) {
/*  798 */     if (src == null)
/*  799 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*  802 */     return lookup(src, src, src.getBounds());
/*      */   }
/*      */   
/*      */   public WritableRaster lookup(Raster src, WritableRaster dst, Rectangle rect) {
/*      */     SampleModel dstSampleModel;
/*  842 */     if (src == null)
/*  843 */       throw new IllegalArgumentException(JaiI18N.getString("LookupTableJAI1")); 
/*  847 */     SampleModel srcSampleModel = src.getSampleModel();
/*  848 */     if (!isIntegralDataType(srcSampleModel))
/*  849 */       throw new IllegalArgumentException(JaiI18N.getString("LookupTableJAI2")); 
/*  854 */     if (rect == null) {
/*  855 */       rect = src.getBounds();
/*      */     } else {
/*  857 */       rect = rect.intersection(src.getBounds());
/*      */     } 
/*  860 */     if (dst != null)
/*  861 */       rect = rect.intersection(dst.getBounds()); 
/*  866 */     if (dst == null) {
/*  867 */       dstSampleModel = getDestSampleModel(srcSampleModel, rect.width, rect.height);
/*  869 */       dst = RasterFactory.createWritableRaster(dstSampleModel, new Point(rect.x, rect.y));
/*      */     } else {
/*  873 */       dstSampleModel = dst.getSampleModel();
/*  875 */       if (dstSampleModel.getTransferType() != getDataType() || dstSampleModel.getNumBands() != getDestNumBands(srcSampleModel.getNumBands()))
/*  878 */         throw new IllegalArgumentException(JaiI18N.getString("LookupTableJAI3")); 
/*      */     } 
/*  884 */     int sTagID = RasterAccessor.findCompatibleTag(null, srcSampleModel);
/*  885 */     int dTagID = RasterAccessor.findCompatibleTag(null, dstSampleModel);
/*  887 */     RasterFormatTag sTag = new RasterFormatTag(srcSampleModel, sTagID);
/*  888 */     RasterFormatTag dTag = new RasterFormatTag(dstSampleModel, dTagID);
/*  890 */     RasterAccessor s = new RasterAccessor(src, rect, sTag, null);
/*  891 */     RasterAccessor d = new RasterAccessor(dst, rect, dTag, null);
/*  893 */     int srcNumBands = s.getNumBands();
/*  894 */     int srcDataType = s.getDataType();
/*  896 */     int tblNumBands = getNumBands();
/*  897 */     int tblDataType = getDataType();
/*  899 */     int dstWidth = d.getWidth();
/*  900 */     int dstHeight = d.getHeight();
/*  901 */     int dstNumBands = d.getNumBands();
/*  902 */     int dstDataType = d.getDataType();
/*  905 */     int srcLineStride = s.getScanlineStride();
/*  906 */     int srcPixelStride = s.getPixelStride();
/*  907 */     int[] srcBandOffsets = s.getBandOffsets();
/*  909 */     byte[][] bSrcData = s.getByteDataArrays();
/*  910 */     short[][] sSrcData = s.getShortDataArrays();
/*  911 */     int[][] iSrcData = s.getIntDataArrays();
/*  913 */     if (srcNumBands < dstNumBands) {
/*      */       byte[] bData0;
/*      */       int j;
/*      */       short[] sData0;
/*  914 */       int k, iData0[], m, offset0 = srcBandOffsets[0];
/*  915 */       srcBandOffsets = new int[dstNumBands];
/*  916 */       for (int i = 0; i < dstNumBands; i++)
/*  917 */         srcBandOffsets[i] = offset0; 
/*  920 */       switch (srcDataType) {
/*      */         case 0:
/*  922 */           bData0 = bSrcData[0];
/*  923 */           bSrcData = new byte[dstNumBands][];
/*  924 */           for (j = 0; j < dstNumBands; j++)
/*  925 */             bSrcData[j] = bData0; 
/*      */           break;
/*      */         case 1:
/*      */         case 2:
/*  930 */           sData0 = sSrcData[0];
/*  931 */           sSrcData = new short[dstNumBands][];
/*  932 */           for (k = 0; k < dstNumBands; k++)
/*  933 */             sSrcData[k] = sData0; 
/*      */           break;
/*      */         case 3:
/*  937 */           iData0 = iSrcData[0];
/*  938 */           iSrcData = new int[dstNumBands][];
/*  939 */           for (m = 0; m < dstNumBands; m++)
/*  940 */             iSrcData[m] = iData0; 
/*      */           break;
/*      */       } 
/*      */     } 
/*  947 */     int[] tblOffsets = getOffsets();
/*  949 */     byte[][] bTblData = getByteData();
/*  950 */     short[][] sTblData = getShortData();
/*  951 */     int[][] iTblData = getIntData();
/*  952 */     float[][] fTblData = getFloatData();
/*  953 */     double[][] dTblData = getDoubleData();
/*  955 */     if (tblNumBands < dstNumBands) {
/*      */       byte[] bData0;
/*      */       int j;
/*      */       short[] sData0;
/*      */       int k, iData0[], m;
/*      */       float[] fData0;
/*      */       int n;
/*      */       double[] dData0;
/*  956 */       int i1, offset0 = tblOffsets[0];
/*  957 */       tblOffsets = new int[dstNumBands];
/*  958 */       for (int i = 0; i < dstNumBands; i++)
/*  959 */         tblOffsets[i] = offset0; 
/*  962 */       switch (tblDataType) {
/*      */         case 0:
/*  964 */           bData0 = bTblData[0];
/*  965 */           bTblData = new byte[dstNumBands][];
/*  966 */           for (j = 0; j < dstNumBands; j++)
/*  967 */             bTblData[j] = bData0; 
/*      */           break;
/*      */         case 1:
/*      */         case 2:
/*  972 */           sData0 = sTblData[0];
/*  973 */           sTblData = new short[dstNumBands][];
/*  974 */           for (k = 0; k < dstNumBands; k++)
/*  975 */             sTblData[k] = sData0; 
/*      */           break;
/*      */         case 3:
/*  979 */           iData0 = iTblData[0];
/*  980 */           iTblData = new int[dstNumBands][];
/*  981 */           for (m = 0; m < dstNumBands; m++)
/*  982 */             iTblData[m] = iData0; 
/*      */           break;
/*      */         case 4:
/*  986 */           fData0 = fTblData[0];
/*  987 */           fTblData = new float[dstNumBands][];
/*  988 */           for (n = 0; n < dstNumBands; n++)
/*  989 */             fTblData[n] = fData0; 
/*      */           break;
/*      */         case 5:
/*  993 */           dData0 = dTblData[0];
/*  994 */           dTblData = new double[dstNumBands][];
/*  995 */           for (i1 = 0; i1 < dstNumBands; i1++)
/*  996 */             dTblData[i1] = dData0; 
/*      */           break;
/*      */       } 
/*      */     } 
/* 1002 */     int dstLineStride = d.getScanlineStride();
/* 1003 */     int dstPixelStride = d.getPixelStride();
/* 1004 */     int[] dstBandOffsets = d.getBandOffsets();
/* 1006 */     byte[][] bDstData = d.getByteDataArrays();
/* 1007 */     short[][] sDstData = d.getShortDataArrays();
/* 1008 */     int[][] iDstData = d.getIntDataArrays();
/* 1009 */     float[][] fDstData = d.getFloatDataArrays();
/* 1010 */     double[][] dDstData = d.getDoubleDataArrays();
/* 1012 */     switch (dstDataType) {
/*      */       case 0:
/* 1014 */         switch (srcDataType) {
/*      */           case 0:
/* 1016 */             lookup(srcLineStride, srcPixelStride, srcBandOffsets, bSrcData, dstWidth, dstHeight, dstNumBands, dstLineStride, dstPixelStride, dstBandOffsets, bDstData, tblOffsets, bTblData);
/*      */             break;
/*      */           case 1:
/* 1025 */             lookupU(srcLineStride, srcPixelStride, srcBandOffsets, sSrcData, dstWidth, dstHeight, dstNumBands, dstLineStride, dstPixelStride, dstBandOffsets, bDstData, tblOffsets, bTblData);
/*      */             break;
/*      */           case 2:
/* 1034 */             lookup(srcLineStride, srcPixelStride, srcBandOffsets, sSrcData, dstWidth, dstHeight, dstNumBands, dstLineStride, dstPixelStride, dstBandOffsets, bDstData, tblOffsets, bTblData);
/*      */             break;
/*      */           case 3:
/* 1043 */             lookup(srcLineStride, srcPixelStride, srcBandOffsets, iSrcData, dstWidth, dstHeight, dstNumBands, dstLineStride, dstPixelStride, dstBandOffsets, bDstData, tblOffsets, bTblData);
/*      */             break;
/*      */         } 
/*      */         break;
/*      */       case 1:
/*      */       case 2:
/* 1055 */         switch (srcDataType) {
/*      */           case 0:
/* 1057 */             lookup(srcLineStride, srcPixelStride, srcBandOffsets, bSrcData, dstWidth, dstHeight, dstNumBands, dstLineStride, dstPixelStride, dstBandOffsets, sDstData, tblOffsets, sTblData);
/*      */             break;
/*      */           case 1:
/* 1066 */             lookupU(srcLineStride, srcPixelStride, srcBandOffsets, sSrcData, dstWidth, dstHeight, dstNumBands, dstLineStride, dstPixelStride, dstBandOffsets, sDstData, tblOffsets, sTblData);
/*      */             break;
/*      */           case 2:
/* 1075 */             lookup(srcLineStride, srcPixelStride, srcBandOffsets, sSrcData, dstWidth, dstHeight, dstNumBands, dstLineStride, dstPixelStride, dstBandOffsets, sDstData, tblOffsets, sTblData);
/*      */             break;
/*      */           case 3:
/* 1084 */             lookup(srcLineStride, srcPixelStride, srcBandOffsets, iSrcData, dstWidth, dstHeight, dstNumBands, dstLineStride, dstPixelStride, dstBandOffsets, sDstData, tblOffsets, sTblData);
/*      */             break;
/*      */         } 
/*      */         break;
/*      */       case 3:
/* 1095 */         switch (srcDataType) {
/*      */           case 0:
/* 1097 */             lookup(srcLineStride, srcPixelStride, srcBandOffsets, bSrcData, dstWidth, dstHeight, dstNumBands, dstLineStride, dstPixelStride, dstBandOffsets, iDstData, tblOffsets, iTblData);
/*      */             break;
/*      */           case 1:
/* 1106 */             lookupU(srcLineStride, srcPixelStride, srcBandOffsets, sSrcData, dstWidth, dstHeight, dstNumBands, dstLineStride, dstPixelStride, dstBandOffsets, iDstData, tblOffsets, iTblData);
/*      */             break;
/*      */           case 2:
/* 1115 */             lookup(srcLineStride, srcPixelStride, srcBandOffsets, sSrcData, dstWidth, dstHeight, dstNumBands, dstLineStride, dstPixelStride, dstBandOffsets, iDstData, tblOffsets, iTblData);
/*      */             break;
/*      */           case 3:
/* 1124 */             lookup(srcLineStride, srcPixelStride, srcBandOffsets, iSrcData, dstWidth, dstHeight, dstNumBands, dstLineStride, dstPixelStride, dstBandOffsets, iDstData, tblOffsets, iTblData);
/*      */             break;
/*      */         } 
/*      */         break;
/*      */       case 4:
/* 1135 */         switch (srcDataType) {
/*      */           case 0:
/* 1137 */             lookup(srcLineStride, srcPixelStride, srcBandOffsets, bSrcData, dstWidth, dstHeight, dstNumBands, dstLineStride, dstPixelStride, dstBandOffsets, fDstData, tblOffsets, fTblData);
/*      */             break;
/*      */           case 1:
/* 1146 */             lookupU(srcLineStride, srcPixelStride, srcBandOffsets, sSrcData, dstWidth, dstHeight, dstNumBands, dstLineStride, dstPixelStride, dstBandOffsets, fDstData, tblOffsets, fTblData);
/*      */             break;
/*      */           case 2:
/* 1155 */             lookup(srcLineStride, srcPixelStride, srcBandOffsets, sSrcData, dstWidth, dstHeight, dstNumBands, dstLineStride, dstPixelStride, dstBandOffsets, fDstData, tblOffsets, fTblData);
/*      */             break;
/*      */           case 3:
/* 1164 */             lookup(srcLineStride, srcPixelStride, srcBandOffsets, iSrcData, dstWidth, dstHeight, dstNumBands, dstLineStride, dstPixelStride, dstBandOffsets, fDstData, tblOffsets, fTblData);
/*      */             break;
/*      */         } 
/*      */         break;
/*      */       case 5:
/* 1175 */         switch (srcDataType) {
/*      */           case 0:
/* 1177 */             lookup(srcLineStride, srcPixelStride, srcBandOffsets, bSrcData, dstWidth, dstHeight, dstNumBands, dstLineStride, dstPixelStride, dstBandOffsets, dDstData, tblOffsets, dTblData);
/*      */             break;
/*      */           case 1:
/* 1186 */             lookupU(srcLineStride, srcPixelStride, srcBandOffsets, sSrcData, dstWidth, dstHeight, dstNumBands, dstLineStride, dstPixelStride, dstBandOffsets, dDstData, tblOffsets, dTblData);
/*      */             break;
/*      */           case 2:
/* 1195 */             lookup(srcLineStride, srcPixelStride, srcBandOffsets, sSrcData, dstWidth, dstHeight, dstNumBands, dstLineStride, dstPixelStride, dstBandOffsets, dDstData, tblOffsets, dTblData);
/*      */             break;
/*      */           case 3:
/* 1204 */             lookup(srcLineStride, srcPixelStride, srcBandOffsets, iSrcData, dstWidth, dstHeight, dstNumBands, dstLineStride, dstPixelStride, dstBandOffsets, dDstData, tblOffsets, dTblData);
/*      */             break;
/*      */         } 
/*      */         break;
/*      */     } 
/* 1215 */     d.copyDataToRaster();
/* 1217 */     return dst;
/*      */   }
/*      */   
/*      */   private void lookup(int srcLineStride, int srcPixelStride, int[] srcBandOffsets, byte[][] srcData, int width, int height, int bands, int dstLineStride, int dstPixelStride, int[] dstBandOffsets, byte[][] dstData, int[] tblOffsets, byte[][] tblData) {
/* 1227 */     for (int b = 0; b < bands; b++) {
/* 1228 */       byte[] s = srcData[b];
/* 1229 */       byte[] d = dstData[b];
/* 1230 */       byte[] t = tblData[b];
/* 1232 */       int srcLineOffset = srcBandOffsets[b];
/* 1233 */       int dstLineOffset = dstBandOffsets[b];
/* 1234 */       int tblOffset = tblOffsets[b];
/* 1236 */       for (int h = 0; h < height; h++) {
/* 1237 */         int srcPixelOffset = srcLineOffset;
/* 1238 */         int dstPixelOffset = dstLineOffset;
/* 1240 */         srcLineOffset += srcLineStride;
/* 1241 */         dstLineOffset += dstLineStride;
/* 1243 */         for (int w = 0; w < width; w++) {
/* 1244 */           d[dstPixelOffset] = t[(s[srcPixelOffset] & 0xFF) - tblOffset];
/* 1246 */           srcPixelOffset += srcPixelStride;
/* 1247 */           dstPixelOffset += dstPixelStride;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void lookupU(int srcLineStride, int srcPixelStride, int[] srcBandOffsets, short[][] srcData, int width, int height, int bands, int dstLineStride, int dstPixelStride, int[] dstBandOffsets, byte[][] dstData, int[] tblOffsets, byte[][] tblData) {
/* 1260 */     for (int b = 0; b < bands; b++) {
/* 1261 */       short[] s = srcData[b];
/* 1262 */       byte[] d = dstData[b];
/* 1263 */       byte[] t = tblData[b];
/* 1265 */       int srcLineOffset = srcBandOffsets[b];
/* 1266 */       int dstLineOffset = dstBandOffsets[b];
/* 1267 */       int tblOffset = tblOffsets[b];
/* 1269 */       for (int h = 0; h < height; h++) {
/* 1270 */         int srcPixelOffset = srcLineOffset;
/* 1271 */         int dstPixelOffset = dstLineOffset;
/* 1273 */         srcLineOffset += srcLineStride;
/* 1274 */         dstLineOffset += dstLineStride;
/* 1276 */         for (int w = 0; w < width; w++) {
/* 1277 */           d[dstPixelOffset] = t[(s[srcPixelOffset] & 0xFFFF) - tblOffset];
/* 1280 */           srcPixelOffset += srcPixelStride;
/* 1281 */           dstPixelOffset += dstPixelStride;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void lookup(int srcLineStride, int srcPixelStride, int[] srcBandOffsets, short[][] srcData, int width, int height, int bands, int dstLineStride, int dstPixelStride, int[] dstBandOffsets, byte[][] dstData, int[] tblOffsets, byte[][] tblData) {
/* 1294 */     for (int b = 0; b < bands; b++) {
/* 1295 */       short[] s = srcData[b];
/* 1296 */       byte[] d = dstData[b];
/* 1297 */       byte[] t = tblData[b];
/* 1299 */       int srcLineOffset = srcBandOffsets[b];
/* 1300 */       int dstLineOffset = dstBandOffsets[b];
/* 1301 */       int tblOffset = tblOffsets[b];
/* 1303 */       for (int h = 0; h < height; h++) {
/* 1304 */         int srcPixelOffset = srcLineOffset;
/* 1305 */         int dstPixelOffset = dstLineOffset;
/* 1307 */         srcLineOffset += srcLineStride;
/* 1308 */         dstLineOffset += dstLineStride;
/* 1310 */         for (int w = 0; w < width; w++) {
/* 1311 */           d[dstPixelOffset] = t[s[srcPixelOffset] - tblOffset];
/* 1313 */           srcPixelOffset += srcPixelStride;
/* 1314 */           dstPixelOffset += dstPixelStride;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void lookup(int srcLineStride, int srcPixelStride, int[] srcBandOffsets, int[][] srcData, int width, int height, int bands, int dstLineStride, int dstPixelStride, int[] dstBandOffsets, byte[][] dstData, int[] tblOffsets, byte[][] tblData) {
/* 1327 */     for (int b = 0; b < bands; b++) {
/* 1328 */       int[] s = srcData[b];
/* 1329 */       byte[] d = dstData[b];
/* 1330 */       byte[] t = tblData[b];
/* 1332 */       int srcLineOffset = srcBandOffsets[b];
/* 1333 */       int dstLineOffset = dstBandOffsets[b];
/* 1334 */       int tblOffset = tblOffsets[b];
/* 1336 */       for (int h = 0; h < height; h++) {
/* 1337 */         int srcPixelOffset = srcLineOffset;
/* 1338 */         int dstPixelOffset = dstLineOffset;
/* 1340 */         srcLineOffset += srcLineStride;
/* 1341 */         dstLineOffset += dstLineStride;
/* 1343 */         for (int w = 0; w < width; w++) {
/* 1344 */           d[dstPixelOffset] = t[s[srcPixelOffset] - tblOffset];
/* 1346 */           srcPixelOffset += srcPixelStride;
/* 1347 */           dstPixelOffset += dstPixelStride;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void lookup(int srcLineStride, int srcPixelStride, int[] srcBandOffsets, byte[][] srcData, int width, int height, int bands, int dstLineStride, int dstPixelStride, int[] dstBandOffsets, short[][] dstData, int[] tblOffsets, short[][] tblData) {
/* 1360 */     for (int b = 0; b < bands; b++) {
/* 1361 */       byte[] s = srcData[b];
/* 1362 */       short[] d = dstData[b];
/* 1363 */       short[] t = tblData[b];
/* 1365 */       int srcLineOffset = srcBandOffsets[b];
/* 1366 */       int dstLineOffset = dstBandOffsets[b];
/* 1367 */       int tblOffset = tblOffsets[b];
/* 1369 */       for (int h = 0; h < height; h++) {
/* 1370 */         int srcPixelOffset = srcLineOffset;
/* 1371 */         int dstPixelOffset = dstLineOffset;
/* 1373 */         srcLineOffset += srcLineStride;
/* 1374 */         dstLineOffset += dstLineStride;
/* 1376 */         for (int w = 0; w < width; w++) {
/* 1377 */           d[dstPixelOffset] = t[(s[srcPixelOffset] & 0xFF) - tblOffset];
/* 1379 */           srcPixelOffset += srcPixelStride;
/* 1380 */           dstPixelOffset += dstPixelStride;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void lookupU(int srcLineStride, int srcPixelStride, int[] srcBandOffsets, short[][] srcData, int width, int height, int bands, int dstLineStride, int dstPixelStride, int[] dstBandOffsets, short[][] dstData, int[] tblOffsets, short[][] tblData) {
/* 1393 */     for (int b = 0; b < bands; b++) {
/* 1394 */       short[] s = srcData[b];
/* 1395 */       short[] d = dstData[b];
/* 1396 */       short[] t = tblData[b];
/* 1398 */       int srcLineOffset = srcBandOffsets[b];
/* 1399 */       int dstLineOffset = dstBandOffsets[b];
/* 1400 */       int tblOffset = tblOffsets[b];
/* 1402 */       for (int h = 0; h < height; h++) {
/* 1403 */         int srcPixelOffset = srcLineOffset;
/* 1404 */         int dstPixelOffset = dstLineOffset;
/* 1406 */         srcLineOffset += srcLineStride;
/* 1407 */         dstLineOffset += dstLineStride;
/* 1409 */         for (int w = 0; w < width; w++) {
/* 1410 */           d[dstPixelOffset] = t[(s[srcPixelOffset] & 0xFFFF) - tblOffset];
/* 1413 */           srcPixelOffset += srcPixelStride;
/* 1414 */           dstPixelOffset += dstPixelStride;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void lookup(int srcLineStride, int srcPixelStride, int[] srcBandOffsets, short[][] srcData, int width, int height, int bands, int dstLineStride, int dstPixelStride, int[] dstBandOffsets, short[][] dstData, int[] tblOffsets, short[][] tblData) {
/* 1427 */     for (int b = 0; b < bands; b++) {
/* 1428 */       short[] s = srcData[b];
/* 1429 */       short[] d = dstData[b];
/* 1430 */       short[] t = tblData[b];
/* 1432 */       int srcLineOffset = srcBandOffsets[b];
/* 1433 */       int dstLineOffset = dstBandOffsets[b];
/* 1434 */       int tblOffset = tblOffsets[b];
/* 1436 */       for (int h = 0; h < height; h++) {
/* 1437 */         int srcPixelOffset = srcLineOffset;
/* 1438 */         int dstPixelOffset = dstLineOffset;
/* 1440 */         srcLineOffset += srcLineStride;
/* 1441 */         dstLineOffset += dstLineStride;
/* 1443 */         for (int w = 0; w < width; w++) {
/* 1444 */           d[dstPixelOffset] = t[s[srcPixelOffset] - tblOffset];
/* 1446 */           srcPixelOffset += srcPixelStride;
/* 1447 */           dstPixelOffset += dstPixelStride;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void lookup(int srcLineStride, int srcPixelStride, int[] srcBandOffsets, int[][] srcData, int width, int height, int bands, int dstLineStride, int dstPixelStride, int[] dstBandOffsets, short[][] dstData, int[] tblOffsets, short[][] tblData) {
/* 1460 */     for (int b = 0; b < bands; b++) {
/* 1461 */       int[] s = srcData[b];
/* 1462 */       short[] d = dstData[b];
/* 1463 */       short[] t = tblData[b];
/* 1465 */       int srcLineOffset = srcBandOffsets[b];
/* 1466 */       int dstLineOffset = dstBandOffsets[b];
/* 1467 */       int tblOffset = tblOffsets[b];
/* 1469 */       for (int h = 0; h < height; h++) {
/* 1470 */         int srcPixelOffset = srcLineOffset;
/* 1471 */         int dstPixelOffset = dstLineOffset;
/* 1473 */         srcLineOffset += srcLineStride;
/* 1474 */         dstLineOffset += dstLineStride;
/* 1476 */         for (int w = 0; w < width; w++) {
/* 1477 */           d[dstPixelOffset] = t[s[srcPixelOffset] - tblOffset];
/* 1479 */           srcPixelOffset += srcPixelStride;
/* 1480 */           dstPixelOffset += dstPixelStride;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void lookup(int srcLineStride, int srcPixelStride, int[] srcBandOffsets, byte[][] srcData, int width, int height, int bands, int dstLineStride, int dstPixelStride, int[] dstBandOffsets, int[][] dstData, int[] tblOffsets, int[][] tblData) {
/* 1493 */     if (tblData == null) {
/* 1494 */       for (int b = 0; b < bands; b++) {
/* 1495 */         byte[] s = srcData[b];
/* 1496 */         int[] d = dstData[b];
/* 1498 */         int srcLineOffset = srcBandOffsets[b];
/* 1499 */         int dstLineOffset = dstBandOffsets[b];
/* 1501 */         for (int h = 0; h < height; h++) {
/* 1502 */           int srcPixelOffset = srcLineOffset;
/* 1503 */           int dstPixelOffset = dstLineOffset;
/* 1505 */           srcLineOffset += srcLineStride;
/* 1506 */           dstLineOffset += dstLineStride;
/* 1508 */           for (int w = 0; w < width; w++) {
/* 1509 */             d[dstPixelOffset] = this.data.getElem(b, s[srcPixelOffset] & 0xFF);
/* 1512 */             srcPixelOffset += srcPixelStride;
/* 1513 */             dstPixelOffset += dstPixelStride;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } else {
/* 1518 */       for (int b = 0; b < bands; b++) {
/* 1519 */         byte[] s = srcData[b];
/* 1520 */         int[] d = dstData[b];
/* 1521 */         int[] t = tblData[b];
/* 1523 */         int srcLineOffset = srcBandOffsets[b];
/* 1524 */         int dstLineOffset = dstBandOffsets[b];
/* 1525 */         int tblOffset = tblOffsets[b];
/* 1527 */         for (int h = 0; h < height; h++) {
/* 1528 */           int srcPixelOffset = srcLineOffset;
/* 1529 */           int dstPixelOffset = dstLineOffset;
/* 1531 */           srcLineOffset += srcLineStride;
/* 1532 */           dstLineOffset += dstLineStride;
/* 1534 */           for (int w = 0; w < width; w++) {
/* 1535 */             d[dstPixelOffset] = t[(s[srcPixelOffset] & 0xFF) - tblOffset];
/* 1538 */             srcPixelOffset += srcPixelStride;
/* 1539 */             dstPixelOffset += dstPixelStride;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void lookupU(int srcLineStride, int srcPixelStride, int[] srcBandOffsets, short[][] srcData, int width, int height, int bands, int dstLineStride, int dstPixelStride, int[] dstBandOffsets, int[][] dstData, int[] tblOffsets, int[][] tblData) {
/* 1553 */     if (tblData == null) {
/* 1554 */       for (int b = 0; b < bands; b++) {
/* 1555 */         short[] s = srcData[b];
/* 1556 */         int[] d = dstData[b];
/* 1558 */         int srcLineOffset = srcBandOffsets[b];
/* 1559 */         int dstLineOffset = dstBandOffsets[b];
/* 1561 */         for (int h = 0; h < height; h++) {
/* 1562 */           int srcPixelOffset = srcLineOffset;
/* 1563 */           int dstPixelOffset = dstLineOffset;
/* 1565 */           srcLineOffset += srcLineStride;
/* 1566 */           dstLineOffset += dstLineStride;
/* 1568 */           for (int w = 0; w < width; w++) {
/* 1569 */             d[dstPixelOffset] = this.data.getElem(b, s[srcPixelOffset] & 0xFFFF);
/* 1572 */             srcPixelOffset += srcPixelStride;
/* 1573 */             dstPixelOffset += dstPixelStride;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } else {
/* 1578 */       for (int b = 0; b < bands; b++) {
/* 1579 */         short[] s = srcData[b];
/* 1580 */         int[] d = dstData[b];
/* 1581 */         int[] t = tblData[b];
/* 1583 */         int srcLineOffset = srcBandOffsets[b];
/* 1584 */         int dstLineOffset = dstBandOffsets[b];
/* 1585 */         int tblOffset = tblOffsets[b];
/* 1587 */         for (int h = 0; h < height; h++) {
/* 1588 */           int srcPixelOffset = srcLineOffset;
/* 1589 */           int dstPixelOffset = dstLineOffset;
/* 1591 */           srcLineOffset += srcLineStride;
/* 1592 */           dstLineOffset += dstLineStride;
/* 1594 */           for (int w = 0; w < width; w++) {
/* 1595 */             d[dstPixelOffset] = t[(s[srcPixelOffset] & 0xFFFF) - tblOffset];
/* 1598 */             srcPixelOffset += srcPixelStride;
/* 1599 */             dstPixelOffset += dstPixelStride;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void lookup(int srcLineStride, int srcPixelStride, int[] srcBandOffsets, short[][] srcData, int width, int height, int bands, int dstLineStride, int dstPixelStride, int[] dstBandOffsets, int[][] dstData, int[] tblOffsets, int[][] tblData) {
/* 1613 */     if (tblData == null) {
/* 1614 */       for (int b = 0; b < bands; b++) {
/* 1615 */         short[] s = srcData[b];
/* 1616 */         int[] d = dstData[b];
/* 1618 */         int srcLineOffset = srcBandOffsets[b];
/* 1619 */         int dstLineOffset = dstBandOffsets[b];
/* 1621 */         for (int h = 0; h < height; h++) {
/* 1622 */           int srcPixelOffset = srcLineOffset;
/* 1623 */           int dstPixelOffset = dstLineOffset;
/* 1625 */           srcLineOffset += srcLineStride;
/* 1626 */           dstLineOffset += dstLineStride;
/* 1628 */           for (int w = 0; w < width; w++) {
/* 1629 */             d[dstPixelOffset] = this.data.getElem(b, s[srcPixelOffset]);
/* 1632 */             srcPixelOffset += srcPixelStride;
/* 1633 */             dstPixelOffset += dstPixelStride;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } else {
/* 1638 */       for (int b = 0; b < bands; b++) {
/* 1639 */         short[] s = srcData[b];
/* 1640 */         int[] d = dstData[b];
/* 1641 */         int[] t = tblData[b];
/* 1643 */         int srcLineOffset = srcBandOffsets[b];
/* 1644 */         int dstLineOffset = dstBandOffsets[b];
/* 1645 */         int tblOffset = tblOffsets[b];
/* 1647 */         for (int h = 0; h < height; h++) {
/* 1648 */           int srcPixelOffset = srcLineOffset;
/* 1649 */           int dstPixelOffset = dstLineOffset;
/* 1651 */           srcLineOffset += srcLineStride;
/* 1652 */           dstLineOffset += dstLineStride;
/* 1654 */           for (int w = 0; w < width; w++) {
/* 1655 */             d[dstPixelOffset] = t[s[srcPixelOffset] - tblOffset];
/* 1657 */             srcPixelOffset += srcPixelStride;
/* 1658 */             dstPixelOffset += dstPixelStride;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void lookup(int srcLineStride, int srcPixelStride, int[] srcBandOffsets, int[][] srcData, int width, int height, int bands, int dstLineStride, int dstPixelStride, int[] dstBandOffsets, int[][] dstData, int[] tblOffsets, int[][] tblData) {
/* 1672 */     if (tblData == null) {
/* 1673 */       for (int b = 0; b < bands; b++) {
/* 1674 */         int[] s = srcData[b];
/* 1675 */         int[] d = dstData[b];
/* 1677 */         int srcLineOffset = srcBandOffsets[b];
/* 1678 */         int dstLineOffset = dstBandOffsets[b];
/* 1680 */         for (int h = 0; h < height; h++) {
/* 1681 */           int srcPixelOffset = srcLineOffset;
/* 1682 */           int dstPixelOffset = dstLineOffset;
/* 1684 */           srcLineOffset += srcLineStride;
/* 1685 */           dstLineOffset += dstLineStride;
/* 1687 */           for (int w = 0; w < width; w++) {
/* 1688 */             d[dstPixelOffset] = this.data.getElem(b, s[srcPixelOffset]);
/* 1690 */             srcPixelOffset += srcPixelStride;
/* 1691 */             dstPixelOffset += dstPixelStride;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } else {
/* 1696 */       for (int b = 0; b < bands; b++) {
/* 1697 */         int[] s = srcData[b];
/* 1698 */         int[] d = dstData[b];
/* 1699 */         int[] t = tblData[b];
/* 1701 */         int srcLineOffset = srcBandOffsets[b];
/* 1702 */         int dstLineOffset = dstBandOffsets[b];
/* 1703 */         int tblOffset = tblOffsets[b];
/* 1705 */         for (int h = 0; h < height; h++) {
/* 1706 */           int srcPixelOffset = srcLineOffset;
/* 1707 */           int dstPixelOffset = dstLineOffset;
/* 1709 */           srcLineOffset += srcLineStride;
/* 1710 */           dstLineOffset += dstLineStride;
/* 1712 */           for (int w = 0; w < width; w++) {
/* 1713 */             d[dstPixelOffset] = t[s[srcPixelOffset] - tblOffset];
/* 1715 */             srcPixelOffset += srcPixelStride;
/* 1716 */             dstPixelOffset += dstPixelStride;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void lookup(int srcLineStride, int srcPixelStride, int[] srcBandOffsets, byte[][] srcData, int width, int height, int bands, int dstLineStride, int dstPixelStride, int[] dstBandOffsets, float[][] dstData, int[] tblOffsets, float[][] tblData) {
/* 1730 */     for (int b = 0; b < bands; b++) {
/* 1731 */       byte[] s = srcData[b];
/* 1732 */       float[] d = dstData[b];
/* 1733 */       float[] t = tblData[b];
/* 1735 */       int srcLineOffset = srcBandOffsets[b];
/* 1736 */       int dstLineOffset = dstBandOffsets[b];
/* 1737 */       int tblOffset = tblOffsets[b];
/* 1739 */       for (int h = 0; h < height; h++) {
/* 1740 */         int srcPixelOffset = srcLineOffset;
/* 1741 */         int dstPixelOffset = dstLineOffset;
/* 1743 */         srcLineOffset += srcLineStride;
/* 1744 */         dstLineOffset += dstLineStride;
/* 1746 */         for (int w = 0; w < width; w++) {
/* 1747 */           d[dstPixelOffset] = t[(s[srcPixelOffset] & 0xFF) - tblOffset];
/* 1749 */           srcPixelOffset += srcPixelStride;
/* 1750 */           dstPixelOffset += dstPixelStride;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void lookupU(int srcLineStride, int srcPixelStride, int[] srcBandOffsets, short[][] srcData, int width, int height, int bands, int dstLineStride, int dstPixelStride, int[] dstBandOffsets, float[][] dstData, int[] tblOffsets, float[][] tblData) {
/* 1763 */     for (int b = 0; b < bands; b++) {
/* 1764 */       short[] s = srcData[b];
/* 1765 */       float[] d = dstData[b];
/* 1766 */       float[] t = tblData[b];
/* 1768 */       int srcLineOffset = srcBandOffsets[b];
/* 1769 */       int dstLineOffset = dstBandOffsets[b];
/* 1770 */       int tblOffset = tblOffsets[b];
/* 1772 */       for (int h = 0; h < height; h++) {
/* 1773 */         int srcPixelOffset = srcLineOffset;
/* 1774 */         int dstPixelOffset = dstLineOffset;
/* 1776 */         srcLineOffset += srcLineStride;
/* 1777 */         dstLineOffset += dstLineStride;
/* 1779 */         for (int w = 0; w < width; w++) {
/* 1780 */           d[dstPixelOffset] = t[(s[srcPixelOffset] & 0xFFFF) - tblOffset];
/* 1783 */           srcPixelOffset += srcPixelStride;
/* 1784 */           dstPixelOffset += dstPixelStride;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void lookup(int srcLineStride, int srcPixelStride, int[] srcBandOffsets, short[][] srcData, int width, int height, int bands, int dstLineStride, int dstPixelStride, int[] dstBandOffsets, float[][] dstData, int[] tblOffsets, float[][] tblData) {
/* 1797 */     for (int b = 0; b < bands; b++) {
/* 1798 */       short[] s = srcData[b];
/* 1799 */       float[] d = dstData[b];
/* 1800 */       float[] t = tblData[b];
/* 1802 */       int srcLineOffset = srcBandOffsets[b];
/* 1803 */       int dstLineOffset = dstBandOffsets[b];
/* 1804 */       int tblOffset = tblOffsets[b];
/* 1806 */       for (int h = 0; h < height; h++) {
/* 1807 */         int srcPixelOffset = srcLineOffset;
/* 1808 */         int dstPixelOffset = dstLineOffset;
/* 1810 */         srcLineOffset += srcLineStride;
/* 1811 */         dstLineOffset += dstLineStride;
/* 1813 */         for (int w = 0; w < width; w++) {
/* 1814 */           d[dstPixelOffset] = t[s[srcPixelOffset] - tblOffset];
/* 1816 */           srcPixelOffset += srcPixelStride;
/* 1817 */           dstPixelOffset += dstPixelStride;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void lookup(int srcLineStride, int srcPixelStride, int[] srcBandOffsets, int[][] srcData, int width, int height, int bands, int dstLineStride, int dstPixelStride, int[] dstBandOffsets, float[][] dstData, int[] tblOffsets, float[][] tblData) {
/* 1830 */     for (int b = 0; b < bands; b++) {
/* 1831 */       int[] s = srcData[b];
/* 1832 */       float[] d = dstData[b];
/* 1833 */       float[] t = tblData[b];
/* 1835 */       int srcLineOffset = srcBandOffsets[b];
/* 1836 */       int dstLineOffset = dstBandOffsets[b];
/* 1837 */       int tblOffset = tblOffsets[b];
/* 1839 */       for (int h = 0; h < height; h++) {
/* 1840 */         int srcPixelOffset = srcLineOffset;
/* 1841 */         int dstPixelOffset = dstLineOffset;
/* 1843 */         srcLineOffset += srcLineStride;
/* 1844 */         dstLineOffset += dstLineStride;
/* 1846 */         for (int w = 0; w < width; w++) {
/* 1847 */           d[dstPixelOffset] = t[s[srcPixelOffset] - tblOffset];
/* 1849 */           srcPixelOffset += srcPixelStride;
/* 1850 */           dstPixelOffset += dstPixelStride;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void lookup(int srcLineStride, int srcPixelStride, int[] srcBandOffsets, byte[][] srcData, int width, int height, int bands, int dstLineStride, int dstPixelStride, int[] dstBandOffsets, double[][] dstData, int[] tblOffsets, double[][] tblData) {
/* 1863 */     for (int b = 0; b < bands; b++) {
/* 1864 */       byte[] s = srcData[b];
/* 1865 */       double[] d = dstData[b];
/* 1866 */       double[] t = tblData[b];
/* 1868 */       int srcLineOffset = srcBandOffsets[b];
/* 1869 */       int dstLineOffset = dstBandOffsets[b];
/* 1870 */       int tblOffset = tblOffsets[b];
/* 1872 */       for (int h = 0; h < height; h++) {
/* 1873 */         int srcPixelOffset = srcLineOffset;
/* 1874 */         int dstPixelOffset = dstLineOffset;
/* 1876 */         srcLineOffset += srcLineStride;
/* 1877 */         dstLineOffset += dstLineStride;
/* 1879 */         for (int w = 0; w < width; w++) {
/* 1880 */           d[dstPixelOffset] = t[(s[srcPixelOffset] & 0xFF) - tblOffset];
/* 1882 */           srcPixelOffset += srcPixelStride;
/* 1883 */           dstPixelOffset += dstPixelStride;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void lookupU(int srcLineStride, int srcPixelStride, int[] srcBandOffsets, short[][] srcData, int width, int height, int bands, int dstLineStride, int dstPixelStride, int[] dstBandOffsets, double[][] dstData, int[] tblOffsets, double[][] tblData) {
/* 1896 */     for (int b = 0; b < bands; b++) {
/* 1897 */       short[] s = srcData[b];
/* 1898 */       double[] d = dstData[b];
/* 1899 */       double[] t = tblData[b];
/* 1901 */       int srcLineOffset = srcBandOffsets[b];
/* 1902 */       int dstLineOffset = dstBandOffsets[b];
/* 1903 */       int tblOffset = tblOffsets[b];
/* 1905 */       for (int h = 0; h < height; h++) {
/* 1906 */         int srcPixelOffset = srcLineOffset;
/* 1907 */         int dstPixelOffset = dstLineOffset;
/* 1909 */         srcLineOffset += srcLineStride;
/* 1910 */         dstLineOffset += dstLineStride;
/* 1912 */         for (int w = 0; w < width; w++) {
/* 1913 */           d[dstPixelOffset] = t[(s[srcPixelOffset] & 0xFFFF) - tblOffset];
/* 1916 */           srcPixelOffset += srcPixelStride;
/* 1917 */           dstPixelOffset += dstPixelStride;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void lookup(int srcLineStride, int srcPixelStride, int[] srcBandOffsets, short[][] srcData, int width, int height, int bands, int dstLineStride, int dstPixelStride, int[] dstBandOffsets, double[][] dstData, int[] tblOffsets, double[][] tblData) {
/* 1930 */     for (int b = 0; b < bands; b++) {
/* 1931 */       short[] s = srcData[b];
/* 1932 */       double[] d = dstData[b];
/* 1933 */       double[] t = tblData[b];
/* 1935 */       int srcLineOffset = srcBandOffsets[b];
/* 1936 */       int dstLineOffset = dstBandOffsets[b];
/* 1937 */       int tblOffset = tblOffsets[b];
/* 1939 */       for (int h = 0; h < height; h++) {
/* 1940 */         int srcPixelOffset = srcLineOffset;
/* 1941 */         int dstPixelOffset = dstLineOffset;
/* 1943 */         srcLineOffset += srcLineStride;
/* 1944 */         dstLineOffset += dstLineStride;
/* 1946 */         for (int w = 0; w < width; w++) {
/* 1947 */           d[dstPixelOffset] = t[s[srcPixelOffset] - tblOffset];
/* 1949 */           srcPixelOffset += srcPixelStride;
/* 1950 */           dstPixelOffset += dstPixelStride;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void lookup(int srcLineStride, int srcPixelStride, int[] srcBandOffsets, int[][] srcData, int width, int height, int bands, int dstLineStride, int dstPixelStride, int[] dstBandOffsets, double[][] dstData, int[] tblOffsets, double[][] tblData) {
/* 1963 */     for (int b = 0; b < bands; b++) {
/* 1964 */       int[] s = srcData[b];
/* 1965 */       double[] d = dstData[b];
/* 1966 */       double[] t = tblData[b];
/* 1968 */       int srcLineOffset = srcBandOffsets[b];
/* 1969 */       int dstLineOffset = dstBandOffsets[b];
/* 1970 */       int tblOffset = tblOffsets[b];
/* 1972 */       for (int h = 0; h < height; h++) {
/* 1973 */         int srcPixelOffset = srcLineOffset;
/* 1974 */         int dstPixelOffset = dstLineOffset;
/* 1976 */         srcLineOffset += srcLineStride;
/* 1977 */         dstLineOffset += dstLineStride;
/* 1979 */         for (int w = 0; w < width; w++) {
/* 1980 */           d[dstPixelOffset] = t[s[srcPixelOffset] - tblOffset];
/* 1982 */           srcPixelOffset += srcPixelStride;
/* 1983 */           dstPixelOffset += dstPixelStride;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public int findNearestEntry(float[] pixel) {
/* 2007 */     if (pixel == null)
/* 2008 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 2011 */     int dataType = this.data.getDataType();
/* 2012 */     int numBands = getNumBands();
/* 2013 */     int numEntries = getNumEntries();
/* 2014 */     int index = -1;
/* 2016 */     if (dataType == 0) {
/* 2017 */       byte[][] buffer = getByteData();
/* 2020 */       float minDistance = 0.0F;
/* 2021 */       index = 0;
/* 2022 */       for (int b = 0; b < numBands; b++) {
/* 2023 */         float delta = pixel[b] - (buffer[b][0] & 0xFF);
/* 2024 */         minDistance += delta * delta;
/*      */       } 
/* 2029 */       for (int i = 1; i < numEntries; i++) {
/* 2030 */         float distance = 0.0F;
/* 2031 */         for (int j = 0; j < numBands; j++) {
/* 2032 */           float delta = pixel[j] - (buffer[j][i] & 0xFF);
/* 2034 */           distance += delta * delta;
/*      */         } 
/* 2037 */         if (distance < minDistance) {
/* 2038 */           minDistance = distance;
/* 2039 */           index = i;
/*      */         } 
/*      */       } 
/* 2042 */     } else if (dataType == 2) {
/* 2043 */       short[][] buffer = getShortData();
/* 2046 */       float minDistance = 0.0F;
/* 2047 */       index = 0;
/* 2048 */       for (int b = 0; b < numBands; b++) {
/* 2049 */         float delta = pixel[b] - buffer[b][0];
/* 2050 */         minDistance += delta * delta;
/*      */       } 
/* 2055 */       for (int i = 1; i < numEntries; i++) {
/* 2056 */         float distance = 0.0F;
/* 2057 */         for (int j = 0; j < numBands; j++) {
/* 2058 */           float delta = pixel[j] - buffer[j][i];
/* 2059 */           distance += delta * delta;
/*      */         } 
/* 2062 */         if (distance < minDistance) {
/* 2063 */           minDistance = distance;
/* 2064 */           index = i;
/*      */         } 
/*      */       } 
/* 2067 */     } else if (dataType == 1) {
/* 2068 */       short[][] buffer = getShortData();
/* 2071 */       float minDistance = 0.0F;
/* 2072 */       index = 0;
/* 2073 */       for (int b = 0; b < numBands; b++) {
/* 2074 */         float delta = pixel[b] - (buffer[b][0] & 0xFFFF);
/* 2075 */         minDistance += delta * delta;
/*      */       } 
/* 2080 */       for (int i = 1; i < numEntries; i++) {
/* 2081 */         float distance = 0.0F;
/* 2082 */         for (int j = 0; j < numBands; j++) {
/* 2083 */           float delta = pixel[j] - (buffer[j][i] & 0xFFFF);
/* 2085 */           distance += delta * delta;
/*      */         } 
/* 2088 */         if (distance < minDistance) {
/* 2089 */           minDistance = distance;
/* 2090 */           index = i;
/*      */         } 
/*      */       } 
/* 2093 */     } else if (dataType == 3) {
/* 2094 */       int[][] buffer = getIntData();
/* 2097 */       float minDistance = 0.0F;
/* 2098 */       index = 0;
/* 2099 */       for (int b = 0; b < numBands; b++) {
/* 2100 */         float delta = pixel[b] - buffer[b][0];
/* 2101 */         minDistance += delta * delta;
/*      */       } 
/* 2106 */       for (int i = 1; i < numEntries; i++) {
/* 2107 */         float distance = 0.0F;
/* 2108 */         for (int j = 0; j < numBands; j++) {
/* 2109 */           float delta = pixel[j] - buffer[j][i];
/* 2110 */           distance += delta * delta;
/*      */         } 
/* 2113 */         if (distance < minDistance) {
/* 2114 */           minDistance = distance;
/* 2115 */           index = i;
/*      */         } 
/*      */       } 
/* 2118 */     } else if (dataType == 4) {
/* 2119 */       float[][] buffer = getFloatData();
/* 2122 */       float minDistance = 0.0F;
/* 2123 */       index = 0;
/* 2124 */       for (int b = 0; b < numBands; b++) {
/* 2125 */         float delta = pixel[b] - buffer[b][0];
/* 2126 */         minDistance += delta * delta;
/*      */       } 
/* 2131 */       for (int i = 1; i < numEntries; i++) {
/* 2132 */         float distance = 0.0F;
/* 2133 */         for (int j = 0; j < numBands; j++) {
/* 2134 */           float delta = pixel[j] - buffer[j][i];
/* 2135 */           distance += delta * delta;
/*      */         } 
/* 2138 */         if (distance < minDistance) {
/* 2139 */           minDistance = distance;
/* 2140 */           index = i;
/*      */         } 
/*      */       } 
/* 2143 */     } else if (dataType == 5) {
/* 2144 */       double[][] buffer = getDoubleData();
/* 2147 */       double minDistance = 0.0D;
/* 2148 */       index = 0;
/* 2149 */       for (int b = 0; b < numBands; b++) {
/* 2150 */         double delta = pixel[b] - buffer[b][0];
/* 2151 */         minDistance += delta * delta;
/*      */       } 
/* 2156 */       for (int i = 1; i < numEntries; i++) {
/* 2157 */         double distance = 0.0D;
/* 2158 */         for (int j = 0; j < numBands; j++) {
/* 2159 */           double delta = pixel[j] - buffer[j][i];
/* 2160 */           distance += delta * delta;
/*      */         } 
/* 2163 */         if (distance < minDistance) {
/* 2164 */           minDistance = distance;
/* 2165 */           index = i;
/*      */         } 
/*      */       } 
/*      */     } else {
/* 2170 */       throw new RuntimeException(JaiI18N.getString("LookupTableJAI0"));
/*      */     } 
/* 2175 */     return (index == -1) ? index : (index + getOffset());
/*      */   }
/*      */   
/*      */   private void writeObject(ObjectOutputStream out) throws IOException {
/* 2184 */     out.defaultWriteObject();
/* 2185 */     out.writeObject(SerializerFactory.getState(this.data));
/*      */   }
/*      */   
/*      */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 2195 */     in.defaultReadObject();
/* 2196 */     Object object = in.readObject();
/* 2197 */     SerializableState ss = (SerializableState)object;
/* 2198 */     this.data = (DataBuffer)ss.getObject();
/*      */   }
/*      */   
/*      */   private void initOffsets(int nbands, int offset) {
/* 2202 */     this.tableOffsets = new int[nbands];
/* 2203 */     for (int i = 0; i < nbands; i++)
/* 2204 */       this.tableOffsets[i] = offset; 
/*      */   }
/*      */   
/*      */   private void initOffsets(int nbands, int[] offset) {
/* 2209 */     this.tableOffsets = new int[nbands];
/* 2210 */     for (int i = 0; i < nbands; i++)
/* 2211 */       this.tableOffsets[i] = offset[i]; 
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\LookupTableJAI.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */