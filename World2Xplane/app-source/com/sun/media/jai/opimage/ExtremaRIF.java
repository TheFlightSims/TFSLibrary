/*    */ package com.sun.media.jai.opimage;
/*    */ 
/*    */ import java.awt.RenderingHints;
/*    */ import java.awt.image.RenderedImage;
/*    */ import java.awt.image.renderable.ParameterBlock;
/*    */ import java.awt.image.renderable.RenderedImageFactory;
/*    */ import javax.media.jai.ROI;
/*    */ 
/*    */ public class ExtremaRIF implements RenderedImageFactory {
/*    */   public RenderedImage create(ParameterBlock paramBlock, RenderingHints hints) {
/* 38 */     RenderedImage src = paramBlock.getRenderedSource(0);
/* 40 */     int xStart = src.getMinX();
/* 41 */     int yStart = src.getMinY();
/* 43 */     int maxWidth = src.getWidth();
/* 44 */     int maxHeight = src.getHeight();
/* 46 */     return (RenderedImage)new ExtremaOpImage(src, (ROI)paramBlock.getObjectParameter(0), xStart, yStart, paramBlock.getIntParameter(1), paramBlock.getIntParameter(2), ((Boolean)paramBlock.getObjectParameter(3)).booleanValue(), paramBlock.getIntParameter(4));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\ExtremaRIF.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */