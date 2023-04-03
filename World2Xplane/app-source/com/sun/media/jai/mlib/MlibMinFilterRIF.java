/*    */ package com.sun.media.jai.mlib;
/*    */ 
/*    */ import com.sun.media.jai.opimage.RIFUtil;
/*    */ import java.awt.RenderingHints;
/*    */ import java.awt.image.RenderedImage;
/*    */ import java.awt.image.renderable.ParameterBlock;
/*    */ import java.awt.image.renderable.RenderedImageFactory;
/*    */ import javax.media.jai.BorderExtender;
/*    */ import javax.media.jai.ImageLayout;
/*    */ import javax.media.jai.operator.MinFilterDescriptor;
/*    */ import javax.media.jai.operator.MinFilterShape;
/*    */ 
/*    */ public class MlibMinFilterRIF implements RenderedImageFactory {
/*    */   public RenderedImage create(ParameterBlock paramBlock, RenderingHints renderHints) {
/* 44 */     ImageLayout layout = RIFUtil.getImageLayoutHint(renderHints);
/* 47 */     if (!MediaLibAccessor.isMediaLibCompatible(paramBlock, layout) || !MediaLibAccessor.hasSameNumBands(paramBlock, layout))
/* 49 */       return null; 
/* 53 */     BorderExtender extender = RIFUtil.getBorderExtenderHint(renderHints);
/* 55 */     MinFilterShape maskType = (MinFilterShape)paramBlock.getObjectParameter(0);
/* 57 */     int maskSize = paramBlock.getIntParameter(1);
/* 58 */     RenderedImage ri = paramBlock.getRenderedSource(0);
/* 60 */     if (maskType.equals(MinFilterDescriptor.MIN_MASK_SQUARE) && (maskSize == 3 || maskSize == 5 || maskSize == 7) && ri.getSampleModel().getNumBands() == 1)
/* 63 */       return (RenderedImage)new MlibMinFilterOpImage(ri, extender, renderHints, layout, maskType, maskSize); 
/* 70 */     return null;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\mlib\MlibMinFilterRIF.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */