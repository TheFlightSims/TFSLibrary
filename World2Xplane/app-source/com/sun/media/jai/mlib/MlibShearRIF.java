/*     */ package com.sun.media.jai.mlib;
/*     */ 
/*     */ import com.sun.media.jai.opimage.RIFUtil;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.renderable.ParameterBlock;
/*     */ import java.awt.image.renderable.RenderedImageFactory;
/*     */ import javax.media.jai.BorderExtender;
/*     */ import javax.media.jai.EnumeratedParameter;
/*     */ import javax.media.jai.ImageLayout;
/*     */ import javax.media.jai.Interpolation;
/*     */ import javax.media.jai.operator.ShearDescriptor;
/*     */ 
/*     */ public class MlibShearRIF implements RenderedImageFactory {
/*     */   public RenderedImage create(ParameterBlock args, RenderingHints hints) {
/*  55 */     ImageLayout layout = RIFUtil.getImageLayoutHint(hints);
/*  57 */     Interpolation interp = (Interpolation)args.getObjectParameter(4);
/*  59 */     RenderedImage source = args.getRenderedSource(0);
/*  61 */     if (!MediaLibAccessor.isMediaLibCompatible(args, layout) || !MediaLibAccessor.hasSameNumBands(args, layout) || source.getTileWidth() >= 32768 || source.getTileHeight() >= 32768)
/*  67 */       return null; 
/*  71 */     BorderExtender extender = RIFUtil.getBorderExtenderHint(hints);
/*  73 */     float shear_amt = args.getFloatParameter(0);
/*  74 */     EnumeratedParameter shear_dir = (EnumeratedParameter)args.getObjectParameter(1);
/*  76 */     float xTrans = args.getFloatParameter(2);
/*  77 */     float yTrans = args.getFloatParameter(3);
/*  78 */     double[] backgroundValues = (double[])args.getObjectParameter(5);
/*  81 */     AffineTransform tr = new AffineTransform();
/*  83 */     if (shear_dir.equals(ShearDescriptor.SHEAR_HORIZONTAL)) {
/*  85 */       tr.setTransform(1.0D, 0.0D, shear_amt, 1.0D, xTrans, 0.0D);
/*     */     } else {
/*  88 */       tr.setTransform(1.0D, shear_amt, 0.0D, 1.0D, 0.0D, yTrans);
/*     */     } 
/*  91 */     if (interp instanceof javax.media.jai.InterpolationNearest)
/*  92 */       return (RenderedImage)new MlibAffineNearestOpImage(source, extender, hints, layout, tr, interp, backgroundValues); 
/*  97 */     if (interp instanceof javax.media.jai.InterpolationBilinear)
/*  98 */       return (RenderedImage)new MlibAffineBilinearOpImage(source, extender, hints, layout, tr, interp, backgroundValues); 
/* 103 */     if (interp instanceof javax.media.jai.InterpolationBicubic || interp instanceof javax.media.jai.InterpolationBicubic2)
/* 105 */       return (RenderedImage)new MlibAffineBicubicOpImage(source, extender, hints, layout, tr, interp, backgroundValues); 
/* 110 */     if (interp instanceof javax.media.jai.InterpolationTable)
/* 111 */       return (RenderedImage)new MlibAffineTableOpImage(source, extender, hints, layout, tr, interp, backgroundValues); 
/* 117 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\mlib\MlibShearRIF.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */