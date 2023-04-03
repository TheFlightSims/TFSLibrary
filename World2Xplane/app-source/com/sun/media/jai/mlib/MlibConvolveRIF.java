/*    */ package com.sun.media.jai.mlib;
/*    */ 
/*    */ import com.sun.media.jai.opimage.RIFUtil;
/*    */ import java.awt.RenderingHints;
/*    */ import java.awt.image.RenderedImage;
/*    */ import java.awt.image.renderable.ParameterBlock;
/*    */ import java.awt.image.renderable.RenderedImageFactory;
/*    */ import javax.media.jai.BorderExtender;
/*    */ import javax.media.jai.ImageLayout;
/*    */ import javax.media.jai.KernelJAI;
/*    */ 
/*    */ public class MlibConvolveRIF implements RenderedImageFactory {
/*    */   public RenderedImage create(ParameterBlock args, RenderingHints hints) {
/* 45 */     ImageLayout layout = RIFUtil.getImageLayoutHint(hints);
/* 47 */     if (!MediaLibAccessor.isMediaLibCompatible(args, layout) || !MediaLibAccessor.hasSameNumBands(args, layout))
/* 49 */       return null; 
/* 53 */     BorderExtender extender = RIFUtil.getBorderExtenderHint(hints);
/* 55 */     RenderedImage source = args.getRenderedSource(0);
/* 57 */     KernelJAI unRotatedKernel = (KernelJAI)args.getObjectParameter(0);
/* 58 */     KernelJAI kJAI = unRotatedKernel.getRotatedKernel();
/* 60 */     int kWidth = kJAI.getWidth();
/* 61 */     int kHeight = kJAI.getHeight();
/* 64 */     if (kWidth < 2 || kHeight < 2)
/* 65 */       return null; 
/* 68 */     if (kJAI.isSeparable() && kWidth == kHeight && (kWidth == 3 || kWidth == 5 || kWidth == 7))
/* 70 */       return (RenderedImage)new MlibSeparableConvolveOpImage(source, extender, hints, layout, kJAI); 
/* 73 */     if (kWidth == kHeight && (kWidth == 2 || kWidth == 3 || kWidth == 4 || kWidth == 5 || kWidth == 7))
/* 76 */       return (RenderedImage)new MlibConvolveNxNOpImage(source, extender, hints, layout, kJAI); 
/* 80 */     return (RenderedImage)new MlibConvolveOpImage(source, extender, hints, layout, kJAI);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\mlib\MlibConvolveRIF.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */