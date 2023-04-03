/*    */ package com.sun.media.jai.mlib;
/*    */ 
/*    */ import com.sun.media.jai.opimage.RIFUtil;
/*    */ import java.awt.RenderingHints;
/*    */ import java.awt.image.RenderedImage;
/*    */ import java.awt.image.renderable.ParameterBlock;
/*    */ import java.awt.image.renderable.RenderedImageFactory;
/*    */ import javax.media.jai.ImageLayout;
/*    */ 
/*    */ public class MlibSubsampleAverageRIF implements RenderedImageFactory {
/*    */   public RenderedImage create(ParameterBlock args, RenderingHints hints) {
/* 44 */     double scaleX = args.getDoubleParameter(0);
/* 45 */     double scaleY = args.getDoubleParameter(1);
/* 48 */     if (scaleX == 1.0D && scaleY == 1.0D)
/* 49 */       return args.getRenderedSource(0); 
/* 53 */     ImageLayout layout = RIFUtil.getImageLayoutHint(hints);
/* 56 */     if (!MediaLibAccessor.isMediaLibCompatible(args, layout) || !MediaLibAccessor.hasSameNumBands(args, layout))
/* 59 */       return null; 
/* 63 */     return (RenderedImage)new MlibSubsampleAverageOpImage(args.getRenderedSource(0), layout, hints, scaleX, scaleY);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\mlib\MlibSubsampleAverageRIF.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */