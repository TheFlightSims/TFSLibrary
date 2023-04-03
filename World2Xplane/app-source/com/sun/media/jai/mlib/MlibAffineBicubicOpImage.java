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
/*     */ public class MlibAffineBicubicOpImage extends MlibAffineOpImage {
/*     */   public MlibAffineBicubicOpImage(RenderedImage source, BorderExtender extender, Map config, ImageLayout layout, AffineTransform tr, Interpolation interp, double[] backgroundValues) {
/*  64 */     super(source, layout, config, extender, tr, interp, backgroundValues);
/*     */   }
/*     */   
/*     */   protected void computeRect(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/*     */     mediaLibImage[] srcML, dstML;
/*     */     String className;
/*  87 */     int mlibInterpType = 2;
/*  90 */     if (this.interp instanceof javax.media.jai.InterpolationBicubic2)
/*  91 */       mlibInterpType = 3; 
/*  94 */     Raster source = sources[0];
/*  95 */     Rectangle srcRect = source.getBounds();
/*  97 */     int formatTag = MediaLibAccessor.findCompatibleTag(sources, dest);
/*  99 */     MediaLibAccessor srcAccessor = new MediaLibAccessor(source, srcRect, formatTag);
/* 101 */     MediaLibAccessor dstAccessor = new MediaLibAccessor(dest, destRect, formatTag);
/* 111 */     double[] medialib_tr = (double[])this.medialib_tr.clone();
/* 113 */     medialib_tr[2] = this.m_transform[0] * srcRect.x + this.m_transform[1] * srcRect.y + this.m_transform[2] - destRect.x;
/* 117 */     medialib_tr[5] = this.m_transform[3] * srcRect.x + this.m_transform[4] * srcRect.y + this.m_transform[5] - destRect.y;
/* 124 */     switch (dstAccessor.getDataType()) {
/*     */       case 0:
/*     */       case 1:
/*     */       case 2:
/*     */       case 3:
/* 129 */         srcML = srcAccessor.getMediaLibImages();
/* 130 */         dstML = dstAccessor.getMediaLibImages();
/* 131 */         if (this.setBackground) {
/* 132 */           Image.Affine2(dstML[0], srcML[0], medialib_tr, mlibInterpType, 0, this.intBackgroundValues);
/*     */         } else {
/* 139 */           Image.Affine(dstML[0], srcML[0], medialib_tr, mlibInterpType, 0);
/*     */         } 
/* 144 */         MlibUtils.clampImage(dstML[0], getColorModel());
/*     */         break;
/*     */       case 4:
/*     */       case 5:
/* 149 */         srcML = srcAccessor.getMediaLibImages();
/* 150 */         dstML = dstAccessor.getMediaLibImages();
/* 152 */         if (this.setBackground) {
/* 153 */           Image.Affine2_Fp(dstML[0], srcML[0], medialib_tr, mlibInterpType, 0, this.backgroundValues);
/*     */           break;
/*     */         } 
/* 160 */         Image.Affine_Fp(dstML[0], srcML[0], medialib_tr, mlibInterpType, 0);
/*     */         break;
/*     */       default:
/* 168 */         className = getClass().getName();
/* 169 */         throw new RuntimeException(JaiI18N.getString("Generic2"));
/*     */     } 
/* 172 */     if (dstAccessor.isDataCopy())
/* 173 */       dstAccessor.copyDataToRaster(); 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\mlib\MlibAffineBicubicOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */