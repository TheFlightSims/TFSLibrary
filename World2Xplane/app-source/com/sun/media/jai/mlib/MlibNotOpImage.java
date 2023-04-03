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
/*    */ final class MlibNotOpImage extends PointOpImage {
/*    */   public MlibNotOpImage(RenderedImage source, Map config, ImageLayout layout) {
/* 45 */     super(source, layout, config, true);
/* 47 */     permitInPlaceOperation();
/*    */   }
/*    */   
/*    */   protected void computeRect(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/*    */     mediaLibImage[] srcML, dstML;
/*    */     int i;
/*    */     String className;
/* 63 */     int formatTag = MediaLibAccessor.findCompatibleTag(sources, dest);
/* 65 */     MediaLibAccessor srcAccessor = new MediaLibAccessor(sources[0], destRect, formatTag);
/* 67 */     MediaLibAccessor dstAccessor = new MediaLibAccessor(dest, destRect, formatTag);
/* 70 */     switch (dstAccessor.getDataType()) {
/*    */       case 0:
/*    */       case 1:
/*    */       case 2:
/*    */       case 3:
/* 75 */         srcML = srcAccessor.getMediaLibImages();
/* 76 */         dstML = dstAccessor.getMediaLibImages();
/* 77 */         for (i = 0; i < dstML.length; i++)
/* 78 */           Image.Not(dstML[i], srcML[i]); 
/*    */         break;
/*    */       default:
/* 82 */         className = getClass().getName();
/* 83 */         throw new RuntimeException(className + JaiI18N.getString("Generic2"));
/*    */     } 
/* 86 */     if (dstAccessor.isDataCopy()) {
/* 87 */       dstAccessor.clampDataArrays();
/* 88 */       dstAccessor.copyDataToRaster();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\mlib\MlibNotOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */