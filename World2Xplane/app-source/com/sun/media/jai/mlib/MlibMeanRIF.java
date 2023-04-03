/*    */ package com.sun.media.jai.mlib;
/*    */ 
/*    */ import java.awt.RenderingHints;
/*    */ import java.awt.image.RenderedImage;
/*    */ import java.awt.image.renderable.ParameterBlock;
/*    */ import java.awt.image.renderable.RenderedImageFactory;
/*    */ import javax.media.jai.ROI;
/*    */ 
/*    */ public class MlibMeanRIF implements RenderedImageFactory {
/*    */   public RenderedImage create(ParameterBlock args, RenderingHints hints) {
/* 44 */     if (!MediaLibAccessor.isMediaLibCompatible(args))
/* 45 */       return null; 
/* 48 */     RenderedImage source = args.getRenderedSource(0);
/* 49 */     ROI roi = (ROI)args.getObjectParameter(0);
/* 50 */     int xPeriod = args.getIntParameter(1);
/* 51 */     int yPeriod = args.getIntParameter(2);
/* 53 */     int xStart = source.getMinX();
/* 54 */     int yStart = source.getMinY();
/* 56 */     int maxWidth = source.getWidth();
/* 57 */     int maxHeight = source.getHeight();
/* 59 */     if (roi != null && !roi.contains(xStart, yStart, maxWidth, maxHeight))
/* 61 */       return null; 
/* 65 */     if (xPeriod != 1 || yPeriod != 1)
/* 66 */       return null; 
/* 69 */     return (RenderedImage)new MlibMeanOpImage(source, roi, xStart, yStart, xPeriod, yPeriod);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\mlib\MlibMeanRIF.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */