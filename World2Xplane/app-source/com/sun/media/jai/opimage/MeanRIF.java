/*    */ package com.sun.media.jai.opimage;
/*    */ 
/*    */ import java.awt.RenderingHints;
/*    */ import java.awt.image.RenderedImage;
/*    */ import java.awt.image.renderable.ParameterBlock;
/*    */ import java.awt.image.renderable.RenderedImageFactory;
/*    */ import javax.media.jai.ROI;
/*    */ 
/*    */ public class MeanRIF implements RenderedImageFactory {
/*    */   public RenderedImage create(ParameterBlock paramBlock, RenderingHints renderHints) {
/* 40 */     RenderedImage src = paramBlock.getRenderedSource(0);
/* 42 */     int xStart = src.getMinX();
/* 43 */     int yStart = src.getMinY();
/* 45 */     int maxWidth = src.getWidth();
/* 46 */     int maxHeight = src.getHeight();
/* 48 */     return (RenderedImage)new MeanOpImage(src, (ROI)paramBlock.getObjectParameter(0), xStart, yStart, paramBlock.getIntParameter(1), paramBlock.getIntParameter(2));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\MeanRIF.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */