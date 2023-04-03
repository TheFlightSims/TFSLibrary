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
/*    */ final class MlibCopyOpImage extends PointOpImage {
/*    */   public MlibCopyOpImage(RenderedImage source, Map config, ImageLayout layout) {
/* 45 */     super(source, layout, config, true);
/*    */   }
/*    */   
/*    */   protected void computeRect(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/* 61 */     int formatTag = MediaLibAccessor.findCompatibleTag(sources, dest);
/* 63 */     MediaLibAccessor srcMA = new MediaLibAccessor(sources[0], destRect, formatTag);
/* 65 */     MediaLibAccessor dstMA = new MediaLibAccessor(dest, destRect, formatTag);
/* 68 */     mediaLibImage[] srcMLI = srcMA.getMediaLibImages();
/* 69 */     mediaLibImage[] dstMLI = dstMA.getMediaLibImages();
/* 71 */     for (int i = 0; i < dstMLI.length; i++)
/* 72 */       Image.Copy(dstMLI[i], srcMLI[i]); 
/* 75 */     if (dstMA.isDataCopy())
/* 76 */       dstMA.copyDataToRaster(); 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\mlib\MlibCopyOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */