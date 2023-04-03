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
/*     */ final class MlibMultiplyConstOpImage extends PointOpImage {
/*     */   private double[] constants;
/*     */   
/*     */   public MlibMultiplyConstOpImage(RenderedImage source, Map config, ImageLayout layout, double[] constants) {
/*  45 */     super(source, layout, config, true);
/*  46 */     this.constants = MlibUtils.initConstants(constants, getSampleModel().getNumBands());
/*  49 */     permitInPlaceOperation();
/*     */   }
/*     */   
/*     */   protected void computeRect(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/*     */     int i;
/*     */     String className;
/*  64 */     Raster source = sources[0];
/*  65 */     Rectangle srcRect = mapDestRect(destRect, 0);
/*  67 */     int formatTag = MediaLibAccessor.findCompatibleTag(sources, dest);
/*  69 */     MediaLibAccessor srcAccessor = new MediaLibAccessor(source, srcRect, formatTag);
/*  71 */     MediaLibAccessor dstAccessor = new MediaLibAccessor(dest, destRect, formatTag);
/*  74 */     mediaLibImage[] srcML = srcAccessor.getMediaLibImages();
/*  75 */     mediaLibImage[] dstML = dstAccessor.getMediaLibImages();
/*  77 */     switch (dstAccessor.getDataType()) {
/*     */       case 0:
/*     */       case 1:
/*     */       case 2:
/*     */       case 3:
/*  82 */         for (i = 0; i < dstML.length; i++) {
/*  83 */           double[] mlconstants = dstAccessor.getDoubleParameters(i, this.constants);
/*  84 */           Image.ConstMul(dstML[i], srcML[i], mlconstants);
/*     */         } 
/*     */         break;
/*     */       case 4:
/*     */       case 5:
/*  90 */         for (i = 0; i < dstML.length; i++) {
/*  91 */           double[] mlconstants = dstAccessor.getDoubleParameters(i, this.constants);
/*  92 */           Image.ConstMul_Fp(dstML[i], srcML[i], mlconstants);
/*     */         } 
/*     */         break;
/*     */       default:
/*  97 */         className = getClass().getName();
/*  98 */         throw new RuntimeException(className + JaiI18N.getString("Generic2"));
/*     */     } 
/* 101 */     if (dstAccessor.isDataCopy()) {
/* 102 */       dstAccessor.clampDataArrays();
/* 103 */       dstAccessor.copyDataToRaster();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\mlib\MlibMultiplyConstOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */