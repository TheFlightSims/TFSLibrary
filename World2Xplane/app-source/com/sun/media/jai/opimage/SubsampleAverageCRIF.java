/*     */ package com.sun.media.jai.opimage;
/*     */ 
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.renderable.ParameterBlock;
/*     */ import java.awt.image.renderable.RenderContext;
/*     */ import java.awt.image.renderable.RenderableImage;
/*     */ import javax.media.jai.CRIFImpl;
/*     */ import javax.media.jai.ImageLayout;
/*     */ 
/*     */ public class SubsampleAverageCRIF extends CRIFImpl {
/*     */   public SubsampleAverageCRIF() {
/*  30 */     super("SubsampleAverage");
/*     */   }
/*     */   
/*     */   public RenderedImage create(ParameterBlock paramBlock, RenderingHints renderHints) {
/*  44 */     ImageLayout layout = RIFUtil.getImageLayoutHint(renderHints);
/*  46 */     RenderedImage source = paramBlock.getRenderedSource(0);
/*  47 */     double scaleX = paramBlock.getDoubleParameter(0);
/*  48 */     double scaleY = paramBlock.getDoubleParameter(1);
/*  52 */     if (scaleX == 1.0D && scaleY == 1.0D)
/*  53 */       return source; 
/*  56 */     return (RenderedImage)new SubsampleAverageOpImage(source, layout, renderHints, scaleX, scaleY);
/*     */   }
/*     */   
/*     */   public RenderedImage create(RenderContext renderContext, ParameterBlock paramBlock) {
/*  67 */     return paramBlock.getRenderedSource(0);
/*     */   }
/*     */   
/*     */   public RenderContext mapRenderContext(int i, RenderContext renderContext, ParameterBlock paramBlock, RenderableImage image) {
/*  87 */     double scaleX = paramBlock.getDoubleParameter(0);
/*  88 */     double scaleY = paramBlock.getDoubleParameter(1);
/*  90 */     AffineTransform scale = new AffineTransform(scaleX, 0.0D, 0.0D, scaleY, 0.0D, 0.0D);
/*  93 */     RenderContext RC = (RenderContext)renderContext.clone();
/*  94 */     AffineTransform usr2dev = RC.getTransform();
/*  95 */     usr2dev.concatenate(scale);
/*  96 */     RC.setTransform(usr2dev);
/*  97 */     return RC;
/*     */   }
/*     */   
/*     */   public Rectangle2D getBounds2D(ParameterBlock paramBlock) {
/* 106 */     RenderableImage source = paramBlock.getRenderableSource(0);
/* 108 */     double scaleX = paramBlock.getDoubleParameter(0);
/* 109 */     double scaleY = paramBlock.getDoubleParameter(1);
/* 112 */     float x0 = source.getMinX();
/* 113 */     float y0 = source.getMinY();
/* 114 */     float w = source.getWidth();
/* 115 */     float h = source.getHeight();
/* 118 */     float d_x0 = (float)(x0 * scaleX);
/* 119 */     float d_y0 = (float)(y0 * scaleY);
/* 120 */     float d_w = (float)(w * scaleX);
/* 121 */     float d_h = (float)(h * scaleY);
/* 123 */     return new Rectangle2D.Float(d_x0, d_y0, d_w, d_h);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\SubsampleAverageCRIF.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */