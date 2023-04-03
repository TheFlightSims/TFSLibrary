/*    */ package com.sun.media.jai.mlib;
/*    */ 
/*    */ import com.sun.media.jai.opimage.RIFUtil;
/*    */ import java.awt.RenderingHints;
/*    */ import java.awt.image.RenderedImage;
/*    */ import java.awt.image.renderable.ParameterBlock;
/*    */ import java.awt.image.renderable.RenderedImageFactory;
/*    */ import javax.media.jai.ImageLayout;
/*    */ 
/*    */ public class MlibClampRIF implements RenderedImageFactory {
/*    */   public RenderedImage create(ParameterBlock args, RenderingHints hints) {
/* 46 */     ImageLayout layout = RIFUtil.getImageLayoutHint(hints);
/* 49 */     if (!MediaLibAccessor.isMediaLibCompatible(args, layout) || !MediaLibAccessor.hasSameNumBands(args, layout))
/* 51 */       return null; 
/* 54 */     return (RenderedImage)new MlibClampOpImage(args.getRenderedSource(0), hints, layout, (double[])args.getObjectParameter(0), (double[])args.getObjectParameter(1));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\mlib\MlibClampRIF.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */