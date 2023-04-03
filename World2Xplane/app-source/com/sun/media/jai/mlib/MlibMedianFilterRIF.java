/*    */ package com.sun.media.jai.mlib;
/*    */ 
/*    */ import com.sun.media.jai.opimage.RIFUtil;
/*    */ import java.awt.RenderingHints;
/*    */ import java.awt.image.RenderedImage;
/*    */ import java.awt.image.renderable.ParameterBlock;
/*    */ import java.awt.image.renderable.RenderedImageFactory;
/*    */ import javax.media.jai.BorderExtender;
/*    */ import javax.media.jai.ImageLayout;
/*    */ import javax.media.jai.operator.MedianFilterShape;
/*    */ 
/*    */ public class MlibMedianFilterRIF implements RenderedImageFactory {
/*    */   public RenderedImage create(ParameterBlock paramBlock, RenderingHints renderHints) {
/* 44 */     ImageLayout layout = RIFUtil.getImageLayoutHint(renderHints);
/* 47 */     if (!MediaLibAccessor.isMediaLibCompatible(paramBlock, layout) || !MediaLibAccessor.hasSameNumBands(paramBlock, layout))
/* 49 */       return null; 
/* 53 */     BorderExtender extender = RIFUtil.getBorderExtenderHint(renderHints);
/* 55 */     MedianFilterShape maskType = (MedianFilterShape)paramBlock.getObjectParameter(0);
/* 57 */     int maskSize = paramBlock.getIntParameter(1);
/* 58 */     RenderedImage ri = paramBlock.getRenderedSource(0);
/* 60 */     return (RenderedImage)new MlibMedianFilterOpImage(ri, extender, renderHints, layout, maskType, maskSize);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\mlib\MlibMedianFilterRIF.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */