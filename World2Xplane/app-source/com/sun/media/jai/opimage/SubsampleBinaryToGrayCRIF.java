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
/*     */ 
/*     */ public class SubsampleBinaryToGrayCRIF extends CRIFImpl {
/*     */   public SubsampleBinaryToGrayCRIF() {
/*  36 */     super("subsamplebinarytogray");
/*     */   }
/*     */   
/*     */   public RenderedImage create(ParameterBlock paramBlock, RenderingHints renderHints) {
/*  48 */     ImageLayout layout = RIFUtil.getImageLayoutHint(renderHints);
/*  52 */     BorderExtender extender = RIFUtil.getBorderExtenderHint(renderHints);
/*  54 */     RenderedImage source = paramBlock.getRenderedSource(0);
/*  55 */     float xScale = paramBlock.getFloatParameter(0);
/*  56 */     float yScale = paramBlock.getFloatParameter(1);
/*  60 */     if (xScale == 1.0F && yScale == 1.0F)
/*  61 */       return (RenderedImage)new CopyOpImage(source, renderHints, layout); 
/*  74 */     SampleModel sm = source.getSampleModel();
/*  75 */     if (sm instanceof java.awt.image.MultiPixelPackedSampleModel && sm.getSampleSize(0) == 1 && (sm.getDataType() == 0 || sm.getDataType() == 1 || sm.getDataType() == 3)) {
/*  81 */       int srcWidth = source.getWidth();
/*  82 */       int srcHeight = source.getHeight();
/*  83 */       float floatTol = 0.1F * Math.min(xScale / (srcWidth * xScale + 1.0F), yScale / (srcHeight * yScale + 1.0F));
/*  84 */       int invScale = Math.round(1.0F / xScale);
/*  85 */       if (Math.abs(invScale - 1.0F / xScale) < floatTol && Math.abs(invScale - 1.0F / yScale) < floatTol)
/*  87 */         switch (invScale) {
/*     */           case 2:
/*  89 */             return (RenderedImage)new SubsampleBinaryToGray2x2OpImage(source, layout, renderHints);
/*     */           case 4:
/*  91 */             return (RenderedImage)new SubsampleBinaryToGray4x4OpImage(source, layout, renderHints);
/*     */         }  
/*  96 */       return (RenderedImage)new SubsampleBinaryToGrayOpImage(source, layout, renderHints, xScale, yScale);
/*     */     } 
/* 103 */     throw new IllegalArgumentException(JaiI18N.getString("SubsampleBinaryToGray3"));
/*     */   }
/*     */   
/*     */   public RenderedImage create(RenderContext renderContext, ParameterBlock paramBlock) {
/* 118 */     return paramBlock.getRenderedSource(0);
/*     */   }
/*     */   
/*     */   public RenderContext mapRenderContext(int i, RenderContext renderContext, ParameterBlock paramBlock, RenderableImage image) {
/* 138 */     float scale_x = paramBlock.getFloatParameter(0);
/* 139 */     float scale_y = paramBlock.getFloatParameter(1);
/* 141 */     AffineTransform scale = new AffineTransform(scale_x, 0.0D, 0.0D, scale_y, 0.0D, 0.0D);
/* 144 */     RenderContext RC = (RenderContext)renderContext.clone();
/* 145 */     AffineTransform usr2dev = RC.getTransform();
/* 146 */     usr2dev.concatenate(scale);
/* 147 */     RC.setTransform(usr2dev);
/* 148 */     return RC;
/*     */   }
/*     */   
/*     */   public Rectangle2D getBounds2D(ParameterBlock paramBlock) {
/* 157 */     RenderableImage source = paramBlock.getRenderableSource(0);
/* 159 */     float scale_x = paramBlock.getFloatParameter(0);
/* 160 */     float scale_y = paramBlock.getFloatParameter(1);
/* 163 */     float x0 = source.getMinX();
/* 164 */     float y0 = source.getMinY();
/* 165 */     float w = source.getWidth();
/* 166 */     float h = source.getHeight();
/* 169 */     float d_x0 = x0 * scale_x;
/* 170 */     float d_y0 = y0 * scale_y;
/* 171 */     float d_w = w * scale_x;
/* 172 */     float d_h = h * scale_y;
/* 177 */     return new Rectangle2D.Float(d_x0, d_y0, d_w, d_h);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\SubsampleBinaryToGrayCRIF.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */