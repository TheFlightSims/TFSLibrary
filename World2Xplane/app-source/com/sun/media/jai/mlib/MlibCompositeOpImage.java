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
/*    */ final class MlibCompositeOpImage extends PointOpImage {
/*    */   private RenderedImage alpha;
/*    */   
/*    */   public MlibCompositeOpImage(RenderedImage source1, RenderedImage source2, Map config, ImageLayout layout, RenderedImage alpha) {
/* 44 */     super(source1, source2, layout, config, true);
/* 46 */     this.alpha = alpha;
/*    */   }
/*    */   
/*    */   protected void computeRect(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/* 56 */     int i, formatTag = MediaLibAccessor.findCompatibleTag(sources, dest);
/* 58 */     MediaLibAccessor srcMA1 = new MediaLibAccessor(sources[0], destRect, formatTag);
/* 60 */     MediaLibAccessor srcMA2 = new MediaLibAccessor(sources[1], destRect, formatTag);
/* 62 */     MediaLibAccessor dstMA = new MediaLibAccessor(dest, destRect, formatTag);
/* 64 */     MediaLibAccessor alphaMA = new MediaLibAccessor(this.alpha.getData(destRect), destRect, formatTag);
/* 67 */     mediaLibImage[] srcMLI1 = srcMA1.getMediaLibImages();
/* 68 */     mediaLibImage[] srcMLI2 = srcMA2.getMediaLibImages();
/* 69 */     mediaLibImage[] dstMLI = dstMA.getMediaLibImages();
/* 70 */     mediaLibImage[] alphaMLI = alphaMA.getMediaLibImages();
/* 72 */     switch (dstMA.getDataType()) {
/*    */       case 0:
/*    */       case 1:
/*    */       case 2:
/*    */       case 3:
/* 77 */         for (i = 0; i < dstMLI.length; i++)
/* 78 */           Image.Blend(dstMLI[i], srcMLI1[i], srcMLI2[i], alphaMLI[0]); 
/*    */         break;
/*    */       case 4:
/*    */       case 5:
/* 86 */         for (i = 0; i < dstMLI.length; i++)
/* 87 */           Image.Blend_Fp(dstMLI[i], srcMLI1[i], srcMLI2[i], alphaMLI[0]); 
/*    */         break;
/*    */       default:
/* 94 */         throw new RuntimeException(JaiI18N.getString("Generic2"));
/*    */     } 
/* 97 */     if (dstMA.isDataCopy()) {
/* 98 */       dstMA.clampDataArrays();
/* 99 */       dstMA.copyDataToRaster();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\mlib\MlibCompositeOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */