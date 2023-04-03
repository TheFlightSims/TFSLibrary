/*      */ package javax.media.jai;
/*      */ 
/*      */ import java.awt.Point;
/*      */ import java.awt.color.ColorSpace;
/*      */ import java.awt.image.ColorModel;
/*      */ import java.awt.image.ComponentColorModel;
/*      */ import java.awt.image.Raster;
/*      */ import java.awt.image.SampleModel;
/*      */ import java.awt.image.WritableRaster;
/*      */ 
/*      */ public class FloatDoubleColorModel extends ComponentColorModel {
/*      */   protected ColorSpace colorSpace;
/*      */   
/*      */   protected int colorSpaceType;
/*      */   
/*      */   protected int numColorComponents;
/*      */   
/*      */   protected int numComponents;
/*      */   
/*      */   protected int transparency;
/*      */   
/*      */   protected boolean hasAlpha;
/*      */   
/*      */   protected boolean isAlphaPremultiplied;
/*      */   
/*      */   private static int[] bitsHelper(int transferType, ColorSpace colorSpace, boolean hasAlpha) {
/*  127 */     int numBits = (transferType == 4) ? 32 : 64;
/*  128 */     int numComponents = colorSpace.getNumComponents();
/*  129 */     if (hasAlpha)
/*  130 */       numComponents++; 
/*  132 */     int[] bits = new int[numComponents];
/*  133 */     for (int i = 0; i < numComponents; i++)
/*  134 */       bits[i] = numBits; 
/*  137 */     return bits;
/*      */   }
/*      */   
/*      */   public FloatDoubleColorModel(ColorSpace colorSpace, boolean hasAlpha, boolean isAlphaPremultiplied, int transparency, int transferType) {
/*  173 */     super(colorSpace, bitsHelper(transferType, colorSpace, hasAlpha), hasAlpha, isAlphaPremultiplied, transparency, transferType);
/*  178 */     if (transferType != 4 && transferType != 5)
/*  180 */       throw new IllegalArgumentException(JaiI18N.getString("FloatDoubleColorModel0")); 
/*  183 */     this.colorSpace = colorSpace;
/*  184 */     this.colorSpaceType = colorSpace.getType();
/*  185 */     this.numComponents = this.numColorComponents = colorSpace.getNumComponents();
/*  187 */     if (hasAlpha)
/*  188 */       this.numComponents++; 
/*  190 */     this.transparency = transparency;
/*  191 */     this.hasAlpha = hasAlpha;
/*  192 */     this.isAlphaPremultiplied = isAlphaPremultiplied;
/*      */   }
/*      */   
/*      */   public int getRed(int pixel) {
/*  201 */     throw new IllegalArgumentException(JaiI18N.getString("FloatDoubleColorModel1"));
/*      */   }
/*      */   
/*      */   public int getGreen(int pixel) {
/*  210 */     throw new IllegalArgumentException(JaiI18N.getString("FloatDoubleColorModel2"));
/*      */   }
/*      */   
/*      */   public int getBlue(int pixel) {
/*  219 */     throw new IllegalArgumentException(JaiI18N.getString("FloatDoubleColorModel3"));
/*      */   }
/*      */   
/*      */   public int getAlpha(int pixel) {
/*  228 */     throw new IllegalArgumentException(JaiI18N.getString("FloatDoubleColorModel4"));
/*      */   }
/*      */   
/*      */   public int getRGB(int pixel) {
/*  237 */     throw new IllegalArgumentException(JaiI18N.getString("FloatDoubleColorModel5"));
/*      */   }
/*      */   
/*      */   private final int clamp(float value) {
/*  242 */     return (value >= 0.0F) ? ((value > 255.0F) ? 255 : (int)value) : 0;
/*      */   }
/*      */   
/*      */   private final int clamp(double value) {
/*  247 */     return (value >= 0.0D) ? ((value > 255.0D) ? 255 : (int)value) : 0;
/*      */   }
/*      */   
/*      */   private int getSample(Object inData, int sample) {
/*      */     float[] rgb;
/*  251 */     boolean needAlpha = (this.hasAlpha && this.isAlphaPremultiplied);
/*  252 */     int type = this.colorSpaceType;
/*  254 */     boolean is_sRGB = this.colorSpace.isCS_sRGB();
/*  256 */     if (type == 6) {
/*  257 */       sample = 0;
/*  258 */       is_sRGB = true;
/*      */     } 
/*  262 */     if (is_sRGB) {
/*  263 */       if (this.transferType == 4) {
/*  264 */         float[] fdata = (float[])inData;
/*  265 */         float fsample = fdata[sample] * 255.0F;
/*  266 */         if (needAlpha) {
/*  267 */           float falp = fdata[this.numColorComponents];
/*  268 */           if (falp == 0.0D)
/*  268 */             return 0; 
/*  270 */           return clamp(fsample / falp);
/*      */         } 
/*  272 */         return clamp(fsample);
/*      */       } 
/*  275 */       double[] arrayOfDouble = (double[])inData;
/*  276 */       double dsample = arrayOfDouble[sample] * 255.0D;
/*  277 */       if (needAlpha) {
/*  278 */         double dalp = arrayOfDouble[this.numColorComponents];
/*  279 */         if (dalp == 0.0D)
/*  279 */           return 0; 
/*  281 */         return clamp(dsample / dalp);
/*      */       } 
/*  283 */       return clamp(dsample);
/*      */     } 
/*  291 */     if (this.transferType == 4) {
/*  292 */       float[] fdata = (float[])inData;
/*  293 */       if (needAlpha) {
/*  294 */         float falp = fdata[this.numColorComponents];
/*  295 */         if (falp == 0.0D)
/*  295 */           return 0; 
/*  296 */         float[] arrayOfFloat = new float[this.numColorComponents];
/*  297 */         for (int i = 0; i < this.numColorComponents; i++)
/*  298 */           arrayOfFloat[i] = fdata[i] / falp; 
/*  300 */         rgb = this.colorSpace.toRGB(arrayOfFloat);
/*      */       } else {
/*  302 */         rgb = this.colorSpace.toRGB(fdata);
/*      */       } 
/*  304 */       return (int)(rgb[sample] * 255.0F + 0.5F);
/*      */     } 
/*  306 */     double[] ddata = (double[])inData;
/*  307 */     float[] norm = new float[this.numColorComponents];
/*  308 */     if (needAlpha) {
/*  309 */       double dalp = ddata[this.numColorComponents];
/*  310 */       if (dalp == 0.0D)
/*  310 */         return 0; 
/*  311 */       for (int i = 0; i < this.numColorComponents; i++)
/*  312 */         norm[i] = (float)(ddata[i] / dalp); 
/*  314 */       rgb = this.colorSpace.toRGB(norm);
/*      */     } else {
/*  316 */       for (int i = 0; i < this.numColorComponents; i++)
/*  317 */         norm[i] = (float)ddata[i]; 
/*  319 */       rgb = this.colorSpace.toRGB(norm);
/*      */     } 
/*  321 */     return (int)((rgb[sample] * 255.0F) + 0.5D);
/*      */   }
/*      */   
/*      */   public int getRed(Object inData) {
/*  349 */     return getSample(inData, 0);
/*      */   }
/*      */   
/*      */   public int getGreen(Object inData) {
/*  376 */     return getSample(inData, 1);
/*      */   }
/*      */   
/*      */   public int getBlue(Object inData) {
/*  403 */     return getSample(inData, 2);
/*      */   }
/*      */   
/*      */   public int getAlpha(Object inData) {
/*  430 */     if (inData == null)
/*  431 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*  434 */     if (!this.hasAlpha)
/*  435 */       return 255; 
/*  438 */     if (this.transferType == 4) {
/*  439 */       float[] fdata = (float[])inData;
/*  440 */       return (int)(fdata[this.numColorComponents] * 255.0F + 0.5F);
/*      */     } 
/*  442 */     double[] ddata = (double[])inData;
/*  443 */     return (int)(ddata[this.numColorComponents] * 255.0D + 0.5D);
/*      */   }
/*      */   
/*      */   public int getRGB(Object inData) {
/*      */     int red, green, blue;
/*  470 */     boolean needAlpha = (this.hasAlpha && this.isAlphaPremultiplied);
/*  471 */     int alpha = 255;
/*  474 */     if (this.colorSpace.isCS_sRGB()) {
/*  475 */       if (this.transferType == 4) {
/*  476 */         float[] fdata = (float[])inData;
/*  477 */         float fred = fdata[0];
/*  478 */         float fgreen = fdata[1];
/*  479 */         float fblue = fdata[2];
/*  480 */         float fscale = 255.0F;
/*  481 */         if (needAlpha) {
/*  482 */           float falpha = fdata[3];
/*  483 */           fscale /= falpha;
/*  484 */           alpha = clamp(255.0F * falpha);
/*      */         } 
/*  487 */         red = clamp(fred * fscale);
/*  488 */         green = clamp(fgreen * fscale);
/*  489 */         blue = clamp(fblue * fscale);
/*      */       } else {
/*  491 */         double[] ddata = (double[])inData;
/*  492 */         double dred = ddata[0];
/*  493 */         double dgreen = ddata[1];
/*  494 */         double dblue = ddata[2];
/*  495 */         double dscale = 255.0D;
/*  496 */         if (needAlpha) {
/*  497 */           double dalpha = ddata[3];
/*  498 */           dscale /= dalpha;
/*  499 */           alpha = clamp(255.0D * dalpha);
/*      */         } 
/*  502 */         red = clamp(dred * dscale);
/*  503 */         green = clamp(dgreen * dscale);
/*  504 */         blue = clamp(dblue * dscale);
/*      */       } 
/*  506 */     } else if (this.colorSpaceType == 6) {
/*  507 */       if (this.transferType == 4) {
/*  508 */         float[] fdata = (float[])inData;
/*  509 */         float fgray = fdata[0];
/*  510 */         if (needAlpha) {
/*  511 */           float falp = fdata[1];
/*  512 */           red = green = blue = clamp(fgray * 255.0F / falp);
/*  513 */           alpha = clamp(255.0F * falp);
/*      */         } else {
/*  515 */           red = green = blue = clamp(fgray * 255.0F);
/*      */         } 
/*      */       } else {
/*  518 */         double[] ddata = (double[])inData;
/*  519 */         double dgray = ddata[0];
/*  520 */         if (needAlpha) {
/*  521 */           double dalp = ddata[1];
/*  522 */           red = green = blue = clamp(dgray * 255.0D / dalp);
/*  523 */           alpha = clamp(255.0D * dalp);
/*      */         } else {
/*  525 */           red = green = blue = clamp(dgray * 255.0D);
/*      */         } 
/*      */       } 
/*      */     } else {
/*      */       float[] norm;
/*  532 */       if (this.transferType == 4) {
/*  533 */         float[] fdata = (float[])inData;
/*  534 */         if (needAlpha) {
/*  535 */           float falp = fdata[this.numColorComponents];
/*  536 */           float invfalp = 1.0F / falp;
/*  537 */           norm = new float[this.numColorComponents];
/*  538 */           for (int i = 0; i < this.numColorComponents; i++)
/*  539 */             norm[i] = fdata[i] * invfalp; 
/*  541 */           alpha = clamp(255.0F * falp);
/*      */         } else {
/*  543 */           norm = fdata;
/*      */         } 
/*      */       } else {
/*  546 */         double[] ddata = (double[])inData;
/*  547 */         norm = new float[this.numColorComponents];
/*  548 */         if (needAlpha) {
/*  549 */           double dalp = ddata[this.numColorComponents];
/*  550 */           double invdalp = 1.0D / dalp;
/*  551 */           for (int i = 0; i < this.numColorComponents; i++)
/*  552 */             norm[i] = (float)(ddata[i] * invdalp); 
/*  554 */           alpha = clamp(255.0D * dalp);
/*      */         } else {
/*  556 */           for (int i = 0; i < this.numColorComponents; i++)
/*  557 */             norm[i] = (float)ddata[i]; 
/*      */         } 
/*      */       } 
/*  563 */       float[] rgb = this.colorSpace.toRGB(norm);
/*  565 */       red = clamp(rgb[0] * 255.0F);
/*  566 */       green = clamp(rgb[1] * 255.0F);
/*  567 */       blue = clamp(rgb[2] * 255.0F);
/*      */     } 
/*  570 */     return alpha << 24 | red << 16 | green << 8 | blue;
/*      */   }
/*      */   
/*      */   public Object getDataElements(int rgb, Object pixel) {
/*      */     double[] doublePixel;
/*  595 */     if (this.transferType == 4) {
/*      */       float[] floatPixel;
/*  598 */       if (pixel == null) {
/*  599 */         floatPixel = new float[this.numComponents];
/*      */       } else {
/*  601 */         if (!(pixel instanceof float[]))
/*  602 */           throw new ClassCastException(JaiI18N.getString("FloatDoubleColorModel7")); 
/*  604 */         floatPixel = (float[])pixel;
/*  605 */         if (floatPixel.length < this.numComponents)
/*  606 */           throw new ArrayIndexOutOfBoundsException(JaiI18N.getString("FloatDoubleColorModel8")); 
/*      */       } 
/*  610 */       float f = 0.003921569F;
/*  611 */       if (this.colorSpace.isCS_sRGB()) {
/*  612 */         int alp = rgb >> 24 & 0xFF;
/*  613 */         int red = rgb >> 16 & 0xFF;
/*  614 */         int grn = rgb >> 8 & 0xFF;
/*  615 */         int blu = rgb & 0xFF;
/*  616 */         float norm = f;
/*  617 */         if (this.isAlphaPremultiplied)
/*  618 */           norm *= alp; 
/*  620 */         floatPixel[0] = red * norm;
/*  621 */         floatPixel[1] = grn * norm;
/*  622 */         floatPixel[2] = blu * norm;
/*  623 */         if (this.hasAlpha)
/*  624 */           floatPixel[3] = alp * f; 
/*  626 */       } else if (this.colorSpaceType == 6) {
/*  627 */         float gray = (rgb >> 16 & 0xFF) * 0.299F * f + (rgb >> 8 & 0xFF) * 0.587F * f + (rgb & 0xFF) * 0.114F * f;
/*  631 */         floatPixel[0] = gray;
/*  633 */         if (this.hasAlpha) {
/*  634 */           int alpha = rgb >> 24 & 0xFF;
/*  635 */           floatPixel[1] = alpha * f;
/*      */         } 
/*      */       } else {
/*  639 */         float[] norm = new float[3];
/*  640 */         norm[0] = (rgb >> 16 & 0xFF) * f;
/*  641 */         norm[1] = (rgb >> 8 & 0xFF) * f;
/*  642 */         norm[2] = (rgb & 0xFF) * f;
/*  644 */         norm = this.colorSpace.fromRGB(norm);
/*  645 */         for (int i = 0; i < this.numColorComponents; i++)
/*  646 */           floatPixel[i] = norm[i]; 
/*  648 */         if (this.hasAlpha) {
/*  649 */           int alpha = rgb >> 24 & 0xFF;
/*  650 */           floatPixel[this.numColorComponents] = alpha * f;
/*      */         } 
/*      */       } 
/*  654 */       return floatPixel;
/*      */     } 
/*  658 */     if (pixel == null) {
/*  659 */       doublePixel = new double[this.numComponents];
/*      */     } else {
/*  661 */       if (!(pixel instanceof double[]))
/*  662 */         throw new ClassCastException(JaiI18N.getString("FloatDoubleColorModel7")); 
/*  664 */       doublePixel = (double[])pixel;
/*  665 */       if (doublePixel.length < this.numComponents)
/*  666 */         throw new ArrayIndexOutOfBoundsException(JaiI18N.getString("FloatDoubleColorModel8")); 
/*      */     } 
/*  670 */     double inv255 = 0.00392156862745098D;
/*  671 */     if (this.colorSpace.isCS_sRGB()) {
/*  672 */       int alp = rgb >> 24 & 0xFF;
/*  673 */       int red = rgb >> 16 & 0xFF;
/*  674 */       int grn = rgb >> 8 & 0xFF;
/*  675 */       int blu = rgb & 0xFF;
/*  676 */       double norm = inv255;
/*  677 */       if (this.isAlphaPremultiplied)
/*  678 */         norm *= alp; 
/*  680 */       doublePixel[0] = red * norm;
/*  681 */       doublePixel[1] = grn * norm;
/*  682 */       doublePixel[2] = blu * norm;
/*  683 */       if (this.hasAlpha)
/*  684 */         doublePixel[3] = alp * inv255; 
/*  686 */     } else if (this.colorSpaceType == 6) {
/*  687 */       double gray = (rgb >> 16 & 0xFF) * 0.299D * inv255 + (rgb >> 8 & 0xFF) * 0.587D * inv255 + (rgb & 0xFF) * 0.114D * inv255;
/*  691 */       doublePixel[0] = gray;
/*  693 */       if (this.hasAlpha) {
/*  694 */         int alpha = rgb >> 24 & 0xFF;
/*  695 */         doublePixel[1] = alpha * inv255;
/*      */       } 
/*      */     } else {
/*  698 */       float inv255F = 0.003921569F;
/*  701 */       float[] norm = new float[3];
/*  702 */       norm[0] = (rgb >> 16 & 0xFF) * inv255F;
/*  703 */       norm[1] = (rgb >> 8 & 0xFF) * inv255F;
/*  704 */       norm[2] = (rgb & 0xFF) * inv255F;
/*  706 */       norm = this.colorSpace.fromRGB(norm);
/*  707 */       for (int i = 0; i < this.numColorComponents; i++)
/*  708 */         doublePixel[i] = norm[i]; 
/*  710 */       if (this.hasAlpha) {
/*  711 */         int alpha = rgb >> 24 & 0xFF;
/*  712 */         doublePixel[this.numColorComponents] = alpha * inv255;
/*      */       } 
/*      */     } 
/*  716 */     return doublePixel;
/*      */   }
/*      */   
/*      */   public int[] getComponents(int pixel, int[] components, int offset) {
/*  726 */     throw new IllegalArgumentException(JaiI18N.getString("FloatDoubleColorModel9"));
/*      */   }
/*      */   
/*      */   public int[] getComponents(Object pixel, int[] components, int offset) {
/*  735 */     throw new IllegalArgumentException(JaiI18N.getString("FloatDoubleColorModel9"));
/*      */   }
/*      */   
/*      */   public int getDataElement(int[] components, int offset) {
/*  745 */     throw new IllegalArgumentException(JaiI18N.getString("FloatDoubleColorModel9"));
/*      */   }
/*      */   
/*      */   public Object getDataElements(int[] components, int offset, Object obj) {
/*      */     double[] pixel;
/*  782 */     if (components.length - offset < this.numComponents)
/*  783 */       throw new IllegalArgumentException(this.numComponents + " " + JaiI18N.getString("FloatDoubleColorModel10")); 
/*  786 */     if (this.transferType == 4) {
/*      */       float[] arrayOfFloat;
/*  788 */       if (obj == null) {
/*  789 */         arrayOfFloat = new float[components.length];
/*      */       } else {
/*  791 */         arrayOfFloat = (float[])obj;
/*      */       } 
/*  793 */       for (int j = 0; j < this.numComponents; j++)
/*  794 */         arrayOfFloat[j] = components[offset + j]; 
/*  797 */       return arrayOfFloat;
/*      */     } 
/*  800 */     if (obj == null) {
/*  801 */       pixel = new double[components.length];
/*      */     } else {
/*  803 */       pixel = (double[])obj;
/*      */     } 
/*  805 */     for (int i = 0; i < this.numComponents; i++)
/*  806 */       pixel[i] = components[offset + i]; 
/*  809 */     return pixel;
/*      */   }
/*      */   
/*      */   public ColorModel coerceData(WritableRaster raster, boolean isAlphaPremultiplied) {
/*  829 */     if (!this.hasAlpha || this.isAlphaPremultiplied == isAlphaPremultiplied)
/*  833 */       return this; 
/*  836 */     int w = raster.getWidth();
/*  837 */     int h = raster.getHeight();
/*  838 */     int aIdx = raster.getNumBands() - 1;
/*  839 */     int rminX = raster.getMinX();
/*  840 */     int rY = raster.getMinY();
/*  843 */     if (raster.getTransferType() != this.transferType)
/*  844 */       throw new IllegalArgumentException(JaiI18N.getString("FloatDoubleColorModel6")); 
/*  848 */     if (isAlphaPremultiplied) {
/*      */       float[] arrayOfFloat;
/*      */       double[] pixel;
/*      */       int y;
/*  849 */       switch (this.transferType) {
/*      */         case 4:
/*  851 */           arrayOfFloat = null;
/*  852 */           for (y = 0; y < h; y++, rY++) {
/*  853 */             int rX = rminX;
/*  854 */             for (int x = 0; x < w; x++, rX++) {
/*  855 */               arrayOfFloat = (float[])raster.getDataElements(rX, rY, arrayOfFloat);
/*  857 */               float fAlpha = arrayOfFloat[aIdx];
/*  858 */               if (fAlpha != 0.0F) {
/*  859 */                 for (int c = 0; c < aIdx; c++)
/*  860 */                   arrayOfFloat[c] = arrayOfFloat[c] * fAlpha; 
/*  862 */                 raster.setDataElements(rX, rY, arrayOfFloat);
/*      */               } 
/*      */             } 
/*      */           } 
/*      */           break;
/*      */         case 5:
/*  870 */           pixel = null;
/*  871 */           for (y = 0; y < h; y++, rY++) {
/*  872 */             int rX = rminX;
/*  873 */             for (int x = 0; x < w; x++, rX++) {
/*  874 */               pixel = (double[])raster.getDataElements(rX, rY, pixel);
/*  876 */               double dAlpha = pixel[aIdx];
/*  877 */               if (dAlpha != 0.0D) {
/*  878 */                 for (int c = 0; c < aIdx; c++)
/*  879 */                   pixel[c] = pixel[c] * dAlpha; 
/*  881 */                 raster.setDataElements(rX, rY, pixel);
/*      */               } 
/*      */             } 
/*      */           } 
/*      */           break;
/*      */         default:
/*  889 */           throw new RuntimeException(JaiI18N.getString("FloatDoubleColorModel0"));
/*      */       } 
/*  892 */       if (isAlphaPremultiplied);
/*      */     } else {
/*      */       int y;
/*  898 */       switch (this.transferType) {
/*      */         case 4:
/*  900 */           for (y = 0; y < h; y++, rY++) {
/*  901 */             int rX = rminX;
/*  902 */             for (int x = 0; x < w; x++, rX++) {
/*  903 */               float[] pixel = null;
/*  904 */               pixel = (float[])raster.getDataElements(rX, rY, pixel);
/*  906 */               float fAlpha = pixel[aIdx];
/*  907 */               if (fAlpha != 0.0F) {
/*  908 */                 float invFAlpha = 1.0F / fAlpha;
/*  909 */                 for (int c = 0; c < aIdx; c++)
/*  910 */                   pixel[c] = pixel[c] * invFAlpha; 
/*      */               } 
/*  913 */               raster.setDataElements(rX, rY, pixel);
/*      */             } 
/*      */           } 
/*  945 */           return new FloatDoubleColorModel(this.colorSpace, this.hasAlpha, isAlphaPremultiplied, this.transparency, this.transferType);
/*      */         case 5:
/*      */           for (y = 0; y < h; y++, rY++) {
/*      */             int rX = rminX;
/*      */             for (int x = 0; x < w; x++, rX++) {
/*      */               double[] pixel = null;
/*      */               pixel = (double[])raster.getDataElements(rX, rY, pixel);
/*      */               double dAlpha = pixel[aIdx];
/*      */               if (dAlpha != 0.0D) {
/*      */                 double invDAlpha = 1.0D / dAlpha;
/*      */                 for (int c = 0; c < aIdx; c++)
/*      */                   pixel[c] = pixel[c] * invDAlpha; 
/*      */               } 
/*      */               raster.setDataElements(rX, rY, pixel);
/*      */             } 
/*      */           } 
/*  945 */           return new FloatDoubleColorModel(this.colorSpace, this.hasAlpha, isAlphaPremultiplied, this.transparency, this.transferType);
/*      */       } 
/*      */       throw new RuntimeException(JaiI18N.getString("FloatDoubleColorModel0"));
/*      */     } 
/*  945 */     return new FloatDoubleColorModel(this.colorSpace, this.hasAlpha, isAlphaPremultiplied, this.transparency, this.transferType);
/*      */   }
/*      */   
/*      */   public boolean isCompatibleRaster(Raster raster) {
/*  958 */     SampleModel sm = raster.getSampleModel();
/*  959 */     return isCompatibleSampleModel(sm);
/*      */   }
/*      */   
/*      */   public WritableRaster createCompatibleWritableRaster(int w, int h) {
/*  979 */     SampleModel sm = createCompatibleSampleModel(w, h);
/*  980 */     return RasterFactory.createWritableRaster(sm, new Point(0, 0));
/*      */   }
/*      */   
/*      */   public SampleModel createCompatibleSampleModel(int w, int h) {
/*  999 */     int[] bandOffsets = new int[this.numComponents];
/* 1000 */     for (int i = 0; i < this.numComponents; i++)
/* 1001 */       bandOffsets[i] = i; 
/* 1003 */     return new ComponentSampleModelJAI(this.transferType, w, h, this.numComponents, w * this.numComponents, bandOffsets);
/*      */   }
/*      */   
/*      */   public boolean isCompatibleSampleModel(SampleModel sm) {
/* 1030 */     if (sm instanceof java.awt.image.ComponentSampleModel) {
/* 1031 */       if (sm.getNumBands() != getNumComponents())
/* 1032 */         return false; 
/* 1034 */       if (sm.getDataType() != this.transferType)
/* 1035 */         return false; 
/* 1037 */       return true;
/*      */     } 
/* 1039 */     return false;
/*      */   }
/*      */   
/*      */   public String toString() {
/* 1045 */     return "FloatDoubleColorModel: " + super.toString();
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\FloatDoubleColorModel.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */