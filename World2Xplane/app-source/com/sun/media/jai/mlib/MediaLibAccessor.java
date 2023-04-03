/*      */ package com.sun.media.jai.mlib;
/*      */ 
/*      */ import com.sun.media.jai.util.DataBufferUtils;
/*      */ import com.sun.media.jai.util.ImageUtil;
/*      */ import com.sun.medialib.mlib.Image;
/*      */ import com.sun.medialib.mlib.mediaLibImage;
/*      */ import java.awt.Rectangle;
/*      */ import java.awt.image.ColorModel;
/*      */ import java.awt.image.ComponentSampleModel;
/*      */ import java.awt.image.DataBuffer;
/*      */ import java.awt.image.DataBufferByte;
/*      */ import java.awt.image.DataBufferInt;
/*      */ import java.awt.image.DataBufferShort;
/*      */ import java.awt.image.DataBufferUShort;
/*      */ import java.awt.image.Raster;
/*      */ import java.awt.image.RenderedImage;
/*      */ import java.awt.image.SampleModel;
/*      */ import java.awt.image.WritableRaster;
/*      */ import java.awt.image.renderable.ParameterBlock;
/*      */ import java.io.FilePermission;
/*      */ import java.security.AccessControlException;
/*      */ import java.security.AccessController;
/*      */ import java.security.PrivilegedAction;
/*      */ import javax.media.jai.ImageLayout;
/*      */ import javax.media.jai.JAI;
/*      */ import javax.media.jai.util.ImagingListener;
/*      */ 
/*      */ public class MediaLibAccessor {
/*      */   private static final int COPY_MASK_SHIFT = 7;
/*      */   
/*      */   private static final int COPY_MASK_SIZE = 1;
/*      */   
/*      */   public static final int COPY_MASK = 128;
/*      */   
/*      */   public static final int UNCOPIED = 0;
/*      */   
/*      */   public static final int COPIED = 128;
/*      */   
/*      */   public static final int DATATYPE_MASK = 127;
/*      */   
/*      */   private static final int BINARY_MASK_SHIFT = 8;
/*      */   
/*      */   private static final int BINARY_MASK_SIZE = 1;
/*      */   
/*      */   public static final int BINARY_MASK = 256;
/*      */   
/*      */   public static final int NONBINARY = 0;
/*      */   
/*      */   public static final int BINARY = 256;
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
/*      */   public static final int TAG_BYTE_COPIED = 128;
/*      */   
/*      */   public static final int TAG_USHORT_COPIED = 129;
/*      */   
/*      */   public static final int TAG_SHORT_COPIED = 130;
/*      */   
/*      */   public static final int TAG_INT_COPIED = 131;
/*      */   
/*      */   public static final int TAG_FLOAT_COPIED = 132;
/*      */   
/*      */   public static final int TAG_DOUBLE_COPIED = 133;
/*      */   
/*      */   protected Raster raster;
/*      */   
/*      */   protected Rectangle rect;
/*      */   
/*      */   protected int numBands;
/*      */   
/*      */   protected int[] bandOffsets;
/*      */   
/*      */   protected int formatTag;
/*      */   
/*      */   protected mediaLibImage[] mlimages;
/*      */   
/*      */   private boolean areBinaryDataPacked;
/*      */   
/*      */   private static boolean useMlibVar = false;
/*      */   
/*      */   private static boolean useMlibVarSet = false;
/*      */   
/*      */   private static synchronized boolean useMlib() {
/*  176 */     if (!useMlibVarSet) {
/*  177 */       setUseMlib();
/*  178 */       useMlibVarSet = true;
/*      */     } 
/*  181 */     return useMlibVar;
/*      */   }
/*      */   
/*      */   private static void setUseMlib() {
/*  188 */     boolean disableMediaLib = false;
/*      */     try {
/*  190 */       disableMediaLib = Boolean.getBoolean("com.sun.media.jai.disableMediaLib");
/*  192 */     } catch (AccessControlException e) {}
/*  205 */     if (disableMediaLib) {
/*  206 */       useMlibVar = false;
/*      */       return;
/*      */     } 
/*      */     try {
/*  212 */       SecurityManager securityManager = System.getSecurityManager();
/*  215 */       if (securityManager != null && MediaLibAccessor.class.getClassLoader() != null) {
/*  233 */         String osName = System.getProperty("os.name");
/*  234 */         String osArch = System.getProperty("os.arch");
/*  237 */         if ((osName.equals("Solaris") || osName.equals("SunOS")) && osArch.equals("sparc")) {
/*  239 */           FilePermission fp = new FilePermission("/usr/bin/uname", "execute");
/*  241 */           securityManager.checkPermission(fp);
/*      */         } 
/*      */       } 
/*  245 */       Boolean result = AccessController.<Boolean>doPrivileged(new PrivilegedAction() {
/*      */             public Object run() {
/*  248 */               return new Boolean(Image.isAvailable());
/*      */             }
/*      */           });
/*  251 */       useMlibVar = result.booleanValue();
/*  252 */       if (!useMlibVar)
/*  253 */         forwardToListener(JaiI18N.getString("MediaLibAccessor2"), new MediaLibLoadException()); 
/*  256 */     } catch (NoClassDefFoundError ncdfe) {
/*  258 */       useMlibVar = false;
/*  259 */       forwardToListener(JaiI18N.getString("MediaLibAccessor3"), ncdfe);
/*  260 */     } catch (ClassFormatError cfe) {
/*  262 */       useMlibVar = false;
/*  263 */       forwardToListener(JaiI18N.getString("MediaLibAccessor3"), cfe);
/*  264 */     } catch (SecurityException se) {
/*  266 */       useMlibVar = false;
/*  267 */       forwardToListener(JaiI18N.getString("MediaLibAccessor4"), se);
/*      */     } 
/*  270 */     if (!useMlibVar)
/*      */       return; 
/*      */   }
/*      */   
/*      */   private static void forwardToListener(String message, Throwable thrown) {
/*  282 */     ImagingListener listener = JAI.getDefaultInstance().getImagingListener();
/*  285 */     if (listener != null) {
/*  286 */       listener.errorOccurred(message, thrown, MediaLibAccessor.class, false);
/*      */     } else {
/*  289 */       System.err.println(message);
/*      */     } 
/*      */   }
/*      */   
/*      */   public static boolean isMediaLibCompatible(ParameterBlock args, ImageLayout layout) {
/*  315 */     if (!isMediaLibCompatible(args))
/*  317 */       return false; 
/*  320 */     if (layout != null) {
/*  321 */       SampleModel sm = layout.getSampleModel(null);
/*  322 */       if (sm != null && (
/*  323 */         !(sm instanceof ComponentSampleModel) || sm.getNumBands() > 4))
/*  325 */         return false; 
/*  329 */       ColorModel cm = layout.getColorModel(null);
/*  330 */       if (cm != null && !(cm instanceof java.awt.image.ComponentColorModel))
/*  331 */         return false; 
/*      */     } 
/*  335 */     return true;
/*      */   }
/*      */   
/*      */   public static boolean isMediaLibCompatible(ParameterBlock args) {
/*  357 */     if (!useMlib())
/*  358 */       return false; 
/*  361 */     int numSrcs = args.getNumSources();
/*  362 */     for (int i = 0; i < numSrcs; i++) {
/*  363 */       Object src = args.getSource(i);
/*  364 */       if (!(src instanceof RenderedImage) || !isMediaLibCompatible((RenderedImage)src))
/*  366 */         return false; 
/*      */     } 
/*  370 */     return true;
/*      */   }
/*      */   
/*      */   public static boolean isMediaLibCompatible(RenderedImage image) {
/*  390 */     if (!useMlib())
/*  391 */       return false; 
/*  394 */     SampleModel sm = image.getSampleModel();
/*  395 */     ColorModel cm = image.getColorModel();
/*  397 */     return (sm instanceof ComponentSampleModel && sm.getNumBands() <= 4 && (cm == null || cm instanceof java.awt.image.ComponentColorModel));
/*      */   }
/*      */   
/*      */   public static boolean isMediaLibCompatible(SampleModel sm, ColorModel cm) {
/*  423 */     if (!useMlib())
/*  424 */       return false; 
/*  427 */     return (sm instanceof ComponentSampleModel && sm.getNumBands() <= 4 && (cm == null || cm instanceof java.awt.image.ComponentColorModel));
/*      */   }
/*      */   
/*      */   public static boolean isMediaLibBinaryCompatible(ParameterBlock args, ImageLayout layout) {
/*  456 */     if (!useMlib())
/*  457 */       return false; 
/*  460 */     SampleModel sm = null;
/*  462 */     int numSrcs = args.getNumSources();
/*  463 */     for (int i = 0; i < numSrcs; i++) {
/*  464 */       Object src = args.getSource(i);
/*  465 */       if (!(src instanceof RenderedImage) || (sm = ((RenderedImage)src).getSampleModel()) == null || !ImageUtil.isBinary(sm))
/*  468 */         return false; 
/*      */     } 
/*  472 */     if (layout != null && (
/*  473 */       sm = layout.getSampleModel(null)) != null && !ImageUtil.isBinary(sm))
/*  475 */       return false; 
/*  479 */     return true;
/*      */   }
/*      */   
/*      */   public static boolean hasSameNumBands(ParameterBlock args, ImageLayout layout) {
/*  491 */     int numSrcs = args.getNumSources();
/*  493 */     if (numSrcs > 0) {
/*  494 */       RenderedImage src = args.getRenderedSource(0);
/*  495 */       int numBands = src.getSampleModel().getNumBands();
/*  497 */       for (int i = 1; i < numSrcs; i++) {
/*  498 */         src = args.getRenderedSource(i);
/*  499 */         if (src.getSampleModel().getNumBands() != numBands)
/*  500 */           return false; 
/*      */       } 
/*  504 */       if (layout != null) {
/*  505 */         SampleModel sm = layout.getSampleModel(null);
/*  506 */         if (sm != null && sm.getNumBands() != numBands)
/*  507 */           return false; 
/*      */       } 
/*      */     } 
/*  512 */     return true;
/*      */   }
/*      */   
/*      */   public static int findCompatibleTag(Raster[] srcs, Raster dst) {
/*  523 */     SampleModel srcSM[], dstSM = dst.getSampleModel();
/*  524 */     int dstDT = dstSM.getDataType();
/*  526 */     int defaultDataType = dstSM.getDataType();
/*  528 */     boolean allComponentSampleModel = dstSM instanceof ComponentSampleModel;
/*  530 */     boolean allBinary = ImageUtil.isBinary(dstSM);
/*  533 */     if (srcs != null) {
/*  534 */       int numSources = srcs.length;
/*  536 */       for (int j = 0; j < numSources; j++) {
/*  537 */         SampleModel srcSampleModel = srcs[j].getSampleModel();
/*  538 */         if (!(srcSampleModel instanceof ComponentSampleModel))
/*  539 */           allComponentSampleModel = false; 
/*  541 */         if (!ImageUtil.isBinary(srcSampleModel))
/*  542 */           allBinary = false; 
/*  544 */         int srcDataType = srcSampleModel.getTransferType();
/*  545 */         if (srcDataType > defaultDataType)
/*  546 */           defaultDataType = srcDataType; 
/*      */       } 
/*      */     } 
/*  551 */     if (allBinary)
/*  555 */       return 256; 
/*  558 */     if (!allComponentSampleModel && (
/*  559 */       defaultDataType == 0 || defaultDataType == 1 || defaultDataType == 2))
/*  562 */       defaultDataType = 3; 
/*  566 */     int tag = defaultDataType | 0x80;
/*  568 */     if (!allComponentSampleModel)
/*  569 */       return tag; 
/*  575 */     if (srcs == null) {
/*  576 */       srcSM = new SampleModel[0];
/*      */     } else {
/*  578 */       srcSM = new SampleModel[srcs.length];
/*      */     } 
/*      */     int i;
/*  580 */     for (i = 0; i < srcSM.length; i++) {
/*  581 */       srcSM[i] = srcs[i].getSampleModel();
/*  582 */       if (dstDT != srcSM[i].getDataType())
/*  583 */         return tag; 
/*      */     } 
/*  586 */     if (isPixelSequential(dstSM)) {
/*  587 */       for (i = 0; i < srcSM.length; i++) {
/*  588 */         if (!isPixelSequential(srcSM[i]))
/*  589 */           return tag; 
/*      */       } 
/*  592 */       for (i = 0; i < srcSM.length; i++) {
/*  593 */         if (!hasMatchingBandOffsets((ComponentSampleModel)dstSM, (ComponentSampleModel)srcSM[i]))
/*  595 */           return tag; 
/*      */       } 
/*  598 */       return dstDT | 0x0;
/*      */     } 
/*  600 */     return tag;
/*      */   }
/*      */   
/*      */   public static boolean isPixelSequential(SampleModel sm) {
/*  608 */     ComponentSampleModel csm = null;
/*  609 */     if (sm instanceof ComponentSampleModel) {
/*  610 */       csm = (ComponentSampleModel)sm;
/*      */     } else {
/*  612 */       return false;
/*      */     } 
/*  614 */     int pixelStride = csm.getPixelStride();
/*  615 */     int[] bandOffsets = csm.getBandOffsets();
/*  616 */     int[] bankIndices = csm.getBankIndices();
/*  617 */     if (pixelStride != bandOffsets.length)
/*  618 */       return false; 
/*  620 */     for (int i = 0; i < bandOffsets.length; i++) {
/*  621 */       if (bandOffsets[i] >= pixelStride || bankIndices[i] != bankIndices[0])
/*  623 */         return false; 
/*  625 */       for (int j = i + 1; j < bandOffsets.length; j++) {
/*  626 */         if (bandOffsets[i] == bandOffsets[j])
/*  627 */           return false; 
/*      */       } 
/*      */     } 
/*  631 */     return true;
/*      */   }
/*      */   
/*      */   public static boolean hasMatchingBandOffsets(ComponentSampleModel dst, ComponentSampleModel src) {
/*  641 */     int[] srcBandOffsets = dst.getBandOffsets();
/*  642 */     int[] dstBandOffsets = src.getBandOffsets();
/*  643 */     if (srcBandOffsets.length != dstBandOffsets.length)
/*  644 */       return false; 
/*  646 */     for (int i = 0; i < srcBandOffsets.length; i++) {
/*  647 */       if (srcBandOffsets[i] != dstBandOffsets[i])
/*  648 */         return false; 
/*      */     } 
/*  651 */     return true;
/*      */   }
/*      */   
/*      */   public static int getMediaLibDataType(int formatTag) {
/*  655 */     int dataType = formatTag & 0x7F;
/*  656 */     switch (dataType) {
/*      */       case 0:
/*  658 */         return 1;
/*      */       case 1:
/*  660 */         return 6;
/*      */       case 2:
/*  662 */         return 2;
/*      */       case 3:
/*  664 */         return 3;
/*      */       case 5:
/*  666 */         return 5;
/*      */       case 4:
/*  668 */         return 4;
/*      */     } 
/*  670 */     return -1;
/*      */   }
/*      */   
/*      */   public MediaLibAccessor(Raster raster, Rectangle rect, int formatTag, boolean preferPacked) {
/*      */     byte[] bdata;
/*      */     short[] usdata, sdata;
/*      */     int[] idata;
/*      */     float[] fdata;
/*      */     double[] ddata;
/*      */     this.mlimages = null;
/*      */     this.areBinaryDataPacked = false;
/*  684 */     this.areBinaryDataPacked = preferPacked;
/*  686 */     this.raster = raster;
/*  687 */     this.rect = new Rectangle(rect);
/*  688 */     this.formatTag = formatTag;
/*  690 */     if (isBinary()) {
/*      */       int mlibType, j;
/*      */       byte[] arrayOfByte;
/*  692 */       this.numBands = 1;
/*  693 */       this.bandOffsets = new int[] { 0 };
/*  698 */       this.mlimages = new mediaLibImage[1];
/*  700 */       if (this.areBinaryDataPacked) {
/*  701 */         mlibType = 0;
/*  702 */         j = (rect.width + 7) / 8;
/*  703 */         arrayOfByte = ImageUtil.getPackedBinaryData(raster, rect);
/*  706 */         if (arrayOfByte == ((DataBufferByte)raster.getDataBuffer()).getData()) {
/*  708 */           this.formatTag |= 0x0;
/*      */         } else {
/*  710 */           this.formatTag |= 0x80;
/*      */         } 
/*      */       } else {
/*  713 */         mlibType = 1;
/*  714 */         j = rect.width;
/*  715 */         arrayOfByte = ImageUtil.getUnpackedBinaryData(raster, rect);
/*  716 */         this.formatTag |= 0x80;
/*      */       } 
/*  719 */       this.mlimages[0] = new mediaLibImage(mlibType, 1, rect.width, rect.height, j, 0, arrayOfByte);
/*      */       return;
/*      */     } 
/*  730 */     if ((formatTag & 0x80) == 0) {
/*      */       DataBufferByte dbb;
/*      */       DataBufferUShort dbus;
/*      */       DataBufferShort dbs;
/*      */       DataBufferInt dbi;
/*      */       DataBuffer dbf, dbd;
/*  731 */       ComponentSampleModel csm = (ComponentSampleModel)raster.getSampleModel();
/*  734 */       this.numBands = csm.getNumBands();
/*  735 */       this.bandOffsets = csm.getBandOffsets();
/*  736 */       int dataOffset = raster.getDataBuffer().getOffset();
/*  737 */       dataOffset += (rect.y - raster.getSampleModelTranslateY()) * csm.getScanlineStride() + (rect.x - raster.getSampleModelTranslateX()) * csm.getPixelStride();
/*  744 */       int j = csm.getScanlineStride();
/*  746 */       switch (formatTag & 0x7F) {
/*      */         case 0:
/*  748 */           dbb = (DataBufferByte)raster.getDataBuffer();
/*  749 */           this.mlimages = new mediaLibImage[1];
/*  750 */           this.mlimages[0] = new mediaLibImage(1, this.numBands, rect.width, rect.height, j, dataOffset, dbb.getData());
/*      */           return;
/*      */         case 1:
/*  761 */           dbus = (DataBufferUShort)raster.getDataBuffer();
/*  763 */           this.mlimages = new mediaLibImage[1];
/*  764 */           this.mlimages[0] = new mediaLibImage(6, this.numBands, rect.width, rect.height, j, dataOffset, dbus.getData());
/*      */           return;
/*      */         case 2:
/*  774 */           dbs = (DataBufferShort)raster.getDataBuffer();
/*  775 */           this.mlimages = new mediaLibImage[1];
/*  776 */           this.mlimages[0] = new mediaLibImage(2, this.numBands, rect.width, rect.height, j, dataOffset, dbs.getData());
/*      */           return;
/*      */         case 3:
/*  786 */           dbi = (DataBufferInt)raster.getDataBuffer();
/*  787 */           this.mlimages = new mediaLibImage[1];
/*  788 */           this.mlimages[0] = new mediaLibImage(3, this.numBands, rect.width, rect.height, j, dataOffset, dbi.getData());
/*      */           return;
/*      */         case 4:
/*  798 */           dbf = raster.getDataBuffer();
/*  799 */           this.mlimages = new mediaLibImage[1];
/*  800 */           this.mlimages[0] = new mediaLibImage(4, this.numBands, rect.width, rect.height, j, dataOffset, DataBufferUtils.getDataFloat(dbf));
/*      */           return;
/*      */         case 5:
/*  810 */           dbd = raster.getDataBuffer();
/*  811 */           this.mlimages = new mediaLibImage[1];
/*  812 */           this.mlimages[0] = new mediaLibImage(5, this.numBands, rect.width, rect.height, j, dataOffset, DataBufferUtils.getDataDouble(dbd));
/*      */           return;
/*      */       } 
/*  822 */       throw new IllegalArgumentException((formatTag & 0x7F) + JaiI18N.getString("MediaLibAccessor1"));
/*      */     } 
/*  826 */     this.numBands = raster.getNumBands();
/*  827 */     this.bandOffsets = new int[this.numBands];
/*  828 */     for (int i = 0; i < this.numBands; i++)
/*  829 */       this.bandOffsets[i] = i; 
/*  831 */     int scanlineStride = rect.width * this.numBands;
/*  833 */     switch (formatTag & 0x7F) {
/*      */       case 0:
/*  835 */         bdata = new byte[rect.width * rect.height * this.numBands];
/*  836 */         this.mlimages = new mediaLibImage[1];
/*  837 */         this.mlimages[0] = new mediaLibImage(1, this.numBands, rect.width, rect.height, scanlineStride, 0, bdata);
/*      */         break;
/*      */       case 1:
/*  847 */         usdata = new short[rect.width * rect.height * this.numBands];
/*  848 */         this.mlimages = new mediaLibImage[1];
/*  849 */         this.mlimages[0] = new mediaLibImage(6, this.numBands, rect.width, rect.height, scanlineStride, 0, usdata);
/*      */         break;
/*      */       case 2:
/*  859 */         sdata = new short[rect.width * rect.height * this.numBands];
/*  860 */         this.mlimages = new mediaLibImage[1];
/*  861 */         this.mlimages[0] = new mediaLibImage(2, this.numBands, rect.width, rect.height, scanlineStride, 0, sdata);
/*      */         break;
/*      */       case 3:
/*  871 */         idata = new int[rect.width * rect.height * this.numBands];
/*  872 */         this.mlimages = new mediaLibImage[1];
/*  873 */         this.mlimages[0] = new mediaLibImage(3, this.numBands, rect.width, rect.height, scanlineStride, 0, idata);
/*      */         break;
/*      */       case 4:
/*  883 */         fdata = new float[rect.width * rect.height * this.numBands];
/*  884 */         this.mlimages = new mediaLibImage[1];
/*  885 */         this.mlimages[0] = new mediaLibImage(4, this.numBands, rect.width, rect.height, scanlineStride, 0, fdata);
/*      */         break;
/*      */       case 5:
/*  895 */         ddata = new double[rect.width * rect.height * this.numBands];
/*  896 */         this.mlimages = new mediaLibImage[1];
/*  897 */         this.mlimages[0] = new mediaLibImage(5, this.numBands, rect.width, rect.height, scanlineStride, 0, ddata);
/*      */         break;
/*      */       default:
/*  907 */         throw new IllegalArgumentException((formatTag & 0x7F) + JaiI18N.getString("MediaLibAccessor1"));
/*      */     } 
/*  909 */     copyDataFromRaster();
/*      */   }
/*      */   
/*      */   public MediaLibAccessor(Raster raster, Rectangle rect, int formatTag) {
/*  918 */     this(raster, rect, formatTag, false);
/*      */   }
/*      */   
/*      */   public boolean isBinary() {
/*  926 */     return ((this.formatTag & 0x100) == 256);
/*      */   }
/*      */   
/*      */   public mediaLibImage[] getMediaLibImages() {
/*  937 */     return this.mlimages;
/*      */   }
/*      */   
/*      */   public int getDataType() {
/*  946 */     return this.formatTag & 0x7F;
/*      */   }
/*      */   
/*      */   public boolean isDataCopy() {
/*  954 */     return ((this.formatTag & 0x80) == 128);
/*      */   }
/*      */   
/*      */   public int[] getBandOffsets() {
/*  959 */     return this.bandOffsets;
/*      */   }
/*      */   
/*      */   public int[] getIntParameters(int band, int[] params) {
/*  968 */     int[] returnParams = new int[this.numBands];
/*  969 */     for (int i = 0; i < this.numBands; i++)
/*  970 */       returnParams[i] = params[this.bandOffsets[i + band]]; 
/*  972 */     return returnParams;
/*      */   }
/*      */   
/*      */   public int[][] getIntArrayParameters(int band, int[][] params) {
/*  981 */     int[][] returnParams = new int[this.numBands][];
/*  982 */     for (int i = 0; i < this.numBands; i++)
/*  983 */       returnParams[i] = params[this.bandOffsets[i + band]]; 
/*  985 */     return returnParams;
/*      */   }
/*      */   
/*      */   public double[] getDoubleParameters(int band, double[] params) {
/*  994 */     double[] returnParams = new double[this.numBands];
/*  995 */     for (int i = 0; i < this.numBands; i++)
/*  996 */       returnParams[i] = params[this.bandOffsets[i + band]]; 
/*  998 */     return returnParams;
/*      */   }
/*      */   
/*      */   private void copyDataFromRaster() {
/* 1009 */     if (this.raster.getSampleModel() instanceof ComponentSampleModel) {
/*      */       byte[][] bArray;
/*      */       int i;
/*      */       short[][] usArray;
/*      */       DataBufferByte dbb;
/*      */       DataBufferUShort dbus;
/*      */       DataBufferShort dbs;
/*      */       DataBufferInt dbi;
/*      */       DataBuffer dbf, dbd;
/*      */       int j;
/*      */       short[][] sArray;
/*      */       byte[][] rasByteDataArray;
/*      */       short[][] rasUShortDataArray, rasShortDataArray;
/*      */       int[][] rasIntDataArray;
/*      */       float[][] rasFloatDataArray;
/*      */       double[][] rasDoubleDataArray;
/*      */       int m, iArray[][], k, n;
/*      */       float[][] fArray;
/*      */       int i1;
/*      */       double[][] dArray;
/*      */       int i2;
/* 1010 */       ComponentSampleModel csm = (ComponentSampleModel)this.raster.getSampleModel();
/* 1012 */       int rasScanlineStride = csm.getScanlineStride();
/* 1013 */       int rasPixelStride = csm.getPixelStride();
/* 1015 */       int subRasterOffset = (this.rect.y - this.raster.getSampleModelTranslateY()) * rasScanlineStride + (this.rect.x - this.raster.getSampleModelTranslateX()) * rasPixelStride;
/* 1019 */       int[] rasBankIndices = csm.getBankIndices();
/* 1020 */       int[] rasBandOffsets = csm.getBandOffsets();
/* 1021 */       int[] rasDataOffsets = this.raster.getDataBuffer().getOffsets();
/* 1023 */       if (rasDataOffsets.length == 1) {
/* 1024 */         for (int i3 = 0; i3 < this.numBands; i3++)
/* 1025 */           rasBandOffsets[i3] = rasBandOffsets[i3] + rasDataOffsets[0] + subRasterOffset; 
/* 1028 */       } else if (rasDataOffsets.length == rasBandOffsets.length) {
/* 1029 */         for (int i3 = 0; i3 < this.numBands; i3++)
/* 1030 */           rasBandOffsets[i3] = rasBandOffsets[i3] + rasDataOffsets[i3] + subRasterOffset; 
/*      */       } 
/* 1035 */       Object mlibDataArray = null;
/* 1036 */       switch (getDataType()) {
/*      */         case 0:
/* 1038 */           bArray = new byte[this.numBands][];
/* 1039 */           for (i = 0; i < this.numBands; i++)
/* 1040 */             bArray[i] = this.mlimages[0].getByteData(); 
/* 1042 */           mlibDataArray = bArray;
/*      */           break;
/*      */         case 1:
/* 1045 */           usArray = new short[this.numBands][];
/* 1046 */           for (j = 0; j < this.numBands; j++)
/* 1047 */             usArray[j] = this.mlimages[0].getUShortData(); 
/* 1049 */           mlibDataArray = usArray;
/*      */           break;
/*      */         case 2:
/* 1052 */           sArray = new short[this.numBands][];
/* 1053 */           for (m = 0; m < this.numBands; m++)
/* 1054 */             sArray[m] = this.mlimages[0].getShortData(); 
/* 1056 */           mlibDataArray = sArray;
/*      */           break;
/*      */         case 3:
/* 1059 */           iArray = new int[this.numBands][];
/* 1060 */           for (n = 0; n < this.numBands; n++)
/* 1061 */             iArray[n] = this.mlimages[0].getIntData(); 
/* 1063 */           mlibDataArray = iArray;
/*      */           break;
/*      */         case 4:
/* 1066 */           fArray = new float[this.numBands][];
/* 1067 */           for (i1 = 0; i1 < this.numBands; i1++)
/* 1068 */             fArray[i1] = this.mlimages[0].getFloatData(); 
/* 1070 */           mlibDataArray = fArray;
/*      */           break;
/*      */         case 5:
/* 1073 */           dArray = new double[this.numBands][];
/* 1074 */           for (i2 = 0; i2 < this.numBands; i2++)
/* 1075 */             dArray[i2] = this.mlimages[0].getDoubleData(); 
/* 1077 */           mlibDataArray = dArray;
/*      */           break;
/*      */       } 
/* 1083 */       Object rasDataArray = null;
/* 1084 */       switch (csm.getDataType()) {
/*      */         case 0:
/* 1086 */           dbb = (DataBufferByte)this.raster.getDataBuffer();
/* 1088 */           rasByteDataArray = new byte[this.numBands][];
/* 1089 */           for (k = 0; k < this.numBands; k++)
/* 1090 */             rasByteDataArray[k] = dbb.getData(rasBankIndices[k]); 
/* 1093 */           rasDataArray = rasByteDataArray;
/*      */           break;
/*      */         case 1:
/* 1097 */           dbus = (DataBufferUShort)this.raster.getDataBuffer();
/* 1099 */           rasUShortDataArray = new short[this.numBands][];
/* 1100 */           for (k = 0; k < this.numBands; k++)
/* 1101 */             rasUShortDataArray[k] = dbus.getData(rasBankIndices[k]); 
/* 1104 */           rasDataArray = rasUShortDataArray;
/*      */           break;
/*      */         case 2:
/* 1108 */           dbs = (DataBufferShort)this.raster.getDataBuffer();
/* 1110 */           rasShortDataArray = new short[this.numBands][];
/* 1111 */           for (k = 0; k < this.numBands; k++)
/* 1112 */             rasShortDataArray[k] = dbs.getData(rasBankIndices[k]); 
/* 1115 */           rasDataArray = rasShortDataArray;
/*      */           break;
/*      */         case 3:
/* 1119 */           dbi = (DataBufferInt)this.raster.getDataBuffer();
/* 1121 */           rasIntDataArray = new int[this.numBands][];
/* 1122 */           for (k = 0; k < this.numBands; k++)
/* 1123 */             rasIntDataArray[k] = dbi.getData(rasBankIndices[k]); 
/* 1126 */           rasDataArray = rasIntDataArray;
/*      */           break;
/*      */         case 4:
/* 1130 */           dbf = this.raster.getDataBuffer();
/* 1132 */           rasFloatDataArray = new float[this.numBands][];
/* 1133 */           for (k = 0; k < this.numBands; k++)
/* 1134 */             rasFloatDataArray[k] = DataBufferUtils.getDataFloat(dbf, rasBankIndices[k]); 
/* 1137 */           rasDataArray = rasFloatDataArray;
/*      */           break;
/*      */         case 5:
/* 1141 */           dbd = this.raster.getDataBuffer();
/* 1143 */           rasDoubleDataArray = new double[this.numBands][];
/* 1144 */           for (k = 0; k < this.numBands; k++)
/* 1145 */             rasDoubleDataArray[k] = DataBufferUtils.getDataDouble(dbd, rasBankIndices[k]); 
/* 1148 */           rasDataArray = rasDoubleDataArray;
/*      */           break;
/*      */       } 
/* 1155 */       Image.Reformat(mlibDataArray, rasDataArray, this.numBands, this.rect.width, this.rect.height, getMediaLibDataType(getDataType()), this.bandOffsets, this.rect.width * this.numBands, this.numBands, getMediaLibDataType(csm.getDataType()), rasBandOffsets, rasScanlineStride, rasPixelStride);
/*      */     } else {
/* 1171 */       switch (getDataType()) {
/*      */         case 3:
/* 1173 */           this.raster.getPixels(this.rect.x, this.rect.y, this.rect.width, this.rect.height, this.mlimages[0].getIntData());
/*      */           break;
/*      */         case 4:
/* 1178 */           this.raster.getPixels(this.rect.x, this.rect.y, this.rect.width, this.rect.height, this.mlimages[0].getFloatData());
/*      */           break;
/*      */         case 5:
/* 1183 */           this.raster.getPixels(this.rect.x, this.rect.y, this.rect.width, this.rect.height, this.mlimages[0].getDoubleData());
/*      */           break;
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public void copyDataToRaster() {
/* 1200 */     if (isDataCopy()) {
/* 1202 */       if (isBinary()) {
/* 1203 */         if (this.areBinaryDataPacked) {
/* 1204 */           ImageUtil.setPackedBinaryData(this.mlimages[0].getBitData(), (WritableRaster)this.raster, this.rect);
/*      */         } else {
/* 1208 */           ImageUtil.setUnpackedBinaryData(this.mlimages[0].getByteData(), (WritableRaster)this.raster, this.rect);
/*      */         } 
/*      */         return;
/*      */       } 
/* 1217 */       WritableRaster wr = (WritableRaster)this.raster;
/* 1219 */       if (wr.getSampleModel() instanceof ComponentSampleModel) {
/*      */         byte[][] bArray;
/*      */         int i;
/*      */         short[][] usArray;
/*      */         int j;
/*      */         short[][] sArray;
/*      */         DataBufferByte dbb;
/*      */         DataBufferUShort dbus;
/*      */         DataBufferShort dbs;
/*      */         DataBufferInt dbi;
/*      */         DataBuffer dbf, dbd;
/*      */         int k, iArray[][];
/*      */         byte[][] rasByteDataArray;
/*      */         short[][] rasUShortDataArray, rasShortDataArray;
/*      */         int[][] rasIntDataArray;
/*      */         float[][] rasFloatDataArray;
/*      */         double[][] rasDoubleDataArray;
/*      */         int n;
/*      */         float[][] fArray;
/*      */         int m, i1;
/*      */         double[][] dArray;
/*      */         int i2;
/* 1220 */         ComponentSampleModel csm = (ComponentSampleModel)wr.getSampleModel();
/* 1222 */         int rasScanlineStride = csm.getScanlineStride();
/* 1223 */         int rasPixelStride = csm.getPixelStride();
/* 1225 */         int subRasterOffset = (this.rect.y - this.raster.getSampleModelTranslateY()) * rasScanlineStride + (this.rect.x - this.raster.getSampleModelTranslateX()) * rasPixelStride;
/* 1229 */         int[] rasBankIndices = csm.getBankIndices();
/* 1230 */         int[] rasBandOffsets = csm.getBandOffsets();
/* 1231 */         int[] rasDataOffsets = this.raster.getDataBuffer().getOffsets();
/* 1233 */         if (rasDataOffsets.length == 1) {
/* 1234 */           for (int i3 = 0; i3 < this.numBands; i3++)
/* 1235 */             rasBandOffsets[i3] = rasBandOffsets[i3] + rasDataOffsets[0] + subRasterOffset; 
/* 1238 */         } else if (rasDataOffsets.length == rasBandOffsets.length) {
/* 1239 */           for (int i3 = 0; i3 < this.numBands; i3++)
/* 1240 */             rasBandOffsets[i3] = rasBandOffsets[i3] + rasDataOffsets[i3] + subRasterOffset; 
/*      */         } 
/* 1245 */         Object mlibDataArray = null;
/* 1246 */         switch (getDataType()) {
/*      */           case 0:
/* 1248 */             bArray = new byte[this.numBands][];
/* 1249 */             for (i = 0; i < this.numBands; i++)
/* 1250 */               bArray[i] = this.mlimages[0].getByteData(); 
/* 1252 */             mlibDataArray = bArray;
/*      */             break;
/*      */           case 1:
/* 1255 */             usArray = new short[this.numBands][];
/* 1256 */             for (j = 0; j < this.numBands; j++)
/* 1257 */               usArray[j] = this.mlimages[0].getUShortData(); 
/* 1259 */             mlibDataArray = usArray;
/*      */             break;
/*      */           case 2:
/* 1262 */             sArray = new short[this.numBands][];
/* 1263 */             for (k = 0; k < this.numBands; k++)
/* 1264 */               sArray[k] = this.mlimages[0].getShortData(); 
/* 1266 */             mlibDataArray = sArray;
/*      */             break;
/*      */           case 3:
/* 1269 */             iArray = new int[this.numBands][];
/* 1270 */             for (n = 0; n < this.numBands; n++)
/* 1271 */               iArray[n] = this.mlimages[0].getIntData(); 
/* 1273 */             mlibDataArray = iArray;
/*      */             break;
/*      */           case 4:
/* 1276 */             fArray = new float[this.numBands][];
/* 1277 */             for (i1 = 0; i1 < this.numBands; i1++)
/* 1278 */               fArray[i1] = this.mlimages[0].getFloatData(); 
/* 1280 */             mlibDataArray = fArray;
/*      */             break;
/*      */           case 5:
/* 1283 */             dArray = new double[this.numBands][];
/* 1284 */             for (i2 = 0; i2 < this.numBands; i2++)
/* 1285 */               dArray[i2] = this.mlimages[0].getDoubleData(); 
/* 1287 */             mlibDataArray = dArray;
/*      */             break;
/*      */         } 
/* 1292 */         byte[] tmpDataArray = null;
/* 1293 */         Object rasDataArray = null;
/* 1294 */         switch (csm.getDataType()) {
/*      */           case 0:
/* 1296 */             dbb = (DataBufferByte)this.raster.getDataBuffer();
/* 1298 */             rasByteDataArray = new byte[this.numBands][];
/* 1299 */             for (m = 0; m < this.numBands; m++)
/* 1300 */               rasByteDataArray[m] = dbb.getData(rasBankIndices[m]); 
/* 1303 */             tmpDataArray = rasByteDataArray[0];
/* 1304 */             rasDataArray = rasByteDataArray;
/*      */             break;
/*      */           case 1:
/* 1308 */             dbus = (DataBufferUShort)this.raster.getDataBuffer();
/* 1310 */             rasUShortDataArray = new short[this.numBands][];
/* 1311 */             for (m = 0; m < this.numBands; m++)
/* 1312 */               rasUShortDataArray[m] = dbus.getData(rasBankIndices[m]); 
/* 1315 */             rasDataArray = rasUShortDataArray;
/*      */             break;
/*      */           case 2:
/* 1319 */             dbs = (DataBufferShort)this.raster.getDataBuffer();
/* 1321 */             rasShortDataArray = new short[this.numBands][];
/* 1322 */             for (m = 0; m < this.numBands; m++)
/* 1323 */               rasShortDataArray[m] = dbs.getData(rasBankIndices[m]); 
/* 1326 */             rasDataArray = rasShortDataArray;
/*      */             break;
/*      */           case 3:
/* 1330 */             dbi = (DataBufferInt)this.raster.getDataBuffer();
/* 1332 */             rasIntDataArray = new int[this.numBands][];
/* 1333 */             for (m = 0; m < this.numBands; m++)
/* 1334 */               rasIntDataArray[m] = dbi.getData(rasBankIndices[m]); 
/* 1337 */             rasDataArray = rasIntDataArray;
/*      */             break;
/*      */           case 4:
/* 1341 */             dbf = this.raster.getDataBuffer();
/* 1343 */             rasFloatDataArray = new float[this.numBands][];
/* 1344 */             for (m = 0; m < this.numBands; m++)
/* 1345 */               rasFloatDataArray[m] = DataBufferUtils.getDataFloat(dbf, rasBankIndices[m]); 
/* 1348 */             rasDataArray = rasFloatDataArray;
/*      */             break;
/*      */           case 5:
/* 1352 */             dbd = this.raster.getDataBuffer();
/* 1354 */             rasDoubleDataArray = new double[this.numBands][];
/* 1355 */             for (m = 0; m < this.numBands; m++)
/* 1356 */               rasDoubleDataArray[m] = DataBufferUtils.getDataDouble(dbd, rasBankIndices[m]); 
/* 1359 */             rasDataArray = rasDoubleDataArray;
/*      */             break;
/*      */         } 
/* 1366 */         Image.Reformat(rasDataArray, mlibDataArray, this.numBands, this.rect.width, this.rect.height, getMediaLibDataType(csm.getDataType()), rasBandOffsets, rasScanlineStride, rasPixelStride, getMediaLibDataType(getDataType()), this.bandOffsets, this.rect.width * this.numBands, this.numBands);
/*      */       } else {
/* 1382 */         switch (getDataType()) {
/*      */           case 3:
/* 1384 */             wr.setPixels(this.rect.x, this.rect.y, this.rect.width, this.rect.height, this.mlimages[0].getIntData());
/*      */             break;
/*      */           case 4:
/* 1389 */             wr.setPixels(this.rect.x, this.rect.y, this.rect.width, this.rect.height, this.mlimages[0].getFloatData());
/*      */             break;
/*      */           case 5:
/* 1394 */             wr.setPixels(this.rect.x, this.rect.y, this.rect.width, this.rect.height, this.mlimages[0].getDoubleData());
/*      */             break;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public void clampDataArrays() {
/* 1417 */     if (!isDataCopy())
/*      */       return; 
/* 1425 */     if (this.raster.getSampleModel() instanceof ComponentSampleModel)
/*      */       return; 
/* 1429 */     int[] bits = this.raster.getSampleModel().getSampleSize();
/* 1437 */     boolean needClamp = false;
/* 1438 */     boolean uniformBitSize = true;
/* 1439 */     for (int i = 0; i < bits.length; i++) {
/* 1440 */       int bitSize = bits[0];
/* 1441 */       if (bits[i] < 32)
/* 1442 */         needClamp = true; 
/* 1444 */       if (bits[i] != bitSize)
/* 1445 */         uniformBitSize = false; 
/*      */     } 
/* 1449 */     if (!needClamp)
/*      */       return; 
/* 1453 */     int dataType = this.raster.getDataBuffer().getDataType();
/* 1454 */     double[] hiVals = new double[bits.length];
/* 1455 */     double[] loVals = new double[bits.length];
/* 1457 */     if (dataType == 1 && uniformBitSize && bits[0] == 16) {
/* 1459 */       for (int j = 0; j < bits.length; j++) {
/* 1460 */         hiVals[j] = 65535.0D;
/* 1461 */         loVals[j] = 0.0D;
/*      */       } 
/* 1463 */     } else if (dataType == 2 && uniformBitSize && bits[0] == 16) {
/* 1465 */       for (int j = 0; j < bits.length; j++) {
/* 1466 */         hiVals[j] = 32767.0D;
/* 1467 */         loVals[j] = -32768.0D;
/*      */       } 
/* 1469 */     } else if (dataType == 3 && uniformBitSize && bits[0] == 32) {
/* 1471 */       for (int j = 0; j < bits.length; j++) {
/* 1472 */         hiVals[j] = 2.147483647E9D;
/* 1473 */         loVals[j] = -2.147483648E9D;
/*      */       } 
/*      */     } else {
/* 1476 */       for (int j = 0; j < bits.length; j++) {
/* 1477 */         hiVals[j] = ((1 << bits[j]) - 1);
/* 1478 */         loVals[j] = 0.0D;
/*      */       } 
/*      */     } 
/* 1481 */     clampDataArray(hiVals, loVals);
/*      */   }
/*      */   
/*      */   private void clampDataArray(double[] hiVals, double[] loVals) {
/* 1485 */     switch (getDataType()) {
/*      */       case 3:
/* 1487 */         clampIntArrays(toIntArray(hiVals), toIntArray(loVals));
/*      */         break;
/*      */       case 4:
/* 1490 */         clampFloatArrays(toFloatArray(hiVals), toFloatArray(loVals));
/*      */         break;
/*      */       case 5:
/* 1493 */         clampDoubleArrays(hiVals, loVals);
/*      */         break;
/*      */     } 
/*      */   }
/*      */   
/*      */   private int[] toIntArray(double[] vals) {
/* 1499 */     int[] returnVals = new int[vals.length];
/* 1500 */     for (int i = 0; i < vals.length; i++)
/* 1501 */       returnVals[i] = (int)vals[i]; 
/* 1503 */     return returnVals;
/*      */   }
/*      */   
/*      */   private float[] toFloatArray(double[] vals) {
/* 1507 */     float[] returnVals = new float[vals.length];
/* 1508 */     for (int i = 0; i < vals.length; i++)
/* 1509 */       returnVals[i] = (float)vals[i]; 
/* 1511 */     return returnVals;
/*      */   }
/*      */   
/*      */   private void clampIntArrays(int[] hiVals, int[] loVals) {
/* 1515 */     int width = this.rect.width;
/* 1516 */     int height = this.rect.height;
/* 1517 */     int scanlineStride = this.numBands * width;
/* 1518 */     for (int k = 0; k < this.numBands; k++) {
/* 1519 */       int[] data = this.mlimages[0].getIntData();
/* 1520 */       int scanlineOffset = k;
/* 1521 */       int hiVal = hiVals[k];
/* 1522 */       int loVal = loVals[k];
/* 1523 */       for (int j = 0; j < height; j++) {
/* 1524 */         int pixelOffset = scanlineOffset;
/* 1525 */         for (int i = 0; i < width; i++) {
/* 1526 */           int tmp = data[pixelOffset];
/* 1527 */           if (tmp < loVal) {
/* 1528 */             data[pixelOffset] = loVal;
/* 1529 */           } else if (tmp > hiVal) {
/* 1530 */             data[pixelOffset] = hiVal;
/*      */           } 
/* 1532 */           pixelOffset += this.numBands;
/*      */         } 
/* 1534 */         scanlineOffset += scanlineStride;
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void clampFloatArrays(float[] hiVals, float[] loVals) {
/* 1540 */     int width = this.rect.width;
/* 1541 */     int height = this.rect.height;
/* 1542 */     int scanlineStride = this.numBands * width;
/* 1543 */     for (int k = 0; k < this.numBands; k++) {
/* 1544 */       float[] data = this.mlimages[0].getFloatData();
/* 1545 */       int scanlineOffset = k;
/* 1546 */       float hiVal = hiVals[k];
/* 1547 */       float loVal = loVals[k];
/* 1548 */       for (int j = 0; j < height; j++) {
/* 1549 */         int pixelOffset = scanlineOffset;
/* 1550 */         for (int i = 0; i < width; i++) {
/* 1551 */           float tmp = data[pixelOffset];
/* 1552 */           if (tmp < loVal) {
/* 1553 */             data[pixelOffset] = loVal;
/* 1554 */           } else if (tmp > hiVal) {
/* 1555 */             data[pixelOffset] = hiVal;
/*      */           } 
/* 1557 */           pixelOffset += this.numBands;
/*      */         } 
/* 1559 */         scanlineOffset += scanlineStride;
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void clampDoubleArrays(double[] hiVals, double[] loVals) {
/* 1565 */     int width = this.rect.width;
/* 1566 */     int height = this.rect.height;
/* 1567 */     int scanlineStride = this.numBands * width;
/* 1568 */     for (int k = 0; k < this.numBands; k++) {
/* 1569 */       double[] data = this.mlimages[0].getDoubleData();
/* 1570 */       int scanlineOffset = k;
/* 1571 */       double hiVal = hiVals[k];
/* 1572 */       double loVal = loVals[k];
/* 1573 */       for (int j = 0; j < height; j++) {
/* 1574 */         int pixelOffset = scanlineOffset;
/* 1575 */         for (int i = 0; i < width; i++) {
/* 1576 */           double tmp = data[pixelOffset];
/* 1577 */           if (tmp < loVal) {
/* 1578 */             data[pixelOffset] = loVal;
/* 1579 */           } else if (tmp > hiVal) {
/* 1580 */             data[pixelOffset] = hiVal;
/*      */           } 
/* 1582 */           pixelOffset += this.numBands;
/*      */         } 
/* 1584 */         scanlineOffset += scanlineStride;
/*      */       } 
/*      */     } 
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\mlib\MediaLibAccessor.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */