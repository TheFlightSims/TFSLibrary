/*    */ package com.sun.media.jai.opimage;
/*    */ 
/*    */ import java.awt.RenderingHints;
/*    */ import java.awt.image.RenderedImage;
/*    */ import java.awt.image.renderable.ParameterBlock;
/*    */ import java.awt.image.renderable.RenderedImageFactory;
/*    */ import javax.media.jai.ColorCube;
/*    */ import javax.media.jai.ImageLayout;
/*    */ import javax.media.jai.KernelJAI;
/*    */ 
/*    */ public class OrderedDitherRIF implements RenderedImageFactory {
/*    */   public RenderedImage create(ParameterBlock paramBlock, RenderingHints renderHints) {
/* 44 */     ImageLayout layout = RIFUtil.getImageLayoutHint(renderHints);
/* 47 */     RenderedImage source = paramBlock.getRenderedSource(0);
/* 48 */     ColorCube colorMap = (ColorCube)paramBlock.getObjectParameter(0);
/* 50 */     KernelJAI[] ditherMask = (KernelJAI[])paramBlock.getObjectParameter(1);
/* 52 */     return (RenderedImage)new OrderedDitherOpImage(source, renderHints, layout, colorMap, ditherMask);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\OrderedDitherRIF.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */