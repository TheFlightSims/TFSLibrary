/*    */ package com.sun.media.jai.opimage;
/*    */ 
/*    */ import java.awt.RenderingHints;
/*    */ import java.awt.image.RenderedImage;
/*    */ import java.awt.image.SampleModel;
/*    */ import java.awt.image.renderable.ParameterBlock;
/*    */ import java.awt.image.renderable.RenderedImageFactory;
/*    */ import javax.media.jai.BorderExtender;
/*    */ import javax.media.jai.ImageLayout;
/*    */ import javax.media.jai.Interpolation;
/*    */ import javax.media.jai.JAI;
/*    */ 
/*    */ public class FilteredSubsampleRIF implements RenderedImageFactory {
/*    */   public RenderedImage create(ParameterBlock paramBlock, RenderingHints renderHints) {
/* 48 */     RenderedImage source = paramBlock.getRenderedSource(0);
/* 50 */     BorderExtender extender = (renderHints == null) ? null : (BorderExtender)renderHints.get(JAI.KEY_BORDER_EXTENDER);
/* 52 */     ImageLayout layout = (renderHints == null) ? null : (ImageLayout)renderHints.get(JAI.KEY_IMAGE_LAYOUT);
/* 55 */     int scaleX = paramBlock.getIntParameter(0);
/* 56 */     int scaleY = paramBlock.getIntParameter(1);
/* 57 */     float[] qsFilter = (float[])paramBlock.getObjectParameter(2);
/* 58 */     Interpolation interp = (Interpolation)paramBlock.getObjectParameter(3);
/* 61 */     SampleModel sm = source.getSampleModel();
/* 62 */     int dataType = sm.getDataType();
/* 65 */     boolean validInterp = (interp instanceof javax.media.jai.InterpolationNearest || interp instanceof javax.media.jai.InterpolationBilinear || interp instanceof javax.media.jai.InterpolationBicubic || interp instanceof javax.media.jai.InterpolationBicubic2);
/* 70 */     if (!validInterp)
/* 71 */       throw new IllegalArgumentException(JaiI18N.getString("FilteredSubsample3")); 
/* 74 */     return (RenderedImage)new FilteredSubsampleOpImage(source, extender, renderHints, layout, scaleX, scaleY, qsFilter, interp);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\FilteredSubsampleRIF.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */