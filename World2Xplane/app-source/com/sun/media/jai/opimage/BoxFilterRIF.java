/*    */ package com.sun.media.jai.opimage;
/*    */ 
/*    */ import java.awt.RenderingHints;
/*    */ import java.awt.image.RenderedImage;
/*    */ import java.awt.image.renderable.ParameterBlock;
/*    */ import java.awt.image.renderable.RenderedImageFactory;
/*    */ import java.util.Arrays;
/*    */ import javax.media.jai.BorderExtender;
/*    */ import javax.media.jai.ImageLayout;
/*    */ import javax.media.jai.KernelJAI;
/*    */ 
/*    */ public class BoxFilterRIF implements RenderedImageFactory {
/*    */   public RenderedImage create(ParameterBlock paramBlock, RenderingHints renderHints) {
/* 47 */     ImageLayout layout = RIFUtil.getImageLayoutHint(renderHints);
/* 51 */     BorderExtender extender = RIFUtil.getBorderExtenderHint(renderHints);
/* 54 */     int width = paramBlock.getIntParameter(0);
/* 55 */     int height = paramBlock.getIntParameter(1);
/* 56 */     int xOrigin = paramBlock.getIntParameter(2);
/* 57 */     int yOrigin = paramBlock.getIntParameter(3);
/* 60 */     float[] dataH = new float[width];
/* 61 */     Arrays.fill(dataH, 1.0F / width);
/* 62 */     float[] dataV = null;
/* 63 */     if (height == width) {
/* 64 */       dataV = dataH;
/*    */     } else {
/* 66 */       dataV = new float[height];
/* 67 */       Arrays.fill(dataV, 1.0F / height);
/*    */     } 
/* 71 */     KernelJAI kernel = new KernelJAI(width, height, xOrigin, yOrigin, dataH, dataV);
/* 75 */     return (RenderedImage)new SeparableConvolveOpImage(paramBlock.getRenderedSource(0), extender, renderHints, layout, kernel);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\BoxFilterRIF.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */