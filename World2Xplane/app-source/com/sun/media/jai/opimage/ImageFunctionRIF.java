/*    */ package com.sun.media.jai.opimage;
/*    */ 
/*    */ import java.awt.RenderingHints;
/*    */ import java.awt.image.RenderedImage;
/*    */ import java.awt.image.renderable.ParameterBlock;
/*    */ import java.awt.image.renderable.RenderedImageFactory;
/*    */ import javax.media.jai.ImageFunction;
/*    */ import javax.media.jai.ImageLayout;
/*    */ 
/*    */ public class ImageFunctionRIF implements RenderedImageFactory {
/*    */   public RenderedImage create(ParameterBlock paramBlock, RenderingHints renderHints) {
/* 44 */     ImageLayout layout = RIFUtil.getImageLayoutHint(renderHints);
/* 47 */     ImageFunction function = (ImageFunction)paramBlock.getObjectParameter(0);
/* 52 */     int numBandsRequired = function.isComplex() ? (function.getNumElements() * 2) : function.getNumElements();
/* 54 */     if (layout != null && layout.isValid(256) && layout.getSampleModel(null).getNumBands() != numBandsRequired)
/* 57 */       throw new RuntimeException(JaiI18N.getString("ImageFunctionRIF0")); 
/* 60 */     int minX = 0;
/* 61 */     int minY = 0;
/* 62 */     if (layout != null) {
/* 63 */       if (layout.isValid(1))
/* 64 */         minX = layout.getMinX(null); 
/* 66 */       if (layout.isValid(2))
/* 67 */         minY = layout.getMinX(null); 
/*    */     } 
/* 71 */     int width = paramBlock.getIntParameter(1);
/* 72 */     int height = paramBlock.getIntParameter(2);
/* 73 */     float xScale = paramBlock.getFloatParameter(3);
/* 74 */     float yScale = paramBlock.getFloatParameter(4);
/* 75 */     float xTrans = paramBlock.getFloatParameter(5);
/* 76 */     float yTrans = paramBlock.getFloatParameter(6);
/* 78 */     return (RenderedImage)new ImageFunctionOpImage(function, minX, minY, width, height, xScale, yScale, xTrans, yTrans, renderHints, layout);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\ImageFunctionRIF.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */