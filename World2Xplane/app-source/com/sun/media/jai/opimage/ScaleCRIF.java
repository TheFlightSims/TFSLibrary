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
/*     */ public class ScaleCRIF extends CRIFImpl {
/*     */   private static final float TOLERANCE = 0.01F;
/*     */   
/*     */   public ScaleCRIF() {
/*  46 */     super("scale");
/*     */   }
/*     */   
/*     */   public RenderedImage create(ParameterBlock paramBlock, RenderingHints renderHints) {
/*  60 */     ImageLayout layout = RIFUtil.getImageLayoutHint(renderHints);
/*  63 */     TileCache cache = RIFUtil.getTileCacheHint(renderHints);
/*  66 */     BorderExtender extender = RIFUtil.getBorderExtenderHint(renderHints);
/*  68 */     RenderedImage source = paramBlock.getRenderedSource(0);
/*  69 */     float xScale = paramBlock.getFloatParameter(0);
/*  70 */     float yScale = paramBlock.getFloatParameter(1);
/*  71 */     float xTrans = paramBlock.getFloatParameter(2);
/*  72 */     float yTrans = paramBlock.getFloatParameter(3);
/*  73 */     Interpolation interp = (Interpolation)paramBlock.getObjectParameter(4);
/*  78 */     if (xScale == 1.0F && yScale == 1.0F && xTrans == 0.0F && yTrans == 0.0F)
/*  80 */       return (RenderedImage)new CopyOpImage(source, renderHints, layout); 
/*  88 */     if (xScale == 1.0F && yScale == 1.0F && Math.abs(xTrans - (int)xTrans) < 0.01F && Math.abs(yTrans - (int)yTrans) < 0.01F && layout == null)
/*  93 */       return (RenderedImage)new TranslateIntOpImage(source, renderHints, (int)xTrans, (int)yTrans); 
/*  99 */     if (interp instanceof javax.media.jai.InterpolationNearest) {
/* 110 */       SampleModel sm = source.getSampleModel();
/* 111 */       if (sm instanceof java.awt.image.MultiPixelPackedSampleModel && sm.getSampleSize(0) == 1 && (sm.getDataType() == 0 || sm.getDataType() == 1 || sm.getDataType() == 3))
/* 116 */         return (RenderedImage)new ScaleNearestBinaryOpImage(source, extender, renderHints, layout, xScale, yScale, xTrans, yTrans, interp); 
/* 126 */       return (RenderedImage)new ScaleNearestOpImage(source, extender, renderHints, layout, xScale, yScale, xTrans, yTrans, interp);
/*     */     } 
/* 136 */     if (interp instanceof javax.media.jai.InterpolationBilinear) {
/* 138 */       SampleModel sm = source.getSampleModel();
/* 139 */       if (sm instanceof java.awt.image.MultiPixelPackedSampleModel && sm.getSampleSize(0) == 1 && (sm.getDataType() == 0 || sm.getDataType() == 1 || sm.getDataType() == 3))
/* 145 */         return (RenderedImage)new ScaleBilinearBinaryOpImage(source, extender, renderHints, layout, xScale, yScale, xTrans, yTrans, interp); 
/* 157 */       return (RenderedImage)new ScaleBilinearOpImage(source, extender, renderHints, layout, xScale, yScale, xTrans, yTrans, interp);
/*     */     } 
/* 164 */     if (interp instanceof javax.media.jai.InterpolationBicubic || interp instanceof javax.media.jai.InterpolationBicubic2)
/* 166 */       return (RenderedImage)new ScaleBicubicOpImage(source, extender, renderHints, layout, xScale, yScale, xTrans, yTrans, interp); 
/* 173 */     return (RenderedImage)new ScaleGeneralOpImage(source, extender, renderHints, layout, xScale, yScale, xTrans, yTrans, interp);
/*     */   }
/*     */   
/*     */   public RenderedImage create(RenderContext renderContext, ParameterBlock paramBlock) {
/* 189 */     return paramBlock.getRenderedSource(0);
/*     */   }
/*     */   
/*     */   public RenderContext mapRenderContext(int i, RenderContext renderContext, ParameterBlock paramBlock, RenderableImage image) {
/* 209 */     float scale_x = paramBlock.getFloatParameter(0);
/* 210 */     float scale_y = paramBlock.getFloatParameter(1);
/* 211 */     float trans_x = paramBlock.getFloatParameter(2);
/* 212 */     float trans_y = paramBlock.getFloatParameter(3);
/* 214 */     AffineTransform scale = new AffineTransform(scale_x, 0.0D, 0.0D, scale_y, trans_x, trans_y);
/* 217 */     RenderContext RC = (RenderContext)renderContext.clone();
/* 218 */     AffineTransform usr2dev = RC.getTransform();
/* 219 */     usr2dev.concatenate(scale);
/* 220 */     RC.setTransform(usr2dev);
/* 221 */     return RC;
/*     */   }
/*     */   
/*     */   public Rectangle2D getBounds2D(ParameterBlock paramBlock) {
/* 230 */     RenderableImage source = paramBlock.getRenderableSource(0);
/* 232 */     float scale_x = paramBlock.getFloatParameter(0);
/* 233 */     float scale_y = paramBlock.getFloatParameter(1);
/* 234 */     float trans_x = paramBlock.getFloatParameter(2);
/* 235 */     float trans_y = paramBlock.getFloatParameter(3);
/* 236 */     Interpolation interp = (Interpolation)paramBlock.getObjectParameter(4);
/* 239 */     float x0 = source.getMinX();
/* 240 */     float y0 = source.getMinY();
/* 241 */     float w = source.getWidth();
/* 242 */     float h = source.getHeight();
/* 245 */     float d_x0 = x0 * scale_x + trans_x;
/* 246 */     float d_y0 = y0 * scale_y + trans_y;
/* 247 */     float d_w = w * scale_x;
/* 248 */     float d_h = h * scale_y;
/* 250 */     return new Rectangle2D.Float(d_x0, d_y0, d_w, d_h);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\ScaleCRIF.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */