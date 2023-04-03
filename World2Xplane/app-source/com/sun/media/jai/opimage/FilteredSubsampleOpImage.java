/*      */ package com.sun.media.jai.opimage;
/*      */ 
/*      */ import com.sun.media.jai.util.ImageUtil;
/*      */ import java.awt.Rectangle;
/*      */ import java.awt.geom.Point2D;
/*      */ import java.awt.image.Raster;
/*      */ import java.awt.image.RenderedImage;
/*      */ import java.awt.image.WritableRaster;
/*      */ import java.util.Map;
/*      */ import javax.media.jai.BorderExtender;
/*      */ import javax.media.jai.GeometricOpImage;
/*      */ import javax.media.jai.ImageLayout;
/*      */ import javax.media.jai.Interpolation;
/*      */ import javax.media.jai.RasterAccessor;
/*      */ import javax.media.jai.RasterFormatTag;
/*      */ 
/*      */ public class FilteredSubsampleOpImage extends GeometricOpImage {
/*      */   protected int scaleX;
/*      */   
/*      */   protected int scaleY;
/*      */   
/*      */   protected int hParity;
/*      */   
/*      */   protected int vParity;
/*      */   
/*      */   protected float[] hKernel;
/*      */   
/*      */   protected float[] vKernel;
/*      */   
/*      */   private static float[] convolveFullKernels(float[] a, float[] b) {
/*  243 */     int lenA = a.length;
/*  244 */     int lenB = b.length;
/*  245 */     float[] c = new float[lenA + lenB - 1];
/*  247 */     for (int k = 0; k < c.length; k++) {
/*  248 */       for (int j = Math.max(0, k - lenB + 1); j <= Math.min(k, lenA - 1); j++)
/*  249 */         c[k] = c[k] + a[j] * b[k - j]; 
/*      */     } 
/*  251 */     return c;
/*      */   }
/*      */   
/*      */   private static float[] convolveSymmetricKernels(int aParity, int bParity, float[] a, float[] b) {
/*  286 */     int lenA = a.length;
/*  287 */     int lenB = b.length;
/*  288 */     int lenTmpA = 2 * lenA - aParity;
/*  289 */     int lenTmpB = 2 * lenB - bParity;
/*  290 */     int lenTmpC = lenTmpA + lenTmpB - 1;
/*  291 */     float[] tmpA = new float[lenTmpA];
/*  292 */     float[] tmpB = new float[lenTmpB];
/*  294 */     float[] c = new float[(lenTmpC + 1) / 2];
/*      */     int k;
/*  297 */     for (k = 0; k < lenTmpA; k++)
/*  298 */       tmpA[k] = a[Math.abs(k - lenA + (aParity - 1) * k / lenA + 1)]; 
/*  301 */     for (k = 0; k < lenTmpB; k++)
/*  302 */       tmpB[k] = b[Math.abs(k - lenB + (bParity - 1) * k / lenB + 1)]; 
/*  305 */     float[] tmpC = convolveFullKernels(tmpA, tmpB);
/*  308 */     int cParity = tmpC.length % 2;
/*  309 */     for (int i = 0; i < c.length; i++)
/*  310 */       c[i] = tmpC[lenTmpC - c.length - i - 1 + cParity]; 
/*  312 */     return c;
/*      */   }
/*      */   
/*      */   private static float[] combineFilters(int scaleFactor, int resampleType, float[] qsFilter) {
/*      */     float[] bilinearKernel, bicubicKernel, bicubic2Kernel;
/*  332 */     if (scaleFactor % 2 == 1)
/*  332 */       return (float[])qsFilter.clone(); 
/*  334 */     int qsParity = 1;
/*  335 */     int resampParity = 0;
/*  337 */     switch (resampleType) {
/*      */       case 0:
/*  339 */         return (float[])qsFilter.clone();
/*      */       case 1:
/*  341 */         bilinearKernel = new float[] { 0.5F };
/*  342 */         return convolveSymmetricKernels(qsParity, resampParity, qsFilter, bilinearKernel);
/*      */       case 2:
/*  345 */         bicubicKernel = new float[] { 0.5625F, -0.0625F };
/*  346 */         return convolveSymmetricKernels(qsParity, resampParity, qsFilter, bicubicKernel);
/*      */       case 3:
/*  349 */         bicubic2Kernel = new float[] { 0.625F, -0.125F };
/*  350 */         return convolveSymmetricKernels(qsParity, resampParity, qsFilter, bicubic2Kernel);
/*      */     } 
/*  353 */     throw new IllegalArgumentException(JaiI18N.getString("FilteredSubsample0"));
/*      */   }
/*      */   
/*      */   private static int filterParity(int scaleFactor, int resampleType) {
/*  373 */     if (scaleFactor % 2 == 1 || resampleType == 0)
/*  374 */       return 1; 
/*  378 */     return 0;
/*      */   }
/*      */   
/*      */   private static final ImageLayout layoutHelper(RenderedImage source, Interpolation interp, int scaleX, int scaleY, int filterSize, ImageLayout il) {
/*  401 */     if (scaleX < 1 || scaleY < 1)
/*  402 */       throw new IllegalArgumentException(JaiI18N.getString("FilteredSubsample1")); 
/*  405 */     if (filterSize < 1)
/*  406 */       throw new IllegalArgumentException(JaiI18N.getString("FilteredSubsample2")); 
/*  411 */     Rectangle bounds = forwardMapRect(source.getMinX(), source.getMinY(), source.getWidth(), source.getHeight(), scaleX, scaleY);
/*  417 */     ImageLayout layout = (il == null) ? new ImageLayout(bounds.x, bounds.y, bounds.width, bounds.height) : (ImageLayout)il.clone();
/*  422 */     if (il != null) {
/*  423 */       layout.setWidth(bounds.width);
/*  424 */       layout.setHeight(bounds.height);
/*  425 */       layout.setMinX(bounds.x);
/*  426 */       layout.setMinY(bounds.y);
/*      */     } 
/*  429 */     return layout;
/*      */   }
/*      */   
/*      */   public FilteredSubsampleOpImage(RenderedImage source, BorderExtender extender, Map config, ImageLayout layout, int scaleX, int scaleY, float[] qsFilter, Interpolation interp) {
/*  459 */     super(vectorize(source), layoutHelper(source, interp, scaleX, scaleY, qsFilter.length, layout), config, true, extender, interp, null);
/*      */     int resampleType;
/*  470 */     if (interp instanceof javax.media.jai.InterpolationNearest) {
/*  471 */       resampleType = 0;
/*  472 */     } else if (interp instanceof javax.media.jai.InterpolationBilinear) {
/*  473 */       resampleType = 1;
/*  474 */     } else if (interp instanceof javax.media.jai.InterpolationBicubic) {
/*  475 */       resampleType = 2;
/*  476 */     } else if (interp instanceof javax.media.jai.InterpolationBicubic2) {
/*  477 */       resampleType = 3;
/*      */     } else {
/*  479 */       throw new IllegalArgumentException(JaiI18N.getString("FilteredSubsample3"));
/*      */     } 
/*  484 */     this.hParity = filterParity(scaleX, resampleType);
/*  485 */     this.vParity = filterParity(scaleY, resampleType);
/*  486 */     this.hKernel = combineFilters(scaleX, resampleType, qsFilter);
/*  487 */     this.vKernel = combineFilters(scaleY, resampleType, qsFilter);
/*  489 */     this.scaleX = scaleX;
/*  490 */     this.scaleY = scaleY;
/*      */   }
/*      */   
/*      */   public Point2D mapDestPoint(Point2D destPt) {
/*  510 */     if (destPt == null)
/*  511 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*  514 */     Point2D pt = (Point2D)destPt.clone();
/*  516 */     pt.setLocation(destPt.getX() * this.scaleX, destPt.getY() * this.scaleY);
/*  518 */     return pt;
/*      */   }
/*      */   
/*      */   public Point2D mapSourcePoint(Point2D sourcePt) {
/*  536 */     if (sourcePt == null)
/*  537 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*  540 */     Point2D pt = (Point2D)sourcePt.clone();
/*  542 */     pt.setLocation(sourcePt.getX() / this.scaleX, sourcePt.getY() / this.scaleY);
/*  544 */     return pt;
/*      */   }
/*      */   
/*      */   public Rectangle mapSourceRect(Rectangle sourceRect, int sourceIndex) {
/*  566 */     if (sourceIndex != 0)
/*  567 */       throw new IllegalArgumentException(JaiI18N.getString("FilteredSubsample4")); 
/*  570 */     int xOffset = sourceRect.x + this.hKernel.length - this.hParity - this.scaleX / 2;
/*  571 */     int yOffset = sourceRect.y + this.vKernel.length - this.vParity - this.scaleY / 2;
/*  572 */     int rectWidth = sourceRect.width - 2 * this.hKernel.length + this.hParity + 1;
/*  573 */     int rectHeight = sourceRect.height - 2 * this.vKernel.length + this.vParity + 1;
/*  574 */     return forwardMapRect(xOffset, yOffset, rectWidth, rectHeight, this.scaleX, this.scaleY);
/*      */   }
/*      */   
/*      */   private static final Rectangle forwardMapRect(int x, int y, int w, int h, int scaleX, int scaleY) {
/*  591 */     float sx = 1.0F / scaleX;
/*  592 */     float sy = 1.0F / scaleY;
/*  594 */     x = Math.round(x * sx);
/*  595 */     y = Math.round(y * sy);
/*  597 */     return new Rectangle(x, y, Math.round((x + w) * sx) - x, Math.round((y + h) * sy) - y);
/*      */   }
/*      */   
/*      */   protected final Rectangle forwardMapRect(Rectangle srcRect, int srcIndex) {
/*  611 */     int x = srcRect.x;
/*  612 */     int y = srcRect.y;
/*  613 */     int w = srcRect.width;
/*  614 */     int h = srcRect.height;
/*  615 */     float sx = 1.0F / this.scaleX;
/*  616 */     float sy = 1.0F / this.scaleY;
/*  618 */     x = Math.round(x * sx);
/*  619 */     y = Math.round(y * sy);
/*  621 */     return new Rectangle(x, y, Math.round((x + w) * sx) - x, Math.round((y + h) * sy) - y);
/*      */   }
/*      */   
/*      */   protected final Rectangle backwardMapRect(Rectangle destRect, int srcIncex) {
/*  634 */     int x = destRect.x;
/*  635 */     int y = destRect.y;
/*  636 */     int w = destRect.width;
/*  637 */     int h = destRect.height;
/*  639 */     return new Rectangle(x * this.scaleX, y * this.scaleY, (x + w) * this.scaleX - x, (y + h) * this.scaleY - y);
/*      */   }
/*      */   
/*      */   public Rectangle mapDestRect(Rectangle destRect, int sourceIndex) {
/*  662 */     if (sourceIndex != 0)
/*  663 */       throw new IllegalArgumentException(JaiI18N.getString("FilteredSubsample4")); 
/*  666 */     int xOffset = destRect.x * this.scaleX - this.hKernel.length + this.hParity + this.scaleX / 2;
/*  667 */     int yOffset = destRect.y * this.scaleY - this.vKernel.length + this.vParity + this.scaleY / 2;
/*  668 */     int rectWidth = destRect.width * this.scaleX + 2 * this.hKernel.length - this.hParity - 1;
/*  669 */     int rectHeight = destRect.height * this.scaleY + 2 * this.vKernel.length - this.vParity - 1;
/*  670 */     return new Rectangle(xOffset, yOffset, rectWidth, rectHeight);
/*      */   }
/*      */   
/*      */   public void computeRect(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/*  688 */     RasterFormatTag[] formatTags = getFormatTags();
/*  691 */     RasterAccessor dst = new RasterAccessor(dest, destRect, formatTags[1], getColorModel());
/*  696 */     RasterAccessor src = new RasterAccessor(sources[0], mapDestRect(destRect, 0), formatTags[0], getSourceImage(0).getColorModel());
/*  701 */     switch (dst.getDataType()) {
/*      */       case 0:
/*  703 */         computeRectByte(src, dst);
/*      */         break;
/*      */       case 1:
/*  706 */         computeRectUShort(src, dst);
/*      */         break;
/*      */       case 2:
/*  709 */         computeRectShort(src, dst);
/*      */         break;
/*      */       case 3:
/*  712 */         computeRectInt(src, dst);
/*      */         break;
/*      */       case 4:
/*  715 */         computeRectFloat(src, dst);
/*      */         break;
/*      */       case 5:
/*  718 */         computeRectDouble(src, dst);
/*      */         break;
/*      */       default:
/*  721 */         throw new IllegalArgumentException(JaiI18N.getString("FilteredSubsample5"));
/*      */     } 
/*  727 */     if (dst.isDataCopy()) {
/*  728 */       dst.clampDataArrays();
/*  729 */       dst.copyDataToRaster();
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void computeRectByte(RasterAccessor src, RasterAccessor dst) {
/*  742 */     int dwidth = dst.getWidth();
/*  743 */     int dheight = dst.getHeight();
/*  744 */     int dnumBands = dst.getNumBands();
/*  747 */     byte[][] dstDataArrays = dst.getByteDataArrays();
/*  748 */     int[] dstBandOffsets = dst.getBandOffsets();
/*  749 */     int dstPixelStride = dst.getPixelStride();
/*  750 */     int dstScanlineStride = dst.getScanlineStride();
/*  753 */     byte[][] srcDataArrays = src.getByteDataArrays();
/*  754 */     int[] srcBandOffsets = src.getBandOffsets();
/*  755 */     int srcPixelStride = src.getPixelStride();
/*  756 */     int srcScanlineStride = src.getScanlineStride();
/*  759 */     int kernelNx = 2 * this.hKernel.length - this.hParity;
/*  760 */     int kernelNy = 2 * this.vKernel.length - this.vParity;
/*  761 */     int stepDown = (kernelNy - 1) * srcScanlineStride;
/*  762 */     int stepRight = (kernelNx - 1) * srcPixelStride;
/*  766 */     float vCtr = this.vKernel[0];
/*  767 */     float hCtr = this.hKernel[0];
/*  770 */     for (int band = 0; band < dnumBands; band++) {
/*  771 */       byte[] dstData = dstDataArrays[band];
/*  772 */       byte[] srcData = srcDataArrays[band];
/*  773 */       int srcScanlineOffset = srcBandOffsets[band];
/*  774 */       int dstScanlineOffset = dstBandOffsets[band];
/*      */       int ySrc;
/*  777 */       for (ySrc = 0; ySrc < this.scaleY * dheight; ySrc += this.scaleY) {
/*  778 */         int dInd = dstScanlineOffset;
/*      */         int xSrc;
/*  779 */         for (xSrc = 0; xSrc < this.scaleX * dwidth; xSrc += this.scaleX) {
/*  781 */           int upLeft0 = xSrc * srcPixelStride + ySrc * srcScanlineStride + srcScanlineOffset;
/*  782 */           int upRight0 = upLeft0 + stepRight;
/*  783 */           int dnLeft0 = upLeft0 + stepDown;
/*  784 */           int dnRight0 = upRight0 + stepDown;
/*  787 */           float sum = 0.0F;
/*  790 */           for (int iy = this.vKernel.length - 1; iy > this.vParity - 1; iy--) {
/*  791 */             int upLeft = upLeft0;
/*  792 */             int upRight = upRight0;
/*  793 */             int dnLeft = dnLeft0;
/*  794 */             int dnRight = dnRight0;
/*  797 */             for (int ix = this.hKernel.length - 1; ix > this.hParity - 1; ix--) {
/*  798 */               float kk = this.hKernel[ix] * this.vKernel[iy];
/*  799 */               sum += kk * ((srcData[upLeft] & 0xFF) + (srcData[upRight] & 0xFF) + (srcData[dnLeft] & 0xFF) + (srcData[dnRight] & 0xFF));
/*  801 */               upLeft += srcPixelStride;
/*  802 */               upRight -= srcPixelStride;
/*  803 */               dnLeft += srcPixelStride;
/*  804 */               dnRight -= srcPixelStride;
/*      */             } 
/*  806 */             upLeft0 += srcScanlineStride;
/*  807 */             upRight0 += srcScanlineStride;
/*  808 */             dnLeft0 -= srcScanlineStride;
/*  809 */             dnRight0 -= srcScanlineStride;
/*      */           } 
/*  815 */           if (this.hParity == 1) {
/*  816 */             int xUp = (xSrc + this.hKernel.length - 1) * srcPixelStride + ySrc * srcScanlineStride + srcScanlineOffset;
/*  818 */             int xDown = xUp + stepDown;
/*  819 */             int kInd = this.vKernel.length - 1;
/*  820 */             while (xUp < xDown) {
/*  821 */               float kk = hCtr * this.vKernel[kInd--];
/*  822 */               sum += kk * ((srcData[xUp] & 0xFF) + (srcData[xDown] & 0xFF));
/*  823 */               xUp += srcScanlineStride;
/*  824 */               xDown -= srcScanlineStride;
/*      */             } 
/*      */           } 
/*  829 */           if (this.vParity == 1) {
/*  830 */             int xLeft = xSrc * srcPixelStride + (ySrc + this.vKernel.length - 1) * srcScanlineStride + srcScanlineOffset;
/*  833 */             int xRight = xLeft + stepRight;
/*  834 */             int kInd = this.hKernel.length - 1;
/*  835 */             while (xLeft < xRight) {
/*  836 */               float kk = vCtr * this.hKernel[kInd--];
/*  837 */               sum += kk * ((srcData[xLeft] & 0xFF) + (srcData[xRight] & 0xFF));
/*  838 */               xLeft += srcPixelStride;
/*  839 */               xRight -= srcPixelStride;
/*      */             } 
/*  843 */             if (this.hParity == 1)
/*  843 */               sum += vCtr * hCtr * (srcData[xLeft] & 0xFF); 
/*      */           } 
/*  848 */           if (sum < 0.0D)
/*  848 */             sum = 0.0F; 
/*  849 */           if (sum > 255.0D)
/*  849 */             sum = 255.0F; 
/*  851 */           dstData[dInd] = (byte)(int)(sum + 0.5D);
/*  853 */           dInd += dstPixelStride;
/*      */         } 
/*  856 */         dstScanlineOffset += dstScanlineStride;
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void computeRectUShort(RasterAccessor src, RasterAccessor dst) {
/*  874 */     int dwidth = dst.getWidth();
/*  875 */     int dheight = dst.getHeight();
/*  876 */     int dnumBands = dst.getNumBands();
/*  879 */     short[][] dstDataArrays = dst.getShortDataArrays();
/*  880 */     int[] dstBandOffsets = dst.getBandOffsets();
/*  881 */     int dstPixelStride = dst.getPixelStride();
/*  882 */     int dstScanlineStride = dst.getScanlineStride();
/*  885 */     short[][] srcDataArrays = src.getShortDataArrays();
/*  886 */     int[] srcBandOffsets = src.getBandOffsets();
/*  887 */     int srcPixelStride = src.getPixelStride();
/*  888 */     int srcScanlineStride = src.getScanlineStride();
/*  891 */     int kernelNx = 2 * this.hKernel.length - this.hParity;
/*  892 */     int kernelNy = 2 * this.vKernel.length - this.vParity;
/*  893 */     int stepDown = (kernelNy - 1) * srcScanlineStride;
/*  894 */     int stepRight = (kernelNx - 1) * srcPixelStride;
/*  898 */     float vCtr = this.vKernel[0];
/*  899 */     float hCtr = this.hKernel[0];
/*  902 */     for (int band = 0; band < dnumBands; band++) {
/*  903 */       short[] dstData = dstDataArrays[band];
/*  904 */       short[] srcData = srcDataArrays[band];
/*  905 */       int srcScanlineOffset = srcBandOffsets[band];
/*  906 */       int dstScanlineOffset = dstBandOffsets[band];
/*      */       int ySrc;
/*  909 */       for (ySrc = 0; ySrc < this.scaleY * dheight; ySrc += this.scaleY) {
/*  910 */         int dInd = dstScanlineOffset;
/*      */         int xSrc;
/*  911 */         for (xSrc = 0; xSrc < this.scaleX * dwidth; xSrc += this.scaleX) {
/*  913 */           int upLeft0 = xSrc * srcPixelStride + ySrc * srcScanlineStride + srcScanlineOffset;
/*  914 */           int upRight0 = upLeft0 + stepRight;
/*  915 */           int dnLeft0 = upLeft0 + stepDown;
/*  916 */           int dnRight0 = upRight0 + stepDown;
/*  919 */           float sum = 0.0F;
/*  922 */           for (int iy = this.vKernel.length - 1; iy > this.vParity - 1; iy--) {
/*  923 */             int upLeft = upLeft0;
/*  924 */             int upRight = upRight0;
/*  925 */             int dnLeft = dnLeft0;
/*  926 */             int dnRight = dnRight0;
/*  929 */             for (int ix = this.hKernel.length - 1; ix > this.hParity - 1; ix--) {
/*  930 */               float kk = this.hKernel[ix] * this.vKernel[iy];
/*  931 */               sum += kk * ((srcData[upLeft] & 0xFFFF) + (srcData[upRight] & 0xFFFF) + (srcData[dnLeft] & 0xFFFF) + (srcData[dnRight] & 0xFFFF));
/*  935 */               upLeft += srcPixelStride;
/*  936 */               upRight -= srcPixelStride;
/*  937 */               dnLeft += srcPixelStride;
/*  938 */               dnRight -= srcPixelStride;
/*      */             } 
/*  940 */             upLeft0 += srcScanlineStride;
/*  941 */             upRight0 += srcScanlineStride;
/*  942 */             dnLeft0 -= srcScanlineStride;
/*  943 */             dnRight0 -= srcScanlineStride;
/*      */           } 
/*  950 */           if (this.hParity == 1) {
/*  951 */             int xUp = (xSrc + this.hKernel.length - 1) * srcPixelStride + ySrc * srcScanlineStride + srcScanlineOffset;
/*  953 */             int xDown = xUp + stepDown;
/*  954 */             int kInd = this.vKernel.length - 1;
/*  955 */             while (xUp < xDown) {
/*  956 */               float kk = hCtr * this.vKernel[kInd--];
/*  957 */               sum += kk * ((srcData[xUp] & 0xFFFF) + (srcData[xDown] & 0xFFFF));
/*  959 */               xUp += srcScanlineStride;
/*  960 */               xDown -= srcScanlineStride;
/*      */             } 
/*      */           } 
/*  965 */           if (this.vParity == 1) {
/*  966 */             int xLeft = xSrc * srcPixelStride + (ySrc + this.vKernel.length - 1) * srcScanlineStride + srcScanlineOffset;
/*  969 */             int xRight = xLeft + stepRight;
/*  970 */             int kInd = this.hKernel.length - 1;
/*  971 */             while (xLeft < xRight) {
/*  972 */               float kk = vCtr * this.hKernel[kInd--];
/*  973 */               sum += kk * ((srcData[xLeft] & 0xFFFF) + (srcData[xRight] & 0xFFFF));
/*  975 */               xLeft += srcPixelStride;
/*  976 */               xRight -= srcPixelStride;
/*      */             } 
/*  980 */             if (this.hParity == 1)
/*  980 */               sum += vCtr * hCtr * (srcData[xLeft] & 0xFFFF); 
/*      */           } 
/*  983 */           int val = (int)(sum + 0.5D);
/*  984 */           dstData[dInd] = (short)((val > 65535) ? 65535 : ((val < 0) ? 0 : val));
/*  986 */           dInd += dstPixelStride;
/*      */         } 
/*  989 */         dstScanlineOffset += dstScanlineStride;
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void computeRectShort(RasterAccessor src, RasterAccessor dst) {
/* 1007 */     int dwidth = dst.getWidth();
/* 1008 */     int dheight = dst.getHeight();
/* 1009 */     int dnumBands = dst.getNumBands();
/* 1012 */     short[][] dstDataArrays = dst.getShortDataArrays();
/* 1013 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 1014 */     int dstPixelStride = dst.getPixelStride();
/* 1015 */     int dstScanlineStride = dst.getScanlineStride();
/* 1018 */     short[][] srcDataArrays = src.getShortDataArrays();
/* 1019 */     int[] srcBandOffsets = src.getBandOffsets();
/* 1020 */     int srcPixelStride = src.getPixelStride();
/* 1021 */     int srcScanlineStride = src.getScanlineStride();
/* 1024 */     int kernelNx = 2 * this.hKernel.length - this.hParity;
/* 1025 */     int kernelNy = 2 * this.vKernel.length - this.vParity;
/* 1026 */     int stepDown = (kernelNy - 1) * srcScanlineStride;
/* 1027 */     int stepRight = (kernelNx - 1) * srcPixelStride;
/* 1031 */     float vCtr = this.vKernel[0];
/* 1032 */     float hCtr = this.hKernel[0];
/* 1035 */     for (int band = 0; band < dnumBands; band++) {
/* 1036 */       short[] dstData = dstDataArrays[band];
/* 1037 */       short[] srcData = srcDataArrays[band];
/* 1038 */       int srcScanlineOffset = srcBandOffsets[band];
/* 1039 */       int dstScanlineOffset = dstBandOffsets[band];
/*      */       int ySrc;
/* 1042 */       for (ySrc = 0; ySrc < this.scaleY * dheight; ySrc += this.scaleY) {
/* 1043 */         int dInd = dstScanlineOffset;
/*      */         int xSrc;
/* 1044 */         for (xSrc = 0; xSrc < this.scaleX * dwidth; xSrc += this.scaleX) {
/* 1046 */           int upLeft0 = xSrc * srcPixelStride + ySrc * srcScanlineStride + srcScanlineOffset;
/* 1047 */           int upRight0 = upLeft0 + stepRight;
/* 1048 */           int dnLeft0 = upLeft0 + stepDown;
/* 1049 */           int dnRight0 = upRight0 + stepDown;
/* 1052 */           float sum = 0.0F;
/* 1055 */           for (int iy = this.vKernel.length - 1; iy > this.vParity - 1; iy--) {
/* 1056 */             int upLeft = upLeft0;
/* 1057 */             int upRight = upRight0;
/* 1058 */             int dnLeft = dnLeft0;
/* 1059 */             int dnRight = dnRight0;
/* 1062 */             for (int ix = this.hKernel.length - 1; ix > this.hParity - 1; ix--) {
/* 1063 */               float kk = this.hKernel[ix] * this.vKernel[iy];
/* 1064 */               sum += kk * (srcData[upLeft] + srcData[upRight] + srcData[dnLeft] + srcData[dnRight]);
/* 1068 */               upLeft += srcPixelStride;
/* 1069 */               upRight -= srcPixelStride;
/* 1070 */               dnLeft += srcPixelStride;
/* 1071 */               dnRight -= srcPixelStride;
/*      */             } 
/* 1073 */             upLeft0 += srcScanlineStride;
/* 1074 */             upRight0 += srcScanlineStride;
/* 1075 */             dnLeft0 -= srcScanlineStride;
/* 1076 */             dnRight0 -= srcScanlineStride;
/*      */           } 
/* 1083 */           if (this.hParity == 1) {
/* 1084 */             int xUp = (xSrc + this.hKernel.length - 1) * srcPixelStride + ySrc * srcScanlineStride + srcScanlineOffset;
/* 1086 */             int xDown = xUp + stepDown;
/* 1087 */             int kInd = this.vKernel.length - 1;
/* 1088 */             while (xUp < xDown) {
/* 1089 */               float kk = hCtr * this.vKernel[kInd--];
/* 1090 */               sum += kk * (srcData[xUp] + srcData[xDown]);
/* 1092 */               xUp += srcScanlineStride;
/* 1093 */               xDown -= srcScanlineStride;
/*      */             } 
/*      */           } 
/* 1098 */           if (this.vParity == 1) {
/* 1099 */             int xLeft = xSrc * srcPixelStride + (ySrc + this.vKernel.length - 1) * srcScanlineStride + srcScanlineOffset;
/* 1102 */             int xRight = xLeft + stepRight;
/* 1103 */             int kInd = this.hKernel.length - 1;
/* 1104 */             while (xLeft < xRight) {
/* 1105 */               float kk = vCtr * this.hKernel[kInd--];
/* 1106 */               sum += kk * (srcData[xLeft] + srcData[xRight]);
/* 1108 */               xLeft += srcPixelStride;
/* 1109 */               xRight -= srcPixelStride;
/*      */             } 
/* 1113 */             if (this.hParity == 1)
/* 1113 */               sum += vCtr * hCtr * srcData[xLeft]; 
/*      */           } 
/* 1117 */           dstData[dInd] = ImageUtil.clampShort((int)(sum + 0.5D));
/* 1118 */           dInd += dstPixelStride;
/*      */         } 
/* 1122 */         dstScanlineOffset += dstScanlineStride;
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void computeRectInt(RasterAccessor src, RasterAccessor dst) {
/* 1140 */     int dwidth = dst.getWidth();
/* 1141 */     int dheight = dst.getHeight();
/* 1142 */     int dnumBands = dst.getNumBands();
/* 1145 */     int[][] dstDataArrays = dst.getIntDataArrays();
/* 1146 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 1147 */     int dstPixelStride = dst.getPixelStride();
/* 1148 */     int dstScanlineStride = dst.getScanlineStride();
/* 1151 */     int[][] srcDataArrays = src.getIntDataArrays();
/* 1152 */     int[] srcBandOffsets = src.getBandOffsets();
/* 1153 */     int srcPixelStride = src.getPixelStride();
/* 1154 */     int srcScanlineStride = src.getScanlineStride();
/* 1157 */     int kernelNx = 2 * this.hKernel.length - this.hParity;
/* 1158 */     int kernelNy = 2 * this.vKernel.length - this.vParity;
/* 1159 */     int stepDown = (kernelNy - 1) * srcScanlineStride;
/* 1160 */     int stepRight = (kernelNx - 1) * srcPixelStride;
/* 1164 */     double vCtr = this.vKernel[0];
/* 1165 */     double hCtr = this.hKernel[0];
/* 1168 */     for (int band = 0; band < dnumBands; band++) {
/* 1169 */       int[] dstData = dstDataArrays[band];
/* 1170 */       int[] srcData = srcDataArrays[band];
/* 1171 */       int srcScanlineOffset = srcBandOffsets[band];
/* 1172 */       int dstScanlineOffset = dstBandOffsets[band];
/*      */       int ySrc;
/* 1175 */       for (ySrc = 0; ySrc < this.scaleY * dheight; ySrc += this.scaleY) {
/* 1176 */         int dInd = dstScanlineOffset;
/*      */         int xSrc;
/* 1177 */         for (xSrc = 0; xSrc < this.scaleX * dwidth; xSrc += this.scaleX) {
/* 1179 */           int upLeft0 = xSrc * srcPixelStride + ySrc * srcScanlineStride + srcScanlineOffset;
/* 1180 */           int upRight0 = upLeft0 + stepRight;
/* 1181 */           int dnLeft0 = upLeft0 + stepDown;
/* 1182 */           int dnRight0 = upRight0 + stepDown;
/* 1185 */           double sum = 0.0D;
/* 1188 */           for (int iy = this.vKernel.length - 1; iy > this.vParity - 1; iy--) {
/* 1189 */             int upLeft = upLeft0;
/* 1190 */             int upRight = upRight0;
/* 1191 */             int dnLeft = dnLeft0;
/* 1192 */             int dnRight = dnRight0;
/* 1195 */             for (int ix = this.hKernel.length - 1; ix > this.hParity - 1; ix--) {
/* 1196 */               double kk = (this.hKernel[ix] * this.vKernel[iy]);
/* 1197 */               sum += kk * (srcData[upLeft] + srcData[upRight] + srcData[dnLeft] + srcData[dnRight]);
/* 1199 */               upLeft += srcPixelStride;
/* 1200 */               upRight -= srcPixelStride;
/* 1201 */               dnLeft += srcPixelStride;
/* 1202 */               dnRight -= srcPixelStride;
/*      */             } 
/* 1204 */             upLeft0 += srcScanlineStride;
/* 1205 */             upRight0 += srcScanlineStride;
/* 1206 */             dnLeft0 -= srcScanlineStride;
/* 1207 */             dnRight0 -= srcScanlineStride;
/*      */           } 
/* 1214 */           if (this.hParity == 1) {
/* 1215 */             int xUp = (xSrc + this.hKernel.length - 1) * srcPixelStride + ySrc * srcScanlineStride + srcScanlineOffset;
/* 1217 */             int xDown = xUp + stepDown;
/* 1218 */             int kInd = this.vKernel.length - 1;
/* 1219 */             while (xUp < xDown) {
/* 1220 */               double kk = hCtr * this.vKernel[kInd--];
/* 1221 */               sum += kk * (srcData[xUp] + srcData[xDown]);
/* 1222 */               xUp += srcScanlineStride;
/* 1223 */               xDown -= srcScanlineStride;
/*      */             } 
/*      */           } 
/* 1228 */           if (this.vParity == 1) {
/* 1229 */             int xLeft = xSrc * srcPixelStride + (ySrc + this.vKernel.length - 1) * srcScanlineStride + srcScanlineOffset;
/* 1232 */             int xRight = xLeft + stepRight;
/* 1233 */             int kInd = this.hKernel.length - 1;
/* 1234 */             while (xLeft < xRight) {
/* 1235 */               double kk = vCtr * this.hKernel[kInd--];
/* 1236 */               sum += kk * (srcData[xLeft] + srcData[xRight]);
/* 1237 */               xLeft += srcPixelStride;
/* 1238 */               xRight -= srcPixelStride;
/*      */             } 
/* 1242 */             if (this.hParity == 1)
/* 1242 */               sum += vCtr * hCtr * srcData[xLeft]; 
/*      */           } 
/* 1246 */           dstData[dInd] = ImageUtil.clampInt((int)(sum + 0.5D));
/* 1248 */           dInd += dstPixelStride;
/*      */         } 
/* 1251 */         dstScanlineOffset += dstScanlineStride;
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void computeRectFloat(RasterAccessor src, RasterAccessor dst) {
/* 1269 */     int dwidth = dst.getWidth();
/* 1270 */     int dheight = dst.getHeight();
/* 1271 */     int dnumBands = dst.getNumBands();
/* 1274 */     float[][] dstDataArrays = dst.getFloatDataArrays();
/* 1275 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 1276 */     int dstPixelStride = dst.getPixelStride();
/* 1277 */     int dstScanlineStride = dst.getScanlineStride();
/* 1280 */     float[][] srcDataArrays = src.getFloatDataArrays();
/* 1281 */     int[] srcBandOffsets = src.getBandOffsets();
/* 1282 */     int srcPixelStride = src.getPixelStride();
/* 1283 */     int srcScanlineStride = src.getScanlineStride();
/* 1286 */     int kernelNx = 2 * this.hKernel.length - this.hParity;
/* 1287 */     int kernelNy = 2 * this.vKernel.length - this.vParity;
/* 1288 */     int stepDown = (kernelNy - 1) * srcScanlineStride;
/* 1289 */     int stepRight = (kernelNx - 1) * srcPixelStride;
/* 1293 */     double vCtr = this.vKernel[0];
/* 1294 */     double hCtr = this.hKernel[0];
/* 1297 */     for (int band = 0; band < dnumBands; band++) {
/* 1298 */       float[] dstData = dstDataArrays[band];
/* 1299 */       float[] srcData = srcDataArrays[band];
/* 1300 */       int srcScanlineOffset = srcBandOffsets[band];
/* 1301 */       int dstScanlineOffset = dstBandOffsets[band];
/*      */       int ySrc;
/* 1304 */       for (ySrc = 0; ySrc < this.scaleY * dheight; ySrc += this.scaleY) {
/* 1305 */         int dInd = dstScanlineOffset;
/*      */         int xSrc;
/* 1306 */         for (xSrc = 0; xSrc < this.scaleX * dwidth; xSrc += this.scaleX) {
/* 1308 */           int upLeft0 = xSrc * srcPixelStride + ySrc * srcScanlineStride + srcScanlineOffset;
/* 1309 */           int upRight0 = upLeft0 + stepRight;
/* 1310 */           int dnLeft0 = upLeft0 + stepDown;
/* 1311 */           int dnRight0 = upRight0 + stepDown;
/* 1314 */           double sum = 0.0D;
/* 1317 */           for (int iy = this.vKernel.length - 1; iy > this.vParity - 1; iy--) {
/* 1318 */             int upLeft = upLeft0;
/* 1319 */             int upRight = upRight0;
/* 1320 */             int dnLeft = dnLeft0;
/* 1321 */             int dnRight = dnRight0;
/* 1324 */             for (int ix = this.hKernel.length - 1; ix > this.hParity - 1; ix--) {
/* 1325 */               double kk = (this.hKernel[ix] * this.vKernel[iy]);
/* 1326 */               sum += kk * (srcData[upLeft] + srcData[upRight] + srcData[dnLeft] + srcData[dnRight]);
/* 1328 */               upLeft += srcPixelStride;
/* 1329 */               upRight -= srcPixelStride;
/* 1330 */               dnLeft += srcPixelStride;
/* 1331 */               dnRight -= srcPixelStride;
/*      */             } 
/* 1333 */             upLeft0 += srcScanlineStride;
/* 1334 */             upRight0 += srcScanlineStride;
/* 1335 */             dnLeft0 -= srcScanlineStride;
/* 1336 */             dnRight0 -= srcScanlineStride;
/*      */           } 
/* 1343 */           if (this.hParity == 1) {
/* 1344 */             int xUp = (xSrc + this.hKernel.length - 1) * srcPixelStride + ySrc * srcScanlineStride + srcScanlineOffset;
/* 1346 */             int xDown = xUp + stepDown;
/* 1347 */             int kInd = this.vKernel.length - 1;
/* 1348 */             while (xUp < xDown) {
/* 1349 */               double kk = hCtr * this.vKernel[kInd--];
/* 1350 */               sum += kk * (srcData[xUp] + srcData[xDown]);
/* 1351 */               xUp += srcScanlineStride;
/* 1352 */               xDown -= srcScanlineStride;
/*      */             } 
/*      */           } 
/* 1357 */           if (this.vParity == 1) {
/* 1358 */             int xLeft = xSrc * srcPixelStride + (ySrc + this.vKernel.length - 1) * srcScanlineStride + srcScanlineOffset;
/* 1361 */             int xRight = xLeft + stepRight;
/* 1362 */             int kInd = this.hKernel.length - 1;
/* 1363 */             while (xLeft < xRight) {
/* 1364 */               double kk = vCtr * this.hKernel[kInd--];
/* 1365 */               sum += kk * (srcData[xLeft] + srcData[xRight]);
/* 1366 */               xLeft += srcPixelStride;
/* 1367 */               xRight -= srcPixelStride;
/*      */             } 
/* 1371 */             if (this.hParity == 1)
/* 1371 */               sum += vCtr * hCtr * srcData[xLeft]; 
/*      */           } 
/* 1375 */           dstData[dInd] = ImageUtil.clampFloat(sum);
/* 1377 */           dInd += dstPixelStride;
/*      */         } 
/* 1380 */         dstScanlineOffset += dstScanlineStride;
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void computeRectDouble(RasterAccessor src, RasterAccessor dst) {
/* 1398 */     int dwidth = dst.getWidth();
/* 1399 */     int dheight = dst.getHeight();
/* 1400 */     int dnumBands = dst.getNumBands();
/* 1403 */     double[][] dstDataArrays = dst.getDoubleDataArrays();
/* 1404 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 1405 */     int dstPixelStride = dst.getPixelStride();
/* 1406 */     int dstScanlineStride = dst.getScanlineStride();
/* 1409 */     double[][] srcDataArrays = src.getDoubleDataArrays();
/* 1410 */     int[] srcBandOffsets = src.getBandOffsets();
/* 1411 */     int srcPixelStride = src.getPixelStride();
/* 1412 */     int srcScanlineStride = src.getScanlineStride();
/* 1415 */     int kernelNx = 2 * this.hKernel.length - this.hParity;
/* 1416 */     int kernelNy = 2 * this.vKernel.length - this.vParity;
/* 1417 */     int stepDown = (kernelNy - 1) * srcScanlineStride;
/* 1418 */     int stepRight = (kernelNx - 1) * srcPixelStride;
/* 1422 */     double vCtr = this.vKernel[0];
/* 1423 */     double hCtr = this.hKernel[0];
/* 1426 */     for (int band = 0; band < dnumBands; band++) {
/* 1427 */       double[] dstData = dstDataArrays[band];
/* 1428 */       double[] srcData = srcDataArrays[band];
/* 1429 */       int srcScanlineOffset = srcBandOffsets[band];
/* 1430 */       int dstScanlineOffset = dstBandOffsets[band];
/*      */       int ySrc;
/* 1433 */       for (ySrc = 0; ySrc < this.scaleY * dheight; ySrc += this.scaleY) {
/* 1434 */         int dInd = dstScanlineOffset;
/*      */         int xSrc;
/* 1435 */         for (xSrc = 0; xSrc < this.scaleX * dwidth; xSrc += this.scaleX) {
/* 1437 */           int upLeft0 = xSrc * srcPixelStride + ySrc * srcScanlineStride + srcScanlineOffset;
/* 1438 */           int upRight0 = upLeft0 + stepRight;
/* 1439 */           int dnLeft0 = upLeft0 + stepDown;
/* 1440 */           int dnRight0 = upRight0 + stepDown;
/* 1443 */           double sum = 0.0D;
/* 1446 */           for (int iy = this.vKernel.length - 1; iy > this.vParity - 1; iy--) {
/* 1447 */             int upLeft = upLeft0;
/* 1448 */             int upRight = upRight0;
/* 1449 */             int dnLeft = dnLeft0;
/* 1450 */             int dnRight = dnRight0;
/* 1453 */             for (int ix = this.hKernel.length - 1; ix > this.hParity - 1; ix--) {
/* 1454 */               double kk = (this.hKernel[ix] * this.vKernel[iy]);
/* 1455 */               sum += kk * (srcData[upLeft] + srcData[upRight] + srcData[dnLeft] + srcData[dnRight]);
/* 1457 */               upLeft += srcPixelStride;
/* 1458 */               upRight -= srcPixelStride;
/* 1459 */               dnLeft += srcPixelStride;
/* 1460 */               dnRight -= srcPixelStride;
/*      */             } 
/* 1462 */             upLeft0 += srcScanlineStride;
/* 1463 */             upRight0 += srcScanlineStride;
/* 1464 */             dnLeft0 -= srcScanlineStride;
/* 1465 */             dnRight0 -= srcScanlineStride;
/*      */           } 
/* 1472 */           if (this.hParity == 1) {
/* 1473 */             int xUp = (xSrc + this.hKernel.length - 1) * srcPixelStride + ySrc * srcScanlineStride + srcScanlineOffset;
/* 1475 */             int xDown = xUp + stepDown;
/* 1476 */             int kInd = this.vKernel.length - 1;
/* 1477 */             while (xUp < xDown) {
/* 1478 */               double kk = hCtr * this.vKernel[kInd--];
/* 1479 */               sum += kk * (srcData[xUp] + srcData[xDown]);
/* 1480 */               xUp += srcScanlineStride;
/* 1481 */               xDown -= srcScanlineStride;
/*      */             } 
/*      */           } 
/* 1486 */           if (this.vParity == 1) {
/* 1487 */             int xLeft = xSrc * srcPixelStride + (ySrc + this.vKernel.length - 1) * srcScanlineStride + srcScanlineOffset;
/* 1490 */             int xRight = xLeft + stepRight;
/* 1491 */             int kInd = this.hKernel.length - 1;
/* 1492 */             while (xLeft < xRight) {
/* 1493 */               double kk = vCtr * this.hKernel[kInd--];
/* 1494 */               sum += kk * (srcData[xLeft] + srcData[xRight]);
/* 1495 */               xLeft += srcPixelStride;
/* 1496 */               xRight -= srcPixelStride;
/*      */             } 
/* 1500 */             if (this.hParity == 1)
/* 1500 */               sum += vCtr * hCtr * srcData[xLeft]; 
/*      */           } 
/* 1504 */           dstData[dInd] = sum;
/* 1506 */           dInd += dstPixelStride;
/*      */         } 
/* 1509 */         dstScanlineOffset += dstScanlineStride;
/*      */       } 
/*      */     } 
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\FilteredSubsampleOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */