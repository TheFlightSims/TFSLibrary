/*     */ package com.sun.media.jai.mlib;
/*     */ 
/*     */ import com.sun.media.jai.opimage.SubsampleAverageOpImage;
/*     */ import com.sun.medialib.mlib.Image;
/*     */ import com.sun.medialib.mlib.mediaLibImage;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.util.Map;
/*     */ import javax.media.jai.ImageLayout;
/*     */ 
/*     */ public class MlibSubsampleAverageOpImage extends SubsampleAverageOpImage {
/*     */   public MlibSubsampleAverageOpImage(RenderedImage source, ImageLayout layout, Map config, double scaleX, double scaleY) {
/*  66 */     super(source, layout, config, scaleX, scaleY);
/*     */   }
/*     */   
/*     */   protected void computeRect(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/*     */     mediaLibImage[] srcML, dstML;
/*  76 */     Raster source = sources[0];
/*  77 */     Rectangle srcRect = source.getBounds();
/*  79 */     int formatTag = MediaLibAccessor.findCompatibleTag(sources, dest);
/*  81 */     MediaLibAccessor srcAccessor = new MediaLibAccessor(source, srcRect, formatTag);
/*  83 */     MediaLibAccessor dstAccessor = new MediaLibAccessor(dest, destRect, formatTag);
/*  88 */     switch (dstAccessor.getDataType()) {
/*     */       case 0:
/*     */       case 1:
/*     */       case 2:
/*     */       case 3:
/*  93 */         srcML = srcAccessor.getMediaLibImages();
/*  94 */         dstML = dstAccessor.getMediaLibImages();
/*  96 */         Image.SubsampleAverage(dstML[0], srcML[0], this.scaleX, this.scaleY);
/*     */         break;
/*     */       case 4:
/*     */       case 5:
/* 104 */         srcML = srcAccessor.getMediaLibImages();
/* 105 */         dstML = dstAccessor.getMediaLibImages();
/* 107 */         Image.SubsampleAverage_Fp(dstML[0], srcML[0], this.scaleX, this.scaleY);
/*     */         break;
/*     */     } 
/* 117 */     if (dstAccessor.isDataCopy())
/* 118 */       dstAccessor.copyDataToRaster(); 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\mlib\MlibSubsampleAverageOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */