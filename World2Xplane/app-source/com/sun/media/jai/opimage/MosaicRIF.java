/*    */ package com.sun.media.jai.opimage;
/*    */ 
/*    */ import java.awt.RenderingHints;
/*    */ import java.awt.image.RenderedImage;
/*    */ import java.awt.image.renderable.ParameterBlock;
/*    */ import java.awt.image.renderable.RenderedImageFactory;
/*    */ import javax.media.jai.PlanarImage;
/*    */ import javax.media.jai.ROI;
/*    */ import javax.media.jai.operator.MosaicType;
/*    */ 
/*    */ public class MosaicRIF implements RenderedImageFactory {
/*    */   public RenderedImage create(ParameterBlock paramBlock, RenderingHints renderHints) {
/* 39 */     return (RenderedImage)new MosaicOpImage(paramBlock.getSources(), RIFUtil.getImageLayoutHint(renderHints), renderHints, (MosaicType)paramBlock.getObjectParameter(0), (PlanarImage[])paramBlock.getObjectParameter(1), (ROI[])paramBlock.getObjectParameter(2), (double[][])paramBlock.getObjectParameter(3), (double[])paramBlock.getObjectParameter(4));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\MosaicRIF.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */