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
/*    */ import javax.media.jai.operator.CompositeDescriptor;
/*    */ 
/*    */ public class MlibCompositeRIF implements RenderedImageFactory {
/*    */   public RenderedImage create(ParameterBlock args, RenderingHints hints) {
/* 52 */     ImageLayout layout = RIFUtil.getImageLayoutHint(hints);
/* 55 */     if (!MediaLibAccessor.isMediaLibCompatible(args, layout) || !MediaLibAccessor.hasSameNumBands(args, layout))
/* 57 */       return null; 
/* 60 */     RenderedImage alpha1 = (RenderedImage)args.getObjectParameter(0);
/* 61 */     Object alpha2 = args.getObjectParameter(1);
/* 62 */     boolean premultiplied = ((Boolean)args.getObjectParameter(2)).booleanValue();
/* 64 */     EnumeratedParameter destAlpha = (EnumeratedParameter)args.getObjectParameter(3);
/* 67 */     SampleModel sm = alpha1.getSampleModel();
/* 69 */     if (!(sm instanceof java.awt.image.ComponentSampleModel) || sm.getNumBands() != 1 || !(alpha1.getColorModel() instanceof java.awt.image.ComponentColorModel) || alpha2 != null || premultiplied || !destAlpha.equals(CompositeDescriptor.NO_DESTINATION_ALPHA))
/* 75 */       return null; 
/* 79 */     return (RenderedImage)new MlibCompositeOpImage(args.getRenderedSource(0), args.getRenderedSource(1), hints, layout, alpha1);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\mlib\MlibCompositeRIF.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */