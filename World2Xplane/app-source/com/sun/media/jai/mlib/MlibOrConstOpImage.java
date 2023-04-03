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
/*    */ final class MlibOrConstOpImage extends PointOpImage {
/*    */   int[] constants;
/*    */   
/*    */   public MlibOrConstOpImage(RenderedImage source, Map config, ImageLayout layout, int[] constants) {
/* 45 */     super(source, layout, config, true);
/* 46 */     this.constants = MlibUtils.initConstants(constants, getSampleModel().getNumBands());
/* 49 */     permitInPlaceOperation();
/*    */   }
/*    */   
/*    */   protected void computeRect(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/*    */     mediaLibImage[] srcML, dstML;
/*    */     int i;
/*    */     String className;
/* 64 */     Raster source = sources[0];
/* 65 */     int formatTag = MediaLibAccessor.findCompatibleTag(sources, dest);
/* 68 */     MediaLibAccessor srcAccessor = new MediaLibAccessor(source, destRect, formatTag);
/* 70 */     MediaLibAccessor dstAccessor = new MediaLibAccessor(dest, destRect, formatTag);
/* 73 */     switch (dstAccessor.getDataType()) {
/*    */       case 0:
/*    */       case 1:
/*    */       case 2:
/*    */       case 3:
/* 78 */         srcML = srcAccessor.getMediaLibImages();
/* 79 */         dstML = dstAccessor.getMediaLibImages();
/* 80 */         for (i = 0; i < dstML.length; i++) {
/* 81 */           int[] mlconstants = dstAccessor.getIntParameters(i, this.constants);
/* 82 */           Image.ConstOr(dstML[i], srcML[i], mlconstants);
/*    */         } 
/*    */         break;
/*    */       default:
/* 86 */         className = getClass().getName();
/* 87 */         throw new RuntimeException(className + JaiI18N.getString("Generic2"));
/*    */     } 
/* 90 */     if (dstAccessor.isDataCopy()) {
/* 91 */       dstAccessor.clampDataArrays();
/* 92 */       dstAccessor.copyDataToRaster();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\mlib\MlibOrConstOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */