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
/*    */ final class MlibMaxOpImage extends PointOpImage {
/*    */   public MlibMaxOpImage(RenderedImage source1, RenderedImage source2, Map config, ImageLayout layout) {
/* 40 */     super(source1, source2, layout, config, true);
/* 42 */     permitInPlaceOperation();
/*    */   }
/*    */   
/*    */   protected void computeRect(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/* 52 */     int i, formatTag = MediaLibAccessor.findCompatibleTag(sources, dest);
/* 54 */     MediaLibAccessor srcMA1 = new MediaLibAccessor(sources[0], destRect, formatTag);
/* 56 */     MediaLibAccessor srcMA2 = new MediaLibAccessor(sources[1], destRect, formatTag);
/* 58 */     MediaLibAccessor dstMA = new MediaLibAccessor(dest, destRect, formatTag);
/* 61 */     mediaLibImage[] srcMLI1 = srcMA1.getMediaLibImages();
/* 62 */     mediaLibImage[] srcMLI2 = srcMA2.getMediaLibImages();
/* 63 */     mediaLibImage[] dstMLI = dstMA.getMediaLibImages();
/* 65 */     switch (dstMA.getDataType()) {
/*    */       case 0:
/*    */       case 1:
/*    */       case 2:
/*    */       case 3:
/* 70 */         for (i = 0; i < dstMLI.length; i++)
/* 71 */           Image.Max(dstMLI[i], srcMLI1[i], srcMLI2[i]); 
/*    */         break;
/*    */       case 4:
/*    */       case 5:
/* 77 */         for (i = 0; i < dstMLI.length; i++)
/* 78 */           Image.Max_Fp(dstMLI[i], srcMLI1[i], srcMLI2[i]); 
/*    */         break;
/*    */     } 
/* 84 */     if (dstMA.isDataCopy()) {
/* 85 */       dstMA.clampDataArrays();
/* 86 */       dstMA.copyDataToRaster();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\mlib\MlibMaxOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */