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
/*     */ final class MlibScaleBicubicOpImage extends MlibScaleOpImage {
/*     */   public MlibScaleBicubicOpImage(RenderedImage source, BorderExtender extender, Map config, ImageLayout layout, float xScale, float yScale, float xTrans, float yTrans, Interpolation interp) {
/*  57 */     super(source, extender, config, layout, xScale, yScale, xTrans, yTrans, interp, true);
/*     */   }
/*     */   
/*     */   protected void computeRect(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/*     */     mediaLibImage[] srcML, dstML;
/*     */     int i;
/*     */     String className;
/*  75 */     int mlibInterpType = 2;
/*  78 */     if (this.interp instanceof javax.media.jai.InterpolationBicubic2)
/*  79 */       mlibInterpType = 3; 
/*  82 */     Raster source = sources[0];
/*  83 */     Rectangle srcRect = source.getBounds();
/*  85 */     int formatTag = MediaLibAccessor.findCompatibleTag(sources, dest);
/*  87 */     MediaLibAccessor srcAccessor = new MediaLibAccessor(source, srcRect, formatTag);
/*  89 */     MediaLibAccessor dstAccessor = new MediaLibAccessor(dest, destRect, formatTag);
/*  93 */     double mlibScaleX = this.scaleXRationalNum / this.scaleXRationalDenom;
/*  95 */     double mlibScaleY = this.scaleYRationalNum / this.scaleYRationalDenom;
/* 104 */     long tempDenomX = this.scaleXRationalDenom * this.transXRationalDenom;
/* 105 */     long tempDenomY = this.scaleYRationalDenom * this.transYRationalDenom;
/* 106 */     long tempNumerX = srcRect.x * this.scaleXRationalNum * this.transXRationalDenom + this.transXRationalNum * this.scaleXRationalDenom - destRect.x * tempDenomX;
/* 110 */     long tempNumerY = srcRect.y * this.scaleYRationalNum * this.transYRationalDenom + this.transYRationalNum * this.scaleYRationalDenom - destRect.y * tempDenomY;
/* 115 */     double tx = tempNumerX / tempDenomX;
/* 116 */     double ty = tempNumerY / tempDenomY;
/* 120 */     switch (dstAccessor.getDataType()) {
/*     */       case 0:
/*     */       case 1:
/*     */       case 2:
/*     */       case 3:
/* 125 */         srcML = srcAccessor.getMediaLibImages();
/* 126 */         dstML = dstAccessor.getMediaLibImages();
/* 127 */         for (i = 0; i < dstML.length; i++) {
/* 128 */           Image.ZoomTranslate(dstML[i], srcML[i], mlibScaleX, mlibScaleY, tx, ty, mlibInterpType, 0);
/* 134 */           MlibUtils.clampImage(dstML[i], getColorModel());
/*     */         } 
/*     */         break;
/*     */       case 4:
/*     */       case 5:
/* 140 */         srcML = srcAccessor.getMediaLibImages();
/* 141 */         dstML = dstAccessor.getMediaLibImages();
/* 142 */         for (i = 0; i < dstML.length; i++)
/* 143 */           Image.ZoomTranslate_Fp(dstML[i], srcML[i], mlibScaleX, mlibScaleY, tx, ty, mlibInterpType, 0); 
/*     */         break;
/*     */       default:
/* 153 */         className = getClass().getName();
/* 154 */         throw new RuntimeException(JaiI18N.getString("Generic2"));
/*     */     } 
/* 157 */     if (dstAccessor.isDataCopy()) {
/* 158 */       dstAccessor.clampDataArrays();
/* 159 */       dstAccessor.copyDataToRaster();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\mlib\MlibScaleBicubicOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */