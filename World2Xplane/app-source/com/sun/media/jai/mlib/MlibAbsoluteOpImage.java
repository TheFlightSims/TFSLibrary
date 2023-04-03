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
/*    */ final class MlibAbsoluteOpImage extends PointOpImage {
/*    */   public MlibAbsoluteOpImage(RenderedImage source1, Map config, ImageLayout layout) {
/* 46 */     super(source1, layout, config, true);
/* 48 */     permitInPlaceOperation();
/*    */   }
/*    */   
/*    */   protected void computeRect(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/*    */     int i;
/*    */     String className;
/* 64 */     int formatTag = MediaLibAccessor.findCompatibleTag(sources, dest);
/* 66 */     MediaLibAccessor srcAccessor = new MediaLibAccessor(sources[0], destRect, formatTag);
/* 68 */     MediaLibAccessor dstAccessor = new MediaLibAccessor(dest, destRect, formatTag);
/* 71 */     mediaLibImage[] srcML = srcAccessor.getMediaLibImages();
/* 72 */     mediaLibImage[] dstML = dstAccessor.getMediaLibImages();
/* 74 */     switch (dstAccessor.getDataType()) {
/*    */       case 0:
/*    */       case 1:
/*    */       case 2:
/*    */       case 3:
/* 79 */         for (i = 0; i < dstML.length; i++)
/* 80 */           Image.Abs(dstML[i], srcML[i]); 
/*    */         break;
/*    */       case 4:
/*    */       case 5:
/* 86 */         for (i = 0; i < dstML.length; i++)
/* 87 */           Image.Abs_Fp(dstML[i], srcML[i]); 
/*    */         break;
/*    */       default:
/* 92 */         className = getClass().getName();
/* 93 */         throw new RuntimeException(className + JaiI18N.getString("Generic2"));
/*    */     } 
/* 96 */     if (dstAccessor.isDataCopy()) {
/* 97 */       dstAccessor.clampDataArrays();
/* 98 */       dstAccessor.copyDataToRaster();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\mlib\MlibAbsoluteOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */