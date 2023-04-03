/*    */ package com.sun.media.jai.mlib;
/*    */ 
/*    */ import com.sun.media.jai.opimage.RIFUtil;
/*    */ import java.awt.RenderingHints;
/*    */ import java.awt.image.RenderedImage;
/*    */ import java.awt.image.SampleModel;
/*    */ import java.awt.image.renderable.ParameterBlock;
/*    */ import java.awt.image.renderable.RenderedImageFactory;
/*    */ import java.util.Vector;
/*    */ import javax.media.jai.ImageLayout;
/*    */ import javax.media.jai.PlanarImage;
/*    */ import javax.media.jai.ROI;
/*    */ import javax.media.jai.operator.MosaicType;
/*    */ 
/*    */ public class MlibMosaicRIF implements RenderedImageFactory {
/*    */   public RenderedImage create(ParameterBlock paramBlock, RenderingHints renderHints) {
/* 44 */     ImageLayout layout = RIFUtil.getImageLayoutHint(renderHints);
/* 47 */     if (!MediaLibAccessor.isMediaLibCompatible(paramBlock, layout) || !MediaLibAccessor.hasSameNumBands(paramBlock, layout))
/* 49 */       return null; 
/* 53 */     Vector sources = paramBlock.getSources();
/* 56 */     SampleModel targetSM = null;
/* 57 */     if (sources.size() > 0) {
/* 58 */       targetSM = ((RenderedImage)sources.get(0)).getSampleModel();
/* 59 */     } else if (layout != null && layout.isValid(256)) {
/* 61 */       targetSM = layout.getSampleModel(null);
/*    */     } 
/* 64 */     if (targetSM != null) {
/* 67 */       int dataType = targetSM.getDataType();
/* 68 */       if (dataType == 4 || dataType == 5)
/* 70 */         return null; 
/*    */     } 
/* 74 */     return (RenderedImage)new MlibMosaicOpImage(sources, layout, renderHints, (MosaicType)paramBlock.getObjectParameter(0), (PlanarImage[])paramBlock.getObjectParameter(1), (ROI[])paramBlock.getObjectParameter(2), (double[][])paramBlock.getObjectParameter(3), (double[])paramBlock.getObjectParameter(4));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\mlib\MlibMosaicRIF.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */