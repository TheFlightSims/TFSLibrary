/*    */ package com.sun.media.jai.opimage;
/*    */ 
/*    */ import com.sun.media.jai.util.ImageUtil;
/*    */ import java.awt.RenderingHints;
/*    */ import java.awt.image.RenderedImage;
/*    */ import java.awt.image.renderable.ParameterBlock;
/*    */ import java.awt.image.renderable.RenderedImageFactory;
/*    */ import javax.media.jai.BorderExtender;
/*    */ import javax.media.jai.ImageLayout;
/*    */ import javax.media.jai.KernelJAI;
/*    */ 
/*    */ public class UnsharpMaskRIF implements RenderedImageFactory {
/*    */   public RenderedImage create(ParameterBlock paramBlock, RenderingHints renderHints) {
/* 44 */     ImageLayout layout = RIFUtil.getImageLayoutHint(renderHints);
/* 48 */     BorderExtender extender = RIFUtil.getBorderExtenderHint(renderHints);
/* 52 */     KernelJAI unRotatedKernel = ImageUtil.getUnsharpMaskEquivalentKernel((KernelJAI)paramBlock.getObjectParameter(0), paramBlock.getFloatParameter(1));
/* 57 */     KernelJAI kJAI = unRotatedKernel.getRotatedKernel();
/* 59 */     RenderedImage source = paramBlock.getRenderedSource(0);
/* 60 */     int dataType = source.getSampleModel().getDataType();
/* 62 */     boolean dataTypeOk = (dataType == 0 || dataType == 2 || dataType == 3);
/* 66 */     if (kJAI.getWidth() == 3 && kJAI.getHeight() == 3 && kJAI.getXOrigin() == 1 && kJAI.getYOrigin() == 1 && dataTypeOk)
/* 68 */       return (RenderedImage)new Convolve3x3OpImage(source, extender, renderHints, layout, kJAI); 
/* 73 */     if (kJAI.isSeparable())
/* 74 */       return (RenderedImage)new SeparableConvolveOpImage(source, extender, renderHints, layout, kJAI); 
/* 81 */     return (RenderedImage)new ConvolveOpImage(source, extender, renderHints, layout, kJAI);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\UnsharpMaskRIF.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */