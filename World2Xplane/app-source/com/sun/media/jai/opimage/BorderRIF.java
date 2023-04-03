/*    */ package com.sun.media.jai.opimage;
/*    */ 
/*    */ import java.awt.RenderingHints;
/*    */ import java.awt.image.RenderedImage;
/*    */ import java.awt.image.renderable.ParameterBlock;
/*    */ import java.awt.image.renderable.RenderedImageFactory;
/*    */ import javax.media.jai.BorderExtender;
/*    */ import javax.media.jai.ImageLayout;
/*    */ 
/*    */ public class BorderRIF implements RenderedImageFactory {
/*    */   public RenderedImage create(ParameterBlock args, RenderingHints renderHints) {
/* 48 */     ImageLayout layout = RIFUtil.getImageLayoutHint(renderHints);
/* 51 */     RenderedImage source = args.getRenderedSource(0);
/* 52 */     int leftPad = args.getIntParameter(0);
/* 53 */     int rightPad = args.getIntParameter(1);
/* 54 */     int topPad = args.getIntParameter(2);
/* 55 */     int bottomPad = args.getIntParameter(3);
/* 56 */     BorderExtender type = (BorderExtender)args.getObjectParameter(4);
/* 59 */     if (type == BorderExtender.createInstance(3)) {
/* 61 */       int minX = source.getMinX() - leftPad;
/* 62 */       int minY = source.getMinY() - topPad;
/* 63 */       int width = source.getWidth() + leftPad + rightPad;
/* 64 */       int height = source.getHeight() + topPad + bottomPad;
/* 66 */       return (RenderedImage)new PatternOpImage(source.getData(), source.getColorModel(), minX, minY, width, height);
/*    */     } 
/* 71 */     return (RenderedImage)new BorderOpImage(source, renderHints, layout, leftPad, rightPad, topPad, bottomPad, type);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\BorderRIF.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */