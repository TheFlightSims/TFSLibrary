/*    */ package com.sun.media.jai.mlib;
/*    */ 
/*    */ import java.awt.RenderingHints;
/*    */ import java.awt.image.RenderedImage;
/*    */ import java.awt.image.renderable.ParameterBlock;
/*    */ import java.awt.image.renderable.RenderedImageFactory;
/*    */ import javax.media.jai.ROI;
/*    */ 
/*    */ public class MlibExtremaRIF implements RenderedImageFactory {
/*    */   public RenderedImage create(ParameterBlock args, RenderingHints hints) {
/* 44 */     if (!MediaLibAccessor.isMediaLibCompatible(args))
/* 45 */       return null; 
/* 48 */     RenderedImage source = args.getRenderedSource(0);
/* 49 */     ROI roi = (ROI)args.getObjectParameter(0);
/* 50 */     int xPeriod = args.getIntParameter(1);
/* 51 */     int yPeriod = args.getIntParameter(2);
/* 52 */     boolean saveLocations = ((Boolean)args.getObjectParameter(3)).booleanValue();
/* 53 */     int maxRuns = args.getIntParameter(4);
/* 55 */     int xStart = source.getMinX();
/* 56 */     int yStart = source.getMinY();
/* 58 */     int maxWidth = source.getWidth();
/* 59 */     int maxHeight = source.getHeight();
/* 61 */     if (roi != null && !roi.contains(xStart, yStart, maxWidth, maxHeight))
/* 63 */       return null; 
/* 66 */     return (RenderedImage)new MlibExtremaOpImage(source, roi, xStart, yStart, xPeriod, yPeriod, saveLocations, maxRuns);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\mlib\MlibExtremaRIF.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */