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
/*    */ public class MlibNotRIF implements RenderedImageFactory {
/*    */   public RenderedImage create(ParameterBlock args, RenderingHints hints) {
/* 45 */     ImageLayout layout = RIFUtil.getImageLayoutHint(hints);
/* 48 */     if (!MediaLibAccessor.isMediaLibCompatible(args, layout) || !MediaLibAccessor.hasSameNumBands(args, layout))
/* 50 */       return null; 
/* 54 */     if (layout != null) {
/* 55 */       SampleModel sm = layout.getSampleModel(null);
/* 57 */       if (sm != null) {
/* 58 */         int dtype = sm.getDataType();
/* 59 */         if (dtype == 4 || dtype == 5)
/* 61 */           return null; 
/*    */       } 
/*    */     } 
/* 66 */     return (RenderedImage)new MlibNotOpImage(args.getRenderedSource(0), hints, layout);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\mlib\MlibNotRIF.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */