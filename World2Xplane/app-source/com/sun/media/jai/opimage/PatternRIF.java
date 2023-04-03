/*    */ package com.sun.media.jai.opimage;
/*    */ 
/*    */ import java.awt.RenderingHints;
/*    */ import java.awt.image.ColorModel;
/*    */ import java.awt.image.Raster;
/*    */ import java.awt.image.RenderedImage;
/*    */ import java.awt.image.renderable.ParameterBlock;
/*    */ import java.awt.image.renderable.RenderedImageFactory;
/*    */ import javax.media.jai.ImageLayout;
/*    */ 
/*    */ public class PatternRIF implements RenderedImageFactory {
/*    */   public RenderedImage create(ParameterBlock paramBlock, RenderingHints renderHints) {
/* 42 */     ImageLayout layout = RIFUtil.getImageLayoutHint(renderHints);
/* 44 */     int minX = 0;
/* 45 */     int minY = 0;
/* 47 */     if (layout != null) {
/* 48 */       if (layout.isValid(1))
/* 49 */         minX = layout.getMinX(null); 
/* 51 */       if (layout.isValid(2))
/* 52 */         minY = layout.getMinY(null); 
/*    */     } 
/* 56 */     RenderedImage source = (RenderedImage)paramBlock.getSource(0);
/* 57 */     Raster pattern = source.getData();
/* 58 */     ColorModel colorModel = source.getColorModel();
/* 61 */     int width = paramBlock.getIntParameter(0);
/* 62 */     int height = paramBlock.getIntParameter(1);
/* 64 */     return (RenderedImage)new PatternOpImage(pattern, colorModel, minX, minY, width, height);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\PatternRIF.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */