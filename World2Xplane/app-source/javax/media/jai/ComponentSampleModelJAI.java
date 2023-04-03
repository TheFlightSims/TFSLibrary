/*      */ package javax.media.jai;
/*      */ 
/*      */ import com.sun.media.jai.util.DataBufferUtils;
/*      */ import java.awt.image.ComponentSampleModel;
/*      */ import java.awt.image.DataBuffer;
/*      */ import java.awt.image.DataBufferByte;
/*      */ import java.awt.image.DataBufferInt;
/*      */ import java.awt.image.DataBufferShort;
/*      */ import java.awt.image.DataBufferUShort;
/*      */ import java.awt.image.SampleModel;
/*      */ 
/*      */ public class ComponentSampleModelJAI extends ComponentSampleModel {
/*      */   public ComponentSampleModelJAI(int dataType, int w, int h, int pixelStride, int scanlineStride, int[] bandOffsets) {
/*   73 */     super(dataType, w, h, pixelStride, scanlineStride, bandOffsets);
/*      */   }
/*      */   
/*      */   public ComponentSampleModelJAI(int dataType, int w, int h, int pixelStride, int scanlineStride, int[] bankIndices, int[] bandOffsets) {
/*  101 */     super(dataType, w, h, pixelStride, scanlineStride, bankIndices, bandOffsets);
/*      */   }
/*      */   
/*      */   private long getBufferSize() {
/*  110 */     int maxBandOff = this.bandOffsets[0];
/*  111 */     for (int i = 1; i < this.bandOffsets.length; i++)
/*  112 */       maxBandOff = Math.max(maxBandOff, this.bandOffsets[i]); 
/*  114 */     long size = 0L;
/*  115 */     if (maxBandOff >= 0)
/*  116 */       size += (maxBandOff + 1); 
/*  117 */     if (this.pixelStride > 0)
/*  118 */       size += (this.pixelStride * (this.width - 1)); 
/*  119 */     if (this.scanlineStride > 0)
/*  120 */       size += (this.scanlineStride * (this.height - 1)); 
/*  121 */     return size;
/*      */   }
/*      */   
/*      */   private int[] JAIorderBands(int[] orig, int step) {
/*  128 */     int[] map = new int[orig.length];
/*  129 */     int[] ret = new int[orig.length];
/*      */     int i;
/*  131 */     for (i = 0; i < map.length; ) {
/*  131 */       map[i] = i;
/*  131 */       i++;
/*      */     } 
/*  133 */     for (i = 0; i < ret.length; i++) {
/*  134 */       int index = i;
/*  135 */       for (int j = i + 1; j < ret.length; j++) {
/*  136 */         if (orig[map[index]] > orig[map[j]])
/*  137 */           index = j; 
/*      */       } 
/*  140 */       ret[map[index]] = i * step;
/*  141 */       map[index] = map[i];
/*      */     } 
/*  143 */     return ret;
/*      */   }
/*      */   
/*      */   public SampleModel createCompatibleSampleModel(int w, int h) {
/*      */     int[] bandOff;
/*  156 */     SampleModel ret = null;
/*  158 */     int minBandOff = this.bandOffsets[0];
/*  159 */     int maxBandOff = this.bandOffsets[0];
/*  160 */     for (int i = 1; i < this.bandOffsets.length; i++) {
/*  161 */       minBandOff = Math.min(minBandOff, this.bandOffsets[i]);
/*  162 */       maxBandOff = Math.max(maxBandOff, this.bandOffsets[i]);
/*      */     } 
/*  164 */     maxBandOff -= minBandOff;
/*  166 */     int bands = this.bandOffsets.length;
/*  168 */     int pStride = Math.abs(this.pixelStride);
/*  169 */     int lStride = Math.abs(this.scanlineStride);
/*  170 */     int bStride = Math.abs(maxBandOff);
/*  172 */     if (pStride > lStride) {
/*  173 */       if (pStride > bStride) {
/*  174 */         if (lStride > bStride) {
/*  175 */           bandOff = new int[this.bandOffsets.length];
/*  176 */           for (int k = 0; k < bands; k++)
/*  177 */             bandOff[k] = this.bandOffsets[k] - minBandOff; 
/*  178 */           lStride = bStride + 1;
/*  179 */           pStride = lStride * h;
/*      */         } else {
/*  181 */           bandOff = JAIorderBands(this.bandOffsets, lStride * h);
/*  182 */           pStride = bands * lStride * h;
/*      */         } 
/*      */       } else {
/*  185 */         pStride = lStride * h;
/*  186 */         bandOff = JAIorderBands(this.bandOffsets, pStride * w);
/*      */       } 
/*  189 */     } else if (pStride > bStride) {
/*  190 */       bandOff = new int[this.bandOffsets.length];
/*  191 */       for (int k = 0; k < bands; k++)
/*  192 */         bandOff[k] = this.bandOffsets[k] - minBandOff; 
/*  193 */       pStride = bStride + 1;
/*  194 */       lStride = pStride * w;
/*  196 */     } else if (lStride > bStride) {
/*  197 */       bandOff = JAIorderBands(this.bandOffsets, pStride * w);
/*  198 */       lStride = bands * pStride * w;
/*      */     } else {
/*  200 */       lStride = pStride * w;
/*  201 */       bandOff = JAIorderBands(this.bandOffsets, lStride * h);
/*      */     } 
/*  207 */     int base = 0;
/*  208 */     if (this.scanlineStride < 0) {
/*  209 */       base += lStride * h;
/*  210 */       lStride *= -1;
/*      */     } 
/*  212 */     if (this.pixelStride < 0) {
/*  213 */       base += pStride * w;
/*  214 */       pStride *= -1;
/*      */     } 
/*  217 */     for (int j = 0; j < bands; j++)
/*  218 */       bandOff[j] = bandOff[j] + base; 
/*  219 */     return new ComponentSampleModelJAI(this.dataType, w, h, pStride, lStride, this.bankIndices, bandOff);
/*      */   }
/*      */   
/*      */   public SampleModel createSubsetSampleModel(int[] bands) {
/*  234 */     int[] newBankIndices = new int[bands.length];
/*  235 */     int[] newBandOffsets = new int[bands.length];
/*  236 */     for (int i = 0; i < bands.length; i++) {
/*  237 */       int b = bands[i];
/*  238 */       newBankIndices[i] = this.bankIndices[b];
/*  239 */       newBandOffsets[i] = this.bandOffsets[b];
/*      */     } 
/*  241 */     return new ComponentSampleModelJAI(this.dataType, this.width, this.height, this.pixelStride, this.scanlineStride, newBankIndices, newBandOffsets);
/*      */   }
/*      */   
/*      */   public DataBuffer createDataBuffer() {
/*  254 */     DataBuffer dataBuffer = null;
/*  256 */     int size = (int)getBufferSize();
/*  257 */     switch (this.dataType) {
/*      */       case 0:
/*  259 */         dataBuffer = new DataBufferByte(size, this.numBanks);
/*  280 */         return dataBuffer;
/*      */       case 1:
/*      */         dataBuffer = new DataBufferUShort(size, this.numBanks);
/*  280 */         return dataBuffer;
/*      */       case 3:
/*      */         dataBuffer = new DataBufferInt(size, this.numBanks);
/*  280 */         return dataBuffer;
/*      */       case 2:
/*      */         dataBuffer = new DataBufferShort(size, this.numBanks);
/*  280 */         return dataBuffer;
/*      */       case 4:
/*      */         dataBuffer = DataBufferUtils.createDataBufferFloat(size, this.numBanks);
/*  280 */         return dataBuffer;
/*      */       case 5:
/*      */         dataBuffer = DataBufferUtils.createDataBufferDouble(size, this.numBanks);
/*  280 */         return dataBuffer;
/*      */     } 
/*      */     throw new RuntimeException(JaiI18N.getString("RasterFactory3"));
/*      */   }
/*      */   
/*      */   public Object getDataElements(int x, int y, Object obj, DataBuffer data) {
/*      */     byte[] bdata;
/*      */     int i;
/*      */     short[] usdata;
/*      */     int j, idata[], k;
/*      */     short[] sdata;
/*      */     int m;
/*      */     float[] fdata;
/*      */     int n;
/*      */     double[] ddata;
/*  323 */     int i1, type = getTransferType();
/*  324 */     int numDataElems = getNumDataElements();
/*  325 */     int pixelOffset = y * this.scanlineStride + x * this.pixelStride;
/*  327 */     switch (type) {
/*      */       case 0:
/*  333 */         if (obj == null) {
/*  334 */           bdata = new byte[numDataElems];
/*      */         } else {
/*  336 */           bdata = (byte[])obj;
/*      */         } 
/*  338 */         for (i = 0; i < numDataElems; i++)
/*  339 */           bdata[i] = (byte)data.getElem(this.bankIndices[i], pixelOffset + this.bandOffsets[i]); 
/*  343 */         obj = bdata;
/*  436 */         return obj;
/*      */       case 1:
/*      */         if (obj == null) {
/*      */           usdata = new short[numDataElems];
/*      */         } else {
/*      */           usdata = (short[])obj;
/*      */         } 
/*      */         for (j = 0; j < numDataElems; j++)
/*      */           usdata[j] = (short)data.getElem(this.bankIndices[j], pixelOffset + this.bandOffsets[j]); 
/*      */         obj = usdata;
/*  436 */         return obj;
/*      */       case 3:
/*      */         if (obj == null) {
/*      */           idata = new int[numDataElems];
/*      */         } else {
/*      */           idata = (int[])obj;
/*      */         } 
/*      */         for (k = 0; k < numDataElems; k++)
/*      */           idata[k] = data.getElem(this.bankIndices[k], pixelOffset + this.bandOffsets[k]); 
/*      */         obj = idata;
/*  436 */         return obj;
/*      */       case 2:
/*      */         if (obj == null) {
/*      */           sdata = new short[numDataElems];
/*      */         } else {
/*      */           sdata = (short[])obj;
/*      */         } 
/*      */         for (m = 0; m < numDataElems; m++)
/*      */           sdata[m] = (short)data.getElem(this.bankIndices[m], pixelOffset + this.bandOffsets[m]); 
/*      */         obj = sdata;
/*  436 */         return obj;
/*      */       case 4:
/*      */         if (obj == null) {
/*      */           fdata = new float[numDataElems];
/*      */         } else {
/*      */           fdata = (float[])obj;
/*      */         } 
/*      */         for (n = 0; n < numDataElems; n++)
/*      */           fdata[n] = data.getElemFloat(this.bankIndices[n], pixelOffset + this.bandOffsets[n]); 
/*      */         obj = fdata;
/*  436 */         return obj;
/*      */       case 5:
/*      */         if (obj == null) {
/*      */           ddata = new double[numDataElems];
/*      */         } else {
/*      */           ddata = (double[])obj;
/*      */         } 
/*      */         for (i1 = 0; i1 < numDataElems; i1++)
/*      */           ddata[i1] = data.getElemDouble(this.bankIndices[i1], pixelOffset + this.bandOffsets[i1]); 
/*      */         obj = ddata;
/*  436 */         return obj;
/*      */     } 
/*      */     throw new RuntimeException(JaiI18N.getString("RasterFactory3"));
/*      */   }
/*      */   
/*      */   public Object getDataElements(int x, int y, int w, int h, Object obj, DataBuffer data) {
/*      */     short[] usdata;
/*      */     int[] idata;
/*      */     short[] sdata;
/*      */     float[] fdata;
/*      */     double[] ddata;
/*      */     byte[] bdata;
/*  487 */     int i, type = getTransferType();
/*  488 */     int numDataElems = getNumDataElements();
/*  489 */     int cnt = 0;
/*  490 */     Object o = null;
/*  492 */     switch (type) {
/*      */       case 0:
/*  498 */         if (obj == null) {
/*  499 */           bdata = new byte[numDataElems * w * h];
/*      */         } else {
/*  501 */           bdata = (byte[])obj;
/*      */         } 
/*  503 */         for (i = y; i < y + h; i++) {
/*  504 */           for (int j = x; j < x + w; j++) {
/*  505 */             o = getDataElements(j, i, o, data);
/*  506 */             byte[] btemp = (byte[])o;
/*  507 */             for (int k = 0; k < numDataElems; k++)
/*  508 */               bdata[cnt++] = btemp[k]; 
/*      */           } 
/*      */         } 
/*  512 */         obj = bdata;
/*  640 */         return obj;
/*      */       case 1:
/*      */         if (obj == null) {
/*      */           usdata = new short[numDataElems * w * h];
/*      */         } else {
/*      */           usdata = (short[])obj;
/*      */         } 
/*      */         for (i = y; i < y + h; i++) {
/*      */           for (int j = x; j < x + w; j++) {
/*      */             o = getDataElements(j, i, o, data);
/*      */             short[] ustemp = (short[])o;
/*      */             for (int k = 0; k < numDataElems; k++)
/*      */               usdata[cnt++] = ustemp[k]; 
/*      */           } 
/*      */         } 
/*      */         obj = usdata;
/*  640 */         return obj;
/*      */       case 3:
/*      */         if (obj == null) {
/*      */           idata = new int[numDataElems * w * h];
/*      */         } else {
/*      */           idata = (int[])obj;
/*      */         } 
/*      */         for (i = y; i < y + h; i++) {
/*      */           for (int j = x; j < x + w; j++) {
/*      */             o = getDataElements(j, i, o, data);
/*      */             int[] itemp = (int[])o;
/*      */             for (int k = 0; k < numDataElems; k++)
/*      */               idata[cnt++] = itemp[k]; 
/*      */           } 
/*      */         } 
/*      */         obj = idata;
/*  640 */         return obj;
/*      */       case 2:
/*      */         if (obj == null) {
/*      */           sdata = new short[numDataElems * w * h];
/*      */         } else {
/*      */           sdata = (short[])obj;
/*      */         } 
/*      */         for (i = y; i < y + h; i++) {
/*      */           for (int j = x; j < x + w; j++) {
/*      */             o = getDataElements(j, i, o, data);
/*      */             short[] stemp = (short[])o;
/*      */             for (int k = 0; k < numDataElems; k++)
/*      */               sdata[cnt++] = stemp[k]; 
/*      */           } 
/*      */         } 
/*      */         obj = sdata;
/*  640 */         return obj;
/*      */       case 4:
/*      */         if (obj == null) {
/*      */           fdata = new float[numDataElems * w * h];
/*      */         } else {
/*      */           fdata = (float[])obj;
/*      */         } 
/*      */         for (i = y; i < y + h; i++) {
/*      */           for (int j = x; j < x + w; j++) {
/*      */             o = getDataElements(j, i, o, data);
/*      */             float[] ftemp = (float[])o;
/*      */             for (int k = 0; k < numDataElems; k++)
/*      */               fdata[cnt++] = ftemp[k]; 
/*      */           } 
/*      */         } 
/*      */         obj = fdata;
/*  640 */         return obj;
/*      */       case 5:
/*      */         if (obj == null) {
/*      */           ddata = new double[numDataElems * w * h];
/*      */         } else {
/*      */           ddata = (double[])obj;
/*      */         } 
/*      */         for (i = y; i < y + h; i++) {
/*      */           for (int j = x; j < x + w; j++) {
/*      */             o = getDataElements(j, i, o, data);
/*      */             double[] dtemp = (double[])o;
/*      */             for (int k = 0; k < numDataElems; k++)
/*      */               ddata[cnt++] = dtemp[k]; 
/*      */           } 
/*      */         } 
/*      */         obj = ddata;
/*  640 */         return obj;
/*      */     } 
/*      */     throw new RuntimeException(JaiI18N.getString("RasterFactory3"));
/*      */   }
/*      */   
/*      */   public void setDataElements(int x, int y, Object obj, DataBuffer data) {
/*      */     byte[] barray;
/*      */     int i;
/*      */     short[] usarray;
/*      */     int j, iarray[], k;
/*      */     short[] sarray;
/*      */     int m;
/*      */     float[] farray;
/*      */     int n;
/*      */     double[] darray;
/*  684 */     int i1, type = getTransferType();
/*  685 */     int numDataElems = getNumDataElements();
/*  686 */     int pixelOffset = y * this.scanlineStride + x * this.pixelStride;
/*  688 */     switch (type) {
/*      */       case 0:
/*  692 */         barray = (byte[])obj;
/*  694 */         for (i = 0; i < numDataElems; i++)
/*  695 */           data.setElem(this.bankIndices[i], pixelOffset + this.bandOffsets[i], barray[i] & 0xFF); 
/*      */         return;
/*      */       case 1:
/*  702 */         usarray = (short[])obj;
/*  704 */         for (j = 0; j < numDataElems; j++)
/*  705 */           data.setElem(this.bankIndices[j], pixelOffset + this.bandOffsets[j], usarray[j] & 0xFFFF); 
/*      */         return;
/*      */       case 3:
/*  712 */         iarray = (int[])obj;
/*  714 */         for (k = 0; k < numDataElems; k++)
/*  715 */           data.setElem(this.bankIndices[k], pixelOffset + this.bandOffsets[k], iarray[k]); 
/*      */         return;
/*      */       case 2:
/*  722 */         sarray = (short[])obj;
/*  724 */         for (m = 0; m < numDataElems; m++)
/*  725 */           data.setElem(this.bankIndices[m], pixelOffset + this.bandOffsets[m], sarray[m]); 
/*      */         return;
/*      */       case 4:
/*  732 */         farray = (float[])obj;
/*  734 */         for (n = 0; n < numDataElems; n++)
/*  735 */           data.setElemFloat(this.bankIndices[n], pixelOffset + this.bandOffsets[n], farray[n]); 
/*      */         return;
/*      */       case 5:
/*  742 */         darray = (double[])obj;
/*  744 */         for (i1 = 0; i1 < numDataElems; i1++)
/*  745 */           data.setElemDouble(this.bankIndices[i1], pixelOffset + this.bandOffsets[i1], darray[i1]); 
/*      */         return;
/*      */     } 
/*  751 */     throw new RuntimeException(JaiI18N.getString("RasterFactory3"));
/*      */   }
/*      */   
/*      */   public void setDataElements(int x, int y, int w, int h, Object obj, DataBuffer data) {
/*      */     byte[] barray;
/*      */     short[] usarray;
/*      */     int[] iArray;
/*      */     short[] sArray;
/*      */     float[] fArray;
/*      */     double[] dArray;
/*      */     byte[] btemp;
/*      */     short[] ustemp;
/*      */     int[] itemp;
/*      */     short[] stemp;
/*      */     float[] ftemp;
/*      */     double[] dtemp;
/*  797 */     int i, cnt = 0;
/*  798 */     Object o = null;
/*  799 */     int type = getTransferType();
/*  800 */     int numDataElems = getNumDataElements();
/*  802 */     switch (type) {
/*      */       case 0:
/*  806 */         barray = (byte[])obj;
/*  807 */         btemp = new byte[numDataElems];
/*  809 */         for (i = y; i < y + h; i++) {
/*  810 */           for (int j = x; j < x + w; j++) {
/*  811 */             for (int k = 0; k < numDataElems; k++)
/*  812 */               btemp[k] = barray[cnt++]; 
/*  815 */             setDataElements(j, i, btemp, data);
/*      */           } 
/*      */         } 
/*      */         return;
/*      */       case 1:
/*  823 */         usarray = (short[])obj;
/*  824 */         ustemp = new short[numDataElems];
/*  826 */         for (i = y; i < y + h; i++) {
/*  827 */           for (int j = x; j < x + w; j++) {
/*  828 */             for (int k = 0; k < numDataElems; k++)
/*  829 */               ustemp[k] = usarray[cnt++]; 
/*  831 */             setDataElements(j, i, ustemp, data);
/*      */           } 
/*      */         } 
/*      */         return;
/*      */       case 3:
/*  839 */         iArray = (int[])obj;
/*  840 */         itemp = new int[numDataElems];
/*  842 */         for (i = y; i < y + h; i++) {
/*  843 */           for (int j = x; j < x + w; j++) {
/*  844 */             for (int k = 0; k < numDataElems; k++)
/*  845 */               itemp[k] = iArray[cnt++]; 
/*  848 */             setDataElements(j, i, itemp, data);
/*      */           } 
/*      */         } 
/*      */         return;
/*      */       case 2:
/*  856 */         sArray = (short[])obj;
/*  857 */         stemp = new short[numDataElems];
/*  859 */         for (i = y; i < y + h; i++) {
/*  860 */           for (int j = x; j < x + w; j++) {
/*  861 */             for (int k = 0; k < numDataElems; k++)
/*  862 */               stemp[k] = sArray[cnt++]; 
/*  865 */             setDataElements(j, i, stemp, data);
/*      */           } 
/*      */         } 
/*      */         return;
/*      */       case 4:
/*  873 */         fArray = (float[])obj;
/*  874 */         ftemp = new float[numDataElems];
/*  876 */         for (i = y; i < y + h; i++) {
/*  877 */           for (int j = x; j < x + w; j++) {
/*  878 */             for (int k = 0; k < numDataElems; k++)
/*  879 */               ftemp[k] = fArray[cnt++]; 
/*  882 */             setDataElements(j, i, ftemp, data);
/*      */           } 
/*      */         } 
/*      */         return;
/*      */       case 5:
/*  890 */         dArray = (double[])obj;
/*  891 */         dtemp = new double[numDataElems];
/*  893 */         for (i = y; i < y + h; i++) {
/*  894 */           for (int j = x; j < x + w; j++) {
/*  895 */             for (int k = 0; k < numDataElems; k++)
/*  896 */               dtemp[k] = dArray[cnt++]; 
/*  899 */             setDataElements(j, i, dtemp, data);
/*      */           } 
/*      */         } 
/*      */         return;
/*      */     } 
/*  906 */     throw new RuntimeException(JaiI18N.getString("RasterFactory3"));
/*      */   }
/*      */   
/*      */   public void setSample(int x, int y, int b, float s, DataBuffer data) {
/*  926 */     data.setElemFloat(this.bankIndices[b], y * this.scanlineStride + x * this.pixelStride + this.bandOffsets[b], s);
/*      */   }
/*      */   
/*      */   public float getSampleFloat(int x, int y, int b, DataBuffer data) {
/*  945 */     float sample = data.getElemFloat(this.bankIndices[b], y * this.scanlineStride + x * this.pixelStride + this.bandOffsets[b]);
/*  949 */     return sample;
/*      */   }
/*      */   
/*      */   public void setSample(int x, int y, int b, double s, DataBuffer data) {
/*  968 */     data.setElemDouble(this.bankIndices[b], y * this.scanlineStride + x * this.pixelStride + this.bandOffsets[b], s);
/*      */   }
/*      */   
/*      */   public double getSampleDouble(int x, int y, int b, DataBuffer data) {
/*  987 */     double sample = data.getElemDouble(this.bankIndices[b], y * this.scanlineStride + x * this.pixelStride + this.bandOffsets[b]);
/*  991 */     return sample;
/*      */   }
/*      */   
/*      */   public double[] getPixels(int x, int y, int w, int h, double[] dArray, DataBuffer data) {
/*      */     double[] pixels;
/* 1010 */     int Offset = 0;
/* 1012 */     if (dArray != null) {
/* 1013 */       pixels = dArray;
/*      */     } else {
/* 1015 */       pixels = new double[this.numBands * w * h];
/*      */     } 
/* 1017 */     for (int i = y; i < h + y; i++) {
/* 1018 */       for (int j = x; j < w + x; j++) {
/* 1019 */         for (int k = 0; k < this.numBands; k++)
/* 1020 */           pixels[Offset++] = getSampleDouble(j, i, k, data); 
/*      */       } 
/*      */     } 
/* 1025 */     return pixels;
/*      */   }
/*      */   
/*      */   public String toString() {
/* 1030 */     String ret = "ComponentSampleModelJAI:   dataType=" + getDataType() + "  numBands=" + getNumBands() + "  width=" + getWidth() + "  height=" + getHeight() + "  bandOffsets=[ ";
/* 1036 */     for (int i = 0; i < this.numBands; i++)
/* 1037 */       ret = ret + getBandOffsets()[i] + " "; 
/* 1039 */     ret = ret + "]";
/* 1040 */     return ret;
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\ComponentSampleModelJAI.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */