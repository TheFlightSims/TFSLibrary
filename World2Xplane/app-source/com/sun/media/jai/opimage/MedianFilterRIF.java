/*    */ package com.sun.media.jai.opimage;
/*    */ 
/*    */ import java.awt.RenderingHints;
/*    */ import java.awt.image.RenderedImage;
/*    */ import java.awt.image.renderable.ParameterBlock;
/*    */ import java.awt.image.renderable.RenderedImageFactory;
/*    */ import javax.media.jai.BorderExtender;
/*    */ import javax.media.jai.ImageLayout;
/*    */ import javax.media.jai.operator.MedianFilterDescriptor;
/*    */ import javax.media.jai.operator.MedianFilterShape;
/*    */ 
/*    */ public class MedianFilterRIF implements RenderedImageFactory {
/*    */   public RenderedImage create(ParameterBlock paramBlock, RenderingHints renderHints) {
/* 43 */     ImageLayout layout = RIFUtil.getImageLayoutHint(renderHints);
/* 47 */     BorderExtender extender = RIFUtil.getBorderExtenderHint(renderHints);
/* 49 */     MedianFilterShape maskType = (MedianFilterShape)paramBlock.getObjectParameter(0);
/* 51 */     int maskSize = paramBlock.getIntParameter(1);
/* 52 */     RenderedImage ri = paramBlock.getRenderedSource(0);
/* 54 */     if (maskType.equals(MedianFilterDescriptor.MEDIAN_MASK_SQUARE))
/* 55 */       return (RenderedImage)new MedianFilterSquareOpImage(ri, extender, renderHints, layout, maskSize); 
/* 60 */     if (maskType.equals(MedianFilterDescriptor.MEDIAN_MASK_PLUS))
/* 61 */       return (RenderedImage)new MedianFilterPlusOpImage(ri, extender, renderHints, layout, maskSize); 
/* 66 */     if (maskType.equals(MedianFilterDescriptor.MEDIAN_MASK_X))
/* 67 */       return (RenderedImage)new MedianFilterXOpImage(ri, extender, renderHints, layout, maskSize); 
/* 72 */     if (maskType.equals(MedianFilterDescriptor.MEDIAN_MASK_SQUARE_SEPARABLE))
/* 73 */       return (RenderedImage)new MedianFilterSeparableOpImage(ri, extender, renderHints, layout, maskSize); 
/* 79 */     return null;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\MedianFilterRIF.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */