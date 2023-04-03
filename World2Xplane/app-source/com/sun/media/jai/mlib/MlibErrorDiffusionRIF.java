/*    */ package com.sun.media.jai.mlib;
/*    */ 
/*    */ import com.sun.media.jai.opimage.RIFUtil;
/*    */ import com.sun.media.jai.util.ImageUtil;
/*    */ import java.awt.RenderingHints;
/*    */ import java.awt.image.ColorModel;
/*    */ import java.awt.image.RenderedImage;
/*    */ import java.awt.image.SampleModel;
/*    */ import java.awt.image.renderable.ParameterBlock;
/*    */ import java.awt.image.renderable.RenderedImageFactory;
/*    */ import javax.media.jai.ImageLayout;
/*    */ import javax.media.jai.KernelJAI;
/*    */ import javax.media.jai.LookupTableJAI;
/*    */ 
/*    */ public class MlibErrorDiffusionRIF implements RenderedImageFactory {
/*    */   public RenderedImage create(ParameterBlock args, RenderingHints hints) {
/* 48 */     RenderedImage source = args.getRenderedSource(0);
/* 49 */     LookupTableJAI colorMap = (LookupTableJAI)args.getObjectParameter(0);
/* 51 */     KernelJAI errorKernel = (KernelJAI)args.getObjectParameter(1);
/* 54 */     if (colorMap.getNumBands() != 1 && colorMap.getNumBands() != 3)
/* 57 */       return null; 
/* 58 */     if (colorMap.getDataType() != 0)
/* 60 */       return null; 
/* 64 */     SampleModel sourceSM = source.getSampleModel();
/* 65 */     if (sourceSM.getDataType() != 0)
/* 67 */       return null; 
/* 68 */     if (sourceSM.getNumBands() != colorMap.getNumBands())
/* 70 */       return null; 
/* 74 */     ImageLayout layoutHint = RIFUtil.getImageLayoutHint(hints);
/* 77 */     ImageLayout layout = MlibErrorDiffusionOpImage.layoutHelper(layoutHint, source, colorMap);
/* 84 */     SampleModel destSM = layout.getSampleModel(null);
/* 85 */     if (!MediaLibAccessor.isMediaLibCompatible(args) || (!MediaLibAccessor.isMediaLibCompatible(destSM, (ColorModel)null) && !ImageUtil.isBinary(destSM)))
/* 88 */       return null; 
/* 91 */     return (RenderedImage)new MlibErrorDiffusionOpImage(source, hints, layout, colorMap, errorKernel);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\mlib\MlibErrorDiffusionRIF.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */