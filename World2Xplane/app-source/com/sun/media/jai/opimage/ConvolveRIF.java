/*    */ package com.sun.media.jai.opimage;
/*    */ 
/*    */ import java.awt.RenderingHints;
/*    */ import java.awt.image.RenderedImage;
/*    */ import java.awt.image.renderable.ParameterBlock;
/*    */ import java.awt.image.renderable.RenderedImageFactory;
/*    */ import javax.media.jai.BorderExtender;
/*    */ import javax.media.jai.ImageLayout;
/*    */ import javax.media.jai.KernelJAI;
/*    */ 
/*    */ public class ConvolveRIF implements RenderedImageFactory {
/*    */   public RenderedImage create(ParameterBlock paramBlock, RenderingHints renderHints) {
/* 41 */     ImageLayout layout = RIFUtil.getImageLayoutHint(renderHints);
/* 45 */     BorderExtender extender = RIFUtil.getBorderExtenderHint(renderHints);
/* 47 */     KernelJAI unRotatedKernel = (KernelJAI)paramBlock.getObjectParameter(0);
/* 49 */     KernelJAI kJAI = unRotatedKernel.getRotatedKernel();
/* 51 */     int dataType = paramBlock.getRenderedSource(0).getSampleModel().getDataType();
/* 53 */     boolean dataTypeOk = (dataType == 0 || dataType == 2 || dataType == 3);
/* 57 */     if (kJAI.getWidth() == 3 && kJAI.getHeight() == 3 && kJAI.getXOrigin() == 1 && kJAI.getYOrigin() == 1 && dataTypeOk)
/* 60 */       return (RenderedImage)new Convolve3x3OpImage(paramBlock.getRenderedSource(0), extender, renderHints, layout, kJAI); 
/* 65 */     if (kJAI.isSeparable())
/* 66 */       return (RenderedImage)new SeparableConvolveOpImage(paramBlock.getRenderedSource(0), extender, renderHints, layout, kJAI); 
/* 73 */     return (RenderedImage)new ConvolveOpImage(paramBlock.getRenderedSource(0), extender, renderHints, layout, kJAI);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\ConvolveRIF.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */