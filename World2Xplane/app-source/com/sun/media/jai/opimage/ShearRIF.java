/*     */ package com.sun.media.jai.opimage;
/*     */ 
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.SampleModel;
/*     */ import java.awt.image.renderable.ParameterBlock;
/*     */ import java.awt.image.renderable.RenderedImageFactory;
/*     */ import javax.media.jai.BorderExtender;
/*     */ import javax.media.jai.EnumeratedParameter;
/*     */ import javax.media.jai.ImageLayout;
/*     */ import javax.media.jai.Interpolation;
/*     */ import javax.media.jai.operator.ShearDescriptor;
/*     */ 
/*     */ public class ShearRIF implements RenderedImageFactory {
/*     */   public RenderedImage create(ParameterBlock paramBlock, RenderingHints renderHints) {
/*  49 */     ImageLayout layout = RIFUtil.getImageLayoutHint(renderHints);
/*  53 */     BorderExtender extender = RIFUtil.getBorderExtenderHint(renderHints);
/*  55 */     RenderedImage source = paramBlock.getRenderedSource(0);
/*  57 */     float shear_amt = paramBlock.getFloatParameter(0);
/*  58 */     EnumeratedParameter shear_dir = (EnumeratedParameter)paramBlock.getObjectParameter(1);
/*  61 */     float xTrans = paramBlock.getFloatParameter(2);
/*  62 */     float yTrans = paramBlock.getFloatParameter(3);
/*  64 */     Object arg1 = paramBlock.getObjectParameter(4);
/*  65 */     Interpolation interp = (Interpolation)arg1;
/*  67 */     double[] backgroundValues = (double[])paramBlock.getObjectParameter(5);
/*  70 */     AffineTransform tr = new AffineTransform();
/*  72 */     if (shear_dir.equals(ShearDescriptor.SHEAR_HORIZONTAL)) {
/*  74 */       tr.setTransform(1.0D, 0.0D, shear_amt, 1.0D, xTrans, 0.0D);
/*     */     } else {
/*  77 */       tr.setTransform(1.0D, shear_amt, 0.0D, 1.0D, 0.0D, yTrans);
/*     */     } 
/*  81 */     if (interp instanceof javax.media.jai.InterpolationNearest) {
/*  82 */       SampleModel sm = source.getSampleModel();
/*  83 */       boolean isBinary = (sm instanceof java.awt.image.MultiPixelPackedSampleModel && sm.getSampleSize(0) == 1 && (sm.getDataType() == 0 || sm.getDataType() == 1 || sm.getDataType() == 3));
/*  88 */       if (isBinary)
/*  89 */         return (RenderedImage)new AffineNearestBinaryOpImage(source, extender, renderHints, layout, tr, interp, backgroundValues); 
/*  97 */       return (RenderedImage)new AffineNearestOpImage(source, extender, renderHints, layout, tr, interp, backgroundValues);
/*     */     } 
/* 104 */     if (interp instanceof javax.media.jai.InterpolationBilinear)
/* 105 */       return (RenderedImage)new AffineBilinearOpImage(source, extender, renderHints, layout, tr, interp, backgroundValues); 
/* 112 */     if (interp instanceof javax.media.jai.InterpolationBicubic)
/* 113 */       return (RenderedImage)new AffineBicubicOpImage(source, extender, renderHints, layout, tr, interp, backgroundValues); 
/* 120 */     if (interp instanceof javax.media.jai.InterpolationBicubic2)
/* 121 */       return (RenderedImage)new AffineBicubic2OpImage(source, extender, renderHints, layout, tr, interp, backgroundValues); 
/* 129 */     return (RenderedImage)new AffineGeneralOpImage(source, extender, renderHints, layout, tr, interp, backgroundValues);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\ShearRIF.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */