/*     */ package com.sun.media.jai.mlib;
/*     */ 
/*     */ import com.sun.media.jai.opimage.RIFUtil;
/*     */ import com.sun.media.jai.opimage.TranslateIntOpImage;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.SampleModel;
/*     */ import java.awt.image.renderable.ParameterBlock;
/*     */ import java.awt.image.renderable.RenderedImageFactory;
/*     */ import javax.media.jai.BorderExtender;
/*     */ import javax.media.jai.ImageLayout;
/*     */ import javax.media.jai.Interpolation;
/*     */ 
/*     */ public class MlibScaleRIF implements RenderedImageFactory {
/*     */   private static final float TOLERANCE = 0.01F;
/*     */   
/*     */   public RenderedImage create(ParameterBlock args, RenderingHints hints) {
/*  61 */     ImageLayout layout = RIFUtil.getImageLayoutHint(hints);
/*  63 */     Interpolation interp = (Interpolation)args.getObjectParameter(4);
/*  65 */     RenderedImage source = args.getRenderedSource(0);
/*  67 */     if (!MediaLibAccessor.isMediaLibCompatible(args, layout) || !MediaLibAccessor.hasSameNumBands(args, layout) || source.getTileWidth() >= 32768 || source.getTileHeight() >= 32768)
/*  73 */       return null; 
/*  76 */     SampleModel sm = source.getSampleModel();
/*  77 */     boolean isBilevel = (sm instanceof java.awt.image.MultiPixelPackedSampleModel && sm.getSampleSize(0) == 1 && (sm.getDataType() == 0 || sm.getDataType() == 1 || sm.getDataType() == 3));
/*  82 */     if (isBilevel)
/*  84 */       return null; 
/*  88 */     BorderExtender extender = RIFUtil.getBorderExtenderHint(hints);
/*  90 */     float xScale = args.getFloatParameter(0);
/*  91 */     float yScale = args.getFloatParameter(1);
/*  92 */     float xTrans = args.getFloatParameter(2);
/*  93 */     float yTrans = args.getFloatParameter(3);
/*  97 */     if (xScale == 1.0F && yScale == 1.0F && xTrans == 0.0F && yTrans == 0.0F)
/*  99 */       return (RenderedImage)new MlibCopyOpImage(source, hints, layout); 
/* 104 */     if (xScale == 1.0F && yScale == 1.0F && Math.abs(xTrans - (int)xTrans) < 0.01F && Math.abs(yTrans - (int)yTrans) < 0.01F && layout == null)
/* 109 */       return (RenderedImage)new TranslateIntOpImage(source, hints, (int)xTrans, (int)yTrans); 
/* 115 */     if (interp instanceof javax.media.jai.InterpolationNearest)
/* 116 */       return (RenderedImage)new MlibScaleNearestOpImage(source, extender, hints, layout, xScale, yScale, xTrans, yTrans, interp); 
/* 121 */     if (interp instanceof javax.media.jai.InterpolationBilinear)
/* 122 */       return (RenderedImage)new MlibScaleBilinearOpImage(source, extender, hints, layout, xScale, yScale, xTrans, yTrans, interp); 
/* 127 */     if (interp instanceof javax.media.jai.InterpolationBicubic || interp instanceof javax.media.jai.InterpolationBicubic2)
/* 129 */       return (RenderedImage)new MlibScaleBicubicOpImage(source, extender, hints, layout, xScale, yScale, xTrans, yTrans, interp); 
/* 134 */     if (interp instanceof javax.media.jai.InterpolationTable)
/* 135 */       return (RenderedImage)new MlibScaleTableOpImage(source, extender, hints, layout, xScale, yScale, xTrans, yTrans, interp); 
/* 141 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\mlib\MlibScaleRIF.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */