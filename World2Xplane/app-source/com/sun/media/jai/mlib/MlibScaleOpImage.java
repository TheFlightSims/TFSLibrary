/*     */ package com.sun.media.jai.mlib;
/*     */ 
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.util.Map;
/*     */ import javax.media.jai.BorderExtender;
/*     */ import javax.media.jai.ImageLayout;
/*     */ import javax.media.jai.Interpolation;
/*     */ import javax.media.jai.ScaleOpImage;
/*     */ 
/*     */ abstract class MlibScaleOpImage extends ScaleOpImage {
/*     */   public MlibScaleOpImage(RenderedImage source, BorderExtender extender, Map config, ImageLayout layout, float scaleX, float scaleY, float transX, float transY, Interpolation interp, boolean cobbleSources) {
/*  62 */     super(source, layout, config, cobbleSources, extender, interp, scaleX, scaleY, transX, transY);
/*  77 */     this.extender = (extender == null) ? BorderExtender.createInstance(1) : extender;
/*     */   }
/*     */   
/*     */   protected Rectangle backwardMapRect(Rectangle destRect, int sourceIndex) {
/* 106 */     Rectangle srcRect = super.backwardMapRect(destRect, sourceIndex);
/* 107 */     Rectangle paddedSrcRect = new Rectangle(srcRect.x - 1, srcRect.y - 1, srcRect.width + 2, srcRect.height + 2);
/* 112 */     return paddedSrcRect;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\mlib\MlibScaleOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */