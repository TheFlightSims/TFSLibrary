/*    */ package com.sun.media.jai.mlib;
/*    */ 
/*    */ import com.sun.media.jai.opimage.CopyOpImage;
/*    */ import com.sun.media.jai.opimage.RIFUtil;
/*    */ import java.awt.RenderingHints;
/*    */ import java.awt.image.RenderedImage;
/*    */ import java.awt.image.renderable.ParameterBlock;
/*    */ import java.awt.image.renderable.RenderedImageFactory;
/*    */ import javax.media.jai.ImageLayout;
/*    */ 
/*    */ public class MlibSubsampleBinaryToGrayRIF implements RenderedImageFactory {
/*    */   private int blockX;
/*    */   
/*    */   private int blockY;
/*    */   
/*    */   public RenderedImage create(ParameterBlock args, RenderingHints hints) {
/* 59 */     RenderedImage source = args.getRenderedSource(0);
/* 62 */     if (!MediaLibAccessor.isMediaLibBinaryCompatible(args, null))
/* 63 */       return null; 
/* 67 */     ImageLayout layout = RIFUtil.getImageLayoutHint(hints);
/* 71 */     if ((layout != null && layout.isValid(256) && !MediaLibAccessor.isMediaLibCompatible(layout.getSampleModel(null), layout.getColorModel(null))) || !MediaLibAccessor.hasSameNumBands(args, layout))
/* 77 */       return null; 
/* 83 */     float xScale = args.getFloatParameter(0);
/* 84 */     float yScale = args.getFloatParameter(1);
/* 87 */     if (xScale == 1.0F && yScale == 1.0F)
/* 90 */       return (RenderedImage)new CopyOpImage(source, hints, layout); 
/* 93 */     return (RenderedImage)new MlibSubsampleBinaryToGrayOpImage(source, layout, hints, xScale, yScale);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\mlib\MlibSubsampleBinaryToGrayRIF.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */