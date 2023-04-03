/*      */ package javax.media.jai;
/*      */ 
/*      */ import com.sun.media.jai.util.DataBufferUtils;
/*      */ import java.awt.Point;
/*      */ import java.awt.color.ColorSpace;
/*      */ import java.awt.image.ComponentColorModel;
/*      */ import java.awt.image.DataBuffer;
/*      */ import java.awt.image.DataBufferByte;
/*      */ import java.awt.image.DataBufferInt;
/*      */ import java.awt.image.DataBufferShort;
/*      */ import java.awt.image.DataBufferUShort;
/*      */ import java.awt.image.PixelInterleavedSampleModel;
/*      */ import java.awt.image.Raster;
/*      */ import java.awt.image.SampleModel;
/*      */ import java.awt.image.WritableRaster;
/*      */ 
/*      */ public class RasterFactory {
/*      */   public static WritableRaster createInterleavedRaster(int dataType, int width, int height, int numBands, Point location) {
/*   79 */     if (numBands < 1)
/*   80 */       throw new IllegalArgumentException(JaiI18N.getString("RasterFactory0")); 
/*   82 */     int[] bandOffsets = new int[numBands];
/*   83 */     for (int i = 0; i < numBands; i++)
/*   84 */       bandOffsets[i] = numBands - 1 - i; 
/*   86 */     return createInterleavedRaster(dataType, width, height, width * numBands, numBands, bandOffsets, location);
/*      */   }
/*      */   
/*      */   public static WritableRaster createInterleavedRaster(int dataType, int width, int height, int scanlineStride, int pixelStride, int[] bandOffsets, Point location) {
/*      */     DataBuffer d;
/*  131 */     if (bandOffsets == null)
/*  132 */       throw new IllegalArgumentException(JaiI18N.getString("RasterFactory4")); 
/*  136 */     int bands = bandOffsets.length;
/*  138 */     int maxBandOff = bandOffsets[0];
/*  139 */     for (int i = 1; i < bands; i++) {
/*  140 */       if (bandOffsets[i] > maxBandOff)
/*  141 */         maxBandOff = bandOffsets[i]; 
/*      */     } 
/*  145 */     long lsize = maxBandOff + scanlineStride * (height - 1) + pixelStride * (width - 1) + 1L;
/*  148 */     if (lsize > 2147483647L)
/*  149 */       throw new IllegalArgumentException(JaiI18N.getString("RasterFactory16")); 
/*  151 */     int size = (int)lsize;
/*  153 */     switch (dataType) {
/*      */       case 0:
/*  155 */         d = new DataBufferByte(size);
/*  182 */         return createInterleavedRaster(d, width, height, scanlineStride, pixelStride, bandOffsets, location);
/*      */       case 1:
/*      */         d = new DataBufferUShort(size);
/*  182 */         return createInterleavedRaster(d, width, height, scanlineStride, pixelStride, bandOffsets, location);
/*      */       case 2:
/*      */         d = new DataBufferShort(size);
/*  182 */         return createInterleavedRaster(d, width, height, scanlineStride, pixelStride, bandOffsets, location);
/*      */       case 3:
/*      */         d = new DataBufferInt(size);
/*  182 */         return createInterleavedRaster(d, width, height, scanlineStride, pixelStride, bandOffsets, location);
/*      */       case 4:
/*      */         d = DataBufferUtils.createDataBufferFloat(size);
/*  182 */         return createInterleavedRaster(d, width, height, scanlineStride, pixelStride, bandOffsets, location);
/*      */       case 5:
/*      */         d = DataBufferUtils.createDataBufferDouble(size);
/*  182 */         return createInterleavedRaster(d, width, height, scanlineStride, pixelStride, bandOffsets, location);
/*      */     } 
/*      */     throw new IllegalArgumentException(JaiI18N.getString("RasterFactory3"));
/*      */   }
/*      */   
/*      */   public static WritableRaster createBandedRaster(int dataType, int width, int height, int bands, Point location) {
/*  218 */     if (bands < 1)
/*  219 */       throw new IllegalArgumentException(JaiI18N.getString("RasterFactory0")); 
/*  221 */     int[] bankIndices = new int[bands];
/*  222 */     int[] bandOffsets = new int[bands];
/*  223 */     for (int i = 0; i < bands; i++) {
/*  224 */       bankIndices[i] = i;
/*  225 */       bandOffsets[i] = 0;
/*      */     } 
/*  228 */     return createBandedRaster(dataType, width, height, width, bankIndices, bandOffsets, location);
/*      */   }
/*      */   
/*      */   public static WritableRaster createBandedRaster(int dataType, int width, int height, int scanlineStride, int[] bankIndices, int[] bandOffsets, Point location) {
/*      */     DataBuffer d;
/*  278 */     int bands = bandOffsets.length;
/*  280 */     if (bankIndices == null)
/*  281 */       throw new IllegalArgumentException(JaiI18N.getString("RasterFactory1")); 
/*  283 */     if (bandOffsets == null)
/*  284 */       throw new IllegalArgumentException(JaiI18N.getString("RasterFactory4")); 
/*  287 */     if (bandOffsets.length != bankIndices.length)
/*  288 */       throw new IllegalArgumentException(JaiI18N.getString("RasterFactory2")); 
/*  292 */     int maxBank = bankIndices[0];
/*  293 */     int maxBandOff = bandOffsets[0];
/*  294 */     for (int i = 1; i < bands; i++) {
/*  295 */       if (bankIndices[i] > maxBank)
/*  296 */         maxBank = bankIndices[i]; 
/*  298 */       if (bandOffsets[i] > maxBandOff)
/*  299 */         maxBandOff = bandOffsets[i]; 
/*      */     } 
/*  303 */     int banks = maxBank + 1;
/*  304 */     long lsize = maxBandOff + scanlineStride * (height - 1) + (width - 1) + 1L;
/*  306 */     if (lsize > 2147483647L)
/*  307 */       throw new IllegalArgumentException(JaiI18N.getString("RasterFactory16")); 
/*  309 */     int size = (int)lsize;
/*  311 */     switch (dataType) {
/*      */       case 0:
/*  313 */         d = new DataBufferByte(size, banks);
/*  340 */         return createBandedRaster(d, width, height, scanlineStride, bankIndices, bandOffsets, location);
/*      */       case 1:
/*      */         d = new DataBufferUShort(size, banks);
/*  340 */         return createBandedRaster(d, width, height, scanlineStride, bankIndices, bandOffsets, location);
/*      */       case 2:
/*      */         d = new DataBufferShort(size, banks);
/*  340 */         return createBandedRaster(d, width, height, scanlineStride, bankIndices, bandOffsets, location);
/*      */       case 3:
/*      */         d = new DataBufferInt(size, banks);
/*  340 */         return createBandedRaster(d, width, height, scanlineStride, bankIndices, bandOffsets, location);
/*      */       case 4:
/*      */         d = DataBufferUtils.createDataBufferFloat(size, banks);
/*  340 */         return createBandedRaster(d, width, height, scanlineStride, bankIndices, bandOffsets, location);
/*      */       case 5:
/*      */         d = DataBufferUtils.createDataBufferDouble(size, banks);
/*  340 */         return createBandedRaster(d, width, height, scanlineStride, bankIndices, bandOffsets, location);
/*      */     } 
/*      */     throw new IllegalArgumentException(JaiI18N.getString("RasterFactory3"));
/*      */   }
/*      */   
/*      */   public static WritableRaster createPackedRaster(int dataType, int width, int height, int[] bandMasks, Point location) {
/*  372 */     return Raster.createPackedRaster(dataType, width, height, bandMasks, location);
/*      */   }
/*      */   
/*      */   public static WritableRaster createPackedRaster(int dataType, int width, int height, int numBands, int bitsPerBand, Point location) {
/*  418 */     if (bitsPerBand <= 0)
/*  419 */       throw new IllegalArgumentException(JaiI18N.getString("RasterFactory15")); 
/*  422 */     return Raster.createPackedRaster(dataType, width, height, numBands, bitsPerBand, location);
/*      */   }
/*      */   
/*      */   public static WritableRaster createInterleavedRaster(DataBuffer dataBuffer, int width, int height, int scanlineStride, int pixelStride, int[] bandOffsets, Point location) {
/*      */     PixelInterleavedSampleModel csm;
/*      */     int minBandOff, maxBandOff, i;
/*      */     SampleModel sm;
/*  459 */     if (bandOffsets == null)
/*  460 */       throw new IllegalArgumentException(JaiI18N.getString("RasterFactory4")); 
/*  462 */     if (location == null)
/*  463 */       location = new Point(0, 0); 
/*  465 */     int dataType = dataBuffer.getDataType();
/*  467 */     switch (dataType) {
/*      */       case 0:
/*      */       case 1:
/*  470 */         csm = new PixelInterleavedSampleModel(dataType, width, height, pixelStride, scanlineStride, bandOffsets);
/*  475 */         return Raster.createWritableRaster(csm, dataBuffer, location);
/*      */       case 2:
/*      */       case 3:
/*      */       case 4:
/*      */       case 5:
/*  481 */         minBandOff = bandOffsets[0];
/*  482 */         maxBandOff = bandOffsets[0];
/*  483 */         for (i = 1; i < bandOffsets.length; i++) {
/*  484 */           minBandOff = Math.min(minBandOff, bandOffsets[i]);
/*  485 */           maxBandOff = Math.max(maxBandOff, bandOffsets[i]);
/*      */         } 
/*  487 */         maxBandOff -= minBandOff;
/*  488 */         if (maxBandOff > scanlineStride)
/*  489 */           throw new IllegalArgumentException(JaiI18N.getString("RasterFactory5")); 
/*  493 */         if (pixelStride * width > scanlineStride)
/*  494 */           throw new IllegalArgumentException(JaiI18N.getString("RasterFactory6")); 
/*  497 */         if (pixelStride < maxBandOff)
/*  498 */           throw new IllegalArgumentException(JaiI18N.getString("RasterFactory7")); 
/*  502 */         sm = new ComponentSampleModelJAI(dataType, width, height, pixelStride, scanlineStride, bandOffsets);
/*  507 */         return Raster.createWritableRaster(sm, dataBuffer, location);
/*      */     } 
/*  510 */     throw new IllegalArgumentException(JaiI18N.getString("RasterFactory3"));
/*      */   }
/*      */   
/*      */   public static WritableRaster createBandedRaster(DataBuffer dataBuffer, int width, int height, int scanlineStride, int[] bankIndices, int[] bandOffsets, Point location) {
/*  555 */     if (location == null)
/*  556 */       location = new Point(0, 0); 
/*  558 */     int dataType = dataBuffer.getDataType();
/*  560 */     if (bankIndices == null)
/*  561 */       throw new IllegalArgumentException(JaiI18N.getString("RasterFactory1")); 
/*  563 */     if (bandOffsets == null)
/*  564 */       throw new IllegalArgumentException(JaiI18N.getString("RasterFactory4")); 
/*  567 */     int bands = bankIndices.length;
/*  568 */     if (bandOffsets.length != bands)
/*  569 */       throw new IllegalArgumentException(JaiI18N.getString("RasterFactory2")); 
/*  572 */     SampleModel bsm = new ComponentSampleModelJAI(dataType, width, height, 1, scanlineStride, bankIndices, bandOffsets);
/*  577 */     switch (dataType) {
/*      */       case 0:
/*      */       case 1:
/*      */       case 2:
/*      */       case 3:
/*      */       case 4:
/*      */       case 5:
/*  584 */         return Raster.createWritableRaster(bsm, dataBuffer, location);
/*      */     } 
/*  587 */     throw new IllegalArgumentException(JaiI18N.getString("RasterFactory3"));
/*      */   }
/*      */   
/*      */   public static WritableRaster createPackedRaster(DataBuffer dataBuffer, int width, int height, int scanlineStride, int[] bandMasks, Point location) {
/*  620 */     return Raster.createPackedRaster(dataBuffer, width, height, scanlineStride, bandMasks, location);
/*      */   }
/*      */   
/*      */   public static WritableRaster createPackedRaster(DataBuffer dataBuffer, int width, int height, int bitsPerPixel, Point location) {
/*  651 */     return Raster.createPackedRaster(dataBuffer, width, height, bitsPerPixel, location);
/*      */   }
/*      */   
/*      */   public static Raster createRaster(SampleModel sampleModel, DataBuffer dataBuffer, Point location) {
/*  671 */     return Raster.createRaster(sampleModel, dataBuffer, location);
/*      */   }
/*      */   
/*      */   public static WritableRaster createWritableRaster(SampleModel sampleModel, Point location) {
/*  687 */     if (location == null)
/*  688 */       location = new Point(0, 0); 
/*  691 */     return createWritableRaster(sampleModel, sampleModel.createDataBuffer(), location);
/*      */   }
/*      */   
/*      */   public static WritableRaster createWritableRaster(SampleModel sampleModel, DataBuffer dataBuffer, Point location) {
/*  712 */     return Raster.createWritableRaster(sampleModel, dataBuffer, location);
/*      */   }
/*      */   
/*      */   public static WritableRaster createWritableChild(WritableRaster raster, int parentX, int parentY, int width, int height, int childMinX, int childMinY, int[] bandList) {
/*  782 */     return raster.createWritableChild(parentX, parentY, width, height, childMinX, childMinY, bandList);
/*      */   }
/*      */   
/*      */   public static SampleModel createBandedSampleModel(int dataType, int width, int height, int numBands, int[] bankIndices, int[] bandOffsets) {
/*  824 */     if (numBands < 1)
/*  825 */       throw new IllegalArgumentException(JaiI18N.getString("RasterFactory0")); 
/*  827 */     if (bankIndices == null) {
/*  828 */       bankIndices = new int[numBands];
/*  829 */       for (int i = 0; i < numBands; i++)
/*  830 */         bankIndices[i] = i; 
/*      */     } 
/*  833 */     if (bandOffsets == null) {
/*  834 */       bandOffsets = new int[numBands];
/*  835 */       for (int i = 0; i < numBands; i++)
/*  836 */         bandOffsets[i] = 0; 
/*      */     } 
/*  839 */     if (bandOffsets.length != bankIndices.length)
/*  840 */       throw new IllegalArgumentException(JaiI18N.getString("RasterFactory2")); 
/*  842 */     return new ComponentSampleModelJAI(dataType, width, height, 1, width, bankIndices, bandOffsets);
/*      */   }
/*      */   
/*      */   public static SampleModel createBandedSampleModel(int dataType, int width, int height, int numBands) {
/*  875 */     return createBandedSampleModel(dataType, width, height, numBands, null, null);
/*      */   }
/*      */   
/*      */   public static SampleModel createPixelInterleavedSampleModel(int dataType, int width, int height, int pixelStride, int scanlineStride, int[] bandOffsets) {
/*  910 */     if (bandOffsets == null)
/*  911 */       throw new IllegalArgumentException(JaiI18N.getString("RasterFactory4")); 
/*  913 */     int minBandOff = bandOffsets[0];
/*  914 */     int maxBandOff = bandOffsets[0];
/*  915 */     for (int i = 1; i < bandOffsets.length; i++) {
/*  916 */       minBandOff = Math.min(minBandOff, bandOffsets[i]);
/*  917 */       maxBandOff = Math.max(maxBandOff, bandOffsets[i]);
/*      */     } 
/*  919 */     maxBandOff -= minBandOff;
/*  920 */     if (maxBandOff > scanlineStride)
/*  921 */       throw new IllegalArgumentException(JaiI18N.getString("RasterFactory5")); 
/*  925 */     if (pixelStride * width > scanlineStride)
/*  926 */       throw new IllegalArgumentException(JaiI18N.getString("RasterFactory6")); 
/*  929 */     if (pixelStride < maxBandOff)
/*  930 */       throw new IllegalArgumentException(JaiI18N.getString("RasterFactory7")); 
/*  934 */     switch (dataType) {
/*      */       case 0:
/*      */       case 1:
/*  937 */         return new PixelInterleavedSampleModel(dataType, width, height, pixelStride, scanlineStride, bandOffsets);
/*      */       case 2:
/*      */       case 3:
/*      */       case 4:
/*      */       case 5:
/*  946 */         return new ComponentSampleModelJAI(dataType, width, height, pixelStride, scanlineStride, bandOffsets);
/*      */     } 
/*  952 */     throw new IllegalArgumentException(JaiI18N.getString("RasterFactory3"));
/*      */   }
/*      */   
/*      */   public static SampleModel createPixelInterleavedSampleModel(int dataType, int width, int height, int numBands) {
/*  981 */     if (numBands < 1)
/*  982 */       throw new IllegalArgumentException(JaiI18N.getString("RasterFactory0")); 
/*  984 */     int[] bandOffsets = new int[numBands];
/*  985 */     for (int i = 0; i < numBands; i++)
/*  986 */       bandOffsets[i] = numBands - 1 - i; 
/*  989 */     return createPixelInterleavedSampleModel(dataType, width, height, numBands, numBands * width, bandOffsets);
/*      */   }
/*      */   
/*      */   public static SampleModel createComponentSampleModel(SampleModel sm, int dataType, int width, int height, int numBands) {
/* 1015 */     if (sm instanceof java.awt.image.BandedSampleModel)
/* 1016 */       return createBandedSampleModel(dataType, width, height, numBands); 
/* 1018 */     return createPixelInterleavedSampleModel(dataType, width, height, numBands);
/*      */   }
/*      */   
/*      */   public static ComponentColorModel createComponentColorModel(int dataType, ColorSpace colorSpace, boolean useAlpha, boolean premultiplied, int transparency) {
/* 1065 */     if (colorSpace == null)
/* 1066 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 1069 */     if (transparency != 1 && transparency != 2 && transparency != 3)
/* 1073 */       throw new IllegalArgumentException(JaiI18N.getString("RasterFactory13")); 
/* 1077 */     if (useAlpha && transparency == 1)
/* 1078 */       throw new IllegalArgumentException(JaiI18N.getString("RasterFactory14")); 
/* 1082 */     if (!useAlpha) {
/* 1083 */       premultiplied = false;
/* 1084 */       transparency = 1;
/*      */     } 
/* 1087 */     int bands = colorSpace.getNumComponents();
/* 1088 */     if (useAlpha)
/* 1089 */       bands++; 
/* 1092 */     int dataTypeSize = DataBuffer.getDataTypeSize(dataType);
/* 1093 */     int[] bits = new int[bands];
/* 1094 */     for (int i = 0; i < bands; i++)
/* 1095 */       bits[i] = dataTypeSize; 
/* 1098 */     switch (dataType) {
/*      */       case 0:
/* 1100 */         return new ComponentColorModel(colorSpace, bits, useAlpha, premultiplied, transparency, dataType);
/*      */       case 1:
/* 1107 */         return new ComponentColorModel(colorSpace, bits, useAlpha, premultiplied, transparency, dataType);
/*      */       case 3:
/* 1120 */         return new ComponentColorModel(colorSpace, bits, useAlpha, premultiplied, transparency, dataType);
/*      */       case 4:
/* 1127 */         return new FloatDoubleColorModel(colorSpace, useAlpha, premultiplied, transparency, dataType);
/*      */       case 5:
/* 1133 */         return new FloatDoubleColorModel(colorSpace, useAlpha, premultiplied, transparency, dataType);
/*      */     } 
/* 1139 */     throw new IllegalArgumentException(JaiI18N.getString("RasterFactory8"));
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\RasterFactory.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */