/*    */ package com.sun.media.jai.mlib;
/*    */ 
/*    */ import com.sun.medialib.mlib.Image;
/*    */ import com.sun.medialib.mlib.mediaLibImage;
/*    */ import java.awt.Rectangle;
/*    */ import java.awt.image.Raster;
/*    */ import java.awt.image.RenderedImage;
/*    */ import java.awt.image.WritableRaster;
/*    */ import java.util.Map;
/*    */ import javax.media.jai.ImageLayout;
/*    */ import javax.media.jai.PointOpImage;
/*    */ 
/*    */ final class MlibLogOpImage extends PointOpImage {
/*    */   public MlibLogOpImage(RenderedImage source, Map config, ImageLayout layout) {
/* 43 */     super(source, layout, config, true);
/* 45 */     permitInPlaceOperation();
/*    */   }
/*    */   
/*    */   protected void computeRect(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/*    */     int i;
/*    */     String className;
/* 60 */     Raster source = sources[0];
/* 61 */     Rectangle srcRect = mapDestRect(destRect, 0);
/* 63 */     int formatTag = MediaLibAccessor.findCompatibleTag(sources, dest);
/* 65 */     MediaLibAccessor srcAccessor = new MediaLibAccessor(source, srcRect, formatTag);
/* 67 */     MediaLibAccessor dstAccessor = new MediaLibAccessor(dest, destRect, formatTag);
/* 70 */     mediaLibImage[] srcML = srcAccessor.getMediaLibImages();
/* 71 */     mediaLibImage[] dstML = dstAccessor.getMediaLibImages();
/* 73 */     switch (dstAccessor.getDataType()) {
/*    */       case 0:
/*    */       case 1:
/*    */       case 2:
/*    */       case 3:
/* 78 */         for (i = 0; i < dstML.length; i++)
/* 79 */           Image.Log(dstML[i], srcML[i]); 
/*    */         break;
/*    */       case 4:
/*    */       case 5:
/* 85 */         for (i = 0; i < dstML.length; i++)
/* 86 */           Image.Log_Fp(dstML[i], srcML[i]); 
/*    */         break;
/*    */       default:
/* 91 */         className = getClass().getName();
/* 92 */         throw new RuntimeException(className + JaiI18N.getString("Generic2"));
/*    */     } 
/* 95 */     if (dstAccessor.isDataCopy()) {
/* 96 */       dstAccessor.clampDataArrays();
/* 97 */       dstAccessor.copyDataToRaster();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\mlib\MlibLogOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */