/*     */ package com.sun.media.jai.opimage;
/*     */ 
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.renderable.ParameterBlock;
/*     */ import java.awt.image.renderable.RenderContext;
/*     */ import javax.media.jai.CRIFImpl;
/*     */ import javax.media.jai.JAI;
/*     */ 
/*     */ public class CropCRIF extends CRIFImpl {
/*     */   public CropCRIF() {
/*  41 */     super("crop");
/*     */   }
/*     */   
/*     */   public RenderedImage create(ParameterBlock args, RenderingHints renderHints) {
/*  54 */     RenderedImage src = args.getRenderedSource(0);
/*  57 */     float originX = args.getFloatParameter(0);
/*  58 */     float originY = args.getFloatParameter(1);
/*  59 */     float width = args.getFloatParameter(2);
/*  60 */     float height = args.getFloatParameter(3);
/*  63 */     return (RenderedImage)new CropOpImage(src, originX, originY, width, height);
/*     */   }
/*     */   
/*     */   public RenderedImage create(RenderContext renderContext, ParameterBlock paramBlock) {
/*  81 */     Rectangle2D dstRect2D = getBounds2D(paramBlock);
/*  86 */     AffineTransform tf = renderContext.getTransform();
/*  87 */     Rectangle2D rect = tf.createTransformedShape(dstRect2D).getBounds2D();
/*  90 */     if (rect.getWidth() < 1.0D || rect.getHeight() < 1.0D) {
/*  91 */       double w = Math.max(rect.getWidth(), 1.0D);
/*  92 */       double h = Math.max(rect.getHeight(), 1.0D);
/*  93 */       rect.setRect(rect.getMinX(), rect.getMinY(), w, h);
/*     */     } 
/*  97 */     ParameterBlock pb = new ParameterBlock();
/*  98 */     pb.addSource(paramBlock.getRenderedSource(0));
/*  99 */     pb.set((float)rect.getMinX(), 0);
/* 100 */     pb.set((float)rect.getMinY(), 1);
/* 101 */     pb.set((float)rect.getWidth(), 2);
/* 102 */     pb.set((float)rect.getHeight(), 3);
/* 105 */     return (RenderedImage)JAI.create("crop", pb, renderContext.getRenderingHints());
/*     */   }
/*     */   
/*     */   public Rectangle2D getBounds2D(ParameterBlock paramBlock) {
/* 117 */     return new Rectangle2D.Float(paramBlock.getFloatParameter(0), paramBlock.getFloatParameter(1), paramBlock.getFloatParameter(2), paramBlock.getFloatParameter(3));
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\CropCRIF.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */