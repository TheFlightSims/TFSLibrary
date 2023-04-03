/*    */ package com.sun.media.jai.mlib;
/*    */ 
/*    */ import java.awt.RenderingHints;
/*    */ import java.awt.image.RenderedImage;
/*    */ import java.awt.image.renderable.ParameterBlock;
/*    */ import java.util.Arrays;
/*    */ import javax.media.jai.KernelJAI;
/*    */ 
/*    */ public class MlibBoxFilterRIF extends MlibConvolveRIF {
/*    */   public RenderedImage create(ParameterBlock paramBlock, RenderingHints renderHints) {
/* 50 */     int width = paramBlock.getIntParameter(0);
/* 51 */     int height = paramBlock.getIntParameter(1);
/* 52 */     int xOrigin = paramBlock.getIntParameter(2);
/* 53 */     int yOrigin = paramBlock.getIntParameter(3);
/* 56 */     float[] dataH = new float[width];
/* 57 */     Arrays.fill(dataH, 1.0F / width);
/* 58 */     float[] dataV = null;
/* 59 */     if (height == width) {
/* 60 */       dataV = dataH;
/*    */     } else {
/* 62 */       dataV = new float[height];
/* 63 */       Arrays.fill(dataV, 1.0F / height);
/*    */     } 
/* 67 */     KernelJAI kernel = new KernelJAI(width, height, xOrigin, yOrigin, dataH, dataV);
/* 71 */     ParameterBlock args = new ParameterBlock(paramBlock.getSources());
/* 72 */     args.add(kernel);
/* 75 */     return super.create(args, renderHints);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\mlib\MlibBoxFilterRIF.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */