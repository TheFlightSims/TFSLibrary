/*    */ package com.sun.media.jai.opimage;
/*    */ 
/*    */ import com.sun.media.jai.util.ImageUtil;
/*    */ import java.awt.RenderingHints;
/*    */ import java.awt.image.RenderedImage;
/*    */ import java.awt.image.renderable.ParameterBlock;
/*    */ import java.awt.image.renderable.RenderedImageFactory;
/*    */ import javax.media.jai.ROI;
/*    */ import javax.media.jai.util.ImagingListener;
/*    */ 
/*    */ public class HistogramRIF implements RenderedImageFactory {
/*    */   public RenderedImage create(ParameterBlock args, RenderingHints hints) {
/* 44 */     RenderedImage src = args.getRenderedSource(0);
/* 46 */     int xStart = src.getMinX();
/* 47 */     int yStart = src.getMinY();
/* 49 */     int maxWidth = src.getWidth();
/* 50 */     int maxHeight = src.getHeight();
/* 52 */     ROI roi = (ROI)args.getObjectParameter(0);
/* 53 */     int xPeriod = args.getIntParameter(1);
/* 54 */     int yPeriod = args.getIntParameter(2);
/* 55 */     int[] numBins = (int[])args.getObjectParameter(3);
/* 56 */     double[] lowValue = (double[])args.getObjectParameter(4);
/* 57 */     double[] highValue = (double[])args.getObjectParameter(5);
/* 59 */     HistogramOpImage op = null;
/*    */     try {
/* 61 */       op = new HistogramOpImage(src, roi, xStart, yStart, xPeriod, yPeriod, numBins, lowValue, highValue);
/* 66 */     } catch (Exception e) {
/* 67 */       ImagingListener listener = ImageUtil.getImagingListener(hints);
/* 68 */       String message = JaiI18N.getString("HistogramRIF0");
/* 69 */       listener.errorOccurred(message, e, this, false);
/*    */     } 
/* 72 */     return (RenderedImage)op;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\HistogramRIF.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */