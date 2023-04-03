/*     */ package com.sun.media.jai.mlib;
/*     */ 
/*     */ import com.sun.media.jai.opimage.RIFUtil;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.renderable.ParameterBlock;
/*     */ import java.awt.image.renderable.RenderedImageFactory;
/*     */ import javax.media.jai.BorderExtender;
/*     */ import javax.media.jai.ImageLayout;
/*     */ import javax.media.jai.Interpolation;
/*     */ import javax.media.jai.Warp;
/*     */ import javax.media.jai.WarpGrid;
/*     */ import javax.media.jai.WarpPolynomial;
/*     */ 
/*     */ public class MlibWarpRIF implements RenderedImageFactory {
/*     */   public RenderedImage create(ParameterBlock args, RenderingHints hints) {
/*  60 */     ImageLayout layout = RIFUtil.getImageLayoutHint(hints);
/*  62 */     RenderedImage source = args.getRenderedSource(0);
/*  65 */     if (!MediaLibAccessor.isMediaLibCompatible(args, layout) || !MediaLibAccessor.hasSameNumBands(args, layout) || source.getTileWidth() >= 32768 || source.getTileHeight() >= 32768)
/*  71 */       return null; 
/*  75 */     BorderExtender extender = RIFUtil.getBorderExtenderHint(hints);
/*  77 */     Warp warp = (Warp)args.getObjectParameter(0);
/*  78 */     Interpolation interp = (Interpolation)args.getObjectParameter(1);
/*  79 */     double[] backgroundValues = (double[])args.getObjectParameter(2);
/*  81 */     int filter = -1;
/*  82 */     if (interp instanceof javax.media.jai.InterpolationNearest) {
/*  83 */       filter = 0;
/*  84 */     } else if (interp instanceof javax.media.jai.InterpolationBilinear) {
/*  85 */       filter = 1;
/*  86 */     } else if (interp instanceof javax.media.jai.InterpolationBicubic) {
/*  87 */       filter = 2;
/*  88 */     } else if (interp instanceof javax.media.jai.InterpolationBicubic2) {
/*  89 */       filter = 3;
/*  90 */     } else if (!(interp instanceof javax.media.jai.InterpolationTable)) {
/*  95 */       return null;
/*     */     } 
/*  98 */     if (warp instanceof WarpGrid) {
/*  99 */       if (interp instanceof javax.media.jai.InterpolationTable)
/* 100 */         return (RenderedImage)new MlibWarpGridTableOpImage(source, extender, hints, layout, (WarpGrid)warp, interp, backgroundValues); 
/* 106 */       return (RenderedImage)new MlibWarpGridOpImage(source, extender, hints, layout, (WarpGrid)warp, interp, filter, backgroundValues);
/*     */     } 
/* 113 */     if (warp instanceof WarpPolynomial) {
/* 114 */       if (interp instanceof javax.media.jai.InterpolationTable)
/* 115 */         return (RenderedImage)new MlibWarpPolynomialTableOpImage(source, extender, hints, layout, (WarpPolynomial)warp, interp, backgroundValues); 
/* 121 */       return (RenderedImage)new MlibWarpPolynomialOpImage(source, extender, hints, layout, (WarpPolynomial)warp, interp, filter, backgroundValues);
/*     */     } 
/* 128 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\mlib\MlibWarpRIF.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */