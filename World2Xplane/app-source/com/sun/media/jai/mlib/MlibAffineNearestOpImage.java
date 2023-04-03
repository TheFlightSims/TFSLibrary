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
/*     */ public class MlibAffineNearestOpImage extends MlibAffineOpImage {
/*     */   public MlibAffineNearestOpImage(RenderedImage source, BorderExtender extender, Map config, ImageLayout layout, AffineTransform tr, Interpolation interp, double[] backgroundValues) {
/*  61 */     super(source, layout, config, extender, tr, interp, backgroundValues);
/*     */   }
/*     */   
/*     */   protected void computeRect(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/*     */     mediaLibImage[] srcML, dstML;
/*     */     String className;
/*  83 */     Raster source = sources[0];
/*  84 */     Rectangle srcRect = source.getBounds();
/*  86 */     int formatTag = MediaLibAccessor.findCompatibleTag(sources, dest);
/*  88 */     MediaLibAccessor srcAccessor = new MediaLibAccessor(source, srcRect, formatTag);
/*  90 */     MediaLibAccessor dstAccessor = new MediaLibAccessor(dest, destRect, formatTag);
/* 100 */     double[] medialib_tr = (double[])this.medialib_tr.clone();
/* 102 */     medialib_tr[2] = this.m_transform[0] * srcRect.x + this.m_transform[1] * srcRect.y + this.m_transform[2] - destRect.x;
/* 106 */     medialib_tr[5] = this.m_transform[3] * srcRect.x + this.m_transform[4] * srcRect.y + this.m_transform[5] - destRect.y;
/* 113 */     switch (dstAccessor.getDataType()) {
/*     */       case 0:
/*     */       case 1:
/*     */       case 2:
/*     */       case 3:
/* 118 */         srcML = srcAccessor.getMediaLibImages();
/* 119 */         dstML = dstAccessor.getMediaLibImages();
/* 121 */         if (this.setBackground) {
/* 122 */           Image.Affine2(dstML[0], srcML[0], medialib_tr, 0, 0, this.intBackgroundValues);
/*     */           break;
/*     */         } 
/* 129 */         Image.Affine(dstML[0], srcML[0], medialib_tr, 0, 0);
/*     */         break;
/*     */       case 4:
/*     */       case 5:
/* 138 */         srcML = srcAccessor.getMediaLibImages();
/* 139 */         dstML = dstAccessor.getMediaLibImages();
/* 141 */         if (this.setBackground) {
/* 142 */           Image.Affine2_Fp(dstML[0], srcML[0], medialib_tr, 0, 0, this.backgroundValues);
/*     */           break;
/*     */         } 
/* 149 */         Image.Affine_Fp(dstML[0], srcML[0], medialib_tr, 0, 0);
/*     */         break;
/*     */       default:
/* 157 */         className = getClass().getName();
/* 158 */         throw new RuntimeException(JaiI18N.getString("Generic2"));
/*     */     } 
/* 161 */     if (dstAccessor.isDataCopy())
/* 162 */       dstAccessor.copyDataToRaster(); 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\mlib\MlibAffineNearestOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */