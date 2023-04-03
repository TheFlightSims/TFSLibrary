/*      */ package com.sun.media.jai.util;
/*      */ 
/*      */ import java.awt.Point;
/*      */ import java.awt.Rectangle;
/*      */ import java.awt.RenderingHints;
/*      */ import java.awt.image.ColorModel;
/*      */ import java.awt.image.ComponentSampleModel;
/*      */ import java.awt.image.DataBuffer;
/*      */ import java.awt.image.DataBufferByte;
/*      */ import java.awt.image.DataBufferInt;
/*      */ import java.awt.image.DataBufferShort;
/*      */ import java.awt.image.DataBufferUShort;
/*      */ import java.awt.image.MultiPixelPackedSampleModel;
/*      */ import java.awt.image.Raster;
/*      */ import java.awt.image.SampleModel;
/*      */ import java.awt.image.SinglePixelPackedSampleModel;
/*      */ import java.awt.image.WritableRaster;
/*      */ import java.awt.image.renderable.ParameterBlock;
/*      */ import java.awt.image.renderable.RenderContext;
/*      */ import java.io.ByteArrayOutputStream;
/*      */ import java.io.PrintStream;
/*      */ import java.lang.reflect.Method;
/*      */ import java.math.BigInteger;
/*      */ import java.util.Arrays;
/*      */ import java.util.Map;
/*      */ import java.util.Vector;
/*      */ import javax.media.jai.DeferredData;
/*      */ import javax.media.jai.JAI;
/*      */ import javax.media.jai.KernelJAI;
/*      */ import javax.media.jai.PixelAccessor;
/*      */ import javax.media.jai.PlanarImage;
/*      */ import javax.media.jai.RasterAccessor;
/*      */ import javax.media.jai.UnpackedImageData;
/*      */ import javax.media.jai.util.ImagingException;
/*      */ import javax.media.jai.util.ImagingListener;
/*      */ 
/*      */ public final class ImageUtil {
/*      */   private static final float FLOAT_MIN = -3.4028235E38F;
/*      */   
/*      */   private static long counter;
/*      */   
/*      */   public static final int BYTE_MASK = 255;
/*      */   
/*      */   public static final int USHORT_MASK = 65535;
/*      */   
/*      */   public static final byte clampByte(int in) {
/*   66 */     return (in > 255) ? -1 : ((in >= 0) ? (byte)in : 0);
/*      */   }
/*      */   
/*      */   public static final short clampUShort(int in) {
/*   71 */     return (in > 65535) ? -1 : ((in >= 0) ? (short)in : 0);
/*      */   }
/*      */   
/*      */   public static final short clampShort(int in) {
/*   76 */     return (in > 32767) ? Short.MAX_VALUE : ((in >= -32768) ? (short)in : Short.MIN_VALUE);
/*      */   }
/*      */   
/*      */   public static final int clampInt(long in) {
/*   82 */     return (in > 2147483647L) ? Integer.MAX_VALUE : ((in >= -2147483648L) ? (int)in : Integer.MIN_VALUE);
/*      */   }
/*      */   
/*      */   public static final float clampFloat(double in) {
/*   88 */     return (in > 3.4028234663852886E38D) ? Float.MAX_VALUE : ((in >= -3.4028234663852886E38D) ? (float)in : -3.4028235E38F);
/*      */   }
/*      */   
/*      */   public static final byte clampRoundByte(float in) {
/*   97 */     return (in > 255.0F) ? -1 : ((in >= 0.0F) ? (byte)(int)(in + 0.5F) : 0);
/*      */   }
/*      */   
/*      */   public static final byte clampRoundByte(double in) {
/*  106 */     return (in > 255.0D) ? -1 : ((in >= 0.0D) ? (byte)(int)(in + 0.5D) : 0);
/*      */   }
/*      */   
/*      */   public static final short clampRoundUShort(float in) {
/*  114 */     return (in > 65535.0F) ? -1 : ((in >= 0.0F) ? (short)(int)(in + 0.5F) : 0);
/*      */   }
/*      */   
/*      */   public static final short clampRoundUShort(double in) {
/*  123 */     return (in > 65535.0D) ? -1 : ((in >= 0.0D) ? (short)(int)(in + 0.5D) : 0);
/*      */   }
/*      */   
/*      */   public static final short clampRoundShort(float in) {
/*  132 */     return (in > 32767.0F) ? Short.MAX_VALUE : ((in >= -32768.0F) ? (short)(int)Math.floor((in + 0.5F)) : Short.MIN_VALUE);
/*      */   }
/*      */   
/*      */   public static final short clampRoundShort(double in) {
/*  142 */     return (in > 32767.0D) ? Short.MAX_VALUE : ((in >= -32768.0D) ? (short)(int)Math.floor(in + 0.5D) : Short.MIN_VALUE);
/*      */   }
/*      */   
/*      */   public static final int clampRoundInt(float in) {
/*  152 */     return (in > 2.1474836E9F) ? Integer.MAX_VALUE : ((in >= -2.1474836E9F) ? (int)Math.floor((in + 0.5F)) : Integer.MIN_VALUE);
/*      */   }
/*      */   
/*      */   public static final int clampRoundInt(double in) {
/*  162 */     return (in > 2.147483647E9D) ? Integer.MAX_VALUE : ((in >= -2.147483648E9D) ? (int)Math.floor(in + 0.5D) : Integer.MIN_VALUE);
/*      */   }
/*      */   
/*      */   public static final byte clampBytePositive(int in) {
/*  169 */     return (in > 255) ? -1 : (byte)in;
/*      */   }
/*      */   
/*      */   public static final byte clampByteNegative(int in) {
/*  174 */     return (in < 0) ? 0 : (byte)in;
/*      */   }
/*      */   
/*      */   public static final short clampUShortPositive(int in) {
/*  182 */     return (in > 65535) ? -1 : (short)in;
/*      */   }
/*      */   
/*      */   public static final short clampUShortNegative(int in) {
/*  190 */     return (in < 0) ? 0 : (short)in;
/*      */   }
/*      */   
/*      */   public static final void copyRaster(RasterAccessor src, RasterAccessor dst) {
/*      */     byte[][] bSrcData, bDstData;
/*      */     int b;
/*      */     short[][] sSrcData, sDstData;
/*      */     int i, iSrcData[][], iDstData[][], j;
/*      */     float[][] fSrcData, fDstData;
/*      */     int k;
/*      */     double[][] dSrcData, dDstData;
/*  195 */     int m, srcPixelStride = src.getPixelStride();
/*  196 */     int srcLineStride = src.getScanlineStride();
/*  197 */     int[] srcBandOffsets = src.getBandOffsets();
/*  199 */     int dstPixelStride = dst.getPixelStride();
/*  200 */     int dstLineStride = dst.getScanlineStride();
/*  201 */     int[] dstBandOffsets = dst.getBandOffsets();
/*  203 */     int width = dst.getWidth() * dstPixelStride;
/*  204 */     int height = dst.getHeight() * dstLineStride;
/*  205 */     int bands = dst.getNumBands();
/*  207 */     switch (dst.getDataType()) {
/*      */       case 0:
/*  209 */         bSrcData = src.getByteDataArrays();
/*  210 */         bDstData = dst.getByteDataArrays();
/*  212 */         for (b = 0; b < bands; b++) {
/*  213 */           byte[] s = bSrcData[b];
/*  214 */           byte[] d = bDstData[b];
/*  216 */           int heightEnd = dstBandOffsets[b] + height;
/*  218 */           int dstLineOffset = dstBandOffsets[b];
/*  219 */           int srcLineOffset = srcBandOffsets[b];
/*  220 */           for (; dstLineOffset < heightEnd; 
/*  221 */             dstLineOffset += dstLineStride, 
/*  222 */             srcLineOffset += srcLineStride) {
/*  224 */             int widthEnd = dstLineOffset + width;
/*  226 */             int dstPixelOffset = dstLineOffset;
/*  227 */             int srcPixelOffset = srcLineOffset;
/*  228 */             for (; dstPixelOffset < widthEnd; 
/*  229 */               dstPixelOffset += dstPixelStride, 
/*  230 */               srcPixelOffset += srcPixelStride)
/*  232 */               d[dstPixelOffset] = s[srcPixelOffset]; 
/*      */           } 
/*      */         } 
/*      */         break;
/*      */       case 1:
/*      */       case 2:
/*  240 */         sSrcData = src.getShortDataArrays();
/*  241 */         sDstData = dst.getShortDataArrays();
/*  243 */         for (i = 0; i < bands; i++) {
/*  244 */           short[] s = sSrcData[i];
/*  245 */           short[] d = sDstData[i];
/*  247 */           int heightEnd = dstBandOffsets[i] + height;
/*  249 */           int dstLineOffset = dstBandOffsets[i];
/*  250 */           int srcLineOffset = srcBandOffsets[i];
/*  251 */           for (; dstLineOffset < heightEnd; 
/*  252 */             dstLineOffset += dstLineStride, 
/*  253 */             srcLineOffset += srcLineStride) {
/*  255 */             int widthEnd = dstLineOffset + width;
/*  257 */             int dstPixelOffset = dstLineOffset;
/*  258 */             int srcPixelOffset = srcLineOffset;
/*  259 */             for (; dstPixelOffset < widthEnd; 
/*  260 */               dstPixelOffset += dstPixelStride, 
/*  261 */               srcPixelOffset += srcPixelStride)
/*  263 */               d[dstPixelOffset] = s[srcPixelOffset]; 
/*      */           } 
/*      */         } 
/*      */         break;
/*      */       case 3:
/*  270 */         iSrcData = src.getIntDataArrays();
/*  271 */         iDstData = dst.getIntDataArrays();
/*  273 */         for (j = 0; j < bands; j++) {
/*  274 */           int[] s = iSrcData[j];
/*  275 */           int[] d = iDstData[j];
/*  277 */           int heightEnd = dstBandOffsets[j] + height;
/*  279 */           int dstLineOffset = dstBandOffsets[j];
/*  280 */           int srcLineOffset = srcBandOffsets[j];
/*  281 */           for (; dstLineOffset < heightEnd; 
/*  282 */             dstLineOffset += dstLineStride, 
/*  283 */             srcLineOffset += srcLineStride) {
/*  285 */             int widthEnd = dstLineOffset + width;
/*  287 */             int dstPixelOffset = dstLineOffset;
/*  288 */             int srcPixelOffset = srcLineOffset;
/*  289 */             for (; dstPixelOffset < widthEnd; 
/*  290 */               dstPixelOffset += dstPixelStride, 
/*  291 */               srcPixelOffset += srcPixelStride)
/*  293 */               d[dstPixelOffset] = s[srcPixelOffset]; 
/*      */           } 
/*      */         } 
/*      */         break;
/*      */       case 4:
/*  300 */         fSrcData = src.getFloatDataArrays();
/*  301 */         fDstData = dst.getFloatDataArrays();
/*  303 */         for (k = 0; k < bands; k++) {
/*  304 */           float[] s = fSrcData[k];
/*  305 */           float[] d = fDstData[k];
/*  307 */           int heightEnd = dstBandOffsets[k] + height;
/*  309 */           int dstLineOffset = dstBandOffsets[k];
/*  310 */           int srcLineOffset = srcBandOffsets[k];
/*  311 */           for (; dstLineOffset < heightEnd; 
/*  312 */             dstLineOffset += dstLineStride, 
/*  313 */             srcLineOffset += srcLineStride) {
/*  315 */             int widthEnd = dstLineOffset + width;
/*  317 */             int dstPixelOffset = dstLineOffset;
/*  318 */             int srcPixelOffset = srcLineOffset;
/*  319 */             for (; dstPixelOffset < widthEnd; 
/*  320 */               dstPixelOffset += dstPixelStride, 
/*  321 */               srcPixelOffset += srcPixelStride)
/*  323 */               d[dstPixelOffset] = s[srcPixelOffset]; 
/*      */           } 
/*      */         } 
/*      */         break;
/*      */       case 5:
/*  330 */         dSrcData = src.getDoubleDataArrays();
/*  331 */         dDstData = dst.getDoubleDataArrays();
/*  333 */         for (m = 0; m < bands; m++) {
/*  334 */           double[] s = dSrcData[m];
/*  335 */           double[] d = dDstData[m];
/*  337 */           int heightEnd = dstBandOffsets[m] + height;
/*  339 */           int dstLineOffset = dstBandOffsets[m];
/*  340 */           int srcLineOffset = srcBandOffsets[m];
/*  341 */           for (; dstLineOffset < heightEnd; 
/*  342 */             dstLineOffset += dstLineStride, 
/*  343 */             srcLineOffset += srcLineStride) {
/*  345 */             int widthEnd = dstLineOffset + width;
/*  347 */             int dstPixelOffset = dstLineOffset;
/*  348 */             int srcPixelOffset = srcLineOffset;
/*  349 */             for (; dstPixelOffset < widthEnd; 
/*  350 */               dstPixelOffset += dstPixelStride, 
/*  351 */               srcPixelOffset += srcPixelStride)
/*  353 */               d[dstPixelOffset] = s[srcPixelOffset]; 
/*      */           } 
/*      */         } 
/*      */         break;
/*      */     } 
/*  360 */     if (dst.isDataCopy()) {
/*  361 */       dst.clampDataArrays();
/*  362 */       dst.copyDataToRaster();
/*      */     } 
/*      */   }
/*      */   
/*      */   public boolean areEqualSampleModels(SampleModel sm1, SampleModel sm2) {
/*  373 */     if (sm1 == sm2)
/*  375 */       return true; 
/*  376 */     if (sm1.getClass() == sm2.getClass() && sm1.getDataType() == sm2.getDataType() && sm1.getTransferType() == sm2.getTransferType() && sm1.getWidth() == sm2.getWidth() && sm1.getHeight() == sm2.getHeight()) {
/*  384 */       if (sm1 instanceof ComponentSampleModel) {
/*  385 */         ComponentSampleModel csm1 = (ComponentSampleModel)sm1;
/*  386 */         ComponentSampleModel csm2 = (ComponentSampleModel)sm2;
/*  387 */         return (csm1.getPixelStride() == csm2.getPixelStride() && csm1.getScanlineStride() == csm2.getScanlineStride() && Arrays.equals(csm1.getBankIndices(), csm2.getBankIndices()) && Arrays.equals(csm1.getBandOffsets(), csm2.getBandOffsets()));
/*      */       } 
/*  393 */       if (sm1 instanceof MultiPixelPackedSampleModel) {
/*  394 */         MultiPixelPackedSampleModel mpp1 = (MultiPixelPackedSampleModel)sm1;
/*  396 */         MultiPixelPackedSampleModel mpp2 = (MultiPixelPackedSampleModel)sm2;
/*  398 */         return (mpp1.getPixelBitStride() == mpp2.getPixelBitStride() && mpp1.getScanlineStride() == mpp2.getScanlineStride() && mpp1.getDataBitOffset() == mpp2.getDataBitOffset());
/*      */       } 
/*  401 */       if (sm1 instanceof SinglePixelPackedSampleModel) {
/*  402 */         SinglePixelPackedSampleModel spp1 = (SinglePixelPackedSampleModel)sm1;
/*  404 */         SinglePixelPackedSampleModel spp2 = (SinglePixelPackedSampleModel)sm2;
/*  406 */         return (spp1.getScanlineStride() == spp2.getScanlineStride() && Arrays.equals(spp1.getBitMasks(), spp2.getBitMasks()));
/*      */       } 
/*      */     } 
/*  411 */     return false;
/*      */   }
/*      */   
/*      */   public static boolean isBinary(SampleModel sm) {
/*  422 */     return (sm instanceof MultiPixelPackedSampleModel && ((MultiPixelPackedSampleModel)sm).getPixelBitStride() == 1 && sm.getNumBands() == 1);
/*      */   }
/*      */   
/*      */   public static byte[] getPackedBinaryData(Raster raster, Rectangle rect) {
/*  444 */     SampleModel sm = raster.getSampleModel();
/*  445 */     if (!isBinary(sm))
/*  446 */       throw new IllegalArgumentException(JaiI18N.getString("ImageUtil0")); 
/*  449 */     int rectX = rect.x;
/*  450 */     int rectY = rect.y;
/*  451 */     int rectWidth = rect.width;
/*  452 */     int rectHeight = rect.height;
/*  454 */     DataBuffer dataBuffer = raster.getDataBuffer();
/*  456 */     int dx = rectX - raster.getSampleModelTranslateX();
/*  457 */     int dy = rectY - raster.getSampleModelTranslateY();
/*  459 */     MultiPixelPackedSampleModel mpp = (MultiPixelPackedSampleModel)sm;
/*  460 */     int lineStride = mpp.getScanlineStride();
/*  461 */     int eltOffset = dataBuffer.getOffset() + mpp.getOffset(dx, dy);
/*  462 */     int bitOffset = mpp.getBitOffset(dx);
/*  464 */     int numBytesPerRow = (rectWidth + 7) / 8;
/*  465 */     if (dataBuffer instanceof DataBufferByte && eltOffset == 0 && bitOffset == 0 && numBytesPerRow == lineStride && (((DataBufferByte)dataBuffer).getData()).length == numBytesPerRow * rectHeight)
/*  470 */       return ((DataBufferByte)dataBuffer).getData(); 
/*  473 */     byte[] binaryDataArray = new byte[numBytesPerRow * rectHeight];
/*  475 */     int b = 0;
/*  477 */     if (bitOffset == 0) {
/*  478 */       if (dataBuffer instanceof DataBufferByte) {
/*  479 */         byte[] data = ((DataBufferByte)dataBuffer).getData();
/*  480 */         int stride = numBytesPerRow;
/*  481 */         int offset = 0;
/*  482 */         for (int y = 0; y < rectHeight; y++) {
/*  483 */           System.arraycopy(data, eltOffset, binaryDataArray, offset, stride);
/*  486 */           offset += stride;
/*  487 */           eltOffset += lineStride;
/*      */         } 
/*  489 */       } else if (dataBuffer instanceof DataBufferShort || dataBuffer instanceof DataBufferUShort) {
/*  491 */         short[] data = (dataBuffer instanceof DataBufferShort) ? ((DataBufferShort)dataBuffer).getData() : ((DataBufferUShort)dataBuffer).getData();
/*  495 */         for (int y = 0; y < rectHeight; y++) {
/*  496 */           int xRemaining = rectWidth;
/*  497 */           int i = eltOffset;
/*  498 */           while (xRemaining > 8) {
/*  499 */             short datum = data[i++];
/*  500 */             binaryDataArray[b++] = (byte)(datum >>> 8 & 0xFF);
/*  501 */             binaryDataArray[b++] = (byte)(datum & 0xFF);
/*  502 */             xRemaining -= 16;
/*      */           } 
/*  504 */           if (xRemaining > 0)
/*  505 */             binaryDataArray[b++] = (byte)(data[i] >>> 8 & 0xFF); 
/*  507 */           eltOffset += lineStride;
/*      */         } 
/*  509 */       } else if (dataBuffer instanceof DataBufferInt) {
/*  510 */         int[] data = ((DataBufferInt)dataBuffer).getData();
/*  512 */         for (int y = 0; y < rectHeight; y++) {
/*  513 */           int xRemaining = rectWidth;
/*  514 */           int i = eltOffset;
/*  515 */           while (xRemaining > 24) {
/*  516 */             int datum = data[i++];
/*  517 */             binaryDataArray[b++] = (byte)(datum >>> 24 & 0xFF);
/*  518 */             binaryDataArray[b++] = (byte)(datum >>> 16 & 0xFF);
/*  519 */             binaryDataArray[b++] = (byte)(datum >>> 8 & 0xFF);
/*  520 */             binaryDataArray[b++] = (byte)(datum & 0xFF);
/*  521 */             xRemaining -= 32;
/*      */           } 
/*  523 */           int shift = 24;
/*  524 */           while (xRemaining > 0) {
/*  525 */             binaryDataArray[b++] = (byte)(data[i] >>> shift & 0xFF);
/*  527 */             shift -= 8;
/*  528 */             xRemaining -= 8;
/*      */           } 
/*  530 */           eltOffset += lineStride;
/*      */         } 
/*      */       } 
/*  534 */     } else if (dataBuffer instanceof DataBufferByte) {
/*  535 */       byte[] data = ((DataBufferByte)dataBuffer).getData();
/*  537 */       if ((bitOffset & 0x7) == 0) {
/*  538 */         int stride = numBytesPerRow;
/*  539 */         int offset = 0;
/*  540 */         for (int y = 0; y < rectHeight; y++) {
/*  541 */           System.arraycopy(data, eltOffset, binaryDataArray, offset, stride);
/*  544 */           offset += stride;
/*  545 */           eltOffset += lineStride;
/*      */         } 
/*      */       } else {
/*  548 */         int leftShift = bitOffset & 0x7;
/*  549 */         int rightShift = 8 - leftShift;
/*  550 */         for (int y = 0; y < rectHeight; y++) {
/*  551 */           int i = eltOffset;
/*  552 */           int xRemaining = rectWidth;
/*  553 */           while (xRemaining > 0) {
/*  554 */             if (xRemaining > rightShift) {
/*  555 */               binaryDataArray[b++] = (byte)((data[i++] & 0xFF) << leftShift | (data[i] & 0xFF) >>> rightShift);
/*      */             } else {
/*  559 */               binaryDataArray[b++] = (byte)((data[i] & 0xFF) << leftShift);
/*      */             } 
/*  562 */             xRemaining -= 8;
/*      */           } 
/*  564 */           eltOffset += lineStride;
/*      */         } 
/*      */       } 
/*  567 */     } else if (dataBuffer instanceof DataBufferShort || dataBuffer instanceof DataBufferUShort) {
/*  569 */       short[] data = (dataBuffer instanceof DataBufferShort) ? ((DataBufferShort)dataBuffer).getData() : ((DataBufferUShort)dataBuffer).getData();
/*  573 */       for (int y = 0; y < rectHeight; y++) {
/*  574 */         int bOffset = bitOffset;
/*  575 */         for (int x = 0; x < rectWidth; x += 8, bOffset += 8) {
/*  576 */           int i = eltOffset + bOffset / 16;
/*  577 */           int mod = bOffset % 16;
/*  578 */           int left = data[i] & 0xFFFF;
/*  579 */           if (mod <= 8) {
/*  580 */             binaryDataArray[b++] = (byte)(left >>> 8 - mod);
/*      */           } else {
/*  582 */             int delta = mod - 8;
/*  583 */             int right = data[i + 1] & 0xFFFF;
/*  584 */             binaryDataArray[b++] = (byte)(left << delta | right >>> 16 - delta);
/*      */           } 
/*      */         } 
/*  589 */         eltOffset += lineStride;
/*      */       } 
/*  591 */     } else if (dataBuffer instanceof DataBufferInt) {
/*  592 */       int[] data = ((DataBufferInt)dataBuffer).getData();
/*  594 */       for (int y = 0; y < rectHeight; y++) {
/*  595 */         int bOffset = bitOffset;
/*  596 */         for (int x = 0; x < rectWidth; x += 8, bOffset += 8) {
/*  597 */           int i = eltOffset + bOffset / 32;
/*  598 */           int mod = bOffset % 32;
/*  599 */           int left = data[i];
/*  600 */           if (mod <= 24) {
/*  601 */             binaryDataArray[b++] = (byte)(left >>> 24 - mod);
/*      */           } else {
/*  604 */             int delta = mod - 24;
/*  605 */             int right = data[i + 1];
/*  606 */             binaryDataArray[b++] = (byte)(left << delta | right >>> 32 - delta);
/*      */           } 
/*      */         } 
/*  611 */         eltOffset += lineStride;
/*      */       } 
/*      */     } 
/*  616 */     return binaryDataArray;
/*      */   }
/*      */   
/*      */   public static byte[] getUnpackedBinaryData(Raster raster, Rectangle rect) {
/*  629 */     SampleModel sm = raster.getSampleModel();
/*  630 */     if (!isBinary(sm))
/*  631 */       throw new IllegalArgumentException(JaiI18N.getString("ImageUtil0")); 
/*  634 */     int rectX = rect.x;
/*  635 */     int rectY = rect.y;
/*  636 */     int rectWidth = rect.width;
/*  637 */     int rectHeight = rect.height;
/*  639 */     DataBuffer dataBuffer = raster.getDataBuffer();
/*  641 */     int dx = rectX - raster.getSampleModelTranslateX();
/*  642 */     int dy = rectY - raster.getSampleModelTranslateY();
/*  644 */     MultiPixelPackedSampleModel mpp = (MultiPixelPackedSampleModel)sm;
/*  645 */     int lineStride = mpp.getScanlineStride();
/*  646 */     int eltOffset = dataBuffer.getOffset() + mpp.getOffset(dx, dy);
/*  647 */     int bitOffset = mpp.getBitOffset(dx);
/*  649 */     byte[] bdata = new byte[rectWidth * rectHeight];
/*  650 */     int maxY = rectY + rectHeight;
/*  651 */     int maxX = rectX + rectWidth;
/*  652 */     int k = 0;
/*  654 */     if (dataBuffer instanceof DataBufferByte) {
/*  655 */       byte[] data = ((DataBufferByte)dataBuffer).getData();
/*  656 */       for (int y = rectY; y < maxY; y++) {
/*  657 */         int bOffset = eltOffset * 8 + bitOffset;
/*  658 */         for (int x = rectX; x < maxX; x++) {
/*  659 */           byte b = data[bOffset / 8];
/*  660 */           bdata[k++] = (byte)(b >>> (7 - bOffset & 0x7) & 0x1);
/*  662 */           bOffset++;
/*      */         } 
/*  664 */         eltOffset += lineStride;
/*      */       } 
/*  666 */     } else if (dataBuffer instanceof DataBufferShort || dataBuffer instanceof DataBufferUShort) {
/*  668 */       short[] data = (dataBuffer instanceof DataBufferShort) ? ((DataBufferShort)dataBuffer).getData() : ((DataBufferUShort)dataBuffer).getData();
/*  671 */       for (int y = rectY; y < maxY; y++) {
/*  672 */         int bOffset = eltOffset * 16 + bitOffset;
/*  673 */         for (int x = rectX; x < maxX; x++) {
/*  674 */           short s = data[bOffset / 16];
/*  675 */           bdata[k++] = (byte)(s >>> 15 - bOffset % 16 & 0x1);
/*  678 */           bOffset++;
/*      */         } 
/*  680 */         eltOffset += lineStride;
/*      */       } 
/*  682 */     } else if (dataBuffer instanceof DataBufferInt) {
/*  683 */       int[] data = ((DataBufferInt)dataBuffer).getData();
/*  684 */       for (int y = rectY; y < maxY; y++) {
/*  685 */         int bOffset = eltOffset * 32 + bitOffset;
/*  686 */         for (int x = rectX; x < maxX; x++) {
/*  687 */           int i = data[bOffset / 32];
/*  688 */           bdata[k++] = (byte)(i >>> 31 - bOffset % 32 & 0x1);
/*  691 */           bOffset++;
/*      */         } 
/*  693 */         eltOffset += lineStride;
/*      */       } 
/*      */     } 
/*  697 */     return bdata;
/*      */   }
/*      */   
/*      */   public static void setPackedBinaryData(byte[] binaryDataArray, WritableRaster raster, Rectangle rect) {
/*  712 */     SampleModel sm = raster.getSampleModel();
/*  713 */     if (!isBinary(sm))
/*  714 */       throw new IllegalArgumentException(JaiI18N.getString("ImageUtil0")); 
/*  717 */     int rectX = rect.x;
/*  718 */     int rectY = rect.y;
/*  719 */     int rectWidth = rect.width;
/*  720 */     int rectHeight = rect.height;
/*  722 */     DataBuffer dataBuffer = raster.getDataBuffer();
/*  724 */     int dx = rectX - raster.getSampleModelTranslateX();
/*  725 */     int dy = rectY - raster.getSampleModelTranslateY();
/*  727 */     MultiPixelPackedSampleModel mpp = (MultiPixelPackedSampleModel)sm;
/*  728 */     int lineStride = mpp.getScanlineStride();
/*  729 */     int eltOffset = dataBuffer.getOffset() + mpp.getOffset(dx, dy);
/*  730 */     int bitOffset = mpp.getBitOffset(dx);
/*  732 */     int b = 0;
/*  734 */     if (bitOffset == 0) {
/*  735 */       if (dataBuffer instanceof DataBufferByte) {
/*  736 */         byte[] data = ((DataBufferByte)dataBuffer).getData();
/*  737 */         if (data == binaryDataArray)
/*      */           return; 
/*  741 */         int stride = (rectWidth + 7) / 8;
/*  742 */         int offset = 0;
/*  743 */         for (int y = 0; y < rectHeight; y++) {
/*  744 */           System.arraycopy(binaryDataArray, offset, data, eltOffset, stride);
/*  747 */           offset += stride;
/*  748 */           eltOffset += lineStride;
/*      */         } 
/*  750 */       } else if (dataBuffer instanceof DataBufferShort || dataBuffer instanceof DataBufferUShort) {
/*  752 */         short[] data = (dataBuffer instanceof DataBufferShort) ? ((DataBufferShort)dataBuffer).getData() : ((DataBufferUShort)dataBuffer).getData();
/*  756 */         for (int y = 0; y < rectHeight; y++) {
/*  757 */           int xRemaining = rectWidth;
/*  758 */           int i = eltOffset;
/*  759 */           while (xRemaining > 8) {
/*  760 */             data[i++] = (short)((binaryDataArray[b++] & 0xFF) << 8 | binaryDataArray[b++] & 0xFF);
/*  763 */             xRemaining -= 16;
/*      */           } 
/*  765 */           if (xRemaining > 0)
/*  766 */             data[i++] = (short)((binaryDataArray[b++] & 0xFF) << 8); 
/*  769 */           eltOffset += lineStride;
/*      */         } 
/*  771 */       } else if (dataBuffer instanceof DataBufferInt) {
/*  772 */         int[] data = ((DataBufferInt)dataBuffer).getData();
/*  774 */         for (int y = 0; y < rectHeight; y++) {
/*  775 */           int xRemaining = rectWidth;
/*  776 */           int i = eltOffset;
/*  777 */           while (xRemaining > 24) {
/*  778 */             data[i++] = (binaryDataArray[b++] & 0xFF) << 24 | (binaryDataArray[b++] & 0xFF) << 16 | (binaryDataArray[b++] & 0xFF) << 8 | binaryDataArray[b++] & 0xFF;
/*  783 */             xRemaining -= 32;
/*      */           } 
/*  785 */           int shift = 24;
/*  786 */           while (xRemaining > 0) {
/*  787 */             data[i] = data[i] | (binaryDataArray[b++] & 0xFF) << shift;
/*  789 */             shift -= 8;
/*  790 */             xRemaining -= 8;
/*      */           } 
/*  792 */           eltOffset += lineStride;
/*      */         } 
/*      */       } 
/*      */     } else {
/*  796 */       int stride = (rectWidth + 7) / 8;
/*  797 */       int offset = 0;
/*  798 */       if (dataBuffer instanceof DataBufferByte) {
/*  799 */         byte[] data = ((DataBufferByte)dataBuffer).getData();
/*  801 */         if ((bitOffset & 0x7) == 0) {
/*  802 */           for (int y = 0; y < rectHeight; y++) {
/*  803 */             System.arraycopy(binaryDataArray, offset, data, eltOffset, stride);
/*  806 */             offset += stride;
/*  807 */             eltOffset += lineStride;
/*      */           } 
/*      */         } else {
/*  810 */           int rightShift = bitOffset & 0x7;
/*  811 */           int leftShift = 8 - rightShift;
/*  812 */           int leftShift8 = 8 + leftShift;
/*  813 */           int mask = (byte)(255 << leftShift);
/*  814 */           int mask1 = (byte)(mask ^ 0xFFFFFFFF);
/*  816 */           for (int y = 0; y < rectHeight; y++) {
/*  817 */             int i = eltOffset;
/*  818 */             int xRemaining = rectWidth;
/*  819 */             while (xRemaining > 0) {
/*  820 */               byte datum = binaryDataArray[b++];
/*  822 */               if (xRemaining > leftShift8) {
/*  825 */                 data[i] = (byte)(data[i] & mask | (datum & 0xFF) >>> rightShift);
/*  827 */                 data[++i] = (byte)((datum & 0xFF) << leftShift);
/*  828 */               } else if (xRemaining > leftShift) {
/*  832 */                 data[i] = (byte)(data[i] & mask | (datum & 0xFF) >>> rightShift);
/*  834 */                 i++;
/*  835 */                 data[i] = (byte)(data[i] & mask1 | (datum & 0xFF) << leftShift);
/*      */               } else {
/*  840 */                 int remainMask = (1 << leftShift - xRemaining) - 1;
/*  841 */                 data[i] = (byte)(data[i] & (mask | remainMask) | (datum & 0xFF) >>> rightShift & (remainMask ^ 0xFFFFFFFF));
/*      */               } 
/*  845 */               xRemaining -= 8;
/*      */             } 
/*  847 */             eltOffset += lineStride;
/*      */           } 
/*      */         } 
/*  850 */       } else if (dataBuffer instanceof DataBufferShort || dataBuffer instanceof DataBufferUShort) {
/*  852 */         short[] data = (dataBuffer instanceof DataBufferShort) ? ((DataBufferShort)dataBuffer).getData() : ((DataBufferUShort)dataBuffer).getData();
/*  856 */         int rightShift = bitOffset & 0x7;
/*  857 */         int leftShift = 8 - rightShift;
/*  858 */         int leftShift16 = 16 + leftShift;
/*  859 */         int mask = (short)(255 << leftShift ^ 0xFFFFFFFF);
/*  860 */         int mask1 = (short)(65535 << leftShift);
/*  861 */         int mask2 = (short)(mask1 ^ 0xFFFFFFFF);
/*  863 */         for (int y = 0; y < rectHeight; y++) {
/*  864 */           int bOffset = bitOffset;
/*  865 */           int xRemaining = rectWidth;
/*  866 */           for (int x = 0; x < rectWidth; 
/*  867 */             x += 8, bOffset += 8, xRemaining -= 8) {
/*  868 */             int i = eltOffset + (bOffset >> 4);
/*  869 */             int mod = bOffset & 0xF;
/*  870 */             int datum = binaryDataArray[b++] & 0xFF;
/*  871 */             if (mod <= 8) {
/*  873 */               if (xRemaining < 8)
/*  875 */                 datum &= 255 << 8 - xRemaining; 
/*  877 */               data[i] = (short)(data[i] & mask | datum << leftShift);
/*  878 */             } else if (xRemaining > leftShift16) {
/*  880 */               data[i] = (short)(data[i] & mask1 | datum >>> rightShift & 0xFFFF);
/*  881 */               data[++i] = (short)(datum << leftShift & 0xFFFF);
/*  883 */             } else if (xRemaining > leftShift) {
/*  886 */               data[i] = (short)(data[i] & mask1 | datum >>> rightShift & 0xFFFF);
/*  887 */               i++;
/*  888 */               data[i] = (short)(data[i] & mask2 | datum << leftShift & 0xFFFF);
/*      */             } else {
/*  893 */               int remainMask = (1 << leftShift - xRemaining) - 1;
/*  894 */               data[i] = (short)(data[i] & (mask1 | remainMask) | datum >>> rightShift & 0xFFFF & (remainMask ^ 0xFFFFFFFF));
/*      */             } 
/*      */           } 
/*  898 */           eltOffset += lineStride;
/*      */         } 
/*  900 */       } else if (dataBuffer instanceof DataBufferInt) {
/*  901 */         int[] data = ((DataBufferInt)dataBuffer).getData();
/*  902 */         int rightShift = bitOffset & 0x7;
/*  903 */         int leftShift = 8 - rightShift;
/*  904 */         int leftShift32 = 32 + leftShift;
/*  905 */         int mask = -1 << leftShift;
/*  906 */         int mask1 = mask ^ 0xFFFFFFFF;
/*  908 */         for (int y = 0; y < rectHeight; y++) {
/*  909 */           int bOffset = bitOffset;
/*  910 */           int xRemaining = rectWidth;
/*  911 */           for (int x = 0; x < rectWidth; 
/*  912 */             x += 8, bOffset += 8, xRemaining -= 8) {
/*  913 */             int i = eltOffset + (bOffset >> 5);
/*  914 */             int mod = bOffset & 0x1F;
/*  915 */             int datum = binaryDataArray[b++] & 0xFF;
/*  916 */             if (mod <= 24) {
/*  918 */               int shift = 24 - mod;
/*  919 */               if (xRemaining < 8)
/*  921 */                 datum &= 255 << 8 - xRemaining; 
/*  923 */               data[i] = data[i] & (255 << shift ^ 0xFFFFFFFF) | datum << shift;
/*  924 */             } else if (xRemaining > leftShift32) {
/*  926 */               data[i] = data[i] & mask | datum >>> rightShift;
/*  927 */               data[++i] = datum << leftShift;
/*  928 */             } else if (xRemaining > leftShift) {
/*  931 */               data[i] = data[i] & mask | datum >>> rightShift;
/*  932 */               i++;
/*  933 */               data[i] = data[i] & mask1 | datum << leftShift;
/*      */             } else {
/*  936 */               int remainMask = (1 << leftShift - xRemaining) - 1;
/*  937 */               data[i] = data[i] & (mask | remainMask) | datum >>> rightShift & (remainMask ^ 0xFFFFFFFF);
/*      */             } 
/*      */           } 
/*  941 */           eltOffset += lineStride;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public static void setUnpackedBinaryData(byte[] bdata, WritableRaster raster, Rectangle rect) {
/*  962 */     SampleModel sm = raster.getSampleModel();
/*  963 */     if (!isBinary(sm))
/*  964 */       throw new IllegalArgumentException(JaiI18N.getString("ImageUtil0")); 
/*  967 */     int rectX = rect.x;
/*  968 */     int rectY = rect.y;
/*  969 */     int rectWidth = rect.width;
/*  970 */     int rectHeight = rect.height;
/*  972 */     DataBuffer dataBuffer = raster.getDataBuffer();
/*  974 */     int dx = rectX - raster.getSampleModelTranslateX();
/*  975 */     int dy = rectY - raster.getSampleModelTranslateY();
/*  977 */     MultiPixelPackedSampleModel mpp = (MultiPixelPackedSampleModel)sm;
/*  978 */     int lineStride = mpp.getScanlineStride();
/*  979 */     int eltOffset = dataBuffer.getOffset() + mpp.getOffset(dx, dy);
/*  980 */     int bitOffset = mpp.getBitOffset(dx);
/*  982 */     int k = 0;
/*  984 */     if (dataBuffer instanceof DataBufferByte) {
/*  985 */       byte[] data = ((DataBufferByte)dataBuffer).getData();
/*  986 */       for (int y = 0; y < rectHeight; y++) {
/*  987 */         int bOffset = eltOffset * 8 + bitOffset;
/*  988 */         for (int x = 0; x < rectWidth; x++) {
/*  989 */           if (bdata[k++] != 0)
/*  990 */             data[bOffset / 8] = (byte)(data[bOffset / 8] | (byte)(1 << (7 - bOffset & 0x7))); 
/*  993 */           bOffset++;
/*      */         } 
/*  995 */         eltOffset += lineStride;
/*      */       } 
/*  997 */     } else if (dataBuffer instanceof DataBufferShort || dataBuffer instanceof DataBufferUShort) {
/*  999 */       short[] data = (dataBuffer instanceof DataBufferShort) ? ((DataBufferShort)dataBuffer).getData() : ((DataBufferUShort)dataBuffer).getData();
/* 1002 */       for (int y = 0; y < rectHeight; y++) {
/* 1003 */         int bOffset = eltOffset * 16 + bitOffset;
/* 1004 */         for (int x = 0; x < rectWidth; x++) {
/* 1005 */           if (bdata[k++] != 0)
/* 1006 */             data[bOffset / 16] = (short)(data[bOffset / 16] | (short)(1 << 15 - bOffset % 16)); 
/* 1010 */           bOffset++;
/*      */         } 
/* 1012 */         eltOffset += lineStride;
/*      */       } 
/* 1014 */     } else if (dataBuffer instanceof DataBufferInt) {
/* 1015 */       int[] data = ((DataBufferInt)dataBuffer).getData();
/* 1016 */       for (int y = 0; y < rectHeight; y++) {
/* 1017 */         int bOffset = eltOffset * 32 + bitOffset;
/* 1018 */         for (int x = 0; x < rectWidth; x++) {
/* 1019 */           if (bdata[k++] != 0)
/* 1020 */             data[bOffset / 32] = data[bOffset / 32] | 1 << 31 - bOffset % 32; 
/* 1024 */           bOffset++;
/*      */         } 
/* 1026 */         eltOffset += lineStride;
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public static void fillBackground(WritableRaster raster, Rectangle rect, double[] backgroundValues) {
/* 1038 */     rect = rect.intersection(raster.getBounds());
/* 1039 */     int numBands = raster.getSampleModel().getNumBands();
/* 1040 */     SampleModel sm = raster.getSampleModel();
/* 1041 */     PixelAccessor accessor = new PixelAccessor(sm, null);
/* 1043 */     if (isBinary(sm)) {
/*      */       byte[] arrayOfByte;
/*      */       short[] arrayOfShort;
/*      */       int data[], bits, otherBits;
/*      */       byte b1;
/*      */       short s1;
/*      */       int mask, lineLength, bits1;
/*      */       byte b2;
/*      */       short s2;
/*      */       int mask1, y;
/* 1045 */       byte value = (byte)((int)backgroundValues[0] & 0x1);
/* 1046 */       if (value == 0)
/*      */         return; 
/* 1048 */       int rectX = rect.x;
/* 1049 */       int rectY = rect.y;
/* 1050 */       int rectWidth = rect.width;
/* 1051 */       int rectHeight = rect.height;
/* 1053 */       int dx = rectX - raster.getSampleModelTranslateX();
/* 1054 */       int dy = rectY - raster.getSampleModelTranslateY();
/* 1056 */       DataBuffer dataBuffer = raster.getDataBuffer();
/* 1057 */       MultiPixelPackedSampleModel mpp = (MultiPixelPackedSampleModel)sm;
/* 1058 */       int lineStride = mpp.getScanlineStride();
/* 1059 */       int eltOffset = dataBuffer.getOffset() + mpp.getOffset(dx, dy);
/* 1060 */       int bitOffset = mpp.getBitOffset(dx);
/* 1062 */       switch (sm.getDataType()) {
/*      */         case 0:
/* 1065 */           arrayOfByte = ((DataBufferByte)dataBuffer).getData();
/* 1066 */           bits = bitOffset & 0x7;
/* 1067 */           otherBits = (bits == 0) ? 0 : (8 - bits);
/* 1069 */           b1 = (byte)(255 >> bits);
/* 1070 */           lineLength = (rectWidth - otherBits) / 8;
/* 1071 */           bits1 = rectWidth - otherBits & 0x7;
/* 1072 */           b2 = (byte)(255 << 8 - bits1);
/* 1075 */           if (lineLength == 0) {
/* 1076 */             b1 = (byte)(b1 & b2);
/* 1077 */             bits1 = 0;
/*      */           } 
/* 1080 */           for (y = 0; y < rectHeight; y++) {
/* 1081 */             int start = eltOffset;
/* 1082 */             int end = start + lineLength;
/* 1083 */             if (bits != 0)
/* 1084 */               arrayOfByte[start++] = (byte)(arrayOfByte[start++] | b1); 
/* 1085 */             while (start < end)
/* 1086 */               arrayOfByte[start++] = -1; 
/* 1087 */             if (bits1 != 0)
/* 1088 */               arrayOfByte[start] = (byte)(arrayOfByte[start] | b2); 
/* 1089 */             eltOffset += lineStride;
/*      */           } 
/*      */           break;
/*      */         case 1:
/* 1095 */           arrayOfShort = ((DataBufferUShort)dataBuffer).getData();
/* 1096 */           bits = bitOffset & 0xF;
/* 1097 */           otherBits = (bits == 0) ? 0 : (16 - bits);
/* 1099 */           s1 = (short)(65535 >> bits);
/* 1100 */           lineLength = (rectWidth - otherBits) / 16;
/* 1101 */           bits1 = rectWidth - otherBits & 0xF;
/* 1102 */           s2 = (short)(65535 << 16 - bits1);
/* 1105 */           if (lineLength == 0) {
/* 1106 */             s1 = (short)(s1 & s2);
/* 1107 */             bits1 = 0;
/*      */           } 
/* 1110 */           for (y = 0; y < rectHeight; y++) {
/* 1111 */             int start = eltOffset;
/* 1112 */             int end = start + lineLength;
/* 1113 */             if (bits != 0)
/* 1114 */               arrayOfShort[start++] = (short)(arrayOfShort[start++] | s1); 
/* 1115 */             while (start < end)
/* 1116 */               arrayOfShort[start++] = -1; 
/* 1117 */             if (bits1 != 0)
/* 1118 */               arrayOfShort[start++] = (short)(arrayOfShort[start++] | s2); 
/* 1119 */             eltOffset += lineStride;
/*      */           } 
/*      */           break;
/*      */         case 3:
/* 1125 */           data = ((DataBufferInt)dataBuffer).getData();
/* 1126 */           bits = bitOffset & 0x1F;
/* 1127 */           otherBits = (bits == 0) ? 0 : (32 - bits);
/* 1129 */           mask = -1 >> bits;
/* 1130 */           lineLength = (rectWidth - otherBits) / 32;
/* 1131 */           bits1 = rectWidth - otherBits & 0x1F;
/* 1132 */           mask1 = -1 << 32 - bits1;
/* 1135 */           if (lineLength == 0) {
/* 1136 */             mask &= mask1;
/* 1137 */             bits1 = 0;
/*      */           } 
/* 1140 */           for (y = 0; y < rectHeight; y++) {
/* 1141 */             int start = eltOffset;
/* 1142 */             int end = start + lineLength;
/* 1143 */             if (bits != 0)
/* 1144 */               data[start++] = data[start++] | mask; 
/* 1145 */             while (start < end)
/* 1146 */               data[start++] = -1; 
/* 1147 */             if (bits1 != 0)
/* 1148 */               data[start++] = data[start++] | mask1; 
/* 1149 */             eltOffset += lineStride;
/*      */           } 
/*      */           break;
/*      */       } 
/*      */     } else {
/*      */       byte[][] bdata;
/*      */       int b;
/*      */       short[][] sdata;
/*      */       int i, idata[][], j;
/*      */       float[][] fdata;
/*      */       int k;
/*      */       double[][] ddata;
/* 1156 */       int m, srcSampleType = (accessor.sampleType == -1) ? 0 : accessor.sampleType;
/* 1158 */       UnpackedImageData uid = accessor.getPixels(raster, rect, srcSampleType, false);
/* 1160 */       rect = uid.rect;
/* 1161 */       int lineStride = uid.lineStride;
/* 1162 */       int pixelStride = uid.pixelStride;
/* 1164 */       switch (uid.type) {
/*      */         case 0:
/* 1166 */           bdata = uid.getByteData();
/* 1167 */           for (b = 0; b < accessor.numBands; b++) {
/* 1168 */             byte value = (byte)(int)backgroundValues[b];
/* 1169 */             byte[] bd = bdata[b];
/* 1170 */             int lastLine = uid.bandOffsets[b] + rect.height * lineStride;
/*      */             int lo;
/* 1172 */             for (lo = uid.bandOffsets[b]; lo < lastLine; lo += lineStride) {
/* 1173 */               int lastPixel = lo + rect.width * pixelStride;
/*      */               int po;
/* 1174 */               for (po = lo; po < lastPixel; po += pixelStride)
/* 1175 */                 bd[po] = value; 
/*      */             } 
/*      */           } 
/*      */           break;
/*      */         case 1:
/*      */         case 2:
/* 1182 */           sdata = uid.getShortData();
/* 1183 */           for (i = 0; i < accessor.numBands; i++) {
/* 1184 */             short value = (short)(int)backgroundValues[i];
/* 1185 */             short[] sd = sdata[i];
/* 1186 */             int lastLine = uid.bandOffsets[i] + rect.height * lineStride;
/*      */             int lo;
/* 1188 */             for (lo = uid.bandOffsets[i]; lo < lastLine; lo += lineStride) {
/* 1189 */               int lastPixel = lo + rect.width * pixelStride;
/*      */               int po;
/* 1190 */               for (po = lo; po < lastPixel; po += pixelStride)
/* 1191 */                 sd[po] = value; 
/*      */             } 
/*      */           } 
/*      */           break;
/*      */         case 3:
/* 1197 */           idata = uid.getIntData();
/* 1198 */           for (j = 0; j < accessor.numBands; j++) {
/* 1199 */             int value = (int)backgroundValues[j];
/* 1200 */             int[] id = idata[j];
/* 1201 */             int lastLine = uid.bandOffsets[j] + rect.height * lineStride;
/*      */             int lo;
/* 1203 */             for (lo = uid.bandOffsets[j]; lo < lastLine; lo += lineStride) {
/* 1204 */               int lastPixel = lo + rect.width * pixelStride;
/*      */               int po;
/* 1205 */               for (po = lo; po < lastPixel; po += pixelStride)
/* 1206 */                 id[po] = value; 
/*      */             } 
/*      */           } 
/*      */           break;
/*      */         case 4:
/* 1212 */           fdata = uid.getFloatData();
/* 1213 */           for (k = 0; k < accessor.numBands; k++) {
/* 1214 */             float value = (float)backgroundValues[k];
/* 1215 */             float[] fd = fdata[k];
/* 1216 */             int lastLine = uid.bandOffsets[k] + rect.height * lineStride;
/*      */             int lo;
/* 1218 */             for (lo = uid.bandOffsets[k]; lo < lastLine; lo += lineStride) {
/* 1219 */               int lastPixel = lo + rect.width * pixelStride;
/*      */               int po;
/* 1220 */               for (po = lo; po < lastPixel; po += pixelStride)
/* 1221 */                 fd[po] = value; 
/*      */             } 
/*      */           } 
/*      */           break;
/*      */         case 5:
/* 1227 */           ddata = uid.getDoubleData();
/* 1228 */           for (m = 0; m < accessor.numBands; m++) {
/* 1229 */             double value = backgroundValues[m];
/* 1230 */             double[] dd = ddata[m];
/* 1231 */             int lastLine = uid.bandOffsets[m] + rect.height * lineStride;
/*      */             int lo;
/* 1233 */             for (lo = uid.bandOffsets[m]; lo < lastLine; lo += lineStride) {
/* 1234 */               int lastPixel = lo + rect.width * pixelStride;
/*      */               int po;
/* 1235 */               for (po = lo; po < lastPixel; po += pixelStride)
/* 1236 */                 dd[po] = value; 
/*      */             } 
/*      */           } 
/*      */           break;
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public static void fillBordersWithBackgroundValues(Rectangle outerRect, Rectangle innerRect, WritableRaster raster, double[] backgroundValues) {
/* 1252 */     int outerMaxX = outerRect.x + outerRect.width;
/* 1253 */     int outerMaxY = outerRect.y + outerRect.height;
/* 1255 */     int innerMaxX = innerRect.x + innerRect.width;
/* 1256 */     int innerMaxY = innerRect.y + innerRect.height;
/* 1258 */     if (outerRect.x < innerRect.x) {
/* 1259 */       Rectangle rect = new Rectangle(outerRect.x, innerRect.y, innerRect.x - outerRect.x, outerMaxY - innerRect.y);
/* 1262 */       fillBackground(raster, rect, backgroundValues);
/*      */     } 
/* 1265 */     if (outerRect.y < innerRect.y) {
/* 1266 */       Rectangle rect = new Rectangle(outerRect.x, outerRect.y, innerMaxX - outerRect.x, innerRect.y - outerRect.y);
/* 1269 */       fillBackground(raster, rect, backgroundValues);
/*      */     } 
/* 1272 */     if (outerMaxX > innerMaxX) {
/* 1273 */       Rectangle rect = new Rectangle(innerMaxX, outerRect.y, outerMaxX - innerMaxX, innerMaxY - outerRect.y);
/* 1276 */       fillBackground(raster, rect, backgroundValues);
/*      */     } 
/* 1279 */     if (outerMaxY > innerMaxY) {
/* 1280 */       Rectangle rect = new Rectangle(innerRect.x, innerMaxY, outerMaxX - innerRect.x, outerMaxY - innerMaxY);
/* 1283 */       fillBackground(raster, rect, backgroundValues);
/*      */     } 
/*      */   }
/*      */   
/*      */   public static KernelJAI getUnsharpMaskEquivalentKernel(KernelJAI kernel, float gain) {
/* 1316 */     int width = kernel.getWidth();
/* 1317 */     int height = kernel.getHeight();
/* 1318 */     int xOrigin = kernel.getXOrigin();
/* 1319 */     int yOrigin = kernel.getYOrigin();
/* 1321 */     float[] oldData = kernel.getKernelData();
/* 1322 */     float[] newData = new float[oldData.length];
/*      */     int k;
/* 1326 */     for (k = 0; k < width * height; k++)
/* 1327 */       newData[k] = -gain * oldData[k]; 
/* 1329 */     k = yOrigin * width + xOrigin;
/* 1330 */     newData[k] = 1.0F + gain * (1.0F - oldData[k]);
/* 1332 */     return new KernelJAI(width, height, xOrigin, yOrigin, newData);
/*      */   }
/*      */   
/*      */   public static final Point[] getTileIndices(int txmin, int txmax, int tymin, int tymax) {
/* 1341 */     if (txmin > txmax || tymin > tymax)
/* 1342 */       return null; 
/* 1345 */     Point[] tileIndices = new Point[(txmax - txmin + 1) * (tymax - tymin + 1)];
/* 1347 */     int k = 0;
/* 1348 */     for (int tj = tymin; tj <= tymax; tj++) {
/* 1349 */       for (int ti = txmin; ti <= txmax; ti++)
/* 1350 */         tileIndices[k++] = new Point(ti, tj); 
/*      */     } 
/* 1354 */     return tileIndices;
/*      */   }
/*      */   
/*      */   public static Vector evaluateParameters(Vector parameters) {
/* 1365 */     if (parameters == null)
/* 1366 */       throw new IllegalArgumentException(); 
/* 1369 */     Vector paramEval = parameters;
/* 1371 */     int size = parameters.size();
/* 1372 */     for (int i = 0; i < size; i++) {
/* 1373 */       Object element = parameters.get(i);
/* 1374 */       if (element instanceof DeferredData) {
/* 1375 */         if (paramEval == parameters)
/* 1376 */           paramEval = (Vector)parameters.clone(); 
/* 1378 */         paramEval.set(i, ((DeferredData)element).getData());
/*      */       } 
/*      */     } 
/* 1382 */     return paramEval;
/*      */   }
/*      */   
/*      */   public static ParameterBlock evaluateParameters(ParameterBlock pb) {
/* 1392 */     if (pb == null)
/* 1393 */       throw new IllegalArgumentException(); 
/* 1396 */     Vector parameters = pb.getParameters();
/* 1397 */     Vector paramEval = evaluateParameters(parameters);
/* 1398 */     return (paramEval == parameters) ? pb : new ParameterBlock(pb.getSources(), paramEval);
/*      */   }
/*      */   
/*      */   public static ColorModel getCompatibleColorModel(SampleModel sm, Map config) {
/* 1411 */     ColorModel cm = null;
/* 1413 */     if (config == null || !Boolean.FALSE.equals(config.get(JAI.KEY_DEFAULT_COLOR_MODEL_ENABLED)))
/* 1419 */       if (config != null && config.containsKey(JAI.KEY_DEFAULT_COLOR_MODEL_METHOD)) {
/* 1422 */         Method cmMethod = (Method)config.get(JAI.KEY_DEFAULT_COLOR_MODEL_METHOD);
/* 1426 */         Class[] paramTypes = cmMethod.getParameterTypes();
/* 1427 */         if ((cmMethod.getModifiers() & 0x8) != 8)
/* 1430 */           throw new RuntimeException(JaiI18N.getString("ImageUtil1")); 
/* 1431 */         if (cmMethod.getReturnType() != ColorModel.class)
/* 1433 */           throw new RuntimeException(JaiI18N.getString("ImageUtil2")); 
/* 1434 */         if (paramTypes.length != 1 || !paramTypes[0].equals(SampleModel.class))
/* 1437 */           throw new RuntimeException(JaiI18N.getString("ImageUtil3")); 
/*      */         try {
/* 1443 */           Object[] args = { sm };
/* 1444 */           cm = (ColorModel)cmMethod.invoke(null, args);
/* 1445 */         } catch (Exception e) {
/* 1446 */           String message = JaiI18N.getString("ImageUtil4") + cmMethod.getName();
/* 1448 */           sendExceptionToListener(message, (Exception)new ImagingException(message, e));
/*      */         } 
/*      */       } else {
/* 1458 */         cm = PlanarImage.createColorModel(sm);
/*      */       }  
/* 1462 */     return cm;
/*      */   }
/*      */   
/*      */   public static String getStackTraceString(Exception e) {
/* 1470 */     ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
/* 1471 */     PrintStream printStream = new PrintStream(byteStream);
/* 1472 */     e.printStackTrace(printStream);
/* 1473 */     printStream.flush();
/* 1474 */     String stackTraceString = byteStream.toString();
/* 1475 */     printStream.close();
/* 1476 */     return stackTraceString;
/*      */   }
/*      */   
/*      */   public static ImagingListener getImagingListener(RenderingHints hints) {
/* 1480 */     ImagingListener listener = null;
/* 1481 */     if (hints != null)
/* 1482 */       listener = (ImagingListener)hints.get(JAI.KEY_IMAGING_LISTENER); 
/* 1484 */     if (listener == null)
/* 1485 */       listener = JAI.getDefaultInstance().getImagingListener(); 
/* 1486 */     return listener;
/*      */   }
/*      */   
/*      */   public static ImagingListener getImagingListener(RenderContext context) {
/* 1490 */     return getImagingListener(context.getRenderingHints());
/*      */   }
/*      */   
/*      */   public static synchronized Object generateID(Object owner) {
/* 1502 */     Class c = owner.getClass();
/* 1503 */     counter++;
/* 1505 */     byte[] uid = new byte[32];
/* 1506 */     int k = 0;
/* 1507 */     for (int i = 7, j = 0; i >= 0; i--, j += 8)
/* 1508 */       uid[k++] = (byte)(int)(counter >> j); 
/* 1509 */     int hash = c.hashCode();
/*      */     int m, n;
/* 1510 */     for (m = 3, n = 0; m >= 0; m--, n += 8)
/* 1511 */       uid[k++] = (byte)(hash >> n); 
/* 1512 */     hash = owner.hashCode();
/* 1513 */     for (m = 3, n = 0; m >= 0; m--, n += 8)
/* 1514 */       uid[k++] = (byte)(hash >> n); 
/* 1515 */     long time = System.currentTimeMillis();
/* 1516 */     for (int i1 = 7, i2 = 0; i1 >= 0; i1--, i2 += 8)
/* 1517 */       uid[k++] = (byte)(int)(time >> i2); 
/* 1518 */     long rand = Double.doubleToLongBits((new Double(Math.random())).doubleValue());
/* 1520 */     for (int i3 = 7, i4 = 0; i3 >= 0; i3--, i4 += 8)
/* 1521 */       uid[k++] = (byte)(int)(rand >> i4); 
/* 1522 */     return new BigInteger(uid);
/*      */   }
/*      */   
/*      */   static void sendExceptionToListener(String message, Exception e) {
/* 1526 */     ImagingListener listener = getImagingListener((RenderingHints)null);
/* 1528 */     listener.errorOccurred(message, e, ImageUtil.class, false);
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\ja\\util\ImageUtil.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */