/*     */ package com.sun.media.jai.mlib;
/*     */ 
/*     */ import com.sun.medialib.mlib.Image;
/*     */ import com.sun.medialib.mlib.mediaLibImage;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.util.Map;
/*     */ import javax.media.jai.ImageLayout;
/*     */ import javax.media.jai.PointOpImage;
/*     */ 
/*     */ final class MlibMultiplyOpImage extends PointOpImage {
/*     */   public MlibMultiplyOpImage(RenderedImage source1, RenderedImage source2, Map config, ImageLayout layout) {
/*  46 */     super(source1, source2, layout, config, true);
/*  48 */     permitInPlaceOperation();
/*     */   }
/*     */   
/*     */   protected void computeRect(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/*     */     int i;
/*     */     String className;
/*  64 */     int formatTag = MediaLibAccessor.findCompatibleTag(sources, dest);
/*  66 */     MediaLibAccessor srcAccessor1 = new MediaLibAccessor(sources[0], destRect, formatTag);
/*  68 */     MediaLibAccessor srcAccessor2 = new MediaLibAccessor(sources[1], destRect, formatTag);
/*  70 */     MediaLibAccessor dstAccessor = new MediaLibAccessor(dest, destRect, formatTag);
/*  73 */     mediaLibImage[] srcML1 = srcAccessor1.getMediaLibImages();
/*  74 */     mediaLibImage[] srcML2 = srcAccessor2.getMediaLibImages();
/*  75 */     mediaLibImage[] dstML = dstAccessor.getMediaLibImages();
/*  77 */     switch (dstAccessor.getDataType()) {
/*     */       case 0:
/*     */       case 1:
/*     */       case 2:
/*     */       case 3:
/*  82 */         for (i = 0; i < dstML.length; i++)
/*  83 */           Image.MulShift(dstML[i], srcML1[i], srcML2[i], 0); 
/*     */         break;
/*     */       case 4:
/*     */       case 5:
/*  89 */         for (i = 0; i < dstML.length; i++)
/*  90 */           Image.Mul_Fp(dstML[i], srcML1[i], srcML2[i]); 
/*     */         break;
/*     */       default:
/*  95 */         className = getClass().getName();
/*  96 */         throw new RuntimeException(className + JaiI18N.getString("Generic2"));
/*     */     } 
/*  99 */     if (dstAccessor.isDataCopy()) {
/* 100 */       dstAccessor.clampDataArrays();
/* 101 */       dstAccessor.copyDataToRaster();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\mlib\MlibMultiplyOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */