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
/*     */ final class MlibAddOpImage extends PointOpImage {
/*     */   private static ImageLayout layoutHelper(ImageLayout layout) {
/*  36 */     if (layout == null)
/*  37 */       return null; 
/*  39 */     return (ImageLayout)layout.clone();
/*     */   }
/*     */   
/*     */   public MlibAddOpImage(RenderedImage source1, RenderedImage source2, Map config, ImageLayout layout) {
/*  55 */     super(source1, source2, layoutHelper(layout), config, true);
/*  57 */     permitInPlaceOperation();
/*     */   }
/*     */   
/*     */   protected void computeRect(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/*     */     int i;
/*     */     String className;
/*  73 */     int formatTag = MediaLibAccessor.findCompatibleTag(sources, dest);
/*  75 */     MediaLibAccessor srcAccessor1 = new MediaLibAccessor(sources[0], destRect, formatTag);
/*  77 */     MediaLibAccessor srcAccessor2 = new MediaLibAccessor(sources[1], destRect, formatTag);
/*  79 */     MediaLibAccessor dstAccessor = new MediaLibAccessor(dest, destRect, formatTag);
/*  82 */     mediaLibImage[] srcML1 = srcAccessor1.getMediaLibImages();
/*  83 */     mediaLibImage[] srcML2 = srcAccessor2.getMediaLibImages();
/*  84 */     mediaLibImage[] dstML = dstAccessor.getMediaLibImages();
/*  86 */     switch (dstAccessor.getDataType()) {
/*     */       case 0:
/*     */       case 1:
/*     */       case 2:
/*     */       case 3:
/*  91 */         for (i = 0; i < dstML.length; i++)
/*  92 */           Image.Add(dstML[i], srcML1[i], srcML2[i]); 
/*     */         break;
/*     */       case 4:
/*     */       case 5:
/*  98 */         for (i = 0; i < dstML.length; i++)
/*  99 */           Image.Add_Fp(dstML[i], srcML1[i], srcML2[i]); 
/*     */         break;
/*     */       default:
/* 104 */         className = getClass().getName();
/* 105 */         throw new RuntimeException(className + JaiI18N.getString("Generic2"));
/*     */     } 
/* 108 */     if (dstAccessor.isDataCopy()) {
/* 109 */       dstAccessor.clampDataArrays();
/* 110 */       dstAccessor.copyDataToRaster();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\mlib\MlibAddOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */