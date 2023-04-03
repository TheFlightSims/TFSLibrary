/*    */ package com.sun.media.jai.mlib;
/*    */ 
/*    */ import com.sun.media.jai.opimage.RIFUtil;
/*    */ import java.awt.RenderingHints;
/*    */ import java.awt.image.RenderedImage;
/*    */ import java.awt.image.SampleModel;
/*    */ import java.awt.image.renderable.ParameterBlock;
/*    */ import java.awt.image.renderable.RenderedImageFactory;
/*    */ import javax.media.jai.ImageLayout;
/*    */ 
/*    */ public class MlibBinarizeRIF implements RenderedImageFactory {
/*    */   public RenderedImage create(ParameterBlock args, RenderingHints hints) {
/* 47 */     RenderedImage source = args.getRenderedSource(0);
/* 48 */     SampleModel sm = source.getSampleModel();
/* 53 */     if (!MediaLibAccessor.isMediaLibCompatible(args) || sm.getNumBands() > 1)
/* 55 */       return null; 
/* 59 */     double thresh = args.getDoubleParameter(0);
/* 62 */     if (((thresh > 255.0D || thresh <= 0.0D) && sm.getDataType() == 0) || ((thresh > 32767.0D || thresh <= 0.0D) && sm.getDataType() == 2) || ((thresh > 2.147483647E9D || thresh <= 0.0D) && sm.getDataType() == 3))
/* 65 */       return null; 
/* 68 */     ImageLayout layout = RIFUtil.getImageLayoutHint(hints);
/* 70 */     return (RenderedImage)new MlibBinarizeOpImage(source, layout, hints, thresh);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\mlib\MlibBinarizeRIF.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */