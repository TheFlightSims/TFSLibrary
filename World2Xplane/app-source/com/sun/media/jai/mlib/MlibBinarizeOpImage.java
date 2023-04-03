/*     */ package com.sun.media.jai.mlib;
/*     */ 
/*     */ import com.sun.media.jai.util.ImageUtil;
/*     */ import com.sun.media.jai.util.JDKWorkarounds;
/*     */ import com.sun.medialib.mlib.Image;
/*     */ import com.sun.medialib.mlib.mediaLibImage;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.image.ColorModel;
/*     */ import java.awt.image.MultiPixelPackedSampleModel;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.SampleModel;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.util.Map;
/*     */ import javax.media.jai.ImageLayout;
/*     */ import javax.media.jai.PointOpImage;
/*     */ 
/*     */ class MlibBinarizeOpImage extends PointOpImage {
/*     */   private double thresh;
/*     */   
/*     */   public MlibBinarizeOpImage(RenderedImage source, ImageLayout layout, Map config, double thresh) {
/*  72 */     super(source, layoutHelper(source, layout, config), config, true);
/*  77 */     this.thresh = thresh;
/*     */   }
/*     */   
/*     */   private static ImageLayout layoutHelper(RenderedImage source, ImageLayout il, Map config) {
/*  86 */     ImageLayout layout = (il == null) ? new ImageLayout() : (ImageLayout)il.clone();
/*  89 */     SampleModel sm = layout.getSampleModel(source);
/*  90 */     if (!ImageUtil.isBinary(sm)) {
/*  91 */       sm = new MultiPixelPackedSampleModel(0, layout.getTileWidth(source), layout.getTileHeight(source), 1);
/*  95 */       layout.setSampleModel(sm);
/*     */     } 
/*  98 */     ColorModel cm = layout.getColorModel(null);
/*  99 */     if (cm == null || !JDKWorkarounds.areCompatibleDataModels(sm, cm))
/* 101 */       layout.setColorModel(ImageUtil.getCompatibleColorModel(sm, config)); 
/* 105 */     return layout;
/*     */   }
/*     */   
/*     */   protected void computeRect(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/*     */     mediaLibImage[] srcML, dstML;
/*     */     int i;
/*     */     String className;
/* 119 */     Raster source = sources[0];
/* 121 */     Rectangle srcRect = mapDestRect(destRect, 0);
/* 125 */     int sourceFormatTag = MediaLibAccessor.findCompatibleTag(sources, source);
/* 130 */     int destFormatTag = dest.getSampleModel().getDataType() | 0x100 | 0x0;
/* 135 */     MediaLibAccessor srcAccessor = new MediaLibAccessor(source, srcRect, sourceFormatTag, false);
/* 137 */     MediaLibAccessor dstAccessor = new MediaLibAccessor(dest, destRect, destFormatTag, true);
/* 142 */     switch (srcAccessor.getDataType()) {
/*     */       case 0:
/*     */       case 1:
/*     */       case 2:
/*     */       case 3:
/* 147 */         srcML = srcAccessor.getMediaLibImages();
/* 148 */         dstML = dstAccessor.getMediaLibImages();
/* 149 */         for (i = 0; i < dstML.length; i++) {
/* 155 */           Image.Thresh1(dstML[i], srcML[i], new int[] { (int)this.thresh - 1 }, new int[] { 1 }, new int[] { 0 });
/*     */         } 
/*     */         break;
/*     */       case 4:
/*     */       case 5:
/* 164 */         srcML = srcAccessor.getMediaLibImages();
/* 165 */         dstML = dstAccessor.getMediaLibImages();
/* 166 */         for (i = 0; i < dstML.length; i++) {
/* 167 */           Image.Thresh1_Fp(dstML[i], srcML[i], new double[] { this.thresh }, new double[] { 1.0D }, new double[] { 0.0D });
/*     */         } 
/*     */         break;
/*     */       default:
/* 175 */         className = getClass().getName();
/* 176 */         throw new RuntimeException(JaiI18N.getString("Generic2"));
/*     */     } 
/* 179 */     if (dstAccessor.isDataCopy()) {
/* 180 */       dstAccessor.clampDataArrays();
/* 181 */       dstAccessor.copyDataToRaster();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\mlib\MlibBinarizeOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */