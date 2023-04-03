/*      */ package javax.media.jai;
/*      */ 
/*      */ import java.awt.Point;
/*      */ import java.awt.color.ColorSpace;
/*      */ import java.awt.image.Raster;
/*      */ import java.awt.image.SampleModel;
/*      */ import java.awt.image.WritableRaster;
/*      */ 
/*      */ public abstract class ColorSpaceJAI extends ColorSpace {
/*      */   private static final double maxXYZ = 1.999969482421875D;
/*      */   
/*      */   private static final double power1 = 0.4166666666666667D;
/*      */   
/*   61 */   private static double[] LUT = new double[256];
/*      */   
/*      */   private boolean isRGBPreferredIntermediary;
/*      */   
/*      */   static {
/*   64 */     for (int i = 0; i < 256; i++) {
/*   65 */       double v = i / 255.0D;
/*   66 */       if (v < 0.040449936D) {
/*   67 */         LUT[i] = v / 12.92D;
/*      */       } else {
/*   69 */         LUT[i] = Math.pow((v + 0.055D) / 1.055D, 2.4D);
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public static WritableRaster CIEXYZToRGB(Raster src, int[] srcComponentSize, WritableRaster dest, int[] destComponentSize) {
/*  225 */     checkParameters(src, srcComponentSize, dest, destComponentSize);
/*  227 */     SampleModel srcSampleModel = src.getSampleModel();
/*  232 */     if (srcComponentSize == null)
/*  233 */       srcComponentSize = srcSampleModel.getSampleSize(); 
/*  236 */     if (dest == null) {
/*  237 */       Point origin = new Point(src.getMinX(), src.getMinY());
/*  238 */       dest = RasterFactory.createWritableRaster(srcSampleModel, origin);
/*      */     } 
/*  245 */     SampleModel dstSampleModel = dest.getSampleModel();
/*  246 */     if (destComponentSize == null)
/*  247 */       destComponentSize = dstSampleModel.getSampleSize(); 
/*  249 */     PixelAccessor srcAcc = new PixelAccessor(srcSampleModel, null);
/*  250 */     UnpackedImageData srcUid = srcAcc.getPixels(src, src.getBounds(), srcSampleModel.getDataType(), false);
/*  254 */     switch (srcSampleModel.getDataType()) {
/*      */       case 0:
/*  257 */         CIEXYZToRGBByte(srcUid, srcComponentSize, dest, destComponentSize);
/*      */         break;
/*      */       case 1:
/*      */       case 2:
/*  262 */         CIEXYZToRGBShort(srcUid, srcComponentSize, dest, destComponentSize);
/*      */         break;
/*      */       case 3:
/*  266 */         CIEXYZToRGBInt(srcUid, srcComponentSize, dest, destComponentSize);
/*      */         break;
/*      */       case 4:
/*  270 */         CIEXYZToRGBFloat(srcUid, srcComponentSize, dest, destComponentSize);
/*      */         break;
/*      */       case 5:
/*  274 */         CIEXYZToRGBDouble(srcUid, srcComponentSize, dest, destComponentSize);
/*      */         break;
/*      */     } 
/*  279 */     return dest;
/*      */   }
/*      */   
/*      */   protected static void checkParameters(Raster src, int[] srcComponentSize, WritableRaster dest, int[] destComponentSize) {
/*  305 */     if (src == null)
/*  306 */       throw new IllegalArgumentException(JaiI18N.getString("ColorSpaceJAI0")); 
/*  308 */     if (src.getNumBands() != 3)
/*  309 */       throw new IllegalArgumentException(JaiI18N.getString("ColorSpaceJAI1")); 
/*  311 */     if (dest != null && dest.getNumBands() != 3)
/*  312 */       throw new IllegalArgumentException(JaiI18N.getString("ColorSpaceJAI2")); 
/*  314 */     if (srcComponentSize != null && srcComponentSize.length != 3)
/*  315 */       throw new IllegalArgumentException(JaiI18N.getString("ColorSpaceJAI3")); 
/*  317 */     if (destComponentSize != null && destComponentSize.length != 3)
/*  318 */       throw new IllegalArgumentException(JaiI18N.getString("ColorSpaceJAI4")); 
/*      */   }
/*      */   
/*      */   static void convertToSigned(double[] buf, int dataType) {
/*  337 */     if (dataType == 2) {
/*  338 */       for (int i = 0; i < buf.length; i++) {
/*  339 */         short temp = (short)((int)buf[i] & 0xFFFF);
/*  340 */         buf[i] = temp;
/*      */       } 
/*  343 */     } else if (dataType == 3) {
/*  344 */       for (int i = 0; i < buf.length; i++) {
/*  345 */         int temp = (int)((long)buf[i] & 0xFFFFFFFFL);
/*  346 */         buf[i] = temp;
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   static void XYZ2RGB(float[] XYZ, float[] RGB) {
/*  352 */     RGB[0] = 2.9311228F * XYZ[0] - 1.4111496F * XYZ[1] - 0.6038046F * XYZ[2];
/*  353 */     RGB[1] = -0.8763701F * XYZ[0] + 1.7219844F * XYZ[1] + 0.0502565F * XYZ[2];
/*  354 */     RGB[2] = 0.05038065F * XYZ[0] - 0.187272F * XYZ[1] + 1.280027F * XYZ[2];
/*  356 */     for (int i = 0; i < 3; i++) {
/*  357 */       float v = RGB[i];
/*  359 */       if (v < 0.0F)
/*  360 */         v = 0.0F; 
/*  362 */       if (v < 0.0031308F) {
/*  363 */         RGB[i] = 12.92F * v;
/*      */       } else {
/*  365 */         if (v > 1.0F)
/*  366 */           v = 1.0F; 
/*  368 */         RGB[i] = (float)(1.055D * Math.pow(v, 0.4166666666666667D) - 0.055D);
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private static void roundValues(double[] data) {
/*  374 */     for (int i = 0; i < data.length; i++)
/*  375 */       data[i] = (long)(data[i] + 0.5D); 
/*      */   }
/*      */   
/*      */   static void CIEXYZToRGBByte(UnpackedImageData src, int[] srcComponentSize, WritableRaster dest, int[] destComponentSize) {
/*  383 */     byte[] xBuf = src.getByteData(0);
/*  384 */     byte[] yBuf = src.getByteData(1);
/*  385 */     byte[] zBuf = src.getByteData(2);
/*  388 */     float normx = (float)(1.999969482421875D / ((1L << srcComponentSize[0]) - 1L));
/*  389 */     float normy = (float)(1.999969482421875D / ((1L << srcComponentSize[1]) - 1L));
/*  390 */     float normz = (float)(1.999969482421875D / ((1L << srcComponentSize[2]) - 1L));
/*  393 */     double upperr = 1.0D, upperg = 1.0D, upperb = 1.0D;
/*  395 */     int dstType = dest.getSampleModel().getDataType();
/*  398 */     if (dstType < 4) {
/*  399 */       upperr = ((1L << destComponentSize[0]) - 1L);
/*  400 */       upperg = ((1L << destComponentSize[1]) - 1L);
/*  401 */       upperb = ((1L << destComponentSize[2]) - 1L);
/*      */     } 
/*  404 */     int height = dest.getHeight();
/*  405 */     int width = dest.getWidth();
/*  407 */     double[] dstPixels = new double[3 * height * width];
/*  409 */     int xStart = src.bandOffsets[0];
/*  410 */     int yStart = src.bandOffsets[1];
/*  411 */     int zStart = src.bandOffsets[2];
/*  412 */     int srcPixelStride = src.pixelStride;
/*  413 */     int srcLineStride = src.lineStride;
/*  415 */     float[] XYZ = new float[3];
/*  416 */     float[] RGB = new float[3];
/*  418 */     int dIndex = 0;
/*  419 */     for (int j = 0; j < height; j++, xStart += srcLineStride, 
/*  420 */       yStart += srcLineStride, zStart += srcLineStride) {
/*  421 */       int i = 0, xIndex = xStart, yIndex = yStart, zIndex = zStart;
/*  422 */       for (; i < width; i++, xIndex += srcPixelStride, 
/*  423 */         yIndex += srcPixelStride, zIndex += srcPixelStride) {
/*  424 */         XYZ[0] = (xBuf[xIndex] & 0xFF) * normx;
/*  425 */         XYZ[1] = (yBuf[yIndex] & 0xFF) * normy;
/*  426 */         XYZ[2] = (zBuf[zIndex] & 0xFF) * normz;
/*  428 */         XYZ2RGB(XYZ, RGB);
/*  430 */         dstPixels[dIndex++] = upperr * RGB[0];
/*  431 */         dstPixels[dIndex++] = upperg * RGB[1];
/*  432 */         dstPixels[dIndex++] = upperb * RGB[2];
/*      */       } 
/*      */     } 
/*  439 */     if (dstType < 4)
/*  440 */       roundValues(dstPixels); 
/*  442 */     convertToSigned(dstPixels, dstType);
/*  443 */     dest.setPixels(dest.getMinX(), dest.getMinY(), width, height, dstPixels);
/*      */   }
/*      */   
/*      */   private static void CIEXYZToRGBShort(UnpackedImageData src, int[] srcComponentSize, WritableRaster dest, int[] destComponentSize) {
/*  450 */     short[] xBuf = src.getShortData(0);
/*  451 */     short[] yBuf = src.getShortData(1);
/*  452 */     short[] zBuf = src.getShortData(2);
/*  455 */     float normx = (float)(1.999969482421875D / ((1L << srcComponentSize[0]) - 1L));
/*  456 */     float normy = (float)(1.999969482421875D / ((1L << srcComponentSize[1]) - 1L));
/*  457 */     float normz = (float)(1.999969482421875D / ((1L << srcComponentSize[2]) - 1L));
/*  460 */     double upperr = 1.0D, upperg = 1.0D, upperb = 1.0D;
/*  462 */     int dstType = dest.getSampleModel().getDataType();
/*  465 */     if (dstType < 4) {
/*  466 */       upperr = ((1L << destComponentSize[0]) - 1L);
/*  467 */       upperg = ((1L << destComponentSize[1]) - 1L);
/*  468 */       upperb = ((1L << destComponentSize[2]) - 1L);
/*      */     } 
/*  471 */     int height = dest.getHeight();
/*  472 */     int width = dest.getWidth();
/*  474 */     double[] dstPixels = new double[3 * height * width];
/*  476 */     int xStart = src.bandOffsets[0];
/*  477 */     int yStart = src.bandOffsets[1];
/*  478 */     int zStart = src.bandOffsets[2];
/*  479 */     int srcPixelStride = src.pixelStride;
/*  480 */     int srcLineStride = src.lineStride;
/*  482 */     float[] XYZ = new float[3];
/*  483 */     float[] RGB = new float[3];
/*  485 */     int dIndex = 0;
/*  486 */     for (int j = 0; j < height; j++, xStart += srcLineStride, 
/*  487 */       yStart += srcLineStride, zStart += srcLineStride) {
/*  488 */       int i = 0, xIndex = xStart, yIndex = yStart, zIndex = zStart;
/*  489 */       for (; i < width; i++, xIndex += srcPixelStride, 
/*  490 */         yIndex += srcPixelStride, zIndex += srcPixelStride) {
/*  491 */         XYZ[0] = (xBuf[xIndex] & 0xFFFF) * normx;
/*  492 */         XYZ[1] = (yBuf[yIndex] & 0xFFFF) * normy;
/*  493 */         XYZ[2] = (zBuf[zIndex] & 0xFFFF) * normz;
/*  495 */         XYZ2RGB(XYZ, RGB);
/*  497 */         dstPixels[dIndex++] = upperr * RGB[0];
/*  498 */         dstPixels[dIndex++] = upperg * RGB[1];
/*  499 */         dstPixels[dIndex++] = upperb * RGB[2];
/*      */       } 
/*      */     } 
/*  506 */     if (dstType < 4)
/*  507 */       roundValues(dstPixels); 
/*  509 */     convertToSigned(dstPixels, dstType);
/*  510 */     dest.setPixels(dest.getMinX(), dest.getMinY(), width, height, dstPixels);
/*      */   }
/*      */   
/*      */   private static void CIEXYZToRGBInt(UnpackedImageData src, int[] srcComponentSize, WritableRaster dest, int[] destComponentSize) {
/*  517 */     int[] xBuf = src.getIntData(0);
/*  518 */     int[] yBuf = src.getIntData(1);
/*  519 */     int[] zBuf = src.getIntData(2);
/*  522 */     float normx = (float)(1.999969482421875D / ((1L << srcComponentSize[0]) - 1L));
/*  523 */     float normy = (float)(1.999969482421875D / ((1L << srcComponentSize[1]) - 1L));
/*  524 */     float normz = (float)(1.999969482421875D / ((1L << srcComponentSize[2]) - 1L));
/*  527 */     double upperr = 1.0D, upperg = 1.0D, upperb = 1.0D;
/*  529 */     int dstType = dest.getSampleModel().getDataType();
/*  532 */     if (dstType < 4) {
/*  533 */       upperr = ((1L << destComponentSize[0]) - 1L);
/*  534 */       upperg = ((1L << destComponentSize[1]) - 1L);
/*  535 */       upperb = ((1L << destComponentSize[2]) - 1L);
/*      */     } 
/*  538 */     int height = dest.getHeight();
/*  539 */     int width = dest.getWidth();
/*  541 */     double[] dstPixels = new double[3 * height * width];
/*  543 */     int xStart = src.bandOffsets[0];
/*  544 */     int yStart = src.bandOffsets[1];
/*  545 */     int zStart = src.bandOffsets[2];
/*  546 */     int srcPixelStride = src.pixelStride;
/*  547 */     int srcLineStride = src.lineStride;
/*  549 */     float[] XYZ = new float[3];
/*  550 */     float[] RGB = new float[3];
/*  552 */     int dIndex = 0;
/*  553 */     for (int j = 0; j < height; j++, xStart += srcLineStride, 
/*  554 */       yStart += srcLineStride, zStart += srcLineStride) {
/*  555 */       int i = 0, xIndex = xStart, yIndex = yStart, zIndex = zStart;
/*  556 */       for (; i < width; i++, xIndex += srcPixelStride, 
/*  557 */         yIndex += srcPixelStride, zIndex += srcPixelStride) {
/*  558 */         XYZ[0] = (float)(xBuf[xIndex] & 0xFFFFFFFFL) * normx;
/*  559 */         XYZ[1] = (float)(yBuf[yIndex] & 0xFFFFFFFFL) * normy;
/*  560 */         XYZ[2] = (float)(zBuf[zIndex] & 0xFFFFFFFFL) * normz;
/*  562 */         XYZ2RGB(XYZ, RGB);
/*  564 */         dstPixels[dIndex++] = upperr * RGB[0];
/*  565 */         dstPixels[dIndex++] = upperg * RGB[1];
/*  566 */         dstPixels[dIndex++] = upperb * RGB[2];
/*      */       } 
/*      */     } 
/*  573 */     if (dstType < 4)
/*  574 */       roundValues(dstPixels); 
/*  576 */     convertToSigned(dstPixels, dstType);
/*  577 */     dest.setPixels(dest.getMinX(), dest.getMinY(), width, height, dstPixels);
/*      */   }
/*      */   
/*      */   private static void CIEXYZToRGBFloat(UnpackedImageData src, int[] srcComponentSize, WritableRaster dest, int[] destComponentSize) {
/*  585 */     float[] xBuf = src.getFloatData(0);
/*  586 */     float[] yBuf = src.getFloatData(1);
/*  587 */     float[] zBuf = src.getFloatData(2);
/*  590 */     double upperr = 1.0D, upperg = 1.0D, upperb = 1.0D;
/*  592 */     int dstType = dest.getSampleModel().getDataType();
/*  595 */     if (dstType < 4) {
/*  596 */       upperr = ((1L << destComponentSize[0]) - 1L);
/*  597 */       upperg = ((1L << destComponentSize[1]) - 1L);
/*  598 */       upperb = ((1L << destComponentSize[2]) - 1L);
/*      */     } 
/*  601 */     int height = dest.getHeight();
/*  602 */     int width = dest.getWidth();
/*  604 */     double[] dstPixels = new double[3 * height * width];
/*  606 */     int xStart = src.bandOffsets[0];
/*  607 */     int yStart = src.bandOffsets[1];
/*  608 */     int zStart = src.bandOffsets[2];
/*  609 */     int srcPixelStride = src.pixelStride;
/*  610 */     int srcLineStride = src.lineStride;
/*  612 */     float[] XYZ = new float[3];
/*  613 */     float[] RGB = new float[3];
/*  615 */     int dIndex = 0;
/*  616 */     for (int j = 0; j < height; j++, xStart += srcLineStride, 
/*  617 */       yStart += srcLineStride, zStart += srcLineStride) {
/*  618 */       int i = 0, xIndex = xStart, yIndex = yStart, zIndex = zStart;
/*  619 */       for (; i < width; i++, xIndex += srcPixelStride, 
/*  620 */         yIndex += srcPixelStride, zIndex += srcPixelStride) {
/*  621 */         XYZ[0] = xBuf[xIndex];
/*  622 */         XYZ[1] = yBuf[yIndex];
/*  623 */         XYZ[2] = zBuf[zIndex];
/*  625 */         XYZ2RGB(XYZ, RGB);
/*  627 */         dstPixels[dIndex++] = upperr * RGB[0];
/*  628 */         dstPixels[dIndex++] = upperg * RGB[1];
/*  629 */         dstPixels[dIndex++] = upperb * RGB[2];
/*      */       } 
/*      */     } 
/*  636 */     if (dstType < 4)
/*  637 */       roundValues(dstPixels); 
/*  639 */     convertToSigned(dstPixels, dstType);
/*  640 */     dest.setPixels(dest.getMinX(), dest.getMinY(), width, height, dstPixels);
/*      */   }
/*      */   
/*      */   private static void CIEXYZToRGBDouble(UnpackedImageData src, int[] srcComponentSize, WritableRaster dest, int[] destComponentSize) {
/*  648 */     double[] xBuf = src.getDoubleData(0);
/*  649 */     double[] yBuf = src.getDoubleData(1);
/*  650 */     double[] zBuf = src.getDoubleData(2);
/*  653 */     double upperr = 1.0D, upperg = 1.0D, upperb = 1.0D;
/*  655 */     int dstType = dest.getSampleModel().getDataType();
/*  658 */     if (dstType < 4) {
/*  659 */       upperr = ((1L << destComponentSize[0]) - 1L);
/*  660 */       upperg = ((1L << destComponentSize[1]) - 1L);
/*  661 */       upperb = ((1L << destComponentSize[2]) - 1L);
/*      */     } 
/*  664 */     int height = dest.getHeight();
/*  665 */     int width = dest.getWidth();
/*  667 */     double[] dstPixels = new double[3 * height * width];
/*  669 */     int xStart = src.bandOffsets[0];
/*  670 */     int yStart = src.bandOffsets[1];
/*  671 */     int zStart = src.bandOffsets[2];
/*  672 */     int srcPixelStride = src.pixelStride;
/*  673 */     int srcLineStride = src.lineStride;
/*  675 */     float[] XYZ = new float[3];
/*  676 */     float[] RGB = new float[3];
/*  678 */     int dIndex = 0;
/*  679 */     for (int j = 0; j < height; j++, xStart += srcLineStride, 
/*  680 */       yStart += srcLineStride, zStart += srcLineStride) {
/*  681 */       int i = 0, xIndex = xStart, yIndex = yStart, zIndex = zStart;
/*  682 */       for (; i < width; i++, xIndex += srcPixelStride, 
/*  683 */         yIndex += srcPixelStride, zIndex += srcPixelStride) {
/*  684 */         XYZ[0] = (float)xBuf[xIndex];
/*  685 */         XYZ[1] = (float)yBuf[yIndex];
/*  686 */         XYZ[2] = (float)zBuf[zIndex];
/*  688 */         XYZ2RGB(XYZ, RGB);
/*  690 */         dstPixels[dIndex++] = upperr * RGB[0];
/*  691 */         dstPixels[dIndex++] = upperg * RGB[1];
/*  692 */         dstPixels[dIndex++] = upperb * RGB[2];
/*      */       } 
/*      */     } 
/*  699 */     if (dstType < 4)
/*  700 */       roundValues(dstPixels); 
/*  702 */     convertToSigned(dstPixels, dstType);
/*  703 */     dest.setPixels(dest.getMinX(), dest.getMinY(), width, height, dstPixels);
/*      */   }
/*      */   
/*      */   public static WritableRaster RGBToCIEXYZ(Raster src, int[] srcComponentSize, WritableRaster dest, int[] destComponentSize) {
/*  847 */     checkParameters(src, srcComponentSize, dest, destComponentSize);
/*  849 */     SampleModel srcSampleModel = src.getSampleModel();
/*  853 */     if (srcComponentSize == null)
/*  854 */       srcComponentSize = srcSampleModel.getSampleSize(); 
/*  857 */     if (dest == null) {
/*  858 */       Point origin = new Point(src.getMinX(), src.getMinY());
/*  859 */       dest = RasterFactory.createWritableRaster(srcSampleModel, origin);
/*      */     } 
/*  863 */     SampleModel dstSampleModel = dest.getSampleModel();
/*  867 */     if (destComponentSize == null)
/*  868 */       destComponentSize = dstSampleModel.getSampleSize(); 
/*  870 */     PixelAccessor srcAcc = new PixelAccessor(srcSampleModel, null);
/*  871 */     UnpackedImageData srcUid = srcAcc.getPixels(src, src.getBounds(), srcSampleModel.getDataType(), false);
/*  875 */     switch (srcSampleModel.getDataType()) {
/*      */       case 0:
/*  878 */         RGBToCIEXYZByte(srcUid, srcComponentSize, dest, destComponentSize);
/*      */         break;
/*      */       case 1:
/*      */       case 2:
/*  882 */         RGBToCIEXYZShort(srcUid, srcComponentSize, dest, destComponentSize);
/*      */         break;
/*      */       case 3:
/*  885 */         RGBToCIEXYZInt(srcUid, srcComponentSize, dest, destComponentSize);
/*      */         break;
/*      */       case 4:
/*  888 */         RGBToCIEXYZFloat(srcUid, srcComponentSize, dest, destComponentSize);
/*      */         break;
/*      */       case 5:
/*  891 */         RGBToCIEXYZDouble(srcUid, srcComponentSize, dest, destComponentSize);
/*      */         break;
/*      */     } 
/*  895 */     return dest;
/*      */   }
/*      */   
/*      */   static void RGB2XYZ(float[] RGB, float[] XYZ) {
/*  899 */     for (int i = 0; i < 3; i++) {
/*  900 */       if (RGB[i] < 0.040449936F) {
/*  901 */         RGB[i] = RGB[i] / 12.92F;
/*      */       } else {
/*  903 */         RGB[i] = (float)Math.pow((RGB[i] + 0.055D) / 1.055D, 2.4D);
/*      */       } 
/*      */     } 
/*  906 */     XYZ[0] = 0.45593762F * RGB[0] + 0.39533818F * RGB[1] + 0.19954965F * RGB[2];
/*  908 */     XYZ[1] = 0.23157515F * RGB[0] + 0.7790526F * RGB[1] + 0.07864978F * RGB[2];
/*  910 */     XYZ[2] = 0.01593493F * RGB[0] + 0.09841772F * RGB[1] + 0.7848861F * RGB[2];
/*      */   }
/*      */   
/*      */   private static void RGBToCIEXYZByte(UnpackedImageData src, int[] srcComponentSize, WritableRaster dest, int[] destComponentSize) {
/*  919 */     byte[] rBuf = src.getByteData(0);
/*  920 */     byte[] gBuf = src.getByteData(1);
/*  921 */     byte[] bBuf = src.getByteData(2);
/*  924 */     int normr = 8 - srcComponentSize[0];
/*  925 */     int normg = 8 - srcComponentSize[1];
/*  926 */     int normb = 8 - srcComponentSize[2];
/*  929 */     double normx = 1.0D, normy = normx, normz = normx;
/*  931 */     int dstType = dest.getSampleModel().getDataType();
/*  932 */     boolean isInt = (dstType < 4);
/*  937 */     if (isInt) {
/*  938 */       normx = ((1L << destComponentSize[0]) - 1L) / 1.999969482421875D;
/*  939 */       normy = ((1L << destComponentSize[1]) - 1L) / 1.999969482421875D;
/*  940 */       normz = ((1L << destComponentSize[2]) - 1L) / 1.999969482421875D;
/*      */     } 
/*  943 */     int height = dest.getHeight();
/*  944 */     int width = dest.getWidth();
/*  946 */     double[] dstPixels = new double[3 * height * width];
/*  948 */     int rStart = src.bandOffsets[0];
/*  949 */     int gStart = src.bandOffsets[1];
/*  950 */     int bStart = src.bandOffsets[2];
/*  951 */     int srcPixelStride = src.pixelStride;
/*  952 */     int srcLineStride = src.lineStride;
/*  954 */     int dIndex = 0;
/*  955 */     for (int j = 0; j < height; j++, rStart += srcLineStride, 
/*  956 */       gStart += srcLineStride, bStart += srcLineStride) {
/*  957 */       int i = 0, rIndex = rStart, gIndex = gStart, bIndex = bStart;
/*  958 */       for (; i < width; i++, rIndex += srcPixelStride, 
/*  959 */         gIndex += srcPixelStride, bIndex += srcPixelStride) {
/*  960 */         double R = LUT[(rBuf[rIndex] & 0xFF) << normr];
/*  961 */         double G = LUT[(gBuf[gIndex] & 0xFF) << normg];
/*  962 */         double B = LUT[(bBuf[bIndex] & 0xFF) << normb];
/*  964 */         if (isInt) {
/*  966 */           dstPixels[dIndex++] = (0.45593763D * R + 0.39533819D * G + 0.19954964D * B) * normx;
/*  968 */           dstPixels[dIndex++] = (0.23157515D * R + 0.77905262D * G + 0.07864978D * B) * normy;
/*  970 */           dstPixels[dIndex++] = (0.01593493D * R + 0.09841772D * G + 0.78488615D * B) * normz;
/*      */         } else {
/*  973 */           dstPixels[dIndex++] = 0.45593763D * R + 0.39533819D * G + 0.19954964D * B;
/*  975 */           dstPixels[dIndex++] = 0.23157515D * R + 0.77905262D * G + 0.07864978D * B;
/*  977 */           dstPixels[dIndex++] = 0.01593493D * R + 0.09841772D * G + 0.78488615D * B;
/*      */         } 
/*      */       } 
/*      */     } 
/*  986 */     if (dstType < 4)
/*  987 */       roundValues(dstPixels); 
/*  989 */     convertToSigned(dstPixels, dstType);
/*  990 */     dest.setPixels(dest.getMinX(), dest.getMinY(), width, height, dstPixels);
/*      */   }
/*      */   
/*      */   private static void RGBToCIEXYZShort(UnpackedImageData src, int[] srcComponentSize, WritableRaster dest, int[] destComponentSize) {
/*  998 */     short[] rBuf = src.getShortData(0);
/*  999 */     short[] gBuf = src.getShortData(1);
/* 1000 */     short[] bBuf = src.getShortData(2);
/* 1003 */     float normr = ((1 << srcComponentSize[0]) - 1);
/* 1004 */     float normg = ((1 << srcComponentSize[1]) - 1);
/* 1005 */     float normb = ((1 << srcComponentSize[2]) - 1);
/* 1008 */     double normx = 1.0D, normy = 1.0D, normz = 1.0D;
/* 1010 */     int dstType = dest.getSampleModel().getDataType();
/* 1011 */     boolean isInt = (dstType < 4);
/* 1015 */     if (isInt) {
/* 1016 */       normx = ((1L << destComponentSize[0]) - 1L) / 1.999969482421875D;
/* 1017 */       normy = ((1L << destComponentSize[1]) - 1L) / 1.999969482421875D;
/* 1018 */       normz = ((1L << destComponentSize[2]) - 1L) / 1.999969482421875D;
/*      */     } 
/* 1021 */     int height = dest.getHeight();
/* 1022 */     int width = dest.getWidth();
/* 1024 */     double[] dstPixels = new double[3 * height * width];
/* 1026 */     int rStart = src.bandOffsets[0];
/* 1027 */     int gStart = src.bandOffsets[1];
/* 1028 */     int bStart = src.bandOffsets[2];
/* 1029 */     int srcPixelStride = src.pixelStride;
/* 1030 */     int srcLineStride = src.lineStride;
/* 1032 */     float[] XYZ = new float[3];
/* 1033 */     float[] RGB = new float[3];
/* 1035 */     int dIndex = 0;
/* 1036 */     for (int j = 0; j < height; j++, rStart += srcLineStride, 
/* 1037 */       gStart += srcLineStride, bStart += srcLineStride) {
/* 1038 */       int i = 0, rIndex = rStart, gIndex = gStart, bIndex = bStart;
/* 1039 */       for (; i < width; i++, rIndex += srcPixelStride, 
/* 1040 */         gIndex += srcPixelStride, bIndex += srcPixelStride) {
/* 1041 */         RGB[0] = (rBuf[rIndex] & 0xFFFF) / normr;
/* 1042 */         RGB[1] = (gBuf[gIndex] & 0xFFFF) / normg;
/* 1043 */         RGB[2] = (bBuf[bIndex] & 0xFFFF) / normb;
/* 1045 */         RGB2XYZ(RGB, XYZ);
/* 1047 */         if (isInt) {
/* 1048 */           dstPixels[dIndex++] = XYZ[0] * normx;
/* 1049 */           dstPixels[dIndex++] = XYZ[1] * normy;
/* 1050 */           dstPixels[dIndex++] = XYZ[2] * normz;
/*      */         } else {
/* 1052 */           dstPixels[dIndex++] = XYZ[0];
/* 1053 */           dstPixels[dIndex++] = XYZ[1];
/* 1054 */           dstPixels[dIndex++] = XYZ[2];
/*      */         } 
/*      */       } 
/*      */     } 
/* 1062 */     if (dstType < 4)
/* 1063 */       roundValues(dstPixels); 
/* 1065 */     convertToSigned(dstPixels, dstType);
/* 1066 */     dest.setPixels(dest.getMinX(), dest.getMinY(), width, height, dstPixels);
/*      */   }
/*      */   
/*      */   private static void RGBToCIEXYZInt(UnpackedImageData src, int[] srcComponentSize, WritableRaster dest, int[] destComponentSize) {
/* 1074 */     int[] rBuf = src.getIntData(0);
/* 1075 */     int[] gBuf = src.getIntData(1);
/* 1076 */     int[] bBuf = src.getIntData(2);
/* 1079 */     float normr = (float)((1L << srcComponentSize[0]) - 1L);
/* 1080 */     float normg = (float)((1L << srcComponentSize[1]) - 1L);
/* 1081 */     float normb = (float)((1L << srcComponentSize[2]) - 1L);
/* 1084 */     double normx = 1.0D, normy = 1.0D, normz = 1.0D;
/* 1086 */     int dstType = dest.getSampleModel().getDataType();
/* 1087 */     boolean isInt = (dstType < 4);
/* 1091 */     if (isInt) {
/* 1092 */       normx = ((1L << destComponentSize[0]) - 1L) / 1.999969482421875D;
/* 1093 */       normy = ((1L << destComponentSize[1]) - 1L) / 1.999969482421875D;
/* 1094 */       normz = ((1L << destComponentSize[2]) - 1L) / 1.999969482421875D;
/*      */     } 
/* 1097 */     int height = dest.getHeight();
/* 1098 */     int width = dest.getWidth();
/* 1100 */     double[] dstPixels = new double[3 * height * width];
/* 1102 */     int rStart = src.bandOffsets[0];
/* 1103 */     int gStart = src.bandOffsets[1];
/* 1104 */     int bStart = src.bandOffsets[2];
/* 1105 */     int srcPixelStride = src.pixelStride;
/* 1106 */     int srcLineStride = src.lineStride;
/* 1108 */     float[] XYZ = new float[3];
/* 1109 */     float[] RGB = new float[3];
/* 1111 */     int dIndex = 0;
/* 1112 */     for (int j = 0; j < height; j++, rStart += srcLineStride, 
/* 1113 */       gStart += srcLineStride, bStart += srcLineStride) {
/* 1114 */       int i = 0, rIndex = rStart, gIndex = gStart, bIndex = bStart;
/* 1115 */       for (; i < width; i++, rIndex += srcPixelStride, 
/* 1116 */         gIndex += srcPixelStride, bIndex += srcPixelStride) {
/* 1117 */         RGB[0] = (float)(rBuf[rIndex] & 0xFFFFFFFFL) / normr;
/* 1118 */         RGB[1] = (float)(gBuf[gIndex] & 0xFFFFFFFFL) / normg;
/* 1119 */         RGB[2] = (float)(bBuf[bIndex] & 0xFFFFFFFFL) / normb;
/* 1121 */         RGB2XYZ(RGB, XYZ);
/* 1123 */         if (isInt) {
/* 1124 */           dstPixels[dIndex++] = XYZ[0] * normx;
/* 1125 */           dstPixels[dIndex++] = XYZ[1] * normx;
/* 1126 */           dstPixels[dIndex++] = XYZ[2] * normx;
/*      */         } else {
/* 1128 */           dstPixels[dIndex++] = XYZ[0];
/* 1129 */           dstPixels[dIndex++] = XYZ[1];
/* 1130 */           dstPixels[dIndex++] = XYZ[2];
/*      */         } 
/*      */       } 
/*      */     } 
/* 1138 */     if (dstType < 4)
/* 1139 */       roundValues(dstPixels); 
/* 1141 */     convertToSigned(dstPixels, dstType);
/* 1142 */     dest.setPixels(dest.getMinX(), dest.getMinY(), width, height, dstPixels);
/*      */   }
/*      */   
/*      */   private static void RGBToCIEXYZFloat(UnpackedImageData src, int[] srcComponentSize, WritableRaster dest, int[] destComponentSize) {
/* 1150 */     float[] rBuf = src.getFloatData(0);
/* 1151 */     float[] gBuf = src.getFloatData(1);
/* 1152 */     float[] bBuf = src.getFloatData(2);
/* 1155 */     double normx = 1.0D, normy = 1.0D, normz = 1.0D;
/* 1157 */     int dstType = dest.getSampleModel().getDataType();
/* 1158 */     boolean isInt = (dstType < 4);
/* 1161 */     if (isInt) {
/* 1162 */       normx = ((1L << destComponentSize[0]) - 1L) / 1.999969482421875D;
/* 1163 */       normy = ((1L << destComponentSize[1]) - 1L) / 1.999969482421875D;
/* 1164 */       normz = ((1L << destComponentSize[2]) - 1L) / 1.999969482421875D;
/*      */     } 
/* 1167 */     int height = dest.getHeight();
/* 1168 */     int width = dest.getWidth();
/* 1170 */     double[] dstPixels = new double[3 * height * width];
/* 1172 */     int rStart = src.bandOffsets[0];
/* 1173 */     int gStart = src.bandOffsets[1];
/* 1174 */     int bStart = src.bandOffsets[2];
/* 1175 */     int srcPixelStride = src.pixelStride;
/* 1176 */     int srcLineStride = src.lineStride;
/* 1178 */     float[] XYZ = new float[3];
/* 1179 */     float[] RGB = new float[3];
/* 1181 */     int dIndex = 0;
/* 1182 */     for (int j = 0; j < height; j++, rStart += srcLineStride, 
/* 1183 */       gStart += srcLineStride, bStart += srcLineStride) {
/* 1184 */       int i = 0, rIndex = rStart, gIndex = gStart, bIndex = bStart;
/* 1185 */       for (; i < width; i++, rIndex += srcPixelStride, 
/* 1186 */         gIndex += srcPixelStride, bIndex += srcPixelStride) {
/* 1187 */         RGB[0] = rBuf[rIndex];
/* 1188 */         RGB[1] = gBuf[gIndex];
/* 1189 */         RGB[2] = bBuf[bIndex];
/* 1191 */         RGB2XYZ(RGB, XYZ);
/* 1193 */         if (isInt) {
/* 1194 */           dstPixels[dIndex++] = XYZ[0] * normx;
/* 1195 */           dstPixels[dIndex++] = XYZ[1] * normx;
/* 1196 */           dstPixels[dIndex++] = XYZ[2] * normx;
/*      */         } else {
/* 1198 */           dstPixels[dIndex++] = XYZ[0];
/* 1199 */           dstPixels[dIndex++] = XYZ[1];
/* 1200 */           dstPixels[dIndex++] = XYZ[2];
/*      */         } 
/*      */       } 
/*      */     } 
/* 1208 */     if (dstType < 4)
/* 1209 */       roundValues(dstPixels); 
/* 1211 */     convertToSigned(dstPixels, dstType);
/* 1212 */     dest.setPixels(dest.getMinX(), dest.getMinY(), width, height, dstPixels);
/*      */   }
/*      */   
/*      */   private static void RGBToCIEXYZDouble(UnpackedImageData src, int[] srcComponentSize, WritableRaster dest, int[] destComponentSize) {
/* 1220 */     double[] rBuf = src.getDoubleData(0);
/* 1221 */     double[] gBuf = src.getDoubleData(1);
/* 1222 */     double[] bBuf = src.getDoubleData(2);
/* 1225 */     double normx = 1.0D, normy = 1.0D, normz = 1.0D;
/* 1227 */     int dstType = dest.getSampleModel().getDataType();
/* 1228 */     boolean isInt = (dstType < 4);
/* 1230 */     if (isInt) {
/* 1231 */       normx = ((1L << destComponentSize[0]) - 1L) / 1.999969482421875D;
/* 1232 */       normy = ((1L << destComponentSize[1]) - 1L) / 1.999969482421875D;
/* 1233 */       normz = ((1L << destComponentSize[2]) - 1L) / 1.999969482421875D;
/*      */     } 
/* 1236 */     int height = dest.getHeight();
/* 1237 */     int width = dest.getWidth();
/* 1239 */     double[] dstPixels = new double[3 * height * width];
/* 1241 */     int rStart = src.bandOffsets[0];
/* 1242 */     int gStart = src.bandOffsets[1];
/* 1243 */     int bStart = src.bandOffsets[2];
/* 1244 */     int srcPixelStride = src.pixelStride;
/* 1245 */     int srcLineStride = src.lineStride;
/* 1247 */     float[] XYZ = new float[3];
/* 1248 */     float[] RGB = new float[3];
/* 1250 */     int dIndex = 0;
/* 1251 */     for (int j = 0; j < height; j++, rStart += srcLineStride, 
/* 1252 */       gStart += srcLineStride, bStart += srcLineStride) {
/* 1253 */       int i = 0, rIndex = rStart, gIndex = gStart, bIndex = bStart;
/* 1254 */       for (; i < width; i++, rIndex += srcPixelStride, 
/* 1255 */         gIndex += srcPixelStride, bIndex += srcPixelStride) {
/* 1256 */         RGB[0] = (float)rBuf[rIndex];
/* 1257 */         RGB[1] = (float)gBuf[gIndex];
/* 1258 */         RGB[2] = (float)bBuf[bIndex];
/* 1260 */         RGB2XYZ(RGB, XYZ);
/* 1262 */         if (isInt) {
/* 1263 */           dstPixels[dIndex++] = XYZ[0] * normx;
/* 1264 */           dstPixels[dIndex++] = XYZ[1] * normx;
/* 1265 */           dstPixels[dIndex++] = XYZ[2] * normx;
/*      */         } else {
/* 1267 */           dstPixels[dIndex++] = XYZ[0];
/* 1268 */           dstPixels[dIndex++] = XYZ[1];
/* 1269 */           dstPixels[dIndex++] = XYZ[2];
/*      */         } 
/*      */       } 
/*      */     } 
/* 1277 */     if (dstType < 4)
/* 1278 */       roundValues(dstPixels); 
/* 1280 */     convertToSigned(dstPixels, dstType);
/* 1281 */     dest.setPixels(dest.getMinX(), dest.getMinY(), width, height, dstPixels);
/*      */   }
/*      */   
/*      */   protected ColorSpaceJAI(int type, int numComponents, boolean isRGBPreferredIntermediary) {
/* 1301 */     super(type, numComponents);
/* 1302 */     this.isRGBPreferredIntermediary = isRGBPreferredIntermediary;
/*      */   }
/*      */   
/*      */   public boolean isRGBPreferredIntermediary() {
/* 1314 */     return this.isRGBPreferredIntermediary;
/*      */   }
/*      */   
/*      */   public abstract WritableRaster fromCIEXYZ(Raster paramRaster, int[] paramArrayOfint1, WritableRaster paramWritableRaster, int[] paramArrayOfint2);
/*      */   
/*      */   public abstract WritableRaster fromRGB(Raster paramRaster, int[] paramArrayOfint1, WritableRaster paramWritableRaster, int[] paramArrayOfint2);
/*      */   
/*      */   public abstract WritableRaster toCIEXYZ(Raster paramRaster, int[] paramArrayOfint1, WritableRaster paramWritableRaster, int[] paramArrayOfint2);
/*      */   
/*      */   public abstract WritableRaster toRGB(Raster paramRaster, int[] paramArrayOfint1, WritableRaster paramWritableRaster, int[] paramArrayOfint2);
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\ColorSpaceJAI.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */