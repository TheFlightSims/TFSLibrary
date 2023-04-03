/*    */ package com.sun.media.jai.mlib;
/*    */ 
/*    */ import com.sun.media.jai.opimage.RIFUtil;
/*    */ import java.awt.RenderingHints;
/*    */ import java.awt.image.RenderedImage;
/*    */ import java.awt.image.renderable.ParameterBlock;
/*    */ import java.awt.image.renderable.RenderedImageFactory;
/*    */ import javax.media.jai.ImageLayout;
/*    */ 
/*    */ public class MlibBandSelectRIF implements RenderedImageFactory {
/*    */   public RenderedImage create(ParameterBlock args, RenderingHints hints) {
/* 44 */     ImageLayout layout = RIFUtil.getImageLayoutHint(hints);
/* 47 */     if (!MediaLibAccessor.isMediaLibCompatible(args, layout))
/* 48 */       return null; 
/* 51 */     int[] bandIndices = (int[])args.getObjectParameter(0);
/* 55 */     for (int i = 1; i < bandIndices.length; i++) {
/* 56 */       if (bandIndices[i] <= bandIndices[i - 1])
/* 57 */         return null; 
/*    */     } 
/* 61 */     return (RenderedImage)new MlibBandSelectOpImage(args.getRenderedSource(0), hints, layout, bandIndices);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\mlib\MlibBandSelectRIF.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */