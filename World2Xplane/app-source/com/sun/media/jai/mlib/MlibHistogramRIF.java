/*     */ package com.sun.media.jai.mlib;
/*     */ 
/*     */ import com.sun.media.jai.util.ImageUtil;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.renderable.ParameterBlock;
/*     */ import java.awt.image.renderable.RenderedImageFactory;
/*     */ import javax.media.jai.ROI;
/*     */ import javax.media.jai.util.ImagingListener;
/*     */ 
/*     */ public class MlibHistogramRIF implements RenderedImageFactory {
/*     */   public RenderedImage create(ParameterBlock args, RenderingHints hints) {
/*     */     int minPixelValue, maxPixelValue;
/*  49 */     if (!MediaLibAccessor.isMediaLibCompatible(args))
/*  50 */       return null; 
/*  54 */     RenderedImage src = args.getRenderedSource(0);
/*  55 */     int dataType = src.getSampleModel().getDataType();
/*  56 */     if (dataType == 4 || dataType == 5)
/*  58 */       return null; 
/*  62 */     ROI roi = (ROI)args.getObjectParameter(0);
/*  63 */     if (roi != null && !roi.equals(new Rectangle(src.getMinX(), src.getMinY(), src.getWidth(), src.getHeight())))
/*  66 */       return null; 
/*  70 */     int xPeriod = args.getIntParameter(1);
/*  71 */     int yPeriod = args.getIntParameter(2);
/*  72 */     int[] numBins = (int[])args.getObjectParameter(3);
/*  73 */     double[] lowValueFP = (double[])args.getObjectParameter(4);
/*  74 */     double[] highValueFP = (double[])args.getObjectParameter(5);
/*  79 */     switch (dataType) {
/*     */       case 2:
/*  81 */         minPixelValue = -32768;
/*  82 */         maxPixelValue = 32767;
/*     */         break;
/*     */       case 1:
/*  85 */         minPixelValue = 0;
/*  86 */         maxPixelValue = 65535;
/*     */         break;
/*     */       case 3:
/*  89 */         minPixelValue = Integer.MIN_VALUE;
/*  90 */         maxPixelValue = Integer.MAX_VALUE;
/*     */         break;
/*     */       default:
/*  94 */         minPixelValue = 0;
/*  95 */         maxPixelValue = 255;
/*     */         break;
/*     */     } 
/*     */     int i;
/*  98 */     for (i = 0; i < lowValueFP.length; i++) {
/*  99 */       if (lowValueFP[i] < minPixelValue || lowValueFP[i] > maxPixelValue)
/* 101 */         return null; 
/*     */     } 
/* 104 */     for (i = 0; i < highValueFP.length; i++) {
/* 105 */       if (highValueFP[i] <= minPixelValue || highValueFP[i] > (maxPixelValue + 1))
/* 107 */         return null; 
/*     */     } 
/* 111 */     MlibHistogramOpImage op = null;
/*     */     try {
/* 113 */       op = new MlibHistogramOpImage(src, xPeriod, yPeriod, numBins, lowValueFP, highValueFP);
/* 116 */     } catch (Exception e) {
/* 117 */       ImagingListener listener = ImageUtil.getImagingListener(hints);
/* 118 */       String message = JaiI18N.getString("MlibHistogramRIF0");
/* 119 */       listener.errorOccurred(message, e, this, false);
/*     */     } 
/* 122 */     return (RenderedImage)op;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\mlib\MlibHistogramRIF.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */