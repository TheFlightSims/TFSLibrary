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
/*     */ final class MlibDivideOpImage extends PointOpImage {
/*     */   public MlibDivideOpImage(RenderedImage source1, RenderedImage source2, Map config, ImageLayout layout) {
/*  45 */     super(source1, source2, layout, config, true);
/*  47 */     permitInPlaceOperation();
/*     */   }
/*     */   
/*     */   protected void computeRect(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/*     */     int i;
/*     */     String className;
/*  63 */     int formatTag = MediaLibAccessor.findCompatibleTag(sources, dest);
/*  65 */     MediaLibAccessor srcAccessor1 = new MediaLibAccessor(sources[0], destRect, formatTag);
/*  67 */     MediaLibAccessor srcAccessor2 = new MediaLibAccessor(sources[1], destRect, formatTag);
/*  69 */     MediaLibAccessor dstAccessor = new MediaLibAccessor(dest, destRect, formatTag);
/*  72 */     mediaLibImage[] srcML1 = srcAccessor1.getMediaLibImages();
/*  73 */     mediaLibImage[] srcML2 = srcAccessor2.getMediaLibImages();
/*  74 */     mediaLibImage[] dstML = dstAccessor.getMediaLibImages();
/*  76 */     switch (dstAccessor.getDataType()) {
/*     */       case 0:
/*     */       case 1:
/*     */       case 2:
/*     */       case 3:
/*  81 */         for (i = 0; i < dstML.length; i++)
/*  82 */           Image.DivShift(dstML[i], srcML1[i], srcML2[i], 0); 
/*     */         break;
/*     */       case 4:
/*     */       case 5:
/*  88 */         for (i = 0; i < dstML.length; i++)
/*  89 */           Image.Div_Fp(dstML[i], srcML1[i], srcML2[i]); 
/*     */         break;
/*     */       default:
/*  94 */         className = getClass().getName();
/*  95 */         throw new RuntimeException(className + JaiI18N.getString("Generic2"));
/*     */     } 
/*  98 */     if (dstAccessor.isDataCopy()) {
/*  99 */       dstAccessor.clampDataArrays();
/* 100 */       dstAccessor.copyDataToRaster();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\mlib\MlibDivideOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */