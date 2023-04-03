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
/*    */ final class MlibInvertOpImage extends PointOpImage {
/*    */   public MlibInvertOpImage(RenderedImage source, Map config, ImageLayout layout) {
/* 39 */     super(source, layout, config, true);
/* 41 */     permitInPlaceOperation();
/*    */   }
/*    */   
/*    */   protected void computeRect(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/* 51 */     int i, formatTag = MediaLibAccessor.findCompatibleTag(sources, dest);
/* 53 */     MediaLibAccessor srcMA = new MediaLibAccessor(sources[0], destRect, formatTag);
/* 55 */     MediaLibAccessor dstMA = new MediaLibAccessor(dest, destRect, formatTag);
/* 58 */     mediaLibImage[] srcMLI = srcMA.getMediaLibImages();
/* 59 */     mediaLibImage[] dstMLI = dstMA.getMediaLibImages();
/* 61 */     switch (dstMA.getDataType()) {
/*    */       case 0:
/*    */       case 1:
/*    */       case 2:
/*    */       case 3:
/* 66 */         for (i = 0; i < dstMLI.length; i++)
/* 67 */           Image.Invert(dstMLI[i], srcMLI[i]); 
/*    */         break;
/*    */       case 4:
/*    */       case 5:
/* 73 */         for (i = 0; i < dstMLI.length; i++)
/* 74 */           Image.Invert_Fp(dstMLI[i], srcMLI[i]); 
/*    */         break;
/*    */       default:
/* 79 */         throw new RuntimeException(JaiI18N.getString("Generic2"));
/*    */     } 
/* 82 */     if (dstMA.isDataCopy()) {
/* 83 */       dstMA.clampDataArrays();
/* 84 */       dstMA.copyDataToRaster();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\mlib\MlibInvertOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */