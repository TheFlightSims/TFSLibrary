/*     */ package com.sun.media.jai.mlib;
/*     */ 
/*     */ import com.sun.medialib.mlib.Image;
/*     */ import com.sun.medialib.mlib.mediaLibImage;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.util.Map;
/*     */ import javax.media.jai.AreaOpImage;
/*     */ import javax.media.jai.BorderExtender;
/*     */ import javax.media.jai.ImageLayout;
/*     */ import javax.media.jai.JAI;
/*     */ 
/*     */ final class MlibDilate3SquareOpImage extends AreaOpImage {
/*     */   private static Map configHelper(Map configuration) {
/*     */     Map config;
/*  50 */     if (configuration == null) {
/*  52 */       config = new RenderingHints(JAI.KEY_REPLACE_INDEX_COLOR_MODEL, Boolean.FALSE);
/*     */     } else {
/*  57 */       config = configuration;
/*  62 */       if (!config.containsKey(JAI.KEY_REPLACE_INDEX_COLOR_MODEL)) {
/*  63 */         RenderingHints hints = (RenderingHints)configuration;
/*  64 */         config = (RenderingHints)hints.clone();
/*  65 */         config.put(JAI.KEY_REPLACE_INDEX_COLOR_MODEL, Boolean.FALSE);
/*     */       } 
/*     */     } 
/*  69 */     return config;
/*     */   }
/*     */   
/*     */   public MlibDilate3SquareOpImage(RenderedImage source, BorderExtender extender, Map config, ImageLayout layout) {
/*  91 */     super(source, layout, configHelper(config), true, extender, 1, 1, 1, 1);
/*     */   }
/*     */   
/*     */   protected void computeRect(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/* 117 */     Raster source = sources[0];
/* 118 */     Rectangle srcRect = mapDestRect(destRect, 0);
/* 120 */     int formatTag = MediaLibAccessor.findCompatibleTag(sources, dest);
/* 122 */     MediaLibAccessor srcAccessor = new MediaLibAccessor(source, srcRect, formatTag, true);
/* 124 */     MediaLibAccessor dstAccessor = new MediaLibAccessor(dest, destRect, formatTag, true);
/* 126 */     int numBands = getSampleModel().getNumBands();
/* 129 */     mediaLibImage[] srcML = srcAccessor.getMediaLibImages();
/* 130 */     mediaLibImage[] dstML = dstAccessor.getMediaLibImages();
/* 131 */     for (int i = 0; i < dstML.length; i++) {
/*     */       String className;
/* 132 */       switch (dstAccessor.getDataType()) {
/*     */         case 0:
/*     */         case 1:
/*     */         case 2:
/*     */         case 3:
/* 137 */           Image.Dilate8(dstML[i], srcML[i]);
/*     */           break;
/*     */         case 4:
/*     */         case 5:
/* 141 */           Image.Dilate8_Fp(dstML[i], srcML[i]);
/*     */           break;
/*     */         default:
/* 144 */           className = getClass().getName();
/* 145 */           throw new RuntimeException(JaiI18N.getString("Generic2"));
/*     */       } 
/*     */     } 
/* 149 */     if (dstAccessor.isDataCopy())
/* 150 */       dstAccessor.copyDataToRaster(); 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\mlib\MlibDilate3SquareOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */