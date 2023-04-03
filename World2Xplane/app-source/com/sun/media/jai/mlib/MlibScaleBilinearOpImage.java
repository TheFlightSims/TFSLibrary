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
/*     */ final class MlibScaleBilinearOpImage extends MlibScaleOpImage {
/*     */   public MlibScaleBilinearOpImage(RenderedImage source, BorderExtender extender, Map config, ImageLayout layout, float xScale, float yScale, float xTrans, float yTrans, Interpolation interp) {
/*  55 */     super(source, extender, config, layout, xScale, yScale, xTrans, yTrans, interp, true);
/*     */   }
/*     */   
/*     */   protected void computeRect(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/*     */     mediaLibImage[] srcML, dstML;
/*     */     int i;
/*     */     String className;
/*  72 */     Raster source = sources[0];
/*  73 */     Rectangle srcRect = source.getBounds();
/*  75 */     int formatTag = MediaLibAccessor.findCompatibleTag(sources, dest);
/*  77 */     MediaLibAccessor srcAccessor = new MediaLibAccessor(source, srcRect, formatTag);
/*  79 */     MediaLibAccessor dstAccessor = new MediaLibAccessor(dest, destRect, formatTag);
/*  83 */     double mlibScaleX = this.scaleXRationalNum / this.scaleXRationalDenom;
/*  85 */     double mlibScaleY = this.scaleYRationalNum / this.scaleYRationalDenom;
/*  94 */     long tempDenomX = this.scaleXRationalDenom * this.transXRationalDenom;
/*  95 */     long tempDenomY = this.scaleYRationalDenom * this.transYRationalDenom;
/*  96 */     long tempNumerX = srcRect.x * this.scaleXRationalNum * this.transXRationalDenom + this.transXRationalNum * this.scaleXRationalDenom - destRect.x * tempDenomX;
/* 100 */     long tempNumerY = srcRect.y * this.scaleYRationalNum * this.transYRationalDenom + this.transYRationalNum * this.scaleYRationalDenom - destRect.y * tempDenomY;
/* 105 */     double tx = tempNumerX / tempDenomX;
/* 106 */     double ty = tempNumerY / tempDenomY;
/* 110 */     switch (dstAccessor.getDataType()) {
/*     */       case 0:
/*     */       case 1:
/*     */       case 2:
/*     */       case 3:
/* 115 */         srcML = srcAccessor.getMediaLibImages();
/* 116 */         dstML = dstAccessor.getMediaLibImages();
/* 117 */         for (i = 0; i < dstML.length; i++) {
/* 118 */           Image.ZoomTranslate(dstML[i], srcML[i], mlibScaleX, mlibScaleY, tx, ty, 1, 0);
/* 124 */           MlibUtils.clampImage(dstML[i], getColorModel());
/*     */         } 
/*     */         break;
/*     */       case 4:
/*     */       case 5:
/* 130 */         srcML = srcAccessor.getMediaLibImages();
/* 131 */         dstML = dstAccessor.getMediaLibImages();
/* 132 */         for (i = 0; i < dstML.length; i++)
/* 133 */           Image.ZoomTranslate_Fp(dstML[i], srcML[i], mlibScaleX, mlibScaleY, tx, ty, 1, 0); 
/*     */         break;
/*     */       default:
/* 143 */         className = getClass().getName();
/* 144 */         throw new RuntimeException(JaiI18N.getString("Generic2"));
/*     */     } 
/* 147 */     if (dstAccessor.isDataCopy()) {
/* 148 */       dstAccessor.clampDataArrays();
/* 149 */       dstAccessor.copyDataToRaster();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\mlib\MlibScaleBilinearOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */