/*    */ package com.sun.media.jai.opimage;
/*    */ 
/*    */ import java.awt.RenderingHints;
/*    */ import java.awt.image.RenderedImage;
/*    */ import java.awt.image.renderable.ParameterBlock;
/*    */ import java.awt.image.renderable.RenderedImageFactory;
/*    */ import javax.media.jai.BorderExtender;
/*    */ import javax.media.jai.ImageLayout;
/*    */ import javax.media.jai.Interpolation;
/*    */ import javax.media.jai.Warp;
/*    */ 
/*    */ public class WarpRIF implements RenderedImageFactory {
/*    */   public RenderedImage create(ParameterBlock paramBlock, RenderingHints renderHints) {
/* 51 */     ImageLayout layout = RIFUtil.getImageLayoutHint(renderHints);
/* 55 */     BorderExtender extender = RIFUtil.getBorderExtenderHint(renderHints);
/* 57 */     RenderedImage source = paramBlock.getRenderedSource(0);
/* 58 */     Warp warp = (Warp)paramBlock.getObjectParameter(0);
/* 59 */     Interpolation interp = (Interpolation)paramBlock.getObjectParameter(1);
/* 61 */     double[] backgroundValues = (double[])paramBlock.getObjectParameter(2);
/* 63 */     if (interp instanceof javax.media.jai.InterpolationNearest)
/* 64 */       return (RenderedImage)new WarpNearestOpImage(source, renderHints, layout, warp, interp, backgroundValues); 
/* 70 */     if (interp instanceof javax.media.jai.InterpolationBilinear)
/* 71 */       return (RenderedImage)new WarpBilinearOpImage(source, extender, renderHints, layout, warp, interp, backgroundValues); 
/* 75 */     return (RenderedImage)new WarpGeneralOpImage(source, extender, renderHints, layout, warp, interp, backgroundValues);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\WarpRIF.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */