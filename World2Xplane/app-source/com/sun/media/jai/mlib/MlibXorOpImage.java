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
/*    */ final class MlibXorOpImage extends PointOpImage {
/*    */   public MlibXorOpImage(RenderedImage source1, RenderedImage source2, Map config, ImageLayout layout) {
/* 45 */     super(source1, source2, layout, config, true);
/* 47 */     permitInPlaceOperation();
/*    */   }
/*    */   
/*    */   protected void computeRect(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/*    */     mediaLibImage[] srcML1, srcML2, dstML;
/*    */     int i;
/*    */     String className;
/* 63 */     int formatTag = MediaLibAccessor.findCompatibleTag(sources, dest);
/* 65 */     MediaLibAccessor srcAccessor1 = new MediaLibAccessor(sources[0], destRect, formatTag);
/* 67 */     MediaLibAccessor srcAccessor2 = new MediaLibAccessor(sources[1], destRect, formatTag);
/* 69 */     MediaLibAccessor dstAccessor = new MediaLibAccessor(dest, destRect, formatTag);
/* 72 */     switch (dstAccessor.getDataType()) {
/*    */       case 0:
/*    */       case 1:
/*    */       case 2:
/*    */       case 3:
/* 77 */         srcML1 = srcAccessor1.getMediaLibImages();
/* 78 */         srcML2 = srcAccessor2.getMediaLibImages();
/* 79 */         dstML = dstAccessor.getMediaLibImages();
/* 80 */         for (i = 0; i < dstML.length; i++)
/* 81 */           Image.Xor(dstML[i], srcML1[i], srcML2[i]); 
/*    */         break;
/*    */       default:
/* 85 */         className = getClass().getName();
/* 86 */         throw new RuntimeException(className + JaiI18N.getString("Generic2"));
/*    */     } 
/* 89 */     if (dstAccessor.isDataCopy()) {
/* 90 */       dstAccessor.clampDataArrays();
/* 91 */       dstAccessor.copyDataToRaster();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\mlib\MlibXorOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */