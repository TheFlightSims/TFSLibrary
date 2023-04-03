/*    */ package com.sun.media.jai.mlib;
/*    */ 
/*    */ import com.sun.media.jai.opimage.RIFUtil;
/*    */ import java.awt.RenderingHints;
/*    */ import java.awt.image.RenderedImage;
/*    */ import java.awt.image.SampleModel;
/*    */ import java.awt.image.renderable.ParameterBlock;
/*    */ import java.awt.image.renderable.RenderedImageFactory;
/*    */ import javax.media.jai.EnumeratedParameter;
/*    */ import javax.media.jai.ImageLayout;
/*    */ 
/*    */ public class MlibTransposeRIF implements RenderedImageFactory {
/*    */   public RenderedImage create(ParameterBlock args, RenderingHints hints) {
/* 49 */     ImageLayout layout = RIFUtil.getImageLayoutHint(hints);
/* 52 */     if (!MediaLibAccessor.isMediaLibCompatible(args, layout) || !MediaLibAccessor.hasSameNumBands(args, layout))
/* 54 */       return null; 
/* 57 */     RenderedImage source = args.getRenderedSource(0);
/* 59 */     SampleModel sm = source.getSampleModel();
/* 60 */     boolean isBilevel = (sm instanceof java.awt.image.MultiPixelPackedSampleModel && sm.getSampleSize(0) == 1 && (sm.getDataType() == 0 || sm.getDataType() == 1 || sm.getDataType() == 3));
/* 65 */     if (isBilevel)
/* 67 */       return null; 
/* 70 */     EnumeratedParameter transposeType = (EnumeratedParameter)args.getObjectParameter(0);
/* 72 */     return (RenderedImage)new MlibTransposeOpImage(args.getRenderedSource(0), hints, layout, transposeType.getValue());
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\mlib\MlibTransposeRIF.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */