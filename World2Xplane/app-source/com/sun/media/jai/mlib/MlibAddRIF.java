/*    */ package com.sun.media.jai.mlib;
/*    */ 
/*    */ import com.sun.media.jai.opimage.RIFUtil;
/*    */ import java.awt.RenderingHints;
/*    */ import java.awt.image.RenderedImage;
/*    */ import java.awt.image.renderable.ParameterBlock;
/*    */ import java.awt.image.renderable.RenderedImageFactory;
/*    */ import javax.media.jai.ImageLayout;
/*    */ 
/*    */ public class MlibAddRIF implements RenderedImageFactory {
/*    */   public RenderedImage create(ParameterBlock args, RenderingHints hints) {
/* 44 */     ImageLayout layout = RIFUtil.getImageLayoutHint(hints);
/* 47 */     if (!MediaLibAccessor.isMediaLibCompatible(args, layout) || !MediaLibAccessor.hasSameNumBands(args, layout))
/* 49 */       return null; 
/* 52 */     return (RenderedImage)new MlibAddOpImage(args.getRenderedSource(0), args.getRenderedSource(1), hints, layout);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\mlib\MlibAddRIF.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */