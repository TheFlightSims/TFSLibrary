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
/*    */ public class MlibGradientRIF implements RenderedImageFactory {
/*    */   public RenderedImage create(ParameterBlock args, RenderingHints hints) {
/* 45 */     ImageLayout layout = RIFUtil.getImageLayoutHint(hints);
/* 48 */     if (!MediaLibAccessor.isMediaLibCompatible(args, layout) || !MediaLibAccessor.hasSameNumBands(args, layout))
/* 50 */       return null; 
/* 54 */     BorderExtender extender = RIFUtil.getBorderExtenderHint(hints);
/* 56 */     RenderedImage source = args.getRenderedSource(0);
/* 62 */     KernelJAI kern_h = (KernelJAI)args.getObjectParameter(0);
/* 63 */     KernelJAI kern_v = (KernelJAI)args.getObjectParameter(1);
/* 66 */     int kWidth = kern_h.getWidth();
/* 67 */     int kHeight = kern_v.getHeight();
/* 71 */     float[] khdata = kern_h.getKernelData();
/* 72 */     float[] kvdata = kern_v.getKernelData();
/* 73 */     if (khdata[0] == -1.0F && khdata[1] == -2.0F && khdata[2] == -1.0F && khdata[3] == 0.0F && khdata[4] == 0.0F && khdata[5] == 0.0F && khdata[6] == 1.0F && khdata[7] == 2.0F && khdata[8] == 1.0F && kvdata[0] == -1.0F && kvdata[1] == 0.0F && kvdata[2] == 1.0F && kvdata[3] == -2.0F && kvdata[4] == 0.0F && kvdata[5] == 2.0F && kvdata[6] == -1.0F && kvdata[7] == 0.0F && kvdata[8] == 1.0F && kWidth == 3 && kHeight == 3)
/* 80 */       return (RenderedImage)new MlibSobelOpImage(source, extender, hints, layout, kern_h); 
/* 86 */     return (RenderedImage)new MlibGradientOpImage(source, extender, hints, layout, kern_h, kern_v);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\mlib\MlibGradientRIF.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */