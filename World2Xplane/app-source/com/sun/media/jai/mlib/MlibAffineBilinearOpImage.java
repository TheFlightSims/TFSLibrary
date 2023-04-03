/*     */ package com.sun.media.jai.mlib;
/*     */ 
/*     */ import com.sun.medialib.mlib.Image;
/*     */ import com.sun.medialib.mlib.mediaLibImage;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.util.Map;
/*     */ import javax.media.jai.BorderExtender;
/*     */ import javax.media.jai.ImageLayout;
/*     */ import javax.media.jai.Interpolation;
/*     */ 
/*     */ public class MlibAffineBilinearOpImage extends MlibAffineOpImage {
/*     */   public MlibAffineBilinearOpImage(RenderedImage source, BorderExtender extender, Map config, ImageLayout layout, AffineTransform tr, Interpolation interp, double[] backgroundValues) {
/*  62 */     super(source, layout, config, extender, tr, interp, backgroundValues);
/*     */   }
/*     */   
/*     */   protected void computeRect(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/*     */     mediaLibImage[] srcML, dstML;
/*     */     String className;
/*  84 */     Raster source = sources[0];
/*  85 */     Rectangle srcRect = source.getBounds();
/*  87 */     int formatTag = MediaLibAccessor.findCompatibleTag(sources, dest);
/*  89 */     MediaLibAccessor srcAccessor = new MediaLibAccessor(source, srcRect, formatTag);
/*  91 */     MediaLibAccessor dstAccessor = new MediaLibAccessor(dest, destRect, formatTag);
/* 101 */     double[] medialib_tr = (double[])this.medialib_tr.clone();
/* 103 */     medialib_tr[2] = this.m_transform[0] * srcRect.x + this.m_transform[1] * srcRect.y + this.m_transform[2] - destRect.x;
/* 107 */     medialib_tr[5] = this.m_transform[3] * srcRect.x + this.m_transform[4] * srcRect.y + this.m_transform[5] - destRect.y;
/* 114 */     switch (dstAccessor.getDataType()) {
/*     */       case 0:
/*     */       case 1:
/*     */       case 2:
/*     */       case 3:
/* 119 */         srcML = srcAccessor.getMediaLibImages();
/* 120 */         dstML = dstAccessor.getMediaLibImages();
/* 122 */         if (this.setBackground) {
/* 123 */           Image.Affine2(dstML[0], srcML[0], medialib_tr, 1, 0, this.intBackgroundValues);
/*     */         } else {
/* 130 */           Image.Affine(dstML[0], srcML[0], medialib_tr, 1, 0);
/*     */         } 
/* 135 */         MlibUtils.clampImage(dstML[0], getColorModel());
/*     */         break;
/*     */       case 4:
/*     */       case 5:
/* 140 */         srcML = srcAccessor.getMediaLibImages();
/* 141 */         dstML = dstAccessor.getMediaLibImages();
/* 143 */         if (this.setBackground) {
/* 144 */           Image.Affine2_Fp(dstML[0], srcML[0], medialib_tr, 1, 0, this.backgroundValues);
/*     */           break;
/*     */         } 
/* 151 */         Image.Affine_Fp(dstML[0], srcML[0], medialib_tr, 1, 0);
/*     */         break;
/*     */       default:
/* 159 */         className = getClass().getName();
/* 160 */         throw new RuntimeException(JaiI18N.getString("Generic2"));
/*     */     } 
/* 163 */     if (dstAccessor.isDataCopy())
/* 164 */       dstAccessor.copyDataToRaster(); 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\mlib\MlibAffineBilinearOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */