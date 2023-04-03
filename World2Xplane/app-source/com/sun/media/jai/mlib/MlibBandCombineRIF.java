/*    */ package com.sun.media.jai.mlib;
/*    */ 
/*    */ import com.sun.media.jai.opimage.RIFUtil;
/*    */ import java.awt.RenderingHints;
/*    */ import java.awt.image.RenderedImage;
/*    */ import java.awt.image.renderable.ParameterBlock;
/*    */ import java.awt.image.renderable.RenderedImageFactory;
/*    */ import javax.media.jai.ImageLayout;
/*    */ 
/*    */ public class MlibBandCombineRIF implements RenderedImageFactory {
/*    */   public RenderedImage create(ParameterBlock args, RenderingHints hints) {
/* 44 */     ImageLayout layout = RIFUtil.getImageLayoutHint(hints);
/* 47 */     if (!MediaLibAccessor.isMediaLibCompatible(args, layout))
/* 48 */       return null; 
/* 52 */     double[][] matrix = (double[][])args.getObjectParameter(0);
/* 53 */     if (matrix.length != 3)
/* 54 */       return null; 
/* 56 */     for (int i = 0; i < 3; i++) {
/* 57 */       if ((matrix[i]).length != 4)
/* 58 */         return null; 
/*    */     } 
/* 62 */     return (RenderedImage)new MlibBandCombineOpImage(args.getRenderedSource(0), hints, layout, matrix);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\mlib\MlibBandCombineRIF.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */