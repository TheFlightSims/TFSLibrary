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
/*     */ final class MlibDivideIntoConstOpImage extends PointOpImage {
/*     */   private double[] constants;
/*     */   
/*     */   public MlibDivideIntoConstOpImage(RenderedImage source, Map config, ImageLayout layout, double[] constants) {
/*  46 */     super(source, layout, config, true);
/*  47 */     this.constants = MlibUtils.initConstants(constants, getSampleModel().getNumBands());
/*  50 */     permitInPlaceOperation();
/*     */   }
/*     */   
/*     */   protected void computeRect(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/*     */     int i;
/*     */     String className;
/*  65 */     Raster source = sources[0];
/*  66 */     Rectangle srcRect = mapDestRect(destRect, 0);
/*  68 */     int formatTag = MediaLibAccessor.findCompatibleTag(sources, dest);
/*  70 */     MediaLibAccessor srcAccessor = new MediaLibAccessor(source, srcRect, formatTag);
/*  72 */     MediaLibAccessor dstAccessor = new MediaLibAccessor(dest, destRect, formatTag);
/*  75 */     mediaLibImage[] srcML = srcAccessor.getMediaLibImages();
/*  76 */     mediaLibImage[] dstML = dstAccessor.getMediaLibImages();
/*  78 */     switch (dstAccessor.getDataType()) {
/*     */       case 0:
/*     */       case 1:
/*     */       case 2:
/*     */       case 3:
/*  83 */         for (i = 0; i < dstML.length; i++) {
/*  84 */           double[] mlconstants = dstAccessor.getDoubleParameters(i, this.constants);
/*  85 */           Image.ConstDiv(dstML[i], srcML[i], mlconstants);
/*     */         } 
/*     */         break;
/*     */       case 4:
/*     */       case 5:
/*  91 */         for (i = 0; i < dstML.length; i++) {
/*  92 */           double[] mlconstants = dstAccessor.getDoubleParameters(i, this.constants);
/*  93 */           Image.ConstDiv_Fp(dstML[i], srcML[i], mlconstants);
/*     */         } 
/*     */         break;
/*     */       default:
/*  98 */         className = getClass().getName();
/*  99 */         throw new RuntimeException(className + JaiI18N.getString("Generic2"));
/*     */     } 
/* 102 */     if (dstAccessor.isDataCopy()) {
/* 103 */       dstAccessor.clampDataArrays();
/* 104 */       dstAccessor.copyDataToRaster();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\mlib\MlibDivideIntoConstOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */