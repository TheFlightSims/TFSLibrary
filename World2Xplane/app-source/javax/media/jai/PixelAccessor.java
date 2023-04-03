/*      */ package javax.media.jai;
/*      */ 
/*      */ import com.sun.media.jai.util.DataBufferUtils;
/*      */ import com.sun.media.jai.util.ImageUtil;
/*      */ import com.sun.media.jai.util.JDKWorkarounds;
/*      */ import java.awt.Rectangle;
/*      */ import java.awt.image.ColorModel;
/*      */ import java.awt.image.ComponentSampleModel;
/*      */ import java.awt.image.DataBuffer;
/*      */ import java.awt.image.DataBufferByte;
/*      */ import java.awt.image.DataBufferInt;
/*      */ import java.awt.image.DataBufferShort;
/*      */ import java.awt.image.DataBufferUShort;
/*      */ import java.awt.image.IndexColorModel;
/*      */ import java.awt.image.MultiPixelPackedSampleModel;
/*      */ import java.awt.image.Raster;
/*      */ import java.awt.image.RenderedImage;
/*      */ import java.awt.image.SampleModel;
/*      */ import java.awt.image.WritableRaster;
/*      */ import java.util.Vector;
/*      */ 
/*      */ public final class PixelAccessor {
/*      */   public static final int TYPE_BIT = -1;
/*      */   
/*      */   public final SampleModel sampleModel;
/*      */   
/*      */   public final ColorModel colorModel;
/*      */   
/*      */   public final boolean isComponentSM;
/*      */   
/*      */   public final boolean isMultiPixelPackedSM;
/*      */   
/*      */   public final boolean isSinglePixelPackedSM;
/*      */   
/*      */   public final int sampleType;
/*      */   
/*      */   public final int bufferType;
/*      */   
/*      */   public final int transferType;
/*      */   
/*      */   public final int numBands;
/*      */   
/*      */   public final int[] sampleSize;
/*      */   
/*      */   public final boolean isPacked;
/*      */   
/*      */   public final boolean hasCompatibleCM;
/*      */   
/*      */   public final boolean isComponentCM;
/*      */   
/*      */   public final boolean isIndexCM;
/*      */   
/*      */   public final boolean isPackedCM;
/*      */   
/*      */   public final int componentType;
/*      */   
/*      */   public final int numComponents;
/*      */   
/*      */   public final int[] componentSize;
/*      */   
/*      */   private static SampleModel getSampleModel(RenderedImage image) {
/*  233 */     if (image == null)
/*  234 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*  236 */     return image.getSampleModel();
/*      */   }
/*      */   
/*      */   public PixelAccessor(RenderedImage image) {
/*  253 */     this(getSampleModel(image), image.getColorModel());
/*      */   }
/*      */   
/*      */   public PixelAccessor(SampleModel sm, ColorModel cm) {
/*  269 */     if (sm == null)
/*  270 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*  273 */     this.sampleModel = sm;
/*  274 */     this.colorModel = cm;
/*  277 */     this.isComponentSM = this.sampleModel instanceof ComponentSampleModel;
/*  278 */     this.isMultiPixelPackedSM = this.sampleModel instanceof MultiPixelPackedSampleModel;
/*  280 */     this.isSinglePixelPackedSM = this.sampleModel instanceof java.awt.image.SinglePixelPackedSampleModel;
/*  283 */     this.bufferType = this.sampleModel.getDataType();
/*  284 */     this.transferType = this.sampleModel.getTransferType();
/*  285 */     this.numBands = this.sampleModel.getNumBands();
/*  286 */     this.sampleSize = this.sampleModel.getSampleSize();
/*  287 */     this.sampleType = this.isComponentSM ? this.bufferType : getType(this.sampleSize);
/*  290 */     this.isPacked = (this.sampleType == -1 && this.numBands == 1);
/*  293 */     this.hasCompatibleCM = (this.colorModel != null && JDKWorkarounds.areCompatibleDataModels(this.sampleModel, this.colorModel));
/*  296 */     if (this.hasCompatibleCM) {
/*  297 */       this.isComponentCM = this.colorModel instanceof java.awt.image.ComponentColorModel;
/*  298 */       this.isIndexCM = this.colorModel instanceof IndexColorModel;
/*  299 */       this.isPackedCM = this.colorModel instanceof java.awt.image.PackedColorModel;
/*  301 */       this.numComponents = this.colorModel.getNumComponents();
/*  302 */       this.componentSize = this.colorModel.getComponentSize();
/*  303 */       int tempType = getType(this.componentSize);
/*  305 */       this.componentType = (tempType == -1) ? 0 : tempType;
/*      */     } else {
/*  308 */       this.isComponentCM = false;
/*  309 */       this.isIndexCM = false;
/*  310 */       this.isPackedCM = false;
/*  311 */       this.numComponents = this.numBands;
/*  312 */       this.componentSize = this.sampleSize;
/*  313 */       this.componentType = this.sampleType;
/*      */     } 
/*      */   }
/*      */   
/*      */   private static int getType(int[] size) {
/*  328 */     int type, maxSize = size[0];
/*  329 */     for (int i = 1; i < size.length; i++)
/*  330 */       maxSize = Math.max(maxSize, size[i]); 
/*  334 */     if (maxSize < 1) {
/*  335 */       type = 32;
/*  336 */     } else if (maxSize == 1) {
/*  337 */       type = -1;
/*  338 */     } else if (maxSize <= 8) {
/*  339 */       type = 0;
/*  340 */     } else if (maxSize <= 16) {
/*  341 */       type = 1;
/*  342 */     } else if (maxSize <= 32) {
/*  343 */       type = 3;
/*  344 */     } else if (maxSize <= 64) {
/*  345 */       type = 5;
/*      */     } else {
/*  347 */       type = 32;
/*      */     } 
/*  349 */     return type;
/*      */   }
/*      */   
/*      */   public static int getPixelType(SampleModel sm) {
/*  364 */     return (sm instanceof ComponentSampleModel) ? sm.getDataType() : getType(sm.getSampleSize());
/*      */   }
/*      */   
/*      */   public static int getDestPixelType(Vector sources) {
/*  402 */     if (sources == null)
/*  403 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*  406 */     int type = 32;
/*  407 */     int size = sources.size();
/*  409 */     if (size > 0) {
/*  410 */       RenderedImage src = sources.get(0);
/*  411 */       SampleModel sm = src.getSampleModel();
/*  413 */       type = getPixelType(sm);
/*  415 */       for (int i = 1; i < size; i++) {
/*  416 */         src = sources.get(i);
/*  417 */         sm = src.getSampleModel();
/*  419 */         int t = getPixelType(sm);
/*  422 */         type = ((type == 1 && t == 2) || (type == 2 && t == 1)) ? 3 : Math.max(type, t);
/*      */       } 
/*      */     } 
/*  429 */     return type;
/*      */   }
/*      */   
/*      */   public static int getDestNumBands(Vector sources) {
/*  463 */     if (sources == null)
/*  464 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*  467 */     int bands = 0;
/*  468 */     int size = sources.size();
/*  470 */     if (size > 0) {
/*  471 */       RenderedImage src = sources.get(0);
/*  472 */       SampleModel sm = src.getSampleModel();
/*  474 */       bands = sm.getNumBands();
/*  476 */       for (int i = 1; i < size; i++) {
/*  477 */         src = sources.get(i);
/*  478 */         sm = src.getSampleModel();
/*  480 */         int b = sm.getNumBands();
/*  482 */         bands = (bands == 1 || b == 1) ? Math.max(bands, b) : Math.min(bands, b);
/*      */       } 
/*      */     } 
/*  486 */     return bands;
/*      */   }
/*      */   
/*      */   public static boolean isPackedOperation(PixelAccessor[] srcs, PixelAccessor dst) {
/*  500 */     boolean canBePacked = dst.isPacked;
/*  501 */     if (canBePacked && srcs != null)
/*  502 */       for (int i = 0; i < srcs.length; i++) {
/*  503 */         canBePacked = (canBePacked && (srcs[i]).isPacked);
/*  504 */         if (!canBePacked)
/*      */           break; 
/*      */       }  
/*  509 */     return canBePacked;
/*      */   }
/*      */   
/*      */   public static boolean isPackedOperation(PixelAccessor src, PixelAccessor dst) {
/*  523 */     return (src.isPacked && dst.isPacked);
/*      */   }
/*      */   
/*      */   public static boolean isPackedOperation(PixelAccessor src1, PixelAccessor src2, PixelAccessor dst) {
/*  539 */     return (src1.isPacked && src2.isPacked && dst.isPacked);
/*      */   }
/*      */   
/*      */   public UnpackedImageData getPixels(Raster raster, Rectangle rect, int type, boolean isDest) {
/*      */     byte[] bd;
/*      */     short[] usd, sd;
/*  586 */     if (!raster.getBounds().contains(rect))
/*  587 */       throw new IllegalArgumentException(JaiI18N.getString("PixelAccessor0")); 
/*  591 */     if (type < 0 || type > 5)
/*  593 */       throw new IllegalArgumentException(JaiI18N.getString("PixelAccessor1")); 
/*  597 */     if (type < this.sampleType || (this.sampleType == 1 && type == 2))
/*  600 */       throw new IllegalArgumentException(JaiI18N.getString("PixelAccessor2")); 
/*  604 */     if (this.isComponentSM)
/*  605 */       return getPixelsCSM(raster, rect, type, isDest); 
/*  609 */     int size = rect.width * rect.height * this.numBands;
/*  611 */     Object data = null;
/*  613 */     switch (type) {
/*      */       case 0:
/*  617 */         if (isDest) {
/*  618 */           bd = new byte[size];
/*  620 */         } else if (this.isMultiPixelPackedSM && this.transferType == 0) {
/*  622 */           bd = (byte[])raster.getDataElements(rect.x, rect.y, rect.width, rect.height, null);
/*      */         } else {
/*  626 */           bd = new byte[size];
/*  627 */           int[] d = raster.getPixels(rect.x, rect.y, rect.width, rect.height, (int[])null);
/*  629 */           for (int i = 0; i < size; i++)
/*  630 */             bd[i] = (byte)(d[i] & 0xFF); 
/*      */         } 
/*  635 */         data = repeatBand(bd, this.numBands);
/*      */         break;
/*      */       case 1:
/*  641 */         if (isDest) {
/*  642 */           usd = new short[size];
/*  644 */         } else if (this.isMultiPixelPackedSM && this.transferType == 1) {
/*  646 */           usd = (short[])raster.getDataElements(rect.x, rect.y, rect.width, rect.height, null);
/*      */         } else {
/*  650 */           usd = new short[size];
/*  651 */           int[] d = raster.getPixels(rect.x, rect.y, rect.width, rect.height, (int[])null);
/*  653 */           for (int i = 0; i < size; i++)
/*  654 */             usd[i] = (short)(d[i] & 0xFFFF); 
/*      */         } 
/*  659 */         data = repeatBand(usd, this.numBands);
/*      */         break;
/*      */       case 2:
/*  663 */         sd = new short[size];
/*  665 */         if (!isDest) {
/*  666 */           int[] d = raster.getPixels(rect.x, rect.y, rect.width, rect.height, (int[])null);
/*  668 */           for (int i = 0; i < size; i++)
/*  669 */             sd[i] = (short)d[i]; 
/*      */         } 
/*  673 */         data = repeatBand(sd, this.numBands);
/*      */         break;
/*      */       case 3:
/*  677 */         return getPixelsInt(raster, rect, isDest);
/*      */       case 4:
/*  680 */         return getPixelsFloat(raster, rect, isDest);
/*      */       case 5:
/*  683 */         return getPixelsDouble(raster, rect, isDest);
/*      */     } 
/*  686 */     return new UnpackedImageData(raster, rect, type, data, this.numBands, this.numBands * rect.width, getInterleavedOffsets(this.numBands), isDest & raster instanceof WritableRaster);
/*      */   }
/*      */   
/*      */   private UnpackedImageData getPixelsCSM(Raster raster, Rectangle rect, int type, boolean isDest) {
/*      */     int pixelStride, lineStride, offsets[];
/*      */     boolean set;
/*  704 */     Object data = null;
/*  712 */     ComponentSampleModel sm = (ComponentSampleModel)raster.getSampleModel();
/*  714 */     if (type == this.sampleType) {
/*      */       byte[][] bbd, bd;
/*      */       int b;
/*      */       short[][] sbd, sd;
/*      */       int j, ibd[][], id[][], k;
/*      */       float[][] fbd, fd;
/*      */       int m;
/*      */       double[][] dbd, dd;
/*      */       int n;
/*  717 */       DataBuffer db = raster.getDataBuffer();
/*  718 */       int[] bankIndices = sm.getBankIndices();
/*  720 */       switch (this.sampleType) {
/*      */         case 0:
/*  722 */           bbd = ((DataBufferByte)db).getBankData();
/*  723 */           bd = new byte[this.numBands][];
/*  725 */           for (b = 0; b < this.numBands; b++)
/*  726 */             bd[b] = bbd[bankIndices[b]]; 
/*  728 */           data = bd;
/*      */           break;
/*      */         case 1:
/*      */         case 2:
/*  733 */           sbd = (this.sampleType == 1) ? ((DataBufferUShort)db).getBankData() : ((DataBufferShort)db).getBankData();
/*  736 */           sd = new short[this.numBands][];
/*  738 */           for (j = 0; j < this.numBands; j++)
/*  739 */             sd[j] = sbd[bankIndices[j]]; 
/*  741 */           data = sd;
/*      */           break;
/*      */         case 3:
/*  745 */           ibd = ((DataBufferInt)db).getBankData();
/*  746 */           id = new int[this.numBands][];
/*  748 */           for (k = 0; k < this.numBands; k++)
/*  749 */             id[k] = ibd[bankIndices[k]]; 
/*  751 */           data = id;
/*      */           break;
/*      */         case 4:
/*  755 */           fbd = DataBufferUtils.getBankDataFloat(db);
/*  756 */           fd = new float[this.numBands][];
/*  758 */           for (m = 0; m < this.numBands; m++)
/*  759 */             fd[m] = fbd[bankIndices[m]]; 
/*  761 */           data = fd;
/*      */           break;
/*      */         case 5:
/*  765 */           dbd = DataBufferUtils.getBankDataDouble(db);
/*  766 */           dd = new double[this.numBands][];
/*  768 */           for (n = 0; n < this.numBands; n++)
/*  769 */             dd[n] = dbd[bankIndices[n]]; 
/*  771 */           data = dd;
/*      */           break;
/*      */       } 
/*  775 */       pixelStride = sm.getPixelStride();
/*  776 */       lineStride = sm.getScanlineStride();
/*  779 */       int[] dbOffsets = db.getOffsets();
/*  780 */       int x = rect.x - raster.getSampleModelTranslateX();
/*  781 */       int y = rect.y - raster.getSampleModelTranslateY();
/*  783 */       offsets = new int[this.numBands];
/*  784 */       for (int i = 0; i < this.numBands; i++)
/*  785 */         offsets[i] = sm.getOffset(x, y, i) + dbOffsets[bankIndices[i]]; 
/*  788 */       set = false;
/*      */     } else {
/*  791 */       switch (type) {
/*      */         case 3:
/*  793 */           return getPixelsInt(raster, rect, isDest);
/*      */         case 4:
/*  796 */           return getPixelsFloat(raster, rect, isDest);
/*      */         case 5:
/*  799 */           return getPixelsDouble(raster, rect, isDest);
/*      */       } 
/*  813 */       int size = rect.width * rect.height * this.numBands;
/*  815 */       short[] sd = new short[size];
/*  817 */       if (!isDest) {
/*  819 */         UnpackedImageData uid = getPixelsCSM(raster, rect, this.sampleType, isDest);
/*  821 */         byte[][] bdata = uid.getByteData();
/*  823 */         for (int b = 0; b < this.numBands; b++) {
/*  824 */           byte[] bd = bdata[b];
/*  825 */           int lo = uid.getOffset(b);
/*  827 */           for (int i = b, h = 0; h < rect.height; h++) {
/*  828 */             int po = lo;
/*  829 */             lo += uid.lineStride;
/*  831 */             for (int w = 0; w < rect.width; w++) {
/*  832 */               sd[i] = (short)(bd[po] & 0xFF);
/*  834 */               po += uid.pixelStride;
/*  835 */               i += this.numBands;
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*  841 */       data = repeatBand(sd, this.numBands);
/*  845 */       pixelStride = this.numBands;
/*  846 */       lineStride = pixelStride * rect.width;
/*  847 */       offsets = getInterleavedOffsets(this.numBands);
/*  848 */       set = isDest & raster instanceof WritableRaster;
/*      */     } 
/*  851 */     return new UnpackedImageData(raster, rect, type, data, pixelStride, lineStride, offsets, set);
/*      */   }
/*      */   
/*      */   private UnpackedImageData getPixelsInt(Raster raster, Rectangle rect, boolean isDest) {
/*  866 */     int size = rect.width * rect.height * this.numBands;
/*  874 */     int[] d = isDest ? new int[size] : raster.getPixels(rect.x, rect.y, rect.width, rect.height, (int[])null);
/*  878 */     return new UnpackedImageData(raster, rect, 3, repeatBand(d, this.numBands), this.numBands, this.numBands * rect.width, getInterleavedOffsets(this.numBands), isDest & raster instanceof WritableRaster);
/*      */   }
/*      */   
/*      */   private UnpackedImageData getPixelsFloat(Raster raster, Rectangle rect, boolean isDest) {
/*  895 */     int size = rect.width * rect.height * this.numBands;
/*  903 */     float[] d = isDest ? new float[size] : raster.getPixels(rect.x, rect.y, rect.width, rect.height, (float[])null);
/*  907 */     return new UnpackedImageData(raster, rect, 4, repeatBand(d, this.numBands), this.numBands, this.numBands * rect.width, getInterleavedOffsets(this.numBands), isDest & raster instanceof WritableRaster);
/*      */   }
/*      */   
/*      */   private UnpackedImageData getPixelsDouble(Raster raster, Rectangle rect, boolean isDest) {
/*  924 */     int size = rect.width * rect.height * this.numBands;
/*  932 */     double[] d = isDest ? new double[size] : raster.getPixels(rect.x, rect.y, rect.width, rect.height, (double[])null);
/*  936 */     return new UnpackedImageData(raster, rect, 5, repeatBand(d, this.numBands), this.numBands, this.numBands * rect.width, getInterleavedOffsets(this.numBands), isDest & raster instanceof WritableRaster);
/*      */   }
/*      */   
/*      */   private byte[][] repeatBand(byte[] d, int numBands) {
/*  946 */     byte[][] data = new byte[numBands][];
/*  947 */     for (int i = 0; i < numBands; i++)
/*  948 */       data[i] = d; 
/*  950 */     return data;
/*      */   }
/*      */   
/*      */   private short[][] repeatBand(short[] d, int numBands) {
/*  954 */     short[][] data = new short[numBands][];
/*  955 */     for (int i = 0; i < numBands; i++)
/*  956 */       data[i] = d; 
/*  958 */     return data;
/*      */   }
/*      */   
/*      */   private int[][] repeatBand(int[] d, int numBands) {
/*  962 */     int[][] data = new int[numBands][];
/*  963 */     for (int i = 0; i < numBands; i++)
/*  964 */       data[i] = d; 
/*  966 */     return data;
/*      */   }
/*      */   
/*      */   private float[][] repeatBand(float[] d, int numBands) {
/*  970 */     float[][] data = new float[numBands][];
/*  971 */     for (int i = 0; i < numBands; i++)
/*  972 */       data[i] = d; 
/*  974 */     return data;
/*      */   }
/*      */   
/*      */   private double[][] repeatBand(double[] d, int numBands) {
/*  978 */     double[][] data = new double[numBands][];
/*  979 */     for (int i = 0; i < numBands; i++)
/*  980 */       data[i] = d; 
/*  982 */     return data;
/*      */   }
/*      */   
/*      */   private int[] getInterleavedOffsets(int numBands) {
/*  987 */     int[] offsets = new int[numBands];
/*  988 */     for (int i = 0; i < numBands; i++)
/*  989 */       offsets[i] = i; 
/*  991 */     return offsets;
/*      */   }
/*      */   
/*      */   public void setPixels(UnpackedImageData uid) {
/* 1009 */     if (uid == null)
/* 1010 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 1013 */     setPixels(uid, true);
/*      */   }
/*      */   
/*      */   public void setPixels(UnpackedImageData uid, boolean clamp) {
/*      */     byte[] bd;
/*      */     int size;
/*      */     short[] sd;
/*      */     int d[], j, i, arrayOfInt1[];
/* 1033 */     if (uid == null)
/* 1034 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 1037 */     if (!uid.convertToDest)
/*      */       return; 
/* 1041 */     if (clamp)
/* 1042 */       switch (this.sampleType) {
/*      */         case 0:
/* 1044 */           clampByte(uid.data, uid.type);
/*      */           break;
/*      */         case 1:
/* 1047 */           clampUShort(uid.data, uid.type);
/*      */           break;
/*      */         case 2:
/* 1050 */           clampShort(uid.data, uid.type);
/*      */           break;
/*      */         case 3:
/* 1053 */           clampInt(uid.data, uid.type);
/*      */           break;
/*      */         case 4:
/* 1056 */           clampFloat(uid.data, uid.type);
/*      */           break;
/*      */       }  
/* 1061 */     WritableRaster raster = (WritableRaster)uid.raster;
/* 1062 */     Rectangle rect = uid.rect;
/* 1063 */     int type = uid.type;
/* 1065 */     switch (type) {
/*      */       case 0:
/* 1067 */         bd = uid.getByteData(0);
/* 1069 */         if (this.isMultiPixelPackedSM && this.transferType == 0) {
/* 1071 */           raster.setDataElements(rect.x, rect.y, rect.width, rect.height, bd);
/*      */           break;
/*      */         } 
/* 1074 */         size = bd.length;
/* 1075 */         d = new int[size];
/* 1076 */         for (i = 0; i < size; i++)
/* 1077 */           d[i] = bd[i] & 0xFF; 
/* 1079 */         raster.setPixels(rect.x, rect.y, rect.width, rect.height, d);
/*      */         break;
/*      */       case 1:
/*      */       case 2:
/* 1085 */         sd = uid.getShortData(0);
/* 1087 */         if (this.isComponentSM) {
/* 1089 */           UnpackedImageData buid = getPixelsCSM(raster, rect, 0, true);
/* 1092 */           byte[][] bdata = buid.getByteData();
/* 1094 */           for (int b = 0; b < this.numBands; b++) {
/* 1095 */             byte[] arrayOfByte = bdata[b];
/* 1096 */             int lo = buid.getOffset(b);
/* 1098 */             for (int k = b, h = 0; h < rect.height; h++) {
/* 1099 */               int po = lo;
/* 1100 */               lo += buid.lineStride;
/* 1102 */               for (int w = 0; w < rect.width; w++) {
/* 1103 */                 arrayOfByte[po] = (byte)sd[k];
/* 1105 */                 po += buid.pixelStride;
/* 1106 */                 k += this.numBands;
/*      */               } 
/*      */             } 
/*      */           } 
/*      */           break;
/*      */         } 
/* 1110 */         if (this.isMultiPixelPackedSM && this.transferType == 1) {
/* 1112 */           raster.setDataElements(rect.x, rect.y, rect.width, rect.height, sd);
/*      */           break;
/*      */         } 
/* 1115 */         j = sd.length;
/* 1116 */         arrayOfInt1 = new int[j];
/* 1117 */         if (type == 1) {
/* 1118 */           for (int k = 0; k < j; k++)
/* 1119 */             arrayOfInt1[k] = sd[k] & 0xFFFF; 
/*      */         } else {
/* 1122 */           for (int k = 0; k < j; k++)
/* 1123 */             arrayOfInt1[k] = sd[k]; 
/*      */         } 
/* 1126 */         raster.setPixels(rect.x, rect.y, rect.width, rect.height, arrayOfInt1);
/*      */         break;
/*      */       case 3:
/* 1131 */         raster.setPixels(rect.x, rect.y, rect.width, rect.height, uid.getIntData(0));
/*      */         break;
/*      */       case 4:
/* 1136 */         raster.setPixels(rect.x, rect.y, rect.width, rect.height, uid.getFloatData(0));
/*      */         break;
/*      */       case 5:
/* 1141 */         raster.setPixels(rect.x, rect.y, rect.width, rect.height, uid.getDoubleData(0));
/*      */         break;
/*      */     } 
/*      */   }
/*      */   
/*      */   private void clampByte(Object data, int type) {
/*      */     int bands;
/*      */     short[][] usd;
/*      */     int j;
/*      */     short[][] sd;
/*      */     int i;
/*      */     int[][] id;
/*      */     int k;
/*      */     float[][] fd;
/*      */     int m;
/*      */     double[][] dd;
/*      */     int n;
/* 1150 */     switch (type) {
/*      */       case 1:
/* 1152 */         usd = (short[][])data;
/* 1153 */         bands = usd.length;
/* 1155 */         for (j = 0; j < bands; j++) {
/* 1156 */           short[] d = usd[j];
/* 1157 */           int size = d.length;
/* 1159 */           for (int i1 = 0; i1 < size; i1++) {
/* 1160 */             int i2 = d[i1] & 0xFFFF;
/* 1161 */             d[i1] = (short)((i2 > 255) ? 255 : i2);
/*      */           } 
/*      */         } 
/*      */         break;
/*      */       case 2:
/* 1167 */         sd = (short[][])data;
/* 1168 */         bands = sd.length;
/* 1170 */         for (i = 0; i < bands; i++) {
/* 1171 */           short[] d = sd[i];
/* 1172 */           int size = d.length;
/* 1174 */           for (int i1 = 0; i1 < size; i1++) {
/* 1175 */             int i2 = d[i1];
/* 1176 */             d[i1] = (short)((i2 > 255) ? 255 : ((i2 < 0) ? 0 : i2));
/*      */           } 
/*      */         } 
/*      */         break;
/*      */       case 3:
/* 1182 */         id = (int[][])data;
/* 1183 */         bands = id.length;
/* 1185 */         for (k = 0; k < bands; k++) {
/* 1186 */           int[] d = id[k];
/* 1187 */           int size = d.length;
/* 1189 */           for (int i1 = 0; i1 < size; i1++) {
/* 1190 */             int i2 = d[i1];
/* 1191 */             d[i1] = (i2 > 255) ? 255 : ((i2 < 0) ? 0 : i2);
/*      */           } 
/*      */         } 
/*      */         break;
/*      */       case 4:
/* 1197 */         fd = (float[][])data;
/* 1198 */         bands = fd.length;
/* 1200 */         for (m = 0; m < bands; m++) {
/* 1201 */           float[] d = fd[m];
/* 1202 */           int size = d.length;
/* 1204 */           for (int i1 = 0; i1 < size; i1++) {
/* 1205 */             float f = d[i1];
/* 1206 */             d[i1] = (f > 255.0F) ? 255.0F : ((f < 0.0F) ? 0.0F : f);
/*      */           } 
/*      */         } 
/*      */         break;
/*      */       case 5:
/* 1212 */         dd = (double[][])data;
/* 1213 */         bands = dd.length;
/* 1215 */         for (n = 0; n < bands; n++) {
/* 1216 */           double[] d = dd[n];
/* 1217 */           int size = d.length;
/* 1219 */           for (int i1 = 0; i1 < size; i1++) {
/* 1220 */             double d1 = d[i1];
/* 1221 */             d[i1] = (d1 > 255.0D) ? 255.0D : ((d1 < 0.0D) ? 0.0D : d1);
/*      */           } 
/*      */         } 
/*      */         break;
/*      */     } 
/*      */   }
/*      */   
/*      */   private void clampUShort(Object data, int type) {
/*      */     int bands;
/*      */     int[][] id;
/*      */     int j;
/*      */     float[][] fd;
/*      */     int i;
/*      */     double[][] dd;
/*      */     int k;
/* 1230 */     switch (type) {
/*      */       case 3:
/* 1232 */         id = (int[][])data;
/* 1233 */         bands = id.length;
/* 1235 */         for (j = 0; j < bands; j++) {
/* 1236 */           int[] d = id[j];
/* 1237 */           int size = d.length;
/* 1239 */           for (int m = 0; m < size; m++) {
/* 1240 */             int n = d[m];
/* 1241 */             d[m] = (n > 65535) ? 65535 : ((n < 0) ? 0 : n);
/*      */           } 
/*      */         } 
/*      */         break;
/*      */       case 4:
/* 1247 */         fd = (float[][])data;
/* 1248 */         bands = fd.length;
/* 1250 */         for (i = 0; i < bands; i++) {
/* 1251 */           float[] d = fd[i];
/* 1252 */           int size = d.length;
/* 1254 */           for (int m = 0; m < size; m++) {
/* 1255 */             float n = d[m];
/* 1256 */             d[m] = (n > 65535.0F) ? 65535.0F : ((n < 0.0F) ? 0.0F : n);
/*      */           } 
/*      */         } 
/*      */         break;
/*      */       case 5:
/* 1262 */         dd = (double[][])data;
/* 1263 */         bands = dd.length;
/* 1265 */         for (k = 0; k < bands; k++) {
/* 1266 */           double[] d = dd[k];
/* 1267 */           int size = d.length;
/* 1269 */           for (int m = 0; m < size; m++) {
/* 1270 */             double n = d[m];
/* 1271 */             d[m] = (n > 65535.0D) ? 65535.0D : ((n < 0.0D) ? 0.0D : n);
/*      */           } 
/*      */         } 
/*      */         break;
/*      */     } 
/*      */   }
/*      */   
/*      */   private void clampShort(Object data, int type) {
/*      */     int bands;
/*      */     int[][] id;
/*      */     int j;
/*      */     float[][] fd;
/*      */     int i;
/*      */     double[][] dd;
/*      */     int k;
/* 1280 */     switch (type) {
/*      */       case 3:
/* 1282 */         id = (int[][])data;
/* 1283 */         bands = id.length;
/* 1285 */         for (j = 0; j < bands; j++) {
/* 1286 */           int[] d = id[j];
/* 1287 */           int size = d.length;
/* 1289 */           for (int m = 0; m < size; m++) {
/* 1290 */             int n = d[m];
/* 1291 */             d[m] = (n > 32767) ? 32767 : ((n < -32768) ? -32768 : n);
/*      */           } 
/*      */         } 
/*      */         break;
/*      */       case 4:
/* 1298 */         fd = (float[][])data;
/* 1299 */         bands = fd.length;
/* 1301 */         for (i = 0; i < bands; i++) {
/* 1302 */           float[] d = fd[i];
/* 1303 */           int size = d.length;
/* 1305 */           for (int m = 0; m < size; m++) {
/* 1306 */             float n = d[m];
/* 1307 */             d[m] = (n > 32767.0F) ? 32767.0F : ((n < -32768.0F) ? -32768.0F : n);
/*      */           } 
/*      */         } 
/*      */         break;
/*      */       case 5:
/* 1314 */         dd = (double[][])data;
/* 1315 */         bands = dd.length;
/* 1317 */         for (k = 0; k < bands; k++) {
/* 1318 */           double[] d = dd[k];
/* 1319 */           int size = d.length;
/* 1321 */           for (int m = 0; m < size; m++) {
/* 1322 */             double n = d[m];
/* 1323 */             d[m] = (n > 32767.0D) ? 32767.0D : ((n < -32768.0D) ? -32768.0D : n);
/*      */           } 
/*      */         } 
/*      */         break;
/*      */     } 
/*      */   }
/*      */   
/*      */   private void clampInt(Object data, int type) {
/*      */     int bands;
/*      */     float[][] fd;
/*      */     int j;
/*      */     double[][] dd;
/*      */     int i;
/* 1333 */     switch (type) {
/*      */       case 4:
/* 1335 */         fd = (float[][])data;
/* 1336 */         bands = fd.length;
/* 1338 */         for (j = 0; j < bands; j++) {
/* 1339 */           float[] d = fd[j];
/* 1340 */           int size = d.length;
/* 1342 */           for (int k = 0; k < size; k++) {
/* 1343 */             float n = d[k];
/* 1344 */             d[k] = (n > 2.1474836E9F) ? 2.1474836E9F : ((n < -2.1474836E9F) ? -2.1474836E9F : n);
/*      */           } 
/*      */         } 
/*      */         break;
/*      */       case 5:
/* 1351 */         dd = (double[][])data;
/* 1352 */         bands = dd.length;
/* 1354 */         for (i = 0; i < bands; i++) {
/* 1355 */           double[] d = dd[i];
/* 1356 */           int size = d.length;
/* 1358 */           for (int k = 0; k < size; k++) {
/* 1359 */             double n = d[k];
/* 1360 */             d[k] = (n > 2.147483647E9D) ? 2.147483647E9D : ((n < -2.147483648E9D) ? -2.147483648E9D : n);
/*      */           } 
/*      */         } 
/*      */         break;
/*      */     } 
/*      */   }
/*      */   
/*      */   private void clampFloat(Object data, int type) {
/*      */     int bands;
/*      */     double[][] dd;
/*      */     int j;
/* 1370 */     switch (type) {
/*      */       case 5:
/* 1372 */         dd = (double[][])data;
/* 1373 */         bands = dd.length;
/* 1375 */         for (j = 0; j < bands; j++) {
/* 1376 */           double[] d = dd[j];
/* 1377 */           int size = d.length;
/* 1379 */           for (int i = 0; i < size; i++) {
/* 1380 */             double n = d[i];
/* 1381 */             d[i] = (n > 3.4028234663852886E38D) ? 3.4028234663852886E38D : ((n < -3.4028234663852886E38D) ? -3.4028234663852886E38D : n);
/*      */           } 
/*      */         } 
/*      */         break;
/*      */     } 
/*      */   }
/*      */   
/*      */   public PackedImageData getPackedPixels(Raster raster, Rectangle rect, boolean isDest, boolean coerceZeroOffset) {
/*      */     byte[] data;
/*      */     int lineStride;
/*      */     int offset;
/*      */     int bitOffset;
/*      */     boolean set;
/* 1436 */     if (!this.isPacked)
/* 1437 */       throw new IllegalArgumentException(JaiI18N.getString("PixelAccessor3")); 
/* 1441 */     if (!raster.getBounds().contains(rect))
/* 1442 */       throw new IllegalArgumentException(JaiI18N.getString("PixelAccessor0")); 
/* 1450 */     if (this.isMultiPixelPackedSM) {
/* 1452 */       set = isDest;
/* 1454 */       if (coerceZeroOffset) {
/* 1456 */         data = ImageUtil.getPackedBinaryData(raster, rect);
/* 1457 */         lineStride = (rect.width + 7) / 8;
/* 1458 */         offset = bitOffset = 0;
/*      */       } else {
/*      */         short[] sd;
/*      */         int i, id[], h, j, k;
/* 1462 */         MultiPixelPackedSampleModel sm = (MultiPixelPackedSampleModel)this.sampleModel;
/* 1465 */         DataBuffer db = raster.getDataBuffer();
/* 1466 */         int dbOffset = db.getOffset();
/* 1468 */         int x = rect.x - raster.getSampleModelTranslateX();
/* 1469 */         int y = rect.y - raster.getSampleModelTranslateY();
/* 1471 */         int smLineStride = sm.getScanlineStride();
/* 1472 */         int minOffset = sm.getOffset(x, y) + dbOffset;
/* 1473 */         int maxOffset = sm.getOffset(x + rect.width - 1, y) + dbOffset;
/* 1474 */         int numElements = maxOffset - minOffset + 1;
/* 1475 */         int smBitOffset = sm.getBitOffset(x);
/* 1477 */         switch (this.bufferType) {
/*      */           case 0:
/* 1479 */             data = ((DataBufferByte)db).getData();
/* 1480 */             lineStride = smLineStride;
/* 1481 */             offset = minOffset;
/* 1482 */             bitOffset = smBitOffset;
/* 1483 */             set = false;
/* 1554 */             return new PackedImageData(raster, rect, data, lineStride, offset, bitOffset, coerceZeroOffset, set);
/*      */           case 1:
/*      */             lineStride = numElements * 2;
/*      */             offset = smBitOffset / 8;
/*      */             bitOffset = smBitOffset % 8;
/*      */             data = new byte[lineStride * rect.height];
/*      */             sd = ((DataBufferUShort)db).getData();
/*      */             for (i = 0, h = 0; h < rect.height; h++) {
/*      */               for (int w = minOffset; w <= maxOffset; w++) {
/*      */                 short d = sd[w];
/*      */                 data[i++] = (byte)(d >>> 8 & 0xFF);
/*      */                 data[i++] = (byte)(d & 0xFF);
/*      */               } 
/*      */               minOffset += smLineStride;
/*      */               maxOffset += smLineStride;
/*      */             } 
/* 1554 */             return new PackedImageData(raster, rect, data, lineStride, offset, bitOffset, coerceZeroOffset, set);
/*      */           case 3:
/*      */             lineStride = numElements * 4;
/*      */             offset = smBitOffset / 8;
/*      */             bitOffset = smBitOffset % 8;
/*      */             data = new byte[lineStride * rect.height];
/*      */             id = ((DataBufferInt)db).getData();
/*      */             for (j = 0, k = 0; k < rect.height; k++) {
/*      */               for (int w = minOffset; w <= maxOffset; w++) {
/*      */                 int d = id[w];
/*      */                 data[j++] = (byte)(d >>> 24 & 0xFF);
/*      */                 data[j++] = (byte)(d >>> 16 & 0xFF);
/*      */                 data[j++] = (byte)(d >>> 8 & 0xFF);
/*      */                 data[j++] = (byte)(d & 0xFF);
/*      */               } 
/*      */               minOffset += smLineStride;
/*      */               maxOffset += smLineStride;
/*      */             } 
/* 1554 */             return new PackedImageData(raster, rect, data, lineStride, offset, bitOffset, coerceZeroOffset, set);
/*      */         } 
/*      */         throw new RuntimeException();
/*      */       } 
/*      */     } else {
/*      */       lineStride = (rect.width + 7) / 8;
/*      */       offset = 0;
/*      */       bitOffset = 0;
/*      */       set = isDest & raster instanceof WritableRaster;
/*      */       data = new byte[lineStride * rect.height];
/*      */       if (!isDest) {
/*      */         int size = lineStride * 8;
/*      */         int[] p = new int[size];
/*      */         for (int i = 0, h = 0; h < rect.height; h++) {
/*      */           p = raster.getPixels(rect.x, rect.y + h, rect.width, 1, p);
/*      */           for (int w = 0; w < size; w += 8)
/*      */             data[i++] = (byte)(p[w] << 7 | p[w + 1] << 6 | p[w + 2] << 5 | p[w + 3] << 4 | p[w + 4] << 3 | p[w + 5] << 2 | p[w + 6] << 1 | p[w + 7]); 
/*      */         } 
/*      */       } 
/*      */     } 
/* 1554 */     return new PackedImageData(raster, rect, data, lineStride, offset, bitOffset, coerceZeroOffset, set);
/*      */   }
/*      */   
/*      */   public void setPackedPixels(PackedImageData pid) {
/* 1575 */     if (pid == null)
/* 1576 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 1579 */     if (!pid.convertToDest)
/*      */       return; 
/* 1583 */     Raster raster = pid.raster;
/* 1584 */     Rectangle rect = pid.rect;
/* 1585 */     byte[] data = pid.data;
/* 1587 */     if (this.isMultiPixelPackedSM) {
/* 1589 */       if (pid.coercedZeroOffset) {
/* 1590 */         ImageUtil.setPackedBinaryData(data, (WritableRaster)raster, rect);
/*      */       } else {
/*      */         short[] sd;
/*      */         int i, id[], h, j, k;
/* 1594 */         MultiPixelPackedSampleModel sm = (MultiPixelPackedSampleModel)this.sampleModel;
/* 1597 */         DataBuffer db = raster.getDataBuffer();
/* 1598 */         int dbOffset = db.getOffset();
/* 1600 */         int x = rect.x - raster.getSampleModelTranslateX();
/* 1601 */         int y = rect.y - raster.getSampleModelTranslateY();
/* 1603 */         int lineStride = sm.getScanlineStride();
/* 1604 */         int minOffset = sm.getOffset(x, y) + dbOffset;
/* 1605 */         int maxOffset = sm.getOffset(x + rect.width - 1, y) + dbOffset;
/* 1608 */         switch (this.bufferType) {
/*      */           case 1:
/* 1610 */             sd = ((DataBufferUShort)db).getData();
/* 1611 */             for (i = 0, h = 0; h < rect.height; h++) {
/* 1612 */               for (int w = minOffset; w <= maxOffset; w++)
/* 1613 */                 sd[w] = (short)(data[i++] << 8 | data[i++]); 
/* 1615 */               minOffset += lineStride;
/* 1616 */               maxOffset += lineStride;
/*      */             } 
/*      */             break;
/*      */           case 3:
/* 1621 */             id = ((DataBufferInt)db).getData();
/* 1622 */             for (j = 0, k = 0; k < rect.height; k++) {
/* 1623 */               for (int w = minOffset; w <= maxOffset; w++)
/* 1624 */                 id[w] = data[j++] << 24 | data[j++] << 16 | data[j++] << 8 | data[j++]; 
/* 1627 */               minOffset += lineStride;
/* 1628 */               maxOffset += lineStride;
/*      */             } 
/*      */             break;
/*      */         } 
/*      */       } 
/*      */     } else {
/* 1640 */       WritableRaster wr = (WritableRaster)raster;
/* 1641 */       int size = pid.lineStride * 8;
/* 1642 */       int[] p = new int[size];
/* 1644 */       for (int i = 0, h = 0; h < rect.height; h++) {
/* 1645 */         for (int w = 0; w < size; w += 8) {
/* 1646 */           p[w] = data[i] >>> 7 & 0x1;
/* 1647 */           p[w + 1] = data[i] >>> 6 & 0x1;
/* 1648 */           p[w + 2] = data[i] >>> 5 & 0x1;
/* 1649 */           p[w + 3] = data[i] >>> 4 & 0x1;
/* 1650 */           p[w + 4] = data[i] >>> 3 & 0x1;
/* 1651 */           p[w + 5] = data[i] >>> 2 & 0x1;
/* 1652 */           p[w + 6] = data[i] >>> 1 & 0x1;
/* 1653 */           p[w + 7] = data[i] & 0x1;
/* 1654 */           i++;
/*      */         } 
/* 1656 */         wr.setPixels(rect.x, rect.y + h, rect.width, 1, p);
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public UnpackedImageData getComponents(Raster raster, Rectangle rect, int type) {
/*      */     byte[] bc;
/*      */     int j;
/*      */     short[] usc;
/*      */     int k;
/*      */     short[] sc;
/*      */     int m;
/*      */     float[] fc;
/*      */     int n;
/*      */     double[] dc;
/*      */     int i1;
/* 1710 */     if (!this.hasCompatibleCM)
/* 1711 */       throw new IllegalArgumentException(JaiI18N.getString("PixelAccessor5")); 
/* 1715 */     if (!raster.getBounds().contains(rect))
/* 1716 */       throw new IllegalArgumentException(JaiI18N.getString("PixelAccessor0")); 
/* 1720 */     if (type < 0 || type > 5)
/* 1722 */       throw new IllegalArgumentException(JaiI18N.getString("PixelAccessor1")); 
/* 1726 */     if (type < this.componentType || (this.componentType == 1 && type == 2))
/* 1729 */       throw new IllegalArgumentException(JaiI18N.getString("PixelAccessor4")); 
/* 1734 */     int size = rect.width * rect.height * this.numComponents;
/* 1735 */     int[] ic = new int[size];
/* 1736 */     int width = rect.x + rect.width;
/* 1737 */     int height = rect.y + rect.height;
/* 1739 */     for (int i = 0, y = rect.y; y < height; y++) {
/* 1740 */       for (int x = rect.x; x < width; x++) {
/* 1741 */         Object p = raster.getDataElements(x, y, null);
/* 1742 */         this.colorModel.getComponents(p, ic, i);
/* 1743 */         i += this.numComponents;
/*      */       } 
/*      */     } 
/* 1748 */     Object data = null;
/* 1749 */     switch (type) {
/*      */       case 0:
/* 1751 */         bc = new byte[size];
/* 1752 */         for (j = 0; j < size; j++)
/* 1753 */           bc[j] = (byte)(ic[j] & 0xFF); 
/* 1755 */         data = repeatBand(bc, this.numComponents);
/*      */         break;
/*      */       case 1:
/* 1759 */         usc = new short[size];
/* 1760 */         for (k = 0; k < size; k++)
/* 1761 */           usc[k] = (short)(ic[k] & 0xFFFF); 
/* 1763 */         data = repeatBand(usc, this.numComponents);
/*      */         break;
/*      */       case 2:
/* 1767 */         sc = new short[size];
/* 1768 */         for (m = 0; m < size; m++)
/* 1769 */           sc[m] = (short)ic[m]; 
/* 1771 */         data = repeatBand(sc, this.numComponents);
/*      */         break;
/*      */       case 3:
/* 1775 */         data = repeatBand(ic, this.numComponents);
/*      */         break;
/*      */       case 4:
/* 1779 */         fc = new float[size];
/* 1780 */         for (n = 0; n < size; n++)
/* 1781 */           fc[n] = ic[n]; 
/* 1783 */         data = repeatBand(fc, this.numComponents);
/*      */         break;
/*      */       case 5:
/* 1787 */         dc = new double[size];
/* 1788 */         for (i1 = 0; i1 < size; i1++)
/* 1789 */           dc[i1] = ic[i1]; 
/* 1791 */         data = repeatBand(dc, this.numComponents);
/*      */         break;
/*      */     } 
/* 1795 */     return new UnpackedImageData(raster, rect, type, data, this.numComponents, this.numComponents * rect.width, getInterleavedOffsets(this.numComponents), raster instanceof WritableRaster);
/*      */   }
/*      */   
/*      */   public void setComponents(UnpackedImageData uid) {
/*      */     byte[] bc;
/*      */     int i;
/*      */     short[] usc;
/*      */     int k;
/*      */     short[] sc;
/*      */     int m;
/*      */     float[] fc;
/*      */     int n;
/*      */     double[] dc;
/*      */     int i1;
/* 1827 */     if (uid == null)
/* 1828 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 1831 */     if (!uid.convertToDest)
/*      */       return; 
/* 1835 */     WritableRaster raster = (WritableRaster)uid.raster;
/* 1836 */     Rectangle rect = uid.rect;
/* 1837 */     int type = uid.type;
/* 1839 */     int size = rect.width * rect.height * this.numComponents;
/* 1840 */     int[] ic = null;
/* 1842 */     switch (type) {
/*      */       case 0:
/* 1844 */         bc = uid.getByteData(0);
/* 1845 */         ic = new int[size];
/* 1846 */         for (i = 0; i < size; i++)
/* 1847 */           ic[i] = bc[i] & 0xFF; 
/*      */         break;
/*      */       case 1:
/* 1852 */         usc = uid.getShortData(0);
/* 1853 */         ic = new int[size];
/* 1854 */         for (k = 0; k < size; k++)
/* 1855 */           ic[k] = usc[k] & 0xFFFF; 
/*      */         break;
/*      */       case 2:
/* 1860 */         sc = uid.getShortData(0);
/* 1861 */         ic = new int[size];
/* 1862 */         for (m = 0; m < size; m++)
/* 1863 */           ic[m] = sc[m]; 
/*      */         break;
/*      */       case 3:
/* 1868 */         ic = uid.getIntData(0);
/*      */         break;
/*      */       case 4:
/* 1872 */         fc = uid.getFloatData(0);
/* 1873 */         ic = new int[size];
/* 1874 */         for (n = 0; n < size; n++)
/* 1875 */           ic[n] = (int)fc[n]; 
/*      */         break;
/*      */       case 5:
/* 1880 */         dc = uid.getDoubleData(0);
/* 1881 */         ic = new int[size];
/* 1882 */         for (i1 = 0; i1 < size; i1++)
/* 1883 */           ic[i1] = (int)dc[i1]; 
/*      */         break;
/*      */     } 
/* 1888 */     int width = rect.x + rect.width;
/* 1889 */     int height = rect.y + rect.height;
/* 1891 */     for (int j = 0, y = rect.y; y < height; y++) {
/* 1892 */       for (int x = rect.x; x < width; x++) {
/* 1893 */         Object p = this.colorModel.getDataElements(ic, j, (Object)null);
/* 1894 */         raster.setDataElements(x, y, p);
/* 1895 */         j += this.numComponents;
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public UnpackedImageData getComponentsRGB(Raster raster, Rectangle rect) {
/* 1937 */     if (!this.hasCompatibleCM)
/* 1938 */       throw new IllegalArgumentException(JaiI18N.getString("PixelAccessor5")); 
/* 1942 */     if (!raster.getBounds().contains(rect))
/* 1943 */       throw new IllegalArgumentException(JaiI18N.getString("PixelAccessor0")); 
/* 1947 */     int size = rect.width * rect.height;
/* 1949 */     byte[][] data = new byte[4][size];
/* 1950 */     byte[] r = data[0];
/* 1951 */     byte[] g = data[1];
/* 1952 */     byte[] b = data[2];
/* 1953 */     byte[] a = data[3];
/* 1956 */     int maxX = rect.x + rect.width;
/* 1957 */     int maxY = rect.y + rect.height;
/* 1959 */     if (this.isIndexCM) {
/* 1961 */       IndexColorModel icm = (IndexColorModel)this.colorModel;
/* 1962 */       int mapSize = icm.getMapSize();
/* 1965 */       byte[] reds = new byte[mapSize];
/* 1966 */       icm.getReds(reds);
/* 1967 */       byte[] greens = new byte[mapSize];
/* 1968 */       icm.getGreens(greens);
/* 1969 */       byte[] blues = new byte[mapSize];
/* 1970 */       icm.getBlues(blues);
/* 1971 */       byte[] alphas = null;
/* 1972 */       if (icm.hasAlpha()) {
/* 1973 */         alphas = new byte[mapSize];
/* 1974 */         icm.getAlphas(alphas);
/*      */       } 
/* 1978 */       int[] indices = raster.getPixels(rect.x, rect.y, rect.width, rect.height, (int[])null);
/* 1983 */       if (alphas == null) {
/* 1985 */         for (int i = 0, y = rect.y; y < maxY; y++) {
/* 1986 */           for (int x = rect.x; x < maxX; x++) {
/* 1987 */             int index = indices[i];
/* 1989 */             r[i] = reds[index];
/* 1990 */             g[i] = greens[index];
/* 1991 */             b[i] = blues[index];
/* 1993 */             i++;
/*      */           } 
/*      */         } 
/*      */       } else {
/* 1998 */         for (int i = 0, y = rect.y; y < maxY; y++) {
/* 1999 */           for (int x = rect.x; x < maxX; x++) {
/* 2000 */             int index = indices[i];
/* 2002 */             r[i] = reds[index];
/* 2003 */             g[i] = greens[index];
/* 2004 */             b[i] = blues[index];
/* 2005 */             a[i] = alphas[index];
/* 2007 */             i++;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } else {
/* 2015 */       for (int i = 0, y = rect.y; y < maxY; y++) {
/* 2016 */         for (int x = rect.x; x < maxX; x++) {
/* 2017 */           Object p = raster.getDataElements(x, y, null);
/* 2019 */           r[i] = (byte)this.colorModel.getRed(p);
/* 2020 */           g[i] = (byte)this.colorModel.getGreen(p);
/* 2021 */           b[i] = (byte)this.colorModel.getBlue(p);
/* 2022 */           a[i] = (byte)this.colorModel.getAlpha(p);
/* 2023 */           i++;
/*      */         } 
/*      */       } 
/*      */     } 
/* 2028 */     return new UnpackedImageData(raster, rect, 0, data, 1, rect.width, new int[4], raster instanceof WritableRaster);
/*      */   }
/*      */   
/*      */   public void setComponentsRGB(UnpackedImageData uid) {
/* 2060 */     if (uid == null)
/* 2061 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 2064 */     if (!uid.convertToDest)
/*      */       return; 
/* 2068 */     byte[][] data = uid.getByteData();
/* 2069 */     byte[] r = data[0];
/* 2070 */     byte[] g = data[1];
/* 2071 */     byte[] b = data[2];
/* 2072 */     byte[] a = data[3];
/* 2074 */     WritableRaster raster = (WritableRaster)uid.raster;
/* 2075 */     Rectangle rect = uid.rect;
/* 2077 */     int maxX = rect.x + rect.width;
/* 2078 */     int maxY = rect.y + rect.height;
/* 2080 */     for (int i = 0, y = rect.y; y < maxY; y++) {
/* 2081 */       for (int x = rect.x; x < maxX; x++) {
/* 2082 */         int rgb = a[i] << 24 | b[i] << 16 | g[i] << 8 | r[i];
/* 2085 */         Object p = this.colorModel.getDataElements(rgb, null);
/* 2086 */         raster.setDataElements(x, y, p);
/* 2087 */         i++;
/*      */       } 
/*      */     } 
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\PixelAccessor.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */