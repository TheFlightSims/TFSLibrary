/*    */ package com.sun.media.jai.opimage;
/*    */ 
/*    */ import java.awt.Image;
/*    */ import java.awt.RenderingHints;
/*    */ import java.awt.image.RenderedImage;
/*    */ import java.awt.image.renderable.ParameterBlock;
/*    */ import java.awt.image.renderable.RenderedImageFactory;
/*    */ import javax.media.jai.ImageLayout;
/*    */ 
/*    */ public class AWTImageRIF implements RenderedImageFactory {
/*    */   public RenderedImage create(ParameterBlock paramBlock, RenderingHints renderHints) {
/* 45 */     ImageLayout layout = RIFUtil.getImageLayoutHint(renderHints);
/* 48 */     Image awtImage = (Image)paramBlock.getObjectParameter(0);
/* 51 */     if (awtImage instanceof RenderedImage)
/* 52 */       return (RenderedImage)awtImage; 
/* 56 */     return (RenderedImage)new AWTImageOpImage(renderHints, layout, awtImage);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\AWTImageRIF.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */