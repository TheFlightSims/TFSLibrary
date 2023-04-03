/*     */ package com.sun.media.jai.mlib;
/*     */ 
/*     */ import com.sun.media.jai.opimage.RIFUtil;
/*     */ import com.sun.media.jai.opimage.TranslateIntOpImage;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.SampleModel;
/*     */ import java.awt.image.renderable.ParameterBlock;
/*     */ import java.awt.image.renderable.RenderedImageFactory;
/*     */ import javax.media.jai.BorderExtender;
/*     */ import javax.media.jai.ImageLayout;
/*     */ import javax.media.jai.Interpolation;
/*     */ 
/*     */ public class MlibAffineRIF implements RenderedImageFactory {
/*     */   private static final float TOLERANCE = 0.01F;
/*     */   
/*     */   public RenderedImage create(ParameterBlock args, RenderingHints hints) {
/*  61 */     ImageLayout layout = RIFUtil.getImageLayoutHint(hints);
/*  64 */     AffineTransform transform = (AffineTransform)args.getObjectParameter(0);
/*  66 */     Interpolation interp = (Interpolation)args.getObjectParameter(1);
/*  67 */     double[] backgroundValues = (double[])args.getObjectParameter(2);
/*  69 */     RenderedImage source = args.getRenderedSource(0);
/*  71 */     if (!MediaLibAccessor.isMediaLibCompatible(args, layout) || !MediaLibAccessor.hasSameNumBands(args, layout) || source.getTileWidth() >= 32768 || source.getTileHeight() >= 32768)
/*  77 */       return null; 
/*  80 */     SampleModel sm = source.getSampleModel();
/*  81 */     boolean isBilevel = (sm instanceof java.awt.image.MultiPixelPackedSampleModel && sm.getSampleSize(0) == 1 && (sm.getDataType() == 0 || sm.getDataType() == 1 || sm.getDataType() == 3));
/*  86 */     if (isBilevel)
/*  88 */       return null; 
/*  92 */     BorderExtender extender = RIFUtil.getBorderExtenderHint(hints);
/*  95 */     double[] tr = new double[6];
/*  96 */     transform.getMatrix(tr);
/* 102 */     if (tr[0] == 1.0D && tr[3] == 1.0D && tr[2] == 0.0D && tr[1] == 0.0D && tr[4] == 0.0D && tr[5] == 0.0D)
/* 109 */       return (RenderedImage)new MlibCopyOpImage(source, hints, layout); 
/* 119 */     if (tr[0] == 1.0D && tr[3] == 1.0D && tr[2] == 0.0D && tr[1] == 0.0D && Math.abs(tr[4] - (int)tr[4]) < 0.009999999776482582D && Math.abs(tr[5] - (int)tr[5]) < 0.009999999776482582D && layout == null)
/* 127 */       return (RenderedImage)new TranslateIntOpImage(source, hints, (int)tr[4], (int)tr[5]); 
/* 138 */     if (tr[0] > 0.0D && tr[2] == 0.0D && tr[1] == 0.0D && tr[3] > 0.0D) {
/* 143 */       if (interp instanceof javax.media.jai.InterpolationNearest)
/* 144 */         return (RenderedImage)new MlibScaleNearestOpImage(source, extender, hints, layout, (float)tr[0], (float)tr[3], (float)tr[4], (float)tr[5], interp); 
/* 153 */       if (interp instanceof javax.media.jai.InterpolationBilinear)
/* 154 */         return (RenderedImage)new MlibScaleBilinearOpImage(source, extender, hints, layout, (float)tr[0], (float)tr[3], (float)tr[4], (float)tr[5], interp); 
/* 163 */       if (interp instanceof javax.media.jai.InterpolationBicubic || interp instanceof javax.media.jai.InterpolationBicubic2)
/* 165 */         return (RenderedImage)new MlibScaleBicubicOpImage(source, extender, hints, layout, (float)tr[0], (float)tr[3], (float)tr[4], (float)tr[5], interp); 
/* 174 */       if (interp instanceof javax.media.jai.InterpolationTable)
/* 175 */         return (RenderedImage)new MlibScaleTableOpImage(source, extender, hints, layout, (float)tr[0], (float)tr[3], (float)tr[4], (float)tr[5], interp); 
/* 185 */       return null;
/*     */     } 
/* 190 */     if (interp instanceof javax.media.jai.InterpolationNearest)
/* 191 */       return (RenderedImage)new MlibAffineNearestOpImage(source, extender, hints, layout, transform, interp, backgroundValues); 
/* 198 */     if (interp instanceof javax.media.jai.InterpolationBilinear)
/* 199 */       return (RenderedImage)new MlibAffineBilinearOpImage(source, extender, hints, layout, transform, interp, backgroundValues); 
/* 206 */     if (interp instanceof javax.media.jai.InterpolationBicubic || interp instanceof javax.media.jai.InterpolationBicubic2)
/* 208 */       return (RenderedImage)new MlibAffineBicubicOpImage(source, extender, hints, layout, transform, interp, backgroundValues); 
/* 215 */     if (interp instanceof javax.media.jai.InterpolationTable)
/* 216 */       return (RenderedImage)new MlibAffineTableOpImage(source, extender, hints, layout, transform, interp, backgroundValues); 
/* 224 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\mlib\MlibAffineRIF.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */