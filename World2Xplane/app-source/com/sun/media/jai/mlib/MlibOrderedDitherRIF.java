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
/*    */ import javax.media.jai.ColorCube;
/*    */ import javax.media.jai.ImageLayout;
/*    */ import javax.media.jai.KernelJAI;
/*    */ 
/*    */ public class MlibOrderedDitherRIF implements RenderedImageFactory {
/*    */   public RenderedImage create(ParameterBlock args, RenderingHints hints) {
/* 48 */     RenderedImage source = args.getRenderedSource(0);
/* 49 */     ColorCube colorMap = (ColorCube)args.getObjectParameter(0);
/* 51 */     KernelJAI[] ditherMask = (KernelJAI[])args.getObjectParameter(1);
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
/* 77 */     ImageLayout layout = MlibOrderedDitherOpImage.layoutHelper(layoutHint, source, colorMap);
/* 84 */     SampleModel destSM = layout.getSampleModel(null);
/* 85 */     if (!MediaLibAccessor.isMediaLibCompatible(args) || (!MediaLibAccessor.isMediaLibCompatible(destSM, (ColorModel)null) && !ImageUtil.isBinary(destSM)))
/* 88 */       return null; 
/* 91 */     return (RenderedImage)new MlibOrderedDitherOpImage(source, hints, layout, colorMap, ditherMask);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\mlib\MlibOrderedDitherRIF.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */