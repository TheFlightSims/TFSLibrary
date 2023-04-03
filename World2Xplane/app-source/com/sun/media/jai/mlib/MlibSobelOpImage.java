/*     */ package com.sun.media.jai.mlib;
/*     */ 
/*     */ import com.sun.medialib.mlib.Image;
/*     */ import com.sun.medialib.mlib.mediaLibImage;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.util.Map;
/*     */ import javax.media.jai.AreaOpImage;
/*     */ import javax.media.jai.BorderExtender;
/*     */ import javax.media.jai.ImageLayout;
/*     */ import javax.media.jai.KernelJAI;
/*     */ 
/*     */ final class MlibSobelOpImage extends AreaOpImage {
/*     */   public MlibSobelOpImage(RenderedImage source, BorderExtender extender, Map config, ImageLayout layout, KernelJAI kernel) {
/*  60 */     super(source, layout, config, true, extender, kernel.getLeftPadding(), kernel.getRightPadding(), kernel.getTopPadding(), kernel.getBottomPadding());
/*     */   }
/*     */   
/*     */   protected void computeRect(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/*  82 */     Raster source = sources[0];
/*  83 */     Rectangle srcRect = mapDestRect(destRect, 0);
/*  85 */     int formatTag = MediaLibAccessor.findCompatibleTag(sources, dest);
/*  87 */     MediaLibAccessor srcAccessor = new MediaLibAccessor(source, srcRect, formatTag);
/*  89 */     MediaLibAccessor dstAccessor = new MediaLibAccessor(dest, destRect, formatTag);
/*  91 */     int numBands = getSampleModel().getNumBands();
/*  93 */     mediaLibImage[] srcML = srcAccessor.getMediaLibImages();
/*  94 */     mediaLibImage[] dstML = dstAccessor.getMediaLibImages();
/*  95 */     for (int i = 0; i < dstML.length; i++) {
/*     */       String className;
/*  96 */       switch (dstAccessor.getDataType()) {
/*     */         case 0:
/*     */         case 1:
/*     */         case 2:
/*     */         case 3:
/* 101 */           Image.Sobel(dstML[i], srcML[i], (1 << numBands) - 1, 0);
/*     */           break;
/*     */         case 4:
/*     */         case 5:
/* 109 */           Image.Sobel_Fp(dstML[i], srcML[i], (1 << numBands) - 1, 0);
/*     */           break;
/*     */         default:
/* 116 */           className = getClass().getName();
/* 117 */           throw new RuntimeException(JaiI18N.getString("Generic2"));
/*     */       } 
/*     */     } 
/* 121 */     if (dstAccessor.isDataCopy())
/* 122 */       dstAccessor.copyDataToRaster(); 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\mlib\MlibSobelOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */