/*    */ package com.sun.media.jai.mlib;
/*    */ 
/*    */ import java.awt.RenderingHints;
/*    */ import java.awt.image.RenderedImage;
/*    */ import java.awt.image.SampleModel;
/*    */ import java.awt.image.renderable.ParameterBlock;
/*    */ import java.awt.image.renderable.RenderedImageFactory;
/*    */ import javax.media.jai.BorderExtender;
/*    */ import javax.media.jai.ImageLayout;
/*    */ import javax.media.jai.Interpolation;
/*    */ import javax.media.jai.JAI;
/*    */ 
/*    */ public class MlibFilteredSubsampleRIF implements RenderedImageFactory {
/*    */   public RenderedImage create(ParameterBlock paramBlock, RenderingHints renderHints) {
/* 47 */     BorderExtender extender = (renderHints == null) ? null : (BorderExtender)renderHints.get(JAI.KEY_BORDER_EXTENDER);
/* 49 */     ImageLayout layout = (renderHints == null) ? null : (ImageLayout)renderHints.get(JAI.KEY_IMAGE_LAYOUT);
/* 53 */     if (!MediaLibAccessor.isMediaLibCompatible(paramBlock, layout) || !MediaLibAccessor.hasSameNumBands(paramBlock, layout))
/* 55 */       return null; 
/* 58 */     RenderedImage source = paramBlock.getRenderedSource(0);
/* 59 */     SampleModel sm = source.getSampleModel();
/* 60 */     boolean isBilevel = (sm instanceof java.awt.image.MultiPixelPackedSampleModel && sm.getSampleSize(0) == 1 && (sm.getDataType() == 0 || sm.getDataType() == 1 || sm.getDataType() == 3));
/* 65 */     if (isBilevel)
/* 67 */       return null; 
/* 70 */     int scaleX = paramBlock.getIntParameter(0);
/* 71 */     int scaleY = paramBlock.getIntParameter(1);
/* 72 */     float[] qsFilter = (float[])paramBlock.getObjectParameter(2);
/* 73 */     Interpolation interp = (Interpolation)paramBlock.getObjectParameter(3);
/* 75 */     return (RenderedImage)new MlibFilteredSubsampleOpImage(source, extender, renderHints, layout, scaleX, scaleY, qsFilter, interp);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\mlib\MlibFilteredSubsampleRIF.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */