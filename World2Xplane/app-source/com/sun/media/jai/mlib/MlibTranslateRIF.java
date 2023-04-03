/*     */ package com.sun.media.jai.mlib;
/*     */ 
/*     */ import com.sun.media.jai.opimage.RIFUtil;
/*     */ import com.sun.media.jai.opimage.TranslateIntOpImage;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.renderable.ParameterBlock;
/*     */ import java.awt.image.renderable.RenderedImageFactory;
/*     */ import javax.media.jai.BorderExtender;
/*     */ import javax.media.jai.ImageLayout;
/*     */ import javax.media.jai.Interpolation;
/*     */ 
/*     */ public class MlibTranslateRIF implements RenderedImageFactory {
/*     */   private static final float TOLERANCE = 0.01F;
/*     */   
/*     */   public RenderedImage create(ParameterBlock args, RenderingHints hints) {
/*  55 */     RenderedImage source = args.getRenderedSource(0);
/*  56 */     float xTrans = args.getFloatParameter(0);
/*  57 */     float yTrans = args.getFloatParameter(1);
/*  58 */     Interpolation interp = (Interpolation)args.getObjectParameter(2);
/*  61 */     ImageLayout layout = RIFUtil.getImageLayoutHint(hints);
/*  63 */     if (Math.abs(xTrans - (int)xTrans) < 0.01F && Math.abs(yTrans - (int)yTrans) < 0.01F && layout == null)
/*  66 */       return (RenderedImage)new TranslateIntOpImage(source, hints, (int)xTrans, (int)yTrans); 
/*  72 */     if (!MediaLibAccessor.isMediaLibCompatible(args, layout) || !MediaLibAccessor.hasSameNumBands(args, layout) || source.getTileWidth() >= 32768 || source.getTileHeight() >= 32768)
/*  78 */       return null; 
/*  82 */     BorderExtender extender = RIFUtil.getBorderExtenderHint(hints);
/*  88 */     float xScale = 1.0F;
/*  89 */     float yScale = 1.0F;
/*  90 */     if (interp instanceof javax.media.jai.InterpolationNearest)
/*  91 */       return (RenderedImage)new MlibScaleNearestOpImage(source, extender, hints, layout, xScale, yScale, xTrans, yTrans, interp); 
/*  96 */     if (interp instanceof javax.media.jai.InterpolationBilinear)
/*  97 */       return (RenderedImage)new MlibScaleBilinearOpImage(source, extender, hints, layout, xScale, yScale, xTrans, yTrans, interp); 
/* 102 */     if (interp instanceof javax.media.jai.InterpolationBicubic || interp instanceof javax.media.jai.InterpolationBicubic2)
/* 104 */       return (RenderedImage)new MlibScaleBicubicOpImage(source, extender, hints, layout, xScale, yScale, xTrans, yTrans, interp); 
/* 109 */     if (interp instanceof javax.media.jai.InterpolationTable)
/* 110 */       return (RenderedImage)new MlibScaleTableOpImage(source, extender, hints, layout, xScale, yScale, xTrans, yTrans, interp); 
/* 116 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\mlib\MlibTranslateRIF.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */