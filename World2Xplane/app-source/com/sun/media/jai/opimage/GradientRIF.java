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
/*    */ public class GradientRIF implements RenderedImageFactory {
/*    */   public RenderedImage create(ParameterBlock paramBlock, RenderingHints renderHints) {
/* 43 */     ImageLayout layout = RIFUtil.getImageLayoutHint(renderHints);
/* 47 */     BorderExtender extender = RIFUtil.getBorderExtenderHint(renderHints);
/* 49 */     RenderedImage source = paramBlock.getRenderedSource(0);
/* 52 */     KernelJAI kern_h = (KernelJAI)paramBlock.getObjectParameter(0);
/* 53 */     KernelJAI kern_v = (KernelJAI)paramBlock.getObjectParameter(1);
/* 55 */     return (RenderedImage)new GradientOpImage(source, extender, renderHints, layout, kern_h, kern_v);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\GradientRIF.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */