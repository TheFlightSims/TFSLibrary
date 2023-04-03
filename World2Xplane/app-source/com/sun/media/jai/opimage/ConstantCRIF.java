/*     */ package com.sun.media.jai.opimage;
/*     */ 
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.renderable.ParameterBlock;
/*     */ import java.awt.image.renderable.RenderContext;
/*     */ import javax.media.jai.CRIFImpl;
/*     */ import javax.media.jai.ImageLayout;
/*     */ 
/*     */ public class ConstantCRIF extends CRIFImpl {
/*     */   private static final int DEFAULT_TILE_SIZE = 128;
/*     */   
/*     */   public ConstantCRIF() {
/*  37 */     super("constant");
/*     */   }
/*     */   
/*     */   public RenderedImage create(ParameterBlock paramBlock, RenderingHints renderHints) {
/*  48 */     ImageLayout layout = RIFUtil.getImageLayoutHint(renderHints);
/*  51 */     int width = Math.round(paramBlock.getFloatParameter(0));
/*  52 */     int height = Math.round(paramBlock.getFloatParameter(1));
/*  53 */     Number[] bandValues = (Number[])paramBlock.getObjectParameter(2);
/*  55 */     int minX = 0;
/*  56 */     int minY = 0;
/*  57 */     int tileWidth = Math.min(width, 128);
/*  58 */     int tileHeight = Math.min(height, 128);
/*  62 */     if (layout != null) {
/*  63 */       if (layout.isValid(1))
/*  64 */         minX = layout.getMinX(null); 
/*  66 */       if (layout.isValid(2))
/*  67 */         minY = layout.getMinY(null); 
/*  69 */       if (layout.isValid(64))
/*  70 */         tileWidth = layout.getTileWidth(null); 
/*  72 */       if (layout.isValid(128))
/*  73 */         tileHeight = layout.getTileHeight(null); 
/*     */     } 
/*  77 */     return (RenderedImage)new ConstantOpImage(minX, minY, width, height, tileWidth, tileHeight, bandValues);
/*     */   }
/*     */   
/*     */   public RenderedImage create(RenderContext renderContext, ParameterBlock paramBlock) {
/*  94 */     float minX = 0.0F;
/*  95 */     float minY = 0.0F;
/*  96 */     float width = paramBlock.getFloatParameter(0);
/*  97 */     float height = paramBlock.getFloatParameter(1);
/*  98 */     Number[] bandValues = (Number[])paramBlock.getObjectParameter(2);
/* 100 */     AffineTransform trans = renderContext.getTransform();
/* 102 */     float[] ptSrc = new float[8];
/* 103 */     float[] ptDst = new float[8];
/* 105 */     ptSrc[0] = minX;
/* 106 */     ptSrc[1] = minY;
/* 107 */     ptSrc[2] = minX + width;
/* 108 */     ptSrc[3] = minY;
/* 109 */     ptSrc[4] = minX + width;
/* 110 */     ptSrc[5] = minY + height;
/* 111 */     ptSrc[6] = minX;
/* 112 */     ptSrc[7] = minY + height;
/* 113 */     trans.transform(ptSrc, 0, ptDst, 0, 4);
/* 115 */     minX = Math.min(ptDst[0], ptDst[2]);
/* 116 */     minX = Math.min(minX, ptDst[4]);
/* 117 */     minX = Math.min(minX, ptDst[6]);
/* 119 */     float maxX = Math.max(ptDst[0], ptDst[2]);
/* 120 */     maxX = Math.max(maxX, ptDst[4]);
/* 121 */     maxX = Math.max(maxX, ptDst[6]);
/* 123 */     minY = Math.min(ptDst[1], ptDst[3]);
/* 124 */     minY = Math.min(minY, ptDst[5]);
/* 125 */     minY = Math.min(minY, ptDst[7]);
/* 127 */     float maxY = Math.max(ptDst[1], ptDst[3]);
/* 128 */     maxY = Math.max(maxY, ptDst[5]);
/* 129 */     maxY = Math.max(maxY, ptDst[7]);
/* 131 */     int iMinX = (int)minX;
/* 132 */     int iMinY = (int)minY;
/* 133 */     int iWidth = (int)maxX - iMinX;
/* 134 */     int iHeight = (int)maxY - iMinY;
/* 136 */     return (RenderedImage)new ConstantOpImage(iMinX, iMinY, iWidth, iHeight, Math.min(iWidth, 128), Math.min(iHeight, 128), bandValues);
/*     */   }
/*     */   
/*     */   public Rectangle2D getBounds2D(ParameterBlock paramBlock) {
/* 149 */     return new Rectangle2D.Float(0.0F, 0.0F, paramBlock.getFloatParameter(0), paramBlock.getFloatParameter(1));
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\ConstantCRIF.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */