/*    */ package com.sun.media.jai.mlib;
/*    */ 
/*    */ import com.sun.media.jai.opimage.RIFUtil;
/*    */ import com.sun.media.jai.util.ImageUtil;
/*    */ import java.awt.RenderingHints;
/*    */ import java.awt.image.RenderedImage;
/*    */ import java.awt.image.renderable.ParameterBlock;
/*    */ import java.awt.image.renderable.RenderedImageFactory;
/*    */ import javax.media.jai.BorderExtender;
/*    */ import javax.media.jai.ImageLayout;
/*    */ import javax.media.jai.KernelJAI;
/*    */ 
/*    */ public class MlibUnsharpMaskRIF implements RenderedImageFactory {
/*    */   public RenderedImage create(ParameterBlock args, RenderingHints hints) {
/* 48 */     ImageLayout layout = RIFUtil.getImageLayoutHint(hints);
/* 51 */     if (!MediaLibAccessor.isMediaLibCompatible(args, layout) || !MediaLibAccessor.hasSameNumBands(args, layout))
/* 53 */       return null; 
/* 57 */     BorderExtender extender = RIFUtil.getBorderExtenderHint(hints);
/* 59 */     RenderedImage source = args.getRenderedSource(0);
/* 63 */     KernelJAI unRotatedKernel = ImageUtil.getUnsharpMaskEquivalentKernel((KernelJAI)args.getObjectParameter(0), args.getFloatParameter(1));
/* 68 */     KernelJAI kJAI = unRotatedKernel.getRotatedKernel();
/* 70 */     int kWidth = kJAI.getWidth();
/* 71 */     int kHeight = kJAI.getHeight();
/* 74 */     if (kWidth < 2 || kHeight < 2)
/* 75 */       return null; 
/* 78 */     if (kJAI.isSeparable() && kWidth >= 3 && kWidth <= 7 && kWidth == kHeight)
/* 79 */       return (RenderedImage)new MlibSeparableConvolveOpImage(source, extender, hints, layout, kJAI); 
/* 82 */     if ((kWidth == 3 && kHeight == 3) || (kWidth == 5 && kHeight == 5))
/* 84 */       return (RenderedImage)new MlibConvolveNxNOpImage(source, extender, hints, layout, kJAI); 
/* 88 */     return (RenderedImage)new MlibConvolveOpImage(source, extender, hints, layout, kJAI);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\mlib\MlibUnsharpMaskRIF.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */