/*     */ package com.sun.media.jai.mlib;
/*     */ 
/*     */ import com.sun.medialib.mlib.Image;
/*     */ import com.sun.medialib.mlib.mediaLibImage;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.util.Map;
/*     */ import javax.media.jai.BorderExtender;
/*     */ import javax.media.jai.ImageLayout;
/*     */ import javax.media.jai.Interpolation;
/*     */ 
/*     */ final class MlibScaleNearestOpImage extends MlibScaleOpImage {
/*     */   public MlibScaleNearestOpImage(RenderedImage source, BorderExtender extender, Map config, ImageLayout layout, float xScale, float yScale, float xTrans, float yTrans, Interpolation interp) {
/*  56 */     super(source, extender, config, layout, xScale, yScale, xTrans, yTrans, interp, true);
/*     */   }
/*     */   
/*     */   protected void computeRect(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/*     */     mediaLibImage[] srcML, dstML;
/*     */     int i;
/*     */     String className;
/*  73 */     Raster source = sources[0];
/*  74 */     Rectangle srcRect = source.getBounds();
/*  76 */     int formatTag = MediaLibAccessor.findCompatibleTag(sources, dest);
/*  78 */     MediaLibAccessor srcAccessor = new MediaLibAccessor(source, srcRect, formatTag);
/*  80 */     MediaLibAccessor dstAccessor = new MediaLibAccessor(dest, destRect, formatTag);
/*  84 */     float mlibScaleX = this.scaleX;
/*  85 */     float mlibScaleY = this.scaleY;
/*  93 */     long tempDenomX = this.scaleXRationalDenom * this.transXRationalDenom;
/*  94 */     long tempDenomY = this.scaleYRationalDenom * this.transYRationalDenom;
/*  95 */     long tempNumerX = srcRect.x * this.scaleXRationalNum * this.transXRationalDenom + this.transXRationalNum * this.scaleXRationalDenom - destRect.x * tempDenomX;
/*  98 */     long tempNumerY = srcRect.y * this.scaleYRationalNum * this.transYRationalDenom + this.transYRationalNum * this.scaleYRationalDenom - destRect.y * tempDenomY;
/* 101 */     float tx = (float)tempNumerX / (float)tempDenomX;
/* 102 */     float ty = (float)tempNumerY / (float)tempDenomY;
/* 106 */     switch (dstAccessor.getDataType()) {
/*     */       case 0:
/*     */       case 1:
/*     */       case 2:
/*     */       case 3:
/* 111 */         srcML = srcAccessor.getMediaLibImages();
/* 112 */         dstML = dstAccessor.getMediaLibImages();
/* 113 */         for (i = 0; i < dstML.length; i++)
/* 114 */           Image.ZoomTranslate(dstML[i], srcML[i], mlibScaleX, mlibScaleY, tx, ty, 0, 0); 
/*     */         break;
/*     */       case 4:
/*     */       case 5:
/* 125 */         srcML = srcAccessor.getMediaLibImages();
/* 126 */         dstML = dstAccessor.getMediaLibImages();
/* 127 */         for (i = 0; i < dstML.length; i++)
/* 128 */           Image.ZoomTranslate_Fp(dstML[i], srcML[i], mlibScaleX, mlibScaleY, tx, ty, 0, 0); 
/*     */         break;
/*     */       default:
/* 138 */         className = getClass().getName();
/* 139 */         throw new RuntimeException(JaiI18N.getString("Generic2"));
/*     */     } 
/* 142 */     if (dstAccessor.isDataCopy()) {
/* 143 */       dstAccessor.clampDataArrays();
/* 144 */       dstAccessor.copyDataToRaster();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\mlib\MlibScaleNearestOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */