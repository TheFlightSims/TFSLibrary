/*     */ package com.sun.media.jai.opimage;
/*     */ 
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.SampleModel;
/*     */ import java.awt.image.renderable.ParameterBlock;
/*     */ import java.awt.image.renderable.RenderContext;
/*     */ import java.awt.image.renderable.RenderableImage;
/*     */ import javax.media.jai.BorderExtender;
/*     */ import javax.media.jai.CRIFImpl;
/*     */ import javax.media.jai.ImageLayout;
/*     */ import javax.media.jai.Interpolation;
/*     */ import javax.media.jai.TileCache;
/*     */ 
/*     */ public class TranslateCRIF extends CRIFImpl {
/*     */   private static final float TOLERANCE = 0.01F;
/*     */   
/*     */   public TranslateCRIF() {
/*  49 */     super("translate");
/*     */   }
/*     */   
/*     */   public RenderedImage create(ParameterBlock paramBlock, RenderingHints renderHints) {
/*  59 */     RenderedImage source = paramBlock.getRenderedSource(0);
/*  60 */     float xTrans = paramBlock.getFloatParameter(0);
/*  61 */     float yTrans = paramBlock.getFloatParameter(1);
/*  62 */     Interpolation interp = (Interpolation)paramBlock.getObjectParameter(2);
/*  66 */     ImageLayout layout = RIFUtil.getImageLayoutHint(renderHints);
/*  69 */     if (Math.abs(xTrans - (int)xTrans) < 0.01F && Math.abs(yTrans - (int)yTrans) < 0.01F && layout == null)
/*  72 */       return (RenderedImage)new TranslateIntOpImage(source, renderHints, (int)xTrans, (int)yTrans); 
/*  79 */     TileCache cache = RIFUtil.getTileCacheHint(renderHints);
/*  82 */     BorderExtender extender = RIFUtil.getBorderExtenderHint(renderHints);
/*  89 */     float xScale = 1.0F;
/*  90 */     float yScale = 1.0F;
/*  91 */     SampleModel sm = source.getSampleModel();
/*  92 */     boolean isBinary = (sm instanceof java.awt.image.MultiPixelPackedSampleModel && sm.getSampleSize(0) == 1 && (sm.getDataType() == 0 || sm.getDataType() == 1 || sm.getDataType() == 3));
/*  99 */     if (interp instanceof javax.media.jai.InterpolationNearest) {
/* 101 */       if (isBinary)
/* 103 */         return (RenderedImage)new ScaleNearestBinaryOpImage(source, extender, renderHints, layout, xScale, yScale, xTrans, yTrans, interp); 
/* 115 */       return (RenderedImage)new ScaleNearestOpImage(source, extender, renderHints, layout, xScale, yScale, xTrans, yTrans, interp);
/*     */     } 
/* 125 */     if (interp instanceof javax.media.jai.InterpolationBilinear) {
/* 127 */       if (isBinary)
/* 129 */         return (RenderedImage)new ScaleBilinearBinaryOpImage(source, extender, renderHints, layout, xScale, yScale, xTrans, yTrans, interp); 
/* 141 */       return (RenderedImage)new ScaleBilinearOpImage(source, extender, renderHints, layout, xScale, yScale, xTrans, yTrans, interp);
/*     */     } 
/* 151 */     if (interp instanceof javax.media.jai.InterpolationBicubic || interp instanceof javax.media.jai.InterpolationBicubic2)
/* 154 */       return (RenderedImage)new ScaleBicubicOpImage(source, extender, renderHints, layout, xScale, yScale, xTrans, yTrans, interp); 
/* 164 */     return (RenderedImage)new ScaleGeneralOpImage(source, extender, renderHints, layout, xScale, yScale, xTrans, yTrans, interp);
/*     */   }
/*     */   
/*     */   public RenderedImage create(RenderContext renderContext, ParameterBlock paramBlock) {
/* 181 */     return paramBlock.getRenderedSource(0);
/*     */   }
/*     */   
/*     */   public RenderContext mapRenderContext(int i, RenderContext renderContext, ParameterBlock paramBlock, RenderableImage image) {
/* 200 */     AffineTransform translate = new AffineTransform();
/* 201 */     translate.setToTranslation(paramBlock.getFloatParameter(0), paramBlock.getFloatParameter(1));
/* 204 */     RenderContext RC = (RenderContext)renderContext.clone();
/* 205 */     AffineTransform usr2dev = RC.getTransform();
/* 206 */     usr2dev.concatenate(translate);
/* 207 */     RC.setTransform(usr2dev);
/* 208 */     return RC;
/*     */   }
/*     */   
/*     */   public Rectangle2D getBounds2D(ParameterBlock paramBlock) {
/* 216 */     RenderableImage source = paramBlock.getRenderableSource(0);
/* 217 */     float xTrans = paramBlock.getFloatParameter(0);
/* 218 */     float yTrans = paramBlock.getFloatParameter(1);
/* 220 */     return new Rectangle2D.Float(source.getMinX() + xTrans, source.getMinY() + yTrans, source.getWidth(), source.getHeight());
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\TranslateCRIF.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */