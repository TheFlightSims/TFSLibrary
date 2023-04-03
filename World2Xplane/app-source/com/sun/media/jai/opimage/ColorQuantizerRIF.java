/*    */ package com.sun.media.jai.opimage;
/*    */ 
/*    */ import java.awt.RenderingHints;
/*    */ import java.awt.image.RenderedImage;
/*    */ import java.awt.image.SampleModel;
/*    */ import java.awt.image.renderable.ParameterBlock;
/*    */ import java.awt.image.renderable.RenderedImageFactory;
/*    */ import javax.media.jai.ImageLayout;
/*    */ import javax.media.jai.JAI;
/*    */ import javax.media.jai.ROI;
/*    */ import javax.media.jai.operator.ColorQuantizerDescriptor;
/*    */ import javax.media.jai.operator.ColorQuantizerType;
/*    */ 
/*    */ public class ColorQuantizerRIF implements RenderedImageFactory {
/*    */   public RenderedImage create(ParameterBlock paramBlock, RenderingHints renderHints) {
/* 50 */     RenderedImage source = paramBlock.getRenderedSource(0);
/* 52 */     ImageLayout layout = (renderHints == null) ? null : (ImageLayout)renderHints.get(JAI.KEY_IMAGE_LAYOUT);
/* 55 */     ColorQuantizerType algorithm = (ColorQuantizerType)paramBlock.getObjectParameter(0);
/* 57 */     int maxColorNum = paramBlock.getIntParameter(1);
/* 58 */     int upperBound = paramBlock.getIntParameter(2);
/* 59 */     ROI roi = (ROI)paramBlock.getObjectParameter(3);
/* 60 */     int xPeriod = paramBlock.getIntParameter(4);
/* 61 */     int yPeriod = paramBlock.getIntParameter(5);
/* 64 */     SampleModel sm = source.getSampleModel();
/* 65 */     if (sm.getNumBands() != 3 && sm.getDataType() == 0)
/* 66 */       throw new IllegalArgumentException("ColorQuantizerRIF0"); 
/* 68 */     if (algorithm.equals(ColorQuantizerDescriptor.NEUQUANT))
/* 69 */       return (RenderedImage)new NeuQuantOpImage(source, renderHints, layout, maxColorNum, upperBound, roi, xPeriod, yPeriod); 
/* 73 */     if (algorithm.equals(ColorQuantizerDescriptor.OCTTREE))
/* 74 */       return (RenderedImage)new OctTreeOpImage(source, renderHints, layout, maxColorNum, upperBound, roi, xPeriod, yPeriod); 
/* 78 */     return (RenderedImage)new MedianCutOpImage(source, renderHints, layout, maxColorNum, upperBound, roi, xPeriod, yPeriod);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\ColorQuantizerRIF.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */