/*    */ package com.sun.media.jai.mlib;
/*    */ 
/*    */ import com.sun.media.jai.opimage.RIFUtil;
/*    */ import java.awt.RenderingHints;
/*    */ import java.awt.image.RenderedImage;
/*    */ import java.awt.image.renderable.ParameterBlock;
/*    */ import java.awt.image.renderable.RenderedImageFactory;
/*    */ import javax.media.jai.ImageLayout;
/*    */ 
/*    */ public class MlibSubtractConstRIF implements RenderedImageFactory {
/*    */   public RenderedImage create(ParameterBlock args, RenderingHints hints) {
/* 45 */     ImageLayout layout = RIFUtil.getImageLayoutHint(hints);
/* 48 */     if (!MediaLibAccessor.isMediaLibCompatible(args, layout) || !MediaLibAccessor.hasSameNumBands(args, layout))
/* 50 */       return null; 
/* 54 */     double[] constants = (double[])args.getObjectParameter(0);
/* 55 */     int length = constants.length;
/* 57 */     double[] negConstants = new double[length];
/* 59 */     for (int i = 0; i < length; i++)
/* 60 */       negConstants[i] = -constants[i]; 
/* 63 */     return (RenderedImage)new MlibAddConstOpImage(args.getRenderedSource(0), hints, layout, negConstants);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\mlib\MlibSubtractConstRIF.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */