/*    */ package com.sun.media.jai.opimage;
/*    */ 
/*    */ import java.awt.RenderingHints;
/*    */ import java.awt.image.RenderedImage;
/*    */ import java.awt.image.renderable.ParameterBlock;
/*    */ import java.awt.image.renderable.RenderedImageFactory;
/*    */ 
/*    */ public class TIFFRIF implements RenderedImageFactory {
/*    */   public RenderedImage create(ParameterBlock paramBlock, RenderingHints renderHints) {
/* 45 */     return CodecRIFUtil.create("tiff", paramBlock, renderHints);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\TIFFRIF.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */