/*    */ package com.sun.media.jai.opimage;
/*    */ 
/*    */ import java.awt.RenderingHints;
/*    */ import java.awt.image.RenderedImage;
/*    */ import java.awt.image.SampleModel;
/*    */ import java.awt.image.renderable.ParameterBlock;
/*    */ import java.awt.image.renderable.RenderedImageFactory;
/*    */ import javax.media.jai.BorderExtender;
/*    */ import javax.media.jai.ImageLayout;
/*    */ import javax.media.jai.KernelJAI;
/*    */ 
/*    */ public class DilateRIF implements RenderedImageFactory {
/*    */   public RenderedImage create(ParameterBlock paramBlock, RenderingHints renderHints) {
/* 42 */     ImageLayout layout = RIFUtil.getImageLayoutHint(renderHints);
/* 46 */     BorderExtender extender = RIFUtil.getBorderExtenderHint(renderHints);
/* 48 */     KernelJAI unRotatedKernel = (KernelJAI)paramBlock.getObjectParameter(0);
/* 50 */     KernelJAI kJAI = unRotatedKernel.getRotatedKernel();
/* 52 */     RenderedImage source = paramBlock.getRenderedSource(0);
/* 53 */     SampleModel sm = source.getSampleModel();
/* 56 */     int dataType = sm.getDataType();
/* 58 */     boolean isBinary = (sm instanceof java.awt.image.MultiPixelPackedSampleModel && sm.getSampleSize(0) == 1 && (dataType == 0 || dataType == 1 || dataType == 3));
/* 65 */     if (isBinary)
/* 68 */       return (RenderedImage)new DilateBinaryOpImage(source, extender, renderHints, layout, kJAI); 
/* 74 */     return (RenderedImage)new DilateOpImage(source, extender, renderHints, layout, kJAI);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\DilateRIF.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */