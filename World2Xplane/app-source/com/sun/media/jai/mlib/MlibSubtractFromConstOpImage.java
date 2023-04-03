/*     */ package com.sun.media.jai.mlib;
/*     */ 
/*     */ import com.sun.media.jai.util.ImageUtil;
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
/*     */ final class MlibSubtractFromConstOpImage extends PointOpImage {
/*     */   private double[] constants;
/*     */   
/*     */   public MlibSubtractFromConstOpImage(RenderedImage source, Map config, ImageLayout layout, double[] constants) {
/*  46 */     super(source, layout, config, true);
/*  47 */     this.constants = MlibUtils.initConstants(constants, getSampleModel().getNumBands());
/*  50 */     permitInPlaceOperation();
/*     */   }
/*     */   
/*     */   protected void computeRect(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/*     */     int constInt[], i;
/*     */     String className;
/*  66 */     Raster source = sources[0];
/*  67 */     int formatTag = MediaLibAccessor.findCompatibleTag(sources, dest);
/*  70 */     MediaLibAccessor srcAccessor = new MediaLibAccessor(source, destRect, formatTag);
/*  72 */     MediaLibAccessor dstAccessor = new MediaLibAccessor(dest, destRect, formatTag);
/*  75 */     mediaLibImage[] srcML = srcAccessor.getMediaLibImages();
/*  76 */     mediaLibImage[] dstML = dstAccessor.getMediaLibImages();
/*  78 */     switch (dstAccessor.getDataType()) {
/*     */       case 0:
/*     */       case 1:
/*     */       case 2:
/*     */       case 3:
/*  83 */         constInt = new int[this.constants.length];
/*  84 */         for (i = 0; i < this.constants.length; i++)
/*  85 */           constInt[i] = ImageUtil.clampRoundInt(this.constants[i]); 
/*  88 */         for (i = 0; i < dstML.length; i++) {
/*  89 */           int[] mlconstants = dstAccessor.getIntParameters(i, constInt);
/*  90 */           Image.ConstSub(dstML[i], srcML[i], mlconstants);
/*     */         } 
/*     */         break;
/*     */       case 4:
/*     */       case 5:
/*  96 */         for (i = 0; i < dstML.length; i++) {
/*  97 */           double[] mlconstants = dstAccessor.getDoubleParameters(i, this.constants);
/*  98 */           Image.ConstSub_Fp(dstML[i], srcML[i], mlconstants);
/*     */         } 
/*     */         break;
/*     */       default:
/* 103 */         className = getClass().getName();
/* 104 */         throw new RuntimeException(className + JaiI18N.getString("Generic2"));
/*     */     } 
/* 107 */     if (dstAccessor.isDataCopy()) {
/* 108 */       dstAccessor.clampDataArrays();
/* 109 */       dstAccessor.copyDataToRaster();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\mlib\MlibSubtractFromConstOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */