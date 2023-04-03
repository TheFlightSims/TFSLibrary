/*     */ package com.sun.media.jai.mlib;
/*     */ 
/*     */ import com.sun.medialib.mlib.Image;
/*     */ import com.sun.medialib.mlib.mediaLibImage;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.util.Map;
/*     */ import javax.media.jai.ImageLayout;
/*     */ import javax.media.jai.PointOpImage;
/*     */ 
/*     */ final class MlibRescaleOpImage extends PointOpImage {
/*     */   private double[] constants;
/*     */   
/*     */   private double[] offsets;
/*     */   
/*     */   private static ImageLayout layoutHelper(ImageLayout layout) {
/*  37 */     if (layout == null)
/*  38 */       return null; 
/*  40 */     return (ImageLayout)layout.clone();
/*     */   }
/*     */   
/*     */   public MlibRescaleOpImage(RenderedImage source, Map config, ImageLayout layout, double[] constants, double[] offsets) {
/*  58 */     super(source, layoutHelper(layout), config, true);
/*  59 */     int numBands = getSampleModel().getNumBands();
/*  60 */     this.constants = MlibUtils.initConstants(constants, numBands);
/*  61 */     this.offsets = MlibUtils.initConstants(offsets, numBands);
/*  63 */     permitInPlaceOperation();
/*     */   }
/*     */   
/*     */   protected void computeRect(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/*     */     int i;
/*     */     String className;
/*  78 */     Raster source = sources[0];
/*  79 */     Rectangle srcRect = mapDestRect(destRect, 0);
/*  81 */     int formatTag = MediaLibAccessor.findCompatibleTag(sources, dest);
/*  83 */     MediaLibAccessor srcAccessor = new MediaLibAccessor(source, srcRect, formatTag);
/*  85 */     MediaLibAccessor dstAccessor = new MediaLibAccessor(dest, destRect, formatTag);
/*  88 */     mediaLibImage[] srcML = srcAccessor.getMediaLibImages();
/*  89 */     mediaLibImage[] dstML = dstAccessor.getMediaLibImages();
/*  91 */     switch (dstAccessor.getDataType()) {
/*     */       case 0:
/*     */       case 1:
/*     */       case 2:
/*     */       case 3:
/*  96 */         for (i = 0; i < dstML.length; i++) {
/*  97 */           double[] mlconstants = dstAccessor.getDoubleParameters(i, this.constants);
/*  98 */           double[] mloffsets = dstAccessor.getDoubleParameters(i, this.offsets);
/*  99 */           Image.Scale2(dstML[i], srcML[i], mlconstants, mloffsets);
/*     */         } 
/*     */         break;
/*     */       case 4:
/*     */       case 5:
/* 105 */         for (i = 0; i < dstML.length; i++) {
/* 106 */           double[] mlconstants = dstAccessor.getDoubleParameters(i, this.constants);
/* 107 */           double[] mloffsets = dstAccessor.getDoubleParameters(i, this.offsets);
/* 108 */           Image.Scale_Fp(dstML[i], srcML[i], mlconstants, mloffsets);
/*     */         } 
/*     */         break;
/*     */       default:
/* 113 */         className = getClass().getName();
/* 114 */         throw new RuntimeException(className + JaiI18N.getString("Generic2"));
/*     */     } 
/* 117 */     if (dstAccessor.isDataCopy()) {
/* 118 */       dstAccessor.clampDataArrays();
/* 119 */       dstAccessor.copyDataToRaster();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\mlib\MlibRescaleOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */