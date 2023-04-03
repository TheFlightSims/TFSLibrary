/*    */ package com.sun.media.jai.opimage;
/*    */ 
/*    */ import java.awt.RenderingHints;
/*    */ import java.awt.image.RenderedImage;
/*    */ import java.awt.image.renderable.ParameterBlock;
/*    */ import java.awt.image.renderable.RenderedImageFactory;
/*    */ import javax.media.jai.ImageLayout;
/*    */ import javax.media.jai.KernelJAI;
/*    */ import javax.media.jai.LookupTableJAI;
/*    */ 
/*    */ public class ErrorDiffusionRIF implements RenderedImageFactory {
/*    */   public RenderedImage create(ParameterBlock paramBlock, RenderingHints renderHints) {
/* 43 */     ImageLayout layout = RIFUtil.getImageLayoutHint(renderHints);
/* 46 */     RenderedImage source = paramBlock.getRenderedSource(0);
/* 47 */     LookupTableJAI lookupTable = (LookupTableJAI)paramBlock.getObjectParameter(0);
/* 49 */     KernelJAI kernel = (KernelJAI)paramBlock.getObjectParameter(1);
/* 51 */     return (RenderedImage)new ErrorDiffusionOpImage(source, renderHints, layout, lookupTable, kernel);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\ErrorDiffusionRIF.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */